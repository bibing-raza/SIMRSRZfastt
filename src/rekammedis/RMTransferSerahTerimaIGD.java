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

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
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
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import keuangan.DlgKamar;
import laporan.DlgHasilLIS;
import laporan.DlgPenyakit;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPeriksaRadiologi;

/**
 *
 * @author perpustakaan
 */
public final class RMTransferSerahTerimaIGD extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabModeResepObat, tabModeFarmasi, tabModeTglBeriObat, tabModeRiwItemObat;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    public DlgKamar kamar = new DlgKamar(null, false);
    private PreparedStatement ps, ps1, ps2, psFar, psTglBO, psRiwIO, pps1, pps2, pps3, pps4, pps5, pps6, pps7;
    private ResultSet rs, rs1, rs2, rsFar, rsTglBO, rsRiwIO, rrs1, rrs2, rrs3, rrs4, rrs5, rrs6, rrs7;
    private int i = 0, x = 0, pilihan = 0, cekHasilRad = 0;
    private String nip_dpjp = "", nip_konsulen1 = "", nip_konsulen2 = "", kd_kamar = "", 
            kd_kamar_pindah = "", resepDipilih = "", cekResep = "", tglResep = "", status_kmr = "",
            tglPemberianObat = "", nip_dokter = "", nip_serah = "", nip_terima = "", ekg = "", torak_foto = "", 
            fotoC = "", fotoG = "", fotoA = "", spiri = "", echo = "", usg = "", ct_scan = "", endos = "",
            ctg = "", penunjang_lain = "", alat_lain = "", infus = "", kateter = "", ngt = "", oksigen = "", 
            drain = "", kdItemrad = "", itemDipilih = "", tglRad = "", jamRad = "", lab = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMTransferSerahTerimaIGD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl. Lahir", "Nama DPJP", "dr. Konsulen 1", "dr. Konsulen 2", "Diagnosis", "Tgl. Masuk", "Ruang/Kamar",
            "Tgl. Jam Pindah", "Pindah Ke Ruang/Kamar", "Alasan R. Inap", "Keluhan", "nip_dpjp", "nip_konsulen1", "nip_konsulen2", "diagnosis", "tgl_masuk",
            "kd_kamar_msk", "tgl_jam_pindah", "kd_kamar_pindah", "alasan_ranap", "keluhan", "riwayat_penyakit", "riwayat_alergi", "gcs_e", "gcs_m", "gcs_v",
            "kesadaran", "td", "nadi", "suhu", "rr", "spo2", "skala_nyeri", "resiko_jatuh", "kriteria_transfer", "ekg", "thoraks_foto", "foto_cervikal",
            "foto_genu", "foto_abdomen", "spiritometri", "echo", "usg", "ct_scan", "ket_ct_scan", "endoskopi", "ket_endoskopi", "ctg", "ket_ctg", "lainnya",
            "ket_lainnya", "diagnosa", "tgl_infus", "tgl_kateter", "tgl_ngt", "tgl_oksigen", "tgl_drain", "lainya_alat", "tgl_alat_lain", "nm_alat_lain",
            "rekomendasi", "alasan_pindah_ruangan", "nm_pasien_keluarga", "nip_dokter_setuju", "nip_menyerahkan", "nip_menerima", "tgl_serah_terima_transfer",
            "infus", "kateter", "ngt", "oksigen", "drain", "lab"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbTransfer.setModel(tabMode);
        tbTransfer.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTransfer.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 76; i++) {
            TableColumn column = tbTransfer.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(250);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(80);                
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(150);
            } else if (i == 11) {
                column.setPreferredWidth(250);
            } else if (i == 12) {
                column.setPreferredWidth(250);
            } else if (i == 13) {
                column.setPreferredWidth(250);
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
            } else if (i == 64) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 65) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 66) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 67) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 68) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 69) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 70) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 71) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 72) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 73) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 74) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 75) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTransfer.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        tabMode1 = new DefaultTableModel(null, new Object[]{"No. RM", "Nama Pasien", "Data Template"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTemplate.setModel(tabMode1);
        tbTemplate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTemplate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbTemplate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(60);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            }
        }
        tbTemplate.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        TCari1.setDocument(new batasInput((int) 100).getKata(TCari1));
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMTransferSerahTerima")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nip_dpjp = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();                            
                            Tdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        btnDPJP.requestFocus();
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nip_konsulen1 = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();                            
                            Tdr_konsulen1.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        btnKonsulen1.requestFocus();
                    } else if (pilihan == 3) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nip_konsulen2 = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();                            
                            Tdr_konsulen2.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        Tdiagnosis.requestFocus();
                    } else if (pilihan == 4) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nip_dokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();                            
                            Tnm_dokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        btnDokter.requestFocus();
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
        
        kamar.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMTransferSerahTerima")) {
                    if (kamar.getTable().getSelectedRow() != -1) {
                        if (pilihan == 1) {
                            status_kmr = kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 6).toString();
                            if (!status_kmr.equals("KOSONG")) {
                                JOptionPane.showMessageDialog(null, "Silahkan pilih kamar yang kosong...!!!");
                                kd_kamar = "";
                                Tnm_kamar.setText("");
                            } else {
                                kd_kamar = kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString();
                                Tnm_kamar.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + kd_kamar + "'"));
                                tgl_pindah.requestFocus();
                            }
                        } else if (pilihan == 2) {
                            status_kmr = kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 6).toString();
                            if (!status_kmr.equals("KOSONG")) {
                                JOptionPane.showMessageDialog(null, "Silahkan pilih kamar yang kosong...!!!");
                                kd_kamar_pindah = "";
                                Tnm_kamar_pindah.setText("");
                            } else {
                                kd_kamar_pindah = kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString();
                                Tnm_kamar_pindah.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + kd_kamar_pindah + "'"));
                                Talasan_ranap.requestFocus();
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
        
        kamar.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("RMTransferSerahTerima")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        kamar.dispose();
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
                if (petugas.getTable().getSelectedRow() != -1) {
                    if (pilihan == 1) {
                        nip_serah = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tnm_petugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        btnPetugas1.requestFocus();
                    } else if (pilihan == 2) {
                        nip_terima = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        Tnm_petugas2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        btnPetugas2.requestFocus();
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
        MnSemuanya = new javax.swing.JMenuItem();
        MnDibatalkan = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanLab = new javax.swing.JMenuItem();
        MnHasilPemeriksaanRad = new javax.swing.JMenuItem();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        jLabel48 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel3 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        noIdObat = new widget.TextBox();
        TIdObat = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormTST = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel5 = new widget.Label();
        tgllahir = new widget.TextBox();
        jLabel8 = new widget.Label();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        Tdpjp = new widget.TextBox();
        Tdr_konsulen1 = new widget.TextBox();
        Tdr_konsulen2 = new widget.TextBox();
        btnDPJP = new widget.Button();
        btnKonsulen1 = new widget.Button();
        btnKonsulen2 = new widget.Button();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        Tnm_kamar = new widget.TextBox();
        btnKamar1 = new widget.Button();
        jLabel13 = new widget.Label();
        tgl_pindah = new widget.Tanggal();
        jLabel14 = new widget.Label();
        tgl_masuk = new widget.Tanggal();
        jLabel15 = new widget.Label();
        Tnm_kamar_pindah = new widget.TextBox();
        btnKamar2 = new widget.Button();
        jLabel16 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        Talasan_ranap = new widget.TextArea();
        jLabel17 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        Tkeluhan = new widget.TextArea();
        scrollPane11 = new widget.ScrollPane();
        Triw_penyakit = new widget.TextArea();
        jLabel20 = new widget.Label();
        jLabel22 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        Triw_alergi = new widget.TextArea();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        gcse = new widget.TextBox();
        jLabel26 = new widget.Label();
        gcsm = new widget.TextBox();
        jLabel27 = new widget.Label();
        gcsv = new widget.TextBox();
        jLabel28 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        td = new widget.TextBox();
        jLabel31 = new widget.Label();
        spo2 = new widget.TextBox();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        nadi = new widget.TextBox();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        nyeri = new widget.TextBox();
        jLabel36 = new widget.Label();
        suhu = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        rr = new widget.TextBox();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        cmbResiko = new widget.ComboBox();
        jLabel41 = new widget.Label();
        cmbKriteria = new widget.ComboBox();
        jLabel42 = new widget.Label();
        ChkEKG = new widget.CekBox();
        ChkThoraks = new widget.CekBox();
        ChkFotoC = new widget.CekBox();
        ChkFotoG = new widget.CekBox();
        ChkFotoA = new widget.CekBox();
        ChkSpiri = new widget.CekBox();
        ChkEcho = new widget.CekBox();
        ChkUSG = new widget.CekBox();
        ChkCTscan = new widget.CekBox();
        ChkEndos = new widget.CekBox();
        ChkCTG = new widget.CekBox();
        ChkLainya = new widget.CekBox();
        Tket_lain = new widget.TextBox();
        jLabel44 = new widget.Label();
        TDiagnosa = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        tgl_infus = new widget.Tanggal();
        tgl_kateter = new widget.Tanggal();
        tgl_ngt = new widget.Tanggal();
        tgl_oksigen = new widget.Tanggal();
        tgl_drain = new widget.Tanggal();
        Talat_lain = new widget.TextBox();
        tgl_alat_lain = new widget.Tanggal();
        jLabel57 = new widget.Label();
        scrollPane13 = new widget.ScrollPane();
        Trekomendasi = new widget.TextArea();
        jLabel58 = new widget.Label();
        Talasan_pindah = new widget.TextBox();
        ChkLain_alat = new widget.CekBox();
        jLabel59 = new widget.Label();
        jLabel60 = new widget.Label();
        Tnm_pasienKlg = new widget.TextBox();
        jLabel61 = new widget.Label();
        Tnm_dokter = new widget.TextBox();
        btnDokter = new widget.Button();
        jLabel62 = new widget.Label();
        Tnm_petugas1 = new widget.TextBox();
        btnPetugas1 = new widget.Button();
        jLabel63 = new widget.Label();
        Tnm_petugas2 = new widget.TextBox();
        btnPetugas2 = new widget.Button();
        Tket_ctg = new widget.TextBox();
        Tket_endos = new widget.TextBox();
        Tket_ctscan = new widget.TextBox();
        tgl_transfer = new widget.Tanggal();
        ChkDrain = new widget.CekBox();
        ChkOksigen = new widget.CekBox();
        ChkNGT = new widget.CekBox();
        ChkKateter = new widget.CekBox();
        ChkInfus = new widget.CekBox();
        scrollPane14 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        jLabel47 = new widget.Label();
        BtnDiagnosis = new widget.Button();
        BtnKeluhan = new widget.Button();
        BtnRiwAlergi = new widget.Button();
        BtnAlasan = new widget.Button();
        BtnRiwPenyakit = new widget.Button();
        BtnDiagnosa = new widget.Button();
        BtnRekom = new widget.Button();
        ChkLab = new widget.CekBox();
        internalFrame5 = new widget.InternalFrame();
        panelGlass13 = new widget.panelisi();
        jLabel54 = new widget.Label();
        DTPCariA = new widget.Tanggal();
        jLabel55 = new widget.Label();
        DTPCariB = new widget.Tanggal();
        jLabel56 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCariObat = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        panelGlass16 = new widget.panelisi();
        jLabel53 = new widget.Label();
        TResepObat = new widget.TextBox();
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
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbTransfer = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSemuanya.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSemuanya.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSemuanya.setText("Conteng Semua Item");
        MnSemuanya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSemuanya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSemuanya.setIconTextGap(5);
        MnSemuanya.setName("MnSemuanya"); // NOI18N
        MnSemuanya.setPreferredSize(new java.awt.Dimension(185, 26));
        MnSemuanya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSemuanyaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSemuanya);

        MnDibatalkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDibatalkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDibatalkan.setText("Hilangkan Semua Conteng");
        MnDibatalkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDibatalkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDibatalkan.setIconTextGap(5);
        MnDibatalkan.setName("MnDibatalkan"); // NOI18N
        MnDibatalkan.setPreferredSize(new java.awt.Dimension(185, 26));
        MnDibatalkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDibatalkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDibatalkan);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHasilPemeriksaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanLab.setText("Hasil Pemeriksaan Lab.");
        MnHasilPemeriksaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanLab.setIconTextGap(5);
        MnHasilPemeriksaanLab.setName("MnHasilPemeriksaanLab"); // NOI18N
        MnHasilPemeriksaanLab.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanLabActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHasilPemeriksaanLab);

        MnHasilPemeriksaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanRad.setText("Hasil Pemeriksaan Radiologi");
        MnHasilPemeriksaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanRad.setIconTextGap(5);
        MnHasilPemeriksaanRad.setName("MnHasilPemeriksaanRad"); // NOI18N
        MnHasilPemeriksaanRad.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanRadActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHasilPemeriksaanRad);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Transfer & Serah Terima Pasien IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi5.setBackground(new java.awt.Color(255, 150, 255));
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Key Word :");
        jLabel48.setName("jLabel48"); // NOI18N
        jLabel48.setPreferredSize(new java.awt.Dimension(70, 23));
        jLabel48.setRequestFocusEnabled(false);
        panelisi5.add(jLabel48);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelisi5.add(TCari2);

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
        panelisi5.add(BtnCari2);

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
        panelisi5.add(BtnCopas);

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
        panelisi5.add(BtnCloseIn1);

        internalFrame6.add(panelisi5, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel3.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel3.add(Scroll3);

        internalFrame6.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        noIdObat.setForeground(new java.awt.Color(0, 0, 0));
        noIdObat.setHighlighter(null);
        noIdObat.setName("noIdObat"); // NOI18N

        TIdObat.setEnabled(false);
        TIdObat.setHighlighter(null);
        TIdObat.setName("TIdObat"); // NOI18N
        TIdObat.setPreferredSize(new java.awt.Dimension(1, 1));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Transfer & Serah Terima Pasien IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        FormTST.setBorder(null);
        FormTST.setName("FormTST"); // NOI18N
        FormTST.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu2);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1186));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 130, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(136, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(332, 10, 225, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tgl. TST : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(558, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 130, 23);

        tgllahir.setEditable(false);
        tgllahir.setBackground(new java.awt.Color(245, 250, 240));
        tgllahir.setForeground(new java.awt.Color(0, 0, 0));
        tgllahir.setName("tgllahir"); // NOI18N
        FormInput.add(tgllahir);
        tgllahir.setBounds(136, 40, 100, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("DPJP :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(240, 40, 110, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Dokter Konsulen 1 :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(240, 70, 110, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Dokter Konsulen 2 :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(240, 100, 110, 23);

        Tdpjp.setEditable(false);
        Tdpjp.setBackground(new java.awt.Color(245, 250, 240));
        Tdpjp.setForeground(new java.awt.Color(0, 0, 0));
        Tdpjp.setName("Tdpjp"); // NOI18N
        FormInput.add(Tdpjp);
        Tdpjp.setBounds(355, 40, 380, 23);

        Tdr_konsulen1.setEditable(false);
        Tdr_konsulen1.setBackground(new java.awt.Color(245, 250, 240));
        Tdr_konsulen1.setForeground(new java.awt.Color(0, 0, 0));
        Tdr_konsulen1.setName("Tdr_konsulen1"); // NOI18N
        FormInput.add(Tdr_konsulen1);
        Tdr_konsulen1.setBounds(355, 70, 380, 23);

        Tdr_konsulen2.setEditable(false);
        Tdr_konsulen2.setBackground(new java.awt.Color(245, 250, 240));
        Tdr_konsulen2.setForeground(new java.awt.Color(0, 0, 0));
        Tdr_konsulen2.setName("Tdr_konsulen2"); // NOI18N
        FormInput.add(Tdr_konsulen2);
        Tdr_konsulen2.setBounds(355, 100, 380, 23);

        btnDPJP.setForeground(new java.awt.Color(0, 0, 0));
        btnDPJP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDPJP.setMnemonic('1');
        btnDPJP.setToolTipText("Alt+1");
        btnDPJP.setName("btnDPJP"); // NOI18N
        btnDPJP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDPJPActionPerformed(evt);
            }
        });
        FormInput.add(btnDPJP);
        btnDPJP.setBounds(735, 40, 28, 23);

        btnKonsulen1.setForeground(new java.awt.Color(0, 0, 0));
        btnKonsulen1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKonsulen1.setMnemonic('1');
        btnKonsulen1.setToolTipText("Alt+1");
        btnKonsulen1.setName("btnKonsulen1"); // NOI18N
        btnKonsulen1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonsulen1ActionPerformed(evt);
            }
        });
        FormInput.add(btnKonsulen1);
        btnKonsulen1.setBounds(735, 70, 28, 23);

        btnKonsulen2.setForeground(new java.awt.Color(0, 0, 0));
        btnKonsulen2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKonsulen2.setMnemonic('1');
        btnKonsulen2.setToolTipText("Alt+1");
        btnKonsulen2.setName("btnKonsulen2"); // NOI18N
        btnKonsulen2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKonsulen2ActionPerformed(evt);
            }
        });
        FormInput.add(btnKonsulen2);
        btnKonsulen2.setBounds(735, 100, 28, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosis :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 130, 130, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Ruang / Kamar :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 207, 130, 23);

        Tnm_kamar.setEditable(false);
        Tnm_kamar.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_kamar.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_kamar.setName("Tnm_kamar"); // NOI18N
        FormInput.add(Tnm_kamar);
        Tnm_kamar.setBounds(136, 207, 599, 23);

        btnKamar1.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar1.setMnemonic('1');
        btnKamar1.setToolTipText("Alt+1");
        btnKamar1.setName("btnKamar1"); // NOI18N
        btnKamar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamar1ActionPerformed(evt);
            }
        });
        FormInput.add(btnKamar1);
        btnKamar1.setBounds(735, 207, 28, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Tgl. / Jam Pindah :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 237, 130, 23);

        tgl_pindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023 10:48:26" }));
        tgl_pindah.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tgl_pindah.setName("tgl_pindah"); // NOI18N
        tgl_pindah.setOpaque(false);
        FormInput.add(tgl_pindah);
        tgl_pindah.setBounds(136, 237, 135, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. Masuk :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 70, 130, 23);

        tgl_masuk.setEditable(false);
        tgl_masuk.setDisplayFormat("dd-MM-yyyy");
        tgl_masuk.setName("tgl_masuk"); // NOI18N
        tgl_masuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_masukKeyPressed(evt);
            }
        });
        FormInput.add(tgl_masuk);
        tgl_masuk.setBounds(136, 70, 100, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pindah ke Ruang/Kamar :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(270, 237, 135, 23);

        Tnm_kamar_pindah.setEditable(false);
        Tnm_kamar_pindah.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_kamar_pindah.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_kamar_pindah.setName("Tnm_kamar_pindah"); // NOI18N
        FormInput.add(Tnm_kamar_pindah);
        Tnm_kamar_pindah.setBounds(412, 237, 324, 23);

        btnKamar2.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar2.setMnemonic('1');
        btnKamar2.setToolTipText("Alt+1");
        btnKamar2.setName("btnKamar2"); // NOI18N
        btnKamar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamar2ActionPerformed(evt);
            }
        });
        FormInput.add(btnKamar2);
        btnKamar2.setBounds(735, 237, 28, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alasan Rawat Inap :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 267, 130, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        Talasan_ranap.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Talasan_ranap.setColumns(20);
        Talasan_ranap.setRows(5);
        Talasan_ranap.setName("Talasan_ranap"); // NOI18N
        Talasan_ranap.setPreferredSize(new java.awt.Dimension(162, 200));
        Talasan_ranap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Talasan_ranapKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(Talasan_ranap);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(136, 267, 260, 100);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Keluhan :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(395, 267, 60, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        Tkeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeluhan.setColumns(20);
        Tkeluhan.setRows(5);
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.setPreferredSize(new java.awt.Dimension(162, 200));
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(Tkeluhan);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(460, 267, 300, 100);

        scrollPane11.setName("scrollPane11"); // NOI18N

        Triw_penyakit.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triw_penyakit.setColumns(20);
        Triw_penyakit.setRows(5);
        Triw_penyakit.setName("Triw_penyakit"); // NOI18N
        Triw_penyakit.setPreferredSize(new java.awt.Dimension(162, 200));
        Triw_penyakit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triw_penyakitKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Triw_penyakit);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(136, 374, 260, 100);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Riwayat Penyakit :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 374, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Riwayat Alergi :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(395, 374, 90, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        Triw_alergi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Triw_alergi.setColumns(20);
        Triw_alergi.setRows(5);
        Triw_alergi.setName("Triw_alergi"); // NOI18N
        Triw_alergi.setPreferredSize(new java.awt.Dimension(162, 200));
        Triw_alergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Triw_alergiKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Triw_alergi);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(490, 374, 270, 100);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("PEMERIKSAAN FISIK");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 482, 130, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Keadaan Umum :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 497, 130, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("GCS : E :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(136, 497, 50, 23);

        gcse.setForeground(new java.awt.Color(0, 0, 0));
        gcse.setName("gcse"); // NOI18N
        gcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gcseKeyPressed(evt);
            }
        });
        FormInput.add(gcse);
        gcse.setBounds(190, 497, 70, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("M :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(260, 497, 25, 23);

        gcsm.setForeground(new java.awt.Color(0, 0, 0));
        gcsm.setName("gcsm"); // NOI18N
        gcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gcsmKeyPressed(evt);
            }
        });
        FormInput.add(gcsm);
        gcsm.setBounds(290, 497, 70, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("V :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(360, 497, 25, 23);

        gcsv.setForeground(new java.awt.Color(0, 0, 0));
        gcsv.setName("gcsv"); // NOI18N
        gcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                gcsvKeyPressed(evt);
            }
        });
        FormInput.add(gcsv);
        gcsv.setBounds(390, 497, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Kesadaran :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(460, 497, 75, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "CM", "Apatis", "Somnolen", "Sopor", "Coma" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        cmbKesadaran.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbKesadaran);
        cmbKesadaran.setBounds(540, 497, 80, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Pemeriksaan Tanda Vital :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(0, 527, 130, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tensi :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(136, 527, 50, 23);

        td.setForeground(new java.awt.Color(0, 0, 0));
        td.setName("td"); // NOI18N
        td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdKeyPressed(evt);
            }
        });
        FormInput.add(td);
        td.setBounds(190, 527, 70, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("SPO2 :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(136, 557, 50, 23);

        spo2.setForeground(new java.awt.Color(0, 0, 0));
        spo2.setName("spo2"); // NOI18N
        spo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                spo2KeyPressed(evt);
            }
        });
        FormInput.add(spo2);
        spo2.setBounds(190, 557, 70, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("mmHg");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(265, 527, 40, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Nadi :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(310, 527, 30, 23);

        nadi.setForeground(new java.awt.Color(0, 0, 0));
        nadi.setName("nadi"); // NOI18N
        nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nadiKeyPressed(evt);
            }
        });
        FormInput.add(nadi);
        nadi.setBounds(345, 527, 70, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("x/menit");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(420, 527, 40, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Skala Nyeri :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(270, 557, 70, 23);

        nyeri.setForeground(new java.awt.Color(0, 0, 0));
        nyeri.setName("nyeri"); // NOI18N
        nyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nyeriKeyPressed(evt);
            }
        });
        FormInput.add(nyeri);
        nyeri.setBounds(345, 557, 410, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Suhu :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(465, 527, 40, 23);

        suhu.setForeground(new java.awt.Color(0, 0, 0));
        suhu.setName("suhu"); // NOI18N
        suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suhuKeyPressed(evt);
            }
        });
        FormInput.add(suhu);
        suhu.setBounds(510, 527, 70, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("°C");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(585, 527, 20, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("RR :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(605, 527, 30, 23);

        rr.setForeground(new java.awt.Color(0, 0, 0));
        rr.setName("rr"); // NOI18N
        rr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                rrKeyPressed(evt);
            }
        });
        FormInput.add(rr);
        rr.setBounds(640, 527, 70, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("x/menit");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(715, 527, 40, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Skore Resiko Jatuh :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 587, 130, 23);

        cmbResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Resiko Tinggi", "Resiko Sedang", "Resiko Rendah" }));
        cmbResiko.setName("cmbResiko"); // NOI18N
        cmbResiko.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResikoActionPerformed(evt);
            }
        });
        FormInput.add(cmbResiko);
        cmbResiko.setBounds(136, 587, 110, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Kriteria Transfer :");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(245, 587, 95, 23);

        cmbKriteria.setForeground(new java.awt.Color(0, 0, 0));
        cmbKriteria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "0", "1", "2", "3" }));
        cmbKriteria.setName("cmbKriteria"); // NOI18N
        cmbKriteria.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbKriteria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKriteriaActionPerformed(evt);
            }
        });
        FormInput.add(cmbKriteria);
        cmbKriteria.setBounds(345, 587, 45, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("PEMERIKSAAN PENUNJANG YANG SUDAH DILAKUKAN");
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 617, 310, 23);

        ChkEKG.setBackground(new java.awt.Color(255, 255, 250));
        ChkEKG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEKG.setForeground(new java.awt.Color(0, 0, 0));
        ChkEKG.setText("EKG");
        ChkEKG.setBorderPainted(true);
        ChkEKG.setBorderPaintedFlat(true);
        ChkEKG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEKG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEKG.setName("ChkEKG"); // NOI18N
        ChkEKG.setOpaque(false);
        ChkEKG.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkEKG);
        ChkEKG.setBounds(20, 667, 90, 23);

        ChkThoraks.setBackground(new java.awt.Color(255, 255, 250));
        ChkThoraks.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkThoraks.setForeground(new java.awt.Color(0, 0, 0));
        ChkThoraks.setText("Toraks Foto");
        ChkThoraks.setBorderPainted(true);
        ChkThoraks.setBorderPaintedFlat(true);
        ChkThoraks.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkThoraks.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkThoraks.setName("ChkThoraks"); // NOI18N
        ChkThoraks.setOpaque(false);
        ChkThoraks.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkThoraks);
        ChkThoraks.setBounds(20, 697, 90, 23);

        ChkFotoC.setBackground(new java.awt.Color(255, 255, 250));
        ChkFotoC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFotoC.setForeground(new java.awt.Color(0, 0, 0));
        ChkFotoC.setText("Foto Cervikal/Vertebrata");
        ChkFotoC.setBorderPainted(true);
        ChkFotoC.setBorderPaintedFlat(true);
        ChkFotoC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFotoC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFotoC.setName("ChkFotoC"); // NOI18N
        ChkFotoC.setOpaque(false);
        ChkFotoC.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkFotoC);
        ChkFotoC.setBounds(20, 727, 140, 23);

        ChkFotoG.setBackground(new java.awt.Color(255, 255, 250));
        ChkFotoG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFotoG.setForeground(new java.awt.Color(0, 0, 0));
        ChkFotoG.setText("Foto Genu/Fen");
        ChkFotoG.setBorderPainted(true);
        ChkFotoG.setBorderPaintedFlat(true);
        ChkFotoG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFotoG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFotoG.setName("ChkFotoG"); // NOI18N
        ChkFotoG.setOpaque(false);
        ChkFotoG.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkFotoG);
        ChkFotoG.setBounds(20, 757, 140, 23);

        ChkFotoA.setBackground(new java.awt.Color(255, 255, 250));
        ChkFotoA.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFotoA.setForeground(new java.awt.Color(0, 0, 0));
        ChkFotoA.setText("Foto Abdomen");
        ChkFotoA.setBorderPainted(true);
        ChkFotoA.setBorderPaintedFlat(true);
        ChkFotoA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFotoA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFotoA.setName("ChkFotoA"); // NOI18N
        ChkFotoA.setOpaque(false);
        ChkFotoA.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkFotoA);
        ChkFotoA.setBounds(175, 667, 100, 23);

        ChkSpiri.setBackground(new java.awt.Color(255, 255, 250));
        ChkSpiri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSpiri.setForeground(new java.awt.Color(0, 0, 0));
        ChkSpiri.setText("Spiritometri");
        ChkSpiri.setBorderPainted(true);
        ChkSpiri.setBorderPaintedFlat(true);
        ChkSpiri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSpiri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSpiri.setName("ChkSpiri"); // NOI18N
        ChkSpiri.setOpaque(false);
        ChkSpiri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSpiri);
        ChkSpiri.setBounds(175, 697, 100, 23);

        ChkEcho.setBackground(new java.awt.Color(255, 255, 250));
        ChkEcho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEcho.setForeground(new java.awt.Color(0, 0, 0));
        ChkEcho.setText("Echo/Treadmill");
        ChkEcho.setBorderPainted(true);
        ChkEcho.setBorderPaintedFlat(true);
        ChkEcho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEcho.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEcho.setName("ChkEcho"); // NOI18N
        ChkEcho.setOpaque(false);
        ChkEcho.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkEcho);
        ChkEcho.setBounds(175, 727, 100, 23);

        ChkUSG.setBackground(new java.awt.Color(255, 255, 250));
        ChkUSG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkUSG.setForeground(new java.awt.Color(0, 0, 0));
        ChkUSG.setText("USG");
        ChkUSG.setBorderPainted(true);
        ChkUSG.setBorderPaintedFlat(true);
        ChkUSG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkUSG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkUSG.setName("ChkUSG"); // NOI18N
        ChkUSG.setOpaque(false);
        ChkUSG.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkUSG);
        ChkUSG.setBounds(175, 757, 100, 23);

        ChkCTscan.setBackground(new java.awt.Color(255, 255, 250));
        ChkCTscan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCTscan.setForeground(new java.awt.Color(0, 0, 0));
        ChkCTscan.setText("CT Scan");
        ChkCTscan.setBorderPainted(true);
        ChkCTscan.setBorderPaintedFlat(true);
        ChkCTscan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCTscan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCTscan.setName("ChkCTscan"); // NOI18N
        ChkCTscan.setOpaque(false);
        ChkCTscan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkCTscan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCTscanActionPerformed(evt);
            }
        });
        FormInput.add(ChkCTscan);
        ChkCTscan.setBounds(285, 667, 70, 23);

        ChkEndos.setBackground(new java.awt.Color(255, 255, 250));
        ChkEndos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEndos.setForeground(new java.awt.Color(0, 0, 0));
        ChkEndos.setText("Endoskopi");
        ChkEndos.setBorderPainted(true);
        ChkEndos.setBorderPaintedFlat(true);
        ChkEndos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEndos.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEndos.setName("ChkEndos"); // NOI18N
        ChkEndos.setOpaque(false);
        ChkEndos.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkEndos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkEndosActionPerformed(evt);
            }
        });
        FormInput.add(ChkEndos);
        ChkEndos.setBounds(285, 697, 70, 23);

        ChkCTG.setBackground(new java.awt.Color(255, 255, 250));
        ChkCTG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCTG.setForeground(new java.awt.Color(0, 0, 0));
        ChkCTG.setText("CTG");
        ChkCTG.setBorderPainted(true);
        ChkCTG.setBorderPaintedFlat(true);
        ChkCTG.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCTG.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCTG.setName("ChkCTG"); // NOI18N
        ChkCTG.setOpaque(false);
        ChkCTG.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkCTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkCTGActionPerformed(evt);
            }
        });
        FormInput.add(ChkCTG);
        ChkCTG.setBounds(285, 727, 70, 23);

        ChkLainya.setBackground(new java.awt.Color(255, 255, 250));
        ChkLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLainya.setForeground(new java.awt.Color(0, 0, 0));
        ChkLainya.setText("Lainnya");
        ChkLainya.setBorderPainted(true);
        ChkLainya.setBorderPaintedFlat(true);
        ChkLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLainya.setName("ChkLainya"); // NOI18N
        ChkLainya.setOpaque(false);
        ChkLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLainyaActionPerformed(evt);
            }
        });
        FormInput.add(ChkLainya);
        ChkLainya.setBounds(285, 757, 70, 23);

        Tket_lain.setBackground(new java.awt.Color(245, 250, 240));
        Tket_lain.setForeground(new java.awt.Color(0, 0, 0));
        Tket_lain.setName("Tket_lain"); // NOI18N
        Tket_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_lainKeyPressed(evt);
            }
        });
        FormInput.add(Tket_lain);
        Tket_lain.setBounds(362, 757, 400, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Diagnosa :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 787, 130, 23);

        TDiagnosa.setBackground(new java.awt.Color(245, 250, 240));
        TDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        TDiagnosa.setName("TDiagnosa"); // NOI18N
        TDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(TDiagnosa);
        TDiagnosa.setBounds(136, 787, 626, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("TINDAKAN MEDIS YANG SUDAH DILAKUKAN");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 817, 260, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Alat Bantu Yang Masih Terpasang :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 832, 190, 23);

        tgl_infus.setEditable(false);
        tgl_infus.setDisplayFormat("dd-MM-yyyy");
        tgl_infus.setName("tgl_infus"); // NOI18N
        tgl_infus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_infusKeyPressed(evt);
            }
        });
        FormInput.add(tgl_infus);
        tgl_infus.setBounds(136, 854, 100, 23);

        tgl_kateter.setEditable(false);
        tgl_kateter.setDisplayFormat("dd-MM-yyyy");
        tgl_kateter.setName("tgl_kateter"); // NOI18N
        tgl_kateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_kateterKeyPressed(evt);
            }
        });
        FormInput.add(tgl_kateter);
        tgl_kateter.setBounds(136, 884, 100, 23);

        tgl_ngt.setEditable(false);
        tgl_ngt.setDisplayFormat("dd-MM-yyyy");
        tgl_ngt.setName("tgl_ngt"); // NOI18N
        tgl_ngt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_ngtKeyPressed(evt);
            }
        });
        FormInput.add(tgl_ngt);
        tgl_ngt.setBounds(136, 914, 100, 23);

        tgl_oksigen.setEditable(false);
        tgl_oksigen.setDisplayFormat("dd-MM-yyyy");
        tgl_oksigen.setName("tgl_oksigen"); // NOI18N
        tgl_oksigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_oksigenKeyPressed(evt);
            }
        });
        FormInput.add(tgl_oksigen);
        tgl_oksigen.setBounds(320, 854, 100, 23);

        tgl_drain.setEditable(false);
        tgl_drain.setDisplayFormat("dd-MM-yyyy");
        tgl_drain.setName("tgl_drain"); // NOI18N
        tgl_drain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_drainKeyPressed(evt);
            }
        });
        FormInput.add(tgl_drain);
        tgl_drain.setBounds(320, 884, 100, 23);

        Talat_lain.setBackground(new java.awt.Color(245, 250, 240));
        Talat_lain.setForeground(new java.awt.Color(0, 0, 0));
        Talat_lain.setName("Talat_lain"); // NOI18N
        Talat_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Talat_lainKeyPressed(evt);
            }
        });
        FormInput.add(Talat_lain);
        Talat_lain.setBounds(320, 914, 335, 23);

        tgl_alat_lain.setEditable(false);
        tgl_alat_lain.setDisplayFormat("dd-MM-yyyy");
        tgl_alat_lain.setName("tgl_alat_lain"); // NOI18N
        tgl_alat_lain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_alat_lainKeyPressed(evt);
            }
        });
        FormInput.add(tgl_alat_lain);
        tgl_alat_lain.setBounds(660, 914, 100, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Rekomendasi :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 944, 130, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Trekomendasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Trekomendasi.setColumns(20);
        Trekomendasi.setRows(5);
        Trekomendasi.setName("Trekomendasi"); // NOI18N
        Trekomendasi.setPreferredSize(new java.awt.Dimension(162, 200));
        Trekomendasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrekomendasiKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Trekomendasi);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(136, 944, 630, 80);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Alasan Pindah Rg. :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 1030, 130, 23);

        Talasan_pindah.setBackground(new java.awt.Color(245, 250, 240));
        Talasan_pindah.setForeground(new java.awt.Color(0, 0, 0));
        Talasan_pindah.setName("Talasan_pindah"); // NOI18N
        Talasan_pindah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Talasan_pindahKeyPressed(evt);
            }
        });
        FormInput.add(Talasan_pindah);
        Talasan_pindah.setBounds(136, 1030, 626, 23);

        ChkLain_alat.setBackground(new java.awt.Color(255, 255, 250));
        ChkLain_alat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLain_alat.setForeground(new java.awt.Color(0, 0, 0));
        ChkLain_alat.setText("Lainnya :");
        ChkLain_alat.setBorderPainted(true);
        ChkLain_alat.setBorderPaintedFlat(true);
        ChkLain_alat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkLain_alat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLain_alat.setName("ChkLain_alat"); // NOI18N
        ChkLain_alat.setOpaque(false);
        ChkLain_alat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkLain_alat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkLain_alatActionPerformed(evt);
            }
        });
        FormInput.add(ChkLain_alat);
        ChkLain_alat.setBounds(236, 914, 80, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Disetujui :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 1060, 130, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Nama Pasien/Keluarga :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(136, 1060, 125, 23);

        Tnm_pasienKlg.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_pasienKlg.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_pasienKlg.setName("Tnm_pasienKlg"); // NOI18N
        Tnm_pasienKlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tnm_pasienKlgKeyPressed(evt);
            }
        });
        FormInput.add(Tnm_pasienKlg);
        Tnm_pasienKlg.setBounds(266, 1060, 496, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Nama Dokter :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(136, 1090, 125, 23);

        Tnm_dokter.setEditable(false);
        Tnm_dokter.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_dokter.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_dokter.setName("Tnm_dokter"); // NOI18N
        FormInput.add(Tnm_dokter);
        Tnm_dokter.setBounds(266, 1090, 470, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('1');
        btnDokter.setToolTipText("Alt+1");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(735, 1090, 28, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Yang Menyerahkan :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(136, 1120, 125, 23);

        Tnm_petugas1.setEditable(false);
        Tnm_petugas1.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_petugas1.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_petugas1.setName("Tnm_petugas1"); // NOI18N
        FormInput.add(Tnm_petugas1);
        Tnm_petugas1.setBounds(266, 1120, 470, 23);

        btnPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas1.setMnemonic('1');
        btnPetugas1.setToolTipText("Alt+1");
        btnPetugas1.setName("btnPetugas1"); // NOI18N
        btnPetugas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugas1ActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas1);
        btnPetugas1.setBounds(735, 1120, 28, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Yang Menerima :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(136, 1150, 125, 23);

        Tnm_petugas2.setEditable(false);
        Tnm_petugas2.setBackground(new java.awt.Color(245, 250, 240));
        Tnm_petugas2.setForeground(new java.awt.Color(0, 0, 0));
        Tnm_petugas2.setName("Tnm_petugas2"); // NOI18N
        FormInput.add(Tnm_petugas2);
        Tnm_petugas2.setBounds(266, 1150, 470, 23);

        btnPetugas2.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas2.setMnemonic('1');
        btnPetugas2.setToolTipText("Alt+1");
        btnPetugas2.setName("btnPetugas2"); // NOI18N
        btnPetugas2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugas2ActionPerformed(evt);
            }
        });
        FormInput.add(btnPetugas2);
        btnPetugas2.setBounds(735, 1150, 28, 23);

        Tket_ctg.setBackground(new java.awt.Color(245, 250, 240));
        Tket_ctg.setForeground(new java.awt.Color(0, 0, 0));
        Tket_ctg.setName("Tket_ctg"); // NOI18N
        Tket_ctg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_ctgKeyPressed(evt);
            }
        });
        FormInput.add(Tket_ctg);
        Tket_ctg.setBounds(362, 727, 400, 23);

        Tket_endos.setBackground(new java.awt.Color(245, 250, 240));
        Tket_endos.setForeground(new java.awt.Color(0, 0, 0));
        Tket_endos.setName("Tket_endos"); // NOI18N
        Tket_endos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_endosKeyPressed(evt);
            }
        });
        FormInput.add(Tket_endos);
        Tket_endos.setBounds(362, 697, 400, 23);

        Tket_ctscan.setBackground(new java.awt.Color(245, 250, 240));
        Tket_ctscan.setForeground(new java.awt.Color(0, 0, 0));
        Tket_ctscan.setName("Tket_ctscan"); // NOI18N
        Tket_ctscan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_ctscanKeyPressed(evt);
            }
        });
        FormInput.add(Tket_ctscan);
        Tket_ctscan.setBounds(362, 667, 400, 23);

        tgl_transfer.setEditable(false);
        tgl_transfer.setDisplayFormat("dd-MM-yyyy");
        tgl_transfer.setName("tgl_transfer"); // NOI18N
        tgl_transfer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl_transferKeyPressed(evt);
            }
        });
        FormInput.add(tgl_transfer);
        tgl_transfer.setBounds(630, 10, 100, 23);

        ChkDrain.setBackground(new java.awt.Color(255, 255, 250));
        ChkDrain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDrain.setForeground(new java.awt.Color(0, 0, 0));
        ChkDrain.setText("Drain :");
        ChkDrain.setBorderPainted(true);
        ChkDrain.setBorderPaintedFlat(true);
        ChkDrain.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkDrain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDrain.setName("ChkDrain"); // NOI18N
        ChkDrain.setOpaque(false);
        ChkDrain.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkDrain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkDrainActionPerformed(evt);
            }
        });
        FormInput.add(ChkDrain);
        ChkDrain.setBounds(236, 884, 80, 23);

        ChkOksigen.setBackground(new java.awt.Color(255, 255, 250));
        ChkOksigen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkOksigen.setForeground(new java.awt.Color(0, 0, 0));
        ChkOksigen.setText("Oksigen :");
        ChkOksigen.setBorderPainted(true);
        ChkOksigen.setBorderPaintedFlat(true);
        ChkOksigen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkOksigen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkOksigen.setName("ChkOksigen"); // NOI18N
        ChkOksigen.setOpaque(false);
        ChkOksigen.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkOksigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkOksigenActionPerformed(evt);
            }
        });
        FormInput.add(ChkOksigen);
        ChkOksigen.setBounds(236, 854, 80, 23);

        ChkNGT.setBackground(new java.awt.Color(255, 255, 250));
        ChkNGT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNGT.setForeground(new java.awt.Color(0, 0, 0));
        ChkNGT.setText("NGT :");
        ChkNGT.setBorderPainted(true);
        ChkNGT.setBorderPaintedFlat(true);
        ChkNGT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkNGT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNGT.setName("ChkNGT"); // NOI18N
        ChkNGT.setOpaque(false);
        ChkNGT.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkNGT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkNGTActionPerformed(evt);
            }
        });
        FormInput.add(ChkNGT);
        ChkNGT.setBounds(0, 914, 130, 23);

        ChkKateter.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateter.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateter.setText("Kateter Urine :");
        ChkKateter.setBorderPainted(true);
        ChkKateter.setBorderPaintedFlat(true);
        ChkKateter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkKateter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateter.setName("ChkKateter"); // NOI18N
        ChkKateter.setOpaque(false);
        ChkKateter.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkKateter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkKateterActionPerformed(evt);
            }
        });
        FormInput.add(ChkKateter);
        ChkKateter.setBounds(0, 884, 130, 23);

        ChkInfus.setBackground(new java.awt.Color(255, 255, 250));
        ChkInfus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkInfus.setForeground(new java.awt.Color(0, 0, 0));
        ChkInfus.setText("Infus/Transfusi Set :");
        ChkInfus.setBorderPainted(true);
        ChkInfus.setBorderPaintedFlat(true);
        ChkInfus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInfus.setName("ChkInfus"); // NOI18N
        ChkInfus.setOpaque(false);
        ChkInfus.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkInfus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInfusActionPerformed(evt);
            }
        });
        FormInput.add(ChkInfus);
        ChkInfus.setBounds(0, 854, 130, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tdiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(162, 200));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tdiagnosis);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(136, 130, 626, 70);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("(Klik kanan pada halaman ini untuk melihat hasil pemeriksaan penunjang)");
        jLabel47.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(320, 617, 380, 23);

        BtnDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosis.setMnemonic('2');
        BtnDiagnosis.setText("Template");
        BtnDiagnosis.setToolTipText("Alt+2");
        BtnDiagnosis.setName("BtnDiagnosis"); // NOI18N
        BtnDiagnosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosis);
        BtnDiagnosis.setBounds(770, 130, 100, 23);

        BtnKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKeluhan.setMnemonic('2');
        BtnKeluhan.setText("Template");
        BtnKeluhan.setToolTipText("Alt+2");
        BtnKeluhan.setName("BtnKeluhan"); // NOI18N
        BtnKeluhan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluhanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKeluhan);
        BtnKeluhan.setBounds(770, 267, 100, 23);

        BtnRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwAlergi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwAlergi.setMnemonic('2');
        BtnRiwAlergi.setText("Template");
        BtnRiwAlergi.setToolTipText("Alt+2");
        BtnRiwAlergi.setName("BtnRiwAlergi"); // NOI18N
        BtnRiwAlergi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwAlergiActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwAlergi);
        BtnRiwAlergi.setBounds(770, 374, 100, 23);

        BtnAlasan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlasan.setMnemonic('2');
        BtnAlasan.setText("Template");
        BtnAlasan.setToolTipText("Alt+2");
        BtnAlasan.setName("BtnAlasan"); // NOI18N
        BtnAlasan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlasanActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlasan);
        BtnAlasan.setBounds(30, 287, 100, 23);

        BtnRiwPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        BtnRiwPenyakit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRiwPenyakit.setMnemonic('2');
        BtnRiwPenyakit.setText("Template");
        BtnRiwPenyakit.setToolTipText("Alt+2");
        BtnRiwPenyakit.setName("BtnRiwPenyakit"); // NOI18N
        BtnRiwPenyakit.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRiwPenyakit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRiwPenyakitActionPerformed(evt);
            }
        });
        FormInput.add(BtnRiwPenyakit);
        BtnRiwPenyakit.setBounds(30, 394, 100, 23);

        BtnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosa.setMnemonic('2');
        BtnDiagnosa.setText("Template");
        BtnDiagnosa.setToolTipText("Alt+2");
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosa);
        BtnDiagnosa.setBounds(770, 787, 100, 23);

        BtnRekom.setForeground(new java.awt.Color(0, 0, 0));
        BtnRekom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnRekom.setMnemonic('2');
        BtnRekom.setText("Template");
        BtnRekom.setToolTipText("Alt+2");
        BtnRekom.setName("BtnRekom"); // NOI18N
        BtnRekom.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnRekom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRekomActionPerformed(evt);
            }
        });
        FormInput.add(BtnRekom);
        BtnRekom.setBounds(770, 944, 100, 23);

        ChkLab.setBackground(new java.awt.Color(255, 255, 250));
        ChkLab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLab.setForeground(new java.awt.Color(0, 0, 0));
        ChkLab.setText("Laboratorium");
        ChkLab.setBorderPainted(true);
        ChkLab.setBorderPaintedFlat(true);
        ChkLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLab.setName("ChkLab"); // NOI18N
        ChkLab.setOpaque(false);
        ChkLab.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLab);
        ChkLab.setBounds(20, 637, 90, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormTST.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Transfer & Serah Terima", FormTST);

        internalFrame5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 45));
        panelGlass13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Tgl. Resep :");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass13.add(jLabel54);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
        DTPCariA.setDisplayFormat("dd-MM-yyyy");
        DTPCariA.setName("DTPCariA"); // NOI18N
        DTPCariA.setOpaque(false);
        DTPCariA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariA);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setText("s.d.");
        jLabel55.setName("jLabel55"); // NOI18N
        jLabel55.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass13.add(jLabel55);

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
        DTPCariB.setDisplayFormat("dd-MM-yyyy");
        DTPCariB.setName("DTPCariB"); // NOI18N
        DTPCariB.setOpaque(false);
        DTPCariB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass13.add(DTPCariB);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Key Word :");
        jLabel56.setName("jLabel56"); // NOI18N
        jLabel56.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass13.add(jLabel56);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass13.add(TCari1);

        BtnCariObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariObat.setMnemonic('3');
        BtnCariObat.setText("Tampilkan Data");
        BtnCariObat.setToolTipText("Alt+3");
        BtnCariObat.setName("BtnCariObat"); // NOI18N
        BtnCariObat.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCariObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariObatActionPerformed(evt);
            }
        });
        panelGlass13.add(BtnCariObat);

        internalFrame5.add(panelGlass13, java.awt.BorderLayout.PAGE_START);

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

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Nama Obat :");
        jLabel53.setName("jLabel53"); // NOI18N
        jLabel53.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass16.add(jLabel53);

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
        panelGlass16.add(TResepObat);

        jPanel4.add(panelGlass16, java.awt.BorderLayout.PAGE_START);

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
        PanelInput1.setPreferredSize(new java.awt.Dimension(816, 260));
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
        Scroll44.setPreferredSize(new java.awt.Dimension(150, 130));

        tbTglBeriObat.setAutoCreateRowSorter(true);
        tbTglBeriObat.setToolTipText("Silahkan klik salah satu tgl. utk melihat obatnya");
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
        ChkPoli1.setText("Hanya dipoli/inst. ini");
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
        Scroll45.setPreferredSize(new java.awt.Dimension(390, 423));

        tbItemObat.setAutoCreateRowSorter(true);
        tbItemObat.setToolTipText("Silahkan klik untuk memilih data obatnya");
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
        panelisi4.add(BtnCari1);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
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

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Resep Obat", internalFrame5);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbTransfer.setAutoCreateRowSorter(true);
        tbTransfer.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbTransfer.setComponentPopupMenu(jPopupMenu2);
        tbTransfer.setName("tbTransfer"); // NOI18N
        tbTransfer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTransferMouseClicked(evt);
            }
        });
        tbTransfer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTransferKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbTransfer);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Triase :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
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

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "07-09-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

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

        internalFrame4.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Transfer & Serah Terima", internalFrame4);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

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
        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak RM 6 / Resep");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(160, 30));
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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "Pasien");
            } else {
                cekData();
                if (Sequel.menyimpantf("transfer_serah_terima_pasien_igd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 63, new String[]{
                    TNoRw.getText(), nip_dpjp, nip_konsulen1, nip_konsulen2, Tdiagnosis.getText(), Valid.SetTgl(tgl_masuk.getSelectedItem() + ""), kd_kamar, 
                    Valid.SetTgl(tgl_pindah.getSelectedItem() + "") + " " + tgl_pindah.getSelectedItem().toString().substring(11, 19), kd_kamar_pindah, Talasan_ranap.getText(),
                    Tkeluhan.getText(), Triw_penyakit.getText(), Triw_alergi.getText(), gcse.getText(), gcsm.getText(), gcsv.getText(), cmbKesadaran.getSelectedItem().toString(),
                    td.getText(), nadi.getText(), suhu.getText(), rr.getText(), spo2.getText(), nyeri.getText(), cmbResiko.getSelectedItem().toString(),
                    cmbKriteria.getSelectedItem().toString(), ekg, torak_foto, fotoC, fotoG, fotoA, spiri, echo, usg, ct_scan, Tket_ctscan.getText(),
                    endos, Tket_endos.getText(), ctg, Tket_ctg.getText(), penunjang_lain, Tket_lain.getText(), TDiagnosa.getText(), Valid.SetTgl(tgl_infus.getSelectedItem() + ""), 
                    Valid.SetTgl(tgl_kateter.getSelectedItem() + ""), Valid.SetTgl(tgl_ngt.getSelectedItem() + ""), Valid.SetTgl(tgl_oksigen.getSelectedItem() + ""), 
                    Valid.SetTgl(tgl_drain.getSelectedItem() + ""), alat_lain, Valid.SetTgl(tgl_alat_lain.getSelectedItem() + ""), Talat_lain.getText(), Trekomendasi.getText(),
                    Talasan_pindah.getText(), Tnm_pasienKlg.getText(), nip_dokter, nip_serah, nip_terima, Valid.SetTgl(tgl_transfer.getSelectedItem() + ""), infus, kateter, ngt,
                    oksigen, drain, lab
                }) == true) {
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                    TabRawat.setSelectedIndex(2);
                }
            }
            
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (Tdpjp.getText().trim().equals("")) {
                Valid.textKosong(Tdpjp, "Nama DPJP");
                btnDPJP.requestFocus();
            } else if (TResepObat.getText().trim().equals("")) {
                Valid.textKosong(TResepObat, "nama obat");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh meresepkan obat...!!!!");
            } else {
                Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(noId,6),signed)),0) from catatan_resep where "
                        + "tgl_perawatan like '%" + Sequel.cariIsi("select year(now())") + "%' ",
                        Sequel.cariIsi("select year(now())"), 6, noIdObat);
                try {
                    Sequel.menyimpan("catatan_resep", "?,?,?,?,?,?,?", "Data", 7, new String[]{
                        noIdObat.getText(), TNoRw.getText(), Valid.SetTgl(tgl_transfer.getSelectedItem() + ""), Sequel.cariIsi("SELECT TIME(NOW())"),
                        TResepObat.getText(), "BELUM", akses.getkode()
                    });
                    Sequel.mengedit("reg_periksa", "no_rawat='" + TNoRw.getText() + "'", "stts='Sudah Diperiksa Dokter'");
                    TResepObat.setText("");
                    TResepObat.requestFocus();
                    tampilResepObat();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data Tidak Tersimpan, Hubungi Admin ! " + e);
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
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
        if (TabRawat.getSelectedIndex() == 0 || TabRawat.getSelectedIndex() == 2) {
            if (tbTransfer.getSelectedRow() > -1) {
                hapus();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
            
        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tabModeResepObat.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
                TNoRw.requestFocus();
            } else if (TPasien.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh menghapus resepnya...!!!!");
            } else if (!TPasien.getText().trim().equals("")) {
                for (i = 0; i < tbResepObat.getRowCount(); i++) {
                    if (tbResepObat.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.queryu("delete from catatan_resep where no_rawat='" + tbResepObat.getValueAt(i, 1).toString()
                                + "' and tgl_perawatan='" + tbResepObat.getValueAt(i, 2).toString()
                                + "' and jam_perawatan='" + tbResepObat.getValueAt(i, 3).toString()
                                + "' and noId='" + tbResepObat.getValueAt(i, 7).toString() + "'");
                    }
                }
                TResepObat.setText("");
                tampilResepObat();
            }
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TabRawat.getSelectedIndex() == 0) {
            if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
                Valid.textKosong(TNoRw, "Pasien");
            } else {
                if (tbTransfer.getSelectedRow() > -1) {
                    ganti();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
            }

        } else if (TabRawat.getSelectedIndex() == 1) {
            if (tbResepObat.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Tidak ada data yang akan diperbaiki..!!!!");
            } else if (TResepObat.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Silakan pilih dulu salah satu datanya pada tabel..!!");
            } else if (akses.getadmin() == true) {
                JOptionPane.showMessageDialog(null, "Meskipun anda admin utama, tetaplah seorang dokter yang boleh merubah resepnya...!!!!");
            } else {
                if (tbResepObat.getSelectedRow() > -1) {
                    Sequel.mengedit("catatan_resep", "noId='" + TIdObat.getText() + "'",
                            "no_rawat='" + TNoRw.getText() + "',tgl_perawatan='" + Valid.SetTgl(tgl_transfer.getSelectedItem() + "") + "',"
                            + "jam_perawatan='" + Sequel.cariIsi("SELECT TIME(NOW())") + "',nama_obat = '" + TResepObat.getText() + "'");
                    TResepObat.setText("");
                    tampilResepObat();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan pilih data yang mau diganti..!!");
                    TCari.requestFocus();
                }
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TabRawat.getSelectedIndex() == 0 || TabRawat.getSelectedIndex() == 2) {
            cetakRM6();
        } else if (TabRawat.getSelectedIndex() == 1) {
            cetakResep();
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
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
        TabRawatMouseClicked(null);
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
        TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(2);
        } else if (Sequel.cariInteger("select count(-1) from transfer_serah_terima_pasien_igd where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            TCari1.setText(TNoRM.getText());
            TResepObat.setText("");
            TCariObat.setText("");
            ChkInput1.setSelected(false);
            isFormRiwayatObat();
            tampilResepObat();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbTransferKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTransferKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTransferKeyPressed

    private void tbTransferMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTransferMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbTransfer.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbTransferMouseClicked

    private void btnDPJPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDPJPActionPerformed
        pilihan = 1;
        akses.setform("RMTransferSerahTerima");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDPJPActionPerformed

    private void btnKonsulen1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonsulen1ActionPerformed
        pilihan = 2;
        akses.setform("RMTransferSerahTerima");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnKonsulen1ActionPerformed

    private void btnKonsulen2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKonsulen2ActionPerformed
        pilihan = 3;
        akses.setform("RMTransferSerahTerima");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnKonsulen2ActionPerformed

    private void btnKamar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamar1ActionPerformed
        status_kmr = "";
        pilihan = 1;        
        akses.setform("RMTransferSerahTerima");        
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_btnKamar1ActionPerformed

    private void tgl_masukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_masukKeyPressed
        
    }//GEN-LAST:event_tgl_masukKeyPressed

    private void btnKamar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamar2ActionPerformed
        status_kmr = "";
        pilihan = 2;
        akses.setform("RMTransferSerahTerima");
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_btnKamar2ActionPerformed

    private void Talasan_ranapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Talasan_ranapKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkeluhan.requestFocus();
        }
    }//GEN-LAST:event_Talasan_ranapKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Triw_penyakit.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void Triw_penyakitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triw_penyakitKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Triw_alergi.requestFocus();
        }
    }//GEN-LAST:event_Triw_penyakitKeyPressed

    private void Triw_alergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Triw_alergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            gcse.requestFocus();
        }
    }//GEN-LAST:event_Triw_alergiKeyPressed

    private void cmbResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResikoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbResikoActionPerformed

    private void cmbKriteriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKriteriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKriteriaActionPerformed

    private void ChkLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLainyaActionPerformed
        if (ChkLainya.isSelected() == true) {
            Tket_lain.setEnabled(true);
            Tket_lain.setText("");
            Tket_lain.requestFocus();
        } else {
            Tket_lain.setEnabled(false);
            Tket_lain.setText("");
        }
    }//GEN-LAST:event_ChkLainyaActionPerformed

    private void tgl_infusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_infusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_infusKeyPressed

    private void tgl_kateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_kateterKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_kateterKeyPressed

    private void tgl_ngtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_ngtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_ngtKeyPressed

    private void tgl_oksigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_oksigenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_oksigenKeyPressed

    private void tgl_drainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_drainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_drainKeyPressed

    private void tgl_alat_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_alat_lainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_alat_lainKeyPressed

    private void gcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gcseKeyPressed
        Valid.pindah(evt, Triw_alergi, gcsm);
    }//GEN-LAST:event_gcseKeyPressed

    private void gcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gcsmKeyPressed
        Valid.pindah(evt, gcse, gcsv);
    }//GEN-LAST:event_gcsmKeyPressed

    private void gcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gcsvKeyPressed
        Valid.pindah(evt, gcsm, cmbKesadaran);
    }//GEN-LAST:event_gcsvKeyPressed

    private void tdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdKeyPressed
        Valid.pindah(evt, cmbKesadaran, nadi);
    }//GEN-LAST:event_tdKeyPressed

    private void nadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nadiKeyPressed
        Valid.pindah(evt, td, suhu);
    }//GEN-LAST:event_nadiKeyPressed

    private void suhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suhuKeyPressed
        Valid.pindah(evt, nadi, rr);
    }//GEN-LAST:event_suhuKeyPressed

    private void rrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rrKeyPressed
        Valid.pindah(evt, suhu, spo2);
    }//GEN-LAST:event_rrKeyPressed

    private void spo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spo2KeyPressed
        Valid.pindah(evt, rr, nyeri);
    }//GEN-LAST:event_spo2KeyPressed

    private void nyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nyeriKeyPressed
        Valid.pindah(evt, spo2, cmbResiko);
    }//GEN-LAST:event_nyeriKeyPressed

    private void Tket_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_lainKeyPressed
        Valid.pindah(evt, Tket_lain, TDiagnosa);
    }//GEN-LAST:event_Tket_lainKeyPressed

    private void TDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagnosaKeyPressed
        Valid.pindah(evt, TDiagnosa, tgl_infus);
    }//GEN-LAST:event_TDiagnosaKeyPressed

    private void Talat_lainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Talat_lainKeyPressed
        Valid.pindah(evt, Talat_lain, tgl_alat_lain);
    }//GEN-LAST:event_Talat_lainKeyPressed

    private void TResepObatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyTyped
        evt.setKeyChar(Character.toUpperCase(evt.getKeyChar()));
    }//GEN-LAST:event_TResepObatKeyTyped

    private void TResepObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TResepObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanActionPerformed(null);
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

    private void TCariObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCariObatKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        if (TCariObat.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Nama Obat/Alkes yang akan dicari harus diisi dulu...!!!");
            TCariObat.requestFocus();
        } else {
            tampilFarmasi();
            tbObat.requestFocus();
        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

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

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariObatActionPerformed(null);
        }
    }//GEN-LAST:event_TCari1KeyPressed

    private void MnSemuanyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSemuanyaActionPerformed
        if (tabModeResepObat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Item resep obat masih kosong...!!!!");
            tbResepObat.requestFocus();
        } else {
            tampilResepObat();
            contengResep();
        }
    }//GEN-LAST:event_MnSemuanyaActionPerformed

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

    private void BtnCariObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariObatActionPerformed
        tampilResepObat();
        ChkInput1.setSelected(false);
        isFormRiwayatObat();
    }//GEN-LAST:event_BtnCariObatActionPerformed

    private void TrekomendasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrekomendasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Talasan_pindah.requestFocus();
        }
    }//GEN-LAST:event_TrekomendasiKeyPressed

    private void Talasan_pindahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Talasan_pindahKeyPressed
        Valid.pindah(evt, Trekomendasi, Tnm_pasienKlg);
    }//GEN-LAST:event_Talasan_pindahKeyPressed

    private void ChkLain_alatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkLain_alatActionPerformed
        if (ChkLain_alat.isSelected() == true) {
            Talat_lain.setText("");
            Talat_lain.setEnabled(true);            
            Talat_lain.requestFocus();
            tgl_alat_lain.setEnabled(true);
        } else {
            Talat_lain.setText("");
            Talat_lain.setEnabled(false);            
            tgl_alat_lain.setEnabled(false);
        }
    }//GEN-LAST:event_ChkLain_alatActionPerformed

    private void Tnm_pasienKlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tnm_pasienKlgKeyPressed
        Valid.pindah(evt, Tnm_pasienKlg, btnDokter);
    }//GEN-LAST:event_Tnm_pasienKlgKeyPressed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        pilihan = 4;
        akses.setform("RMTransferSerahTerima");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void btnPetugas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugas1ActionPerformed
        pilihan = 1;
        akses.setform("RMTransferSerahTerima");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugas1ActionPerformed

    private void btnPetugas2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugas2ActionPerformed
        pilihan = 2;
        akses.setform("RMTransferSerahTerima");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugas2ActionPerformed

    private void Tket_ctgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_ctgKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tket_ctgKeyPressed

    private void Tket_endosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_endosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tket_endosKeyPressed

    private void Tket_ctscanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_ctscanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tket_ctscanKeyPressed

    private void ChkCTscanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCTscanActionPerformed
        if (ChkCTscan.isSelected() == true) {
            Tket_ctscan.setEnabled(true);
            Tket_ctscan.setText("");
            Tket_ctscan.requestFocus();
        } else {
            Tket_ctscan.setEnabled(false);
            Tket_ctscan.setText("");
        }
    }//GEN-LAST:event_ChkCTscanActionPerformed

    private void ChkEndosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkEndosActionPerformed
        if (ChkEndos.isSelected() == true) {
            Tket_endos.setEnabled(true);
            Tket_endos.setText("");
            Tket_endos.requestFocus();
        } else {
            Tket_endos.setEnabled(false);
            Tket_endos.setText("");
        }
    }//GEN-LAST:event_ChkEndosActionPerformed

    private void ChkCTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkCTGActionPerformed
        if (ChkCTG.isSelected() == true) {
            Tket_ctg.setEnabled(true);
            Tket_ctg.setText("");
            Tket_ctg.requestFocus();
        } else {
            Tket_ctg.setEnabled(false);
            Tket_ctg.setText("");
        }
    }//GEN-LAST:event_ChkCTGActionPerformed

    private void tgl_transferKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl_transferKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl_transferKeyPressed

    private void ChkDrainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkDrainActionPerformed
        if (ChkDrain.isSelected() == true) {            
            tgl_drain.setEnabled(true);            
            tgl_drain.requestFocus();
        } else {            
            tgl_drain.setEnabled(false);
            tgl_drain.setDate(new Date());
        }
    }//GEN-LAST:event_ChkDrainActionPerformed

    private void ChkOksigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkOksigenActionPerformed
        if (ChkOksigen.isSelected() == true) {
            tgl_oksigen.setEnabled(true);
            tgl_oksigen.requestFocus();
        } else {
            tgl_oksigen.setEnabled(false);
            tgl_oksigen.setDate(new Date());
        }
    }//GEN-LAST:event_ChkOksigenActionPerformed

    private void ChkNGTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkNGTActionPerformed
        if (ChkNGT.isSelected() == true) {
            tgl_ngt.setEnabled(true);
            tgl_ngt.requestFocus();
        } else {
            tgl_ngt.setEnabled(false);
            tgl_ngt.setDate(new Date());
        }
    }//GEN-LAST:event_ChkNGTActionPerformed

    private void ChkKateterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkKateterActionPerformed
        if (ChkKateter.isSelected() == true) {
            tgl_kateter.setEnabled(true);
            tgl_kateter.requestFocus();
        } else {
            tgl_kateter.setEnabled(false);
            tgl_kateter.setDate(new Date());
        }
    }//GEN-LAST:event_ChkKateterActionPerformed

    private void ChkInfusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInfusActionPerformed
        if (ChkInfus.isSelected() == true) {
            tgl_infus.setEnabled(true);
            tgl_infus.requestFocus();
        } else {
            tgl_infus.setEnabled(false);
            tgl_infus.setDate(new Date());
        }
    }//GEN-LAST:event_ChkInfusActionPerformed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            btnKamar1.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void MnHasilPemeriksaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanLabActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + TNoRw.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium (LIS) tidak ditemukan ...!!!!");
            } else {
                DlgHasilLIS lis = new DlgHasilLIS(null, false);
                lis.setSize(914, internalFrame1.getHeight() - 40);
                lis.setLocationRelativeTo(internalFrame1);
                lis.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
                lis.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanLabActionPerformed

    private void MnHasilPemeriksaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanRadActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekHasilRad = 0;
            kdItemrad = "";
            itemDipilih = "";
            tglRad = "";
            jamRad = "";
            
            tglRad = Sequel.cariIsi("select tgl_periksa from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            jamRad = Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            kdItemrad = Sequel.cariIsi("select kd_jenis_prw from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                    + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");
            itemDipilih = Sequel.cariIsi("select nm_perawatan from jns_perawatan_radiologi where kd_jenis_prw='" + kdItemrad + "'");
            cekHasilRad = Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                    + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");

            if (cekHasilRad >= 1) {                
                akses.setform("RMTransferSerahTerimaIGD");
                DlgCariPeriksaRadiologi form = new DlgCariPeriksaRadiologi(null, false);
                form.WindowHasil.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.WindowHasil.setLocationRelativeTo(internalFrame1);
                form.isCek();
                form.setData(TNoRw.getText(), kdItemrad, itemDipilih, "OK", TNoRM.getText(), TPasien.getText(), tglRad, jamRad, Tdiagnosis.getText(),
                        Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.kd_dokter where pr.no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.dokter_perujuk where pr.no_rawat='" + TNoRw.getText() + "'"),
                        Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where "
                                + "kd_rujukan='" + Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'") + "'"));
                form.WindowHasil.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan radiologi (expertise) tidak ditemukan ...!!!!");
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanRadActionPerformed

    private void BtnDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnDiagnosisActionPerformed

    private void BtnKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluhanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Keluhan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnKeluhanActionPerformed

    private void BtnRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwAlergiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Riwayat Alergi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnRiwAlergiActionPerformed

    private void BtnAlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlasanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alasan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnAlasanActionPerformed

    private void BtnRiwPenyakitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRiwPenyakitActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Riwayat Penyakit ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnRiwPenyakitActionPerformed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnRekomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRekomActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari2.setText("");

        pilihan = 7;
        tampilTemplate();
        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Rekomendasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari2.requestFocus();
    }//GEN-LAST:event_BtnRekomActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilTemplate();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

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
                    Ttemplate.setText(tbTemplate.getValueAt(tbTemplate.getSelectedRow(), 2).toString());
                }
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTemplateMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMTransferSerahTerimaIGD dialog = new RMTransferSerahTerimaIGD(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlasan;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCariObat;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnDiagnosis;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluhan;
    private widget.Button BtnPrint;
    private widget.Button BtnRekom;
    private widget.Button BtnRiwAlergi;
    private widget.Button BtnRiwPenyakit;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkCTG;
    public widget.CekBox ChkCTscan;
    public widget.CekBox ChkDrain;
    public widget.CekBox ChkEKG;
    public widget.CekBox ChkEcho;
    public widget.CekBox ChkEndos;
    public widget.CekBox ChkFotoA;
    public widget.CekBox ChkFotoC;
    public widget.CekBox ChkFotoG;
    public widget.CekBox ChkInfus;
    private widget.CekBox ChkInput1;
    public widget.CekBox ChkKateter;
    public widget.CekBox ChkLab;
    public widget.CekBox ChkLain_alat;
    public widget.CekBox ChkLainya;
    public widget.CekBox ChkNGT;
    public widget.CekBox ChkOksigen;
    private widget.CekBox ChkPoli1;
    public widget.CekBox ChkSpiri;
    public widget.CekBox ChkThoraks;
    public widget.CekBox ChkUSG;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTST;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDibatalkan;
    private javax.swing.JMenuItem MnHasilPemeriksaanLab;
    private javax.swing.JMenuItem MnHasilPemeriksaanRad;
    private javax.swing.JMenuItem MnSemuanya;
    private javax.swing.JPanel PanelInput1;
    private javax.swing.JPanel PanelRiwayatObat;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll44;
    private widget.ScrollPane Scroll45;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCari2;
    private widget.TextBox TCariObat;
    private widget.TextBox TDiagnosa;
    private widget.TextBox TIdObat;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TResepObat;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talasan_pindah;
    private widget.TextArea Talasan_ranap;
    private widget.TextBox Talat_lain;
    private widget.TextArea Tdiagnosis;
    private widget.TextBox Tdpjp;
    private widget.TextBox Tdr_konsulen1;
    private widget.TextBox Tdr_konsulen2;
    private widget.TextArea Tkeluhan;
    private widget.TextBox Tket_ctg;
    private widget.TextBox Tket_ctscan;
    private widget.TextBox Tket_endos;
    private widget.TextBox Tket_lain;
    private widget.TextBox Tnm_dokter;
    private widget.TextBox Tnm_kamar;
    private widget.TextBox Tnm_kamar_pindah;
    private widget.TextBox Tnm_pasienKlg;
    private widget.TextBox Tnm_petugas1;
    private widget.TextBox Tnm_petugas2;
    private widget.TextArea Trekomendasi;
    private widget.TextArea Triw_alergi;
    private widget.TextArea Triw_penyakit;
    private widget.TextArea Ttemplate;
    private javax.swing.JDialog WindowTemplate;
    private widget.Button btnDPJP;
    private widget.Button btnDokter;
    private widget.Button btnKamar1;
    private widget.Button btnKamar2;
    private widget.Button btnKonsulen1;
    private widget.Button btnKonsulen2;
    private widget.Button btnPetugas1;
    private widget.Button btnPetugas2;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbKriteria;
    private widget.ComboBox cmbResiko;
    private widget.TextBox gcse;
    private widget.TextBox gcsm;
    private widget.TextBox gcsv;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
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
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
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
    private widget.Label jLabel62;
    private widget.Label jLabel63;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel74;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.TextBox nadi;
    private widget.TextBox noIdObat;
    private widget.TextBox nyeri;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass16;
    private widget.panelisi panelGlass17;
    private widget.panelisi panelGlass18;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.TextBox rr;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane9;
    private widget.TextBox spo2;
    private widget.TextBox suhu;
    private widget.Table tbItemObat;
    private widget.Table tbObat;
    private widget.Table tbResepObat;
    private widget.Table tbTemplate;
    private widget.Table tbTglBeriObat;
    private widget.Table tbTransfer;
    private widget.TextBox td;
    private widget.Tanggal tgl_alat_lain;
    private widget.Tanggal tgl_drain;
    private widget.Tanggal tgl_infus;
    private widget.Tanggal tgl_kateter;
    private widget.Tanggal tgl_masuk;
    private widget.Tanggal tgl_ngt;
    private widget.Tanggal tgl_oksigen;
    private widget.Tanggal tgl_pindah;
    private widget.Tanggal tgl_transfer;
    private widget.TextBox tgllahir;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, "
                    + "d.nm_dokter dpjp, date_format(ts.tgl_masuk,'%d-%m-%Y') tglmsk, date_format(tgl_jam_pindah,'%d-%m-%Y %H:%i') tgljampndh, "
                    + "ts.* from transfer_serah_terima_pasien_igd ts inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join dokter d on d.kd_dokter=ts.nip_dpjp where "
                    + "ts.tgl_serah_terima_transfer between ? and ? and ts.no_rawat like ? or "
                    + "ts.tgl_serah_terima_transfer between ? and ? and p.no_rkm_medis like ? or "
                    + "ts.tgl_serah_terima_transfer between ? and ? and p.nm_pasien like ? or "
                    + "ts.tgl_serah_terima_transfer between ? and ? and ts.nm_pasien_keluarga like ? or "
                    + "ts.tgl_serah_terima_transfer between ? and ? and d.nm_dokter like ? "
                    + "order by ts.tgl_serah_terima_transfer desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("dpjp"),
                        Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rs.getString("nip_konsulen1") + "'"),
                        Sequel.cariIsi("select ifnull(nama,'-') from pegawai where nik='" + rs.getString("nip_konsulen2") + "'"),
                        rs.getString("diagnosis"),
                        rs.getString("tglmsk"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar_msk") + "'"),
                        rs.getString("tgljampndh"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar_pindah") + "'"),
                        rs.getString("alasan_ranap"),
                        rs.getString("keluhan"),
                        rs.getString("nip_dpjp"),
                        rs.getString("nip_konsulen1"),
                        rs.getString("nip_konsulen2"),
                        rs.getString("diagnosis"),
                        rs.getString("tgl_masuk"),
                        rs.getString("kd_kamar_msk"),
                        rs.getString("tgl_jam_pindah"),
                        rs.getString("kd_kamar_pindah"),
                        rs.getString("alasan_ranap"),
                        rs.getString("keluhan"),
                        rs.getString("riwayat_penyakit"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("rr"),
                        rs.getString("spo2"),
                        rs.getString("skala_nyeri"),
                        rs.getString("resiko_jatuh"),
                        rs.getString("kriteria_transfer"),                        
                        rs.getString("ekg"),
                        rs.getString("thoraks_foto"),
                        rs.getString("foto_cervikal"),
                        rs.getString("foto_genu"),
                        rs.getString("foto_abdomen"),
                        rs.getString("spiritometri"),
                        rs.getString("echo"),
                        rs.getString("usg"),
                        rs.getString("ct_scan"),
                        rs.getString("ket_ct_scan"),
                        rs.getString("endoskopi"),
                        rs.getString("ket_endoskopi"),
                        rs.getString("ctg"),
                        rs.getString("ket_ctg"),
                        rs.getString("lainnya"),
                        rs.getString("ket_lainnya"),
                        rs.getString("diagnosa"),
                        rs.getString("tgl_infus"),
                        rs.getString("tgl_kateter"),
                        rs.getString("tgl_ngt"),
                        rs.getString("tgl_oksigen"),
                        rs.getString("tgl_drain"),
                        rs.getString("lainya_alat"),
                        rs.getString("tgl_alat_lain"),
                        rs.getString("nm_alat_lain"),
                        rs.getString("rekomendasi"),
                        rs.getString("alasan_pindah_ruangan"),
                        rs.getString("nm_pasien_keluarga"),
                        rs.getString("nip_dokter_setuju"),
                        rs.getString("nip_menyerahkan"),
                        rs.getString("nip_menerima"),
                        rs.getString("tgl_serah_terima_transfer"),
                        rs.getString("infus"),
                        rs.getString("kateter"),
                        rs.getString("ngt"),
                        rs.getString("oksigen"),
                        rs.getString("drain"),
                        rs.getString("lab")
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
    
    public void emptTeks(){
        nip_dpjp = "-";
        nip_konsulen1 = "-";
        nip_konsulen2 = "-";
        Tdpjp.setText("-");
        Tdr_konsulen1.setText("-");
        Tdr_konsulen2.setText("-");
        
        tgl_transfer.setDate(new Date());
        tgl_masuk.setDate(new Date());
        Tdiagnosis.setText("");
        Tnm_kamar.setText("");
        tgl_pindah.setDate(new Date());
        Tnm_kamar_pindah.setText("");
        Talasan_ranap.setText("");
        Tkeluhan.setText("");
        Triw_penyakit.setText("");
        Triw_alergi.setText("");
        gcse.setText("");
        gcsm.setText("");
        gcsv.setText("");
        cmbKesadaran.setSelectedIndex(0);        
        spo2.setText("");        
        cmbResiko.setSelectedIndex(0);
        cmbKriteria.setSelectedIndex(0);
        ChkLab.setSelected(false);
        ChkEKG.setSelected(false);
        ChkThoraks.setSelected(false);
        ChkFotoC.setSelected(false);
        ChkFotoG.setSelected(false);
        ChkFotoA.setSelected(false);
        ChkSpiri.setSelected(false);
        ChkEcho.setSelected(false);
        ChkUSG.setSelected(false);
        ChkCTscan.setSelected(false);
        ChkEndos.setSelected(false);
        ChkCTG.setSelected(false);
        ChkLainya.setSelected(false);
        Tket_ctscan.setText("");
        Tket_endos.setText("");
        Tket_ctg.setText("");
        Tket_lain.setText("");
        Tket_ctscan.setEnabled(false);
        Tket_endos.setEnabled(false);
        Tket_ctg.setEnabled(false);
        Tket_lain.setEnabled(false);
        TDiagnosa.setText("");
        
        tgl_infus.setDate(new Date());
        tgl_kateter.setDate(new Date());
        tgl_ngt.setDate(new Date());
        tgl_oksigen.setDate(new Date());
        tgl_drain.setDate(new Date());
        tgl_alat_lain.setDate(new Date());
        
        tgl_infus.setEnabled(false);
        tgl_kateter.setEnabled(false);
        tgl_ngt.setEnabled(false);
        tgl_oksigen.setEnabled(false);
        tgl_drain.setEnabled(false);
        tgl_alat_lain.setEnabled(false);
        
        ChkInfus.setSelected(false);
        ChkKateter.setSelected(false);
        ChkNGT.setSelected(false);
        ChkOksigen.setSelected(false);
        ChkDrain.setSelected(false);
        ChkLain_alat.setSelected(false);
        
        Talat_lain.setText("");
        Talat_lain.setEnabled(false);        
        Trekomendasi.setText("");
        Talasan_pindah.setText("");
        Tnm_pasienKlg.setText("");
        
        nip_dokter = "-";
        nip_serah = "-";
        nip_terima = "-";
        Tnm_dokter.setText("-");
        Tnm_petugas1.setText("-");
        Tnm_petugas2.setText("-");

        TResepObat.setText("");
        TCariObat.setText("");
        ChkInput1.setSelected(false);
        isFormRiwayatObat();
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        tampil();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_triase_igd());
        BtnHapus.setEnabled(akses.getdata_triase_igd());
        BtnPrint.setEnabled(akses.getdata_triase_igd());
        BtnEdit.setEnabled(akses.getdata_triase_igd());
        TabRawat.setEnabledAt(1, akses.getresep_dokter());
    }
    
    private void getData() {
        nip_dpjp = "";
        nip_konsulen1 = "";
        nip_konsulen2 = "";
        kd_kamar = "";
        kd_kamar_pindah = "";
        resepDipilih = "";
        cekResep = "";
        tglResep = "";
        status_kmr = "";
        tglPemberianObat = "";
        nip_dokter = "";
        nip_serah = "";
        nip_terima = "";
        ekg = "";
        torak_foto = "";
        fotoC = "";
        fotoG = "";
        fotoA = "";
        spiri = "";
        echo = "";
        usg = "";
        ct_scan = "";
        endos = "";
        ctg = "";
        penunjang_lain = "";
        alat_lain = "";
        infus = "";
        kateter = "";
        ngt = "";
        oksigen = "";
        drain = "";
        lab = "";

        if (tbTransfer.getSelectedRow() != -1) {
            TNoRw.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString());
            TNoRM.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 1).toString());
            TPasien.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 2).toString());
            tgllahir.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 3).toString());
            Tdpjp.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 4).toString());
            Tdr_konsulen1.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 5).toString());
            Tdr_konsulen2.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 6).toString());
            Tdiagnosis.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 7).toString());
            Valid.SetTgl(tgl_masuk, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 18).toString());
            Tnm_kamar.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 9).toString());
            Valid.SetTgl2(tgl_pindah, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 20).toString());
            Tnm_kamar_pindah.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 11).toString());            
            Talasan_ranap.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 12).toString());
            Tkeluhan.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 13).toString());            
            nip_dpjp = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 14).toString();
            nip_konsulen1 = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 15).toString();
            nip_konsulen2 = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 16).toString();            
            kd_kamar = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 19).toString();
            kd_kamar_pindah = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 21).toString();
            Triw_penyakit.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 24).toString());
            Triw_alergi.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 25).toString());
            gcse.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 26).toString());
            gcsm.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 27).toString());
            gcsv.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 28).toString());
            cmbKesadaran.setSelectedItem(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 29).toString());
            td.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 30).toString());
            nadi.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 31).toString());
            suhu.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 32).toString());
            rr.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 33).toString());
            spo2.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 34).toString());
            nyeri.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 35).toString());
            cmbResiko.setSelectedItem(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 36).toString());            
            cmbKriteria.setSelectedItem(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 37).toString());
            ekg = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 38).toString();
            torak_foto = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 39).toString();
            fotoC = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 40).toString();
            fotoG = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 41).toString();
            fotoA = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 42).toString();
            spiri = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 43).toString();
            echo = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 44).toString();
            usg = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 45).toString();
            ct_scan = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 46).toString();
            Tket_ctscan.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 47).toString());
            endos = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 48).toString();
            Tket_endos.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 49).toString());
            ctg = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 50).toString();
            Tket_ctg.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 51).toString());
            penunjang_lain = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 52).toString();
            Tket_lain.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 53).toString());
            TDiagnosa.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 54).toString());
            Valid.SetTgl(tgl_infus, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 55).toString());
            Valid.SetTgl(tgl_kateter, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 56).toString());
            Valid.SetTgl(tgl_ngt, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 57).toString());
            Valid.SetTgl(tgl_oksigen, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 58).toString());
            Valid.SetTgl(tgl_drain, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 59).toString());
            alat_lain = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 60).toString();
            Valid.SetTgl(tgl_alat_lain, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 61).toString());
            Talat_lain.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 62).toString());
            Trekomendasi.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 63).toString());
            Talasan_pindah.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 64).toString());
            Tnm_pasienKlg.setText(tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 65).toString());
            nip_dokter = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 66).toString();
            Tnm_dokter.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip_dokter + "'"));
            nip_serah = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 67).toString();
            Tnm_petugas1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip_serah + "'"));
            nip_terima = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 68).toString();
            Tnm_petugas2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip_terima + "'"));
            Valid.SetTgl(tgl_transfer, tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 69).toString());
            infus = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 70).toString();
            kateter = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 71).toString();
            ngt = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 72).toString();
            oksigen = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 73).toString();
            drain = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 74).toString();
            lab = tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 75).toString();
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from transfer_serah_terima_pasien_igd where no_rawat=?", 1, new String[]{
                tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString()
            }) == true) {                             
                tampil();
                emptTeks();                
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("transfer_serah_terima_pasien_igd", "no_rawat=?", "no_rawat=?, nip_dpjp=?, nip_konsulen1=?, nip_konsulen2=?, diagnosis=?, tgl_masuk=?, "
                + "kd_kamar_msk=?, tgl_jam_pindah=?, kd_kamar_pindah=?, alasan_ranap=?, keluhan=?, riwayat_penyakit=?, riwayat_alergi=?, gcs_e=?, gcs_m=?, gcs_v=?, "
                + "kesadaran=?, td=?, nadi=?, suhu=?, rr=?, spo2=?, skala_nyeri=?, resiko_jatuh=?, kriteria_transfer=?, ekg=?, thoraks_foto=?, foto_cervikal=?, "
                + "foto_genu=?, foto_abdomen=?, spiritometri=?, echo=?, usg=?, ct_scan=?, ket_ct_scan=?, endoskopi=?, ket_endoskopi=?, ctg=?, ket_ctg=?, lainnya=?, "
                + "ket_lainnya=?, diagnosa=?, tgl_infus=?, tgl_kateter=?, tgl_ngt=?, tgl_oksigen=?, tgl_drain=?, lainya_alat=?, tgl_alat_lain=?, nm_alat_lain=?, "
                + "rekomendasi=?, alasan_pindah_ruangan=?, nm_pasien_keluarga=?, nip_dokter_setuju=?, nip_menyerahkan=?, nip_menerima=?, tgl_serah_terima_transfer=?, "
                + "infus=?, kateter=?, ngt=?, oksigen=?, drain=?, lab=?", 64, new String[]{
                    TNoRw.getText(), nip_dpjp, nip_konsulen1, nip_konsulen2, Tdiagnosis.getText(), Valid.SetTgl(tgl_masuk.getSelectedItem() + ""), kd_kamar,
                    Valid.SetTgl(tgl_pindah.getSelectedItem() + "") + " " + tgl_pindah.getSelectedItem().toString().substring(11, 19), kd_kamar_pindah, Talasan_ranap.getText(),
                    Tkeluhan.getText(), Triw_penyakit.getText(), Triw_alergi.getText(), gcse.getText(), gcsm.getText(), gcsv.getText(), cmbKesadaran.getSelectedItem().toString(),
                    td.getText(), nadi.getText(), suhu.getText(), rr.getText(), spo2.getText(), nyeri.getText(), cmbResiko.getSelectedItem().toString(),
                    cmbKriteria.getSelectedItem().toString(), ekg, torak_foto, fotoC, fotoG, fotoA, spiri, echo, usg, ct_scan, Tket_ctscan.getText(),
                    endos, Tket_endos.getText(), ctg, Tket_ctg.getText(), penunjang_lain, Tket_lain.getText(), TDiagnosa.getText(), Valid.SetTgl(tgl_infus.getSelectedItem() + ""),
                    Valid.SetTgl(tgl_kateter.getSelectedItem() + ""), Valid.SetTgl(tgl_ngt.getSelectedItem() + ""), Valid.SetTgl(tgl_oksigen.getSelectedItem() + ""),
                    Valid.SetTgl(tgl_drain.getSelectedItem() + ""), alat_lain, Valid.SetTgl(tgl_alat_lain.getSelectedItem() + ""), Talat_lain.getText(), Trekomendasi.getText(),
                    Talasan_pindah.getText(), Tnm_pasienKlg.getText(), nip_dokter, nip_serah, nip_terima, Valid.SetTgl(tgl_transfer.getSelectedItem() + ""), infus, kateter, ngt,
                    oksigen, drain, lab,
                    tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString()
                }) == true) {

            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(2);
        }
    }
    
    private void isRawat() {
        try {
            ps1 = koneksi.prepareStatement("select rp.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, "
                    + "rp.tgl_registrasi, IFNULL(pr.td,'') td, IFNULL(pr.nadi,'') nadi, IFNULL(pr.suhu,'') suhu, IFNULL(pr.nafas,'') nafas, "
                    + "ifnull(pa.diag_medis_sementara,'') diagnosa, ifnull(pr.skala_nyeri,'') skala FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "LEFT JOIN penilaian_awal_medis_igd pa ON pa.no_rawat = rp.no_rawat "
                    + "left join penilaian_awal_keperawatan_igdrz pr on pr.no_rawat=rp.no_rawat WHERE rp.no_rawat = ?");
            try {
                ps1.setString(1, TNoRw.getText());
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    DTPCari1.setDate(rs1.getDate("tgl_registrasi"));
                    DTPCariA.setDate(rs1.getDate("tgl_registrasi"));
                    tgllahir.setText(rs1.getString("tgl_lahir"));
                    
                    td.setText(rs1.getString("td"));
                    nadi.setText(rs1.getString("nadi"));
                    rr.setText(rs1.getString("nafas"));
                    suhu.setText(rs1.getString("suhu"));
                    Tdiagnosis.setText(rs1.getString("diagnosa"));
                    nyeri.setText(rs1.getString("skala"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
    
    private void tampilResepObat() {
        Valid.tabelKosong(tabModeResepObat);
        try {
            ps2 = koneksi.prepareStatement("select c.no_rawat, c.tgl_perawatan, c.jam_perawatan, c.nama_obat, c.status, d.nm_dokter, c.noID from catatan_resep c "
                    + "inner join reg_periksa r on r.no_rawat = c.no_rawat  "
                    + "inner join dokter d on d.kd_dokter = c.kd_dokter where  "
                    + "c.tgl_perawatan between ? and ? and c.no_rawat like ? or "
                    + "c.tgl_perawatan between ? and ? and c.nama_obat like ? or "
                    + "c.tgl_perawatan between ? and ? and r.no_rkm_medis like ? "
                    + "order by c.noId");
            try {
                ps2.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps2.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps2.setString(3, "%" + TCari1.getText().trim() + "%");
                ps2.setString(4, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps2.setString(5, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps2.setString(6, "%" + TCari1.getText().trim() + "%");
                ps2.setString(7, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps2.setString(8, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps2.setString(9, "%" + TCari1.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabModeResepObat.addRow(new Object[]{
                        false,
                        rs2.getString(1),
                        rs2.getString(2),
                        rs2.getString(3),
                        rs2.getString(4),
                        rs2.getString(5),
                        rs2.getString(6),
                        rs2.getString(7)
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
        LCount.setText("" + tabModeResepObat.getRowCount());
    }
    
    private void contengResep() {
        for (i = 0; i < tbResepObat.getRowCount(); i++) {
            if (tbResepObat.getValueAt(i, 1).equals(TNoRw.getText())) {
                tbResepObat.setValueAt(Boolean.TRUE, i, 0);
            }
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
    
    private void isFormRiwayatObat() {
        if (ChkInput1.isSelected() == true) {
            ChkInput1.setVisible(false);
            PanelInput1.setPreferredSize(new Dimension(WIDTH, 220));
            PanelRiwayatObat.setVisible(true);
            ChkInput1.setVisible(true);
            ChkPoli1.setSelected(true);
            
            if (ChkPoli1.isSelected() == true) {
                ChkPoli1.setText("Hanya dipoli/inst. ini");
                tampilTglBeriObat();
            } else if (ChkPoli1.isSelected() == false) {
                ChkPoli1.setText("Semua Poli/Inst.");
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
    
    private void tampilTglBeriObat() {
        Valid.tabelKosong(tabModeTglBeriObat);
        try {
            if (ChkPoli1.isSelected() == true) {
                psTglBO = koneksi.prepareStatement("select dpo.tgl_perawatan tglAsli, date_format(dpo.tgl_perawatan,'%d-%m-%Y') tanggal, "
                        + "count(dpo.kode_brng) jlhItem, pl.nm_poli, d.nm_dokter from detail_pemberian_obat dpo "
                        + "inner join reg_periksa rp on rp.no_rawat=dpo.no_rawat "
                        + "inner join poliklinik pl on pl.kd_poli=rp.kd_poli "
                        + "inner join dokter d on d.kd_dokter=rp.kd_dokter where "
                        + "rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='IGDK' and rp.status_lanjut='ralan' "
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
    
    private void getDataCatatanResep() {
        if (tbResepObat.getSelectedRow() != -1) {
            TNoRw.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 1).toString());
            TResepObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 4).toString());            
            TIdObat.setText(tbResepObat.getValueAt(tbResepObat.getSelectedRow(), 7).toString());
        }
    }
    
    private void getDataRiwObat() {
        tglPemberianObat = "";
        if (tbTglBeriObat.getSelectedRow() != -1) {
            tglPemberianObat = tbTglBeriObat.getValueAt(tbTglBeriObat.getSelectedRow(), 0).toString();
            tampilRiwItemObat();
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
                        + "dpo.tgl_perawatan='" + tglPemberianObat + "' and rp.no_rkm_medis='" + TNoRM.getText() + "' and rp.kd_poli='IGDK' and rp.status_lanjut='ralan'");
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
    
    private void getDataItemObat() {
        if (tbItemObat.getSelectedRow() != -1) {
            TResepObat.setText(tbItemObat.getValueAt(tbItemObat.getSelectedRow(), 1).toString());
            TResepObat.requestFocus();
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
    
    private void cetakResep() {
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
                JOptionPane.showMessageDialog(null, "Item resep yg. akan dicetak masih belum diconteng...!!");
                contengResep();
                BtnPrint.requestFocus();
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
                    JOptionPane.showMessageDialog(null, "Maaf, hanya resep utk. hari ini yg. bisa dicetak, krn. pasien sdh. dilayani/diresepkan obatnya...!!!");
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
    }
    
    private void cekData() {
        if (ChkLab.isSelected() == true) {
            lab = "ya";
        } else {
            lab = "tidak";
        }
        
        if (ChkEKG.isSelected() == true) {
            ekg = "ya";
        } else {
            ekg = "tidak";
        }
        
        if (ChkThoraks.isSelected() == true) {
            torak_foto = "ya";
        } else {
            torak_foto = "tidak";
        }
        
        if (ChkFotoC.isSelected() == true) {
            fotoC = "ya";
        } else {
            fotoC = "tidak";
        }
        
        if (ChkFotoG.isSelected() == true) {
            fotoG = "ya";
        } else {
            fotoG = "tidak";
        }
        
        if (ChkFotoA.isSelected() == true) {
            fotoA = "ya";
        } else {
            fotoA = "tidak";
        }
        
        if (ChkSpiri.isSelected() == true) {
            spiri = "ya";
        } else {
            spiri = "tidak";
        }
        
        if (ChkEcho.isSelected() == true) {
            echo = "ya";
        } else {
            echo = "tidak";
        }
        
        if (ChkUSG.isSelected() == true) {
            usg = "ya";
        } else {
            usg = "tidak";
        }
        
        if (ChkCTscan.isSelected() == true) {
            ct_scan = "ya";
        } else {
            ct_scan = "tidak";
        }
        
        if (ChkEndos.isSelected() == true) {
            endos = "ya";
        } else {
            endos = "tidak";
        }
        
        if (ChkCTG.isSelected() == true) {
            ctg = "ya";
        } else {
            ctg = "tidak";
        }
        
        if (ChkLainya.isSelected() == true) {
            penunjang_lain = "ya";
        } else {
            penunjang_lain = "tidak";
        }
        
        if (ChkInfus.isSelected() == true) {
            infus = "ya";
        } else {
            infus = "tidak";
        }
        
        if (ChkKateter.isSelected() == true) {
            kateter = "ya";
        } else {
            kateter = "tidak";
        }
        
        if (ChkNGT.isSelected() == true) {
            ngt = "ya";
        } else {
            ngt = "tidak";
        }
        
        if (ChkOksigen.isSelected() == true) {
            oksigen = "ya";
        } else {
            oksigen = "tidak";
        }
        
        if (ChkDrain.isSelected() == true) {
            drain = "ya";
        } else {
            drain = "tidak";
        }
        
        if (ChkLain_alat.isSelected() == true) {
            alat_lain = "ya";
        } else {
            alat_lain = "tidak";
        }
    }
    
    private void cetakRM6() {
        if (tbTransfer.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", tgllahir.getText());
            param.put("nmdpjpp", Tdpjp.getText());
            param.put("konsulen1", Tdr_konsulen1.getText());
            param.put("konsulen2", Tdr_konsulen2.getText());
            param.put("diagnosis", Tdiagnosis.getText());
            param.put("tglmasuk", tgl_masuk.getSelectedItem().toString());
            param.put("ruangkamar", Tnm_kamar.getText());
            param.put("tgljampindah", tgl_pindah.getSelectedItem().toString());
            param.put("ruangkamarpindah", Tnm_kamar_pindah.getText());
            param.put("alasanranap", Talasan_ranap.getText());
            param.put("keluhan", Tkeluhan.getText());
            param.put("riwpenyakit", Triw_penyakit.getText());
            param.put("riwalergi", Triw_alergi.getText());
            param.put("gcse", gcse.getText());
            param.put("gcsm", gcsm.getText());
            param.put("gcsv", gcsv.getText());
            param.put("kesadaran", cmbKesadaran.getSelectedItem().toString());
            param.put("tensi", td.getText());
            param.put("nadi", nadi.getText());
            param.put("suhu", suhu.getText());
            param.put("rr", rr.getText());
            param.put("spo2", spo2.getText());
            param.put("nyeri", nyeri.getText());
            param.put("resikojatuh", cmbResiko.getSelectedItem().toString());
            param.put("kriteria", cmbKriteria.getSelectedItem().toString());
            param.put("diagnosa", TDiagnosa.getText());
            param.put("rekomendasi", Trekomendasi.getText());
            param.put("alasanpindahruangan", Talasan_pindah.getText());
            param.put("nmpasienkeluarga", Tnm_pasienKlg.getText());
            param.put("nmdokter", Tnm_dokter.getText());
            param.put("ygmenyerahkan", Tnm_petugas1.getText());
            param.put("ygmenerima", Tnm_petugas2.getText());
            param.put("tgltransferserah", tgl_transfer.getSelectedItem().toString().substring(0, 2) + " " 
                    + Sequel.bulanINDONESIA("select date_format(tgl_serah_terima_transfer,'%m') from transfer_serah_terima_pasien_igd where "
                            + "no_rawat='" + tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString() + "'") + " "
                    + tgl_transfer.getSelectedItem().toString().substring(6, 10));
            
            if (ChkLab.isSelected() == true) {
                param.put("lab", "V");
            } else {
                param.put("lab", "");
            }
            
            if (ChkEKG.isSelected() == true) {
                param.put("ekg", "V");
            } else {
                param.put("ekg", "");
            }
            
            if (ChkThoraks.isSelected() == true) {
                param.put("torak", "V");
            } else {
                param.put("torak", "");
            }
            
            if (ChkFotoC.isSelected() == true) {
                param.put("fotoc", "V");
            } else {
                param.put("fotoc", "");
            }
            
            if (ChkFotoG.isSelected() == true) {
                param.put("fotog", "V");
            } else {
                param.put("fotog", "");
            }
            
            if (ChkFotoA.isSelected() == true) {
                param.put("fotoa", "V");
            } else {
                param.put("fotoa", "");
            }
            
            if (ChkSpiri.isSelected() == true) {
                param.put("spiri", "V");
            } else {
                param.put("spiri", "");
            }
            
            if (ChkEcho.isSelected() == true) {
                param.put("echo", "V");
            } else {
                param.put("echo", "");
            }
            
            if (ChkUSG.isSelected() == true) {
                param.put("usg", "V");
            } else {
                param.put("usg", "");
            }
            
            if (ChkCTscan.isSelected() == true) {
                param.put("ctscan", "V");
                param.put("ketctscan", Tket_ctscan.getText());
            } else {
                param.put("ctscan", "");
                param.put("ketctscan", "");
            }
            
            if (ChkEndos.isSelected() == true) {
                param.put("endos", "V");
                param.put("ketendos", Tket_endos.getText());
            } else {
                param.put("endos", "");
                param.put("ketendos", "");
            }
            
            if (ChkCTG.isSelected() == true) {
                param.put("ctg", "V");
                param.put("ketctg", Tket_ctg.getText());
            } else {
                param.put("ctg", "");
                param.put("ketctg", "");
            }
            
            if (ChkLainya.isSelected() == true) {
                param.put("lainya", "V");
                param.put("ketlainya", Tket_lain.getText());
            } else {
                param.put("lainya", "");
                param.put("ketlainya", "");
            }
            
            if (ChkInfus.isSelected() == true) {
                param.put("infus", "V");
                param.put("tglinfus", tgl_infus.getSelectedItem().toString());
            } else {
                param.put("infus", "");
                param.put("tglinfus", "-");
            }
            
            if (ChkKateter.isSelected() == true) {
                param.put("kateter", "V");
                param.put("tglkateter", tgl_kateter.getSelectedItem().toString());
            } else {
                param.put("kateter", "");
                param.put("tglkateter", "-");
            }
            
            if (ChkNGT.isSelected() == true) {
                param.put("ngt", "V");
                param.put("tglngt", tgl_ngt.getSelectedItem().toString());
            } else {
                param.put("ngt", "");
                param.put("tglngt", "-");
            }
            
            if (ChkOksigen.isSelected() == true) {
                param.put("oksigen", "V");
                param.put("tgloksigen", tgl_oksigen.getSelectedItem().toString());
            } else {
                param.put("oksigen", "");
                param.put("tgloksigen", "-");
            }
            
            if (ChkDrain.isSelected() == true) {
                param.put("drain", "V");
                param.put("tgldrain", tgl_drain.getSelectedItem().toString());
            } else {
                param.put("drain", "");
                param.put("tgldrain", "-");
            }
            
            if (ChkLain_alat.isSelected() == true) {
                param.put("lainalat", "V");
                param.put("nmalatlain", Talat_lain.getText());
                param.put("tgllainalat", tgl_alat_lain.getSelectedItem().toString());
            } else {
                param.put("lainalat", "");
                param.put("nmalatlain", "-");
                param.put("tgllainalat", "-");
            }

            if (Sequel.cariInteger("select count(-1) from catatan_resep where no_rawat='" + tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString() + "'") == 0) {
                Valid.MyReport("rptTransferPasienIGDnonResep.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien IGD ]::",
                        "SELECT date(now())", param);
            } else {
                Valid.MyReport("rptTransferPasienIGD.jasper", "report", "::[ Laporan Data Transfer & Serah Terima Pasien IGD ]::",
                        "SELECT nama_obat, noId FROM catatan_resep WHERE no_rawat ='" + tbTransfer.getValueAt(tbTransfer.getSelectedRow(), 0).toString() + "' "
                        + "ORDER BY noId", param);
            }

            emptTeks();
            TabRawat.setSelectedIndex(2);
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
    }
    
    private void dataCek() {
        if (lab.equals("ya")) {
            ChkLab.setSelected(true);
        } else {
            ChkLab.setSelected(false);
        }
        
        if (ekg.equals("ya")) {
            ChkEKG.setSelected(true);
        } else {
            ChkEKG.setSelected(false);
        }

        if (torak_foto.equals("ya")) {
            ChkThoraks.setSelected(true);
        } else {
            ChkThoraks.setSelected(false);
        }

        if (fotoC.equals("ya")) {
            ChkFotoC.setSelected(true);
        } else {
            ChkFotoC.setSelected(false);
        }

        if (fotoG.equals("ya")) {
            ChkFotoG.setSelected(true);
        } else {
            ChkFotoG.setSelected(false);
        }

        if (fotoA.equals("ya")) {
            ChkFotoA.setSelected(true);
        } else {
            ChkFotoA.setSelected(false);
        }

        if (spiri.equals("ya")) {
            ChkSpiri.setSelected(true);
        } else {
            ChkSpiri.setSelected(false);
        }

        if (echo.equals("ya")) {
            ChkEcho.setSelected(true);
        } else {
            ChkEcho.setSelected(false);
        }

        if (usg.equals("ya")) {
            ChkUSG.setSelected(true);
        } else {
            ChkUSG.setSelected(false);
        }

        if (ct_scan.equals("ya")) {
            ChkCTscan.setSelected(true);
            Tket_ctscan.setEnabled(true);
        } else {
            ChkCTscan.setSelected(false);
            Tket_ctscan.setEnabled(false);
        }

        if (endos.equals("ya")) {
            ChkEndos.setSelected(true);
            Tket_endos.setEnabled(true);
        } else {
            ChkEndos.setSelected(false);
            Tket_endos.setEnabled(false);
        }

        if (ctg.equals("ya")) {
            ChkCTG.setSelected(true);
            Tket_ctg.setEnabled(true);
        } else {
            ChkCTG.setSelected(false);
            Tket_ctg.setEnabled(false);
        }

        if (penunjang_lain.equals("ya")) {
            ChkLainya.setSelected(true);
            Tket_lain.setEnabled(true);
        } else {
            ChkLainya.setSelected(false);
            Tket_lain.setEnabled(false);
        }

        if (infus.equals("ya")) {
            ChkInfus.setSelected(true);
            tgl_infus.setEnabled(true);
        } else {
            ChkInfus.setSelected(false);
            tgl_infus.setEnabled(false);
        }

        if (kateter.equals("ya")) {
            ChkKateter.setSelected(true);
            tgl_kateter.setEnabled(true);
        } else {
            ChkKateter.setSelected(false);
            tgl_kateter.setEnabled(false);
        }

        if (ngt.equals("ya")) {
            ChkNGT.setSelected(true);
            tgl_ngt.setEnabled(true);
        } else {
            ChkNGT.setSelected(false);
            tgl_ngt.setEnabled(false);
        }

        if (oksigen.equals("ya")) {
            ChkOksigen.setSelected(true);
            tgl_oksigen.setEnabled(true);
        } else {
            ChkOksigen.setSelected(false);
            tgl_oksigen.setEnabled(false);
        }

        if (drain.equals("ya")) {
            ChkDrain.setSelected(true);
            tgl_drain.setEnabled(true);
        } else {
            ChkDrain.setSelected(false);
            tgl_drain.setEnabled(false);
        }

        if (alat_lain.equals("ya")) {
            ChkLain_alat.setSelected(true);
            Talat_lain.setEnabled(true);
            tgl_alat_lain.setEnabled(true);
        } else {
            ChkLain_alat.setSelected(false);
            Talat_lain.setEnabled(false);
            tgl_alat_lain.setEnabled(false);
        }
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.diagnosis<>'' and p.no_rkm_medis like ? OR "
                        + "ts.diagnosis<>'' and p.nm_pasien like ? OR "
                        + "ts.diagnosis<>'' and ts.diagnosis like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.keluhan<>'' and p.no_rkm_medis like ? OR "
                        + "ts.keluhan<>'' and p.nm_pasien like ? OR "
                        + "ts.keluhan<>'' and ts.keluhan like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 3) {
                pps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.riwayat_alergi<>'' and p.no_rkm_medis like ? OR "
                        + "ts.riwayat_alergi<>'' and p.nm_pasien like ? OR "
                        + "ts.riwayat_alergi<>'' and ts.riwayat_alergi like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 4) {
                pps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.alasan_ranap<>'' and p.no_rkm_medis like ? OR "
                        + "ts.alasan_ranap<>'' and p.nm_pasien like ? OR "
                        + "ts.alasan_ranap<>'' and ts.alasan_ranap like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 5) {
                pps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.riwayat_penyakit<>'' and p.no_rkm_medis like ? OR "
                        + "ts.riwayat_penyakit<>'' and p.nm_pasien like ? OR "
                        + "ts.riwayat_penyakit<>'' and ts.riwayat_penyakit like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 6) {
                pps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.diagnosa<>'' and p.no_rkm_medis like ? OR "
                        + "ts.diagnosa<>'' and p.nm_pasien like ? OR "
                        + "ts.diagnosa<>'' and ts.diagnosa like ? ORDER BY ts.tgl_masuk desc limit 20");
            } else if (pilihan == 7) {
                pps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, ts.* from transfer_serah_terima_pasien_igd ts "
                        + "inner join reg_periksa rp on rp.no_rawat=ts.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "ts.rekomendasi<>'' and p.no_rkm_medis like ? OR "
                        + "ts.rekomendasi<>'' and p.nm_pasien like ? OR "
                        + "ts.rekomendasi<>'' and ts.rekomendasi like ? ORDER BY ts.tgl_masuk desc limit 20");
            }
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari2.getText() + "%");
                    pps1.setString(2, "%" + TCari2.getText() + "%");
                    pps1.setString(3, "%" + TCari2.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode1.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("diagnosis")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari2.getText() + "%");
                    pps2.setString(2, "%" + TCari2.getText() + "%");
                    pps2.setString(3, "%" + TCari2.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode1.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("keluhan")
                        });
                    }
                } else if (pilihan == 3) {
                    pps3.setString(1, "%" + TCari2.getText() + "%");
                    pps3.setString(2, "%" + TCari2.getText() + "%");
                    pps3.setString(3, "%" + TCari2.getText() + "%");
                    rrs3 = pps3.executeQuery();
                    while (rrs3.next()) {
                        tabMode1.addRow(new String[]{
                            rrs3.getString("no_rkm_medis"),
                            rrs3.getString("nm_pasien"),
                            rrs3.getString("riwayat_alergi")
                        });
                    }
                } else if (pilihan == 4) {
                    pps4.setString(1, "%" + TCari2.getText() + "%");
                    pps4.setString(2, "%" + TCari2.getText() + "%");
                    pps4.setString(3, "%" + TCari2.getText() + "%");
                    rrs4 = pps4.executeQuery();
                    while (rrs4.next()) {
                        tabMode1.addRow(new String[]{
                            rrs4.getString("no_rkm_medis"),
                            rrs4.getString("nm_pasien"),
                            rrs4.getString("alasan_ranap")
                        });
                    }
                } else if (pilihan == 5) {
                    pps5.setString(1, "%" + TCari2.getText() + "%");
                    pps5.setString(2, "%" + TCari2.getText() + "%");
                    pps5.setString(3, "%" + TCari2.getText() + "%");
                    rrs5 = pps5.executeQuery();
                    while (rrs5.next()) {
                        tabMode1.addRow(new String[]{
                            rrs5.getString("no_rkm_medis"),
                            rrs5.getString("nm_pasien"),
                            rrs5.getString("riwayat_penyakit")
                        });
                    }
                } else if (pilihan == 6) {
                    pps6.setString(1, "%" + TCari2.getText() + "%");
                    pps6.setString(2, "%" + TCari2.getText() + "%");
                    pps6.setString(3, "%" + TCari2.getText() + "%");
                    rrs6 = pps6.executeQuery();
                    while (rrs6.next()) {
                        tabMode1.addRow(new String[]{
                            rrs6.getString("no_rkm_medis"),
                            rrs6.getString("nm_pasien"),
                            rrs6.getString("diagnosa")
                        });
                    }
                } else if (pilihan == 7) {
                    pps7.setString(1, "%" + TCari2.getText() + "%");
                    pps7.setString(2, "%" + TCari2.getText() + "%");
                    pps7.setString(3, "%" + TCari2.getText() + "%");
                    rrs7 = pps7.executeQuery();
                    while (rrs7.next()) {
                        tabMode1.addRow(new String[]{
                            rrs7.getString("no_rkm_medis"),
                            rrs7.getString("nm_pasien"),
                            rrs7.getString("rekomendasi")
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
                } else if (rrs7 != null) {
                    rrs7.close();
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
                } else if (pps7 != null) {
                    pps7.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            Tdiagnosis.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tkeluhan.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Triw_alergi.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Talasan_ranap.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            Triw_penyakit.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            TDiagnosa.setText(Ttemplate.getText());
        } else if (pilihan == 7) {
            Trekomendasi.setText(Ttemplate.getText());
        }
    }
}