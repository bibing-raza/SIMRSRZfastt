package simrskhanza;

import inventory.DlgPemberianObat;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;
import laporan.DlgDiagnosaPenyakit;

/**
 *
 * @author perpustakaan
 */
public final class DlgRawatInap extends javax.swing.JDialog {

    private final DefaultTableModel tabModeDr, tabModePr, tabModeDrPr, tabModePemeriksaan, tabModeCppt;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Jurnal jur = new Jurnal();
    private Connection koneksi = koneksiDB.condb();
    public DlgCariPerawatanRanap perawatan = new DlgCariPerawatanRanap(null, false);
    public DlgCariPerawatanRanap2 perawatan2 = new DlgCariPerawatanRanap2(null, false);
    public DlgDiagnosaPenyakit diagnosa = new DlgDiagnosaPenyakit(null, false);
    //public DlgKamarInap kamarinap = new DlgKamarInap(null, false);
    private DlgPasien pasien = new DlgPasien(null, false);
    public  DlgCariDokter dokter_umum=new DlgCariDokter(null,false);
    private PreparedStatement ps, ps2, ps3, ps4, psrekening, pstarif, psdiagnosa, pscppt;
    private ResultSet rs, rsrekening, rscppt;
    private Date date = new Date();
    private Date date2 = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    private int i = 0, x, cekDb = 0, cekAda = 0;
    private double ttljmdokter = 0, ttljmperawat = 0, ttlkso = 0, ttlpendapatan = 0, lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
    private String Suspen_Piutang_Tindakan_Ranap = "", Tindakan_Ranap = "", Beban_Jasa_Medik_Dokter_Tindakan_Ranap = "",
            Utang_Jasa_Medik_Dokter_Tindakan_Ranap = "", Beban_Jasa_Medik_Paramedis_Tindakan_Ranap = "",
            Utang_Jasa_Medik_Paramedis_Tindakan_Ranap = "", Beban_KSO_Tindakan_Ranap = "", kd_pj, kamar, tgl_m, kdDiag = "", tgm = "",
            tglmasuk, jammasuk, Utang_KSO_Tindakan_Ranap = "", hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal"),
            now = dateFormat.format(date), diag_awal, key = "", cekdpjp = "";

    /**
     * Creates new form DlgRawatInap
     *
     * @param parent
     * @param modal
     */
    public DlgRawatInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8, 1);
        setSize(885, 674);

        tabModeDr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Perawatan/Tindakan", "Kode Dokter",
            "Dokter Yg Menangani", "Tgl.Input", "Jam Input", "Biaya", "Kode", "Tarif Dokter", "KSO","Dokter Umum/Yg Mewakili","kdDokter"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbRawatDr.setModel(tabModeDr);
        tbRawatDr.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRawatDr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbRawatDr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {//sembunyi
                //column.setPreferredWidth(90);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(70);
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
                column.setPreferredWidth(180);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRawatDr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Perawatan/Tindakan", "NIP",
            "Petugas Yg Menangani", "Tgl.Input", "Jam Input", "Biaya", "Kode", "Tarif Perawat", "KSO"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbRawatPr.setModel(tabModePr);
        tbRawatPr.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRawatPr.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbRawatPr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(300);
            } else if (i == 5) {//sembunyi
                //column.setPreferredWidth(90);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setPreferredWidth(180);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(70);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRawatPr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModeDrPr = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Perawatan/Tindakan", "Kode Dokter",
            "Dokter Yg Menangani", "NIP", "Petugas Yg Menangani", "Tgl.Input", "Jam Input",
            "Biaya", "Kode", "Tarif Dokter", "Tarif Petugas", "KSO"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
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

        for (i = 0; i < 16; i++) {
            TableColumn column = tbRawatDrPr.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(270);
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
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(70);
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
            }
        }
        tbRawatDrPr.setDefaultRenderer(Object.class, new WarnaTable());

        tabModePemeriksaan = new DefaultTableModel(null, new Object[]{
            "P", "No.Rawat", "No.R.M.", "Nama Pasien", "Tgl.Input", "Jam Input", "Suhu(C)", "Tensi", "Nadi(/menit)",
            "Respirasi(/menit)", "Tinggi(Cm)", "Berat(Kg)", "GCS(E,V,M)", "Keluhan", "Pemeriksaan", "Alergi"}) {
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
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbPemeriksaan.setModel(tabModePemeriksaan);
        tbPemeriksaan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemeriksaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 16; i++) {
            TableColumn column = tbPemeriksaan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(180);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(90);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setPreferredWidth(180);
            } else if (i == 14) {
                column.setPreferredWidth(180);
            } else if (i == 15) {
                column.setPreferredWidth(130);
            }
        }
        tbPemeriksaan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(40);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());

        kdptg.setDocument(new batasInput((byte) 20).getKata(kdptg));
        kdptg2.setDocument(new batasInput((byte) 20).getKata(kdptg2));
        KdDok.setDocument(new batasInput((byte) 20).getKata(KdDok));
        KdDok2.setDocument(new batasInput((byte) 20).getKata(KdDok2));
        TNoRw.setDocument(new batasInput((byte) 17).getKata(TNoRw));
        TKdPrw.setDocument(new batasInput((byte) 15).getKata(TKdPrw));
        TSuhu.setDocument(new batasInput((byte) 5).getKata(TSuhu));
        TTensi.setDocument(new batasInput((byte) 7).getKata(TTensi));
        TCariPasien.setDocument(new batasInput((byte) 20).getKata(TCariPasien));
        TKeluhan.setDocument(new batasInput((int) 300).getKata(TKeluhan));
        TTinggi.setDocument(new batasInput((byte) 5).getKata(TTinggi));
        TBerat.setDocument(new batasInput((byte) 5).getKata(TBerat));
        TPemeriksaan.setDocument(new batasInput((int) 300).getKata(TPemeriksaan));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        if (koneksiDB.cariCepat().equals("aktif")) {
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPr();
                    } else if (TabRawat.getSelectedIndex() == 2) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 3) {
                        tampilPemeriksaan();
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPr();
                    } else if (TabRawat.getSelectedIndex() == 2) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 3) {
                        tampilPemeriksaan();
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    if (TabRawat.getSelectedIndex() == 0) {
                        tampilDr();
                    } else if (TabRawat.getSelectedIndex() == 1) {
                        tampilPr();
                    } else if (TabRawat.getSelectedIndex() == 2) {
                        tampilDrPr();
                    } else if (TabRawat.getSelectedIndex() == 3) {
                        tampilPemeriksaan();
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
                if (akses.getform().equals("DlgRawatInap")) {
                    if (pasien.getTable().getSelectedRow() != -1) {
                        TCariPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    }
                    TCariPasien.requestFocus();
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
                if (akses.getform().equals("DlgRawatInap")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        pasien.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        perawatan.dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
                    if (perawatan.dokter.getTable().getSelectedRow() != -1) {
                        if (TabRawat.getSelectedIndex() == 0) {
                            KdDok.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(), 0).toString());
                            TDokter.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(), 1).toString());
                            KdDok.requestFocus();
                        } else if (TabRawat.getSelectedIndex() == 2) {
                            KdDok2.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(), 0).toString());
                            TDokter2.setText(perawatan.dokter.getTable().getValueAt(perawatan.dokter.getTable().getSelectedRow(), 1).toString());
                            KdDok2.requestFocus();
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
        
        dokter_umum.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
                    if (dokter_umum.getTable().getSelectedRow() != -1) {
                        if (TabRawat.getSelectedIndex() == 0) {
                            KdDok1.setText(dokter_umum.getTable().getValueAt(dokter_umum.getTable().getSelectedRow(), 0).toString());
                            TDokter1.setText(dokter_umum.getTable().getValueAt(dokter_umum.getTable().getSelectedRow(), 1).toString());
                            KdDok1.requestFocus();
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

        perawatan.petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
                    if (perawatan.petugas.getTable().getSelectedRow() != -1) {
                        if (TabRawat.getSelectedIndex() == 1) {
                            kdptg.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(), 0).toString());
                            TPerawat.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(), 1).toString());
                            kdptg.requestFocus();
                        } else if (TabRawat.getSelectedIndex() == 2) {
                            kdptg2.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(), 0).toString());
                            TPerawat2.setText(perawatan.petugas.getTable().getValueAt(perawatan.petugas.getTable().getSelectedRow(), 1).toString());
                            kdptg2.requestFocus();
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

        perawatan.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
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
                    }
                    TKdPrw.requestFocus();

                    if (perawatan.edit == false) {
                        if (TabRawat.getSelectedIndex() == 0) {
                            tampilDr();
                        } else if (TabRawat.getSelectedIndex() == 1) {
                            tampilPr();
                        } else if (TabRawat.getSelectedIndex() == 2) {
                            tampilDrPr();
                        }
                        emptTextTindakan();
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

        perawatan2.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
                    if (perawatan2.getTable().getSelectedRow() != -1) {
                        TKdPrw.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 4).toString());
                        TNmPrw.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 5).toString());
                        BagianRS.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 8).toString());
                        Bhp.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 9).toString());
                        JmDokter.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 10).toString());
                        JmPerawat.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 11).toString());
                        KSO.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 12).toString());
                        Menejemen.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 13).toString());
                        TTnd.setText(perawatan2.getTable().getValueAt(perawatan2.getTable().getSelectedRow(), 7).toString());
                    }
                    TKdPrw.requestFocus();
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

        diagnosa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgRawatInap")) {
                    try {
                        key = "";
                        psdiagnosa = koneksi.prepareStatement("select kd_penyakit from diagnosa_pasien where no_rawat=? order by prioritas asc");
                        try {
                            psdiagnosa.setString(1, TNoRw.getText());
                            rs = psdiagnosa.executeQuery();
                            while (rs.next()) {
                                key = rs.getString(1) + ", " + key;
                            }
                        } catch (Exception ex) {
                            System.out.println("Notifikasi : " + ex);
                        } finally {
                            if (rs != null) {
                                rs.close();
                            }
                            if (psdiagnosa != null) {
                                psdiagnosa.close();
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }

                    if (DlgMati.isVisible() == true) {
                        diagnosaakhir.setText(key);
                        diagnosaakhir.requestFocus();
                    } else if (DlgMati.isVisible() == false) {
                        Sequel.mengedit("kamar_inap", "no_rawat='" + TNoRw.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'", "diagnosa_akhir='" + key + "'");
                        //tampil();
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

        TJmlHari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isjml();
            }
        });

        TTarif.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                isjml();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                isjml();
            }
        });

        Valid.LoadTahun(CmbTahun);

        ChkInput.setSelected(false);
        isForm();
        jam();
        try {
            psrekening = koneksi.prepareStatement("select * from set_akun_ranap");
            try {
                rsrekening = psrekening.executeQuery();
                while (rsrekening.next()) {
                    Suspen_Piutang_Tindakan_Ranap = rsrekening.getString("Suspen_Piutang_Tindakan_Ranap");
                    Tindakan_Ranap = rsrekening.getString("Tindakan_Ranap");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ranap = rsrekening.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ranap");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ranap = rsrekening.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ranap");
                    Beban_Jasa_Medik_Paramedis_Tindakan_Ranap = rsrekening.getString("Beban_Jasa_Medik_Paramedis_Tindakan_Ranap");
                    Utang_Jasa_Medik_Paramedis_Tindakan_Ranap = rsrekening.getString("Utang_Jasa_Medik_Paramedis_Tindakan_Ranap");
                    Beban_KSO_Tindakan_Ranap = rsrekening.getString("Beban_KSO_Tindakan_Ranap");
                    Utang_KSO_Tindakan_Ranap = rsrekening.getString("Utang_KSO_Tindakan_Ranap");
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

        BagianRS = new javax.swing.JTextField();
        Bhp = new javax.swing.JTextField();
        JmDokter = new javax.swing.JTextField();
        JmPerawat = new javax.swing.JTextField();
        TTnd = new javax.swing.JTextField();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnPemberianObat = new javax.swing.JMenuItem();
        MnDiagnosa = new javax.swing.JMenuItem();
        KSO = new javax.swing.JTextField();
        Menejemen = new javax.swing.JTextField();
        DlgMati = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        BtnSimpanMati = new widget.Button();
        jLabel42 = new widget.Label();
        ket = new widget.TextBox();
        jLabel43 = new widget.Label();
        TglMati = new widget.Tanggal();
        jLabel39 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel17 = new widget.Label();
        kdkamar = new widget.TextBox();
        TKdBngsal = new widget.TextBox();
        TBangsal = new widget.TextBox();
        jLabel26 = new widget.Label();
        TSttsKamar = new widget.TextBox();
        jLabel27 = new widget.Label();
        CmbTgl = new widget.ComboBox();
        CmbBln = new widget.ComboBox();
        CmbTahun = new widget.ComboBox();
        jLabel29 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel30 = new widget.Label();
        diagnosaakhir = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        jLabel31 = new widget.Label();
        TJmlHari = new widget.TextBox();
        TTarif = new widget.TextBox();
        jLabel32 = new widget.Label();
        ttlbiaya = new widget.TextBox();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        LblStts = new widget.Label();
        jLabel35 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel28 = new widget.Label();
        BtnBatalMati = new widget.Button();
        TIn = new javax.swing.JTextField();
        TOut = new javax.swing.JTextField();
        JamMasuk = new widget.TextBox();
        diagnosaawal = new widget.TextBox();
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
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel20 = new widget.Label();
        TCariPasien = new widget.TextBox();
        btnPasien = new widget.Button();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        internalFrame6 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbRawatDr = new widget.Table();
        panelGlass7 = new widget.panelisi();
        TDokter = new widget.TextBox();
        jLabel5 = new widget.Label();
        KdDok = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel40 = new widget.Label();
        KdDok1 = new widget.TextBox();
        TDokter1 = new widget.TextBox();
        btnDokter1 = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbRawatPr = new widget.Table();
        panelGlass9 = new widget.panelisi();
        TPerawat = new widget.TextBox();
        jLabel13 = new widget.Label();
        kdptg = new widget.TextBox();
        btnPetugas = new widget.Button();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbRawatDrPr = new widget.Table();
        panelGlass11 = new widget.panelisi();
        TDokter2 = new widget.TextBox();
        jLabel12 = new widget.Label();
        btnSeekDokter2 = new widget.Button();
        jLabel14 = new widget.Label();
        TPerawat2 = new widget.TextBox();
        btnSeekPetugas2 = new widget.Button();
        KdDok2 = new widget.TextBox();
        kdptg2 = new widget.TextBox();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        tbPemeriksaan = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel8 = new widget.Label();
        TKeluhan = new widget.TextBox();
        jLabel7 = new widget.Label();
        TSuhu = new widget.TextBox();
        jLabel4 = new widget.Label();
        TTensi = new widget.TextBox();
        jLabel25 = new widget.Label();
        TBerat = new widget.TextBox();
        jLabel9 = new widget.Label();
        TPemeriksaan = new widget.TextBox();
        jLabel16 = new widget.Label();
        TTinggi = new widget.TextBox();
        jLabel24 = new widget.Label();
        TNadi = new widget.TextBox();
        jLabel15 = new widget.Label();
        TAlergi = new widget.TextBox();
        jLabel23 = new widget.Label();
        TRespirasi = new widget.TextBox();
        jLabel22 = new widget.Label();
        TGCS = new widget.TextBox();
        internalFrame9 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel3 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        TKdPrw = new widget.TextBox();
        btnTindakan = new widget.Button();
        TNmPrw = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        btnTindakan2 = new widget.Button();
        jLabel36 = new widget.Label();
        TRuangan = new widget.TextBox();
        jLabel37 = new widget.Label();
        Ttgl_rawat = new widget.TextBox();
        jLabel38 = new widget.Label();
        Tjam_rawat = new widget.TextBox();
        ChkInput = new widget.CekBox();

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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Pemberian Obat/BHP");
        MnPemberianObat.setEnabled(false);
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(180, 26));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPemberianObat);

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

        KSO.setEditable(false);
        KSO.setText("0");
        KSO.setName("KSO"); // NOI18N

        Menejemen.setEditable(false);
        Menejemen.setText("0");
        Menejemen.setName("Menejemen"); // NOI18N

        DlgMati.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        DlgMati.setName("DlgMati"); // NOI18N
        DlgMati.setUndecorated(true);
        DlgMati.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pasien Meninggal ]::"));
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(null);

        BtnSimpanMati.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanMati.setMnemonic('S');
        BtnSimpanMati.setText("Simpan");
        BtnSimpanMati.setToolTipText("Alt+S");
        BtnSimpanMati.setName("BtnSimpanMati"); // NOI18N
        BtnSimpanMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanMatiActionPerformed(evt);
            }
        });
        BtnSimpanMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanMatiKeyPressed(evt);
            }
        });
        internalFrame8.add(BtnSimpanMati);
        BtnSimpanMati.setBounds(450, 220, 90, 30);

        jLabel42.setForeground(new java.awt.Color(0, 0, 153));
        jLabel42.setText("Tgl. Meninggal :");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame8.add(jLabel42);
        jLabel42.setBounds(10, 50, 120, 23);

        ket.setForeground(new java.awt.Color(0, 0, 153));
        ket.setHighlighter(null);
        ket.setName("ket"); // NOI18N
        ket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ketKeyPressed(evt);
            }
        });
        internalFrame8.add(ket);
        ket.setBounds(135, 20, 500, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 153));
        jLabel43.setText("Keterangan Meninggal :");
        jLabel43.setName("jLabel43"); // NOI18N
        internalFrame8.add(jLabel43);
        jLabel43.setBounds(10, 20, 120, 23);

        TglMati.setEditable(false);
        TglMati.setForeground(new java.awt.Color(0, 0, 153));
        TglMati.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
        TglMati.setDisplayFormat("dd-MM-yyyy");
        TglMati.setName("TglMati"); // NOI18N
        TglMati.setOpaque(false);
        TglMati.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TglMatiItemStateChanged(evt);
            }
        });
        TglMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglMatiKeyPressed(evt);
            }
        });
        internalFrame8.add(TglMati);
        TglMati.setBounds(138, 50, 100, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 153));
        jLabel39.setText("Jam :");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame8.add(jLabel39);
        jLabel39.setBounds(240, 50, 35, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 153));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbJam2);
        cmbJam2.setBounds(280, 50, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 153));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbMnt2);
        cmbMnt2.setBounds(330, 50, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 153));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk2KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbDtk2);
        cmbDtk2.setBounds(380, 50, 45, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Kamar :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame8.add(jLabel17);
        jLabel17.setBounds(10, 100, 120, 23);

        kdkamar.setEditable(false);
        kdkamar.setForeground(new java.awt.Color(0, 0, 0));
        kdkamar.setHighlighter(null);
        kdkamar.setName("kdkamar"); // NOI18N
        kdkamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdkamarKeyPressed(evt);
            }
        });
        internalFrame8.add(kdkamar);
        kdkamar.setBounds(138, 100, 95, 23);

        TKdBngsal.setEditable(false);
        TKdBngsal.setForeground(new java.awt.Color(0, 0, 0));
        TKdBngsal.setName("TKdBngsal"); // NOI18N
        internalFrame8.add(TKdBngsal);
        TKdBngsal.setBounds(236, 100, 82, 23);

        TBangsal.setEditable(false);
        TBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TBangsal.setHighlighter(null);
        TBangsal.setName("TBangsal"); // NOI18N
        TBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBangsalKeyPressed(evt);
            }
        });
        internalFrame8.add(TBangsal);
        TBangsal.setBounds(320, 100, 318, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Stts.Kamar :");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame8.add(jLabel26);
        jLabel26.setBounds(500, 130, 70, 23);

        TSttsKamar.setEditable(false);
        TSttsKamar.setForeground(new java.awt.Color(0, 0, 0));
        TSttsKamar.setName("TSttsKamar"); // NOI18N
        internalFrame8.add(TSttsKamar);
        TSttsKamar.setBounds(575, 130, 63, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tanggal Pulang/Keluar :");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame8.add(jLabel27);
        jLabel27.setBounds(10, 130, 120, 23);

        CmbTgl.setForeground(new java.awt.Color(0, 0, 0));
        CmbTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        CmbTgl.setName("CmbTgl"); // NOI18N
        CmbTgl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTglCmbTahunItemStateChanged(evt);
            }
        });
        CmbTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbTglKeyPressed(evt);
            }
        });
        internalFrame8.add(CmbTgl);
        CmbTgl.setBounds(140, 130, 48, 23);

        CmbBln.setForeground(new java.awt.Color(0, 0, 0));
        CmbBln.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        CmbBln.setName("CmbBln"); // NOI18N
        CmbBln.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbBlnCmbTahunItemStateChanged(evt);
            }
        });
        CmbBln.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbBlnKeyPressed(evt);
            }
        });
        internalFrame8.add(CmbBln);
        CmbBln.setBounds(190, 130, 48, 23);

        CmbTahun.setForeground(new java.awt.Color(0, 0, 0));
        CmbTahun.setName("CmbTahun"); // NOI18N
        CmbTahun.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbTahunItemStateChanged(evt);
            }
        });
        CmbTahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbTahunKeyPressed(evt);
            }
        });
        internalFrame8.add(CmbTahun);
        CmbTahun.setBounds(240, 130, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Jam :");
        jLabel29.setName("jLabel29"); // NOI18N
        internalFrame8.add(jLabel29);
        jLabel29.setBounds(315, 130, 30, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbJam1CmbTahunItemStateChanged(evt);
            }
        });
        cmbJam1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJam1ActionPerformed(evt);
            }
        });
        cmbJam1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJam1KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbJam1);
        cmbJam1.setBounds(350, 130, 48, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMnt1CmbTahunItemStateChanged(evt);
            }
        });
        cmbMnt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMnt1KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbMnt1);
        cmbMnt1.setBounds(400, 130, 48, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbDtk1CmbTahunItemStateChanged(evt);
            }
        });
        cmbDtk1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtk1KeyPressed(evt);
            }
        });
        internalFrame8.add(cmbDtk1);
        cmbDtk1.setBounds(450, 130, 48, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Diagnosa Akhir Keluar :");
        jLabel30.setName("jLabel30"); // NOI18N
        internalFrame8.add(jLabel30);
        jLabel30.setBounds(10, 160, 120, 23);

        diagnosaakhir.setForeground(new java.awt.Color(0, 0, 0));
        diagnosaakhir.setHighlighter(null);
        diagnosaakhir.setName("diagnosaakhir"); // NOI18N
        diagnosaakhir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosaakhirKeyPressed(evt);
            }
        });
        internalFrame8.add(diagnosaakhir);
        diagnosaakhir.setBounds(138, 160, 190, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('3');
        btnDiagnosa.setToolTipText("Alt+3");
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
        internalFrame8.add(btnDiagnosa);
        btnDiagnosa.setBounds(330, 160, 28, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Biaya :");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame8.add(jLabel31);
        jLabel31.setBounds(10, 190, 120, 23);

        TJmlHari.setEditable(false);
        TJmlHari.setForeground(new java.awt.Color(0, 0, 0));
        TJmlHari.setText("0");
        TJmlHari.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TJmlHari.setHighlighter(null);
        TJmlHari.setName("TJmlHari"); // NOI18N
        internalFrame8.add(TJmlHari);
        TJmlHari.setBounds(138, 190, 50, 23);

        TTarif.setEditable(false);
        TTarif.setForeground(new java.awt.Color(0, 0, 0));
        TTarif.setText("0");
        TTarif.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        TTarif.setHighlighter(null);
        TTarif.setName("TTarif"); // NOI18N
        internalFrame8.add(TTarif);
        TTarif.setBounds(205, 190, 120, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("=");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame8.add(jLabel32);
        jLabel32.setBounds(320, 190, 20, 23);

        ttlbiaya.setEditable(false);
        ttlbiaya.setForeground(new java.awt.Color(0, 0, 0));
        ttlbiaya.setText("0");
        ttlbiaya.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ttlbiaya.setHighlighter(null);
        ttlbiaya.setName("ttlbiaya"); // NOI18N
        ttlbiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ttlbiayaKeyPressed(evt);
            }
        });
        internalFrame8.add(ttlbiaya);
        ttlbiaya.setBounds(350, 190, 287, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("X");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame8.add(jLabel33);
        jLabel33.setBounds(190, 190, 15, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Proses :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame8.add(jLabel34);
        jLabel34.setBounds(10, 220, 120, 23);

        LblStts.setForeground(new java.awt.Color(0, 0, 0));
        LblStts.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblStts.setText("Check In");
        LblStts.setName("LblStts"); // NOI18N
        internalFrame8.add(LblStts);
        LblStts.setBounds(140, 220, 180, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Status Pulang/Keluar :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame8.add(jLabel35);
        jLabel35.setBounds(365, 160, 120, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Meninggal >= 48 Jam", "Meninggal < 48 Jam" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        cmbStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbStatusKeyPressed(evt);
            }
        });
        internalFrame8.add(cmbStatus);
        cmbStatus.setBounds(490, 160, 140, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel28.setName("jLabel28"); // NOI18N
        internalFrame8.add(jLabel28);
        jLabel28.setBounds(0, 80, 850, 14);

        BtnBatalMati.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatalMati.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatalMati.setMnemonic('B');
        BtnBatalMati.setText("Batal");
        BtnBatalMati.setToolTipText("Alt+B");
        BtnBatalMati.setName("BtnBatalMati"); // NOI18N
        BtnBatalMati.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalMatiActionPerformed(evt);
            }
        });
        BtnBatalMati.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalMatiKeyPressed(evt);
            }
        });
        internalFrame8.add(BtnBatalMati);
        BtnBatalMati.setBounds(550, 220, 90, 30);

        DlgMati.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        TIn.setEditable(false);
        TIn.setText("0");
        TIn.setName("TIn"); // NOI18N

        TOut.setEditable(false);
        TOut.setText("0");
        TOut.setName("TOut"); // NOI18N

        JamMasuk.setEditable(false);
        JamMasuk.setForeground(new java.awt.Color(255, 255, 255));
        JamMasuk.setHighlighter(null);
        JamMasuk.setName("JamMasuk"); // NOI18N
        JamMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                JamMasukKeyPressed(evt);
            }
        });

        diagnosaawal.setEditable(false);
        diagnosaawal.setForeground(new java.awt.Color(255, 255, 255));
        diagnosaawal.setHighlighter(null);
        diagnosaawal.setName("diagnosaawal"); // NOI18N
        diagnosaawal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosaawalKeyPressed(evt);
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Perawatan/Tindakan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setBackground(new java.awt.Color(215, 225, 215));
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
        BtnSimpan.setIconTextGap(3);
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
        BtnBatal.setIconTextGap(3);
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
        BtnHapus.setIconTextGap(3);
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
        BtnEdit.setIconTextGap(3);
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
        BtnPrint.setIconTextGap(3);
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
        jLabel10.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass8.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(85, 30));
        panelGlass8.add(LCount);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setIconTextGap(3);
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(64, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass10.add(DTPCari2);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("No.RM :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass10.add(jLabel20);

        TCariPasien.setForeground(new java.awt.Color(0, 0, 0));
        TCariPasien.setName("TCariPasien"); // NOI18N
        TCariPasien.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass10.add(TCariPasien);

        btnPasien.setForeground(new java.awt.Color(0, 0, 0));
        btnPasien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
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
        panelGlass10.add(btnPasien);

        jSeparator5.setBackground(new java.awt.Color(220, 225, 215));
        jSeparator5.setForeground(new java.awt.Color(220, 225, 215));
        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator5.setName("jSeparator5"); // NOI18N
        jSeparator5.setOpaque(true);
        jSeparator5.setPreferredSize(new java.awt.Dimension(1, 23));
        panelGlass10.add(jSeparator5);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(240, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('D');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+D");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(140, 23));
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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.GridLayout());

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)));
        TabRawat.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbRawatDr.setAutoCreateRowSorter(true);
        tbRawatDr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatDr.setComponentPopupMenu(jPopupMenu1);
        tbRawatDr.setName("tbRawatDr"); // NOI18N
        tbRawatDr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatDrMouseClicked(evt);
            }
        });
        tbRawatDr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRawatDrKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbRawatDr);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 75));
        panelGlass7.setLayout(null);

        TDokter.setEditable(false);
        TDokter.setForeground(new java.awt.Color(0, 0, 0));
        TDokter.setHighlighter(null);
        TDokter.setName("TDokter"); // NOI18N
        panelGlass7.add(TDokter);
        TDokter.setBounds(246, 10, 330, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Dokter Umum/Mewakili :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 38, 130, 23);

        KdDok.setEditable(false);
        KdDok.setForeground(new java.awt.Color(0, 0, 0));
        KdDok.setHighlighter(null);
        KdDok.setName("KdDok"); // NOI18N
        KdDok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDokKeyPressed(evt);
            }
        });
        panelGlass7.add(KdDok);
        KdDok.setBounds(134, 10, 110, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('4');
        btnDokter.setToolTipText("ALt+4");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        panelGlass7.add(btnDokter);
        btnDokter.setBounds(580, 10, 28, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Dokter Spesialis :");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass7.add(jLabel40);
        jLabel40.setBounds(0, 10, 130, 23);

        KdDok1.setEditable(false);
        KdDok1.setForeground(new java.awt.Color(0, 0, 0));
        KdDok1.setHighlighter(null);
        KdDok1.setName("KdDok1"); // NOI18N
        KdDok1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok1KeyPressed(evt);
            }
        });
        panelGlass7.add(KdDok1);
        KdDok1.setBounds(134, 38, 110, 23);

        TDokter1.setEditable(false);
        TDokter1.setForeground(new java.awt.Color(0, 0, 0));
        TDokter1.setHighlighter(null);
        TDokter1.setName("TDokter1"); // NOI18N
        panelGlass7.add(TDokter1);
        TDokter1.setBounds(246, 38, 330, 23);

        btnDokter1.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter1.setMnemonic('4');
        btnDokter1.setToolTipText("ALt+4");
        btnDokter1.setName("btnDokter1"); // NOI18N
        btnDokter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokter1ActionPerformed(evt);
            }
        });
        panelGlass7.add(btnDokter1);
        btnDokter1.setBounds(580, 38, 30, 23);

        internalFrame2.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Dokter", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbRawatPr.setAutoCreateRowSorter(true);
        tbRawatPr.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbRawatPr.setComponentPopupMenu(jPopupMenu1);
        tbRawatPr.setName("tbRawatPr"); // NOI18N
        tbRawatPr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbRawatPrMouseClicked(evt);
            }
        });
        tbRawatPr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbRawatPrKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbRawatPr);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(null);

        TPerawat.setEditable(false);
        TPerawat.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TPerawat.setHighlighter(null);
        TPerawat.setName("TPerawat"); // NOI18N
        panelGlass9.add(TPerawat);
        TPerawat.setBounds(206, 10, 380, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Petugas :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass9.add(jLabel13);
        jLabel13.setBounds(0, 10, 70, 23);

        kdptg.setEditable(false);
        kdptg.setForeground(new java.awt.Color(0, 0, 0));
        kdptg.setHighlighter(null);
        kdptg.setName("kdptg"); // NOI18N
        kdptg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptgKeyPressed(evt);
            }
        });
        panelGlass9.add(kdptg);
        kdptg.setBounds(74, 10, 130, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('5');
        btnPetugas.setToolTipText("ALt+5");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelGlass9.add(btnPetugas);
        btnPetugas.setBounds(590, 10, 28, 23);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Petugas", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

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

        TDokter2.setEditable(false);
        TDokter2.setForeground(new java.awt.Color(0, 0, 0));
        TDokter2.setHighlighter(null);
        TDokter2.setName("TDokter2"); // NOI18N
        panelGlass11.add(TDokter2);
        TDokter2.setBounds(206, 10, 380, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Dokter :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass11.add(jLabel12);
        jLabel12.setBounds(0, 10, 70, 23);

        btnSeekDokter2.setForeground(new java.awt.Color(0, 0, 0));
        btnSeekDokter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSeekDokter2.setMnemonic('4');
        btnSeekDokter2.setToolTipText("ALt+4");
        btnSeekDokter2.setName("btnSeekDokter2"); // NOI18N
        btnSeekDokter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeekDokter2ActionPerformed(evt);
            }
        });
        panelGlass11.add(btnSeekDokter2);
        btnSeekDokter2.setBounds(590, 10, 28, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Petugas :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass11.add(jLabel14);
        jLabel14.setBounds(0, 38, 70, 23);

        TPerawat2.setEditable(false);
        TPerawat2.setBackground(new java.awt.Color(202, 202, 202));
        TPerawat2.setForeground(new java.awt.Color(0, 0, 0));
        TPerawat2.setHighlighter(null);
        TPerawat2.setName("TPerawat2"); // NOI18N
        panelGlass11.add(TPerawat2);
        TPerawat2.setBounds(206, 38, 380, 23);

        btnSeekPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        btnSeekPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSeekPetugas2.setMnemonic('5');
        btnSeekPetugas2.setToolTipText("ALt+5");
        btnSeekPetugas2.setName("btnSeekPetugas2"); // NOI18N
        btnSeekPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeekPetugas2ActionPerformed(evt);
            }
        });
        panelGlass11.add(btnSeekPetugas2);
        btnSeekPetugas2.setBounds(590, 38, 28, 23);

        KdDok2.setEditable(false);
        KdDok2.setForeground(new java.awt.Color(0, 0, 0));
        KdDok2.setHighlighter(null);
        KdDok2.setName("KdDok2"); // NOI18N
        KdDok2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdDok2KeyPressed(evt);
            }
        });
        panelGlass11.add(KdDok2);
        KdDok2.setBounds(74, 10, 130, 23);

        kdptg2.setEditable(false);
        kdptg2.setForeground(new java.awt.Color(0, 0, 0));
        kdptg2.setHighlighter(null);
        kdptg2.setName("kdptg2"); // NOI18N
        kdptg2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdptg2KeyPressed(evt);
            }
        });
        panelGlass11.add(kdptg2);
        kdptg2.setBounds(74, 38, 130, 23);

        internalFrame4.add(panelGlass11, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Penanganan Dokter & Petugas", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setComponentPopupMenu(jPopupMenu1);
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        tbPemeriksaan.setAutoCreateRowSorter(true);
        tbPemeriksaan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemeriksaan.setComponentPopupMenu(jPopupMenu1);
        tbPemeriksaan.setName("tbPemeriksaan"); // NOI18N
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
        Scroll3.setViewportView(tbPemeriksaan);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 90));
        panelGlass12.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Keluhan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass12.add(jLabel8);
        jLabel8.setBounds(0, 5, 95, 23);

        TKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        TKeluhan.setHighlighter(null);
        TKeluhan.setName("TKeluhan"); // NOI18N
        TKeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKeluhanKeyPressed(evt);
            }
        });
        panelGlass12.add(TKeluhan);
        TKeluhan.setBounds(100, 5, 300, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Suhu Badan(C) :");
        jLabel7.setName("jLabel7"); // NOI18N
        panelGlass12.add(jLabel7);
        jLabel7.setBounds(400, 5, 100, 23);

        TSuhu.setForeground(new java.awt.Color(0, 0, 0));
        TSuhu.setFocusTraversalPolicyProvider(true);
        TSuhu.setName("TSuhu"); // NOI18N
        TSuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSuhuKeyPressed(evt);
            }
        });
        panelGlass12.add(TSuhu);
        TSuhu.setBounds(505, 5, 60, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Tensi :");
        jLabel4.setName("jLabel4"); // NOI18N
        panelGlass12.add(jLabel4);
        jLabel4.setBounds(565, 5, 78, 23);

        TTensi.setForeground(new java.awt.Color(0, 0, 0));
        TTensi.setHighlighter(null);
        TTensi.setName("TTensi"); // NOI18N
        TTensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTensiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTensi);
        TTensi.setBounds(645, 5, 60, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Berat(Kg) :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelGlass12.add(jLabel25);
        jLabel25.setBounds(705, 5, 70, 23);

        TBerat.setForeground(new java.awt.Color(0, 0, 0));
        TBerat.setHighlighter(null);
        TBerat.setName("TBerat"); // NOI18N
        TBerat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TBeratKeyPressed(evt);
            }
        });
        panelGlass12.add(TBerat);
        TBerat.setBounds(780, 5, 60, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Pemeriksaan :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass12.add(jLabel9);
        jLabel9.setBounds(0, 33, 95, 23);

        TPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TPemeriksaan.setHighlighter(null);
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        panelGlass12.add(TPemeriksaan);
        TPemeriksaan.setBounds(100, 33, 300, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Tinggi Badan(Cm) :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass12.add(jLabel16);
        jLabel16.setBounds(400, 33, 100, 23);

        TTinggi.setForeground(new java.awt.Color(0, 0, 0));
        TTinggi.setFocusTraversalPolicyProvider(true);
        TTinggi.setName("TTinggi"); // NOI18N
        TTinggi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TTinggiKeyPressed(evt);
            }
        });
        panelGlass12.add(TTinggi);
        TTinggi.setBounds(505, 33, 60, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Nadi(/menit) :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass12.add(jLabel24);
        jLabel24.setBounds(565, 33, 78, 23);

        TNadi.setForeground(new java.awt.Color(0, 0, 0));
        TNadi.setFocusTraversalPolicyProvider(true);
        TNadi.setName("TNadi"); // NOI18N
        TNadi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TNadiActionPerformed(evt);
            }
        });
        TNadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNadiKeyPressed(evt);
            }
        });
        panelGlass12.add(TNadi);
        TNadi.setBounds(645, 33, 60, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Alergi :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass12.add(jLabel15);
        jLabel15.setBounds(0, 60, 95, 23);

        TAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TAlergi.setHighlighter(null);
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        panelGlass12.add(TAlergi);
        TAlergi.setBounds(100, 60, 300, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Respirasi(/menit) :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelGlass12.add(jLabel23);
        jLabel23.setBounds(400, 60, 100, 23);

        TRespirasi.setForeground(new java.awt.Color(0, 0, 0));
        TRespirasi.setHighlighter(null);
        TRespirasi.setName("TRespirasi"); // NOI18N
        TRespirasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRespirasiKeyPressed(evt);
            }
        });
        panelGlass12.add(TRespirasi);
        TRespirasi.setBounds(505, 60, 60, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("GCS(E,V,M) :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass12.add(jLabel22);
        jLabel22.setBounds(565, 60, 78, 23);

        TGCS.setForeground(new java.awt.Color(0, 0, 0));
        TGCS.setFocusTraversalPolicyProvider(true);
        TGCS.setName("TGCS"); // NOI18N
        TGCS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TGCSKeyPressed(evt);
            }
        });
        panelGlass12.add(TGCS);
        TGCS.setBounds(645, 60, 110, 23);

        internalFrame5.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        TabRawat.addTab("Pemeriksaan", internalFrame5);

        internalFrame6.add(TabRawat);

        internalFrame9.setBorder(null);
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setPreferredSize(new java.awt.Dimension(600, 500));
        internalFrame9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk membaca CPPT");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCPPTMouseClicked(evt);
            }
        });
        tbCPPT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCPPTKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbCPPT);

        internalFrame9.add(Scroll4);

        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(600, 500));
        internalFrame7.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 350));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        internalFrame7.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 250));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        internalFrame7.add(scrollPane4, java.awt.BorderLayout.CENTER);
        scrollPane4.getAccessibleContext().setAccessibleName("CPPT");

        internalFrame9.add(internalFrame7);

        internalFrame6.add(internalFrame9);

        internalFrame1.add(internalFrame6, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(710, 150));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(710, 100));
        FormInput.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("No.Rawat : ");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(0, 12, 95, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(98, 12, 130, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(230, 12, 80, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(313, 12, 500, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Tndkn/Tghan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 68, 95, 23);

        TKdPrw.setEditable(false);
        TKdPrw.setForeground(new java.awt.Color(0, 0, 0));
        TKdPrw.setName("TKdPrw"); // NOI18N
        TKdPrw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKdPrwKeyPressed(evt);
            }
        });
        FormInput.add(TKdPrw);
        TKdPrw.setBounds(98, 68, 140, 23);

        btnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        btnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
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
        btnTindakan.setBounds(825, 68, 28, 23);

        TNmPrw.setEditable(false);
        TNmPrw.setForeground(new java.awt.Color(0, 0, 0));
        TNmPrw.setName("TNmPrw"); // NOI18N
        FormInput.add(TNmPrw);
        TNmPrw.setBounds(240, 68, 580, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "23-10-2023" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(100, 96, 110, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 96, 95, 23);

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
        cmbJam.setBounds(212, 96, 55, 23);

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
        cmbMnt.setBounds(270, 96, 55, 23);

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
        cmbDtk.setBounds(330, 96, 55, 23);

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
        ChkJln.setBounds(390, 96, 23, 23);

        btnTindakan2.setForeground(new java.awt.Color(0, 0, 0));
        btnTindakan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnTindakan2.setMnemonic('3');
        btnTindakan2.setToolTipText("Alt+3");
        btnTindakan2.setName("btnTindakan2"); // NOI18N
        btnTindakan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTindakan2ActionPerformed(evt);
            }
        });
        btnTindakan2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnTindakan2KeyPressed(evt);
            }
        });
        FormInput.add(btnTindakan2);
        btnTindakan2.setBounds(860, 68, 28, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Ruang Rawat : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 40, 95, 23);

        TRuangan.setEditable(false);
        TRuangan.setForeground(new java.awt.Color(0, 0, 0));
        TRuangan.setHighlighter(null);
        TRuangan.setName("TRuangan"); // NOI18N
        FormInput.add(TRuangan);
        TRuangan.setBounds(98, 40, 420, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Tgl. Rawat : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(520, 40, 70, 23);

        Ttgl_rawat.setEditable(false);
        Ttgl_rawat.setForeground(new java.awt.Color(0, 0, 0));
        Ttgl_rawat.setHighlighter(null);
        Ttgl_rawat.setName("Ttgl_rawat"); // NOI18N
        FormInput.add(Ttgl_rawat);
        Ttgl_rawat.setBounds(590, 40, 90, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Jam : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(685, 40, 30, 23);

        Tjam_rawat.setEditable(false);
        Tjam_rawat.setForeground(new java.awt.Color(0, 0, 0));
        Tjam_rawat.setHighlighter(null);
        Tjam_rawat.setName("Tjam_rawat"); // NOI18N
        FormInput.add(Tjam_rawat);
        Tjam_rawat.setBounds(715, 40, 90, 23);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isRawat();
            isPsien();
        } else {
            Valid.pindah(evt, TCari, TKdPrw);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TSuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSuhuKeyPressed
        Valid.pindah(evt, TAlergi, TTensi);
}//GEN-LAST:event_TSuhuKeyPressed

    private void TTensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTensiKeyPressed
        Valid.pindah(evt, TSuhu, TBerat);
}//GEN-LAST:event_TTensiKeyPressed

    private void TKeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKeluhanKeyPressed
        Valid.pindah(evt, TKdPrw, TPemeriksaan);
}//GEN-LAST:event_TKeluhanKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        Valid.pindah(evt, TKeluhan, TAlergi);
}//GEN-LAST:event_TPemeriksaanKeyPressed

    private void TKdPrwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdPrwKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            isJns();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnTindakanActionPerformed(null);
        } else {
            Valid.pindah(evt, TNoRw, DTPTgl);
        }
}//GEN-LAST:event_TKdPrwKeyPressed

    private void btnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (TabRawat.getSelectedIndex() == 0) {
                if (TDokter.getText().trim().equals("")) {
                    Valid.textKosong(KdDok, "Dokter");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan.setPetugas(KdDok.getText(), TDokter.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan.emptTeks();
                        perawatan.isCek();
                        perawatan.tampil();
                        perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                        perawatan.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan.setLocationRelativeTo(internalFrame1);
                        perawatan.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan.setPetugas(KdDok.getText(), TDokter.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan.emptTeks();
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                            perawatan.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                if (TPerawat.getText().trim().equals("")) {
                    Valid.textKosong(kdptg, "perawat");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan.setNoRm(TNoRw.getText(), "rawat_inap_pr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan.setPetugas(kdptg.getText(), TPerawat.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan.emptTeks();
                        perawatan.isCek();
                        perawatan.tampil();
                        perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                        perawatan.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan.setLocationRelativeTo(internalFrame1);
                        perawatan.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_pr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan.setPetugas(kdptg.getText(), TPerawat.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan.emptTeks();
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                            perawatan.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 2) {
                if (TDokter2.getText().trim().equals("")) {
                    Valid.textKosong(KdDok2, "Dokter");
                } else if (TPerawat2.getText().trim().equals("")) {
                    Valid.textKosong(kdptg2, "perawat");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan.setNoRm(TNoRw.getText(), "rawat_inap_drpr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan.setPetugas(KdDok2.getText(), TDokter2.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), kdptg2.getText(), TPerawat2.getText(),
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan.emptTeks();
                        perawatan.isCek();
                        perawatan.tampil();
                        perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                        perawatan.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan.setLocationRelativeTo(internalFrame1);
                        perawatan.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan.setNoRm(TNoRw.getText(), "rawat_inap_drpr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan.setPetugas(KdDok2.getText(), TDokter2.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), kdptg2.getText(), TPerawat2.getText(),
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan.emptTeks();
                            perawatan.isCek();
                            perawatan.tampil();
                            perawatan.setPasien(TNoRw.getText(), TNoRM.getText(), TPasien.getText(), TRuangan.getText(), Ttgl_rawat.getText(), Tjam_rawat.getText());
                            perawatan.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan.setLocationRelativeTo(internalFrame1);
                            perawatan.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih penanganan..!!!");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_btnTindakanActionPerformed

    private void btnTindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakanKeyPressed
        Valid.pindah(evt, TKdPrw, TKeluhan);
}//GEN-LAST:event_btnTindakanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if ((!TKeluhan.getText().trim().equals("")) || (!TPemeriksaan.getText().trim().equals(""))
                    || (!TSuhu.getText().trim().equals("")) || (!TTensi.getText().trim().equals(""))
                    || (!TAlergi.getText().trim().equals("")) || (!TTinggi.getText().trim().equals(""))
                    || (!TBerat.getText().trim().equals("")) || (!TRespirasi.getText().trim().equals(""))
                    || (!TNadi.getText().trim().equals("")) || (!TGCS.getText().trim().equals(""))) {
                Sequel.menyimpan("pemeriksaan_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 13, new String[]{
                    TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    TSuhu.getText(), TTensi.getText(), TNadi.getText(), TRespirasi.getText(), TTinggi.getText(),
                    TBerat.getText(), TGCS.getText(), TKeluhan.getText(), TPemeriksaan.getText(), TAlergi.getText()
                });
            }
            if (TabRawat.getSelectedIndex() == 0) {
                if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("") || KdDok.getText().trim().equals("-") || KdDok.getText().trim().equals("--")) {
                    Valid.textKosong(KdDok, "Dokter Spesialis");
                } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                    Valid.textKosong(TKdPrw, "perawatan");
                } else if (KdDok1.getText().trim().equals("") || TDokter1.getText().trim().equals("")) {
                    Valid.textKosong(KdDok1, "Dokter Umum");
                } else if (TKdPrw.getText().equals("2017RI1U03.A") || (TKdPrw.getText().equals("2017RI1U03.B")) || (TKdPrw.getText().equals("2017RI1U03.C"))) {
                    JOptionPane.showMessageDialog(null, "Utk. tindakan " + TNmPrw.getText() + " hanya bisa dimasukkan di pilihan penanganan petugas saja...!!");
                    btnTindakan.requestFocus();
                } else {
                    if (Sequel.menyimpantf("rawat_inap_dr", "?,?,?,?,?,?,?,?,?,?,?,?", "Data", 12, new String[]{
                        TNoRw.getText(), TKdPrw.getText(), KdDok.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        BagianRS.getText(), Bhp.getText(), JmDokter.getText(), KSO.getText(), Menejemen.getText(), TTnd.getText(), KdDok1.getText()
                    }) == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmDokter.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','" + JmDokter.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','0','" + JmDokter.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "TINDAKAN RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                        Sequel.AutoComitTrue();
                        tampilDr();
                        emptTextTindakan();
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                if (kdptg.getText().trim().equals("") || TPerawat.getText().trim().equals("")) {
                    Valid.textKosong(kdptg, "Petugas");
                } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                    Valid.textKosong(TKdPrw, "perawatan");
                } else {
                    if (Sequel.menyimpantf("rawat_inap_pr", "?,?,?,?,?,?,?,?,?,?,?", "Data", 11, new String[]{
                        TNoRw.getText(), TKdPrw.getText(), kdptg.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        BagianRS.getText(), Bhp.getText(), JmPerawat.getText(), KSO.getText(), Menejemen.getText(), TTnd.getText()
                    }) == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmPerawat.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','" + JmPerawat.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','0','" + JmPerawat.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "TINDAKAN RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                        Sequel.AutoComitTrue();
                        tampilPr();
                        emptTextTindakan();
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 2) {
                if (KdDok2.getText().trim().equals("") || TDokter2.getText().trim().equals("")) {
                    Valid.textKosong(KdDok2, "Dokter");
                } else if (kdptg2.getText().trim().equals("") || TPerawat2.getText().trim().equals("")) {
                    Valid.textKosong(kdptg2, "Petugas");
                } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                    Valid.textKosong(TKdPrw, "perawatan");
                } else if (TKdPrw.getText().equals("2017RI1U03.A") || (TKdPrw.getText().equals("2017RI1U03.B")) || (TKdPrw.getText().equals("2017RI1U03.C"))) {
                    JOptionPane.showMessageDialog(null, "Utk. tindakan " + TNmPrw.getText() + " hanya bisa dimasukkan di pilihan penanganan petugas saja...!!");
                    btnTindakan.requestFocus();
                } else {
                    if (Sequel.menyimpantf("rawat_inap_drpr", "?,?,?,?,?,?,?,?,?,?,?,?,?", "Data", 13, new String[]{
                        TNoRw.getText(), TKdPrw.getText(), KdDok2.getText(), kdptg2.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        BagianRS.getText(), Bhp.getText(), JmDokter.getText(), JmPerawat.getText(), KSO.getText(), Menejemen.getText(), TTnd.getText()
                    }) == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmDokter.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','" + JmDokter.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','0','" + JmDokter.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmPerawat.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','" + JmPerawat.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','0','" + JmPerawat.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "TINDAKAN RAWAT INAP PASIEN " + TPasien.getText() + " DIPOSTING OLEH " + akses.getkode());
                        Sequel.AutoComitTrue();
                        tampilDrPr();
                        emptTextTindakan();
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 3) {
                tampilPemeriksaan();
                emptTextTindakan();
            }
            Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Inap','Simpan'");
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            if (TabRawat.getSelectedIndex() == 0) {
                Valid.pindah(evt, KdDok, BtnBatal);
            } else if (TabRawat.getSelectedIndex() == 1) {
                Valid.pindah(evt, kdptg, BtnBatal);
            } else if (TabRawat.getSelectedIndex() == 2) {
                Valid.pindah(evt, kdptg2, BtnBatal);
            } else if (TabRawat.getSelectedIndex() == 3) {
                Valid.pindah(evt, TGCS, BtnBatal);
            }
        }
}//GEN-LAST:event_BtnSimpanKeyPressed


    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTextTindakan();
        isForm();
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
            if (tabModeDr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                Sequel.AutoComitFalse();
                ttljmdokter = 0;
                ttljmperawat = 0;
                ttlkso = 0;
                ttlpendapatan = 0;
                for (i = 0; i < tbRawatDr.getRowCount(); i++) {
                    if (tbRawatDr.getValueAt(i, 0).toString().equals("true")) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryutf("delete from rawat_inap_dr where no_rawat='" + tbRawatDr.getValueAt(i, 1).toString()
                                    + "' and kd_jenis_prw='" + tbRawatDr.getValueAt(i, 10)
                                    + "' and kd_dokter='" + tbRawatDr.getValueAt(i, 5).toString()
                                    + "' and tgl_perawatan='" + tbRawatDr.getValueAt(i, 7).toString()
                                    + "' and jam_rawat='" + tbRawatDr.getValueAt(i, 8).toString() + "'") == true) {
                                ttljmdokter = ttljmdokter + Double.parseDouble(tbRawatDr.getValueAt(i, 11).toString());
                                ttlkso = ttlkso + Double.parseDouble(tbRawatDr.getValueAt(i, 12).toString());
                                ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatDr.getValueAt(i, 9).toString());
                            }
                        } else {
                            if (Sequel.cariRegistrasi(tbRawatDr.getValueAt(i, 1).toString()) > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            } else {
                                if (Sequel.queryutf("delete from rawat_inap_dr where no_rawat='" + tbRawatDr.getValueAt(i, 1).toString()
                                        + "' and kd_jenis_prw='" + tbRawatDr.getValueAt(i, 10)
                                        + "' and kd_dokter='" + tbRawatDr.getValueAt(i, 5).toString()
                                        + "' and tgl_perawatan='" + tbRawatDr.getValueAt(i, 7).toString()
                                        + "' and jam_rawat='" + tbRawatDr.getValueAt(i, 8).toString() + "'") == true) {
                                    ttljmdokter = ttljmdokter + Double.parseDouble(tbRawatDr.getValueAt(i, 11).toString());
                                    ttlkso = ttlkso + Double.parseDouble(tbRawatDr.getValueAt(i, 12).toString());
                                    ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatDr.getValueAt(i, 9).toString());
                                }
                            }
                        }
                    }
                }
                Sequel.queryu("delete from tampjurnal");
                if (ttlpendapatan > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + ttlpendapatan + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                }
                if (ttljmdokter > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','0','" + ttljmdokter + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','" + ttljmdokter + "','0'", "Rekening");
                }
                if (ttlkso > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + ttlkso + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + ttlkso + "','0'", "Rekening");
                }
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());
                Sequel.AutoComitTrue();
                tampilDr();
            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tabModePr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                Sequel.AutoComitFalse();
                ttljmdokter = 0;
                ttljmperawat = 0;
                ttlkso = 0;
                ttlpendapatan = 0;
                for (i = 0; i < tbRawatPr.getRowCount(); i++) {
                    if (tbRawatPr.getValueAt(i, 0).toString().equals("true")) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryutf("delete from rawat_inap_pr where no_rawat='" + tbRawatPr.getValueAt(i, 1).toString()
                                    + "' and kd_jenis_prw='" + tbRawatPr.getValueAt(i, 10)
                                    + "' and nip='" + tbRawatPr.getValueAt(i, 5).toString()
                                    + "' and tgl_perawatan='" + tbRawatPr.getValueAt(i, 7).toString()
                                    + "' and jam_rawat='" + tbRawatPr.getValueAt(i, 8).toString() + "' ") == true) {
                                ttljmperawat = ttljmperawat + Double.parseDouble(tbRawatPr.getValueAt(i, 11).toString());
                                ttlkso = ttlkso + Double.parseDouble(tbRawatPr.getValueAt(i, 12).toString());
                                ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatPr.getValueAt(i, 9).toString());
                            }
                        } else {
                            if (Sequel.cariRegistrasi(tbRawatPr.getValueAt(i, 1).toString()) > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            } else {
                                if (Sequel.queryutf("delete from rawat_inap_pr where no_rawat='" + tbRawatPr.getValueAt(i, 1).toString()
                                        + "' and kd_jenis_prw='" + tbRawatPr.getValueAt(i, 10)
                                        + "' and nip='" + tbRawatPr.getValueAt(i, 5).toString()
                                        + "' and tgl_perawatan='" + tbRawatPr.getValueAt(i, 7).toString()
                                        + "' and jam_rawat='" + tbRawatPr.getValueAt(i, 8).toString() + "' ") == true) {
                                    ttljmperawat = ttljmperawat + Double.parseDouble(tbRawatPr.getValueAt(i, 11).toString());
                                    ttlkso = ttlkso + Double.parseDouble(tbRawatPr.getValueAt(i, 12).toString());
                                    ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatPr.getValueAt(i, 9).toString());
                                }
                            }
                        }
                    }
                }
                Sequel.queryu("delete from tampjurnal");
                if (ttlpendapatan > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + ttlpendapatan + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                }
                if (ttljmperawat > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','0','" + ttljmperawat + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','" + ttljmperawat + "','0'", "Rekening");
                }
                if (ttlkso > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + ttlkso + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + ttlkso + "','0'", "Rekening");
                }
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());
                Sequel.AutoComitTrue();
                tampilPr();
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (tabModeDrPr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                Sequel.AutoComitFalse();
                ttljmdokter = 0;
                ttljmperawat = 0;
                ttlkso = 0;
                ttlpendapatan = 0;
                for (i = 0; i < tbRawatDrPr.getRowCount(); i++) {
                    if (tbRawatDrPr.getValueAt(i, 0).toString().equals("true")) {
                        if (akses.getkode().equals("Admin Utama")) {
                            if (Sequel.queryutf("delete from rawat_inap_drpr where no_rawat='" + tbRawatDrPr.getValueAt(i, 1).toString()
                                    + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(i, 12)
                                    + "' and kd_dokter='" + tbRawatDrPr.getValueAt(i, 5).toString()
                                    + "' and nip='" + tbRawatDrPr.getValueAt(i, 7).toString()
                                    + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(i, 9).toString()
                                    + "' and jam_rawat='" + tbRawatDrPr.getValueAt(i, 10).toString() + "' ") == true) {
                                ttljmdokter = ttljmdokter + Double.parseDouble(tbRawatDrPr.getValueAt(i, 13).toString());
                                ttljmperawat = ttljmperawat + Double.parseDouble(tbRawatDrPr.getValueAt(i, 14).toString());
                                ttlkso = ttlkso + Double.parseDouble(tbRawatDrPr.getValueAt(i, 15).toString());
                                ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatDrPr.getValueAt(i, 11).toString());
                            }
                        } else {
                            if (Sequel.cariRegistrasi(tbRawatDrPr.getValueAt(i, 1).toString()) > 0) {
                                JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                                TCari.requestFocus();
                            } else {
                                if (Sequel.queryutf("delete from rawat_inap_drpr where no_rawat='" + tbRawatDrPr.getValueAt(i, 1).toString()
                                        + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(i, 12)
                                        + "' and kd_dokter='" + tbRawatDrPr.getValueAt(i, 5).toString()
                                        + "' and nip='" + tbRawatDrPr.getValueAt(i, 7).toString()
                                        + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(i, 9).toString()
                                        + "' and jam_rawat='" + tbRawatDrPr.getValueAt(i, 10).toString() + "' ") == true) {
                                    ttljmdokter = ttljmdokter + Double.parseDouble(tbRawatDrPr.getValueAt(i, 13).toString());
                                    ttljmperawat = ttljmperawat + Double.parseDouble(tbRawatDrPr.getValueAt(i, 14).toString());
                                    ttlkso = ttlkso + Double.parseDouble(tbRawatDrPr.getValueAt(i, 15).toString());
                                    ttlpendapatan = ttlpendapatan + Double.parseDouble(tbRawatDrPr.getValueAt(i, 11).toString());
                                }
                            }
                        }
                    }
                }
                Sequel.queryu("delete from tampjurnal");
                if (ttlpendapatan > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + ttlpendapatan + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + ttlpendapatan + "','0'", "Rekening");
                }
                if (ttljmdokter > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','0','" + ttljmdokter + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','" + ttljmdokter + "','0'", "Rekening");
                }
                if (ttljmperawat > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','0','" + ttljmperawat + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','" + ttljmperawat + "','0'", "Rekening");
                }
                if (ttlkso > 0) {
                    Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + ttlkso + "'", "Rekening");
                    Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + ttlkso + "','0'", "Rekening");
                }
                jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());
                Sequel.AutoComitTrue();
                tampilDrPr();
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            if (tabModePemeriksaan.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else {
                for (i = 0; i < tbPemeriksaan.getRowCount(); i++) {
                    if (tbPemeriksaan.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu("delete from pemeriksaan_ranap where no_rawat='" + tbPemeriksaan.getValueAt(i, 1).toString()
                                + "' and tgl_perawatan='" + tbPemeriksaan.getValueAt(i, 4).toString()
                                + "' and jam_rawat='" + tbPemeriksaan.getValueAt(i, 5).toString() + "' ");
                    }
                }
                tampilPemeriksaan();
            }
        }
        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Inap','Hapus'");
        emptTextTindakan();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (!TCari.getText().trim().equals("")) {
            BtnCariActionPerformed(evt);
        }
        if (TabRawat.getSelectedIndex() == 0) {
            if (tabModeDr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                BtnBatal.requestFocus();
            } else if (tabModeDr.getRowCount() != 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

                String tgl = " rawat_inap_dr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                Valid.MyReport("rptInapDr.jasper", "report", "::[ Data Rawat Inap Yang Ditangani Dokter ]::",
                        "select rawat_inap_dr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "jns_perawatan_inap.nm_perawatan,rawat_inap_dr.kd_dokter,dokter.nm_dokter,"
                        + "rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat,rawat_inap_dr.biaya_rawat "
                        + "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "
                        + "dokter inner join rawat_inap_dr "
                        + "on rawat_inap_dr.no_rawat=reg_periksa.no_rawat "
                        + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter "
                        + "where " + tgl + " and rawat_inap_dr.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and jns_perawatan_inap.nm_perawatan like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and rawat_inap_dr.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and tgl_perawatan like '%" + TCari.getText().trim() + "%' "
                        + " order by rawat_inap_dr.no_rawat desc", param);

            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tabModePr.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                BtnBatal.requestFocus();
            } else if (tabModePr.getRowCount() != 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

                String tgl = " rawat_inap_pr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                Valid.MyReport("rptInapPr.jasper", "report", "::[ Data Rawat Inap Yang Ditangani Perawat ]::",
                        "select rawat_inap_pr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "jns_perawatan_inap.nm_perawatan,rawat_inap_pr.nip,petugas.nama,"
                        + "rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,rawat_inap_pr.biaya_rawat "
                        + "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "
                        + "petugas inner join rawat_inap_pr "
                        + "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "
                        + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                        + "and rawat_inap_pr.nip=petugas.nip where  "
                        + tgl + "and rawat_inap_pr.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and jns_perawatan_inap.nm_perawatan like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and rawat_inap_pr.nip like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and petugas.nama like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and rawat_inap_pr.tgl_perawatan like '%" + TCari.getText().trim() + "%'  "
                        + "order by rawat_inap_pr.no_rawat desc", param);
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
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

                String tgl = " rawat_inap_drpr.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                Valid.MyReport("rptInapDrPr.jasper", "report", "::[ Data Rawat Inap Yang Ditangani Dokter ]::",
                        "select rawat_inap_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "jns_perawatan_inap.nm_perawatan,rawat_inap_drpr.kd_dokter,dokter.nm_dokter,rawat_inap_drpr.nip,petugas.nama,"
                        + "rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.biaya_rawat "
                        + "from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "
                        + "dokter inner join rawat_inap_drpr inner join "
                        + "petugas on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "
                        + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                        + "and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter "
                        + "and rawat_inap_drpr.nip=petugas.nip "
                        + "where " + tgl + " and rawat_inap_drpr.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and jns_perawatan_inap.nm_perawatan like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and rawat_inap_drpr.kd_dokter like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and dokter.nm_dokter like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and rawat_inap_drpr.nip like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and petugas.nama like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and tgl_perawatan like '%" + TCari.getText().trim() + "%' "
                        + " order by rawat_inap_drpr.no_rawat desc", param);
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            if (tabModePemeriksaan.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
                BtnBatal.requestFocus();
            } else if (tabModePemeriksaan.getRowCount() != 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("alamatrs", akses.getalamatrs());
                param.put("kotars", akses.getkabupatenrs());
                param.put("propinsirs", akses.getpropinsirs());
                param.put("kontakrs", akses.getkontakrs());
                param.put("emailrs", akses.getemailrs());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                String pas = " and reg_periksa.no_rkm_medis like '%" + TCariPasien.getText() + "%' ";

                String tgl = " pemeriksaan_ranap.tgl_perawatan between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' " + pas;
                Valid.MyReport("rptInapPemeriksaan.jasper", "report", "::[ Data Pemeriksaan Rawat Inap ]::",
                        "select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                        + "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, "
                        + "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, "
                        + "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan, "
                        + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi from pasien inner join reg_periksa inner join pemeriksaan_ranap "
                        + "on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
                        + tgl + "and pemeriksaan_ranap.no_rawat like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and reg_periksa.no_rkm_medis like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pasien.nm_pasien like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pemeriksaan_ranap.alergi like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pemeriksaan_ranap.keluhan like '%" + TCari.getText().trim() + "%' or "
                        + tgl + "and pemeriksaan_ranap.pemeriksaan like '%" + TCari.getText().trim() + "%' "
                        + "order by pemeriksaan_ranap.no_rawat desc", param);
            }
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
        if (cmbStatus.getSelectedItem().equals("-")) {
            dispose();
        } else {
            kamar = Sequel.cariIsi("SELECT kd_kamar FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' AND stts_pulang='-' ");
            kdkamar.setText(kamar);

            isKmr();

            tgl_m = Sequel.cariIsi("SELECT tgl_masuk FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' AND stts_pulang='-' ");
            TIn.setText(tgl_m);

            jammasuk = Sequel.cariIsi("SELECT jam_masuk FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' AND stts_pulang='-' ");
            JamMasuk.setText(jammasuk);

            diag_awal = Sequel.cariIsi("SELECT diagnosa_awal FROM kamar_inap WHERE no_rawat='" + TNoRw.getText() + "' ");
            diagnosaawal.setText(diag_awal);

            lama = Sequel.cariIsiAngka("select lamajam from set_jam_minimal");
            hariawal = Sequel.cariIsi("select hariawal from set_jam_minimal");
            LblStts.setText("Pulang/Check Out");
            diagnosaakhir.setText("");
            ket.setText("-");
            date = new Date();
            now = dateFormat.format(date);
            CmbTahun.setSelectedItem(now.substring(0, 4));
            CmbBln.setSelectedItem(now.substring(5, 7));
            CmbTgl.setSelectedItem(now.substring(8, 10));
            cmbJam1.setSelectedItem(now.substring(11, 13));
            cmbMnt1.setSelectedItem(now.substring(14, 16));
            cmbDtk1.setSelectedItem(now.substring(17, 19));

            cmbJam2.setSelectedItem(now.substring(11, 13));
            cmbMnt2.setSelectedItem(now.substring(14, 16));
            cmbDtk2.setSelectedItem(now.substring(17, 19));

            tglmasuk = TIn.getText();
            jammasuk = JamMasuk.getText();

            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            isjml();

            DlgMati.setSize(659, 261);
            DlgMati.setLocationRelativeTo(internalFrame1);
            DlgMati.setVisible(true);
        }
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
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
        Thasil.setText("");
        Tinstruksi.setText("");
        tampilCppt();
        
        if (TabRawat.getSelectedIndex() == 0) {
            tampilDr();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilPr();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilDrPr();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilPemeriksaan();
        }
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnCari);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        Thasil.setText("");
        Tinstruksi.setText("");
        tampilCppt();
        
        if (!TKdPrw.getText().trim().equals("")) {
            isJns();
        }
        if (TabRawat.getSelectedIndex() == 0) {
            tampilDr();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampilPr();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampilDrPr();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampilPemeriksaan();
        }
}//GEN-LAST:event_TabRawatMouseClicked

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

    private void btnPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPasienActionPerformed
        akses.setform("DlgRawatInap");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setVisible(rootPaneCheckingEnabled);
}//GEN-LAST:event_btnPasienActionPerformed

    private void btnPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPasienKeyPressed
        Valid.pindah(evt, TCariPasien, DTPCari1);
}//GEN-LAST:event_btnPasienKeyPressed

    private void tbRawatDrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatDrMouseClicked
        if (tabModeDr.getRowCount() != 0) {
            try {
                getDataDr();
            } catch (java.lang.NullPointerException e) {
            }

        }
}//GEN-LAST:event_tbRawatDrMouseClicked

    private void tbRawatDrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatDrKeyPressed
        if (tabModeDr.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataDr();
                } catch (java.lang.NullPointerException e) {
                }
            }

        }
}//GEN-LAST:event_tbRawatDrKeyPressed

    private void tbRawatPrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRawatPrMouseClicked
        if (tabModePr.getRowCount() != 0) {
            try {
                getDataPr();
            } catch (java.lang.NullPointerException e) {
            }

        }
}//GEN-LAST:event_tbRawatPrMouseClicked

    private void tbRawatPrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRawatPrKeyPressed
        if (tabModePr.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPr();
                } catch (java.lang.NullPointerException e) {
                }
            }

        }
}//GEN-LAST:event_tbRawatPrKeyPressed

private void KdDokKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDokKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnDokterActionPerformed(null);
    } else {
        Valid.pindah(evt, TGCS, BtnSimpan);
    }
}//GEN-LAST:event_KdDokKeyPressed

private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
    akses.setform("DlgRawatInap");
    perawatan.dokter.emptTeks();
    perawatan.dokter.isCek();
    perawatan.dokter.setSize(1049, internalFrame1.getHeight() - 40);
    perawatan.dokter.setLocationRelativeTo(internalFrame1);
    perawatan.dokter.setVisible(true);
}//GEN-LAST:event_btnDokterActionPerformed

private void kdptgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptgKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        Sequel.cariIsi("select nama from petugas where nip=?", TPerawat, kdptg.getText());
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        btnPetugasActionPerformed(null);
    } else {
        Valid.pindah(evt, TGCS, BtnSimpan);
    }
}//GEN-LAST:event_kdptgKeyPressed

private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
    akses.setform("DlgRawatInap");
    perawatan.petugas.emptTeks();
    perawatan.petugas.isCek();
    perawatan.petugas.setSize(970, internalFrame1.getHeight() - 40);
    perawatan.petugas.setLocationRelativeTo(internalFrame1);
    perawatan.petugas.setVisible(true);
}//GEN-LAST:event_btnPetugasActionPerformed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
    isForm();
}//GEN-LAST:event_ChkInputActionPerformed

private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_ChkJlnActionPerformed

private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
    if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No.Rawat");
    } else {
        if (TabRawat.getSelectedIndex() == 0) {
            if (KdDok.getText().trim().equals("") || TDokter.getText().trim().equals("") || KdDok.getText().trim().equals("-") || KdDok.getText().trim().equals("--")) {
                Valid.textKosong(KdDok, "Dokter Spesialis");
            } else if (KdDok1.getText().trim().equals("") || TDokter1.getText().trim().equals("")) {
                Valid.textKosong(KdDok1, "Dokter Umum");
            } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                Valid.textKosong(TKdPrw, "perawatan");
            } else {
                if (tbRawatDr.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("rawat_inap_dr", "no_rawat='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 1)
                            + "' and kd_jenis_prw='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 10)
                            + "' and kd_dokter='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 5)
                            + "' and tgl_perawatan='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 7)
                            + "' and jam_rawat='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8) + "'",
                            "no_rawat='" + TNoRw.getText() + "',kd_jenis_prw='" + TKdPrw.getText()
                            + "',kd_dokter='" + KdDok.getText() + "',material='" + BagianRS.getText()
                            + "',bhp='" + Bhp.getText() + "',tarif_tindakandr='" + JmDokter.getText() + "',biaya_rawat='" + TTnd.getText()
                            + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                            + "kso='" + KSO.getText() + "',menejemen='" + Menejemen.getText() + "',kd_dokter_mewakili='" + KdDok1.getText() + "'") == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 9).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 9).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 9).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 11).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','0','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 11).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 11).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 12).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 12).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 12).toString() + "','0'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PERUBAHAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());

                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmDokter.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','" + JmDokter.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','0','" + JmDokter.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PERUBAHAN TINDAKAN RAWAT INAP PASIEN " + TPasien.getText());

                        Sequel.AutoComitTrue();
                        tampilDr();
                        emptTextTindakan();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }

            }
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (kdptg.getText().trim().equals("") || TPerawat.getText().trim().equals("")) {
                Valid.textKosong(kdptg, "Petugas");
            } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                Valid.textKosong(TKdPrw, "perawatan");
            } else {
                if (tbRawatPr.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("rawat_inap_pr", "no_rawat='" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 1)
                            + "' and kd_jenis_prw='" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 10)
                            + "' and nip='" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 5)
                            + "' and tgl_perawatan='" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 7)
                            + "' and jam_rawat='" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 8) + "'",
                            "no_rawat='" + TNoRw.getText() + "',kd_jenis_prw='" + TKdPrw.getText()
                            + "',nip='" + kdptg.getText() + "',material='" + BagianRS.getText()
                            + "',bhp='" + Bhp.getText() + "',tarif_tindakanpr='" + JmPerawat.getText() + "',biaya_rawat='" + TTnd.getText()
                            + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                            + "kso='" + KSO.getText() + "',menejemen='" + Menejemen.getText() + "'") == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 9).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 9).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 9).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 11).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','0','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 11).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 11).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 12).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 12).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 12).toString() + "','0'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());

                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmPerawat.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','" + JmPerawat.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','0','" + JmPerawat.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "TINDAKAN RAWAT INAP PASIEN " + TPasien.getText());

                        Sequel.AutoComitTrue();
                        tampilPr();
                        emptTextTindakan();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }
            }
        } else if (TabRawat.getSelectedIndex() == 2) {
            if (KdDok2.getText().trim().equals("") || TDokter2.getText().trim().equals("")) {
                Valid.textKosong(KdDok2, "Dokter");
            } else if (kdptg2.getText().trim().equals("") || TPerawat2.getText().trim().equals("")) {
                Valid.textKosong(kdptg2, "Petugas");
            } else if (TKdPrw.getText().trim().equals("") || TNmPrw.getText().trim().equals("")) {
                Valid.textKosong(TKdPrw, "perawatan");
            } else {
                if (tbRawatDrPr.getSelectedRow() > -1) {
                    if (Sequel.mengedittf("rawat_inap_drpr", "no_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 1)
                            + "' and kd_jenis_prw='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 12)
                            + "' and kd_dokter='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5)
                            + "' and nip='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7)
                            + "' and tgl_perawatan='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 9)
                            + "' and jam_rawat='" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10) + "'",
                            "no_rawat='" + TNoRw.getText() + "',kd_jenis_prw='" + TKdPrw.getText()
                            + "',nip='" + kdptg2.getText() + "',kd_dokter='" + KdDok2.getText() + "',material='" + BagianRS.getText()
                            + "',bhp='" + Bhp.getText() + "',tarif_tindakanpr='" + JmPerawat.getText() + "',tarif_tindakandr='" + JmDokter.getText() + "',biaya_rawat='" + TTnd.getText()
                            + "',tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                            + "kso='" + KSO.getText() + "',menejemen='" + Menejemen.getText() + "'") == true) {
                        Sequel.AutoComitFalse();
                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','0','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 11).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 13).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','0','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 13).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 13).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 14).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','0','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 14).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 14).toString() + "','0'", "Rekening");
                        }
                        if (Valid.SetAngka(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 15).toString()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','0','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 15).toString() + "'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','" + tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 15).toString() + "','0'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "PEMBATALAN TINDAKAN RAWAT INAP PASIEN OLEH " + akses.getkode());

                        Sequel.queryu("delete from tampjurnal");
                        if (Valid.SetAngka(TTnd.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Suspen_Piutang_Tindakan_Ranap + "','Suspen Piutang Tindakan Ranap','" + TTnd.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Tindakan_Ranap + "','Pendapatan Tindakan Rawat Inap','0','" + TTnd.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmDokter.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Dokter_Tindakan_Ranap + "','Beban Jasa Medik Dokter Tindakan Ranap','" + JmDokter.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Dokter_Tindakan_Ranap + "','Utang Jasa Medik Dokter Tindakan Ranap','0','" + JmDokter.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(JmPerawat.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Beban Jasa Medik Paramedis Tindakan Ranap','" + JmPerawat.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_Jasa_Medik_Paramedis_Tindakan_Ranap + "','Utang Jasa Medik Paramedis Tindakan Ranap','0','" + JmPerawat.getText() + "'", "Rekening");
                        }
                        if (Valid.SetAngka(KSO.getText()) > 0) {
                            Sequel.menyimpan("tampjurnal", "'" + Beban_KSO_Tindakan_Ranap + "','Beban KSO Tindakan Ranap','" + KSO.getText() + "','0'", "Rekening");
                            Sequel.menyimpan("tampjurnal", "'" + Utang_KSO_Tindakan_Ranap + "','Utang KSO Tindakan Ranap','0','" + KSO.getText() + "'", "Rekening");
                        }
                        jur.simpanJurnal(TNoRw.getText(), Valid.SetTgl(DTPTgl.getSelectedItem() + ""), "U", "TINDAKAN RAWAT INAP PASIEN " + TPasien.getText());

                        Sequel.AutoComitTrue();
                        tampilDrPr();
                        emptTextTindakan();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }
            }
        } else if (TabRawat.getSelectedIndex() == 3) {
            if (TKeluhan.getText().trim().equals("") && TPemeriksaan.getText().trim().equals("") && TSuhu.getText().trim().equals("") && TTensi.getText().trim().equals("")) {
                Valid.textKosong(TKeluhan, "Hasil Periksa/Perkambangan/Suhu Badan/Tensi");
            } else {
                if (tbPemeriksaan.getSelectedRow() > -1) {
                    Sequel.mengedit("pemeriksaan_ranap", "no_rawat='" + tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1)
                            + "' and tgl_perawatan='" + tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4)
                            + "' and jam_rawat='" + tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5) + "'",
                            "no_rawat='" + TNoRw.getText() + "',suhu_tubuh='" + TSuhu.getText() + "',tensi='" + TTensi.getText() + "',"
                            + "keluhan='" + TKeluhan.getText() + "',pemeriksaan='" + TPemeriksaan.getText() + "',"
                            + "nadi='" + TNadi.getText() + "',respirasi='" + TRespirasi.getText() + "',"
                            + "tinggi='" + TTinggi.getText() + "',berat='" + TBerat.getText() + "',"
                            + "gcs='" + TGCS.getText() + "',alergi='" + TAlergi.getText() + "',"
                            + "tgl_perawatan='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "',"
                            + "jam_rawat='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");
                    tampilPemeriksaan();
                    emptTextTindakan();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }
            }
        }
        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Tindakan Rawat Inap','Ganti'");
    }
}//GEN-LAST:event_BtnEditActionPerformed

private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnEditActionPerformed(null);
    } else {
        Valid.pindah(evt, BtnHapus, BtnPrint);
    }
}//GEN-LAST:event_BtnEditKeyPressed

    private void btnTindakan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTindakan2ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No.Rawat");
        } else {
            if (TabRawat.getSelectedIndex() == 0) {
                if (TDokter.getText().trim().equals("")) {
                    Valid.textKosong(KdDok, "Dokter");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan2.setPetugas(KdDok.getText(), TDokter.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan2.isCek();
                        perawatan2.tampil2();
                        perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan2.setLocationRelativeTo(internalFrame1);
                        perawatan2.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_dr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan2.setPetugas(KdDok.getText(), TDokter.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan2.isCek();
                            perawatan2.tampil2();
                            perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan2.setLocationRelativeTo(internalFrame1);
                            perawatan2.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 1) {
                if (TPerawat.getText().trim().equals("")) {
                    Valid.textKosong(kdptg, "perawat");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_pr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan2.setPetugas(kdptg.getText(), TPerawat.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan2.isCek();
                        perawatan2.tampil2();
                        perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan2.setLocationRelativeTo(internalFrame1);
                        perawatan2.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_pr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan2.setPetugas(kdptg.getText(), TPerawat.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), "", "",
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan2.isCek();
                            perawatan2.tampil2();
                            perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan2.setLocationRelativeTo(internalFrame1);
                            perawatan2.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 2) {
                if (TDokter2.getText().trim().equals("")) {
                    Valid.textKosong(KdDok2, "Dokter");
                } else if (TPerawat2.getText().trim().equals("")) {
                    Valid.textKosong(kdptg2, "perawat");
                } else {
                    if (akses.getkode().equals("Admin Utama")) {
                        perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_drpr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                        perawatan2.setPetugas(KdDok2.getText(), TDokter2.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), kdptg2.getText(), TPerawat2.getText(),
                                TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                        akses.setform("DlgRawatInap");
                        perawatan2.isCek();
                        perawatan2.tampil2();
                        perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                        perawatan2.setLocationRelativeTo(internalFrame1);
                        perawatan2.setVisible(true);
                    } else {
                        if (Sequel.cariRegistrasi(TNoRw.getText()) > 0) {
                            JOptionPane.showMessageDialog(rootPane, "Data billing sudah terverifikasi, data tidak boleh dihapus. Silahkan hubungi bagian kasir/keuangan ..!!");
                            TCari.requestFocus();
                        } else {
                            perawatan2.setNoRm(TNoRw.getText(), "rawat_inap_drpr", DTPTgl.getDate(), cmbJam.getSelectedItem().toString(), cmbMnt.getSelectedItem().toString(), cmbDtk.getSelectedItem().toString(), false, TPasien.getText());
                            perawatan2.setPetugas(KdDok2.getText(), TDokter2.getText(), TSuhu.getText(), TTensi.getText(), TKeluhan.getText(), TPemeriksaan.getText(), kdptg2.getText(), TPerawat2.getText(),
                                    TBerat.getText(), TTinggi.getText(), TNadi.getText(), TRespirasi.getText(), TGCS.getText(), TAlergi.getText());
                            akses.setform("DlgRawatInap");
                            perawatan2.isCek();
                            perawatan2.tampil2();
                            perawatan2.setSize(910, internalFrame1.getHeight() - 40);
                            perawatan2.setLocationRelativeTo(internalFrame1);
                            perawatan2.setVisible(true);
                        }
                    }
                }
            } else if (TabRawat.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih penanganan..!!!");
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_btnTindakan2ActionPerformed

    private void btnTindakan2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTindakan2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTindakan2KeyPressed

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

    private void btnSeekDokter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeekDokter2ActionPerformed
        akses.setform("DlgRawatInap");
        perawatan.dokter.emptTeks();
        perawatan.dokter.isCek();
        perawatan.dokter.setSize(1049, internalFrame1.getHeight() - 40);
        perawatan.dokter.setLocationRelativeTo(internalFrame1);
        perawatan.dokter.setVisible(true);
    }//GEN-LAST:event_btnSeekDokter2ActionPerformed

    private void btnSeekPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeekPetugas2ActionPerformed
        akses.setform("DlgRawatInap");
        perawatan.petugas.emptTeks();
        perawatan.petugas.isCek();
        perawatan.petugas.setSize(970, internalFrame1.getHeight() - 40);
        perawatan.petugas.setLocationRelativeTo(internalFrame1);
        perawatan.petugas.setVisible(true);
    }//GEN-LAST:event_btnSeekPetugas2ActionPerformed

    private void KdDok2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter2, KdDok2.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnSeekDokter2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TGCS, kdptg2);
        }
    }//GEN-LAST:event_KdDok2KeyPressed

    private void kdptg2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdptg2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Sequel.cariIsi("select nama from petugas where nip=?", TPerawat2, kdptg2.getText());
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            btnSeekPetugas2ActionPerformed(null);
        } else {
            Valid.pindah(evt, KdDok2, BtnSimpan);
        }
    }//GEN-LAST:event_kdptg2KeyPressed

    private void tbPemeriksaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemeriksaanMouseClicked
        if (tabModePemeriksaan.getRowCount() != 0) {
            try {
                getDataPemeriksaan();
            } catch (java.lang.NullPointerException e) {
            }

        }
    }//GEN-LAST:event_tbPemeriksaanMouseClicked

    private void tbPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemeriksaanKeyPressed
        if (tabModePemeriksaan.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataPemeriksaan();
                } catch (java.lang.NullPointerException e) {
                }
            }

        }
    }//GEN-LAST:event_tbPemeriksaanKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        Valid.pindah(evt, TPemeriksaan, TSuhu);
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TGCSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TGCSKeyPressed
        if (TabRawat.getSelectedIndex() == 0) {
            Valid.pindah(evt, TRespirasi, KdDok);
        } else if (TabRawat.getSelectedIndex() == 1) {
            Valid.pindah(evt, TRespirasi, kdptg);
        } else if (TabRawat.getSelectedIndex() == 2) {
            Valid.pindah(evt, TRespirasi, KdDok2);
        } else if (TabRawat.getSelectedIndex() == 3) {
            Valid.pindah(evt, TRespirasi, BtnSimpan);
        }
    }//GEN-LAST:event_TGCSKeyPressed

    private void TRespirasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRespirasiKeyPressed
        Valid.pindah(evt, TNadi, TGCS);
    }//GEN-LAST:event_TRespirasiKeyPressed

    private void TNadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNadiKeyPressed
        Valid.pindah(evt, TTinggi, TRespirasi);
    }//GEN-LAST:event_TNadiKeyPressed

    private void TBeratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBeratKeyPressed
        Valid.pindah(evt, TTensi, TTinggi);
    }//GEN-LAST:event_TBeratKeyPressed

    private void TNadiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TNadiActionPerformed

    }//GEN-LAST:event_TNadiActionPerformed

    private void TTinggiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TTinggiKeyPressed
        Valid.pindah(evt, TBerat, TNadi);
    }//GEN-LAST:event_TTinggiKeyPressed

    private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
        if (TPasien.getText().trim().equals("") || TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            TCari.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            DlgPemberianObat dlgrwinap = new DlgPemberianObat(null, false);
            dlgrwinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            dlgrwinap.setLocationRelativeTo(internalFrame1);
            dlgrwinap.isCek();
            dlgrwinap.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate(), "ranap");
            dlgrwinap.tampilPO();
            dlgrwinap.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPemberianObatActionPerformed

    private void MnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDiagnosaActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            TCari.requestFocus();
        } else {
            DlgDiagnosaPenyakit resep = new DlgDiagnosaPenyakit(null, false);
            resep.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            resep.setLocationRelativeTo(internalFrame1);
            resep.isCek();
            resep.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "Ranap");
            resep.tampilDiagStatistik();
            resep.tampilDiagInadrg();
            resep.setVisible(true);
        }
    }//GEN-LAST:event_MnDiagnosaActionPerformed

    private void BtnSimpanMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanMatiActionPerformed

        kdDiag = "";
        tgm = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
        date = new Date();
        date2 = new Date();
        now = dateFormat2.format(date);

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(tgm);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(now);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Data tidak bisa disimpan, Tanggal Pulang Tidak Valid !!!");
        }

        if (TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (TKdBngsal.getText().trim().equals("")) {
            Valid.textKosong(kdkamar, "kamar");
        } else if (Double.parseDouble(TJmlHari.getText().trim()) < 0) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Cek Tanggal yang Anda Input !!!");
        } else if (date.after(date2)) {
            JOptionPane.showMessageDialog(null, "Data Tidak Bisa disimpan, Tanggal Pulang Tidak Valid !!!");
        } else {
            if (TNoRw.isEditable() == true) {
                switch (TSttsKamar.getText().trim()) {
                    case "ISI":
                        JOptionPane.showMessageDialog(null, "Maaf, Status kamar sudah terisi silahkan pilih kamar kosong..!!!!");
                        kdkamar.requestFocus();
                        break;
                    case "KOSONG":

                        Sequel.menyimpan("kamar_inap", "'" + TNoRw.getText() + "','"
                                + kdkamar.getText() + "','" + TTarif.getText() + "','"
                                + diagnosaawal.getText() + "','"
                                + diagnosaakhir.getText() + "','"
                                + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + "','"
                                + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "','0000-00-00','00:00:00','" + TJmlHari.getText() + "','"
                                + ttlbiaya.getText() + "','-'", "No.Rawat");
                        Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "status_lanjut='Ranap'");
                        Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='ISI'");
                        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Masuk Kamar Inap','Simpan'");

                        emptTeks();
                        break;
                }
                TNoRw.requestFocus();
            } else if (TNoRw.isEditable() == false) {
                if (cmbStatus.getSelectedItem().equals("-")) {
                    Valid.textKosong(cmbStatus, "Status Pulang");
                } else if (diagnosaakhir.getText().trim().equals("")) {
                    Valid.textKosong(diagnosaakhir, "Diagnosa Akhir");
                } else {
                    for (int i = 0; i < diagnosaakhir.getText().trim().length(); i++) {
                        if (i == diagnosaakhir.getText().length() - 1) {
                            kdDiag = kdDiag + diagnosaakhir.getText().substring(i, i + 1);
                            cekDb = Sequel.cariInteger("select count(-1) from penyakit where kd_penyakit =?", kdDiag.trim());
                            if (cekDb == 0) {
                                cekAda = cekAda + 1;
                            }
                            kdDiag = "";
                        } else if (diagnosaakhir.getText().substring(i, i + 1).equals(",")) {
                            cekDb = Sequel.cariInteger("select count(-1) from penyakit where kd_penyakit =?", kdDiag.trim());
                            if (cekDb == 0) {
                                cekAda = cekAda + 1;
                            }
                            kdDiag = "";
                        } else {
                            kdDiag = kdDiag + diagnosaakhir.getText().substring(i, i + 1);
                        }
                    }
                    if (cekAda > 0) {
                        JOptionPane.showMessageDialog(null, "Maaf, Kode Diagnosa Akhir Salah, Silakan Input Ulang......");
                        diagnosaakhir.setText("");
                        diagnosaakhir.requestFocus();
                        cekAda = 0;
                    } else {
                        Sequel.mengedit("kamar_inap", "no_rawat='" + TNoRw.getText() + "' and kd_kamar='" + kdkamar.getText() + "' and tgl_masuk='" + TIn.getText() + "' and jam_masuk='" + JamMasuk.getText() + "'",
                                "tgl_keluar='" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem()
                                + "',trf_kamar='" + TTarif.getText() + "',jam_keluar='" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem()
                                + "',ttl_biaya='" + ttlbiaya.getText() + "',stts_pulang='" + cmbStatus.getSelectedItem() + "',diagnosa_akhir='" + diagnosaakhir.getText() + "',lama='" + TJmlHari.getText() + "'");

                        if (cmbStatus.getSelectedItem().equals("Meninggal >= 48 Jam") || (cmbStatus.getSelectedItem().equals("Meninggal < 48 Jam"))) {
                            Sequel.menyimpan("pasien_mati", "'" + Valid.SetTgl(TglMati.getSelectedItem() + "") + "','"
                                    + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "','"
                                    + TNoRM.getText() + "','" + cmbStatus.getSelectedItem() + " diruang " + TBangsal.getText() + " - "
                                    + ket.getText() + "','Rumah Sakit','-','-','-','-','Ruangan Inap','-','',''", "pasien");

                        } else if (cmbStatus.getSelectedItem().equals("Dirujuk")) {
                            DlgRujuk dlgrjk = new DlgRujuk(null, false);
                            dlgrjk.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                            dlgrjk.setLocationRelativeTo(internalFrame1);
                            dlgrjk.emptTeks();
                            dlgrjk.isCek();
                            dlgrjk.setNoRm(TNoRw.getText(), DTPCari1.getDate(), DTPCari2.getDate());
                            dlgrjk.tampilLain();
                            dlgrjk.setVisible(true);
                        }
                        Sequel.mengedit("kamar", "kd_kamar='" + kdkamar.getText() + "'", "status='KOSONG'");
                        Sequel.menyimpan("history_user", "Now(),'" + TNoRw.getText() + "','" + akses.getkode() + "','Dari kamar inap pasien pulang meninggal','Simpan'");

                        x = JOptionPane.showConfirmDialog(null, "Apakah anda akan mencetak surat keterangan kematian ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                        if (x == JOptionPane.YES_OPTION) {
                            ctkSuratMati();
                            DlgMati.dispose();
                            emptTeks();
                            dispose();

                        } else {
                            DlgMati.dispose();
                            emptTeks();
                            dispose();

                        }
                    }

                }
            }
            //tampil();
        }
    }//GEN-LAST:event_BtnSimpanMatiActionPerformed

    private void BtnSimpanMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpanMatiKeyPressed

    private void ketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ketKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ketKeyPressed

    private void TglMatiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TglMatiItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiItemStateChanged

    private void TglMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TglMatiKeyPressed

    private void cmbJam2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam2KeyPressed
        Valid.pindah(evt, cmbJam2, cmbMnt2);
    }//GEN-LAST:event_cmbJam2KeyPressed

    private void cmbMnt2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt2KeyPressed
        Valid.pindah(evt, cmbMnt2, cmbDtk2);
    }//GEN-LAST:event_cmbMnt2KeyPressed

    private void cmbDtk2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk2KeyPressed
        Valid.pindah(evt, cmbDtk2, CmbTgl);
    }//GEN-LAST:event_cmbDtk2KeyPressed

    private void kdkamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdkamarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            i = 1;
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CmbTahun.requestFocus();
            isKmr();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            TNoRw.requestFocus();
            isKmr();
        }
    }//GEN-LAST:event_kdkamarKeyPressed

    private void TBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TBangsalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TBangsalKeyPressed

    private void CmbTglCmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbTglCmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_CmbTglCmbTahunItemStateChanged

    private void CmbTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbTglKeyPressed
        Valid.pindah(evt, CmbTgl, CmbBln);
    }//GEN-LAST:event_CmbTglKeyPressed

    private void CmbBlnCmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbBlnCmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_CmbBlnCmbTahunItemStateChanged

    private void CmbBlnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbBlnKeyPressed
        Valid.pindah(evt, CmbBln, CmbTahun);
    }//GEN-LAST:event_CmbBlnKeyPressed

    private void CmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_CmbTahunItemStateChanged

    private void CmbTahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbTahunKeyPressed
        Valid.pindah(evt, CmbTahun, cmbJam1);
    }//GEN-LAST:event_CmbTahunKeyPressed

    private void cmbJam1CmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbJam1CmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_cmbJam1CmbTahunItemStateChanged

    private void cmbJam1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJam1KeyPressed
        Valid.pindah(evt, cmbJam1, cmbMnt1);
    }//GEN-LAST:event_cmbJam1KeyPressed

    private void cmbMnt1CmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMnt1CmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_cmbMnt1CmbTahunItemStateChanged

    private void cmbMnt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMnt1KeyPressed
        Valid.pindah(evt, cmbMnt1, cmbDtk1);
    }//GEN-LAST:event_cmbMnt1KeyPressed

    private void cmbDtk1CmbTahunItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbDtk1CmbTahunItemStateChanged
        if ((DlgMati.isVisible() == true) && (!TBangsal.getText().equals("")) && (!TNoRw.getText().equals(""))) {
            if (TIn.getText().equals("")) {
                tglmasuk = CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem();
                jammasuk = cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem();
            } else {
                tglmasuk = TIn.getText();
                jammasuk = JamMasuk.getText();
            }
            if (hariawal.equals("Yes")) {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))+1) as lama", TJmlHari);
            } else {
                Sequel.cariIsi("select (if(to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "')=0,if(time_to_sec('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-time_to_sec('" + tglmasuk + " " + jammasuk + "')>(3600*" + lama + "),1,0),to_days('" + CmbTahun.getSelectedItem() + "-" + CmbBln.getSelectedItem() + "-" + CmbTgl.getSelectedItem() + " " + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "')-to_days('" + tglmasuk + " " + jammasuk + "'))) as lama", TJmlHari);
            }
            //Sequel.cariIsi("select (if(to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"')=0,if(time_to_sec('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-time_to_sec('"+tglmasuk+" "+jammasuk+"')>(3600*"+lama+"),1,0),to_days('"+CmbTahun.getSelectedItem()+"-"+CmbBln.getSelectedItem()+"-"+CmbTgl.getSelectedItem()+" "+cmbJam.getSelectedItem()+":"+cmbMnt.getSelectedItem()+":"+cmbDtk.getSelectedItem()+"')-to_days('"+tglmasuk+" "+jammasuk+"'))+1) as lama",TJmlHari);
        }
    }//GEN-LAST:event_cmbDtk1CmbTahunItemStateChanged

    private void cmbDtk1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtk1KeyPressed
        Valid.pindah(evt, cmbDtk1, btnDiagnosa);
    }//GEN-LAST:event_cmbDtk1KeyPressed

    private void diagnosaakhirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosaakhirKeyPressed
        Valid.pindah(evt, cmbStatus, BtnSimpan);
    }//GEN-LAST:event_diagnosaakhirKeyPressed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu pasien...!!!");
            diagnosaakhir.requestFocus();
        } else {
            akses.setform("DlgRawatInap");
            diagnosa.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
            diagnosa.setLocationRelativeTo(internalFrame1);
            diagnosa.isCek();            
            diagnosa.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "Ranap");
            diagnosa.tampilDiagStatistik();
            diagnosa.tampilDiagInadrg();
            diagnosa.setVisible(true);
        }
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    private void btnDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnDiagnosaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDiagnosaKeyPressed

    private void ttlbiayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ttlbiayaKeyPressed
        // Valid.pindah(evt,TKdOb,BtnSimpan);
    }//GEN-LAST:event_ttlbiayaKeyPressed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed

    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbStatusKeyPressed

    }//GEN-LAST:event_cmbStatusKeyPressed

    private void JamMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JamMasukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JamMasukKeyPressed

    private void diagnosaawalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosaawalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diagnosaawalKeyPressed

    private void cmbJam1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJam1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbJam1ActionPerformed

    private void BtnBatalMatiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalMatiActionPerformed
        cmbStatus.setSelectedItem("-");
        DlgMati.dispose();
        emptTeks();
    }//GEN-LAST:event_BtnBatalMatiActionPerformed

    private void BtnBatalMatiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalMatiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBatalMatiKeyPressed

    private void KdDok1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdDok1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KdDok1KeyPressed

    private void btnDokter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokter1ActionPerformed
        akses.setform("DlgRawatInap");
        dokter_umum.emptTeks();
        dokter_umum.isCek();
        dokter_umum.setSize(1049, internalFrame1.getHeight() - 40);
        dokter_umum.setLocationRelativeTo(internalFrame1);
        dokter_umum.setVisible(true);
    }//GEN-LAST:event_btnDokter1ActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if(tabModeCppt.getRowCount()!=0){
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if(tabModeCppt.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Thasil.setText("");
        Tinstruksi.setText("");
        tampilCppt();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRawatInap dialog = new DlgRawatInap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnBatalMati;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanMati;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.ComboBox CmbBln;
    private widget.ComboBox CmbTahun;
    private widget.ComboBox CmbTgl;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private javax.swing.JDialog DlgMati;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JamMasuk;
    private javax.swing.JTextField JmDokter;
    private javax.swing.JTextField JmPerawat;
    private javax.swing.JTextField KSO;
    private widget.TextBox KdDok;
    private widget.TextBox KdDok1;
    private widget.TextBox KdDok2;
    private widget.Label LCount;
    private widget.Label LblStts;
    private javax.swing.JTextField Menejemen;
    private javax.swing.JMenuItem MnDiagnosa;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.TextBox TAlergi;
    private widget.TextBox TBangsal;
    private widget.TextBox TBerat;
    private widget.TextBox TCari;
    private widget.TextBox TCariPasien;
    private widget.TextBox TDokter;
    private widget.TextBox TDokter1;
    private widget.TextBox TDokter2;
    private widget.TextBox TGCS;
    private javax.swing.JTextField TIn;
    private widget.TextBox TJmlHari;
    private widget.TextBox TKdBngsal;
    private widget.TextBox TKdPrw;
    private widget.TextBox TKeluhan;
    private widget.TextBox TNadi;
    private widget.TextBox TNmPrw;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private javax.swing.JTextField TOut;
    private widget.TextBox TPasien;
    private widget.TextBox TPemeriksaan;
    private widget.TextBox TPerawat;
    private widget.TextBox TPerawat2;
    private widget.TextBox TRespirasi;
    private widget.TextBox TRuangan;
    private widget.TextBox TSttsKamar;
    private widget.TextBox TSuhu;
    private widget.TextBox TTarif;
    private widget.TextBox TTensi;
    private widget.TextBox TTinggi;
    private javax.swing.JTextField TTnd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal TglMati;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tjam_rawat;
    private widget.TextBox Ttgl_rawat;
    private widget.Button btnDiagnosa;
    private widget.Button btnDokter;
    private widget.Button btnDokter1;
    private widget.Button btnPasien;
    private widget.Button btnPetugas;
    private widget.Button btnSeekDokter2;
    private widget.Button btnSeekPetugas2;
    private widget.Button btnTindakan;
    private widget.Button btnTindakan2;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbStatus;
    private widget.TextBox diagnosaakhir;
    private widget.TextBox diagnosaawal;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator5;
    private widget.TextBox kdkamar;
    private widget.TextBox kdptg;
    private widget.TextBox kdptg2;
    private widget.TextBox ket;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbPemeriksaan;
    private widget.Table tbRawatDr;
    private widget.Table tbRawatDrPr;
    private widget.Table tbRawatPr;
    private widget.TextBox ttlbiaya;
    // End of variables declaration//GEN-END:variables

    public void tampilDr() {
        Valid.tabelKosong(tabModeDr);
        try {
            ps = koneksi.prepareStatement("SELECT rid.no_rawat, rp.no_rkm_medis, p.nm_pasien, ji.nm_perawatan, rid.kd_dokter, d1.nm_dokter,  "
                    + "rid.tgl_perawatan, rid.jam_rawat, rid.biaya_rawat, rid.kd_jenis_prw, rid.tarif_tindakandr,  "
                    + "rid.kso, IFNULL(d2.nm_dokter,'-') dokter2, IF(rid.kd_dokter_mewakili='','-',IFNULL(rid.kd_dokter_mewakili,'-')) kd_dokter_mewakili  "
                    + "FROM rawat_inap_dr rid INNER JOIN reg_periksa rp on rp.no_rawat=rid.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN jns_perawatan_inap ji on ji.kd_jenis_prw=rid.kd_jenis_prw "
                    + "INNER JOIN dokter d1 on d1.kd_dokter=rid.kd_dokter LEFT JOIN dokter d2 on d2.kd_dokter=rid.kd_dokter_mewakili "
                    + "WHERE rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND rid.no_rawat LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND rp.no_rkm_medis LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND p.nm_pasien LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND ji.nm_perawatan LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND rid.kd_dokter LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND d1.nm_dokter LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND d2.nm_dokter LIKE ? OR  "
                    + "rid.tgl_perawatan BETWEEN ? AND ? AND rp.no_rkm_medis LIKE ? AND tgl_perawatan LIKE ?  "
                    + "ORDER BY rid.no_rawat DESC");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCariPasien.getText() + "%");
                ps.setString(4, "%" + TCari.getText().trim() + "%");
                ps.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(7, "%" + TCariPasien.getText() + "%");
                ps.setString(8, "%" + TCari.getText().trim() + "%");
                ps.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(11, "%" + TCariPasien.getText() + "%");
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCariPasien.getText() + "%");
                ps.setString(16, "%" + TCari.getText().trim() + "%");
                ps.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(19, "%" + TCariPasien.getText() + "%");
                ps.setString(20, "%" + TCari.getText().trim() + "%");
                ps.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(23, "%" + TCariPasien.getText() + "%");
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCariPasien.getText() + "%");
                ps.setString(28, "%" + TCari.getText().trim() + "%");                
                ps.setString(29, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(30, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(31, "%" + TCariPasien.getText() + "%");
                ps.setString(32, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabModeDr.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getDouble(9), rs.getString("kd_jenis_prw"), rs.getString("tarif_tindakandr"), rs.getString("kso"),
                        rs.getString("dokter2"),rs.getString("kd_dokter_mewakili")
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
        LCount.setText("" + tabModeDr.getRowCount());
    }

    private void getDataDr() {
        if (tbRawatDr.getSelectedRow() != -1) {
            TNoRw.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 1).toString());
            TNoRM.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 2).toString());
            TPasien.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 3).toString());
            KdDok.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 5).toString());
            TDokter.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 6).toString());            
            cmbJam.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 7).toString());
            Sequel.cariIsi("select kd_jenis_prw from jns_perawatan_inap where nm_perawatan=? ", TKdPrw, tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 4).toString());
            
//            KdDok1.setText(Sequel.cariIsi("select kd_dokter_mewakili from rawat_inap_dr where no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + TKdPrw.getText() + "' "
//                    + "and tgl_perawatan='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 7).toString() + "' "
//                    + "and jam_rawat='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8).toString() + "'"));

            KdDok1.setText(tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 14).toString());

//            if (KdDok1.getText().equals("")) {
//                KdDok1.setText("-");
//            } else {
//                KdDok1.setText(Sequel.cariIsi("select kd_dokter_mewakili from rawat_inap_dr where no_rawat='" + TNoRw.getText() + "' and kd_jenis_prw='" + TKdPrw.getText() + "' "
//                        + "and tgl_perawatan='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 7).toString() + "' "
//                        + "and jam_rawat='" + tbRawatDr.getValueAt(tbRawatDr.getSelectedRow(), 8).toString() + "'"));
//            }
            TDokter1.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + KdDok1.getText() + "'"));
            isJns();
        }
    }

    private void tampilPr() {
        Valid.tabelKosong(tabModePr);
        try {
            ps2 = koneksi.prepareStatement("select rawat_inap_pr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "jns_perawatan_inap.nm_perawatan,rawat_inap_pr.nip,petugas.nama,"
                    + "rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat,rawat_inap_pr.biaya_rawat,rawat_inap_pr.kd_jenis_prw, "
                    + "rawat_inap_pr.tarif_tindakanpr,rawat_inap_pr.kso from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "
                    + "petugas inner join rawat_inap_pr "
                    + "on rawat_inap_pr.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                    + "and rawat_inap_pr.nip=petugas.nip where  "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.no_rawat like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and jns_perawatan_inap.nm_perawatan like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.nip like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and petugas.nama like ? or "
                    + "rawat_inap_pr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_pr.tgl_perawatan like ? "
                    + "order by rawat_inap_pr.no_rawat desc");
            try {
                ps2.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(3, "%" + TCariPasien.getText() + "%");
                ps2.setString(4, "%" + TCari.getText().trim() + "%");
                ps2.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(7, "%" + TCariPasien.getText() + "%");
                ps2.setString(8, "%" + TCari.getText().trim() + "%");
                ps2.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(11, "%" + TCariPasien.getText() + "%");
                ps2.setString(12, "%" + TCari.getText().trim() + "%");
                ps2.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(15, "%" + TCariPasien.getText() + "%");
                ps2.setString(16, "%" + TCari.getText().trim() + "%");
                ps2.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(19, "%" + TCariPasien.getText() + "%");
                ps2.setString(20, "%" + TCari.getText().trim() + "%");
                ps2.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(23, "%" + TCariPasien.getText() + "%");
                ps2.setString(24, "%" + TCari.getText().trim() + "%");
                ps2.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps2.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps2.setString(27, "%" + TCariPasien.getText() + "%");
                ps2.setString(28, "%" + TCari.getText().trim() + "%");
                rs = ps2.executeQuery();
                while (rs.next()) {
                    tabModePr.addRow(new Object[]{false, rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getDouble(9),
                        rs.getString("kd_jenis_prw"),
                        rs.getString("tarif_tindakanpr"),
                        rs.getString("kso")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabModePr.getRowCount());
    }

    private void getDataPr() {
        if (tbRawatPr.getSelectedRow() != -1) {
            TNoRw.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 1).toString());
            TNoRM.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 2).toString());
            TPasien.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 3).toString());
            TNmPrw.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 4).toString());
            kdptg.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 5).toString());
            TPerawat.setText(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 6).toString());
            cmbJam.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 8).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 8).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 8).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 7).toString());
            Sequel.cariIsi("select kd_jenis_prw from jns_perawatan_inap where nm_perawatan=? ", TKdPrw, tbRawatPr.getValueAt(tbRawatPr.getSelectedRow(), 4).toString());
            isJns();
        }
    }

    public void tampilDrPr() {
        Valid.tabelKosong(tabModeDrPr);
        try {
            ps3 = koneksi.prepareStatement("select rawat_inap_drpr.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "jns_perawatan_inap.nm_perawatan,rawat_inap_drpr.kd_dokter,dokter.nm_dokter,"
                    + "rawat_inap_drpr.nip,petugas.nama,rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat,rawat_inap_drpr.biaya_rawat,rawat_inap_drpr.kd_jenis_prw, "
                    + "rawat_inap_drpr.tarif_tindakandr,rawat_inap_drpr.tarif_tindakanpr,rawat_inap_drpr.kso from pasien inner join reg_periksa inner join jns_perawatan_inap inner join "
                    + "dokter inner join rawat_inap_drpr inner join petugas on rawat_inap_drpr.no_rawat=reg_periksa.no_rawat "
                    + "and reg_periksa.no_rkm_medis=pasien.no_rkm_medis and rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                    + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter and rawat_inap_drpr.nip=petugas.nip "
                    + "where rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.no_rawat like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and jns_perawatan_inap.nm_perawatan like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.kd_dokter like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and dokter.nm_dokter like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and rawat_inap_drpr.nip like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and petugas.nama like ? or "
                    + "rawat_inap_drpr.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and tgl_perawatan like ?  "
                    + " order by rawat_inap_drpr.no_rawat desc");
            try {
                ps3.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(3, "%" + TCariPasien.getText() + "%");
                ps3.setString(4, "%" + TCari.getText().trim() + "%");
                ps3.setString(5, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(6, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(7, "%" + TCariPasien.getText() + "%");
                ps3.setString(8, "%" + TCari.getText().trim() + "%");
                ps3.setString(9, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(10, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(11, "%" + TCariPasien.getText() + "%");
                ps3.setString(12, "%" + TCari.getText().trim() + "%");
                ps3.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(15, "%" + TCariPasien.getText() + "%");
                ps3.setString(16, "%" + TCari.getText().trim() + "%");
                ps3.setString(17, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(18, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(19, "%" + TCariPasien.getText() + "%");
                ps3.setString(20, "%" + TCari.getText().trim() + "%");
                ps3.setString(21, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(22, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(23, "%" + TCariPasien.getText() + "%");
                ps3.setString(24, "%" + TCari.getText().trim() + "%");
                ps3.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(27, "%" + TCariPasien.getText() + "%");
                ps3.setString(28, "%" + TCari.getText().trim() + "%");
                ps3.setString(29, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(30, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(31, "%" + TCariPasien.getText() + "%");
                ps3.setString(32, "%" + TCari.getText().trim() + "%");
                ps3.setString(33, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps3.setString(34, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps3.setString(35, "%" + TCariPasien.getText() + "%");
                ps3.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps3.executeQuery();
                while (rs.next()) {
                    tabModeDrPr.addRow(new Object[]{
                        false, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10), rs.getDouble(11), rs.getString("kd_jenis_prw"),
                        rs.getString("tarif_tindakandr"), rs.getString("tarif_tindakanpr"), rs.getString("kso")
                    });
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
            TPasien.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 3).toString());
            KdDok2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 5).toString());
            TDokter2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 6).toString());
            kdptg2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 7).toString());
            TPerawat2.setText(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 8).toString());
            cmbJam.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 10).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 9).toString());
            Sequel.cariIsi("select kd_jenis_prw from jns_perawatan_inap where nm_perawatan=? ", TKdPrw, tbRawatDrPr.getValueAt(tbRawatDrPr.getSelectedRow(), 4).toString());
            isJns();
        }
    }

    private void isRawat() {
        Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat=? ", TNoRM, TNoRw.getText());
    }

    private void isPsien() {
        Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis=? ", TPasien, TNoRM.getText());
    }

    private void isJns() {
        Sequel.cariIsi("select nm_perawatan from jns_perawatan_inap where kd_jenis_prw=? ", TNmPrw, TKdPrw.getText());
        Sequel.cariIsi("select bhp from jns_perawatan_inap where kd_jenis_prw=? ", Bhp, TKdPrw.getText());
        Sequel.cariIsi("select material from jns_perawatan_inap where kd_jenis_prw=? ", BagianRS, TKdPrw.getText());
        Sequel.cariIsi("select tarif_tindakandr from jns_perawatan_inap where kd_jenis_prw=? ", JmDokter, TKdPrw.getText());
        Sequel.cariIsi("select tarif_tindakanpr from jns_perawatan_inap where kd_jenis_prw=? ", JmPerawat, TKdPrw.getText());
        Sequel.cariIsi("select kso from jns_perawatan_inap where kd_jenis_prw=? ", KSO, TKdPrw.getText());
        Sequel.cariIsi("select menejemen from jns_perawatan_inap where kd_jenis_prw=? ", Menejemen, TKdPrw.getText());
        if (TabRawat.getSelectedIndex() == 0) {
            Sequel.cariIsi("select total_byrdr from jns_perawatan_inap where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        } else if (TabRawat.getSelectedIndex() == 1) {
            Sequel.cariIsi("select total_byrpr from jns_perawatan_inap where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        } else if (TabRawat.getSelectedIndex() == 2) {
            Sequel.cariIsi("select total_byrdrpr from jns_perawatan_inap where kd_jenis_prw=? ", TTnd, TKdPrw.getText());
        }
    }

    public void setNoRm(String norwt, Date awal, Date akhir) {
        cekdpjp = "";
        cekdpjp = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat=?", norwt);
        
        if (cekdpjp.equals("")) {
            KdDok.setText("-");
            TDokter.setText("-");
        } else {
            KdDok.setText(Sequel.cariIsi("select kd_dokter from dokter where kd_dokter='" + cekdpjp + "'"));
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter, KdDok.getText());
        }
        
        TNoRw.setText(norwt);
        KdDok2.setText(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat=?", norwt));        
        KdDok1.setText("-");
        TDokter1.setText("-");
        isRawat();
        isPsien();
        DTPCari1.setDate(awal);
        DTPCari2.setDate(akhir);
        TCari.setText(norwt);        
        Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", TDokter2, KdDok2.getText());
        ChkInput.setSelected(true);
        isForm();
        TCari.setText(TNoRw.getText());

        tampilDr();
        tampilPr();
        tampilDrPr();
    }

    public void setStatus(String stat_mati) {
        cmbStatus.setSelectedItem(stat_mati);
    }

    public void setHidup(String stat_hidup) {
        cmbStatus.setSelectedItem("-");
    }

    public void setpetugas() {
        if (akses.getkode().equals("Admin Utama")) {
            kdptg.setText("");
            TPerawat.setText("");
            kdptg2.setText("");
            TPerawat2.setText("");
        } else {
            kdptg.setText(akses.getkode());
            TPerawat.setText(akses.getnamauser());
            kdptg2.setText(akses.getkode());
            TPerawat2.setText(akses.getnamauser());
        }
    }

    private void isForm() {
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 150));
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
        BtnSimpan.setEnabled(akses.gettindakan_ranap());
        BtnHapus.setEnabled(akses.gettindakan_ranap());
        BtnEdit.setEnabled(akses.gettindakan_ranap());
        BtnPrint.setEnabled(akses.gettindakan_ranap());

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

    private void tampilPemeriksaan() {
        Valid.tabelKosong(tabModePemeriksaan);
        try {
            ps4 = koneksi.prepareStatement("select pemeriksaan_ranap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,"
                    + "pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat,pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi, "
                    + "pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,pemeriksaan_ranap.tinggi, "
                    + "pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan, "
                    + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi from pasien inner join reg_periksa inner join pemeriksaan_ranap "
                    + "on pemeriksaan_ranap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis where  "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.no_rawat like ? or "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and reg_periksa.no_rkm_medis like ? or "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pasien.nm_pasien like ? or  "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.alergi like ? or "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.keluhan like ? or "
                    + "pemeriksaan_ranap.tgl_perawatan between ? and ? and reg_periksa.no_rkm_medis like ? and pemeriksaan_ranap.pemeriksaan like ? "
                    + "order by pemeriksaan_ranap.no_rawat desc");
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
                rs = ps4.executeQuery();
                while (rs.next()) {
                    tabModePemeriksaan.addRow(new Object[]{false, rs.getString(1),
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
                        rs.getString(15)
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
        LCount.setText("" + tabModePemeriksaan.getRowCount());
    }

    private void getDataPemeriksaan() {
        if (tbPemeriksaan.getSelectedRow() != -1) {
            TNoRw.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 2).toString());
            TPasien.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 3).toString());
            TSuhu.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 6).toString());
            TTensi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 7).toString());
            TNadi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 8).toString());
            TRespirasi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 9).toString());
            TTinggi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 10).toString());
            TBerat.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 11).toString());
            TGCS.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 12).toString());
            TKeluhan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 13).toString());
            TPemeriksaan.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 14).toString());
            TAlergi.setText(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 15).toString());
            cmbJam.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 5).toString().substring(6, 8));
            Valid.SetTgl(DTPTgl, tbPemeriksaan.getValueAt(tbPemeriksaan.getSelectedRow(), 4).toString());
        }
    }

    private void isjml() {
        DecimalFormat df2 = new DecimalFormat("####");
        if ((!TJmlHari.getText().equals("")) && (!TTarif.getText().equals(""))) {
            double x = Double.parseDouble(TJmlHari.getText().trim());
            double y = Double.parseDouble(TTarif.getText().trim());
            ttlbiaya.setText(df2.format(x * y));
        }
    }

    public void emptTeks() {
        ket.setText("-");
        TglMati.setDate(new Date());
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        kdkamar.setText("");
        TKdBngsal.setText("");
        TBangsal.setText("");
        diagnosaakhir.setText("");
        TSttsKamar.setText("");
        cmbStatus.setSelectedIndex(0);
        TJmlHari.setText("0");
        TTarif.setText("0");
        ttlbiaya.setText("0");
    }

    public void ctkSuratMati() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("alamatrs", akses.getalamatrs());
        param.put("kotars", akses.getkabupatenrs());
        param.put("propinsirs", akses.getpropinsirs());
        param.put("kontakrs", akses.getkontakrs());
        param.put("emailrs", akses.getemailrs());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        Valid.MyReport("rptSuratKematian.jasper", "report", "::[ Surat Keterangan Kematian ]::",
                " SELECT pasien_mati.tanggal, pasien_mati.jam, pasien_mati.no_rkm_medis, "
                + " pasien.nm_pasien, TIMESTAMPDIFF(Year,pasien.tgl_lahir,CURDATE()) as umur_thn, "
                + " TIMESTAMPDIFF(MONTH,pasien.tgl_lahir,CURDATE()) % 12 as umur_bln, "
                + " FLOOR( TIMESTAMPDIFF( DAY, pasien.tgl_lahir, CURDATE() ) % 30.4375 ) as umur_hari, "
                + " pasien.alamat, pasien.no_ktp, "
                + " pasien.no_tlp, if(jk = 'L','Laki-laki','Perempuan') AS Kelamin, "
                + " pasien.tmp_lahir, DATE_FORMAT(pasien.tgl_lahir,'%d %M %Y') AS tgl_lahir, "
                + " pasien.gol_darah, pasien.stts_nikah, pasien.agama, pasien_mati.keterangan, "
                + " kelurahan.nm_kel, kecamatan.nm_kec, kabupaten.nm_kab, "
                + " ifnull(pasien_mati.no_surat,'.....') nosurat, ifnull(pasien_mati.reg_bulan,'.....') noregbulan "        
                + " FROM pasien_mati, pasien "
                + " INNER JOIN kabupaten ON kabupaten.kd_kab = pasien.kd_kab "
                + " INNER JOIN kecamatan ON kecamatan.kd_kec = pasien.kd_kec "
                + " INNER JOIN kelurahan ON kelurahan.kd_kel = pasien.kd_kel "
                + " WHERE pasien_mati.no_rkm_medis = pasien.no_rkm_medis "
                + " and pasien_mati.no_rkm_medis='" + TNoRM.getText() + "' ", param);
    }

    private void isKmr() {

        kd_pj = Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", TNoRw.getText());
        Sequel.cariIsi("select kd_bangsal from kamar where kd_kamar=? ", TKdBngsal, kdkamar.getText());
        Sequel.cariIsi("select nm_bangsal from bangsal where kd_bangsal=? ", TBangsal, TKdBngsal.getText());
        Sequel.cariIsi("select status from kamar where kd_kamar=? ", TSttsKamar, kdkamar.getText());
        try {
            pstarif = koneksi.prepareStatement("select tarif from set_harga_kamar where kd_kamar=? and kd_pj=?");
            try {
                pstarif.setString(1, kdkamar.getText());
                pstarif.setString(2, kd_pj);
                rs = pstarif.executeQuery();
                if (rs.next()) {
                    TTarif.setText(rs.getString(1));
                } else {
//                        Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                    if (Sequel.cariInteger("select count(-1) from reg_periksa where no_rawat = '" + TNoRw.getText() + "' and tgl_registrasi < '2018-05-01'") > 0) {
                        Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                    } else {
                        Sequel.cariIsi("select trf_kamar from kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                    }
//                        Sequel.cariIsi("select trf_kamar from riwayat_tarif_kamar where kd_kamar=? ", TTarif, kdkamar.getText());
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (pstarif != null) {
                    pstarif.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void dataRawat(String ruangan, String tanggal, String jam) {
        TRuangan.setText(ruangan);
        Ttgl_rawat.setText(tanggal);
        Tjam_rawat.setText(jam);
    }

    private void emptTextTindakan() {
        TSuhu.setText("");
        TKdPrw.setText("");
        TNmPrw.setText("");
        TTensi.setText("");
        TKeluhan.setText("");
        TPemeriksaan.setText("");
        TBerat.setText("");
        TTinggi.setText("");
        TNadi.setText("");
        TRespirasi.setText("");
        TGCS.setText("");
        TAlergi.setText("");
        TTnd.setText("0");
        BagianRS.setText("0");
        Bhp.setText("0");
        JmDokter.setText("0");
        JmPerawat.setText("0");
        DTPTgl.setDate(new Date());
        TNoRw.requestFocus();
        ChkInput.setSelected(true);
        KdDok1.setText("-");
        TDokter1.setText("-");
    }
    
    private void tampilCppt() {     
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                        + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                        + "c.instruksi_nakes, c.waktu_simpan from cppt c inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                        + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                        + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new Object[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes")                        
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rscppt != null) {
                    rscppt.close();
                }
                if (pscppt != null) {
                    pscppt.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getDataCppt() {
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
            } else {
                Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                        + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
            }
        }
    }
}
