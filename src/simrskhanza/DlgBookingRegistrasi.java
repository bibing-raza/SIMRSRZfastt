package simrskhanza;

import bridging.BPJSApi;
import bridging.BPJSCekNoKartu;
import bridging.BPJSCekReferensiDokterDPJP;
import bridging.BPJSCekReferensiFaskes;
import bridging.BPJSCekReferensiKabupaten;
import bridging.BPJSCekReferensiKecamatan;
import bridging.BPJSCekReferensiPenyakit;
import bridging.BPJSCekReferensiPoli;
import bridging.BPJSCekReferensiPropinsi;
import bridging.BPJSDataSEP;
import bridging.BPJSRujukanKeluar;
import bridging.BPJSSuratKontrol;
import bridging.DlgSKDPBPJS;
import bridging.DlgVerifikasiKodeBoking;
import bridging.ICareRiwayatPerawatan;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import informasi.InformasiJadwal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import static javafx.concurrent.Worker.State.FAILED;
import javafx.embed.swing.JFXPanel;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.transform.Scale;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.bouncycastle.util.Strings;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgPasien;
import simrskhanza.DlgRujukMasuk;

/**
 *
 * @author dosen
 */
public class DlgBookingRegistrasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, pskelengkapanBPJS, psPasien, ps3;
    private ResultSet rs, rskelengkapanBPJS, rsPasien, rs3;
    private WebEngine engine;
    private final JFXPanel jfxPanel = new JFXPanel();
    private final JPanel panel = new JPanel(new BorderLayout());
    private int i = 0, pilihan = 1, cekDataPlg = 0, x = 0, n = 0;
    private BPJSApi api = new BPJSApi();
    public DlgCariDokter2 dokter = new DlgCariDokter2(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private BPJSCekReferensiPoli poliBPJS = new BPJSCekReferensiPoli(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private BPJSCekReferensiDokterDPJP dpjp = new BPJSCekReferensiDokterDPJP(null, false);
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private BPJSCekReferensiPropinsi provinsi = new BPJSCekReferensiPropinsi(null, false);
    private BPJSCekReferensiKabupaten kabupaten = new BPJSCekReferensiKabupaten(null, false);
    private BPJSCekReferensiKecamatan kecamatan = new BPJSCekReferensiKecamatan(null, false);
    private BPJSSuratKontrol surat_suratan = new BPJSSuratKontrol(null, false);
    public DlgBahasa bahasa = new DlgBahasa(null, false);
    public DlgSuku suku = new DlgSuku(null, false);
    private String URUTNOREG = "", status = "", no_rawat = "", umur = "", sttsumur = "", no_peserta = "", user = "", 
            tglkkl = "0000-00-00", URL = "", utc = "", pembi = "", kdpnjg = "", SEPkontrol = "", tglPulangInap = "",
            flag = "", asesmen = "", jkel = "", cekPembi = "", cekFlag = "", cekKDpen = "", cekAses = "", cekRujukan = "",
            link = "", cekKodePJ = "";
    private LocalDate date1, date2;
    private final JProgressBar progressBar = new JProgressBar();

    /**
     * Creates new form DlgPemberianInfus
     *
     * @param parent
     * @param modal
     */
    public DlgBookingRegistrasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        WindowCariNoRujuk.setSize(874, 250);

        tabMode = new DefaultTableModel(null, new Object[]{
            "Kode Booking",
            "Tgl. Booking",
            "No. RM",
            "Nama Pasien",
            "Tgl. Periksa",
            "Jenis Pasien",
            "Poliklinik",
            "Nama Dokter",
            "No.Reg",
            "Alamatnya",
            "Status Booking",
            "Verifikasi Data",
            "kd_poli",
            "kd_dokter",
            "kd_penjab",
            "No. Telp. Pemesan",
            "No. Rawat",
            "Status Cetak SEP",
            "Antrian Khusus"
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbBoking.setModel(tabMode);
        tbBoking.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbBoking.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbBoking.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(58);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(170);
            } else if (i == 7) {
                column.setPreferredWidth(210);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(360);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
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
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(110);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setPreferredWidth(90);
            }
        }
        tbBoking.setDefaultRenderer(Object.class, new WarnaTable());
        
        //tabel 10 riwayat kunjungan terakhir
        tabMode5=new DefaultTableModel(null,new String[]{"No.","No. RM","Tgl. Kunjungan","Jns. Rawat",
                "Poliklinik","Cara Bayar","No. SEP","No. Rujukan","No. Surat Kontrol/SPRI","Urutan SEP","kdpj","jk",
                "norawat","nokartu","nmpasien","tgllahir","diagnosa"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbRiwayat.setModel(tabMode5);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 17; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(150);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(150);
            } else if (i == 9) {
                column.setPreferredWidth(75);
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
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 1
        tabMode1 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes1.setModel(tabMode1);
        tbFaskes1.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes1.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes1.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 2
        tabMode2 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes2.setModel(tabMode2);
        tbFaskes2.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes2.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes2.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 1 banyak
        tabMode3 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes3.setModel(tabMode3);
        tbFaskes3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes3.setDefaultRenderer(Object.class, new WarnaTable());

        //tabel riwayat rujukan faskes 2 banyak
        tabMode4 = new DefaultTableModel(null, new String[]{"No.", "No. Rujukan", "Tgl. Rujukan",
            "kode_ppk", "Nama PPK Rujukan", "Poli Rujukan", "kode_icd", "Diagnosa", "Keluhan", "kode_poli",
            "kode_ply", "Jns. Pelayn."}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbFaskes4.setModel(tabMode4);
        tbFaskes4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaskes4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 12; i++) {
            TableColumn column = tbFaskes4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(125);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {//sembunyi
                //column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            }
        }
        tbFaskes4.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRM.setDocument(new batasInput((byte) 6).getOnlyAngka(TNoRM));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        KdDokter.setDocument(new batasInput((byte) 3).getKata(KdDokter));
        no_telp.setDocument(new batasInput((byte) 15).getOnlyAngka(no_telp));
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

        ChkInput.setSelected(false);
        isForm();

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dokter.getTable().getSelectedRow() != -1) {
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    isNomer();
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

        suku.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (suku.getTable().getSelectedRow() != -1) {
                        kdsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 0).toString());
                        nmsuku.setText(suku.getTable().getValueAt(suku.getTable().getSelectedRow(), 1).toString());
                        BtnBahasa.requestFocus();
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

        suku.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        suku.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        bahasa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (bahasa.getTable().getSelectedRow() != -1) {
                        kdbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 0).toString());
                        nmbahasa.setText(bahasa.getTable().getValueAt(bahasa.getTable().getSelectedRow(), 1).toString());
                        BtnSimpan.requestFocus();
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

        bahasa.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        bahasa.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        provinsi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (provinsi.getTable().getSelectedRow() != -1) {
                    KdProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(), 1).toString());
                    NmProv.setText(provinsi.getTable().getValueAt(provinsi.getTable().getSelectedRow(), 2).toString());
                    btnProv.requestFocus();
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

        provinsi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    provinsi.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kabupaten.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kabupaten.getTable().getSelectedRow() != -1) {
                    KdKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(), 1).toString());
                    NmKab.setText(kabupaten.getTable().getValueAt(kabupaten.getTable().getSelectedRow(), 2).toString());
                    btnKab.requestFocus();
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

        kabupaten.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kabupaten.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        kecamatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (kecamatan.getTable().getSelectedRow() != -1) {
                    KdKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(), 1).toString());
                    NmKec.setText(kecamatan.getTable().getValueAt(kecamatan.getTable().getSelectedRow(), 2).toString());
                    btnKec.requestFocus();
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

        kecamatan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    kecamatan.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dpjp.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (dpjp.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        Kddpjp.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString());
                        NmDPJP.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 2).toString());
                        KddpjpLayan.setText(Kddpjp.getText());
                        nmdpjpLayan.setText(NmDPJP.getText());
                        btnDPJP.requestFocus();
                    } else if (pilihan == 2) {
                        KddpjpLayan.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 1).toString());
                        nmdpjpLayan.setText(dpjp.getTable().getValueAt(dpjp.getTable().getSelectedRow(), 2).toString());
                        btnDPJPlayan.requestFocus();
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

        dpjp.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    dpjp.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        surat_suratan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(surat_suratan.getTable().getSelectedRow()!= -1){                   
                    noSurat.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 9).toString());                    
                    Kddpjp.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 11).toString());
                    NmDPJP.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 12).toString());
                    KddpjpLayan.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 11).toString());
                    nmdpjpLayan.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 12).toString());
                    KdPenyakit.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 15).toString());
                    NmPenyakit.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 7).toString());
                    KdPoli1.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 13).toString());
                    NmPoli1.setText(surat_suratan.getTable().getValueAt(surat_suratan.getTable().getSelectedRow(), 14).toString());
                    btnNoSurat.requestFocus();
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
        
        surat_suratan.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    surat_suratan.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });

        poliBPJS.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (poliBPJS.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
//                        KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
//                        NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
//                        KdPoli.requestFocus();

                        KdPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 1).toString());
                        NmPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 2).toString());
                        KdPoli1.requestFocus();
                    } else if (pilihan == 2) {
                        KdPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 1).toString());
                        NmPoli1.setText(poliBPJS.getTable().getValueAt(poliBPJS.getTable().getSelectedRow(), 2).toString());
                        KdPoli1.requestFocus();
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

        poliBPJS.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    poliBPJS.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        KdPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        rujukanSEP.setText(NmPpkRujukan.getText()); 
                        kode_rujukanya.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString());
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

        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (penyakit.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit.requestFocus();
                    } else if (pilihan == 2) {
                        KdPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 1).toString());
                        NmPenyakit.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(), 2).toString());
                        KdPenyakit.requestFocus();

//                        KdPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
//                        NmPenyakit1.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());
//                        KdPenyakit1.requestFocus();
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

        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    penyakit.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (faskes.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan.requestFocus();
                    } else if (pilihan == 2) {
                        KdPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                        NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                        KdPpkRujukan.requestFocus();

//                        KdPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),1).toString());
//                        NmPpkRujukan1.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(),2).toString());
//                        KdPpkRujukan1.requestFocus();
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

        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    faskes.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                if (poli.getTable().getSelectedRow() != -1) {
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());

                    KdPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                    NmPoli1.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                    isNomer();
                    KdDokter.setText("");
                    NmDokter.setText("");
                    TanggalPeriksa.requestFocus();
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

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                    jk.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 4).toString());                    
                    kdsuku.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 27).toString());
                    kdbahasa.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 28).toString());                    
                    Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());
                    Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                    Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());                    
                }
                BtnPasien.requestFocus();
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
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                        verif_data.requestFocus();

                        if (akses.getkode().equals("Admin Utama") || (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03")))) {
                            FormInput.setEnabledAt(1, true);
                            FormInput.setEnabledAt(2, true);
                            FormInput.setEnabledAt(3, true);
                            tampilCekFinger();
                        } else if ((!kdpnj.getText().equals("B01") || (!kdpnj.getText().equals("A03")))) {
                            FormInput.setEnabledAt(1, false);
                            FormInput.setEnabledAt(2, false);
                            FormInput.setEnabledAt(3, false);
                            FormInput.setSelectedIndex(0);
                            emptKelengkapanSEP();
                            
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

        pasien.penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgBookingRegistrasi")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.penjab.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URUTNOREG = prop.getProperty("URUTNOREG");
        } catch (Exception e) {
            URUTNOREG = "";
            System.out.println("SEP XML : " + e);
        }
        
        try {
            if (akses.getadmin() == true) {
                user = "AdminUtama";
            } else {
                user = Sequel.cariIsi("select nama from pegawai where nik='" + akses.getkode() + "'").replace(" ", "").substring(0, 10);
//                user = akses.getnamauser().replace(" ", "").substring(0, 10);
            }
        } catch (Exception e) {
            if (akses.getadmin() == true) {
                user = "AdminUtama";
            } else {
                user = Sequel.cariIsi("select nama from pegawai where nik='" + akses.getkode() + "'");
//                user = akses.getnamauser();
            }
        }

        cekLAYAN();
        try {
            link = koneksiDB.HOSTport();
        } catch (Exception e) {
            System.out.println("E : " + e);
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
        Popup = new javax.swing.JPopupMenu();
        MnCetakKodeBarkode = new javax.swing.JMenuItem();
        MnCetakKodeQR = new javax.swing.JMenuItem();
        MnTidakjadibatal = new javax.swing.JMenuItem();
        MnSEP = new javax.swing.JMenuItem();
        MnVerifikasi = new javax.swing.JMenuItem();
        MnSttsCetakSEP = new javax.swing.JMenu();
        Mnsudah = new javax.swing.JMenuItem();
        Mnbelum = new javax.swing.JMenuItem();
        Mngagal = new javax.swing.JMenuItem();
        Popup1 = new javax.swing.JPopupMenu();
        ppRencanaKontrollagi1 = new javax.swing.JMenuItem();
        TanggalBooking = new widget.Tanggal();
        WindowCariNoRujuk = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        TabRujukan = new javax.swing.JTabbedPane();
        internalFrame6 = new widget.InternalFrame();
        scrollPane3 = new widget.ScrollPane();
        tbFaskes3 = new widget.Table();
        internalFrame9 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        tbFaskes4 = new widget.Table();
        internalFrame4 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbFaskes1 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        scrollPane2 = new widget.ScrollPane();
        tbFaskes2 = new widget.Table();
        panelisi3 = new widget.panelisi();
        BtnKeluar1 = new widget.Button();
        kode_rujukanya = new widget.TextBox();
        nmfaskes_keluar = new widget.TextBox();
        LokasiLaka = new widget.TextBox();
        jk = new widget.TextBox();
        rmMati = new widget.TextBox();
        jamMati = new widget.TextBox();
        tglMati = new widget.TextBox();
        cekKDboking = new widget.TextBox();
        statusboking = new widget.TextBox();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        cekKelengkapanSEP = new widget.TextBox();
        txtURL = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBoking = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnJadwal = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R2 = new widget.RadioButton();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        R3 = new widget.RadioButton();
        DTPCari3 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.TabPane();
        internalFrame2 = new widget.InternalFrame();
        panelisi1 = new widget.panelisi();
        jLabel4 = new widget.Label();
        TNoRM = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        TPasien = new widget.TextBox();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalPeriksa = new widget.Tanggal();
        NoReg = new widget.TextBox();
        jLabel18 = new widget.Label();
        BtnPasien = new widget.Button();
        jLabel19 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        kdboking = new widget.TextBox();
        jLabel21 = new widget.Label();
        jLabel23 = new widget.Label();
        no_telp = new widget.TextBox();
        jLabel29 = new widget.Label();
        norawat = new widget.TextBox();
        jLabel32 = new widget.Label();
        nmsuku = new widget.TextBox();
        BtnSuku = new widget.Button();
        jLabel40 = new widget.Label();
        nmbahasa = new widget.TextBox();
        BtnBahasa = new widget.Button();
        jLabel33 = new widget.Label();
        verif_data = new widget.ComboBox();
        ChkNoTelp = new widget.CekBox();
        jLabel35 = new widget.Label();
        label_hari = new widget.TextArea();
        jLabel38 = new widget.Label();
        cmbAntrianKhusus = new widget.ComboBox();
        PanelContent = new widget.panelisi();
        internalFrame3 = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel24 = new widget.Label();
        JenisPeserta = new widget.TextBox();
        jLabel20 = new widget.Label();
        JK = new widget.TextBox();
        jLabel5 = new widget.Label();
        NoKartu = new widget.TextBox();
        jLabel26 = new widget.Label();
        Status = new widget.TextBox();
        jLabel27 = new widget.Label();
        NoRujukan = new widget.TextBox();
        btnNoRujukan = new widget.Button();
        jLabel28 = new widget.Label();
        TanggalRujuk = new widget.Tanggal();
        jLabel10 = new widget.Label();
        noSurat = new widget.TextBox();
        btnNoSurat = new widget.Button();
        jLabel30 = new widget.Label();
        AsalRujukan = new widget.ComboBox();
        rujukanSEP = new widget.Label();
        jLabel42 = new widget.Label();
        Kddpjp = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        btnDPJP = new widget.Button();
        jLabel12 = new widget.Label();
        KdPpkRujukan = new widget.TextBox();
        NmPpkRujukan = new widget.TextBox();
        btnPPKRujukan = new widget.Button();
        jLabel13 = new widget.Label();
        KdPenyakit = new widget.TextBox();
        NmPenyakit = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        LabelPoli = new widget.Label();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        btnPoli = new widget.Button();
        LabelKatarak = new widget.Label();
        KasusKatarak = new widget.ComboBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        JenisPelayanan = new widget.ComboBox();
        Eksekutif = new widget.ComboBox();
        LabelKelas = new widget.Label();
        LabelKelas1 = new widget.Label();
        hakKelas = new widget.ComboBox();
        COB = new widget.ComboBox();
        jLabel43 = new widget.Label();
        KdPPK = new widget.TextBox();
        NmPPK = new widget.TextBox();
        jLabel31 = new widget.Label();
        NoTelp = new widget.TextBox();
        label_cetak = new widget.Label();
        statusSEP = new widget.TextBox();
        jLabel46 = new widget.Label();
        KddpjpLayan = new widget.TextBox();
        nmdpjpLayan = new widget.TextBox();
        btnDPJPlayan = new widget.Button();
        jLabel41 = new widget.Label();
        tujuanKun = new widget.ComboBox();
        jLabel39 = new widget.Label();
        flagPro = new widget.ComboBox();
        jLabel44 = new widget.Label();
        asesmenPel = new widget.ComboBox();
        jLabel34 = new widget.Label();
        cmbPembiayaan = new widget.ComboBox();
        jLabel37 = new widget.Label();
        jLabel45 = new widget.Label();
        kdPenunjang = new widget.ComboBox();
        pngjawab = new widget.TextBox();
        btnNoRujukanInap = new widget.Button();
        jLabel17 = new widget.Label();
        Scroll39 = new widget.ScrollPane();
        label_pesan = new widget.TextArea();
        internalFrame7 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        LakaLantas = new widget.ComboBox();
        LabTglkll = new widget.Label();
        TanggalKejadian = new widget.Tanggal();
        LabKetkll = new widget.Label();
        Ket = new widget.TextBox();
        LabProv = new widget.Label();
        KdProv = new widget.TextBox();
        NmProv = new widget.TextBox();
        btnProv = new widget.Button();
        LabKab = new widget.Label();
        KdKab = new widget.TextBox();
        NmKab = new widget.TextBox();
        btnKab = new widget.Button();
        LabKec = new widget.Label();
        KdKec = new widget.TextBox();
        NmKec = new widget.TextBox();
        btnKec = new widget.Button();
        LabSup = new widget.Label();
        suplesi = new widget.ComboBox();
        LabNoSup = new widget.Label();
        NoSEPSuplesi = new widget.TextBox();
        internalFrame10 = new widget.InternalFrame();
        scrollInput2 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();

        Popup.setName("Popup"); // NOI18N
        Popup.setPreferredSize(new java.awt.Dimension(200, 145));

        MnCetakKodeBarkode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKodeBarkode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKodeBarkode.setText("Cetak Kode Booking (Barcode)");
        MnCetakKodeBarkode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKodeBarkode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKodeBarkode.setIconTextGap(5);
        MnCetakKodeBarkode.setName("MnCetakKodeBarkode"); // NOI18N
        MnCetakKodeBarkode.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakKodeBarkode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKodeBarkodeActionPerformed(evt);
            }
        });
        Popup.add(MnCetakKodeBarkode);

        MnCetakKodeQR.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKodeQR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKodeQR.setText("Cetak Kode Booking (QR Code)");
        MnCetakKodeQR.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakKodeQR.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakKodeQR.setIconTextGap(5);
        MnCetakKodeQR.setName("MnCetakKodeQR"); // NOI18N
        MnCetakKodeQR.setPreferredSize(new java.awt.Dimension(200, 28));
        MnCetakKodeQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakKodeQRActionPerformed(evt);
            }
        });
        Popup.add(MnCetakKodeQR);

        MnTidakjadibatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTidakjadibatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTidakjadibatal.setText("Tidak Jadi Batal");
        MnTidakjadibatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTidakjadibatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTidakjadibatal.setIconTextGap(5);
        MnTidakjadibatal.setName("MnTidakjadibatal"); // NOI18N
        MnTidakjadibatal.setPreferredSize(new java.awt.Dimension(200, 28));
        MnTidakjadibatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTidakjadibatalActionPerformed(evt);
            }
        });
        Popup.add(MnTidakjadibatal);

        MnSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEP.setText("Bridging SEP BPJS");
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(200, 28));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        Popup.add(MnSEP);

        MnVerifikasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnVerifikasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnVerifikasi.setText("Verifikasi Kode Booking BPJS");
        MnVerifikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnVerifikasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnVerifikasi.setIconTextGap(5);
        MnVerifikasi.setName("MnVerifikasi"); // NOI18N
        MnVerifikasi.setPreferredSize(new java.awt.Dimension(200, 28));
        MnVerifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnVerifikasiActionPerformed(evt);
            }
        });
        Popup.add(MnVerifikasi);

        MnSttsCetakSEP.setBackground(new java.awt.Color(248, 253, 243));
        MnSttsCetakSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSttsCetakSEP.setText("Update Status Cetak SEP");
        MnSttsCetakSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSttsCetakSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSttsCetakSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSttsCetakSEP.setIconTextGap(5);
        MnSttsCetakSEP.setName("MnSttsCetakSEP"); // NOI18N
        MnSttsCetakSEP.setOpaque(true);
        MnSttsCetakSEP.setPreferredSize(new java.awt.Dimension(200, 28));

        Mnsudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnsudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnsudah.setText("SUDAH");
        Mnsudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnsudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnsudah.setIconTextGap(5);
        Mnsudah.setName("Mnsudah"); // NOI18N
        Mnsudah.setPreferredSize(new java.awt.Dimension(80, 28));
        Mnsudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnsudahActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mnsudah);

        Mnbelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mnbelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mnbelum.setText("BELUM");
        Mnbelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mnbelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mnbelum.setIconTextGap(5);
        Mnbelum.setName("Mnbelum"); // NOI18N
        Mnbelum.setPreferredSize(new java.awt.Dimension(80, 28));
        Mnbelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnbelumActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mnbelum);

        Mngagal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Mngagal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Mngagal.setText("GAGAL");
        Mngagal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Mngagal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Mngagal.setIconTextGap(5);
        Mngagal.setName("Mngagal"); // NOI18N
        Mngagal.setPreferredSize(new java.awt.Dimension(80, 28));
        Mngagal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MngagalActionPerformed(evt);
            }
        });
        MnSttsCetakSEP.add(Mngagal);

        Popup.add(MnSttsCetakSEP);

        Popup1.setName("Popup1"); // NOI18N

        ppRencanaKontrollagi1.setBackground(new java.awt.Color(242, 242, 242));
        ppRencanaKontrollagi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRencanaKontrollagi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRencanaKontrollagi1.setText("Penjadwalan Rencana Kontrol");
        ppRencanaKontrollagi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRencanaKontrollagi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRencanaKontrollagi1.setIconTextGap(8);
        ppRencanaKontrollagi1.setName("ppRencanaKontrollagi1"); // NOI18N
        ppRencanaKontrollagi1.setPreferredSize(new java.awt.Dimension(200, 25));
        ppRencanaKontrollagi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRencanaKontrollagi1ActionPerformed(evt);
            }
        });
        Popup1.add(ppRencanaKontrollagi1);

        TanggalBooking.setEditable(false);
        TanggalBooking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        TanggalBooking.setDisplayFormat("dd-MM-yyyy");
        TanggalBooking.setName("TanggalBooking"); // NOI18N
        TanggalBooking.setOpaque(false);
        TanggalBooking.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalBookingItemStateChanged(evt);
            }
        });
        TanggalBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalBookingKeyPressed(evt);
            }
        });

        WindowCariNoRujuk.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCariNoRujuk.setName("WindowCariNoRujuk"); // NOI18N
        WindowCariNoRujuk.setUndecorated(true);
        WindowCariNoRujuk.setResizable(false);
        WindowCariNoRujuk.getContentPane().setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cek Riwayat Rujukan Faskes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        TabRujukan.setToolTipText("");
        TabRujukan.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRujukan.setName("TabRujukan"); // NOI18N
        TabRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRujukanMouseClicked(evt);
            }
        });

        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setOpaque(true);

        tbFaskes3.setToolTipText("Klik data di tabel");
        tbFaskes3.setName("tbFaskes3"); // NOI18N
        tbFaskes3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes3MouseClicked(evt);
            }
        });
        tbFaskes3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes3KeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(tbFaskes3);

        internalFrame6.add(scrollPane3, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1 (banyak)", internalFrame6);

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setOpaque(true);

        tbFaskes4.setToolTipText("Klik data di tabel");
        tbFaskes4.setName("tbFaskes4"); // NOI18N
        tbFaskes4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes4MouseClicked(evt);
            }
        });
        tbFaskes4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes4KeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(tbFaskes4);

        internalFrame9.add(scrollPane4, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS) banyak", internalFrame9);

        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbFaskes1.setToolTipText("Klik data di tabel");
        tbFaskes1.setName("tbFaskes1"); // NOI18N
        tbFaskes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes1MouseClicked(evt);
            }
        });
        tbFaskes1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes1KeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbFaskes1);

        internalFrame4.add(scrollPane1, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 1", internalFrame4);

        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane2.setName("scrollPane2"); // NOI18N
        scrollPane2.setOpaque(true);

        tbFaskes2.setToolTipText("Klik data di tabel");
        tbFaskes2.setName("tbFaskes2"); // NOI18N
        tbFaskes2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaskes2MouseClicked(evt);
            }
        });
        tbFaskes2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaskes2KeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(tbFaskes2);

        internalFrame5.add(scrollPane2, java.awt.BorderLayout.CENTER);

        TabRujukan.addTab(".: Faskes 2 (RS)", internalFrame5);

        internalFrame8.add(TabRujukan, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(44, 54));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi3.add(BtnKeluar1);

        internalFrame8.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        WindowCariNoRujuk.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        kode_rujukanya.setForeground(new java.awt.Color(0, 0, 0));
        kode_rujukanya.setHighlighter(null);
        kode_rujukanya.setName("kode_rujukanya"); // NOI18N
        kode_rujukanya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kode_rujukanyaKeyPressed(evt);
            }
        });

        nmfaskes_keluar.setForeground(new java.awt.Color(0, 0, 0));
        nmfaskes_keluar.setHighlighter(null);
        nmfaskes_keluar.setName("nmfaskes_keluar"); // NOI18N
        nmfaskes_keluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmfaskes_keluarKeyPressed(evt);
            }
        });

        LokasiLaka.setForeground(new java.awt.Color(0, 0, 0));
        LokasiLaka.setHighlighter(null);
        LokasiLaka.setName("LokasiLaka"); // NOI18N

        jk.setForeground(new java.awt.Color(0, 0, 0));
        jk.setHighlighter(null);
        jk.setName("jk"); // NOI18N
        jk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jkKeyPressed(evt);
            }
        });

        rmMati.setForeground(new java.awt.Color(0, 0, 0));
        rmMati.setHighlighter(null);
        rmMati.setName("rmMati"); // NOI18N
        rmMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rmMatiKeyPressed(evt);
            }
        });

        jamMati.setForeground(new java.awt.Color(0, 0, 0));
        jamMati.setHighlighter(null);
        jamMati.setName("jamMati"); // NOI18N
        jamMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jamMatiKeyPressed(evt);
            }
        });

        tglMati.setForeground(new java.awt.Color(0, 0, 0));
        tglMati.setHighlighter(null);
        tglMati.setName("tglMati"); // NOI18N
        tglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMatiKeyPressed(evt);
            }
        });

        cekKDboking.setForeground(new java.awt.Color(0, 0, 0));
        cekKDboking.setHighlighter(null);
        cekKDboking.setName("cekKDboking"); // NOI18N
        cekKDboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKDbokingKeyPressed(evt);
            }
        });

        statusboking.setForeground(new java.awt.Color(0, 0, 0));
        statusboking.setToolTipText("");
        statusboking.setHighlighter(null);
        statusboking.setName("statusboking"); // NOI18N
        statusboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                statusbokingKeyPressed(evt);
            }
        });

        kdsuku.setEditable(false);
        kdsuku.setForeground(new java.awt.Color(0, 0, 0));
        kdsuku.setHighlighter(null);
        kdsuku.setName("kdsuku"); // NOI18N
        kdsuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdsukuKeyPressed(evt);
            }
        });

        kdbahasa.setEditable(false);
        kdbahasa.setForeground(new java.awt.Color(0, 0, 0));
        kdbahasa.setHighlighter(null);
        kdbahasa.setName("kdbahasa"); // NOI18N
        kdbahasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbahasaKeyPressed(evt);
            }
        });

        cekKelengkapanSEP.setForeground(new java.awt.Color(0, 0, 0));
        cekKelengkapanSEP.setHighlighter(null);
        cekKelengkapanSEP.setName("cekKelengkapanSEP"); // NOI18N
        cekKelengkapanSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekKelengkapanSEPKeyPressed(evt);
            }
        });

        txtURL.setEditable(false);
        txtURL.setForeground(new java.awt.Color(0, 0, 0));
        txtURL.setHighlighter(null);
        txtURL.setName("txtURL"); // NOI18N
        txtURL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtURLKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Booking Registrasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 255)));
        Scroll.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBoking.setAutoCreateRowSorter(true);
        tbBoking.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbBoking.setComponentPopupMenu(Popup);
        tbBoking.setName("tbBoking"); // NOI18N
        tbBoking.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBokingMouseClicked(evt);
            }
        });
        tbBoking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBokingKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBoking);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
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

        BtnHapus.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Batal Booking");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(120, 30));
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

        BtnJadwal.setForeground(new java.awt.Color(0, 0, 0));
        BtnJadwal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept_page.png"))); // NOI18N
        BtnJadwal.setMnemonic('J');
        BtnJadwal.setText("Jadwal Dokter");
        BtnJadwal.setToolTipText("Alt+J");
        BtnJadwal.setName("BtnJadwal"); // NOI18N
        BtnJadwal.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnJadwal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJadwalActionPerformed(evt);
            }
        });
        BtnJadwal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnJadwalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnJadwal);

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
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
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
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R2.setBackground(new java.awt.Color(240, 250, 230));
        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setSelected(true);
        R2.setText("Tanggal Booking :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(125, 23));
        panelCari.add(R2);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari1ItemStateChanged(evt);
            }
        });
        DTPCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari1KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari2KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari2);

        R3.setBackground(new java.awt.Color(240, 250, 230));
        R3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R3);
        R3.setText("Tanggal Periksa :");
        R3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R3.setName("R3"); // NOI18N
        R3.setPreferredSize(new java.awt.Dimension(135, 23));
        panelCari.add(R3);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari3ItemStateChanged(evt);
            }
        });
        DTPCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari3KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari3);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        DTPCari4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPCari4ItemStateChanged(evt);
            }
        });
        DTPCari4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPCari4KeyPressed(evt);
            }
        });
        panelCari.add(DTPCari4);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 380));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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

        FormInput.setForeground(new java.awt.Color(0, 0, 0));
        FormInput.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(400, 210));
        FormInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FormInputMouseClicked(evt);
            }
        });

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.GridLayout(1, 2));

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. RM : ");
        jLabel4.setName("jLabel4"); // NOI18N
        panelisi1.add(jLabel4);
        jLabel4.setBounds(0, 10, 73, 23);

        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setToolTipText("Harus diisi dengan angka saja..!!!");
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        panelisi1.add(TNoRM);
        TNoRM.setBounds(75, 10, 70, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelisi1.add(jLabel9);
        jLabel9.setBounds(0, 94, 73, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        panelisi1.add(NmDokter);
        NmDokter.setBounds(168, 94, 300, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        panelisi1.add(TPasien);
        TPasien.setBounds(148, 10, 320, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        panelisi1.add(KdDokter);
        KdDokter.setBounds(75, 94, 90, 23);

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
        panelisi1.add(BtnDokter);
        BtnDokter.setBounds(473, 94, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Poliklinik : ");
        jLabel11.setName("jLabel11"); // NOI18N
        panelisi1.add(jLabel11);
        jLabel11.setBounds(0, 38, 73, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        panelisi1.add(KdPoli);
        KdPoli.setBounds(75, 38, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        panelisi1.add(NmPoli);
        NmPoli.setBounds(148, 38, 320, 23);

        BtnPoli.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPoli);
        BtnPoli.setBounds(473, 38, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Rencana Tgl. Periksa :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelisi1.add(jLabel14);
        jLabel14.setBounds(0, 66, 115, 23);

        TanggalPeriksa.setEditable(false);
        TanggalPeriksa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        TanggalPeriksa.setDisplayFormat("dd-MM-yyyy");
        TanggalPeriksa.setName("TanggalPeriksa"); // NOI18N
        TanggalPeriksa.setOpaque(false);
        TanggalPeriksa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TanggalPeriksaActionPerformed(evt);
            }
        });
        panelisi1.add(TanggalPeriksa);
        TanggalPeriksa.setBounds(120, 66, 90, 23);

        NoReg.setEditable(false);
        NoReg.setForeground(new java.awt.Color(0, 0, 0));
        NoReg.setHighlighter(null);
        NoReg.setName("NoReg"); // NOI18N
        NoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRegKeyPressed(evt);
            }
        });
        panelisi1.add(NoReg);
        NoReg.setBounds(270, 66, 60, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("No.Reg :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelisi1.add(jLabel18);
        jLabel18.setBounds(215, 66, 50, 23);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('X');
        BtnPasien.setToolTipText("Alt+X");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        BtnPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPasienKeyPressed(evt);
            }
        });
        panelisi1.add(BtnPasien);
        BtnPasien.setBounds(473, 10, 28, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Jns. Pasien : ");
        jLabel19.setName("jLabel19"); // NOI18N
        panelisi1.add(jLabel19);
        jLabel19.setBounds(0, 123, 73, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        panelisi1.add(kdpnj);
        kdpnj.setBounds(75, 123, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        panelisi1.add(nmpnj);
        nmpnj.setBounds(148, 123, 320, 23);

        btnPenjab.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab.setMnemonic('2');
        btnPenjab.setToolTipText("ALt+2");
        btnPenjab.setName("btnPenjab"); // NOI18N
        btnPenjab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjabActionPerformed(evt);
            }
        });
        btnPenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPenjabKeyPressed(evt);
            }
        });
        panelisi1.add(btnPenjab);
        btnPenjab.setBounds(473, 123, 28, 23);

        kdboking.setEditable(false);
        kdboking.setForeground(new java.awt.Color(0, 0, 0));
        kdboking.setName("kdboking"); // NOI18N
        kdboking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdbokingKeyPressed(evt);
            }
        });
        panelisi1.add(kdboking);
        kdboking.setBounds(607, 10, 130, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("No. Telpn. Pemesan : ");
        jLabel21.setName("jLabel21"); // NOI18N
        panelisi1.add(jLabel21);
        jLabel21.setBounds(0, 181, 115, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Kode Booking : ");
        jLabel23.setName("jLabel23"); // NOI18N
        panelisi1.add(jLabel23);
        jLabel23.setBounds(520, 10, 85, 23);

        no_telp.setForeground(new java.awt.Color(0, 0, 0));
        no_telp.setToolTipText("Harus diisi dengan angka saja..!!!");
        no_telp.setName("no_telp"); // NOI18N
        no_telp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                no_telpKeyPressed(evt);
            }
        });
        panelisi1.add(no_telp);
        no_telp.setBounds(118, 181, 128, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("No. Rawat : ");
        jLabel29.setName("jLabel29"); // NOI18N
        panelisi1.add(jLabel29);
        jLabel29.setBounds(520, 38, 85, 23);

        norawat.setEditable(false);
        norawat.setForeground(new java.awt.Color(0, 0, 0));
        norawat.setName("norawat"); // NOI18N
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        panelisi1.add(norawat);
        norawat.setBounds(607, 38, 130, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Suku/Bangsa : ");
        jLabel32.setName("jLabel32"); // NOI18N
        panelisi1.add(jLabel32);
        jLabel32.setBounds(260, 152, 80, 23);

        nmsuku.setEditable(false);
        nmsuku.setForeground(new java.awt.Color(0, 0, 0));
        nmsuku.setName("nmsuku"); // NOI18N
        panelisi1.add(nmsuku);
        nmsuku.setBounds(340, 152, 128, 23);

        BtnSuku.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSuku.setMnemonic('1');
        BtnSuku.setToolTipText("ALt+1");
        BtnSuku.setName("BtnSuku"); // NOI18N
        BtnSuku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSukuActionPerformed(evt);
            }
        });
        BtnSuku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSukuKeyPressed(evt);
            }
        });
        panelisi1.add(BtnSuku);
        BtnSuku.setBounds(473, 152, 28, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Bahasa Dipakai : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelisi1.add(jLabel40);
        jLabel40.setBounds(250, 181, 90, 23);

        nmbahasa.setEditable(false);
        nmbahasa.setForeground(new java.awt.Color(0, 0, 0));
        nmbahasa.setName("nmbahasa"); // NOI18N
        panelisi1.add(nmbahasa);
        nmbahasa.setBounds(340, 181, 128, 23);

        BtnBahasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBahasa.setMnemonic('1');
        BtnBahasa.setToolTipText("ALt+1");
        BtnBahasa.setName("BtnBahasa"); // NOI18N
        BtnBahasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBahasaActionPerformed(evt);
            }
        });
        BtnBahasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBahasaKeyPressed(evt);
            }
        });
        panelisi1.add(BtnBahasa);
        BtnBahasa.setBounds(473, 181, 28, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Verifikasi Data : ");
        jLabel33.setName("jLabel33"); // NOI18N
        panelisi1.add(jLabel33);
        jLabel33.setBounds(0, 152, 115, 23);

        verif_data.setForeground(new java.awt.Color(0, 0, 0));
        verif_data.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "WhatsApp Messenger", "Telegram", "Mobile JKN" }));
        verif_data.setName("verif_data"); // NOI18N
        verif_data.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                verif_dataKeyPressed(evt);
            }
        });
        panelisi1.add(verif_data);
        verif_data.setBounds(120, 152, 140, 23);

        ChkNoTelp.setBackground(new java.awt.Color(255, 255, 250));
        ChkNoTelp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNoTelp.setForeground(new java.awt.Color(0, 0, 0));
        ChkNoTelp.setText("Gunakan No. Telp/HP Pasien sebagai No. Telpn. Pemesan (Jika opsi ini diconteng)");
        ChkNoTelp.setBorderPainted(true);
        ChkNoTelp.setBorderPaintedFlat(true);
        ChkNoTelp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNoTelp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNoTelp.setName("ChkNoTelp"); // NOI18N
        ChkNoTelp.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkNoTelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNoTelpActionPerformed(evt);
            }
        });
        panelisi1.add(ChkNoTelp);
        ChkNoTelp.setBounds(118, 210, 490, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Ket. Hari : ");
        jLabel35.setName("jLabel35"); // NOI18N
        panelisi1.add(jLabel35);
        jLabel35.setBounds(520, 66, 85, 23);

        label_hari.setEditable(false);
        label_hari.setColumns(20);
        label_hari.setRows(5);
        label_hari.setText("-");
        label_hari.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label_hari.setName("label_hari"); // NOI18N
        panelisi1.add(label_hari);
        label_hari.setBounds(607, 64, 350, 85);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Antrian Khusus : ");
        jLabel38.setName("jLabel38"); // NOI18N
        panelisi1.add(jLabel38);
        jLabel38.setBounds(0, 238, 115, 23);

        cmbAntrianKhusus.setForeground(new java.awt.Color(0, 0, 0));
        cmbAntrianKhusus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "YA", "TIDAK" }));
        cmbAntrianKhusus.setSelectedIndex(2);
        cmbAntrianKhusus.setName("cmbAntrianKhusus"); // NOI18N
        panelisi1.add(cmbAntrianKhusus);
        cmbAntrianKhusus.setBounds(118, 238, 70, 23);

        internalFrame2.add(panelisi1);

        PanelContent.setName("PanelContent"); // NOI18N
        PanelContent.setPreferredSize(new java.awt.Dimension(55, 55));
        PanelContent.setLayout(new java.awt.BorderLayout());
        internalFrame2.add(PanelContent);

        FormInput.addTab(".: Data Booking", internalFrame2);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelisi2.add(jLabel8);
        jLabel8.setBounds(0, 8, 95, 23);

        TglLahir.setEditable(false);
        TglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        panelisi2.add(TglLahir);
        TglLahir.setBounds(100, 8, 100, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Peserta :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel24);
        jLabel24.setBounds(200, 8, 50, 23);

        JenisPeserta.setEditable(false);
        JenisPeserta.setBackground(new java.awt.Color(245, 250, 240));
        JenisPeserta.setForeground(new java.awt.Color(0, 0, 0));
        JenisPeserta.setName("JenisPeserta"); // NOI18N
        panelisi2.add(JenisPeserta);
        JenisPeserta.setBounds(255, 8, 170, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jns. Kelamin :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelisi2.add(jLabel20);
        jLabel20.setBounds(426, 8, 80, 23);

        JK.setEditable(false);
        JK.setBackground(new java.awt.Color(245, 250, 240));
        JK.setForeground(new java.awt.Color(0, 0, 0));
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        panelisi2.add(JK);
        JK.setBounds(510, 8, 100, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Kartu :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelisi2.add(jLabel5);
        jLabel5.setBounds(0, 37, 95, 23);

        NoKartu.setEditable(false);
        NoKartu.setBackground(new java.awt.Color(245, 250, 240));
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        panelisi2.add(NoKartu);
        NoKartu.setBounds(100, 37, 152, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Status :");
        jLabel26.setName("jLabel26"); // NOI18N
        jLabel26.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel26);
        jLabel26.setBounds(252, 37, 45, 23);

        Status.setEditable(false);
        Status.setBackground(new java.awt.Color(245, 250, 240));
        Status.setForeground(new java.awt.Color(0, 0, 0));
        Status.setHighlighter(null);
        Status.setName("Status"); // NOI18N
        panelisi2.add(Status);
        Status.setBounds(302, 37, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("No. Rujukan :");
        jLabel27.setName("jLabel27"); // NOI18N
        jLabel27.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel27);
        jLabel27.setBounds(426, 37, 80, 23);

        NoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NoRujukan.setHighlighter(null);
        NoRujukan.setName("NoRujukan"); // NOI18N
        NoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(NoRujukan);
        NoRujukan.setBounds(510, 37, 208, 23);

        btnNoRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnNoRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoRujukan.setMnemonic('X');
        btnNoRujukan.setToolTipText("Alt+X");
        btnNoRujukan.setName("btnNoRujukan"); // NOI18N
        btnNoRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoRujukanActionPerformed(evt);
            }
        });
        btnNoRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnNoRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(btnNoRujukan);
        btnNoRujukan.setBounds(720, 37, 28, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tgl. Rujuk :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel28);
        jLabel28.setBounds(0, 67, 95, 23);

        TanggalRujuk.setEditable(false);
        TanggalRujuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        TanggalRujuk.setDisplayFormat("dd-MM-yyyy");
        TanggalRujuk.setName("TanggalRujuk"); // NOI18N
        TanggalRujuk.setOpaque(false);
        TanggalRujuk.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalRujuk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalRujukMouseClicked(evt);
            }
        });
        TanggalRujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalRujukKeyPressed(evt);
            }
        });
        panelisi2.add(TanggalRujuk);
        TanggalRujuk.setBounds(100, 67, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Surat Kontrol :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelisi2.add(jLabel10);
        jLabel10.setBounds(375, 67, 110, 23);

        noSurat.setForeground(new java.awt.Color(0, 0, 0));
        noSurat.setName("noSurat"); // NOI18N
        noSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSuratKeyPressed(evt);
            }
        });
        panelisi2.add(noSurat);
        noSurat.setBounds(490, 67, 228, 23);

        btnNoSurat.setForeground(new java.awt.Color(0, 0, 0));
        btnNoSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnNoSurat.setMnemonic('X');
        btnNoSurat.setToolTipText("Alt+X");
        btnNoSurat.setName("btnNoSurat"); // NOI18N
        btnNoSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoSuratActionPerformed(evt);
            }
        });
        panelisi2.add(btnNoSurat);
        btnNoSurat.setBounds(720, 67, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Asal Rujukan :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelisi2.add(jLabel30);
        jLabel30.setBounds(0, 96, 95, 23);

        AsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        AsalRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Faskes 1", "2. Faskes 2(RS)" }));
        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AsalRujukanMouseClicked(evt);
            }
        });
        AsalRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AsalRujukanActionPerformed(evt);
            }
        });
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(AsalRujukan);
        AsalRujukan.setBounds(100, 96, 120, 23);

        rujukanSEP.setForeground(new java.awt.Color(0, 0, 0));
        rujukanSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rujukanSEP.setText("- belum terisi -");
        rujukanSEP.setName("rujukanSEP"); // NOI18N
        panelisi2.add(rujukanSEP);
        rujukanSEP.setBounds(230, 96, 170, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("DPJP :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelisi2.add(jLabel42);
        jLabel42.setBounds(400, 96, 40, 23);

        Kddpjp.setEditable(false);
        Kddpjp.setBackground(new java.awt.Color(245, 250, 240));
        Kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        Kddpjp.setHighlighter(null);
        Kddpjp.setName("Kddpjp"); // NOI18N
        panelisi2.add(Kddpjp);
        Kddpjp.setBounds(445, 96, 70, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setBackground(new java.awt.Color(245, 250, 240));
        NmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        NmDPJP.setHighlighter(null);
        NmDPJP.setName("NmDPJP"); // NOI18N
        panelisi2.add(NmDPJP);
        NmDPJP.setBounds(518, 96, 200, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setMnemonic('X');
        btnDPJP.setToolTipText("Alt+X");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        btnDPJP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDPJPKeyPressed(evt);
            }
        });
        panelisi2.add(btnDPJP);
        btnDPJP.setBounds(720, 96, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("PPK Rujukan :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelisi2.add(jLabel12);
        jLabel12.setBounds(0, 125, 95, 23);

        KdPpkRujukan.setEditable(false);
        KdPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        KdPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        KdPpkRujukan.setHighlighter(null);
        KdPpkRujukan.setName("KdPpkRujukan"); // NOI18N
        panelisi2.add(KdPpkRujukan);
        KdPpkRujukan.setBounds(100, 125, 75, 23);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setHighlighter(null);
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        panelisi2.add(NmPpkRujukan);
        NmPpkRujukan.setBounds(180, 125, 180, 23);

        btnPPKRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan.setMnemonic('X');
        btnPPKRujukan.setToolTipText("Alt+X");
        btnPPKRujukan.setName("btnPPKRujukan"); // NOI18N
        btnPPKRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukanActionPerformed(evt);
            }
        });
        btnPPKRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPPKRujukanKeyPressed(evt);
            }
        });
        panelisi2.add(btnPPKRujukan);
        btnPPKRujukan.setBounds(360, 125, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Diagnosa Awal :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelisi2.add(jLabel13);
        jLabel13.setBounds(0, 153, 95, 23);

        KdPenyakit.setEditable(false);
        KdPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        KdPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        KdPenyakit.setHighlighter(null);
        KdPenyakit.setName("KdPenyakit"); // NOI18N
        panelisi2.add(KdPenyakit);
        KdPenyakit.setBounds(100, 153, 75, 23);

        NmPenyakit.setEditable(false);
        NmPenyakit.setBackground(new java.awt.Color(245, 250, 240));
        NmPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        NmPenyakit.setHighlighter(null);
        NmPenyakit.setName("NmPenyakit"); // NOI18N
        panelisi2.add(NmPenyakit);
        NmPenyakit.setBounds(180, 153, 180, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('X');
        btnDiagnosa.setToolTipText("Alt+X");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        btnDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnDiagnosaKeyPressed(evt);
            }
        });
        panelisi2.add(btnDiagnosa);
        btnDiagnosa.setBounds(360, 153, 28, 23);

        LabelPoli.setForeground(new java.awt.Color(0, 0, 0));
        LabelPoli.setText("Poli Tujuan :");
        LabelPoli.setName("LabelPoli"); // NOI18N
        panelisi2.add(LabelPoli);
        LabelPoli.setBounds(0, 181, 95, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setBackground(new java.awt.Color(245, 250, 240));
        KdPoli1.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N
        panelisi2.add(KdPoli1);
        KdPoli1.setBounds(100, 181, 75, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setBackground(new java.awt.Color(245, 250, 240));
        NmPoli1.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli1.setHighlighter(null);
        NmPoli1.setName("NmPoli1"); // NOI18N
        panelisi2.add(NmPoli1);
        NmPoli1.setBounds(180, 181, 180, 23);

        btnPoli.setForeground(new java.awt.Color(0, 0, 0));
        btnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPoli.setMnemonic('X');
        btnPoli.setToolTipText("Alt+X");
        btnPoli.setName("btnPoli"); // NOI18N
        btnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPoliActionPerformed(evt);
            }
        });
        btnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPoliKeyPressed(evt);
            }
        });
        panelisi2.add(btnPoli);
        btnPoli.setBounds(360, 181, 28, 23);

        LabelKatarak.setForeground(new java.awt.Color(0, 0, 0));
        LabelKatarak.setText("Kasus Katarak :");
        LabelKatarak.setName("LabelKatarak"); // NOI18N
        panelisi2.add(LabelKatarak);
        LabelKatarak.setBounds(565, 181, 90, 23);

        KasusKatarak.setForeground(new java.awt.Color(0, 0, 0));
        KasusKatarak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        KasusKatarak.setName("KasusKatarak"); // NOI18N
        KasusKatarak.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KasusKatarakMouseClicked(evt);
            }
        });
        KasusKatarak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KasusKatarakKeyPressed(evt);
            }
        });
        panelisi2.add(KasusKatarak);
        KasusKatarak.setBounds(660, 181, 75, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jns. Pelayanan :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelisi2.add(jLabel15);
        jLabel15.setBounds(400, 153, 85, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Eksekutif :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelisi2.add(jLabel16);
        jLabel16.setBounds(400, 181, 85, 23);

        JenisPelayanan.setForeground(new java.awt.Color(0, 0, 0));
        JenisPelayanan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Ranap", "2. Ralan" }));
        JenisPelayanan.setSelectedIndex(1);
        JenisPelayanan.setEnabled(false);
        JenisPelayanan.setName("JenisPelayanan"); // NOI18N
        JenisPelayanan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JenisPelayananItemStateChanged(evt);
            }
        });
        JenisPelayanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JenisPelayananMouseClicked(evt);
            }
        });
        JenisPelayanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JenisPelayananKeyPressed(evt);
            }
        });
        panelisi2.add(JenisPelayanan);
        JenisPelayanan.setBounds(490, 153, 75, 23);

        Eksekutif.setForeground(new java.awt.Color(0, 0, 0));
        Eksekutif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1.Ya" }));
        Eksekutif.setName("Eksekutif"); // NOI18N
        Eksekutif.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                EksekutifItemStateChanged(evt);
            }
        });
        Eksekutif.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EksekutifMouseClicked(evt);
            }
        });
        Eksekutif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EksekutifKeyPressed(evt);
            }
        });
        panelisi2.add(Eksekutif);
        Eksekutif.setBounds(490, 181, 75, 23);

        LabelKelas.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas.setText("Hak Kls. Rawat :");
        LabelKelas.setName("LabelKelas"); // NOI18N
        panelisi2.add(LabelKelas);
        LabelKelas.setBounds(400, 209, 85, 23);

        LabelKelas1.setForeground(new java.awt.Color(0, 0, 0));
        LabelKelas1.setText("COB :");
        LabelKelas1.setName("LabelKelas1"); // NOI18N
        panelisi2.add(LabelKelas1);
        LabelKelas1.setBounds(565, 153, 90, 23);

        hakKelas.setForeground(new java.awt.Color(0, 0, 0));
        hakKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Kelas 1", "2. Kelas 2", "3. Kelas 3" }));
        hakKelas.setName("hakKelas"); // NOI18N
        hakKelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hakKelasMouseClicked(evt);
            }
        });
        hakKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                hakKelasKeyPressed(evt);
            }
        });
        panelisi2.add(hakKelas);
        hakKelas.setBounds(490, 209, 75, 23);

        COB.setForeground(new java.awt.Color(0, 0, 0));
        COB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak ", "1.Ya" }));
        COB.setName("COB"); // NOI18N
        COB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                COBMouseClicked(evt);
            }
        });
        COB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                COBKeyPressed(evt);
            }
        });
        panelisi2.add(COB);
        COB.setBounds(660, 153, 75, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("PPK Pelayanan :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelisi2.add(jLabel43);
        jLabel43.setBounds(400, 125, 85, 23);

        KdPPK.setEditable(false);
        KdPPK.setBackground(new java.awt.Color(245, 250, 240));
        KdPPK.setForeground(new java.awt.Color(0, 0, 0));
        KdPPK.setHighlighter(null);
        KdPPK.setName("KdPPK"); // NOI18N
        panelisi2.add(KdPPK);
        KdPPK.setBounds(490, 125, 75, 23);

        NmPPK.setEditable(false);
        NmPPK.setBackground(new java.awt.Color(245, 250, 240));
        NmPPK.setForeground(new java.awt.Color(0, 0, 0));
        NmPPK.setHighlighter(null);
        NmPPK.setName("NmPPK"); // NOI18N
        panelisi2.add(NmPPK);
        NmPPK.setBounds(570, 125, 180, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("No.Telp. :");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi2.add(jLabel31);
        jLabel31.setBounds(470, 297, 65, 23);

        NoTelp.setEditable(false);
        NoTelp.setForeground(new java.awt.Color(0, 0, 0));
        NoTelp.setMaxLenth(13);
        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        panelisi2.add(NoTelp);
        NoTelp.setBounds(540, 297, 150, 23);

        label_cetak.setForeground(new java.awt.Color(0, 0, 0));
        label_cetak.setText("Status Cetak SEP : ");
        label_cetak.setName("label_cetak"); // NOI18N
        panelisi2.add(label_cetak);
        label_cetak.setBounds(558, 268, 110, 23);

        statusSEP.setEditable(false);
        statusSEP.setBackground(new java.awt.Color(245, 250, 240));
        statusSEP.setForeground(new java.awt.Color(255, 0, 0));
        statusSEP.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        statusSEP.setHighlighter(null);
        statusSEP.setName("statusSEP"); // NOI18N
        panelisi2.add(statusSEP);
        statusSEP.setBounds(670, 268, 70, 23);

        jLabel46.setForeground(new java.awt.Color(255, 0, 0));
        jLabel46.setText("DPJP Layan :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelisi2.add(jLabel46);
        jLabel46.setBounds(0, 209, 95, 23);

        KddpjpLayan.setEditable(false);
        KddpjpLayan.setBackground(new java.awt.Color(245, 250, 240));
        KddpjpLayan.setForeground(new java.awt.Color(0, 0, 0));
        KddpjpLayan.setName("KddpjpLayan"); // NOI18N
        panelisi2.add(KddpjpLayan);
        KddpjpLayan.setBounds(100, 209, 75, 23);

        nmdpjpLayan.setEditable(false);
        nmdpjpLayan.setBackground(new java.awt.Color(245, 250, 240));
        nmdpjpLayan.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjpLayan.setName("nmdpjpLayan"); // NOI18N
        panelisi2.add(nmdpjpLayan);
        nmdpjpLayan.setBounds(180, 209, 180, 23);

        btnDPJPlayan.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJPlayan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJPlayan.setMnemonic('X');
        btnDPJPlayan.setToolTipText("Alt+X");
        btnDPJPlayan.setName("btnDPJPlayan"); // NOI18N
        btnDPJPlayan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPlayanActionPerformed(evt);
            }
        });
        panelisi2.add(btnDPJPlayan);
        btnDPJPlayan.setBounds(360, 209, 28, 23);

        jLabel41.setForeground(new java.awt.Color(255, 0, 0));
        jLabel41.setText("Tujuan Kunj. :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelisi2.add(jLabel41);
        jLabel41.setBounds(0, 238, 95, 23);

        tujuanKun.setForeground(new java.awt.Color(0, 0, 0));
        tujuanKun.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Normal", "1. Prosedur", "2. Konsul Dokter" }));
        tujuanKun.setName("tujuanKun"); // NOI18N
        panelisi2.add(tujuanKun);
        tujuanKun.setBounds(100, 238, 110, 23);

        jLabel39.setForeground(new java.awt.Color(255, 0, 0));
        jLabel39.setText("Flag Prosedur :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelisi2.add(jLabel39);
        jLabel39.setBounds(0, 268, 95, 23);

        flagPro.setForeground(new java.awt.Color(0, 0, 0));
        flagPro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "0. Prosedur Tidak Berkelanjutan", "1. Prosedur dan Terapi Berkelanjutan" }));
        flagPro.setName("flagPro"); // NOI18N
        panelisi2.add(flagPro);
        flagPro.setBounds(100, 268, 210, 23);

        jLabel44.setForeground(new java.awt.Color(255, 0, 0));
        jLabel44.setText("Asses. Pelyn. :");
        jLabel44.setName("jLabel44"); // NOI18N
        panelisi2.add(jLabel44);
        jLabel44.setBounds(0, 297, 95, 23);

        asesmenPel.setForeground(new java.awt.Color(0, 0, 0));
        asesmenPel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Poli spesialis tidak tersedia pada hari sebelumnya", "2. Jam Poli telah berakhir pada hari sebelumnya", "3. Dokter Spesialis yang dimaksud tidak praktek pada hari sebelumnya", "4. Atas Instruksi RS", "5. Tujuan Kontrol" }));
        asesmenPel.setName("asesmenPel"); // NOI18N
        panelisi2.add(asesmenPel);
        asesmenPel.setBounds(100, 297, 370, 23);

        jLabel34.setForeground(new java.awt.Color(255, 0, 0));
        jLabel34.setText("Pembiayaan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelisi2.add(jLabel34);
        jLabel34.setBounds(210, 238, 80, 23);

        cmbPembiayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPembiayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Pribadi", "2. Pemberi Kerja", "3. Asuransi Kesehatan Tambahan" }));
        cmbPembiayaan.setName("cmbPembiayaan"); // NOI18N
        cmbPembiayaan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPembiayaanItemStateChanged(evt);
            }
        });
        cmbPembiayaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbPembiayaanKeyReleased(evt);
            }
        });
        panelisi2.add(cmbPembiayaan);
        cmbPembiayaan.setBounds(295, 238, 190, 23);

        jLabel37.setForeground(new java.awt.Color(255, 0, 0));
        jLabel37.setText("Pngg. Jawab :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelisi2.add(jLabel37);
        jLabel37.setBounds(485, 238, 80, 23);

        jLabel45.setForeground(new java.awt.Color(255, 0, 0));
        jLabel45.setText("Kode Penunjang :");
        jLabel45.setName("jLabel45"); // NOI18N
        panelisi2.add(jLabel45);
        jLabel45.setBounds(310, 268, 95, 23);

        kdPenunjang.setForeground(new java.awt.Color(0, 0, 0));
        kdPenunjang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1. Radioterapi", "2. Kemoterapi", "3. Rehabilitasi Medik", "4. Rehabilitasi Psikososial", "5. Transfusi Darah", "6. Pelayanan Gigi", "7. Laboratorium", "8. USG", "9. Farmasi", "10. Lain-Lain", "11. MRI", "12. HEMODIALISA" }));
        kdPenunjang.setName("kdPenunjang"); // NOI18N
        panelisi2.add(kdPenunjang);
        kdPenunjang.setBounds(410, 268, 150, 23);

        pngjawab.setEditable(false);
        pngjawab.setForeground(new java.awt.Color(0, 0, 0));
        pngjawab.setName("pngjawab"); // NOI18N
        panelisi2.add(pngjawab);
        pngjawab.setBounds(570, 238, 210, 23);

        btnNoRujukanInap.setForeground(new java.awt.Color(0, 0, 0));
        btnNoRujukanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Preview.png"))); // NOI18N
        btnNoRujukanInap.setToolTipText("Cek No. SEP Rawat Inap terakhir");
        btnNoRujukanInap.setName("btnNoRujukanInap"); // NOI18N
        btnNoRujukanInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNoRujukanInapActionPerformed(evt);
            }
        });
        panelisi2.add(btnNoRujukanInap);
        btnNoRujukanInap.setBounds(755, 37, 32, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Info SEP BPJS : ");
        jLabel17.setName("jLabel17"); // NOI18N
        panelisi2.add(jLabel17);
        jLabel17.setBounds(790, 8, 100, 23);

        Scroll39.setName("Scroll39"); // NOI18N

        label_pesan.setEditable(false);
        label_pesan.setColumns(20);
        label_pesan.setForeground(new java.awt.Color(0, 0, 255));
        label_pesan.setRows(5);
        label_pesan.setText("-");
        label_pesan.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        label_pesan.setName("label_pesan"); // NOI18N
        label_pesan.setPreferredSize(new java.awt.Dimension(230, 580));
        label_pesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_pesanMouseClicked(evt);
            }
        });
        label_pesan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                label_pesanKeyPressed(evt);
            }
        });
        Scroll39.setViewportView(label_pesan);

        panelisi2.add(Scroll39);
        Scroll39.setBounds(890, 6, 480, 310);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Kelengkapan SEP BPJS", internalFrame3);

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout());

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setLayout(null);

        jLabel36.setForeground(new java.awt.Color(255, 0, 0));
        jLabel36.setText("Laka Lantas :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelisi4.add(jLabel36);
        jLabel36.setBounds(0, 8, 90, 23);

        LakaLantas.setForeground(new java.awt.Color(0, 0, 0));
        LakaLantas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Bukan Kecelakaan lalu lintas [BKLL]", "1. KLL dan Bukan Kecelakaan Kerja [BKK]", "2. KLL dan KK", "3. KK" }));
        LakaLantas.setName("LakaLantas"); // NOI18N
        LakaLantas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LakaLantasKeyPressed(evt);
            }
        });
        panelisi4.add(LakaLantas);
        LakaLantas.setBounds(95, 8, 230, 23);

        LabTglkll.setForeground(new java.awt.Color(0, 0, 0));
        LabTglkll.setText("Tgl. Kejadian :");
        LabTglkll.setName("LabTglkll"); // NOI18N
        panelisi4.add(LabTglkll);
        LabTglkll.setBounds(0, 38, 90, 23);

        TanggalKejadian.setEditable(false);
        TanggalKejadian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-05-2024" }));
        TanggalKejadian.setDisplayFormat("dd-MM-yyyy");
        TanggalKejadian.setName("TanggalKejadian"); // NOI18N
        TanggalKejadian.setOpaque(false);
        TanggalKejadian.setPreferredSize(new java.awt.Dimension(95, 23));
        TanggalKejadian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TanggalKejadianMouseClicked(evt);
            }
        });
        TanggalKejadian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKejadianKeyPressed(evt);
            }
        });
        panelisi4.add(TanggalKejadian);
        TanggalKejadian.setBounds(95, 38, 90, 23);

        LabKetkll.setForeground(new java.awt.Color(0, 0, 0));
        LabKetkll.setText("Keterangan :");
        LabKetkll.setName("LabKetkll"); // NOI18N
        panelisi4.add(LabKetkll);
        LabKetkll.setBounds(0, 67, 90, 23);

        Ket.setForeground(new java.awt.Color(0, 0, 0));
        Ket.setHighlighter(null);
        Ket.setName("Ket"); // NOI18N
        Ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetKeyPressed(evt);
            }
        });
        panelisi4.add(Ket);
        Ket.setBounds(95, 67, 270, 23);

        LabProv.setForeground(new java.awt.Color(0, 0, 0));
        LabProv.setText("Provinsi :");
        LabProv.setName("LabProv"); // NOI18N
        panelisi4.add(LabProv);
        LabProv.setBounds(0, 96, 90, 23);

        KdProv.setEditable(false);
        KdProv.setBackground(new java.awt.Color(245, 250, 240));
        KdProv.setForeground(new java.awt.Color(0, 0, 0));
        KdProv.setHighlighter(null);
        KdProv.setName("KdProv"); // NOI18N
        panelisi4.add(KdProv);
        KdProv.setBounds(95, 96, 60, 23);

        NmProv.setEditable(false);
        NmProv.setBackground(new java.awt.Color(245, 250, 240));
        NmProv.setForeground(new java.awt.Color(0, 0, 0));
        NmProv.setHighlighter(null);
        NmProv.setName("NmProv"); // NOI18N
        panelisi4.add(NmProv);
        NmProv.setBounds(158, 96, 170, 23);

        btnProv.setForeground(new java.awt.Color(0, 0, 0));
        btnProv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnProv.setMnemonic('X');
        btnProv.setToolTipText("Alt+X");
        btnProv.setName("btnProv"); // NOI18N
        btnProv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProvActionPerformed(evt);
            }
        });
        btnProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnProvKeyPressed(evt);
            }
        });
        panelisi4.add(btnProv);
        btnProv.setBounds(330, 96, 28, 23);

        LabKab.setForeground(new java.awt.Color(0, 0, 0));
        LabKab.setText("Kabupaten :");
        LabKab.setName("LabKab"); // NOI18N
        panelisi4.add(LabKab);
        LabKab.setBounds(0, 125, 90, 23);

        KdKab.setEditable(false);
        KdKab.setBackground(new java.awt.Color(245, 250, 240));
        KdKab.setForeground(new java.awt.Color(0, 0, 0));
        KdKab.setHighlighter(null);
        KdKab.setName("KdKab"); // NOI18N
        panelisi4.add(KdKab);
        KdKab.setBounds(95, 125, 60, 23);

        NmKab.setEditable(false);
        NmKab.setBackground(new java.awt.Color(245, 250, 240));
        NmKab.setForeground(new java.awt.Color(0, 0, 0));
        NmKab.setHighlighter(null);
        NmKab.setName("NmKab"); // NOI18N
        panelisi4.add(NmKab);
        NmKab.setBounds(158, 125, 170, 23);

        btnKab.setForeground(new java.awt.Color(0, 0, 0));
        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('X');
        btnKab.setToolTipText("Alt+X");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        btnKab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKabKeyPressed(evt);
            }
        });
        panelisi4.add(btnKab);
        btnKab.setBounds(330, 125, 28, 23);

        LabKec.setForeground(new java.awt.Color(0, 0, 0));
        LabKec.setText("Kecamatan :");
        LabKec.setName("LabKec"); // NOI18N
        panelisi4.add(LabKec);
        LabKec.setBounds(0, 154, 90, 23);

        KdKec.setEditable(false);
        KdKec.setBackground(new java.awt.Color(245, 250, 240));
        KdKec.setForeground(new java.awt.Color(0, 0, 0));
        KdKec.setHighlighter(null);
        KdKec.setName("KdKec"); // NOI18N
        panelisi4.add(KdKec);
        KdKec.setBounds(95, 154, 60, 23);

        NmKec.setEditable(false);
        NmKec.setBackground(new java.awt.Color(245, 250, 240));
        NmKec.setForeground(new java.awt.Color(0, 0, 0));
        NmKec.setHighlighter(null);
        NmKec.setName("NmKec"); // NOI18N
        panelisi4.add(NmKec);
        NmKec.setBounds(158, 154, 170, 23);

        btnKec.setForeground(new java.awt.Color(0, 0, 0));
        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('X');
        btnKec.setToolTipText("Alt+X");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        btnKec.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnKecKeyPressed(evt);
            }
        });
        panelisi4.add(btnKec);
        btnKec.setBounds(330, 154, 28, 23);

        LabSup.setForeground(new java.awt.Color(0, 0, 0));
        LabSup.setText("Suplesi :");
        LabSup.setName("LabSup"); // NOI18N
        panelisi4.add(LabSup);
        LabSup.setBounds(0, 184, 90, 23);

        suplesi.setForeground(new java.awt.Color(0, 0, 0));
        suplesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tidak", "1. Ya" }));
        suplesi.setName("suplesi"); // NOI18N
        suplesi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suplesiMouseClicked(evt);
            }
        });
        suplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suplesiKeyPressed(evt);
            }
        });
        panelisi4.add(suplesi);
        suplesi.setBounds(95, 184, 70, 23);

        LabNoSup.setForeground(new java.awt.Color(0, 0, 0));
        LabNoSup.setText("No. SEP Suplesi :");
        LabNoSup.setName("LabNoSup"); // NOI18N
        panelisi4.add(LabNoSup);
        LabNoSup.setBounds(165, 184, 90, 23);

        NoSEPSuplesi.setForeground(new java.awt.Color(0, 0, 0));
        NoSEPSuplesi.setHighlighter(null);
        NoSEPSuplesi.setName("NoSEPSuplesi"); // NOI18N
        NoSEPSuplesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPSuplesiKeyPressed(evt);
            }
        });
        panelisi4.add(NoSEPSuplesi);
        NoSEPSuplesi.setBounds(260, 184, 270, 23);

        internalFrame7.add(panelisi4, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: Laka Lantas BPJS", internalFrame7);

        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout());

        scrollInput2.setName("scrollInput2"); // NOI18N
        scrollInput2.setPreferredSize(new java.awt.Dimension(102, 1200));

        tbRiwayat.setToolTipText("");
        tbRiwayat.setComponentPopupMenu(Popup1);
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        tbRiwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRiwayatMouseClicked(evt);
            }
        });
        tbRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRiwayatKeyPressed(evt);
            }
        });
        scrollInput2.setViewportView(tbRiwayat);

        internalFrame10.add(scrollInput2, java.awt.BorderLayout.CENTER);

        FormInput.addTab(".: 10 Riwayat Kunjungan Terakhir", internalFrame10);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cekPasien();
        }
}//GEN-LAST:event_TNoRMKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        cekRujukan = "";
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_poli='" + KdPoli.getText() + "' and "
                + "tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' and "
                + "no_rkm_medis=?", cekKDboking, TNoRM.getText());
        
        if (NoRujukan.getText().equals(Sequel.cariIsi("select k.no_rujukan from booking_registrasi b "
                + "inner join kelengkapan_booking_sep_bpjs k on k.kd_booking=b.kd_booking where "
                + "b.tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' and b.no_rkm_medis='" + TNoRM.getText() + "'"))) {
            cekRujukan = NoRujukan.getText();
        } else {
            cekRujukan = NoRujukan.getText();
        }

        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (NmPoli.getText().trim().equals("") || NmPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (NoReg.getText().trim().equals("")) {
            Valid.textKosong(NoReg, "No.Antri");
        } else if (kdpnj.getText().trim().equals("") || (nmpnj.getText().trim().equals(""))) {
            Valid.textKosong(kdpnj, "Jenis Pasien");
        } else if (kdboking.getText().trim().equals("")) {
            Valid.textKosong(kdboking, "Kode Booking");
        } else if (no_telp.getText().trim().equals("")) {
            Valid.textKosong(no_telp, "No. Telpon/HP Pemesan");
        } else if (verif_data.getSelectedItem().toString().equals(" ")) {
            Valid.textKosong(verif_data, "verifikasi data");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (!cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini tidak boleh terdaftar dipoli & tgl. yang sama,..!!!");
            BtnPoli.requestFocus();
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", TNoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
            ChkInput.setSelected(true);
            isForm();
            TNoRM.setText("");
            TPasien.setText("");
            jk.setText("");
            rmMati.setText("");
            TNoRM.requestFocus();

        } else if (jk.getText().equals("L") && (KdPoli.getText().equals("OBG"))) {
            JOptionPane.showMessageDialog(null, "Pasien dengan jns. kelamin laki-laki tidak boleh mendaftar ke poli " + NmPoli.getText() + ".");
            BtnPoli.requestFocus();
        } else if (!rmMati.getText().equals("")) {
            Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis=? ", jamMati, TNoRM.getText());
            Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis=? ", tglMati, TNoRM.getText());

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada tanggal " + tglMati.getText() + " jam " + jamMati.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            emptTeks();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. rencana tgl. periksanya");
        } else if (Sequel.cariInteger("SELECT COUNT(-1) from booking_registrasi b inner join kelengkapan_booking_sep_bpjs k on k.kd_booking=b.kd_booking WHERE "
                + "b.tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' and b.no_rkm_medis='" + TNoRM.getText() + "' "
                + "and b.kd_pj='B01' and k.no_rujukan='" + cekRujukan + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pasien ini sudah mendaftar dipoliklinik " + Sequel.cariIsi("select concat(p.nm_poli,' pada tgl. ',date_format(b.tanggal_periksa,'%d-%m-%Y')) "
                    + "from booking_registrasi b inner join poliklinik p on p.kd_poli=b.kd_poli WHERE b.kd_pj='B01' and "
                    + "b.tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "' and b.no_rkm_medis='" + TNoRM.getText() + "'") + " dg. No. Rujukan   \n"
                    + "yang sama, silahkan konfirmasi lagi ke pasiennya utk. penjadwalan ulang di tgl. yang berbeda\n"
                    + "untuk kunjungan kepoliklinik " + NmPoli.getText() + ".");
        } else if (cmbAntrianKhusus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Apakah pasien tersebut termasuk antrian khusus/prioritas dipoliklinik..?, silahkan pilih dulu salah satu..!!");
            cmbAntrianKhusus.requestFocus();
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {

                date1 = LocalDate.parse(Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
                date2 = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
                long days = ChronoUnit.DAYS.between(date2, date1);

                if (TglLahir.getText().trim().equals("")) {
                    Valid.textKosong(TglLahir, "data kelengkapan SEP BPJS");
                } else if (JenisPeserta.getText().trim().equals("")) {
                    Valid.textKosong(JenisPeserta, "Jenis Peserta");
                } else if (JK.getText().trim().equals("")) {
                    Valid.textKosong(JK, "Jenis Kelamin");
                } else if (KdPPK.getText().trim().equals("") || (NmPPK.getText().trim().equals(""))) {
                    Valid.textKosong(KdPPK, "Kode PPK");
                } else if (NoKartu.getText().trim().equals("")) {
                    Valid.textKosong(NoKartu, "No. Kartu");
                } else if (Status.getText().trim().equals("")) {
                    Valid.textKosong(Status, "Status Kepesertaan");
                } else if (NoRujukan.getText().trim().equals("")) {
                    Valid.textKosong(NoRujukan, "No. Rujukan");
                } else if (KdPpkRujukan.getText().trim().equals("") || (NmPpkRujukan.getText().trim().equals(""))) {
                    Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
                } else if (KdPenyakit.getText().trim().equals("") || (NmPenyakit.getText().trim().equals(""))) {
                    Valid.textKosong(KdPenyakit, "Diagnosa Pasien");
                } else if (KdPoli1.getText().trim().equals("") || (NmPoli1.getText().trim().equals(""))) {
                    Valid.textKosong(KdPoli1, "Poliklinik");
                } else if (days > 90){
                    JOptionPane.showMessageDialog(null, "Surat Rujukan ini Masa Berlaku Habis,Maksimal 3(tiga) bulan dari tanggal rujukan.Silahkan ke Faskes Perujuk Untuk Perbarui Rujukan");                    
                } else {
                    autoNomorBooking();
                    simpanBooking();
                    simpanKelengkapanSEP();
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                    emptTeks();
                    tampil();
                }
            } else if (kdpnj.getText().equals("U01")) {
                autoNomorBooking();
                simpanBooking();
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                emptTeks();
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, "Selain pasien Umum atau BPJS silakan lsg. ke loket pendaftaran, utk. saat ini belum ada kebijakan dari menejemen...");
                btnPenjab.requestFocus();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, NoReg, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih salah satu pasiennya dulu...");
            tbBoking.requestFocus();
        } else if (kdboking.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Silakan pilih salah satu pasiennya dulu...");
            tbBoking.requestFocus();
        } else {
            Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Batal'");
        }

        tampil();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            if (R2.isSelected() == true) {
                status = " booking_registrasi.tanggal_booking between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ";
            } else if (R3.isSelected() == true) {
                status = " booking_registrasi.tanggal_periksa between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
            }
            Valid.MyReport("rptBookingRegistrasi.jasper", "report", "::[ Laporan Daftar Booking Registrasi ]::",
                    "select booking_registrasi.tanggal_booking,booking_registrasi.no_rkm_medis, "
                    + "pasien.nm_pasien,booking_registrasi.tanggal_periksa,booking_registrasi.kd_dokter,"
                    + "dokter.nm_dokter,booking_registrasi.kd_poli,poliklinik.nm_poli,booking_registrasi.no_reg "
                    + "from booking_registrasi inner join pasien inner join dokter inner join poliklinik on "
                    + "booking_registrasi.no_rkm_medis=pasien.no_rkm_medis and "
                    + "booking_registrasi.kd_dokter=dokter.kd_dokter and booking_registrasi.kd_poli=poliklinik.kd_poli "
                    + "where " + status + " and booking_registrasi.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + status + " and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + status + " and poliklinik.nm_poli like '%" + TCari.getText().trim() + "%' or "
                    + status + " and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' order by booking_registrasi.tanggal_booking,dokter.nm_dokter", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
        emptTeks();
        emptKelengkapanSEP();
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
        tampil();
        emptTeks();
        emptKelengkapanSEP();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampil();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbBokingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBokingMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbBokingMouseClicked

    private void tbBokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBokingKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbBokingKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    dokter.setPoli(NmPoli.getText());
    dokter.isCek();
    dokter.setHari(Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
    dokter.tampil();
    dokter.TCari.requestFocus();
    dokter.setSize(1046, 341);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
    dokter.emptTeks();
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        BtnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TanggalPeriksa, btnPenjab);
    }
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari1KeyPressed

    }//GEN-LAST:event_DTPCari1KeyPressed

    private void DTPCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari2KeyPressed
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari2KeyPressed

    private void DTPCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari3KeyPressed

    private void DTPCari4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPCari4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPCari4KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        autoNomorBooking();
        FormInput.setEnabledAt(1, false);
        FormInput.setEnabledAt(2, false);
        FormInput.setEnabledAt(3, false);
        cekHariLibur();
    }//GEN-LAST:event_formWindowOpened

    private void DTPCari1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPCari1ItemStateChanged

    private void DTPCari3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari3ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari3ItemStateChanged

    private void DTPCari4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPCari4ItemStateChanged
        R3.setSelected(true);
    }//GEN-LAST:event_DTPCari4ItemStateChanged

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPoliActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPasien, BtnDokter);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.isCek();
        poli.setSize(1074, 662);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void NoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRegKeyPressed
        Valid.pindah(evt, TanggalPeriksa, BtnSimpan);
    }//GEN-LAST:event_NoRegKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
        pasien.fokus();
    }//GEN-LAST:event_BtnPasienActionPerformed

    private void BtnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPasienActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnPoli);
        }
    }//GEN-LAST:event_BtnPasienKeyPressed

    private void FormInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FormInputMouseClicked
        if (FormInput.getSelectedIndex() == 0) {
            BtnPasien.requestFocus();
        } else if (FormInput.getSelectedIndex() == 1) {
            cekBPJS();
            tampil();
        } else if (FormInput.getSelectedIndex() == 3) {
            if (TNoRM.getText().trim().equals("")) {
                Valid.tabelKosong(tabMode5);
            } else {
                SEPkontrol = "";
                tampilKunjungan();
            }
        }
    }//GEN-LAST:event_FormInputMouseClicked

    private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed

    }//GEN-LAST:event_kdpnjKeyPressed

    private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
        akses.setform("DlgBookingRegistrasi");
        pasien.penjab.onCari();
        pasien.penjab.isCek();
        pasien.penjab.setSize(906, 729);
        pasien.penjab.setLocationRelativeTo(internalFrame1);
        pasien.penjab.setVisible(true);
        pasien.penjab.onCari();
    }//GEN-LAST:event_btnPenjabActionPerformed

    private void kdbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbokingKeyPressed

    private void TanggalBookingItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalBookingItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalBookingItemStateChanged

    private void TanggalBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalBookingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalBookingKeyPressed

    private void TanggalPeriksaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TanggalPeriksaActionPerformed
        cekHariLibur();
    }//GEN-LAST:event_TanggalPeriksaActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (NmDokter.getText().trim().equals("") || KdDokter.getText().trim().equals("")) {
            Valid.textKosong(KdDokter, "Dokter Spesialis");
        } else if (NmPoli.getText().trim().equals("") || KdPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (kdpnj.getText().trim().equals("") || nmpnj.getText().trim().equals("")) {
            Valid.textKosong(kdpnj, "Jenis Pasien");
        } else if (no_telp.getText().trim().equals("")) {
            Valid.textKosong(no_telp, "No. Telpon/HP Pemesan");
        } else if (verif_data.getSelectedItem().toString().equals(" ")) {
            Valid.textKosong(verif_data, "verifikasi data");
        } else if (jk.getText().equals("L") && (KdPoli.getText().equals("OBG"))) {
            JOptionPane.showMessageDialog(null, "Pasien dengan jns. kelamin laki-laki tidak boleh mendaftar ke poli " + NmPoli.getText() + ".");
            BtnPoli.requestFocus();
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (!rmMati.getText().equals("")) {
            Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis=? ", jamMati, TNoRM.getText());
            Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis=? ", tglMati, TNoRM.getText());

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada tanggal " + tglMati.getText() + " jam " + jamMati.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            BtnBatal.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'") > 0) {
            JOptionPane.showMessageDialog(rootPane, "Pelayanan rawat jalan poliklinik TUTUP, karena sedang/memperingati "
                    + Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'")
                    + ", silahkan ganti hari lain utk. rencana tgl. periksanya");
        } else if (cmbAntrianKhusus.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Apakah pasien tersebut termasuk antrian khusus/prioritas dipoliklinik..?, silahkan pilih dulu salah satu..!!");
            cmbAntrianKhusus.requestFocus();
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                if (TglLahir.getText().trim().equals("")) {
                    Valid.textKosong(TglLahir, "data kelengkapan SEP BPJS");
                } else if (JenisPeserta.getText().trim().equals("")) {
                    Valid.textKosong(JenisPeserta, "Jenis Peserta");
                } else if (JK.getText().trim().equals("")) {
                    Valid.textKosong(JK, "Jenis Kelamin");
                } else if (KdPPK.getText().trim().equals("") || (NmPPK.getText().trim().equals(""))) {
                    Valid.textKosong(KdPPK, "Kode PPK");
                } else if (NoKartu.getText().trim().equals("")) {
                    Valid.textKosong(NoKartu, "No. Kartu");
                } else if (Status.getText().trim().equals("")) {
                    Valid.textKosong(Status, "Status Kepesertaan");
                } else if (NoRujukan.getText().trim().equals("")) {
                    Valid.textKosong(NoRujukan, "No. Rujukan");
                } else if (KdPpkRujukan.getText().trim().equals("") || (NmPpkRujukan.getText().trim().equals(""))) {
                    Valid.textKosong(KdPpkRujukan, "PPK Rujukan");
                } else if (KdPenyakit.getText().trim().equals("") || (NmPenyakit.getText().trim().equals(""))) {
                    Valid.textKosong(KdPenyakit, "Diagnosa Pasien");
                } else if (KdPoli1.getText().trim().equals("") || (NmPoli1.getText().trim().equals(""))) {
                    Valid.textKosong(KdPoli1, "Poliklinik");
                } else {
                    gantiDataBooking();
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                    Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where kd_booking='" + kdboking.getText() + "'", cekKelengkapanSEP);
                    if (cekKelengkapanSEP.getText().equals("")) {
                        simpanKelengkapanSEP();
                    } else if (!cekKelengkapanSEP.getText().equals("")) {
                        gantiDataSEP();
                    }
                    tampil();
                    emptTeks();
                }
            } else if (kdpnj.getText().equals("U01")) {
                gantiDataBooking();
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                Sequel.meghapus("kelengkapan_booking_sep_bpjs", "kd_booking", kdboking.getText());
                tampil();
                emptTeks();
            } else {
                JOptionPane.showMessageDialog(null, "Selain pasien Umum atau BPJS silakan lsg. ke loket pendaftaran, utk. saat ini belum ada kebijakan dari menejemen...");
                btnPenjab.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiKeyPressed

    private void no_telpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_telpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_no_telpKeyPressed

    private void BtnJadwalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJadwalActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        InformasiJadwal jadwal = new InformasiJadwal(null, false);
        jadwal.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        jadwal.setLocationRelativeTo(internalFrame1);
        jadwal.setVisible(true);
        jadwal.TCari.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnJadwalActionPerformed

    private void BtnJadwalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnJadwalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnJadwalKeyPressed

    private void btnPenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPenjabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            btnPenjabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnDokter, verif_data);
        }
    }//GEN-LAST:event_btnPenjabKeyPressed

    private void NoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRujukanKeyPressed
        Valid.pindah(evt, TCari, TanggalRujuk);
    }//GEN-LAST:event_NoRujukanKeyPressed

    private void btnNoRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanActionPerformed
        if (TNoRM.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "Pasien");
        } else if (NoKartu.getText().trim().equals("")) {
            Valid.textKosong(NoKartu, "Nomor Kartu");
        } else {
            WindowCariNoRujuk.setLocationRelativeTo(internalFrame1);
            WindowCariNoRujuk.setVisible(true);
            TabRujukan.setSelectedIndex(0);
            tampilFaskes1BYK();
        }
    }//GEN-LAST:event_btnNoRujukanActionPerformed

    private void btnNoRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnNoRujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNoRujukanKeyPressed

    private void TanggalRujukMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalRujukMouseClicked

    }//GEN-LAST:event_TanggalRujukMouseClicked

    private void TanggalRujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalRujukKeyPressed
        Valid.pindah(evt, NoRujukan, btnNoSurat);
    }//GEN-LAST:event_TanggalRujukKeyPressed

    private void noSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSuratKeyPressed

    private void btnNoSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoSuratActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            surat_suratan.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            surat_suratan.setLocationRelativeTo(internalFrame1);
            surat_suratan.emptTeks();
            surat_suratan.TCari.setText(NoKartu.getText());
            surat_suratan.TCari.requestFocus();
            surat_suratan.tampil();
            surat_suratan.ChkInput.setSelected(false);
            surat_suratan.isForm();
            surat_suratan.isCek();
            surat_suratan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());            
        }

//        skdp.setNoRm(TNoRM.getText(), TPasien.getText());
//        skdp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
//        skdp.setLocationRelativeTo(internalFrame1);
//        skdp.setVisible(true);
//        skdp.fokusdata();
    }//GEN-LAST:event_btnNoSuratActionPerformed

    private void AsalRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AsalRujukanMouseClicked
        AsalRujukan.setEditable(false);
    }//GEN-LAST:event_AsalRujukanMouseClicked

    private void AsalRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AsalRujukanActionPerformed
        if (AsalRujukan.getSelectedIndex() == 1) {
            KdPpkRujukan.setText(KdPPK.getText());
            rujukanSEP.setText(NmPPK.getText());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//            NmPpkRujukan.setText(NmPPK.getText());
//            NmPpkRujukan.setText("");
        } else if (AsalRujukan.getSelectedIndex() == 0) {
            KdPpkRujukan.setText("");
            rujukanSEP.setText("- belum terisi -");
            NmPpkRujukan.setText("");
        }
    }//GEN-LAST:event_AsalRujukanActionPerformed

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        Valid.pindah(evt, btnNoSurat, btnPPKRujukan);
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        pilihan = 1;
        dpjp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.Dokter.requestFocus();
        dpjp.poliklinik(KdPoli.getText(), NmPoli.getText());
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void btnDPJPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDPJPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDPJPKeyPressed

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        akses.setform("DlgBookingRegistrasi");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();

        //        pilihan=1;
        //        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        //        faskes.setLocationRelativeTo(internalFrame1);
        //        faskes.setVisible(true);
        //        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukanActionPerformed

    private void btnPPKRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPPKRujukanKeyPressed
        Valid.pindah(evt, AsalRujukan, btnDiagnosa);
    }//GEN-LAST:event_btnPPKRujukanKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        pilihan = 1;
        penyakit.setSize(841, 417);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.fokus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        Valid.pindah(evt, btnPPKRujukan, btnPoli);
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void btnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPoliActionPerformed
        pilihan = 1;
        poliBPJS.setSize(842, 390);
        poliBPJS.setLocationRelativeTo(internalFrame1);
        poliBPJS.setVisible(true);
        poliBPJS.Poli.requestFocus();
    }//GEN-LAST:event_btnPoliActionPerformed

    private void btnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPoliKeyPressed
//        Valid.pindah(evt,btnDiagnosa,Catatan);
    }//GEN-LAST:event_btnPoliKeyPressed

    private void KasusKatarakMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KasusKatarakMouseClicked
        KasusKatarak.setEditable(false);
    }//GEN-LAST:event_KasusKatarakMouseClicked

    private void KasusKatarakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KasusKatarakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KasusKatarakKeyPressed

    private void JenisPelayananItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JenisPelayananItemStateChanged
        cekLAYAN();
        cekPPKRUJUKAN();
    }//GEN-LAST:event_JenisPelayananItemStateChanged

    private void JenisPelayananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JenisPelayananMouseClicked
        JenisPelayanan.setEditable(false);
    }//GEN-LAST:event_JenisPelayananMouseClicked

    private void JenisPelayananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JenisPelayananKeyPressed
//        Valid.pindah(evt,LakaLantas,Kelas);
    }//GEN-LAST:event_JenisPelayananKeyPressed

    private void EksekutifItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_EksekutifItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_EksekutifItemStateChanged

    private void EksekutifMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EksekutifMouseClicked
        Eksekutif.setEditable(false);
    }//GEN-LAST:event_EksekutifMouseClicked

    private void EksekutifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EksekutifKeyPressed
        Valid.pindah(evt, hakKelas, COB);
    }//GEN-LAST:event_EksekutifKeyPressed

    private void hakKelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hakKelasMouseClicked
        hakKelas.setEditable(false);
    }//GEN-LAST:event_hakKelasMouseClicked

    private void hakKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hakKelasKeyPressed
        Valid.pindah(evt, JenisPelayanan, Eksekutif);
    }//GEN-LAST:event_hakKelasKeyPressed

    private void COBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_COBMouseClicked
        COB.setEditable(false);
    }//GEN-LAST:event_COBMouseClicked

    private void COBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_COBKeyPressed
        Valid.pindah(evt, Eksekutif, KasusKatarak);
    }//GEN-LAST:event_COBKeyPressed

    private void tbFaskes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes1MouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataFK1();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes1MouseClicked

    private void tbFaskes1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes1KeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
            LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
            LocalDate habisBerlaku = tgldirujuk.plusDays(89);
            if (NoRujukan.getText().equals(Sequel.cariIsi("SELECT no_rujukan FROM bridging_sep WHERE "
                    + "nomr='" + TNoRM.getText() + "' AND jnspelayanan='2' ORDER BY tglsep DESC LIMIT 1"))) {
                label_pesan.setText("Pastikan tanggal surat rujukan masih berlaku..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan diperkirakan "
                        + "akan berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            } else {
                label_pesan.setText("Nomor rujukan tidak sama dengan nomor rujukan kunjungan sebelumnya, mohon dicek kembali apakah ini surat rujukan baru..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan "
                        + "berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            }
        }
    }//GEN-LAST:event_tbFaskes1KeyPressed

    private void tbFaskes2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes2MouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataFK2();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes2MouseClicked

    private void tbFaskes2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes2KeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
            LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
            LocalDate habisBerlaku = tgldirujuk.plusDays(89);
            if (NoRujukan.getText().equals(Sequel.cariIsi("SELECT no_rujukan FROM bridging_sep WHERE "
                    + "nomr='" + TNoRM.getText() + "' AND jnspelayanan='2' ORDER BY tglsep DESC LIMIT 1"))) {
                label_pesan.setText("Pastikan tanggal surat rujukan masih berlaku..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan diperkirakan "
                        + "akan berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            } else {
                label_pesan.setText("Nomor rujukan tidak sama dengan nomor rujukan kunjungan sebelumnya, mohon dicek kembali apakah ini surat rujukan baru..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan "
                        + "berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            }
        }
    }//GEN-LAST:event_tbFaskes2KeyPressed

    private void tbFaskes3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes3MouseClicked
        if (tabMode3.getRowCount() != 0) {
            try {
                getDataFK1byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes3MouseClicked

    private void tbFaskes3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes3KeyPressed
        if (tabMode3.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK1byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();            
            LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
            LocalDate habisBerlaku = tgldirujuk.plusDays(89);
            if (NoRujukan.getText().equals(Sequel.cariIsi("SELECT no_rujukan FROM bridging_sep WHERE "
                    + "nomr='" + TNoRM.getText() + "' AND jnspelayanan='2' ORDER BY tglsep DESC LIMIT 1"))) {
                label_pesan.setText("Pastikan tanggal surat rujukan masih berlaku..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan diperkirakan "
                        + "akan berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            } else {
                label_pesan.setText("Nomor rujukan tidak sama dengan nomor rujukan kunjungan sebelumnya, mohon dicek kembali apakah ini surat rujukan baru..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan "
                        + "berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            }
        }
    }//GEN-LAST:event_tbFaskes3KeyPressed

    private void tbFaskes4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaskes4MouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataFK2byk();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaskes4MouseClicked

    private void tbFaskes4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaskes4KeyPressed
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataFK2byk();
                } catch (java.lang.NullPointerException e) {
                }
            }
            WindowCariNoRujuk.dispose();
            LocalDate tgldirujuk = LocalDate.parse(Valid.SetTgl(TanggalRujuk.getSelectedItem() + ""));
            LocalDate habisBerlaku = tgldirujuk.plusDays(89);
            if (NoRujukan.getText().equals(Sequel.cariIsi("SELECT no_rujukan FROM bridging_sep WHERE "
                    + "nomr='" + TNoRM.getText() + "' AND jnspelayanan='2' ORDER BY tglsep DESC LIMIT 1"))) {
                label_pesan.setText("Pastikan tanggal surat rujukan masih berlaku..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan diperkirakan "
                        + "akan berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            } else {
                label_pesan.setText("Nomor rujukan tidak sama dengan nomor rujukan kunjungan sebelumnya, mohon dicek kembali apakah ini surat rujukan baru..!!\n\n"
                        + "Surat rujukan ini diterbitkan pada tgl. " + TanggalRujuk.getSelectedItem() + " dan "
                        + "berakhir masa berlakunya pada tgl. " + Valid.SetTglINDONESIA(habisBerlaku) + "");
            }
        }
    }//GEN-LAST:event_tbFaskes4KeyPressed

    private void TabRujukanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRujukanMouseClicked
        if (TabRujukan.getSelectedIndex() == 0) {
            tampilFaskes1BYK();
        } else if (TabRujukan.getSelectedIndex() == 1) {
            tampilFaskes2BYK();
        } else if (TabRujukan.getSelectedIndex() == 2) {
            tampilFaskes1();
        } else if (TabRujukan.getSelectedIndex() == 3) {
            tampilFaskes2();
        }
    }//GEN-LAST:event_TabRujukanMouseClicked

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowCariNoRujuk.dispose();
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void kode_rujukanyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kode_rujukanyaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kode_rujukanyaKeyPressed

    private void nmfaskes_keluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmfaskes_keluarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmfaskes_keluarKeyPressed

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoTelpKeyPressed

    private void LakaLantasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LakaLantasKeyPressed
        Valid.pindah(evt, btnPoli, JenisPelayanan);
    }//GEN-LAST:event_LakaLantasKeyPressed

    private void TanggalKejadianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TanggalKejadianMouseClicked
        TanggalKejadian.setEditable(false);
    }//GEN-LAST:event_TanggalKejadianMouseClicked

    private void TanggalKejadianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKejadianKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalKejadianKeyPressed

    private void KetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetKeyPressed

    private void btnProvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProvActionPerformed
        pilihan = 1;
        provinsi.setSize(618, 338);
        provinsi.setLocationRelativeTo(internalFrame1);
        provinsi.setVisible(true);
        provinsi.fokus();
    }//GEN-LAST:event_btnProvActionPerformed

    private void btnProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnProvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnProvKeyPressed

    private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
        pilihan = 1;
        kabupaten.setSize(746, 310);
        kabupaten.setLocationRelativeTo(internalFrame1);
        kabupaten.setVisible(true);
        kabupaten.fokus(KdProv.getText(), NmProv.getText());
    }//GEN-LAST:event_btnKabActionPerformed

    private void btnKabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKabKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKabKeyPressed

    private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
        pilihan = 1;
        kecamatan.setSize(764, 376);
        kecamatan.setLocationRelativeTo(internalFrame1);
        kecamatan.setVisible(true);
        kecamatan.fokus(KdKab.getText(), NmKab.getText());
    }//GEN-LAST:event_btnKecActionPerformed

    private void btnKecKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnKecKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnKecKeyPressed

    private void suplesiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suplesiMouseClicked
        suplesi.setEditable(false);
    }//GEN-LAST:event_suplesiMouseClicked

    private void suplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_suplesiKeyPressed

    private void NoSEPSuplesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPSuplesiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSEPSuplesiKeyPressed

    private void MnCetakKodeBarkodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKodeBarkodeActionPerformed
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking=?", cekKDboking, kdboking.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            R2.requestFocus();
        } else if (TPasien.getText().trim().equals("") && (TNoRM.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbBoking.requestFocus();
        } else if (cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode booking tidak ditemukan didatabase...!!!");
            tbBoking.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.AutoPrintToImage("rptKodeBooking.jasper", "report", "::[ Kode Booking (Barcode) Pasien ]::",
                    "SELECT br.kd_booking, date_format(br.tanggal_periksa,'%d %M %Y') tgl_periksa, pl.nm_poli FROM booking_registrasi br "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli "
                    + "WHERE br.kd_booking='" + cekKDboking.getText() + "'", param, Sequel.cariFolder(), "Barcode Booking");
            this.setCursor(Cursor.getDefaultCursor());

            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnCetakKodeBarkodeActionPerformed

    private void jkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkKeyPressed

    private void rmMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rmMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_rmMatiKeyPressed

    private void jamMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jamMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jamMatiKeyPressed

    private void tglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tglMatiKeyPressed

    private void cekKDbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKDbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKDbokingKeyPressed

    private void MnCetakKodeQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakKodeQRActionPerformed
        Sequel.cariIsi("select kd_booking from booking_registrasi where kd_booking=?", cekKDboking, kdboking.getText());

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            R2.requestFocus();
        } else if (TPasien.getText().trim().equals("") && (TNoRM.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbBoking.requestFocus();
        } else if (cekKDboking.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Kode booking tidak ditemukan didatabase...!!!");
            tbBoking.requestFocus();
        } else {

            Valid.cetakQr(kdboking.getText(), Sequel.cariFolder(), "QR.jpg");
            Sequel.queryu("delete from setting_qr where judul = 'QR'");
            Sequel.menyimpanQr("setting_qr", "'QR'", "file QRCode Booking", Sequel.cariFolderPrint());

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("lokasi", Sequel.cariGambar("select gambar from setting_qr where judul = 'QR'"));
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.AutoPrintToImage("rptKodeBookingQR.jasper", "report", "::[ Kode Booking (QR Code) Pasien ]::",
                    "SELECT br.kd_booking, date_format(br.tanggal_periksa,'%d %M %Y') tgl_periksa, pl.nm_poli FROM booking_registrasi br "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli "
                    + "WHERE br.kd_booking='" + cekKDboking.getText() + "'", param, Sequel.cariFolder(), "QRCode Booking");
            this.setCursor(Cursor.getDefaultCursor());

            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnCetakKodeQRActionPerformed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void MnTidakjadibatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTidakjadibatalActionPerformed
        if (statusboking.getText().equals("Terdaftar")) {
            JOptionPane.showMessageDialog(null, "Pasien sudah terdaftar dipoliklinik, status booking tidak bisa diganti...!!!");
        } else {
            Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'", "status_booking='Menunggu' ");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnTidakjadibatalActionPerformed

    private void statusbokingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_statusbokingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusbokingKeyPressed

    private void BtnSukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSukuActionPerformed
        akses.setform("DlgBookingRegistrasi");
        suku.isCek();
        suku.setSize(1105, 512);
        suku.setLocationRelativeTo(internalFrame1);
        suku.setVisible(true);
        suku.TCari.requestFocus();
    }//GEN-LAST:event_BtnSukuActionPerformed

    private void BtnSukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSukuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSukuActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnBahasa.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBahasa.requestFocus();
        }
    }//GEN-LAST:event_BtnSukuKeyPressed

    private void BtnBahasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBahasaActionPerformed
        akses.setform("DlgBookingRegistrasi");
        bahasa.isCek();
        bahasa.setSize(1105, 512);
        bahasa.setLocationRelativeTo(internalFrame1);
        bahasa.setVisible(true);
        bahasa.TCari.requestFocus();
    }//GEN-LAST:event_BtnBahasaActionPerformed

    private void BtnBahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBahasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnBahasaActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnSuku.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_BtnBahasaKeyPressed

    private void kdsukuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdsukuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdsukuKeyPressed

    private void kdbahasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdbahasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_kdbahasaKeyPressed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            NoReg.requestFocus();
        } else if (TNoRM.getText().trim().equals("") || (TPasien.getText().trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbBoking.requestFocus();
        } else if (kdpnj.getText().equals("U01")) {
            JOptionPane.showMessageDialog(null, "Pasien umum tidak ada membuat SEP...!!!");
        } else {
            if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                if (statusSEP.getText().equals("SUDAH")) {
                    JOptionPane.showMessageDialog(null, "Pasien ini SEP nya sudah tercetak...!!!");
                    emptTeks();
                    tampil();
                    ChkInput.setSelected(true);
                    isForm();
                } else if (statusSEP.getText().equals("BELUM")) {
                    JOptionPane.showMessageDialog(null, "Pasien ini belum datang, biar aja SEP nya dicetak sendiri...!!!");
                    emptTeks();
                    tampil();
                    ChkInput.setSelected(true);
                    isForm();
                } else if (statusSEP.getText().equals("GAGAL")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgBookingRegistrasi");
                    BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
                    dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    dlgki.setLocationRelativeTo(internalFrame1);
                    dlgki.isCek();
                    dlgki.setNoRm(norawat.getText(), "2. Ralan", KdPoli1.getText(), NmPoli1.getText());
                    dlgki.Catatan.setText("Registrasi pasien dengan mendaftar online");
                    dlgki.tampil();
                    dlgki.setVisible(true);
                    dlgki.cekLAYAN();
                    dlgki.tampilNoRujukan(NoKartu.getText());
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void verif_dataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verif_dataKeyPressed
        Valid.pindah(evt, btnPenjab, no_telp);
    }//GEN-LAST:event_verif_dataKeyPressed

    private void MnsudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnsudahActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (akses.getkode().equals("Admin Utama")) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbBoking.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='SUDAH' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnsudahActionPerformed

    private void MnbelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnbelumActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (akses.getkode().equals("Admin Utama") || akses.getbooking_registrasi()) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbBoking.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='BELUM' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MnbelumActionPerformed

    private void MngagalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MngagalActionPerformed
        Sequel.cariIsi("select kd_booking from kelengkapan_booking_sep_bpjs where nomr=?", cekKDboking, TNoRM.getText());

        if (akses.getkode().equals("Admin Utama") || akses.getbooking_registrasi()) {
            if (cekKDboking.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Pilih dulu salah satu nama pasiennya dg. mengklik data pada tabel & hanya utk. pasien BPJS saja...!!!");
                tbBoking.requestFocus();
            } else if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'", "status_cetak_sep='GAGAL' ");
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan koordinasi dulu dg. UNIT SIMRS utk. memastikan datanya...!!!");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_MngagalActionPerformed

    private void cekKelengkapanSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekKelengkapanSEPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekKelengkapanSEPKeyPressed

    private void btnDPJPlayanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPlayanActionPerformed
        pilihan = 2;
        dpjp.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        dpjp.setLocationRelativeTo(internalFrame1);
        dpjp.setVisible(true);
        dpjp.Dokter.requestFocus();
        dpjp.poliklinik(KdPoli.getText(), NmPoli.getText());
        dpjp.tglLayan(TanggalPeriksa.getDate());
    }//GEN-LAST:event_btnDPJPlayanActionPerformed

    private void cmbPembiayaanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPembiayaanItemStateChanged
        cmbPembiayaanKeyReleased(null);
    }//GEN-LAST:event_cmbPembiayaanItemStateChanged

    private void cmbPembiayaanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPembiayaanKeyReleased
        if (cmbPembiayaan.getSelectedIndex() == 0) {
            pngjawab.setText("");
        } else if (cmbPembiayaan.getSelectedIndex() == 1) {
            pngjawab.setText("Pribadi");
        } else if (cmbPembiayaan.getSelectedIndex() == 2) {
            pngjawab.setText("Pemberi Kerja");
        } else if (cmbPembiayaan.getSelectedIndex() == 3) {
            pngjawab.setText("Asuransi Kesehatan Tambahan");
        }
    }//GEN-LAST:event_cmbPembiayaanKeyReleased

    private void ChkNoTelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNoTelpActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
            ChkNoTelp.setSelected(false);
            no_telp.setText("");
            TNoRM.requestFocus();
        } else {
            if (ChkNoTelp.isSelected() == true) {
                no_telp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            } else {
                no_telp.setText("");
            }
        }
    }//GEN-LAST:event_ChkNoTelpActionPerformed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabMode5.getRowCount() != 0) {
            try {
                getDataKunjungan();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabMode5.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getDataKunjungan();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void ppRencanaKontrollagi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRencanaKontrollagi1ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode5.getRowCount() < 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasiennya...!!!");
        } else if (SEPkontrol.equals("") || SEPkontrol.equals("-")) {
            JOptionPane.showMessageDialog(null, "No. SEP utk. rencana kontrol blm. dipilih, Silahkan klik salah satu data kunjungan pada tabel..!!");
            FormInput.setSelectedIndex(3);
            FormInput.setEnabled(true);
            tbRiwayat.requestFocus();            
        } else if (!tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString().equals("B01")) {
            JOptionPane.showMessageDialog(null, "Data kunjungan yg. dipilih bukan dg. cara bayar BPJS Kesehatan...!!!");
            tbRiwayat.requestFocus();
        } else {
            surat_suratan.setNoRm(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString(), SEPkontrol,
                tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString(),
                tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 14).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 15).toString(),
                tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 11).toString(), tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 16).toString(),
                Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString() + "'"));
            surat_suratan.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            surat_suratan.setLocationRelativeTo(internalFrame1);
            surat_suratan.ChkInput.setSelected(true);
            surat_suratan.TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 13).toString());
            surat_suratan.tampil();
            surat_suratan.isForm();
            surat_suratan.isCek();
            surat_suratan.setVisible(true);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_ppRencanaKontrollagi1ActionPerformed

    private void btnNoRujukanInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNoRujukanInapActionPerformed
        if (NoKartu.getText().equals("")) {
            Valid.textKosong(NoKartu, "Pasien");
        } else {
            cekDataPlg = 0;
            tglPulangInap = "";
            cekDataPlg = Sequel.cariInteger("select count(-1) from reg_periksa where no_rkm_medis='" + TNoRM.getText() + "' and status_lanjut='Ranap'");

            if (cekDataPlg > 0) {
                tglPulangInap = Sequel.cariIsi("SELECT CONCAT('tgl. ',DATE_FORMAT(ki.tgl_keluar,'%d-%m-%Y'),', Jam : ',ki.jam_keluar) FROM kamar_inap ki "
                    + "INNER JOIN reg_periksa rp ON rp.no_rawat=ki.no_rawat WHERE rp.no_rkm_medis='" + TNoRM.getText() + "' AND "
                    + "rp.status_lanjut='Ranap' AND ki.stts_pulang NOT IN ('-','pindah kamar') ORDER BY ki.tgl_keluar DESC LIMIT 1");

                x = JOptionPane.showConfirmDialog(rootPane, "Terakhir plg. rawat inap " + tglPulangInap + ". Apakah No. SEP nya    \n"
                        + "akan dipakai utk. No. Rujukan kontrol rawat jalan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    NoRujukan.setText(Sequel.cariIsi("select ifnull(no_sep,'no. sep tidak ditemukan') from bridging_sep where nomr='" + TNoRM.getText() + "' "
                        + "and jnsPelayanan='1' order by tglsep desc limit 1"));
            } else {
                NoRujukan.setText("");
            }
        } else if (cekDataPlg == 0) {
            JOptionPane.showMessageDialog(null, "Selama berobat disini, pasien ini belum pernah rawat inap...!!!!");
            NoRujukan.setText("");
        }
        }
    }//GEN-LAST:event_btnNoRujukanInapActionPerformed

    private void label_pesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_pesanMouseClicked

    }//GEN-LAST:event_label_pesanMouseClicked

    private void label_pesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label_pesanKeyPressed
        //        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_label_pesanKeyPressed

    private void txtURLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtURLKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtURLKeyPressed

    private void MnVerifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnVerifikasiActionPerformed
        if (Sequel.cariInteger("select count(-1) from booking_registrasi where kd_booking='" + kdboking.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Kode booking tidak ditemukan, silahkan klik salah satu datanya pada tabel...!!!!");
            tbBoking.requestFocus();
        } else {
            cekKodePJ = "";
            cekKodePJ = Sequel.cariIsi("select kd_pj from booking_registrasi where kd_booking='" + kdboking.getText() + "'");
            if (cekKodePJ.equals("B01") || cekKodePJ.equals("A03")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgBookingRegistrasi");
                DlgVerifikasiKodeBoking form = new DlgVerifikasiKodeBoking(null, false);
                form.emptTeksBokingBPJS();
                form.setData(kdboking.getText(), "ya");
                form.Tantrian.setText("");
                form.setSize(460, 258);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Verifikasi hanya utk. kode booking pasien BPJS...!!!!");
            }
        }
    }//GEN-LAST:event_MnVerifikasiActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgBookingRegistrasi dialog = new DlgBookingRegistrasi(new javax.swing.JFrame(), true);
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
    private widget.ComboBox AsalRujukan;
    private widget.Button BtnAll;
    private widget.Button BtnBahasa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnJadwal;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnPasien;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSuku;
    private widget.ComboBox COB;
    public widget.CekBox ChkInput;
    public widget.CekBox ChkNoTelp;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.ComboBox Eksekutif;
    public widget.TabPane FormInput;
    private widget.TextBox JK;
    private widget.ComboBox JenisPelayanan;
    private widget.TextBox JenisPeserta;
    private widget.ComboBox KasusKatarak;
    private widget.TextBox KdDokter;
    private widget.TextBox KdKab;
    private widget.TextBox KdKec;
    private widget.TextBox KdPPK;
    private widget.TextBox KdPenyakit;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private widget.TextBox KdPpkRujukan;
    private widget.TextBox KdProv;
    private widget.TextBox Kddpjp;
    private widget.TextBox KddpjpLayan;
    private widget.TextBox Ket;
    private widget.Label LCount;
    private widget.Label LabKab;
    private widget.Label LabKec;
    private widget.Label LabKetkll;
    private widget.Label LabNoSup;
    private widget.Label LabProv;
    private widget.Label LabSup;
    private widget.Label LabTglkll;
    private widget.Label LabelKatarak;
    private widget.Label LabelKelas;
    private widget.Label LabelKelas1;
    private widget.Label LabelPoli;
    private widget.ComboBox LakaLantas;
    private widget.TextBox LokasiLaka;
    private javax.swing.JMenuItem MnCetakKodeBarkode;
    private javax.swing.JMenuItem MnCetakKodeQR;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenu MnSttsCetakSEP;
    private javax.swing.JMenuItem MnTidakjadibatal;
    private javax.swing.JMenuItem MnVerifikasi;
    private javax.swing.JMenuItem Mnbelum;
    private javax.swing.JMenuItem Mngagal;
    private javax.swing.JMenuItem Mnsudah;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmDokter;
    private widget.TextBox NmKab;
    private widget.TextBox NmKec;
    private widget.TextBox NmPPK;
    private widget.TextBox NmPenyakit;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPpkRujukan;
    private widget.TextBox NmProv;
    private widget.TextBox NoKartu;
    private widget.TextBox NoReg;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoSEPSuplesi;
    private widget.TextBox NoTelp;
    private widget.panelisi PanelContent;
    private javax.swing.JPanel PanelInput;
    private javax.swing.JPopupMenu Popup;
    private javax.swing.JPopupMenu Popup1;
    private widget.RadioButton R2;
    private widget.RadioButton R3;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll39;
    private widget.TextBox Status;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRujukan;
    private widget.Tanggal TanggalBooking;
    private widget.Tanggal TanggalKejadian;
    private widget.Tanggal TanggalPeriksa;
    private widget.Tanggal TanggalRujuk;
    private widget.TextBox TglLahir;
    private javax.swing.JDialog WindowCariNoRujuk;
    private widget.ComboBox asesmenPel;
    private widget.Button btnDPJP;
    private widget.Button btnDPJPlayan;
    private widget.Button btnDiagnosa;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnNoRujukan;
    private widget.Button btnNoRujukanInap;
    private widget.Button btnNoSurat;
    private widget.Button btnPPKRujukan;
    private widget.Button btnPenjab;
    private widget.Button btnPoli;
    private widget.Button btnProv;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.TextBox cekKDboking;
    private widget.TextBox cekKelengkapanSEP;
    private widget.ComboBox cmbAntrianKhusus;
    private widget.ComboBox cmbPembiayaan;
    private widget.ComboBox flagPro;
    private widget.ComboBox hakKelas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame2;
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
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private widget.TextBox jamMati;
    private widget.TextBox jk;
    private widget.ComboBox kdPenunjang;
    private widget.TextBox kdbahasa;
    private widget.TextBox kdboking;
    private widget.TextBox kdpnj;
    private widget.TextBox kdsuku;
    private widget.TextBox kode_rujukanya;
    private widget.Label label_cetak;
    private widget.TextArea label_hari;
    private widget.TextArea label_pesan;
    private widget.TextBox nmbahasa;
    private widget.TextBox nmdpjpLayan;
    private widget.TextBox nmfaskes_keluar;
    private widget.TextBox nmpnj;
    private widget.TextBox nmsuku;
    private widget.TextBox noSurat;
    private widget.TextBox no_telp;
    private widget.TextBox norawat;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.TextBox pngjawab;
    private javax.swing.JMenuItem ppRencanaKontrollagi1;
    private widget.TextBox rmMati;
    private widget.Label rujukanSEP;
    private widget.ScrollPane scrollInput2;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.TextBox statusSEP;
    private widget.TextBox statusboking;
    private widget.ComboBox suplesi;
    private widget.Table tbBoking;
    private widget.Table tbFaskes1;
    private widget.Table tbFaskes2;
    private widget.Table tbFaskes3;
    private widget.Table tbFaskes4;
    public widget.Table tbRiwayat;
    private widget.TextBox tglMati;
    private widget.ComboBox tujuanKun;
    private widget.TextBox txtURL;
    private widget.ComboBox verif_data;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        if (R2.isSelected() == true) {
            status = " br.tanggal_booking between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " 00:00:00' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + " 23:59:59' ";
        } else if (R3.isSelected() == true) {
            status = " br.tanggal_periksa between '" + Valid.SetTgl(DTPCari3.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari4.getSelectedItem() + "") + "' ";
        }
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement(
                    "select br.kd_booking,DATE_FORMAT(br.tanggal_booking,'%d-%m-%Y') tgl_boking,br.no_rkm_medis, p.nm_pasien,br.tanggal_periksa,pj.png_jawab,pl.nm_poli, "
                    + "d.nm_dokter,br.no_reg,concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) alamat, "
                    + "br.status_booking, br.data_dari, br.kd_poli, br.kd_dokter, br.kd_pj, br.no_telp_pemesan, br.no_rawat, "
                    + "if(br.kd_pj='U01','Tidak ada SEP',ks.status_cetak_sep) sep_bpjs, br.antrian_khusus from booking_registrasi br "
                    + "inner join pasien p on p.no_rkm_medis=br.no_rkm_medis inner join dokter d on d.kd_dokter=br.kd_dokter "
                    + "inner join poliklinik pl on pl.kd_poli=br.kd_poli INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab "
                    + "INNER JOIN penjab pj on pj.kd_pj=br.kd_pj left join kelengkapan_booking_sep_bpjs ks on ks.kd_booking=br.kd_booking where "
                    + status + " and br.no_rkm_medis like ? or "
                    + status + " and p.nm_pasien like ? or "
                    + status + " and pl.nm_poli like ? or "
                    + status + " and br.kd_booking like ? or "
                    + status + " and br.no_rkm_medis like ? or "
                    + status + " and pj.png_jawab like ? or "
                    + status + " and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,',',kb.nm_kab) like ? or "
                    + status + " and br.status_booking like ? or "
                    + status + " and br.data_dari like ? or "
                    + status + " and br.no_telp_pemesan like ? or "
                    + status + " and br.no_rawat like ? or "
                    + status + " and if(br.kd_pj='U01','Tidak ada SEP',ks.status_cetak_sep) like ? or "
                    + status + " and d.nm_dokter like ? order by br.tanggal_booking,d.nm_dokter");
            try {
                ps.setString(1, "%" + TCari.getText().trim() + "%");
                ps.setString(2, "%" + TCari.getText().trim() + "%");
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, "%" + TCari.getText().trim() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + TCari.getText().trim() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        rs.getString("kd_booking"),
                        rs.getString("tgl_boking"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tanggal_periksa"),
                        rs.getString("png_jawab"),
                        rs.getString("nm_poli"),
                        rs.getString("nm_dokter"),
                        rs.getString("no_reg"),
                        rs.getString("alamat"),
                        rs.getString("status_booking"),
                        rs.getString("data_dari"),
                        rs.getString("kd_poli"),
                        rs.getString("kd_dokter"),
                        rs.getString("kd_pj"),
                        rs.getString("no_telp_pemesan"),
                        rs.getString("no_rawat"),
                        rs.getString("sep_bpjs"),
                        rs.getString("antrian_khusus")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        autoNomorBooking();
        FormInput.setSelectedIndex(0);
        FormInput.setEnabledAt(1, false);
        FormInput.setEnabledAt(2, false);
        FormInput.setEnabledAt(3, false);
        TNoRM.requestFocus();
        KdDokter.setText("");
        NmDokter.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        kdpnj.setText("");
        nmpnj.setText("");
        no_telp.setText("");
        statusSEP.setText("");
        jk.setText("");
        rmMati.setText("");
        jamMati.setText("");
        tglMati.setText("");
        cekKDboking.setText("");
        norawat.setText("");
        statusboking.setText("");
        kdsuku.setText("");
        nmsuku.setText("");
        kdbahasa.setText("");
        nmbahasa.setText("");
        cekKelengkapanSEP.setText("");
        verif_data.setSelectedIndex(0);
        ChkNoTelp.setSelected(false);
        TanggalBooking.setDate(new Date());
        TanggalPeriksa.setDate(new Date());
        label_pesan.setText("");
        cmbAntrianKhusus.setSelectedIndex(2);
        loadURL("");
        initComponents2();
        cekHariLibur();
        isNomer();
        emptKelengkapanSEP();
    }

    private void isNomer() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='" + KdPoli.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and kd_poli='" + KdPoli.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='" + KdDokter.getText() + "' and tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'", "", 3, NoReg);
                break;
        }
    }

    private void getData() {
        n = 0;
        label_pesan.setText("");
        
        if (tbBoking.getSelectedRow() != -1) {
            kdboking.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 0).toString());
            TNoRM.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 2).toString());
            TPasien.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 3).toString());
            KdPoli.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 12).toString());
            Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=? ", NmPoli, KdPoli.getText());
            KdDokter.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 13).toString());
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=? ", NmDokter, KdDokter.getText());
            kdpnj.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 14).toString());
            Sequel.cariIsi("select png_jawab from penjab where kd_pj=? ", nmpnj, kdpnj.getText());
            NoReg.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 8).toString());
            statusboking.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 10).toString());
            verif_data.setSelectedItem(tbBoking.getValueAt(tbBoking.getSelectedRow(), 11).toString());
            Valid.SetTgl(TanggalPeriksa, tbBoking.getValueAt(tbBoking.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select no_telp_pemesan from booking_registrasi where kd_booking=? ", no_telp, kdboking.getText());
            Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());
            Sequel.cariIsi("select jk from pasien where no_rkm_medis=? ", jk, TNoRM.getText());
            norawat.setText(tbBoking.getValueAt(tbBoking.getSelectedRow(), 16).toString());
            Sequel.cariIsi("select suku_bangsa from pasien where no_rkm_medis=?", kdsuku, TNoRM.getText());
            Sequel.cariIsi("select bahasa_pasien from pasien where no_rkm_medis=?", kdbahasa, TNoRM.getText());
            Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
            Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
            cmbAntrianKhusus.setSelectedItem(tbBoking.getValueAt(tbBoking.getSelectedRow(), 18).toString());
            
            if (!kdpnj.getText().equals("B01")) {
                label_pesan.setText("-");
            } else {
                label_pesan.setText("");
                n = Sequel.cariInteger("select count(-1) from bridging_sep_backup where "
                        + "tglsep='" + tbBoking.getValueAt(tbBoking.getSelectedRow(), 4).toString() + "' and "
                        + "nomr='" + TNoRM.getText() + "' and code<>'XXX'");

                for (i = 0; i < n; i++) {
                    if (Sequel.cariInteger("select count(-1) from bridging_sep_backup where "
                            + "tglsep='" + tbBoking.getValueAt(tbBoking.getSelectedRow(), 4).toString() + "' and "
                            + "nomr='" + TNoRM.getText() + "' and code<>'XXX'") > 0) {

                        label_pesan.setText(label_pesan.getText() + Sequel.cariIsi("select concat(code,' : ',message) from bridging_sep_backup where "
                                + "tglsep='" + tbBoking.getValueAt(tbBoking.getSelectedRow(), 4).toString() + "' and "
                                + "nomr='" + TNoRM.getText() + "' and code<>'XXX'") + "\n\n");
                    }
                }
            }

            if (akses.getkode().equals("Admin Utama") || (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03")))) {
                FormInput.setEnabledAt(1, true);
                FormInput.setEnabledAt(2, true);
                FormInput.setEnabledAt(3, true);
            } else if ((!kdpnj.getText().equals("B01") || (!kdpnj.getText().equals("A03")))) {
                FormInput.setEnabledAt(1, false);
                FormInput.setEnabledAt(2, false);
                FormInput.setEnabledAt(3, false);
                FormInput.setSelectedIndex(0);
                emptKelengkapanSEP();
            }
            
            if (TNoRM.getText().trim().equals("")) {
                Valid.tabelKosong(tabMode5);
            } else {
                SEPkontrol = "";
                tampilKunjungan();
            }

            cekDataKelengkapan();
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            JenisPelayanan.setSelectedIndex(1);
            //untuk penjamin laka masih belum bisa ditampilkan (bingung caranya)

//            ChkInput.setSelected(true);
//            isForm();
            label_cetak.setVisible(true);
            statusSEP.setVisible(true);
            tampilCekFinger();
        }
    }

    public void setNoRm(String norm, String nama) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    public void setNoRm(String norm, String nama, String kodepoli, String namapoli, String kodedokter, String namadokter) {
        TNoRM.setText(norm);
        TPasien.setText(nama);
        KdPoli.setText(kodepoli);
        NmPoli.setText(namapoli);
        KdDokter.setText(kodedokter);
        NmDokter.setText(namadokter);
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }

    public void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 380));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }

    public void isCek() {
        BtnSimpan.setEnabled(akses.getbooking_registrasi());
        BtnHapus.setEnabled(akses.getbooking_registrasi());
//        BtnPrint.setEnabled(var.getbooking_registrasi());
        BtnPrint.setEnabled(false);
    }

    public void autoNomorBooking() {
        TanggalBooking.setDate(new Date());

        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_booking,4),signed)),0) from booking_registrasi where "
                + "tanggal_booking like '%" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(0, 7) + "%' ", "/BK/" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(TanggalBooking.getSelectedItem() + "").substring(0, 4), 4, kdboking);
    }

    public void tampilFaskes1() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode1);
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------                
//                JsonNode response = root.path("response").path("rujukan");
                tabMode1.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes2() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode2);
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/RS/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------                
//                JsonNode response = root.path("response").path("rujukan");
                tabMode2.addRow(new Object[]{
                    "1", response.path("noKunjungan").asText(),
                    response.path("tglKunjungan").asText(),
                    response.path("provPerujuk").path("kode").asText(),
                    response.path("provPerujuk").path("nama").asText(),
                    response.path("poliRujukan").path("nama").asText(),
                    response.path("diagnosa").path("kode").asText(),
                    response.path("diagnosa").path("nama").asText(),
                    response.path("keluhan").asText(),
                    response.path("poliRujukan").path("kode").asText(),
                    response.path("pelayanan").path("kode").asText(),
                    response.path("pelayanan").path("nama").asText()
                });
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 2.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes1BYK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode3);
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/List/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------                
//                JsonNode response = root.path("response");
                if (response.path("rujukan").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("rujukan")) {
                        tabMode3.addRow(new Object[]{
                            i + ".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1 data rujukan banyak.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    public void tampilFaskes2BYK() {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            Valid.tabelKosong(tabMode4);
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/RS/List/Peserta/" + NoKartu.getText();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------            
//                JsonNode response = root.path("response");
                if (response.path("rujukan").isArray()) {
                    i = 1;
                    for (JsonNode list : response.path("rujukan")) {
                        tabMode4.addRow(new Object[]{
                            i + ".",
                            list.path("noKunjungan").asText(),
                            list.path("tglKunjungan").asText(),
                            list.path("provPerujuk").path("kode").asText(),
                            list.path("provPerujuk").path("nama").asText(),
                            list.path("poliRujukan").asText(),
                            list.path("diagnosa").path("kode").asText(),
                            list.path("diagnosa").path("nama").asText(),
                            list.path("keluhan").asText(),
                            list.path("poliRujukan").path("kode").asText(),
                            list.path("pelayanan").path("kode").asText(),
                            list.path("pelayanan").path("nama").asText()
                        });
                        i++;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 2 data rujukan banyak.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private void getDataFK1() {
        if (tbFaskes1.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
//NmPpkRujukan.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 7).toString());
//            KdPoli1.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 9).toString());
//            NmPoli1.setText(tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 5).toString());

            if (tbFaskes1.getValueAt(tbFaskes1.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK2() {
        if (tbFaskes2.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 7).toString());
//            KdPoli1.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 9).toString());
//            NmPoli1.setText(tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 5).toString());

            if (tbFaskes2.getValueAt(tbFaskes2.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK1byk() {
        if (tbFaskes3.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(0);
            NoRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 7).toString());
//            KdPoli1.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 9).toString());
//            NmPoli1.setText(tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 5).toString());

            if (tbFaskes3.getValueAt(tbFaskes3.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    private void getDataFK2byk() {
        if (tbFaskes4.getSelectedRow() != -1) {
            AsalRujukan.setSelectedIndex(1);
            NoRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 1).toString());
            Valid.SetTgl(TanggalRujuk, tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 2).toString());
            KdPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 3).toString());
            rujukanSEP.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            //NmPpkRujukan.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 4).toString());
            KdPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 6).toString());
            NmPenyakit.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 7).toString());
//            KdPoli1.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 9).toString());
//            NmPoli1.setText(tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 5).toString());

            if (tbFaskes4.getValueAt(tbFaskes4.getSelectedRow(), 10).toString().contains("2")) {
                JenisPelayanan.setSelectedIndex(1);
            } else {
                JenisPelayanan.setSelectedIndex(0);
            }
        }
    }

    public void cekPPKRUJUKAN() {
        if (!NoKartu.getText().equals("")) {
            if (AsalRujukan.getSelectedIndex() == 0) {
                KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
//                NmPpkRujukan.setText(cekViaBPJSKartu.provUmumnmProvider);
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            } else if (AsalRujukan.getSelectedIndex() == 1) {
                KdPpkRujukan.setText(KdPPK.getText());
//                NmPpkRujukan.setText(NmPPK.getText());
                Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
            }
        } else if (NoKartu.getText().equals("")) {
            cekLAYAN();
        } else {
            JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
            btnDiagnosa.requestFocus();
        }
    }

    public void cekLAYAN() {
        if (JenisPelayanan.getSelectedItem().equals("1. Ranap")) {
            KdPoli.setText("");
            NmPoli.setText("");
            LabelPoli.setVisible(false);
            KdPoli.setVisible(false);
            NmPoli.setVisible(false);
            btnPoli.setVisible(false);

            jLabel10.setText("No. SPRI :");
            NoRujukan.setText("");
            noSurat.setText("");
//            NoRujukan.setText(TNoRw.getText());
//            noSKDP.setText(TNoRw.getText());
            AsalRujukan.setSelectedIndex(1);
        } else {
            LabelPoli.setVisible(true);
            KdPoli.setVisible(true);
            NmPoli.setVisible(true);
            btnPoli.setVisible(true);
            jLabel10.setText("No. Surat Kontrol :");
            noSurat.setText("");
            AsalRujukan.setSelectedIndex(0);
        }
    }

    public void cekBPJS() {
        no_peserta = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis=?", TNoRM.getText());

        if (no_peserta.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien tidak mempunyai kepesertaan BPJS");
            BtnBatal.requestFocus();
        } else {
            cekViaBPJSKartu.tampil(no_peserta, Valid.SetTgl(TanggalPeriksa.getSelectedItem() + ""));
            if (cekViaBPJSKartu.informasi.equals("OK")) {
                if (cekViaBPJSKartu.statusPesertaketerangan.equals("AKTIF")) {
                    KdPPK.setText(Sequel.cariIsi("select kode_ppk from setting"));
                    NmPPK.setText(Sequel.cariIsi("select nama_instansi from setting"));
                    TPasien.setText(Strings.toUpperCase(cekViaBPJSKartu.nama));
                    TglLahir.setText(cekViaBPJSKartu.tglLahir);
                    NoKartu.setText(no_peserta);
                    JenisPeserta.setText(cekViaBPJSKartu.jenisPesertaketerangan);
                    Status.setText(cekViaBPJSKartu.statusPesertaketerangan);

                    if (cekViaBPJSKartu.sex.equals("L")) {
                        JK.setText("Laki-laki");
                    } else {
                        JK.setText("Perempuan");
                    }

                    if (AsalRujukan.getSelectedIndex() == 0) {
                        KdPpkRujukan.setText(cekViaBPJSKartu.provUmumkdProvider);
                        rujukanSEP.setText(cekViaBPJSKartu.provUmumnmProvider);
                        Sequel.cariIsi("select nama_rujukan from master_nama_rujukan where kode_faskes_bpjs=? ", NmPpkRujukan, KdPpkRujukan.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
//                            NmPpkRujukan.setText("");
                    } else if (AsalRujukan.getSelectedIndex() == 1) {
                        KdPpkRujukan.setText(KdPPK.getText());
//                            NmPpkRujukan.setText(NmPPK.getText());
                        rujukanSEP.setText(NmPPK.getText());
                        NmPpkRujukan.setText(NmPPK.getText());
                        Sequel.cariIsi("select kd_rujukan from master_nama_rujukan where status='1' and kode_faskes_bpjs=? ", kode_rujukanya, KdPpkRujukan.getText());
                    }
                    
                    if (cekViaBPJSKartu.hakKelaskode.equals("1")) {
                        hakKelas.setSelectedIndex(0);
                    } else if (cekViaBPJSKartu.hakKelaskode.equals("2")) {
                        hakKelas.setSelectedIndex(1);
                    } else if (cekViaBPJSKartu.hakKelaskode.equals("3")) {
                        hakKelas.setSelectedIndex(2);
                    }

//                    tampilNoRujukan(no_peserta);
                    NoTelp.setText(no_telp.getText());

//                    if (cekViaBPJSKartu.mrnoTelepon.equals("null")) {
//                        NoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
//                    } else {
//                        NoTelp.setText(cekViaBPJSKartu.mrnoTelepon);
//                    }
//                    NoTelp.requestFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Status kepesertaan tidak aktif..!!");
                    BtnBatal.requestFocus();
                }
            } else {
                BtnBatal.requestFocus();
            }
        }
    }

    public void tampilNoRujukan(String nomorPeserta) {
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/Peserta/" + nomorPeserta;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            if (nameNode.path("code").asText().equals("200")) {
                Valid.tabelKosong(tabMode);
//ini yang baru -----------            
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc)).path("rujukan");
//sampai sini -------------
//                JsonNode response = root.path("response").path("rujukan");
                
                KdPenyakit.setText(response.path("diagnosa").path("kode").asText());
                NmPenyakit.setText(response.path("diagnosa").path("nama").asText());
                NoRujukan.setText(response.path("noKunjungan").asText());
                KdPpkRujukan.setText(response.path("provPerujuk").path("kode").asText());
//                NmPpkRujukan.setText(response.path("provPerujuk").path("nama").asText());
                Valid.SetTgl(TanggalRujuk, response.path("tglKunjungan").asText());
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText() + " di Faskes 1.");
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
            }
        }
    }

    private void simpanBooking() {
        Sequel.menyimpan("booking_registrasi",
                "Now(),"
                + "'" + TNoRM.getText() + "',"
                + "'" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "',"
                + "'" + KdDokter.getText() + "',"
                + "'" + KdPoli.getText() + "',"
                + "'" + NoReg.getText() + "',"
                + "'" + kdboking.getText() + "',"
                + "'Menunggu',"
                + "'" + kdpnj.getText() + "',"
                + "'" + verif_data.getSelectedItem().toString() + "',"
                + "'" + no_telp.getText() + "',"
                + "'-',"
                + "'" + cmbAntrianKhusus.getSelectedItem().toString() + "'");
    }

    private void simpanKelengkapanSEP() {
        pembi = "";
        kdpnjg = "";
        flag = "";
        asesmen = "";
        jkel = "";

        if (JK.getText().equals("Laki-laki")) {
            jkel = "L";
        } else {
            jkel = "P";
        }

        tglkkl = "0000-00-00";
        if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2 || LakaLantas.getSelectedIndex() == 3) {
            tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
        }

        //-----------------------------------------------------------------------------------------------
        if (cmbPembiayaan.getSelectedIndex() == 0) {
            pembi = "";
        } else {
            pembi = cmbPembiayaan.getSelectedItem().toString().substring(0, 1);
        }

        if (flagPro.getSelectedIndex() == 0) {
            flag = "";
        } else {
            flag = flagPro.getSelectedItem().toString().substring(0, 1);
        }

        if (kdPenunjang.getSelectedIndex() == 10 || kdPenunjang.getSelectedIndex() == 11 || kdPenunjang.getSelectedIndex() == 12) {
            kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 2);
        } else if (kdPenunjang.getSelectedIndex() == 0) {
            kdpnjg = "";
        } else {
            kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 1);
        }

        if (asesmenPel.getSelectedIndex() == 0) {
            asesmen = "";
        } else {
            asesmen = asesmenPel.getSelectedItem().toString().substring(0, 1);
        }

        if (JenisPelayanan.getSelectedIndex() == 0) {
            KddpjpLayan.setText("");
            nmdpjpLayan.setText("");
        }
        //-----------------------------------------------------------------------------------------------

        Sequel.menyimpan("kelengkapan_booking_sep_bpjs", ""
                + "'" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "',"
                + "'" + NoRujukan.getText() + "',"
                + "'" + KdPpkRujukan.getText() + "',"
                + "'" + rujukanSEP.getText() + "',"
                + "'" + KdPPK.getText() + "',"
                + "'" + NmPPK.getText() + "',"
                + "'" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "',"
                + "'Registrasi pasien dengan mendaftar online',"
                + "'" + KdPenyakit.getText() + "',"
                + "'" + NmPenyakit.getText().replace("'", "''") + "',"
                + "'" + KdPoli1.getText() + "',"
                + "'" + NmPoli1.getText() + "',"
                + "'" + hakKelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + LokasiLaka.getText() + "',"
                + "'" + user + "',"
                + "'" + TNoRM.getText() + "',"
                + "'" + TPasien.getText().replace("'", "''") + "',"
                + "'" + TglLahir.getText() + "',"
                + "'" + JenisPeserta.getText() + "',"
                + "'" + jkel + "',"
                + "'" + NoKartu.getText() + "',"
                + "'0000-00-00 00:00:00',"
                + "'" + AsalRujukan.getSelectedItem().toString() + "',"
                + "'" + Eksekutif.getSelectedItem().toString() + "',"
                + "'" + COB.getSelectedItem().toString() + "',"
                + "'',"
                + "'" + no_telp.getText() + "',"
                + "'" + KasusKatarak.getSelectedItem().toString() + "',"
                + "'" + tglkkl + "',"
                + "'" + Ket.getText() + "',"
                + "'" + suplesi.getSelectedItem().toString() + "',"
                + "'" + NoSEPSuplesi.getText() + "',"
                + "'" + KdProv.getText() + "',"
                + "'" + NmProv.getText() + "',"
                + "'" + KdKab.getText() + "',"
                + "'" + NmKab.getText() + "',"
                + "'" + KdKec.getText() + "',"
                + "'" + NmKec.getText() + "',"
                + "'" + noSurat.getText() + "',"
                + "'" + Kddpjp.getText() + "',"
                + "'" + NmDPJP.getText() + "',"
                + "'',"
                + "'" + kdboking.getText() + "',"
                + "'BELUM',"
                + "'" + NmPpkRujukan.getText() + "',"
                + "'-',"                        
                + "'" + hakKelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "'" + pembi + "',"
                + "'" + pngjawab.getText() + "',"
                + "'" + tujuanKun.getSelectedItem() + "',"
                + "'" + flag + "',"
                + "'" + kdpnjg + "',"
                + "'" + asesmen + "',"
                + "'" + KddpjpLayan.getText() + "',"
                + "'" + nmdpjpLayan.getText() + "'");

        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + no_telp.getText() + "' ");
    }

    private void cekPasien() {
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRM, "pasien");
            TNoRM.requestFocus();
        } else if (!TNoRM.getText().equals("")) {
            Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());

            if (TPasien.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Data pasien tidak ditemukan...");
                TPasien.setText("");
                TNoRM.requestFocus();
            } else if (!TPasien.getText().equals("")) {
                Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis=? ", rmMati, TNoRM.getText());                
                try {
                    psPasien = koneksi.prepareStatement(
                            "select nm_pasien, jk, suku_bangsa, bahasa_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
                    try {
                        rsPasien = psPasien.executeQuery();
                        while (rsPasien.next()) {
                            TPasien.setText(rsPasien.getString("nm_pasien"));
                            jk.setText(rsPasien.getString("jk"));
                            kdsuku.setText(rsPasien.getString("suku_bangsa"));
                            kdbahasa.setText(rsPasien.getString("bahasa_pasien"));
                            nmsuku.setText(Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id='" + kdsuku.getText() + "'"));
                            nmbahasa.setText(Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id='" + kdbahasa.getText() + "'"));
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsPasien != null) {
                            rsPasien.close();
                        }
                        if (psPasien != null) {
                            psPasien.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                BtnPoli.requestFocus();
            }
        }
    }

    public void emptKelengkapanSEP() {
        kode_rujukanya.setText("");
        nmfaskes_keluar.setText("");
        LokasiLaka.setText("");

        TglLahir.setText("");
        JenisPeserta.setText("");
        JK.setText("");
        KdPPK.setText("");
        NmPPK.setText("");
        NoKartu.setText("");
        Status.setText("");
        NoRujukan.setText("");
        noSurat.setText("");
        AsalRujukan.setSelectedIndex(0);
        rujukanSEP.setText("- belum terisi -");
        Kddpjp.setText("");
        NmDPJP.setText("");
        KdPpkRujukan.setText("");
        NmPpkRujukan.setText("");
        KasusKatarak.setSelectedIndex(0);
        NoTelp.setText("");
        KdPenyakit.setText("");
        NmPenyakit.setText("");
        JenisPelayanan.setSelectedIndex(1);
        hakKelas.setSelectedIndex(0);
        KdPoli1.setText("");
        NmPoli1.setText("");
        TanggalRujuk.setDate(new Date());
        Eksekutif.setSelectedIndex(0);
        COB.setSelectedIndex(0);
        label_cetak.setVisible(false);
        statusSEP.setVisible(false);

        LakaLantas.setSelectedIndex(0);
        TanggalKejadian.setDate(new Date());
        Ket.setText("");
        KdProv.setText("");
        NmProv.setText("");
        KdKab.setText("");
        NmKab.setText("");
        KdKec.setText("");
        NmKec.setText("");
        suplesi.setSelectedIndex(0);
        NoSEPSuplesi.setText("");
        
        KddpjpLayan.setText("");
        nmdpjpLayan.setText("");
        tujuanKun.setSelectedIndex(0);
        cmbPembiayaan.setSelectedIndex(0);
        pngjawab.setText("");
        flagPro.setSelectedIndex(0);
        kdPenunjang.setSelectedIndex(0);
        asesmenPel.setSelectedIndex(0);        
    }

    private void gantiDataBooking() {
        Sequel.mengedit("booking_registrasi", "kd_booking='" + kdboking.getText() + "'",
                "tanggal_periksa='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "', "
                + "kd_dokter='" + KdDokter.getText() + "', "
                + "kd_poli='" + KdPoli.getText() + "', "
                + "kd_pj='" + kdpnj.getText() + "', "
                + "data_dari='" + verif_data.getSelectedItem().toString() + "', "
                + "no_telp_pemesan='" + no_telp.getText() + "', "
                + "antrian_khusus='" + cmbAntrianKhusus.getSelectedItem().toString() + "' ");
    }

    private void gantiDataSEP() {
        pembi = "";
        kdpnjg = "";
        flag = "";
        asesmen = "";
        jkel = "";

        if (JK.getText().equals("Laki-laki")) {
            jkel = "L";
        } else {
            jkel = "P";
        }

        if (LakaLantas.getSelectedIndex() == 1 || LakaLantas.getSelectedIndex() == 2 || LakaLantas.getSelectedIndex() == 3) {
            tglkkl = Valid.SetTgl(TanggalKejadian.getSelectedItem() + "");
        } else {
            tglkkl = "0000-00-00";
        }

        //-----------------------------------------------------------------------------------------------
        if (cmbPembiayaan.getSelectedIndex() == 0) {
            pembi = "";
        } else {
            pembi = cmbPembiayaan.getSelectedItem().toString().substring(0, 1);
        }

        if (flagPro.getSelectedIndex() == 0) {
            flag = "";
        } else {
            flag = flagPro.getSelectedItem().toString().substring(0, 1);
        }

        if (kdPenunjang.getSelectedIndex() == 10 || kdPenunjang.getSelectedIndex() == 11 || kdPenunjang.getSelectedIndex() == 12) {
            kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 2);
        } else if (kdPenunjang.getSelectedIndex() == 0) {
            kdpnjg = "";
        } else {
            kdpnjg = kdPenunjang.getSelectedItem().toString().substring(0, 1);
        }

        if (asesmenPel.getSelectedIndex() == 0) {
            asesmen = "";
        } else {
            asesmen = asesmenPel.getSelectedItem().toString().substring(0, 1);
        }

        if (JenisPelayanan.getSelectedIndex() == 0) {
            KddpjpLayan.setText("");
            nmdpjpLayan.setText("");
        }
        //-----------------------------------------------------------------------------------------------

        Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + kdboking.getText() + "'",
                "tglrujukan='" + Valid.SetTgl(TanggalRujuk.getSelectedItem() + "") + "', "
                + "no_rujukan='" + NoRujukan.getText() + "',"
                + "kdppkrujukan='" + KdPpkRujukan.getText() + "', "
                + "nmppkrujukan='" + rujukanSEP.getText() + "',"
                + "kdppkpelayanan='" + KdPPK.getText() + "',"
                + "nmppkpelayanan='" + NmPPK.getText() + "',"
                + "jnspelayanan='" + JenisPelayanan.getSelectedItem().toString().substring(0, 1) + "',"
                + "diagawal='" + KdPenyakit.getText() + "',"
                + "nmdiagnosaawal='" + NmPenyakit.getText().replace("'", "''") + "',"
                + "kdpolitujuan='" + KdPoli1.getText() + "',"
                + "nmpolitujuan='" + NmPoli1.getText() + "',"
                + "klsrawat='" + hakKelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "lakalantas='" + LakaLantas.getSelectedItem().toString().substring(0, 1) + "',"
                + "lokasilaka='" + LokasiLaka.getText() + "',"
                + "user='" + user + "',"
                + "nomr='" + TNoRM.getText() + "',"
                + "nama_pasien='" + TPasien.getText().replace("'", "''") + "',"
                + "tanggal_lahir='" + TglLahir.getText() + "',"
                + "peserta='" + JenisPeserta.getText() + "',"
                + "jkel='" + jkel + "',"
                + "no_kartu='" + NoKartu.getText() + "',"
                + "asal_rujukan='" + AsalRujukan.getSelectedItem().toString() + "',"
                + "eksekutif='" + Eksekutif.getSelectedItem().toString() + "',"
                + "cob='" + COB.getSelectedItem().toString() + "',"
                + "notelep='" + no_telp.getText() + "',"
                + "katarak='" + KasusKatarak.getSelectedItem().toString() + "',"
                + "tglkkl='" + tglkkl + "',"
                + "keterangankkl='" + Ket.getText() + "',"
                + "suplesi='" + suplesi.getSelectedItem().toString() + "',"
                + "no_sep_suplesi='" + NoSEPSuplesi.getText() + "',"
                + "kdprop='" + KdProv.getText() + "',"
                + "nmprop='" + NmProv.getText() + "',"
                + "kdkab='" + KdKab.getText() + "',"
                + "nmkab='" + NmKab.getText() + "',"
                + "kdkec='" + KdKec.getText() + "',"
                + "nmkec='" + NmKec.getText() + "',"
                + "noskdp='" + noSurat.getText() + "',"
                + "kddpjp='" + Kddpjp.getText() + "',"
                + "nmdpdjp='" + NmDPJP.getText() + "',"
                + "rujukan_masuknya='" + NmPpkRujukan.getText() + "',"
                + "klsRawatHak='" + hakKelas.getSelectedItem().toString().substring(0, 1) + "',"
                + "pembiayaan='" + pembi + "',"
                + "penanggungJawab='" + pngjawab.getText() + "',"
                + "tujuanKunjungan='" + tujuanKun.getSelectedItem() + "',"
                + "flagProcedur='" + flag + "',"
                + "kdPenunjang='" + kdpnjg + "',"
                + "assesmentPel='" + asesmen + "',"
                + "dpjpLayan='" + KddpjpLayan.getText() + "',"
                + "nmdpjpLayan='" + nmdpjpLayan.getText() + "' ");

        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "no_tlp='" + no_telp.getText() + "'");
    }

    private void cekDataKelengkapan() {
        cekPembi = "";
        cekFlag = "";
        cekKDpen = "";
        cekAses = "";
        try {
            pskelengkapanBPJS = koneksi.prepareStatement("select tanggal_lahir, peserta, jkel, kdppkpelayanan, nmppkpelayanan, no_kartu, 'AKTIF' status, "
                    + "no_rujukan, tglrujukan, noskdp, katarak, asal_rujukan, nmppkrujukan, kddpjp, nmdpdjp, kdppkrujukan, notelep, diagawal, "
                    + "nmdiagnosaawal, IF (klsrawat = '1','1. Kelas 1',IF (klsrawat = '2','2. Kelas 2','3. Kelas 3')) kls_rwt, kdpolitujuan, nmpolitujuan, eksekutif, "
                    + "cob, IF (lakalantas = '0','0. Tidak','1. Ya') kasus_laka, keterangankkl, kdprop, nmprop, kdkab, nmkab, kdkec, nmkec, suplesi, no_sep_suplesi, status_cetak_sep, "
                    + "dpjpLayan, nmdpjpLayan, klsRawatHak, ifnull(pembiayaan,'') pembiayaan, penanggungJawab, tujuanKunjungan, ifnull(flagProcedur,'') flagProcedur, "
                    + "ifnull(kdPenunjang,'') kdPenunjang , ifnull(assesmentPel,'') assesmentPel from kelengkapan_booking_sep_bpjs where kd_booking=?");
            try {
                pskelengkapanBPJS.setString(1, kdboking.getText());
                rskelengkapanBPJS = pskelengkapanBPJS.executeQuery();
                while (rskelengkapanBPJS.next()) {
                    TglLahir.setText(rskelengkapanBPJS.getString("tanggal_lahir"));
                    JenisPeserta.setText(rskelengkapanBPJS.getString("peserta"));
                    JK.setText(rskelengkapanBPJS.getString("jkel").replaceAll("L", "Laki-laki").replaceAll("P", "Perempuan"));
                    KdPPK.setText(rskelengkapanBPJS.getString("kdppkpelayanan"));
                    NmPPK.setText(rskelengkapanBPJS.getString("nmppkpelayanan"));
                    NoKartu.setText(rskelengkapanBPJS.getString("no_kartu"));
                    Status.setText(rskelengkapanBPJS.getString("status"));
                    NoRujukan.setText(rskelengkapanBPJS.getString("no_rujukan"));
                    Valid.SetTgl(TanggalRujuk, rskelengkapanBPJS.getString("tglrujukan"));
                    noSurat.setText(rskelengkapanBPJS.getString("noskdp"));
                    KasusKatarak.setSelectedItem(rskelengkapanBPJS.getString("katarak"));
                    AsalRujukan.setSelectedItem(rskelengkapanBPJS.getString("asal_rujukan"));
                    rujukanSEP.setText(rskelengkapanBPJS.getString("nmppkrujukan"));
                    Kddpjp.setText(rskelengkapanBPJS.getString("kddpjp"));
                    NmDPJP.setText(rskelengkapanBPJS.getString("nmdpdjp"));
                    KdPpkRujukan.setText(rskelengkapanBPJS.getString("kdppkrujukan"));
                    NoTelp.setText(rskelengkapanBPJS.getString("notelep"));
                    KdPenyakit.setText(rskelengkapanBPJS.getString("diagawal"));
                    NmPenyakit.setText(rskelengkapanBPJS.getString("nmdiagnosaawal"));
                    hakKelas.setSelectedItem(rskelengkapanBPJS.getString("kls_rwt"));
                    KdPoli1.setText(rskelengkapanBPJS.getString("kdpolitujuan"));
                    NmPoli1.setText(rskelengkapanBPJS.getString("nmpolitujuan"));
                    Eksekutif.setSelectedItem(rskelengkapanBPJS.getString("eksekutif"));
                    COB.setSelectedItem(rskelengkapanBPJS.getString("cob"));
                    LakaLantas.setSelectedItem(rskelengkapanBPJS.getString("kasus_laka"));
                    Ket.setText(rskelengkapanBPJS.getString("keterangankkl"));
                    KdProv.setText(rskelengkapanBPJS.getString("kdprop"));
                    NmProv.setText(rskelengkapanBPJS.getString("nmprop"));
                    KdKab.setText(rskelengkapanBPJS.getString("kdkab"));
                    NmKab.setText(rskelengkapanBPJS.getString("nmkab"));
                    KdKec.setText(rskelengkapanBPJS.getString("kdkec"));
                    NmKec.setText(rskelengkapanBPJS.getString("nmkec"));
                    suplesi.setSelectedItem(rskelengkapanBPJS.getString("suplesi"));
                    NoSEPSuplesi.setText(rskelengkapanBPJS.getString("no_sep_suplesi"));
                    statusSEP.setText(rskelengkapanBPJS.getString("status_cetak_sep"));                    
                    KddpjpLayan.setText(rskelengkapanBPJS.getString("dpjpLayan"));
                    nmdpjpLayan.setText(rskelengkapanBPJS.getString("nmdpjpLayan"));                    
                    pngjawab.setText(rskelengkapanBPJS.getString("penanggungJawab")); 
                    tujuanKun.setSelectedItem(rskelengkapanBPJS.getString("tujuanKunjungan"));
                  
                    cekPembi = rskelengkapanBPJS.getString("pembiayaan");
                    if (cekPembi.equals("")) {
                        cmbPembiayaan.setSelectedIndex(0);
                    } else if (cekPembi.equals("1")) {
                        cmbPembiayaan.setSelectedIndex(1);
                    } else if (cekPembi.equals("2")) {
                        cmbPembiayaan.setSelectedIndex(2);
                    } else if (cekPembi.equals("3")) {
                        cmbPembiayaan.setSelectedIndex(3);
                    }
                    
                    cekFlag = rskelengkapanBPJS.getString("flagProcedur");
                    if (cekFlag.equals("")) {
                        flagPro.setSelectedIndex(0);
                    } else if (cekFlag.equals("0")) {
                        flagPro.setSelectedIndex(1);
                    } else if (cekFlag.equals("1")) {
                        flagPro.setSelectedIndex(2);
                    }
                    
                    cekKDpen = rskelengkapanBPJS.getString("kdPenunjang");
                    if (cekKDpen.equals("")) {
                        kdPenunjang.setSelectedIndex(0);
                    } else if (cekKDpen.equals("1")) {
                        kdPenunjang.setSelectedIndex(1);
                    } else if (cekKDpen.equals("2")) {
                        kdPenunjang.setSelectedIndex(2);
                    } else if (cekKDpen.equals("3")) {
                        kdPenunjang.setSelectedIndex(3);
                    } else if (cekKDpen.equals("4")) {
                        kdPenunjang.setSelectedIndex(4);
                    } else if (cekKDpen.equals("5")) {
                        kdPenunjang.setSelectedIndex(5);
                    } else if (cekKDpen.equals("6")) {
                        kdPenunjang.setSelectedIndex(6);
                    } else if (cekKDpen.equals("7")) {
                        kdPenunjang.setSelectedIndex(7);
                    } else if (cekKDpen.equals("8")) {
                        kdPenunjang.setSelectedIndex(8);
                    } else if (cekKDpen.equals("9")) {
                        kdPenunjang.setSelectedIndex(9);
                    } else if (cekKDpen.equals("10")) {
                        kdPenunjang.setSelectedIndex(10);
                    } else if (cekKDpen.equals("11")) {
                        kdPenunjang.setSelectedIndex(11);
                    } else if (cekKDpen.equals("12")) {
                        kdPenunjang.setSelectedIndex(12);
                    }
                    
                    cekAses = rskelengkapanBPJS.getString("assesmentPel");
                    if (cekAses.equals("")) {
                        asesmenPel.setSelectedIndex(0);
                    } else if (cekAses.equals("1")) {
                        asesmenPel.setSelectedIndex(1);
                    } else if (cekAses.equals("2")) {
                        asesmenPel.setSelectedIndex(2);
                    } else if (cekAses.equals("3")) {
                        asesmenPel.setSelectedIndex(3);
                    } else if (cekAses.equals("4")) {
                        asesmenPel.setSelectedIndex(4);
                    } else if (cekAses.equals("5")) {
                        asesmenPel.setSelectedIndex(5);
                    }                   
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rskelengkapanBPJS != null) {
                    rskelengkapanBPJS.close();
                }

                if (pskelengkapanBPJS != null) {
                    pskelengkapanBPJS.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private void getDataKunjungan() {
        SEPkontrol = "";        
        if (tbRiwayat.getSelectedRow() != -1) {
            SEPkontrol = tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString();            
        }
    }
    
    public void tampilKunjungan() {
        Valid.tabelKosong(tabMode5);
        try {
            ps3 = koneksi.prepareStatement("SELECT r.no_rkm_medis, date_format(r.tgl_registrasi,'%d-%m-%Y') tglReg, if(r.status_lanjut='Ralan','R. Jalan','R. Inap') status_lanjut, "
                    + "p.nm_poli, pj.png_jawab, ifnull(bs.no_sep,'-') no_sep, ifnull(bs.no_rujukan,'-') no_rujukan, r.kd_pj, ifnull(bs.jkel,'-') jkel, "
                    + "ifnull(bs.noskdp,'-') noskdp, ifnull(bs.urutan_sep,'-') urutan_sep, r.no_rawat, bs.no_kartu,bs.nama_pasien, bs.tanggal_lahir, bs.diagawal, "
                    + "bs.nmdiagnosaawal FROM reg_periksa r INNER JOIN poliklinik p ON p.kd_poli=r.kd_poli INNER JOIN penjab pj ON pj.kd_pj=r.kd_pj "
                    + "LEFT JOIN bridging_sep bs ON bs.no_rawat=r.no_rawat WHERE r.no_rkm_medis ='" + TNoRM.getText() + "' ORDER BY r.tgl_registrasi DESC LIMIT 10");
            try {
                rs3 = ps3.executeQuery();
                i = 1;
                while (rs3.next()) {
                    tabMode5.addRow(new String[]{
                        i + ".",
                        rs3.getString("no_rkm_medis"),
                        rs3.getString("tglReg"),
                        rs3.getString("status_lanjut"),
                        rs3.getString("nm_poli"),
                        rs3.getString("png_jawab"),
                        rs3.getString("no_sep"),
                        rs3.getString("no_rujukan"),
                        rs3.getString("noskdp"),
                        rs3.getString("urutan_sep"),
                        rs3.getString("kd_pj"),
                        rs3.getString("jkel"),                        
                        rs3.getString("no_rawat"),
                        rs3.getString("no_kartu"),
                        rs3.getString("nama_pasien"),
                        rs3.getString("tanggal_lahir"),
                        rs3.getString("nmdiagnosaawal")
                    });
                    i++;
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

        if (TNoRM.getText().trim().equals("") || tabMode5.getRowCount() < 1) {
            JOptionPane.showMessageDialog(rootPane, "Periksa lagi No. RM pasien & harus terisi dengan benar...!!!");
        }
    }
    
    private void cekHariLibur() {
        label_hari.setText("-");
        
        if (Sequel.cariIsi("select ifnull(tgl_libur,'') from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'").equals("")) {
            label_hari.setText("Kalender/penanggalan/hari normal seperti biasa.");
            label_hari.setForeground(Color.BLACK);
        } else {
            label_hari.setText(Sequel.cariIsi("select keterangan from hari_libur where tgl_libur='" + Valid.SetTgl(TanggalPeriksa.getSelectedItem() + "") + "'"));
            label_hari.setForeground(Color.RED);
        }
    }
    
    private void tampilCekFinger() {
        try {
            if (TNoRM.getText().equals("")) {
                loadURL("");
                initComponents2();
            } else {
                if (kdpnj.getText().equals("B01") || (kdpnj.getText().equals("A03"))) {
                    loadURL(link + "cekfp/?cari=" + TNoRM.getText());
                    initComponents2();
                } else {
                    loadURL("");
                    initComponents2();
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
        } 
    }
    
    private void initComponents2() {           
        panel.add(jfxPanel, BorderLayout.CENTER);        
        PanelContent.setLayout(new BorderLayout());
        PanelContent.add(panel);
    }
    
    public void loadURL(String url) {
        try {            
            createScene();
        } catch (Exception e) {
        }

        Platform.runLater(() -> {
            try {
                engine.load(url);
            } catch (Exception exception) {
                engine.load(url);
            }
        });
    }
    
    private void createScene() {   
        Platform.runLater(new Runnable() {

            public void run() {
                WebView view = new WebView();
                
                engine = view.getEngine();
                engine.setJavaScriptEnabled(true);
                
                engine.setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {
                    @Override
                    public WebEngine call(PopupFeatures p) {
                        Stage stage = new Stage(StageStyle.TRANSPARENT);
                        return view.getEngine();
                    }
                });
                
                engine.getLoadWorker().exceptionProperty().addListener((ObservableValue<? extends Throwable> o, Throwable old, final Throwable value) -> {
                    if (engine.getLoadWorker().getState() == FAILED) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(
                                    panel,
                                    (value != null) ?
                                            engine.getLocation() + "\n" + value.getMessage() :
                                            engine.getLocation() + "\nUnexpected Video.",
                                    "Loading Video...",
                                    JOptionPane.ERROR_MESSAGE);
                        });
                    }
                });                
                
                engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
                    @Override
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            try {
                                if (engine.getLocation().contains(link + "cekfp/?cari=" + TNoRM.getText())) {
                                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                                    engine.executeScript("history.back()");
                                    setCursor(Cursor.getDefaultCursor());
                                } else if (engine.getLocation().contains(link + "cekfp/?cari=" + TNoRM.getText())) {
                                    dispose();
                                }
                            } catch (Exception ex) {
                                System.out.println("Notifikasi : "+ex);
                            }
                        } 
                    }
                });
                
                jfxPanel.setScene(new Scene(view));
            }
        });
    }
}
