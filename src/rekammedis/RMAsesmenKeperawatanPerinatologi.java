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
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 135, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(136, 38, 420, 23);

        TtglMsk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2025" }));
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

        TtglAses.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2025" }));
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

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2025" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-01-2025" }));
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
    private widget.TextBox TnmPerawat;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglAses;
    private widget.Tanggal TtglMsk;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
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
