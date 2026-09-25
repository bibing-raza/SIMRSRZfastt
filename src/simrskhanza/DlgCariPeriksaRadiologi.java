package simrskhanza;

import keuangan.Jurnal;
import fungsi.WarnaTable;
import fungsi.WarnaTableRadiologi;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import keuangan.DlgKamar;
import laporan.DlgKirimWhatsapp;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rekammedis.RMDokumenPenunjangMedis;

public class DlgCariPeriksaRadiologi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    private DlgCariPoli poli;
    private DlgCariDokter dokter;
    private DlgRujukMasuk rujukmasuk;
    private DlgCariPetugas petugas;
    private DlgPenanggungJawab penjab;
    private DlgKamar kamarnya;
    private final Properties prop = new Properties();
    private int i, x = 0, pilihanDokter = 0, cekRujukan = 0, n = 0, cekHasil = 0, cekPeriksa = 0, pilihPetugas = 0, dipilih = 0;
    private PreparedStatement ps, ps2, ps3, ps4, ps5, ps6, ps7, ps8, ps9, ps10, ps11, psrekening, psItem;
    private ResultSet rs, rs2, rs3, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rsrekening, rsItem;
    private String kamar, namakamar, pemeriksaan = "", pilihan = "", status = "", tglperiksa = "", jam = "", sttsRawat = "",
            tglhasil = "", jamhasil = "", lihatHasil = "", kodeRujukan = "", tgldaftar = "", tglnoRW = "", alamatFaskes = "",
            kdItem = "", cekDiagnos = "", kd_kamar = "", pilihMenu = "", kodepoliUnit = "";
    private double ttl = 0, item = 0, cekRad;
    private double ttljmdokter = 0, ttljmpetugas = 0, ttlkso = 0, ttlpendapatan = 0, ttlbhp = 0;
    private String Suspen_Piutang_Radiologi_Ranap = "", Radiologi_Ranap = "", Beban_Jasa_Medik_Dokter_Radiologi_Ranap = "",
            Utang_Jasa_Medik_Dokter_Radiologi_Ranap = "", Beban_Jasa_Medik_Petugas_Radiologi_Ranap = "", dialog_simpan = "",
            Utang_Jasa_Medik_Petugas_Radiologi_Ranap = "", Beban_Kso_Radiologi_Ranap = "", Utang_Kso_Radiologi_Ranap = "",
            HPP_Persediaan_Radiologi_Rawat_Inap = "", Persediaan_BHP_Radiologi_Rawat_Inap = "", cekDataRad = "", khususIgd = "",
            dokterBaca = "", cekJamSelesai = "", cekDurasi = "", tte = "", cariDataIgd = "", CariData = "", cariBayar = "",
            nipPtgs = "", nipPenerimaLap = "", cekPemeriksaan = "", cekLapor = "", cekLapDit = "";
    private frmUtama formUtama;

    /**
     * Creates new form DlgProgramStudi
     *
     * @param parent
     * @param modal
     */
    public DlgCariPeriksaRadiologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        Object[] row = {"No. Rawat", "Pasien", "Nama Petugas", "Tgl. Periksa", "Jam Input", "Dokter Perujuk/Pengirim",
            "Dokter Pemeriksa Radiologi", "kddokterRad", "jnsRawat", "kddokterPengirim", "nipPetugas", "Parameter Pemeriksaan",
            "nilai_kv", "nilai_mas", "nilai_ma", "nilai_fase", "nilai_dlp", "warna", "Kategori Pelayanan", "unit_pengirim"};
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPeriksaRadiologi.setModel(tabMode);
        tbPeriksaRadiologi.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbPeriksaRadiologi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 20; i++) {
            TableColumn column = tbPeriksaRadiologi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(450);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
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
                column.setPreferredWidth(200);
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
                column.setPreferredWidth(115);
            } else if (i == 19) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
//        tbPeriksaRadiologi.setDefaultRenderer(Object.class, new WarnaTable());
        tbPeriksaRadiologi.setDefaultRenderer(Object.class, new WarnaTableRadiologi());

        tabMode1 = new DefaultTableModel(null, new Object[]{"No.", "Nama Pemeriksaan", "Tgl. Periksa", "Jam Periksa",
            "Tarif PerBup", "noRawat", "kdjnsPemeriksaan", "tglperiksaData"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbPemeriksaan.setModel(tabMode1);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(270);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{"No.", "Nama Pemeriksaan", "Tgl. Periksa", "Jam Periksa",
            "Tarif PerBup", "noRawat", "kdjnsPemeriksaan", "tglperiksaData"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbDataPeriksa.setModel(tabMode2);
        tbDataPeriksa.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDataPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbDataPeriksa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(270);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDataPeriksa.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbDataPeriksa.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbDataPeriksa.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbDataPeriksa.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabMode3 = new DefaultTableModel(null, new String[]{"No. Rawat", "Tgl. Periksa", "Jam Periksa", "Nama Pemeriksaan",
            "Diagnosa Klinis Rad.", "tgl_periksa", "hasil", "kd_jenis_prw", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbExpertise.setModel(tabMode3);
        tbExpertise.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbExpertise.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbExpertise.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbExpertise.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbExpertise.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbExpertise.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbExpertise.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode4 = new DefaultTableModel(null, new String[]{"No.", "Tgl. Periksa", "Jam Input", "Nama Pemeriksaan",
            "tgl_periksa", "kd_jenis_prw"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbItem.setModel(tabMode4);
        tbItem.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 6; i++) {
            TableColumn column = tbItem.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(270);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItem.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbItem.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbItem.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbItem.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tabMode5 = new DefaultTableModel(null, new String[]{"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Periksa", "Jam Input", "Nama Petugas", "Nama Pemeriksaan", "Jam Pmrksaan. Selesai",
            "Jam Lapor", "Jam Lap. Diterima", "Petugas Penerima Lap.", "Keterangan",
            "kd_jenis_prw", "tgl_periksa", "jam", "nip_petugas", "cek_pemeriksaan", "jam_pemeriksaan", "cek_lapor", "jam_lapor", "cek_lap_diterima", "jam_lap_diterima",
            "nip_penerima_lap", "keterangan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbKritis.setModel(tabMode5);
        tbKritis.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKritis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 25; i++) {
            TableColumn column = tbKritis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(130);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(250);
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
        tbKritis.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbKritis.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbKritis.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        
        tabMode6 = new DefaultTableModel(null, new String[]{"No.", "Tgl. Periksa", "Jam Input", "Nama Pemeriksaan",
            "Jam Pemeriksaan", "tgl_periksa", "kd_jenis_prw", "jam_pemeriksaan", "no_rawat"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbItem1.setModel(tabMode6);
        tbItem1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbItem1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbItem1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbItem1.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbItem1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbItem1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbItem1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbItem1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        TNoRW.setDocument(new batasInput((byte) 17).getKata(TNoRW));
        TnoRM.setDocument(new batasInput((byte) 8).getKata(TnoRM));
        Tbb.setDocument(new batasInput((byte) 10).getKata(Tbb));
        TnilaiKv.setDocument(new batasInput((byte) 20).getKata(TnilaiKv));        
        TnilaiMas.setDocument(new batasInput((byte) 20).getKata(TnilaiMas));
        TnilaiMa.setDocument(new batasInput((byte) 20).getKata(TnilaiMa));
        TnilaiFase.setDocument(new batasInput((byte) 20).getKata(TnilaiFase));
        TnilaiDlp.setDocument(new batasInput((byte) 20).getKata(TnilaiDlp));
        Tketerangan.setDocument(new batasInput((int) 255).getKata(Tketerangan));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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

        try {
            StringBuilder sb6 = new StringBuilder();
            sb6.append("select * from set_akun_ranap");
            psrekening = koneksi.prepareStatement(sb6.toString());

            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Radiologi_Ranap = rsrekening.getString("Suspen_Piutang_Radiologi_Ranap");
                    Radiologi_Ranap = rsrekening.getString("Radiologi_Ranap");
                    Beban_Jasa_Medik_Dokter_Radiologi_Ranap = rsrekening.getString("Beban_Jasa_Medik_Dokter_Radiologi_Ranap");
                    Utang_Jasa_Medik_Dokter_Radiologi_Ranap = rsrekening.getString("Utang_Jasa_Medik_Dokter_Radiologi_Ranap");
                    Beban_Jasa_Medik_Petugas_Radiologi_Ranap = rsrekening.getString("Beban_Jasa_Medik_Petugas_Radiologi_Ranap");
                    Utang_Jasa_Medik_Petugas_Radiologi_Ranap = rsrekening.getString("Utang_Jasa_Medik_Petugas_Radiologi_Ranap");
                    Beban_Kso_Radiologi_Ranap = rsrekening.getString("Beban_Kso_Radiologi_Ranap");
                    Utang_Kso_Radiologi_Ranap = rsrekening.getString("Utang_Kso_Radiologi_Ranap");
                    HPP_Persediaan_Radiologi_Rawat_Inap = rsrekening.getString("HPP_Persediaan_Radiologi_Rawat_Inap");
                    Persediaan_BHP_Radiologi_Rawat_Inap = rsrekening.getString("Persediaan_BHP_Radiologi_Rawat_Inap");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : " + e);
            } finally {
                if (rsrekening != null) {
                    rsrekening.close();
                }
                if (psrekening != null) {
                    psrekening.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
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

        Kd2 = new widget.TextBox();
        NoBalasan = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnCetakNota = new javax.swing.JMenu();
        MnTTDnota = new javax.swing.JMenuItem();
        MnTTEnota = new javax.swing.JMenuItem();
        MnKirimKeWhatsappNota = new javax.swing.JMenuItem();
        MnCetakBuktiReg = new javax.swing.JMenu();
        MnTTDreg = new javax.swing.JMenuItem();
        MnTTEreg = new javax.swing.JMenuItem();
        MnHasilPemeriksaan = new javax.swing.JMenuItem();
        MnRiwayatPerawatan = new javax.swing.JMenuItem();
        MnDokumenPenunjangMedis = new javax.swing.JMenuItem();
        MnRiwayatExpertise = new javax.swing.JMenuItem();
        MnNilaiKritis = new javax.swing.JMenuItem();
        MnGantiData = new javax.swing.JMenu();
        MnJamPemeriksaan = new javax.swing.JMenuItem();
        MnBeratBadan = new javax.swing.JMenuItem();
        MnParamaterPemeriksaan = new javax.swing.JMenuItem();
        MnDokterPemeriksaRad = new javax.swing.JMenuItem();
        MnDokterPengirim = new javax.swing.JMenuItem();
        MnPetugasRadiologi = new javax.swing.JMenuItem();
        MnPemeriksaanRadiologi = new javax.swing.JMenuItem();
        MnWaktuPeriksa = new javax.swing.JMenuItem();
        MnAsalRujukan = new javax.swing.JMenuItem();
        MnUnitPengirim = new javax.swing.JMenuItem();
        MnExportDataKeExcel = new javax.swing.JMenu();
        MnTglRegKhususIGD = new javax.swing.JMenuItem();
        MnTglReg = new javax.swing.JMenuItem();
        MnTglPemeriksaan = new javax.swing.JMenuItem();
        jMnLapInap = new javax.swing.JMenu();
        MnInapSemuaCaraBayar = new javax.swing.JMenuItem();
        MnInapDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnInapPerCaraBayar = new javax.swing.JMenuItem();
        MnInapDetailPerCaraBayar = new javax.swing.JMenuItem();
        jMnLapJalan = new javax.swing.JMenu();
        MnJalanSemuaCaraBayar = new javax.swing.JMenuItem();
        MnJalanDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnJalanPerCaraBayar = new javax.swing.JMenuItem();
        MnJalanDetailPerCaraBayar = new javax.swing.JMenuItem();
        MnJalanPerPoli = new javax.swing.JMenuItem();
        MnJalanDetailPerPoli = new javax.swing.JMenuItem();
        jMnLapSemuaRawat = new javax.swing.JMenu();
        MnSRSemuaCaraBayar = new javax.swing.JMenuItem();
        MnSRDetailSemuaCaraBayar = new javax.swing.JMenuItem();
        MnSRPerCaraBayar = new javax.swing.JMenuItem();
        MnSRDetailPerCaraBayar = new javax.swing.JMenuItem();
        jMnLapJenisPeriksaRad = new javax.swing.JMenu();
        MnRekapJenisPeriksaRadInap = new javax.swing.JMenuItem();
        MnRekapJenisPeriksaRadJalan = new javax.swing.JMenuItem();
        MnRekapJenisPeriksaRadSemua = new javax.swing.JMenuItem();
        jMnLapPendapatan = new javax.swing.JMenu();
        MnPendapatanRalan = new javax.swing.JMenuItem();
        MnPendapatanRanap = new javax.swing.JMenuItem();
        MnPendapatanSemuaRawat = new javax.swing.JMenuItem();
        jMnLapPemeriksaanDokter = new javax.swing.JMenu();
        MnRekapTotalPemeriksaan = new javax.swing.JMenuItem();
        MnRekapRincianPemeriksaan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnRiwayatPerawatan1 = new javax.swing.JMenuItem();
        MnKirimKeWhatsapp = new javax.swing.JMenuItem();
        jMnGantiData1 = new javax.swing.JMenu();
        MnDokterPemeriksaRad1 = new javax.swing.JMenuItem();
        MnDokterPengirim1 = new javax.swing.JMenuItem();
        MnWaktuPeriksa1 = new javax.swing.JMenuItem();
        MnAsalRujukan1 = new javax.swing.JMenuItem();
        WindowHasil = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnPrintQR = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnHapus1 = new widget.Button();
        jLabel10 = new widget.Label();
        cmbCetak = new widget.ComboBox();
        BtnPrint1 = new widget.Button();
        BtnCloseIn5 = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel21 = new widget.Label();
        diagKlinisRad = new widget.TextBox();
        jLabel22 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        jLabel23 = new widget.Label();
        tglperiksarad = new widget.TextBox();
        jLabel24 = new widget.Label();
        jamperiksarad = new widget.TextBox();
        labelunit = new widget.Label();
        rg_poli = new widget.TextBox();
        jLabel25 = new widget.Label();
        dokterRad = new widget.TextBox();
        jLabel26 = new widget.Label();
        dokterPengirim = new widget.TextBox();
        jLabel27 = new widget.Label();
        asalRujukan = new widget.TextBox();
        jLabel42 = new widget.Label();
        TunitPengirim = new widget.TextBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel28 = new widget.Label();
        itemDipilih = new widget.TextBox();
        Scroll3 = new widget.ScrollPane();
        HasilPeriksa = new widget.TextArea();
        WindowGantiDokterRad = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel13 = new widget.Label();
        kddokter = new widget.TextBox();
        TDokter = new widget.TextBox();
        btnCariDokter = new widget.Button();
        WindowWaktuPeriksa = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        BtnCloseIn6 = new widget.Button();
        jLabel51 = new widget.Label();
        tanggalPeriksa = new widget.Tanggal();
        BtnSimpan2 = new widget.Button();
        jLabel52 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        WindowGantiDokterPerujuk = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        BtnCloseIn2 = new widget.Button();
        BtnSimpan3 = new widget.Button();
        jLabel14 = new widget.Label();
        kddokter1 = new widget.TextBox();
        TDokter1 = new widget.TextBox();
        btnCariDokter1 = new widget.Button();
        WindowGantiRujukan = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel15 = new widget.Label();
        nmFaskes = new widget.TextBox();
        btnCariFaskes = new widget.Button();
        WindowGantiPetugasRadiologi = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan5 = new widget.Button();
        jLabel16 = new widget.Label();
        kdpetugas = new widget.TextBox();
        TPetugas = new widget.TextBox();
        btnCariPetugas = new widget.Button();
        WindowRiwayatExpertise = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        internalFrame12 = new widget.InternalFrame();
        jLabel11 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel12 = new widget.Label();
        TnmPemeriksaan = new widget.TextBox();
        jLabel17 = new widget.Label();
        TtglPemeriksaan = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel20 = new widget.Label();
        TpemeriksaanExp = new widget.TextBox();
        TtglExp = new widget.TextBox();
        jLabel30 = new widget.Label();
        TjamPemeriksaan = new widget.TextBox();
        jLabel31 = new widget.Label();
        TjamExp = new widget.TextBox();
        internalFrame9 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbDataPeriksa = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        tbExpertise = new widget.Table();
        Scroll5 = new widget.ScrollPane();
        HasilPeriksa1 = new widget.TextArea();
        internalFrame10 = new widget.InternalFrame();
        BtnBatal = new widget.Button();
        BtnDisamakan = new widget.Button();
        BtnCari1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        WindowBeratBadan = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnSimpan6 = new widget.Button();
        jLabel32 = new widget.Label();
        Tbb = new widget.TextBox();
        WindowParameter = new javax.swing.JDialog();
        internalFrame14 = new widget.InternalFrame();
        BtnCloseIn8 = new widget.Button();
        BtnSimpan7 = new widget.Button();
        jLabel33 = new widget.Label();
        TnilaiKv = new widget.TextBox();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        TnilaiMas = new widget.TextBox();
        TnilaiMa = new widget.TextBox();
        TnilaiFase = new widget.TextBox();
        TnilaiDlp = new widget.TextBox();
        WindowNilaiKritis = new javax.swing.JDialog();
        internalFrame15 = new widget.InternalFrame();
        internalFrame16 = new widget.InternalFrame();
        jLabel38 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        Scroll6 = new widget.ScrollPane();
        tbItem = new widget.Table();
        jLabel39 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel50 = new widget.Label();
        TnmPetugasPenerima = new widget.TextBox();
        BtnPetugasPenerima = new widget.Button();
        jLabel53 = new widget.Label();
        Tketerangan = new widget.TextBox();
        chkSaya = new widget.CekBox();
        chkSaya1 = new widget.CekBox();
        chkPemeriksaan = new widget.CekBox();
        chkLapor = new widget.CekBox();
        chkLapDiterima = new widget.CekBox();
        cmbJam2 = new widget.ComboBox();
        cmbJam3 = new widget.ComboBox();
        cmbJam4 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        panelGlass9 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbKritis = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel54 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel55 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel8 = new widget.Label();
        LCount1 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan8 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnHapus2 = new widget.Button();
        BtnGanti = new widget.Button();
        BtnDownload = new widget.Button();
        BtnAll1 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        WindowJamPemeriksaan = new javax.swing.JDialog();
        internalFrame17 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        jLabel40 = new widget.Label();
        TNoRw2 = new widget.TextBox();
        TNoRM2 = new widget.TextBox();
        TPasien2 = new widget.TextBox();
        jLabel41 = new widget.Label();
        cmbJam5 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        Scroll7 = new widget.ScrollPane();
        tbItem1 = new widget.Table();
        panelGlass11 = new widget.panelisi();
        BtnSimpan9 = new widget.Button();
        BtnBatal2 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        WindowGantiUnitPengirim = new javax.swing.JDialog();
        internalFrame19 = new widget.InternalFrame();
        BtnCloseIn9 = new widget.Button();
        BtnSimpan10 = new widget.Button();
        jLabel43 = new widget.Label();
        TunitPengirimGanti = new widget.TextBox();
        btnCariUnit = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbPeriksaRadiologi = new widget.Table();
        panelisi3 = new widget.panelisi();
        label15 = new widget.Label();
        TNoRW = new widget.TextBox();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label16 = new widget.Label();
        TnoRM = new widget.TextBox();
        Tpasien = new widget.TextBox();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        BtnBersih = new widget.Button();
        jLabel19 = new widget.Label();
        kdpoli = new widget.TextBox();
        TPoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        label12 = new widget.Label();
        diagKlinis = new widget.TextBox();
        label14 = new widget.Label();
        cmbSttsTran = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnCariIgd = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnHapus = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel29 = new widget.Label();
        tglNota = new widget.Tanggal();

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        NoBalasan.setName("NoBalasan"); // NOI18N
        NoBalasan.setPreferredSize(new java.awt.Dimension(207, 23));

        jPopupMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(260, 400));
        jPopupMenu1.setRequestFocusEnabled(false);

        MnCetakNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakNota.setText("Cetak Nota Radiologi");
        MnCetakNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakNota.setName("MnCetakNota"); // NOI18N
        MnCetakNota.setOpaque(true);
        MnCetakNota.setPreferredSize(new java.awt.Dimension(260, 26));

        MnTTDnota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTDnota.setText("TTD Basah");
        MnTTDnota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDnota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDnota.setIconTextGap(5);
        MnTTDnota.setName("MnTTDnota"); // NOI18N
        MnTTDnota.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTDnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDnotaActionPerformed(evt);
            }
        });
        MnCetakNota.add(MnTTDnota);

        MnTTEnota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTEnota.setText("TTE (QRCode)");
        MnTTEnota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEnota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEnota.setIconTextGap(5);
        MnTTEnota.setName("MnTTEnota"); // NOI18N
        MnTTEnota.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTEnota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEnotaActionPerformed(evt);
            }
        });
        MnCetakNota.add(MnTTEnota);

        jPopupMenu1.add(MnCetakNota);

        MnKirimKeWhatsappNota.setBackground(new java.awt.Color(242, 242, 242));
        MnKirimKeWhatsappNota.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWhatsappNota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWhatsappNota.setText("Kirim Nota Ke WhatsApp");
        MnKirimKeWhatsappNota.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKirimKeWhatsappNota.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKirimKeWhatsappNota.setName("MnKirimKeWhatsappNota"); // NOI18N
        MnKirimKeWhatsappNota.setPreferredSize(new java.awt.Dimension(260, 26));
        MnKirimKeWhatsappNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWhatsappNotaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKirimKeWhatsappNota);

        MnCetakBuktiReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakBuktiReg.setText("Cetak Bukti Reg. Radiologi");
        MnCetakBuktiReg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakBuktiReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakBuktiReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakBuktiReg.setName("MnCetakBuktiReg"); // NOI18N
        MnCetakBuktiReg.setOpaque(true);
        MnCetakBuktiReg.setPreferredSize(new java.awt.Dimension(260, 26));

        MnTTDreg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTDreg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTDreg.setText("TTD Basah");
        MnTTDreg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTDreg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTDreg.setIconTextGap(5);
        MnTTDreg.setName("MnTTDreg"); // NOI18N
        MnTTDreg.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTDreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTDregActionPerformed(evt);
            }
        });
        MnCetakBuktiReg.add(MnTTDreg);

        MnTTEreg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTTEreg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnTTEreg.setText("TTE (QRCode)");
        MnTTEreg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTTEreg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTTEreg.setIconTextGap(5);
        MnTTEreg.setName("MnTTEreg"); // NOI18N
        MnTTEreg.setPreferredSize(new java.awt.Dimension(120, 26));
        MnTTEreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTTEregActionPerformed(evt);
            }
        });
        MnCetakBuktiReg.add(MnTTEreg);

        jPopupMenu1.add(MnCetakBuktiReg);

        MnHasilPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaan.setText("Hasil Pemeriksaan Radiologi");
        MnHasilPemeriksaan.setName("MnHasilPemeriksaan"); // NOI18N
        MnHasilPemeriksaan.setPreferredSize(new java.awt.Dimension(260, 26));
        MnHasilPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaan);

        MnRiwayatPerawatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerawatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatPerawatan.setText("Riwayat Perawatan");
        MnRiwayatPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatPerawatan.setName("MnRiwayatPerawatan"); // NOI18N
        MnRiwayatPerawatan.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatPerawatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerawatanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatPerawatan);

        MnDokumenPenunjangMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenPenunjangMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenPenunjangMedis.setText("Dokumen Penunjang Medis");
        MnDokumenPenunjangMedis.setName("Dokumen Penunjang Medis"); // NOI18N
        MnDokumenPenunjangMedis.setPreferredSize(new java.awt.Dimension(260, 26));
        MnDokumenPenunjangMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenPenunjangMedisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenPenunjangMedis);

        MnRiwayatExpertise.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatExpertise.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatExpertise.setText("Riwayat Hasil Expertise");
        MnRiwayatExpertise.setName("MnRiwayatExpertise"); // NOI18N
        MnRiwayatExpertise.setPreferredSize(new java.awt.Dimension(260, 26));
        MnRiwayatExpertise.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatExpertiseActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatExpertise);

        MnNilaiKritis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnNilaiKritis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnNilaiKritis.setText("Nilai Kritis Pemeriksaan");
        MnNilaiKritis.setName("MnNilaiKritis"); // NOI18N
        MnNilaiKritis.setPreferredSize(new java.awt.Dimension(260, 26));
        MnNilaiKritis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnNilaiKritisActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnNilaiKritis);

        MnGantiData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiData.setText("Ganti Data");
        MnGantiData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiData.setName("MnGantiData"); // NOI18N
        MnGantiData.setPreferredSize(new java.awt.Dimension(260, 26));

        MnJamPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJamPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJamPemeriksaan.setText("Jam Pemeriksaan");
        MnJamPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJamPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJamPemeriksaan.setIconTextGap(5);
        MnJamPemeriksaan.setName("MnJamPemeriksaan"); // NOI18N
        MnJamPemeriksaan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnJamPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJamPemeriksaanActionPerformed(evt);
            }
        });
        MnGantiData.add(MnJamPemeriksaan);

        MnBeratBadan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBeratBadan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBeratBadan.setText("Berat Badan Pasien");
        MnBeratBadan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBeratBadan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBeratBadan.setIconTextGap(5);
        MnBeratBadan.setName("MnBeratBadan"); // NOI18N
        MnBeratBadan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnBeratBadan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBeratBadanActionPerformed(evt);
            }
        });
        MnGantiData.add(MnBeratBadan);

        MnParamaterPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnParamaterPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnParamaterPemeriksaan.setText("Parameter Pemeriksaan");
        MnParamaterPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnParamaterPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnParamaterPemeriksaan.setIconTextGap(5);
        MnParamaterPemeriksaan.setName("MnParamaterPemeriksaan"); // NOI18N
        MnParamaterPemeriksaan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnParamaterPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnParamaterPemeriksaanActionPerformed(evt);
            }
        });
        MnGantiData.add(MnParamaterPemeriksaan);

        MnDokterPemeriksaRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterPemeriksaRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterPemeriksaRad.setText("Dokter Pemeriksa Radiologi");
        MnDokterPemeriksaRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterPemeriksaRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterPemeriksaRad.setIconTextGap(5);
        MnDokterPemeriksaRad.setName("MnDokterPemeriksaRad"); // NOI18N
        MnDokterPemeriksaRad.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDokterPemeriksaRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterPemeriksaRadActionPerformed(evt);
            }
        });
        MnGantiData.add(MnDokterPemeriksaRad);

        MnDokterPengirim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterPengirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterPengirim.setText("Dokter Pengirim / Perujuk");
        MnDokterPengirim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterPengirim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterPengirim.setIconTextGap(5);
        MnDokterPengirim.setName("MnDokterPengirim"); // NOI18N
        MnDokterPengirim.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDokterPengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterPengirimActionPerformed(evt);
            }
        });
        MnGantiData.add(MnDokterPengirim);

        MnPetugasRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPetugasRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPetugasRadiologi.setText("Petugas Pemeriksa Radiologi");
        MnPetugasRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPetugasRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPetugasRadiologi.setIconTextGap(5);
        MnPetugasRadiologi.setName("MnPetugasRadiologi"); // NOI18N
        MnPetugasRadiologi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPetugasRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPetugasRadiologiActionPerformed(evt);
            }
        });
        MnGantiData.add(MnPetugasRadiologi);

        MnPemeriksaanRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaanRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemeriksaanRadiologi.setText("Pemeriksaan Radiologi");
        MnPemeriksaanRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemeriksaanRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemeriksaanRadiologi.setIconTextGap(5);
        MnPemeriksaanRadiologi.setName("MnPemeriksaanRadiologi"); // NOI18N
        MnPemeriksaanRadiologi.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPemeriksaanRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemeriksaanRadiologiActionPerformed(evt);
            }
        });
        MnGantiData.add(MnPemeriksaanRadiologi);

        MnWaktuPeriksa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnWaktuPeriksa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnWaktuPeriksa.setText("Tgl. Pemeriksaan");
        MnWaktuPeriksa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnWaktuPeriksa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnWaktuPeriksa.setIconTextGap(5);
        MnWaktuPeriksa.setName("MnWaktuPeriksa"); // NOI18N
        MnWaktuPeriksa.setPreferredSize(new java.awt.Dimension(180, 26));
        MnWaktuPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnWaktuPeriksaActionPerformed(evt);
            }
        });
        MnGantiData.add(MnWaktuPeriksa);

        MnAsalRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsalRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsalRujukan.setText("Asal Rujukan");
        MnAsalRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsalRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsalRujukan.setIconTextGap(5);
        MnAsalRujukan.setName("MnAsalRujukan"); // NOI18N
        MnAsalRujukan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnAsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsalRujukanActionPerformed(evt);
            }
        });
        MnGantiData.add(MnAsalRujukan);

        MnUnitPengirim.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUnitPengirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUnitPengirim.setText("Unit Pengirim");
        MnUnitPengirim.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUnitPengirim.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUnitPengirim.setIconTextGap(5);
        MnUnitPengirim.setName("MnUnitPengirim"); // NOI18N
        MnUnitPengirim.setPreferredSize(new java.awt.Dimension(180, 26));
        MnUnitPengirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUnitPengirimActionPerformed(evt);
            }
        });
        MnGantiData.add(MnUnitPengirim);

        jPopupMenu1.add(MnGantiData);

        MnExportDataKeExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnExportDataKeExcel.setText("Export Data Menjadi File Excel");
        MnExportDataKeExcel.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnExportDataKeExcel.setName("MnExportDataKeExcel"); // NOI18N
        MnExportDataKeExcel.setPreferredSize(new java.awt.Dimension(260, 26));

        MnTglRegKhususIGD.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTglRegKhususIGD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnTglRegKhususIGD.setText("Berdasarkan Tgl. Reg. (Khusus IGD)");
        MnTglRegKhususIGD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTglRegKhususIGD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTglRegKhususIGD.setIconTextGap(5);
        MnTglRegKhususIGD.setName("MnTglRegKhususIGD"); // NOI18N
        MnTglRegKhususIGD.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTglRegKhususIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTglRegKhususIGDActionPerformed(evt);
            }
        });
        MnExportDataKeExcel.add(MnTglRegKhususIGD);

        MnTglReg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTglReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnTglReg.setText("Berdasarkan Tgl. Registrasi");
        MnTglReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTglReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTglReg.setIconTextGap(5);
        MnTglReg.setName("MnTglReg"); // NOI18N
        MnTglReg.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTglReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTglRegActionPerformed(evt);
            }
        });
        MnExportDataKeExcel.add(MnTglReg);

        MnTglPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTglPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        MnTglPemeriksaan.setText("Berdasarkan Tgl. Pemeriksaan");
        MnTglPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTglPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTglPemeriksaan.setIconTextGap(5);
        MnTglPemeriksaan.setName("MnTglPemeriksaan"); // NOI18N
        MnTglPemeriksaan.setPreferredSize(new java.awt.Dimension(230, 26));
        MnTglPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTglPemeriksaanActionPerformed(evt);
            }
        });
        MnExportDataKeExcel.add(MnTglPemeriksaan);

        jPopupMenu1.add(MnExportDataKeExcel);

        jMnLapInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapInap.setText("Lap. Transaksi Radiologi Rawat Inap");
        jMnLapInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapInap.setName("jMnLapInap"); // NOI18N
        jMnLapInap.setPreferredSize(new java.awt.Dimension(260, 26));

        MnInapSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnInapSemuaCaraBayar.setName("MnInapSemuaCaraBayar"); // NOI18N
        MnInapSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnInapSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapSemuaCaraBayar);

        MnInapDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnInapDetailSemuaCaraBayar.setName("MnInapDetailSemuaCaraBayar"); // NOI18N
        MnInapDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnInapDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapDetailSemuaCaraBayar);

        MnInapPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnInapPerCaraBayar.setName("MnInapPerCaraBayar"); // NOI18N
        MnInapPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnInapPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapPerCaraBayar);

        MnInapDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnInapDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnInapDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnInapDetailPerCaraBayar.setName("MnInapDetailPerCaraBayar"); // NOI18N
        MnInapDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnInapDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInapDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapInap.add(MnInapDetailPerCaraBayar);

        jPopupMenu1.add(jMnLapInap);

        jMnLapJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapJalan.setText("Lap. Transaksi Radiologi Rawat Jalan");
        jMnLapJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapJalan.setName("jMnLapJalan"); // NOI18N
        jMnLapJalan.setPreferredSize(new java.awt.Dimension(260, 26));

        MnJalanSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnJalanSemuaCaraBayar.setName("MnJalanSemuaCaraBayar"); // NOI18N
        MnJalanSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanSemuaCaraBayar);

        MnJalanDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnJalanDetailSemuaCaraBayar.setName("MnJalanDetailSemuaCaraBayar"); // NOI18N
        MnJalanDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailSemuaCaraBayar);

        MnJalanPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnJalanPerCaraBayar.setName("MnJalanPerCaraBayar"); // NOI18N
        MnJalanPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanPerCaraBayar);

        MnJalanDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnJalanDetailPerCaraBayar.setName("MnJalanDetailPerCaraBayar"); // NOI18N
        MnJalanDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailPerCaraBayar);

        MnJalanPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanPerPoli.setText("Rekap Total Pemrk. Per Unit/Poli");
        MnJalanPerPoli.setName("MnJalanPerPoli"); // NOI18N
        MnJalanPerPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanPerPoliActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanPerPoli);

        MnJalanDetailPerPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJalanDetailPerPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnJalanDetailPerPoli.setText("Rekap Detail Pemrk. Per Unit/Poli");
        MnJalanDetailPerPoli.setName("MnJalanDetailPerPoli"); // NOI18N
        MnJalanDetailPerPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnJalanDetailPerPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJalanDetailPerPoliActionPerformed(evt);
            }
        });
        jMnLapJalan.add(MnJalanDetailPerPoli);

        jPopupMenu1.add(jMnLapJalan);

        jMnLapSemuaRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapSemuaRawat.setText("Lap. Transaksi Radiologi Semua Rawat");
        jMnLapSemuaRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapSemuaRawat.setName("jMnLapSemuaRawat"); // NOI18N
        jMnLapSemuaRawat.setPreferredSize(new java.awt.Dimension(260, 26));

        MnSRSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRSemuaCaraBayar.setText("Rekap Total Pemrk. Semua Cara Bayar");
        MnSRSemuaCaraBayar.setName("MnSRSemuaCaraBayar"); // NOI18N
        MnSRSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSRSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRSemuaCaraBayar);

        MnSRDetailSemuaCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRDetailSemuaCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRDetailSemuaCaraBayar.setText("Rekap Detail Pemrk. Semua Cara Bayar");
        MnSRDetailSemuaCaraBayar.setName("MnSRDetailSemuaCaraBayar"); // NOI18N
        MnSRDetailSemuaCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSRDetailSemuaCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRDetailSemuaCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRDetailSemuaCaraBayar);

        MnSRPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRPerCaraBayar.setText("Rekap Total Pemrk. Per Cara Bayar");
        MnSRPerCaraBayar.setName("MnSRPerCaraBayar"); // NOI18N
        MnSRPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSRPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRPerCaraBayar);

        MnSRDetailPerCaraBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSRDetailPerCaraBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnSRDetailPerCaraBayar.setText("Rekap Detail Pemrk. Per Cara Bayar");
        MnSRDetailPerCaraBayar.setName("MnSRDetailPerCaraBayar"); // NOI18N
        MnSRDetailPerCaraBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnSRDetailPerCaraBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSRDetailPerCaraBayarActionPerformed(evt);
            }
        });
        jMnLapSemuaRawat.add(MnSRDetailPerCaraBayar);

        jPopupMenu1.add(jMnLapSemuaRawat);

        jMnLapJenisPeriksaRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapJenisPeriksaRad.setText("Lap. Jns. Pmriksn. Rad. PerCara Bayar");
        jMnLapJenisPeriksaRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapJenisPeriksaRad.setName("jMnLapJenisPeriksaRad"); // NOI18N
        jMnLapJenisPeriksaRad.setPreferredSize(new java.awt.Dimension(260, 26));

        MnRekapJenisPeriksaRadInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapJenisPeriksaRadInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapJenisPeriksaRadInap.setText("Pemeriksaan Rawat Inap");
        MnRekapJenisPeriksaRadInap.setName("MnRekapJenisPeriksaRadInap"); // NOI18N
        MnRekapJenisPeriksaRadInap.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRekapJenisPeriksaRadInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapJenisPeriksaRadInapActionPerformed(evt);
            }
        });
        jMnLapJenisPeriksaRad.add(MnRekapJenisPeriksaRadInap);

        MnRekapJenisPeriksaRadJalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapJenisPeriksaRadJalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapJenisPeriksaRadJalan.setText("Pemeriksaan Rawat Jalan");
        MnRekapJenisPeriksaRadJalan.setName("MnRekapJenisPeriksaRadJalan"); // NOI18N
        MnRekapJenisPeriksaRadJalan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRekapJenisPeriksaRadJalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapJenisPeriksaRadJalanActionPerformed(evt);
            }
        });
        jMnLapJenisPeriksaRad.add(MnRekapJenisPeriksaRadJalan);

        MnRekapJenisPeriksaRadSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapJenisPeriksaRadSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapJenisPeriksaRadSemua.setText("Pemeriksaan Semua Rawat");
        MnRekapJenisPeriksaRadSemua.setName("MnRekapJenisPeriksaRadSemua"); // NOI18N
        MnRekapJenisPeriksaRadSemua.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRekapJenisPeriksaRadSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapJenisPeriksaRadSemuaActionPerformed(evt);
            }
        });
        jMnLapJenisPeriksaRad.add(MnRekapJenisPeriksaRadSemua);

        jPopupMenu1.add(jMnLapJenisPeriksaRad);

        jMnLapPendapatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapPendapatan.setText("Lap. Rekap Pendapatan Radiologi");
        jMnLapPendapatan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapPendapatan.setName("jMnLapPendapatan"); // NOI18N
        jMnLapPendapatan.setPreferredSize(new java.awt.Dimension(260, 26));

        MnPendapatanRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanRalan.setText("Rekap Pendapatan Rawat Jalan");
        MnPendapatanRalan.setName("MnPendapatanRalan"); // NOI18N
        MnPendapatanRalan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPendapatanRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanRalanActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanRalan);

        MnPendapatanRanap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanRanap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanRanap.setText("Rekap Pendapatan Rawat Inap");
        MnPendapatanRanap.setName("MnPendapatanRanap"); // NOI18N
        MnPendapatanRanap.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPendapatanRanap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanRanapActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanRanap);

        MnPendapatanSemuaRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPendapatanSemuaRawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPendapatanSemuaRawat.setText("Rekap Pendapatan Semua Rawat");
        MnPendapatanSemuaRawat.setName("MnPendapatanSemuaRawat"); // NOI18N
        MnPendapatanSemuaRawat.setPreferredSize(new java.awt.Dimension(220, 26));
        MnPendapatanSemuaRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPendapatanSemuaRawatActionPerformed(evt);
            }
        });
        jMnLapPendapatan.add(MnPendapatanSemuaRawat);

        jPopupMenu1.add(jMnLapPendapatan);

        jMnLapPemeriksaanDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        jMnLapPemeriksaanDokter.setText("Lap. Rekap Pemeriksaan Dokter");
        jMnLapPemeriksaanDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnLapPemeriksaanDokter.setName("jMnLapPemeriksaanDokter"); // NOI18N
        jMnLapPemeriksaanDokter.setPreferredSize(new java.awt.Dimension(260, 26));

        MnRekapTotalPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapTotalPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapTotalPemeriksaan.setText("Rekap Total Pemeriksaan Dokter");
        MnRekapTotalPemeriksaan.setName("MnRekapTotalPemeriksaan"); // NOI18N
        MnRekapTotalPemeriksaan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnRekapTotalPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapTotalPemeriksaanActionPerformed(evt);
            }
        });
        jMnLapPemeriksaanDokter.add(MnRekapTotalPemeriksaan);

        MnRekapRincianPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRekapRincianPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnRekapRincianPemeriksaan.setText("Rekap Rincian Jumlah Pemeriksaan");
        MnRekapRincianPemeriksaan.setName("MnRekapRincianPemeriksaan"); // NOI18N
        MnRekapRincianPemeriksaan.setPreferredSize(new java.awt.Dimension(220, 26));
        MnRekapRincianPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRekapRincianPemeriksaanActionPerformed(evt);
            }
        });
        jMnLapPemeriksaanDokter.add(MnRekapRincianPemeriksaan);

        jPopupMenu1.add(jMnLapPemeriksaanDokter);

        jPopupMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jPopupMenu2.setName("jPopupMenu2"); // NOI18N
        jPopupMenu2.setPreferredSize(new java.awt.Dimension(200, 85));

        MnRiwayatPerawatan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatPerawatan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatPerawatan1.setText("Riwayat Perawatan");
        MnRiwayatPerawatan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatPerawatan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatPerawatan1.setIconTextGap(5);
        MnRiwayatPerawatan1.setName("MnRiwayatPerawatan1"); // NOI18N
        MnRiwayatPerawatan1.setPreferredSize(new java.awt.Dimension(200, 85));
        MnRiwayatPerawatan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatPerawatan1ActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnRiwayatPerawatan1);

        MnKirimKeWhatsapp.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKirimKeWhatsapp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/whatsapp.png"))); // NOI18N
        MnKirimKeWhatsapp.setText("Kirim Hasil Ke WhatsApp");
        MnKirimKeWhatsapp.setName("MnKirimKeWhatsapp"); // NOI18N
        MnKirimKeWhatsapp.setPreferredSize(new java.awt.Dimension(200, 85));
        MnKirimKeWhatsapp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKirimKeWhatsappActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnKirimKeWhatsapp);

        jMnGantiData1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMnGantiData1.setText("Ganti Data");
        jMnGantiData1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMnGantiData1.setName("jMnGantiData1"); // NOI18N
        jMnGantiData1.setPreferredSize(new java.awt.Dimension(200, 85));

        MnDokterPemeriksaRad1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterPemeriksaRad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterPemeriksaRad1.setText("Dokter Pemeriksa Radiologi");
        MnDokterPemeriksaRad1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterPemeriksaRad1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterPemeriksaRad1.setIconTextGap(5);
        MnDokterPemeriksaRad1.setName("MnDokterPemeriksaRad1"); // NOI18N
        MnDokterPemeriksaRad1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDokterPemeriksaRad1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterPemeriksaRad1ActionPerformed(evt);
            }
        });
        jMnGantiData1.add(MnDokterPemeriksaRad1);

        MnDokterPengirim1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokterPengirim1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokterPengirim1.setText("Dokter Pengirim / Perujuk");
        MnDokterPengirim1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokterPengirim1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokterPengirim1.setIconTextGap(5);
        MnDokterPengirim1.setName("MnDokterPengirim1"); // NOI18N
        MnDokterPengirim1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnDokterPengirim1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokterPengirim1ActionPerformed(evt);
            }
        });
        jMnGantiData1.add(MnDokterPengirim1);

        MnWaktuPeriksa1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnWaktuPeriksa1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnWaktuPeriksa1.setText("Tgl. Pemeriksaan");
        MnWaktuPeriksa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnWaktuPeriksa1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnWaktuPeriksa1.setIconTextGap(5);
        MnWaktuPeriksa1.setName("MnWaktuPeriksa1"); // NOI18N
        MnWaktuPeriksa1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnWaktuPeriksa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnWaktuPeriksa1ActionPerformed(evt);
            }
        });
        jMnGantiData1.add(MnWaktuPeriksa1);

        MnAsalRujukan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsalRujukan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsalRujukan1.setText("Asal Rujukan");
        MnAsalRujukan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsalRujukan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsalRujukan1.setIconTextGap(5);
        MnAsalRujukan1.setName("MnAsalRujukan1"); // NOI18N
        MnAsalRujukan1.setPreferredSize(new java.awt.Dimension(180, 26));
        MnAsalRujukan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsalRujukan1ActionPerformed(evt);
            }
        });
        jMnGantiData1.add(MnAsalRujukan1);

        jPopupMenu2.add(jMnGantiData1);

        WindowHasil.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHasil.setName("WindowHasil"); // NOI18N
        WindowHasil.setUndecorated(true);
        WindowHasil.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hasil Pemeriksaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        BtnPrintQR.setName("BtnPrintQR"); // NOI18N
        BtnPrintQR.setPreferredSize(new java.awt.Dimension(55, 55));
        BtnPrintQR.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+M");
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
        BtnPrintQR.add(BtnSimpan);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        BtnPrintQR.add(BtnHapus1);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Expertise Rad. :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnPrintQR.add(jLabel10);

        cmbCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Biasa", "Dengan QRCode" }));
        cmbCetak.setName("cmbCetak"); // NOI18N
        cmbCetak.setPreferredSize(new java.awt.Dimension(117, 23));
        BtnPrintQR.add(cmbCetak);

        BtnPrint1.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint1.setText("Cetak");
        BtnPrint1.setToolTipText("Alt+T");
        BtnPrint1.setName("BtnPrint1"); // NOI18N
        BtnPrint1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint1ActionPerformed(evt);
            }
        });
        BtnPrintQR.add(BtnPrint1);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        BtnPrintQR.add(BtnCloseIn5);

        internalFrame6.add(BtnPrintQR, java.awt.BorderLayout.PAGE_END);

        panelGlass7.setComponentPopupMenu(jPopupMenu2);
        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(55, 125));
        panelGlass7.setLayout(null);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Diagnosa Klinis : ");
        jLabel21.setComponentPopupMenu(jPopupMenu2);
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel21);
        jLabel21.setBounds(6, 62, 100, 23);

        diagKlinisRad.setForeground(new java.awt.Color(0, 0, 0));
        diagKlinisRad.setComponentPopupMenu(jPopupMenu2);
        diagKlinisRad.setName("diagKlinisRad"); // NOI18N
        diagKlinisRad.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(diagKlinisRad);
        diagKlinisRad.setBounds(108, 62, 345, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Pasien : ");
        jLabel22.setComponentPopupMenu(jPopupMenu2);
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(6, 8, 100, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setComponentPopupMenu(jPopupMenu2);
        norm.setName("norm"); // NOI18N
        norm.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(norm);
        norm.setBounds(108, 8, 69, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setComponentPopupMenu(jPopupMenu2);
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(nmpasien);
        nmpasien.setBounds(180, 8, 270, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Periksa : ");
        jLabel23.setComponentPopupMenu(jPopupMenu2);
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel23);
        jLabel23.setBounds(6, 35, 100, 23);

        tglperiksarad.setEditable(false);
        tglperiksarad.setForeground(new java.awt.Color(0, 0, 0));
        tglperiksarad.setComponentPopupMenu(jPopupMenu2);
        tglperiksarad.setName("tglperiksarad"); // NOI18N
        tglperiksarad.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(tglperiksarad);
        tglperiksarad.setBounds(108, 35, 150, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Jam : ");
        jLabel24.setComponentPopupMenu(jPopupMenu2);
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(262, 35, 35, 23);

        jamperiksarad.setEditable(false);
        jamperiksarad.setForeground(new java.awt.Color(0, 0, 0));
        jamperiksarad.setComponentPopupMenu(jPopupMenu2);
        jamperiksarad.setName("jamperiksarad"); // NOI18N
        jamperiksarad.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(jamperiksarad);
        jamperiksarad.setBounds(297, 35, 80, 23);

        labelunit.setForeground(new java.awt.Color(0, 0, 0));
        labelunit.setText("labelunit");
        labelunit.setComponentPopupMenu(jPopupMenu2);
        labelunit.setName("labelunit"); // NOI18N
        labelunit.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(labelunit);
        labelunit.setBounds(6, 89, 100, 23);

        rg_poli.setEditable(false);
        rg_poli.setForeground(new java.awt.Color(0, 0, 0));
        rg_poli.setComponentPopupMenu(jPopupMenu2);
        rg_poli.setName("rg_poli"); // NOI18N
        rg_poli.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(rg_poli);
        rg_poli.setBounds(108, 89, 345, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Dokter Radiologi : ");
        jLabel25.setComponentPopupMenu(jPopupMenu2);
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel25);
        jLabel25.setBounds(460, 8, 100, 23);

        dokterRad.setEditable(false);
        dokterRad.setForeground(new java.awt.Color(0, 0, 0));
        dokterRad.setComponentPopupMenu(jPopupMenu2);
        dokterRad.setName("dokterRad"); // NOI18N
        dokterRad.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(dokterRad);
        dokterRad.setBounds(562, 8, 345, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Dokter Pengirim : ");
        jLabel26.setComponentPopupMenu(jPopupMenu2);
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(460, 35, 100, 23);

        dokterPengirim.setEditable(false);
        dokterPengirim.setForeground(new java.awt.Color(0, 0, 0));
        dokterPengirim.setComponentPopupMenu(jPopupMenu2);
        dokterPengirim.setName("dokterPengirim"); // NOI18N
        dokterPengirim.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(dokterPengirim);
        dokterPengirim.setBounds(562, 35, 345, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Asal Rujukan : ");
        jLabel27.setComponentPopupMenu(jPopupMenu2);
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(460, 62, 100, 23);

        asalRujukan.setEditable(false);
        asalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        asalRujukan.setComponentPopupMenu(jPopupMenu2);
        asalRujukan.setName("asalRujukan"); // NOI18N
        asalRujukan.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(asalRujukan);
        asalRujukan.setBounds(562, 62, 345, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Unit Pengirim :");
        jLabel42.setComponentPopupMenu(jPopupMenu2);
        jLabel42.setName("jLabel42"); // NOI18N
        jLabel42.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass7.add(jLabel42);
        jLabel42.setBounds(460, 89, 100, 23);

        TunitPengirim.setEditable(false);
        TunitPengirim.setForeground(new java.awt.Color(0, 0, 0));
        TunitPengirim.setComponentPopupMenu(jPopupMenu2);
        TunitPengirim.setName("TunitPengirim"); // NOI18N
        TunitPengirim.setPreferredSize(new java.awt.Dimension(550, 23));
        panelGlass7.add(TunitPengirim);
        TunitPengirim.setBounds(562, 89, 345, 23);

        internalFrame6.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        jPanel1.setComponentPopupMenu(jPopupMenu2);
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Pemeriksaan Yang Dilakukan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel3.setComponentPopupMenu(jPopupMenu2);
        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(245, 255, 235)));
        Scroll2.setComponentPopupMenu(jPopupMenu2);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit");
        tbPemeriksaan.setComponentPopupMenu(jPopupMenu2);
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
        tbPemeriksaan.getTableHeader().setReorderingAllowed(false);
        tbPemeriksaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemeriksaanMouseClicked(evt);
            }
        });
        tbPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemeriksaanKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbPemeriksaan);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Deskripsi/Uraian Pemeriksaan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setComponentPopupMenu(jPopupMenu2);
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi4.setLayout(null);

        jLabel28.setForeground(new java.awt.Color(0, 51, 153));
        jLabel28.setText("Pemeriksaan Dipilih : ");
        jLabel28.setComponentPopupMenu(jPopupMenu2);
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(125, 23));
        panelisi4.add(jLabel28);
        jLabel28.setBounds(5, 8, 130, 23);

        itemDipilih.setEditable(false);
        itemDipilih.setForeground(new java.awt.Color(0, 51, 153));
        itemDipilih.setComponentPopupMenu(jPopupMenu2);
        itemDipilih.setName("itemDipilih"); // NOI18N
        itemDipilih.setPreferredSize(new java.awt.Dimension(345, 23));
        itemDipilih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemDipilihKeyPressed(evt);
            }
        });
        panelisi4.add(itemDipilih);
        itemDipilih.setBounds(138, 8, 335, 23);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        Scroll3.setComponentPopupMenu(jPopupMenu2);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        HasilPeriksa.setColumns(20);
        HasilPeriksa.setRows(5);
        HasilPeriksa.setComponentPopupMenu(jPopupMenu2);
        HasilPeriksa.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        HasilPeriksa.setName("HasilPeriksa"); // NOI18N
        Scroll3.setViewportView(HasilPeriksa);

        jPanel2.add(Scroll3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame6.add(jPanel1, java.awt.BorderLayout.CENTER);

        WindowHasil.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowGantiDokterRad.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokterRad.setName("WindowGantiDokterRad"); // NOI18N
        WindowGantiDokterRad.setUndecorated(true);
        WindowGantiDokterRad.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Penanggung Jwb. Periksa Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(null);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setText("Tutup");
        BtnCloseIn1.setToolTipText("Alt+U");
        BtnCloseIn1.setName("BtnCloseIn1"); // NOI18N
        BtnCloseIn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn1ActionPerformed(evt);
            }
        });
        BtnCloseIn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn1KeyPressed(evt);
            }
        });
        internalFrame3.add(BtnCloseIn1);
        BtnCloseIn1.setBounds(645, 30, 80, 30);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        internalFrame3.add(BtnSimpan1);
        BtnSimpan1.setBounds(540, 30, 100, 30);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Dokter : ");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame3.add(jLabel13);
        jLabel13.setBounds(0, 32, 90, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setHighlighter(null);
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        internalFrame3.add(kddokter);
        kddokter.setBounds(95, 32, 90, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        internalFrame3.add(TDokter);
        TDokter.setBounds(188, 32, 310, 23);

        btnCariDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariDokter.setToolTipText("ALt+7");
        btnCariDokter.setName("btnCariDokter"); // NOI18N
        btnCariDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokterActionPerformed(evt);
            }
        });
        internalFrame3.add(btnCariDokter);
        btnCariDokter.setBounds(505, 32, 28, 23);

        WindowGantiDokterRad.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowWaktuPeriksa.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowWaktuPeriksa.setName("WindowWaktuPeriksa"); // NOI18N
        WindowWaktuPeriksa.setUndecorated(true);
        WindowWaktuPeriksa.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Tanggal & Jam Input Pemeriksaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(null);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        BtnCloseIn6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn6KeyPressed(evt);
            }
        });
        internalFrame11.add(BtnCloseIn6);
        BtnCloseIn6.setBounds(140, 85, 90, 30);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Tgl. Pemeriksaan :");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame11.add(jLabel51);
        jLabel51.setBounds(0, 25, 130, 23);

        tanggalPeriksa.setEditable(false);
        tanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2026" }));
        tanggalPeriksa.setDisplayFormat("dd-MM-yyyy");
        tanggalPeriksa.setName("tanggalPeriksa"); // NOI18N
        tanggalPeriksa.setOpaque(false);
        tanggalPeriksa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tanggalPeriksaItemStateChanged(evt);
            }
        });
        tanggalPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tanggalPeriksaKeyPressed(evt);
            }
        });
        internalFrame11.add(tanggalPeriksa);
        tanggalPeriksa.setBounds(135, 25, 100, 23);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+N");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        BtnSimpan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan2KeyPressed(evt);
            }
        });
        internalFrame11.add(BtnSimpan2);
        BtnSimpan2.setBounds(30, 85, 100, 30);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Jam Input :");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame11.add(jLabel52);
        jLabel52.setBounds(0, 55, 130, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam1KeyPressed(evt);
            }
        });
        internalFrame11.add(cmbJam1);
        cmbJam1.setBounds(135, 55, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt1KeyPressed(evt);
            }
        });
        internalFrame11.add(cmbMnt1);
        cmbMnt1.setBounds(185, 55, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk1KeyPressed(evt);
            }
        });
        internalFrame11.add(cmbDtk1);
        cmbDtk1.setBounds(235, 55, 45, 23);

        WindowWaktuPeriksa.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        WindowGantiDokterPerujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiDokterPerujuk.setName("WindowGantiDokterPerujuk"); // NOI18N
        WindowGantiDokterPerujuk.setUndecorated(true);
        WindowGantiDokterPerujuk.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Dokter Perujuk / Pengirim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame4.setLayout(null);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        BtnCloseIn2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseIn2KeyPressed(evt);
            }
        });
        internalFrame4.add(BtnCloseIn2);
        BtnCloseIn2.setBounds(645, 30, 80, 30);

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setText("Simpan");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        BtnSimpan3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan3KeyPressed(evt);
            }
        });
        internalFrame4.add(BtnSimpan3);
        BtnSimpan3.setBounds(540, 30, 100, 30);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama Dokter : ");
        jLabel14.setName("jLabel14"); // NOI18N
        internalFrame4.add(jLabel14);
        jLabel14.setBounds(0, 32, 90, 23);

        kddokter1.setEditable(false);
        kddokter1.setForeground(new java.awt.Color(0, 0, 0));
        kddokter1.setHighlighter(null);
        kddokter1.setName("kddokter1"); // NOI18N
        kddokter1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokter1KeyPressed(evt);
            }
        });
        internalFrame4.add(kddokter1);
        kddokter1.setBounds(95, 32, 90, 23);

        TDokter1.setEditable(false);
        TDokter1.setForeground(new java.awt.Color(0, 0, 0));
        TDokter1.setName("TDokter1"); // NOI18N
        internalFrame4.add(TDokter1);
        TDokter1.setBounds(188, 32, 310, 23);

        btnCariDokter1.setForeground(new java.awt.Color(0, 0, 0));
        btnCariDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariDokter1.setToolTipText("ALt+7");
        btnCariDokter1.setName("btnCariDokter1"); // NOI18N
        btnCariDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDokter1ActionPerformed(evt);
            }
        });
        internalFrame4.add(btnCariDokter1);
        btnCariDokter1.setBounds(505, 32, 28, 23);

        WindowGantiDokterPerujuk.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        WindowGantiRujukan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiRujukan.setName("WindowGantiRujukan"); // NOI18N
        WindowGantiRujukan.setUndecorated(true);
        WindowGantiRujukan.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Asal Rujukan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(645, 30, 80, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(540, 30, 100, 30);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama Rujukan / Faskes : ");
        jLabel15.setName("jLabel15"); // NOI18N
        internalFrame5.add(jLabel15);
        jLabel15.setBounds(0, 32, 140, 23);

        nmFaskes.setEditable(false);
        nmFaskes.setForeground(new java.awt.Color(0, 0, 0));
        nmFaskes.setName("nmFaskes"); // NOI18N
        internalFrame5.add(nmFaskes);
        nmFaskes.setBounds(143, 32, 355, 23);

        btnCariFaskes.setForeground(new java.awt.Color(0, 0, 0));
        btnCariFaskes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariFaskes.setToolTipText("ALt+7");
        btnCariFaskes.setName("btnCariFaskes"); // NOI18N
        btnCariFaskes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariFaskesActionPerformed(evt);
            }
        });
        internalFrame5.add(btnCariFaskes);
        btnCariFaskes.setBounds(505, 32, 28, 23);

        WindowGantiRujukan.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowGantiPetugasRadiologi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiPetugasRadiologi.setName("WindowGantiPetugasRadiologi"); // NOI18N
        WindowGantiPetugasRadiologi.setUndecorated(true);
        WindowGantiPetugasRadiologi.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Petugas Pemeriksa Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(798, 28, 80, 30);

        BtnSimpan5.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan5.setText("Simpan");
        BtnSimpan5.setToolTipText("Alt+S");
        BtnSimpan5.setName("BtnSimpan5"); // NOI18N
        BtnSimpan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan5ActionPerformed(evt);
            }
        });
        internalFrame7.add(BtnSimpan5);
        BtnSimpan5.setBounds(690, 28, 100, 30);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Petugas : ");
        jLabel16.setName("jLabel16"); // NOI18N
        internalFrame7.add(jLabel16);
        jLabel16.setBounds(0, 32, 110, 23);

        kdpetugas.setEditable(false);
        kdpetugas.setForeground(new java.awt.Color(0, 0, 0));
        kdpetugas.setName("kdpetugas"); // NOI18N
        internalFrame7.add(kdpetugas);
        kdpetugas.setBounds(115, 32, 170, 23);

        TPetugas.setEditable(false);
        TPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TPetugas.setName("TPetugas"); // NOI18N
        internalFrame7.add(TPetugas);
        TPetugas.setBounds(290, 32, 360, 23);

        btnCariPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnCariPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariPetugas.setToolTipText("ALt+7");
        btnCariPetugas.setName("btnCariPetugas"); // NOI18N
        btnCariPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariPetugasActionPerformed(evt);
            }
        });
        internalFrame7.add(btnCariPetugas);
        btnCariPetugas.setBounds(650, 32, 28, 23);

        WindowGantiPetugasRadiologi.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        WindowRiwayatExpertise.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayatExpertise.setName("WindowRiwayatExpertise"); // NOI18N
        WindowRiwayatExpertise.setUndecorated(true);
        WindowRiwayatExpertise.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Riwayat Expertise Pemeriksaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setPreferredSize(new java.awt.Dimension(12, 105));
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(null);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("No. Rawat :");
        jLabel11.setName("jLabel11"); // NOI18N
        internalFrame12.add(jLabel11);
        jLabel11.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        internalFrame12.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        internalFrame12.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        internalFrame12.add(TPasien);
        TPasien.setBounds(319, 10, 360, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Nama Pemeriksaan :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame12.add(jLabel12);
        jLabel12.setBounds(0, 38, 110, 23);

        TnmPemeriksaan.setEditable(false);
        TnmPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemeriksaan.setName("TnmPemeriksaan"); // NOI18N
        internalFrame12.add(TnmPemeriksaan);
        TnmPemeriksaan.setBounds(114, 38, 330, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tgl. Pemeriksaan :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame12.add(jLabel17);
        jLabel17.setBounds(0, 66, 110, 23);

        TtglPemeriksaan.setEditable(false);
        TtglPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TtglPemeriksaan.setName("TtglPemeriksaan"); // NOI18N
        internalFrame12.add(TtglPemeriksaan);
        TtglPemeriksaan.setBounds(114, 66, 190, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tgl. Expertise :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame12.add(jLabel18);
        jLabel18.setBounds(445, 66, 140, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Pemeriksaan Expertise :");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame12.add(jLabel20);
        jLabel20.setBounds(445, 38, 140, 23);

        TpemeriksaanExp.setEditable(false);
        TpemeriksaanExp.setForeground(new java.awt.Color(0, 0, 0));
        TpemeriksaanExp.setName("TpemeriksaanExp"); // NOI18N
        internalFrame12.add(TpemeriksaanExp);
        TpemeriksaanExp.setBounds(590, 38, 330, 23);

        TtglExp.setEditable(false);
        TtglExp.setForeground(new java.awt.Color(0, 0, 0));
        TtglExp.setName("TtglExp"); // NOI18N
        internalFrame12.add(TtglExp);
        TtglExp.setBounds(590, 66, 190, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Jam :");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame12.add(jLabel30);
        jLabel30.setBounds(310, 66, 40, 23);

        TjamPemeriksaan.setEditable(false);
        TjamPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TjamPemeriksaan.setName("TjamPemeriksaan"); // NOI18N
        internalFrame12.add(TjamPemeriksaan);
        TjamPemeriksaan.setBounds(354, 66, 90, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Jam :");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame12.add(jLabel31);
        jLabel31.setBounds(785, 66, 40, 23);

        TjamExp.setEditable(false);
        TjamExp.setForeground(new java.awt.Color(0, 0, 0));
        TjamExp.setName("TjamExp"); // NOI18N
        internalFrame12.add(TjamExp);
        TjamExp.setBounds(830, 66, 90, 23);

        internalFrame8.add(internalFrame12, java.awt.BorderLayout.PAGE_START);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(new java.awt.GridLayout(1, 3));

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Pemeriksaan Radiologi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbDataPeriksa.setName("tbDataPeriksa"); // NOI18N
        tbDataPeriksa.getTableHeader().setReorderingAllowed(false);
        tbDataPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDataPeriksaMouseClicked(evt);
            }
        });
        tbDataPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDataPeriksaKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbDataPeriksa);

        internalFrame9.add(Scroll1);

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Riwayat Expertise ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbExpertise.setName("tbExpertise"); // NOI18N
        tbExpertise.getTableHeader().setReorderingAllowed(false);
        tbExpertise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbExpertiseMouseClicked(evt);
            }
        });
        tbExpertise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbExpertiseKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbExpertise);

        internalFrame9.add(Scroll4);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Deskripsi Hasil Pemeriksaan Expertise ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setComponentPopupMenu(jPopupMenu2);
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        HasilPeriksa1.setEditable(false);
        HasilPeriksa1.setColumns(20);
        HasilPeriksa1.setRows(5);
        HasilPeriksa1.setComponentPopupMenu(jPopupMenu2);
        HasilPeriksa1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        HasilPeriksa1.setName("HasilPeriksa1"); // NOI18N
        Scroll5.setViewportView(HasilPeriksa1);

        internalFrame9.add(Scroll5);

        internalFrame8.add(internalFrame9, java.awt.BorderLayout.CENTER);

        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setPreferredSize(new java.awt.Dimension(12, 47));
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnBatal.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnBatal);

        BtnDisamakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnDisamakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnDisamakan.setText("Expertise Disamakan Dengan Pemeriksaan");
        BtnDisamakan.setToolTipText("Alt+G");
        BtnDisamakan.setName("BtnDisamakan"); // NOI18N
        BtnDisamakan.setPreferredSize(new java.awt.Dimension(300, 30));
        BtnDisamakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDisamakanActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnDisamakan);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnCari1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnKeluar1);

        internalFrame8.add(internalFrame10, java.awt.BorderLayout.PAGE_END);

        WindowRiwayatExpertise.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        WindowBeratBadan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowBeratBadan.setName("WindowBeratBadan"); // NOI18N
        WindowBeratBadan.setUndecorated(true);
        WindowBeratBadan.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Data Berat Badan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(null);

        BtnCloseIn7.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn7.setText("Tutup");
        BtnCloseIn7.setToolTipText("Alt+U");
        BtnCloseIn7.setName("BtnCloseIn7"); // NOI18N
        BtnCloseIn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn7ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(320, 30, 80, 30);

        BtnSimpan6.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan6.setText("Simpan");
        BtnSimpan6.setToolTipText("Alt+S");
        BtnSimpan6.setName("BtnSimpan6"); // NOI18N
        BtnSimpan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan6ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnSimpan6);
        BtnSimpan6.setBounds(210, 30, 100, 30);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Berat Badan :");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame13.add(jLabel32);
        jLabel32.setBounds(0, 32, 90, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        internalFrame13.add(Tbb);
        Tbb.setBounds(95, 32, 110, 23);

        WindowBeratBadan.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowParameter.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowParameter.setName("WindowParameter"); // NOI18N
        WindowParameter.setUndecorated(true);
        WindowParameter.setResizable(false);

        internalFrame14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Data Parameter Pemeriksaan Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(null);

        BtnCloseIn8.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn8.setText("Tutup");
        BtnCloseIn8.setToolTipText("Alt+U");
        BtnCloseIn8.setName("BtnCloseIn8"); // NOI18N
        BtnCloseIn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn8ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnCloseIn8);
        BtnCloseIn8.setBounds(190, 180, 80, 30);

        BtnSimpan7.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan7.setText("Simpan");
        BtnSimpan7.setToolTipText("Alt+S");
        BtnSimpan7.setName("BtnSimpan7"); // NOI18N
        BtnSimpan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan7ActionPerformed(evt);
            }
        });
        internalFrame14.add(BtnSimpan7);
        BtnSimpan7.setBounds(80, 180, 100, 30);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Parameter kV :");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame14.add(jLabel33);
        jLabel33.setBounds(0, 32, 130, 23);

        TnilaiKv.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiKv.setName("TnilaiKv"); // NOI18N
        TnilaiKv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnilaiKvKeyPressed(evt);
            }
        });
        internalFrame14.add(TnilaiKv);
        TnilaiKv.setBounds(135, 32, 90, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Parameter mAs :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame14.add(jLabel34);
        jLabel34.setBounds(0, 60, 130, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Parameter mA/CTDi :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame14.add(jLabel35);
        jLabel35.setBounds(0, 88, 130, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Parameter s/Fase :");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame14.add(jLabel36);
        jLabel36.setBounds(0, 116, 130, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Parameter DLP :");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame14.add(jLabel37);
        jLabel37.setBounds(0, 144, 130, 23);

        TnilaiMas.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMas.setName("TnilaiMas"); // NOI18N
        TnilaiMas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnilaiMasKeyPressed(evt);
            }
        });
        internalFrame14.add(TnilaiMas);
        TnilaiMas.setBounds(135, 60, 90, 23);

        TnilaiMa.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMa.setName("TnilaiMa"); // NOI18N
        TnilaiMa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnilaiMaKeyPressed(evt);
            }
        });
        internalFrame14.add(TnilaiMa);
        TnilaiMa.setBounds(135, 88, 90, 23);

        TnilaiFase.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFase.setName("TnilaiFase"); // NOI18N
        TnilaiFase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnilaiFaseKeyPressed(evt);
            }
        });
        internalFrame14.add(TnilaiFase);
        TnilaiFase.setBounds(135, 116, 90, 23);

        TnilaiDlp.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiDlp.setName("TnilaiDlp"); // NOI18N
        TnilaiDlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnilaiDlpKeyPressed(evt);
            }
        });
        internalFrame14.add(TnilaiDlp);
        TnilaiDlp.setBounds(135, 144, 90, 23);

        WindowParameter.getContentPane().add(internalFrame14, java.awt.BorderLayout.CENTER);

        WindowNilaiKritis.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNilaiKritis.setName("WindowNilaiKritis"); // NOI18N
        WindowNilaiKritis.setUndecorated(true);
        WindowNilaiKritis.setResizable(false);

        internalFrame15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Nilai Kritis Pemeriksaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.BorderLayout());

        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setPreferredSize(new java.awt.Dimension(2, 320));
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(null);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Pasien :");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame16.add(jLabel38);
        jLabel38.setBounds(0, 10, 110, 23);

        TNoRw1.setEditable(false);
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        internalFrame16.add(TNoRw1);
        TNoRw1.setBounds(114, 10, 131, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM1.setName("TNoRM1"); // NOI18N
        internalFrame16.add(TNoRM1);
        TNoRM1.setBounds(247, 10, 70, 23);

        TPasien1.setEditable(false);
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setHighlighter(null);
        TPasien1.setName("TPasien1"); // NOI18N
        internalFrame16.add(TPasien1);
        TPasien1.setBounds(319, 10, 390, 23);

        Scroll6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Item Jenis Pemeriksaan Radiologi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbItem.setToolTipText("Silahkan klik untuk memilih data yang dipilih");
        tbItem.setName("tbItem"); // NOI18N
        tbItem.getTableHeader().setReorderingAllowed(false);
        Scroll6.setViewportView(tbItem);

        internalFrame16.add(Scroll6);
        Scroll6.setBounds(114, 38, 540, 100);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Nama Petugas :");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame16.add(jLabel39);
        jLabel39.setBounds(0, 143, 110, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        internalFrame16.add(TnmPetugas);
        TnmPetugas.setBounds(114, 143, 390, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnPetugas);
        BtnPetugas.setBounds(510, 143, 28, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Petugas Penerima Lap. :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame16.add(jLabel50);
        jLabel50.setBounds(0, 255, 140, 23);

        TnmPetugasPenerima.setEditable(false);
        TnmPetugasPenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugasPenerima.setName("TnmPetugasPenerima"); // NOI18N
        internalFrame16.add(TnmPetugasPenerima);
        TnmPetugasPenerima.setBounds(144, 255, 390, 23);

        BtnPetugasPenerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugasPenerima.setToolTipText("Alt+2");
        BtnPetugasPenerima.setName("BtnPetugasPenerima"); // NOI18N
        BtnPetugasPenerima.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugasPenerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasPenerimaActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnPetugasPenerima);
        BtnPetugasPenerima.setBounds(540, 255, 28, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Keterangan :");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame16.add(jLabel53);
        jLabel53.setBounds(0, 283, 140, 23);

        Tketerangan.setForeground(new java.awt.Color(0, 0, 0));
        Tketerangan.setName("Tketerangan"); // NOI18N
        Tketerangan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketeranganKeyPressed(evt);
            }
        });
        internalFrame16.add(Tketerangan);
        Tketerangan.setBounds(144, 283, 597, 23);

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
        internalFrame16.add(chkSaya);
        chkSaya.setBounds(550, 143, 100, 23);

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
        internalFrame16.add(chkSaya1);
        chkSaya1.setBounds(580, 255, 100, 23);

        chkPemeriksaan.setBackground(new java.awt.Color(255, 255, 250));
        chkPemeriksaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        chkPemeriksaan.setText("Jam Pemeriksaan Selesai :");
        chkPemeriksaan.setBorderPainted(true);
        chkPemeriksaan.setBorderPaintedFlat(true);
        chkPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemeriksaan.setName("chkPemeriksaan"); // NOI18N
        chkPemeriksaan.setOpaque(false);
        chkPemeriksaan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPemeriksaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPemeriksaanActionPerformed(evt);
            }
        });
        internalFrame16.add(chkPemeriksaan);
        chkPemeriksaan.setBounds(114, 171, 160, 23);

        chkLapor.setBackground(new java.awt.Color(255, 255, 250));
        chkLapor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLapor.setForeground(new java.awt.Color(0, 0, 0));
        chkLapor.setText("Jam Lapor :");
        chkLapor.setBorderPainted(true);
        chkLapor.setBorderPaintedFlat(true);
        chkLapor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkLapor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLapor.setName("chkLapor"); // NOI18N
        chkLapor.setOpaque(false);
        chkLapor.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLapor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLaporActionPerformed(evt);
            }
        });
        internalFrame16.add(chkLapor);
        chkLapor.setBounds(114, 199, 160, 23);

        chkLapDiterima.setBackground(new java.awt.Color(255, 255, 250));
        chkLapDiterima.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLapDiterima.setForeground(new java.awt.Color(0, 0, 0));
        chkLapDiterima.setText("Jam Laporan Diterima :");
        chkLapDiterima.setBorderPainted(true);
        chkLapDiterima.setBorderPaintedFlat(true);
        chkLapDiterima.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkLapDiterima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLapDiterima.setName("chkLapDiterima"); // NOI18N
        chkLapDiterima.setOpaque(false);
        chkLapDiterima.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLapDiterima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLapDiterimaActionPerformed(evt);
            }
        });
        internalFrame16.add(chkLapDiterima);
        chkLapDiterima.setBounds(114, 227, 160, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbJam2);
        cmbJam2.setBounds(280, 171, 45, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbJam3);
        cmbJam3.setBounds(280, 199, 45, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbJam4);
        cmbJam4.setBounds(280, 227, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbMnt2);
        cmbMnt2.setBounds(333, 171, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbMnt3);
        cmbMnt3.setBounds(333, 199, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbMnt4);
        cmbMnt4.setBounds(333, 227, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbDtk2);
        cmbDtk2.setBounds(386, 171, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbDtk3);
        cmbDtk3.setBounds(386, 199, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        internalFrame16.add(cmbDtk4);
        cmbDtk4.setBounds(386, 227, 45, 23);

        internalFrame15.add(internalFrame16, java.awt.BorderLayout.PAGE_START);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKritis.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbKritis.setName("tbKritis"); // NOI18N
        tbKritis.getTableHeader().setReorderingAllowed(false);
        tbKritis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKritisMouseClicked(evt);
            }
        });
        tbKritis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKritisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKritis);

        panelGlass9.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 48));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tgl. Simpan :");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel54);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("s.d.");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel55);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel6);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(195, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass10.add(TCari1);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+T");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 26));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Record :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass10.add(jLabel8);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(LCount1);

        panelGlass9.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame15.add(panelGlass9, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan8.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan8.setText("Simpan");
        BtnSimpan8.setToolTipText("Alt+S");
        BtnSimpan8.setName("BtnSimpan8"); // NOI18N
        BtnSimpan8.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan8ActionPerformed(evt);
            }
        });
        BtnSimpan8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan8KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan8);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
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
        panelGlass8.add(BtnBatal1);

        BtnHapus2.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus2.setText("Hapus");
        BtnHapus2.setToolTipText("Alt+H");
        BtnHapus2.setName("BtnHapus2"); // NOI18N
        BtnHapus2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus2ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnHapus2);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
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

        BtnDownload.setForeground(new java.awt.Color(0, 0, 0));
        BtnDownload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/export-excel.png"))); // NOI18N
        BtnDownload.setText("Download Data");
        BtnDownload.setName("BtnDownload"); // NOI18N
        BtnDownload.setPreferredSize(new java.awt.Dimension(135, 30));
        BtnDownload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDownloadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnDownload);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setText("Semua");
        BtnAll1.setToolTipText("Alt+M");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll1);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnKeluar2);

        internalFrame15.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        WindowNilaiKritis.getContentPane().add(internalFrame15, java.awt.BorderLayout.CENTER);

        WindowJamPemeriksaan.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowJamPemeriksaan.setName("WindowJamPemeriksaan"); // NOI18N
        WindowJamPemeriksaan.setUndecorated(true);
        WindowJamPemeriksaan.setResizable(false);

        internalFrame17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Jam Pemeriksaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.BorderLayout());

        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(2, 75));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(null);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Pasien :");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame18.add(jLabel40);
        jLabel40.setBounds(0, 10, 110, 23);

        TNoRw2.setEditable(false);
        TNoRw2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw2.setName("TNoRw2"); // NOI18N
        internalFrame18.add(TNoRw2);
        TNoRw2.setBounds(114, 10, 131, 23);

        TNoRM2.setEditable(false);
        TNoRM2.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM2.setName("TNoRM2"); // NOI18N
        internalFrame18.add(TNoRM2);
        TNoRM2.setBounds(247, 10, 70, 23);

        TPasien2.setEditable(false);
        TPasien2.setForeground(new java.awt.Color(0, 0, 0));
        TPasien2.setHighlighter(null);
        TPasien2.setName("TPasien2"); // NOI18N
        internalFrame18.add(TPasien2);
        TPasien2.setBounds(319, 10, 390, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Jam Pemeriksaan :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame18.add(jLabel41);
        jLabel41.setBounds(0, 38, 110, 23);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        internalFrame18.add(cmbJam5);
        cmbJam5.setBounds(114, 38, 45, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        internalFrame18.add(cmbMnt5);
        cmbMnt5.setBounds(165, 38, 45, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        internalFrame18.add(cmbDtk5);
        cmbDtk5.setBounds(217, 38, 45, 23);

        internalFrame17.add(internalFrame18, java.awt.BorderLayout.PAGE_START);

        Scroll7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Silahkan pilih/klik salah satu datanya dulu pada tabel ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbItem1.setToolTipText("Silahkan klik untuk memilih data yang dipilih");
        tbItem1.setName("tbItem1"); // NOI18N
        tbItem1.getTableHeader().setReorderingAllowed(false);
        tbItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbItem1MouseClicked(evt);
            }
        });
        tbItem1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbItem1KeyPressed(evt);
            }
        });
        Scroll7.setViewportView(tbItem1);

        internalFrame17.add(Scroll7, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan9.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan9.setText("Simpan");
        BtnSimpan9.setToolTipText("Alt+S");
        BtnSimpan9.setName("BtnSimpan9"); // NOI18N
        BtnSimpan9.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan9ActionPerformed(evt);
            }
        });
        BtnSimpan9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan9KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnSimpan9);

        BtnBatal2.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal2.setText("Baru");
        BtnBatal2.setToolTipText("Alt+B");
        BtnBatal2.setName("BtnBatal2"); // NOI18N
        BtnBatal2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal2ActionPerformed(evt);
            }
        });
        BtnBatal2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatal2KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnBatal2);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setText("Keluar");
        BtnKeluar3.setToolTipText("Alt+K");
        BtnKeluar3.setName("BtnKeluar3"); // NOI18N
        BtnKeluar3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar3ActionPerformed(evt);
            }
        });
        BtnKeluar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar3KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnKeluar3);

        internalFrame17.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        WindowJamPemeriksaan.getContentPane().add(internalFrame17, java.awt.BorderLayout.CENTER);

        WindowGantiUnitPengirim.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGantiUnitPengirim.setName("WindowGantiUnitPengirim"); // NOI18N
        WindowGantiUnitPengirim.setUndecorated(true);
        WindowGantiUnitPengirim.setResizable(false);

        internalFrame19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Unit Pengirim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(null);

        BtnCloseIn9.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn9.setText("Tutup");
        BtnCloseIn9.setToolTipText("Alt+U");
        BtnCloseIn9.setName("BtnCloseIn9"); // NOI18N
        BtnCloseIn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn9ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn9);
        BtnCloseIn9.setBounds(645, 30, 80, 30);

        BtnSimpan10.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan10.setText("Simpan");
        BtnSimpan10.setToolTipText("Alt+S");
        BtnSimpan10.setName("BtnSimpan10"); // NOI18N
        BtnSimpan10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnSimpan10);
        BtnSimpan10.setBounds(540, 30, 100, 30);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Nama Unit Pengirim : ");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame19.add(jLabel43);
        jLabel43.setBounds(0, 32, 140, 23);

        TunitPengirimGanti.setEditable(false);
        TunitPengirimGanti.setForeground(new java.awt.Color(0, 0, 0));
        TunitPengirimGanti.setName("TunitPengirimGanti"); // NOI18N
        internalFrame19.add(TunitPengirimGanti);
        TunitPengirimGanti.setBounds(143, 32, 355, 23);

        btnCariUnit.setForeground(new java.awt.Color(0, 0, 0));
        btnCariUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnCariUnit.setToolTipText("ALt+7");
        btnCariUnit.setName("btnCariUnit"); // NOI18N
        btnCariUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariUnitActionPerformed(evt);
            }
        });
        internalFrame19.add(btnCariUnit);
        btnCariUnit.setBounds(505, 32, 28, 23);

        WindowGantiUnitPengirim.getContentPane().add(internalFrame19, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Pemeriksaan Radiologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPeriksaRadiologi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbPeriksaRadiologi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPeriksaRadiologi.setComponentPopupMenu(jPopupMenu1);
        tbPeriksaRadiologi.setName("tbPeriksaRadiologi"); // NOI18N
        tbPeriksaRadiologi.getTableHeader().setReorderingAllowed(false);
        tbPeriksaRadiologi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPeriksaRadiologiMouseClicked(evt);
            }
        });
        tbPeriksaRadiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPeriksaRadiologiKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbPeriksaRadiologi);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 100));
        panelisi3.setLayout(null);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Pasien :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label15);
        label15.setBounds(0, 10, 105, 23);

        TNoRW.setEditable(false);
        TNoRW.setForeground(new java.awt.Color(0, 0, 0));
        TNoRW.setName("TNoRW"); // NOI18N
        TNoRW.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(TNoRW);
        TNoRW.setBounds(109, 10, 130, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label11);
        label11.setBounds(0, 40, 105, 23);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        panelisi3.add(Tgl1);
        Tgl1.setBounds(109, 40, 90, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Cara Bayar :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label16);
        label16.setBounds(670, 10, 100, 23);

        TnoRM.setEditable(false);
        TnoRM.setForeground(new java.awt.Color(0, 0, 0));
        TnoRM.setName("TnoRM"); // NOI18N
        TnoRM.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi3.add(TnoRM);
        TnoRM.setBounds(244, 10, 70, 23);

        Tpasien.setEditable(false);
        Tpasien.setForeground(new java.awt.Color(0, 0, 0));
        Tpasien.setName("Tpasien"); // NOI18N
        Tpasien.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(Tpasien);
        Tpasien.setBounds(318, 10, 350, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label18);
        label18.setBounds(200, 40, 30, 23);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        panelisi3.add(Tgl2);
        Tgl2.setBounds(233, 40, 90, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpnj.setEnabled(false);
        kdpnj.setName("kdpnj"); // NOI18N
        panelisi3.add(kdpnj);
        kdpnj.setBounds(775, 10, 60, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        nmpnj.setEnabled(false);
        nmpnj.setName("nmpnj"); // NOI18N
        panelisi3.add(nmpnj);
        nmpnj.setBounds(840, 10, 230, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        panelisi3.add(btnPenjab);
        btnPenjab.setBounds(1075, 10, 28, 23);

        BtnBersih.setForeground(new java.awt.Color(0, 0, 0));
        BtnBersih.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnBersih.setName("BtnBersih"); // NOI18N
        BtnBersih.setPreferredSize(new java.awt.Dimension(150, 23));
        BtnBersih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBersihActionPerformed(evt);
            }
        });
        panelisi3.add(BtnBersih);
        BtnBersih.setBounds(1110, 10, 30, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Poliklinik/Inst. :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelisi3.add(jLabel19);
        jLabel19.setBounds(670, 40, 100, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        kdpoli.setEnabled(false);
        kdpoli.setName("kdpoli"); // NOI18N
        panelisi3.add(kdpoli);
        kdpoli.setBounds(775, 40, 60, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TPoli.setEnabled(false);
        TPoli.setName("TPoli"); // NOI18N
        panelisi3.add(TPoli);
        TPoli.setBounds(840, 40, 230, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        panelisi3.add(BtnUnit);
        BtnUnit.setBounds(1075, 40, 28, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Diagnosa Klinis :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label12);
        label12.setBounds(0, 70, 105, 23);

        diagKlinis.setEditable(false);
        diagKlinis.setForeground(new java.awt.Color(0, 0, 0));
        diagKlinis.setName("diagKlinis"); // NOI18N
        diagKlinis.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(diagKlinis);
        diagKlinis.setBounds(109, 70, 560, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Status Transaksi :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label14);
        label14.setBounds(670, 70, 100, 23);

        cmbSttsTran.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsTran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Sudah Lunas", "Belum Bayar", "Piutang", "Transaksi Belum Selesai" }));
        cmbSttsTran.setName("cmbSttsTran"); // NOI18N
        panelisi3.add(cmbSttsTran);
        cmbSttsTran.setBounds(775, 70, 145, 23);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Key Word :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(170, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+5");
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
        panelisi1.add(BtnCari);

        BtnCariIgd.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariIgd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariIgd.setText("Tampilkan Khusus IGD");
        BtnCariIgd.setToolTipText("Alt+5");
        BtnCariIgd.setName("BtnCariIgd"); // NOI18N
        BtnCariIgd.setPreferredSize(new java.awt.Dimension(170, 23));
        BtnCariIgd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariIgdActionPerformed(evt);
            }
        });
        BtnCariIgd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariIgdKeyPressed(evt);
            }
        });
        panelisi1.add(BtnCariIgd);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi1.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi1.add(LCount);

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
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
        panelisi1.add(BtnHapus);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
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
        panelisi1.add(BtnAll);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
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
        panelisi1.add(BtnKeluar);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tgl. Nota/Kwitansi : ");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi1.add(jLabel29);

        tglNota.setEditable(false);
        tglNota.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-09-2026" }));
        tglNota.setDisplayFormat("dd-MM-yyyy");
        tglNota.setName("tglNota"); // NOI18N
        tglNota.setOpaque(false);
        panelisi1.add(tglNota);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

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
        TNoRW.setText("");
        TnoRM.setText("");
        Tpasien.setText("");
        diagKlinis.setText("");
        khususIgd = "tidak";
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
        TNoRW.setText("");
        TnoRM.setText("");
        Tpasien.setText("");
        diagKlinis.setText("");
        khususIgd = "tidak";
        empttext();
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        WindowGantiDokterPerujuk.dispose();
        WindowGantiDokterRad.dispose();
        WindowGantiPetugasRadiologi.dispose();
        WindowGantiRujukan.dispose();
        WindowHasil.dispose();
        WindowRiwayatExpertise.dispose();
        WindowWaktuPeriksa.dispose();
        WindowBeratBadan.dispose();
        WindowParameter.dispose();
        WindowNilaiKritis.dispose();
        WindowJamPemeriksaan.dispose();
        WindowGantiUnitPengirim.dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
        TCari.requestFocus();
    } else if (Kd2.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik No.Rawat pada tabel untuk memilih...!!!!");
    } else if (!(Kd2.getText().trim().equals(""))) {
        if (akses.getadmin() == true) {
            try {
                Sequel.AutoComitFalse();
                status = "";
                ttljmdokter = 0;
                ttljmpetugas = 0;
                ttlkso = 0;
                ttlpendapatan = 0;
                ttlbhp = 0;
                ttljmdokter = Sequel.cariIsiAngka("select sum(tarif_perujuk)+sum(tarif_tindakan_dokter) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                        + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                        + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                ttljmpetugas = Sequel.cariIsiAngka("select sum(tarif_tindakan_petugas) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                        + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                        + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                ttlkso = Sequel.cariIsiAngka("select sum(kso) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                        + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                        + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                ttlpendapatan = Sequel.cariIsiAngka("select sum(biaya) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                        + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                        + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");

                status = Sequel.cariIsi("select status from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                        + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                        + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");

                if (Sequel.queryu2tf("delete from periksa_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
                    tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
                }) == true) {
//                    Sequel.queryu2("delete from hasil_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
//                        tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
//                    });
                    ps3.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                    ps3.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                    ps3.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        Sequel.mengedit("ipsrsbarang", "kode_brng=?", "stok=stok+?", 2, new String[]{
                            rs3.getString("jumlah"), rs3.getString("kode_brng")
                        });
                    }
                    ttlbhp = Sequel.cariIsiAngka("select sum(total) from beri_bhp_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                    if (Sequel.queryu2tf("delete from beri_bhp_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
                        tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
                    }) == false) {
                        ttlbhp = 0;
                    }
                    if (status.equals("Ranap")) {
                        Sequel.queryu("delete from tampjurnal");
                        if (ttlpendapatan > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Radiologi_Ranap + "','Suspen Piutang Radiologi Ranap','0','" + ttlpendapatan + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Pendapatan Radiologi Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                        }
                        if (ttljmdokter > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ranap + "','Beban Jasa Medik Dokter Radiologi Ranap','0','" + ttljmdokter + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ranap + "','Utang Jasa Medik Dokter Radiologi Ranap','" + ttljmdokter + "','0'", "Rekening");
                        }
                        if (ttljmpetugas > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ranap + "','Beban Jasa Medik Petugas Radiologi Ranap','0','" + ttljmpetugas + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ranap + "','Utang Jasa Medik Petugas Radiologi Ranap','" + ttljmpetugas + "','0'", "Rekening");
                        }
                        if (ttlbhp > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Inap + "','HPP Persediaan Radiologi Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Inap + "','Persediaan BHP Radiologi Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                        }
                        if (ttlkso > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Radiologi_Ranap + "','Beban KSO Radiologi Ranap','0','" + ttlkso + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Radiologi_Ranap + "','Utang KSO Radiologi Ranap','" + ttlkso + "','0'", "Rekening");
                        }
                        jur.simpanJurnal(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), Sequel.cariIsi("select current_date()"), "U", "PEMBATALAN PEMERIKSAAN RADIOLOGI RAWAT INAP PASIEN OLEH " + akses.getkode());
                    }
                }
                Sequel.AutoComitTrue();
                TNoRW.setText("");
                TnoRM.setText("");
                Tpasien.setText("");
                diagKlinis.setText("");
                khususIgd = "tidak";
                tampil();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada table untuk memilih data...!!!!");
            }
        } else {
            cekRad = 0;
            cekDataRad = "select count(1) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                    + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                    + "' and stts_bayar = 'Belum' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'";

            if (Sequel.cariIsiAngka(cekDataRad) == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                TCari.requestFocus();
            } else {
                try {
                    Sequel.AutoComitFalse();
                    status = "";
                    ttljmdokter = 0;
                    ttljmpetugas = 0;
                    ttlkso = 0;
                    ttlpendapatan = 0;
                    ttlbhp = 0;
                    ttljmdokter = Sequel.cariIsiAngka("select sum(tarif_perujuk)+sum(tarif_tindakan_dokter) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                    ttljmpetugas = Sequel.cariIsiAngka("select sum(tarif_tindakan_petugas) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                    ttlkso = Sequel.cariIsiAngka("select sum(kso) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                    ttlpendapatan = Sequel.cariIsiAngka("select sum(biaya) from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");

                    status = Sequel.cariIsi("select status from periksa_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                            + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                            + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");

                    if (Sequel.queryu2tf("delete from periksa_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
                        tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
                    }) == true) {
//                        Sequel.queryu2("delete from hasil_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
//                            tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
//                        });
                        ps3.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                        ps3.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                        ps3.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            Sequel.mengedit("ipsrsbarang", "kode_brng=?", "stok=stok+?", 2, new String[]{
                                rs3.getString("jumlah"), rs3.getString("kode_brng")
                            });
                        }
                        ttlbhp = Sequel.cariIsiAngka("select sum(total) from beri_bhp_radiologi where no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0)
                                + "' and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3)
                                + "' and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4) + "'");
                        if (Sequel.queryu2tf("delete from beri_bhp_radiologi where no_rawat=? and tgl_periksa=? and jam=?", 3, new String[]{
                            tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(), tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString()
                        }) == false) {
                            ttlbhp = 0;
                        }
                        if (status.equals("Ranap")) {
                            Sequel.queryu("delete from tampjurnal");
                            if (ttlpendapatan > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Radiologi_Ranap + "','Suspen Piutang Radiologi Ranap','0','" + ttlpendapatan + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Radiologi_Ranap + "','Pendapatan Radiologi Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                            }
                            if (ttljmdokter > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Radiologi_Ranap + "','Beban Jasa Medik Dokter Radiologi Ranap','0','" + ttljmdokter + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Radiologi_Ranap + "','Utang Jasa Medik Dokter Radiologi Ranap','" + ttljmdokter + "','0'", "Rekening");
                            }
                            if (ttljmpetugas > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Petugas_Radiologi_Ranap + "','Beban Jasa Medik Petugas Radiologi Ranap','0','" + ttljmpetugas + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Petugas_Radiologi_Ranap + "','Utang Jasa Medik Petugas Radiologi Ranap','" + ttljmpetugas + "','0'", "Rekening");
                            }
                            if (ttlbhp > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + HPP_Persediaan_Radiologi_Rawat_Inap + "','HPP Persediaan Radiologi Rawat Inap','0','" + ttlbhp + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Persediaan_BHP_Radiologi_Rawat_Inap + "','Persediaan BHP Radiologi Rawat Inap','" + ttlbhp + "','0'", "Rekening");
                            }
                            if (ttlkso > 0) {
                                Sequel.menyimpan("tampjurnal", "'" + Beban_Kso_Radiologi_Ranap + "','Beban KSO Radiologi Ranap','0','" + ttlkso + "'", "Rekening");
                                Sequel.menyimpan("tampjurnal", "'" + Utang_Kso_Radiologi_Ranap + "','Utang KSO Radiologi Ranap','" + ttlkso + "','0'", "Rekening");
                            }
                            jur.simpanJurnal(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(), Sequel.cariIsi("select current_date()"), "U", "PEMBATALAN PEMERIKSAAN RADIOLOGI RAWAT INAP PASIEN OLEH " + akses.getkode());
                        }
                    }
                    Sequel.AutoComitTrue();
                    TNoRW.setText("");
                    TnoRM.setText("");
                    Tpasien.setText("");
                    diagKlinis.setText("");
                    khususIgd = "tidak";
                    tampil();
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                    JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...  Klik data pada table untuk memilih data...!!!!");
                }
            }
        }
    }
}//GEN-LAST:event_BtnHapusActionPerformed

private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnHapusActionPerformed(null);
    } else {
        Valid.pindah(evt, TCari, BtnAll);
    }
}//GEN-LAST:event_BtnHapusKeyPressed

private void tbPeriksaRadiologiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPeriksaRadiologiMouseClicked
    if (tabMode.getRowCount() != 0) {
        try {
            getData();
        } catch (java.lang.NullPointerException e) {
        }
    }
}//GEN-LAST:event_tbPeriksaRadiologiMouseClicked

private void tbPeriksaRadiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPeriksaRadiologiKeyPressed
    if (tabMode.getRowCount() != 0) {
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }
}//GEN-LAST:event_tbPeriksaRadiologiKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        khususIgd = "tidak";
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void MnHasilPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanActionPerformed
        lihatHasil = "";
        cekPeriksa = 0;

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                cekPeriksa = Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'");
                if (cekPeriksa == 1) {
                    kdItem = Sequel.cariIsi("select kd_jenis_prw from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'");
                    itemDipilih.setText(Sequel.cariIsi("select nm_perawatan from jns_perawatan_radiologi where kd_jenis_prw='" + kdItem + "'"));
                    deskripsiHasil();
                    HasilPeriksa.requestFocus();
                } else {
                    kdItem = "";
                    itemDipilih.setText("");
                    tbPemeriksaan.requestFocus();
                }

                lihatHasil = "OK";
                tampilItem();
                WindowHasil.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                WindowHasil.setLocationRelativeTo(internalFrame1);
                WindowHasil.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowHasil.dispose();
        TNoRW.setText("");
        TnoRM.setText("");
        Tpasien.setText("");
        diagKlinis.setText("");
        khususIgd = "tidak";
        empttext();
        tampil();
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void BtnPrint1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint1ActionPerformed
        cekHasil = 0;
        String isi;

        if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...");
        } else if (kdItem.equals("") || tglhasil.equals("") || jamhasil.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih salah satu dulu item pemeriksaan yang akan diprint pada tabel...");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (cmbCetak.getSelectedIndex() == 0) {
                cekHasil = Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                        + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'");
                
                if (cekHasil == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, untuk hasil pemeriksaan radiologi " + itemDipilih.getText() + " belum tersimpan...!!!!");
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(waktu_simpan) from hasil_radiologi where "
                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")));
                    param.put("tglperiksa", Sequel.cariIsi("select date_format(tgl_periksa,'%d') from hasil_radiologi where "
                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.cariIsi("select year(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"));

                    if (sttsRawat.equals("Ralan")) {
                        param.put("kamar", "Poliklinik/Inst.");
                        param.put("namakamar", Sequel.cariIsi("select nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + Kd2.getText() + "'"));
                    } else {
                        kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + Kd2.getText() + "' order by tgl_masuk desc limit 1");
                        param.put("kamar", "Ruang Rawat");
                        param.put("namakamar", Sequel.cariIsi("select nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "'"));
                    }

                    cekRujukan = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + Kd2.getText() + "'");
                    if (cekRujukan == 0) {
                        param.put("namaFaskes", "-");
                    } else {
                        if (kodeRujukan.equals("784")) {
                            param.put("namaFaskes", "-");
                        } else {
                            param.put("namaFaskes", Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                        }
                    }
                    
                    if (Sequel.cariInteger("select count(-1) from pembaca_hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                            + "kd_jenis_prw='" + kdItem + "' and tgl_periksa='" + tglhasil + "' and jam_periksa='" + jamhasil + "'") == 0) {
                        param.put("judulPembaca", "");
                        param.put("dokterBaca", "");
                    } else {
                        tampilPembaca(Kd2.getText(), kdItem, tglhasil, jamhasil);
                        param.put("judulPembaca", "Hasil pemeriksaan Radiologi telah dibaca oleh dokter : ");
                        param.put("dokterBaca", dokterBaca);
                    }

                    Valid.MyReport("rptPeriksaRadiologi.jasper", "report", "::[ Lembar Hasil Pemeriksaan Radiologi ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, concat(IF(p.jk='L','Laki-laki','Perempuan'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') jk_umur, p.alamat, pr.no_rawat, "
                            + "jpr.nm_perawatan, hr.diag_klinis_radiologi, d1.nm_dokter dr_pengirim, hr.jam, hr.hasil, d2.nm_dokter dr_rad FROM periksa_radiologi pr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "INNER JOIN dokter d1 on d1.kd_dokter=pr.dokter_perujuk INNER JOIN dokter d2 on d2.kd_dokter=pr.kd_dokter "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.tgl_periksa=pr.tgl_periksa and hr.jam=pr.jam and hr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "WHERE hr.no_rawat='" + Kd2.getText() + "' and hr.tgl_periksa='" + tglhasil + "' and hr.jam='" + jamhasil + "' and hr.kd_jenis_prw='" + kdItem + "'", param);
                }
                
            } else if (cmbCetak.getSelectedIndex() == 1) {
                cekHasil = Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                        + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'");

                if (cekHasil == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, untuk hasil pemeriksaan radiologi " + itemDipilih.getText() + " belum tersimpan...!!!!");
                } else {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("alamatrs", akses.getalamatrs());
                    param.put("kotars", akses.getkabupatenrs());
                    param.put("propinsirs", akses.getpropinsirs());
                    param.put("kontakrs", akses.getkontakrs());
                    param.put("emailrs", akses.getemailrs());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Sequel.cariIsi("select date(waktu_simpan) from hasil_radiologi where "
                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")));
                    param.put("tglperiksa", Sequel.cariIsi("select date_format(tgl_periksa,'%d') from hasil_radiologi where "
                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.bulanINDONESIA("select month(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'") + " "
                            + Sequel.cariIsi("select year(tgl_periksa) from hasil_radiologi where "
                                    + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"));

                    if (sttsRawat.equals("Ralan")) {
                        param.put("kamar", "Poliklinik/Inst.");
                        param.put("namakamar", Sequel.cariIsi("select nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + Kd2.getText() + "'"));
                    } else {
                        kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + Kd2.getText() + "' order by tgl_masuk desc limit 1");
                        param.put("kamar", "Ruang Rawat");
                        param.put("namakamar", Sequel.cariIsi("select nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "'"));
                    }

                    cekRujukan = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + Kd2.getText() + "'");
                    if (cekRujukan == 0) {
                        param.put("namaFaskes", "-");
                    } else {
                        if (kodeRujukan.equals("784")) {
                            param.put("namaFaskes", "-");
                        } else {
                            param.put("namaFaskes", Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                        }
                    }
                    
                    if (Sequel.cariInteger("select count(-1) from pembaca_hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                            + "kd_jenis_prw='" + kdItem + "' and tgl_periksa='" + tglhasil + "' and jam_periksa='" + jamhasil + "'") == 0) {
                        param.put("judulPembaca", "");
                        param.put("dokterBaca", "");
                    } else {
                        tampilPembaca(Kd2.getText(), kdItem, tglhasil, jamhasil);
                        param.put("judulPembaca", "Hasil pemeriksaan Radiologi telah dibaca oleh dokter : ");
                        param.put("dokterBaca", dokterBaca);
                    }
                    
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='006'"),
                                    "Hasil Pemeriksaan Radiologi (Expertise)", TDokter.getText(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from hasil_radiologi where "
                                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from hasil_radiologi where "
                                            + "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'")) + "') from kalimat_tte where kode='006'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Radiologi", Sequel.cariFolderPrintTte());
                    param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='006'"));
                    
                    Valid.MyReport("rptPeriksaRadiologiQr.jasper", "report", "::[ Lembar Hasil Pemeriksaan Radiologi ]::",
                            "SELECT p.no_rkm_medis, p.nm_pasien, concat(IF(p.jk='L','Laki-laki','Perempuan'),' / ',rp.umurdaftar,' ',rp.sttsumur,'.') jk_umur, p.alamat, pr.no_rawat, "
                            + "jpr.nm_perawatan, hr.diag_klinis_radiologi, d1.nm_dokter dr_pengirim, hr.jam, hr.hasil, d2.nm_dokter dr_rad FROM periksa_radiologi pr "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "INNER JOIN dokter d1 on d1.kd_dokter=pr.dokter_perujuk INNER JOIN dokter d2 on d2.kd_dokter=pr.kd_dokter "
                            + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.tgl_periksa=pr.tgl_periksa and hr.jam=pr.jam and hr.kd_jenis_prw=pr.kd_jenis_prw "
                            + "WHERE hr.no_rawat='" + Kd2.getText() + "' and hr.tgl_periksa='" + tglhasil + "' and hr.jam='" + jamhasil + "' and hr.kd_jenis_prw='" + kdItem + "'", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            }            
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrint1ActionPerformed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
        } else if (kdItem.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu Item Pemeriksaan Radiologinya pada tabel...!!!!");
            tbPemeriksaan.requestFocus();
        } else {
            if (HasilPeriksa.getText().equals("")) {
                Sequel.queryu2("delete from hasil_radiologi where no_rawat=? and tgl_periksa=? and jam=? and kd_jenis_prw=?", 4, new String[]{
                    Kd2.getText(), tglhasil, jamhasil, kdItem
                });
            } else {
                cekHasil = 0;
                cekHasil = Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                        + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'");

                if (cekHasil == 0) {
                    Sequel.menyimpan("hasil_radiologi", "'" + Kd2.getText() + "','" + tglhasil + "','" + jamhasil + "',"
                            + "'" + HasilPeriksa.getText() + "','" + diagKlinisRad.getText() + "','" + kdItem + "',"
                            + "'" + Sequel.cariIsi("select now()") + "'", "Hasil Pemeriksaan Radiologi");
                } else {
                    Sequel.mengedit("hasil_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'",
                            "hasil='" + HasilPeriksa.getText() + "', diag_klinis_radiologi='" + diagKlinisRad.getText() + "', kd_jenis_prw='" + kdItem + "'");
                }
                
                if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and status='1' "
                        + "and (nm_dokter like '%dr.%' or nm_dokter like '%drg.%')") > 0) {
                    Sequel.menyimpanIgnore("pembaca_hasil_radiologi",
                            "'" + Kd2.getText() + "','" + akses.getkode() + "','" + kdItem + "','" + tglhasil + "','" + jamhasil + "',"
                            + "'" + Sequel.cariIsi("select now()") + "'", "Pembaca hasil radiologi");
                }
            }

            JOptionPane.showMessageDialog(null, "Deskripsi hasil pemeriksaan " + itemDipilih.getText() + " berhasil disimpan / diupdate..!!!");
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

	private void MnDokumenPenunjangMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenPenunjangMedisActionPerformed
            if (tabMode.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            } else if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
                khususIgd = "tidak";
                tampil();
                tbPeriksaRadiologi.requestFocus();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCariPeriksaRadiologi");
                RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
                form.setData(Kd2.getText(), TnoRM.getText(), Tpasien.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
    }//GEN-LAST:event_MnDokumenPenunjangMedisActionPerformed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        initPenjab();
        akses.setform("DlgCariPeriksaRadiologi");
        penjab.isCek();
        penjab.onCari();
        penjab.setSize(868, internalFrame1.getHeight() - 40);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setVisible(true);
        penjab.TCari.requestFocus();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void BtnBersihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBersihActionPerformed
        kdpnj.setText("");
        nmpnj.setText("");
        kdpoli.setText("");
        TPoli.setText("");
    }//GEN-LAST:event_BtnBersihActionPerformed

    private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
        TPoli.setText("");
        kdpoli.setText("");
        dipilih = 0;
        dipilih = 1;

        initPoli();
        akses.setform("DlgCariPeriksaRadiologi");
        poli.isCek();
        poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);

    }//GEN-LAST:event_BtnUnitActionPerformed

    private void MnInapSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTISemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Rawat Inap Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and periksa_radiologi.status='Ranap' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapSemuaCaraBayarActionPerformed

    private void MnInapPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTIPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Rawat Inap Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ranap' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapPerCaraBayarActionPerformed

    private void MnJalanSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTJSemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Rawat Jalan Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and periksa_radiologi.status='Ralan' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanSemuaCaraBayarActionPerformed

    private void MnJalanPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTJPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Rawat Jalan Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ralan' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanPerCaraBayarActionPerformed

    private void MnJalanPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTJPerPoli.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Rawat Jalan Per Unit/Poliklinik ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ralan' and reg_periksa.kd_poli='" + kdpoli.getText() + "' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanPerPoliActionPerformed

    private void MnSRSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTSRSemuaCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Semua Jenis Rawat Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRSemuaCaraBayarActionPerformed

    private void MnSRPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRTSRPerCaraBayar.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Radiologi Semua Jenis Rawat Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, dokter.nm_dokter as dokter_rad, periksa_radiologi.tgl_periksa, "
                + " sum(periksa_radiologi.bagian_rs) as jasa_rs, sum(periksa_radiologi.bhp) as bakhp, sum(periksa_radiologi.tarif_tindakan_dokter) as jasa_dokter, "
                + " sum(periksa_radiologi.tarif_tindakan_petugas) as jasa_operator, sum(periksa_radiologi.biaya) as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " GROUP BY periksa_radiologi.no_rawat, periksa_radiologi.tgl_periksa order by penjab.png_jawab, periksa_radiologi.tgl_periksa", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRPerCaraBayarActionPerformed

    private void MnInapDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTISemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Rawat Inap Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and periksa_radiologi.status='Ranap' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapDetailSemuaCaraBayarActionPerformed

    private void MnInapDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInapDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTIPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Rawat Inap Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ranap' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnInapDetailPerCaraBayarActionPerformed

    private void MnJalanDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTJSemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Rawat Jalan Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and periksa_radiologi.status='Ralan' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailSemuaCaraBayarActionPerformed

    private void MnJalanDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTJPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Rawat Jalan Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ralan' and reg_periksa.kd_pj='" + kdpnj.getText() + "' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailPerCaraBayarActionPerformed

    private void MnJalanDetailPerPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJalanDetailPerPoliActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTJPerPoli.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Rawat Jalan Per Unit/Poliklinik ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " and periksa_radiologi.status='Ralan' and reg_periksa.kd_poli='" + kdpoli.getText() + "' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnJalanDetailPerPoliActionPerformed

    private void MnSRDetailSemuaCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRDetailSemuaCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTSRSemuaCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Semua Jenis Rawat Semua Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRDetailSemuaCaraBayarActionPerformed

    private void MnSRDetailPerCaraBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSRDetailPerCaraBayarActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptRadRDTSRPerCaraBayar.jasper", "report", "::[ Laporan Detail Rekap Total Pemeriksaan Radiologi Semua Jenis Rawat Per Cara Bayar ]::",
                " SELECT (SELECT MIN(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_awal, "
                + " (SELECT MAX(tgl_periksa) FROM periksa_radiologi WHERE periksa_radiologi.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') AS tgl_akhir, "
                + " periksa_radiologi.no_rawat, reg_periksa.no_rkm_medis, pasien.nm_pasien, "
                + " CONCAT(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur) as umur, "
                + " periksa_radiologi.`status`, IF (reg_periksa.status_lanjut = 'RANAP', b.nm_bangsal, p.nm_poli) unit, "
                + " penjab.png_jawab, periksa_radiologi.tgl_periksa, dokter.nm_dokter, jns_perawatan_radiologi.nm_perawatan, "
                + " periksa_radiologi.bagian_rs as jasa_rs, periksa_radiologi.bhp as bakhp, periksa_radiologi.tarif_tindakan_dokter as jasa_dokter, "
                + " periksa_radiologi.tarif_tindakan_petugas as jasa_operator, periksa_radiologi.biaya as total_biaya "
                + " FROM periksa_radiologi INNER JOIN reg_periksa INNER JOIN pasien INNER JOIN petugas "
                + " INNER JOIN dokter ON periksa_radiologi.no_rawat = reg_periksa.no_rawat AND reg_periksa.no_rkm_medis = pasien.no_rkm_medis AND periksa_radiologi.kd_dokter = dokter.kd_dokter AND periksa_radiologi.nip = petugas.nip "
                + " INNER JOIN jns_perawatan_radiologi ON periksa_radiologi.kd_jenis_prw = jns_perawatan_radiologi.kd_jenis_prw "
                + " INNER JOIN penjab on reg_periksa.kd_pj = penjab.kd_pj "
                + " LEFT JOIN kamar_inap ki on ki.no_rawat = reg_periksa.no_rawat and ki.stts_pulang <> 'Pindah Kamar' "
                + " LEFT JOIN kamar k on k.kd_kamar = ki.kd_kamar "
                + " LEFT JOIN bangsal b on b.kd_bangsal = k.kd_bangsal "
                + " LEFT JOIN poliklinik p on p.kd_poli = reg_periksa.kd_poli "
                + " where periksa_radiologi.tgl_periksa between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + " AND reg_periksa.kd_pj='" + kdpnj.getText() + "' order by periksa_radiologi.tgl_periksa, periksa_radiologi.no_rawat, penjab.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnSRDetailPerCaraBayarActionPerformed

    private void MnRekapJenisPeriksaRadInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapJenisPeriksaRadInapActionPerformed
        if (kdpnj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu cara bayar pasiennya..!!");
            btnPenjab.requestFocus();
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
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Jenis Bayar " + nmpnj.getText());
            Valid.MyReport("rptRekapJenisPeriksaRadInap.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Radiologi Rawat Inap Sesuai Cara Bayarnya ]::",
                    " SELECT jpr.nm_perawatan, COUNT(*) total_pemeriksaan, jpr.total_byr tarif_dsr, (COUNT(*)*jpr.total_byr) biaya FROM periksa_radiologi pr  "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat  "
                    + "INNER JOIN penjab p ON p.kd_pj=rp.kd_pj  "
                    + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw  "
                    + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "AND jpr.kd_jenis_prw NOT IN ('RD0371', 'RD0372') AND pr.`status`='Ranap' and p.png_jawab like '%" + nmpnj.getText() + "%' "
                    + "GROUP BY jpr.nm_perawatan ORDER BY jpr.nm_perawatan", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapJenisPeriksaRadInapActionPerformed

    private void MnRekapJenisPeriksaRadJalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapJenisPeriksaRadJalanActionPerformed
        if (kdpnj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu cara bayar pasiennya..!!");
            btnPenjab.requestFocus();
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
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Jenis Bayar " + nmpnj.getText());
            Valid.MyReport("rptRekapJenisPeriksaRadJalan.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Radiologi Rawat Jalan Sesuai Cara Bayarnya ]::",
                    " SELECT jpr.nm_perawatan, COUNT(*) total_pemeriksaan, jpr.total_byr tarif_dsr, (COUNT(*)*jpr.total_byr) biaya FROM periksa_radiologi pr  "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat  "
                    + "INNER JOIN penjab p ON p.kd_pj=rp.kd_pj  "
                    + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw  "
                    + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'  "
                    + "AND jpr.kd_jenis_prw NOT IN ('RD0371', 'RD0372') AND pr.`status`='Ralan' and p.png_jawab like '%" + nmpnj.getText() + "%' "
                    + "GROUP BY jpr.nm_perawatan ORDER BY jpr.nm_perawatan", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapJenisPeriksaRadJalanActionPerformed

    private void MnRekapJenisPeriksaRadSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapJenisPeriksaRadSemuaActionPerformed
        if (kdpnj.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih dulu salah satu cara bayar pasiennya..!!");
            btnPenjab.requestFocus();
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
            param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem() + " Untuk Jenis Bayar " + nmpnj.getText());
            Valid.MyReport("rptRekapJenisPeriksaRadSemua.jasper", "report", "::[ Laporan Rekap Total Jenis Pemeriksaan Radiologi Semua Rawat Sesuai Cara Bayarnya ]::",
                    " SELECT jpr.nm_perawatan, COUNT(*) total_pemeriksaan, jpr.total_byr tarif_dsr, (COUNT(*)*jpr.total_byr) biaya FROM periksa_radiologi pr  "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat  "
                    + "INNER JOIN penjab p ON p.kd_pj=rp.kd_pj "
                    + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw  "
                    + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                    + "AND jpr.kd_jenis_prw NOT IN ('RD0371', 'RD0372') and p.png_jawab like '%" + nmpnj.getText() + "%' GROUP BY jpr.nm_perawatan ORDER BY jpr.nm_perawatan", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRekapJenisPeriksaRadSemuaActionPerformed

    private void MnPendapatanRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanRalanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Radiologi Rawat Jalan");
        Valid.MyReport("rptRekapPendapatanRadiologi.jasper", "report", "::[ Laporan Rekap Pendapatan Radiologi Rawat Jalan ]::",
                "SELECT pj.png_jawab, sum(pr.biaya) total_biaya FROM periksa_radiologi pr "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat "
                + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                + "LEFT JOIN kamar_inap ki ON ki.no_rawat = rp.no_rawat AND ki.stts_pulang not in ('Pindah Kamar','-') "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and rp.stts_daftar<>'Batal' AND pr.`status`='Ralan' GROUP BY pj.kd_pj ORDER BY sum(pr.biaya) DESC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanRalanActionPerformed

    private void MnPendapatanRanapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanRanapActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Radiologi Rawat Inap");
        Valid.MyReport("rptRekapPendapatanRadiologi.jasper", "report", "::[ Laporan Rekap Pendapatan Radiologi Rawat Inap ]::",
                "SELECT pj.png_jawab, sum(pr.biaya) total_biaya FROM periksa_radiologi pr "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat "
                + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                + "LEFT JOIN kamar_inap ki ON ki.no_rawat = rp.no_rawat AND ki.stts_pulang not in ('Pindah Kamar','-') "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and rp.stts_daftar<>'Batal' AND pr.`status`='Ranap' GROUP BY pj.kd_pj ORDER BY sum(pr.biaya) DESC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanRanapActionPerformed

    private void MnPendapatanSemuaRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPendapatanSemuaRawatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        param.put("judul", "Laporan Rekap Pendapatan Radiologi Semua Rawat");
        Valid.MyReport("rptRekapPendapatanRadiologi.jasper", "report", "::[ Laporan Rekap Pendapatan Radiologi Semua Rawat ]::",
                "SELECT pj.png_jawab, sum(pr.biaya) total_biaya FROM periksa_radiologi pr "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat "
                + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj "
                + "LEFT JOIN kamar_inap ki ON ki.no_rawat = rp.no_rawat AND ki.stts_pulang not in ('Pindah Kamar','-') "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "and rp.stts_daftar<>'Batal' GROUP BY pj.kd_pj ORDER BY sum(pr.biaya) DESC", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnPendapatanSemuaRawatActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        if (lihatHasil.equals("")) {
            WindowGantiDokterRad.dispose();
            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();
        } else if (lihatHasil.equals("OK")) {
            WindowGantiDokterRad.dispose();
        }
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (kddokter.getText().trim().equals("") || TDokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "Dokter");
        } else {
            if (lihatHasil.equals("")) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                        "kd_dokter='" + kddokter.getText() + "'");

                TNoRW.setText("");
                TnoRM.setText("");
                Tpasien.setText("");
                diagKlinis.setText("");
                khususIgd = "tidak";
                empttext();
                tampil();

            } else if (lihatHasil.equals("OK")) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'",
                        "kd_dokter='" + kddokter.getText() + "'");
                dokterRad.setText(TDokter.getText());
            }

            WindowGantiDokterRad.dispose();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnCariDokterActionPerformed(null);
        }
    }//GEN-LAST:event_kddokterKeyPressed

    private void btnCariDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokterActionPerformed
        initDokter();
        akses.setform("DlgCariPeriksaRadiologi");
        pilihanDokter = 1;
        dokter.isCek();
        dokter.setSize(1046, 341);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.TCari.setText("rad");
        dokter.tampil();
        dokter.setVisible(true);
    }//GEN-LAST:event_btnCariDokterActionPerformed

    private void MnDokterPemeriksaRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterPemeriksaRadActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            WindowGantiDokterRad.setSize(743, 82);
            WindowGantiDokterRad.setLocationRelativeTo(internalFrame1);
            WindowGantiDokterRad.setVisible(true);
            btnCariDokter.requestFocus();
        }
    }//GEN-LAST:event_MnDokterPemeriksaRadActionPerformed

    private void MnRiwayatPerawatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerawatanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu datanya pada tabel, klik pada nama pasiennya...!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(TnoRM.getText(), Tpasien.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRiwayatPerawatanActionPerformed

    private void MnDokterPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterPengirimActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            WindowGantiDokterPerujuk.setSize(743, 82);
            WindowGantiDokterPerujuk.setLocationRelativeTo(internalFrame1);
            WindowGantiDokterPerujuk.setVisible(true);
            btnCariDokter1.requestFocus();
        }
    }//GEN-LAST:event_MnDokterPengirimActionPerformed

    private void MnWaktuPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnWaktuPeriksaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            Valid.SetTgl(tanggalPeriksa, tglperiksa);
            cmbJam1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(3, 5));
            cmbDtk1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(6, 8));

            WindowWaktuPeriksa.setSize(318, 136);
            WindowWaktuPeriksa.setLocationRelativeTo(internalFrame1);
            WindowWaktuPeriksa.setVisible(true);
            tanggalPeriksa.requestFocus();
        }
    }//GEN-LAST:event_MnWaktuPeriksaActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        if (lihatHasil.equals("")) {
            WindowWaktuPeriksa.dispose();
            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();
        } else if (lihatHasil.equals("OK")) {
            WindowWaktuPeriksa.dispose();
            tampilItem();
        }
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void BtnCloseIn6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn6KeyPressed

    private void tanggalPeriksaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tanggalPeriksaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalPeriksaItemStateChanged

    private void tanggalPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tanggalPeriksaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tanggalPeriksaKeyPressed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (lihatHasil.equals("")) {
            Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                    "tgl_periksa='" + Valid.SetTgl(tanggalPeriksa.getSelectedItem() + "") + "', "
                    + "jam='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "'");

            Sequel.mengedit("hasil_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                    "tgl_periksa='" + Valid.SetTgl(tanggalPeriksa.getSelectedItem() + "") + "', "
                    + "jam='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "'");

            khususIgd = "tidak";
            tampil();
            empttext();

        } else if (lihatHasil.equals("OK")) {
            Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'",
                    "tgl_periksa='" + Valid.SetTgl(tanggalPeriksa.getSelectedItem() + "") + "', "
                    + "jam='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "'");

            Sequel.mengedit("hasil_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'",
                    "tgl_periksa='" + Valid.SetTgl(tanggalPeriksa.getSelectedItem() + "") + "', "
                    + "jam='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "'");

            tglhasil = Valid.SetTgl(tanggalPeriksa.getSelectedItem() + "");
            jamhasil = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            tglperiksarad.setText(Sequel.cariIsi("select date_format('" + tglhasil + "','%d %M %Y')"));
            jamperiksarad.setText(jamhasil);
            tampilItem();
        }

        WindowWaktuPeriksa.dispose();
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void cmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam1KeyPressed
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1KeyPressed

    private void cmbMnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt1KeyPressed
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1KeyPressed

    private void cmbDtk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk1KeyPressed
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1KeyPressed

    private void MnRiwayatPerawatan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatPerawatan1ActionPerformed
        if (lihatHasil.equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(TnoRM.getText(), Tpasien.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());

        } else if (lihatHasil.equals("OK")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(norm.getText(), nmpasien.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnRiwayatPerawatan1ActionPerformed

    private void MnKirimKeWhatsappActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWhatsappActionPerformed
        if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...");
        } else if (kdItem.equals("") || tglhasil.equals("") || jamhasil.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan anda pilih salah satu dulu item pemeriksaan yang akan diprint pada tabel...");
        } else {
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCariPeriksaRadiologi");
            DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
            form.setSize(557, 148);
            form.setLocationRelativeTo(internalFrame8);
            form.emptTeks();
            form.setData("hasil expertise radiologi", Kd2.getText(), "", nmpasien.getText());
            form.jaminanTrans(kdItem, tglhasil, jamhasil, itemDipilih.getText(), sttsRawat, kodeRujukan, TDokter.getText(), dokterBaca, "", "", "");
            form.dataKirim();            
            form.setVisible(true);
            form.toFront();
            form.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKirimKeWhatsappActionPerformed

    private void MnDokterPemeriksaRad1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterPemeriksaRad1ActionPerformed
        kddokter.setText(Sequel.cariIsi("select kd_dokter from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'"));
        TDokter.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter.getText() + "'"));

        WindowGantiDokterRad.setSize(743, 82);
        WindowGantiDokterRad.setLocationRelativeTo(internalFrame1);
        WindowGantiDokterRad.setVisible(true);
        btnCariDokter.requestFocus();
    }//GEN-LAST:event_MnDokterPemeriksaRad1ActionPerformed

    private void MnDokterPengirim1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokterPengirim1ActionPerformed
        kddokter1.setText(Sequel.cariIsi("select dokter_perujuk from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'"));
        TDokter1.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter1.getText() + "'"));

        WindowGantiDokterPerujuk.setSize(743, 82);
        WindowGantiDokterPerujuk.setLocationRelativeTo(internalFrame1);
        WindowGantiDokterPerujuk.setVisible(true);
        btnCariDokter1.requestFocus();
    }//GEN-LAST:event_MnDokterPengirim1ActionPerformed

    private void MnWaktuPeriksa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnWaktuPeriksa1ActionPerformed
        if (lihatHasil.equals("")) {
            Valid.SetTgl(tanggalPeriksa, tglperiksa);
            cmbJam1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(3, 5));
            cmbDtk1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'").toString().substring(6, 8));
        } else if (lihatHasil.equals("OK")) {
            Valid.SetTgl(tanggalPeriksa, tglhasil);
            cmbJam1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'").toString().substring(0, 2));
            cmbMnt1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'").toString().substring(3, 5));
            cmbDtk1.setSelectedItem(Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'").toString().substring(6, 8));
        }

        WindowWaktuPeriksa.setSize(318, 136);
        WindowWaktuPeriksa.setLocationRelativeTo(internalFrame1);
        WindowWaktuPeriksa.setVisible(true);
        tanggalPeriksa.requestFocus();
    }//GEN-LAST:event_MnWaktuPeriksa1ActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        if (lihatHasil.equals("")) {
            WindowGantiDokterPerujuk.dispose();
            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();
        } else if (lihatHasil.equals("OK")) {
            WindowGantiDokterPerujuk.dispose();
        }
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnCloseIn2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn2KeyPressed

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        if (kddokter1.getText().trim().equals("") || TDokter1.getText().trim().equals("")) {
            Valid.textKosong(kddokter1, "Dokter Perujuk/Pengirim");
        } else {
            if (lihatHasil.equals("")) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                        "dokter_perujuk='" + kddokter1.getText() + "'");

                TNoRW.setText("");
                TnoRM.setText("");
                Tpasien.setText("");
                diagKlinis.setText("");
                khususIgd = "tidak";
                empttext();
                tampil();

            } else if (lihatHasil.equals("OK")) {
                Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "'",
                        "dokter_perujuk='" + kddokter1.getText() + "'");
                dokterPengirim.setText(TDokter1.getText());
            }

            WindowGantiDokterPerujuk.dispose();
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnSimpan3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan3KeyPressed

    private void kddokter1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokter1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kddokter1KeyPressed

    private void btnCariDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDokter1ActionPerformed
        initDokter();
        akses.setform("DlgCariPeriksaRadiologi");
        pilihanDokter = 2;
        dokter.isCek();
        dokter.setSize(1046, 341);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.TCari.setText("");
        dokter.tampil();
        dokter.setVisible(true);
    }//GEN-LAST:event_btnCariDokter1ActionPerformed

    private void MnAsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsalRujukanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            WindowGantiRujukan.setSize(743, 82);
            WindowGantiRujukan.setLocationRelativeTo(internalFrame1);
            WindowGantiRujukan.setVisible(true);
            btnCariFaskes.requestFocus();
        }
    }//GEN-LAST:event_MnAsalRujukanActionPerformed

    private void MnAsalRujukan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsalRujukan1ActionPerformed
        kodeRujukan = "";
        kodeRujukan = Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + Kd2.getText() + "'");

        if (kodeRujukan.equals("")) {
            nmFaskes.setText("");
        } else {
            nmFaskes.setText(Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
        }

        WindowGantiRujukan.setSize(743, 82);
        WindowGantiRujukan.setLocationRelativeTo(internalFrame1);
        WindowGantiRujukan.setVisible(true);
        btnCariFaskes.requestFocus();
    }//GEN-LAST:event_MnAsalRujukan1ActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        if (lihatHasil.equals("")) {
            WindowGantiRujukan.dispose();
            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();
        } else if (lihatHasil.equals("OK")) {
            WindowGantiRujukan.dispose();
        }
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        cekRujukan = 0;
        cekRujukan = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + Kd2.getText() + "'");

        if (lihatHasil.equals("")) {
            if (cekRujukan == 0) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where "
                        + "reg_periksa.tgl_registrasi='" + tgldaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                Sequel.menyimpan("rujuk_masuk", "'" + Kd2.getText() + "','" + nmFaskes.getText() + "','" + alamatFaskes + "',"
                        + "'-','0','" + nmFaskes.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kodeRujukan + "'", "No. Rujuk");
            } else {
                Sequel.mengedit("rujuk_masuk", "no_rawat='" + Kd2.getText() + "'", "perujuk='" + nmFaskes.getText() + "', alamat='" + alamatFaskes + "',"
                        + "dokter_perujuk='" + nmFaskes.getText() + "',kd_rujukan='" + kodeRujukan + "' ");
            }

            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();

        } else if (lihatHasil.equals("OK")) {
            if (cekRujukan == 0) {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where "
                        + "reg_periksa.tgl_registrasi='" + tgldaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                Sequel.menyimpan("rujuk_masuk", "'" + Kd2.getText() + "','" + nmFaskes.getText() + "','" + alamatFaskes + "',"
                        + "'-','0','" + nmFaskes.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kodeRujukan + "'", "No. Rujuk");
            } else {
                Sequel.mengedit("rujuk_masuk", "no_rawat='" + Kd2.getText() + "'", "perujuk='" + nmFaskes.getText() + "', alamat='" + alamatFaskes + "',"
                        + "dokter_perujuk='" + nmFaskes.getText() + "',kd_rujukan='" + kodeRujukan + "' ");
            }

            if (kodeRujukan.equals("784")) {
                asalRujukan.setText("-");
                nmFaskes.setText("-");
            } else {
                asalRujukan.setText(Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                nmFaskes.setText(Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
            }
        }

        WindowGantiRujukan.dispose();
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void btnCariFaskesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariFaskesActionPerformed
        dipilih = 0;
        dipilih = 1;
        initRujukMasuk();
        akses.setform("DlgCariPeriksaRadiologi");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnCariFaskesActionPerformed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getData1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData1();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemeriksaanKeyPressed

    private void itemDipilihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemDipilihKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemDipilihKeyPressed

    private void MnRekapTotalPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapTotalPemeriksaanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rpRekapTotalPeriksaDokterRad.jasper", "report", "::[ Laporan Rekap Total Pemeriksaan Dokter Radiologi ]::",
                "SELECT d.nm_dokter, pj.png_jawab, COUNT(case when pr.status='Ranap' then 1 end) as ranap, "
                + "COUNT(case when pr.status='Ralan' then 1 end) as ralan, COUNT(case when pr.status='Ranap' then 1 end) + COUNT(case when pr.status='Ralan' then 1 end) total "
                + "FROM periksa_radiologi pr INNER JOIN dokter d on d.kd_dokter=pr.kd_dokter "
                + "INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "GROUP BY pr.kd_dokter, rp.kd_pj ORDER BY d.nm_dokter, pj.png_jawab", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapTotalPemeriksaanActionPerformed

    private void MnRekapRincianPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRekapRincianPemeriksaanActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("periode", "Periode Tgl. " + Tgl1.getSelectedItem() + " s.d " + Tgl2.getSelectedItem());
        Valid.MyReport("rptRekapRincianPemeriksaanDokterRad.jasper", "report", "::[ Laporan Rekap Rincian Jumlah Pemeriksaan Dokter Radiologi ]::",
                "SELECT d.nm_dokter, if(pr.status='Ranap','R. Inap','R. Jalan') status, pj.png_jawab, p.no_rkm_medis, p.nm_pasien, pr.tgl_periksa, pr.jam, jpr.nm_perawatan FROM periksa_radiologi pr "
                + "INNER JOIN dokter d on d.kd_dokter=pr.kd_dokter INNER JOIN reg_periksa rp on rp.no_rawat=pr.no_rawat "
                + "INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                + "INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                + "ORDER BY pr.status, d.nm_dokter, pj.png_jawab, p.nm_pasien, pr.tgl_periksa, pr.jam", param);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnRekapRincianPemeriksaanActionPerformed

    private void MnPetugasRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPetugasRadiologiActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (Kd2.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Klik dulu salah satu nama pasiennya pada tabel...!!!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
        } else {
            kdpetugas.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 10).toString());
            TPetugas.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 2).toString());

            WindowGantiPetugasRadiologi.setSize(904, 84);
            WindowGantiPetugasRadiologi.setLocationRelativeTo(internalFrame1);
            WindowGantiPetugasRadiologi.setVisible(true);
            btnCariPetugas.requestFocus();
        }
    }//GEN-LAST:event_MnPetugasRadiologiActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        if (lihatHasil.equals("")) {
            WindowGantiPetugasRadiologi.dispose();
            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            khususIgd = "tidak";
            empttext();
            tampil();
        } else if (lihatHasil.equals("OK")) {
            WindowGantiPetugasRadiologi.dispose();
        }
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan5ActionPerformed
        if (kdpetugas.getText().trim().equals("") || kdpetugas.getText().trim().equals("-") || kdpetugas.getText().trim().equals("--")) {
            Valid.textKosong(kdpetugas, "Petugas");
        } else {
            Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                    "nip='" + kdpetugas.getText() + "'");

            TNoRW.setText("");
            TnoRM.setText("");
            Tpasien.setText("");
            diagKlinis.setText("");
            WindowGantiPetugasRadiologi.dispose();
            khususIgd = "tidak";
            empttext();
            tampil();
        }
    }//GEN-LAST:event_BtnSimpan5ActionPerformed

    private void btnCariPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariPetugasActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 1;
        initPetugas();
        akses.setform("DlgCariPeriksaRadiologi");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnCariPetugasActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "' and kd_sps ='S0004'") > 0 || akses.getadmin() == true) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin deskripsi/expertise/uraian hasil pemeriksaan akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                Sequel.queryu2("delete from hasil_radiologi where no_rawat=? and tgl_periksa=? and jam=? and kd_jenis_prw=?", 4, new String[]{
                    Kd2.getText(), tglhasil, jamhasil, kdItem});
                
                Sequel.queryu2("delete from pembaca_hasil_radiologi where no_rawat=? and tgl_periksa=? and jam_periksa=? and kd_jenis_prw=?", 4, new String[]{
                    Kd2.getText(), tglhasil, jamhasil, kdItem});
                HasilPeriksa.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Maaf, hanya dokter radiologi yang bisa menghapus data expertisenya..!!");
            khususIgd = "tidak";
            tampil();
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void MnPemeriksaanRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemeriksaanRadiologiActionPerformed
        if (tbPeriksaRadiologi.getSelectedRow() > -1) {
            if (Sequel.cariRegistrasi(Kd2.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus/diubah. Silahkan hubungi bagian kasir/keuangan ..!!");
                khususIgd = "tidak";
                tampil();
            } else {
                if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
                } else {
                    akses.setform("DlgCariPeriksaRadiologi");
                    DlgPeriksaRadiologi periksarad = new DlgPeriksaRadiologi(null, false);
                    periksarad.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    periksarad.setLocationRelativeTo(internalFrame1);
                    periksarad.emptTeks();
                    periksarad.setNoRm(Kd2.getText(), sttsRawat, "perbaikan", tglperiksa, jam);
                    periksarad.tampil();
                    periksarad.isCek();
                    periksarad.setVisible(true);
                    periksarad.fokus_kursor();
                    khususIgd = "tidak";
                    tampil();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada no. rawat pasien ditabel..!!");
            khususIgd = "tidak";
            tampil();
            tbPeriksaRadiologi.requestFocus();
        }
    }//GEN-LAST:event_MnPemeriksaanRadiologiActionPerformed

    private void tbDataPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDataPeriksaMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                TNoRw.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 5).toString());
                TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
                TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                TnmPemeriksaan.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 1).toString());
                TtglPemeriksaan.setText(Valid.SetTglINDONESIA(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 7).toString()));
                TjamPemeriksaan.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 3).toString());
                
                TpemeriksaanExp.setText("");
                TtglExp.setText("");
                TjamExp.setText("");
                HasilPeriksa1.setText("");
                tampilRiwExpertise();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDataPeriksaMouseClicked

    private void tbDataPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDataPeriksaKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    TNoRw.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 5).toString());
                    TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
                    TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                    TnmPemeriksaan.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 1).toString());
                    TtglPemeriksaan.setText(Valid.SetTglINDONESIA(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 7).toString()));
                    TjamPemeriksaan.setText(tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 3).toString());
                    
                    TpemeriksaanExp.setText("");
                    TtglExp.setText("");
                    TjamExp.setText("");
                    HasilPeriksa1.setText("");
                    tampilRiwExpertise();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDataPeriksaKeyPressed

    private void tbExpertiseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbExpertiseMouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                TpemeriksaanExp.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 3).toString());
                TtglExp.setText(Valid.SetTglINDONESIA(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 5).toString()));
                TjamExp.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 2).toString());
                HasilPeriksa1.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 6).toString());
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbExpertiseMouseClicked

    private void tbExpertiseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbExpertiseKeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    TpemeriksaanExp.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 3).toString());
                    TtglExp.setText(Valid.SetTglINDONESIA(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 5).toString()));
                    TjamExp.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 2).toString());
                    HasilPeriksa1.setText(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 6).toString());
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbExpertiseKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeksRiwayat();
        tampilDataPeriksa();
        Valid.tabelKosong(tabMode3);
        HasilPeriksa1.setText("");
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnDisamakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDisamakanActionPerformed
        if (tbDataPeriksa.getSelectedRow() > -1 && tbExpertise.getSelectedRow() > -1) {
            if (tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 6).toString().equals(tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 7).toString())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin hasil expertise radiologi akan disamakan tanggal & jamnya dg. nama pemeriksaanya..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    if (Sequel.mengedittf("hasil_radiologi", "waktu_simpan=?", "tgl_periksa=?, jam=?", 3, new String[]{
                        tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 7).toString(),
                        tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 3).toString(),
                        tbExpertise.getValueAt(tbExpertise.getSelectedRow(), 8).toString()
                    }) == true) {
                        BtnBatalActionPerformed(null);
                    }
                } else {
                    BtnBatalActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Pemeriksaan radiologi yang dipilih harus sama dengan hasil expertisenya...!!!");
                tbDataPeriksa.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel pemeriksaan radiologi &    \n"
                    + "pilih juga salah satu pada tabel data riwayat expertise..!!");
            tbDataPeriksa.requestFocus();
        }
    }//GEN-LAST:event_BtnDisamakanActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowRiwayatExpertise.dispose();
        khususIgd = "tidak";
        tampil();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilDataPeriksa();
        Valid.tabelKosong(tabMode3);
        HasilPeriksa1.setText("");        
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void MnRiwayatExpertiseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatExpertiseActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {                
                WindowRiwayatExpertise.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                WindowRiwayatExpertise.setLocationRelativeTo(internalFrame1);
                WindowRiwayatExpertise.setVisible(true);
                BtnBatalActionPerformed(null);
            }
        }
    }//GEN-LAST:event_MnRiwayatExpertiseActionPerformed

    private void MnTglRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTglRegActionPerformed
        if (Sequel.cariInteger("SELECT count(-1) FROM reg_periksa rp INNER JOIN periksa_radiologi pr ON pr.no_rawat = rp.no_rawat "
                + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data tidak ditemukan, atur lagi tgl. periode registrasinya...!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT "
                    + "    @rownum := @rownum + 1 AS `No.`, "
                    + "    x.`No. RM`, "
                    + "    x.`Nama Pasien`, "
                    + "    x.`Jns. Kelamin`, "
                    + "    x.`Umur`, "
                    + "    x.`Berat Badan`, "
                    + "    x.`Tgl. Registrasi`, "
                    + "    x.`R. Jalan`, "
                    + "    x.`R. Inap`, "
                    + "    x.`Nama Pemeriksaan Rad.`, "
                    + "    x.`Tgl. Pemeriksaan`, "
                    + "    x.`Jam Input`, "
                    + "    x.`Jam Pmrksn. Selesai`, "
                    + "    x.`Cara Bayar`, "
                    + "    x.`Rg. Rawat/Poli/Inst.`, "
                    + "    x.`Parameter kV`, "
                    + "    x.`Parameter mAs`, "
                    + "    x.`Parameter mA/CTDi`, "
                    + "    x.`Parameter s/Fase`, "
                    + "    x.`Parameter DLP`, "
                    + "    x.`Jam Selesai Hasil Bacaan`, "
                    + "    x.`Durasi Pelayanan`, "
                    + "    x.`Unit Pengirim`, "
                    + "    x.`Kategori Pelayanan` "
                    + "FROM (SELECT * FROM (SELECT p.no_rkm_medis AS `No. RM`, p.nm_pasien AS `Nama Pasien`, IF(p.jk = 'L','Laki-laki','Perempuan') AS `Jns. Kelamin`, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') AS `Umur`, IFNULL(CASE WHEN pr.berat_badan = '' THEN '-' WHEN LOWER(pr.berat_badan) LIKE '%kg%' "
                    + "OR LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan WHEN LENGTH(pr.berat_badan) >= 4 THEN CONCAT(pr.berat_badan, ' gram') "
                    + "ELSE CONCAT(pr.berat_badan, ' kg') END,'-') AS `Berat Badan`, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') AS `Tgl. Registrasi`, "
                    + "IF(rp.status_lanjut = 'Ralan','V','-') AS `R. Jalan`, IF(rp.status_lanjut = 'Ranap','V','-') AS `R. Inap`, "
                    + "jpr.nm_perawatan AS `Nama Pemeriksaan Rad.`, DATE_FORMAT(pr.tgl_periksa,'%d-%m-%Y') AS `Tgl. Pemeriksaan`, time_format(pr.jam,'%H:%i:%s') AS `Jam Input`, "
                    + "pj.png_jawab AS `Cara Bayar`, COALESCE((SELECT b2.nm_bangsal FROM kamar_inap ki2 INNER JOIN kamar k2 ON k2.kd_kamar = ki2.kd_kamar "
                    + "INNER JOIN bangsal b2 ON b2.kd_bangsal = k2.kd_bangsal WHERE ki2.no_rawat = rp.no_rawat AND "
                    + "TIMESTAMP(pr.tgl_periksa,pr.jam) >= TIMESTAMP(ki2.tgl_masuk,ki2.jam_masuk) AND (ki2.stts_pulang = '-' OR "
                    + "TIMESTAMP(pr.tgl_periksa,pr.jam) <= TIMESTAMP(ki2.tgl_keluar,ki2.jam_keluar)) "
                    + "ORDER BY ki2.tgl_masuk DESC, ki2.jam_masuk DESC, ki2.tgl_keluar DESC, ki2.jam_keluar DESC LIMIT 1), pl.nm_poli, '-') AS `Rg. Rawat/Poli/Inst.`, "
                    + "pr.nilai_kv AS `Parameter kV`, pr.nilai_mas AS `Parameter mAs`, pr.nilai_ma AS `Parameter mA/CTDi`, pr.nilai_fase AS `Parameter s/Fase`, "
                    + "pr.nilai_dlp AS `Parameter DLP`, pr.tgl_periksa AS tgl_periksa_sort, pr.jam AS jam_sort, p.no_rkm_medis AS no_rkm_medis_sort, "
                    + "if(pr.jam_pemeriksaan='00:00:00','',pr.jam_pemeriksaan) AS `Jam Pmrksn. Selesai`, ifnull(time_format(hr1.waktu_simpan,'%H:%i:%s'),'-') 'Jam Selesai Hasil Bacaan', "
                    + "IFNULL(CASE WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 0 THEN '-' "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 3600 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 60),' Menit') "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 86400 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') ELSE "
                    + "CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) / 86400),' Hari ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 86400) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') END,'-') 'Durasi Pelayanan', pr.unit_pengirim 'Unit Pengirim', "
                    + "CASE "
                    + "    WHEN rp.kd_poli IN ('PON','IGDK') AND NOT EXISTS (SELECT 1 FROM kamar_inap ki0 WHERE ki0.no_rawat = pr.no_rawat AND "
                    + "    TIMESTAMP(ki0.tgl_masuk, ki0.jam_masuk) <= TIMESTAMP(pr.tgl_periksa, pr.jam)) THEN 'Kuning' "
                    + "    WHEN EXISTS (SELECT 1 FROM kamar_inap kiw INNER JOIN kamar kw ON kw.kd_kamar = kiw.kd_kamar INNER JOIN bangsal bw ON bw.kd_bangsal = kw.kd_bangsal WHERE "
                    + "    kiw.no_rawat = pr.no_rawat AND bw.nm_gedung IN ('ICU','PERINATOLOGI') "
                    + "    AND TIMESTAMP(pr.tgl_periksa, pr.jam) >= TIMESTAMP(kiw.tgl_masuk, kiw.jam_masuk) "
                    + "    AND (kiw.stts_pulang = '-' OR TIMESTAMP(pr.tgl_periksa, pr.jam) <= TIMESTAMP(kiw.tgl_keluar, kiw.jam_keluar))) THEN 'Kuning' "
                    + "    ELSE 'Hijau' END AS 'Kategori Pelayanan' "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj INNER JOIN periksa_radiologi pr ON pr.no_rawat = rp.no_rawat  "
                    + "INNER JOIN jns_perawatan_radiologi jpr ON jpr.kd_jenis_prw = pr.kd_jenis_prw INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                    + "left join hasil_radiologi hr1 on hr1.no_rawat=pr.no_rawat and hr1.tgl_periksa=pr.tgl_periksa and hr1.jam=pr.jam and hr1.kd_jenis_prw=pr.kd_jenis_prw "
                    + "left join hasil_radiologi hr2 on hr2.no_rawat=pr.no_rawat and hr2.tgl_periksa=pr.tgl_periksa and hr2.jam=pr.jam and hr2.kd_jenis_prw=pr.kd_jenis_prw "
                    + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') sub "
                    + "ORDER BY sub.tgl_periksa_sort, sub.jam_sort, sub.no_rkm_medis_sort) x CROSS JOIN (SELECT @rownum := 0) r", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data pemeriksaan radiologi sesuai dg. tgl. registrasinya berhasil diexport menjadi file excel,..!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnTglRegActionPerformed

    private void MnTglPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTglPemeriksaanActionPerformed
        if (Sequel.cariInteger("SELECT count(-1) FROM reg_periksa rp INNER JOIN periksa_radiologi pr ON pr.no_rawat = rp.no_rawat "
                + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data tidak ditemukan, atur lagi tgl. periode pemeriksaan radiologinya...");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT "
                    + "    @rownum := @rownum + 1 AS `No.`, "
                    + "    x.`No. RM`, "
                    + "    x.`Nama Pasien`, "
                    + "    x.`Jns. Kelamin`, "
                    + "    x.`Umur`, "
                    + "    x.`Berat Badan`, "
                    + "    x.`Tgl. Registrasi`, "
                    + "    x.`R. Jalan`, "
                    + "    x.`R. Inap`, "
                    + "    x.`Nama Pemeriksaan Rad.`, "
                    + "    x.`Tgl. Pemeriksaan`, "
                    + "    x.`Jam Input`, "
                    + "    x.`Jam Pmrksn. Selesai`, "
                    + "    x.`Cara Bayar`, "
                    + "    x.`Rg. Rawat/Poli/Inst.`, "
                    + "    x.`Parameter kV`, "
                    + "    x.`Parameter mAs`, "
                    + "    x.`Parameter mA/CTDi`, "
                    + "    x.`Parameter s/Fase`, "
                    + "    x.`Parameter DLP`, "
                    + "    x.`Jam Selesai Hasil Bacaan`, "
                    + "    x.`Durasi Pelayanan`, "
                    + "    x.`Unit Pengirim`, "
                    + "    x.`Kategori Pelayanan` "
                    + "FROM (SELECT * FROM (SELECT p.no_rkm_medis AS `No. RM`, p.nm_pasien AS `Nama Pasien`, IF(p.jk = 'L','Laki-laki','Perempuan') AS `Jns. Kelamin`, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') AS `Umur`, IFNULL(CASE WHEN pr.berat_badan = '' THEN '-' WHEN LOWER(pr.berat_badan) LIKE '%kg%' "
                    + "OR LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan WHEN LENGTH(pr.berat_badan) >= 4 THEN CONCAT(pr.berat_badan, ' gram') "
                    + "ELSE CONCAT(pr.berat_badan, ' kg') END,'-') AS `Berat Badan`, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') AS `Tgl. Registrasi`, "
                    + "IF(rp.status_lanjut = 'Ralan','V','-') AS `R. Jalan`, IF(rp.status_lanjut = 'Ranap','V','-') AS `R. Inap`, "
                    + "jpr.nm_perawatan AS `Nama Pemeriksaan Rad.`, DATE_FORMAT(pr.tgl_periksa,'%d-%m-%Y') AS `Tgl. Pemeriksaan`, time_format(pr.jam,'%H:%i:%s') AS `Jam Input`, "
                    + "pj.png_jawab AS `Cara Bayar`, COALESCE((SELECT b2.nm_bangsal FROM kamar_inap ki2 INNER JOIN kamar k2 ON k2.kd_kamar = ki2.kd_kamar "
                    + "INNER JOIN bangsal b2 ON b2.kd_bangsal = k2.kd_bangsal WHERE ki2.no_rawat = rp.no_rawat AND "
                    + "TIMESTAMP(pr.tgl_periksa,pr.jam) >= TIMESTAMP(ki2.tgl_masuk,ki2.jam_masuk) AND (ki2.stts_pulang = '-' OR "
                    + "TIMESTAMP(pr.tgl_periksa,pr.jam) <= TIMESTAMP(ki2.tgl_keluar,ki2.jam_keluar)) "
                    + "ORDER BY ki2.tgl_masuk DESC, ki2.jam_masuk DESC, ki2.tgl_keluar DESC, ki2.jam_keluar DESC LIMIT 1), pl.nm_poli, '-') AS `Rg. Rawat/Poli/Inst.`, "
                    + "pr.nilai_kv AS `Parameter kV`, pr.nilai_mas AS `Parameter mAs`, pr.nilai_ma AS `Parameter mA/CTDi`, pr.nilai_fase AS `Parameter s/Fase`, "
                    + "pr.nilai_dlp AS `Parameter DLP`, pr.tgl_periksa AS tgl_periksa_sort, pr.jam AS jam_sort, p.no_rkm_medis AS no_rkm_medis_sort, "
                    + "if(pr.jam_pemeriksaan='00:00:00','',pr.jam_pemeriksaan) AS `Jam Pmrksn. Selesai`, ifnull(time_format(hr1.waktu_simpan,'%H:%i:%s'),'-') 'Jam Selesai Hasil Bacaan', "
                    + "IFNULL(CASE WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 0 THEN '-' "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 3600 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 60),' Menit') "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 86400 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') ELSE "
                    + "CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) / 86400),' Hari ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 86400) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') END,'-') 'Durasi Pelayanan', pr.unit_pengirim 'Unit Pengirim', "
                    + "CASE "
                    + "    WHEN rp.kd_poli IN ('PON','IGDK') AND NOT EXISTS (SELECT 1 FROM kamar_inap ki0 WHERE ki0.no_rawat = pr.no_rawat AND "
                    + "    TIMESTAMP(ki0.tgl_masuk, ki0.jam_masuk) <= TIMESTAMP(pr.tgl_periksa, pr.jam)) THEN 'Kuning' "
                    + "    WHEN EXISTS (SELECT 1 FROM kamar_inap kiw INNER JOIN kamar kw ON kw.kd_kamar = kiw.kd_kamar INNER JOIN bangsal bw ON bw.kd_bangsal = kw.kd_bangsal WHERE "
                    + "    kiw.no_rawat = pr.no_rawat AND bw.nm_gedung IN ('ICU','PERINATOLOGI') "
                    + "    AND TIMESTAMP(pr.tgl_periksa, pr.jam) >= TIMESTAMP(kiw.tgl_masuk, kiw.jam_masuk) "
                    + "    AND (kiw.stts_pulang = '-' OR TIMESTAMP(pr.tgl_periksa, pr.jam) <= TIMESTAMP(kiw.tgl_keluar, kiw.jam_keluar))) THEN 'Kuning' "
                    + "    ELSE 'Hijau' END AS 'Kategori Pelayanan' "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj INNER JOIN periksa_radiologi pr ON pr.no_rawat = rp.no_rawat  "
                    + "INNER JOIN jns_perawatan_radiologi jpr ON jpr.kd_jenis_prw = pr.kd_jenis_prw INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli "
                    + "left join hasil_radiologi hr1 on hr1.no_rawat=pr.no_rawat and hr1.tgl_periksa=pr.tgl_periksa and hr1.jam=pr.jam and hr1.kd_jenis_prw=pr.kd_jenis_prw "
                    + "left join hasil_radiologi hr2 on hr2.no_rawat=pr.no_rawat and hr2.tgl_periksa=pr.tgl_periksa and hr2.jam=pr.jam and hr2.kd_jenis_prw=pr.kd_jenis_prw "
                    + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "') sub "
                    + "ORDER BY sub.tgl_periksa_sort, sub.jam_sort, sub.no_rkm_medis_sort) x CROSS JOIN (SELECT @rownum := 0) r", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data pemeriksaan radiologi sesuai dg. tgl. pemeriksaannya berhasil diexport menjadi file excel,..!!!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnTglPemeriksaanActionPerformed

    private void BtnCariIgdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariIgdActionPerformed
        TNoRW.setText("");
        TnoRM.setText("");
        Tpasien.setText("");
        diagKlinis.setText("");
        khususIgd = "ya";
        tampil();
    }//GEN-LAST:event_BtnCariIgdActionPerformed

    private void BtnCariIgdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariIgdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariIgdActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCariIgdKeyPressed

    private void MnTglRegKhususIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTglRegKhususIGDActionPerformed
        if (Sequel.cariInteger("SELECT count(-1) FROM reg_periksa rp INNER JOIN periksa_radiologi pr ON pr.no_rawat = rp.no_rawat "
                + "WHERE rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data tidak ditemukan, atur lagi tgl. periode registrasinya...!!!!");            
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT @rownum := @rownum + 1 AS `No.`, "
                    + "    x.`No. RM`, "
                    + "    x.`Nama Pasien`, "
                    + "    x.`Jns. Kelamin`, "
                    + "    x.`Umur`, "
                    + "    x.`Berat Badan`, "
                    + "    x.`Tgl. Registrasi`, "
                    + "    x.`R. Jalan`, "
                    + "    x.`R. Inap`, "
                    + "    x.`Nama Pemeriksaan Rad.`, "
                    + "    x.`Tgl. Pemeriksaan`, "
                    + "    x.`Jam Input`, "
                    + "    x.`Jam Pmrksn. Selesai`, "
                    + "    x.`Cara Bayar`, "
                    + "    x.`Rg. Rawat/Poli/Inst.`, "
                    + "    x.`Parameter kV`, "
                    + "    x.`Parameter mAs`, "
                    + "    x.`Parameter mA/CTDi`, "
                    + "    x.`Parameter s/Fase`, "
                    + "    x.`Parameter DLP`, "
                    + "    x.`Jam Selesai Hasil Bacaan`, "
                    + "    x.`Durasi Pelayanan`, "
                    + "    x.`Unit Pengirim`, "
                    + "    x.`Kategori Pelayanan` "
                    + "FROM (SELECT rp.no_rkm_medis AS `No. RM`, p.nm_pasien AS `Nama Pasien`, IF(p.jk='L','Laki-laki','Perempuan') AS `Jns. Kelamin`, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') AS `Umur`, IFNULL(CASE WHEN pr.berat_badan='' THEN '-' WHEN LOWER(pr.berat_badan) LIKE '%kg%' OR "
                    + "LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan WHEN LENGTH(pr.berat_badan)>=4 THEN CONCAT(pr.berat_badan,' gram') "
                    + "ELSE CONCAT(pr.berat_badan,' kg') END,'-') AS `Berat Badan`, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') AS `Tgl. Registrasi`, "
                    + "IF(rp.status_lanjut='Ralan','V','-') AS `R. Jalan`, IF(rp.status_lanjut='Ranap','V','-') AS `R. Inap`, jpr.nm_perawatan AS `Nama Pemeriksaan Rad.`, "
                    + "DATE_FORMAT(pr.tgl_periksa,'%d-%m-%Y') AS `Tgl. Pemeriksaan`, TIME_FORMAT(pr.jam,'%H:%i:%s') AS `Jam Input`, pj.png_jawab AS `Cara Bayar`, "
                    + "pl.nm_poli AS `Rg. Rawat/Poli/Inst.`, pr.nilai_kv AS `Parameter kV`, pr.nilai_mas AS `Parameter mAs`, pr.nilai_ma AS `Parameter mA/CTDi`, "
                    + "pr.nilai_fase AS `Parameter s/Fase`, pr.nilai_dlp AS `Parameter DLP`, pr.tgl_periksa AS tgl_periksa_sort, pr.jam AS jam_sort, "
                    + "if(pr.jam_pemeriksaan='00:00:00','',pr.jam_pemeriksaan) AS `Jam Pmrksn. Selesai`, ifnull(time_format(hr1.waktu_simpan,'%H:%i:%s'),'-') 'Jam Selesai Hasil Bacaan', "
                    + "IFNULL(CASE WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 0 THEN '-' "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 3600 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 60),' Menit') "
                    + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) < 86400 "
                    + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr2.waktu_simpan) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') ELSE "
                    + "CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) / 86400),' Hari ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 86400) / 3600),' Jam ', "
                    + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr2.waktu_simpan) % 3600) / 60),' Menit') END,'-') 'Durasi Pelayanan', pr.unit_pengirim 'Unit Pengirim', "
                    + "'Kuning' AS 'Kategori Pelayanan' FROM periksa_radiologi pr "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=pr.no_rawat INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis INNER JOIN petugas pt ON pt.nip=pr.nip "
                    + "INNER JOIN dokter d ON d.kd_dokter=pr.kd_dokter INNER JOIN penjab pj ON pj.kd_pj=rp.kd_pj "
                    + "INNER JOIN jns_perawatan_radiologi jpr ON jpr.kd_jenis_prw=pr.kd_jenis_prw INNER JOIN poliklinik pl ON pl.kd_poli=rp.kd_poli "
                    + "LEFT JOIN billing bl ON bl.no_rawat=pr.no_rawat LEFT JOIN piutang_pasien pp ON pp.no_rawat=pr.no_rawat "
                    + "left join hasil_radiologi hr1 on hr1.no_rawat=pr.no_rawat and hr1.tgl_periksa=pr.tgl_periksa and hr1.jam=pr.jam and hr1.kd_jenis_prw=pr.kd_jenis_prw "
                    + "left join hasil_radiologi hr2 on hr2.no_rawat=pr.no_rawat and hr2.tgl_periksa=pr.tgl_periksa and hr2.jam=pr.jam and hr2.kd_jenis_prw=pr.kd_jenis_prw "
                    + "WHERE pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND rp.kd_poli='IGDK' "
                    + "GROUP BY CONCAT(pr.no_rawat, pr.tgl_periksa, pr.jam) ORDER BY pr.tgl_periksa DESC, pr.jam DESC) x "
                    + "CROSS JOIN (SELECT @rownum := 0) r ORDER BY x.tgl_periksa_sort DESC, x.jam_sort DESC", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data pemeriksaan radiologi sesuai tgl. registrasi khusus pasien IGD          \n"
                        + "berhasil diexport menjadi file excel,..!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnTglRegKhususIGDActionPerformed

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        WindowBeratBadan.dispose();
        tampil();
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnSimpan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan6ActionPerformed
        Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "'", "berat_badan='" + Tbb.getText() + "'");
        WindowBeratBadan.dispose();
        tampil();
    }//GEN-LAST:event_BtnSimpan6ActionPerformed

    private void MnBeratBadanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBeratBadanActionPerformed
        if (TNoRW.getText().equals("") || TNoRW.getText().equals("Cara Bayar") || TNoRW.getText().equals("Berat Badan")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel..!!");
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else {
            WindowBeratBadan.setSize(469, 88);
            WindowBeratBadan.setLocationRelativeTo(internalFrame1);
            WindowBeratBadan.setVisible(true);
            Tbb.setText(Sequel.cariIsi("select berat_badan from periksa_radiologi where no_rawat='" + Kd2.getText() + "'"));
            Tbb.requestFocus();
        }
    }//GEN-LAST:event_MnBeratBadanActionPerformed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan6ActionPerformed(null);
        }
    }//GEN-LAST:event_TbbKeyPressed

    private void MnTTDnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDnotaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    ps4.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                    ps4.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                    ps4.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        Sequel.queryu("delete from temporary");
                        koneksi.setAutoCommit(false);
                        ps2.setString(1, rs.getString("no_rawat"));
                        ps2.setString(2, rs.getString("tgl_periksa"));
                        ps2.setString(3, rs.getString("jam"));
                        rs2 = ps2.executeQuery();
                        ttl = 0;
                        while (rs2.next()) {
                            item = rs2.getDouble("biaya") + Sequel.cariIsiAngka("select sum(biaya_item) from template_laboratorium where kd_jenis_prw=?", rs2.getString("kd_jenis_prw"));
                            ttl = ttl + item;
                            Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','" + item + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        }
                        Sequel.menyimpan("temporary", "'0','Total Biaya Pemeriksaan Radiologi','" + ttl + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        tte = "";
                        tte = "tidak";
                        cetakNota();
                        koneksi.setAutoCommit(true);

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTTDnotaActionPerformed

    private void MnTTEnotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEnotaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    ps4.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                    ps4.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                    ps4.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        Sequel.queryu("delete from temporary");
                        koneksi.setAutoCommit(false);
                        ps2.setString(1, rs.getString("no_rawat"));
                        ps2.setString(2, rs.getString("tgl_periksa"));
                        ps2.setString(3, rs.getString("jam"));
                        rs2 = ps2.executeQuery();
                        ttl = 0;
                        while (rs2.next()) {
                            item = rs2.getDouble("biaya") + Sequel.cariIsiAngka("select sum(biaya_item) from template_laboratorium where kd_jenis_prw=?", rs2.getString("kd_jenis_prw"));
                            ttl = ttl + item;
                            Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','" + item + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        }
                        Sequel.menyimpan("temporary", "'0','Total Biaya Pemeriksaan Radiologi','" + ttl + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        tte = "";
                        tte = "ya";
                        cetakNota();
                        koneksi.setAutoCommit(true);

                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTTEnotaActionPerformed

    private void MnTTDregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTDregActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    ps4.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                    ps4.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                    ps4.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        Sequel.queryu("delete from temporary");
                        koneksi.setAutoCommit(false);
                        ps2.setString(1, rs.getString("no_rawat"));
                        ps2.setString(2, rs.getString("tgl_periksa"));
                        ps2.setString(3, rs.getString("jam"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        }
                        
                        tte = "";
                        tte = "tidak";
                        cetakBuktiReg();
                        koneksi.setAutoCommit(true);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTTDregActionPerformed

    private void MnTTEregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTTEregActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                try {
                    ps4.setString(1, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString());
                    ps4.setString(2, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString());
                    ps4.setString(3, tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
                    rs = ps4.executeQuery();
                    while (rs.next()) {
                        Sequel.queryu("delete from temporary");
                        koneksi.setAutoCommit(false);
                        ps2.setString(1, rs.getString("no_rawat"));
                        ps2.setString(2, rs.getString("tgl_periksa"));
                        ps2.setString(3, rs.getString("jam"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            Sequel.menyimpan("temporary", "'0','" + rs2.getString("nm_perawatan") + "','','','','','','','',"
                            + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Transaksi Biaya Rad");
                        }
                        
                        tte = "";
                        tte = "ya";
                        cetakBuktiReg();
                        koneksi.setAutoCommit(true);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnTTEregActionPerformed

    private void MnKirimKeWhatsappNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKirimKeWhatsappNotaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        } else if (tbPeriksaRadiologi.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data..!!");
            khususIgd = "tidak";
            tampil();
        } else {
            if (Kd2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data yang mau ditampilkan...!!!!");
                khususIgd = "tidak";
                tampil();
            } else if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, silahkan klik tepat pada nama pasiennya...!!!!");
            } else {
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                akses.setform("DlgCariPeriksaRadiologi");
                DlgKirimWhatsapp form = new DlgKirimWhatsapp(null, false);
                form.setSize(557, 148);
                form.setLocationRelativeTo(internalFrame1);
                form.emptTeks();
                form.setData("nota radiologi", tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString(),
                        tglNota.getSelectedItem().toString(), Sequel.cariIsi("select p.nm_pasien from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                        + "where rp.no_rawat='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString() + "'"));
                form.jaminanTrans(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString(),
                        tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString(), TnoRM.getText(), 
                        Tgl1.getSelectedItem() + "", Tgl2.getSelectedItem() + "", CariData, cariBayar, cariDataIgd, khususIgd, 
                        Tpasien.getText(), dokterRad.getText());
                form.dataKirim();
                form.setVisible(true);
                form.toFront();
                form.requestFocus();
            }
        }
    }//GEN-LAST:event_MnKirimKeWhatsappNotaActionPerformed

    private void BtnCloseIn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn8ActionPerformed
        WindowParameter.dispose();
        tampil();
    }//GEN-LAST:event_BtnCloseIn8ActionPerformed

    private void BtnSimpan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan7ActionPerformed
        Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                "nilai_kv='" + TnilaiKv.getText() + "', nilai_mas='" + TnilaiMas.getText() + "', nilai_ma='" + TnilaiMa.getText() + "', "
                + "nilai_fase='" + TnilaiFase.getText() + "', nilai_dlp='" + TnilaiDlp.getText() + "'");
        WindowParameter.dispose();
        tampil();
    }//GEN-LAST:event_BtnSimpan7ActionPerformed

    private void MnParamaterPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnParamaterPemeriksaanActionPerformed
        if (TNoRW.getText().equals("") || TNoRW.getText().equals("Cara Bayar") || TNoRW.getText().equals("Berat Badan")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel..!!");
            tampil();
            tbPeriksaRadiologi.requestFocus();
        } else {
            WindowParameter.setSize(362, 231);
            WindowParameter.setLocationRelativeTo(internalFrame1);
            WindowParameter.setVisible(true);            
            TnilaiKv.requestFocus();
        }
    }//GEN-LAST:event_MnParamaterPemeriksaanActionPerformed

    private void TnilaiKvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnilaiKvKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnilaiMas.requestFocus();
        }
    }//GEN-LAST:event_TnilaiKvKeyPressed

    private void TnilaiMasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnilaiMasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnilaiMa.requestFocus();
        }
    }//GEN-LAST:event_TnilaiMasKeyPressed

    private void TnilaiMaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnilaiMaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnilaiFase.requestFocus();
        }
    }//GEN-LAST:event_TnilaiMaKeyPressed

    private void TnilaiFaseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnilaiFaseKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnilaiDlp.requestFocus();
        }
    }//GEN-LAST:event_TnilaiFaseKeyPressed

    private void TnilaiDlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnilaiDlpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan7.requestFocus();
        }
    }//GEN-LAST:event_TnilaiDlpKeyPressed

    private void tbKritisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKritisMouseClicked
        if(tabMode5.getRowCount()!=0){
            try {
                getDataNilaiKritis();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKritisMouseClicked

    private void tbKritisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKritisKeyPressed
        if(tabMode5.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataNilaiKritis();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKritisKeyPressed

    private void BtnSimpan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan8ActionPerformed
        if (TNoRw1.getText().equals("")) {
            Valid.textKosong(TNoRw1, "Pasien");
        } else {
            cekData();
            if (tbItem.getSelectedRow() != -1) {
                if (Sequel.menyimpantf("nilai_kritis_radiologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 14, new String[]{
                    TNoRw1.getText(), tbItem.getValueAt(tbItem.getSelectedRow(), 5).toString(), tbItem.getValueAt(tbItem.getSelectedRow(), 4).toString(),
                    tbItem.getValueAt(tbItem.getSelectedRow(), 2).toString(), nipPtgs,
                    cekPemeriksaan, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                    cekLapor, cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                    cekLapDit, cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                    nipPenerimaLap, Tketerangan.getText(), Sequel.cariIsi("select now()")
                }) == true) {
                    
                    Sequel.mengedit("periksa_radiologi", "no_rawat='" + TNoRw1.getText() + "' "
                            + "and tgl_periksa='" + tbItem.getValueAt(tbItem.getSelectedRow(), 4).toString() + "' "
                            + "and jam='" + tbItem.getValueAt(tbItem.getSelectedRow(), 2).toString() + "' "
                            + "and kd_jenis_prw='" + tbItem.getValueAt(tbItem.getSelectedRow(), 5).toString() + "'",
                            "jam_pemeriksaan='" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "'");
                    
                    TCari1.setText(TNoRw1.getText());
                    tampilNilaiKritis();
                    emptTeksNilaiKritis();
                    tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik salah satu pada tabel nama item pemeriksaan radiologi yang dilakukan...");
                tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
                tbItem.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnSimpan8ActionPerformed

    private void BtnSimpan8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan8KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpan8ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan8KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        emptTeksNilaiKritis();
        tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
        tampilNilaiKritis();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeksNilaiKritis();
        } else {
            Valid.pindah(evt, BtnSimpan8, BtnGanti);
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnHapus2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus2ActionPerformed
        if (tbKritis.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from nilai_kritis_radiologi where waktu_simpan=?", 1, new String[]{
                    tbKritis.getValueAt(tbKritis.getSelectedRow(), 24).toString()
                }) == true) {
                    tampilNilaiKritis();
                    emptTeksNilaiKritis();
                    tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilNilaiKritis();
                emptTeksNilaiKritis();
                tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampilNilaiKritis();
            tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
        }
    }//GEN-LAST:event_BtnHapus2ActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbKritis.getSelectedRow() > -1) {
            cekData();
            if (tbItem.getSelectedRow() != -1) {
                if (Sequel.mengedittf("nilai_kritis_radiologi", "waktu_simpan=?", "kd_jenis_prw=?, tgl_periksa=?, jam=?, nip_petugas=?, cek_pemeriksaan=?, jam_pemeriksaan=?, "
                        + "cek_lapor=?, jam_lapor=?, cek_lap_diterima=?, jam_lap_diterima=?, nip_penerima_lap=?, keterangan=?", 13, new String[]{
                            tbItem.getValueAt(tbItem.getSelectedRow(), 5).toString(), tbItem.getValueAt(tbItem.getSelectedRow(), 4).toString(),
                            tbItem.getValueAt(tbItem.getSelectedRow(), 2).toString(), nipPtgs,
                            cekPemeriksaan, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                            cekLapor, cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                            cekLapDit, cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                            nipPenerimaLap, Tketerangan.getText(),
                            tbKritis.getValueAt(tbKritis.getSelectedRow(), 24).toString()
                        }) == true) {

                    Sequel.mengedit("periksa_radiologi", "no_rawat='" + TNoRw1.getText() + "' "
                            + "and tgl_periksa='" + tbItem.getValueAt(tbItem.getSelectedRow(), 4).toString() + "' "
                            + "and jam='" + tbItem.getValueAt(tbItem.getSelectedRow(), 2).toString() + "' "
                            + "and kd_jenis_prw='" + tbItem.getValueAt(tbItem.getSelectedRow(), 5).toString() + "'",
                            "jam_pemeriksaan='" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "'");

                    TCari1.setText(TNoRw1.getText());
                    tampilNilaiKritis();
                    emptTeksNilaiKritis();
                    tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih/klik salah satu pada tabel nama item pemeriksaan radiologi yang dilakukan...");
                tampilItemPemeriksaan(TNoRw1.getText(), tglperiksa, jam);
                tbItem.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
            tampilNilaiKritis();
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal1, BtnKeluar2);
        }
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari1.setText("");
        BtnCari2ActionPerformed(null);
        emptTeksNilaiKritis();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
            TCari1.setText("");
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        WindowNilaiKritis.dispose();
        tampil();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowNilaiKritis.dispose();
            tampil();
        }
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 2;
        initPetugas();
        akses.setform("DlgCariPeriksaRadiologi");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPetugasPenerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasPenerimaActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 3;
        initPetugas();
        akses.setform("DlgCariPeriksaRadiologi");
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasPenerimaActionPerformed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilNilaiKritis();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPtgs = "-";
                TnmPetugas.setText("-");
            } else {
                nipPtgs = akses.getkode();
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPtgs + "'"));
            }
        } else {
            nipPtgs = "-";
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipPenerimaLap = "-";
                TnmPetugasPenerima.setText("-");
            } else {
                nipPenerimaLap = akses.getkode();
                TnmPetugasPenerima.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPenerimaLap + "'"));
            }
        } else {
            nipPenerimaLap = "-";
            TnmPetugasPenerima.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar2.requestFocus();
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void MnNilaiKritisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnNilaiKritisActionPerformed
        if (tbPeriksaRadiologi.getSelectedRow() != -1) {
            if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            } else {
                WindowNilaiKritis.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                WindowNilaiKritis.setLocationRelativeTo(internalFrame1);
                WindowNilaiKritis.setVisible(true);
                tampilItemPemeriksaan(Kd2.getText(), tglperiksa, jam);
                emptTeksNilaiKritis();
                TCari1.setText(TNoRw1.getText());
                tampilNilaiKritis();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            tampil();
        }
    }//GEN-LAST:event_MnNilaiKritisActionPerformed

    private void TketeranganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketeranganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan8.requestFocus();
        }
    }//GEN-LAST:event_TketeranganKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void chkPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPemeriksaanActionPerformed
        if (chkPemeriksaan.isSelected() == true) {
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
    }//GEN-LAST:event_chkPemeriksaanActionPerformed

    private void chkLaporActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLaporActionPerformed
        if (chkLapor.isSelected() == true) {
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
    }//GEN-LAST:event_chkLaporActionPerformed

    private void chkLapDiterimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLapDiterimaActionPerformed
        if (chkLapDiterima.isSelected() == true) {
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
    }//GEN-LAST:event_chkLapDiterimaActionPerformed

    private void BtnDownloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDownloadActionPerformed
        if (Sequel.cariInteger("select count(-1) from nilai_kritis_radiologi where "
                + "date(waktu_simpan) between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data tidak ditemukan untuk periode tgl. yang anda tentukan, silahkan ulangi lagi...");
            return;
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            dialog_simpan = Valid.openDialog();
            if (Valid.MyReportToExcelBoolean("SELECT @rownum := @rownum + 1 AS `No.`, "
                    + "    x.`No. RM`, "
                    + "    x.`Nama Pasien`, "
                    + "    x.`Tgl. Periksa`, "
                    + "    x.`Jam Input`, "
                    + "    x.`Poli/Inst./Ruang Rawat`, "
                    + "    x.`Nama Petugas`, "
                    + "    x.`Nama Pemeriksaan`, "
                    + "    x.`Jam Pmrksaan. Selesai`, "
                    + "    x.`Jam Lapor`, "
                    + "    x.`Jam Lap. Diterima`, "
                    + "    x.`Petugas Penerima Lap.`, "
                    + "    x.`Keterangan` "
                    + "FROM (SELECT p.no_rkm_medis AS `No. RM`, p.nm_pasien AS `Nama Pasien`, DATE_FORMAT(nk.tgl_periksa,'%d-%m-%Y') AS `Tgl. Periksa`, "
                    + "        TIME_FORMAT(nk.jam,'%H:%i:%s') AS `Jam Input`, IF(rp.status_lanjut = 'Ralan',pl.nm_poli,b.nm_bangsal) AS `Poli/Inst./Ruang Rawat`, "
                    + "        pg1.nama AS `Nama Petugas`, jpr.nm_perawatan AS `Nama Pemeriksaan`, IF(nk.cek_pemeriksaan = 'ya',TIME_FORMAT(nk.jam_pemeriksaan,'%H:%i:%s'),'') AS `Jam Pmrksaan. Selesai`, "
                    + "        IF(nk.cek_lapor = 'ya',TIME_FORMAT(nk.jam_lapor,'%H:%i:%s'),'') AS `Jam Lapor`, "
                    + "        IF(nk.cek_lap_diterima = 'ya',TIME_FORMAT(nk.jam_lap_diterima,'%H:%i:%s'),'') AS `Jam Lap. Diterima`, "
                    + "        pg2.nama AS `Petugas Penerima Lap.`, nk.keterangan AS `Keterangan`, nk.waktu_simpan AS waktu_simpan_sort "
                    + "    FROM nilai_kritis_radiologi nk INNER JOIN jns_perawatan_radiologi jpr ON jpr.kd_jenis_prw = nk.kd_jenis_prw "
                    + "    INNER JOIN reg_periksa rp ON rp.no_rawat = nk.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                    + "    INNER JOIN pegawai pg1 ON pg1.nik = nk.nip_petugas INNER JOIN pegawai pg2 ON pg2.nik = nk.nip_penerima_lap "
                    + "    INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli LEFT JOIN kamar_inap ki ON ki.no_rawat = rp.no_rawat "
                    + "    LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                    + "    WHERE DATE(nk.waktu_simpan) BETWEEN '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ORDER BY nk.waktu_simpan) x "
                    + "CROSS JOIN (SELECT @rownum := 0) r ORDER BY x.waktu_simpan_sort", dialog_simpan) == true) {
                JOptionPane.showMessageDialog(null, "Data nilai kritis pemeriksaan radiologi sesuai tgl. simpannya          \n"
                        + "berhasil didownload menjadi file excel,..!");
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnDownloadActionPerformed

    private void MnJamPemeriksaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJamPemeriksaanActionPerformed
        if (tbPeriksaRadiologi.getSelectedRow() != -1) {
            if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            } else {
                WindowJamPemeriksaan.setSize(777, 330);
                WindowJamPemeriksaan.setLocationRelativeTo(internalFrame1);
                WindowJamPemeriksaan.setVisible(true);
                tampilItemPemeriksaanJam(Kd2.getText(), tglperiksa, jam);
                emptTeksJamPemeriksaan();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            tampil();
        }
    }//GEN-LAST:event_MnJamPemeriksaanActionPerformed

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void BtnSimpan9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan9ActionPerformed
        if (tbItem1.getSelectedRow() != -1) {
            if (Sequel.mengedittf("periksa_radiologi", "no_rawat=? and tgl_periksa='" + tbItem1.getValueAt(tbItem1.getSelectedRow(), 5).toString() + "' "
                    + "and jam='" + tbItem1.getValueAt(tbItem1.getSelectedRow(), 2).toString() + "' "
                    + "and kd_jenis_prw='" + tbItem1.getValueAt(tbItem1.getSelectedRow(), 6).toString() + "'",
                    "jam_pemeriksaan=?", 2, new String[]{
                        cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(),
                        tbItem1.getValueAt(tbItem1.getSelectedRow(), 8).toString()
                    }) == true) {

                emptTeksJamPemeriksaan();
                tampilItemPemeriksaanJam(TNoRw2.getText(), tglperiksa, jam);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel...!");
            tampilItemPemeriksaanJam(TNoRw2.getText(), tglperiksa, jam);
        }
    }//GEN-LAST:event_BtnSimpan9ActionPerformed

    private void BtnSimpan9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan9KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpan9ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan9KeyPressed

    private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
        WindowJamPemeriksaan.dispose();
        tampil();
    }//GEN-LAST:event_BtnKeluar3ActionPerformed

    private void BtnBatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal2ActionPerformed
        emptTeksJamPemeriksaan();
        tampilItemPemeriksaanJam(TNoRw2.getText(), tglperiksa, jam);
    }//GEN-LAST:event_BtnBatal2ActionPerformed

    private void BtnBatal2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeksJamPemeriksaan();
        }
    }//GEN-LAST:event_BtnBatal2KeyPressed

    private void tbItem1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbItem1KeyPressed
        if(tabMode6.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataJamPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbItem1KeyPressed

    private void tbItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbItem1MouseClicked
        if(tabMode6.getRowCount()!=0){
            try {
                getDataJamPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbItem1MouseClicked

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowJamPemeriksaan.dispose();
            tampil();
        }
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void BtnCloseIn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn9ActionPerformed
        WindowGantiUnitPengirim.dispose();
        tampil();
    }//GEN-LAST:event_BtnCloseIn9ActionPerformed

    private void BtnSimpan10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan10ActionPerformed
        Sequel.mengedit("periksa_radiologi", "no_rawat='" + Kd2.getText() + "' and tgl_periksa='" + tglperiksa + "' and jam='" + jam + "'",
                "unit_pengirim='" + TunitPengirimGanti.getText() + "'");
        WindowGantiUnitPengirim.dispose();
        tampil();
    }//GEN-LAST:event_BtnSimpan10ActionPerformed

    private void btnCariUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariUnitActionPerformed
        if (sttsRawat.equals("Ralan")) {
            i = 0;
            dipilih = 0;
            dipilih = 2;
            
            pilihMenu = (String) JOptionPane.showInputDialog(null, "Silahkan pilih salah satu jenis asal unit pengirimnya .....           ", "Konfirmasi Pilihan",
                    JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Dari : Poliklinik/Instalasi", "Dari : Faskes Rujukan"}, "Unit Pengirim");
            switch (pilihMenu) {
                case "Dari : Poliklinik/Instalasi":
                    i = 1;
                    break;
                case "Dari : Faskes Rujukan":
                    i = 2;
                    break;
            }

            if (i == 1) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                initPoli();
                akses.setform("DlgCariPeriksaRadiologi");
                poli.isCek();
                poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                poli.setLocationRelativeTo(internalFrame1);
                poli.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (i == 2) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                initRujukMasuk();
                akses.setform("DlgCariPeriksaRadiologi");
                rujukmasuk.tampil2();
                rujukmasuk.WindowPerujuk.setSize(900, internalFrame1.getHeight() - 40);
                rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
                rujukmasuk.WindowPerujuk.setVisible(true);
                rujukmasuk.onCariPerujuk();
                this.setCursor(Cursor.getDefaultCursor());
            }
        } else {
            initKamar();
            akses.setform("DlgCariPeriksaRadiologi");
            kamarnya.load();
            kamarnya.isCek();
            kamarnya.emptTeks();
            kamarnya.tampil();
            kamarnya.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            kamarnya.setLocationRelativeTo(internalFrame1);
            kamarnya.setVisible(true);
        }
    }//GEN-LAST:event_btnCariUnitActionPerformed

    private void MnUnitPengirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUnitPengirimActionPerformed
        if (tbPeriksaRadiologi.getSelectedRow() != -1) {
            if (Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + Kd2.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            } else {                
                WindowGantiUnitPengirim.setSize(769, 88);
                WindowGantiUnitPengirim.setLocationRelativeTo(internalFrame1);
                WindowGantiUnitPengirim.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya, klik pada nama/no. RM pasien ditabel....");
            tampil();
        }
    }//GEN-LAST:event_MnUnitPengirimActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCariPeriksaRadiologi dialog = new DlgCariPeriksaRadiologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnBatal2;
    private widget.Button BtnBersih;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCariIgd;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCloseIn8;
    private widget.Button BtnCloseIn9;
    private widget.Button BtnDisamakan;
    private widget.Button BtnDownload;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapus2;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnPetugas;
    private widget.Button BtnPetugasPenerima;
    private widget.Button BtnPrint1;
    private widget.panelisi BtnPrintQR;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan10;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSimpan4;
    private widget.Button BtnSimpan5;
    private widget.Button BtnSimpan6;
    private widget.Button BtnSimpan7;
    private widget.Button BtnSimpan8;
    private widget.Button BtnSimpan9;
    private widget.Button BtnUnit;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextArea HasilPeriksa;
    private widget.TextArea HasilPeriksa1;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnAsalRujukan;
    private javax.swing.JMenuItem MnAsalRujukan1;
    private javax.swing.JMenuItem MnBeratBadan;
    private javax.swing.JMenu MnCetakBuktiReg;
    private javax.swing.JMenu MnCetakNota;
    private javax.swing.JMenuItem MnDokterPemeriksaRad;
    private javax.swing.JMenuItem MnDokterPemeriksaRad1;
    private javax.swing.JMenuItem MnDokterPengirim;
    private javax.swing.JMenuItem MnDokterPengirim1;
    private javax.swing.JMenuItem MnDokumenPenunjangMedis;
    private javax.swing.JMenu MnExportDataKeExcel;
    private javax.swing.JMenu MnGantiData;
    private javax.swing.JMenuItem MnHasilPemeriksaan;
    private javax.swing.JMenuItem MnInapDetailPerCaraBayar;
    private javax.swing.JMenuItem MnInapDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnInapPerCaraBayar;
    private javax.swing.JMenuItem MnInapSemuaCaraBayar;
    private javax.swing.JMenuItem MnJalanDetailPerCaraBayar;
    private javax.swing.JMenuItem MnJalanDetailPerPoli;
    private javax.swing.JMenuItem MnJalanDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnJalanPerCaraBayar;
    private javax.swing.JMenuItem MnJalanPerPoli;
    private javax.swing.JMenuItem MnJalanSemuaCaraBayar;
    private javax.swing.JMenuItem MnJamPemeriksaan;
    private javax.swing.JMenuItem MnKirimKeWhatsapp;
    private javax.swing.JMenuItem MnKirimKeWhatsappNota;
    private javax.swing.JMenuItem MnNilaiKritis;
    private javax.swing.JMenuItem MnParamaterPemeriksaan;
    private javax.swing.JMenuItem MnPemeriksaanRadiologi;
    private javax.swing.JMenuItem MnPendapatanRalan;
    private javax.swing.JMenuItem MnPendapatanRanap;
    private javax.swing.JMenuItem MnPendapatanSemuaRawat;
    private javax.swing.JMenuItem MnPetugasRadiologi;
    private javax.swing.JMenuItem MnRekapJenisPeriksaRadInap;
    private javax.swing.JMenuItem MnRekapJenisPeriksaRadJalan;
    private javax.swing.JMenuItem MnRekapJenisPeriksaRadSemua;
    private javax.swing.JMenuItem MnRekapRincianPemeriksaan;
    private javax.swing.JMenuItem MnRekapTotalPemeriksaan;
    private javax.swing.JMenuItem MnRiwayatExpertise;
    private javax.swing.JMenuItem MnRiwayatPerawatan;
    private javax.swing.JMenuItem MnRiwayatPerawatan1;
    private javax.swing.JMenuItem MnSRDetailPerCaraBayar;
    private javax.swing.JMenuItem MnSRDetailSemuaCaraBayar;
    private javax.swing.JMenuItem MnSRPerCaraBayar;
    private javax.swing.JMenuItem MnSRSemuaCaraBayar;
    private javax.swing.JMenuItem MnTTDnota;
    private javax.swing.JMenuItem MnTTDreg;
    private javax.swing.JMenuItem MnTTEnota;
    private javax.swing.JMenuItem MnTTEreg;
    private javax.swing.JMenuItem MnTglPemeriksaan;
    private javax.swing.JMenuItem MnTglReg;
    private javax.swing.JMenuItem MnTglRegKhususIGD;
    private javax.swing.JMenuItem MnUnitPengirim;
    private javax.swing.JMenuItem MnWaktuPeriksa;
    private javax.swing.JMenuItem MnWaktuPeriksa1;
    private widget.TextBox NoBalasan;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TDokter;
    private widget.TextBox TDokter1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRM2;
    private widget.TextBox TNoRW;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TNoRw2;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextBox TPasien2;
    private widget.TextBox TPetugas;
    private widget.TextBox TPoli;
    private widget.TextBox Tbb;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.TextBox TjamExp;
    private widget.TextBox TjamPemeriksaan;
    private widget.TextBox Tketerangan;
    private widget.TextBox TnilaiDlp;
    private widget.TextBox TnilaiFase;
    private widget.TextBox TnilaiKv;
    private widget.TextBox TnilaiMa;
    private widget.TextBox TnilaiMas;
    private widget.TextBox TnmPemeriksaan;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmPetugasPenerima;
    private widget.TextBox TnoRM;
    private widget.TextBox Tpasien;
    private widget.TextBox TpemeriksaanExp;
    private widget.TextBox TtglExp;
    private widget.TextBox TtglPemeriksaan;
    private widget.TextBox TunitPengirim;
    private widget.TextBox TunitPengirimGanti;
    private javax.swing.JDialog WindowBeratBadan;
    private javax.swing.JDialog WindowGantiDokterPerujuk;
    private javax.swing.JDialog WindowGantiDokterRad;
    private javax.swing.JDialog WindowGantiPetugasRadiologi;
    private javax.swing.JDialog WindowGantiRujukan;
    private javax.swing.JDialog WindowGantiUnitPengirim;
    public javax.swing.JDialog WindowHasil;
    private javax.swing.JDialog WindowJamPemeriksaan;
    private javax.swing.JDialog WindowNilaiKritis;
    private javax.swing.JDialog WindowParameter;
    private javax.swing.JDialog WindowRiwayatExpertise;
    private javax.swing.JDialog WindowWaktuPeriksa;
    private widget.TextBox asalRujukan;
    private widget.Button btnCariDokter;
    private widget.Button btnCariDokter1;
    private widget.Button btnCariFaskes;
    private widget.Button btnCariPetugas;
    private widget.Button btnCariUnit;
    private widget.Button btnPenjab;
    public widget.CekBox chkLapDiterima;
    public widget.CekBox chkLapor;
    public widget.CekBox chkPemeriksaan;
    private widget.CekBox chkSaya;
    private widget.CekBox chkSaya1;
    private widget.ComboBox cmbCetak;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbSttsTran;
    private widget.TextBox diagKlinis;
    private widget.TextBox diagKlinisRad;
    private widget.TextBox dokterPengirim;
    private widget.TextBox dokterRad;
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
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.TextBox itemDipilih;
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
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel39;
    private widget.Label jLabel40;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private javax.swing.JMenu jMnGantiData1;
    private javax.swing.JMenu jMnLapInap;
    private javax.swing.JMenu jMnLapJalan;
    private javax.swing.JMenu jMnLapJenisPeriksaRad;
    private javax.swing.JMenu jMnLapPemeriksaanDokter;
    private javax.swing.JMenu jMnLapPendapatan;
    private javax.swing.JMenu jMnLapSemuaRawat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.TextBox jamperiksarad;
    private widget.TextBox kddokter;
    private widget.TextBox kddokter1;
    private widget.TextBox kdpetugas;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label18;
    private widget.Label labelunit;
    private widget.TextBox nmFaskes;
    private widget.TextBox nmpasien;
    private widget.TextBox nmpnj;
    private widget.TextBox norm;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.TextBox rg_poli;
    private widget.ScrollPane scrollPane1;
    private widget.Tanggal tanggalPeriksa;
    private widget.Table tbDataPeriksa;
    private widget.Table tbExpertise;
    private widget.Table tbItem;
    private widget.Table tbItem1;
    private widget.Table tbKritis;
    private widget.Table tbPemeriksaan;
    private widget.Table tbPeriksaRadiologi;
    private widget.Tanggal tglNota;
    private widget.TextBox tglperiksarad;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        queryTampil();
        Valid.tabelKosong(tabMode);
        try {
            rs = ps.executeQuery();
            ttl = 0;
            while (rs.next()) {
                if (khususIgd.equals("ya")) {
                    namakamar = rs.getString("nm_poli");
                    kamar = "Poliklinik/Inst.";
                } else {
                    if (rs.getString("status_lanjut").equals("Ranap")) {
                        namakamar = rs.getString("nmBangsal");
                        kamar = "Kamar";
                    } else {
                        namakamar = rs.getString("nm_poli");
                        kamar = "Poliklinik/Inst.";
                    }
                }
                
                tabMode.addRow(new Object[]{
                    rs.getString("no_rawat"), rs.getString("no_rkm_medis") + " - " + rs.getString("nm_pasien") + " Umur : " + rs.getString("umur_thn") + ". (" + kamar + " : " + namakamar + ")", rs.getString("nama"),
                    rs.getString("tgl_periksa"), rs.getString("jam"), Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs.getString("dokter_perujuk")),
                    rs.getString("nm_dokter"), rs.getString("kd_dokter"), rs.getString("status_lanjut"), rs.getString("dokter_perujuk"), rs.getString("nip"),
                    "kV : " + rs.getString("kv") + ", mAs : " + rs.getString("mas") + ", mA/CTDi : " + rs.getString("ma"),
                    rs.getString("nilai_kv"), rs.getString("nilai_mas"), rs.getString("nilai_ma"), rs.getString("nilai_fase"), rs.getString("nilai_dlp"), rs.getString("warna"), "", rs.getString("unitPengirim")
                });
                tabMode.addRow(new Object[]{"Cara Bayar", ": " + rs.getString("png_jawab") + " (" + rs.getString("cekBayar") + ") | Berat Badan : " + rs.getString("bb"),
                    "Kode Periksa :", "Nama Pemeriksaan :", "Biaya Pmrksn. :", "Jam Selesai Hasil Bacaan :", "Durasi Pelayanan :", "", "", "", "",
                    "s/Fase : " + rs.getString("fase") + ", DLP : " + rs.getString("dlp"), "", "", "", "", "", "", "", ""});

                ps2.setString(1, rs.getString("no_rawat"));
                ps2.setString(2, rs.getString("tgl_periksa"));
                ps2.setString(3, rs.getString("jam"));
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    String warnaKritis = "", jamPemeriksaan = "";
                    ttl = ttl + rs2.getDouble("biaya");
                    cekJamSelesai = Sequel.cariIsi("select ifnull(time_format(hr.waktu_simpan,'%H:%i:%s'),'-') from periksa_radiologi pr "
                            + "inner join hasil_radiologi hr on hr.no_rawat=pr.no_rawat where "
                            + "hr.no_rawat='" + rs.getString("no_rawat") + "' and hr.tgl_periksa='" + rs.getString("tgl_periksa") + "' and hr.jam='" + rs.getString("jam") + "' "
                            + "and hr.kd_jenis_prw='" + rs2.getString("kd_jenis_prw") + "'");

                    cekDurasi = Sequel.cariIsi("SELECT IFNULL(CASE WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr.waktu_simpan) < 0 THEN '-' "
                            + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr.waktu_simpan) < 3600 "
                            + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr.waktu_simpan) / 60),' Menit') "
                            + "WHEN TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr.waktu_simpan) < 86400 "
                            + "THEN CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam),hr.waktu_simpan) / 3600),' Jam ', "
                            + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr.waktu_simpan) % 3600) / 60),' Menit') ELSE "
                            + "CONCAT(FLOOR(TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr.waktu_simpan) / 86400),' Hari ', "
                            + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr.waktu_simpan) % 86400) / 3600),' Jam ', "
                            + "FLOOR((TIMESTAMPDIFF(SECOND,TIMESTAMP(pr.tgl_periksa, pr.jam), hr.waktu_simpan) % 3600) / 60),' Menit') END,'-') "
                            + "FROM periksa_radiologi pr INNER JOIN hasil_radiologi hr ON hr.no_rawat = pr.no_rawat "
                            + "AND hr.tgl_periksa = pr.tgl_periksa AND hr.jam = pr.jam AND hr.kd_jenis_prw = pr.kd_jenis_prw where "
                            + "hr.no_rawat='" + rs.getString("no_rawat") + "' and hr.tgl_periksa='" + rs.getString("tgl_periksa") + "' and hr.jam='" + rs.getString("jam") + "' "
                            + "and hr.kd_jenis_prw='" + rs2.getString("kd_jenis_prw") + "'");

                    jamPemeriksaan = Sequel.cariIsi("select if(jam_pemeriksaan='00:00:00','',concat(jam_pemeriksaan,' WITA')) from periksa_radiologi where "
                            + "no_rawat='" + rs.getString("no_rawat") + "' and tgl_periksa='" + rs.getString("tgl_periksa") + "' "
                            + "and jam='" + rs.getString("jam") + "' and kd_jenis_prw='" + rs2.getString("kd_jenis_prw") + "'");
                    
                    if (Sequel.cariInteger("select count(*) from nilai_kritis_radiologi where no_rawat='" + rs.getString("no_rawat") + "' and tgl_periksa='" + rs.getString("tgl_periksa") + "' "
                            + "and jam='" + rs.getString("jam") + "' and kd_jenis_prw='" + rs2.getString("kd_jenis_prw") + "'") > 0) {
                        warnaKritis = "merah";
                    }
                    
                    tabMode.addRow(new Object[]{"Jam Pmrksn. Selesai", ": " + jamPemeriksaan, rs2.getString("kd_jenis_prw"), rs2.getString("nm_perawatan"),
                        Valid.SetAngka(rs2.getDouble("biaya")), cekJamSelesai, cekDurasi, "",
                        "", "", "", "", "", "", "", "", "", warnaKritis, "", ""});
                }
                tabMode.addRow(new Object[]{"Unit Pengirim", ": " + rs.getString("unitPengirim"), "Kode BHP", "Nama BHP", "Satuan", "Jumlah",
                    "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
                ps3.setString(1, rs.getString("no_rawat"));
                ps3.setString(2, rs.getString("tgl_periksa"));
                ps3.setString(3, rs.getString("jam"));
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode.addRow(new Object[]{"", "", rs3.getString("kode_brng"), rs3.getString("nama_brng"), rs3.getString("kode_sat"), rs3.getString("jumlah"),
                        "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
                }
            }
            if (ttl > 0) {
                tabMode.addRow(new Object[]{">>", "Total : " + Valid.SetAngka(ttl), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
            }
            jumlahNoRawat();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void SetNoRw(String norw) {
        TNoRW.setText(norw);
        khususIgd = "tidak";
        tampil();
        Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'", Tgl1);
    }

    private void getData() {
        Kd2.setText("");
        tglperiksa = "";
        tglhasil = "";
        jam = "";
        jamhasil = "";
        sttsRawat = "";
        lihatHasil = "";
        kodeRujukan = "";
        tgldaftar = "";
        tglnoRW = "";
        alamatFaskes = "";

        if (tbPeriksaRadiologi.getSelectedRow() != -1) {
            Kd2.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 0).toString());
            TNoRW.setText(Kd2.getText());
            TNoRw1.setText(Kd2.getText());
            TNoRw2.setText(Kd2.getText());
            TnoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + TNoRW.getText() + "'"));
            TNoRM1.setText(TnoRM.getText());
            TNoRM2.setText(TnoRM.getText());
            norm.setText(TnoRM.getText());
            Tpasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TnoRM.getText() + "'"));
            TPasien1.setText(Tpasien.getText());
            TPasien2.setText(Tpasien.getText());
            nmpasien.setText(Tpasien.getText());
            tglperiksa = tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString();
            tglhasil = tglperiksa;
            jam = tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString();
            jamhasil = jam;
            kddokter.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 7).toString());
            TDokter.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter.getText() + "'"));
            dokterRad.setText(TDokter.getText());
            dokterPengirim.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 5).toString());
            sttsRawat = tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 8).toString();
            tglperiksarad.setText(Valid.SetTglINDONESIA(tglperiksa));
            jamperiksarad.setText(jam);
            kddokter1.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 9).toString());
            TDokter1.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter1.getText() + "'"));
            tgldaftar = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRW.getText() + "'");
            tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT('" + tgldaftar + "','%Y/%m/%d')");
            kodeRujukan = Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + Kd2.getText() + "'");            
            TnilaiKv.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 12).toString());
            TnilaiMas.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 13).toString());
            TnilaiMa.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 14).toString());
            TnilaiFase.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 15).toString());
            TnilaiDlp.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 16).toString());
            TunitPengirim.setText(tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 19).toString());
            TunitPengirimGanti.setText(TunitPengirim.getText());

            if (kodeRujukan.equals("") || kodeRujukan.equals("784")) {
                asalRujukan.setText("-");
                nmFaskes.setText("-");
            } else {
                asalRujukan.setText(Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
                nmFaskes.setText(Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kd_rujukan='" + kodeRujukan + "'"));
            }

            if (sttsRawat.equals("Ralan")) {
                diagKlinis.setText(Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + Kd2.getText() + "'"));
                diagKlinisRad.setText(diagKlinis.getText());
                labelunit.setText("Poliklinik/Inst. : ");
                rg_poli.setText(Sequel.cariIsi("select nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + Kd2.getText() + "'"));
            } else {
                kamar = Sequel.cariIsi("select ifnull(kd_kamar,'') from kamar_inap where no_rawat='" + Kd2.getText() + "' order by tgl_masuk desc limit 1");
                diagKlinis.setText(Sequel.cariIsi("select diagnosa_awal from kamar_inap where no_rawat='" + Kd2.getText() + "'"));
                diagKlinisRad.setText(diagKlinis.getText());
                labelunit.setText("Ruang Rawat : ");
                rg_poli.setText(Sequel.cariIsi("select nm_bangsal from bangsal b inner join kamar k on b.kd_bangsal=k.kd_bangsal where k.kd_kamar='" + kamar + "'"));
            }
        }
    }

    private void getData1() {
        kdItem = "";
        if (tbPemeriksaan.getSelectedRow() != -1) {
            itemDipilih.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1).toString());
            kdItem = tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 6).toString();
            deskripsiHasil();
        }
    }

    public void isCek() {
        MnCetakNota.setEnabled(akses.getbilling_ralan());
        MnKirimKeWhatsappNota.setEnabled(akses.getbilling_ralan());
        MnCetakBuktiReg.setEnabled(akses.getperiksa_radiologi());
        BtnHapus.setEnabled(akses.getperiksa_radiologi());
        MnGantiData.setEnabled(akses.getperiksa_radiologi());
        MnPetugasRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnRiwayatPerawatan.setEnabled(akses.getperiksa_radiologi());
        BtnSimpan.setEnabled(akses.gettombolsimpan_hasil_rad());
        MnGantiData.setEnabled(akses.gettombolsimpan_hasil_rad());
        jMnGantiData1.setEnabled(akses.gettombolsimpan_hasil_rad());
        MnPemeriksaanRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnRiwayatExpertise.setEnabled(akses.getperiksa_radiologi());
        MnNilaiKritis.setEnabled(akses.getperiksa_radiologi());
        tglNota.setDate(new Date());
    }

    public void setPasien(String pasien) {
        TNoRW.setText(pasien);
    }

    public void empttext() {
        Kd2.setText("");
        tglperiksa = "";
        jam = "";
        sttsRawat = "";
        kddokter.setText("");
        TDokter.setText("");
        kddokter1.setText("");
        TDokter1.setText("");
        norm.setText("");
        nmpasien.setText("");
        tglperiksarad.setText("");
        jamperiksarad.setText("");
        diagKlinisRad.setText("");
        rg_poli.setText("");
        dokterRad.setText("");
        dokterPengirim.setText("");
        asalRujukan.setText("");
        HasilPeriksa.setText("");
        nmFaskes.setText("");
        itemDipilih.setText("");
    }

    private void tampilItem() {
        Valid.tabelKosong(tabMode1);
        try {
            psItem = koneksi.prepareStatement("select j.nm_perawatan, pr.tgl_periksa, pr.jam, concat('Rp. ',FORMAT(j.total_byr,0)) tarif, "
                    + "pr.no_rawat, pr.kd_jenis_prw, date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw where "
                    + "pr.no_rawat=? and pr.tgl_periksa=? and pr.jam=? ORDER BY pr.tgl_periksa desc, pr.jam desc");

            try {
                if (lihatHasil.equals("")) {
                    psItem.setString(1, Kd2.getText());
                    psItem.setString(2, tglperiksa);
                    psItem.setString(3, jam);
                } else if (lihatHasil.equals("OK")) {
                    psItem.setString(1, Kd2.getText());
                    psItem.setString(2, tglhasil);
                    psItem.setString(3, jamhasil);
                }

                rsItem = psItem.executeQuery();
                n = 1;
                while (rsItem.next()) {
                    tabMode1.addRow(new Object[]{
                        n + ".",
                        rsItem.getString("nm_perawatan"),
                        rsItem.getString("tglnya"),
                        rsItem.getString("jam"),
                        rsItem.getString("tarif"),
                        rsItem.getString("no_rawat"),
                        rsItem.getString("kd_jenis_prw"),
                        rsItem.getString("tgl_periksa")
                    });
                    n++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgCariPeriksaRadiologi.tampilItem() : " + e);
            } finally {
                if (rsItem != null) {
                    rsItem.close();
                }
                if (psItem != null) {
                    psItem.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void deskripsiHasil() {
        cekHasil = 0;
        cekDiagnos = "";

        if (lihatHasil.equals("")) {
            cekHasil = Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                    + "tgl_periksa='" + tglperiksa + "' and jam='" + jam + "' and kd_jenis_prw='" + kdItem + "'");
            cekDiagnos = Sequel.cariIsi("select diag_klinis_radiologi from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                    + "tgl_periksa='" + tglperiksa + "' and jam='" + jam + "' and kd_jenis_prw='" + kdItem + "'");
        } else if (lihatHasil.equals("OK")) {
            cekHasil = Sequel.cariInteger("select count(-1) from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                    + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'");
            cekDiagnos = Sequel.cariIsi("select diag_klinis_radiologi from hasil_radiologi where no_rawat='" + Kd2.getText() + "' and "
                    + "tgl_periksa='" + tglhasil + "' and jam='" + jamhasil + "' and kd_jenis_prw='" + kdItem + "'");
        }

        if (cekHasil == 0) {
            HasilPeriksa.setText("");
        } else {
            try {
                if (lihatHasil.equals("")) {
                    ps5.setString(1, "%" + Kd2.getText() + "%");
                    ps5.setString(2, "%" + tglperiksa + "%");
                    ps5.setString(3, "%" + jam + "%");
                    ps5.setString(4, "%" + kdItem + "%");
                } else if (lihatHasil.equals("OK")) {
                    ps5.setString(1, "%" + Kd2.getText() + "%");
                    ps5.setString(2, "%" + tglhasil + "%");
                    ps5.setString(3, "%" + jamhasil + "%");
                    ps5.setString(4, "%" + kdItem + "%");
                }

                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    HasilPeriksa.setText(rs5.getString("hasil"));
                    if (cekDiagnos.trim().equals("")) {
                        diagKlinisRad.setText("");
                    } else {
                        diagKlinisRad.setText(rs5.getString("diag_klinis_radiologi"));
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    private void cetakNota() {
        String crbyr = "", nmpetgs = "";
        crbyr = Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + Kd2.getText() + "'");
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("norm", TnoRM.getText());
        param.put("nmpasien", Tpasien.getText());
        param.put("tglPeriksa", tglperiksa + ", Pukul : " + jam);
        param.put("drRad", dokterRad.getText());
        param.put("cara_byr", crbyr);
        param.put("tglNota", "Martapura, " + tglNota.getSelectedItem().toString());
        param.put("umur", Sequel.cariIsi("select concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (',rp.umurdaftar,' ',rp.sttsumur,'.)') from reg_periksa rp "
                + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + Kd2.getText() + "'"));
        
        if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + Kd2.getText() + "' and status_lanjut='Ralan'") > 0) {
            param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + Kd2.getText() + "'"));
        } else {
            param.put("nmUnit", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + Kd2.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
        }

        if (akses.getadmin() == true) {
            nmpetgs = "...................";            
        } else {
            nmpetgs = Sequel.cariIsi("select nama from petugas where nip='" + akses.getkode() + "'");
        }
        
        param.put("petugas_ksr", "( " + nmpetgs + " )");

        if (tte.equals("tidak")) {
            Valid.MyReport("rptNotaRadiologi.jasper", "report", "::[ Nota Transaksi Radiologi ]::",
                    " SELECT temp1, FORMAT(temp2, 0) biaya, (SELECT FORMAT(temp2, 0) FROM temporary WHERE temp1 = 'Total Biaya Pemeriksaan Radiologi') total_byr "
                    + "FROM temporary WHERE temp1 NOT LIKE '%biaya%' ", param);
        } else if (tte.equals("ya")) {
            if (akses.getadmin() == true) {
                Valid.MyReport("rptNotaRadiologi.jasper", "report", "::[ Nota Transaksi Radiologi ]::",
                        " SELECT temp1, FORMAT(temp2, 0) biaya, (SELECT FORMAT(temp2, 0) FROM temporary WHERE temp1 = 'Total Biaya Pemeriksaan Radiologi') total_byr "
                        + "FROM temporary WHERE temp1 NOT LIKE '%biaya%' ", param);
            } else {
                String isi = "";
                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='011'"),
                                "Nota Transaksi Radiologi", nmpetgs,
                                Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='011'");

                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Nota Transaksi Radiologi", Sequel.cariFolderPrintTte());
                param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='011'"));

                Valid.MyReport("rptNotaRadiologiQr.jasper", "report", "::[ Nota Transaksi Radiologi ]::",
                        " SELECT temp1, FORMAT(temp2, 0) biaya, (SELECT FORMAT(temp2, 0) FROM temporary WHERE temp1 = 'Total Biaya Pemeriksaan Radiologi') total_byr "
                        + "FROM temporary WHERE temp1 NOT LIKE '%biaya%' ", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            }
        }
    }
    
    private void cetakBuktiReg() {
        String crbyr = "", nmpetgs = "";
        crbyr = Sequel.cariIsi("select p.png_jawab from reg_periksa r inner join penjab p on p.kd_pj=r.kd_pj where r.no_rawat='" + Kd2.getText() + "'");
        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("norm", TnoRM.getText());
        param.put("nmpasien", Tpasien.getText());
        param.put("tglPeriksa", tglperiksa + ", Pukul : " + jam);
        param.put("drRad", dokterRad.getText());
        param.put("cara_byr", crbyr);
        param.put("tglReg", "Martapura, " + tglNota.getSelectedItem().toString());
        param.put("umur", Sequel.cariIsi("select concat(date_format(p.tgl_lahir,'%d/%m/%Y'),' (',rp.umurdaftar,' ',rp.sttsumur,'.)') from reg_periksa rp "
                + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + Kd2.getText() + "'"));

        if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat='" + Kd2.getText() + "' and status_lanjut='Ralan'") > 0) {
            param.put("nmUnit", Sequel.cariIsi("select pl.nm_poli from reg_periksa rp inner join poliklinik pl on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + Kd2.getText() + "'"));
        } else {
            param.put("nmUnit", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + Kd2.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
        }

        if (akses.getadmin() == true) {
            nmpetgs = "...................";            
        } else {
            nmpetgs = Sequel.cariIsi("select pg.nama from periksa_radiologi p inner join pegawai pg on pg.nik=p.nip where p.no_rawat='" + Kd2.getText() + "' "
                    + "and p.tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString() + "' "
                    + "and p.jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString() + "'");
        }
        
        param.put("petugas", "( " + nmpetgs + " )");
        
        if (tte.equals("tidak")) {
            Valid.MyReport("rptBuktiRegRadiologi.jasper", "report", "::[ Bukti Registrasi Radiologi ]::",
                    "SELECT temp1, temp2 FROM temporary WHERE temp1 NOT LIKE '%biaya%'", param);
        } else if (tte.equals("ya")) {
            if (akses.getadmin() == true) {
                Valid.MyReport("rptBuktiRegRadiologi.jasper", "report", "::[ Bukti Registrasi Radiologi ]::",
                        "SELECT temp1, temp2 FROM temporary WHERE temp1 NOT LIKE '%biaya%'", param);
            } else {                
                String isi = "", nipPtgs = "";
                nipPtgs = Sequel.cariIsi("select nip from periksa_radiologi where no_rawat='" + Kd2.getText() + "' "
                        + "and tgl_periksa='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 3).toString() + "' "
                        + "and jam='" + tbPeriksaRadiologi.getValueAt(tbPeriksaRadiologi.getSelectedRow(), 4).toString() + "'");

                if (nipPtgs.equals("") || nipPtgs.equals("-") || nipPtgs.equals("--") || nipPtgs.equals("PP23")) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf, nama petugas radiologi harus diisi dulu dengan benar sesuai namanya,...   ");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Bukti Registrasi Radiologi", nmpetgs,
                                    Sequel.cariIsi("select date_format('" + Valid.SetTgl(tglNota.getSelectedItem() + "") + "','%d/%m/%Y')"),
                                    Sequel.cariIsi("select time(now())")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Bukti Registrasi Radiologi", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                    Valid.MyReport("rptBuktiRegRadiologiQr.jasper", "report", "::[ Bukti Registrasi Radiologi ]::",
                            "SELECT temp1, temp2 FROM temporary WHERE temp1 NOT LIKE '%biaya%'", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            }
        }
    }

    public void setData(String norw, String kditem, String item, String lihathasil, String Norm, String nmPas,
            String tglrad, String jamrad, String diagnosa, String unit, String drRad, String drKirim, String rujukan) {
        cekPeriksa = 0;
        norm.setText(Norm);
        nmpasien.setText(nmPas);
        tglperiksarad.setText(Valid.SetTglINDONESIA(tglrad));
        jamperiksarad.setText(jamrad);
        diagKlinisRad.setText(diagnosa);
        rg_poli.setText(unit);
        dokterRad.setText(drRad);
        dokterPengirim.setText(drKirim);
        asalRujukan.setText(rujukan);
        Kd2.setText(norw);
        tglhasil = tglrad;
        jamhasil = jamrad;

        if (Sequel.cariIsi("select status_lanjut from reg_periksa where no_rawat='" + norw + "'").equals("ralan")) {
            labelunit.setText("Poliklinik/Inst. : ");
        } else {
            labelunit.setText("Ruang Rawat : ");
        }

        cekPeriksa = Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + norw + "' "
                + "and tgl_periksa='" + tglrad + "' and jam='" + jamrad + "'");
        if (cekPeriksa == 1) {
            kdItem = kditem;
            itemDipilih.setText(item);
            lihatHasil = lihathasil;
            tampilItem();
            deskripsiHasil();
            HasilPeriksa.requestFocus();
        } else if (cekPeriksa > 1) {
            kdItem = kditem;
            itemDipilih.setText(item);
            lihatHasil = lihathasil;
            tampilItem();
            tbPemeriksaan.requestFocus();
        }
    }
    
    private void tampilDataPeriksa() {
        Valid.tabelKosong(tabMode2);
        try {
            ps6 = koneksi.prepareStatement("select j.nm_perawatan, pr.tgl_periksa, pr.jam, concat('Rp. ',FORMAT(j.total_byr,0)) tarif, "
                    + "pr.no_rawat, pr.kd_jenis_prw, date_format(pr.tgl_periksa,'%d-%m-%Y') tglnya FROM periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi j on j.kd_jenis_prw=pr.kd_jenis_prw where "
                    + "pr.no_rawat=? and pr.tgl_periksa=? and pr.jam=? ORDER BY pr.tgl_periksa desc, pr.jam desc");
            try {
                ps6.setString(1, Kd2.getText());
                ps6.setString(2, tglhasil);
                ps6.setString(3, jamhasil);
                rs6 = ps6.executeQuery();
                n = 1;
                while (rs6.next()) {
                    tabMode2.addRow(new String[]{
                        n + ".",
                        rs6.getString("nm_perawatan"),
                        rs6.getString("tglnya"),
                        rs6.getString("jam"),
                        rs6.getString("tarif"),
                        rs6.getString("no_rawat"),
                        rs6.getString("kd_jenis_prw"),
                        rs6.getString("tgl_periksa")
                    });
                    n++;
                }                
            } catch (Exception e) {
                System.out.println("tampilDataPeriksa() : " + e);
            } finally {
                if (rs6 != null) {
                    rs6.close();
                }
                if (ps6 != null) {
                    ps6.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwExpertise() {
        Valid.tabelKosong(tabMode3);
        try {
            ps7 = koneksi.prepareStatement("SELECT hr.*, DATE_FORMAT(hr.tgl_periksa,'%d-%m-%Y') tglPeriksa, jpr.nm_perawatan FROM hasil_radiologi hr "
                    + "inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=hr.kd_jenis_prw where "
                    + "hr.no_rawat='" + tbDataPeriksa.getValueAt(tbDataPeriksa.getSelectedRow(), 5).toString() + "'");
            try {
                rs7 = ps7.executeQuery();
                while (rs7.next()) {
                    tabMode3.addRow(new String[]{
                        rs7.getString("no_rawat"),
                        rs7.getString("tglPeriksa"),
                        rs7.getString("jam"),
                        rs7.getString("nm_perawatan"),
                        rs7.getString("diag_klinis_radiologi"),
                        rs7.getString("tgl_periksa"),
                        rs7.getString("hasil"),
                        rs7.getString("kd_jenis_prw"),
                        rs7.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilRiwExpertise() : " + e);
            } finally {
                if (rs7 != null) {
                    rs7.close();
                }
                if (ps7 != null) {
                    ps7.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void emptTeksRiwayat() {
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");        
        TnmPemeriksaan.setText("");
        TtglPemeriksaan.setText("");
        TjamPemeriksaan.setText("");        
        TpemeriksaanExp.setText("");
        TtglExp.setText("");
        TjamExp.setText("");
    }

    private void jumlahNoRawat() {
        int jmlnorwt = 0;
        for (i = 0; i < tabMode.getRowCount(); i++) {
            if (!tabMode.getValueAt(i, 0).equals("") && !tabMode.getValueAt(i, 0).equals("Cara Bayar")
                    && !tabMode.getValueAt(i, 0).equals("Jam Pmrksn. Selesai") && !tabMode.getValueAt(i, 0).equals(">>")
                    && !tabMode.getValueAt(i, 0).equals("Unit Pengirim")) {
                jmlnorwt++;
            }
        }
        LCount.setText(Valid.SetAngka(jmlnorwt));
    }

    private void queryTampil() {
        cariDataIgd = "";
        CariData = "";
        cariBayar = "";
        
        cariDataIgd = "pr.no_rawat LIKE '%" + TCari.getText() + "%' OR "
                + "rp.no_rkm_medis LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nip LIKE '%" + TCari.getText() + "%' OR "
                + "p.nm_pasien LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nama LIKE '%" + TCari.getText() + "%' OR "
                + "jpr.nm_perawatan LIKE '%" + TCari.getText() + "%' OR "
                + "pj.png_jawab LIKE '%" + TCari.getText() + "%' OR "
                + "rp.kd_pj LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_kv='','-',pr.nilai_kv) LIKE '%" + TCari.getText() + "%' OR "                
                + "if(pr.nilai_mas='','-',pr.nilai_mas) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_ma='','-',pr.nilai_ma) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_fase='','-',pr.nilai_fase) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_dlp='','-',pr.nilai_dlp) LIKE '%" + TCari.getText() + "%' OR "
                + "pr.unit_pengirim LIKE '%" + TCari.getText() + "%' OR "
                + "d.nm_dokter LIKE '%" + TCari.getText() + "%'";
        
        CariData = "pr.no_rawat LIKE '%" + TCari.getText() + "%' OR "
                + "rp.no_rkm_medis LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nip LIKE '%" + TCari.getText() + "%' OR "
                + "p.nm_pasien LIKE '%" + TCari.getText() + "%' OR "
                + "pt.nama LIKE '%" + TCari.getText() + "%' OR "
                + "jpr.nm_perawatan LIKE '%" + TCari.getText() + "%' OR "
                + "pl.nm_poli LIKE '%" + TCari.getText() + "%' OR "
                + "IFNULL(b.nm_bangsal,'-') LIKE '%" + TCari.getText() + "%' OR "
                + "pj.png_jawab LIKE '%" + TCari.getText() + "%' OR "
                + "rp.kd_pj LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_kv='','-',pr.nilai_kv) LIKE '%" + TCari.getText() + "%' OR "                
                + "if(pr.nilai_mas='','-',pr.nilai_mas) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_ma='','-',pr.nilai_ma) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_fase='','-',pr.nilai_fase) LIKE '%" + TCari.getText() + "%' OR "
                + "if(pr.nilai_dlp='','-',pr.nilai_dlp) LIKE '%" + TCari.getText() + "%' OR "
                + "pr.unit_pengirim LIKE '%" + TCari.getText() + "%' OR "
                + "CASE "
                + "    WHEN rp.kd_poli IN ('PON','IGDK') AND NOT EXISTS (SELECT 1 FROM kamar_inap ki0 WHERE ki0.no_rawat = pr.no_rawat AND "
                + "    TIMESTAMP(ki0.tgl_masuk, ki0.jam_masuk) <= TIMESTAMP(pr.tgl_periksa, pr.jam)) THEN 'kuning' "
                + "    WHEN EXISTS (SELECT 1 FROM kamar_inap kiw INNER JOIN kamar kw ON kw.kd_kamar = kiw.kd_kamar INNER JOIN bangsal bw ON bw.kd_bangsal = kw.kd_bangsal WHERE "
                + "    kiw.no_rawat = pr.no_rawat AND bw.nm_gedung IN ('ICU','PERINATOLOGI') "
                + "    AND TIMESTAMP(pr.tgl_periksa, pr.jam) >= TIMESTAMP(kiw.tgl_masuk, kiw.jam_masuk) "
                + "    AND (kiw.stts_pulang = '-' OR TIMESTAMP(pr.tgl_periksa, pr.jam) <= TIMESTAMP(kiw.tgl_keluar, kiw.jam_keluar))) THEN 'kuning' "
                + "    ELSE 'hijau' END LIKE '%" + TCari.getText() + "%' OR "
                + "d.nm_dokter LIKE '%" + TCari.getText() + "%'";

        if (cmbSttsTran.getSelectedIndex() == 0) {
            cariBayar = "";
        } else {
            cariBayar = "HAVING cekBayar = '" + cmbSttsTran.getSelectedItem().toString() + "'";
        }
        
        try {
            if (khususIgd.equals("ya")) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("select rp.no_rkm_medis, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur) as umur_thn, pt.nama, ");
                sb1.append("d.nm_dokter, rp.status_lanjut, pj.png_jawab, rp.kd_pj, pl.nm_poli, '-' nmBangsal, ");
                sb1.append("CASE WHEN rp.kd_pj='U01' THEN IF(COUNT(bl.no_rawat) > 0, 'Sudah Lunas', 'Belum Bayar') ");
                sb1.append("ELSE IF(COUNT(pp.no_rawat) > 0, 'Piutang', 'Transaksi Belum Selesai') END cekBayar, IFNULL(CASE WHEN pr.berat_badan = '' THEN '-' ");
                sb1.append("WHEN LOWER(pr.berat_badan) LIKE '%kg%' OR LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan ");
                sb1.append("WHEN LENGTH(pr.berat_badan) >= 4 THEN CONCAT(pr.berat_badan, ' gram') ");
                sb1.append("ELSE CONCAT(pr.berat_badan, ' kg') END,'-') bb, if(pr.nilai_kv='','-',pr.nilai_kv) kv, if(pr.nilai_mas='','-',pr.nilai_mas) mas, ");
                sb1.append("if(pr.nilai_ma='','-',pr.nilai_ma) ma, if(pr.nilai_fase='','-',pr.nilai_fase) fase, if(pr.nilai_dlp='','-',pr.nilai_dlp) dlp, pr.*, 'kuning' warna, 'IGD' as unitPengirim ");
                sb1.append("from periksa_radiologi pr inner join reg_periksa rp on rp.no_rawat=pr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis ");
                sb1.append("inner join petugas pt on pt.nip=pr.nip inner join dokter d on d.kd_dokter=pr.kd_dokter inner join penjab pj on pj.kd_pj=rp.kd_pj ");
                sb1.append("inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw inner join poliklinik pl on pl.kd_poli=rp.kd_poli ");
                sb1.append("LEFT JOIN billing bl ON bl.no_rawat=pr.no_rawat LEFT JOIN piutang_pasien pp ON pp.no_rawat=pr.no_rawat where ");
                sb1.append("pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND rp.kd_poli = 'igdk' AND (" + cariDataIgd + ") ");
                sb1.append("group by concat(pr.no_rawat,pr.tgl_periksa,pr.jam) " + cariBayar + " order by pr.tgl_periksa desc, pr.jam desc");
                ps = koneksi.prepareStatement(sb1.toString());
            } else {
                StringBuilder sb1 = new StringBuilder();
                sb1.append("SELECT rp.no_rkm_medis, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur) AS umur_thn, pt.nama, d.nm_dokter, ");
                sb1.append("rp.status_lanjut, pj.png_jawab, rp.kd_pj, pl.nm_poli, IFNULL(b.nm_bangsal,'-') AS nmBangsal, ");
                sb1.append("CASE WHEN rp.kd_pj='U01' THEN IF(COUNT(bl.no_rawat) > 0, 'Sudah Lunas', 'Belum Bayar') ELSE IF(COUNT(pp.no_rawat) > 0, 'Piutang', 'Transaksi Belum Selesai') END AS cekBayar, ");
                sb1.append("IFNULL(CASE WHEN pr.berat_badan = '' THEN '-' WHEN LOWER(pr.berat_badan) LIKE '%kg%' OR LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan ");
                sb1.append("WHEN LENGTH(pr.berat_badan) >= 4 THEN CONCAT(pr.berat_badan, ' gram') ELSE CONCAT(pr.berat_badan, ' kg') END,'-') AS bb, ");
                sb1.append("IF(pr.nilai_kv='','-',pr.nilai_kv) AS kv, IF(pr.nilai_mas='','-',pr.nilai_mas) AS mas, IF(pr.nilai_ma='','-',pr.nilai_ma) AS ma, ");
                sb1.append("IF(pr.nilai_fase='','-',pr.nilai_fase) AS fase, IF(pr.nilai_dlp='','-',pr.nilai_dlp) AS dlp, pr.*, ");
                sb1.append("CASE ");
                // RALAN IGD/PONEK: pemeriksaan terjadi sebelum pasien masuk kamar rawat inap
                sb1.append("    WHEN rp.kd_poli IN ('PON','IGDK') AND NOT EXISTS (SELECT 1 FROM kamar_inap ki0 WHERE ki0.no_rawat = pr.no_rawat AND ");
                sb1.append("    TIMESTAMP(ki0.tgl_masuk, ki0.jam_masuk) <= TIMESTAMP(pr.tgl_periksa, pr.jam)) THEN 'kuning' ");
                // RANAP: pada waktu pemeriksaan pasien berada di ICU/PERINATOLOGI 
                sb1.append("    WHEN EXISTS (SELECT 1 FROM kamar_inap kiw INNER JOIN kamar kw ON kw.kd_kamar = kiw.kd_kamar INNER JOIN bangsal bw ON bw.kd_bangsal = kw.kd_bangsal WHERE ");
                sb1.append("    kiw.no_rawat = pr.no_rawat AND bw.nm_gedung IN ('ICU','PERINATOLOGI') ");
                // sudah masuk kamar 
                sb1.append("    AND TIMESTAMP(pr.tgl_periksa, pr.jam) >= TIMESTAMP(kiw.tgl_masuk, kiw.jam_masuk) ");
                // belum keluar dari kamar tersebut 
                sb1.append("    AND (kiw.stts_pulang = '-' OR TIMESTAMP(pr.tgl_periksa, pr.jam) <= TIMESTAMP(kiw.tgl_keluar, kiw.jam_keluar))) THEN 'kuning' ");
                // Ralan biasa atau Ranap di ruangan biasa 
                sb1.append("    ELSE 'hijau' END AS warna, pr.unit_pengirim as unitPengirim ");
                sb1.append("FROM periksa_radiologi pr INNER JOIN reg_periksa rp ON rp.no_rawat = pr.no_rawat ");
                sb1.append("INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN petugas pt ON pt.nip = pr.nip ");
                sb1.append("INNER JOIN dokter d ON d.kd_dokter = pr.kd_dokter INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj ");
                sb1.append("INNER JOIN jns_perawatan_radiologi jpr ON jpr.kd_jenis_prw = pr.kd_jenis_prw INNER JOIN poliklinik pl ON pl.kd_poli = rp.kd_poli ");
                sb1.append("LEFT JOIN kamar_inap ki ON ki.no_rawat = pr.no_rawat LEFT JOIN kamar k ON k.kd_kamar = ki.kd_kamar ");
                sb1.append("LEFT JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal LEFT JOIN billing bl ON bl.no_rawat = pr.no_rawat LEFT JOIN piutang_pasien pp ON pp.no_rawat = pr.no_rawat where ");
                sb1.append("pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND (" + CariData + ") ");
                sb1.append("group by concat(pr.no_rawat,pr.tgl_periksa,pr.jam) " + cariBayar + " order by pr.tgl_periksa desc, pr.jam desc");
                
//                sb1.append("select rp.no_rkm_medis, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur) as umur_thn, pt.nama, ");
//                sb1.append("d.nm_dokter, rp.status_lanjut, pj.png_jawab, rp.kd_pj, pl.nm_poli, ifnull(b.nm_bangsal,'-') nmBangsal, ");
//                sb1.append("CASE WHEN rp.kd_pj='U01' THEN IF(COUNT(bl.no_rawat) > 0, 'Sudah Lunas', 'Belum Bayar') ");
//                sb1.append("ELSE IF(COUNT(pp.no_rawat) > 0, 'Piutang', 'Transaksi Belum Selesai') END cekBayar, IFNULL(CASE WHEN pr.berat_badan = '' THEN '-' ");
//                sb1.append("WHEN LOWER(pr.berat_badan) LIKE '%kg%' OR LOWER(pr.berat_badan) LIKE '%gram%' THEN pr.berat_badan ");
//                sb1.append("WHEN LENGTH(pr.berat_badan) >= 4 THEN CONCAT(pr.berat_badan, ' gram') ");
//                sb1.append("ELSE CONCAT(pr.berat_badan, ' kg') END,'-') bb, if(pr.nilai_kv='','-',pr.nilai_kv) kv, if(pr.nilai_mas='','-',pr.nilai_mas) mas, ");
//                sb1.append("if(pr.nilai_ma='','-',pr.nilai_ma) ma, if(pr.nilai_fase='','-',pr.nilai_fase) fase, if(pr.nilai_dlp='','-',pr.nilai_dlp) dlp, pr.*, ");
//                sb1.append("CASE WHEN rp.status_lanjut = 'Ralan' AND rp.kd_poli IN ('PON', 'IGDK') THEN 'kuning' ");
//                sb1.append("    WHEN rp.status_lanjut = 'Ralan' THEN 'hijau' ");
//                sb1.append("    WHEN rp.status_lanjut = 'Ranap' AND EXISTS (SELECT 1 FROM kamar_inap kiw INNER JOIN kamar kw ON kw.kd_kamar = kiw.kd_kamar ");
//                sb1.append("    INNER JOIN bangsal bw ON bw.kd_bangsal = kw.kd_bangsal WHERE kiw.no_rawat = pr.no_rawat AND bw.nm_gedung IN ('ICU', 'PERINATOLOGI') ");
//                sb1.append("    AND TIMESTAMP(pr.tgl_periksa, pr.jam) >= TIMESTAMP(kiw.tgl_masuk, kiw.jam_masuk) ");
//                sb1.append("    AND (kiw.stts_pulang in ('-','Pindah Kamar') OR TIMESTAMP(pr.tgl_periksa, pr.jam) <= TIMESTAMP(kiw.tgl_keluar, kiw.jam_keluar))) THEN 'kuning' ELSE 'hijau' END AS warna ");
//                sb1.append("from periksa_radiologi pr inner join reg_periksa rp on rp.no_rawat=pr.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis ");
//                sb1.append("inner join petugas pt on pt.nip=pr.nip inner join dokter d on d.kd_dokter=pr.kd_dokter inner join penjab pj on pj.kd_pj=rp.kd_pj ");
//                sb1.append("inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw inner join poliklinik pl on pl.kd_poli=rp.kd_poli ");
//                sb1.append("left join kamar_inap ki on ki.no_rawat=pr.no_rawat left join kamar k on k.kd_kamar=ki.kd_kamar ");
//                sb1.append("left join bangsal b on b.kd_bangsal=k.kd_bangsal LEFT JOIN billing bl ON bl.no_rawat=pr.no_rawat ");
//                sb1.append("LEFT JOIN piutang_pasien pp ON pp.no_rawat=pr.no_rawat where ");
//                sb1.append("pr.tgl_periksa BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' AND '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND (" + CariData + ") ");
//                sb1.append("group by concat(pr.no_rawat,pr.tgl_periksa,pr.jam) " + cariBayar + " order by pr.tgl_periksa desc, pr.jam desc");
                ps = koneksi.prepareStatement(sb1.toString());
            }
      
            StringBuilder sb2 = new StringBuilder();
            sb2.append("select jns_perawatan_radiologi.kd_jenis_prw,jns_perawatan_radiologi.nm_perawatan,periksa_radiologi.biaya ");
            sb2.append("from periksa_radiologi inner join jns_perawatan_radiologi on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw where ");
            sb2.append("periksa_radiologi.no_rawat like ? and periksa_radiologi.tgl_periksa like ? and periksa_radiologi.jam like ?");
            ps2 = koneksi.prepareStatement(sb2.toString());

            StringBuilder sb3 = new StringBuilder();
            sb3.append("select beri_bhp_radiologi.kode_brng,ipsrsbarang.nama_brng,beri_bhp_radiologi.kode_sat,beri_bhp_radiologi.jumlah, ");
            sb3.append("beri_bhp_radiologi.total from beri_bhp_radiologi inner join ipsrsbarang on ipsrsbarang.kode_brng=beri_bhp_radiologi.kode_brng ");
            sb3.append("where beri_bhp_radiologi.no_rawat like ? and beri_bhp_radiologi.tgl_periksa like ? and beri_bhp_radiologi.jam like ?");
            ps3 = koneksi.prepareStatement(sb3.toString());

            StringBuilder sb4 = new StringBuilder();
            sb4.append("select periksa_radiologi.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,reg_periksa.umurdaftar umur,petugas.nama,periksa_radiologi.tgl_periksa,periksa_radiologi.jam,");
            sb4.append("periksa_radiologi.dokter_perujuk,periksa_radiologi.kd_dokter,periksa_radiologi.tgl_periksa,periksa_radiologi.jam,pasien.alamat,dokter.nm_dokter from periksa_radiologi inner join reg_periksa inner join pasien inner join petugas  inner join dokter ");
            sb4.append("on periksa_radiologi.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and periksa_radiologi.nip=petugas.nip and periksa_radiologi.kd_dokter=dokter.kd_dokter where ");
            sb4.append("periksa_radiologi.tgl_periksa like ? and periksa_radiologi.jam like ? and periksa_radiologi.no_rawat like ? group by concat(periksa_radiologi.no_rawat,periksa_radiologi.tgl_periksa,periksa_radiologi.jam)");
            ps4 = koneksi.prepareStatement(sb4.toString());

            StringBuilder sb5 = new StringBuilder();
            sb5.append("select hasil, diag_klinis_radiologi, kd_jenis_prw from hasil_radiologi where hasil_radiologi.no_rawat like ? and ");
            sb5.append("hasil_radiologi.tgl_periksa like ? and hasil_radiologi.jam like ? and hasil_radiologi.kd_jenis_prw like ?");
            ps5 = koneksi.prepareStatement(sb5.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void tampilPembaca(String norw, String kdPemriksaan, String tgl, String jamm) {
        dokterBaca = "";
        try {
            ps8 = koneksi.prepareStatement("SELECT ph.*, p.nama, date_format(ph.waktu_simpan,'%d-%m-%Y') tglBaca, time_format(ph.waktu_simpan,'%H:%i:%s') jamBaca "
                    + "FROM pembaca_hasil_radiologi ph inner join pegawai p on p.nik=ph.kd_dokter "
                    + "inner join jns_perawatan_radiologi jp on jp.kd_jenis_prw=ph.kd_jenis_prw where "
                    + "ph.no_rawat='" + norw + "' and ph.kd_jenis_prw='" + kdPemriksaan + "' and ph.tgl_periksa='" + tgl + "' "
                    + "and ph.jam_periksa='" + jamm + "' order by ph.waktu_simpan");
            try {
                rs8 = ps8.executeQuery();
                x = 0;
                while (rs8.next()) {                    
                    x++;
                    if (dokterBaca.equals("")) {
                        dokterBaca = x + ". " + rs8.getString("nama") + " (Tgl. " + rs8.getString("tglBaca") + ", Jam " + rs8.getString("jamBaca").substring(0, 5) + " Wita)";
                    } else {
                        dokterBaca = dokterBaca + "\n" + x + ". " + rs8.getString("nama") + " (Tgl. " + rs8.getString("tglBaca") + ", Jam " + rs8.getString("jamBaca").substring(0, 5) + " Wita)";
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void awalData() {
        khususIgd = "tidak";
        tampil();
    }

    private void initPoli() {
        if (poli == null) {
            poli = new DlgCariPoli(null, false);

            poli.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (dipilih == 1) {
                            if (poli.getTable().getSelectedRow() != -1) {
                                kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                                TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                kdpoli.requestFocus();
                            }
                        } else if (dipilih == 2) {
                            if (poli.getTable().getSelectedRow() != -1) {
                                kodepoliUnit = poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString();
                                if (kodepoliUnit.equals("-") || kodepoliUnit.equals("igdk") || kodepoliUnit.equals("rad") || kodepoliUnit.equals("laa")
                                        || kodepoliUnit.equals("lab") || kodepoliUnit.equals("iob") || kodepoliUnit.equals("kjh") || kodepoliUnit.equals("pkbrs")
                                        || kodepoliUnit.equals("pon") || kodepoliUnit.equals("tum")) {
                                    TunitPengirimGanti.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                } else {
                                    TunitPengirimGanti.setText("Poliklinik " + poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                }
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
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            if (pilihanDokter == 1) {
                                kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                                TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                                BtnSimpan1.requestFocus();
                            } else if (pilihanDokter == 2) {
                                kddokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                                TDokter1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                                BtnSimpan3.requestFocus();
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
        }
    }
    
    private void initRujukMasuk() {
        if (rujukmasuk == null) {
            rujukmasuk = new DlgRujukMasuk(null, false);

            rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (dipilih == 1) {
                            if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                                nmFaskes.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                                alamatFaskes = rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 3).toString();
                                kodeRujukan = rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString();
                            }
                            btnCariFaskes.requestFocus();
                        } else if (dipilih == 2) {
                            if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                                TunitPengirimGanti.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());                                
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
                    if (pilihPetugas == 1) {
                        if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                            if (petugas.getTable().getSelectedRow() != -1) {
                                kdpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                                TPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            }
                            btnCariPetugas.requestFocus();
                        }
                    } else if (pilihPetugas == 2) {
                        if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                            if (petugas.getTable().getSelectedRow() != -1) {
                                nipPtgs = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                                TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            }
                            chkSaya.setSelected(false);
                            BtnPetugas.requestFocus();
                        }
                    } else if (pilihPetugas == 3) {
                        if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                            if (petugas.getTable().getSelectedRow() != -1) {
                                nipPenerimaLap = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                                TnmPetugasPenerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            }
                            chkSaya1.setSelected(false);
                            BtnPetugasPenerima.requestFocus();
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

    private void initPenjab() {
        if (penjab == null) {
            penjab = new DlgPenanggungJawab(null, false);

            penjab.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (penjab.getTable().getSelectedRow() != -1) {
                            kdpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 1).toString());
                            nmpnj.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(), 2).toString());
                            btnPenjab.requestFocus();
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

            penjab.getTable().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            penjab.dispose();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
    
    private void tampilItemPemeriksaan(String norwt, String tglPeriksa, String jamPeriksa) {
        Valid.tabelKosong(tabMode4);
        try {
            ps9 = koneksi.prepareStatement("select pr.*, date_format(pr.tgl_periksa,'%d-%m-%Y') tglPeriksa, jpr.nm_perawatan from periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw where "
                    + "pr.no_rawat='" + norwt + "' and pr.tgl_periksa='" + tglPeriksa + "' and pr.jam='" + jamPeriksa + "' order by pr.jam");
            try {
                rs9 = ps9.executeQuery();
                x = 1;
                while (rs9.next()) {
                    tabMode4.addRow(new String[]{
                        x + ".",
                        rs9.getString("tglPeriksa"),
                        rs9.getString("jam"),
                        rs9.getString("nm_perawatan"),
                        rs9.getString("tgl_periksa"),
                        rs9.getString("kd_jenis_prw")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs9 != null) {
                    rs9.close();
                }
                if (ps9 != null) {
                    ps9.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilItemPemeriksaanJam(String norwt, String tglPeriksa, String jamPeriksa) {
        Valid.tabelKosong(tabMode6);
        try {
            ps11 = koneksi.prepareStatement("select pr.*, date_format(pr.tgl_periksa,'%d-%m-%Y') tglPeriksa, jpr.nm_perawatan, "
                    + "if(pr.jam_pemeriksaan='00:00:00','',pr.jam_pemeriksaan) jamPemeriksaan from periksa_radiologi pr "
                    + "inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw where "
                    + "pr.no_rawat='" + norwt + "' and pr.tgl_periksa='" + tglPeriksa + "' and pr.jam='" + jamPeriksa + "' order by pr.jam");
            try {
                rs11 = ps11.executeQuery();
                x = 1;
                while (rs11.next()) {
                    tabMode6.addRow(new String[]{
                        x + ".",
                        rs11.getString("tglPeriksa"),
                        rs11.getString("jam"),
                        rs11.getString("nm_perawatan"),
                        rs11.getString("jamPemeriksaan"),
                        rs11.getString("tgl_periksa"),
                        rs11.getString("kd_jenis_prw"),
                        rs11.getString("jam_pemeriksaan"),
                        rs11.getString("no_rawat")
                    });
                    x++;
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs11 != null) {
                    rs11.close();
                }
                if (ps11 != null) {
                    ps11.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void emptTeksNilaiKritis() {
        nipPtgs = "-";
        TnmPetugas.setText("-");
        
        chkPemeriksaan.setSelected(false);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        
        chkLapor.setSelected(false);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        
        chkLapDiterima.setSelected(false);
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);
        
        nipPenerimaLap = "-";
        TnmPetugasPenerima.setText("-");
        Tketerangan.setText("");
        chkSaya.setSelected(false);
        chkSaya1.setSelected(false);
    }
    
    private void tampilNilaiKritis() {
        Valid.tabelKosong(tabMode5);
        try {
            ps10 = koneksi.prepareStatement("select nk.*, p.no_rkm_medis, p.nm_pasien, date_format(nk.tgl_periksa,'%d-%m-%Y') tglPeriksa, pg1.nama nmPetugas, jpr.nm_perawatan, "
                    + "pg2.nama nmPenerima, if(nk.cek_pemeriksaan='ya',nk.jam_pemeriksaan,'') jamPemeriksaan, if(nk.cek_lapor='ya',nk.jam_lapor,'') jamLapor, "
                    + "if(nk.cek_lap_diterima='ya',nk.jam_lap_diterima,'') jamLapDiterima from nilai_kritis_radiologi nk "
                    + "inner join jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=nk.kd_jenis_prw inner join reg_periksa rp on rp.no_rawat=nk.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=nk.nip_petugas "
                    + "inner join pegawai pg2 on pg2.nik=nk.nip_penerima_lap where "
                    + "date(nk.waktu_simpan) between ? and ? and nk.no_rawat like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and p.nm_pasien like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and pg1.nama like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and pg2.nama like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and jpr.nm_perawatan like ? or "
                    + "date(nk.waktu_simpan) between ? and ? and nk.keterangan like ? order by nk.waktu_simpan desc");
            try {
                ps10.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(3, "%" + TCari1.getText() + "%");                
                ps10.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(6, "%" + TCari1.getText() + "%");
                ps10.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(9, "%" + TCari1.getText() + "%");
                ps10.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(12, "%" + TCari1.getText() + "%");
                ps10.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(15, "%" + TCari1.getText() + "%");
                ps10.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(18, "%" + TCari1.getText() + "%");
                ps10.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps10.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps10.setString(21, "%" + TCari1.getText() + "%");
                rs10 = ps10.executeQuery();
                while (rs10.next()) {
                    tabMode5.addRow(new String[]{
                        rs10.getString("no_rawat"),
                        rs10.getString("no_rkm_medis"),
                        rs10.getString("nm_pasien"),
                        rs10.getString("tglPeriksa"),
                        rs10.getString("jam"),
                        rs10.getString("nmPetugas"),
                        rs10.getString("nm_perawatan"),                        
                        rs10.getString("jamPemeriksaan"),
                        rs10.getString("jamLapor"),
                        rs10.getString("jamLapDiterima"),
                        rs10.getString("nmPenerima"),
                        rs10.getString("keterangan"),
                        rs10.getString("kd_jenis_prw"),
                        rs10.getString("tgl_periksa"),
                        rs10.getString("jam"),
                        rs10.getString("nip_petugas"),
                        rs10.getString("cek_pemeriksaan"),
                        rs10.getString("jam_pemeriksaan"),
                        rs10.getString("cek_lapor"),
                        rs10.getString("jam_lapor"),
                        rs10.getString("cek_lap_diterima"),
                        rs10.getString("jam_lap_diterima"),
                        rs10.getString("nip_penerima_lap"),
                        rs10.getString("keterangan"),
                        rs10.getString("waktu_simpan")                        
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs10 != null) {
                    rs10.close();
                }
                if (ps10 != null) {
                    ps10.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode5.getRowCount());
    }
    
    private void getDataNilaiKritis() {
        nipPtgs = "";
        nipPenerimaLap = "";
        cekPemeriksaan = "";
        cekLapor = "";
        cekLapDit = "";
        tglperiksa = "";
        jam = "";
        
        if (tbKritis.getSelectedRow() != -1) {
            TNoRw1.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 0).toString());
            TNoRM1.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 1).toString());
            TPasien1.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 2).toString());
            tampilItemPemeriksaan(TNoRw1.getText(), tbKritis.getValueAt(tbKritis.getSelectedRow(), 13).toString(),
                    tbKritis.getValueAt(tbKritis.getSelectedRow(), 14).toString());
            tglperiksa = tbKritis.getValueAt(tbKritis.getSelectedRow(), 13).toString();
            jam = tbKritis.getValueAt(tbKritis.getSelectedRow(), 14).toString();
            nipPtgs = tbKritis.getValueAt(tbKritis.getSelectedRow(), 15).toString();
            TnmPetugas.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 5).toString());
            
            cekPemeriksaan = tbKritis.getValueAt(tbKritis.getSelectedRow(), 16).toString();
            cmbJam2.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 17).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 17).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 17).toString().substring(6, 8));
            
            cekLapor = tbKritis.getValueAt(tbKritis.getSelectedRow(), 18).toString();
            cmbJam3.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 19).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 19).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 19).toString().substring(6, 8));
            
            cekLapDit = tbKritis.getValueAt(tbKritis.getSelectedRow(), 20).toString();
            cmbJam4.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 21).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 21).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbKritis.getValueAt(tbKritis.getSelectedRow(), 21).toString().substring(6, 8));
            
            nipPenerimaLap = tbKritis.getValueAt(tbKritis.getSelectedRow(), 22).toString();
            TnmPetugasPenerima.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 10).toString());
            Tketerangan.setText(tbKritis.getValueAt(tbKritis.getSelectedRow(), 11).toString());
            dataCek();
            chkSaya.setSelected(false);
            chkSaya1.setSelected(false);
        }
    }
    
    private void getDataJamPemeriksaan() {
        if (tbItem1.getSelectedRow() != -1) {            
            cmbJam5.setSelectedItem(tbItem1.getValueAt(tbItem1.getSelectedRow(), 7).toString().substring(0, 2));
            cmbMnt5.setSelectedItem(tbItem1.getValueAt(tbItem1.getSelectedRow(), 7).toString().substring(3, 5));
            cmbDtk5.setSelectedItem(tbItem1.getValueAt(tbItem1.getSelectedRow(), 7).toString().substring(6, 8));
            TNoRw2.setText(tbItem1.getValueAt(tbItem1.getSelectedRow(), 8).toString());
        }
    }
    
    private void cekData() {
        if (chkPemeriksaan.isSelected() == true) {
            cekPemeriksaan = "ya";
        } else {
            cekPemeriksaan = "tidak";
        }
        
        if (chkLapor.isSelected() == true) {
            cekLapor = "ya";
        } else {
            cekLapor = "tidak";
        }
        
        if (chkLapDiterima.isSelected() == true) {
            cekLapDit = "ya";
        } else {
            cekLapDit = "tidak";
        }
    }
    
    private void dataCek() {
        if (cekPemeriksaan.equals("ya")) {
            chkPemeriksaan.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            chkPemeriksaan.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        if (cekLapor.equals("ya")) {
            chkLapor.setSelected(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            chkLapor.setSelected(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
        
        if (cekLapDit.equals("ya")) {
            chkLapDiterima.setSelected(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            chkLapDiterima.setSelected(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
    }
    
    private void emptTeksJamPemeriksaan() {
        cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk5.setSelectedIndex(0);
        cmbJam5.requestFocus();
    }
    
    private void initKamar() {
        if (kamarnya == null) {
            kamarnya = new DlgKamar(null, false);

            kamarnya.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (kamarnya.getTable().getSelectedRow() != -1) {
                            kd_kamar = kamarnya.getTable().getValueAt(kamarnya.getTable().getSelectedRow(), 1).toString();
                            TunitPengirimGanti.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + kd_kamar + "'"));
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

            kamarnya.getTable().addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (akses.getform().equals("DlgCariPeriksaRadiologi")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            kamarnya.dispose();
                        }
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });
        }
    }
}
