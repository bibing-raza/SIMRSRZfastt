/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */

package rekammedis;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public final class RMAsesmenPasienTerminal extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, pscppt;
    private ResultSet rs, rs1, rscppt;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private Properties prop = new Properties();
    private String cekDyspnoe = "", cekNafasTak = "", cekAdaSekret = "", cekNafasCepat = "", cekNafasMelalui = "", cekSpo2 = "", cekNafasLambat = "", cekMukosa = "",
            cekTak11 = "", cekMual1 = "", cekSulitMenelan = "", cekInkonAlvi = "", cekPenurunan = "", cekDistensi = "", cekTak12 = "", cekSulitBicara = "", cekInkonUrin = "",
            cekBercak = "", cekGelisah = "", cekLemas = "", cekKulit = "", cekTekanan = "", cekNadi = "", cekTak14 = "", cekMelakukanAktivitas = "", cekPindah = "", cekLainya = "",
            cekMual3 = "", cekPerubahanPersepsi = "", cekNyeriAkut = "", cekPolaNafas = "", cekKonstipasi = "", cekNyeriKronis = "", cekBersihan = "", cekDefisit = "", cekMenyangkal = "",
            cekMarah = "", cekTakut = "", cekSedih = "", cekRasa63 = "", cekKetidakberdayaan = "", cekAnxietas = "", cekDistress = "", cekMarah64 = "", cekGangguan = "",
            cekPenurunanKonsentrasi = "", cekKetidakmampuan = "", cekKeluargaKurangKomunikasi = "", cekKoping = "", cekLetih = "", cekRasa64 = "", cekPerubahanKebiasaan = "",
            cekKeluargaKurangPartisipasi = "", cekDistressSpiritual = "", cekPasienPerlu = "", cekKeluargaDapat = "", cekSahabat = "", cekLainya7 = "", cekTidak = "", cekAutopsi = "",
            cekDonasi = "", cekLainya8 = "", cekMarah9 = "", cekDepresi = "", cekRasa9 = "", cekPerubahanKebiasaan9 = "", cekKetidakmampuan9 = "", cekKoping9 = "", cekLetih9 = "",
            cekGangguan9 = "", cekSedih9 = "", cekPenurunan9 = "", cekDistress9 = "", dataKonfirmasi = "", nipDokter = "", nipPetugas = "", usernya = "", pwdnya = "", idParameterTtd = "",
            URL = "", idFileTtd = "";
    private frmUtama formUtama;

    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMAsesmenPasienTerminal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Perawatan", "Tgl. Assesmen", "Jam Assesmen", "Nama Pembuat Pernyataan", "Dokter", "Nama Petugas",
            "tgl_asesmen", "jam_asesmen", "cek_dyspnoe", "cek_nafas_tak", "cek_ada_sekret", "cek_nafas_cepat", "cek_nafas_melalui", "cek_spo2", "cek_nafas_lambat",
            "cek_mukosa", "cek_tak11", "cek_mual1", "cek_sulit_menelan", "cek_inkon_alvi", "cek_penurunan", "cek_distensi", "cek_tak12", "cek_sulit_bicara",
            "cek_inkon_urin", "nyeri", "ket_nyeri", "cek_bercak", "cek_gelisah", "cek_lemas", "cek_kulit", "cek_tekanan", "cek_nadi", "cek_tak14", "cek_melakukan_aktivitas",
            "cek_pindah", "cek_lainya", "ket_lainya", "cek_mual3", "cek_perubahan_persepsi", "cek_nyeri_akut", "cek_pola_nafas", "cek_konstipasi", "cek_nyeri_kronis",
            "cek_bersihan", "cek_defisit", "spriritual", "ket_spriritual", "didoakan", "bimbingan", "pendampingan", "orang_dihubungi", "siapa_orang_dihubungi",
            "hub_orang_dihubungi", "dimana_orang_dihubungi", "telp_orang_dihubungi", "perawatan_selanjutnya", "lingkungan_rumah", "mampu_merawat", "ket_mampu_merawat",
            "fasilitasi_rs", "cek_menyangkal", "cek_marah", "cek_takut", "cek_sedih", "cek_rasa63", "cek_ketidakberdayaan", "cek_anxietas", "cek_distress", "cek_marah64",
            "cek_gangguan", "cek_penurunan_konsentrasi", "cek_ketidakmampuan", "cek_keluarga_kurang_komunikasi", "cek_koping", "cek_letih", "cek_rasa64", "cek_perubahan_kebiasaan",
            "cek_keluarga_kurang_partisipasi", "cek_distress_spiritual", "cek_pasien_perlu", "cek_keluarga_dapat", "cek_sahabat", "cek_lainya7", "ket_lainya7", "cek_tidak",
            "cek_autopsi", "cek_donasi", "ket_donasi", "cek_lainya8", "ket_lainya8", "cek_marah9", "cek_depresi", "cek_rasa9", "cek_perubahan_kebiasaan9", "cek_ketidakmampuan9",
            "cek_koping9", "cek_letih9", "cek_gangguan9", "cek_sedih9", "cek_penurunan9", "cek_distress9", "nip_dokter", "nm_pembuat_pernyataan", "nip_petugas",
            "id_file_nm_pembuat_pernyataan", "waktu_simpan"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 110; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable()); 
        
        tabModeCppt=new DefaultTableModel(null, new Object[]{
            "Tgl. CPPT", "Jam CPPT", "Jenis Bagian", "DPJP Konsulen", "Jenis PPA",
            "Nama PPA", "Shift", "hasil", "instruksi", "no_rawat", "tgl_cppt", "jam_cppt"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCPPT.setModel(tabModeCppt);
        tbCPPT.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCPPT.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = tbCPPT.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(70);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbCPPT.setDefaultRenderer(Object.class, new WarnaTable());
        
        TketNyeri.setDocument(new batasInput((int) 150).getKata(TketNyeri));
        TketLain2.setDocument(new batasInput((int) 150).getKata(TketLain2));
        TketSpiritual.setDocument(new batasInput((int) 200).getKata(TketSpiritual));
        Tsiapa.setDocument(new batasInput((int) 100).getKata(Tsiapa));
        Thubungan.setDocument(new batasInput((int) 100).getKata(Thubungan));
        Tdimana.setDocument(new batasInput((int) 100).getKata(Tdimana));
        TnoTelp.setDocument(new batasInput((byte) 13).getOnlyAngka(TnoTelp));
        TketMampu.setDocument(new batasInput((int) 100).getKata(TketMampu));
        TketLain7.setDocument(new batasInput((int) 200).getKata(TketLain7));
        TketDonasi.setDocument(new batasInput((int) 200).getKata(TketDonasi));
        TketLain8.setDocument(new batasInput((int) 200).getKata(TketLain8));        
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
                    nipPetugas = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
                if (akses.getform().equals("RMAsesmenPasienTerminal")) {
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
        
        ChkAccor.setSelected(false);
        isMenu();
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("notif : " + e);
        }
        
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
        internalFrame6 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        jLabel125 = new widget.Label();
        cmbRekmed = new widget.ComboBox();
        panelisi7 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel68 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
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
        jLabel10 = new widget.Label();
        chkDyspnoe = new widget.CekBox();
        chkNafasTak = new widget.CekBox();
        chkAdaSekret = new widget.CekBox();
        jLabel34 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel12 = new widget.Label();
        TtglAsesmen = new widget.Tanggal();
        jLabel13 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        chkNafasCepat = new widget.CekBox();
        chkNafasMelalui = new widget.CekBox();
        chkSpo2 = new widget.CekBox();
        chkNafasLambat = new widget.CekBox();
        chkMukosa = new widget.CekBox();
        chkTak11 = new widget.CekBox();
        jLabel67 = new widget.Label();
        chkMual1 = new widget.CekBox();
        chkPenurunan = new widget.CekBox();
        chkSulitBicara = new widget.CekBox();
        chkSulitMenelan = new widget.CekBox();
        chkDistensi = new widget.CekBox();
        chkInkonUrin = new widget.CekBox();
        chkInkonAlvi = new widget.CekBox();
        chkTak12 = new widget.CekBox();
        jLabel69 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        TketNyeri = new widget.TextBox();
        jLabel70 = new widget.Label();
        chkBercak = new widget.CekBox();
        chkKulit = new widget.CekBox();
        chkTak14 = new widget.CekBox();
        chkGelisah = new widget.CekBox();
        chkTekanan = new widget.CekBox();
        chkLemas = new widget.CekBox();
        chkNadi = new widget.CekBox();
        jLabel66 = new widget.Label();
        chkMelakukanAktivitas = new widget.CekBox();
        chkPindah = new widget.CekBox();
        chkLainya = new widget.CekBox();
        TketLain2 = new widget.TextBox();
        jLabel71 = new widget.Label();
        chkMual3 = new widget.CekBox();
        chkPolaNafas = new widget.CekBox();
        chkBersihan = new widget.CekBox();
        chkPerubahanPersepsi = new widget.CekBox();
        chkKonstipasi = new widget.CekBox();
        chkDefisit = new widget.CekBox();
        chkNyeriAkut = new widget.CekBox();
        chkNyeriKronis = new widget.CekBox();
        jLabel72 = new widget.Label();
        cmbSpiritual = new widget.ComboBox();
        TketSpiritual = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        jLabel16 = new widget.Label();
        cmbDoa = new widget.ComboBox();
        cmbBimbingan = new widget.ComboBox();
        cmbPendamping = new widget.ComboBox();
        jLabel74 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbDihubungi = new widget.ComboBox();
        jLabel17 = new widget.Label();
        Tsiapa = new widget.TextBox();
        jLabel18 = new widget.Label();
        Thubungan = new widget.TextBox();
        jLabel20 = new widget.Label();
        Tdimana = new widget.TextBox();
        jLabel22 = new widget.Label();
        TnoTelp = new widget.TextBox();
        jLabel76 = new widget.Label();
        cmbRencana = new widget.ComboBox();
        jLabel23 = new widget.Label();
        cmbDisiapkan = new widget.ComboBox();
        jLabel24 = new widget.Label();
        cmbMampu = new widget.ComboBox();
        TketMampu = new widget.TextBox();
        jLabel25 = new widget.Label();
        cmbDifasilitasi = new widget.ComboBox();
        jLabel77 = new widget.Label();
        chkMenyangkal = new widget.CekBox();
        chkMarah = new widget.CekBox();
        chkTakut = new widget.CekBox();
        chkSedih = new widget.CekBox();
        chkRasa63 = new widget.CekBox();
        chkKetidakberdayaan = new widget.CekBox();
        jLabel78 = new widget.Label();
        chkAnxietas = new widget.CekBox();
        chkDistress = new widget.CekBox();
        jLabel79 = new widget.Label();
        chkMarah64 = new widget.CekBox();
        chkGangguan = new widget.CekBox();
        chkPenurunanKonsentrasi = new widget.CekBox();
        chkKetidakmampuan = new widget.CekBox();
        chkKeluargaKurangKomunikasi = new widget.CekBox();
        chkLetih = new widget.CekBox();
        chkRasa64 = new widget.CekBox();
        chkPerubahanKebiasaan = new widget.CekBox();
        chkKeluargaKurangPartisipasi = new widget.CekBox();
        jLabel80 = new widget.Label();
        chkKoping = new widget.CekBox();
        chkDistressSpiritual = new widget.CekBox();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        chkPasienPerlu = new widget.CekBox();
        chkKeluargaDapat = new widget.CekBox();
        chkSahabat = new widget.CekBox();
        chkLainya7 = new widget.CekBox();
        TketLain7 = new widget.TextBox();
        jLabel83 = new widget.Label();
        chkTidak = new widget.CekBox();
        chkAutopsi = new widget.CekBox();
        chkDonasi = new widget.CekBox();
        TketDonasi = new widget.TextBox();
        chkLainya8 = new widget.CekBox();
        TketLain8 = new widget.TextBox();
        jLabel84 = new widget.Label();
        chkMarah9 = new widget.CekBox();
        chkPerubahanKebiasaan9 = new widget.CekBox();
        chkGangguan9 = new widget.CekBox();
        chkDepresi = new widget.CekBox();
        chkKetidakmampuan9 = new widget.CekBox();
        chkSedih9 = new widget.CekBox();
        chkRasa9 = new widget.CekBox();
        chkLetih9 = new widget.CekBox();
        chkPenurunan9 = new widget.CekBox();
        jLabel85 = new widget.Label();
        chkKoping9 = new widget.CekBox();
        chkDistress9 = new widget.CekBox();
        jLabel86 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel87 = new widget.Label();
        TnmPembuat = new widget.TextBox();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormMenu = new widget.PanelBiasa();
        Scroll4 = new widget.ScrollPane();
        tbCPPT = new widget.Table();
        panelGlass14 = new widget.panelisi();
        scrollPane5 = new widget.ScrollPane();
        Thasil = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        internalFrame3 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();
        panelGlass13 = new widget.panelisi();
        panelGlass23 = new widget.panelisi();
        scrollPane7 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel93 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(190, 26));
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
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(190, 26));
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

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi5.setBackground(new java.awt.Color(255, 150, 255));
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi5.setLayout(null);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Pilih Rekam Medis :");
        jLabel125.setName("jLabel125"); // NOI18N
        panelisi5.add(jLabel125);
        jLabel125.setBounds(0, 10, 120, 23);

        cmbRekmed.setBackground(new java.awt.Color(245, 253, 240));
        cmbRekmed.setForeground(new java.awt.Color(0, 0, 0));
        cmbRekmed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRekmed.setLightWeightPopupEnabled(false);
        cmbRekmed.setName("cmbRekmed"); // NOI18N
        panelisi5.add(cmbRekmed);
        cmbRekmed.setBounds(127, 10, 550, 23);

        internalFrame6.add(panelisi5, java.awt.BorderLayout.CENTER);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

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
        panelisi7.add(BtnTampilkanQr);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelisi7.add(BtnCloseIn3);

        internalFrame6.add(panelisi7, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Pasien Terminal Dan Keluarganya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Cetak Dalam Bentuk :");
        jLabel68.setName("jLabel68"); // NOI18N
        jLabel68.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel68);

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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1624));
        FormInput.setLayout(null);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(319, 10, 410, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 10, 70, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 110, 23);

        chkDyspnoe.setBackground(new java.awt.Color(255, 255, 250));
        chkDyspnoe.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDyspnoe.setForeground(new java.awt.Color(0, 0, 0));
        chkDyspnoe.setText("Dyspnoe");
        chkDyspnoe.setBorderPainted(true);
        chkDyspnoe.setBorderPaintedFlat(true);
        chkDyspnoe.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDyspnoe.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDyspnoe.setName("chkDyspnoe"); // NOI18N
        chkDyspnoe.setOpaque(false);
        chkDyspnoe.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDyspnoe);
        chkDyspnoe.setBounds(230, 122, 80, 23);

        chkNafasTak.setBackground(new java.awt.Color(255, 255, 250));
        chkNafasTak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNafasTak.setForeground(new java.awt.Color(0, 0, 0));
        chkNafasTak.setText("Nafas tak teratur");
        chkNafasTak.setBorderPainted(true);
        chkNafasTak.setBorderPaintedFlat(true);
        chkNafasTak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNafasTak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNafasTak.setName("chkNafasTak"); // NOI18N
        chkNafasTak.setOpaque(false);
        chkNafasTak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNafasTak);
        chkNafasTak.setBounds(230, 150, 120, 23);

        chkAdaSekret.setBackground(new java.awt.Color(255, 255, 250));
        chkAdaSekret.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAdaSekret.setForeground(new java.awt.Color(0, 0, 0));
        chkAdaSekret.setText("Ada sekret");
        chkAdaSekret.setBorderPainted(true);
        chkAdaSekret.setBorderPaintedFlat(true);
        chkAdaSekret.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAdaSekret.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAdaSekret.setName("chkAdaSekret"); // NOI18N
        chkAdaSekret.setOpaque(false);
        chkAdaSekret.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAdaSekret);
        chkAdaSekret.setBounds(230, 178, 90, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Nama Petugas :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 1585, 110, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(115, 1585, 390, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(510, 1585, 28, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tgl. Asesmen :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 110, 23);

        TtglAsesmen.setEditable(false);
        TtglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2026" }));
        TtglAsesmen.setDisplayFormat("dd-MM-yyyy");
        TtglAsesmen.setName("TtglAsesmen"); // NOI18N
        TtglAsesmen.setOpaque(false);
        TtglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAsesmen);
        TtglAsesmen.setBounds(115, 66, 90, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pukul :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(205, 66, 60, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(270, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(322, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(374, 66, 45, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(115, 38, 615, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("1. Gejala Seperti Mau Muntah dan Kesulitan Bernafas");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 340, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("1.1 Kegawatan Pernafasan :");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 122, 220, 23);

        chkNafasCepat.setBackground(new java.awt.Color(255, 255, 250));
        chkNafasCepat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNafasCepat.setForeground(new java.awt.Color(0, 0, 0));
        chkNafasCepat.setText("Nafas cepat dan dangkal");
        chkNafasCepat.setBorderPainted(true);
        chkNafasCepat.setBorderPaintedFlat(true);
        chkNafasCepat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNafasCepat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNafasCepat.setName("chkNafasCepat"); // NOI18N
        chkNafasCepat.setOpaque(false);
        chkNafasCepat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNafasCepat);
        chkNafasCepat.setBounds(360, 122, 150, 23);

        chkNafasMelalui.setBackground(new java.awt.Color(255, 255, 250));
        chkNafasMelalui.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNafasMelalui.setForeground(new java.awt.Color(0, 0, 0));
        chkNafasMelalui.setText("Nafas melalui mulut");
        chkNafasMelalui.setBorderPainted(true);
        chkNafasMelalui.setBorderPaintedFlat(true);
        chkNafasMelalui.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNafasMelalui.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNafasMelalui.setName("chkNafasMelalui"); // NOI18N
        chkNafasMelalui.setOpaque(false);
        chkNafasMelalui.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNafasMelalui);
        chkNafasMelalui.setBounds(360, 150, 130, 23);

        chkSpo2.setBackground(new java.awt.Color(255, 255, 250));
        chkSpo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpo2.setForeground(new java.awt.Color(0, 0, 0));
        chkSpo2.setText("SpO2 < Normal");
        chkSpo2.setBorderPainted(true);
        chkSpo2.setBorderPaintedFlat(true);
        chkSpo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpo2.setName("chkSpo2"); // NOI18N
        chkSpo2.setOpaque(false);
        chkSpo2.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpo2);
        chkSpo2.setBounds(360, 178, 110, 23);

        chkNafasLambat.setBackground(new java.awt.Color(255, 255, 250));
        chkNafasLambat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNafasLambat.setForeground(new java.awt.Color(0, 0, 0));
        chkNafasLambat.setText("Nafas lambat");
        chkNafasLambat.setBorderPainted(true);
        chkNafasLambat.setBorderPaintedFlat(true);
        chkNafasLambat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNafasLambat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNafasLambat.setName("chkNafasLambat"); // NOI18N
        chkNafasLambat.setOpaque(false);
        chkNafasLambat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNafasLambat);
        chkNafasLambat.setBounds(530, 122, 100, 23);

        chkMukosa.setBackground(new java.awt.Color(255, 255, 250));
        chkMukosa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMukosa.setForeground(new java.awt.Color(0, 0, 0));
        chkMukosa.setText("Mukosa oral kering");
        chkMukosa.setBorderPainted(true);
        chkMukosa.setBorderPaintedFlat(true);
        chkMukosa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMukosa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMukosa.setName("chkMukosa"); // NOI18N
        chkMukosa.setOpaque(false);
        chkMukosa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMukosa);
        chkMukosa.setBounds(530, 150, 130, 23);

        chkTak11.setBackground(new java.awt.Color(255, 255, 250));
        chkTak11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTak11.setForeground(new java.awt.Color(0, 0, 0));
        chkTak11.setText("T.A.K");
        chkTak11.setBorderPainted(true);
        chkTak11.setBorderPaintedFlat(true);
        chkTak11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTak11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTak11.setName("chkTak11"); // NOI18N
        chkTak11.setOpaque(false);
        chkTak11.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTak11);
        chkTak11.setBounds(530, 178, 60, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("1.2 Kehilangan Tinus Otot :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 206, 220, 23);

        chkMual1.setBackground(new java.awt.Color(255, 255, 250));
        chkMual1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMual1.setForeground(new java.awt.Color(0, 0, 0));
        chkMual1.setText("Mual");
        chkMual1.setBorderPainted(true);
        chkMual1.setBorderPaintedFlat(true);
        chkMual1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMual1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMual1.setName("chkMual1"); // NOI18N
        chkMual1.setOpaque(false);
        chkMual1.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMual1);
        chkMual1.setBounds(230, 206, 50, 23);

        chkPenurunan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenurunan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenurunan.setText("Penurunan pergerakan tubuh");
        chkPenurunan.setBorderPainted(true);
        chkPenurunan.setBorderPaintedFlat(true);
        chkPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenurunan.setName("chkPenurunan"); // NOI18N
        chkPenurunan.setOpaque(false);
        chkPenurunan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenurunan);
        chkPenurunan.setBounds(360, 206, 170, 23);

        chkSulitBicara.setBackground(new java.awt.Color(255, 255, 250));
        chkSulitBicara.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSulitBicara.setForeground(new java.awt.Color(0, 0, 0));
        chkSulitBicara.setText("Sulit berbicara");
        chkSulitBicara.setBorderPainted(true);
        chkSulitBicara.setBorderPaintedFlat(true);
        chkSulitBicara.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSulitBicara.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSulitBicara.setName("chkSulitBicara"); // NOI18N
        chkSulitBicara.setOpaque(false);
        chkSulitBicara.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSulitBicara);
        chkSulitBicara.setBounds(540, 206, 100, 23);

        chkSulitMenelan.setBackground(new java.awt.Color(255, 255, 250));
        chkSulitMenelan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSulitMenelan.setForeground(new java.awt.Color(0, 0, 0));
        chkSulitMenelan.setText("Sulit menelan");
        chkSulitMenelan.setBorderPainted(true);
        chkSulitMenelan.setBorderPaintedFlat(true);
        chkSulitMenelan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSulitMenelan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSulitMenelan.setName("chkSulitMenelan"); // NOI18N
        chkSulitMenelan.setOpaque(false);
        chkSulitMenelan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSulitMenelan);
        chkSulitMenelan.setBounds(230, 234, 100, 23);

        chkDistensi.setBackground(new java.awt.Color(255, 255, 250));
        chkDistensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistensi.setForeground(new java.awt.Color(0, 0, 0));
        chkDistensi.setText("Distensi abdomen");
        chkDistensi.setBorderPainted(true);
        chkDistensi.setBorderPaintedFlat(true);
        chkDistensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistensi.setName("chkDistensi"); // NOI18N
        chkDistensi.setOpaque(false);
        chkDistensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistensi);
        chkDistensi.setBounds(360, 234, 120, 23);

        chkInkonUrin.setBackground(new java.awt.Color(255, 255, 250));
        chkInkonUrin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInkonUrin.setForeground(new java.awt.Color(0, 0, 0));
        chkInkonUrin.setText("Inkontinensia urine");
        chkInkonUrin.setBorderPainted(true);
        chkInkonUrin.setBorderPaintedFlat(true);
        chkInkonUrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInkonUrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInkonUrin.setName("chkInkonUrin"); // NOI18N
        chkInkonUrin.setOpaque(false);
        chkInkonUrin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkInkonUrin);
        chkInkonUrin.setBounds(540, 234, 130, 23);

        chkInkonAlvi.setBackground(new java.awt.Color(255, 255, 250));
        chkInkonAlvi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInkonAlvi.setForeground(new java.awt.Color(0, 0, 0));
        chkInkonAlvi.setText("Inkontinensia alvi");
        chkInkonAlvi.setBorderPainted(true);
        chkInkonAlvi.setBorderPaintedFlat(true);
        chkInkonAlvi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInkonAlvi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInkonAlvi.setName("chkInkonAlvi"); // NOI18N
        chkInkonAlvi.setOpaque(false);
        chkInkonAlvi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkInkonAlvi);
        chkInkonAlvi.setBounds(230, 262, 120, 23);

        chkTak12.setBackground(new java.awt.Color(255, 255, 250));
        chkTak12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTak12.setForeground(new java.awt.Color(0, 0, 0));
        chkTak12.setText("T.A.K");
        chkTak12.setBorderPainted(true);
        chkTak12.setBorderPaintedFlat(true);
        chkTak12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTak12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTak12.setName("chkTak12"); // NOI18N
        chkTak12.setOpaque(false);
        chkTak12.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTak12);
        chkTak12.setBounds(360, 262, 60, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("1.3 Nyeri :");
        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 290, 220, 23);

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
        cmbNyeri.setBounds(230, 290, 60, 23);

        TketNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TketNyeri.setName("TketNyeri"); // NOI18N
        TketNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketNyeriKeyPressed(evt);
            }
        });
        FormInput.add(TketNyeri);
        TketNyeri.setBounds(295, 290, 435, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("1.4 Perlambatan Sirkulasi :");
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 318, 220, 23);

        chkBercak.setBackground(new java.awt.Color(255, 255, 250));
        chkBercak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBercak.setForeground(new java.awt.Color(0, 0, 0));
        chkBercak.setText("Bercak dan sianosis pada ekstremitas");
        chkBercak.setBorderPainted(true);
        chkBercak.setBorderPaintedFlat(true);
        chkBercak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBercak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBercak.setName("chkBercak"); // NOI18N
        chkBercak.setOpaque(false);
        chkBercak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBercak);
        chkBercak.setBounds(230, 318, 210, 23);

        chkKulit.setBackground(new java.awt.Color(255, 255, 250));
        chkKulit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKulit.setForeground(new java.awt.Color(0, 0, 0));
        chkKulit.setText("Kulit dingin dan berkeringat");
        chkKulit.setBorderPainted(true);
        chkKulit.setBorderPaintedFlat(true);
        chkKulit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKulit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKulit.setName("chkKulit"); // NOI18N
        chkKulit.setOpaque(false);
        chkKulit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKulit);
        chkKulit.setBounds(450, 318, 160, 23);

        chkTak14.setBackground(new java.awt.Color(255, 255, 250));
        chkTak14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTak14.setForeground(new java.awt.Color(0, 0, 0));
        chkTak14.setText("T.A.K");
        chkTak14.setBorderPainted(true);
        chkTak14.setBorderPaintedFlat(true);
        chkTak14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTak14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTak14.setName("chkTak14"); // NOI18N
        chkTak14.setOpaque(false);
        chkTak14.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTak14);
        chkTak14.setBounds(620, 318, 60, 23);

        chkGelisah.setBackground(new java.awt.Color(255, 255, 250));
        chkGelisah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGelisah.setForeground(new java.awt.Color(0, 0, 0));
        chkGelisah.setText("Gelisah");
        chkGelisah.setBorderPainted(true);
        chkGelisah.setBorderPaintedFlat(true);
        chkGelisah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGelisah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGelisah.setName("chkGelisah"); // NOI18N
        chkGelisah.setOpaque(false);
        chkGelisah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGelisah);
        chkGelisah.setBounds(230, 346, 70, 23);

        chkTekanan.setBackground(new java.awt.Color(255, 255, 250));
        chkTekanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTekanan.setForeground(new java.awt.Color(0, 0, 0));
        chkTekanan.setText("Tekanan darah menurun");
        chkTekanan.setBorderPainted(true);
        chkTekanan.setBorderPaintedFlat(true);
        chkTekanan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTekanan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTekanan.setName("chkTekanan"); // NOI18N
        chkTekanan.setOpaque(false);
        chkTekanan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTekanan);
        chkTekanan.setBounds(450, 346, 160, 23);

        chkLemas.setBackground(new java.awt.Color(255, 255, 250));
        chkLemas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLemas.setForeground(new java.awt.Color(0, 0, 0));
        chkLemas.setText("Lemas");
        chkLemas.setBorderPainted(true);
        chkLemas.setBorderPaintedFlat(true);
        chkLemas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLemas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLemas.setName("chkLemas"); // NOI18N
        chkLemas.setOpaque(false);
        chkLemas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLemas);
        chkLemas.setBounds(230, 374, 70, 23);

        chkNadi.setBackground(new java.awt.Color(255, 255, 250));
        chkNadi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNadi.setForeground(new java.awt.Color(0, 0, 0));
        chkNadi.setText("Nadi lambat dan lemah");
        chkNadi.setBorderPainted(true);
        chkNadi.setBorderPaintedFlat(true);
        chkNadi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNadi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNadi.setName("chkNadi"); // NOI18N
        chkNadi.setOpaque(false);
        chkNadi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNadi);
        chkNadi.setBounds(450, 374, 140, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("2. Faktor - Faktor Yang Meningkatkan Dan Membangkitkan Gejala Fisik");
        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 402, 440, 23);

        chkMelakukanAktivitas.setBackground(new java.awt.Color(255, 255, 250));
        chkMelakukanAktivitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMelakukanAktivitas.setForeground(new java.awt.Color(0, 0, 0));
        chkMelakukanAktivitas.setText("Melakukan aktivitas fisik");
        chkMelakukanAktivitas.setBorderPainted(true);
        chkMelakukanAktivitas.setBorderPaintedFlat(true);
        chkMelakukanAktivitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMelakukanAktivitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMelakukanAktivitas.setName("chkMelakukanAktivitas"); // NOI18N
        chkMelakukanAktivitas.setOpaque(false);
        chkMelakukanAktivitas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMelakukanAktivitas);
        chkMelakukanAktivitas.setBounds(230, 430, 150, 23);

        chkPindah.setBackground(new java.awt.Color(255, 255, 250));
        chkPindah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPindah.setForeground(new java.awt.Color(0, 0, 0));
        chkPindah.setText("Pindah posisi");
        chkPindah.setBorderPainted(true);
        chkPindah.setBorderPaintedFlat(true);
        chkPindah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPindah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPindah.setName("chkPindah"); // NOI18N
        chkPindah.setOpaque(false);
        chkPindah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPindah);
        chkPindah.setBounds(387, 430, 95, 23);

        chkLainya.setBackground(new java.awt.Color(255, 255, 250));
        chkLainya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainya.setForeground(new java.awt.Color(0, 0, 0));
        chkLainya.setText("Lainnya :");
        chkLainya.setBorderPainted(true);
        chkLainya.setBorderPaintedFlat(true);
        chkLainya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainya.setName("chkLainya"); // NOI18N
        chkLainya.setOpaque(false);
        chkLainya.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainyaActionPerformed(evt);
            }
        });
        FormInput.add(chkLainya);
        chkLainya.setBounds(490, 430, 70, 23);

        TketLain2.setForeground(new java.awt.Color(0, 0, 0));
        TketLain2.setName("TketLain2"); // NOI18N
        TketLain2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLain2KeyPressed(evt);
            }
        });
        FormInput.add(TketLain2);
        TketLain2.setBounds(560, 430, 170, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("3. Manajemen Gejala Saat Ini Dan Respon Pasien : Masalah Keperawatan *");
        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 458, 460, 23);

        chkMual3.setBackground(new java.awt.Color(255, 255, 250));
        chkMual3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMual3.setForeground(new java.awt.Color(0, 0, 0));
        chkMual3.setText("Mual");
        chkMual3.setBorderPainted(true);
        chkMual3.setBorderPaintedFlat(true);
        chkMual3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMual3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMual3.setName("chkMual3"); // NOI18N
        chkMual3.setOpaque(false);
        chkMual3.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMual3);
        chkMual3.setBounds(230, 486, 60, 23);

        chkPolaNafas.setBackground(new java.awt.Color(255, 255, 250));
        chkPolaNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPolaNafas.setForeground(new java.awt.Color(0, 0, 0));
        chkPolaNafas.setText("Pola nafas tidak efektif");
        chkPolaNafas.setBorderPainted(true);
        chkPolaNafas.setBorderPaintedFlat(true);
        chkPolaNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPolaNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPolaNafas.setName("chkPolaNafas"); // NOI18N
        chkPolaNafas.setOpaque(false);
        chkPolaNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPolaNafas);
        chkPolaNafas.setBounds(400, 486, 140, 23);

        chkBersihan.setBackground(new java.awt.Color(255, 255, 250));
        chkBersihan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBersihan.setForeground(new java.awt.Color(0, 0, 0));
        chkBersihan.setText("Bersihan jalan nafas tidak efektif");
        chkBersihan.setBorderPainted(true);
        chkBersihan.setBorderPaintedFlat(true);
        chkBersihan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBersihan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBersihan.setName("chkBersihan"); // NOI18N
        chkBersihan.setOpaque(false);
        chkBersihan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBersihan);
        chkBersihan.setBounds(560, 486, 200, 23);

        chkPerubahanPersepsi.setBackground(new java.awt.Color(255, 255, 250));
        chkPerubahanPersepsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerubahanPersepsi.setForeground(new java.awt.Color(0, 0, 0));
        chkPerubahanPersepsi.setText("Perubahan persepsi sensori");
        chkPerubahanPersepsi.setBorderPainted(true);
        chkPerubahanPersepsi.setBorderPaintedFlat(true);
        chkPerubahanPersepsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerubahanPersepsi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerubahanPersepsi.setName("chkPerubahanPersepsi"); // NOI18N
        chkPerubahanPersepsi.setOpaque(false);
        chkPerubahanPersepsi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerubahanPersepsi);
        chkPerubahanPersepsi.setBounds(230, 514, 160, 23);

        chkKonstipasi.setBackground(new java.awt.Color(255, 255, 250));
        chkKonstipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKonstipasi.setForeground(new java.awt.Color(0, 0, 0));
        chkKonstipasi.setText("Konstipasi");
        chkKonstipasi.setBorderPainted(true);
        chkKonstipasi.setBorderPaintedFlat(true);
        chkKonstipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKonstipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKonstipasi.setName("chkKonstipasi"); // NOI18N
        chkKonstipasi.setOpaque(false);
        chkKonstipasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKonstipasi);
        chkKonstipasi.setBounds(400, 514, 80, 23);

        chkDefisit.setBackground(new java.awt.Color(255, 255, 250));
        chkDefisit.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDefisit.setForeground(new java.awt.Color(0, 0, 0));
        chkDefisit.setText("Defisit perawatan diri");
        chkDefisit.setBorderPainted(true);
        chkDefisit.setBorderPaintedFlat(true);
        chkDefisit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDefisit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDefisit.setName("chkDefisit"); // NOI18N
        chkDefisit.setOpaque(false);
        chkDefisit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDefisit);
        chkDefisit.setBounds(560, 514, 130, 23);

        chkNyeriAkut.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriAkut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriAkut.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeriAkut.setText("Nyeri akut");
        chkNyeriAkut.setBorderPainted(true);
        chkNyeriAkut.setBorderPaintedFlat(true);
        chkNyeriAkut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriAkut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriAkut.setName("chkNyeriAkut"); // NOI18N
        chkNyeriAkut.setOpaque(false);
        chkNyeriAkut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriAkut);
        chkNyeriAkut.setBounds(230, 542, 90, 23);

        chkNyeriKronis.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriKronis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriKronis.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeriKronis.setText("Nyeri kronis");
        chkNyeriKronis.setBorderPainted(true);
        chkNyeriKronis.setBorderPaintedFlat(true);
        chkNyeriKronis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriKronis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriKronis.setName("chkNyeriKronis"); // NOI18N
        chkNyeriKronis.setOpaque(false);
        chkNyeriKronis.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriKronis);
        chkNyeriKronis.setBounds(400, 542, 90, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("<html><div style=\"text-align: right;\"><b>4. Orientasi Spiritual Pasien Dan Keluarga : </b><br>Apakah Perlu Pelayanan Spiritual ?</div></html>");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 570, 280, 30);

        cmbSpiritual.setForeground(new java.awt.Color(0, 0, 0));
        cmbSpiritual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya, Oleh" }));
        cmbSpiritual.setName("cmbSpiritual"); // NOI18N
        cmbSpiritual.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSpiritual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSpiritualActionPerformed(evt);
            }
        });
        FormInput.add(cmbSpiritual);
        cmbSpiritual.setBounds(290, 570, 75, 23);

        TketSpiritual.setForeground(new java.awt.Color(0, 0, 0));
        TketSpiritual.setName("TketSpiritual"); // NOI18N
        TketSpiritual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketSpiritualKeyPressed(evt);
            }
        });
        FormInput.add(TketSpiritual);
        TketSpiritual.setBounds(370, 570, 360, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("5. Urusan Dan Kebutuhan Spiritual Pasien Dan Keluarga Seperti Putus Asa, Penderitaan, Rasa Bersalah Atau Pengampunan :");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 605, 730, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Perlu di do'akan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 633, 110, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Perlu bimbingan rohani :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(175, 633, 140, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Perlu pendampingan rohani :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(380, 633, 160, 23);

        cmbDoa.setForeground(new java.awt.Color(0, 0, 0));
        cmbDoa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbDoa.setName("cmbDoa"); // NOI18N
        cmbDoa.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDoa);
        cmbDoa.setBounds(115, 633, 60, 23);

        cmbBimbingan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBimbingan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbBimbingan.setName("cmbBimbingan"); // NOI18N
        cmbBimbingan.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbBimbingan);
        cmbBimbingan.setBounds(320, 633, 60, 23);

        cmbPendamping.setForeground(new java.awt.Color(0, 0, 0));
        cmbPendamping.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbPendamping.setName("cmbPendamping"); // NOI18N
        cmbPendamping.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbPendamping);
        cmbPendamping.setBounds(545, 633, 60, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("6. Status Psikososial Dan Keluarga :");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 661, 240, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("6.1 Apakah Ada Orang Yang Ingin Dihubungi Saat Ini ?");
        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 689, 360, 23);

        cmbDihubungi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDihubungi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbDihubungi.setName("cmbDihubungi"); // NOI18N
        cmbDihubungi.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbDihubungi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDihubungiActionPerformed(evt);
            }
        });
        FormInput.add(cmbDihubungi);
        cmbDihubungi.setBounds(370, 689, 60, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Ya, Siapa :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 717, 110, 23);

        Tsiapa.setForeground(new java.awt.Color(0, 0, 0));
        Tsiapa.setName("Tsiapa"); // NOI18N
        Tsiapa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsiapaKeyPressed(evt);
            }
        });
        FormInput.add(Tsiapa);
        Tsiapa.setBounds(115, 717, 250, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Hubungan dengan pasien sebagai :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(370, 717, 180, 23);

        Thubungan.setForeground(new java.awt.Color(0, 0, 0));
        Thubungan.setName("Thubungan"); // NOI18N
        Thubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThubunganKeyPressed(evt);
            }
        });
        FormInput.add(Thubungan);
        Thubungan.setBounds(555, 717, 175, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Dimana :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(0, 745, 110, 23);

        Tdimana.setForeground(new java.awt.Color(0, 0, 0));
        Tdimana.setName("Tdimana"); // NOI18N
        Tdimana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdimanaKeyPressed(evt);
            }
        });
        FormInput.add(Tdimana);
        Tdimana.setBounds(115, 745, 250, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("No. Telpn./HP :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(370, 745, 180, 23);

        TnoTelp.setForeground(new java.awt.Color(0, 0, 0));
        TnoTelp.setName("TnoTelp"); // NOI18N
        TnoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnoTelpKeyPressed(evt);
            }
        });
        FormInput.add(TnoTelp);
        TnoTelp.setBounds(555, 745, 175, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("6.2 Bagaimana Rencana Perawatan Selanjutnya ?");
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 773, 340, 23);

        cmbRencana.setForeground(new java.awt.Color(0, 0, 0));
        cmbRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tetap dirawat di RS", "Dirawat di rumah" }));
        cmbRencana.setName("cmbRencana"); // NOI18N
        cmbRencana.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRencanaActionPerformed(evt);
            }
        });
        FormInput.add(cmbRencana);
        cmbRencana.setBounds(350, 773, 130, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Apakah Lingkungan Rumah Sudah Disiapkan ?");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 801, 340, 23);

        cmbDisiapkan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDisiapkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbDisiapkan.setName("cmbDisiapkan"); // NOI18N
        cmbDisiapkan.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbDisiapkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDisiapkanActionPerformed(evt);
            }
        });
        FormInput.add(cmbDisiapkan);
        cmbDisiapkan.setBounds(350, 801, 60, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Jika Ya, Apakah Ada Yang Mampu Merawat Pasien Di Rumah ?");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 829, 340, 23);

        cmbMampu.setForeground(new java.awt.Color(0, 0, 0));
        cmbMampu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya, Oleh", "Tidak" }));
        cmbMampu.setName("cmbMampu"); // NOI18N
        cmbMampu.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMampu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMampuActionPerformed(evt);
            }
        });
        FormInput.add(cmbMampu);
        cmbMampu.setBounds(350, 829, 75, 23);

        TketMampu.setForeground(new java.awt.Color(0, 0, 0));
        TketMampu.setName("TketMampu"); // NOI18N
        TketMampu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketMampuKeyPressed(evt);
            }
        });
        FormInput.add(TketMampu);
        TketMampu.setBounds(430, 829, 300, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("<html>Jika Tidak, Apakah Perlu Difasilitasi RS (<i>Home Care</i>) ?</html>");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 857, 340, 23);

        cmbDifasilitasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDifasilitasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak", "Ya" }));
        cmbDifasilitasi.setName("cmbDifasilitasi"); // NOI18N
        cmbDifasilitasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbDifasilitasi);
        cmbDifasilitasi.setBounds(350, 857, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("6.3 Reaksi Pasien Atas Penyakitnya : Asesmen Informasi");
        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 885, 380, 23);

        chkMenyangkal.setBackground(new java.awt.Color(255, 255, 250));
        chkMenyangkal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMenyangkal.setForeground(new java.awt.Color(0, 0, 0));
        chkMenyangkal.setText("Menyangkal");
        chkMenyangkal.setBorderPainted(true);
        chkMenyangkal.setBorderPaintedFlat(true);
        chkMenyangkal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMenyangkal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMenyangkal.setName("chkMenyangkal"); // NOI18N
        chkMenyangkal.setOpaque(false);
        chkMenyangkal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMenyangkal);
        chkMenyangkal.setBounds(115, 913, 90, 23);

        chkMarah.setBackground(new java.awt.Color(255, 255, 250));
        chkMarah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMarah.setForeground(new java.awt.Color(0, 0, 0));
        chkMarah.setText("Marah");
        chkMarah.setBorderPainted(true);
        chkMarah.setBorderPaintedFlat(true);
        chkMarah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMarah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMarah.setName("chkMarah"); // NOI18N
        chkMarah.setOpaque(false);
        chkMarah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMarah);
        chkMarah.setBounds(115, 941, 60, 23);

        chkTakut.setBackground(new java.awt.Color(255, 255, 250));
        chkTakut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTakut.setForeground(new java.awt.Color(0, 0, 0));
        chkTakut.setText("Takut");
        chkTakut.setBorderPainted(true);
        chkTakut.setBorderPaintedFlat(true);
        chkTakut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTakut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTakut.setName("chkTakut"); // NOI18N
        chkTakut.setOpaque(false);
        chkTakut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTakut);
        chkTakut.setBounds(220, 913, 60, 23);

        chkSedih.setBackground(new java.awt.Color(255, 255, 250));
        chkSedih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSedih.setForeground(new java.awt.Color(0, 0, 0));
        chkSedih.setText("Sedih / Menangis");
        chkSedih.setBorderPainted(true);
        chkSedih.setBorderPaintedFlat(true);
        chkSedih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedih.setName("chkSedih"); // NOI18N
        chkSedih.setOpaque(false);
        chkSedih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedih);
        chkSedih.setBounds(220, 941, 110, 23);

        chkRasa63.setBackground(new java.awt.Color(255, 255, 250));
        chkRasa63.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRasa63.setForeground(new java.awt.Color(0, 0, 0));
        chkRasa63.setText("Rasa Bersalah");
        chkRasa63.setBorderPainted(true);
        chkRasa63.setBorderPaintedFlat(true);
        chkRasa63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRasa63.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRasa63.setName("chkRasa63"); // NOI18N
        chkRasa63.setOpaque(false);
        chkRasa63.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRasa63);
        chkRasa63.setBounds(350, 913, 110, 23);

        chkKetidakberdayaan.setBackground(new java.awt.Color(255, 255, 250));
        chkKetidakberdayaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetidakberdayaan.setForeground(new java.awt.Color(0, 0, 0));
        chkKetidakberdayaan.setText("Ketidak Berdayaan");
        chkKetidakberdayaan.setBorderPainted(true);
        chkKetidakberdayaan.setBorderPaintedFlat(true);
        chkKetidakberdayaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetidakberdayaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetidakberdayaan.setName("chkKetidakberdayaan"); // NOI18N
        chkKetidakberdayaan.setOpaque(false);
        chkKetidakberdayaan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetidakberdayaan);
        chkKetidakberdayaan.setBounds(350, 941, 130, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText(" : Masalah Keperawatan *");
        jLabel78.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 969, 260, 23);

        chkAnxietas.setBackground(new java.awt.Color(255, 255, 250));
        chkAnxietas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnxietas.setForeground(new java.awt.Color(0, 0, 0));
        chkAnxietas.setText("Anxietas");
        chkAnxietas.setBorderPainted(true);
        chkAnxietas.setBorderPaintedFlat(true);
        chkAnxietas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnxietas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnxietas.setName("chkAnxietas"); // NOI18N
        chkAnxietas.setOpaque(false);
        chkAnxietas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnxietas);
        chkAnxietas.setBounds(270, 969, 80, 23);

        chkDistress.setBackground(new java.awt.Color(255, 255, 250));
        chkDistress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistress.setForeground(new java.awt.Color(0, 0, 0));
        chkDistress.setText("Distress Spriritual");
        chkDistress.setBorderPainted(true);
        chkDistress.setBorderPaintedFlat(true);
        chkDistress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistress.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistress.setName("chkDistress"); // NOI18N
        chkDistress.setOpaque(false);
        chkDistress.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistress);
        chkDistress.setBounds(370, 969, 120, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("6.4 Reaksi Keluarga Atas Penyakit Pasien : Asesmen Informasi");
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 997, 410, 23);

        chkMarah64.setBackground(new java.awt.Color(255, 255, 250));
        chkMarah64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMarah64.setForeground(new java.awt.Color(0, 0, 0));
        chkMarah64.setText("Marah");
        chkMarah64.setBorderPainted(true);
        chkMarah64.setBorderPaintedFlat(true);
        chkMarah64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMarah64.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMarah64.setName("chkMarah64"); // NOI18N
        chkMarah64.setOpaque(false);
        chkMarah64.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMarah64);
        chkMarah64.setBounds(115, 1025, 60, 23);

        chkGangguan.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguan.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguan.setText("Gangguan tidur");
        chkGangguan.setBorderPainted(true);
        chkGangguan.setBorderPaintedFlat(true);
        chkGangguan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguan.setName("chkGangguan"); // NOI18N
        chkGangguan.setOpaque(false);
        chkGangguan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguan);
        chkGangguan.setBounds(115, 1053, 110, 23);

        chkPenurunanKonsentrasi.setBackground(new java.awt.Color(255, 255, 250));
        chkPenurunanKonsentrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenurunanKonsentrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkPenurunanKonsentrasi.setText("Penurunan konsentrasi");
        chkPenurunanKonsentrasi.setBorderPainted(true);
        chkPenurunanKonsentrasi.setBorderPaintedFlat(true);
        chkPenurunanKonsentrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenurunanKonsentrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenurunanKonsentrasi.setName("chkPenurunanKonsentrasi"); // NOI18N
        chkPenurunanKonsentrasi.setOpaque(false);
        chkPenurunanKonsentrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenurunanKonsentrasi);
        chkPenurunanKonsentrasi.setBounds(115, 1081, 150, 23);

        chkKetidakmampuan.setBackground(new java.awt.Color(255, 255, 250));
        chkKetidakmampuan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetidakmampuan.setForeground(new java.awt.Color(0, 0, 0));
        chkKetidakmampuan.setText("Ketidakmampuan memenuhi peran yang diharapkan");
        chkKetidakmampuan.setBorderPainted(true);
        chkKetidakmampuan.setBorderPaintedFlat(true);
        chkKetidakmampuan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetidakmampuan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetidakmampuan.setName("chkKetidakmampuan"); // NOI18N
        chkKetidakmampuan.setOpaque(false);
        chkKetidakmampuan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetidakmampuan);
        chkKetidakmampuan.setBounds(280, 1025, 280, 23);

        chkKeluargaKurangKomunikasi.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluargaKurangKomunikasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluargaKurangKomunikasi.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluargaKurangKomunikasi.setText("Keluarga kurang berkomunikasi dengan pasien");
        chkKeluargaKurangKomunikasi.setBorderPainted(true);
        chkKeluargaKurangKomunikasi.setBorderPaintedFlat(true);
        chkKeluargaKurangKomunikasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluargaKurangKomunikasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluargaKurangKomunikasi.setName("chkKeluargaKurangKomunikasi"); // NOI18N
        chkKeluargaKurangKomunikasi.setOpaque(false);
        chkKeluargaKurangKomunikasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeluargaKurangKomunikasi);
        chkKeluargaKurangKomunikasi.setBounds(280, 1053, 250, 23);

        chkLetih.setBackground(new java.awt.Color(255, 255, 250));
        chkLetih.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLetih.setForeground(new java.awt.Color(0, 0, 0));
        chkLetih.setText("Letih / lelah");
        chkLetih.setBorderPainted(true);
        chkLetih.setBorderPaintedFlat(true);
        chkLetih.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLetih.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLetih.setName("chkLetih"); // NOI18N
        chkLetih.setOpaque(false);
        chkLetih.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLetih);
        chkLetih.setBounds(280, 1081, 90, 23);

        chkRasa64.setBackground(new java.awt.Color(255, 255, 250));
        chkRasa64.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRasa64.setForeground(new java.awt.Color(0, 0, 0));
        chkRasa64.setText("Rasa bersalah");
        chkRasa64.setBorderPainted(true);
        chkRasa64.setBorderPaintedFlat(true);
        chkRasa64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRasa64.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRasa64.setName("chkRasa64"); // NOI18N
        chkRasa64.setOpaque(false);
        chkRasa64.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRasa64);
        chkRasa64.setBounds(570, 1025, 100, 23);

        chkPerubahanKebiasaan.setBackground(new java.awt.Color(255, 255, 250));
        chkPerubahanKebiasaan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerubahanKebiasaan.setForeground(new java.awt.Color(0, 0, 0));
        chkPerubahanKebiasaan.setText("Perubahan kebiasaan pola komunikasi");
        chkPerubahanKebiasaan.setBorderPainted(true);
        chkPerubahanKebiasaan.setBorderPaintedFlat(true);
        chkPerubahanKebiasaan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerubahanKebiasaan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerubahanKebiasaan.setName("chkPerubahanKebiasaan"); // NOI18N
        chkPerubahanKebiasaan.setOpaque(false);
        chkPerubahanKebiasaan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerubahanKebiasaan);
        chkPerubahanKebiasaan.setBounds(570, 1053, 210, 23);

        chkKeluargaKurangPartisipasi.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluargaKurangPartisipasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluargaKurangPartisipasi.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluargaKurangPartisipasi.setText("<html>Keluarga kurang berpartisipasi membuat keputusan<br>dalam perawatan pasien</html>");
        chkKeluargaKurangPartisipasi.setBorderPainted(true);
        chkKeluargaKurangPartisipasi.setBorderPaintedFlat(true);
        chkKeluargaKurangPartisipasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluargaKurangPartisipasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluargaKurangPartisipasi.setName("chkKeluargaKurangPartisipasi"); // NOI18N
        chkKeluargaKurangPartisipasi.setOpaque(false);
        chkKeluargaKurangPartisipasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeluargaKurangPartisipasi);
        chkKeluargaKurangPartisipasi.setBounds(570, 1081, 280, 30);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText(" : Masalah Keperawatan *");
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1109, 260, 23);

        chkKoping.setBackground(new java.awt.Color(255, 255, 250));
        chkKoping.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKoping.setForeground(new java.awt.Color(0, 0, 0));
        chkKoping.setText("Koping individu tidak efektif");
        chkKoping.setBorderPainted(true);
        chkKoping.setBorderPaintedFlat(true);
        chkKoping.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKoping.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKoping.setName("chkKoping"); // NOI18N
        chkKoping.setOpaque(false);
        chkKoping.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKoping);
        chkKoping.setBounds(270, 1109, 160, 23);

        chkDistressSpiritual.setBackground(new java.awt.Color(255, 255, 250));
        chkDistressSpiritual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistressSpiritual.setForeground(new java.awt.Color(0, 0, 0));
        chkDistressSpiritual.setText("Distress Spriritual");
        chkDistressSpiritual.setBorderPainted(true);
        chkDistressSpiritual.setBorderPaintedFlat(true);
        chkDistressSpiritual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistressSpiritual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistressSpiritual.setName("chkDistressSpiritual"); // NOI18N
        chkDistressSpiritual.setOpaque(false);
        chkDistressSpiritual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistressSpiritual);
        chkDistressSpiritual.setBounds(440, 1109, 120, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("* Selanjutnya gunakan formulir rencana asuhan keperawatan sesuai dengan masalah keperawatan");
        jLabel81.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 1137, 520, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("7. Kebutuhan Dukungan Atau Kelonggaran Pelayanan Bagi Pasien, Keluarga Dan Pemberi Pelayanan Lain :");
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1165, 630, 23);

        chkPasienPerlu.setBackground(new java.awt.Color(255, 255, 250));
        chkPasienPerlu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPasienPerlu.setForeground(new java.awt.Color(0, 0, 0));
        chkPasienPerlu.setText("Pasien perlu didampingi keluarga");
        chkPasienPerlu.setBorderPainted(true);
        chkPasienPerlu.setBorderPaintedFlat(true);
        chkPasienPerlu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPasienPerlu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPasienPerlu.setName("chkPasienPerlu"); // NOI18N
        chkPasienPerlu.setOpaque(false);
        chkPasienPerlu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPasienPerlu);
        chkPasienPerlu.setBounds(115, 1193, 190, 23);

        chkKeluargaDapat.setBackground(new java.awt.Color(255, 255, 250));
        chkKeluargaDapat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKeluargaDapat.setForeground(new java.awt.Color(0, 0, 0));
        chkKeluargaDapat.setText("Keluarga dapat mengunjungi pasien di luar waktu berkunjung");
        chkKeluargaDapat.setBorderPainted(true);
        chkKeluargaDapat.setBorderPaintedFlat(true);
        chkKeluargaDapat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKeluargaDapat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKeluargaDapat.setName("chkKeluargaDapat"); // NOI18N
        chkKeluargaDapat.setOpaque(false);
        chkKeluargaDapat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKeluargaDapat);
        chkKeluargaDapat.setBounds(115, 1221, 320, 23);

        chkSahabat.setBackground(new java.awt.Color(255, 255, 250));
        chkSahabat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSahabat.setForeground(new java.awt.Color(0, 0, 0));
        chkSahabat.setText("Sahabat dapat mengunjungi pasien di luar waktu berkunjung");
        chkSahabat.setBorderPainted(true);
        chkSahabat.setBorderPaintedFlat(true);
        chkSahabat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSahabat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSahabat.setName("chkSahabat"); // NOI18N
        chkSahabat.setOpaque(false);
        chkSahabat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSahabat);
        chkSahabat.setBounds(115, 1249, 320, 23);

        chkLainya7.setBackground(new java.awt.Color(255, 255, 250));
        chkLainya7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainya7.setForeground(new java.awt.Color(0, 0, 0));
        chkLainya7.setText("Lainnya :");
        chkLainya7.setBorderPainted(true);
        chkLainya7.setBorderPaintedFlat(true);
        chkLainya7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainya7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainya7.setName("chkLainya7"); // NOI18N
        chkLainya7.setOpaque(false);
        chkLainya7.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainya7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainya7ActionPerformed(evt);
            }
        });
        FormInput.add(chkLainya7);
        chkLainya7.setBounds(115, 1277, 70, 23);

        TketLain7.setForeground(new java.awt.Color(0, 0, 0));
        TketLain7.setName("TketLain7"); // NOI18N
        TketLain7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLain7KeyPressed(evt);
            }
        });
        FormInput.add(TketLain7);
        TketLain7.setBounds(185, 1277, 545, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("8. Apakah Ada Kebutuhan Akan Alternatif Atau Tingkat Pelayanan Lain :");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(0, 1305, 440, 23);

        chkTidak.setBackground(new java.awt.Color(255, 255, 250));
        chkTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidak.setForeground(new java.awt.Color(0, 0, 0));
        chkTidak.setText("Tidak");
        chkTidak.setBorderPainted(true);
        chkTidak.setBorderPaintedFlat(true);
        chkTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidak.setName("chkTidak"); // NOI18N
        chkTidak.setOpaque(false);
        chkTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTidak);
        chkTidak.setBounds(115, 1333, 55, 23);

        chkAutopsi.setBackground(new java.awt.Color(255, 255, 250));
        chkAutopsi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAutopsi.setForeground(new java.awt.Color(0, 0, 0));
        chkAutopsi.setText("Autopsi");
        chkAutopsi.setBorderPainted(true);
        chkAutopsi.setBorderPaintedFlat(true);
        chkAutopsi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAutopsi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAutopsi.setName("chkAutopsi"); // NOI18N
        chkAutopsi.setOpaque(false);
        chkAutopsi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAutopsi);
        chkAutopsi.setBounds(180, 1333, 70, 23);

        chkDonasi.setBackground(new java.awt.Color(255, 255, 250));
        chkDonasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDonasi.setForeground(new java.awt.Color(0, 0, 0));
        chkDonasi.setText("Donasi Organ :");
        chkDonasi.setBorderPainted(true);
        chkDonasi.setBorderPaintedFlat(true);
        chkDonasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDonasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDonasi.setName("chkDonasi"); // NOI18N
        chkDonasi.setOpaque(false);
        chkDonasi.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDonasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDonasiActionPerformed(evt);
            }
        });
        FormInput.add(chkDonasi);
        chkDonasi.setBounds(260, 1333, 100, 23);

        TketDonasi.setForeground(new java.awt.Color(0, 0, 0));
        TketDonasi.setName("TketDonasi"); // NOI18N
        TketDonasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketDonasiKeyPressed(evt);
            }
        });
        FormInput.add(TketDonasi);
        TketDonasi.setBounds(360, 1333, 370, 23);

        chkLainya8.setBackground(new java.awt.Color(255, 255, 250));
        chkLainya8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainya8.setForeground(new java.awt.Color(0, 0, 0));
        chkLainya8.setText("Lainnya :");
        chkLainya8.setBorderPainted(true);
        chkLainya8.setBorderPaintedFlat(true);
        chkLainya8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainya8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainya8.setName("chkLainya8"); // NOI18N
        chkLainya8.setOpaque(false);
        chkLainya8.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainya8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainya8ActionPerformed(evt);
            }
        });
        FormInput.add(chkLainya8);
        chkLainya8.setBounds(115, 1361, 70, 23);

        TketLain8.setForeground(new java.awt.Color(0, 0, 0));
        TketLain8.setName("TketLain8"); // NOI18N
        TketLain8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLain8KeyPressed(evt);
            }
        });
        FormInput.add(TketLain8);
        TketLain8.setBounds(185, 1361, 545, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("9. Faktor Resiko Bagi Keluarga Yang Ditinggalkan : Asesmen Informasi :");
        jLabel84.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1389, 440, 23);

        chkMarah9.setBackground(new java.awt.Color(255, 255, 250));
        chkMarah9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMarah9.setForeground(new java.awt.Color(0, 0, 0));
        chkMarah9.setText("Marah");
        chkMarah9.setBorderPainted(true);
        chkMarah9.setBorderPaintedFlat(true);
        chkMarah9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMarah9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMarah9.setName("chkMarah9"); // NOI18N
        chkMarah9.setOpaque(false);
        chkMarah9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMarah9);
        chkMarah9.setBounds(115, 1417, 60, 23);

        chkPerubahanKebiasaan9.setBackground(new java.awt.Color(255, 255, 250));
        chkPerubahanKebiasaan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerubahanKebiasaan9.setForeground(new java.awt.Color(0, 0, 0));
        chkPerubahanKebiasaan9.setText("Perubahan kebiasaan pola komunikasi");
        chkPerubahanKebiasaan9.setBorderPainted(true);
        chkPerubahanKebiasaan9.setBorderPaintedFlat(true);
        chkPerubahanKebiasaan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerubahanKebiasaan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerubahanKebiasaan9.setName("chkPerubahanKebiasaan9"); // NOI18N
        chkPerubahanKebiasaan9.setOpaque(false);
        chkPerubahanKebiasaan9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerubahanKebiasaan9);
        chkPerubahanKebiasaan9.setBounds(225, 1417, 210, 23);

        chkGangguan9.setBackground(new java.awt.Color(255, 255, 250));
        chkGangguan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGangguan9.setForeground(new java.awt.Color(0, 0, 0));
        chkGangguan9.setText("Gangguan tidur");
        chkGangguan9.setBorderPainted(true);
        chkGangguan9.setBorderPaintedFlat(true);
        chkGangguan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGangguan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGangguan9.setName("chkGangguan9"); // NOI18N
        chkGangguan9.setOpaque(false);
        chkGangguan9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkGangguan9);
        chkGangguan9.setBounds(515, 1417, 110, 23);

        chkDepresi.setBackground(new java.awt.Color(255, 255, 250));
        chkDepresi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDepresi.setForeground(new java.awt.Color(0, 0, 0));
        chkDepresi.setText("Depresi");
        chkDepresi.setBorderPainted(true);
        chkDepresi.setBorderPaintedFlat(true);
        chkDepresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDepresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDepresi.setName("chkDepresi"); // NOI18N
        chkDepresi.setOpaque(false);
        chkDepresi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDepresi);
        chkDepresi.setBounds(115, 1445, 70, 23);

        chkKetidakmampuan9.setBackground(new java.awt.Color(255, 255, 250));
        chkKetidakmampuan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKetidakmampuan9.setForeground(new java.awt.Color(0, 0, 0));
        chkKetidakmampuan9.setText("Ketidak mampuan memenuhi peran yang diharapkan");
        chkKetidakmampuan9.setBorderPainted(true);
        chkKetidakmampuan9.setBorderPaintedFlat(true);
        chkKetidakmampuan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKetidakmampuan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKetidakmampuan9.setName("chkKetidakmampuan9"); // NOI18N
        chkKetidakmampuan9.setOpaque(false);
        chkKetidakmampuan9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKetidakmampuan9);
        chkKetidakmampuan9.setBounds(225, 1445, 280, 23);

        chkSedih9.setBackground(new java.awt.Color(255, 255, 250));
        chkSedih9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSedih9.setForeground(new java.awt.Color(0, 0, 0));
        chkSedih9.setText("Sedih / Menangis");
        chkSedih9.setBorderPainted(true);
        chkSedih9.setBorderPaintedFlat(true);
        chkSedih9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedih9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedih9.setName("chkSedih9"); // NOI18N
        chkSedih9.setOpaque(false);
        chkSedih9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedih9);
        chkSedih9.setBounds(515, 1445, 110, 23);

        chkRasa9.setBackground(new java.awt.Color(255, 255, 250));
        chkRasa9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRasa9.setForeground(new java.awt.Color(0, 0, 0));
        chkRasa9.setText("Rasa bersalah");
        chkRasa9.setBorderPainted(true);
        chkRasa9.setBorderPaintedFlat(true);
        chkRasa9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRasa9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRasa9.setName("chkRasa9"); // NOI18N
        chkRasa9.setOpaque(false);
        chkRasa9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRasa9);
        chkRasa9.setBounds(115, 1473, 100, 23);

        chkLetih9.setBackground(new java.awt.Color(255, 255, 250));
        chkLetih9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLetih9.setForeground(new java.awt.Color(0, 0, 0));
        chkLetih9.setText("Letih / lelah");
        chkLetih9.setBorderPainted(true);
        chkLetih9.setBorderPaintedFlat(true);
        chkLetih9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLetih9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLetih9.setName("chkLetih9"); // NOI18N
        chkLetih9.setOpaque(false);
        chkLetih9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLetih9);
        chkLetih9.setBounds(225, 1473, 90, 23);

        chkPenurunan9.setBackground(new java.awt.Color(255, 255, 250));
        chkPenurunan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenurunan9.setForeground(new java.awt.Color(0, 0, 0));
        chkPenurunan9.setText("Penurunan konsentrasi");
        chkPenurunan9.setBorderPainted(true);
        chkPenurunan9.setBorderPaintedFlat(true);
        chkPenurunan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenurunan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenurunan9.setName("chkPenurunan9"); // NOI18N
        chkPenurunan9.setOpaque(false);
        chkPenurunan9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenurunan9);
        chkPenurunan9.setBounds(515, 1473, 150, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText(" : Masalah Keperawatan *");
        jLabel85.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(0, 1501, 260, 23);

        chkKoping9.setBackground(new java.awt.Color(255, 255, 250));
        chkKoping9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKoping9.setForeground(new java.awt.Color(0, 0, 0));
        chkKoping9.setText("Koping individu tidak efektif");
        chkKoping9.setBorderPainted(true);
        chkKoping9.setBorderPaintedFlat(true);
        chkKoping9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKoping9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKoping9.setName("chkKoping9"); // NOI18N
        chkKoping9.setOpaque(false);
        chkKoping9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKoping9);
        chkKoping9.setBounds(270, 1501, 160, 23);

        chkDistress9.setBackground(new java.awt.Color(255, 255, 250));
        chkDistress9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDistress9.setForeground(new java.awt.Color(0, 0, 0));
        chkDistress9.setText("Distress Spriritual");
        chkDistress9.setBorderPainted(true);
        chkDistress9.setBorderPaintedFlat(true);
        chkDistress9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDistress9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDistress9.setName("chkDistress9"); // NOI18N
        chkDistress9.setOpaque(false);
        chkDistress9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDistress9);
        chkDistress9.setBounds(440, 1501, 120, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Nama Dokter :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1529, 110, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        FormInput.add(TnmDokter);
        TnmDokter.setBounds(115, 1529, 390, 23);

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
        BtnDokter.setBounds(510, 1529, 28, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Nama Pembuat Pernyataan :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 1557, 180, 23);

        TnmPembuat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPembuat.setName("TnmPembuat"); // NOI18N
        FormInput.add(TnmPembuat);
        TnmPembuat.setBounds(185, 1557, 390, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(900, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout());

        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setToolTipText("Silahkan Klik Untuk Membaca CPPT");
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(22, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2rightarrow.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/2leftarrow.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormMenu.setBackground(new java.awt.Color(250, 250, 245));
        FormMenu.setName("FormMenu"); // NOI18N
        FormMenu.setPreferredSize(new java.awt.Dimension(150, 483));
        FormMenu.setLayout(new java.awt.GridLayout(1, 2));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " CPPT ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        tbCPPT.setToolTipText("Silahkan klik untuk memilih data yang dibaca cpptnya");
        tbCPPT.setName("tbCPPT"); // NOI18N
        tbCPPT.getTableHeader().setReorderingAllowed(false);
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

        FormMenu.add(Scroll4);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 300));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        scrollPane5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemeriksaan, Analisa, Rencana, Penatalaksanaan Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane5.setName("scrollPane5"); // NOI18N
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 450));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
        Thasil.setToolTipText("Silahkan klik kanan utk. copy data CPPT hasil pemeriksaan");
        Thasil.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Thasil.setName("Thasil"); // NOI18N
        Thasil.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane5.setViewportView(Thasil);

        panelGlass14.add(scrollPane5, java.awt.BorderLayout.PAGE_START);

        scrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Instruksi Tenaga Kesehatan Termasuk Pasca Bedah/Prosedur ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        scrollPane4.setName("scrollPane4"); // NOI18N
        scrollPane4.setPreferredSize(new java.awt.Dimension(212, 150));

        Tinstruksi.setEditable(false);
        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setToolTipText("Silahkan klik kanan utk. copy data CPPT instruksi nakes");
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        internalFrame2.add(PanelAccor, java.awt.BorderLayout.EAST);

        TabRawat.addTab("Input Assesmen", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAsesmen.setComponentPopupMenu(jPopupMenu1);
        tbAsesmen.setName("tbAsesmen"); // NOI18N
        tbAsesmen.getTableHeader().setReorderingAllowed(false);
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

        panelGlass10.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(282, 44));
        panelGlass13.setLayout(null);

        panelGlass23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane7.setName("scrollPane7"); // NOI18N
        scrollPane7.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane7.setViewportView(gambarQR);

        panelGlass23.add(scrollPane7);

        panelGlass13.add(panelGlass23);
        panelGlass23.setBounds(12, 10, 230, 245);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel93.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel93.setName("jLabel93"); // NOI18N
        jLabel93.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelGlass13.add(jLabel93);
        jLabel93.setBounds(20, 262, 210, 60);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass13.add(Scroll5);
        Scroll5.setBounds(12, 335, 260, 240);

        panelGlass10.add(panelGlass13, java.awt.BorderLayout.EAST);

        internalFrame3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Assesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(100, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-08-2026" }));
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
        BtnCari.setMnemonic('T');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+T");
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

        TabRawat.addTab("Data Assesmen", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("asesmen_pasien_terminal", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 103, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        cekDyspnoe, cekNafasTak, cekAdaSekret, cekNafasCepat, cekNafasMelalui, cekSpo2, cekNafasLambat, cekMukosa, cekTak11, cekMual1, cekSulitMenelan,
                        cekInkonAlvi, cekPenurunan, cekDistensi, cekTak12, cekSulitBicara, cekInkonUrin, cmbNyeri.getSelectedItem().toString(), TketNyeri.getText(),
                        cekBercak, cekGelisah, cekLemas, cekKulit, cekTekanan, cekNadi, cekTak14, cekMelakukanAktivitas, cekPindah, cekLainya, TketLain2.getText(),
                        cekMual3, cekPerubahanPersepsi, cekNyeriAkut, cekPolaNafas, cekKonstipasi, cekNyeriKronis, cekBersihan, cekDefisit, cmbSpiritual.getSelectedItem().toString(),
                        TketSpiritual.getText(), cmbDoa.getSelectedItem().toString(), cmbBimbingan.getSelectedItem().toString(), cmbPendamping.getSelectedItem().toString(),
                        cmbDihubungi.getSelectedItem().toString(), Tsiapa.getText(), Thubungan.getText(), Tdimana.getText(), TnoTelp.getText(), cmbRencana.getSelectedItem().toString(),
                        cmbDisiapkan.getSelectedItem().toString(), cmbMampu.getSelectedItem().toString(), TketMampu.getText(), cmbDifasilitasi.getSelectedItem().toString(),
                        cekMenyangkal, cekMarah, cekTakut, cekSedih, cekRasa63, cekKetidakberdayaan, cekAnxietas, cekDistress, cekMarah64, cekGangguan, cekPenurunanKonsentrasi,
                        cekKetidakmampuan, cekKeluargaKurangKomunikasi, cekKoping, cekLetih, cekRasa64, cekPerubahanKebiasaan, cekKeluargaKurangPartisipasi, cekDistressSpiritual,
                        cekPasienPerlu, cekKeluargaDapat, cekSahabat, cekLainya7, TketLain7.getText(), cekTidak, cekAutopsi, cekDonasi, TketDonasi.getText(), cekLainya8,
                        TketLain8.getText(), cekMarah9, cekDepresi, cekRasa9, cekPerubahanKebiasaan9, cekKetidakmampuan9, cekKoping9, cekLetih9, cekGangguan9, cekSedih9,
                        cekPenurunan9, cekDistress9, nipDokter, TnmPembuat.getText(), nipPetugas, "", Sequel.cariIsi("select now()")
                    }) == true) {
                
                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Pasien Terminal Dan Keluarganya", "Simpan");
                TCari.setText(TNoRw.getText());
                TabRawat.setSelectedIndex(1);
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

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
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from asesmen_pasien_terminal where no_rawat=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
                    if (!idFileTtd.equals("")) {
                        Sequel.hapusSemuaTtd(idFileTtd);
                    }
                    
                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("asesmen_pasien_terminal", "no_rawat=?", "tgl_asesmen=?, jam_asesmen=?, cek_dyspnoe=?, cek_nafas_tak=?, cek_ada_sekret=?, cek_nafas_cepat=?, "
                        + "cek_nafas_melalui=?, cek_spo2=?, cek_nafas_lambat=?, cek_mukosa=?, cek_tak11=?, cek_mual1=?, cek_sulit_menelan=?, cek_inkon_alvi=?, cek_penurunan=?, "
                        + "cek_distensi=?, cek_tak12=?, cek_sulit_bicara=?, cek_inkon_urin=?, nyeri=?, ket_nyeri=?, cek_bercak=?, cek_gelisah=?, cek_lemas=?, cek_kulit=?, cek_tekanan=?, "
                        + "cek_nadi=?, cek_tak14=?, cek_melakukan_aktivitas=?, cek_pindah=?, cek_lainya=?, ket_lainya=?, cek_mual3=?, cek_perubahan_persepsi=?, cek_nyeri_akut=?, "
                        + "cek_pola_nafas=?, cek_konstipasi=?, cek_nyeri_kronis=?, cek_bersihan=?, cek_defisit=?, spriritual=?, ket_spriritual=?, didoakan=?, bimbingan=?, pendampingan=?, "
                        + "orang_dihubungi=?, siapa_orang_dihubungi=?, hub_orang_dihubungi=?, dimana_orang_dihubungi=?, telp_orang_dihubungi=?, perawatan_selanjutnya=?, lingkungan_rumah=?, "
                        + "mampu_merawat=?, ket_mampu_merawat=?, fasilitasi_rs=?, cek_menyangkal=?, cek_marah=?, cek_takut=?, cek_sedih=?, cek_rasa63=?, cek_ketidakberdayaan=?, "
                        + "cek_anxietas=?, cek_distress=?, cek_marah64=?, cek_gangguan=?, cek_penurunan_konsentrasi=?, cek_ketidakmampuan=?, cek_keluarga_kurang_komunikasi=?, cek_koping=?, "
                        + "cek_letih=?, cek_rasa64=?, cek_perubahan_kebiasaan=?, cek_keluarga_kurang_partisipasi=?, cek_distress_spiritual=?, cek_pasien_perlu=?, cek_keluarga_dapat=?, "
                        + "cek_sahabat=?, cek_lainya7=?, ket_lainya7=?, cek_tidak=?, cek_autopsi=?, cek_donasi=?, ket_donasi=?, cek_lainya8=?, ket_lainya8=?, cek_marah9=?, cek_depresi=?, "
                        + "cek_rasa9=?, cek_perubahan_kebiasaan9=?, cek_ketidakmampuan9=?, cek_koping9=?, cek_letih9=?, cek_gangguan9=?, cek_sedih9=?, cek_penurunan9=?, cek_distress9=?, "
                        + "nip_dokter=?, nm_pembuat_pernyataan=?, nip_petugas=?", 100, new String[]{
                            Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            cekDyspnoe, cekNafasTak, cekAdaSekret, cekNafasCepat, cekNafasMelalui, cekSpo2, cekNafasLambat, cekMukosa, cekTak11, cekMual1, cekSulitMenelan,
                            cekInkonAlvi, cekPenurunan, cekDistensi, cekTak12, cekSulitBicara, cekInkonUrin, cmbNyeri.getSelectedItem().toString(), TketNyeri.getText(),
                            cekBercak, cekGelisah, cekLemas, cekKulit, cekTekanan, cekNadi, cekTak14, cekMelakukanAktivitas, cekPindah, cekLainya, TketLain2.getText(),
                            cekMual3, cekPerubahanPersepsi, cekNyeriAkut, cekPolaNafas, cekKonstipasi, cekNyeriKronis, cekBersihan, cekDefisit, cmbSpiritual.getSelectedItem().toString(),
                            TketSpiritual.getText(), cmbDoa.getSelectedItem().toString(), cmbBimbingan.getSelectedItem().toString(), cmbPendamping.getSelectedItem().toString(),
                            cmbDihubungi.getSelectedItem().toString(), Tsiapa.getText(), Thubungan.getText(), Tdimana.getText(), TnoTelp.getText(), cmbRencana.getSelectedItem().toString(),
                            cmbDisiapkan.getSelectedItem().toString(), cmbMampu.getSelectedItem().toString(), TketMampu.getText(), cmbDifasilitasi.getSelectedItem().toString(),
                            cekMenyangkal, cekMarah, cekTakut, cekSedih, cekRasa63, cekKetidakberdayaan, cekAnxietas, cekDistress, cekMarah64, cekGangguan, cekPenurunanKonsentrasi,
                            cekKetidakmampuan, cekKeluargaKurangKomunikasi, cekKoping, cekLetih, cekRasa64, cekPerubahanKebiasaan, cekKeluargaKurangPartisipasi, cekDistressSpiritual,
                            cekPasienPerlu, cekKeluargaDapat, cekSahabat, cekLainya7, TketLain7.getText(), cekTidak, cekAutopsi, cekDonasi, TketDonasi.getText(), cekLainya8,
                            TketLain8.getText(), cekMarah9, cekDepresi, cekRasa9, cekPerubahanKebiasaan9, cekKetidakmampuan9, cekKoping9, cekLetih9, cekGangguan9, cekSedih9,
                            cekPenurunan9, cekDistress9, nipDokter, TnmPembuat.getText(), nipPetugas,
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Pasien Terminal Dan Keluarganya", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                    TabRawat.setSelectedIndex(1);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel....");
                TabRawat.setSelectedIndex(1);
                tampil();
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        WindowNomorDokumenRM.dispose();
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

            param.put("ruangRwt", TrgRawat.getText());
            param.put("tglJamAses", TtglAsesmen.getSelectedItem().toString().replaceAll("-", "/") + "    Pukul : " + cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            
            if (chkDyspnoe.isSelected() == true) {
                param.put("cekDyspnoe", "V");
            } else {
                param.put("cekDyspnoe", "");
            }

            if (chkNafasTak.isSelected() == true) {
                param.put("cekNafasTak", "V");
            } else {
                param.put("cekNafasTak", "");
            }

            if (chkAdaSekret.isSelected() == true) {
                param.put("cekAdaSekret", "V");
            } else {
                param.put("cekAdaSekret", "");
            }

            if (chkNafasCepat.isSelected() == true) {
                param.put("cekNafasCepat", "V");
            } else {
                param.put("cekNafasCepat", "");
            }

            if (chkNafasMelalui.isSelected() == true) {
                param.put("cekNafasMelalui", "V");
            } else {
                param.put("cekNafasMelalui", "");
            }

            if (chkSpo2.isSelected() == true) {
                param.put("cekSpo2", "V");
            } else {
                param.put("cekSpo2", "");
            }

            if (chkNafasLambat.isSelected() == true) {
                param.put("cekNafasLambat", "V");
            } else {
                param.put("cekNafasLambat", "");
            }

            if (chkMukosa.isSelected() == true) {
                param.put("cekMukosa", "V");
            } else {
                param.put("cekMukosa", "");
            }

            if (chkTak11.isSelected() == true) {
                param.put("cekTak11", "V");
            } else {
                param.put("cekTak11", "");
            }

            if (chkMual1.isSelected() == true) {
                param.put("cekMual1", "V");
            } else {
                param.put("cekMual1", "");
            }

            if (chkSulitMenelan.isSelected() == true) {
                param.put("cekSulitMenelan", "V");
            } else {
                param.put("cekSulitMenelan", "");
            }

            if (chkInkonAlvi.isSelected() == true) {
                param.put("cekInkonAlvi", "V");
            } else {
                param.put("cekInkonAlvi", "");
            }

            if (chkPenurunan.isSelected() == true) {
                param.put("cekPenurunan", "V");
            } else {
                param.put("cekPenurunan", "");
            }

            if (chkDistensi.isSelected() == true) {
                param.put("cekDistensi", "V");
            } else {
                param.put("cekDistensi", "");
            }

            if (chkTak12.isSelected() == true) {
                param.put("cekTak12", "V");
            } else {
                param.put("cekTak12", "");
            }

            if (chkSulitBicara.isSelected() == true) {
                param.put("cekSulitBicara", "V");
            } else {
                param.put("cekSulitBicara", "");
            }

            if (chkInkonUrin.isSelected() == true) {
                param.put("cekInkonUrin", "V");
            } else {
                param.put("cekInkonUrin", "");
            }
            
            if (cmbNyeri.getSelectedIndex() == 2) {
                if (TketNyeri.getText().equals("")) {
                    param.put("nyeri", cmbNyeri.getSelectedItem().toString() + ", .............");
                } else {
                    param.put("nyeri", cmbNyeri.getSelectedItem().toString() + ", " + TketNyeri.getText());
                }
            } else {
                param.put("nyeri", cmbNyeri.getSelectedItem().toString());
            }

            if (chkBercak.isSelected() == true) {
                param.put("cekBercak", "V");
            } else {
                param.put("cekBercak", "");
            }

            if (chkGelisah.isSelected() == true) {
                param.put("cekGelisah", "V");
            } else {
                param.put("cekGelisah", "");
            }

            if (chkLemas.isSelected() == true) {
                param.put("cekLemas", "V");
            } else {
                param.put("cekLemas", "");
            }

            if (chkKulit.isSelected() == true) {
                param.put("cekKulit", "V");
            } else {
                param.put("cekKulit", "");
            }

            if (chkTekanan.isSelected() == true) {
                param.put("cekTekanan", "V");
            } else {
                param.put("cekTekanan", "");
            }

            if (chkNadi.isSelected() == true) {
                param.put("cekNadi", "V");
            } else {
                param.put("cekNadi", "");
            }

            if (chkTak14.isSelected() == true) {
                param.put("cekTak14", "V");
            } else {
                param.put("cekTak14", "");
            }

            if (chkMelakukanAktivitas.isSelected() == true) {
                param.put("cekMelakukanAktivitas", "V");
            } else {
                param.put("cekMelakukanAktivitas", "");
            }

            if (chkPindah.isSelected() == true) {
                param.put("cekPindah", "V");
            } else {
                param.put("cekPindah", "");
            }

            if (chkLainya.isSelected() == true) {
                param.put("cekLainya", "V");
                if (TketLain2.getText().equals("")) {
                    param.put("lainya2", chkLainya.getText() + " .........");
                } else {
                    param.put("lainya2", chkLainya.getText() + " " + TketLain2.getText());
                }
            } else {
                param.put("cekLainya", "");
                param.put("lainya2", chkLainya.getText() + " .........");
            }

            if (chkMual3.isSelected() == true) {
                param.put("cekMual3", "V");
            } else {
                param.put("cekMual3", "");
            }

            if (chkPerubahanPersepsi.isSelected() == true) {
                param.put("cekPerubahanPersepsi", "V");
            } else {
                param.put("cekPerubahanPersepsi", "");
            }

            if (chkNyeriAkut.isSelected() == true) {
                param.put("cekNyeriAkut", "V");
            } else {
                param.put("cekNyeriAkut", "");
            }

            if (chkPolaNafas.isSelected() == true) {
                param.put("cekPolaNafas", "V");
            } else {
                param.put("cekPolaNafas", "");
            }

            if (chkKonstipasi.isSelected() == true) {
                param.put("cekKonstipasi", "V");
            } else {
                param.put("cekKonstipasi", "");
            }

            if (chkNyeriKronis.isSelected() == true) {
                param.put("cekNyeriKronis", "V");
            } else {
                param.put("cekNyeriKronis", "");
            }

            if (chkBersihan.isSelected() == true) {
                param.put("cekBersihan", "V");
            } else {
                param.put("cekBersihan", "");
            }

            if (chkDefisit.isSelected() == true) {
                param.put("cekDefisit", "V");
            } else {
                param.put("cekDefisit", "");
            }

            if (cmbSpiritual.getSelectedIndex() == 2) {
                if (TketSpiritual.getText().equals("")) {
                    param.put("spiritual", cmbSpiritual.getSelectedItem().toString() + " : ..........");
                } else {
                    param.put("spiritual", cmbSpiritual.getSelectedItem().toString() + " : " + TketSpiritual.getText());
                }
            } else {
                param.put("spiritual", cmbSpiritual.getSelectedItem().toString());
            }
            
            param.put("didoakan", cmbDoa.getSelectedItem().toString());
            param.put("bimbingan", cmbBimbingan.getSelectedItem().toString());
            param.put("pendamping", cmbPendamping.getSelectedItem().toString());
            param.put("dihubungi", cmbDihubungi.getSelectedItem().toString());
            
            if (Tsiapa.getText().equals("")) {
                param.put("siapa", "..........");
            } else {
                param.put("siapa", Tsiapa.getText());
            }
            
            if (Tdimana.getText().equals("")) {
                param.put("dimana", "..........");
            } else {
                param.put("dimana", Tdimana.getText());
            }
            
            if (Thubungan.getText().equals("")) {
                param.put("hubungan", "..........");
            } else {
                param.put("hubungan", Thubungan.getText());
            }
            
            if (TnoTelp.getText().equals("")) {
                param.put("noTelpn", "..........");
            } else {
                param.put("noTelpn", TnoTelp.getText());
            }
            
            param.put("rencana", cmbRencana.getSelectedItem().toString());
            param.put("disiapkan", cmbDisiapkan.getSelectedItem().toString());
            param.put("difasilitasi", cmbDifasilitasi.getSelectedItem().toString());
            
            if (cmbMampu.getSelectedIndex() == 1) {
                if (TketMampu.getText().equals("")) {
                    param.put("mampuMerawat", cmbMampu.getSelectedItem().toString() + " : ............");
                } else {
                    param.put("mampuMerawat", cmbMampu.getSelectedItem().toString() + " : " + TketMampu.getText());
                }
            } else {
                param.put("mampuMerawat", cmbMampu.getSelectedItem().toString());
            }
            
            if (chkMenyangkal.isSelected() == true) {
                param.put("cekMenyangkal", "V");
            } else {
                param.put("cekMenyangkal", "");
            }

            if (chkMarah.isSelected() == true) {
                param.put("cekMarah", "V");
            } else {
                param.put("cekMarah", "");
            }

            if (chkTakut.isSelected() == true) {
                param.put("cekTakut", "V");
            } else {
                param.put("cekTakut", "");
            }

            if (chkSedih.isSelected() == true) {
                param.put("cekSedih", "V");
            } else {
                param.put("cekSedih", "");
            }

            if (chkRasa63.isSelected() == true) {
                param.put("cekRasa63", "V");
            } else {
                param.put("cekRasa63", "");
            }

            if (chkKetidakberdayaan.isSelected() == true) {
                param.put("cekKetidakberdayaan", "V");
            } else {
                param.put("cekKetidakberdayaan", "");
            }

            if (chkAnxietas.isSelected() == true) {
                param.put("cekAnxietas", "V");
            } else {
                param.put("cekAnxietas", "");
            }

            if (chkDistress.isSelected() == true) {
                param.put("cekDistress", "V");
            } else {
                param.put("cekDistress", "");
            }

            if (chkMarah64.isSelected() == true) {
                param.put("cekMarah64", "V");
            } else {
                param.put("cekMarah64", "");
            }

            if (chkGangguan.isSelected() == true) {
                param.put("cekGangguan", "V");
            } else {
                param.put("cekGangguan", "");
            }

            if (chkPenurunanKonsentrasi.isSelected() == true) {
                param.put("cekPenurunanKonsentrasi", "V");
            } else {
                param.put("cekPenurunanKonsentrasi", "");
            }

            if (chkKetidakmampuan.isSelected() == true) {
                param.put("cekKetidakmampuan", "V");
            } else {
                param.put("cekKetidakmampuan", "");
            }

            if (chkKeluargaKurangKomunikasi.isSelected() == true) {
                param.put("cekKeluargaKurangKomunikasi", "V");
            } else {
                param.put("cekKeluargaKurangKomunikasi", "");
            }

            if (chkKoping.isSelected() == true) {
                param.put("cekKoping", "V");
            } else {
                param.put("cekKoping", "");
            }

            if (chkLetih.isSelected() == true) {
                param.put("cekLetih", "V");
            } else {
                param.put("cekLetih", "");
            }

            if (chkRasa64.isSelected() == true) {
                param.put("cekRasa64", "V");
            } else {
                param.put("cekRasa64", "");
            }

            if (chkPerubahanKebiasaan.isSelected() == true) {
                param.put("cekPerubahanKebiasaan", "V");
            } else {
                param.put("cekPerubahanKebiasaan", "");
            }

            if (chkKeluargaKurangPartisipasi.isSelected() == true) {
                param.put("cekKeluargaKurangPartisipasi", "V");
            } else {
                param.put("cekKeluargaKurangPartisipasi", "");
            }

            if (chkDistressSpiritual.isSelected() == true) {
                param.put("cekDistressSpiritual", "V");
            } else {
                param.put("cekDistressSpiritual", "");
            }

            if (chkPasienPerlu.isSelected() == true) {
                param.put("cekPasienPerlu", "V");
            } else {
                param.put("cekPasienPerlu", "");
            }

            if (chkKeluargaDapat.isSelected() == true) {
                param.put("cekKeluargaDapat", "V");
            } else {
                param.put("cekKeluargaDapat", "");
            }

            if (chkSahabat.isSelected() == true) {
                param.put("cekSahabat", "V");
            } else {
                param.put("cekSahabat", "");
            }

            if (chkLainya7.isSelected() == true) {
                param.put("cekLainya7", "V");
                if (TketLain7.getText().equals("")) {
                    param.put("lainya7", chkLainya7.getText() + " ............");
                } else {
                    param.put("lainya7", chkLainya7.getText() + " " + TketLain7.getText());
                }
            } else {
                param.put("cekLainya7", "");
                param.put("lainya7", chkLainya7.getText() + " ............");
            }

            if (chkTidak.isSelected() == true) {
                param.put("cekTidak", "V");
            } else {
                param.put("cekTidak", "");
            }

            if (chkAutopsi.isSelected() == true) {
                param.put("cekAutopsi", "V");
            } else {
                param.put("cekAutopsi", "");
            }

            if (chkDonasi.isSelected() == true) {
                param.put("cekDonasi", "V");
                if (TketDonasi.getText().equals("")) {
                    param.put("ketDonasi", chkDonasi.getText() + " ............");
                } else {
                    param.put("ketDonasi", chkDonasi.getText() + " " + TketDonasi.getText());
                }
            } else {
                param.put("cekDonasi", "");
                param.put("ketDonasi", chkDonasi.getText() + " ............");
            }

            if (chkLainya8.isSelected() == true) {
                param.put("cekLainya8", "V");
                if (TketLain8.getText().equals("")) {
                    param.put("lainya8", chkLainya8.getText() + " ............");
                } else {
                    param.put("lainya8", chkLainya8.getText() + " " + TketLain8.getText());
                }
            } else {
                param.put("cekLainya8", "");
                param.put("lainya8", chkLainya8.getText() + " ............");
            }

            if (chkMarah9.isSelected() == true) {
                param.put("cekMarah9", "V");
            } else {
                param.put("cekMarah9", "");
            }

            if (chkDepresi.isSelected() == true) {
                param.put("cekDepresi", "V");
            } else {
                param.put("cekDepresi", "");
            }

            if (chkRasa9.isSelected() == true) {
                param.put("cekRasa9", "V");
            } else {
                param.put("cekRasa9", "");
            }

            if (chkPerubahanKebiasaan9.isSelected() == true) {
                param.put("cekPerubahanKebiasaan9", "V");
            } else {
                param.put("cekPerubahanKebiasaan9", "");
            }

            if (chkKetidakmampuan9.isSelected() == true) {
                param.put("cekKetidakmampuan9", "V");
            } else {
                param.put("cekKetidakmampuan9", "");
            }

            if (chkKoping9.isSelected() == true) {
                param.put("cekKoping9", "V");
            } else {
                param.put("cekKoping9", "");
            }

            if (chkLetih9.isSelected() == true) {
                param.put("cekLetih9", "V");
            } else {
                param.put("cekLetih9", "");
            }

            if (chkGangguan9.isSelected() == true) {
                param.put("cekGangguan9", "V");
            } else {
                param.put("cekGangguan9", "");
            }

            if (chkSedih9.isSelected() == true) {
                param.put("cekSedih9", "V");
            } else {
                param.put("cekSedih9", "");
            }

            if (chkPenurunan9.isSelected() == true) {
                param.put("cekPenurunan9", "V");
            } else {
                param.put("cekPenurunan9", "");
            }

            if (chkDistress9.isSelected() == true) {
                param.put("cekDistress9", "V");
            } else {
                param.put("cekDistress9", "");
            }
            
            param.put("dokter", "(" + TnmDokter.getText() + ")");
            param.put("pembuatPernyataan", "(" + TnmPembuat.getText() + ")");
            param.put("petugas", "(" + TnmPetugas.getText() + ")");

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiDokter = "", isiPetugas = "", tglSimpan = "", jamSimpan = "", kalimatFoter = "";
                tglSimpan = Sequel.cariIsi("select date_format('" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString() + "','%d/%m/%Y')");
                jamSimpan = Sequel.cariIsi("select time('" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 109).toString() + "')");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                //dokter
                if (nipDokter.equals("") || nipDokter.equals("-") || nipDokter.equals("--")) {
                    param.put("lokasiQrDokter", "");
                } else {
                    isiDokter = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Pasien Terminal Dan Keluarganya", TnmDokter.getText() + " (Dokter)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                    param.put("lokasiQrDokter", Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg");
                }

                //petugas
                if (nipPetugas.equals("") || nipPetugas.equals("-") || nipPetugas.equals("--")) {
                    param.put("lokasiQrPetugas", "");
                } else {
                    isiPetugas = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Assesmen Pasien Terminal Dan Keluarganya", TnmPetugas.getText() + " (Saksi/Petugas)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPetugas, Sequel.cariFolderTte(), "QRTtePetugas.jpg", "select logo from setting");
                    param.put("lokasiQrPetugas", Sequel.cariFolderTte() + File.separator + "QRTtePetugas.jpg");
                }

                //ttd pembuat pernyataan
                try {
                    StringBuilder htmlContent = new StringBuilder();
                    String gambar = "", ipGambar = "";
                    try {
                        //cek atau ping ip addres
                        ipGambar = "192.168.0.230";
                        InetAddress inet = InetAddress.getByName(ipGambar);

                        //ping sukses timeout 100 ms (0.1 detik)
                        if (inet.isReachable(100)) {
                            if (idFileTtd.equals("")) {
                                gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                            } else {
                                gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileTtd;
                            }
                            //ping gagal
                        } else {
                            gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                        gambar = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    }

                    param.put("gambarTtd", gambar);
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }

                Valid.MyReport("rptAsesmenPasienTerminal2Qr.jasper", "report", "::[ RM Asesmen Pasien Terminal Dan Keluarganya (hal. 2) ]::",
                        "SELECT now() tanggal", param);
                Valid.MyReport("rptAsesmenPasienTerminal1Qr.jasper", "report", "::[ RM Asesmen Pasien Terminal Dan Keluarganya (hal. 1) ]::",
                        "SELECT now() tanggal", param);
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                Valid.MyReport("rptAsesmenPasienTerminal2.jasper", "report", "::[ RM Asesmen Pasien Terminal Dan Keluarganya (hal. 2) ]::",
                        "SELECT now() tanggal", param);
                Valid.MyReport("rptAsesmenPasienTerminal1.jasper", "report", "::[ RM Asesmen Pasien Terminal Dan Keluarganya (hal. 1) ]::",
                        "SELECT now() tanggal", param);
            }
            
            emptTeks();            
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
            tbAsesmen.requestFocus();
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
        ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
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
        ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
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

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            ChkAccor.setSelected(false);
            isMenu();
            scrollKeAtas();
        } else if (TabRawat.getSelectedIndex() == 1) {
            ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (Sequel.cariInteger("select count(-1) from asesmen_pasien_terminal where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_pasien_terminal where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            scrollKeAtas();
        }
        
        ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRekmed);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        isMenu();
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void tbCPPTMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCPPTMouseClicked
        if (tabModeCppt.getRowCount() != 0) {
            try {
                getDataCppt();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbCPPTMouseClicked

    private void tbCPPTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCPPTKeyPressed
        if (tabModeCppt.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataCppt();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbCPPTKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsesmenPasienTerminal");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

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
        ChkAccor.setSelected(false);
        isMenu();

        akses.setform("RMAsesmenPasienTerminal");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        TketNyeri.setText("");
        if (cmbNyeri.getSelectedIndex() == 2) {
            TketNyeri.setEnabled(true);
            TketNyeri.requestFocus();
        } else {
            TketNyeri.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void chkLainyaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainyaActionPerformed
        TketLain2.setText("");
        if (chkLainya.isSelected() == true) {
            TketLain2.setEnabled(true);
            TketLain2.requestFocus();
        } else {
            TketLain2.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainyaActionPerformed

    private void cmbSpiritualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSpiritualActionPerformed
        TketSpiritual.setText("");
        if (cmbSpiritual.getSelectedIndex() == 2) {
            TketSpiritual.setEnabled(true);
            TketSpiritual.requestFocus();
        } else {
            TketSpiritual.setEnabled(false);
        }
    }//GEN-LAST:event_cmbSpiritualActionPerformed

    private void cmbDihubungiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDihubungiActionPerformed
        Tsiapa.setText("");
        Tdimana.setText("");
        Thubungan.setText("");
        TnoTelp.setText("");
        if (cmbDihubungi.getSelectedIndex() == 2) {
            Tsiapa.setEnabled(true);
            Tdimana.setEnabled(true);
            Thubungan.setEnabled(true);
            TnoTelp.setEnabled(true);
            Tsiapa.requestFocus();
        } else {
            Tsiapa.setEnabled(false);
            Tdimana.setEnabled(false);
            Thubungan.setEnabled(false);
            TnoTelp.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDihubungiActionPerformed

    private void cmbRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRencanaActionPerformed
        cmbDisiapkan.setSelectedIndex(0);
        cmbMampu.setSelectedIndex(0);
        TketMampu.setText("");
        cmbDifasilitasi.setSelectedIndex(0);
        cmbDisiapkan.setSelectedIndex(0);

        if (cmbRencana.getSelectedIndex() == 2) {
            cmbDisiapkan.setEnabled(true);
            if (cmbDisiapkan.getSelectedIndex() == 2) {
                cmbMampu.setEnabled(true);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(false);
            } else if (cmbDisiapkan.getSelectedIndex() == 1) {
                cmbMampu.setEnabled(false);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(true);
            } else {
                cmbMampu.setEnabled(false);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(false);
            }
        } else {     
            cmbDisiapkan.setEnabled(false);
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRencanaActionPerformed

    private void cmbDisiapkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDisiapkanActionPerformed
        cmbMampu.setSelectedIndex(0);
        TketMampu.setText("");
        cmbDifasilitasi.setSelectedIndex(0);
        
        if (cmbDisiapkan.getSelectedIndex() == 2) {
            cmbMampu.setEnabled(true);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        } else if (cmbDisiapkan.getSelectedIndex() == 1) {
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(true);
        } else {
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDisiapkanActionPerformed

    private void chkLainya7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainya7ActionPerformed
        TketLain7.setText("");
        if (chkLainya7.isSelected() == true) {
            TketLain7.setEnabled(true);
            TketLain7.requestFocus();
        } else {
            TketLain7.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainya7ActionPerformed

    private void chkDonasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDonasiActionPerformed
        TketDonasi.setText("");
        if (chkDonasi.isSelected() == true) {
            TketDonasi.setEnabled(true);
            TketDonasi.requestFocus();
        } else {
            TketDonasi.setEnabled(false);
        }
    }//GEN-LAST:event_chkDonasiActionPerformed

    private void chkLainya8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainya8ActionPerformed
        TketLain8.setText("");
        if (chkLainya8.isSelected() == true) {
            TketLain8.setEnabled(true);
            TketLain8.requestFocus();
        } else {
            TketLain8.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainya8ActionPerformed

    private void TketNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketNyeriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkBercak.requestFocus();
        }
    }//GEN-LAST:event_TketNyeriKeyPressed

    private void TketLain2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLain2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMual3.requestFocus();
        }
    }//GEN-LAST:event_TketLain2KeyPressed

    private void TketSpiritualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketSpiritualKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDoa.requestFocus();
        }
    }//GEN-LAST:event_TketSpiritualKeyPressed

    private void TsiapaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsiapaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Thubungan.requestFocus();
        }
    }//GEN-LAST:event_TsiapaKeyPressed

    private void ThubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThubunganKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdimana.requestFocus();
        }
    }//GEN-LAST:event_ThubunganKeyPressed

    private void TdimanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdimanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnoTelp.requestFocus();
        }
    }//GEN-LAST:event_TdimanaKeyPressed

    private void TnoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnoTelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRencana.requestFocus();
        }
    }//GEN-LAST:event_TnoTelpKeyPressed

    private void TketMampuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketMampuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMenyangkal.requestFocus();
        }
    }//GEN-LAST:event_TketMampuKeyPressed

    private void TketLain7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLain7KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTidak.requestFocus();
        }
    }//GEN-LAST:event_TketLain7KeyPressed

    private void TketDonasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketDonasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainya8.requestFocus();
        }
    }//GEN-LAST:event_TketDonasiKeyPressed

    private void TketLain8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLain8KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkMarah9.requestFocus();
        }
    }//GEN-LAST:event_TketLain8KeyPressed

    private void cmbMampuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMampuActionPerformed
        TketMampu.setText("");
        if (cmbMampu.getSelectedIndex() == 1) {
            TketMampu.setEnabled(true);
            TketMampu.requestFocus();
        } else {
            TketMampu.setEnabled(false);
        }
    }//GEN-LAST:event_cmbMampuActionPerformed

    private void MnHapusTtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan pembuat pernyataan mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileTtd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Yang membuat pernyataan belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileTtd) == true) {
                                Sequel.mengedit("asesmen_pasien_terminal", "no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'",
                                    "id_file_nm_pembuat_pernyataan=''");
                                tampil();
                                emptTeks();
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
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN PASIEN TERMINAL DAN KELUARGANYA%'") > 0) {
                bikinQR();
            } else {
                WindowNomorDokumenRM.setSize(737, 125);
                WindowNomorDokumenRM.setLocationRelativeTo(internalFrame1);
                WindowNomorDokumenRM.setVisible(true);

                cmbRekmed.setSelectedIndex(0);
                cmbRekmed.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnBikinQrCodeActionPerformed

    private void BtnTampilkanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTampilkanQrActionPerformed
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            if (Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'").equals(akses.getkode())) {
                usernya = akses.getkode();
                pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                        + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
            } else {
                usernya = Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'");
                pwdnya = Sequel.cariIsi("select CAST(AES_DECRYPT(password,'windi') AS CHAR) from user where CAST(AES_DECRYPT(id_user,'nur') AS CHAR)='" + usernya + "'");
            }
        }

        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRekmed.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                    + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRekmed.getSelectedItem().toString() + "'") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMAsesmenPasienTerminal.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                    blob.free();
                }

                BtnCloseIn3ActionPerformed(null);
                tampil();
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRekmed.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        emptTeks();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenPasienTerminal dialog = new RMAsesmenPasienTerminal(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn3;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnHapusTtd;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tdimana;
    private widget.TextArea Thasil;
    private widget.TextBox Thubungan;
    private widget.TextArea Tinstruksi;
    private widget.TextBox TketDonasi;
    private widget.TextBox TketLain2;
    private widget.TextBox TketLain7;
    private widget.TextBox TketLain8;
    private widget.TextBox TketMampu;
    private widget.TextBox TketNyeri;
    private widget.TextBox TketSpiritual;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmPembuat;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnoTelp;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tsiapa;
    private widget.Tanggal TtglAsesmen;
    private javax.swing.JDialog WindowNomorDokumenRM;
    public widget.CekBox chkAdaSekret;
    public widget.CekBox chkAnxietas;
    public widget.CekBox chkAutopsi;
    public widget.CekBox chkBercak;
    public widget.CekBox chkBersihan;
    public widget.CekBox chkDefisit;
    public widget.CekBox chkDepresi;
    public widget.CekBox chkDistensi;
    public widget.CekBox chkDistress;
    public widget.CekBox chkDistress9;
    public widget.CekBox chkDistressSpiritual;
    public widget.CekBox chkDonasi;
    public widget.CekBox chkDyspnoe;
    public widget.CekBox chkGangguan;
    public widget.CekBox chkGangguan9;
    public widget.CekBox chkGelisah;
    public widget.CekBox chkInkonAlvi;
    public widget.CekBox chkInkonUrin;
    public widget.CekBox chkKeluargaDapat;
    public widget.CekBox chkKeluargaKurangKomunikasi;
    public widget.CekBox chkKeluargaKurangPartisipasi;
    public widget.CekBox chkKetidakberdayaan;
    public widget.CekBox chkKetidakmampuan;
    public widget.CekBox chkKetidakmampuan9;
    public widget.CekBox chkKonstipasi;
    public widget.CekBox chkKoping;
    public widget.CekBox chkKoping9;
    public widget.CekBox chkKulit;
    public widget.CekBox chkLainya;
    public widget.CekBox chkLainya7;
    public widget.CekBox chkLainya8;
    public widget.CekBox chkLemas;
    public widget.CekBox chkLetih;
    public widget.CekBox chkLetih9;
    public widget.CekBox chkMarah;
    public widget.CekBox chkMarah64;
    public widget.CekBox chkMarah9;
    public widget.CekBox chkMelakukanAktivitas;
    public widget.CekBox chkMenyangkal;
    public widget.CekBox chkMual1;
    public widget.CekBox chkMual3;
    public widget.CekBox chkMukosa;
    public widget.CekBox chkNadi;
    public widget.CekBox chkNafasCepat;
    public widget.CekBox chkNafasLambat;
    public widget.CekBox chkNafasMelalui;
    public widget.CekBox chkNafasTak;
    public widget.CekBox chkNyeriAkut;
    public widget.CekBox chkNyeriKronis;
    public widget.CekBox chkPasienPerlu;
    public widget.CekBox chkPenurunan;
    public widget.CekBox chkPenurunan9;
    public widget.CekBox chkPenurunanKonsentrasi;
    public widget.CekBox chkPerubahanKebiasaan;
    public widget.CekBox chkPerubahanKebiasaan9;
    public widget.CekBox chkPerubahanPersepsi;
    public widget.CekBox chkPindah;
    public widget.CekBox chkPolaNafas;
    public widget.CekBox chkRasa63;
    public widget.CekBox chkRasa64;
    public widget.CekBox chkRasa9;
    public widget.CekBox chkSahabat;
    public widget.CekBox chkSedih;
    public widget.CekBox chkSedih9;
    public widget.CekBox chkSpo2;
    public widget.CekBox chkSulitBicara;
    public widget.CekBox chkSulitMenelan;
    public widget.CekBox chkTak11;
    public widget.CekBox chkTak12;
    public widget.CekBox chkTak14;
    public widget.CekBox chkTakut;
    public widget.CekBox chkTekanan;
    public widget.CekBox chkTidak;
    private widget.ComboBox cmbBimbingan;
    private widget.ComboBox cmbDifasilitasi;
    private widget.ComboBox cmbDihubungi;
    private widget.ComboBox cmbDisiapkan;
    private widget.ComboBox cmbDoa;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMampu;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPendamping;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRekmed;
    private widget.ComboBox cmbRencana;
    private widget.ComboBox cmbSpiritual;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel125;
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
    private widget.Label jLabel34;
    private widget.Label jLabel6;
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
    private widget.Label jLabel80;
    private widget.Label jLabel81;
    private widget.Label jLabel82;
    private widget.Label jLabel83;
    private widget.Label jLabel84;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel93;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi7;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbAsesmen;
    private widget.Table tbCPPT;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ap.*, p.no_rkm_medis, p.nm_pasien, date_format(ap.tgl_asesmen,'%d-%m-%Y') tglAses, time_format(ap.jam_asesmen,'%H:%i Wita') jam, "
                    + "pg1.nama nmDokter, pg2.nama nmPetugas from asesmen_pasien_terminal ap inner join reg_periksa rp on rp.no_rawat=ap.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=ap.nip_dokter inner join pegawai pg2 on pg2.nik=ap.nip_petugas WHERE "
                    + "ap.tgl_asesmen BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "ap.tgl_asesmen BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "ap.tgl_asesmen BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "ap.tgl_asesmen BETWEEN ? AND ? AND pg1.nama LIKE ? or "
                    + "ap.tgl_asesmen BETWEEN ? AND ? AND pg2.nama LIKE ? ORDER BY ap.tgl_asesmen desc");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglAses"),
                        rs.getString("jam"),
                        rs.getString("nm_pembuat_pernyataan"),
                        rs.getString("nmDokter"),
                        rs.getString("nmPetugas"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("cek_dyspnoe"),
                        rs.getString("cek_nafas_tak"),
                        rs.getString("cek_ada_sekret"),
                        rs.getString("cek_nafas_cepat"),
                        rs.getString("cek_nafas_melalui"),
                        rs.getString("cek_spo2"),
                        rs.getString("cek_nafas_lambat"),
                        rs.getString("cek_mukosa"),
                        rs.getString("cek_tak11"),
                        rs.getString("cek_mual1"),
                        rs.getString("cek_sulit_menelan"),
                        rs.getString("cek_inkon_alvi"),
                        rs.getString("cek_penurunan"),
                        rs.getString("cek_distensi"),
                        rs.getString("cek_tak12"),
                        rs.getString("cek_sulit_bicara"),
                        rs.getString("cek_inkon_urin"),
                        rs.getString("nyeri"),
                        rs.getString("ket_nyeri"),
                        rs.getString("cek_bercak"),
                        rs.getString("cek_gelisah"),
                        rs.getString("cek_lemas"),
                        rs.getString("cek_kulit"),
                        rs.getString("cek_tekanan"),
                        rs.getString("cek_nadi"),
                        rs.getString("cek_tak14"),
                        rs.getString("cek_melakukan_aktivitas"),
                        rs.getString("cek_pindah"),
                        rs.getString("cek_lainya"),
                        rs.getString("ket_lainya"),
                        rs.getString("cek_mual3"),
                        rs.getString("cek_perubahan_persepsi"),
                        rs.getString("cek_nyeri_akut"),
                        rs.getString("cek_pola_nafas"),
                        rs.getString("cek_konstipasi"),
                        rs.getString("cek_nyeri_kronis"),
                        rs.getString("cek_bersihan"),
                        rs.getString("cek_defisit"),
                        rs.getString("spriritual"),
                        rs.getString("ket_spriritual"),
                        rs.getString("didoakan"),
                        rs.getString("bimbingan"),
                        rs.getString("pendampingan"),
                        rs.getString("orang_dihubungi"),
                        rs.getString("siapa_orang_dihubungi"),
                        rs.getString("hub_orang_dihubungi"),
                        rs.getString("dimana_orang_dihubungi"),
                        rs.getString("telp_orang_dihubungi"),
                        rs.getString("perawatan_selanjutnya"),
                        rs.getString("lingkungan_rumah"),
                        rs.getString("mampu_merawat"),
                        rs.getString("ket_mampu_merawat"),
                        rs.getString("fasilitasi_rs"),
                        rs.getString("cek_menyangkal"),
                        rs.getString("cek_marah"),
                        rs.getString("cek_takut"),
                        rs.getString("cek_sedih"),
                        rs.getString("cek_rasa63"),
                        rs.getString("cek_ketidakberdayaan"),
                        rs.getString("cek_anxietas"),
                        rs.getString("cek_distress"),
                        rs.getString("cek_marah64"),
                        rs.getString("cek_gangguan"),
                        rs.getString("cek_penurunan_konsentrasi"),
                        rs.getString("cek_ketidakmampuan"),
                        rs.getString("cek_keluarga_kurang_komunikasi"),
                        rs.getString("cek_koping"),
                        rs.getString("cek_letih"),
                        rs.getString("cek_rasa64"),
                        rs.getString("cek_perubahan_kebiasaan"),
                        rs.getString("cek_keluarga_kurang_partisipasi"),
                        rs.getString("cek_distress_spiritual"),
                        rs.getString("cek_pasien_perlu"),
                        rs.getString("cek_keluarga_dapat"),
                        rs.getString("cek_sahabat"),
                        rs.getString("cek_lainya7"),
                        rs.getString("ket_lainya7"),
                        rs.getString("cek_tidak"),
                        rs.getString("cek_autopsi"),
                        rs.getString("cek_donasi"),
                        rs.getString("ket_donasi"),
                        rs.getString("cek_lainya8"),
                        rs.getString("ket_lainya8"),
                        rs.getString("cek_marah9"),
                        rs.getString("cek_depresi"),
                        rs.getString("cek_rasa9"),
                        rs.getString("cek_perubahan_kebiasaan9"),
                        rs.getString("cek_ketidakmampuan9"),
                        rs.getString("cek_koping9"),
                        rs.getString("cek_letih9"),
                        rs.getString("cek_gangguan9"),
                        rs.getString("cek_sedih9"),
                        rs.getString("cek_penurunan9"),
                        rs.getString("cek_distress9"),
                        rs.getString("nip_dokter"),
                        rs.getString("nm_pembuat_pernyataan"),
                        rs.getString("nip_petugas"),
                        rs.getString("id_file_nm_pembuat_pernyataan"),
                        rs.getString("waktu_simpan")
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
        TtglAsesmen.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        chkDyspnoe.setSelected(false);
        chkNafasTak.setSelected(false);
        chkAdaSekret.setSelected(false);
        chkNafasCepat.setSelected(false);
        chkNafasMelalui.setSelected(false);
        chkSpo2.setSelected(false);
        chkNafasLambat.setSelected(false);
        chkMukosa.setSelected(false);
        chkTak11.setSelected(false);
        chkMual1.setSelected(false);
        chkSulitMenelan.setSelected(false);
        chkInkonAlvi.setSelected(false);
        chkPenurunan.setSelected(false);
        chkDistensi.setSelected(false);
        chkTak12.setSelected(false);
        cmbNyeri.setSelectedIndex(0);
        TketNyeri.setText("");
        TketNyeri.setEnabled(false);
        
        chkSulitBicara.setSelected(false);
        chkInkonUrin.setSelected(false);
        chkBercak.setSelected(false);
        chkGelisah.setSelected(false);
        chkLemas.setSelected(false);
        chkKulit.setSelected(false);
        chkTekanan.setSelected(false);
        chkNadi.setSelected(false);
        chkTak14.setSelected(false);
        chkMelakukanAktivitas.setSelected(false);
        chkPindah.setSelected(false);
        chkLainya.setSelected(false);
        TketLain2.setText("");
        TketLain2.setEnabled(false);
        
        chkMual3.setSelected(false);
        chkPerubahanPersepsi.setSelected(false);
        chkNyeriAkut.setSelected(false);
        chkPolaNafas.setSelected(false);
        chkKonstipasi.setSelected(false);
        chkNyeriKronis.setSelected(false);
        cmbSpiritual.setSelectedIndex(0);
        TketSpiritual.setText("");
        TketSpiritual.setEnabled(false);
        cmbDoa.setSelectedIndex(0);
        cmbBimbingan.setSelectedIndex(0);
        cmbPendamping.setSelectedIndex(0);
        cmbDihubungi.setSelectedIndex(0);
        Tsiapa.setText("");
        Tdimana.setText("");
        Thubungan.setText("");
        TnoTelp.setText("");
        Tsiapa.setEnabled(false);
        Tdimana.setEnabled(false);
        Thubungan.setEnabled(false);
        TnoTelp.setEnabled(false);
        cmbRencana.setSelectedIndex(0);
        cmbDisiapkan.setSelectedIndex(0);
        cmbMampu.setSelectedIndex(0);
        TketMampu.setText("");
        TketMampu.setEnabled(false);
        cmbDifasilitasi.setSelectedIndex(0);
        cmbDisiapkan.setEnabled(false);
        cmbMampu.setEnabled(false);
        cmbDifasilitasi.setEnabled(false);
        
        chkBersihan.setSelected(false);
        chkDefisit.setSelected(false);
        chkMenyangkal.setSelected(false);
        chkMarah.setSelected(false);
        chkTakut.setSelected(false);
        chkSedih.setSelected(false);
        chkRasa63.setSelected(false);
        chkKetidakberdayaan.setSelected(false);
        chkAnxietas.setSelected(false);
        chkDistress.setSelected(false);
        chkMarah64.setSelected(false);
        chkGangguan.setSelected(false);
        chkPenurunanKonsentrasi.setSelected(false);
        chkKetidakmampuan.setSelected(false);
        chkKeluargaKurangKomunikasi.setSelected(false);
        chkKoping.setSelected(false);
        chkLetih.setSelected(false);
        chkRasa64.setSelected(false);
        chkPerubahanKebiasaan.setSelected(false);
        chkKeluargaKurangPartisipasi.setSelected(false);
        chkDistressSpiritual.setSelected(false);
        chkPasienPerlu.setSelected(false);
        chkKeluargaDapat.setSelected(false);
        chkSahabat.setSelected(false);
        chkLainya7.setSelected(false);
        TketLain7.setText("");
        TketLain7.setEnabled(false);
        
        chkTidak.setSelected(false);
        chkAutopsi.setSelected(false);
        chkDonasi.setSelected(false);
        TketDonasi.setText("");
        TketDonasi.setEnabled(false);
        chkLainya8.setSelected(false);
        TketLain8.setText("");
        TketLain8.setEnabled(false);
        
        chkMarah9.setSelected(false);
        chkDepresi.setSelected(false);
        chkRasa9.setSelected(false);
        chkPerubahanKebiasaan9.setSelected(false);
        chkKetidakmampuan9.setSelected(false);
        chkKoping9.setSelected(false);
        chkLetih9.setSelected(false);
        chkGangguan9.setSelected(false);
        chkSedih9.setSelected(false);
        chkPenurunan9.setSelected(false);
        chkDistress9.setSelected(false);
        
        nipDokter = "-";
        TnmDokter.setText("-");
        TnmPembuat.setText("");
        nipPetugas = "-";
        TnmPetugas.setText("-");
        LoadHTML1.setText("");
    }

    private void getData() {
        variabelBersih();
        ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
        
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            Valid.SetTgl(TtglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString().substring(6, 8));

            cekDyspnoe = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString();
            cekNafasTak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString();
            cekAdaSekret = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            cekNafasCepat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString();
            cekNafasMelalui = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString();
            cekSpo2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString();
            cekNafasLambat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString();
            cekMukosa = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            cekTak11 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString();
            cekMual1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString();
            cekSulitMenelan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString();
            cekInkonAlvi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString();
            cekPenurunan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            cekDistensi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            cekTak12 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            cekSulitBicara = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString();
            cekInkonUrin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString();
            cmbNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            TketNyeri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            
            cekBercak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString();
            cekGelisah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString();
            cekLemas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString();
            cekKulit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            cekTekanan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString();
            cekNadi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            cekTak14 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString();
            cekMelakukanAktivitas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString();
            cekPindah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString();
            cekLainya = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString();
            TketLain2.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            
            cekMual3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString();
            cekPerubahanPersepsi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString();
            cekNyeriAkut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString();
            cekPolaNafas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString();
            cekKonstipasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString();
            cekNyeriKronis = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString();
            cekBersihan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString();
            cekDefisit = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString();
            cmbSpiritual.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            TketSpiritual.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());            
            cmbDoa.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            cmbBimbingan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            cmbPendamping.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            cmbDihubungi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            Tsiapa.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString());
            Thubungan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString());
            Tdimana.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString());
            TnoTelp.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString());
            cmbRencana.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString());
            cmbDisiapkan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString());
            cmbMampu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString());
            TketMampu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString());
            cmbDifasilitasi.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString());
            
            cekMenyangkal = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            cekMarah = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            cekTakut = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            cekSedih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString();
            cekRasa63 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString();
            cekKetidakberdayaan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString();
            cekAnxietas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString();
            cekDistress = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString();
            cekMarah64 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString();
            cekGangguan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString();
            cekPenurunanKonsentrasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString();
            cekKetidakmampuan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString();
            cekKeluargaKurangKomunikasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString();
            cekKoping = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 77).toString();
            cekLetih = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 78).toString();
            cekRasa64 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 79).toString();
            cekPerubahanKebiasaan = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 80).toString();
            cekKeluargaKurangPartisipasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 81).toString();
            cekDistressSpiritual = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 82).toString();
            cekPasienPerlu = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 83).toString();
            cekKeluargaDapat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 84).toString();
            cekSahabat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 85).toString();
            cekLainya7 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 86).toString();
            TketLain7.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 87).toString());
            
            cekTidak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 88).toString();
            cekAutopsi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 89).toString();
            cekDonasi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 90).toString();
            TketDonasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 91).toString());            
            cekLainya8 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 92).toString();
            TketLain8.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 93).toString());
            
            cekMarah9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 94).toString();
            cekDepresi = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 95).toString();
            cekRasa9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 96).toString();
            cekPerubahanKebiasaan9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 97).toString();
            cekKetidakmampuan9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 98).toString();
            cekKoping9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 99).toString();
            cekLetih9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 100).toString();
            cekGangguan9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 101).toString();
            cekSedih9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 102).toString();
            cekPenurunan9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 103).toString();
            cekDistress9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 104).toString();
            
            nipDokter = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 105).toString();
            TnmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString()); 
            TnmPembuat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 106).toString()); 
            nipPetugas = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 107).toString();
            TnmPetugas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            idFileTtd = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 108).toString();
            dataCek();
            tampilTTD();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.tgl_registrasi, rp.jam_reg "
                    + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
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
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(new Date());
        TrgRawat.setText(rgrawat);
        isRawat();       
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());

        if (akses.getjml2() >= 1) {
            nipPetugas = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPetugas);
            if (TnmPetugas.getText().equals("")) {
                nipPetugas = "";
            }
        }        
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("asesmen_pasien_terminal", "no_rawat=?", "tgl_asesmen=?, jam_asesmen=?, cek_dyspnoe=?, cek_nafas_tak=?, cek_ada_sekret=?, cek_nafas_cepat=?, "
                + "cek_nafas_melalui=?, cek_spo2=?, cek_nafas_lambat=?, cek_mukosa=?, cek_tak11=?, cek_mual1=?, cek_sulit_menelan=?, cek_inkon_alvi=?, cek_penurunan=?, "
                + "cek_distensi=?, cek_tak12=?, cek_sulit_bicara=?, cek_inkon_urin=?, nyeri=?, ket_nyeri=?, cek_bercak=?, cek_gelisah=?, cek_lemas=?, cek_kulit=?, cek_tekanan=?, "
                + "cek_nadi=?, cek_tak14=?, cek_melakukan_aktivitas=?, cek_pindah=?, cek_lainya=?, ket_lainya=?, cek_mual3=?, cek_perubahan_persepsi=?, cek_nyeri_akut=?, "
                + "cek_pola_nafas=?, cek_konstipasi=?, cek_nyeri_kronis=?, cek_bersihan=?, cek_defisit=?, spriritual=?, ket_spriritual=?, didoakan=?, bimbingan=?, pendampingan=?, "
                + "orang_dihubungi=?, siapa_orang_dihubungi=?, hub_orang_dihubungi=?, dimana_orang_dihubungi=?, telp_orang_dihubungi=?, perawatan_selanjutnya=?, lingkungan_rumah=?, "
                + "mampu_merawat=?, ket_mampu_merawat=?, fasilitasi_rs=?, cek_menyangkal=?, cek_marah=?, cek_takut=?, cek_sedih=?, cek_rasa63=?, cek_ketidakberdayaan=?, "
                + "cek_anxietas=?, cek_distress=?, cek_marah64=?, cek_gangguan=?, cek_penurunan_konsentrasi=?, cek_ketidakmampuan=?, cek_keluarga_kurang_komunikasi=?, cek_koping=?, "
                + "cek_letih=?, cek_rasa64=?, cek_perubahan_kebiasaan=?, cek_keluarga_kurang_partisipasi=?, cek_distress_spiritual=?, cek_pasien_perlu=?, cek_keluarga_dapat=?, "
                + "cek_sahabat=?, cek_lainya7=?, ket_lainya7=?, cek_tidak=?, cek_autopsi=?, cek_donasi=?, ket_donasi=?, cek_lainya8=?, ket_lainya8=?, cek_marah9=?, cek_depresi=?, "
                + "cek_rasa9=?, cek_perubahan_kebiasaan9=?, cek_ketidakmampuan9=?, cek_koping9=?, cek_letih9=?, cek_gangguan9=?, cek_sedih9=?, cek_penurunan9=?, cek_distress9=?, "
                + "nip_dokter=?, nm_pembuat_pernyataan=?, nip_petugas=?", 100, new String[]{
                    Valid.SetTgl(TtglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    cekDyspnoe, cekNafasTak, cekAdaSekret, cekNafasCepat, cekNafasMelalui, cekSpo2, cekNafasLambat, cekMukosa, cekTak11, cekMual1, cekSulitMenelan,
                    cekInkonAlvi, cekPenurunan, cekDistensi, cekTak12, cekSulitBicara, cekInkonUrin, cmbNyeri.getSelectedItem().toString(), TketNyeri.getText(),
                    cekBercak, cekGelisah, cekLemas, cekKulit, cekTekanan, cekNadi, cekTak14, cekMelakukanAktivitas, cekPindah, cekLainya, TketLain2.getText(),
                    cekMual3, cekPerubahanPersepsi, cekNyeriAkut, cekPolaNafas, cekKonstipasi, cekNyeriKronis, cekBersihan, cekDefisit, cmbSpiritual.getSelectedItem().toString(),
                    TketSpiritual.getText(), cmbDoa.getSelectedItem().toString(), cmbBimbingan.getSelectedItem().toString(), cmbPendamping.getSelectedItem().toString(),
                    cmbDihubungi.getSelectedItem().toString(), Tsiapa.getText(), Thubungan.getText(), Tdimana.getText(), TnoTelp.getText(), cmbRencana.getSelectedItem().toString(),
                    cmbDisiapkan.getSelectedItem().toString(), cmbMampu.getSelectedItem().toString(), TketMampu.getText(), cmbDifasilitasi.getSelectedItem().toString(),
                    cekMenyangkal, cekMarah, cekTakut, cekSedih, cekRasa63, cekKetidakberdayaan, cekAnxietas, cekDistress, cekMarah64, cekGangguan, cekPenurunanKonsentrasi,
                    cekKetidakmampuan, cekKeluargaKurangKomunikasi, cekKoping, cekLetih, cekRasa64, cekPerubahanKebiasaan, cekKeluargaKurangPartisipasi, cekDistressSpiritual,
                    cekPasienPerlu, cekKeluargaDapat, cekSahabat, cekLainya7, TketLain7.getText(), cekTidak, cekAutopsi, cekDonasi, TketDonasi.getText(), cekLainya8,
                    TketLain8.getText(), cekMarah9, cekDepresi, cekRasa9, cekPerubahanKebiasaan9, cekKetidakmampuan9, cekKoping9, cekLetih9, cekGangguan9, cekSedih9,
                    cekPenurunan9, cekDistress9, nipDokter, TnmPembuat.getText(), nipPetugas,
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Assesmen Pasien Terminal Dan Keluarganya", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
            TabRawat.setSelectedIndex(1);
        }
    }
    
    private void cekData() {
        if (chkDyspnoe.isSelected() == true) {
            cekDyspnoe = "ya";
        } else {
            cekDyspnoe = "tidak";
        }

        if (chkNafasTak.isSelected() == true) {
            cekNafasTak = "ya";
        } else {
            cekNafasTak = "tidak";
        }

        if (chkAdaSekret.isSelected() == true) {
            cekAdaSekret = "ya";
        } else {
            cekAdaSekret = "tidak";
        }

        if (chkNafasCepat.isSelected() == true) {
            cekNafasCepat = "ya";
        } else {
            cekNafasCepat = "tidak";
        }

        if (chkNafasMelalui.isSelected() == true) {
            cekNafasMelalui = "ya";
        } else {
            cekNafasMelalui = "tidak";
        }

        if (chkSpo2.isSelected() == true) {
            cekSpo2 = "ya";
        } else {
            cekSpo2 = "tidak";
        }

        if (chkNafasLambat.isSelected() == true) {
            cekNafasLambat = "ya";
        } else {
            cekNafasLambat = "tidak";
        }

        if (chkMukosa.isSelected() == true) {
            cekMukosa = "ya";
        } else {
            cekMukosa = "tidak";
        }

        if (chkTak11.isSelected() == true) {
            cekTak11 = "ya";
        } else {
            cekTak11 = "tidak";
        }

        if (chkMual1.isSelected() == true) {
            cekMual1 = "ya";
        } else {
            cekMual1 = "tidak";
        }

        if (chkSulitMenelan.isSelected() == true) {
            cekSulitMenelan = "ya";
        } else {
            cekSulitMenelan = "tidak";
        }

        if (chkInkonAlvi.isSelected() == true) {
            cekInkonAlvi = "ya";
        } else {
            cekInkonAlvi = "tidak";
        }

        if (chkPenurunan.isSelected() == true) {
            cekPenurunan = "ya";
        } else {
            cekPenurunan = "tidak";
        }

        if (chkDistensi.isSelected() == true) {
            cekDistensi = "ya";
        } else {
            cekDistensi = "tidak";
        }

        if (chkTak12.isSelected() == true) {
            cekTak12 = "ya";
        } else {
            cekTak12 = "tidak";
        }

        if (chkSulitBicara.isSelected() == true) {
            cekSulitBicara = "ya";
        } else {
            cekSulitBicara = "tidak";
        }

        if (chkInkonUrin.isSelected() == true) {
            cekInkonUrin = "ya";
        } else {
            cekInkonUrin = "tidak";
        }

        if (chkBercak.isSelected() == true) {
            cekBercak = "ya";
        } else {
            cekBercak = "tidak";
        }

        if (chkGelisah.isSelected() == true) {
            cekGelisah = "ya";
        } else {
            cekGelisah = "tidak";
        }

        if (chkLemas.isSelected() == true) {
            cekLemas = "ya";
        } else {
            cekLemas = "tidak";
        }

        if (chkKulit.isSelected() == true) {
            cekKulit = "ya";
        } else {
            cekKulit = "tidak";
        }

        if (chkTekanan.isSelected() == true) {
            cekTekanan = "ya";
        } else {
            cekTekanan = "tidak";
        }

        if (chkNadi.isSelected() == true) {
            cekNadi = "ya";
        } else {
            cekNadi = "tidak";
        }

        if (chkTak14.isSelected() == true) {
            cekTak14 = "ya";
        } else {
            cekTak14 = "tidak";
        }

        if (chkMelakukanAktivitas.isSelected() == true) {
            cekMelakukanAktivitas = "ya";
        } else {
            cekMelakukanAktivitas = "tidak";
        }

        if (chkPindah.isSelected() == true) {
            cekPindah = "ya";
        } else {
            cekPindah = "tidak";
        }

        if (chkLainya.isSelected() == true) {
            cekLainya = "ya";
        } else {
            cekLainya = "tidak";
        }

        if (chkMual3.isSelected() == true) {
            cekMual3 = "ya";
        } else {
            cekMual3 = "tidak";
        }

        if (chkPerubahanPersepsi.isSelected() == true) {
            cekPerubahanPersepsi = "ya";
        } else {
            cekPerubahanPersepsi = "tidak";
        }

        if (chkNyeriAkut.isSelected() == true) {
            cekNyeriAkut = "ya";
        } else {
            cekNyeriAkut = "tidak";
        }

        if (chkPolaNafas.isSelected() == true) {
            cekPolaNafas = "ya";
        } else {
            cekPolaNafas = "tidak";
        }

        if (chkKonstipasi.isSelected() == true) {
            cekKonstipasi = "ya";
        } else {
            cekKonstipasi = "tidak";
        }

        if (chkNyeriKronis.isSelected() == true) {
            cekNyeriKronis = "ya";
        } else {
            cekNyeriKronis = "tidak";
        }

        if (chkBersihan.isSelected() == true) {
            cekBersihan = "ya";
        } else {
            cekBersihan = "tidak";
        }

        if (chkDefisit.isSelected() == true) {
            cekDefisit = "ya";
        } else {
            cekDefisit = "tidak";
        }

        if (chkMenyangkal.isSelected() == true) {
            cekMenyangkal = "ya";
        } else {
            cekMenyangkal = "tidak";
        }

        if (chkMarah.isSelected() == true) {
            cekMarah = "ya";
        } else {
            cekMarah = "tidak";
        }

        if (chkTakut.isSelected() == true) {
            cekTakut = "ya";
        } else {
            cekTakut = "tidak";
        }

        if (chkSedih.isSelected() == true) {
            cekSedih = "ya";
        } else {
            cekSedih = "tidak";
        }

        if (chkRasa63.isSelected() == true) {
            cekRasa63 = "ya";
        } else {
            cekRasa63 = "tidak";
        }

        if (chkKetidakberdayaan.isSelected() == true) {
            cekKetidakberdayaan = "ya";
        } else {
            cekKetidakberdayaan = "tidak";
        }

        if (chkAnxietas.isSelected() == true) {
            cekAnxietas = "ya";
        } else {
            cekAnxietas = "tidak";
        }

        if (chkDistress.isSelected() == true) {
            cekDistress = "ya";
        } else {
            cekDistress = "tidak";
        }

        if (chkMarah64.isSelected() == true) {
            cekMarah64 = "ya";
        } else {
            cekMarah64 = "tidak";
        }

        if (chkGangguan.isSelected() == true) {
            cekGangguan = "ya";
        } else {
            cekGangguan = "tidak";
        }

        if (chkPenurunanKonsentrasi.isSelected() == true) {
            cekPenurunanKonsentrasi = "ya";
        } else {
            cekPenurunanKonsentrasi = "tidak";
        }

        if (chkKetidakmampuan.isSelected() == true) {
            cekKetidakmampuan = "ya";
        } else {
            cekKetidakmampuan = "tidak";
        }

        if (chkKeluargaKurangKomunikasi.isSelected() == true) {
            cekKeluargaKurangKomunikasi = "ya";
        } else {
            cekKeluargaKurangKomunikasi = "tidak";
        }

        if (chkKoping.isSelected() == true) {
            cekKoping = "ya";
        } else {
            cekKoping = "tidak";
        }

        if (chkLetih.isSelected() == true) {
            cekLetih = "ya";
        } else {
            cekLetih = "tidak";
        }

        if (chkRasa64.isSelected() == true) {
            cekRasa64 = "ya";
        } else {
            cekRasa64 = "tidak";
        }

        if (chkPerubahanKebiasaan.isSelected() == true) {
            cekPerubahanKebiasaan = "ya";
        } else {
            cekPerubahanKebiasaan = "tidak";
        }

        if (chkKeluargaKurangPartisipasi.isSelected() == true) {
            cekKeluargaKurangPartisipasi = "ya";
        } else {
            cekKeluargaKurangPartisipasi = "tidak";
        }

        if (chkDistressSpiritual.isSelected() == true) {
            cekDistressSpiritual = "ya";
        } else {
            cekDistressSpiritual = "tidak";
        }

        if (chkPasienPerlu.isSelected() == true) {
            cekPasienPerlu = "ya";
        } else {
            cekPasienPerlu = "tidak";
        }

        if (chkKeluargaDapat.isSelected() == true) {
            cekKeluargaDapat = "ya";
        } else {
            cekKeluargaDapat = "tidak";
        }

        if (chkSahabat.isSelected() == true) {
            cekSahabat = "ya";
        } else {
            cekSahabat = "tidak";
        }

        if (chkLainya7.isSelected() == true) {
            cekLainya7 = "ya";
        } else {
            cekLainya7 = "tidak";
        }

        if (chkTidak.isSelected() == true) {
            cekTidak = "ya";
        } else {
            cekTidak = "tidak";
        }

        if (chkAutopsi.isSelected() == true) {
            cekAutopsi = "ya";
        } else {
            cekAutopsi = "tidak";
        }

        if (chkDonasi.isSelected() == true) {
            cekDonasi = "ya";
        } else {
            cekDonasi = "tidak";
        }

        if (chkLainya8.isSelected() == true) {
            cekLainya8 = "ya";
        } else {
            cekLainya8 = "tidak";
        }

        if (chkMarah9.isSelected() == true) {
            cekMarah9 = "ya";
        } else {
            cekMarah9 = "tidak";
        }

        if (chkDepresi.isSelected() == true) {
            cekDepresi = "ya";
        } else {
            cekDepresi = "tidak";
        }

        if (chkRasa9.isSelected() == true) {
            cekRasa9 = "ya";
        } else {
            cekRasa9 = "tidak";
        }

        if (chkPerubahanKebiasaan9.isSelected() == true) {
            cekPerubahanKebiasaan9 = "ya";
        } else {
            cekPerubahanKebiasaan9 = "tidak";
        }

        if (chkKetidakmampuan9.isSelected() == true) {
            cekKetidakmampuan9 = "ya";
        } else {
            cekKetidakmampuan9 = "tidak";
        }

        if (chkKoping9.isSelected() == true) {
            cekKoping9 = "ya";
        } else {
            cekKoping9 = "tidak";
        }

        if (chkLetih9.isSelected() == true) {
            cekLetih9 = "ya";
        } else {
            cekLetih9 = "tidak";
        }

        if (chkGangguan9.isSelected() == true) {
            cekGangguan9 = "ya";
        } else {
            cekGangguan9 = "tidak";
        }

        if (chkSedih9.isSelected() == true) {
            cekSedih9 = "ya";
        } else {
            cekSedih9 = "tidak";
        }

        if (chkPenurunan9.isSelected() == true) {
            cekPenurunan9 = "ya";
        } else {
            cekPenurunan9 = "tidak";
        }

        if (chkDistress9.isSelected() == true) {
            cekDistress9 = "ya";
        } else {
            cekDistress9 = "tidak";
        }
    }
    
    private void dataCek() {
        if (cekDyspnoe.equals("ya")) {
            chkDyspnoe.setSelected(true);
        } else {
            chkDyspnoe.setSelected(false);
        }

        if (cekNafasTak.equals("ya")) {
            chkNafasTak.setSelected(true);
        } else {
            chkNafasTak.setSelected(false);
        }

        if (cekAdaSekret.equals("ya")) {
            chkAdaSekret.setSelected(true);
        } else {
            chkAdaSekret.setSelected(false);
        }

        if (cekNafasCepat.equals("ya")) {
            chkNafasCepat.setSelected(true);
        } else {
            chkNafasCepat.setSelected(false);
        }

        if (cekNafasMelalui.equals("ya")) {
            chkNafasMelalui.setSelected(true);
        } else {
            chkNafasMelalui.setSelected(false);
        }

        if (cekSpo2.equals("ya")) {
            chkSpo2.setSelected(true);
        } else {
            chkSpo2.setSelected(false);
        }

        if (cekNafasLambat.equals("ya")) {
            chkNafasLambat.setSelected(true);
        } else {
            chkNafasLambat.setSelected(false);
        }

        if (cekMukosa.equals("ya")) {
            chkMukosa.setSelected(true);
        } else {
            chkMukosa.setSelected(false);
        }

        if (cekTak11.equals("ya")) {
            chkTak11.setSelected(true);
        } else {
            chkTak11.setSelected(false);
        }

        if (cekMual1.equals("ya")) {
            chkMual1.setSelected(true);
        } else {
            chkMual1.setSelected(false);
        }

        if (cekSulitMenelan.equals("ya")) {
            chkSulitMenelan.setSelected(true);
        } else {
            chkSulitMenelan.setSelected(false);
        }

        if (cekInkonAlvi.equals("ya")) {
            chkInkonAlvi.setSelected(true);
        } else {
            chkInkonAlvi.setSelected(false);
        }

        if (cekPenurunan.equals("ya")) {
            chkPenurunan.setSelected(true);
        } else {
            chkPenurunan.setSelected(false);
        }

        if (cekDistensi.equals("ya")) {
            chkDistensi.setSelected(true);
        } else {
            chkDistensi.setSelected(false);
        }

        if (cekTak12.equals("ya")) {
            chkTak12.setSelected(true);
        } else {
            chkTak12.setSelected(false);
        }
        
        if (cmbNyeri.getSelectedIndex() == 2) {
            TketNyeri.setEnabled(true);
        } else {
            TketNyeri.setEnabled(false);
        }

        if (cekSulitBicara.equals("ya")) {
            chkSulitBicara.setSelected(true);
        } else {
            chkSulitBicara.setSelected(false);
        }

        if (cekInkonUrin.equals("ya")) {
            chkInkonUrin.setSelected(true);
        } else {
            chkInkonUrin.setSelected(false);
        }

        if (cekBercak.equals("ya")) {
            chkBercak.setSelected(true);
        } else {
            chkBercak.setSelected(false);
        }

        if (cekGelisah.equals("ya")) {
            chkGelisah.setSelected(true);
        } else {
            chkGelisah.setSelected(false);
        }

        if (cekLemas.equals("ya")) {
            chkLemas.setSelected(true);
        } else {
            chkLemas.setSelected(false);
        }

        if (cekKulit.equals("ya")) {
            chkKulit.setSelected(true);
        } else {
            chkKulit.setSelected(false);
        }

        if (cekTekanan.equals("ya")) {
            chkTekanan.setSelected(true);
        } else {
            chkTekanan.setSelected(false);
        }

        if (cekNadi.equals("ya")) {
            chkNadi.setSelected(true);
        } else {
            chkNadi.setSelected(false);
        }

        if (cekTak14.equals("ya")) {
            chkTak14.setSelected(true);
        } else {
            chkTak14.setSelected(false);
        }

        if (cekMelakukanAktivitas.equals("ya")) {
            chkMelakukanAktivitas.setSelected(true);
        } else {
            chkMelakukanAktivitas.setSelected(false);
        }

        if (cekPindah.equals("ya")) {
            chkPindah.setSelected(true);
        } else {
            chkPindah.setSelected(false);
        }

        if (cekLainya.equals("ya")) {
            chkLainya.setSelected(true);
            TketLain2.setEnabled(true);
        } else {
            chkLainya.setSelected(false);
            TketLain2.setEnabled(false);
        }

        if (cekMual3.equals("ya")) {
            chkMual3.setSelected(true);
        } else {
            chkMual3.setSelected(false);
        }

        if (cekPerubahanPersepsi.equals("ya")) {
            chkPerubahanPersepsi.setSelected(true);
        } else {
            chkPerubahanPersepsi.setSelected(false);
        }

        if (cekNyeriAkut.equals("ya")) {
            chkNyeriAkut.setSelected(true);
        } else {
            chkNyeriAkut.setSelected(false);
        }

        if (cekPolaNafas.equals("ya")) {
            chkPolaNafas.setSelected(true);
        } else {
            chkPolaNafas.setSelected(false);
        }

        if (cekKonstipasi.equals("ya")) {
            chkKonstipasi.setSelected(true);
        } else {
            chkKonstipasi.setSelected(false);
        }

        if (cekNyeriKronis.equals("ya")) {
            chkNyeriKronis.setSelected(true);
        } else {
            chkNyeriKronis.setSelected(false);
        }
        
        if (cmbSpiritual.getSelectedIndex() == 2) {
            TketSpiritual.setEnabled(true);
        } else {
            TketSpiritual.setEnabled(false);
        }
        
        if (cmbDihubungi.getSelectedIndex() == 2) {
            Tsiapa.setEnabled(true);
            Tdimana.setEnabled(true);
            Thubungan.setEnabled(true);
            TnoTelp.setEnabled(true);
        } else {
            Tsiapa.setEnabled(false);
            Tdimana.setEnabled(false);
            Thubungan.setEnabled(false);
            TnoTelp.setEnabled(false);
        }
        
        if (cmbRencana.getSelectedIndex() == 2) {
            cmbDisiapkan.setEnabled(true);
            if (cmbDisiapkan.getSelectedIndex() == 2) {
                cmbMampu.setEnabled(true);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(false);
            } else if (cmbDisiapkan.getSelectedIndex() == 1) {
                cmbMampu.setEnabled(false);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(true);
            } else {
                cmbMampu.setEnabled(false);
                TketMampu.setEnabled(false);
                cmbDifasilitasi.setEnabled(false);
            }
        } else {
            cmbDisiapkan.setEnabled(false);
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        }
        
        if (cmbDisiapkan.getSelectedIndex() == 2) {
            cmbMampu.setEnabled(true);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        } else if (cmbDisiapkan.getSelectedIndex() == 1) {
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(true);
        } else {
            cmbMampu.setEnabled(false);
            TketMampu.setEnabled(false);
            cmbDifasilitasi.setEnabled(false);
        }
        
        if (cmbMampu.getSelectedIndex() == 1) {
            TketMampu.setEnabled(true);
        } else {
            TketMampu.setEnabled(false);
        }
        
        if (cekBersihan.equals("ya")) {
            chkBersihan.setSelected(true);
        } else {
            chkBersihan.setSelected(false);
        }

        if (cekDefisit.equals("ya")) {
            chkDefisit.setSelected(true);
        } else {
            chkDefisit.setSelected(false);
        }

        if (cekMenyangkal.equals("ya")) {
            chkMenyangkal.setSelected(true);
        } else {
            chkMenyangkal.setSelected(false);
        }

        if (cekMarah.equals("ya")) {
            chkMarah.setSelected(true);
        } else {
            chkMarah.setSelected(false);
        }

        if (cekTakut.equals("ya")) {
            chkTakut.setSelected(true);
        } else {
            chkTakut.setSelected(false);
        }

        if (cekSedih.equals("ya")) {
            chkSedih.setSelected(true);
        } else {
            chkSedih.setSelected(false);
        }

        if (cekRasa63.equals("ya")) {
            chkRasa63.setSelected(true);
        } else {
            chkRasa63.setSelected(false);
        }

        if (cekKetidakberdayaan.equals("ya")) {
            chkKetidakberdayaan.setSelected(true);
        } else {
            chkKetidakberdayaan.setSelected(false);
        }

        if (cekAnxietas.equals("ya")) {
            chkAnxietas.setSelected(true);
        } else {
            chkAnxietas.setSelected(false);
        }

        if (cekDistress.equals("ya")) {
            chkDistress.setSelected(true);
        } else {
            chkDistress.setSelected(false);
        }

        if (cekMarah64.equals("ya")) {
            chkMarah64.setSelected(true);
        } else {
            chkMarah64.setSelected(false);
        }

        if (cekGangguan.equals("ya")) {
            chkGangguan.setSelected(true);
        } else {
            chkGangguan.setSelected(false);
        }

        if (cekPenurunanKonsentrasi.equals("ya")) {
            chkPenurunanKonsentrasi.setSelected(true);
        } else {
            chkPenurunanKonsentrasi.setSelected(false);
        }

        if (cekKetidakmampuan.equals("ya")) {
            chkKetidakmampuan.setSelected(true);
        } else {
            chkKetidakmampuan.setSelected(false);
        }

        if (cekKeluargaKurangKomunikasi.equals("ya")) {
            chkKeluargaKurangKomunikasi.setSelected(true);
        } else {
            chkKeluargaKurangKomunikasi.setSelected(false);
        }

        if (cekKoping.equals("ya")) {
            chkKoping.setSelected(true);
        } else {
            chkKoping.setSelected(false);
        }

        if (cekLetih.equals("ya")) {
            chkLetih.setSelected(true);
        } else {
            chkLetih.setSelected(false);
        }

        if (cekRasa64.equals("ya")) {
            chkRasa64.setSelected(true);
        } else {
            chkRasa64.setSelected(false);
        }

        if (cekPerubahanKebiasaan.equals("ya")) {
            chkPerubahanKebiasaan.setSelected(true);
        } else {
            chkPerubahanKebiasaan.setSelected(false);
        }

        if (cekKeluargaKurangPartisipasi.equals("ya")) {
            chkKeluargaKurangPartisipasi.setSelected(true);
        } else {
            chkKeluargaKurangPartisipasi.setSelected(false);
        }

        if (cekDistressSpiritual.equals("ya")) {
            chkDistressSpiritual.setSelected(true);
        } else {
            chkDistressSpiritual.setSelected(false);
        }

        if (cekPasienPerlu.equals("ya")) {
            chkPasienPerlu.setSelected(true);
        } else {
            chkPasienPerlu.setSelected(false);
        }

        if (cekKeluargaDapat.equals("ya")) {
            chkKeluargaDapat.setSelected(true);
        } else {
            chkKeluargaDapat.setSelected(false);
        }

        if (cekSahabat.equals("ya")) {
            chkSahabat.setSelected(true);
        } else {
            chkSahabat.setSelected(false);
        }

        if (cekLainya7.equals("ya")) {
            chkLainya7.setSelected(true);
            TketLain7.setEnabled(true);
        } else {
            chkLainya7.setSelected(false);
            TketLain7.setEnabled(false);
        }

        if (cekTidak.equals("ya")) {
            chkTidak.setSelected(true);
        } else {
            chkTidak.setSelected(false);
        }

        if (cekAutopsi.equals("ya")) {
            chkAutopsi.setSelected(true);
        } else {
            chkAutopsi.setSelected(false);
        }

        if (cekDonasi.equals("ya")) {
            chkDonasi.setSelected(true);
            TketDonasi.setEnabled(true);
        } else {
            chkDonasi.setSelected(false);
            TketDonasi.setEnabled(false);
        }

        if (cekLainya8.equals("ya")) {
            chkLainya8.setSelected(true);
            TketLain8.setEnabled(true);
        } else {
            chkLainya8.setSelected(false);
            TketLain8.setEnabled(false);
        }

        if (cekMarah9.equals("ya")) {
            chkMarah9.setSelected(true);
        } else {
            chkMarah9.setSelected(false);
        }

        if (cekDepresi.equals("ya")) {
            chkDepresi.setSelected(true);
        } else {
            chkDepresi.setSelected(false);
        }

        if (cekRasa9.equals("ya")) {
            chkRasa9.setSelected(true);
        } else {
            chkRasa9.setSelected(false);
        }

        if (cekPerubahanKebiasaan9.equals("ya")) {
            chkPerubahanKebiasaan9.setSelected(true);
        } else {
            chkPerubahanKebiasaan9.setSelected(false);
        }

        if (cekKetidakmampuan9.equals("ya")) {
            chkKetidakmampuan9.setSelected(true);
        } else {
            chkKetidakmampuan9.setSelected(false);
        }

        if (cekKoping9.equals("ya")) {
            chkKoping9.setSelected(true);
        } else {
            chkKoping9.setSelected(false);
        }

        if (cekLetih9.equals("ya")) {
            chkLetih9.setSelected(true);
        } else {
            chkLetih9.setSelected(false);
        }

        if (cekGangguan9.equals("ya")) {
            chkGangguan9.setSelected(true);
        } else {
            chkGangguan9.setSelected(false);
        }

        if (cekSedih9.equals("ya")) {
            chkSedih9.setSelected(true);
        } else {
            chkSedih9.setSelected(false);
        }

        if (cekPenurunan9.equals("ya")) {
            chkPenurunan9.setSelected(true);
        } else {
            chkPenurunan9.setSelected(false);
        }

        if (cekDistress9.equals("ya")) {
            chkDistress9.setSelected(true);
        } else {
            chkDistress9.setSelected(false);
        }
    }
    
    public void isMenu() {
        if (ChkAccor.isSelected() == true) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(900, HEIGHT));
            FormMenu.setVisible(true);
            ChkAccor.setVisible(true);
            Thasil.setText("");
            Tinstruksi.setText("");
            tampilCppt();
        } else if (ChkAccor.isSelected() == false) {
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(22, HEIGHT));
            FormMenu.setVisible(false);
            ChkAccor.setVisible(true);
        }
    }
    
    private void tampilCppt() {
        Valid.tabelKosong(tabModeCppt);
        try {
            pscppt = koneksi.prepareStatement("SELECT c.verifikasi, DATE_FORMAT(c.tgl_cppt,'%d-%m-%Y') tgl, if(c.cek_jam='ya',TIME_FORMAT(c.jam_cppt,'%H:%i'),'-') jam, "
                    + "c.jenis_bagian, pg1.nama nmdpjp, c.jenis_ppa, pg2.nama nmppa, c.cppt_shift, c.hasil_pemeriksaan, "
                    + "c.instruksi_nakes, c.waktu_simpan, c.no_rawat, c.tgl_cppt, c.jam_cppt from cppt c "
                    + "inner join pegawai pg1 on pg1.nik=c.nip_konsulen "
                    + "inner join pegawai pg2 on pg2.nik=c.nip_ppa where "
                    + "c.flag_hapus='tidak' and c.status='ranap' and c.no_rawat='" + TNoRw.getText() + "' order by c.tgl_cppt, c.jam_cppt");
            try {
                rscppt = pscppt.executeQuery();                
                while (rscppt.next()) {
                    tabModeCppt.addRow(new String[]{
                        rscppt.getString("tgl"),
                        rscppt.getString("jam"),
                        rscppt.getString("jenis_bagian"),
                        rscppt.getString("nmdpjp"),
                        rscppt.getString("jenis_ppa"),
                        rscppt.getString("nmppa"),
                        rscppt.getString("cppt_shift"),
                        rscppt.getString("hasil_pemeriksaan"),
                        rscppt.getString("instruksi_nakes"),
                        rscppt.getString("no_rawat"),
                        rscppt.getString("tgl_cppt"),
                        rscppt.getString("jam_cppt")
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
        dataKonfirmasi = "";
        
        if (tbCPPT.getSelectedRow() != -1) {
            Thasil.setText("Tgl. CPPT : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 0).toString() + ", Jam : " + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 1).toString() + " WITA\n\n"
                    + "" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 7).toString());
            
            //konfirmasi terapi
            if (Sequel.cariInteger("select count(-1) from cppt_konfirmasi_terapi where "
                    + "no_rawat='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString() + "' "
                    + "and tgl_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString() + "' "
                    + "and cppt_shift='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString() + "' "
                    + "and jam_cppt='" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString() + "'") > 0) {

                tampilKonfirmasi(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 9).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 10).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 6).toString(),
                        tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 11).toString());
                
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")\n\n"
                            + "KONFIRMASI TERAPI VIA TELP. :\n\n" + dataKonfirmasi);
                }
            } else {
                if (tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString().equals("-")) {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString());
                } else {
                    Tinstruksi.setText(tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 8).toString() + "\n\n"
                            + "(" + tbCPPT.getValueAt(tbCPPT.getSelectedRow(), 5).toString() + ")");
                }
            }
        }
    }
    
    private void tampilKonfirmasi(String norwt, String tglcppt, String sift, String jamcppt) {
        try {
            ps1 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs1.getString("tgllapor") + ", Jam : " + rs1.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs1.getString("tglverif") + ", Jam : " + rs1.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs1.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs1.getString("dpjp");
                    }
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
    
    private void variabelBersih() {
        cekDyspnoe = "";
        cekNafasTak = "";
        cekAdaSekret = "";
        cekNafasCepat = "";
        cekNafasMelalui = "";
        cekSpo2 = "";
        cekNafasLambat = "";
        cekMukosa = "";
        cekTak11 = "";
        cekMual1 = "";
        cekSulitMenelan = "";
        cekInkonAlvi = "";
        cekPenurunan = "";
        cekDistensi = "";
        cekTak12 = "";
        cekSulitBicara = "";
        cekInkonUrin = "";
        cekBercak = "";
        cekGelisah = "";
        cekLemas = "";
        cekKulit = "";
        cekTekanan = "";
        cekNadi = "";
        cekTak14 = "";
        cekMelakukanAktivitas = "";
        cekPindah = "";
        cekLainya = "";
        cekMual3 = "";
        cekPerubahanPersepsi = "";
        cekNyeriAkut = "";
        cekPolaNafas = "";
        cekKonstipasi = "";
        cekNyeriKronis = "";
        cekBersihan = "";
        cekDefisit = "";
        cekMenyangkal = "";
        cekMarah = "";
        cekTakut = "";
        cekSedih = "";
        cekRasa63 = "";
        cekKetidakberdayaan = "";
        cekAnxietas = "";
        cekDistress = "";
        cekMarah64 = "";
        cekGangguan = "";
        cekPenurunanKonsentrasi = "";
        cekKetidakmampuan = "";
        cekKeluargaKurangKomunikasi = "";
        cekKoping = "";
        cekLetih = "";
        cekRasa64 = "";
        cekPerubahanKebiasaan = "";
        cekKeluargaKurangPartisipasi = "";
        cekDistressSpiritual = "";
        cekPasienPerlu = "";
        cekKeluargaDapat = "";
        cekSahabat = "";
        cekLainya7 = "";
        cekTidak = "";
        cekAutopsi = "";
        cekDonasi = "";
        cekLainya8 = "";
        cekMarah9 = "";
        cekDepresi = "";
        cekRasa9 = "";
        cekPerubahanKebiasaan9 = "";
        cekKetidakmampuan9 = "";
        cekKoping9 = "";
        cekLetih9 = "";
        cekGangguan9 = "";
        cekSedih9 = "";
        cekPenurunan9 = "";
        cekDistress9 = "";
        nipDokter = "";
        nipPetugas = "";
        idFileTtd = "";
    }
    
    public void awalData() {
        if (Sequel.cariInteger("select count(-1) from asesmen_pasien_terminal where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
            tampil();
        } else if (Sequel.cariInteger("select count(-1) from asesmen_pasien_terminal where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
            scrollKeAtas();
        }
        
        ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRekmed);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }
    
    private void scrollKeAtas() {
        SwingUtilities.invokeLater(() -> {
            scrollInput.getVerticalScrollBar().setValue(0);
            scrollInput.getHorizontalScrollBar().setValue(0);
        });
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
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            if (Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'").equals(akses.getkode())) {
                usernya = akses.getkode();
                pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                        + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
            } else {
                usernya = Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'");
                pwdnya = Sequel.cariIsi("select CAST(AES_DECRYPT(password,'windi') AS CHAR) from user where CAST(AES_DECRYPT(id_user,'nur') AS CHAR)='" + usernya + "'");
            }            
        }
        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%ASESMEN PASIEN TERMINAL DAN KELUARGANYA%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMAsesmenPasienTerminal.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMAsesmenPasienTerminal.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            emptTeks();
            tampil();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
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
                    if (idFileTtd.equals("")) {
                        gambar = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileTtd;
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
                    + "<td align='center' bgcolor='#f8fdf3'><b>Yang Membuat Pernyataan</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Yang Membuat Pernyataan'><br>(" + TnmPembuat.getText() + ")<br></td>"
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
}