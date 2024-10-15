    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import inventory.DlgPemberianObatPasien;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDiet;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariJumlahPemberianDiet;
import simrskhanza.DlgNotepad;
import simrskhanza.DlgPemberianDiet;

/**
 *
 * @author dosen
 */
public class DlgCPPT extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabMode4, tabMode5, tabMode6, tabModeResiko;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7, ps8, pps1, pps2, pps3, pps4, pps5, pps6, 
            pspasien, psdiet, psrestor, psLaprm, psFakIGD, psRes, psCetak;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rrs1, rrs2, rrs3, rrs4, rrs5, rrs6, 
            rspasien, rsdiet, rsrestor, rsLaprm, rsFakIGD, rsRes, rsPrev, rsCetak;
    private int i = 0, x = 0, pilihan = 0, totskorTriase = 0, skorGZ1 = 0, skorYaGZ1 = 0, skorGZ2 = 0, skor = 0, paste = 0, cekKonfirmasi = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDiet diet = new DlgCariDiet(null, false);
    private Date date = new Date(), jamSekarang, jamCPPT1, jamCPPT2, jamCPPT3;
    private DlgCariJumlahPemberianDiet jlhberi = new DlgCariJumlahPemberianDiet(null, false);
    private String whereNya = "", status = "", cekjam = "", statusOK = "", nipppa = "", jamkeluar = "", nipDPJPlain = "", dapatobat = "",
            obatsesuai = "", obatefektif = "", obataman = "", waktuSimpanDiet = "", kemasan = "", dataDiet = "", jnsRawat = "",
            kdUnit = "", instruksiDiet = "", siftppa = "", soap = "", hasil_pemeriksaan = "", instruksi_nakes = "", tgm = "",
            skorAsesIGD = "", kesimpulanGZanak = "", kesimpulanGZDewasa = "", faktorresikoigd = "", TotSkorRJ = "",
            kesimpulanResikoJatuh = "", TotSkorGZD = "", TotSkorGZA = "", wktSimpanKonfirmasi = "", konfirmasi_terapi = "",
            asuhanGiziHasil = "", asuhanGiziInstruksi = "", cekSttsGizi = "", sttsgz1 = "", sttsgz2 = "", sttsgz3 = "",
            klinis1 = "", klinis2 = "", klinis3 = "", klinis4 = "", klinis5 = "", klinis6 = "", klinis7 = "", klinis8 = "", 
            klinislain = "", cekKlinisGizi = "", riw1 = "", riw2 = "", riw3 = "", riw4 = "", riw5 = "", riw6 = "", riw7 = "", 
            riw8 = "", cekRiwGizi = "", riwGiziLain = "", diagnosaGizi = "", proteinGizi = "", lemakGizi = "", karboGizi = "",
            ren1 = "", ren2 = "", ren3 = "", ren4 = "", ren5 = "", cekRenMonevGizi = "", monevGizi = "", dataKonfir = "";

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public DlgCPPT(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row = {"No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Tgl. CPPT", "Jam CPPT", "Jenis PPA", "Nama PPA", "Hasil Pemeriksaan",
            "Instruksi Nakes", "Verifikasi", "Nama DPJP", "Status", "tanggal", "nip_dpjp", "wkt_simpan", "cekjam", "jam_cppt", "Jenis Bagian",
            "nipppa", "serah_terima_cppt", "nmkonsulen", "nipkonsulen", "petugas_serah", "petugas_terima", "nip_petugas_serah",
            "nip_petugas_terima", "Shift", "jam_serah_terima", "pilihan_soap", "subjektif", "objektif", "asesmen", "planing"
                
        };
        tabMode = new DefaultTableModel(null, row) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbCPPT.setModel(tabMode);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 34; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(250);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(60);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(45);
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
                column.setPreferredWidth(90);
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
                column.setPreferredWidth(45);
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
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Tgl. CPPT", "Jam CPPT", "Jns. PPA", 
            "Sift", "Data Template"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "kd_diet", "Nama Diet", "Jns. Makanan", "Jlh. Pemberian", "waktusimpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbDiet.setModel(tabMode2);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(180);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(100);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Dihapus Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. CPPT", "Jam CPPT", "Nama DPJP",
            "Status", "Jenis PPA", "Nama PPA", "Jenis Bagian", "DPJP Konsulen", "Shift",
            "flag_hapus", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbSampah.setModel(tabMode3);
        tbSampah.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSampah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbSampah.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(40);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSampah.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode4 = new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Sift", "Petugas Konfirmasi", "Tgl. Lapor", "Jam Lapor",
            "Dengan DPJP", "Tgl. Verifikasi", "Jam Verifikasi", "tgl_cppt", "jam_cppt", "tgl_lapor",
            "jam_lapor", "tgl_verifikasi", "jam_verifikasi", "no_rawat", "nip_petugas_konfir", "nip_dpjp_konfir", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbKonfirmasi.setModel(tabMode4);
        tbKonfirmasi.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbKonfirmasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 19; i++) {
            TableColumn column = tbKonfirmasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(40);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(90);
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
            } 
        }
        tbKonfirmasi.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode5 = new DefaultTableModel(null, new Object[]{
            "Diganti/Diedit Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. CPPT", "Jam CPPT", "Nama DPJP",
            "Status", "Jenis PPA", "Nama PPA", "Jenis Bagian", "DPJP Konsulen", "Shift",
            "Tgl. Diedit", "waktu_ganti"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode5);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 15; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(65);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(40);
            } else if (i == 13) {
                column.setPreferredWidth(110);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode6 = new DefaultTableModel(null, new Object[]{
            "No. RM", "Nama Pasien", "Tgl. Monev", "Ruang Perawatan", "Perkembangan Fisik/Klinis",
            "Perkembangan Diet", "Evaluasi Tindak Lanjut", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbMonev.setModel(tabMode6);
        tbMonev.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMonev.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbMonev.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(220);
            } else if (i == 2) {
                column.setPreferredWidth(75);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbMonev.setDefaultRenderer(Object.class, new WarnaTable());
        
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

        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        diet.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (diet.getTable().getSelectedRow() != -1) {
                        kddiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 0).toString());
                        nmdiet.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 1).toString());
                        jnsMakanan.setText(diet.getTable().getValueAt(diet.getTable().getSelectedRow(), 2).toString());
                        
                        if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                            kdberi.setText("-");
                            jumlahBeri.setText("");
                            jumlahBeri.setEditable(true);
                            cmbSatuan.setEnabled(true);
                            jumlahBeri.requestFocus();
                        } else {
                            jumlahBeri.setEditable(false);
                            cmbSatuan.setEnabled(false);
                            btnJumlahBeri.requestFocus();
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
        
        diet.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        diet.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        jlhberi.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (jlhberi.getTable().getSelectedRow() != -1) {
                        kdberi.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 0).toString());
                        jumlahBeri.setText(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 1).toString());
                        cmbSatuan.setSelectedItem(jlhberi.getTable().getValueAt(jlhberi.getTable().getSelectedRow(), 2).toString());
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
        
        jlhberi.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        jlhberi.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipppa = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        nmppa.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPPA.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipSerah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmSerah.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        ChkSamaPPA.setSelected(false);
                        ChkSamaPPA.setEnabled(true);
                        BtnSerah.requestFocus();
                    }
                } else if (pilihan == 3) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipTerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmTerima.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnTerima.requestFocus();
                    }
                } else if (pilihan == 4) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPetugasKonfir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmPetugasKonfir.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugasKonfir.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("DlgCPPT")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDPJP.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDPJPlain = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            nmDPJPlainya.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDPJPLain.requestFocus();
                        }
                    } else if (pilihan == 3) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipKonfirDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmKonfirDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnKonfirDpjp.requestFocus();
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
        
        LoadHTML1.setEditable(true);
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML1.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
        
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
        MnUrutkanData = new javax.swing.JMenuItem();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        MnDataPemberianDiet = new javax.swing.JMenuItem();
        MnPemantauanDewasa = new javax.swing.JMenuItem();
        MnGrafikPemantauanDewasa = new javax.swing.JMenuItem();
        MnDataSampah = new javax.swing.JMenuItem();
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
        MnPemberianObat = new javax.swing.JMenuItem();
        MnAsesmenMedikObstetriIGD = new javax.swing.JMenu();
        MnInputDataAsesmenMedikObstetri = new javax.swing.JMenuItem();
        MnLihatDataAsesmenMedikObstetri = new javax.swing.JMenuItem();
        MnAsesmenKebidanan = new javax.swing.JMenu();
        MnInputDataKebidanan = new javax.swing.JMenuItem();
        MnLihatDataKebidanan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnCeklisFarmasi = new javax.swing.JMenuItem();
        MnPemberianDiet = new javax.swing.JMenuItem();
        MnAsuhanGiziHasil = new javax.swing.JMenuItem();
        MnAsuhanGiziInstruksi = new javax.swing.JMenuItem();
        MnMonevGizi = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        MnTemplateS = new javax.swing.JMenuItem();
        MnUlangiS = new javax.swing.JMenuItem();
        MnPasteLabS = new javax.swing.JMenuItem();
        MnCeklisFarmasiS = new javax.swing.JMenuItem();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        MnTemplateO = new javax.swing.JMenuItem();
        MnUlangiO = new javax.swing.JMenuItem();
        MnPasteLabO = new javax.swing.JMenuItem();
        MnCeklisFarmasiO = new javax.swing.JMenuItem();
        jPopupMenu5 = new javax.swing.JPopupMenu();
        MnTemplateA = new javax.swing.JMenuItem();
        MnUlangiA = new javax.swing.JMenuItem();
        MnPasteLabA = new javax.swing.JMenuItem();
        MnCeklisFarmasiA = new javax.swing.JMenuItem();
        jPopupMenu6 = new javax.swing.JPopupMenu();
        MnTemplateP = new javax.swing.JMenuItem();
        MnUlangiP = new javax.swing.JMenuItem();
        MnPasteLabP = new javax.swing.JMenuItem();
        MnCeklisFarmasiP = new javax.swing.JMenuItem();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        WindowCPPT = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        internalFrame27 = new widget.InternalFrame();
        jLabel42 = new widget.Label();
        cmbJnsCppt = new widget.ComboBox();
        jLabel47 = new widget.Label();
        cmbSiftPetugas = new widget.ComboBox();
        tglA = new widget.Tanggal();
        jLabel49 = new widget.Label();
        tglB = new widget.Tanggal();
        cmbTanggal = new widget.ComboBox();
        jLabel50 = new widget.Label();
        internalFrame28 = new widget.InternalFrame();
        BtnCetakCPPT = new widget.Button();
        BtnCloseIn6 = new widget.Button();
        WindowFarmasi = new javax.swing.JDialog();
        internalFrame11 = new widget.InternalFrame();
        BtnCloseIn7 = new widget.Button();
        BtnSimpanCeklis = new widget.Button();
        ChkDapatObat = new widget.CekBox();
        ChkObatSesuai = new widget.CekBox();
        ChkObatEfektif = new widget.CekBox();
        ChkObatAman = new widget.CekBox();
        ChkSemua = new widget.CekBox();
        WindowDiet = new javax.swing.JDialog();
        internalFrame12 = new widget.InternalFrame();
        internalFrame15 = new widget.InternalFrame();
        BtnPilihDiet = new widget.Button();
        BtnHapusPilihan = new widget.Button();
        BtnGanti = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbDiet = new widget.Table();
        internalFrame14 = new widget.InternalFrame();
        internalFrame16 = new widget.InternalFrame();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        FormInput1 = new widget.PanelBiasa();
        cekWaktu = new widget.CekBox();
        cekDietPagi = new widget.CekBox();
        cekDietSiang = new widget.CekBox();
        cekDietSore = new widget.CekBox();
        cekKemasan = new widget.CekBox();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        DTPTgl = new widget.Tanggal();
        WindowDataDiet = new javax.swing.JDialog();
        internalFrame9 = new widget.InternalFrame();
        panelGlass7 = new widget.panelisi();
        nmdiet = new widget.TextBox();
        kddiet = new widget.TextBox();
        jLabel46 = new widget.Label();
        jnsMakanan = new widget.TextBox();
        jLabel48 = new widget.Label();
        kdberi = new widget.TextBox();
        jumlahBeri = new widget.TextBox();
        cmbSatuan = new widget.ComboBox();
        btnDiet = new widget.Button();
        btnJumlahBeri = new widget.Button();
        jLabel51 = new widget.Label();
        panelGlass11 = new widget.panelisi();
        BtnSimpan2 = new widget.Button();
        BtnEdit1 = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        WindowDataSampah = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel30 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll1 = new widget.Button();
        jLabel33 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnHapusSampah = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        TabHistory = new javax.swing.JTabbedPane();
        Scroll6 = new widget.ScrollPane();
        tbSampah = new widget.Table();
        Scroll7 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        WindowDataMonevGizi = new javax.swing.JDialog();
        internalFrame25 = new widget.InternalFrame();
        Scroll9 = new widget.ScrollPane();
        tbMonev = new widget.Table();
        panelGlass12 = new widget.panelisi();
        jLabel43 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel44 = new widget.Label();
        tgl2 = new widget.Tanggal();
        BtnCari3 = new widget.Button();
        jLabel45 = new widget.Label();
        cmbTujuanPaste = new widget.ComboBox();
        BtnCopas1 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        TKd = new widget.TextBox();
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
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        internalFrame1 = new widget.InternalFrame();
        TabCPPT = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TabPilihan = new javax.swing.JTabbedPane();
        scrollPane8 = new widget.ScrollPane();
        internalFrame4 = new widget.InternalFrame();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        THasil = new widget.TextArea();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TInstruksi = new widget.TextArea();
        BtnHasil = new widget.Button();
        BtnInstruksi = new widget.Button();
        BtnUlangiHasil = new widget.Button();
        BtnUlangiInstruksi = new widget.Button();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        BtnPasteHasil = new widget.Button();
        BtnPasteInstruksi = new widget.Button();
        internalFrame6 = new widget.InternalFrame();
        scrollPane4 = new widget.ScrollPane();
        TSubjektif = new widget.TextArea();
        scrollPane5 = new widget.ScrollPane();
        TObjektif = new widget.TextArea();
        scrollPane6 = new widget.ScrollPane();
        TAsesmen = new widget.TextArea();
        scrollPane7 = new widget.ScrollPane();
        TPlaning = new widget.TextArea();
        internalFrame7 = new widget.InternalFrame();
        jLabel5 = new widget.Label();
        jLabel8 = new widget.Label();
        tglCppt = new widget.Tanggal();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        ChkJam = new widget.CekBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbSift = new widget.ComboBox();
        TPasien = new widget.TextBox();
        jLabel34 = new widget.Label();
        cmbSoap = new widget.ComboBox();
        internalFrame8 = new widget.InternalFrame();
        TabPetugas = new javax.swing.JTabbedPane();
        internalFrame20 = new widget.InternalFrame();
        jLabel3 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        BtnDPJP = new widget.Button();
        BtnHapusDPJP = new widget.Button();
        BtnHapusKonsulen = new widget.Button();
        BtnDPJPLain = new widget.Button();
        nmDPJPlainya = new widget.TextBox();
        jLabel18 = new widget.Label();
        cmbBagian = new widget.ComboBox();
        jLabel17 = new widget.Label();
        jLabel4 = new widget.Label();
        cmbPPA = new widget.ComboBox();
        jLabel16 = new widget.Label();
        nmppa = new widget.TextBox();
        BtnPPA = new widget.Button();
        BtnHapusPPA = new widget.Button();
        ChkSamaPPA = new widget.CekBox();
        cmbDtk1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbJam1 = new widget.ComboBox();
        jLabel20 = new widget.Label();
        cmbSertim = new widget.ComboBox();
        jLabel9 = new widget.Label();
        jLabel22 = new widget.Label();
        nipSerah = new widget.TextBox();
        nmSerah = new widget.TextBox();
        BtnSerah = new widget.Button();
        BtnHapusSerah = new widget.Button();
        BtnHapusTerima = new widget.Button();
        BtnTerima = new widget.Button();
        nmTerima = new widget.TextBox();
        nipTerima = new widget.TextBox();
        jLabel23 = new widget.Label();
        chkDpjp = new widget.CekBox();
        internalFrame21 = new widget.InternalFrame();
        internalFrame22 = new widget.InternalFrame();
        internalFrame23 = new widget.InternalFrame();
        jLabel35 = new widget.Label();
        tglLapor = new widget.Tanggal();
        jLabel37 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel38 = new widget.Label();
        tglVerifikasi = new widget.Tanggal();
        jLabel39 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel40 = new widget.Label();
        nipPetugasKonfir = new widget.TextBox();
        nmPetugasKonfir = new widget.TextBox();
        BtnPetugasKonfir = new widget.Button();
        BtnHapusPetugasKonfir = new widget.Button();
        jLabel41 = new widget.Label();
        nipKonfirDpjp = new widget.TextBox();
        nmKonfirDpjp = new widget.TextBox();
        BtnKonfirDpjp = new widget.Button();
        BtnHapusKonfirDpjp = new widget.Button();
        internalFrame24 = new widget.InternalFrame();
        BtnSimpan3 = new widget.Button();
        BtnBatal2 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnEdit2 = new widget.Button();
        BtnLihatKon = new widget.Button();
        Scroll4 = new widget.ScrollPane();
        tbKonfirmasi = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel15 = new widget.Label();
        cmbRawat = new widget.ComboBox();
        jLabel25 = new widget.Label();
        cmbSiftCppt = new widget.ComboBox();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame26 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel14 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TNoRm1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        Scroll10 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnVerif = new widget.Button();
        BtnResep = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnUrutkanData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUrutkanData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnUrutkanData.setText("Urutkan Data Terakhir");
        MnUrutkanData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUrutkanData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUrutkanData.setIconTextGap(5);
        MnUrutkanData.setName("MnUrutkanData"); // NOI18N
        MnUrutkanData.setPreferredSize(new java.awt.Dimension(230, 26));
        MnUrutkanData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUrutkanDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnUrutkanData);

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(230, 26));
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
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        MnDataPemberianDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataPemberianDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataPemberianDiet.setText("Data Pemberian Diet");
        MnDataPemberianDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataPemberianDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataPemberianDiet.setIconTextGap(5);
        MnDataPemberianDiet.setName("MnDataPemberianDiet"); // NOI18N
        MnDataPemberianDiet.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDataPemberianDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataPemberianDietActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataPemberianDiet);

        MnPemantauanDewasa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemantauanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemantauanDewasa.setText("Pemantauan Harian (Dewasa)");
        MnPemantauanDewasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemantauanDewasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemantauanDewasa.setIconTextGap(5);
        MnPemantauanDewasa.setName("MnPemantauanDewasa"); // NOI18N
        MnPemantauanDewasa.setPreferredSize(new java.awt.Dimension(230, 26));
        MnPemantauanDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemantauanDewasaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnPemantauanDewasa);

        MnGrafikPemantauanDewasa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnGrafikPemantauanDewasa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnGrafikPemantauanDewasa.setText("Grafik Pemantauan Harian (Dewasa)");
        MnGrafikPemantauanDewasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnGrafikPemantauanDewasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnGrafikPemantauanDewasa.setIconTextGap(5);
        MnGrafikPemantauanDewasa.setName("MnGrafikPemantauanDewasa"); // NOI18N
        MnGrafikPemantauanDewasa.setPreferredSize(new java.awt.Dimension(230, 26));
        MnGrafikPemantauanDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnGrafikPemantauanDewasaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnGrafikPemantauanDewasa);

        MnDataSampah.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDataSampah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDataSampah.setText("Data Sampah");
        MnDataSampah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDataSampah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDataSampah.setIconTextGap(5);
        MnDataSampah.setName("MnDataSampah"); // NOI18N
        MnDataSampah.setPreferredSize(new java.awt.Dimension(230, 26));
        MnDataSampah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDataSampahActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDataSampah);

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
        MnInputDataTransferSerahTerimaIGD.setPreferredSize(new java.awt.Dimension(130, 26));
        MnInputDataTransferSerahTerimaIGD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnInputDataTransferSerahTerimaIGDActionPerformed(evt);
            }
        });
        MnTransferSerahTerimaIGD.add(MnInputDataTransferSerahTerimaIGD);

        MnPemberianObat.setBackground(new java.awt.Color(255, 255, 254));
        MnPemberianObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianObat.setForeground(new java.awt.Color(50, 50, 50));
        MnPemberianObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianObat.setText("Pemberian Obat");
        MnPemberianObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianObat.setIconTextGap(5);
        MnPemberianObat.setName("MnPemberianObat"); // NOI18N
        MnPemberianObat.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPemberianObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianObatActionPerformed(evt);
            }
        });
        MnTransferSerahTerimaIGD.add(MnPemberianObat);

        MnRMGawatDarurat.add(MnTransferSerahTerimaIGD);

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

        jPopupMenu1.add(MnRMGawatDarurat);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnCeklisFarmasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasi.setText("Ceklist Farmasi");
        MnCeklisFarmasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasi.setIconTextGap(5);
        MnCeklisFarmasi.setName("MnCeklisFarmasi"); // NOI18N
        MnCeklisFarmasi.setPreferredSize(new java.awt.Dimension(227, 26));
        MnCeklisFarmasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnCeklisFarmasi);

        MnPemberianDiet.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPemberianDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnPemberianDiet.setText("Pemberian Diet Gizi");
        MnPemberianDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPemberianDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPemberianDiet.setIconTextGap(5);
        MnPemberianDiet.setName("MnPemberianDiet"); // NOI18N
        MnPemberianDiet.setPreferredSize(new java.awt.Dimension(227, 26));
        MnPemberianDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPemberianDietActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnPemberianDiet);

        MnAsuhanGiziHasil.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsuhanGiziHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsuhanGiziHasil.setText("Paste Asuhan Gizi (Hasil)");
        MnAsuhanGiziHasil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsuhanGiziHasil.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsuhanGiziHasil.setIconTextGap(5);
        MnAsuhanGiziHasil.setName("MnAsuhanGiziHasil"); // NOI18N
        MnAsuhanGiziHasil.setPreferredSize(new java.awt.Dimension(227, 26));
        MnAsuhanGiziHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsuhanGiziHasilActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnAsuhanGiziHasil);

        MnAsuhanGiziInstruksi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnAsuhanGiziInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnAsuhanGiziInstruksi.setText("Paste Asuhan Gizi (Instruksi)");
        MnAsuhanGiziInstruksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnAsuhanGiziInstruksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnAsuhanGiziInstruksi.setIconTextGap(5);
        MnAsuhanGiziInstruksi.setName("MnAsuhanGiziInstruksi"); // NOI18N
        MnAsuhanGiziInstruksi.setPreferredSize(new java.awt.Dimension(227, 26));
        MnAsuhanGiziInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnAsuhanGiziInstruksiActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnAsuhanGiziInstruksi);

        MnMonevGizi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonevGizi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonevGizi.setText("Cek Data Monitoring & Evaluasi Gizi");
        MnMonevGizi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMonevGizi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMonevGizi.setIconTextGap(5);
        MnMonevGizi.setName("MnMonevGizi"); // NOI18N
        MnMonevGizi.setPreferredSize(new java.awt.Dimension(227, 26));
        MnMonevGizi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonevGiziActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnMonevGizi);

        jPopupMenu3.setName("jPopupMenu3"); // NOI18N

        MnTemplateS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTemplateS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTemplateS.setText("Template");
        MnTemplateS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTemplateS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTemplateS.setIconTextGap(5);
        MnTemplateS.setName("MnTemplateS"); // NOI18N
        MnTemplateS.setPreferredSize(new java.awt.Dimension(130, 26));
        MnTemplateS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTemplateSActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnTemplateS);

        MnUlangiS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUlangiS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnUlangiS.setText("Ulangi");
        MnUlangiS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUlangiS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUlangiS.setIconTextGap(5);
        MnUlangiS.setName("MnUlangiS"); // NOI18N
        MnUlangiS.setPreferredSize(new java.awt.Dimension(130, 26));
        MnUlangiS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUlangiSActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnUlangiS);

        MnPasteLabS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasteLabS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnPasteLabS.setText("Paste");
        MnPasteLabS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPasteLabS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPasteLabS.setIconTextGap(5);
        MnPasteLabS.setName("MnPasteLabS"); // NOI18N
        MnPasteLabS.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPasteLabS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasteLabSActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnPasteLabS);

        MnCeklisFarmasiS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasiS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasiS.setText("Ceklist Farmasi");
        MnCeklisFarmasiS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasiS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasiS.setIconTextGap(5);
        MnCeklisFarmasiS.setName("MnCeklisFarmasiS"); // NOI18N
        MnCeklisFarmasiS.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCeklisFarmasiS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiSActionPerformed(evt);
            }
        });
        jPopupMenu3.add(MnCeklisFarmasiS);

        jPopupMenu4.setName("jPopupMenu4"); // NOI18N

        MnTemplateO.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTemplateO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTemplateO.setText("Template");
        MnTemplateO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTemplateO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTemplateO.setIconTextGap(5);
        MnTemplateO.setName("MnTemplateO"); // NOI18N
        MnTemplateO.setPreferredSize(new java.awt.Dimension(130, 26));
        MnTemplateO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTemplateOActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnTemplateO);

        MnUlangiO.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUlangiO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnUlangiO.setText("Ulangi");
        MnUlangiO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUlangiO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUlangiO.setIconTextGap(5);
        MnUlangiO.setName("MnUlangiO"); // NOI18N
        MnUlangiO.setPreferredSize(new java.awt.Dimension(130, 26));
        MnUlangiO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUlangiOActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnUlangiO);

        MnPasteLabO.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasteLabO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnPasteLabO.setText("Paste");
        MnPasteLabO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPasteLabO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPasteLabO.setIconTextGap(5);
        MnPasteLabO.setName("MnPasteLabO"); // NOI18N
        MnPasteLabO.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPasteLabO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasteLabOActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnPasteLabO);

        MnCeklisFarmasiO.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasiO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasiO.setText("Ceklist Farmasi");
        MnCeklisFarmasiO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasiO.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasiO.setIconTextGap(5);
        MnCeklisFarmasiO.setName("MnCeklisFarmasiO"); // NOI18N
        MnCeklisFarmasiO.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCeklisFarmasiO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiOActionPerformed(evt);
            }
        });
        jPopupMenu4.add(MnCeklisFarmasiO);

        jPopupMenu5.setName("jPopupMenu5"); // NOI18N

        MnTemplateA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTemplateA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTemplateA.setText("Template");
        MnTemplateA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTemplateA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTemplateA.setIconTextGap(5);
        MnTemplateA.setName("MnTemplateA"); // NOI18N
        MnTemplateA.setPreferredSize(new java.awt.Dimension(130, 26));
        MnTemplateA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTemplateAActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnTemplateA);

        MnUlangiA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUlangiA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnUlangiA.setText("Ulangi");
        MnUlangiA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUlangiA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUlangiA.setIconTextGap(5);
        MnUlangiA.setName("MnUlangiA"); // NOI18N
        MnUlangiA.setPreferredSize(new java.awt.Dimension(130, 26));
        MnUlangiA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUlangiAActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnUlangiA);

        MnPasteLabA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasteLabA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnPasteLabA.setText("Paste");
        MnPasteLabA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPasteLabA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPasteLabA.setIconTextGap(5);
        MnPasteLabA.setName("MnPasteLabA"); // NOI18N
        MnPasteLabA.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPasteLabA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasteLabAActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnPasteLabA);

        MnCeklisFarmasiA.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasiA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasiA.setText("Ceklist Farmasi");
        MnCeklisFarmasiA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasiA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasiA.setIconTextGap(5);
        MnCeklisFarmasiA.setName("MnCeklisFarmasiA"); // NOI18N
        MnCeklisFarmasiA.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCeklisFarmasiA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiAActionPerformed(evt);
            }
        });
        jPopupMenu5.add(MnCeklisFarmasiA);

        jPopupMenu6.setName("jPopupMenu6"); // NOI18N

        MnTemplateP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnTemplateP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnTemplateP.setText("Template");
        MnTemplateP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnTemplateP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnTemplateP.setIconTextGap(5);
        MnTemplateP.setName("MnTemplateP"); // NOI18N
        MnTemplateP.setPreferredSize(new java.awt.Dimension(130, 26));
        MnTemplateP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnTemplatePActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnTemplateP);

        MnUlangiP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnUlangiP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnUlangiP.setText("Ulangi");
        MnUlangiP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnUlangiP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnUlangiP.setIconTextGap(5);
        MnUlangiP.setName("MnUlangiP"); // NOI18N
        MnUlangiP.setPreferredSize(new java.awt.Dimension(130, 26));
        MnUlangiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnUlangiPActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnUlangiP);

        MnPasteLabP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnPasteLabP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        MnPasteLabP.setText("Paste");
        MnPasteLabP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnPasteLabP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnPasteLabP.setIconTextGap(5);
        MnPasteLabP.setName("MnPasteLabP"); // NOI18N
        MnPasteLabP.setPreferredSize(new java.awt.Dimension(130, 26));
        MnPasteLabP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnPasteLabPActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnPasteLabP);

        MnCeklisFarmasiP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCeklisFarmasiP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnCeklisFarmasiP.setText("Ceklist Farmasi");
        MnCeklisFarmasiP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnCeklisFarmasiP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnCeklisFarmasiP.setIconTextGap(5);
        MnCeklisFarmasiP.setName("MnCeklisFarmasiP"); // NOI18N
        MnCeklisFarmasiP.setPreferredSize(new java.awt.Dimension(130, 26));
        MnCeklisFarmasiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCeklisFarmasiPActionPerformed(evt);
            }
        });
        jPopupMenu6.add(MnCeklisFarmasiP);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setAutoCreateRowSorter(true);
        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Ttemplate.setPreferredSize(new java.awt.Dimension(210, 4000));
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Key Word :");
        jLabel36.setName("jLabel36"); // NOI18N
        jLabel36.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel36.setRequestFocusEnabled(false);
        panelisi4.add(jLabel36);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelisi4.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 23));
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

        BtnCopas.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas.setMnemonic('U');
        BtnCopas.setText("Copy & Paste");
        BtnCopas.setToolTipText("Alt+U");
        BtnCopas.setName("BtnCopas"); // NOI18N
        BtnCopas.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopasActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCopas);

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.PAGE_END);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        WindowCPPT.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowCPPT.setName("WindowCPPT"); // NOI18N
        WindowCPPT.setUndecorated(true);
        WindowCPPT.setResizable(false);

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Membaca & Lihat Laporan CPPT ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        internalFrame27.setName("internalFrame27"); // NOI18N
        internalFrame27.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame27.setLayout(null);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Jenis CPPT :");
        jLabel42.setName("jLabel42"); // NOI18N
        internalFrame27.add(jLabel42);
        jLabel42.setBounds(0, 10, 90, 23);

        cmbJnsCppt.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rawat Inap", "IGD" }));
        cmbJnsCppt.setName("cmbJnsCppt"); // NOI18N
        cmbJnsCppt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsCpptActionPerformed(evt);
            }
        });
        internalFrame27.add(cmbJnsCppt);
        cmbJnsCppt.setBounds(97, 10, 90, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Shift Petugas :");
        jLabel47.setName("jLabel47"); // NOI18N
        internalFrame27.add(jLabel47);
        jLabel47.setBounds(190, 10, 90, 23);

        cmbSiftPetugas.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftPetugas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "Semua" }));
        cmbSiftPetugas.setName("cmbSiftPetugas"); // NOI18N
        internalFrame27.add(cmbSiftPetugas);
        cmbSiftPetugas.setBounds(287, 10, 65, 23);

        tglA.setEditable(false);
        tglA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tglA.setDisplayFormat("dd-MM-yyyy");
        tglA.setName("tglA"); // NOI18N
        tglA.setOpaque(false);
        tglA.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame27.add(tglA);
        tglA.setBounds(195, 38, 90, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("s.d");
        jLabel49.setName("jLabel49"); // NOI18N
        internalFrame27.add(jLabel49);
        jLabel49.setBounds(288, 38, 30, 23);

        tglB.setEditable(false);
        tglB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tglB.setDisplayFormat("dd-MM-yyyy");
        tglB.setName("tglB"); // NOI18N
        tglB.setOpaque(false);
        tglB.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame27.add(tglB);
        tglB.setBounds(322, 38, 90, 23);

        cmbTanggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Periode", "Per Tanggal" }));
        cmbTanggal.setName("cmbTanggal"); // NOI18N
        cmbTanggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTanggalActionPerformed(evt);
            }
        });
        internalFrame27.add(cmbTanggal);
        cmbTanggal.setBounds(97, 38, 90, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Tgl. CPPT :");
        jLabel50.setName("jLabel50"); // NOI18N
        internalFrame27.add(jLabel50);
        jLabel50.setBounds(0, 38, 90, 23);

        internalFrame10.add(internalFrame27, java.awt.BorderLayout.CENTER);

        internalFrame28.setName("internalFrame28"); // NOI18N
        internalFrame28.setPreferredSize(new java.awt.Dimension(0, 46));
        internalFrame28.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame28.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 8));

        BtnCetakCPPT.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetakCPPT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnCetakCPPT.setMnemonic('U');
        BtnCetakCPPT.setText("Cetak CPPT");
        BtnCetakCPPT.setToolTipText("Alt+U");
        BtnCetakCPPT.setName("BtnCetakCPPT"); // NOI18N
        BtnCetakCPPT.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCetakCPPT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakCPPTActionPerformed(evt);
            }
        });
        internalFrame28.add(BtnCetakCPPT);

        BtnCloseIn6.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn6.setMnemonic('U');
        BtnCloseIn6.setText("Tutup");
        BtnCloseIn6.setToolTipText("Alt+U");
        BtnCloseIn6.setName("BtnCloseIn6"); // NOI18N
        BtnCloseIn6.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnCloseIn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn6ActionPerformed(evt);
            }
        });
        internalFrame28.add(BtnCloseIn6);

        internalFrame10.add(internalFrame28, java.awt.BorderLayout.PAGE_END);

        WindowCPPT.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowFarmasi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowFarmasi.setName("WindowFarmasi"); // NOI18N
        WindowFarmasi.setUndecorated(true);
        WindowFarmasi.setResizable(false);

        internalFrame11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ceklist Pemantauan Penggunaan Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(null);

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
        internalFrame11.add(BtnCloseIn7);
        BtnCloseIn7.setBounds(150, 120, 90, 30);

        BtnSimpanCeklis.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanCeklis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanCeklis.setMnemonic('U');
        BtnSimpanCeklis.setText("Simpan");
        BtnSimpanCeklis.setToolTipText("Alt+U");
        BtnSimpanCeklis.setName("BtnSimpanCeklis"); // NOI18N
        BtnSimpanCeklis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanCeklisActionPerformed(evt);
            }
        });
        internalFrame11.add(BtnSimpanCeklis);
        BtnSimpanCeklis.setBounds(40, 120, 90, 30);

        ChkDapatObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkDapatObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDapatObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkDapatObat.setText("Dapat Obat ");
        ChkDapatObat.setBorderPainted(true);
        ChkDapatObat.setBorderPaintedFlat(true);
        ChkDapatObat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkDapatObat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkDapatObat.setName("ChkDapatObat"); // NOI18N
        ChkDapatObat.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkDapatObat);
        ChkDapatObat.setBounds(20, 27, 100, 23);

        ChkObatSesuai.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatSesuai.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatSesuai.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatSesuai.setText("Obat Sesuai");
        ChkObatSesuai.setBorderPainted(true);
        ChkObatSesuai.setBorderPaintedFlat(true);
        ChkObatSesuai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatSesuai.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatSesuai.setName("ChkObatSesuai"); // NOI18N
        ChkObatSesuai.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatSesuai);
        ChkObatSesuai.setBounds(20, 55, 100, 23);

        ChkObatEfektif.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatEfektif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatEfektif.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatEfektif.setText("Obat Efektif");
        ChkObatEfektif.setBorderPainted(true);
        ChkObatEfektif.setBorderPaintedFlat(true);
        ChkObatEfektif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatEfektif.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatEfektif.setName("ChkObatEfektif"); // NOI18N
        ChkObatEfektif.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatEfektif);
        ChkObatEfektif.setBounds(150, 27, 100, 23);

        ChkObatAman.setBackground(new java.awt.Color(255, 255, 250));
        ChkObatAman.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObatAman.setForeground(new java.awt.Color(0, 0, 0));
        ChkObatAman.setText("Obat Aman");
        ChkObatAman.setBorderPainted(true);
        ChkObatAman.setBorderPaintedFlat(true);
        ChkObatAman.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkObatAman.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkObatAman.setName("ChkObatAman"); // NOI18N
        ChkObatAman.setPreferredSize(new java.awt.Dimension(175, 23));
        internalFrame11.add(ChkObatAman);
        ChkObatAman.setBounds(150, 55, 100, 23);

        ChkSemua.setBackground(new java.awt.Color(255, 255, 250));
        ChkSemua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSemua.setForeground(new java.awt.Color(0, 0, 0));
        ChkSemua.setText("Conteng Semua");
        ChkSemua.setBorderPainted(true);
        ChkSemua.setBorderPaintedFlat(true);
        ChkSemua.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkSemua.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkSemua.setName("ChkSemua"); // NOI18N
        ChkSemua.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaActionPerformed(evt);
            }
        });
        internalFrame11.add(ChkSemua);
        ChkSemua.setBounds(20, 83, 100, 23);

        WindowFarmasi.getContentPane().add(internalFrame11, java.awt.BorderLayout.CENTER);

        WindowDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDiet.setName("WindowDiet"); // NOI18N
        WindowDiet.setUndecorated(true);
        WindowDiet.setResizable(false);

        internalFrame12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Diet Harian Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(new java.awt.BorderLayout());

        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setPreferredSize(new java.awt.Dimension(55, 45));
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BtnPilihDiet.setForeground(new java.awt.Color(0, 0, 0));
        BtnPilihDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPilihDiet.setMnemonic('P');
        BtnPilihDiet.setText("Pilihan Diet");
        BtnPilihDiet.setToolTipText("Alt+P");
        BtnPilihDiet.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnPilihDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnPilihDiet.setName("BtnPilihDiet"); // NOI18N
        BtnPilihDiet.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnPilihDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPilihDietActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnPilihDiet);

        BtnHapusPilihan.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPilihan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusPilihan.setMnemonic('H');
        BtnHapusPilihan.setText("Hapus Dipilih");
        BtnHapusPilihan.setToolTipText("Alt+H");
        BtnHapusPilihan.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnHapusPilihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnHapusPilihan.setName("BtnHapusPilihan"); // NOI18N
        BtnHapusPilihan.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnHapusPilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPilihanActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnHapusPilihan);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti Dipilih");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setGlassColor(new java.awt.Color(0, 153, 153));
        BtnGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(118, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnGanti);

        internalFrame12.add(internalFrame15, java.awt.BorderLayout.PAGE_START);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Diet yang dipilih/ditentukan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
        tbDiet.setName("tbDiet"); // NOI18N
        tbDiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDietMouseClicked(evt);
            }
        });
        tbDiet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbDietKeyPressed(evt);
            }
        });
        Scroll5.setViewportView(tbDiet);

        internalFrame12.add(Scroll5, java.awt.BorderLayout.CENTER);

        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setPreferredSize(new java.awt.Dimension(55, 120));
        internalFrame14.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame14.setLayout(new java.awt.BorderLayout());

        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setPreferredSize(new java.awt.Dimension(55, 45));
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
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
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        internalFrame16.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
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
        internalFrame16.add(BtnBatal1);

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
        internalFrame16.add(BtnKeluar1);

        internalFrame14.add(internalFrame16, java.awt.BorderLayout.PAGE_END);

        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(160, 70));
        FormInput1.setLayout(null);

        cekWaktu.setBackground(new java.awt.Color(242, 242, 242));
        cekWaktu.setBorder(null);
        cekWaktu.setForeground(new java.awt.Color(0, 0, 0));
        cekWaktu.setText("Semua Waktu Diet");
        cekWaktu.setBorderPainted(true);
        cekWaktu.setBorderPaintedFlat(true);
        cekWaktu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekWaktu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekWaktu.setName("cekWaktu"); // NOI18N
        cekWaktu.setOpaque(false);
        cekWaktu.setPreferredSize(new java.awt.Dimension(100, 23));
        cekWaktu.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cekWaktuItemStateChanged(evt);
            }
        });
        cekWaktu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cekWaktuMouseClicked(evt);
            }
        });
        cekWaktu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekWaktuActionPerformed(evt);
            }
        });
        FormInput1.add(cekWaktu);
        cekWaktu.setBounds(325, 12, 125, 23);

        cekDietPagi.setBackground(new java.awt.Color(242, 242, 242));
        cekDietPagi.setBorder(null);
        cekDietPagi.setForeground(new java.awt.Color(0, 0, 0));
        cekDietPagi.setText("Diet Pagi");
        cekDietPagi.setBorderPainted(true);
        cekDietPagi.setBorderPaintedFlat(true);
        cekDietPagi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietPagi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietPagi.setName("cekDietPagi"); // NOI18N
        cekDietPagi.setOpaque(false);
        cekDietPagi.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietPagi);
        cekDietPagi.setBounds(85, 12, 70, 23);

        cekDietSiang.setBackground(new java.awt.Color(242, 242, 242));
        cekDietSiang.setBorder(null);
        cekDietSiang.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSiang.setText("Diet Siang");
        cekDietSiang.setBorderPainted(true);
        cekDietSiang.setBorderPaintedFlat(true);
        cekDietSiang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSiang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSiang.setName("cekDietSiang"); // NOI18N
        cekDietSiang.setOpaque(false);
        cekDietSiang.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietSiang);
        cekDietSiang.setBounds(165, 12, 70, 23);

        cekDietSore.setBackground(new java.awt.Color(242, 242, 242));
        cekDietSore.setBorder(null);
        cekDietSore.setForeground(new java.awt.Color(0, 0, 0));
        cekDietSore.setText("Diet Sore");
        cekDietSore.setBorderPainted(true);
        cekDietSore.setBorderPaintedFlat(true);
        cekDietSore.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekDietSore.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekDietSore.setName("cekDietSore"); // NOI18N
        cekDietSore.setOpaque(false);
        cekDietSore.setPreferredSize(new java.awt.Dimension(100, 23));
        FormInput1.add(cekDietSore);
        cekDietSore.setBounds(245, 12, 70, 23);

        cekKemasan.setBackground(new java.awt.Color(242, 242, 242));
        cekKemasan.setBorder(null);
        cekKemasan.setForeground(new java.awt.Color(0, 0, 0));
        cekKemasan.setText("Disajikan Seperti Biasa");
        cekKemasan.setBorderPainted(true);
        cekKemasan.setBorderPaintedFlat(true);
        cekKemasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cekKemasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        cekKemasan.setName("cekKemasan"); // NOI18N
        cekKemasan.setOpaque(false);
        cekKemasan.setPreferredSize(new java.awt.Dimension(100, 23));
        cekKemasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cekKemasanActionPerformed(evt);
            }
        });
        FormInput1.add(cekKemasan);
        cekKemasan.setBounds(185, 40, 250, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Waktu Diet :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(0, 12, 80, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Tanggal :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput1.add(jLabel29);
        jLabel29.setBounds(0, 40, 80, 23);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput1.add(DTPTgl);
        DTPTgl.setBounds(85, 40, 90, 23);

        internalFrame14.add(FormInput1, java.awt.BorderLayout.PAGE_START);

        internalFrame12.add(internalFrame14, java.awt.BorderLayout.PAGE_END);

        WindowDiet.getContentPane().add(internalFrame12, java.awt.BorderLayout.CENTER);

        WindowDataDiet.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataDiet.setName("WindowDataDiet"); // NOI18N
        WindowDataDiet.setUndecorated(true);
        WindowDataDiet.setResizable(false);

        internalFrame9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pilihan Diet ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 100));
        panelGlass7.setLayout(null);

        nmdiet.setEditable(false);
        nmdiet.setForeground(new java.awt.Color(0, 0, 0));
        nmdiet.setName("nmdiet"); // NOI18N
        panelGlass7.add(nmdiet);
        nmdiet.setBounds(197, 10, 200, 23);

        kddiet.setEditable(false);
        kddiet.setForeground(new java.awt.Color(0, 0, 0));
        kddiet.setName("kddiet"); // NOI18N
        panelGlass7.add(kddiet);
        kddiet.setBounds(113, 10, 80, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Makanan : ");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass7.add(jLabel46);
        jLabel46.setBounds(0, 37, 110, 23);

        jnsMakanan.setEditable(false);
        jnsMakanan.setForeground(new java.awt.Color(0, 0, 0));
        jnsMakanan.setName("jnsMakanan"); // NOI18N
        panelGlass7.add(jnsMakanan);
        jnsMakanan.setBounds(113, 37, 283, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Jumlah Pemberian : ");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass7.add(jLabel48);
        jLabel48.setBounds(0, 64, 110, 23);

        kdberi.setEditable(false);
        kdberi.setForeground(new java.awt.Color(0, 0, 0));
        kdberi.setName("kdberi"); // NOI18N
        panelGlass7.add(kdberi);
        kdberi.setBounds(113, 64, 80, 23);

        jumlahBeri.setEditable(false);
        jumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        jumlahBeri.setName("jumlahBeri"); // NOI18N
        panelGlass7.add(jumlahBeri);
        jumlahBeri.setBounds(197, 64, 130, 23);

        cmbSatuan.setForeground(new java.awt.Color(0, 0, 0));
        cmbSatuan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbSatuan.setName("cmbSatuan"); // NOI18N
        cmbSatuan.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass7.add(cmbSatuan);
        cmbSatuan.setBounds(332, 64, 65, 23);

        btnDiet.setForeground(new java.awt.Color(0, 0, 0));
        btnDiet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiet.setMnemonic('X');
        btnDiet.setToolTipText("Alt+X");
        btnDiet.setName("btnDiet"); // NOI18N
        btnDiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDietActionPerformed(evt);
            }
        });
        panelGlass7.add(btnDiet);
        btnDiet.setBounds(400, 10, 28, 23);

        btnJumlahBeri.setForeground(new java.awt.Color(0, 0, 0));
        btnJumlahBeri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnJumlahBeri.setMnemonic('X');
        btnJumlahBeri.setToolTipText("Alt+X");
        btnJumlahBeri.setName("btnJumlahBeri"); // NOI18N
        btnJumlahBeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJumlahBeriActionPerformed(evt);
            }
        });
        panelGlass7.add(btnJumlahBeri);
        btnJumlahBeri.setBounds(400, 64, 28, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Nama Diet : ");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass7.add(jLabel51);
        jLabel51.setBounds(0, 10, 110, 23);

        internalFrame9.add(panelGlass7, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 150));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 5));

        BtnSimpan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan2.setMnemonic('S');
        BtnSimpan2.setText("Simpan");
        BtnSimpan2.setToolTipText("Alt+S");
        BtnSimpan2.setName("BtnSimpan2"); // NOI18N
        BtnSimpan2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan2ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnSimpan2);

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
        panelGlass11.add(BtnEdit1);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelGlass11.add(BtnCloseIn3);

        internalFrame9.add(panelGlass11, java.awt.BorderLayout.CENTER);

        WindowDataDiet.getContentPane().add(internalFrame9, java.awt.BorderLayout.CENTER);

        WindowDataSampah.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataSampah.setName("WindowDataSampah"); // NOI18N
        WindowDataSampah.setUndecorated(true);
        WindowDataSampah.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ CPPT Data Sampah ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tgl. CPPT :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel30);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("s.d.");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel31);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel32);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
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
        internalFrame17.add(BtnCari2);

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
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
        internalFrame17.add(BtnAll1);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Record :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel33);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        BtnHapusSampah.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSampah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusSampah.setMnemonic('U');
        BtnHapusSampah.setText("Hapus");
        BtnHapusSampah.setToolTipText("Alt+U");
        BtnHapusSampah.setName("BtnHapusSampah"); // NOI18N
        BtnHapusSampah.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnHapusSampah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSampahActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnHapusSampah);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        TabHistory.setBackground(new java.awt.Color(254, 255, 254));
        TabHistory.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabHistory.setName("TabHistory"); // NOI18N
        TabHistory.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabHistory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabHistoryMouseClicked(evt);
            }
        });

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbSampah.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbSampah.setName("tbSampah"); // NOI18N
        Scroll6.setViewportView(tbSampah);

        TabHistory.addTab("Data CPPT Terhapus", Scroll6);

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll7.setViewportView(tbRiwayat);

        TabHistory.addTab("Riwayat CPPT", Scroll7);

        internalFrame13.add(TabHistory, java.awt.BorderLayout.CENTER);

        WindowDataSampah.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        WindowDataMonevGizi.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDataMonevGizi.setName("WindowDataMonevGizi"); // NOI18N
        WindowDataMonevGizi.setUndecorated(true);
        WindowDataMonevGizi.setResizable(false);

        internalFrame25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Monitoring & Evaluasi Gizi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame25.setName("internalFrame25"); // NOI18N
        internalFrame25.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame25.setLayout(new java.awt.BorderLayout());

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbMonev.setToolTipText("Silahkan pilih salah satu data yang mau dicopas");
        tbMonev.setName("tbMonev"); // NOI18N
        Scroll9.setViewportView(tbMonev);

        internalFrame25.add(Scroll9, java.awt.BorderLayout.CENTER);

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 5));

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Tgl. Monev :");
        jLabel43.setName("jLabel43"); // NOI18N
        jLabel43.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel43);

        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(tgl1);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("s.d.");
        jLabel44.setName("jLabel44"); // NOI18N
        jLabel44.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass12.add(jLabel44);

        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass12.add(tgl2);

        BtnCari3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari3.setMnemonic('2');
        BtnCari3.setText("Tampilkan Data");
        BtnCari3.setName("BtnCari3"); // NOI18N
        BtnCari3.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari3ActionPerformed(evt);
            }
        });
        BtnCari3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari3KeyPressed(evt);
            }
        });
        panelGlass12.add(BtnCari3);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Tujuan Paste :");
        jLabel45.setName("jLabel45"); // NOI18N
        jLabel45.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass12.add(jLabel45);

        cmbTujuanPaste.setForeground(new java.awt.Color(0, 0, 0));
        cmbTujuanPaste.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Hasil Pemeriksaan", "Instruksi Nakes" }));
        cmbTujuanPaste.setName("cmbTujuanPaste"); // NOI18N
        cmbTujuanPaste.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass12.add(cmbTujuanPaste);

        BtnCopas1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCopas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnCopas1.setMnemonic('U');
        BtnCopas1.setText("Copy & Paste");
        BtnCopas1.setToolTipText("Alt+U");
        BtnCopas1.setName("BtnCopas1"); // NOI18N
        BtnCopas1.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCopas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCopas1ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCopas1);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(90, 30));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        panelGlass12.add(BtnCloseIn4);

        internalFrame25.add(panelGlass12, java.awt.BorderLayout.PAGE_END);

        WindowDataMonevGizi.getContentPane().add(internalFrame25, java.awt.BorderLayout.CENTER);

        TKd.setEditable(false);
        TKd.setForeground(new java.awt.Color(0, 0, 0));
        TKd.setName("TKd"); // NOI18N

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

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        Scroll8.setViewportView(tbFaktorResiko);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Perkembangan Pasien Terintegrasi (CPPT) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabCPPT.setBackground(new java.awt.Color(254, 255, 254));
        TabCPPT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabCPPT.setName("TabCPPT"); // NOI18N
        TabCPPT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabCPPTMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 760));
        FormInput.setLayout(new java.awt.BorderLayout());

        TabPilihan.setBackground(new java.awt.Color(254, 255, 254));
        TabPilihan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPilihan.setName("TabPilihan"); // NOI18N
        TabPilihan.setPreferredSize(new java.awt.Dimension(0, 2000));

        scrollPane8.setBorder(null);
        scrollPane8.setName("scrollPane8"); // NOI18N
        scrollPane8.setPreferredSize(new java.awt.Dimension(900, 500));

        internalFrame4.setBorder(null);
        internalFrame4.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame4.setComponentPopupMenu(jPopupMenu1);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setPreferredSize(new java.awt.Dimension(900, 485));
        internalFrame4.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Hasil Pemeriksaan, Analisa, :");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame4.add(jLabel10);
        jLabel10.setBounds(0, 10, 180, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Rencana, Penatalaksanaan ");
        jLabel11.setName("jLabel11"); // NOI18N
        internalFrame4.add(jLabel11);
        jLabel11.setBounds(0, 24, 180, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        THasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        THasil.setColumns(20);
        THasil.setRows(5);
        THasil.setComponentPopupMenu(jPopupMenu2);
        THasil.setName("THasil"); // NOI18N
        THasil.setPreferredSize(new java.awt.Dimension(162, 4000));
        THasil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                THasilKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(THasil);

        internalFrame4.add(scrollPane2);
        scrollPane2.setBounds(185, 10, 630, 280);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Instruksi Tenaga Kesehatan :");
        jLabel12.setName("jLabel12"); // NOI18N
        internalFrame4.add(jLabel12);
        jLabel12.setBounds(0, 295, 180, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Termasuk Pasca Bedah  ");
        jLabel13.setName("jLabel13"); // NOI18N
        internalFrame4.add(jLabel13);
        jLabel13.setBounds(0, 309, 180, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TInstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TInstruksi.setColumns(20);
        TInstruksi.setRows(5);
        TInstruksi.setComponentPopupMenu(jPopupMenu2);
        TInstruksi.setName("TInstruksi"); // NOI18N
        TInstruksi.setPreferredSize(new java.awt.Dimension(162, 2000));
        TInstruksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInstruksiKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TInstruksi);

        internalFrame4.add(scrollPane3);
        scrollPane3.setBounds(185, 295, 630, 182);

        BtnHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnHasil.setMnemonic('2');
        BtnHasil.setText("Template");
        BtnHasil.setToolTipText("Alt+2");
        BtnHasil.setName("BtnHasil"); // NOI18N
        BtnHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHasilActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnHasil);
        BtnHasil.setBounds(825, 10, 100, 23);

        BtnInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnInstruksi.setMnemonic('2');
        BtnInstruksi.setText("Template");
        BtnInstruksi.setToolTipText("Alt+2");
        BtnInstruksi.setName("BtnInstruksi"); // NOI18N
        BtnInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInstruksiActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnInstruksi);
        BtnInstruksi.setBounds(825, 295, 100, 23);

        BtnUlangiHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiHasil.setMnemonic('2');
        BtnUlangiHasil.setText("Ulangi");
        BtnUlangiHasil.setToolTipText("Alt+2");
        BtnUlangiHasil.setName("BtnUlangiHasil"); // NOI18N
        BtnUlangiHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnUlangiHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiHasilActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnUlangiHasil);
        BtnUlangiHasil.setBounds(825, 40, 100, 23);

        BtnUlangiInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnUlangiInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnUlangiInstruksi.setMnemonic('2');
        BtnUlangiInstruksi.setText("Ulangi");
        BtnUlangiInstruksi.setToolTipText("Alt+2");
        BtnUlangiInstruksi.setName("BtnUlangiInstruksi"); // NOI18N
        BtnUlangiInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnUlangiInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUlangiInstruksiActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnUlangiInstruksi);
        BtnUlangiInstruksi.setBounds(825, 325, 100, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Pasien ");
        jLabel26.setName("jLabel26"); // NOI18N
        internalFrame4.add(jLabel26);
        jLabel26.setBounds(0, 38, 180, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("/Prosedur  ");
        jLabel27.setName("jLabel27"); // NOI18N
        internalFrame4.add(jLabel27);
        jLabel27.setBounds(0, 323, 180, 23);

        BtnPasteHasil.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteHasil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteHasil.setMnemonic('L');
        BtnPasteHasil.setText("Paste");
        BtnPasteHasil.setToolTipText("Alt+L");
        BtnPasteHasil.setName("BtnPasteHasil"); // NOI18N
        BtnPasteHasil.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteHasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteHasilActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnPasteHasil);
        BtnPasteHasil.setBounds(825, 70, 100, 23);

        BtnPasteInstruksi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPasteInstruksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPasteInstruksi.setMnemonic('L');
        BtnPasteInstruksi.setText("Paste");
        BtnPasteInstruksi.setToolTipText("Alt+L");
        BtnPasteInstruksi.setName("BtnPasteInstruksi"); // NOI18N
        BtnPasteInstruksi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPasteInstruksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteInstruksiActionPerformed(evt);
            }
        });
        internalFrame4.add(BtnPasteInstruksi);
        BtnPasteInstruksi.setBounds(825, 355, 100, 23);

        scrollPane8.setViewportView(internalFrame4);

        TabPilihan.addTab("SOAP Pilihan 1", scrollPane8);

        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.GridLayout(1, 4));

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Subjektif (S) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N

        TSubjektif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TSubjektif.setColumns(20);
        TSubjektif.setRows(5);
        TSubjektif.setComponentPopupMenu(jPopupMenu3);
        TSubjektif.setName("TSubjektif"); // NOI18N
        TSubjektif.setPreferredSize(new java.awt.Dimension(162, 6000));
        TSubjektif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSubjektifKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TSubjektif);

        internalFrame6.add(scrollPane4);

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Objektif (O) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N

        TObjektif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TObjektif.setColumns(20);
        TObjektif.setRows(5);
        TObjektif.setComponentPopupMenu(jPopupMenu4);
        TObjektif.setName("TObjektif"); // NOI18N
        TObjektif.setPreferredSize(new java.awt.Dimension(162, 6000));
        TObjektif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TObjektifKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TObjektif);

        internalFrame6.add(scrollPane5);

        scrollPane6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Asesment (A) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane6.setName("scrollPane6"); // NOI18N

        TAsesmen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAsesmen.setColumns(20);
        TAsesmen.setRows(5);
        TAsesmen.setComponentPopupMenu(jPopupMenu5);
        TAsesmen.setName("TAsesmen"); // NOI18N
        TAsesmen.setPreferredSize(new java.awt.Dimension(162, 6000));
        TAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAsesmenKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(TAsesmen);

        internalFrame6.add(scrollPane6);

        scrollPane7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Instruksi/Planning (P) ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane7.setName("scrollPane7"); // NOI18N

        TPlaning.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPlaning.setColumns(20);
        TPlaning.setRows(5);
        TPlaning.setComponentPopupMenu(jPopupMenu6);
        TPlaning.setName("TPlaning"); // NOI18N
        TPlaning.setPreferredSize(new java.awt.Dimension(162, 6000));
        TPlaning.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPlaningKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(TPlaning);

        internalFrame6.add(scrollPane7);

        TabPilihan.addTab("SOAP Pilihan 2", internalFrame6);

        FormInput.add(TabPilihan, java.awt.BorderLayout.CENTER);

        internalFrame7.setBorder(null);
        internalFrame7.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame7.setComponentPopupMenu(jPopupMenu1);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(0, 70));
        internalFrame7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        internalFrame7.add(jLabel5);
        jLabel5.setBounds(0, 10, 180, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. CPPT :");
        jLabel8.setName("jLabel8"); // NOI18N
        internalFrame7.add(jLabel8);
        jLabel8.setBounds(0, 38, 180, 23);

        tglCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tglCppt.setDisplayFormat("dd-MM-yyyy");
        tglCppt.setName("tglCppt"); // NOI18N
        tglCppt.setOpaque(false);
        tglCppt.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame7.add(tglCppt);
        tglCppt.setBounds(185, 38, 90, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        internalFrame7.add(TNoRw);
        TNoRw.setBounds(185, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        internalFrame7.add(TNoRm);
        TNoRm.setBounds(312, 10, 70, 23);

        ChkJam.setBackground(new java.awt.Color(255, 255, 250));
        ChkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJam.setForeground(new java.awt.Color(0, 0, 0));
        ChkJam.setText("Jam CPPT :");
        ChkJam.setBorderPainted(true);
        ChkJam.setBorderPaintedFlat(true);
        ChkJam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkJam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJam.setName("ChkJam"); // NOI18N
        ChkJam.setOpaque(false);
        ChkJam.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamActionPerformed(evt);
            }
        });
        internalFrame7.add(ChkJam);
        ChkJam.setBounds(275, 38, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        internalFrame7.add(cmbJam);
        cmbJam.setBounds(371, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        internalFrame7.add(cmbMnt);
        cmbMnt.setBounds(422, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        internalFrame7.add(cmbDtk);
        cmbDtk.setBounds(474, 38, 45, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("CPPT Shift :");
        jLabel24.setName("jLabel24"); // NOI18N
        internalFrame7.add(jLabel24);
        jLabel24.setBounds(525, 38, 80, 23);

        cmbSift.setForeground(new java.awt.Color(0, 0, 0));
        cmbSift.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3" }));
        cmbSift.setName("cmbSift"); // NOI18N
        cmbSift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSiftActionPerformed(evt);
            }
        });
        internalFrame7.add(cmbSift);
        cmbSift.setBounds(612, 38, 40, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        internalFrame7.add(TPasien);
        TPasien.setBounds(386, 10, 407, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("SOAP :");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame7.add(jLabel34);
        jLabel34.setBounds(655, 38, 50, 23);

        cmbSoap.setForeground(new java.awt.Color(0, 0, 0));
        cmbSoap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilihan 1", "Pilihan 2" }));
        cmbSoap.setName("cmbSoap"); // NOI18N
        cmbSoap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSoapActionPerformed(evt);
            }
        });
        internalFrame7.add(cmbSoap);
        cmbSoap.setBounds(712, 38, 80, 23);

        FormInput.add(internalFrame7, java.awt.BorderLayout.PAGE_START);

        internalFrame8.setBorder(null);
        internalFrame8.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame8.setComponentPopupMenu(jPopupMenu1);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setPreferredSize(new java.awt.Dimension(0, 215));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        TabPetugas.setBackground(new java.awt.Color(254, 255, 254));
        TabPetugas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPetugas.setName("TabPetugas"); // NOI18N
        TabPetugas.setPreferredSize(new java.awt.Dimension(0, 2000));
        TabPetugas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPetugasMouseClicked(evt);
            }
        });

        internalFrame20.setBorder(null);
        internalFrame20.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame20.setComponentPopupMenu(jPopupMenu1);
        internalFrame20.setName("internalFrame20"); // NOI18N
        internalFrame20.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame20.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama DPJP Utama :");
        jLabel3.setName("jLabel3"); // NOI18N
        internalFrame20.add(jLabel3);
        jLabel3.setBounds(0, 8, 180, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        internalFrame20.add(kddpjp);
        kddpjp.setBounds(186, 8, 130, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        internalFrame20.add(nmdpjp);
        nmdpjp.setBounds(320, 8, 360, 23);

        BtnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJP.setMnemonic('1');
        BtnDPJP.setToolTipText("Alt+1");
        BtnDPJP.setName("BtnDPJP"); // NOI18N
        BtnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnDPJP);
        BtnDPJP.setBounds(684, 8, 28, 23);

        BtnHapusDPJP.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusDPJP.setToolTipText("Hapus DPJP Utama");
        BtnHapusDPJP.setName("BtnHapusDPJP"); // NOI18N
        BtnHapusDPJP.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusDPJPActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusDPJP);
        BtnHapusDPJP.setBounds(721, 8, 28, 23);

        BtnHapusKonsulen.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKonsulen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusKonsulen.setToolTipText("Hapus DPJP Konsulen");
        BtnHapusKonsulen.setName("BtnHapusKonsulen"); // NOI18N
        BtnHapusKonsulen.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusKonsulen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKonsulenActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusKonsulen);
        BtnHapusKonsulen.setBounds(721, 36, 28, 23);

        BtnDPJPLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnDPJPLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDPJPLain.setMnemonic('1');
        BtnDPJPLain.setToolTipText("Alt+1");
        BtnDPJPLain.setName("BtnDPJPLain"); // NOI18N
        BtnDPJPLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDPJPLainActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnDPJPLain);
        BtnDPJPLain.setBounds(684, 36, 28, 23);

        nmDPJPlainya.setEditable(false);
        nmDPJPlainya.setForeground(new java.awt.Color(0, 0, 0));
        nmDPJPlainya.setName("nmDPJPlainya"); // NOI18N
        internalFrame20.add(nmDPJPlainya);
        nmDPJPlainya.setBounds(370, 36, 310, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("DPJP Lainnya :");
        jLabel18.setName("jLabel18"); // NOI18N
        internalFrame20.add(jLabel18);
        jLabel18.setBounds(274, 36, 90, 23);

        cmbBagian.setForeground(new java.awt.Color(0, 0, 0));
        cmbBagian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dokter IGD", "DPJP (K)", "PPA", "DPJP", "DPJP Raber" }));
        cmbBagian.setName("cmbBagian"); // NOI18N
        cmbBagian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBagianActionPerformed(evt);
            }
        });
        internalFrame20.add(cmbBagian);
        cmbBagian.setBounds(186, 36, 88, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jenis Bagian :");
        jLabel17.setName("jLabel17"); // NOI18N
        internalFrame20.add(jLabel17);
        jLabel17.setBounds(0, 36, 180, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jenis PPA :");
        jLabel4.setName("jLabel4"); // NOI18N
        internalFrame20.add(jLabel4);
        jLabel4.setBounds(0, 64, 180, 23);

        cmbPPA.setForeground(new java.awt.Color(0, 0, 0));
        cmbPPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Perawat", "Bidan", "Apoteker", "Nutrisionis", "Fisioterapis", "Dokter IRNA", "DPJP", "DPJP Raber" }));
        cmbPPA.setName("cmbPPA"); // NOI18N
        cmbPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPPAActionPerformed(evt);
            }
        });
        internalFrame20.add(cmbPPA);
        cmbPPA.setBounds(186, 64, 95, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama PPA :");
        jLabel16.setName("jLabel16"); // NOI18N
        internalFrame20.add(jLabel16);
        jLabel16.setBounds(280, 64, 70, 23);

        nmppa.setEditable(false);
        nmppa.setForeground(new java.awt.Color(0, 0, 0));
        nmppa.setName("nmppa"); // NOI18N
        internalFrame20.add(nmppa);
        nmppa.setBounds(355, 64, 325, 23);

        BtnPPA.setForeground(new java.awt.Color(0, 0, 0));
        BtnPPA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPPA.setMnemonic('1');
        BtnPPA.setToolTipText("Alt+1");
        BtnPPA.setName("BtnPPA"); // NOI18N
        BtnPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPPAActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnPPA);
        BtnPPA.setBounds(684, 64, 28, 23);

        BtnHapusPPA.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPPA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPPA.setToolTipText("Hapus Nama PPA");
        BtnHapusPPA.setName("BtnHapusPPA"); // NOI18N
        BtnHapusPPA.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPPAActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusPPA);
        BtnHapusPPA.setBounds(721, 64, 28, 23);

        ChkSamaPPA.setBackground(new java.awt.Color(255, 255, 250));
        ChkSamaPPA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSamaPPA.setForeground(new java.awt.Color(0, 0, 0));
        ChkSamaPPA.setText("Sama Dengan Petugas PPA");
        ChkSamaPPA.setBorderPainted(true);
        ChkSamaPPA.setBorderPaintedFlat(true);
        ChkSamaPPA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSamaPPA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSamaPPA.setName("ChkSamaPPA"); // NOI18N
        ChkSamaPPA.setOpaque(false);
        ChkSamaPPA.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkSamaPPA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSamaPPAActionPerformed(evt);
            }
        });
        internalFrame20.add(ChkSamaPPA);
        ChkSamaPPA.setBounds(520, 92, 160, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbDtk1);
        cmbDtk1.setBounds(458, 92, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbMnt1);
        cmbMnt1.setBounds(406, 92, 45, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        internalFrame20.add(cmbJam1);
        cmbJam1.setBounds(355, 92, 45, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam Serah Terima :");
        jLabel20.setName("jLabel20"); // NOI18N
        internalFrame20.add(jLabel20);
        jLabel20.setBounds(250, 92, 100, 23);

        cmbSertim.setForeground(new java.awt.Color(0, 0, 0));
        cmbSertim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbSertim.setName("cmbSertim"); // NOI18N
        cmbSertim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSertimActionPerformed(evt);
            }
        });
        internalFrame20.add(cmbSertim);
        cmbSertim.setBounds(186, 92, 60, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Serah Terima CPPT :");
        jLabel9.setName("jLabel9"); // NOI18N
        internalFrame20.add(jLabel9);
        jLabel9.setBounds(0, 92, 180, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Petugas Yang Menyerahkan :");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame20.add(jLabel22);
        jLabel22.setBounds(0, 120, 180, 23);

        nipSerah.setEditable(false);
        nipSerah.setForeground(new java.awt.Color(0, 0, 0));
        nipSerah.setName("nipSerah"); // NOI18N
        internalFrame20.add(nipSerah);
        nipSerah.setBounds(186, 120, 130, 23);

        nmSerah.setEditable(false);
        nmSerah.setForeground(new java.awt.Color(0, 0, 0));
        nmSerah.setName("nmSerah"); // NOI18N
        internalFrame20.add(nmSerah);
        nmSerah.setBounds(320, 120, 360, 23);

        BtnSerah.setForeground(new java.awt.Color(0, 0, 0));
        BtnSerah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSerah.setMnemonic('1');
        BtnSerah.setToolTipText("Alt+1");
        BtnSerah.setName("BtnSerah"); // NOI18N
        BtnSerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSerahActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnSerah);
        BtnSerah.setBounds(684, 120, 28, 23);

        BtnHapusSerah.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusSerah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusSerah.setToolTipText("Hapus Nama Petugas Yang Menyerahkan");
        BtnHapusSerah.setName("BtnHapusSerah"); // NOI18N
        BtnHapusSerah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusSerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusSerahActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusSerah);
        BtnHapusSerah.setBounds(721, 120, 28, 23);

        BtnHapusTerima.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusTerima.setToolTipText("Hapus Nama Petugas Yang Menerima");
        BtnHapusTerima.setName("BtnHapusTerima"); // NOI18N
        BtnHapusTerima.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusTerimaActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnHapusTerima);
        BtnHapusTerima.setBounds(721, 148, 28, 23);

        BtnTerima.setForeground(new java.awt.Color(0, 0, 0));
        BtnTerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTerima.setMnemonic('1');
        BtnTerima.setToolTipText("Alt+1");
        BtnTerima.setName("BtnTerima"); // NOI18N
        BtnTerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTerimaActionPerformed(evt);
            }
        });
        internalFrame20.add(BtnTerima);
        BtnTerima.setBounds(684, 148, 28, 23);

        nmTerima.setEditable(false);
        nmTerima.setForeground(new java.awt.Color(0, 0, 0));
        nmTerima.setName("nmTerima"); // NOI18N
        internalFrame20.add(nmTerima);
        nmTerima.setBounds(320, 148, 360, 23);

        nipTerima.setEditable(false);
        nipTerima.setForeground(new java.awt.Color(0, 0, 0));
        nipTerima.setName("nipTerima"); // NOI18N
        internalFrame20.add(nipTerima);
        nipTerima.setBounds(186, 148, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Petugas Yang Menerima :");
        jLabel23.setName("jLabel23"); // NOI18N
        internalFrame20.add(jLabel23);
        jLabel23.setBounds(0, 148, 180, 23);

        chkDpjp.setBackground(new java.awt.Color(242, 242, 242));
        chkDpjp.setForeground(new java.awt.Color(0, 0, 0));
        chkDpjp.setText("Saya Sendiri");
        chkDpjp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDpjp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDpjp.setName("chkDpjp"); // NOI18N
        chkDpjp.setOpaque(false);
        chkDpjp.setPreferredSize(new java.awt.Dimension(220, 23));
        chkDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDpjpActionPerformed(evt);
            }
        });
        internalFrame20.add(chkDpjp);
        chkDpjp.setBounds(760, 8, 90, 23);

        TabPetugas.addTab("Petugas", internalFrame20);

        internalFrame21.setBorder(null);
        internalFrame21.setName("internalFrame21"); // NOI18N
        internalFrame21.setLayout(new java.awt.GridLayout(1, 4));

        internalFrame22.setBorder(null);
        internalFrame22.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        internalFrame22.setComponentPopupMenu(jPopupMenu1);
        internalFrame22.setName("internalFrame22"); // NOI18N
        internalFrame22.setPreferredSize(new java.awt.Dimension(900, 900));
        internalFrame22.setLayout(new java.awt.BorderLayout());

        internalFrame23.setBorder(null);
        internalFrame23.setToolTipText("");
        internalFrame23.setComponentPopupMenu(jPopupMenu1);
        internalFrame23.setName("internalFrame23"); // NOI18N
        internalFrame23.setPreferredSize(new java.awt.Dimension(0, 136));
        internalFrame23.setLayout(null);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tgl. Lapor :");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame23.add(jLabel35);
        jLabel35.setBounds(0, 8, 120, 23);

        tglLapor.setEditable(false);
        tglLapor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tglLapor.setDisplayFormat("dd-MM-yyyy");
        tglLapor.setName("tglLapor"); // NOI18N
        tglLapor.setOpaque(false);
        tglLapor.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame23.add(tglLapor);
        tglLapor.setBounds(127, 8, 90, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Jam Lapor :");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame23.add(jLabel37);
        jLabel37.setBounds(218, 8, 100, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbJam2);
        cmbJam2.setBounds(325, 8, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbMnt2);
        cmbMnt2.setBounds(376, 8, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbDtk2);
        cmbDtk2.setBounds(428, 8, 45, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Tgl. Verifikasi :");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame23.add(jLabel38);
        jLabel38.setBounds(0, 36, 120, 23);

        tglVerifikasi.setEditable(false);
        tglVerifikasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        tglVerifikasi.setDisplayFormat("dd-MM-yyyy");
        tglVerifikasi.setName("tglVerifikasi"); // NOI18N
        tglVerifikasi.setOpaque(false);
        tglVerifikasi.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame23.add(tglVerifikasi);
        tglVerifikasi.setBounds(127, 36, 90, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Jam Verifikasi :");
        jLabel39.setName("jLabel39"); // NOI18N
        internalFrame23.add(jLabel39);
        jLabel39.setBounds(218, 36, 100, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbJam3);
        cmbJam3.setBounds(325, 36, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbMnt3);
        cmbMnt3.setBounds(376, 36, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        internalFrame23.add(cmbDtk3);
        cmbDtk3.setBounds(428, 36, 45, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Petugas Konfirmasi :");
        jLabel40.setName("jLabel40"); // NOI18N
        internalFrame23.add(jLabel40);
        jLabel40.setBounds(0, 64, 120, 23);

        nipPetugasKonfir.setEditable(false);
        nipPetugasKonfir.setForeground(new java.awt.Color(0, 0, 0));
        nipPetugasKonfir.setName("nipPetugasKonfir"); // NOI18N
        internalFrame23.add(nipPetugasKonfir);
        nipPetugasKonfir.setBounds(127, 64, 130, 23);

        nmPetugasKonfir.setEditable(false);
        nmPetugasKonfir.setForeground(new java.awt.Color(0, 0, 0));
        nmPetugasKonfir.setName("nmPetugasKonfir"); // NOI18N
        internalFrame23.add(nmPetugasKonfir);
        nmPetugasKonfir.setBounds(260, 64, 360, 23);

        BtnPetugasKonfir.setForeground(new java.awt.Color(0, 0, 0));
        BtnPetugasKonfir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugasKonfir.setMnemonic('1');
        BtnPetugasKonfir.setToolTipText("Alt+1");
        BtnPetugasKonfir.setName("BtnPetugasKonfir"); // NOI18N
        BtnPetugasKonfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasKonfirActionPerformed(evt);
            }
        });
        internalFrame23.add(BtnPetugasKonfir);
        BtnPetugasKonfir.setBounds(620, 64, 28, 23);

        BtnHapusPetugasKonfir.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusPetugasKonfir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusPetugasKonfir.setToolTipText("Hapus Nama Petugas Yang Menyerahkan");
        BtnHapusPetugasKonfir.setName("BtnHapusPetugasKonfir"); // NOI18N
        BtnHapusPetugasKonfir.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusPetugasKonfir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusPetugasKonfirActionPerformed(evt);
            }
        });
        internalFrame23.add(BtnHapusPetugasKonfir);
        BtnHapusPetugasKonfir.setBounds(655, 64, 28, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Dengan DPJP :");
        jLabel41.setName("jLabel41"); // NOI18N
        internalFrame23.add(jLabel41);
        jLabel41.setBounds(0, 92, 120, 23);

        nipKonfirDpjp.setEditable(false);
        nipKonfirDpjp.setForeground(new java.awt.Color(0, 0, 0));
        nipKonfirDpjp.setName("nipKonfirDpjp"); // NOI18N
        internalFrame23.add(nipKonfirDpjp);
        nipKonfirDpjp.setBounds(127, 92, 130, 23);

        nmKonfirDpjp.setEditable(false);
        nmKonfirDpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmKonfirDpjp.setName("nmKonfirDpjp"); // NOI18N
        internalFrame23.add(nmKonfirDpjp);
        nmKonfirDpjp.setBounds(260, 92, 360, 23);

        BtnKonfirDpjp.setForeground(new java.awt.Color(0, 0, 0));
        BtnKonfirDpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKonfirDpjp.setMnemonic('1');
        BtnKonfirDpjp.setToolTipText("Alt+1");
        BtnKonfirDpjp.setName("BtnKonfirDpjp"); // NOI18N
        BtnKonfirDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKonfirDpjpActionPerformed(evt);
            }
        });
        internalFrame23.add(BtnKonfirDpjp);
        BtnKonfirDpjp.setBounds(620, 92, 28, 23);

        BtnHapusKonfirDpjp.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusKonfirDpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusKonfirDpjp.setToolTipText("Hapus Nama Petugas Yang Menyerahkan");
        BtnHapusKonfirDpjp.setName("BtnHapusKonfirDpjp"); // NOI18N
        BtnHapusKonfirDpjp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnHapusKonfirDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusKonfirDpjpActionPerformed(evt);
            }
        });
        internalFrame23.add(BtnHapusKonfirDpjp);
        BtnHapusKonfirDpjp.setBounds(655, 92, 28, 23);

        internalFrame22.add(internalFrame23, java.awt.BorderLayout.PAGE_START);

        internalFrame24.setBorder(null);
        internalFrame24.setToolTipText("");
        internalFrame24.setComponentPopupMenu(jPopupMenu1);
        internalFrame24.setName("internalFrame24"); // NOI18N
        internalFrame24.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame24.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        BtnSimpan3.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan3.setMnemonic('S');
        BtnSimpan3.setText("Simpan Konf.");
        BtnSimpan3.setToolTipText("Alt+S");
        BtnSimpan3.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnSimpan3.setName("BtnSimpan3"); // NOI18N
        BtnSimpan3.setPreferredSize(new java.awt.Dimension(125, 30));
        BtnSimpan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan3ActionPerformed(evt);
            }
        });
        internalFrame24.add(BtnSimpan3);

        BtnBatal2.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal2.setMnemonic('B');
        BtnBatal2.setText("Konf. Baru");
        BtnBatal2.setToolTipText("Alt+B");
        BtnBatal2.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnBatal2.setName("BtnBatal2"); // NOI18N
        BtnBatal2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal2ActionPerformed(evt);
            }
        });
        internalFrame24.add(BtnBatal2);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setMnemonic('H');
        BtnHapus1.setText("Hapus Konf.");
        BtnHapus1.setToolTipText("Alt+H");
        BtnHapus1.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        internalFrame24.add(BtnHapus1);

        BtnEdit2.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit2.setMnemonic('G');
        BtnEdit2.setText("Ganti Konf.");
        BtnEdit2.setToolTipText("Alt+G");
        BtnEdit2.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnEdit2.setName("BtnEdit2"); // NOI18N
        BtnEdit2.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnEdit2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit2ActionPerformed(evt);
            }
        });
        internalFrame24.add(BtnEdit2);

        BtnLihatKon.setForeground(new java.awt.Color(0, 0, 0));
        BtnLihatKon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnLihatKon.setMnemonic('G');
        BtnLihatKon.setText("Lihat Konf.");
        BtnLihatKon.setToolTipText("Alt+G");
        BtnLihatKon.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnLihatKon.setName("BtnLihatKon"); // NOI18N
        BtnLihatKon.setPreferredSize(new java.awt.Dimension(115, 30));
        BtnLihatKon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLihatKonActionPerformed(evt);
            }
        });
        internalFrame24.add(BtnLihatKon);

        internalFrame22.add(internalFrame24, java.awt.BorderLayout.CENTER);

        internalFrame21.add(internalFrame22);

        Scroll4.setComponentPopupMenu(jPopupMenu1);
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(460, 422));

        tbKonfirmasi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbKonfirmasi.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKonfirmasi.setComponentPopupMenu(jPopupMenu1);
        tbKonfirmasi.setName("tbKonfirmasi"); // NOI18N
        tbKonfirmasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKonfirmasiMouseClicked(evt);
            }
        });
        tbKonfirmasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKonfirmasiKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbKonfirmasiKeyReleased(evt);
            }
        });
        Scroll4.setViewportView(tbKonfirmasi);

        internalFrame21.add(Scroll4);

        TabPetugas.addTab("Konfirmasi Terapi Via Telpon", internalFrame21);

        internalFrame8.add(TabPetugas, java.awt.BorderLayout.PAGE_START);

        FormInput.add(internalFrame8, java.awt.BorderLayout.PAGE_END);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabCPPT.addTab("Input Data CPPT", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setComponentPopupMenu(jPopupMenu1);
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 200));

        tbCPPT.setAutoCreateRowSorter(true);
        tbCPPT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbCPPT.setComponentPopupMenu(jPopupMenu1);
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
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbCPPTKeyReleased(evt);
            }
        });
        Scroll2.setViewportView(tbCPPT);

        internalFrame3.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. CPPT :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-09-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Jns. Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel15);

        cmbRawat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRawat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "R. Jalan", "R. Inap", "Semua" }));
        cmbRawat.setName("cmbRawat"); // NOI18N
        cmbRawat.setPreferredSize(new java.awt.Dimension(77, 23));
        cmbRawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRawatActionPerformed(evt);
            }
        });
        panelGlass10.add(cmbRawat);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Shift :");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass10.add(jLabel25);

        cmbSiftCppt.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiftCppt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1", "2", "3", "Semua" }));
        cmbSiftCppt.setName("cmbSiftCppt"); // NOI18N
        cmbSiftCppt.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(cmbSiftCppt);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+2");
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
        panelGlass10.add(BtnAll);

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

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        TabCPPT.addTab("Data CPPT", internalFrame3);

        internalFrame26.setBorder(null);
        internalFrame26.setName("internalFrame26"); // NOI18N
        internalFrame26.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pasien :");
        jLabel14.setName("jLabel14"); // NOI18N
        jLabel14.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel14);

        TNoRw1.setEditable(false);
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        TNoRw1.setPreferredSize(new java.awt.Dimension(122, 24));
        panelGlass13.add(TNoRw1);

        TNoRm1.setEditable(false);
        TNoRm1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm1.setName("TNoRm1"); // NOI18N
        TNoRm1.setPreferredSize(new java.awt.Dimension(70, 24));
        panelGlass13.add(TNoRm1);

        TPasien1.setEditable(false);
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        TPasien1.setPreferredSize(new java.awt.Dimension(407, 24));
        panelGlass13.add(TPasien1);

        internalFrame26.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

        Scroll10.setComponentPopupMenu(jPopupMenu1);
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);
        Scroll10.setPreferredSize(new java.awt.Dimension(452, 200));

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll10.setViewportView(LoadHTML1);

        internalFrame26.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabCPPT.addTab("Preview CPPT", internalFrame26);

        internalFrame1.add(TabCPPT, java.awt.BorderLayout.CENTER);
        TabCPPT.getAccessibleContext().setAccessibleName("Input Data CPPT");

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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
        panelGlass8.add(BtnPrint);

        BtnVerif.setForeground(new java.awt.Color(0, 0, 0));
        BtnVerif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/checked.png"))); // NOI18N
        BtnVerif.setMnemonic('V');
        BtnVerif.setText("Verifikasi");
        BtnVerif.setToolTipText("Alt+V");
        BtnVerif.setName("BtnVerif"); // NOI18N
        BtnVerif.setPreferredSize(new java.awt.Dimension(110, 30));
        BtnVerif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVerifActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnVerif);

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResep);

        BtnNotepad.setForeground(new java.awt.Color(0, 0, 0));
        BtnNotepad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnNotepad.setMnemonic('N');
        BtnNotepad.setText("Notepad");
        BtnNotepad.setToolTipText("Alt+N");
        BtnNotepad.setName("BtnNotepad"); // NOI18N
        BtnNotepad.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNotepad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNotepadActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnNotepad);

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

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }
            
            if (ChkJam.isSelected() == true) {
                cekjam = "ya";
            } else {
                cekjam = "tidak";
            }
            
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                statusOK = "Ralan";
            } else if (status.equals("ranap")) {
                statusOK = "Ranap";
            }
            
            if (cmbPPA.getSelectedIndex() == 3 || cmbPPA.getSelectedIndex() == 4 || cmbPPA.getSelectedIndex() == 5) {
                siftppa = "1";
            } else {
                siftppa = cmbSift.getSelectedItem().toString();
            }
            
            if (cmbSoap.getSelectedIndex() == 0) {
                soap = "Pilihan 1";
                hasil_pemeriksaan = THasil.getText();
                instruksi_nakes = TInstruksi.getText();
            } else {
                soap = "Pilihan 2";
                hasil_pemeriksaan = "S : " + TSubjektif.getText() + "\n\n"
                        + "O : " + TObjektif.getText() + "\n\n"
                        + "A : " + TAsesmen.getText() + "\n";
                instruksi_nakes = TPlaning.getText();
            }

            try {
                Sequel.menyimpan("cppt", "'" + TNoRw.getText() + "',"
                        + "'" + Valid.SetTgl(tglCppt.getSelectedItem() + "") + "',"
                        + "'-','" + Valid.mysql_real_escape_stringERM(hasil_pemeriksaan) + "','" + Valid.mysql_real_escape_stringERM(instruksi_nakes) + "',"
                        + "'Belum','" + kddpjp.getText() + "','" + statusOK + "',"
                        + "'" + Sequel.cariIsi("select now()") + "','" + cekjam + "',"
                        + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                        + "'" + cmbPPA.getSelectedItem().toString() + "','" + nipppa + "','" + cmbBagian.getSelectedItem().toString() + "',"
                        + "'" + cmbSertim.getSelectedItem().toString() + "','" + nipDPJPlain + "','" + nipSerah.getText() + "',"
                        + "'" + nipTerima.getText() + "','" + siftppa + "',"
                        + "'" + cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem() + "',"
                        + "'tidak','-','" + soap + "','" + Valid.mysql_real_escape_stringERM(TSubjektif.getText()) + "',"
                        + "'" + Valid.mysql_real_escape_stringERM(TObjektif.getText()) + "','" + Valid.mysql_real_escape_stringERM(TAsesmen.getText()) + "',"
                        + "'" + Valid.mysql_real_escape_stringERM(TPlaning.getText()) + "'", "CPPT Pasien");

                //menyamakan tgl, jam & sift cppt dengan data konfirmasi terapi
                if (tbKonfirmasi.getRowCount() != 0) {
                    try {
                        for (i = 0; i < tbKonfirmasi.getRowCount(); i++) {
                            Sequel.mengedit("cppt_konfirmasi_terapi", "waktu_simpan=?", "no_rawat=?, tgl_cppt=?, "
                                    + "cppt_shift=?, jam_cppt=?", 5, new String[]{
                                        TNoRw.getText(), Valid.SetTgl(tglCppt.getSelectedItem() + ""), cmbSift.getSelectedItem().toString(),
                                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                        tbKonfirmasi.getValueAt(i, 18).toString()
                                    });
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                }

                TCari.setText(TNoRw.getText());
                cmbSiftCppt.setSelectedItem(cmbSift.getSelectedItem());
                tampil();
                emptTeks();
                TabCPPT.setSelectedIndex(1);

            } catch (Exception e) {
                System.out.println("Simpan CPPT : " + e);
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCPPT.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                x = JOptionPane.showConfirmDialog(rootPane, "Pilih YES untuk hapus biasa, NO untuk hapus lenyap..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.mengedit("cppt", "waktu_simpan=?", "flag_hapus=?, nip_penghapus=?", 3, new String[]{
                        "ya", "-", tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString()
                    });
                    tampil();
                    emptTeks();
                } else {
                    if (Sequel.queryu2tf("delete from cppt where waktu_simpan=?", 1, new String[]{
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString()
                    }) == true) {
                        tampil();
                        emptTeks();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                    }
                }
            } else {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.mengedit("cppt", "waktu_simpan=?", "flag_hapus=?, nip_penghapus=?", 3, new String[]{
                        "ya", akses.getkode(), tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString()
                    });
                    tampil();
                    emptTeks();
                } else {
                    tampil();
                    emptTeks();
                }
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (kddpjp.getText().equals("")) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                kddpjp.setText(kddpjp.getText());
                nmdpjp.setText(nmdpjp.getText());
            }
            
            if (ChkJam.isSelected() == true) {
                cekjam = "ya";
            } else {
                cekjam = "tidak";
            }
            
            if (cmbSoap.getSelectedIndex() == 0) {
                soap = "Pilihan 1";
                hasil_pemeriksaan = THasil.getText();
                instruksi_nakes = TInstruksi.getText();
            } else {
                soap = "Pilihan 2";
                hasil_pemeriksaan = "S : " + TSubjektif.getText() + "\n\n"
                        + "O : " + TObjektif.getText() + "\n\n"
                        + "A : " + TAsesmen.getText() + "\n";
                instruksi_nakes = TPlaning.getText();
            }
            
            try {
                if (tbCPPT.getSelectedRow() > -1) {
                    //sebelum diganti data cppt sebelumnya disimpan dulu ke tabel cppt_history
                    simpanHistory(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString());
                    
                    Sequel.mengedit("cppt", "waktu_simpan=?", "tgl_cppt=?, hasil_pemeriksaan=?, "
                            + "instruksi_nakes=?, nip_dpjp=?, cek_jam=?, jam_cppt=?, jenis_ppa=?, nip_ppa=?, jenis_bagian=?, "
                            + "serah_terima_cppt=?, nip_konsulen=?, nip_petugas_serah=?, nip_petugas_terima=?, cppt_shift=?, jam_serah_terima=?, "
                            + "pilihan_soap=?, subjektif=?, objektif=?, asesmen=?, planing=?", 21, new String[]{
                                Valid.SetTgl(tglCppt.getSelectedItem() + ""), Valid.mysql_real_escape_stringERM(hasil_pemeriksaan), Valid.mysql_real_escape_stringERM(instruksi_nakes),
                                kddpjp.getText(), cekjam, cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                cmbPPA.getSelectedItem().toString(), nipppa, cmbBagian.getSelectedItem().toString(),
                                cmbSertim.getSelectedItem().toString(), nipDPJPlain, nipSerah.getText(), nipTerima.getText(),
                                cmbSift.getSelectedItem().toString(), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                                soap, Valid.mysql_real_escape_stringERM(TSubjektif.getText()), Valid.mysql_real_escape_stringERM(TObjektif.getText()),
                                Valid.mysql_real_escape_stringERM(TAsesmen.getText()), Valid.mysql_real_escape_stringERM(TPlaning.getText()),
                                tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 15).toString()
                            });

                    //menyamakan tgl, jam & sift cppt dengan data konfirmasi terapi
                    if (tbKonfirmasi.getRowCount() != 0) {
                        try {
                            for (i = 0; i < tbKonfirmasi.getRowCount(); i++) {
                                Sequel.mengedit("cppt_konfirmasi_terapi", "waktu_simpan=?", "no_rawat=?, tgl_cppt=?, "
                                        + "cppt_shift=?, jam_cppt=?", 5, new String[]{
                                            TNoRw.getText(), Valid.SetTgl(tglCppt.getSelectedItem() + ""), cmbSift.getSelectedItem().toString(),
                                            cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                            tbKonfirmasi.getValueAt(i, 18).toString()
                                        });
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi : " + e);
                        }
                    }

                    TCari.setText(TNoRw.getText());
                    cmbSiftCppt.setSelectedItem(cmbSift.getSelectedItem());
                    tampil();
                    emptTeks();
                    TabCPPT.setSelectedIndex(1);
                }
            } catch (Exception e) {
                System.out.println("Ganti CPPT : " + e);
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowTemplate.dispose();
        WindowDataMonevGizi.dispose();
        WindowCPPT.dispose();
        WindowFarmasi.dispose();
        WindowDiet.dispose();
        WindowDataDiet.dispose();
        WindowDataSampah.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbCPPT.requestFocus();
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

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCari.setText("");
                TCari.requestFocus();
            }           
        }
}//GEN-LAST:event_tbCPPTKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        MnCeklisFarmasi.setEnabled(false);        
        Sequel.cariIsiComboDB("select satuan from diet_master_pemberian group by satuan", cmbSatuan);
    }//GEN-LAST:event_formWindowOpened

    private void tbCPPTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbCPPTKeyReleased

    private void BtnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPActionPerformed
        pilihan = 1;
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPActionPerformed

    private void THasilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_THasilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TInstruksi.requestFocus();
        }
    }//GEN-LAST:event_THasilKeyPressed

    private void TInstruksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInstruksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDPJP.requestFocus();
        }
    }//GEN-LAST:event_TInstruksiKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, tabel masih kosong...!!!!");
            TCari.requestFocus();
        } else if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih dulu salah satu datanya pada tabel...!!!!");
            TabCPPT.setSelectedIndex(1);
            tbCPPT.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data cppt pasien tersebut belum disimpan...!!!!");
        } else {
            if (cmbRawat.getSelectedIndex() == 0) {
                cetakCPPTralan();
            } else {
                WindowCPPT.setSize(456, 139);
                WindowCPPT.setLocationRelativeTo(internalFrame1);
                WindowCPPT.setAlwaysOnTop(false);
                WindowCPPT.setVisible(true);

                cmbJnsCppt.setSelectedIndex(0);
                cmbTanggal.setSelectedIndex(0);
                cmbSiftPetugas.setSelectedIndex(0);
                tglA.setDate(new Date());
                tglB.setDate(new Date());

                cmbTanggal.setEnabled(true);
                tglA.setEnabled(false);
                tglB.setEnabled(false);
                cmbSiftPetugas.setEnabled(false);
                cmbJnsCppt.requestFocus();
            }

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHasilActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Hasil Pemeriksaan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_BtnHasilActionPerformed

    private void BtnInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInstruksiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Instruksi Nakes ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_BtnInstruksiActionPerformed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnCopasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopasActionPerformed
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah data ini akan dipakai..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            copas();
            WindowTemplate.dispose();
        }
    }//GEN-LAST:event_BtnCopasActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        WindowTemplate.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void tbTemplateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTemplateMouseClicked
        if(tabMode1.getRowCount() != 0) {
            try {
                if (tbTemplate.getSelectedRow() != -1) {
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 6).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    private void ChkJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamActionPerformed
        if (ChkJam.isSelected() == true) {
            cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }
    }//GEN-LAST:event_ChkJamActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPPAActionPerformed
        pilihan = 1;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPPAActionPerformed

    private void BtnDPJPLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDPJPLainActionPerformed
        pilihan = 2;
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDPJPLainActionPerformed

    private void BtnSerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSerahActionPerformed
        pilihan = 2;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnSerahActionPerformed

    private void BtnTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTerimaActionPerformed
        pilihan = 3;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnTerimaActionPerformed

    private void ChkSamaPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSamaPPAActionPerformed
        if (ChkSamaPPA.isSelected() == true) {
            nipSerah.setText(nipppa);
            nmSerah.setText(nmppa.getText());
        } else {
            nipSerah.setText("-");
            nmSerah.setText("-");
        }
    }//GEN-LAST:event_ChkSamaPPAActionPerformed

    private void cmbRawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRawatActionPerformed
        if (cmbRawat.getSelectedIndex() == 1) {
            cmbSiftCppt.setSelectedIndex(0);
            cmbSiftCppt.setEnabled(true);
        } else {
            cmbSiftCppt.setSelectedIndex(0);
            cmbSiftCppt.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRawatActionPerformed

    private void cmbBagianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBagianActionPerformed
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            nipDPJPlain = "-";
            nmDPJPlainya.setText("-");
            cmbPPA.setSelectedIndex(0);
            cmbSertim.setSelectedIndex(0);
            nipppa = "-";
            nmppa.setText("-");

            if (cmbBagian.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(rootPane, "Jenis bagian PPA hanya untuk pengisian CPPT rawat inap..!!!");
                cmbBagian.setSelectedIndex(0);
                BtnDPJPLain.setEnabled(false);
                cmbBagian.requestFocus();
            } else if (cmbBagian.getSelectedIndex() == 2 || cmbBagian.getSelectedIndex() == 4 || cmbBagian.getSelectedIndex() == 5) {
                BtnDPJPLain.setEnabled(true);
            } else {
                BtnDPJPLain.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbBagianActionPerformed

    private void BtnCloseIn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn6ActionPerformed
        WindowCPPT.dispose();
        cmbJnsCppt.setSelectedIndex(0);
        cmbSiftPetugas.setSelectedIndex(0);
        cmbTanggal.setSelectedIndex(0);
        tglA.setDate(new Date());
        tglB.setDate(new Date());
    }//GEN-LAST:event_BtnCloseIn6ActionPerformed

    private void cmbJnsCpptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsCpptActionPerformed
        tglA.setDate(new Date());
        tglB.setDate(new Date());
        cmbSiftPetugas.setSelectedIndex(0);
        cmbTanggal.setSelectedIndex(0);

        if (cmbJnsCppt.getSelectedIndex() == 0) {
            cmbTanggal.setEnabled(true);
            cmbSiftPetugas.setEnabled(false);
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        } else if (cmbJnsCppt.getSelectedIndex() == 1) {
            cmbTanggal.setEnabled(false);
            cmbSiftPetugas.setEnabled(false);
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJnsCpptActionPerformed

    private void BtnCetakCPPTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakCPPTActionPerformed
        if (cmbJnsCppt.getSelectedIndex() == 0) {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ranap'") > 0) {
                cetakCPPTranap();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT rawat inap tidak ditemukan...!!!");
            }
        } else if (cmbJnsCppt.getSelectedIndex() == 1) {
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and STATUS='Ralan'") > 0) {
                cetakCPPTralan();
            } else {
                JOptionPane.showMessageDialog(null, "Data CPPT IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_BtnCetakCPPTActionPerformed

    private void cmbTanggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTanggalActionPerformed
        cmbSiftPetugas.setEnabled(true);
        if (cmbTanggal.getSelectedIndex() == 0) {
            tglA.setEnabled(false);
            tglB.setEnabled(false);
        } else if (cmbTanggal.getSelectedIndex() == 1) {
            tglA.setEnabled(true);
            tglB.setEnabled(true);
            tglA.requestFocus();
        } else if (cmbTanggal.getSelectedIndex() == 2) {
            tglA.setEnabled(true);
            tglB.setEnabled(false);
            tglA.requestFocus();
        }
    }//GEN-LAST:event_cmbTanggalActionPerformed

    private void cmbSertimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSertimActionPerformed
        if (cmbSertim.getSelectedIndex() == 1) {
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            BtnSerah.setEnabled(true);
            BtnTerima.setEnabled(true);
            ChkSamaPPA.setEnabled(true);
            
            if (cmbSift.getSelectedIndex() == 1) {
                cmbJam1.setSelectedItem("14");
                cmbMnt1.setSelectedItem("15");
            } else if (cmbSift.getSelectedIndex() == 2) {
                cmbJam1.setSelectedItem("20");
                cmbMnt1.setSelectedItem("15");
            } else if (cmbSift.getSelectedIndex() == 3) {
                cmbJam1.setSelectedItem("08");
                cmbMnt1.setSelectedItem("15");
            } else {
                cmbJam1.setSelectedIndex(0);
                cmbMnt1.setSelectedIndex(0);
            }
            
            cmbDtk1.setSelectedIndex(0);
            ChkSamaPPA.setSelected(false);
            nipSerah.setText("-");
            nmSerah.setText("-");
            nipTerima.setText("-");
            nmTerima.setText("-");
        } else {
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            BtnSerah.setEnabled(false);
            BtnTerima.setEnabled(false);
            ChkSamaPPA.setEnabled(false);
            ChkSamaPPA.setSelected(false);
            cmbJam1.setSelectedIndex(0);
            cmbMnt1.setSelectedIndex(0);
            cmbDtk1.setSelectedIndex(0);
            nipSerah.setText("-");
            nmSerah.setText("-");
            nipTerima.setText("-");
            nmTerima.setText("-");
        }
    }//GEN-LAST:event_cmbSertimActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPPAActionPerformed
        nipppa = "-";
        nmppa.setText("-");
        cmbSertim.setSelectedIndex(0);
        
        if (cmbPPA.getSelectedIndex() == 0) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(false);
            MnCeklisFarmasi.setEnabled(false);
        } else if (cmbPPA.getSelectedIndex() == 1 || cmbPPA.getSelectedIndex() == 2) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
        } else if (cmbPPA.getSelectedIndex() == 3) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(true);
        } else if (cmbPPA.getSelectedIndex() == 4) {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
        } else {
            BtnPPA.setEnabled(true);
            cmbSertim.setEnabled(true);
            MnCeklisFarmasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPPAActionPerformed

    private void TabCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabCPPTMouseClicked
        if (TabCPPT.getSelectedIndex() == 1) {
            tampil();
        } else if (TabCPPT.getSelectedIndex() == 0) {
            TabPetugas.setSelectedIndex(0);
        } else if (TabCPPT.getSelectedIndex() == 2) {
            TNoRw1.setText(TNoRw.getText());
            TNoRm1.setText(TNoRm.getText());
            TPasien1.setText(TPasien.getText());
            tampilPreviewCppt();
        }
    }//GEN-LAST:event_TabCPPTMouseClicked

    private void BtnCloseIn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn7ActionPerformed
        WindowFarmasi.dispose();
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);
    }//GEN-LAST:event_BtnCloseIn7ActionPerformed

    private void BtnSimpanCeklisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanCeklisActionPerformed
        dapatobat = "";
        obatsesuai = "";
        obatefektif = "";
        obataman = "";
        
        if (ChkDapatObat.isSelected() == true) {
            dapatobat = "- Dapat Obat : YA\n";
        } else {
            dapatobat = "- Dapat Obat : TIDAK\n";
        }
        
        if (ChkObatSesuai.isSelected() == true) {
            obatsesuai = "- Obat Sesuai : YA\n";
        } else {
            obatsesuai = "- Obat Sesuai : TIDAK\n";
        }
        
        if (ChkObatEfektif.isSelected() == true) {
            obatefektif = "- Obat Efektif : YA\n";
        } else {
            obatefektif = "- Obat Efektif : TIDAK\n";
        }
        
        if (ChkObatAman.isSelected() == true) {
            obataman = "- Obat Aman : YA\n";
        } else {
            obataman = "- Obat Aman : TIDAK";
        }

        if (cmbSoap.getSelectedIndex() == 0) {
            if (THasil.getText().equals("")) {
                THasil.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
            } else {
                THasil.setText(THasil.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
            }
            BtnCloseIn7ActionPerformed(null);
        } else if (cmbSoap.getSelectedIndex() == 1) {
            if (paste == 1) {
                if (TSubjektif.getText().equals("")) {
                    TSubjektif.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
                } else {
                    TSubjektif.setText(TSubjektif.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
                }
                BtnCloseIn7ActionPerformed(null);
            } else if (paste == 2) {
                if (TObjektif.getText().equals("")) {
                    TObjektif.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
                } else {
                    TObjektif.setText(TObjektif.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
                }
                BtnCloseIn7ActionPerformed(null);
            } else if (paste == 3) {
                if (TAsesmen.getText().equals("")) {
                    TAsesmen.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
                } else {
                    TAsesmen.setText(TAsesmen.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
                }
                BtnCloseIn7ActionPerformed(null);
            } else if (paste == 4) {
                if (TPlaning.getText().equals("")) {
                    TPlaning.setText("Pemantauan Penggunaan Obat :\n\n" + dapatobat + obatsesuai + obatefektif + obataman);
                } else {
                    TPlaning.setText(TPlaning.getText() + "\n\nPemantauan Penggunaan Obat :\n" + dapatobat + obatsesuai + obatefektif + obataman);
                }
                BtnCloseIn7ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpanCeklisActionPerformed

    private void MnCeklisFarmasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiActionPerformed
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);
        
        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiActionPerformed

    private void MnPemberianDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianDietActionPerformed
        BtnBatal1ActionPerformed(null);
        WindowDiet.setSize(500, 600);
        WindowDiet.setLocationRelativeTo(internalFrame1);
        WindowDiet.setAlwaysOnTop(false);
        WindowDiet.setVisible(true);
    }//GEN-LAST:event_MnPemberianDietActionPerformed

    private void BtnUlangiHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiHasilActionPerformed
        if (THasil.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Hasil pemeriksaan masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin hasil pemeriksaan akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                THasil.setText("");
                THasil.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnUlangiHasilActionPerformed

    private void BtnUlangiInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUlangiInstruksiActionPerformed
        if (TInstruksi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Instruksi tenaga kesehatan masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin instruksi tenaga kesehatan akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TInstruksi.setText("");
                TInstruksi.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnUlangiInstruksiActionPerformed

    private void ChkSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaActionPerformed
        if (ChkSemua.isSelected() == true) {
            ChkDapatObat.setSelected(true);
            ChkObatSesuai.setSelected(true);
            ChkObatEfektif.setSelected(true);
            ChkObatAman.setSelected(true);
        } else {
            ChkDapatObat.setSelected(false);
            ChkObatSesuai.setSelected(false);
            ChkObatEfektif.setSelected(false);
            ChkObatAman.setSelected(false);
        }
    }//GEN-LAST:event_ChkSemuaActionPerformed

    private void MnAsuhanGiziHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsuhanGiziHasilActionPerformed
        if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data asuhan gizi pasien ini belum ada tersimpan...!!!");
        } else {            
            asuhanGizi("hasil");
        }
    }//GEN-LAST:event_MnAsuhanGiziHasilActionPerformed

    private void BtnHapusDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusDPJPActionPerformed
        kddpjp.setText("-");
        nmdpjp.setText("-");
    }//GEN-LAST:event_BtnHapusDPJPActionPerformed

    private void BtnHapusKonsulenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKonsulenActionPerformed
        nipDPJPlain = "-";
        nmDPJPlainya.setText("-");
    }//GEN-LAST:event_BtnHapusKonsulenActionPerformed

    private void BtnHapusPPAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPPAActionPerformed
        nipppa = "-";
        nmppa.setText("-");
    }//GEN-LAST:event_BtnHapusPPAActionPerformed

    private void BtnHapusSerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSerahActionPerformed
        nipSerah.setText("-");
        nmSerah.setText("-");
    }//GEN-LAST:event_BtnHapusSerahActionPerformed

    private void BtnHapusTerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusTerimaActionPerformed
        nipTerima.setText("-");
        nmTerima.setText("-");
    }//GEN-LAST:event_BtnHapusTerimaActionPerformed

    private void cekWaktuItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cekWaktuItemStateChanged
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuItemStateChanged

    private void cekWaktuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cekWaktuMouseClicked
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuMouseClicked

    private void cekWaktuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekWaktuActionPerformed
        if (cekWaktu.isSelected() == true) {
            cekWaktu.setText("Semua Waktu Diet");
            cekDietPagi.setSelected(true);
            cekDietSiang.setSelected(true);
            cekDietSore.setSelected(true);
        } else if (cekWaktu.isSelected() == false) {
            cekWaktu.setText("Waktu Diet BERBEDA");
            cekDietPagi.setSelected(false);
            cekDietSiang.setSelected(false);
            cekDietSore.setSelected(false);
        }
    }//GEN-LAST:event_cekWaktuActionPerformed

    private void cekKemasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekKemasanActionPerformed
        if (cekKemasan.isSelected() == true) {
            cekKemasan.setText("Dikemas Dengan KOTAKAN");
        } else if (cekKemasan.isSelected() == false) {
            cekKemasan.setText("Disajikan Seperti Biasa");
        }
    }//GEN-LAST:event_cekKemasanActionPerformed

    private void tbDietKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDietKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDiet();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbDietKeyPressed

    private void tbDietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDietMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDiet();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbDietMouseClicked

    private void BtnPilihDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPilihDietActionPerformed
        kddiet.setText("");
        nmdiet.setText("");
        jnsMakanan.setText("");
        kdberi.setText("");
        jumlahBeri.setText("");
        cmbSatuan.setSelectedIndex(0);
        jumlahBeri.setEditable(false);
        cmbSatuan.setEnabled(false);
        BtnSimpan2.setEnabled(true);
        BtnEdit1.setEnabled(false);

        WindowDataDiet.setSize(458, 168);
        WindowDataDiet.setLocationRelativeTo(internalFrame12);
        WindowDataDiet.setVisible(true);
        WindowDataDiet.requestFocus();
    }//GEN-LAST:event_BtnPilihDietActionPerformed

    private void BtnHapusPilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPilihanActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diet yang dipilih/ditentukan belum ada...!!");
            BtnPilihDiet.requestFocus();
        } else if (waktuSimpanDiet.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data diet yang dipilih pada tabel...!!!!");
            tbDiet.requestFocus();
        } else if (tbDiet.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data dietnya pada tabel...!!!!");
            tbDiet.requestFocus();
        } else {
            tabMode2.removeRow(tbDiet.getSelectedRow());
            kddiet.setText("");
            nmdiet.setText("");
            jnsMakanan.setText("");
            kdberi.setText("");
            jumlahBeri.setText("");
            cmbSatuan.setSelectedIndex(0);
            jumlahBeri.setEditable(false);
            cmbSatuan.setEnabled(false);
            BtnHapusPilihan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusPilihanActionPerformed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Diet yang dipilih/ditentukan belum ada...!!");
            BtnPilihDiet.requestFocus();
        } else if (waktuSimpanDiet.equals("")) {
            JOptionPane.showMessageDialog(null, "Belum ada data diet yang dipilih pada tabel...!!!!");
            tbDiet.requestFocus();
        } else if (tbDiet.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data dietnya pada tabel...!!!!");
            tbDiet.requestFocus();
        } else {
            jumlahBeri.setText("");
            cmbSatuan.setSelectedIndex(0);
            BtnSimpan2.setEnabled(false);
            BtnEdit1.setEnabled(true);

            WindowDataDiet.setSize(458, 168);
            WindowDataDiet.setLocationRelativeTo(internalFrame12);
            WindowDataDiet.setVisible(true);
            WindowDataDiet.requestFocus();

            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                kdberi.setText("-");
                jumlahBeri.setEditable(true);
                cmbSatuan.setEnabled(true);
                jumlahBeri.requestFocus();
            } else {
                kdberi.setText("");
                jumlahBeri.setEditable(false);
                cmbSatuan.setEnabled(false);
                btnJumlahBeri.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiActionPerformed

    private void btnDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDietActionPerformed
        akses.setform("DlgCPPT");
        diet.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        diet.setLocationRelativeTo(internalFrame1);
        diet.setVisible(true);
        diet.TCari.requestFocus();
    }//GEN-LAST:event_btnDietActionPerformed

    private void btnJumlahBeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJumlahBeriActionPerformed
        akses.setform("DlgCPPT");
        jlhberi.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        jlhberi.setLocationRelativeTo(internalFrame1);
        jlhberi.tampil();
        jlhberi.setVisible(true);
        jlhberi.TCari.requestFocus();
    }//GEN-LAST:event_btnJumlahBeriActionPerformed

    private void BtnSimpan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan2ActionPerformed
        if (kddiet.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis dietnya...!!!!");
        } else if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    if (Sequel.cariInteger("select count(-1) from diet_master_pemberian where nm_pemberian='" + jumlahBeri.getText() + "' and satuan='" + cmbSatuan.getSelectedItem().toString() + "'") == 0) {
                        kdberi.setText("");
                        Valid.autoNomer("diet_master_pemberian", "DP", 4, kdberi);
                        Sequel.menyimpan("diet_master_pemberian", "'" + kdberi.getText() + "','" + jumlahBeri.getText() + "','" + cmbSatuan.getSelectedItem() + "','1'", "Jumlah Pemberian Diet");
                        tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                            jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                        BtnCloseIn3ActionPerformed(null);
                    } else {
                        tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                            jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                        BtnCloseIn3ActionPerformed(null);
                    }
                }
            } else {
                tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                    jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                BtnCloseIn3ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnSimpan2ActionPerformed

    private void BtnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit1ActionPerformed
        if (kdberi.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu jenis jlh. pemberian dietnya...!!!!");
        } else {
            if (nmdiet.getText().equals("F75") || nmdiet.getText().equals("F100") || nmdiet.getText().equals("F135")) {
                if (jumlahBeri.getText().trim().equals("-") || jumlahBeri.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Jlh. pemberian diet harus diisi dg. benar...!!!!");
                } else if (cmbSatuan.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Pilih salah satu satuan jlh. pemberian dietnya dg. benar...!!!!");
                } else {
                    tabMode2.removeRow(tbDiet.getSelectedRow());
                    tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                        jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                    BtnCloseIn3ActionPerformed(null);
                }
            } else {
                tabMode2.removeRow(tbDiet.getSelectedRow());
                tabMode2.addRow(new Object[]{kddiet.getText(), nmdiet.getText(), jnsMakanan.getText(),
                    jumlahBeri.getText() + " " + cmbSatuan.getSelectedItem(), Sequel.cariIsi("select now()")});
                BtnCloseIn3ActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnEdit1ActionPerformed

    private void BtnEdit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnEditActionPerformed(null);
        }
    }//GEN-LAST:event_BtnEdit1KeyPressed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        kddiet.setText("");
        nmdiet.setText("");
        jnsMakanan.setText("");
        kdberi.setText("");
        jumlahBeri.setText("");
        waktuSimpanDiet = "";
        cmbSatuan.setSelectedIndex(0);
        jumlahBeri.setEditable(false);
        cmbSatuan.setEnabled(false);
        WindowDataDiet.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data diet belum terisi...!!");
        } else {
            kemasan = "";
            if (cekKemasan.isSelected() == true) {
                kemasan = "kotak";
            } else if (cekKemasan.isSelected() == false) {
                kemasan = "biasa";
            }
            
            if (jnsRawat.equals("Ranap")) {
                simpanDietINAP();
            } else if (jnsRawat.equals("Ralan")) {
                simpanDietJALAN();
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, TPasien, BtnBatal);
        }
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        Valid.tabelKosong(tabMode2);
        DTPTgl.setDate(new Date());
        cekWaktu.setSelected(false);
        cekDietPagi.setSelected(false);
        cekDietSiang.setSelected(false);
        cekDietSore.setSelected(false);
        cekKemasan.setSelected(false);
        waktuSimpanDiet = "";
        
        if (cekKemasan.isSelected() == true) {
            cekKemasan.setText("Dikemas Dengan KOTAKAN");
        } else if (cekKemasan.isSelected() == false) {
            cekKemasan.setText("Disajikan Seperti Biasa");
        }
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnBatal1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatal1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        }
    }//GEN-LAST:event_BtnBatal1KeyPressed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowDiet.dispose();
        BtnBatal1ActionPerformed(null);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowDiet.dispose();
        }
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void MnDataPemberianDietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataPemberianDietActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
        } else {
            DlgPemberianDiet rawatinap = new DlgPemberianDiet(null, false);
            rawatinap.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            rawatinap.setLocationRelativeTo(internalFrame1);
            rawatinap.emptTeks();
            rawatinap.cekWaktu.setSelected(true);
            rawatinap.TCari.setText("");
            rawatinap.setNoRm(TNoRw.getText());
            rawatinap.isCek();
            rawatinap.setVisible(true);
        }
    }//GEN-LAST:event_MnDataPemberianDietActionPerformed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt, TCari, DTPTgl);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (TabHistory.getSelectedIndex() == 0) {
            if (tbSampah.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.mengedit("cppt", "waktu_simpan=?", "flag_hapus=?, nip_penghapus=?", 3, new String[]{
                        "tidak", "-", tbSampah.getValueAt(tbSampah.getSelectedRow(), 14).toString()
                    });

                    JOptionPane.showMessageDialog(rootPane, "Data yang dipilih berhasil direstore/kembalikan..!!");
                    cmbSiftCppt.setSelectedItem(tbSampah.getValueAt(tbSampah.getSelectedRow(), 12).toString());
                    TCari.setText(tbSampah.getValueAt(tbSampah.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                    TabCPPT.setSelectedIndex(1);
                }
            } else {
                WindowDataSampah.setSize(1043, internalFrame1.getHeight() - 40);
                WindowDataSampah.setLocationRelativeTo(internalFrame1);
                WindowDataSampah.setAlwaysOnTop(false);
                WindowDataSampah.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        } else {
            if (tbRiwayat.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    kembalikanHistory(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 14).toString());

                    JOptionPane.showMessageDialog(rootPane, "Data yang dipilih berhasil direstore/kembalikan..!!");
                    cmbSiftCppt.setSelectedItem(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 12).toString());
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                    TabCPPT.setSelectedIndex(1);
                }
            } else {
                WindowDataSampah.setSize(1043, internalFrame1.getHeight() - 40);
                WindowDataSampah.setLocationRelativeTo(internalFrame1);
                WindowDataSampah.setAlwaysOnTop(false);
                WindowDataSampah.setVisible(true);
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowDataSampah.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        } 
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        if (TabHistory.getSelectedIndex() == 0) {
            tampilSampah();
            BtnHapusSampah.setEnabled(true);
        } else {
            tampilRiwayat();
            BtnHapusSampah.setEnabled(false);
        }
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        if (TabHistory.getSelectedIndex() == 0) {
            tampilSampah();
        } else {
            tampilRiwayat();
        }
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, kddpjp);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        emptTeks();
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnHapusSampahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusSampahActionPerformed
        if (tbSampah.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                cmbSiftCppt.setSelectedItem(tbSampah.getValueAt(tbSampah.getSelectedRow(), 12).toString());
                TCari.setText(tbSampah.getValueAt(tbSampah.getSelectedRow(), 1).toString());

                if (Sequel.queryu2tf("delete from cppt where waktu_simpan=?", 1, new String[]{
                    tbSampah.getValueAt(tbSampah.getSelectedRow(), 14).toString()
                }) == true) {
                    JOptionPane.showMessageDialog(rootPane, "Data yang dipilih berhasil terhapus..!!");
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                    TabCPPT.setSelectedIndex(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            WindowDataSampah.setSize(1043, internalFrame1.getHeight() - 40);
            WindowDataSampah.setLocationRelativeTo(internalFrame1);
            WindowDataSampah.setAlwaysOnTop(false);
            WindowDataSampah.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusSampahActionPerformed

    private void MnDataSampahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDataSampahActionPerformed
        DTPCari3.setDate(new Date());
        DTPCari4.setDate(new Date());
        TCari2.setText("");
        TabHistory.setSelectedIndex(0);
        BtnCari2ActionPerformed(null);
        WindowDataSampah.setSize(1043, internalFrame1.getHeight() - 40);
        WindowDataSampah.setLocationRelativeTo(internalFrame1);
        WindowDataSampah.setAlwaysOnTop(false);
        WindowDataSampah.setVisible(true);
    }//GEN-LAST:event_MnDataSampahActionPerformed

    private void TSubjektifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSubjektifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TObjektif.requestFocus();
        }
    }//GEN-LAST:event_TSubjektifKeyPressed

    private void TObjektifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TObjektifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAsesmen.requestFocus();
        }
    }//GEN-LAST:event_TObjektifKeyPressed

    private void TAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAsesmenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPlaning.requestFocus();
        }
    }//GEN-LAST:event_TAsesmenKeyPressed

    private void TPlaningKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPlaningKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDPJP.requestFocus();
        }
    }//GEN-LAST:event_TPlaningKeyPressed

    private void cmbSoapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSoapActionPerformed
        if (cmbSoap.getSelectedIndex() == 0) {
            TabPilihan.setSelectedIndex(0);
            TabPilihan.setEnabledAt(0, true);
            TabPilihan.setEnabledAt(1, false);
        } else {
            TabPilihan.setSelectedIndex(1);
            TabPilihan.setEnabledAt(0, false);
            TabPilihan.setEnabledAt(1, true);
        }
    }//GEN-LAST:event_cmbSoapActionPerformed

    private void MnTemplateSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTemplateSActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Subjektif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_MnTemplateSActionPerformed

    private void MnUlangiSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUlangiSActionPerformed
        if (TSubjektif.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data subjektif masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data subjektif akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TSubjektif.setText("");
                TSubjektif.requestFocus();
            }
        }
    }//GEN-LAST:event_MnUlangiSActionPerformed

    private void MnTemplateOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTemplateOActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Objektif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_MnTemplateOActionPerformed

    private void MnUlangiOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUlangiOActionPerformed
        if (TObjektif.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data objektif masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data objektif akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TObjektif.setText("");
                TObjektif.requestFocus();
            }
        }
    }//GEN-LAST:event_MnUlangiOActionPerformed

    private void MnTemplateAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTemplateAActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_MnTemplateAActionPerformed

    private void MnUlangiAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUlangiAActionPerformed
        if (TAsesmen.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data asesmen masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data asesmen akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TAsesmen.setText("");
                TAsesmen.requestFocus();
            }
        }
    }//GEN-LAST:event_MnUlangiAActionPerformed

    private void MnTemplatePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnTemplatePActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Instruksi/Planning ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.setText(TNoRm.getText());
        TCari1.requestFocus();
        tampilTemplate();
    }//GEN-LAST:event_MnTemplatePActionPerformed

    private void MnUlangiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUlangiPActionPerformed
        if (TPlaning.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Data instruksi/planning masih kosong/belum terisi...!!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data instruksi/planning akan hapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                TPlaning.setText("");
                TPlaning.requestFocus();
            }
        }
    }//GEN-LAST:event_MnUlangiPActionPerformed

    private void BtnVerifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVerifActionPerformed
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            if (akses.getadmin() == true) {
                if (TNoRw.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                } else {
                    DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                    verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    verif.setLocationRelativeTo(internalFrame1);
                    verif.setData(TNoRw.getText(), status);
                    verif.setVisible(true);
                }
            } else if (akses.getkode().equals(Sequel.cariIsi("select kd_dokter from reg_periksa where no_rawat='" + TNoRw.getText() + "'"))) {
                if (TNoRw.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                } else {
                    DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                    verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    verif.setLocationRelativeTo(internalFrame1);
                    verif.setData(TNoRw.getText(), status);
                    verif.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Verifikasi CPPT hanya dilakukan oleh DPJP pasien tersebut...!!!");
            }
        } else if (status.equals("ranap")) {
            if (akses.getadmin() == true) {
                if (TNoRw.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                } else {
                    DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                    verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    verif.setLocationRelativeTo(internalFrame1);
                    verif.setData(TNoRw.getText(), status);
                    verif.setVisible(true);
                }
            } else if (akses.getkode().equals(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'"))) {
                if (TNoRw.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
                } else {
                    DlgVerifikasiCPPT verif = new DlgVerifikasiCPPT(null, false);
                    verif.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    verif.setLocationRelativeTo(internalFrame1);
                    verif.setData(TNoRw.getText(), status);
                    verif.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Verifikasi CPPT hanya dilakukan oleh DPJP pasien tersebut...!!!");
            }
        }
    }//GEN-LAST:event_BtnVerifActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                DlgCatatanResep form = new DlgCatatanResep(null, false);
                form.isCek();
                form.setData(TNoRw.getText(), status);
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else if (status.equals("ranap")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                DlgCatatanResep form = new DlgCatatanResep(null, false);
                form.isCek();
                form.setData(TNoRw.getText(), "ranap");
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            }
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnPasteHasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteHasilActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (THasil.getText().equals("")) {
                THasil.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                THasil.setText(THasil.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteHasilActionPerformed

    private void MnPasteLabSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasteLabSActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TSubjektif.getText().equals("")) {
                TSubjektif.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TSubjektif.setText(TSubjektif.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_MnPasteLabSActionPerformed

    private void MnPasteLabOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasteLabOActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TObjektif.getText().equals("")) {
                TObjektif.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TObjektif.setText(TObjektif.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_MnPasteLabOActionPerformed

    private void MnPasteLabAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasteLabAActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TAsesmen.getText().equals("")) {
                TAsesmen.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TAsesmen.setText(TAsesmen.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_MnPasteLabAActionPerformed

    private void MnInputDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataTriaseIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                RMTriaseIGD form = new RMTriaseIGD(null, false);
                form.isCek();
                form.emptTeks();
                form.setNoRm(TNoRw.getText());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    if ((Sequel.cariInteger("select count(-1) from triase_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgCPPT");
                        RMTriaseIGD form = new RMTriaseIGD(null, false);
                        form.isCek();
                        form.emptTeks();
                        form.setNoRm(TNoRw.getText());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                    }
                } else if (status.equals("ranap")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgCPPT");
                    RMTriaseIGD form = new RMTriaseIGD(null, false);
                    form.isCek();
                    form.emptTeks();
                    form.setNoRm(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnInputDataTriaseIGDActionPerformed

    private void MnLihatDataTriaseIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataTriaseIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from triase_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakDataTriase();
            } else {
                JOptionPane.showMessageDialog(null, "Data triase IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnLihatDataTriaseIGDActionPerformed

    private void MnInputDataAssesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAssesmenMedikIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                RMPenilaianAwalMedikIGD form = new RMPenilaianAwalMedikIGD(null, false);
                form.isCek();
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "IGD (Ralan)");
                } else if (status.equals("ranap")) {
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ranap");
                }
                form.emptTeks();
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    if ((Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgCPPT");
                        RMPenilaianAwalMedikIGD form = new RMPenilaianAwalMedikIGD(null, false);
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "IGD (Ralan)");
                        form.emptTeks();
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                    }
                } else if (status.equals("ranap")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgCPPT");
                    RMPenilaianAwalMedikIGD form = new RMPenilaianAwalMedikIGD(null, false);
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ranap");
                    form.emptTeks();
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnInputDataAssesmenMedikIGDActionPerformed

    private void MnLihatDataAssesmenMedikIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAssesmenMedikIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesMedikIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnLihatDataAssesmenMedikIGDActionPerformed

    private void MnInputDataAssesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAssesmenKeperawatanIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                RMPenilaianAwalKeperawatanIGDrz form = new RMPenilaianAwalKeperawatanIGDrz(null, false);
                form.emptTeks();
                form.isCek();
                form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    if ((Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)
                            || (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat = '" + TNoRw.getText() + "' and now() <= DATE_ADD(tanggal,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgCPPT");
                        RMPenilaianAwalKeperawatanIGDrz form = new RMPenilaianAwalKeperawatanIGDrz(null, false);
                        form.emptTeks();
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                    }
                } else if (status.equals("ranap")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgCPPT");
                    RMPenilaianAwalKeperawatanIGDrz form = new RMPenilaianAwalKeperawatanIGDrz(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnInputDataAssesmenKeperawatanIGDActionPerformed

    private void MnLihatDataAssesmenKeperawatanIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAssesmenKeperawatanIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesKepIGD();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen keperawatan IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnLihatDataAssesmenKeperawatanIGDActionPerformed

    private void MnInputDataTransferSerahTerimaIGDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataTransferSerahTerimaIGDActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
                RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                form.emptTeks();
                form.isCek();
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "IGD (Ralan)", "IGDK", "IGD");
                } else if (status.equals("ralan")) {
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ralan",
                            Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                            Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + TNoRw.getText() + "'"));
                } else if (status.equals("ranap")) {
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ranap",
                            Sequel.cariIsi("select kd_kamar from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc, jam_masuk desc limit 1"),
                            Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                }
                
                form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.setLocationRelativeTo(internalFrame1);
                form.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    if ((Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' "
                                    + "and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgCPPT");
                        RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                        form.emptTeks();
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "IGD (Ralan)", "IGDK", "IGD");
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                    }
                } else if (status.equals("ralan")) {
                    if ((Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "'") == 0)
                            || (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat = '" + TNoRw.getText() + "' "
                                    + "and now() <= DATE_ADD(tgl_jam_pindah,Interval 24 DAY_HOUR)") == 1)) {
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        akses.setform("DlgCPPT");
                        RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                        form.emptTeks();
                        form.isCek();
                        form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ralan",
                                Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'"),
                                Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + TNoRw.getText() + "'"));
                        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                        form.setLocationRelativeTo(internalFrame1);
                        form.setVisible(true);
                        this.setCursor(Cursor.getDefaultCursor());
                    } else {
                        JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                    }
                } else if (status.equals("ranap")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    akses.setform("DlgCPPT");
                    RMTransferSerahTerimaIGD form = new RMTransferSerahTerimaIGD(null, false);;
                    form.emptTeks();
                    form.isCek();
                    form.setNoRm(TNoRw.getText(), DTPCari2.getDate(), "ranap",
                            Sequel.cariIsi("select kd_kamar from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc, jam_masuk desc limit 1"),
                            Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnInputDataTransferSerahTerimaIGDActionPerformed

    private void MnPemberianObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemberianObatActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
                akses.setform("DlgCPPT");
                beriObat.emptTeks();
                beriObat.isCek();
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "IGD (Ralan)", "IGD");
                } else if (status.equals("ralan")) {
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "ralan",
                            Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + TNoRw.getText() + "'"));
                } else if (status.equals("ranap")) {
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "ranap", Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                }
                beriObat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                beriObat.setLocationRelativeTo(internalFrame1);
                beriObat.setAlwaysOnTop(false);
                beriObat.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
                    akses.setform("DlgCPPT");
                    beriObat.emptTeks();
                    beriObat.isCek();
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "IGD (Ralan)", "IGD");
                    beriObat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    beriObat.setLocationRelativeTo(internalFrame1);
                    beriObat.setAlwaysOnTop(false);
                    beriObat.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else if (status.equals("ralan")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
                    akses.setform("DlgCPPT");
                    beriObat.emptTeks();
                    beriObat.isCek();
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "ralan",
                            Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + TNoRw.getText() + "'"));
                    beriObat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    beriObat.setLocationRelativeTo(internalFrame1);
                    beriObat.setAlwaysOnTop(false);
                    beriObat.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else if (status.equals("ranap")) {
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgPemberianObatPasien beriObat = new DlgPemberianObatPasien(null, false);
                    akses.setform("DlgCPPT");
                    beriObat.emptTeks();
                    beriObat.isCek();
                    beriObat.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), "ranap",
                            Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
                    beriObat.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    beriObat.setLocationRelativeTo(internalFrame1);
                    beriObat.setAlwaysOnTop(false);
                    beriObat.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }//GEN-LAST:event_MnPemberianObatActionPerformed

    private void MnInputDataAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataAsesmenMedikObstetriActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("IGDK")
                    || Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("OBG")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
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
    }//GEN-LAST:event_MnInputDataAsesmenMedikObstetriActionPerformed

    private void MnLihatDataAsesmenMedikObstetriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataAsesmenMedikObstetriActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_obstetri_ralan where no_rawat='" + TNoRw.getText() + "'") > 0) {
                cetakAsesMedikObs();
            } else {
                JOptionPane.showMessageDialog(null, "Data asesmen medik obstetri pasien IGD tidak ditemukan...!!!");
            }
        }
    }//GEN-LAST:event_MnLihatDataAsesmenMedikObstetriActionPerformed

    private void MnInputDataKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnInputDataKebidananActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu dengan mengklik data pada tabel...!!!");
        } else if (!Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("IGDK")
                && !Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'").equals("OBG")) {
            JOptionPane.showMessageDialog(null, "Fitur ini hanya untuk pasien yg. terdaftar di IGD Ponek/Poli Kandungan saja...!!!");
        } else if (Sequel.cariIsi("select jk from pasien where no_rkm_medis='" + TNoRm.getText() + "'").equals("L")) {
            JOptionPane.showMessageDialog(null, "Hanya untuk pasien yang berjenis kelamin perempuan saja...!!!!");
        } else {
            if (akses.getadmin() == true) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                akses.setform("DlgCPPT");
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
                    akses.setform("DlgCPPT");
                    RMAsesmenKebidananRalan form = new RMAsesmenKebidananRalan(null, false);
                    form.emptTeks();
                    form.isCek();
                    form.setData(TNoRw.getText());
                    form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                    form.setLocationRelativeTo(internalFrame1);
                    form.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                } else {
                    JOptionPane.showMessageDialog(null, "Sudah Lewat Dari 24 Jam, Data Tidak Bisa Diganti !!!");
                }
            }
        }
    }//GEN-LAST:event_MnInputDataKebidananActionPerformed

    private void MnLihatDataKebidananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLihatDataKebidananActionPerformed
        JOptionPane.showMessageDialog(null, "Segera tayang (Comming Soon)...!!!");
    }//GEN-LAST:event_MnLihatDataKebidananActionPerformed

    private void BtnPasteInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteInstruksiActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TInstruksi.getText().equals("")) {
                TInstruksi.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TInstruksi.setText(TInstruksi.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteInstruksiActionPerformed

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

    private void BtnPetugasKonfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasKonfirActionPerformed
        pilihan = 4;
        akses.setform("DlgCPPT");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasKonfirActionPerformed

    private void BtnHapusPetugasKonfirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusPetugasKonfirActionPerformed
        nipPetugasKonfir.setText("-");
        nmPetugasKonfir.setText("-");
    }//GEN-LAST:event_BtnHapusPetugasKonfirActionPerformed

    private void BtnKonfirDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKonfirDpjpActionPerformed
        pilihan = 3;
        akses.setform("DlgCPPT");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnKonfirDpjpActionPerformed

    private void BtnHapusKonfirDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusKonfirDpjpActionPerformed
        nipKonfirDpjp.setText("-");
        nmKonfirDpjp.setText("-");
    }//GEN-LAST:event_BtnHapusKonfirDpjpActionPerformed

    private void cmbSiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSiftActionPerformed
        if (cmbSift.getSelectedIndex() == 1) {
            if (cmbSertim.getSelectedIndex() == 1) {
                cmbJam1.setSelectedItem("14");
                cmbMnt1.setSelectedItem("15");
            } else {
                cmbJam1.setSelectedIndex(0);
                cmbMnt1.setSelectedIndex(0);
                cmbDtk1.setSelectedIndex(0);
            }
        } else if (cmbSift.getSelectedIndex() == 2) {
            if (cmbSertim.getSelectedIndex() == 1) {
                cmbJam1.setSelectedItem("20");
                cmbMnt1.setSelectedItem("15");
            } else {
                cmbJam1.setSelectedIndex(0);
                cmbMnt1.setSelectedIndex(0);
                cmbDtk1.setSelectedIndex(0);
            }
        } else if (cmbSift.getSelectedIndex() == 3) {
            if (cmbSertim.getSelectedIndex() == 1) {
                cmbJam1.setSelectedItem("08");
                cmbMnt1.setSelectedItem("15");
            } else {
                cmbJam1.setSelectedIndex(0);
                cmbMnt1.setSelectedIndex(0);
                cmbDtk1.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_cmbSiftActionPerformed

    private void MnCeklisFarmasiSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiSActionPerformed
        paste = 1;
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);
        
        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiSActionPerformed

    private void MnCeklisFarmasiOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiOActionPerformed
        paste = 2;
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);

        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiOActionPerformed

    private void MnCeklisFarmasiAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiAActionPerformed
        paste = 3;
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);

        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiAActionPerformed

    private void MnCeklisFarmasiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCeklisFarmasiPActionPerformed
        paste = 4;
        ChkDapatObat.setSelected(false);
        ChkObatSesuai.setSelected(false);
        ChkObatEfektif.setSelected(false);
        ChkObatAman.setSelected(false);
        ChkSemua.setSelected(false);

        WindowFarmasi.setSize(300, 172);
        WindowFarmasi.setLocationRelativeTo(internalFrame1);
        WindowFarmasi.setAlwaysOnTop(false);
        WindowFarmasi.setVisible(true);
    }//GEN-LAST:event_MnCeklisFarmasiPActionPerformed

    private void tbKonfirmasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKonfirmasiMouseClicked
        if (tabMode4.getRowCount() != 0) {
            try {
                getDataKonfirmasi();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbKonfirmasiMouseClicked

    private void tbKonfirmasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKonfirmasiKeyPressed
        if (tabMode4.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                getDataKonfirmasi();
            }
        }
    }//GEN-LAST:event_tbKonfirmasiKeyPressed

    private void tbKonfirmasiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKonfirmasiKeyReleased
        if (tabMode4.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataKonfirmasi();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbKonfirmasiKeyReleased

    private void TabPetugasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPetugasMouseClicked
        if (TabPetugas.getSelectedIndex() == 0) {
            nipPetugasKonfir.setText("-");
            nmPetugasKonfir.setText("-");
            nipKonfirDpjp.setText("-");
            nmKonfirDpjp.setText("-");
        } else if (TabPetugas.getSelectedIndex() == 1) {
            tampilKonfirmasi();
            nipPetugasKonfir.setText(nipppa);
            nmPetugasKonfir.setText(nmppa.getText());
            nipKonfirDpjp.setText(kddpjp.getText());
            nmKonfirDpjp.setText(nmdpjp.getText());
        }
    }//GEN-LAST:event_TabPetugasMouseClicked

    private void BtnSimpan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan3ActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            Sequel.menyimpan("cppt_konfirmasi_terapi", "'" + TNoRw.getText() + "',"
                    + "'" + Valid.SetTgl(tglCppt.getSelectedItem() + "") + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + cmbSift.getSelectedItem().toString() + "','" + Sequel.cariIsi("select now()") + "',"
                    + "'" + Valid.SetTgl(tglLapor.getSelectedItem() + "") + "',"
                    + "'" + cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem() + "',"
                    + "'" + Valid.SetTgl(tglVerifikasi.getSelectedItem() + "") + "',"
                    + "'" + cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem() + "',"
                    + "'" + nipPetugasKonfir.getText() + "','" + nipKonfirDpjp.getText() + "'", "Konfirmasi CPPT Pasien");
            tampilKonfirmasi();
            BtnBatal2ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan3ActionPerformed

    private void BtnBatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal2ActionPerformed
        tglLapor.setDate(new Date());
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        tglVerifikasi.setDate(new Date());
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        nipPetugasKonfir.setText("-");
        nmPetugasKonfir.setText("-");
        nipKonfirDpjp.setText("-");
        nmKonfirDpjp.setText("-");
        wktSimpanKonfirmasi = "";
    }//GEN-LAST:event_BtnBatal2ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tbKonfirmasi.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data konfirmasi terapi via telpon tidak ditemukan..!!");
        } else if (wktSimpanKonfirmasi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data konfirmasi terapi via telpon pada tabel..!!");
            tbKonfirmasi.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from cppt_konfirmasi_terapi where waktu_simpan=?", 1, new String[]{
                    wktSimpanKonfirmasi
                }) == true) {
                    tampilKonfirmasi();
                    BtnBatal2ActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnEdit2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit2ActionPerformed
        if (wktSimpanKonfirmasi.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbKonfirmasi.requestFocus();
        } else {
            Sequel.mengedit("cppt_konfirmasi_terapi", "waktu_simpan=?", "tgl_cppt=?, jam_cppt=?, cppt_shift=?, "
                    + "tgl_lapor=?, jam_lapor=?, tgl_verifikasi=?, jam_verifikasi=?, nip_petugas_konfir=?, nip_dpjp_konfir=?", 10, new String[]{
                        Valid.SetTgl(tglCppt.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cmbSift.getSelectedItem().toString(), Valid.SetTgl(tglLapor.getSelectedItem() + ""),
                        cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                        Valid.SetTgl(tglVerifikasi.getSelectedItem() + ""), cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                        nipPetugasKonfir.getText(), nipKonfirDpjp.getText(),
                        wktSimpanKonfirmasi
                    });

            BtnBatal2ActionPerformed(null);
            tampilKonfirmasi();
        }
    }//GEN-LAST:event_BtnEdit2ActionPerformed

    private void BtnLihatKonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLihatKonActionPerformed
        tampilKonfirmasi();
    }//GEN-LAST:event_BtnLihatKonActionPerformed

    private void TabHistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabHistoryMouseClicked
        BtnCari2ActionPerformed(null);
    }//GEN-LAST:event_TabHistoryMouseClicked

    private void MnUrutkanDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnUrutkanDataActionPerformed
        if (tbCPPT.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data cppt untuk pasien tersebut belum ada..!!");
        } else {
            tampilUrutan();
        }
    }//GEN-LAST:event_MnUrutkanDataActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("DlgCPPT");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("DlgCPPT");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRm.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCPPT");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void MnPemantauanDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPemantauanDewasaActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
        } else if (jnsRawat.equals("Ralan")) {
            JOptionPane.showMessageDialog(null, "Fitur/Menu ini hanya untuk pasien rawat inap saja,..!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCPPT");
            RMPemantauanHarian24Jam form = new RMPemantauanHarian24Jam(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRw.getText(), Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                    + "WHERE k.kd_kamar='" + kdUnit + "'"));
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnPemantauanDewasaActionPerformed

    private void MnGrafikPemantauanDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnGrafikPemantauanDewasaActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Pasien belum dipilih..!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("DlgCPPT");
            RMGrafikPemantauanHarian24Jam form = new RMGrafikPemantauanHarian24Jam(null, false);
            form.setData(TNoRw.getText());
            form.setSize(747, 71);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnGrafikPemantauanDewasaActionPerformed

    private void MnPasteLabPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnPasteLabPActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (TPlaning.getText().equals("")) {
                TPlaning.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                TPlaning.setText(TPlaning.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_MnPasteLabPActionPerformed

    private void chkDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDpjpActionPerformed
        if (chkDpjp.isSelected() == true) {
            if (akses.getadmin() == true) {
                kddpjp.setText("-");
                nmdpjp.setText("-");
            } else {
                if (Sequel.cariInteger("select count(-1) from dokter where kd_dokter='" + akses.getkode() + "'") == 0) {
                    JOptionPane.showMessageDialog(null, "Maaf, yang login harus dokter..!!!!");
                    chkDpjp.setSelected(false);
                    kddpjp.setText("-");
                    nmdpjp.setText("-");
                } else {
                    kddpjp.setText(akses.getkode());
                    nmdpjp.setText(Sequel.cariIsi("select nama from pegawai where nik='" + kddpjp.getText() + "'"));
                }
            }
        } else {            
            kddpjp.setText("-");
            nmdpjp.setText("-");
        }
    }//GEN-LAST:event_chkDpjpActionPerformed

    private void MnAsuhanGiziInstruksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnAsuhanGiziInstruksiActionPerformed
        if (Sequel.cariInteger("select count(-1) from asuhan_gizi_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data asuhan gizi pasien ini belum ada tersimpan...!!!");
        } else {
            asuhanGizi("instruksi");
        }
    }//GEN-LAST:event_MnAsuhanGiziInstruksiActionPerformed

    private void MnMonevGiziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonevGiziActionPerformed
        if (Sequel.cariInteger("select count(-1) from monev_asuhan_gizi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data monitoring & evaluasi gizi pasien ini belum ada tersimpan...!!!");
        } else {
            monevGizi = "";
            cmbTujuanPaste.setSelectedIndex(0);
            Valid.SetTgl(tgl1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            tgl2.setDate(new Date());
            tampilMonevGizi();
            
            WindowDataMonevGizi.setSize(961, 444);
            WindowDataMonevGizi.setLocationRelativeTo(internalFrame1);
            WindowDataMonevGizi.setAlwaysOnTop(false);
            WindowDataMonevGizi.setVisible(true);
            tbMonev.requestFocus();
        }
    }//GEN-LAST:event_MnMonevGiziActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowDataMonevGizi.dispose();
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnCopas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCopas1ActionPerformed
        if (tbMonev.getSelectedRow() > -1) {
            monevGizi = "Tgl. Monev : " + tbMonev.getValueAt(tbMonev.getSelectedRow(), 2).toString() + "\n"
                    + "Perkembangan Fisik/Klinis : " + tbMonev.getValueAt(tbMonev.getSelectedRow(), 4).toString() + "\n\n"
                    + "Perkembangan Diet : " + tbMonev.getValueAt(tbMonev.getSelectedRow(), 5).toString() + "\n\n"
                    + "Evaluasi dan Tindak Lanjut : " + tbMonev.getValueAt(tbMonev.getSelectedRow(), 6).toString() + "\n";

            if (cmbTujuanPaste.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu tujuan untuk paste datanya..!!");
                cmbTujuanPaste.requestFocus();
            } else if (cmbTujuanPaste.getSelectedIndex() == 1) {
                if (THasil.getText().equals("")) {
                    THasil.setText(monevGizi);
                } else {
                    THasil.setText(THasil.getText() + "\n\n" + monevGizi);
                }
                WindowDataMonevGizi.dispose();
            } else if (cmbTujuanPaste.getSelectedIndex() == 2) {
                if (TInstruksi.getText().equals("")) {
                    TInstruksi.setText(monevGizi);
                } else {
                    TInstruksi.setText(TInstruksi.getText() + "\n\n" + monevGizi);
                }
                WindowDataMonevGizi.dispose();
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_BtnCopas1ActionPerformed

    private void BtnCari3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari3ActionPerformed
        tampilMonevGizi();
    }//GEN-LAST:event_BtnCari3ActionPerformed

    private void BtnCari3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari3KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari3KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgCPPT dialog = new DlgCPPT(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCari3;
    private widget.Button BtnCetakCPPT;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnCloseIn3;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn6;
    private widget.Button BtnCloseIn7;
    private widget.Button BtnCopas;
    private widget.Button BtnCopas1;
    private widget.Button BtnDPJP;
    private widget.Button BtnDPJPLain;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit1;
    private widget.Button BtnEdit2;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusDPJP;
    private widget.Button BtnHapusKonfirDpjp;
    private widget.Button BtnHapusKonsulen;
    private widget.Button BtnHapusPPA;
    private widget.Button BtnHapusPetugasKonfir;
    private widget.Button BtnHapusPilihan;
    private widget.Button BtnHapusSampah;
    private widget.Button BtnHapusSerah;
    private widget.Button BtnHapusTerima;
    private widget.Button BtnHasil;
    private widget.Button BtnInstruksi;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKonfirDpjp;
    private widget.Button BtnLihatKon;
    private widget.Button BtnNotepad;
    private widget.Button BtnPPA;
    private widget.Button BtnPasteHasil;
    private widget.Button BtnPasteInstruksi;
    private widget.Button BtnPetugasKonfir;
    private widget.Button BtnPilihDiet;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnRestor;
    private widget.Button BtnSerah;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan2;
    private widget.Button BtnSimpan3;
    private widget.Button BtnSimpanCeklis;
    private widget.Button BtnTerima;
    private widget.Button BtnUlangiHasil;
    private widget.Button BtnUlangiInstruksi;
    private widget.Button BtnVerif;
    public widget.CekBox ChkDapatObat;
    public widget.CekBox ChkJam;
    public widget.CekBox ChkObatAman;
    public widget.CekBox ChkObatEfektif;
    public widget.CekBox ChkObatSesuai;
    public widget.CekBox ChkSamaPPA;
    public widget.CekBox ChkSemua;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenu MnAsesmenKebidanan;
    private javax.swing.JMenu MnAsesmenMedikObstetriIGD;
    private javax.swing.JMenu MnAssesmenKeperawatanIGD;
    private javax.swing.JMenu MnAssesmenMedikIGD;
    private javax.swing.JMenuItem MnAsuhanGiziHasil;
    private javax.swing.JMenuItem MnAsuhanGiziInstruksi;
    private javax.swing.JMenuItem MnCeklisFarmasi;
    private javax.swing.JMenuItem MnCeklisFarmasiA;
    private javax.swing.JMenuItem MnCeklisFarmasiO;
    private javax.swing.JMenuItem MnCeklisFarmasiP;
    private javax.swing.JMenuItem MnCeklisFarmasiS;
    private javax.swing.JMenuItem MnDataPemberianDiet;
    private javax.swing.JMenuItem MnDataSampah;
    private javax.swing.JMenu MnDataTriaseIGD;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnGrafikPemantauanDewasa;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JMenuItem MnInputDataAsesmenMedikObstetri;
    private javax.swing.JMenuItem MnInputDataAssesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnInputDataAssesmenMedikIGD;
    private javax.swing.JMenuItem MnInputDataKebidanan;
    private javax.swing.JMenuItem MnInputDataTransferSerahTerimaIGD;
    private javax.swing.JMenuItem MnInputDataTriaseIGD;
    private javax.swing.JMenuItem MnLihatDataAsesmenMedikObstetri;
    private javax.swing.JMenuItem MnLihatDataAssesmenKeperawatanIGD;
    private javax.swing.JMenuItem MnLihatDataAssesmenMedikIGD;
    private javax.swing.JMenuItem MnLihatDataKebidanan;
    private javax.swing.JMenuItem MnLihatDataTriaseIGD;
    private javax.swing.JMenuItem MnMonevGizi;
    private javax.swing.JMenuItem MnPasteLabA;
    private javax.swing.JMenuItem MnPasteLabO;
    private javax.swing.JMenuItem MnPasteLabP;
    private javax.swing.JMenuItem MnPasteLabS;
    private javax.swing.JMenuItem MnPemantauanDewasa;
    private javax.swing.JMenuItem MnPemberianDiet;
    private javax.swing.JMenuItem MnPemberianObat;
    private javax.swing.JMenu MnRMGawatDarurat;
    private javax.swing.JMenuItem MnTemplateA;
    private javax.swing.JMenuItem MnTemplateO;
    private javax.swing.JMenuItem MnTemplateP;
    private javax.swing.JMenuItem MnTemplateS;
    private javax.swing.JMenu MnTransferSerahTerimaIGD;
    private javax.swing.JMenuItem MnUlangiA;
    private javax.swing.JMenuItem MnUlangiO;
    private javax.swing.JMenuItem MnUlangiP;
    private javax.swing.JMenuItem MnUlangiS;
    private javax.swing.JMenuItem MnUrutkanData;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextArea TAsesmen;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextArea THasil;
    private widget.TextArea TInstruksi;
    private widget.TextBox TKd;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRm1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextArea TObjektif;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextArea TPlaning;
    private widget.TextArea TSubjektif;
    private javax.swing.JTabbedPane TabCPPT;
    private javax.swing.JTabbedPane TabHistory;
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private javax.swing.JTabbedPane TabPetugas;
    private javax.swing.JTabbedPane TabPilihan;
    private javax.swing.JTabbedPane TabTindakanPencegahan;
    private widget.TextArea Ttemplate;
    private javax.swing.JDialog WindowCPPT;
    private javax.swing.JDialog WindowDataDiet;
    private javax.swing.JDialog WindowDataMonevGizi;
    private javax.swing.JDialog WindowDataSampah;
    private javax.swing.JDialog WindowDiet;
    private javax.swing.JDialog WindowFarmasi;
    private javax.swing.JDialog WindowTemplate;
    private widget.TextArea anakA;
    private widget.TextArea anakB;
    private widget.Button btnDiet;
    private widget.Button btnJumlahBeri;
    private widget.CekBox cekDietPagi;
    private widget.CekBox cekDietSiang;
    private widget.CekBox cekDietSore;
    private widget.CekBox cekKemasan;
    public widget.CekBox cekWaktu;
    private widget.CekBox chkDpjp;
    private widget.ComboBox cmbBagian;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJnsCppt;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbPPA;
    private widget.ComboBox cmbRawat;
    private widget.ComboBox cmbSatuan;
    private widget.ComboBox cmbSertim;
    private widget.ComboBox cmbSift;
    private widget.ComboBox cmbSiftCppt;
    private widget.ComboBox cmbSiftPetugas;
    private widget.ComboBox cmbSoap;
    private widget.ComboBox cmbTanggal;
    private widget.ComboBox cmbTujuanPaste;
    private widget.TextArea dewasaA;
    private widget.TextArea dewasaB;
    private widget.TextArea dewasaC;
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
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame20;
    private widget.InternalFrame internalFrame21;
    private widget.InternalFrame internalFrame22;
    private widget.InternalFrame internalFrame23;
    private widget.InternalFrame internalFrame24;
    private widget.InternalFrame internalFrame25;
    private widget.InternalFrame internalFrame26;
    private widget.InternalFrame internalFrame27;
    private widget.InternalFrame internalFrame28;
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
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JPopupMenu jPopupMenu5;
    private javax.swing.JPopupMenu jPopupMenu6;
    private widget.TextBox jnsMakanan;
    private widget.TextBox jumlahBeri;
    private widget.TextBox kdberi;
    private widget.TextBox kddiet;
    private widget.TextBox kddpjp;
    private widget.TextBox nipKonfirDpjp;
    private widget.TextBox nipPetugasKonfir;
    private widget.TextBox nipSerah;
    private widget.TextBox nipTerima;
    private widget.TextBox nmDPJPlainya;
    private widget.TextBox nmKonfirDpjp;
    private widget.TextBox nmPetugasKonfir;
    private widget.TextBox nmSerah;
    private widget.TextBox nmTerima;
    private widget.TextBox nmdiet;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmppa;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa14;
    private widget.PanelBiasa panelBiasa15;
    private widget.PanelBiasa panelBiasa6;
    private widget.PanelBiasa panelBiasa7;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbCPPT;
    private widget.Table tbDiet;
    private widget.Table tbFaktorResiko;
    private widget.Table tbKonfirmasi;
    private widget.Table tbMonev;
    private widget.Table tbRiwayat;
    private widget.Table tbSampah;
    private widget.Table tbTemplate;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    private widget.Tanggal tglA;
    private widget.Tanggal tglB;
    private widget.Tanggal tglCppt;
    private widget.Tanggal tglLapor;
    private widget.Tanggal tglVerifikasi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbRawat.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and pg.nama like ? order by c.tgl_cppt, time(c.waktu_simpan) desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() != 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and pg.nama like ? "
                        + "order by c.tgl_cppt, time(c.waktu_simpan) desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() == 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and pg.nama like ? order by c.tgl_cppt, time(c.waktu_simpan) desc");
            } else if (cmbRawat.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and pg.nama like ? order by c.tgl_cppt, time(c.waktu_simpan) desc");
            }
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");                
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");    
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("tgllhr"),
                        rs.getString("tglcppt"),
                        rs.getString("jam_cppt_data"),
                        rs.getString("jenis_ppa"),
                        rs.getString("nmppa"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("instruksi_nakes"),
                        rs.getString("verifikasi"),
                        rs.getString("nmdpjp"),
                        rs.getString("status"),
                        rs.getString("tgl_cppt"),
                        rs.getString("nip_dpjp"),
                        rs.getString("waktu_simpan"),
                        rs.getString("cek_jam"),
                        rs.getString("jam_cppt"),                        
                        rs.getString("jenis_bagian"),
                        rs.getString("nip_ppa"),                        
                        rs.getString("serah_terima_cppt"),
                        rs.getString("nmkonsulen"),
                        rs.getString("nip_konsulen"),
                        rs.getString("petugasSerah"),
                        rs.getString("petugasTerima"),
                        rs.getString("nip_petugas_serah"),
                        rs.getString("nip_petugas_terima"),
                        rs.getString("cppt_shift"),
                        rs.getString("jam_serah_terima"),
                        rs.getString("pilihan_soap"),
                        rs.getString("subjektif"),
                        rs.getString("objektif"),
                        rs.getString("asesmen"),
                        rs.getString("planing")
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
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    private void tampilUrutan() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbRawat.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ralan' and flag_hapus='tidak' and pg.nama like ? order by c.waktu_simpan desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() != 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.cppt_shift like '%" + cmbSiftCppt.getSelectedItem() + "%' and pg.nama like ? "
                        + "order by c.waktu_simpan desc");
            } else if (cmbRawat.getSelectedIndex() == 1 && cmbSiftCppt.getSelectedIndex() == 4) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and c.status='Ranap' and flag_hapus='tidak' and pg.nama like ? order by c.waktu_simpan desc");
            } else if (cmbRawat.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("SELECT c.no_rawat, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT( p.tgl_lahir, '%d-%m-%Y' ) tgllhr, "
                        + "DATE_FORMAT( c.tgl_cppt, '%d-%m-%Y' ) tglcppt, c.hasil_pemeriksaan, c.instruksi_nakes, c.verifikasi, pg.nama nmdpjp, c.STATUS, "
                        + "c.tgl_cppt, c.nip_dpjp, c.waktu_simpan, c.cek_jam, c.jam_cppt, IF(c.cek_jam = 'ya', c.jam_cppt, '-') jam_cppt_data, c.jenis_ppa, "
                        + "pg1.nama nmppa, c.jenis_bagian, c.nip_ppa, c.serah_terima_cppt, pg2.nama nmkonsulen, c.nip_konsulen, pg3.nama petugasSerah, "
                        + "pg4.nama petugasTerima, c.nip_petugas_serah, c.nip_petugas_terima, c.cppt_shift, c.jam_serah_terima, c.pilihan_soap, "
                        + "c.subjektif, c.objektif, c.asesmen, c.planing FROM cppt c "
                        + "INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp INNER JOIN pegawai pg1 ON pg1.nik = c.nip_ppa "
                        + "INNER JOIN pegawai pg2 ON pg2.nik = c.nip_konsulen INNER JOIN pegawai pg3 ON pg3.nik = c.nip_petugas_serah "
                        + "INNER JOIN pegawai pg4 ON pg4.nik = c.nip_petugas_terima where "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.no_rawat like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and p.no_rkm_medis like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and p.nm_pasien like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.hasil_pemeriksaan like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.instruksi_nakes like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.verifikasi like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.jenis_bagian like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and c.jenis_ppa like ? or "
                        + "c.tgl_cppt between ? and ? and flag_hapus='tidak' and pg.nama like ? order by c.waktu_simpan desc");
            }
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
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");                
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText().trim() + "%");    
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"), 
                        rs.getString("no_rkm_medis"), 
                        rs.getString("nm_pasien"), 
                        rs.getString("tgllhr"),
                        rs.getString("tglcppt"),
                        rs.getString("jam_cppt_data"),
                        rs.getString("hasil_pemeriksaan"),
                        rs.getString("instruksi_nakes"),
                        rs.getString("verifikasi"),
                        rs.getString("nmdpjp"),
                        rs.getString("status"),
                        rs.getString("tgl_cppt"),
                        rs.getString("nip_dpjp"),
                        rs.getString("waktu_simpan"),
                        rs.getString("cek_jam"),
                        rs.getString("jam_cppt"),
                        rs.getString("jenis_ppa"),
                        rs.getString("nmppa"),
                        rs.getString("jenis_bagian"),
                        rs.getString("nip_ppa"),                        
                        rs.getString("serah_terima_cppt"),
                        rs.getString("nmkonsulen"),
                        rs.getString("nip_konsulen"),
                        rs.getString("petugasSerah"),
                        rs.getString("petugasTerima"),
                        rs.getString("nip_petugas_serah"),
                        rs.getString("nip_petugas_terima"),
                        rs.getString("cppt_shift"),
                        rs.getString("jam_serah_terima"),
                        rs.getString("pilihan_soap"),
                        rs.getString("subjektif"),
                        rs.getString("objektif"),
                        rs.getString("asesmen"),
                        rs.getString("planing")
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
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }

    public void emptTeks() {
        tglCppt.setDate(new Date());
        ChkJam.setSelected(false);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
        THasil.setText("");
        TInstruksi.setText("");
        kddpjp.setText("-");
        nmdpjp.setText("-");
        cmbPPA.setSelectedIndex(0);
        nmppa.setText("-");
        nipppa = "-";
        cmbBagian.setSelectedIndex(0);
        cmbSertim.setSelectedIndex(0);
        nipDPJPlain = "-";
        nmDPJPlainya.setText("-");        
        nipSerah.setText("-");
        nmSerah.setText("-");
        BtnSerah.setEnabled(false);
        nipTerima.setText("-");
        nmTerima.setText("-");
        BtnTerima.setEnabled(false);
        ChkSamaPPA.setSelected(false);
        ChkSamaPPA.setEnabled(false);
        cmbSift.setSelectedIndex(0);
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        MnCeklisFarmasi.setEnabled(false);
        MnPemberianDiet.setEnabled(false);
        cmbSoap.setSelectedIndex(0);
        TSubjektif.setText("");
        TObjektif.setText("");
        TAsesmen.setText("");
        TPlaning.setText("");
        TabPilihan.setSelectedIndex(0);
        TabPilihan.setEnabledAt(0, true);
        TabPilihan.setEnabledAt(1, false);
        tglLapor.setDate(new Date());
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        tglVerifikasi.setDate(new Date());
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        nipPetugasKonfir.setText("-");
        nmPetugasKonfir.setText("-");
        nipKonfirDpjp.setText("-");
        nmKonfirDpjp.setText("-");
        TabPetugas.setSelectedIndex(0);
        chkDpjp.setSelected(false);
    }

    private void getData() {
        statusOK = "";
        cekjam = "";
        nipppa = "";
        nipDPJPlain = "";
        soap = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            TNoRw.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString());
            TNoRm.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString());
            TPasien.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 2).toString());
            Valid.SetTgl(tglCppt, tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 13).toString());
            THasil.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
            TInstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString());
            kddpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 14).toString());
            nmdpjp.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
            statusOK = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 12).toString();
            cekjam = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 16).toString();
            cmbJam.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 17).toString().substring(6, 8));
            cmbPPA.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString());            
            nmppa.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            cmbBagian.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 18).toString());
            nipppa = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 19).toString();
            cmbSertim.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 20).toString());                      
            nmDPJPlainya.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 21).toString());
            nipDPJPlain = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 22).toString();
            nmSerah.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 23).toString());
            nmTerima.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 24).toString());
            nipSerah.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 25).toString());
            nipTerima.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 26).toString());
            cmbSift.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 27).toString());            
            cmbJam1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 28).toString().substring(6, 8));
            soap = tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 29).toString();
            TSubjektif.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 30).toString());
            TObjektif.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 31).toString());
            TAsesmen.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 32).toString());
            TPlaning.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 33).toString());
            dataCek();
            tampilKonfirmasi();
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       BtnEdit.setEnabled(akses.getcppt());
       MnDataSampah.setEnabled(akses.getadmin());
       MnPemberianDiet.setEnabled(akses.getdiet_pasien());
       MnAsuhanGiziHasil.setEnabled(akses.getassesmen_gizi_harian());
       MnAsuhanGiziInstruksi.setEnabled(akses.getassesmen_gizi_harian());
       MnMonevGizi.setEnabled(akses.getmonev_asuhan_gizi());
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.hasil_pemeriksaan<>'' and p.no_rkm_medis like ? OR "
                        + "c.hasil_pemeriksaan<>'' and p.nm_pasien like ? OR "
                        + "c.hasil_pemeriksaan<>'' and c.hasil_pemeriksaan like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.instruksi_nakes<>'' and p.no_rkm_medis like ? OR "
                        + "c.instruksi_nakes<>'' and p.nm_pasien like ? OR "
                        + "c.instruksi_nakes<>'' and c.instruksi_nakes like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            } else if (pilihan == 3) {
                pps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.subjektif<>'' and p.no_rkm_medis like ? OR "
                        + "c.subjektif<>'' and p.nm_pasien like ? OR "
                        + "c.subjektif<>'' and c.subjektif like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            } else if (pilihan == 4) {
                pps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.objektif<>'' and p.no_rkm_medis like ? OR "
                        + "c.objektif<>'' and p.nm_pasien like ? OR "
                        + "c.objektif<>'' and c.objektif like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            } else if (pilihan == 5) {
                pps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.asesmen<>'' and p.no_rkm_medis like ? OR "
                        + "c.asesmen<>'' and p.nm_pasien like ? OR "
                        + "c.asesmen<>'' and c.asesmen like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            } else if (pilihan == 6) {
                pps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                        + "time_format(c.jam_cppt,'%H:%i') jam, c.* from cppt c "
                        + "inner join reg_periksa rp on rp.no_rawat=c.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "c.planing<>'' and p.no_rkm_medis like ? OR "
                        + "c.planing<>'' and p.nm_pasien like ? OR "
                        + "c.planing<>'' and c.planing like ? ORDER BY c.tgl_cppt, c.jam_cppt limit 200");
            }
            
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode1.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("tglcppt"),
                            rrs1.getString("jam"),
                            rrs1.getString("jenis_ppa"),
                            rrs1.getString("cppt_shift"),
                            rrs1.getString("hasil_pemeriksaan")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode1.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("tglcppt"),
                            rrs2.getString("jam"),
                            rrs2.getString("jenis_ppa"),
                            rrs2.getString("cppt_shift"),
                            rrs2.getString("instruksi_nakes")
                        });
                    }
                } else if (pilihan == 3) {
                    pps3.setString(1, "%" + TCari1.getText() + "%");
                    pps3.setString(2, "%" + TCari1.getText() + "%");
                    pps3.setString(3, "%" + TCari1.getText() + "%");
                    rrs3 = pps3.executeQuery();
                    while (rrs3.next()) {
                        tabMode1.addRow(new String[]{
                            rrs3.getString("no_rkm_medis"),
                            rrs3.getString("nm_pasien"),
                            rrs3.getString("tglcppt"),
                            rrs3.getString("jam"),
                            rrs3.getString("jenis_ppa"),
                            rrs3.getString("cppt_shift"),
                            rrs3.getString("subjektif")
                        });
                    }
                } else if (pilihan == 4) {
                    pps4.setString(1, "%" + TCari1.getText() + "%");
                    pps4.setString(2, "%" + TCari1.getText() + "%");
                    pps4.setString(3, "%" + TCari1.getText() + "%");
                    rrs4 = pps4.executeQuery();
                    while (rrs4.next()) {
                        tabMode1.addRow(new String[]{
                            rrs4.getString("no_rkm_medis"),
                            rrs4.getString("nm_pasien"),
                            rrs4.getString("tglcppt"),
                            rrs4.getString("jam"),
                            rrs4.getString("jenis_ppa"),
                            rrs4.getString("cppt_shift"),
                            rrs4.getString("objektif")
                        });
                    }
                } else if (pilihan == 5) {
                    pps5.setString(1, "%" + TCari1.getText() + "%");
                    pps5.setString(2, "%" + TCari1.getText() + "%");
                    pps5.setString(3, "%" + TCari1.getText() + "%");
                    rrs5 = pps5.executeQuery();
                    while (rrs5.next()) {
                        tabMode1.addRow(new String[]{
                            rrs5.getString("no_rkm_medis"),
                            rrs5.getString("nm_pasien"),
                            rrs5.getString("tglcppt"),
                            rrs5.getString("jam"),
                            rrs5.getString("jenis_ppa"),
                            rrs5.getString("cppt_shift"),
                            rrs5.getString("asesmen")
                        });
                    }
                } else if (pilihan == 6) {
                    pps6.setString(1, "%" + TCari1.getText() + "%");
                    pps6.setString(2, "%" + TCari1.getText() + "%");
                    pps6.setString(3, "%" + TCari1.getText() + "%");
                    rrs6 = pps6.executeQuery();
                    while (rrs6.next()) {
                        tabMode1.addRow(new String[]{
                            rrs6.getString("no_rkm_medis"),
                            rrs6.getString("nm_pasien"),
                            rrs6.getString("tglcppt"),
                            rrs6.getString("jam"),
                            rrs6.getString("jenis_ppa"),
                            rrs6.getString("cppt_shift"),
                            rrs6.getString("planing")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } else if (rrs3 != null) {
                    rrs3.close();
                } else if (rrs4 != null) {
                    rrs4.close();
                } else if (rrs5 != null) {
                    rrs5.close();
                } else if (rrs6 != null) {
                    rrs6.close();
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } else if (pps3 != null) {
                    pps3.close();
                } else if (pps4 != null) {
                    pps4.close();
                } else if (pps5 != null) {
                    pps5.close();
                } else if (pps6 != null) {
                    pps6.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            THasil.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            TInstruksi.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            TSubjektif.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            TObjektif.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            TAsesmen.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            TPlaning.setText(Ttemplate.getText());
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String sttsrawat, String gedung) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TCari.setText(norw); 
        status = sttsrawat;
        
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        DTPCari2.setDate(new Date());
        ChkJam.setSelected(true);
        cmbJam.setEnabled(true);
        cmbMnt.setEnabled(true);
        cmbDtk.setEnabled(true);        
        isJam();
        isRawat();
        
        if (sttsrawat.equals("IGD (Ralan)") || sttsrawat.equals("IGD (Ranap)") || sttsrawat.equals("ralan")) {
            isIGD_Ralan();
        } else if (sttsrawat.equals("ranap")) {
            isRanap(gedung);
        } else {
            cmbRawat.setSelectedIndex(2);
            cmbPPA.setSelectedIndex(0);
            cmbPPA.setEnabled(true);
            cmbSertim.setSelectedIndex(0);
            cmbSertim.setEnabled(true);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            nipppa = "-";
            nmppa.setText("-");
            nmppa.setEnabled(true);
            BtnPPA.setEnabled(true);
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(false);
        }
    }
    
    private void dataCek() {
        if (cekjam.equals("ya")) {
            ChkJam.setSelected(true);
            cmbJam.setEnabled(true);
            cmbMnt.setEnabled(true);
            cmbDtk.setEnabled(true);
        } else {
            ChkJam.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
        }

        if (cmbSertim.getSelectedIndex() == 1) {
            cmbSertim.setEnabled(true);
            BtnSerah.setEnabled(true);
            BtnTerima.setEnabled(true);
            ChkSamaPPA.setSelected(false);
            ChkSamaPPA.setEnabled(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
        } else {
            cmbSertim.setEnabled(false);
            BtnSerah.setEnabled(false);
            BtnTerima.setEnabled(false);
            ChkSamaPPA.setSelected(false);
            ChkSamaPPA.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
        }
        
        if (soap.equals("Pilihan 1")) {
            cmbSoap.setSelectedIndex(0);
            TabPilihan.setSelectedIndex(0);
            TabPilihan.setEnabledAt(0, true);
            TabPilihan.setEnabledAt(1, false);
        } else {
            cmbSoap.setSelectedIndex(1);
            TabPilihan.setSelectedIndex(1);
            TabPilihan.setEnabledAt(0, false);
            TabPilihan.setEnabledAt(1, true);
        }
        
        //cek shift ranap
        if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)")) {
            cmbSift.setEnabled(false);
            cmbSiftCppt.setEnabled(false);
        } else if (status.equals("ranap")) {
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(true);
        } else {
            cmbSift.setEnabled(true);
            cmbSiftCppt.setEnabled(true);
        }
    }
    
    private void cetakCPPTranap() {        
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));

        if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap), Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("1", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);

                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //periode    
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' "
                    + "and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("2", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() != 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Shift : " + cmbSiftPetugas.getSelectedItem().toString());
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak' "
                    + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("3", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
            
            //-----------------------------------------------------------------------------------------------------------
        } else if (cmbTanggal.getSelectedIndex() == 0 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap), Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("4", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);                
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //periode    
        } else if (cmbTanggal.getSelectedIndex() == 1 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Periode Tgl. " + tglA.getSelectedItem() + " S.D Tgl. " + tglB.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak' "
                    + "and tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("5", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }

            //pertanggal
        } else if (cmbTanggal.getSelectedIndex() == 2 && cmbSiftPetugas.getSelectedIndex() == 4) {
            param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (Rawat Inap)\n"
                    + "Tgl. " + tglA.getSelectedItem() + ", Semua Shift Petugas");
            if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ranap' and flag_hapus='tidak' "
                    + "and tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "'") == 0) {
                JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
            } else {
                simpanTemporaryCppt("6", "Ranap");
                Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Inap ]::",
                        "SELECT * from temporary_cppt", param);
                
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
                BtnCloseIn6ActionPerformed(null);
            }
        }
    }

    private void cetakCPPTralan() {
        Map<String, Object> param = new HashMap<>();
        param.put("namars", akses.getnamars());
        param.put("logo", Sequel.cariGambar("select logo from setting"));
        param.put("judul", "CATATAN PERKEMBANGAN PASIEN TERINTEGRASI (IGD)");
        if (Sequel.cariInteger("select count(-1) from cppt where no_rawat = '" + TNoRw.getText() + "' AND STATUS='Ralan' and flag_hapus='tidak'") == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data tidak ditemukan, silahkan ulangi lagi..!!");
        } else {
            simpanTemporaryCppt("4", "Ralan");
            Valid.MyReport("rptCPPT.jasper", "report", "::[ Laporan CPPT Rawat Jalan/IGD ]::",
                    "SELECT * from temporary_cppt", param);

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            BtnCloseIn6ActionPerformed(null);
        }
    }
    
    public void setUtama() {
        cmbRawat.setSelectedIndex(2);
        cmbSiftCppt.setSelectedIndex(0);
        cmbSiftCppt.setEnabled(true);
    }
    
    private void getDiet() {
        waktuSimpanDiet = "";
        if (tbDiet.getSelectedRow() != -1) {
            kddiet.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 0).toString());
            nmdiet.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 1).toString());
            jnsMakanan.setText(tbDiet.getValueAt(tbDiet.getSelectedRow(), 2).toString());
            waktuSimpanDiet = tbDiet.getValueAt(tbDiet.getSelectedRow(), 4).toString();
        }
    }
    
    private void simpanDietINAP() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ranap_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet sore");
                }
            }
            dataDietnya();
            BtnKeluar1ActionPerformed(null);
        }  
    }
    
    private void dietOK() {
        dataDiet = "";
        TKd.setText("");
        
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (dataDiet.equals("")) {
                dataDiet = tbDiet.getValueAt(i, 1).toString() + " " + tbDiet.getValueAt(i, 3).toString();
            } else {
                dataDiet = dataDiet + " + " + tbDiet.getValueAt(i, 1).toString() + " " + tbDiet.getValueAt(i, 3).toString();
            }
        }
        
        if (Sequel.cariInteger("select count(-1) from diet where nama_diet='" + dataDiet + "'") == 0) {
            Valid.autoNomer("diet", "DB", 4, TKd);
            Sequel.menyimpan("diet", "'" + TKd.getText() + "','" + dataDiet + "','UMUM','1'", "Data diet pasien");
        } else {
            TKd.setText(Sequel.cariIsi("select kd_diet from diet where nama_diet='" + dataDiet + "'"));
        }
    }
    
    private void isRawat() {
        try {
            pspasien = koneksi.prepareStatement("select date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lhr, rp.status_lanjut, rp.kd_poli from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rspasien = pspasien.executeQuery();
                while (rspasien.next()) {
                    jnsRawat = rspasien.getString("status_lanjut");

                    if (jnsRawat.equals("Ralan")) {
                        kdUnit = rspasien.getString("kd_poli");
                    } else if (jnsRawat.equals("Ranap")) {
                        kdUnit = "";
                        kdUnit = Sequel.cariIsi("select kd_kamar from kamar_inap where no_rawat='" + TNoRw.getText() + "' order by tgl_masuk desc, jam_masuk desc limit 1");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    }
    
    private void simpanDietJALAN() {
        if ((cekDietPagi.isSelected() == false) && (cekDietSiang.isSelected() == false) && (cekDietSore.isSelected() == false)) {
            JOptionPane.showMessageDialog(null, "Waktu diet harus dipilih...!!!!");
        } else {
            dietOK();
            if (cekDietPagi.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Pagi','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet pagi");
                }
            }
            if (cekDietSiang.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Siang','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet siang");
                }
            }
            if (cekDietSore.isSelected() == true) {
                Sequel.menyimpan("detail_beri_diet_ralan", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                        + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                        + TKd.getText() + "','" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "data");

                for (i = 0; i < tbDiet.getRowCount(); i++) {
                    Sequel.menyimpan("diet_ralan_daftar_rincian", "'" + TNoRw.getText() + "','" + kdUnit + "','"
                            + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "','Sore','"
                            + tbDiet.getValueAt(i, 0).toString() + "','" + tbDiet.getValueAt(i, 3).toString() + "',"
                            + "'" + Sequel.cariIsi("SELECT NOW()") + "','" + kemasan + "'", "diet sore");
                }
            }
            dataDietnya();
            BtnKeluar1ActionPerformed(null);
        }
    }
    
    private void dataDietnya() {
        instruksiDiet = "";
        try {
            psdiet = koneksi.prepareStatement("SELECT concat('Waktu ',db.waktu,' : ') wkt, d.nama_diet FROM detail_beri_diet db "
                    + "INNER JOIN diet d on db.kd_diet = d.kd_diet WHERE "
                    + "db.tanggal='" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + "' AND db.no_rawat LIKE '%" + TNoRw.getText() + "%' "
                    + "ORDER BY db.tanggal, d.nama_diet");
            try {
                rsdiet = psdiet.executeQuery();
                while (rsdiet.next()) {
                    if (instruksiDiet.equals("")) {
                        instruksiDiet = rsdiet.getString("wkt") + " " + rsdiet.getString("nama_diet");
                    } else {
                        instruksiDiet = instruksiDiet + "\n" + rsdiet.getString("wkt") + " " + rsdiet.getString("nama_diet");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        if (TInstruksi.getText().equals("")) {
            TInstruksi.setText("Diet Harian Pasien :\n" + instruksiDiet);
        } else {
            TInstruksi.setText(TInstruksi.getText() + "\n\nDiet Harian Pasien :\n" + instruksiDiet);
        }
    }
    
    private void tampilSampah() {
        Valid.tabelKosong(tabMode3);
        try {
            psrestor = koneksi.prepareStatement("SELECT if(pg4.nama='-','Admin Utama',pg4.nama) dihapus, c.no_rawat, "
                    + "p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                    + "time_format(c.jam_cppt,'%H:%i') jamcppt, pg1.nama dpjp, c.status, c.jenis_ppa, pg2.nama nmppa, "
                    + "c.jenis_bagian, pg3.nama nmkonsulen, c.cppt_shift, c.flag_hapus, c.waktu_simpan from cppt c "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=c.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg1 on pg1.nik=c.nip_dpjp INNER JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                    + "INNER JOIN pegawai pg3 on pg3.nik=c.nip_konsulen INNER JOIN pegawai pg4 on pg4.nik=c.nip_penghapus where "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and pg4.nama like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and c.no_rawat like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and p.no_rkm_medis like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and p.nm_pasien like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and pg1.nama like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and c.status like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and c.jenis_ppa like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and pg2.nama like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and c.jenis_bagian like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and pg3.nama like ? or "
                    + "c.tgl_cppt between ? and ? and flag_hapus='ya' and c.cppt_shift like ? "
                    + "order by c.tgl_cppt desc, c.jam_cppt desc");
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(16, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(17, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(18, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(19, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(20, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(21, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(22, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(23, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(24, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(25, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(26, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
		psrestor.setString(27, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(28, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(29, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
		psrestor.setString(30, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(31, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(32, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
		psrestor.setString(33, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode3.addRow(new String[]{
                        rsrestor.getString("dihapus"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tglcppt"),
                        rsrestor.getString("jamcppt"),
                        rsrestor.getString("dpjp"),
                        rsrestor.getString("status"),
                        rsrestor.getString("jenis_ppa"),
                        rsrestor.getString("nmppa"),
                        rsrestor.getString("jenis_bagian"),
                        rsrestor.getString("nmkonsulen"),
                        rsrestor.getString("cppt_shift"),
                        rsrestor.getString("flag_hapus"),
                        rsrestor.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode3.getRowCount());
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
            psLaprm = koneksi.prepareStatement("select * from transfer_serah_terima_pasien_igd where no_rawat='" + TNoRw.getText() + "'");
            try {
                rsLaprm = psLaprm.executeQuery();
                while (rsLaprm.next()) {
                    Map<String, Object> param = new HashMap<>();
                    param.put("namars", akses.getnamars());
                    param.put("logo", Sequel.cariGambar("select logo from setting"));
                    param.put("norm", TNoRm.getText());
                    param.put("nmpasien", Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
                    param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
                    param.put("nmdpjpp", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_dpjp") + "'"));
                    param.put("konsulen1", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_konsulen1") + "'"));
                    param.put("konsulen2", Sequel.cariIsi("select nama from pegawai where nik='" + rsLaprm.getString("nip_konsulen2") + "'"));
                    param.put("diagnosis", rsLaprm.getString("diagnosis"));
                    param.put("tglmasuk", Sequel.cariIsi("select date_format(tgl_masuk,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));

                    if (rsLaprm.getString("kd_kamar_msk").equals("")) {
                        param.put("ruangkamar", "");
                    } else if (rsLaprm.getString("kd_kamar_msk").equals("IGDK")) {
                        param.put("ruangkamar", Sequel.cariIsi("SELECT nm_poli FROM poliklinik WHERE kd_poli='" + rsLaprm.getString("kd_kamar_msk") + "'"));
                    } else {
                        param.put("ruangkamar", Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + rsLaprm.getString("kd_kamar_msk") + "'"));
                    }

                    param.put("tgljampindah", Sequel.cariIsi("select date_format(tgl_jam_pindah,'%d-%m-%Y / %H:%i') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
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
                    param.put("tgltransferserah",
                            Sequel.cariIsi("select date_format(tgl_serah_terima_transfer,'%d') from transfer_serah_terima_pasien_igd where "
                                    + "no_rawat='" + rsLaprm.getString("no_rawat") + "'") + " "
                            + Sequel.bulanINDONESIA("select date_format(tgl_serah_terima_transfer,'%m') from transfer_serah_terima_pasien_igd where "
                                    + "no_rawat='" + rsLaprm.getString("no_rawat") + "'") + " "
                            + Sequel.cariIsi("select date_format(tgl_serah_terima_transfer,'%Y') from transfer_serah_terima_pasien_igd where "
                                    + "no_rawat='" + rsLaprm.getString("no_rawat") + "'"));

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
                        param.put("tglinfus", Sequel.cariIsi("select date_format(tgl_infus,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("infus", "");
                        param.put("tglinfus", "-");
                    }

                    if (rsLaprm.getString("kateter").equals("ya")) {
                        param.put("kateter", "V");
                        param.put("tglkateter", Sequel.cariIsi("select date_format(tgl_kateter,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("kateter", "");
                        param.put("tglkateter", "-");
                    }

                    if (rsLaprm.getString("ngt").equals("ya")) {
                        param.put("ngt", "V");
                        param.put("tglngt", Sequel.cariIsi("select date_format(tgl_ngt,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("ngt", "");
                        param.put("tglngt", "-");
                    }

                    if (rsLaprm.getString("oksigen").equals("ya")) {
                        param.put("oksigen", "V");
                        param.put("tgloksigen", Sequel.cariIsi("select date_format(tgl_oksigen,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("oksigen", "");
                        param.put("tgloksigen", "-");
                    }

                    if (rsLaprm.getString("drain").equals("ya")) {
                        param.put("drain", "V");
                        param.put("tgldrain", Sequel.cariIsi("select date_format(tgl_drain,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("drain", "");
                        param.put("tgldrain", "-");
                    }

                    if (rsLaprm.getString("lainya_alat").equals("ya")) {
                        param.put("lainalat", "V");
                        param.put("nmalatlain", rsLaprm.getString("nm_alat_lain"));
                        param.put("tgllainalat", Sequel.cariIsi("select date_format(tgl_alat_lain,'%d-%m-%Y') from transfer_serah_terima_pasien_igd where no_rawat='" + rsLaprm.getString("no_rawat") + "'"));
                    } else {
                        param.put("lainalat", "");
                        param.put("nmalatlain", "-");
                        param.put("tgllainalat", "-");
                    }

                    if (Sequel.cariInteger("select count(-1) from pemberian_obat where "
                            + "no_rawat='" + rsLaprm.getString("no_rawat") + "' and status='Ralan'") == 0) {
                        Valid.MyReport("rptTransferPasienIGDnonResep.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien IGD ]::",
                                "SELECT date(now())", param);
                    } else {
                        Valid.MyReport("rptTransferPasienIGD.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien IGD ]::",
                                "SELECT * FROM pemberian_obat WHERE no_rawat ='" + rsLaprm.getString("no_rawat") + "' "
                                + "and status='Ralan' ORDER BY waktu_simpan desc", param);
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
    
    private void tampilKonfirmasi() {
        Valid.tabelKosong(tabMode4);
        try {
            ps1 = koneksi.prepareStatement("select date_format(ck.tgl_cppt,'%d-%m-%Y') tglcppt, time_format(ck.jam_cppt,'%H:%i') jamcppt, ck.cppt_shift, "
                    + "pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif, "
                    + "ck.tgl_cppt, ck.jam_cppt, ck.tgl_lapor, ck.jam_lapor, ck.tgl_verifikasi, ck.jam_verifikasi, ck.no_rawat, "
                    + "ck.nip_petugas_konfir, ck.nip_dpjp_konfir, ck.waktu_simpan from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat='" + TNoRw.getText() + "' and ck.tgl_cppt='" + Valid.SetTgl(tglCppt.getSelectedItem() + "") + "' "
                    + "and ck.cppt_shift='" + cmbSift.getSelectedItem().toString() + "' and "
                    + "ck.jam_cppt='" + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + ":" + cmbDtk.getSelectedItem().toString() + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode4.addRow(new Object[]{
                        rs1.getString("tglcppt"),
                        rs1.getString("jamcppt"),
                        rs1.getString("cppt_shift"),
                        rs1.getString("ptgs"),
                        rs1.getString("tgllapor"),
                        rs1.getString("jamlapor"),
                        rs1.getString("dpjp"),
                        rs1.getString("tglverif"),
                        rs1.getString("jamverif"),
                        rs1.getString("tgl_cppt"),
                        rs1.getString("jam_cppt"),
                        rs1.getString("tgl_lapor"),
                        rs1.getString("jam_lapor"),
                        rs1.getString("tgl_verifikasi"),
                        rs1.getString("jam_verifikasi"),
                        rs1.getString("no_rawat"),
                        rs1.getString("nip_petugas_konfir"),
                        rs1.getString("nip_dpjp_konfir"),
                        rs1.getString("waktu_simpan")
                    });
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
    
    private void getDataKonfirmasi() {
        wktSimpanKonfirmasi = "";
        if (tbKonfirmasi.getSelectedRow() != -1) {
            Valid.SetTgl(tglLapor, tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 11).toString());
            cmbJam2.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 12).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 12).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 12).toString().substring(6, 8));
            Valid.SetTgl(tglVerifikasi, tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 13).toString());
            cmbJam3.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 14).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 14).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 14).toString().substring(6, 8));
            nipPetugasKonfir.setText(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 16).toString());
            nipKonfirDpjp.setText(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 17).toString());
            nmPetugasKonfir.setText(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 3).toString());
            nmKonfirDpjp.setText(tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 6).toString());
            wktSimpanKonfirmasi = tbKonfirmasi.getValueAt(tbKonfirmasi.getSelectedRow(), 18).toString();
        }
    }
    
    private void konfirmasiTerapiPreview(String norw, String tgl, String jam, String sift) {
        konfirmasi_terapi = "";
        try {
            ps2 = koneksi.prepareStatement("SELECT date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i WITA') jamlapor, "
                    + "date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i WITA') jamverif, "
                    + "pg1.nama ptgs, pg2.nama dpjp FROM cppt_konfirmasi_terapi ck INNER JOIN pegawai pg1 ON pg1.nik = ck.nip_petugas_konfir "
                    + "INNER JOIN pegawai pg2 ON pg2.nik = ck.nip_dpjp_konfir WHERE "
                    + "ck.no_rawat = '" + norw + "' and ck.tgl_cppt='" + tgl + "' and ck.jam_cppt='" + jam + "' and ck.cppt_shift='" + sift + "' "
                    + "ORDER BY ck.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (konfirmasi_terapi.equals("")) {
                        konfirmasi_terapi
                                = "KONFIRMASI TERAPI VIA TELP. :<br/>Tgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + "<br/>"
                                + "Tgl. Verif : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + "<br/>"
                                + "Petugas,<br/><br/><br/><br/>(" + rs2.getString("ptgs") + ")<br/><br/>"
                                + "Dengan DPJP,<br/><br/><br/><br/>(" + rs2.getString("dpjp") + ")";
                    } else {
                        konfirmasi_terapi = konfirmasi_terapi + "<br/>"
                                + "KONFIRMASI TERAPI VIA TELP. :<br/>Tgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + "<br/>"
                                + "Tgl. Verif : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + "<br/>"
                                + "Petugas,<br/><br/><br/><br/>(" + rs2.getString("ptgs") + ")<br/><br/>"
                                + "Dengan DPJP,<br/><br/><br/><br/>(" + rs2.getString("dpjp") + ")";
                    }
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
    
    private void konfirmasiTerapi(String norw, String tgl, String jam, String sift) {
        konfirmasi_terapi = "";
        try {
            ps2 = koneksi.prepareStatement("SELECT date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i WITA') jamlapor, "
                    + "date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i WITA') jamverif, "
                    + "pg1.nama ptgs, pg2.nama dpjp FROM cppt_konfirmasi_terapi ck INNER JOIN pegawai pg1 ON pg1.nik = ck.nip_petugas_konfir "
                    + "INNER JOIN pegawai pg2 ON pg2.nik = ck.nip_dpjp_konfir WHERE "
                    + "ck.no_rawat = '" + norw + "' and ck.tgl_cppt='" + tgl + "' and ck.jam_cppt='" + jam + "' and ck.cppt_shift='" + sift + "' "
                    + "ORDER BY ck.waktu_simpan");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (konfirmasi_terapi.equals("")) {
                        konfirmasi_terapi
                                = "KONFIRMASI TERAPI VIA TELP. :\nTgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + "\n"
                                + "Tgl. Verif : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + "\n"
                                + "Petugas,\n\n\n\n(" + rs2.getString("ptgs") + ")\n\n"
                                + "Dengan DPJP,\n\n\n\n(" + rs2.getString("dpjp") + ")";
                    } else {
                        konfirmasi_terapi = konfirmasi_terapi + "\n"
                                + "KONFIRMASI TERAPI VIA TELP. :\nTgl. Lapor : " + rs2.getString("tgllapor") + ", Jam : " + rs2.getString("jamlapor") + "\n"
                                + "Tgl. Verif : " + rs2.getString("tglverif") + ", Jam : " + rs2.getString("jamverif") + "\n"
                                + "Petugas,\n\n\n\n(" + rs2.getString("ptgs") + ")\n\n"
                                + "Dengan DPJP,\n\n\n\n(" + rs2.getString("dpjp") + ")";
                    }
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
    
    private void simpanHistory(String ws) {
        try {
            ps3 = koneksi.prepareStatement("select * from cppt where waktu_simpan='" + ws + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    Sequel.menyimpanPesanGagalnyaDiTerminal("cppt_history", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "CPPT History", 29, new String[]{
                        rs3.getString("no_rawat"),
                        rs3.getString("tgl_cppt"),
                        rs3.getString("bagian"),
                        Valid.mysql_real_escape_stringERM(rs3.getString("hasil_pemeriksaan")),
                        Valid.mysql_real_escape_stringERM(rs3.getString("instruksi_nakes")),
                        rs3.getString("verifikasi"),
                        rs3.getString("nip_dpjp"),
                        rs3.getString("status"),
                        rs3.getString("waktu_simpan"),
                        rs3.getString("cek_jam"),
                        rs3.getString("jam_cppt"),
                        rs3.getString("jenis_ppa"),
                        rs3.getString("nip_ppa"),
                        rs3.getString("jenis_bagian"),
                        rs3.getString("serah_terima_cppt"),
                        rs3.getString("nip_konsulen"),
                        rs3.getString("nip_petugas_serah"),
                        rs3.getString("nip_petugas_terima"),
                        rs3.getString("cppt_shift"),
                        rs3.getString("jam_serah_terima"),
                        rs3.getString("flag_hapus"),
                        rs3.getString("nip_penghapus"),
                        rs3.getString("pilihan_soap"),
                        Valid.mysql_real_escape_stringERM(rs3.getString("subjektif")),
                        Valid.mysql_real_escape_stringERM(rs3.getString("objektif")),
                        Valid.mysql_real_escape_stringERM(rs3.getString("asesmen")),
                        Valid.mysql_real_escape_stringERM(rs3.getString("planing")),
                        Sequel.cariIsi("select now()"),
                        akses.getkode()
                    });
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanHistory(String wg) {
        try {
            ps5 = koneksi.prepareStatement("select * from cppt_history where waktu_ganti='" + wg + "'");
            try {
                rs5 = ps5.executeQuery();
                while (rs5.next()) {
                    Sequel.menyimpan("cppt",
                            "'" + rs5.getString("no_rawat") + "',"
                            + "'" + rs5.getString("tgl_cppt") + "',"
                            + "'" + rs5.getString("bagian") + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("hasil_pemeriksaan")) + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("instruksi_nakes")) + "',"
                            + "'" + rs5.getString("verifikasi") + "',"
                            + "'" + rs5.getString("nip_dpjp") + "',"
                            + "'" + rs5.getString("status") + "',"
                            + "'" + Sequel.cariIsi("select now()") + "',"
                            + "'" + rs5.getString("cek_jam") + "',"
                            + "'" + rs5.getString("jam_cppt") + "',"
                            + "'" + rs5.getString("jenis_ppa") + "',"
                            + "'" + rs5.getString("nip_ppa") + "',"
                            + "'" + rs5.getString("jenis_bagian") + "',"
                            + "'" + rs5.getString("serah_terima_cppt") + "',"
                            + "'" + rs5.getString("nip_konsulen") + "',"
                            + "'" + rs5.getString("nip_petugas_serah") + "',"
                            + "'" + rs5.getString("nip_petugas_terima") + "',"
                            + "'" + rs5.getString("cppt_shift") + "',"
                            + "'" + rs5.getString("jam_serah_terima") + "',"
                            + "'" + rs5.getString("flag_hapus") + "',"
                            + "'" + rs5.getString("nip_penghapus") + "',"
                            + "'" + rs5.getString("pilihan_soap") + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("subjektif")) + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("objektif")) + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("asesmen")) + "',"
                            + "'" + Valid.mysql_real_escape_stringERM(rs5.getString("planing")) + "'", "CPPT Pasien");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs5 != null) {
                    rs5.close();
                }
                if (ps5 != null) {
                    ps5.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode5);
        try {
            ps4 = koneksi.prepareStatement("SELECT if(pg4.nama='-','Admin Utama',pg4.nama) diganti, c.no_rawat, "
                    + "p.no_rkm_medis, p.nm_pasien, date_format(c.tgl_cppt,'%d-%m-%Y') tglcppt, "
                    + "time_format(c.jam_cppt,'%H:%i') jamcppt, pg1.nama dpjp, c.status, c.jenis_ppa, pg2.nama nmppa, "
                    + "c.jenis_bagian, pg3.nama nmkonsulen, c.cppt_shift, date_format(c.waktu_ganti,'%d-%m-%Y %H:%i:%s') wktganti, c.waktu_ganti from cppt_history c "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=c.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg1 on pg1.nik=c.nip_dpjp INNER JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                    + "INNER JOIN pegawai pg3 on pg3.nik=c.nip_konsulen INNER JOIN pegawai pg4 on pg4.nik=c.nip_pengganti where "
                    + "c.tgl_cppt between ? and ? and pg4.nama like ? or "
                    + "c.tgl_cppt between ? and ? and c.no_rawat like ? or "
                    + "c.tgl_cppt between ? and ? and p.no_rkm_medis like ? or "
                    + "c.tgl_cppt between ? and ? and p.nm_pasien like ? or "
                    + "c.tgl_cppt between ? and ? and pg1.nama like ? or "
                    + "c.tgl_cppt between ? and ? and c.status like ? or "
                    + "c.tgl_cppt between ? and ? and c.jenis_ppa like ? or "
                    + "c.tgl_cppt between ? and ? and pg2.nama like ? or "
                    + "c.tgl_cppt between ? and ? and c.jenis_bagian like ? or "
                    + "c.tgl_cppt between ? and ? and pg3.nama like ? or "
                    + "c.tgl_cppt between ? and ? and c.cppt_shift like ? "
                    + "order by c.waktu_ganti desc");
            try {
                ps4.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(3, "%" + TCari2.getText().trim() + "%");
                ps4.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(6, "%" + TCari2.getText().trim() + "%");
                ps4.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(9, "%" + TCari2.getText().trim() + "%");
                ps4.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(12, "%" + TCari2.getText().trim() + "%");
                ps4.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(15, "%" + TCari2.getText().trim() + "%");
                ps4.setString(16, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(17, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(18, "%" + TCari2.getText().trim() + "%");
                ps4.setString(19, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(20, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(21, "%" + TCari2.getText().trim() + "%");
                ps4.setString(22, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(23, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(24, "%" + TCari2.getText().trim() + "%");
                ps4.setString(25, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(26, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(27, "%" + TCari2.getText().trim() + "%");
                ps4.setString(28, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(29, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(30, "%" + TCari2.getText().trim() + "%");
                ps4.setString(31, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                ps4.setString(32, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                ps4.setString(33, "%" + TCari2.getText().trim() + "%");
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    tabMode5.addRow(new String[]{
                        rs4.getString("diganti"),
                        rs4.getString("no_rawat"),
                        rs4.getString("no_rkm_medis"),
                        rs4.getString("nm_pasien"),
                        rs4.getString("tglcppt"),
                        rs4.getString("jamcppt"),
                        rs4.getString("dpjp"),
                        rs4.getString("status"),
                        rs4.getString("jenis_ppa"),
                        rs4.getString("nmppa"),
                        rs4.getString("jenis_bagian"),
                        rs4.getString("nmkonsulen"),
                        rs4.getString("cppt_shift"),
                        rs4.getString("wktganti"),
                        rs4.getString("waktu_ganti")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode5.getRowCount());
    }
    
    private void isJam() {
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);

        try {
            jamSekarang = new SimpleDateFormat("HH:mm").parse(Sequel.cariIsi("SELECT TIME_FORMAT(NOW(),'%H:%i')"));
            jamCPPT1 = new SimpleDateFormat("HH:mm").parse("08:00");
            jamCPPT2 = new SimpleDateFormat("HH:mm").parse("14:00");
            jamCPPT3 = new SimpleDateFormat("HH:mm").parse("20:00");
        } catch (Exception e) {
            System.out.println("Tanggal error, cek lagi..!!");
        }

        if (jamCPPT1.before(jamSekarang)) {
            cmbSift.setSelectedIndex(1);
        }

        if (jamCPPT2.before(jamSekarang)) {
            cmbSift.setSelectedIndex(2);
        }

        if (jamCPPT3.before(jamSekarang)) {
            cmbSift.setSelectedIndex(3);
        }
    }
    
    private void isIGD_Ralan() {
        cmbRawat.setSelectedIndex(0);
        cmbPPA.setSelectedIndex(0);
        cmbPPA.setEnabled(false);
        cmbSertim.setSelectedIndex(0);
        cmbSertim.setEnabled(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        nipppa = "-";
        nmppa.setText("-");
        nmppa.setEnabled(false);
        BtnPPA.setEnabled(false);
        cmbSift.setEnabled(false);
        cmbSiftCppt.setEnabled(false);

        if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='Ralan' and flag_hapus='tidak'") > 0) {
            TabCPPT.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='Ralan' and flag_hapus='tidak'") == 0) {
            TabCPPT.setSelectedIndex(0);
        }
    }
    
    private void isRanap(String namagedung) {
        cmbRawat.setSelectedIndex(1);        
        cmbPPA.setEnabled(true);
        cmbSertim.setSelectedIndex(0);
        cmbSertim.setEnabled(true);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        nipppa = "-";
        nmppa.setText("-");
        nmppa.setEnabled(true);
        BtnPPA.setEnabled(true);
        cmbSift.setEnabled(true);
        cmbSiftCppt.setEnabled(true);

        if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='ranap' and flag_hapus='tidak'") > 0) {
            cmbSiftCppt.setSelectedIndex(4);
            TabCPPT.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from cppt where no_rawat='" + TNoRw.getText() + "' and status='ranap' and flag_hapus='tidak'") == 0) {
            cmbSiftCppt.setSelectedIndex(0);
            TabCPPT.setSelectedIndex(0);
        }

        //khusus dr. riswan di ruang as-sami
        if (akses.getkode().equals("197606202002121006") && namagedung.equals("AS-SAMI")) {
            chkDpjp.setSelected(true);
            kddpjp.setText(akses.getkode());
            nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
            cmbBagian.setSelectedIndex(4);
            cmbPPA.setSelectedIndex(7);
            cmbSoap.setSelectedIndex(1);
        } else {
            if (Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {                
                kddpjp.setText(Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + TNoRw.getText() + "'"));
                nmdpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddpjp.getText() + "'"));
            } else {
                chkDpjpActionPerformed(null);
            }
        }
    }
    
    private void asuhanGizi(String tujuanPaste) {
        emptVariabelGizi();        
        try {
            ps6 = koneksi.prepareStatement("select *, date_format(tgl_asuhan,'%d/%m/%Y') tgl from asuhan_gizi_ranap where no_rawat like ?");
            try {
                ps6.setString(1, "%" + TNoRw.getText().trim() + "%");
                rs6 = ps6.executeQuery();
                while (rs6.next()) {
                    //status gizi
                    if (rs6.getString("jenis_asuhan").equals("Dewasa")) {
                        if (rs6.getString("klasifikasi_imt").equals("Berdasarkan Kemenkes RI")) {
                            cekSttsGizi = "Klasifikasi IMT (" + rs6.getString("klasifikasi_imt") + "), Status Gizi : " + rs6.getString("status_gizi");
                        } else if (rs6.getString("klasifikasi_imt").equals("Berdasarkan CDC")) {
                            cekSttsGizi = "Klasifikasi IMT (" + rs6.getString("klasifikasi_imt") + "), Persentase CDC : " + rs6.getString("persentase_cdc") + " %, Status Gizi : " + rs6.getString("status_gizi");
                        } else {
                            cekSttsGizi = "Status Gizi : " + rs6.getString("status_gizi");
                        }
                    } else if (rs6.getString("jenis_asuhan").equals("Anak")) {
                        if (rs6.getString("indek_bbu").equals("ya")) {
                            sttsgz1 = " - Indeks (BB/U) : " + rs6.getString("ket_indek_bbu") + ", Kategori Status Gizi : " + rs6.getString("stts_gizi_bbu") + "\n";
                        } else {
                            sttsgz1 = "";
                        }

                        if (rs6.getString("indek_pbu").equals("ya")) {
                            sttsgz2 = " - Indeks (PB/U atau TB/U) : " + rs6.getString("ket_indek_pbu") + ", Kategori Status Gizi : " + rs6.getString("stts_gizi_pbu") + "\n";
                        } else {
                            sttsgz2 = "";
                        }

                        if (rs6.getString("indek_bbpb").equals("ya")) {
                            sttsgz3 = " - Indeks (BB/PB atau BB/TB) : " + rs6.getString("ket_indek_bbpb") + ", Kategori Status Gizi : " + rs6.getString("stts_gizi_bbpb") + "\n";
                        } else {
                            sttsgz3 = "";
                        }
                        cekSttsGizi = "Status Gizi : \n" + sttsgz1 + sttsgz2 + sttsgz3;
                    } else {
                        cekSttsGizi = "";
                    }
                    
                    //klinis fisik
                    if (rs6.getString("mual_muntah").equals("ya")) {
                        klinis1 = "Mual Muntah, ";
                    } else {
                        klinis1 = "";
                    }

                    if (rs6.getString("nyeri_ulu_hati").equals("ya")) {
                        klinis2 = "Nyeri Ulu Hati, ";
                    } else {
                        klinis2 = "";
                    }

                    if (rs6.getString("diare").equals("ya")) {
                        klinis3 = "Diare, ";
                    } else {
                        klinis3 = "";
                    }

                    if (rs6.getString("kesulitan_menelan").equals("ya")) {
                        klinis4 = "Kesulitan Menelan, ";
                    } else {
                        klinis4 = "";
                    }
                    
                    if (rs6.getString("oedema").equals("ya")) {
                        klinis5 = "Oedema, ";
                    } else {
                        klinis5 = "";
                    }

                    if (rs6.getString("konstipasi").equals("ya")) {
                        klinis6 = "Konstipasi, ";
                    } else {
                        klinis6 = "";
                    }

                    if (rs6.getString("anoreksia").equals("ya")) {
                        klinis7 = "Anoreksia, ";
                    } else {
                        klinis7 = "";
                    }

                    if (rs6.getString("gangguan_gigi_geligi").equals("ya")) {
                        klinis8 = "Gangguan Gigi Geligi, ";
                    } else {
                        klinis8 = "";
                    }

                    if (!rs6.getString("klinis_lainnya").equals("")) {
                        klinislain = "Lainnya : " + rs6.getString("klinis_lainnya");
                    } else {
                        klinislain = "";
                    }                    
                    cekKlinisGizi = klinis1 + klinis2 + klinis3 + klinis4 + klinis5 + klinis6 + klinis7 + klinis8 + klinislain;
                    
                    //riwayat gizi
                    if (rs6.getString("makan_lebih_3x").equals("ya")) {
                        riw1 = "Makan >= 3 x Sehari, ";
                    } else {
                        riw1 = "";
                    }

                    if (rs6.getString("makan_kurang_3x").equals("ya")) {
                        riw2 = "Makan <= 3 x Sehari, ";
                    } else {
                        riw2 = "";
                    }

                    if (!rs6.getString("riwayat_gizi_lainnya").equals("")) {
                        riwGiziLain = "Riwayat Gizi Lain : " + rs6.getString("riwayat_gizi_lainnya") + ", ";
                    } else {
                        riwGiziLain = "";
                    }

                    if (rs6.getString("alergi_makanan").equals("ya")) {
                        riw3 = "Alergi Makanan : " + rs6.getString("ket_alergi_makanan") + ", ";
                    } else {
                        riw3 = "";
                    }

                    if (rs6.getString("pantangan_makan").equals("ya")) {
                        riw4 = "Pantangan Makan : " + rs6.getString("ket_pantangan_makan") + "\n";
                    } else {
                        riw4 = "";
                    }
                    
                    if (rs6.getString("asupan_cukup").equals("ya")) {
                        riw5 = "Asupan Cukup & Tidak Ada Perubahan, ";
                    } else {
                        riw5 = "";
                    }

                    if (rs6.getString("asupan_menurun").equals("ya")) {
                        riw6 = "Asupan Menurun Tahap Ringan, ";
                    } else {
                        riw6 = "";
                    }

                    if (rs6.getString("asupan_rendah").equals("ya")) {
                        riw7 = "Asupan Rendah, Tetapi Ada Peningkatan, ";
                    } else {
                        riw7 = "";
                    }

                    if (rs6.getString("asupan_tidak_cukup").equals("ya")) {
                        riw8 = "Asupan Tidak Cukup & Menurun Tahap Berat, ";
                    } else {
                        riw8 = "";
                    }
                    cekRiwGizi = "\nRiwayat Gizi : Riwayat Makan SMRS :\n" + riw1 + riw2 + riwGiziLain + riw3 + riw4
                            + "\nAsupan Makan SMRS :\n" + riw5 + riw6 + riw7 + riw8 + "Hasil Recall Intake : " + rs6.getString("hasil_recall");
               
                    //diagnosa gizi
                    try {
                        ps7 = koneksi.prepareStatement("select k.* from detail_diagnosa_asuhan_gizi d "
                                + "inner join katalog_diagnosa_gizi k on k.kd_diagnosa_gizi=d.kd_diagnosa_gizi where "
                                + "d.no_rawat like ? order by k.kd_diagnosa_gizi");
                        try {
                            ps7.setString(1, "%" + TNoRw.getText().trim() + "%");
                            rs7 = ps7.executeQuery();
                            while (rs7.next()) {
                                //untuk diprin
                                if (diagnosaGizi.equals("")) {
                                    diagnosaGizi = rs7.getString("kd_diagnosa_gizi") + " " + rs7.getString("deskripsi_diagnosa");
                                } else {
                                    diagnosaGizi = diagnosaGizi + "\n" + rs7.getString("kd_diagnosa_gizi") + " " + rs7.getString("deskripsi_diagnosa");
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                        } finally {
                            if (rs7 != null) {
                                rs7.close();
                            }
                            if (ps7 != null) {
                                ps7.close();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                    
                    //intervensi
                    if (rs6.getString("protein").equals("Lainnya")) {
                        proteinGizi = "Lainnya : " + rs6.getString("protein_lain");
                    } else {
                        proteinGizi = rs6.getString("protein");
                    }

                    if (rs6.getString("lemak").equals("Lainnya")) {
                        lemakGizi = "Lainnya : " + rs6.getString("lemak_lain");
                    } else {
                        lemakGizi = rs6.getString("lemak");
                    }

                    if (rs6.getString("karbohidrat").equals("Lainnya")) {
                        karboGizi = "Lainnya : " + rs6.getString("karbohidrat_lain");
                    } else {
                        karboGizi = rs6.getString("karbohidrat");
                    }
                    
                    //rencana monev
                    if (rs6.getString("monev_asupan_makan").equals("ya")) {
                        ren1 = "Asupan Makan, ";
                    } else {
                        ren1 = "";
                    }

                    if (rs6.getString("monev_antropometri").equals("ya")) {
                        ren2 = "Antropometri, ";
                    } else {
                        ren2 = "";
                    }

                    if (rs6.getString("monev_biokimia").equals("ya")) {
                        ren3 = "Biokimia, ";
                    } else {
                        ren3 = "";
                    }

                    if (rs6.getString("monev_klinis").equals("ya")) {
                        ren4 = "Klinis, ";
                    } else {
                        ren4 = "";
                    }

                    if (rs6.getString("monev_lain").equals("ya")) {
                        ren5 = "Lain-lain";
                    } else {
                        ren5 = "";
                    }

                    if (ren1.equals("") && ren2.equals("") && ren3.equals("") && ren4.equals("") && ren5.equals("")) {
                        cekRenMonevGizi = "-";
                    } else {
                        cekRenMonevGizi = ren1 + ren2 + ren3 + ren4 + ren5;
                    }

                    //mempaste datanya
                    if (tujuanPaste.equals("hasil")) {
                        asuhanGiziHasil = "Tgl. Asuhan : " + rs6.getString("tgl") + "\nAntropometri :\n"
                                + " - Berat Badan : " + rs6.getString("bb") + " Kg.\n"
                                + " - TB : " + rs6.getString("tb") + " Cm.\n"
                                + " - IMT : " + rs6.getString("imt") + " Kg/Cm3\n"
                                + " - LILA : " + rs6.getString("lila") + " Cm.\n"
                                + " - BBI : " + rs6.getString("bbi") + " Kg.\n"
                                + " - Tinggi Lutut : " + rs6.getString("tinggi_lutut") + " Cm.\n"
                                + " - ULNA : " + rs6.getString("ulna") + " Cm.\n"
                                + " - TB est : " + rs6.getString("tb_est") + " Cm.\n"
                                + " - BB Koreksi : " + rs6.getString("tb_est") + " Kg.\n"
                                + cekSttsGizi + "\n"
                                + "Biokimia : " + rs6.getString("biokimia") + "\n\n"
                                + "Klinis / Fisik :\n"
                                + cekKlinisGizi + cekRiwGizi + "\n"
                                + "Riwayat Penyakit Personal : " + rs6.getString("riwayat_penyakit_personal") + "\n\n"
                                + "Diagnosa Gizi [P]:\n" + diagnosaGizi + "\n\n"
                                + "Berkaitan Dengan [E] : " + rs6.getString("berkaitan_dengan") + "\n\n"
                                + "Ditandai Dengan [S] : " + rs6.getString("ditandai_dengan") + "\n";
                        
                        if (THasil.getText().equals("")) {
                            THasil.setText(asuhanGiziHasil);
                        } else {
                            THasil.setText(THasil.getText() + "\n\n" + asuhanGiziHasil);
                        }
                        
                    } else if (tujuanPaste.equals("instruksi")) {
                        asuhanGiziInstruksi = "Tgl. Asuhan : " + rs6.getString("tgl") + "\n"
                                + "INTERVENSI :\n"
                                + "Bentuk Makanan : " + rs6.getString("bentuk_makanan") + ", "
                                + "Rute Makanan : " + rs6.getString("rute_makanan") + ", "
                                + "Jenis Diet : " + rs6.getString("jenis_diet") + "\n\n"
                                + "Metode Hitungan (" + rs6.getString("metode_hitungan") + ") :\n"
                                + "Kalori (KKal) : " + rs6.getString("kalori") + "\n"
                                + "Protein : " + proteinGizi + "\n"
                                + "Lemak : " + lemakGizi + "\n"
                                + "Karbohidrat : " + karboGizi + "\n"
                                + "Rencana Monev : " + cekRenMonevGizi + "\n";
                        
                        if (TInstruksi.getText().equals("")) {
                            TInstruksi.setText(asuhanGiziInstruksi);
                        } else {
                            TInstruksi.setText(TInstruksi.getText() + "\n\n" + asuhanGiziInstruksi);
                        }
                    }
                    
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
    }
    
    private void emptVariabelGizi() {
        asuhanGiziHasil = "";
        asuhanGiziInstruksi = "";
        cekSttsGizi = "";
        sttsgz1 = "";
        sttsgz2 = "";
        sttsgz3 = "";
        klinis1 = "";
        klinis2 = "";
        klinis3 = "";
        klinis4 = "";
        klinis5 = "";
        klinis6 = "";
        klinis7 = "";
        klinis8 = "";
        klinislain = "";
        cekKlinisGizi = "";
        riw1 = "";
        riw2 = "";
        riw3 = "";
        riw4 = "";
        riw5 = "";
        riw6 = "";
        riw7 = "";
        riw8 = "";
        cekRiwGizi = "";
        riwGiziLain = "";
        diagnosaGizi = "";
        proteinGizi = "";
        lemakGizi = "";
        karboGizi = "";
        ren1 = "";
        ren2 = "";
        ren3 = "";
        ren4 = "";
        ren5 = "";
        cekRenMonevGizi = "";
    }
    
    private void tampilMonevGizi() {
        Valid.tabelKosong(tabMode6);
        try {
            ps8 = koneksi.prepareStatement("SELECT m.*, p.no_rkm_medis, p.nm_pasien, date_format(m.tgl_monev,'%d-%m-%Y') tgl "
                    + "from monev_asuhan_gizi m inner join reg_periksa rp on rp.no_rawat=m.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis WHERE "
                    + "m.tgl_monev BETWEEN ? and ? and m.no_rawat like '" + TNoRw.getText() + "' order by m.tgl_monev desc"); 
            try {
                ps8.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps8.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                rs8 = ps8.executeQuery();
                while (rs8.next()) {
                    tabMode6.addRow(new String[]{
                        rs8.getString("no_rkm_medis"),
                        rs8.getString("nm_pasien"),
                        rs8.getString("tgl"),
                        rs8.getString("ruang_rawat"),
                        rs8.getString("perkembangan_fisik_klinis"),
                        rs8.getString("perkembangan_diet"),
                        rs8.getString("evaluasi_tindak_lanjut"),
                        rs8.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilMonevGizi() : " + e);
            } finally {
                if (rs8 != null) {
                    rs8.close();
                }
                if (ps8 != null) {
                    ps8.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void tampilPreviewCppt() {
        cekKonfirmasi = 0;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsPrev = koneksi.prepareStatement("SELECT DISTINCT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, IF(c.cek_jam='ya',concat(date_format(c.tgl_cppt,'%d-%m-%Y'),'<br/>',date_format(c.jam_cppt,'%H:%i'),' Wita'), "
                        + "date_format(c.tgl_cppt,'%d-%m-%Y')) tglcppt, c.bagian, "
                        + "ifnull(if(c.jenis_bagian='' or c.jenis_bagian='-','-',if(c.jenis_bagian='Dokter IGD' or c.jenis_bagian='DPJP' or c.jenis_bagian='DPJP (K)' or c.jenis_bagian='DPJP Raber',c.jenis_bagian,concat(c.jenis_bagian,' : ',c.jenis_ppa))),'-') bagian_cppt, "
                        + "replace(c.hasil_pemeriksaan,'<','&lt') hasil_pemeriksaan, "
                        + "concat(replace(c.instruksi_nakes,'<','&lt'),if(c.jenis_bagian='DPJP' or c.jenis_bagian='DPJP (K)' or c.jenis_bagian='DPJP Raber',concat('<br/><br/>(',pg1.nama,')'),if(c.jenis_bagian='PPA',concat('<br/><br/>(',pg2.nama,')'),''))) instruksi_nakes, "
                        + "concat('(', c.verifikasi,') - ',pg.nama) verif, "
                        + "if(c.serah_terima_cppt='ya',concat('<br/><br/>Tgl. ',date_format(c.tgl_cppt,'%d-%m-%Y'),', Jam : ',ifnull(date_format(c.jam_serah_terima,'%H:%i'),'00:00'),'<br/>','Menyerahkan :<br/>',pg3.nama),'') ptgsSerah, "
                        + "if(c.serah_terima_cppt='ya',concat('Menerima :<br/>',pg4.nama),'') ptgsTerima, c.tgl_cppt, c.jam_cppt, c.cppt_shift "
                        + "FROM cppt c INNER JOIN reg_periksa rp ON rp.no_rawat = c.no_rawat INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis "
                        + "INNER JOIN pegawai pg ON pg.nik = c.nip_dpjp LEFT JOIN pegawai pg1 on pg1.nik=c.nip_konsulen LEFT JOIN pegawai pg2 on pg2.nik=c.nip_ppa "
                        + "LEFT JOIN pegawai pg3 on pg3.nik=c.nip_petugas_serah LEFT JOIN pegawai pg4 on pg4.nik=c.nip_petugas_terima "
                        + "WHERE c.no_rawat = '" + TNoRw.getText() + "' and c.flag_hapus='tidak' ORDER BY c.tgl_cppt, c.jam_cppt").executeQuery();

                if (rsPrev.next()) {
                    htmlContent.append(
                            "<table width='100%' class='isi'>"
                            + "<thead>"
                            + "<tr class='isi'>"
                            + "<td width='20%' align='center' bgcolor='#f8fdf3'><b>TANGGAL<br/>JAM</b></td>"
                            + "<td width='50%' align='center' bgcolor='#f8fdf3'><b>BAGIAN</b></td>"
                            + "<td width='80%' align='center' bgcolor='#f8fdf3'><b>HASIL PEMERIKSAAN, ANALISA, RENCANA,<br/>PENATALAKSANAAN PASIEN</b></td>"
                            + "<td width='80%' align='center' bgcolor='#f8fdf3'><b>INSTRUKSI TENAGA KESEHATAN<br/>TERMASUK PASCA BEDAH/PROSEDUR</b></td>"
                            + "<td width='60%' align='center' bgcolor='#f8fdf3'><b>VERIFIKASI TANDA TANGAN DAN<br/>NAMA JELAS DPJP</b></td>"
                            + "</tr>"
                            + "</thead>"
                            + "<tbody>"
                    );
                    
                    rsPrev.beforeFirst();
                    while (rsPrev.next()) {
                        cekKonfirmasi = Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where no_rawat='" + TNoRw.getText() + "' "
                                + "and tgl_cppt='" + rsPrev.getString("tgl_cppt") + "' and jam_cppt='" + rsPrev.getString("jam_cppt") + "' "
                                + "and cppt_shift='" + rsPrev.getString("cppt_shift") + "'");
                        
                        if (cekKonfirmasi == 0) {
                            konfirmasi_terapi = "";
                        } else {
                            konfirmasiTerapiPreview(TNoRw.getText(), rsPrev.getString("tgl_cppt"), rsPrev.getString("jam_cppt"), rsPrev.getString("cppt_shift"));
                        }
                        
                        if (rsPrev.getString("bagian_cppt").contains("DPJP")
                                || rsPrev.getString("bagian_cppt").contains("DPJP (K)")
                                || rsPrev.getString("bagian_cppt").contains("DPJP Raber")) {
                            htmlContent.append(
                                    "<tr style='background-color: #d2e9e9' class='isi'>"
                                    + "<td width='20%' valign='top' align='center'>" + rsPrev.getString("tglcppt") + "</td>"
                                    + "<td width='50%' valign='top'>" + rsPrev.getString("bagian_cppt") + "</td>"
                                    + "<td width='80%' valign='top'>" + rsPrev.getString("hasil_pemeriksaan").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/></td>"
                                    + "<td width='80%' valign='top'>" + rsPrev.getString("instruksi_nakes").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/><br/>" + konfirmasi_terapi + "<br/></td>"
                                    + "<td width='60%' valign='top'>" + rsPrev.getString("verif") + "<br/><span style='color:CC0033'>" + rsPrev.getString("ptgsSerah") + "<br/><br/>" + rsPrev.getString("ptgsTerima") + "<br/></td>"
                                    + "</tr>"
                            );
                        } else {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td width='20%' valign='top' align='center'>" + rsPrev.getString("tglcppt") + "</td>"
                                    + "<td width='50%' valign='top'>" + rsPrev.getString("bagian_cppt") + "</td>"
                                    + "<td width='80%' valign='top'>" + rsPrev.getString("hasil_pemeriksaan").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/></td>"
                                    + "<td width='80%' valign='top'>" + rsPrev.getString("instruksi_nakes").replaceAll(":&lt", ": kurang dari ").replaceAll(" &lt", " kurang dari ").replaceAll("\n", "<br/>") + "<br/><br/>" + konfirmasi_terapi + "<br/></td>"
                                    + "<td width='60%' valign='top'>" + rsPrev.getString("verif") + "<br/><span style='color:CC0033'>" + rsPrev.getString("ptgsSerah") + "<br/><br/>" + rsPrev.getString("ptgsTerima") + "<br/></td>"
                                    + "</tr>"
                            );
                        }
                    }
                    htmlContent.append(
                            "</tbody>"
                            + "</table>"
                    );
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
                if (rsPrev != null) {
                    rsPrev.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    private void simpanTemporaryCppt(String nomor, String rwt) {
        whereNya = "";
        dataKonfir = "";
        Sequel.AutoComitFalse();
        Sequel.queryu("delete from temporary_cppt");
        try {
            //dengan sift petugas
            if (nomor.equals("1")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "' and c.cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'";
            } else if (nomor.equals("2")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "' and c.tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "' and c.cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'";
            } else if (nomor.equals("3")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "' and c.tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "and c.cppt_shift like '%" + cmbSiftPetugas.getSelectedItem().toString() + "%'";

            //tanpa sift petugas    
            } else if (nomor.equals("4")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "'";
            } else if (nomor.equals("5")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "' and c.tgl_cppt between '" + Valid.SetTgl(tglA.getSelectedItem() + "") + "' "
                        + "and '" + Valid.SetTgl(tglB.getSelectedItem() + "") + "'";
            } else if (nomor.equals("6")) {
                whereNya = "c.no_rawat = '" + TNoRw.getText() + "' AND c.STATUS='" + rwt + "' and c.tgl_cppt='" + Valid.SetTgl(tglA.getSelectedItem() + "") + "'";
            }

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
                    + "WHERE " + whereNya + " and c.flag_hapus='tidak' ORDER BY c.tgl_cppt, c.jam_cppt");
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
}
