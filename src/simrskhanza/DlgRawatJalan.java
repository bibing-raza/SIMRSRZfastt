/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */
package simrskhanza;

import permintaan.DlgPermintaanLabRAZA;
import bridging.ICareRiwayatPerawatan;
import inventory.DlgPemberianObat;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgPeresepanDokter;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgDiagnosaPenyakit;
import permintaan.DlgPermintaanRadiologi;
import rekammedis.RMPenilaianAwalKeperawatanIGDrz;
import rekammedis.RMPenilaianAwalKeperawatanKebidanan;
import rekammedis.RMPenilaianAwalKeperawatanRalan;
import rekammedis.RMPenilaianAwalMedikIGD;
import rekammedis.RMPenilaianAwalMedikObstetriRalan;
import rekammedis.RMPenilaianAwalMedisRalanGeriatri;
import rekammedis.RMPenilaianAwalMedisRalanMata;
import rekammedis.RMPenilaianAwalMedisRalanTHT;
import rekammedis.RMPenilaianTambahanGeriatri;
import rekammedis.RMTransferSerahTerimaIGD;
import rekammedis.RMTriaseIGD;

/**
 *
 * @author dosen
 */
public final class DlgRawatJalan extends javax.swing.JDialog {
    private final DefaultTableModel tabModeDrPr, tabModePemeriksaanDr,
            tabModeResepObat, tabModePemeriksaanPr, tabModeLab1, 
            tabModeRad1, tabModeFarmasi, tabModeKunjungan, tabModeResep1, tabModeResep2, 
            tabModeRujukan, tabModeTglBeriObat, tabModeRiwItemObat;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps3, ps4, ps5, ps6, ps7, psFar, psLab1, psRad1, psparu, psRiwKunj, 
            psPet, psR1, psR2, psru1, psTglBO, psRiwIO;
    private ResultSet rs, rs2, rs3, rs4, rsDiag, rsDiag1, rsObat, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rsLab1, rsRad1,
            rsLIS1, rsLIS2, rsLIS3, rsLISMaster, rsparu, rsFar, rsRiwKunj, rsPet, rsR1, rsR2, rsru1, 
            rs13, rs14, rsTglBO, rsRiwIO, rsrad, rshslRad, rsprmrj;
    private int i = 0, n = 0, pilih_prmrj = 0, x = 0, k = 0, cekSuratTindakan = 0, lis1 = 0, lis2 = 0, lisM = 0, cekPilihanRehab = 0,
            ceksensusparu = 0, z = 0, cekRujukInternal = 0, x1 = 0, cekDataPetugas = 0, j = 0, cekPemeriksaan = 0;
    private String kode_poli = "", cekIGD = "", a = "", orang1 = "", orang2 = "", nmOrang1 = "", nmOrang2 = "", mencari = "",
            noordeR = "", noordeR1 = "", noiD = "", waktuSimpanRad = "", tglPeriksaLAB = "", jamPeriksaLAB = "", cekppok = "",
            cekobattb = "", PoliKhusus = "", polinya = "", gudang = "", norw_dipilih = "", kddokter_dipilih = "", noMinta = "",
            diperiksa = "", cekDiagnosa = "", cekResep = "", tglResep = "", cekTotPemeriksaan = "";
    private final Properties prop = new Properties();
    private String sql, host = "", jawaban = "", tglSimpanRujukan = "", tglPemberianObat = "", resepDipilih = "", prmrj = "";
    private Date dateReg, timeReg, dateSimpan, timeSimpan;
    private DlgPasien pasien = new DlgPasien(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariPerawatanRalan perawatan = new DlgCariPerawatanRalan(null, false);
    private DlgRujukanPoliInternal dlgrjk = new DlgRujukanPoliInternal(null, false);

    /* Creates new form DlgPerawatan
     *
     * @param parent
     * @param modal
     */
    public DlgRawatJalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabModeDrPr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Perawatan/Tindakan", "Kode Dokter",
            "Dokter Yg Menangani", "NIP", "Petugas Yg Menangani", "Biaya", "Tgl.Input", "Jam Input", "Kode", "Tarif Dokter", "Tarif Petugas", "KSO"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRawatDrPr.setModel(tabModeDrPr);
        tbRawatDrPr.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRawatDrPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbRawatDrPr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(55);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {//sembunyi
                //column.setPreferredWidth(90);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {//sembunyi
                //column.setPreferredWidth(90);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(180);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(70);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRawatDrPr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePemeriksaanDr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Dokter Yg Menangani", "No.R.M.", "Nama Pasien", "Tgl.Input", "Jam Input", "Suhu(C)", "Tensi", "Nadi(/menit)",
            "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "GCS(E,V,M)", "Keluhan", "Pemeriksaan", "Alergi", "Imun Ke",
            "Diagnosa Pasien", "Rencana Follow Up", "Rincian Tindakan", "Terapi", "Data PRMRJ", "SPO2"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaanDr.setModel(tabModePemeriksaanDr);
        tbPemeriksaanDr.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaanDr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 24; i++) {
            TableColumn column = tbPemeriksaanDr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(280);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(180);
            } else if (i == 15) {
                column.setPreferredWidth(180);
            } else if (i == 16) {
                column.setPreferredWidth(130);
            } else if (i == 17) {
                column.setPreferredWidth(50);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
                column.setPreferredWidth(180);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(75);
            } else if (i == 23) {
                column.setPreferredWidth(60);
            }
        }
        tbPemeriksaanDr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePemeriksaanPr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Petugas Yg Menangani", "No.R.M.", "Nama Pasien", "Tgl.Input", "Jam Input", "Suhu(C)", "Tensi", "Nadi(/menit)",
            "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "GCS(E,V,M)", "Keluhan", "Pemeriksaan", "Alergi", "Imun Ke",
            "Diagnosa Pasien", "Rencana Follow Up", "nip", "Rincian Tindakan", "Terapi", "kd_poli", "spo2"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaanPr.setModel(tabModePemeriksaanPr);
        tbPemeriksaanPr.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaanPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbPemeriksaanPr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(190);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(180);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(180);
            } else if (i == 15) {
                column.setPreferredWidth(180);
            } else if (i == 16) {
                column.setPreferredWidth(130);
            } else if (i == 17) {
                column.setPreferredWidth(50);
            } else if (i == 18) {
                column.setPreferredWidth(180);
            } else if (i == 19) {
                column.setPreferredWidth(180);
            } else if (i == 20) {
//                column.setPreferredWidth(180);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 21) {
                column.setPreferredWidth(180);
            } else if (i == 22) {
                column.setPreferredWidth(180);
            } else if (i == 23) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 24) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemeriksaanPr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeResepObat = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "Tgl.Input", "Jam Input", "Nama Obat", "Status", "Nama Dokter", "Id"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbResepObat.setModel(tabModeResepObat);
        tbResepObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbResepObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbResepObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(240);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbResepObat.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeLab1 = new DefaultTableModel(null, new Object[]{
            "No.", "Nama Pemeriksaan Lab.", "Tgl. Permintaan", "Jam Permintaan", "Diperiksa", "norawat", "No. Permintaan", "CITO"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPeriksaLab.setModel(tabModeLab1);
        tbPeriksaLab.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPeriksaLab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPeriksaLab.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(95);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(115);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            }
        }
        tbPeriksaLab.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeRad1 = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "Jns. Pemeriksaan Radiologi", "Tgl. Permintaan",
            "Jam", "Dokter yg. meminta", "No. Order", "Status", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPeriksaRad.setModel(tabModeRad1);
        tbPeriksaRad.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPeriksaRad.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPeriksaRad.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbPeriksaRad.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeFarmasi = new DefaultTableModel(null, new Object[]{"Nama Obat/Alkes", "Satuan", "Stok Apotek IGD (UMUM)","Stok Apotek Sentral (BPJS)"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabModeFarmasi);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 4; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(350);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabModeResep1 = new DefaultTableModel(null, new Object[]{
            "Tgl. Resep", "Dokter Peresep", "Poliklinik","tgl_resep", "kdpoli"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemberianResep.setModel(tabModeResep1);
        tbPemberianResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberianResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbPemberianResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(170);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemberianResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResep2 = new DefaultTableModel(null, new Object[]{
            "Cek", "no_rawat", "Tgl. Resep", "jam_input", "Nama Item Obat", "status", "nm_dokter", "id"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbItemResep.setModel(tabModeResep2);
        tbItemResep.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemResep.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbItemResep.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(32);
            } else if (i == 1) {
//                column.setPreferredWidth(105);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
//                column.setPreferredWidth(80);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(350);
            } else if (i == 5) {
//                column.setPreferredWidth(50);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
//                column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItemResep.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRujukan = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "Tgl. Dirujuk", "Dari Poliklinik", "Oleh Dokter", "Ket. Rujukan", "Poliklinik Tujuan",
            "Jawaban Rujukan", "Status Balasan", "tglRencanaRujukan", "statusJawaban", "tglsimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = false;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRujukan.setModel(tabModeRujukan);
        tbRujukan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRujukan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 11; i++) {
            TableColumn column = tbRujukan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
//                column.setPreferredWidth(250);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRujukan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeTglBeriObat = new DefaultTableModel(null, new Object[]{
            "tgl", "Tanggal","Jlh. Item", "Poliklinik", "Dokter Peresep"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTglBeriObat.setModel(tabModeTglBeriObat);
        tbTglBeriObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTglBeriObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbTglBeriObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);                
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(56);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } 
        }
        tbTglBeriObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwItemObat = new DefaultTableModel(null, new Object[]{
            "Tanggal","Nama Obat/Alkes/BHP", "Jumlah"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbItemObat.setModel(tabModeRiwItemObat);
        tbItemObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItemObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbItemObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(64);            
            } else if (i == 1) {
                column.setPreferredWidth(250);
            } else if (i == 2) {
                column.setPreferredWidth(56);
            } 
        }
        tbItemObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TKdPrw.setDocument(new batasInput((byte) 15).getKata(TKdPrw));
        kdptg.setDocument(new batasInput((byte) 20).getKata(kdptg));
        KdDok.setDocument(new batasInput((byte) 20).getKata(KdDok));
        TSuhu.setDocument(new batasInput((byte) 5).getKata(TSuhu));
        TTensi.setDocument(new batasInput((byte) 7).getKata(TTensi));
        TKeluhan.setDocument(new batasInput((int) 400).getKata(TKeluhan));
        TPemeriksaan.setDocument(new batasInput((int) 400).getKata(TPemeriksaan));
        TAlergi.setDocument(new batasInput((int) 50).getKata(TAlergi));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        TGCS.setDocument(new batasInput((byte) 10).getKata(TGCS));
        TTinggi.setDocument(new batasInput((byte) 5).getKata(TTinggi));
        TBerat.setDocument(new batasInput((byte) 5).getKata(TBerat));
        TNadi.setDocument(new batasInput((byte) 3).getOnlyAngka(TNadi));
        Tspo21.setDocument(new batasInput((byte) 3).getOnlyAngka(Tspo21));
        Tspo2.setDocument(new batasInput((byte) 3).getOnlyAngka(Tspo2));
        TRespirasi.setDocument(new batasInput((byte) 3).getOnlyAngka(TRespirasi));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPemeriksaanDokter();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPemeriksaanDokter();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPemeriksaanDokter();
                    }
                }
            });
        }
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        perawatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (perawatan.getTable().getSelectedRow() != -1) {
                        TKdPrw.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 1).toString());
                        TNmPrw.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 2).toString());
                        BagianRS.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 5).toString());
                        Bhp.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 6).toString());
                        JmDokter.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 7).toString());
                        JmPerawat.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 8).toString());
                        KSO.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 9).toString());
                        Menejemen.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 10).toString());
                        TTnd.setText(perawatan.getTable().getValueAt(perawatan.getTable().getSelectedRow(), 4).toString());
                        Valid.isiNmPrwtn(TNmPrw.getText());
                    }
                    TKdPrw.requestFocus();
//                    tampilDrPr();
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

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        KdDok.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        KdDok.requestFocus();
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
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatJalan")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        if (TabRawat.getSelectedIndex() == 0) {
                            kdptg.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            Valid.isiKdPtgs(kdptg.getText());
                            Valid.isiNmPtgs(TPerawat.getText());
                            kdptg.requestFocus();
                        } else if (TabRawat.getSelectedIndex() == 2) {
                            kdptg1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                            TPerawat1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            kdptg1.requestFocus();
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

        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgFrekuensiPenyakitRalan")) {
                    if (poli.getTable().getSelectedRow() != -1) {
//                        if (pilihan == 1) {
                        kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                        TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            switch (TStatus.getText()) {
//                                case "Baru":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                                case "Lama":
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
//                                    break;
//                                default:
//                                    TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
//                                    break;
//                            }
//                            isNumber();
                        kdpoli.requestFocus();
                        isCek();
                        
//                        } else if (pilihan == 2) {
//                            CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
//                            CrPoli.requestFocus();
//                            tampil();
                    }
                }
//                }
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

        ChkInput.setSelected(false);
        isForm();
        jam();
        tampilDrPr();
        ChkAccor.setSelected(true);
        isMenu();

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("simrskhanza.DlgRawatJalan.<init>() : " + e);
        }

        LoadHTML.setEditable(true);
        LoadHTML1.setEditable(true);
        LoadHTML2.setEditable(true);
        LoadHTML3.setEditable(true);
        LoadHTML4.setEditable(true);
        LoadHTML5.setEditable(true);
        LoadHTML6.setEditable(true);
        LoadHTML7.setEditable(true);

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditorKit(kit);
        LoadHTML1.setEditorKit(kit);
        LoadHTML2.setEditorKit(kit);
        LoadHTML3.setEditorKit(kit);
        LoadHTML4.setEditorKit(kit);
        LoadHTML5.setEditorKit(kit);
        LoadHTML6.setEditorKit(kit);
        LoadHTML7.setEditorKit(kit);

        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML1.setDocument(doc);
        LoadHTML2.setDocument(doc);
        LoadHTML3.setDocument(doc);
        LoadHTML4.setDocument(doc);
        LoadHTML5.setDocument(doc);
        LoadHTML6.setDocument(doc);
        LoadHTML7.setDocument(doc);
        LoadHTML.setEditable(false);
        LoadHTML1.setEditable(false);
        LoadHTML2.setEditable(false);
        LoadHTML3.setEditable(false);
        LoadHTML4.setEditable(false);
        LoadHTML5.setEditable(false);
        LoadHTML6.setEditable(false);
        LoadHTML7.setEditable(false);

        LoadHTML.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

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
        
        LoadHTML3.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML4.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML5.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML6.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML7.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
    int y = 0, w = 0, urut;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDiagnosa = new javax.swing.JMenuItem();
        MnContengResep = new javax.swing.JMenu();
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        MnPermintaanPenunjang = new javax.swing.JMenu();
        MnPermintaanLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnHapusPenunjang = new javax.swing.JMenu();
        MnHapusPermintaanLab = new javax.swing.JMenuItem();
        MnHapusPeriksaRadiologi = new javax.swing.JMenuItem();
        MnRekamMedis = new javax.swing.JMenu();
        MnPenilaianAwalMedisRalanGeriatri = new javax.swing.JMenuItem();
        MnPenilaianTambahanGeriatri = new javax.swing.JMenuItem();
        MnPenilaianAwalKeperawatanRalan = new javax.swing.JMenuItem();
        MnPenilaianAwalKeperawatanKebidanan = new javax.swing.JMenuItem();
        MnPenilaianAwalMedisRalanTHT = new javax.swing.JMenuItem();
        MnPenilaianAwalMedisRalanMata = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetri = new javax.swing.JMenuItem();
        MnRiwayatPerawatanICareNoKartu = new javax.swing.JMenuItem();
        MnSuratPengantarRanap = new javax.swing.JMenuItem();
        MnRehabMedik = new javax.swing.JMenuItem();
        MnDataParu = new javax.swing.JMenuItem();
        MnRujukanInternalPoli = new javax.swing.JMenuItem();
        MnSuratTindakanDokter = new javax.swing.JMenuItem();
        MnStatusPasienPerKunjungan = new javax.swing.JMenuItem();
        MnStatusPasienAllKunjungan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnPrinPRMRJ = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnCetakJawabanRujukan = new javax.swing.JMenuItem();
        WindowDataParu = new javax.swing.JDialog();
        internalFrame12 = new widget.InternalFrame();
        BtnCloseIn5 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel57 = new widget.Label();
        no_rawat = new widget.TextBox();
        no_rm = new widget.TextBox();
        jLabel58 = new widget.Label();
        nm_pasien = new widget.TextBox();
        jLabel59 = new widget.Label();
        tgl_reg = new widget.TextBox();
        jLabel60 = new widget.Label();
        nm_poli = new widget.TextBox();
        jLabel61 = new widget.Label();
        serangan = new widget.RadioButton();
        konsultasi = new widget.RadioButton();
        jLabel62 = new widget.Label();
        lain = new widget.RadioButton();
        bpjs = new widget.RadioButton();
        dot = new widget.RadioButton();
        puskes = new widget.RadioButton();
        jLabel63 = new widget.Label();
        Scroll31 = new widget.ScrollPane();
        ket_paru = new widget.TextArea();
        BtnBaru = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit1 = new widget.Button();
        WindowRiwayatKunjungan = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        Scroll32 = new widget.ScrollPane();
        tbRiwayatKunj = new widget.Table();
        jLabel65 = new widget.Label();
        jLabel66 = new widget.Label();
        pasiendipilih = new widget.TextBox();
        BtnRM = new widget.Button();
        WindowRiwayatResep = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        panelGlass6 = new widget.panelisi();
        ChkPoli = new widget.CekBox();
        jLabel68 = new widget.Label();
        cmbConteng = new widget.ComboBox();
        BtnCopyResep = new widget.Button();
        BtnCloseIn8 = new widget.Button();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        Scroll34 = new widget.ScrollPane();
        tbPemberianResep = new widget.Table();
        jPanel7 = new javax.swing.JPanel();
        Scroll35 = new widget.ScrollPane();
        tbItemResep = new widget.Table();
        WindowRehabMedik = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        BtnCloseIn9 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel69 = new widget.Label();
        cmbRM = new widget.ComboBox();
        BagianRS = new javax.swing.JTextField();
        Bhp = new javax.swing.JTextField();
        JmDokter = new javax.swing.JTextField();
        JmPerawat = new javax.swing.JTextField();
        TTnd = new javax.swing.JTextField();
        KSO = new javax.swing.JTextField();
        Menejemen = new javax.swing.JTextField();
        noIdObat = new widget.TextBox();
        unitRJ = new widget.TextBox();
        DiagnosaRJdokter = new widget.TextBox();
        DiagnosaRJpetugas = new widget.TextBox();
        TIdObat = new widget.TextBox();
        TNoRw2 = new widget.TextBox();
        TglKunRwt = new widget.Tanggal();
        noIdObatCopy = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        BtnCari = new widget.Button();
        BtnCariRekamMedis = new widget.Button();
        panelGlass9 = new widget.panelisi();
        ChkTanggal = new widget.CekBox();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel75 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel24 = new widget.Label();
        TCariPasien = new widget.TextBox();
        btnPasien = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRawatDrPr = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel14 = new widget.Label();
        kdptg = new widget.TextBox();
        BtnSeekPetugas2 = new widget.Button();
        TPerawat = new widget.TextBox();
        jLabel12 = new widget.Label();
        KdDok = new widget.TextBox();
        TDokter = new widget.TextBox();
        BtnSeekDokter2 = new widget.Button();
        jLabel13 = new widget.Label();
        label_tot_pemeriksaan = new widget.Label();
        internalFrame5 = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        Scroll36 = new widget.ScrollPane();
        FormInput2 = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        jLabel7 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel4 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel16 = new widget.Label();
        TBerat = new widget.TextBox();
        jLabel9 = new widget.Label();
        jLabel17 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel25 = new widget.Label();
        cmbImun = new widget.ComboBox();
        jLabel18 = new widget.Label();
        TNadi = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel20 = new widget.Label();
        TRespirasi = new widget.TextBox();
        jLabel22 = new widget.Label();
        TGCS = new widget.TextBox();
        jLabel26 = new widget.Label();
        jLabel28 = new widget.Label();
        Scroll6 = new widget.ScrollPane();
        TDiagnosa = new widget.TextArea();
        Scroll7 = new widget.ScrollPane();
        TKeluhan = new widget.TextArea();
        Scroll8 = new widget.ScrollPane();
        TPemeriksaan = new widget.TextArea();
        Scroll9 = new widget.ScrollPane();
        TAlergi = new widget.TextArea();
        Scroll21 = new widget.ScrollPane();
        TRncanaFolow = new widget.TextArea();
        jLabel54 = new widget.Label();
        Scroll22 = new widget.ScrollPane();
        TRincianTindakan = new widget.TextArea();
        jLabel29 = new widget.Label();
        Scroll28 = new widget.ScrollPane();
        TTerapi = new widget.TextArea();
        ChkPemeriksaan = new widget.CekBox();
        ChkCopyPemeriksaanDR = new widget.CekBox();
        ChkPrmrj = new widget.CekBox();
        jLabel44 = new widget.Label();
        Tspo2 = new widget.TextBox();
        TabPemeriksaanDokter = new javax.swing.JTabbedPane();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaanDr = new widget.Table();
        Scroll29 = new widget.ScrollPane();
        tbPeriksaLab = new widget.Table();
        Scroll30 = new widget.ScrollPane();
        tbPeriksaRad = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        panelGlass14 = new widget.panelisi();
        Scroll37 = new widget.ScrollPane();
        FormInput3 = new widget.PanelBiasa();
        jLabel19 = new widget.Label();
        jLabel30 = new widget.Label();
        TSuhu1 = new widget.TextBox();
        jLabel31 = new widget.Label();
        TTensi1 = new widget.TextBox();
        jLabel32 = new widget.Label();
        TBerat1 = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        TTinggi1 = new widget.TextBox();
        jLabel35 = new widget.Label();
        cmbImun1 = new widget.ComboBox();
        jLabel36 = new widget.Label();
        TNadi1 = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        TRespirasi1 = new widget.TextBox();
        jLabel39 = new widget.Label();
        TGCS1 = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        Scroll11 = new widget.ScrollPane();
        TDiagnosa1 = new widget.TextArea();
        Scroll12 = new widget.ScrollPane();
        TKeluhan1 = new widget.TextArea();
        Scroll13 = new widget.ScrollPane();
        TPemeriksaan1 = new widget.TextArea();
        Scroll14 = new widget.ScrollPane();
        TAlergi1 = new widget.TextArea();
        jLabel42 = new widget.Label();
        kdptg1 = new widget.TextBox();
        TPerawat1 = new widget.TextBox();
        BtnSeekPetugas1 = new widget.Button();
        Scroll23 = new widget.ScrollPane();
        TRncanaFolow1 = new widget.TextArea();
        jLabel55 = new widget.Label();
        Scroll24 = new widget.ScrollPane();
        TRincianTindakan1 = new widget.TextArea();
        jLabel56 = new widget.Label();
        Scroll26 = new widget.ScrollPane();
        TTerapi1 = new widget.TextArea();
        jLabel43 = new widget.Label();
        Tspo21 = new widget.TextBox();
        Scroll10 = new widget.ScrollPane();
        tbPemeriksaanPr = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        Scroll15 = new widget.ScrollPane();
        LoadHTML5 = new widget.editorpane();
        internalFrame6 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel27 = new widget.Label();
        TResepObat = new widget.TextBox();
        BtnSimpanObat = new widget.Button();
        BtnEditObat = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelGlass16 = new widget.panelisi();
        BtnPrinResep = new widget.Button();
        BtnCopyResepTerakhir = new widget.Button();
        BtnResep = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbResepObat = new widget.Table();
        PanelInput1 = new javax.swing.JPanel();
        ChkInput1 = new widget.CekBox();
        PanelRiwayatObat = new javax.swing.JPanel();
        panelGlass18 = new widget.panelisi();
        Scroll44 = new widget.ScrollPane();
        tbTglBeriObat = new widget.Table();
        panelGlass17 = new widget.panelisi();
        ChkPoli1 = new widget.CekBox();
        jLabel74 = new widget.Label();
        Scroll45 = new widget.ScrollPane();
        tbItemObat = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel64 = new widget.Label();
        TCariObat = new widget.TextBox();
        BtnCari1 = new widget.Button();
        Scroll33 = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        internalFrame11 = new widget.InternalFrame();
        Scroll27 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        internalFrame10 = new widget.InternalFrame();
        Scroll25 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        internalFrame16 = new widget.InternalFrame();
        Scroll41 = new widget.ScrollPane();
        jPanel8 = new javax.swing.JPanel();
        panelisi8 = new widget.panelisi();
        jLabel5 = new widget.Label();
        jLabel70 = new widget.Label();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        TDariPoli = new widget.TextBox();
        tglDirujuk = new widget.TextBox();
        OlehDokter = new widget.TextBox();
        Scroll39 = new widget.ScrollPane();
        TKetAsalRujukan = new widget.TextArea();
        jLabel73 = new widget.Label();
        Tnorawat = new widget.TextBox();
        panelisi6 = new widget.panelisi();
        jLabel77 = new widget.Label();
        Scroll40 = new widget.ScrollPane();
        TJwbnRujukan = new widget.TextArea();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        dokterMenjawab = new widget.TextBox();
        poliMenjawab = new widget.TextBox();
        jLabel81 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        Scroll42 = new widget.ScrollPane();
        tbRujukan = new widget.Table();
        internalFrame17 = new widget.InternalFrame();
        Scroll38 = new widget.ScrollPane();
        LoadHTML3 = new widget.editorpane();
        internalFrame18 = new widget.InternalFrame();
        Scroll43 = new widget.ScrollPane();
        LoadHTML4 = new widget.editorpane();
        internalFrame19 = new widget.InternalFrame();
        Scroll46 = new widget.ScrollPane();
        LoadHTML6 = new widget.editorpane();
        internalFrame20 = new widget.InternalFrame();
        Scroll47 = new widget.ScrollPane();
        LoadHTML7 = new widget.editorpane();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        btnTindakan = new widget.Button();
        TKdPrw = new widget.TextBox();
        TNmPrw = new widget.TextBox();
        jLabel23 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        label_rehab = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        ScrollMenu = new widget.ScrollPane();
        FormMenu = new widget.PanelBiasa();
        BtnTarifTindakan = new widget.Button();
        BtnPemeriksaanDR = new widget.Button();
        BtnPemeriksaanPR = new widget.Button();
        BtnPRMRJ = new widget.Button();
        BtnCatatanResep = new widget.Button();
        BtnRingkasanRiwayat = new widget.Button();
        BtnRingkasanPulangRanap = new widget.Button();
        BtnPenilaianKeperawatan = new widget.Button();
        BtnMenjawabRujukan = new widget.Button();
        BtnPenilaianAwalMedisTHT = new widget.Button();
        BtnHasilPemeriksaanRad = new widget.Button();
        BtnPenilaianAwalMedisMata = new widget.Button();
        BtnHasilPemeriksaanLab = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDiagnosa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDiagnosa.setText("Diagnosa Pasien");
        MnDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDiagnosa.setIconTextGap(5);
        MnDiagnosa.setName("MnDiagnosa"); // NOI18N
        MnDiagnosa.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDiagnosaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDiagnosa);

        MnContengResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnContengResep.setText("Conteng Item Resep Obat");
        MnContengResep.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnContengResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnContengResep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnContengResep.setIconTextGap(5);
        MnContengResep.setName("MnContengResep"); // NOI18N
        MnContengResep.setOpaque(true);
        MnContengResep.setPreferredSize(new java.awt.Dimension(245, 26));

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Semuanya");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        MnContengResep.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Conteng Semua");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        MnContengResep.add(MnDibatalkan);

        jPopupMenu1.add(MnContengResep);

        MnPermintaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanPenunjang.setText("Permintaan Penunjang Medis");
        MnPermintaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanPenunjang.setIconTextGap(5);
        MnPermintaanPenunjang.setName("MnPermintaanPenunjang"); // NOI18N
        MnPermintaanPenunjang.setOpaque(true);
        MnPermintaanPenunjang.setPreferredSize(new java.awt.Dimension(245, 26));

        MnPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPermintaanLab.setText("Permintaan Periksa Laboratorium");
        MnPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPermintaanLab.setIconTextGap(5);
        MnPermintaanLab.setName("MnPermintaanLab"); // NOI18N
        MnPermintaanLab.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPermintaanLabActionPerformed(evt);
            }
        });
        MnPermintaanPenunjang.add(MnPermintaanLab);

        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Permintaan Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnPermintaanPenunjang.add(MnPeriksaRadiologi);

        jPopupMenu1.add(MnPermintaanPenunjang);

        MnHapusPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusPenunjang.setText("Hapus Permintaan Penunjang Medis");
        MnHapusPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusPenunjang.setIconTextGap(5);
        MnHapusPenunjang.setName("MnHapusPenunjang"); // NOI18N
        MnHapusPenunjang.setOpaque(true);
        MnHapusPenunjang.setPreferredSize(new java.awt.Dimension(245, 26));

        MnHapusPermintaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusPermintaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusPermintaanLab.setText("Hapus Permintaan Laboratorium");
        MnHapusPermintaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusPermintaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusPermintaanLab.setIconTextGap(5);
        MnHapusPermintaanLab.setName("MnHapusPermintaanLab"); // NOI18N
        MnHapusPermintaanLab.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusPermintaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusPermintaanLabActionPerformed(evt);
            }
        });
        MnHapusPenunjang.add(MnHapusPermintaanLab);

        MnHapusPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusPeriksaRadiologi.setText("Hapus Permintaan Radiologi");
        MnHapusPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusPeriksaRadiologi.setIconTextGap(5);
        MnHapusPeriksaRadiologi.setName("MnHapusPeriksaRadiologi"); // NOI18N
        MnHapusPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(220, 26));
        MnHapusPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnHapusPenunjang.add(MnHapusPeriksaRadiologi);

        jPopupMenu1.add(MnHapusPenunjang);

        MnRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRekamMedis.setText("Data Rekam Medis");
        MnRekamMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekamMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRekamMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRekamMedis.setIconTextGap(5);
        MnRekamMedis.setName("MnRekamMedis"); // NOI18N
        MnRekamMedis.setOpaque(true);
        MnRekamMedis.setPreferredSize(new java.awt.Dimension(245, 26));

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
                MnPenilaianAwalMedisRalanGeriatriActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnPenilaianAwalMedisRalanGeriatri);

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
                MnPenilaianTambahanGeriatriActionPerformed(evt);
            }
        });
        MnRekamMedis.add(MnPenilaianTambahanGeriatri);

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
        MnRekamMedis.add(MnPenilaianAwalKeperawatanRalan);

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
        MnRekamMedis.add(MnPenilaianAwalKeperawatanKebidanan);

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
        MnRekamMedis.add(MnPenilaianAwalMedisRalanTHT);

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
        MnRekamMedis.add(MnPenilaianAwalMedisRalanMata);

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
        MnRekamMedis.add(MnAsesmenMedikObstetri);

        jPopupMenu1.add(MnRekamMedis);

        MnRiwayatPerawatanICareNoKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerawatanICareNoKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatPerawatanICareNoKartu.setText("Cek Riwayat Perawatan ICare BPJS");
        MnRiwayatPerawatanICareNoKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatPerawatanICareNoKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatPerawatanICareNoKartu.setIconTextGap(5);
        MnRiwayatPerawatanICareNoKartu.setName("MnRiwayatPerawatanICareNoKartu"); // NOI18N
        MnRiwayatPerawatanICareNoKartu.setPreferredSize(new java.awt.Dimension(245, 26));
        MnRiwayatPerawatanICareNoKartu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerawatanICareNoKartuBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatPerawatanICareNoKartu);

        MnSuratPengantarRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratPengantarRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratPengantarRanap.setText("Surat Pengantar Rawat Inap");
        MnSuratPengantarRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratPengantarRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratPengantarRanap.setIconTextGap(5);
        MnSuratPengantarRanap.setName("MnSuratPengantarRanap"); // NOI18N
        MnSuratPengantarRanap.setPreferredSize(new java.awt.Dimension(245, 26));
        MnSuratPengantarRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratPengantarRanapBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratPengantarRanap);

        MnRehabMedik.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRehabMedik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRehabMedik.setText("Pilihan Rehabilitasi Medik");
        MnRehabMedik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRehabMedik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRehabMedik.setIconTextGap(5);
        MnRehabMedik.setName("MnRehabMedik"); // NOI18N
        MnRehabMedik.setPreferredSize(new java.awt.Dimension(245, 26));
        MnRehabMedik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRehabMedikActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRehabMedik);

        MnDataParu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataParu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataParu.setText("Data Pasien Paru");
        MnDataParu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataParu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataParu.setIconTextGap(5);
        MnDataParu.setName("MnDataParu"); // NOI18N
        MnDataParu.setPreferredSize(new java.awt.Dimension(245, 26));
        MnDataParu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataParuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataParu);

        MnRujukanInternalPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukanInternalPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukanInternalPoli.setText("Rujukan Internal Poliklinik");
        MnRujukanInternalPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukanInternalPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukanInternalPoli.setIconTextGap(5);
        MnRujukanInternalPoli.setName("MnRujukanInternalPoli"); // NOI18N
        MnRujukanInternalPoli.setPreferredSize(new java.awt.Dimension(245, 26));
        MnRujukanInternalPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukanInternalPoliBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRujukanInternalPoli);

        MnSuratTindakanDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSuratTindakanDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSuratTindakanDokter.setText("Surat Tindakan Kedokteran");
        MnSuratTindakanDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSuratTindakanDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSuratTindakanDokter.setIconTextGap(5);
        MnSuratTindakanDokter.setName("MnSuratTindakanDokter"); // NOI18N
        MnSuratTindakanDokter.setPreferredSize(new java.awt.Dimension(245, 26));
        MnSuratTindakanDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratTindakanDokterActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSuratTindakanDokter);

        MnStatusPasienPerKunjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusPasienPerKunjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusPasienPerKunjungan.setText("Lembar Status Pasien PerKunjungan");
        MnStatusPasienPerKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusPasienPerKunjungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusPasienPerKunjungan.setIconTextGap(5);
        MnStatusPasienPerKunjungan.setName("MnStatusPasienPerKunjungan"); // NOI18N
        MnStatusPasienPerKunjungan.setPreferredSize(new java.awt.Dimension(245, 26));
        MnStatusPasienPerKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusPasienPerKunjunganBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusPasienPerKunjungan);

        MnStatusPasienAllKunjungan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatusPasienAllKunjungan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatusPasienAllKunjungan.setText("Lembar Status Pasien Semua Kunjungan");
        MnStatusPasienAllKunjungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatusPasienAllKunjungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatusPasienAllKunjungan.setIconTextGap(5);
        MnStatusPasienAllKunjungan.setName("MnStatusPasienAllKunjungan"); // NOI18N
        MnStatusPasienAllKunjungan.setPreferredSize(new java.awt.Dimension(245, 26));
        MnStatusPasienAllKunjungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnStatusPasienAllKunjunganBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnStatusPasienAllKunjungan);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnPrinPRMRJ.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinPRMRJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPrinPRMRJ.setText("Cetak Lembar PRMRJ");
        MnPrinPRMRJ.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinPRMRJ.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinPRMRJ.setIconTextGap(5);
        MnPrinPRMRJ.setName("MnPrinPRMRJ"); // NOI18N
        MnPrinPRMRJ.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPrinPRMRJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinPRMRJActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnPrinPRMRJ);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnCetakJawabanRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakJawabanRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakJawabanRujukan.setText("Surat Jawaban Rujukan");
        MnCetakJawabanRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakJawabanRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakJawabanRujukan.setIconTextGap(5);
        MnCetakJawabanRujukan.setName("MnCetakJawabanRujukan"); // NOI18N
        MnCetakJawabanRujukan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnCetakJawabanRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakJawabanRujukanActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCetakJawabanRujukan);

        WindowDataParu.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataParu.setName("WindowDataParu"); // NOI18N
        WindowDataParu.setUndecorated(true);
        WindowDataParu.setResizable(false);

        internalFrame12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Tambahan Sensus Poliklinik Paru ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(null);

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
        internalFrame12.add(BtnCloseIn5);
        BtnCloseIn5.setBounds(440, 250, 100, 30);

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
        internalFrame12.add(BtnSimpan5);
        BtnSimpan5.setBounds(20, 250, 90, 30);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("No. Rawat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame12.add(jLabel57);
        jLabel57.setBounds(0, 20, 77, 23);

        no_rawat.setEditable(false);
        no_rawat.setForeground(new java.awt.Color(0, 0, 0));
        no_rawat.setHighlighter(null);
        no_rawat.setName("no_rawat"); // NOI18N
        no_rawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                no_rawatKeyPressed(evt);
            }
        });
        internalFrame12.add(no_rawat);
        no_rawat.setBounds(81, 20, 130, 23);

        no_rm.setEditable(false);
        no_rm.setForeground(new java.awt.Color(0, 0, 0));
        no_rm.setName("no_rm"); // NOI18N
        internalFrame12.add(no_rm);
        no_rm.setBounds(81, 49, 80, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Pasien : ");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame12.add(jLabel58);
        jLabel58.setBounds(0, 49, 77, 23);

        nm_pasien.setEditable(false);
        nm_pasien.setForeground(new java.awt.Color(0, 0, 0));
        nm_pasien.setName("nm_pasien"); // NOI18N
        internalFrame12.add(nm_pasien);
        nm_pasien.setBounds(165, 49, 390, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Tgl. Kunjungan : ");
        jLabel59.setName("jLabel59"); // NOI18N
        internalFrame12.add(jLabel59);
        jLabel59.setBounds(215, 20, 90, 23);

        tgl_reg.setEditable(false);
        tgl_reg.setForeground(new java.awt.Color(0, 0, 0));
        tgl_reg.setHighlighter(null);
        tgl_reg.setName("tgl_reg"); // NOI18N
        tgl_reg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_regKeyPressed(evt);
            }
        });
        internalFrame12.add(tgl_reg);
        tgl_reg.setBounds(306, 20, 100, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Poliklinik : ");
        jLabel60.setName("jLabel60"); // NOI18N
        internalFrame12.add(jLabel60);
        jLabel60.setBounds(0, 78, 77, 23);

        nm_poli.setEditable(false);
        nm_poli.setForeground(new java.awt.Color(0, 0, 0));
        nm_poli.setName("nm_poli"); // NOI18N
        internalFrame12.add(nm_poli);
        nm_poli.setBounds(81, 78, 474, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("PPOK : ");
        jLabel61.setName("jLabel61"); // NOI18N
        internalFrame12.add(jLabel61);
        jLabel61.setBounds(0, 107, 77, 23);

        serangan.setBackground(new java.awt.Color(240, 250, 230));
        serangan.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        serangan.setText("Serangan");
        serangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        serangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        serangan.setName("serangan"); // NOI18N
        serangan.setPreferredSize(new java.awt.Dimension(95, 23));
        serangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seranganActionPerformed(evt);
            }
        });
        internalFrame12.add(serangan);
        serangan.setBounds(80, 107, 80, 23);

        konsultasi.setBackground(new java.awt.Color(240, 250, 230));
        konsultasi.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        konsultasi.setText("Konsultasi");
        konsultasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        konsultasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        konsultasi.setName("konsultasi"); // NOI18N
        konsultasi.setPreferredSize(new java.awt.Dimension(95, 23));
        konsultasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                konsultasiActionPerformed(evt);
            }
        });
        internalFrame12.add(konsultasi);
        konsultasi.setBounds(165, 107, 80, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Obat TB : ");
        jLabel62.setName("jLabel62"); // NOI18N
        internalFrame12.add(jLabel62);
        jLabel62.setBounds(0, 137, 77, 23);

        lain.setBackground(new java.awt.Color(240, 250, 230));
        lain.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        lain.setText("Lain-lain");
        lain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        lain.setName("lain"); // NOI18N
        lain.setPreferredSize(new java.awt.Dimension(95, 23));
        lain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lainActionPerformed(evt);
            }
        });
        internalFrame12.add(lain);
        lain.setBounds(80, 137, 80, 23);

        bpjs.setBackground(new java.awt.Color(240, 250, 230));
        bpjs.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        bpjs.setText("BPJS");
        bpjs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        bpjs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        bpjs.setName("bpjs"); // NOI18N
        bpjs.setPreferredSize(new java.awt.Dimension(95, 23));
        bpjs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bpjsActionPerformed(evt);
            }
        });
        internalFrame12.add(bpjs);
        bpjs.setBounds(165, 137, 50, 23);

        dot.setBackground(new java.awt.Color(240, 250, 230));
        dot.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        dot.setText("D.O.T");
        dot.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dot.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        dot.setName("dot"); // NOI18N
        dot.setPreferredSize(new java.awt.Dimension(95, 23));
        dot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dotActionPerformed(evt);
            }
        });
        internalFrame12.add(dot);
        dot.setBounds(220, 137, 60, 23);

        puskes.setBackground(new java.awt.Color(240, 250, 230));
        puskes.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        puskes.setText("Puskesmas");
        puskes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        puskes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        puskes.setName("puskes"); // NOI18N
        puskes.setPreferredSize(new java.awt.Dimension(95, 23));
        puskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puskesActionPerformed(evt);
            }
        });
        internalFrame12.add(puskes);
        puskes.setBounds(285, 137, 80, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ket. : ");
        jLabel63.setName("jLabel63"); // NOI18N
        internalFrame12.add(jLabel63);
        jLabel63.setBounds(0, 167, 77, 23);

        Scroll31.setName("Scroll31"); // NOI18N
        Scroll31.setOpaque(true);

        ket_paru.setColumns(20);
        ket_paru.setRows(5);
        ket_paru.setName("ket_paru"); // NOI18N
        ket_paru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_paruKeyPressed(evt);
            }
        });
        Scroll31.setViewportView(ket_paru);

        internalFrame12.add(Scroll31);
        Scroll31.setBounds(80, 167, 470, 70);

        BtnBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaru.setMnemonic('B');
        BtnBaru.setText("Baru");
        BtnBaru.setToolTipText("Alt+B");
        BtnBaru.setName("BtnBaru"); // NOI18N
        BtnBaru.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruActionPerformed(evt);
            }
        });
        BtnBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBaruKeyPressed(evt);
            }
        });
        internalFrame12.add(BtnBaru);
        BtnBaru.setBounds(120, 250, 90, 30);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
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
        BtnHapus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapus1KeyPressed(evt);
            }
        });
        internalFrame12.add(BtnHapus1);
        BtnHapus1.setBounds(330, 250, 100, 30);

        BtnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit1.setMnemonic('G');
        BtnEdit1.setText("Ganti");
        BtnEdit1.setToolTipText("Alt+G");
        BtnEdit1.setName("BtnEdit1"); // NOI18N
        BtnEdit1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit1ActionPerformed(evt);
            }
        });
        BtnEdit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit1KeyPressed(evt);
            }
        });
        internalFrame12.add(BtnEdit1);
        BtnEdit1.setBounds(220, 250, 100, 30);

        WindowDataParu.getContentPane().add(internalFrame12, java.awt.BorderLayout.CENTER);

        WindowRiwayatKunjungan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatKunjungan.setName("WindowRiwayatKunjungan"); // NOI18N
        WindowRiwayatKunjungan.setUndecorated(true);
        WindowRiwayatKunjungan.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Kunjungan 7 Hari Yang Lalu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(null);

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
        internalFrame13.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(780, 315, 80, 30);

        Scroll32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Kunjungan Yang Tercatat ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll32.setName("Scroll32"); // NOI18N
        Scroll32.setOpaque(true);

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
        Scroll32.setViewportView(tbRiwayatKunj);

        internalFrame13.add(Scroll32);
        Scroll32.setBounds(15, 45, 850, 260);

        jLabel65.setForeground(new java.awt.Color(0, 51, 255));
        jLabel65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel65.setText("Mohon dilengkapi kekurangan data rekam medis rawat jalan pasien yang terdaftar pada tabel dibawah ini..!!");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        internalFrame13.add(jLabel65);
        jLabel65.setBounds(15, 20, 790, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Pasien Dipilih : ");
        jLabel66.setName("jLabel66"); // NOI18N
        internalFrame13.add(jLabel66);
        jLabel66.setBounds(20, 315, 80, 23);

        pasiendipilih.setEditable(false);
        pasiendipilih.setForeground(new java.awt.Color(0, 0, 0));
        pasiendipilih.setHighlighter(null);
        pasiendipilih.setName("pasiendipilih"); // NOI18N
        pasiendipilih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pasiendipilihKeyPressed(evt);
            }
        });
        internalFrame13.add(pasiendipilih);
        pasiendipilih.setBounds(100, 315, 520, 23);

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
        internalFrame13.add(BtnRM);
        BtnRM.setBounds(630, 315, 142, 30);

        WindowRiwayatKunjungan.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowRiwayatResep.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatResep.setName("WindowRiwayatResep"); // NOI18N
        WindowRiwayatResep.setUndecorated(true);
        WindowRiwayatResep.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Resep Rawat Jalan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.BorderLayout());

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkPoli.setBackground(new java.awt.Color(255, 255, 250));
        ChkPoli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPoli.setForeground(new java.awt.Color(0, 0, 0));
        ChkPoli.setText("Hanya Poliklinik Ini");
        ChkPoli.setBorderPainted(true);
        ChkPoli.setBorderPaintedFlat(true);
        ChkPoli.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoli.setName("ChkPoli"); // NOI18N
        ChkPoli.setPreferredSize(new java.awt.Dimension(130, 23));
        ChkPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoliActionPerformed(evt);
            }
        });
        panelGlass6.add(ChkPoli);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Conteng Item Resep : ");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass6.add(jLabel68);

        cmbConteng.setForeground(new java.awt.Color(0, 0, 0));
        cmbConteng.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Semuanya", "Dibatalkan" }));
        cmbConteng.setName("cmbConteng"); // NOI18N
        cmbConteng.setPreferredSize(new java.awt.Dimension(96, 23));
        cmbConteng.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbContengItemStateChanged(evt);
            }
        });
        panelGlass6.add(cmbConteng);

        BtnCopyResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnCopyResep.setMnemonic('R');
        BtnCopyResep.setText("Copy Resep");
        BtnCopyResep.setToolTipText("Alt+R");
        BtnCopyResep.setName("BtnCopyResep"); // NOI18N
        BtnCopyResep.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnCopyResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepActionPerformed(evt);
            }
        });
        BtnCopyResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCopyResepKeyPressed(evt);
            }
        });
        panelGlass6.add(BtnCopyResep);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setMnemonic('U');
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        panelGlass6.add(BtnCloseIn8);

        internalFrame15.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Pemberian Resep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel6.setName("jPanel6"); // NOI18N
        jPanel6.setOpaque(false);
        jPanel6.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll34.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        Scroll34.setName("Scroll34"); // NOI18N
        Scroll34.setOpaque(true);

        tbPemberianResep.setToolTipText("Silahkan Klik salah satu tgl. resep utk. melihat resep yg. pernah diberikan");
        tbPemberianResep.setName("tbPemberianResep"); // NOI18N
        tbPemberianResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianResepMouseClicked(evt);
            }
        });
        tbPemberianResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianResepKeyPressed(evt);
            }
        });
        Scroll34.setViewportView(tbPemberianResep);

        jPanel6.add(Scroll34, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Item Resep Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N
        jPanel7.setOpaque(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll35.setName("Scroll35"); // NOI18N
        Scroll35.setOpaque(true);

        tbItemResep.setToolTipText("Silahkan conteng item resep obat yg. dipilih / gunakan fitur conteng..!!");
        tbItemResep.setName("tbItemResep"); // NOI18N
        tbItemResep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemResepMouseClicked(evt);
            }
        });
        tbItemResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemResepKeyPressed(evt);
            }
        });
        Scroll35.setViewportView(tbItemResep);

        jPanel7.add(Scroll35, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7);

        internalFrame15.add(jPanel5, java.awt.BorderLayout.CENTER);

        WindowRiwayatResep.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        WindowRehabMedik.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRehabMedik.setName("WindowRehabMedik"); // NOI18N
        WindowRehabMedik.setUndecorated(true);
        WindowRehabMedik.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Rehabilitasi Medik ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame14.setLayout(null);

        BtnCloseIn9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn9.setMnemonic('U');
        BtnCloseIn9.setText("Tutup");
        BtnCloseIn9.setToolTipText("Alt+U");
        BtnCloseIn9.setName("BtnCloseIn9"); // NOI18N
        BtnCloseIn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn9ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnCloseIn9);
        BtnCloseIn9.setBounds(410, 30, 100, 30);

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
        internalFrame14.add(BtnSimpan7);
        BtnSimpan7.setBounds(300, 30, 100, 30);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Jenis Rehabilitasi Medik : ");
        jLabel69.setName("jLabel69"); // NOI18N
        internalFrame14.add(jLabel69);
        jLabel69.setBounds(0, 32, 150, 23);

        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "FISIOTERAPI", "OKUPASI TERAPI", "TERAPI WICARA" }));
        cmbRM.setName("cmbRM"); // NOI18N
        internalFrame14.add(cmbRM);
        cmbRM.setBounds(155, 32, 140, 23);

        WindowRehabMedik.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        BagianRS.setEditable(false);
        BagianRS.setText("0");
        BagianRS.setName("BagianRS"); // NOI18N

        Bhp.setEditable(false);
        Bhp.setText("0");
        Bhp.setName("Bhp"); // NOI18N

        JmDokter.setEditable(false);
        JmDokter.setText("0");
        JmDokter.setName("JmDokter"); // NOI18N

        JmPerawat.setEditable(false);
        JmPerawat.setText("0");
        JmPerawat.setName("JmPerawat"); // NOI18N

        TTnd.setEditable(false);
        TTnd.setText("0");
        TTnd.setName("TTnd"); // NOI18N

        KSO.setEditable(false);
        KSO.setText("0");
        KSO.setName("KSO"); // NOI18N

        Menejemen.setEditable(false);
        Menejemen.setText("0");
        Menejemen.setName("Menejemen"); // NOI18N

        noIdObat.setForeground(new java.awt.Color(0, 0, 0));
        noIdObat.setHighlighter(null);
        noIdObat.setName("noIdObat"); // NOI18N
        noIdObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noIdObatKeyPressed(evt);
            }
        });

        unitRJ.setForeground(new java.awt.Color(0, 0, 0));
        unitRJ.setHighlighter(null);
        unitRJ.setName("unitRJ"); // NOI18N
        unitRJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unitRJKeyPressed(evt);
            }
        });

        DiagnosaRJdokter.setForeground(new java.awt.Color(0, 0, 0));
        DiagnosaRJdokter.setHighlighter(null);
        DiagnosaRJdokter.setName("DiagnosaRJdokter"); // NOI18N
        DiagnosaRJdokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaRJdokterKeyPressed(evt);
            }
        });

        DiagnosaRJpetugas.setForeground(new java.awt.Color(0, 0, 0));
        DiagnosaRJpetugas.setHighlighter(null);
        DiagnosaRJpetugas.setName("DiagnosaRJpetugas"); // NOI18N
        DiagnosaRJpetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DiagnosaRJpetugasKeyPressed(evt);
            }
        });

        TIdObat.setEnabled(false);
        TIdObat.setHighlighter(null);
        TIdObat.setName("TIdObat"); // NOI18N
        TIdObat.setPreferredSize(new java.awt.Dimension(1, 1));
        TIdObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TIdObatKeyPressed(evt);
            }
        });

        TNoRw2.setEditable(false);
        TNoRw2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw2.setHighlighter(null);
        TNoRw2.setName("TNoRw2"); // NOI18N
        TNoRw2.setPreferredSize(new java.awt.Dimension(1, 1));
        TNoRw2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRw2KeyPressed(evt);
            }
        });

        TglKunRwt.setEditable(false);
        TglKunRwt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2023" }));
        TglKunRwt.setDisplayFormat("dd-MM-yyyy");
        TglKunRwt.setName("TglKunRwt"); // NOI18N
        TglKunRwt.setOpaque(false);

        noIdObatCopy.setForeground(new java.awt.Color(0, 0, 0));
        noIdObatCopy.setHighlighter(null);
        noIdObatCopy.setName("noIdObatCopy"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBackground(new java.awt.Color(204, 255, 204));
        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Perawatan/Tindakan Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
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
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

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

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
        panelGlass8.add(LCount);

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

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass8.add(BtnCari);

        BtnCariRekamMedis.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariRekamMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/barralan.png"))); // NOI18N
        BtnCariRekamMedis.setMnemonic('K');
        BtnCariRekamMedis.setText("Riwayat Kunjungan");
        BtnCariRekamMedis.setToolTipText("Alt+K");
        BtnCariRekamMedis.setName("BtnCariRekamMedis"); // NOI18N
        BtnCariRekamMedis.setPreferredSize(new java.awt.Dimension(160, 30));
        BtnCariRekamMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariRekamMedisActionPerformed(evt);
            }
        });
        BtnCariRekamMedis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariRekamMedisKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnCariRekamMedis);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkTanggal.setBorder(null);
        ChkTanggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkTanggal.setText("Tgl.Rawat :");
        ChkTanggal.setBorderPainted(true);
        ChkTanggal.setBorderPaintedFlat(true);
        ChkTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setName("ChkTanggal"); // NOI18N
        ChkTanggal.setOpaque(false);
        ChkTanggal.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTanggalItemStateChanged(evt);
            }
        });
        panelGlass9.add(ChkTanggal);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jSeparator7.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator7.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator7.setName("jSeparator7"); // NOI18N
        jSeparator7.setOpaque(true);
        jSeparator7.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator7);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Poliklinik : ");
        jLabel75.setName("jLabel75"); // NOI18N
        jLabel75.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel75);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.setPreferredSize(new java.awt.Dimension(55, 24));
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdpoliKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        panelGlass9.add(kdpoli);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setHighlighter(null);
        TPoli.setName("TPoli"); // NOI18N
        TPoli.setPreferredSize(new java.awt.Dimension(185, 24));
        TPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TPoliKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPoliKeyPressed(evt);
            }
        });
        panelGlass9.add(TPoli);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.setPreferredSize(new java.awt.Dimension(30, 25));
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelGlass9.add(BtnUnit);

        jSeparator6.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator6.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator6.setName("jSeparator6"); // NOI18N
        jSeparator6.setOpaque(true);
        jSeparator6.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator6);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("No. RM :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass9.add(jLabel24);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(TCariPasien);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnPasien.setMnemonic('6');
        btnPasien.setToolTipText("Alt+6");
        btnPasien.setName("btnPasien"); // NOI18N
        btnPasien.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPasienActionPerformed(evt);
            }
        });
        btnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPasienKeyPressed(evt);
            }
        });
        panelGlass9.add(btnPasien);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass9.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(204, 255, 204));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "1. Tarif Pemeriksaan/Tindakan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbRawatDrPr.setAutoCreateRowSorter(true);
        tbRawatDrPr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatDrPr.setComponentPopupMenu(jPopupMenu1);
        tbRawatDrPr.setName("tbRawatDrPr"); // NOI18N
        tbRawatDrPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatDrPrMouseClicked(evt);
            }
        });
        tbRawatDrPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRawatDrPrKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbRawatDrPr);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 74));
        panelGlass11.setLayout(null);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Petugas : ");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(428, 10, 60, 23);

        kdptg.setEditable(false);
        kdptg.setForeground(new java.awt.Color(0, 0, 0));
        kdptg.setName("kdptg"); // NOI18N
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdptgKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelGlass11.add(kdptg);
        kdptg.setBounds(490, 10, 80, 23);

        BtnSeekPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeekPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeekPetugas2.setMnemonic('5');
        BtnSeekPetugas2.setToolTipText("ALt+5");
        BtnSeekPetugas2.setName("BtnSeekPetugas2"); // NOI18N
        BtnSeekPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPetugas2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSeekPetugas2);
        BtnSeekPetugas2.setBounds(830, 10, 28, 23);

        TPerawat.setEditable(false);
        TPerawat.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TPerawat.setName("TPerawat"); // NOI18N
        panelGlass11.add(TPerawat);
        TPerawat.setBounds(573, 10, 250, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Dokter :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 10, 53, 23);

        KdDok.setEditable(false);
        KdDok.setForeground(new java.awt.Color(0, 0, 0));
        KdDok.setName("KdDok"); // NOI18N
        KdDok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                KdDokKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokKeyPressed(evt);
            }
        });
        panelGlass11.add(KdDok);
        KdDok.setBounds(57, 10, 80, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        panelGlass11.add(TDokter);
        TDokter.setBounds(140, 10, 250, 23);

        BtnSeekDokter2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeekDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeekDokter2.setMnemonic('4');
        BtnSeekDokter2.setToolTipText("ALt+4");
        BtnSeekDokter2.setName("BtnSeekDokter2"); // NOI18N
        BtnSeekDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekDokter2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSeekDokter2);
        BtnSeekDokter2.setBounds(395, 10, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Total Biaya Pemeriksaan/Tindakan : ");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass11.add(jLabel13);
        jLabel13.setBounds(0, 40, 200, 23);

        label_tot_pemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        label_tot_pemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_tot_pemeriksaan.setText("label_tot_pemeriksaan");
        label_tot_pemeriksaan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        label_tot_pemeriksaan.setName("label_tot_pemeriksaan"); // NOI18N
        panelGlass11.add(label_tot_pemeriksaan);
        label_tot_pemeriksaan.setBounds(200, 40, 310, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("1.", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "2. Pemeriksaan Dokter", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 237));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        Scroll36.setName("Scroll36"); // NOI18N
        Scroll36.setOpaque(true);

        FormInput2.setBackground(new java.awt.Color(255, 255, 255));
        FormInput2.setBorder(null);
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(1087, 300));
        FormInput2.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Keluhan :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput2.add(jLabel8);
        jLabel8.setBounds(0, 30, 95, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Suhu Badan(C) :");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput2.add(jLabel7);
        jLabel7.setBounds(540, 5, 100, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        FormInput2.add(TSuhu);
        TSuhu.setBounds(645, 5, 43, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tensi :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput2.add(jLabel4);
        jLabel4.setBounds(690, 5, 70, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        FormInput2.add(TTensi);
        TTensi.setBounds(765, 5, 70, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Berat Badan(Kg) :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput2.add(jLabel16);
        jLabel16.setBounds(836, 5, 92, 23);

        TBerat.setForeground(new java.awt.Color(0, 0, 0));
        TBerat.setFocusTraversalPolicyProvider(true);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        FormInput2.add(TBerat);
        TBerat.setBounds(932, 5, 70, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Pemeriksaan :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput2.add(jLabel9);
        jLabel9.setBounds(0, 88, 95, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tinggi Badan(Cm) :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput2.add(jLabel17);
        jLabel17.setBounds(540, 33, 100, 23);

        TTinggi.setForeground(new java.awt.Color(0, 0, 0));
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        FormInput2.add(TTinggi);
        TTinggi.setBounds(645, 33, 43, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Imun Ke :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput2.add(jLabel25);
        jLabel25.setBounds(690, 33, 70, 23);

        cmbImun.setForeground(new java.awt.Color(0, 0, 0));
        cmbImun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" }));
        cmbImun.setName("cmbImun"); // NOI18N
        cmbImun.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbImun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbImunKeyPressed(evt);
            }
        });
        FormInput2.add(cmbImun);
        cmbImun.setBounds(765, 33, 48, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Nadi(/menit) :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput2.add(jLabel18);
        jLabel18.setBounds(836, 33, 92, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        FormInput2.add(TNadi);
        TNadi.setBounds(932, 33, 70, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Terapi :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput2.add(jLabel15);
        jLabel15.setBounds(0, 234, 95, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Respirasi(/menit) :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput2.add(jLabel20);
        jLabel20.setBounds(540, 61, 100, 23);

        TRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRespirasiActionPerformed(evt);
            }
        });
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        FormInput2.add(TRespirasi);
        TRespirasi.setBounds(645, 61, 43, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("GCS(E,V,M) :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput2.add(jLabel22);
        jLabel22.setBounds(690, 61, 70, 23);

        TGCS.setForeground(new java.awt.Color(0, 0, 0));
        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        FormInput2.add(TGCS);
        TGCS.setBounds(765, 61, 244, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Diagnosa :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput2.add(jLabel26);
        jLabel26.setBounds(0, 175, 95, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Rencana Follow Up :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput2.add(jLabel28);
        jLabel28.setBounds(540, 90, 100, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        TDiagnosa.setColumns(20);
        TDiagnosa.setRows(5);
        TDiagnosa.setName("TDiagnosa"); // NOI18N
        TDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosaKeyPressed(evt);
            }
        });
        Scroll6.setViewportView(TDiagnosa);

        FormInput2.add(Scroll6);
        Scroll6.setBounds(100, 175, 430, 55);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        TKeluhan.setColumns(20);
        TKeluhan.setRows(5);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        Scroll7.setViewportView(TKeluhan);

        FormInput2.add(Scroll7);
        Scroll7.setBounds(100, 30, 430, 55);

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        TPemeriksaan.setColumns(20);
        TPemeriksaan.setRows(5);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        Scroll8.setViewportView(TPemeriksaan);

        FormInput2.add(Scroll8);
        Scroll8.setBounds(100, 88, 430, 55);

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        TAlergi.setColumns(20);
        TAlergi.setRows(5);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        Scroll9.setViewportView(TAlergi);

        FormInput2.add(Scroll9);
        Scroll9.setBounds(645, 210, 430, 55);

        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        TRncanaFolow.setColumns(20);
        TRncanaFolow.setRows(5);
        TRncanaFolow.setName("TRncanaFolow"); // NOI18N
        TRncanaFolow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRncanaFolowKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(TRncanaFolow);

        FormInput2.add(Scroll21);
        Scroll21.setBounds(645, 90, 430, 55);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Rincian Tindakan :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput2.add(jLabel54);
        jLabel54.setBounds(540, 150, 100, 23);

        Scroll22.setName("Scroll22"); // NOI18N
        Scroll22.setOpaque(true);

        TRincianTindakan.setColumns(20);
        TRincianTindakan.setRows(5);
        TRincianTindakan.setName("TRincianTindakan"); // NOI18N
        TRincianTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRincianTindakanKeyPressed(evt);
            }
        });
        Scroll22.setViewportView(TRincianTindakan);

        FormInput2.add(Scroll22);
        Scroll22.setBounds(645, 150, 430, 55);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Alergi :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput2.add(jLabel29);
        jLabel29.setBounds(540, 210, 100, 23);

        Scroll28.setName("Scroll28"); // NOI18N
        Scroll28.setOpaque(true);

        TTerapi.setColumns(20);
        TTerapi.setRows(5);
        TTerapi.setName("TTerapi"); // NOI18N
        TTerapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapiKeyPressed(evt);
            }
        });
        Scroll28.setViewportView(TTerapi);

        FormInput2.add(Scroll28);
        Scroll28.setBounds(100, 234, 430, 55);

        ChkPemeriksaan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPemeriksaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPemeriksaan.setText("Lihat Hasil Pemeriksaan Pasien Oleh Perawat/Bidan");
        ChkPemeriksaan.setBorderPainted(true);
        ChkPemeriksaan.setBorderPaintedFlat(true);
        ChkPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPemeriksaan.setName("ChkPemeriksaan"); // NOI18N
        ChkPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPemeriksaanActionPerformed(evt);
            }
        });
        FormInput2.add(ChkPemeriksaan);
        ChkPemeriksaan.setBounds(100, 5, 310, 23);

        ChkCopyPemeriksaanDR.setBackground(new java.awt.Color(255, 255, 250));
        ChkCopyPemeriksaanDR.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCopyPemeriksaanDR.setForeground(new java.awt.Color(0, 0, 0));
        ChkCopyPemeriksaanDR.setText("Copy Pemeriksaan Dokter Ke Pemeriksaan Perawat/Bidan");
        ChkCopyPemeriksaanDR.setBorderPainted(true);
        ChkCopyPemeriksaanDR.setBorderPaintedFlat(true);
        ChkCopyPemeriksaanDR.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkCopyPemeriksaanDR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCopyPemeriksaanDR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCopyPemeriksaanDR.setName("ChkCopyPemeriksaanDR"); // NOI18N
        ChkCopyPemeriksaanDR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCopyPemeriksaanDRActionPerformed(evt);
            }
        });
        FormInput2.add(ChkCopyPemeriksaanDR);
        ChkCopyPemeriksaanDR.setBounds(645, 270, 350, 23);

        ChkPrmrj.setBackground(new java.awt.Color(255, 255, 250));
        ChkPrmrj.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPrmrj.setForeground(new java.awt.Color(0, 0, 0));
        ChkPrmrj.setText("Sebagai Data ke PRMRJ");
        ChkPrmrj.setBorderPainted(true);
        ChkPrmrj.setBorderPaintedFlat(true);
        ChkPrmrj.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPrmrj.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPrmrj.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPrmrj.setName("ChkPrmrj"); // NOI18N
        ChkPrmrj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPrmrjActionPerformed(evt);
            }
        });
        FormInput2.add(ChkPrmrj);
        ChkPrmrj.setBounds(100, 148, 220, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("SpO2(%) :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput2.add(jLabel44);
        jLabel44.setBounds(1005, 5, 60, 23);

        Tspo2.setForeground(new java.awt.Color(0, 0, 0));
        Tspo2.setFocusTraversalPolicyProvider(true);
        Tspo2.setName("Tspo2"); // NOI18N
        Tspo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspo2KeyPressed(evt);
            }
        });
        FormInput2.add(Tspo2);
        Tspo2.setBounds(1070, 5, 42, 23);

        Scroll36.setViewportView(FormInput2);

        panelGlass12.add(Scroll36, java.awt.BorderLayout.CENTER);

        internalFrame5.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabPemeriksaanDokter.setBackground(new java.awt.Color(250, 255, 245));
        TabPemeriksaanDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabPemeriksaanDokter.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabPemeriksaanDokter.setName("TabPemeriksaanDokter"); // NOI18N
        TabPemeriksaanDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPemeriksaanDokterMouseClicked(evt);
            }
        });

        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPemeriksaanDr.setAutoCreateRowSorter(true);
        tbPemeriksaanDr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaanDr.setComponentPopupMenu(jPopupMenu1);
        tbPemeriksaanDr.setName("tbPemeriksaanDr"); // NOI18N
        tbPemeriksaanDr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanDrMouseClicked(evt);
            }
        });
        tbPemeriksaanDr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanDrKeyPressed(evt);
            }
        });
        Scroll3.setViewportView(tbPemeriksaanDr);

        TabPemeriksaanDokter.addTab(".: Pemeriksaan Dokter", Scroll3);

        Scroll29.setComponentPopupMenu(jPopupMenu1);
        Scroll29.setName("Scroll29"); // NOI18N
        Scroll29.setOpaque(true);

        tbPeriksaLab.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbPeriksaLab.setComponentPopupMenu(jPopupMenu1);
        tbPeriksaLab.setName("tbPeriksaLab"); // NOI18N
        tbPeriksaLab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPeriksaLabMouseClicked(evt);
            }
        });
        tbPeriksaLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPeriksaLabKeyPressed(evt);
            }
        });
        Scroll29.setViewportView(tbPeriksaLab);

        TabPemeriksaanDokter.addTab(".: Periksa Laboratorium yang diminta", Scroll29);

        Scroll30.setComponentPopupMenu(jPopupMenu1);
        Scroll30.setName("Scroll30"); // NOI18N
        Scroll30.setOpaque(true);

        tbPeriksaRad.setAutoCreateRowSorter(true);
        tbPeriksaRad.setToolTipText("Silahkan klik untuk memilih data yang mau dihapus");
        tbPeriksaRad.setComponentPopupMenu(jPopupMenu1);
        tbPeriksaRad.setName("tbPeriksaRad"); // NOI18N
        tbPeriksaRad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPeriksaRadMouseClicked(evt);
            }
        });
        tbPeriksaRad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPeriksaRadKeyPressed(evt);
            }
        });
        Scroll30.setViewportView(tbPeriksaRad);

        TabPemeriksaanDokter.addTab(".: Periksa Radiologi yang diminta", Scroll30);

        internalFrame5.add(TabPemeriksaanDokter, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("2.", internalFrame5);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "3. Pemeriksaan Petugas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 230));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        Scroll37.setName("Scroll37"); // NOI18N
        Scroll37.setOpaque(true);

        FormInput3.setBackground(new java.awt.Color(255, 255, 255));
        FormInput3.setBorder(null);
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(1087, 282));
        FormInput3.setLayout(null);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Keluhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput3.add(jLabel19);
        jLabel19.setBounds(0, 5, 95, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Suhu Badan(C) :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput3.add(jLabel30);
        jLabel30.setBounds(540, 5, 100, 23);

        TSuhu1.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu1.setFocusTraversalPolicyProvider(true);
        TSuhu1.setName("TSuhu1"); // NOI18N
        TSuhu1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhu1KeyPressed(evt);
            }
        });
        FormInput3.add(TSuhu1);
        TSuhu1.setBounds(645, 5, 42, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tensi :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput3.add(jLabel31);
        jLabel31.setBounds(690, 5, 70, 23);

        TTensi1.setForeground(new java.awt.Color(0, 0, 0));
        TTensi1.setName("TTensi1"); // NOI18N
        TTensi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensi1KeyPressed(evt);
            }
        });
        FormInput3.add(TTensi1);
        TTensi1.setBounds(765, 5, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Berat Badan(Kg) :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput3.add(jLabel32);
        jLabel32.setBounds(836, 5, 92, 23);

        TBerat1.setForeground(new java.awt.Color(0, 0, 0));
        TBerat1.setFocusTraversalPolicyProvider(true);
        TBerat1.setName("TBerat1"); // NOI18N
        TBerat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBerat1KeyPressed(evt);
            }
        });
        FormInput3.add(TBerat1);
        TBerat1.setBounds(932, 5, 70, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pemeriksaan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput3.add(jLabel33);
        jLabel33.setBounds(0, 63, 95, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Tinggi Badan(Cm) :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput3.add(jLabel34);
        jLabel34.setBounds(540, 33, 100, 23);

        TTinggi1.setForeground(new java.awt.Color(0, 0, 0));
        TTinggi1.setName("TTinggi1"); // NOI18N
        TTinggi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggi1KeyPressed(evt);
            }
        });
        FormInput3.add(TTinggi1);
        TTinggi1.setBounds(645, 33, 42, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Imun Ke :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput3.add(jLabel35);
        jLabel35.setBounds(690, 33, 70, 23);

        cmbImun1.setForeground(new java.awt.Color(0, 0, 0));
        cmbImun1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13" }));
        cmbImun1.setName("cmbImun1"); // NOI18N
        cmbImun1.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbImun1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbImun1KeyPressed(evt);
            }
        });
        FormInput3.add(cmbImun1);
        cmbImun1.setBounds(765, 33, 48, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Nadi(/menit) :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput3.add(jLabel36);
        jLabel36.setBounds(836, 33, 92, 23);

        TNadi1.setForeground(new java.awt.Color(0, 0, 0));
        TNadi1.setFocusTraversalPolicyProvider(true);
        TNadi1.setName("TNadi1"); // NOI18N
        TNadi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadi1KeyPressed(evt);
            }
        });
        FormInput3.add(TNadi1);
        TNadi1.setBounds(932, 33, 70, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Alergi :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput3.add(jLabel37);
        jLabel37.setBounds(0, 182, 95, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Respirasi(/menit) :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput3.add(jLabel38);
        jLabel38.setBounds(540, 61, 100, 23);

        TRespirasi1.setForeground(new java.awt.Color(0, 0, 0));
        TRespirasi1.setName("TRespirasi1"); // NOI18N
        TRespirasi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRespirasi1ActionPerformed(evt);
            }
        });
        TRespirasi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasi1KeyPressed(evt);
            }
        });
        FormInput3.add(TRespirasi1);
        TRespirasi1.setBounds(645, 61, 42, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("GCS(E,V,M) :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput3.add(jLabel39);
        jLabel39.setBounds(690, 61, 70, 23);

        TGCS1.setForeground(new java.awt.Color(0, 0, 0));
        TGCS1.setFocusTraversalPolicyProvider(true);
        TGCS1.setName("TGCS1"); // NOI18N
        TGCS1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCS1KeyPressed(evt);
            }
        });
        FormInput3.add(TGCS1);
        TGCS1.setBounds(765, 61, 244, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Diagnosa :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput3.add(jLabel40);
        jLabel40.setBounds(0, 122, 95, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Rencana Follow Up :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput3.add(jLabel41);
        jLabel41.setBounds(540, 90, 100, 23);

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        TDiagnosa1.setColumns(20);
        TDiagnosa1.setRows(5);
        TDiagnosa1.setName("TDiagnosa1"); // NOI18N
        TDiagnosa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosa1KeyPressed(evt);
            }
        });
        Scroll11.setViewportView(TDiagnosa1);

        FormInput3.add(Scroll11);
        Scroll11.setBounds(100, 122, 430, 55);

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        TKeluhan1.setColumns(20);
        TKeluhan1.setRows(5);
        TKeluhan1.setName("TKeluhan1"); // NOI18N
        TKeluhan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhan1KeyPressed(evt);
            }
        });
        Scroll12.setViewportView(TKeluhan1);

        FormInput3.add(Scroll12);
        Scroll12.setBounds(100, 5, 430, 55);

        Scroll13.setName("Scroll13"); // NOI18N
        Scroll13.setOpaque(true);

        TPemeriksaan1.setColumns(20);
        TPemeriksaan1.setRows(5);
        TPemeriksaan1.setName("TPemeriksaan1"); // NOI18N
        TPemeriksaan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaan1KeyPressed(evt);
            }
        });
        Scroll13.setViewportView(TPemeriksaan1);

        FormInput3.add(Scroll13);
        Scroll13.setBounds(100, 63, 430, 55);

        Scroll14.setName("Scroll14"); // NOI18N
        Scroll14.setOpaque(true);

        TAlergi1.setColumns(20);
        TAlergi1.setRows(5);
        TAlergi1.setName("TAlergi1"); // NOI18N
        TAlergi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergi1KeyPressed(evt);
            }
        });
        Scroll14.setViewportView(TAlergi1);

        FormInput3.add(Scroll14);
        Scroll14.setBounds(100, 182, 430, 55);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Petugas :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput3.add(jLabel42);
        jLabel42.setBounds(0, 245, 95, 23);

        kdptg1.setForeground(new java.awt.Color(0, 0, 0));
        kdptg1.setName("kdptg1"); // NOI18N
        kdptg1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kdptg1KeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptg1KeyPressed(evt);
            }
        });
        FormInput3.add(kdptg1);
        kdptg1.setBounds(100, 245, 130, 23);

        TPerawat1.setEditable(false);
        TPerawat1.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat1.setForeground(new java.awt.Color(0, 0, 0));
        TPerawat1.setName("TPerawat1"); // NOI18N
        FormInput3.add(TPerawat1);
        TPerawat1.setBounds(235, 245, 290, 23);

        BtnSeekPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeekPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeekPetugas1.setMnemonic('5');
        BtnSeekPetugas1.setToolTipText("ALt+5");
        BtnSeekPetugas1.setName("BtnSeekPetugas1"); // NOI18N
        BtnSeekPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeekPetugas1ActionPerformed(evt);
            }
        });
        FormInput3.add(BtnSeekPetugas1);
        BtnSeekPetugas1.setBounds(528, 245, 28, 23);

        Scroll23.setName("Scroll23"); // NOI18N
        Scroll23.setOpaque(true);

        TRncanaFolow1.setColumns(20);
        TRncanaFolow1.setRows(5);
        TRncanaFolow1.setName("TRncanaFolow1"); // NOI18N
        TRncanaFolow1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRncanaFolow1KeyPressed(evt);
            }
        });
        Scroll23.setViewportView(TRncanaFolow1);

        FormInput3.add(Scroll23);
        Scroll23.setBounds(645, 90, 430, 55);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Rincian Tindakan :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput3.add(jLabel55);
        jLabel55.setBounds(540, 151, 100, 23);

        Scroll24.setName("Scroll24"); // NOI18N
        Scroll24.setOpaque(true);

        TRincianTindakan1.setColumns(20);
        TRincianTindakan1.setRows(5);
        TRincianTindakan1.setName("TRincianTindakan1"); // NOI18N
        TRincianTindakan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRincianTindakan1KeyPressed(evt);
            }
        });
        Scroll24.setViewportView(TRincianTindakan1);

        FormInput3.add(Scroll24);
        Scroll24.setBounds(645, 151, 430, 55);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Terapi :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput3.add(jLabel56);
        jLabel56.setBounds(540, 211, 100, 23);

        Scroll26.setName("Scroll26"); // NOI18N
        Scroll26.setOpaque(true);

        TTerapi1.setColumns(20);
        TTerapi1.setRows(5);
        TTerapi1.setName("TTerapi1"); // NOI18N
        TTerapi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTerapi1KeyPressed(evt);
            }
        });
        Scroll26.setViewportView(TTerapi1);

        FormInput3.add(Scroll26);
        Scroll26.setBounds(645, 211, 430, 55);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("SpO2(%) :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput3.add(jLabel43);
        jLabel43.setBounds(1005, 5, 60, 23);

        Tspo21.setForeground(new java.awt.Color(0, 0, 0));
        Tspo21.setFocusTraversalPolicyProvider(true);
        Tspo21.setName("Tspo21"); // NOI18N
        Tspo21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspo21KeyPressed(evt);
            }
        });
        FormInput3.add(Tspo21);
        Tspo21.setBounds(1070, 5, 42, 23);

        Scroll37.setViewportView(FormInput3);

        panelGlass14.add(Scroll37, java.awt.BorderLayout.CENTER);

        internalFrame8.add(panelGlass14, java.awt.BorderLayout.PAGE_START);

        Scroll10.setComponentPopupMenu(jPopupMenu1);
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbPemeriksaanPr.setAutoCreateRowSorter(true);
        tbPemeriksaanPr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaanPr.setComponentPopupMenu(jPopupMenu1);
        tbPemeriksaanPr.setName("tbPemeriksaanPr"); // NOI18N
        tbPemeriksaanPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanPrMouseClicked(evt);
            }
        });
        tbPemeriksaanPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanPrKeyPressed(evt);
            }
        });
        Scroll10.setViewportView(tbPemeriksaanPr);

        internalFrame8.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("3.", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " PROFIL RINGKAS MEDIS RAWAT JALAN (PRMRJ) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 13))); // NOI18N
        Scroll15.setComponentPopupMenu(jPopupMenu2);
        Scroll15.setName("Scroll15"); // NOI18N
        Scroll15.setOpaque(true);

        LoadHTML5.setBorder(null);
        LoadHTML5.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML5.setComponentPopupMenu(jPopupMenu2);
        LoadHTML5.setName("LoadHTML5"); // NOI18N
        Scroll15.setViewportView(LoadHTML5);

        internalFrame9.add(Scroll15, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("4.", internalFrame9);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "5. Catatan Resep Obat", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Nama Obat :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(jLabel27);

        TResepObat.setForeground(new java.awt.Color(0, 0, 0));
        TResepObat.setName("TResepObat"); // NOI18N
        TResepObat.setPreferredSize(new java.awt.Dimension(450, 24));
        TResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TResepObatKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TResepObatKeyPressed(evt);
            }
        });
        panelGlass13.add(TResepObat);

        BtnSimpanObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanObat.setMnemonic('8');
        BtnSimpanObat.setText("Simpan Obat");
        BtnSimpanObat.setToolTipText("Alt+8");
        BtnSimpanObat.setName("BtnSimpanObat"); // NOI18N
        BtnSimpanObat.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnSimpanObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanObatActionPerformed(evt);
            }
        });
        BtnSimpanObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanObatKeyPressed(evt);
            }
        });
        panelGlass13.add(BtnSimpanObat);

        BtnEditObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnEditObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEditObat.setMnemonic('G');
        BtnEditObat.setText("Ganti Obat");
        BtnEditObat.setToolTipText("");
        BtnEditObat.setName("BtnEditObat"); // NOI18N
        BtnEditObat.setPreferredSize(new java.awt.Dimension(110, 23));
        BtnEditObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditObatActionPerformed(evt);
            }
        });
        BtnEditObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditObatKeyPressed(evt);
            }
        });
        panelGlass13.add(BtnEditObat);

        internalFrame6.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Obat/Resep yang diberikan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(660, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass16.setName("panelGlass16"); // NOI18N
        panelGlass16.setPreferredSize(new java.awt.Dimension(44, 40));
        panelGlass16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 8));

        BtnPrinResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrinResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrinResep.setMnemonic('P');
        BtnPrinResep.setText("Print Resep");
        BtnPrinResep.setToolTipText("");
        BtnPrinResep.setName("BtnPrinResep"); // NOI18N
        BtnPrinResep.setPreferredSize(new java.awt.Dimension(114, 23));
        BtnPrinResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrinResepActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnPrinResep);

        BtnCopyResepTerakhir.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopyResepTerakhir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopyResepTerakhir.setMnemonic('P');
        BtnCopyResepTerakhir.setText("Copy Resep Terakhir");
        BtnCopyResepTerakhir.setToolTipText("");
        BtnCopyResepTerakhir.setName("BtnCopyResepTerakhir"); // NOI18N
        BtnCopyResepTerakhir.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnCopyResepTerakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopyResepTerakhirActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnCopyResepTerakhir);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/tasksgroup.png"))); // NOI18N
        BtnResep.setMnemonic('8');
        BtnResep.setText("Riwayat Resep");
        BtnResep.setToolTipText("Alt+8");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(140, 23));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass16.add(BtnResep);

        jPanel4.add(panelGlass16, java.awt.BorderLayout.PAGE_START);
        panelGlass16.getAccessibleContext().setAccessibleName("");

        Scroll4.setComponentPopupMenu(jPopupMenu1);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbResepObat.setAutoCreateRowSorter(true);
        tbResepObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbResepObat.setComponentPopupMenu(jPopupMenu1);
        tbResepObat.setName("tbResepObat"); // NOI18N
        tbResepObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbResepObatMouseClicked(evt);
            }
        });
        tbResepObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbResepObatKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbResepObat);

        jPanel4.add(Scroll4, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(816, 220));
        PanelInput1.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput1.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setText(".: Riwayat Obat Farmasi");
        ChkInput1.setToolTipText("");
        ChkInput1.setBorderPainted(true);
        ChkInput1.setBorderPaintedFlat(true);
        ChkInput1.setFocusable(false);
        ChkInput1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        ChkInput1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput1.setName("ChkInput1"); // NOI18N
        ChkInput1.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInput1ActionPerformed(evt);
            }
        });
        PanelInput1.add(ChkInput1, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.setName("PanelRiwayatObat"); // NOI18N
        PanelRiwayatObat.setOpaque(false);
        PanelRiwayatObat.setPreferredSize(new java.awt.Dimension(816, 150));
        PanelRiwayatObat.setLayout(new javax.swing.BoxLayout(PanelRiwayatObat, javax.swing.BoxLayout.LINE_AXIS));

        panelGlass18.setName("panelGlass18"); // NOI18N
        panelGlass18.setPreferredSize(new java.awt.Dimension(340, 40));
        panelGlass18.setLayout(new java.awt.BorderLayout());

        Scroll44.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tgl. Pemberian Obat ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll44.setName("Scroll44"); // NOI18N
        Scroll44.setOpaque(true);
        Scroll44.setPreferredSize(new java.awt.Dimension(150, 130));

        tbTglBeriObat.setAutoCreateRowSorter(true);
        tbTglBeriObat.setToolTipText("Silahkan klik salah satu tgl. utk melihat obatnya");
        tbTglBeriObat.setComponentPopupMenu(jPopupMenu1);
        tbTglBeriObat.setName("tbTglBeriObat"); // NOI18N
        tbTglBeriObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTglBeriObatMouseClicked(evt);
            }
        });
        tbTglBeriObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTglBeriObatKeyPressed(evt);
            }
        });
        Scroll44.setViewportView(tbTglBeriObat);

        panelGlass18.add(Scroll44, java.awt.BorderLayout.CENTER);

        panelGlass17.setName("panelGlass17"); // NOI18N
        panelGlass17.setPreferredSize(new java.awt.Dimension(44, 33));
        panelGlass17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 3));

        ChkPoli1.setBorder(null);
        ChkPoli1.setForeground(new java.awt.Color(0, 0, 0));
        ChkPoli1.setSelected(true);
        ChkPoli1.setText("Hanya dipoliklinik ini");
        ChkPoli1.setBorderPainted(true);
        ChkPoli1.setBorderPaintedFlat(true);
        ChkPoli1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ChkPoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPoli1.setName("ChkPoli1"); // NOI18N
        ChkPoli1.setOpaque(false);
        ChkPoli1.setPreferredSize(new java.awt.Dimension(127, 23));
        ChkPoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkPoli1ActionPerformed(evt);
            }
        });
        panelGlass17.add(ChkPoli1);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("untuk 5 kunjungan terakhir.");
        jLabel74.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        jLabel74.setPreferredSize(new java.awt.Dimension(170, 23));
        panelGlass17.add(jLabel74);

        panelGlass18.add(panelGlass17, java.awt.BorderLayout.PAGE_START);

        PanelRiwayatObat.add(panelGlass18);

        Scroll45.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Item Obat Sesuai Yang Diresepkan ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll45.setName("Scroll45"); // NOI18N
        Scroll45.setOpaque(true);
        Scroll45.setPreferredSize(new java.awt.Dimension(390, 423));

        tbItemObat.setAutoCreateRowSorter(true);
        tbItemObat.setToolTipText("Silahkan klik untuk memilih data obatnya");
        tbItemObat.setComponentPopupMenu(jPopupMenu1);
        tbItemObat.setName("tbItemObat"); // NOI18N
        tbItemObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItemObatMouseClicked(evt);
            }
        });
        tbItemObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItemObatKeyPressed(evt);
            }
        });
        Scroll45.setViewportView(tbItemObat);

        PanelRiwayatObat.add(Scroll45);

        PanelInput1.add(PanelRiwayatObat, java.awt.BorderLayout.CENTER);

        jPanel4.add(PanelInput1, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Obat/Alkes Farmasi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cari Obat/Alkes : ");
        jLabel64.setName("jLabel64"); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(jLabel64);

        TCariObat.setForeground(new java.awt.Color(0, 0, 0));
        TCariObat.setName("TCariObat"); // NOI18N
        TCariObat.setPreferredSize(new java.awt.Dimension(180, 23));
        TCariObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariObatKeyPressed(evt);
            }
        });
        panelisi4.add(TCariObat);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari1.setMnemonic('C');
        BtnCari1.setText("Cek");
        BtnCari1.setToolTipText("Alt+C");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(65, 30));
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

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
            }
        });
        Scroll33.setViewportView(tbObat);

        jPanel2.add(Scroll33, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame6.add(jPanel1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("5.", internalFrame6);

        internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "6. Ringkasan Riwayat Rawat Jalan", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setComponentPopupMenu(jPopupMenu1);
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML.setComponentPopupMenu(jPopupMenu1);
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll5.setViewportView(LoadHTML);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("6.", internalFrame7);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "7. Ringkasan Pulang Rawat Inap", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll27.setComponentPopupMenu(jPopupMenu1);
        Scroll27.setName("Scroll27"); // NOI18N
        Scroll27.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setComponentPopupMenu(jPopupMenu1);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll27.setViewportView(LoadHTML2);

        internalFrame11.add(Scroll27, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("7.", internalFrame11);

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "8. Penilaian Awal Keperawatan/Assesmen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll25.setComponentPopupMenu(jPopupMenu1);
        Scroll25.setName("Scroll25"); // NOI18N
        Scroll25.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setComponentPopupMenu(jPopupMenu1);
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll25.setViewportView(LoadHTML1);

        internalFrame10.add(Scroll25, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("8.", internalFrame10);

        internalFrame16.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "9. Menjawab Rujukan Internal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setLayout(new java.awt.BorderLayout());

        Scroll41.setName("Scroll41"); // NOI18N
        Scroll41.setPreferredSize(new java.awt.Dimension(1089, 265));

        jPanel8.setName("jPanel8"); // NOI18N
        jPanel8.setOpaque(false);
        jPanel8.setPreferredSize(new java.awt.Dimension(1180, 320));
        jPanel8.setLayout(new java.awt.GridLayout(1, 0));

        panelisi8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Isi Pesan/Rujukan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelisi8.setName("panelisi8"); // NOI18N
        panelisi8.setPreferredSize(new java.awt.Dimension(100, 355));
        panelisi8.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dari Poliklinik : ");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi8.add(jLabel5);
        jLabel5.setBounds(0, 57, 95, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Tgl. Dirujuk : ");
        jLabel70.setName("jLabel70"); // NOI18N
        panelisi8.add(jLabel70);
        jLabel70.setBounds(240, 28, 75, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Oleh Dokter : ");
        jLabel71.setName("jLabel71"); // NOI18N
        panelisi8.add(jLabel71);
        jLabel71.setBounds(0, 86, 95, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Ket. Rujukan : ");
        jLabel72.setName("jLabel72"); // NOI18N
        panelisi8.add(jLabel72);
        jLabel72.setBounds(0, 115, 95, 23);

        TDariPoli.setEditable(false);
        TDariPoli.setForeground(new java.awt.Color(0, 0, 0));
        TDariPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TDariPoli.setName("TDariPoli"); // NOI18N
        TDariPoli.setPreferredSize(new java.awt.Dimension(185, 24));
        panelisi8.add(TDariPoli);
        TDariPoli.setBounds(98, 57, 410, 24);

        tglDirujuk.setEditable(false);
        tglDirujuk.setForeground(new java.awt.Color(0, 0, 0));
        tglDirujuk.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tglDirujuk.setName("tglDirujuk"); // NOI18N
        tglDirujuk.setPreferredSize(new java.awt.Dimension(185, 24));
        panelisi8.add(tglDirujuk);
        tglDirujuk.setBounds(317, 28, 190, 24);

        OlehDokter.setEditable(false);
        OlehDokter.setForeground(new java.awt.Color(0, 0, 0));
        OlehDokter.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        OlehDokter.setName("OlehDokter"); // NOI18N
        OlehDokter.setPreferredSize(new java.awt.Dimension(185, 24));
        panelisi8.add(OlehDokter);
        OlehDokter.setBounds(98, 86, 410, 24);

        Scroll39.setName("Scroll39"); // NOI18N

        TKetAsalRujukan.setEditable(false);
        TKetAsalRujukan.setColumns(20);
        TKetAsalRujukan.setRows(5);
        TKetAsalRujukan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TKetAsalRujukan.setName("TKetAsalRujukan"); // NOI18N
        TKetAsalRujukan.setPreferredSize(new java.awt.Dimension(250, 360));
        Scroll39.setViewportView(TKetAsalRujukan);

        panelisi8.add(Scroll39);
        Scroll39.setBounds(100, 115, 480, 190);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("No. Rawat : ");
        jLabel73.setName("jLabel73"); // NOI18N
        panelisi8.add(jLabel73);
        jLabel73.setBounds(0, 28, 95, 23);

        Tnorawat.setEditable(false);
        Tnorawat.setForeground(new java.awt.Color(0, 0, 0));
        Tnorawat.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        Tnorawat.setName("Tnorawat"); // NOI18N
        Tnorawat.setPreferredSize(new java.awt.Dimension(185, 24));
        panelisi8.add(Tnorawat);
        Tnorawat.setBounds(98, 28, 140, 24);

        jPanel8.add(panelisi8);

        panelisi6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Balasan/Jawaban Rujukan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N
        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 325));
        panelisi6.setLayout(null);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Uraian Balasan/Jawaban : ");
        jLabel77.setName("jLabel77"); // NOI18N
        panelisi6.add(jLabel77);
        jLabel77.setBounds(0, 115, 150, 23);

        Scroll40.setName("Scroll40"); // NOI18N
        Scroll40.setOpaque(true);

        TJwbnRujukan.setColumns(20);
        TJwbnRujukan.setRows(5);
        TJwbnRujukan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TJwbnRujukan.setName("TJwbnRujukan"); // NOI18N
        TJwbnRujukan.setPreferredSize(new java.awt.Dimension(250, 430));
        Scroll40.setViewportView(TJwbnRujukan);

        panelisi6.add(Scroll40);
        Scroll40.setBounds(155, 115, 420, 190);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Nama Dokter : ");
        jLabel79.setName("jLabel79"); // NOI18N
        panelisi6.add(jLabel79);
        jLabel79.setBounds(0, 86, 150, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Poliklinik : ");
        jLabel80.setName("jLabel80"); // NOI18N
        panelisi6.add(jLabel80);
        jLabel80.setBounds(0, 57, 150, 23);

        dokterMenjawab.setEditable(false);
        dokterMenjawab.setForeground(new java.awt.Color(0, 0, 0));
        dokterMenjawab.setName("dokterMenjawab"); // NOI18N
        panelisi6.add(dokterMenjawab);
        dokterMenjawab.setBounds(155, 86, 420, 23);

        poliMenjawab.setEditable(false);
        poliMenjawab.setForeground(new java.awt.Color(0, 0, 0));
        poliMenjawab.setName("poliMenjawab"); // NOI18N
        panelisi6.add(poliMenjawab);
        poliMenjawab.setBounds(155, 57, 420, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Status Balasan : ");
        jLabel81.setName("jLabel81"); // NOI18N
        panelisi6.add(jLabel81);
        jLabel81.setBounds(0, 28, 150, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Terjawab", "Belum Dijawab" }));
        cmbStatus.setSelectedIndex(1);
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(145, 23));
        panelisi6.add(cmbStatus);
        cmbStatus.setBounds(155, 28, 110, 23);

        jPanel8.add(panelisi6);

        Scroll41.setViewportView(jPanel8);

        internalFrame16.add(Scroll41, java.awt.BorderLayout.PAGE_START);

        Scroll42.setComponentPopupMenu(jPopupMenu1);
        Scroll42.setName("Scroll42"); // NOI18N
        Scroll42.setOpaque(true);
        Scroll42.setPreferredSize(new java.awt.Dimension(452, 400));

        tbRujukan.setAutoCreateRowSorter(true);
        tbRujukan.setToolTipText("Silahkan klik untuk memilih data rujukan yang mau dijawab");
        tbRujukan.setComponentPopupMenu(jPopupMenu3);
        tbRujukan.setName("tbRujukan"); // NOI18N
        tbRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRujukanMouseClicked(evt);
            }
        });
        tbRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRujukanKeyPressed(evt);
            }
        });
        Scroll42.setViewportView(tbRujukan);

        internalFrame16.add(Scroll42, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("9.", internalFrame16);

        internalFrame17.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame17.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "10. Penilaian Awal Medis THT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setLayout(new java.awt.BorderLayout());

        Scroll38.setComponentPopupMenu(jPopupMenu1);
        Scroll38.setName("Scroll38"); // NOI18N
        Scroll38.setOpaque(true);

        LoadHTML3.setBorder(null);
        LoadHTML3.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML3.setComponentPopupMenu(jPopupMenu1);
        LoadHTML3.setName("LoadHTML3"); // NOI18N
        Scroll38.setViewportView(LoadHTML3);

        internalFrame17.add(Scroll38, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("10.", internalFrame17);

        internalFrame18.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame18.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "11. Hasil Pemeriksaan Radiologi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setLayout(new java.awt.BorderLayout());

        Scroll43.setComponentPopupMenu(jPopupMenu1);
        Scroll43.setName("Scroll43"); // NOI18N
        Scroll43.setOpaque(true);

        LoadHTML4.setBorder(null);
        LoadHTML4.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML4.setComponentPopupMenu(jPopupMenu1);
        LoadHTML4.setName("LoadHTML4"); // NOI18N
        Scroll43.setViewportView(LoadHTML4);

        internalFrame18.add(Scroll43, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("11.", internalFrame18);

        internalFrame19.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "12. Penilaian Awal Medis Mata", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setLayout(new java.awt.BorderLayout());

        Scroll46.setComponentPopupMenu(jPopupMenu1);
        Scroll46.setName("Scroll46"); // NOI18N
        Scroll46.setOpaque(true);

        LoadHTML6.setBorder(null);
        LoadHTML6.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML6.setComponentPopupMenu(jPopupMenu1);
        LoadHTML6.setName("LoadHTML6"); // NOI18N
        Scroll46.setViewportView(LoadHTML6);

        internalFrame19.add(Scroll46, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("12.", internalFrame19);

        internalFrame20.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "13. Hasil Pemeriksaan Laboratorium", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setLayout(new java.awt.BorderLayout());

        Scroll47.setComponentPopupMenu(jPopupMenu1);
        Scroll47.setName("Scroll47"); // NOI18N
        Scroll47.setOpaque(true);

        LoadHTML7.setBorder(null);
        LoadHTML7.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML7.setComponentPopupMenu(jPopupMenu1);
        LoadHTML7.setName("LoadHTML7"); // NOI18N
        Scroll47.setViewportView(LoadHTML7);

        internalFrame20.add(Scroll47, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("13.", internalFrame20);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);
        TabRawat.getAccessibleContext().setAccessibleDescription("");

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(260, 65));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 6, 95, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(100, 6, 140, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNoRMActionPerformed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(243, 6, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(338, 6, 530, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tndkn/Tghan :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 34, 95, 23);

        btnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        btnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        btnTindakan.setMnemonic('3');
        btnTindakan.setToolTipText("Alt+3");
        btnTindakan.setName("btnTindakan"); // NOI18N
        btnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakanActionPerformed(evt);
            }
        });
        btnTindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakanKeyPressed(evt);
            }
        });
        FormInput.add(btnTindakan);
        btnTindakan.setBounds(640, 34, 28, 23);

        TKdPrw.setEditable(false);
        TKdPrw.setForeground(new java.awt.Color(0, 0, 0));
        TKdPrw.setName("TKdPrw"); // NOI18N
        TKdPrw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPrwKeyPressed(evt);
            }
        });
        FormInput.add(TKdPrw);
        TKdPrw.setBounds(100, 34, 137, 23);

        TNmPrw.setEditable(false);
        TNmPrw.setForeground(new java.awt.Color(0, 0, 0));
        TNmPrw.setName("TNmPrw"); // NOI18N
        FormInput.add(TNmPrw);
        TNmPrw.setBounds(240, 34, 400, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tanggal :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(675, 34, 60, 23);

        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(740, 34, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(835, 34, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(885, 34, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(935, 34, 45, 23);

        ChkJln.setBackground(new java.awt.Color(235, 255, 235));
        ChkJln.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJln.setForeground(new java.awt.Color(0, 0, 0));
        ChkJln.setSelected(true);
        ChkJln.setBorderPainted(true);
        ChkJln.setBorderPaintedFlat(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(985, 34, 23, 23);

        label_rehab.setForeground(new java.awt.Color(0, 0, 0));
        label_rehab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_rehab.setText("label_rehab");
        label_rehab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_rehab.setName("label_rehab"); // NOI18N
        FormInput.add(label_rehab);
        label_rehab.setBounds(875, 6, 460, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(350, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 255, 248)));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
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

        ScrollMenu.setBorder(null);
        ScrollMenu.setName("ScrollMenu"); // NOI18N
        ScrollMenu.setOpaque(true);
        ScrollMenu.setPreferredSize(new java.awt.Dimension(130, 383));

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 1, 1));

        BtnTarifTindakan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnTarifTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTarifTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnTarifTindakan.setText("1. Tarif Pemeriksaan/Tindakan");
        BtnTarifTindakan.setFocusPainted(false);
        BtnTarifTindakan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnTarifTindakan.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnTarifTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnTarifTindakan.setIconTextGap(10);
        BtnTarifTindakan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnTarifTindakan.setName("BtnTarifTindakan"); // NOI18N
        BtnTarifTindakan.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnTarifTindakan.setRoundRect(false);
        BtnTarifTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTarifTindakanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnTarifTindakan);

        BtnPemeriksaanDR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPemeriksaanDR.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemeriksaanDR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPemeriksaanDR.setText("2. Pemeriksaan Dokter");
        BtnPemeriksaanDR.setFocusPainted(false);
        BtnPemeriksaanDR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPemeriksaanDR.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPemeriksaanDR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPemeriksaanDR.setIconTextGap(10);
        BtnPemeriksaanDR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPemeriksaanDR.setName("BtnPemeriksaanDR"); // NOI18N
        BtnPemeriksaanDR.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPemeriksaanDR.setRoundRect(false);
        BtnPemeriksaanDR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemeriksaanDRActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPemeriksaanDR);

        BtnPemeriksaanPR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPemeriksaanPR.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemeriksaanPR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPemeriksaanPR.setText("3. Pemeriksaan Petugas");
        BtnPemeriksaanPR.setFocusPainted(false);
        BtnPemeriksaanPR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPemeriksaanPR.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPemeriksaanPR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPemeriksaanPR.setIconTextGap(10);
        BtnPemeriksaanPR.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPemeriksaanPR.setName("BtnPemeriksaanPR"); // NOI18N
        BtnPemeriksaanPR.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPemeriksaanPR.setRoundRect(false);
        BtnPemeriksaanPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemeriksaanPRActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPemeriksaanPR);

        BtnPRMRJ.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPRMRJ.setForeground(new java.awt.Color(0, 0, 0));
        BtnPRMRJ.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPRMRJ.setText("4. PRMRJ Pasien");
        BtnPRMRJ.setFocusPainted(false);
        BtnPRMRJ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPRMRJ.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPRMRJ.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPRMRJ.setIconTextGap(10);
        BtnPRMRJ.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPRMRJ.setName("BtnPRMRJ"); // NOI18N
        BtnPRMRJ.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPRMRJ.setRoundRect(false);
        BtnPRMRJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPRMRJActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPRMRJ);

        BtnCatatanResep.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnCatatanResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnCatatanResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCatatanResep.setText("5. Catatan Resep Obat");
        BtnCatatanResep.setFocusPainted(false);
        BtnCatatanResep.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnCatatanResep.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnCatatanResep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnCatatanResep.setIconTextGap(10);
        BtnCatatanResep.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnCatatanResep.setName("BtnCatatanResep"); // NOI18N
        BtnCatatanResep.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnCatatanResep.setRoundRect(false);
        BtnCatatanResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCatatanResepActionPerformed(evt);
            }
        });
        FormMenu.add(BtnCatatanResep);

        BtnRingkasanRiwayat.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnRingkasanRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        BtnRingkasanRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnRingkasanRiwayat.setText("6. Ringkasan Riwayat Rawat Jalan");
        BtnRingkasanRiwayat.setFocusPainted(false);
        BtnRingkasanRiwayat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnRingkasanRiwayat.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnRingkasanRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRingkasanRiwayat.setIconTextGap(10);
        BtnRingkasanRiwayat.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRingkasanRiwayat.setName("BtnRingkasanRiwayat"); // NOI18N
        BtnRingkasanRiwayat.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnRingkasanRiwayat.setRoundRect(false);
        BtnRingkasanRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRingkasanRiwayatActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRingkasanRiwayat);

        BtnRingkasanPulangRanap.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnRingkasanPulangRanap.setForeground(new java.awt.Color(0, 0, 0));
        BtnRingkasanPulangRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnRingkasanPulangRanap.setText("7. Ringkasan Pulang Rawat Inap");
        BtnRingkasanPulangRanap.setFocusPainted(false);
        BtnRingkasanPulangRanap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnRingkasanPulangRanap.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnRingkasanPulangRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnRingkasanPulangRanap.setIconTextGap(10);
        BtnRingkasanPulangRanap.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnRingkasanPulangRanap.setName("BtnRingkasanPulangRanap"); // NOI18N
        BtnRingkasanPulangRanap.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnRingkasanPulangRanap.setRoundRect(false);
        BtnRingkasanPulangRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRingkasanPulangRanapActionPerformed(evt);
            }
        });
        FormMenu.add(BtnRingkasanPulangRanap);

        BtnPenilaianKeperawatan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPenilaianKeperawatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenilaianKeperawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPenilaianKeperawatan.setText("8. Penilaian Awal Keperawatan/Assesmen");
        BtnPenilaianKeperawatan.setFocusPainted(false);
        BtnPenilaianKeperawatan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPenilaianKeperawatan.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPenilaianKeperawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPenilaianKeperawatan.setIconTextGap(10);
        BtnPenilaianKeperawatan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPenilaianKeperawatan.setName("BtnPenilaianKeperawatan"); // NOI18N
        BtnPenilaianKeperawatan.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPenilaianKeperawatan.setRoundRect(false);
        BtnPenilaianKeperawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenilaianKeperawatanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPenilaianKeperawatan);

        BtnMenjawabRujukan.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnMenjawabRujukan.setForeground(new java.awt.Color(0, 0, 0));
        BtnMenjawabRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnMenjawabRujukan.setText("9. Menjawab Rujukan Internal");
        BtnMenjawabRujukan.setFocusPainted(false);
        BtnMenjawabRujukan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnMenjawabRujukan.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnMenjawabRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnMenjawabRujukan.setIconTextGap(10);
        BtnMenjawabRujukan.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnMenjawabRujukan.setName("BtnMenjawabRujukan"); // NOI18N
        BtnMenjawabRujukan.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnMenjawabRujukan.setRoundRect(false);
        BtnMenjawabRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnMenjawabRujukanActionPerformed(evt);
            }
        });
        FormMenu.add(BtnMenjawabRujukan);

        BtnPenilaianAwalMedisTHT.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPenilaianAwalMedisTHT.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenilaianAwalMedisTHT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPenilaianAwalMedisTHT.setText("10. Penilaian Awal Medis THT");
        BtnPenilaianAwalMedisTHT.setFocusPainted(false);
        BtnPenilaianAwalMedisTHT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPenilaianAwalMedisTHT.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPenilaianAwalMedisTHT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPenilaianAwalMedisTHT.setIconTextGap(10);
        BtnPenilaianAwalMedisTHT.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPenilaianAwalMedisTHT.setName("BtnPenilaianAwalMedisTHT"); // NOI18N
        BtnPenilaianAwalMedisTHT.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPenilaianAwalMedisTHT.setRoundRect(false);
        BtnPenilaianAwalMedisTHT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenilaianAwalMedisTHTActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPenilaianAwalMedisTHT);

        BtnHasilPemeriksaanRad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnHasilPemeriksaanRad.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasilPemeriksaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnHasilPemeriksaanRad.setText("11. Hasil Pemeriksaan Radiologi");
        BtnHasilPemeriksaanRad.setFocusPainted(false);
        BtnHasilPemeriksaanRad.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnHasilPemeriksaanRad.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnHasilPemeriksaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHasilPemeriksaanRad.setIconTextGap(10);
        BtnHasilPemeriksaanRad.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnHasilPemeriksaanRad.setName("BtnHasilPemeriksaanRad"); // NOI18N
        BtnHasilPemeriksaanRad.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnHasilPemeriksaanRad.setRoundRect(false);
        BtnHasilPemeriksaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilPemeriksaanRadActionPerformed(evt);
            }
        });
        FormMenu.add(BtnHasilPemeriksaanRad);

        BtnPenilaianAwalMedisMata.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnPenilaianAwalMedisMata.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenilaianAwalMedisMata.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnPenilaianAwalMedisMata.setText("12. Penilaian Awal Medis Mata");
        BtnPenilaianAwalMedisMata.setFocusPainted(false);
        BtnPenilaianAwalMedisMata.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnPenilaianAwalMedisMata.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnPenilaianAwalMedisMata.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPenilaianAwalMedisMata.setIconTextGap(10);
        BtnPenilaianAwalMedisMata.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnPenilaianAwalMedisMata.setName("BtnPenilaianAwalMedisMata"); // NOI18N
        BtnPenilaianAwalMedisMata.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnPenilaianAwalMedisMata.setRoundRect(false);
        BtnPenilaianAwalMedisMata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenilaianAwalMedisMataActionPerformed(evt);
            }
        });
        FormMenu.add(BtnPenilaianAwalMedisMata);

        BtnHasilPemeriksaanLab.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnHasilPemeriksaanLab.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasilPemeriksaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnHasilPemeriksaanLab.setText("13. Hasil Pemeriksaan Laboratorium");
        BtnHasilPemeriksaanLab.setFocusPainted(false);
        BtnHasilPemeriksaanLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        BtnHasilPemeriksaanLab.setGlassColor(new java.awt.Color(204, 255, 204));
        BtnHasilPemeriksaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHasilPemeriksaanLab.setIconTextGap(10);
        BtnHasilPemeriksaanLab.setMargin(new java.awt.Insets(1, 1, 1, 1));
        BtnHasilPemeriksaanLab.setName("BtnHasilPemeriksaanLab"); // NOI18N
        BtnHasilPemeriksaanLab.setPreferredSize(new java.awt.Dimension(300, 23));
        BtnHasilPemeriksaanLab.setRoundRect(false);
        BtnHasilPemeriksaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilPemeriksaanLabActionPerformed(evt);
            }
        });
        FormMenu.add(BtnHasilPemeriksaanLab);

        ScrollMenu.setViewportView(FormMenu);

        PanelAccor.add(ScrollMenu, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTensi.requestFocus();
        }
}//GEN-LAST:event_TSuhuKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        DiagnosaRJpetugas.setText(Sequel.cariIsi("select diagnosa from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "' "));

        try {
            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "No.Rawat");
            } else {
//            if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals(""))
//                    || (!TSuhu.getText().trim().equals("")) || (!TTensi.getText().trim().equals(""))
//                    || (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
//                    || (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals(""))
//                    || (!TNadi.getText().trim().equals("")) || (!TGCS.getText().trim().equals(""))) {
//                Sequel.menyimpan("pemeriksaan_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 17, new String[]{
//                    TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                    TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(),
//                    TBerat.getText(), TGCS.getText(), TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(),
//                    cmbImun.getSelectedItem().toString(), TDiagnosa.getText(), KdDok2.getText(), TRncanaFolow.getText()
//                });
//                
//                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'","tinggi_badan='" + TTinggi.getText() + "'" );
//            }
                dateSimpan = new Date();
                timeSimpan = new Date();
                dateReg = new Date();
                timeReg = new Date();

                dateReg = new SimpleDateFormat("yyyy-MM-dd").parse(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat = '" + TNoRw.getText() + "'"));
                timeReg = new SimpleDateFormat("HH:mm:ss").parse(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat = '" + TNoRw.getText() + "'"));
                dateSimpan = new SimpleDateFormat("yyyy-MM-dd").parse(Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
                timeSimpan = new SimpleDateFormat("HH:mm:ss").parse(cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());

                if (dateReg.after(dateSimpan)) {
                    JOptionPane.showMessageDialog(null, "Tanggal Registrasi Lebih Besar Dari Tanggal Tindakan ");
                } else if (dateReg.equals(dateSimpan)) {
                    if (timeReg.after(timeSimpan)) {
                        JOptionPane.showMessageDialog(null, "Waktu Registrasi Lebih Besar Dari Waktu Tindakan");
                    } else {
                        if (TabRawat.getSelectedIndex() == 0) {
                            if (unitRJ.getText().equals("IGD")) {
                                if (DiagnosaRJpetugas.getText().trim().equals("")) {
                                    JOptionPane.showMessageDialog(null, "Silakan isi dulu dengan benar diagnosa medisnya petugas...!!");
                                    TabRawat.setSelectedIndex(2);
                                    TDiagnosa1.requestFocus();
                                } else if (!DiagnosaRJpetugas.getText().equals("")) {
                                    SimpanPenangananDokterPetugas();
                                    BtnBatalActionPerformed(evt);
                                }

                            } else if (!unitRJ.getText().equals("IGD")) {
                                SimpanPenangananDokterPetugas();
                                BtnBatalActionPerformed(evt);
                            }

                        } else if (TabRawat.getSelectedIndex() == 1) {
                            SimpanPemeriksaan();                            

                        } else if (TabRawat.getSelectedIndex() == 2) {
                            if (unitRJ.getText().equals("IGD")) {
                                if (!DiagnosaRJpetugas.getText().equals("")) {
                                    JOptionPane.showMessageDialog(null, "Diagnosa medis sudah tersimpan, utk perubahan data silakan klik pada tabel...!!");
                                } else if ((TDiagnosa1.getText().trim().equals("")) || (TDiagnosa1.getText().trim().equals("-"))) {
                                    JOptionPane.showMessageDialog(null, "Diagnosa medis harus diisi dengan benar...!!");
                                    kdptg1.setText(akses.getkode());
                                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                                    TDiagnosa1.requestFocus();
                                } else if (kdptg1.getText().trim().equals("")) {
                                    Valid.textKosong(kdptg1, "Petugas");
                                } else if (!TDiagnosa1.getText().trim().equals("") || (!TDiagnosa1.getText().trim().equals("-"))) {
                                    SimpanPemeriksaanPetugas();
                                    BtnBatalActionPerformed(evt);
                                }

                            } else if (!unitRJ.getText().equals("IGD")) {
                                if (kdptg1.getText().trim().equals("")) {
                                    Valid.textKosong(kdptg1, "Petugas");
                                } else {
                                    SimpanPemeriksaanPetugas();
                                    BtnBatalActionPerformed(evt);
                                }
                            }

                        } else if (TabRawat.getSelectedIndex() == 4) {
                            tampilResepObat();
                            BtnBatalActionPerformed(evt);
                        } else if (TabRawat.getSelectedIndex() == 8) {
                            if (Sequel.cariIsi("select ifnull(kd_dokter_pembalas,'') from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'").equals("")) {
                                if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") > 0 || akses.getkode().equals("Admin Utama")) {
                                    simpanJawabanRujukan();
                                    tampilRujukanInternal();

                                    if (tabModeRujukan.getRowCount() != 0) {
                                        TabRawat.setBackgroundAt(8, Color.ORANGE);
                                        BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
                                    } else {
                                        TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                                        BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Maaf, hanya dokter saja yg. bisa memberi balasan/jawaban rujukan internal poliklinik ini...!!");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Jawaban rujukan internal poliklinik sdh. pernah tersimpan...!!");
                            }                           
                        }

                        if (cekIGD.equals("inap")) {
                            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan(Tambahan IGD)','Simpan'");
                        } else {
                            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan','Simpan'");
                        }
                    }

                } else {
                    if (TabRawat.getSelectedIndex() == 0) {
                        if (unitRJ.getText().equals("IGD")) {
                            if (DiagnosaRJpetugas.getText().trim().equals("")) {
                                JOptionPane.showMessageDialog(null, "Silakan isi dulu dengan benar diagnosa medisnya petugas...!!");
                                TabRawat.setSelectedIndex(2);
                                TDiagnosa1.requestFocus();
                            } else if (!DiagnosaRJpetugas.getText().equals("")) {
                                SimpanPenangananDokterPetugas();
                                BtnBatalActionPerformed(evt);
                            }

                        } else if (!unitRJ.getText().equals("IGD")) {
                            SimpanPenangananDokterPetugas();
                            BtnBatalActionPerformed(evt);
                        }

                    } else if (TabRawat.getSelectedIndex() == 1) {
                        SimpanPemeriksaan();

                    } else if (TabRawat.getSelectedIndex() == 2) {
                        if (unitRJ.getText().equals("IGD")) {
                            if (!DiagnosaRJpetugas.getText().equals("")) {
                                JOptionPane.showMessageDialog(null, "Diagnosa medis sudah tersimpan, utk perubahan data silakan klik pada tabel...!!");
                            } else if ((TDiagnosa1.getText().trim().equals("")) || (TDiagnosa1.getText().trim().equals("-"))) {
                                JOptionPane.showMessageDialog(null, "Diagnosa medis harus diisi dengan benar...!!");
                                kdptg1.setText(akses.getkode());
                                TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                                TDiagnosa1.requestFocus();
                            } else if (kdptg1.getText().trim().equals("")) {
                                Valid.textKosong(kdptg1, "Petugas");
                            } else if (!TDiagnosa1.getText().trim().equals("") || (!TDiagnosa1.getText().trim().equals("-"))) {
                                SimpanPemeriksaanPetugas();
                                BtnBatalActionPerformed(evt);
                            }

                        } else if (!unitRJ.getText().equals("IGD")) {
                            if (kdptg1.getText().trim().equals("")) {
                                Valid.textKosong(kdptg1, "Petugas");
                            } else {
                                SimpanPemeriksaanPetugas();
                                BtnBatalActionPerformed(evt);
                            }
                        }

                    } else if (TabRawat.getSelectedIndex() == 4) {
                        tampilResepObat();
                        BtnBatalActionPerformed(evt);
                    } else if (TabRawat.getSelectedIndex() == 8) {
                        if (Sequel.cariIsi("select ifnull(kd_dokter_pembalas,'') from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'").equals("")) {
                            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") > 0 || akses.getkode().equals("Admin Utama")) {
                                simpanJawabanRujukan();
                                tampilRujukanInternal();
                                if (tabModeRujukan.getRowCount() != 0) {
                                    TabRawat.setBackgroundAt(8, Color.ORANGE);
                                    BtnMenjawabRujukan.setGlassColor(Color.ORANGE);                                    
                                } else {
                                    TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                                    BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Maaf, hanya dokter saja yg. bisa memberi balasan/jawaban rujukan internal poliklinik ini...!!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Jawaban rujukan internal poliklinik sdh. pernah tersimpan...!!");
                        }
                    }

                    if (cekIGD.equals("inap")) {
                        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan(Tambahan IGD)','Simpan'");
                    } else {
                        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan','Simpan'");
                    }

                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal Tersimpan, hubungi Admin");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            if (TabRawat.getSelectedIndex() == 0) {
                Valid.pindah(evt, kdptg, BtnBatal);
            } else if (TabRawat.getSelectedIndex() == 1) {
                Valid.pindah(evt, TGCS, BtnBatal);
            } else if (TabRawat.getSelectedIndex() == 2) {
                Valid.pindah(evt, TDiagnosa1, BtnBatal);
            }
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptText();        
        if (TabRawat.getSelectedIndex() == 8) {
            emptTeksRujukanInternal();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBatalActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (tabModeDrPr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (!(TPasien.getText().trim().equals(""))) {
                for (i = 0; i < tbRawatDrPr.getRowCount(); i++) {
                    if (tbRawatDrPr.getValueAt(i, 0).toString().equals("true")) {
                        if (akses.getkode().equals("Admin Utama")) {
                            Sequel.queryu("delete from rawat_jl_drpr where no_rawat='" + tbRawatDrPr.getValueAt(i, 1).toString()
                                    + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(i, 12).toString()
                                    + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(i, 10).toString()
                                    + "' and jam_rawat='" + tbRawatDrPr.getValueAt(i, 11).toString()
                                    + "' and kd_dokter='" + tbRawatDrPr.getValueAt(i, 5).toString() + "' and nip='" + tbRawatDrPr.getValueAt(i, 7).toString() + "'");
                        } else {
                            if (Sequel.cariInteger("select count(-1) from rawat_jl_drpr where no_rawat='" + tbRawatDrPr.getValueAt(i, 1).toString() + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(i, 12).toString() + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(i, 10).toString() + "' and jam_rawat='" + tbRawatDrPr.getValueAt(i, 11).toString() + "' and stts_bayar = 'BAYAR' and kd_dokter='" + tbRawatDrPr.getValueAt(i, 5).toString() + "' and nip='" + tbRawatDrPr.getValueAt(i, 7).toString() + "'") > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            } else {
                                Sequel.queryu("delete from rawat_jl_drpr where no_rawat='" + tbRawatDrPr.getValueAt(i, 1).toString()
                                        + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(i, 12).toString()
                                        + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(i, 10).toString()
                                        + "' and jam_rawat='" + tbRawatDrPr.getValueAt(i, 11).toString()
                                        + "' and stts_bayar = 'Belum' and kd_dokter='" + tbRawatDrPr.getValueAt(i, 5).toString() + "' and nip='" + tbRawatDrPr.getValueAt(i, 7).toString() + "'");
                            }
                        }
                    }
                }

                tampilDrPr();
                TotalNominal();
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                if (tabModePemeriksaanDr.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                    TNoRw.requestFocus();
                } else if (TPasien.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
                } else if (!(TPasien.getText().trim().equals(""))) {
                    orang1 = "";
                    nmOrang1 = "";
                    orang1 = Sequel.cariIsi("select kd_dokter from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
                    nmOrang1 = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + orang1 + "'");

                    if (orang1.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
                        for (i = 0; i < tbPemeriksaanDr.getRowCount(); i++) {
                            if (tbPemeriksaanDr.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.queryu("delete from pemeriksaan_ralan where no_rawat='" + tbPemeriksaanDr.getValueAt(i, 1).toString() + "'");
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Conteng dulu datanya...!!");
                                BtnBatalActionPerformed(evt);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Yang bisa menghapus hanya " + nmOrang1 + ", krn. beliau yang menyimpan datanya.");
                        BtnBatalActionPerformed(evt);
                    }
                    tampilPemeriksaanDokter();
                }
            } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                if (tabModeLab1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                } else if (noiD.equals("") && noMinta.equals("")) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Lab. dengan mengklik data pada tabel...!!!");
                    tbPeriksaLab.requestFocus();
                } else if (diperiksa.equals("SUDAH")) {
                    JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat dihapus..!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + noiD + "' and no_minta='" + noMinta + "'");
                        noiD = "";
                        noMinta = "";
                        tampilMintaLab1();
                    }
                }
            } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                if (tabModeRad1.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                } else if (noordeR1.equals("") && (waktuSimpanRad.equals(""))) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Radiologi dg. mengklik data pada tabel...!!!");
                    tbPeriksaRad.requestFocus();
                } else if (Sequel.cariInteger("select count(-1) from permintaan_radiologi where noorder='" + noordeR1 + "' and "
                        + "waktu_simpan='" + waktuSimpanRad + "' and status='Diterima'") > 0) {
                    JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi sudah diperiksa...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("DELETE FROM permintaan_radiologi WHERE noorder='" + noordeR1 + "' and waktu_simpan='" + waktuSimpanRad + "'");
                        tampilMintaRad1();
                    }
                }
            }
            
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (tabModePemeriksaanPr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (!(TPasien.getText().trim().equals(""))) {
                orang2 = "";
                nmOrang2 = "";
                orang2 = Sequel.cariIsi("select nip from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'");
                nmOrang2 = Sequel.cariIsi("select nama from petugas where nip='" + orang2 + "'");

                if (orang2.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
                    for (i = 0; i < tbPemeriksaanPr.getRowCount(); i++) {
                        if (tbPemeriksaanPr.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.queryu("delete from pemeriksaan_ralan_petugas where no_rawat='" + tbPemeriksaanPr.getValueAt(i, 1).toString() + "'");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Yang bisa menghapus hanya " + nmOrang2 + ", krn. dia yang menyimpan datanya.");
                    BtnBatalActionPerformed(evt);
                }
                tampilPemeriksaanPetugas();
            }
        } else if (TabRawat.getSelectedIndex() == 4) {
            if (tabModeResepObat.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh menghapus resepnya...!!!!");
            } else if (!TPasien.getText().trim().equals("")) {
                if (Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode()) || akses.getkode().equals("Admin Utama")) {
                    for (i = 0; i < tbResepObat.getRowCount(); i++) {
                        if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.queryu("delete from catatan_resep where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                                    + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                                    + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                                    + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Maaf, dokter yang login tidak sama dg. dokter terjadwal dipoliklinik...!!");
                }
                tampilResepObat();
            }
        } else if (TabRawat.getSelectedIndex() == 8) {
            hapusJawabanRujukan();
            tampilRujukanInternal();
            if (tabModeRujukan.getRowCount() != 0) {
                TabRawat.setBackgroundAt(8, Color.ORANGE);
                BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
            } else {
                TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
            }
        }

        if (cekIGD.equals("inap")) {
            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan(Tambahan IGD)','Hapus'");
        } else {
            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan','Hapus'");
        }
        BtnBatalActionPerformed(evt);
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void TRespirasiActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TCari.getText().trim().equals("")) {
            BtnCariActionPerformed(evt);
        }
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                if (tabModeDrPr.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabModeDrPr.getRowCount() != 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

                    String tgl = " rawat_jl_drpr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                    Valid.MyReport("rptJalanDrPr.jasper", "report", "::[ Data Rawat Jalan Yang Ditangani Dokter ]::",
                            "select rawat_jl_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                            + "jns_perawatan.nm_perawatan,rawat_jl_drpr.kd_dokter,dokter.nm_dokter,rawat_jl_drpr.nip,petugas.nama,"
                            + "rawat_jl_drpr.tgl_perawatan,rawat_jl_drpr.jam_rawat,rawat_jl_drpr.biaya_rawat "
                            + "from pasien inner join reg_periksa inner join jns_perawatan inner join "
                            + "dokter inner join rawat_jl_drpr inner join "
                            + "petugas on rawat_jl_drpr.no_rawat=reg_periksa.no_rawat "
                            + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                            + "and rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                            + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter "
                            + "and rawat_jl_drpr.nip=petugas.nip "
                            + "where " + tgl + " and rawat_jl_drpr.no_rawat like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and jns_perawatan.nm_perawatan like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and rawat_jl_drpr.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and rawat_jl_drpr.nip like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and petugas.nama like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and tgl_perawatan like '%" + TCari.getText().trim() + "%' "
                            + " order by rawat_jl_drpr.no_rawat desc", param);
                }
                break;
            case 1:
                if (tabModePemeriksaanDr.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                    BtnBatal.requestFocus();
                } else if (tabModePemeriksaanDr.getRowCount() != 0) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

                    String tgl = " pemeriksaan_ralan.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                    Valid.MyReport("rptJalanPemeriksaan.jasper", "report", "::[ Data Pemeriksaan Rawat Jalan ]::",
                            "select pemeriksaan_ralan.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                            + "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, "
                            + "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, "
                            + "pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                            + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.imun_ke from pasien inner join reg_periksa inner join pemeriksaan_ralan "
                            + "on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
                            + tgl + "and pemeriksaan_ralan.no_rawat like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and pemeriksaan_ralan.alergi like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and pemeriksaan_ralan.keluhan like '%" + TCari.getText().trim() + "%' or "
                            + tgl + "and pemeriksaan_ralan.pemeriksaan like '%" + TCari.getText().trim() + "%' "
                            + "order by pemeriksaan_ralan.no_rawat desc", param);
                }
                break;
            default:
                break;
        }

        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnCari);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowDataParu.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
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
        kdpoli.setText("");
        TPoli.setText("");

        if (TabRawat.getSelectedIndex() == 0) {            
            tampilDrPr();
            TotalNominal();
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                tampilPemeriksaanDokter();
            } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                tampilMintaLab1();
            } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                tampilMintaRad1();
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            kdptg1.setText(akses.getkode());
            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
            tampilPemeriksaanPetugas();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilPRMRJ();
        } else if (TabRawat.getSelectedIndex() == 4) {
            tampilResepObat();
            ChkInput1.setSelected(false);
            isFormRiwayatObat();
        } else if (TabRawat.getSelectedIndex() == 5) {
            tampilRingkasan();
        } else if (TabRawat.getSelectedIndex() == 6) {
            TCari.setText(TNoRM.getText());
            tampilRingkasanPulangRanap();
        } else if (TabRawat.getSelectedIndex() == 7) {
            TCari.setText(TNoRM.getText());
            tampilAssesmen();
        } else if (TabRawat.getSelectedIndex() == 8) {
            Tnorawat.setText("");
            TDariPoli.setText("");
            tglDirujuk.setText("");
            OlehDokter.setText("");
            TKetAsalRujukan.setText("");

            poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
            dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                    + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
            TJwbnRujukan.setText("");
            tbRujukan.requestFocus();

            if (cmbStatus.getSelectedIndex() == 0) {
                jawaban = "Sudah";
            } else {
                jawaban = "Belum";
            }
            
            tampilRujukanInternal();
            if (tabModeRujukan.getRowCount() != 0) {
                TabRawat.setBackgroundAt(8, Color.ORANGE);
                BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
            } else {
                TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
            }
        } else if (TabRawat.getSelectedIndex() == 9) {
            TCari.setText(TNoRM.getText());
            tampilAwalMedisTHT();
        } else if (TabRawat.getSelectedIndex() == 10) {
            tampilHasilRadiologi();
        } else if (TabRawat.getSelectedIndex() == 11) {
            TCari.setText(TNoRM.getText());
            tampilAwalMedisMata();
        } else if (TabRawat.getSelectedIndex() == 12) {
            TCari.setText(TNoRM.getText());
            tampilHasilLaboratorium();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnCari);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TBerat.requestFocus();
        }
    }//GEN-LAST:event_TTensiKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (!TKdPrw.getText().trim().equals("")) {
            isJns();
        }

        if (TabRawat.getSelectedIndex() == 0) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());            
            tampilDrPr();
            TotalNominal();
        } else if (TabRawat.getSelectedIndex() == 1) {
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                        TCari.setText(TNoRM.getText());
                        tampilPemeriksaanDokter();
                    } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                        tampilMintaLab1();
                    } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                        tampilMintaRad1();
                    }
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                            TCari.setText(TNoRM.getText());
                            tampilPemeriksaanDokter();
                        } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                            tampilMintaLab1();
                        } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                            tampilMintaRad1();
                        }
                    }
                }
            } else {
                if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                    TCari.setText(TNoRM.getText());
                    tampilPemeriksaanDokter();
                } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                    tampilMintaLab1();
                } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                    tampilMintaRad1();
                }
            }          
            
        } else if (TabRawat.getSelectedIndex() == 2) {
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    if (ChkCopyPemeriksaanDR.isSelected() == true) {
                        kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                        TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                    } else {
                        kdptg1.setText(akses.getkode());
                        TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                        TCari.setText(TNoRM.getText());
                    }
                    tampilPemeriksaanPetugas();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        if (ChkCopyPemeriksaanDR.isSelected() == true) {
                            kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                        } else {
                            kdptg1.setText(akses.getkode());
                            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                            TCari.setText(TNoRM.getText());
                        }
                        tampilPemeriksaanPetugas();
                    }
                }
            } else {
                if (ChkCopyPemeriksaanDR.isSelected() == true) {
                    kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                } else {
                    kdptg1.setText(akses.getkode());
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                    TCari.setText(TNoRM.getText());
                }
                tampilPemeriksaanPetugas();
            }
            
        } else if (TabRawat.getSelectedIndex() == 3) {
            kdpoli.setText("");
            TPoli.setText("");
            tampilPRMRJ();
        } else if (TabRawat.getSelectedIndex() == 4) {
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    TCari.setText(TNoRM.getText());
                    TResepObat.setText("");
                    TCariObat.setText("");
                    tampilResepObat();
                    ChkInput1.setSelected(false);
                    isFormRiwayatObat();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        TCari.setText(TNoRM.getText());
                        TResepObat.setText("");
                        TCariObat.setText("");
                        tampilResepObat();
                        ChkInput1.setSelected(false);
                        isFormRiwayatObat();
                    }
                }
            } else {
                TCari.setText(TNoRM.getText());
                TResepObat.setText("");
                TCariObat.setText("");
                tampilResepObat();
                ChkInput1.setSelected(false);
                isFormRiwayatObat();
            }

        } else if (TabRawat.getSelectedIndex() == 5) {
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    TCari.setText(TNoRM.getText());
                    tampilRingkasan();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        TCari.setText(TNoRM.getText());
                        tampilRingkasan();
                    }
                }
            } else {
                kdpoli.setText(polinya);
                TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                TCari.setText(TNoRM.getText());
                tampilRingkasan();
            }            
            
        } else if (TabRawat.getSelectedIndex() == 6) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilRingkasanPulangRanap();
        } else if (TabRawat.getSelectedIndex() == 7) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAssesmen();
        } else if (TabRawat.getSelectedIndex() == 8) {
            kdpoli.setText("");
            TPoli.setText("");
            emptTeksRujukanInternal();
            tampilRujukanInternal();
            if (tabModeRujukan.getRowCount() != 0) {
                TabRawat.setBackgroundAt(8, Color.ORANGE);
                BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
            } else {
                TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
            }
        } else if (TabRawat.getSelectedIndex() == 9) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAwalMedisTHT();
        } else if (TabRawat.getSelectedIndex() == 10) {
            kdpoli.setText("");
            TPoli.setText("");
            tampilHasilRadiologi();
        } else if (TabRawat.getSelectedIndex() == 11) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAwalMedisMata();
        } else if (TabRawat.getSelectedIndex() == 12) {
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilHasilLaboratorium();
        } 
    }//GEN-LAST:event_TabRawatMouseClicked

private void TKdPrwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPrwKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        isJns();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnTindakanActionPerformed(null);
    } else {
        Valid.pindah(evt, TNoRw, TKeluhan);
    }
}//GEN-LAST:event_TKdPrwKeyPressed

private void btnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanActionPerformed
    if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from nota_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien telah menyelesaikan seluruh transaksi & pembayaran biaya perawatan IGD & rawat inap....");

        } else if (TabRawat.getSelectedIndex() == 0) {
            DiagnosaRJpetugas.setText(Sequel.cariIsi("select diagnosa from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "' "));

            if (unitRJ.getText().equals("IGD")) {
                if (DiagnosaRJpetugas.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Silakan isi dulu dengan benar diagnosa medisnya petugas...!!");
                    kdptg1.setText(akses.getkode());
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                    TabRawat.setSelectedIndex(2);
                    TDiagnosa1.requestFocus();

                } else {
                    if (TDokter.getText().trim().equals("")) {
                        Valid.textKosong(KdDok, "Dokter");
                    } else if (TPerawat.getText().trim().equals("")) {
                        Valid.textKosong(kdptg, "petugas/perawat/bidan");
                    } else {
                        if (akses.getkode().equals("Admin Utama")) {
                            akses.setform("DlgRawatJalan");
                            perawatan.setNoRm(TNoRw.getText(), KdDok.getText(), TDokter.getText(), "rawat_jl_drpr", TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "", kdptg.getText(), TPerawat.getText(),
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);                            
                        } else {
                            akses.setform("DlgRawatJalan");
                            perawatan.setNoRm(TNoRw.getText(), KdDok.getText(), TDokter.getText(), "rawat_jl_drpr", TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "", kdptg.getText(), TPerawat.getText(),
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    }
                }

            } else {
                if (TDokter.getText().trim().equals("")) {
                    Valid.textKosong(KdDok, "Dokter");
                } else if (TPerawat.getText().trim().equals("")) {
                    Valid.textKosong(kdptg, "petugas/perawat/bidan");
                    kdptg1.setText(akses.getkode());
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        akses.setform("DlgRawatJalan");
                        perawatan.setNoRm(TNoRw.getText(), KdDok.getText(), TDokter.getText(), "rawat_jl_drpr", TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "", kdptg.getText(), TPerawat.getText(),
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        perawatan.isCek();
                        perawatan.tampil();
                        perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        perawatan.setLocationRelativeTo(internalFrame1);
                        perawatan.setVisible(true);
                    } else {
                        akses.setform("DlgRawatJalan");
                        perawatan.setNoRm(TNoRw.getText(), KdDok.getText(), TDokter.getText(), "rawat_jl_drpr", TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "", kdptg.getText(), TPerawat.getText(),
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        perawatan.isCek();
                        perawatan.tampil();
                        perawatan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        perawatan.setLocationRelativeTo(internalFrame1);
                        perawatan.setVisible(true);
                    }
                }
            }
        }
    }
}//GEN-LAST:event_btnTindakanActionPerformed

private void btnTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakanKeyPressed
    Valid.pindah(evt, TKdPrw, TKeluhan);
}//GEN-LAST:event_btnTindakanKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    try {
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            dateSimpan = new Date();
            timeSimpan = new Date();
            dateReg = new Date();
            timeReg = new Date();

            dateReg = new SimpleDateFormat("yyyy-MM-dd").parse(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat = '" + TNoRw.getText() + "'"));
            timeReg = new SimpleDateFormat("HH:mm:ss").parse(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat = '" + TNoRw.getText() + "'"));
            dateSimpan = new SimpleDateFormat("yyyy-MM-dd").parse(Valid.SetTgl(DTPTgl.getSelectedItem() + ""));
            timeSimpan = new SimpleDateFormat("HH:mm:ss").parse(cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem());

            if (dateReg.after(dateSimpan)) {
                JOptionPane.showMessageDialog(null, "Tanggal Registrasi Lebih Besar Dari Tanggal Tindakan ");
            } else if (dateReg.equals(dateSimpan)) {
                if (timeReg.after(timeSimpan)) {
                    JOptionPane.showMessageDialog(null, "Waktu Registrasi Lebih Besar Dari Waktu Tindakan");
                } else {
                    if (TabRawat.getSelectedIndex() == 0) {
                        if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
                            Valid.textKosong(KdDok, "Dokter");
                        } else if (kdptg.getText().trim().equals("") || TPerawat.getText().trim().equals("")) {
                            Valid.textKosong(kdptg, "Petugas");
                        } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                            Valid.textKosong(TKdPrw, "perawatan");
                        } else {
                            if (tbRawatDrPr.getSelectedRow() > -1) {
                                if (Sequel.mengedittf("rawat_jl_drpr", "no_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 1)
                                        + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 12)
                                        + "' and kd_dokter='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5)
                                        + "' and nip='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7)
                                        + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10)
                                        + "' and jam_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11) + "'",
                                        "no_rawat='" + TNoRw.getText() + "',kd_jenis_prw='" + TKdPrw.getText()
                                        + "',nip='" + kdptg.getText() + "',kd_dokter='" + KdDok.getText() + "',material='" + BagianRS.getText()
                                        + "',bhp='" + Bhp.getText() + "',tarif_tindakanpr='" + JmPerawat.getText() + "',tarif_tindakandr='" + JmDokter.getText() + "',biaya_rawat='" + TTnd.getText()
                                        + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                        + "kso='" + KSO.getText() + "',menejemen='" + Menejemen.getText() + "'") == true) {

                                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi.getText() + "'");
                                    tampilDrPr();
                                    TotalNominal();
                                    BtnBatalActionPerformed(evt);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Ganti Gagal");
                                }

                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                                TCari.requestFocus();
                            }
                        }
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        orang1 = "";
                        nmOrang1 = "";
                        prmrj = "";
                        orang1 = Sequel.cariIsi("select kd_dokter from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
                        nmOrang1 = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + orang1 + "'");

                        if (tbPemeriksaanDr.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
                        } else if (orang1.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
//                if (TKeluhan.getText().trim().equals("") && TPemeriksaan.getText().trim().equals("")
//                        && TSuhu.getText().trim().equals("") && TTensi.getText().trim().equals("")
//                        && TAlergi.getText().trim().equals("") && TTinggi.getText().trim().equals("")
//                        && TBerat.getText().trim().equals("") && TRespirasi.getText().trim().equals("")
//                        && TNadi.getText().trim().equals("") && TGCS.getText().trim().equals("")) {
                            if ((TDiagnosa.getText().trim().equals("")) || (TDiagnosa.getText().trim().equals("-"))) {
                                Valid.textKosong(TKeluhan, "Diagnosa Tidak Boleh Kosong");
                            } else {
                                if (ChkPrmrj.isSelected() == true) {
                                    prmrj = "1";
                                } else {
                                    prmrj = "0";
                                }

                                if (tbPemeriksaanDr.getSelectedRow() > -1) {
                                    Sequel.mengedit("pemeriksaan_ralan", "no_rawat='" + tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 1) + "'",
                                            "no_rawat='" + TNoRw2.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                            + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                            + "suhu_tubuh='" + TSuhu.getText() + "',tensi='" + TTensi.getText() + "',imun_ke='" + cmbImun.getSelectedItem().toString() + "',"
                                            + "keluhan='" + TKeluhan.getText() + "',pemeriksaan='" + TPemeriksaan.getText() + "',"
                                            + "nadi='" + TNadi.getText() + "',respirasi='" + TRespirasi.getText() + "',"
                                            + "tinggi='" + TTinggi.getText() + "',berat='" + TBerat.getText() + "',"
                                            + "gcs='" + TGCS.getText() + "',alergi='" + TAlergi.getText() + "',alergi='" + TAlergi.getText() + "',"
                                            + "diagnosa='" + TDiagnosa.getText() + "',rencana_follow_up='" + TRncanaFolow.getText() + "',"
                                            + "rincian_tindakan='" + TRincianTindakan.getText() + "',terapi='" + TTerapi.getText() + "',"
                                            + "stts_prmrj='" + prmrj + "',spo2='" + Tspo2.getText() + "'");

                                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi.getText() + "'");
                                    if (ChkCopyPemeriksaanDR.isSelected() == true) {
                                        Sequel.queryu("delete from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw2.getText() + "'");
                                        SimpanPemeriksaanPetugas();
                                    }
                                    tampilPemeriksaanDokter();
                                    BtnBatalActionPerformed(evt);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                                    TCari.requestFocus();
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Yang bisa merubah hanya " + nmOrang1 + ", krn. beliau yang menyimpan datanya.");
                            BtnBatalActionPerformed(evt);
                        }

                    } else if (TabRawat.getSelectedIndex() == 2) {
                        orang2 = "";
                        nmOrang2 = "";
                        orang2 = Sequel.cariIsi("select nip from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'");
                        nmOrang2 = Sequel.cariIsi("select nama from petugas where nip='" + orang2 + "'");

                        if (tbPemeriksaanPr.getRowCount() == 0) {
                            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
                        } else if (orang2.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
                            if (TKeluhan1.getText().trim().equals("") && TPemeriksaan1.getText().trim().equals("")
                                    && TSuhu1.getText().trim().equals("") && TTensi1.getText().trim().equals("")
                                    && TAlergi1.getText().trim().equals("") && TTinggi1.getText().trim().equals("")
                                    && TBerat1.getText().trim().equals("") && TRespirasi1.getText().trim().equals("")
                                    && TNadi1.getText().trim().equals("") && TGCS1.getText().trim().equals("")) {
                                Valid.textKosong(TKeluhan1, "Hasil Periksa/Perkembangan/Suhu Badan/Tensi");
                            } else {
                                if (tbPemeriksaanPr.getSelectedRow() > -1) {
                                    Sequel.mengedit("pemeriksaan_ralan_petugas", "no_rawat='" + tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 1) + "'",
                                            "no_rawat='" + TNoRw2.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                            + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                            + "suhu_tubuh='" + TSuhu1.getText() + "',tensi='" + TTensi1.getText() + "',"
                                            + "keluhan='" + TKeluhan1.getText() + "',pemeriksaan='" + TPemeriksaan1.getText() + "',"
                                            + "nadi='" + TNadi1.getText() + "',respirasi='" + TRespirasi1.getText() + "',"
                                            + "tinggi='" + TTinggi1.getText() + "',berat='" + TBerat1.getText() + "',imun_ke='" + cmbImun1.getSelectedItem().toString() + "',"
                                            + "gcs='" + TGCS1.getText() + "',alergi='" + TAlergi1.getText() + "',diagnosa='" + TDiagnosa1.getText() + "',"
                                            + "rencana_follow_up='" + TRncanaFolow1.getText() + "',nip='" + kdptg1.getText() + "',"
                                            + "rincian_tindakan='" + TRincianTindakan1.getText() + "',terapi='" + TTerapi1.getText() + "',spo2='" + Tspo21.getText() + "'");

                                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi1.getText() + "'");
                                    tampilPemeriksaanPetugas();
                                    BtnBatalActionPerformed(evt);
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                                    TCari.requestFocus();
                                }
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Yang bisa merubah hanya " + nmOrang2 + ", krn. dia yang menyimpan datanya.");
                            BtnBatalActionPerformed(evt);
                        }

                    } else if (TabRawat.getSelectedIndex() == 8) {
                        gantiJawabanRujukan();
                        tampilRujukanInternal();
                        if (tabModeRujukan.getRowCount() != 0) {
                            TabRawat.setBackgroundAt(8, Color.ORANGE);
                            BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
                        } else {
                            TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                            BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
                        }
                    }

                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan','Ganti'");

                }
            } else {
                if (TabRawat.getSelectedIndex() == 0) {
                    if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
                        Valid.textKosong(KdDok, "Dokter");
                    } else if (kdptg.getText().trim().equals("") || TPerawat.getText().trim().equals("")) {
                        Valid.textKosong(kdptg, "Petugas");
                    } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                        Valid.textKosong(TKdPrw, "perawatan");
                    } else {
                        if (tbRawatDrPr.getSelectedRow() > -1) {
                            if (Sequel.mengedittf("rawat_jl_drpr", "no_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 1)
                                    + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 12)
                                    + "' and kd_dokter='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5)
                                    + "' and nip='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7)
                                    + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10)
                                    + "' and jam_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11) + "'",
                                    "no_rawat='" + TNoRw.getText() + "',kd_jenis_prw='" + TKdPrw.getText()
                                    + "',nip='" + kdptg.getText() + "',kd_dokter='" + KdDok.getText() + "',material='" + BagianRS.getText()
                                    + "',bhp='" + Bhp.getText() + "',tarif_tindakanpr='" + JmPerawat.getText() + "',tarif_tindakandr='" + JmDokter.getText() + "',biaya_rawat='" + TTnd.getText()
                                    + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                    + "kso='" + KSO.getText() + "',menejemen='" + Menejemen.getText() + "'") == true) {

                                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi.getText() + "'");
                                tampilDrPr();
                                BtnBatalActionPerformed(evt);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Ganti Gagal");
                            }

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                            TCari.requestFocus();
                        }
                    }
                } else if (TabRawat.getSelectedIndex() == 1) {
                    orang1 = "";
                    nmOrang1 = "";
                    orang1 = Sequel.cariIsi("select kd_dokter from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
                    nmOrang1 = Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + orang1 + "'");

                    if (tbPemeriksaanDr.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
                    } else if (orang1.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
//                if (TKeluhan.getText().trim().equals("") && TPemeriksaan.getText().trim().equals("")
//                        && TSuhu.getText().trim().equals("") && TTensi.getText().trim().equals("")
//                        && TAlergi.getText().trim().equals("") && TTinggi.getText().trim().equals("")
//                        && TBerat.getText().trim().equals("") && TRespirasi.getText().trim().equals("")
//                        && TNadi.getText().trim().equals("") && TGCS.getText().trim().equals("")) {
                        if ((TDiagnosa.getText().trim().equals("")) || (TDiagnosa.getText().trim().equals("-"))) {
                            Valid.textKosong(TKeluhan, "Diagnosa Tidak Boleh Kosong");
                        } else {
                            if (tbPemeriksaanDr.getSelectedRow() > -1) {
                                Sequel.mengedit("pemeriksaan_ralan", "no_rawat='" + tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 1) + "'",
                                        "no_rawat='" + TNoRw2.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                        + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                        + "suhu_tubuh='" + TSuhu.getText() + "',tensi='" + TTensi.getText() + "',imun_ke='" + cmbImun.getSelectedItem().toString() + "',"
                                        + "keluhan='" + TKeluhan.getText() + "',pemeriksaan='" + TPemeriksaan.getText() + "',"
                                        + "nadi='" + TNadi.getText() + "',respirasi='" + TRespirasi.getText() + "',"
                                        + "tinggi='" + TTinggi.getText() + "',berat='" + TBerat.getText() + "',"
                                        + "gcs='" + TGCS.getText() + "',alergi='" + TAlergi.getText() + "',alergi='" + TAlergi.getText() + "',"
                                        + "diagnosa='" + TDiagnosa.getText() + "',rencana_follow_up='" + TRncanaFolow.getText() + "',"
                                        + "rincian_tindakan='" + TRincianTindakan.getText() + "'");

                                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi.getText() + "'");
                                if (ChkCopyPemeriksaanDR.isSelected() == true) {
                                    Sequel.queryu("delete from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw2.getText() + "'");
                                    SimpanPemeriksaanPetugas();
                                }
                                tampilPemeriksaanDokter();
                                BtnBatalActionPerformed(evt);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                                TCari.requestFocus();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Yang bisa merubah hanya " + nmOrang1 + ", krn. beliau yang menyimpan datanya.");
                        BtnBatalActionPerformed(evt);
                    }

                } else if (TabRawat.getSelectedIndex() == 2) {
                    orang2 = "";
                    nmOrang2 = "";
                    orang2 = Sequel.cariIsi("select nip from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'");
                    nmOrang2 = Sequel.cariIsi("select nama from petugas where nip='" + orang2 + "'");

                    if (tbPemeriksaanPr.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
                    } else if (orang2.equals(akses.getkode()) || (akses.getkode().equals("Admin Utama"))) {
                        if (TKeluhan1.getText().trim().equals("") && TPemeriksaan1.getText().trim().equals("")
                                && TSuhu1.getText().trim().equals("") && TTensi1.getText().trim().equals("")
                                && TAlergi1.getText().trim().equals("") && TTinggi1.getText().trim().equals("")
                                && TBerat1.getText().trim().equals("") && TRespirasi1.getText().trim().equals("")
                                && TNadi1.getText().trim().equals("") && TGCS1.getText().trim().equals("")) {
                            Valid.textKosong(TKeluhan1, "Hasil Periksa/Perkembangan/Suhu Badan/Tensi");
                        } else {
                            if (tbPemeriksaanPr.getSelectedRow() > -1) {
                                Sequel.mengedit("pemeriksaan_ralan_petugas", "no_rawat='" + tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 1) + "'",
                                        "no_rawat='" + TNoRw2.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                                        + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                        + "suhu_tubuh='" + TSuhu1.getText() + "',tensi='" + TTensi1.getText() + "',"
                                        + "keluhan='" + TKeluhan1.getText() + "',pemeriksaan='" + TPemeriksaan1.getText() + "',"
                                        + "nadi='" + TNadi1.getText() + "',respirasi='" + TRespirasi1.getText() + "',"
                                        + "tinggi='" + TTinggi1.getText() + "',berat='" + TBerat1.getText() + "',imun_ke='" + cmbImun1.getSelectedItem().toString() + "',"
                                        + "gcs='" + TGCS1.getText() + "',alergi='" + TAlergi1.getText() + "',diagnosa='" + TDiagnosa1.getText() + "',"
                                        + "rencana_follow_up='" + TRncanaFolow1.getText() + "',nip='" + kdptg1.getText() + "',"
                                        + "rincian_tindakan='" + TRincianTindakan1.getText() + "'");

                                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi1.getText() + "'");
                                tampilPemeriksaanPetugas();
                                BtnBatalActionPerformed(evt);
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                                TCari.requestFocus();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Yang bisa merubah hanya " + nmOrang2 + ", krn. dia yang menyimpan datanya.");
                        BtnBatalActionPerformed(evt);
                    }

                } else if (TabRawat.getSelectedIndex() == 8) {
                    gantiJawabanRujukan();
                    tampilRujukanInternal();
                    if (tabModeRujukan.getRowCount() != 0) {
                        TabRawat.setBackgroundAt(8, Color.ORANGE);
                        BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
                    } else {
                        TabRawat.setBackgroundAt(8, TabRawat.getBackground());
                        BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
                    }
                }

                Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Jalan','Ganti'");
            }

        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Gagal Tersimpan, hubungi Admin");
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnEditActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnPrint);
    }
}//GEN-LAST:event_BtnEditKeyPressed

    private void tbRawatDrPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatDrPrMouseClicked
        if (tabModeDrPr.getRowCount() != 0) {
            try {
                getDataDrPr();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRawatDrPrMouseClicked

    private void tbRawatDrPrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatDrPrKeyPressed
        if (tabModeDrPr.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDrPr();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRawatDrPrKeyPressed

    private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama from petugas where nip=?", TPerawat, kdptg.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekPetugas2ActionPerformed(null);
        } else {
            Valid.pindah(evt, KdDok, BtnSimpan);
        }
    }//GEN-LAST:event_kdptgKeyPressed

    private void BtnSeekPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPetugas2ActionPerformed
        akses.setform("DlgRawatJalan");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPetugas2ActionPerformed

    private void KdDokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekDokter2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TGCS, kdptg);
        }
    }//GEN-LAST:event_KdDokKeyPressed

    private void BtnSeekDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekDokter2ActionPerformed
        akses.setform("DlgRawatJalan");
        dokter.emptTeks();
        dokter.isCek();
        dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnSeekDokter2ActionPerformed

    private void tbPemeriksaanDrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanDrMouseClicked
        if (tabModePemeriksaanDr.getRowCount() != 0) {
            try {
                getDataPemeriksaan();
                getDataPemeriksaanPr();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanDrMouseClicked

    private void tbPemeriksaanDrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanDrKeyPressed
        if (tabModePemeriksaanDr.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPemeriksaan();
                    getDataPemeriksaanPr();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanDrKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tspo2.requestFocus();
        }
    }//GEN-LAST:event_TBeratKeyPressed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TNadi.requestFocus();
        }
    }//GEN-LAST:event_TTinggiKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRespirasi.requestFocus();
        }
    }//GEN-LAST:event_TNadiKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TGCS.requestFocus();
        }
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        if (TabRawat.getSelectedIndex() == 0) {
            Valid.pindah(evt, TRespirasi, KdDok);
        } else if (TabRawat.getSelectedIndex() == 1) {
            Valid.pindah(evt, TRespirasi, BtnSimpan);
        }
    }//GEN-LAST:event_TGCSKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TKdPrw, cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, TKeluhan);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgRawatJalan");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(rootPaneCheckingEnabled);
    }//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
    }//GEN-LAST:event_btnPasienKeyPressed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            } else {
                DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
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

    private void cmbImunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbImunKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbImunKeyPressed

    private void TResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanObatActionPerformed(null);
        }
    }//GEN-LAST:event_TResepObatKeyPressed

    private void tbResepObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbResepObatMouseClicked
        if (tabModeResepObat.getRowCount() != 0) {
            try {
                getDataCatatanResep();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbResepObatMouseClicked

    private void tbResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbResepObatKeyPressed
        if (tabModeResepObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCatatanResep();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbResepObatKeyPressed

    private void BtnSimpanObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanObatActionPerformed
        if (TDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDok, "Dokter");
        } else if (TResepObat.getText().trim().equals("")) {
            Valid.textKosong(TResepObat, "nama obat");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Anda bukan dokter,.!! Fitur/menu ini khusus untuk dokter.");
            } else if (!Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
                JOptionPane.showMessageDialog(null, "Maaf, dokter yang login tidak sama dg. dokter terjadwal dipoliklinik...!!");
            } else {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where tgl_perawatan like '%" + DTPTgl.getSelectedItem().toString().substring(6, 10) + "%' ", DTPTgl.getSelectedItem().toString().substring(6, 10), 6, noIdObat);
                try {
                    Sequel.menyimpan("catatan_resep", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                        noIdObat.getText(), TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TResepObat.getText(), "BELUM", akses.getkode()
                    });
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    TResepObat.setText("");
                    tampilResepObat();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan, Hubungi Admin ! " + e);
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanObatActionPerformed

    private void BtnSimpanObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanObatKeyPressed

    private void noIdObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noIdObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noIdObatKeyPressed

    private void TResepObatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TResepObatKeyTyped

    private void kdpoliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpoliKeyTyped

    private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdpoliKeyPressed

    private void TPoliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPoliKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TPoliKeyTyped

    private void TPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPoliKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TPoliKeyPressed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");

        akses.setform("DlgFrekuensiPenyakitRalan");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnUnitActionPerformed

    private void unitRJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitRJKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_unitRJKeyPressed

    private void DiagnosaRJdokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaRJdokterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaRJdokterKeyPressed

    private void TDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapi.requestFocus();
        }
    }//GEN-LAST:event_TDiagnosaKeyPressed

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaan.requestFocus();
        }
    }//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TAlergiKeyPressed

    private void tbPemeriksaanPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanPrMouseClicked
        if (tabModePemeriksaanPr.getRowCount() != 0) {
            try {
                getDataPemeriksaanPr();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanPrMouseClicked

    private void tbPemeriksaanPrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanPrKeyPressed
        if (tabModePemeriksaanPr.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPemeriksaanPr();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanPrKeyPressed

    private void TSuhu1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhu1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTensi1.requestFocus();
        }
    }//GEN-LAST:event_TSuhu1KeyPressed

    private void TTensi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TBerat1.requestFocus();
        }
    }//GEN-LAST:event_TTensi1KeyPressed

    private void TBerat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBerat1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tspo21.requestFocus();
        }
    }//GEN-LAST:event_TBerat1KeyPressed

    private void TTinggi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TNadi1.requestFocus();
        }
    }//GEN-LAST:event_TTinggi1KeyPressed

    private void cmbImun1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbImun1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbImun1KeyPressed

    private void TNadi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRespirasi1.requestFocus();
        }
    }//GEN-LAST:event_TNadi1KeyPressed

    private void TRespirasi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRespirasi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TRespirasi1ActionPerformed

    private void TRespirasi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TGCS1.requestFocus();
        }
    }//GEN-LAST:event_TRespirasi1KeyPressed

    private void TGCS1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCS1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKeluhan1.requestFocus();
        }
    }//GEN-LAST:event_TGCS1KeyPressed

    private void TDiagnosa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosa1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAlergi1.requestFocus();
        }
    }//GEN-LAST:event_TDiagnosa1KeyPressed

    private void TKeluhan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaan1.requestFocus();
        }
    }//GEN-LAST:event_TKeluhan1KeyPressed

    private void TPemeriksaan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TDiagnosa1.requestFocus();
        }
    }//GEN-LAST:event_TPemeriksaan1KeyPressed

    private void TAlergi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRncanaFolow1.requestFocus();
        }
    }//GEN-LAST:event_TAlergi1KeyPressed

    private void kdptg1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptg1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Sequel.cariIsi("select nama from petugas where nip=?", TPerawat1, kdptg1.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnSeekPetugas1ActionPerformed(null);
        }
    }//GEN-LAST:event_kdptg1KeyPressed

    private void BtnSeekPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeekPetugas1ActionPerformed
        akses.setform("DlgRawatJalan");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSeekPetugas1ActionPerformed

    private void KdDokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_KdDokKeyTyped

    private void kdptgKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdptgKeyTyped

    private void kdptg1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptg1KeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_kdptg1KeyTyped

    private void DiagnosaRJpetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DiagnosaRJpetugasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiagnosaRJpetugasKeyPressed

    private void MnPrinPRMRJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinPRMRJActionPerformed
        if (LoadHTML5.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (!LoadHTML5.getText().equals("")) {
            if (!TNoRM.getText().equals("")) {
                cetakPRMRJ();                
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya dulu,..!!");
            }
        }
    }//GEN-LAST:event_MnPrinPRMRJActionPerformed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
            isPsien();
        } else {
            Valid.pindah(evt, TCari, TKdPrw);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void TNoRw2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRw2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TNoRw2KeyPressed

    private void TIdObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TIdObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TIdObatKeyPressed

    private void BtnEditObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditObatActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
        } else if (TResepObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silakan pilih dulu salah satu datanya pada tabel..!!");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh merubah resepnya...!!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Anda bukan dokter,.!! Fitur/menu ini khusus untuk dokter.");
            } else if (!Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode())) {
                JOptionPane.showMessageDialog(null, "Maaf, dokter yang login tidak sama dg. dokter terjadwal dipoliklinik...!!");
            } else {
                if (tbResepObat.getSelectedRow() > -1) {
                    Sequel.mengedit("catatan_resep", "noId='" + TIdObat.getText() + "'",
                            "no_rawat='" + TNoRw2.getText() + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "jam_perawatan='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',nama_obat = '" + TResepObat.getText() + "'");
                    tampilResepObat();
                    BtnBatalActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_BtnEditObatActionPerformed

    private void BtnEditObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditObatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEditObatKeyPressed

    private void TNoRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNoRMActionPerformed
        isPsien();
    }//GEN-LAST:event_TNoRMActionPerformed

    private void TRncanaFolowKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRncanaFolowKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRincianTindakan.requestFocus();
        }
    }//GEN-LAST:event_TRncanaFolowKeyPressed

    private void TRincianTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRincianTindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAlergi.requestFocus();
        }
    }//GEN-LAST:event_TRincianTindakanKeyPressed

    private void TRncanaFolow1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRncanaFolow1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRincianTindakan1.requestFocus();
        }
    }//GEN-LAST:event_TRncanaFolow1KeyPressed

    private void TRincianTindakan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRincianTindakan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTerapi1.requestFocus();
        }
    }//GEN-LAST:event_TRincianTindakan1KeyPressed

    private void MnPenilaianAwalKeperawatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalKeperawatanRalanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Masukkan diagnosa lewat kamar inap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
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

    private void TTerapi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TTerapi1KeyPressed

    private void MnStatusPasienPerKunjunganBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusPasienPerKunjunganBtnPrintActionPerformed
        if (tbPemeriksaanPr.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbPemeriksaanPr.requestFocus();
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
            Valid.MyReport("rptRMRaza1.jasper", "report", "::[ Lembar Status Pasien Rawat Jalan Yang Sudah Terisi PerKunjungan ]::",
                    " SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, ifnull(p.no_ktp,'-') no_ktp, IF (p.jk = 'L','Laki-laki','Perempuan') jk, "
                    + "p.tmp_lahir, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "p.gol_darah, p.pekerjaan, p.stts_nikah, p.agama, concat(date_format(rp.tgl_registrasi,'%d/%m/%y'),' ',pj.png_jawab) tgl_registrasi, p.no_tlp, p.umur, p.pnd, "
                    + "LOWER(prp.pemeriksaan) pemeriksaan, LOWER(prp.diagnosa) diagnosa, IFNULL(dp.kd_penyakit,'-') kd_icd_10, LOWER(IFNULL(prp.terapi,'-')) terapi, d.nm_dokter "
                    + "FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 WHERE rp.no_rawat = '" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnStatusPasienPerKunjunganBtnPrintActionPerformed

    private void MnStatusPasienAllKunjunganBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed
        x = 0;
        x = Sequel.cariInteger("SELECT count(1) cek FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                + "WHERE rp.no_rkm_medis = '" + TNoRM.getText() + "'");

        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            tbPemeriksaanPr.requestFocus();
        } else if (x == 0) {
            JOptionPane.showMessageDialog(null, "Data status pasien rawat jalan utk. semua kunjungan tdk. ditemukan..!!!!");
            tbPemeriksaanPr.requestFocus();
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
                    + "LOWER(prp.pemeriksaan) pemeriksaan, LOWER(prp.diagnosa) diagnosa, IFNULL(dp.kd_penyakit,'-') kd_icd_10, LOWER(IFNULL(prp.terapi,'-')) terapi, d.nm_dokter, "
                    + "IFNULL(GROUP_CONCAT(concat(db.nama_brng,' ',dpo.jml,' ',LOWER(db.kode_sat)) SEPARATOR ', '),'-') obat_farmasi "
                    + "FROM pemeriksaan_ralan_petugas prp INNER JOIN reg_periksa rp on rp.no_rawat=prp.no_rawat "
                    + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=prp.no_rawat and dp.prioritas=1 "
                    + "LEFT JOIN detail_pemberian_obat dpo on dpo.no_rawat=rp.no_rawat "
                    + "LEFT JOIN databarang db on db.kode_brng=dpo.kode_brng "
                    + "WHERE rp.no_rkm_medis = '" + TNoRM.getText() + "' GROUP BY dpo.no_rawat ORDER BY rp.tgl_registrasi DESC ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnStatusPasienAllKunjunganBtnPrintActionPerformed

    private void TTerapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTerapiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TRncanaFolow.requestFocus();
        }
    }//GEN-LAST:event_TTerapiKeyPressed

    private void MnPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPermintaanLabActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbPemeriksaanDr.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanLabRAZA lab = new DlgPermintaanLabRAZA(null, false);
                lab.setSize(978, internalFrame1.getHeight() - 40);
                lab.setLocationRelativeTo(internalFrame1);
                lab.isPasien(TNoRw.getText());
                lab.AutoNomerMinta();
                lab.nmPemeriksaan.setText("");
                lab.nmPemeriksaan.requestFocus();
                lab.tampilItemLab();
                lab.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPermintaanLabActionPerformed

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbPemeriksaanDr.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPermintaanRadiologi periksarad = new DlgPermintaanRadiologi(null, false);
                periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                periksarad.setLocationRelativeTo(internalFrame1);
                periksarad.emptTeks();
                periksarad.isCek();
                periksarad.setNoRm(TNoRw.getText(), Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
                periksarad.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void TabPemeriksaanDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPemeriksaanDokterMouseClicked
        if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
            tampilPemeriksaanDokter();
        } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
            tampilMintaLab1();
        } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
            tampilMintaRad1();
        }
    }//GEN-LAST:event_TabPemeriksaanDokterMouseClicked

    private void tbPeriksaLabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPeriksaLabMouseClicked
        if (tabModeLab1.getRowCount() != 0) {
            try {
                getDataMintaLab1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPeriksaLabMouseClicked

    private void tbPeriksaLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeriksaLabKeyPressed
        if (tabModeLab1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMintaLab1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPeriksaLabKeyPressed

    private void tbPeriksaRadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPeriksaRadMouseClicked
        if (tabModeRad1.getRowCount() != 0) {
            try {
                getDataMintaRad1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPeriksaRadMouseClicked

    private void tbPeriksaRadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeriksaRadKeyPressed
        if (tabModeRad1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataMintaRad1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPeriksaRadKeyPressed

    private void MnHapusPermintaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusPermintaanLabActionPerformed
        if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
            if (noiD.equals("") && noMinta.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Lab. dengan mengklik data pada tabel...!!!");
                tbPeriksaLab.requestFocus();
            } else if (diperiksa.equals("SUDAH")) {
                JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat dihapus..!!!");
            } else {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + noiD + "' and no_minta='" + noMinta + "'");
                    noiD = "";
                    noMinta = "";
                    tampilMintaLab1();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Lab. dengan mengklik data pada tabel...!!!");
            TabPemeriksaanDokter.setSelectedIndex(1);
            tampilMintaLab1();
        }
    }//GEN-LAST:event_MnHapusPermintaanLabActionPerformed

    private void MnHapusPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusPeriksaRadiologiActionPerformed
        if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
            if (noordeR1.equals("") && (waktuSimpanRad.equals(""))) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Radiologi dengan mengklik data pada tabel...!!!");
                tbPeriksaRad.requestFocus();
            } else if (Sequel.cariInteger("select count(-1) from permintaan_radiologi where noorder='" + noordeR1 + "' and "
                    + "waktu_simpan='" + waktuSimpanRad + "' and status='Diterima'") > 0) {
                JOptionPane.showMessageDialog(null, "Data permintaan pemeriksaan radiologi sudah diperiksa...!!!!");
            } else {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.queryu("DELETE FROM permintaan_radiologi WHERE noorder='" + noordeR1 + "' and waktu_simpan='" + waktuSimpanRad + "'");
                    tampilMintaRad1();
                }
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data permintaan periksa Radiologi dengan mengklik data pada tabel...!!!");
            TabPemeriksaanDokter.setSelectedIndex(2);
            tampilMintaRad1();
        }
    }//GEN-LAST:event_MnHapusPeriksaRadiologiActionPerformed

    private void MnSuratTindakanDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratTindakanDokterActionPerformed
        cekSuratTindakan = 0;
        cekSuratTindakan = Sequel.cariInteger("select count(1) cek from surat_tindakan_kedokteran where no_rawat='" + TNoRw.getText() + "' and kasus_tindakan='Ralan'");

        if (TNoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            BtnCari.requestFocus();
        } else {
            DlgIGD surat = new DlgIGD(null, false);
            surat.DlgSuratTindakanDokter.setSize(674, 373);
            surat.DlgSuratTindakanDokter.setLocationRelativeTo(internalFrame1);
            surat.norwSurat.setText(TNoRw.getText());
            surat.cekSuratTindakan(TNoRw.getText());
            surat.TNoRM.setText(TNoRM.getText());
            surat.DlgSuratTindakanDokter.setVisible(true);

            if (cekSuratTindakan == 0) {
                surat.TglSuratTindakan.setDate(new Date());
            } else {
                surat.cekSuratTindakan(TNoRw.getText());
            }

            surat.nmPJ.setText(Sequel.cariIsi("select namakeluarga from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            surat.nmPJ.requestFocus();

            if (surat.jnsSurat.getSelectedItem().toString().equals("PERSETUJUAN")) {
                surat.alasanTolak.setText("-");
                surat.alasanTolak.setEnabled(false);
            } else {
                surat.alasanTolak.setText("");
                surat.alasanTolak.setEnabled(true);
            }
        }
    }//GEN-LAST:event_MnSuratTindakanDokterActionPerformed

    private void MnDataParuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataParuActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien poliklinik paru belum terpilih...!!!");
        } else {
            WindowDataParu.setSize(571, 302);
            WindowDataParu.setLocationRelativeTo(internalFrame1);
            WindowDataParu.setVisible(true);
            PasienParu(TNoRw.getText());
            cekDataParu(TNoRw.getText());
        }
    }//GEN-LAST:event_MnDataParuActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowDataParu.dispose();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (serangan.isSelected() == true) {
            cekppok = "SERANGAN";
        } else if (konsultasi.isSelected() == true) {
            cekppok = "KONSULTASI";
        } else {
            cekppok = "-";
        }
        
        if (lain.isSelected() == true) {
            cekobattb = "LAIN-LAIN";
        } else if (bpjs.isSelected() == true) {
            cekobattb = "BPJS";
        } else if (dot.isSelected() == true) {
            cekobattb = "DOT";
        } else if (puskes.isSelected() == true) {
            cekobattb = "PUSKESMAS";
        } else {
            cekobattb = "-";
        }
        
        if (!kode_poli.equals("PAR")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak terdaftar sebagai pasien poliklinik paru...!!!");
            BtnCloseIn5.requestFocus();
        } else {
            if (serangan.isSelected() == false && konsultasi.isSelected() == false
                    && lain.isSelected() == false && bpjs.isSelected() == false
                    && dot.isSelected() == false && puskes.isSelected() == false
                    && (ket_paru.getText().equals(" ") || ket_paru.getText().equals("") || ket_paru.getText().equals("-"))) {
                Sequel.meghapus("sensus_poli_paru", "no_rawat", no_rawat.getText());
            } else {
                Sequel.menyimpan("sensus_poli_paru", "'" + no_rawat.getText() + "','" + cekppok + "','" + cekobattb + "','" + ket_paru.getText() + "'", "Data Tambahan Sensus Poliklinik Paru");
            }
            BtnBaruActionPerformed(null);
            WindowDataParu.dispose();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void no_rawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_rawatKeyPressed

    }//GEN-LAST:event_no_rawatKeyPressed

    private void tgl_regKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_regKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_regKeyPressed

    private void seranganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seranganActionPerformed
        if (serangan.isSelected() == true) {
            konsultasi.setSelected(false);
        }
    }//GEN-LAST:event_seranganActionPerformed

    private void konsultasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_konsultasiActionPerformed
        if (konsultasi.isSelected() == true) {
            serangan.setSelected(false);
        }
    }//GEN-LAST:event_konsultasiActionPerformed

    private void lainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lainActionPerformed
        if (lain.isSelected() == true) {
            bpjs.setSelected(false);
            dot.setSelected(false);
            puskes.setSelected(false);
        }
    }//GEN-LAST:event_lainActionPerformed

    private void bpjsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bpjsActionPerformed
        if (bpjs.isSelected() == true) {
            lain.setSelected(false);
            dot.setSelected(false);
            puskes.setSelected(false);
        }
    }//GEN-LAST:event_bpjsActionPerformed

    private void dotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dotActionPerformed
        if (dot.isSelected() == true) {
            bpjs.setSelected(false);
            lain.setSelected(false);
            puskes.setSelected(false);
        }
    }//GEN-LAST:event_dotActionPerformed

    private void puskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puskesActionPerformed
        if (puskes.isSelected() == true) {
            bpjs.setSelected(false);
            dot.setSelected(false);
            lain.setSelected(false);
        }
    }//GEN-LAST:event_puskesActionPerformed

    private void ket_paruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_paruKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ket_paruKeyPressed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        serangan.setSelected(false);
        serangan.requestFocus();
        konsultasi.setSelected(false);
        lain.setSelected(false);
        bpjs.setSelected(false);
        dot.setSelected(false);
        puskes.setSelected(false);
        ket_paru.setText("");
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void BtnBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBaruKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
    }//GEN-LAST:event_BtnBaruKeyPressed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        ceksensusparu = 0;
        ceksensusparu = Sequel.cariInteger("select count(1) cek from sensus_poli_paru where no_rawat='" + no_rawat.getText() + "'");

        WindowDataParu.setSize(571, 302);
        WindowDataParu.setLocationRelativeTo(internalFrame1);
        WindowDataParu.setVisible(true);

        if (ceksensusparu >= 1) {
            i = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                Sequel.meghapus("sensus_poli_paru", "no_rawat", no_rawat.getText());
                JOptionPane.showMessageDialog(null, "Data tambahan sensus pasien poliklinik paru berhasil dihapus...!!!");
                BtnBaruActionPerformed(null);
                WindowDataParu.dispose();
            } else {
                WindowDataParu.setSize(571, 302);
                WindowDataParu.setLocationRelativeTo(internalFrame1);
                WindowDataParu.setVisible(true);
                serangan.requestFocus();
            }
        } else if (ceksensusparu == 0) {
            WindowDataParu.setSize(571, 302);
            WindowDataParu.setLocationRelativeTo(internalFrame1);
            WindowDataParu.setVisible(true);
            JOptionPane.showMessageDialog(null, "Tidak ditemukan data tambahan sensus pasien poliklinik paru utk. pasien ini...!!!");
            BtnBaruActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnHapus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
    }//GEN-LAST:event_BtnHapus1KeyPressed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (serangan.isSelected() == true) {
            cekppok = "SERANGAN";
        } else if (konsultasi.isSelected() == true) {
            cekppok = "KONSULTASI";
        } else {
            cekppok = "-";
        }

        if (lain.isSelected() == true) {
            cekobattb = "LAIN-LAIN";
        } else if (bpjs.isSelected() == true) {
            cekobattb = "BPJS";
        } else if (dot.isSelected() == true) {
            cekobattb = "DOT";
        } else if (puskes.isSelected() == true) {
            cekobattb = "PUSKESMAS";
        } else {
            cekobattb = "-";
        }

        if (!kode_poli.equals("PAR")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak terdaftar sebagai pasien poliklinik paru...!!!");
            BtnCloseIn5.requestFocus();
        } else {
            if (serangan.isSelected() == false && konsultasi.isSelected() == false
                    && lain.isSelected() == false && bpjs.isSelected() == false
                    && dot.isSelected() == false && puskes.isSelected() == false
                    && (ket_paru.getText().equals(" ") || ket_paru.getText().equals("") || ket_paru.getText().equals("-"))) {
                Sequel.meghapus("sensus_poli_paru", "no_rawat", no_rawat.getText());
            } else {
                Sequel.mengedit("sensus_poli_paru", "no_rawat='" + no_rawat.getText() + "'",
                        "kasus_ppok='" + cekppok + "', obat_TB='" + cekobattb + "',keterangan='" + ket_paru.getText() + "' ");
            }
            BtnBaruActionPerformed(null);
            WindowDataParu.dispose();
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabModeFarmasi.getRowCount() != 0) {
            try {
                getDataFarmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if (tabModeFarmasi.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFarmasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (TCariObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Obat/Alkes yang akan dicari harus diisi dulu...!!!");
            TCariObat.requestFocus();
        } else {
            tampilFarmasi();
            tbObat.requestFocus();
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCariRekamMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariRekamMedisActionPerformed
        WindowRiwayatKunjungan.setSize(874, 361);
        WindowRiwayatKunjungan.setLocationRelativeTo(internalFrame1);
        pasiendipilih.setText("");
        tampilRiwayatKun(Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
        WindowRiwayatKunjungan.setVisible(true);
        tbRiwayatKunj.requestFocus();
    }//GEN-LAST:event_BtnCariRekamMedisActionPerformed

    private void BtnCariRekamMedisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariRekamMedisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariRekamMedisKeyPressed

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        WindowRiwayatKunjungan.dispose();
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void tbRiwayatKunjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatKunjMouseClicked

        try {
            getdataRiwKunj();
        } catch (java.lang.NullPointerException e) {
        }
    }//GEN-LAST:event_tbRiwayatKunjMouseClicked

    private void tbRiwayatKunjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKunjKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                //                    getdataRiwKunj();
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
            setNoRm(norw_dipilih, TglKunRwt.getDate(), TglKunRwt.getDate());
            tampilDrPr();
            petugas(kddokter_dipilih, akses.getkode());
            isCek();
        }
    }//GEN-LAST:event_BtnRMActionPerformed

    private void MnRujukanInternalPoliBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukanInternalPoliBtnPrintActionPerformed
        cekRujukInternal = 0;
        cekRujukInternal = Sequel.cariInteger("select count(-1) from rujukan_internal_poli where no_rawat='" + TNoRw.getText() + "'");
                
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasiennya pada tabel rawat jalan...!!!");
        } else {
            if (cekRujukInternal == 0 || cekRujukInternal > 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
                dlgrjk.tampil();
                dlgrjk.inputbaru();                
                dlgrjk.setVisible(true);
            } else if (cekRujukInternal == 1) {
                dlgrjk.setSize(787, 588);
                dlgrjk.setLocationRelativeTo(internalFrame1);
                dlgrjk.isCek();
                dlgrjk.setNoRm(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
                dlgrjk.SatuTujuanPoli();
                dlgrjk.tampil();                
                dlgrjk.setVisible(true);
                dlgrjk.BtnUnit.requestFocus();
            }
        }
    }//GEN-LAST:event_MnRujukanInternalPoliBtnPrintActionPerformed

    private void ChkPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPemeriksaanActionPerformed
        cekDataPetugas = 0;
        cekDataPetugas = Sequel.cariInteger("select count(-1) from pemeriksaan_ralan_petugas p inner join reg_periksa r on r.no_rawat=p.no_rawat where "
                + "p.tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' and r.no_rkm_medis like '%" + TCariPasien.getText() + "%' "
                + "and r.kd_poli like '%" + kdpoli.getText() + "%' and p.no_rawat='" + TNoRw.getText() + "'");

        if (ChkPemeriksaan.isSelected() == true) {
            if (cekDataPetugas == 0) {
                JOptionPane.showMessageDialog(null, "Data pemeriksaan pasien oleh perawat/bidan tidak ditemukan...!!!");
                ChkPemeriksaan.setSelected(false);
            } else {
                cekPemeriksaanPetugas();
            }
        } else if (ChkPemeriksaan.isSelected() == false) {
            TSuhu.setText("");
            TTensi.setText("");
            TNadi.setText("");
            TRespirasi.setText("");
            TTinggi.setText("");
            TBerat.setText("");
            TGCS.setText("");
            TKeluhan.setText("");
            Tspo2.setText("");
            cmbImun.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkPemeriksaanActionPerformed

    private void BtnCopyResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepActionPerformed
        if (tabModeResep2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbPemberianResep.requestFocus();
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
        } else {
            try {
                j = 0;
                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                        j++;
                    }
                }

                if (j == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu item resep obatnya,.!!!");
                    tbItemResep.requestFocus();
                } else {
                    for (i = 0; i < tbItemResep.getRowCount(); i++) {
                        if (tbItemResep.getValueAt(i, 0).toString().equals("true")) {
                            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                                    + "tgl_perawatan like '%" + DTPTgl.getSelectedItem().toString().substring(6, 10) + "%' ",
                                    DTPTgl.getSelectedItem().toString().substring(6, 10), 6, noIdObatCopy);

                            Sequel.menyimpan("catatan_resep", "'" + noIdObatCopy.getText() + "','" + TNoRw.getText() + "', "
                                    + "'" + Sequel.cariIsi("select date(now())") + "',"
                                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                                    + "'" + tbItemResep.getValueAt(i, 4).toString() + "','BELUM','" + akses.getkode() + "'", "Copy Resep Sebelumnya");
                        }
                    }

                    ChkTanggal.setSelected(false);
                    DTPCari1.setDate(new Date());
                    DTPCari2.setDate(new Date());
                    WindowRiwayatResep.dispose();
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    JOptionPane.showMessageDialog(null, "Resep sebelumnya pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " berhasil tercopy,..!!!");
                }

            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnCopyResepActionPerformed

    private void BtnCopyResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCopyResepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCopyResepKeyPressed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowRiwayatResep.dispose();
        TResepObat.requestFocus();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void tbPemberianResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianResepMouseClicked
        tglResep = "";
        
        if(tabModeResep1.getRowCount()!=0){
            try {                
                tampilItemResep(tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 3).toString(), TNoRM.getText(), 
                        tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 4).toString());
                tglResep = tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 3).toString();
                cmbConteng.setSelectedIndex(0);
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemberianResepMouseClicked

    private void tbPemberianResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianResepKeyPressed
        if (tabModeResep1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    tampilItemResep(tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 3).toString(), TNoRM.getText(),
                            tbPemberianResep.getValueAt(tbPemberianResep.getSelectedRow(), 4).toString());
                    cmbConteng.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemberianResepKeyPressed

    private void tbItemResepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemResepMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepMouseClicked

    private void tbItemResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbItemResepKeyPressed

    private void cmbContengItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbContengItemStateChanged
        if (tabModeResep2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbPemberianResep.requestFocus();
        } else if (cmbConteng.getSelectedItem().equals("Semuanya")) {
            for (i = 0; i < tbItemResep.getRowCount(); i++) {
                tbItemResep.setValueAt(Boolean.TRUE, i, 0);
            }
        } else if (cmbConteng.getSelectedItem().equals(" ") || cmbConteng.getSelectedItem().equals("Dibatalkan")) {
            for (i = 0; i < tbItemResep.getRowCount(); i++) {
                tbItemResep.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_cmbContengItemStateChanged

    private void ChkPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPoliActionPerformed
        if (ChkPoli.isSelected() == true) {
            ChkPoli.setText("SEMUA Poliklinik");
            cmbConteng.setSelectedIndex(0);
            tampilTglResep();
            tampilItemResep("tglnya kosong", "normnya kosong", "poli zonk");
        } else if (ChkPoli.isSelected() == false) {
            ChkPoli.setText("HANYA Poliklinik Ini");
            cmbConteng.setSelectedIndex(0);
            tampilTglResep();
            tampilItemResep("tglnya kosong", "normnya kosong", "poli zonk");
        }
    }//GEN-LAST:event_ChkPoliActionPerformed

    private void TCariObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCariObatKeyPressed

    private void BtnPrinResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrinResepActionPerformed
        if (tbResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Resep obat untuk pasien tersebut belum ada ditabel...!!!!");
            BtnCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No. rawat pasien tidak ditemukan...!!!!");
            tbResepObat.requestFocus();
        } else {
            //cek contengnya
            x = 0;
            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                    x++;                    
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Item resep yg. telah diberikan oleh " + TDokter.getText() + " belum diconteng utk. dicetak...!!");
                contengResep();
                BtnPrinResep.requestFocus();
            } else {
                resepDipilih = "";
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        if (resepDipilih.equals("")) {
                            resepDipilih = "'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        } else {
                            resepDipilih = resepDipilih + ",'" + tbResepObat.getValueAt(i, 7).toString() + "'";
                        }
                    }
                }

                if (Sequel.cariInteger("SELECT COUNT(-1) FROM catatan_resep WHERE no_rawat ='" + TNoRw.getText() + "' AND noId IN (" + resepDipilih + ")") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, hnya. resep utk. hari ini dari poli/inst. " + Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'")
                            + " yg. bisa dicetak, krn. pasien sdg. dilayani disini...!!!");
                    tbResepObat.requestFocus();
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
                    Valid.MyReport("rptResepRalan.jasper", "report", "::[ Resep Dokter Poliklinik/Unit Rawat Jalan ]::",
                            " select c.no_rawat, pl.nm_poli, d.nm_dokter, CONCAT('Martapura, ',DATE_FORMAT(c.tgl_perawatan,'%d/%m/%Y')) tgl_resep, c.nama_obat, "
                            + "r.no_rkm_medis, p.nm_pasien, CONCAT(r.umurdaftar,' ',r.sttsumur) umur, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, d.no_ijn_praktek no_sip, ifnull(p.no_tlp,'-') noHP from catatan_resep c "
                            + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                            + "INNER JOIN poliklinik pl on pl.kd_poli=r.kd_poli INNER JOIN pasien p on p.no_rkm_medis=r.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where c.no_rawat ='" + TNoRw.getText() + "' and c.noId in (" + resepDipilih + ") order by c.noId", param);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_BtnPrinResepActionPerformed

    private void BtnCopyResepTerakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopyResepTerakhirActionPerformed
        cekResep = "";
        tglResep = "";
        cekResep = Sequel.cariIsi("SELECT count(-1) cek FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                + "WHERE rp.kd_poli='" + polinya + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");
        
        if (cekResep.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, tidak ada resep terakhir sesuai kunjungan yg. tersimpan didalam sistem...!!!!");
        } else if (akses.getadmin() == true) {
            JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
        } else {            
            tglResep = Sequel.cariIsi("SELECT cr.tgl_perawatan FROM catatan_resep cr INNER JOIN reg_periksa rp ON rp.no_rawat=cr.no_rawat "
                    + "WHERE rp.kd_poli='" + polinya + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' "
                    + "GROUP BY cr.tgl_perawatan, rp.no_rkm_medis ORDER BY cr.tgl_perawatan DESC LIMIT 1");
            
            x = JOptionPane.showConfirmDialog(null, "Resep terakhir pada tgl. " + Valid.SetTglINDONESIA(tglResep) + " apakah akan dicopy...?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tampilItemResep(tglResep, TNoRM.getText(), polinya);

                for (i = 0; i < tbItemResep.getRowCount(); i++) {
                    tbItemResep.setValueAt(Boolean.TRUE, i, 0);
                }
                BtnCopyResepActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnCopyResepTerakhirActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        WindowRiwayatResep.setSize(1063, 505);
        WindowRiwayatResep.setLocationRelativeTo(internalFrame1);
        WindowRiwayatResep.setVisible(true);
        ChkPoli.setSelected(false);
        cmbConteng.setSelectedIndex(0);
        tampilTglResep();
        Valid.tabelKosong(tabModeResep2);
    }//GEN-LAST:event_BtnResepActionPerformed

    private void ChkCopyPemeriksaanDRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCopyPemeriksaanDRActionPerformed
        if (ChkCopyPemeriksaanDR.isSelected() == true) {
            TKeluhan1.setText(TKeluhan.getText());
            TPemeriksaan1.setText(TPemeriksaan.getText());
            TAlergi1.setText(TAlergi.getText());
            TDiagnosa1.setText(TDiagnosa.getText());
            TSuhu1.setText(TSuhu.getText());
            TTensi1.setText(TTensi.getText());
            TBerat1.setText(TBerat.getText());
            TTinggi1.setText(TTinggi.getText());
            cmbImun1.setSelectedItem(cmbImun.getSelectedItem().toString());
            TNadi1.setText(TNadi.getText());
            TRespirasi1.setText(TRespirasi.getText());
            TGCS1.setText(TGCS.getText());
            TRncanaFolow1.setText(TRncanaFolow.getText());
            TRincianTindakan1.setText(TRincianTindakan.getText());
            TTerapi1.setText(TTerapi.getText());
            kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
        } else {
            TKeluhan1.setText("");
            TPemeriksaan1.setText("");
            TAlergi1.setText("");
            TDiagnosa1.setText("");
            TSuhu1.setText("");
            TTensi1.setText("");
            TBerat1.setText("");
            TTinggi1.setText("");
            cmbImun1.setSelectedIndex(0);
            TNadi1.setText("");
            TRespirasi1.setText("");
            TGCS1.setText("");
            TRncanaFolow1.setText("");
            TRincianTindakan1.setText("");
            TTerapi1.setText("");
        }
    }//GEN-LAST:event_ChkCopyPemeriksaanDRActionPerformed

    private void MnRehabMedikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRehabMedikActionPerformed
        cekPilihanRehab = 0;
        
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
        } else if (!polinya.equals("IRM")) {
            JOptionPane.showMessageDialog(rootPane, "Hanya utk. pasien yg. berkunjung ke poliklinik rehabilitasi medik...!!!!");
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

    private void BtnCloseIn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn9ActionPerformed
        WindowRehabMedik.dispose();
    }//GEN-LAST:event_BtnCloseIn9ActionPerformed

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
                Sequel.menyimpan("data_rehab_medik","?,?","Jenis Rehabilitasi Medik",2,new String[]{TNoRw.getText(),cmbRM.getSelectedItem().toString()});
            } else if (cekPilihanRehab > 0) {
                Sequel.mengedit("data_rehab_medik", "no_rawat=?", " jns_rehabmedik=?", 2, new String[]{cmbRM.getSelectedItem().toString(), TNoRw.getText()});
            }

            cekRehabMedik();
            WindowRehabMedik.dispose();
        }
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void MnPenilaianAwalKeperawatanKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalKeperawatanKebidananActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (!polinya.equals("OBG")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik KEBIDANAN & KANDUNGAN...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalKeperawatanKebidanan form = new RMPenilaianAwalKeperawatanKebidanan(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPenilaianAwalKeperawatanKebidananActionPerformed

    private void tbRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRujukanMouseClicked
        if (tabModeRujukan.getRowCount() != 0) {
            try {
                getDataRujukan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRujukanMouseClicked

    private void tbRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRujukanKeyPressed
        if (tabModeRujukan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRujukan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRujukanKeyPressed

    private void MnCetakJawabanRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakJawabanRujukanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (Tnorawat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rujukanya pada tabel...!!!!");
            tbRujukan.requestFocus();
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tglSuratPenjawab", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
            param.put("tglSuratPerujuk", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + Tnorawat.getText() + "'")));
            param.put("nmDokterPerujuk", OlehDokter.getText());

            if (Sequel.cariIsi("select ifnull(keterangan_balasan,'') from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'").equals("")) {
                param.put("judul", "SURAT RUJUKAN INTERNAL POLIKLINIK");
                param.put("nmDokterPenjawab", ".........................................");
            } else {
                param.put("judul", "SURAT BALASAN/JAWABAN RUJUKAN INTERNAL POLIKLINIK");
                param.put("nmDokterPenjawab", dokterMenjawab.getText());
            }
            Valid.MyReport("rptSuratRujukanInternal.jasper", "report", "::[ Surat Rujukan Poliklinik Internal ]::",
                    "SELECT r.no_rawat, p.no_rkm_medis, p.nm_pasien, pl1.nm_poli asal_poli, pl2.nm_poli ke_poli, "
                    + "DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_registrasi, d.nm_dokter dr_perujuk, "
                    + "r.keterangan, DATE_FORMAT(r.tgl_rencana_dirujuk,'%d-%m-%Y') tgl_rencana_dirujuk, IF(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, ifnull(r.keterangan_balasan,'-') jawaban FROM rujukan_internal_poli r "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = r.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN poliklinik pl1 ON pl1.kd_poli = r.kd_poli INNER JOIN poliklinik pl2 ON pl2.kd_poli = r.kd_poli_pembalas "
                    + "INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter WHERE r.no_rawat='" + Tnorawat.getText() + "' AND r.kd_poli_pembalas='" + polinya + "'", param);

            Tnorawat.setText("");
            TDariPoli.setText("");
            tglDirujuk.setText("");
            OlehDokter.setText("");
            TKetAsalRujukan.setText("");

            cmbStatus.setSelectedIndex(0);
            poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
            dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                    + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
            TJwbnRujukan.setText("");
            tbRujukan.requestFocus();

            if (cmbStatus.getSelectedIndex() == 0) {
                jawaban = "Sudah";
            } else {
                jawaban = "Belum";
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCetakJawabanRujukanActionPerformed

    private void MnPenilaianAwalMedisRalanTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanTHTActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (!polinya.equals("THT")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik THT...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanTHT form = new RMPenilaianAwalMedisRalanTHT(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), KdDok.getText(), TDokter.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanTHTActionPerformed

    private void ChkInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInput1ActionPerformed
        isFormRiwayatObat();
    }//GEN-LAST:event_ChkInput1ActionPerformed

    private void tbTglBeriObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTglBeriObatMouseClicked
        if (tabModeTglBeriObat.getRowCount() != 0) {
            try {
                getDataRiwObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTglBeriObatMouseClicked

    private void tbTglBeriObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTglBeriObatKeyPressed
        if (tabModeTglBeriObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataRiwObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTglBeriObatKeyPressed

    private void tbItemObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItemObatMouseClicked
        if (tabModeRiwItemObat.getRowCount() != 0) {
            try {
                getDataItemObat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItemObatMouseClicked

    private void tbItemObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItemObatKeyPressed
        if (tabModeRiwItemObat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataItemObat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItemObatKeyPressed

    private void ChkPoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPoli1ActionPerformed
        Valid.tabelKosong(tabModeRiwItemObat);
        if (ChkPoli1.isSelected() == true) {
            ChkPoli1.setText("Hanya dipoliklinik ini");
            tampilTglBeriObat();
        } else if (ChkPoli1.isSelected() == false) {
            ChkPoli1.setText("Semua Poliklinik");
            tampilTglBeriObat();
        }
    }//GEN-LAST:event_ChkPoli1ActionPerformed

    private void MnSuratPengantarRanapBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratPengantarRanapBtnPrintActionPerformed
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
                surat.setData(TNoRw.getText(), TNoRM.getText(), Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + TNoRw.getText() + "'"), KdDok.getText());
                surat.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnSuratPengantarRanapBtnPrintActionPerformed

    private void MnDibatalkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDibatalkanActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();            

            for (i = 0; i < tbResepObat.getRowCount(); i++) {
                if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                    tbResepObat.setValueAt(Boolean.FALSE, i, 0);
                }
            }
        }
    }//GEN-LAST:event_MnDibatalkanActionPerformed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();
            contengResep();
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

    private void ChkPrmrjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkPrmrjActionPerformed
        if (ChkPrmrj.isSelected() == true) {
            if (TDiagnosa.getText().length() < 1) {
                JOptionPane.showMessageDialog(null, "Maaf, diagnosa harus sudah terisi dulu dg. benar...!!!!");
                TDiagnosa.requestFocus();
                ChkPrmrj.setSelected(false);
            }
        }        
    }//GEN-LAST:event_ChkPrmrjActionPerformed

    private void MnPenilaianAwalMedisRalanGeriatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanGeriatriActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            TCari.requestFocus();
        } else if (!polinya.equals("006")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Geriatri...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanGeriatri form = new RMPenilaianAwalMedisRalanGeriatri(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), KdDok.getText(), TDokter.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanGeriatriActionPerformed

    private void MnPenilaianTambahanGeriatriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianTambahanGeriatriActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada tabel...!!!");
            TCari.requestFocus();
        } else if (!polinya.equals("006")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Geriatri...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianTambahanGeriatri form = new RMPenilaianTambahanGeriatri(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), KdDok.getText(), TDokter.getText());
            form.tampil();
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPenilaianTambahanGeriatriActionPerformed

    private void MnPenilaianAwalMedisRalanMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPenilaianAwalMedisRalanMataActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else if (!polinya.equals("MAT")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar dipoliklinik Mata...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            RMPenilaianAwalMedisRalanMata form = new RMPenilaianAwalMedisRalanMata(null, false);
            form.isCek();
            form.emptTeks();
            form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPenilaianAwalMedisRalanMataActionPerformed

    private void ChkTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTanggalItemStateChanged
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_ChkTanggalItemStateChanged

    private void Tspo21KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspo21KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTinggi1.requestFocus();
        }
    }//GEN-LAST:event_Tspo21KeyPressed

    private void Tspo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspo2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER || evt.getKeyCode() == KeyEvent.VK_TAB) {
            TTinggi.requestFocus();
        }
    }//GEN-LAST:event_Tspo2KeyPressed

    private void MnAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsesmenMedikObstetriActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            if (polinya.equals("IGDK") || polinya.equals("OBG")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgRawatJalan");
                RMPenilaianAwalMedikObstetriRalan form = new RMPenilaianAwalMedikObstetriRalan(null, false);
                form.emptTeks();
                form.isCek();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Menu ini hanya utk. pasien yg. terdaftar di IGD/PONEK/Poliklinik Kandungan saja...!!!");
            }
        }
    }//GEN-LAST:event_MnAsesmenMedikObstetriActionPerformed

    private void MnRiwayatPerawatanICareNoKartuBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerawatanICareNoKartuBtnPrintActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasiennya...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));            
            if (!Sequel.cariIsi("select dpjpLayan from bridging_sep where no_rawat='" + TNoRw.getText() + "'").equals("")) {
                akses.setform("DlgRawatJalan");
                ICareRiwayatPerawatan dlgki = new ICareRiwayatPerawatan(null, false);
                dlgki.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                dlgki.setLocationRelativeTo(internalFrame1);
                dlgki.setPasien(Sequel.cariIsi("select no_peserta from pasien where pasien.no_rkm_medis=?", TNoRM.getText()), 
                        Sequel.cariIsi("select dpjpLayan from bridging_sep where no_rawat='" + TNoRw.getText() + "'"));
                dlgki.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, menu ini hanya untuk pasien BPJS saja...!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRiwayatPerawatanICareNoKartuBtnPrintActionPerformed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void BtnTarifTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTarifTindakanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(0);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());            
            tampilDrPr();
            TotalNominal();
        }
    }//GEN-LAST:event_BtnTarifTindakanActionPerformed

    private void BtnPemeriksaanDRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemeriksaanDRActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(1);
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                        TCari.setText(TNoRM.getText());
                        tampilPemeriksaanDokter();
                    } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                        tampilMintaLab1();
                    } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                        tampilMintaRad1();
                    }
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                            TCari.setText(TNoRM.getText());
                            tampilPemeriksaanDokter();
                        } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                            tampilMintaLab1();
                        } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                            tampilMintaRad1();
                        }
                    }
                }
            } else {
                if (TabPemeriksaanDokter.getSelectedIndex() == 0) {
                    TCari.setText(TNoRM.getText());
                    tampilPemeriksaanDokter();
                } else if (TabPemeriksaanDokter.getSelectedIndex() == 1) {
                    tampilMintaLab1();
                } else if (TabPemeriksaanDokter.getSelectedIndex() == 2) {
                    tampilMintaRad1();
                }
            }
        }
    }//GEN-LAST:event_BtnPemeriksaanDRActionPerformed

    private void BtnPemeriksaanPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemeriksaanPRActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(2);
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    if (ChkCopyPemeriksaanDR.isSelected() == true) {
                        kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                        TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                    } else {
                        kdptg1.setText(akses.getkode());
                        TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                        TCari.setText(TNoRM.getText());
                    }
                    tampilPemeriksaanPetugas();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        if (ChkCopyPemeriksaanDR.isSelected() == true) {
                            kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                        } else {
                            kdptg1.setText(akses.getkode());
                            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                            TCari.setText(TNoRM.getText());
                        }
                        tampilPemeriksaanPetugas();
                    }
                }
            } else {
                if (ChkCopyPemeriksaanDR.isSelected() == true) {
                    kdptg1.setText(Sequel.cariIsi("select nip from hak_akses_unit where kode_unit='" + polinya + "'"));
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                } else {
                    kdptg1.setText(akses.getkode());
                    TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
                    TCari.setText(TNoRM.getText());
                }
                tampilPemeriksaanPetugas();
            }
        }
    }//GEN-LAST:event_BtnPemeriksaanPRActionPerformed

    private void BtnPRMRJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPRMRJActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(3);
            kdpoli.setText("");
            TPoli.setText("");
            tampilPRMRJ();
        }
    }//GEN-LAST:event_BtnPRMRJActionPerformed

    private void BtnCatatanResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCatatanResepActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(4);
            kdpoli.setText("");
            TPoli.setText("");
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    TCari.setText(TNoRM.getText());
                    TResepObat.setText("");
                    TCariObat.setText("");
                    tampilResepObat();
                    ChkInput1.setSelected(false);
                    isFormRiwayatObat();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        TCari.setText(TNoRM.getText());
                        TResepObat.setText("");
                        TCariObat.setText("");
                        tampilResepObat();
                        ChkInput1.setSelected(false);
                        isFormRiwayatObat();
                    }
                }
            } else {
                TCari.setText(TNoRM.getText());
                TResepObat.setText("");
                TCariObat.setText("");
                tampilResepObat();
                ChkInput1.setSelected(false);
                isFormRiwayatObat();
            }
        }
    }//GEN-LAST:event_BtnCatatanResepActionPerformed

    private void BtnRingkasanRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRingkasanRiwayatActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(5);
            if (polinya.equals("IRM")) {
                if (akses.getkode().equals("Admin Utama") || akses.getkode().equals("D0000029")) {
                    TCari.setText(TNoRM.getText());
                    tampilRingkasan();
                } else {
                    if (cekPilihanRehab == 0) {
                        TabRawat.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(rootPane, "Silahkan tentukan pilihan jenis rehabilitasi mediknya dulu...!!!!");
                    } else if (cekPilihanRehab > 0) {
                        TCari.setText(TNoRM.getText());
                        tampilRingkasan();
                    }
                }
            } else {
                kdpoli.setText(polinya);
                TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
                TCari.setText(TNoRM.getText());
                tampilRingkasan();
            }
        }
    }//GEN-LAST:event_BtnRingkasanRiwayatActionPerformed

    private void BtnRingkasanPulangRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRingkasanPulangRanapActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(6);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilRingkasanPulangRanap();
        }
    }//GEN-LAST:event_BtnRingkasanPulangRanapActionPerformed

    private void BtnPenilaianKeperawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenilaianKeperawatanActionPerformed
        if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(7);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAssesmen();
        }
    }//GEN-LAST:event_BtnPenilaianKeperawatanActionPerformed

    private void BtnMenjawabRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnMenjawabRujukanActionPerformed
        if(TPasien.getText().trim().equals("")||TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(8);
            kdpoli.setText("");
            TPoli.setText("");
            emptTeksRujukanInternal();
            tampilRujukanInternal();            
            if (tabModeRujukan.getRowCount() != 0) {
                BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
            } else {
                BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
            }
        }
    }//GEN-LAST:event_BtnMenjawabRujukanActionPerformed

    private void BtnPenilaianAwalMedisTHTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenilaianAwalMedisTHTActionPerformed
        if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(9);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAwalMedisTHT();
        }
    }//GEN-LAST:event_BtnPenilaianAwalMedisTHTActionPerformed

    private void BtnHasilPemeriksaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilPemeriksaanRadActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(10);
            kdpoli.setText("");
            TPoli.setText("");
            tampilHasilRadiologi();
        }
    }//GEN-LAST:event_BtnHasilPemeriksaanRadActionPerformed

    private void BtnPenilaianAwalMedisMataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenilaianAwalMedisMataActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(11);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilAwalMedisMata();
        }
    }//GEN-LAST:event_BtnPenilaianAwalMedisMataActionPerformed

    private void BtnHasilPemeriksaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilPemeriksaanLabActionPerformed
        if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            TabRawat.setSelectedIndex(12);
            kdpoli.setText("");
            TPoli.setText("");
            TCari.setText(TNoRM.getText());
            tampilHasilLaboratorium();
        }
    }//GEN-LAST:event_BtnHasilPemeriksaanLabActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRawatJalan dialog = new DlgRawatJalan(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField BagianRS;
    private javax.swing.JTextField Bhp;
    public widget.Button BtnBaru;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCariRekamMedis;
    private widget.Button BtnCatatanResep;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnCloseIn9;
    private widget.Button BtnCopyResep;
    private widget.Button BtnCopyResepTerakhir;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEditObat;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHasilPemeriksaanLab;
    private widget.Button BtnHasilPemeriksaanRad;
    private widget.Button BtnKeluar;
    private widget.Button BtnMenjawabRujukan;
    private widget.Button BtnPRMRJ;
    private widget.Button BtnPemeriksaanDR;
    private widget.Button BtnPemeriksaanPR;
    private widget.Button BtnPenilaianAwalMedisMata;
    private widget.Button BtnPenilaianAwalMedisTHT;
    private widget.Button BtnPenilaianKeperawatan;
    private widget.Button BtnPrinResep;
    private widget.Button BtnPrint;
    private widget.Button BtnRM;
    private widget.Button BtnResep;
    private widget.Button BtnRingkasanPulangRanap;
    private widget.Button BtnRingkasanRiwayat;
    private widget.Button BtnSeekDokter2;
    private widget.Button BtnSeekPetugas1;
    private widget.Button BtnSeekPetugas2;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan7;
    private widget.Button BtnSimpanObat;
    private widget.Button BtnTarifTindakan;
    private widget.Button BtnUnit;
    public widget.CekBox ChkAccor;
    private widget.CekBox ChkCopyPemeriksaanDR;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkInput1;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkPemeriksaan;
    private widget.CekBox ChkPoli;
    private widget.CekBox ChkPoli1;
    private widget.CekBox ChkPrmrj;
    private widget.CekBox ChkTanggal;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.TextBox DiagnosaRJdokter;
    private widget.TextBox DiagnosaRJpetugas;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.PanelBiasa FormMenu;
    private javax.swing.JTextField JmDokter;
    private javax.swing.JTextField JmPerawat;
    private javax.swing.JTextField KSO;
    private widget.TextBox KdDok;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML1;
    private widget.editorpane LoadHTML2;
    private widget.editorpane LoadHTML3;
    private widget.editorpane LoadHTML4;
    private widget.editorpane LoadHTML5;
    private widget.editorpane LoadHTML6;
    private widget.editorpane LoadHTML7;
    private javax.swing.JTextField Menejemen;
    private javax.swing.JMenuItem MnAsesmenMedikObstetri;
    private javax.swing.JMenuItem MnCetakJawabanRujukan;
    private javax.swing.JMenu MnContengResep;
    private javax.swing.JMenuItem MnDataParu;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenu MnHapusPenunjang;
    private javax.swing.JMenuItem MnHapusPeriksaRadiologi;
    private javax.swing.JMenuItem MnHapusPermintaanLab;
    private javax.swing.JMenuItem MnPenilaianAwalKeperawatanKebidanan;
    private javax.swing.JMenuItem MnPenilaianAwalKeperawatanRalan;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanGeriatri;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanMata;
    private javax.swing.JMenuItem MnPenilaianAwalMedisRalanTHT;
    private javax.swing.JMenuItem MnPenilaianTambahanGeriatri;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenuItem MnPermintaanLab;
    private javax.swing.JMenu MnPermintaanPenunjang;
    private javax.swing.JMenuItem MnPrinPRMRJ;
    private javax.swing.JMenuItem MnRehabMedik;
    private javax.swing.JMenu MnRekamMedis;
    private javax.swing.JMenuItem MnRiwayatPerawatanICareNoKartu;
    private javax.swing.JMenuItem MnRujukanInternalPoli;
    private javax.swing.JMenuItem MnSemuanya;
    private javax.swing.JMenuItem MnStatusPasienAllKunjungan;
    private javax.swing.JMenuItem MnStatusPasienPerKunjungan;
    private javax.swing.JMenuItem MnSuratPengantarRanap;
    private javax.swing.JMenuItem MnSuratTindakanDokter;
    private widget.TextBox OlehDokter;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelRiwayatObat;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll13;
    private widget.ScrollPane Scroll14;
    private widget.ScrollPane Scroll15;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll21;
    private widget.ScrollPane Scroll22;
    private widget.ScrollPane Scroll23;
    private widget.ScrollPane Scroll24;
    private widget.ScrollPane Scroll25;
    private widget.ScrollPane Scroll26;
    private widget.ScrollPane Scroll27;
    private widget.ScrollPane Scroll28;
    private widget.ScrollPane Scroll29;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll30;
    private widget.ScrollPane Scroll31;
    private widget.ScrollPane Scroll32;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll34;
    private widget.ScrollPane Scroll35;
    private widget.ScrollPane Scroll36;
    private widget.ScrollPane Scroll37;
    private widget.ScrollPane Scroll38;
    private widget.ScrollPane Scroll39;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll40;
    private widget.ScrollPane Scroll41;
    private widget.ScrollPane Scroll42;
    private widget.ScrollPane Scroll43;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.ScrollPane Scroll46;
    private widget.ScrollPane Scroll47;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ScrollPane ScrollMenu;
    private widget.TextArea TAlergi;
    private widget.TextArea TAlergi1;
    private widget.TextBox TBerat;
    private widget.TextBox TBerat1;
    private widget.TextBox TCari;
    private widget.TextBox TCariObat;
    private widget.TextBox TCariPasien;
    private widget.TextBox TDariPoli;
    private widget.TextArea TDiagnosa;
    private widget.TextArea TDiagnosa1;
    private widget.TextBox TDokter;
    private widget.TextBox TGCS;
    private widget.TextBox TGCS1;
    private widget.TextBox TIdObat;
    private widget.TextArea TJwbnRujukan;
    private widget.TextBox TKdPrw;
    private widget.TextArea TKeluhan;
    private widget.TextArea TKeluhan1;
    private widget.TextArea TKetAsalRujukan;
    private widget.TextBox TNadi;
    private widget.TextBox TNadi1;
    private widget.TextBox TNmPrw;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw2;
    private widget.TextBox TPasien;
    private widget.TextArea TPemeriksaan;
    private widget.TextArea TPemeriksaan1;
    private widget.TextBox TPerawat;
    private widget.TextBox TPerawat1;
    private widget.TextBox TPoli;
    private widget.TextBox TResepObat;
    private widget.TextBox TRespirasi;
    private widget.TextBox TRespirasi1;
    private widget.TextArea TRincianTindakan;
    private widget.TextArea TRincianTindakan1;
    private widget.TextArea TRncanaFolow;
    private widget.TextArea TRncanaFolow1;
    private widget.TextBox TSuhu;
    private widget.TextBox TSuhu1;
    private widget.TextBox TTensi;
    private widget.TextBox TTensi1;
    private widget.TextArea TTerapi;
    private widget.TextArea TTerapi1;
    private widget.TextBox TTinggi;
    private widget.TextBox TTinggi1;
    private javax.swing.JTextField TTnd;
    private javax.swing.JTabbedPane TabPemeriksaanDokter;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglKunRwt;
    private widget.TextBox Tnorawat;
    private widget.TextBox Tspo2;
    private widget.TextBox Tspo21;
    public javax.swing.JDialog WindowDataParu;
    private javax.swing.JDialog WindowRehabMedik;
    public javax.swing.JDialog WindowRiwayatKunjungan;
    private javax.swing.JDialog WindowRiwayatResep;
    private widget.RadioButton bpjs;
    private widget.Button btnPasien;
    private widget.Button btnTindakan;
    private widget.ComboBox cmbConteng;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbImun;
    private widget.ComboBox cmbImun1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbStatus;
    private widget.TextBox dokterMenjawab;
    private widget.RadioButton dot;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame20;
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
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel4;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel5;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
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
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private widget.TextBox kdpoli;
    private widget.TextBox kdptg;
    private widget.TextBox kdptg1;
    private widget.TextArea ket_paru;
    private widget.RadioButton konsultasi;
    private widget.Label label_rehab;
    private widget.Label label_tot_pemeriksaan;
    private widget.RadioButton lain;
    private widget.TextBox nm_pasien;
    private widget.TextBox nm_poli;
    private widget.TextBox noIdObat;
    private widget.TextBox noIdObatCopy;
    private widget.TextBox no_rawat;
    private widget.TextBox no_rm;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi6;
    private widget.panelisi panelisi8;
    private widget.TextBox pasiendipilih;
    private widget.TextBox poliMenjawab;
    private widget.RadioButton puskes;
    private widget.RadioButton serangan;
    private widget.Table tbItemObat;
    private widget.Table tbItemResep;
    private widget.Table tbObat;
    private widget.Table tbPemberianResep;
    private widget.Table tbPemeriksaanDr;
    private widget.Table tbPemeriksaanPr;
    private widget.Table tbPeriksaLab;
    private widget.Table tbPeriksaRad;
    private widget.Table tbRawatDrPr;
    private widget.Table tbResepObat;
    private widget.Table tbRiwayatKunj;
    private widget.Table tbRujukan;
    private widget.Table tbTglBeriObat;
    private widget.TextBox tglDirujuk;
    private widget.TextBox tgl_reg;
    private widget.TextBox unitRJ;
    // End of variables declaration//GEN-END:variables

    public void tampilDrPr() {
        Valid.tabelKosong(tabModeDrPr);
        try {
            ps3 = koneksi.prepareStatement("select rawat_jl_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "jns_perawatan.nm_perawatan,rawat_jl_drpr.kd_dokter,dokter.nm_dokter,"
                    + "rawat_jl_drpr.nip,petugas.nama,rawat_jl_drpr.biaya_rawat,rawat_jl_drpr.tgl_perawatan, rawat_jl_drpr.jam_rawat,rawat_jl_drpr.kd_jenis_prw  "
                    + "from pasien inner join reg_periksa inner join petugas "
                    + "inner join jns_perawatan inner join dokter inner join rawat_jl_drpr "
                    + "on rawat_jl_drpr.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                    + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter "
                    + "and rawat_jl_drpr.nip=petugas.nip "
                    + "where reg_periksa.tgl_registrasi between ? and ? and rawat_jl_drpr.no_rawat like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and jns_perawatan.nm_perawatan like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and rawat_jl_drpr.kd_dokter like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and rawat_jl_drpr.nip like ? or "
                    + " reg_periksa.tgl_registrasi between ? and ? and petugas.nama like ? order by rawat_jl_drpr.no_rawat desc");
            try {
                ps3.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(3, "%" + TCari.getText().trim() + "%");
                ps3.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(6, "%" + TCari.getText().trim() + "%");
                ps3.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(9, "%" + TCari.getText().trim() + "%");
                ps3.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(12, "%" + TCari.getText().trim() + "%");
                ps3.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(15, "%" + TCari.getText().trim() + "%");
                ps3.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(18, "%" + TCari.getText().trim() + "%");
                ps3.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(21, "%" + TCari.getText().trim() + "%");
                ps3.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps3.executeQuery();
                while (rs.next()) {
                    Object[] data = {false,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        Valid.SetAngka(rs.getDouble(9)),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString("kd_jenis_prw")};
                    tabModeDrPr.addRow(data);
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModeDrPr.getRowCount());
    }

    private void getDataDrPr() {
        if (tbRawatDrPr.getSelectedRow() != -1) {
            TNoRw.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 1).toString());
            TNoRM.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 2).toString());
//            TPasien.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 3).toString());
            isPsien();
            KdDok.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5).toString());
            TDokter.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 6).toString());
            kdptg.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7).toString());
            TPerawat.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 8).toString());
            cmbJam.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString());
            Sequel.cariIsi("select kd_jenis_prw from jns_perawatan where nm_perawatan=? ", TKdPrw, tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 4).toString());
            isJns();
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select concat(nm_pasien,' (Tgl. Lahir : ',date_format(tgl_lahir,'%d-%m-%Y'),', Jns. Kel. : ',if(jk='L','Laki-laki','Perempuan'),')') pasien "
                + "from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void isJns() {
        Sequel.cariIsi("select nm_perawatan from jns_perawatan where kd_jenis_prw=? ", TNmPrw, TKdPrw.getText());
        Sequel.cariIsi("select bhp from jns_perawatan where kd_jenis_prw=? ", Bhp, TKdPrw.getText());
        Sequel.cariIsi("select material from jns_perawatan where kd_jenis_prw=? ", BagianRS, TKdPrw.getText());
        Sequel.cariIsi("select tarif_tindakandr from jns_perawatan where kd_jenis_prw=? ", JmDokter, TKdPrw.getText());
        Sequel.cariIsi("select tarif_tindakanpr from jns_perawatan where kd_jenis_prw=? ", JmPerawat, TKdPrw.getText());
        Sequel.cariIsi("select kso from jns_perawatan where kd_jenis_prw=? ", KSO, TKdPrw.getText());
        Sequel.cariIsi("select menejemen from jns_perawatan where kd_jenis_prw=? ", Menejemen, TKdPrw.getText());
        if (TabRawat.getSelectedIndex() == 0) {
            Sequel.cariIsi("select total_byrdr from jns_perawatan where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        } else if (TabRawat.getSelectedIndex() == 1) {
            Sequel.cariIsi("select total_byrpr from jns_perawatan where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        } else if (TabRawat.getSelectedIndex() == 2) {
            Sequel.cariIsi("select total_byrdrpr from jns_perawatan where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        }
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2) {
        PoliKhusus = "";
        polinya = "";
        cekPilihanRehab = 0;
        cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + norwt + "'");
        PoliKhusus = Sequel.cariIsi("SELECT ifnull(kode_unit,'-') kode_unit FROM hak_akses_unit WHERE nip='" + akses.getkode() + "'");
        
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        KdDok.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", norwt));
        polinya = Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + norwt + "'");
//        kdpoli.setText(Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + norwt + "'"));
//        TPoli.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
        kdpoli.setText("");
        TPoli.setText("");
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
        ChkInput.setSelected(true);
        ChkInput1.setSelected(false);
        isForm();
        isFormRiwayatObat();
        Sequel.cariIsi("select tinggi_badan from pasien where no_rkm_medis = '" + TNoRM.getText() + "'", TTinggi);
        Valid.SetTgl(DTPTgl, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        
        if (cekPilihanRehab == 0) {
            label_rehab.setVisible(false);
            label_rehab.setText("");
        } else if (cekPilihanRehab > 0) {
            label_rehab.setVisible(true);
            label_rehab.setText("Jenis Rehabilitasi Medik : " + Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + norwt + "'"));
        }
        
        emptText();
        Valid.tabelKosong(tabModePemeriksaanDr);
        
        //cek rujukan internal poliklinik
        emptTeksRujukanInternal();
        tampilRujukanInternal();

        if (tabModeRujukan.getRowCount() != 0) {
            TabRawat.setBackgroundAt(8, Color.ORANGE);
            BtnMenjawabRujukan.setGlassColor(Color.ORANGE);
        } else {
            TabRawat.setBackgroundAt(8, TabRawat.getBackground());
            BtnMenjawabRujukan.setGlassColor(TabRawat.getBackground());
        }
    }
    
    private void cekRehabMedik() {
        cekPilihanRehab = 0;
        cekPilihanRehab = Sequel.cariInteger("select count(-1) from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'");

        if (cekPilihanRehab == 0) {
            label_rehab.setVisible(false);
            label_rehab.setText("");
        } else if (cekPilihanRehab > 0) {
            label_rehab.setVisible(true);
            label_rehab.setText("Jenis Rehabilitasi Medik : " + Sequel.cariIsi("select jns_rehabmedik from data_rehab_medik where no_rawat='" + TNoRw.getText() + "'"));
        }
    }       

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 82));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    private void isFormRiwayatObat() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObat.setVisible(true);
            ChkInput1.setVisible(true);
            ChkPoli1.setSelected(true);
            
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Hanya dipoliklinik ini");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua Poliklinik");
                tampilTglBeriObat();
            }
        } else if (ChkInput1.isSelected() == false) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 20));
            PanelRiwayatObat.setVisible(false);
            ChkInput1.setVisible(true);
            Valid.tabelKosong(tabModeTglBeriObat);
            Valid.tabelKosong(tabModeRiwItemObat);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.gettindakan_ralan());
        BtnHapus.setEnabled(akses.gettindakan_ralan());
        BtnEdit.setEnabled(akses.gettindakan_ralan());
        BtnPrint.setEnabled(akses.gettindakan_ralan());
        MnDiagnosa.setEnabled(akses.getdiagnosa_pasien());
        MnSuratTindakanDokter.setEnabled(akses.getdiagnosa_pasien());
        MnRujukanInternalPoli.setEnabled(akses.getrujukan_poli_internal());
        MnContengResep.setEnabled(akses.getrujukan_poli_internal());
        BtnResep.setEnabled(akses.getrujukan_poli_internal());
        BtnCopyResepTerakhir.setEnabled(akses.getrujukan_poli_internal());
        BtnSimpanObat.setEnabled(akses.getrujukan_poli_internal());
        BtnEditObat.setEnabled(akses.getrujukan_poli_internal());  
        ChkCopyPemeriksaanDR.setEnabled(akses.getcopy_pemeriksaan_dr_kepetugas());

        MnPenilaianAwalKeperawatanRalan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        MnAsesmenMedikObstetri.setEnabled(akses.getpenilaian_awal_medis_ralan_kebidanan());
        MnPenilaianAwalKeperawatanKebidanan.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        MnPenilaianAwalMedisRalanTHT.setEnabled(akses.getpenilaian_awal_medis_ralan_tht());  
        MnPenilaianAwalMedisRalanGeriatri.setEnabled(akses.getpenilaian_pasien_geriatri()); 
        MnPenilaianTambahanGeriatri.setEnabled(akses.getpenilaian_pasien_geriatri()); 
        MnPenilaianAwalMedisRalanMata.setEnabled(akses.getpenilaian_awal_medis_ralan_mata());        
        
        MnStatusPasienPerKunjungan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        MnStatusPasienAllKunjungan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());

        MnPermintaanLab.setEnabled(akses.getpermintaan_lab());
        MnPeriksaRadiologi.setEnabled(akses.getpermintaan_radiologi());
        MnHapusPermintaanLab.setEnabled(akses.getpermintaan_lab());
        MnHapusPeriksaRadiologi.setEnabled(akses.getpermintaan_radiologi());
        
        kdptg.setText(validasi.getKdPtgs());
        TPerawat.setText(validasi.getNmPtgs());

        if ((PoliKhusus.equals("PAR") && polinya.equals("PAR")) || akses.getkode().equals("Admin Utama")) {
            MnDataParu.setEnabled(true);
        } else {
            MnDataParu.setEnabled(false);
        }
    }

    private void tampilPemeriksaanDokter() {
        Valid.tabelKosong(tabModePemeriksaanDr);
        try {
            ps4 = koneksi.prepareStatement("select pemeriksaan_ralan.no_rawat,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "pemeriksaan_ralan.tgl_perawatan,pemeriksaan_ralan.jam_rawat,pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi, "
                    + "pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,pemeriksaan_ralan.tinggi, "
                    + "pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                    + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,pemeriksaan_ralan.imun_ke, "
                    + "pemeriksaan_ralan.diagnosa,pemeriksaan_ralan.rencana_follow_up, pemeriksaan_ralan.rincian_tindakan, pemeriksaan_ralan.terapi, "
                    + "pemeriksaan_ralan.stts_prmrj, ifnull(pemeriksaan_ralan.spo2,'') spo2 from pasien inner join reg_periksa inner join pemeriksaan_ralan "
                    + "on pemeriksaan_ralan.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis inner join dokter on dokter.kd_dokter = pemeriksaan_ralan.kd_dokter where  "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ralan.no_rawat like ? or "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ralan.alergi like ? or "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ralan.keluhan like ? or "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ralan.terapi like ? or "
                    + "pemeriksaan_ralan.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ralan.pemeriksaan like ? "
                    + "order by pemeriksaan_ralan.no_rawat desc");
            try {
                ps4.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(3, "%" + TCariPasien.getText() + "%");
                ps4.setString(4, "%" + TCari.getText().trim() + "%");
                ps4.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(7, "%" + TCariPasien.getText() + "%");
                ps4.setString(8, "%" + TCari.getText().trim() + "%");
                ps4.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(11, "%" + TCariPasien.getText() + "%");
                ps4.setString(12, "%" + TCari.getText().trim() + "%");
                ps4.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(15, "%" + TCariPasien.getText() + "%");
                ps4.setString(16, "%" + TCari.getText().trim() + "%");
                ps4.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(19, "%" + TCariPasien.getText() + "%");
                ps4.setString(20, "%" + TCari.getText().trim() + "%");
                ps4.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(23, "%" + TCariPasien.getText() + "%");
                ps4.setString(24, "%" + TCari.getText().trim() + "%");
                ps4.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps4.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps4.setString(27, "%" + TCariPasien.getText() + "%");
                ps4.setString(28, "%" + TCari.getText().trim() + "%");
                rs = ps4.executeQuery();
                while (rs.next()) {
                    tabModePemeriksaanDr.addRow(new Object[]{false,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getString(21),
                        rs.getString(22).replaceAll("0", "Tidak").replaceAll("1", "Ya"),
                        rs.getString(23)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModePemeriksaanDr.getRowCount());
    }

    private void getDataPemeriksaan() {
        prmrj = "";
        if (tbPemeriksaanDr.getSelectedRow() != -1) {
//            TNoRw.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 1).toString());
            TNoRw2.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 3).toString());
//            TPasien.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 3).toString());
            isPsien();
            TSuhu.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 7).toString());
            TTensi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 8).toString());
            TNadi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 9).toString());
            TRespirasi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 10).toString());
            TTinggi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 11).toString());
            TBerat.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 12).toString());
            TGCS.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 13).toString());
            TKeluhan.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 14).toString());
            TPemeriksaan.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 15).toString());
            TAlergi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 16).toString());
            cmbImun.setSelectedItem(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 17).toString());
            TRncanaFolow.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 19).toString());
            cmbImun.setSelectedItem(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 16).toString());
            cmbJam.setSelectedItem(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 6).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 6).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 6).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 5).toString());
            TDiagnosa.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 18).toString());
            TRincianTindakan.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 20).toString());
            TTerapi.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 21).toString());
            prmrj = tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 22).toString();
            Tspo2.setText(tbPemeriksaanDr.getValueAt(tbPemeriksaanDr.getSelectedRow(), 23).toString());
            
            if (prmrj.equals("Ya")) {
                ChkPrmrj.setSelected(true);
            } else if (prmrj.equals("Tidak")) {
                ChkPrmrj.setSelected(false);
            }
        }
    }

    private void getDataMintaLab1() {
        noiD = "";
        noMinta = "";
        diperiksa = "";
        if (tbPeriksaLab.getSelectedRow() != -1) {
            diperiksa = tbPeriksaLab.getValueAt(tbPeriksaLab.getSelectedRow(), 4).toString();
            noiD = tbPeriksaLab.getValueAt(tbPeriksaLab.getSelectedRow(), 5).toString();
            noMinta = tbPeriksaLab.getValueAt(tbPeriksaLab.getSelectedRow(), 6).toString();
        }
    }

    private void getDataMintaRad1() {
        noordeR1 = "";
        waktuSimpanRad = "";

        if (tbPeriksaRad.getSelectedRow() != -1) {
            noordeR1 = tbPeriksaRad.getValueAt(tbPeriksaRad.getSelectedRow(), 5).toString();
            waktuSimpanRad = tbPeriksaRad.getValueAt(tbPeriksaRad.getSelectedRow(), 7).toString();
        }
    }

    private void getDataPemeriksaanPr() {
        if (tbPemeriksaanPr.getSelectedRow() != -1) {
//            TNoRw.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 1).toString());
            TNoRw2.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 3).toString());
//            TPasien.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 3).toString());
            isPsien();
            TSuhu1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 7).toString());
            TTensi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 8).toString());
            TNadi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 9).toString());
            TRespirasi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 10).toString());
            TTinggi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 11).toString());
            TBerat1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 12).toString());
            TGCS1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 13).toString());
            TKeluhan1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 14).toString());
            TPemeriksaan1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 15).toString());
            TAlergi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 16).toString());
            TRncanaFolow1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 19).toString());
            kdptg1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 20).toString());
            TPerawat1.setText(Sequel.cariIsi("select nama from petugas where nip='" + kdptg1.getText() + "'"));
            cmbImun1.setSelectedItem(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 17).toString());
            cmbJam.setSelectedItem(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 6).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 6).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 6).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 5).toString());
            TDiagnosa1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 18).toString());
            TRincianTindakan1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 21).toString());
            TTerapi1.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 22).toString());
            Tspo21.setText(tbPemeriksaanPr.getValueAt(tbPemeriksaanPr.getSelectedRow(), 24).toString());
        }
    }

    private void tampilResepObat() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            ps5 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, c.noID from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat  "
                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where  "
                    + "c.tgl_perawatan between ? and ? and c.no_rawat like ? or "
                    + "c.tgl_perawatan between ? and ? and c.nama_obat like ? or "
                    + "c.tgl_perawatan between ? and ? and r.no_rkm_medis like ? "
                    + "order by c.noId");
            try {
                ps5.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps5.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps5.setString(3, "%" + TCari.getText().trim() + "%");
                ps5.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps5.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps5.setString(6, "%" + TCari.getText().trim() + "%");
                ps5.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps5.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps5.setString(9, "%" + TCari.getText().trim() + "%");
                rs = ps5.executeQuery();
                while (rs.next()) {
                    tabModeResepObat.addRow(new Object[]{false,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModeResepObat.getRowCount());
    }

    private void tampilRingkasan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            if (!kdpoli.getText().equals("")) {
                a = " and reg_periksa.kd_poli='" + kdpoli.getText() + "'";
            } else {
                a = "";
            }
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,"
                                    + "penjab.png_jawab from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where "
                                    + "stts<>'Batal' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and reg_periksa.status_lanjut='Ralan' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'" + a).executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select * from (select a.no_reg, a.no_rawat,date_format(a.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(a.jam_reg,'%h:%i %p') jam_reg,a.kd_dokter,a.nm_dokter,"
                                    + "a.nm_poli,a.p_jawab,a.almt_pj,a.hubunganpj,a.biaya_reg,if(a.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,a.png_jawab, "
                                    + "a.tgl_registrasi tglReg, a.jam_reg jamReg from (select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where "
                                    + "stts<>'Batal' and reg_periksa.status_lanjut='Ralan' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "'" + a + ") as a "
                                    + "ORDER BY a.tgl_registrasi desc, a.jam_reg desc limit 3) as a order by a.tglReg, a.jamReg").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Kunjungan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"                           
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan catatan diagnosa
//                            try {
//                                rsDiag=koneksi.prepareStatement(
//                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "+                                        
//                                        "where no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rsDiag.next()){
//                                    htmlContent.append(
//                                            "<tr class='isi'>"+ 
//                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Diagnosa</td>"+
//                                "<td valign='top' width='1%' align='center'>:</td>"+
//                                "<td valign='top' width='79%'>"+rsDiag.getString("diagnosa")+"</td>"+
//                                      "</tr>");
//                                }                                    
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rsDiag!=null){
//                                    rsDiag.close();
//                                }
//                            }

                            //menampilkan rencana follow up dokter
                            try {
                                rsDiag = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }
                            
                            //menampilkan rencana follow up perawat/bidan
                            try {
                                rsDiag1 = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rencana Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                rsObat = koneksi.prepareStatement(
                                        "Select nama_obat,status from catatan_resep "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Catatan Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Nama Obat</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Status</td></tr>");

                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                rs3 = koneksi.prepareStatement("select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status "
                                        + "from diagnosa_pasien inner join penyakit "
                                        + "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                                        + "where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs3 = koneksi.prepareStatement("select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                                        + "from prosedur_pasien inner join icd9 on prosedur_pasien.kode=icd9.kode "
                                        + "where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prosedur Tindakan/ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-9</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Deskripsi Tindakan/Prosedur ICD-9</td>"
                                            + "</tr>");

                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td>"
                                                + "</tr>");

                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"
                                        + "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                                        + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, "
                                        + "ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan.terapi,'-') terapi, "
                                        + "ifnull(pemeriksaan_ralan.spo2,'-') spo2 from pemeriksaan_ralan where "
                                        + "pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='7%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan rujukan internal poliklinik
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT ifnull(pl.nm_poli,'-') ke_poli, ifnull(DATE_FORMAT(ri.tgl_rencana_dirujuk,'%d-%m-%Y'),'-') tgl_dirujuk, "
                                        + "ifnull(ri.keterangan,'-') keterangan, ifnull(ri.keterangan_balasan,'-') jwbn, ifnull(d.nm_dokter,'') drMenjawab FROM rujukan_internal_poli ri "
                                        + "INNER JOIN poliklinik pl on pl.kd_poli=ri.kd_poli_pembalas left join dokter d on d.kd_dokter=ri.kd_dokter_pembalas "
                                        + "WHERE ri.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirujuk Internal Ke</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik/Inst.</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Renc. Dirujuk</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Isi/Pesan Rujukan</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Balasan/Jawaban Rujukan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"                                                
                                                + "<td valign='top'>" + rs3.getString("ke_poli") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_dirujuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jwbn").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br>Ttd.<br>" + rs3.getString("drMenjawab") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,"
                                        + "pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, "
                                        + "pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa, "
                                        + "ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, "
                                        + "ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi, ifnull(pemeriksaan_ralan_petugas.spo2,'-') spo2 from pemeriksaan_ralan_petugas where "
                                        + "pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='2%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='14%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }                            

                            //menampilkan riwayat pemeriksaan ranap
//                            try {
//                                rs3=koneksi.prepareStatement(
//                                        "select pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi," +
//                                        "pemeriksaan_ranap.tinggi,pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan," +
//                                        "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat from pemeriksaan_ranap where pemeriksaan_ranap.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){
//                                    htmlContent.append(
//                                      "<tr class='isi'>"+ 
//                                        "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Rawat Inap</td>"+
//                                        "<td valign='top' width='1%' align='center'>:</td>"+
//                                        "<td valign='top' width='79%'>"+
//                                          "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                             "<tr align='center'>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Suhu(C)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tensi</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>Berat(Kg)</td>"+
//                                                "<td valign='top' width='5%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"+
//                                                "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"+
//                                                "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"+
//                                                "<td valign='top' width='10%' bgcolor='#f8fdf3'>Alergi</td>"+
//                                             "</tr>"
//                                    );
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("suhu_tubuh")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tensi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nadi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("respirasi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tinggi")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("berat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("gcs")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("keluhan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("pemeriksaan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("alergi")+"</td>"+
//                                             "</tr>");                                        
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                          "</table>"+
//                                        "</td>"+
//                                      "</tr>");
//                                }
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }

                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement("SELECT no_lab FROM lis_reg WHERE no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr "
                                                        + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab LEFT JOIN lis_hasil_data_pasien lhdp on lhdp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, "
                                                        + "lhp.sub_kategori_pemeriksaan_nama desc, lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, "
                                                                + "lhp.satuan, lhp.flag_kode FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                                + "LEFT JOIN lis_hasil_data_pasien lhdp ON lhdp.no_lab=lr.no_lab WHERE lr.no_lab='" + rsLISMaster.getString("no_lab") + "' and "
                                                                + "lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and "
                                                                + "lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY lhp.pemeriksaan_nama "
                                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut, lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }
                            
                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"                                            
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ralan
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ralan' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan dokter ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,"+
//                                        "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"+
//                                        "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "+
//                                        "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "+
//                                        "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
//                                        "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_dokter")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
                            //tindakan paramedis ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,"+
//                                        "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"+
//                                        "petugas.nama,rawat_inap_pr.biaya_rawat "+
//                                        "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "+
//                                        "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "+
//                                        "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nama")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }      
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
//                            
//                            //tindakan paramedis dan dokter ranap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.kd_jenis_prw,"+
//                                        "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "+
//                                        "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "+
//                                        "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "+
//                                        "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+            
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Kode</td>"+
//                                          "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"+
//                                          "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"+
//                                          "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_perawatan")+" "+rs3.getString("jam_rawat")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_jenis_prw")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_perawatan")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nm_dokter")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("nama")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("biaya_rawat"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }
//                            
//                            //kamar inap
//                            try{
//                                rs3=koneksi.prepareStatement(
//                                        "select kamar_inap.kd_kamar,bangsal.nm_bangsal,kamar_inap.tgl_masuk, kamar_inap.tgl_keluar, "+
//                                        "kamar_inap.stts_pulang,kamar_inap.lama,kamar_inap.jam_masuk,kamar_inap.jam_keluar,"+
//                                        "kamar_inap.ttl_biaya from kamar_inap inner join bangsal inner join kamar "+
//                                        "on kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  "+
//                                        "where kamar_inap.no_rawat='"+rs2.getString("no_rawat")+"'").executeQuery();
//                                if(rs3.next()){                                    
//                                    htmlContent.append(  
//                                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
//                                        "<tr><td valign='top' colspan='5'>Penggunaan Kamar</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"+            
//                                        "<tr align='center'>"+
//                                          "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal Masuk</td>"+
//                                          "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggak Keluar</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Lama Inap</td>"+
//                                          "<td valign='top' width='35%' bgcolor='#f8fdf3'>Kamar</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Status</td>"+
//                                          "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"+
//                                        "</tr>");
//                                    rs3.beforeFirst();
//                                    w=1;
//                                    while(rs3.next()){
//                                        htmlContent.append(
//                                             "<tr>"+
//                                                "<td valign='top' align='center'>"+w+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_masuk")+" "+rs3.getString("jam_masuk")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("tgl_keluar")+" "+rs3.getString("jam_keluar")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("lama")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("kd_kamar")+", "+rs3.getString("nm_bangsal")+"</td>"+
//                                                "<td valign='top'>"+rs3.getString("stts_pulang")+"</td>"+
//                                                "<td valign='top' align='right'>"+Valid.SetAngka(rs3.getDouble("ttl_biaya"))+"</td>"+
//                                             "</tr>"); 
//                                        w++;
//                                    }
//                                    htmlContent.append(
//                                      "</table>");
//                                }                                
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : "+e);
//                            } finally{
//                                if(rs3!=null){
//                                    rs3.close();
//                                }
//                            }

                            //operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,"
                                        + "operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, "
                                        + "operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,"
                                        + "operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, "
                                        + "operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, "
                                        + "operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, "
                                        + "operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, "
                                        + "operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,"
                                        + "operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,"
                                        + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
                                        + "(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+"
                                        + "operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+"
                                        + "operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+"
                                        + "operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+"
                                        + "operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
                                        + "operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi "
                                        + "on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "' order by operasi.tgl_operasi").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,periksa_radiologi.kd_jenis_prw, "
                                        + "jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter "
                                        + "from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter "
                                        + "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter "
                                        + "and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by periksa_radiologi.tgl_periksa,periksa_radiologi.jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Pemeriksaan</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "' order by tgl_periksa,jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total "
                                        + "FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat "
                                        + "INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL "
                                        + "SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total "
                                        + "FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw "
                                        + "LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "' order by detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(beri_obat_operasi.tanggal,'%d-%m-%Y') tanggal,beri_obat_operasi.kd_obat,beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, "
                                        + "(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void getDataResepObat() {
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRw.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            cmbJam.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 2).toString());
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    public void setNoRm(String norwt, Date tgl1, Date tgl2, String kodedokter, String namadokter) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari1.setDate(tgl1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien();
        ChkInput.setSelected(true);
        isForm();
        KdDok.setText(kodedokter);
        TDokter.setText(namadokter);
    }

    public void unitRawatJalan(String unit) {
        unitRJ.setText(unit);
    }

    public void SetPoli(String KodePoli) {
        this.kode_poli = KodePoli;
    }

    public void fokus() {
        DiagnosaRJpetugas.setText(Sequel.cariIsi("select diagnosa from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "' "));

        if (unitRJ.getText().equals("IGD")) {
            if (DiagnosaRJpetugas.getText().equals("")) {
                TabRawat.setSelectedIndex(2);
                tampilPemeriksaanPetugas();
                TDiagnosa1.requestFocus();
            } else {
                TabRawat.setSelectedIndex(0);
                tampilDrPr();
                BtnSeekPetugas2.requestFocus();
            }

        } else {
            TabRawat.setSelectedIndex(0);
            tampilDrPr();
            BtnSeekPetugas2.requestFocus();
        }
    }

    public void cekInapIGD(String Cek) {
        cekIGD = Cek;
    }

    public void SimpanPemeriksaan() {
        if (TDiagnosa.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Diagnosa pasien harus terisi dengan benar...!!");
            TDiagnosa.requestFocus();
        } else {
            if (Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals(akses.getkode()) || akses.getkode().equals("Admin Utama")) {
                if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals(""))
                        || (!TSuhu.getText().trim().equals("")) || (!TTensi.getText().trim().equals(""))
                        || (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
                        || (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals(""))
                        || (!TNadi.getText().trim().equals("")) || (!TGCS.getText().trim().equals(""))
                        || (!TRincianTindakan.getText().trim().equals("")) || (!TTerapi.getText().trim().equals(""))) {

                    prmrj = "";
                    if (ChkPrmrj.isSelected() == true) {
                        prmrj = "1";
                    } else {
                        prmrj = "0";
                    }

                    Sequel.menyimpan("pemeriksaan_ralan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 21, new String[]{
                        TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(),
                        TBerat.getText(), TGCS.getText(), TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText(),
                        cmbImun.getSelectedItem().toString(), TDiagnosa.getText(), KdDok.getText(), TRncanaFolow.getText(),
                        TRincianTindakan.getText(), TTerapi.getText(), prmrj, Tspo2.getText()
                    });

                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi.getText() + "'");
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");

                    if (ChkCopyPemeriksaanDR.isSelected() == true) {
                        cekPemeriksaan = 0;
                        cekPemeriksaan = Sequel.cariInteger("select count(-1) from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'");
                        if (cekPemeriksaan == 0) {
                            SimpanPemeriksaanPetugas();
                        } else if (cekPemeriksaan > 0) {
                            Sequel.queryu("delete from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'");
                            SimpanPemeriksaanPetugas();
                        }
                    }
                    emptText();
                } else {
                    JOptionPane.showMessageDialog(null, "Data Pemeriksaan Kurang Lengkap...!!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, dokter yang login tidak sama dg. dokter terjadwal dipoliklinik...!!");
            }
            tampilPemeriksaanDokter();
        }
    }

    public void SimpanPenangananDokterPetugas() {
        if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDok, "Dokter");
        } else if (kdptg.getText().trim().equals("") || TPerawat.getText().trim().equals("")) {
            Valid.textKosong(kdptg, "Petugas");
        } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
            Valid.textKosong(TKdPrw, "perawatan");
        } else {
            Sequel.menyimpan("rawat_jl_drpr", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 15, new String[]{
                TNoRw.getText(), TKdPrw.getText(), KdDok.getText(), kdptg.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                BagianRS.getText(), Bhp.getText(), JmDokter.getText(), JmPerawat.getText(), KSO.getText(), Menejemen.getText(), TTnd.getText(), "Belum", "-"
            });
            tampilDrPr();
            TotalNominal();
        }
    }

    public void petugas(String kodeDokter, String kdPetugas) {
        KdDok.setText(kodeDokter);
        kdptg1.setText(kdPetugas);
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=? ", TDokter, KdDok.getText());
        Sequel.cariIsi("select nama from petugas where nip=? ", TPerawat1, kdptg1.getText());
    }

    public void SimpanPemeriksaanPetugas() {
        if ((!TKeluhan1.getText().trim().equals("")) || (!TPemeriksaan1.getText().trim().equals(""))
                || (!TSuhu1.getText().trim().equals("")) || (!TTensi1.getText().trim().equals(""))
                || (!TAlergi1.getText().trim().equals("")) || (!TTinggi1.getText().trim().equals(""))
                || (!TBerat1.getText().trim().equals("")) || (!TRespirasi1.getText().trim().equals(""))
                || (!TNadi1.getText().trim().equals("")) || (!TGCS1.getText().trim().equals(""))
                || (!TDiagnosa1.getText().trim().equals("")) || (!TRincianTindakan1.getText().trim().equals(""))) {
            Sequel.menyimpan("pemeriksaan_ralan_petugas", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 21, new String[]{
                TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                TSuhu1.getText(), TTensi1.getText(), TNadi1.getText(), TRespirasi1.getText(), TTinggi1.getText(),
                TBerat1.getText(), TGCS1.getText(), TKeluhan1.getText(), TPemeriksaan1.getText(), TAlergi1.getText(),
                cmbImun1.getSelectedItem().toString(), TDiagnosa1.getText(), KdDok.getText(), TRncanaFolow1.getText(),
                kdptg1.getText(), TRincianTindakan1.getText(), TTerapi1.getText(), Tspo21.getText()
            });

            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "tinggi_badan='" + TTinggi1.getText() + "'");
            tampilPemeriksaanPetugas();
        } else {
            JOptionPane.showMessageDialog(null, "Data Pemeriksaan Kurang Lengkap...!!");
        }
    }

    private void tampilPemeriksaanPetugas() {
        Valid.tabelKosong(tabModePemeriksaanPr);
        try {
            ps6 = koneksi.prepareStatement("SELECT prp.no_rawat, ifnull(pt.nama,'-') nama, rp.no_rkm_medis, p.nm_pasien, prp.tgl_perawatan, prp.jam_rawat, prp.suhu_tubuh,"
                    + "prp.tensi, prp.nadi, prp.respirasi, prp.tinggi, prp.berat, prp.gcs, prp.keluhan, prp.pemeriksaan, prp.alergi,"
                    + "prp.imun_ke, prp.diagnosa, prp.rencana_follow_up, prp.nip, prp.rincian_tindakan, "
                    + "ifnull(prp.terapi,'-') terapi, rp.kd_poli, ifnull(prp.spo2,'') spo2 from pasien p inner join reg_periksa rp on rp.no_rkm_medis=p.no_rkm_medis "
                    + "inner join pemeriksaan_ralan_petugas prp on prp.no_rawat=rp.no_rawat inner join dokter d on d.kd_dokter=prp.kd_dokter "
                    + "left join petugas pt on pt.nip = prp.nip where "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and prp.no_rawat like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and rp.no_rkm_medis like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and p.nm_pasien like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and prp.alergi like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and prp.keluhan like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and prp.pemeriksaan like ? or "
                    + "prp.tgl_perawatan between ? and ? and rp.no_rkm_medis like ? and rp.kd_poli like ? and prp.terapi like ? order by prp.no_rawat desc");

            try {
                ps6.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(3, "%" + TCariPasien.getText() + "%");
                ps6.setString(4, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(5, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(6, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(7, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(8, "%" + TCariPasien.getText() + "%");
                ps6.setString(9, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(10, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(11, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(12, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(13, "%" + TCariPasien.getText() + "%");
                ps6.setString(14, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(15, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(18, "%" + TCariPasien.getText() + "%");
                ps6.setString(19, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(20, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(23, "%" + TCariPasien.getText() + "%");
                ps6.setString(24, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(25, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(26, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(27, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(28, "%" + TCariPasien.getText() + "%");
                ps6.setString(29, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(30, "%" + TCari.getText().trim() + "%");                
                
                ps6.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps6.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps6.setString(33, "%" + TCariPasien.getText() + "%");
                ps6.setString(34, "%" + kdpoli.getText().trim() + "%");
                ps6.setString(35, "%" + TCari.getText().trim() + "%");                
                
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    tabModePemeriksaanPr.addRow(new Object[]{false, rs6.getString(1),
                        rs6.getString(2),
                        rs6.getString(3),
                        rs6.getString(4),
                        rs6.getString(5),
                        rs6.getString(6),
                        rs6.getString(7),
                        rs6.getString(8),
                        rs6.getString(9),
                        rs6.getString(10),
                        rs6.getString(11),
                        rs6.getString(12),
                        rs6.getString(13),
                        rs6.getString(14),
                        rs6.getString(15),
                        rs6.getString(16),
                        rs6.getString(17),
                        rs6.getString(18),
                        rs6.getString(19),
                        rs6.getString(20),
                        rs6.getString(21),
                        rs6.getString(22),
                        rs6.getString(23),
                        rs6.getString(24)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModePemeriksaanPr.getRowCount());
    }

    private void cetakPRMRJ() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("norm", TNoRM.getText());
        param.put("nmpasien", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
   
        if (ChkTanggal.isSelected() == true) {
            Valid.MyReport("rptprmrj.jasper", "report", "::[ Lembar RM 3.1.1 (Profil Ringkas Medis Rawat Jalan) ]::",
                    "select rp.no_rawat, date_format(rp.tgl_registrasi, '%d-%m-%Y') tglKun, pr.diagnosa, "
                    + "concat(ifnull(py.kd_penyakit,'-'),' - ',ifnull(py.ciri_ciri,'-')) icd10, ifnull(GROUP_CONCAT(DISTINCT ' ',jr.nm_perawatan),'-') pemrk_rad, "
                    + "ifnull(GROUP_CONCAT(DISTINCT ' ',tl.Pemeriksaan),'-') pemrk_lab, ifnull(GROUP_CONCAT(DISTINCT ' ',cr.nama_obat),'-') obat, d.nm_dokter dpjp "
                    + "FROM reg_periksa rp INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat LEFT JOIN catatan_resep cr on cr.no_rawat=rp.no_rawat "
                    + "LEFT JOIN periksa_radiologi prad on prad.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_radiologi jr on jr.kd_jenis_prw=prad.kd_jenis_prw "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=rp.no_rawat and dp.prioritas='1' LEFT JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                    + "LEFT JOIN detail_periksa_lab dl on dl.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_lab jl on jl.kd_jenis_prw=dl.kd_jenis_prw "
                    + "LEFT JOIN template_laboratorium tl on tl.id_template=dl.id_template WHERE "
                    + "rp.no_rkm_medis = '" + TNoRM.getText() + "' AND pr.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and pr.stts_prmrj='1' and rp.status_lanjut='ralan' GROUP BY rp.no_rawat ORDER BY pr.tgl_perawatan, pr.jam_rawat", param);
        } else {
            Valid.MyReport("rptprmrj.jasper", "report", "::[ Lembar RM 3.1.1 (Profil Ringkas Medis Rawat Jalan) ]::",
                    "select rp.no_rawat, date_format(rp.tgl_registrasi, '%d-%m-%Y') tglKun, pr.diagnosa, "
                    + "concat(ifnull(py.kd_penyakit,'-'),' - ',ifnull(py.ciri_ciri,'-')) icd10, ifnull(GROUP_CONCAT(DISTINCT ' ',jr.nm_perawatan),'-') pemrk_rad, "
                    + "ifnull(GROUP_CONCAT(DISTINCT ' ',tl.Pemeriksaan),'-') pemrk_lab, ifnull(GROUP_CONCAT(DISTINCT ' ',cr.nama_obat),'-') obat, d.nm_dokter dpjp "
                    + "FROM reg_periksa rp INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                    + "INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat LEFT JOIN catatan_resep cr on cr.no_rawat=rp.no_rawat "
                    + "LEFT JOIN periksa_radiologi prad on prad.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_radiologi jr on jr.kd_jenis_prw=prad.kd_jenis_prw "
                    + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=rp.no_rawat and dp.prioritas='1' LEFT JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                    + "LEFT JOIN detail_periksa_lab dl on dl.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_lab jl on jl.kd_jenis_prw=dl.kd_jenis_prw "
                    + "LEFT JOIN template_laboratorium tl on tl.id_template=dl.id_template WHERE "
                    + "rp.no_rkm_medis = '" + TNoRM.getText() + "' and pr.stts_prmrj='1' and rp.status_lanjut='ralan' "
                    + "GROUP BY rp.no_rawat ORDER BY pr.tgl_perawatan, pr.jam_rawat limit 5", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
        tampilPRMRJ();
    }

    private void getDataCatatanResep() {
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRw2.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            TResepObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 4).toString());
            if(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 0).toString().equals("false") ){
                Valid.SetTgl(DTPTgl, tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 2).toString());
            }            
            cmbJam.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 3).toString().substring(6, 8));
            TIdObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 7).toString());
        }
    }

    private void tampilAssesmen() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE "
                        + "p.no_rkm_medis = '" + TCari.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();
                x = 1;
                while (rs8.next()) {
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs8.getString("no_rkm_medis") + "' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg desc").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("select * from (SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab, rp.tgl_registrasi tglReg FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs8.getString("no_rkm_medis") + "' "
                                    + "ORDER BY rp.tgl_registrasi desc, rp.jam_reg desc limit 3) as a order by a.tglReg").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_dokter") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penanggung Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alamat P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("almt_pj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hubungan P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("hubunganpj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + "</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan asuhan awal keperawatan rawat jalan 
                            try {
                                rs3 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','Laki-Laki','Perempuan') jk, "
                                        + "date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.agama, bp.nama_bahasa, pa.nama_cacat_fisik nama_cacat, "
                                        + "date_format(pa.tanggal,'%d %M %Y, Jam : %h:%i %p') tanggal, pa.informasi, pa.td, pa.nadi, pa.rr, pa.suhu, pa.bb, pa.tb, pa.nadi, pa.rr, pa.suhu, pa.gcs, pa.bb, pa.tb, "
                                        + "pa.bmi, pa.keluhan_utama, pa.rpd, pa.rpk, pa.rpo, pa.alergi, pa.alat_bantu, pa.ket_bantu, pa.prothesa, "
                                        + "pa.ket_pro, pa.adl, pa.status_psiko, pa.ket_psiko, pa.hub_keluarga, pa.tinggal_dengan, pa.ket_tinggal, "
                                        + "pa.ekonomi, pa.edukasi, pa.ket_edukasi, pa.berjalan_a, pa.berjalan_b, pa.berjalan_c, pa.hasil, pa.lapor, "
                                        + "pa.ket_lapor, pa.sg1, pa.nilai1,  pa.sg2, pa.nilai2, pa.total_hasil, pa.nyeri, pa.provokes, pa.ket_provokes, "
                                        + "pa.quality, pa.ket_quality, pa.lokasi, pa.menyebar, pa.skala_nyeri, pa.durasi, pa.nyeri_hilang, "
                                        + "pa.ket_nyeri, pa.pada_dokter, pa.ket_dokter, pa.rencana, pa.nip, pt.nama, pa.budaya, pa.ket_budaya FROM reg_periksa rp  "
                                        + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN penilaian_awal_keperawatan_ralan pa ON rp.no_rawat = pa.no_rawat "
                                        + "INNER JOIN petugas pt ON pa.nip = pt.nip INNER JOIN bahasa_pasien bp ON bp.id = p.bahasa_pasien "
                                        + "WHERE pa.no_rawat = '" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penilaian Awal Keperawatan Rawat Jalan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>"
                                                + "YANG MELAKUKAN PENGKAJIAN"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='20%' border='0'>Tanggal : " + rs3.getString("tanggal") + "</td>"
                                                + "<td width='33%' border='0'>Nama Petugas/Perawat/Bidan : " + rs3.getString("nama") + "</td>"
                                                + "<td width='20%' border='0'>Informasi didapat dari : " + rs3.getString("informasi") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "I. KEADAAN UMUM"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='20%' border='0'>TD : " + rs3.getString("td") + " mmHg</td>"
                                                + "<td width='20%' border='0'>Nadi : " + rs3.getString("nadi") + " x/menit</td>"
                                                + "<td width='20%' border='0'>RR : " + rs3.getString("rr") + " x/menit</td>"
                                                + "<td width='20%' border='0'>Suhu : " + rs3.getString("suhu") + " °C</td>"
                                                + "<td width='20%' border='0'>GCS(E,V,M) : " + rs3.getString("gcs") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "II. STATUS NUTRISI"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='33%' border='0'>BB : " + rs3.getString("bb") + " Kg</td>"
                                                + "<td width='33%' border='0'>TB : " + rs3.getString("tb") + " Cm</td>"
                                                + "<td width='33%' border='0'>BMI : " + rs3.getString("bmi") + " Kg/m²</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "III. RIWAYAT KESEHATAN"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' colspan='2'>Keluhan Utama : " + rs3.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penyakit Dahulu : " + rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Alergi : " + rs3.getString("alergi") + " Cm</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penyakit Keluarga : " + rs3.getString("rpk").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Pengobatan : " + rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + " Cm</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "IV. FUNGSIONAL"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Alat Bantu : " + rs3.getString("alat_bantu") + " " + rs3.getString("ket_bantu") + "</td>"
                                                + "<td width='50%' border='0'>Prothesa : " + rs3.getString("prothesa") + " " + rs3.getString("ket_pro") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Cacat Fisik : " + rs3.getString("nama_cacat") + "</td>"
                                                + "<td width='50%' border='0'>Aktivitas Kehidupan Sehari-hari ( ADL ) : " + rs3.getString("adl") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "V. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Status Psikologis : " + rs3.getString("status_psiko") + " " + rs3.getString("ket_psiko") + "</td>"
                                                + "<td width='50%' border='0'>Bahasa yang digunakan sehari-hari : " + rs3.getString("nama_bahasa") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0' colspan='2'>Status Sosial dan ekonomi :</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;a. Hubungan pasien dengan anggota keluarga</td>"
                                                + "<td width='50%' border='0'>: " + rs3.getString("hub_keluarga") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;b. Tinggal dengan</td>"
                                                + "<td width='50%' border='0'>: " + rs3.getString("tinggal_dengan") + " " + rs3.getString("ket_tinggal") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;c. Ekonomi</td>"
                                                + "<td width='50%' border='0'>: " + rs3.getString("ekonomi") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan</td>"
                                                + "<td width='50%' border='0'>: " + rs3.getString("budaya") + " " + rs3.getString("ket_budaya") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Agama : " + rs3.getString("agama") + "</td>"
                                                + "<td width='50%' border='0'>Edukasi diberikan kepada : " + rs3.getString("edukasi") + " " + rs3.getString("ket_edukasi") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "VI. PENILAIAN RESIKO JATUH"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td colpsan='2' border='0'>a. Cara Berjalan :</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;1. Tidak seimbang / sempoyongan / limbung</td>"
                                                + "<td width='25%' border='0'>: " + rs3.getString("berjalan_a") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;2. Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td>"
                                                + "<td width='25%' border='0'>: " + rs3.getString("berjalan_b") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='75%' border='0'>b. Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja / benda lain sebagai penopang</td>"
                                                + "<td width='25%' border='0'>: " + rs3.getString("berjalan_c") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td colspan='2' border='0'>Hasil : " + rs3.getString("hasil") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dilaporkan kepada dokter ? " + rs3.getString("lapor") + " Jam dilaporkan : " + rs3.getString("ket_lapor") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "VII. SKRINING GIZI"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td valign='middle' bgcolor='#f8fdf3' align='center' width='5%'>No</td>"
                                                + "<td valign='middle' bgcolor='#f8fdf3' align='center' width='70%'>Parameter</td>"
                                                + "<td valign='middle' bgcolor='#f8fdf3' align='center' width='25%' colspan='2'>Nilai</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>1</td>"
                                                + "<td valign='top'>Apakah ada penurunan berat badanyang tidak diinginkan selama enam bulan terakhir ?</td>"
                                                + "<td valign='top' align='center' width='20%'>" + rs3.getString("sg1") + "</td>"
                                                + "<td valign='top' align='right' width='5%'>" + rs3.getString("nilai1") + "&nbsp;&nbsp;</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>2</td>"
                                                + "<td valign='top'>Apakah nafsu makan berkurang karena tidak nafsu makan ?</td>"
                                                + "<td valign='top' align='center' width='20%'>" + rs3.getString("sg2") + "</td>"
                                                + "<td valign='top' align='right' width='5%'>" + rs3.getString("nilai2") + "&nbsp;&nbsp;</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top' align='right' colspan='2'>Total Skor</td>"
                                                + "<td valign='top' align='right' colspan='2'>" + rs3.getString("total_hasil") + "&nbsp;&nbsp;</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "VIII. PENILAIAN TINGKAT NYERI"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Tingkat Nyeri : " + rs3.getString("nyeri") + ", Waktu / Durasi : " + rs3.getString("durasi") + " Menit</td>"
                                                + "<td width='50%' border='0'>Penyebab : " + rs3.getString("provokes") + " " + rs3.getString("ket_provokes") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Kualitas : " + rs3.getString("quality") + " " + rs3.getString("ket_quality") + "</td>"
                                                + "<td width='50%' border='0'>Severity : Skala Nyeri " + rs3.getString("skala_nyeri") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' colspan='0' border='0'>Wilayah :</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;Lokasi : " + rs3.getString("lokasi") + "</td>"
                                                + "<td width='50%' border='0'>Menyebar : " + rs3.getString("menyebar") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' border='0'>Nyeri hilang bila : " + rs3.getString("nyeri_hilang") + " " + rs3.getString("ket_nyeri") + "</td>"
                                                + "<td width='50%' border='0'>Diberitahukan pada dokter ? " + rs3.getString("pada_dokter") + ", Jam : " + rs3.getString("ket_dokter") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td valign='middle' bgcolor='#f8fdf3' align='center' width='50%'>MASALAH KEPERAWATAN :</td>"
                                                + "<td valign='middle' bgcolor='#f8fdf3' align='center' width='50%'>RENCANA KEPERAWATAN :</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td>");
                                        try {
                                            rs4 = koneksi.prepareStatement("SELECT mk.kode_masalah, mk.nama_masalah FROM master_masalah_keperawatan mk  "
                                                    + "INNER JOIN penilaian_awal_keperawatan_ralan_masalah pam ON pam.kode_masalah = mk.kode_masalah "
                                                    + "WHERE pam.no_rawat = '" + rs2.getString("no_rawat") + "' ORDER BY mk.kode_masalah").executeQuery();
                                            while (rs4.next()) {
                                                htmlContent.append(rs4.getString("nama_masalah") + "<br>");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : " + e);
                                        } finally {
                                            if (rs4 != null) {
                                                rs4.close();
                                            }
                                        }
                                        htmlContent.append("</td>"
                                                + "<td>" + rs3.getString("rencana").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                        );
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML1.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void tampilRingkasanPulangRanap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs9 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE  "
                        + "p.no_rkm_medis = '" + TCari.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();

                y = 1;
                while (rs9.next()) {
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_rawat, b.nm_bangsal, b.nm_gedung, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, DATE_FORMAT(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y') tgl_pulang, DATE_FORMAT(ki.jam_keluar, '%h:%i %p') jam_plg, pj.png_jawab, ifnull(d.nm_dokter,'-') dpjp, ki.stts_pulang, IFNULL(ki.diagnosa_awal,'-') diag_awal "
                                    + "FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN dpjp_ranap dr ON dr.no_rawat = ki.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE ki.stts_pulang NOT IN ('-', 'Pindah Kamar') AND rp.no_rkm_medis = '" + rs9.getString("no_rkm_medis") + "' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg desc").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("select * from (SELECT rp.no_rawat, b.nm_bangsal, b.nm_gedung, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, DATE_FORMAT(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y') tgl_pulang, DATE_FORMAT(ki.jam_keluar, '%h:%i %p') jam_plg, pj.png_jawab, ifnull(d.nm_dokter,'-') dpjp, "
                                    + "ki.stts_pulang, IFNULL(ki.diagnosa_awal,'-') diag_awal, rp.tgl_registrasi FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar "
                                    + "INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN dpjp_ranap dr ON dr.no_rawat = ki.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE ki.stts_pulang NOT IN ('-', 'Pindah Kamar') AND rp.no_rkm_medis = '" + rs9.getString("no_rkm_medis") + "' "
                                    + "ORDER BY rp.tgl_registrasi desc, rp.jam_reg desc limit 3) as a order by a.tgl_registrasi").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='5px'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanggal Masuk</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_reg") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanggal Pulang</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_pulang") + ", Jam : " + rs2.getString("jam_plg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Pulang</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("stts_pulang") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ruang Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_bangsal") + ", Gedung : " + rs2.getString("nm_gedung") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama DPJP</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("dpjp") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa Awal</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("diag_awal") + "</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan ringkasan pulang rawat inap
                            try {
                                rs3 = koneksi.prepareStatement("SELECT rr.no_rawat, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                                        + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, "
                                        + "rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, "
                                        + "rr.terapi_pulang, rr.pengobatan_dilanjutkan, if(rr.pengobatan_dilanjutkan='Dokter Luar',rr.dokter_luar_lanjutan,'-') dr_luar, "
                                        + "if(rr.tgl_kontrol_poliklinik='0000-00-00','-',date_format(rr.tgl_kontrol_poliklinik,'%d %M %Y')) tgl_kontrol FROM ringkasan_pulang_ranap rr "
                                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                                        + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and rr.no_rawat  = '" + rs2.getString("no_rawat") + "'").executeQuery();

                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ringkasan Pulang Rawat Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Dokter Pengirim</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("dr_pengirim") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Alasan Masuk Dirawat</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("alasan_masuk_dirawat") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Ringkasan Riwayat Penyakit</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("ringkasan_riwayat_penyakit") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Pemeriksaan Fisik</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("pemeriksaan_fisik") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Pemeriksaan Penunjang Diagnostik</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("pemeriksaan_penunjang") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Terapi Pengobatan Selama Dirawat & Efek Samping (bila ada)</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("terapi_pengobatan") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Keadaan Umum</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("keadaan_umum") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Kesadaran</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("kesadaran") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Tanda Vital</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>Tekanan Darah : " + rs3.getString("tekanan_darah") + ", Suhu : " + rs3.getString("suhu") + ", Nadi : " + rs3.getString("nadi") + ", Frekuensi Nafas : " + rs3.getString("frekuensi_nafas") + ", GCS : " + rs3.getString("gcs") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Pengobatan Lanjutan</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("pengobatan_dilanjutkan") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Dokter Luar</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("dr_luar") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Tanggal Kontrol Ulang</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("tgl_kontrol") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Catatan Penting (Kondisi saat ini)</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("catatan_penting") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Terapi Pulang</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("terapi_pulang") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Diagnosa Utama/Primer yang diketik manual</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("diagnosa_utama") + "</td>"
                                                + "</tr>"
                                                + "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>Diagnosa Sekunder yang diketik manual</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>" + rs3.getString("diagnosa_sekunder") + "</td>"
                                                + "</tr>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan diagnosa utama/primer
                            try {
                                rs10 = koneksi.prepareStatement("SELECT dp.no_rawat, dp.kd_penyakit, py.ciri_ciri nm_penyakit FROM diagnosa_pasien dp "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit WHERE dp.prioritas='1' and "
                                        + "dp.no_rawat='" + rs2.getString("no_rawat") + "' and dp.status='Ranap' ORDER BY dp.prioritas").executeQuery();

                                if (rs10.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa Utama/Primer ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td></tr>");

                                    rs10.beforeFirst();
                                    w = 1;
                                    while (rs10.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs10.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs10.getString("nm_penyakit") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs10 != null) {
                                    rs10.close();
                                }
                            }

                            //menampilkan diagnosa sekunder
                            try {
                                rs11 = koneksi.prepareStatement("SELECT dp.no_rawat, dp.kd_penyakit, py.ciri_ciri nm_penyakit FROM diagnosa_pasien dp "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit WHERE dp.prioritas<>'1' and "
                                        + "dp.no_rawat='" + rs2.getString("no_rawat") + "' and dp.status='Ranap' ORDER BY dp.prioritas").executeQuery();

                                if (rs11.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa Sekunder ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td></tr>");

                                    rs11.beforeFirst();
                                    w = 1;
                                    while (rs11.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs11.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs11.getString("nm_penyakit") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs11 != null) {
                                    rs11.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs12 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                                        + "WHERE pp.no_rawat = '" + rs2.getString("no_rawat") + "' and pp.status='Ranap'").executeQuery();

                                if (rs12.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prosedur Tindakan/ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-9</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Tindakan/Prosedur ICD-9</td></tr>");

                                    rs12.beforeFirst();
                                    w = 1;
                                    while (rs12.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs12.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs12.getString("deskripsi_panjang") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs12 != null) {
                                    rs12.close();
                                }
                            }
                            
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement("SELECT no_lab FROM lis_reg WHERE no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, lhp.satuan, lhp.flag_kode FROM lis_reg lr "
                                                                + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                                + "lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' "
                                                                + "GROUP BY lhp.pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }
                            
                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"                                            
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ranap
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ranap' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG Rawat Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement("select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Jalan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td colspan='2' valign='top' width='6%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_reg") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement("select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Jalan Paramedis</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_reg") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement("select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Jalan Dokter & Paramedis</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_reg") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement("select date_format(rawat_inap_dr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(rawat_inap_dr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "
                                        + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "
                                        + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by rawat_inap_dr.tgl_perawatan, rawat_inap_dr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Inap Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan paramedis ranap
                            try {
                                rs3 = koneksi.prepareStatement("select date_format(rawat_inap_pr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_pr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "petugas.nama,rawat_inap_pr.biaya_rawat "
                                        + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "
                                        + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by rawat_inap_pr.tgl_perawatan, rawat_inap_pr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Inap Paramedis</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //tindakan paramedis dan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement("select date_format(rawat_inap_drpr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(rawat_inap_drpr.jam_rawat,'%h:%i %p') jam_rawat,rawat_inap_drpr.kd_jenis_prw,"
                                        + "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "
                                        + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "
                                        + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "
                                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan Rawat Inap Dokter & Paramedis</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan pemberian obat farmasi
                            try {
                                rs3 = koneksi.prepareStatement("select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(detail_pemberian_obat.jam,'%h:%i %p') jam, lower(databarang.kode_sat) kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemberian Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"                                            
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"                                                
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan resep pulang farmasi
                            try {
                                rs3 = koneksi.prepareStatement("select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "lower(databarang.kode_sat) kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Resep Pulang</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td colspan='2' valign='top' width='7%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan retur obat
                            try {
                                rs3 = koneksi.prepareStatement("select databarang.kode_brng, databarang.nama_brng, lower(detreturjual.kode_sat) kode_sat,"
                                        +"detreturjual.h_retur, (detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' "
                                        + "order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Retur Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='4px' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='85%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td colspan='2' valign='top' width='6%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td style='border-right:none'>Rp.</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void tampilMintaLab1() {
        Valid.tabelKosong(tabModeLab1);
        try {
            psLab1 = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                    + "p.status_periksa, p.no_rawat, p.no_minta, p.cito FROM permintaan_lab_raza p inner join dokter d on d.kd_dokter=p.dokter_perujuk where "
                    + "p.status_rawat='Ralan' and p.no_rawat='" + TNoRw.getText() + "' order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            try {
                rsLab1 = psLab1.executeQuery();
                x1 = 1;
                while (rsLab1.next()) {
                    tabModeLab1.addRow(new String[]{
                        x1 + ".",
                        rsLab1.getString("nm_pemeriksaan"),
                        rsLab1.getString("tglminta"),
                        rsLab1.getString("jam_permintaan"),
                        rsLab1.getString("status_periksa"),
                        rsLab1.getString("no_rawat"),
                        rsLab1.getString("no_minta"),
                        rsLab1.getString("cito")
                    });
                    x1++;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsLab1 != null) {
                    rsLab1.close();
                }
                if (psLab1 != null) {
                    psLab1.close();
                }
            }
            LCount.setText("" + tabModeLab1.getRowCount());
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        
//        try {
//            psLab1 = koneksi.prepareStatement("SELECT pl.no_rawat, jpl.nm_perawatan, tl.Pemeriksaan, DATE_FORMAT(pl.tgl_permintaan,'%d-%m-%Y') tgl_permintaan, "
//                    + "IF (pl.jam_permintaan = '00:00:00','',pl.jam_permintaan) jam_permintaan, d.nm_dokter dr_pengorder, pl.noorder, tl.id_template "
//                    + "FROM permintaan_lab pl INNER JOIN reg_periksa rp on rp.no_rawat=pl.no_rawat "
//                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN permintaan_pemeriksaan_lab ppl on ppl.noorder=pl.noorder "
//                    + "INNER JOIN permintaan_detail_permintaan_lab pdpl on pdpl.kd_jenis_prw=ppl.kd_jenis_prw "
//                    + "INNER JOIN jns_perawatan_lab jpl on jpl.kd_jenis_prw=ppl.kd_jenis_prw "
//                    + "INNER JOIN template_laboratorium tl on tl.id_template=pdpl.id_template INNER JOIN dokter d on d.kd_dokter=pl.dokter_perujuk "
//                    + "WHERE pl.tgl_permintaan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' AND pl.no_rawat LIKE '%" + TNoRw.getText() + "%' "
//                    + "ORDER BY pl.tgl_permintaan, pl.jam_permintaan");
//            try {
//                rsLab1 = psLab1.executeQuery();
//                while (rsLab1.next()) {
//                    tabModeLab1.addRow(new String[]{
//                        rsLab1.getString("no_rawat"),
//                        rsLab1.getString("nm_perawatan"),
//                        rsLab1.getString("Pemeriksaan"),
//                        rsLab1.getString("tgl_permintaan"),
//                        rsLab1.getString("jam_permintaan"),
//                        rsLab1.getString("dr_pengorder"),
//                        rsLab1.getString("noorder"),
//                        rsLab1.getString("id_template")
//                    });
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : " + e);
//            } finally {
//                if (rsLab1 != null) {
//                    rsLab1.close();
//                }
//                if (psLab1 != null) {
//                    psLab1.close();
//                }
//            }
//            LCount.setText("" + tabModeLab1.getRowCount());
//        } catch (Exception e) {
//            System.out.println("Notif : " + e);
//        }
    }

    private void tampilMintaRad1() {
        Valid.tabelKosong(tabModeRad1);
        try {
            psRad1 = koneksi.prepareStatement("SELECT pr.no_rawat, pr.nm_pemeriksaan, DATE_FORMAT(pr.tgl_permintaan,'%d-%m-%Y') tgl_permintaan, "
                    + "IF(pr.jam_permintaan='00:00:00','',pr.jam_permintaan) jam_permintaan, d.nm_dokter, pr.noorder, pr.status, "
                    + "pr.waktu_simpan FROM permintaan_radiologi pr INNER JOIN reg_periksa rp ON rp.no_rawat = pr.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN dokter d ON d.kd_dokter = pr.dokter_perujuk WHERE "
                    + "pr.tgl_permintaan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "AND pr.no_rawat LIKE '%" + TNoRw.getText() + "%' ORDER BY pr.tgl_permintaan, pr.jam_permintaan");
            try {
                rsRad1 = psRad1.executeQuery();
                while (rsRad1.next()) {
                    tabModeRad1.addRow(new String[]{
                        rsRad1.getString("no_rawat"),
                        rsRad1.getString("nm_pemeriksaan"),
                        rsRad1.getString("tgl_permintaan"),
                        rsRad1.getString("jam_permintaan"),
                        rsRad1.getString("nm_dokter"),
                        rsRad1.getString("noorder"),
                        rsRad1.getString("status"),
                        rsRad1.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsRad1 != null) {
                    rsRad1.close();
                }
                if (psRad1 != null) {
                    psRad1.close();
                }
            }
            LCount.setText("" + tabModeRad1.getRowCount());
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
    public void PasienParu (String norwt1) {        
        try {
            psparu = koneksi.prepareStatement("select rp.no_rawat, date_format(tgl_registrasi,'%d-%m-%Y') tgl_reg, p.no_rkm_medis, "
                    + "p.nm_pasien, rp.kd_poli, pl.nm_poli from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + norwt1 + "'");

            try {
                rsparu = psparu.executeQuery();
                while (rsparu.next()) {
                    kode_poli = rsparu.getString("kd_poli");
                    no_rawat.setText(rsparu.getString("no_rawat"));
                    tgl_reg.setText(rsparu.getString("tgl_reg"));
                    no_rm.setText(rsparu.getString("no_rkm_medis"));
                    nm_pasien.setText(rsparu.getString("nm_pasien"));
                    nm_poli.setText(rsparu.getString("nm_poli"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsparu != null) {
                    rsparu.close();
                }
                if (psparu != null) {
                    psparu.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void cekDataParu(String norwt2) {
        ceksensusparu = 0;
        ceksensusparu = Sequel.cariInteger("select count(1) cek from sensus_poli_paru where no_rawat='" + norwt2 + "'");

        if (ceksensusparu >= 1) {
            cekppok = Sequel.cariIsi("select kasus_ppok from sensus_poli_paru where no_rawat='" + norwt2 + "'");
            cekobattb = Sequel.cariIsi("select obat_TB from sensus_poli_paru where no_rawat='" + norwt2 + "'");
            ket_paru.setText(Sequel.cariIsi("select keterangan from sensus_poli_paru where no_rawat='" + norwt2 + "'"));
            
            if (cekppok.equals("SERANGAN")) {
                serangan.setSelected(true);
                konsultasi.setSelected(false);
            } else if (cekppok.equals("KONSULTASI")) {
                serangan.setSelected(false);
                konsultasi.setSelected(true);
            } else {
                serangan.setSelected(false);
                konsultasi.setSelected(false);
            }
            
            if (cekobattb.equals("LAIN-LAIN")) {
                lain.setSelected(true);
                bpjs.setSelected(false);
                dot.setSelected(false);
                puskes.setSelected(false);
            } else if (cekobattb.equals("BPJS")) {
                lain.setSelected(false);
                bpjs.setSelected(true);
                dot.setSelected(false);
                puskes.setSelected(false);
            } else if (cekobattb.equals("DOT")) {
                lain.setSelected(false);
                bpjs.setSelected(false);
                dot.setSelected(true);
                puskes.setSelected(false);
            } else if (cekobattb.equals("PUSKESMAS")) {
                lain.setSelected(false);
                bpjs.setSelected(false);
                dot.setSelected(false);
                puskes.setSelected(true);
            } else {
                lain.setSelected(false);
                bpjs.setSelected(false);
                dot.setSelected(false);
                puskes.setSelected(false);
            }
        } else if (ceksensusparu == 0) {
            BtnBaruActionPerformed(null);
        }
    }
    
    private void tampilFarmasi() {
        Valid.tabelKosong(tabModeFarmasi);
        try {
            psFar = koneksi.prepareStatement("SELECT db.nama_brng, db.kode_sat, sum(case when gd.kd_bangsal = 'APT01' then ifnull(format(gd.stok,0),0) END) apotek_igd, "
                    + "sum(case when gd.kd_bangsal = 'APT02' then ifnull(format(gd.stok,0),0) END) apotek_sentral FROM gudangbarang gd "
                    + "INNER JOIN databarang db on db.kode_brng=gd.kode_brng where "
                    + "db.nama_brng like ? and gd.kd_bangsal in ('APT01','APT02') and db.nama_brng not like '(FR)%' group by gd.kode_brng "
                    + "order by db.nama_brng");
            try {
                psFar.setString(1, "%" + TCariObat.getText().trim() + "%");
                rsFar = psFar.executeQuery();
                while (rsFar.next()) {
                    tabModeFarmasi.addRow(new Object[]{
                        rsFar.getString("nama_brng"),
                        rsFar.getString("kode_sat"),
                        rsFar.getString("apotek_igd"),
                        rsFar.getString("apotek_sentral")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsFar != null) {
                    rsFar.close();
                }
                if (psFar != null) {
                    psFar.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataFarmasi() {
        if (tbObat.getSelectedRow() != -1) {
            if (TResepObat.getText().equals("")) {
                TResepObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " ");
            } else {
                TResepObat.setText(TResepObat.getText() + " " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
            }
            TResepObat.requestFocus();
        }        
    }
    
    private void tampilRiwayatKun(String code) {
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
    
    private void emptText() {
        ChkInput.setSelected(true);
        isForm();
        kdpoli.setText("");
        TPoli.setText("");
        TSuhu.setText("");
        TKdPrw.setText("");
        TNmPrw.setText("");
        TTensi.setText("");
        TKeluhan.setText("");
        TPemeriksaan.setText("");
        TAlergi.setText("");
        TBerat.setText("");
        TTinggi.setText("");
        TNadi.setText("");
        TRespirasi.setText("");
        TGCS.setText("");
        TTnd.setText("0");
        BagianRS.setText("0");
        Bhp.setText("0");
        JmDokter.setText("0");
        JmPerawat.setText("0");
        TDiagnosa.setText("");
        TResepObat.setText("");
        TRncanaFolow.setText("");
        TRincianTindakan.setText("");
        TTerapi.setText("");
        cmbImun.setSelectedIndex(0);
        TNoRw.requestFocus();

        TSuhu1.setText("");
        TTensi1.setText("");
        TKeluhan1.setText("");
        TPemeriksaan1.setText("");
        TAlergi1.setText("");
        TBerat1.setText("");
        TTinggi1.setText("");
        TNadi1.setText("");
        TRespirasi1.setText("");
        TGCS1.setText("");
        TDiagnosa1.setText("");
        TRncanaFolow1.setText("");
        TRincianTindakan1.setText("");
        cmbImun1.setSelectedIndex(0);
        TTerapi1.setText("");
        Tspo21.setText("");
        Tspo2.setText("");

        TNoRw2.setText("");
        ChkPemeriksaan.setSelected(false);
        ChkPrmrj.setSelected(false);
        ChkCopyPemeriksaanDR.setSelected(false);
    }
    
    private void cekPemeriksaanPetugas() {
        try {
            psPet = koneksi.prepareStatement("select p.suhu_tubuh, p.tensi, p.nadi, p.respirasi, p.tinggi, p.berat, p.gcs, p.keluhan, "
                    + "p.imun_ke, ifnull(p.spo2,'') spo2 from pemeriksaan_ralan_petugas p inner join reg_periksa r on r.no_rawat=p.no_rawat where "
                    + "p.tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' and r.no_rkm_medis like '%" + TCariPasien.getText() + "%' "
                    + "and r.kd_poli like '%" + kdpoli.getText() + "%' and p.no_rawat='" + TNoRw.getText() + "' limit 1");
            try {
                rsPet = psPet.executeQuery();
                while (rsPet.next()) {
                    TSuhu.setText(rsPet.getString("suhu_tubuh"));
                    TTensi.setText(rsPet.getString("tensi"));
                    TNadi.setText(rsPet.getString("nadi"));
                    TRespirasi.setText(rsPet.getString("respirasi"));
                    TTinggi.setText(rsPet.getString("tinggi"));
                    TBerat.setText(rsPet.getString("berat"));
                    TGCS.setText(rsPet.getString("gcs"));
                    TKeluhan.setText(rsPet.getString("keluhan"));
                    cmbImun.setSelectedItem(rsPet.getString("imun_ke"));
                    Tspo2.setText(rsPet.getString("spo2"));
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPet != null) {
                    rsPet.close();
                }
                if (psPet != null) {
                    psPet.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilTglResep() {
        Valid.tabelKosong(tabModeResep1);
        try {
            if (ChkPoli.isSelected() == true) {
                psR1 = koneksi.prepareStatement("select date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter, pl.nm_poli, c.tgl_perawatan, r.kd_poli from catatan_resep c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                        + "inner join poliklinik pl on pl.kd_poli=r.kd_poli where r.no_rkm_medis='" + TNoRM.getText() + "' GROUP BY c.no_rawat "
                        + "order by c.tgl_perawatan DESC LIMIT 5");
            } else {
                psR1 = koneksi.prepareStatement("select date_format(c.tgl_perawatan,'%d-%m-%Y') tglnya, d.nm_dokter, pl.nm_poli, c.tgl_perawatan, r.kd_poli from catatan_resep c "
                        + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter "
                        + "inner join poliklinik pl on pl.kd_poli=r.kd_poli where r.kd_poli='" + polinya + "' and r.no_rkm_medis='" + TNoRM.getText() + "' GROUP BY c.no_rawat "
                        + "order by c.tgl_perawatan DESC LIMIT 5");
            }
            try {
                rsR1 = psR1.executeQuery();
                while (rsR1.next()) {
                    tabModeResep1.addRow(new Object[]{
                        rsR1.getString("tglnya"),
                        rsR1.getString("nm_dokter"),
                        rsR1.getString("nm_poli"),
                        rsR1.getString("tgl_perawatan"),
                        rsR1.getString("kd_poli")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR1 != null) {
                    rsR1.close();
                }
                if (psR1 != null) {
                    psR1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemResep(String tglresep, String norm, String kdpoli) {
        Valid.tabelKosong(tabModeResep2);
        try {
            psR2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, c.noID from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat inner join dokter d on d.kd_dokter = c.kd_dokter where "
                    + "c.tgl_perawatan='" + tglresep + "' and r.no_rkm_medis='" + norm + "' and r.kd_poli='" + kdpoli + "' order by c.noId");
            try {
                rsR2 = psR2.executeQuery();
                while (rsR2.next()) {
                    tabModeResep2.addRow(new Object[]{false,
                        rsR2.getString(1),
                        rsR2.getString(2),
                        rsR2.getString(3),
                        rsR2.getString(4),
                        rsR2.getString(5),
                        rsR2.getString(6),
                        rsR2.getString(7)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsR2 != null) {
                    rsR2.close();
                }
                if (psR2 != null) {
                    psR2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void TotalNominal() {
        cekTotPemeriksaan = "";
        cekTotPemeriksaan = Sequel.cariIsi("SELECT CONCAT('Rp. ',FORMAT(SUM(biaya_rawat),0)) FROM rawat_jl_drpr "
                + "WHERE no_rawat='" + TNoRw.getText().trim() + "' GROUP BY no_rawat");

        if (cekTotPemeriksaan.equals("")) {
            label_tot_pemeriksaan.setText("Rp. 0");
        } else {
            label_tot_pemeriksaan.setText(cekTotPemeriksaan);
        }
    }
    
    private void tampilRujukanInternal() {
        Valid.tabelKosong(tabModeRujukan);
        try {
            psru1 = koneksi.prepareStatement("SELECT r.no_rawat, DATE_FORMAT(r.tgl_rencana_dirujuk,'%d-%m-%Y') tglDirujuk, pl1.nm_poli poliAsal, "
                    + "d.nm_dokter, r.keterangan, pl2.nm_poli poliTujuan, ifnull(r.keterangan_balasan,'') keterangan_balasan, "
                    + "r.status_jawaban, r.tgl_rencana_dirujuk, r.no_rawat_pembalas, r.status_jawaban, r.tgl_simpan FROM rujukan_internal_poli r "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = r.no_rawat INNER JOIN poliklinik pl1 ON pl1.kd_poli = r.kd_poli "
                    + "INNER JOIN poliklinik pl2 ON pl2.kd_poli = r.kd_poli_pembalas INNER JOIN dokter d ON d.kd_dokter = rp.kd_dokter "
                    + "WHERE r.kd_poli_pembalas='" + polinya + "' AND r.status_jawaban='" + jawaban + "' "
                    + "AND rp.no_rkm_medis='" + TNoRM.getText() + "' ORDER BY r.tgl_rencana_dirujuk DESC limit 10");
            try {
                rsru1 = psru1.executeQuery();
                while (rsru1.next()) {
                    tabModeRujukan.addRow(new Object[]{
                        rsru1.getString("no_rawat"),
                        rsru1.getString("tglDirujuk"),
                        rsru1.getString("poliAsal"),
                        rsru1.getString("nm_dokter"),
                        rsru1.getString("keterangan"),
                        rsru1.getString("poliTujuan"),
                        rsru1.getString("keterangan_balasan"),
                        rsru1.getString("status_jawaban").replaceAll("Sudah", "Terjawab").replaceAll("Belum", "Belum Dijawab"),
                        rsru1.getString("tgl_rencana_dirujuk"),
                        rsru1.getString("status_jawaban"),
                        rsru1.getString("tgl_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsru1 != null) {
                    rsru1.close();
                }
                if (psru1 != null) {
                    psru1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRujukan() {
        jawaban = "";
        tglSimpanRujukan = "";
        if (tbRujukan.getSelectedRow() != -1) {
            Tnorawat.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 0).toString());
            tglDirujuk.setText(Valid.SetTglINDONESIA(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 8).toString()));
            TDariPoli.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 2).toString());            
            OlehDokter.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 3).toString());            
            TKetAsalRujukan.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 4).toString());
            poliMenjawab.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 5).toString());            
            TJwbnRujukan.setText(tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 6).toString());            
            jawaban = tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 9).toString();
            tglSimpanRujukan = tbRujukan.getValueAt(tbRujukan.getSelectedRow(), 10).toString();
            
            if (jawaban.equals("Terjawab")) {
                cmbStatus.setSelectedIndex(0);
            } else {
                cmbStatus.setSelectedIndex(1);
            }
            
            TJwbnRujukan.requestFocus();
        }
    }
    
    private void emptTeksRujukanInternal() {
        Tnorawat.setText("");
        TDariPoli.setText("");
        tglDirujuk.setText("");
        OlehDokter.setText("");
        TKetAsalRujukan.setText("");

        cmbStatus.setSelectedIndex(1);
        poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
        dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
        TJwbnRujukan.setText("");
        tbRujukan.requestFocus();
        
        if (cmbStatus.getSelectedIndex() == 0) {
            jawaban = "Sudah";
        } else {
            jawaban = "Belum";
        }
    }
    
    private void gantiJawabanRujukan() {
        if (Tnorawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rujukannya pada tabel...!!");
            tbRujukan.requestFocus();
        } else if (TJwbnRujukan.getText().trim().equals("") || TJwbnRujukan.getText().trim().length() < 6) {
            JOptionPane.showMessageDialog(null, "Kalimat uraian balasan/jawaban rujukan belum terisi dg. benar, silahkan lengkapi lagi...!!");
            TJwbnRujukan.requestFocus();
        } else {
            if (Sequel.cariIsi("select kd_dokter_pembalas from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'").equals(akses.getkode()) || akses.getkode().equals("Admin Utama")) {
                
                Sequel.mengedit("rujukan_internal_poli", "no_rawat='" + Tnorawat.getText() + "' and tgl_simpan='" + tglSimpanRujukan + "'",
                        "status_jawaban='Sudah', no_rawat_pembalas='" + TNoRw.getText() + "', keterangan_balasan='" + TJwbnRujukan.getText() + "', "
                        + "kd_dokter_pembalas='" + Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'") + "'");

                Tnorawat.setText("");
                TDariPoli.setText("");
                tglDirujuk.setText("");
                OlehDokter.setText("");
                TKetAsalRujukan.setText("");

                cmbStatus.setSelectedIndex(0);
                poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
                dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
                TJwbnRujukan.setText("");
                tbRujukan.requestFocus();

                if (cmbStatus.getSelectedIndex() == 0) {
                    jawaban = "Sudah";
                } else {
                    jawaban = "Belum";
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Maaf, hanya " + Sequel.cariIsi("select d.nm_dokter from rujukan_internal_poli r "
                        + "inner join dokter d on d.kd_dokter=r.kd_dokter_pembalas where "
                        + "r.no_rawat_pembalas='" + Sequel.cariIsi("select no_rawat_pembalas from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'") + "'")
                        + " yg. bisa memperbaiki balasan/jawaban rujukannya..!!");
            }
        }
    }
    
    private void hapusJawabanRujukan() {
        if (Tnorawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rujukannya pada tabel...!!");
            tbRujukan.requestFocus();
        } else {
            if (Sequel.cariIsi("select kd_dokter_pembalas from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'").equals(akses.getkode()) || akses.getkode().equals("Admin Utama")) {
                
                Sequel.mengedit("rujukan_internal_poli", "no_rawat='" + Tnorawat.getText() + "' and tgl_simpan='" + tglSimpanRujukan + "'",
                        "status_jawaban='Belum', no_rawat_pembalas='', keterangan_balasan='', kd_dokter_pembalas=''");

                Tnorawat.setText("");
                TDariPoli.setText("");
                tglDirujuk.setText("");
                OlehDokter.setText("");
                TKetAsalRujukan.setText("");

                cmbStatus.setSelectedIndex(1);
                poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
                dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
                TJwbnRujukan.setText("");
                tbRujukan.requestFocus();

                if (cmbStatus.getSelectedIndex() == 0) {
                    jawaban = "Sudah";
                } else {
                    jawaban = "Belum";
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Maaf, hanya " + Sequel.cariIsi("select d.nm_dokter from rujukan_internal_poli r "
                        + "inner join dokter d on d.kd_dokter=r.kd_dokter_pembalas where "
                        + "r.no_rawat_pembalas='" + Sequel.cariIsi("select no_rawat_pembalas from rujukan_internal_poli where no_rawat='" + Tnorawat.getText() + "'") + "'")
                        + " yg. bisa menghapus balasan/jawaban rujukannya..!!");
            }
        }
    }
    
    private void simpanJawabanRujukan() {
        if (Tnorawat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data rujukannya pada tabel...!!");
            tbRujukan.requestFocus();
        } else if (TJwbnRujukan.getText().trim().equals("") || TJwbnRujukan.getText().trim().length() < 6) {
            JOptionPane.showMessageDialog(null, "Kalimat uraian balasan/jawaban rujukan belum terisi dg. benar, silahkan lengkapi lagi...!!");
            TJwbnRujukan.requestFocus();
        } else {
            Sequel.mengedit("rujukan_internal_poli", "no_rawat='" + Tnorawat.getText() + "' and tgl_simpan='" + tglSimpanRujukan + "'",
                    "status_jawaban='Sudah', no_rawat_pembalas='" + TNoRw.getText() + "', keterangan_balasan='" + TJwbnRujukan.getText() + "', "
                    + "kd_dokter_pembalas='" + Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'") + "'");

            Tnorawat.setText("");
            TDariPoli.setText("");
            tglDirujuk.setText("");
            OlehDokter.setText("");
            TKetAsalRujukan.setText("");

            cmbStatus.setSelectedIndex(0);
            poliMenjawab.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + polinya + "'"));
            dokterMenjawab.setText(Sequel.cariIsi("select ifnull(d.nm_dokter,'') from reg_periksa rp "
                    + "inner join dokter d on d.kd_dokter=rp.kd_dokter where no_rawat='" + TNoRw.getText() + "'"));
            TJwbnRujukan.setText("");
            tbRujukan.requestFocus();

            if (cmbStatus.getSelectedIndex() == 0) {
                jawaban = "Sudah";
            } else {
                jawaban = "Belum";
            }
        }
    }
    
    private void tampilAwalMedisTHT() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs13 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE "
                        + "p.no_rkm_medis = '" + TCari.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();
                x = 1;
                while (rs13.next()) {
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs13.getString("no_rkm_medis") + "' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "and rp.kd_poli='THT' ORDER BY rp.tgl_registrasi, rp.jam_reg desc").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("select * from (SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab, rp.tgl_registrasi tglReg FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs13.getString("no_rkm_medis") + "' "
                                    + "and rp.kd_poli='THT' ORDER BY rp.tgl_registrasi desc, rp.jam_reg desc limit 3) as a order by a.tglReg").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_dokter") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penanggung Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alamat P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("almt_pj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hubungan P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("hubunganpj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + "</td>"
                                    + "</tr>"                                    
                            );
                            urut++;

                            //menampilkan pwnilaian awal medis rawat jalan THT
                            try {
                                rs14 = koneksi.prepareStatement(
                                        "SELECT date_format(pt.tanggal,'%d-%m-%Y, Jam : %H:%i %p') tanggal, pt.kd_dokter, pt.anamnesis, pt.hubungan, pt.keluhan_utama, "
                                        + "pt.rps, pt.rpd, pt.rpo, pt.alergi, pt.td,pt.nadi, pt.rr,pt.suhu,pt.bb,pt.tb,pt.nyeri,pt.status_nutrisi, "
                                        + "pt.kondisi,pt.ket_lokalis,pt.lab,pt.rad,pt.tes_pendengaran, pt.penunjang,pt.diagnosis, "
                                        + "pt.diagnosisbanding,pt.permasalahan, pt.terapi,pt.tindakan,pt.tatalaksana,pt.edukasi,d.nm_dokter "
                                        + "FROM penilaian_medis_ralan_tht pt INNER JOIN dokter d ON pt.kd_dokter=d.kd_dokter "
                                        + "WHERE pt.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs14.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penilaian Awal Medis Rawat Jalan THT</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs14.beforeFirst();
                                    while (rs14.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>"
                                                + "YANG MELAKUKAN PENGKAJIAN"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='33%' border='0'>Tanggal : " + rs14.getString("tanggal") + "</td>"
                                                + "<td width='33%' border='0'>Dokter : " + rs14.getString("nm_dokter") + "</td>"
                                                + "<td width='33%' border='0'>Anamnesis : " + rs14.getString("anamnesis") + (rs14.getString("hubungan").equals("") ? "" : ", " + rs14.getString("hubungan")) + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "I. RIWAYAT KESEHATAN"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td colspan='2'>Keluhan Utama : " + rs14.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penyakit Sekarang : " + rs14.getString("rps").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Penyakit Dahulu : " + rs14.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penggunaan Obat : " + rs14.getString("rpo").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Alergi : " + rs14.getString("alergi") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "II. PEMERIKSAAN FISIK"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='16%' border='0'>TD : " + rs14.getString("td") + " mmHg</td>"
                                                + "<td width='16%' border='0'>TB : " + rs14.getString("tb") + " Cm</td>"
                                                + "<td width='16%' border='0'>BB : " + rs14.getString("bb") + " Kg</td>"
                                                + "<td width='16%' border='0'>Suhu : " + rs14.getString("suhu") + " °C</td>"
                                                + "<td width='16%' border='0'>Nadi : " + rs14.getString("nadi") + " x/menit</td>"
                                                + "<td width='16%' border='0'>RR : " + rs14.getString("rr") + " x/menit</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' colspan='3' valign='top'>Nyeri : " + rs14.getString("nyeri") + "</td>"
                                                + "<td width='50%' colspan='3' valign='top'>Status Nutrisi : " + rs14.getString("status_nutrisi") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%' colspan='6' valign='top'>Kondisi Umum : " + rs14.getString("kondisi") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "III. STATUS LOKALIS"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>" + rs14.getString("ket_lokalis").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "IV. PEMERIKSAAN PENUNJANG"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' valign='top'>Laboratorium : " + rs14.getString("lab").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%' valign='top'>Radiologi : " + rs14.getString("rad").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' valign='top'>Tes Pendengaran : " + rs14.getString("tes_pendengaran").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%' valign='top'>Penunjang Lainnya : " + rs14.getString("penunjang").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "V. DIAGNOSIS/ASESMEN"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' valign='top'>Asesmen Kerja : " + rs14.getString("diagnosis").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%' valign='top'>Asesmen Banding : " + rs14.getString("diagnosisbanding").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "VI. PERMASALAHAN & TATALAKSANA"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='50%' valign='top'>Permasalahan : " + rs14.getString("permasalahan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%' valign='top'>Terapi/Pengobatan : " + rs14.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%' valign='top'>Tindakan/Rencana Tindakan : " + rs14.getString("tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%' valign='top'>Tatalaksana Lainnya : " + rs14.getString("tatalaksana").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'>"
                                                + "VII. EDUKASI"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>" + rs14.getString("edukasi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs14 != null) {
                                    rs14.close();
                                }
                            }
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML3.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs13 != null) {
                    rs13.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilTglBeriObat() {
        Valid.tabelKosong(tabModeTglBeriObat);
        try {
            if (ChkPoli1.isSelected() == true) {
                psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter from detail_pemberian_obat dpo "
                        + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                        + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + polinya + "' and rp.status_lanjut='ralan' "
                        + "group by dpo.tgl_perawatan order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
            } else if (ChkPoli1.isSelected() == false) {
                psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter from detail_pemberian_obat dpo "
                        + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                        + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.status_lanjut='ralan' "
                        + "group by dpo.tgl_perawatan order by dpo.tgl_perawatan desc, dpo.jam desc limit 5");
            }
            try {
                rsTglBO = psTglBO.executeQuery();
                while (rsTglBO.next()) {
                    tabModeTglBeriObat.addRow(new Object[]{
                        rsTglBO.getString("tglAsli"),
                        rsTglBO.getString("tanggal"),
                        rsTglBO.getString("jlhItem"),
                        rsTglBO.getString("nm_poli"),
                        rsTglBO.getString("nm_dokter")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsTglBO != null) {
                    rsTglBO.close();
                }
                if (psTglBO != null) {
                    psTglBO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwItemObat() {
        Valid.tabelKosong(tabModeRiwItemObat);
        try {
            if (ChkPoli1.isSelected() == true) {
                psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                        + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                        + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                        + "dpo.tgl_perawatan='" + tglPemberianObat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='" + polinya + "' and rp.status_lanjut='ralan'");
            } else if (ChkPoli1.isSelected() == false) {
                psRiwIO = koneksi.prepareStatement("SELECT DATE_FORMAT(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, db.nama_brng,"
                        + "CONCAT(dpo.jml ,' ',LOWER(db.kode_sat)) jlh FROM detail_pemberian_obat dpo "
                        + "INNER JOIN databarang db ON dpo.kode_brng=db.kode_brng "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=dpo.no_rawat WHERE "
                        + "dpo.tgl_perawatan='" + tglPemberianObat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.status_lanjut='ralan'");
            }
            
            try {
                rsRiwIO = psRiwIO.executeQuery();
                while (rsRiwIO.next()) {
                    tabModeRiwItemObat.addRow(new Object[]{
                        rsRiwIO.getString("tanggal"),
                        rsRiwIO.getString("nama_brng"),
                        rsRiwIO.getString("jlh")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsRiwIO != null) {
                    rsRiwIO.close();
                }
                if (psRiwIO != null) {
                    psRiwIO.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataRiwObat() {
        tglPemberianObat = "";
        if (tbTglBeriObat.getSelectedRow() != -1) {
            tglPemberianObat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 0).toString();
            tampilRiwItemObat();
        }
    }
    
    private void getDataItemObat() {
        if (tbItemObat.getSelectedRow() != -1) {
            TResepObat.setText(tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 1).toString());
            TResepObat.requestFocus();
        }
    }
    
    private void contengResep() {
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                tbResepObat.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }
    
    private void tampilHasilRadiologi() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                if (ChkTanggal.isSelected() == true) {
                    rsrad = koneksi.prepareStatement("SELECT rp.no_rawat, concat(date_format(rp.tgl_registrasi, '%d-%m-%Y'),', Jam : ',date_format(rp.jam_reg,'%H:%i %p')) tglKun, "
                            + "if(rp.status_lanjut='ralan', concat(ifnull(pl.nm_poli,'-'),' (',ifnull(d.nm_dokter,'-'),')'), "
                            + "CONCAT((SELECT l.nm_bangsal FROM kamar_inap k INNER JOIN kamar b ON b.kd_kamar=k.kd_kamar INNER JOIN bangsal l ON l.kd_bangsal=b.kd_bangsal "
                            + "WHERE k.no_rawat=rp.no_rawat AND k.stts_pulang<>'Pindah Kamar'),' (',dd.nm_dokter,')')) polidokter, concat(ifnull(pj.png_jawab,'-')) crbyr, "
                            + "if(rp.status_lanjut='ralan','Rawat Jalan','Rawat Inap') stts_rawat FROM reg_periksa rp "
                            + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                            + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN periksa_radiologi pr on pr.no_rawat=rp.no_rawat INNER JOIN dokter dd ON dd.kd_dokter=pr.dokter_perujuk "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam WHERE "
                            + "rp.no_rkm_medis = '" + TNoRM.getText() + "' AND pr.tgl_periksa BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "GROUP BY rp.no_rawat ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                } else {
                    rsrad = koneksi.prepareStatement("select * from (SELECT rp.no_rawat, concat(date_format(rp.tgl_registrasi, '%d-%m-%Y'),', Jam : ',date_format(rp.jam_reg,'%H:%i %p')) tglKun, "
                            + "if(rp.status_lanjut='ralan', concat(ifnull(pl.nm_poli,'-'),' (',ifnull(d.nm_dokter,'-'),')'), "
                            + "CONCAT((SELECT l.nm_bangsal FROM kamar_inap k INNER JOIN kamar b ON b.kd_kamar=k.kd_kamar INNER JOIN bangsal l ON l.kd_bangsal=b.kd_bangsal "
                            + "WHERE k.no_rawat=rp.no_rawat AND k.stts_pulang<>'Pindah Kamar'),' (',dd.nm_dokter,')')) polidokter, concat(ifnull(pj.png_jawab,'-')) crbyr, "
                            + "if(rp.status_lanjut='ralan','Rawat Jalan','Rawat Inap') stts_rawat, pr.tgl_periksa FROM reg_periksa rp "
                            + "INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                            + "INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter INNER JOIN periksa_radiologi pr on pr.no_rawat=rp.no_rawat INNER JOIN dokter dd ON dd.kd_dokter=pr.dokter_perujuk "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam WHERE "
                            + "rp.no_rkm_medis = '" + TNoRM.getText() + "' GROUP BY rp.no_rawat ORDER BY pr.tgl_periksa desc, pr.jam desc limit 5) as a order by a.tgl_periksa").executeQuery();
                }

                urut = 1;
                while (rsrad.next()) {
                    htmlContent.append(                            
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "    <td valign='top' rowspan='5' width='20px'>" + urut + ".</td>"
                            + "    <td width='160px'>No. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("no_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Tgl. Kunj./Tgl. MRS</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("tglKun") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Jenis Perawatan</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("stts_rawat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "    <td>Poliklinik/Inst./Rg. Rawat</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("polidokter") + "</td>"
                            + "</tr>"
                            +"<tr class='isi'>"
                            + "    <td>Cara Bayar</td>"
                            + "    <td colspan='4'>: " + rsrad.getString("crbyr") + "</td>"
                            + "</tr>"
                            + "</thead>"       
                            + "</table>"
                    );
                    urut++;
                    
                    //hasil pemeriksaan radiolgi
                    try {
                        rshslRad = koneksi.prepareStatement("SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(d.nm_dokter,'-') drRad, ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr "
                                + "INNER JOIN dokter d on d.kd_dokter=pr.kd_dokter INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                + "WHERE pr.no_rawat='" + rsrad.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();

                        if (rshslRad.next()) {
                            htmlContent.append(
                                    "<table width='100%' class='isi'>"
                                    + "<thead>"
                                    + "<tr class='isi'>"
                                    + "    <td width='20px'></td>"
                                    + "    <td width='100px' bgcolor='#f8fdf3'><b>Tgl. Pemeriksaan</b></td>"
                                    + "    <td width='50px' bgcolor='#f8fdf3'><b>Jam</b></td>"                                    
                                    + "    <td width='150px' bgcolor='#f8fdf3'><b>Nama Pemeriksaan</b></td>"
                                    + "    <td width='180px' bgcolor='#f8fdf3'><b>Dokter Radiologi</b></td>"
                                    + "    <td bgcolor='#f8fdf3'><b>Bacaan/Hasil Pemeriksaan</b></td>"
                                    + "  </tr>"
                                    + "</thead>"
                                    + "<tbody>"
                            );
                            
                            rshslRad.beforeFirst();
                            while (rshslRad.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td width='20px'></td>"
                                        + "<td valign='top' width='100px'>" + rshslRad.getString("tgl_periksa") + "</td>"
                                        + "<td valign='top' width='50px'>" + rshslRad.getString("jam") + "</td>"                                        
                                        + "<td valign='top' width='150px'>" + rshslRad.getString("nm_pemeriksaan") + "</td>"
                                        + "<td valign='top' width='180px'>" + rshslRad.getString("drRad") + "</td>"
                                        + "<td valign='top'>" + rshslRad.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                        + "</tr>"                                        
                                );
                            }
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td colspan='6' bgcolor='#7eccb9'></td>"
                                    + "</tr>"
                                    + "</tbody>"                                          
                                    + "</table>"
                            );
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rshslRad != null) {
                            rshslRad.close();
                        }
                    }
                }
                
                LoadHTML4.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsrad != null) {
                    rsrad.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilPRMRJ() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                if (ChkTanggal.isSelected() == true) {
                    rsprmrj = koneksi.prepareStatement("select rp.no_rawat, date_format(rp.tgl_registrasi, '%d-%m-%Y') tglKun, pr.diagnosa, "
                            + "concat(ifnull(py.kd_penyakit,'-'),' - ',ifnull(py.ciri_ciri,'-')) icd10, ifnull(GROUP_CONCAT(DISTINCT ' ',jr.nm_perawatan),'-') pemrk_rad, "
                            + "ifnull(GROUP_CONCAT(DISTINCT ' ',tl.Pemeriksaan),'-') pemrk_lab, ifnull(GROUP_CONCAT(DISTINCT ' ',cr.nama_obat),'-') obat, d.nm_dokter dpjp "
                            + "FROM reg_periksa rp INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                            + "INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat LEFT JOIN catatan_resep cr on cr.no_rawat=rp.no_rawat "
                            + "LEFT JOIN periksa_radiologi prad on prad.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_radiologi jr on jr.kd_jenis_prw=prad.kd_jenis_prw "
                            + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=rp.no_rawat and dp.prioritas='1' LEFT JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                            + "LEFT JOIN detail_periksa_lab dl on dl.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_lab jl on jl.kd_jenis_prw=dl.kd_jenis_prw "
                            + "LEFT JOIN template_laboratorium tl on tl.id_template=dl.id_template WHERE "
                            + "rp.no_rkm_medis = '" + TNoRM.getText() + "' AND pr.tgl_perawatan BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                            + "and pr.stts_prmrj='1' and rp.status_lanjut='ralan' GROUP BY rp.no_rawat ORDER BY pr.tgl_perawatan, pr.jam_rawat desc").executeQuery();
                } else {
                    rsprmrj = koneksi.prepareStatement("select * from (select rp.no_rawat, date_format(rp.tgl_registrasi, '%d-%m-%Y') tglKun, pr.diagnosa, "
                            + "concat(ifnull(py.kd_penyakit,'-'),' - ',ifnull(py.ciri_ciri,'-')) icd10, ifnull(GROUP_CONCAT(DISTINCT ' ',jr.nm_perawatan),'-') pemrk_rad, "
                            + "ifnull(GROUP_CONCAT(DISTINCT ' ',tl.Pemeriksaan),'-') pemrk_lab, ifnull(GROUP_CONCAT(DISTINCT ' ',cr.nama_obat),'-') obat, d.nm_dokter dpjp, pr.tgl_perawatan "
                            + "FROM reg_periksa rp INNER JOIN poliklinik pl on pl.kd_poli=rp.kd_poli INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN dokter d on d.kd_dokter=rp.kd_dokter "
                            + "INNER JOIN pemeriksaan_ralan pr on pr.no_rawat=rp.no_rawat LEFT JOIN catatan_resep cr on cr.no_rawat=rp.no_rawat "
                            + "LEFT JOIN periksa_radiologi prad on prad.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_radiologi jr on jr.kd_jenis_prw=prad.kd_jenis_prw "
                            + "LEFT JOIN diagnosa_pasien dp on dp.no_rawat=rp.no_rawat and dp.prioritas='1' LEFT JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit "
                            + "LEFT JOIN detail_periksa_lab dl on dl.no_rawat=rp.no_rawat LEFT JOIN jns_perawatan_lab jl on jl.kd_jenis_prw=dl.kd_jenis_prw "
                            + "LEFT JOIN template_laboratorium tl on tl.id_template=dl.id_template WHERE "
                            + "rp.no_rkm_medis = '" + TNoRM.getText() + "' and pr.stts_prmrj='1' and rp.status_lanjut='ralan' "
                            + "GROUP BY rp.no_rawat ORDER BY pr.tgl_perawatan desc, pr.jam_rawat desc limit 5) as a order by a.tgl_perawatan").executeQuery();
                }

                urut = 1;
                if (rsprmrj.next()) {
                    htmlContent.append(                            
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>No.</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Tgl. Kunjungan</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Diagnosis</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>ICD-10</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Pemeriksaan Penunjang</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Obat - Obatan</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Riwayat MRS &<br>Kunjungan Terakhir</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Prosedur Bedah / Operasi<br>Sejak Kunjungan Terakhir</b></td>"
                            + "    <td align='center' bgcolor='#f8fdf3'><b>Nama DPJP</b></td>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>"
                    );                    

                    rsprmrj.beforeFirst();
                    while (rsprmrj.next()) {
                        htmlContent.append(
                                "<tr class='isi'>"
                                + "<td valign='top' width='20px'>" + urut + ".</td>"
                                + "<td valign='top' align='center' width='50px'>" + rsprmrj.getString("tglKun") + "</td>"
                                + "<td valign='top'>" + rsprmrj.getString("diagnosa") + "</td>"
                                + "<td valign='top'>" + rsprmrj.getString("icd10") + "</td>"
                                + "<td valign='top'>" + rsprmrj.getString("pemrk_rad") + ", " + rsprmrj.getString("pemrk_lab") + "</td>"
                                + "<td valign='top'>" + rsprmrj.getString("obat") + "</td>"
                                + "<td></td>"
                                + "<td></td>"
                                + "<td valign='top'>" + rsprmrj.getString("dpjp") + "</td>"
                                + "</tr>"
                        );
                        urut++;
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>"
                    );                    
                }                
                LoadHTML5.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsprmrj != null) {
                    rsprmrj.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilAwalMedisMata() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE "
                        + "p.no_rkm_medis = '" + TCari.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();
                x = 1;
                while (rs8.next()) {
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs8.getString("no_rkm_medis") + "' AND rp.kd_poli='MAT' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg desc").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("select * from (SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab, rp.tgl_registrasi tglReg FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rs8.getString("no_rkm_medis") + "' AND rp.kd_poli='MAT' "
                                    + "ORDER BY rp.tgl_registrasi desc, rp.jam_reg desc limit 3) as a order by a.tglReg").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;No. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Registrasi</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_dokter") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penanggung Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alamat P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("almt_pj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hubungan P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("hubunganpj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + "</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan penilaian awal medis rawat jalan mata
                            try {
                                rs3 = koneksi.prepareStatement("select date_format(pm.tanggal,'%d-%m-%Y') tanggal,pm.kd_dokter,pm.anamnesis,pm.hubungan, "
                                        + "pm.keluhan_utama,pm.rps,pm.rpd,pm.rpo,pm.alergi, pm.status,pm.td,pm.nadi,pm.rr,pm.suhu, pm.nyeri,pm.bb,pm.visuskanan,"
                                        + "pm.visuskiri,pm.cckanan, pm.cckiri,pm.palkanan,pm.palkiri,pm.conkanan,pm.conkiri, pm.corneakanan,pm.corneakiri,"
                                        + "pm.coakanan,pm.coakiri, pm.pupilkanan,pm.pupilkiri,pm.lensakanan,pm.lensakiri, pm.funduskanan,pm.funduskiri,"
                                        + "pm.papilkanan,pm.papilkiri, pm.retinakanan,pm.retinakiri,pm.makulakanan,pm.makulakiri, pm.tiokanan,pm.tiokiri,"
                                        + "pm.mbokanan,pm.mbokiri,pm.lab, pm.rad,pm.penunjang,pm.tes,pm.pemeriksaan, pm.diagnosis,pm.diagnosisbdg,pm.permasalahan,"
                                        + "pm.terapi, pm.tindakan, pm.edukasi,d.nm_dokter FROM penilaian_medis_ralan_mata pm "
                                        + "INNER JOIN dokter d ON pm.kd_dokter = d.kd_dokter WHERE pm.no_rawat = '" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penilaian Awal Medis Rawat Jalan Mata</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"                                                
                                                + "<td valign='top'><b>"
                                                + "YANG MELAKUKAN PENGKAJIAN</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='33%' border='0'>Tanggal : " + rs3.getString("tanggal") + "</td>"
                                                + "<td width='33%' border='0'>Nama Dokter : " + rs3.getString("nm_dokter") + "</td>"
                                                + "<td width='33%' border='0'>Anamnesis : " + rs3.getString("anamnesis") + (rs3.getString("hubungan").equals("") ? "" : ", " + rs3.getString("hubungan")) + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "I. RIWAYAT KESEHATAN</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td colspan='2'>Keluhan Utama : " + rs3.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penyakit Sekarang : " + rs3.getString("rps").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Penyakit Dahulu : " + rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='50%'>Riwayat Penggunaan Obat : " + rs3.getString("rpo").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td width='50%'>Riwayat Alergi : " + rs3.getString("alergi") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "II. PEMERIKSAAN FISIK</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='20%' border='0'>TD : " + rs3.getString("td") + " mmHg</td>"
                                                + "<td width='20%' border='0'>BB : " + rs3.getString("bb") + " Kg</td>"
                                                + "<td width='20%' border='0'>Suhu : " + rs3.getString("suhu") + " °C</td>"
                                                + "<td width='20%' border='0'>Nadi : " + rs3.getString("nadi") + " x/menit</td>"
                                                + "<td width='20%' border='0'>RR : " + rs3.getString("rr") + " x/menit</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td border='0' colspan='2'>Nyeri : " + rs3.getString("nyeri") + "</td>"
                                                + "<td border='0' colspan='3'>Status Nutrisi : " + rs3.getString("status") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "III. STATUS OFTAMOLOGIS</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='40%' bgcolor='#f8fdf3'><b>OD : Mata Kanan</b></td>"
                                                + "<td valign='top' width='20%' bgcolor='#f8fdf3'><b>Status</b></td>"
                                                + "<td valign='top' width='40%' bgcolor='#f8fdf3'><b>OS : Mata Kiri</b></td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("visuskanan") + "</td>"
                                                + "<td>Visus SC</td>"
                                                + "<td>" + rs3.getString("visuskiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("cckanan") + "</td>"
                                                + "<td>CC</td>"
                                                + "<td>" + rs3.getString("cckiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("palkanan") + "</td>"
                                                + "<td>Palpebra</td>"
                                                + "<td>" + rs3.getString("palkiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("conkanan") + "</td>"
                                                + "<td>Conjungtiva</td>"
                                                + "<td>" + rs3.getString("conkiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("corneakanan") + "</td>"
                                                + "<td>Cornea</td>"
                                                + "<td>" + rs3.getString("corneakiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("coakanan") + "</td>"
                                                + "<td>COA</td>"
                                                + "<td>" + rs3.getString("coakiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("pupilkanan") + "</td>"
                                                + "<td>Pupil</td>"
                                                + "<td>" + rs3.getString("pupilkiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("lensakanan") + "</td>"
                                                + "<td>Lensa</td>"
                                                + "<td>" + rs3.getString("lensakiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("funduskanan") + "</td>"
                                                + "<td>Fundus Media</td>"
                                                + "<td>" + rs3.getString("funduskiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("papilkanan") + "</td>"
                                                + "<td>Papil</td>"
                                                + "<td>" + rs3.getString("papilkiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("retinakanan") + "</td>"
                                                + "<td>Retina</td>"
                                                + "<td>" + rs3.getString("retinakiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("makulakanan") + "</td>"
                                                + "<td>Makula</td>"
                                                + "<td>" + rs3.getString("makulakiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("tiokanan") + "</td>"
                                                + "<td>TIO</td>"
                                                + "<td>" + rs3.getString("tiokiri") + "</td>"
                                                + "</tr>"
                                                + "<tr align='center'>"
                                                + "<td>" + rs3.getString("mbokanan") + "</td>"
                                                + "<td>MBO</td>"
                                                + "<td>" + rs3.getString("mbokiri") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "IV. PEMERIKSAAN PENUNJANG</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%'>Laboratorium : " + rs3.getString("lab").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%'>Radiologi : " + rs3.getString("rad").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%'>Penunjang Lainnya : " + rs3.getString("penunjang").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%'>Tes Penglihatan : " + rs3.getString("tes").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%'>Pemeriksaan Lain : " + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "V. DIAGNOSIS/ASESMEN</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%'>Asesmen Kerja : " + rs3.getString("diagnosis").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%'>Asesmen Banding : " + rs3.getString("diagnosisbdg").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "VI. PERMASALAHAN & TATALAKSANA</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>Permasalahan : " + rs3.getString("permasalahan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>Terapi/Pengobatan : " + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>Tindakan/Rencana Tindakan : " + rs3.getString("tindakan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>"
                                                + "<tr>"
                                                + "<td valign='top'><b>"
                                                + "VII. EDUKASI</b>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"
                                                + "<tr>"
                                                + "<td width='100%' border='0'>" + rs3.getString("edukasi").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "</tr>"
                                                + "</table>"
                                                + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML6.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void tampilHasilLaboratorium() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            if (ChkTanggal.isSelected() == true) {
                rs = koneksi.prepareStatement("select * from ("
                        +"(select *, waktu_reg_lab 'waktu' ,date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam, date_format(pasien_tgl_lahir,'%d-%m-%Y') tgllhr "
                        + "from lis_hasil_data_pasien where pasien_no_rm='" + TNoRM.getText() + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc) as a ) order by a.waktu").executeQuery();
            } else {
                rs = koneksi.prepareStatement("select * from ("
                        +"(select *,waktu_reg_lab 'waktu' ,date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam, date_format(pasien_tgl_lahir,'%d-%m-%Y') tgllhr "
                        + "from lis_hasil_data_pasien where pasien_no_rm='" + TNoRM.getText() + "' ORDER BY waktu_reg_lab desc, no_lab desc limit 3) as a ) order by a.waktu").executeQuery();
            }
            try {
                urut = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pasien_no_rm") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgllhr") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar/Penjamin</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("penjamin") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rg. Rawat/Poli./Inst.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("unit_asal") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter Pengirim</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("dokter_pengirim") + "</td>"
                            + "</tr>"
                    );
                    urut++;

                    //hasil pemeriksaan laboratorium LIS
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rsLISMaster = koneksi.prepareStatement(
                                    "select *, date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam from lis_hasil_data_pasien where "
                                    + "no_lab='" + rs.getString("no_lab") + "' and date(waktu_reg_lab) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' "
                                    + "and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY waktu_reg_lab desc, no_lab desc").executeQuery();
                        } else {
                            rsLISMaster = koneksi.prepareStatement(
                                    "select *, date_format(waktu_reg_lab,'%d-%m-%Y') tgl, time_format(waktu_reg_lab,'%H:%i') jam from lis_hasil_data_pasien where "
                                    + "no_lab='" + rs.getString("no_lab") + "' ORDER BY waktu_reg_lab desc, no_lab desc limit 3").executeQuery();
                        }
                        if (rsLISMaster.next()) {
                            rsLISMaster.beforeFirst();
                            lisM = 1;
                            while (rsLISMaster.next()) {
                                htmlContent.append(
                                        "<tr class='isi'>"
                                        + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                        + "<td valign='top' width='1%' align='center'>:</td>"
                                        + "<td valign='top' width='79%'>"
                                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                        + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + ", Tgl. Periksa : " + rsLISMaster.getString("tgl") + ", Jam : " + rsLISMaster.getString("jam") + "</td></td></tr>"
                                        + "<tr align='center'>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                        + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                        + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                        + "</tr>"
                                );

                                rsLIS1 = koneksi.prepareStatement(
                                        "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                        + "WHERE no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY kategori_pemeriksaan_nama "
                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();

                                if (rsLIS1.next()) {
                                    rsLIS1.beforeFirst();
                                    w = 1;
                                    while (rsLIS1.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                + "</tr>");

                                        rsLIS2 = koneksi.prepareStatement("SELECT ifnull(sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_hasil_periksa_lab "
                                                + "WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                + "GROUP BY sub_kategori_pemeriksaan_nama ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, "
                                                + "sub_kategori_pemeriksaan_nama desc, pemeriksaan_no_urut").executeQuery();
                                        if (rsLIS2.next()) {
                                            rsLIS2.beforeFirst();
                                            lis1 = 1;
                                            while (rsLIS2.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS3 = koneksi.prepareStatement("SELECT ifnull(pemeriksaan_nama,'') pemeriksaan_nama, metode, nilai_hasil, nilai_rujukan, "
                                                        + "satuan, flag_kode FROM lis_hasil_periksa_lab WHERE no_lab='" + rsLISMaster.getString("no_lab") + "' and "
                                                        + "sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' and "
                                                        + "kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' GROUP BY pemeriksaan_nama "
                                                        + "ORDER BY kategori_pemeriksaan_no_urut, sub_kategori_pemeriksaan_no_urut, pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS3.next()) {
                                                    rsLIS3.beforeFirst();
                                                    lis2 = 1;
                                                    while (rsLIS3.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                + "</tr>");
                                                        lis2++;
                                                    }
                                                }
                                                lis1++;
                                            }
                                        }
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table><br/>");
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsLIS1 != null) {
                            rsLIS1.close();
                        }
                    }
                    htmlContent.append(
                            "</td>"
                            + "</tr>"
                    );
                    htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }
            LoadHTML7.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(350, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
}
