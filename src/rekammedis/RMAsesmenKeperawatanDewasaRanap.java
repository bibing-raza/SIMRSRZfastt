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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import laporan.DlgHasilLIS;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenKeperawatanDewasaRanap extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, skor = 0, skorDecu = 0;
    private String nip = "", kdkamar = "", alergiObat = "", alergiMakanan = "", alergiLain = "", gelang = "", ibadah = "",
            obatObatan = "", perawatanLuka = "", manajemenNyeri = "", diet = "", fisio = "", hipertermi = "", nyeri = "", resiko = "",
            kelebihan = "", bersihkan = "", pola = "", gangguan = "", cemas = "", ketidakseimbangan = "", perubahan = "", penurunan = "",
            kerusakan = "", intoleransi = "", kurang = "", perluMPP = "", perluDP = "", resikojatuh = "", resikodecubitus = "", skorFix = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenKeperawatanDewasaRanap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Masuk Ruangan", "Tgl. Masuk", "Jam Masuk", "Tiba Dg. Cara", "Melalui", "Keluhan Utama",
            "Tgl. Asesmen", "Jam", "Nama Perawat", "kd_kamar_msk", "tgl_msk_ruangan", "jam_msk_ruangan", "tiba_diruang", "tiba_diruang_lainnya", "msk_melalui", 
            "keluhan_utama", "riwayat_alergi", "alergi_obat", "reaksi_alergi_obat", "alergi_makanan", "reaksi_alergi_makanan", "alergi_lainnya", "reaksi_alergi_lainnya", 
            "pasang_gelang_tanda", "alergi_diberitahukan", "riwayat_penyakit_sekarang", "kesadaran", "gcs_e", "gcs_m", "gcs_v", "tensi", "temp", "hr", "rr", 
            "bb_sebelum_skt", "bb_msk_rs", "tb", "imt", "crt", "spo2", "pernafasan", "pernafasan_lain", "penglihatan", "penglihatan_lain", "pendengaran", "pendengaran_lain", 
            "mulut", "mulut_lain", "reflek_menelan", "reflek_menelan_lain", "bicara", "bicara_lain", "defekasi", "defekasi_lain", "miksi", "miksi_lain", "gastrointestinal", 
            "gastrointestinal_lain", "pola_tidur", "pola_tidur_lain", "makan", "berpakaian", "buang_air", "mandi", "berpindah", "kesimpulan", "keluarga_terdekat", 
            "hubungan", "tinggal_dengan", "tinggal_dengan_lain", "curiga_penganiayaan", "butuh_bantuan_ibadah", "status_emosional", "gizi_1", "gizi_1ya", "gizi_2", 
            "onset", "provocation", "provocation_lain", "quality", "quality_lain", "radiation", "severity", "time", "time_lama", "relief", "relief_lain", "asociated_sign", 
            "asociated_sign_lain", "skala_nyeri", "tindakan_pencegahan", "nilai_skala_kekuatan_otot", "obat_obatan", "perawatan_luka", "manajemen_lain", "menejemen_nyeri", 
            "diet_nutrisi", "fisioterapi", "rehabilitasi_lain", "hipertermi", "nyeri", "kurang_volume_cairan", "lebih_volume_cairan", "bersihkan_jln_nfs_tdk_efektif", 
            "pola_nfs_tdk_efektif", "gangguan_pertukaran_gas", "cemas", "ketidakseimbangan_nutrisi", "perubahan_perfusi", "penurunan_curah_jantung", "kerusakan_integritas", 
            "intoleransi_aktifitas", "kurang_perawatan_diri", "memerlukan_mpp", "memerlukan_dp", "daftar_masalah_lain", "tgl_asesmen", "nip_perawat", "jam_asesmen", 
            "waktu_simpan", "nm_alergi_obat", "nm_alergi_makanan", "nm_alergi_lainnya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 127; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(200);
            } else if (i == 5) {
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(80);                
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(220);
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
            } else if (i == 76) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 77) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 78) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 79) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 80) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 81) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 82) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 83) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 84) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 85) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 86) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 87) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 88) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 89) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 90) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 91) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 92) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 93) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 94) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 95) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 96) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 97) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 98) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 99) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 100) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 101) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 102) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 103) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 104) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 105) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 106) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 107) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 108) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 109) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 110) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 111) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 112) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 113) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 114) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 115) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 116) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 117) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 118) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 119) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 120) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 121) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 122) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 123) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 124) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 125) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 126) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA / POIN", "SKOR"
            }){
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
        
        tbFaktorResiko.setModel(tabMode1);
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
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(255);
            } else if (i == 5) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "PARAMETER", "PENILAIAN", "SKOR"
            }){
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
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbFaktorDecu.setModel(tabMode2);
        tbFaktorDecu.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbFaktorDecu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbFaktorDecu.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(150);
            } else if (i == 4) {
                column.setPreferredWidth(40);
            }
        }
        tbFaktorDecu.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        Tkesadaran.setDocument(new batasInput((int) 180).getKata(Tkesadaran));
        Tgcse.setDocument(new batasInput((int) 5).getKata(Tgcse));
        Tgcsm.setDocument(new batasInput((int) 5).getKata(Tgcsm));
        Tgcsv.setDocument(new batasInput((int) 5).getKata(Tgcsv));
        Ttensi.setDocument(new batasInput((int) 7).getKata(Ttensi));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        TbbBelum.setDocument(new batasInput((int) 7).getKata(TbbBelum));
        TbbMasuk.setDocument(new batasInput((int) 7).getKata(TbbMasuk));
        Ttb.setDocument(new batasInput((int) 7).getKata(Ttb));
        Timt.setDocument(new batasInput((int) 7).getKata(Timt));
        Tcrt.setDocument(new batasInput((int) 7).getKata(Tcrt));
        Tspo.setDocument(new batasInput((int) 7).getKata(Tspo));
        TKlgDekat.setDocument(new batasInput((int) 150).getKata(TKlgDekat));
        Thubungan.setDocument(new batasInput((int) 150).getKata(Thubungan));
        TtglDenganLain.setDocument(new batasInput((int) 150).getKata(TtglDenganLain));
        TnmAlergiObat.setDocument(new batasInput((int) 200).getKata(TnmAlergiObat));
        TnmAlergiMakanan.setDocument(new batasInput((int) 200).getKata(TnmAlergiMakanan));
        TnmAlergiLain.setDocument(new batasInput((int) 200).getKata(TnmAlergiLain));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPerawat.requestFocus();
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

        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel5 = new widget.Label();
        Truangan = new widget.TextBox();
        TtglMsk = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel8 = new widget.Label();
        cmbTiba = new widget.ComboBox();
        TtibaLain = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbMasuk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbRiwAlergi = new widget.ComboBox();
        ChkAlergiObat = new widget.CekBox();
        ChkAlergiMakanan = new widget.CekBox();
        ChkAlergiLainya = new widget.CekBox();
        ChkGelang = new widget.CekBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        TreaksiObat = new widget.TextBox();
        TreaksiMakanan = new widget.TextBox();
        TreaksiLain = new widget.TextBox();
        jLabel15 = new widget.Label();
        cmbAlergiDiberitahu = new widget.ComboBox();
        jLabel16 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        TriwPenyktSkg = new widget.TextArea();
        jLabel17 = new widget.Label();
        jLabel22 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel23 = new widget.Label();
        Tgcse = new widget.TextBox();
        jLabel24 = new widget.Label();
        Tgcsm = new widget.TextBox();
        jLabel25 = new widget.Label();
        Tgcsv = new widget.TextBox();
        jLabel26 = new widget.Label();
        Ttensi = new widget.TextBox();
        jLabel27 = new widget.Label();
        Ttemp = new widget.TextBox();
        jLabel28 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel29 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel30 = new widget.Label();
        TbbBelum = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        TbbMasuk = new widget.TextBox();
        jLabel33 = new widget.Label();
        Ttb = new widget.TextBox();
        jLabel34 = new widget.Label();
        Timt = new widget.TextBox();
        jLabel35 = new widget.Label();
        Tcrt = new widget.TextBox();
        jLabel36 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        cmbPernafasan = new widget.ComboBox();
        cmbPenglihatan = new widget.ComboBox();
        cmbPendengaran = new widget.ComboBox();
        cmbMulut = new widget.ComboBox();
        cmbReflek = new widget.ComboBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        cmbBicara = new widget.ComboBox();
        cmbDefekasi = new widget.ComboBox();
        cmbMiksi = new widget.ComboBox();
        cmbGastro = new widget.ComboBox();
        cmbPola = new widget.ComboBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        cmbMakan = new widget.ComboBox();
        cmbBerpakaian = new widget.ComboBox();
        cmbBuang = new widget.ComboBox();
        cmbMandi = new widget.ComboBox();
        cmbBerpindah = new widget.ComboBox();
        jLabel54 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        Tkesimpulan = new widget.TextArea();
        jLabel55 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel57 = new widget.Label();
        TsttsNikah = new widget.TextBox();
        TKlgDekat = new widget.TextBox();
        jLabel58 = new widget.Label();
        Thubungan = new widget.TextBox();
        jLabel59 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel60 = new widget.Label();
        cmbTinggal = new widget.ComboBox();
        TtglDenganLain = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        cmbCuriga = new widget.ComboBox();
        ChkIbadah = new widget.CekBox();
        jLabel63 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel64 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbGizi1 = new widget.ComboBox();
        skorGizi1 = new widget.TextBox();
        cmbYaGizi1 = new widget.ComboBox();
        skorYaGizi1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        cmbGizi2 = new widget.ComboBox();
        skorGizi2 = new widget.TextBox();
        jLabel74 = new widget.Label();
        TotSkorGizi = new widget.TextBox();
        kesimpulanGizi = new widget.TextArea();
        jLabel65 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel66 = new widget.Label();
        jLabel67 = new widget.Label();
        Tonset = new widget.TextBox();
        jLabel68 = new widget.Label();
        jLabel70 = new widget.Label();
        cmbProvo = new widget.ComboBox();
        Tprovo = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        cmbQuality = new widget.ComboBox();
        Tquality = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel76 = new widget.Label();
        cmbRadia = new widget.ComboBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbSever = new widget.ComboBox();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        cmbTime = new widget.ComboBox();
        jLabel81 = new widget.Label();
        cmbLama = new widget.ComboBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        cmbRelief = new widget.ComboBox();
        Trelief = new widget.TextBox();
        jLabel84 = new widget.Label();
        jLabel85 = new widget.Label();
        cmbAsso = new widget.ComboBox();
        Tasso = new widget.TextBox();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        kesimpulanResikoJatuh = new widget.TextArea();
        jLabel97 = new widget.Label();
        TotSkorRJ = new widget.TextBox();
        label12 = new widget.Label();
        TCariResiko = new widget.TextBox();
        BtnCariResiko = new widget.Button();
        BtnAllResiko = new widget.Button();
        Scroll9 = new widget.ScrollPane();
        tbFaktorDecu = new widget.Table();
        kesimpulanResikoDecu = new widget.TextArea();
        jLabel98 = new widget.Label();
        TotSkorDecu = new widget.TextBox();
        label13 = new widget.Label();
        TCariDecu = new widget.TextBox();
        BtnCariDecu = new widget.Button();
        BtnAllDecu = new widget.Button();
        jLabel86 = new widget.Label();
        TabPencegahanDewasa = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        cegahA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        cegahB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        cegahC = new widget.TextArea();
        jLabel100 = new widget.Label();
        cmbSkala = new widget.ComboBox();
        jLabel140 = new widget.Label();
        cmbTindakanCegah = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbNilai = new widget.ComboBox();
        Tketerangan = new widget.TextArea();
        jLabel88 = new widget.Label();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        ChkObat = new widget.CekBox();
        ChkPerawatan = new widget.CekBox();
        TmanajemenLain = new widget.TextBox();
        jLabel91 = new widget.Label();
        ChkManajemen = new widget.CekBox();
        ChkDiet = new widget.CekBox();
        ChkFisioterapi = new widget.CekBox();
        TrehabLain = new widget.TextBox();
        jLabel92 = new widget.Label();
        jLabel93 = new widget.Label();
        ChkHipertermi = new widget.CekBox();
        ChkNyeri = new widget.CekBox();
        ChkResiko = new widget.CekBox();
        ChkKelebihan = new widget.CekBox();
        ChkBersihkan = new widget.CekBox();
        ChkPola = new widget.CekBox();
        ChkGangguan = new widget.CekBox();
        ChkCemas = new widget.CekBox();
        ChkKetidakseimbangan = new widget.CekBox();
        ChkPerubahan = new widget.CekBox();
        ChkPenurunan = new widget.CekBox();
        ChkKerusakan = new widget.CekBox();
        ChkIntoleransi = new widget.CekBox();
        ChkKurang = new widget.CekBox();
        ChkMPP = new widget.CekBox();
        ChkDP = new widget.CekBox();
        jLabel94 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        TmasalahLain = new widget.TextArea();
        jLabel95 = new widget.Label();
        TtglAses = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel99 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        Tpernafasan = new widget.TextBox();
        Tpenglihatan = new widget.TextBox();
        Tpendengaran = new widget.TextBox();
        Tmulut = new widget.TextBox();
        Treflek = new widget.TextBox();
        Tbicara = new widget.TextBox();
        Tdefekasi = new widget.TextBox();
        Tmiksi = new widget.TextBox();
        Tgastro = new widget.TextBox();
        Tpola = new widget.TextBox();
        BtnIMT = new widget.Button();
        TnmAlergiObat = new widget.TextBox();
        TnmAlergiMakanan = new widget.TextBox();
        TnmAlergiLain = new widget.TextBox();
        internalFrame4 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Dewasa ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("");
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2297));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 135, 23);

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
        TPasien.setBounds(332, 10, 390, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(558, 38, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Masuk Ruang : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 135, 23);

        Truangan.setEditable(false);
        Truangan.setBackground(new java.awt.Color(245, 250, 240));
        Truangan.setForeground(new java.awt.Color(0, 0, 0));
        Truangan.setName("Truangan"); // NOI18N
        FormInput.add(Truangan);
        Truangan.setBounds(136, 38, 420, 23);

        TtglMsk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-12-2023" }));
        TtglMsk.setDisplayFormat("dd-MM-yyyy");
        TtglMsk.setName("TtglMsk"); // NOI18N
        TtglMsk.setOpaque(false);
        TtglMsk.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglMsk);
        TtglMsk.setBounds(630, 38, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jam : ");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(722, 38, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(765, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(816, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(868, 38, 45, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tiba Diruang Dengan Cara : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 163, 23);

        cmbTiba.setForeground(new java.awt.Color(0, 0, 0));
        cmbTiba.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Jalan", "Kursi Roda", "Brankar", "Lainnya" }));
        cmbTiba.setName("cmbTiba"); // NOI18N
        cmbTiba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTibaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTiba);
        cmbTiba.setBounds(165, 66, 85, 23);

        TtibaLain.setBackground(new java.awt.Color(245, 250, 240));
        TtibaLain.setForeground(new java.awt.Color(0, 0, 0));
        TtibaLain.setName("TtibaLain"); // NOI18N
        TtibaLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtibaLainKeyPressed(evt);
            }
        });
        FormInput.add(TtibaLain);
        TtibaLain.setBounds(256, 66, 300, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Masuk Melalui : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(560, 66, 90, 23);

        cmbMasuk.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasuk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "IGD", "Poliklinik" }));
        cmbMasuk.setName("cmbMasuk"); // NOI18N
        FormInput.add(cmbMasuk);
        cmbMasuk.setBounds(653, 66, 75, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("KELUHAN UTAMA : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 135, 23);

        Tkeluhan.setBackground(new java.awt.Color(245, 250, 240));
        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(136, 94, 780, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("RIWAYAT ALERGI : ");
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 135, 23);

        cmbRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak ada", "Tidak diketahui" }));
        cmbRiwAlergi.setName("cmbRiwAlergi"); // NOI18N
        FormInput.add(cmbRiwAlergi);
        cmbRiwAlergi.setBounds(136, 122, 110, 23);

        ChkAlergiObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiObat.setText("Alergi Obat ");
        ChkAlergiObat.setBorderPainted(true);
        ChkAlergiObat.setBorderPaintedFlat(true);
        ChkAlergiObat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiObat.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiObat.setName("ChkAlergiObat"); // NOI18N
        ChkAlergiObat.setOpaque(false);
        ChkAlergiObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiObat);
        ChkAlergiObat.setBounds(15, 150, 115, 23);

        ChkAlergiMakanan.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiMakanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiMakanan.setText("Alergi Makanan ");
        ChkAlergiMakanan.setBorderPainted(true);
        ChkAlergiMakanan.setBorderPaintedFlat(true);
        ChkAlergiMakanan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiMakanan.setName("ChkAlergiMakanan"); // NOI18N
        ChkAlergiMakanan.setOpaque(false);
        ChkAlergiMakanan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiMakananActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiMakanan);
        ChkAlergiMakanan.setBounds(15, 178, 115, 23);

        ChkAlergiLainya.setBackground(new java.awt.Color(255, 255, 250));
        ChkAlergiLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAlergiLainya.setForeground(new java.awt.Color(0, 0, 0));
        ChkAlergiLainya.setText("Alergi Lainnya ");
        ChkAlergiLainya.setBorderPainted(true);
        ChkAlergiLainya.setBorderPaintedFlat(true);
        ChkAlergiLainya.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkAlergiLainya.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        ChkAlergiLainya.setName("ChkAlergiLainya"); // NOI18N
        ChkAlergiLainya.setOpaque(false);
        ChkAlergiLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkAlergiLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAlergiLainyaActionPerformed(evt);
            }
        });
        FormInput.add(ChkAlergiLainya);
        ChkAlergiLainya.setBounds(15, 206, 115, 23);

        ChkGelang.setBackground(new java.awt.Color(255, 255, 250));
        ChkGelang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGelang.setForeground(new java.awt.Color(0, 0, 0));
        ChkGelang.setText("Gelang Tanda Alergi Dipasang (Warna Merah)");
        ChkGelang.setBorderPainted(true);
        ChkGelang.setBorderPaintedFlat(true);
        ChkGelang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkGelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGelang.setName("ChkGelang"); // NOI18N
        ChkGelang.setOpaque(false);
        ChkGelang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGelang);
        ChkGelang.setBounds(15, 234, 270, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Reaksi : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(466, 150, 50, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Reaksi : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(466, 178, 50, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Reaksi : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(466, 206, 50, 23);

        TreaksiObat.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiObat.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiObat.setName("TreaksiObat"); // NOI18N
        FormInput.add(TreaksiObat);
        TreaksiObat.setBounds(516, 150, 400, 23);

        TreaksiMakanan.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiMakanan.setName("TreaksiMakanan"); // NOI18N
        FormInput.add(TreaksiMakanan);
        TreaksiMakanan.setBounds(516, 178, 400, 23);

        TreaksiLain.setBackground(new java.awt.Color(245, 250, 240));
        TreaksiLain.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiLain.setName("TreaksiLain"); // NOI18N
        FormInput.add(TreaksiLain);
        TreaksiLain.setBounds(516, 206, 400, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Alergi Diberitahukan Kepada : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(300, 234, 170, 23);

        cmbAlergiDiberitahu.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlergiDiberitahu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dokter", "Farmasis/Apoteker", "Ahli Gizi" }));
        cmbAlergiDiberitahu.setName("cmbAlergiDiberitahu"); // NOI18N
        FormInput.add(cmbAlergiDiberitahu);
        cmbAlergiDiberitahu.setBounds(473, 234, 130, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Riwayat Penyakit Sekarang : ");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 262, 163, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        TriwPenyktSkg.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TriwPenyktSkg.setColumns(20);
        TriwPenyktSkg.setRows(5);
        TriwPenyktSkg.setName("TriwPenyktSkg"); // NOI18N
        TriwPenyktSkg.setPreferredSize(new java.awt.Dimension(162, 700));
        TriwPenyktSkg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TriwPenyktSkgKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(TriwPenyktSkg);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(165, 262, 750, 70);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("KEADAAN UMUM : ");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 340, 135, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Kesadaran : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 355, 135, 23);

        Tkesadaran.setBackground(new java.awt.Color(245, 250, 240));
        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Tkesadaran);
        Tkesadaran.setBounds(136, 355, 510, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("GCS : E ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(650, 355, 50, 23);

        Tgcse.setBackground(new java.awt.Color(245, 250, 240));
        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        FormInput.add(Tgcse);
        Tgcse.setBounds(705, 355, 50, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("M");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(755, 355, 20, 23);

        Tgcsm.setBackground(new java.awt.Color(245, 250, 240));
        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsm);
        Tgcsm.setBounds(775, 355, 50, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("V");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(825, 355, 20, 23);

        Tgcsv.setBackground(new java.awt.Color(245, 250, 240));
        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        FormInput.add(Tgcsv);
        Tgcsv.setBounds(845, 355, 50, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tensi : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 383, 135, 23);

        Ttensi.setBackground(new java.awt.Color(245, 250, 240));
        Ttensi.setForeground(new java.awt.Color(0, 0, 0));
        Ttensi.setName("Ttensi"); // NOI18N
        Ttensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtensiKeyPressed(evt);
            }
        });
        FormInput.add(Ttensi);
        Ttensi.setBounds(136, 383, 70, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("mmHg       Temp : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(210, 383, 90, 23);

        Ttemp.setBackground(new java.awt.Color(245, 250, 240));
        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(304, 383, 70, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("°C      HR : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(380, 383, 55, 23);

        Thr.setBackground(new java.awt.Color(245, 250, 240));
        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(436, 383, 70, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt      RR : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(510, 383, 70, 23);

        Trr.setBackground(new java.awt.Color(245, 250, 240));
        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(583, 383, 70, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("x/mnt      BB Sebelum Sakit : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(660, 383, 140, 23);

        TbbBelum.setBackground(new java.awt.Color(245, 250, 240));
        TbbBelum.setForeground(new java.awt.Color(0, 0, 0));
        TbbBelum.setName("TbbBelum"); // NOI18N
        TbbBelum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbBelumKeyPressed(evt);
            }
        });
        FormInput.add(TbbBelum);
        TbbBelum.setBounds(798, 383, 55, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Kg.");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(858, 383, 30, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("BB Masuk RS : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 411, 135, 23);

        TbbMasuk.setBackground(new java.awt.Color(245, 250, 240));
        TbbMasuk.setForeground(new java.awt.Color(0, 0, 0));
        TbbMasuk.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        TbbMasuk.setName("TbbMasuk"); // NOI18N
        TbbMasuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbMasukKeyPressed(evt);
            }
        });
        FormInput.add(TbbMasuk);
        TbbMasuk.setBounds(136, 411, 70, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Kg.           TB :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(210, 411, 73, 23);

        Ttb.setBackground(new java.awt.Color(245, 250, 240));
        Ttb.setForeground(new java.awt.Color(0, 0, 0));
        Ttb.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Ttb.setName("Ttb"); // NOI18N
        Ttb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtbKeyPressed(evt);
            }
        });
        FormInput.add(Ttb);
        Ttb.setBounds(283, 411, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Cm");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(348, 411, 20, 23);

        Timt.setBackground(new java.awt.Color(245, 250, 240));
        Timt.setForeground(new java.awt.Color(0, 0, 0));
        Timt.setName("Timt"); // NOI18N
        Timt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TimtKeyPressed(evt);
            }
        });
        FormInput.add(Timt);
        Timt.setBounds(501, 411, 68, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Kg.      CRT : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(575, 411, 65, 23);

        Tcrt.setBackground(new java.awt.Color(245, 250, 240));
        Tcrt.setForeground(new java.awt.Color(0, 0, 0));
        Tcrt.setName("Tcrt"); // NOI18N
        Tcrt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcrtKeyPressed(evt);
            }
        });
        FormInput.add(Tcrt);
        Tcrt.setBounds(642, 411, 70, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("detik       SPO2 : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(719, 411, 81, 23);

        Tspo.setBackground(new java.awt.Color(245, 250, 240));
        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(800, 411, 55, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("%");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(860, 411, 30, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("PENILAIAN FISIK : ");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(0, 439, 135, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Pernafasan : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 454, 135, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Penglihatan : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 482, 135, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Pendengaran : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(0, 510, 135, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Mulut : ");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 538, 135, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Reflek Menelan : ");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 566, 135, 23);

        cmbPernafasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernafasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Batuk", "Sesak", "Lainnya" }));
        cmbPernafasan.setName("cmbPernafasan"); // NOI18N
        cmbPernafasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernafasanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernafasan);
        cmbPernafasan.setBounds(136, 454, 70, 23);

        cmbPenglihatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenglihatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Rusak", "Alat Bantu", "Lainnya" }));
        cmbPenglihatan.setName("cmbPenglihatan"); // NOI18N
        cmbPenglihatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenglihatanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPenglihatan);
        cmbPenglihatan.setBounds(136, 482, 85, 23);

        cmbPendengaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendengaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Rusak", "Alat Bantu", "Lainnya" }));
        cmbPendengaran.setName("cmbPendengaran"); // NOI18N
        cmbPendengaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPendengaranActionPerformed(evt);
            }
        });
        FormInput.add(cmbPendengaran);
        cmbPendengaran.setBounds(136, 510, 85, 23);

        cmbMulut.setForeground(new java.awt.Color(0, 0, 0));
        cmbMulut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bersih", "Kotor", "Lainnya" }));
        cmbMulut.setName("cmbMulut"); // NOI18N
        cmbMulut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMulutActionPerformed(evt);
            }
        });
        FormInput.add(cmbMulut);
        cmbMulut.setBounds(136, 538, 85, 23);

        cmbReflek.setForeground(new java.awt.Color(0, 0, 0));
        cmbReflek.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Sulit", "Rusak", "Lainnya" }));
        cmbReflek.setName("cmbReflek"); // NOI18N
        cmbReflek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReflekActionPerformed(evt);
            }
        });
        FormInput.add(cmbReflek);
        cmbReflek.setBounds(136, 566, 70, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Bicara : ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(343, 454, 105, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Defekasi : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(343, 482, 105, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Miksi : ");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(343, 510, 105, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Gastrointestinal : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(343, 538, 105, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Pola Tidur : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(343, 566, 105, 23);

        cmbBicara.setForeground(new java.awt.Color(0, 0, 0));
        cmbBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Gangguan", "Lainnya" }));
        cmbBicara.setName("cmbBicara"); // NOI18N
        cmbBicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBicaraActionPerformed(evt);
            }
        });
        FormInput.add(cmbBicara);
        cmbBicara.setBounds(451, 454, 82, 23);

        cmbDefekasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDefekasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Konstipasi", "Diare", "Inkontinensia Alvi", "Lainnya" }));
        cmbDefekasi.setName("cmbDefekasi"); // NOI18N
        cmbDefekasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDefekasiActionPerformed(evt);
            }
        });
        FormInput.add(cmbDefekasi);
        cmbDefekasi.setBounds(451, 482, 119, 23);

        cmbMiksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbMiksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Retensi", "Inkontinensia Urin", "Lainnya" }));
        cmbMiksi.setName("cmbMiksi"); // NOI18N
        cmbMiksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMiksiActionPerformed(evt);
            }
        });
        FormInput.add(cmbMiksi);
        cmbMiksi.setBounds(451, 510, 121, 23);

        cmbGastro.setForeground(new java.awt.Color(0, 0, 0));
        cmbGastro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Refluks", "Nausea", "Muntah", "Lainnya" }));
        cmbGastro.setName("cmbGastro"); // NOI18N
        cmbGastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGastroActionPerformed(evt);
            }
        });
        FormInput.add(cmbGastro);
        cmbGastro.setBounds(451, 538, 70, 23);

        cmbPola.setForeground(new java.awt.Color(0, 0, 0));
        cmbPola.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Masalah", "Lainnya" }));
        cmbPola.setName("cmbPola"); // NOI18N
        cmbPola.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPolaActionPerformed(evt);
            }
        });
        FormInput.add(cmbPola);
        cmbPola.setBounds(451, 566, 75, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Makan : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(692, 454, 80, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Berpakaian : ");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(692, 482, 80, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Buang Air : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(692, 510, 80, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Mandi : ");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(692, 538, 80, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Berpindah : ");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(692, 566, 80, 23);

        cmbMakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbMakan.setName("cmbMakan"); // NOI18N
        FormInput.add(cmbMakan);
        cmbMakan.setBounds(775, 454, 103, 23);

        cmbBerpakaian.setForeground(new java.awt.Color(0, 0, 0));
        cmbBerpakaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBerpakaian.setName("cmbBerpakaian"); // NOI18N
        FormInput.add(cmbBerpakaian);
        cmbBerpakaian.setBounds(775, 482, 103, 23);

        cmbBuang.setForeground(new java.awt.Color(0, 0, 0));
        cmbBuang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBuang.setName("cmbBuang"); // NOI18N
        FormInput.add(cmbBuang);
        cmbBuang.setBounds(775, 510, 103, 23);

        cmbMandi.setForeground(new java.awt.Color(0, 0, 0));
        cmbMandi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbMandi.setName("cmbMandi"); // NOI18N
        FormInput.add(cmbMandi);
        cmbMandi.setBounds(775, 538, 103, 23);

        cmbBerpindah.setForeground(new java.awt.Color(0, 0, 0));
        cmbBerpindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu Alat", "Dibantu Orang", "Dibantu Total" }));
        cmbBerpindah.setName("cmbBerpindah"); // NOI18N
        FormInput.add(cmbBerpindah);
        cmbBerpindah.setBounds(775, 566, 103, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Kesimpulan : ");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(0, 594, 135, 23);

        scrollPane4.setName("scrollPane4"); // NOI18N

        Tkesimpulan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkesimpulan.setColumns(20);
        Tkesimpulan.setRows(5);
        Tkesimpulan.setName("Tkesimpulan"); // NOI18N
        Tkesimpulan.setPreferredSize(new java.awt.Dimension(162, 700));
        Tkesimpulan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesimpulanKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Tkesimpulan);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(136, 594, 740, 70);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("PENGKAJIAN SOSIAL DAN PSIKOLOGIS : ");
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(0, 667, 250, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Status Pernikahan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 688, 135, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Keluarga Terdekat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(360, 688, 120, 23);

        TsttsNikah.setEditable(false);
        TsttsNikah.setBackground(new java.awt.Color(245, 250, 240));
        TsttsNikah.setForeground(new java.awt.Color(0, 0, 0));
        TsttsNikah.setName("TsttsNikah"); // NOI18N
        FormInput.add(TsttsNikah);
        TsttsNikah.setBounds(136, 688, 220, 23);

        TKlgDekat.setBackground(new java.awt.Color(245, 250, 240));
        TKlgDekat.setForeground(new java.awt.Color(0, 0, 0));
        TKlgDekat.setName("TKlgDekat"); // NOI18N
        TKlgDekat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TKlgDekatKeyPressed(evt);
            }
        });
        FormInput.add(TKlgDekat);
        TKlgDekat.setBounds(482, 688, 170, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Hubungan : ");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(654, 688, 70, 23);

        Thubungan.setBackground(new java.awt.Color(245, 250, 240));
        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(727, 688, 170, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("No. Telpn : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 716, 135, 23);

        TnoTelp.setEditable(false);
        TnoTelp.setBackground(new java.awt.Color(245, 250, 240));
        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(136, 716, 150, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Tinggal Dengan  : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(286, 716, 100, 23);

        cmbTinggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Orang Tua", "Suami", "Istri", "Suami/Istri", "Anak", "Sendiri", "Lainnya" }));
        cmbTinggal.setName("cmbTinggal"); // NOI18N
        cmbTinggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinggalActionPerformed(evt);
            }
        });
        FormInput.add(cmbTinggal);
        cmbTinggal.setBounds(390, 716, 86, 23);

        TtglDenganLain.setBackground(new java.awt.Color(245, 250, 240));
        TtglDenganLain.setForeground(new java.awt.Color(0, 0, 0));
        TtglDenganLain.setName("TtglDenganLain"); // NOI18N
        TtglDenganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtglDenganLainKeyPressed(evt);
            }
        });
        FormInput.add(TtglDenganLain);
        TtglDenganLain.setBounds(482, 716, 290, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Kegiatan Ibadah : ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(286, 744, 100, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Curiga Penganiayaan / Penelantaran : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 744, 200, 23);

        cmbCuriga.setForeground(new java.awt.Color(0, 0, 0));
        cmbCuriga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbCuriga.setName("cmbCuriga"); // NOI18N
        FormInput.add(cmbCuriga);
        cmbCuriga.setBounds(204, 744, 60, 23);

        ChkIbadah.setBackground(new java.awt.Color(255, 255, 250));
        ChkIbadah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIbadah.setForeground(new java.awt.Color(0, 0, 0));
        ChkIbadah.setText("Membutuhkan Bantuan Dalam Beribadah");
        ChkIbadah.setBorderPainted(true);
        ChkIbadah.setBorderPaintedFlat(true);
        ChkIbadah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIbadah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIbadah.setName("ChkIbadah"); // NOI18N
        ChkIbadah.setOpaque(false);
        ChkIbadah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIbadah);
        ChkIbadah.setBounds(390, 744, 225, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Status Emosional : ");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(624, 744, 100, 23);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Tidak Semangat", "Rasa Tertekan", "Depresi", "Cemas", "Sulit Tidur", "Cepat Lelah", "Sulit Konsentrasi", "Merasa Bersalah" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(727, 744, 113, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 772, 650, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Skor :");
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(680, 772, 40, 23);

        cmbGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Yakin (ada tanda : baju menjadi longgar)", "Ya ada penurunan BB sebanyak :" }));
        cmbGizi1.setName("cmbGizi1"); // NOI18N
        cmbGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi1ActionPerformed(evt);
            }
        });
        FormInput.add(cmbGizi1);
        cmbGizi1.setBounds(360, 800, 310, 23);

        skorGizi1.setEditable(false);
        skorGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi1.setText("0");
        skorGizi1.setFocusTraversalPolicyProvider(true);
        skorGizi1.setName("skorGizi1"); // NOI18N
        FormInput.add(skorGizi1);
        skorGizi1.setBounds(680, 800, 40, 23);

        cmbYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbYaGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "> 15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbYaGizi1.setName("cmbYaGizi1"); // NOI18N
        cmbYaGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYaGizi1ActionPerformed(evt);
            }
        });
        FormInput.add(cmbYaGizi1);
        cmbYaGizi1.setBounds(360, 828, 310, 23);

        skorYaGizi1.setEditable(false);
        skorYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGizi1.setText("0");
        skorYaGizi1.setFocusTraversalPolicyProvider(true);
        skorYaGizi1.setName("skorYaGizi1"); // NOI18N
        FormInput.add(skorYaGizi1);
        skorYaGizi1.setBounds(680, 828, 40, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?");
        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 856, 640, 23);

        cmbGizi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGizi2.setName("cmbGizi2"); // NOI18N
        cmbGizi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi2ActionPerformed(evt);
            }
        });
        FormInput.add(cmbGizi2);
        cmbGizi2.setBounds(605, 884, 65, 23);

        skorGizi2.setEditable(false);
        skorGizi2.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi2.setText("0");
        skorGizi2.setFocusTraversalPolicyProvider(true);
        skorGizi2.setName("skorGizi2"); // NOI18N
        FormInput.add(skorGizi2);
        skorGizi2.setBounds(680, 884, 40, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(600, 912, 70, 23);

        TotSkorGizi.setEditable(false);
        TotSkorGizi.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGizi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGizi.setText("0");
        TotSkorGizi.setFocusTraversalPolicyProvider(true);
        TotSkorGizi.setName("TotSkorGizi"); // NOI18N
        FormInput.add(TotSkorGizi);
        TotSkorGizi.setBounds(680, 912, 40, 23);

        kesimpulanGizi.setEditable(false);
        kesimpulanGizi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGizi.setColumns(20);
        kesimpulanGizi.setRows(5);
        kesimpulanGizi.setName("kesimpulanGizi"); // NOI18N
        FormInput.add(kesimpulanGizi);
        kesimpulanGizi.setBounds(38, 884, 350, 50);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("ASESMEN NYERI :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 940, 135, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(30, 960, 540, 230);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Onset");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1200, 100, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText(": Nyeri atau ketidaknyamanan saat muncul");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(110, 1200, 220, 23);

        Tonset.setBackground(new java.awt.Color(245, 250, 240));
        Tonset.setForeground(new java.awt.Color(0, 0, 0));
        Tonset.setName("Tonset"); // NOI18N
        Tonset.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TonsetKeyPressed(evt);
            }
        });
        FormInput.add(Tonset);
        Tonset.setBounds(335, 1200, 600, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Provocation");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1228, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText(": Faktor yang memperburuk rasa nyeri");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(110, 1228, 220, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        FormInput.add(cmbProvo);
        cmbProvo.setBounds(335, 1228, 80, 23);

        Tprovo.setBackground(new java.awt.Color(245, 250, 240));
        Tprovo.setForeground(new java.awt.Color(0, 0, 0));
        Tprovo.setName("Tprovo"); // NOI18N
        Tprovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprovoKeyPressed(evt);
            }
        });
        FormInput.add(Tprovo);
        Tprovo.setBounds(420, 1228, 515, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Quality");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1256, 100, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText(": Rasa nyeri seperti");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(110, 1256, 220, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        FormInput.add(cmbQuality);
        cmbQuality.setBounds(335, 1256, 81, 23);

        Tquality.setBackground(new java.awt.Color(245, 250, 240));
        Tquality.setForeground(new java.awt.Color(0, 0, 0));
        Tquality.setName("Tquality"); // NOI18N
        Tquality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqualityKeyPressed(evt);
            }
        });
        FormInput.add(Tquality);
        Tquality.setBounds(420, 1256, 515, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Radiation");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 1284, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText(": Nyeri menjalar ke bagian tubuh yang lain");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(110, 1284, 220, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        FormInput.add(cmbRadia);
        cmbRadia.setBounds(335, 1284, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Severity");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1312, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText(": Tingkat keparahan nyeri");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(110, 1312, 220, 23);

        cmbSever.setForeground(new java.awt.Color(0, 0, 0));
        cmbSever.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSever.setName("cmbSever"); // NOI18N
        FormInput.add(cmbSever);
        cmbSever.setBounds(335, 1312, 86, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Time");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1340, 100, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText(": Nyeri berlangsung");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(110, 1340, 220, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        FormInput.add(cmbTime);
        cmbTime.setBounds(335, 1340, 105, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Lama  : ");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(440, 1340, 50, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        FormInput.add(cmbLama);
        cmbLama.setBounds(495, 1340, 86, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Relief");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1368, 100, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText(": Yang membuat nyeri berkurang");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(110, 1368, 220, 23);

        cmbRelief.setForeground(new java.awt.Color(0, 0, 0));
        cmbRelief.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kompres Hangat", "Kompres Dingin", "Farmako Terapi", "Lainnya" }));
        cmbRelief.setName("cmbRelief"); // NOI18N
        cmbRelief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReliefActionPerformed(evt);
            }
        });
        FormInput.add(cmbRelief);
        cmbRelief.setBounds(335, 1368, 112, 23);

        Trelief.setBackground(new java.awt.Color(245, 250, 240));
        Trelief.setForeground(new java.awt.Color(0, 0, 0));
        Trelief.setName("Trelief"); // NOI18N
        Trelief.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreliefKeyPressed(evt);
            }
        });
        FormInput.add(Trelief);
        Trelief.setBounds(454, 1368, 482, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Associated Sign");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1396, 100, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText(": Efek dari nyeri");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(110, 1396, 220, 23);

        cmbAsso.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Mual / Muntah", "Gangguan Tidur", "Nafsu Makan Menurun", "Emosi", "Aktivitas Berkurang", "Lainnya" }));
        cmbAsso.setName("cmbAsso"); // NOI18N
        cmbAsso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAssoActionPerformed(evt);
            }
        });
        FormInput.add(cmbAsso);
        cmbAsso.setBounds(335, 1396, 139, 23);

        Tasso.setBackground(new java.awt.Color(245, 250, 240));
        Tasso.setForeground(new java.awt.Color(0, 0, 0));
        Tasso.setName("Tasso"); // NOI18N
        Tasso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TassoKeyPressed(evt);
            }
        });
        FormInput.add(Tasso);
        Tasso.setBounds(480, 1396, 455, 23);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH MORSE : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        Scroll8.setName("Scroll8"); // NOI18N

        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        tbFaktorResiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaktorResikoMouseClicked(evt);
            }
        });
        tbFaktorResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbFaktorResiko);

        FormInput.add(Scroll8);
        Scroll8.setBounds(10, 1424, 650, 143);

        kesimpulanResikoJatuh.setEditable(false);
        kesimpulanResikoJatuh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skor Resiko Jatuh : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResikoJatuh.setColumns(20);
        kesimpulanResikoJatuh.setRows(5);
        kesimpulanResikoJatuh.setName("kesimpulanResikoJatuh"); // NOI18N
        FormInput.add(kesimpulanResikoJatuh);
        kesimpulanResikoJatuh.setBounds(663, 1424, 270, 110);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Total Skor Resiko Jatuh :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(663, 1541, 130, 23);

        TotSkorRJ.setEditable(false);
        TotSkorRJ.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorRJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorRJ.setText("0");
        TotSkorRJ.setFocusTraversalPolicyProvider(true);
        TotSkorRJ.setName("TotSkorRJ"); // NOI18N
        FormInput.add(TotSkorRJ);
        TotSkorRJ.setBounds(799, 1541, 40, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(16, 1569, 60, 23);

        TCariResiko.setForeground(new java.awt.Color(0, 0, 0));
        TCariResiko.setToolTipText("Alt+C");
        TCariResiko.setName("TCariResiko"); // NOI18N
        TCariResiko.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariResikoKeyPressed(evt);
            }
        });
        FormInput.add(TCariResiko);
        TCariResiko.setBounds(80, 1569, 215, 23);

        BtnCariResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariResiko.setMnemonic('1');
        BtnCariResiko.setToolTipText("Alt+1");
        BtnCariResiko.setName("BtnCariResiko"); // NOI18N
        BtnCariResiko.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariResikoActionPerformed(evt);
            }
        });
        BtnCariResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariResikoKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariResiko);
        BtnCariResiko.setBounds(299, 1569, 28, 23);

        BtnAllResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllResiko.setMnemonic('2');
        BtnAllResiko.setToolTipText("2Alt+2");
        BtnAllResiko.setName("BtnAllResiko"); // NOI18N
        BtnAllResiko.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllResikoActionPerformed(evt);
            }
        });
        BtnAllResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllResikoKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllResiko);
        BtnAllResiko.setBounds(331, 1569, 28, 23);

        Scroll9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO DECUBITUS NORTON SCALE : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        Scroll9.setName("Scroll9"); // NOI18N

        tbFaktorDecu.setName("tbFaktorDecu"); // NOI18N
        tbFaktorDecu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaktorDecuMouseClicked(evt);
            }
        });
        tbFaktorDecu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaktorDecuKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFaktorDecuKeyReleased(evt);
            }
        });
        Scroll9.setViewportView(tbFaktorDecu);

        FormInput.add(Scroll9);
        Scroll9.setBounds(10, 1756, 390, 143);

        kesimpulanResikoDecu.setEditable(false);
        kesimpulanResikoDecu.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skor Decubitus : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResikoDecu.setColumns(20);
        kesimpulanResikoDecu.setRows(5);
        kesimpulanResikoDecu.setName("kesimpulanResikoDecu"); // NOI18N
        FormInput.add(kesimpulanResikoDecu);
        kesimpulanResikoDecu.setBounds(405, 1756, 270, 80);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Total Skor Decubitus : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(405, 1843, 120, 23);

        TotSkorDecu.setEditable(false);
        TotSkorDecu.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorDecu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorDecu.setText("0");
        TotSkorDecu.setFocusTraversalPolicyProvider(true);
        TotSkorDecu.setName("TotSkorDecu"); // NOI18N
        FormInput.add(TotSkorDecu);
        TotSkorDecu.setBounds(528, 1843, 40, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Key Word :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(405, 1871, 60, 23);

        TCariDecu.setForeground(new java.awt.Color(0, 0, 0));
        TCariDecu.setToolTipText("Alt+C");
        TCariDecu.setName("TCariDecu"); // NOI18N
        TCariDecu.setPreferredSize(new java.awt.Dimension(140, 23));
        TCariDecu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariDecuKeyPressed(evt);
            }
        });
        FormInput.add(TCariDecu);
        TCariDecu.setBounds(470, 1871, 200, 23);

        BtnCariDecu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariDecu.setMnemonic('1');
        BtnCariDecu.setToolTipText("Alt+1");
        BtnCariDecu.setName("BtnCariDecu"); // NOI18N
        BtnCariDecu.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCariDecu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariDecuActionPerformed(evt);
            }
        });
        BtnCariDecu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariDecuKeyPressed(evt);
            }
        });
        FormInput.add(BtnCariDecu);
        BtnCariDecu.setBounds(675, 1871, 28, 23);

        BtnAllDecu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllDecu.setMnemonic('2');
        BtnAllDecu.setToolTipText("2Alt+2");
        BtnAllDecu.setName("BtnAllDecu"); // NOI18N
        BtnAllDecu.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAllDecu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllDecuActionPerformed(evt);
            }
        });
        BtnAllDecu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllDecuKeyPressed(evt);
            }
        });
        FormInput.add(BtnAllDecu);
        BtnAllDecu.setBounds(710, 1871, 28, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Tindakan Pencegahan Resiko Jatuh :");
        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1597, 230, 23);

        TabPencegahanDewasa.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanDewasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanDewasa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanDewasa.setName("TabPencegahanDewasa"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        cegahA.setEditable(false);
        cegahA.setBackground(new java.awt.Color(255, 255, 255));
        cegahA.setColumns(20);
        cegahA.setRows(5);
        cegahA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        cegahA.setName("cegahA"); // NOI18N
        cegahA.setOpaque(true);
        panelBiasa8.add(cegahA, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        cegahB.setEditable(false);
        cegahB.setBackground(new java.awt.Color(255, 255, 255));
        cegahB.setColumns(20);
        cegahB.setRows(5);
        cegahB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        cegahB.setName("cegahB"); // NOI18N
        cegahB.setOpaque(true);
        panelBiasa9.add(cegahB, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        cegahC.setEditable(false);
        cegahC.setBackground(new java.awt.Color(255, 255, 255));
        cegahC.setColumns(20);
        cegahC.setRows(5);
        cegahC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        cegahC.setName("cegahC"); // NOI18N
        cegahC.setOpaque(true);
        panelBiasa10.add(cegahC, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        FormInput.add(TabPencegahanDewasa);
        TabPencegahanDewasa.setBounds(25, 1619, 565, 128);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(580, 1172, 80, 23);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        FormInput.add(cmbSkala);
        cmbSkala.setBounds(665, 1172, 45, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Tindakan Pencegahan Resiko Jatuh :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(600, 1717, 190, 23);

        cmbTindakanCegah.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakanCegah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "A", "B", "C" }));
        cmbTindakanCegah.setName("cmbTindakanCegah"); // NOI18N
        cmbTindakanCegah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanCegahActionPerformed(evt);
            }
        });
        FormInput.add(cmbTindakanCegah);
        cmbTindakanCegah.setBounds(795, 1717, 40, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("SKALA KEKUATAN OTOT - NILAI : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1903, 200, 23);

        cmbNilai.setForeground(new java.awt.Color(0, 0, 0));
        cmbNilai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "5/5", "4/5", "3/5", "2/5", "1/5", "0/5" }));
        cmbNilai.setName("cmbNilai"); // NOI18N
        cmbNilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNilaiActionPerformed(evt);
            }
        });
        FormInput.add(cmbNilai);
        cmbNilai.setBounds(204, 1903, 49, 23);

        Tketerangan.setEditable(false);
        Tketerangan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Keterangan : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        Tketerangan.setColumns(20);
        Tketerangan.setRows(5);
        Tketerangan.setName("Tketerangan"); // NOI18N
        FormInput.add(Tketerangan);
        Tketerangan.setBounds(261, 1903, 670, 60);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("KEBUTUHAN EDUKASI (DIKAJI PADA PASIEN DAN KELUARGA) :");
        jLabel88.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1967, 380, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Manajemen : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1990, 100, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Rehabilitasi : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 2018, 100, 23);

        ChkObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkObat.setText("Obat - Obatan");
        ChkObat.setBorderPainted(true);
        ChkObat.setBorderPaintedFlat(true);
        ChkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObat.setName("ChkObat"); // NOI18N
        ChkObat.setOpaque(false);
        ChkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkObat);
        ChkObat.setBounds(104, 1990, 100, 23);

        ChkPerawatan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerawatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerawatan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerawatan.setText("Perawatan Luka");
        ChkPerawatan.setBorderPainted(true);
        ChkPerawatan.setBorderPaintedFlat(true);
        ChkPerawatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerawatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerawatan.setName("ChkPerawatan"); // NOI18N
        ChkPerawatan.setOpaque(false);
        ChkPerawatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerawatan);
        ChkPerawatan.setBounds(223, 1990, 104, 23);

        TmanajemenLain.setForeground(new java.awt.Color(0, 0, 0));
        TmanajemenLain.setToolTipText("Alt+C");
        TmanajemenLain.setName("TmanajemenLain"); // NOI18N
        TmanajemenLain.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TmanajemenLain);
        TmanajemenLain.setBounds(400, 1990, 530, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Lainnya : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(337, 1990, 60, 23);

        ChkManajemen.setBackground(new java.awt.Color(255, 255, 250));
        ChkManajemen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkManajemen.setForeground(new java.awt.Color(0, 0, 0));
        ChkManajemen.setText("Manajemen Nyeri");
        ChkManajemen.setBorderPainted(true);
        ChkManajemen.setBorderPaintedFlat(true);
        ChkManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkManajemen.setName("ChkManajemen"); // NOI18N
        ChkManajemen.setOpaque(false);
        ChkManajemen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkManajemen);
        ChkManajemen.setBounds(104, 2018, 110, 23);

        ChkDiet.setBackground(new java.awt.Color(255, 255, 250));
        ChkDiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDiet.setForeground(new java.awt.Color(0, 0, 0));
        ChkDiet.setText("Diet dan Nutrisi");
        ChkDiet.setBorderPainted(true);
        ChkDiet.setBorderPaintedFlat(true);
        ChkDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDiet.setName("ChkDiet"); // NOI18N
        ChkDiet.setOpaque(false);
        ChkDiet.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDiet);
        ChkDiet.setBounds(223, 2018, 104, 23);

        ChkFisioterapi.setBackground(new java.awt.Color(255, 255, 250));
        ChkFisioterapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkFisioterapi.setForeground(new java.awt.Color(0, 0, 0));
        ChkFisioterapi.setText("Fisioterapi");
        ChkFisioterapi.setBorderPainted(true);
        ChkFisioterapi.setBorderPaintedFlat(true);
        ChkFisioterapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkFisioterapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkFisioterapi.setName("ChkFisioterapi"); // NOI18N
        ChkFisioterapi.setOpaque(false);
        ChkFisioterapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkFisioterapi);
        ChkFisioterapi.setBounds(340, 2018, 80, 23);

        TrehabLain.setForeground(new java.awt.Color(0, 0, 0));
        TrehabLain.setToolTipText("Alt+C");
        TrehabLain.setName("TrehabLain"); // NOI18N
        TrehabLain.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TrehabLain);
        TrehabLain.setBounds(490, 2018, 440, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Lainnya : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(428, 2018, 60, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("DAFTAR MASALAH KEPERAWATAN :");
        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(0, 2046, 230, 23);

        ChkHipertermi.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertermi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertermi.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertermi.setText("Hipertermi");
        ChkHipertermi.setBorderPainted(true);
        ChkHipertermi.setBorderPaintedFlat(true);
        ChkHipertermi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertermi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertermi.setName("ChkHipertermi"); // NOI18N
        ChkHipertermi.setOpaque(false);
        ChkHipertermi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertermi);
        ChkHipertermi.setBounds(30, 2069, 110, 23);

        ChkNyeri.setBackground(new java.awt.Color(255, 255, 250));
        ChkNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNyeri.setForeground(new java.awt.Color(0, 0, 0));
        ChkNyeri.setText("Nyeri");
        ChkNyeri.setBorderPainted(true);
        ChkNyeri.setBorderPaintedFlat(true);
        ChkNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNyeri.setName("ChkNyeri"); // NOI18N
        ChkNyeri.setOpaque(false);
        ChkNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNyeri);
        ChkNyeri.setBounds(30, 2097, 110, 23);

        ChkResiko.setBackground(new java.awt.Color(255, 255, 250));
        ChkResiko.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkResiko.setForeground(new java.awt.Color(0, 0, 0));
        ChkResiko.setText("(Resiko) Kurang Volume Cairan");
        ChkResiko.setBorderPainted(true);
        ChkResiko.setBorderPaintedFlat(true);
        ChkResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResiko.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResiko.setName("ChkResiko"); // NOI18N
        ChkResiko.setOpaque(false);
        ChkResiko.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkResiko);
        ChkResiko.setBounds(30, 2125, 180, 23);

        ChkKelebihan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKelebihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKelebihan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKelebihan.setText("Kelebihan Volume Cairan");
        ChkKelebihan.setBorderPainted(true);
        ChkKelebihan.setBorderPaintedFlat(true);
        ChkKelebihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKelebihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKelebihan.setName("ChkKelebihan"); // NOI18N
        ChkKelebihan.setOpaque(false);
        ChkKelebihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKelebihan);
        ChkKelebihan.setBounds(30, 2153, 180, 23);

        ChkBersihkan.setBackground(new java.awt.Color(255, 255, 250));
        ChkBersihkan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBersihkan.setForeground(new java.awt.Color(0, 0, 0));
        ChkBersihkan.setText("Bersihkan Jalan Nafas Tidak Efektif");
        ChkBersihkan.setBorderPainted(true);
        ChkBersihkan.setBorderPaintedFlat(true);
        ChkBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBersihkan.setName("ChkBersihkan"); // NOI18N
        ChkBersihkan.setOpaque(false);
        ChkBersihkan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBersihkan);
        ChkBersihkan.setBounds(30, 2181, 200, 23);

        ChkPola.setBackground(new java.awt.Color(255, 255, 250));
        ChkPola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPola.setForeground(new java.awt.Color(0, 0, 0));
        ChkPola.setText("Pola Nafas Tidak Efektif");
        ChkPola.setBorderPainted(true);
        ChkPola.setBorderPaintedFlat(true);
        ChkPola.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPola.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPola.setName("ChkPola"); // NOI18N
        ChkPola.setOpaque(false);
        ChkPola.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPola);
        ChkPola.setBounds(30, 2209, 150, 23);

        ChkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangguan.setText("Gangguan Pertukaran Gas");
        ChkGangguan.setBorderPainted(true);
        ChkGangguan.setBorderPaintedFlat(true);
        ChkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangguan.setName("ChkGangguan"); // NOI18N
        ChkGangguan.setOpaque(false);
        ChkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangguan);
        ChkGangguan.setBounds(250, 2069, 160, 23);

        ChkCemas.setBackground(new java.awt.Color(255, 255, 250));
        ChkCemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCemas.setForeground(new java.awt.Color(0, 0, 0));
        ChkCemas.setText("Cemas");
        ChkCemas.setBorderPainted(true);
        ChkCemas.setBorderPaintedFlat(true);
        ChkCemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCemas.setName("ChkCemas"); // NOI18N
        ChkCemas.setOpaque(false);
        ChkCemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCemas);
        ChkCemas.setBounds(250, 2097, 70, 23);

        ChkKetidakseimbangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKetidakseimbangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKetidakseimbangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKetidakseimbangan.setText("Ketidakseimbangan Nutrisi : Kurang Dari Kebutuhan Tubuh");
        ChkKetidakseimbangan.setBorderPainted(true);
        ChkKetidakseimbangan.setBorderPaintedFlat(true);
        ChkKetidakseimbangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKetidakseimbangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKetidakseimbangan.setName("ChkKetidakseimbangan"); // NOI18N
        ChkKetidakseimbangan.setOpaque(false);
        ChkKetidakseimbangan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKetidakseimbangan);
        ChkKetidakseimbangan.setBounds(250, 2125, 317, 23);

        ChkPerubahan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPerubahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPerubahan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPerubahan.setText("Perubahan Perfusi Jaringan Tidak Efektif");
        ChkPerubahan.setBorderPainted(true);
        ChkPerubahan.setBorderPaintedFlat(true);
        ChkPerubahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPerubahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPerubahan.setName("ChkPerubahan"); // NOI18N
        ChkPerubahan.setOpaque(false);
        ChkPerubahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPerubahan);
        ChkPerubahan.setBounds(250, 2153, 230, 23);

        ChkPenurunan.setBackground(new java.awt.Color(255, 255, 250));
        ChkPenurunan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        ChkPenurunan.setText("Penurunan Curah Jantung");
        ChkPenurunan.setBorderPainted(true);
        ChkPenurunan.setBorderPaintedFlat(true);
        ChkPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPenurunan.setName("ChkPenurunan"); // NOI18N
        ChkPenurunan.setOpaque(false);
        ChkPenurunan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPenurunan);
        ChkPenurunan.setBounds(250, 2181, 160, 23);

        ChkKerusakan.setBackground(new java.awt.Color(255, 255, 250));
        ChkKerusakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKerusakan.setForeground(new java.awt.Color(0, 0, 0));
        ChkKerusakan.setText("Kerusakan Integritas Kulit / Jaringan");
        ChkKerusakan.setBorderPainted(true);
        ChkKerusakan.setBorderPaintedFlat(true);
        ChkKerusakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKerusakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKerusakan.setName("ChkKerusakan"); // NOI18N
        ChkKerusakan.setOpaque(false);
        ChkKerusakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKerusakan);
        ChkKerusakan.setBounds(250, 2209, 210, 23);

        ChkIntoleransi.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntoleransi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntoleransi.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntoleransi.setText("Intoleransi Aktifitas");
        ChkIntoleransi.setBorderPainted(true);
        ChkIntoleransi.setBorderPaintedFlat(true);
        ChkIntoleransi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntoleransi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntoleransi.setName("ChkIntoleransi"); // NOI18N
        ChkIntoleransi.setOpaque(false);
        ChkIntoleransi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIntoleransi);
        ChkIntoleransi.setBounds(578, 2069, 130, 23);

        ChkKurang.setBackground(new java.awt.Color(255, 255, 250));
        ChkKurang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKurang.setForeground(new java.awt.Color(0, 0, 0));
        ChkKurang.setText("Kurang Perawatan Diri");
        ChkKurang.setBorderPainted(true);
        ChkKurang.setBorderPaintedFlat(true);
        ChkKurang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKurang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKurang.setName("ChkKurang"); // NOI18N
        ChkKurang.setOpaque(false);
        ChkKurang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKurang);
        ChkKurang.setBounds(578, 2097, 140, 23);

        ChkMPP.setBackground(new java.awt.Color(255, 255, 250));
        ChkMPP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMPP.setForeground(new java.awt.Color(0, 0, 0));
        ChkMPP.setText("Memerlukan Manajer Pelayanan Pasien");
        ChkMPP.setBorderPainted(true);
        ChkMPP.setBorderPaintedFlat(true);
        ChkMPP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMPP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMPP.setName("ChkMPP"); // NOI18N
        ChkMPP.setOpaque(false);
        ChkMPP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMPP);
        ChkMPP.setBounds(578, 2125, 220, 23);

        ChkDP.setBackground(new java.awt.Color(255, 255, 250));
        ChkDP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDP.setForeground(new java.awt.Color(0, 0, 0));
        ChkDP.setText("Memerlukan Discharge Planning");
        ChkDP.setBorderPainted(true);
        ChkDP.setBorderPaintedFlat(true);
        ChkDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDP.setName("ChkDP"); // NOI18N
        ChkDP.setOpaque(false);
        ChkDP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDP);
        ChkDP.setBounds(578, 2153, 190, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel94.setText("Masalah Keperawatan Lain : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(578, 2176, 150, 23);

        scrollPane5.setName("scrollPane5"); // NOI18N

        TmasalahLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TmasalahLain.setColumns(20);
        TmasalahLain.setRows(5);
        TmasalahLain.setName("TmasalahLain"); // NOI18N
        TmasalahLain.setPreferredSize(new java.awt.Dimension(162, 700));
        TmasalahLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmasalahLainKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TmasalahLain);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(578, 2198, 340, 85);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tgl. Asesmen : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 2237, 100, 23);

        TtglAses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-12-2023" }));
        TtglAses.setDisplayFormat("dd-MM-yyyy");
        TtglAses.setName("TtglAses"); // NOI18N
        TtglAses.setOpaque(false);
        TtglAses.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAses);
        TtglAses.setBounds(103, 2237, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(195, 2237, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(238, 2237, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(290, 2237, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(343, 2237, 45, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama Perawat : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 2265, 100, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(103, 2265, 430, 23);

        BtnPerawat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawat.setMnemonic('2');
        BtnPerawat.setToolTipText("Alt+2");
        BtnPerawat.setName("BtnPerawat"); // NOI18N
        BtnPerawat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawat);
        BtnPerawat.setBounds(540, 2265, 28, 23);

        Tpernafasan.setBackground(new java.awt.Color(245, 250, 240));
        Tpernafasan.setForeground(new java.awt.Color(0, 0, 0));
        Tpernafasan.setName("Tpernafasan"); // NOI18N
        FormInput.add(Tpernafasan);
        Tpernafasan.setBounds(213, 454, 130, 23);

        Tpenglihatan.setBackground(new java.awt.Color(245, 250, 240));
        Tpenglihatan.setForeground(new java.awt.Color(0, 0, 0));
        Tpenglihatan.setName("Tpenglihatan"); // NOI18N
        FormInput.add(Tpenglihatan);
        Tpenglihatan.setBounds(227, 482, 115, 23);

        Tpendengaran.setBackground(new java.awt.Color(245, 250, 240));
        Tpendengaran.setForeground(new java.awt.Color(0, 0, 0));
        Tpendengaran.setName("Tpendengaran"); // NOI18N
        FormInput.add(Tpendengaran);
        Tpendengaran.setBounds(227, 510, 115, 23);

        Tmulut.setBackground(new java.awt.Color(245, 250, 240));
        Tmulut.setForeground(new java.awt.Color(0, 0, 0));
        Tmulut.setName("Tmulut"); // NOI18N
        FormInput.add(Tmulut);
        Tmulut.setBounds(227, 538, 115, 23);

        Treflek.setBackground(new java.awt.Color(245, 250, 240));
        Treflek.setForeground(new java.awt.Color(0, 0, 0));
        Treflek.setName("Treflek"); // NOI18N
        FormInput.add(Treflek);
        Treflek.setBounds(213, 566, 130, 23);

        Tbicara.setBackground(new java.awt.Color(245, 250, 240));
        Tbicara.setForeground(new java.awt.Color(0, 0, 0));
        Tbicara.setName("Tbicara"); // NOI18N
        FormInput.add(Tbicara);
        Tbicara.setBounds(539, 454, 152, 23);

        Tdefekasi.setBackground(new java.awt.Color(245, 250, 240));
        Tdefekasi.setForeground(new java.awt.Color(0, 0, 0));
        Tdefekasi.setName("Tdefekasi"); // NOI18N
        FormInput.add(Tdefekasi);
        Tdefekasi.setBounds(576, 482, 115, 23);

        Tmiksi.setBackground(new java.awt.Color(245, 250, 240));
        Tmiksi.setForeground(new java.awt.Color(0, 0, 0));
        Tmiksi.setName("Tmiksi"); // NOI18N
        FormInput.add(Tmiksi);
        Tmiksi.setBounds(576, 510, 115, 23);

        Tgastro.setBackground(new java.awt.Color(245, 250, 240));
        Tgastro.setForeground(new java.awt.Color(0, 0, 0));
        Tgastro.setName("Tgastro"); // NOI18N
        FormInput.add(Tgastro);
        Tgastro.setBounds(526, 538, 165, 23);

        Tpola.setBackground(new java.awt.Color(245, 250, 240));
        Tpola.setForeground(new java.awt.Color(0, 0, 0));
        Tpola.setName("Tpola"); // NOI18N
        FormInput.add(Tpola);
        Tpola.setBounds(532, 566, 159, 23);

        BtnIMT.setForeground(new java.awt.Color(0, 0, 0));
        BtnIMT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnIMT.setText("Hitung IMT : ");
        BtnIMT.setToolTipText("");
        BtnIMT.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnIMT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnIMT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnIMT.setName("BtnIMT"); // NOI18N
        BtnIMT.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnIMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnIMTActionPerformed(evt);
            }
        });
        BtnIMT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnIMTKeyPressed(evt);
            }
        });
        FormInput.add(BtnIMT);
        BtnIMT.setBounds(375, 411, 120, 23);

        TnmAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiObat.setName("TnmAlergiObat"); // NOI18N
        TnmAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiObatKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiObat);
        TnmAlergiObat.setBounds(136, 150, 330, 23);

        TnmAlergiMakanan.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiMakanan.setName("TnmAlergiMakanan"); // NOI18N
        TnmAlergiMakanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiMakananKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiMakanan);
        TnmAlergiMakanan.setBounds(136, 178, 330, 23);

        TnmAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TnmAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TnmAlergiLain.setName("TnmAlergiLain"); // NOI18N
        TnmAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmAlergiLainKeyPressed(evt);
            }
        });
        FormInput.add(TnmAlergiLain);
        TnmAlergiLain.setBounds(136, 206, 330, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Asesmen", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmen.setAutoCreateRowSorter(true);
        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setName("tbAsesmen"); // NOI18N
        tbAsesmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenMouseClicked(evt);
            }
        });
        tbAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmen);

        internalFrame4.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-12-2023" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-12-2023" }));
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

        TabRawat.addTab("Data Asesmen", internalFrame4);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("penilaian_awal_keperawatan_dewasa_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 115, new String[]{
                        TNoRw.getText(), kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
                        alergiObat, TreaksiObat.getText(), alergiMakanan, TreaksiMakanan.getText(), alergiLain, TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(),
                        TriwPenyktSkg.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(),
                        TbbBelum.getText(), TbbMasuk.getText(), Ttb.getText(), Timt.getText(), Tcrt.getText(), Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), Tpernafasan.getText(),
                        cmbPenglihatan.getSelectedItem().toString(), Tpenglihatan.getText(), cmbPendengaran.getSelectedItem().toString(), Tpendengaran.getText(), cmbMulut.getSelectedItem().toString(),
                        Tmulut.getText(), cmbReflek.getSelectedItem().toString(), Treflek.getText(), cmbBicara.getSelectedItem().toString(), Tbicara.getText(), cmbDefekasi.getSelectedItem().toString(),
                        Tdefekasi.getText(), cmbMiksi.getSelectedItem().toString(), Tmiksi.getText(), cmbGastro.getSelectedItem().toString(), Tgastro.getText(), cmbPola.getSelectedItem().toString(),
                        Tpola.getText(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(), cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(),
                        cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(), Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(),
                        cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(), cmbGizi1.getSelectedItem().toString(), cmbYaGizi1.getSelectedItem().toString(),
                        cmbGizi2.getSelectedItem().toString(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
                        cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
                        cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
                        cmbTindakanCegah.getSelectedItem().toString(), cmbNilai.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri, diet, fisio,
                        TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan, intoleransi,
                        kurang, perluMPP, perluDP, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), nip, cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        Sequel.cariIsi("select now()"), TnmAlergiObat.getText(), TnmAlergiMakanan.getText(), TnmAlergiLain.getText()
                    }) == true) {
                
                //simpan faktor resiko
                for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                    if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan2("penilaian_awal_keperawatan_dewasa_ranap_resiko", "?,?", 2, new String[]{
                            TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                    }
                }
                
                //simpan resiko decubitus
                for (i = 0; i < tbFaktorDecu.getRowCount(); i++) {
                    if (tbFaktorDecu.getValueAt(i, 0).toString().equals("true")) {
                        Sequel.menyimpan2("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "?,?", 2, new String[]{
                            TNoRw.getText(), tbFaktorDecu.getValueAt(i, 1).toString()});
                    }
                }

                emptTeks();
                TCari.setText(TNoRw.getText());                
                tampil();
                TabRawat.setSelectedIndex(1);
                tampilFaktorResiko();
                tampilResikoDecubitus();
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
        if (tbAsesmen.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString() + " ..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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
        skorFix = "";
        
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("ruangan", Truangan.getText() + ", Tanggal : " + TtglMsk.getSelectedItem().toString() + ", Pukul : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            if (cmbTiba.getSelectedIndex() == 4) {
                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString() + " (" + TtibaLain.getText() + ")");
            } else {
                param.put("tibaDiruang", cmbTiba.getSelectedItem().toString());
            }
            
            param.put("mskMelalui", cmbMasuk.getSelectedItem().toString());
            param.put("keluhan", Tkeluhan.getText());
            param.put("riwayatAlergi", cmbRiwAlergi.getSelectedItem().toString());
            
            if (alergiObat.equals("ya")) {
                param.put("cekObat", "V");
                param.put("alergiObat", TnmAlergiObat.getText() + ", Reaksi : " + TreaksiObat.getText());
            } else {
                param.put("cekObat", "");
                param.put("alergiObat", " - , Reaksi : -");
            }
            
            if (alergiMakanan.equals("ya")) {
                param.put("cekMakan", "V");
                param.put("alergiMakan", TnmAlergiMakanan.getText() + ", Reaksi : " + TreaksiMakanan.getText());
            } else {
                param.put("cekMakan", "");
                param.put("alergiMakan", " - , Reaksi : -");
            }
            
            if (alergiLain.equals("ya")) {
                param.put("cekLain", "V");
                param.put("alergiLain", TnmAlergiLain.getText() + ", Reaksi : " + TreaksiLain.getText());
            } else {
                param.put("cekLain", "");
                param.put("alergiLain", " - , Reaksi : -");
            }
            
            if (gelang.equals("ya")) {
                param.put("gelang", "V");
            } else {
                param.put("gelang", "");
            }
            
            param.put("alergiDiberitau", cmbAlergiDiberitahu.getSelectedItem().toString());
            param.put("riwayatPS", TriwPenyktSkg.getText());
            param.put("kesadaran", Tkesadaran.getText());
            param.put("gcs", Tgcse.getText() + " M : " + Tgcsm.getText() + " V : " + Tgcsv.getText());
            param.put("tensi", Ttensi.getText() + " mmHg");
            param.put("temp", Ttemp.getText() + " °C");
            param.put("hr", Thr.getText() + " x/mnt");
            param.put("rr", Trr.getText() + " x/mnt");
            param.put("BBbelum", TbbBelum.getText() + " Kg.");
            param.put("BBmasuk", TbbMasuk.getText() + " Kg.");
            param.put("tb", Ttb.getText() + " Cm.");
            param.put("imt", Ttb.getText() + " Kg.");
            param.put("crt", Tcrt.getText() + " detik");
            param.put("spo", Tspo.getText() + " %");
            
            if (cmbPernafasan.getSelectedIndex() == 4) {
                param.put("pernafasan", cmbPernafasan.getSelectedItem().toString() + " (" + Tpernafasan.getText() + ")");
            } else {
                param.put("pernafasan", cmbPernafasan.getSelectedItem().toString());
            }
            
            if (cmbPenglihatan.getSelectedIndex() == 4) {
                param.put("penglihatan", cmbPenglihatan.getSelectedItem().toString() + " (" + Tpenglihatan.getText() + ")");
            } else {
                param.put("penglihatan", cmbPenglihatan.getSelectedItem().toString());
            }
            
            if (cmbPendengaran.getSelectedIndex() == 4) {
                param.put("pendengaran", cmbPendengaran.getSelectedItem().toString() + " (" + Tpendengaran.getText() + ")");
            } else {
                param.put("pendengaran", cmbPendengaran.getSelectedItem().toString());
            }
            
            if (cmbMulut.getSelectedIndex() == 3) {
                param.put("mulut", cmbMulut.getSelectedItem().toString() + " (" + Tmulut.getText() + ")");
            } else {
                param.put("mulut", cmbMulut.getSelectedItem().toString());
            }
            
            if (cmbReflek.getSelectedIndex() == 4) {
                param.put("reflek", cmbReflek.getSelectedItem().toString() + " (" + Treflek.getText() + ")");
            } else {
                param.put("reflek", cmbReflek.getSelectedItem().toString());
            }
            
            if (cmbBicara.getSelectedIndex() == 3) {
                param.put("bicara", cmbBicara.getSelectedItem().toString() + " (" + Tbicara.getText() + ")");
            } else {
                param.put("bicara", cmbBicara.getSelectedItem().toString());
            }
            
            if (cmbDefekasi.getSelectedIndex() == 5) {
                param.put("defekasi", cmbDefekasi.getSelectedItem().toString() + " (" + Tdefekasi.getText() + ")");
            } else {
                param.put("defekasi", cmbDefekasi.getSelectedItem().toString());
            }
            
            if (cmbMiksi.getSelectedIndex() == 4) {
                param.put("miksi", cmbMiksi.getSelectedItem().toString() + " (" + Tmiksi.getText() + ")");
            } else {
                param.put("miksi", cmbMiksi.getSelectedItem().toString());
            }
            
            if (cmbGastro.getSelectedIndex() == 5) {
                param.put("gastro", cmbGastro.getSelectedItem().toString() + " (" + Tgastro.getText() + ")");
            } else {
                param.put("gastro", cmbGastro.getSelectedItem().toString());
            }
            
            if (cmbPola.getSelectedIndex() == 3) {
                param.put("pola", cmbPola.getSelectedItem().toString() + " (" + Tpola.getText() + ")");
            } else {
                param.put("pola", cmbPola.getSelectedItem().toString());
            }
            
            param.put("makan", cmbMakan.getSelectedItem().toString());
            param.put("berpakaian", cmbBerpakaian.getSelectedItem().toString());
            param.put("buang", cmbBuang.getSelectedItem().toString());
            param.put("mandi", cmbMandi.getSelectedItem().toString());
            param.put("berpindah", cmbBerpindah.getSelectedItem().toString());
            param.put("kesimpulan", Tkesimpulan.getText());
            param.put("sttsNikah", TsttsNikah.getText());
            param.put("keluarga", TKlgDekat.getText());
            param.put("hubungan", Thubungan.getText());
            param.put("notelp", TnoTelp.getText());
            
            if (cmbTinggal.getSelectedIndex() == 7) {
                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString() + " (" + TtglDenganLain.getText() + ")");
            } else {
                param.put("tinggalDengan", cmbTinggal.getSelectedItem().toString());
            }
            
            param.put("curiga", cmbCuriga.getSelectedItem().toString());
            
            if (ibadah.equals("ya")) {
                param.put("ibadah", "V");
            } else {
                param.put("ibadah", "");
            }
            
            param.put("emosi", cmbStatus.getSelectedItem().toString());

            if (cmbGizi1.getSelectedIndex() == 2) {
                skorFix = "\n" + cmbYaGizi1.getSelectedItem().toString() + "   Skor (" + skorYaGizi1.getText() + ")\n\n";
            } else {
                skorFix = "   Skor (" + skorGizi1.getText() + ")\n\n";
            }
            param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
                    + cmbGizi1.getSelectedItem().toString() + "" + skorFix + ""
                    + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
                    + cmbGizi2.getSelectedItem().toString() + "   Skor (" + skorGizi2.getText() + ")\n"
                    + "_______________________________________________________________________\n"
                    + "Total Skor : (" + TotSkorGizi.getText() + ")\n"
                    + "Kesimpulan Skrining Gizi :\n"
                    + kesimpulanGizi.getText() + "\n");
            param.put("skala", cmbSkala.getSelectedItem().toString());
            param.put("onset", Tonset.getText());

            if (cmbProvo.getSelectedIndex() == 5) {
                param.put("provo", cmbProvo.getSelectedItem().toString() + " (" + Tprovo.getText() + ")");
            } else {
                param.put("provo", cmbProvo.getSelectedItem().toString());
            }
            
            if (cmbQuality.getSelectedIndex() == 9) {
                param.put("quality", cmbQuality.getSelectedItem().toString() + " (" + Tquality.getText() + ")");
            } else {
                param.put("quality", cmbQuality.getSelectedItem().toString());
            }
            
            param.put("radiation", cmbRadia.getSelectedItem().toString());
            param.put("sever", cmbSever.getSelectedItem().toString());
            param.put("time", cmbTime.getSelectedItem().toString() + ", Lama : " + cmbLama.getSelectedItem().toString());
            
            if (cmbRelief.getSelectedIndex() == 4) {
                param.put("relief", cmbRelief.getSelectedItem().toString() + " (" + Trelief.getText() + ")");
            } else {
                param.put("relief", cmbRelief.getSelectedItem().toString());
            }
            
            if (cmbAsso.getSelectedIndex() == 6) {
                param.put("asso", cmbAsso.getSelectedItem().toString() + " (" + Tasso.getText() + ")");
            } else {
                param.put("asso", cmbAsso.getSelectedItem().toString());
            }
            
            //faktor resiko jatuh
            try {
                resikojatuh = "";
                ps3 = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                        + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_resiko pm ON pm.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'Dewasa Ranap' and pm.no_rawat=? ORDER BY pm.kode_resiko");
                try {
                    ps3.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        resikojatuh = rs3.getString("resiko") + "\n" + resikojatuh;
                    }
                    
                    if (resikojatuh.endsWith("\n")) {
                        resikojatuh = resikojatuh.substring(0, resikojatuh.length() - 1);
                    }

                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                } finally {
                    if (rs3 != null) {
                        rs3.close();
                    }
                    if (ps3 != null) {
                        ps3.close();
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            }
            
            param.put("resikoJatuh", resikojatuh);
            param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ.getText() + ")");
            param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh.getText());

            if (cmbTindakanCegah.getSelectedIndex() == 0) {
                param.put("JudultindakanRJ", "Tindakan Pencegahan Resiko Jatuh");
                param.put("IsitindakanRJ", "-");
            } else if (cmbTindakanCegah.getSelectedIndex() == 1) {
                param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                param.put("IsitindakanRJ", cegahA.getText());
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
                param.put("IsitindakanRJ", cegahB.getText());
            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
                param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
                param.put("IsitindakanRJ", cegahC.getText());
            }
            
            //resiko decbitus
            try {
                resikodecubitus = "";
                ps4 = koneksi.prepareStatement("select m.kode_decubitus, concat(m.parameter,' : ',m.penilaian,', Skor (',m.skor,')') decubitus "
                        + "FROM master_data_decubitus m INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_decubitus pa ON pa.kode_decubitus = m.kode_decubitus "
                        + "WHERE pa.no_rawat=? ORDER BY pa.kode_decubitus");
                try {
                    ps4.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        resikodecubitus = rs4.getString("decubitus") + "\n" + resikodecubitus;
                    }
                    
                    if (resikodecubitus.endsWith("\n")) {
                        resikodecubitus = resikodecubitus.substring(0, resikodecubitus.length() - 1);
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
                System.out.println("Notif : " + e);
            }
            
            param.put("resikoDecu", resikodecubitus);
            param.put("TotSkorResikoDecu", "Total Skor : (" + TotSkorDecu.getText() + ")");
            param.put("KesResikoDecu", "Kesimpulan Skor Resiko Decubitus :\n" + kesimpulanResikoDecu.getText());
            param.put("nilai", cmbNilai.getSelectedItem().toString() + " : " + Tketerangan.getText());
            
            if (obatObatan.equals("ya")) {
                param.put("obat", "V");
            } else {
                param.put("obat", "");
            }
            
            if (perawatanLuka.equals("ya")) {
                param.put("rawatluka", "V");
            } else {
                param.put("rawatluka", "");
            }
            
            param.put("manajemenLain", TmanajemenLain.getText());
            
            if (manajemenNyeri.equals("ya")) {
                param.put("manajNyeri", "V");
            } else {
                param.put("manajNyeri", "");
            }
            
            if (diet.equals("ya")) {
                param.put("diet", "V");
            } else {
                param.put("diet", "");
            }
            
            if (fisio.equals("ya")) {
                param.put("fisio", "V");
            } else {
                param.put("fisio", "");
            }
            
            param.put("rehabLain", TrehabLain.getText());
            
            if (hipertermi.equals("ya")) {
                param.put("hipertermi", "V");
            } else {
                param.put("hipertermi", "");
            }
            
            if (nyeri.equals("ya")) {
                param.put("nyeri", "V");
            } else {
                param.put("nyeri", "");
            }
            
            if (resiko.equals("ya")) {
                param.put("resiko", "V");
            } else {
                param.put("resiko", "");
            }
            
            if (kelebihan.equals("ya")) {
                param.put("kelebihan", "V");
            } else {
                param.put("kelebihan", "");
            }
            
            if (bersihkan.equals("ya")) {
                param.put("bersihkan", "V");
            } else {
                param.put("bersihkan", "");
            }
            
            if (pola.equals("ya")) {
                param.put("polaNafas", "V");
            } else {
                param.put("polaNafas", "");
            }
            
            if (gangguan.equals("ya")) {
                param.put("gangguan", "V");
            } else {
                param.put("gangguan", "");
            }
            
            if (cemas.equals("ya")) {
                param.put("cemas", "V");
            } else {
                param.put("cemas", "");
            }
            
            if (ketidakseimbangan.equals("ya")) {
                param.put("ketidakseimbangan", "V");
            } else {
                param.put("ketidakseimbangan", "");
            }
            
            if (perubahan.equals("ya")) {
                param.put("perubahan", "V");
            } else {
                param.put("perubahan", "");
            }
            
            if (penurunan.equals("ya")) {
                param.put("penurunan", "V");
            } else {
                param.put("penurunan", "");
            }
            
            if (kerusakan.equals("ya")) {
                param.put("kerusakan", "V");
            } else {
                param.put("kerusakan", "");
            }
            
            if (intoleransi.equals("ya")) {
                param.put("intol", "V");
            } else {
                param.put("intol", "");
            }
            
            if (kurang.equals("ya")) {
                param.put("kurang", "V");
            } else {
                param.put("kurang", "");
            }
            
            if (perluMPP.equals("ya")) {
                param.put("mpp", "V");
            } else {
                param.put("mpp", "");
            }
            
            if (perluDP.equals("ya")) {
                param.put("dp", "V");
            } else {
                param.put("dp", "");
            }
            
            param.put("masalahLain", TmasalahLain.getText());
            param.put("tglAsesmen", TtglAses.getSelectedItem().toString());
            param.put("jamAsesmen", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            param.put("perawat", "(" + TnmPerawat.getText() + ")");
            
            Valid.MyReport("rptAsesmenKeperawatanDewasa1.jasper", "report", "::[ Asesmen Keperawatan Dewasa Rawat Inap Hal. 1 ]::",
                    "SELECT now() tanggal", param);
            
            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
            tampilResikoDecubitus();
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
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
        tampil();
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
        tampilFaktorResiko();
        tampilResikoDecubitus();
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbAsesmenKeyPressed

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbAsesmen.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_tbAsesmenMouseClicked

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void cmbTibaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTibaActionPerformed
        TtibaLain.setText("");
        if (cmbTiba.getSelectedIndex() == 4) {
            TtibaLain.setEnabled(true);
            TtibaLain.requestFocus();
        } else {
            TtibaLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTibaActionPerformed

    private void ChkAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiObatActionPerformed
        TreaksiObat.setText("");
        TnmAlergiObat.setText("");
        
        if (ChkAlergiObat.isSelected() == true) {
            TreaksiObat.setEnabled(true);
            TnmAlergiObat.setEnabled(true);
            TnmAlergiObat.requestFocus();
        } else {
            TreaksiObat.setEnabled(false);
            TnmAlergiObat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiObatActionPerformed

    private void ChkAlergiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiMakananActionPerformed
        TreaksiMakanan.setText("");
        TnmAlergiMakanan.setText("");
        
        if (ChkAlergiMakanan.isSelected() == true) {
            TreaksiMakanan.setEnabled(true);
            TnmAlergiMakanan.setEnabled(true);
            TnmAlergiMakanan.requestFocus();
        } else {
            TreaksiMakanan.setEnabled(false);
            TnmAlergiMakanan.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiMakananActionPerformed

    private void ChkAlergiLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAlergiLainyaActionPerformed
        TreaksiLain.setText("");
        TnmAlergiLain.setText("");
        
        if (ChkAlergiLainya.isSelected() == true) {
            TreaksiLain.setEnabled(true);
            TnmAlergiLain.setEnabled(true);
            TnmAlergiLain.requestFocus();
        } else {
            TreaksiLain.setEnabled(false);
            TnmAlergiLain.setEnabled(false);
        }
    }//GEN-LAST:event_ChkAlergiLainyaActionPerformed

    private void TriwPenyktSkgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TriwPenyktSkgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkesadaran.requestFocus();
        }
    }//GEN-LAST:event_TriwPenyktSkgKeyPressed

    private void TtibaLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtibaLainKeyPressed
        Valid.pindah(evt, cmbTiba, cmbMasuk);
    }//GEN-LAST:event_TtibaLainKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        Valid.pindah(evt, cmbMasuk, cmbRiwAlergi);
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, TriwPenyktSkg, Tgcse);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tkeluhan, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Ttensi);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TtensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtensiKeyPressed
        Valid.pindah(evt, Tgcsv, Ttemp);
    }//GEN-LAST:event_TtensiKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        Valid.pindah(evt, Ttensi, Thr);
    }//GEN-LAST:event_TtempKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        Valid.pindah(evt, Ttemp, Trr);
    }//GEN-LAST:event_ThrKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Thr, TbbBelum);
    }//GEN-LAST:event_TrrKeyPressed

    private void TbbBelumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbBelumKeyPressed
        Valid.pindah(evt, Trr, TbbMasuk);
    }//GEN-LAST:event_TbbBelumKeyPressed

    private void TbbMasukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbMasukKeyPressed
        Valid.pindah(evt, TbbBelum, Ttb);
    }//GEN-LAST:event_TbbMasukKeyPressed

    private void TtbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnIMTActionPerformed(null);
        }
    }//GEN-LAST:event_TtbKeyPressed

    private void TimtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TimtKeyPressed
        Valid.pindah(evt, Ttb, Tcrt);
    }//GEN-LAST:event_TimtKeyPressed

    private void TcrtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcrtKeyPressed
        Valid.pindah(evt, Timt, Tspo);
    }//GEN-LAST:event_TcrtKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Tcrt, cmbPernafasan);
    }//GEN-LAST:event_TspoKeyPressed

    private void TkesimpulanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesimpulanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TKlgDekat.requestFocus();
        }
    }//GEN-LAST:event_TkesimpulanKeyPressed

    private void TKlgDekatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKlgDekatKeyPressed
        Valid.pindah(evt, Tkesimpulan, Thubungan);
    }//GEN-LAST:event_TKlgDekatKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThubunganKeyPressed

    private void TtglDenganLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtglDenganLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtglDenganLainKeyPressed

    private void cmbTinggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinggalActionPerformed
        TtglDenganLain.setText("");
        if (cmbTinggal.getSelectedIndex() == 7) {
            TtglDenganLain.setEnabled(true);
            TtglDenganLain.requestFocus();
        } else {
            TtglDenganLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTinggalActionPerformed

    private void cmbGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi1ActionPerformed
        cmbYaGizi1.setSelectedIndex(0);
        skorYaGizi1.setText("0");

        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGizi1.setText("2");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(true);
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi1ActionPerformed

    private void cmbYaGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYaGizi1ActionPerformed
        if (cmbYaGizi1.getSelectedIndex() == 0) {
            skorYaGizi1.setText("0");
        } else if (cmbYaGizi1.getSelectedIndex() == 1) {
            skorYaGizi1.setText("1");
        } else if (cmbYaGizi1.getSelectedIndex() == 2) {
            skorYaGizi1.setText("2");
        } else if (cmbYaGizi1.getSelectedIndex() == 3) {
            skorYaGizi1.setText("3");
        } else if (cmbYaGizi1.getSelectedIndex() == 4) {
            skorYaGizi1.setText("4");
        } else if (cmbYaGizi1.getSelectedIndex() == 5) {
            skorYaGizi1.setText("2");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbYaGizi1ActionPerformed

    private void cmbGizi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi2ActionPerformed
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGizi2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGizi2.setText("1");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi2ActionPerformed

    private void TonsetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TonsetKeyPressed
        Valid.pindah(evt, Tonset, cmbProvo);
    }//GEN-LAST:event_TonsetKeyPressed

    private void cmbProvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvoActionPerformed
        Tprovo.setText("");
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
            Tprovo.requestFocus();
        } else {
            Tprovo.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProvoActionPerformed

    private void TprovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprovoKeyPressed
        Valid.pindah(evt, cmbProvo, cmbQuality);
    }//GEN-LAST:event_TprovoKeyPressed

    private void cmbQualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbQualityActionPerformed
        Tquality.setText("");
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
            Tquality.requestFocus();
        } else {
            Tquality.setEnabled(false);
        }
    }//GEN-LAST:event_cmbQualityActionPerformed

    private void TqualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqualityKeyPressed
        Valid.pindah(evt, cmbQuality, cmbRadia);
    }//GEN-LAST:event_TqualityKeyPressed

    private void TreliefKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreliefKeyPressed
        Valid.pindah(evt, cmbRelief, cmbAsso);
    }//GEN-LAST:event_TreliefKeyPressed

    private void cmbReliefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReliefActionPerformed
        Trelief.setText("");
        if (cmbRelief.getSelectedIndex() == 4) {
            Trelief.setEnabled(true);
            Trelief.requestFocus();
        } else {
            Trelief.setEnabled(false);
        }
    }//GEN-LAST:event_cmbReliefActionPerformed

    private void cmbAssoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAssoActionPerformed
        Tasso.setText("");
        if (cmbAsso.getSelectedIndex() == 6) {
            Tasso.setEnabled(true);
            Tasso.requestFocus();
        } else {
            Tasso.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAssoActionPerformed

    private void TassoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TassoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TassoKeyPressed

    private void tbFaktorResikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaktorResikoMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                hitungSkorRJ();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaktorResikoMouseClicked

    private void tbFaktorResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyPressed
        if(tabMode1.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariResiko.setText("");
                TCariResiko.requestFocus();
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyPressed

    private void tbFaktorResikoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyReleased
        if(tabMode1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    hitungSkorRJ();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyReleased

    private void TCariResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilFaktorResiko();
        }
    }//GEN-LAST:event_TCariResikoKeyPressed

    private void BtnCariResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariResikoActionPerformed
        tampilFaktorResiko();
        hitungSkorRJ();
    }//GEN-LAST:event_BtnCariResikoActionPerformed

    private void BtnCariResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilFaktorResiko();
        }
    }//GEN-LAST:event_BtnCariResikoKeyPressed

    private void BtnAllResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllResikoActionPerformed
        TCariResiko.setText("");
        tampilFaktorResiko();
        hitungSkorRJ();
    }//GEN-LAST:event_BtnAllResikoActionPerformed

    private void BtnAllResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllResikoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllResikoActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariResiko, TCariResiko);
        }
    }//GEN-LAST:event_BtnAllResikoKeyPressed

    private void tbFaktorDecuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaktorDecuMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                hitungSkorDecu();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaktorDecuMouseClicked

    private void tbFaktorDecuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorDecuKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCariDecu.setText("");
                TCariDecu.requestFocus();
            }
        }
    }//GEN-LAST:event_tbFaktorDecuKeyPressed

    private void tbFaktorDecuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorDecuKeyReleased
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    hitungSkorDecu();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbFaktorDecuKeyReleased

    private void TCariDecuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariDecuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilResikoDecubitus();
        }
    }//GEN-LAST:event_TCariDecuKeyPressed

    private void BtnCariDecuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariDecuActionPerformed
        tampilResikoDecubitus();
        hitungSkorDecu();
    }//GEN-LAST:event_BtnCariDecuActionPerformed

    private void BtnCariDecuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariDecuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampilResikoDecubitus();
        }
    }//GEN-LAST:event_BtnCariDecuKeyPressed

    private void BtnAllDecuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllDecuActionPerformed
        TCariDecu.setText("");
        tampilResikoDecubitus();
        hitungSkorDecu();
    }//GEN-LAST:event_BtnAllDecuActionPerformed

    private void BtnAllDecuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllDecuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllDecuActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCariDecu, TCariDecu);
        }
    }//GEN-LAST:event_BtnAllDecuKeyPressed

    private void cmbNilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNilaiActionPerformed
        if (cmbNilai.getSelectedIndex() == 0) {
            Tketerangan.setText("");
        } else if (cmbNilai.getSelectedIndex() == 1) {
            Tketerangan.setText("Mampu menggerakkan persendian dalam lingkup gerak penuh, mampu melawan gaya gravitasi, mampu melawan dengan tahanan penuh");
        } else if (cmbNilai.getSelectedIndex() == 2) {
            Tketerangan.setText("Mampu menggerakkan persendian dengan gaya gravitasi. Mampu melawan dengan tahanan sedang");
        } else if (cmbNilai.getSelectedIndex() == 3) {
            Tketerangan.setText("Mampu melakukan gerakan mengangkat ekstremitas/tetapi tidak bisa melawan tahanan");
        } else if (cmbNilai.getSelectedIndex() == 4) {
            Tketerangan.setText("Mampu melakukan gerakan tetapi tidak bisa melawan tahanan");
        } else if (cmbNilai.getSelectedIndex() == 5) {
            Tketerangan.setText("Kontraksi otot dapat di palpasi tanpa gerakan persendian");
        } else if (cmbNilai.getSelectedIndex() == 6) {
            Tketerangan.setText("Tidak ada kontraksi otot");
        }
    }//GEN-LAST:event_cmbNilaiActionPerformed

    private void cmbTindakanCegahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanCegahActionPerformed
        if (cmbTindakanCegah.getSelectedIndex() == 1) {
            TabPencegahanDewasa.setSelectedIndex(0);
        } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
            TabPencegahanDewasa.setSelectedIndex(1);
        } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
            TabPencegahanDewasa.setSelectedIndex(2);
        } else {
            TabPencegahanDewasa.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbTindakanCegahActionPerformed

    private void TmasalahLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmasalahLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TmasalahLainKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMAsesmenKeperawatanDewasaRanap");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void cmbPernafasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernafasanActionPerformed
        Tpernafasan.setText("");
        if (cmbPernafasan.getSelectedIndex() == 4) {
            Tpernafasan.setEnabled(true);
            Tpernafasan.requestFocus();
        } else {
            Tpernafasan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPernafasanActionPerformed

    private void cmbPenglihatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenglihatanActionPerformed
        Tpenglihatan.setText("");
        if (cmbPenglihatan.getSelectedIndex() == 4) {
            Tpenglihatan.setEnabled(true);
            Tpenglihatan.requestFocus();
        } else {
            Tpenglihatan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPenglihatanActionPerformed

    private void cmbPendengaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPendengaranActionPerformed
        Tpendengaran.setText("");
        if (cmbPendengaran.getSelectedIndex() == 4) {
            Tpendengaran.setEnabled(true);
            Tpendengaran.requestFocus();
        } else {
            Tpendengaran.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPendengaranActionPerformed

    private void cmbMulutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMulutActionPerformed
        Tmulut.setText("");
        if (cmbMulut.getSelectedIndex() == 3) {
            Tmulut.setEnabled(true);
            Tmulut.requestFocus();
        } else {
            Tmulut.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMulutActionPerformed

    private void cmbReflekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReflekActionPerformed
        Treflek.setText("");
        if (cmbReflek.getSelectedIndex() == 4) {
            Treflek.setEnabled(true);
            Treflek.requestFocus();
        } else {
            Treflek.setEnabled(false);
        }
    }//GEN-LAST:event_cmbReflekActionPerformed

    private void cmbBicaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBicaraActionPerformed
        Tbicara.setText("");
        if (cmbBicara.getSelectedIndex() == 3) {
            Tbicara.setEnabled(true);
            Tbicara.requestFocus();
        } else {
            Tbicara.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBicaraActionPerformed

    private void cmbDefekasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDefekasiActionPerformed
        Tdefekasi.setText("");
        if (cmbDefekasi.getSelectedIndex() == 5) {
            Tdefekasi.setEnabled(true);
            Tdefekasi.requestFocus();
        } else {
            Tdefekasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDefekasiActionPerformed

    private void cmbMiksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMiksiActionPerformed
        Tmiksi.setText("");
        if (cmbMiksi.getSelectedIndex() == 4) {
            Tmiksi.setEnabled(true);
            Tmiksi.requestFocus();
        } else {
            Tmiksi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMiksiActionPerformed

    private void cmbGastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGastroActionPerformed
        Tgastro.setText("");
        if (cmbGastro.getSelectedIndex() == 5) {
            Tgastro.setEnabled(true);
            Tgastro.requestFocus();
        } else {
            Tgastro.setEnabled(false);
        }
    }//GEN-LAST:event_cmbGastroActionPerformed

    private void cmbPolaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPolaActionPerformed
        Tpola.setText("");
        if (cmbPola.getSelectedIndex() == 3) {
            Tpola.setEnabled(true);
            Tpola.requestFocus();
        } else {
            Tpola.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPolaActionPerformed

    private void BtnIMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnIMTActionPerformed
        hitungIMT();
    }//GEN-LAST:event_BtnIMTActionPerformed

    private void BtnIMTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnIMTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnIMTActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnIMTKeyPressed

    private void TnmAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiObatKeyPressed
        Valid.pindah(evt, TnmAlergiObat, TreaksiObat);
    }//GEN-LAST:event_TnmAlergiObatKeyPressed

    private void TnmAlergiMakananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiMakananKeyPressed
        Valid.pindah(evt, TnmAlergiMakanan, TreaksiMakanan);
    }//GEN-LAST:event_TnmAlergiMakananKeyPressed

    private void TnmAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmAlergiLainKeyPressed
        Valid.pindah(evt, TnmAlergiLain, TreaksiLain);
    }//GEN-LAST:event_TnmAlergiLainKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKeperawatanDewasaRanap dialog = new RMAsesmenKeperawatanDewasaRanap(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAllDecu;
    private widget.Button BtnAllResiko;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCariDecu;
    private widget.Button BtnCariResiko;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnIMT;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAlergiLainya;
    public widget.CekBox ChkAlergiMakanan;
    public widget.CekBox ChkAlergiObat;
    public widget.CekBox ChkBersihkan;
    public widget.CekBox ChkCemas;
    public widget.CekBox ChkDP;
    public widget.CekBox ChkDiet;
    public widget.CekBox ChkFisioterapi;
    public widget.CekBox ChkGangguan;
    public widget.CekBox ChkGelang;
    public widget.CekBox ChkHipertermi;
    public widget.CekBox ChkIbadah;
    public widget.CekBox ChkIntoleransi;
    public widget.CekBox ChkKelebihan;
    public widget.CekBox ChkKerusakan;
    public widget.CekBox ChkKetidakseimbangan;
    public widget.CekBox ChkKurang;
    public widget.CekBox ChkMPP;
    public widget.CekBox ChkManajemen;
    public widget.CekBox ChkNyeri;
    public widget.CekBox ChkObat;
    public widget.CekBox ChkPenurunan;
    public widget.CekBox ChkPerawatan;
    public widget.CekBox ChkPerubahan;
    public widget.CekBox ChkPola;
    public widget.CekBox ChkResiko;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCariDecu;
    private widget.TextBox TCariResiko;
    private widget.TextBox TKlgDekat;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tasso;
    private widget.TextBox TbbBelum;
    private widget.TextBox TbbMasuk;
    private widget.TextBox Tbicara;
    private widget.TextBox Tcrt;
    private widget.TextBox Tdefekasi;
    private widget.TextBox Tgastro;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Thr;
    private widget.TextBox Thubungan;
    private widget.TextBox Timt;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tkesadaran;
    private widget.TextArea Tkesimpulan;
    private widget.TextArea Tketerangan;
    private widget.TextBox TmanajemenLain;
    private widget.TextArea TmasalahLain;
    private widget.TextBox Tmiksi;
    private widget.TextBox Tmulut;
    private widget.TextBox TnmAlergiLain;
    private widget.TextBox TnmAlergiMakanan;
    private widget.TextBox TnmAlergiObat;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnoTelp;
    private widget.TextBox Tonset;
    private widget.TextBox TotSkorDecu;
    private widget.TextBox TotSkorGizi;
    private widget.TextBox TotSkorRJ;
    private widget.TextBox Tpendengaran;
    private widget.TextBox Tpenglihatan;
    private widget.TextBox Tpernafasan;
    private widget.TextBox Tpola;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox TreaksiLain;
    private widget.TextBox TreaksiMakanan;
    private widget.TextBox TreaksiObat;
    private widget.TextBox Treflek;
    private widget.TextBox TrehabLain;
    private widget.TextBox Trelief;
    private widget.TextArea TriwPenyktSkg;
    private widget.TextBox Trr;
    private widget.TextBox Truangan;
    private widget.TextBox Tspo;
    private widget.TextBox TsttsNikah;
    private widget.TextBox Ttb;
    private widget.TextBox Ttemp;
    private widget.TextBox Ttensi;
    private widget.Tanggal TtglAses;
    private widget.TextBox TtglDenganLain;
    private widget.Tanggal TtglMsk;
    private widget.TextBox TtibaLain;
    private widget.TextArea cegahA;
    private widget.TextArea cegahB;
    private widget.TextArea cegahC;
    private widget.ComboBox cmbAlergiDiberitahu;
    private widget.ComboBox cmbAsso;
    private widget.ComboBox cmbBerpakaian;
    private widget.ComboBox cmbBerpindah;
    private widget.ComboBox cmbBicara;
    private widget.ComboBox cmbBuang;
    private widget.ComboBox cmbCuriga;
    private widget.ComboBox cmbDefekasi;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbGastro;
    private widget.ComboBox cmbGizi1;
    private widget.ComboBox cmbGizi2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbMakan;
    private widget.ComboBox cmbMandi;
    private widget.ComboBox cmbMasuk;
    private widget.ComboBox cmbMiksi;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMulut;
    private widget.ComboBox cmbNilai;
    private widget.ComboBox cmbPendengaran;
    private widget.ComboBox cmbPenglihatan;
    private widget.ComboBox cmbPernafasan;
    private widget.ComboBox cmbPola;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbReflek;
    private widget.ComboBox cmbRelief;
    private widget.ComboBox cmbRiwAlergi;
    private widget.ComboBox cmbSever;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTiba;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbTindakanCegah;
    private widget.ComboBox cmbTinggal;
    private widget.ComboBox cmbYaGizi1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel140;
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
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
    private widget.Label jLabel5;
    private widget.Label jLabel50;
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
    private widget.Label jLabel62;
    private widget.Label jLabel63;
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
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel9;
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
    private widget.TextArea kesimpulanGizi;
    private widget.TextArea kesimpulanResikoDecu;
    private widget.TextArea kesimpulanResikoJatuh;
    private widget.Label label12;
    private widget.Label label13;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.TextBox skorGizi1;
    private widget.TextBox skorGizi2;
    private widget.TextBox skorYaGizi1;
    private widget.Table tbAsesmen;
    private widget.Table tbFaktorDecu;
    private widget.Table tbFaktorResiko;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT *, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "date_format(pa.tgl_msk_ruangan,'%d-%m-%Y') tglmsk, time_format(pa.jam_msk_ruangan,'%H:%i') jammsk, concat(pa.tiba_diruang,' ',pa.tiba_diruang_lainnya) tiba, "
                    + "pa.msk_melalui, pa.keluhan_utama, date_format(pa.tgl_asesmen,'%d-%m-%Y') tglases, time_format(pa.jam_asesmen,'%H:%i') jamases, pg.nama perawat "
                    + "from penilaian_awal_keperawatan_dewasa_ranap pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pa.nip_perawat where "
                    + "pa.tgl_asesmen between ? and ? and pa.no_rawat like ? or "
                    + "pa.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "pa.tgl_asesmen between ? and ? and p.nm_pasien like ? or "
                    + "pa.tgl_asesmen between ? and ? and pa.keluhan_utama like ? or "
                    + "pa.tgl_asesmen between ? and ? and pg.nama like ? order by pa.waktu_simpan desc");
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
                        rs.getString("tgllahir"),
                        Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + rs.getString("kd_kamar_msk") + "'"),
                        rs.getString("tglmsk"),
                        rs.getString("jammsk"),
                        rs.getString("tiba"),
                        rs.getString("msk_melalui"),
                        rs.getString("keluhan_utama"),
                        rs.getString("tglases"),
                        rs.getString("jamases"),
                        rs.getString("perawat"),
                        rs.getString("kd_kamar_msk"),
                        rs.getString("tgl_msk_ruangan"),
                        rs.getString("jam_msk_ruangan"),
                        rs.getString("tiba_diruang"),
                        rs.getString("tiba_diruang_lainnya"),
                        rs.getString("msk_melalui"),
                        rs.getString("keluhan_utama"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("alergi_obat"),
                        rs.getString("reaksi_alergi_obat"),
                        rs.getString("alergi_makanan"),
                        rs.getString("reaksi_alergi_makanan"),
                        rs.getString("alergi_lainnya"),
                        rs.getString("reaksi_alergi_lainnya"),
                        rs.getString("pasang_gelang_tanda"),
                        rs.getString("alergi_diberitahukan"),
                        rs.getString("riwayat_penyakit_sekarang"),
                        rs.getString("kesadaran"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tensi"),
                        rs.getString("temp"),
                        rs.getString("hr"),
                        rs.getString("rr"),
                        rs.getString("bb_sebelum_skt"),
                        rs.getString("bb_msk_rs"),
                        rs.getString("tb"),
                        rs.getString("imt"),
                        rs.getString("crt"),
                        rs.getString("spo2"),
                        rs.getString("pernafasan"),
                        rs.getString("pernafasan_lain"),
                        rs.getString("penglihatan"),
                        rs.getString("penglihatan_lain"),
                        rs.getString("pendengaran"),
                        rs.getString("pendengaran_lain"),
                        rs.getString("mulut"),
                        rs.getString("mulut_lain"),
                        rs.getString("reflek_menelan"),
                        rs.getString("reflek_menelan_lain"),
                        rs.getString("bicara"),
                        rs.getString("bicara_lain"),
                        rs.getString("defekasi"),
                        rs.getString("defekasi_lain"),
                        rs.getString("miksi"),
                        rs.getString("miksi_lain"),
                        rs.getString("gastrointestinal"),
                        rs.getString("gastrointestinal_lain"),
                        rs.getString("pola_tidur"),
                        rs.getString("pola_tidur_lain"),
                        rs.getString("makan"),
                        rs.getString("berpakaian"),
                        rs.getString("buang_air"),
                        rs.getString("mandi"),
                        rs.getString("berpindah"),
                        rs.getString("kesimpulan"),
                        rs.getString("keluarga_terdekat"),
                        rs.getString("hubungan"),
                        rs.getString("tinggal_dengan"),
                        rs.getString("tinggal_dengan_lain"),
                        rs.getString("curiga_penganiayaan"),
                        rs.getString("butuh_bantuan_ibadah"),
                        rs.getString("status_emosional"),
                        rs.getString("gizi_1"),
                        rs.getString("gizi_1ya"),
                        rs.getString("gizi_2"),
                        rs.getString("onset"),
                        rs.getString("provocation"),
                        rs.getString("provocation_lain"),
                        rs.getString("quality"),
                        rs.getString("quality_lain"),
                        rs.getString("radiation"),
                        rs.getString("severity"),
                        rs.getString("time"),
                        rs.getString("time_lama"),
                        rs.getString("relief"),
                        rs.getString("relief_lain"),
                        rs.getString("asociated_sign"),
                        rs.getString("asociated_sign_lain"),
                        rs.getString("skala_nyeri"),
                        rs.getString("tindakan_pencegahan"),
                        rs.getString("nilai_skala_kekuatan_otot"),
                        rs.getString("obat_obatan"),
                        rs.getString("perawatan_luka"),
                        rs.getString("manajemen_lain"),
                        rs.getString("menejemen_nyeri"),
                        rs.getString("diet_nutrisi"),
                        rs.getString("fisioterapi"),
                        rs.getString("rehabilitasi_lain"),
                        rs.getString("hipertermi"),
                        rs.getString("nyeri"),
                        rs.getString("kurang_volume_cairan"),
                        rs.getString("lebih_volume_cairan"),
                        rs.getString("bersihkan_jln_nfs_tdk_efektif"),
                        rs.getString("pola_nfs_tdk_efektif"),
                        rs.getString("gangguan_pertukaran_gas"),
                        rs.getString("cemas"),
                        rs.getString("ketidakseimbangan_nutrisi"),
                        rs.getString("perubahan_perfusi"),
                        rs.getString("penurunan_curah_jantung"),
                        rs.getString("kerusakan_integritas"),
                        rs.getString("intoleransi_aktifitas"),
                        rs.getString("kurang_perawatan_diri"),
                        rs.getString("memerlukan_mpp"),
                        rs.getString("memerlukan_dp"),
                        rs.getString("daftar_masalah_lain"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("nip_perawat"),
                        rs.getString("jam_asesmen"),
                        rs.getString("waktu_simpan"),                        
                        rs.getString("nm_alergi_obat"),
                        rs.getString("nm_alergi_makanan"),
                        rs.getString("nm_alergi_lainnya")
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
        TtglMsk.setDate(new Date());
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbTiba.setSelectedIndex(0);
        TtibaLain.setText("");
        TtibaLain.setEnabled(false);
        Tkeluhan.setText("");
        cmbRiwAlergi.setSelectedIndex(0);
        alergiObat = "";
        alergiMakanan = "";
        alergiLain = "";
        gelang = "";
        ChkAlergiObat.setSelected(false);
        ChkAlergiMakanan.setSelected(false);
        ChkAlergiLainya.setSelected(false);
        TnmAlergiObat.setText("");
        TnmAlergiMakanan.setText("");
        TnmAlergiLain.setText("");
        TnmAlergiObat.setEnabled(false);
        TnmAlergiMakanan.setEnabled(false);
        TnmAlergiLain.setEnabled(false);        
        ChkGelang.setSelected(false);
        TreaksiObat.setText("");
        TreaksiMakanan.setText("");
        TreaksiLain.setText("");
        TreaksiObat.setEnabled(false);
        TreaksiMakanan.setEnabled(false);
        TreaksiLain.setEnabled(false);
        cmbAlergiDiberitahu.setSelectedIndex(0);
        TriwPenyktSkg.setText("");
        Tkesadaran.setText("");
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Ttensi.setText("");
        Ttemp.setText("");
        Thr.setText("");
        Trr.setText("");
        TbbBelum.setText("");
        TbbMasuk.setText("");
        Ttb.setText("");
        Timt.setText("");
        Tcrt.setText("");
        Tspo.setText("");
        cmbPernafasan.setSelectedIndex(0);
        Tpernafasan.setText("");
        Tpernafasan.setEnabled(false);
        cmbPenglihatan.setSelectedIndex(0);
        Tpenglihatan.setText("");
        Tpenglihatan.setEnabled(false);
        cmbPendengaran.setSelectedIndex(0);
        Tpendengaran.setText("");
        Tpendengaran.setEnabled(false);
        cmbMulut.setSelectedIndex(0);
        Tmulut.setText("");
        Tmulut.setEnabled(false);
        cmbReflek.setSelectedIndex(0);
        Treflek.setText("");
        Treflek.setEnabled(false);
        cmbBicara.setSelectedIndex(0);
        Tbicara.setText("");
        Tbicara.setEnabled(false);
        cmbDefekasi.setSelectedIndex(0);
        Tdefekasi.setText("");
        Tdefekasi.setEnabled(false);
        cmbMiksi.setSelectedIndex(0);
        Tmiksi.setText("");
        Tmiksi.setEnabled(false);
        cmbGastro.setSelectedIndex(0);
        Tgastro.setText("");
        Tgastro.setEnabled(false);
        cmbPola.setSelectedIndex(0);
        Tpola.setText("");
        Tpola.setEnabled(false);
        cmbMakan.setSelectedIndex(0);
        cmbBerpakaian.setSelectedIndex(0);
        cmbBuang.setSelectedIndex(0);
        cmbMandi.setSelectedIndex(0);
        cmbBerpindah.setSelectedIndex(0);
        Tkesimpulan.setText("");
        TKlgDekat.setText("");
        Thubungan.setText("");
        cmbTinggal.setSelectedIndex(0);
        TtglDenganLain.setText("");
        TtglDenganLain.setEnabled(false);
        cmbCuriga.setSelectedIndex(0);
        ibadah = "";
        ChkIbadah.setSelected(false);
        cmbStatus.setSelectedIndex(0);
        
        kesimpulanGizi.setText("");
        cmbGizi1.setSelectedIndex(0);
        cmbYaGizi1.setSelectedIndex(0);
        cmbYaGizi1.setEnabled(false);
        skorGizi1.setText("0");
        skorYaGizi1.setText("0");
        cmbGizi2.setSelectedIndex(0);
        skorGizi2.setText("0");
        TotSkorGizi.setText("0");
        
        Tonset.setText("");
        cmbProvo.setSelectedIndex(0);
        Tprovo.setText("");
        Tprovo.setEnabled(false);
        cmbQuality.setSelectedIndex(0);
        Tquality.setText("");
        Tquality.setEnabled(false);
        cmbRadia.setSelectedIndex(0);
        cmbSever.setSelectedIndex(0);
        cmbTime.setSelectedIndex(0);
        cmbLama.setSelectedIndex(0);
        cmbRelief.setSelectedIndex(0);
        Trelief.setText("");
        Trelief.setEnabled(false);
        cmbAsso.setSelectedIndex(0);
        Tasso.setText("");
        Tasso.setEnabled(false);
        cmbSkala.setSelectedIndex(0);  
        
        TotSkorRJ.setText("0");
        kesimpulanResikoJatuh.setText("");
        BtnAllResikoActionPerformed(null);
        TotSkorDecu.setText("0");
        kesimpulanResikoDecu.setText("");
        BtnAllDecuActionPerformed(null);
        cmbTindakanCegah.setSelectedIndex(0);
        cmbNilai.setSelectedIndex(0);
        Tketerangan.setText("");
        
        obatObatan = "";
        perawatanLuka = "";
        ChkObat.setSelected(false);
        ChkPerawatan.setSelected(false);
        TmanajemenLain.setText("");
        manajemenNyeri = "";
        diet = "";
        fisio = "";
        ChkManajemen.setSelected(false);
        ChkDiet.setSelected(false);
        ChkFisioterapi.setSelected(false);
        TrehabLain.setText("");
        
        hipertermi = "";
        nyeri = "";
        resiko = "";
        kelebihan = "";
        bersihkan = "";
        pola = "";
        gangguan = "";
        cemas = "";
        ketidakseimbangan = "";
        perubahan = "";
        penurunan = "";
        kerusakan = "";
        intoleransi = "";
        kurang = "";
        perluMPP = "";
        perluDP = "";
        
        ChkHipertermi.setSelected(false);
        ChkNyeri.setSelected(false);
        ChkResiko.setSelected(false);
        ChkKelebihan.setSelected(false);
        ChkBersihkan.setSelected(false);
        ChkPola.setSelected(false);
        ChkGangguan.setSelected(false);
        ChkCemas.setSelected(false);
        ChkKetidakseimbangan.setSelected(false);
        ChkPerubahan.setSelected(false);
        ChkPenurunan.setSelected(false);
        ChkKerusakan.setSelected(false);
        ChkIntoleransi.setSelected(false);
        ChkKurang.setSelected(false);
        ChkMPP.setSelected(false);
        ChkDP.setSelected(false);
        TmasalahLain.setText("");
        
        TtglAses.setDate(new Date());
        cmbJam1.setSelectedIndex(0);
        cmbMnt1.setSelectedIndex(0);
        cmbDtk1.setSelectedIndex(0);
        nip = "-";
        TnmPerawat.setText("-");
    }
    
    public void setData(String norwt, String kdkmr) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        kdkamar = kdkmr;
        Truangan.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM kamar k INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal WHERE k.kd_kamar='" + kdkmr + "'"));
        TsttsNikah.setText(Sequel.cariIsi("select stts_nikah from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TnoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + norwt + "'") == 0) {
            cmbMasuk.setSelectedIndex(0);
        } else {
            cmbMasuk.setSelectedIndex(1);
        }
        TCari.setText(norwt);        
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {            
            BtnPerawat.setEnabled(false);
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
            if (TnmPerawat.getText().equals("")) {
                nip = "";
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }  
    }
    
    private void getData() {
        bersihkanString();
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            kdkamar = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            Truangan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglMsk, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(6, 8));
            cmbTiba.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            TtibaLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            cmbMasuk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString());
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            cmbRiwAlergi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            alergiObat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            alergiMakanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            alergiLain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            TreaksiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString());
            TreaksiMakanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString());
            TreaksiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            gelang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            cmbAlergiDiberitahu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            TriwPenyktSkg.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            Tkesadaran.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            Tgcse.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            Tgcsm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            Tgcsv.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString());
            Ttensi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            Ttemp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString());
            Thr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            Trr.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString());
            TbbBelum.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            TbbMasuk.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString());
            Ttb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            Timt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            Tcrt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            Tspo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbPernafasan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            Tpernafasan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbPenglihatan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            Tpenglihatan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            cmbPendengaran.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            Tpendengaran.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            cmbMulut.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            Tmulut.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            cmbReflek.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            Treflek.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            cmbBicara.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            Tbicara.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            cmbDefekasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            Tdefekasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            cmbMiksi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            Tmiksi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            cmbGastro.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),60).toString());
            Tgastro.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            cmbPola.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),62).toString());
            Tpola.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            cmbMakan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),64).toString());
            cmbBerpakaian.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),65).toString());
            cmbBuang.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),66).toString());
            cmbMandi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            cmbBerpindah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            Tkesimpulan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString());
            TsttsNikah.setText(Sequel.cariIsi("select stts_nikah from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            TnoTelp.setText(Sequel.cariIsi("select ifnull(no_tlp,'0') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            TKlgDekat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            Thubungan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            cmbTinggal.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            TtglDenganLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            cmbCuriga.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            ibadah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            cmbStatus.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            cmbGizi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString());
            cmbYaGizi1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString());
            cmbGizi2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString());
            cmbSkala.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            Tonset.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString());
            cmbProvo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString());
            Tprovo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString());
            cmbQuality.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString());
            Tquality.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString());
            cmbRadia.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString());
            cmbSever.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString());
            cmbTime.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            cmbLama.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString());
            cmbRelief.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString());
            Trelief.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString());
            cmbAsso.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());
            Tasso.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString());
            cmbTindakanCegah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString());
            cmbNilai.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString());
            obatObatan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString();
            perawatanLuka = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString();
            TmanajemenLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString());
            manajemenNyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString();
            diet = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            fisio = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            TrehabLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString());
            hipertermi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            nyeri = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString();
            resiko = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString();
            kelebihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString();
            bersihkan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString();
            pola = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString();
            gangguan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString();
            cemas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 110).toString();
            ketidakseimbangan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 111).toString();
            perubahan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 112).toString();
            penurunan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 113).toString();
            kerusakan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 114).toString();
            intoleransi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 115).toString();
            kurang = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 116).toString();
            perluMPP = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 117).toString();
            perluDP = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 118).toString();
            TmasalahLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 119).toString());
            Valid.SetTgl(TtglAses, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 120).toString());
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 122).toString().substring(6, 8));
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 121).toString();
            TnmPerawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());            
            TnmAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString());
            TnmAlergiMakanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 125).toString());
            TnmAlergiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 126).toString());
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_dewasa_ranap where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat",
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                Sequel.meghapus("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat",
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                
                tampil();
                emptTeks();
                tampilFaktorResiko();
                tampilResikoDecubitus();
                TabRawat.setSelectedIndex(1);              
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("penilaian_awal_keperawatan_dewasa_ranap", "no_rawat=?", "kd_kamar_msk=?, tgl_msk_ruangan=?, jam_msk_ruangan=?, tiba_diruang=?, "
                + "tiba_diruang_lainnya=?, msk_melalui=?, keluhan_utama=?, riwayat_alergi=?, alergi_obat=?, reaksi_alergi_obat=?, alergi_makanan=?, reaksi_alergi_makanan=?, "
                + "alergi_lainnya=?, reaksi_alergi_lainnya=?, pasang_gelang_tanda=?, alergi_diberitahukan=?, riwayat_penyakit_sekarang=?, kesadaran=?, gcs_e=?, gcs_m=?, gcs_v=?, "
                + "tensi=?, temp=?, hr=?, rr=?, bb_sebelum_skt=?, bb_msk_rs=?, tb=?, imt=?, crt=?, spo2=?, pernafasan=?, pernafasan_lain=?, penglihatan=?, penglihatan_lain=?, "
                + "pendengaran=?, pendengaran_lain=?, mulut=?, mulut_lain=?, reflek_menelan=?, reflek_menelan_lain=?, bicara=?, bicara_lain=?, defekasi=?, defekasi_lain=?, miksi=?, "
                + "miksi_lain=?, gastrointestinal=?, gastrointestinal_lain=?, pola_tidur=?, pola_tidur_lain=?, makan=?, berpakaian=?, buang_air=?, mandi=?, berpindah=?, kesimpulan=?, "
                + "keluarga_terdekat=?, hubungan=?, tinggal_dengan=?, tinggal_dengan_lain=?, curiga_penganiayaan=?, butuh_bantuan_ibadah=?, status_emosional=?, gizi_1=?, gizi_1ya=?, "
                + "gizi_2=?, onset=?, provocation=?, provocation_lain=?, quality=?, quality_lain=?, radiation=?, severity=?, time=?, time_lama=?, relief=?, relief_lain=?, asociated_sign=?, "
                + "asociated_sign_lain=?, skala_nyeri=?, tindakan_pencegahan=?, nilai_skala_kekuatan_otot=?, obat_obatan=?, perawatan_luka=?, manajemen_lain=?, menejemen_nyeri=?, "
                + "diet_nutrisi=?, fisioterapi=?, rehabilitasi_lain=?, hipertermi=?, nyeri=?, kurang_volume_cairan=?, lebih_volume_cairan=?, bersihkan_jln_nfs_tdk_efektif=?, "
                + "pola_nfs_tdk_efektif=?, gangguan_pertukaran_gas=?, cemas=?, ketidakseimbangan_nutrisi=?, perubahan_perfusi=?, penurunan_curah_jantung=?, kerusakan_integritas=?, "
                + "intoleransi_aktifitas=?, kurang_perawatan_diri=?, memerlukan_mpp=?, memerlukan_dp=?, daftar_masalah_lain=?, tgl_asesmen=?, nip_perawat=?, jam_asesmen=?, "
                + "nm_alergi_obat=?, nm_alergi_makanan=?, nm_alergi_lainnya=?", 114, new String[]{
                    kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
                    alergiObat, TreaksiObat.getText(), alergiMakanan, TreaksiMakanan.getText(), alergiLain, TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(),
                    TriwPenyktSkg.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(),
                    TbbBelum.getText(), TbbMasuk.getText(), Ttb.getText(), Timt.getText(), Tcrt.getText(), Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), Tpernafasan.getText(),
                    cmbPenglihatan.getSelectedItem().toString(), Tpenglihatan.getText(), cmbPendengaran.getSelectedItem().toString(), Tpendengaran.getText(), cmbMulut.getSelectedItem().toString(),
                    Tmulut.getText(), cmbReflek.getSelectedItem().toString(), Treflek.getText(), cmbBicara.getSelectedItem().toString(), Tbicara.getText(), cmbDefekasi.getSelectedItem().toString(),
                    Tdefekasi.getText(), cmbMiksi.getSelectedItem().toString(), Tmiksi.getText(), cmbGastro.getSelectedItem().toString(), Tgastro.getText(), cmbPola.getSelectedItem().toString(),
                    Tpola.getText(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(), cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(),
                    cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(), Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(),
                    cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(), cmbGizi1.getSelectedItem().toString(), cmbYaGizi1.getSelectedItem().toString(),
                    cmbGizi2.getSelectedItem().toString(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
                    cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
                    cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
                    cmbTindakanCegah.getSelectedItem().toString(), cmbNilai.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri, diet, fisio,
                    TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan, intoleransi,
                    kurang, perluMPP, perluDP, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), nip, cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    TnmAlergiObat.getText(), TnmAlergiMakanan.getText(), TnmAlergiLain.getText(),
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.meghapus("penilaian_awal_keperawatan_dewasa_ranap_resiko", "no_rawat",
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            Sequel.meghapus("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "no_rawat",
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());

            //simpan faktor resiko
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_dewasa_ranap_resiko", "?,?", 2, new String[]{
                        TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                }
            }

            //simpan resiko decubitus
            for (i = 0; i < tbFaktorDecu.getRowCount(); i++) {
                if (tbFaktorDecu.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_dewasa_ranap_decubitus", "?,?", 2, new String[]{
                        TNoRw.getText(), tbFaktorDecu.getValueAt(i, 1).toString()});
                }
            }

            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
            tampilResikoDecubitus();
            tampil();
            emptTeks();
        }
    }
    
    private void cekData() {
        if (ChkAlergiObat.isSelected() == true) {
            alergiObat = "ya";
        } else {
            alergiObat = "tidak";
        }
        
        if (ChkAlergiMakanan.isSelected() == true) {
            alergiMakanan = "ya";
        } else {
            alergiMakanan = "tidak";
        }
        
        if (ChkAlergiLainya.isSelected() == true) {
            alergiLain = "ya";
        } else {
            alergiLain = "tidak";
        }
        
        if (ChkGelang.isSelected() == true) {
            gelang = "ya";
        } else {
            gelang = "tidak";
        }
        
        if (ChkIbadah.isSelected() == true) {
            ibadah = "ya";
        } else {
            ibadah = "tidak";
        }
        
        if (ChkObat.isSelected() == true) {
            obatObatan = "ya";
        } else {
            obatObatan = "tidak";
        }
        
        if (ChkPerawatan.isSelected() == true) {
            perawatanLuka = "ya";
        } else {
            perawatanLuka = "tidak";
        }
        
        if (ChkManajemen.isSelected() == true) {
            manajemenNyeri = "ya";
        } else {
            manajemenNyeri = "tidak";
        }
        
        if (ChkDiet.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (ChkFisioterapi.isSelected() == true) {
            fisio = "ya";
        } else {
            fisio = "tidak";
        }
        
        if (ChkHipertermi.isSelected() == true) {
            hipertermi = "ya";
        } else {
            hipertermi = "tidak";
        }
        
        if (ChkNyeri.isSelected() == true) {
            nyeri = "ya";
        } else {
            nyeri = "tidak";
        }
        
        if (ChkResiko.isSelected() == true) {
            resiko = "ya";
        } else {
            resiko = "tidak";
        }
        
        if (ChkKelebihan.isSelected() == true) {
            kelebihan = "ya";
        } else {
            kelebihan = "tidak";
        }
        
        if (ChkBersihkan.isSelected() == true) {
            bersihkan = "ya";
        } else {
            bersihkan = "tidak";
        }
        
        if (ChkPola.isSelected() == true) {
            pola = "ya";
        } else {
            pola = "tidak";
        }
        
        if (ChkGangguan.isSelected() == true) {
            gangguan = "ya";
        } else {
            gangguan = "tidak";
        }
        
        if (ChkCemas.isSelected() == true) {
            cemas = "ya";
        } else {
            cemas = "tidak";
        }
        
        if (ChkKetidakseimbangan.isSelected() == true) {
            ketidakseimbangan = "ya";
        } else {
            ketidakseimbangan = "tidak";
        }
        
        if (ChkPerubahan.isSelected() == true) {
            perubahan = "ya";
        } else {
            perubahan = "tidak";
        }
        
        if (ChkPenurunan.isSelected() == true) {
            penurunan = "ya";
        } else {
            penurunan = "tidak";
        }
        
        if (ChkKerusakan.isSelected() == true) {
            kerusakan = "ya";
        } else {
            kerusakan = "tidak";
        }
        
        if (ChkIntoleransi.isSelected() == true) {
            intoleransi = "ya";
        } else {
            intoleransi = "tidak";
        }
        
        if (ChkKurang.isSelected() == true) {
            kurang = "ya";
        } else {
            kurang = "tidak";
        }
        
        if (ChkMPP.isSelected() == true) {
            perluMPP = "ya";
        } else {
            perluMPP = "tidak";
        }
        
        if (ChkDP.isSelected() == true) {
            perluDP = "ya";
        } else {
            perluDP = "tidak";
        }
    }
    
    private void dataCek() {
        //tiba diruang
        if (cmbTiba.getSelectedIndex() == 4) {
            TtibaLain.setEnabled(true);
        } else {
            TtibaLain.setEnabled(false);
        }
        
        //riwayat alergi
        if (alergiObat.equals("ya")) {
            ChkAlergiObat.setSelected(true);
            TreaksiObat.setEnabled(true);
            TnmAlergiObat.setEnabled(true);
        } else {
            ChkAlergiObat.setSelected(false);
            TreaksiObat.setEnabled(false);
            TnmAlergiObat.setEnabled(false);
        }
        
        if (alergiMakanan.equals("ya")) {
            ChkAlergiMakanan.setSelected(true);
            TreaksiMakanan.setEnabled(true);
            TnmAlergiMakanan.setEnabled(true);
        } else {
            ChkAlergiMakanan.setSelected(false);
            TreaksiMakanan.setEnabled(false);
            TnmAlergiMakanan.setEnabled(false);
        }
        
        if (alergiLain.equals("ya")) {
            ChkAlergiLainya.setSelected(true);
            TreaksiLain.setEnabled(true);
            TnmAlergiLain.setEnabled(true);
        } else {
            ChkAlergiLainya.setSelected(false);
            TreaksiLain.setEnabled(false);
            TnmAlergiLain.setEnabled(false);
        }
        
        if (gelang.equals("ya")) {
            ChkGelang.setSelected(true);
        } else {
            ChkGelang.setSelected(false);
        }
        
        if (ibadah.equals("ya")) {
            ChkIbadah.setSelected(true);
        } else {
            ChkIbadah.setSelected(false);
        }
        
        //penilaian fisik
        if (cmbPernafasan.getSelectedIndex() == 4) {
            Tpernafasan.setEnabled(true);
        } else {
            Tpernafasan.setEnabled(false);
        }
        
        if (cmbPenglihatan.getSelectedIndex() == 4) {
            Tpenglihatan.setEnabled(true);
        } else {
            Tpenglihatan.setEnabled(false);
        }
        
        if (cmbPendengaran.getSelectedIndex() == 4) {
            Tpendengaran.setEnabled(true);
        } else {
            Tpendengaran.setEnabled(false);
        }
        
        if (cmbMulut.getSelectedIndex() == 3) {
            Tmulut.setEnabled(true);
        } else {
            Tmulut.setEnabled(false);
        }
        
        if (cmbReflek.getSelectedIndex() == 4) {
            Treflek.setEnabled(true);
        } else {
            Treflek.setEnabled(false);
        }
        
        if (cmbBicara.getSelectedIndex() == 3) {
            Tbicara.setEnabled(true);
        } else {
            Tbicara.setEnabled(false);
        }
        
        if (cmbDefekasi.getSelectedIndex() == 5) {
            Tdefekasi.setEnabled(true);
        } else {
            Tdefekasi.setEnabled(false);
        }
        
        if (cmbMiksi.getSelectedIndex() == 4) {
            Tmiksi.setEnabled(true);
        } else {
            Tmiksi.setEnabled(false);
        }
        
        if (cmbGastro.getSelectedIndex() == 5) {
            Tgastro.setEnabled(true);
        } else {
            Tgastro.setEnabled(false);
        }
        
        if (cmbPola.getSelectedIndex() == 3) {
            Tpola.setEnabled(true);
        } else {
            Tpola.setEnabled(false);
        }

        //skrining gizi
        cmbGizi1.setEnabled(true);
        cmbGizi2.setEnabled(true);

        if (cmbGizi1.getSelectedIndex() == 0) {
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            cmbYaGizi1.setEnabled(true);
        }
        hitungSkorGizi();

        //tindakan pencegahan resiko jatuh
        if (cmbTindakanCegah.getSelectedIndex() == 1) {
            TabPencegahanDewasa.setSelectedIndex(0);
        } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
            TabPencegahanDewasa.setSelectedIndex(1);
        } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
            TabPencegahanDewasa.setSelectedIndex(2);
        } else {
            TabPencegahanDewasa.setSelectedIndex(0);
        }
        
        //tinggal dengan
        if (cmbTinggal.getSelectedIndex() == 7) {
            TtglDenganLain.setEnabled(true);
        } else {
            TtglDenganLain.setEnabled(false);
        }
        
        //provocation
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
        } else {
            Tprovo.setEnabled(false);
        }
        
        //quality
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
        } else {
            Tquality.setEnabled(false);
        }
        
        //relief
        if (cmbRelief.getSelectedIndex() == 4) {
            Trelief.setEnabled(true);
        } else {
            Trelief.setEnabled(false);
        }
        
        //associated sign
        if (cmbAsso.getSelectedIndex() == 6) {
            Tasso.setEnabled(true);
        } else {
            Tasso.setEnabled(false);
        }
        
        //faktor resiko
        try {
            Valid.tabelKosong(tabMode1);
                ps1 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                        + "INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_resiko pa ON pa.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'Dewasa Ranap' and pa.no_rawat=? ORDER BY pa.kode_resiko");
            try {
                ps1.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        true,
                        rs1.getString("kode_resiko"),
                        rs1.getString("asesmen"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("skala"),
                        rs1.getString("skor")
                    });
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
        hitungSkorRJ();
        
        //resiko decubitus
        try {
            Valid.tabelKosong(tabMode2);
                ps2 = koneksi.prepareStatement("select m.kode_decubitus, m.parameter, m.penilaian, m.skor FROM master_data_decubitus m "
                        + "INNER JOIN penilaian_awal_keperawatan_dewasa_ranap_decubitus pa ON pa.kode_decubitus = m.kode_decubitus "
                        + "WHERE pa.no_rawat=? ORDER BY pa.kode_decubitus");
            try {
                ps2.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        true,
                        rs2.getString("kode_decubitus"),
                        rs2.getString("parameter"),
                        rs2.getString("penilaian"),
                        rs2.getString("skor")
                    });
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
            System.out.println("Notif : " + e);
        }
        hitungSkorDecu();
        
        //nilai skala kekuatan otot
        if (cmbNilai.getSelectedIndex() == 0) {
            Tketerangan.setText("");
        } else if (cmbNilai.getSelectedIndex() == 1) {
            Tketerangan.setText("Mampu menggerakkan persendian dalam lingkup gerak penuh, mampu melawan gaya gravitasi, mampu melawan dengan tahanan penuh");
        } else if (cmbNilai.getSelectedIndex() == 2) {
            Tketerangan.setText("Mampu menggerakkan persendian dengan gaya gravitasi. Mampu melawan dengan tahanan sedang");
        } else if (cmbNilai.getSelectedIndex() == 3) {
            Tketerangan.setText("Mampu melakukan gerakan mengangkat ekstremitas/tetapi tidak bisa melawan tahanan");
        } else if (cmbNilai.getSelectedIndex() == 4) {
            Tketerangan.setText("Mampu melakukan gerakan tetapi tidak bisa melawan tahanan");
        } else if (cmbNilai.getSelectedIndex() == 5) {
            Tketerangan.setText("Kontraksi otot dapat di palpasi tanpa gerakan persendian");
        } else if (cmbNilai.getSelectedIndex() == 6) {
            Tketerangan.setText("Tidak ada kontraksi otot");
        }
        
        //kebutuhan edukasi
        if (obatObatan.equals("ya")) {
            ChkObat.setSelected(true);
        } else {
            ChkObat.setSelected(false);
        }
        
        if (perawatanLuka.equals("ya")) {
            ChkPerawatan.setSelected(true);
        } else {
            ChkPerawatan.setSelected(false);
        }
        
        if (manajemenNyeri.equals("ya")) {
            ChkManajemen.setSelected(true);
        } else {
            ChkManajemen.setSelected(false);
        }
        
        if (diet.equals("ya")) {
            ChkDiet.setSelected(true);
        } else {
            ChkDiet.setSelected(false);
        }
        
        if (fisio.equals("ya")) {
            ChkFisioterapi.setSelected(true);
        } else {
            ChkFisioterapi.setSelected(false);
        }
        
        //daftar masalah
        if (hipertermi.equals("ya")) {
            ChkHipertermi.setSelected(true);
        } else {
            ChkHipertermi.setSelected(false);
        }
        
        if (nyeri.equals("ya")) {
            ChkNyeri.setSelected(true);
        } else {
            ChkNyeri.setSelected(false);
        }
        
        if (resiko.equals("ya")) {
            ChkResiko.setSelected(true);
        } else {
            ChkResiko.setSelected(false);
        }
        
        if (kelebihan.equals("ya")) {
            ChkKelebihan.setSelected(true);
        } else {
            ChkKelebihan.setSelected(false);
        }
        
        if (bersihkan.equals("ya")) {
            ChkBersihkan.setSelected(true);
        } else {
            ChkBersihkan.setSelected(false);
        }
        
        if (pola.equals("ya")) {
            ChkPola.setSelected(true);
        } else {
            ChkPola.setSelected(false);
        }
        
        if (gangguan.equals("ya")) {
            ChkGangguan.setSelected(true);
        } else {
            ChkGangguan.setSelected(false);
        }
        
        if (cemas.equals("ya")) {
            ChkCemas.setSelected(true);
        } else {
            ChkCemas.setSelected(false);
        }
        
        if (ketidakseimbangan.equals("ya")) {
            ChkKetidakseimbangan.setSelected(true);
        } else {
            ChkKetidakseimbangan.setSelected(false);
        }
        
        if (perubahan.equals("ya")) {
            ChkPerubahan.setSelected(true);
        } else {
            ChkPerubahan.setSelected(false);
        }
        
        if (penurunan.equals("ya")) {
            ChkPenurunan.setSelected(true);
        } else {
            ChkPenurunan.setSelected(false);
        }
        
        if (kerusakan.equals("ya")) {
            ChkKerusakan.setSelected(true);
        } else {
            ChkKerusakan.setSelected(false);
        }
        
        if (intoleransi.equals("ya")) {
            ChkIntoleransi.setSelected(true);
        } else {
            ChkIntoleransi.setSelected(false);
        }
        
        if (kurang.equals("ya")) {
            ChkKurang.setSelected(true);
        } else {
            ChkKurang.setSelected(false);
        }
        
        if (perluMPP.equals("ya")) {
            ChkMPP.setSelected(true);
        } else {
            ChkMPP.setSelected(false);
        }
        
        if (perluDP.equals("ya")) {
            ChkDP.setSelected(true);
        } else {
            ChkDP.setSelected(false);
        }
    }
    
    private void hitungSkorGizi() {
        int A, B, C, Total;
        A = Integer.parseInt(skorGizi1.getText());
        B = Integer.parseInt(skorYaGizi1.getText());
        C = Integer.parseInt(skorGizi2.getText());

        Total = 0;
        Total = A + B + C;
        TotSkorGizi.setText(Valid.SetAngka2(Total));

        if (Total == 0 || Total == 1) {
            kesimpulanGizi.setText("Pasien tidak beresiko malnutrisi");
        } else if (Total >= 2) {
            kesimpulanGizi.setText(">= 2 : Pasien beresiko malnutrisi, konsul ke Ahli Gizi");
        }
    }
    
    private void hitungSkorRJ() {
        skor = 0;
        for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
            if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
            }
        }
        
        TotSkorRJ.setText(Valid.SetAngka2(skor));
        if (skor > 45) {
            kesimpulanResikoJatuh.setText("Resiko Tinggi : > 45, pasang kancing berwarna kuning");
        } else if (skor >= 25 && skor <= 45) {
            kesimpulanResikoJatuh.setText("Resiko Sedang : 25-45, pasang kancing berwarna kuning");
        } else if (skor >= 0 && skor <= 24) {
            kesimpulanResikoJatuh.setText("Resiko Rendah : 0-24");
        }
    }
    
    public void tampilFaktorResiko() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                    + "asesmen = 'Dewasa Ranap' and faktor_resiko like ? or "
                    + "asesmen = 'Dewasa Ranap' and skala like ? order by kode_resiko");
            try {
                ps1.setString(1, "%" + TCariResiko.getText().trim() + "%");
                ps1.setString(2, "%" + TCariResiko.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("kode_resiko"),
                        rs1.getString("asesmen"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("skala"),
                        rs1.getString("skor")
                    });
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
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungSkorDecu() {
        skorDecu = 0;
        for (i = 0; i < tbFaktorDecu.getRowCount(); i++) {
            if (tbFaktorDecu.getValueAt(i, 0).toString().equals("true")) {
                skorDecu = skorDecu + Integer.parseInt(tbFaktorDecu.getValueAt(i, 4).toString());
            }
        }
        
        TotSkorDecu.setText(Valid.SetAngka2(skorDecu));
        if (skorDecu <= 14) {
            kesimpulanResikoDecu.setText("Score <= 14, Pasien beresiko decubitus");
        } else {
            kesimpulanResikoDecu.setText("Pasien tidak beresiko decubitus");
        } 
    }
    
    public void tampilResikoDecubitus() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("select * from master_data_decubitus where "
                    + "parameter like ? or "
                    + "penilaian like ? order by kode_decubitus");
            try {
                ps2.setString(1, "%" + TCariDecu.getText().trim() + "%");
                ps2.setString(2, "%" + TCariDecu.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new Object[]{
                        false,
                        rs2.getString("kode_decubitus"),
                        rs2.getString("parameter"),
                        rs2.getString("penilaian"),
                        rs2.getString("skor")
                    });
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

    private void hitungIMT() {
        try {
            double A, B, C, D, Total;
            if (TbbMasuk.getText().equals("")) {
                TbbMasuk.setText("0");
            }

            if (Ttb.getText().equals("")) {
                Ttb.setText("0");
            }

            A = Double.parseDouble(TbbMasuk.getText());
            B = Double.parseDouble(Ttb.getText());
            C = B / 100;
            D = C * C;

            Total = 0;
            Total = A / D;
            
            if (Valid.SetAngka4(Total).equals("NaN")) {
                Timt.setText("0");
            } else {
                Timt.setText(Valid.SetAngka4(Total));
            }
            
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            JOptionPane.showMessageDialog(rootPane, "Silahkan koreksi lagi angka BB masuk RS & tinggi badannya,    \n"
                    + "jika menggunakan koma, gantilah tanda koma dengan titik sebagai komanya !!");
        }
    }
    
    private void bersihkanString() {
        nip = "";
        kdkamar = "";
        alergiObat = "";
        alergiMakanan = "";
        alergiLain = "";
        gelang = "";
        ibadah = "";
        obatObatan = "";
        perawatanLuka = "";
        manajemenNyeri = "";
        diet = "";
        fisio = "";
        hipertermi = "";
        nyeri = "";
        resiko = "";
        kelebihan = "";
        bersihkan = "";
        pola = "";
        gangguan = "";
        cemas = "";
        ketidakseimbangan = "";
        perubahan = "";
        penurunan = "";
        kerusakan = "";
        intoleransi = "";
        kurang = "";
        perluMPP = "";
        perluDP = "";
    }
}
