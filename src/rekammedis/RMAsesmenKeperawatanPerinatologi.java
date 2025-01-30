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
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenKeperawatanPerinatologi extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);    
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private String nip = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenKeperawatanPerinatologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Masuk Ruangan", "Tgl. Masuk", "Jam Masuk", "Tiba Dg. Cara", "Melalui", "Keluhan Utama",
            "Tgl. Asesmen", "Jam", "Nama Perawat",
            "kd_kamar_msk", "tgl_msk_ruangan", "jam_msk_ruangan", "tiba_diruang", "tiba_diruang_lainnya", "msk_melalui", "keluhan_utama", "riwayat_alergi", "alergi_obat",
            "nm_alergi_obat", "reaksi_alergi_obat", "alergi_makanan", "nm_alergi_makanan", "reaksi_alergi_makanan", "alergi_lainnya", "nm_alergi_lainnya", "reaksi_alergi_lainnya",
            "pasang_gelang_tanda", "alergi_diberitahukan", "riwayat_penyakit_sekarang", "campak", "diare", "difteri", "tetanus", "kejang", "tbc", "batuk_rejan", "sesak",
            "kuning", "demam_tifoid", "urtikaria", "cacing", "sakit_tenggorokan", "lainya", "kalimat_lainya", "kesadaran", "gcs_e", "gcs_m", "gcs_v", "tensi", "temp",
            "hr", "rr", "bb_sebelum_skt", "bb_msk_rs", "tb", "imt", "crt", "spo2", "pernafasan", "penglihatan", "pendengaran", "mulut", "reflek_menelan", "bicara",
            "defekasi", "miksi", "gastrointestinal", "pola_tidur", "makan", "berpakaian", "buang_air", "mandi", "berpindah", "kesimpulan", "keluarga_terdekat",
            "hubungan", "tinggal_dengan", "tinggal_dengan_lain", "curiga_penganiayaan", "butuh_bantuan_ibadah", "status_emosional", "hepatitis_b_umur1",
            "hepatitis_b_umur2", "hepatitis_b_umur3", "hepatitis_b_umur4", "hepatitis_b_umur_ulang", "polio_umur1", "polio_umur2", "polio_umur3", "polio_umur4",
            "polio_umur_ulang", "bcg_umur_dasar", "bcg_umur_ulang", "dpt_umur1", "dpt_umur2", "dpt_umur3", "dpt_umur_ulang", "meningitis_umur1", "meningitis_umur2",
            "meningitis_umur3", "meningitis_umur_ulang", "campak_umur_dasar", "campak_umur_ulang", "mk_menegakkan_kepala", "mk_tengkurang", "mk_duduk", "mk_berjalan",
            "mh_menggenggam_mainan", "mh_mencari_benda", "mh_mencoret", "mh_mengenal_warna", "bicara_berceloteh", "bicara_mengucapkan", "bicara_menyebutkan",
            "bicara_menyebut_kata", "se_tersenyum", "se_bermain", "se_mengenal", "se_makan_minum", "saat_ini_sekolah", "kelas", "onset", "provocation", "provocation_lain",
            "quality", "quality_lain", "radiation", "severity", "time", "time_lama", "relief", "relief_lain", "asociated_sign", "asociated_sign_lain", "skala_nyeri",
            "skrining_gizi_1", "skrining_gizi_2", "skrining_gizi_3", "skrining_gizi_4", "tindakan_pencegahan", "obat_obatan", "perawatan_luka", "manajemen_lain",
            "menejemen_nyeri", "diet_nutrisi", "fisioterapi", "rehabilitasi_lain", "hipertermi", "nyeri", "kurang_volume_cairan", "lebih_volume_cairan",
            "bersihkan_jln_nfs_tdk_efektif", "pola_nfs_tdk_efektif", "gangguan_pertukaran_gas", "cemas", "ketidakseimbangan_nutrisi", "perubahan_perfusi",
            "penurunan_curah_jantung", "kerusakan_integritas", "intoleransi_aktifitas", "kurang_perawatan_diri", "daftar_masalah_lain", "tgl_asesmen", "jam_asesmen",
            "nip_perawat", "waktu_simpan", "gastrointestinal_lainnya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 171; i++) {
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
            } else if (i == 127) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 128) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 129) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 130) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 131) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 132) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 133) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 134) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 135) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 136) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 137) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 138) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 139) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 140) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 141) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 142) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 143) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 144) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 145) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 146) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 147) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 148) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 149) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 150) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 151) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 152) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 153) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 154) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 155) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 156) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 157) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 158) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 159) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 160) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 161) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 162) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 163) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 164) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 165) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 166) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 167) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 168) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 169) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 170) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDokumenJangMed = new javax.swing.JMenuItem();
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
        TrgRawat = new widget.TextBox();
        TtglMsk = new widget.Tanggal();
        jLabel20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel95 = new widget.Label();
        TtglAses = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel99 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel8 = new widget.Label();
        cmbSumber = new widget.ComboBox();
        TlainSumber = new widget.TextBox();
        jLabel9 = new widget.Label();
        cmbRujukan = new widget.ComboBox();
        jLabel10 = new widget.Label();
        cmbJnsRujukan = new widget.ComboBox();
        jLabel11 = new widget.Label();
        TdiagnosaRujukan = new widget.TextBox();
        jLabel12 = new widget.Label();
        Tkeluhan = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        TnmIdentitas = new widget.TextBox();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel17 = new widget.Label();
        cmbPendidikan = new widget.ComboBox();
        cmbAgama = new widget.ComboBox();
        jLabel22 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        Tbbl = new widget.TextBox();
        jLabel25 = new widget.Label();
        Tpb = new widget.TextBox();
        jLabel26 = new widget.Label();
        Tlk = new widget.TextBox();
        jLabel27 = new widget.Label();
        Tld = new widget.TextBox();
        jLabel28 = new widget.Label();
        Tlp = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        Tll = new widget.TextBox();
        Tkk = new widget.TextBox();
        jLabel32 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel33 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel34 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        chkJernih = new widget.CekBox();
        chkKeruh = new widget.CekBox();
        chkLumpur = new widget.CekBox();
        chkHijau = new widget.CekBox();
        chkBerbau = new widget.CekBox();
        chkKering = new widget.CekBox();
        jLabel37 = new widget.Label();
        cmbAnus = new widget.ComboBox();
        jLabel38 = new widget.Label();
        cmbBab = new widget.ComboBox();
        jLabel39 = new widget.Label();
        cmbBak = new widget.ComboBox();
        jLabel40 = new widget.Label();
        Tkelainan = new widget.TextBox();
        jLabel41 = new widget.Label();
        Tjenkel = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        TanakKe = new widget.TextBox();
        jLabel45 = new widget.Label();
        TumurHamil = new widget.TextBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        cmbRiwPenyakitIbu = new widget.ComboBox();
        chkHipertensi = new widget.CekBox();
        chkDM = new widget.CekBox();
        chkPMS = new widget.CekBox();
        chkTBC = new widget.CekBox();
        chkAsma = new widget.CekBox();
        chkHepB = new widget.CekBox();
        chkLainyaRiwayat = new widget.CekBox();
        TlainRiwayat = new widget.TextBox();
        jLabel48 = new widget.Label();
        cmbMasih = new widget.ComboBox();
        jLabel49 = new widget.Label();
        Tobat = new widget.TextBox();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        TdiagnosaIbu = new widget.TextBox();
        jLabel52 = new widget.Label();
        TtglLahir = new widget.Tanggal();
        jLabel53 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel54 = new widget.Label();
        TkeadaanSaat = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tas = new widget.TextBox();
        jLabel56 = new widget.Label();
        chkSpontan = new widget.CekBox();
        chkVakum = new widget.CekBox();
        chkForcep = new widget.CekBox();
        chkSectio = new widget.CekBox();
        chkLainyaCara = new widget.CekBox();
        TlainCara = new widget.TextBox();
        jLabel57 = new widget.Label();
        chkSegar = new widget.CekBox();
        chkLayu = new widget.CekBox();
        chkSimpul = new widget.CekBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        chkIbuDemam = new widget.CekBox();
        chkKpd24 = new widget.CekBox();
        chkKetuban = new widget.CekBox();
        chkChorio = new widget.CekBox();
        chkFetal = new widget.CekBox();
        jLabel60 = new widget.Label();
        chkKpd12 = new widget.CekBox();
        chkAsfiksia = new widget.CekBox();
        chkBblr = new widget.CekBox();
        chkIsk = new widget.CekBox();
        chkUK = new widget.CekBox();
        chkGemeli = new widget.CekBox();
        chkKeputihan = new widget.CekBox();
        chkSuhuIbu = new widget.CekBox();
        jLabel61 = new widget.Label();
        cmbRiwAlergi = new widget.ComboBox();
        chkObatAlergi = new widget.CekBox();
        TobatAlergi = new widget.TextBox();
        chkMakananAlergi = new widget.CekBox();
        TmakananAlergi = new widget.TextBox();
        chkLainAlergi = new widget.CekBox();
        TlainyaAlergi = new widget.TextBox();
        jLabel62 = new widget.Label();
        Treaksi = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        cmbMasalah = new widget.ComboBox();
        cmbAdaPerkawinan = new widget.ComboBox();
        TlainMasalah = new widget.TextBox();
        jLabel65 = new widget.Label();
        cmbMengalami = new widget.ComboBox();
        cmbAdaMengalami = new widget.ComboBox();
        cmbDialami = new widget.ComboBox();
        jLabel66 = new widget.Label();
        cmbTrauma = new widget.ComboBox();
        TjelaskanTrauma = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        cmbGangguan = new widget.ComboBox();
        jLabel69 = new widget.Label();
        cmbKonsultasi = new widget.ComboBox();
        jLabel70 = new widget.Label();
        cmbPenerimaan = new widget.ComboBox();
        jLabel71 = new widget.Label();
        chkSuami = new widget.CekBox();
        chkOrangTua = new widget.CekBox();
        chkKeluarga = new widget.CekBox();
        chkLainDukungan = new widget.CekBox();
        TlainDukungan = new widget.TextBox();
        jLabel72 = new widget.Label();
        jLabel73 = new widget.Label();
        cmbStatusNikah = new widget.ComboBox();
        Tkali = new widget.TextBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbHubungan = new widget.ComboBox();
        jLabel76 = new widget.Label();
        cmbTinggal = new widget.ComboBox();
        TlainTinggal = new widget.TextBox();
        jLabel77 = new widget.Label();
        cmbTempat = new widget.ComboBox();
        TlainTempat = new widget.TextBox();
        jLabel78 = new widget.Label();
        jLabel79 = new widget.Label();
        TnmKerabat = new widget.TextBox();
        jLabel80 = new widget.Label();
        ThubKerabat = new widget.TextBox();
        jLabel81 = new widget.Label();
        TtelpKerabat = new widget.TextBox();
        jLabel82 = new widget.Label();
        jLabel83 = new widget.Label();
        TkegiatanAgama = new widget.TextBox();
        jLabel84 = new widget.Label();
        TkegiatanSpiritual = new widget.TextBox();
        jLabel85 = new widget.Label();
        jLabel86 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel87 = new widget.Label();
        jLabel88 = new widget.Label();
        cmbCrying = new widget.ComboBox();
        TskorCrying = new widget.TextBox();
        jLabel89 = new widget.Label();
        jLabel90 = new widget.Label();
        cmbRequires = new widget.ComboBox();
        jLabel91 = new widget.Label();
        TskorRequires = new widget.TextBox();
        jLabel92 = new widget.Label();
        cmbIncreased = new widget.ComboBox();
        jLabel93 = new widget.Label();
        TskorIncreased = new widget.TextBox();
        jLabel94 = new widget.Label();
        cmbExpresion = new widget.ComboBox();
        jLabel97 = new widget.Label();
        TskorExpresion = new widget.TextBox();
        jLabel98 = new widget.Label();
        cmbSleepless = new widget.ComboBox();
        jLabel100 = new widget.Label();
        TskorSleepless = new widget.TextBox();
        jLabel101 = new widget.Label();
        TtotSkor = new widget.TextBox();
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
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDokumenJangMed.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnDokumenJangMed.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnDokumenJangMed.setText("Dokumen Penunjang Medis");
        MnDokumenJangMed.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnDokumenJangMed.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnDokumenJangMed.setIconTextGap(5);
        MnDokumenJangMed.setName("MnDokumenJangMed"); // NOI18N
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(190, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Perinatologi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2610));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 407, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(568, 38, 60, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 38, 450, 23);

        TtglMsk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2025" }));
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

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Tgl. Asesmen : ");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(0, 2573, 100, 23);

        TtglAses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2025" }));
        TtglAses.setDisplayFormat("dd-MM-yyyy");
        TtglAses.setName("TtglAses"); // NOI18N
        TtglAses.setOpaque(false);
        TtglAses.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAses);
        TtglAses.setBounds(103, 2573, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(195, 2573, 40, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(238, 2573, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(290, 2573, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(343, 2573, 45, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama Perawat : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(390, 2573, 100, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(490, 2573, 430, 23);

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
        BtnPerawat.setBounds(920, 2573, 28, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sumber Data : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        cmbSumber.setForeground(new java.awt.Color(0, 0, 0));
        cmbSumber.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pasien", "Keluarga", "Lainnya" }));
        cmbSumber.setName("cmbSumber"); // NOI18N
        cmbSumber.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSumberActionPerformed(evt);
            }
        });
        FormInput.add(cmbSumber);
        cmbSumber.setBounds(114, 66, 75, 23);

        TlainSumber.setBackground(new java.awt.Color(245, 250, 240));
        TlainSumber.setForeground(new java.awt.Color(0, 0, 0));
        TlainSumber.setName("TlainSumber"); // NOI18N
        TlainSumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainSumberKeyPressed(evt);
            }
        });
        FormInput.add(TlainSumber);
        TlainSumber.setBounds(195, 66, 368, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jenis Rujukan : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(200, 94, 90, 23);

        cmbRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbRujukan.setName("cmbRujukan"); // NOI18N
        cmbRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRujukanActionPerformed(evt);
            }
        });
        FormInput.add(cmbRujukan);
        cmbRujukan.setBounds(114, 94, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Rujukan : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 110, 23);

        cmbJnsRujukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsRujukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "RS", "Puskesmas", "Dokter" }));
        cmbJnsRujukan.setName("cmbJnsRujukan"); // NOI18N
        cmbJnsRujukan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbJnsRujukan);
        cmbJnsRujukan.setBounds(293, 94, 86, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Diagnosa Rujukan : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 122, 110, 23);

        TdiagnosaRujukan.setBackground(new java.awt.Color(245, 250, 240));
        TdiagnosaRujukan.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaRujukan.setName("TdiagnosaRujukan"); // NOI18N
        TdiagnosaRujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaRujukanKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaRujukan);
        TdiagnosaRujukan.setBounds(114, 122, 610, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("1. KELUHAN UTAMA :");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 150, 160, 23);

        Tkeluhan.setBackground(new java.awt.Color(245, 250, 240));
        Tkeluhan.setForeground(new java.awt.Color(0, 0, 0));
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        FormInput.add(Tkeluhan);
        Tkeluhan.setBounds(164, 150, 560, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("2. IDENTITAS (Orang Tua/Keluarga)");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 178, 246, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Nama : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 206, 110, 23);

        TnmIdentitas.setBackground(new java.awt.Color(245, 250, 240));
        TnmIdentitas.setForeground(new java.awt.Color(0, 0, 0));
        TnmIdentitas.setName("TnmIdentitas"); // NOI18N
        TnmIdentitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmIdentitasKeyPressed(evt);
            }
        });
        FormInput.add(TnmIdentitas);
        TnmIdentitas.setBounds(114, 206, 610, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Pendidikan : ");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 234, 110, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Pekerjaan :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(220, 234, 70, 23);

        Tpekerjaan.setBackground(new java.awt.Color(245, 250, 240));
        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        Tpekerjaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanKeyPressed(evt);
            }
        });
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(294, 234, 430, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Agama : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 262, 110, 23);

        cmbPendidikan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendidikan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Sekolah", "TK", "SD", "SMP", "SMA", "D1", "D2", "D3", "D4", "S1", "S2", "S3" }));
        cmbPendidikan.setName("cmbPendidikan"); // NOI18N
        FormInput.add(cmbPendidikan);
        cmbPendidikan.setBounds(114, 234, 100, 23);

        cmbAgama.setBackground(new java.awt.Color(245, 253, 240));
        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "ISLAM", "KRISTEN", "KATOLIK", "HINDU", "BUDHA", "KONG HU CHU" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(114, 262, 130, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Alamat : ");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(245, 262, 60, 23);

        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(308, 262, 416, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("3. PEMERIKSAAN FISIK");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 290, 170, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("BBL : ");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 318, 110, 23);

        Tbbl.setBackground(new java.awt.Color(245, 250, 240));
        Tbbl.setForeground(new java.awt.Color(0, 0, 0));
        Tbbl.setName("Tbbl"); // NOI18N
        Tbbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbblKeyPressed(evt);
            }
        });
        FormInput.add(Tbbl);
        Tbbl.setBounds(114, 318, 50, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("gram     PB :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(170, 318, 60, 23);

        Tpb.setBackground(new java.awt.Color(245, 250, 240));
        Tpb.setForeground(new java.awt.Color(0, 0, 0));
        Tpb.setName("Tpb"); // NOI18N
        Tpb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpbKeyPressed(evt);
            }
        });
        FormInput.add(Tpb);
        Tpb.setBounds(234, 318, 60, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("cm      LK : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(300, 318, 53, 23);

        Tlk.setBackground(new java.awt.Color(245, 250, 240));
        Tlk.setForeground(new java.awt.Color(0, 0, 0));
        Tlk.setName("Tlk"); // NOI18N
        Tlk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlkKeyPressed(evt);
            }
        });
        FormInput.add(Tlk);
        Tlk.setBounds(355, 318, 60, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("cm      LD : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(420, 318, 53, 23);

        Tld.setBackground(new java.awt.Color(245, 250, 240));
        Tld.setForeground(new java.awt.Color(0, 0, 0));
        Tld.setName("Tld"); // NOI18N
        Tld.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TldKeyPressed(evt);
            }
        });
        FormInput.add(Tld);
        Tld.setBounds(475, 318, 60, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("cm      LP : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(540, 318, 53, 23);

        Tlp.setBackground(new java.awt.Color(245, 250, 240));
        Tlp.setForeground(new java.awt.Color(0, 0, 0));
        Tlp.setName("Tlp"); // NOI18N
        Tlp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlpKeyPressed(evt);
            }
        });
        FormInput.add(Tlp);
        Tlp.setBounds(595, 318, 60, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("cm");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(660, 318, 20, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("LL : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 346, 110, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("cm     KK :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(170, 346, 50, 23);

        Tll.setBackground(new java.awt.Color(245, 250, 240));
        Tll.setForeground(new java.awt.Color(0, 0, 0));
        Tll.setName("Tll"); // NOI18N
        Tll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TllKeyPressed(evt);
            }
        });
        FormInput.add(Tll);
        Tll.setBounds(114, 346, 50, 23);

        Tkk.setBackground(new java.awt.Color(245, 250, 240));
        Tkk.setForeground(new java.awt.Color(0, 0, 0));
        Tkk.setName("Tkk"); // NOI18N
        Tkk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkkKeyPressed(evt);
            }
        });
        FormInput.add(Tkk);
        Tkk.setBounds(220, 346, 60, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel32.setText("cm      Nadi : ");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(285, 346, 63, 23);

        Tnadi.setBackground(new java.awt.Color(245, 250, 240));
        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(350, 346, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("x/menit     RR : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(415, 346, 77, 23);

        Trr.setBackground(new java.awt.Color(245, 250, 240));
        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(490, 346, 60, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("x/menit    Suhu : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(555, 346, 83, 23);

        Tsuhu.setBackground(new java.awt.Color(245, 250, 240));
        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(637, 346, 60, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("°C");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(702, 346, 20, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Amnion : ");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 374, 110, 23);

        chkJernih.setBackground(new java.awt.Color(255, 255, 250));
        chkJernih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJernih.setForeground(new java.awt.Color(0, 0, 0));
        chkJernih.setText("Jernih");
        chkJernih.setBorderPainted(true);
        chkJernih.setBorderPaintedFlat(true);
        chkJernih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJernih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJernih.setName("chkJernih"); // NOI18N
        chkJernih.setOpaque(false);
        chkJernih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJernih);
        chkJernih.setBounds(114, 374, 60, 23);

        chkKeruh.setBackground(new java.awt.Color(255, 255, 250));
        chkKeruh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeruh.setForeground(new java.awt.Color(0, 0, 0));
        chkKeruh.setText("Keruh");
        chkKeruh.setBorderPainted(true);
        chkKeruh.setBorderPaintedFlat(true);
        chkKeruh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeruh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeruh.setName("chkKeruh"); // NOI18N
        chkKeruh.setOpaque(false);
        chkKeruh.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeruh);
        chkKeruh.setBounds(180, 374, 60, 23);

        chkLumpur.setBackground(new java.awt.Color(255, 255, 250));
        chkLumpur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLumpur.setForeground(new java.awt.Color(0, 0, 0));
        chkLumpur.setText("Lumpur");
        chkLumpur.setBorderPainted(true);
        chkLumpur.setBorderPaintedFlat(true);
        chkLumpur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLumpur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLumpur.setName("chkLumpur"); // NOI18N
        chkLumpur.setOpaque(false);
        chkLumpur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLumpur);
        chkLumpur.setBounds(250, 374, 60, 23);

        chkHijau.setBackground(new java.awt.Color(255, 255, 250));
        chkHijau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHijau.setForeground(new java.awt.Color(0, 0, 0));
        chkHijau.setText("Hijau");
        chkHijau.setBorderPainted(true);
        chkHijau.setBorderPaintedFlat(true);
        chkHijau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHijau.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHijau.setName("chkHijau"); // NOI18N
        chkHijau.setOpaque(false);
        chkHijau.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHijau);
        chkHijau.setBounds(320, 374, 50, 23);

        chkBerbau.setBackground(new java.awt.Color(255, 255, 250));
        chkBerbau.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBerbau.setForeground(new java.awt.Color(0, 0, 0));
        chkBerbau.setText("Berbau");
        chkBerbau.setBorderPainted(true);
        chkBerbau.setBorderPaintedFlat(true);
        chkBerbau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBerbau.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBerbau.setName("chkBerbau"); // NOI18N
        chkBerbau.setOpaque(false);
        chkBerbau.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBerbau);
        chkBerbau.setBounds(380, 374, 60, 23);

        chkKering.setBackground(new java.awt.Color(255, 255, 250));
        chkKering.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKering.setForeground(new java.awt.Color(0, 0, 0));
        chkKering.setText("Kering");
        chkKering.setBorderPainted(true);
        chkKering.setBorderPaintedFlat(true);
        chkKering.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKering.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKering.setName("chkKering"); // NOI18N
        chkKering.setOpaque(false);
        chkKering.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKering);
        chkKering.setBounds(450, 374, 60, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Anus : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(0, 402, 110, 23);

        cmbAnus.setForeground(new java.awt.Color(0, 0, 0));
        cmbAnus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbAnus.setName("cmbAnus"); // NOI18N
        cmbAnus.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAnus);
        cmbAnus.setBounds(114, 402, 80, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("BAB : ");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(200, 402, 40, 23);

        cmbBab.setForeground(new java.awt.Color(0, 0, 0));
        cmbBab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbBab.setName("cmbBab"); // NOI18N
        cmbBab.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBab);
        cmbBab.setBounds(243, 402, 80, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("BAK : ");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(330, 402, 40, 23);

        cmbBak.setForeground(new java.awt.Color(0, 0, 0));
        cmbBak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbBak.setName("cmbBak"); // NOI18N
        cmbBak.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBak);
        cmbBak.setBounds(373, 402, 80, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Kelainan Bawaan : ");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 430, 110, 23);

        Tkelainan.setBackground(new java.awt.Color(245, 250, 240));
        Tkelainan.setForeground(new java.awt.Color(0, 0, 0));
        Tkelainan.setName("Tkelainan"); // NOI18N
        Tkelainan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkelainanKeyPressed(evt);
            }
        });
        FormInput.add(Tkelainan);
        Tkelainan.setBounds(114, 430, 440, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Jenis Kelamin : ");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(555, 430, 90, 23);

        Tjenkel.setEditable(false);
        Tjenkel.setBackground(new java.awt.Color(245, 250, 240));
        Tjenkel.setForeground(new java.awt.Color(0, 0, 0));
        Tjenkel.setName("Tjenkel"); // NOI18N
        FormInput.add(Tjenkel);
        Tjenkel.setBounds(645, 430, 80, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("4. RIWAYAT KESEHATAN");
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 458, 170, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("a. Riwayat Prenatal");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 486, 140, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Anak Ke : ");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 514, 110, 23);

        TanakKe.setBackground(new java.awt.Color(245, 250, 240));
        TanakKe.setForeground(new java.awt.Color(0, 0, 0));
        TanakKe.setName("TanakKe"); // NOI18N
        TanakKe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanakKeKeyPressed(evt);
            }
        });
        FormInput.add(TanakKe);
        TanakKe.setBounds(114, 514, 50, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Umur Kehamilan : ");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(165, 514, 110, 23);

        TumurHamil.setBackground(new java.awt.Color(245, 250, 240));
        TumurHamil.setForeground(new java.awt.Color(0, 0, 0));
        TumurHamil.setName("TumurHamil"); // NOI18N
        TumurHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurHamilKeyPressed(evt);
            }
        });
        FormInput.add(TumurHamil);
        TumurHamil.setBounds(275, 514, 50, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Minggu");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(330, 514, 40, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Riwayat Penyakit Ibu : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 542, 170, 23);

        cmbRiwPenyakitIbu.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwPenyakitIbu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbRiwPenyakitIbu.setName("cmbRiwPenyakitIbu"); // NOI18N
        cmbRiwPenyakitIbu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwPenyakitIbu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwPenyakitIbuActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwPenyakitIbu);
        cmbRiwPenyakitIbu.setBounds(172, 542, 60, 23);

        chkHipertensi.setBackground(new java.awt.Color(255, 255, 250));
        chkHipertensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHipertensi.setForeground(new java.awt.Color(0, 0, 0));
        chkHipertensi.setText("Hipertensi");
        chkHipertensi.setBorderPainted(true);
        chkHipertensi.setBorderPaintedFlat(true);
        chkHipertensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHipertensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHipertensi.setName("chkHipertensi"); // NOI18N
        chkHipertensi.setOpaque(false);
        chkHipertensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHipertensi);
        chkHipertensi.setBounds(240, 542, 80, 23);

        chkDM.setBackground(new java.awt.Color(255, 255, 250));
        chkDM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDM.setForeground(new java.awt.Color(0, 0, 0));
        chkDM.setText("DM");
        chkDM.setBorderPainted(true);
        chkDM.setBorderPaintedFlat(true);
        chkDM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDM.setName("chkDM"); // NOI18N
        chkDM.setOpaque(false);
        chkDM.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDM);
        chkDM.setBounds(330, 542, 40, 23);

        chkPMS.setBackground(new java.awt.Color(255, 255, 250));
        chkPMS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPMS.setForeground(new java.awt.Color(0, 0, 0));
        chkPMS.setText("PMS");
        chkPMS.setBorderPainted(true);
        chkPMS.setBorderPaintedFlat(true);
        chkPMS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPMS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPMS.setName("chkPMS"); // NOI18N
        chkPMS.setOpaque(false);
        chkPMS.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPMS);
        chkPMS.setBounds(380, 542, 45, 23);

        chkTBC.setBackground(new java.awt.Color(255, 255, 250));
        chkTBC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTBC.setForeground(new java.awt.Color(0, 0, 0));
        chkTBC.setText("TBC");
        chkTBC.setBorderPainted(true);
        chkTBC.setBorderPaintedFlat(true);
        chkTBC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTBC.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTBC.setName("chkTBC"); // NOI18N
        chkTBC.setOpaque(false);
        chkTBC.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTBC);
        chkTBC.setBounds(434, 542, 50, 23);

        chkAsma.setBackground(new java.awt.Color(255, 255, 250));
        chkAsma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsma.setForeground(new java.awt.Color(0, 0, 0));
        chkAsma.setText("Asma");
        chkAsma.setBorderPainted(true);
        chkAsma.setBorderPaintedFlat(true);
        chkAsma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsma.setName("chkAsma"); // NOI18N
        chkAsma.setOpaque(false);
        chkAsma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsma);
        chkAsma.setBounds(490, 542, 50, 23);

        chkHepB.setBackground(new java.awt.Color(255, 255, 250));
        chkHepB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHepB.setForeground(new java.awt.Color(0, 0, 0));
        chkHepB.setText("Hep B");
        chkHepB.setBorderPainted(true);
        chkHepB.setBorderPaintedFlat(true);
        chkHepB.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHepB.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHepB.setName("chkHepB"); // NOI18N
        chkHepB.setOpaque(false);
        chkHepB.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHepB);
        chkHepB.setBounds(550, 542, 56, 23);

        chkLainyaRiwayat.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaRiwayat.setText("Lainnya");
        chkLainyaRiwayat.setBorderPainted(true);
        chkLainyaRiwayat.setBorderPaintedFlat(true);
        chkLainyaRiwayat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaRiwayat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaRiwayat.setName("chkLainyaRiwayat"); // NOI18N
        chkLainyaRiwayat.setOpaque(false);
        chkLainyaRiwayat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaRiwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaRiwayatActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaRiwayat);
        chkLainyaRiwayat.setBounds(240, 570, 63, 23);

        TlainRiwayat.setBackground(new java.awt.Color(245, 250, 240));
        TlainRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        TlainRiwayat.setName("TlainRiwayat"); // NOI18N
        TlainRiwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainRiwayatKeyPressed(evt);
            }
        });
        FormInput.add(TlainRiwayat);
        TlainRiwayat.setBounds(304, 570, 420, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Masih Pengobatan : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 598, 170, 23);

        cmbMasih.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasih.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbMasih.setName("cmbMasih"); // NOI18N
        cmbMasih.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMasih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMasihActionPerformed(evt);
            }
        });
        FormInput.add(cmbMasih);
        cmbMasih.setBounds(172, 598, 60, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Obat : ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(235, 598, 40, 23);

        Tobat.setBackground(new java.awt.Color(245, 250, 240));
        Tobat.setForeground(new java.awt.Color(0, 0, 0));
        Tobat.setName("Tobat"); // NOI18N
        Tobat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatKeyPressed(evt);
            }
        });
        FormInput.add(Tobat);
        Tobat.setBounds(279, 598, 445, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("b. Riwayat Intranatal");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(0, 626, 140, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Diagnosa Ibu : ");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 654, 110, 23);

        TdiagnosaIbu.setBackground(new java.awt.Color(245, 250, 240));
        TdiagnosaIbu.setForeground(new java.awt.Color(0, 0, 0));
        TdiagnosaIbu.setName("TdiagnosaIbu"); // NOI18N
        TdiagnosaIbu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaIbuKeyPressed(evt);
            }
        });
        FormInput.add(TdiagnosaIbu);
        TdiagnosaIbu.setBounds(114, 654, 610, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Tgl. Lahir : ");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 682, 110, 23);

        TtglLahir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2025" }));
        TtglLahir.setDisplayFormat("dd-MM-yyyy");
        TtglLahir.setName("TtglLahir"); // NOI18N
        TtglLahir.setOpaque(false);
        TtglLahir.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(114, 682, 90, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Jam :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(205, 682, 40, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(250, 682, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(302, 682, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(355, 682, 45, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Keadaan Saat Lahir :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(400, 682, 120, 23);

        TkeadaanSaat.setBackground(new java.awt.Color(245, 250, 240));
        TkeadaanSaat.setForeground(new java.awt.Color(0, 0, 0));
        TkeadaanSaat.setName("TkeadaanSaat"); // NOI18N
        TkeadaanSaat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeadaanSaatKeyPressed(evt);
            }
        });
        FormInput.add(TkeadaanSaat);
        TkeadaanSaat.setBounds(524, 682, 200, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("AS :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(480, 710, 40, 23);

        Tas.setBackground(new java.awt.Color(245, 250, 240));
        Tas.setForeground(new java.awt.Color(0, 0, 0));
        Tas.setName("Tas"); // NOI18N
        Tas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TasKeyPressed(evt);
            }
        });
        FormInput.add(Tas);
        Tas.setBounds(524, 710, 200, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Cara Persalinan : ");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(0, 738, 110, 23);

        chkSpontan.setBackground(new java.awt.Color(255, 255, 250));
        chkSpontan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpontan.setForeground(new java.awt.Color(0, 0, 0));
        chkSpontan.setText("Spontan");
        chkSpontan.setBorderPainted(true);
        chkSpontan.setBorderPaintedFlat(true);
        chkSpontan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpontan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpontan.setName("chkSpontan"); // NOI18N
        chkSpontan.setOpaque(false);
        chkSpontan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpontan);
        chkSpontan.setBounds(114, 738, 70, 23);

        chkVakum.setBackground(new java.awt.Color(255, 255, 250));
        chkVakum.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVakum.setForeground(new java.awt.Color(0, 0, 0));
        chkVakum.setText("Vakum");
        chkVakum.setBorderPainted(true);
        chkVakum.setBorderPaintedFlat(true);
        chkVakum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVakum.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVakum.setName("chkVakum"); // NOI18N
        chkVakum.setOpaque(false);
        chkVakum.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkVakum);
        chkVakum.setBounds(190, 738, 60, 23);

        chkForcep.setBackground(new java.awt.Color(255, 255, 250));
        chkForcep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkForcep.setForeground(new java.awt.Color(0, 0, 0));
        chkForcep.setText("Forcep Ekstraksi");
        chkForcep.setBorderPainted(true);
        chkForcep.setBorderPaintedFlat(true);
        chkForcep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkForcep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkForcep.setName("chkForcep"); // NOI18N
        chkForcep.setOpaque(false);
        chkForcep.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkForcep);
        chkForcep.setBounds(255, 738, 104, 23);

        chkSectio.setBackground(new java.awt.Color(255, 255, 250));
        chkSectio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSectio.setForeground(new java.awt.Color(0, 0, 0));
        chkSectio.setText("Sectio Caesarea");
        chkSectio.setBorderPainted(true);
        chkSectio.setBorderPaintedFlat(true);
        chkSectio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSectio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSectio.setName("chkSectio"); // NOI18N
        chkSectio.setOpaque(false);
        chkSectio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSectio);
        chkSectio.setBounds(369, 738, 104, 23);

        chkLainyaCara.setBackground(new java.awt.Color(255, 255, 250));
        chkLainyaCara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainyaCara.setForeground(new java.awt.Color(0, 0, 0));
        chkLainyaCara.setText("Lainnya");
        chkLainyaCara.setBorderPainted(true);
        chkLainyaCara.setBorderPaintedFlat(true);
        chkLainyaCara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainyaCara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainyaCara.setName("chkLainyaCara"); // NOI18N
        chkLainyaCara.setOpaque(false);
        chkLainyaCara.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainyaCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaCaraActionPerformed(evt);
            }
        });
        FormInput.add(chkLainyaCara);
        chkLainyaCara.setBounds(480, 738, 63, 23);

        TlainCara.setBackground(new java.awt.Color(245, 250, 240));
        TlainCara.setForeground(new java.awt.Color(0, 0, 0));
        TlainCara.setName("TlainCara"); // NOI18N
        TlainCara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainCaraKeyPressed(evt);
            }
        });
        FormInput.add(TlainCara);
        TlainCara.setBounds(544, 738, 180, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Tali Pusat : ");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 766, 110, 23);

        chkSegar.setBackground(new java.awt.Color(255, 255, 250));
        chkSegar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSegar.setForeground(new java.awt.Color(0, 0, 0));
        chkSegar.setText("Segar");
        chkSegar.setBorderPainted(true);
        chkSegar.setBorderPaintedFlat(true);
        chkSegar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSegar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSegar.setName("chkSegar"); // NOI18N
        chkSegar.setOpaque(false);
        chkSegar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSegar);
        chkSegar.setBounds(114, 766, 70, 23);

        chkLayu.setBackground(new java.awt.Color(255, 255, 250));
        chkLayu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLayu.setForeground(new java.awt.Color(0, 0, 0));
        chkLayu.setText("Layu");
        chkLayu.setBorderPainted(true);
        chkLayu.setBorderPaintedFlat(true);
        chkLayu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLayu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLayu.setName("chkLayu"); // NOI18N
        chkLayu.setOpaque(false);
        chkLayu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLayu);
        chkLayu.setBounds(190, 766, 50, 23);

        chkSimpul.setBackground(new java.awt.Color(255, 255, 250));
        chkSimpul.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimpul.setForeground(new java.awt.Color(0, 0, 0));
        chkSimpul.setText("Simpul");
        chkSimpul.setBorderPainted(true);
        chkSimpul.setBorderPaintedFlat(true);
        chkSimpul.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimpul.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimpul.setName("chkSimpul"); // NOI18N
        chkSimpul.setOpaque(false);
        chkSimpul.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSimpul);
        chkSimpul.setBounds(255, 766, 60, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("c. Faktor Resiko Infeksi");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 794, 140, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Mayor : ");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(0, 822, 110, 23);

        chkIbuDemam.setBackground(new java.awt.Color(255, 255, 250));
        chkIbuDemam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIbuDemam.setForeground(new java.awt.Color(0, 0, 0));
        chkIbuDemam.setText("Ibu Demam > 38 °C");
        chkIbuDemam.setBorderPainted(true);
        chkIbuDemam.setBorderPaintedFlat(true);
        chkIbuDemam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIbuDemam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIbuDemam.setName("chkIbuDemam"); // NOI18N
        chkIbuDemam.setOpaque(false);
        chkIbuDemam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIbuDemam);
        chkIbuDemam.setBounds(114, 822, 120, 23);

        chkKpd24.setBackground(new java.awt.Color(255, 255, 250));
        chkKpd24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKpd24.setForeground(new java.awt.Color(0, 0, 0));
        chkKpd24.setText("KPD > 24 Jam");
        chkKpd24.setBorderPainted(true);
        chkKpd24.setBorderPaintedFlat(true);
        chkKpd24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKpd24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKpd24.setName("chkKpd24"); // NOI18N
        chkKpd24.setOpaque(false);
        chkKpd24.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKpd24);
        chkKpd24.setBounds(242, 822, 93, 23);

        chkKetuban.setBackground(new java.awt.Color(255, 255, 250));
        chkKetuban.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetuban.setForeground(new java.awt.Color(0, 0, 0));
        chkKetuban.setText("Ketuban Hijau");
        chkKetuban.setBorderPainted(true);
        chkKetuban.setBorderPaintedFlat(true);
        chkKetuban.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetuban.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetuban.setName("chkKetuban"); // NOI18N
        chkKetuban.setOpaque(false);
        chkKetuban.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetuban);
        chkKetuban.setBounds(342, 822, 95, 23);

        chkChorio.setBackground(new java.awt.Color(255, 255, 250));
        chkChorio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkChorio.setForeground(new java.awt.Color(0, 0, 0));
        chkChorio.setText("Chorioamnionitis");
        chkChorio.setBorderPainted(true);
        chkChorio.setBorderPaintedFlat(true);
        chkChorio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkChorio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkChorio.setName("chkChorio"); // NOI18N
        chkChorio.setOpaque(false);
        chkChorio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkChorio);
        chkChorio.setBounds(445, 822, 107, 23);

        chkFetal.setBackground(new java.awt.Color(255, 255, 250));
        chkFetal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFetal.setForeground(new java.awt.Color(0, 0, 0));
        chkFetal.setText("Fetal Distress");
        chkFetal.setBorderPainted(true);
        chkFetal.setBorderPaintedFlat(true);
        chkFetal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFetal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFetal.setName("chkFetal"); // NOI18N
        chkFetal.setOpaque(false);
        chkFetal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFetal);
        chkFetal.setBounds(560, 822, 100, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Minor : ");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(0, 850, 110, 23);

        chkKpd12.setBackground(new java.awt.Color(255, 255, 250));
        chkKpd12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKpd12.setForeground(new java.awt.Color(0, 0, 0));
        chkKpd12.setText("KPD < 12 Jam");
        chkKpd12.setBorderPainted(true);
        chkKpd12.setBorderPaintedFlat(true);
        chkKpd12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKpd12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKpd12.setName("chkKpd12"); // NOI18N
        chkKpd12.setOpaque(false);
        chkKpd12.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKpd12);
        chkKpd12.setBounds(114, 850, 90, 23);

        chkAsfiksia.setBackground(new java.awt.Color(255, 255, 250));
        chkAsfiksia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAsfiksia.setForeground(new java.awt.Color(0, 0, 0));
        chkAsfiksia.setText("Asfiksia");
        chkAsfiksia.setBorderPainted(true);
        chkAsfiksia.setBorderPaintedFlat(true);
        chkAsfiksia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAsfiksia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAsfiksia.setName("chkAsfiksia"); // NOI18N
        chkAsfiksia.setOpaque(false);
        chkAsfiksia.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAsfiksia);
        chkAsfiksia.setBounds(212, 850, 64, 23);

        chkBblr.setBackground(new java.awt.Color(255, 255, 250));
        chkBblr.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBblr.setForeground(new java.awt.Color(0, 0, 0));
        chkBblr.setText("BBLR");
        chkBblr.setBorderPainted(true);
        chkBblr.setBorderPaintedFlat(true);
        chkBblr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBblr.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBblr.setName("chkBblr"); // NOI18N
        chkBblr.setOpaque(false);
        chkBblr.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBblr);
        chkBblr.setBounds(284, 850, 50, 23);

        chkIsk.setBackground(new java.awt.Color(255, 255, 250));
        chkIsk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIsk.setForeground(new java.awt.Color(0, 0, 0));
        chkIsk.setText("ISK");
        chkIsk.setBorderPainted(true);
        chkIsk.setBorderPaintedFlat(true);
        chkIsk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIsk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIsk.setName("chkIsk"); // NOI18N
        chkIsk.setOpaque(false);
        chkIsk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIsk);
        chkIsk.setBounds(340, 850, 45, 23);

        chkUK.setBackground(new java.awt.Color(255, 255, 250));
        chkUK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkUK.setForeground(new java.awt.Color(0, 0, 0));
        chkUK.setText("UK < 37 Minggu");
        chkUK.setBorderPainted(true);
        chkUK.setBorderPaintedFlat(true);
        chkUK.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkUK.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkUK.setName("chkUK"); // NOI18N
        chkUK.setOpaque(false);
        chkUK.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkUK);
        chkUK.setBounds(390, 850, 102, 23);

        chkGemeli.setBackground(new java.awt.Color(255, 255, 250));
        chkGemeli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGemeli.setForeground(new java.awt.Color(0, 0, 0));
        chkGemeli.setText("Gemeli");
        chkGemeli.setBorderPainted(true);
        chkGemeli.setBorderPaintedFlat(true);
        chkGemeli.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGemeli.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGemeli.setName("chkGemeli"); // NOI18N
        chkGemeli.setOpaque(false);
        chkGemeli.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGemeli);
        chkGemeli.setBounds(500, 850, 55, 23);

        chkKeputihan.setBackground(new java.awt.Color(255, 255, 250));
        chkKeputihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeputihan.setForeground(new java.awt.Color(0, 0, 0));
        chkKeputihan.setText("Keputihan");
        chkKeputihan.setBorderPainted(true);
        chkKeputihan.setBorderPaintedFlat(true);
        chkKeputihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeputihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeputihan.setName("chkKeputihan"); // NOI18N
        chkKeputihan.setOpaque(false);
        chkKeputihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeputihan);
        chkKeputihan.setBounds(563, 850, 75, 23);

        chkSuhuIbu.setBackground(new java.awt.Color(255, 255, 250));
        chkSuhuIbu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuhuIbu.setForeground(new java.awt.Color(0, 0, 0));
        chkSuhuIbu.setText("Suhu Ibu > 37 °C");
        chkSuhuIbu.setBorderPainted(true);
        chkSuhuIbu.setBorderPaintedFlat(true);
        chkSuhuIbu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuhuIbu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuhuIbu.setName("chkSuhuIbu"); // NOI18N
        chkSuhuIbu.setOpaque(false);
        chkSuhuIbu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSuhuIbu);
        chkSuhuIbu.setBounds(645, 850, 110, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("d. Riwayat Alergi (Pada Orang Tua : Ayah/Ibu ; Saudara Kandung) :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 878, 360, 23);

        cmbRiwAlergi.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwAlergi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbRiwAlergi.setName("cmbRiwAlergi"); // NOI18N
        cmbRiwAlergi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRiwAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwAlergiActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwAlergi);
        cmbRiwAlergi.setBounds(365, 878, 60, 23);

        chkObatAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkObatAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObatAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkObatAlergi.setText("Obat");
        chkObatAlergi.setBorderPainted(true);
        chkObatAlergi.setBorderPaintedFlat(true);
        chkObatAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObatAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObatAlergi.setName("chkObatAlergi"); // NOI18N
        chkObatAlergi.setOpaque(false);
        chkObatAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkObatAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkObatAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkObatAlergi);
        chkObatAlergi.setBounds(114, 906, 50, 23);

        TobatAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TobatAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TobatAlergi.setName("TobatAlergi"); // NOI18N
        TobatAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TobatAlergi);
        TobatAlergi.setBounds(169, 906, 555, 23);

        chkMakananAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkMakananAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMakananAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkMakananAlergi.setText("Makanan");
        chkMakananAlergi.setBorderPainted(true);
        chkMakananAlergi.setBorderPaintedFlat(true);
        chkMakananAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMakananAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMakananAlergi.setName("chkMakananAlergi"); // NOI18N
        chkMakananAlergi.setOpaque(false);
        chkMakananAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkMakananAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMakananAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkMakananAlergi);
        chkMakananAlergi.setBounds(114, 934, 70, 23);

        TmakananAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TmakananAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TmakananAlergi.setName("TmakananAlergi"); // NOI18N
        TmakananAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmakananAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TmakananAlergi);
        TmakananAlergi.setBounds(189, 934, 535, 23);

        chkLainAlergi.setBackground(new java.awt.Color(255, 255, 250));
        chkLainAlergi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainAlergi.setForeground(new java.awt.Color(0, 0, 0));
        chkLainAlergi.setText("Lainnya");
        chkLainAlergi.setBorderPainted(true);
        chkLainAlergi.setBorderPaintedFlat(true);
        chkLainAlergi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainAlergi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainAlergi.setName("chkLainAlergi"); // NOI18N
        chkLainAlergi.setOpaque(false);
        chkLainAlergi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainAlergi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainAlergiActionPerformed(evt);
            }
        });
        FormInput.add(chkLainAlergi);
        chkLainAlergi.setBounds(114, 962, 65, 23);

        TlainyaAlergi.setBackground(new java.awt.Color(245, 250, 240));
        TlainyaAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TlainyaAlergi.setName("TlainyaAlergi"); // NOI18N
        TlainyaAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainyaAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TlainyaAlergi);
        TlainyaAlergi.setBounds(184, 962, 540, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Reaksi : ");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 990, 110, 23);

        Treaksi.setBackground(new java.awt.Color(245, 250, 240));
        Treaksi.setForeground(new java.awt.Color(0, 0, 0));
        Treaksi.setName("Treaksi"); // NOI18N
        Treaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreaksiKeyPressed(evt);
            }
        });
        FormInput.add(Treaksi);
        Treaksi.setBounds(114, 990, 610, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("5. KEBUTUHAN PSIKOLOGIS (Untuk Orang Tua : Ayah/Ibu)");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 1018, 350, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Masalah Perkawinan :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 1046, 170, 23);

        cmbMasalah.setForeground(new java.awt.Color(0, 0, 0));
        cmbMasalah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbMasalah.setName("cmbMasalah"); // NOI18N
        cmbMasalah.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMasalah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMasalahActionPerformed(evt);
            }
        });
        FormInput.add(cmbMasalah);
        cmbMasalah.setBounds(177, 1046, 80, 23);

        cmbAdaPerkawinan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdaPerkawinan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Cerai", "Istri Baru", "Simpanan", "Lain-lain" }));
        cmbAdaPerkawinan.setName("cmbAdaPerkawinan"); // NOI18N
        cmbAdaPerkawinan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAdaPerkawinan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAdaPerkawinanActionPerformed(evt);
            }
        });
        FormInput.add(cmbAdaPerkawinan);
        cmbAdaPerkawinan.setBounds(264, 1046, 80, 23);

        TlainMasalah.setBackground(new java.awt.Color(245, 250, 240));
        TlainMasalah.setForeground(new java.awt.Color(0, 0, 0));
        TlainMasalah.setName("TlainMasalah"); // NOI18N
        TlainMasalah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainMasalahKeyPressed(evt);
            }
        });
        FormInput.add(TlainMasalah);
        TlainMasalah.setBounds(349, 1046, 375, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Mengalami Kekerasan Fisik :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 1074, 170, 23);

        cmbMengalami.setForeground(new java.awt.Color(0, 0, 0));
        cmbMengalami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbMengalami.setName("cmbMengalami"); // NOI18N
        cmbMengalami.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMengalami.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMengalamiActionPerformed(evt);
            }
        });
        FormInput.add(cmbMengalami);
        cmbMengalami.setBounds(177, 1074, 80, 23);

        cmbAdaMengalami.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdaMengalami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mencederai Diri", "Mencederai Orang Lain" }));
        cmbAdaMengalami.setName("cmbAdaMengalami"); // NOI18N
        cmbAdaMengalami.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbAdaMengalami);
        cmbAdaMengalami.setBounds(264, 1074, 145, 23);

        cmbDialami.setForeground(new java.awt.Color(0, 0, 0));
        cmbDialami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pernah", "Tidak Pernah" }));
        cmbDialami.setName("cmbDialami"); // NOI18N
        cmbDialami.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDialami);
        cmbDialami.setBounds(415, 1074, 95, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Trauma Dalam Kehidupan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1102, 170, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        cmbTrauma.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTraumaActionPerformed(evt);
            }
        });
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(177, 1102, 80, 23);

        TjelaskanTrauma.setBackground(new java.awt.Color(245, 250, 240));
        TjelaskanTrauma.setForeground(new java.awt.Color(0, 0, 0));
        TjelaskanTrauma.setName("TjelaskanTrauma"); // NOI18N
        TjelaskanTrauma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjelaskanTraumaKeyPressed(evt);
            }
        });
        FormInput.add(TjelaskanTrauma);
        TjelaskanTrauma.setBounds(329, 1102, 395, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Jelaskan : ");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(260, 1102, 66, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Gangguan Tidur :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1130, 170, 23);

        cmbGangguan.setForeground(new java.awt.Color(0, 0, 0));
        cmbGangguan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbGangguan.setName("cmbGangguan"); // NOI18N
        cmbGangguan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbGangguan);
        cmbGangguan.setBounds(177, 1130, 80, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Konsultasi Dengan Psikologi/Psikiater :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(260, 1130, 200, 23);

        cmbKonsultasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKonsultasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ada", "Tidak Ada" }));
        cmbKonsultasi.setName("cmbKonsultasi"); // NOI18N
        cmbKonsultasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKonsultasi);
        cmbKonsultasi.setBounds(465, 1130, 80, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Penerimaan Terhadap Kondisi Bayi Saat Ini :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 1158, 240, 23);

        cmbPenerimaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenerimaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Menerima", "Tidak Menerima" }));
        cmbPenerimaan.setName("cmbPenerimaan"); // NOI18N
        cmbPenerimaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenerimaan);
        cmbPenerimaan.setBounds(247, 1158, 108, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Dukungan Sosial Dari :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1186, 170, 23);

        chkSuami.setBackground(new java.awt.Color(255, 255, 250));
        chkSuami.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuami.setForeground(new java.awt.Color(0, 0, 0));
        chkSuami.setText("Suami");
        chkSuami.setBorderPainted(true);
        chkSuami.setBorderPaintedFlat(true);
        chkSuami.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuami.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuami.setName("chkSuami"); // NOI18N
        chkSuami.setOpaque(false);
        chkSuami.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSuami);
        chkSuami.setBounds(177, 1186, 55, 23);

        chkOrangTua.setBackground(new java.awt.Color(255, 255, 250));
        chkOrangTua.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkOrangTua.setForeground(new java.awt.Color(0, 0, 0));
        chkOrangTua.setText("Orang Tua");
        chkOrangTua.setBorderPainted(true);
        chkOrangTua.setBorderPaintedFlat(true);
        chkOrangTua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOrangTua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOrangTua.setName("chkOrangTua"); // NOI18N
        chkOrangTua.setOpaque(false);
        chkOrangTua.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkOrangTua);
        chkOrangTua.setBounds(240, 1186, 80, 23);

        chkKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluarga.setText("Keluarga");
        chkKeluarga.setBorderPainted(true);
        chkKeluarga.setBorderPaintedFlat(true);
        chkKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluarga.setName("chkKeluarga"); // NOI18N
        chkKeluarga.setOpaque(false);
        chkKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeluarga);
        chkKeluarga.setBounds(330, 1186, 70, 23);

        chkLainDukungan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainDukungan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainDukungan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainDukungan.setText("Lain - Lain");
        chkLainDukungan.setBorderPainted(true);
        chkLainDukungan.setBorderPaintedFlat(true);
        chkLainDukungan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainDukungan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainDukungan.setName("chkLainDukungan"); // NOI18N
        chkLainDukungan.setOpaque(false);
        chkLainDukungan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainDukungan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainDukunganActionPerformed(evt);
            }
        });
        FormInput.add(chkLainDukungan);
        chkLainDukungan.setBounds(410, 1186, 75, 23);

        TlainDukungan.setBackground(new java.awt.Color(245, 250, 240));
        TlainDukungan.setForeground(new java.awt.Color(0, 0, 0));
        TlainDukungan.setName("TlainDukungan"); // NOI18N
        TlainDukungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainDukunganKeyPressed(evt);
            }
        });
        FormInput.add(TlainDukungan);
        TlainDukungan.setBounds(486, 1186, 238, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("6. RIWAYAT SOSIAL EKONOMI DAN SPIRITUAL (Orang Tua)");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1214, 350, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("a. Status Pernikahan :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 1242, 160, 23);

        cmbStatusNikah.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatusNikah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Single", "Menikah", "Bercerai" }));
        cmbStatusNikah.setName("cmbStatusNikah"); // NOI18N
        cmbStatusNikah.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbStatusNikah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusNikahActionPerformed(evt);
            }
        });
        FormInput.add(cmbStatusNikah);
        cmbStatusNikah.setBounds(164, 1242, 75, 23);

        Tkali.setBackground(new java.awt.Color(245, 250, 240));
        Tkali.setForeground(new java.awt.Color(0, 0, 0));
        Tkali.setName("Tkali"); // NOI18N
        Tkali.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkaliKeyPressed(evt);
            }
        });
        FormInput.add(Tkali);
        Tkali.setBounds(245, 1242, 50, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel74.setText("Kali");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(300, 1242, 40, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("b. Status Sosial : Hubungan Pasien Dengan Anggota Keluarga :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 1270, 355, 23);

        cmbHubungan.setForeground(new java.awt.Color(0, 0, 0));
        cmbHubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Baik", "Tidak Baik" }));
        cmbHubungan.setName("cmbHubungan"); // NOI18N
        cmbHubungan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbHubungan);
        cmbHubungan.setBounds(360, 1270, 80, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Tinggal Bersama :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 1298, 160, 23);

        cmbTinggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Suami", "Istri", "Suami/Istri", "Anak", "Orang Tua", "Sendiri", "Lain-lain" }));
        cmbTinggal.setName("cmbTinggal"); // NOI18N
        cmbTinggal.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTinggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinggalActionPerformed(evt);
            }
        });
        FormInput.add(cmbTinggal);
        cmbTinggal.setBounds(164, 1298, 85, 23);

        TlainTinggal.setBackground(new java.awt.Color(245, 250, 240));
        TlainTinggal.setForeground(new java.awt.Color(0, 0, 0));
        TlainTinggal.setName("TlainTinggal"); // NOI18N
        TlainTinggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainTinggalKeyPressed(evt);
            }
        });
        FormInput.add(TlainTinggal);
        TlainTinggal.setBounds(254, 1298, 470, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Tempat Tinggal :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1326, 160, 23);

        cmbTempat.setForeground(new java.awt.Color(0, 0, 0));
        cmbTempat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Rumah", "Apartemen", "Panti", "Lainnya" }));
        cmbTempat.setName("cmbTempat"); // NOI18N
        cmbTempat.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTempat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTempatActionPerformed(evt);
            }
        });
        FormInput.add(cmbTempat);
        cmbTempat.setBounds(164, 1326, 85, 23);

        TlainTempat.setBackground(new java.awt.Color(245, 250, 240));
        TlainTempat.setForeground(new java.awt.Color(0, 0, 0));
        TlainTempat.setName("TlainTempat"); // NOI18N
        TlainTempat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainTempatKeyPressed(evt);
            }
        });
        FormInput.add(TlainTempat);
        TlainTempat.setBounds(254, 1326, 470, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Kerabat Terdekat Yang Dapat Dihubungi :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 1354, 250, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Nama :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1382, 160, 23);

        TnmKerabat.setBackground(new java.awt.Color(245, 250, 240));
        TnmKerabat.setForeground(new java.awt.Color(0, 0, 0));
        TnmKerabat.setName("TnmKerabat"); // NOI18N
        TnmKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKerabatKeyPressed(evt);
            }
        });
        FormInput.add(TnmKerabat);
        TnmKerabat.setBounds(164, 1382, 560, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Hubungan :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1410, 160, 23);

        ThubKerabat.setBackground(new java.awt.Color(245, 250, 240));
        ThubKerabat.setForeground(new java.awt.Color(0, 0, 0));
        ThubKerabat.setName("ThubKerabat"); // NOI18N
        ThubKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubKerabatKeyPressed(evt);
            }
        });
        FormInput.add(ThubKerabat);
        ThubKerabat.setBounds(164, 1410, 340, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Telepon :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(510, 1410, 60, 23);

        TtelpKerabat.setBackground(new java.awt.Color(245, 250, 240));
        TtelpKerabat.setForeground(new java.awt.Color(0, 0, 0));
        TtelpKerabat.setName("TtelpKerabat"); // NOI18N
        TtelpKerabat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtelpKerabatKeyPressed(evt);
            }
        });
        FormInput.add(TtelpKerabat);
        TtelpKerabat.setBounds(574, 1410, 150, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("c. Status Spiritual :");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1438, 160, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Kegiatan Keagamaan Yang Biasa Dilakukan :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 1466, 250, 23);

        TkegiatanAgama.setBackground(new java.awt.Color(245, 250, 240));
        TkegiatanAgama.setForeground(new java.awt.Color(0, 0, 0));
        TkegiatanAgama.setName("TkegiatanAgama"); // NOI18N
        TkegiatanAgama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkegiatanAgamaKeyPressed(evt);
            }
        });
        FormInput.add(TkegiatanAgama);
        TkegiatanAgama.setBounds(254, 1466, 470, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Kegiatan Spiritual Yang Dibutuhkan Selama Perawatan :");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1494, 300, 23);

        TkegiatanSpiritual.setBackground(new java.awt.Color(245, 250, 240));
        TkegiatanSpiritual.setForeground(new java.awt.Color(0, 0, 0));
        TkegiatanSpiritual.setName("TkegiatanSpiritual"); // NOI18N
        TkegiatanSpiritual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkegiatanSpiritualKeyPressed(evt);
            }
        });
        FormInput.add(TkegiatanSpiritual);
        TkegiatanSpiritual.setBounds(304, 1494, 420, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("7. PENILAIAN NYERI NEONATUS");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 1522, 200, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Nyeri : ");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1550, 80, 23);

        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(82, 1550, 60, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Penilaian : ");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1578, 80, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("1. Crying : ");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(82, 1578, 80, 23);

        cmbCrying.setForeground(new java.awt.Color(0, 0, 0));
        cmbCrying.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada tangisan/tangisan tidak melengking", "Tangisan melengking tapi bayi mudah dihibur", "Tangisan melengking dan bayi tidak mudah dihibur" }));
        cmbCrying.setName("cmbCrying"); // NOI18N
        cmbCrying.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbCrying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCryingActionPerformed(evt);
            }
        });
        FormInput.add(cmbCrying);
        cmbCrying.setBounds(165, 1578, 280, 23);

        TskorCrying.setEditable(false);
        TskorCrying.setBackground(new java.awt.Color(245, 250, 240));
        TskorCrying.setForeground(new java.awt.Color(0, 0, 0));
        TskorCrying.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorCrying.setName("TskorCrying"); // NOI18N
        TskorCrying.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorCryingKeyPressed(evt);
            }
        });
        FormInput.add(TskorCrying);
        TskorCrying.setBounds(564, 1578, 40, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Skor : ");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(522, 1578, 40, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("2. Requires : ");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(82, 1606, 80, 23);

        cmbRequires.setForeground(new java.awt.Color(0, 0, 0));
        cmbRequires.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak perlu oksigen", "Perlu oksigen < 30", "Perlu oksigen > 30" }));
        cmbRequires.setName("cmbRequires"); // NOI18N
        cmbRequires.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRequires.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRequiresActionPerformed(evt);
            }
        });
        FormInput.add(cmbRequires);
        cmbRequires.setBounds(165, 1606, 125, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Skor : ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(522, 1606, 40, 23);

        TskorRequires.setEditable(false);
        TskorRequires.setBackground(new java.awt.Color(245, 250, 240));
        TskorRequires.setForeground(new java.awt.Color(0, 0, 0));
        TskorRequires.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorRequires.setName("TskorRequires"); // NOI18N
        TskorRequires.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorRequiresKeyPressed(evt);
            }
        });
        FormInput.add(TskorRequires);
        TskorRequires.setBounds(564, 1606, 40, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("3. Increased : ");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(82, 1634, 80, 23);

        cmbIncreased.setForeground(new java.awt.Color(0, 0, 0));
        cmbIncreased.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Detak jantung dan tekanan darah tidak berubah dari base line", "Detak jantung dan tekanan darah meningkat < 20 dari base line", "Detak jantung dan tekanan darah meningkat > 20 dari base line" }));
        cmbIncreased.setName("cmbIncreased"); // NOI18N
        cmbIncreased.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbIncreased.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIncreasedActionPerformed(evt);
            }
        });
        FormInput.add(cmbIncreased);
        cmbIncreased.setBounds(165, 1634, 340, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Skor : ");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(522, 1634, 40, 23);

        TskorIncreased.setEditable(false);
        TskorIncreased.setBackground(new java.awt.Color(245, 250, 240));
        TskorIncreased.setForeground(new java.awt.Color(0, 0, 0));
        TskorIncreased.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorIncreased.setName("TskorIncreased"); // NOI18N
        TskorIncreased.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorIncreasedKeyPressed(evt);
            }
        });
        FormInput.add(TskorIncreased);
        TskorIncreased.setBounds(564, 1634, 40, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("4. Expression : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(72, 1662, 90, 23);

        cmbExpresion.setForeground(new java.awt.Color(0, 0, 0));
        cmbExpresion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada seringal", "Ada seringal" }));
        cmbExpresion.setName("cmbExpresion"); // NOI18N
        cmbExpresion.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbExpresion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbExpresionActionPerformed(evt);
            }
        });
        FormInput.add(cmbExpresion);
        cmbExpresion.setBounds(165, 1662, 120, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Skor : ");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(522, 1662, 40, 23);

        TskorExpresion.setEditable(false);
        TskorExpresion.setBackground(new java.awt.Color(245, 250, 240));
        TskorExpresion.setForeground(new java.awt.Color(0, 0, 0));
        TskorExpresion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorExpresion.setName("TskorExpresion"); // NOI18N
        TskorExpresion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorExpresionKeyPressed(evt);
            }
        });
        FormInput.add(TskorExpresion);
        TskorExpresion.setBounds(564, 1662, 40, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("5. Sleepless : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(82, 1690, 80, 23);

        cmbSleepless.setForeground(new java.awt.Color(0, 0, 0));
        cmbSleepless.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bayi secara terus menerus tidur", "Bayi terbangun pada interval berulang", "Bayi terjaga/terbangun terus menerus" }));
        cmbSleepless.setName("cmbSleepless"); // NOI18N
        cmbSleepless.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSleepless.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSleeplessActionPerformed(evt);
            }
        });
        FormInput.add(cmbSleepless);
        cmbSleepless.setBounds(165, 1690, 220, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skor : ");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(522, 1690, 40, 23);

        TskorSleepless.setEditable(false);
        TskorSleepless.setBackground(new java.awt.Color(245, 250, 240));
        TskorSleepless.setForeground(new java.awt.Color(0, 0, 0));
        TskorSleepless.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorSleepless.setName("TskorSleepless"); // NOI18N
        TskorSleepless.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorSleeplessKeyPressed(evt);
            }
        });
        FormInput.add(TskorSleepless);
        TskorSleepless.setBounds(564, 1690, 40, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Total Nilai :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(610, 1690, 60, 23);

        TtotSkor.setEditable(false);
        TtotSkor.setBackground(new java.awt.Color(245, 250, 240));
        TtotSkor.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotSkor.setName("TtotSkor"); // NOI18N
        TtotSkor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotSkorKeyPressed(evt);
            }
        });
        FormInput.add(TtotSkor);
        TtotSkor.setBounds(674, 1690, 40, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Asesmen", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmen.setAutoCreateRowSorter(true);
        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setComponentPopupMenu(jPopupMenu1);
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-01-2025" }));
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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
//            if (Sequel.menyimpantf("penilaian_awal_keperawatan_anak_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
//                    + "?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 159, new String[]{
//                        TNoRw.getText(), kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                        cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
//                        alergiObat, TnmAlergiObat.getText(), TreaksiObat.getText(), alergiMakanan, TnmAlergiMakanan.getText(), TreaksiMakanan.getText(), alergiLain, TnmAlergiLain.getText(), 
//                        TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(), TriwPenyktSkg.getText(), campak, diare, difteri, tetanus, kejang, tbc, batukRejan,
//                        sesak, kuning, demamTifoid, urtikaria, cacing, sakitTenggorokan, lainnya, Tlainnya.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), 
//                        Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(), TbbBelum.getText(), TbbMasuk.getText().trim(), Ttb.getText().trim(), Timt.getText().trim(), Tcrt.getText(), 
//                        Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), cmbPenglihatan.getSelectedItem().toString(), cmbPendengaran.getSelectedItem().toString(), cmbMulut.getSelectedItem().toString(),
//                        cmbReflek.getSelectedItem().toString(), cmbBicara.getSelectedItem().toString(), cmbDefekasi.getSelectedItem().toString(), cmbMiksi.getSelectedItem().toString(), 
//                        cmbGastro.getSelectedItem().toString(), cmbPola.getSelectedItem().toString(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(), 
//                        cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(), cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(), 
//                        Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(), cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(), 
//                        Thepa1.getText(), Thepa2.getText(), Thepa3.getText(), Thepa4.getText(), ThepaUlang.getText(), Tpolio1.getText(), Tpolio2.getText(), Tpolio3.getText(), Tpolio4.getText(),
//                        TpolioUlang.getText(), TbcgDasar.getText(), TbcgUlang.getText(), Tdpt1.getText(), Tdpt2.getText(), Tdpt3.getText(), TdptUlang.getText(), Tmening1.getText(),
//                        Tmening2.getText(), Tmening3.getText(), TmeningUlang.getText(), TcampakDasar.getText(), TcampakUlang.getText(), TtegakanKepala.getText(), Ttengkurap.getText(),
//                        Tduduk.getText(), Tberjalan.getText(), Tgenggam.getText(), Tmencari.getText(), Tmencoret.getText(), Tmengenal24.getText(), Tberceloteh.getText(), Tmengucapkan.getText(),
//                        Tmenyebutkan.getText(), Tmenyebut36.getText(), Ttersenyum.getText(), Tbermain.getText(), Tmengenal24.getText(), Tmakan.getText(), cmbSekolah.getSelectedItem().toString(),
//                        Tkelas.getText(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
//                        cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
//                        cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
//                        cekgizi1, cekgizi2, cekgizi3, cekgizi4, cmbTindakanCegah.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri, 
//                        diet, fisio, TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan, 
//                        intoleransi, kurang, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
//                        nip, Sequel.cariIsi("select now()"), TgastroLain.getText()
//                    }) == true) {
//
//                emptTeks();
//                TCari.setText(TNoRw.getText());                
//                tampil();
//                TabRawat.setSelectedIndex(1);
//            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
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
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("ruangan", TrgRawat.getText() + ", Tanggal : " + TtglMsk.getSelectedItem().toString() + ", Pukul : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            Valid.MyReport("rptAsesmenKeperawatanAnak1.jasper", "report", "::[ Asesmen Keperawatan Anak Rawat Inap Hal. 1 ]::",
                    "SELECT now() tanggal", param);
            Valid.MyReport("rptAsesmenKeperawatanAnak2.jasper", "report", "::[ Asesmen Keperawatan Anak Rawat Inap Hal. 2 ]::",
                    "SELECT now() tanggal", param);
            
            TabRawat.setSelectedIndex(1);
            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
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
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + TNoRw.getText() + "'") == 0) {
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

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMAsesmenKeperawatanDewasaRanap");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMAsesmenKeperawatanDewasaRanap");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void cmbSumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSumberActionPerformed
        TlainSumber.setText("");
        if (cmbSumber.getSelectedIndex() == 3) {
            TlainSumber.setEnabled(true);
            TlainSumber.requestFocus();
        } else {
            TlainSumber.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSumberActionPerformed

    private void TlainSumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainSumberKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRujukan.requestFocus();
        }
    }//GEN-LAST:event_TlainSumberKeyPressed

    private void cmbRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRujukanActionPerformed
        cmbJnsRujukan.setSelectedIndex(0);
        if (cmbRujukan.getSelectedIndex() == 1) {
            cmbJnsRujukan.setEnabled(true);
            cmbJnsRujukan.requestFocus();
        } else {
            cmbJnsRujukan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRujukanActionPerformed

    private void TdiagnosaRujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaRujukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkeluhan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaRujukanKeyPressed

    private void TnmIdentitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmIdentitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPendidikan.requestFocus();
        }
    }//GEN-LAST:event_TnmIdentitasKeyPressed

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmIdentitas.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void TpekerjaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAgama.requestFocus();
        }
    }//GEN-LAST:event_TpekerjaanKeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbbl.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void TbblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbblKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpb.requestFocus();
        }
    }//GEN-LAST:event_TbblKeyPressed

    private void TpbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpbKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlk.requestFocus();
        }
    }//GEN-LAST:event_TpbKeyPressed

    private void TlkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tld.requestFocus();
        }
    }//GEN-LAST:event_TlkKeyPressed

    private void TldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlp.requestFocus();
        }
    }//GEN-LAST:event_TldKeyPressed

    private void TlpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tll.requestFocus();
        }
    }//GEN-LAST:event_TlpKeyPressed

    private void TllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tkk.requestFocus();
        }
    }//GEN-LAST:event_TllKeyPressed

    private void TkkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkkKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TkkKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tsuhu.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJernih.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TkelainanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkelainanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TanakKe.requestFocus();
        }
    }//GEN-LAST:event_TkelainanKeyPressed

    private void TanakKeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanakKeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurHamil.requestFocus();
        }
    }//GEN-LAST:event_TanakKeKeyPressed

    private void TumurHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurHamilKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRiwPenyakitIbu.requestFocus();
        }
    }//GEN-LAST:event_TumurHamilKeyPressed

    private void TlainRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainRiwayatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMasih.requestFocus();
        }
    }//GEN-LAST:event_TlainRiwayatKeyPressed

    private void cmbRiwPenyakitIbuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwPenyakitIbuActionPerformed
        chkHipertensi.setSelected(false);
        chkDM.setSelected(false);
        chkPMS.setSelected(false);
        chkTBC.setSelected(false);
        chkAsma.setSelected(false);
        chkHepB.setSelected(false);
        chkLainyaRiwayat.setSelected(false);
        TlainRiwayat.setEnabled(false);
        
        if (cmbRiwPenyakitIbu.getSelectedIndex() == 2) {
            chkHipertensi.setEnabled(true);
            chkDM.setEnabled(true);
            chkPMS.setEnabled(true);
            chkTBC.setEnabled(true);
            chkAsma.setEnabled(true);
            chkHepB.setEnabled(true);
            chkLainyaRiwayat.setEnabled(true);
            chkHipertensi.requestFocus();
        } else {
            chkHipertensi.setEnabled(false);
            chkDM.setEnabled(false);
            chkPMS.setEnabled(false);
            chkTBC.setEnabled(false);
            chkAsma.setEnabled(false);
            chkHepB.setEnabled(false);
            chkLainyaRiwayat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwPenyakitIbuActionPerformed

    private void chkLainyaRiwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaRiwayatActionPerformed
        TlainRiwayat.setText("");
        if (chkLainyaRiwayat.isSelected() == true) {
            TlainRiwayat.setEnabled(true);
            TlainRiwayat.requestFocus();
        } else {
            TlainRiwayat.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaRiwayatActionPerformed

    private void TobatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TdiagnosaIbu.requestFocus();
        }
    }//GEN-LAST:event_TobatKeyPressed

    private void cmbMasihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasihActionPerformed
        Tobat.setText("");
        if (cmbMasih.getSelectedIndex() == 2) {
            Tobat.setEnabled(true);
            Tobat.requestFocus();
        } else {
            Tobat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMasihActionPerformed

    private void TdiagnosaIbuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaIbuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglLahir.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaIbuKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TkeadaanSaatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeadaanSaatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tas.requestFocus();
        }
    }//GEN-LAST:event_TkeadaanSaatKeyPressed

    private void TasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSpontan.requestFocus();
        }
    }//GEN-LAST:event_TasKeyPressed

    private void TlainCaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainCaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkSegar.requestFocus();
        }
    }//GEN-LAST:event_TlainCaraKeyPressed

    private void chkLainyaCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaCaraActionPerformed
        TlainCara.setText("");
        if (chkLainyaCara.isSelected() == true) {
            TlainCara.setEnabled(true);
            TlainCara.requestFocus();
        } else {
            TlainCara.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaCaraActionPerformed

    private void cmbRiwAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwAlergiActionPerformed
        chkObatAlergi.setSelected(false);
        chkMakananAlergi.setSelected(false);
        chkLainAlergi.setSelected(false);
        TobatAlergi.setText("");
        TmakananAlergi.setText("");
        TlainyaAlergi.setText("");
        Treaksi.setText("");
        
        if (cmbRiwAlergi.getSelectedIndex() == 2) {
            chkObatAlergi.setEnabled(true);
            chkMakananAlergi.setEnabled(true);
            chkLainAlergi.setEnabled(true);
            TobatAlergi.setEnabled(true);
            TmakananAlergi.setEnabled(true);
            TlainyaAlergi.setEnabled(true);
            Treaksi.setEnabled(true);
            chkObatAlergi.requestFocus();
        } else {
            chkObatAlergi.setEnabled(false);
            chkMakananAlergi.setEnabled(false);
            chkLainAlergi.setEnabled(false);
            TobatAlergi.setEnabled(false);
            TmakananAlergi.setEnabled(false);
            TlainyaAlergi.setEnabled(false);
            Treaksi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRiwAlergiActionPerformed

    private void TobatAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMakananAlergi.requestFocus();
        }
    }//GEN-LAST:event_TobatAlergiKeyPressed

    private void TmakananAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmakananAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainAlergi.requestFocus();
        }
    }//GEN-LAST:event_TmakananAlergiKeyPressed

    private void TlainyaAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainyaAlergiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Treaksi.requestFocus();
        }
    }//GEN-LAST:event_TlainyaAlergiKeyPressed

    private void chkObatAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkObatAlergiActionPerformed
        TobatAlergi.setText("");
        if (chkObatAlergi.isSelected() == true) {
            TobatAlergi.setEnabled(true);
            TobatAlergi.requestFocus();
        } else {
            TobatAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkObatAlergiActionPerformed

    private void chkMakananAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMakananAlergiActionPerformed
        TmakananAlergi.setText("");
        if (chkMakananAlergi.isSelected() == true) {
            TmakananAlergi.setEnabled(true);
            TmakananAlergi.requestFocus();
        } else {
            TmakananAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkMakananAlergiActionPerformed

    private void chkLainAlergiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainAlergiActionPerformed
        TlainyaAlergi.setText("");
        if (chkLainAlergi.isSelected() == true) {
            TlainyaAlergi.setEnabled(true);
            TlainyaAlergi.requestFocus();
        } else {
            TlainyaAlergi.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainAlergiActionPerformed

    private void TreaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreaksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMasalah.requestFocus();
        }
    }//GEN-LAST:event_TreaksiKeyPressed

    private void TlainMasalahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainMasalahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMengalami.requestFocus();
        }
    }//GEN-LAST:event_TlainMasalahKeyPressed

    private void cmbAdaPerkawinanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAdaPerkawinanActionPerformed
        TlainMasalah.setText("");
        if (cmbAdaPerkawinan.getSelectedIndex() == 4) {
            TlainMasalah.setEnabled(true);
            TlainMasalah.requestFocus();
        } else {
            TlainMasalah.setEnabled(false);
        }
    }//GEN-LAST:event_cmbAdaPerkawinanActionPerformed

    private void cmbMasalahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMasalahActionPerformed
        cmbAdaPerkawinan.setSelectedIndex(0);
        TlainMasalah.setText("");

        if (cmbMasalah.getSelectedIndex() == 1) {
            cmbAdaPerkawinan.setEnabled(true);
            TlainMasalah.setEnabled(true);
            cmbAdaPerkawinan.requestFocus();
        } else {
            cmbAdaPerkawinan.setEnabled(false);
            TlainMasalah.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMasalahActionPerformed

    private void cmbMengalamiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMengalamiActionPerformed
        cmbAdaMengalami.setSelectedIndex(0);
        cmbDialami.setSelectedIndex(0);
        
        if (cmbMengalami.getSelectedIndex() == 1) {
            cmbAdaMengalami.setEnabled(true);
            cmbDialami.setEnabled(true);
            cmbAdaMengalami.requestFocus();
        } else {
            cmbAdaMengalami.setEnabled(false);
            cmbDialami.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMengalamiActionPerformed

    private void cmbTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTraumaActionPerformed
        TjelaskanTrauma.setText("");
        if (cmbTrauma.getSelectedIndex() == 1) {
            TjelaskanTrauma.setEnabled(true);
            TjelaskanTrauma.requestFocus();
        } else {
            TjelaskanTrauma.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTraumaActionPerformed

    private void TjelaskanTraumaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjelaskanTraumaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbGangguan.requestFocus();
        }
    }//GEN-LAST:event_TjelaskanTraumaKeyPressed

    private void TlainDukunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainDukunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbStatusNikah.requestFocus();
        }
    }//GEN-LAST:event_TlainDukunganKeyPressed

    private void chkLainDukunganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainDukunganActionPerformed
        TlainDukungan.setText("");
        if (chkLainDukungan.isSelected() == true) {
            TlainDukungan.setEnabled(true);
            TlainDukungan.requestFocus();
        } else {
            TlainDukungan.setEnabled(false);
        }                  
    }//GEN-LAST:event_chkLainDukunganActionPerformed

    private void TkaliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkaliKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbHubungan.requestFocus();
        }
    }//GEN-LAST:event_TkaliKeyPressed

    private void cmbStatusNikahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusNikahActionPerformed
        Tkali.setText("");
        if (cmbStatusNikah.getSelectedIndex() == 2) {
            Tkali.setEnabled(true);
            Tkali.requestFocus();
        } else {
            Tkali.setEnabled(false);
        }
    }//GEN-LAST:event_cmbStatusNikahActionPerformed

    private void cmbTinggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinggalActionPerformed
        TlainTinggal.setText("");
        if (cmbTinggal.getSelectedIndex() == 7) {
            TlainTinggal.setEnabled(true);
            TlainTinggal.requestFocus();
        } else {
            TlainTinggal.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTinggalActionPerformed

    private void TlainTinggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainTinggalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbTempat.requestFocus();
        }
    }//GEN-LAST:event_TlainTinggalKeyPressed

    private void cmbTempatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTempatActionPerformed
        TlainTempat.setText("");
        if (cmbTempat.getSelectedIndex() == 4) {
            TlainTempat.setEnabled(true);
            TlainTempat.requestFocus();
        } else {
            TlainTempat.setEnabled(false);
        }
    }//GEN-LAST:event_cmbTempatActionPerformed

    private void TlainTempatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainTempatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmKerabat.requestFocus();
        }
    }//GEN-LAST:event_TlainTempatKeyPressed

    private void TnmKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ThubKerabat.requestFocus();
        }
    }//GEN-LAST:event_TnmKerabatKeyPressed

    private void ThubKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtelpKerabat.requestFocus();
        }
    }//GEN-LAST:event_ThubKerabatKeyPressed

    private void TtelpKerabatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtelpKerabatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkegiatanAgama.requestFocus();
        }
    }//GEN-LAST:event_TtelpKerabatKeyPressed

    private void TkegiatanAgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkegiatanAgamaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TkegiatanSpiritual.requestFocus();
        }
    }//GEN-LAST:event_TkegiatanAgamaKeyPressed

    private void TkegiatanSpiritualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkegiatanSpiritualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNyeri.requestFocus();
        }
    }//GEN-LAST:event_TkegiatanSpiritualKeyPressed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        cmbCrying.setSelectedIndex(0);
        cmbRequires.setSelectedIndex(0);
        cmbIncreased.setSelectedIndex(0);
        cmbExpresion.setSelectedIndex(0);
        cmbSleepless.setSelectedIndex(0);
        TskorCrying.setText("0");
        TskorRequires.setText("0");
        TskorIncreased.setText("0");
        TskorExpresion.setText("0");
        TskorSleepless.setText("0");
        TtotSkor.setText("0");
        
        if (cmbNyeri.getSelectedIndex() == 2) {
            cmbCrying.setEnabled(true);
            cmbRequires.setEnabled(true);
            cmbIncreased.setEnabled(true);
            cmbExpresion.setEnabled(true);
            cmbSleepless.setEnabled(true);
            cmbCrying.requestFocus();
        } else {
            cmbCrying.setEnabled(false);
            cmbRequires.setEnabled(false);
            cmbIncreased.setEnabled(false);
            cmbExpresion.setEnabled(false);
            cmbSleepless.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void cmbCryingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCryingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCryingActionPerformed

    private void TskorCryingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorCryingKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorCryingKeyPressed

    private void cmbRequiresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRequiresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRequiresActionPerformed

    private void TskorRequiresKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorRequiresKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorRequiresKeyPressed

    private void cmbIncreasedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIncreasedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbIncreasedActionPerformed

    private void TskorIncreasedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorIncreasedKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorIncreasedKeyPressed

    private void cmbExpresionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbExpresionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbExpresionActionPerformed

    private void TskorExpresionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorExpresionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorExpresionKeyPressed

    private void cmbSleeplessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSleeplessActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSleeplessActionPerformed

    private void TskorSleeplessKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorSleeplessKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorSleeplessKeyPressed

    private void TtotSkorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotSkorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtotSkorKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKeperawatanPerinatologi dialog = new RMAsesmenKeperawatanPerinatologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Talamat;
    private widget.TextBox TanakKe;
    private widget.TextBox Tas;
    private widget.TextBox Tbbl;
    private widget.TextBox TdiagnosaIbu;
    private widget.TextBox TdiagnosaRujukan;
    private widget.TextBox ThubKerabat;
    private widget.TextBox TjelaskanTrauma;
    private widget.TextBox Tjenkel;
    private widget.TextBox Tkali;
    private widget.TextBox TkeadaanSaat;
    private widget.TextBox TkegiatanAgama;
    private widget.TextBox TkegiatanSpiritual;
    private widget.TextBox Tkelainan;
    private widget.TextBox Tkeluhan;
    private widget.TextBox Tkk;
    private widget.TextBox TlainCara;
    private widget.TextBox TlainDukungan;
    private widget.TextBox TlainMasalah;
    private widget.TextBox TlainRiwayat;
    private widget.TextBox TlainSumber;
    private widget.TextBox TlainTempat;
    private widget.TextBox TlainTinggal;
    private widget.TextBox TlainyaAlergi;
    private widget.TextBox Tld;
    private widget.TextBox Tlk;
    private widget.TextBox Tll;
    private widget.TextBox Tlp;
    private widget.TextBox TmakananAlergi;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmIdentitas;
    private widget.TextBox TnmKerabat;
    private widget.TextBox TnmPerawat;
    private widget.TextBox Tobat;
    private widget.TextBox TobatAlergi;
    private widget.TextBox Tpb;
    private widget.TextBox Tpekerjaan;
    private widget.TextBox Treaksi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox TskorCrying;
    private widget.TextBox TskorExpresion;
    private widget.TextBox TskorIncreased;
    private widget.TextBox TskorRequires;
    private widget.TextBox TskorSleepless;
    private widget.TextBox Tsuhu;
    private widget.TextBox TtelpKerabat;
    private widget.Tanggal TtglAses;
    private widget.Tanggal TtglLahir;
    private widget.Tanggal TtglMsk;
    private widget.TextBox TtotSkor;
    private widget.TextBox TumurHamil;
    public widget.CekBox chkAsfiksia;
    public widget.CekBox chkAsma;
    public widget.CekBox chkBblr;
    public widget.CekBox chkBerbau;
    public widget.CekBox chkChorio;
    public widget.CekBox chkDM;
    public widget.CekBox chkFetal;
    public widget.CekBox chkForcep;
    public widget.CekBox chkGemeli;
    public widget.CekBox chkHepB;
    public widget.CekBox chkHijau;
    public widget.CekBox chkHipertensi;
    public widget.CekBox chkIbuDemam;
    public widget.CekBox chkIsk;
    public widget.CekBox chkJernih;
    public widget.CekBox chkKeluarga;
    public widget.CekBox chkKeputihan;
    public widget.CekBox chkKering;
    public widget.CekBox chkKeruh;
    public widget.CekBox chkKetuban;
    public widget.CekBox chkKpd12;
    public widget.CekBox chkKpd24;
    public widget.CekBox chkLainAlergi;
    public widget.CekBox chkLainDukungan;
    public widget.CekBox chkLainyaCara;
    public widget.CekBox chkLainyaRiwayat;
    public widget.CekBox chkLayu;
    public widget.CekBox chkLumpur;
    public widget.CekBox chkMakananAlergi;
    public widget.CekBox chkObatAlergi;
    public widget.CekBox chkOrangTua;
    public widget.CekBox chkPMS;
    public widget.CekBox chkSectio;
    public widget.CekBox chkSegar;
    public widget.CekBox chkSimpul;
    public widget.CekBox chkSpontan;
    public widget.CekBox chkSuami;
    public widget.CekBox chkSuhuIbu;
    public widget.CekBox chkTBC;
    public widget.CekBox chkUK;
    public widget.CekBox chkVakum;
    private widget.ComboBox cmbAdaMengalami;
    private widget.ComboBox cmbAdaPerkawinan;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbAnus;
    private widget.ComboBox cmbBab;
    private widget.ComboBox cmbBak;
    private widget.ComboBox cmbCrying;
    private widget.ComboBox cmbDialami;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbExpresion;
    private widget.ComboBox cmbGangguan;
    private widget.ComboBox cmbHubungan;
    private widget.ComboBox cmbIncreased;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJnsRujukan;
    private widget.ComboBox cmbKonsultasi;
    private widget.ComboBox cmbMasalah;
    private widget.ComboBox cmbMasih;
    private widget.ComboBox cmbMengalami;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPendidikan;
    private widget.ComboBox cmbPenerimaan;
    private widget.ComboBox cmbRequires;
    private widget.ComboBox cmbRiwAlergi;
    private widget.ComboBox cmbRiwPenyakitIbu;
    private widget.ComboBox cmbRujukan;
    private widget.ComboBox cmbSleepless;
    private widget.ComboBox cmbStatusNikah;
    private widget.ComboBox cmbSumber;
    private widget.ComboBox cmbTempat;
    private widget.ComboBox cmbTinggal;
    private widget.ComboBox cmbTrauma;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.Table tbAsesmen;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pa.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "date_format(pa.tgl_msk_ruangan,'%d-%m-%Y') tglmsk, time_format(pa.jam_msk_ruangan,'%H:%i') jammsk, concat(pa.tiba_diruang,' ',pa.tiba_diruang_lainnya) tiba, "
                    + "pa.msk_melalui, pa.keluhan_utama, date_format(pa.tgl_asesmen,'%d-%m-%Y') tglases, time_format(pa.jam_asesmen,'%H:%i') jamases, pg.nama perawat "
                    + "FROM penilaian_awal_keperawatan_anak_ranap pa INNER JOIN reg_periksa rp ON rp.no_rawat = pa.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = pa.nip_perawat where "
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
                        rs.getString("nm_alergi_obat"),
                        rs.getString("reaksi_alergi_obat"),
                        rs.getString("alergi_makanan"),
                        rs.getString("nm_alergi_makanan"),
                        rs.getString("reaksi_alergi_makanan"),
                        rs.getString("alergi_lainnya"),
                        rs.getString("nm_alergi_lainnya"),
                        rs.getString("reaksi_alergi_lainnya"),
                        rs.getString("pasang_gelang_tanda"),
                        rs.getString("alergi_diberitahukan"),
                        rs.getString("riwayat_penyakit_sekarang"),
                        rs.getString("campak"),
                        rs.getString("diare"),
                        rs.getString("difteri"),
                        rs.getString("tetanus"),
                        rs.getString("kejang"),
                        rs.getString("tbc"),
                        rs.getString("batuk_rejan"),
                        rs.getString("sesak"),
                        rs.getString("kuning"),
                        rs.getString("demam_tifoid"),
                        rs.getString("urtikaria"),
                        rs.getString("cacing"),
                        rs.getString("sakit_tenggorokan"),
                        rs.getString("lainya"),
                        rs.getString("kalimat_lainya"),
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
                        rs.getString("penglihatan"),
                        rs.getString("pendengaran"),
                        rs.getString("mulut"),
                        rs.getString("reflek_menelan"),
                        rs.getString("bicara"),
                        rs.getString("defekasi"),
                        rs.getString("miksi"),
                        rs.getString("gastrointestinal"),
                        rs.getString("pola_tidur"),
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
                        rs.getString("hepatitis_b_umur1"),
                        rs.getString("hepatitis_b_umur2"),
                        rs.getString("hepatitis_b_umur3"),
                        rs.getString("hepatitis_b_umur4"),
                        rs.getString("hepatitis_b_umur_ulang"),
                        rs.getString("polio_umur1"),
                        rs.getString("polio_umur2"),
                        rs.getString("polio_umur3"),
                        rs.getString("polio_umur4"),
                        rs.getString("polio_umur_ulang"),
                        rs.getString("bcg_umur_dasar"),
                        rs.getString("bcg_umur_ulang"),
                        rs.getString("dpt_umur1"),
                        rs.getString("dpt_umur2"),
                        rs.getString("dpt_umur3"),
                        rs.getString("dpt_umur_ulang"),
                        rs.getString("meningitis_umur1"),
                        rs.getString("meningitis_umur2"),
                        rs.getString("meningitis_umur3"),
                        rs.getString("meningitis_umur_ulang"),
                        rs.getString("campak_umur_dasar"),
                        rs.getString("campak_umur_ulang"),
                        rs.getString("mk_menegakkan_kepala"),
                        rs.getString("mk_tengkurap"),
                        rs.getString("mk_duduk"),
                        rs.getString("mk_berjalan"),
                        rs.getString("mh_menggenggam_mainan"),
                        rs.getString("mh_mencari_benda"),
                        rs.getString("mh_mencoret"),
                        rs.getString("mh_mengenal_warna"),
                        rs.getString("bicara_berceloteh"),
                        rs.getString("bicara_mengucapkan"),
                        rs.getString("bicara_menyebutkan"),
                        rs.getString("bicara_menyebut_kata"),
                        rs.getString("se_tersenyum"),
                        rs.getString("se_bermain"),
                        rs.getString("se_mengenal"),
                        rs.getString("se_makan_minum"),
                        rs.getString("saat_ini_sekolah"),
                        rs.getString("kelas"),
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
                        rs.getString("skrining_gizi_1"),
                        rs.getString("skrining_gizi_2"),
                        rs.getString("skrining_gizi_3"),
                        rs.getString("skrining_gizi_4"),
                        rs.getString("tindakan_pencegahan"),
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
                        rs.getString("daftar_masalah_lain"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("nip_perawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("gastrointestinal_lainnya")
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
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        Valid.SetTgl(TtglMsk, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        
        cmbJam.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norwt + "'").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norwt + "'").substring(3, 5));
        cmbDtk.setSelectedItem(Sequel.cariIsi("select jam_reg from reg_periksa where no_rawat='" + norwt + "'").substring(6, 8));
        
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {            
//            BtnPerawat.setEnabled(false);
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
            if (TnmPerawat.getText().equals("")) {
                nip = "";
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }  
    }
    
    private void getData() {
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 4).toString());
            Valid.SetTgl(TtglMsk, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString().substring(6, 8));
            
            Valid.SetTgl(TtglAses, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 166).toString());            
            cmbJam1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 167).toString().substring(6, 8));
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 168).toString();
            TnmPerawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_anak_ranap where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("penilaian_awal_keperawatan_anak_ranap_resiko", "no_rawat",
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());                
                
                tampil();
                emptTeks();
                TabRawat.setSelectedIndex(1);              
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        } else {
            tampil();
            emptTeks();
        }
    }
    
    private void ganti() {
        cekData();
//        if (Sequel.mengedittf("penilaian_awal_keperawatan_anak_ranap", "no_rawat=?", "kd_kamar_msk=?, tgl_msk_ruangan=?, jam_msk_ruangan=?, tiba_diruang=?, tiba_diruang_lainnya=?, msk_melalui=?, keluhan_utama=?, riwayat_alergi=?, alergi_obat=?, "
//                + "nm_alergi_obat=?, reaksi_alergi_obat=?, alergi_makanan=?, nm_alergi_makanan=?, reaksi_alergi_makanan=?, alergi_lainnya=?, nm_alergi_lainnya=?, reaksi_alergi_lainnya=?, "
//                + "pasang_gelang_tanda=?, alergi_diberitahukan=?, riwayat_penyakit_sekarang=?, campak=?, diare=?, difteri=?, tetanus=?, kejang=?, tbc=?, batuk_rejan=?, sesak=?, "
//                + "kuning=?, demam_tifoid=?, urtikaria=?, cacing=?, sakit_tenggorokan=?, lainya=?, kalimat_lainya=?, kesadaran=?, gcs_e=?, gcs_m=?, gcs_v=?, tensi=?, temp=?, "
//                + "hr=?, rr=?, bb_sebelum_skt=?, bb_msk_rs=?, tb=?, imt=?, crt=?, spo2=?, pernafasan=?, penglihatan=?, pendengaran=?, mulut=?, reflek_menelan=?, bicara=?, "
//                + "defekasi=?, miksi=?, gastrointestinal=?, pola_tidur=?, makan=?, berpakaian=?, buang_air=?, mandi=?, berpindah=?, kesimpulan=?, keluarga_terdekat=?, hubungan=?, "
//                + "tinggal_dengan=?, tinggal_dengan_lain=?, curiga_penganiayaan=?, butuh_bantuan_ibadah=?, status_emosional=?, hepatitis_b_umur1=?, hepatitis_b_umur2=?, "
//                + "hepatitis_b_umur3=?, hepatitis_b_umur4=?, hepatitis_b_umur_ulang=?, polio_umur1=?, polio_umur2=?, polio_umur3=?, polio_umur4=?, polio_umur_ulang=?, "
//                + "bcg_umur_dasar=?, bcg_umur_ulang=?, dpt_umur1=?, dpt_umur2=?, dpt_umur3=?, dpt_umur_ulang=?, meningitis_umur1=?, meningitis_umur2=?, meningitis_umur3=?, "
//                + "meningitis_umur_ulang=?, campak_umur_dasar=?, campak_umur_ulang=?, mk_menegakkan_kepala=?, mk_tengkurap=?, mk_duduk=?, mk_berjalan=?, mh_menggenggam_mainan=?, "
//                + "mh_mencari_benda=?, mh_mencoret=?, mh_mengenal_warna=?, bicara_berceloteh=?, bicara_mengucapkan=?, bicara_menyebutkan=?, bicara_menyebut_kata=?, se_tersenyum=?, "
//                + "se_bermain=?, se_mengenal=?, se_makan_minum=?, saat_ini_sekolah=?, kelas=?, onset=?, provocation=?, provocation_lain=?, quality=?, quality_lain=?, radiation=?, "
//                + "severity=?, time=?, time_lama=?, relief=?, relief_lain=?, asociated_sign=?, asociated_sign_lain=?, skala_nyeri=?, skrining_gizi_1=?, skrining_gizi_2=?, "
//                + "skrining_gizi_3=?, skrining_gizi_4=?, tindakan_pencegahan=?, obat_obatan=?, perawatan_luka=?, manajemen_lain=?, menejemen_nyeri=?, diet_nutrisi=?, "
//                + "fisioterapi=?, rehabilitasi_lain=?, hipertermi=?, nyeri=?, kurang_volume_cairan=?, lebih_volume_cairan=?, bersihkan_jln_nfs_tdk_efektif=?, pola_nfs_tdk_efektif=?, "
//                + "gangguan_pertukaran_gas=?, cemas=?, ketidakseimbangan_nutrisi=?, perubahan_perfusi=?, penurunan_curah_jantung=?, kerusakan_integritas=?, intoleransi_aktifitas=?, "
//                + "kurang_perawatan_diri=?, daftar_masalah_lain=?, tgl_asesmen=?, jam_asesmen=?, nip_perawat=?, gastrointestinal_lainnya=?", 158, new String[]{
//                    kdkamar, Valid.SetTgl(TtglMsk.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
//                    cmbTiba.getSelectedItem().toString(), TtibaLain.getText(), cmbMasuk.getSelectedItem().toString(), Tkeluhan.getText(), cmbRiwAlergi.getSelectedItem().toString(),
//                    alergiObat, TnmAlergiObat.getText(), TreaksiObat.getText(), alergiMakanan, TnmAlergiMakanan.getText(), TreaksiMakanan.getText(), alergiLain, TnmAlergiLain.getText(),
//                    TreaksiLain.getText(), gelang, cmbAlergiDiberitahu.getSelectedItem().toString(), TriwPenyktSkg.getText(), campak, diare, difteri, tetanus, kejang, tbc, batukRejan,
//                    sesak, kuning, demamTifoid, urtikaria, cacing, sakitTenggorokan, lainnya, Tlainnya.getText(), Tkesadaran.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(),
//                    Ttensi.getText(), Ttemp.getText(), Thr.getText(), Trr.getText(), TbbBelum.getText(), TbbMasuk.getText().trim(), Ttb.getText().trim(), Timt.getText().trim(), Tcrt.getText(),
//                    Tspo.getText(), cmbPernafasan.getSelectedItem().toString(), cmbPenglihatan.getSelectedItem().toString(), cmbPendengaran.getSelectedItem().toString(), cmbMulut.getSelectedItem().toString(),
//                    cmbReflek.getSelectedItem().toString(), cmbBicara.getSelectedItem().toString(), cmbDefekasi.getSelectedItem().toString(), cmbMiksi.getSelectedItem().toString(),
//                    cmbGastro.getSelectedItem().toString(), cmbPola.getSelectedItem().toString(), cmbMakan.getSelectedItem().toString(), cmbBerpakaian.getSelectedItem().toString(),
//                    cmbBuang.getSelectedItem().toString(), cmbMandi.getSelectedItem().toString(), cmbBerpindah.getSelectedItem().toString(), Tkesimpulan.getText(), TKlgDekat.getText(),
//                    Thubungan.getText(), cmbTinggal.getSelectedItem().toString(), TtglDenganLain.getText(), cmbCuriga.getSelectedItem().toString(), ibadah, cmbStatus.getSelectedItem().toString(),
//                    Thepa1.getText(), Thepa2.getText(), Thepa3.getText(), Thepa4.getText(), ThepaUlang.getText(), Tpolio1.getText(), Tpolio2.getText(), Tpolio3.getText(), Tpolio4.getText(),
//                    TpolioUlang.getText(), TbcgDasar.getText(), TbcgUlang.getText(), Tdpt1.getText(), Tdpt2.getText(), Tdpt3.getText(), TdptUlang.getText(), Tmening1.getText(),
//                    Tmening2.getText(), Tmening3.getText(), TmeningUlang.getText(), TcampakDasar.getText(), TcampakUlang.getText(), TtegakanKepala.getText(), Ttengkurap.getText(),
//                    Tduduk.getText(), Tberjalan.getText(), Tgenggam.getText(), Tmencari.getText(), Tmencoret.getText(), Tmengenal24.getText(), Tberceloteh.getText(), Tmengucapkan.getText(),
//                    Tmenyebutkan.getText(), Tmenyebut36.getText(), Ttersenyum.getText(), Tbermain.getText(), Tmengenal24.getText(), Tmakan.getText(), cmbSekolah.getSelectedItem().toString(),
//                    Tkelas.getText(), Tonset.getText(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
//                    cmbRadia.getSelectedItem().toString(), cmbSever.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(),
//                    cmbRelief.getSelectedItem().toString(), Trelief.getText(), cmbAsso.getSelectedItem().toString(), Tasso.getText(), cmbSkala.getSelectedItem().toString(),
//                    cekgizi1, cekgizi2, cekgizi3, cekgizi4, cmbTindakanCegah.getSelectedItem().toString(), obatObatan, perawatanLuka, TmanajemenLain.getText(), manajemenNyeri,
//                    diet, fisio, TrehabLain.getText(), hipertermi, nyeri, resiko, kelebihan, bersihkan, pola, gangguan, cemas, ketidakseimbangan, perubahan, penurunan, kerusakan,
//                    intoleransi, kurang, TmasalahLain.getText(), Valid.SetTgl(TtglAses.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
//                    nip, TgastroLain.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
//                }) == true) {
//
//            TabRawat.setSelectedIndex(1);
//            tampil();
//            emptTeks();
//        }
    }
    
    private void cekData() {
//        if (ChkAlergiObat.isSelected() == true) {
//            alergiObat = "ya";
//        } else {
//            alergiObat = "tidak";
//        }
    }
    
    private void dataCek() {        
//        if (alergiObat.equals("ya")) {
//            ChkAlergiObat.setSelected(true);
//        } else {
//            ChkAlergiObat.setSelected(false);
//        }
    }
}
