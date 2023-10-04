package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenKebidananRalan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeRiwayat, tabModeResiko;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4;
    private ResultSet rs, rs1, rs2, rs3, rs4;
    private int i = 0, x = 0, skor = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String wktSimpan = "", kdpoli = "", nipPerawat = "", nipDokter = "", nipBidan = "", alergiObat = "", alergiMakan = "",
            alergiLain = "", gelang = "", diagnosa = "", tindakan = "", obat = "", rehab = "", diet = "", menejemen = "", lainSebutkan = "",
            pasien = "", klgPasien = "", tidakBeri = "", jam = "", iden1 = "", iden2 = "", iden3 = "", iden4 = "", iden5 = "", iden6 = "",
            iden7 = "", iden8 = "", iden9 = "", iden10 = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenKebidananRalan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang/Poli/Inst.", "Tgl. Asesmen", "Umur Pasien",
            "Pekerjaan Pasien", "Agama", "Alamat Pasien", "Status Nikah", "Nama Perawat", "Nama Bidan", "Nama Dokter",
            "kd_poli", "tgl_asesmen", "nm_suami", "umur_suami", "pekerjaan_suami", "alamat_suami", "agama_suami", "alasan_masuk_rs", "tensi", "nadi",
            "respirasi", "suhu", "kesadaran", "spo2", "g", "p", "a", "jml_kawin_istri", "jml_kawin_suami", "usia_perkawinan", "id_usia_kawin", "keluarga_terdekat",
            "hub_keluarga_terdekat", "tinggal_dengan", "tinggal_dengan_lain", "curiga_penganiayaan", "kegiatan_ibadah", "status_emosional", "status_ekonomi",
            "status_ekonomi_lain", "nyeri", "nyeri_ya_lokasi", "jenis", "provocation", "provocation_lain", "quality", "quality_lain", "radiation", "severity",
            "time", "time_lama", "skala_nyeri", "skrining_gizi1", "skrining_gizi1_ya", "skrining_gizi2", "riwayat_alergi", "alergi_obat", "reaksi_alergi_obat",
            "alergi_makanan", "reaksi_alergi_makanan", "alergi_lainnya", "reaksi_alergi_lainnya", "gelang_tanda", "alergi_diberitahukan", "alat_bantu", "prothesis",
            "cacat_tubuh", "adl", "riw_jatuh_3bulan", "nip_perawat", "hambatan_pembelajaran", "hambatan_ya", "hambatan_ya_lainya", "penerjemah", "penerjemah_ya",
            "bahasa_isyarat", "diagnosa_menejemen_penyakit", "tindakan_keperawatan", "ket_tindakan_keperawatan", "obat_obatan", "rehabilitasi", "diet_nutrisi",
            "manajemen_nyeri", "lain_lain", "ket_lain_lain", "edukasi_pasien", "edukasi_keluarga_pasien", "edukasi_nm_keluarga_pasien", "tidak_memberi_edukasi",
            "ket_tidak_memberi_edukasi", "tanggal", "jam", "cek_jam", "nip_dokter", "identifikasi1", "identifikasi2", "identifikasi3", "identifikasi4", "identifikasi5",
            "identifikasi6", "identifikasi7", "identifikasi8", "identifikasi9", "identifikasi10", "manajer_pelayanan", "discharge_planing", "nip_bidan",
            "cara_datang", "ket_rujukan", "ket_pkm", "ket_rs_lain", "hamil", "dengan", "perut_mules", "tgl_perut", "keluar_lendir", "tgl_lendir",
            "darah_encer", "tgl_darah", "keluar_air", "tgl_air", "jenis_keluar_air", "pusing", "tgl_pusing"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 128; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(110);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(90);
            } else if (i == 7) {
                column.setPreferredWidth(200);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(250);
            } else if (i == 10) {
                column.setPreferredWidth(90);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeRiwayat = new DefaultTableModel(null, new Object[]{
            "no_rawat", "Thn. Partus", "Tempat Partus", "Umur Hamil", "Jns. Persalinan", "Penolong Persalinan",
            "Penyulit", "Jns. Kelamin", "Brt. Lahir", "Keadaan Anak Skrng.", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {return false;}
        };
        
        tbRiwayat.setModel(tabModeRiwayat);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 11; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(140);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            } else if (i == 6) {
                column.setPreferredWidth(140);
            } else if (i == 7) {
                column.setPreferredWidth(90);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(140);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeResiko=new DefaultTableModel(null,new Object[]{
                "#", "KODE", "ASESMEN", "FAKTOR RESIKO", "SKALA", "SKOR"
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
        
        TnmSuami.setDocument(new batasInput((int) 150).getKata(TnmSuami));
        TumurSuami.setDocument(new batasInput((byte) 3).getOnlyAngka(TumurSuami));
        TpekerjaanSuami.setDocument(new batasInput((int) 150).getKata(TpekerjaanSuami));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Trespi.setDocument(new batasInput((int) 7).getKata(Trespi));
        Tsuhu.setDocument(new batasInput((int) 7).getKata(Tsuhu));
        Tkesadaran.setDocument(new batasInput((int) 100).getKata(Tkesadaran));
        Tspo2.setDocument(new batasInput((int) 7).getKata(Tspo2));
        Tg.setDocument(new batasInput((int) 5).getKata(Tg));
        Tp.setDocument(new batasInput((int) 5).getKata(Tp));
        Ta.setDocument(new batasInput((int) 5).getKata(Ta));
        TthnPartus.setDocument(new batasInput((byte) 4).getOnlyAngka(TthnPartus));
        TtmptPartus.setDocument(new batasInput((int) 150).getKata(TtmptPartus));
        TumurHamil.setDocument(new batasInput((int) 8).getKata(TumurHamil));
        TjnsPersalinan.setDocument(new batasInput((int) 150).getKata(TjnsPersalinan));
        TpenPersalinan.setDocument(new batasInput((int) 150).getKata(TpenPersalinan));
        Tpenyulit.setDocument(new batasInput((int) 150).getKata(Tpenyulit));
        TbrtLahir.setDocument(new batasInput((int) 7).getKata(TbrtLahir));
        TkeadaanAnk.setDocument(new batasInput((int) 200).getKata(TkeadaanAnk));
        TusiaKawin.setDocument(new batasInput((int) 5).getKata(TusiaKawin));
        TklgTerdekat.setDocument(new batasInput((int) 170).getKata(TklgTerdekat));
        Thubungan.setDocument(new batasInput((int) 150).getKata(Thubungan));
        TtglDenganLain.setDocument(new batasInput((int) 150).getKata(TtglDenganLain));
        TsttsEkonomi.setDocument(new batasInput((int) 130).getKata(TsttsEkonomi));
        Tlokasi.setDocument(new batasInput((int) 150).getKata(Tlokasi));
        TalatBantu.setDocument(new batasInput((int) 150).getKata(TalatBantu));
        Tprothesis.setDocument(new batasInput((int) 150).getKata(Tprothesis));
        TcacatTubuh.setDocument(new batasInput((int) 150).getKata(TcacatTubuh));
        TnmKlgPasien.setDocument(new batasInput((int) 150).getKata(TnmKlgPasien));
        Thamil.setDocument(new batasInput((byte) 3).getOnlyAngka(Thamil));
        
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
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {                    
                        nipPerawat = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPerawat.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipBidan = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmBidan.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnBidan.requestFocus();
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMAsesmenKebidananRalan")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDokter = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDokter.requestFocus();
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


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        TglAsesmen = new widget.Tanggal();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel11 = new widget.Label();
        BtnAlamat = new widget.Button();
        jLabel12 = new widget.Label();
        TumurPx = new widget.TextBox();
        jLabel13 = new widget.Label();
        TpekerjaanPx = new widget.TextBox();
        jLabel14 = new widget.Label();
        TagamaPx = new widget.TextBox();
        jLabel15 = new widget.Label();
        TalamatPx = new widget.TextBox();
        jLabel16 = new widget.Label();
        TnmSuami = new widget.TextBox();
        jLabel17 = new widget.Label();
        TumurSuami = new widget.TextBox();
        jLabel18 = new widget.Label();
        TpekerjaanSuami = new widget.TextBox();
        jLabel20 = new widget.Label();
        TalamatSuami = new widget.TextBox();
        jLabel22 = new widget.Label();
        cmbAgama = new widget.ComboBox();
        jLabel23 = new widget.Label();
        Talasan = new widget.TextBox();
        jLabel24 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        Trespi = new widget.TextBox();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel33 = new widget.Label();
        Tspo2 = new widget.TextBox();
        jLabel34 = new widget.Label();
        jLabel36 = new widget.Label();
        Tg = new widget.TextBox();
        jLabel37 = new widget.Label();
        Tp = new widget.TextBox();
        jLabel38 = new widget.Label();
        Ta = new widget.TextBox();
        jLabel39 = new widget.Label();
        TthnPartus = new widget.TextBox();
        jLabel40 = new widget.Label();
        TtmptPartus = new widget.TextBox();
        TumurHamil = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        TjnsPersalinan = new widget.TextBox();
        jLabel44 = new widget.Label();
        TpenPersalinan = new widget.TextBox();
        jLabel45 = new widget.Label();
        Tpenyulit = new widget.TextBox();
        jLabel46 = new widget.Label();
        cmbJK = new widget.ComboBox();
        jLabel47 = new widget.Label();
        TbrtLahir = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        TkeadaanAnk = new widget.TextBox();
        BtnBatalRiw = new widget.Button();
        BtnSimpanRiw = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
        BtnHapusRiw = new widget.Button();
        BtnGantiRiw = new widget.Button();
        cmbPoli = new widget.ComboBox();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        TsttsKawin = new widget.TextBox();
        jLabel52 = new widget.Label();
        jLabel53 = new widget.Label();
        cmbIstri = new widget.ComboBox();
        jLabel54 = new widget.Label();
        cmbSuami = new widget.ComboBox();
        jLabel55 = new widget.Label();
        TusiaKawin = new widget.TextBox();
        cmbUsiaKawin = new widget.ComboBox();
        jLabel56 = new widget.Label();
        TklgTerdekat = new widget.TextBox();
        jLabel57 = new widget.Label();
        Thubungan = new widget.TextBox();
        jLabel58 = new widget.Label();
        cmbTinggal = new widget.ComboBox();
        TtglDenganLain = new widget.TextBox();
        jLabel59 = new widget.Label();
        cmbCuriga = new widget.ComboBox();
        jLabel60 = new widget.Label();
        cmbEmosi = new widget.ComboBox();
        jLabel61 = new widget.Label();
        TkegiatanIbadah = new widget.TextBox();
        jLabel62 = new widget.Label();
        cmbEkonomi = new widget.ComboBox();
        TsttsEkonomi = new widget.TextBox();
        jLabel63 = new widget.Label();
        jLabel64 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel65 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel66 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        jLabel80 = new widget.Label();
        cmbProvo = new widget.ComboBox();
        Tprovo = new widget.TextBox();
        jLabel81 = new widget.Label();
        cmbQuality = new widget.ComboBox();
        Tquality = new widget.TextBox();
        jLabel82 = new widget.Label();
        cmbRadia = new widget.ComboBox();
        jLabel84 = new widget.Label();
        cmbSevere = new widget.ComboBox();
        jLabel86 = new widget.Label();
        cmbTime = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbLama = new widget.ComboBox();
        jLabel100 = new widget.Label();
        cmbSkala = new widget.ComboBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel69 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbGizi1 = new widget.ComboBox();
        skorGZ1 = new widget.TextBox();
        cmbGizi1Ya = new widget.ComboBox();
        skorYaGZ1 = new widget.TextBox();
        jLabel70 = new widget.Label();
        jLabel73 = new widget.Label();
        jLabel76 = new widget.Label();
        cmbGizi2 = new widget.ComboBox();
        skorGZ2 = new widget.TextBox();
        jLabel74 = new widget.Label();
        TotSkorGZ = new widget.TextBox();
        kesimpulanGizi = new widget.TextArea();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        cmbRiwayat = new widget.ComboBox();
        chkAlergiObat = new widget.CekBox();
        jLabel77 = new widget.Label();
        reaksiAlergiObat = new widget.TextBox();
        chkAlergiMakanan = new widget.CekBox();
        jLabel78 = new widget.Label();
        reaksiAlergiMakanan = new widget.TextBox();
        chkAlergiLain = new widget.CekBox();
        jLabel79 = new widget.Label();
        reaksiAlergiLain = new widget.TextBox();
        chkGelang = new widget.CekBox();
        jLabel83 = new widget.Label();
        cmbdiberitahukan = new widget.ComboBox();
        jLabel85 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        jLabel97 = new widget.Label();
        TotSkorRJ = new widget.TextBox();
        kesimpulanResikoJatuh = new widget.TextArea();
        label12 = new widget.Label();
        TCariResiko = new widget.TextBox();
        BtnCariResiko = new widget.Button();
        BtnAllResiko = new widget.Button();
        label13 = new widget.Label();
        jLabel88 = new widget.Label();
        TalatBantu = new widget.TextBox();
        jLabel89 = new widget.Label();
        Tprothesis = new widget.TextBox();
        TcacatTubuh = new widget.TextBox();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        cmbAdl = new widget.ComboBox();
        jLabel92 = new widget.Label();
        cmbRiwayat3bln = new widget.ComboBox();
        label14 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel93 = new widget.Label();
        jLabel94 = new widget.Label();
        cmbHambatan = new widget.ComboBox();
        jLabel95 = new widget.Label();
        cmbHambatanYa = new widget.ComboBox();
        ThambatanLain = new widget.TextBox();
        jLabel96 = new widget.Label();
        cmbPenerjemah = new widget.ComboBox();
        jLabel98 = new widget.Label();
        Tsebutkan = new widget.TextBox();
        jLabel99 = new widget.Label();
        cmbBahasa = new widget.ComboBox();
        jLabel101 = new widget.Label();
        chkDiagnosa = new widget.CekBox();
        chkTindakan = new widget.CekBox();
        Ttindakan = new widget.TextBox();
        chkObat = new widget.CekBox();
        chkRehab = new widget.CekBox();
        chkDiet = new widget.CekBox();
        chkManajemen = new widget.CekBox();
        chkLain = new widget.CekBox();
        TLainSebutkan = new widget.TextBox();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        chkPasien = new widget.CekBox();
        chkKlgPasien = new widget.CekBox();
        TnmKlgPasien = new widget.TextBox();
        chkTdkBeriEdukasi = new widget.CekBox();
        TtdkBeriEdukasi = new widget.TextBox();
        jLabel104 = new widget.Label();
        Ttgl = new widget.Tanggal();
        ChkJam = new widget.CekBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel105 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        chkIden1 = new widget.CekBox();
        chkIden2 = new widget.CekBox();
        chkIden3 = new widget.CekBox();
        chkIden4 = new widget.CekBox();
        chkIden5 = new widget.CekBox();
        chkIden6 = new widget.CekBox();
        chkIden7 = new widget.CekBox();
        chkIden8 = new widget.CekBox();
        chkIden9 = new widget.CekBox();
        chkIden10 = new widget.CekBox();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        cmbMPP = new widget.ComboBox();
        jLabel110 = new widget.Label();
        cmbDP = new widget.ComboBox();
        jLabel111 = new widget.Label();
        TnmBidan = new widget.TextBox();
        BtnBidan = new widget.Button();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        cmbCaraDatang = new widget.ComboBox();
        jLabel114 = new widget.Label();
        Trujukan = new widget.TextBox();
        jLabel115 = new widget.Label();
        Tpkm = new widget.TextBox();
        jLabel116 = new widget.Label();
        TrsLain = new widget.TextBox();
        jLabel117 = new widget.Label();
        Thamil = new widget.TextBox();
        jLabel35 = new widget.Label();
        jLabel118 = new widget.Label();
        Tdengan = new widget.TextBox();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        cmbPerut = new widget.ComboBox();
        jLabel121 = new widget.Label();
        Ttgl_perut = new widget.Tanggal();
        jLabel122 = new widget.Label();
        jLabel123 = new widget.Label();
        cmbLendir = new widget.ComboBox();
        jLabel124 = new widget.Label();
        Ttgl_lendir = new widget.Tanggal();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        cmbDarah = new widget.ComboBox();
        jLabel127 = new widget.Label();
        Ttgl_darah = new widget.Tanggal();
        jLabel128 = new widget.Label();
        jLabel129 = new widget.Label();
        cmbAir = new widget.ComboBox();
        jLabel130 = new widget.Label();
        Ttgl_air = new widget.Tanggal();
        jLabel131 = new widget.Label();
        cmbJenisAir = new widget.ComboBox();
        jLabel132 = new widget.Label();
        cmbPusing = new widget.ComboBox();
        jLabel133 = new widget.Label();
        Ttgl_pusing = new widget.Tanggal();
        jLabel134 = new widget.Label();
        internalFrame3 = new widget.InternalFrame();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asesmen Awal Kebidanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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

        TabRawat.setBackground(new java.awt.Color(254, 255, 254));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2041));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(125, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(364, 10, 370, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(259, 10, 100, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(740, 10, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(805, 10, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 120, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Asesmen :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(670, 150, 84, 23);

        TglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023 10:00:43" }));
        TglAsesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsesmen.setName("TglAsesmen"); // NOI18N
        TglAsesmen.setOpaque(false);
        TglAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TglAsesmenKeyPressed(evt);
            }
        });
        FormInput.add(TglAsesmen);
        TglAsesmen.setBounds(760, 150, 130, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(25, 1133, 530, 230);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ruang/Poli/Inst. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(260, 150, 110, 23);

        BtnAlamat.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlamat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlamat.setMnemonic('2');
        BtnAlamat.setText("Sama Dengan Pasien");
        BtnAlamat.setToolTipText("Alt+2");
        BtnAlamat.setName("BtnAlamat"); // NOI18N
        BtnAlamat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlamatActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlamat);
        BtnAlamat.setBounds(840, 122, 160, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Umur Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 38, 120, 23);

        TumurPx.setEditable(false);
        TumurPx.setForeground(new java.awt.Color(0, 0, 0));
        TumurPx.setName("TumurPx"); // NOI18N
        FormInput.add(TumurPx);
        TumurPx.setBounds(125, 38, 70, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pekerjaan Pasien :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(200, 38, 100, 23);

        TpekerjaanPx.setEditable(false);
        TpekerjaanPx.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanPx.setName("TpekerjaanPx"); // NOI18N
        FormInput.add(TpekerjaanPx);
        TpekerjaanPx.setBounds(306, 38, 230, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Agama Pasien :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(540, 38, 90, 23);

        TagamaPx.setEditable(false);
        TagamaPx.setForeground(new java.awt.Color(0, 0, 0));
        TagamaPx.setName("TagamaPx"); // NOI18N
        FormInput.add(TagamaPx);
        TagamaPx.setBounds(634, 38, 250, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Alamat Pasien :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 66, 120, 23);

        TalamatPx.setEditable(false);
        TalamatPx.setForeground(new java.awt.Color(0, 0, 0));
        TalamatPx.setName("TalamatPx"); // NOI18N
        FormInput.add(TalamatPx);
        TalamatPx.setBounds(125, 66, 875, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Nama Suami :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 94, 120, 23);

        TnmSuami.setForeground(new java.awt.Color(0, 0, 0));
        TnmSuami.setName("TnmSuami"); // NOI18N
        TnmSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TnmSuami);
        TnmSuami.setBounds(125, 94, 240, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Umur Suami :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(370, 94, 70, 23);

        TumurSuami.setForeground(new java.awt.Color(0, 0, 0));
        TumurSuami.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TumurSuami.setName("TumurSuami"); // NOI18N
        TumurSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TumurSuami);
        TumurSuami.setBounds(445, 94, 50, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Thn.     Pekerjaan Suami :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(500, 94, 130, 23);

        TpekerjaanSuami.setForeground(new java.awt.Color(0, 0, 0));
        TpekerjaanSuami.setName("TpekerjaanSuami"); // NOI18N
        TpekerjaanSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpekerjaanSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TpekerjaanSuami);
        TpekerjaanSuami.setBounds(630, 94, 370, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Alamat Suami :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 122, 120, 23);

        TalamatSuami.setForeground(new java.awt.Color(0, 0, 0));
        TalamatSuami.setName("TalamatSuami"); // NOI18N
        TalamatSuami.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatSuamiKeyPressed(evt);
            }
        });
        FormInput.add(TalamatSuami);
        TalamatSuami.setBounds(125, 122, 710, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Agama Suami :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 150, 120, 23);

        cmbAgama.setBackground(new java.awt.Color(245, 253, 240));
        cmbAgama.setForeground(new java.awt.Color(0, 0, 0));
        cmbAgama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Islam", "Kristen Protestan", "Kristen Katolik", "Hindu", "Budha", "Konghuchu", "Kepercayaan Lain" }));
        cmbAgama.setLightWeightPopupEnabled(false);
        cmbAgama.setName("cmbAgama"); // NOI18N
        FormInput.add(cmbAgama);
        cmbAgama.setBounds(125, 150, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Alasan Masuk RS :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 178, 120, 23);

        Talasan.setForeground(new java.awt.Color(0, 0, 0));
        Talasan.setName("Talasan"); // NOI18N
        Talasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanKeyPressed(evt);
            }
        });
        FormInput.add(Talasan);
        Talasan.setBounds(125, 178, 875, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tekanan Darah :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(125, 206, 90, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(220, 206, 80, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText("mmHg");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(305, 206, 40, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Nadi :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(125, 234, 90, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(220, 234, 80, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("x/mnt");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(305, 234, 40, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Respirasi :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(350, 206, 60, 23);

        Trespi.setForeground(new java.awt.Color(0, 0, 0));
        Trespi.setName("Trespi"); // NOI18N
        Trespi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrespiKeyPressed(evt);
            }
        });
        FormInput.add(Trespi);
        Trespi.setBounds(415, 206, 80, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("x/mnt");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(500, 206, 40, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Suhu :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(350, 234, 60, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(415, 234, 80, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("°C");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(500, 234, 40, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Kesadaran :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(540, 206, 60, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Tkesadaran);
        Tkesadaran.setBounds(605, 206, 200, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("SpO2 :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(540, 234, 60, 23);

        Tspo2.setForeground(new java.awt.Color(0, 0, 0));
        Tspo2.setName("Tspo2"); // NOI18N
        Tspo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspo2KeyPressed(evt);
            }
        });
        FormInput.add(Tspo2);
        Tspo2.setBounds(605, 234, 80, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("%");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(690, 234, 40, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Gr :");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput.add(jLabel36);
        jLabel36.setBounds(0, 346, 40, 23);

        Tg.setForeground(new java.awt.Color(0, 0, 0));
        Tg.setName("Tg"); // NOI18N
        Tg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgKeyPressed(evt);
            }
        });
        FormInput.add(Tg);
        Tg.setBounds(45, 346, 50, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Pr :");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput.add(jLabel37);
        jLabel37.setBounds(97, 346, 30, 23);

        Tp.setForeground(new java.awt.Color(0, 0, 0));
        Tp.setName("Tp"); // NOI18N
        Tp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpKeyPressed(evt);
            }
        });
        FormInput.add(Tp);
        Tp.setBounds(132, 346, 50, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("A :");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput.add(jLabel38);
        jLabel38.setBounds(182, 346, 30, 23);

        Ta.setForeground(new java.awt.Color(0, 0, 0));
        Ta.setName("Ta"); // NOI18N
        Ta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TaKeyPressed(evt);
            }
        });
        FormInput.add(Ta);
        Ta.setBounds(216, 346, 50, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Tahun Partus :");
        jLabel39.setName("jLabel39"); // NOI18N
        FormInput.add(jLabel39);
        jLabel39.setBounds(0, 650, 120, 23);

        TthnPartus.setForeground(new java.awt.Color(0, 0, 0));
        TthnPartus.setName("TthnPartus"); // NOI18N
        TthnPartus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TthnPartusKeyPressed(evt);
            }
        });
        FormInput.add(TthnPartus);
        TthnPartus.setBounds(125, 650, 60, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Tempat Partus :");
        jLabel40.setName("jLabel40"); // NOI18N
        FormInput.add(jLabel40);
        jLabel40.setBounds(0, 678, 120, 23);

        TtmptPartus.setForeground(new java.awt.Color(0, 0, 0));
        TtmptPartus.setName("TtmptPartus"); // NOI18N
        TtmptPartus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtmptPartusKeyPressed(evt);
            }
        });
        FormInput.add(TtmptPartus);
        TtmptPartus.setBounds(125, 678, 270, 23);

        TumurHamil.setForeground(new java.awt.Color(0, 0, 0));
        TumurHamil.setName("TumurHamil"); // NOI18N
        TumurHamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurHamilKeyPressed(evt);
            }
        });
        FormInput.add(TumurHamil);
        TumurHamil.setBounds(125, 706, 90, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Umur Hamil :");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(0, 706, 120, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Jenis Persalinan :");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(0, 734, 120, 23);

        TjnsPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        TjnsPersalinan.setName("TjnsPersalinan"); // NOI18N
        TjnsPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjnsPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(TjnsPersalinan);
        TjnsPersalinan.setBounds(125, 734, 270, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Penolong Persalinan :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(0, 762, 120, 23);

        TpenPersalinan.setForeground(new java.awt.Color(0, 0, 0));
        TpenPersalinan.setName("TpenPersalinan"); // NOI18N
        TpenPersalinan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenPersalinanKeyPressed(evt);
            }
        });
        FormInput.add(TpenPersalinan);
        TpenPersalinan.setBounds(125, 762, 270, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Penyulit :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(0, 790, 120, 23);

        Tpenyulit.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyulit.setName("Tpenyulit"); // NOI18N
        Tpenyulit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyulitKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyulit);
        Tpenyulit.setBounds(125, 790, 270, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Jenis Kelamin :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(0, 818, 120, 23);

        cmbJK.setBackground(new java.awt.Color(245, 253, 240));
        cmbJK.setForeground(new java.awt.Color(0, 0, 0));
        cmbJK.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Laki-laki", "Perempuan" }));
        cmbJK.setLightWeightPopupEnabled(false);
        cmbJK.setName("cmbJK"); // NOI18N
        FormInput.add(cmbJK);
        cmbJK.setBounds(125, 818, 90, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Berat Lahir :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(220, 818, 70, 23);

        TbrtLahir.setForeground(new java.awt.Color(0, 0, 0));
        TbrtLahir.setName("TbrtLahir"); // NOI18N
        TbrtLahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbrtLahirKeyPressed(evt);
            }
        });
        FormInput.add(TbrtLahir);
        TbrtLahir.setBounds(295, 818, 60, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("gram");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(360, 818, 40, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Keadaan Anak :");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 846, 120, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Sekarang  ");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(0, 861, 120, 23);

        TkeadaanAnk.setForeground(new java.awt.Color(0, 0, 0));
        TkeadaanAnk.setName("TkeadaanAnk"); // NOI18N
        TkeadaanAnk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeadaanAnkKeyPressed(evt);
            }
        });
        FormInput.add(TkeadaanAnk);
        TkeadaanAnk.setBounds(125, 846, 270, 23);

        BtnBatalRiw.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatalRiw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatalRiw.setMnemonic('B');
        BtnBatalRiw.setText("Baru");
        BtnBatalRiw.setToolTipText("Alt+B");
        BtnBatalRiw.setGlassColor(new java.awt.Color(255, 153, 255));
        BtnBatalRiw.setName("BtnBatalRiw"); // NOI18N
        BtnBatalRiw.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatalRiw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalRiwActionPerformed(evt);
            }
        });
        FormInput.add(BtnBatalRiw);
        BtnBatalRiw.setBounds(554, 846, 80, 30);

        BtnSimpanRiw.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanRiw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpanRiw.setMnemonic('B');
        BtnSimpanRiw.setText("Simpan Riwayat");
        BtnSimpanRiw.setToolTipText("Alt+B");
        BtnSimpanRiw.setGlassColor(new java.awt.Color(255, 153, 255));
        BtnSimpanRiw.setName("BtnSimpanRiw"); // NOI18N
        BtnSimpanRiw.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanRiw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanRiwActionPerformed(evt);
            }
        });
        FormInput.add(BtnSimpanRiw);
        BtnSimpanRiw.setBounds(404, 846, 140, 30);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Data Riwayat Kehamilan, Persalinan, & Nifas Yang Lalu ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/diperbaiki");
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
        Scroll5.setViewportView(tbRiwayat);

        FormInput.add(Scroll5);
        Scroll5.setBounds(404, 622, 600, 218);

        BtnHapusRiw.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusRiw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapusRiw.setMnemonic('B');
        BtnHapusRiw.setText("Hapus Riwayat");
        BtnHapusRiw.setToolTipText("Alt+B");
        BtnHapusRiw.setGlassColor(new java.awt.Color(255, 153, 255));
        BtnHapusRiw.setName("BtnHapusRiw"); // NOI18N
        BtnHapusRiw.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapusRiw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusRiwActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusRiw);
        BtnHapusRiw.setBounds(644, 846, 130, 30);

        BtnGantiRiw.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiRiw.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiRiw.setMnemonic('B');
        BtnGantiRiw.setText("Ganti Riwayat");
        BtnGantiRiw.setToolTipText("Alt+B");
        BtnGantiRiw.setGlassColor(new java.awt.Color(255, 153, 255));
        BtnGantiRiw.setName("BtnGantiRiw"); // NOI18N
        BtnGantiRiw.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGantiRiw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiRiwActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiRiw);
        BtnGantiRiw.setBounds(784, 846, 130, 30);

        cmbPoli.setBackground(new java.awt.Color(245, 253, 240));
        cmbPoli.setForeground(new java.awt.Color(0, 0, 0));
        cmbPoli.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbPoli.setLightWeightPopupEnabled(false);
        cmbPoli.setName("cmbPoli"); // NOI18N
        FormInput.add(cmbPoli);
        cmbPoli.setBounds(375, 150, 270, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("RIWAYAT PSIKOSOSIAL DAN SPIRITUAL :");
        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(25, 889, 300, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Status Perkawinan :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(0, 913, 120, 23);

        TsttsKawin.setEditable(false);
        TsttsKawin.setForeground(new java.awt.Color(0, 0, 0));
        TsttsKawin.setName("TsttsKawin"); // NOI18N
        FormInput.add(TsttsKawin);
        TsttsKawin.setBounds(125, 913, 240, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Jumlah Perkawinan :");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(0, 941, 120, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Istri :");
        jLabel53.setName("jLabel53"); // NOI18N
        FormInput.add(jLabel53);
        jLabel53.setBounds(125, 941, 40, 23);

        cmbIstri.setBackground(new java.awt.Color(245, 253, 240));
        cmbIstri.setForeground(new java.awt.Color(0, 0, 0));
        cmbIstri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1 X", "2 X", "3 X", "> 3 X" }));
        cmbIstri.setLightWeightPopupEnabled(false);
        cmbIstri.setName("cmbIstri"); // NOI18N
        FormInput.add(cmbIstri);
        cmbIstri.setBounds(170, 941, 60, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Suami :");
        jLabel54.setName("jLabel54"); // NOI18N
        FormInput.add(jLabel54);
        jLabel54.setBounds(230, 941, 50, 23);

        cmbSuami.setBackground(new java.awt.Color(245, 253, 240));
        cmbSuami.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuami.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "1 X", "2 X", "3 X", "> 3 X" }));
        cmbSuami.setLightWeightPopupEnabled(false);
        cmbSuami.setName("cmbSuami"); // NOI18N
        FormInput.add(cmbSuami);
        cmbSuami.setBounds(286, 941, 60, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("Usia Perkawinan :");
        jLabel55.setName("jLabel55"); // NOI18N
        FormInput.add(jLabel55);
        jLabel55.setBounds(350, 941, 100, 23);

        TusiaKawin.setForeground(new java.awt.Color(0, 0, 0));
        TusiaKawin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TusiaKawin.setName("TusiaKawin"); // NOI18N
        TusiaKawin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TusiaKawinKeyPressed(evt);
            }
        });
        FormInput.add(TusiaKawin);
        TusiaKawin.setBounds(456, 941, 50, 23);

        cmbUsiaKawin.setBackground(new java.awt.Color(245, 253, 240));
        cmbUsiaKawin.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsiaKawin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Hari", "Minggu", "Bulan", "Tahun" }));
        cmbUsiaKawin.setLightWeightPopupEnabled(false);
        cmbUsiaKawin.setName("cmbUsiaKawin"); // NOI18N
        FormInput.add(cmbUsiaKawin);
        cmbUsiaKawin.setBounds(512, 941, 70, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("Hubungan :");
        jLabel56.setName("jLabel56"); // NOI18N
        FormInput.add(jLabel56);
        jLabel56.setBounds(427, 969, 70, 23);

        TklgTerdekat.setForeground(new java.awt.Color(0, 0, 0));
        TklgTerdekat.setName("TklgTerdekat"); // NOI18N
        TklgTerdekat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TklgTerdekatKeyPressed(evt);
            }
        });
        FormInput.add(TklgTerdekat);
        TklgTerdekat.setBounds(125, 969, 300, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setText("Keluarga Terdekat :");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(0, 969, 120, 23);

        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(503, 969, 300, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Tinggal Dengan :");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(0, 997, 120, 23);

        cmbTinggal.setBackground(new java.awt.Color(245, 253, 240));
        cmbTinggal.setForeground(new java.awt.Color(0, 0, 0));
        cmbTinggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Orang Tua", "Suami", "Anak", "Sendiri", "Lainnya" }));
        cmbTinggal.setLightWeightPopupEnabled(false);
        cmbTinggal.setName("cmbTinggal"); // NOI18N
        cmbTinggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTinggalActionPerformed(evt);
            }
        });
        FormInput.add(cmbTinggal);
        cmbTinggal.setBounds(125, 997, 85, 23);

        TtglDenganLain.setForeground(new java.awt.Color(0, 0, 0));
        TtglDenganLain.setName("TtglDenganLain"); // NOI18N
        TtglDenganLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtglDenganLainKeyPressed(evt);
            }
        });
        FormInput.add(TtglDenganLain);
        TtglDenganLain.setBounds(216, 997, 280, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("Curiga Penganiayaan/Penelantaran :");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(500, 997, 190, 23);

        cmbCuriga.setBackground(new java.awt.Color(245, 253, 240));
        cmbCuriga.setForeground(new java.awt.Color(0, 0, 0));
        cmbCuriga.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbCuriga.setLightWeightPopupEnabled(false);
        cmbCuriga.setName("cmbCuriga"); // NOI18N
        FormInput.add(cmbCuriga);
        cmbCuriga.setBounds(695, 997, 58, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setText("Status Emosional :");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(590, 1025, 100, 23);

        cmbEmosi.setBackground(new java.awt.Color(245, 253, 240));
        cmbEmosi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEmosi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Tidak Semangat", "Tertekan", "Depresi", "Cemas", "Sulit Tidur" }));
        cmbEmosi.setLightWeightPopupEnabled(false);
        cmbEmosi.setName("cmbEmosi"); // NOI18N
        FormInput.add(cmbEmosi);
        cmbEmosi.setBounds(695, 1025, 110, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Kegiatan Ibadah :");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 1025, 120, 23);

        TkegiatanIbadah.setForeground(new java.awt.Color(0, 0, 0));
        TkegiatanIbadah.setName("TkegiatanIbadah"); // NOI18N
        TkegiatanIbadah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkegiatanIbadahKeyPressed(evt);
            }
        });
        FormInput.add(TkegiatanIbadah);
        TkegiatanIbadah.setBounds(125, 1025, 460, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Status Ekonomi :");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 1053, 120, 23);

        cmbEkonomi.setBackground(new java.awt.Color(245, 253, 240));
        cmbEkonomi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEkonomi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Asuransi", "Jaminan", "Biaya Sendiri", "Lainnya" }));
        cmbEkonomi.setLightWeightPopupEnabled(false);
        cmbEkonomi.setName("cmbEkonomi"); // NOI18N
        cmbEkonomi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEkonomiActionPerformed(evt);
            }
        });
        FormInput.add(cmbEkonomi);
        cmbEkonomi.setBounds(125, 1053, 95, 23);

        TsttsEkonomi.setForeground(new java.awt.Color(0, 0, 0));
        TsttsEkonomi.setName("TsttsEkonomi"); // NOI18N
        TsttsEkonomi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsttsEkonomiKeyPressed(evt);
            }
        });
        FormInput.add(TsttsEkonomi);
        TsttsEkonomi.setBounds(225, 1053, 360, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel63.setText("ASESMEN NYERI :");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(25, 1081, 220, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Nyeri :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 1105, 120, 23);

        cmbNyeri.setBackground(new java.awt.Color(245, 253, 240));
        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbNyeri.setLightWeightPopupEnabled(false);
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(125, 1105, 58, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Lokasi :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(185, 1105, 50, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        FormInput.add(Tlokasi);
        Tlokasi.setBounds(240, 1105, 360, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(605, 1105, 40, 23);

        cmbJenis.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Akut", "Kronis" }));
        cmbJenis.setLightWeightPopupEnabled(false);
        cmbJenis.setName("cmbJenis"); // NOI18N
        FormInput.add(cmbJenis);
        cmbJenis.setBounds(652, 1105, 63, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Provocation : Faktor yang memperburuk rasa nyeri");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(560, 1133, 270, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        FormInput.add(cmbProvo);
        cmbProvo.setBounds(837, 1133, 80, 23);

        Tprovo.setForeground(new java.awt.Color(0, 0, 0));
        Tprovo.setToolTipText("Alt+C");
        Tprovo.setName("Tprovo"); // NOI18N
        Tprovo.setPreferredSize(new java.awt.Dimension(140, 23));
        Tprovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprovoKeyPressed(evt);
            }
        });
        FormInput.add(Tprovo);
        Tprovo.setBounds(837, 1161, 240, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Quality : Rasa nyeri seperti");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(560, 1189, 270, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik Dibakar", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        FormInput.add(cmbQuality);
        cmbQuality.setBounds(837, 1189, 104, 23);

        Tquality.setForeground(new java.awt.Color(0, 0, 0));
        Tquality.setToolTipText("Alt+C");
        Tquality.setName("Tquality"); // NOI18N
        Tquality.setPreferredSize(new java.awt.Dimension(140, 23));
        Tquality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqualityKeyPressed(evt);
            }
        });
        FormInput.add(Tquality);
        Tquality.setBounds(837, 1217, 240, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Radiation : Nyeri menjalar ke bagian tubuh yang lain");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(560, 1245, 270, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        FormInput.add(cmbRadia);
        cmbRadia.setBounds(837, 1245, 60, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Severity : Tingkat keparahan nyeri");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(560, 1273, 270, 23);

        cmbSevere.setForeground(new java.awt.Color(0, 0, 0));
        cmbSevere.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSevere.setName("cmbSevere"); // NOI18N
        FormInput.add(cmbSevere);
        cmbSevere.setBounds(837, 1273, 90, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Time : Nyeri berlangsung");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(560, 1301, 270, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        FormInput.add(cmbTime);
        cmbTime.setBounds(837, 1301, 105, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lama :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(946, 1301, 40, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        FormInput.add(cmbLama);
        cmbLama.setBounds(990, 1301, 90, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(560, 1329, 270, 23);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        FormInput.add(cmbSkala);
        cmbSkala.setBounds(837, 1329, 45, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel67.setText("SKRINING GIZI AWAL :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(25, 1369, 220, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 1393, 390, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("diinginkan dalam 6 bulan terakhir ?");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 1409, 210, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(348, 1409, 40, 23);

        cmbGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak yakin (ada tanda : baju menjadi longgar)", "Ya, ada penurunan BB sebanyak" }));
        cmbGizi1.setName("cmbGizi1"); // NOI18N
        cmbGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi1ActionPerformed(evt);
            }
        });
        FormInput.add(cmbGizi1);
        cmbGizi1.setBounds(45, 1437, 310, 23);

        skorGZ1.setEditable(false);
        skorGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ1.setText("0");
        skorGZ1.setFocusTraversalPolicyProvider(true);
        skorGZ1.setName("skorGZ1"); // NOI18N
        FormInput.add(skorGZ1);
        skorGZ1.setBounds(358, 1437, 30, 23);

        cmbGizi1Ya.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi1Ya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbGizi1Ya.setName("cmbGizi1Ya"); // NOI18N
        cmbGizi1Ya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi1YaActionPerformed(evt);
            }
        });
        FormInput.add(cmbGizi1Ya);
        cmbGizi1Ya.setBounds(45, 1465, 310, 23);

        skorYaGZ1.setEditable(false);
        skorYaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGZ1.setText("0");
        skorYaGZ1.setFocusTraversalPolicyProvider(true);
        skorYaGZ1.setName("skorYaGZ1"); // NOI18N
        FormInput.add(skorYaGZ1);
        skorYaGZ1.setBounds(358, 1465, 30, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(400, 1393, 360, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("   makan / kesulitan menerima makanan ?");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(400, 1409, 240, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Skor :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(720, 1409, 40, 23);

        cmbGizi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGizi2.setName("cmbGizi2"); // NOI18N
        cmbGizi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi2ActionPerformed(evt);
            }
        });
        FormInput.add(cmbGizi2);
        cmbGizi2.setBounds(446, 1437, 65, 23);

        skorGZ2.setEditable(false);
        skorGZ2.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ2.setText("0");
        skorGZ2.setFocusTraversalPolicyProvider(true);
        skorGZ2.setName("skorGZ2"); // NOI18N
        FormInput.add(skorGZ2);
        skorGZ2.setBounds(730, 1437, 30, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("------>> Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(400, 1465, 320, 23);

        TotSkorGZ.setEditable(false);
        TotSkorGZ.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGZ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGZ.setText("0");
        TotSkorGZ.setFocusTraversalPolicyProvider(true);
        TotSkorGZ.setName("TotSkorGZ"); // NOI18N
        FormInput.add(TotSkorGZ);
        TotSkorGZ.setBounds(727, 1465, 35, 23);

        kesimpulanGizi.setEditable(false);
        kesimpulanGizi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGizi.setColumns(20);
        kesimpulanGizi.setRows(5);
        kesimpulanGizi.setName("kesimpulanGizi"); // NOI18N
        FormInput.add(kesimpulanGizi);
        kesimpulanGizi.setBounds(780, 1393, 300, 100);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("RIWAYAT ALERGI :");
        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(25, 1493, 220, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Riwayat Alergi :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 1517, 130, 23);

        cmbRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwayat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak ada", "Tidak diketahui" }));
        cmbRiwayat.setName("cmbRiwayat"); // NOI18N
        FormInput.add(cmbRiwayat);
        cmbRiwayat.setBounds(136, 1517, 110, 23);

        chkAlergiObat.setBackground(new java.awt.Color(255, 255, 250));
        chkAlergiObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        chkAlergiObat.setText("Alergi Obat");
        chkAlergiObat.setBorderPainted(true);
        chkAlergiObat.setBorderPaintedFlat(true);
        chkAlergiObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlergiObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlergiObat.setName("chkAlergiObat"); // NOI18N
        chkAlergiObat.setOpaque(false);
        chkAlergiObat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(chkAlergiObat);
        chkAlergiObat.setBounds(260, 1517, 100, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Reaksi : ");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(365, 1517, 60, 23);

        reaksiAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiObat.setFocusTraversalPolicyProvider(true);
        reaksiAlergiObat.setName("reaksiAlergiObat"); // NOI18N
        FormInput.add(reaksiAlergiObat);
        reaksiAlergiObat.setBounds(427, 1517, 600, 23);

        chkAlergiMakanan.setBackground(new java.awt.Color(255, 255, 250));
        chkAlergiMakanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        chkAlergiMakanan.setText("Alergi Makanan");
        chkAlergiMakanan.setBorderPainted(true);
        chkAlergiMakanan.setBorderPaintedFlat(true);
        chkAlergiMakanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlergiMakanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlergiMakanan.setName("chkAlergiMakanan"); // NOI18N
        chkAlergiMakanan.setOpaque(false);
        chkAlergiMakanan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAlergiMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlergiMakananActionPerformed(evt);
            }
        });
        FormInput.add(chkAlergiMakanan);
        chkAlergiMakanan.setBounds(260, 1545, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Reaksi : ");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(365, 1545, 60, 23);

        reaksiAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiMakanan.setFocusTraversalPolicyProvider(true);
        reaksiAlergiMakanan.setName("reaksiAlergiMakanan"); // NOI18N
        FormInput.add(reaksiAlergiMakanan);
        reaksiAlergiMakanan.setBounds(427, 1545, 600, 23);

        chkAlergiLain.setBackground(new java.awt.Color(255, 255, 250));
        chkAlergiLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        chkAlergiLain.setText("Alergi Lainnya");
        chkAlergiLain.setBorderPainted(true);
        chkAlergiLain.setBorderPaintedFlat(true);
        chkAlergiLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlergiLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlergiLain.setName("chkAlergiLain"); // NOI18N
        chkAlergiLain.setOpaque(false);
        chkAlergiLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAlergiLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAlergiLainActionPerformed(evt);
            }
        });
        FormInput.add(chkAlergiLain);
        chkAlergiLain.setBounds(260, 1573, 100, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Reaksi : ");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(365, 1573, 60, 23);

        reaksiAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiLain.setFocusTraversalPolicyProvider(true);
        reaksiAlergiLain.setName("reaksiAlergiLain"); // NOI18N
        FormInput.add(reaksiAlergiLain);
        reaksiAlergiLain.setBounds(427, 1573, 600, 23);

        chkGelang.setBackground(new java.awt.Color(255, 255, 250));
        chkGelang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGelang.setForeground(new java.awt.Color(0, 0, 0));
        chkGelang.setText("Gelang tanda alergi dipasang (warna merah)");
        chkGelang.setBorderPainted(true);
        chkGelang.setBorderPaintedFlat(true);
        chkGelang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGelang.setName("chkGelang"); // NOI18N
        chkGelang.setOpaque(false);
        chkGelang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGelang);
        chkGelang.setBounds(260, 1601, 245, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Alergi diberitahukan kepada :");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(520, 1601, 150, 23);

        cmbdiberitahukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbdiberitahukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dokter", "Farmasis/Apoteker", "Ahli Gizi" }));
        cmbdiberitahukan.setName("cmbdiberitahukan"); // NOI18N
        FormInput.add(cmbdiberitahukan);
        cmbdiberitahukan.setBounds(675, 1601, 120, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel85.setText("ASESMEN RESIKO JATUH MORSE :");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(25, 1629, 210, 23);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Katalog Data Asesmen Resiko Jatuh ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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
        Scroll8.setBounds(25, 1653, 720, 143);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Jumlah Skor :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(753, 1653, 80, 23);

        TotSkorRJ.setEditable(false);
        TotSkorRJ.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorRJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorRJ.setText("0");
        TotSkorRJ.setFocusTraversalPolicyProvider(true);
        TotSkorRJ.setName("TotSkorRJ"); // NOI18N
        FormInput.add(TotSkorRJ);
        TotSkorRJ.setBounds(840, 1653, 40, 23);

        kesimpulanResikoJatuh.setEditable(false);
        kesimpulanResikoJatuh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skor Resiko Jatuh : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResikoJatuh.setColumns(20);
        kesimpulanResikoJatuh.setRows(5);
        kesimpulanResikoJatuh.setName("kesimpulanResikoJatuh"); // NOI18N
        FormInput.add(kesimpulanResikoJatuh);
        kesimpulanResikoJatuh.setBounds(753, 1681, 220, 113);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(25, 1801, 60, 23);

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
        TCariResiko.setBounds(89, 1801, 215, 23);

        BtnCariResiko.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCariResiko.setMnemonic('1');
        BtnCariResiko.setText("Tampilkan Katalog");
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
        BtnCariResiko.setBounds(308, 1801, 142, 23);

        BtnAllResiko.setForeground(new java.awt.Color(0, 0, 0));
        BtnAllResiko.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAllResiko.setMnemonic('2');
        BtnAllResiko.setText("Semua Data");
        BtnAllResiko.setToolTipText("Alt+2");
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
        BtnAllResiko.setBounds(460, 1801, 120, 23);

        label13.setForeground(new java.awt.Color(0, 0, 0));
        label13.setText("Fungsional :");
        label13.setName("label13"); // NOI18N
        label13.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label13);
        label13.setBounds(0, 1829, 120, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("1. Alat Bantu :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1853, 120, 23);

        TalatBantu.setForeground(new java.awt.Color(0, 0, 0));
        TalatBantu.setName("TalatBantu"); // NOI18N
        TalatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatBantuKeyPressed(evt);
            }
        });
        FormInput.add(TalatBantu);
        TalatBantu.setBounds(125, 1853, 390, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("2. Prothesis :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1881, 120, 23);

        Tprothesis.setForeground(new java.awt.Color(0, 0, 0));
        Tprothesis.setName("Tprothesis"); // NOI18N
        Tprothesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprothesisKeyPressed(evt);
            }
        });
        FormInput.add(Tprothesis);
        Tprothesis.setBounds(125, 1881, 390, 23);

        TcacatTubuh.setForeground(new java.awt.Color(0, 0, 0));
        TcacatTubuh.setName("TcacatTubuh"); // NOI18N
        TcacatTubuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcacatTubuhKeyPressed(evt);
            }
        });
        FormInput.add(TcacatTubuh);
        TcacatTubuh.setBounds(650, 1853, 390, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("3. Cacat Tubuh :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(555, 1853, 90, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("4. ADL :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(555, 1881, 90, 23);

        cmbAdl.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Mandiri", "Dibantu" }));
        cmbAdl.setName("cmbAdl"); // NOI18N
        FormInput.add(cmbAdl);
        cmbAdl.setBounds(650, 1881, 72, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("5. Riwayat Jatuh Dalam 3 Bulan Terakhir :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(724, 1881, 220, 23);

        cmbRiwayat3bln.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwayat3bln.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwayat3bln.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRiwayat3bln.setLightWeightPopupEnabled(false);
        cmbRiwayat3bln.setName("cmbRiwayat3bln"); // NOI18N
        FormInput.add(cmbRiwayat3bln);
        cmbRiwayat3bln.setBounds(950, 1881, 58, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Nama Perawat :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label14);
        label14.setBounds(555, 1825, 90, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(650, 1825, 390, 23);

        BtnPerawat.setForeground(new java.awt.Color(0, 0, 0));
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
        BtnPerawat.setBounds(1040, 1825, 28, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("KEBUTUHAN KOMUNIKASI DAN EDUKASI :");
        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(25, 1909, 260, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Terdapat Hambatan Dalam Pembelajaran :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 1933, 250, 23);

        cmbHambatan.setBackground(new java.awt.Color(245, 253, 240));
        cmbHambatan.setForeground(new java.awt.Color(0, 0, 0));
        cmbHambatan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbHambatan.setLightWeightPopupEnabled(false);
        cmbHambatan.setName("cmbHambatan"); // NOI18N
        cmbHambatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHambatanActionPerformed(evt);
            }
        });
        FormInput.add(cmbHambatan);
        cmbHambatan.setBounds(257, 1933, 58, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Jika Ya :");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(320, 1933, 50, 23);

        cmbHambatanYa.setBackground(new java.awt.Color(245, 253, 240));
        cmbHambatanYa.setForeground(new java.awt.Color(0, 0, 0));
        cmbHambatanYa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Pendengaran", "Budaya", "Penglihatan", "Emosi", "Kognitif", "Bahasa", "Fisik", "Lainnya" }));
        cmbHambatanYa.setLightWeightPopupEnabled(false);
        cmbHambatanYa.setName("cmbHambatanYa"); // NOI18N
        cmbHambatanYa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHambatanYaActionPerformed(evt);
            }
        });
        FormInput.add(cmbHambatanYa);
        cmbHambatanYa.setBounds(375, 1933, 100, 23);

        ThambatanLain.setForeground(new java.awt.Color(0, 0, 0));
        ThambatanLain.setName("ThambatanLain"); // NOI18N
        ThambatanLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThambatanLainKeyPressed(evt);
            }
        });
        FormInput.add(ThambatanLain);
        ThambatanLain.setBounds(482, 1933, 560, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Dibutuhkan Penerjemah :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 1961, 250, 23);

        cmbPenerjemah.setBackground(new java.awt.Color(245, 253, 240));
        cmbPenerjemah.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenerjemah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbPenerjemah.setLightWeightPopupEnabled(false);
        cmbPenerjemah.setName("cmbPenerjemah"); // NOI18N
        cmbPenerjemah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPenerjemahActionPerformed(evt);
            }
        });
        FormInput.add(cmbPenerjemah);
        cmbPenerjemah.setBounds(257, 1961, 58, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Sebutkan :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(320, 1961, 60, 23);

        Tsebutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tsebutkan.setName("Tsebutkan"); // NOI18N
        Tsebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsebutkanKeyPressed(evt);
            }
        });
        FormInput.add(Tsebutkan);
        Tsebutkan.setBounds(386, 1961, 470, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Bahasa Isyarat :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(855, 1961, 90, 23);

        cmbBahasa.setBackground(new java.awt.Color(245, 253, 240));
        cmbBahasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbBahasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbBahasa.setLightWeightPopupEnabled(false);
        cmbBahasa.setName("cmbBahasa"); // NOI18N
        FormInput.add(cmbBahasa);
        cmbBahasa.setBounds(950, 1961, 58, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kebutuhan Edukasi (pilih topik edukasi pada kotak yang tersedia) :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 1989, 350, 23);

        chkDiagnosa.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagnosa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagnosa.setText("Diagnosa dan Manajemen Penyakit");
        chkDiagnosa.setBorderPainted(true);
        chkDiagnosa.setBorderPaintedFlat(true);
        chkDiagnosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagnosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagnosa.setName("chkDiagnosa"); // NOI18N
        chkDiagnosa.setOpaque(false);
        chkDiagnosa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDiagnosa);
        chkDiagnosa.setBounds(25, 2013, 200, 23);

        chkTindakan.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakan.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakan.setText("Tindakan Keperawatan");
        chkTindakan.setBorderPainted(true);
        chkTindakan.setBorderPaintedFlat(true);
        chkTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakan.setName("chkTindakan"); // NOI18N
        chkTindakan.setOpaque(false);
        chkTindakan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTindakanActionPerformed(evt);
            }
        });
        FormInput.add(chkTindakan);
        chkTindakan.setBounds(25, 2041, 140, 23);

        Ttindakan.setForeground(new java.awt.Color(0, 0, 0));
        Ttindakan.setName("Ttindakan"); // NOI18N
        FormInput.add(Ttindakan);
        Ttindakan.setBounds(165, 2069, 560, 23);

        chkObat.setBackground(new java.awt.Color(255, 255, 250));
        chkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObat.setForeground(new java.awt.Color(0, 0, 0));
        chkObat.setText("Obat-obatan / Terapi");
        chkObat.setBorderPainted(true);
        chkObat.setBorderPaintedFlat(true);
        chkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObat.setName("chkObat"); // NOI18N
        chkObat.setOpaque(false);
        chkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkObat);
        chkObat.setBounds(250, 2013, 130, 23);

        chkRehab.setBackground(new java.awt.Color(255, 255, 250));
        chkRehab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRehab.setForeground(new java.awt.Color(0, 0, 0));
        chkRehab.setText("Rehabilitasi");
        chkRehab.setBorderPainted(true);
        chkRehab.setBorderPaintedFlat(true);
        chkRehab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRehab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRehab.setName("chkRehab"); // NOI18N
        chkRehab.setOpaque(false);
        chkRehab.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRehab);
        chkRehab.setBounds(250, 2041, 130, 23);

        chkDiet.setBackground(new java.awt.Color(255, 255, 250));
        chkDiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiet.setForeground(new java.awt.Color(0, 0, 0));
        chkDiet.setText("Diet dan Nutrisi");
        chkDiet.setBorderPainted(true);
        chkDiet.setBorderPaintedFlat(true);
        chkDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiet.setName("chkDiet"); // NOI18N
        chkDiet.setOpaque(false);
        chkDiet.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDiet);
        chkDiet.setBounds(400, 2013, 110, 23);

        chkManajemen.setBackground(new java.awt.Color(255, 255, 250));
        chkManajemen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkManajemen.setForeground(new java.awt.Color(0, 0, 0));
        chkManajemen.setText("Manajemen Nyeri");
        chkManajemen.setBorderPainted(true);
        chkManajemen.setBorderPaintedFlat(true);
        chkManajemen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkManajemen.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkManajemen.setName("chkManajemen"); // NOI18N
        chkManajemen.setOpaque(false);
        chkManajemen.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkManajemen);
        chkManajemen.setBounds(400, 2041, 110, 23);

        chkLain.setBackground(new java.awt.Color(255, 255, 250));
        chkLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLain.setText("Lain-lain, Sebutkan");
        chkLain.setBorderPainted(true);
        chkLain.setBorderPaintedFlat(true);
        chkLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLain.setName("chkLain"); // NOI18N
        chkLain.setOpaque(false);
        chkLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainActionPerformed(evt);
            }
        });
        FormInput.add(chkLain);
        chkLain.setBounds(530, 2013, 120, 23);

        TLainSebutkan.setForeground(new java.awt.Color(0, 0, 0));
        TLainSebutkan.setName("TLainSebutkan"); // NOI18N
        FormInput.add(TLainSebutkan);
        TLainSebutkan.setBounds(655, 2013, 385, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Tindakan Keperawatan :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 2069, 160, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Edukasi Pasien : Edukasi awal disampaikan tentang diagnosis, rencana dan tujuan terapi kepada");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 2097, 490, 23);

        chkPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkPasien.setText("Pasien");
        chkPasien.setBorderPainted(true);
        chkPasien.setBorderPaintedFlat(true);
        chkPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasien.setName("chkPasien"); // NOI18N
        chkPasien.setOpaque(false);
        chkPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPasien);
        chkPasien.setBounds(25, 2121, 60, 23);

        chkKlgPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkKlgPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkKlgPasien.setText("Keluarga Pasien, Nama :");
        chkKlgPasien.setBorderPainted(true);
        chkKlgPasien.setBorderPaintedFlat(true);
        chkKlgPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKlgPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKlgPasien.setName("chkKlgPasien"); // NOI18N
        chkKlgPasien.setOpaque(false);
        chkKlgPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        chkKlgPasien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkKlgPasienActionPerformed(evt);
            }
        });
        FormInput.add(chkKlgPasien);
        chkKlgPasien.setBounds(95, 2121, 140, 23);

        TnmKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgPasien.setName("TnmKlgPasien"); // NOI18N
        FormInput.add(TnmKlgPasien);
        TnmKlgPasien.setBounds(236, 2121, 330, 23);

        chkTdkBeriEdukasi.setBackground(new java.awt.Color(255, 255, 250));
        chkTdkBeriEdukasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTdkBeriEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        chkTdkBeriEdukasi.setText("Tidak dapat memberikan edukasi kepada pasien atau keluarga, karena");
        chkTdkBeriEdukasi.setBorderPainted(true);
        chkTdkBeriEdukasi.setBorderPaintedFlat(true);
        chkTdkBeriEdukasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTdkBeriEdukasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTdkBeriEdukasi.setName("chkTdkBeriEdukasi"); // NOI18N
        chkTdkBeriEdukasi.setOpaque(false);
        chkTdkBeriEdukasi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTdkBeriEdukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTdkBeriEdukasiActionPerformed(evt);
            }
        });
        FormInput.add(chkTdkBeriEdukasi);
        chkTdkBeriEdukasi.setBounds(25, 2149, 360, 23);

        TtdkBeriEdukasi.setForeground(new java.awt.Color(0, 0, 0));
        TtdkBeriEdukasi.setName("TtdkBeriEdukasi"); // NOI18N
        FormInput.add(TtdkBeriEdukasi);
        TtdkBeriEdukasi.setBounds(385, 2149, 655, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Tanggal :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 2177, 70, 23);

        Ttgl.setEditable(false);
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        FormInput.add(Ttgl);
        Ttgl.setBounds(75, 2177, 100, 23);

        ChkJam.setBackground(new java.awt.Color(255, 255, 250));
        ChkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJam.setForeground(new java.awt.Color(0, 0, 0));
        ChkJam.setText("Jam :");
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
        FormInput.add(ChkJam);
        ChkJam.setBounds(180, 2177, 60, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(245, 2177, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(295, 2177, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(345, 2177, 45, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Nama Dokter :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(390, 2177, 90, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(485, 2177, 555, 23);

        BtnDokter.setForeground(new java.awt.Color(0, 0, 0));
        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(1040, 2177, 28, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("LEMBAR DISCHARGE PLANNING :");
        jLabel106.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(25, 2205, 260, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("IDENTIFIKASI, Seleksi/Skrining Pasien");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 2229, 210, 23);

        chkIden1.setBackground(new java.awt.Color(255, 255, 250));
        chkIden1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden1.setForeground(new java.awt.Color(0, 0, 0));
        chkIden1.setText("Pasien dengan keterbatasan kognitif, ketergantungan ADL tinggi");
        chkIden1.setBorderPainted(true);
        chkIden1.setBorderPaintedFlat(true);
        chkIden1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden1.setName("chkIden1"); // NOI18N
        chkIden1.setOpaque(false);
        chkIden1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden1);
        chkIden1.setBounds(25, 2257, 340, 23);

        chkIden2.setBackground(new java.awt.Color(255, 255, 250));
        chkIden2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden2.setForeground(new java.awt.Color(0, 0, 0));
        chkIden2.setText("Wanita usia rentan (Ibu hamil, Ibu menyusui, Lansia)");
        chkIden2.setBorderPainted(true);
        chkIden2.setBorderPaintedFlat(true);
        chkIden2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden2.setName("chkIden2"); // NOI18N
        chkIden2.setOpaque(false);
        chkIden2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden2);
        chkIden2.setBounds(25, 2285, 280, 23);

        chkIden3.setBackground(new java.awt.Color(255, 255, 250));
        chkIden3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden3.setForeground(new java.awt.Color(0, 0, 0));
        chkIden3.setText("Pasien dengan resiko tinggi (infeksi, kejang, penurunan kesadaran)");
        chkIden3.setBorderPainted(true);
        chkIden3.setBorderPaintedFlat(true);
        chkIden3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden3.setName("chkIden3"); // NOI18N
        chkIden3.setOpaque(false);
        chkIden3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden3);
        chkIden3.setBounds(25, 2313, 360, 23);

        chkIden4.setBackground(new java.awt.Color(255, 255, 250));
        chkIden4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden4.setForeground(new java.awt.Color(0, 0, 0));
        chkIden4.setText("Potensi komplain tinggi");
        chkIden4.setBorderPainted(true);
        chkIden4.setBorderPaintedFlat(true);
        chkIden4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden4.setName("chkIden4"); // NOI18N
        chkIden4.setOpaque(false);
        chkIden4.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden4);
        chkIden4.setBounds(25, 2341, 150, 23);

        chkIden5.setBackground(new java.awt.Color(255, 255, 250));
        chkIden5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden5.setForeground(new java.awt.Color(0, 0, 0));
        chkIden5.setText("Pasien dengan penyakit kronis, katastropik, terminal");
        chkIden5.setBorderPainted(true);
        chkIden5.setBorderPaintedFlat(true);
        chkIden5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden5.setName("chkIden5"); // NOI18N
        chkIden5.setOpaque(false);
        chkIden5.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden5);
        chkIden5.setBounds(25, 2369, 280, 23);

        chkIden6.setBackground(new java.awt.Color(255, 255, 250));
        chkIden6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden6.setForeground(new java.awt.Color(0, 0, 0));
        chkIden6.setText("Sering masuk IGD, readmisi RS");
        chkIden6.setBorderPainted(true);
        chkIden6.setBorderPaintedFlat(true);
        chkIden6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden6.setName("chkIden6"); // NOI18N
        chkIden6.setOpaque(false);
        chkIden6.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden6);
        chkIden6.setBounds(400, 2257, 270, 23);

        chkIden7.setBackground(new java.awt.Color(255, 255, 250));
        chkIden7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden7.setForeground(new java.awt.Color(0, 0, 0));
        chkIden7.setText("Perkiraan asuhan dengan biaya tinggi");
        chkIden7.setBorderPainted(true);
        chkIden7.setBorderPaintedFlat(true);
        chkIden7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden7.setName("chkIden7"); // NOI18N
        chkIden7.setOpaque(false);
        chkIden7.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden7);
        chkIden7.setBounds(400, 2285, 270, 23);

        chkIden8.setBackground(new java.awt.Color(255, 255, 250));
        chkIden8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden8.setForeground(new java.awt.Color(0, 0, 0));
        chkIden8.setText("Pasien tanpa keluarga / terlantar, tinggal sendiri");
        chkIden8.setBorderPainted(true);
        chkIden8.setBorderPaintedFlat(true);
        chkIden8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden8.setName("chkIden8"); // NOI18N
        chkIden8.setOpaque(false);
        chkIden8.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden8);
        chkIden8.setBounds(400, 2313, 270, 23);

        chkIden9.setBackground(new java.awt.Color(255, 255, 250));
        chkIden9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden9.setForeground(new java.awt.Color(0, 0, 0));
        chkIden9.setText("Kasus yang melebihi rata-rata lama rawat");
        chkIden9.setBorderPainted(true);
        chkIden9.setBorderPaintedFlat(true);
        chkIden9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden9.setName("chkIden9"); // NOI18N
        chkIden9.setOpaque(false);
        chkIden9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden9);
        chkIden9.setBounds(400, 2341, 270, 23);

        chkIden10.setBackground(new java.awt.Color(255, 255, 250));
        chkIden10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden10.setForeground(new java.awt.Color(0, 0, 0));
        chkIden10.setText("Kasus yang membutuhkan kontinuitas pelayanan, rencana pemulangan penting/beresiko");
        chkIden10.setBorderPainted(true);
        chkIden10.setBorderPaintedFlat(true);
        chkIden10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden10.setName("chkIden10"); // NOI18N
        chkIden10.setOpaque(false);
        chkIden10.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden10);
        chkIden10.setBounds(400, 2369, 450, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("Memerlukan :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(690, 2229, 70, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Manajer Pelayanan Pasien :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(690, 2257, 140, 23);

        cmbMPP.setBackground(new java.awt.Color(245, 253, 240));
        cmbMPP.setForeground(new java.awt.Color(0, 0, 0));
        cmbMPP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbMPP.setLightWeightPopupEnabled(false);
        cmbMPP.setName("cmbMPP"); // NOI18N
        FormInput.add(cmbMPP);
        cmbMPP.setBounds(835, 2257, 58, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Discharge Planning :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(690, 2285, 140, 23);

        cmbDP.setBackground(new java.awt.Color(245, 253, 240));
        cmbDP.setForeground(new java.awt.Color(0, 0, 0));
        cmbDP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbDP.setLightWeightPopupEnabled(false);
        cmbDP.setName("cmbDP"); // NOI18N
        FormInput.add(cmbDP);
        cmbDP.setBounds(835, 2285, 58, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Nama Bidan :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(690, 2313, 75, 23);

        TnmBidan.setEditable(false);
        TnmBidan.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan.setName("TnmBidan"); // NOI18N
        FormInput.add(TnmBidan);
        TnmBidan.setBounds(770, 2313, 270, 23);

        BtnBidan.setForeground(new java.awt.Color(0, 0, 0));
        BtnBidan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan.setMnemonic('2');
        BtnBidan.setToolTipText("Alt+2");
        BtnBidan.setName("BtnBidan"); // NOI18N
        BtnBidan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidanActionPerformed(evt);
            }
        });
        FormInput.add(BtnBidan);
        BtnBidan.setBounds(1040, 2313, 28, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Keadaan Umum :");
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(25, 206, 100, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Cara Pasien Datang :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 262, 120, 23);

        cmbCaraDatang.setBackground(new java.awt.Color(245, 253, 240));
        cmbCaraDatang.setForeground(new java.awt.Color(0, 0, 0));
        cmbCaraDatang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sendiri", "Rujukan Bidan/BPM", "PKM", "SPOG", "RS Lain" }));
        cmbCaraDatang.setLightWeightPopupEnabled(false);
        cmbCaraDatang.setName("cmbCaraDatang"); // NOI18N
        cmbCaraDatang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCaraDatangActionPerformed(evt);
            }
        });
        FormInput.add(cmbCaraDatang);
        cmbCaraDatang.setBounds(125, 262, 130, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Rujukan Bidan/BPM :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(260, 262, 110, 23);

        Trujukan.setForeground(new java.awt.Color(0, 0, 0));
        Trujukan.setName("Trujukan"); // NOI18N
        Trujukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrujukanKeyPressed(evt);
            }
        });
        FormInput.add(Trujukan);
        Trujukan.setBounds(375, 262, 625, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("PKM :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(260, 290, 110, 23);

        Tpkm.setForeground(new java.awt.Color(0, 0, 0));
        Tpkm.setName("Tpkm"); // NOI18N
        Tpkm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpkmKeyPressed(evt);
            }
        });
        FormInput.add(Tpkm);
        Tpkm.setBounds(375, 290, 625, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("RS Lain :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(260, 318, 110, 23);

        TrsLain.setForeground(new java.awt.Color(0, 0, 0));
        TrsLain.setName("TrsLain"); // NOI18N
        TrsLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrsLainKeyPressed(evt);
            }
        });
        FormInput.add(TrsLain);
        TrsLain.setBounds(375, 318, 625, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Hamil :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(268, 346, 45, 23);

        Thamil.setForeground(new java.awt.Color(0, 0, 0));
        Thamil.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Thamil.setName("Thamil"); // NOI18N
        Thamil.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThamilKeyPressed(evt);
            }
        });
        FormInput.add(Thamil);
        Thamil.setBounds(318, 346, 50, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("minggu");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(372, 346, 40, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Dengan :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 374, 120, 23);

        Tdengan.setForeground(new java.awt.Color(0, 0, 0));
        Tdengan.setName("Tdengan"); // NOI18N
        Tdengan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdenganKeyPressed(evt);
            }
        });
        FormInput.add(Tdengan);
        Tdengan.setBounds(125, 374, 875, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel119.setText("Keluhan :");
        jLabel119.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(25, 402, 100, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Perut Mules / Nyeri Mulai :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 426, 190, 23);

        cmbPerut.setBackground(new java.awt.Color(245, 253, 240));
        cmbPerut.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbPerut.setLightWeightPopupEnabled(false);
        cmbPerut.setName("cmbPerut"); // NOI18N
        cmbPerut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerutActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerut);
        cmbPerut.setBounds(195, 426, 58, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Mulai Tgl. / Jam :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(260, 426, 90, 23);

        Ttgl_perut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023 18:02:07" }));
        Ttgl_perut.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Ttgl_perut.setName("Ttgl_perut"); // NOI18N
        Ttgl_perut.setOpaque(false);
        FormInput.add(Ttgl_perut);
        Ttgl_perut.setBounds(355, 426, 130, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel122.setText("WITA");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(490, 426, 40, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Keluar Lendir Darah / Darah :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 454, 190, 23);

        cmbLendir.setBackground(new java.awt.Color(245, 253, 240));
        cmbLendir.setForeground(new java.awt.Color(0, 0, 0));
        cmbLendir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbLendir.setLightWeightPopupEnabled(false);
        cmbLendir.setName("cmbLendir"); // NOI18N
        cmbLendir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLendirActionPerformed(evt);
            }
        });
        FormInput.add(cmbLendir);
        cmbLendir.setBounds(195, 454, 58, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Mulai Tgl. / Jam :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(260, 454, 90, 23);

        Ttgl_lendir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 00:02:46" }));
        Ttgl_lendir.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Ttgl_lendir.setName("Ttgl_lendir"); // NOI18N
        Ttgl_lendir.setOpaque(false);
        FormInput.add(Ttgl_lendir);
        Ttgl_lendir.setBounds(355, 454, 130, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("WITA");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(490, 454, 40, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Darah Encer / Segar / Bergumpal :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 482, 190, 23);

        cmbDarah.setBackground(new java.awt.Color(245, 253, 240));
        cmbDarah.setForeground(new java.awt.Color(0, 0, 0));
        cmbDarah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbDarah.setLightWeightPopupEnabled(false);
        cmbDarah.setName("cmbDarah"); // NOI18N
        cmbDarah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDarahActionPerformed(evt);
            }
        });
        FormInput.add(cmbDarah);
        cmbDarah.setBounds(195, 482, 58, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Banyak / Sedikit-sedikit Mulai Tgl. / Jam :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(260, 482, 206, 23);

        Ttgl_darah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 00:09:13" }));
        Ttgl_darah.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Ttgl_darah.setName("Ttgl_darah"); // NOI18N
        Ttgl_darah.setOpaque(false);
        FormInput.add(Ttgl_darah);
        Ttgl_darah.setBounds(472, 482, 130, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel128.setText("WITA");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(610, 482, 40, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Keluar Air-air :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(0, 510, 190, 23);

        cmbAir.setBackground(new java.awt.Color(245, 253, 240));
        cmbAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbAir.setLightWeightPopupEnabled(false);
        cmbAir.setName("cmbAir"); // NOI18N
        cmbAir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAirActionPerformed(evt);
            }
        });
        FormInput.add(cmbAir);
        cmbAir.setBounds(195, 510, 58, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Banyak / Sedikit-sedikit Mulai Tgl. / Jam :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(260, 510, 206, 23);

        Ttgl_air.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 00:16:48" }));
        Ttgl_air.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Ttgl_air.setName("Ttgl_air"); // NOI18N
        Ttgl_air.setOpaque(false);
        FormInput.add(Ttgl_air);
        Ttgl_air.setBounds(472, 510, 130, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel131.setText("WITA");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(610, 510, 40, 23);

        cmbJenisAir.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenisAir.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenisAir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Jernih", "Keruh", "Meconium" }));
        cmbJenisAir.setLightWeightPopupEnabled(false);
        cmbJenisAir.setName("cmbJenisAir"); // NOI18N
        FormInput.add(cmbJenisAir);
        cmbJenisAir.setBounds(650, 510, 85, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Pusing :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(0, 538, 190, 23);

        cmbPusing.setBackground(new java.awt.Color(245, 253, 240));
        cmbPusing.setForeground(new java.awt.Color(0, 0, 0));
        cmbPusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbPusing.setLightWeightPopupEnabled(false);
        cmbPusing.setName("cmbPusing"); // NOI18N
        cmbPusing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPusingActionPerformed(evt);
            }
        });
        FormInput.add(cmbPusing);
        cmbPusing.setBounds(195, 538, 58, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Mulai Tgl. / Jam :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(260, 538, 90, 23);

        Ttgl_pusing.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "05-10-2023 00:27:12" }));
        Ttgl_pusing.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        Ttgl_pusing.setName("Ttgl_pusing"); // NOI18N
        Ttgl_pusing.setOpaque(false);
        FormInput.add(Ttgl_pusing);
        Ttgl_pusing.setBounds(355, 538, 130, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("WITA");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(490, 538, 40, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Asesmen", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023" }));
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

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-10-2023" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(195, 23));
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
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass9.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(LCount);

        internalFrame3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        TabRawat.addTab("Data Asesmen", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            kdpoli = "";
            if (cmbPoli.getSelectedIndex() == 0) {
                kdpoli = "-";
            } else if (cmbPoli.getSelectedIndex() == 1) {
                kdpoli = "IGDK";
            } else if (cmbPoli.getSelectedIndex() == 2) {
                kdpoli = "OBG";
            }
            
//            if (Sequel.menyimpantf("penilaian_awal_keperawatan_kebidanan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 119, new String[]{
//                TNoRw.getText(), Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.getSelectedItem().toString().substring(11, 19), Informasi.getSelectedItem().toString(), TD.getText(), Nadi.getText(), RR.getText(), Suhu.getText(), GCS.getText(), BB.getText(), TB.getText(), LILA.getText(), BMI.getText(), TFU.getText(), TBJ.getText(), Letak.getText(),
//                Presentasi.getText(), Penurunan.getText(), Kontraksi.getText(), Kekuatan.getText(), Lamanya.getText(), BJJ.getText(), KeteranganBJJ.getSelectedItem().toString(), Portio.getText(), PembukaanServiks.getText(), Ketuban.getText(), Hodge.getText(), Inspekulo.getSelectedItem().toString(), KeteranganInspekulo.getText(), CTG.getSelectedItem().toString(),
//                KeteranganCTG.getText(), USG.getSelectedItem().toString(), KeteranganUSG.getText(), Laboratorium.getSelectedItem().toString(), KeteranganLaboratorium.getText(), Lakmus.getSelectedItem().toString(), KeteranganLakmus.getText(), PemeriksaanPanggul.getSelectedItem().toString(), KeluhanUtama.getText(), Umur.getText(), Lama.getText(),
//                Banyaknya.getText(), HaidTerakhir.getText(), Siklus.getText(), KetSiklus.getSelectedItem().toString(), KetSiklus2.getSelectedItem().toString(), StatusMenikah.getSelectedItem().toString(), KaliMenikah.getText(), UsiaKawin1.getText(), StatusKawin1.getSelectedItem().toString(), UsiaKawin2.getText(), StatusKawin2.getSelectedItem().toString(),
//                UsiaKawin3.getText(), StatusKawin3.getSelectedItem().toString(), Valid.SetTgl(HPHT.getSelectedItem() + ""), UsiaKehamilan.getText(), Valid.SetTgl(TP.getSelectedItem() + ""), RiwayatImunisasi.getSelectedItem().toString(), JumlahImunisasi.getText(), G.getText(), P.getText(), A.getText(), Hidup.getText(), RiwayatGenekologi.getSelectedItem().toString(),
//                KebiasaanObat.getSelectedItem().toString(), KebiasaanObatDiminum.getText(), KebiasaanMerokok.getSelectedItem().toString(), KebiasaanJumlahRokok.getText(), KebiasaanAlkohol.getSelectedItem().toString(), KebiasaanJumlahAlkohol.getText(), KebiasaanNarkoba.getSelectedItem().toString(), RiwayatKB.getSelectedItem().toString(), LamanyaKB.getText(),
//                KomplikasiKB.getSelectedItem().toString(), KeteranganKomplikasiKB.getText(), BerhentiKB.getText(), AlasanBerhentiKB.getText(), AlatBantu.getSelectedItem().toString(), KetBantu.getText(), Prothesa.getSelectedItem().toString(), KetProthesa.getText(), ADL.getSelectedItem().toString(), StatusPsiko.getSelectedItem().toString(), KetPsiko.getText(),
//                HubunganKeluarga.getSelectedItem().toString(), TinggalDengan.getSelectedItem().toString(), KetTinggal.getText(), Ekonomi.getSelectedItem().toString(), StatusBudaya.getSelectedItem().toString(), KetBudaya.getText(), Edukasi.getSelectedItem().toString(), KetEdukasi.getText(), RJa1.getSelectedItem().toString(), RJa2.getSelectedItem().toString(),
//                RJb.getSelectedItem().toString(), Hasil.getSelectedItem().toString(), Lapor.getSelectedItem().toString(), KetLapor.getText(), SG1.getSelectedItem().toString(), Nilai1.getSelectedItem().toString(), SG2.getSelectedItem().toString(), Nilai2.getSelectedItem().toString(), TotalHasil.getText(), Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
//                KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(), Lokasi.getText(), Menyebar.getSelectedItem().toString(), SkalaNyeri.getSelectedItem().toString(), Durasi.getText(), NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(), PadaDokter.getSelectedItem().toString(), KetDokter.getText(), Masalah.getText(),
//                Tindakan.getText(), KdPetugas.getText(), CacatFisik.getText()
//            }) == true) {
//                emptTeks();
//            }
        }    
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } 
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
//                if (KdPetugas.getText().equals(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString())) {
//                    hapus();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
//                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRM.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
//                    if (KdPetugas.getText().equals(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 124).toString())) {
//                        ganti();
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh petugas yang bersangkutan..!!");
//                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        } else if (tabMode.getRowCount() != 0) {

        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            TCari.setText("");
            tampil();
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                
            } catch (java.lang.NullPointerException e) {
            }
            if((evt.getClickCount()==2)&&(tbAsesmen.getSelectedColumn()==0)){
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    getData();
                    TabRawat.setSelectedIndex(0);
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==1){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilFaktorResiko();
        Sequel.cariIsiComboDB("select nm_poli from poliklinik where kd_poli in ('IGDK','OBG') order by nm_poli", cmbPoli);
        
        if (Sequel.cariInteger("select count(-1) from asesmen_kebidanan_ralan where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from asesmen_kebidanan_ralan where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void TglAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TglAsesmenKeyPressed
        //Valid.pindah(evt,Rencana,Informasi);
    }//GEN-LAST:event_TglAsesmenKeyPressed

    private void BtnAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlamatActionPerformed
        if (TalamatPx.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Alamat pasien masih belum terisi..!!");
        } else {
            TalamatSuami.setText(TalamatPx.getText());
            cmbAgama.requestFocus();
        }
    }//GEN-LAST:event_BtnAlamatActionPerformed

    private void TnmSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSuamiKeyPressed
        Valid.pindah(evt, TnmSuami, TumurSuami);
    }//GEN-LAST:event_TnmSuamiKeyPressed

    private void TumurSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurSuamiKeyPressed
        Valid.pindah(evt, TnmSuami, TpekerjaanSuami);
    }//GEN-LAST:event_TumurSuamiKeyPressed

    private void TpekerjaanSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpekerjaanSuamiKeyPressed
        Valid.pindah(evt, TumurSuami, TalamatSuami);
    }//GEN-LAST:event_TpekerjaanSuamiKeyPressed

    private void TalamatSuamiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatSuamiKeyPressed
        Valid.pindah(evt, TpekerjaanSuami, cmbAgama);
    }//GEN-LAST:event_TalamatSuamiKeyPressed

    private void TalasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanKeyPressed
        Valid.pindah(evt, cmbPoli, Ttd);
    }//GEN-LAST:event_TalasanKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        Valid.pindah(evt, Talasan, Tnadi);
    }//GEN-LAST:event_TtdKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, Ttd, Trespi);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TrespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrespiKeyPressed
        Valid.pindah(evt, Tnadi, Tsuhu);
    }//GEN-LAST:event_TrespiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Trespi, Tkesadaran);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, Tsuhu, Tspo2);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgKeyPressed
        Valid.pindah(evt, Tg, Tp);
    }//GEN-LAST:event_TgKeyPressed

    private void TpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpKeyPressed
        Valid.pindah(evt, Tp, Ta);
    }//GEN-LAST:event_TpKeyPressed

    private void TaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TaKeyPressed
        Valid.pindah(evt, Tp, TthnPartus);
    }//GEN-LAST:event_TaKeyPressed

    private void TthnPartusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TthnPartusKeyPressed
        Valid.pindah(evt, Ta, TtmptPartus);
    }//GEN-LAST:event_TthnPartusKeyPressed

    private void Tspo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspo2KeyPressed
        Valid.pindah(evt, Tkesadaran, Tg);
    }//GEN-LAST:event_Tspo2KeyPressed

    private void TtmptPartusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtmptPartusKeyPressed
        Valid.pindah(evt, TthnPartus, TumurHamil);
    }//GEN-LAST:event_TtmptPartusKeyPressed

    private void TumurHamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurHamilKeyPressed
        Valid.pindah(evt, TtmptPartus, TjnsPersalinan);
    }//GEN-LAST:event_TumurHamilKeyPressed

    private void TjnsPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjnsPersalinanKeyPressed
        Valid.pindah(evt, TumurHamil, TpenPersalinan);
    }//GEN-LAST:event_TjnsPersalinanKeyPressed

    private void TpenPersalinanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenPersalinanKeyPressed
        Valid.pindah(evt, TjnsPersalinan, Tpenyulit);
    }//GEN-LAST:event_TpenPersalinanKeyPressed

    private void TpenyulitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyulitKeyPressed
        Valid.pindah(evt, TpenPersalinan, cmbJK);
    }//GEN-LAST:event_TpenyulitKeyPressed

    private void TbrtLahirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbrtLahirKeyPressed
        Valid.pindah(evt, cmbJK, TkeadaanAnk);
    }//GEN-LAST:event_TbrtLahirKeyPressed

    private void TkeadaanAnkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeadaanAnkKeyPressed
        Valid.pindah(evt, TbrtLahir, BtnSimpanRiw);
    }//GEN-LAST:event_TkeadaanAnkKeyPressed

    private void BtnBatalRiwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalRiwActionPerformed
        TthnPartus.setText("");
        TthnPartus.requestFocus();
        TtmptPartus.setText("");
        TumurHamil.setText("");
        TjnsPersalinan.setText("");
        TpenPersalinan.setText("");
        Tpenyulit.setText("");
        cmbJK.setSelectedIndex(0);
        TbrtLahir.setText("");
        TkeadaanAnk.setText("");
        wktSimpan = "";
    }//GEN-LAST:event_BtnBatalRiwActionPerformed

    private void BtnSimpanRiwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanRiwActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (TthnPartus.getText().equals("")) {
            Valid.textKosong(TthnPartus, "Tahun Partus");
            TthnPartus.requestFocus();
        } else if (cmbJK.getSelectedIndex() == 0) {
            Valid.textKosong(cmbJK, "Jenis Kelamin");
            cmbJK.requestFocus();
        } else {
            tabModeRiwayat.addRow(new Object[]{
                TNoRw.getText(), 
                TthnPartus.getText(), 
                TtmptPartus.getText(),
                TumurHamil.getText(), 
                TjnsPersalinan.getText(),
                TpenPersalinan.getText(),
                Tpenyulit.getText(),
                cmbJK.getSelectedItem().toString(),
                TbrtLahir.getText(),
                TkeadaanAnk.getText(),
                Sequel.cariIsi("select now()")});
            BtnBatalRiwActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanRiwActionPerformed

    private void tbRiwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbRiwayatMouseClicked
        if (tabModeRiwayat.getRowCount() != 0) {
            try {
                getRiwayat();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbRiwayatMouseClicked

    private void tbRiwayatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbRiwayatKeyPressed
        if (tabModeRiwayat.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getRiwayat();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbRiwayatKeyPressed

    private void BtnHapusRiwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusRiwActionPerformed
        if (tabModeRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat kehamilan, persalinan & nifas masih kosong...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kehamilan pada tabel...!!!!");
            tbRiwayat.requestFocus();
        } else if (tbRiwayat.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kehamilan pada tabel...!!!!");
            tbRiwayat.requestFocus();
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data riwayat ini mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tabModeRiwayat.removeRow(tbRiwayat.getSelectedRow());
                BtnBatalRiwActionPerformed(null);
            }
        }
    }//GEN-LAST:event_BtnHapusRiwActionPerformed

    private void BtnGantiRiwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiRiwActionPerformed
        if (tabModeRiwayat.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data riwayat kehamilan, persalinan & nifas masih kosong...!!!!");
        } else if (wktSimpan.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kehamilan pada tabel...!!!!");
            tbRiwayat.requestFocus();
        } else if (tbRiwayat.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data riwayat kehamilan pada tabel...!!!!");
            tbRiwayat.requestFocus();
        } else {
            tabModeRiwayat.removeRow(tbRiwayat.getSelectedRow());            
            tabModeRiwayat.addRow(new Object[]{
                TNoRw.getText(), 
                TthnPartus.getText(), 
                TtmptPartus.getText(),
                TumurHamil.getText(), 
                TjnsPersalinan.getText(),
                TpenPersalinan.getText(),
                Tpenyulit.getText(),
                cmbJK.getSelectedItem().toString(),
                TbrtLahir.getText(),
                TkeadaanAnk.getText(),
                wktSimpan});
            BtnBatalRiwActionPerformed(null);
        }
    }//GEN-LAST:event_BtnGantiRiwActionPerformed

    private void TusiaKawinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TusiaKawinKeyPressed
        Valid.pindah(evt, cmbSuami, cmbUsiaKawin);
    }//GEN-LAST:event_TusiaKawinKeyPressed

    private void TklgTerdekatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TklgTerdekatKeyPressed
        Valid.pindah(evt, cmbUsiaKawin, Thubungan);
    }//GEN-LAST:event_TklgTerdekatKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        Valid.pindah(evt, TklgTerdekat, cmbTinggal);
    }//GEN-LAST:event_ThubunganKeyPressed

    private void TtglDenganLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtglDenganLainKeyPressed
        Valid.pindah(evt, cmbTinggal, cmbCuriga);
    }//GEN-LAST:event_TtglDenganLainKeyPressed

    private void TkegiatanIbadahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkegiatanIbadahKeyPressed
        Valid.pindah(evt, cmbCuriga, cmbEmosi);
    }//GEN-LAST:event_TkegiatanIbadahKeyPressed

    private void cmbTinggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTinggalActionPerformed
        if (cmbTinggal.getSelectedIndex() == 5) {
            TtglDenganLain.setEnabled(true);
            TtglDenganLain.setText("");
            TtglDenganLain.requestFocus();
        } else {
            TtglDenganLain.setEnabled(false);
            TtglDenganLain.setText("");
        }
    }//GEN-LAST:event_cmbTinggalActionPerformed

    private void TsttsEkonomiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsttsEkonomiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TsttsEkonomiKeyPressed

    private void cmbEkonomiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEkonomiActionPerformed
        if (cmbEkonomi.getSelectedIndex() == 4) {
            TsttsEkonomi.setEnabled(true);
            TsttsEkonomi.setText("");
            TsttsEkonomi.requestFocus();
        } else {
            TsttsEkonomi.setEnabled(false);
            TsttsEkonomi.setText("");
        }
    }//GEN-LAST:event_cmbEkonomiActionPerformed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
            Tlokasi.setText("");
            Tlokasi.requestFocus();
        } else {
            Tlokasi.setEnabled(false);
            Tlokasi.setText("");
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TlokasiKeyPressed

    private void cmbProvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvoActionPerformed
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
            Tprovo.setText("");
            Tprovo.requestFocus();
        } else {
            Tprovo.setEnabled(false);
            Tprovo.setText("");
        }
    }//GEN-LAST:event_cmbProvoActionPerformed

    private void TprovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprovoKeyPressed
        Valid.pindah(evt, cmbProvo, cmbQuality);
    }//GEN-LAST:event_TprovoKeyPressed

    private void cmbQualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbQualityActionPerformed
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
            Tquality.setText("");
            Tquality.requestFocus();
        } else {
            Tquality.setEnabled(false);
            Tquality.setText("");
        }
    }//GEN-LAST:event_cmbQualityActionPerformed

    private void TqualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqualityKeyPressed
        Valid.pindah(evt, cmbQuality, cmbRadia);
    }//GEN-LAST:event_TqualityKeyPressed

    private void cmbGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi1ActionPerformed
        cmbGizi1Ya.setSelectedIndex(0);
        skorYaGZ1.setText("0");

        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGZ1.setText("0");
            cmbGizi1Ya.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGZ1.setText("2");
            cmbGizi1Ya.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGZ1.setText("0");
            cmbGizi1Ya.setEnabled(true);
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbGizi1ActionPerformed

    private void cmbGizi1YaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi1YaActionPerformed
        if (cmbGizi1Ya.getSelectedIndex() == 0) {
            skorYaGZ1.setText("0");
        } else if (cmbGizi1Ya.getSelectedIndex() == 1) {
            skorYaGZ1.setText("1");
        } else if (cmbGizi1Ya.getSelectedIndex() == 2) {
            skorYaGZ1.setText("2");
        } else if (cmbGizi1Ya.getSelectedIndex() == 3) {
            skorYaGZ1.setText("3");
        } else if (cmbGizi1Ya.getSelectedIndex() == 4) {
            skorYaGZ1.setText("4");
        } else if (cmbGizi1Ya.getSelectedIndex() == 5) {
            skorYaGZ1.setText("2");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbGizi1YaActionPerformed

    private void cmbGizi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi2ActionPerformed
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGZ2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGZ2.setText("1");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbGizi2ActionPerformed

    private void chkAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiObatActionPerformed
        if (chkAlergiObat.isSelected() == true) {
            reaksiAlergiObat.setEnabled(true);
            reaksiAlergiObat.setText("");
            reaksiAlergiObat.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiObatActionPerformed

    private void chkAlergiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiMakananActionPerformed
        if (chkAlergiMakanan.isSelected() == true) {
            reaksiAlergiMakanan.setEnabled(true);
            reaksiAlergiMakanan.setText("");
            reaksiAlergiMakanan.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiMakananActionPerformed

    private void chkAlergiLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiLainActionPerformed
        if (chkAlergiLain.isSelected() == true) {
            reaksiAlergiLain.setEnabled(true);
            reaksiAlergiLain.setText("");
            reaksiAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiLainActionPerformed

    private void tbFaktorResikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaktorResikoMouseClicked
        if (tabModeResiko.getRowCount() != 0) {
            try {
                hitungSkorRJ();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaktorResikoMouseClicked

    private void tbFaktorResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyPressed
        if(tabModeResiko.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
                TCariResiko.setText("");
                TCariResiko.requestFocus();
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyPressed

    private void tbFaktorResikoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyReleased
        if(tabModeResiko.getRowCount()!=0){
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

    private void TalatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatBantuKeyPressed
        Valid.pindah(evt, TalatBantu, Tprothesis);
    }//GEN-LAST:event_TalatBantuKeyPressed

    private void TprothesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprothesisKeyPressed
        Valid.pindah(evt, TalatBantu, TcacatTubuh);
    }//GEN-LAST:event_TprothesisKeyPressed

    private void TcacatTubuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcacatTubuhKeyPressed
        Valid.pindah(evt, Tprothesis, cmbAdl);
    }//GEN-LAST:event_TcacatTubuhKeyPressed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        pilihan = 1;
        akses.setform("RMAsesmenKebidananRalan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void cmbHambatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHambatanActionPerformed
        if (cmbHambatan.getSelectedIndex() == 1) {
            cmbHambatanYa.setEnabled(true);
            cmbHambatanYa.setSelectedIndex(0);
            cmbHambatanYa.requestFocus();
        } else {
            cmbHambatanYa.setEnabled(false);
            cmbHambatanYa.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbHambatanActionPerformed

    private void ThambatanLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThambatanLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThambatanLainKeyPressed

    private void cmbHambatanYaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHambatanYaActionPerformed
        if (cmbHambatanYa.getSelectedIndex() == 8) {
            ThambatanLain.setText("");
            ThambatanLain.setEnabled(true);
            ThambatanLain.requestFocus();
        } else {
            ThambatanLain.setText("");
            ThambatanLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbHambatanYaActionPerformed

    private void cmbPenerjemahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPenerjemahActionPerformed
        if (cmbPenerjemah.getSelectedIndex() == 1) {
            Tsebutkan.setEnabled(true);
            Tsebutkan.setText("");
            Tsebutkan.requestFocus();
        } else {
            Tsebutkan.setEnabled(false);
            Tsebutkan.setText("");
        }
    }//GEN-LAST:event_cmbPenerjemahActionPerformed

    private void TsebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsebutkanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TsebutkanKeyPressed

    private void chkTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTindakanActionPerformed
        if (chkTindakan.isSelected() == true) {
            Ttindakan.setEnabled(true);
            Ttindakan.setText("");
            Ttindakan.requestFocus();
        } else {
            Ttindakan.setEnabled(false);
            Ttindakan.setText("");
        }
    }//GEN-LAST:event_chkTindakanActionPerformed

    private void chkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainActionPerformed
        if (chkLain.isSelected() == true) {
            TLainSebutkan.setEnabled(true);
            TLainSebutkan.setText("");
            TLainSebutkan.requestFocus();
        } else {
            TLainSebutkan.setEnabled(false);
            TLainSebutkan.setText("");
        }
    }//GEN-LAST:event_chkLainActionPerformed

    private void chkKlgPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKlgPasienActionPerformed
        if (chkKlgPasien.isSelected() == true) {
            TnmKlgPasien.setText("");
            TnmKlgPasien.setEnabled(true);
            TnmKlgPasien.requestFocus();
        } else {
            TnmKlgPasien.setText("");
            TnmKlgPasien.setEnabled(false);
        }
    }//GEN-LAST:event_chkKlgPasienActionPerformed

    private void chkTdkBeriEdukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTdkBeriEdukasiActionPerformed
        if (chkTdkBeriEdukasi.isSelected() == true) {
            TtdkBeriEdukasi.setText("");
            TtdkBeriEdukasi.setEnabled(true);
            TtdkBeriEdukasi.requestFocus();
        } else {
            TtdkBeriEdukasi.setText("");
            TtdkBeriEdukasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkTdkBeriEdukasiActionPerformed

    private void ChkJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamActionPerformed
        if (ChkJam.isSelected() == true) {
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

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMAsesmenKebidananRalan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnBidanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidanActionPerformed
        pilihan = 2;
        akses.setform("RMAsesmenKebidananRalan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBidanActionPerformed

    private void TrujukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrujukanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrujukanKeyPressed

    private void TpkmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpkmKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpkmKeyPressed

    private void TrsLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrsLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TrsLainKeyPressed

    private void cmbCaraDatangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCaraDatangActionPerformed
        Trujukan.setText("");
        Tpkm.setText("");
        TrsLain.setText("");
        
        if (cmbCaraDatang.getSelectedIndex() == 2) {            
            Trujukan.setEnabled(true);
            Tpkm.setEnabled(false);
            TrsLain.setEnabled(false);
            Trujukan.requestFocus();
        } else if (cmbCaraDatang.getSelectedIndex() == 3) {
            Trujukan.setEnabled(false);
            Tpkm.setEnabled(true);
            TrsLain.setEnabled(false);
            Tpkm.requestFocus();
        } else if (cmbCaraDatang.getSelectedIndex() == 5) {
            Trujukan.setEnabled(false);
            Tpkm.setEnabled(false);
            TrsLain.setEnabled(true);
            TrsLain.requestFocus();
        } else {
            Trujukan.setEnabled(false);
            Tpkm.setEnabled(false);
            TrsLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbCaraDatangActionPerformed

    private void ThamilKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThamilKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ThamilKeyPressed

    private void TdenganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdenganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TdenganKeyPressed

    private void cmbPerutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerutActionPerformed
        if (cmbPerut.getSelectedIndex() == 1) {
            Ttgl_perut.setEnabled(true);
            Ttgl_perut.setDate(new Date());
            Ttgl_perut.requestFocus();
        } else {
            Ttgl_perut.setEnabled(false);
            Ttgl_perut.setDate(new Date());
        }
    }//GEN-LAST:event_cmbPerutActionPerformed

    private void cmbLendirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLendirActionPerformed
        if (cmbLendir.getSelectedIndex() == 1) {
            Ttgl_lendir.setEnabled(true);
            Ttgl_lendir.setDate(new Date());
            Ttgl_lendir.requestFocus();
        } else {
            Ttgl_lendir.setEnabled(false);
            Ttgl_lendir.setDate(new Date());
        }
    }//GEN-LAST:event_cmbLendirActionPerformed

    private void cmbDarahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDarahActionPerformed
        if (cmbDarah.getSelectedIndex() == 1) {
            Ttgl_darah.setEnabled(true);
            Ttgl_darah.setDate(new Date());
            Ttgl_darah.requestFocus();
        } else {
            Ttgl_darah.setEnabled(false);
            Ttgl_darah.setDate(new Date());
        }
    }//GEN-LAST:event_cmbDarahActionPerformed

    private void cmbAirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAirActionPerformed
        if (cmbAir.getSelectedIndex() == 1) {
            Ttgl_air.setEnabled(true);
            Ttgl_air.setDate(new Date());
            cmbJenisAir.setEnabled(true);
            cmbJenisAir.setSelectedIndex(0);
            Ttgl_air.requestFocus();
        } else {
            Ttgl_air.setEnabled(false);
            Ttgl_air.setDate(new Date());
            cmbJenisAir.setEnabled(false);
            cmbJenisAir.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbAirActionPerformed

    private void cmbPusingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPusingActionPerformed
        if (cmbPusing.getSelectedIndex() == 1) {
            Ttgl_pusing.setEnabled(true);
            Ttgl_pusing.setDate(new Date());
            Ttgl_pusing.requestFocus();
        } else {
            Ttgl_pusing.setEnabled(false);
            Ttgl_pusing.setDate(new Date());
        }
    }//GEN-LAST:event_cmbPusingActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenKebidananRalan dialog = new RMAsesmenKebidananRalan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlamat;
    private widget.Button BtnAll;
    private widget.Button BtnAllResiko;
    private widget.Button BtnBatal;
    private widget.Button BtnBatalRiw;
    private widget.Button BtnBidan;
    private widget.Button BtnCari;
    private widget.Button BtnCariResiko;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnGantiRiw;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusRiw;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpanRiw;
    public widget.CekBox ChkJam;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll8;
    private widget.TextBox TCari;
    private widget.TextBox TCariResiko;
    private widget.TextBox TLainSebutkan;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Ta;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox TagamaPx;
    private widget.TextBox TalamatPx;
    private widget.TextBox TalamatSuami;
    private widget.TextBox Talasan;
    private widget.TextBox TalatBantu;
    private widget.TextBox TbrtLahir;
    private widget.TextBox TcacatTubuh;
    private widget.TextBox Tdengan;
    private widget.TextBox Tg;
    private widget.Tanggal TglAsesmen;
    private widget.TextBox TglLahir;
    private widget.TextBox ThambatanLain;
    private widget.TextBox Thamil;
    private widget.TextBox Thubungan;
    private widget.TextBox TjnsPersalinan;
    private widget.TextBox TkeadaanAnk;
    private widget.TextBox TkegiatanIbadah;
    private widget.TextBox Tkesadaran;
    private widget.TextBox TklgTerdekat;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmBidan;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmKlgPasien;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TnmSuami;
    private widget.TextBox TotSkorGZ;
    private widget.TextBox TotSkorRJ;
    private widget.TextBox Tp;
    private widget.TextBox TpekerjaanPx;
    private widget.TextBox TpekerjaanSuami;
    private widget.TextBox TpenPersalinan;
    private widget.TextBox Tpenyulit;
    private widget.TextBox Tpkm;
    private widget.TextBox Tprothesis;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox Trespi;
    private widget.TextBox TrsLain;
    private widget.TextBox Trujukan;
    private widget.TextBox Tsebutkan;
    private widget.TextBox Tspo2;
    private widget.TextBox TsttsEkonomi;
    private widget.TextBox TsttsKawin;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttd;
    private widget.TextBox TtdkBeriEdukasi;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtglDenganLain;
    private widget.Tanggal Ttgl_air;
    private widget.Tanggal Ttgl_darah;
    private widget.Tanggal Ttgl_lendir;
    private widget.Tanggal Ttgl_perut;
    private widget.Tanggal Ttgl_pusing;
    private widget.TextBox TthnPartus;
    private widget.TextBox Ttindakan;
    private widget.TextBox TtmptPartus;
    private widget.TextBox TumurHamil;
    private widget.TextBox TumurPx;
    private widget.TextBox TumurSuami;
    private widget.TextBox TusiaKawin;
    public widget.CekBox chkAlergiLain;
    public widget.CekBox chkAlergiMakanan;
    public widget.CekBox chkAlergiObat;
    public widget.CekBox chkDiagnosa;
    public widget.CekBox chkDiet;
    public widget.CekBox chkGelang;
    public widget.CekBox chkIden1;
    public widget.CekBox chkIden10;
    public widget.CekBox chkIden2;
    public widget.CekBox chkIden3;
    public widget.CekBox chkIden4;
    public widget.CekBox chkIden5;
    public widget.CekBox chkIden6;
    public widget.CekBox chkIden7;
    public widget.CekBox chkIden8;
    public widget.CekBox chkIden9;
    public widget.CekBox chkKlgPasien;
    public widget.CekBox chkLain;
    public widget.CekBox chkManajemen;
    public widget.CekBox chkObat;
    public widget.CekBox chkPasien;
    public widget.CekBox chkRehab;
    public widget.CekBox chkTdkBeriEdukasi;
    public widget.CekBox chkTindakan;
    private widget.ComboBox cmbAdl;
    private widget.ComboBox cmbAgama;
    private widget.ComboBox cmbAir;
    private widget.ComboBox cmbBahasa;
    private widget.ComboBox cmbCaraDatang;
    private widget.ComboBox cmbCuriga;
    private widget.ComboBox cmbDP;
    private widget.ComboBox cmbDarah;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbEkonomi;
    private widget.ComboBox cmbEmosi;
    private widget.ComboBox cmbGizi1;
    private widget.ComboBox cmbGizi1Ya;
    private widget.ComboBox cmbGizi2;
    private widget.ComboBox cmbHambatan;
    private widget.ComboBox cmbHambatanYa;
    private widget.ComboBox cmbIstri;
    private widget.ComboBox cmbJK;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbJenisAir;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbLendir;
    private widget.ComboBox cmbMPP;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPenerjemah;
    private widget.ComboBox cmbPerut;
    private widget.ComboBox cmbPoli;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbPusing;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbRiwayat;
    private widget.ComboBox cmbRiwayat3bln;
    private widget.ComboBox cmbSevere;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbSuami;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbTinggal;
    private widget.ComboBox cmbUsiaKawin;
    private widget.ComboBox cmbdiberitahukan;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel101;
    private widget.Label jLabel102;
    private widget.Label jLabel103;
    private widget.Label jLabel104;
    private widget.Label jLabel105;
    private widget.Label jLabel106;
    private widget.Label jLabel107;
    private widget.Label jLabel108;
    private widget.Label jLabel109;
    private widget.Label jLabel11;
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
    private widget.Label jLabel12;
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel124;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel13;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
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
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel49;
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
    private widget.TextArea kesimpulanResikoJatuh;
    private widget.Label label11;
    private widget.Label label12;
    private widget.Label label13;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.TextBox reaksiAlergiLain;
    private widget.TextBox reaksiAlergiMakanan;
    private widget.TextBox reaksiAlergiObat;
    private widget.ScrollPane scrollInput;
    private widget.TextBox skorGZ1;
    private widget.TextBox skorGZ2;
    private widget.TextBox skorYaGZ1;
    private widget.Table tbAsesmen;
    private widget.Table tbFaktorResiko;
    private widget.Table tbRiwayat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps = koneksi.prepareStatement("select p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pl.nm_poli, "
                    + "date_format(ak.tgl_asesmen,'%d-%m-%Y %H:%i') tglases, concat(rp.umurdaftar,' ',rp.sttsumur) umurPx, ifnull(p.pekerjaan,'-') pekerjaanPx, "
                    + "p.agama, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatPx, p.stts_nikah, pg1.nama nmperawat, pg2.nama nmbidan, "
                    + "pg3.nama nmdokter, ak.* from reg_periksa rp inner join asesmen_kebidanan_ralan ak on ak.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join poliklinik pl on pl.kd_poli=ak.kd_poli "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab inner join pegawai pg1 on pg1.nik=ak.nip_perawat "
                    + "inner join pegawai pg2 on pg2.nik=ak.nip_bidan inner join pegawai pg3 on pg1.nik=ak.nip_dokter where "
                    + "date(ak.tgl_asesmen) between ? and ? and ak.no_rawat like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and p.nm_pasien like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and pl.nm_poli like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and concat(rp.umurdaftar,' ',rp.sttsumur) like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and p.pekerjaan like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and p.agama like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and p.stts_nikah like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and pg1.nama like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and pg2.nama like ? or "
                    + "date(ak.tgl_asesmen) between ? and ? and pg3.nama like ? order by ak.tgl_asesmen desc");            
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
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText().trim() + "%");
                ps.setString(31, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(32, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(33, "%" + TCari.getText().trim() + "%");
                ps.setString(34, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(35, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(36, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("nm_poli"),
                        rs.getString("tglases"),
                        rs.getString("umurPx"),
                        rs.getString("pekerjaanPx"),
                        rs.getString("agama"),
                        rs.getString("alamatPx"),
                        rs.getString("stts_nikah"),
                        rs.getString("nmperawat"),
                        rs.getString("nmbidan"),
                        rs.getString("nmdokter"),                        
                        rs.getString("kd_poli"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("nm_suami"),
                        rs.getString("umur_suami"),
                        rs.getString("pekerjaan_suami"),
                        rs.getString("alamat_suami"),
                        rs.getString("agama_suami"),
                        rs.getString("alasan_masuk_rs"),                        
                        rs.getString("tensi"),
                        rs.getString("nadi"),
                        rs.getString("respirasi"),
                        rs.getString("suhu"),
                        rs.getString("kesadaran"),
                        rs.getString("spo2"),
                        rs.getString("g"),
                        rs.getString("p"),
                        rs.getString("a"),
                        rs.getString("jml_kawin_istri"),
                        rs.getString("jml_kawin_suami"),
                        rs.getString("usia_perkawinan"),
                        rs.getString("id_usia_kawin"),
                        rs.getString("keluarga_terdekat"),
                        rs.getString("hub_keluarga_terdekat"),
                        rs.getString("tinggal_dengan"),
                        rs.getString("tinggal_dengan_lain"),
                        rs.getString("curiga_penganiayaan"),
                        rs.getString("kegiatan_ibadah"),
                        rs.getString("status_emosional"),
                        rs.getString("status_ekonomi"),
                        rs.getString("status_ekonomi_lain"),
                        rs.getString("nyeri"),
                        rs.getString("nyeri_ya_lokasi"),
                        rs.getString("jenis"),
                        rs.getString("provocation"),
                        rs.getString("provocation_lain"),
                        rs.getString("quality"),
                        rs.getString("quality_lain"),
                        rs.getString("radiation"),
                        rs.getString("severity"),
                        rs.getString("time"),
                        rs.getString("time_lama"),
                        rs.getString("skala_nyeri"),
                        rs.getString("skrining_gizi1"),
                        rs.getString("skrining_gizi1_ya"),
                        rs.getString("skrining_gizi2"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("alergi_obat"),                        
                        rs.getString("reaksi_alergi_obat"),
                        rs.getString("alergi_makanan"),
                        rs.getString("reaksi_alergi_makanan"),
                        rs.getString("alergi_lainnya"),
                        rs.getString("reaksi_alergi_lainnya"),
                        rs.getString("gelang_tanda"),
                        rs.getString("alergi_diberitahukan"),
                        rs.getString("alat_bantu"),                        
                        rs.getString("prothesis"),
                        rs.getString("cacat_tubuh"),
                        rs.getString("adl"),
                        rs.getString("riw_jatuh_3bulan"),
                        rs.getString("nip_perawat"),
                        rs.getString("hambatan_pembelajaran"),
                        rs.getString("hambatan_ya"),
                        rs.getString("hambatan_ya_lainya"),
                        rs.getString("penerjemah"),
                        rs.getString("penerjemah_ya"),
                        rs.getString("bahasa_isyarat"),
                        rs.getString("diagnosa_menejemen_penyakit"),
                        rs.getString("tindakan_keperawatan"),
                        rs.getString("ket_tindakan_keperawatan"),
                        rs.getString("obat_obatan"),
                        rs.getString("rehabilitasi"),
                        rs.getString("diet_nutrisi"),
                        rs.getString("manajemen_nyeri"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lain_lain"),
                        rs.getString("edukasi_pasien"),
                        rs.getString("edukasi_keluarga_pasien"),
                        rs.getString("edukasi_nm_keluarga_pasien"),
                        rs.getString("tidak_memberi_edukasi"),
                        rs.getString("ket_tidak_memberi_edukasi"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("cek_jam"),
                        rs.getString("nip_dokter"),
                        rs.getString("identifikasi1"),
                        rs.getString("identifikasi2"),
                        rs.getString("identifikasi3"),
                        rs.getString("identifikasi4"),
                        rs.getString("identifikasi5"),
                        rs.getString("identifikasi6"),
                        rs.getString("identifikasi7"),
                        rs.getString("identifikasi8"),
                        rs.getString("identifikasi9"),
                        rs.getString("identifikasi10"),
                        rs.getString("manajer_pelayanan"),
                        rs.getString("discharge_planing"),
                        rs.getString("nip_bidan"),
                        rs.getString("cara_datang"),
                        rs.getString("ket_rujukan"),
                        rs.getString("ket_pkm"),
                        rs.getString("ket_rs_lain"),
                        rs.getString("hamil"),
                        rs.getString("dengan"),
                        rs.getString("perut_mules"),
                        rs.getString("tgl_perut"),
                        rs.getString("keluar_lendir"),
                        rs.getString("tgl_lendir"),
                        rs.getString("darah_encer"),
                        rs.getString("tgl_darah"),
                        rs.getString("keluar_air"),
                        rs.getString("tgl_air"),
                        rs.getString("jenis_keluar_air"),
                        rs.getString("pusing"),
                        rs.getString("tgl_pusing")
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
        TglAsesmen.setDate(new Date());
        TnmSuami.setText("");
        TumurSuami.setText("");
        TpekerjaanSuami.setText("");
        TalamatSuami.setText("");
        cmbAgama.setSelectedIndex(0);
        cmbPoli.setSelectedIndex(0);
        Talasan.setText("");
        Ttd.setText("");
        Tnadi.setText("");
        Trespi.setText("");
        Tsuhu.setText("");
        Tkesadaran.setText("");
        Tspo2.setText("");
        Tg.setText("");
        Tp.setText("");
        Ta.setText("");
        
        TthnPartus.setText("");
        TtmptPartus.setText("");
        TumurHamil.setText("");
        TjnsPersalinan.setText("");
        TpenPersalinan.setText("");
        Tpenyulit.setText("");
        cmbJK.setSelectedIndex(0);
        TbrtLahir.setText("");
        TkeadaanAnk.setText("");
        Valid.tabelKosong(tabModeRiwayat);
        TsttsKawin.setText("");
        cmbIstri.setSelectedIndex(0);
        cmbSuami.setSelectedIndex(0);
        TusiaKawin.setText("");
        cmbUsiaKawin.setSelectedIndex(0);
        TklgTerdekat.setText("");
        Thubungan.setText("");
        cmbTinggal.setSelectedIndex(0);
        TtglDenganLain.setText("");
        TtglDenganLain.setEnabled(false);
        cmbCuriga.setSelectedIndex(0);
        TkegiatanIbadah.setText("");
        cmbEmosi.setSelectedIndex(0);
        cmbEkonomi.setSelectedIndex(0);
        TsttsEkonomi.setText("");
        TsttsEkonomi.setEnabled(false);
        cmbNyeri.setSelectedIndex(0);
        Tlokasi.setText("");
        Tlokasi.setEnabled(false);
        cmbJenis.setSelectedIndex(0);
        cmbProvo.setSelectedIndex(0);
        Tprovo.setText("");
        Tprovo.setEnabled(false);
        cmbQuality.setSelectedIndex(0);
        Tquality.setText("");
        Tquality.setEnabled(false);
        cmbRadia.setSelectedIndex(0);
        cmbSevere.setSelectedIndex(0);
        cmbTime.setSelectedIndex(0);
        cmbLama.setSelectedIndex(0);
        cmbSkala.setSelectedIndex(0);
        cmbGizi1.setSelectedIndex(0);
        cmbGizi1Ya.setSelectedIndex(0);
        skorGZ1.setText("0");
        skorYaGZ1.setText("0");
        cmbGizi2.setSelectedIndex(0);
        skorGZ2.setText("0");
        TotSkorGZ.setText("0");
        kesimpulanGizi.setText("");
        cmbRiwayat.setSelectedIndex(0);
        chkAlergiObat.setSelected(false);
        chkAlergiMakanan.setSelected(false);
        chkAlergiLain.setSelected(false);
        chkGelang.setSelected(false);
        reaksiAlergiObat.setText("");
        reaksiAlergiMakanan.setText("");
        reaksiAlergiLain.setText("");
        reaksiAlergiObat.setEnabled(false);
        reaksiAlergiMakanan.setEnabled(false);
        reaksiAlergiLain.setEnabled(false);
        cmbdiberitahukan.setSelectedIndex(0);
        TotSkorRJ.setText("0");
        kesimpulanResikoJatuh.setText("");
        TCariResiko.setText("");
        TalatBantu.setText("");
        Tprothesis.setText("");
        TcacatTubuh.setText("");
        cmbAdl.setSelectedIndex(0);
        cmbRiwayat3bln.setSelectedIndex(0);
        nipPerawat = "";
        TnmPerawat.setText("");
        cmbHambatan.setSelectedIndex(0);
        cmbHambatanYa.setSelectedIndex(0);
        cmbHambatanYa.setEnabled(false);
        ThambatanLain.setText("");
        ThambatanLain.setEnabled(false);
        cmbPenerjemah.setSelectedIndex(0);
        Tsebutkan.setText("");
        Tsebutkan.setEnabled(false);
        cmbBahasa.setSelectedIndex(0);
        chkDiagnosa.setSelected(false);
        chkTindakan.setSelected(false);
        Ttindakan.setText("");
        Ttindakan.setEnabled(false);
        chkObat.setSelected(false);
        chkRehab.setSelected(false);
        chkDiet.setSelected(false);
        chkManajemen.setSelected(false);
        chkLain.setSelected(false);
        TLainSebutkan.setText("");
        TLainSebutkan.setEnabled(false);
        chkPasien.setSelected(false);
        chkKlgPasien.setSelected(false);
        TnmKlgPasien.setText("");
        TnmKlgPasien.setEnabled(false);
        chkTdkBeriEdukasi.setSelected(false);
        TtdkBeriEdukasi.setText("");
        TtdkBeriEdukasi.setEnabled(false);
        Ttgl.setDate(new Date());
        ChkJam.setSelected(false);        
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);
        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
        nipDokter = "";
        TnmDokter.setText("");
        chkIden1.setSelected(false);        
        chkIden2.setSelected(false);
        chkIden3.setSelected(false);
        chkIden4.setSelected(false);
        chkIden5.setSelected(false);
        chkIden6.setSelected(false);
        chkIden7.setSelected(false);
        chkIden8.setSelected(false);
        chkIden9.setSelected(false);
        chkIden10.setSelected(false);
        cmbMPP.setSelectedIndex(0);
        cmbDP.setSelectedIndex(0);
        nipBidan = "";
        TnmBidan.setText("");
        cmbCaraDatang.setSelectedIndex(0);
        Trujukan.setText("");
        Trujukan.setEnabled(false);
        Tpkm.setText("");
        Tpkm.setEnabled(false);
        TrsLain.setText("");
        TrsLain.setEnabled(false);
        Thamil.setText("");
        Tdengan.setText("");
        cmbPerut.setSelectedIndex(0);
        Ttgl_perut.setEnabled(false);
        Ttgl_perut.setDate(new Date());
        cmbLendir.setSelectedIndex(0);
        Ttgl_lendir.setEnabled(false);
        Ttgl_lendir.setDate(new Date());
        cmbDarah.setSelectedIndex(0);
        Ttgl_darah.setEnabled(false);
        Ttgl_darah.setDate(new Date());
        cmbAir.setSelectedIndex(0);
        Ttgl_air.setEnabled(false);
        Ttgl_air.setDate(new Date());
        cmbJenisAir.setSelectedIndex(0);
        cmbJenisAir.setEnabled(false);
        cmbPusing.setSelectedIndex(0);
        Ttgl_pusing.setEnabled(false);
        Ttgl_pusing.setDate(new Date());
    } 

    private void getData() {
        if(tbAsesmen.getSelectedRow()!= -1){
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),0).toString()); 
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),2).toString()); 
            
            Valid.SetTgl2(TglAsesmen,tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(),8).toString());
        }
    }

    public void setData(String norwt) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        isPasien();
        tampil();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_kebidanan()); 
        
        if(akses.getjml2()>=1){
            BtnBidan.setEnabled(false);
            nipBidan = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan, nipBidan);
            if (TnmBidan.getText().equals("")) {
                nipBidan = "";
            }
        }            
    }

    private void hapus() {
        if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_kebidanan where no_rawat=?", 1, new String[]{
            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
        }) == true) {
            
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private void ganti() {
//        if (Sequel.mengedittf("penilaian_awal_keperawatan_kebidanan", "no_rawat=?", "no_rawat=?,tanggal=?,informasi=?,td=?,nadi=?,rr=?,suhu=?,gcs=?,bb=?,tb=?,lila=?,bmi=?,tfu=?,tbj=?,letak=?,presentasi=?,penurunan=?,his=?,kekuatan=?,lamanya=?,bjj=?,ket_bjj=?,portio=?,serviks=?,ketuban=?,hodge=?,inspekulo=?,ket_inspekulo=?,ctg=?,ket_ctg=?,usg=?,ket_usg=?,lab=?,ket_lab=?,"
//                + "lakmus=?,ket_lakmus=?,panggul=?,keluhan_utama=?,umur=?,lama=?,banyaknya=?,haid=?,siklus=?,ket_siklus=?,ket_siklus1=?,status=?,kali=?,usia1=?,ket1=?,usia2=?,ket2=?,usia3=?,ket3=?,hpht=?,usia_kehamilan=?,tp=?,imunisasi=?,ket_imunisasi=?,g=?,p=?,a=?,hidup=?,ginekologi=?,kebiasaan=?,ket_kebiasaan=?,kebiasaan1=?,ket_kebiasaan1=?,kebiasaan2=?,ket_kebiasaan2=?,"
//                + "kebiasaan3=?,kb=?,ket_kb=?,komplikasi=?,ket_komplikasi=?,berhenti=?,alasan=?,alat_bantu=?,ket_bantu=?,prothesa=?,ket_pro=?,adl=?,status_psiko=?,ket_psiko=?,hub_keluarga=?,tinggal_dengan=?,ket_tinggal=?,ekonomi=?,budaya=?,ket_budaya=?,edukasi=?,ket_edukasi=?,berjalan_a=?,berjalan_b=?,berjalan_c=?,hasil=?,lapor=?,ket_lapor=?,sg1=?,nilai1=?,sg2=?,nilai2=?,"
//                + "total_hasil=?,nyeri=?,provokes=?,ket_provokes=?,quality=?,ket_quality=?,lokasi=?,menyebar=?,skala_nyeri=?,durasi=?,nyeri_hilang=?,ket_nyeri=?,pada_dokter=?,ket_dokter=?,masalah=?,tindakan=?,nip=?,cacat_fisik=?", 120, new String[]{
//                    TNoRw.getText(), Valid.SetTgl(TglAsuhan.getSelectedItem() + "") + " " + TglAsuhan.getSelectedItem().toString().substring(11, 19), Informasi.getSelectedItem().toString(), TD.getText(), Nadi.getText(), RR.getText(), Suhu.getText(), GCS.getText(), BB.getText(), TB.getText(), LILA.getText(), BMI.getText(), TFU.getText(), TBJ.getText(), Letak.getText(),
//                    Presentasi.getText(), Penurunan.getText(), Kontraksi.getText(), Kekuatan.getText(), Lamanya.getText(), BJJ.getText(), KeteranganBJJ.getSelectedItem().toString(), Portio.getText(), PembukaanServiks.getText(), Ketuban.getText(), Hodge.getText(), Inspekulo.getSelectedItem().toString(), KeteranganInspekulo.getText(), CTG.getSelectedItem().toString(),
//                    KeteranganCTG.getText(), USG.getSelectedItem().toString(), KeteranganUSG.getText(), Laboratorium.getSelectedItem().toString(), KeteranganLaboratorium.getText(), Lakmus.getSelectedItem().toString(), KeteranganLakmus.getText(), PemeriksaanPanggul.getSelectedItem().toString(), KeluhanUtama.getText(), Umur.getText(), Lama.getText(),
//                    Banyaknya.getText(), HaidTerakhir.getText(), Siklus.getText(), KetSiklus.getSelectedItem().toString(), KetSiklus2.getSelectedItem().toString(), StatusMenikah.getSelectedItem().toString(), KaliMenikah.getText(), UsiaKawin1.getText(), StatusKawin1.getSelectedItem().toString(), UsiaKawin2.getText(), StatusKawin2.getSelectedItem().toString(),
//                    UsiaKawin3.getText(), StatusKawin3.getSelectedItem().toString(), Valid.SetTgl(HPHT.getSelectedItem() + ""), UsiaKehamilan.getText(), Valid.SetTgl(TP.getSelectedItem() + ""), RiwayatImunisasi.getSelectedItem().toString(), JumlahImunisasi.getText(), G.getText(), P.getText(), A.getText(), Hidup.getText(), RiwayatGenekologi.getSelectedItem().toString(),
//                    KebiasaanObat.getSelectedItem().toString(), KebiasaanObatDiminum.getText(), KebiasaanMerokok.getSelectedItem().toString(), KebiasaanJumlahRokok.getText(), KebiasaanAlkohol.getSelectedItem().toString(), KebiasaanJumlahAlkohol.getText(), KebiasaanNarkoba.getSelectedItem().toString(), RiwayatKB.getSelectedItem().toString(), LamanyaKB.getText(),
//                    KomplikasiKB.getSelectedItem().toString(), KeteranganKomplikasiKB.getText(), BerhentiKB.getText(), AlasanBerhentiKB.getText(), AlatBantu.getSelectedItem().toString(), KetBantu.getText(), Prothesa.getSelectedItem().toString(), KetProthesa.getText(), ADL.getSelectedItem().toString(), StatusPsiko.getSelectedItem().toString(), KetPsiko.getText(),
//                    HubunganKeluarga.getSelectedItem().toString(), TinggalDengan.getSelectedItem().toString(), KetTinggal.getText(), Ekonomi.getSelectedItem().toString(), StatusBudaya.getSelectedItem().toString(), KetBudaya.getText(), Edukasi.getSelectedItem().toString(), KetEdukasi.getText(), RJa1.getSelectedItem().toString(), RJa2.getSelectedItem().toString(),
//                    RJb.getSelectedItem().toString(), Hasil.getSelectedItem().toString(), Lapor.getSelectedItem().toString(), KetLapor.getText(), SG1.getSelectedItem().toString(), Nilai1.getSelectedItem().toString(), SG2.getSelectedItem().toString(), Nilai2.getSelectedItem().toString(), TotalHasil.getText(), Nyeri.getSelectedItem().toString(), Provokes.getSelectedItem().toString(),
//                    KetProvokes.getText(), Quality.getSelectedItem().toString(), KetQuality.getText(), Lokasi.getText(), Menyebar.getSelectedItem().toString(), SkalaNyeri.getSelectedItem().toString(), Durasi.getText(), NyeriHilang.getSelectedItem().toString(), KetNyeri.getText(), PadaDokter.getSelectedItem().toString(), KetDokter.getText(), Masalah.getText(),
//                    Tindakan.getText(), KdPetugas.getText(), CacatFisik.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
//                }) == true) {
//            tampil();
//            emptTeks();
//            TabRawat.setSelectedIndex(1);
    }
    
    private void getRiwayat() {
        wktSimpan = "";
        if (tbRiwayat.getSelectedRow() != -1) {
            TthnPartus.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
            TtmptPartus.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 2).toString());
            TumurHamil.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 3).toString());
            TjnsPersalinan.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
            TpenPersalinan.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 5).toString());
            Tpenyulit.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString());
            cmbJK.setSelectedItem(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString());
            TbrtLahir.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString());
            TkeadaanAnk.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 9).toString());
            wktSimpan = tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 10).toString();
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabModeRiwayat);
        try {
            ps1 = koneksi.prepareStatement("select * from riwayat_kehamilan_asesmen where no_rawat'" + TNoRw.getText() + "' order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabModeRiwayat.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("tahun_partus"),
                        rs1.getString("tempat_partus"),
                        rs1.getString("umur_hamil"),
                        rs1.getString("jns_persalinan"),
                        rs1.getString("penolong_persalinan"),
                        rs1.getString("penyulit"),
                        rs1.getString("jk"),
                        rs1.getString("bb"),
                        rs1.getString("keadaan_anak_skrng"),
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
    
    private void hitungSkorGZ() {
        int A, B, C, Total;
        A = Integer.parseInt(skorGZ1.getText());
        B = Integer.parseInt(skorYaGZ1.getText());
        C = Integer.parseInt(skorGZ2.getText());

        Total = 0;
        Total = A + B + C;
        TotSkorGZ.setText(Valid.SetAngka2(Total));
        if (Total == 0 || Total == 1) {
            kesimpulanGizi.setText("0 - 1 : tidak beresiko malnutrisi");
        } else if (Total >= 2) {
            kesimpulanGizi.setText(">= 2 : pasien beresiko malnutrisi, konsul ke Ahli Gizi");
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
        if (skor >= 45) {
            kesimpulanResikoJatuh.setText("Resiko : Tinggi, Skor >= 45");
        } else if (skor >= 25 && skor <= 44) {
            kesimpulanResikoJatuh.setText("Resiko : Sedang, Skor 25-44");
        } else if (skor >= 0 && skor <= 24) {
            kesimpulanResikoJatuh.setText("Resiko : Rendah, Skor 0-24");
        }
    }
    
    public void tampilFaktorResiko() {
        Valid.tabelKosong(tabModeResiko);
        try {
            ps2 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                    + "asesmen = 'kebidanan' and faktor_resiko like ? or "
                    + "asesmen = 'kebidanan' and skala like ? order by kode_resiko");
            try {
                ps2.setString(1, "%" + TCariResiko.getText().trim() + "%");
                ps2.setString(2, "%" + TCariResiko.getText().trim() + "%");
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabModeResiko.addRow(new Object[]{
                        false,
                        rs2.getString("kode_resiko"),
                        rs2.getString("asesmen"),
                        rs2.getString("faktor_resiko"),
                        rs2.getString("skala"),
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
    
    private void cekData() {
        if (chkAlergiObat.isSelected() == true) {
            alergiObat = "ya";
        } else {
            alergiObat = "tidak";
        }
        
        if (chkAlergiMakanan.isSelected() == true) {
            alergiMakan = "ya";
        } else {
            alergiMakan = "tidak";
        }
        
        if (chkAlergiLain.isSelected() == true) {
            alergiLain = "ya";
        } else {
            alergiLain = "tidak";
        }
        
        if (chkGelang.isSelected() == true) {
            gelang = "ya";
        } else {
            gelang = "tidak";
        }
        
        if (chkDiagnosa.isSelected() == true) {
            diagnosa = "ya";
        } else {
            diagnosa = "tidak";
        }
        
        if (chkTindakan.isSelected() == true) {
            tindakan = "ya";
        } else {
            tindakan = "tidak";
        }
        
        if (chkObat.isSelected() == true) {
            obat = "ya";
        } else {
            obat = "tidak";
        }
        
        if (chkRehab.isSelected() == true) {
            rehab = "ya";
        } else {
            rehab = "tidak";
        }
        
        if (chkDiet.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (chkManajemen.isSelected() == true) {
            menejemen = "ya";
        } else {
            menejemen = "tidak";
        }
        
        if (chkLain.isSelected() == true) {
            lainSebutkan = "ya";
        } else {
            lainSebutkan = "tidak";
        }
        
        if (chkPasien.isSelected() == true) {
            pasien = "ya";
        } else {
            pasien = "tidak";
        }
        
        if (chkKlgPasien.isSelected() == true) {
            klgPasien = "ya";
        } else {
            klgPasien = "tidak";
        }
        
        if (chkTdkBeriEdukasi.isSelected() == true) {
            tidakBeri = "ya";
        } else {
            tidakBeri = "tidak";
        }
        
        if (ChkJam.isSelected() == true) {
            jam = "ya";
        } else {
            jam = "tidak";
        }
        
        if (chkIden1.isSelected() == true) {
            iden1 = "ya";
        } else {
            iden1 = "tidak";
        }
        
        if (chkIden2.isSelected() == true) {
            iden2 = "ya";
        } else {
            iden2 = "tidak";
        }
        
        if (chkIden3.isSelected() == true) {
            iden3 = "ya";
        } else {
            iden3 = "tidak";
        }
        
        if (chkIden4.isSelected() == true) {
            iden4 = "ya";
        } else {
            iden4 = "tidak";
        }
        
        if (chkIden5.isSelected() == true) {
            iden5 = "ya";
        } else {
            iden5 = "tidak";
        }
        
        if (chkIden6.isSelected() == true) {
            iden6 = "ya";
        } else {
            iden6 = "tidak";
        }
        
        if (chkIden7.isSelected() == true) {
            iden7 = "ya";
        } else {
            iden7 = "tidak";
        }
        
        if (chkIden8.isSelected() == true) {
            iden8 = "ya";
        } else {
            iden8 = "tidak";
        }
        
        if (chkIden9.isSelected() == true) {
            iden9 = "ya";
        } else {
            iden9 = "tidak";
        }
        
        if (chkIden10.isSelected() == true) {
            iden10 = "ya";
        } else {
            iden10 = "tidak";
        }
    }
    
    private void dataCek() {
        if (alergiObat.equals("ya")) {
            chkAlergiObat.setSelected(true);
        } else {
            chkAlergiObat.setSelected(false);
        }
        
        if (alergiMakan.equals("ya")) {
            chkAlergiMakanan.setSelected(true);
        } else {
            chkAlergiMakanan.setSelected(false);
        }
        
        if (alergiLain.equals("ya")) {
            chkAlergiLain.setSelected(true);
        } else {
            chkAlergiLain.setSelected(false);
        }
        
        if (gelang.equals("ya")) {
            chkGelang.setSelected(true);
        } else {
            chkGelang.setSelected(false);
        }
        
        if (diagnosa.equals("ya")) {
            chkDiagnosa.setSelected(true);
        } else {
            chkDiagnosa.setSelected(false);
        }
        
        if (tindakan.equals("ya")) {
            chkTindakan.setSelected(true);
        } else {
            chkTindakan.setSelected(false);
        }

        if (obat.equals("ya")) {
            chkObat.setSelected(true);
        } else {
            chkObat.setSelected(false);
        }

        if (rehab.equals("ya")) {
            chkRehab.setSelected(true);
        } else {
            chkRehab.setSelected(false);
        }

        if (diet.equals("ya")) {
            chkDiet.setSelected(true);
        } else {
            chkDiet.setSelected(false);
        }
         
        if (menejemen.equals("ya")) {
            chkManajemen.setSelected(true);
        } else {
            chkManajemen.setSelected(false);
        }
        
        if (lainSebutkan.equals("ya")) {
            chkLain.setSelected(true);
        } else {
            chkLain.setSelected(false);
        }
            
        if (pasien.equals("ya")) {
            chkPasien.setSelected(true);
        } else {
            chkPasien.setSelected(false);
        }
            
        if (klgPasien.equals("ya")) {
            chkKlgPasien.setSelected(true);
        } else {
            chkKlgPasien.setSelected(false);
        }
            
        if (tidakBeri.equals("ya")) {
            chkTdkBeriEdukasi.setSelected(true);
        } else {
            chkTdkBeriEdukasi.setSelected(false);
        }
        
        if (jam.equals("ya")) {
            ChkJam.setSelected(true);
        } else {
            ChkJam.setSelected(false);
        }
            
        //identifikasi
        if (iden1.equals("ya")) {
            chkIden1.setSelected(true);
        } else {
            chkIden1.setSelected(false);
        }

        if (iden2.equals("ya")) {
            chkIden2.setSelected(true);
        } else {
            chkIden2.setSelected(false);
        }

        if (iden3.equals("ya")) {
            chkIden3.setSelected(true);
        } else {
            chkIden3.setSelected(false);
        }

        if (iden4.equals("ya")) {
            chkIden4.setSelected(true);
        } else {
            chkIden4.setSelected(false);
        }

        if (iden5.equals("ya")) {
            chkIden5.setSelected(true);
        } else {
            chkIden5.setSelected(false);
        }

        if (iden6.equals("ya")) {
            chkIden6.setSelected(true);
        } else {
            chkIden6.setSelected(false);
        }

        if (iden7.equals("ya")) {
            chkIden7.setSelected(true);
        } else {
            chkIden7.setSelected(false);
        }

        if (iden8.equals("ya")) {
            chkIden8.setSelected(true);
        } else {
            chkIden8.setSelected(false);
        }

        if (iden9.equals("ya")) {
            chkIden9.setSelected(true);
        } else {
            chkIden9.setSelected(false);
        }

        if (iden10.equals("ya")) {
            chkIden10.setSelected(true);
        } else {
            chkIden10.setSelected(false);
        }

        hitungSkorGZ();
        //faktor resiko
        try {
            Valid.tabelKosong(tabModeResiko);
            ps3 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                    + "INNER JOIN penilaian_resiko_jatuh_kebidanan pr ON pr.kode_resiko = m.kode_resiko "
                    + "WHERE m.asesmen = 'kebidanan' and pr.no_rawat=? ORDER BY pr.kode_resiko");
            try {
                ps3.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabModeResiko.addRow(new Object[]{
                        true,
                        rs3.getString("kode_resiko"),
                        rs3.getString("asesmen"),
                        rs3.getString("faktor_resiko"),
                        rs3.getString("skala"),
                        rs3.getString("skor")
                    });
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
        hitungSkorRJ();
    }
    
    private void isPasien() {
        try {
            ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, concat(rp.umurdaftar,' ',rp.sttsumur) umurPx, "
                    + "ifnull(p.pekerjaan,'-') pekerjaanPx, p.agama, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamatPx, "
                    + "if(p.keluarga='SUAMI',p.namakeluarga,'') nmSuami, if(p.keluarga='SUAMI',ifnull(p.umur_pj,''),'') umurSuami, "
                    + "if(p.keluarga='SUAMI',ifnull(p.pekerjaanpj,''),'') pekerjaanSuami, p.stts_nikah, rp.tgl_registrasi from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kl on kl.kd_kel=p.kd_kel "
                    + "inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat=?");
            try {
                ps4.setString(1, TNoRw.getText());
                rs4 = ps4.executeQuery();
                if (rs4.next()) {
                    TNoRM.setText(rs4.getString("no_rkm_medis"));
                    TPasien.setText(rs4.getString("nm_pasien"));
                    TglLahir.setText(rs4.getString("tgllahir"));
                    TumurPx.setText(rs4.getString("umurPx"));
                    TpekerjaanPx.setText(rs4.getString("pekerjaanPx"));
                    TagamaPx.setText(rs4.getString("agama"));
                    TalamatPx.setText(rs4.getString("alamatPx"));
                    TnmSuami.setText(rs4.getString("nmSuami"));
                    TumurSuami.setText(rs4.getString("umurSuami"));
                    TpekerjaanSuami.setText(rs4.getString("pekerjaanSuami"));
                    TsttsKawin.setText(rs4.getString("stts_nikah"));
                    DTPCari1.setDate(rs4.getDate("tgl_registrasi"));
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
    }
    
    private void stringBersih() {
        wktSimpan = "";
        kdpoli = "";
        nipPerawat = "";
        nipDokter = "";
        nipBidan = "";
        alergiObat = "";
        alergiMakan = "";
        alergiLain = "";
        gelang = "";
        diagnosa = "";
        tindakan = "";
        obat = "";
        rehab = "";
        diet = "";
        menejemen = "";
        lainSebutkan = "";
        pasien = "";
        klgPasien = "";
        tidakBeri = "";
        jam = "";
        iden1 = "";
        iden2 = "";
        iden3 = "";
        iden4 = "";
        iden5 = "";
        iden6 = "";
        iden7 = "";
        iden8 = "";
        iden9 = "";
        iden10 = "";
    }
}
