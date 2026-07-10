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

import static com.sun.org.glassfish.external.amx.AMXUtil.prop;
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
import java.net.InetAddress;
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
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.util.Properties;
import javax.swing.ImageIcon;
import keuangan.DlgKamar;

/**
 *
 * @author perpustakaan
 */
public final class RMPemberianInformasiEdukasi extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    public DlgKamar kamar;
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0, x = 0, pilihan = 0;
    private final Properties prop = new Properties();
    private String nipPemberi = "", nipPengedukasi = "", bahasa = "", pendengaran = "", masalahPenglihatan = "", hilangMemori = "", tidakAdaPartisipasi = "", 
            secaraFisiologi = "", tidakDitemukanHambatan = "", cemas = "", emosi = "", kognitif = "", motifasiBuruk = "", bahasaIndonesia = "", 
            bahasaDaerah = "", bahasaInggris = "", bahasaLainnya = "", prosesPenyakit = "", pengobatan = "", alatBantuMedis = "", lainLain = "",
            terapiObat = "", nutrisi = "";
    private String diagnosis = "", kondisiPasien = "", tindakan = "", tataCara = "", manfaat = "", namaOrang = "", kemungkinanAlternative = "",
            prognosis = "", kemungkinanTdkTerduga = "", kemungkinanBila = "", pendidikanKesehatan = "", hasilAsuhan = "", penanganan = "",
            perawatanLuka = "", alatAlat = "", informasiPasien = "", keamananPenggunaan = "", prosedurTindakan = "", farmakologiObat = "",
            farmakologiInjeksi = "", farmakologiSedasi = "", perawatanLatihan = "", distraksi = "", pengalihanPerhatian = "", caraCuci = "",
            etikaBatuk = "", caraBuang = "", tempatToileting = "", lainyaPerawatBidan = "", diet = "", konsulGiziRanap = "", konsulGiziRalan = "",
            hakDan = "", jamKonsultasi = "", informasiKejadian = "", audio = "", demonstrasi = "", lisan = "", tulisan = "", visual = "", metode = "",
            pasien = "", keluarga = "", lainPenerimaPnd = "", penerimaPnd = "", edukasiLat = "", positioning = "", latihanAktif = "", simulasi = "", namaDan = "",
            caraAturan = "", resikoEfek = "", penyimpananObat = "", idFilePenerimaEdukasi = "", idParameterTtd = "", usernya = "", pwdnya = "", URL = "",
            ruangrawat = "", kd_kamar = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPemberianInformasiEdukasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Edukasi", "Jam Edukasi", "Nama Petugas",
            "bahasa", "pendengaran", "masalah_penglihatan", "hilang_memori", "tidak_ada_partisipasi", "secara_fisiologi", "tidak_ditemukan_hambatan", 
            "cemas", "emosi", "kognitif", "motifasi_buruk", "bicara", "ket_kapan", "bahasa_indonesia", "indonesia", "bahasa_daerah", "ket_daerah",
            "bahasa_inggris", "inggris", "bahasa_lainnya", "ket_bahasa_lainnya", "penerjemah", "ket_penerjemah", "nilai_pasien", "kesediaan_menerima",
            "proses_penyakit", "pengobatan", "alat_bantu_medis", "lain_lain", "ket_lainlain", "terapi_obat", "nutrisi", "penggunaan_herbal", "vegetarian",
            "menolak_vaksinasi", "kepercayaan_terhadap", "puasa", "menolak_dilakukan", "menolak_pulang", "menolak_dilayani", "tidak_memakan",
            "lain_lain_identifikasi", "waktu_simpan", "nip_petugas", "tanggal", "jam"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPemberian.setModel(tabMode);
        tbPemberian.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPemberian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 55; i++) {
            TableColumn column = tbPemberian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbPemberian.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan", "Tgl. Penilaian", "Jam Penilaian", "Penerima Pnddkn.", "Metode", "Profesi", "Tingkat Pemahaman", "Evaluasi Respon", "Nama Petugas", "Penerima Edukasi",
            "diagnosis", "kondisi_pasien", "tindakan", "tata_cara", "manfaat", "nama_orang", "kemungkinan_alternative", "prognosis", "kemungkinan_tdk_terduga", "kemungkinan_bila", "pendidikan_kesehatan",
            "ket_pendidikan", "hasil_asuhan", "penanganan", "perawatan_luka", "alat_alat", "informasi_pasien", "keamanan_penggunaan", "prosedur_tindakan", "farmakologi_obat", "farmakologi_injeksi",
            "farmakologi_sedasi", "perawatan_latihan", "distraksi", "pengalihan_perhatian", "cara_cuci", "etika_batuk", "cara_buang", "tempat_toileting", "lainya_perawat_bidan", "ket_lainya_perawat_bidan", "diet",
            "konsul_gizi_ranap", "konsul_gizi_ralan", "ket_lainya_nutrisionis", "hak_dan", "jam_konsultasi", "informasi_kejadian", "ket_lainya_admisi", "edukasi_lain_lanjutan", "nip_petugas", "nm_penerima_edukasi",
            "waktu_simpan", "tanggal", "jam", "profesi", "profesi_lainya", "audio", "demontrasi", "lisan", "tulisan", "visual", "pasien", "keluarga", "lainPenerimaPnd", "edukasi_latihan", "positioning", "latihan_aktif", 
            "simulasi_bicara", "ket_lainya_fisioterapi", "nama_dan", "cara_aturan", "resiko_efek", "penyimpanan_obat", "ket_lainya_apoteker", "id_file_nm_penerima_edukasi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbPenilaian.setModel(tabMode1);
        tbPenilaian.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbPenilaian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 79; i++) {
            TableColumn column = tbPenilaian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(120);
            } else if (i == 9) {
                column.setPreferredWidth(180);
            } else if (i == 10) {
                column.setPreferredWidth(180);
            } else if (i == 11) {
                column.setPreferredWidth(220);
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
            }
        }
        tbPenilaian.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tprofesi.setDocument(new batasInput((int) 100).getKata(Tprofesi));
        TpendidikanKes.setDocument(new batasInput((int) 250).getKata(TpendidikanKes));
        TnmPenerima.setDocument(new batasInput((int) 200).getKata(TnmPenerima));
        Tkapan.setDocument(new batasInput((int) 200).getKata(Tkapan));
        Tdaerah.setDocument(new batasInput((int) 200).getKata(Tdaerah));
        TbhsLainya.setDocument(new batasInput((int) 200).getKata(TbhsLainya));
        Tperlu.setDocument(new batasInput((int) 200).getKata(Tperlu));
        TPotensialLain.setDocument(new batasInput((int) 200).getKata(TPotensialLain));
        TCari1.setDocument(new batasInput((int) 100).getKata(TCari1));
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari1.getText().length()>2){
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
                        nipPemberi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPtgsPemberi.requestFocus();
                    }
                } else {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPengedukasi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPtgsPengedukasi.requestFocus();
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
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
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            usernya = akses.getkode();
            pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                    + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
        }
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
        MnHapusTtd = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel99 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi5 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        TabEdukasi = new javax.swing.JTabbedPane();
        FormAsesmen = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel146 = new widget.Label();
        chkBahasa = new widget.CekBox();
        chkPendengaran = new widget.CekBox();
        chkMasalah = new widget.CekBox();
        chkHilangMemori = new widget.CekBox();
        chkTidakAda = new widget.CekBox();
        chkSecara = new widget.CekBox();
        chkTidakDitemukan = new widget.CekBox();
        chkCemas = new widget.CekBox();
        chkEmosi = new widget.CekBox();
        chkKognitif = new widget.CekBox();
        chkMotivasi = new widget.CekBox();
        jLabel147 = new widget.Label();
        jLabel148 = new widget.Label();
        cmbBicara = new widget.ComboBox();
        Tkapan = new widget.TextBox();
        jLabel149 = new widget.Label();
        chkBhsIndonesia = new widget.CekBox();
        cmbBhsIndo = new widget.ComboBox();
        chkDaerah = new widget.CekBox();
        Tdaerah = new widget.TextBox();
        chkBhsInggris = new widget.CekBox();
        cmbBhsInggris = new widget.ComboBox();
        chkBhsLainya = new widget.CekBox();
        TbhsLainya = new widget.TextBox();
        jLabel150 = new widget.Label();
        cmbPerlu = new widget.ComboBox();
        Tperlu = new widget.TextBox();
        jLabel151 = new widget.Label();
        Tpnd = new widget.TextBox();
        jLabel152 = new widget.Label();
        Tagama = new widget.TextBox();
        jLabel153 = new widget.Label();
        cmbNilaiPasien = new widget.ComboBox();
        jLabel154 = new widget.Label();
        cmbKesediaan = new widget.ComboBox();
        jLabel155 = new widget.Label();
        chkProses = new widget.CekBox();
        chkPengobatan = new widget.CekBox();
        chkAlatBantu = new widget.CekBox();
        chkTerapi = new widget.CekBox();
        chkNutrisi = new widget.CekBox();
        chkPotensialLain = new widget.CekBox();
        TPotensialLain = new widget.TextBox();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        jLabel158 = new widget.Label();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        jLabel161 = new widget.Label();
        jLabel162 = new widget.Label();
        jLabel163 = new widget.Label();
        jLabel164 = new widget.Label();
        jLabel165 = new widget.Label();
        jLabel166 = new widget.Label();
        cmbPenggunaanHerbal = new widget.ComboBox();
        cmbVegetarian = new widget.ComboBox();
        cmbMenolakVaksin = new widget.ComboBox();
        cmbKepercayaan = new widget.ComboBox();
        cmbPuasa = new widget.ComboBox();
        cmbMenolakDilakukan = new widget.ComboBox();
        cmbMenolakPulang = new widget.ComboBox();
        cmbMenolakDilayani = new widget.ComboBox();
        cmbTidakMemakan = new widget.ComboBox();
        cmbLainLain = new widget.ComboBox();
        jLabel167 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPtgsPemberi = new widget.Button();
        jLabel168 = new widget.Label();
        TtglPemberian = new widget.Tanggal();
        jLabel169 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        FormInput2 = new widget.PanelBiasa();
        Scroll1 = new widget.ScrollPane();
        tbPemberian = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();
        internalFrame4 = new widget.InternalFrame();
        ScrollTriase2 = new widget.ScrollPane();
        FormInput1 = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw1 = new widget.TextBox();
        TPasien1 = new widget.TextBox();
        TNoRM1 = new widget.TextBox();
        jLabel11 = new widget.Label();
        TrgRawat1 = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtglPenilaian = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel14 = new widget.Label();
        jLabel16 = new widget.Label();
        cmbProfesi = new widget.ComboBox();
        Tprofesi = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        chkDiagnosis = new widget.CekBox();
        chkKondisi = new widget.CekBox();
        chkTindakan = new widget.CekBox();
        chkTataCara = new widget.CekBox();
        chkManfaat = new widget.CekBox();
        chkNamaOrang = new widget.CekBox();
        chkKemungkinan = new widget.CekBox();
        chkPrognosis = new widget.CekBox();
        chkKemTdkTerduga = new widget.CekBox();
        chkKemBila = new widget.CekBox();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        chkPendidikanKes = new widget.CekBox();
        TpendidikanKes = new widget.TextBox();
        chkHasilAsuhan = new widget.CekBox();
        chkPenanganan = new widget.CekBox();
        chkPerawatanLuka = new widget.CekBox();
        chkAlatAlat = new widget.CekBox();
        chkInformasi = new widget.CekBox();
        chkKeamanan = new widget.CekBox();
        chkProsedur = new widget.CekBox();
        chkFarmaObat = new widget.CekBox();
        chkFarmaInjek = new widget.CekBox();
        chkFarmaSedasi = new widget.CekBox();
        chkPerawatanLatihan = new widget.CekBox();
        chkDistraksi = new widget.CekBox();
        chkPengalihan = new widget.CekBox();
        chkCaraCuci = new widget.CekBox();
        chkEtika = new widget.CekBox();
        chkCaraBuang = new widget.CekBox();
        chkTempat = new widget.CekBox();
        chkLainProPerawat = new widget.CekBox();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        chkDiet = new widget.CekBox();
        chkKonsulGiziRanap = new widget.CekBox();
        chkKonsulGiziRalan = new widget.CekBox();
        scrollPane14 = new widget.ScrollPane();
        TpndNutrisionisLain = new widget.TextArea();
        jLabel27 = new widget.Label();
        jLabel28 = new widget.Label();
        chkHak = new widget.CekBox();
        chkJam = new widget.CekBox();
        chkInfoKejadian = new widget.CekBox();
        scrollPane15 = new widget.ScrollPane();
        TpndAdmisiLain = new widget.TextArea();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        scrollPane16 = new widget.ScrollPane();
        TpndLainyaLain = new widget.TextArea();
        jLabel31 = new widget.Label();
        cmbTingkat = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbEvaluasi = new widget.ComboBox();
        jLabel33 = new widget.Label();
        TnmPetugas1 = new widget.TextBox();
        BtnPtgsPengedukasi = new widget.Button();
        jLabel34 = new widget.Label();
        TnmPenerima = new widget.TextBox();
        scrollPane17 = new widget.ScrollPane();
        TpndPerawatLain = new widget.TextArea();
        jLabel19 = new widget.Label();
        chkAudio = new widget.CekBox();
        chkDemonstrasi = new widget.CekBox();
        chkLisan = new widget.CekBox();
        chkTulisan = new widget.CekBox();
        chkVisual = new widget.CekBox();
        chkPasien = new widget.CekBox();
        chkKeluarga = new widget.CekBox();
        chkLainPnrmaPnd = new widget.CekBox();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        chkEdukasiLat = new widget.CekBox();
        chkPositioning = new widget.CekBox();
        chkLatihanAktif = new widget.CekBox();
        chkSimulasi = new widget.CekBox();
        scrollPane18 = new widget.ScrollPane();
        TpndFisioterapiLain = new widget.TextArea();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        chkNamaDan = new widget.CekBox();
        chkCaraAturan = new widget.CekBox();
        chkResikoEfek = new widget.CekBox();
        chkPenyimpanan = new widget.CekBox();
        scrollPane19 = new widget.ScrollPane();
        TpndApotekerLain = new widget.TextArea();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        panelGlass11 = new widget.panelisi();
        scrollPane3 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel82 = new widget.Label();
        ChkRuangan = new widget.CekBox();
        btnKamar = new widget.Button();
        FormInput3 = new widget.PanelBiasa();
        Scroll = new widget.ScrollPane();
        tbPenilaian = new widget.Table();
        panelGlass9 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount1 = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(172, 55));

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(150, 26));
        MnHapusTtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtd);

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(150, 26));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBikinQrCode);

        WindowNomorDokumenRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNomorDokumenRM.setName("WindowNomorDokumenRM"); // NOI18N
        WindowNomorDokumenRM.setUndecorated(true);
        WindowNomorDokumenRM.setResizable(false);

        internalFrame3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Pilih Rekam Medis :");
        jLabel99.setName("jLabel99"); // NOI18N
        panelisi3.add(jLabel99);
        jLabel99.setBounds(0, 10, 120, 23);

        cmbRM.setBackground(new java.awt.Color(245, 253, 240));
        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRM.setLightWeightPopupEnabled(false);
        cmbRM.setName("cmbRM"); // NOI18N
        panelisi3.add(cmbRM);
        cmbRM.setBounds(127, 10, 550, 23);

        internalFrame3.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnTampilkanQr.setForeground(new java.awt.Color(0, 0, 0));
        BtnTampilkanQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clear24.png"))); // NOI18N
        BtnTampilkanQr.setMnemonic('S');
        BtnTampilkanQr.setText("Tampilkan Qr Code TTD");
        BtnTampilkanQr.setToolTipText("Alt+S");
        BtnTampilkanQr.setName("BtnTampilkanQr"); // NOI18N
        BtnTampilkanQr.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnTampilkanQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTampilkanQrActionPerformed(evt);
            }
        });
        panelisi5.add(BtnTampilkanQr);

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

        internalFrame3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemberian Informasi Dan Edukasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabEdukasi.setBackground(new java.awt.Color(255, 255, 254));
        TabEdukasi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabEdukasi.setName("TabEdukasi"); // NOI18N
        TabEdukasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabEdukasiMouseClicked(evt);
            }
        });

        FormAsesmen.setBorder(null);
        FormAsesmen.setName("FormAsesmen"); // NOI18N
        FormAsesmen.setLayout(new java.awt.GridLayout(1, 2));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 760));

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1520));
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
        TrgRawat.setBounds(114, 38, 608, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Pengkajian Hambatan :");
        jLabel146.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 66, 150, 23);

        chkBahasa.setBackground(new java.awt.Color(255, 255, 250));
        chkBahasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBahasa.setForeground(new java.awt.Color(0, 0, 0));
        chkBahasa.setText("Bahasa");
        chkBahasa.setBorderPainted(true);
        chkBahasa.setBorderPaintedFlat(true);
        chkBahasa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBahasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBahasa.setName("chkBahasa"); // NOI18N
        chkBahasa.setOpaque(false);
        chkBahasa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBahasa);
        chkBahasa.setBounds(160, 66, 80, 23);

        chkPendengaran.setBackground(new java.awt.Color(255, 255, 250));
        chkPendengaran.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendengaran.setForeground(new java.awt.Color(0, 0, 0));
        chkPendengaran.setText("Pendengaran");
        chkPendengaran.setBorderPainted(true);
        chkPendengaran.setBorderPaintedFlat(true);
        chkPendengaran.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendengaran.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendengaran.setName("chkPendengaran"); // NOI18N
        chkPendengaran.setOpaque(false);
        chkPendengaran.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPendengaran);
        chkPendengaran.setBounds(160, 94, 100, 23);

        chkMasalah.setBackground(new java.awt.Color(255, 255, 250));
        chkMasalah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMasalah.setForeground(new java.awt.Color(0, 0, 0));
        chkMasalah.setText("Masalah Penglihatan");
        chkMasalah.setBorderPainted(true);
        chkMasalah.setBorderPaintedFlat(true);
        chkMasalah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMasalah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMasalah.setName("chkMasalah"); // NOI18N
        chkMasalah.setOpaque(false);
        chkMasalah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMasalah);
        chkMasalah.setBounds(160, 122, 130, 23);

        chkHilangMemori.setBackground(new java.awt.Color(255, 255, 250));
        chkHilangMemori.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHilangMemori.setForeground(new java.awt.Color(0, 0, 0));
        chkHilangMemori.setText("Hilang Memori");
        chkHilangMemori.setBorderPainted(true);
        chkHilangMemori.setBorderPaintedFlat(true);
        chkHilangMemori.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHilangMemori.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHilangMemori.setName("chkHilangMemori"); // NOI18N
        chkHilangMemori.setOpaque(false);
        chkHilangMemori.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHilangMemori);
        chkHilangMemori.setBounds(160, 150, 100, 23);

        chkTidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakAda.setText("Tidak Ada Partisipasi Dari Caregive");
        chkTidakAda.setBorderPainted(true);
        chkTidakAda.setBorderPaintedFlat(true);
        chkTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakAda.setName("chkTidakAda"); // NOI18N
        chkTidakAda.setOpaque(false);
        chkTidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakAda);
        chkTidakAda.setBounds(160, 178, 200, 23);

        chkSecara.setBackground(new java.awt.Color(255, 255, 250));
        chkSecara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSecara.setForeground(new java.awt.Color(0, 0, 0));
        chkSecara.setText("Secara Fisiologi Tidak Mampu Belajar");
        chkSecara.setBorderPainted(true);
        chkSecara.setBorderPaintedFlat(true);
        chkSecara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSecara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSecara.setName("chkSecara"); // NOI18N
        chkSecara.setOpaque(false);
        chkSecara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSecara);
        chkSecara.setBounds(160, 206, 200, 23);

        chkTidakDitemukan.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakDitemukan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakDitemukan.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakDitemukan.setText("Tidak Ditemukan Hambatan Belajar");
        chkTidakDitemukan.setBorderPainted(true);
        chkTidakDitemukan.setBorderPaintedFlat(true);
        chkTidakDitemukan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakDitemukan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakDitemukan.setName("chkTidakDitemukan"); // NOI18N
        chkTidakDitemukan.setOpaque(false);
        chkTidakDitemukan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidakDitemukan);
        chkTidakDitemukan.setBounds(400, 66, 200, 23);

        chkCemas.setBackground(new java.awt.Color(255, 255, 250));
        chkCemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCemas.setForeground(new java.awt.Color(0, 0, 0));
        chkCemas.setText("Cemas");
        chkCemas.setBorderPainted(true);
        chkCemas.setBorderPaintedFlat(true);
        chkCemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCemas.setName("chkCemas"); // NOI18N
        chkCemas.setOpaque(false);
        chkCemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCemas);
        chkCemas.setBounds(400, 94, 70, 23);

        chkEmosi.setBackground(new java.awt.Color(255, 255, 250));
        chkEmosi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEmosi.setForeground(new java.awt.Color(0, 0, 0));
        chkEmosi.setText("Emosi");
        chkEmosi.setBorderPainted(true);
        chkEmosi.setBorderPaintedFlat(true);
        chkEmosi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEmosi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEmosi.setName("chkEmosi"); // NOI18N
        chkEmosi.setOpaque(false);
        chkEmosi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEmosi);
        chkEmosi.setBounds(400, 122, 60, 23);

        chkKognitif.setBackground(new java.awt.Color(255, 255, 250));
        chkKognitif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKognitif.setForeground(new java.awt.Color(0, 0, 0));
        chkKognitif.setText("Kognitif");
        chkKognitif.setBorderPainted(true);
        chkKognitif.setBorderPaintedFlat(true);
        chkKognitif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKognitif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKognitif.setName("chkKognitif"); // NOI18N
        chkKognitif.setOpaque(false);
        chkKognitif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKognitif);
        chkKognitif.setBounds(400, 150, 70, 23);

        chkMotivasi.setBackground(new java.awt.Color(255, 255, 250));
        chkMotivasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMotivasi.setForeground(new java.awt.Color(0, 0, 0));
        chkMotivasi.setText("Motivasi Buruk");
        chkMotivasi.setBorderPainted(true);
        chkMotivasi.setBorderPaintedFlat(true);
        chkMotivasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMotivasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMotivasi.setName("chkMotivasi"); // NOI18N
        chkMotivasi.setOpaque(false);
        chkMotivasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMotivasi);
        chkMotivasi.setBounds(400, 178, 110, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Pengkajian : ");
        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(0, 234, 170, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Bicara : ");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(0, 262, 170, 23);

        cmbBicara.setForeground(new java.awt.Color(0, 0, 0));
        cmbBicara.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Normal", "Serangan Awal Gangguan", "Bicara, Kapan" }));
        cmbBicara.setName("cmbBicara"); // NOI18N
        cmbBicara.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbBicara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBicaraActionPerformed(evt);
            }
        });
        FormInput.add(cmbBicara);
        cmbBicara.setBounds(174, 262, 160, 23);

        Tkapan.setBackground(new java.awt.Color(245, 250, 240));
        Tkapan.setForeground(new java.awt.Color(0, 0, 0));
        Tkapan.setName("Tkapan"); // NOI18N
        Tkapan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkapanKeyPressed(evt);
            }
        });
        FormInput.add(Tkapan);
        Tkapan.setBounds(342, 262, 380, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Bahasa Sehari - Hari : ");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(0, 290, 170, 23);

        chkBhsIndonesia.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsIndonesia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsIndonesia.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsIndonesia.setText("Indonesia");
        chkBhsIndonesia.setBorderPainted(true);
        chkBhsIndonesia.setBorderPaintedFlat(true);
        chkBhsIndonesia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsIndonesia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsIndonesia.setName("chkBhsIndonesia"); // NOI18N
        chkBhsIndonesia.setOpaque(false);
        chkBhsIndonesia.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsIndonesia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsIndonesiaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsIndonesia);
        chkBhsIndonesia.setBounds(174, 290, 75, 23);

        cmbBhsIndo.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsIndo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsIndo.setName("cmbBhsIndo"); // NOI18N
        cmbBhsIndo.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsIndo);
        cmbBhsIndo.setBounds(255, 290, 60, 23);

        chkDaerah.setBackground(new java.awt.Color(255, 255, 250));
        chkDaerah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDaerah.setForeground(new java.awt.Color(0, 0, 0));
        chkDaerah.setText("Daerah");
        chkDaerah.setBorderPainted(true);
        chkDaerah.setBorderPaintedFlat(true);
        chkDaerah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDaerah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDaerah.setName("chkDaerah"); // NOI18N
        chkDaerah.setOpaque(false);
        chkDaerah.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDaerah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDaerahActionPerformed(evt);
            }
        });
        FormInput.add(chkDaerah);
        chkDaerah.setBounds(174, 318, 75, 23);

        Tdaerah.setBackground(new java.awt.Color(245, 250, 240));
        Tdaerah.setForeground(new java.awt.Color(0, 0, 0));
        Tdaerah.setName("Tdaerah"); // NOI18N
        Tdaerah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdaerahKeyPressed(evt);
            }
        });
        FormInput.add(Tdaerah);
        Tdaerah.setBounds(255, 318, 467, 23);

        chkBhsInggris.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsInggris.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsInggris.setText("Inggris");
        chkBhsInggris.setBorderPainted(true);
        chkBhsInggris.setBorderPaintedFlat(true);
        chkBhsInggris.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsInggris.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsInggris.setName("chkBhsInggris"); // NOI18N
        chkBhsInggris.setOpaque(false);
        chkBhsInggris.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsInggris.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsInggrisActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsInggris);
        chkBhsInggris.setBounds(342, 290, 60, 23);

        cmbBhsInggris.setForeground(new java.awt.Color(0, 0, 0));
        cmbBhsInggris.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Aktif", "Pasif" }));
        cmbBhsInggris.setName("cmbBhsInggris"); // NOI18N
        cmbBhsInggris.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBhsInggris);
        cmbBhsInggris.setBounds(408, 290, 60, 23);

        chkBhsLainya.setBackground(new java.awt.Color(255, 255, 250));
        chkBhsLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        chkBhsLainya.setText("Lainnya");
        chkBhsLainya.setBorderPainted(true);
        chkBhsLainya.setBorderPaintedFlat(true);
        chkBhsLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBhsLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBhsLainya.setName("chkBhsLainya"); // NOI18N
        chkBhsLainya.setOpaque(false);
        chkBhsLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        chkBhsLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkBhsLainyaActionPerformed(evt);
            }
        });
        FormInput.add(chkBhsLainya);
        chkBhsLainya.setBounds(174, 346, 75, 23);

        TbhsLainya.setBackground(new java.awt.Color(245, 250, 240));
        TbhsLainya.setForeground(new java.awt.Color(0, 0, 0));
        TbhsLainya.setName("TbhsLainya"); // NOI18N
        TbhsLainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbhsLainyaKeyPressed(evt);
            }
        });
        FormInput.add(TbhsLainya);
        TbhsLainya.setBounds(255, 346, 467, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Perlu Penerjemah : ");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(0, 374, 170, 23);

        cmbPerlu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerlu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, Bahasa Asing" }));
        cmbPerlu.setName("cmbPerlu"); // NOI18N
        cmbPerlu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbPerlu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerluActionPerformed(evt);
            }
        });
        FormInput.add(cmbPerlu);
        cmbPerlu.setBounds(174, 374, 120, 23);

        Tperlu.setBackground(new java.awt.Color(245, 250, 240));
        Tperlu.setForeground(new java.awt.Color(0, 0, 0));
        Tperlu.setName("Tperlu"); // NOI18N
        Tperlu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TperluKeyPressed(evt);
            }
        });
        FormInput.add(Tperlu);
        Tperlu.setBounds(302, 374, 420, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Tingkat Pendidikan : ");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(0, 402, 170, 23);

        Tpnd.setEditable(false);
        Tpnd.setBackground(new java.awt.Color(245, 250, 240));
        Tpnd.setForeground(new java.awt.Color(0, 0, 0));
        Tpnd.setName("Tpnd"); // NOI18N
        FormInput.add(Tpnd);
        Tpnd.setBounds(174, 402, 60, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Agama :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(240, 402, 50, 23);

        Tagama.setEditable(false);
        Tagama.setBackground(new java.awt.Color(245, 250, 240));
        Tagama.setForeground(new java.awt.Color(0, 0, 0));
        Tagama.setName("Tagama"); // NOI18N
        FormInput.add(Tagama);
        Tagama.setBounds(295, 402, 250, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Nilai - Nilai Pasien Dan Budaya : ");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(0, 430, 170, 23);

        cmbNilaiPasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbNilaiPasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Modern", "Moderat", "Konvensional" }));
        cmbNilaiPasien.setName("cmbNilaiPasien"); // NOI18N
        cmbNilaiPasien.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbNilaiPasien);
        cmbNilaiPasien.setBounds(174, 430, 100, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Kesediaan Menerima Informasi :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(280, 430, 170, 23);

        cmbKesediaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesediaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKesediaan.setName("cmbKesediaan"); // NOI18N
        cmbKesediaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKesediaan);
        cmbKesediaan.setBounds(456, 430, 60, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Potensial Kebutuhan Pembelajaran : ");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(0, 458, 200, 23);

        chkProses.setBackground(new java.awt.Color(255, 255, 250));
        chkProses.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProses.setForeground(new java.awt.Color(0, 0, 0));
        chkProses.setText("Proses Penyakit");
        chkProses.setBorderPainted(true);
        chkProses.setBorderPaintedFlat(true);
        chkProses.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProses.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProses.setName("chkProses"); // NOI18N
        chkProses.setOpaque(false);
        chkProses.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkProses);
        chkProses.setBounds(205, 458, 120, 23);

        chkPengobatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPengobatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPengobatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPengobatan.setText("Pengobatan/Tindakan");
        chkPengobatan.setBorderPainted(true);
        chkPengobatan.setBorderPaintedFlat(true);
        chkPengobatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPengobatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPengobatan.setName("chkPengobatan"); // NOI18N
        chkPengobatan.setOpaque(false);
        chkPengobatan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPengobatan);
        chkPengobatan.setBounds(205, 486, 134, 23);

        chkAlatBantu.setBackground(new java.awt.Color(255, 255, 250));
        chkAlatBantu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        chkAlatBantu.setText("Alat Bantu Medis");
        chkAlatBantu.setBorderPainted(true);
        chkAlatBantu.setBorderPaintedFlat(true);
        chkAlatBantu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlatBantu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlatBantu.setName("chkAlatBantu"); // NOI18N
        chkAlatBantu.setOpaque(false);
        chkAlatBantu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAlatBantu);
        chkAlatBantu.setBounds(205, 514, 120, 23);

        chkTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTerapi.setForeground(new java.awt.Color(0, 0, 0));
        chkTerapi.setText("Terapi/Obat");
        chkTerapi.setBorderPainted(true);
        chkTerapi.setBorderPaintedFlat(true);
        chkTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTerapi.setName("chkTerapi"); // NOI18N
        chkTerapi.setOpaque(false);
        chkTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTerapi);
        chkTerapi.setBounds(350, 458, 100, 23);

        chkNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkNutrisi.setText("Nutrisi");
        chkNutrisi.setBorderPainted(true);
        chkNutrisi.setBorderPaintedFlat(true);
        chkNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNutrisi.setName("chkNutrisi"); // NOI18N
        chkNutrisi.setOpaque(false);
        chkNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNutrisi);
        chkNutrisi.setBounds(350, 486, 60, 23);

        chkPotensialLain.setBackground(new java.awt.Color(255, 255, 250));
        chkPotensialLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        chkPotensialLain.setText("Lain-Lain, Jelaskan");
        chkPotensialLain.setBorderPainted(true);
        chkPotensialLain.setBorderPaintedFlat(true);
        chkPotensialLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPotensialLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPotensialLain.setName("chkPotensialLain"); // NOI18N
        chkPotensialLain.setOpaque(false);
        chkPotensialLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPotensialLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPotensialLainActionPerformed(evt);
            }
        });
        FormInput.add(chkPotensialLain);
        chkPotensialLain.setBounds(350, 514, 120, 23);

        TPotensialLain.setBackground(new java.awt.Color(245, 250, 240));
        TPotensialLain.setForeground(new java.awt.Color(0, 0, 0));
        TPotensialLain.setName("TPotensialLain"); // NOI18N
        FormInput.add(TPotensialLain);
        TPotensialLain.setBounds(472, 514, 250, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Identifikasi Nilai - Nilai Dan Kepercayaan :");
        jLabel156.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(0, 542, 260, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("Penggunaan Herbal / Jamu :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(0, 570, 230, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Vegetarian :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(0, 598, 230, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("Menolak Vaksinasi :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(0, 626, 230, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("Kepercayaan Terhadap Mistik/Hal-Hal Gaib :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 654, 230, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setText("Puasa Pada Hari Tertentu :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(0, 682, 230, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("Menolak Dilakukan Transfusi Darah :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(300, 570, 330, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("Menolak Pulang Hari Tertentu :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(300, 598, 330, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setText("Menolak Dilayani Oleh Petugas Laki-Laki Pada Pasien Perempuan :");
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(300, 626, 330, 23);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("Tidak Memakan Suatu Jenis Makanan Tertentu, Misal Daging Sapi, Ikan Tidak Bersisik, dll :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(300, 654, 440, 23);

        jLabel166.setForeground(new java.awt.Color(0, 0, 0));
        jLabel166.setText("Lain - Lain :");
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(300, 682, 330, 23);

        cmbPenggunaanHerbal.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenggunaanHerbal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPenggunaanHerbal.setName("cmbPenggunaanHerbal"); // NOI18N
        cmbPenggunaanHerbal.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPenggunaanHerbal);
        cmbPenggunaanHerbal.setBounds(236, 570, 60, 23);

        cmbVegetarian.setForeground(new java.awt.Color(0, 0, 0));
        cmbVegetarian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbVegetarian.setName("cmbVegetarian"); // NOI18N
        cmbVegetarian.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbVegetarian);
        cmbVegetarian.setBounds(236, 598, 60, 23);

        cmbMenolakVaksin.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakVaksin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakVaksin.setName("cmbMenolakVaksin"); // NOI18N
        cmbMenolakVaksin.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakVaksin);
        cmbMenolakVaksin.setBounds(236, 626, 60, 23);

        cmbKepercayaan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKepercayaan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbKepercayaan.setName("cmbKepercayaan"); // NOI18N
        cmbKepercayaan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbKepercayaan);
        cmbKepercayaan.setBounds(236, 654, 60, 23);

        cmbPuasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbPuasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPuasa.setName("cmbPuasa"); // NOI18N
        cmbPuasa.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPuasa);
        cmbPuasa.setBounds(236, 682, 60, 23);

        cmbMenolakDilakukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilakukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilakukan.setName("cmbMenolakDilakukan"); // NOI18N
        cmbMenolakDilakukan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilakukan);
        cmbMenolakDilakukan.setBounds(635, 570, 60, 23);

        cmbMenolakPulang.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakPulang.setName("cmbMenolakPulang"); // NOI18N
        cmbMenolakPulang.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakPulang);
        cmbMenolakPulang.setBounds(635, 598, 60, 23);

        cmbMenolakDilayani.setForeground(new java.awt.Color(0, 0, 0));
        cmbMenolakDilayani.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMenolakDilayani.setName("cmbMenolakDilayani"); // NOI18N
        cmbMenolakDilayani.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbMenolakDilayani);
        cmbMenolakDilayani.setBounds(635, 626, 60, 23);

        cmbTidakMemakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTidakMemakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbTidakMemakan.setName("cmbTidakMemakan"); // NOI18N
        cmbTidakMemakan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbTidakMemakan);
        cmbTidakMemakan.setBounds(747, 654, 60, 23);

        cmbLainLain.setForeground(new java.awt.Color(0, 0, 0));
        cmbLainLain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbLainLain.setName("cmbLainLain"); // NOI18N
        cmbLainLain.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbLainLain);
        cmbLainLain.setBounds(635, 682, 60, 23);

        jLabel167.setForeground(new java.awt.Color(0, 0, 0));
        jLabel167.setText("Nama Petugas Yang Memberikan Pendidikan Kesehatan : ");
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(0, 710, 300, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(300, 710, 360, 23);

        BtnPtgsPemberi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtgsPemberi.setMnemonic('2');
        BtnPtgsPemberi.setToolTipText("Alt+2");
        BtnPtgsPemberi.setName("BtnPtgsPemberi"); // NOI18N
        BtnPtgsPemberi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPtgsPemberi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgsPemberiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPtgsPemberi);
        BtnPtgsPemberi.setBounds(664, 710, 28, 23);

        jLabel168.setForeground(new java.awt.Color(0, 0, 0));
        jLabel168.setText("Tanggal : ");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(0, 738, 300, 23);

        TtglPemberian.setEditable(false);
        TtglPemberian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-07-2026" }));
        TtglPemberian.setDisplayFormat("dd-MM-yyyy");
        TtglPemberian.setName("TtglPemberian"); // NOI18N
        TtglPemberian.setOpaque(false);
        TtglPemberian.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPemberian);
        TtglPemberian.setBounds(300, 738, 90, 23);

        jLabel169.setForeground(new java.awt.Color(0, 0, 0));
        jLabel169.setText("Jam :");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(390, 738, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(435, 738, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(486, 738, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(537, 738, 45, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormAsesmen.add(ScrollTriase1);

        FormInput2.setBorder(null);
        FormInput2.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput2.setName("FormInput2"); // NOI18N
        FormInput2.setPreferredSize(new java.awt.Dimension(870, 718));
        FormInput2.setLayout(new java.awt.BorderLayout());

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Informasi dan Edukasi ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbPemberian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPemberian.setName("tbPemberian"); // NOI18N
        tbPemberian.getTableHeader().setReorderingAllowed(false);
        tbPemberian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPemberianMouseClicked(evt);
            }
        });
        tbPemberian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPemberianKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbPemberian);

        FormInput2.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Pemberian :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel20);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-07-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-07-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

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
        panelGlass10.add(BtnCari);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        FormInput2.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        FormAsesmen.add(FormInput2);

        TabEdukasi.addTab("Informasi Edukasi", FormAsesmen);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout());

        ScrollTriase2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase2.setName("ScrollTriase2"); // NOI18N
        ScrollTriase2.setOpaque(true);
        ScrollTriase2.setPreferredSize(new java.awt.Dimension(102, 458));

        FormInput1.setBorder(null);
        FormInput1.setName("FormInput1"); // NOI18N
        FormInput1.setPreferredSize(new java.awt.Dimension(870, 1518));
        FormInput1.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat : ");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput1.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        TNoRw1.setEditable(false);
        TNoRw1.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw1.setName("TNoRw1"); // NOI18N
        FormInput1.add(TNoRw1);
        TNoRw1.setBounds(114, 10, 122, 23);

        TPasien1.setEditable(false);
        TPasien1.setBackground(new java.awt.Color(245, 250, 240));
        TPasien1.setForeground(new java.awt.Color(0, 0, 0));
        TPasien1.setName("TPasien1"); // NOI18N
        FormInput1.add(TPasien1);
        TPasien1.setBounds(315, 10, 407, 23);

        TNoRM1.setEditable(false);
        TNoRM1.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM1.setName("TNoRM1"); // NOI18N
        FormInput1.add(TNoRM1);
        TNoRM1.setBounds(240, 10, 70, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Ruang Rawat : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput1.add(jLabel11);
        jLabel11.setBounds(0, 38, 110, 23);

        TrgRawat1.setEditable(false);
        TrgRawat1.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat1.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat1.setName("TrgRawat1"); // NOI18N
        FormInput1.add(TrgRawat1);
        TrgRawat1.setBounds(114, 38, 440, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tanggal : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput1.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        TtglPenilaian.setEditable(false);
        TtglPenilaian.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-07-2026" }));
        TtglPenilaian.setDisplayFormat("dd-MM-yyyy");
        TtglPenilaian.setName("TtglPenilaian"); // NOI18N
        TtglPenilaian.setOpaque(false);
        TtglPenilaian.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput1.add(TtglPenilaian);
        TtglPenilaian.setBounds(114, 66, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Jam : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput1.add(jLabel13);
        jLabel13.setBounds(210, 66, 50, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput1.add(cmbJam1);
        cmbJam1.setBounds(265, 66, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput1.add(cmbMnt1);
        cmbMnt1.setBounds(317, 66, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput1.add(cmbDtk1);
        cmbDtk1.setBounds(370, 66, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Penerimaan Pendidikan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput1.add(jLabel14);
        jLabel14.setBounds(0, 94, 160, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Profesi :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput1.add(jLabel16);
        jLabel16.setBounds(427, 94, 60, 23);

        cmbProfesi.setForeground(new java.awt.Color(0, 0, 0));
        cmbProfesi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dokter", "Perawat", "Bidan", "Nutrisionis", "Admisi", "Fisioterapi", "Apoteker", "Lainnya" }));
        cmbProfesi.setName("cmbProfesi"); // NOI18N
        cmbProfesi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbProfesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProfesiActionPerformed(evt);
            }
        });
        FormInput1.add(cmbProfesi);
        cmbProfesi.setBounds(492, 94, 85, 23);

        Tprofesi.setBackground(new java.awt.Color(245, 250, 240));
        Tprofesi.setForeground(new java.awt.Color(0, 0, 0));
        Tprofesi.setName("Tprofesi"); // NOI18N
        Tprofesi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprofesiKeyPressed(evt);
            }
        });
        FormInput1.add(Tprofesi);
        Tprofesi.setBounds(582, 94, 140, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Profesi Dokter : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput1.add(jLabel17);
        jLabel17.setBounds(0, 178, 160, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput1.add(jLabel18);
        jLabel18.setBounds(165, 178, 190, 23);

        chkDiagnosis.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagnosis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagnosis.setText("Diagnosis (Diagnosis Kerja & Diagnosis Banding Dan Dasar)");
        chkDiagnosis.setBorderPainted(true);
        chkDiagnosis.setBorderPaintedFlat(true);
        chkDiagnosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagnosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagnosis.setName("chkDiagnosis"); // NOI18N
        chkDiagnosis.setOpaque(false);
        chkDiagnosis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDiagnosis);
        chkDiagnosis.setBounds(165, 206, 310, 23);

        chkKondisi.setBackground(new java.awt.Color(255, 255, 250));
        chkKondisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKondisi.setForeground(new java.awt.Color(0, 0, 0));
        chkKondisi.setText("Kondisi Pasien");
        chkKondisi.setBorderPainted(true);
        chkKondisi.setBorderPaintedFlat(true);
        chkKondisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKondisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKondisi.setName("chkKondisi"); // NOI18N
        chkKondisi.setOpaque(false);
        chkKondisi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKondisi);
        chkKondisi.setBounds(165, 234, 100, 23);

        chkTindakan.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakan.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakan.setText("Tindakan Yang Diusulkan");
        chkTindakan.setBorderPainted(true);
        chkTindakan.setBorderPaintedFlat(true);
        chkTindakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakan.setName("chkTindakan"); // NOI18N
        chkTindakan.setOpaque(false);
        chkTindakan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTindakan);
        chkTindakan.setBounds(165, 262, 150, 23);

        chkTataCara.setBackground(new java.awt.Color(255, 255, 250));
        chkTataCara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTataCara.setForeground(new java.awt.Color(0, 0, 0));
        chkTataCara.setText("Tata Cara dan Tujuan Tindakan");
        chkTataCara.setBorderPainted(true);
        chkTataCara.setBorderPaintedFlat(true);
        chkTataCara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTataCara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTataCara.setName("chkTataCara"); // NOI18N
        chkTataCara.setOpaque(false);
        chkTataCara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTataCara);
        chkTataCara.setBounds(165, 290, 190, 23);

        chkManfaat.setBackground(new java.awt.Color(255, 255, 250));
        chkManfaat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkManfaat.setForeground(new java.awt.Color(0, 0, 0));
        chkManfaat.setText("Manfaat Dan Resiko Tindakan");
        chkManfaat.setBorderPainted(true);
        chkManfaat.setBorderPaintedFlat(true);
        chkManfaat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkManfaat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkManfaat.setName("chkManfaat"); // NOI18N
        chkManfaat.setOpaque(false);
        chkManfaat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkManfaat);
        chkManfaat.setBounds(165, 318, 190, 23);

        chkNamaOrang.setBackground(new java.awt.Color(255, 255, 250));
        chkNamaOrang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNamaOrang.setForeground(new java.awt.Color(0, 0, 0));
        chkNamaOrang.setText("Nama Orang Yang Mengerjakan Tindakan");
        chkNamaOrang.setBorderPainted(true);
        chkNamaOrang.setBorderPaintedFlat(true);
        chkNamaOrang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNamaOrang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNamaOrang.setName("chkNamaOrang"); // NOI18N
        chkNamaOrang.setOpaque(false);
        chkNamaOrang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkNamaOrang);
        chkNamaOrang.setBounds(490, 206, 230, 23);

        chkKemungkinan.setBackground(new java.awt.Color(255, 255, 250));
        chkKemungkinan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemungkinan.setForeground(new java.awt.Color(0, 0, 0));
        chkKemungkinan.setText("Kemungkinan Alternative");
        chkKemungkinan.setBorderPainted(true);
        chkKemungkinan.setBorderPaintedFlat(true);
        chkKemungkinan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemungkinan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemungkinan.setName("chkKemungkinan"); // NOI18N
        chkKemungkinan.setOpaque(false);
        chkKemungkinan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemungkinan);
        chkKemungkinan.setBounds(490, 234, 160, 23);

        chkPrognosis.setBackground(new java.awt.Color(255, 255, 250));
        chkPrognosis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPrognosis.setForeground(new java.awt.Color(0, 0, 0));
        chkPrognosis.setText("Prognosis Dari Tindakan");
        chkPrognosis.setBorderPainted(true);
        chkPrognosis.setBorderPaintedFlat(true);
        chkPrognosis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPrognosis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPrognosis.setName("chkPrognosis"); // NOI18N
        chkPrognosis.setOpaque(false);
        chkPrognosis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPrognosis);
        chkPrognosis.setBounds(490, 262, 150, 23);

        chkKemTdkTerduga.setBackground(new java.awt.Color(255, 255, 250));
        chkKemTdkTerduga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemTdkTerduga.setForeground(new java.awt.Color(0, 0, 0));
        chkKemTdkTerduga.setText("Kemungkinan Hasil Yang Tidak Terduga");
        chkKemTdkTerduga.setBorderPainted(true);
        chkKemTdkTerduga.setBorderPaintedFlat(true);
        chkKemTdkTerduga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemTdkTerduga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemTdkTerduga.setName("chkKemTdkTerduga"); // NOI18N
        chkKemTdkTerduga.setOpaque(false);
        chkKemTdkTerduga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemTdkTerduga);
        chkKemTdkTerduga.setBounds(490, 290, 220, 23);

        chkKemBila.setBackground(new java.awt.Color(255, 255, 250));
        chkKemBila.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKemBila.setForeground(new java.awt.Color(0, 0, 0));
        chkKemBila.setText("<html>Kemungkinan Hasil Bila Tidak Dilakukan<br>Tindakan</html>");
        chkKemBila.setBorderPainted(true);
        chkKemBila.setBorderPaintedFlat(true);
        chkKemBila.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKemBila.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKemBila.setName("chkKemBila"); // NOI18N
        chkKemBila.setOpaque(false);
        chkKemBila.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKemBila);
        chkKemBila.setBounds(490, 318, 230, 28);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Profesi Perawat / Bidan : ");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput1.add(jLabel23);
        jLabel23.setBounds(0, 346, 160, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput1.add(jLabel24);
        jLabel24.setBounds(165, 346, 190, 23);

        chkPendidikanKes.setBackground(new java.awt.Color(255, 255, 250));
        chkPendidikanKes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendidikanKes.setForeground(new java.awt.Color(0, 0, 0));
        chkPendidikanKes.setText("Pendidikan Kesehatan Tentang :");
        chkPendidikanKes.setBorderPainted(true);
        chkPendidikanKes.setBorderPaintedFlat(true);
        chkPendidikanKes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendidikanKes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendidikanKes.setName("chkPendidikanKes"); // NOI18N
        chkPendidikanKes.setOpaque(false);
        chkPendidikanKes.setPreferredSize(new java.awt.Dimension(175, 23));
        chkPendidikanKes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPendidikanKesActionPerformed(evt);
            }
        });
        FormInput1.add(chkPendidikanKes);
        chkPendidikanKes.setBounds(165, 374, 180, 23);

        TpendidikanKes.setBackground(new java.awt.Color(245, 250, 240));
        TpendidikanKes.setForeground(new java.awt.Color(0, 0, 0));
        TpendidikanKes.setName("TpendidikanKes"); // NOI18N
        TpendidikanKes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpendidikanKesKeyPressed(evt);
            }
        });
        FormInput1.add(TpendidikanKes);
        TpendidikanKes.setBounds(347, 374, 375, 23);

        chkHasilAsuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkHasilAsuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHasilAsuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkHasilAsuhan.setText("Hasil Asuhan Keperawatan");
        chkHasilAsuhan.setBorderPainted(true);
        chkHasilAsuhan.setBorderPaintedFlat(true);
        chkHasilAsuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHasilAsuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHasilAsuhan.setName("chkHasilAsuhan"); // NOI18N
        chkHasilAsuhan.setOpaque(false);
        chkHasilAsuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkHasilAsuhan);
        chkHasilAsuhan.setBounds(165, 402, 180, 23);

        chkPenanganan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenanganan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenanganan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenanganan.setText("Penanganan & Cara Perawatan Di Rumah");
        chkPenanganan.setBorderPainted(true);
        chkPenanganan.setBorderPaintedFlat(true);
        chkPenanganan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenanganan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenanganan.setName("chkPenanganan"); // NOI18N
        chkPenanganan.setOpaque(false);
        chkPenanganan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPenanganan);
        chkPenanganan.setBounds(165, 430, 230, 23);

        chkPerawatanLuka.setBackground(new java.awt.Color(255, 255, 250));
        chkPerawatanLuka.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerawatanLuka.setForeground(new java.awt.Color(0, 0, 0));
        chkPerawatanLuka.setText("Perawatan Luka");
        chkPerawatanLuka.setBorderPainted(true);
        chkPerawatanLuka.setBorderPaintedFlat(true);
        chkPerawatanLuka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerawatanLuka.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerawatanLuka.setName("chkPerawatanLuka"); // NOI18N
        chkPerawatanLuka.setOpaque(false);
        chkPerawatanLuka.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPerawatanLuka);
        chkPerawatanLuka.setBounds(420, 402, 110, 23);

        chkAlatAlat.setBackground(new java.awt.Color(255, 255, 250));
        chkAlatAlat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAlatAlat.setForeground(new java.awt.Color(0, 0, 0));
        chkAlatAlat.setText("Alat - Alat Yang Perlu Disiapkan Di Rumah");
        chkAlatAlat.setBorderPainted(true);
        chkAlatAlat.setBorderPaintedFlat(true);
        chkAlatAlat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAlatAlat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAlatAlat.setName("chkAlatAlat"); // NOI18N
        chkAlatAlat.setOpaque(false);
        chkAlatAlat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkAlatAlat);
        chkAlatAlat.setBounds(420, 430, 230, 23);

        chkInformasi.setBackground(new java.awt.Color(255, 255, 250));
        chkInformasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInformasi.setForeground(new java.awt.Color(0, 0, 0));
        chkInformasi.setText("Informasi Pasien Baru");
        chkInformasi.setBorderPainted(true);
        chkInformasi.setBorderPaintedFlat(true);
        chkInformasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInformasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInformasi.setName("chkInformasi"); // NOI18N
        chkInformasi.setOpaque(false);
        chkInformasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkInformasi);
        chkInformasi.setBounds(165, 458, 140, 23);

        chkKeamanan.setBackground(new java.awt.Color(255, 255, 250));
        chkKeamanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeamanan.setForeground(new java.awt.Color(0, 0, 0));
        chkKeamanan.setText("Keamanan Penggunaan Alat-Alat Kesehatan (Infuse Pump, Siringe Pump, Injeksi Insulin, Dsb)");
        chkKeamanan.setBorderPainted(true);
        chkKeamanan.setBorderPaintedFlat(true);
        chkKeamanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeamanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeamanan.setName("chkKeamanan"); // NOI18N
        chkKeamanan.setOpaque(false);
        chkKeamanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKeamanan);
        chkKeamanan.setBounds(165, 486, 480, 23);

        chkProsedur.setBackground(new java.awt.Color(255, 255, 250));
        chkProsedur.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkProsedur.setForeground(new java.awt.Color(0, 0, 0));
        chkProsedur.setText("<html>Prosedur Tindakan/Kedokteran Yang Tidak Perlu Informed Consent (Infuse Line, Foley Catather,<br>Nasogastric Tube Sesuai Kebijakan)</html>");
        chkProsedur.setBorderPainted(true);
        chkProsedur.setBorderPaintedFlat(true);
        chkProsedur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkProsedur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkProsedur.setName("chkProsedur"); // NOI18N
        chkProsedur.setOpaque(false);
        chkProsedur.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkProsedur);
        chkProsedur.setBounds(165, 514, 510, 27);

        chkFarmaObat.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaObat.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaObat.setText("Farmakologi : Obat Oral");
        chkFarmaObat.setBorderPainted(true);
        chkFarmaObat.setBorderPaintedFlat(true);
        chkFarmaObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaObat.setName("chkFarmaObat"); // NOI18N
        chkFarmaObat.setOpaque(false);
        chkFarmaObat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaObat);
        chkFarmaObat.setBounds(165, 547, 140, 23);

        chkFarmaInjek.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaInjek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaInjek.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaInjek.setText("Farmakologi : Injeksi");
        chkFarmaInjek.setBorderPainted(true);
        chkFarmaInjek.setBorderPaintedFlat(true);
        chkFarmaInjek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaInjek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaInjek.setName("chkFarmaInjek"); // NOI18N
        chkFarmaInjek.setOpaque(false);
        chkFarmaInjek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaInjek);
        chkFarmaInjek.setBounds(330, 547, 130, 23);

        chkFarmaSedasi.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmaSedasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmaSedasi.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmaSedasi.setText("Farmakologi : Sedasi");
        chkFarmaSedasi.setBorderPainted(true);
        chkFarmaSedasi.setBorderPaintedFlat(true);
        chkFarmaSedasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmaSedasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmaSedasi.setName("chkFarmaSedasi"); // NOI18N
        chkFarmaSedasi.setOpaque(false);
        chkFarmaSedasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkFarmaSedasi);
        chkFarmaSedasi.setBounds(470, 547, 130, 23);

        chkPerawatanLatihan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerawatanLatihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerawatanLatihan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerawatanLatihan.setText("Perawatan Latihan Nafas Dalam");
        chkPerawatanLatihan.setBorderPainted(true);
        chkPerawatanLatihan.setBorderPaintedFlat(true);
        chkPerawatanLatihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerawatanLatihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerawatanLatihan.setName("chkPerawatanLatihan"); // NOI18N
        chkPerawatanLatihan.setOpaque(false);
        chkPerawatanLatihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPerawatanLatihan);
        chkPerawatanLatihan.setBounds(165, 575, 190, 23);

        chkDistraksi.setBackground(new java.awt.Color(255, 255, 250));
        chkDistraksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistraksi.setForeground(new java.awt.Color(0, 0, 0));
        chkDistraksi.setText("Distraksi");
        chkDistraksi.setBorderPainted(true);
        chkDistraksi.setBorderPaintedFlat(true);
        chkDistraksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistraksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistraksi.setName("chkDistraksi"); // NOI18N
        chkDistraksi.setOpaque(false);
        chkDistraksi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDistraksi);
        chkDistraksi.setBounds(165, 603, 70, 23);

        chkPengalihan.setBackground(new java.awt.Color(255, 255, 250));
        chkPengalihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPengalihan.setForeground(new java.awt.Color(0, 0, 0));
        chkPengalihan.setText("Pengalihan Perhatian, Evaluasi Nyeri Sesuai Derajatnya");
        chkPengalihan.setBorderPainted(true);
        chkPengalihan.setBorderPaintedFlat(true);
        chkPengalihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPengalihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPengalihan.setName("chkPengalihan"); // NOI18N
        chkPengalihan.setOpaque(false);
        chkPengalihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPengalihan);
        chkPengalihan.setBounds(165, 631, 295, 23);

        chkCaraCuci.setBackground(new java.awt.Color(255, 255, 250));
        chkCaraCuci.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaraCuci.setForeground(new java.awt.Color(0, 0, 0));
        chkCaraCuci.setText("Cara Cuci Tangan");
        chkCaraCuci.setBorderPainted(true);
        chkCaraCuci.setBorderPaintedFlat(true);
        chkCaraCuci.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaraCuci.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaraCuci.setName("chkCaraCuci"); // NOI18N
        chkCaraCuci.setOpaque(false);
        chkCaraCuci.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkCaraCuci);
        chkCaraCuci.setBounds(165, 659, 120, 23);

        chkEtika.setBackground(new java.awt.Color(255, 255, 250));
        chkEtika.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEtika.setForeground(new java.awt.Color(0, 0, 0));
        chkEtika.setText("Etika Batuk");
        chkEtika.setBorderPainted(true);
        chkEtika.setBorderPaintedFlat(true);
        chkEtika.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtika.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtika.setName("chkEtika"); // NOI18N
        chkEtika.setOpaque(false);
        chkEtika.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkEtika);
        chkEtika.setBounds(470, 575, 90, 23);

        chkCaraBuang.setBackground(new java.awt.Color(255, 255, 250));
        chkCaraBuang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaraBuang.setForeground(new java.awt.Color(0, 0, 0));
        chkCaraBuang.setText("Cara Buang Sampah Medis Dan Non Medis");
        chkCaraBuang.setBorderPainted(true);
        chkCaraBuang.setBorderPaintedFlat(true);
        chkCaraBuang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaraBuang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaraBuang.setName("chkCaraBuang"); // NOI18N
        chkCaraBuang.setOpaque(false);
        chkCaraBuang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkCaraBuang);
        chkCaraBuang.setBounds(470, 603, 230, 23);

        chkTempat.setBackground(new java.awt.Color(255, 255, 250));
        chkTempat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTempat.setForeground(new java.awt.Color(0, 0, 0));
        chkTempat.setText("Tempat Toileting");
        chkTempat.setBorderPainted(true);
        chkTempat.setBorderPaintedFlat(true);
        chkTempat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTempat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTempat.setName("chkTempat"); // NOI18N
        chkTempat.setOpaque(false);
        chkTempat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTempat);
        chkTempat.setBounds(470, 631, 110, 23);

        chkLainProPerawat.setBackground(new java.awt.Color(255, 255, 250));
        chkLainProPerawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainProPerawat.setForeground(new java.awt.Color(0, 0, 0));
        chkLainProPerawat.setText("Lain - Lain");
        chkLainProPerawat.setBorderPainted(true);
        chkLainProPerawat.setBorderPaintedFlat(true);
        chkLainProPerawat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainProPerawat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainProPerawat.setName("chkLainProPerawat"); // NOI18N
        chkLainProPerawat.setOpaque(false);
        chkLainProPerawat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainProPerawat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainProPerawatActionPerformed(evt);
            }
        });
        FormInput1.add(chkLainProPerawat);
        chkLainProPerawat.setBounds(165, 687, 75, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Profesi Nutrisionis : ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput1.add(jLabel25);
        jLabel25.setBounds(0, 771, 160, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput1.add(jLabel26);
        jLabel26.setBounds(165, 771, 190, 23);

        chkDiet.setBackground(new java.awt.Color(255, 255, 250));
        chkDiet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiet.setForeground(new java.awt.Color(0, 0, 0));
        chkDiet.setText("Diet Dan Nutrisi");
        chkDiet.setBorderPainted(true);
        chkDiet.setBorderPaintedFlat(true);
        chkDiet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiet.setName("chkDiet"); // NOI18N
        chkDiet.setOpaque(false);
        chkDiet.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDiet);
        chkDiet.setBounds(165, 800, 106, 23);

        chkKonsulGiziRanap.setBackground(new java.awt.Color(255, 255, 250));
        chkKonsulGiziRanap.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKonsulGiziRanap.setForeground(new java.awt.Color(0, 0, 0));
        chkKonsulGiziRanap.setText("Konsultasi Gizi Rawat Inap");
        chkKonsulGiziRanap.setBorderPainted(true);
        chkKonsulGiziRanap.setBorderPaintedFlat(true);
        chkKonsulGiziRanap.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKonsulGiziRanap.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKonsulGiziRanap.setName("chkKonsulGiziRanap"); // NOI18N
        chkKonsulGiziRanap.setOpaque(false);
        chkKonsulGiziRanap.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKonsulGiziRanap);
        chkKonsulGiziRanap.setBounds(280, 800, 160, 23);

        chkKonsulGiziRalan.setBackground(new java.awt.Color(255, 255, 250));
        chkKonsulGiziRalan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKonsulGiziRalan.setForeground(new java.awt.Color(0, 0, 0));
        chkKonsulGiziRalan.setText("Konsultasi Gizi Rawat Jalan");
        chkKonsulGiziRalan.setBorderPainted(true);
        chkKonsulGiziRalan.setBorderPaintedFlat(true);
        chkKonsulGiziRalan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKonsulGiziRalan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKonsulGiziRalan.setName("chkKonsulGiziRalan"); // NOI18N
        chkKonsulGiziRalan.setOpaque(false);
        chkKonsulGiziRalan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKonsulGiziRalan);
        chkKonsulGiziRalan.setBounds(450, 800, 160, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        TpndNutrisionisLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndNutrisionisLain.setColumns(20);
        TpndNutrisionisLain.setRows(5);
        TpndNutrisionisLain.setName("TpndNutrisionisLain"); // NOI18N
        TpndNutrisionisLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndNutrisionisLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndNutrisionisLainKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(TpndNutrisionisLain);

        FormInput1.add(scrollPane14);
        scrollPane14.setBounds(165, 828, 560, 74);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Profesi Admisi : ");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput1.add(jLabel27);
        jLabel27.setBounds(0, 908, 160, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput1.add(jLabel28);
        jLabel28.setBounds(165, 908, 190, 23);

        chkHak.setBackground(new java.awt.Color(255, 255, 250));
        chkHak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHak.setForeground(new java.awt.Color(0, 0, 0));
        chkHak.setText("Hak Dan Kewajiban Pasien");
        chkHak.setBorderPainted(true);
        chkHak.setBorderPaintedFlat(true);
        chkHak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHak.setName("chkHak"); // NOI18N
        chkHak.setOpaque(false);
        chkHak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkHak);
        chkHak.setBounds(165, 936, 160, 23);

        chkJam.setBackground(new java.awt.Color(255, 255, 250));
        chkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJam.setForeground(new java.awt.Color(0, 0, 0));
        chkJam.setText("Jam Konsultasi, Biaya, Tata Tertib, Fasilitas RS");
        chkJam.setBorderPainted(true);
        chkJam.setBorderPaintedFlat(true);
        chkJam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJam.setName("chkJam"); // NOI18N
        chkJam.setOpaque(false);
        chkJam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkJam);
        chkJam.setBounds(165, 964, 270, 23);

        chkInfoKejadian.setBackground(new java.awt.Color(255, 255, 250));
        chkInfoKejadian.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInfoKejadian.setForeground(new java.awt.Color(0, 0, 0));
        chkInfoKejadian.setText("Informasi Kejadian Yang Tidak Diharapkan");
        chkInfoKejadian.setBorderPainted(true);
        chkInfoKejadian.setBorderPaintedFlat(true);
        chkInfoKejadian.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfoKejadian.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfoKejadian.setName("chkInfoKejadian"); // NOI18N
        chkInfoKejadian.setOpaque(false);
        chkInfoKejadian.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkInfoKejadian);
        chkInfoKejadian.setBounds(350, 936, 230, 23);

        scrollPane15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane15.setName("scrollPane15"); // NOI18N

        TpndAdmisiLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndAdmisiLain.setColumns(20);
        TpndAdmisiLain.setRows(5);
        TpndAdmisiLain.setName("TpndAdmisiLain"); // NOI18N
        TpndAdmisiLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndAdmisiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndAdmisiLainKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(TpndAdmisiLain);

        FormInput1.add(scrollPane15);
        scrollPane15.setBounds(165, 992, 560, 74);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Profesi Lainnya : ");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput1.add(jLabel29);
        jLabel29.setBounds(0, 1343, 160, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("ISI PENDIDIKAN KESEHATAN (Edukasi Lain/Lanjutan)");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput1.add(jLabel30);
        jLabel30.setBounds(165, 1343, 270, 23);

        scrollPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane16.setName("scrollPane16"); // NOI18N

        TpndLainyaLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndLainyaLain.setColumns(20);
        TpndLainyaLain.setRows(5);
        TpndLainyaLain.setName("TpndLainyaLain"); // NOI18N
        TpndLainyaLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndLainyaLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndLainyaLainKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(TpndLainyaLain);

        FormInput1.add(scrollPane16);
        scrollPane16.setBounds(165, 1371, 560, 74);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Tingkat Pemahaman : ");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput1.add(jLabel31);
        jLabel31.setBounds(0, 1450, 160, 23);

        cmbTingkat.setForeground(new java.awt.Color(0, 0, 0));
        cmbTingkat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - Edukasi Pertama", "2 - ReEdukasi ke 2/3 atau Lebih" }));
        cmbTingkat.setName("cmbTingkat"); // NOI18N
        cmbTingkat.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(cmbTingkat);
        cmbTingkat.setBounds(165, 1450, 185, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Evaluasi Respon :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput1.add(jLabel32);
        jLabel32.setBounds(350, 1450, 110, 23);

        cmbEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - Tidak Mengerti", "2 - Menyatakan Pemahaman", "3 - Mampu Menjelaskan", "4 - Mampu Demonstrasi / Simulasi" }));
        cmbEvaluasi.setName("cmbEvaluasi"); // NOI18N
        cmbEvaluasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput1.add(cmbEvaluasi);
        cmbEvaluasi.setBounds(466, 1450, 195, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Pemberi Edukasi : ");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput1.add(jLabel33);
        jLabel33.setBounds(0, 150, 160, 23);

        TnmPetugas1.setEditable(false);
        TnmPetugas1.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas1.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas1.setName("TnmPetugas1"); // NOI18N
        FormInput1.add(TnmPetugas1);
        TnmPetugas1.setBounds(165, 150, 360, 23);

        BtnPtgsPengedukasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtgsPengedukasi.setMnemonic('2');
        BtnPtgsPengedukasi.setToolTipText("Alt+2");
        BtnPtgsPengedukasi.setName("BtnPtgsPengedukasi"); // NOI18N
        BtnPtgsPengedukasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPtgsPengedukasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgsPengedukasiActionPerformed(evt);
            }
        });
        FormInput1.add(BtnPtgsPengedukasi);
        BtnPtgsPengedukasi.setBounds(529, 150, 28, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Penerima Edukasi : ");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput1.add(jLabel34);
        jLabel34.setBounds(0, 1478, 160, 23);

        TnmPenerima.setBackground(new java.awt.Color(245, 250, 240));
        TnmPenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenerima.setName("TnmPenerima"); // NOI18N
        FormInput1.add(TnmPenerima);
        TnmPenerima.setBounds(165, 1478, 440, 23);

        scrollPane17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane17.setName("scrollPane17"); // NOI18N

        TpndPerawatLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndPerawatLain.setColumns(20);
        TpndPerawatLain.setRows(5);
        TpndPerawatLain.setName("TpndPerawatLain"); // NOI18N
        TpndPerawatLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndPerawatLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndPerawatLainKeyPressed(evt);
            }
        });
        scrollPane17.setViewportView(TpndPerawatLain);

        FormInput1.add(scrollPane17);
        scrollPane17.setBounds(245, 687, 480, 80);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Metode : ");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput1.add(jLabel19);
        jLabel19.setBounds(0, 122, 160, 23);

        chkAudio.setBackground(new java.awt.Color(255, 255, 250));
        chkAudio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAudio.setForeground(new java.awt.Color(0, 0, 0));
        chkAudio.setText("1 - Audio");
        chkAudio.setBorderPainted(true);
        chkAudio.setBorderPaintedFlat(true);
        chkAudio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAudio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAudio.setName("chkAudio"); // NOI18N
        chkAudio.setOpaque(false);
        chkAudio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkAudio);
        chkAudio.setBounds(165, 122, 72, 23);

        chkDemonstrasi.setBackground(new java.awt.Color(255, 255, 250));
        chkDemonstrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDemonstrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkDemonstrasi.setText("2 - Demonstrasi");
        chkDemonstrasi.setBorderPainted(true);
        chkDemonstrasi.setBorderPaintedFlat(true);
        chkDemonstrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDemonstrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDemonstrasi.setName("chkDemonstrasi"); // NOI18N
        chkDemonstrasi.setOpaque(false);
        chkDemonstrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkDemonstrasi);
        chkDemonstrasi.setBounds(250, 122, 103, 23);

        chkLisan.setBackground(new java.awt.Color(255, 255, 250));
        chkLisan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLisan.setForeground(new java.awt.Color(0, 0, 0));
        chkLisan.setText("3 - Lisan");
        chkLisan.setBorderPainted(true);
        chkLisan.setBorderPaintedFlat(true);
        chkLisan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLisan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLisan.setName("chkLisan"); // NOI18N
        chkLisan.setOpaque(false);
        chkLisan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkLisan);
        chkLisan.setBounds(366, 122, 70, 23);

        chkTulisan.setBackground(new java.awt.Color(255, 255, 250));
        chkTulisan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTulisan.setForeground(new java.awt.Color(0, 0, 0));
        chkTulisan.setText("4 - Tulisan");
        chkTulisan.setBorderPainted(true);
        chkTulisan.setBorderPaintedFlat(true);
        chkTulisan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTulisan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTulisan.setName("chkTulisan"); // NOI18N
        chkTulisan.setOpaque(false);
        chkTulisan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkTulisan);
        chkTulisan.setBounds(450, 122, 80, 23);

        chkVisual.setBackground(new java.awt.Color(255, 255, 250));
        chkVisual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkVisual.setForeground(new java.awt.Color(0, 0, 0));
        chkVisual.setText("5 - Visual");
        chkVisual.setBorderPainted(true);
        chkVisual.setBorderPaintedFlat(true);
        chkVisual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkVisual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkVisual.setName("chkVisual"); // NOI18N
        chkVisual.setOpaque(false);
        chkVisual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkVisual);
        chkVisual.setBounds(540, 122, 80, 23);

        chkPasien.setBackground(new java.awt.Color(255, 255, 250));
        chkPasien.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPasien.setForeground(new java.awt.Color(0, 0, 0));
        chkPasien.setText("P - Pasien");
        chkPasien.setBorderPainted(true);
        chkPasien.setBorderPaintedFlat(true);
        chkPasien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPasien.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasien.setName("chkPasien"); // NOI18N
        chkPasien.setOpaque(false);
        chkPasien.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPasien);
        chkPasien.setBounds(165, 94, 72, 23);

        chkKeluarga.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluarga.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluarga.setText("K - Keluarga");
        chkKeluarga.setBorderPainted(true);
        chkKeluarga.setBorderPaintedFlat(true);
        chkKeluarga.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluarga.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluarga.setName("chkKeluarga"); // NOI18N
        chkKeluarga.setOpaque(false);
        chkKeluarga.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkKeluarga);
        chkKeluarga.setBounds(250, 94, 90, 23);

        chkLainPnrmaPnd.setBackground(new java.awt.Color(255, 255, 250));
        chkLainPnrmaPnd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainPnrmaPnd.setForeground(new java.awt.Color(0, 0, 0));
        chkLainPnrmaPnd.setText("L - Lain-lain");
        chkLainPnrmaPnd.setBorderPainted(true);
        chkLainPnrmaPnd.setBorderPaintedFlat(true);
        chkLainPnrmaPnd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainPnrmaPnd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainPnrmaPnd.setName("chkLainPnrmaPnd"); // NOI18N
        chkLainPnrmaPnd.setOpaque(false);
        chkLainPnrmaPnd.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkLainPnrmaPnd);
        chkLainPnrmaPnd.setBounds(346, 94, 90, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Profesi Fisioterapi : ");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput1.add(jLabel35);
        jLabel35.setBounds(0, 1072, 160, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel36.setName("jLabel36"); // NOI18N
        FormInput1.add(jLabel36);
        jLabel36.setBounds(165, 1072, 190, 23);

        chkEdukasiLat.setBackground(new java.awt.Color(255, 255, 250));
        chkEdukasiLat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEdukasiLat.setForeground(new java.awt.Color(0, 0, 0));
        chkEdukasiLat.setText("Edukasi Latihan");
        chkEdukasiLat.setBorderPainted(true);
        chkEdukasiLat.setBorderPaintedFlat(true);
        chkEdukasiLat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEdukasiLat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEdukasiLat.setName("chkEdukasiLat"); // NOI18N
        chkEdukasiLat.setOpaque(false);
        chkEdukasiLat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkEdukasiLat);
        chkEdukasiLat.setBounds(165, 1100, 105, 23);

        chkPositioning.setBackground(new java.awt.Color(255, 255, 250));
        chkPositioning.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPositioning.setForeground(new java.awt.Color(0, 0, 0));
        chkPositioning.setText("Positioning");
        chkPositioning.setBorderPainted(true);
        chkPositioning.setBorderPaintedFlat(true);
        chkPositioning.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPositioning.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPositioning.setName("chkPositioning"); // NOI18N
        chkPositioning.setOpaque(false);
        chkPositioning.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPositioning);
        chkPositioning.setBounds(280, 1100, 82, 23);

        chkLatihanAktif.setBackground(new java.awt.Color(255, 255, 250));
        chkLatihanAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLatihanAktif.setForeground(new java.awt.Color(0, 0, 0));
        chkLatihanAktif.setText("Latihan Aktif Assisted");
        chkLatihanAktif.setBorderPainted(true);
        chkLatihanAktif.setBorderPaintedFlat(true);
        chkLatihanAktif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLatihanAktif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLatihanAktif.setName("chkLatihanAktif"); // NOI18N
        chkLatihanAktif.setOpaque(false);
        chkLatihanAktif.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkLatihanAktif);
        chkLatihanAktif.setBounds(370, 1100, 130, 23);

        chkSimulasi.setBackground(new java.awt.Color(255, 255, 250));
        chkSimulasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSimulasi.setForeground(new java.awt.Color(0, 0, 0));
        chkSimulasi.setText("Simulasi Bicara");
        chkSimulasi.setBorderPainted(true);
        chkSimulasi.setBorderPaintedFlat(true);
        chkSimulasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSimulasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSimulasi.setName("chkSimulasi"); // NOI18N
        chkSimulasi.setOpaque(false);
        chkSimulasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkSimulasi);
        chkSimulasi.setBounds(510, 1100, 100, 23);

        scrollPane18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane18.setName("scrollPane18"); // NOI18N

        TpndFisioterapiLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndFisioterapiLain.setColumns(20);
        TpndFisioterapiLain.setRows(5);
        TpndFisioterapiLain.setName("TpndFisioterapiLain"); // NOI18N
        TpndFisioterapiLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndFisioterapiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndFisioterapiLainKeyPressed(evt);
            }
        });
        scrollPane18.setViewportView(TpndFisioterapiLain);

        FormInput1.add(scrollPane18);
        scrollPane18.setBounds(165, 1128, 560, 74);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Profesi Apoteker : ");
        jLabel37.setName("jLabel37"); // NOI18N
        FormInput1.add(jLabel37);
        jLabel37.setBounds(0, 1207, 160, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("ISI PENDIDIKAN KESEHATAN");
        jLabel38.setName("jLabel38"); // NOI18N
        FormInput1.add(jLabel38);
        jLabel38.setBounds(165, 1207, 190, 23);

        chkNamaDan.setBackground(new java.awt.Color(255, 255, 250));
        chkNamaDan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNamaDan.setForeground(new java.awt.Color(0, 0, 0));
        chkNamaDan.setText("Nama dan Indikasi Obat");
        chkNamaDan.setBorderPainted(true);
        chkNamaDan.setBorderPaintedFlat(true);
        chkNamaDan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNamaDan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNamaDan.setName("chkNamaDan"); // NOI18N
        chkNamaDan.setOpaque(false);
        chkNamaDan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkNamaDan);
        chkNamaDan.setBounds(165, 1235, 145, 23);

        chkCaraAturan.setBackground(new java.awt.Color(255, 255, 250));
        chkCaraAturan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCaraAturan.setForeground(new java.awt.Color(0, 0, 0));
        chkCaraAturan.setText("Cara/Aturan Penggunaan Obat");
        chkCaraAturan.setBorderPainted(true);
        chkCaraAturan.setBorderPaintedFlat(true);
        chkCaraAturan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCaraAturan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCaraAturan.setName("chkCaraAturan"); // NOI18N
        chkCaraAturan.setOpaque(false);
        chkCaraAturan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkCaraAturan);
        chkCaraAturan.setBounds(318, 1235, 180, 23);

        chkResikoEfek.setBackground(new java.awt.Color(255, 255, 250));
        chkResikoEfek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkResikoEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkResikoEfek.setText("Resiko Efek Samping Obat");
        chkResikoEfek.setBorderPainted(true);
        chkResikoEfek.setBorderPaintedFlat(true);
        chkResikoEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkResikoEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkResikoEfek.setName("chkResikoEfek"); // NOI18N
        chkResikoEfek.setOpaque(false);
        chkResikoEfek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkResikoEfek);
        chkResikoEfek.setBounds(506, 1235, 155, 23);

        chkPenyimpanan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenyimpanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenyimpanan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenyimpanan.setText("Penyimpanan Obat");
        chkPenyimpanan.setBorderPainted(true);
        chkPenyimpanan.setBorderPaintedFlat(true);
        chkPenyimpanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenyimpanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenyimpanan.setName("chkPenyimpanan"); // NOI18N
        chkPenyimpanan.setOpaque(false);
        chkPenyimpanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput1.add(chkPenyimpanan);
        chkPenyimpanan.setBounds(670, 1235, 120, 23);

        scrollPane19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane19.setName("scrollPane19"); // NOI18N

        TpndApotekerLain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TpndApotekerLain.setColumns(20);
        TpndApotekerLain.setRows(5);
        TpndApotekerLain.setName("TpndApotekerLain"); // NOI18N
        TpndApotekerLain.setPreferredSize(new java.awt.Dimension(162, 2000));
        TpndApotekerLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpndApotekerLainKeyPressed(evt);
            }
        });
        scrollPane19.setViewportView(TpndApotekerLain);

        FormInput1.add(scrollPane19);
        scrollPane19.setBounds(165, 1263, 560, 74);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        FormInput1.add(Scroll5);
        Scroll5.setBounds(750, 330, 260, 240);

        panelGlass11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass11.setComponentPopupMenu(jPopupMenu1);
        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane3.setComponentPopupMenu(jPopupMenu1);
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane3.setViewportView(gambarQR);

        panelGlass11.add(scrollPane3);

        FormInput1.add(panelGlass11);
        panelGlass11.setBounds(750, 10, 230, 245);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel82.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel82.setName("jLabel82"); // NOI18N
        jLabel82.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput1.add(jLabel82);
        jLabel82.setBounds(760, 262, 210, 60);

        ChkRuangan.setBackground(new java.awt.Color(255, 255, 250));
        ChkRuangan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRuangan.setForeground(new java.awt.Color(0, 0, 0));
        ChkRuangan.setText("Rg. Rawat Sekarang");
        ChkRuangan.setBorderPainted(true);
        ChkRuangan.setBorderPaintedFlat(true);
        ChkRuangan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRuangan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRuangan.setName("ChkRuangan"); // NOI18N
        ChkRuangan.setOpaque(false);
        ChkRuangan.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkRuangan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkRuanganActionPerformed(evt);
            }
        });
        FormInput1.add(ChkRuangan);
        ChkRuangan.setBounds(600, 38, 140, 23);

        btnKamar.setForeground(new java.awt.Color(0, 0, 0));
        btnKamar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnKamar.setName("btnKamar"); // NOI18N
        btnKamar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKamarActionPerformed(evt);
            }
        });
        FormInput1.add(btnKamar);
        btnKamar.setBounds(560, 38, 30, 23);

        ScrollTriase2.setViewportView(FormInput1);

        internalFrame4.add(ScrollTriase2, java.awt.BorderLayout.CENTER);

        FormInput3.setBorder(null);
        FormInput3.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis..!!");
        FormInput3.setName("FormInput3"); // NOI18N
        FormInput3.setPreferredSize(new java.awt.Dimension(770, 718));
        FormInput3.setLayout(new java.awt.BorderLayout());

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Data Penilaian Pemberian Pendidikan Kesehatan ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPenilaian.setAutoCreateRowSorter(true);
        tbPenilaian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenilaian.setComponentPopupMenu(jPopupMenu1);
        tbPenilaian.setName("tbPenilaian"); // NOI18N
        tbPenilaian.getTableHeader().setReorderingAllowed(false);
        tbPenilaian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPenilaianMouseClicked(evt);
            }
        });
        tbPenilaian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPenilaianKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPenilaian);

        FormInput3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari1.setForeground(new java.awt.Color(0, 0, 0));
        TCari1.setName("TCari1"); // NOI18N
        TCari1.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari1KeyPressed(evt);
            }
        });
        panelGlass9.add(TCari1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('3');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari1);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount1);

        FormInput3.add(panelGlass9, java.awt.BorderLayout.PAGE_END);

        internalFrame4.add(FormInput3, java.awt.BorderLayout.EAST);

        TabEdukasi.addTab("Penilaian Pemberian Pendidikan Kesehatan", internalFrame4);

        internalFrame1.add(TabEdukasi, java.awt.BorderLayout.CENTER);

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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Cetak Dalam Bentuk :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel63);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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
        if (TabEdukasi.getSelectedIndex() == 0) {
            simpanInformasiEdukasi();
        } else {
//            if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw1.getText() + "'") > 0) {
            simpanPenilaianEdukasi();
//            } else {
//                JOptionPane.showMessageDialog(null, "Data pemberian informasi & edukasi pasien harus disimpan dulu sebelum memberikan penilaian..!!");
//                TabEdukasi.setSelectedIndex(0);                
//            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            emptTeks();
            tampil();
        } else {
            ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
            emptTeksPenilaian();
            tampilPenilaian();
        }
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            if (TabEdukasi.getSelectedIndex() == 0) {
                emptTeks();
                tampil();
            } else {
                emptTeksPenilaian();
                tampilPenilaian();
            }
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
    if (TabEdukasi.getSelectedIndex() == 0) {
        hapusInformasiEdukasi();
    } else {
        hapusPenilaianEdukasi();
    }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            gantiInformasiEdukasi();
        } else {
            gantiPenilaianEdukasi();
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
        WindowNomorDokumenRM.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari1);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
        } else {
            if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
                Map<String, Object> param = new HashMap<>();
                param.put("namars", akses.getnamars());
                param.put("logo", Sequel.cariGambar("select logo from setting"));
                param.put("norm", TNoRM.getText());
                param.put("nmpasien", TPasien.getText());
                param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

                //data pemberian informasi edukasi
                try {
                    ps2 = koneksi.prepareStatement("select *, time_format(jam,'%H:%i Wita') jamnya from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'");
                    try {
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
                            if (rs2.getString("bahasa").equals("ya")) {
                                param.put("bahasa", "V");
                            } else {
                                param.put("bahasa", "");
                            }

                            if (rs2.getString("pendengaran").equals("ya")) {
                                param.put("pendengaran", "V");
                            } else {
                                param.put("pendengaran", "");
                            }

                            if (rs2.getString("masalah_penglihatan").equals("ya")) {
                                param.put("masalahPenglihatan", "V");
                            } else {
                                param.put("masalahPenglihatan", "");
                            }

                            if (rs2.getString("hilang_memori").equals("ya")) {
                                param.put("hilangMemori", "V");
                            } else {
                                param.put("hilangMemori", "");
                            }

                            if (rs2.getString("tidak_ada_partisipasi").equals("ya")) {
                                param.put("tidakAdaPartisipasi", "V");
                            } else {
                                param.put("tidakAdaPartisipasi", "");
                            }

                            if (rs2.getString("secara_fisiologi").equals("ya")) {
                                param.put("secaraFisiologi", "V");
                            } else {
                                param.put("secaraFisiologi", "");
                            }

                            if (rs2.getString("tidak_ditemukan_hambatan").equals("ya")) {
                                param.put("tidakDitemukanHambatan", "V");
                            } else {
                                param.put("tidakDitemukanHambatan", "");
                            }

                            if (rs2.getString("cemas").equals("ya")) {
                                param.put("cemas", "V");
                            } else {
                                param.put("cemas", "");
                            }

                            if (rs2.getString("emosi").equals("ya")) {
                                param.put("emosi", "V");
                            } else {
                                param.put("emosi", "");
                            }

                            if (rs2.getString("kognitif").equals("ya")) {
                                param.put("kognitif", "V");
                            } else {
                                param.put("kognitif", "");
                            }

                            if (rs2.getString("motifasi_buruk").equals("ya")) {
                                param.put("motifasiBuruk", "V");
                            } else {
                                param.put("motifasiBuruk", "");
                            }

                            if (rs2.getString("bicara").equals("Bicara, Kapan")) {
                                if (rs2.getString("ket_kapan").equals("")) {
                                    param.put("bicara", "Bicara : " + rs2.getString("bicara") + " ..........");
                                } else {
                                    param.put("bicara", "Bicara : " + rs2.getString("bicara") + " " + rs2.getString("ket_kapan"));
                                }
                            } else {
                                param.put("bicara", "Bicara : " + rs2.getString("bicara"));
                            }

                            if (rs2.getString("bahasa_indonesia").equals("ya")) {
                                param.put("bahasaIndonesia", "V");
                                param.put("akpasIndonesia", "Indonesia, " + rs2.getString("indonesia"));
                            } else {
                                param.put("bahasaIndonesia", "");
                                param.put("akpasIndonesia", "Indonesia");
                            }

                            if (rs2.getString("bahasa_daerah").equals("ya")) {
                                param.put("bahasaDaerah", "V");
                                if (rs2.getString("ket_daerah").equals("")) {
                                    param.put("jelasDaerah", "Daerah, jelaskan .......");
                                } else {
                                    param.put("jelasDaerah", "Daerah, jelaskan " + rs2.getString("ket_daerah"));
                                }
                            } else {
                                param.put("bahasaDaerah", "");
                                param.put("jelasDaerah", "Daerah, jelaskan .......");
                            }

                            if (rs2.getString("bahasa_inggris").equals("ya")) {
                                param.put("bahasaInggris", "V");
                                param.put("akpasInggris", "Inggris, " + rs2.getString("inggris"));
                            } else {
                                param.put("bahasaInggris", "");
                                param.put("akpasInggris", "Inggris");
                            }

                            if (rs2.getString("bahasa_lainnya").equals("ya")) {
                                param.put("bahasaLainnya", "V");
                                if (rs2.getString("ket_bahasa_lainnya").equals("")) {
                                    param.put("ketLainya", "Lainnya, .......");
                                } else {
                                    param.put("ketLainya", "Lainnya, " + rs2.getString("ket_bahasa_lainnya"));
                                }
                            } else {
                                param.put("bahasaLainnya", "");
                                param.put("ketLainya", "Lainnya, .......");
                            }

                            if (rs2.getString("penerjemah").equals("Ya, Bahasa Asing")) {
                                if (rs2.getString("ket_penerjemah").equals("")) {
                                    param.put("penerjemah", rs2.getString("penerjemah") + " ..........");
                                } else {
                                    param.put("penerjemah", rs2.getString("penerjemah") + " " + rs2.getString("ket_penerjemah"));
                                }
                            } else {
                                param.put("penerjemah", rs2.getString("penerjemah"));
                            }

                            param.put("pnddkn", Sequel.cariIsi("select pnd from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                            param.put("agama", Sequel.cariIsi("select agama from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                            param.put("nilaiPasien", rs2.getString("nilai_pasien"));
                            param.put("kesediaan", rs2.getString("kesediaan_menerima"));

                            if (rs2.getString("proses_penyakit").equals("ya")) {
                                param.put("prosesPenyakit", "V");
                            } else {
                                param.put("prosesPenyakit", "");
                            }

                            if (rs2.getString("pengobatan").equals("ya")) {
                                param.put("pengobatan", "V");
                            } else {
                                param.put("pengobatan", "");
                            }

                            if (rs2.getString("alat_bantu_medis").equals("ya")) {
                                param.put("alatBantuMedis", "V");
                            } else {
                                param.put("alatBantuMedis", "");
                            }

                            if (rs2.getString("lain_lain").equals("ya")) {
                                param.put("lainLain", "V");
                                if (rs2.getString("ket_lainlain").equals("")) {
                                    param.put("ketlainLain", "Lain-Lain, Jelaskan ........");
                                } else {
                                    param.put("ketlainLain", "Lain-Lain, Jelaskan " + rs2.getString("ket_lainlain"));
                                }
                            } else {
                                param.put("lainLain", "");
                                param.put("ketlainLain", "Lain-Lain, Jelaskan ........");
                            }

                            if (rs2.getString("terapi_obat").equals("ya")) {
                                param.put("terapiObat", "V");
                            } else {
                                param.put("terapiObat", "");
                            }

                            if (rs2.getString("nutrisi").equals("ya")) {
                                param.put("nutrisi", "V");
                            } else {
                                param.put("nutrisi", "");
                            }

                            param.put("herbal", rs2.getString("penggunaan_herbal"));
                            param.put("veget", rs2.getString("vegetarian"));
                            param.put("nolakVaksin", rs2.getString("menolak_vaksinasi"));
                            param.put("kepercayaan", rs2.getString("kepercayaan_terhadap"));
                            param.put("puasa", rs2.getString("puasa"));
                            param.put("nolakDilakukan", rs2.getString("menolak_dilakukan"));
                            param.put("nolakPulang", rs2.getString("menolak_pulang"));
                            param.put("nolakDilayani", rs2.getString("menolak_dilayani"));
                            param.put("tidakMemakan", rs2.getString("tidak_memakan"));
                            param.put("lainIdentifikasi", rs2.getString("lain_lain_identifikasi"));
                            param.put("petugasPemberi", Sequel.cariIsi("select nama from pegawai where nik='" + rs2.getString("nip_petugas") + "'"));
                            param.put("tglPemberian", Valid.SetTglINDONESIA(rs2.getString("tanggal")));
                            param.put("jamPemberian", rs2.getString("jamnya"));
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
                //--------------------------------------------------------------------------------------------

                //data penilaian informasi edukasi
                Sequel.AutoComitFalse();
                Sequel.queryu("delete from temporary_tte2");
                Sequel.queryu("delete from temporary3");
                try {
                    ps3 = koneksi.prepareStatement("select pi.*, date_format(pi.tanggal,'%d/%m/%Y') tggl, time_format(pi.jam,'%H:%i') jamm, "
                            + "date_format(pi.tanggal,'%W') harii, date_format(pi.waktu_simpan,'%d/%m/%Y') tglSimpan, time(pi.waktu_simpan) jamSimpan, "
                            + "pg.nama nmPetugas, date_format(pi.waktu_simpan,'%Y-%m-%d %H:%i:%s') wktSimpan from penilaian_informasi_edukasi pi "
                            + "inner join pegawai pg on pg.nik=pi.nip_petugas where pi.no_rawat='" + TNoRw.getText() + "' order by pi.tanggal, jam");
                    try {
                        rs3 = ps3.executeQuery();
                        while (rs3.next()) {
                            variabelBersihPenilaian();
                            String profesi = "", isiPenKesDokter = "", isiPenKesPERBID = "", isiPenKesNutrisionis = "", isiPenKesNutrisionisLain = "",
                                    isiPenKesAdmisi = "", isiPenKesFisio = "", isiPenKesApotek = "", isiPenKesAdmisiLain = "", isiPenKesProfesiLain = "",
                                    isiPenKesOK = "", tingkatPemahanan = "", evaluasi = "", metodeNilai = "", metodAudio = "", metodDemon = "", metodLisan = "",
                                    metodTulis = "", metodVisual = "", penerimaPND = "", psn = "", klg = "", lainPnermaPND = "", isiPenKesFisioLain = "",
                                    isiPenKesApotekLain = "";

                            if (rs3.getString("profesi").equals("Lainnya")) {
                                if (rs3.getString("profesi_lainya").equals("")) {
                                    profesi = rs3.getString("profesi");
                                } else {
                                    profesi = rs3.getString("profesi") + " (" + rs3.getString("profesi_lainya") + ")";
                                }
                            } else {
                                profesi = rs3.getString("profesi");
                            }
                            
                            if (rs3.getString("penerima_pendidikan").equals("P - Pasien")) {
                                penerimaPND = "P - Pasien";
                            } else if (rs3.getString("penerima_pendidikan").equals("K - Keluarga")) {
                                penerimaPND = "K - Keluarga";
                            } else if (rs3.getString("penerima_pendidikan").equals("L - Lain-lain")) {
                                penerimaPND = "L - Lain-lain";
                            } else {
                                if (rs3.getString("pasien").equals("ya")) {
                                    psn = "P - Pasien\n";
                                } else {
                                    psn = "";
                                }
                                
                                if (rs3.getString("keluarga").equals("ya")) {
                                    klg = "K - Keluarga\n";
                                } else {
                                    klg = "";
                                }
                                
                                if (rs3.getString("lainPenerimaPnd").equals("ya")) {
                                    lainPnermaPND = "L - Lain-lain";
                                } else {
                                    lainPnermaPND = "";
                                }
                                
                                if (psn.equals("") && klg.equals("") && lainPnermaPND.equals("")) {
                                    penerimaPND = "-";
                                } else {
                                    penerimaPND = psn + klg + lainPnermaPND;
                                }
                            }
                            
                            if (rs3.getString("metode").equals("1 - Audio")) {
                                metodeNilai = "1 - Audio";
                            } else if (rs3.getString("metode").equals("2 - Demonstrasi")) {
                                metodeNilai = "2 - Demonstrasi";
                            } else if (rs3.getString("metode").equals("3 - Lisan")) {
                                metodeNilai = "3 - Lisan";
                            } else if (rs3.getString("metode").equals("4 - Tulisan")) {
                                metodeNilai = "4 - Tulisan";
                            } else if (rs3.getString("metode").equals("5 - Visual")) {
                                metodeNilai = "5 - Visual";
                            } else {
                                if (rs3.getString("audio").equals("ya")) {
                                    metodAudio = "1 - Audio\n";
                                } else {
                                    metodAudio = "";
                                }
                                
                                if (rs3.getString("demonstrasi").equals("ya")) {
                                    metodDemon = "2 - Demonstrasi\n";
                                } else {
                                    metodDemon = "";
                                }
                                
                                if (rs3.getString("lisan").equals("ya")) {
                                    metodLisan = "3 - Lisan\n";
                                } else {
                                    metodLisan = "";
                                }
                                
                                if (rs3.getString("tulisan").equals("ya")) {
                                    metodTulis = "4 - Tulisan\n";
                                } else {
                                    metodTulis = "";
                                }
                                
                                if (rs3.getString("visual").equals("ya")) {
                                    metodVisual = "5 - Visual";
                                } else {
                                    metodVisual = "";
                                }
                                
                                if (metodAudio.equals("") && metodDemon.equals("") && metodLisan.equals("")
                                        && metodTulis.equals("") && metodVisual.equals("")) {
                                    metodeNilai = "-";
                                } else {
                                    metodeNilai = metodAudio + metodDemon + metodLisan + metodTulis + metodVisual;
                                }
                            }

                            if (rs3.getString("profesi").equals("Dokter")) {
                                if (rs3.getString("diagnosis").equals("ya")) {
                                    diagnosis = "- Diagnosis (Diagnosis Kerja & Diagnosis Banding Dan Dasar)\n";
                                } else {
                                    diagnosis = "";
                                }

                                if (rs3.getString("kondisi_pasien").equals("ya")) {
                                    kondisiPasien = "- Kondisi Pasien\n";
                                } else {
                                    kondisiPasien = "";
                                }

                                if (rs3.getString("tindakan").equals("ya")) {
                                    tindakan = "- Tindakan Yang Diusulkan\n";
                                } else {
                                    tindakan = "";
                                }

                                if (rs3.getString("tata_cara").equals("ya")) {
                                    tataCara = "- Tata Cara dan Tujuan Tindakan\n";
                                } else {
                                    tataCara = "";
                                }

                                if (rs3.getString("manfaat").equals("ya")) {
                                    manfaat = "- Manfaat Dan Resiko Tindakan\n";
                                } else {
                                    manfaat = "";
                                }

                                if (rs3.getString("nama_orang").equals("ya")) {
                                    namaOrang = "- Nama Orang Yang Mengerjakan Tindakan\n";
                                } else {
                                    namaOrang = "";
                                }

                                if (rs3.getString("kemungkinan_alternative").equals("ya")) {
                                    kemungkinanAlternative = "- Kemungkinan Alternative\n";
                                } else {
                                    kemungkinanAlternative = "";
                                }

                                if (rs3.getString("prognosis").equals("ya")) {
                                    prognosis = "- Prognosis Dari Tindakan\n";
                                } else {
                                    prognosis = "";
                                }

                                if (rs3.getString("kemungkinan_tdk_terduga").equals("ya")) {
                                    kemungkinanTdkTerduga = "- Kemungkinan Hasil Yang Tidak Terduga\n";
                                } else {
                                    kemungkinanTdkTerduga = "";
                                }

                                if (rs3.getString("kemungkinan_bila").equals("ya")) {
                                    kemungkinanBila = "- Kemungkinan Hasil Bila Tidak Dilakukan Tindakan\n";
                                } else {
                                    kemungkinanBila = "";
                                }

                                isiPenKesDokter = diagnosis + kondisiPasien + tindakan + tataCara + manfaat + namaOrang
                                        + kemungkinanAlternative + prognosis + kemungkinanTdkTerduga + kemungkinanBila;

                            } else if (rs3.getString("profesi").equals("Perawat") || rs3.getString("profesi").equals("Bidan")) {
                                if (rs3.getString("pendidikan_kesehatan").equals("ya")) {
                                    if (rs3.getString("ket_pendidikan").equals("")) {
                                        pendidikanKesehatan = "- Pendidikan Kesehatan Tentang : .......\n";
                                    } else {
                                        pendidikanKesehatan = "- Pendidikan Kesehatan Tentang : " + rs3.getString("ket_pendidikan") + "\n";
                                    }
                                } else {
                                    pendidikanKesehatan = "";
                                }

                                if (rs3.getString("hasil_asuhan").equals("ya")) {
                                    hasilAsuhan = "- Hasil Asuhan Keperawatan\n";
                                } else {
                                    hasilAsuhan = "";
                                }

                                if (rs3.getString("penanganan").equals("ya")) {
                                    penanganan = "- Penanganan & Cara Perawatan Di Rumah\n";
                                } else {
                                    penanganan = "";
                                }

                                if (rs3.getString("perawatan_luka").equals("ya")) {
                                    perawatanLuka = "- Perawatan Luka\n";
                                } else {
                                    perawatanLuka = "";
                                }

                                if (rs3.getString("alat_alat").equals("ya")) {
                                    alatAlat = "- Alat - Alat Yang Perlu Disiapkan Di Rumah\n";
                                } else {
                                    alatAlat = "";
                                }

                                if (rs3.getString("informasi_pasien").equals("ya")) {
                                    informasiPasien = "- Informasi Pasien Baru\n";
                                } else {
                                    informasiPasien = "";
                                }

                                if (rs3.getString("keamanan_penggunaan").equals("ya")) {
                                    keamananPenggunaan = "- Keamanan Penggunaan Alat-Alat Kesehatan (Infuse Pump, Siringe Pump, Injeksi Insulin, Dsb)\n";
                                } else {
                                    keamananPenggunaan = "";
                                }

                                if (rs3.getString("prosedur_tindakan").equals("ya")) {
                                    prosedurTindakan = "- Prosedur Tindakan/Kedokteran Yang Tidak Perlu Informed Consent (Infuse Line, Foley Catather, Nasogastric Tube Sesuai Kebijakan)\n";
                                } else {
                                    prosedurTindakan = "";
                                }

                                if (rs3.getString("farmakologi_obat").equals("ya")) {
                                    farmakologiObat = "- Farmakologi : Obat Oral\n";
                                } else {
                                    farmakologiObat = "";
                                }

                                if (rs3.getString("farmakologi_injeksi").equals("ya")) {
                                    farmakologiInjeksi = "- Farmakologi : Injeksi\n";
                                } else {
                                    farmakologiInjeksi = "";
                                }

                                if (rs3.getString("farmakologi_sedasi").equals("ya")) {
                                    farmakologiSedasi = "- Farmakologi : Sedasi\n";
                                } else {
                                    farmakologiSedasi = "";
                                }

                                if (rs3.getString("perawatan_latihan").equals("ya")) {
                                    perawatanLatihan = "- Perawatan Latihan Nafas Dalam\n";
                                } else {
                                    perawatanLatihan = "";
                                }

                                if (rs3.getString("distraksi").equals("ya")) {
                                    distraksi = "- Distraksi\n";
                                } else {
                                    distraksi = "";
                                }

                                if (rs3.getString("pengalihan_perhatian").equals("ya")) {
                                    pengalihanPerhatian = "- Pengalihan Perhatian, Evaluasi Nyeri Sesuai Derajatnya\n";
                                } else {
                                    pengalihanPerhatian = "";
                                }

                                if (rs3.getString("cara_cuci").equals("ya")) {
                                    caraCuci = "- Cara Cuci Tangan\n";
                                } else {
                                    caraCuci = "";
                                }

                                if (rs3.getString("etika_batuk").equals("ya")) {
                                    etikaBatuk = "- Etika Batuk\n";
                                } else {
                                    etikaBatuk = "";
                                }

                                if (rs3.getString("cara_buang").equals("ya")) {
                                    caraBuang = "- Cara Buang Sampah Medis Dan Non Medis\n";
                                } else {
                                    caraBuang = "";
                                }

                                if (rs3.getString("tempat_toileting").equals("ya")) {
                                    tempatToileting = "- Tempat Toileting\n";
                                } else {
                                    tempatToileting = "";
                                }

                                if (rs3.getString("lainya_perawat_bidan").equals("ya")) {
                                    if (rs3.getString("ket_lainya_perawat_bidan").equals("")) {
                                        lainyaPerawatBidan = "- Lain - Lain : .......\n";
                                    } else {
                                        lainyaPerawatBidan = "- Lain - Lain : " + rs3.getString("ket_lainya_perawat_bidan") + "\n";
                                    }
                                } else {
                                    lainyaPerawatBidan = "";
                                }

                                isiPenKesPERBID = pendidikanKesehatan + hasilAsuhan + penanganan + perawatanLuka + alatAlat + informasiPasien
                                        + keamananPenggunaan + prosedurTindakan + farmakologiObat + farmakologiInjeksi + farmakologiSedasi
                                        + perawatanLatihan + distraksi + pengalihanPerhatian + caraCuci + etikaBatuk + caraBuang + tempatToileting
                                        + lainyaPerawatBidan;

                            } else if (rs3.getString("profesi").equals("Nutrisionis")) {
                                if (rs3.getString("diet").equals("ya")) {
                                    diet = "- Diet Dan Nutrisi\n";
                                } else {
                                    diet = "";
                                }

                                if (rs3.getString("konsul_gizi_ranap").equals("ya")) {
                                    konsulGiziRanap = "- Konsultasi Gizi Rawat Inap\n";
                                } else {
                                    konsulGiziRanap = "";
                                }

                                if (rs3.getString("konsul_gizi_ralan").equals("ya")) {
                                    konsulGiziRalan = "- Konsultasi Gizi Rawat Jalan\n";
                                } else {
                                    konsulGiziRalan = "";
                                }

                                if (rs3.getString("ket_lainya_nutrisionis").equals("")) {
                                    isiPenKesNutrisionisLain = "";
                                } else {
                                    isiPenKesNutrisionisLain = rs3.getString("ket_lainya_nutrisionis") + "\n";
                                }

                                isiPenKesNutrisionis = diet + konsulGiziRanap + konsulGiziRalan + isiPenKesNutrisionisLain;

                            } else if (rs3.getString("profesi").equals("Admisi")) {
                                if (rs3.getString("hak_dan").equals("ya")) {
                                    hakDan = "- Hak Dan Kewajiban Pasien\n";
                                } else {
                                    hakDan = "";
                                }

                                if (rs3.getString("jam_konsultasi").equals("ya")) {
                                    jamKonsultasi = "- Jam Konsultasi, Biaya, Tata Tertib, Fasilitas RS\n";
                                } else {
                                    jamKonsultasi = "";
                                }

                                if (rs3.getString("informasi_kejadian").equals("ya")) {
                                    informasiKejadian = "- Informasi Kejadian Yang Tidak Diharapkan\n";
                                } else {
                                    informasiKejadian = "";
                                }

                                if (rs3.getString("ket_lainya_admisi").equals("")) {
                                    isiPenKesAdmisiLain = "";
                                } else {
                                    isiPenKesAdmisiLain = rs3.getString("ket_lainya_admisi") + "\n";
                                }

                                isiPenKesAdmisi = hakDan + jamKonsultasi + informasiKejadian + isiPenKesAdmisiLain;

                            } else if (rs3.getString("profesi").equals("Fisioterapi")) {
                                if (rs3.getString("edukasi_latihan").equals("ya")) {
                                    edukasiLat = "- Edukasi Latihan\n";
                                } else {
                                    edukasiLat = "";
                                }
                                
                                if (rs3.getString("positioning").equals("ya")) {
                                    positioning = "- Positioning\n";
                                } else {
                                    positioning = "";
                                }
                                
                                if (rs3.getString("latihan_aktif").equals("ya")) {
                                    latihanAktif = "- Latihan Aktif Assisted\n";
                                } else {
                                    latihanAktif = "";
                                }
                                
                                if (rs3.getString("simulasi_bicara").equals("ya")) {
                                    simulasi = "- Simulasi Bicara\n";
                                } else {
                                    simulasi = "";
                                }
                                
                                if (rs3.getString("ket_lainya_fisioterapi").equals("")) {
                                    isiPenKesFisioLain = "";
                                } else {
                                    isiPenKesFisioLain = rs3.getString("ket_lainya_fisioterapi") + "\n";
                                }

                                isiPenKesFisio = edukasiLat + positioning + latihanAktif + simulasi + isiPenKesFisioLain;

                            } else if (rs3.getString("profesi").equals("Apoteker")) {
                                if (rs3.getString("nama_dan").equals("ya")) {
                                    namaDan = "- Nama dan Indikasi Obat\n";
                                } else {
                                    namaDan = "";
                                }
                                
                                if (rs3.getString("cara_aturan").equals("ya")) {
                                    caraAturan = "- Cara/Aturan Penggunaan Obat\n";
                                } else {
                                    caraAturan = "";
                                }
                                
                                if (rs3.getString("resiko_efek").equals("ya")) {
                                    resikoEfek = "- Resiko Efek Samping Obat\n";
                                } else {
                                    resikoEfek = "";
                                }
                                
                                if (rs3.getString("penyimpanan_obat").equals("ya")) {
                                    penyimpananObat = "- Penyimpanan Obat\n";
                                } else {
                                    penyimpananObat = "";
                                }
                                
                                if (rs3.getString("ket_lainya_apoteker").equals("")) {
                                    isiPenKesApotekLain = "";
                                } else {
                                    isiPenKesApotekLain = rs3.getString("ket_lainya_apoteker") + "\n";
                                }

                                isiPenKesApotek = namaDan + caraAturan + resikoEfek + penyimpananObat + isiPenKesApotekLain;
                            
                            } else if (rs3.getString("profesi").equals("Lainnya")) {
                                if (rs3.getString("edukasi_lain_lanjutan").equals("")) {
                                    isiPenKesProfesiLain = "";
                                } else {
                                    isiPenKesProfesiLain = "** Edukasi Lain/Lanjutan **\n\n" + rs3.getString("edukasi_lain_lanjutan") + "\n";
                                }
                            }

                            isiPenKesOK = isiPenKesDokter + isiPenKesPERBID + isiPenKesNutrisionis + isiPenKesAdmisi + isiPenKesFisio + isiPenKesApotek + isiPenKesProfesiLain;
                            
                            if (rs3.getString("tingkat_pemahaman").equals("2 - ReEdukasi ke 2/3 atau Lebih")) {
                                tingkatPemahanan = "2 - ReEdukasi\nke 2/3 atau Lebih";
                            } else {
                                tingkatPemahanan = rs3.getString("tingkat_pemahaman");
                            }
                            
                            if (rs3.getString("evaluasi_respon").equals("2 - Menyatakan Pemahaman")) {
                                evaluasi = "2 - Menyatakan\nPemahaman";
                            } else if (rs3.getString("evaluasi_respon").equals("4 - Mampu Demonstrasi / Simulasi")) {
                                evaluasi = "4 - Mampu\nDemonstrasi/Simulasi";
                            } else {
                                evaluasi = rs3.getString("evaluasi_respon");
                            }
                            
                            if (cmbPilihCetak.getSelectedIndex() == 0) {
                                String isi = "", ipGambar = "";
                                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                                isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                                        + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                                "Pemberian Informasi Dan Edukasi", rs3.getString("nmPetugas"),
                                                rs3.getString("tglSimpan"), rs3.getString("jamSimpan")) + "') from kalimat_tte where kode='001'");

                                Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");                                
                                Sequel.menyimpanQrTte2("temporary_tte2",
                                        "'" + Sequel.hariINDONESIAnamaHari(rs3.getString("harii")) + ", " + rs3.getString("tggl") + "\n" + rs3.getString("jamm") + " Wita" + "',"
                                        + "'" + penerimaPND + "','" + metodeNilai + "','" + profesi + "','" + isiPenKesOK + "','" + tingkatPemahanan + "',"
                                        + "'" + evaluasi + "','" + rs3.getString("nmPetugas") + "','" + rs3.getString("nm_penerima_edukasi") + "',"
                                        + "'" + rs3.getString("wktSimpan") + "','" + rs3.getString("id_file_nm_penerima_edukasi") + "'",
                                        "file QRCode TTE Pemberian Informasi Dan Edukasi", Sequel.cariFolderPrintTte(), "");
                                
                                //proses update set gambar ttd ke tabel temporary_tte2 field tempGambar2
                                try {
                                    //cek atau ping ip addres
                                    ipGambar = "192.168.0.230";
                                    InetAddress inet = InetAddress.getByName(ipGambar);

                                    //ping sukses timeout 100 ms (0.1 detik)
                                    if (inet.isReachable(100)) {
                                        if (!rs3.getString("id_file_nm_penerima_edukasi").equals("")) {
                                            Valid.jalankanURL("http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/update_blob?id_file=" + rs3.getString("id_file_nm_penerima_edukasi"));
                                        }
                                        //ping gagal
                                    } else {
                                        System.out.println("Notif : gagal ping ke ipAddress 192.168.0.230");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notif : " + e);
                                }                                
                                
                            } else {
                                Sequel.menyimpanIgnore("temporary3",
                                        "'" + Sequel.hariINDONESIAnamaHari(rs3.getString("harii")) + ", " + rs3.getString("tggl") + "\n" + rs3.getString("jamm") + " Wita" + "','"
                                        + penerimaPND + "','"
                                        + metodeNilai + "','"
                                        + profesi + "','"
                                        + isiPenKesOK + "','"
                                        + tingkatPemahanan + "','"
                                        + evaluasi + "','"
                                        + rs3.getString("nmPetugas") + "','"
                                        + rs3.getString("nm_penerima_edukasi") + "','"
                                        + rs3.getString("wktSimpan") + "','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',"
                                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','','',"
                                        + "'','','','','','','','','','','','','','','','','','','','','','','','','','',''", "Pemberian Informasi Edukasi");
                            }
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
                Sequel.AutoComitTrue();

                if (Sequel.cariInteger("select count(-1) from penilaian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") == 0) {
                    Valid.MyReport("rptPemberianInformasiEdukasiKosong.jasper", "report", "::[ Pemberian Informasi Dan Edukasi ]::",
                            "SELECT date(now()) tanggal", param);
                } else {
                    if (cmbPilihCetak.getSelectedIndex() == 0) {
                        Valid.MyReport("rptPemberianInformasiEdukasiQr.jasper", "report", "::[ Pemberian Informasi Dan Edukasi ]::",
                                "SELECT * FROM temporary_tte2", param);
                        Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                        Sequel.queryu("delete from temporary_tte2");
                    } else {
                        Valid.MyReport("rptPemberianInformasiEdukasi.jasper", "report", "::[ Pemberian Informasi Dan Edukasi ]::",
                                "SELECT * FROM temporary3", param);
                    }
                }

                BtnBatalActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, data pemberian informasi & edukasi belum tersimpan untuk pasien ini..!!!");
            }
        }
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCari1KeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
        tampilPenilaian();
}//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari1ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari1, BtnAll);
        }
}//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        if (TabEdukasi.getSelectedIndex() == 0) {
            TCari.setText("");
            tampil();
        } else {
            ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
            TCari1.setText("");
            tampilPenilaian();
        }
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (TabEdukasi.getSelectedIndex() == 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                TCari.setText("");
                tampil();
            } else {
                Valid.pindah(evt, BtnCari, TPasien);
            }
        } else {
            if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
                TCari1.setText("");
                tampilPenilaian();
            } else {
                Valid.pindah(evt, BtnCari1, TPasien1);
            }
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabEdukasi.setSelectedIndex(1);
            tampilPenilaian();
        } else if (Sequel.cariInteger("select count(-1) from pemberian_informasi_edukasi where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabEdukasi.setSelectedIndex(0);
            tampil();
        }
        
        ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='TPPRI, Ruang Perawatan & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void TabEdukasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabEdukasiMouseClicked
        if (TabEdukasi.getSelectedIndex() == 0) {
            tampil();
        } else {
            ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
            tampilPenilaian();
        }
    }//GEN-LAST:event_TabEdukasiMouseClicked

    private void tbPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenilaianKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPenilaianKeyPressed

    private void tbPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenilaianMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }            
        }
    }//GEN-LAST:event_tbPenilaianMouseClicked

    private void cmbBicaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBicaraActionPerformed
        Tkapan.setText("");
        if (cmbBicara.getSelectedIndex() == 3) {
            Tkapan.setEnabled(true);
            Tkapan.requestFocus();
        } else {
            Tkapan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbBicaraActionPerformed

    private void chkBhsIndonesiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsIndonesiaActionPerformed
        cmbBhsIndo.setSelectedIndex(0);
        if (chkBhsIndonesia.isSelected() == true) {
            cmbBhsIndo.setEnabled(true);
            cmbBhsIndo.requestFocus();
        } else {
            cmbBhsIndo.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsIndonesiaActionPerformed

    private void TkapanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkapanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsIndonesia.requestFocus();
        }
    }//GEN-LAST:event_TkapanKeyPressed

    private void chkDaerahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDaerahActionPerformed
        Tdaerah.setText("");
        if (chkDaerah.isSelected() == true) {
            Tdaerah.setEnabled(true);
            Tdaerah.requestFocus();
        } else {
            Tdaerah.setEnabled(false);
        }
    }//GEN-LAST:event_chkDaerahActionPerformed

    private void chkBhsInggrisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsInggrisActionPerformed
        cmbBhsInggris.setSelectedIndex(0);
        if (chkBhsInggris.isSelected() == true) {
            cmbBhsInggris.setEnabled(true);
            cmbBhsInggris.requestFocus();
        } else {
            cmbBhsInggris.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsInggrisActionPerformed

    private void TdaerahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdaerahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBhsLainya.requestFocus();
        }
    }//GEN-LAST:event_TdaerahKeyPressed

    private void chkBhsLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkBhsLainyaActionPerformed
        TbhsLainya.setText("");
        if (chkBhsLainya.isSelected() == true) {
            TbhsLainya.setEnabled(true);
            TbhsLainya.requestFocus();
        } else {
            TbhsLainya.setEnabled(false);
        }
    }//GEN-LAST:event_chkBhsLainyaActionPerformed

    private void TbhsLainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbhsLainyaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPerlu.requestFocus();
        }
    }//GEN-LAST:event_TbhsLainyaKeyPressed

    private void cmbPerluActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerluActionPerformed
        Tperlu.setText("");
        if (cmbPerlu.getSelectedIndex() == 2) {
            Tperlu.setEnabled(true);
            Tperlu.requestFocus();
        } else {
            Tperlu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPerluActionPerformed

    private void chkPotensialLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPotensialLainActionPerformed
        TPotensialLain.setText("");
        if (chkPotensialLain.isSelected() == true) {
            TPotensialLain.setEnabled(true);
            TPotensialLain.requestFocus();
        } else {
            TPotensialLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkPotensialLainActionPerformed

    private void TperluKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TperluKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbNilaiPasien.requestFocus();
        }
    }//GEN-LAST:event_TperluKeyPressed

    private void tbPemberianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPemberianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbPemberianMouseClicked

    private void tbPemberianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPemberianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbPemberianKeyPressed

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

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbProfesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProfesiActionPerformed
        Tprofesi.setText("");
        chkDiagnosis.setSelected(false);
        chkKondisi.setSelected(false);
        chkTindakan.setSelected(false);
        chkTataCara.setSelected(false);
        chkManfaat.setSelected(false);
        chkNamaOrang.setSelected(false);
        chkKemungkinan.setSelected(false);
        chkPrognosis.setSelected(false);
        chkKemTdkTerduga.setSelected(false);
        chkKemBila.setSelected(false);
        
        chkPendidikanKes.setSelected(false);
        chkHasilAsuhan.setSelected(false);
        chkPenanganan.setSelected(false);
        chkPerawatanLuka.setSelected(false);
        chkAlatAlat.setSelected(false);
        chkInformasi.setSelected(false);
        chkKeamanan.setSelected(false);
        chkProsedur.setSelected(false);
        chkFarmaObat.setSelected(false);
        chkFarmaInjek.setSelected(false);
        chkFarmaSedasi.setSelected(false);
        chkPerawatanLatihan.setSelected(false);
        chkDistraksi.setSelected(false);
        chkPengalihan.setSelected(false);
        chkEtika.setSelected(false);
        chkCaraBuang.setSelected(false);
        chkTempat.setSelected(false);
        chkCaraCuci.setSelected(false);
        chkLainProPerawat.setSelected(false);
        TpendidikanKes.setText("");
        TpndPerawatLain.setText("");
        
        chkDiet.setSelected(false);
        chkKonsulGiziRanap.setSelected(false);
        chkKonsulGiziRalan.setSelected(false);
        TpndNutrisionisLain.setText("");
        
        chkHak.setSelected(false);
        chkJam.setSelected(false);
        chkInfoKejadian.setSelected(false);
        TpndAdmisiLain.setText("");
        
        chkEdukasiLat.setSelected(false);
        chkPositioning.setSelected(false);
        chkLatihanAktif.setSelected(false);
        chkSimulasi.setSelected(false);
        TpndFisioterapiLain.setText("");
        
        chkNamaDan.setSelected(false);
        chkCaraAturan.setSelected(false);
        chkResikoEfek.setSelected(false);
        chkPenyimpanan.setSelected(false);
        TpndApotekerLain.setText("");
        
        TpndLainyaLain.setText("");

        if (cmbProfesi.getSelectedIndex() == 1) {
            dokterTRUE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 2 || cmbProfesi.getSelectedIndex() == 3) {
            dokterFALSE();
            perawatBidanTRUE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 4) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisTRUE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 5) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiTRUE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 6) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioTRUE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 7) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerTRUE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 8) {
            Tprofesi.setEnabled(true);
            Tprofesi.requestFocus();
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(true);
        } else {
            Tprofesi.setEnabled(false);
            chkDiagnosis.setEnabled(false);
            chkKondisi.setEnabled(false);
            chkTindakan.setEnabled(false);
            chkTataCara.setEnabled(false);
            chkManfaat.setEnabled(false);
            chkNamaOrang.setEnabled(false);
            chkKemungkinan.setEnabled(false);
            chkPrognosis.setEnabled(false);
            chkKemTdkTerduga.setEnabled(false);
            chkKemBila.setEnabled(false);
            
            chkPendidikanKes.setEnabled(false);
            chkHasilAsuhan.setEnabled(false);
            chkPenanganan.setEnabled(false);
            chkPerawatanLuka.setEnabled(false);
            chkAlatAlat.setEnabled(false);
            chkInformasi.setEnabled(false);
            chkKeamanan.setEnabled(false);
            chkProsedur.setEnabled(false);
            chkFarmaObat.setEnabled(false);
            chkFarmaInjek.setEnabled(false);
            chkFarmaSedasi.setEnabled(false);
            chkPerawatanLatihan.setEnabled(false);
            chkDistraksi.setEnabled(false);
            chkPengalihan.setEnabled(false);
            chkEtika.setEnabled(false);
            chkCaraBuang.setEnabled(false);
            chkTempat.setEnabled(false);
            chkCaraCuci.setEnabled(false);
            chkLainProPerawat.setEnabled(false);
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            
            chkDiet.setEnabled(false);
            chkKonsulGiziRanap.setEnabled(false);
            chkKonsulGiziRalan.setEnabled(false);
            TpndNutrisionisLain.setEnabled(false);
            
            chkHak.setEnabled(false);
            chkJam.setEnabled(false);
            chkInfoKejadian.setEnabled(false);
            TpndAdmisiLain.setEnabled(false);
            
            chkEdukasiLat.setEnabled(false);
            chkPositioning.setEnabled(false);
            chkLatihanAktif.setEnabled(false);
            chkSimulasi.setEnabled(false);
            TpndFisioterapiLain.setEnabled(false);
            
            chkNamaDan.setEnabled(false);
            chkCaraAturan.setEnabled(false);
            chkResikoEfek.setEnabled(false);
            chkPenyimpanan.setEnabled(false);
            TpndApotekerLain.setEnabled(false);            
            
            TpndLainyaLain.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProfesiActionPerformed

    private void TprofesiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprofesiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TpndLainyaLain.requestFocus();
        }
    }//GEN-LAST:event_TprofesiKeyPressed

    private void TpendidikanKesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpendidikanKesKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkHasilAsuhan.requestFocus();
        }
    }//GEN-LAST:event_TpendidikanKesKeyPressed

    private void chkPendidikanKesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPendidikanKesActionPerformed
        TpendidikanKes.setText("");
        if (chkPendidikanKes.isSelected() == true) {
            TpendidikanKes.setEnabled(true);
            TpendidikanKes.requestFocus();
        } else {
            TpendidikanKes.setEnabled(false);
        }
    }//GEN-LAST:event_chkPendidikanKesActionPerformed

    private void chkLainProPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainProPerawatActionPerformed
        TpndPerawatLain.setText("");
        if (chkLainProPerawat.isSelected() == true) {
            TpndPerawatLain.setEnabled(true);
            TpndPerawatLain.requestFocus();
        } else {
            TpndPerawatLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainProPerawatActionPerformed

    private void BtnPtgsPengedukasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgsPengedukasiActionPerformed
        pilihan = 0;
        pilihan = 2;
        
        akses.setform("RMPemberianInformasiEdukasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtgsPengedukasiActionPerformed

    private void BtnPtgsPemberiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgsPemberiActionPerformed
        pilihan = 0;
        pilihan = 1;
        
        akses.setform("RMPemberianInformasiEdukasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtgsPemberiActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TpndPerawatLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndPerawatLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTingkat.requestFocus();
        }
    }//GEN-LAST:event_TpndPerawatLainKeyPressed

    private void TpndNutrisionisLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndNutrisionisLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTingkat.requestFocus();
        }
    }//GEN-LAST:event_TpndNutrisionisLainKeyPressed

    private void TpndAdmisiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndAdmisiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkEdukasiLat.requestFocus();
        }
    }//GEN-LAST:event_TpndAdmisiLainKeyPressed

    private void TpndLainyaLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndLainyaLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbTingkat.requestFocus();
        }
    }//GEN-LAST:event_TpndLainyaLainKeyPressed

    private void TpndFisioterapiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndFisioterapiLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpndFisioterapiLainKeyPressed

    private void TpndApotekerLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpndApotekerLainKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TpndApotekerLainKeyPressed

    private void MnHapusTtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            if (akses.getadmin() == true || nipPengedukasi.equals(akses.getkode())) {
                x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan penerima edukasi mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    String ipGambar = "";
                    try {
                        //cek atau ping ip addres
                        ipGambar = "192.168.0.230";
                        InetAddress inet = InetAddress.getByName(ipGambar);

                        //ping sukses timeout 100 ms (0.1 detik)
                        if (inet.isReachable(100)) {
                            if (idFilePenerimaEdukasi.equals("")) {
                                JOptionPane.showMessageDialog(null, "Penerima edukasi pasien ini belum melakukan tanda tangan...!!!!");
                            } else {
                                if (Sequel.hapusFileTTD(idFilePenerimaEdukasi) == true) {
                                    Sequel.mengedit("penilaian_informasi_edukasi",
                                            "waktu_simpan='" + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString() + "'",
                                            "id_file_nm_penerima_edukasi=''");
                                    tampilPenilaian();
                                    emptTeksPenilaian();
                                }
                            }
                            //ping gagal
                        } else {
                            JOptionPane.showMessageDialog(null, "Koneksi ke server terputus...!!!!");
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                    }
                } else {
                    tampilPenilaian();
                    emptTeksPenilaian();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Maaf, tanda tangan penerima edukasi hanya bisa dihapus oleh " + TnmPetugas1.getText() + " ...!!");
                tampilPenilaian();
                emptTeksPenilaian();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdActionPerformed

    private void BtnTampilkanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTampilkanQrActionPerformed
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");        
        
        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRM.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw1.getText() + "','" + TNoRM1.getText() + "','"
                    + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                    blob.free();
                }
                
                BtnCloseIn1ActionPerformed(null);
                tampilPenilaian();
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRM.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn1ActionPerformed
        emptTeksPenilaian();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn1ActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%Pemberian Informasi Dan Edukasi%'") > 0) {
                bikinQR();
            } else {
                WindowNomorDokumenRM.setSize(737, 125);
                WindowNomorDokumenRM.setLocationRelativeTo(internalFrame1);
                WindowNomorDokumenRM.setVisible(true);
                
                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnBikinQrCodeActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMPemberianInformasiEdukasi");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void ChkRuanganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkRuanganActionPerformed
        if (ChkRuangan.isSelected() == true) {
            TrgRawat1.setText(ruangrawat);
        } else {
            if (tbPenilaian.getRowCount() == 0) {
                TrgRawat1.setText(ruangrawat);
            } else {
                TrgRawat1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 3).toString());
            }
        }
    }//GEN-LAST:event_ChkRuanganActionPerformed

    private void btnKamarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKamarActionPerformed
        initKamar();
        akses.setform("RMPemberianInformasiEdukasi");
        kamar.load();
        kamar.isCek();
        kamar.emptTeks();
        kamar.tampil();
        kamar.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        kamar.setLocationRelativeTo(internalFrame1);
        kamar.setVisible(true);
    }//GEN-LAST:event_btnKamarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemberianInformasiEdukasi dialog = new RMPemberianInformasiEdukasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnNotepad;
    private widget.Button BtnPrint;
    private widget.Button BtnPtgsPemberi;
    private widget.Button BtnPtgsPengedukasi;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    public widget.CekBox ChkRuangan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormAsesmen;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormInput1;
    private widget.PanelBiasa FormInput2;
    private widget.PanelBiasa FormInput3;
    private widget.Label LCount;
    private widget.Label LCount1;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnHapusTtd;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane ScrollTriase1;
    private widget.ScrollPane ScrollTriase2;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRM1;
    private widget.TextBox TNoRw;
    private widget.TextBox TNoRw1;
    private widget.TextBox TPasien;
    private widget.TextBox TPasien1;
    private widget.TextBox TPotensialLain;
    private javax.swing.JTabbedPane TabEdukasi;
    private widget.TextBox Tagama;
    private widget.TextBox TbhsLainya;
    private widget.TextBox Tdaerah;
    private widget.TextBox Tkapan;
    private widget.TextBox TnmPenerima;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmPetugas1;
    private widget.TextBox TpendidikanKes;
    private widget.TextBox Tperlu;
    private widget.TextBox Tpnd;
    private widget.TextArea TpndAdmisiLain;
    private widget.TextArea TpndApotekerLain;
    private widget.TextArea TpndFisioterapiLain;
    private widget.TextArea TpndLainyaLain;
    private widget.TextArea TpndNutrisionisLain;
    private widget.TextArea TpndPerawatLain;
    private widget.TextBox Tprofesi;
    private widget.TextBox TrgRawat;
    private widget.TextBox TrgRawat1;
    private widget.Tanggal TtglPemberian;
    private widget.Tanggal TtglPenilaian;
    private javax.swing.JDialog WindowNomorDokumenRM;
    private widget.Button btnKamar;
    public widget.CekBox chkAlatAlat;
    public widget.CekBox chkAlatBantu;
    public widget.CekBox chkAudio;
    public widget.CekBox chkBahasa;
    public widget.CekBox chkBhsIndonesia;
    public widget.CekBox chkBhsInggris;
    public widget.CekBox chkBhsLainya;
    public widget.CekBox chkCaraAturan;
    public widget.CekBox chkCaraBuang;
    public widget.CekBox chkCaraCuci;
    public widget.CekBox chkCemas;
    public widget.CekBox chkDaerah;
    public widget.CekBox chkDemonstrasi;
    public widget.CekBox chkDiagnosis;
    public widget.CekBox chkDiet;
    public widget.CekBox chkDistraksi;
    public widget.CekBox chkEdukasiLat;
    public widget.CekBox chkEmosi;
    public widget.CekBox chkEtika;
    public widget.CekBox chkFarmaInjek;
    public widget.CekBox chkFarmaObat;
    public widget.CekBox chkFarmaSedasi;
    public widget.CekBox chkHak;
    public widget.CekBox chkHasilAsuhan;
    public widget.CekBox chkHilangMemori;
    public widget.CekBox chkInfoKejadian;
    public widget.CekBox chkInformasi;
    public widget.CekBox chkJam;
    public widget.CekBox chkKeamanan;
    public widget.CekBox chkKeluarga;
    public widget.CekBox chkKemBila;
    public widget.CekBox chkKemTdkTerduga;
    public widget.CekBox chkKemungkinan;
    public widget.CekBox chkKognitif;
    public widget.CekBox chkKondisi;
    public widget.CekBox chkKonsulGiziRalan;
    public widget.CekBox chkKonsulGiziRanap;
    public widget.CekBox chkLainPnrmaPnd;
    public widget.CekBox chkLainProPerawat;
    public widget.CekBox chkLatihanAktif;
    public widget.CekBox chkLisan;
    public widget.CekBox chkManfaat;
    public widget.CekBox chkMasalah;
    public widget.CekBox chkMotivasi;
    public widget.CekBox chkNamaDan;
    public widget.CekBox chkNamaOrang;
    public widget.CekBox chkNutrisi;
    public widget.CekBox chkPasien;
    public widget.CekBox chkPenanganan;
    public widget.CekBox chkPendengaran;
    public widget.CekBox chkPendidikanKes;
    public widget.CekBox chkPengalihan;
    public widget.CekBox chkPengobatan;
    public widget.CekBox chkPenyimpanan;
    public widget.CekBox chkPerawatanLatihan;
    public widget.CekBox chkPerawatanLuka;
    public widget.CekBox chkPositioning;
    public widget.CekBox chkPotensialLain;
    public widget.CekBox chkPrognosis;
    public widget.CekBox chkProsedur;
    public widget.CekBox chkProses;
    public widget.CekBox chkResikoEfek;
    public widget.CekBox chkSecara;
    public widget.CekBox chkSimulasi;
    public widget.CekBox chkTataCara;
    public widget.CekBox chkTempat;
    public widget.CekBox chkTerapi;
    public widget.CekBox chkTidakAda;
    public widget.CekBox chkTidakDitemukan;
    public widget.CekBox chkTindakan;
    public widget.CekBox chkTulisan;
    public widget.CekBox chkVisual;
    private widget.ComboBox cmbBhsIndo;
    private widget.ComboBox cmbBhsInggris;
    private widget.ComboBox cmbBicara;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbEvaluasi;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbKepercayaan;
    private widget.ComboBox cmbKesediaan;
    private widget.ComboBox cmbLainLain;
    private widget.ComboBox cmbMenolakDilakukan;
    private widget.ComboBox cmbMenolakDilayani;
    private widget.ComboBox cmbMenolakPulang;
    private widget.ComboBox cmbMenolakVaksin;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbNilaiPasien;
    private widget.ComboBox cmbPenggunaanHerbal;
    private widget.ComboBox cmbPerlu;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbProfesi;
    private widget.ComboBox cmbPuasa;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbTidakMemakan;
    private widget.ComboBox cmbTingkat;
    private widget.ComboBox cmbVegetarian;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel146;
    private widget.Label jLabel147;
    private widget.Label jLabel148;
    private widget.Label jLabel149;
    private widget.Label jLabel150;
    private widget.Label jLabel151;
    private widget.Label jLabel152;
    private widget.Label jLabel153;
    private widget.Label jLabel154;
    private widget.Label jLabel155;
    private widget.Label jLabel156;
    private widget.Label jLabel157;
    private widget.Label jLabel158;
    private widget.Label jLabel159;
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel82;
    private widget.Label jLabel9;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi5;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.ScrollPane scrollPane18;
    private widget.ScrollPane scrollPane19;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbPemberian;
    private widget.Table tbPenilaian;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT pi.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tglLahir, DATE_FORMAT(pi.tanggal,'%d-%m-%Y') tglEdukasi, TIME_FORMAT(pi.jam,'%H:%i Wita') jamEdukasi, "
                    + "pg.nama nmPetugas FROM pemberian_informasi_edukasi pi INNER JOIN reg_periksa rp on rp.no_rawat=pi.no_rawat "
                    + "INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis INNER JOIN pegawai pg on pg.nik=pi.nip_petugas where "
                    + "pi.tanggal between ? and ? and pi.no_rawat like ? or "
                    + "pi.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "pi.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "pi.tanggal between ? and ? and pg.nama like ? or "
                    + "pi.tanggal between ? and ? and pi.ruang_rawat like ? order by pi.tanggal desc, pi.jam desc");
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
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglEdukasi"),
                        rs.getString("jamEdukasi"),
                        rs.getString("nmPetugas"),                        
                        rs.getString("bahasa"),
                        rs.getString("pendengaran"),
                        rs.getString("masalah_penglihatan"),
                        rs.getString("hilang_memori"),
                        rs.getString("tidak_ada_partisipasi"),
                        rs.getString("secara_fisiologi"),
                        rs.getString("tidak_ditemukan_hambatan"),
                        rs.getString("cemas"),
                        rs.getString("emosi"),
                        rs.getString("kognitif"),
                        rs.getString("motifasi_buruk"),
                        rs.getString("bicara"),
                        rs.getString("ket_kapan"),
                        rs.getString("bahasa_indonesia"),
                        rs.getString("indonesia"),
                        rs.getString("bahasa_daerah"),
                        rs.getString("ket_daerah"),
                        rs.getString("bahasa_inggris"),
                        rs.getString("inggris"),
                        rs.getString("bahasa_lainnya"),
                        rs.getString("ket_bahasa_lainnya"),
                        rs.getString("penerjemah"),
                        rs.getString("ket_penerjemah"),
                        rs.getString("nilai_pasien"),
                        rs.getString("kesediaan_menerima"),
                        rs.getString("proses_penyakit"),
                        rs.getString("pengobatan"),
                        rs.getString("alat_bantu_medis"),
                        rs.getString("lain_lain"),
                        rs.getString("ket_lainlain"),
                        rs.getString("terapi_obat"),
                        rs.getString("nutrisi"),
                        rs.getString("penggunaan_herbal"),
                        rs.getString("vegetarian"),
                        rs.getString("menolak_vaksinasi"),
                        rs.getString("kepercayaan_terhadap"),
                        rs.getString("puasa"),
                        rs.getString("menolak_dilakukan"),
                        rs.getString("menolak_pulang"),
                        rs.getString("menolak_dilayani"),
                        rs.getString("tidak_memakan"),
                        rs.getString("lain_lain_identifikasi"),
                        rs.getString("waktu_simpan"),
                        rs.getString("nip_petugas"),
                        rs.getString("tanggal"),
                        rs.getString("jam")
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
    
    public void tampilPenilaian() {        
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT pi.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(pi.tanggal,'%d-%m-%Y') tglNilai, TIME_FORMAT(pi.jam,'%H:%i Wita') jamNilai, "
                    + "if(pi.profesi='Lainnya',concat(pi.profesi,' (',pi.profesi_lainya,')'),pi.profesi) profesinya, pg.nama nmPetugas FROM penilaian_informasi_edukasi pi "
                    + "INNER JOIN reg_periksa rp on rp.no_rawat=pi.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on pg.nik=pi.nip_petugas where "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and p.no_rkm_medis like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and p.nm_pasien like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.penerima_pendidikan like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.metode like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.profesi like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.profesi_lainya like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.tingkat_pemahaman like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.evaluasi_respon like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pi.nm_penerima_edukasi like ? or "
                    + "pi.no_rawat ='" + TNoRw1.getText() + "' and pg.nama like ? order by pi.tanggal desc, pi.jam desc");
            try {
                ps1.setString(1, "%" + TCari1.getText().trim() + "%");
                ps1.setString(2, "%" + TCari1.getText().trim() + "%");
                ps1.setString(3, "%" + TCari1.getText().trim() + "%");
                ps1.setString(4, "%" + TCari1.getText().trim() + "%");                
                ps1.setString(5, "%" + TCari1.getText().trim() + "%");
                ps1.setString(6, "%" + TCari1.getText().trim() + "%");
                ps1.setString(7, "%" + TCari1.getText().trim() + "%");
                ps1.setString(8, "%" + TCari1.getText().trim() + "%");                
                ps1.setString(9, "%" + TCari1.getText().trim() + "%");
                ps1.setString(10, "%" + TCari1.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("no_rkm_medis"),
                        rs1.getString("nm_pasien"),
                        rs1.getString("ruang_rawat"),
                        rs1.getString("tglNilai"),
                        rs1.getString("jamNilai"),
                        rs1.getString("penerima_pendidikan"),
                        rs1.getString("metode"),
                        rs1.getString("profesinya"),
                        rs1.getString("tingkat_pemahaman"),
                        rs1.getString("evaluasi_respon"),
                        rs1.getString("nmPetugas"),
                        rs1.getString("nm_penerima_edukasi"),                        
                        rs1.getString("diagnosis"),
                        rs1.getString("kondisi_pasien"),
                        rs1.getString("tindakan"),
                        rs1.getString("tata_cara"),
                        rs1.getString("manfaat"),
                        rs1.getString("nama_orang"),
                        rs1.getString("kemungkinan_alternative"),
                        rs1.getString("prognosis"),
                        rs1.getString("kemungkinan_tdk_terduga"),
                        rs1.getString("kemungkinan_bila"),
                        rs1.getString("pendidikan_kesehatan"),
                        rs1.getString("ket_pendidikan"),
                        rs1.getString("hasil_asuhan"),
                        rs1.getString("penanganan"),
                        rs1.getString("perawatan_luka"),
                        rs1.getString("alat_alat"),
                        rs1.getString("informasi_pasien"),
                        rs1.getString("keamanan_penggunaan"),
                        rs1.getString("prosedur_tindakan"),
                        rs1.getString("farmakologi_obat"),
                        rs1.getString("farmakologi_injeksi"),
                        rs1.getString("farmakologi_sedasi"),
                        rs1.getString("perawatan_latihan"),
                        rs1.getString("distraksi"),
                        rs1.getString("pengalihan_perhatian"),
                        rs1.getString("cara_cuci"),
                        rs1.getString("etika_batuk"),
                        rs1.getString("cara_buang"),
                        rs1.getString("tempat_toileting"),
                        rs1.getString("lainya_perawat_bidan"),
                        rs1.getString("ket_lainya_perawat_bidan"),
                        rs1.getString("diet"),
                        rs1.getString("konsul_gizi_ranap"),
                        rs1.getString("konsul_gizi_ralan"),
                        rs1.getString("ket_lainya_nutrisionis"),
                        rs1.getString("hak_dan"),
                        rs1.getString("jam_konsultasi"),
                        rs1.getString("informasi_kejadian"),
                        rs1.getString("ket_lainya_admisi"),
                        rs1.getString("edukasi_lain_lanjutan"),
                        rs1.getString("nip_petugas"),
                        rs1.getString("nm_penerima_edukasi"),
                        rs1.getString("waktu_simpan"),
                        rs1.getString("tanggal"),
                        rs1.getString("jam"),
                        rs1.getString("profesi"),
                        rs1.getString("profesi_lainya"),                        
                        rs1.getString("audio"),
                        rs1.getString("demonstrasi"),
                        rs1.getString("lisan"),
                        rs1.getString("tulisan"),
                        rs1.getString("visual"),
                        rs1.getString("pasien"),
                        rs1.getString("keluarga"),
                        rs1.getString("lainPenerimaPnd"),
                        rs1.getString("edukasi_latihan"),
                        rs1.getString("positioning"),
                        rs1.getString("latihan_aktif"),
                        rs1.getString("simulasi_bicara"),
                        rs1.getString("ket_lainya_fisioterapi"),
                        rs1.getString("nama_dan"),
                        rs1.getString("cara_aturan"),
                        rs1.getString("resiko_efek"),
                        rs1.getString("penyimpanan_obat"),
                        rs1.getString("ket_lainya_apoteker"),
                        rs1.getString("id_file_nm_penerima_edukasi")
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
        LCount1.setText("" + tabMode1.getRowCount());
    }
    
    public void emptTeks(){
        TtglPemberian.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        chkBahasa.setSelected(false);
        chkPendengaran.setSelected(false);
        chkMasalah.setSelected(false);
        chkHilangMemori.setSelected(false);
        chkTidakAda.setSelected(false);
        chkSecara.setSelected(false);
        chkTidakDitemukan.setSelected(false);
        chkCemas.setSelected(false);
        chkEmosi.setSelected(false);
        chkKognitif.setSelected(false);
        chkMotivasi.setSelected(false);
        cmbBicara.setSelectedIndex(0);        
        Tkapan.setText("");
        Tkapan.setEnabled(false);
        chkBhsIndonesia.setSelected(false);
        cmbBhsIndo.setSelectedIndex(0);
        cmbBhsIndo.setEnabled(false);
        chkBhsInggris.setSelected(false);
        cmbBhsInggris.setSelectedIndex(0);
        cmbBhsInggris.setEnabled(false);
        chkDaerah.setSelected(false);
        Tdaerah.setText("");
        Tdaerah.setEnabled(false);
        chkBhsLainya.setSelected(false);
        TbhsLainya.setText("");
        TbhsLainya.setEnabled(false);
        cmbPerlu.setSelectedIndex(0);
        Tperlu.setText("");
        Tperlu.setEnabled(false);
        cmbNilaiPasien.setSelectedIndex(0);
        cmbKesediaan.setSelectedIndex(0);
        chkProses.setSelected(false);
        chkPengobatan.setSelected(false);
        chkAlatBantu.setSelected(false);
        chkTerapi.setSelected(false);
        chkNutrisi.setSelected(false);
        chkPotensialLain.setSelected(false);
        TPotensialLain.setText("");
        TPotensialLain.setEnabled(false);
        cmbPenggunaanHerbal.setSelectedIndex(0);
        cmbVegetarian.setSelectedIndex(0);
        cmbMenolakVaksin.setSelectedIndex(0);
        cmbKepercayaan.setSelectedIndex(0);
        cmbPuasa.setSelectedIndex(0);
        cmbMenolakDilakukan.setSelectedIndex(0);
        cmbMenolakPulang.setSelectedIndex(0);
        cmbMenolakDilayani.setSelectedIndex(0);
        cmbTidakMemakan.setSelectedIndex(0);
        cmbLainLain.setSelectedIndex(0);
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRw1.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TNoRM1.setText(TNoRM.getText());
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TPasien1.setText(TPasien.getText());
        TrgRawat.setText(rgrawat);
        TrgRawat1.setText(rgrawat);
        ruangrawat = rgrawat;
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        Tpnd.setText(Sequel.cariIsi("select pnd from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        Tagama.setText(Sequel.cariIsi("select agama from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TCari.setText(norwt);
        TCari1.setText("");
    }
    
    public void isCek() {
        if (akses.getcppt() == true || akses.getbpjs_sep() == true || akses.getadmin() == true) {            
            BtnSimpan.setEnabled(true);
            BtnHapus.setEnabled(true);
            BtnPrint.setEnabled(true);
            BtnEdit.setEnabled(true);
        } else {
            BtnSimpan.setEnabled(false);
            BtnHapus.setEnabled(false);
            BtnPrint.setEnabled(false);
            BtnEdit.setEnabled(false);
        }
        
        if (akses.getjml2() >= 1) {
            nipPemberi = akses.getkode();
            nipPengedukasi = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPemberi);
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas1, nipPengedukasi);
            if (TnmPetugas.getText().equals("")) {
                nipPemberi = "-";
                TnmPetugas.setText("-");
                nipPengedukasi = "-";
                TnmPetugas1.setText("-");
            }
        }  
    }

    private void getData() {
        if (TabEdukasi.getSelectedIndex() == 0) {
            variabelBersihInformasiEdukasi();
            if (tbPemberian.getSelectedRow() != -1) {
                TCari.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                TNoRw.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                TNoRM.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 1).toString());
                TPasien.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 2).toString());
                TrgRawat.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 5).toString());
                
                TNoRw1.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                TNoRM1.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 1).toString());
                TPasien1.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 2).toString());
                TrgRawat1.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 5).toString());
                
                TnmPetugas.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 8).toString());
                bahasa = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 9).toString();
                pendengaran = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 10).toString();
                masalahPenglihatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 11).toString();
                hilangMemori = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 12).toString();
                tidakAdaPartisipasi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 13).toString();
                secaraFisiologi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 14).toString();
                tidakDitemukanHambatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 15).toString();
                cemas = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 16).toString();
                emosi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 17).toString();
                kognitif = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 18).toString();
                motifasiBuruk = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 19).toString();
                cmbBicara.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 20).toString());
                Tkapan.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 21).toString());
                bahasaIndonesia = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 22).toString();
                cmbBhsIndo.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 23).toString());
                bahasaDaerah = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 24).toString();
                Tdaerah.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 25).toString());
                bahasaInggris = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 26).toString();
                cmbBhsInggris.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 27).toString());
                bahasaLainnya = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 28).toString();
                TbhsLainya.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 29).toString());
                cmbPerlu.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 30).toString());
                Tperlu.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 31).toString());
                Tpnd.setText(Sequel.cariIsi("select pnd from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                Tagama.setText(Sequel.cariIsi("select agama from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
                cmbNilaiPasien.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 32).toString());
                cmbKesediaan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 33).toString());
                prosesPenyakit = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 34).toString();
                pengobatan = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 35).toString();
                alatBantuMedis = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 36).toString();
                lainLain = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 37).toString();
                TPotensialLain.setText(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 38).toString());
                terapiObat = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 39).toString();
                nutrisi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 40).toString();
                cmbPenggunaanHerbal.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 41).toString());
                cmbVegetarian.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 42).toString());
                cmbMenolakVaksin.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 43).toString());
                cmbKepercayaan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 44).toString());
                cmbPuasa.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 45).toString());
                cmbMenolakDilakukan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 46).toString());
                cmbMenolakPulang.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 47).toString());
                cmbMenolakDilayani.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 48).toString());
                cmbTidakMemakan.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 49).toString());
                cmbLainLain.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 50).toString());
                nipPemberi = tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 52).toString();                
                Valid.SetTgl(TtglPemberian, tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 53).toString());
                cmbJam.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 54).toString().substring(0, 2));
                cmbMnt.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 54).toString().substring(3, 5));
                cmbDtk.setSelectedItem(tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 54).toString().substring(6, 8));
                dataCekPemberian();
            }
        } else {
            variabelBersihPenilaian();
            if (tbPenilaian.getSelectedRow() != -1) {
                TNoRw1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString());
                TNoRM1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 1).toString());
                TPasien1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 2).toString());
                TrgRawat1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 3).toString());
                Valid.SetTgl(TtglPenilaian, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 56).toString());
                cmbJam1.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString().substring(0, 2));
                cmbMnt1.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString().substring(3, 5));
                cmbDtk1.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString().substring(6, 8));
                penerimaPnd = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 6).toString();
                metode = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 7).toString();
                cmbProfesi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 58).toString());
                Tprofesi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 59).toString());
                nipPengedukasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 53).toString();
                TnmPetugas1.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 11).toString());
                
                diagnosis = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 13).toString();
                kondisiPasien = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 14).toString();
                tindakan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 15).toString();
                tataCara = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 16).toString();
                manfaat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 17).toString();
                namaOrang = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 18).toString();
                kemungkinanAlternative = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 19).toString();
                prognosis = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 20).toString();
                kemungkinanTdkTerduga = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 21).toString();
                kemungkinanBila = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 22).toString();
                pendidikanKesehatan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 23).toString();
                TpendidikanKes.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 24).toString());
                hasilAsuhan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 25).toString();
                penanganan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 26).toString();
                perawatanLuka = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 27).toString();
                alatAlat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 28).toString();
                informasiPasien = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 29).toString();
                keamananPenggunaan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 30).toString();
                prosedurTindakan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 31).toString();
                farmakologiObat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 32).toString();
                farmakologiInjeksi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 33).toString();
                farmakologiSedasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 34).toString();
                perawatanLatihan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 35).toString();
                distraksi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 36).toString();
                pengalihanPerhatian = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 37).toString();
                caraCuci = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 38).toString();
                etikaBatuk = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 39).toString();
                caraBuang = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 40).toString();
                tempatToileting = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 41).toString();
                lainyaPerawatBidan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 42).toString();
                TpndPerawatLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 43).toString());
                diet = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 44).toString();
                konsulGiziRanap = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 45).toString();
                konsulGiziRalan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 46).toString();
                TpndNutrisionisLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 47).toString());
                hakDan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 48).toString();
                jamKonsultasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 49).toString();
                informasiKejadian = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 50).toString();
                TpndAdmisiLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 51).toString());
                TpndLainyaLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 52).toString());
                cmbTingkat.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 9).toString());
                cmbEvaluasi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString());
                TnmPenerima.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 12).toString());                
                audio = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 60).toString();
                demonstrasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 61).toString();
                lisan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 62).toString();
                tulisan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 63).toString();
                visual = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 64).toString();                
                pasien = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 65).toString();
                keluarga = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 66).toString();
                lainPenerimaPnd = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 67).toString();
                edukasiLat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 68).toString();
                positioning = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 69).toString();
                latihanAktif = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 70).toString();
                simulasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 71).toString();
                TpndFisioterapiLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 72).toString());
                namaDan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 73).toString();
                caraAturan = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 74).toString();
                resikoEfek = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 75).toString();
                penyimpananObat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 76).toString();
                TpndApotekerLain.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 77).toString());
                idFilePenerimaEdukasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 78).toString();
                dataCekPenilaian();
                tampilTTD();
            }         
        }
    }
    
    private void cekDataPemberian() {
        if (chkBahasa.isSelected() == true) {
            bahasa = "ya";
        } else {
            bahasa = "tidak";
        }
        
        if (chkPendengaran.isSelected() == true) {
            pendengaran = "ya";
        } else {
            pendengaran = "tidak";
        }
        
        if (chkMasalah.isSelected() == true) {
            masalahPenglihatan = "ya";
        } else {
            masalahPenglihatan = "tidak";
        }
        
        if (chkHilangMemori.isSelected() == true) {
            hilangMemori = "ya";
        } else {
            hilangMemori = "tidak";
        }
        
        if (chkTidakAda.isSelected() == true) {
            tidakAdaPartisipasi = "ya";
        } else {
            tidakAdaPartisipasi = "tidak";
        }
        
        if (chkSecara.isSelected() == true) {
            secaraFisiologi = "ya";
        } else {
            secaraFisiologi = "tidak";
        }
        
        if (chkTidakDitemukan.isSelected() == true) {
            tidakDitemukanHambatan = "ya";
        } else {
            tidakDitemukanHambatan = "tidak";
        }
        
        if (chkCemas.isSelected() == true) {
            cemas = "ya";
        } else {
            cemas = "tidak";
        }
        
        if (chkEmosi.isSelected() == true) {
            emosi = "ya";
        } else {
            emosi = "tidak";
        }
        
        if (chkKognitif.isSelected() == true) {
            kognitif = "ya";
        } else {
            kognitif = "tidak";
        }
        
        if (chkMotivasi.isSelected() == true) {
            motifasiBuruk = "ya";
        } else {
            motifasiBuruk = "tidak";
        }
        
        if (chkBhsIndonesia.isSelected() == true) {
            bahasaIndonesia = "ya";
        } else {
            bahasaIndonesia = "tidak";
        }
        
        if (chkDaerah.isSelected() == true) {
            bahasaDaerah = "ya";
        } else {
            bahasaDaerah = "tidak";
        }
        
        if (chkBhsInggris.isSelected() == true) {
            bahasaInggris = "ya";
        } else {
            bahasaInggris = "tidak";
        }
        
        if (chkBhsLainya.isSelected() == true) {
            bahasaLainnya = "ya";
        } else {
            bahasaLainnya = "tidak";
        }
        
        if (chkProses.isSelected() == true) {
            prosesPenyakit = "ya";
        } else {
            prosesPenyakit = "tidak";
        }
        
        if (chkPengobatan.isSelected() == true) {
            pengobatan = "ya";
        } else {
            pengobatan = "tidak";
        }
        
        if (chkAlatBantu.isSelected() == true) {
            alatBantuMedis = "ya";
        } else {
            alatBantuMedis = "tidak";
        }
        
        if (chkPotensialLain.isSelected() == true) {
            lainLain = "ya";
        } else {
            lainLain = "tidak";
        }
        
        if (chkTerapi.isSelected() == true) {
            terapiObat = "ya";
        } else {
            terapiObat = "tidak";
        }
        
        if (chkNutrisi.isSelected() == true) {
            nutrisi = "ya";
        } else {
            nutrisi = "tidak";
        }
    }
    
    private void dataCekPemberian() {
        if (bahasa.equals("ya")) {
            chkBahasa.setSelected(true);
        } else {
            chkBahasa.setSelected(false);
        }
        
        if (pendengaran.equals("ya")) {
            chkPendengaran.setSelected(true);
        } else {
            chkPendengaran.setSelected(false);
        }
        
        if (masalahPenglihatan.equals("ya")) {
            chkMasalah.setSelected(true);
        } else {
            chkMasalah.setSelected(false);
        }
        
        if (hilangMemori.equals("ya")) {
            chkHilangMemori.setSelected(true);
        } else {
            chkHilangMemori.setSelected(false);
        }
        
        if (tidakAdaPartisipasi.equals("ya")) {
            chkTidakAda.setSelected(true);
        } else {
            chkTidakAda.setSelected(false);
        }
        
        if (secaraFisiologi.equals("ya")) {
            chkSecara.setSelected(true);
        } else {
            chkSecara.setSelected(false);
        }
        
        if (tidakDitemukanHambatan.equals("ya")) {
            chkTidakDitemukan.setSelected(true);
        } else {
            chkTidakDitemukan.setSelected(false);
        }
        
        if (cemas.equals("ya")) {
            chkCemas.setSelected(true);
        } else {
            chkCemas.setSelected(false);
        }
        
        if (emosi.equals("ya")) {
            chkEmosi.setSelected(true);
        } else {
            chkEmosi.setSelected(false);
        }
        
        if (kognitif.equals("ya")) {
            chkKognitif.setSelected(true);
        } else {
            chkKognitif.setSelected(false);
        }
        
        if (motifasiBuruk.equals("ya")) {
            chkMotivasi.setSelected(true);
        } else {
            chkMotivasi.setSelected(false);
        }
        
        if (bahasaIndonesia.equals("ya")) {
            chkBhsIndonesia.setSelected(true);
            cmbBhsIndo.setEnabled(true);
        } else {
            chkBhsIndonesia.setSelected(false);
            cmbBhsIndo.setEnabled(false);
        }
        
        if (bahasaDaerah.equals("ya")) {
            chkDaerah.setSelected(true);
            Tdaerah.setEnabled(true);
        } else {
            chkDaerah.setSelected(false);
            Tdaerah.setEnabled(false);
        }
        
        if (bahasaInggris.equals("ya")) {
            chkBhsInggris.setSelected(true);
            cmbBhsInggris.setEnabled(true);
        } else {
            chkBhsInggris.setSelected(false);
            cmbBhsInggris.setEnabled(false);
        }
        
        if (bahasaLainnya.equals("ya")) {
            chkBhsLainya.setSelected(true);
            TbhsLainya.setEnabled(true);
        } else {
            chkBhsLainya.setSelected(false);
            TbhsLainya.setEnabled(false);
        }
        
        if (prosesPenyakit.equals("ya")) {
            chkProses.setSelected(true);
        } else {
            chkProses.setSelected(false);
        }
        
        if (pengobatan.equals("ya")) {
            chkPengobatan.setSelected(true);
        } else {
            chkPengobatan.setSelected(false);
        }
        
        if (alatBantuMedis.equals("ya")) {
            chkAlatBantu.setSelected(true);
        } else {
            chkAlatBantu.setSelected(false);
        }
        
        if (lainLain.equals("ya")) {
            chkPotensialLain.setSelected(true);
            TPotensialLain.setEnabled(true);
        } else {
            chkPotensialLain.setSelected(false);
            TPotensialLain.setEnabled(false);
        }
        
        if (terapiObat.equals("ya")) {
            chkTerapi.setSelected(true);
        } else {
            chkTerapi.setSelected(false);
        }
        
        if (nutrisi.equals("ya")) {
            chkNutrisi.setSelected(true);
        } else {
            chkNutrisi.setSelected(false);
        }
    }
    
    private void variabelBersihInformasiEdukasi() {
        nipPemberi = "";
        bahasa = "";
        pendengaran = "";
        masalahPenglihatan = "";
        hilangMemori = "";
        tidakAdaPartisipasi = "";
        secaraFisiologi = "";
        tidakDitemukanHambatan = "";
        cemas = "";
        emosi = "";
        kognitif = "";
        motifasiBuruk = "";
        bahasaIndonesia = "";
        bahasaDaerah = "";
        bahasaInggris = "";
        bahasaLainnya = "";
        prosesPenyakit = "";
        pengobatan = "";
        alatBantuMedis = "";
        lainLain = "";
        terapiObat = "";
        nutrisi = "";
    }
    
    private void variabelBersihPenilaian() {
        penerimaPnd = "";
        pasien = "";
        keluarga = "";
        lainPenerimaPnd = "";
        metode = "";
        audio = "";
        demonstrasi = "";
        lisan = "";
        tulisan = "";
        visual = "";        
        nipPengedukasi = "";
        diagnosis = "";
        kondisiPasien = "";
        tindakan = "";
        tataCara = "";
        manfaat = "";
        namaOrang = "";
        kemungkinanAlternative = "";
        prognosis = "";
        kemungkinanTdkTerduga = "";
        kemungkinanBila = "";
        pendidikanKesehatan = "";
        hasilAsuhan = "";
        penanganan = "";
        perawatanLuka = "";
        alatAlat = "";
        informasiPasien = "";
        keamananPenggunaan = "";
        prosedurTindakan = "";
        farmakologiObat = "";
        farmakologiInjeksi = "";
        farmakologiSedasi = "";
        perawatanLatihan = "";
        distraksi = "";
        pengalihanPerhatian = "";
        caraCuci = "";
        etikaBatuk = "";
        caraBuang = "";
        tempatToileting = "";
        lainyaPerawatBidan = "";
        diet = "";
        konsulGiziRanap = "";
        konsulGiziRalan = "";
        hakDan = "";
        jamKonsultasi = "";
        informasiKejadian = "";
        edukasiLat = "";
        positioning = "";
        latihanAktif = "";
        simulasi = "";
        namaDan = "";
        caraAturan = "";
        resikoEfek = "";
        penyimpananObat = "";
        idFilePenerimaEdukasi = "";
        idParameterTtd = "";
        ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
    }
    
    public void setTampil(){
       TabEdukasi.setSelectedIndex(1);
       tampil();
    }
    
    private void simpanInformasiEdukasi() {
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekDataPemberian();
            if (Sequel.menyimpantf("pemberian_informasi_edukasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 48, new String[]{
                TNoRw.getText(), TrgRawat.getText(), bahasa, pendengaran, masalahPenglihatan, hilangMemori, tidakAdaPartisipasi, secaraFisiologi, tidakDitemukanHambatan,
                cemas, emosi, kognitif, motifasiBuruk, cmbBicara.getSelectedItem().toString(), Tkapan.getText(), bahasaIndonesia, cmbBhsIndo.getSelectedItem().toString(),
                bahasaDaerah, Tdaerah.getText(), bahasaInggris, cmbBhsInggris.getSelectedItem().toString(), bahasaLainnya, TbhsLainya.getText(), cmbPerlu.getSelectedItem().toString(),
                Tperlu.getText(), cmbNilaiPasien.getSelectedItem().toString(), cmbKesediaan.getSelectedItem().toString(), prosesPenyakit, pengobatan, alatBantuMedis,
                lainLain, TPotensialLain.getText(), terapiObat, nutrisi, cmbPenggunaanHerbal.getSelectedItem().toString(), cmbVegetarian.getSelectedItem().toString(),
                cmbMenolakVaksin.getSelectedItem().toString(), cmbKepercayaan.getSelectedItem().toString(), cmbPuasa.getSelectedItem().toString(), cmbMenolakDilakukan.getSelectedItem().toString(),
                cmbMenolakPulang.getSelectedItem().toString(), cmbMenolakDilayani.getSelectedItem().toString(), cmbTidakMemakan.getSelectedItem().toString(),
                cmbLainLain.getSelectedItem().toString(), Sequel.cariIsi("select now()"), nipPemberi, Valid.SetTgl(TtglPemberian.getSelectedItem() + ""),
                cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem()
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Pemberian Informasi Dan Edukasi", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        }
    }
    
    private void simpanPenilaianEdukasi() {
        if (TNoRw1.getText().trim().equals("")) {
            Valid.textKosong(TNoRw1, "Pasien");
        } else if (nipPengedukasi.equals("") || nipPengedukasi.equals("-") || nipPengedukasi.equals("--")) {
            JOptionPane.showMessageDialog(rootPane, "Maaf, nama pemberi edukasi harus diisi/dipilih salah satu dengan benar..!");
            BtnPtgsPengedukasi.requestFocus();
        } else {
            cekDataPenilaian();
            if (Sequel.menyimpantf("penilaian_informasi_edukasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 72, new String[]{
                        TNoRw1.getText(), Valid.SetTgl(TtglPenilaian.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                        "-", "-", cmbProfesi.getSelectedItem().toString(), Tprofesi.getText(), cmbTingkat.getSelectedItem().toString(), cmbEvaluasi.getSelectedItem().toString(), diagnosis,
                        kondisiPasien, tindakan, tataCara, manfaat, namaOrang, kemungkinanAlternative, prognosis, kemungkinanTdkTerduga, kemungkinanBila, pendidikanKesehatan,
                        TpendidikanKes.getText(), hasilAsuhan, penanganan, perawatanLuka, alatAlat, informasiPasien, keamananPenggunaan, prosedurTindakan, farmakologiObat, farmakologiInjeksi,
                        farmakologiSedasi, perawatanLatihan, distraksi, pengalihanPerhatian, caraCuci, etikaBatuk, caraBuang, tempatToileting, lainyaPerawatBidan, TpndPerawatLain.getText(),
                        diet, konsulGiziRanap, konsulGiziRalan, TpndNutrisionisLain.getText(), hakDan, jamKonsultasi, informasiKejadian, TpndAdmisiLain.getText(), TpndLainyaLain.getText(),
                        nipPengedukasi, TnmPenerima.getText(), Sequel.cariIsi("select now()"), audio, demonstrasi, lisan, tulisan, visual, pasien, keluarga, lainPenerimaPnd, edukasiLat,
                        positioning, latihanAktif, simulasi, TpndFisioterapiLain.getText(), namaDan, caraAturan, resikoEfek, penyimpananObat, TpndApotekerLain.getText(), "", TrgRawat1.getText()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw1.getText(), "Penilaian Pemberian Pendidikan Kesehatan", "Simpan");
                tampilPenilaian();
                emptTeksPenilaian();
            }
        }
    }
    
    private void gantiInformasiEdukasi() {
        if (tbPemberian.getSelectedRow() > -1) {
            cekDataPemberian();
            if (Sequel.mengedittf("pemberian_informasi_edukasi", "no_rawat=?", "bahasa=?, pendengaran=?, masalah_penglihatan=?, hilang_memori=?, "
                    + "tidak_ada_partisipasi=?, secara_fisiologi=?, tidak_ditemukan_hambatan=?, cemas=?, emosi=?, kognitif=?, motifasi_buruk=?, bicara=?, ket_kapan=?, "
                    + "bahasa_indonesia=?, indonesia=?, bahasa_daerah=?, ket_daerah=?, bahasa_inggris=?, inggris=?, bahasa_lainnya=?, ket_bahasa_lainnya=?, penerjemah=?, "
                    + "ket_penerjemah=?, nilai_pasien=?, kesediaan_menerima=?, proses_penyakit=?, pengobatan=?, alat_bantu_medis=?, lain_lain=?, ket_lainlain=?, "
                    + "terapi_obat=?, nutrisi=?, penggunaan_herbal=?, vegetarian=?, menolak_vaksinasi=?, kepercayaan_terhadap=?, puasa=?, menolak_dilakukan=?, "
                    + "menolak_pulang=?, menolak_dilayani=?, tidak_memakan=?, lain_lain_identifikasi=?, nip_petugas=?, tanggal=?, jam=?", 46, new String[]{
                        bahasa, pendengaran, masalahPenglihatan, hilangMemori, tidakAdaPartisipasi, secaraFisiologi, tidakDitemukanHambatan,
                        cemas, emosi, kognitif, motifasiBuruk, cmbBicara.getSelectedItem().toString(), Tkapan.getText(), bahasaIndonesia,
                        cmbBhsIndo.getSelectedItem().toString(), bahasaDaerah, Tdaerah.getText(), bahasaInggris, cmbBhsInggris.getSelectedItem().toString(),
                        bahasaLainnya, TbhsLainya.getText(), cmbPerlu.getSelectedItem().toString(), Tperlu.getText(), cmbNilaiPasien.getSelectedItem().toString(),
                        cmbKesediaan.getSelectedItem().toString(), prosesPenyakit, pengobatan, alatBantuMedis, lainLain, TPotensialLain.getText(), terapiObat, nutrisi,
                        cmbPenggunaanHerbal.getSelectedItem().toString(), cmbVegetarian.getSelectedItem().toString(), cmbMenolakVaksin.getSelectedItem().toString(),
                        cmbKepercayaan.getSelectedItem().toString(), cmbPuasa.getSelectedItem().toString(), cmbMenolakDilakukan.getSelectedItem().toString(),
                        cmbMenolakPulang.getSelectedItem().toString(), cmbMenolakDilayani.getSelectedItem().toString(), cmbTidakMemakan.getSelectedItem().toString(),
                        cmbLainLain.getSelectedItem().toString(), nipPemberi, Valid.SetTgl(TtglPemberian.getSelectedItem() + ""),
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString()
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Pemberian Informasi Dan Edukasi", "Ganti");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void gantiPenilaianEdukasi() {
        if (tbPenilaian.getSelectedRow() > -1) {
            if (nipPengedukasi.equals("") || nipPengedukasi.equals("-") || nipPengedukasi.equals("--")) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, nama pemberi edukasi harus diisi/dipilih salah satu dengan benar..!");
                BtnPtgsPengedukasi.requestFocus();
            } else if (Sequel.cariInteger("select count(-1) from ttd_erm_keluarga_pasien where id_file='" + idFilePenerimaEdukasi + "'") > 0
                    && !tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 12).toString().equals(TnmPenerima.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, penerima edukasi pasien sudah bertanda tangan, nama tidak bisa dirubah, kecuali       \n"
                        + "tanda tangan yang sudah tersimpan dihapus dulu, lalu lakukan tanda tangan ulang ..!!");
            } else {
                cekDataPenilaian();
                if (Sequel.mengedittf("penilaian_informasi_edukasi", "waktu_simpan=?", "tanggal=?, jam=?, penerima_pendidikan=?, metode=?, profesi=?, "
                        + "profesi_lainya=?, tingkat_pemahaman=?, evaluasi_respon=?, diagnosis=?, kondisi_pasien=?, tindakan=?, tata_cara=?, manfaat=?, nama_orang=?, "
                        + "kemungkinan_alternative=?, prognosis=?, kemungkinan_tdk_terduga=?, kemungkinan_bila=?, pendidikan_kesehatan=?, ket_pendidikan=?, hasil_asuhan=?, "
                        + "penanganan=?, perawatan_luka=?, alat_alat=?, informasi_pasien=?, keamanan_penggunaan=?, prosedur_tindakan=?, farmakologi_obat=?, farmakologi_injeksi=?, "
                        + "farmakologi_sedasi=?, perawatan_latihan=?, distraksi=?, pengalihan_perhatian=?, cara_cuci=?, etika_batuk=?, cara_buang=?, tempat_toileting=?, "
                        + "lainya_perawat_bidan=?, ket_lainya_perawat_bidan=?, diet=?, konsul_gizi_ranap=?, konsul_gizi_ralan=?, ket_lainya_nutrisionis=?, hak_dan=?, "
                        + "jam_konsultasi=?, informasi_kejadian=?, ket_lainya_admisi=?, edukasi_lain_lanjutan=?, nip_petugas=?, nm_penerima_edukasi=?, audio=?, demonstrasi=?, "
                        + "lisan=?, tulisan=?, visual=?, pasien=?, keluarga=?, lainPenerimaPnd=?, edukasi_latihan=?, positioning=?, latihan_aktif=?, simulasi_bicara=?, "
                        + "ket_lainya_fisioterapi=?, nama_dan=?, cara_aturan=?, resiko_efek=?, penyimpanan_obat=?, ket_lainya_apoteker=?, ruang_rawat=?", 70, new String[]{
                            Valid.SetTgl(TtglPenilaian.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                            "-", "-", cmbProfesi.getSelectedItem().toString(), Tprofesi.getText(), cmbTingkat.getSelectedItem().toString(),
                            cmbEvaluasi.getSelectedItem().toString(), diagnosis, kondisiPasien, tindakan, tataCara, manfaat, namaOrang, kemungkinanAlternative, prognosis,
                            kemungkinanTdkTerduga, kemungkinanBila, pendidikanKesehatan, TpendidikanKes.getText(), hasilAsuhan, penanganan, perawatanLuka, alatAlat, informasiPasien,
                            keamananPenggunaan, prosedurTindakan, farmakologiObat, farmakologiInjeksi, farmakologiSedasi, perawatanLatihan, distraksi, pengalihanPerhatian, caraCuci,
                            etikaBatuk, caraBuang, tempatToileting, lainyaPerawatBidan, TpndPerawatLain.getText(), diet, konsulGiziRanap, konsulGiziRalan, TpndNutrisionisLain.getText(),
                            hakDan, jamKonsultasi, informasiKejadian, TpndAdmisiLain.getText(), TpndLainyaLain.getText(), nipPengedukasi, TnmPenerima.getText(), audio, demonstrasi,
                            lisan, tulisan, visual, pasien, keluarga, lainPenerimaPnd, edukasiLat, positioning, latihanAktif, simulasi, TpndFisioterapiLain.getText(), namaDan,
                            caraAturan, resikoEfek, penyimpananObat, TpndApotekerLain.getText(), TrgRawat1.getText(),
                            tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw1.getText(), "Penilaian Pemberian Pendidikan Kesehatan", "Ganti");
                    tampilPenilaian();
                    emptTeksPenilaian();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }

    private void hapusInformasiEdukasi() {
        if (tbPemberian.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin semua data pemberian informasi & edukasi termasuk penilaiannya mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pemberian_informasi_edukasi where no_rawat=?", 1, new String[]{
                    tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString()
                }) == true) {
                    Sequel.meghapus("penilaian_informasi_edukasi", "no_rawat",
                            tbPemberian.getValueAt(tbPemberian.getSelectedRow(), 0).toString());
                    if (!idFilePenerimaEdukasi.equals("")) {
                        Sequel.hapusSemuaTtd(idFilePenerimaEdukasi);
                    }
                    
                    tampil();
                    emptTeks();
                    emptTeksPenilaian();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
                emptTeksPenilaian();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }
    
    private void hapusPenilaianEdukasi() {
        if (tbPenilaian.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data penilaian informasi & edukasi mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from penilaian_informasi_edukasi where waktu_simpan=?", 1, new String[]{
                    tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString()
                }) == true) {
                    if (!idFilePenerimaEdukasi.equals("")) {
                        Sequel.hapusSemuaTtd(idFilePenerimaEdukasi);
                    }
                    
                    tampilPenilaian();
                    emptTeksPenilaian();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampilPenilaian();
                emptTeksPenilaian();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }

    private void dokterTRUE() {
        chkDiagnosis.setEnabled(true);
        chkKondisi.setEnabled(true);
        chkTindakan.setEnabled(true);
        chkTataCara.setEnabled(true);
        chkManfaat.setEnabled(true);
        chkNamaOrang.setEnabled(true);
        chkKemungkinan.setEnabled(true);
        chkPrognosis.setEnabled(true);
        chkKemTdkTerduga.setEnabled(true);
        chkKemBila.setEnabled(true);
    }

    private void dokterFALSE() {
        chkDiagnosis.setEnabled(false);
        chkKondisi.setEnabled(false);
        chkTindakan.setEnabled(false);
        chkTataCara.setEnabled(false);
        chkManfaat.setEnabled(false);
        chkNamaOrang.setEnabled(false);
        chkKemungkinan.setEnabled(false);
        chkPrognosis.setEnabled(false);
        chkKemTdkTerduga.setEnabled(false);
        chkKemBila.setEnabled(false);
    }
    
    private void perawatBidanTRUE() {
        chkPendidikanKes.setEnabled(true);
        chkHasilAsuhan.setEnabled(true);
        chkPenanganan.setEnabled(true);
        chkPerawatanLuka.setEnabled(true);
        chkAlatAlat.setEnabled(true);
        chkInformasi.setEnabled(true);
        chkKeamanan.setEnabled(true);
        chkProsedur.setEnabled(true);
        chkFarmaObat.setEnabled(true);
        chkFarmaInjek.setEnabled(true);
        chkFarmaSedasi.setEnabled(true);
        chkPerawatanLatihan.setEnabled(true);
        chkDistraksi.setEnabled(true);
        chkPengalihan.setEnabled(true);
        chkEtika.setEnabled(true);
        chkCaraBuang.setEnabled(true);
        chkTempat.setEnabled(true);
        chkCaraCuci.setEnabled(true);
        chkLainProPerawat.setEnabled(true);
    }
    
    private void perawatBidanFALSE() {
        chkPendidikanKes.setEnabled(false);
        chkHasilAsuhan.setEnabled(false);
        chkPenanganan.setEnabled(false);
        chkPerawatanLuka.setEnabled(false);
        chkAlatAlat.setEnabled(false);
        chkInformasi.setEnabled(false);
        chkKeamanan.setEnabled(false);
        chkProsedur.setEnabled(false);
        chkFarmaObat.setEnabled(false);
        chkFarmaInjek.setEnabled(false);
        chkFarmaSedasi.setEnabled(false);
        chkPerawatanLatihan.setEnabled(false);
        chkDistraksi.setEnabled(false);
        chkPengalihan.setEnabled(false);
        chkEtika.setEnabled(false);
        chkCaraBuang.setEnabled(false);
        chkTempat.setEnabled(false);
        chkCaraCuci.setEnabled(false);
        chkLainProPerawat.setEnabled(false);
    }
    
    private void nutrisionisTRUE() {
        chkDiet.setEnabled(true);
        chkKonsulGiziRanap.setEnabled(true);
        chkKonsulGiziRalan.setEnabled(true);
        TpndNutrisionisLain.setEnabled(true);
    }
    
    private void nutrisionisFALSE() {
        chkDiet.setEnabled(false);
        chkKonsulGiziRanap.setEnabled(false);
        chkKonsulGiziRalan.setEnabled(false);
        TpndNutrisionisLain.setEnabled(false);
    }
    
    private void admisiTRUE() {
        chkHak.setEnabled(true);
        chkJam.setEnabled(true);
        chkInfoKejadian.setEnabled(true);
        TpndAdmisiLain.setEnabled(true);
    }
    
    private void fisioTRUE() {
        chkEdukasiLat.setEnabled(true);
        chkPositioning.setEnabled(true);
        chkLatihanAktif.setEnabled(true);
        chkSimulasi.setEnabled(true);
        TpndFisioterapiLain.setEnabled(true);
    }
    
    private void fisioFALSE() {
        chkEdukasiLat.setEnabled(false);
        chkPositioning.setEnabled(false);
        chkLatihanAktif.setEnabled(false);
        chkSimulasi.setEnabled(false);
        TpndFisioterapiLain.setEnabled(false);
    }
    
    private void apotekerTRUE() {
        chkNamaDan.setEnabled(true);
        chkCaraAturan.setEnabled(true);
        chkResikoEfek.setEnabled(true);
        chkPenyimpanan.setEnabled(true);
        TpndApotekerLain.setEnabled(true);
    }
    
    private void apotekerFALSE() {
        chkNamaDan.setEnabled(false);
        chkCaraAturan.setEnabled(false);
        chkResikoEfek.setEnabled(false);
        chkPenyimpanan.setEnabled(false);
        TpndApotekerLain.setEnabled(false);
    }

    private void admisiFALSE() {
        chkHak.setEnabled(false);
        chkJam.setEnabled(false);
        chkInfoKejadian.setEnabled(false);
        TpndAdmisiLain.setEnabled(false);
    }
    
    public void emptTeksPenilaian() {
        ChkRuangan.setSelected(false);
        TtglPenilaian.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        chkPasien.setSelected(false);
        chkKeluarga.setSelected(false);
        chkLainPnrmaPnd.setSelected(false);
        TnmPenerima.setText("");
        chkAudio.setSelected(false);
        chkDemonstrasi.setSelected(false);
        chkLisan.setSelected(false);
        chkTulisan.setSelected(false);
        chkVisual.setSelected(false);
        
        cmbProfesi.setSelectedIndex(0);
        Tprofesi.setText("");
        chkDiagnosis.setSelected(false);
        chkKondisi.setSelected(false);
        chkTindakan.setSelected(false);
        chkTataCara.setSelected(false);
        chkManfaat.setSelected(false);
        chkNamaOrang.setSelected(false);
        chkKemungkinan.setSelected(false);
        chkPrognosis.setSelected(false);
        chkKemTdkTerduga.setSelected(false);
        chkKemBila.setSelected(false);
        
        chkPendidikanKes.setSelected(false);
        chkHasilAsuhan.setSelected(false);
        chkPenanganan.setSelected(false);
        chkPerawatanLuka.setSelected(false);
        chkAlatAlat.setSelected(false);
        chkInformasi.setSelected(false);
        chkKeamanan.setSelected(false);
        chkProsedur.setSelected(false);
        chkFarmaObat.setSelected(false);
        chkFarmaInjek.setSelected(false);
        chkFarmaSedasi.setSelected(false);
        chkPerawatanLatihan.setSelected(false);
        chkDistraksi.setSelected(false);
        chkPengalihan.setSelected(false);
        chkEtika.setSelected(false);
        chkCaraBuang.setSelected(false);
        chkTempat.setSelected(false);
        chkCaraCuci.setSelected(false);
        chkLainProPerawat.setSelected(false);
        TpendidikanKes.setText("");
        TpndPerawatLain.setText("");
        
        chkDiet.setSelected(false);
        chkKonsulGiziRanap.setSelected(false);
        chkKonsulGiziRalan.setSelected(false);
        TpndNutrisionisLain.setText("");

        chkHak.setSelected(false);
        chkJam.setSelected(false);
        chkInfoKejadian.setSelected(false);
        TpndAdmisiLain.setText("");
        
        chkEdukasiLat.setSelected(false);
        chkPositioning.setSelected(false);
        chkLatihanAktif.setSelected(false);
        chkSimulasi.setSelected(false);
        TpndFisioterapiLain.setText("");
        
        chkNamaDan.setSelected(false);
        chkCaraAturan.setSelected(false);
        chkResikoEfek.setSelected(false);
        chkPenyimpanan.setSelected(false);
        TpndApotekerLain.setText("");

        TpndLainyaLain.setText("");
        Tprofesi.setEnabled(false);
        chkDiagnosis.setEnabled(false);
        chkKondisi.setEnabled(false);
        chkTindakan.setEnabled(false);
        chkTataCara.setEnabled(false);
        chkManfaat.setEnabled(false);
        chkNamaOrang.setEnabled(false);
        chkKemungkinan.setEnabled(false);
        chkPrognosis.setEnabled(false);
        chkKemTdkTerduga.setEnabled(false);
        chkKemBila.setEnabled(false);

        chkPendidikanKes.setEnabled(false);
        chkHasilAsuhan.setEnabled(false);
        chkPenanganan.setEnabled(false);
        chkPerawatanLuka.setEnabled(false);
        chkAlatAlat.setEnabled(false);
        chkInformasi.setEnabled(false);
        chkKeamanan.setEnabled(false);
        chkProsedur.setEnabled(false);
        chkFarmaObat.setEnabled(false);
        chkFarmaInjek.setEnabled(false);
        chkFarmaSedasi.setEnabled(false);
        chkPerawatanLatihan.setEnabled(false);
        chkDistraksi.setEnabled(false);
        chkPengalihan.setEnabled(false);
        chkEtika.setEnabled(false);
        chkCaraBuang.setEnabled(false);
        chkTempat.setEnabled(false);
        chkCaraCuci.setEnabled(false);
        chkLainProPerawat.setEnabled(false);
        TpendidikanKes.setEnabled(false);
        TpndPerawatLain.setEnabled(false);

        chkDiet.setEnabled(false);
        chkKonsulGiziRanap.setEnabled(false);
        chkKonsulGiziRalan.setEnabled(false);
        TpndNutrisionisLain.setEnabled(false);

        chkHak.setEnabled(false);
        chkJam.setEnabled(false);
        chkInfoKejadian.setEnabled(false);
        TpndAdmisiLain.setEnabled(false);
        
        chkEdukasiLat.setEnabled(false);
        chkPositioning.setEnabled(false);
        chkLatihanAktif.setEnabled(false);
        chkSimulasi.setEnabled(false);
        TpndFisioterapiLain.setEnabled(false);
        
        chkNamaDan.setEnabled(false);
        chkCaraAturan.setEnabled(false);
        chkResikoEfek.setEnabled(false);
        chkPenyimpanan.setEnabled(false);
        TpndApotekerLain.setEnabled(false);

        TpndLainyaLain.setEnabled(false);
        cmbTingkat.setSelectedIndex(0);
        cmbEvaluasi.setSelectedIndex(0);
        LoadHTML1.setText("");        
    }
    
    private void cekDataPenilaian() {
        if (chkPasien.isSelected() == true) {
            pasien = "ya";
        } else {
            pasien = "tidak";
        }
        
        if (chkKeluarga.isSelected() == true) {
            keluarga = "ya";
        } else {
            keluarga = "tidak";
        }
        
        if (chkLainPnrmaPnd.isSelected() == true) {
            lainPenerimaPnd = "ya";
        } else {
            lainPenerimaPnd = "tidak";
        }
        
        if (chkAudio.isSelected() == true) {
            audio = "ya";
        } else {
            audio = "tidak";
        }
        
        if (chkDemonstrasi.isSelected() == true) {
            demonstrasi = "ya";
        } else {
            demonstrasi = "tidak";
        }
        
        if (chkLisan.isSelected() == true) {
            lisan = "ya";
        } else {
            lisan = "tidak";
        }
        
        if (chkTulisan.isSelected() == true) {
            tulisan = "ya";
        } else {
            tulisan = "tidak";
        }
        
        if (chkVisual.isSelected() == true) {
            visual = "ya";
        } else {
            visual = "tidak";
        }
        
        if (chkDiagnosis.isSelected() == true) {
            diagnosis = "ya";
        } else {
            diagnosis = "tidak";
        }
        
        if (chkKondisi.isSelected() == true) {
            kondisiPasien = "ya";
        } else {
            kondisiPasien = "tidak";
        }
        
        if (chkTindakan.isSelected() == true) {
            tindakan = "ya";
        } else {
            tindakan = "tidak";
        }
        
        if (chkTataCara.isSelected() == true) {
            tataCara = "ya";
        } else {
            tataCara = "tidak";
        }
        
        if (chkManfaat.isSelected() == true) {
            manfaat = "ya";
        } else {
            manfaat = "tidak";
        }
        
        if (chkNamaOrang.isSelected() == true) {
            namaOrang = "ya";
        } else {
            namaOrang = "tidak";
        }
        
        if (chkKemungkinan.isSelected() == true) {
            kemungkinanAlternative = "ya";
        } else {
            kemungkinanAlternative = "tidak";
        }
        
        if (chkPrognosis.isSelected() == true) {
            prognosis = "ya";
        } else {
            prognosis = "tidak";
        }
        
        if (chkKemTdkTerduga.isSelected() == true) {
            kemungkinanTdkTerduga = "ya";
        } else {
            kemungkinanTdkTerduga = "tidak";
        }
        
        if (chkKemBila.isSelected() == true) {
            kemungkinanBila = "ya";
        } else {
            kemungkinanBila = "tidak";
        }
        
        if (chkPendidikanKes.isSelected() == true) {
            pendidikanKesehatan = "ya";
        } else {
            pendidikanKesehatan = "tidak";
        }
        
        if (chkHasilAsuhan.isSelected() == true) {
            hasilAsuhan = "ya";
        } else {
            hasilAsuhan = "tidak";
        }
        
        if (chkPenanganan.isSelected() == true) {
            penanganan = "ya";
        } else {
            penanganan = "tidak";
        }
        
        if (chkPerawatanLuka.isSelected() == true) {
            perawatanLuka = "ya";
        } else {
            perawatanLuka = "tidak";
        }
        
        if (chkAlatAlat.isSelected() == true) {
            alatAlat = "ya";
        } else {
            alatAlat = "tidak";
        }
        
        if (chkInformasi.isSelected() == true) {
            informasiPasien = "ya";
        } else {
            informasiPasien = "tidak";
        }
        
        if (chkKeamanan.isSelected() == true) {
            keamananPenggunaan = "ya";
        } else {
            keamananPenggunaan = "tidak";
        }
        
        if (chkProsedur.isSelected() == true) {
            prosedurTindakan = "ya";
        } else {
            prosedurTindakan = "tidak";
        }
        
        if (chkFarmaObat.isSelected() == true) {
            farmakologiObat = "ya";
        } else {
            farmakologiObat = "tidak";
        }
        
        if (chkFarmaInjek.isSelected() == true) {
            farmakologiInjeksi = "ya";
        } else {
            farmakologiInjeksi = "tidak";
        }
        
        if (chkFarmaSedasi.isSelected() == true) {
            farmakologiSedasi = "ya";
        } else {
            farmakologiSedasi = "tidak";
        }
        
        if (chkPerawatanLatihan.isSelected() == true) {
            perawatanLatihan = "ya";
        } else {
            perawatanLatihan = "tidak";
        }
        
        if (chkDistraksi.isSelected() == true) {
            distraksi = "ya";
        } else {
            distraksi = "tidak";
        }
        
        if (chkPengalihan.isSelected() == true) {
            pengalihanPerhatian = "ya";
        } else {
            pengalihanPerhatian = "tidak";
        }
        
        if (chkCaraCuci.isSelected() == true) {
            caraCuci = "ya";
        } else {
            caraCuci = "tidak";
        }
        
        if (chkEtika.isSelected() == true) {
            etikaBatuk = "ya";
        } else {
            etikaBatuk = "tidak";
        }
        
        if (chkCaraBuang.isSelected() == true) {
            caraBuang = "ya";
        } else {
            caraBuang = "tidak";
        }
        
        if (chkTempat.isSelected() == true) {
            tempatToileting = "ya";
        } else {
            tempatToileting = "tidak";
        }
        
        if (chkLainProPerawat.isSelected() == true) {
            lainyaPerawatBidan = "ya";
        } else {
            lainyaPerawatBidan = "tidak";
        }
        
        if (chkDiet.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (chkKonsulGiziRanap.isSelected() == true) {
            konsulGiziRanap = "ya";
        } else {
            konsulGiziRanap = "tidak";
        }
        
        if (chkKonsulGiziRalan.isSelected() == true) {
            konsulGiziRalan = "ya";
        } else {
            konsulGiziRalan = "tidak";
        }
        
        if (chkHak.isSelected() == true) {
            hakDan = "ya";
        } else {
            hakDan = "tidak";
        }
        
        if (chkJam.isSelected() == true) {
            jamKonsultasi = "ya";
        } else {
            jamKonsultasi = "tidak";
        }
        
        if (chkInfoKejadian.isSelected() == true) {
            informasiKejadian = "ya";
        } else {
            informasiKejadian = "tidak";
        }
        
        if (chkEdukasiLat.isSelected() == true) {
            edukasiLat = "ya";
        } else {
            edukasiLat = "tidak";
        }
        
        if (chkPositioning.isSelected() == true) {
            positioning = "ya";
        } else {
            positioning = "tidak";
        }
        
        if (chkLatihanAktif.isSelected() == true) {
            latihanAktif = "ya";
        } else {
            latihanAktif = "tidak";
        }
        
        if (chkSimulasi.isSelected() == true) {
            simulasi = "ya";
        } else {
            simulasi = "tidak";
        }
        
        if (chkNamaDan.isSelected() == true) {
            namaDan = "ya";
        } else {
            namaDan = "tidak";
        }
        
        if (chkCaraAturan.isSelected() == true) {
            caraAturan = "ya";
        } else {
            caraAturan = "tidak";
        }
        
        if (chkResikoEfek.isSelected() == true) {
            resikoEfek = "ya";
        } else {
            resikoEfek = "tidak";
        }
        
        if (chkPenyimpanan.isSelected() == true) {
            penyimpananObat = "ya";
        } else {
            penyimpananObat = "tidak";
        }
    }
    
    private void dataCekPenilaian() {
        if (penerimaPnd.equals("P - Pasien")) {
            chkPasien.setSelected(true);
            chkKeluarga.setSelected(false);
            chkLainPnrmaPnd.setSelected(false);
        } else if (penerimaPnd.equals("K - Keluarga")) {
            chkPasien.setSelected(false);
            chkKeluarga.setSelected(true);
            chkLainPnrmaPnd.setSelected(false);
        } else if (penerimaPnd.equals("L - Lain-lain")) {
            chkPasien.setSelected(false);
            chkKeluarga.setSelected(false);
            chkLainPnrmaPnd.setSelected(true);
        } else {
            if (pasien.equals("ya")) {
                chkPasien.setSelected(true);
            } else {
                chkPasien.setSelected(false);
            }

            if (keluarga.equals("ya")) {
                chkKeluarga.setSelected(true);
            } else {
                chkKeluarga.setSelected(false);
            }

            if (lainPenerimaPnd.equals("ya")) {
                chkLainPnrmaPnd.setSelected(true);
            } else {
                chkLainPnrmaPnd.setSelected(false);
            }
        }
        
        if (metode.equals("1 - Audio")) {
            chkAudio.setSelected(true);
            chkDemonstrasi.setSelected(false);
            chkLisan.setSelected(false);
            chkTulisan.setSelected(false);
            chkVisual.setSelected(false);
        } else if (metode.equals("2 - Demonstrasi")) {
            chkAudio.setSelected(false);
            chkDemonstrasi.setSelected(true);
            chkLisan.setSelected(false);
            chkTulisan.setSelected(false);
            chkVisual.setSelected(false);
        } else if (metode.equals("3 - Lisan")) {
            chkAudio.setSelected(false);
            chkDemonstrasi.setSelected(false);
            chkLisan.setSelected(true);
            chkTulisan.setSelected(false);
            chkVisual.setSelected(false);
        } else if (metode.equals("4 - Tulisan")) {
            chkAudio.setSelected(false);
            chkDemonstrasi.setSelected(false);
            chkLisan.setSelected(false);
            chkTulisan.setSelected(true);
            chkVisual.setSelected(false);
        } else if (metode.equals("5 - Visual")) {
            chkAudio.setSelected(false);
            chkDemonstrasi.setSelected(false);
            chkLisan.setSelected(false);
            chkTulisan.setSelected(false);
            chkVisual.setSelected(true);
        } else {
            if (audio.equals("ya")) {
                chkAudio.setSelected(true);
            } else {
                chkAudio.setSelected(false);
            }

            if (demonstrasi.equals("ya")) {
                chkDemonstrasi.setSelected(true);
            } else {
                chkDemonstrasi.setSelected(false);
            }

            if (lisan.equals("ya")) {
                chkLisan.setSelected(true);
            } else {
                chkLisan.setSelected(false);
            }

            if (tulisan.equals("ya")) {
                chkTulisan.setSelected(true);
            } else {
                chkTulisan.setSelected(false);
            }

            if (visual.equals("ya")) {
                chkVisual.setSelected(true);
            } else {
                chkVisual.setSelected(false);
            }
        }
        
        if (diagnosis.equals("ya")) {
            chkDiagnosis.setSelected(true);
        } else {
            chkDiagnosis.setSelected(false);
        }
        
        if (kondisiPasien.equals("ya")) {
            chkKondisi.setSelected(true);
        } else {
            chkKondisi.setSelected(false);
        }
        
        if (tindakan.equals("ya")) {
            chkTindakan.setSelected(true);
        } else {
            chkTindakan.setSelected(false);
        }
        
        if (tataCara.equals("ya")) {
            chkTataCara.setSelected(true);
        } else {
            chkTataCara.setSelected(false);
        }
        
        if (manfaat.equals("ya")) {
            chkManfaat.setSelected(true);
        } else {
            chkManfaat.setSelected(false);
        }
        
        if (namaOrang.equals("ya")) {
            chkNamaOrang.setSelected(true);
        } else {
            chkNamaOrang.setSelected(false);
        }
        
        if (kemungkinanAlternative.equals("ya")) {
            chkKemungkinan.setSelected(true);
        } else {
            chkKemungkinan.setSelected(false);
        }
        
        if (prognosis.equals("ya")) {
            chkPrognosis.setSelected(true);
        } else {
            chkPrognosis.setSelected(false);
        }
        
        if (kemungkinanTdkTerduga.equals("ya")) {
            chkKemTdkTerduga.setSelected(true);
        } else {
            chkKemTdkTerduga.setSelected(false);
        }
        
        if (kemungkinanBila.equals("ya")) {
            chkKemBila.setSelected(true);
        } else {
            chkKemBila.setSelected(false);
        }
        
        if (pendidikanKesehatan.equals("ya")) {
            chkPendidikanKes.setSelected(true);
        } else {
            chkPendidikanKes.setSelected(false);
        }
        
        if (hasilAsuhan.equals("ya")) {
            chkHasilAsuhan.setSelected(true);
        } else {
            chkHasilAsuhan.setSelected(false);
        }
        
        if (penanganan.equals("ya")) {
            chkPenanganan.setSelected(true);
        } else {
            chkPenanganan.setSelected(false);
        }
        
        if (perawatanLuka.equals("ya")) {
            chkPerawatanLuka.setSelected(true);
        } else {
            chkPerawatanLuka.setSelected(false);
        }
        
        if (alatAlat.equals("ya")) {
            chkAlatAlat.setSelected(true);
        } else {
            chkAlatAlat.setSelected(false);
        }
        
        if (informasiPasien.equals("ya")) {
            chkInformasi.setSelected(true);
        } else {
            chkInformasi.setSelected(false);
        }
        
        if (keamananPenggunaan.equals("ya")) {
            chkKeamanan.setSelected(true);
        } else {
            chkKeamanan.setSelected(false);
        }
        
        if (prosedurTindakan.equals("ya")) {
            chkProsedur.setSelected(true);
        } else {
            chkProsedur.setSelected(false);
        }
        
        if (farmakologiObat.equals("ya")) {
            chkFarmaObat.setSelected(true);
        } else {
            chkFarmaObat.setSelected(false);
        }
        
        if (farmakologiInjeksi.equals("ya")) {
            chkFarmaInjek.setSelected(true);
        } else {
            chkFarmaInjek.setSelected(false);
        }
        
        if (farmakologiSedasi.equals("ya")) {
            chkFarmaSedasi.setSelected(true);
        } else {
            chkFarmaSedasi.setSelected(false);
        }
        
        if (perawatanLatihan.equals("ya")) {
            chkPerawatanLatihan.setSelected(true);
        } else {
            chkPerawatanLatihan.setSelected(false);
        }
        
        if (distraksi.equals("ya")) {
            chkDistraksi.setSelected(true);
        } else {
            chkDistraksi.setSelected(false);
        }
        
        if (pengalihanPerhatian.equals("ya")) {
            chkPengalihan.setSelected(true);
        } else {
            chkPengalihan.setSelected(false);
        }
        
        if (caraCuci.equals("ya")) {
            chkCaraCuci.setSelected(true);
        } else {
            chkCaraCuci.setSelected(false);
        }
        
        if (etikaBatuk.equals("ya")) {
            chkEtika.setSelected(true);
        } else {
            chkEtika.setSelected(false);
        }
        
        if (caraBuang.equals("ya")) {
            chkCaraBuang.setSelected(true);
        } else {
            chkCaraBuang.setSelected(false);
        }
        
        if (tempatToileting.equals("ya")) {
            chkTempat.setSelected(true);
        } else {
            chkTempat.setSelected(false);
        }
        
        if (lainyaPerawatBidan.equals("ya")) {
            chkLainProPerawat.setSelected(true);
        } else {
            chkLainProPerawat.setSelected(false);
        }
        
        if (diet.equals("ya")) {
            chkDiet.setSelected(true);
        } else {
            chkDiet.setSelected(false);
        }
        
        if (konsulGiziRanap.equals("ya")) {
            chkKonsulGiziRanap.setSelected(true);
        } else {
            chkKonsulGiziRanap.setSelected(false);
        }
        
        if (konsulGiziRalan.equals("ya")) {
            chkKonsulGiziRalan.setSelected(true);
        } else {
            chkKonsulGiziRalan.setSelected(false);
        }
        
        if (hakDan.equals("ya")) {
            chkHak.setSelected(true);
        } else {
            chkHak.setSelected(false);
        }
        
        if (jamKonsultasi.equals("ya")) {
            chkJam.setSelected(true);
        } else {
            chkJam.setSelected(false);
        }
        
        if (edukasiLat.equals("ya")) {
            chkEdukasiLat.setSelected(true);
        } else {
            chkEdukasiLat.setSelected(false);
        }
        
        if (positioning.equals("ya")) {
            chkPositioning.setSelected(true);
        } else {
            chkPositioning.setSelected(false);
        }
        
        if (latihanAktif.equals("ya")) {
            chkLatihanAktif.setSelected(true);
        } else {
            chkLatihanAktif.setSelected(false);
        }
        
        if (simulasi.equals("ya")) {
            chkSimulasi.setSelected(true);
        } else {
            chkSimulasi.setSelected(false);
        }
        
        if (namaDan.equals("ya")) {
            chkNamaDan.setSelected(true);
        } else {
            chkNamaDan.setSelected(false);
        }
        
        if (caraAturan.equals("ya")) {
            chkCaraAturan.setSelected(true);
        } else {
            chkCaraAturan.setSelected(false);
        }
        
        if (resikoEfek.equals("ya")) {
            chkResikoEfek.setSelected(true);
        } else {
            chkResikoEfek.setSelected(false);
        }
        
        if (penyimpananObat.equals("ya")) {
            chkPenyimpanan.setSelected(true);
        } else {
            chkPenyimpanan.setSelected(false);
        }
        
        if (informasiKejadian.equals("ya")) {
            chkInfoKejadian.setSelected(true);
        } else {
            chkInfoKejadian.setSelected(false);
        }
        
        //------------------------------------------------------
        if (cmbProfesi.getSelectedIndex() == 1) {
            dokterTRUE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 2 || cmbProfesi.getSelectedIndex() == 3) {
            dokterFALSE();
            perawatBidanTRUE();
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
            
            if (chkPendidikanKes.isSelected() == true) {
                TpendidikanKes.setEnabled(true);
            } else {
                TpendidikanKes.setEnabled(false);
            }
            if (chkLainProPerawat.isSelected() == true) {
                TpndPerawatLain.setEnabled(true);
            } else {
                TpndPerawatLain.setEnabled(false);
            }
            
        } else if (cmbProfesi.getSelectedIndex() == 4) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisTRUE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 5) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiTRUE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 6) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioTRUE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 7) {
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerTRUE();
            TpndLainyaLain.setEnabled(false);
        } else if (cmbProfesi.getSelectedIndex() == 8) {
            Tprofesi.setEnabled(true);
            Tprofesi.requestFocus();
            dokterFALSE();
            perawatBidanFALSE();
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            nutrisionisFALSE();
            admisiFALSE();
            fisioFALSE();
            apotekerFALSE();
            TpndLainyaLain.setEnabled(true);
        } else {
            Tprofesi.setEnabled(false);
            chkDiagnosis.setEnabled(false);
            chkKondisi.setEnabled(false);
            chkTindakan.setEnabled(false);
            chkTataCara.setEnabled(false);
            chkManfaat.setEnabled(false);
            chkNamaOrang.setEnabled(false);
            chkKemungkinan.setEnabled(false);
            chkPrognosis.setEnabled(false);
            chkKemTdkTerduga.setEnabled(false);
            chkKemBila.setEnabled(false);
            
            chkPendidikanKes.setEnabled(false);
            chkHasilAsuhan.setEnabled(false);
            chkPenanganan.setEnabled(false);
            chkPerawatanLuka.setEnabled(false);
            chkAlatAlat.setEnabled(false);
            chkInformasi.setEnabled(false);
            chkKeamanan.setEnabled(false);
            chkProsedur.setEnabled(false);
            chkFarmaObat.setEnabled(false);
            chkFarmaInjek.setEnabled(false);
            chkFarmaSedasi.setEnabled(false);
            chkPerawatanLatihan.setEnabled(false);
            chkDistraksi.setEnabled(false);
            chkPengalihan.setEnabled(false);
            chkEtika.setEnabled(false);
            chkCaraBuang.setEnabled(false);
            chkTempat.setEnabled(false);
            chkCaraCuci.setEnabled(false);
            chkLainProPerawat.setEnabled(false);
            TpendidikanKes.setEnabled(false);
            TpndPerawatLain.setEnabled(false);
            
            chkDiet.setEnabled(false);
            chkKonsulGiziRanap.setEnabled(false);
            chkKonsulGiziRalan.setEnabled(false);
            TpndNutrisionisLain.setEnabled(false);
            
            chkHak.setEnabled(false);
            chkJam.setEnabled(false);
            chkInfoKejadian.setEnabled(false);
            TpndAdmisiLain.setEnabled(false);
            
            chkEdukasiLat.setEnabled(false);
            chkPositioning.setEnabled(false);
            chkLatihanAktif.setEnabled(false);
            chkSimulasi.setEnabled(false);
            TpndFisioterapiLain.setEnabled(false);
            
            chkNamaDan.setEnabled(false);
            chkCaraAturan.setEnabled(false);
            chkResikoEfek.setEnabled(false);
            chkPenyimpanan.setEnabled(false);
            TpndApotekerLain.setEnabled(false); 
            
            TpndLainyaLain.setEnabled(false);
        }
    }
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);

                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFilePenerimaEdukasi.equals("")) {
                        gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePenerimaEdukasi;
                    }
                    //ping gagal
                } else {
                    gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }

            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "<td align='center' bgcolor='#f8fdf3'><b>Penerima Edukasi</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Penerima Edukasi'><br>(" + TnmPenerima.getText() + ")<br></td>"
                    + "</tr>"
            );

            htmlContent.append("</tbody>"
                    + "</table>");

            LoadHTML1.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public class Painter extends Canvas {
        Image image;
        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                System.out.println(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }
        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            try {
                double d = image.getHeight(this) / this.getHeight();
                double w = image.getWidth(this) / d;
                double x = this.getWidth() / 2 - w / 2;
                g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
            } catch (Exception e) {
            }            
        }
    }
    
    private void bikinQR() {        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw1.getText() + "','" + TNoRM1.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%Pemberian Informasi Dan Edukasi%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMPemberianInformasiEdukasi.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            emptTeksPenilaian();
            tampilPenilaian();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void initKamar() {
        if (kamar == null) {
            kamar = new DlgKamar(null, false);

            kamar.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (akses.getform().equals("RMPemberianInformasiEdukasi")) {
                        if (kamar.getTable().getSelectedRow() != -1) {
                            kd_kamar = kamar.getTable().getValueAt(kamar.getTable().getSelectedRow(), 1).toString();
                            TrgRawat1.setText(Sequel.cariIsi("SELECT b.nm_bangsal FROM bangsal b INNER JOIN kamar k ON k.kd_bangsal=b.kd_bangsal WHERE k.kd_kamar='" + kd_kamar + "'"));
                            ChkRuangan.setSelected(false);
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
                    if (akses.getform().equals("RMPemberianInformasiEdukasi")) {
                        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                            kamar.dispose();
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
