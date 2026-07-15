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
import java.awt.event.KeyListener;
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
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMInformasiTindakanPembiusan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, ps5;
    private ResultSet rs, rs1, rs2, rs3, rs4, rs5;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipDrPelaksana = "", nipPemberi = "",
            diagKerja = "", diagBanding = "", intubasi = "", lma = "", fm = "", tiva = "", spinal = "", epidural = "", blok = "", shok = "",
            henti = "", meninggal = "", sistemPer = "", jantung = "", sistemSar = "", tindakan = "", suhu = "", efek = "", cideraAkibat = "", muntah = "",
            perut = "", tenggor = "", kompliSeg = "", penurunan = "", anestesi = "", reakTok = "", reakAlergiSyok = "", kompliLan = "", nyeriKepala = "",
            nyeriPung = "", tdkBisa = "", infeksi = "", cideraSaraf = "", pendarahan = "", reakAler = "", reakMual = "", reakMun = "", syokAnaf = "",
            idFilePenerima = "", idFilePihakRS = "", idFilePihakKlg = "", idFileMenyatakan = "", usernya = "", pwdnya = "", idParameterTtd = "", URL = "",
            nmPenerima = "", nmPhkRS = "", nmPhkKlg = "", nmBerttdMenyatakan = "", phkRSternyata = "", nipPhkRS = "", ttdBasah = "", ttdQrcode1 = "", ttdQrcode2 = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMInformasiTindakanPembiusan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Dokter Pelaksana", "Pemberi Informasi", "Nama Penerima Informasi", "Tgl. Informasi", "Jam Informasi",
            "Persetujuan/Penolakan", "Tgl. Setuju/Tolak", "Jam Setuju/Tolak",
            "nip_dokter_pelaksana", "nip_pemberi_info", "nm_penerima_info", "cek_diagnosa_kerja", "cek_diagnosa_banding", "asa_diag_kerja", "ket_asa_diag_kerja", "asa_diag_banding",
            "ket_asa_diag_banding", "klinis", "radiologi", "lab", "ekg", "intubasi", "lma", "fm", "tiva", "spinal", "epidural", "blok_perifier", "tata_cara_tindakan",
            "shock", "henti_jantung", "meninggal", "bius_umum_sis_pernapasan", "bius_umum_jantung", "bius_umum_sis_saraf", "bius_umum_tindakan", "bius_umum_suhu", "bius_umum_efek",
            "bius_umum_cidera", "bius_umum_muntah", "bius_umum_perut", "bius_umum_tenggorokan", "bius_reg_kompli_segera", "bius_reg_penurunan", "bius_reg_anes", "bius_reg_reaksi_tok",
            "bius_reg_reaksi_alergi", "bius_reg_kompli_lanjutan", "bius_reg_nyeri_kepala", "bius_reg_nyeri_punggung", "bius_reg_tidak", "bius_reg_infeksi", "bius_reg_cidera",
            "bius_reg_pendarahan", "prognosis", "alternatif_tindakan", "reaksi_alergi", "reaksi_mual", "reaksi_muntah", "syok_anafilaktik", "lain_lain", "tgl_informasi", "jam_informasi",
            "nm_pihak_rs", "nm_pihak_klg", "jns_tindakan_kedokteran", "nm_betttd", "umur_betttd", "jenkel_berttd", "alamat_betttd", "selaku", "tindakan_berupa", "alasan_penolakan",
            "tgl_setuju_tolak", "jam_setuju_tolak", "id_file_penerima_info", "id_file_nm_pihak_rs", "id_file_nm_pihak_klg", "id_file_nm_menyatakan", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbInformasi.setModel(tabMode);
        tbInformasi.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbInformasi.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 85; i++) {
            TableColumn column = tbInformasi.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(220);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(80);
            } else if (i == 10) {
                column.setPreferredWidth(110);
            } else if (i == 11) {
                column.setPreferredWidth(90);
            } else if (i == 12) {
                column.setPreferredWidth(90);
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
            } 
        }
        tbInformasi.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        TnmPenerima.setDocument(new batasInput((int) 150).getKata(TnmPenerima));
        TketDiagKerja.setDocument(new batasInput((int) 255).getKata(TketDiagKerja));        
        TketDiagBanding.setDocument(new batasInput((int) 255).getKata(TketDiagBanding));
        Tklinis.setDocument(new batasInput((int) 100).getKata(Tklinis));
        Tradiologi.setDocument(new batasInput((int) 100).getKata(Tradiologi));
        Tlab.setDocument(new batasInput((int) 100).getKata(Tlab));
        Tekg.setDocument(new batasInput((int) 100).getKata(Tekg));
        TnmSaksiKlg.setDocument(new batasInput((int) 150).getKata(TnmSaksiKlg));
        TnmSaksiRs.setDocument(new batasInput((int) 150).getKata(TnmSaksiRs));
        TnmBerttd.setDocument(new batasInput((int) 150).getKata(TnmBerttd));
        TumurBerttd.setDocument(new batasInput((int) 15).getKata(TumurBerttd));
        TtindakanBerupa.setDocument(new batasInput((int) 200).getKata(TtindakanBerupa));
        Talasan.setDocument(new batasInput((int) 200).getKata(Talasan));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));        
        
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampil();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampil();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampil();}
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
                if (akses.getform().equals("RMInformasiTindakanPembiusan")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDrPelaksana = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDrPelaksana.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDrPelaksana.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMInformasiTindakanPembiusan")) {
                    if (pilihan == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPemberi = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPemberi.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPemberi.requestFocus();
                        }
                    } else if (pilihan == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            TnmSaksiRs.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPihakRS.requestFocus();
                            chkSamaPenerima1.setSelected(false);
                        }
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
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusTtdMenyatakan = new javax.swing.JMenuItem();
        MnHapusTtdPenerima = new javax.swing.JMenuItem();
        MnHapusTtdPihakKlg = new javax.swing.JMenuItem();
        MnHapusTtdPihakRs = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame3 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel115 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi5 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel1 = new javax.swing.JPanel();
        Scroll2 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel95 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel65 = new widget.Label();
        TnmPenerima = new widget.TextBox();
        jLabel87 = new widget.Label();
        jLabel66 = new widget.Label();
        chkDiagKerja = new widget.CekBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel70 = new widget.Label();
        TnmDrPelaksana = new widget.TextBox();
        jLabel75 = new widget.Label();
        TnmPemberi = new widget.TextBox();
        BtnDrPelaksana = new widget.Button();
        BtnPemberi = new widget.Button();
        scrollPane12 = new widget.ScrollPane();
        TtataCara = new widget.TextArea();
        cmbAsaKerja = new widget.ComboBox();
        jLabel92 = new widget.Label();
        TtglInformasi = new widget.Tanggal();
        jLabel93 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel286 = new widget.Label();
        TketDiagKerja = new widget.TextBox();
        chkDiagBanding = new widget.CekBox();
        jLabel86 = new widget.Label();
        cmbAsaBanding = new widget.ComboBox();
        jLabel94 = new widget.Label();
        TketDiagBanding = new widget.TextBox();
        jLabel96 = new widget.Label();
        Tklinis = new widget.TextBox();
        jLabel97 = new widget.Label();
        Tradiologi = new widget.TextBox();
        jLabel98 = new widget.Label();
        Tlab = new widget.TextBox();
        jLabel99 = new widget.Label();
        Tekg = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        chkIntubasi = new widget.CekBox();
        chkLma = new widget.CekBox();
        chkFm = new widget.CekBox();
        chkTiva = new widget.CekBox();
        jLabel102 = new widget.Label();
        chkSpinal = new widget.CekBox();
        chkEpidural = new widget.CekBox();
        chkBlok = new widget.CekBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel73 = new widget.Label();
        chkShock = new widget.CekBox();
        chkHenti = new widget.CekBox();
        chkMeninggal = new widget.CekBox();
        jLabel74 = new widget.Label();
        jLabel104 = new widget.Label();
        chkSistemPer = new widget.CekBox();
        chkJantung = new widget.CekBox();
        chkSistemSar = new widget.CekBox();
        chkTindakanLar = new widget.CekBox();
        chkSuhu = new widget.CekBox();
        chkEfek = new widget.CekBox();
        chkCideraAkibat = new widget.CekBox();
        chkMuntah = new widget.CekBox();
        chkPerut = new widget.CekBox();
        chkTenggor = new widget.CekBox();
        jLabel105 = new widget.Label();
        chkKomSegera = new widget.CekBox();
        chkPenurunan = new widget.CekBox();
        chkAnesSpinal = new widget.CekBox();
        jLabel106 = new widget.Label();
        chkReaksiTok = new widget.CekBox();
        chkReaksiAler = new widget.CekBox();
        chkKomLanjut = new widget.CekBox();
        chkNyeriKepala = new widget.CekBox();
        chkNyeriPunggung = new widget.CekBox();
        chkTdkBisa = new widget.CekBox();
        chkInfeksi = new widget.CekBox();
        chkCideraSaraf = new widget.CekBox();
        chkPendarahan = new widget.CekBox();
        scrollPane14 = new widget.ScrollPane();
        Tprognosis = new widget.TextArea();
        jLabel76 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Talternatif = new widget.TextArea();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        chkReakAler = new widget.CekBox();
        chkReakMual = new widget.CekBox();
        chkReakMuntah = new widget.CekBox();
        chkSyokAnaf = new widget.CekBox();
        scrollPane16 = new widget.ScrollPane();
        Tlainlain = new widget.TextArea();
        jLabel79 = new widget.Label();
        jLabel89 = new widget.Label();
        TnmSaksiRs = new widget.TextBox();
        jLabel107 = new widget.Label();
        TnmSaksiKlg = new widget.TextBox();
        jLabel108 = new widget.Label();
        cmbJnsTindakan = new widget.ComboBox();
        jLabel110 = new widget.Label();
        jLabel90 = new widget.Label();
        TnmBerttd = new widget.TextBox();
        jLabel111 = new widget.Label();
        TumurBerttd = new widget.TextBox();
        jLabel91 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel64 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel80 = new widget.Label();
        cmbSelaku = new widget.ComboBox();
        jLabel112 = new widget.Label();
        TtindakanBerupa = new widget.TextBox();
        jLabel81 = new widget.Label();
        Talasan = new widget.TextBox();
        TtglSetujuTolak = new widget.Tanggal();
        jLabel113 = new widget.Label();
        jLabel114 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel287 = new widget.Label();
        chkSamaDiagBanding = new widget.CekBox();
        chkSamaDiagKerja = new widget.CekBox();
        chkSamaPenerima2 = new widget.CekBox();
        chkSamaPenerima3 = new widget.CekBox();
        chkSamaPenerima1 = new widget.CekBox();
        panelGlass13 = new widget.panelisi();
        scrollPane17 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel82 = new widget.Label();
        BtnTataCara = new widget.Button();
        BtnPrognosis = new widget.Button();
        BtnAlternatif = new widget.Button();
        BtnLain = new widget.Button();
        BtnPihakRS = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        panelGlass14 = new widget.panelisi();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        Scroll = new widget.ScrollPane();
        tbInformasi = new widget.Table();
        panelGlass11 = new widget.panelisi();
        panelGlass12 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHasilPemeriksaanPenunjang.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanPenunjang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanPenunjang.setText("Hasil Pemeriksaan Penunjang");
        MnHasilPemeriksaanPenunjang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanPenunjang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanPenunjang.setIconTextGap(5);
        MnHasilPemeriksaanPenunjang.setName("MnHasilPemeriksaanPenunjang"); // NOI18N
        MnHasilPemeriksaanPenunjang.setPreferredSize(new java.awt.Dimension(195, 26));
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
        MnDokumenJangMed.setPreferredSize(new java.awt.Dimension(195, 26));
        MnDokumenJangMed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDokumenJangMedActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDokumenJangMed);

        jPopupMenu2.setName("jPopupMenu2"); // NOI18N

        MnHapusTtdMenyatakan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdMenyatakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdMenyatakan.setText("Hapus TTD Menyatakan");
        MnHapusTtdMenyatakan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdMenyatakan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdMenyatakan.setIconTextGap(5);
        MnHapusTtdMenyatakan.setName("MnHapusTtdMenyatakan"); // NOI18N
        MnHapusTtdMenyatakan.setPreferredSize(new java.awt.Dimension(220, 30));
        MnHapusTtdMenyatakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdMenyatakanActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusTtdMenyatakan);

        MnHapusTtdPenerima.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdPenerima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdPenerima.setText("Hapus TTD Penerima Informasi");
        MnHapusTtdPenerima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdPenerima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdPenerima.setIconTextGap(5);
        MnHapusTtdPenerima.setName("MnHapusTtdPenerima"); // NOI18N
        MnHapusTtdPenerima.setPreferredSize(new java.awt.Dimension(220, 30));
        MnHapusTtdPenerima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdPenerimaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusTtdPenerima);

        MnHapusTtdPihakKlg.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdPihakKlg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdPihakKlg.setText("Hapus TTD Pihak Keluarga");
        MnHapusTtdPihakKlg.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdPihakKlg.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdPihakKlg.setIconTextGap(5);
        MnHapusTtdPihakKlg.setName("MnHapusTtdPihakKlg"); // NOI18N
        MnHapusTtdPihakKlg.setPreferredSize(new java.awt.Dimension(220, 30));
        MnHapusTtdPihakKlg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdPihakKlgActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusTtdPihakKlg);

        MnHapusTtdPihakRs.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdPihakRs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdPihakRs.setText("Hapus TTD Pihak RS");
        MnHapusTtdPihakRs.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdPihakRs.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdPihakRs.setIconTextGap(5);
        MnHapusTtdPihakRs.setName("MnHapusTtdPihakRs"); // NOI18N
        MnHapusTtdPihakRs.setPreferredSize(new java.awt.Dimension(220, 30));
        MnHapusTtdPihakRs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdPihakRsActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusTtdPihakRs);

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(220, 30));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnBikinQrCode);

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

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Pilih Rekam Medis :");
        jLabel115.setName("jLabel115"); // NOI18N
        panelisi3.add(jLabel115);
        jLabel115.setBounds(0, 10, 120, 23);

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

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCloseIn2);

        internalFrame3.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame3, java.awt.BorderLayout.CENTER);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.getTableHeader().setReorderingAllowed(false);
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbTemplate);

        jPanel1.add(Scroll2);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Informasi Tindakan Pembiusan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
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
        panelGlass8.add(BtnHapus);

        BtnGanti.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti.setMnemonic('G');
        BtnGanti.setText("Ganti");
        BtnGanti.setToolTipText("Alt+G");
        BtnGanti.setName("BtnGanti"); // NOI18N
        BtnGanti.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiActionPerformed(evt);
            }
        });
        BtnGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnGantiKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnGanti);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setText("Cetak Dalam Bentuk :");
        jLabel95.setName("jLabel95"); // NOI18N
        jLabel95.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel95);

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

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass9.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1685));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 140, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(145, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(279, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(352, 10, 407, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Ruang Rawat :");
        jLabel63.setName("jLabel63"); // NOI18N
        FormInput.add(jLabel63);
        jLabel63.setBounds(0, 38, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 38, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Penerima Informasi :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 122, 140, 23);

        TnmPenerima.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenerima.setName("TnmPenerima"); // NOI18N
        TnmPenerima.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmPenerimaKeyPressed(evt);
            }
        });
        FormInput.add(TnmPenerima);
        TnmPenerima.setBounds(145, 122, 410, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("JENIS INFORMASI :");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 150, 140, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Status Fisik ASA :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(270, 178, 100, 23);

        chkDiagKerja.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagKerja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagKerja.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagKerja.setText("Diagnosis Kerja");
        chkDiagKerja.setBorderPainted(true);
        chkDiagKerja.setBorderPaintedFlat(true);
        chkDiagKerja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagKerja.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagKerja.setName("chkDiagKerja"); // NOI18N
        chkDiagKerja.setOpaque(false);
        chkDiagKerja.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDiagKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiagKerjaActionPerformed(evt);
            }
        });
        FormInput.add(chkDiagKerja);
        chkDiagKerja.setBounds(145, 178, 110, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Keterangan Diagnosa Kerja :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(145, 206, 155, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Dasar Diagnosis :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 290, 140, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Dokter Pelaksana :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 66, 140, 23);

        TnmDrPelaksana.setEditable(false);
        TnmDrPelaksana.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrPelaksana.setName("TnmDrPelaksana"); // NOI18N
        FormInput.add(TnmDrPelaksana);
        TnmDrPelaksana.setBounds(145, 66, 410, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Pemberi Informasi :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 94, 140, 23);

        TnmPemberi.setEditable(false);
        TnmPemberi.setForeground(new java.awt.Color(0, 0, 0));
        TnmPemberi.setName("TnmPemberi"); // NOI18N
        FormInput.add(TnmPemberi);
        TnmPemberi.setBounds(145, 94, 410, 23);

        BtnDrPelaksana.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrPelaksana.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrPelaksana.setMnemonic('1');
        BtnDrPelaksana.setToolTipText("Alt+1");
        BtnDrPelaksana.setName("BtnDrPelaksana"); // NOI18N
        BtnDrPelaksana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrPelaksanaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrPelaksana);
        BtnDrPelaksana.setBounds(560, 66, 28, 23);

        BtnPemberi.setForeground(new java.awt.Color(0, 0, 0));
        BtnPemberi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPemberi.setMnemonic('1');
        BtnPemberi.setToolTipText("Alt+1");
        BtnPemberi.setName("BtnPemberi"); // NOI18N
        BtnPemberi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPemberiActionPerformed(evt);
            }
        });
        FormInput.add(BtnPemberi);
        BtnPemberi.setBounds(560, 94, 28, 23);

        scrollPane12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane12.setName("scrollPane12"); // NOI18N

        TtataCara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TtataCara.setColumns(20);
        TtataCara.setRows(5);
        TtataCara.setName("TtataCara"); // NOI18N
        TtataCara.setPreferredSize(new java.awt.Dimension(162, 2000));
        TtataCara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtataCaraKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(TtataCara);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(145, 486, 615, 80);

        cmbAsaKerja.setBackground(new java.awt.Color(245, 253, 240));
        cmbAsaKerja.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsaKerja.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I", "II", "III", "IV", "V", "VI", "IE", "IIE", "IIIE", "IVE", "VE" }));
        cmbAsaKerja.setLightWeightPopupEnabled(false);
        cmbAsaKerja.setName("cmbAsaKerja"); // NOI18N
        FormInput.add(cmbAsaKerja);
        cmbAsaKerja.setBounds(377, 178, 50, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Tgl. Info Tindakan :");
        jLabel92.setName("jLabel92"); // NOI18N
        FormInput.add(jLabel92);
        jLabel92.setBounds(0, 1356, 140, 23);

        TtglInformasi.setEditable(false);
        TtglInformasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        TtglInformasi.setDisplayFormat("dd-MM-yyyy");
        TtglInformasi.setName("TtglInformasi"); // NOI18N
        TtglInformasi.setOpaque(false);
        TtglInformasi.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglInformasi);
        TtglInformasi.setBounds(145, 1356, 90, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Pukul :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(240, 1356, 60, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(304, 1356, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(357, 1356, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(409, 1356, 45, 23);

        jLabel286.setForeground(new java.awt.Color(0, 0, 0));
        jLabel286.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel286.setText("Wita");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(461, 1356, 50, 23);

        TketDiagKerja.setForeground(new java.awt.Color(0, 0, 0));
        TketDiagKerja.setName("TketDiagKerja"); // NOI18N
        TketDiagKerja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketDiagKerjaKeyPressed(evt);
            }
        });
        FormInput.add(TketDiagKerja);
        TketDiagKerja.setBounds(305, 206, 455, 23);

        chkDiagBanding.setBackground(new java.awt.Color(255, 255, 250));
        chkDiagBanding.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        chkDiagBanding.setText("Diagnosis Banding");
        chkDiagBanding.setBorderPainted(true);
        chkDiagBanding.setBorderPaintedFlat(true);
        chkDiagBanding.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDiagBanding.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDiagBanding.setName("chkDiagBanding"); // NOI18N
        chkDiagBanding.setOpaque(false);
        chkDiagBanding.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDiagBanding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDiagBandingActionPerformed(evt);
            }
        });
        FormInput.add(chkDiagBanding);
        chkDiagBanding.setBounds(145, 234, 120, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Status Fisik ASA :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(270, 234, 100, 23);

        cmbAsaBanding.setBackground(new java.awt.Color(245, 253, 240));
        cmbAsaBanding.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsaBanding.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "I", "II", "III", "IV", "V", "VI", "IE", "IIE", "IIIE", "IVE", "VE" }));
        cmbAsaBanding.setLightWeightPopupEnabled(false);
        cmbAsaBanding.setName("cmbAsaBanding"); // NOI18N
        FormInput.add(cmbAsaBanding);
        cmbAsaBanding.setBounds(377, 234, 50, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Keterangan Diagnosa Banding :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(110, 262, 190, 23);

        TketDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        TketDiagBanding.setName("TketDiagBanding"); // NOI18N
        TketDiagBanding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketDiagBandingKeyPressed(evt);
            }
        });
        FormInput.add(TketDiagBanding);
        TketDiagBanding.setBounds(305, 262, 455, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Klinis :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(145, 290, 60, 23);

        Tklinis.setForeground(new java.awt.Color(0, 0, 0));
        Tklinis.setName("Tklinis"); // NOI18N
        Tklinis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TklinisKeyPressed(evt);
            }
        });
        FormInput.add(Tklinis);
        Tklinis.setBounds(210, 290, 550, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Radiologi :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(125, 318, 80, 23);

        Tradiologi.setForeground(new java.awt.Color(0, 0, 0));
        Tradiologi.setName("Tradiologi"); // NOI18N
        Tradiologi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TradiologiKeyPressed(evt);
            }
        });
        FormInput.add(Tradiologi);
        Tradiologi.setBounds(210, 318, 550, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Laboratorium :");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(125, 346, 80, 23);

        Tlab.setForeground(new java.awt.Color(0, 0, 0));
        Tlab.setName("Tlab"); // NOI18N
        Tlab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlabKeyPressed(evt);
            }
        });
        FormInput.add(Tlab);
        Tlab.setBounds(210, 346, 550, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("EKG :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(125, 374, 80, 23);

        Tekg.setForeground(new java.awt.Color(0, 0, 0));
        Tekg.setName("Tekg"); // NOI18N
        Tekg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TekgKeyPressed(evt);
            }
        });
        FormInput.add(Tekg);
        Tekg.setBounds(210, 374, 550, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Tindakan Kedokteran :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 402, 140, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Anestesi / Pembiusan :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(145, 402, 120, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("1. Umum :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(145, 430, 80, 23);

        chkIntubasi.setBackground(new java.awt.Color(255, 255, 250));
        chkIntubasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIntubasi.setForeground(new java.awt.Color(0, 0, 0));
        chkIntubasi.setText("Intubasi");
        chkIntubasi.setBorderPainted(true);
        chkIntubasi.setBorderPaintedFlat(true);
        chkIntubasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIntubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIntubasi.setName("chkIntubasi"); // NOI18N
        chkIntubasi.setOpaque(false);
        chkIntubasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIntubasi);
        chkIntubasi.setBounds(232, 430, 70, 23);

        chkLma.setBackground(new java.awt.Color(255, 255, 250));
        chkLma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLma.setForeground(new java.awt.Color(0, 0, 0));
        chkLma.setText("LMA");
        chkLma.setBorderPainted(true);
        chkLma.setBorderPaintedFlat(true);
        chkLma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLma.setName("chkLma"); // NOI18N
        chkLma.setOpaque(false);
        chkLma.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkLma);
        chkLma.setBounds(310, 430, 50, 23);

        chkFm.setBackground(new java.awt.Color(255, 255, 250));
        chkFm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFm.setForeground(new java.awt.Color(0, 0, 0));
        chkFm.setText("FM");
        chkFm.setBorderPainted(true);
        chkFm.setBorderPaintedFlat(true);
        chkFm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFm.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFm.setName("chkFm"); // NOI18N
        chkFm.setOpaque(false);
        chkFm.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFm);
        chkFm.setBounds(370, 430, 50, 23);

        chkTiva.setBackground(new java.awt.Color(255, 255, 250));
        chkTiva.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTiva.setForeground(new java.awt.Color(0, 0, 0));
        chkTiva.setText("TIVA");
        chkTiva.setBorderPainted(true);
        chkTiva.setBorderPaintedFlat(true);
        chkTiva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTiva.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTiva.setName("chkTiva"); // NOI18N
        chkTiva.setOpaque(false);
        chkTiva.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTiva);
        chkTiva.setBounds(430, 430, 60, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("2. Regional :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(145, 458, 80, 23);

        chkSpinal.setBackground(new java.awt.Color(255, 255, 250));
        chkSpinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSpinal.setForeground(new java.awt.Color(0, 0, 0));
        chkSpinal.setText("Spinal");
        chkSpinal.setBorderPainted(true);
        chkSpinal.setBorderPaintedFlat(true);
        chkSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpinal.setName("chkSpinal"); // NOI18N
        chkSpinal.setOpaque(false);
        chkSpinal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSpinal);
        chkSpinal.setBounds(232, 458, 70, 23);

        chkEpidural.setBackground(new java.awt.Color(255, 255, 250));
        chkEpidural.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEpidural.setForeground(new java.awt.Color(0, 0, 0));
        chkEpidural.setText("Epidural");
        chkEpidural.setBorderPainted(true);
        chkEpidural.setBorderPaintedFlat(true);
        chkEpidural.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEpidural.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEpidural.setName("chkEpidural"); // NOI18N
        chkEpidural.setOpaque(false);
        chkEpidural.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEpidural);
        chkEpidural.setBounds(310, 458, 70, 23);

        chkBlok.setBackground(new java.awt.Color(255, 255, 250));
        chkBlok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBlok.setForeground(new java.awt.Color(0, 0, 0));
        chkBlok.setText("Blok Perifier");
        chkBlok.setBorderPainted(true);
        chkBlok.setBorderPaintedFlat(true);
        chkBlok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBlok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBlok.setName("chkBlok"); // NOI18N
        chkBlok.setOpaque(false);
        chkBlok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBlok);
        chkBlok.setBounds(390, 458, 90, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Tata Cara Tindakan :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 486, 140, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Indikasi dan Tujuan :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 572, 140, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel103.setText("Memfasilitasi Operasi, menghilangkan rasa sakit saat operasi");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(145, 572, 340, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Resiko :");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 600, 140, 23);

        chkShock.setBackground(new java.awt.Color(255, 255, 250));
        chkShock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkShock.setForeground(new java.awt.Color(0, 0, 0));
        chkShock.setText("Shock");
        chkShock.setBorderPainted(true);
        chkShock.setBorderPaintedFlat(true);
        chkShock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkShock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkShock.setName("chkShock"); // NOI18N
        chkShock.setOpaque(false);
        chkShock.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkShock);
        chkShock.setBounds(145, 600, 60, 23);

        chkHenti.setBackground(new java.awt.Color(255, 255, 250));
        chkHenti.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkHenti.setForeground(new java.awt.Color(0, 0, 0));
        chkHenti.setText("Henti Jantung");
        chkHenti.setBorderPainted(true);
        chkHenti.setBorderPaintedFlat(true);
        chkHenti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkHenti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkHenti.setName("chkHenti"); // NOI18N
        chkHenti.setOpaque(false);
        chkHenti.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkHenti);
        chkHenti.setBounds(215, 600, 100, 23);

        chkMeninggal.setBackground(new java.awt.Color(255, 255, 250));
        chkMeninggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        chkMeninggal.setText("Meninggal Dunia Di Meja Operasi");
        chkMeninggal.setBorderPainted(true);
        chkMeninggal.setBorderPaintedFlat(true);
        chkMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMeninggal.setName("chkMeninggal"); // NOI18N
        chkMeninggal.setOpaque(false);
        chkMeninggal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMeninggal);
        chkMeninggal.setBounds(330, 600, 190, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Komplikasi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 628, 140, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("1. Bius Umum");
        jLabel104.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(145, 628, 90, 23);

        chkSistemPer.setBackground(new java.awt.Color(255, 255, 250));
        chkSistemPer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSistemPer.setForeground(new java.awt.Color(0, 0, 0));
        chkSistemPer.setText("<html>Sistem pernapasan : kejang dan penyempitan jalan nafas, kekurangan kadar O2 dalam darah, kekurangan atau kelebihan Co2 dalam darah, aspirasi pneumonia/masuknya isi lambung ke dalam saluran nafas/paru</html>");
        chkSistemPer.setBorderPainted(true);
        chkSistemPer.setBorderPaintedFlat(true);
        chkSistemPer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSistemPer.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSistemPer.setName("chkSistemPer"); // NOI18N
        chkSistemPer.setOpaque(false);
        chkSistemPer.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSistemPer.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        chkSistemPer.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(chkSistemPer);
        chkSistemPer.setBounds(145, 656, 615, 35);

        chkJantung.setBackground(new java.awt.Color(255, 255, 250));
        chkJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJantung.setForeground(new java.awt.Color(0, 0, 0));
        chkJantung.setText("Jantung dan pembuluh darah : tekanan darah turun, tekanan darah naik, gangguan irama jantung sampai henti jantung");
        chkJantung.setBorderPainted(true);
        chkJantung.setBorderPaintedFlat(true);
        chkJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJantung.setName("chkJantung"); // NOI18N
        chkJantung.setOpaque(false);
        chkJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkJantung);
        chkJantung.setBounds(145, 697, 615, 23);

        chkSistemSar.setBackground(new java.awt.Color(255, 255, 250));
        chkSistemSar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSistemSar.setForeground(new java.awt.Color(0, 0, 0));
        chkSistemSar.setText("Sistem saraf : kejang, bangun lambat, trauma saraf tepi");
        chkSistemSar.setBorderPainted(true);
        chkSistemSar.setBorderPaintedFlat(true);
        chkSistemSar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSistemSar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSistemSar.setName("chkSistemSar"); // NOI18N
        chkSistemSar.setOpaque(false);
        chkSistemSar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSistemSar);
        chkSistemSar.setBounds(145, 725, 310, 23);

        chkTindakanLar.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakanLar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakanLar.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakanLar.setText("Tindakan laringoskopi intubasi (gigi patah, luka mulut, pendarahan)");
        chkTindakanLar.setBorderPainted(true);
        chkTindakanLar.setBorderPaintedFlat(true);
        chkTindakanLar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakanLar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakanLar.setName("chkTindakanLar"); // NOI18N
        chkTindakanLar.setOpaque(false);
        chkTindakanLar.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTindakanLar);
        chkTindakanLar.setBounds(145, 753, 360, 23);

        chkSuhu.setBackground(new java.awt.Color(255, 255, 250));
        chkSuhu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSuhu.setForeground(new java.awt.Color(0, 0, 0));
        chkSuhu.setText("Suhu tubuh naik/turun");
        chkSuhu.setBorderPainted(true);
        chkSuhu.setBorderPaintedFlat(true);
        chkSuhu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuhu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuhu.setName("chkSuhu"); // NOI18N
        chkSuhu.setOpaque(false);
        chkSuhu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSuhu);
        chkSuhu.setBounds(145, 781, 150, 23);

        chkEfek.setBackground(new java.awt.Color(255, 255, 250));
        chkEfek.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEfek.setForeground(new java.awt.Color(0, 0, 0));
        chkEfek.setText("Efek merugikan obat dan alergi (syok anafiatik sampai meninggal dunia)");
        chkEfek.setBorderPainted(true);
        chkEfek.setBorderPaintedFlat(true);
        chkEfek.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEfek.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEfek.setName("chkEfek"); // NOI18N
        chkEfek.setOpaque(false);
        chkEfek.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEfek);
        chkEfek.setBounds(145, 809, 380, 23);

        chkCideraAkibat.setBackground(new java.awt.Color(255, 255, 250));
        chkCideraAkibat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCideraAkibat.setForeground(new java.awt.Color(0, 0, 0));
        chkCideraAkibat.setText("Cidera akibat posisi saat operasi");
        chkCideraAkibat.setBorderPainted(true);
        chkCideraAkibat.setBorderPaintedFlat(true);
        chkCideraAkibat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCideraAkibat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCideraAkibat.setName("chkCideraAkibat"); // NOI18N
        chkCideraAkibat.setOpaque(false);
        chkCideraAkibat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCideraAkibat);
        chkCideraAkibat.setBounds(145, 837, 200, 23);

        chkMuntah.setBackground(new java.awt.Color(255, 255, 250));
        chkMuntah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMuntah.setForeground(new java.awt.Color(0, 0, 0));
        chkMuntah.setText("Muntah");
        chkMuntah.setBorderPainted(true);
        chkMuntah.setBorderPaintedFlat(true);
        chkMuntah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMuntah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMuntah.setName("chkMuntah"); // NOI18N
        chkMuntah.setOpaque(false);
        chkMuntah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMuntah);
        chkMuntah.setBounds(145, 865, 65, 23);

        chkPerut.setBackground(new java.awt.Color(255, 255, 250));
        chkPerut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerut.setForeground(new java.awt.Color(0, 0, 0));
        chkPerut.setText("Perut Kembung");
        chkPerut.setBorderPainted(true);
        chkPerut.setBorderPaintedFlat(true);
        chkPerut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPerut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPerut.setName("chkPerut"); // NOI18N
        chkPerut.setOpaque(false);
        chkPerut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerut);
        chkPerut.setBounds(220, 865, 110, 23);

        chkTenggor.setBackground(new java.awt.Color(255, 255, 250));
        chkTenggor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTenggor.setForeground(new java.awt.Color(0, 0, 0));
        chkTenggor.setText("Tenggorokan Serak");
        chkTenggor.setBorderPainted(true);
        chkTenggor.setBorderPaintedFlat(true);
        chkTenggor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTenggor.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTenggor.setName("chkTenggor"); // NOI18N
        chkTenggor.setOpaque(false);
        chkTenggor.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTenggor);
        chkTenggor.setBounds(340, 865, 130, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("<html><b>2. Bius Regional : <i>Spinal / Epidural</i></b></html>");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(145, 893, 210, 23);

        chkKomSegera.setBackground(new java.awt.Color(255, 255, 250));
        chkKomSegera.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKomSegera.setForeground(new java.awt.Color(0, 0, 0));
        chkKomSegera.setText("Komplikasi segera :");
        chkKomSegera.setBorderPainted(true);
        chkKomSegera.setBorderPaintedFlat(true);
        chkKomSegera.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKomSegera.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKomSegera.setName("chkKomSegera"); // NOI18N
        chkKomSegera.setOpaque(false);
        chkKomSegera.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKomSegera);
        chkKomSegera.setBounds(145, 921, 115, 23);

        chkPenurunan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenurunan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenurunan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenurunan.setText("Penurunan tekanan darah");
        chkPenurunan.setBorderPainted(true);
        chkPenurunan.setBorderPaintedFlat(true);
        chkPenurunan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenurunan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenurunan.setName("chkPenurunan"); // NOI18N
        chkPenurunan.setOpaque(false);
        chkPenurunan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPenurunan);
        chkPenurunan.setBounds(270, 921, 155, 23);

        chkAnesSpinal.setBackground(new java.awt.Color(255, 255, 250));
        chkAnesSpinal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAnesSpinal.setForeground(new java.awt.Color(0, 0, 0));
        chkAnesSpinal.setText("Anestesi spinal");
        chkAnesSpinal.setBorderPainted(true);
        chkAnesSpinal.setBorderPaintedFlat(true);
        chkAnesSpinal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnesSpinal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnesSpinal.setName("chkAnesSpinal"); // NOI18N
        chkAnesSpinal.setOpaque(false);
        chkAnesSpinal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAnesSpinal);
        chkAnesSpinal.setBounds(436, 921, 110, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel106.setText("total (penurunan kesadaran, penurunan denyut jantung, nafas berhenti)");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(165, 949, 370, 23);

        chkReaksiTok.setBackground(new java.awt.Color(255, 255, 250));
        chkReaksiTok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReaksiTok.setForeground(new java.awt.Color(0, 0, 0));
        chkReaksiTok.setText("Reaksi toksik (kejang, henti jantung)");
        chkReaksiTok.setBorderPainted(true);
        chkReaksiTok.setBorderPaintedFlat(true);
        chkReaksiTok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReaksiTok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReaksiTok.setName("chkReaksiTok"); // NOI18N
        chkReaksiTok.setOpaque(false);
        chkReaksiTok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReaksiTok);
        chkReaksiTok.setBounds(145, 977, 205, 23);

        chkReaksiAler.setBackground(new java.awt.Color(255, 255, 250));
        chkReaksiAler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReaksiAler.setForeground(new java.awt.Color(0, 0, 0));
        chkReaksiAler.setText("Reaksi alergi (syok anafilatik sampai meninggal)");
        chkReaksiAler.setBorderPainted(true);
        chkReaksiAler.setBorderPaintedFlat(true);
        chkReaksiAler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReaksiAler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReaksiAler.setName("chkReaksiAler"); // NOI18N
        chkReaksiAler.setOpaque(false);
        chkReaksiAler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReaksiAler);
        chkReaksiAler.setBounds(360, 977, 260, 23);

        chkKomLanjut.setBackground(new java.awt.Color(255, 255, 250));
        chkKomLanjut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKomLanjut.setForeground(new java.awt.Color(0, 0, 0));
        chkKomLanjut.setText("Komplikasi lanjutan");
        chkKomLanjut.setBorderPainted(true);
        chkKomLanjut.setBorderPaintedFlat(true);
        chkKomLanjut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKomLanjut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKomLanjut.setName("chkKomLanjut"); // NOI18N
        chkKomLanjut.setOpaque(false);
        chkKomLanjut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKomLanjut);
        chkKomLanjut.setBounds(145, 1005, 125, 23);

        chkNyeriKepala.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriKepala.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriKepala.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeriKepala.setText("Nyeri kepala cekot-cekot");
        chkNyeriKepala.setBorderPainted(true);
        chkNyeriKepala.setBorderPaintedFlat(true);
        chkNyeriKepala.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriKepala.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriKepala.setName("chkNyeriKepala"); // NOI18N
        chkNyeriKepala.setOpaque(false);
        chkNyeriKepala.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriKepala);
        chkNyeriKepala.setBounds(278, 1005, 150, 23);

        chkNyeriPunggung.setBackground(new java.awt.Color(255, 255, 250));
        chkNyeriPunggung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkNyeriPunggung.setForeground(new java.awt.Color(0, 0, 0));
        chkNyeriPunggung.setText("Nyeri punggung");
        chkNyeriPunggung.setBorderPainted(true);
        chkNyeriPunggung.setBorderPaintedFlat(true);
        chkNyeriPunggung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNyeriPunggung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNyeriPunggung.setName("chkNyeriPunggung"); // NOI18N
        chkNyeriPunggung.setOpaque(false);
        chkNyeriPunggung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkNyeriPunggung);
        chkNyeriPunggung.setBounds(436, 1005, 110, 23);

        chkTdkBisa.setBackground(new java.awt.Color(255, 255, 250));
        chkTdkBisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTdkBisa.setForeground(new java.awt.Color(0, 0, 0));
        chkTdkBisa.setText("Tidak bisa berkemih");
        chkTdkBisa.setBorderPainted(true);
        chkTdkBisa.setBorderPaintedFlat(true);
        chkTdkBisa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTdkBisa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTdkBisa.setName("chkTdkBisa"); // NOI18N
        chkTdkBisa.setOpaque(false);
        chkTdkBisa.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTdkBisa);
        chkTdkBisa.setBounds(145, 1033, 125, 23);

        chkInfeksi.setBackground(new java.awt.Color(255, 255, 250));
        chkInfeksi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInfeksi.setForeground(new java.awt.Color(0, 0, 0));
        chkInfeksi.setText("Infeksi");
        chkInfeksi.setBorderPainted(true);
        chkInfeksi.setBorderPaintedFlat(true);
        chkInfeksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfeksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfeksi.setName("chkInfeksi"); // NOI18N
        chkInfeksi.setOpaque(false);
        chkInfeksi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkInfeksi);
        chkInfeksi.setBounds(278, 1033, 60, 23);

        chkCideraSaraf.setBackground(new java.awt.Color(255, 255, 250));
        chkCideraSaraf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCideraSaraf.setForeground(new java.awt.Color(0, 0, 0));
        chkCideraSaraf.setText("Cidera saraf");
        chkCideraSaraf.setBorderPainted(true);
        chkCideraSaraf.setBorderPaintedFlat(true);
        chkCideraSaraf.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCideraSaraf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCideraSaraf.setName("chkCideraSaraf"); // NOI18N
        chkCideraSaraf.setOpaque(false);
        chkCideraSaraf.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCideraSaraf);
        chkCideraSaraf.setBounds(350, 1033, 90, 23);

        chkPendarahan.setBackground(new java.awt.Color(255, 255, 250));
        chkPendarahan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPendarahan.setForeground(new java.awt.Color(0, 0, 0));
        chkPendarahan.setText("Pendarahan");
        chkPendarahan.setBorderPainted(true);
        chkPendarahan.setBorderPaintedFlat(true);
        chkPendarahan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPendarahan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPendarahan.setName("chkPendarahan"); // NOI18N
        chkPendarahan.setOpaque(false);
        chkPendarahan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPendarahan);
        chkPendarahan.setBounds(450, 1033, 90, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

        Tprognosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tprognosis.setColumns(20);
        Tprognosis.setRows(5);
        Tprognosis.setName("Tprognosis"); // NOI18N
        Tprognosis.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tprognosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprognosisKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tprognosis);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(145, 1061, 615, 80);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("Prognosis :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 1061, 140, 23);

        scrollPane15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane15.setName("scrollPane15"); // NOI18N

        Talternatif.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Talternatif.setColumns(20);
        Talternatif.setRows(5);
        Talternatif.setName("Talternatif"); // NOI18N
        Talternatif.setPreferredSize(new java.awt.Dimension(162, 2000));
        Talternatif.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalternatifKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Talternatif);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(145, 1147, 615, 80);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Alternatif Tindakan :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1147, 140, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("<html><div style='text-align:right'>Pemberian Analgetik Pasca Tindakan :</div></html>");
        jLabel78.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel78.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel78.setName("jLabel78"); // NOI18N
        jLabel78.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel78);
        jLabel78.setBounds(20, 1234, 120, 30);

        chkReakAler.setBackground(new java.awt.Color(255, 255, 250));
        chkReakAler.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReakAler.setForeground(new java.awt.Color(0, 0, 0));
        chkReakAler.setText("Reaksi Alergi");
        chkReakAler.setBorderPainted(true);
        chkReakAler.setBorderPaintedFlat(true);
        chkReakAler.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReakAler.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReakAler.setName("chkReakAler"); // NOI18N
        chkReakAler.setOpaque(false);
        chkReakAler.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReakAler);
        chkReakAler.setBounds(145, 1234, 90, 23);

        chkReakMual.setBackground(new java.awt.Color(255, 255, 250));
        chkReakMual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReakMual.setForeground(new java.awt.Color(0, 0, 0));
        chkReakMual.setText("Reaksi Mual");
        chkReakMual.setBorderPainted(true);
        chkReakMual.setBorderPaintedFlat(true);
        chkReakMual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReakMual.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReakMual.setName("chkReakMual"); // NOI18N
        chkReakMual.setOpaque(false);
        chkReakMual.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReakMual);
        chkReakMual.setBounds(244, 1234, 85, 23);

        chkReakMuntah.setBackground(new java.awt.Color(255, 255, 250));
        chkReakMuntah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkReakMuntah.setForeground(new java.awt.Color(0, 0, 0));
        chkReakMuntah.setText("Reaksi Muntah");
        chkReakMuntah.setBorderPainted(true);
        chkReakMuntah.setBorderPaintedFlat(true);
        chkReakMuntah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkReakMuntah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkReakMuntah.setName("chkReakMuntah"); // NOI18N
        chkReakMuntah.setOpaque(false);
        chkReakMuntah.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkReakMuntah);
        chkReakMuntah.setBounds(340, 1234, 100, 23);

        chkSyokAnaf.setBackground(new java.awt.Color(255, 255, 250));
        chkSyokAnaf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSyokAnaf.setForeground(new java.awt.Color(0, 0, 0));
        chkSyokAnaf.setText("Syok Anafilaktik");
        chkSyokAnaf.setBorderPainted(true);
        chkSyokAnaf.setBorderPaintedFlat(true);
        chkSyokAnaf.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSyokAnaf.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSyokAnaf.setName("chkSyokAnaf"); // NOI18N
        chkSyokAnaf.setOpaque(false);
        chkSyokAnaf.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSyokAnaf);
        chkSyokAnaf.setBounds(450, 1234, 120, 23);

        scrollPane16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane16.setName("scrollPane16"); // NOI18N

        Tlainlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tlainlain.setColumns(20);
        Tlainlain.setRows(5);
        Tlainlain.setName("Tlainlain"); // NOI18N
        Tlainlain.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tlainlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainlainKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tlainlain);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(145, 1270, 615, 80);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Lain - lain :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1270, 140, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Nama Saksi Pihak RS :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1384, 140, 23);

        TnmSaksiRs.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksiRs.setName("TnmSaksiRs"); // NOI18N
        TnmSaksiRs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSaksiRsKeyPressed(evt);
            }
        });
        FormInput.add(TnmSaksiRs);
        TnmSaksiRs.setBounds(145, 1384, 410, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("<html><div style='text-align:right'>Nama Saksi Pihak Keluarga :</div></html>");
        jLabel107.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel107.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel107.setName("jLabel107"); // NOI18N
        jLabel107.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel107);
        jLabel107.setBounds(20, 1412, 120, 30);

        TnmSaksiKlg.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksiKlg.setName("TnmSaksiKlg"); // NOI18N
        TnmSaksiKlg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSaksiKlgKeyPressed(evt);
            }
        });
        FormInput.add(TnmSaksiKlg);
        TnmSaksiKlg.setBounds(145, 1412, 410, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("TINDAKAN KEDOKTERAN :");
        jLabel108.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(0, 1448, 170, 23);

        cmbJnsTindakan.setBackground(new java.awt.Color(245, 253, 240));
        cmbJnsTindakan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJnsTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "PERSETUJUAN", "PENOLAKAN" }));
        cmbJnsTindakan.setLightWeightPopupEnabled(false);
        cmbJnsTindakan.setName("cmbJnsTindakan"); // NOI18N
        cmbJnsTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJnsTindakanActionPerformed(evt);
            }
        });
        FormInput.add(cmbJnsTindakan);
        cmbJnsTindakan.setBounds(145, 1476, 105, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("<html><div style='text-align:right'>Jenis Tindakan Kedokteran :</div></html>");
        jLabel110.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel110.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel110.setName("jLabel110"); // NOI18N
        jLabel110.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel110);
        jLabel110.setBounds(20, 1476, 120, 30);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("<html><div style='text-align:right'>Nama Bertanda Tangan/Menyatakan :</div></html>");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(250, 1476, 140, 30);

        TnmBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TnmBerttd.setName("TnmBerttd"); // NOI18N
        TnmBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TnmBerttd);
        TnmBerttd.setBounds(395, 1476, 365, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("<html><div style='text-align:right'>Umur Yang Bertanda Tangan :</div></html>");
        jLabel111.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel111.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel111.setName("jLabel111"); // NOI18N
        jLabel111.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel111);
        jLabel111.setBounds(20, 1512, 120, 30);

        TumurBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TumurBerttd.setName("TumurBerttd"); // NOI18N
        TumurBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TumurBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TumurBerttd);
        TumurBerttd.setBounds(145, 1512, 90, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Jenis Kelamin :");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(235, 1512, 90, 23);

        cmbJenkel.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Laki-laki", "Perempuan" }));
        cmbJenkel.setLightWeightPopupEnabled(false);
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(330, 1512, 90, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Alamat :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 1548, 140, 23);

        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(145, 1548, 615, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Selaku :");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1576, 140, 23);

        cmbSelaku.setBackground(new java.awt.Color(245, 253, 240));
        cmbSelaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbSelaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Pasien", "Ayah", "Ibu", "Anak", "Suami", "Istri" }));
        cmbSelaku.setLightWeightPopupEnabled(false);
        cmbSelaku.setName("cmbSelaku"); // NOI18N
        FormInput.add(cmbSelaku);
        cmbSelaku.setBounds(145, 1576, 65, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("<html><div style='text-align:right'>Tindakan Kedokteran Berupa :</div></html>");
        jLabel112.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel112.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel112.setName("jLabel112"); // NOI18N
        jLabel112.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel112);
        jLabel112.setBounds(210, 1576, 120, 30);

        TtindakanBerupa.setForeground(new java.awt.Color(0, 0, 0));
        TtindakanBerupa.setName("TtindakanBerupa"); // NOI18N
        TtindakanBerupa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanBerupaKeyPressed(evt);
            }
        });
        FormInput.add(TtindakanBerupa);
        TtindakanBerupa.setBounds(335, 1576, 425, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Alasan Penolakan :");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 1612, 140, 23);

        Talasan.setForeground(new java.awt.Color(0, 0, 0));
        Talasan.setName("Talasan"); // NOI18N
        Talasan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalasanKeyPressed(evt);
            }
        });
        FormInput.add(Talasan);
        Talasan.setBounds(145, 1612, 615, 23);

        TtglSetujuTolak.setEditable(false);
        TtglSetujuTolak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        TtglSetujuTolak.setDisplayFormat("dd-MM-yyyy");
        TtglSetujuTolak.setName("TtglSetujuTolak"); // NOI18N
        TtglSetujuTolak.setOpaque(false);
        TtglSetujuTolak.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglSetujuTolak);
        TtglSetujuTolak.setBounds(145, 1640, 90, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("<html><div style='text-align:right'>Tgl. Persetujuan / Penolakan :</div></html>");
        jLabel113.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel113.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel113.setName("jLabel113"); // NOI18N
        jLabel113.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel113);
        jLabel113.setBounds(20, 1640, 120, 30);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("<html><div style='text-align:right'>Pukul Persetujuan / Penolakan :</div></html>");
        jLabel114.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel114.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel114.setName("jLabel114"); // NOI18N
        jLabel114.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel114);
        jLabel114.setBounds(240, 1640, 120, 30);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(365, 1640, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(418, 1640, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(470, 1640, 45, 23);

        jLabel287.setForeground(new java.awt.Color(0, 0, 0));
        jLabel287.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel287.setText("Wita");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(522, 1640, 50, 23);

        chkSamaDiagBanding.setBackground(new java.awt.Color(255, 255, 250));
        chkSamaDiagBanding.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSamaDiagBanding.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaDiagBanding.setText("Sama Dengan Diagnosa Kerja");
        chkSamaDiagBanding.setBorderPainted(true);
        chkSamaDiagBanding.setBorderPaintedFlat(true);
        chkSamaDiagBanding.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaDiagBanding.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaDiagBanding.setName("chkSamaDiagBanding"); // NOI18N
        chkSamaDiagBanding.setOpaque(false);
        chkSamaDiagBanding.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSamaDiagBanding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaDiagBandingActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaDiagBanding);
        chkSamaDiagBanding.setBounds(437, 234, 200, 23);

        chkSamaDiagKerja.setBackground(new java.awt.Color(255, 255, 250));
        chkSamaDiagKerja.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSamaDiagKerja.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaDiagKerja.setText("Sama Dengan Diagnosa Banding");
        chkSamaDiagKerja.setBorderPainted(true);
        chkSamaDiagKerja.setBorderPaintedFlat(true);
        chkSamaDiagKerja.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaDiagKerja.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaDiagKerja.setName("chkSamaDiagKerja"); // NOI18N
        chkSamaDiagKerja.setOpaque(false);
        chkSamaDiagKerja.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSamaDiagKerja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaDiagKerjaActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaDiagKerja);
        chkSamaDiagKerja.setBounds(437, 178, 200, 23);

        chkSamaPenerima2.setBackground(new java.awt.Color(255, 255, 250));
        chkSamaPenerima2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSamaPenerima2.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPenerima2.setText("Sama Dengan Penerima Informasi");
        chkSamaPenerima2.setBorderPainted(true);
        chkSamaPenerima2.setBorderPaintedFlat(true);
        chkSamaPenerima2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPenerima2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPenerima2.setName("chkSamaPenerima2"); // NOI18N
        chkSamaPenerima2.setOpaque(false);
        chkSamaPenerima2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSamaPenerima2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPenerima2ActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPenerima2);
        chkSamaPenerima2.setBounds(560, 1412, 200, 23);

        chkSamaPenerima3.setBackground(new java.awt.Color(255, 255, 250));
        chkSamaPenerima3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSamaPenerima3.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPenerima3.setText("Sama Dengan Penerima Informasi");
        chkSamaPenerima3.setBorderPainted(true);
        chkSamaPenerima3.setBorderPaintedFlat(true);
        chkSamaPenerima3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPenerima3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPenerima3.setName("chkSamaPenerima3"); // NOI18N
        chkSamaPenerima3.setOpaque(false);
        chkSamaPenerima3.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSamaPenerima3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPenerima3ActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPenerima3);
        chkSamaPenerima3.setBounds(560, 1504, 200, 23);

        chkSamaPenerima1.setBackground(new java.awt.Color(255, 255, 250));
        chkSamaPenerima1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSamaPenerima1.setForeground(new java.awt.Color(0, 0, 0));
        chkSamaPenerima1.setText("Sama Dengan Pemberi Informasi");
        chkSamaPenerima1.setBorderPainted(true);
        chkSamaPenerima1.setBorderPaintedFlat(true);
        chkSamaPenerima1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSamaPenerima1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSamaPenerima1.setName("chkSamaPenerima1"); // NOI18N
        chkSamaPenerima1.setOpaque(false);
        chkSamaPenerima1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSamaPenerima1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaPenerima1ActionPerformed(evt);
            }
        });
        FormInput.add(chkSamaPenerima1);
        chkSamaPenerima1.setBounds(600, 1384, 200, 23);

        panelGlass13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane17.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane17.setName("scrollPane17"); // NOI18N
        scrollPane17.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane17.setViewportView(gambarQR);

        panelGlass13.add(scrollPane17);

        FormInput.add(panelGlass13);
        panelGlass13.setBounds(630, 725, 230, 245);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel82.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel82.setName("jLabel82"); // NOI18N
        jLabel82.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel82);
        jLabel82.setBounds(645, 975, 210, 60);

        BtnTataCara.setForeground(new java.awt.Color(0, 0, 0));
        BtnTataCara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTataCara.setMnemonic('2');
        BtnTataCara.setText("Template");
        BtnTataCara.setToolTipText("Alt+2");
        BtnTataCara.setName("BtnTataCara"); // NOI18N
        BtnTataCara.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTataCara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTataCaraActionPerformed(evt);
            }
        });
        FormInput.add(BtnTataCara);
        BtnTataCara.setBounds(770, 486, 100, 23);

        BtnPrognosis.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrognosis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPrognosis.setMnemonic('2');
        BtnPrognosis.setText("Template");
        BtnPrognosis.setToolTipText("Alt+2");
        BtnPrognosis.setName("BtnPrognosis"); // NOI18N
        BtnPrognosis.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPrognosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrognosisActionPerformed(evt);
            }
        });
        FormInput.add(BtnPrognosis);
        BtnPrognosis.setBounds(770, 1061, 100, 23);

        BtnAlternatif.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlternatif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlternatif.setMnemonic('2');
        BtnAlternatif.setText("Template");
        BtnAlternatif.setToolTipText("Alt+2");
        BtnAlternatif.setName("BtnAlternatif"); // NOI18N
        BtnAlternatif.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlternatif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlternatifActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlternatif);
        BtnAlternatif.setBounds(770, 1147, 100, 23);

        BtnLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnLain.setMnemonic('2');
        BtnLain.setText("Template");
        BtnLain.setToolTipText("Alt+2");
        BtnLain.setName("BtnLain"); // NOI18N
        BtnLain.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLainActionPerformed(evt);
            }
        });
        FormInput.add(BtnLain);
        BtnLain.setBounds(770, 1270, 100, 23);

        BtnPihakRS.setForeground(new java.awt.Color(0, 0, 0));
        BtnPihakRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPihakRS.setMnemonic('1');
        BtnPihakRS.setToolTipText("Alt+1");
        BtnPihakRS.setName("BtnPihakRS"); // NOI18N
        BtnPihakRS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPihakRSActionPerformed(evt);
            }
        });
        FormInput.add(BtnPihakRS);
        BtnPihakRS.setBounds(560, 1384, 28, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(900, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass14.setLayout(new java.awt.BorderLayout());

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tanda Tangan Saksi, Yang Menyatakan, & Penerima Informasi Pasien ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass14.add(Scroll5, java.awt.BorderLayout.CENTER);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Informasi Tindakan Pembiusan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 510));

        tbInformasi.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbInformasi.setComponentPopupMenu(jPopupMenu2);
        tbInformasi.setName("tbInformasi"); // NOI18N
        tbInformasi.getTableHeader().setReorderingAllowed(false);
        tbInformasi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbInformasiMouseClicked(evt);
            }
        });
        tbInformasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInformasiKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbInformasi);

        panelGlass14.add(Scroll, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass14, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Informasi Tindakan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass12.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-06-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(DTPCari2);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass12.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass12.add(LCount);

        panelGlass11.add(panelGlass12, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 42));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setText("Tampilkan Data");
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
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 23));
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

        panelGlass11.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        PanelInput1.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        panelGlass9.add(PanelInput1, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (TnmPenerima.getText().equals("")) {
                nmPenerima = "";
            } else {
                nmPenerima = TnmPenerima.getText() + " (Penerima Informasi)";
            }

            if (TnmSaksiRs.getText().equals("")) {
                nmPhkRS = "";
            } else {
                nmPhkRS = TnmSaksiRs.getText() + " (Pihak RS)";
            }

            if (TnmSaksiKlg.getText().equals("")) {
                nmPhkKlg = "";
            } else {
                nmPhkKlg = TnmSaksiKlg.getText() + " (Pihak Keluarga)";
            }
            
            if (TnmBerttd.getText().equals("")) {
                nmBerttdMenyatakan = "";
            } else {
                nmBerttdMenyatakan = TnmBerttd.getText() + " (Yang Menyatakan)";
            }
            
            cekData();
            if (Sequel.menyimpantf("informasi_tindakan_pembiusan_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 74, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), nipDrPelaksana, nipPemberi, nmPenerima, diagKerja, diagBanding, cmbAsaKerja.getSelectedItem().toString(),
                        TketDiagKerja.getText(), cmbAsaBanding.getSelectedItem().toString(), TketDiagBanding.getText(), Tklinis.getText(), Tradiologi.getText(),
                        Tlab.getText(), Tekg.getText(), intubasi, lma, fm, tiva, spinal, epidural, blok, TtataCara.getText(), shok, henti, meninggal, sistemPer,
                        jantung, sistemSar, tindakan, suhu, efek, cideraAkibat, muntah, perut, tenggor, kompliSeg, penurunan, anestesi, reakTok, reakAlergiSyok,
                        kompliLan, nyeriKepala, nyeriPung, tdkBisa, infeksi, cideraSaraf, pendarahan, Tprognosis.getText(), Talternatif.getText(), reakAler, reakMual,
                        reakMun, syokAnaf, Tlainlain.getText(), Valid.SetTgl(TtglInformasi.getSelectedItem() + ""),
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), nmPhkRS, nmPhkKlg,
                        cmbJnsTindakan.getSelectedItem().toString(), nmBerttdMenyatakan, TumurBerttd.getText(), cmbJenkel.getSelectedItem().toString(), Talamat.getText(),
                        cmbSelaku.getSelectedItem().toString(), TtindakanBerupa.getText(), Talasan.getText(), Valid.SetTgl(TtglSetujuTolak.getSelectedItem() + ""),
                        cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), "", "", "", "", Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Informasi Tindakan Pembiusan", "Simpan");
                TCari.setText(TNoRw.getText());
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
        ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            if (tbInformasi.getSelectedRow() > -1) {
                if (TnmPenerima.getText().equals("")) {
                    nmPenerima = "";
                } else {
                    nmPenerima = TnmPenerima.getText() + " (Penerima Informasi)";
                }

                if (TnmSaksiRs.getText().equals("")) {
                    nmPhkRS = "";
                } else {
                    nmPhkRS = TnmSaksiRs.getText() + " (Pihak RS)";
                }

                if (TnmSaksiKlg.getText().equals("")) {
                    nmPhkKlg = "";
                } else {
                    nmPhkKlg = TnmSaksiKlg.getText() + " (Pihak Keluarga)";
                }

                if (TnmBerttd.getText().equals("")) {
                    nmBerttdMenyatakan = "";
                } else {
                    nmBerttdMenyatakan = TnmBerttd.getText() + " (Yang Menyatakan)";
                }

                cekData();
                if (Sequel.mengedittf("informasi_tindakan_pembiusan_operasi", "waktu_simpan=?", "nip_dokter_pelaksana=?, nip_pemberi_info=?, nm_penerima_info=?, cek_diagnosa_kerja=?, "
                        + "cek_diagnosa_banding=?, asa_diag_kerja=?, ket_asa_diag_kerja=?, asa_diag_banding=?, ket_asa_diag_banding=?, klinis=?, radiologi=?, lab=?, ekg=?, intubasi=?, "
                        + "lma=?, fm=?, tiva=?, spinal=?, epidural=?, blok_perifier=?, tata_cara_tindakan=?, shock=?, henti_jantung=?, meninggal=?, bius_umum_sis_pernapasan=?, "
                        + "bius_umum_jantung=?, bius_umum_sis_saraf=?, bius_umum_tindakan=?, bius_umum_suhu=?, bius_umum_efek=?, bius_umum_cidera=?, bius_umum_muntah=?, bius_umum_perut=?, "
                        + "bius_umum_tenggorokan=?, bius_reg_kompli_segera=?, bius_reg_penurunan=?, bius_reg_anes=?, bius_reg_reaksi_tok=?, bius_reg_reaksi_alergi=?, "
                        + "bius_reg_kompli_lanjutan=?, bius_reg_nyeri_kepala=?, bius_reg_nyeri_punggung=?, bius_reg_tidak=?, bius_reg_infeksi=?, bius_reg_cidera=?, bius_reg_pendarahan=?, "
                        + "prognosis=?, alternatif_tindakan=?, reaksi_alergi=?, reaksi_mual=?, reaksi_muntah=?, syok_anafilaktik=?, lain_lain=?, tgl_informasi=?, jam_informasi=?, "
                        + "nm_pihak_rs=?, nm_pihak_klg=?, jns_tindakan_kedokteran=?, nm_betttd=?, umur_betttd=?, jenkel_berttd=?, alamat_betttd=?, selaku=?, tindakan_berupa=?, "
                        + "alasan_penolakan=?, tgl_setuju_tolak=?, jam_setuju_tolak=?", 68, new String[]{
                            nipDrPelaksana, nipPemberi, nmPenerima, diagKerja, diagBanding, cmbAsaKerja.getSelectedItem().toString(),
                            TketDiagKerja.getText(), cmbAsaBanding.getSelectedItem().toString(), TketDiagBanding.getText(), Tklinis.getText(), Tradiologi.getText(),
                            Tlab.getText(), Tekg.getText(), intubasi, lma, fm, tiva, spinal, epidural, blok, TtataCara.getText(), shok, henti, meninggal, sistemPer,
                            jantung, sistemSar, tindakan, suhu, efek, cideraAkibat, muntah, perut, tenggor, kompliSeg, penurunan, anestesi, reakTok, reakAlergiSyok,
                            kompliLan, nyeriKepala, nyeriPung, tdkBisa, infeksi, cideraSaraf, pendarahan, Tprognosis.getText(), Talternatif.getText(), reakAler, reakMual,
                            reakMun, syokAnaf, Tlainlain.getText(), Valid.SetTgl(TtglInformasi.getSelectedItem() + ""),
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), nmPhkRS, nmPhkKlg, cmbJnsTindakan.getSelectedItem().toString(),
                            nmBerttdMenyatakan, TumurBerttd.getText(), cmbJenkel.getSelectedItem().toString(), Talamat.getText(), cmbSelaku.getSelectedItem().toString(),
                            TtindakanBerupa.getText(), Talasan.getText(), Valid.SetTgl(TtglSetujuTolak.getSelectedItem() + ""),
                            cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), 
                            tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Informasi Tindakan Pembiusan", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbInformasi.requestFocus();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowNomorDokumenRM.dispose();
        WindowTemplate.dispose();
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
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
        ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
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
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
            TCari.setText("");
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbInformasiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbInformasiMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbInformasiMouseClicked

    private void tbInformasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInformasiKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbInformasiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from informasi_tindakan_pembiusan_operasi where waktu_simpan=?", 1, new String[]{
                    tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString()
                }) == true) {
                    if (!idFilePenerima.equals("")) {
                        Sequel.hapusSemuaTtd(idFilePenerima);
                    }

                    if (!idFilePihakRS.equals("")) {
                        Sequel.hapusSemuaTtd(idFilePihakRS);
                    }

                    if (!idFilePihakKlg.equals("")) {
                        Sequel.hapusSemuaTtd(idFilePihakKlg);
                    }
                    
                    if (!idFileMenyatakan.equals("")) {
                        Sequel.hapusSemuaTtd(idFileMenyatakan);
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
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbInformasi.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("dokter", TnmDrPelaksana.getText());
            param.put("pemberi", TnmPemberi.getText());
            param.put("nmPenerima", TnmPenerima.getText());
            
            if (Sequel.cariInteger("select count(-1) from informasi_tindakan_pembiusan_operasi it inner join pegawai pg on pg.nama=replace(it.nm_pihak_rs,' (Pihak RS)','') "
                    + "where it.waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'") > 0) {
                nipPhkRS = Sequel.cariIsi("select nik from pegawai where nama='" + TnmSaksiRs.getText() + "'");
                phkRSternyata = "karyawan";
            } else {
                phkRSternyata = "lainya";
            }
            
            if (chkDiagKerja.isSelected() == true) {
                if (TketDiagKerja.getText().equals("")) {
                    param.put("diagnosKerja", cmbAsaKerja.getSelectedItem().toString());
                } else {
                    param.put("diagnosKerja", cmbAsaKerja.getSelectedItem().toString() + ", " + TketDiagKerja.getText());
                }                
            } else {
                param.put("diagnosKerja", "");
            }
            
            if (chkDiagBanding.isSelected() == true) {
                if (TketDiagBanding.getText().equals("")) {
                    param.put("diagnosBanding", cmbAsaBanding.getSelectedItem().toString());
                } else {
                    param.put("diagnosBanding", cmbAsaBanding.getSelectedItem().toString() + ", " + TketDiagBanding.getText());
                }                
            } else {
                param.put("diagnosBanding", "");
            }            
            
            param.put("klinis", Tklinis.getText());
            param.put("radio", Tradiologi.getText());
            param.put("lab", Tlab.getText());            
            param.put("ekg", Tekg.getText());
            
            if (chkIntubasi.isSelected() == true) {
                param.put("intubasi", "V");
            } else {
                param.put("intubasi", "");
            }
            
            if (chkLma.isSelected() == true) {
                param.put("lma", "V");
            } else {
                param.put("lma", "");
            }
            
            if (chkFm.isSelected() == true) {
                param.put("fm", "V");
            } else {
                param.put("fm", "");
            }
            
            if (chkTiva.isSelected() == true) {
                param.put("tiva", "V");
            } else {
                param.put("tiva", "");
            }
            
            if (chkSpinal.isSelected() == true) {
                param.put("spinal", "V");
            } else {
                param.put("spinal", "");
            }
            
            if (chkEpidural.isSelected() == true) {
                param.put("epidural", "V");
            } else {
                param.put("epidural", "");
            }
            
            if (chkBlok.isSelected() == true) {
                param.put("blok", "V");
            } else {
                param.put("blok", "");
            }
            
            param.put("tatacara", TtataCara.getText());
            
            if (chkShock.isSelected() == true) {
                param.put("shok", "V");
            } else {
                param.put("shok", "");
            }
            
            if (chkHenti.isSelected() == true) {
                param.put("henti", "V");
            } else {
                param.put("henti", "");
            }
            
            if (chkMeninggal.isSelected() == true) {
                param.put("mening", "V");
            } else {
                param.put("mening", "");
            }
            
            if (chkSistemPer.isSelected() == true) {
                param.put("sisPer", "V");
            } else {
                param.put("sisPer", "");
            }
            
            if (chkJantung.isSelected() == true) {
                param.put("jantung", "V");
            } else {
                param.put("jantung", "");
            }
            
            if (chkSistemSar.isSelected() == true) {
                param.put("sisSar", "V");
            } else {
                param.put("sisSar", "");
            }
            
            if (chkTindakanLar.isSelected() == true) {
                param.put("tindak", "V");
            } else {
                param.put("tindak", "");
            }
            
            if (chkSuhu.isSelected() == true) {
                param.put("suhu", "V");
            } else {
                param.put("suhu", "");
            }
            
            if (chkEfek.isSelected() == true) {
                param.put("efek", "V");
            } else {
                param.put("efek", "");
            }
            
            if (chkCideraAkibat.isSelected() == true) {
                param.put("cidAk", "V");
            } else {
                param.put("cidAk", "");
            }
            
            if (chkMuntah.isSelected() == true) {
                param.put("muntah", "V");
            } else {
                param.put("muntah", "");
            }
            
            if (chkPerut.isSelected() == true) {
                param.put("perut", "V");
            } else {
                param.put("perut", "");
            }
            
            if (chkTenggor.isSelected() == true) {
                param.put("tenggor", "V");
            } else {
                param.put("tenggor", "");
            }
            
            if (chkKomSegera.isSelected() == true) {
                param.put("komSeg", "V");
            } else {
                param.put("komSeg", "");
            }
            
            if (chkPenurunan.isSelected() == true) {
                param.put("penur", "V");
            } else {
                param.put("penur", "");
            }
            
            if (chkAnesSpinal.isSelected() == true) {
                param.put("anes", "V");
            } else {
                param.put("anes", "");
            }
            
            if (chkReaksiTok.isSelected() == true) {
                param.put("reakTok", "V");
            } else {
                param.put("reakTok", "");
            }
            
            if (chkReaksiAler.isSelected() == true) {
                param.put("reakAlerSyok", "V");
            } else {
                param.put("reakAlerSyok", "");
            }
            
            if (chkKomLanjut.isSelected() == true) {
                param.put("komLan", "V");
            } else {
                param.put("komLan", "");
            }
            
            if (chkNyeriKepala.isSelected() == true) {
                param.put("nyerKep", "V");
            } else {
                param.put("nyerKep", "");
            }
            
            if (chkNyeriPunggung.isSelected() == true) {
                param.put("nyerPung", "V");
            } else {
                param.put("nyerPung", "");
            }
            
            if (chkTdkBisa.isSelected() == true) {
                param.put("tdkBisa", "V");
            } else {
                param.put("tdkBisa", "");
            }
            
            if (chkInfeksi.isSelected() == true) {
                param.put("infeksi", "V");
            } else {
                param.put("infeksi", "");
            }
            
            if (chkCideraSaraf.isSelected() == true) {
                param.put("cidSar", "V");
            } else {
                param.put("cidSar", "");
            }
            
            if (chkPendarahan.isSelected() == true) {
                param.put("pendar", "V");
            } else {
                param.put("pendar", "");
            }
            
            param.put("prognosis", Tprognosis.getText());
            param.put("alter", Talternatif.getText());
            
            if (chkReakAler.isSelected() == true) {
                param.put("reakAler", "V");
            } else {
                param.put("reakAler", "");
            }
            
            if (chkReakMual.isSelected() == true) {
                param.put("reakMual", "V");
            } else {
                param.put("reakMual", "");
            }
            
            if (chkReakMuntah.isSelected() == true) {
                param.put("reakMuntah", "V");
            } else {
                param.put("reakMuntah", "");
            }
            
            if (chkSyokAnaf.isSelected() == true) {
                param.put("syok", "V");
            } else {
                param.put("syok", "");
            }
            
            param.put("lain", Tlainlain.getText());            
            param.put("tglInformasi", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglInformasi.getSelectedItem() + ""))
                    + "          Pukul " + cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " WITA");
            
            if (TnmSaksiRs.getText().equals("")) {
                param.put("pihakRS", ".................");
            } else {
                param.put("pihakRS", TnmSaksiRs.getText());
            }
            
            if (TnmSaksiKlg.getText().equals("")) {
                param.put("pihakKLG", ".................");
            } else {
                param.put("pihakKLG", TnmSaksiKlg.getText());
            }
            
            if (TumurBerttd.getText().equals("")) {
                param.put("umur", "..........");
            } else {
                param.put("umur", TumurBerttd.getText());
            }
            
            param.put("jenkel", cmbJenkel.getSelectedItem().toString());
            
            if (Talamat.getText().equals("")) {
                param.put("alamat", ".................");
            } else {
                param.put("alamat", Talamat.getText());
            }
            
            param.put("selaku", cmbSelaku.getSelectedItem().toString());
            param.put("jnsTindakan", cmbJnsTindakan.getSelectedItem().toString());
            
            if (TtindakanBerupa.getText().equals("")) {
                param.put("berupa", ".................");
            } else {
                param.put("berupa", TtindakanBerupa.getText());
            }
            
            if (Talasan.getText().equals("")) {
                param.put("alasan", ".................");
            } else {
                param.put("alasan", Talasan.getText());
            }
            
            param.put("umurJKpas", Sequel.cariIsi("select concat(rp.umurdaftar,' ',rp.sttsumur,'. / ',if(p.jk='L','Laki-laki','Perempuan')) from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where rp.no_rawat='" + TNoRw.getText() + "'"));
            param.put("alamatPas", Sequel.cariIsi("select concat(p.alamat,', Kel. ',kel.nm_kel,', Kec. ',kec.nm_kec,', Kab. ',kab.nm_kab) from reg_periksa rp "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join kelurahan kel on kel.kd_kel=p.kd_kel "
                    + "inner join kecamatan kec on kec.kd_kec=p.kd_kec inner join kabupaten kab on kab.kd_kab=p.kd_kab where rp.no_rawat ='" + TNoRw.getText() + "'"));
            
            param.put("tglSetujuTolak", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglSetujuTolak.getSelectedItem() + ""))
                    + ", Pukul " + cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString() + " Wita");
            
            try {
                StringBuilder htmlContent = new StringBuilder();
                String gambar1 = "", gambar2 = "", gambar3 = "", gambar4 = "", ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileMenyatakan.equals("")) {
                            gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                        } else {
                            gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileMenyatakan;
                        }

                        if (idFilePenerima.equals("")) {
                            gambar2 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                        } else {
                            gambar2 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePenerima;
                        }

                        if (idFilePihakKlg.equals("")) {
                            gambar3 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                        } else {
                            gambar3 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePihakKlg;
                        }

                        if (idFilePihakRS.equals("")) {
                            gambar4 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                        } else {
                            gambar4 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePihakRS;
                        }
                        //ping gagal
                    } else {
                        gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        gambar3 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        gambar4 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                    gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar3 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar4 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }

                param.put("gambarTtd1", gambar1);
                param.put("gambarTtd2", gambar2);
                param.put("gambarTtd3", gambar3);
                ttdBasah = gambar4;
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiPemberi = "", isiPhkRS1 = "", isiPhkRS2 = "", tglSimpan = "", jamSimpan = "", kalimatFoter = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from informasi_tindakan_pembiusan_operasi "
                        + "where waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from informasi_tindakan_pembiusan_operasi "
                        + "where waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'");
                kalimatFoter = Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'");
                
                //pemberi informasi
                if (nipPemberi.equals("") || nipPemberi.equals("-") || nipPemberi.equals("--")) {
                    param.put("lokasiQr", "");
                } else {
                    isiPemberi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Informasi Tindakan Pembiusan", TnmPemberi.getText() + " (Pemberi Informasi)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPemberi, Sequel.cariFolderTte(), "QRTtePemberiInfo.jpg", "select logo from setting");
                    param.put("lokasiQr", Sequel.cariFolderTte() + File.separator + "QRTtePemberiInfo.jpg");
                }
                
                //saksi pihak RS
                if (nipPhkRS.equals("") || nipPhkRS.equals("-") || nipPhkRS.equals("--")) {
                    ttdQrcode1 = "";
                    ttdQrcode2 = "";
                } else {
                    isiPhkRS1 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Informasi Tindakan Pembiusan", TnmSaksiRs.getText() + " (Saksi Pihak RS)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPhkRS1, Sequel.cariFolderTte(), "QRTtePihakRS1.jpg", "select logo from setting");
                    ttdQrcode1 = Sequel.cariFolderTte() + File.separator + "QRTtePihakRS1.jpg";

                    isiPhkRS2 = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    cmbJnsTindakan.getSelectedItem().toString() + " TINDAKAN KEDOKTERAN", TnmSaksiRs.getText() + " (Saksi Pihak RS)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPhkRS2, Sequel.cariFolderTte(), "QRTtePihakRS2.jpg", "select logo from setting");
                    ttdQrcode2 = Sequel.cariFolderTte() + File.separator + "QRTtePihakRS2.jpg";
                }

                if (phkRSternyata.equals("karyawan")) {
                    if (cmbJnsTindakan.getSelectedIndex() == 1 || cmbJnsTindakan.getSelectedIndex() == 2) {
                        param.put("kalimatTte1", kalimatFoter);
                        param.put("kalimatTte2", kalimatFoter);
                        param.put("ttdPihakRS1", ttdQrcode1);
                        param.put("ttdPihakRS2", ttdQrcode2);
                    } else {
                        param.put("kalimatTte1", "");
                        param.put("kalimatTte2", "");
                        param.put("ttdPihakRS1", ttdQrcode1);
                        param.put("ttdPihakRS2", ttdQrcode2);
                    }
                } else {
                    if (cmbJnsTindakan.getSelectedIndex() == 1 || cmbJnsTindakan.getSelectedIndex() == 2) {
                        param.put("kalimatTte1", kalimatFoter);
                        param.put("kalimatTte2", "");
                        param.put("ttdPihakRS1", ttdBasah);
                        param.put("ttdPihakRS2", ttdBasah);
                    } else {
                        param.put("kalimatTte1", "");
                        param.put("kalimatTte2", "");
                        param.put("ttdPihakRS1", ttdBasah);
                        param.put("ttdPihakRS2", ttdBasah);
                    }
                }
                
                if (cmbJnsTindakan.getSelectedIndex() == 1) {
                    Valid.MyReport("rptSetujuTindakanDokterBiusQr.jasper", "report", "::[ Lembar Formulir Persetujuan Tindakan Kedokteran ]::",
                            "SELECT if(it.nm_betttd='','......................',replace(it.nm_betttd,' (Yang Menyatakan)','')) nmbetttd, "
                            + "CONCAT(it.umur_betttd,' / ',it.jenkel_berttd) umur_pj, "
                            + "if(it.alamat_betttd='','......................',it.alamat_betttd) alamat_pj, it.selaku, it.tindakan_berupa, it.jns_tindakan_kedokteran, "
                            + "it.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat_px, "
                            + "CONCAT('Martapura, ',DATE_FORMAT(it.tgl_setuju_tolak,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(it.jam_setuju_tolak,'%H:%i'),' WITA') tgl_surat, "
                            + "if(it.nm_pihak_klg='','......................',replace(it.nm_pihak_klg,' (Pihak Keluarga)','')) phkKeluarga, "
                            + "if(it.nm_pihak_rs='','......................',replace(it.nm_pihak_rs,' (Pihak RS)','')) phkRS FROM informasi_tindakan_pembiusan_operasi it "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=it.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab WHERE it.waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "' "
                            + "and it.jns_tindakan_kedokteran='" + cmbJnsTindakan.getSelectedItem() + "'", param);
                } else if (cmbJnsTindakan.getSelectedIndex() == 2) {
                    Valid.MyReport("rptTolakTindakanDokterBiusQr.jasper", "report", "::[ Lembar Formulir Penolakan Tindakan Kedokteran ]::",
                            "SELECT if(it.nm_betttd='','......................',replace(it.nm_betttd,' (Yang Menyatakan)','')) nmbetttd, "
                            + "CONCAT(it.umur_betttd,' Thn. / ',it.jenkel_berttd) umur_pj, "
                            + "if(it.alamat_betttd='','......................',it.alamat_betttd) alamat_pj, it.selaku, it.jns_tindakan_kedokteran, "
                            + "it.tindakan_berupa, it.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat_px, "
                            + "CONCAT('Martapura, ',DATE_FORMAT(it.tgl_setuju_tolak,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(it.jam_setuju_tolak,'%H:%i'),' WITA') tgl_surat, "
                            + "if(it.nm_pihak_klg='','......................',replace(it.nm_pihak_klg,' (Pihak Keluarga)','')) phkKeluarga, "
                            + "if(it.nm_pihak_rs='','......................',replace(it.nm_pihak_rs,' (Pihak RS)','')) phkRS FROM informasi_tindakan_pembiusan_operasi it "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=it.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab WHERE it.waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "' "
                            + "and it.jns_tindakan_kedokteran='" + cmbJnsTindakan.getSelectedItem() + "'", param);
                }

                Valid.MyReport("rptLembarInformasiTindakanPembiusan2Qr.jasper", "report", "::[ Lembar Informasi Tindakan Pembiusan hal. 2 ]::",
                        "SELECT now() tanggal", param);

                Valid.MyReport("rptLembarInformasiTindakanPembiusan1Qr.jasper", "report", "::[ Lembar Informasi Tindakan Pembiusan hal. 1 ]::",
                        "SELECT now() tanggal", param);

                emptTeks();
                tampil();
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                if (cmbJnsTindakan.getSelectedIndex() == 1) {
                    Valid.MyReport("rptSetujuTindakanDokterBius.jasper", "report", "::[ Lembar Formulir Persetujuan Tindakan Kedokteran ]::",
                            "SELECT if(it.nm_betttd='','......................',replace(it.nm_betttd,' (Yang Menyatakan)','')) nmbetttd, "
                            + "CONCAT(it.umur_betttd,' / ',it.jenkel_berttd) umur_pj, "
                            + "if(it.alamat_betttd='','......................',it.alamat_betttd) alamat_pj, it.selaku, it.tindakan_berupa, it.jns_tindakan_kedokteran, "
                            + "it.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat_px, "
                            + "CONCAT('Martapura, ',DATE_FORMAT(it.tgl_setuju_tolak,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(it.jam_setuju_tolak,'%H:%i'),' WITA') tgl_surat, "
                            + "if(it.nm_pihak_klg='','......................',replace(it.nm_pihak_klg,' (Pihak Keluarga)','')) phkKeluarga, "
                            + "if(it.nm_pihak_rs='','......................',replace(it.nm_pihak_rs,' (Pihak RS)','')) phkRS FROM informasi_tindakan_pembiusan_operasi it "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=it.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab WHERE it.waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "' "
                            + "and it.jns_tindakan_kedokteran='" + cmbJnsTindakan.getSelectedItem() + "'", param);
                } else if (cmbJnsTindakan.getSelectedIndex() == 2) {
                    Valid.MyReport("rptTolakTindakanDokterBius.jasper", "report", "::[ Lembar Formulir Penolakan Tindakan Kedokteran ]::",
                            "SELECT if(it.nm_betttd='','......................',replace(it.nm_betttd,' (Yang Menyatakan)','')) nmbetttd, "
                            + "CONCAT(it.umur_betttd,' Thn. / ',it.jenkel_berttd) umur_pj, "
                            + "if(it.alamat_betttd='','......................',it.alamat_betttd) alamat_pj, it.selaku, it.jns_tindakan_kedokteran, "
                            + "it.tindakan_berupa, it.alasan_penolakan, p.nm_pasien, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'. / ',IF(p.jk='L','Laki-laki','Perempuan')) umur_px, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat_px, "
                            + "CONCAT('Martapura, ',DATE_FORMAT(it.tgl_setuju_tolak,'%d-%m-%Y'),' Pukul ',DATE_FORMAT(it.jam_setuju_tolak,'%H:%i'),' WITA') tgl_surat, "
                            + "if(it.nm_pihak_klg='','......................',replace(it.nm_pihak_klg,' (Pihak Keluarga)','')) phkKeluarga, "
                            + "if(it.nm_pihak_rs='','......................',replace(it.nm_pihak_rs,' (Pihak RS)','')) phkRS FROM informasi_tindakan_pembiusan_operasi it "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=it.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab WHERE it.waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "' "
                            + "and it.jns_tindakan_kedokteran='" + cmbJnsTindakan.getSelectedItem() + "'", param);
                }
                
                Valid.MyReport("rptLembarInformasiTindakanPembiusan2.jasper", "report", "::[ Lembar Informasi Tindakan Pembiusan hal. 2 ]::",
                        "SELECT now() tanggal", param);
                
                Valid.MyReport("rptLembarInformasiTindakanPembiusan1.jasper", "report", "::[ Lembar Informasi Tindakan Pembiusan hal. 1 ]::",
                        "SELECT now() tanggal", param);
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbInformasi.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnDrPelaksanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrPelaksanaActionPerformed
        akses.setform("RMInformasiTindakanPembiusan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrPelaksanaActionPerformed

    private void BtnPemberiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPemberiActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMInformasiTindakanPembiusan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPemberiActionPerformed

    private void TnmPenerimaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmPenerimaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkDiagKerja.requestFocus();
        }
    }//GEN-LAST:event_TnmPenerimaKeyPressed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TnmSaksiRsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSaksiRsKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmSaksiKlg.requestFocus();
        }
    }//GEN-LAST:event_TnmSaksiRsKeyPressed

    private void TnmSaksiKlgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSaksiKlgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJnsTindakan.requestFocus();
        }
    }//GEN-LAST:event_TnmSaksiKlgKeyPressed

    private void cmbJnsTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJnsTindakanActionPerformed
        Talasan.setText("");
        if (cmbJnsTindakan.getSelectedIndex() == 0 || cmbJnsTindakan.getSelectedIndex() == 1) {
            Talasan.setEnabled(false);
        } else if (cmbJnsTindakan.getSelectedIndex() == 2) {
            Talasan.setEnabled(true);
        }
    }//GEN-LAST:event_cmbJnsTindakanActionPerformed

    private void TumurBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TumurBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenkel.requestFocus();
        }
    }//GEN-LAST:event_TumurBerttdKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TketDiagKerjaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketDiagKerjaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkDiagBanding.requestFocus();
        }
    }//GEN-LAST:event_TketDiagKerjaKeyPressed

    private void TketDiagBandingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketDiagBandingKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tklinis.requestFocus();
        }
    }//GEN-LAST:event_TketDiagBandingKeyPressed

    private void TklinisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TklinisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tradiologi.requestFocus();
        }
    }//GEN-LAST:event_TklinisKeyPressed

    private void TradiologiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TradiologiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tlab.requestFocus();
        }
    }//GEN-LAST:event_TradiologiKeyPressed

    private void TlabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlabKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tekg.requestFocus();
        }
    }//GEN-LAST:event_TlabKeyPressed

    private void TekgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TekgKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkIntubasi.requestFocus();
        }
    }//GEN-LAST:event_TekgKeyPressed

    private void TtataCaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtataCaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkShock.requestFocus();
        }
    }//GEN-LAST:event_TtataCaraKeyPressed

    private void TprognosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprognosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Talternatif.requestFocus();
        }
    }//GEN-LAST:event_TprognosisKeyPressed

    private void TalternatifKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalternatifKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkReakAler.requestFocus();
        }
    }//GEN-LAST:event_TalternatifKeyPressed

    private void TlainlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglInformasi.requestFocus();
        }
    }//GEN-LAST:event_TlainlainKeyPressed

    private void TnmBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TumurBerttd.requestFocus();
        }
    }//GEN-LAST:event_TnmBerttdKeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSelaku.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void TtindakanBerupaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanBerupaKeyPressed
        if (cmbJnsTindakan.getSelectedIndex() == 0 || cmbJnsTindakan.getSelectedIndex() == 1) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                TtglSetujuTolak.requestFocus();
            }
        } else if (cmbJnsTindakan.getSelectedIndex() == 2) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                Talasan.requestFocus();
            }
        }
    }//GEN-LAST:event_TtindakanBerupaKeyPressed

    private void TalasanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalasanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglSetujuTolak.requestFocus();
        }
    }//GEN-LAST:event_TalasanKeyPressed

    private void chkDiagKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiagKerjaActionPerformed
        cmbAsaKerja.setSelectedIndex(0);
        TketDiagKerja.setText("");
        if (chkDiagKerja.isSelected() == true) {
            cmbAsaKerja.setEnabled(true);
            TketDiagKerja.setEnabled(true);
            cmbAsaKerja.requestFocus();
        } else {
            cmbAsaKerja.setEnabled(false);
            TketDiagKerja.setEnabled(false);
        }
    }//GEN-LAST:event_chkDiagKerjaActionPerformed

    private void chkDiagBandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDiagBandingActionPerformed
        cmbAsaBanding.setSelectedIndex(0);
        TketDiagBanding.setText("");
        if (chkDiagBanding.isSelected() == true) {
            cmbAsaBanding.setEnabled(true);
            TketDiagBanding.setEnabled(true);
            cmbAsaBanding.requestFocus();
        } else {
            cmbAsaBanding.setEnabled(false);
            TketDiagBanding.setEnabled(false);
        }
    }//GEN-LAST:event_chkDiagBandingActionPerformed

    private void chkSamaDiagBandingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaDiagBandingActionPerformed
        if (chkSamaDiagBanding.isSelected() == true) {
            if (chkDiagKerja.isSelected() == true) {
                chkDiagBanding.setSelected(true);
                cmbAsaBanding.setSelectedItem(cmbAsaKerja.getSelectedItem().toString());
                TketDiagBanding.setText(TketDiagKerja.getText());
                cmbAsaBanding.setEnabled(true);
                TketDiagBanding.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Diagnosa kerja belum ada datanya..!!");
                chkSamaDiagBanding.setSelected(false);
            }
        }
    }//GEN-LAST:event_chkSamaDiagBandingActionPerformed

    private void chkSamaDiagKerjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaDiagKerjaActionPerformed
        if (chkSamaDiagKerja.isSelected() == true) {
            if (chkDiagBanding.isSelected() == true) {
                chkDiagKerja.setSelected(true);
                cmbAsaKerja.setSelectedItem(cmbAsaBanding.getSelectedItem().toString());
                TketDiagKerja.setText(TketDiagBanding.getText());
                cmbAsaKerja.setEnabled(true);
                TketDiagKerja.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Diagnosa banding belum ada datanya..!!");
                chkSamaDiagKerja.setSelected(false);
            }
        }
    }//GEN-LAST:event_chkSamaDiagKerjaActionPerformed

    private void chkSamaPenerima2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPenerima2ActionPerformed
        if (chkSamaPenerima2.isSelected() == true) {
            TnmSaksiKlg.setText(TnmPenerima.getText());
        } else {
            TnmSaksiKlg.setText("");
        }
    }//GEN-LAST:event_chkSamaPenerima2ActionPerformed

    private void chkSamaPenerima3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPenerima3ActionPerformed
        if (chkSamaPenerima3.isSelected() == true) {
            TnmBerttd.setText(TnmPenerima.getText());
        } else {
            TnmBerttd.setText("");
        }
    }//GEN-LAST:event_chkSamaPenerima3ActionPerformed

    private void chkSamaPenerima1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaPenerima1ActionPerformed
        if (chkSamaPenerima1.isSelected() == true) {
            TnmSaksiRs.setText(TnmPemberi.getText());
        } else {
            TnmSaksiRs.setText("");
        }
    }//GEN-LAST:event_chkSamaPenerima1ActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMInformasiTindakanPembiusan");
            DlgHasilPenunjangMedis form = new DlgHasilPenunjangMedis(null, false);
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
            form.setVisible(true);
        }
    }//GEN-LAST:event_MnHasilPemeriksaanPenunjangActionPerformed

    private void MnDokumenJangMedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDokumenJangMedActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMInformasiTindakanPembiusan");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%Informasi Tindakan Pembiusan%'") > 0) {
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

    private void BtnTampilkanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTampilkanQrActionPerformed
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRM.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                    + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                    blob.free();
                }

                BtnCloseIn2ActionPerformed(null);
                tampil();
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRM.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        emptTeks();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

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

    private void BtnTataCaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTataCaraActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Tata Cara Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTataCaraActionPerformed

    private void BtnPrognosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrognosisActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Prognosis ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnPrognosisActionPerformed

    private void BtnAlternatifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlternatifActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Alternatif Tindakan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlternatifActionPerformed

    private void BtnLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLainActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3),
                "::[ Data Template Lain-lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnLainActionPerformed

    private void MnHapusTtdPihakRsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdPihakRsActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan pihak rumah sakit mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFilePihakRS.equals("")) {
                            JOptionPane.showMessageDialog(null, "Pihak rumah sakit belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFilePihakRS) == true) {
                                Sequel.mengedit("informasi_tindakan_pembiusan_operasi",
                                        "waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'", "id_file_nm_pihak_rs=''");
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
    }//GEN-LAST:event_MnHapusTtdPihakRsActionPerformed

    private void MnHapusTtdPihakKlgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdPihakKlgActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan pihak keluarga mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFilePihakKlg.equals("")) {
                            JOptionPane.showMessageDialog(null, "Pihak keluarga pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFilePihakKlg) == true) {
                                Sequel.mengedit("informasi_tindakan_pembiusan_operasi",
                                        "waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'", "id_file_nm_pihak_klg=''");
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
    }//GEN-LAST:event_MnHapusTtdPihakKlgActionPerformed

    private void MnHapusTtdPenerimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdPenerimaActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan penerima informasi mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFilePenerima.equals("")) {
                            JOptionPane.showMessageDialog(null, "Penerima informasi pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFilePenerima) == true) {
                                Sequel.mengedit("informasi_tindakan_pembiusan_operasi",
                                        "waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'", "id_file_penerima_info=''");
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
    }//GEN-LAST:event_MnHapusTtdPenerimaActionPerformed

    private void MnHapusTtdMenyatakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdMenyatakanActionPerformed
        if (tbInformasi.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan yang bertanda tangan/menyatakan pasien ini mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileMenyatakan.equals("")) {
                            JOptionPane.showMessageDialog(null, "Yang bertanda tangan/menyatakan pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileMenyatakan) == true) {
                                Sequel.mengedit("informasi_tindakan_pembiusan_operasi",
                                        "waktu_simpan='" + tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 84).toString() + "'", "id_file_nm_menyatakan=''");
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
    }//GEN-LAST:event_MnHapusTtdMenyatakanActionPerformed

    private void BtnPihakRSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPihakRSActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMInformasiTindakanPembiusan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPihakRSActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMInformasiTindakanPembiusan dialog = new RMInformasiTindakanPembiusan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlternatif;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCloseIn2;
    private widget.Button BtnCopas;
    private widget.Button BtnDrPelaksana;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnLain;
    private widget.Button BtnPemberi;
    private widget.Button BtnPihakRS;
    private widget.Button BtnPrint;
    private widget.Button BtnPrognosis;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Button BtnTataCara;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapusTtdMenyatakan;
    private javax.swing.JMenuItem MnHapusTtdPenerima;
    private javax.swing.JMenuItem MnHapusTtdPihakKlg;
    private javax.swing.JMenuItem MnHapusTtdPihakRs;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll5;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Talamat;
    private widget.TextBox Talasan;
    private widget.TextArea Talternatif;
    private widget.TextBox Tekg;
    private widget.TextBox TketDiagBanding;
    private widget.TextBox TketDiagKerja;
    private widget.TextBox Tklinis;
    private widget.TextBox Tlab;
    private widget.TextArea Tlainlain;
    private widget.TextBox TnmBerttd;
    private widget.TextBox TnmDrPelaksana;
    private widget.TextBox TnmPemberi;
    private widget.TextBox TnmPenerima;
    private widget.TextBox TnmSaksiKlg;
    private widget.TextBox TnmSaksiRs;
    private widget.TextArea Tprognosis;
    private widget.TextBox Tradiologi;
    private widget.TextBox TrgRawat;
    private widget.TextArea TtataCara;
    private widget.TextArea Ttemplate;
    private widget.Tanggal TtglInformasi;
    private widget.Tanggal TtglSetujuTolak;
    private widget.TextBox TtindakanBerupa;
    private widget.TextBox TumurBerttd;
    private javax.swing.JDialog WindowNomorDokumenRM;
    private javax.swing.JDialog WindowTemplate;
    public widget.CekBox chkAnesSpinal;
    public widget.CekBox chkBlok;
    public widget.CekBox chkCideraAkibat;
    public widget.CekBox chkCideraSaraf;
    public widget.CekBox chkDiagBanding;
    public widget.CekBox chkDiagKerja;
    public widget.CekBox chkEfek;
    public widget.CekBox chkEpidural;
    public widget.CekBox chkFm;
    public widget.CekBox chkHenti;
    public widget.CekBox chkInfeksi;
    public widget.CekBox chkIntubasi;
    public widget.CekBox chkJantung;
    public widget.CekBox chkKomLanjut;
    public widget.CekBox chkKomSegera;
    public widget.CekBox chkLma;
    public widget.CekBox chkMeninggal;
    public widget.CekBox chkMuntah;
    public widget.CekBox chkNyeriKepala;
    public widget.CekBox chkNyeriPunggung;
    public widget.CekBox chkPendarahan;
    public widget.CekBox chkPenurunan;
    public widget.CekBox chkPerut;
    public widget.CekBox chkReakAler;
    public widget.CekBox chkReakMual;
    public widget.CekBox chkReakMuntah;
    public widget.CekBox chkReaksiAler;
    public widget.CekBox chkReaksiTok;
    public widget.CekBox chkSamaDiagBanding;
    public widget.CekBox chkSamaDiagKerja;
    public widget.CekBox chkSamaPenerima1;
    public widget.CekBox chkSamaPenerima2;
    public widget.CekBox chkSamaPenerima3;
    public widget.CekBox chkShock;
    public widget.CekBox chkSistemPer;
    public widget.CekBox chkSistemSar;
    public widget.CekBox chkSpinal;
    public widget.CekBox chkSuhu;
    public widget.CekBox chkSyokAnaf;
    public widget.CekBox chkTdkBisa;
    public widget.CekBox chkTenggor;
    public widget.CekBox chkTindakanLar;
    public widget.CekBox chkTiva;
    private widget.ComboBox cmbAsaBanding;
    private widget.ComboBox cmbAsaKerja;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbJnsTindakan;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbSelaku;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame5;
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
    private widget.Label jLabel110;
    private widget.Label jLabel111;
    private widget.Label jLabel112;
    private widget.Label jLabel113;
    private widget.Label jLabel114;
    private widget.Label jLabel115;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel286;
    private widget.Label jLabel287;
    private widget.Label jLabel36;
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
    private widget.Label jLabel86;
    private widget.Label jLabel87;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.Table tbInformasi;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select it.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "pg1.nama nmDokter, pg2.nama nmPemberi, date_format(it.tgl_informasi,'%d-%m-%Y') tglInformasi, time_format(it.jam_informasi,'%H:%i Wita') jamInformasi, "
                    + "date_format(it.tgl_setuju_tolak,'%d-%m-%Y') tglSetujuTolak, time_format(it.jam_setuju_tolak,'%H:%i Wita') jamSetujuTolak, "
                    + "replace(it.nm_penerima_info,' (Penerima Informasi)','') nmpenerima, replace(it.nm_pihak_rs,' (Pihak RS)','') nmpihakrs, "
                    + "replace(it.nm_pihak_klg,' (Pihak Keluarga)','') nmpihakklg, replace(it.nm_betttd,' (Yang Menyatakan)','') nmBerttdMenyatakan "
                    + "from informasi_tindakan_pembiusan_operasi it inner join reg_periksa rp on rp.no_rawat=it.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=it.nip_dokter_pelaksana "
                    + "inner join pegawai pg2 on pg2.nik=it.nip_pemberi_info where "
                    + "it.tgl_informasi between ? and ? and it.no_rawat LIKE ? or "
                    + "it.tgl_informasi between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "it.tgl_informasi between ? and ? and p.nm_pasien LIKE ? or "
                    + "it.tgl_informasi between ? and ? and pg1.nama LIKE ? or "
                    + "it.tgl_informasi between ? and ? and pg2.nama LIKE ? or "
                    + "it.tgl_informasi between ? and ? and it.jns_tindakan_kedokteran LIKE ? or "
                    + "it.tgl_informasi between ? and ? and it.ruang_rawat LIKE ? ORDER BY it.tgl_informasi desc");
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
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText() + "%");                
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("nmDokter"),
                        rs.getString("nmPemberi"),
                        rs.getString("nmpenerima"),
                        rs.getString("tglInformasi"),
                        rs.getString("jamInformasi"),
                        rs.getString("jns_tindakan_kedokteran"),
                        rs.getString("tglSetujuTolak"),
                        rs.getString("jamSetujuTolak"),                        
                        rs.getString("nip_dokter_pelaksana"),
                        rs.getString("nip_pemberi_info"),
                        rs.getString("nm_penerima_info"),
                        rs.getString("cek_diagnosa_kerja"),
                        rs.getString("cek_diagnosa_banding"),
                        rs.getString("asa_diag_kerja"),
                        rs.getString("ket_asa_diag_kerja"),
                        rs.getString("asa_diag_banding"),
                        rs.getString("ket_asa_diag_banding"),
                        rs.getString("klinis"),
                        rs.getString("radiologi"),
                        rs.getString("lab"),
                        rs.getString("ekg"),
                        rs.getString("intubasi"),
                        rs.getString("lma"),
                        rs.getString("fm"),
                        rs.getString("tiva"),
                        rs.getString("spinal"),
                        rs.getString("epidural"),
                        rs.getString("blok_perifier"),
                        rs.getString("tata_cara_tindakan"),
                        rs.getString("shock"),
                        rs.getString("henti_jantung"),
                        rs.getString("meninggal"),
                        rs.getString("bius_umum_sis_pernapasan"),
                        rs.getString("bius_umum_jantung"),
                        rs.getString("bius_umum_sis_saraf"),
                        rs.getString("bius_umum_tindakan"),
                        rs.getString("bius_umum_suhu"),
                        rs.getString("bius_umum_efek"),
                        rs.getString("bius_umum_cidera"),
                        rs.getString("bius_umum_muntah"),
                        rs.getString("bius_umum_perut"),
                        rs.getString("bius_umum_tenggorokan"),
                        rs.getString("bius_reg_kompli_segera"),
                        rs.getString("bius_reg_penurunan"),
                        rs.getString("bius_reg_anes"),
                        rs.getString("bius_reg_reaksi_tok"),
                        rs.getString("bius_reg_reaksi_alergi"),
                        rs.getString("bius_reg_kompli_lanjutan"),
                        rs.getString("bius_reg_nyeri_kepala"),
                        rs.getString("bius_reg_nyeri_punggung"),
                        rs.getString("bius_reg_tidak"),
                        rs.getString("bius_reg_infeksi"),
                        rs.getString("bius_reg_cidera"),
                        rs.getString("bius_reg_pendarahan"),
                        rs.getString("prognosis"),
                        rs.getString("alternatif_tindakan"),
                        rs.getString("reaksi_alergi"),
                        rs.getString("reaksi_mual"),
                        rs.getString("reaksi_muntah"),
                        rs.getString("syok_anafilaktik"),
                        rs.getString("lain_lain"),
                        rs.getString("tgl_informasi"),
                        rs.getString("jam_informasi"),
                        rs.getString("nmpihakrs"),
                        rs.getString("nmpihakklg"),
                        rs.getString("jns_tindakan_kedokteran"),
                        rs.getString("nmBerttdMenyatakan"),
                        rs.getString("umur_betttd"),
                        rs.getString("jenkel_berttd"),
                        rs.getString("alamat_betttd"),
                        rs.getString("selaku"),
                        rs.getString("tindakan_berupa"),
                        rs.getString("alasan_penolakan"),
                        rs.getString("tgl_setuju_tolak"),
                        rs.getString("jam_setuju_tolak"),
                        rs.getString("id_file_penerima_info"),
                        rs.getString("id_file_nm_pihak_rs"),
                        rs.getString("id_file_nm_pihak_klg"),
                        rs.getString("id_file_nm_menyatakan"),
                        rs.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMLaporanOperasi.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount.setText("" + tabMode.getRowCount());
    }
    
    public void emptTeks() {
        nipDrPelaksana = "-";
        TnmDrPelaksana.setText("-");
        nipPemberi = "-";
        TnmPemberi.setText("-");        
        TnmPenerima.setText("");        
        chkDiagKerja.setSelected(false);        
        cmbAsaKerja.setSelectedIndex(0);
        TketDiagKerja.setText("");
        cmbAsaKerja.setEnabled(false);
        TketDiagKerja.setEnabled(false);
        chkSamaDiagKerja.setSelected(false);        
        chkDiagBanding.setSelected(false);        
        cmbAsaBanding.setSelectedIndex(0);
        TketDiagBanding.setText("");
        cmbAsaBanding.setEnabled(false);
        TketDiagBanding.setEnabled(false);
        chkSamaDiagBanding.setSelected(false);        
        Tklinis.setText("");
        Tradiologi.setText("");
        Tlab.setText("");
        Tekg.setText("");        
        chkIntubasi.setSelected(false);
        chkLma.setSelected(false);
        chkFm.setSelected(false);
        chkTiva.setSelected(false);
        chkSpinal.setSelected(false);
        chkEpidural.setSelected(false);
        chkBlok.setSelected(false);        
        TtataCara.setText("");
        chkShock.setSelected(false);
        chkHenti.setSelected(false);
        chkMeninggal.setSelected(false);
        chkSistemPer.setSelected(false);
        chkJantung.setSelected(false);
        chkSistemSar.setSelected(false);
        chkTindakanLar.setSelected(false);
        chkSuhu.setSelected(false);
        chkEfek.setSelected(false);
        chkCideraAkibat.setSelected(false);
        chkMuntah.setSelected(false);
        chkPerut.setSelected(false);
        chkTenggor.setSelected(false);
        chkKomSegera.setSelected(false);
        chkPenurunan.setSelected(false);
        chkAnesSpinal.setSelected(false);
        chkReaksiTok.setSelected(false);
        chkReaksiAler.setSelected(false);
        chkKomLanjut.setSelected(false);
        chkNyeriKepala.setSelected(false);
        chkNyeriPunggung.setSelected(false);
        chkTdkBisa.setSelected(false);
        chkInfeksi.setSelected(false);
        chkCideraSaraf.setSelected(false);
        chkPendarahan.setSelected(false);
        Tprognosis.setText("");
        Talternatif.setText("");
        chkReakAler.setSelected(false);
        chkReakMual.setSelected(false);
        chkReakMuntah.setSelected(false);
        chkSyokAnaf.setSelected(false);
        Tlainlain.setText("");
        TtglInformasi.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        TnmSaksiRs.setText("");
        chkSamaPenerima1.setSelected(false);
        TnmSaksiKlg.setText("");
        chkSamaPenerima2.setSelected(false);        
        cmbJnsTindakan.setSelectedIndex(0);
        Talasan.setEnabled(false);
        TnmBerttd.setText("");
        chkSamaPenerima3.setSelected(false);
        TumurBerttd.setText("");
        cmbJenkel.setSelectedIndex(0);
        Talamat.setText("");
        cmbSelaku.setSelectedIndex(0);
        TtindakanBerupa.setText("");
        Talasan.setText("");
        TtglSetujuTolak.setDate(new Date());
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        LoadHTML1.setText("");
    }

    private void getData() {
        variabelBersih();
        ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
        if (tbInformasi.getSelectedRow() != -1) {
            TNoRw.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 0).toString());
            TNoRM.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 1).toString());
            TPasien.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 4).toString());
            nipDrPelaksana = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 13).toString();
            TnmDrPelaksana.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 5).toString());
            nipPemberi = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 14).toString();
            TnmPemberi.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 6).toString());            
            TnmPenerima.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 7).toString());
            diagKerja = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 16).toString();
            cmbAsaKerja.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 18).toString());
            TketDiagKerja.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 19).toString());
            diagBanding = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 17).toString();
            cmbAsaBanding.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 20).toString());
            TketDiagBanding.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 21).toString());
            Tklinis.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 22).toString());
            Tradiologi.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 23).toString());
            Tlab.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 24).toString());
            Tekg.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 25).toString());            
            intubasi = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 26).toString();
            lma = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 27).toString();
            fm = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 28).toString();
            tiva = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 29).toString();
            spinal = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 30).toString();
            epidural = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 31).toString();
            blok = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 32).toString();
            TtataCara.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 33).toString());
            shok = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 34).toString();
            henti = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 35).toString();
            meninggal = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 36).toString();            
            sistemPer = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 37).toString();
            jantung = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 38).toString();
            sistemSar = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 39).toString();
            tindakan = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 40).toString();
            suhu = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 41).toString();
            efek = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 42).toString();
            cideraAkibat = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 43).toString();
            muntah = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 44).toString();
            perut = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 45).toString();
            tenggor = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 46).toString();
            kompliSeg = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 47).toString();
            penurunan = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 48).toString();
            anestesi = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 49).toString();
            reakTok = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 50).toString();
            reakAlergiSyok = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 51).toString();
            kompliLan = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 52).toString();
            nyeriKepala = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 53).toString();
            nyeriPung = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 54).toString();
            tdkBisa = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 55).toString();
            infeksi = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 56).toString();
            cideraSaraf = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 57).toString();
            pendarahan = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 58).toString();
            Tprognosis.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 59).toString());
            Talternatif.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 60).toString());
            reakAler = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 61).toString();
            reakMual = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 62).toString();
            reakMun = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 63).toString();
            syokAnaf = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 64).toString();
            Tlainlain.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 65).toString());
            Valid.SetTgl(TtglInformasi, tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 66).toString());
            cmbJam1.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 67).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 67).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 67).toString().substring(6, 8));
            TnmSaksiRs.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 68).toString());
            TnmSaksiKlg.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 69).toString());
            cmbJnsTindakan.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 70).toString());
            TnmBerttd.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 71).toString());            
            TumurBerttd.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 72).toString());
            cmbJenkel.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 73).toString());
            Talamat.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 74).toString());
            cmbSelaku.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 75).toString());
            TtindakanBerupa.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 76).toString());
            Talasan.setText(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 77).toString());
            Valid.SetTgl(TtglSetujuTolak, tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 78).toString());
            cmbJam2.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 79).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 79).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 79).toString().substring(6, 8));            
            idFilePenerima = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 80).toString();
            idFilePihakRS = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 81).toString();
            idFilePihakKlg = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 82).toString();
            idFileMenyatakan = tbInformasi.getValueAt(tbInformasi.getSelectedRow(), 83).toString();            
            tampilTTD();
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }
    
    private void dataCek() {
        chkSamaDiagKerja.setSelected(false);
        chkSamaDiagBanding.setSelected(false);
        
        if (diagKerja.equals("ya")) {
            chkDiagKerja.setSelected(true);
            cmbAsaKerja.setEnabled(true);
            TketDiagKerja.setEnabled(true);
        } else {
            chkDiagKerja.setSelected(false);
            cmbAsaKerja.setEnabled(false);
            TketDiagKerja.setEnabled(false);
        }

        if (diagBanding.equals("ya")) {
            chkDiagBanding.setSelected(true);
            cmbAsaBanding.setEnabled(true);
            TketDiagBanding.setEnabled(true);
        } else {
            chkDiagBanding.setSelected(false);
            cmbAsaBanding.setEnabled(false);
            TketDiagBanding.setEnabled(false);
        }
        
        if (intubasi.equals("ya")) {
            chkIntubasi.setSelected(true);
        } else {
            chkIntubasi.setSelected(false);
        }
        
        if (lma.equals("ya")) {
            chkLma.setSelected(true);
        } else {
            chkLma.setSelected(false);
        }
        
        if (fm.equals("ya")) {
            chkFm.setSelected(true);
        } else {
            chkFm.setSelected(false);
        }
        
        if (tiva.equals("ya")) {
            chkTiva.setSelected(true);
        } else {
            chkTiva.setSelected(false);
        }
        
        if (spinal.equals("ya")) {
            chkSpinal.setSelected(true);
        } else {
            chkSpinal.setSelected(false);
        }
        
        if (epidural.equals("ya")) {
            chkEpidural.setSelected(true);
        } else {
            chkEpidural.setSelected(false);
        }
        
        if (blok.equals("ya")) {
            chkBlok.setSelected(true);
        } else {
            chkBlok.setSelected(false);
        }
        
        if (shok.equals("ya")) {
            chkShock.setSelected(true);
        } else {
            chkShock.setSelected(false);
        }
        
        if (henti.equals("ya")) {
            chkHenti.setSelected(true);
        } else {
            chkHenti.setSelected(false);
        }
        
        if (meninggal.equals("ya")) {
            chkMeninggal.setSelected(true);
        } else {
            chkMeninggal.setSelected(false);
        }
        
        if (sistemPer.equals("ya")) {
            chkSistemPer.setSelected(true);
        } else {
            chkSistemPer.setSelected(false);
        }
        
        if (jantung.equals("ya")) {
            chkJantung.setSelected(true);
        } else {
            chkJantung.setSelected(false);
        }
        
        if (sistemSar.equals("ya")) {
            chkSistemSar.setSelected(true);
        } else {
            chkSistemSar.setSelected(false);
        }
        
        if (tindakan.equals("ya")) {
            chkTindakanLar.setSelected(true);
        } else {
            chkTindakanLar.setSelected(false);
        }
        
        if (suhu.equals("ya")) {
            chkSuhu.setSelected(true);
        } else {
            chkSuhu.setSelected(false);
        }
        
        if (efek.equals("ya")) {
            chkEfek.setSelected(true);
        } else {
            chkEfek.setSelected(false);
        }
        
        if (cideraAkibat.equals("ya")) {
            chkCideraAkibat.setSelected(true);
        } else {
            chkCideraAkibat.setSelected(false);
        }
        
        if (muntah.equals("ya")) {
            chkMuntah.setSelected(true);
        } else {
            chkMuntah.setSelected(false);
        }
        
        if (perut.equals("ya")) {
            chkPerut.setSelected(true);
        } else {
            chkPerut.setSelected(false);
        }
        
        if (tenggor.equals("ya")) {
            chkTenggor.setSelected(true);
        } else {
            chkTenggor.setSelected(false);
        }
        
        if (kompliSeg.equals("ya")) {
            chkKomSegera.setSelected(true);
        } else {
            chkKomSegera.setSelected(false);
        }
        
        if (penurunan.equals("ya")) {
            chkPenurunan.setSelected(true);
        } else {
            chkPenurunan.setSelected(false);
        }
        
        if (anestesi.equals("ya")) {
            chkAnesSpinal.setSelected(true);
        } else {
            chkAnesSpinal.setSelected(false);
        }
        
        if (reakTok.equals("ya")) {
            chkReaksiTok.setSelected(true);
        } else {
            chkReaksiTok.setSelected(false);
        }
        
        if (reakAlergiSyok.equals("ya")) {
            chkReaksiAler.setSelected(true);
        } else {
            chkReaksiAler.setSelected(false);
        }
        
        if (kompliLan.equals("ya")) {
            chkKomLanjut.setSelected(true);
        } else {
            chkKomLanjut.setSelected(false);
        }
        
        if (nyeriKepala.equals("ya")) {
            chkNyeriKepala.setSelected(true);
        } else {
            chkNyeriKepala.setSelected(false);
        }
        
        if (nyeriPung.equals("ya")) {
            chkNyeriPunggung.setSelected(true);
        } else {
            chkNyeriPunggung.setSelected(false);
        }
        
        if (tdkBisa.equals("ya")) {
            chkTdkBisa.setSelected(true);
        } else {
            chkTdkBisa.setSelected(false);
        }
        
        if (infeksi.equals("ya")) {
            chkInfeksi.setSelected(true);
        } else {
            chkInfeksi.setSelected(false);
        }
        
        if (cideraSaraf.equals("ya")) {
            chkCideraSaraf.setSelected(true);
        } else {
            chkCideraSaraf.setSelected(false);
        }
        
        if (pendarahan.equals("ya")) {
            chkPendarahan.setSelected(true);
        } else {
            chkPendarahan.setSelected(false);
        }
        
        if (reakAler.equals("ya")) {
            chkReakAler.setSelected(true);
        } else {
            chkReakAler.setSelected(false);
        }
        
        if (reakMual.equals("ya")) {
            chkReakMual.setSelected(true);
        } else {
            chkReakMual.setSelected(false);
        }
        
        if (reakMun.equals("ya")) {
            chkReakMuntah.setSelected(true);
        } else {
            chkReakMuntah.setSelected(false);
        }
        
        if (syokAnaf.equals("ya")) {
            chkSyokAnaf.setSelected(true);
        } else {
            chkSyokAnaf.setSelected(false);
        }
        
        if (cmbJnsTindakan.getSelectedIndex() == 0 || cmbJnsTindakan.getSelectedIndex() == 1) {
            Talasan.setEnabled(false);
        } else if (cmbJnsTindakan.getSelectedIndex() == 2) {
            Talasan.setEnabled(true);
        }     
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norw + "'"));
        TCari.setText(norw);
    }
    
    private void cekData() {
        if (chkDiagKerja.isSelected() == true) {
            diagKerja = "ya";
        } else {
            diagKerja = "tidak";
        }
        
        if (chkDiagBanding.isSelected() == true) {
            diagBanding = "ya";
        } else {
            diagBanding = "tidak";
        }
        
        if (chkIntubasi.isSelected() == true) {
            intubasi = "ya";
        } else {
            intubasi = "tidak";
        }
        
        if (chkLma.isSelected() == true) {
            lma = "ya";
        } else {
            lma = "tidak";
        }
        
        if (chkFm.isSelected() == true) {
            fm = "ya";
        } else {
            fm = "tidak";
        }
        
        if (chkTiva.isSelected() == true) {
            tiva = "ya";
        } else {
            tiva = "tidak";
        }
        
        if (chkSpinal.isSelected() == true) {
            spinal = "ya";
        } else {
            spinal = "tidak";
        }
        
        if (chkEpidural.isSelected() == true) {
            epidural = "ya";
        } else {
            epidural = "tidak";
        }
        
        if (chkBlok.isSelected() == true) {
            blok = "ya";
        } else {
            blok = "tidak";
        }
        
        if (chkShock.isSelected() == true) {
            shok = "ya";
        } else {
            shok = "tidak";
        }
        
        if (chkHenti.isSelected() == true) {
            henti = "ya";
        } else {
            henti = "tidak";
        }
        
        if (chkMeninggal.isSelected() == true) {
            meninggal = "ya";
        } else {
            meninggal = "tidak";
        }
        
        if (chkSistemPer.isSelected() == true) {
            sistemPer = "ya";
        } else {
            sistemPer = "tidak";
        }
        
        if (chkJantung.isSelected() == true) {
            jantung = "ya";
        } else {
            jantung = "tidak";
        }
        
        if (chkSistemSar.isSelected() == true) {
            sistemSar = "ya";
        } else {
            sistemSar = "tidak";
        }
        
        if (chkTindakanLar.isSelected() == true) {
            tindakan = "ya";
        } else {
            tindakan = "tidak";
        }
        
        if (chkSuhu.isSelected() == true) {
            suhu = "ya";
        } else {
            suhu = "tidak";
        }
        
        if (chkEfek.isSelected() == true) {
            efek = "ya";
        } else {
            efek = "tidak";
        }
        
        if (chkCideraAkibat.isSelected() == true) {
            cideraAkibat = "ya";
        } else {
            cideraAkibat = "tidak";
        }
        
        if (chkMuntah.isSelected() == true) {
            muntah = "ya";
        } else {
            muntah = "tidak";
        }
        
        if (chkPerut.isSelected() == true) {
            perut = "ya";
        } else {
            perut = "tidak";
        }
        
        if (chkTenggor.isSelected() == true) {
            tenggor = "ya";
        } else {
            tenggor = "tidak";
        }
        
        if (chkKomSegera.isSelected() == true) {
            kompliSeg = "ya";
        } else {
            kompliSeg = "tidak";
        }
        
        if (chkPenurunan.isSelected() == true) {
            penurunan = "ya";
        } else {
            penurunan = "tidak";
        }
        
        if (chkAnesSpinal.isSelected() == true) {
            anestesi = "ya";
        } else {
            anestesi = "tidak";
        }
        
        if (chkReaksiTok.isSelected() == true) {
            reakTok = "ya";
        } else {
            reakTok = "tidak";
        }
        
        if (chkReaksiAler.isSelected() == true) {
            reakAlergiSyok = "ya";
        } else {
            reakAlergiSyok = "tidak";
        }
        
        if (chkKomLanjut.isSelected() == true) {
            kompliLan = "ya";
        } else {
            kompliLan = "tidak";
        }
        
        if (chkNyeriKepala.isSelected() == true) {
            nyeriKepala = "ya";
        } else {
            nyeriKepala = "tidak";
        }
        
        if (chkNyeriPunggung.isSelected() == true) {
            nyeriPung = "ya";
        } else {
            nyeriPung = "tidak";
        }
        
        if (chkTdkBisa.isSelected() == true) {
            tdkBisa = "ya";
        } else {
            tdkBisa = "tidak";
        }
        
        if (chkInfeksi.isSelected() == true) {
            infeksi = "ya";
        } else {
            infeksi = "tidak";
        }
        
        if (chkCideraSaraf.isSelected() == true) {
            cideraSaraf = "ya";
        } else {
            cideraSaraf = "tidak";
        }
        
        if (chkPendarahan.isSelected() == true) {
            pendarahan = "ya";
        } else {
            pendarahan = "tidak";
        }
        
        if (chkReakAler.isSelected() == true) {
            reakAler = "ya";
        } else {
            reakAler = "tidak";
        }
        
        if (chkReakMual.isSelected() == true) {
            reakMual = "ya";
        } else {
            reakMual = "tidak";
        }
        
        if (chkReakMuntah.isSelected() == true) {
            reakMun = "ya";
        } else {
            reakMun = "tidak";
        }
        
        if (chkSyokAnaf.isSelected() == true) {
            syokAnaf = "ya";
        } else {
            syokAnaf = "tidak";
        }
    }
    
    private void variabelBersih() {
        nipDrPelaksana = "";
        nipPemberi = "";
        diagKerja = "";
        diagBanding = "";
        intubasi = "";
        lma = "";
        fm = "";
        tiva = "";
        spinal = "";
        epidural = "";
        blok = "";
        shok = "";
        henti = "";
        meninggal = "";
        sistemPer = "";
        jantung = "";
        sistemSar = "";
        tindakan = "";
        suhu = "";
        efek = "";
        cideraAkibat = "";
        muntah = "";
        perut = "";
        tenggor = "";
        kompliSeg = "";
        penurunan = "";
        anestesi = "";
        reakTok = "";
        reakAlergiSyok = "";
        kompliLan = "";
        nyeriKepala = "";
        nyeriPung = "";
        tdkBisa = "";
        infeksi = "";
        cideraSaraf = "";
        pendarahan = "";
        reakAler = "";
        reakMual = "";
        reakMun = "";
        syokAnaf = "";
        idFilePenerima = "";
        idFilePihakRS = "";
        idFilePihakKlg = "";
        idFileMenyatakan = "";
    }
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar1 = "", gambar2 = "", gambar3 = "", gambar4 = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);
                
                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFilePenerima.equals("")) {
                        gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePenerima;
                    }
                    
                    if (idFilePihakRS.equals("")) {
                        gambar2 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar2 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePihakRS;
                    }
                    
                    if (idFilePihakKlg.equals("")) {
                        gambar3 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar3 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFilePihakKlg;
                    }
                    
                    if (idFileMenyatakan.equals("")) {
                        gambar4 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar4 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileMenyatakan;
                    }
                    //ping gagal
                } else {
                    gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar3 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar4 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }                
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                gambar3 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                gambar4 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }
            
            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Penerima Informasi</b></td>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Pihak RS</b></td>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Pihak Keluarga</b></td>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Yang Menyatakan</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar1 + "' width='160' height='160' alt='TTD Pihak Keluarga'><br>(" + TnmPenerima.getText() + ")<br></td>"
                    + "<td valign='middle' align='center'><img src='" + gambar2 + "' width='160' height='160' alt='TTD Pihak RS'><br>(" + TnmSaksiRs.getText() + ")<br></td>"
                    + "<td valign='middle' align='center'><img src='" + gambar3 + "' width='160' height='160' alt='TTD Yang Menyatakan'><br>(" + TnmSaksiKlg.getText() + ")<br></td>"
                    + "<td valign='middle' align='center'><img src='" + gambar4 + "' width='160' height='160' alt='TTD Penerima Informasi'><br>(" + TnmBerttd.getText() + ")<br></td>"
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
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%Informasi Tindakan Pembiusan%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
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
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                ps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, i.* from informasi_tindakan_pembiusan_operasi i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "i.tata_cara_tindakan<>'' and p.no_rkm_medis like ? OR "
                        + "i.tata_cara_tindakan<>'' and p.nm_pasien like ? OR "
                        + "i.tata_cara_tindakan<>'' and i.tata_cara_tindakan like ? ORDER BY i.waktu_simpan desc limit 20");
            } else if (pilihan == 2) {
                ps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, i.* from informasi_tindakan_pembiusan_operasi i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "i.prognosis<>'' and p.no_rkm_medis like ? OR "
                        + "i.prognosis<>'' and p.nm_pasien like ? OR "
                        + "i.prognosis<>'' and i.prognosis like ? ORDER BY i.waktu_simpan desc limit 20");
            } else if (pilihan == 3) {
                ps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, i.* from informasi_tindakan_pembiusan_operasi i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "i.alternatif_tindakan<>'' and p.no_rkm_medis like ? OR "
                        + "i.alternatif_tindakan<>'' and p.nm_pasien like ? OR "
                        + "i.alternatif_tindakan<>'' and i.alternatif_tindakan like ? ORDER BY i.waktu_simpan desc limit 20");
            } else if (pilihan == 4) {
                ps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, i.* from informasi_tindakan_pembiusan_operasi i "
                        + "inner join reg_periksa rp on rp.no_rawat=i.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "i.lain_lain<>'' and p.no_rkm_medis like ? OR "
                        + "i.lain_lain<>'' and p.nm_pasien like ? OR "
                        + "i.lain_lain<>'' and i.lain_lain like ? ORDER BY i.waktu_simpan desc limit 20");
            } 
            
            try {
                if (pilihan == 1) {
                    ps2.setString(1, "%" + TCari1.getText() + "%");
                    ps2.setString(2, "%" + TCari1.getText() + "%");
                    ps2.setString(3, "%" + TCari1.getText() + "%");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode1.addRow(new String[]{
                            rs2.getString("no_rkm_medis"),
                            rs2.getString("nm_pasien"),
                            rs2.getString("tata_cara_tindakan")
                        });
                    }
                } else if (pilihan == 2) {
                    ps3.setString(1, "%" + TCari1.getText() + "%");
                    ps3.setString(2, "%" + TCari1.getText() + "%");
                    ps3.setString(3, "%" + TCari1.getText() + "%");
                    rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        tabMode1.addRow(new String[]{
                            rs3.getString("no_rkm_medis"),
                            rs3.getString("nm_pasien"),
                            rs3.getString("prognosis")
                        });
                    }
                } else if (pilihan == 3) {
                    ps4.setString(1, "%" + TCari1.getText() + "%");
                    ps4.setString(2, "%" + TCari1.getText() + "%");
                    ps4.setString(3, "%" + TCari1.getText() + "%");
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        tabMode1.addRow(new String[]{
                            rs4.getString("no_rkm_medis"),
                            rs4.getString("nm_pasien"),
                            rs4.getString("alternatif_tindakan")
                        });
                    }
                } else if (pilihan == 4) {
                    ps5.setString(1, "%" + TCari1.getText() + "%");
                    ps5.setString(2, "%" + TCari1.getText() + "%");
                    ps5.setString(3, "%" + TCari1.getText() + "%");
                    rs5 = ps5.executeQuery();
                    while (rs5.next()) {
                        tabMode1.addRow(new String[]{
                            rs5.getString("no_rkm_medis"),
                            rs5.getString("nm_pasien"),
                            rs5.getString("lain_lain")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                } else if (rs3 != null) {
                    rs3.close();
                } else if (rs4 != null) {
                    rs4.close();
                } else if (rs5 != null) {
                    rs5.close();
                } 

                if (ps2 != null) {
                    ps2.close();
                } else if (ps3 != null) {
                    ps3.close();
                } else if (ps4 != null) {
                    ps4.close();
                } else if (ps5 != null) {
                    ps5.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            TtataCara.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tprognosis.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            Talternatif.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            Tlainlain.setText(Ttemplate.getText());
        } 
    }
    
    public void awalData() {
        ((RMInformasiTindakanPembiusan.Painter) gambarQR).setImage("");
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        tampil();
    }
}
