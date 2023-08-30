/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgReg.java
 *
 * Created on Jun 8, 2010, choose Tools | Templates
 * and open the template in10:28:56 PM
 */
package simrskhanza;
import bridging.BPJSApi;
import inventory.DlgResepObat;
import inventory.DlgPemberianObat;
import bridging.BPJSDataSEP;
import bridging.BPJSSuratKontrol;
import bridging.CoronaPasien;
import bridging.InhealthDataSJP;
import bridging.SisruteRujukanKeluar;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.BackgroundMusic;
import laporan.DlgDiagnosaPenyakit;
import laporan.DlgFrekuensiPenyakitRalan;
import keuangan.DlgBilingRalan;
import fungsi.WarnaTable;
import fungsi.batasInput;
import grafikanalisa.grafikperiksaperagama;
import grafikanalisa.grafikperiksaperbulan;
import grafikanalisa.grafikperiksaperdokter;
import grafikanalisa.grafikperiksaperhari;
import grafikanalisa.grafikperiksaperjk;
import grafikanalisa.grafikperiksaperpekerjaan;
import grafikanalisa.grafikperiksaperpoli;
import grafikanalisa.grafikperiksapertahun;
import grafikanalisa.grafiksql;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.DlgLhtPiutang;
import org.apache.poi.ss.formula.functions.Now;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class DlgReg extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    public DlgPasien pasien = new DlgPasien(null, false);
    public DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgCariDokter2 dokter2 = new DlgCariDokter2(null, false);
    public DlgCariDokter dokter3 = new DlgCariDokter(null, false);
    public DlgCariDokter2 dokter4 = new DlgCariDokter2(null, false);
    private DlgCariPoli poli = new DlgCariPoli(null, false);
    private DlgCariPoli2 poli2 = new DlgCariPoli2(null, false);
    private DlgCariPoli poli3 = new DlgCariPoli(null, false);
    private DlgCariPoli2 poli4 = new DlgCariPoli2(null, false);
    public DlgRujukMasuk rujukmasuk = new DlgRujukMasuk(null, false);
    public DlgBahasa bahasa = new DlgBahasa(null, false);
    public DlgSuku suku = new DlgSuku(null, false);
    private BPJSApi api = new BPJSApi();
    private PreparedStatement ps, ps2, ps3, pscaripiutang;
    private Properties prop = new Properties();
    private ResultSet rs, rs2;
    private int pilihan = 0, i = 0, cekRujuk = 0, cekSEP = 0, cekjampersal = 0, cekjamkesda = 0,
            diagnosa_cek = 0, cekUmur = 0, x = 0, panggilan = 0, nomorpasien = 0, hasilantrian = 0,
            cekPangBPJS = 0, cekPangUmum = 0, cekPangLB = 0, cekPangInap = 0;
    private Date cal = new Date();
    private String nosisrute = "", URUTNOREG = "", alamatperujuk = "-", URL = "", utc = "", noka = "", wktPanggil = "", wktAmbilNomor = "", panggilanFix = "",
            aktifjadwal = "", IPPRINTERTRACER = "", umur = "0", sttsumur = "Th", cekSEPboking = "", diagnosa_ok = "", noPangAkhir = "", noRwNew = "",
            tglDaftar = "", tglnoRW = "", sttsumur1 = "", validasiregistrasi = Sequel.cariIsi("select wajib_closing_kasir from set_validasi_registrasi");
    private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd");
    private String[] urut = {"", "./suara/satu.mp3", "./suara/dua.mp3", "./suara/tiga.mp3", "./suara/empat.mp3",
        "./suara/lima.mp3", "./suara/enam.mp3", "./suara/tujuh.mp3", "./suara/delapan.mp3",
        "./suara/sembilan.mp3", "./suara/sepuluh.mp3", "./suara/sebelas.mp3"};
    private BackgroundMusic music;
    private char ESC = 27;
    // ganti kertas
    private char[] FORM_FEED = {12};
    // reset setting
    private char[] RESET = {ESC, '@'};
    // huruf tebal diaktifkan
    private char[] BOLD_ON = {ESC, 'E'};
    // huruf tebal dimatikan
    private char[] BOLD_OFF = {ESC, 'F'};
    // huruf miring diaktifkan
    private char[] ITALIC_ON = {ESC, '4'};
    // huruf miring dimatikan
    private char[] ITALIC_OFF = {ESC, '5'};
    // mode draft diaktifkan
    private char[] MODE_DRAFT = {ESC, 'x', 0};
    private char[] MODE_NLQ = {ESC, 'x', 1};
    // font Roman (halaman 47)
    private char[] FONT_ROMAN = {ESC, 'k', 0};
    // font Sans serif
    private char[] FONT_SANS_SERIF = {ESC, 'k', 1};
    // font size (halaman 49)
    private char[] SIZE_5_CPI = {ESC, 'W', '1', ESC, 'P'};
    private char[] SIZE_6_CPI = {ESC, 'W', '1', ESC, 'M'};
    private char[] SIZE_10_CPI = {ESC, 'P'};
    private char[] SIZE_12_CPI = {ESC, 'M'};
    //font height
    private char[] HEIGHT_NORMAL = {ESC, 'w', '0'};
    private char[] HEIGHT_DOUBLE = {ESC, 'w', '1'};
    // double strike (satu dot dicetak 2 kali)
    private char[] DOUBLE_STRIKE_ON = {ESC, 'G'};
    private char[] DOUBLE_STRIKE_OFF = {ESC, 'H'};
    // http://www.berklix.com/~jhs/standards/escapes.epson
    // condensed (huruf kurus)
    private char[] CONDENSED_ON = {15};
    private char[] CONDENSED_OFF = {18};
    // condensed (huruf gemuk)
    private char[] ENLARGED_ON = {(char) 14};
    private char[] ENLARGED_OFF = {(char) 20};
    // line spacing
    private char[] SPACING_9_72 = {ESC, '0'};
    private char[] SPACING_7_72 = {ESC, '1'};
    private char[] SPACING_12_72 = {ESC, '2'};
    // set unit for margin setting
    private char[] UNIT_1_360 = {ESC, 40, 'U', '1', '0'};
    // move vertical print position
    private char[] VERTICAL_PRINT_POSITION = {ESC, 'J', '1'};
    public Timer tRefreshAntri;

    /**
     * Creates new form DlgReg
     *
     * @param parent
     * @param modal
     */
    public DlgReg(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode = new DefaultTableModel(null, new Object[]{
            "P", "No. Reg", "No. Rawat", "Tanggal", "Jam", "Kd.Dokter", "Dokter Dituju", "Nomer RM", "Nama Pasien", "J.K.",
            "Umur", "Poliklinik", "Jenis Bayar", "No. SEP BPJS", "Penanggung Jawab", "Alamat P.J.", "Hubungan P.J.",
            "Biaya Regristrasi", "Jns. Pasien", "No.Telp", "Stts. Transaksi", "Petugas TPPRJ"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbregistrasiRalan.setModel(tabMode);
        tbregistrasiRalan.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbregistrasiRalan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 22; i++) {
            TableColumn column = tbregistrasiRalan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(50);
            } else if (i == 2) {
                column.setPreferredWidth(120);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {//sembunyi                
                //column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(70);
            } else if (i == 8) {
                column.setPreferredWidth(200);
            } else if (i == 9) {
                column.setPreferredWidth(30);
            } else if (i == 10) {
                column.setPreferredWidth(50);
            } else if (i == 11) {
                column.setPreferredWidth(140);
            } else if (i == 12) {
                column.setPreferredWidth(140);
            } else if (i == 13) {
                column.setPreferredWidth(130);
            } else if (i == 14) {
                column.setPreferredWidth(110);
            } else if (i == 15) {
//                column.setPreferredWidth(200);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
//                column.setPreferredWidth(90);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {//sembunyi
                //column.setPreferredWidth(100);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setPreferredWidth(70);
            } else if (i == 19) {
                column.setPreferredWidth(85);
            } else if (i == 20) {
                column.setPreferredWidth(140);
            } else if (i == 21) {
                column.setPreferredWidth(170);
            }
        }
        tbregistrasiRalan.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new String[]{
            "Jenis Reset Nomor", "Nomor Terakhir", "Tgl. Reset", "Jam Reset", "tglreset"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbrestorAntrian.setModel(tabMode1);
        tbrestorAntrian.setPreferredScrollableViewportSize(new Dimension(800, 800));
        tbrestorAntrian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbrestorAntrian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbrestorAntrian.setDefaultRenderer(Object.class, new WarnaTable());

        TNoReg.setDocument(new batasInput((byte) 8).getKata(TNoReg));
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TNoRM.setDocument(new batasInput((byte) 15).getKata(TNoRM));
        AsalRujukan.setDocument(new batasInput((byte) 60).getKata(AsalRujukan));
        kddokter.setDocument(new batasInput((byte) 20).getKata(kddokter));
        kdpnj.setDocument(new batasInput((byte) 3).getKata(kdpnj));
        TPngJwb.setDocument(new batasInput((byte) 30).getKata(TPngJwb));
        kdpoli.setDocument(new batasInput((byte) 5).getKata(kdpoli));
        THbngn.setDocument(new batasInput((byte) 20).getKata(THbngn));
        TAlmt.setDocument(new batasInput((byte) 60).getKata(TAlmt));
        TBiaya.setDocument(new batasInput((byte) 13).getOnlyAngka(TBiaya));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        CrPoli.setDocument(new batasInput((byte) 100).getKata(CrPoli));
        CrDokter.setDocument(new batasInput((byte) 100).getKata(CrDokter));
        Tumur.setDocument(new batasInput((byte) 3).getOnlyAngka(Tumur));
        
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    tampilAwal();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    tampilAwal();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    tampilAwal();
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
                if (akses.getform().equals("DlgReg")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TNoID.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                        TNoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                        JnsnoID.setSelectedIndex(0);
                        kdsuku.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 26).toString());
                        kdbahasa.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 27).toString());
                        Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                        Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());
                        isPas();
                        isNumber();
                    }
                    TNoID.requestFocus();
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
                if (akses.getform().equals("DlgReg")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
                if (akses.getform().equals("DlgReg")) {
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
                if (akses.getform().equals("DlgReg")) {
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
                if (akses.getform().equals("DlgReg")) {
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
                if (akses.getform().equals("DlgReg")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        bahasa.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            isNumber();
                            kddokter.requestFocus();
                        } else if (pilihan == 2) {
                            CrDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            CrDokter.requestFocus();
                            tampilAwal();
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

        dokter2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (dokter2.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kddokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 1).toString());
                            isNumber();
                            kddokter.requestFocus();
                        } else if (pilihan == 2) {
                            CrDokter.setText(dokter2.getTable().getValueAt(dokter2.getTable().getSelectedRow(), 1).toString());
                            CrDokter.requestFocus();
                            tampilAwal();
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

        dokter3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (dokter3.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kdDokterRujuk.setText(dokter3.getTable().getValueAt(dokter3.getTable().getSelectedRow(), 0).toString());
                            nmDokterRujuk.setText(dokter3.getTable().getValueAt(dokter3.getTable().getSelectedRow(), 1).toString());
                        } else if (pilihan == 2) {
                            tampilAwal();
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

        dokter4.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (dokter4.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            kdDokterRujuk.setText(dokter4.getTable().getValueAt(dokter4.getTable().getSelectedRow(), 0).toString());
                            nmDokterRujuk.setText(dokter4.getTable().getValueAt(dokter4.getTable().getSelectedRow(), 1).toString());
                        } else if (pilihan == 2) {
                            tampilAwal();
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
                if (akses.getform().equals("DlgReg")) {
                    if (poli.getTable().getSelectedRow() != -1) {
                        if ((akses.getkode().equals("PP23"))) {
                            if (!poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString().equals("RAD")) {
                                kdpoli.setText("");
                                TPoli.setText("");
                                TBiaya.setText("");
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");

                            } else {
                                if (pilihan == 1) {
                                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                    switch (TStatus.getText()) {
                                        case "Baru":
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                            break;
                                        case "Lama":
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
                                            break;
                                        default:
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                            break;
                                    }
                                    isNumber();
                                    kdpoli.requestFocus();
                                } else if (pilihan == 2) {
                                    CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                    CrPoli.requestFocus();
                                    tampilAwal();
                                }
                            }
                        } else if ((akses.getkode().equals("PP24"))) {
                            if (!poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString().equals("LAA") && !poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString().equals("LAB")) {
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");
                                kdpoli.setText("");
                                TPoli.setText("");
                                TBiaya.setText("");
                            } else {
                                if (pilihan == 1) {
                                    kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                                    TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                    switch (TStatus.getText()) {
                                        case "Baru":
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                            break;
                                        case "Lama":
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
                                            break;
                                        default:
                                            TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                            break;
                                    }
                                    isNumber();
                                    kdpoli.requestFocus();
                                } else if (pilihan == 2) {
                                    CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                    CrPoli.requestFocus();
                                    tampilAwal();
                                }
                            }

                        } else {
                            if (pilihan == 1) {
                                kdpoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 0).toString());
                                TPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                switch (TStatus.getText()) {
                                    case "Baru":
                                        TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                        break;
                                    case "Lama":
                                        TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 3).toString());
                                        break;
                                    default:
                                        TBiaya.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 2).toString());
                                        break;
                                }
                                isNumber();
                                kdpoli.requestFocus();
                            } else if (pilihan == 2) {
                                CrPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(), 1).toString());
                                CrPoli.requestFocus();
                                tampilAwal();
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

        poli2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (poli2.getTable().getSelectedRow() != -1) {
                        if ((akses.getkode().equals("PP23"))) {
                            if (!poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString().equals("RAD")) {
                                kdpoli.setText("");
                                TPoli.setText("");
                                TBiaya.setText("");
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");

                            } else {
                                if (pilihan == 1) {
                                    kdpoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString());
                                    TPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                    switch (TStatus.getText()) {
                                        case "Baru":
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                            break;
                                        case "Lama":
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 3).toString());
                                            break;
                                        default:
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                            break;
                                    }
                                    isNumber();
                                    kdpoli.requestFocus();
                                } else if (pilihan == 2) {
                                    CrPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                    CrPoli.requestFocus();
                                    tampilAwal();
                                }
                            }
                        } else if ((akses.getkode().equals("PP24"))) {
                            if (!poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString().equals("LAA") && !poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString().equals("LAB")) {
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");
                                kdpoli.setText("");
                                TPoli.setText("");
                                TBiaya.setText("");
                            } else {
                                if (pilihan == 1) {
                                    kdpoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString());
                                    TPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                    switch (TStatus.getText()) {
                                        case "Baru":
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                            break;
                                        case "Lama":
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 3).toString());
                                            break;
                                        default:
                                            TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                            break;
                                    }
                                    isNumber();
                                    kdpoli.requestFocus();
                                } else if (pilihan == 2) {
                                    CrPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                    CrPoli.requestFocus();
                                    tampilAwal();
                                }
                            }

                        } else {
                            if (pilihan == 1) {
                                kdpoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 0).toString());
                                TPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                switch (TStatus.getText()) {
                                    case "Baru":
                                        TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                        break;
                                    case "Lama":
                                        TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 3).toString());
                                        break;
                                    default:
                                        TBiaya.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 2).toString());
                                        break;
                                }
                                isNumber();
                                kdpoli.requestFocus();
                            } else if (pilihan == 2) {
                                CrPoli.setText(poli2.getTable().getValueAt(poli2.getTable().getSelectedRow(), 1).toString());
                                CrPoli.requestFocus();
                                tampilAwal();
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

        poli3.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (poli3.getTable().getSelectedRow() != -1) {
                        if ((akses.getkode().equals("PP23"))) {
                            if (!poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString().equals("RAD")) {
                                kdpoliRujuk.setText("");
                                nmpoliRujuk.setText("");
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");
                            } else {
                                if (pilihan == 1) {
                                    kdpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString());
                                    nmpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 1).toString());
                                } else if (pilihan == 2) {
                                    tampilAwal();
                                }
                            }
                        } else if ((akses.getkode().equals("PP24"))) {
                            if (!poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString().equals("LAA")
                                    && !poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString().equals("LAB")) {
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");
                                kdpoliRujuk.setText("");
                                nmpoliRujuk.setText("");
                            } else {
                                if (pilihan == 1) {
                                    kdpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString());
                                    nmpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 1).toString());
                                } else if (pilihan == 2) {
                                    tampilAwal();
                                }
                            }

                        } else {
                            if (pilihan == 1) {
                                kdpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 0).toString());
                                nmpoliRujuk.setText(poli3.getTable().getValueAt(poli3.getTable().getSelectedRow(), 1).toString());
                            } else if (pilihan == 2) {
                                tampilAwal();
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

        poli4.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (poli4.getTable().getSelectedRow() != -1) {
                        if ((akses.getkode().equals("PP23"))) {
                            if (!poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString().equals("RAD")) {
                                kdpoliRujuk.setText("");
                                nmpoliRujuk.setText("");
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");

                            } else {
                                if (pilihan == 1) {
                                    kdpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString());
                                    nmpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 1).toString());
                                } else if (pilihan == 2) {
                                    tampilAwal();
                                }
                            }
                        } else if ((akses.getkode().equals("PP24"))) {
                            if (!poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString().equals("LAA")
                                    && !poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString().equals("LAB")) {
                                JOptionPane.showMessageDialog(null, "Untuk pelayanan rawat jalan lainnya, silakan daftar di loket");
                                kdpoliRujuk.setText("");
                                nmpoliRujuk.setText("");
                            } else {
                                if (pilihan == 1) {
                                    kdpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString());
                                    nmpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 1).toString());
                                } else if (pilihan == 2) {
                                    tampilAwal();
                                }
                            }

                        } else {
                            if (pilihan == 1) {
                                kdpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 0).toString());
                                nmpoliRujuk.setText(poli4.getTable().getValueAt(poli4.getTable().getSelectedRow(), 1).toString());
                            } else if (pilihan == 2) {
                                tampilAwal();
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

        pasien.kab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (pasien.kab.getTable().getSelectedRow() != -1) {
                        Kabupaten2.setText(pasien.kab.getTable().getValueAt(pasien.kab.getTable().getSelectedRow(), 0).toString());
                    }
                    Kabupaten2.requestFocus();
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

        pasien.kec.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (pasien.kec.getTable().getSelectedRow() != -1) {
                        Kecamatan2.setText(pasien.kec.getTable().getValueAt(pasien.kec.getTable().getSelectedRow(), 0).toString());
                    }
                    Kecamatan2.requestFocus();
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

        pasien.kel.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (pasien.kel.getTable().getSelectedRow() != -1) {
                        Kelurahan2.setText(pasien.kel.getTable().getValueAt(pasien.kel.getTable().getSelectedRow(), 0).toString());
                    }
                    Kelurahan2.requestFocus();
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

        pasien.penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (pasien.penjab.getTable().getSelectedRow() != -1) {
                        kdpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 1).toString());
                        nmpnj.setText(pasien.penjab.getTable().getValueAt(pasien.penjab.getTable().getSelectedRow(), 2).toString());
                    }
                    kdpnj.requestFocus();
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

        rujukmasuk.WindowPerujuk.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgReg")) {
                    if (rujukmasuk.tbPerujuk.getSelectedRow() != -1) {
                        AsalRujukan.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 2).toString());
                        alamatperujuk = rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 3).toString();
                        kode_rujukanya.setText(rujukmasuk.tbPerujuk.getValueAt(rujukmasuk.tbPerujuk.getSelectedRow(), 4).toString());
                    }
                    AsalRujukan.requestFocus();
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
                if (akses.getform().equals("DlgReg")) {
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
            aktifjadwal = prop.getProperty("JADWALDOKTERDIREGISTRASI");
            IPPRINTERTRACER = prop.getProperty("IPPRINTERTRACER");
            URUTNOREG = prop.getProperty("URUTNOREG");
            cekRefreshAntri();
        } catch (Exception ex) {
            aktifjadwal = "";
            IPPRINTERTRACER = "";
            URUTNOREG = "";
        }

        ChkInput.setSelected(false);
        isForm();
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
        MnKartu = new javax.swing.JMenu();
        MnKartuUmum = new javax.swing.JMenuItem();
        MnKartuNonUmum = new javax.swing.JMenuItem();
        MnCetakKelengkapanInap = new javax.swing.JMenu();
        MnGelangDewasaAnak = new javax.swing.JMenu();
        MnPrinterBaru = new javax.swing.JMenuItem();
        MnPrinterLama = new javax.swing.JMenuItem();
        MnGelangBayi = new javax.swing.JMenu();
        MnPrinterBaru1 = new javax.swing.JMenuItem();
        MnPrinterBaru2 = new javax.swing.JMenuItem();
        MnPrinterLama1 = new javax.swing.JMenuItem();
        MnBarcodeRM1 = new javax.swing.JMenuItem();
        MnBarcodeRM2 = new javax.swing.JMenuItem();
        MnLabelRM1 = new javax.swing.JMenuItem();
        MnLabelRM2 = new javax.swing.JMenuItem();
        MnLabelPxRanap3 = new javax.swing.JMenu();
        MrkChampion = new javax.swing.JMenuItem();
        MrkAjp = new javax.swing.JMenuItem();
        MrkCox = new javax.swing.JMenuItem();
        MrkAlfa = new javax.swing.JMenuItem();
        MrkOlean = new javax.swing.JMenuItem();
        MrkKojico = new javax.swing.JMenuItem();
        MnLabelPxRanap1 = new javax.swing.JMenuItem();
        MnLabelPxRanap2 = new javax.swing.JMenuItem();
        MnKamarInap = new javax.swing.JMenuItem();
        MnPemeriksaan = new javax.swing.JMenu();
        MnPeriksaLab = new javax.swing.JMenuItem();
        MnPeriksaRadiologi = new javax.swing.JMenuItem();
        MnRujukan = new javax.swing.JMenu();
        MnRujukMasuk = new javax.swing.JMenuItem();
        MnRujuk = new javax.swing.JMenuItem();
        MnRujukInternal = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MnLaporanRekapKunjunganPoli = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganDokter = new javax.swing.JMenuItem();
        MnLaporanRekapJenisBayar = new javax.swing.JMenuItem();
        MnLaporanRekapRawatDarurat = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulanan = new javax.swing.JMenuItem();
        MnLaporanRekapKunjunganBulananPoli = new javax.swing.JMenuItem();
        MnLaporanRekapPenyakitRalan = new javax.swing.JMenuItem();
        MnLaporanRekapPerujuk = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ppGrafikPerpoli = new javax.swing.JMenuItem();
        ppGrafikPerpoli1 = new javax.swing.JMenuItem();
        ppGrafikPerpoli2 = new javax.swing.JMenuItem();
        ppGrafikPerdokter = new javax.swing.JMenuItem();
        ppGrafikPerdokter1 = new javax.swing.JMenuItem();
        ppGrafikPerdokter2 = new javax.swing.JMenuItem();
        ppGrafikPerJK = new javax.swing.JMenuItem();
        ppGrafikPerPekerjaan = new javax.swing.JMenuItem();
        ppGrafikPerAgama = new javax.swing.JMenuItem();
        ppGrafikPerTahun = new javax.swing.JMenuItem();
        ppGrafikPerBulan = new javax.swing.JMenuItem();
        ppGrafikPerTanggal = new javax.swing.JMenuItem();
        ppGrafikDemografi = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MnCetakSuratSehat = new javax.swing.JMenuItem();
        MnCetakSuratSehat1 = new javax.swing.JMenuItem();
        MnCetakBebasNarkoba = new javax.swing.JMenuItem();
        MnCetakSuratSakit = new javax.swing.JMenuItem();
        MnCetakSuratSakit2 = new javax.swing.JMenuItem();
        MnCetakSuratSakit3 = new javax.swing.JMenuItem();
        MnCetakRegister = new javax.swing.JMenuItem();
        MnCetakRegister1 = new javax.swing.JMenuItem();
        MnPersetujuanMedis = new javax.swing.JMenuItem();
        MnBuktiPelayananRalan = new javax.swing.JMenuItem();
        MnLembarCasemix = new javax.swing.JMenuItem();
        MnSPBK = new javax.swing.JMenuItem();
        MnJKRA = new javax.swing.JMenuItem();
        MnGChalaman1 = new javax.swing.JMenuItem();
        MnGChalaman2 = new javax.swing.JMenuItem();
        MnManualSEPBPJS = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MnCheckList = new javax.swing.JMenuItem();
        MnCheckList1 = new javax.swing.JMenuItem();
        MnCheckList2 = new javax.swing.JMenuItem();
        MnCheckList3 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        MnCheckList4 = new javax.swing.JMenuItem();
        MnCheckList5 = new javax.swing.JMenuItem();
        MnCheckList6 = new javax.swing.JMenuItem();
        MnCheckList7 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        MnLabelTracker = new javax.swing.JMenuItem();
        MnLabelTracker1 = new javax.swing.JMenuItem();
        MnLabelTracker2 = new javax.swing.JMenuItem();
        MnLabelTracker3 = new javax.swing.JMenuItem();
        MnBarcode = new javax.swing.JMenuItem();
        MnGelang1 = new javax.swing.JMenuItem();
        MnGelang2 = new javax.swing.JMenuItem();
        MnGelang3 = new javax.swing.JMenuItem();
        MnGelang4 = new javax.swing.JMenuItem();
        MnGelang5 = new javax.swing.JMenuItem();
        MnGelang6 = new javax.swing.JMenuItem();
        MnStatus = new javax.swing.JMenu();
        ppBerkas = new javax.swing.JMenuItem();
        MnSudah = new javax.swing.JMenuItem();
        MnBelum = new javax.swing.JMenuItem();
        MnBatal = new javax.swing.JMenuItem();
        MnBridging = new javax.swing.JMenu();
        ppPasienCorona = new javax.swing.JMenuItem();
        ppCekFingerPrin = new javax.swing.JMenuItem();
        MnSEP = new javax.swing.JMenuItem();
        MnSJP = new javax.swing.JMenuItem();
        ppSuratKontrol = new javax.swing.JMenuItem();
        MnJAMKESDA = new javax.swing.JMenuItem();
        MnJAMPERSAL = new javax.swing.JMenuItem();
        MnRujukSisrute = new javax.swing.JMenuItem();
        MnPetugasReg = new javax.swing.JMenuItem();
        MnBilling = new javax.swing.JMenuItem();
        MnGantiTglReg = new javax.swing.JMenuItem();
        MnGantiUmurReg = new javax.swing.JMenuItem();
        MnHemodialisa = new javax.swing.JMenuItem();
        ppRiwayat = new javax.swing.JMenuItem();
        ppCatatanPasien = new javax.swing.JMenuItem();
        buttonGroup2 = new javax.swing.ButtonGroup();
        DlgSakit = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelBiasa2 = new widget.PanelBiasa();
        TglSakit1 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        BtnPrint2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        jLabel32 = new widget.Label();
        TglSakit2 = new widget.Tanggal();
        jLabel33 = new widget.Label();
        lmsakit = new widget.TextBox();
        DlgDemografi = new javax.swing.JDialog();
        internalFrame4 = new widget.InternalFrame();
        panelBiasa3 = new widget.PanelBiasa();
        BtnPrint3 = new widget.Button();
        BtnKeluar3 = new widget.Button();
        Kelurahan2 = new widget.TextBox();
        btnKel = new widget.Button();
        Kecamatan2 = new widget.TextBox();
        btnKec = new widget.Button();
        Kabupaten2 = new widget.TextBox();
        btnKab = new widget.Button();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        BtnPrint4 = new widget.Button();
        DlgJamkesda = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        BtnCloseIn1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        jLabel25 = new widget.Label();
        noSrt = new widget.TextBox();
        jLabel26 = new widget.Label();
        TglSurat = new widget.Tanggal();
        BtnGantijkd = new widget.Button();
        BtnCtkJkd = new widget.Button();
        DlgJampersal = new javax.swing.JDialog();
        internalFrame7 = new widget.InternalFrame();
        BtnCloseIn2 = new widget.Button();
        BtnSimpan2 = new widget.Button();
        jLabel27 = new widget.Label();
        noSrt1 = new widget.TextBox();
        jLabel28 = new widget.Label();
        TglSurat1 = new widget.Tanggal();
        BtnGantijmp = new widget.Button();
        BtnCtkJmp = new widget.Button();
        DlgTanggalReg = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnCloseIn3 = new widget.Button();
        jLabel29 = new widget.Label();
        TglReg = new widget.Tanggal();
        BtnGantiTgl = new widget.Button();
        DlgRegRujukInternal = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        panelBiasa5 = new widget.PanelBiasa();
        BtnSimpanRujuk = new widget.Button();
        BtnKeluar5 = new widget.Button();
        jLabel40 = new widget.Label();
        norwPerujuk = new widget.TextBox();
        jLabel41 = new widget.Label();
        kdpoliRujuk = new widget.TextBox();
        nmpoliRujuk = new widget.TextBox();
        jLabel42 = new widget.Label();
        kdDokterRujuk = new widget.TextBox();
        nmDokterRujuk = new widget.TextBox();
        BtnPoliRujuk = new widget.Button();
        BtnDokterRujuk = new widget.Button();
        jLabel43 = new widget.Label();
        poliPerujuk = new widget.TextBox();
        jLabel44 = new widget.Label();
        drPerujuk = new widget.TextBox();
        DlgGantiUmurReg = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        jLabel37 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel39 = new widget.Label();
        Ttgl_lahir = new widget.Tanggal();
        BtnGantiUmur = new widget.Button();
        jLabel46 = new widget.Label();
        cmbSttsUmur = new widget.ComboBox();
        Kd2 = new widget.TextBox();
        NoBalasan = new widget.TextBox();
        sepJkd = new widget.TextBox();
        sepJmp = new widget.TextBox();
        NoPeserta = new widget.TextBox();
        rmMati = new widget.TextBox();
        jamMati = new widget.TextBox();
        tglMati = new widget.TextBox();
        TNoRM = new widget.TextBox();
        kode_rujukanya = new widget.TextBox();
        cekPasien = new widget.TextBox();
        kdsuku = new widget.TextBox();
        kdbahasa = new widget.TextBox();
        prosesTombol = new widget.ProgressBar();
        internalFrame1 = new widget.InternalFrame();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        jLabel4 = new widget.Label();
        TDokter = new widget.TextBox();
        TNoRw = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel20 = new widget.Label();
        jLabel21 = new widget.Label();
        TPngJwb = new widget.TextBox();
        TNoID = new widget.TextBox();
        TNoReg = new widget.TextBox();
        TAlmt = new widget.TextBox();
        BtnPasien = new widget.Button();
        TPasien = new widget.TextBox();
        jLabel22 = new widget.Label();
        THbngn = new widget.TextBox();
        jLabel19 = new widget.Label();
        TPoli = new widget.TextBox();
        TBiaya = new widget.TextBox();
        kddokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        kdpoli = new widget.TextBox();
        BtnUnit = new widget.Button();
        jLabel30 = new widget.Label();
        TStatus = new widget.TextBox();
        jLabel18 = new widget.Label();
        kdpnj = new widget.TextBox();
        nmpnj = new widget.TextBox();
        btnPenjab = new widget.Button();
        jLabel23 = new widget.Label();
        AsalRujukan = new widget.TextBox();
        btnPenjab1 = new widget.Button();
        ChkTracker = new widget.CekBox();
        JnsnoID = new widget.ComboBox();
        jLabel5 = new widget.Label();
        jLabel7 = new widget.Label();
        jLabel11 = new widget.Label();
        nmsuku = new widget.TextBox();
        BtnSuku = new widget.Button();
        nmbahasa = new widget.TextBox();
        BtnBahasa = new widget.Button();
        jLabel12 = new widget.Label();
        label_pesan = new widget.TextArea();
        tulisan_tanggal = new widget.Label();
        ChkInput = new widget.CekBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame17 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbregistrasiRalan = new widget.Table();
        panelGlass12 = new widget.panelisi();
        panelGlass8 = new widget.panelisi();
        jLabel14 = new widget.Label();
        CrDokter = new widget.TextBox();
        BtnSeek3 = new widget.Button();
        jLabel16 = new widget.Label();
        CrPoli = new widget.TextBox();
        BtnSeek4 = new widget.Button();
        panelGlass7 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        panelGlass6 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnEdit = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        jLabel9 = new widget.Label();
        jLabel38 = new widget.Label();
        loket1 = new widget.RadioButton();
        loket2 = new widget.RadioButton();
        loket3 = new widget.RadioButton();
        loket4 = new widget.RadioButton();
        loket5 = new widget.RadioButton();
        loket6 = new widget.RadioButton();
        jLabel45 = new widget.Label();
        statusOperator = new widget.Label();
        loketRalan = new widget.CekBox();
        loketRanap = new widget.CekBox();
        Scroll1 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        internalFrame25 = new widget.InternalFrame();
        nobpjs = new widget.Label();
        jLabel51 = new widget.Label();
        BtnPangBPJS = new widget.Button();
        BtnUlangPangBPJS = new widget.Button();
        jLabel52 = new widget.Label();
        internalFrame26 = new widget.InternalFrame();
        noumum = new widget.Label();
        BtnPangUmum = new widget.Button();
        BtnUlangPangUmum = new widget.Button();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        jPanel2 = new javax.swing.JPanel();
        internalFrame27 = new widget.InternalFrame();
        nolansia = new widget.Label();
        BtnPangLansia = new widget.Button();
        BtnUlangPangLansia = new widget.Button();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        internalFrame28 = new widget.InternalFrame();
        noinap = new widget.Label();
        BtnPangInap = new widget.Button();
        BtnUlangPangInap = new widget.Button();
        jLabel57 = new widget.Label();
        jLabel58 = new widget.Label();
        panelGlass14 = new widget.panelisi();
        BtnSimpanOperator = new widget.Button();
        BtnSimpanOperator1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        internalFrame11 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame20 = new widget.InternalFrame();
        infobpjs1 = new widget.Label();
        infobpjs2 = new widget.Label();
        internalFrame21 = new widget.InternalFrame();
        infoumum1 = new widget.Label();
        infoumum2 = new widget.Label();
        internalFrame22 = new widget.InternalFrame();
        infolb1 = new widget.Label();
        infolb2 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        inforanap1 = new widget.Label();
        inforanap2 = new widget.Label();
        internalFrame12 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel61 = new widget.Label();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        jnsReset = new widget.TextBox();
        noTerakhir = new widget.TextBox();
        tglReset = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbrestorAntrian = new widget.Table();
        panelGlass11 = new widget.panelisi();
        jLabel59 = new widget.Label();
        tglA = new widget.Tanggal();
        jLabel60 = new widget.Label();
        tglB = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnReset = new widget.Button();
        BtnKeluar6 = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnKartu.setBackground(new java.awt.Color(242, 242, 242));
        MnKartu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartu.setText("Kartu Berobat Pasien (KIB)");
        MnKartu.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartu.setIconTextGap(5);
        MnKartu.setName("MnKartu"); // NOI18N
        MnKartu.setOpaque(true);
        MnKartu.setPreferredSize(new java.awt.Dimension(220, 26));

        MnKartuUmum.setBackground(new java.awt.Color(242, 242, 242));
        MnKartuUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuUmum.setText("Pasien UMUM");
        MnKartuUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuUmum.setIconTextGap(5);
        MnKartuUmum.setName("MnKartuUmum"); // NOI18N
        MnKartuUmum.setPreferredSize(new java.awt.Dimension(140, 26));
        MnKartuUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuUmum);

        MnKartuNonUmum.setBackground(new java.awt.Color(242, 242, 242));
        MnKartuNonUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKartuNonUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKartuNonUmum.setText("Pasien NON UMUM");
        MnKartuNonUmum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKartuNonUmum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKartuNonUmum.setIconTextGap(5);
        MnKartuNonUmum.setName("MnKartuNonUmum"); // NOI18N
        MnKartuNonUmum.setPreferredSize(new java.awt.Dimension(140, 26));
        MnKartuNonUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKartuNonUmumActionPerformed(evt);
            }
        });
        MnKartu.add(MnKartuNonUmum);

        jPopupMenu1.add(MnKartu);

        MnCetakKelengkapanInap.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakKelengkapanInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakKelengkapanInap.setText("Cetak Kelengkapan Opname");
        MnCetakKelengkapanInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakKelengkapanInap.setName("MnCetakKelengkapanInap"); // NOI18N
        MnCetakKelengkapanInap.setOpaque(true);
        MnCetakKelengkapanInap.setPreferredSize(new java.awt.Dimension(220, 26));

        MnGelangDewasaAnak.setBackground(new java.awt.Color(242, 242, 242));
        MnGelangDewasaAnak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangDewasaAnak.setText("Gelang DEWASA & ANAK-ANAK");
        MnGelangDewasaAnak.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangDewasaAnak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangDewasaAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangDewasaAnak.setIconTextGap(5);
        MnGelangDewasaAnak.setName("MnGelangDewasaAnak"); // NOI18N
        MnGelangDewasaAnak.setOpaque(true);
        MnGelangDewasaAnak.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru.setBackground(new java.awt.Color(242, 242, 242));
        MnPrinterBaru.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru.setText("Printer BARU");
        MnPrinterBaru.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru.setIconTextGap(5);
        MnPrinterBaru.setName("MnPrinterBaru"); // NOI18N
        MnPrinterBaru.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaruActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterBaru);

        MnPrinterLama.setBackground(new java.awt.Color(242, 242, 242));
        MnPrinterLama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama.setText("Printer LAMA");
        MnPrinterLama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama.setIconTextGap(5);
        MnPrinterLama.setName("MnPrinterLama"); // NOI18N
        MnPrinterLama.setPreferredSize(new java.awt.Dimension(125, 26));
        MnPrinterLama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLamaActionPerformed(evt);
            }
        });
        MnGelangDewasaAnak.add(MnPrinterLama);

        MnCetakKelengkapanInap.add(MnGelangDewasaAnak);

        MnGelangBayi.setBackground(new java.awt.Color(242, 242, 242));
        MnGelangBayi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelangBayi.setText("Gelang BAYI");
        MnGelangBayi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelangBayi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelangBayi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelangBayi.setIconTextGap(5);
        MnGelangBayi.setName("MnGelangBayi"); // NOI18N
        MnGelangBayi.setOpaque(true);
        MnGelangBayi.setPreferredSize(new java.awt.Dimension(210, 26));

        MnPrinterBaru1.setBackground(new java.awt.Color(242, 242, 242));
        MnPrinterBaru1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru1.setText("Printer BARU Versi 1");
        MnPrinterBaru1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru1.setIconTextGap(5);
        MnPrinterBaru1.setName("MnPrinterBaru1"); // NOI18N
        MnPrinterBaru1.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterBaru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaru1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterBaru1);

        MnPrinterBaru2.setBackground(new java.awt.Color(242, 242, 242));
        MnPrinterBaru2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterBaru2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterBaru2.setText("Printer BARU Versi 2");
        MnPrinterBaru2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterBaru2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterBaru2.setIconTextGap(5);
        MnPrinterBaru2.setName("MnPrinterBaru2"); // NOI18N
        MnPrinterBaru2.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterBaru2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterBaru2ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterBaru2);

        MnPrinterLama1.setBackground(new java.awt.Color(242, 242, 242));
        MnPrinterLama1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPrinterLama1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnPrinterLama1.setText("Printer LAMA");
        MnPrinterLama1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPrinterLama1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPrinterLama1.setIconTextGap(5);
        MnPrinterLama1.setName("MnPrinterLama1"); // NOI18N
        MnPrinterLama1.setPreferredSize(new java.awt.Dimension(150, 26));
        MnPrinterLama1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPrinterLama1ActionPerformed(evt);
            }
        });
        MnGelangBayi.add(MnPrinterLama1);

        MnCetakKelengkapanInap.add(MnGelangBayi);

        MnBarcodeRM1.setBackground(new java.awt.Color(242, 242, 242));
        MnBarcodeRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM1.setText("Barcode (KERTAS BESAR)");
        MnBarcodeRM1.setName("MnBarcodeRM1"); // NOI18N
        MnBarcodeRM1.setPreferredSize(new java.awt.Dimension(220, 26));
        MnBarcodeRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnBarcodeRM1);

        MnBarcodeRM2.setBackground(new java.awt.Color(242, 242, 242));
        MnBarcodeRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcodeRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnBarcodeRM2.setText("Barcode (KERTAS KECIL)");
        MnBarcodeRM2.setName("MnBarcodeRM2"); // NOI18N
        MnBarcodeRM2.setPreferredSize(new java.awt.Dimension(220, 26));
        MnBarcodeRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeRM2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnBarcodeRM2);

        MnLabelRM1.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelRM1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM1.setText("Label Identitas (KERTAS BESAR)");
        MnLabelRM1.setName("MnLabelRM1"); // NOI18N
        MnLabelRM1.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLabelRM1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelRM1);

        MnLabelRM2.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelRM2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelRM2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelRM2.setText("Label Identitas (KERTAS KECIL)");
        MnLabelRM2.setName("MnLabelRM2"); // NOI18N
        MnLabelRM2.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLabelRM2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelRM2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelRM2);

        MnLabelPxRanap3.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelPxRanap3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelPxRanap3.setText("Label Pasien (4,9 x 1,9 Cm)");
        MnLabelPxRanap3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap3.setIconTextGap(5);
        MnLabelPxRanap3.setName("MnLabelPxRanap3"); // NOI18N
        MnLabelPxRanap3.setOpaque(true);
        MnLabelPxRanap3.setPreferredSize(new java.awt.Dimension(220, 26));

        MrkChampion.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkChampion);

        MrkAjp.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkAjp);

        MrkCox.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkCox);

        MrkAlfa.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkAlfa);

        MrkOlean.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkOlean);

        MrkKojico.setBackground(new java.awt.Color(242, 242, 242));
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
        MnLabelPxRanap3.add(MrkKojico);

        MnCetakKelengkapanInap.add(MnLabelPxRanap3);

        MnLabelPxRanap1.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelPxRanap1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelPxRanap1.setText("Label Pasien (3,9 x 1,9 Cm)");
        MnLabelPxRanap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap1.setIconTextGap(5);
        MnLabelPxRanap1.setName("MnLabelPxRanap1"); // NOI18N
        MnLabelPxRanap1.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLabelPxRanap1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap1ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelPxRanap1);

        MnLabelPxRanap2.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelPxRanap2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelPxRanap2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        MnLabelPxRanap2.setText("Label Pasien (6,4 x 3,2 Cm)");
        MnLabelPxRanap2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelPxRanap2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelPxRanap2.setIconTextGap(5);
        MnLabelPxRanap2.setName("MnLabelPxRanap2"); // NOI18N
        MnLabelPxRanap2.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLabelPxRanap2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelPxRanap2ActionPerformed(evt);
            }
        });
        MnCetakKelengkapanInap.add(MnLabelPxRanap2);

        jPopupMenu1.add(MnCetakKelengkapanInap);

        MnKamarInap.setBackground(new java.awt.Color(242, 242, 242));
        MnKamarInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnKamarInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnKamarInap.setText("Kamar Inap");
        MnKamarInap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnKamarInap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnKamarInap.setIconTextGap(5);
        MnKamarInap.setName("MnKamarInap"); // NOI18N
        MnKamarInap.setPreferredSize(new java.awt.Dimension(240, 26));
        MnKamarInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnKamarInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnKamarInap);

        MnPemeriksaan.setBackground(new java.awt.Color(242, 242, 242));
        MnPemeriksaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemeriksaan.setText("Pemeriksaan Penunjang");
        MnPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemeriksaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemeriksaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemeriksaan.setIconTextGap(5);
        MnPemeriksaan.setName("MnPemeriksaan"); // NOI18N
        MnPemeriksaan.setOpaque(true);
        MnPemeriksaan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnPeriksaLab.setBackground(new java.awt.Color(242, 242, 242));
        MnPeriksaLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaLab.setText("Periksa Laboratorium");
        MnPeriksaLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaLab.setIconTextGap(5);
        MnPeriksaLab.setName("MnPeriksaLab"); // NOI18N
        MnPeriksaLab.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPeriksaLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaLabActionPerformed(evt);
            }
        });
        MnPemeriksaan.add(MnPeriksaLab);

        MnPeriksaRadiologi.setBackground(new java.awt.Color(242, 242, 242));
        MnPeriksaRadiologi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPeriksaRadiologi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPeriksaRadiologi.setText("Periksa Radiologi");
        MnPeriksaRadiologi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPeriksaRadiologi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPeriksaRadiologi.setIconTextGap(5);
        MnPeriksaRadiologi.setName("MnPeriksaRadiologi"); // NOI18N
        MnPeriksaRadiologi.setPreferredSize(new java.awt.Dimension(160, 26));
        MnPeriksaRadiologi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPeriksaRadiologiActionPerformed(evt);
            }
        });
        MnPemeriksaan.add(MnPeriksaRadiologi);

        jPopupMenu1.add(MnPemeriksaan);

        MnRujukan.setBackground(new java.awt.Color(242, 242, 242));
        MnRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukan.setText("Rujukan");
        MnRujukan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukan.setIconTextGap(5);
        MnRujukan.setName("MnRujukan"); // NOI18N
        MnRujukan.setOpaque(true);
        MnRujukan.setPreferredSize(new java.awt.Dimension(220, 26));

        MnRujukMasuk.setBackground(new java.awt.Color(242, 242, 242));
        MnRujukMasuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukMasuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukMasuk.setText("Masuk");
        MnRujukMasuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukMasuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukMasuk.setIconTextGap(5);
        MnRujukMasuk.setName("MnRujukMasuk"); // NOI18N
        MnRujukMasuk.setPreferredSize(new java.awt.Dimension(125, 26));
        MnRujukMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukMasukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujukMasuk);

        MnRujuk.setBackground(new java.awt.Color(242, 242, 242));
        MnRujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujuk.setText("Keluar");
        MnRujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujuk.setIconTextGap(5);
        MnRujuk.setName("MnRujuk"); // NOI18N
        MnRujuk.setPreferredSize(new java.awt.Dimension(125, 26));
        MnRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujuk);

        MnRujukInternal.setBackground(new java.awt.Color(242, 242, 242));
        MnRujukInternal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukInternal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukInternal.setText("Internal Poliklinik");
        MnRujukInternal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukInternal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukInternal.setIconTextGap(5);
        MnRujukInternal.setName("MnRujukInternal"); // NOI18N
        MnRujukInternal.setPreferredSize(new java.awt.Dimension(125, 26));
        MnRujukInternal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukInternalActionPerformed(evt);
            }
        });
        MnRujukan.add(MnRujukInternal);

        jPopupMenu1.add(MnRujukan);

        jMenu1.setBackground(new java.awt.Color(242, 242, 242));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu1.setText("Laporan");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu1.setIconTextGap(5);
        jMenu1.setName("jMenu1"); // NOI18N
        jMenu1.setOpaque(true);
        jMenu1.setPreferredSize(new java.awt.Dimension(240, 26));

        MnLaporanRekapKunjunganPoli.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapKunjunganPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganPoli.setText("Laporan Rekap Kunjungan Per Poli");
        MnLaporanRekapKunjunganPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganPoli.setName("MnLaporanRekapKunjunganPoli"); // NOI18N
        MnLaporanRekapKunjunganPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganPoli);

        MnLaporanRekapKunjunganDokter.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapKunjunganDokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganDokter.setText("Laporan Rekap Kunjungan Per Dokter");
        MnLaporanRekapKunjunganDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganDokter.setIconTextGap(5);
        MnLaporanRekapKunjunganDokter.setName("MnLaporanRekapKunjunganDokter"); // NOI18N
        MnLaporanRekapKunjunganDokter.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganDokterActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganDokter);

        MnLaporanRekapJenisBayar.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapJenisBayar.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapJenisBayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapJenisBayar.setText("Laporan RL 315 Cara bayar");
        MnLaporanRekapJenisBayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapJenisBayar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapJenisBayar.setIconTextGap(5);
        MnLaporanRekapJenisBayar.setName("MnLaporanRekapJenisBayar"); // NOI18N
        MnLaporanRekapJenisBayar.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapJenisBayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapJenisBayarActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapJenisBayar);

        MnLaporanRekapRawatDarurat.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapRawatDarurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapRawatDarurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapRawatDarurat.setText("Laporan RL 32 Rawat Darurat");
        MnLaporanRekapRawatDarurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapRawatDarurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapRawatDarurat.setIconTextGap(5);
        MnLaporanRekapRawatDarurat.setName("MnLaporanRekapRawatDarurat"); // NOI18N
        MnLaporanRekapRawatDarurat.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapRawatDarurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapRawatDaruratActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapRawatDarurat);

        MnLaporanRekapKunjunganBulanan.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapKunjunganBulanan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulanan.setText("Laporan RL 51");
        MnLaporanRekapKunjunganBulanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulanan.setIconTextGap(5);
        MnLaporanRekapKunjunganBulanan.setName("MnLaporanRekapKunjunganBulanan"); // NOI18N
        MnLaporanRekapKunjunganBulanan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulanan);

        MnLaporanRekapKunjunganBulananPoli.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapKunjunganBulananPoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setText("Laporan RL 52");
        MnLaporanRekapKunjunganBulananPoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapKunjunganBulananPoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapKunjunganBulananPoli.setIconTextGap(5);
        MnLaporanRekapKunjunganBulananPoli.setName("MnLaporanRekapKunjunganBulananPoli"); // NOI18N
        MnLaporanRekapKunjunganBulananPoli.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapKunjunganBulananPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapKunjunganBulananPoliActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapKunjunganBulananPoli);

        MnLaporanRekapPenyakitRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapPenyakitRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapPenyakitRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapPenyakitRalan.setText("Laporan Frekuensi Penyakit Ralan");
        MnLaporanRekapPenyakitRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapPenyakitRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapPenyakitRalan.setIconTextGap(5);
        MnLaporanRekapPenyakitRalan.setName("MnLaporanRekapPenyakitRalan"); // NOI18N
        MnLaporanRekapPenyakitRalan.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapPenyakitRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapPenyakitRalanActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapPenyakitRalan);

        MnLaporanRekapPerujuk.setBackground(new java.awt.Color(242, 242, 242));
        MnLaporanRekapPerujuk.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanRekapPerujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanRekapPerujuk.setText("Laporan Rekap Perperujuk");
        MnLaporanRekapPerujuk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLaporanRekapPerujuk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLaporanRekapPerujuk.setIconTextGap(5);
        MnLaporanRekapPerujuk.setName("MnLaporanRekapPerujuk"); // NOI18N
        MnLaporanRekapPerujuk.setPreferredSize(new java.awt.Dimension(240, 26));
        MnLaporanRekapPerujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanRekapPerujukActionPerformed(evt);
            }
        });
        jMenu1.add(MnLaporanRekapPerujuk);

        jPopupMenu1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(242, 242, 242));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu2.setText("Grafik Analisa");
        jMenu2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu2.setIconTextGap(5);
        jMenu2.setName("jMenu2"); // NOI18N
        jMenu2.setOpaque(true);
        jMenu2.setPreferredSize(new java.awt.Dimension(240, 26));

        ppGrafikPerpoli.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerpoli.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli.setText("Grafik Plot Kunjungan Per Poli");
        ppGrafikPerpoli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli.setIconTextGap(5);
        ppGrafikPerpoli.setName("ppGrafikPerpoli"); // NOI18N
        ppGrafikPerpoli.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerpoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoliActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli);

        ppGrafikPerpoli1.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerpoli1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli1.setText("Grafik Pie Kunjungan Per Poli");
        ppGrafikPerpoli1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli1.setIconTextGap(5);
        ppGrafikPerpoli1.setName("ppGrafikPerpoli1"); // NOI18N
        ppGrafikPerpoli1.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerpoli1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoli1ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli1);

        ppGrafikPerpoli2.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerpoli2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerpoli2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerpoli2.setText("Grafik Batang Kunjungan Per Poli");
        ppGrafikPerpoli2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerpoli2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerpoli2.setIconTextGap(5);
        ppGrafikPerpoli2.setName("ppGrafikPerpoli2"); // NOI18N
        ppGrafikPerpoli2.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerpoli2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerpoli2ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerpoli2);

        ppGrafikPerdokter.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerdokter.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter.setText("Grafik Plot Kunjungan Per Dokter");
        ppGrafikPerdokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter.setIconTextGap(5);
        ppGrafikPerdokter.setName("ppGrafikPerdokter"); // NOI18N
        ppGrafikPerdokter.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerdokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokterActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter);

        ppGrafikPerdokter1.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerdokter1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter1.setText("Grafik Pie Kunjungan Per Dokter");
        ppGrafikPerdokter1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter1.setIconTextGap(5);
        ppGrafikPerdokter1.setName("ppGrafikPerdokter1"); // NOI18N
        ppGrafikPerdokter1.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerdokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokter1ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter1);

        ppGrafikPerdokter2.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerdokter2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerdokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerdokter2.setText("Grafik Batang Kunjungan Per Dokter");
        ppGrafikPerdokter2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerdokter2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerdokter2.setIconTextGap(5);
        ppGrafikPerdokter2.setName("ppGrafikPerdokter2"); // NOI18N
        ppGrafikPerdokter2.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerdokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerdokter2ActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerdokter2);

        ppGrafikPerJK.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerJK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerJK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerJK.setText("Grafik Plot Kunjungan Per Jenis Kelamin");
        ppGrafikPerJK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerJK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerJK.setIconTextGap(5);
        ppGrafikPerJK.setName("ppGrafikPerJK"); // NOI18N
        ppGrafikPerJK.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerJK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerJKActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerJK);

        ppGrafikPerPekerjaan.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerPekerjaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerPekerjaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerPekerjaan.setText("Grafik Plot Kunjungan Per Pekerjaan");
        ppGrafikPerPekerjaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerPekerjaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerPekerjaan.setIconTextGap(5);
        ppGrafikPerPekerjaan.setName("ppGrafikPerPekerjaan"); // NOI18N
        ppGrafikPerPekerjaan.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerPekerjaanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerPekerjaan);

        ppGrafikPerAgama.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerAgama.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerAgama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerAgama.setText("Grafik Plot Kunjungan Per Agama");
        ppGrafikPerAgama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerAgama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerAgama.setIconTextGap(5);
        ppGrafikPerAgama.setName("ppGrafikPerAgama"); // NOI18N
        ppGrafikPerAgama.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerAgama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerAgamaActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerAgama);

        ppGrafikPerTahun.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerTahun.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTahun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTahun.setText("Grafik Plot Kunjungan Per Tahun");
        ppGrafikPerTahun.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTahun.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTahun.setIconTextGap(5);
        ppGrafikPerTahun.setName("ppGrafikPerTahun"); // NOI18N
        ppGrafikPerTahun.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTahunActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTahun);

        ppGrafikPerBulan.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerBulan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerBulan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerBulan.setText("Grafik Plot Kunjungan Per Bulan");
        ppGrafikPerBulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerBulan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerBulan.setIconTextGap(5);
        ppGrafikPerBulan.setName("ppGrafikPerBulan"); // NOI18N
        ppGrafikPerBulan.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerBulanActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerBulan);

        ppGrafikPerTanggal.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikPerTanggal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikPerTanggal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikPerTanggal.setText("Grafik Kunjungan Per Tanggal");
        ppGrafikPerTanggal.setActionCommand("Grafik Plot Kunjungan Per Tanggal");
        ppGrafikPerTanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikPerTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikPerTanggal.setIconTextGap(5);
        ppGrafikPerTanggal.setName("ppGrafikPerTanggal"); // NOI18N
        ppGrafikPerTanggal.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikPerTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikPerTanggalActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikPerTanggal);

        ppGrafikDemografi.setBackground(new java.awt.Color(242, 242, 242));
        ppGrafikDemografi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppGrafikDemografi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppGrafikDemografi.setText("Demografi Pendaftar");
        ppGrafikDemografi.setActionCommand("Grafik Demografi Pasien");
        ppGrafikDemografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGrafikDemografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGrafikDemografi.setIconTextGap(5);
        ppGrafikDemografi.setName("ppGrafikDemografi"); // NOI18N
        ppGrafikDemografi.setPreferredSize(new java.awt.Dimension(240, 26));
        ppGrafikDemografi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppGrafikDemografiActionPerformed(evt);
            }
        });
        jMenu2.add(ppGrafikDemografi);

        jPopupMenu1.add(jMenu2);

        jMenu4.setBackground(new java.awt.Color(242, 242, 242));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu4.setText("Surat-Surat");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu4.setIconTextGap(5);
        jMenu4.setName("jMenu4"); // NOI18N
        jMenu4.setOpaque(true);
        jMenu4.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCetakSuratSehat.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakSuratSehat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat.setText("Surat Keterangan Sehat 1");
        MnCetakSuratSehat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSehat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSehat.setIconTextGap(5);
        MnCetakSuratSehat.setName("MnCetakSuratSehat"); // NOI18N
        MnCetakSuratSehat.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSehat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehatActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSehat);

        MnCetakSuratSehat1.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakSuratSehat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSehat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSehat1.setText("Surat Keterangan Sehat 2");
        MnCetakSuratSehat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSehat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSehat1.setIconTextGap(5);
        MnCetakSuratSehat1.setName("MnCetakSuratSehat1"); // NOI18N
        MnCetakSuratSehat1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSehat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSehat1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSehat1);

        MnCetakBebasNarkoba.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakBebasNarkoba.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakBebasNarkoba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakBebasNarkoba.setText("Surat Keterangan Bebas Narkoba");
        MnCetakBebasNarkoba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakBebasNarkoba.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakBebasNarkoba.setIconTextGap(5);
        MnCetakBebasNarkoba.setName("MnCetakBebasNarkoba"); // NOI18N
        MnCetakBebasNarkoba.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakBebasNarkoba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakBebasNarkobaActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakBebasNarkoba);

        MnCetakSuratSakit.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakSuratSakit.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit.setText("Surat Cuti Sakit 1");
        MnCetakSuratSakit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit.setIconTextGap(5);
        MnCetakSuratSakit.setName("MnCetakSuratSakit"); // NOI18N
        MnCetakSuratSakit.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakitActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit);

        MnCetakSuratSakit2.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakSuratSakit2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit2.setText("Surat Cuti Sakit 2");
        MnCetakSuratSakit2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit2.setIconTextGap(5);
        MnCetakSuratSakit2.setName("MnCetakSuratSakit2"); // NOI18N
        MnCetakSuratSakit2.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit2);

        MnCetakSuratSakit3.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakSuratSakit3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakSuratSakit3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakSuratSakit3.setText("Surat Cuti Sakit 3");
        MnCetakSuratSakit3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakSuratSakit3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakSuratSakit3.setIconTextGap(5);
        MnCetakSuratSakit3.setName("MnCetakSuratSakit3"); // NOI18N
        MnCetakSuratSakit3.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakSuratSakit3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakSuratSakit3ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakSuratSakit3);

        MnCetakRegister.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakRegister.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister.setText("Bukti Register 1");
        MnCetakRegister.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister.setIconTextGap(5);
        MnCetakRegister.setName("MnCetakRegister"); // NOI18N
        MnCetakRegister.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegisterActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister);

        MnCetakRegister1.setBackground(new java.awt.Color(242, 242, 242));
        MnCetakRegister1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakRegister1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCetakRegister1.setText("Bukti Register 2");
        MnCetakRegister1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCetakRegister1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCetakRegister1.setIconTextGap(5);
        MnCetakRegister1.setName("MnCetakRegister1"); // NOI18N
        MnCetakRegister1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnCetakRegister1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakRegister1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnCetakRegister1);

        MnPersetujuanMedis.setBackground(new java.awt.Color(242, 242, 242));
        MnPersetujuanMedis.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPersetujuanMedis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPersetujuanMedis.setText("Persetujuan Medis");
        MnPersetujuanMedis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPersetujuanMedis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPersetujuanMedis.setIconTextGap(5);
        MnPersetujuanMedis.setName("MnPersetujuanMedis"); // NOI18N
        MnPersetujuanMedis.setPreferredSize(new java.awt.Dimension(320, 26));
        MnPersetujuanMedis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPersetujuanMedisActionPerformed(evt);
            }
        });
        jMenu4.add(MnPersetujuanMedis);

        MnBuktiPelayananRalan.setBackground(new java.awt.Color(242, 242, 242));
        MnBuktiPelayananRalan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBuktiPelayananRalan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBuktiPelayananRalan.setText("Surat Jaminan & Bukti Pelayanan Ralan");
        MnBuktiPelayananRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBuktiPelayananRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBuktiPelayananRalan.setIconTextGap(5);
        MnBuktiPelayananRalan.setName("MnBuktiPelayananRalan"); // NOI18N
        MnBuktiPelayananRalan.setPreferredSize(new java.awt.Dimension(320, 26));
        MnBuktiPelayananRalan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBuktiPelayananRalanActionPerformed(evt);
            }
        });
        jMenu4.add(MnBuktiPelayananRalan);

        MnLembarCasemix.setBackground(new java.awt.Color(242, 242, 242));
        MnLembarCasemix.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLembarCasemix.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLembarCasemix.setText("Lembar Casemix");
        MnLembarCasemix.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLembarCasemix.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLembarCasemix.setIconTextGap(5);
        MnLembarCasemix.setName("MnLembarCasemix"); // NOI18N
        MnLembarCasemix.setPreferredSize(new java.awt.Dimension(320, 26));
        MnLembarCasemix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLembarCasemixActionPerformed(evt);
            }
        });
        jMenu4.add(MnLembarCasemix);

        MnSPBK.setBackground(new java.awt.Color(242, 242, 242));
        MnSPBK.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSPBK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSPBK.setText("Surat Bukti Pelayanan Kesehatan (SBPK)");
        MnSPBK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSPBK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSPBK.setIconTextGap(5);
        MnSPBK.setName("MnSPBK"); // NOI18N
        MnSPBK.setPreferredSize(new java.awt.Dimension(320, 26));
        MnSPBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSPBKActionPerformed(evt);
            }
        });
        jMenu4.add(MnSPBK);

        MnJKRA.setBackground(new java.awt.Color(242, 242, 242));
        MnJKRA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJKRA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJKRA.setText("Surat Jaminan Kesehatan Nasional (JKRA) Rawat Jalan");
        MnJKRA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJKRA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJKRA.setIconTextGap(5);
        MnJKRA.setName("MnJKRA"); // NOI18N
        MnJKRA.setPreferredSize(new java.awt.Dimension(320, 26));
        MnJKRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJKRAActionPerformed(evt);
            }
        });
        jMenu4.add(MnJKRA);

        MnGChalaman1.setBackground(new java.awt.Color(242, 242, 242));
        MnGChalaman1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGChalaman1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGChalaman1.setText("Lembar General Consent hal. 1");
        MnGChalaman1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGChalaman1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGChalaman1.setIconTextGap(5);
        MnGChalaman1.setName("MnGChalaman1"); // NOI18N
        MnGChalaman1.setPreferredSize(new java.awt.Dimension(320, 26));
        MnGChalaman1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGChalaman1ActionPerformed(evt);
            }
        });
        jMenu4.add(MnGChalaman1);

        MnGChalaman2.setBackground(new java.awt.Color(242, 242, 242));
        MnGChalaman2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGChalaman2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGChalaman2.setText("Lembar General Consent hal. 2");
        MnGChalaman2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGChalaman2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGChalaman2.setIconTextGap(5);
        MnGChalaman2.setName("MnGChalaman2"); // NOI18N
        MnGChalaman2.setPreferredSize(new java.awt.Dimension(320, 26));
        MnGChalaman2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGChalaman2ActionPerformed(evt);
            }
        });
        jMenu4.add(MnGChalaman2);

        MnManualSEPBPJS.setBackground(new java.awt.Color(242, 242, 242));
        MnManualSEPBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnManualSEPBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnManualSEPBPJS.setText("SEP Manual Pasien BPJS Rawat Jalan");
        MnManualSEPBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnManualSEPBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnManualSEPBPJS.setIconTextGap(5);
        MnManualSEPBPJS.setName("MnManualSEPBPJS"); // NOI18N
        MnManualSEPBPJS.setPreferredSize(new java.awt.Dimension(320, 26));
        MnManualSEPBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnManualSEPBPJSActionPerformed(evt);
            }
        });
        jMenu4.add(MnManualSEPBPJS);

        jPopupMenu1.add(jMenu4);

        jMenu3.setBackground(new java.awt.Color(242, 242, 242));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu3.setText("Check List Kelengkapan Pendaftaran");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu3.setIconTextGap(5);
        jMenu3.setName("jMenu3"); // NOI18N
        jMenu3.setOpaque(true);
        jMenu3.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCheckList.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList.setText("Check List Kelengkapan Pendaftaran Kanan");
        MnCheckList.setToolTipText("");
        MnCheckList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList.setIconTextGap(5);
        MnCheckList.setName("MnCheckList"); // NOI18N
        MnCheckList.setPreferredSize(new java.awt.Dimension(290, 26));
        MnCheckList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckListActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList);

        MnCheckList1.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList1.setText("Check List Kelengkapan Pendaftaran Kiri");
        MnCheckList1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList1.setIconTextGap(5);
        MnCheckList1.setName("MnCheckList1"); // NOI18N
        MnCheckList1.setPreferredSize(new java.awt.Dimension(290, 26));
        MnCheckList1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList1ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList1);

        MnCheckList2.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList2.setText("Check List Kelengkapan Pendaftaran Kanan+Tracker");
        MnCheckList2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList2.setIconTextGap(5);
        MnCheckList2.setName("MnCheckList2"); // NOI18N
        MnCheckList2.setPreferredSize(new java.awt.Dimension(290, 26));
        MnCheckList2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList2ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList2);

        MnCheckList3.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList3.setText("Check List Kelengkapan Pendaftaran Kiri+Tracker");
        MnCheckList3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList3.setIconTextGap(5);
        MnCheckList3.setName("MnCheckList3"); // NOI18N
        MnCheckList3.setPreferredSize(new java.awt.Dimension(290, 26));
        MnCheckList3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList3ActionPerformed(evt);
            }
        });
        jMenu3.add(MnCheckList3);

        jPopupMenu1.add(jMenu3);

        jMenu5.setBackground(new java.awt.Color(242, 242, 242));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu5.setText("Lembar Periksa Pasien");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu5.setIconTextGap(5);
        jMenu5.setName("jMenu5"); // NOI18N
        jMenu5.setOpaque(true);
        jMenu5.setPreferredSize(new java.awt.Dimension(240, 26));

        MnCheckList4.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList4.setText("Lembar Periksa Pasien Kanan");
        MnCheckList4.setToolTipText("");
        MnCheckList4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList4.setIconTextGap(5);
        MnCheckList4.setName("MnCheckList4"); // NOI18N
        MnCheckList4.setPreferredSize(new java.awt.Dimension(210, 26));
        MnCheckList4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList4ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList4);

        MnCheckList5.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList5.setText("Lembar Periksa Pasien Kiri");
        MnCheckList5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList5.setIconTextGap(5);
        MnCheckList5.setName("MnCheckList5"); // NOI18N
        MnCheckList5.setPreferredSize(new java.awt.Dimension(210, 26));
        MnCheckList5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList5ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList5);

        MnCheckList6.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList6.setText("Lembar Periksa Pasien Kanan 2");
        MnCheckList6.setToolTipText("");
        MnCheckList6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList6.setIconTextGap(5);
        MnCheckList6.setName("MnCheckList6"); // NOI18N
        MnCheckList6.setPreferredSize(new java.awt.Dimension(210, 26));
        MnCheckList6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList6ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList6);

        MnCheckList7.setBackground(new java.awt.Color(242, 242, 242));
        MnCheckList7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCheckList7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCheckList7.setText("Lembar Periksa Pasien Kiri 2");
        MnCheckList7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCheckList7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCheckList7.setIconTextGap(5);
        MnCheckList7.setName("MnCheckList7"); // NOI18N
        MnCheckList7.setPreferredSize(new java.awt.Dimension(210, 26));
        MnCheckList7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCheckList7ActionPerformed(evt);
            }
        });
        jMenu5.add(MnCheckList7);

        jPopupMenu1.add(jMenu5);

        jMenu6.setBackground(new java.awt.Color(242, 242, 242));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        jMenu6.setText("Label & Barcode");
        jMenu6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jMenu6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jMenu6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jMenu6.setIconTextGap(5);
        jMenu6.setName("jMenu6"); // NOI18N
        jMenu6.setOpaque(true);
        jMenu6.setPreferredSize(new java.awt.Dimension(240, 26));

        MnLabelTracker.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelTracker.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker.setText("Label Tracker 1");
        MnLabelTracker.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker.setIconTextGap(5);
        MnLabelTracker.setName("MnLabelTracker"); // NOI18N
        MnLabelTracker.setPreferredSize(new java.awt.Dimension(140, 26));
        MnLabelTracker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTrackerActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker);

        MnLabelTracker1.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelTracker1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker1.setText("Label Tracker 2");
        MnLabelTracker1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker1.setIconTextGap(5);
        MnLabelTracker1.setName("MnLabelTracker1"); // NOI18N
        MnLabelTracker1.setPreferredSize(new java.awt.Dimension(140, 26));
        MnLabelTracker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker1);

        MnLabelTracker2.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelTracker2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker2.setText("Label Tracker 3");
        MnLabelTracker2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker2.setIconTextGap(5);
        MnLabelTracker2.setName("MnLabelTracker2"); // NOI18N
        MnLabelTracker2.setPreferredSize(new java.awt.Dimension(140, 26));
        MnLabelTracker2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker2ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker2);

        MnLabelTracker3.setBackground(new java.awt.Color(242, 242, 242));
        MnLabelTracker3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLabelTracker3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLabelTracker3.setText("Label Tracker 4");
        MnLabelTracker3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnLabelTracker3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnLabelTracker3.setIconTextGap(5);
        MnLabelTracker3.setName("MnLabelTracker3"); // NOI18N
        MnLabelTracker3.setPreferredSize(new java.awt.Dimension(140, 26));
        MnLabelTracker3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLabelTracker3ActionPerformed(evt);
            }
        });
        jMenu6.add(MnLabelTracker3);

        MnBarcode.setBackground(new java.awt.Color(242, 242, 242));
        MnBarcode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBarcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBarcode.setText("Barcode Perawatan");
        MnBarcode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBarcode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBarcode.setIconTextGap(5);
        MnBarcode.setName("MnBarcode"); // NOI18N
        MnBarcode.setPreferredSize(new java.awt.Dimension(140, 26));
        MnBarcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBarcodeActionPerformed(evt);
            }
        });
        jMenu6.add(MnBarcode);

        MnGelang1.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang1.setText("Gelang Pasien 1");
        MnGelang1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang1.setIconTextGap(5);
        MnGelang1.setName("MnGelang1"); // NOI18N
        MnGelang1.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang1ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang1);

        MnGelang2.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang2.setText("Gelang Pasien 2");
        MnGelang2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang2.setIconTextGap(5);
        MnGelang2.setName("MnGelang2"); // NOI18N
        MnGelang2.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang2ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang2);

        MnGelang3.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang3.setText("Gelang Pasien 3");
        MnGelang3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang3.setIconTextGap(5);
        MnGelang3.setName("MnGelang3"); // NOI18N
        MnGelang3.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang3ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang3);

        MnGelang4.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang4.setText("Gelang Pasien 4");
        MnGelang4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang4.setIconTextGap(5);
        MnGelang4.setName("MnGelang4"); // NOI18N
        MnGelang4.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang4ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang4);

        MnGelang5.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang5.setText("Gelang Pasien 5");
        MnGelang5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang5.setIconTextGap(5);
        MnGelang5.setName("MnGelang5"); // NOI18N
        MnGelang5.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang5ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang5);

        MnGelang6.setBackground(new java.awt.Color(242, 242, 242));
        MnGelang6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGelang6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGelang6.setText("Gelang Pasien 6");
        MnGelang6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGelang6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGelang6.setIconTextGap(5);
        MnGelang6.setName("MnGelang6"); // NOI18N
        MnGelang6.setPreferredSize(new java.awt.Dimension(140, 26));
        MnGelang6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGelang6ActionPerformed(evt);
            }
        });
        jMenu6.add(MnGelang6);

        jPopupMenu1.add(jMenu6);

        MnStatus.setBackground(new java.awt.Color(242, 242, 242));
        MnStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnStatus.setText("Set Status");
        MnStatus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnStatus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnStatus.setIconTextGap(5);
        MnStatus.setName("MnStatus"); // NOI18N
        MnStatus.setOpaque(true);
        MnStatus.setPreferredSize(new java.awt.Dimension(220, 26));

        ppBerkas.setBackground(new java.awt.Color(242, 242, 242));
        ppBerkas.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBerkas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBerkas.setText("Berkas R.M. Diterima");
        ppBerkas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBerkas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBerkas.setIconTextGap(5);
        ppBerkas.setName("ppBerkas"); // NOI18N
        ppBerkas.setPreferredSize(new java.awt.Dimension(140, 26));
        ppBerkas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBerkasBtnPrintActionPerformed(evt);
            }
        });
        MnStatus.add(ppBerkas);

        MnSudah.setBackground(new java.awt.Color(242, 242, 242));
        MnSudah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSudah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSudah.setText("Sudah Periksa");
        MnSudah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSudah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSudah.setIconTextGap(5);
        MnSudah.setName("MnSudah"); // NOI18N
        MnSudah.setPreferredSize(new java.awt.Dimension(140, 26));
        MnSudah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSudahActionPerformed(evt);
            }
        });
        MnStatus.add(MnSudah);

        MnBelum.setBackground(new java.awt.Color(242, 242, 242));
        MnBelum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBelum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBelum.setText("Belum Periksa");
        MnBelum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBelum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBelum.setIconTextGap(5);
        MnBelum.setName("MnBelum"); // NOI18N
        MnBelum.setPreferredSize(new java.awt.Dimension(140, 26));
        MnBelum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBelumActionPerformed(evt);
            }
        });
        MnStatus.add(MnBelum);

        MnBatal.setBackground(new java.awt.Color(242, 242, 242));
        MnBatal.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBatal.setText("Batal Periksa");
        MnBatal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBatal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBatal.setIconTextGap(5);
        MnBatal.setName("MnBatal"); // NOI18N
        MnBatal.setPreferredSize(new java.awt.Dimension(140, 26));
        MnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBatalActionPerformed(evt);
            }
        });
        MnStatus.add(MnBatal);

        jPopupMenu1.add(MnStatus);

        MnBridging.setBackground(new java.awt.Color(242, 242, 242));
        MnBridging.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBridging.setText("Bridging");
        MnBridging.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBridging.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBridging.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBridging.setIconTextGap(5);
        MnBridging.setName("MnBridging"); // NOI18N
        MnBridging.setOpaque(true);
        MnBridging.setPreferredSize(new java.awt.Dimension(240, 26));

        ppPasienCorona.setBackground(new java.awt.Color(242, 242, 242));
        ppPasienCorona.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPasienCorona.setForeground(new java.awt.Color(50, 50, 50));
        ppPasienCorona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPasienCorona.setText("Pasien Corona Kemenkes");
        ppPasienCorona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPasienCorona.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPasienCorona.setIconTextGap(5);
        ppPasienCorona.setName("ppPasienCorona"); // NOI18N
        ppPasienCorona.setPreferredSize(new java.awt.Dimension(170, 26));
        ppPasienCorona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPasienCoronaBtnPrintActionPerformed(evt);
            }
        });
        MnBridging.add(ppPasienCorona);

        ppCekFingerPrin.setBackground(new java.awt.Color(242, 242, 242));
        ppCekFingerPrin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCekFingerPrin.setForeground(new java.awt.Color(50, 50, 50));
        ppCekFingerPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCekFingerPrin.setText("Cek Finger Print BPJS");
        ppCekFingerPrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCekFingerPrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCekFingerPrin.setIconTextGap(5);
        ppCekFingerPrin.setName("ppCekFingerPrin"); // NOI18N
        ppCekFingerPrin.setPreferredSize(new java.awt.Dimension(170, 26));
        ppCekFingerPrin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCekFingerPrinBtnPrintActionPerformed(evt);
            }
        });
        MnBridging.add(ppCekFingerPrin);

        MnSEP.setBackground(new java.awt.Color(242, 242, 242));
        MnSEP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSEP.setText("Bridging SEP BPJS");
        MnSEP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSEP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSEP.setIconTextGap(5);
        MnSEP.setName("MnSEP"); // NOI18N
        MnSEP.setPreferredSize(new java.awt.Dimension(170, 26));
        MnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSEPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSEP);

        MnSJP.setBackground(new java.awt.Color(242, 242, 242));
        MnSJP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSJP.setText("Bridging SJP Inhealth");
        MnSJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSJP.setIconTextGap(5);
        MnSJP.setName("MnSJP"); // NOI18N
        MnSJP.setPreferredSize(new java.awt.Dimension(170, 26));
        MnSJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSJPActionPerformed(evt);
            }
        });
        MnBridging.add(MnSJP);

        ppSuratKontrol.setBackground(new java.awt.Color(242, 242, 242));
        ppSuratKontrol.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppSuratKontrol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppSuratKontrol.setText("Rencana Kontrol BPJS");
        ppSuratKontrol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSuratKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSuratKontrol.setIconTextGap(5);
        ppSuratKontrol.setName("ppSuratKontrol"); // NOI18N
        ppSuratKontrol.setPreferredSize(new java.awt.Dimension(180, 26));
        ppSuratKontrol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppSuratKontrolActionPerformed(evt);
            }
        });
        MnBridging.add(ppSuratKontrol);

        MnJAMKESDA.setBackground(new java.awt.Color(242, 242, 242));
        MnJAMKESDA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJAMKESDA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJAMKESDA.setText("Bridging SEP Jamkesda");
        MnJAMKESDA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJAMKESDA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJAMKESDA.setIconTextGap(5);
        MnJAMKESDA.setName("MnJAMKESDA"); // NOI18N
        MnJAMKESDA.setPreferredSize(new java.awt.Dimension(170, 26));
        MnJAMKESDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJAMKESDAActionPerformed(evt);
            }
        });
        MnBridging.add(MnJAMKESDA);

        MnJAMPERSAL.setBackground(new java.awt.Color(242, 242, 242));
        MnJAMPERSAL.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnJAMPERSAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnJAMPERSAL.setText("Bridging SEP Jampersal");
        MnJAMPERSAL.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnJAMPERSAL.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnJAMPERSAL.setIconTextGap(5);
        MnJAMPERSAL.setName("MnJAMPERSAL"); // NOI18N
        MnJAMPERSAL.setPreferredSize(new java.awt.Dimension(170, 26));
        MnJAMPERSAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnJAMPERSALActionPerformed(evt);
            }
        });
        MnBridging.add(MnJAMPERSAL);

        MnRujukSisrute.setBackground(new java.awt.Color(242, 242, 242));
        MnRujukSisrute.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRujukSisrute.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRujukSisrute.setText("Rujuk Keluar Via Sisrute");
        MnRujukSisrute.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRujukSisrute.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRujukSisrute.setIconTextGap(5);
        MnRujukSisrute.setName("MnRujukSisrute"); // NOI18N
        MnRujukSisrute.setPreferredSize(new java.awt.Dimension(180, 26));
        MnRujukSisrute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRujukSisruteActionPerformed(evt);
            }
        });
        MnBridging.add(MnRujukSisrute);

        jPopupMenu1.add(MnBridging);

        MnPetugasReg.setBackground(new java.awt.Color(242, 242, 242));
        MnPetugasReg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPetugasReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPetugasReg.setText("Lihat Nama Petugas TPPRJ");
        MnPetugasReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPetugasReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPetugasReg.setIconTextGap(5);
        MnPetugasReg.setName("MnPetugasReg"); // NOI18N
        MnPetugasReg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnPetugasReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPetugasRegActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPetugasReg);

        MnBilling.setBackground(new java.awt.Color(242, 242, 242));
        MnBilling.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBilling.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnBilling.setText("Billing/Pembayaran Pasien");
        MnBilling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBilling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBilling.setIconTextGap(5);
        MnBilling.setName("MnBilling"); // NOI18N
        MnBilling.setPreferredSize(new java.awt.Dimension(240, 26));
        MnBilling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBillingActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBilling);

        MnGantiTglReg.setBackground(new java.awt.Color(242, 242, 242));
        MnGantiTglReg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiTglReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiTglReg.setText("Ganti Tanggal Registrasi");
        MnGantiTglReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiTglReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiTglReg.setIconTextGap(5);
        MnGantiTglReg.setName("MnGantiTglReg"); // NOI18N
        MnGantiTglReg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnGantiTglReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiTglRegActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiTglReg);

        MnGantiUmurReg.setBackground(new java.awt.Color(242, 242, 242));
        MnGantiUmurReg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGantiUmurReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGantiUmurReg.setText("Ganti Umur Daftar & Tgl. Lahir");
        MnGantiUmurReg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGantiUmurReg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGantiUmurReg.setIconTextGap(5);
        MnGantiUmurReg.setName("MnGantiUmurReg"); // NOI18N
        MnGantiUmurReg.setPreferredSize(new java.awt.Dimension(240, 26));
        MnGantiUmurReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGantiUmurRegActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGantiUmurReg);

        MnHemodialisa.setBackground(new java.awt.Color(242, 242, 242));
        MnHemodialisa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHemodialisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHemodialisa.setText("Hemodialisa");
        MnHemodialisa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHemodialisa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHemodialisa.setIconTextGap(5);
        MnHemodialisa.setName("MnHemodialisa"); // NOI18N
        MnHemodialisa.setPreferredSize(new java.awt.Dimension(240, 26));
        MnHemodialisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHemodialisaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHemodialisa);

        ppRiwayat.setBackground(new java.awt.Color(242, 242, 242));
        ppRiwayat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRiwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRiwayat.setText("Riwayat Perawatan");
        ppRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRiwayat.setIconTextGap(5);
        ppRiwayat.setName("ppRiwayat"); // NOI18N
        ppRiwayat.setPreferredSize(new java.awt.Dimension(240, 26));
        ppRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRiwayatBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRiwayat);

        ppCatatanPasien.setBackground(new java.awt.Color(242, 242, 242));
        ppCatatanPasien.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppCatatanPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppCatatanPasien.setText("Catatan Untuk Pasien");
        ppCatatanPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCatatanPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCatatanPasien.setIconTextGap(5);
        ppCatatanPasien.setName("ppCatatanPasien"); // NOI18N
        ppCatatanPasien.setPreferredSize(new java.awt.Dimension(240, 26));
        ppCatatanPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppCatatanPasienBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppCatatanPasien);

        DlgSakit.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgSakit.setName("DlgSakit"); // NOI18N
        DlgSakit.setUndecorated(true);
        DlgSakit.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Cetak Surat Cuti Sakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa2.setName("panelBiasa2"); // NOI18N
        panelBiasa2.setLayout(null);

        TglSakit1.setEditable(false);
        TglSakit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        TglSakit1.setDisplayFormat("dd-MM-yyyy");
        TglSakit1.setName("TglSakit1"); // NOI18N
        TglSakit1.setOpaque(false);
        panelBiasa2.add(TglSakit1);
        TglSakit1.setBounds(70, 10, 100, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Lama Sakit :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelBiasa2.add(jLabel31);
        jLabel31.setBounds(297, 10, 110, 23);

        BtnPrint2.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint2.setMnemonic('T');
        BtnPrint2.setText("Cetak");
        BtnPrint2.setToolTipText("Alt+T");
        BtnPrint2.setName("BtnPrint2"); // NOI18N
        BtnPrint2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnPrint2);
        BtnPrint2.setBounds(10, 50, 100, 30);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        panelBiasa2.add(BtnKeluar2);
        BtnKeluar2.setBounds(430, 50, 100, 30);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("s/d");
        jLabel32.setName("jLabel32"); // NOI18N
        panelBiasa2.add(jLabel32);
        jLabel32.setBounds(176, 10, 20, 23);

        TglSakit2.setEditable(false);
        TglSakit2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        TglSakit2.setDisplayFormat("dd-MM-yyyy");
        TglSakit2.setName("TglSakit2"); // NOI18N
        TglSakit2.setOpaque(false);
        panelBiasa2.add(TglSakit2);
        TglSakit2.setBounds(200, 10, 100, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Tanggal :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa2.add(jLabel33);
        jLabel33.setBounds(0, 10, 66, 23);

        lmsakit.setForeground(new java.awt.Color(0, 0, 0));
        lmsakit.setHighlighter(null);
        lmsakit.setName("lmsakit"); // NOI18N
        panelBiasa2.add(lmsakit);
        lmsakit.setBounds(410, 10, 110, 23);

        internalFrame3.add(panelBiasa2, java.awt.BorderLayout.CENTER);

        DlgSakit.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        DlgDemografi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgDemografi.setName("DlgDemografi"); // NOI18N
        DlgDemografi.setUndecorated(true);
        DlgDemografi.setResizable(false);

        internalFrame4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Demografi Pendaftar ]::"));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        panelBiasa3.setName("panelBiasa3"); // NOI18N
        panelBiasa3.setLayout(null);

        BtnPrint3.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint3.setMnemonic('G');
        BtnPrint3.setText("Grafik");
        BtnPrint3.setToolTipText("Alt+G");
        BtnPrint3.setName("BtnPrint3"); // NOI18N
        BtnPrint3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint3ActionPerformed(evt);
            }
        });
        BtnPrint3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint3KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint3);
        BtnPrint3.setBounds(110, 110, 100, 30);

        BtnKeluar3.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar3.setMnemonic('K');
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
        panelBiasa3.add(BtnKeluar3);
        BtnKeluar3.setBounds(430, 110, 100, 30);

        Kelurahan2.setForeground(new java.awt.Color(0, 0, 0));
        Kelurahan2.setHighlighter(null);
        Kelurahan2.setName("Kelurahan2"); // NOI18N
        Kelurahan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kelurahan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kelurahan2);
        Kelurahan2.setBounds(105, 70, 350, 23);

        btnKel.setForeground(new java.awt.Color(0, 0, 0));
        btnKel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKel.setMnemonic('1');
        btnKel.setToolTipText("ALt+1");
        btnKel.setName("btnKel"); // NOI18N
        btnKel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKelActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKel);
        btnKel.setBounds(460, 70, 28, 23);

        Kecamatan2.setForeground(new java.awt.Color(0, 0, 0));
        Kecamatan2.setHighlighter(null);
        Kecamatan2.setName("Kecamatan2"); // NOI18N
        Kecamatan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kecamatan2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kecamatan2);
        Kecamatan2.setBounds(105, 40, 350, 23);

        btnKec.setForeground(new java.awt.Color(0, 0, 0));
        btnKec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKec.setMnemonic('1');
        btnKec.setToolTipText("ALt+1");
        btnKec.setName("btnKec"); // NOI18N
        btnKec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKecActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKec);
        btnKec.setBounds(460, 40, 28, 23);

        Kabupaten2.setForeground(new java.awt.Color(0, 0, 0));
        Kabupaten2.setHighlighter(null);
        Kabupaten2.setName("Kabupaten2"); // NOI18N
        Kabupaten2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kabupaten2KeyPressed(evt);
            }
        });
        panelBiasa3.add(Kabupaten2);
        Kabupaten2.setBounds(105, 10, 350, 23);

        btnKab.setForeground(new java.awt.Color(0, 0, 0));
        btnKab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKab.setMnemonic('1');
        btnKab.setToolTipText("ALt+1");
        btnKab.setName("btnKab"); // NOI18N
        btnKab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKabActionPerformed(evt);
            }
        });
        panelBiasa3.add(btnKab);
        btnKab.setBounds(460, 10, 28, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Kelurahan :");
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa3.add(jLabel34);
        jLabel34.setBounds(0, 70, 100, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Kabupaten :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa3.add(jLabel35);
        jLabel35.setBounds(0, 10, 100, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Kecamatan :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelBiasa3.add(jLabel36);
        jLabel36.setBounds(0, 40, 100, 23);

        BtnPrint4.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrint4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint4.setMnemonic('T');
        BtnPrint4.setText("Cetak");
        BtnPrint4.setToolTipText("Alt+T");
        BtnPrint4.setName("BtnPrint4"); // NOI18N
        BtnPrint4.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrint4ActionPerformed(evt);
            }
        });
        BtnPrint4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrint4KeyPressed(evt);
            }
        });
        panelBiasa3.add(BtnPrint4);
        BtnPrint4.setBounds(10, 110, 100, 30);

        internalFrame4.add(panelBiasa3, java.awt.BorderLayout.CENTER);

        DlgDemografi.getContentPane().add(internalFrame4, java.awt.BorderLayout.CENTER);

        DlgJamkesda.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJamkesda.setName("DlgJamkesda"); // NOI18N
        DlgJamkesda.setUndecorated(true);
        DlgJamkesda.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMKESDA Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(null);

        BtnCloseIn1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn1.setMnemonic('U');
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
        internalFrame6.add(BtnCloseIn1);
        BtnCloseIn1.setBounds(340, 80, 100, 30);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
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
        internalFrame6.add(BtnSimpan1);
        BtnSimpan1.setBounds(10, 80, 100, 30);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Tgl. Surat Ketrgn. :");
        jLabel25.setName("jLabel25"); // NOI18N
        internalFrame6.add(jLabel25);
        jLabel25.setBounds(0, 50, 110, 23);

        noSrt.setForeground(new java.awt.Color(0, 0, 0));
        noSrt.setHighlighter(null);
        noSrt.setName("noSrt"); // NOI18N
        noSrt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrtKeyPressed(evt);
            }
        });
        internalFrame6.add(noSrt);
        noSrt.setBounds(120, 20, 350, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("No. Surat Ketrgn. :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame6.add(jLabel26);
        jLabel26.setBounds(0, 20, 110, 23);

        TglSurat.setEditable(false);
        TglSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        TglSurat.setDisplayFormat("dd-MM-yyyy");
        TglSurat.setName("TglSurat"); // NOI18N
        TglSurat.setOpaque(false);
        TglSurat.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSuratItemStateChanged(evt);
            }
        });
        TglSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSuratKeyPressed(evt);
            }
        });
        internalFrame6.add(TglSurat);
        TglSurat.setBounds(120, 50, 100, 23);

        BtnGantijkd.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantijkd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantijkd.setMnemonic('G');
        BtnGantijkd.setText("Ganti");
        BtnGantijkd.setToolTipText("Alt+G");
        BtnGantijkd.setName("BtnGantijkd"); // NOI18N
        BtnGantijkd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantijkdActionPerformed(evt);
            }
        });
        BtnGantijkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantijkdKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnGantijkd);
        BtnGantijkd.setBounds(120, 80, 100, 30);

        BtnCtkJkd.setForeground(new java.awt.Color(0, 0, 0));
        BtnCtkJkd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCtkJkd.setMnemonic('C');
        BtnCtkJkd.setText("Cetak SEP");
        BtnCtkJkd.setToolTipText("Alt+C");
        BtnCtkJkd.setName("BtnCtkJkd"); // NOI18N
        BtnCtkJkd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCtkJkdActionPerformed(evt);
            }
        });
        BtnCtkJkd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCtkJkdKeyPressed(evt);
            }
        });
        internalFrame6.add(BtnCtkJkd);
        BtnCtkJkd.setBounds(230, 80, 100, 30);

        DlgJamkesda.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        DlgJampersal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgJampersal.setName("DlgJampersal"); // NOI18N
        DlgJampersal.setUndecorated(true);
        DlgJampersal.setResizable(false);

        internalFrame7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Bridging JAMPERSAL Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
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
        internalFrame7.add(BtnCloseIn2);
        BtnCloseIn2.setBounds(340, 80, 100, 30);

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
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
        internalFrame7.add(BtnSimpan2);
        BtnSimpan2.setBounds(10, 80, 100, 30);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tgl. Surat Ketrgn. :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame7.add(jLabel27);
        jLabel27.setBounds(0, 50, 110, 23);

        noSrt1.setForeground(new java.awt.Color(0, 0, 0));
        noSrt1.setHighlighter(null);
        noSrt1.setName("noSrt1"); // NOI18N
        noSrt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noSrt1KeyPressed(evt);
            }
        });
        internalFrame7.add(noSrt1);
        noSrt1.setBounds(120, 20, 350, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("No. Surat Ketrgn. :");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame7.add(jLabel28);
        jLabel28.setBounds(0, 20, 110, 23);

        TglSurat1.setEditable(false);
        TglSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        TglSurat1.setDisplayFormat("dd-MM-yyyy");
        TglSurat1.setName("TglSurat1"); // NOI18N
        TglSurat1.setOpaque(false);
        TglSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglSurat1ItemStateChanged(evt);
            }
        });
        TglSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglSurat1KeyPressed(evt);
            }
        });
        internalFrame7.add(TglSurat1);
        TglSurat1.setBounds(120, 50, 100, 23);

        BtnGantijmp.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantijmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantijmp.setMnemonic('G');
        BtnGantijmp.setText("Ganti");
        BtnGantijmp.setToolTipText("Alt+G");
        BtnGantijmp.setName("BtnGantijmp"); // NOI18N
        BtnGantijmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantijmpActionPerformed(evt);
            }
        });
        BtnGantijmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantijmpKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnGantijmp);
        BtnGantijmp.setBounds(120, 80, 100, 30);

        BtnCtkJmp.setForeground(new java.awt.Color(0, 0, 0));
        BtnCtkJmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCtkJmp.setMnemonic('C');
        BtnCtkJmp.setText("Cetak SEP");
        BtnCtkJmp.setToolTipText("Alt+C");
        BtnCtkJmp.setName("BtnCtkJmp"); // NOI18N
        BtnCtkJmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCtkJmpActionPerformed(evt);
            }
        });
        BtnCtkJmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCtkJmpKeyPressed(evt);
            }
        });
        internalFrame7.add(BtnCtkJmp);
        BtnCtkJmp.setBounds(230, 80, 100, 30);

        DlgJampersal.getContentPane().add(internalFrame7, java.awt.BorderLayout.CENTER);

        DlgTanggalReg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgTanggalReg.setName("DlgTanggalReg"); // NOI18N
        DlgTanggalReg.setUndecorated(true);
        DlgTanggalReg.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Tanggal Registrasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

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
        internalFrame8.add(BtnCloseIn3);
        BtnCloseIn3.setBounds(350, 20, 90, 30);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tanggal Registrasi :");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame8.add(jLabel29);
        jLabel29.setBounds(0, 25, 130, 23);

        TglReg.setEditable(false);
        TglReg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        TglReg.setDisplayFormat("dd-MM-yyyy");
        TglReg.setName("TglReg"); // NOI18N
        TglReg.setOpaque(false);
        TglReg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglRegItemStateChanged(evt);
            }
        });
        TglReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglRegKeyPressed(evt);
            }
        });
        internalFrame8.add(TglReg);
        TglReg.setBounds(135, 25, 100, 23);

        BtnGantiTgl.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiTgl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiTgl.setMnemonic('G');
        BtnGantiTgl.setText("Ganti");
        BtnGantiTgl.setToolTipText("Alt+G");
        BtnGantiTgl.setName("BtnGantiTgl"); // NOI18N
        BtnGantiTgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiTglActionPerformed(evt);
            }
        });
        BtnGantiTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiTglKeyPressed(evt);
            }
        });
        internalFrame8.add(BtnGantiTgl);
        BtnGantiTgl.setBounds(240, 20, 100, 30);

        DlgTanggalReg.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        DlgRegRujukInternal.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgRegRujukInternal.setName("DlgRegRujukInternal"); // NOI18N
        DlgRegRujukInternal.setUndecorated(true);
        DlgRegRujukInternal.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Registrasi Rujuk Internal Poliklinik Dihari Yang Sama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

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
        BtnSimpanRujuk.setBounds(340, 145, 90, 30);

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
        BtnKeluar5.setBounds(440, 145, 90, 30);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("No. Rawat Perujuk : ");
        jLabel40.setName("jLabel40"); // NOI18N
        panelBiasa5.add(jLabel40);
        jLabel40.setBounds(0, 8, 130, 23);

        norwPerujuk.setEditable(false);
        norwPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        norwPerujuk.setName("norwPerujuk"); // NOI18N
        panelBiasa5.add(norwPerujuk);
        norwPerujuk.setBounds(130, 8, 140, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Poliklinik Tujuan : ");
        jLabel41.setName("jLabel41"); // NOI18N
        panelBiasa5.add(jLabel41);
        jLabel41.setBounds(0, 89, 130, 23);

        kdpoliRujuk.setEditable(false);
        kdpoliRujuk.setForeground(new java.awt.Color(0, 0, 0));
        kdpoliRujuk.setName("kdpoliRujuk"); // NOI18N
        panelBiasa5.add(kdpoliRujuk);
        kdpoliRujuk.setBounds(130, 89, 50, 23);

        nmpoliRujuk.setEditable(false);
        nmpoliRujuk.setForeground(new java.awt.Color(0, 0, 0));
        nmpoliRujuk.setName("nmpoliRujuk"); // NOI18N
        panelBiasa5.add(nmpoliRujuk);
        nmpoliRujuk.setBounds(183, 89, 350, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Nama Dokter : ");
        jLabel42.setName("jLabel42"); // NOI18N
        panelBiasa5.add(jLabel42);
        jLabel42.setBounds(0, 116, 130, 23);

        kdDokterRujuk.setEditable(false);
        kdDokterRujuk.setForeground(new java.awt.Color(0, 0, 0));
        kdDokterRujuk.setName("kdDokterRujuk"); // NOI18N
        panelBiasa5.add(kdDokterRujuk);
        kdDokterRujuk.setBounds(130, 116, 90, 23);

        nmDokterRujuk.setEditable(false);
        nmDokterRujuk.setForeground(new java.awt.Color(0, 0, 0));
        nmDokterRujuk.setName("nmDokterRujuk"); // NOI18N
        panelBiasa5.add(nmDokterRujuk);
        nmDokterRujuk.setBounds(224, 116, 310, 23);

        BtnPoliRujuk.setForeground(new java.awt.Color(0, 0, 0));
        BtnPoliRujuk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoliRujuk.setMnemonic('3');
        BtnPoliRujuk.setToolTipText("ALt+3");
        BtnPoliRujuk.setName("BtnPoliRujuk"); // NOI18N
        BtnPoliRujuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliRujukActionPerformed(evt);
            }
        });
        panelBiasa5.add(BtnPoliRujuk);
        BtnPoliRujuk.setBounds(535, 89, 28, 23);

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
        BtnDokterRujuk.setBounds(535, 116, 28, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Poliklinik Perujuk : ");
        jLabel43.setName("jLabel43"); // NOI18N
        panelBiasa5.add(jLabel43);
        jLabel43.setBounds(0, 35, 130, 23);

        poliPerujuk.setEditable(false);
        poliPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        poliPerujuk.setName("poliPerujuk"); // NOI18N
        panelBiasa5.add(poliPerujuk);
        poliPerujuk.setBounds(130, 35, 320, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Dokter Perujuk : ");
        jLabel44.setName("jLabel44"); // NOI18N
        panelBiasa5.add(jLabel44);
        jLabel44.setBounds(0, 62, 130, 23);

        drPerujuk.setEditable(false);
        drPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        drPerujuk.setName("drPerujuk"); // NOI18N
        panelBiasa5.add(drPerujuk);
        drPerujuk.setBounds(130, 62, 320, 23);

        internalFrame9.add(panelBiasa5, java.awt.BorderLayout.CENTER);

        DlgRegRujukInternal.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        DlgGantiUmurReg.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgGantiUmurReg.setName("DlgGantiUmurReg"); // NOI18N
        DlgGantiUmurReg.setUndecorated(true);
        DlgGantiUmurReg.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Perbaiki Umur Mendaftar & Tgl. Lahir ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(null);

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
        internalFrame10.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(330, 50, 90, 30);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Lahir :");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame10.add(jLabel37);
        jLabel37.setBounds(261, 20, 65, 23);

        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tumur.setHighlighter(null);
        Tumur.setName("Tumur"); // NOI18N
        internalFrame10.add(Tumur);
        Tumur.setBounds(75, 20, 50, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Umur :");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame10.add(jLabel39);
        jLabel39.setBounds(0, 20, 70, 23);

        Ttgl_lahir.setEditable(false);
        Ttgl_lahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        Ttgl_lahir.setDisplayFormat("dd-MM-yyyy");
        Ttgl_lahir.setName("Ttgl_lahir"); // NOI18N
        Ttgl_lahir.setOpaque(false);
        internalFrame10.add(Ttgl_lahir);
        Ttgl_lahir.setBounds(331, 20, 100, 23);

        BtnGantiUmur.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiUmur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiUmur.setMnemonic('G');
        BtnGantiUmur.setText("Ganti");
        BtnGantiUmur.setToolTipText("Alt+G");
        BtnGantiUmur.setName("BtnGantiUmur"); // NOI18N
        BtnGantiUmur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiUmurActionPerformed(evt);
            }
        });
        internalFrame10.add(BtnGantiUmur);
        BtnGantiUmur.setBounds(50, 50, 90, 30);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Status Umur :");
        jLabel46.setName("jLabel46"); // NOI18N
        internalFrame10.add(jLabel46);
        jLabel46.setBounds(125, 20, 80, 23);

        cmbSttsUmur.setForeground(new java.awt.Color(0, 0, 0));
        cmbSttsUmur.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Th", "Bl", "Hr" }));
        cmbSttsUmur.setName("cmbSttsUmur"); // NOI18N
        internalFrame10.add(cmbSttsUmur);
        cmbSttsUmur.setBounds(211, 20, 50, 23);

        DlgGantiUmurReg.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        NoBalasan.setHighlighter(null);
        NoBalasan.setName("NoBalasan"); // NOI18N

        sepJkd.setName("sepJkd"); // NOI18N
        sepJkd.setPreferredSize(new java.awt.Dimension(207, 23));

        sepJmp.setName("sepJmp"); // NOI18N
        sepJmp.setPreferredSize(new java.awt.Dimension(207, 23));

        NoPeserta.setName("NoPeserta"); // NOI18N
        NoPeserta.setPreferredSize(new java.awt.Dimension(207, 23));

        rmMati.setName("rmMati"); // NOI18N
        rmMati.setPreferredSize(new java.awt.Dimension(207, 23));

        jamMati.setName("jamMati"); // NOI18N
        jamMati.setPreferredSize(new java.awt.Dimension(207, 23));

        tglMati.setName("tglMati"); // NOI18N
        tglMati.setPreferredSize(new java.awt.Dimension(207, 23));

        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(207, 23));

        kode_rujukanya.setName("kode_rujukanya"); // NOI18N
        kode_rujukanya.setPreferredSize(new java.awt.Dimension(207, 23));

        cekPasien.setHighlighter(null);
        cekPasien.setName("cekPasien"); // NOI18N
        cekPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cekPasienKeyPressed(evt);
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

        prosesTombol.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        prosesTombol.setName("prosesTombol"); // NOI18N
        prosesTombol.setPreferredSize(new java.awt.Dimension(160, 40));
        prosesTombol.setStringPainted(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Registrasi Periksa Hari Ini ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(490, 167));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No. ID Sesuai dg. :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(280, 12, 100, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 42, 77, 23);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setName("TDokter"); // NOI18N
        FormInput.add(TDokter);
        TDokter.setBounds(154, 130, 210, 23);

        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(81, 42, 190, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Reg. :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 72, 77, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Dr Dituju :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 132, 77, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Png. Jawab :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(396, 42, 100, 23);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Almt Png. Jwb :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(396, 72, 100, 23);

        TPngJwb.setForeground(new java.awt.Color(0, 0, 0));
        TPngJwb.setHighlighter(null);
        TPngJwb.setName("TPngJwb"); // NOI18N
        TPngJwb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPngJwbKeyPressed(evt);
            }
        });
        FormInput.add(TPngJwb);
        TPngJwb.setBounds(500, 42, 180, 23);

        TNoID.setForeground(new java.awt.Color(0, 0, 0));
        TNoID.setName("TNoID"); // NOI18N
        TNoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoIDKeyPressed(evt);
            }
        });
        FormInput.add(TNoID);
        TNoID.setBounds(499, 12, 120, 23);

        TNoReg.setForeground(new java.awt.Color(0, 0, 0));
        TNoReg.setName("TNoReg"); // NOI18N
        TNoReg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRegKeyPressed(evt);
            }
        });
        FormInput.add(TNoReg);
        TNoReg.setBounds(81, 12, 70, 23);

        TAlmt.setForeground(new java.awt.Color(0, 0, 0));
        TAlmt.setHighlighter(null);
        TAlmt.setName("TAlmt"); // NOI18N
        TAlmt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlmtKeyPressed(evt);
            }
        });
        FormInput.add(TAlmt);
        TAlmt.setBounds(500, 72, 250, 23);

        BtnPasien.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPasien.setMnemonic('1');
        BtnPasien.setToolTipText("ALt+1");
        BtnPasien.setName("BtnPasien"); // NOI18N
        BtnPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasienActionPerformed(evt);
            }
        });
        FormInput.add(BtnPasien);
        BtnPasien.setBounds(852, 12, 28, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(623, 12, 224, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Hubungan :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(648, 42, 98, 23);

        THbngn.setForeground(new java.awt.Color(0, 0, 0));
        THbngn.setHighlighter(null);
        THbngn.setName("THbngn"); // NOI18N
        THbngn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THbngnKeyPressed(evt);
            }
        });
        FormInput.add(THbngn);
        THbngn.setBounds(750, 42, 130, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Poli / Unit :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(0, 102, 77, 23);

        TPoli.setEditable(false);
        TPoli.setForeground(new java.awt.Color(0, 0, 0));
        TPoli.setName("TPoli"); // NOI18N
        FormInput.add(TPoli);
        TPoli.setBounds(133, 100, 135, 23);

        TBiaya.setEditable(false);
        TBiaya.setForeground(new java.awt.Color(0, 0, 0));
        TBiaya.setName("TBiaya"); // NOI18N
        TBiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBiayaKeyPressed(evt);
            }
        });
        FormInput.add(TBiaya);
        TBiaya.setBounds(270, 100, 94, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        kddokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kddokterKeyPressed(evt);
            }
        });
        FormInput.add(kddokter);
        kddokter.setBounds(80, 130, 70, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('3');
        BtnDokter.setToolTipText("ALt+3");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(366, 130, 28, 23);

        kdpoli.setEditable(false);
        kdpoli.setForeground(new java.awt.Color(0, 0, 0));
        kdpoli.setHighlighter(null);
        kdpoli.setName("kdpoli"); // NOI18N
        kdpoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpoliKeyPressed(evt);
            }
        });
        FormInput.add(kdpoli);
        kdpoli.setBounds(80, 100, 50, 23);

        BtnUnit.setForeground(new java.awt.Color(0, 0, 0));
        BtnUnit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnUnit.setMnemonic('4');
        BtnUnit.setToolTipText("ALt+4");
        BtnUnit.setName("BtnUnit"); // NOI18N
        BtnUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUnitActionPerformed(evt);
            }
        });
        FormInput.add(BtnUnit);
        BtnUnit.setBounds(366, 100, 28, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Status :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(748, 72, 50, 23);

        TStatus.setEditable(false);
        TStatus.setForeground(new java.awt.Color(0, 0, 0));
        TStatus.setHighlighter(null);
        TStatus.setName("TStatus"); // NOI18N
        FormInput.add(TStatus);
        TStatus.setBounds(800, 72, 80, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Jenis Bayar :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(396, 102, 100, 23);

        kdpnj.setEditable(false);
        kdpnj.setForeground(new java.awt.Color(0, 0, 0));
        kdpnj.setHighlighter(null);
        kdpnj.setName("kdpnj"); // NOI18N
        kdpnj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpnjKeyPressed(evt);
            }
        });
        FormInput.add(kdpnj);
        kdpnj.setBounds(500, 102, 70, 23);

        nmpnj.setEditable(false);
        nmpnj.setForeground(new java.awt.Color(0, 0, 0));
        nmpnj.setName("nmpnj"); // NOI18N
        FormInput.add(nmpnj);
        nmpnj.setBounds(572, 102, 279, 23);

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
        FormInput.add(btnPenjab);
        btnPenjab.setBounds(852, 102, 28, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Asal Rujukan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(396, 132, 100, 23);

        AsalRujukan.setEditable(false);
        AsalRujukan.setForeground(new java.awt.Color(0, 0, 0));
        AsalRujukan.setName("AsalRujukan"); // NOI18N
        AsalRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsalRujukanKeyPressed(evt);
            }
        });
        FormInput.add(AsalRujukan);
        AsalRujukan.setBounds(500, 132, 351, 23);

        btnPenjab1.setForeground(new java.awt.Color(0, 0, 0));
        btnPenjab1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPenjab1.setMnemonic('2');
        btnPenjab1.setToolTipText("ALt+2");
        btnPenjab1.setName("btnPenjab1"); // NOI18N
        btnPenjab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPenjab1ActionPerformed(evt);
            }
        });
        FormInput.add(btnPenjab1);
        btnPenjab1.setBounds(852, 132, 28, 23);

        ChkTracker.setBackground(new java.awt.Color(235, 255, 235));
        ChkTracker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkTracker.setForeground(new java.awt.Color(0, 0, 0));
        ChkTracker.setBorderPainted(true);
        ChkTracker.setBorderPaintedFlat(true);
        ChkTracker.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkTracker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkTracker.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkTracker.setName("ChkTracker"); // NOI18N
        ChkTracker.setOpaque(false);
        FormInput.add(ChkTracker);
        ChkTracker.setBounds(153, 12, 23, 23);

        JnsnoID.setForeground(new java.awt.Color(0, 0, 0));
        JnsnoID.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "No. Rekam Medik", "No. Kartu BPJS", "NIK KTP" }));
        JnsnoID.setName("JnsnoID"); // NOI18N
        JnsnoID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JnsnoIDItemStateChanged(evt);
            }
        });
        JnsnoID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JnsnoIDMouseClicked(evt);
            }
        });
        JnsnoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JnsnoIDKeyPressed(evt);
            }
        });
        FormInput.add(JnsnoID);
        JnsnoID.setBounds(385, 12, 110, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Reg. :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 12, 77, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Suku/Bangsa : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(890, 12, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Bahasa Dipakai : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(890, 42, 100, 23);

        nmsuku.setEditable(false);
        nmsuku.setForeground(new java.awt.Color(0, 0, 0));
        nmsuku.setName("nmsuku"); // NOI18N
        FormInput.add(nmsuku);
        nmsuku.setBounds(990, 12, 160, 23);

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
        FormInput.add(BtnSuku);
        BtnSuku.setBounds(1155, 12, 28, 23);

        nmbahasa.setEditable(false);
        nmbahasa.setForeground(new java.awt.Color(0, 0, 0));
        nmbahasa.setName("nmbahasa"); // NOI18N
        FormInput.add(nmbahasa);
        nmbahasa.setBounds(990, 42, 160, 23);

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
        FormInput.add(BtnBahasa);
        BtnBahasa.setBounds(1155, 42, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Info SEP BPJS : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(890, 72, 100, 23);

        label_pesan.setEditable(false);
        label_pesan.setColumns(20);
        label_pesan.setForeground(new java.awt.Color(0, 0, 255));
        label_pesan.setRows(5);
        label_pesan.setText("-");
        label_pesan.setName("label_pesan"); // NOI18N
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
        FormInput.add(label_pesan);
        label_pesan.setBounds(990, 71, 270, 85);

        tulisan_tanggal.setForeground(new java.awt.Color(0, 0, 0));
        tulisan_tanggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tulisan_tanggal.setText("tulisan_tanggal");
        tulisan_tanggal.setName("tulisan_tanggal"); // NOI18N
        FormInput.add(tulisan_tanggal);
        tulisan_tanggal.setBounds(81, 72, 300, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

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

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setLayout(new java.awt.BorderLayout());

        Scroll.setToolTipText("Klik data di tabel, kemudian klik kanan untuk memilih menu yang diinginkan");
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ScrollMouseClicked(evt);
            }
        });

        tbregistrasiRalan.setAutoCreateRowSorter(true);
        tbregistrasiRalan.setToolTipText("Klik data di table, kemudian klik kanan untuk memilih menu yang diinginkan");
        tbregistrasiRalan.setComponentPopupMenu(jPopupMenu1);
        tbregistrasiRalan.setName("tbregistrasiRalan"); // NOI18N
        tbregistrasiRalan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbregistrasiRalanMouseClicked(evt);
            }
        });
        tbregistrasiRalan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbregistrasiRalanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbregistrasiRalan);

        internalFrame17.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Dokter :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(jLabel14);

        CrDokter.setEditable(false);
        CrDokter.setForeground(new java.awt.Color(0, 0, 0));
        CrDokter.setName("CrDokter"); // NOI18N
        CrDokter.setPreferredSize(new java.awt.Dimension(300, 23));
        panelGlass8.add(CrDokter);

        BtnSeek3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek3.setMnemonic('6');
        BtnSeek3.setToolTipText("ALt+6");
        BtnSeek3.setName("BtnSeek3"); // NOI18N
        BtnSeek3.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek3ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSeek3);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Poliklinik / Unit :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(125, 23));
        panelGlass8.add(jLabel16);

        CrPoli.setEditable(false);
        CrPoli.setForeground(new java.awt.Color(0, 0, 0));
        CrPoli.setName("CrPoli"); // NOI18N
        CrPoli.setPreferredSize(new java.awt.Dimension(300, 23));
        panelGlass8.add(CrPoli);

        BtnSeek4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek4.setMnemonic('5');
        BtnSeek4.setToolTipText("ALt+5");
        BtnSeek4.setName("BtnSeek4"); // NOI18N
        BtnSeek4.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek4ActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnSeek4);

        panelGlass12.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Periode :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass7.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass7.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(158, 23));
        panelGlass7.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(300, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass7.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('7');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+7");
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
        panelGlass7.add(BtnCari);

        panelGlass12.add(panelGlass7, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelGlass6.add(BtnSimpan);

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
        panelGlass6.add(BtnBatal);

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
        panelGlass6.add(BtnEdit);

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
        panelGlass6.add(BtnHapus);

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
        panelGlass6.add(BtnPrint);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 30));
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
        jLabel10.setPreferredSize(new java.awt.Dimension(70, 30));
        panelGlass6.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(72, 30));
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

        panelGlass12.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        internalFrame17.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab(".: Registrasi Polikllinik", internalFrame17);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(494, 492));
        internalFrame2.setLayout(new java.awt.BorderLayout());

        panelGlass9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ OPERATOR ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 120));
        panelGlass9.setLayout(null);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tentukan Pilihan Jenis Loket : ");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass9.add(jLabel9);
        jLabel9.setBounds(0, 20, 170, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Tentukan Pilihan Nomor Loket : ");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass9.add(jLabel38);
        jLabel38.setBounds(0, 50, 170, 23);

        loket1.setBackground(new java.awt.Color(240, 250, 230));
        loket1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket1);
        loket1.setText("1 (SATU)");
        loket1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket1.setName("loket1"); // NOI18N
        loket1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket1);
        loket1.setBounds(175, 50, 80, 23);

        loket2.setBackground(new java.awt.Color(240, 250, 230));
        loket2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket2);
        loket2.setText("2 (DUA)");
        loket2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket2.setName("loket2"); // NOI18N
        loket2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket2);
        loket2.setBounds(262, 50, 80, 23);

        loket3.setBackground(new java.awt.Color(240, 250, 230));
        loket3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket3);
        loket3.setText("3 (TIGA)");
        loket3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket3.setName("loket3"); // NOI18N
        loket3.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket3);
        loket3.setBounds(349, 50, 80, 23);

        loket4.setBackground(new java.awt.Color(240, 250, 230));
        loket4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket4);
        loket4.setText("4 (EMPAT)");
        loket4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket4.setName("loket4"); // NOI18N
        loket4.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket4);
        loket4.setBounds(436, 50, 80, 23);

        loket5.setBackground(new java.awt.Color(240, 250, 230));
        loket5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket5);
        loket5.setText("5 (LIMA)");
        loket5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket5.setName("loket5"); // NOI18N
        loket5.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket5);
        loket5.setBounds(523, 50, 80, 23);

        loket6.setBackground(new java.awt.Color(240, 250, 230));
        loket6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup2.add(loket6);
        loket6.setText("6 (ENAM)");
        loket6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loket6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loket6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loket6.setName("loket6"); // NOI18N
        loket6.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(loket6);
        loket6.setBounds(610, 50, 80, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Status Operator : ");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass9.add(jLabel45);
        jLabel45.setBounds(0, 80, 170, 23);

        statusOperator.setForeground(new java.awt.Color(0, 0, 0));
        statusOperator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusOperator.setText("statusOperator");
        statusOperator.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        statusOperator.setName("statusOperator"); // NOI18N
        panelGlass9.add(statusOperator);
        statusOperator.setBounds(175, 80, 950, 23);

        loketRalan.setBackground(new java.awt.Color(235, 255, 235));
        loketRalan.setBorder(null);
        buttonGroup1.add(loketRalan);
        loketRalan.setForeground(new java.awt.Color(0, 0, 0));
        loketRalan.setText("RAWAT JALAN");
        loketRalan.setBorderPainted(true);
        loketRalan.setBorderPaintedFlat(true);
        loketRalan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loketRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loketRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loketRalan.setName("loketRalan"); // NOI18N
        loketRalan.setOpaque(false);
        panelGlass9.add(loketRalan);
        loketRalan.setBounds(175, 20, 110, 23);

        loketRanap.setBackground(new java.awt.Color(235, 255, 235));
        loketRanap.setBorder(null);
        buttonGroup1.add(loketRanap);
        loketRanap.setForeground(new java.awt.Color(0, 0, 0));
        loketRanap.setText("RAWAT INAP");
        loketRanap.setBorderPainted(true);
        loketRanap.setBorderPaintedFlat(true);
        loketRanap.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        loketRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        loketRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loketRanap.setName("loketRanap"); // NOI18N
        loketRanap.setOpaque(false);
        panelGlass9.add(loketRanap);
        loketRanap.setBounds(300, 20, 110, 23);

        internalFrame2.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        Scroll1.setToolTipText("");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(492, 800));

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(490, 300));
        FormInput1.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ PANGGILAN ANTRIAN ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Nomor Pasien Rawat Jalan BPJS ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame25.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame25.setName("internalFrame25"); // NOI18N
        internalFrame25.setPreferredSize(new java.awt.Dimension(0, 130));
        internalFrame25.setWarnaBawah(new java.awt.Color(153, 255, 0));
        internalFrame25.setLayout(null);

        nobpjs.setForeground(new java.awt.Color(0, 0, 0));
        nobpjs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nobpjs.setText("nobpjs");
        nobpjs.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        nobpjs.setName("nobpjs"); // NOI18N
        internalFrame25.add(nobpjs);
        nobpjs.setBounds(0, 20, 220, 70);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setText("PANGGILAN ANTRIAN");
        jLabel51.setName("jLabel51"); // NOI18N
        internalFrame25.add(jLabel51);
        jLabel51.setBounds(226, 100, 120, 23);

        BtnPangBPJS.setForeground(new java.awt.Color(0, 0, 0));
        BtnPangBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icons8-megaphone-64.png"))); // NOI18N
        BtnPangBPJS.setToolTipText("");
        BtnPangBPJS.setGlassColor(new java.awt.Color(0, 102, 0));
        BtnPangBPJS.setName("BtnPangBPJS"); // NOI18N
        BtnPangBPJS.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPangBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPangBPJSActionPerformed(evt);
            }
        });
        internalFrame25.add(BtnPangBPJS);
        BtnPangBPJS.setBounds(240, 20, 90, 70);

        BtnUlangPangBPJS.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangPangBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/system-software-update.png"))); // NOI18N
        BtnUlangPangBPJS.setToolTipText("");
        BtnUlangPangBPJS.setGlassColor(new java.awt.Color(0, 102, 0));
        BtnUlangPangBPJS.setName("BtnUlangPangBPJS"); // NOI18N
        BtnUlangPangBPJS.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUlangPangBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangPangBPJSActionPerformed(evt);
            }
        });
        internalFrame25.add(BtnUlangPangBPJS);
        BtnUlangPangBPJS.setBounds(400, 20, 68, 70);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel52.setText("ULANGI PANGGILAN");
        jLabel52.setName("jLabel52"); // NOI18N
        internalFrame25.add(jLabel52);
        jLabel52.setBounds(375, 100, 120, 23);

        jPanel4.add(internalFrame25, java.awt.BorderLayout.PAGE_START);

        internalFrame26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Nomor Pasien Rawat Jalan Umum (NON BPJS) ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame26.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame26.setName("internalFrame26"); // NOI18N
        internalFrame26.setPreferredSize(new java.awt.Dimension(0, 130));
        internalFrame26.setWarnaBawah(new java.awt.Color(153, 153, 153));
        internalFrame26.setLayout(null);

        noumum.setForeground(new java.awt.Color(0, 0, 0));
        noumum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noumum.setText("noumum");
        noumum.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        noumum.setName("noumum"); // NOI18N
        internalFrame26.add(noumum);
        noumum.setBounds(0, 20, 220, 70);

        BtnPangUmum.setForeground(new java.awt.Color(0, 0, 0));
        BtnPangUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icons8-megaphone-64.png"))); // NOI18N
        BtnPangUmum.setToolTipText("");
        BtnPangUmum.setGlassColor(new java.awt.Color(0, 0, 0));
        BtnPangUmum.setName("BtnPangUmum"); // NOI18N
        BtnPangUmum.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPangUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPangUmumActionPerformed(evt);
            }
        });
        internalFrame26.add(BtnPangUmum);
        BtnPangUmum.setBounds(240, 20, 90, 70);

        BtnUlangPangUmum.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangPangUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/system-software-update.png"))); // NOI18N
        BtnUlangPangUmum.setToolTipText("");
        BtnUlangPangUmum.setGlassColor(new java.awt.Color(0, 0, 0));
        BtnUlangPangUmum.setName("BtnUlangPangUmum"); // NOI18N
        BtnUlangPangUmum.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUlangPangUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangPangUmumActionPerformed(evt);
            }
        });
        internalFrame26.add(BtnUlangPangUmum);
        BtnUlangPangUmum.setBounds(400, 20, 68, 70);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel53.setText("ULANGI PANGGILAN");
        jLabel53.setName("jLabel53"); // NOI18N
        internalFrame26.add(jLabel53);
        jLabel53.setBounds(375, 100, 120, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("PANGGILAN ANTRIAN");
        jLabel54.setName("jLabel54"); // NOI18N
        internalFrame26.add(jLabel54);
        jLabel54.setBounds(226, 100, 120, 23);

        jPanel4.add(internalFrame26, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        internalFrame27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Nomor Pasien Rawat Jalan LANSIA / BAYI ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame27.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame27.setName("internalFrame27"); // NOI18N
        internalFrame27.setPreferredSize(new java.awt.Dimension(0, 130));
        internalFrame27.setWarnaBawah(new java.awt.Color(220, 124, 220));
        internalFrame27.setLayout(null);

        nolansia.setForeground(new java.awt.Color(0, 0, 0));
        nolansia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nolansia.setText("nolansia");
        nolansia.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        nolansia.setName("nolansia"); // NOI18N
        internalFrame27.add(nolansia);
        nolansia.setBounds(0, 20, 220, 70);

        BtnPangLansia.setForeground(new java.awt.Color(0, 0, 0));
        BtnPangLansia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icons8-megaphone-64.png"))); // NOI18N
        BtnPangLansia.setToolTipText("");
        BtnPangLansia.setGlassColor(new java.awt.Color(102, 0, 102));
        BtnPangLansia.setName("BtnPangLansia"); // NOI18N
        BtnPangLansia.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPangLansia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPangLansiaActionPerformed(evt);
            }
        });
        internalFrame27.add(BtnPangLansia);
        BtnPangLansia.setBounds(240, 20, 90, 70);

        BtnUlangPangLansia.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangPangLansia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/system-software-update.png"))); // NOI18N
        BtnUlangPangLansia.setToolTipText("");
        BtnUlangPangLansia.setGlassColor(new java.awt.Color(102, 0, 102));
        BtnUlangPangLansia.setName("BtnUlangPangLansia"); // NOI18N
        BtnUlangPangLansia.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUlangPangLansia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangPangLansiaActionPerformed(evt);
            }
        });
        internalFrame27.add(BtnUlangPangLansia);
        BtnUlangPangLansia.setBounds(400, 20, 68, 70);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("ULANGI PANGGILAN");
        jLabel55.setName("jLabel55"); // NOI18N
        internalFrame27.add(jLabel55);
        jLabel55.setBounds(375, 100, 120, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("PANGGILAN ANTRIAN");
        jLabel56.setName("jLabel56"); // NOI18N
        internalFrame27.add(jLabel56);
        jLabel56.setBounds(226, 100, 120, 23);

        jPanel2.add(internalFrame27, java.awt.BorderLayout.PAGE_START);

        internalFrame28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Nomor Pasien Rawat Inap ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame28.setName("internalFrame28"); // NOI18N
        internalFrame28.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame28.setWarnaBawah(new java.awt.Color(255, 255, 0));
        internalFrame28.setLayout(null);

        noinap.setForeground(new java.awt.Color(0, 0, 0));
        noinap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        noinap.setText("noinap");
        noinap.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        noinap.setName("noinap"); // NOI18N
        internalFrame28.add(noinap);
        noinap.setBounds(0, 20, 220, 70);

        BtnPangInap.setForeground(new java.awt.Color(0, 0, 0));
        BtnPangInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/icons8-megaphone-64.png"))); // NOI18N
        BtnPangInap.setToolTipText("");
        BtnPangInap.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnPangInap.setName("BtnPangInap"); // NOI18N
        BtnPangInap.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPangInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPangInapActionPerformed(evt);
            }
        });
        internalFrame28.add(BtnPangInap);
        BtnPangInap.setBounds(240, 20, 90, 70);

        BtnUlangPangInap.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangPangInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/48x48/system-software-update.png"))); // NOI18N
        BtnUlangPangInap.setToolTipText("");
        BtnUlangPangInap.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnUlangPangInap.setName("BtnUlangPangInap"); // NOI18N
        BtnUlangPangInap.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUlangPangInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangPangInapActionPerformed(evt);
            }
        });
        internalFrame28.add(BtnUlangPangInap);
        BtnUlangPangInap.setBounds(400, 20, 68, 70);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("PANGGILAN ANTRIAN");
        jLabel57.setName("jLabel57"); // NOI18N
        internalFrame28.add(jLabel57);
        jLabel57.setBounds(226, 100, 120, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("ULANGI PANGGILAN");
        jLabel58.setName("jLabel58"); // NOI18N
        internalFrame28.add(jLabel58);
        jLabel58.setBounds(375, 100, 120, 23);

        jPanel2.add(internalFrame28, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        FormInput1.add(jPanel1, java.awt.BorderLayout.CENTER);

        Scroll1.setViewportView(FormInput1);

        internalFrame2.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpanOperator.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/man1-24.png"))); // NOI18N
        BtnSimpanOperator.setMnemonic('O');
        BtnSimpanOperator.setText("Simpan Operator");
        BtnSimpanOperator.setToolTipText("Alt+O");
        BtnSimpanOperator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnSimpanOperator.setIconTextGap(5);
        BtnSimpanOperator.setName("BtnSimpanOperator"); // NOI18N
        BtnSimpanOperator.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnSimpanOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanOperatorActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnSimpanOperator);

        BtnSimpanOperator1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanOperator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/icons8-audio-24.png"))); // NOI18N
        BtnSimpanOperator1.setMnemonic('U');
        BtnSimpanOperator1.setText("Cek Audio");
        BtnSimpanOperator1.setToolTipText("Alt+U");
        BtnSimpanOperator1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnSimpanOperator1.setIconTextGap(5);
        BtnSimpanOperator1.setName("BtnSimpanOperator1"); // NOI18N
        BtnSimpanOperator1.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnSimpanOperator1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanOperator1ActionPerformed(evt);
            }
        });
        panelGlass14.add(BtnSimpanOperator1);

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
        panelGlass14.add(BtnKeluar1);

        internalFrame2.add(panelGlass14, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab(".: Operator & Panggilan Antrian", internalFrame2);

        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout());

        internalFrame18.setBorder(null);
        internalFrame18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(0, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame20.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan BPJS ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame20.setWarnaBawah(new java.awt.Color(204, 255, 153));
        internalFrame20.setLayout(null);

        infobpjs1.setForeground(new java.awt.Color(0, 0, 0));
        infobpjs1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infobpjs1.setText("infobpjs1");
        infobpjs1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infobpjs1.setName("infobpjs1"); // NOI18N
        internalFrame20.add(infobpjs1);
        infobpjs1.setBounds(20, 20, 1000, 23);

        infobpjs2.setForeground(new java.awt.Color(0, 0, 0));
        infobpjs2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infobpjs2.setText("infobpjs2");
        infobpjs2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infobpjs2.setName("infobpjs2"); // NOI18N
        internalFrame20.add(infobpjs2);
        infobpjs2.setBounds(20, 47, 1000, 23);

        internalFrame18.add(internalFrame20, java.awt.BorderLayout.PAGE_START);

        internalFrame21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan Umum (NON BPJS) ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame21.setWarnaBawah(new java.awt.Color(204, 204, 204));
        internalFrame21.setLayout(null);

        infoumum1.setForeground(new java.awt.Color(0, 0, 0));
        infoumum1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoumum1.setText("infoumum1");
        infoumum1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoumum1.setName("infoumum1"); // NOI18N
        internalFrame21.add(infoumum1);
        infoumum1.setBounds(20, 20, 1000, 23);

        infoumum2.setForeground(new java.awt.Color(0, 0, 0));
        infoumum2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoumum2.setText("infoumum2");
        infoumum2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infoumum2.setName("infoumum2"); // NOI18N
        internalFrame21.add(infoumum2);
        infoumum2.setBounds(20, 47, 1000, 23);

        internalFrame18.add(internalFrame21, java.awt.BorderLayout.CENTER);

        internalFrame22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Jalan LANSIA / BAYI ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame22.setName("internalFrame22"); // NOI18N
        internalFrame22.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame22.setWarnaBawah(new java.awt.Color(220, 124, 220));
        internalFrame22.setLayout(null);

        infolb1.setForeground(new java.awt.Color(0, 0, 0));
        infolb1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infolb1.setText("infolb1");
        infolb1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infolb1.setName("infolb1"); // NOI18N
        internalFrame22.add(infolb1);
        infolb1.setBounds(20, 20, 1000, 23);

        infolb2.setForeground(new java.awt.Color(0, 0, 0));
        infolb2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infolb2.setText("infolb2");
        infolb2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        infolb2.setName("infolb2"); // NOI18N
        internalFrame22.add(infolb2);
        infolb2.setBounds(20, 47, 1000, 23);

        internalFrame18.add(internalFrame22, java.awt.BorderLayout.PAGE_END);

        internalFrame11.add(internalFrame18, java.awt.BorderLayout.PAGE_START);

        internalFrame19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Rawat Inap ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        internalFrame19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(0, 80));
        internalFrame19.setWarnaBawah(new java.awt.Color(255, 255, 153));
        internalFrame19.setLayout(null);

        inforanap1.setForeground(new java.awt.Color(0, 0, 0));
        inforanap1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inforanap1.setText("inforanap1");
        inforanap1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inforanap1.setName("inforanap1"); // NOI18N
        internalFrame19.add(inforanap1);
        inforanap1.setBounds(20, 20, 1000, 23);

        inforanap2.setForeground(new java.awt.Color(0, 0, 0));
        inforanap2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        inforanap2.setText("inforanap2");
        inforanap2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        inforanap2.setName("inforanap2"); // NOI18N
        internalFrame19.add(inforanap2);
        inforanap2.setBounds(20, 47, 1000, 23);

        internalFrame11.add(internalFrame19, java.awt.BorderLayout.CENTER);

        TabRawat.addTab(".: Informasi Antrian", internalFrame11);

        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout());

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass13.setLayout(null);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Jenis Reset Nomor : ");
        jLabel61.setName("jLabel61"); // NOI18N
        jLabel61.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass13.add(jLabel61);
        jLabel61.setBounds(0, 10, 140, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Nomor Terakhir : ");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass13.add(jLabel63);
        jLabel63.setBounds(375, 10, 100, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Tgl. Reset : ");
        jLabel64.setName("jLabel64"); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass13.add(jLabel64);
        jLabel64.setBounds(0, 40, 140, 23);

        jnsReset.setEditable(false);
        jnsReset.setForeground(new java.awt.Color(0, 0, 0));
        jnsReset.setName("jnsReset"); // NOI18N
        panelGlass13.add(jnsReset);
        jnsReset.setBounds(143, 10, 230, 23);

        noTerakhir.setEditable(false);
        noTerakhir.setForeground(new java.awt.Color(0, 0, 0));
        noTerakhir.setName("noTerakhir"); // NOI18N
        panelGlass13.add(noTerakhir);
        noTerakhir.setBounds(478, 10, 60, 23);

        tglReset.setEditable(false);
        tglReset.setForeground(new java.awt.Color(0, 0, 0));
        tglReset.setName("tglReset"); // NOI18N
        panelGlass13.add(tglReset);
        tglReset.setBounds(143, 40, 230, 23);

        internalFrame12.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " History Reset Nomor Antrian Pasien ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setToolTipText("");
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Scroll2MouseClicked(evt);
            }
        });

        tbrestorAntrian.setAutoCreateRowSorter(true);
        tbrestorAntrian.setToolTipText("");
        tbrestorAntrian.setName("tbrestorAntrian"); // NOI18N
        tbrestorAntrian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbrestorAntrianMouseClicked(evt);
            }
        });
        tbrestorAntrian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbrestorAntrianKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbrestorAntrian);

        internalFrame12.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Tgl. Reset : ");
        jLabel59.setName("jLabel59"); // NOI18N
        jLabel59.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass11.add(jLabel59);

        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(tglA);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("s.d");
        jLabel60.setName("jLabel60"); // NOI18N
        jLabel60.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass11.add(jLabel60);

        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-08-2023" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass11.add(tglB);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('7');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+7");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass11.add(BtnCari1);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('7');
        BtnRestor.setText("Restore Diproses");
        BtnRestor.setToolTipText("Alt+7");
        BtnRestor.setIconTextGap(8);
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnRestor);

        BtnReset.setForeground(new java.awt.Color(0, 0, 0));
        BtnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnReset.setMnemonic('N');
        BtnReset.setText("Reset Nomor");
        BtnReset.setToolTipText("Alt+N");
        BtnReset.setIconTextGap(8);
        BtnReset.setName("BtnReset"); // NOI18N
        BtnReset.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResetActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnReset);

        BtnKeluar6.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar6.setMnemonic('K');
        BtnKeluar6.setText("Keluar");
        BtnKeluar6.setToolTipText("Alt+K");
        BtnKeluar6.setName("BtnKeluar6"); // NOI18N
        BtnKeluar6.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar6ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnKeluar6);

        internalFrame12.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab(".: Restore Nomor Antrian", internalFrame12);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        Valid.pindah(evt, TNoReg, BtnUnit);
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPngJwbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPngJwbKeyPressed
        Valid.pindah(evt, TNoID, THbngn);
}//GEN-LAST:event_TPngJwbKeyPressed

    private void TNoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoIDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isPas();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnPasienActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            if (!TPasien.getText().equals("")) {
                Map<String, Object> param = new HashMap<>();
                param.put("poli", TPoli.getText());
                param.put("antrian", TNoReg.getText());
                param.put("nama", TPasien.getText());
                param.put("norm", TNoID.getText());
                param.put("bayar", nmpnj.getText());
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                Valid.MyReport("rptCheckList.jasper", "report", "::[ Check List ]::",
                        "select current_date() as sekarang", param);
            }
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            isPas();
            TPngJwb.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kdpoli.requestFocus();
        }
}//GEN-LAST:event_TNoIDKeyPressed

    private void TNoRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRegKeyPressed
        Valid.pindah(evt, TCari, TNoRw);
}//GEN-LAST:event_TNoRegKeyPressed

    private void TAlmtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlmtKeyPressed
        Valid.pindah(evt, THbngn, kdpnj);
}//GEN-LAST:event_TAlmtKeyPressed

    private void BtnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasienActionPerformed
        akses.setform("DlgReg");
        pasien.isiKeterangan("Daftar Pasien Poli");
        pasien.penjab.removeWindowListener(null);
        pasien.penjab.getTable().removeKeyListener(null);
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
}//GEN-LAST:event_BtnPasienActionPerformed

    private void THbngnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THbngnKeyPressed
        Valid.pindah(evt, TPngJwb, TAlmt);
}//GEN-LAST:event_THbngnKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        rmMati.setText(Sequel.cariIsi("select no_rkm_medis from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where tgl_registrasi='" + tglDaftar + "' and "
                + "kd_poli='" + kdpoli.getText() + "' and no_rkm_medis=?", cekPasien, TNoRM.getText());
        cekSEP = Sequel.cariInteger("SELECT count(-1) tgl FROM kelengkapan_booking_sep_bpjs k "
                + "INNER JOIN booking_registrasi b on b.kd_booking=k.kd_booking WHERE k.status_cetak_sep='gagal' and b.tanggal_periksa <DATE(NOW())");

        if (TNoReg.getText().trim().equals("")) {
            Valid.textKosong(TNoReg, "No.Regristrasi");
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (TDokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "dokter");
        } else if (TNoID.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoID, "pasien");
        } else if (TPoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "poliklinik");
        } else if (kdpoli.getText().equals("RAD") && kode_rujukanya.getText().equals("")) {
            Valid.textKosong(kode_rujukanya, "Asal Rujukan Pasien");
        } else if (TBiaya.getText().trim().equals("")) {
            Valid.textKosong(TBiaya, "biaya regristrasi");
        } else if (kdpnj.getText().trim().equals("") || nmpnj.getText().trim().equals("")) {
            Valid.textKosong(kdpnj, "Jenis Bayar");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", TNoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
            TNoID.requestFocus();
        } else if (!cekPasien.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini sdh. terdaftar dipoli & tanggal yang sama...");
            BtnUnit.requestFocus();
        } else if (!rmMati.getText().equals("")) {
            jamMati.setText("Jam : " + Sequel.cariIsi("select jam from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));
            tglMati.setText("Tgl. : " + Sequel.cariIsi("select DATE_FORMAT(tanggal,'%d-%m-%Y') tgl from pasien_mati where no_rkm_medis='" + TNoRM.getText() + "' "));

            JOptionPane.showMessageDialog(null, "Pasien tersebut sudah dinyatakan meninggal pada " + tglMati.getText() + " " + jamMati.getText() + ", data tidak dapat disimpan.");
            ChkInput.setSelected(true);
            isForm();
            emptTeks();
        } else if (cekUmur > Sequel.cariInteger("select batas_maksimal from set_batas_umur") || cekUmur < Sequel.cariInteger("select batas_minimal from set_batas_umur")) {
            JOptionPane.showMessageDialog(null, "Silahkan perbaiki dulu tgl. lahir pasien ini, karena umurnya " + umur + " " + sttsumur1);
        } else {
            if (cekSEP == 0 || kdpoli.getText().equals("-") || kdpoli.getText().equals("HDL") || kdpoli.getText().equals("RAD")
                    || kdpoli.getText().equals("LAA") || kdpoli.getText().equals("LAB")) {
                if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                        new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                            kddokter.getText(), TNoRM.getText(), kdpoli.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                            TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {
                    simpanIKM();
                    UpdateUmur();
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");

                    if (!AsalRujukan.getText().equals("")) {
                        Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                    }
                    if (ChkTracker.isSelected() == true) {
                        ctk();
                    }
                    tampilAwal();
                    emptTeks();
                } else {
                    isNumber();
                    if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                            new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                kddokter.getText(), TNoRM.getText(), kdpoli.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                                TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {
                        simpanIKM();
                        UpdateUmur();
                        Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");

                        if (!AsalRujukan.getText().equals("")) {
                            Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                    + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                        }
                        if (ChkTracker.isSelected() == true) {
                            ctk();
                        }
                        tampilAwal();
                        emptTeks();
                    } else {
                        isNumber();
                        if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                                new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                    kddokter.getText(), TNoRM.getText(), kdpoli.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                                    TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {
                            simpanIKM();
                            UpdateUmur();
                            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");

                            if (!AsalRujukan.getText().equals("")) {
                                Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                        + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                            }
                            if (ChkTracker.isSelected() == true) {
                                ctk();
                            }
                            tampilAwal();
                            emptTeks();
                        } else {
                            isNumber();
                            if (Sequel.menyimpantf2("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                                    new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                        kddokter.getText(), TNoRM.getText(), kdpoli.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                                        TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {
                                simpanIKM();
                                UpdateUmur();
                                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                                Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");

                                if (!AsalRujukan.getText().equals("")) {
                                    Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                            + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                                }
                                if (ChkTracker.isSelected() == true) {
                                    ctk();
                                }
                                tampilAwal();
                                emptTeks();
                            } else {
                                isNumber();
                                if (Sequel.menyimpantf("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                                        new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                                            kddokter.getText(), TNoRM.getText(), kdpoli.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                                            TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {
                                    simpanIKM();
                                    UpdateUmur();
                                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                                    Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Simpan'");

                                    if (!AsalRujukan.getText().equals("")) {
                                        Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                                                + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                                    }
                                    if (ChkTracker.isSelected() == true) {
                                        ctk();
                                    }
                                    tampilAwal();
                                    emptTeks();
                                } else {
                                    TNoID.requestFocus();
                                    isNumber();
                                }
                            }
                        }
                    }
                }
                
            } else if ((cekSEP >= 1 && !kdpoli.getText().equals("-")) || (cekSEP >= 1 && !kdpoli.getText().equals("HDL"))
                    || (cekSEP >= 1 && !kdpoli.getText().equals("RAD")) || (cekSEP >= 1 && !kdpoli.getText().equals("LAB"))
                    || (cekSEP >= 1 && !kdpoli.getText().equals("LAA"))) {
                JOptionPane.showMessageDialog(null, "Data tdk. bisa disimpan, silahkan perbaiki dulu SEP BPJS dari menu booking\n"
                        + "untuk yg. sdh. tercetak manual di anjungan elektronik pasien (SIPO)");
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, kdpnj, BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        ChkInput.setSelected(true);
        isForm();
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        cekSEPboking = "";

        for (i = 0; i < tbregistrasiRalan.getRowCount(); i++) {
            if (tbregistrasiRalan.getValueAt(i, 0).toString().equals("true")) {
                sepJkd.setText(Sequel.cariIsi("SELECT bridging_jamkesda.no_sep FROM reg_periksa "
                        + "INNER JOIN bridging_jamkesda ON reg_periksa.no_rawat = bridging_jamkesda.no_rawat "
                        + "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                        + "WHERE bridging_jamkesda.no_rawat='" + TNoRw.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan' AND penjab.png_jawab like '%jamkesda%'"));

                cekSEPboking = Sequel.cariIsi("select kd_booking from booking_registrasi where no_rawat='" + TNoRw.getText() + "'");

                Sequel.meghapus("rujuk_masuk", "no_rawat", tbregistrasiRalan.getValueAt(i, 2).toString());
                Sequel.meghapus("rujuk", "no_rawat", tbregistrasiRalan.getValueAt(i, 2).toString());
                Sequel.meghapus("pasien_mati", "no_rkm_medis", tbregistrasiRalan.getValueAt(i, 7).toString());
                Sequel.meghapus("bridging_jamkesda", "no_sep", sepJkd.getText());
                Sequel.meghapus("reg_periksa", "no_rawat", tbregistrasiRalan.getValueAt(i, 2).toString());
                Sequel.meghapus("reg_rujukan_intern", "no_rawat_ke", tbregistrasiRalan.getValueAt(i, 2).toString());

                if (!cekSEPboking.equals("")) {
                    Sequel.mengedit("kelengkapan_booking_sep_bpjs", "kd_booking='" + cekSEPboking + "'", "status_cetak_sep='BELUM' ");
                    Sequel.mengedit("booking_registrasi", "kd_booking='" + cekSEPboking + "'", "status_booking='Batal' ");
                }

                if (akses.getkode().equals("Admin Utama")) {
                    Sequel.meghapus("nota_inap", "no_rawat", tbregistrasiRalan.getValueAt(i, 2).toString());
                    Sequel.meghapus("nota_jalan", "no_rawat", tbregistrasiRalan.getValueAt(i, 2).toString());
                }
            }
        }
        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Hapus'");
        tampilAwal();
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

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
            Valid.MyReport("rptReg.jasper", "report", "::[ Data Registrasi Periksa ]::", "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,ifnull((select perujuk from rujuk_masuk where rujuk_masuk.no_rawat=reg_periksa.no_rawat),'') as perujuk,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab  "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_poli=poliklinik.kd_poli  "
                    + "where poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_reg like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_rawat like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.tgl_registrasi like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.stts_daftar like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and poliklinik.nm_poli like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.p_jawab like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.almt_pj like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and penjab.png_jawab like '%" + TCari.getText().trim() + "%' or "
                    + "poliklinik.kd_poli<>'IGDK' and dokter.nm_dokter like '%" + CrDokter.getText() + "%' and poliklinik.nm_poli like '%" + CrPoli.getText() + "%' and tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' and reg_periksa.hubunganpj like '%" + TCari.getText().trim() + "%' order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        CrPoli.setText("");
        CrDokter.setText("");
        TCari.setText("");
        tampilAwal();
        emptTeks();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            tampilAwal();
            TCari.setText("");
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoReg.getText().trim().equals("")) {
            Valid.textKosong(TNoReg, "No.Regristrasi");
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (TDokter.getText().trim().equals("")) {
            Valid.textKosong(kddokter, "dokter");
        } else if (TNoID.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoID, "pasien");
        } else if (TPoli.getText().trim().equals("")) {
            Valid.textKosong(kdpoli, "poliklinik");
        } else if (TBiaya.getText().trim().equals("")) {
            Valid.textKosong(TBiaya, "biaya regristrasi");
        } else if (kdsuku.getText().trim().equals("19") || kdsuku.getText().trim().equals("") || kdsuku.getText().trim().equals("-")) {
            Valid.textKosong(kdsuku, "Suku/Bangsa Pasien");
        } else if (kdbahasa.getText().trim().equals("12") || kdbahasa.getText().trim().equals("") || kdbahasa.getText().trim().equals("-")) {
            Valid.textKosong(kdbahasa, "Bahasa Pasien");
        } else if (kode_rujukanya.getText().trim().equals("")) {
            Valid.textKosong(kode_rujukanya, "Asal Rujukan");
        } else {

            cekRujuk = Sequel.cariInteger("select count(-1) from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'");
            if (akses.getedit_registrasi() == true) {
                Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"
                        + "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=? where no_rawat=?", 12,
                        new String[]{TNoRw.getText(), TNoReg.getText(), kddokter.getText(), TNoRM.getText(), kdpoli.getText(),
                            TPngJwb.getText(), TAlmt.getText(), TBiaya.getText(), THbngn.getText(), TStatus.getText(), kdpnj.getText(),
                            tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString()
                        });

                Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                        + "dokter_perujuk='" + AsalRujukan.getText() + "',kd_rujukan='" + kode_rujukanya.getText() + "' ");
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                if (cekRujuk == 0) {
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                    Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "',"
                            + "'-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                } else {
                    Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "'");
                }
            } else {
                if ((Sequel.cariInteger("select count(no_rawat) from rawat_jl_dr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from rawat_jl_pr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from rawat_jl_drpr where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from periksa_lab where no_rawat=?", TNoRw.getText()) > 0)
                        || (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0)) {
                    JOptionPane.showMessageDialog(rootPane, "Maaf pasien sudah ada transaksi sebelumnya & tidak bisa diedit..!!! ");
                    TCari.requestFocus();
                } else {
                    Sequel.queryu2("update reg_periksa set no_rawat=?,no_reg=?,kd_dokter=?,no_rkm_medis=?,kd_poli=?,"
                            + "p_jawab=?,almt_pj=?,biaya_reg=?,hubunganpj=?,stts_daftar=?,kd_pj=? where no_rawat=?", 12,
                            new String[]{TNoRw.getText(), TNoReg.getText(), kddokter.getText(), TNoRM.getText(), kdpoli.getText(),
                                TPngJwb.getText(), TAlmt.getText(), TBiaya.getText(), THbngn.getText(), TStatus.getText(), kdpnj.getText(),
                                tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString()
                            });

                    Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                            + "dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "' ");
                    Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");
                }

                if (cekRujuk == 0) {
                    Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
                    Sequel.menyimpan("rujuk_masuk", "'" + TNoRw.getText() + "','" + AsalRujukan.getText() + "','" + alamatperujuk + "','-','0','" + AsalRujukan.getText() + "','-','-','-','" + NoBalasan.getText() + "','" + kode_rujukanya.getText() + "'", "No.Rujuk");
                } else {
                    Sequel.mengedit("rujuk_masuk", "no_rawat='" + TNoRw.getText() + "'", "perujuk='" + AsalRujukan.getText() + "', alamat='" + alamatperujuk + "', "
                            + "dokter_perujuk='" + AsalRujukan.getText() + "', kd_rujukan='" + kode_rujukanya.getText() + "' ");
                }
            }
            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Registrasi Pasien','Ganti'");
            tampilAwal();
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

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
        tampilAwal();
        infoSEP();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TBiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBiayaKeyPressed
        Valid.pindah(evt, kdpoli, BtnSimpan);
}//GEN-LAST:event_TBiayaKeyPressed

    private void tbregistrasiRalanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbregistrasiRalanMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if (evt.getClickCount() == 2) {
                i = tbregistrasiRalan.getSelectedColumn();
                if (i == 1) {
                    if (akses.getkamar_inap() == true) {
                        MnKamarInapActionPerformed(null);
                    }
                } else if (i == 2) {
                    if (akses.getperiksa_lab() == true) {
                        MnPeriksaLabActionPerformed(null);
                    }
                } else if (i == 3) {
                    if (akses.getrujukan_masuk() == true) {
                        MnRujukMasukActionPerformed(null);
                    }
                } else if (i == 4) {
                    MnCetakRegisterActionPerformed(null);
                }
            }

        }

}//GEN-LAST:event_tbregistrasiRalanMouseClicked

    private void tbregistrasiRalanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbregistrasiRalanKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
                i = tbregistrasiRalan.getSelectedColumn();
                if (i == 1) {
                    if (akses.getkamar_inap() == true) {
                        MnKamarInapActionPerformed(null);
                    }
                } else if (i == 2) {
                    if (akses.getperiksa_lab() == true) {
                        MnPeriksaLabActionPerformed(null);
                    }
                } else if (i == 3) {
                    if (akses.getrujukan_masuk() == true) {
                        MnRujukMasukActionPerformed(null);
                    }
                } else if (i == 4) {
                    MnCetakRegisterActionPerformed(null);
                } else if (i == 5) {
                    MnBuktiPelayananRalanActionPerformed(null);
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_A) {
                for (i = 0; i < tbregistrasiRalan.getRowCount(); i++) {
                    tbregistrasiRalan.setValueAt(true, i, 0);
                }
            }
        }
}//GEN-LAST:event_tbregistrasiRalanKeyPressed

private void kddokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kddokterKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        isNumber();
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, kddokter.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TNoRw, kdpoli);
    }
}//GEN-LAST:event_kddokterKeyPressed

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    pilihan = 1;
    akses.setform("DlgReg");
    if (aktifjadwal.equals("aktif")) {
        if (akses.getkode().equals("Admin Utama")) {
            dokter.isCek();
            dokter.TCari.requestFocus();
            dokter.setSize(1041, 332);
            dokter.setLocationRelativeTo(internalFrame1);
            dokter.setVisible(true);
            dokter.emptTeks();
        } else {
            dokter2.setPoli(TPoli.getText());
            dokter2.isCek();
            dokter2.tampil();
            dokter2.TCari.requestFocus();
            dokter2.setSize(1041, 332);
            dokter2.setLocationRelativeTo(internalFrame1);
            dokter2.setVisible(true);
            dokter2.emptTeks();
        }
    } else {
        dokter.isCek();
        dokter.TCari.requestFocus();
        dokter.setSize(1041, 332);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
        dokter.emptTeks();
    }
}//GEN-LAST:event_BtnDokterActionPerformed

private void kdpoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpoliKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nm_poli from poliklinik where kd_poli=?", TPoli, kdpoli.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnUnitActionPerformed(null);
    } else {
        Valid.pindah(evt, kddokter, TNoID);
    }
}//GEN-LAST:event_kdpoliKeyPressed

private void BtnUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUnitActionPerformed
    TPoli.setText("");
    kdpoli.setText("");
    kddokter.setText("");
    TDokter.setText("");

    akses.setform("DlgReg");
    pilihan = 1;

    if (aktifjadwal.equals("aktif")) {
        if (akses.getkode().equals("Admin Utama")) {
            poli.isCek();
            poli.setSize(1048, 653);
            poli.setLocationRelativeTo(internalFrame1);
            poli.setVisible(true);
            poli.emptTeks();
        } else {
            poli2.isCek();
            poli2.tampil();
            poli2.setSize(1048, 653);
            poli2.setLocationRelativeTo(internalFrame1);
            poli2.setVisible(true);
            poli2.emptTeks();
        }
    } else {
        poli.isCek();
        poli.setSize(1048, 653);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
        poli.emptTeks();
    }
}//GEN-LAST:event_BtnUnitActionPerformed

private void BtnSeek3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek3ActionPerformed
    akses.setform("DlgReg");
    pilihan = 2;
    dokter.isCek();
    dokter.TCari.requestFocus();
    dokter.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    dokter.setLocationRelativeTo(internalFrame1);
    dokter.setVisible(true);
}//GEN-LAST:event_BtnSeek3ActionPerformed

private void BtnSeek4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek4ActionPerformed
    akses.setform("DlgReg");
    pilihan = 2;
    poli.isCek();
    poli.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    poli.setLocationRelativeTo(internalFrame1);
    poli.setVisible(true);
}//GEN-LAST:event_BtnSeek4ActionPerformed

private void MnKamarInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKamarInapActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiRalan.requestFocus();
    } else {
        if (akses.getkode().equals("Admin Utama")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setstatus(true);
            DlgKamarInap dlgki = new DlgKamarInap(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.emptTeks();
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText());
            //dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekKetMati();
            dlgki.UserValid();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            if (kdpoli.getText().equals("-")) {
                if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi..!!");
                } else {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setstatus(true);
                    DlgKamarInap dlgki = new DlgKamarInap(null, false);
                    dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                    dlgki.setLocationRelativeTo(internalFrame1);
                    dlgki.emptTeks();
                    dlgki.isCek();
                    dlgki.setNoRm(TNoRw.getText());
                    //dlgki.tampil();
                    dlgki.setVisible(true);
                    dlgki.cekKetMati();
                    dlgki.UserValid();
                    this.setCursor(Cursor.getDefaultCursor());
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Kunjungan rawat jalan poliklinik, tdk. bisa langsung rawat inap. Sepakati dulu alurnya..!!");
                tbregistrasiRalan.requestFocus();
            }
        }
    }
}//GEN-LAST:event_MnKamarInapActionPerformed

private void MnRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbregistrasiRalan.requestFocus();
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
        dlgrjk.btnFaskes.requestFocus();
        this.setCursor(Cursor.getDefaultCursor());
    }
}//GEN-LAST:event_MnRujukActionPerformed

private void MnBillingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBillingActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        tbregistrasiRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            try {
                pscaripiutang = koneksi.prepareStatement("select tgl_piutang from piutang_pasien where no_rkm_medis=? and status='Belum Lunas' order by tgl_piutang asc limit 1");
                try {
                    pscaripiutang.setString(1, TNoRM.getText());
                    rs = pscaripiutang.executeQuery();
                    if (rs.next()) {
                        i = JOptionPane.showConfirmDialog(null, "Masih ada tunggakan pembayaran, apa mau bayar sekarang ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (i == JOptionPane.YES_OPTION) {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgLhtPiutang piutang = new DlgLhtPiutang(null, false);
                            piutang.setNoRm(TNoRM.getText(), rs.getDate(1));
                            piutang.tampil();
                            piutang.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            piutang.setLocationRelativeTo(internalFrame1);
                            piutang.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        } else {
                            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            DlgBilingRalan dlgbil = new DlgBilingRalan(null, false);
                            dlgbil.TNoRw.setText(TNoRw.getText());
                            dlgbil.isCek();
                            dlgbil.isRawat();
                            dlgbil.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                            dlgbil.setLocationRelativeTo(internalFrame1);
                            dlgbil.setVisible(true);
                            this.setCursor(Cursor.getDefaultCursor());
                        }
                    } else {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        DlgBilingRalan dlgbil = new DlgBilingRalan(null, false);
                        dlgbil.TNoRw.setText(TNoRw.getText());
                        dlgbil.isCek();
                        dlgbil.isRawat();
                        dlgbil.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                        dlgbil.setLocationRelativeTo(internalFrame1);
                        dlgbil.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    }
                } catch (Exception e) {
                    System.out.println(e);
                } finally {
                    if (rs != null) {
                        rs.close();
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

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void ppGrafikPerpoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoliActionPerformed
    tampilAwal();
    grafikperiksaperpoli kas = new grafikperiksaperpoli("Grafik Periksa Per Unit/Poli Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerpoliActionPerformed

private void ppGrafikPerdokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokterActionPerformed
    tampilAwal();
    grafikperiksaperdokter kas = new grafikperiksaperdokter("Grafik Periksa Per Dokter Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerdokterActionPerformed

private void ppGrafikPerJKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerJKActionPerformed
    tampilAwal();
    grafikperiksaperjk kas = new grafikperiksaperjk("Grafik Periksa Per Jenis Kelamin " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerJKActionPerformed

private void ppGrafikPerPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerPekerjaanActionPerformed
    tampilAwal();
    grafikperiksaperpekerjaan kas = new grafikperiksaperpekerjaan("Grafik Periksa Per Pekerjaan " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerPekerjaanActionPerformed

private void ppGrafikPerAgamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerAgamaActionPerformed
    tampilAwal();
    grafikperiksaperagama kas = new grafikperiksaperagama("Grafik Periksa Per Agama " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerAgamaActionPerformed

private void ppGrafikPerTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTahunActionPerformed
    tampilAwal();
    grafikperiksapertahun kas = new grafikperiksapertahun("Grafik Periksa Per Tahun " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTahunActionPerformed

private void ppGrafikPerBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerBulanActionPerformed
    tampilAwal();
    grafikperiksaperbulan kas = new grafikperiksaperbulan("Grafik Periksa Per Bulan " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerBulanActionPerformed

private void ppGrafikPerTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerTanggalActionPerformed
    tampilAwal();
    grafikperiksaperhari kas = new grafikperiksaperhari("Grafik Periksa Per Hari " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ");
    kas.setSize(this.getWidth(), this.getHeight());
    kas.setLocationRelativeTo(this);
    kas.setVisible(true);
}//GEN-LAST:event_ppGrafikPerTanggalActionPerformed

private void MnPeriksaLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaLabActionPerformed
    diagnosa_ok = "";
    diagnosa_cek = 0;
    diagnosa_cek = Sequel.cariInteger("select count(1) cek from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");

    if (diagnosa_cek == 0) {
        diagnosa_ok = "-";
    } else {
        diagnosa_ok = Sequel.cariIsi("select diagnosa from pemeriksaan_ralan where no_rawat='" + TNoRw.getText() + "'");
    }

    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
        tbregistrasiRalan.requestFocus();
    } else {
        if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPeriksaLaboratorium dlgro = new DlgPeriksaLaboratorium(null, false);
            dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgro.setLocationRelativeTo(internalFrame1);
            dlgro.emptTeks();
            dlgro.isCek();
            dlgro.KodePerujuk.setText(kddokter.getText());
            dlgro.setNoRm(TNoRw.getText(), "Ralan", diagnosa_ok, "-", TPoli.getText());
            dlgro.tampiltarif();
            dlgro.tampil();
            dlgro.setVisible(true);
            dlgro.fokus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }
}//GEN-LAST:event_MnPeriksaLabActionPerformed

private void MnCetakSuratSakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakitActionPerformed
    pilihan = 1;
    DlgSakit.setSize(550, 121);
    DlgSakit.setLocationRelativeTo(internalFrame1);
    DlgSakit.setVisible(true);
}//GEN-LAST:event_MnCetakSuratSakitActionPerformed

private void BtnPrint2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint2ActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
    } else {
        if (pilihan == 1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("hari", lmsakit.getText());
            param.put("TanggalAwal", TglSakit1.getSelectedItem().toString());
            param.put("TanggalAkhir", TglSakit2.getSelectedItem().toString());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSakit.jasper", "report", "::[ Surat Sakit ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat"
                    + " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten"
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        } else if (pilihan == 2) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("hari", lmsakit.getText());
            param.put("TanggalAwal", TglSakit1.getSelectedItem().toString());
            param.put("TanggalAkhir", TglSakit2.getSelectedItem().toString());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSakit3.jasper", "report", "::[ Surat Sakit ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat"
                    + " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten"
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

        } else if (pilihan == 3) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("hari", lmsakit.getText());
            param.put("TanggalAwal", TglSakit1.getSelectedItem().toString());
            param.put("TanggalAkhir", TglSakit2.getSelectedItem().toString());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("penyakit", Sequel.cariIsi("select concat(diagnosa_pasien.kd_penyakit,' ',penyakit.nm_penyakit) from diagnosa_pasien inner join reg_periksa inner join penyakit "
                    + "on diagnosa_pasien.no_rawat=reg_periksa.no_rawat and diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                    + "where diagnosa_pasien.no_rawat=? and diagnosa_pasien.prioritas='1'", TNoRw.getText()));
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSakit4.jasper", "report", "::[ Surat Sakit ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.keluarga,pasien.namakeluarga,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat"
                    + " from reg_periksa inner join pasien inner join dokter inner join kelurahan inner join kecamatan inner join kabupaten"
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());

        }

    }
}//GEN-LAST:event_BtnPrint2ActionPerformed

private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
    DlgSakit.dispose();
}//GEN-LAST:event_BtnKeluar2ActionPerformed

private void BtnPrint3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint3ActionPerformed

    if (!Kelurahan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + " and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and kelurahan.nm_kel='" + Kelurahan2.getText() + "'  and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'   ",
                "pasien.alamat", "Area");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kecamatan2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel "
                + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'  ",
                "kelurahan.nm_kel", "Kelurahan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (!Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                " reg_periksa inner join pasien inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab "
                + "and pasien.kd_kec=kecamatan.kd_kec and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where kabupaten.nm_kab='" + Kabupaten2.getText() + "'  and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "'  ",
                "kecamatan.nm_kec", "Kecamatan");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    } else if (Kabupaten2.getText().equals("")) {
        DlgDemografi.dispose();
        grafiksql kas = new grafiksql("::[ Data Demografi Per Kabupaten ]::",
                " reg_periksa inner join pasien inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' ",
                "kabupaten.nm_kab", "Kabupaten");
        kas.setSize(this.getWidth(), this.getHeight());
        kas.setLocationRelativeTo(this);
        kas.setVisible(true);
    }
}//GEN-LAST:event_BtnPrint3ActionPerformed

private void BtnKeluar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar3ActionPerformed
    DlgDemografi.dispose();
}//GEN-LAST:event_BtnKeluar3ActionPerformed

private void btnKelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelActionPerformed
    akses.setform("DlgReg");
    pasien.kel.emptTeks();
    pasien.kel.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kel.setLocationRelativeTo(internalFrame1);
    pasien.kel.setVisible(true);

}//GEN-LAST:event_btnKelActionPerformed

private void btnKecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKecActionPerformed
    akses.setform("DlgReg");
    pasien.kec.emptTeks();
    pasien.kec.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kec.setLocationRelativeTo(internalFrame1);
    pasien.kec.setVisible(true);
}//GEN-LAST:event_btnKecActionPerformed

private void btnKabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKabActionPerformed
    akses.setform("DlgReg");
    pasien.kab.emptTeks();
    pasien.kab.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.kab.setLocationRelativeTo(internalFrame1);
    pasien.kab.setVisible(true);
}//GEN-LAST:event_btnKabActionPerformed

private void BtnPrint4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrint4ActionPerformed
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
        BtnBatal.requestFocus();
    } else if (tabMode.getRowCount() != 0) {
        if (!Kelurahan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Area");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Area Kelurahan " + Kelurahan2.getText() + ", Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select  pasien.alamat as area,count(pasien.alamat) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' "
                    + "and kelurahan.nm_kel='" + Kelurahan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by pasien.alamat order by pasien.alamat", data);
        } else if (!Kecamatan2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + ", Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kelurahan");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Kelurahan Kecamatan " + Kecamatan2.getText() + " Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kelurahan.nm_kel as area,count(kelurahan.nm_kel) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan inner join kelurahan on pasien.kd_kab=kabupaten.kd_kab "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kel=kelurahan.kd_kel and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and kecamatan.nm_kec='" + Kecamatan2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kelurahan.nm_kel order by kelurahan.nm_kel", data);
        } else if (!Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kecamatan Kabupaten " + Kabupaten2.getText());
            data.put("area", "Kecamatan");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Per Kecamatan Kabupaten " + Kabupaten2.getText() + " ]::",
                    "select kecamatan.nm_kec as area,count(kecamatan.nm_kec) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten inner join kecamatan on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and pasien.kd_kec=kecamatan.kd_kec where kabupaten.nm_kab='" + Kabupaten2.getText() + "' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kecamatan.nm_kec order by kecamatan.nm_kec", data);
        } else if (Kabupaten2.getText().equals("")) {
            DlgDemografi.dispose();
            Map<String, Object> data = new HashMap<>();
            data.put("judul", "Data Demografi Per Kabupaten");
            data.put("area", "Kabupaten");
            data.put("namars", akses.getnamars());
            data.put("alamatrs", akses.getalamatrs());
            data.put("kotars", akses.getkabupatenrs());
            data.put("propinsirs", akses.getpropinsirs());
            data.put("kontakrs", akses.getkontakrs());
            data.put("emailrs", akses.getemailrs());
            data.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDemografi.jasper", "report", "::[ Data Demografi Per Kabupaten ]::", "select kabupaten.nm_kab as area,count(kabupaten.nm_kab) as jumlah from reg_periksa inner join pasien "
                    + "inner join kabupaten on pasien.kd_kab=kabupaten.kd_kab and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by kabupaten.nm_kab order by kabupaten.nm_kab", data);
        }
    }
    this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrint4ActionPerformed

private void ppGrafikDemografiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikDemografiActionPerformed
    DlgDemografi.setSize(550, 180);
    DlgDemografi.setLocationRelativeTo(internalFrame1);
    DlgDemografi.setVisible(true);
}//GEN-LAST:event_ppGrafikDemografiActionPerformed

private void MnRujukMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukMasukActionPerformed
    if (TPasien.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        tbregistrasiRalan.requestFocus();
    } else {
        rujukmasuk.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
        rujukmasuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.emptTeks();
        rujukmasuk.isCek();
        rujukmasuk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
        rujukmasuk.tampil();
        rujukmasuk.setVisible(true);
        //this.dispose();
    }
}//GEN-LAST:event_MnRujukMasukActionPerformed

private void kdpnjKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpnjKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select png_jawab from penjab where kd_pj=?", nmpnj, kdpnj.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        TAlmt.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        AsalRujukan.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnPenjabActionPerformed(null);
    }
}//GEN-LAST:event_kdpnjKeyPressed

private void btnPenjabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjabActionPerformed
    akses.setform("DlgReg");
    pasien.penjab.onCari();
    pasien.penjab.isCek();
    pasien.penjab.setSize(868, 519);
    pasien.penjab.setLocationRelativeTo(internalFrame1);
    pasien.penjab.setVisible(true);
    pasien.penjab.onCari();
}//GEN-LAST:event_btnPenjabActionPerformed

private void MnLaporanRekapKunjunganPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganPoliActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganPoli.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganPoliActionPerformed

private void MnLaporanRekapKunjunganDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganDokterActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganDokter.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganDokterActionPerformed

private void MnLaporanRekapJenisBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapJenisBayarActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganJenisBayar.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapJenisBayarActionPerformed

private void MnLaporanRekapRawatDaruratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapRawatDaruratActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanRawatDarurat.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapRawatDaruratActionPerformed

private void MnLaporanRekapKunjunganBulananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganBulanan.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananActionPerformed

private void MnLaporanRekapKunjunganBulananPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed
    if (tabMode.getRowCount() == 0) {
        JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
        TNoReg.requestFocus();
    } else {
        Valid.panggilUrl("billing/LaporanKunjunganBulananPoli.php?tanggal1=" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "&tanggal2=" + Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
    }
}//GEN-LAST:event_MnLaporanRekapKunjunganBulananPoliActionPerformed

    private void MnLaporanRekapPenyakitRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapPenyakitRalanActionPerformed
        if (akses.getkode().equals("Admin Utama") || akses.getpenyakit() == true) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgFrekuensiPenyakitRalan ktginventaris = new DlgFrekuensiPenyakitRalan(null, false);
            ktginventaris.isCek();
            ktginventaris.setSize(this.getWidth() - 40, this.getHeight() - 40);
            ktginventaris.setLocationRelativeTo(this);
            ktginventaris.setVisible(true);
            ktginventaris.UserValid();
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(null, "Untuk melihat frekuensi rekap penyakit pasien rawat jalan, silakan koordinasi dg. Inst. Rekam Medik...!!!");
            BtnCari.requestFocus();
        }
    }//GEN-LAST:event_MnLaporanRekapPenyakitRalanActionPerformed

    private void MnCheckListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckListActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList.jasper", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckListActionPerformed

    private void Kabupaten2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kabupaten2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKabActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar3, Kecamatan2);
        }
    }//GEN-LAST:event_Kabupaten2KeyPressed

    private void Kecamatan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kecamatan2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKecActionPerformed(null);
        } else {
            Valid.pindah(evt, Kabupaten2, Kelurahan2);
        }
    }//GEN-LAST:event_Kecamatan2KeyPressed

    private void Kelurahan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kelurahan2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnKelActionPerformed(null);
        } else {
            Valid.pindah(evt, Kecamatan2, BtnPrint4);
        }
    }//GEN-LAST:event_Kelurahan2KeyPressed

    private void BtnPrint4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint4KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrint4ActionPerformed(null);
        } else {
            Valid.pindah(evt, Kelurahan2, BtnPrint3);
        }
    }//GEN-LAST:event_BtnPrint4KeyPressed

    private void BtnPrint3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrint3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrint3ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint4, BtnKeluar3);
        }
    }//GEN-LAST:event_BtnPrint3KeyPressed

    private void BtnKeluar3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluar3ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnPrint3, Kabupaten2);
        }
    }//GEN-LAST:event_BtnKeluar3KeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilAwal();
        tRefreshAntri.start();
    }//GEN-LAST:event_formWindowOpened

    private void MnPeriksaRadiologiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPeriksaRadiologiActionPerformed
        if (tbregistrasiRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPeriksaRadiologi dlgro = new DlgPeriksaRadiologi(null, false);
                dlgro.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                dlgro.setLocationRelativeTo(internalFrame1);
                dlgro.emptTeks();
                dlgro.isCek();
                dlgro.setNoRm(TNoRw.getText(), "Ralan");
                dlgro.tampil();
                dlgro.setVisible(true);
                dlgro.fokus_kursor();
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_MnPeriksaRadiologiActionPerformed

    private void MnBarcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("nama", TPasien.getText());
            param.put("alamat", Sequel.cariIsi("select date_format(tgl_lahir,'%d/%m/%Y') from pasien where no_rkm_medis=?", TNoID.getText()));
            param.put("norm", TNoID.getText());
            param.put("parameter", "%" + TCari.getText().trim() + "%");
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            Valid.MyReport("rptBarcodeRawat.jasper", "report", "::[ Barcode No.Rawat ]::",
                    "select reg_periksa.no_rawat from reg_periksa where no_rawat='" + TNoRw.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeActionPerformed

    private void MnCheckList1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList1ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList2.jasper", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList1ActionPerformed

    private void ppGrafikPerpoli1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoli1ActionPerformed
        tampilAwal();
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {
            rs = koneksi.prepareStatement("select poliklinik.nm_poli,count(poliklinik.nm_poli) as jumlah "
                    + "from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by poliklinik.nm_poli").executeQuery();
            while (rs.next()) {
                dpd.setValue(rs.getString(1) + "(" + rs.getString(2) + ")", rs.getDouble(2));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        JFreeChart freeChart = ChartFactory.createPieChart("Grafik Periksa Per Unit/Poli Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), dpd, true, true, false); //String title,PieDatasheet datasheet,boolean legends,boolean tooltips,boolean url 
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Unit/Poli", freeChart);
        cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_ppGrafikPerpoli1ActionPerformed

    private void ppGrafikPerpoli2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerpoli2ActionPerformed
        tampilAwal();
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {
            rs = koneksi.prepareStatement("select poliklinik.nm_poli,count(poliklinik.nm_poli) as jumlah "
                    + "from reg_periksa inner join poliklinik on reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by poliklinik.nm_poli").executeQuery();
            while (rs.next()) {
                dcd.setValue(rs.getDouble(2), rs.getString(1) + "(" + rs.getString(2) + ")", rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        JFreeChart freeChart = ChartFactory.createBarChart("Grafik Periksa Per Unit/Poli Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Poliklinik/Unit", "Jumlah Pasien", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Unit/Poli", freeChart);
        cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_ppGrafikPerpoli2ActionPerformed

    private void ppGrafikPerdokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokter1ActionPerformed
        tampilAwal();
        DefaultPieDataset dpd = new DefaultPieDataset();
        try {
            rs = koneksi.prepareStatement("select dokter.nm_dokter,count(dokter.nm_dokter) as jumlah "
                    + "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                    + "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by dokter.nm_dokter").executeQuery();
            while (rs.next()) {
                dpd.setValue(rs.getString(1) + "(" + rs.getString(2) + ")", rs.getDouble(2));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        JFreeChart freeChart = ChartFactory.createPieChart("Grafik Periksa Per Dokter Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), dpd, true, true, false);
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Dokter", freeChart);
        cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_ppGrafikPerdokter1ActionPerformed

    private void ppGrafikPerdokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppGrafikPerdokter2ActionPerformed
        tampilAwal();
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        try {
            rs = koneksi.prepareStatement("select dokter.nm_dokter,count(dokter.nm_dokter) as jumlah "
                    + "from reg_periksa inner join dokter on reg_periksa.kd_dokter=dokter.kd_dokter "
                    + "where tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' group by dokter.nm_dokter").executeQuery();
            while (rs.next()) {
                dcd.setValue(rs.getDouble(2), rs.getString(1) + "(" + rs.getString(2) + ")", rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        JFreeChart freeChart = ChartFactory.createBarChart("Grafik Periksa Per Dokter Tanggal " + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + " S.D. " + Valid.SetTgl(DTPCari2.getSelectedItem() + ""), "Dokter", "Jumlah Pasien", dcd, PlotOrientation.VERTICAL, true, true, true);
        ChartFrame cf = new ChartFrame("Grafik Periksa Per Dokter", freeChart);
        cf.setSize(internalFrame1.getWidth() - 50, internalFrame1.getHeight() - 50);
        cf.setLocationRelativeTo(internalFrame1);
        cf.setAlwaysOnTop(false);
        cf.setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
        cf.setVisible(true);
    }//GEN-LAST:event_ppGrafikPerdokter2ActionPerformed

    private void MnHemodialisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHemodialisaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgHemodialisa resume = new DlgHemodialisa(null, false);
            resume.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
            resume.isCek();
            resume.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnHemodialisaActionPerformed

    private void MnLabelTrackerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTrackerActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("no_rawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker.jasper", param, "::[ Label Tracker ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTrackerActionPerformed

    private void MnCheckList2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList2ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("bayar", nmpnj.getText());
            param.put("dokter", TDokter.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("alamat", TAlmt.getText());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList4.jasper", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList2ActionPerformed

    private void MnCheckList3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList3ActionPerformed
        if (!TPasien.getText().equals("")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("bayar", nmpnj.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("alamat", TAlmt.getText());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptCheckList3.jasper", "report", "::[ Check List ]::",
                    "select current_date() as sekarang", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCheckList3ActionPerformed

    private void btnPenjab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPenjab1ActionPerformed
        akses.setform("DlgReg");
        rujukmasuk.tampil2();
        rujukmasuk.WindowPerujuk.setSize(900, 573);
        rujukmasuk.WindowPerujuk.setLocationRelativeTo(internalFrame1);
        rujukmasuk.WindowPerujuk.setVisible(true);
        rujukmasuk.onCariPerujuk();
    }//GEN-LAST:event_btnPenjab1ActionPerformed

    private void AsalRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsalRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            kdpnj.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnPenjabActionPerformed(null);
        }
    }//GEN-LAST:event_AsalRujukanKeyPressed

    private void MnLaporanRekapPerujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanRekapPerujukActionPerformed

        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptRegPerPerujuk.jasper", "report", "::[ Data Registrasi Per Perujuk ]::",
                    "select reg_periksa.tgl_registrasi,rujuk_masuk.perujuk,count(reg_periksa.no_rawat) as jumlah,"
                    + "sum(reg_periksa.biaya_reg) as jasarujuk "
                    + "from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat "
                    + "where reg_periksa.tgl_registrasi between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "group by rujuk_masuk.perujuk order by count(reg_periksa.no_rawat) desc ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }

    }//GEN-LAST:event_MnLaporanRekapPerujukActionPerformed

    private void MnCheckList4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa.jasper", "report", "::[ Lembar Periksa ]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList4ActionPerformed

    private void MnCheckList5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa2.jasper", "report", "::[ Lembar Periksa ]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList5ActionPerformed

    private void MnLabelTracker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("no_rawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLabelTracker2.jasper", param, "::[ Label Tracker ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker1ActionPerformed

    private void MnCetakRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegisterActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptBuktiRegister.jasper", "report", "::[ Bukti Register ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,pasien.no_tlp,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,pasien.umur as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegisterActionPerformed

    private void MnPersetujuanMedisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPersetujuanMedisActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("persetujuantindakanmedis.jasper", "report", "::[ Persetujuan Tindakan ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPersetujuanMedisActionPerformed

    private void MnGelang1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM6.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang1ActionPerformed

    private void MnGelang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM7.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang2ActionPerformed

    private void MnCheckList6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", TNoRM.getText()));
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa3.jasper", "report", "::[ Lembar Periksa]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList6ActionPerformed

    private void MnCheckList7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCheckList7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TPasien.getText().equals("")) {
            Map<String, Object> param = new HashMap<>();
            param.put("poli", TPoli.getText());
            param.put("antrian", TNoReg.getText());
            param.put("nama", TPasien.getText());
            param.put("norm", TNoRM.getText());
            param.put("lahir", Sequel.cariIsi("select DATE_FORMAT(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ", TNoRM.getText()));
            param.put("dokter", TDokter.getText());
            param.put("no_rawat", TNoRw.getText());
            param.put("bayar", nmpnj.getText());
            param.put("penjab", TPngJwb.getText());
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptLembarPeriksa4.jasper", "report", "::[ Lembar Periksa]::",
                    "select date_format(current_date(),'%d/%m/%Y') as sekarang", param);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_MnCheckList7ActionPerformed

    private void MnGelang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang3ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM8.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang3ActionPerformed

    private void MnGelang4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang4ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM10.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang4ActionPerformed

    private void MnBuktiPelayananRalanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBuktiPelayananRalanActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptBuktiPelayananRalan.jasper", "report", "::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,"
                    + "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBuktiPelayananRalanActionPerformed

    private void MnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSEPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgReg");
            BPJSDataSEP dlgki = new BPJSDataSEP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(), "2. Ralan", kdpoli.getText(), TPoli.getText());
            dlgki.tampil();
            dlgki.setVisible(true);
            dlgki.cekLAYAN();
//            dlgki.tampilNoRujukan(NoPeserta.getText());
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSEPActionPerformed

    private void MnLabelTracker2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker2ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptLabelTracker3.jasper", "report", "::[ Label Tracker ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker2ActionPerformed

    private void MnLabelTracker3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelTracker3ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptLabelTracker4.jasper", "report", "::[ Label Tracker ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelTracker3ActionPerformed

    private void MnGelang5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang5ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM14.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang5ActionPerformed

    private void MnCetakSuratSakit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit2ActionPerformed
        pilihan = 2;
        DlgSakit.setSize(550, 121);
        DlgSakit.setLocationRelativeTo(internalFrame1);
        DlgSakit.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit2ActionPerformed

    private void ppRiwayatBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRiwayatBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgResumePerawatan resume = new DlgResumePerawatan(null, true);
            resume.setNoRm(TNoRM.getText(), TPasien.getText());
            resume.tampil();
            resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resume.setLocationRelativeTo(internalFrame1);
            resume.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppRiwayatBtnPrintActionPerformed

    private void MnCetakSuratSehatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehatActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptSuratSehat.jasper", "report", "::[ Surat Keterangan Sehat ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat "
                    + " from reg_periksa inner join pasien inner join dokter "
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "
                    + "where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehatActionPerformed

    private void MnCetakBebasNarkobaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakBebasNarkobaActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptBebasNarkoba.jasper", "report", "::[ Surat Keterangan Bebas Narkoba ]::",
                    "select reg_periksa.no_rawat,dokter.nm_dokter,pasien.tgl_lahir,pasien.jk,"
                    + " pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,pasien.pekerjaan,pasien.alamat "
                    + " from reg_periksa inner join pasien inner join dokter "
                    + " on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_dokter=dokter.kd_dokter  "
                    + "where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakBebasNarkobaActionPerformed

    private void MnSJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSJPActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else if (!kdpnj.getText().equals("A01")) {
            JOptionPane.showMessageDialog(null, "Pasien itu bukan pasien Inhealth...!!!");
            tbregistrasiRalan.requestFocus();
        } else if (kdpnj.getText().equals("A01")) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgReg");
            InhealthDataSJP dlgki = new InhealthDataSJP(null, false);
            dlgki.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            dlgki.setLocationRelativeTo(internalFrame1);
            dlgki.isCek();
            dlgki.setNoRm(TNoRw.getText(), tglDaftar, "3 RJTL RAWAT JALAN TINGKAT LANJUT", kdpoli.getText(), TPoli.getText());
            dlgki.tampil();
            dlgki.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSJPActionPerformed

    private void MnGelang6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGelang6ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("tanggal", tulisan_tanggal.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBarcodeRM16.jasper", "report", "::[ Gelang Pasien ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, pasien.tgl_lahir,pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoID.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGelang6ActionPerformed

    private void MnLembarCasemixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLembarCasemixActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptLembarCasemix.jasper", "report", "::[ Surat Jaminan & Bukti Pelayanan Rawat Jalan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,"
                    + "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLembarCasemixActionPerformed

    private void ppCatatanPasienBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCatatanPasienBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRw.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgCatatan catatan = new DlgCatatan(null, true);
            catatan.setNoRm(TNoRM.getText());
            catatan.setSize(720, 330);
            catatan.setLocationRelativeTo(internalFrame1);
            catatan.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCatatanPasienBtnPrintActionPerformed

    private void MnSPBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSPBKActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
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
            Valid.MyReport("rptSBPK.jasper", "report", "::[ Surat Bukti Pelayanan Kesehatan ]::",
                    "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,"
                    + "reg_periksa.jam_reg, reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,"
                    + "reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_peserta,pasien.tgl_lahir "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab "
                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "where reg_periksa.no_rawat='" + TNoRw.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnSPBKActionPerformed

    private void ppBerkasBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBerkasBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, table masih kosong...!!!!");
            //TNoReg.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Sequel.menyimpan("mutasi_berkas", "'" + TNoRw.getText() + "','Sudah Diterima',now(),now(),'0000-00-00 00:00:00','0000-00-00 00:00:00'", "status='Sudah Diterima',diterima=now()", "no_rawat='" + TNoRw.getText() + "'");
            Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Berkas Diterima'");
            if (tabMode.getRowCount() != 0) {
                tampilAwal();
            }
        }
    }//GEN-LAST:event_ppBerkasBtnPrintActionPerformed

    private void MnSudahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSudahActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Sudah Diperiksa Dokter'");
                if (tabMode.getRowCount() != 0) {
                    tampilAwal();
                }
            }

        }
    }//GEN-LAST:event_MnSudahActionPerformed

    private void MnBelumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBelumActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Belum'");
                if (tabMode.getRowCount() != 0) {
                    tampilAwal();
                }
            }

        }
    }//GEN-LAST:event_MnBelumActionPerformed

    private void MnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBatalActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (Sequel.cariInteger("select count(no_rawat) from kamar_inap where no_rawat=?", TNoRw.getText()) > 0) {
                JOptionPane.showMessageDialog(null, "Maaf, Pasien sudah masuk Kamar Inap. Gunakan billing Ranap..!!!");
            } else {
                Valid.editTable(tabMode, "reg_periksa", "no_rawat", TNoRw, "stts='Batal'");
                if (tabMode.getRowCount() != 0) {
                    tampilAwal();
                }
            }
        }
    }//GEN-LAST:event_MnBatalActionPerformed

    private void MnCetakSuratSakit3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSakit3ActionPerformed
        pilihan = 3;
        DlgSakit.setSize(550, 121);
        DlgSakit.setLocationRelativeTo(internalFrame1);
        DlgSakit.setVisible(true);
    }//GEN-LAST:event_MnCetakSuratSakit3ActionPerformed

    private void MnJKRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJKRAActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("perujuk", Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?", TNoRw.getText()));
            param.put("no_rawat", TNoRw.getText());
            param.put("petugas", Sequel.cariIsi("select nama from petugas where nip=?", akses.getkode()));
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptJkra.jasper", param, "::[ SURAT JAMINAN KESEHATAN NASIONAL (JKRA) RAWAT JALAN ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnJKRAActionPerformed

    private void MnCetakRegister1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakRegister1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("norawat", TNoRw.getText());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptBuktiRegister3.jasper", param, "::[ Bukti Register ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakRegister1ActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampilAwal();
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (akses.getJenisLoket().equals("") && akses.getNomorLoket().equals("")) {
                statusOperator.setText("Anda belum menentukan jenis & no. loketnya.");
                cekLoket();
            } else if (!akses.getJenisLoket().equals("") && akses.getNomorLoket().equals("")) {
                cekLoket();
                statusOperator.setText("Anda sebagai loket " + akses.getJenisLoket() + " dan nomor loketnya belum dipilih.");
            } else if (akses.getJenisLoket().equals("") && !akses.getNomorLoket().equals("")) {
                cekLoket();
                statusOperator.setText("Anda belum memilih jenis loketnya dan memilih nomor loket " + akses.getNomorLoket());
            } else {
                cekLoket();
                statusOperator.setText("Anda sebagai loket " + akses.getJenisLoket() + " utk. melayani pasien diloket " + akses.getNomorLoket());
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            emptTeksRestor();
            tampilHistoryReset();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void MnCetakSuratSehat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakSuratSehat1ActionPerformed
        if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("norawat", TNoRw.getText());
            param.put("bb", Sequel.cariIsi("select berat from pemeriksaan_ralan where no_rawat=?", TNoRw.getText()));
            param.put("td", Sequel.cariIsi("select tensi from pemeriksaan_ralan where no_rawat=?", TNoRw.getText()));
            param.put("tb", Sequel.cariIsi("select tinggi from pemeriksaan_ralan where no_rawat=?", TNoRw.getText()));
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptSuratSehat2.jasper", param, "::[ Surat Keterangan Sehat ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakSuratSehat1ActionPerformed

    private void MnManualSEPBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnManualSEPBPJSActionPerformed
        if (tbregistrasiRalan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        } else if (tbregistrasiRalan.getRowCount() != 0) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select bpjs from gambar"));
            Valid.MyReport("rptManualSEPJalan.jasper", "report", "::[ Cetak Manual SEP Pasien BPJS Rawat Jalan ]::",
                    " SELECT DATE_FORMAT(reg_periksa.tgl_registrasi,'%Y-%m-%d') tgl_sep, CONCAT(pasien.no_peserta,' (MR. ',reg_periksa.no_rkm_medis,')') no_kartu, "
                    + "pasien.nm_pasien, CONCAT(DATE_FORMAT(pasien.tgl_lahir,'%Y-%m-%d'),' Kelamin : ',IF(pasien.jk='L','Laki-laki','Perempuan')) tgl_lahir, "
                    + "IFNULL(pasien.no_tlp,'-') no_telpon, poliklinik.nm_poli, penjab.png_jawab, IF(reg_periksa.status_lanjut='Ralan','R. Jalan','R. Inap') jns_rawat, d.nm_dokter "
                    + "FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + "INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + "INNER JOIN dokter d ON d.kd_dokter=reg_periksa.kd_dokter WHERE reg_periksa.status_lanjut='Ralan' AND penjab.png_jawab LIKE '%bpjs%' "
                    + "AND reg_periksa.no_rawat='" + TNoRw.getText() + "'", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnManualSEPBPJSActionPerformed

    private void MnJAMKESDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJAMKESDAActionPerformed
        if (nmpnj.getText().equals("")) {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbregistrasiRalan.requestFocus();
        } else if (kdpnj.getText().equals("D01")) {
            DlgJamkesda.setSize(490, 125);
            DlgJamkesda.setLocationRelativeTo(internalFrame1);
            DlgJamkesda.setVisible(true);
        } else {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbregistrasiRalan.requestFocus();
        }
    }//GEN-LAST:event_MnJAMKESDAActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        DlgJamkesda.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void BtnCloseIn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn1KeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        cekjamkesda = 0;
        cekjamkesda = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jamkesda bj INNER JOIN reg_periksa rp ON bj.no_rawat = rp.no_rawat "
                + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE bj.jns_rawat='Jalan' AND pj.png_jawab LIKE '%jamkesda%' and bj.no_rawat='" + TNoRw.getText() + "'");

        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else if (cekjamkesda >= 1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jamkesda", "'" + TNoRw.getText() + "','" + noSrt.getText() + "','Jalan',"
                    + "'" + tglDaftar + "',"
                    + "'" + Valid.SetTgl(TglSurat.getSelectedItem() + "") + "',"
                    + "'" + TNoRw.getText() + "RJ' ", "Data SEP Jamkesda");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void noSrtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrtKeyPressed

    }//GEN-LAST:event_noSrtKeyPressed

    private void TglSuratItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSuratItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratItemStateChanged

    private void TglSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSuratKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSuratKeyPressed

    private void BtnGantijkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijkdActionPerformed
        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta Jamkesda");
        } else {
            Sequel.mengedit("bridging_jamkesda", "no_sep='" + sepJkd.getText() + "'", "no_surat='" + noSrt.getText() + "', tgl_surat='" + Valid.SetTgl(TglSurat.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJkd.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijkdActionPerformed

    private void BtnGantijkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijkdKeyPressed

    private void BtnCtkJkdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJkdActionPerformed
        sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan' "));

        if (noSrt.getText().trim().equals("")) {
            Valid.textKosong(noSrt, "No. Surat Keterangan Peserta " + nmpnj.getText());
        } else if (sepJkd.getText().equals("")) {
            Valid.textKosong(sepJkd, "No. SEP " + nmpnj.getText());
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
            param.put("nmPenjab", nmpnj.getText());
            Valid.MyReport("rptSEPJalan.jasper", "report", "::[ Cetak SEP Pasien " + nmpnj.getText() + " Rawat Jalan ]::",
                    " SELECT pasien.no_rkm_medis, bridging_jamkesda.no_sep, reg_periksa.tgl_registrasi, pasien.no_ktp, "
                    + " pasien.nm_pasien, pasien.tgl_lahir, IF(pasien.jk='L','Laki-laki','Perempuan') as jk, "
                    + " poliklinik.nm_poli, reg_periksa.no_rawat, bridging_jamkesda.no_surat, bridging_jamkesda.jns_rawat, "
                    + " bridging_jamkesda.tgl_surat, bridging_jamkesda.tgl_rawat, penjab.png_jawab "
                    + " FROM reg_periksa INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis "
                    + " INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli "
                    + " INNER JOIN bridging_jamkesda ON bridging_jamkesda.no_rawat = reg_periksa.no_rawat "
                    + " INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj "
                    + " WHERE bridging_jamkesda.no_sep='" + sepJkd.getText() + "' AND bridging_jamkesda.jns_rawat='Jalan' AND reg_periksa.kd_pj='D01'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJamkesda.dispose();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCtkJkdActionPerformed

    private void BtnCtkJkdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJkdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJkdKeyPressed

    private void JnsnoIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JnsnoIDMouseClicked
        JnsnoID.setEditable(false);
    }//GEN-LAST:event_JnsnoIDMouseClicked

    private void JnsnoIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JnsnoIDKeyPressed

    }//GEN-LAST:event_JnsnoIDKeyPressed

    private void JnsnoIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JnsnoIDItemStateChanged
        isPas();
        JnsnoID.requestFocus();
    }//GEN-LAST:event_JnsnoIDItemStateChanged

    private void MnRujukSisruteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukSisruteActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            if (akses.getkode().equals("Admin Utama") || (akses.getsisrute_rujukan_keluar())) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgReg");
                SisruteRujukanKeluar dlgki = new SisruteRujukanKeluar(null, false);
                dlgki.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
                dlgki.setLocationRelativeTo(internalFrame1);
                dlgki.isCek();
                dlgki.setPasien(TNoRw.getText());
                dlgki.JenisRujukan.setSelectedItem(0);
                dlgki.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Hanya petugas yang memiliki hak akses saja yang bisa menggunakan fitur ini...!!!");
            }
        }
    }//GEN-LAST:event_MnRujukSisruteActionPerformed

    private void cekPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cekPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cekPasienKeyPressed

    private void BtnSukuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSukuActionPerformed
        akses.setform("DlgReg");
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
        akses.setform("DlgReg");
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

    private void label_pesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_pesanMouseClicked

    }//GEN-LAST:event_label_pesanMouseClicked

    private void label_pesanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_label_pesanKeyPressed
        //        Valid.pindah(evt,COB,LokasiLaka);
    }//GEN-LAST:event_label_pesanKeyPressed

    private void MnGChalaman1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGChalaman1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnGChalaman1ActionPerformed

    private void MnGChalaman2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGChalaman2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MnGChalaman2ActionPerformed

    private void ScrollMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ScrollMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollMouseClicked

    private void MnKartuUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoID.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobat.jasper", "report", "::[ Kartu Berobat Pasien (KIB) Umum ]::",
                    "select * from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuUmumActionPerformed

    private void MnKartuNonUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnKartuNonUmumActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoID.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.MyReport("rptKartuBerobatAsuransi.jasper", "report", "::[ Kartu Berobat Pasien (KIB) Non Umum ]::",
                    "select * from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ");
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnKartuNonUmumActionPerformed

    private void MnJAMPERSALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnJAMPERSALActionPerformed
        if (nmpnj.getText().equals("")) {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Silakan klik dulu nama pasiennya pada tabel..!!");
            tbregistrasiRalan.requestFocus();
        } else if (nmpnj.getText().equals("JAMPERSAL (PBI)")) {
            DlgJampersal.setSize(490, 125);
            DlgJampersal.setLocationRelativeTo(internalFrame1);
            DlgJampersal.setVisible(true);
        } else {
            emptTeks();
            JOptionPane.showMessageDialog(null, "Jenis cara bayar pasien tersebut tidak sesuai..!!");
            tbregistrasiRalan.requestFocus();
        }
    }//GEN-LAST:event_MnJAMPERSALActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        DlgJampersal.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    private void BtnCloseIn2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn2KeyPressed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        cekjampersal = 0;
        cekjampersal = Sequel.cariInteger("SELECT Count(1) cek FROM bridging_jampersal WHERE jns_rawat='Jalan' and no_rawat='" + TNoRw.getText() + "'");

        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else if (cekjampersal >= 1) {
            JOptionPane.showMessageDialog(null, "Data tidak tersimpan, SEP sudah pernah dibikinkan dengan no. rawat yang sama..!!");
            noSrt1.requestFocus();
        } else {
            Sequel.menyimpan("bridging_jampersal", "'" + TNoRw.getText() + "',"
                    + "'" + noSrt1.getText() + "','Jalan',"
                    + "'" + tglDaftar + "',"
                    + "'" + Valid.SetTgl(TglSurat1.getSelectedItem() + "") + "',"
                    + "'" + TNoRw.getText() + "JJ' ", "Data SEP Jampersal");
            JOptionPane.showMessageDialog(null, "Data berhasil disimpan, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnSimpan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan2KeyPressed

    private void noSrt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noSrt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noSrt1KeyPressed

    private void TglSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglSurat1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSurat1ItemStateChanged

    private void TglSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglSurat1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglSurat1KeyPressed

    private void BtnGantijmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantijmpActionPerformed
        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else {
            Sequel.mengedit("bridging_jampersal", "no_sep='" + sepJmp.getText() + "'", "no_surat='" + noSrt1.getText() + "',"
                    + " tgl_surat='" + Valid.SetTgl(TglSurat1.getSelectedItem() + "") + "' ");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah, SEP siap dicetak......");
            BtnCtkJmp.requestFocus();
        }
    }//GEN-LAST:event_BtnGantijmpActionPerformed

    private void BtnGantijmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantijmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantijmpKeyPressed

    private void BtnCtkJmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCtkJmpActionPerformed
        sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));

        if (noSrt1.getText().trim().equals("")) {
            Valid.textKosong(noSrt1, "No. Surat Keterangan Peserta Jampersal");
        } else if (sepJmp.getText().equals("")) {
            Valid.textKosong(sepJmp, "No. SEP Jampersal");
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
            Valid.MyReport("rptSEPJalan1.jasper", "report", "::[ Cetak SEP Pasien JAMPERSAL Rawat Jalan ]::",
                    " SELECT p.no_rkm_medis, bp.no_sep, rp.tgl_registrasi, p.no_ktp, "
                    + "p.nm_pasien, p.tgl_lahir, IF(p.jk='L','Laki-laki','Perempuan') as jk, "
                    + "pl.nm_poli, rp.no_rawat, bp.no_surat, bp.jns_rawat, bp.tgl_surat, bp.tgl_rawat, pj.png_jawab "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli INNER JOIN bridging_jampersal bp ON bp.no_rawat = rp.no_rawat "
                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE bp.no_sep='" + sepJmp.getText() + "'  "
                    + "AND bp.jns_rawat='Jalan' AND pj.png_jawab LIKE '%jampersal%'", param);
            this.setCursor(Cursor.getDefaultCursor());
            DlgJampersal.dispose();
            emptTeks();
        }
    }//GEN-LAST:event_BtnCtkJmpActionPerformed

    private void BtnCtkJmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCtkJmpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCtkJmpKeyPressed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        DlgTanggalReg.dispose();
        tampilAwal();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnCloseIn3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseIn3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCloseIn3KeyPressed

    private void TglRegItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglRegItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglRegItemStateChanged

    private void TglRegKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglRegKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglRegKeyPressed

    private void BtnGantiTglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiTglActionPerformed
        Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", 
                "tgl_registrasi='" + Valid.SetTgl(TglReg.getSelectedItem() + "") + "'");
        NoRWTbaru(Valid.SetTglMiring(TglReg.getSelectedItem() + ""));
        Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'",
                "no_rawat='" + noRwNew + "'");
        
        DlgTanggalReg.dispose();
        tampilAwal();
        emptTeks();
    }//GEN-LAST:event_BtnGantiTglActionPerformed

    private void BtnGantiTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiTglKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnGantiTglKeyPressed

    private void MnGantiTglRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiTglRegActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoID.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            if ((kdpoli.getText().equals("HDL")) || (kdpoli.getText().equals("-"))) {
                DlgTanggalReg.setSize(459, 67);
                DlgTanggalReg.setLocationRelativeTo(internalFrame1);
                DlgTanggalReg.setVisible(true);
                TglReg.requestFocus();
            } else if (akses.getkode().equals("Admin Utama")) {
                DlgTanggalReg.setSize(459, 67);
                DlgTanggalReg.setLocationRelativeTo(internalFrame1);
                DlgTanggalReg.setVisible(true);
                TglReg.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Tanggal registrasi pasien tidak dapat diganti...!!!!");
            }
        }
    }//GEN-LAST:event_MnGantiTglRegActionPerformed

    private void ppPasienCoronaBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPasienCoronaBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            CoronaPasien form = new CoronaPasien(null, false);
            form.setPasien(TNoRM.getText(), tglDaftar, "Poliklinik");
            form.isCek();
            form.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            form.Inisial.requestFocus();
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppPasienCoronaBtnPrintActionPerformed

    private void BtnSimpanRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRujukActionPerformed
        tglDaftar = Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar");
        tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d') tgl_daftar");
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where tgl_registrasi='" + tglDaftar + "' and "
                + "kd_poli='" + kdpoliRujuk.getText() + "' and no_rkm_medis=?", cekPasien, TNoRM.getText());

        if (TNoReg.getText().trim().equals("")) {
            Valid.textKosong(TNoReg, "No.Registrasi");
        } else if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else if (norwPerujuk.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data rujukan internal poliklinik pasien yg. dipilih...!!!");
        } else if (kdpoliRujuk.getText().equals("")) {
            Valid.textKosong(kdpoliRujuk, "Poliklinik Tujuan");
        } else if (kdDokterRujuk.getText().equals("")) {
            Valid.textKosong(kdDokterRujuk, "Dokter Spesialis");
        } else if (Sequel.cariInteger("select count(pasien.no_rkm_medis) from pasien inner join reg_periksa inner join kamar_inap "
                + "on reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.no_rawat=kamar_inap.no_rawat "
                + "where kamar_inap.stts_pulang='-' and pasien.no_rkm_medis=?", TNoRM.getText()) > 0) {
            JOptionPane.showMessageDialog(null, "Pasien sedang dalam masa perawatan di kamar inap..!!");
        } else if (!cekPasien.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien ini sdh. terdaftar dipoli & tanggal yang sama...");
            BtnPoliRujuk.requestFocus();
        } else {
            JnsnoID.setSelectedIndex(0);
            TNoID.setText(TNoRM.getText());
            isCekPasien();
            isNumberRujuk();
            if (Sequel.menyimpantf("reg_periksa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 18,
                    new String[]{TNoReg.getText(), TNoRw.getText(), tglDaftar, Sequel.cariIsi("SELECT TIME(NOW()) jam"),
                        kdDokterRujuk.getText(), TNoRM.getText(), kdpoliRujuk.getText(), TPngJwb.getText(), TAlmt.getText(), THbngn.getText(), TBiaya.getText(), "Belum",
                        TStatus.getText(), "Ralan", kdpnj.getText(), umur, sttsumur, akses.getkode()}) == true) {

                Sequel.menyimpan("reg_rujukan_intern", "'" + norwPerujuk.getText() + "','" + TNoRw.getText() + "'", "Reg. Rujukan Internal Poliklinik");
                UpdateUmur();
                Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'", "suku_bangsa='" + kdsuku.getText() + "', bahasa_pasien='" + kdbahasa.getText() + "' ");

                BtnKeluar5ActionPerformed(null);
                JOptionPane.showMessageDialog(null, "Registrasi rujukan internal poliklinik telah tersimpan...!!!");
                tampilAwal();
                emptTeks();
                isNumber();
            }
        }
    }//GEN-LAST:event_BtnSimpanRujukActionPerformed

    private void BtnKeluar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar5ActionPerformed
        DlgRegRujukInternal.dispose();
        norwPerujuk.setText("");
        kdpoliRujuk.setText("");
        nmpoliRujuk.setText("");
        kdDokterRujuk.setText("");
        nmDokterRujuk.setText("");
        emptTeks();
        tampilAwal();
    }//GEN-LAST:event_BtnKeluar5ActionPerformed

    private void BtnPoliRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliRujukActionPerformed
        nmpoliRujuk.setText("");
        kdpoliRujuk.setText("");
        kdDokterRujuk.setText("");
        nmDokterRujuk.setText("");

        akses.setform("DlgReg");
        pilihan = 1;

        if (aktifjadwal.equals("aktif")) {
            if (akses.getkode().equals("Admin Utama")) {
                poli3.isCek();
                poli3.setSize(1048, 653);
                poli3.setLocationRelativeTo(internalFrame1);
                poli3.setVisible(true);
                poli3.emptTeks();
            } else {
                poli4.isCek();
                poli4.tampil();
                poli4.setSize(1048, 653);
                poli4.setLocationRelativeTo(internalFrame1);
                poli4.setVisible(true);
                poli4.emptTeks();
            }
        } else {
            poli3.isCek();
            poli3.setSize(1048, 653);
            poli3.setLocationRelativeTo(internalFrame1);
            poli3.setVisible(true);
            poli3.emptTeks();
        }
    }//GEN-LAST:event_BtnPoliRujukActionPerformed

    private void BtnDokterRujukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterRujukActionPerformed
        pilihan = 1;
        akses.setform("DlgReg");
        if (aktifjadwal.equals("aktif")) {
            if (akses.getkode().equals("Admin Utama")) {
                dokter3.isCek();
                dokter3.TCari.requestFocus();
                dokter3.setSize(1041, 332);
                dokter3.setLocationRelativeTo(internalFrame1);
                dokter3.setVisible(true);
                dokter3.emptTeks();
            } else {
                dokter4.setPoli(nmpoliRujuk.getText());
                dokter4.isCek();
                dokter4.tampil();
                dokter4.TCari.requestFocus();
                dokter4.setSize(1041, 332);
                dokter4.setLocationRelativeTo(internalFrame1);
                dokter4.setVisible(true);
                dokter4.emptTeks();
            }
        } else {
            dokter3.isCek();
            dokter3.TCari.requestFocus();
            dokter3.setSize(1041, 332);
            dokter3.setLocationRelativeTo(internalFrame1);
            dokter3.setVisible(true);
            dokter3.emptTeks();
        }
    }//GEN-LAST:event_BtnDokterRujukActionPerformed

    private void MnRujukInternalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRujukInternalActionPerformed
        cekRujuk = 0;
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else if (!tglDaftar.equals(Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar"))) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya berlaku untuk tanggal yang sama...!!!");
        } else {
            cekRujuk = Sequel.cariInteger("SELECT count(-1) FROM reg_rujukan_intern where no_rawat_dari='" + Kd2.getText() + "'");
            if (cekRujuk == 0) {
                DlgRegRujukInternal.setSize(608, 213);
                DlgRegRujukInternal.setLocationRelativeTo(internalFrame1);
                DlgRegRujukInternal.setVisible(true);
                kdpoliRujuk.setText("");
                nmpoliRujuk.setText("");
                kdDokterRujuk.setText("");
                nmDokterRujuk.setText("");
                BtnPoliRujuk.requestFocus();
            } else if (cekRujuk > 0) {
                JOptionPane.showMessageDialog(null, "Data rujukan internal poliklinik pasien ini sudah tersimpan...!!!");
                tampilAwal();
                emptTeks();
            }
        }
    }//GEN-LAST:event_MnRujukInternalActionPerformed

    private void ppCekFingerPrinBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppCekFingerPrinBtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TNoReg.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else if (!Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat='" + Kd2.getText() + "'").equals("B01")) {
            JOptionPane.showMessageDialog(null, "Maaf, fitur ini hanya untuk pasien BPJS Kesehatan saja...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            noka = "";
            noka = Sequel.cariIsi("select no_peserta from pasien where no_rkm_medis='" + tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 7).toString() + "'");

            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                prop.loadFromXML(new FileInputStream("setting/database.xml"));
                URL = prop.getProperty("URLAPIBPJS") + "SEP/FingerPrint/Peserta/" + noka + "/TglPelayanan/" + tglDaftar;

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
                System.out.println("code : " + nameNode.path("code").asText());
                System.out.println("message : " + nameNode.path("message").asText());

                if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------            
                    JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------                
                    JOptionPane.showMessageDialog(null, "Pasien A.n. " + TPasien.getText() + ", dg. tgl. kunjungan : "
                            + Valid.SetTglINDONESIA(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 3).toString())
                            + ", sesuai data dari BPJS bahwa " + response.path("status").asText());
                } else {
                    System.out.println("Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", isi pesan : " + nameNode.path("message").asText());
                    JOptionPane.showMessageDialog(null, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", isi pesan : " + nameNode.path("message").asText());
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Peserta : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_ppCekFingerPrinBtnPrintActionPerformed

    private void ppSuratKontrolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppSuratKontrolActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
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
                        TNoRM.getText(),
                        TPasien.getText(),
                        Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'"),
                        Sequel.cariIsi("select IF(jk='L','Laki-laki','Perempuan') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"),
                        Sequel.cariIsi("select nmdiagnosaawal from bridging_sep where no_rawat='" + TNoRw.getText() + "'"),
                        kdpoli.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.ChkInput.setSelected(true);
                form.TCari.setText(TNoRM.getText());
                form.tampil();
                form.isForm();
                form.isCek();
                form.setVisible(true);
            }
        }
    }//GEN-LAST:event_ppSuratKontrolActionPerformed

    private void BtnSimpanOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanOperatorActionPerformed
        if (loketRalan.isSelected() == false && loketRanap.isSelected() == false
                && loket1.isSelected() == false && loket2.isSelected() == false
                && loket3.isSelected() == false && loket4.isSelected() == false
                && loket5.isSelected() == false && loket6.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Anda belum menentukan jenis & nomor loketnya...!!!!");
        } else {
            if (loketRalan.isSelected() == true) {
                akses.setNMLoket("ralan");
            } else if (loketRanap.isSelected() == true) {
                akses.setNMLoket("ranap");
            } else {
                akses.setNMLoket("");
            }

            if (loket1.isSelected() == true) {
                akses.setNomorLoket("1");
            } else if (loket2.isSelected() == true) {
                akses.setNomorLoket("2");
            } else if (loket3.isSelected() == true) {
                akses.setNomorLoket("3");
            } else if (loket4.isSelected() == true) {
                akses.setNomorLoket("4");
            } else if (loket5.isSelected() == true) {
                akses.setNomorLoket("5");
            } else if (loket6.isSelected() == true) {
                akses.setNomorLoket("6");
            } else {
                akses.setNomorLoket("");
            }

            if (akses.getJenisLoket().equals("") && akses.getNomorLoket().equals("")) {
                statusOperator.setText("Anda belum menentukan jenis & no. loketnya.");
            } else if (!akses.getJenisLoket().equals("") && akses.getNomorLoket().equals("")) {
                statusOperator.setText("Anda sebagai loket " + akses.getJenisLoket() + " dan nomor loketnya belum dipilih.");
            } else if (akses.getJenisLoket().equals("") && !akses.getNomorLoket().equals("")) {
                statusOperator.setText("Anda belum memilih jenis loketnya dan memilih nomor loket " + akses.getNomorLoket());
            } else {
                statusOperator.setText("Anda sebagai loket " + akses.getJenisLoket() + " utk. melayani pasien diloket " + akses.getNomorLoket());
            }
        }
    }//GEN-LAST:event_BtnSimpanOperatorActionPerformed

    private void BtnPangBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPangBPJSActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infobpjs2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian BPJS,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infobpjs2.getText().equals("Antrian pasien rawat jalan BPJS sudah habis.")) {
                JOptionPane.showMessageDialog(null, "Hentikan panggilan untuk pasien BPJS karena sudah habis dilayani, terima kasih...!!");
            } else {
                tambahPangBPJS();
                suaraPanggilanBPJS();
                Sequel.mengedit("antrian_pemanggil_bpjs", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnPangBPJSActionPerformed

    private void BtnUlangPangBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangPangBPJSActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infobpjs2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian BPJS,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infobpjs2.getText().contains("Masih ada") == true && nobpjs.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lakukan panggilan dulu, kemudian panggilannya baru bisa diulangi...!!");
            } else {
                Sequel.mengedit("antrian_pemanggil_bpjs", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='proses'");
                suaraPanggilanBPJS();
                Sequel.mengedit("antrian_pemanggil_bpjs", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnUlangPangBPJSActionPerformed

    private void BtnPangUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPangUmumActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infoumum2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian Umum (Non BPJS),...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infoumum2.getText().equals("Antrian pasien rawat jalan Umum (NON BPJS) sudah habis.")) {
                JOptionPane.showMessageDialog(null, "Hentikan panggilan untuk pasien Umum (Non BPJS) karena sudah habis dilayani, terima kasih...!!");
            } else {
                tambahPangUmum();
                suaraPanggilanUMUM();
                Sequel.mengedit("antrian_pemanggil_umum", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnPangUmumActionPerformed

    private void BtnUlangPangUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangPangUmumActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infoumum2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian Umum (Non BPJS),...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infoumum2.getText().contains("Masih ada") == true && noumum.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lakukan panggilan dulu, kemudian panggilannya baru bisa diulangi...!!");
            } else {
                Sequel.mengedit("antrian_pemanggil_umum", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_umum where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='proses'");
                suaraPanggilanUMUM();
                Sequel.mengedit("antrian_pemanggil_umum", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_umum where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnUlangPangUmumActionPerformed

    private void BtnPangLansiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPangLansiaActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infolb2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian Lansia/Bayi,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infolb2.getText().equals("Antrian pasien rawat jalan LANSIA / BAYI sudah habis.")) {
                JOptionPane.showMessageDialog(null, "Hentikan panggilan untuk pasien Lansia/Bayi karena sudah habis dilayani, terima kasih...!!");
            } else {
                tambahPangLB();
                suaraPanggilanLB();
                Sequel.mengedit("antrian_pemanggil_lansia", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnPangLansiaActionPerformed

    private void BtnUlangPangLansiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangPangLansiaActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (infolb2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian Lansia/Bayi,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (infolb2.getText().contains("Masih ada") == true && nolansia.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lakukan panggilan dulu, kemudian panggilannya baru bisa diulangi...!!");
            } else {
                Sequel.mengedit("antrian_pemanggil_lansia", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='proses'");
                suaraPanggilanLB();
                Sequel.mengedit("antrian_pemanggil_lansia", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnUlangPangLansiaActionPerformed

    private void BtnPangInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPangInapActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (inforanap2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian rawat inap,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (inforanap2.getText().equals("Antrian pasien rawat inap sudah habis.")) {
                JOptionPane.showMessageDialog(null, "Hentikan panggilan untuk pasien rawat inap karena sudah habis dilayani, terima kasih...!!");
            } else {
                tambahPangInap();
                suaraPanggilanINAP();
                Sequel.mengedit("antrian_pemanggil_inap", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnPangInapActionPerformed

    private void BtnUlangPangInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangPangInapActionPerformed
        cekPangBPJS = 0;
        cekPangUmum = 0;
        cekPangLB = 0;
        cekPangInap = 0;

        if (akses.getJenisLoket().equals("Rawat Jalan")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            JOptionPane.showMessageDialog(null, "Anda salah memilih jenis loket untuk panggilan antrian...!!");
        } else if (akses.getJenisLoket().equals("Rawat Inap")
                && (akses.getNomorLoket().equals("1") || akses.getNomorLoket().equals("2")
                || akses.getNomorLoket().equals("3") || akses.getNomorLoket().equals("4")
                || akses.getNomorLoket().equals("5") || akses.getNomorLoket().equals("6"))) {
            cekPangBPJS = Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where status='proses' and date(waktu_panggil)=date(now())");
            cekPangUmum = Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where status='proses' and date(waktu_panggil)=date(now())");
            cekPangLB = Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where status='proses' and date(waktu_panggil)=date(now())");
            cekPangInap = Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where status='proses' and date(waktu_panggil)=date(now())");

            if (inforanap2.getText().equals("Hari ini masih belum ada pasien.")) {
                JOptionPane.showMessageDialog(null, "Belum ada pasien yang mengambil nomor antrian rawat inap,...!!");
            } else if (cekPangBPJS > 0 || cekPangUmum > 0 || cekPangLB > 0 || cekPangInap > 0) {
                JOptionPane.showMessageDialog(null, "Loket lain masih memanggil antrian, tunggu sebentar,...!!");
            } else if (inforanap2.getText().contains("Masih ada") == true && noinap.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Lakukan panggilan dulu, kemudian panggilannya baru bisa diulangi...!!");
            } else {
                Sequel.mengedit("antrian_pemanggil_inap", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_inap where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='proses'");
                suaraPanggilanINAP();
                Sequel.mengedit("antrian_pemanggil_inap", "no_antrian='" + Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_inap where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1") + "'",
                        "status='ok'");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan tentukan dulu jenis & nomor loketnya utk. melakukan panggilan antrian & simpan sbg. operator..!!");
        }
    }//GEN-LAST:event_BtnUlangPangInapActionPerformed

    private void tbrestorAntrianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbrestorAntrianMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataAntrian();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbrestorAntrianMouseClicked

    private void tbrestorAntrianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbrestorAntrianKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataAntrian();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbrestorAntrianKeyPressed

    private void Scroll2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Scroll2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Scroll2MouseClicked

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilHistoryReset();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (jnsReset.getText().equals("") && noTerakhir.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel history nomor antrian..!!");
            tbrestorAntrian.requestFocus();
        } else {
            i = JOptionPane.showConfirmDialog(null, "Sebelum proses restore " + jnsReset.getText() + ", nomor saat ini akan direset terlebih dulu. Anda yakin..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (i == JOptionPane.YES_OPTION) {
                //nomor panggilan
                if (jnsReset.getText().equals("Panggilan Antrian BPJS")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_pemanggil_bpjs where date(waktu_panggil) = date(now())");
                    
                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                                + "'ok','1','" + Sequel.cariIsi("select now()") + "'", "Restore nomor panggilan antrian pasien BPJS");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor panggilan antrian pasien BPJS berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Panggilan Antrian LANSIA-BAYI")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_pemanggil_lansia where date(waktu_panggil) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                                + "'ok','1','" + Sequel.cariIsi("select now()") + "'", "Restore nomor panggilan antrian pasien LANSIA-BAYI");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor panggilan antrian pasien LANSIA-BAYI berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Panggilan Antrian Umum")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_pemanggil_umum where date(waktu_panggil) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                                + "'ok','1','" + Sequel.cariIsi("select now()") + "'", "Restore nomor panggilan antrian pasien Umum");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor panggilan antrian pasien Umum berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Panggilan Antrian Ranap")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_pemanggil_inap where date(waktu_panggil) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                                + "'ok','1','" + Sequel.cariIsi("select now()") + "'", "Restore nomor panggilan antrian pasien Ranap");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor panggilan antrian pasien ranap berhasil dikembalikan..!!");
                    
                //nomor antrian pasien    
                } else if (jnsReset.getText().equals("Nomor Antrian Umum")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_nomor_umum where date(waktu_cetak) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_nomor_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_nomor_umum where date(waktu_cetak)=date(now())") + "',"
                                + "'oke','" + Sequel.cariIsi("select now()") + "'", "Restore Nomor Antrian Pasien Rawat Jalan Umum (Non BPJS)");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor antrian pasien rawat jalan Umum (Non BPJS) berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Nomor Antrian LANSIA-BAYI")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_nomor_lansia where date(waktu_cetak) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_nomor_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_nomor_lansia where date(waktu_cetak)=date(now())") + "',"
                                + "'oke','" + Sequel.cariIsi("select now()") + "'", "Restore Nomor Antrian Pasien Rawat Jalan LANSIA-BAYI");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor antrian pasien rawat jalan LANSIA-BAYI berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Nomor Antrian BPJS")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_nomor_bpjs where date(waktu_cetak) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_nomor_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_nomor_bpjs where date(waktu_cetak)=date(now())") + "',"
                                + "'oke','" + Sequel.cariIsi("select now()") + "'", "Restore Nomor Antrian Pasien Rawat Jalan BPJS");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor antrian pasien rawat jalan BPJS berhasil dikembalikan..!!");
                } else if (jnsReset.getText().equals("Nomor Antrian Ranap")) {
                    x = 0;
                    x = Integer.parseInt(noTerakhir.getText());
                    Sequel.queryu("delete from antrian_nomor_inap where date(waktu_cetak) = date(now())");

                    for (i = 0; i < x; i++) {
                        Sequel.menyimpan("antrian_nomor_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_nomor_inap where date(waktu_cetak)=date(now())") + "',"
                                + "'oke','" + Sequel.cariIsi("select now()") + "'", "Restore Nomor Antrian Pasien Rawat Inap");
                    }

                    emptTeksRestor();
                    tampilHistoryReset();
                    JOptionPane.showMessageDialog(null, "Proses restore nomor antrian pasien rawat inap berhasil dikembalikan..!!");
                }
            }
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnSimpanOperator1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanOperator1ActionPerformed
        i = JOptionPane.showConfirmDialog(null, "Apakah cek audio/suara panggilan antrian pasien akan dilakukan...??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            Sequel.mengedit("antrian_pemanggil_bpjs", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            Sequel.mengedit("antrian_pemanggil_umum", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            Sequel.mengedit("antrian_pemanggil_lansia", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");
            Sequel.mengedit("antrian_pemanggil_inap", "status='proses' and date(waktu_panggil)=date(now())", "status='ok'");

            JOptionPane.showMessageDialog(null, "Perbaikan error audio/suara panggilan antrian pasien berhasil dilakukan, proses pemanggilan antrian bisa dilakukan lagi...!!");
        }
    }//GEN-LAST:event_BtnSimpanOperator1ActionPerformed

    private void MnPrinterBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaruActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangDewasa.jasper", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaruActionPerformed

    private void MnPrinterLamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLamaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
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
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptBarcodeRM7.jasper", "report", "::[ Gelang Pasien Dewasa/Anak (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLamaActionPerformed

    private void MnPrinterBaru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaru1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangBayi2021.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaru1ActionPerformed

    private void MnPrinterLama1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterLama1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada table...!!!");
            TCari.requestFocus();
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
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangBayi.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                    "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterLama1ActionPerformed

    private void MnBarcodeRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
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
            Valid.MyReport("rptBarcodeRM11.jasper", "report", "::[ BARCODE (Label BESAR Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab  where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM1ActionPerformed

    private void MnBarcodeRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBarcodeRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
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
            Valid.MyReport("rptBarcodeRM111.jasper", "report", "::[ BARCODE (Label KECIL Rekam Medis) ]::", "select pasien.no_rkm_medis, pasien.nm_pasien, pasien.no_ktp, pasien.jk, "
                    + "pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d/%m/%Y') as tgl_lahir, pasien.nm_ibu, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.gol_darah, pasien.pekerjaan,"
                    + "pasien.stts_nikah,pasien.agama,pasien.tgl_daftar,pasien.no_tlp,pasien.umur,"
                    + "pasien.pnd, pasien.keluarga, pasien.namakeluarga,penjab.png_jawab,pasien.pekerjaanpj,"
                    + "concat(pasien.alamatpj,', ',pasien.kelurahanpj,', ',pasien.kecamatanpj,', ',pasien.kabupatenpj) as alamatpj from pasien "
                    + "inner join kelurahan inner join kecamatan inner join kabupaten "
                    + "inner join penjab on pasien.kd_pj=penjab.kd_pj and pasien.kd_kel=kelurahan.kd_kel "
                    + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnBarcodeRM2ActionPerformed

    private void MnLabelRM1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptLabelRM.jasper", "report", "::[ LABEL BESAR IDENTITAS (Rekam Medis) ]::",
                    "select no_rkm_medis, nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM1ActionPerformed

    private void MnLabelRM2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelRM2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoRM.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data pasien dengan mengklik data pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                    + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptLabelRM1.jasper", "report", "::[ LABEL KECIL IDENTITAS (Rekam Medis) ]::",
                    "select no_rkm_medis, nm_pasien from pasien where pasien.no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnLabelRM2ActionPerformed

    private void MnPrinterBaru2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPrinterBaru2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data registrasi sudah habis...!!!!");
            TCari.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu data registrasi pada tabel...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("kotars", akses.getkabupatenrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("tgl_lahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d') from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.bulanINDONESIA("select month(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + " "
                + Sequel.cariIsi("select year(tgl_lahir) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            Valid.MyReport("rptGelangBayi2022.jasper", "report", "::[ Gelang Pasien Bayi (Laki-laki/Perempuan) ]::",
                "select no_rkm_medis, nm_pasien, jk from pasien where no_rkm_medis='" + TNoRM.getText() + "' ", param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPrinterBaru2ActionPerformed

    private void MrkChampionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkChampionActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap7.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek CHAMPION ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkChampionActionPerformed

    private void MrkAjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAjpActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap6.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek AJP BRAND ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkAjpActionPerformed

    private void MrkCoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkCoxActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap5.jasper", "report", "::[ Label Pasien (4,9 x 1,8 Cm) Merek COX ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkCoxActionPerformed

    private void MrkAlfaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkAlfaActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap2.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek ALFA PREMIUM ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkAlfaActionPerformed

    private void MrkOleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkOleanActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap3.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek OLEAN CITY BRAND ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkOleanActionPerformed

    private void MrkKojicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MrkKojicoActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap4.jasper", "report", "::[ Label Pasien (4,9 x 1,9 Cm) Merek KOJICO BRAND ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MrkKojicoActionPerformed

    private void MnLabelPxRanap1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap1ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap.jasper", "report", "::[ Label Pasien (3,9 x 1,9 Cm) ]::",
                "select no_rkm_medis, concat(nm_pasien,' (',if(jk='L','LK','PR'),')') nm_pasien, "
                + "date_format(tgl_lahir,'%d-%m-%Y') tgl_lhr from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap1ActionPerformed

    private void MnLabelPxRanap2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLabelPxRanap2ActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRM.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu salah satu data pasiennya pada tabel...!!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            Valid.MyReport("rptLabelPxRanap1.jasper", "report", "::[ Label Pasien (6,4 x 3,2 Cm) ]::",
                "select p.no_rkm_medis, concat(p.nm_pasien,' (',if(p.jk='L','LK','PR'),')') nm_pasien, date_format(p.tgl_lahir,'%d %M %Y') tgl_lhr, "
                + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat from pasien p "
                + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab where p.no_rkm_medis='" + TNoRM.getText() + "'");
        }
    }//GEN-LAST:event_MnLabelPxRanap2ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar6ActionPerformed
        BtnKeluarActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar6ActionPerformed

    private void BtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResetActionPerformed
        i = JOptionPane.showConfirmDialog(null, "Apakah semua nomor antrian pasien & panggilan yakin akan direset..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (i == JOptionPane.YES_OPTION) {
            resetPanggilanAntrian();
            resetNomorAntrian();
            JOptionPane.showMessageDialog(null, "Proses reset nomor antrian pasien & panggilan selesai dilakukan...!!!!");
            tampilHistoryReset();
        }
    }//GEN-LAST:event_BtnResetActionPerformed

    private void MnGantiUmurRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGantiUmurRegActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data pasien sudah habis...!!!!");
            TNoID.requestFocus();
        } else if (TPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih dulu salah satu data pasien pada tabel...!!!");
            tbregistrasiRalan.requestFocus();
        } else {
            if (akses.getkode().equals("Admin Utama")) {                
                DlgGantiUmurReg.setSize(457, 98);
                DlgGantiUmurReg.setLocationRelativeTo(internalFrame1);
                DlgGantiUmurReg.setVisible(true);
                Tumur.requestFocus();
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, akses anda tertutup untuk menu ini...!!!!");
            }
        }
    }//GEN-LAST:event_MnGantiUmurRegActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        DlgGantiUmurReg.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnGantiUmurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiUmurActionPerformed
        if (Tumur.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Isi umurnya dulu dengan benar...!!!!");
            Tumur.requestFocus();
        } else {
            Sequel.mengedit("reg_periksa", "no_rawat='" + Kd2.getText() + "'",
                    "umurdaftar='" + Tumur.getText() + "', sttsumur='" + cmbSttsUmur.getSelectedItem().toString() + "'");
            Sequel.mengedit("pasien", "no_rkm_medis='" + TNoRM.getText() + "'",
                    "tgl_lahir='" + Valid.SetTgl(Ttgl_lahir.getSelectedItem() + "") + "'");
            BtnCloseIn4ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnGantiUmurActionPerformed

    private void MnPetugasRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPetugasRegActionPerformed
        tampil();
        infoSEP();
    }//GEN-LAST:event_MnPetugasRegActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgReg dialog = new DlgReg(new javax.swing.JFrame(), true);
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
    private widget.TextBox AsalRujukan;
    private widget.Button BtnAll;
    private widget.Button BtnBahasa;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCtkJkd;
    private widget.Button BtnCtkJmp;
    private widget.Button BtnDokter;
    private widget.Button BtnDokterRujuk;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiTgl;
    private widget.Button BtnGantiUmur;
    private widget.Button BtnGantijkd;
    private widget.Button BtnGantijmp;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnKeluar3;
    private widget.Button BtnKeluar5;
    private widget.Button BtnKeluar6;
    private widget.Button BtnPangBPJS;
    private widget.Button BtnPangInap;
    private widget.Button BtnPangLansia;
    private widget.Button BtnPangUmum;
    private widget.Button BtnPasien;
    private widget.Button BtnPoliRujuk;
    private widget.Button BtnPrint;
    private widget.Button BtnPrint2;
    private widget.Button BtnPrint3;
    private widget.Button BtnPrint4;
    private widget.Button BtnReset;
    private widget.Button BtnRestor;
    private widget.Button BtnSeek3;
    private widget.Button BtnSeek4;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpanOperator;
    private widget.Button BtnSimpanOperator1;
    private widget.Button BtnSimpanRujuk;
    private widget.Button BtnSuku;
    private widget.Button BtnUlangPangBPJS;
    private widget.Button BtnUlangPangInap;
    private widget.Button BtnUlangPangLansia;
    private widget.Button BtnUlangPangUmum;
    private widget.Button BtnUnit;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkTracker;
    private widget.TextBox CrDokter;
    private widget.TextBox CrPoli;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JDialog DlgDemografi;
    private javax.swing.JDialog DlgGantiUmurReg;
    private javax.swing.JDialog DlgJamkesda;
    private javax.swing.JDialog DlgJampersal;
    private javax.swing.JDialog DlgRegRujukInternal;
    private javax.swing.JDialog DlgSakit;
    private javax.swing.JDialog DlgTanggalReg;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.ComboBox JnsnoID;
    private widget.TextBox Kabupaten2;
    private widget.TextBox Kd2;
    private widget.TextBox Kecamatan2;
    private widget.TextBox Kelurahan2;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnBarcode;
    private javax.swing.JMenuItem MnBarcodeRM1;
    private javax.swing.JMenuItem MnBarcodeRM2;
    private javax.swing.JMenuItem MnBatal;
    private javax.swing.JMenuItem MnBelum;
    private javax.swing.JMenuItem MnBilling;
    private javax.swing.JMenu MnBridging;
    private javax.swing.JMenuItem MnBuktiPelayananRalan;
    private javax.swing.JMenuItem MnCetakBebasNarkoba;
    private javax.swing.JMenu MnCetakKelengkapanInap;
    private javax.swing.JMenuItem MnCetakRegister;
    private javax.swing.JMenuItem MnCetakRegister1;
    private javax.swing.JMenuItem MnCetakSuratSakit;
    private javax.swing.JMenuItem MnCetakSuratSakit2;
    private javax.swing.JMenuItem MnCetakSuratSakit3;
    private javax.swing.JMenuItem MnCetakSuratSehat;
    private javax.swing.JMenuItem MnCetakSuratSehat1;
    private javax.swing.JMenuItem MnCheckList;
    private javax.swing.JMenuItem MnCheckList1;
    private javax.swing.JMenuItem MnCheckList2;
    private javax.swing.JMenuItem MnCheckList3;
    private javax.swing.JMenuItem MnCheckList4;
    private javax.swing.JMenuItem MnCheckList5;
    private javax.swing.JMenuItem MnCheckList6;
    private javax.swing.JMenuItem MnCheckList7;
    private javax.swing.JMenuItem MnGChalaman1;
    private javax.swing.JMenuItem MnGChalaman2;
    private javax.swing.JMenuItem MnGantiTglReg;
    private javax.swing.JMenuItem MnGantiUmurReg;
    private javax.swing.JMenuItem MnGelang1;
    private javax.swing.JMenuItem MnGelang2;
    private javax.swing.JMenuItem MnGelang3;
    private javax.swing.JMenuItem MnGelang4;
    private javax.swing.JMenuItem MnGelang5;
    private javax.swing.JMenuItem MnGelang6;
    private javax.swing.JMenu MnGelangBayi;
    private javax.swing.JMenu MnGelangDewasaAnak;
    private javax.swing.JMenuItem MnHemodialisa;
    private javax.swing.JMenuItem MnJAMKESDA;
    private javax.swing.JMenuItem MnJAMPERSAL;
    private javax.swing.JMenuItem MnJKRA;
    private javax.swing.JMenuItem MnKamarInap;
    private javax.swing.JMenu MnKartu;
    private javax.swing.JMenuItem MnKartuNonUmum;
    private javax.swing.JMenuItem MnKartuUmum;
    private javax.swing.JMenuItem MnLabelPxRanap1;
    private javax.swing.JMenuItem MnLabelPxRanap2;
    private javax.swing.JMenu MnLabelPxRanap3;
    private javax.swing.JMenuItem MnLabelRM1;
    private javax.swing.JMenuItem MnLabelRM2;
    private javax.swing.JMenuItem MnLabelTracker;
    private javax.swing.JMenuItem MnLabelTracker1;
    private javax.swing.JMenuItem MnLabelTracker2;
    private javax.swing.JMenuItem MnLabelTracker3;
    private javax.swing.JMenuItem MnLaporanRekapJenisBayar;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulanan;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganBulananPoli;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganDokter;
    private javax.swing.JMenuItem MnLaporanRekapKunjunganPoli;
    private javax.swing.JMenuItem MnLaporanRekapPenyakitRalan;
    private javax.swing.JMenuItem MnLaporanRekapPerujuk;
    private javax.swing.JMenuItem MnLaporanRekapRawatDarurat;
    private javax.swing.JMenuItem MnLembarCasemix;
    private javax.swing.JMenuItem MnManualSEPBPJS;
    private javax.swing.JMenu MnPemeriksaan;
    private javax.swing.JMenuItem MnPeriksaLab;
    private javax.swing.JMenuItem MnPeriksaRadiologi;
    private javax.swing.JMenuItem MnPersetujuanMedis;
    private javax.swing.JMenuItem MnPetugasReg;
    private javax.swing.JMenuItem MnPrinterBaru;
    private javax.swing.JMenuItem MnPrinterBaru1;
    private javax.swing.JMenuItem MnPrinterBaru2;
    private javax.swing.JMenuItem MnPrinterLama;
    private javax.swing.JMenuItem MnPrinterLama1;
    private javax.swing.JMenuItem MnRujuk;
    private javax.swing.JMenuItem MnRujukInternal;
    private javax.swing.JMenuItem MnRujukMasuk;
    private javax.swing.JMenuItem MnRujukSisrute;
    private javax.swing.JMenu MnRujukan;
    private javax.swing.JMenuItem MnSEP;
    private javax.swing.JMenuItem MnSJP;
    private javax.swing.JMenuItem MnSPBK;
    private javax.swing.JMenu MnStatus;
    private javax.swing.JMenuItem MnSudah;
    private javax.swing.JMenuItem MrkAjp;
    private javax.swing.JMenuItem MrkAlfa;
    private javax.swing.JMenuItem MrkChampion;
    private javax.swing.JMenuItem MrkCox;
    private javax.swing.JMenuItem MrkKojico;
    private javax.swing.JMenuItem MrkOlean;
    private widget.TextBox NoBalasan;
    private widget.TextBox NoPeserta;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TAlmt;
    private widget.TextBox TBiaya;
    private widget.TextBox TCari;
    private widget.TextBox TDokter;
    private widget.TextBox THbngn;
    private widget.TextBox TNoID;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoReg;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TPngJwb;
    private widget.TextBox TPoli;
    private widget.TextBox TStatus;
    public javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglReg;
    private widget.Tanggal TglSakit1;
    private widget.Tanggal TglSakit2;
    private widget.Tanggal TglSurat;
    private widget.Tanggal TglSurat1;
    private widget.Tanggal Ttgl_lahir;
    private widget.TextBox Tumur;
    private widget.Button btnKab;
    private widget.Button btnKec;
    private widget.Button btnKel;
    private widget.Button btnPenjab;
    private widget.Button btnPenjab1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private widget.TextBox cekPasien;
    private widget.ComboBox cmbSttsUmur;
    private widget.TextBox drPerujuk;
    private widget.Label infobpjs1;
    private widget.Label infobpjs2;
    private widget.Label infolb1;
    private widget.Label infolb2;
    private widget.Label inforanap1;
    private widget.Label inforanap2;
    private widget.Label infoumum1;
    private widget.Label infoumum2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame22;
    private widget.InternalFrame internalFrame25;
    private widget.InternalFrame internalFrame26;
    private widget.InternalFrame internalFrame27;
    private widget.InternalFrame internalFrame28;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
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
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel5;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel53;
    private widget.Label jLabel54;
    private widget.Label jLabel55;
    private widget.Label jLabel56;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel61;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jamMati;
    private widget.TextBox jnsReset;
    private widget.TextBox kdDokterRujuk;
    private widget.TextBox kdbahasa;
    private widget.TextBox kddokter;
    private widget.TextBox kdpnj;
    private widget.TextBox kdpoli;
    private widget.TextBox kdpoliRujuk;
    private widget.TextBox kdsuku;
    private widget.TextBox kode_rujukanya;
    private widget.TextArea label_pesan;
    private widget.TextBox lmsakit;
    private widget.RadioButton loket1;
    private widget.RadioButton loket2;
    private widget.RadioButton loket3;
    private widget.RadioButton loket4;
    private widget.RadioButton loket5;
    private widget.RadioButton loket6;
    private widget.CekBox loketRalan;
    private widget.CekBox loketRanap;
    private widget.TextBox nmDokterRujuk;
    private widget.TextBox nmbahasa;
    private widget.TextBox nmpnj;
    private widget.TextBox nmpoliRujuk;
    private widget.TextBox nmsuku;
    private widget.TextBox noSrt;
    private widget.TextBox noSrt1;
    private widget.TextBox noTerakhir;
    private widget.Label nobpjs;
    private widget.Label noinap;
    private widget.Label nolansia;
    private widget.TextBox norwPerujuk;
    private widget.Label noumum;
    private widget.PanelBiasa panelBiasa2;
    private widget.PanelBiasa panelBiasa3;
    private widget.PanelBiasa panelBiasa5;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox poliPerujuk;
    private javax.swing.JMenuItem ppBerkas;
    private javax.swing.JMenuItem ppCatatanPasien;
    private javax.swing.JMenuItem ppCekFingerPrin;
    private javax.swing.JMenuItem ppGrafikDemografi;
    private javax.swing.JMenuItem ppGrafikPerAgama;
    private javax.swing.JMenuItem ppGrafikPerBulan;
    private javax.swing.JMenuItem ppGrafikPerJK;
    private javax.swing.JMenuItem ppGrafikPerPekerjaan;
    private javax.swing.JMenuItem ppGrafikPerTahun;
    private javax.swing.JMenuItem ppGrafikPerTanggal;
    private javax.swing.JMenuItem ppGrafikPerdokter;
    private javax.swing.JMenuItem ppGrafikPerdokter1;
    private javax.swing.JMenuItem ppGrafikPerdokter2;
    private javax.swing.JMenuItem ppGrafikPerpoli;
    private javax.swing.JMenuItem ppGrafikPerpoli1;
    private javax.swing.JMenuItem ppGrafikPerpoli2;
    private javax.swing.JMenuItem ppPasienCorona;
    private javax.swing.JMenuItem ppRiwayat;
    private javax.swing.JMenuItem ppSuratKontrol;
    private widget.ProgressBar prosesTombol;
    private widget.TextBox rmMati;
    private widget.TextBox sepJkd;
    private widget.TextBox sepJmp;
    private widget.Label statusOperator;
    private widget.Table tbregistrasiRalan;
    private widget.Table tbrestorAntrian;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    private widget.TextBox tglMati;
    private widget.TextBox tglReset;
    private widget.Label tulisan_tanggal;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        String petugasSIPO = "";        
        try {
            ps = koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,"
                    + "IFNULL(bridging_sep.no_sep,'-') nosep, if(reg_periksa.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) nm_petugas "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab on reg_periksa.kd_dokter=dokter.kd_dokter and "
                    + "reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "LEFT JOIN bridging_sep ON bridging_sep.no_rawat = reg_periksa.no_rawat left join pegawai pg on pg.nik = reg_periksa.nip_petugas where "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_reg like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.tgl_registrasi like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.kd_dokter like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.stts_daftar like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and poliklinik.nm_poli like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.p_jawab like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.almt_pj like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.hubunganpj like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and penjab.png_jawab like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and "
                    + "if(reg_periksa.nip_petugas='Admin Utama','Admin Utama',ifnull(pg.nama,'-')) like ? order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc");
            try {
                ps.setString(1, "%" + CrPoli.getText() + "%");
                ps.setString(2, "%" + CrDokter.getText() + "%");
                ps.setString(3, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(4, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + CrPoli.getText() + "%");
                ps.setString(7, "%" + CrDokter.getText() + "%");
                ps.setString(8, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(9, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + CrPoli.getText() + "%");
                ps.setString(12, "%" + CrDokter.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + CrPoli.getText() + "%");
                ps.setString(17, "%" + CrDokter.getText() + "%");
                ps.setString(18, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(19, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, "%" + CrPoli.getText() + "%");
                ps.setString(22, "%" + CrDokter.getText() + "%");
                ps.setString(23, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(24, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                ps.setString(26, "%" + CrPoli.getText() + "%");
                ps.setString(27, "%" + CrDokter.getText() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, "%" + CrPoli.getText() + "%");
                ps.setString(32, "%" + CrDokter.getText() + "%");
                ps.setString(33, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(34, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(35, "%" + TCari.getText().trim() + "%");
                ps.setString(36, "%" + CrPoli.getText() + "%");
                ps.setString(37, "%" + CrDokter.getText() + "%");
                ps.setString(38, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(39, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(40, "%" + TCari.getText().trim() + "%");
                ps.setString(41, "%" + CrPoli.getText() + "%");
                ps.setString(42, "%" + CrDokter.getText() + "%");
                ps.setString(43, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(44, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(45, "%" + TCari.getText().trim() + "%");
                ps.setString(46, "%" + CrPoli.getText() + "%");
                ps.setString(47, "%" + CrDokter.getText() + "%");
                ps.setString(48, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(49, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(50, "%" + TCari.getText().trim() + "%");
                ps.setString(51, "%" + CrPoli.getText() + "%");
                ps.setString(52, "%" + CrDokter.getText() + "%");
                ps.setString(53, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(54, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(55, "%" + TCari.getText().trim() + "%");
                ps.setString(56, "%" + CrPoli.getText() + "%");
                ps.setString(57, "%" + CrDokter.getText() + "%");
                ps.setString(58, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(59, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(60, "%" + TCari.getText().trim() + "%");
                ps.setString(61, "%" + CrPoli.getText() + "%");
                ps.setString(62, "%" + CrDokter.getText() + "%");
                ps.setString(63, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(64, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(65, "%" + TCari.getText().trim() + "%");                
                ps.setString(66, "%" + CrPoli.getText() + "%");
                ps.setString(67, "%" + CrDokter.getText() + "%");
                ps.setString(68, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(69, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(70, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    if (Sequel.cariIsi("select ifnull(user,'-') from kelengkapan_booking_sep_bpjs where no_rawat='" + rs.getString("no_rawat") + "'").equals("-")) {
                        petugasSIPO = "-";
                    } else if (Sequel.cariInteger("select count(*) from kelengkapan_booking_sep_bpjs where no_rawat='" + rs.getString("no_rawat") + "'") > 0) {
                        petugasSIPO = Sequel.cariIsi("select user from kelengkapan_booking_sep_bpjs where no_rawat='" + rs.getString("no_rawat") + "'") + " (SIPO)";
                    }
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_reg"),
                        rs.getString("no_rawat"),
                        rs.getString("tgl_registrasi"),
                        rs.getString("jam_reg"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("umur"),
                        rs.getString("nm_poli"),
                        rs.getString("png_jawab"),
                        rs.getString("nosep"),
                        rs.getString("p_jawab"),
                        rs.getString("almt_pj"),
                        rs.getString("hubunganpj"),
                        Valid.SetAngka(rs.getDouble("biaya_reg")),
                        rs.getString("stts_daftar"),
                        rs.getString("no_tlp"),
                        rs.getString("stts"),
                        rs.getString("nm_petugas").replaceAll("-", petugasSIPO)
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        TNoReg.setText("");
        TNoRw.setText("");
        Kd2.setText("");
        tulisan_tanggal.setText(Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d')") + " "
                + Sequel.bulanINDONESIA("SELECT DATE_FORMAT(NOW(),'%m')") + " "
                + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y')"));
        tglDaftar = Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar");
        tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d') tgl_daftar");
        TNoID.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TPngJwb.setText("");
        THbngn.setText("");
        TAlmt.setText("");
        TStatus.setText("");
        AsalRujukan.setText("");
        alamatperujuk = "";
        rmMati.setText("");
        jamMati.setText("");
        tglMati.setText("");

//ini tambahannya : 
        kdpoli.setText("");
        TPoli.setText("");
        TBiaya.setText("");
        TDokter.setText("");
        kddokter.setText("");
        isNumber();
        TNoID.requestFocus();
        kdpnj.setText("");
        nmpnj.setText("");
        NoPeserta.setText("");
        JnsnoID.setSelectedIndex(0);
        kode_rujukanya.setText("");

        noSrt.setText("");
        noSrt1.setText("");
        TglSurat.setDate(new Date());
        TglSurat1.setDate(new Date());
        sepJkd.setText("");
        sepJmp.setText("");
        DlgJamkesda.setVisible(false);
        DlgJampersal.setVisible(false);
        cekPasien.setText("");
        kdsuku.setText("");
        nmsuku.setText("");
        kdbahasa.setText("");
        nmbahasa.setText("");
        label_pesan.setText("-");
        infoSEP();

        if ((akses.getkode().equals("PP24")) || (akses.getkode().equals("PP23"))) {
            kdpnj.setText("U01");
            nmpnj.setText("UMUM");
        }
    }

    private void getData() {
        tglDaftar = "";

        if (tbregistrasiRalan.getSelectedRow() != -1) {
            TNoReg.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 1).toString());
            Kd2.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            TNoRw.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            norwPerujuk.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            tglDaftar = tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 3).toString();
            Valid.SetTgl(TglReg, tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 3).toString());
            kddokter.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 5).toString());
            TDokter.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 6).toString());
            drPerujuk.setText(TDokter.getText());
            TNoID.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 7).toString());
            TNoRM.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 7).toString());
            isCekPasien();
            TPoli.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 11).toString());
            poliPerujuk.setText(TPoli.getText());
            nmpnj.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 12).toString());
            TPngJwb.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 14).toString());
            TAlmt.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 15).toString());
            THbngn.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 16).toString());
            TBiaya.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 17).toString());
            TStatus.setText(tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 18).toString());
            Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", kdpnj, tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?", kdpoli, tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            Sequel.cariIsi("select perujuk from rujuk_masuk where no_rawat=?", AsalRujukan, tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            Sequel.cariIsi("select kd_rujukan from rujuk_masuk where no_rawat=?", kode_rujukanya, tbregistrasiRalan.getValueAt(tbregistrasiRalan.getSelectedRow(), 2).toString());
            JnsnoID.setSelectedIndex(0);
            Sequel.cariIsi("select suku_bangsa from pasien where no_rkm_medis=?", kdsuku, TNoRM.getText());
            Sequel.cariIsi("select bahasa_pasien from pasien where no_rkm_medis=?", kdbahasa, TNoRM.getText());
            Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
            Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());

            Valid.SetTgl(TglSurat, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan' "));
            Valid.SetTgl(TglSurat1, Sequel.cariIsi("SELECT tgl_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan' "));
            noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
            noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
            sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
            sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));

            if (noSrt.getText().equals("")) {
                tbregistrasiRalan.requestFocus();
            } else {
                noSrt.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
                sepJkd.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jamkesda WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
            }

            if (noSrt1.getText().equals("")) {
                tbregistrasiRalan.requestFocus();
            } else {
                noSrt1.setText(Sequel.cariIsi("SELECT no_surat FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
                sepJmp.setText(Sequel.cariIsi("SELECT no_sep FROM bridging_jampersal WHERE no_rawat='" + TNoRw.getText() + "' AND jns_rawat='Jalan'"));
            }
            
            Tumur.setText(Sequel.cariIsi("select umurdaftar from reg_periksa where no_rawat='" + Kd2.getText() + "'"));
            cmbSttsUmur.setSelectedItem(Sequel.cariIsi("select sttsumur from reg_periksa where no_rawat='" + Kd2.getText() + "'"));
            Valid.SetTgl(Ttgl_lahir, Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        }
    }

    private void isPas() {
        if (validasiregistrasi.equals("Yes")) {
            if (Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where no_rkm_medis=? and (stts='Sudah' or stts='Belum') ", TNoRM.getText()) > 0) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, pasien pada kunjungan sebelumnya memiliki tagihan yang belum di closing. Silahkan konfirmasi dengan pihak kasir.. !!");
            } else {
                if (Sequel.cariInteger("select count(no_rkm_medis) from catatan_pasien where no_rkm_medis=?", TNoRM.getText()) > 0) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgCatatan catatan = new DlgCatatan(null, true);
                    catatan.setNoRm(TNoRM.getText());
                    catatan.setSize(720, 330);
                    catatan.setLocationRelativeTo(internalFrame1);
                    catatan.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
                isCekPasien();
            }
        } else {
            isCekPasien();
        }
    }

    private void isCekPasien() {
        try {
            if (JnsnoID.getSelectedItem().equals("No. Rekam Medik")) {
                ps3 = koneksi.prepareStatement("select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                        + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                        + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                        + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + "pasien.no_peserta, pasien.no_rkm_medis, pasien.suku_bangsa, pasien.bahasa_pasien from pasien "
                        + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                        + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                        + "where pasien.no_rkm_medis=?");

            } else if (JnsnoID.getSelectedItem().equals("No. Kartu BPJS")) {
                ps3 = koneksi.prepareStatement("select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                        + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                        + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                        + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + "pasien.no_peserta, pasien.no_rkm_medis, pasien.suku_bangsa, pasien.bahasa_pasien from pasien "
                        + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                        + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                        + "where pasien.kd_pj='B01' and pasien.no_peserta=?");

            } else if (JnsnoID.getSelectedItem().equals("NIK KTP")) {
                ps3 = koneksi.prepareStatement("select nm_pasien,concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) asal,"
                        + "namakeluarga,keluarga,pasien.kd_pj,penjab.png_jawab,if(tgl_daftar=?,'Baru','Lama') as daftar, "
                        + "TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) as tahun, "
                        + "(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12)) as bulan, "
                        + "TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()) as hari, "
                        + "pasien.no_peserta, pasien.no_rkm_medis, pasien.suku_bangsa, pasien.bahasa_pasien from pasien "
                        + "inner join kelurahan inner join kecamatan inner join kabupaten inner join penjab "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_pj=penjab.kd_pj "
                        + "and pasien.kd_kec=kecamatan.kd_kec and pasien.kd_kab=kabupaten.kd_kab "
                        + "where pasien.no_ktp=?");
            }

            try {
                ps3.setString(1, tglDaftar);
                ps3.setString(2, TNoID.getText());

                rs = ps3.executeQuery();
                while (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    TAlmt.setText(rs.getString("asal"));
                    TPngJwb.setText(rs.getString("namakeluarga"));
                    THbngn.setText(rs.getString("keluarga"));
                    kdpnj.setText(rs.getString("kd_pj"));
                    nmpnj.setText(rs.getString("png_jawab"));
                    TStatus.setText(rs.getString("daftar"));
                    NoPeserta.setText(rs.getString("no_peserta"));
                    kdsuku.setText(rs.getString("suku_bangsa"));
                    kdbahasa.setText(rs.getString("bahasa_pasien"));
                    Sequel.cariIsi("select nama_suku_bangsa from suku_bangsa where id=?", nmsuku, kdsuku.getText());
                    Sequel.cariIsi("select nama_bahasa from bahasa_pasien where id=?", nmbahasa, kdbahasa.getText());

                    umur = "0";
                    sttsumur = "Th";
                    cekUmur = 0;
                    sttsumur1 = "";
                    if (rs.getInt("tahun") > 0) {
                        cekUmur = rs.getInt("tahun");
                        sttsumur1 = "Tahun.";
                        umur = rs.getString("tahun");
                        sttsumur = "Th";
                    } else if (rs.getInt("tahun") == 0) {
                        if (rs.getInt("bulan") > 0) {
                            cekUmur = rs.getInt("bulan");
                            sttsumur1 = "Bulan.";
                            umur = rs.getString("bulan");
                            sttsumur = "Bl";
                        } else if (rs.getInt("bulan") == 0) {
                            cekUmur = rs.getInt("hari");
                            sttsumur1 = "Hari.";
                            umur = rs.getString("hari");
                            sttsumur = "Hr";
                        }
                    }
                    switch (rs.getString("daftar")) {
                        case "Baru":
                            Sequel.cariIsi("select registrasi from poliklinik where kd_poli=?", TBiaya, kdpoli.getText());
                            break;
                        case "Lama":
                            Sequel.cariIsi("select registrasilama from poliklinik where kd_poli=?", TBiaya, kdpoli.getText());
                            break;
                        default:
                            Sequel.cariIsi("select registrasi from poliklinik where kd_poli=?", TBiaya, kdpoli.getText());
                            break;
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JTextField getTextField() {
        return TNoRw;
    }

    public JButton getButton() {
        return BtnKeluar;
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 188));
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
        tulisan_tanggal.setText(Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%d')") + " "
                + Sequel.bulanINDONESIA("SELECT DATE_FORMAT(NOW(),'%m')") + " "
                + Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y')"));
        tglDaftar = Sequel.cariIsi("SELECT DATE(NOW()) tgl_daftar");
        tglnoRW = Sequel.cariIsi("SELECT DATE_FORMAT(NOW(),'%Y/%m/%d') tgl_daftar");
        DTPCari1.setDate(new Date());
        DTPCari2.setDate(new Date());
        BtnSimpan.setEnabled(akses.getregistrasi());
        BtnHapus.setEnabled(akses.getregistrasi());
        BtnEdit.setEnabled(akses.getregistrasi());
        BtnPrint.setEnabled(akses.getregistrasi());
        MnKamarInap.setEnabled(akses.getkamar_inap());
        MnBilling.setEnabled(akses.getbilling_ralan());
        MnPeriksaLab.setEnabled(akses.getperiksa_lab());
        MnPeriksaRadiologi.setEnabled(akses.getperiksa_radiologi());
        MnRujuk.setEnabled(akses.getrujukan_keluar());
        MnRujukMasuk.setEnabled(akses.getrujukan_masuk());
        MnSEP.setEnabled(akses.getbpjs_sep());
        ppSuratKontrol.setEnabled(akses.getRencanaKontrolJKN());
        ppCekFingerPrin.setEnabled(akses.getbpjs_sep());
        MnSJP.setEnabled(akses.getinhealth_sjp());
        ppRiwayat.setEnabled(akses.getresume_pasien());
        ppCatatanPasien.setEnabled(akses.getcatatan_pasien());
        MnStatus.setEnabled(akses.getsetstatusralan());
        ppPasienCorona.setEnabled(akses.getpasien_corona());

        if (akses.getkode().equals("Admin Utama")) {
            TabRawat.setEnabled(true);
            TabRawat.setEnabledAt(1, true);
            TabRawat.setEnabledAt(3, true);
        } else {
            TabRawat.setEnabledAt(1, akses.getOperator_antrian());
            TabRawat.setEnabledAt(3, false);
        }
    }

    private void isNumber() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kdpoli.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and kd_poli='" + kdpoli.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kddokter.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
        }

        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(rujuk_masuk.no_rawat,4),signed)),0) from reg_periksa inner join rujuk_masuk on reg_periksa.no_rawat=rujuk_masuk.no_rawat where reg_periksa.tgl_registrasi='" + tglDaftar + "' ", "BR/" + tglnoRW + "/", 4, NoBalasan);
        if (Kd2.getText().equals("")) {
            Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + tglDaftar + "' ", tglnoRW + "/", 6, TNoRw);
        }
    }

    private void isNumberRujuk() {
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_poli='" + kdpoliRujuk.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            case "dokter & poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and kd_poli='" + kdpoliRujuk.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from reg_periksa where kd_dokter='" + kdDokterRujuk.getText() + "' and tgl_registrasi='" + tglDaftar + "'", "", 3, TNoReg);
                break;
        }
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + tglDaftar + "' ", tglnoRW + "/", 6, TNoRw);
    }

    public void ctk() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Runtime rt = Runtime.getRuntime();
            FileWriter writer = null;
            if (os.contains("win")) {
                writer = new FileWriter("//" + IPPRINTERTRACER);
            } else if (os.contains("mac")) {
                writer = new FileWriter("smb://" + IPPRINTERTRACER);
            } else if (os.contains("nix") || os.contains("nux")) {
                writer = new FileWriter("smb://" + IPPRINTERTRACER);
            }
            writer.write(".: TRACER :.");
            cetakStruk("Draft Sans Serif Condensed", writer,
                    MODE_DRAFT,
                    FONT_SANS_SERIF,
                    CONDENSED_ON,
                    SIZE_12_CPI,
                    SPACING_12_72);
            sendCommand(RESET, writer);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Notif Writer 3 : " + ex);
        }
    }

    private void cetakStruk(String title, FileWriter writer, char[]... mode) throws IOException {
        sendCommand(RESET, writer);
        for (int i = 0; i < mode.length; i++) {
            char[] cmd = mode[i];
            sendCommand(cmd, writer);
        }

        cetakStruk2(title, writer);
        sendCommand(VERTICAL_PRINT_POSITION, writer);
    }

    public void sendCommand(char[] command, Writer writer) throws IOException {
        writer.write(command);
    }

    private void cetakStruk2(String title, FileWriter writer) {
        String strukFile = "tracerRm.txt";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(strukFile));
            String tgll = Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            String[] tglref = tgll.split("-");
            boltText(writer);
            writer.write(".: TRACER :.");
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("No. RM      : ");
            boltText(writer);
            writer.write(TNoID.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Nama Pasien : ");
            boltText(writer);
            writer.write(TPasien.getText());
            boltTextOff(writer);
            gantiBaris(writer);
            writer.write("Tgl. Daftar : ");
            boltText(writer);
            writer.write(tglref[2] + "-" + tglref[1] + "-" + tglref[0] + "/" + Sequel.cariIsi("SELECT TIME(NOW()) jam"));
            gantiBaris(writer);
            boltTextOff(writer);
            writer.write("Ruangan     : ");
            boltText(writer);
            writer.write(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdpoli.getText() + "'"));
            boltTextOff(writer);

            gantiBaris(writer);
            gantiBaris(writer);
            gantiBaris(writer);
            reader.close();
        } catch (Exception ex) {
            System.out.println("Notif : " + ex);
        }
    }

    private void boltText(Writer writer) {
        try {
            writer.write(ESC);
            writer.write((char) 14);
            writer.write(ESC);
            writer.write('E');
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void boltTextOff(Writer writer) {
        try {
            writer.write(ESC);
            writer.write('F');
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void gantiBaris(Writer writer) {
        try {
            writer.write(" ");
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }

    private void UpdateUmur() {
        Sequel.mengedit("pasien", "no_rkm_medis=?", "umur=CONCAT(CONCAT(CONCAT(TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()), ' Th '),CONCAT(TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12), ' Bl ')),CONCAT(TIMESTAMPDIFF(DAY, DATE_ADD(DATE_ADD(tgl_lahir,INTERVAL TIMESTAMPDIFF(YEAR, tgl_lahir, CURDATE()) YEAR), INTERVAL TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) - ((TIMESTAMPDIFF(MONTH, tgl_lahir, CURDATE()) div 12) * 12) MONTH), CURDATE()), ' Hr'))", 1, new String[]{TNoID.getText()});
    }

    public void SetPasien(String norm, String nosisrute, String FaskesAsal) {
        ChkInput.setSelected(true);
        isForm();
        TNoRM.setText(norm);
        this.nosisrute = nosisrute;
        AsalRujukan.setText(FaskesAsal);
        isPas();
    }

    public void setPasien(String NamaPasien, String Kontak, String Alamat, String TempatLahir, String TglLahir,
            String JK, String NoKartuJKN, String NIK, String nosisrute, String FaskesAsal) {
        akses.setform("DlgReg");
        ChkInput.setSelected(true);
        isForm();
        pasien.emptTeks();
        pasien.isCek();
        this.nosisrute = nosisrute;
        AsalRujukan.setText(FaskesAsal);
        pasien.setPasien(NamaPasien, Kontak, Alamat, TempatLahir, TglLahir, JK, NoKartuJKN, NIK);
        pasien.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(true);
    }

    private void infoSEP() {
        label_pesan.setText(Sequel.cariIsi("SELECT DATE_FORMAT(b.tanggal_periksa,'%d %M %Y') tgl FROM kelengkapan_booking_sep_bpjs k "
                + "INNER JOIN booking_registrasi b on b.kd_booking=k.kd_booking WHERE status_cetak_sep='gagal' ORDER BY b.tanggal_periksa limit 1"));

        if (label_pesan.getText().equals("")) {
            label_pesan.setText("-");
        } else if (!label_pesan.getText().equals("")) {
            label_pesan.setText("Pada tgl. periksa pasien " + Sequel.cariIsi("SELECT DATE_FORMAT(b.tanggal_periksa,'%d %M %Y') tgl FROM kelengkapan_booking_sep_bpjs k "
                    + "INNER JOIN booking_registrasi b on b.kd_booking=k.kd_booking WHERE status_cetak_sep='gagal' ORDER BY b.tanggal_periksa limit 1") + " ditemukan SEP "
                    + "manual tercetak dari anjungan elektronik pasien, cek lagi dimenu booking registrasi & mohon dibikinkan SEP nya lagi..!!");
        }
    }

    private void cekLoket() {
        if (akses.getJenisLoket().equals("Rawat Jalan")) {
            loketRalan.setSelected(true);
            loketRanap.setSelected(false);
        } else if (akses.getJenisLoket().equals("Rawat Inap")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(true);
        } else {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
        }

        if (akses.getNomorLoket().equals("1")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(true);
            loket2.setSelected(false);
            loket3.setSelected(false);
            loket4.setSelected(false);
            loket5.setSelected(false);
            loket6.setSelected(false);
        } else if (akses.getNomorLoket().equals("2")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(true);
            loket3.setSelected(false);
            loket4.setSelected(false);
            loket5.setSelected(false);
            loket6.setSelected(false);
        } else if (akses.getNomorLoket().equals("3")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(false);
            loket3.setSelected(true);
            loket4.setSelected(false);
            loket5.setSelected(false);
            loket6.setSelected(false);
        } else if (akses.getNomorLoket().equals("4")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(false);
            loket3.setSelected(false);
            loket4.setSelected(true);
            loket5.setSelected(false);
            loket6.setSelected(false);
        } else if (akses.getNomorLoket().equals("5")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(false);
            loket3.setSelected(false);
            loket4.setSelected(false);
            loket5.setSelected(true);
            loket6.setSelected(false);
        } else if (akses.getNomorLoket().equals("6")) {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(false);
            loket3.setSelected(false);
            loket4.setSelected(false);
            loket5.setSelected(false);
            loket6.setSelected(true);
        } else {
            loketRalan.setSelected(false);
            loketRanap.setSelected(false);
            loket1.setSelected(false);
            loket2.setSelected(false);
            loket3.setSelected(false);
            loket4.setSelected(false);
            loket5.setSelected(false);
            loket6.setSelected(false);
        }
    }

    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";

                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                nilai_jam = now.getHours();
                nilai_menit = now.getMinutes();
                nilai_detik = now.getSeconds();

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
                
                //bisa dimasukkan skrip apa aja
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void tampilHistoryReset() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select jenis_reset, nomor_terakhir, date_format(tgl_reset,'%Y-%m-%d') tgl, "
                    + "date_format(tgl_reset,'%H:%i:%s') jam, date_format(tgl_reset,'%d %b %Y, Pukul : %H:%i:%s') tgl_reset from antrian_history_reset where "
                    + "date(tgl_reset) between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                    + "and jenis_reset not like '%resep%' order by tgl_reset desc");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("jenis_reset"),
                        rs2.getString("nomor_terakhir"),
                        rs2.getString("tgl"),
                        rs2.getString("jam"),
                        rs2.getString("tgl_reset")
                    });
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
    }

    private void getDataAntrian() {
        if (tbrestorAntrian.getSelectedRow() != -1) {
            jnsReset.setText(tbrestorAntrian.getValueAt(tbrestorAntrian.getSelectedRow(), 0).toString());
            noTerakhir.setText(tbrestorAntrian.getValueAt(tbrestorAntrian.getSelectedRow(), 1).toString());
            tglReset.setText(tbrestorAntrian.getValueAt(tbrestorAntrian.getSelectedRow(), 4).toString());
        }
    }

    private void emptTeksRestor() {
        jnsReset.setText("");
        noTerakhir.setText("");
        tglReset.setText("");
    }

    private void cekAntrianBPJS() {
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
            infobpjs1.setText("Hari ini masih belum ada antrian pasien rawat jalan BPJS.");
            infobpjs2.setText("Hari ini masih belum ada pasien.");
        } else {
            infobpjs1.setText("Antrian terakhir pasien adalah nomor : " + nomorpasien + "");

            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infobpjs2.setText("Ada antrian pasien rawat jalan BPJS yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infobpjs2.setText("Masih ada : " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan BPJS.");
            }

            if (hasilantrian < 1) {
                infobpjs2.setText("Antrian pasien rawat jalan BPJS sudah habis.");
            }

            if (panggilan > nomorpasien) {
                infobpjs2.setText("Ada antrian pasien rawat jalan BPJS yang terlewati dari panggilan operator.");
            }
        }
    }

    private void cekAntrianUMUM() {
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
            infoumum1.setText("Hari ini masih belum ada antrian pasien rawat jalan Umum (NON BPJS).");
            infoumum2.setText("Hari ini masih belum ada pasien.");
        } else {
            infoumum1.setText("Antrian terakhir pasien adalah nomor : PU-" + nomorpasien + "");

            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infoumum2.setText("Ada antrian pasien rawat jalan Umum (NON BPJS) yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infoumum2.setText("Masih ada : " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan Umum (NON BPJS).");
            }

            if (hasilantrian < 1) {
                infoumum2.setText("Antrian pasien rawat jalan Umum (NON BPJS) sudah habis.");
            }

            if (panggilan > nomorpasien) {
                infoumum2.setText("Ada antrian pasien rawat jalan Umum (NON BPJS) yang terlewati dari panggilan operator.");
            }
        }
    }

    private void cekAntrianLB() {
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
            infolb1.setText("Hari ini masih belum ada antrian pasien rawat jalan LANSIA / BAYI.");
            infolb2.setText("Hari ini masih belum ada pasien.");
        } else {
            infolb1.setText("Antrian terakhir pasien adalah nomor : LB-" + nomorpasien + "");

            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                infolb2.setText("Ada antrian pasien rawat jalan LANSIA / BAYI yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                infolb2.setText("Masih ada : " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat jalan LANSIA / BAYI.");
            }

            if (hasilantrian < 1) {
                infolb2.setText("Antrian pasien rawat jalan LANSIA / BAYI sudah habis.");
            }

            if (panggilan > nomorpasien) {
                infolb2.setText("Ada antrian pasien rawat jalan LANSIA / BAYI yang terlewati dari panggilan operator.");
            }
        }
    }

    private void cekAntrianRanap() {
        panggilan = 0;
        nomorpasien = 0;
        hasilantrian = 0;

        //cek nomor antrian pasien
        if (Sequel.cariIsi("select no_antrian from antrian_nomor_inap where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1").equals("")) {
            nomorpasien = 0;
        } else {
            nomorpasien = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_nomor_inap where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1"));
        }

        //cek nomor panggilan pasien
        if (Sequel.cariIsi("SELECT no_antrian FROM antrian_pemanggil_inap where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1").equals("")) {
            panggilan = 0;
        } else {
            panggilan = Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1"));
        }

        if (panggilan == 0 && nomorpasien == 0) {
            inforanap1.setText("Hari ini masih belum ada antrian pasien rawat inap.");
            inforanap2.setText("Hari ini masih belum ada pasien.");
        } else {
            inforanap1.setText("Antrian terakhir pasien adalah nomor : " + nomorpasien + "");

            //hasil hitungan antrian
            hasilantrian = nomorpasien - panggilan;
            if (nomorpasien > panggilan) {
                inforanap2.setText("Ada antrian pasien rawat inap yang belum dipanggil & dilayani oleh petugas pendaftaran.");
            }

            if (hasilantrian >= 1) {
                inforanap2.setText("Masih ada : " + hasilantrian + " orang lagi yang belum dipanggil & dilayani oleh petugas pendaftaran pasien rawat inap.");
            }

            if (hasilantrian < 1) {
                inforanap2.setText("Antrian pasien rawat inap sudah habis.");
            }

            if (panggilan > nomorpasien) {
                inforanap2.setText("Ada antrian pasien rawat inap yang terlewati dari panggilan operator.");
            }
        }
    }

    private void tambahPangBPJS() {
        wktPanggil = "";
        wktAmbilNomor = "";
        panggilanFix = "";
        noPangAkhir = "";
        wktPanggil = Sequel.cariIsi("select now()");

        if (akses.getNomorLoket().equals("1")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','1','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("2")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','2','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("3")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','3','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("4")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','4','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("5")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','5','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("6")) {
            Sequel.menyimpan("antrian_pemanggil_bpjs", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','6','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan BPJS");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_bpjs where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        }
    }

    private void tambahPangUmum() {
        wktPanggil = "";
        wktAmbilNomor = "";
        panggilanFix = "";
        noPangAkhir = "";
        wktPanggil = Sequel.cariIsi("select now()");

        if (akses.getNomorLoket().equals("1")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','1','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("2")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','2','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("3")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','3','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("4")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','4','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("5")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','5','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("6")) {
            Sequel.menyimpan("antrian_pemanggil_umum", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','6','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Umum");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_umum where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "PU-" + noPangAkhir;
        }
    }

    private void tambahPangLB() {
        wktPanggil = "";
        wktAmbilNomor = "";
        panggilanFix = "";
        noPangAkhir = "";
        wktPanggil = Sequel.cariIsi("select now()");

        if (akses.getNomorLoket().equals("1")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','1','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("2")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','2','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("3")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','3','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("4")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','4','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("5")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','5','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        } else if (akses.getNomorLoket().equals("6")) {
            Sequel.menyimpan("antrian_pemanggil_lansia", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','6','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Lansia/Bayi");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_lansia where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = "LB-" + noPangAkhir;
        }
    }

    private void tambahPangInap() {
        wktPanggil = "";
        wktAmbilNomor = "";
        panggilanFix = "";
        noPangAkhir = "";
        wktPanggil = Sequel.cariIsi("select now()");

        if (akses.getNomorLoket().equals("1")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','1','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("2")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','2','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("3")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','3','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("4")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','4','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("5")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','5','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        } else if (akses.getNomorLoket().equals("6")) {
            Sequel.menyimpan("antrian_pemanggil_inap", "'" + Sequel.cariIsi("select ifnull(MAX(no_antrian)+1,1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") + "',"
                    + "'proses','6','" + Sequel.cariIsi("select now()") + "'", "Nomor Panggilan Rawat Inap");

            noPangAkhir = Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1");
            wktAmbilNomor = Sequel.cariIsi("select waktu_cetak from antrian_nomor_inap where no_antrian='" + noPangAkhir + "' and date(waktu_cetak)=date(now())");
            panggilanFix = noPangAkhir;
        }
    }

    private void persenTombolPanggilan() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //proses bar diatur 30%. nilai i < 31 hasilnya 30%
                for (int i = 0; i < 31; i++) {
                    try {
                        prosesTombol.setValue(i);
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(DlgReg.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (prosesTombol.getString().equals("30%")) {
                    BtnPangBPJS.setEnabled(true);
                    BtnUlangPangBPJS.setEnabled(true);
                    BtnPangUmum.setEnabled(true);
                    BtnUlangPangUmum.setEnabled(true);
                    BtnPangLansia.setEnabled(true);
                    BtnUlangPangLansia.setEnabled(true);
                    BtnPangInap.setEnabled(true);
                    BtnUlangPangInap.setEnabled(true);
                }
            }
        });

        t.start();
    }

    private void simpanIKM() {
        if (akses.getOperator_antrian() == true && !wktAmbilNomor.equals("") && !wktPanggil.equals("") && !akses.getJenisLoket().equals("Rawat Inap")) {
            Sequel.menyimpan("antrian_history", "'" + panggilanFix + "','" + wktAmbilNomor + "',"
                    + "'" + wktPanggil + "','" + Sequel.cariIsi("select now()") + "','" + TNoRw.getText() + "'", "Simpan data IKM");

            wktPanggil = "";
            wktAmbilNomor = "";
            panggilanFix = "";
        }
    }

    private void panggilAntrian(int antrian) {
        if (antrian < 12) {
            try {
                music = new BackgroundMusic(urut[antrian]);
                music.start();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } else if (antrian < 20) {
            try {
                music = new BackgroundMusic(urut[antrian - 10]);
                music.start();
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            try {
                music = new BackgroundMusic("./suara/belas.mp3");
                music.start();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } else if (antrian < 100) {
            try {
                music = new BackgroundMusic(urut[antrian / 10]);
                music.start();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            try {
                music = new BackgroundMusic("./suara/puluh.mp3");
                music.start();
                Thread.sleep(600);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            panggilAntrian(antrian % 10);
        } else if (antrian < 200) {
            try {
                music = new BackgroundMusic("./suara/seratus.mp3");
                music.start();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            panggilAntrian(antrian - 100);
        } else if (antrian < 1000) {
            panggilAntrian(antrian / 100);

            try {
                music = new BackgroundMusic("./suara/ratus.mp3");
                music.start();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }

            panggilAntrian(antrian % 100);
        }
    }

    private void suaraPanggilanBPJS() {
        try {
            music = new BackgroundMusic("./suara/intro_rawat_jalan_bpj_s.mp3");
            music.start();

            BtnPangBPJS.setEnabled(false);
            BtnUlangPangBPJS.setEnabled(false);
            BtnPangUmum.setEnabled(false);
            BtnUlangPangUmum.setEnabled(false);
            BtnPangLansia.setEnabled(false);
            BtnUlangPangLansia.setEnabled(false);
            BtnPangInap.setEnabled(false);
            BtnUlangPangInap.setEnabled(false);

            Thread.sleep(6000);
            panggilAntrian(Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1")));
            music = new BackgroundMusic("./suara/diloket.mp3");
            music.start();
            Thread.sleep(1000);
            panggilAntrian(Integer.parseInt(akses.getNomorLoket()));

            persenTombolPanggilan();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void suaraPanggilanUMUM() {
        try {
            music = new BackgroundMusic("./suara/intro_rawat_jalan_umum_p_u.mp3");
            music.start();

            BtnPangBPJS.setEnabled(false);
            BtnUlangPangBPJS.setEnabled(false);
            BtnPangUmum.setEnabled(false);
            BtnUlangPangUmum.setEnabled(false);
            BtnPangLansia.setEnabled(false);
            BtnUlangPangLansia.setEnabled(false);
            BtnPangInap.setEnabled(false);
            BtnUlangPangInap.setEnabled(false);

            Thread.sleep(6300);
            panggilAntrian(Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1")));
            music = new BackgroundMusic("./suara/diloket.mp3");
            music.start();
            Thread.sleep(1000);
            panggilAntrian(Integer.parseInt(akses.getNomorLoket()));

            persenTombolPanggilan();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void suaraPanggilanLB() {
        try {
            music = new BackgroundMusic("./suara/intro_rawat_jalan_lansia_atau_bayi_baru_lahir_elbe.mp3");
            music.start();

            BtnPangBPJS.setEnabled(false);
            BtnUlangPangBPJS.setEnabled(false);
            BtnPangUmum.setEnabled(false);
            BtnUlangPangUmum.setEnabled(false);
            BtnPangLansia.setEnabled(false);
            BtnUlangPangLansia.setEnabled(false);
            BtnPangInap.setEnabled(false);
            BtnUlangPangInap.setEnabled(false);

            Thread.sleep(6800);
            panggilAntrian(Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1")));
            music = new BackgroundMusic("./suara/diloket.mp3");
            music.start();
            Thread.sleep(1000);
            panggilAntrian(Integer.parseInt(akses.getNomorLoket()));

            persenTombolPanggilan();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void suaraPanggilanINAP() {
        try {
            music = new BackgroundMusic("./suara/intro_rawat_inap.mp3");
            music.start();

            BtnPangBPJS.setEnabled(false);
            BtnUlangPangBPJS.setEnabled(false);
            BtnPangUmum.setEnabled(false);
            BtnUlangPangUmum.setEnabled(false);
            BtnPangLansia.setEnabled(false);
            BtnUlangPangLansia.setEnabled(false);
            BtnPangInap.setEnabled(false);
            BtnUlangPangInap.setEnabled(false);

            Thread.sleep(4600);
            panggilAntrian(Integer.parseInt(Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1")));
            music = new BackgroundMusic("./suara/diloket.mp3");
            music.start();
            Thread.sleep(1000);
            panggilAntrian(Integer.parseInt(akses.getNomorLoket()));

            persenTombolPanggilan();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void cekRefreshAntri() {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nobpjs.setText(Sequel.cariIsi("SELECT ifnull(no_antrian,'-') FROM antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1"));
                nolansia.setText(Sequel.cariIsi("SELECT ifnull(concat('LB-',no_antrian),'-') FROM antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1"));
                noumum.setText(Sequel.cariIsi("SELECT ifnull(concat('PU-',no_antrian),'-') FROM antrian_pemanggil_umum where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1"));
                noinap.setText(Sequel.cariIsi("SELECT ifnull(no_antrian,'-') FROM antrian_pemanggil_inap where date(waktu_panggil)=date(now()) ORDER BY no_antrian DESC LIMIT 1"));
                cekAntrianBPJS();
                cekAntrianUMUM();
                cekAntrianLB();
                cekAntrianRanap();
            }
        };
        // Timer
        //interval 1000 ms = 1 detik
        //interval 30000 ms = 30 detik atau setngah menit
        tRefreshAntri = new Timer(1000, taskPerformer);        
    }
    
    private void resetPanggilanAntrian() {
        //panggilan bpjs
        if (Sequel.cariInteger("select count(-1) from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Panggilan Antrian BPJS',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_pemanggil_bpjs where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Panggilan BPJS");   
            Sequel.queryu("delete from antrian_pemanggil_bpjs where date(waktu_panggil) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }

        //panggilan lansia
        if (Sequel.cariInteger("select count(-1) from antrian_pemanggil_lansia where date(waktu_panggil)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Panggilan Antrian LANSIA-BAYI',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_pemanggil_lansia where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Panggilan LANSIA-BAYI");
            Sequel.queryu("delete from antrian_pemanggil_lansia where date(waktu_panggil) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }

        //panggilan umum
        if (Sequel.cariInteger("select count(-1) from antrian_pemanggil_umum where date(waktu_panggil)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Panggilan Antrian Umum',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_pemanggil_umum where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Panggilan Umum");
            Sequel.queryu("delete from antrian_pemanggil_umum where date(waktu_panggil) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }

        //panggilan inap
        if (Sequel.cariInteger("select count(-1) from antrian_pemanggil_inap where date(waktu_panggil)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Panggilan Antrian Ranap',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_pemanggil_inap where date(waktu_panggil)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Panggilan Ranap");
            Sequel.queryu("delete from antrian_pemanggil_inap where date(waktu_panggil) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }
    }
    
    private void resetNomorAntrian() {
        //nomor umum
        if (Sequel.cariInteger("select count(-1) from antrian_nomor_umum where date(waktu_cetak)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Nomor Antrian Umum',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_nomor_umum where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Pasien Umum");
            Sequel.queryu("delete from antrian_nomor_umum where date(waktu_cetak) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }

        //nomor lansia
        if (Sequel.cariInteger("select count(-1) from antrian_nomor_lansia where date(waktu_cetak)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Nomor Antrian LANSIA-BAYI',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_nomor_lansia where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Pasien LANSIA-BAYI");
            Sequel.queryu("delete from antrian_nomor_lansia where date(waktu_cetak) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }

        //nomor bpjs
        if (Sequel.cariInteger("select count(-1) from antrian_nomor_bpjs where date(waktu_cetak)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Nomor Antrian BPJS',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_nomor_bpjs where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Pasien BPJS");
            Sequel.queryu("delete from antrian_nomor_bpjs where date(waktu_cetak) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }
        
        //nomor inap
        if (Sequel.cariInteger("select count(-1) from antrian_nomor_inap where date(waktu_cetak)=date(now())") > 0) {
            Sequel.menyimpan("antrian_history_reset", "'Nomor Antrian Ranap',"
                    + "'" + Sequel.cariIsi("select no_antrian from antrian_nomor_inap where date(waktu_cetak)=date(now()) order by no_antrian desc limit 1") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "Nomor Antrian Pasien Ranap");
            Sequel.queryu("delete from antrian_nomor_inap where date(waktu_cetak) < DATE_FORMAT(date_sub(now(), interval 2 day),'%Y-%m-%d')");
        }
    }
    
    private void NoRWTbaru(String tgl) {
        noRwNew = "";
        noRwNew = Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_rawat,6),signed)),0) from reg_periksa where tgl_registrasi='" + tgl + "' ", tgl + "/", 6);
    }
    
    public void tampilAwal() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,"
                    + "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,pasien.jk,concat(reg_periksa.umurdaftar,' ',reg_periksa.sttsumur)as umur,poliklinik.nm_poli,"
                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,pasien.no_tlp,reg_periksa.stts,"
                    + "IFNULL(bridging_sep.no_sep,'-') nosep, if(reg_periksa.nip_petugas='Admin Utama','Admin Utama',ifnull(reg_periksa.nip_petugas,'-')) nm_petugas "
                    + "from reg_periksa inner join dokter inner join pasien inner join poliklinik inner join penjab on reg_periksa.kd_dokter=dokter.kd_dokter and "
                    + "reg_periksa.no_rkm_medis=pasien.no_rkm_medis and reg_periksa.kd_pj=penjab.kd_pj and reg_periksa.kd_poli=poliklinik.kd_poli "
                    + "LEFT JOIN bridging_sep ON bridging_sep.no_rawat = reg_periksa.no_rawat where "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_reg like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_rawat like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.tgl_registrasi like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.kd_dokter like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and dokter.nm_dokter like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.no_rkm_medis like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.stts_daftar like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and pasien.nm_pasien like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and poliklinik.nm_poli like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.p_jawab like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.almt_pj like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and reg_periksa.hubunganpj like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and penjab.png_jawab like ? or "
                    + " poliklinik.kd_poli<>'IGDK' and poliklinik.nm_poli like ? and dokter.nm_dokter like ? and tgl_registrasi between ? and ? and "
                    + "if(reg_periksa.nip_petugas='Admin Utama','Admin Utama',ifnull(reg_periksa.nip_petugas,'-')) like ? order by reg_periksa.tgl_registrasi,reg_periksa.jam_reg desc");
            try {
                ps.setString(1, "%" + CrPoli.getText() + "%");
                ps.setString(2, "%" + CrDokter.getText() + "%");
                ps.setString(3, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(4, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(5, "%" + TCari.getText().trim() + "%");
                ps.setString(6, "%" + CrPoli.getText() + "%");
                ps.setString(7, "%" + CrDokter.getText() + "%");
                ps.setString(8, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(9, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(10, "%" + TCari.getText().trim() + "%");
                ps.setString(11, "%" + CrPoli.getText() + "%");
                ps.setString(12, "%" + CrDokter.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, "%" + CrPoli.getText() + "%");
                ps.setString(17, "%" + CrDokter.getText() + "%");
                ps.setString(18, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(19, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, "%" + CrPoli.getText() + "%");
                ps.setString(22, "%" + CrDokter.getText() + "%");
                ps.setString(23, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(24, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(25, "%" + TCari.getText().trim() + "%");
                ps.setString(26, "%" + CrPoli.getText() + "%");
                ps.setString(27, "%" + CrDokter.getText() + "%");
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, "%" + CrPoli.getText() + "%");
                ps.setString(32, "%" + CrDokter.getText() + "%");
                ps.setString(33, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(34, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(35, "%" + TCari.getText().trim() + "%");
                ps.setString(36, "%" + CrPoli.getText() + "%");
                ps.setString(37, "%" + CrDokter.getText() + "%");
                ps.setString(38, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(39, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(40, "%" + TCari.getText().trim() + "%");
                ps.setString(41, "%" + CrPoli.getText() + "%");
                ps.setString(42, "%" + CrDokter.getText() + "%");
                ps.setString(43, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(44, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(45, "%" + TCari.getText().trim() + "%");
                ps.setString(46, "%" + CrPoli.getText() + "%");
                ps.setString(47, "%" + CrDokter.getText() + "%");
                ps.setString(48, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(49, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(50, "%" + TCari.getText().trim() + "%");
                ps.setString(51, "%" + CrPoli.getText() + "%");
                ps.setString(52, "%" + CrDokter.getText() + "%");
                ps.setString(53, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(54, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(55, "%" + TCari.getText().trim() + "%");
                ps.setString(56, "%" + CrPoli.getText() + "%");
                ps.setString(57, "%" + CrDokter.getText() + "%");
                ps.setString(58, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(59, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(60, "%" + TCari.getText().trim() + "%");
                ps.setString(61, "%" + CrPoli.getText() + "%");
                ps.setString(62, "%" + CrDokter.getText() + "%");
                ps.setString(63, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(64, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(65, "%" + TCari.getText().trim() + "%");
                ps.setString(66, "%" + CrPoli.getText() + "%");
                ps.setString(67, "%" + CrDokter.getText() + "%");
                ps.setString(68, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(69, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(70, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false,
                        rs.getString("no_reg"),
                        rs.getString("no_rawat"),
                        rs.getString("tgl_registrasi"),
                        rs.getString("jam_reg"),
                        rs.getString("kd_dokter"),
                        rs.getString("nm_dokter"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("umur"),
                        rs.getString("nm_poli"),
                        rs.getString("png_jawab"),
                        rs.getString("nosep"),
                        rs.getString("p_jawab"),
                        rs.getString("almt_pj"),
                        rs.getString("hubunganpj"),
                        Valid.SetAngka(rs.getDouble("biaya_reg")),
                        rs.getString("stts_daftar"),
                        rs.getString("no_tlp"),
                        rs.getString("stts"),
                        rs.getString("nm_petugas").replaceAll("-", "Petugas SIPO")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        LCount.setText("" + tabMode.getRowCount());
    }
}
