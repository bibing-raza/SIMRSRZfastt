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
public class RMSerahTerimaPascaOperasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps2;
    private ResultSet rs, rs2;
    private int i = 0, x = 0, pilihDokter = 0, pilihPetugas = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipDrOperator = "", nipDrAnes = "", nipPrwtIbs = "", nipPrwtRuang = "", 
            ett = "", lma = "", fima = "", tiva = "", spinal = "", epidural = "", cse = "", infil = "", blok = "", topikal = "", ringan = "",
            sedang = "", dalam = "", tdkSakit = "", sedSakit = "", agak = "", menggang = "", sangat = "", tak = "", cekJamInfus1 = "",
            cekJamInfus2 = "", cekJamAnti = "", cekJamAnal = "", asesDewasa = "", asesAnak = "", idFileTtd = "", idParameterTtd = "", 
            URL = "", usernya = "", pwdnya = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSerahTerimaPascaOperasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Serah Terima", "Pukul", "Dokter Operator", "Dokter Anestesi", "Perawat IBS", "Perawat Ruang",
            "diagnosis_medis", "tindakan_operasi", "nip_dr_operator", "ett", "lma", "fima", "tiva", "spinal", "epidural", "cse", "infiltrasi", "block", "sedasi_ringan", "sedasi_sedang",
            "sedasi_dalam", "nip_dr_anestesi", "tgl_pindah", "jam_pindah", "td", "rr", "nadi", "temp", "mual_muntah", "respon_nyeri", "tidak_sakit", "sedikit_sakit", "agak_mengganggu",
            "mengganggu_aktivitas", "sangat_mengganggu", "tidak_tertahankan", "drain", "ngt", "dc", "irigasi", "total_aldret_skor", "total_bromag_skor", "cairan_infus1",
            "jml_cairan_infus1", "cek_jam_cairan1", "jam_cairan_infus1", "cairan_infus2", "jml_cairan_infus2", "cek_jam_cairan2", "jam_cairan_infus2", "transfusi1", "jml_transfusi1",
            "transfusi2", "jml_transfusi2", "antibiotik", "cek_jam_antibiotik", "jam_antibiotik", "analgesik", "cek_jam_analgesik", "jam_analgesik", "obat_lain", "advis_diruangan",
            "jenis_jaringan", "pemeriksaan_pa", "pemeriksaan_kultur", "jaringan_dibawakan", "asesmen_dewasa", "asesmen_anak", "arj_dewasa_riwayat_jatuh", "arj_dewasa_kondisi_kesehatan", 
            "arj_dewasa_alat_bantu", "arj_dewasa_terpasang_infus", "arj_dewasa_gaya_berjalan", "arj_dewasa_status_mental", "arj_anak_usia", "arj_anak_jenis_kelamin", "arj_anak_diagnosis",
            "arj_anak_gangguan_kognitif", "arj_anak_respon", "arj_anak_penggunaan_medikamentosa", "arj_anak_faktor_lingkungan", "tgl_serah", "pukul_serah", "nm_keluarga_pasien",
            "nip_perawat_ibs", "nip_perawat_ruang", "waktu_simpan", "id_file_nm_keluarga_pasien", "topikal"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSerah.setModel(tabMode);
        tbSerah.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSerah.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 94; i++) {
            TableColumn column = tbSerah.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(70);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
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
            }
        }
        tbSerah.setDefaultRenderer(Object.class, new WarnaTable());
        
        Tdiagnosa.setDocument(new batasInput((int) 200).getKata(Tdiagnosa));
        Ttindakan.setDocument(new batasInput((int) 200).getKata(Ttindakan));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Tnadi.setDocument(new batasInput((int) 7).getKata(Tnadi));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        TtotAldret.setDocument(new batasInput((int) 7).getKata(TtotAldret));
        TtotBromag.setDocument(new batasInput((int) 7).getKata(TtotBromag));
        TcairanInfus1.setDocument(new batasInput((int) 100).getKata(TcairanInfus1));
        TjmlInfus1.setDocument(new batasInput((int) 10).getKata(TjmlInfus1));
        TcairanInfus2.setDocument(new batasInput((int) 100).getKata(TcairanInfus2));
        TjmlInfus2.setDocument(new batasInput((int) 10).getKata(TjmlInfus2));
        Ttranfusi1.setDocument(new batasInput((int) 100).getKata(Ttranfusi1));
        TjmlTranfusi1.setDocument(new batasInput((int) 10).getKata(TjmlTranfusi1));
        Ttranfusi2.setDocument(new batasInput((int) 100).getKata(Ttranfusi2));
        TjmlTranfusi2.setDocument(new batasInput((int) 10).getKata(TjmlTranfusi2));
        Tantibiotik.setDocument(new batasInput((int) 200).getKata(Tantibiotik));
        Tanalgesik.setDocument(new batasInput((int) 200).getKata(Tanalgesik));
        TjnsJaringan.setDocument(new batasInput((int) 200).getKata(TjnsJaringan));
        TnmKeluarga.setDocument(new batasInput((int) 200).getKata(TnmKeluarga));
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
                if (akses.getform().equals("RMSerahTerimaPascaOperasi")) {
                    if (pilihDokter == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrOperator  = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDrOperator.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDrOperator.requestFocus();
                        }                        
                    } else if (pilihDokter == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            nipDrAnes = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                            TnmDrAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                            BtnDrAnestesi.requestFocus();
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMSerahTerimaPascaOperasi")) {
                    if (pilihPetugas == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPrwtIbs = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPrwtIbs.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPrwtIbs.requestFocus();
                        }
                    } else if (pilihPetugas == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPrwtRuang = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPrwtRuang.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPrwtRuang.requestFocus();
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
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanPenunjang = new javax.swing.JMenuItem();
        MnDokumenJangMed = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        MnHapusTtd = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel140 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi6 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn2 = new widget.Button();
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
        jLabel64 = new widget.Label();
        Tdiagnosa = new widget.TextBox();
        jLabel65 = new widget.Label();
        Ttindakan = new widget.TextBox();
        jLabel66 = new widget.Label();
        chkEtt = new widget.CekBox();
        chkLma = new widget.CekBox();
        chkFima = new widget.CekBox();
        chkTiva = new widget.CekBox();
        jLabel67 = new widget.Label();
        chkSpinal = new widget.CekBox();
        chkEpidural = new widget.CekBox();
        chkCse = new widget.CekBox();
        jLabel68 = new widget.Label();
        chkInfiltrasi = new widget.CekBox();
        chkBlock = new widget.CekBox();
        jLabel70 = new widget.Label();
        TnmDrOperator = new widget.TextBox();
        jLabel71 = new widget.Label();
        TnmPrwtIbs = new widget.TextBox();
        jLabel74 = new widget.Label();
        TnmDrAnestesi = new widget.TextBox();
        jLabel75 = new widget.Label();
        TnmPrwtRuang = new widget.TextBox();
        BtnDrOperator = new widget.Button();
        BtnPrwtIbs = new widget.Button();
        BtnDrAnestesi = new widget.Button();
        BtnPrwtRuang = new widget.Button();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        TtglPindah = new widget.Tanggal();
        jLabel77 = new widget.Label();
        cmbMual = new widget.ComboBox();
        jLabel89 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel279 = new widget.Label();
        jLabel280 = new widget.Label();
        Ttd = new widget.TextBox();
        Trr = new widget.TextBox();
        Tnadi = new widget.TextBox();
        Ttemp = new widget.TextBox();
        TtglSerah = new widget.Tanggal();
        jLabel93 = new widget.Label();
        cmbJam6 = new widget.ComboBox();
        cmbMnt6 = new widget.ComboBox();
        cmbDtk6 = new widget.ComboBox();
        jLabel286 = new widget.Label();
        jLabel86 = new widget.Label();
        chkRingan = new widget.CekBox();
        chkSedang = new widget.CekBox();
        chkDalam = new widget.CekBox();
        jLabel87 = new widget.Label();
        jLabel94 = new widget.Label();
        jLabel96 = new widget.Label();
        jLabel287 = new widget.Label();
        jLabel281 = new widget.Label();
        jLabel283 = new widget.Label();
        jLabel288 = new widget.Label();
        jLabel289 = new widget.Label();
        jLabel97 = new widget.Label();
        cmbRespon = new widget.ComboBox();
        jLabel98 = new widget.Label();
        PanelWall = new usu.widget.glass.PanelGlass();
        chkTdkSakit = new widget.CekBox();
        chkSedikitSakit = new widget.CekBox();
        chkAgak = new widget.CekBox();
        chkMengganggu = new widget.CekBox();
        chkSangat = new widget.CekBox();
        chkTak = new widget.CekBox();
        jLabel99 = new widget.Label();
        cmbDrain = new widget.ComboBox();
        jLabel100 = new widget.Label();
        cmbNgt = new widget.ComboBox();
        jLabel101 = new widget.Label();
        cmbDc = new widget.ComboBox();
        jLabel102 = new widget.Label();
        cmbIrigasi = new widget.ComboBox();
        jLabel103 = new widget.Label();
        TtotAldret = new widget.TextBox();
        jLabel104 = new widget.Label();
        TtotBromag = new widget.TextBox();
        jLabel105 = new widget.Label();
        jLabel69 = new widget.Label();
        TcairanInfus1 = new widget.TextBox();
        jLabel76 = new widget.Label();
        TcairanInfus2 = new widget.TextBox();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        TjmlInfus1 = new widget.TextBox();
        TjmlInfus2 = new widget.TextBox();
        jLabel108 = new widget.Label();
        jLabel109 = new widget.Label();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        jLabel282 = new widget.Label();
        jLabel78 = new widget.Label();
        Ttranfusi1 = new widget.TextBox();
        jLabel110 = new widget.Label();
        TjmlTranfusi1 = new widget.TextBox();
        jLabel111 = new widget.Label();
        jLabel79 = new widget.Label();
        Ttranfusi2 = new widget.TextBox();
        jLabel112 = new widget.Label();
        TjmlTranfusi2 = new widget.TextBox();
        jLabel113 = new widget.Label();
        chkJamCairan1 = new widget.CekBox();
        chkJamCairan2 = new widget.CekBox();
        jLabel114 = new widget.Label();
        Tantibiotik = new widget.TextBox();
        chkJamAntibiotik = new widget.CekBox();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel284 = new widget.Label();
        jLabel115 = new widget.Label();
        Tanalgesik = new widget.TextBox();
        chkJamAnalgesik = new widget.CekBox();
        cmbJam5 = new widget.ComboBox();
        cmbMnt5 = new widget.ComboBox();
        cmbDtk5 = new widget.ComboBox();
        jLabel285 = new widget.Label();
        jLabel116 = new widget.Label();
        TobatLain = new widget.TextBox();
        jLabel117 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        TjnsJaringan = new widget.TextBox();
        jLabel120 = new widget.Label();
        cmbPemeriksaanPA = new widget.ComboBox();
        jLabel121 = new widget.Label();
        cmbPemeriksaanKul = new widget.ComboBox();
        jLabel122 = new widget.Label();
        cmbJaringanDibawkan = new widget.ComboBox();
        jLabel123 = new widget.Label();
        jLabel125 = new widget.Label();
        jLabel126 = new widget.Label();
        cmbRiwJatuh = new widget.ComboBox();
        jLabel127 = new widget.Label();
        cmbKondisi = new widget.ComboBox();
        jLabel128 = new widget.Label();
        cmbAlat = new widget.ComboBox();
        jLabel129 = new widget.Label();
        cmbTerpasang = new widget.ComboBox();
        jLabel130 = new widget.Label();
        cmbGaya = new widget.ComboBox();
        jLabel131 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel132 = new widget.Label();
        jLabel133 = new widget.Label();
        jLabel134 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel136 = new widget.Label();
        jLabel137 = new widget.Label();
        TskorRiw = new widget.TextBox();
        TskorKon = new widget.TextBox();
        TskorAlat = new widget.TextBox();
        TskorTerpasang = new widget.TextBox();
        TskorGaya = new widget.TextBox();
        TskorStatus = new widget.TextBox();
        jLabel138 = new widget.Label();
        TtotSkorDewasa = new widget.TextBox();
        jLabel139 = new widget.Label();
        TkesDewasa = new widget.TextBox();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        cmbUsia = new widget.ComboBox();
        jLabel143 = new widget.Label();
        TskorUsia = new widget.TextBox();
        jLabel144 = new widget.Label();
        cmbJenkel = new widget.ComboBox();
        jLabel145 = new widget.Label();
        TskorJenkel = new widget.TextBox();
        jLabel146 = new widget.Label();
        cmbDiagnosis = new widget.ComboBox();
        jLabel147 = new widget.Label();
        TskorDiag = new widget.TextBox();
        jLabel148 = new widget.Label();
        cmbGang = new widget.ComboBox();
        jLabel149 = new widget.Label();
        TskorGang = new widget.TextBox();
        jLabel150 = new widget.Label();
        cmbResTerhadap = new widget.ComboBox();
        jLabel151 = new widget.Label();
        TskorResTerhadap = new widget.TextBox();
        jLabel152 = new widget.Label();
        jLabel153 = new widget.Label();
        cmbPenggu = new widget.ComboBox();
        jLabel154 = new widget.Label();
        TskorPenggu = new widget.TextBox();
        jLabel155 = new widget.Label();
        cmbFaktor = new widget.ComboBox();
        jLabel156 = new widget.Label();
        TskorFaktor = new widget.TextBox();
        jLabel157 = new widget.Label();
        TtotSkorAnak = new widget.TextBox();
        jLabel158 = new widget.Label();
        TkesAnak = new widget.TextBox();
        jLabel159 = new widget.Label();
        jLabel160 = new widget.Label();
        TnmKeluarga = new widget.TextBox();
        chkDewasa = new widget.CekBox();
        chkAnak = new widget.CekBox();
        chkTopikal = new widget.CekBox();
        PanelInput1 = new javax.swing.JPanel();
        panelGlass13 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbSerah = new widget.Table();
        panelGlass14 = new widget.panelisi();
        panelGlass15 = new widget.panelisi();
        scrollPane3 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel82 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
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

        MnHapusTtd.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtd.setText("Hapus Tanda Tangan");
        MnHapusTtd.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtd.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtd.setIconTextGap(5);
        MnHapusTtd.setName("MnHapusTtd"); // NOI18N
        MnHapusTtd.setPreferredSize(new java.awt.Dimension(160, 26));
        MnHapusTtd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdActionPerformed(evt);
            }
        });
        jPopupMenu2.add(MnHapusTtd);

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(160, 26));
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

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Pilih Rekam Medis :");
        jLabel140.setName("jLabel140"); // NOI18N
        panelisi3.add(jLabel140);
        jLabel140.setBounds(0, 10, 120, 23);

        cmbRM.setBackground(new java.awt.Color(245, 253, 240));
        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRM.setLightWeightPopupEnabled(false);
        cmbRM.setName("cmbRM"); // NOI18N
        panelisi3.add(cmbRM);
        cmbRM.setBounds(127, 10, 550, 23);

        internalFrame6.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

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
        panelisi6.add(BtnTampilkanQr);

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
        panelisi6.add(BtnCloseIn2);

        internalFrame6.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Serah Terima Pasien Pasca Operasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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
        panelGlass9.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Hasil Pemeriksaan Penunjang Medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1824));
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

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Diagnosa Medis :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        Tdiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosa.setName("Tdiagnosa"); // NOI18N
        Tdiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosaKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosa);
        Tdiagnosa.setBounds(145, 66, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Tindakan Operasi :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 140, 23);

        Ttindakan.setForeground(new java.awt.Color(0, 0, 0));
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        FormInput.add(Ttindakan);
        Ttindakan.setBounds(145, 94, 615, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Anestesi GA :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 150, 140, 23);

        chkEtt.setBackground(new java.awt.Color(255, 255, 250));
        chkEtt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkEtt.setForeground(new java.awt.Color(0, 0, 0));
        chkEtt.setText("ETT");
        chkEtt.setBorderPainted(true);
        chkEtt.setBorderPaintedFlat(true);
        chkEtt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtt.setName("chkEtt"); // NOI18N
        chkEtt.setOpaque(false);
        chkEtt.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkEtt);
        chkEtt.setBounds(145, 150, 60, 23);

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
        chkLma.setBounds(215, 150, 60, 23);

        chkFima.setBackground(new java.awt.Color(255, 255, 250));
        chkFima.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFima.setForeground(new java.awt.Color(0, 0, 0));
        chkFima.setText("FIMA");
        chkFima.setBorderPainted(true);
        chkFima.setBorderPaintedFlat(true);
        chkFima.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFima.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFima.setName("chkFima"); // NOI18N
        chkFima.setOpaque(false);
        chkFima.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkFima);
        chkFima.setBounds(288, 150, 60, 23);

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
        chkTiva.setBounds(360, 150, 60, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("RA :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 178, 140, 23);

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
        chkSpinal.setBounds(145, 178, 60, 23);

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
        chkEpidural.setBounds(215, 178, 65, 23);

        chkCse.setBackground(new java.awt.Color(255, 255, 250));
        chkCse.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkCse.setForeground(new java.awt.Color(0, 0, 0));
        chkCse.setText("CSE");
        chkCse.setBorderPainted(true);
        chkCse.setBorderPaintedFlat(true);
        chkCse.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCse.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCse.setName("chkCse"); // NOI18N
        chkCse.setOpaque(false);
        chkCse.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCse);
        chkCse.setBounds(288, 178, 60, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Lokal :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 206, 140, 23);

        chkInfiltrasi.setBackground(new java.awt.Color(255, 255, 250));
        chkInfiltrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkInfiltrasi.setForeground(new java.awt.Color(0, 0, 0));
        chkInfiltrasi.setText("Infiltrasi");
        chkInfiltrasi.setBorderPainted(true);
        chkInfiltrasi.setBorderPaintedFlat(true);
        chkInfiltrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkInfiltrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkInfiltrasi.setName("chkInfiltrasi"); // NOI18N
        chkInfiltrasi.setOpaque(false);
        chkInfiltrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkInfiltrasi);
        chkInfiltrasi.setBounds(145, 206, 70, 23);

        chkBlock.setBackground(new java.awt.Color(255, 255, 250));
        chkBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBlock.setForeground(new java.awt.Color(0, 0, 0));
        chkBlock.setText("Block");
        chkBlock.setBorderPainted(true);
        chkBlock.setBorderPaintedFlat(true);
        chkBlock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBlock.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBlock.setName("chkBlock"); // NOI18N
        chkBlock.setOpaque(false);
        chkBlock.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkBlock);
        chkBlock.setBounds(225, 206, 55, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Dokter Operator :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 122, 140, 23);

        TnmDrOperator.setEditable(false);
        TnmDrOperator.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrOperator.setName("TnmDrOperator"); // NOI18N
        FormInput.add(TnmDrOperator);
        TnmDrOperator.setBounds(145, 122, 410, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Nama Perawat IBS :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1756, 140, 23);

        TnmPrwtIbs.setEditable(false);
        TnmPrwtIbs.setForeground(new java.awt.Color(0, 0, 0));
        TnmPrwtIbs.setName("TnmPrwtIbs"); // NOI18N
        FormInput.add(TnmPrwtIbs);
        TnmPrwtIbs.setBounds(145, 1756, 410, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Dokter Anestesi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 262, 140, 23);

        TnmDrAnestesi.setEditable(false);
        TnmDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrAnestesi.setName("TnmDrAnestesi"); // NOI18N
        FormInput.add(TnmDrAnestesi);
        TnmDrAnestesi.setBounds(145, 262, 410, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Nama Perawat Ruang :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 1784, 140, 23);

        TnmPrwtRuang.setEditable(false);
        TnmPrwtRuang.setForeground(new java.awt.Color(0, 0, 0));
        TnmPrwtRuang.setName("TnmPrwtRuang"); // NOI18N
        FormInput.add(TnmPrwtRuang);
        TnmPrwtRuang.setBounds(145, 1784, 410, 23);

        BtnDrOperator.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrOperator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrOperator.setMnemonic('1');
        BtnDrOperator.setToolTipText("Alt+1");
        BtnDrOperator.setName("BtnDrOperator"); // NOI18N
        BtnDrOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrOperatorActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrOperator);
        BtnDrOperator.setBounds(560, 122, 28, 23);

        BtnPrwtIbs.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrwtIbs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPrwtIbs.setMnemonic('1');
        BtnPrwtIbs.setToolTipText("Alt+1");
        BtnPrwtIbs.setName("BtnPrwtIbs"); // NOI18N
        BtnPrwtIbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrwtIbsActionPerformed(evt);
            }
        });
        FormInput.add(BtnPrwtIbs);
        BtnPrwtIbs.setBounds(560, 1756, 28, 23);

        BtnDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrAnestesi.setMnemonic('1');
        BtnDrAnestesi.setToolTipText("Alt+1");
        BtnDrAnestesi.setName("BtnDrAnestesi"); // NOI18N
        BtnDrAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrAnestesi);
        BtnDrAnestesi.setBounds(560, 262, 28, 23);

        BtnPrwtRuang.setForeground(new java.awt.Color(0, 0, 0));
        BtnPrwtRuang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPrwtRuang.setMnemonic('1');
        BtnPrwtRuang.setToolTipText("Alt+1");
        BtnPrwtRuang.setName("BtnPrwtRuang"); // NOI18N
        BtnPrwtRuang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrwtRuangActionPerformed(evt);
            }
        });
        FormInput.add(BtnPrwtRuang);
        BtnPrwtRuang.setBounds(560, 1784, 28, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(430, 290, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(482, 290, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(534, 290, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(585, 290, 50, 23);

        TtglPindah.setEditable(false);
        TtglPindah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2026" }));
        TtglPindah.setDisplayFormat("dd-MM-yyyy");
        TtglPindah.setName("TtglPindah"); // NOI18N
        TtglPindah.setOpaque(false);
        TtglPindah.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPindah);
        TtglPindah.setBounds(287, 290, 90, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("b. Reaksi Mual & Muntah :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 374, 175, 23);

        cmbMual.setBackground(new java.awt.Color(245, 253, 240));
        cmbMual.setForeground(new java.awt.Color(0, 0, 0));
        cmbMual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMual.setLightWeightPopupEnabled(false);
        cmbMual.setName("cmbMual"); // NOI18N
        FormInput.add(cmbMual);
        cmbMual.setBounds(180, 374, 60, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("ALAT BANTU YANG TERPASANG :");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 733, 210, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(598, 873, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(650, 873, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(701, 873, 45, 23);

        jLabel279.setForeground(new java.awt.Color(0, 0, 0));
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel279.setText("Wita");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(753, 873, 50, 23);

        jLabel280.setForeground(new java.awt.Color(0, 0, 0));
        jLabel280.setText("TD :");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(145, 318, 30, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(180, 318, 80, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(180, 346, 60, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(345, 318, 60, 23);

        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(332, 346, 60, 23);

        TtglSerah.setEditable(false);
        TtglSerah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2026" }));
        TtglSerah.setDisplayFormat("dd-MM-yyyy");
        TtglSerah.setName("TtglSerah"); // NOI18N
        TtglSerah.setOpaque(false);
        TtglSerah.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglSerah);
        TtglSerah.setBounds(318, 1700, 90, 23);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setText("Pukul :");
        jLabel93.setName("jLabel93"); // NOI18N
        FormInput.add(jLabel93);
        jLabel93.setBounds(410, 1700, 50, 23);

        cmbJam6.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam6.setName("cmbJam6"); // NOI18N
        cmbJam6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam6MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam6);
        cmbJam6.setBounds(466, 1700, 45, 23);

        cmbMnt6.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt6.setName("cmbMnt6"); // NOI18N
        cmbMnt6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt6MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt6);
        cmbMnt6.setBounds(518, 1700, 45, 23);

        cmbDtk6.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk6.setName("cmbDtk6"); // NOI18N
        cmbDtk6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk6MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk6);
        cmbDtk6.setBounds(570, 1700, 45, 23);

        jLabel286.setForeground(new java.awt.Color(0, 0, 0));
        jLabel286.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel286.setText("Wita");
        jLabel286.setName("jLabel286"); // NOI18N
        FormInput.add(jLabel286);
        jLabel286.setBounds(620, 1700, 50, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Sedasi :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 234, 140, 23);

        chkRingan.setBackground(new java.awt.Color(255, 255, 250));
        chkRingan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRingan.setForeground(new java.awt.Color(0, 0, 0));
        chkRingan.setText("Ringan");
        chkRingan.setBorderPainted(true);
        chkRingan.setBorderPaintedFlat(true);
        chkRingan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRingan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRingan.setName("chkRingan"); // NOI18N
        chkRingan.setOpaque(false);
        chkRingan.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkRingan);
        chkRingan.setBounds(145, 234, 70, 23);

        chkSedang.setBackground(new java.awt.Color(255, 255, 250));
        chkSedang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSedang.setForeground(new java.awt.Color(0, 0, 0));
        chkSedang.setText("Sedang");
        chkSedang.setBorderPainted(true);
        chkSedang.setBorderPaintedFlat(true);
        chkSedang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedang.setName("chkSedang"); // NOI18N
        chkSedang.setOpaque(false);
        chkSedang.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedang);
        chkSedang.setBounds(225, 234, 65, 23);

        chkDalam.setBackground(new java.awt.Color(255, 255, 250));
        chkDalam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDalam.setForeground(new java.awt.Color(0, 0, 0));
        chkDalam.setText("Dalam");
        chkDalam.setBorderPainted(true);
        chkDalam.setBorderPaintedFlat(true);
        chkDalam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDalam.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDalam.setName("chkDalam"); // NOI18N
        chkDalam.setOpaque(false);
        chkDalam.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkDalam);
        chkDalam.setBounds(300, 234, 70, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Kondisi Pasien Terakhir (Saat Dipindahkan) Tanggal :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 290, 280, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Pukul :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(375, 290, 47, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("a. Tanda Vital :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 318, 140, 23);

        jLabel287.setForeground(new java.awt.Color(0, 0, 0));
        jLabel287.setText("RR :");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(145, 346, 30, 23);

        jLabel281.setForeground(new java.awt.Color(0, 0, 0));
        jLabel281.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel281.setText("mmHg     Nadi :");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(266, 318, 75, 23);

        jLabel283.setForeground(new java.awt.Color(0, 0, 0));
        jLabel283.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel283.setText("x/menit    Temp :");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(246, 346, 84, 23);

        jLabel288.setForeground(new java.awt.Color(0, 0, 0));
        jLabel288.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel288.setText("x/menit");
        jLabel288.setName("jLabel288"); // NOI18N
        FormInput.add(jLabel288);
        jLabel288.setBounds(410, 318, 50, 23);

        jLabel289.setForeground(new java.awt.Color(0, 0, 0));
        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel289.setText("°C");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(400, 346, 30, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("c. Respon Nyeri :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 402, 175, 23);

        cmbRespon.setBackground(new java.awt.Color(245, 253, 240));
        cmbRespon.setForeground(new java.awt.Color(0, 0, 0));
        cmbRespon.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRespon.setLightWeightPopupEnabled(false);
        cmbRespon.setName("cmbRespon"); // NOI18N
        FormInput.add(cmbRespon);
        cmbRespon.setBounds(180, 402, 60, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Dengan Skala Wajah : Beri Tanda Centang ==>");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 430, 280, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(50, 486, 540, 240);

        chkTdkSakit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkTdkSakit.setForeground(new java.awt.Color(0, 0, 0));
        chkTdkSakit.setToolTipText("Tidak Sakit");
        chkTdkSakit.setBorderPainted(true);
        chkTdkSakit.setBorderPaintedFlat(true);
        chkTdkSakit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTdkSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTdkSakit.setName("chkTdkSakit"); // NOI18N
        chkTdkSakit.setOpaque(false);
        chkTdkSakit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTdkSakit);
        chkTdkSakit.setBounds(80, 458, 35, 23);

        chkSedikitSakit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkSedikitSakit.setForeground(new java.awt.Color(0, 0, 0));
        chkSedikitSakit.setToolTipText("Sedikit Sakit");
        chkSedikitSakit.setBorderPainted(true);
        chkSedikitSakit.setBorderPaintedFlat(true);
        chkSedikitSakit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSedikitSakit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedikitSakit.setName("chkSedikitSakit"); // NOI18N
        chkSedikitSakit.setOpaque(false);
        chkSedikitSakit.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSedikitSakit);
        chkSedikitSakit.setBounds(170, 458, 35, 23);

        chkAgak.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkAgak.setForeground(new java.awt.Color(0, 0, 0));
        chkAgak.setToolTipText("Agak Mengganggu");
        chkAgak.setBorderPainted(true);
        chkAgak.setBorderPaintedFlat(true);
        chkAgak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAgak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAgak.setName("chkAgak"); // NOI18N
        chkAgak.setOpaque(false);
        chkAgak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAgak);
        chkAgak.setBounds(257, 458, 35, 23);

        chkMengganggu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkMengganggu.setForeground(new java.awt.Color(0, 0, 0));
        chkMengganggu.setToolTipText("Mengganggu Aktivitas");
        chkMengganggu.setBorderPainted(true);
        chkMengganggu.setBorderPaintedFlat(true);
        chkMengganggu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkMengganggu.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMengganggu.setName("chkMengganggu"); // NOI18N
        chkMengganggu.setOpaque(false);
        chkMengganggu.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMengganggu);
        chkMengganggu.setBounds(343, 458, 35, 23);

        chkSangat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkSangat.setForeground(new java.awt.Color(0, 0, 0));
        chkSangat.setToolTipText("Sangat Mengganggu");
        chkSangat.setBorderPainted(true);
        chkSangat.setBorderPaintedFlat(true);
        chkSangat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSangat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSangat.setName("chkSangat"); // NOI18N
        chkSangat.setOpaque(false);
        chkSangat.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSangat);
        chkSangat.setBounds(430, 458, 35, 23);

        chkTak.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        chkTak.setForeground(new java.awt.Color(0, 0, 0));
        chkTak.setToolTipText("Tak Tertahankan");
        chkTak.setBorderPainted(true);
        chkTak.setBorderPaintedFlat(true);
        chkTak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkTak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTak.setName("chkTak"); // NOI18N
        chkTak.setOpaque(false);
        chkTak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTak);
        chkTak.setBounds(516, 458, 35, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Drain :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 761, 140, 23);

        cmbDrain.setBackground(new java.awt.Color(245, 253, 240));
        cmbDrain.setForeground(new java.awt.Color(0, 0, 0));
        cmbDrain.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDrain.setLightWeightPopupEnabled(false);
        cmbDrain.setName("cmbDrain"); // NOI18N
        FormInput.add(cmbDrain);
        cmbDrain.setBounds(145, 761, 60, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("NGT :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(210, 761, 40, 23);

        cmbNgt.setBackground(new java.awt.Color(245, 253, 240));
        cmbNgt.setForeground(new java.awt.Color(0, 0, 0));
        cmbNgt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNgt.setLightWeightPopupEnabled(false);
        cmbNgt.setName("cmbNgt"); // NOI18N
        FormInput.add(cmbNgt);
        cmbNgt.setBounds(257, 761, 60, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("DC :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(320, 761, 40, 23);

        cmbDc.setBackground(new java.awt.Color(245, 253, 240));
        cmbDc.setForeground(new java.awt.Color(0, 0, 0));
        cmbDc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDc.setLightWeightPopupEnabled(false);
        cmbDc.setName("cmbDc"); // NOI18N
        FormInput.add(cmbDc);
        cmbDc.setBounds(366, 761, 60, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Irigasi :");
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(430, 761, 50, 23);

        cmbIrigasi.setBackground(new java.awt.Color(245, 253, 240));
        cmbIrigasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbIrigasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbIrigasi.setLightWeightPopupEnabled(false);
        cmbIrigasi.setName("cmbIrigasi"); // NOI18N
        FormInput.add(cmbIrigasi);
        cmbIrigasi.setBounds(485, 761, 60, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("d. General Anstesi Dengan Nilai Total Aldrete Score :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(0, 789, 280, 23);

        TtotAldret.setForeground(new java.awt.Color(0, 0, 0));
        TtotAldret.setName("TtotAldret"); // NOI18N
        TtotAldret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotAldretKeyPressed(evt);
            }
        });
        FormInput.add(TtotAldret);
        TtotAldret.setBounds(287, 789, 60, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("e. General Anstesi Dengan Nilai Total Bromage Scale :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 817, 280, 23);

        TtotBromag.setForeground(new java.awt.Color(0, 0, 0));
        TtotBromag.setName("TtotBromag"); // NOI18N
        TtotBromag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotBromagKeyPressed(evt);
            }
        });
        FormInput.add(TtotBromag);
        TtotBromag.setBounds(287, 817, 60, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Program Terapi Yang Telah Diberikan :");
        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 845, 250, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("a. Cairan Infuse : 1.");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 873, 140, 23);

        TcairanInfus1.setForeground(new java.awt.Color(0, 0, 0));
        TcairanInfus1.setName("TcairanInfus1"); // NOI18N
        TcairanInfus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcairanInfus1KeyPressed(evt);
            }
        });
        FormInput.add(TcairanInfus1);
        TcairanInfus1.setBounds(145, 873, 220, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("2.");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 901, 140, 23);

        TcairanInfus2.setForeground(new java.awt.Color(0, 0, 0));
        TcairanInfus2.setName("TcairanInfus2"); // NOI18N
        TcairanInfus2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcairanInfus2KeyPressed(evt);
            }
        });
        FormInput.add(TcairanInfus2);
        TcairanInfus2.setBounds(145, 901, 220, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Jumlah :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(365, 873, 60, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Jumlah :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(365, 901, 60, 23);

        TjmlInfus1.setForeground(new java.awt.Color(0, 0, 0));
        TjmlInfus1.setName("TjmlInfus1"); // NOI18N
        TjmlInfus1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlInfus1KeyPressed(evt);
            }
        });
        FormInput.add(TjmlInfus1);
        TjmlInfus1.setBounds(430, 873, 80, 23);

        TjmlInfus2.setForeground(new java.awt.Color(0, 0, 0));
        TjmlInfus2.setName("TjmlInfus2"); // NOI18N
        TjmlInfus2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlInfus2KeyPressed(evt);
            }
        });
        FormInput.add(TjmlInfus2);
        TjmlInfus2.setBounds(430, 901, 80, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel108.setText("CC.");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(515, 873, 30, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("CC.");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(515, 901, 30, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(598, 901, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(650, 901, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(701, 901, 45, 23);

        jLabel282.setForeground(new java.awt.Color(0, 0, 0));
        jLabel282.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel282.setText("Wita");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(753, 901, 50, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("b. Transfusi : 1.");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 929, 140, 23);

        Ttranfusi1.setForeground(new java.awt.Color(0, 0, 0));
        Ttranfusi1.setName("Ttranfusi1"); // NOI18N
        Ttranfusi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttranfusi1KeyPressed(evt);
            }
        });
        FormInput.add(Ttranfusi1);
        Ttranfusi1.setBounds(145, 929, 440, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("Jumlah :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(586, 929, 50, 23);

        TjmlTranfusi1.setForeground(new java.awt.Color(0, 0, 0));
        TjmlTranfusi1.setName("TjmlTranfusi1"); // NOI18N
        TjmlTranfusi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlTranfusi1KeyPressed(evt);
            }
        });
        FormInput.add(TjmlTranfusi1);
        TjmlTranfusi1.setBounds(642, 929, 80, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel111.setText("Kalf");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(730, 929, 30, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("2.");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 957, 140, 23);

        Ttranfusi2.setForeground(new java.awt.Color(0, 0, 0));
        Ttranfusi2.setName("Ttranfusi2"); // NOI18N
        Ttranfusi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttranfusi2KeyPressed(evt);
            }
        });
        FormInput.add(Ttranfusi2);
        Ttranfusi2.setBounds(145, 957, 440, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Jumlah :");
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(586, 957, 50, 23);

        TjmlTranfusi2.setForeground(new java.awt.Color(0, 0, 0));
        TjmlTranfusi2.setName("TjmlTranfusi2"); // NOI18N
        TjmlTranfusi2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlTranfusi2KeyPressed(evt);
            }
        });
        FormInput.add(TjmlTranfusi2);
        TjmlTranfusi2.setBounds(642, 957, 80, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel113.setText("Kalf");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(730, 957, 30, 23);

        chkJamCairan1.setBackground(new java.awt.Color(255, 255, 250));
        chkJamCairan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJamCairan1.setForeground(new java.awt.Color(0, 0, 0));
        chkJamCairan1.setText("Jam :");
        chkJamCairan1.setBorderPainted(true);
        chkJamCairan1.setBorderPaintedFlat(true);
        chkJamCairan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJamCairan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJamCairan1.setName("chkJamCairan1"); // NOI18N
        chkJamCairan1.setOpaque(false);
        chkJamCairan1.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJamCairan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJamCairan1ActionPerformed(evt);
            }
        });
        FormInput.add(chkJamCairan1);
        chkJamCairan1.setBounds(545, 873, 50, 23);

        chkJamCairan2.setBackground(new java.awt.Color(255, 255, 250));
        chkJamCairan2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJamCairan2.setForeground(new java.awt.Color(0, 0, 0));
        chkJamCairan2.setText("Jam :");
        chkJamCairan2.setBorderPainted(true);
        chkJamCairan2.setBorderPaintedFlat(true);
        chkJamCairan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJamCairan2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJamCairan2.setName("chkJamCairan2"); // NOI18N
        chkJamCairan2.setOpaque(false);
        chkJamCairan2.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJamCairan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJamCairan2ActionPerformed(evt);
            }
        });
        FormInput.add(chkJamCairan2);
        chkJamCairan2.setBounds(545, 901, 50, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("c. Antibiotik :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 985, 140, 23);

        Tantibiotik.setForeground(new java.awt.Color(0, 0, 0));
        Tantibiotik.setName("Tantibiotik"); // NOI18N
        Tantibiotik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantibiotikKeyPressed(evt);
            }
        });
        FormInput.add(Tantibiotik);
        Tantibiotik.setBounds(145, 985, 390, 23);

        chkJamAntibiotik.setBackground(new java.awt.Color(255, 255, 250));
        chkJamAntibiotik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJamAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        chkJamAntibiotik.setText("Jam :");
        chkJamAntibiotik.setBorderPainted(true);
        chkJamAntibiotik.setBorderPaintedFlat(true);
        chkJamAntibiotik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJamAntibiotik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJamAntibiotik.setName("chkJamAntibiotik"); // NOI18N
        chkJamAntibiotik.setOpaque(false);
        chkJamAntibiotik.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJamAntibiotik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJamAntibiotikActionPerformed(evt);
            }
        });
        FormInput.add(chkJamAntibiotik);
        chkJamAntibiotik.setBounds(545, 985, 50, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam4);
        cmbJam4.setBounds(598, 985, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt4);
        cmbMnt4.setBounds(650, 985, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk4);
        cmbDtk4.setBounds(701, 985, 45, 23);

        jLabel284.setForeground(new java.awt.Color(0, 0, 0));
        jLabel284.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel284.setText("Wita");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(753, 985, 50, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("d. Analgesik :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(0, 1013, 140, 23);

        Tanalgesik.setForeground(new java.awt.Color(0, 0, 0));
        Tanalgesik.setName("Tanalgesik"); // NOI18N
        Tanalgesik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanalgesikKeyPressed(evt);
            }
        });
        FormInput.add(Tanalgesik);
        Tanalgesik.setBounds(145, 1013, 390, 23);

        chkJamAnalgesik.setBackground(new java.awt.Color(255, 255, 250));
        chkJamAnalgesik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkJamAnalgesik.setForeground(new java.awt.Color(0, 0, 0));
        chkJamAnalgesik.setText("Jam :");
        chkJamAnalgesik.setBorderPainted(true);
        chkJamAnalgesik.setBorderPaintedFlat(true);
        chkJamAnalgesik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkJamAnalgesik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkJamAnalgesik.setName("chkJamAnalgesik"); // NOI18N
        chkJamAnalgesik.setOpaque(false);
        chkJamAnalgesik.setPreferredSize(new java.awt.Dimension(175, 23));
        chkJamAnalgesik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkJamAnalgesikActionPerformed(evt);
            }
        });
        FormInput.add(chkJamAnalgesik);
        chkJamAnalgesik.setBounds(545, 1013, 50, 23);

        cmbJam5.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam5.setName("cmbJam5"); // NOI18N
        cmbJam5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam5MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam5);
        cmbJam5.setBounds(598, 1013, 45, 23);

        cmbMnt5.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt5.setName("cmbMnt5"); // NOI18N
        cmbMnt5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt5MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt5);
        cmbMnt5.setBounds(650, 1013, 45, 23);

        cmbDtk5.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk5.setName("cmbDtk5"); // NOI18N
        cmbDtk5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk5MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk5);
        cmbDtk5.setBounds(701, 1013, 45, 23);

        jLabel285.setForeground(new java.awt.Color(0, 0, 0));
        jLabel285.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel285.setText("Wita");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(753, 1013, 50, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("e. Obat-obat Lain :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(0, 1041, 140, 23);

        TobatLain.setForeground(new java.awt.Color(0, 0, 0));
        TobatLain.setName("TobatLain"); // NOI18N
        TobatLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatLainKeyPressed(evt);
            }
        });
        FormInput.add(TobatLain);
        TobatLain.setBounds(145, 1041, 600, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("Advis Selanjutnya Di Ruangan :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1069, 190, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseReleased(evt);
            }
        });
        FormInput.add(cmbRuangan);
        cmbRuangan.setBounds(197, 1069, 170, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("Pengelolaan Spesimen :");
        jLabel118.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1097, 180, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("a. Jenis Jaringan :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 1125, 140, 23);

        TjnsJaringan.setForeground(new java.awt.Color(0, 0, 0));
        TjnsJaringan.setName("TjnsJaringan"); // NOI18N
        TjnsJaringan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjnsJaringanKeyPressed(evt);
            }
        });
        FormInput.add(TjnsJaringan);
        TjnsJaringan.setBounds(145, 1125, 600, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("b. Pemeriksaan PA :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 1153, 140, 23);

        cmbPemeriksaanPA.setBackground(new java.awt.Color(245, 253, 240));
        cmbPemeriksaanPA.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemeriksaanPA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPemeriksaanPA.setLightWeightPopupEnabled(false);
        cmbPemeriksaanPA.setName("cmbPemeriksaanPA"); // NOI18N
        FormInput.add(cmbPemeriksaanPA);
        cmbPemeriksaanPA.setBounds(145, 1153, 60, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("c. Pemeriksaan Kultur :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(210, 1153, 130, 23);

        cmbPemeriksaanKul.setBackground(new java.awt.Color(245, 253, 240));
        cmbPemeriksaanKul.setForeground(new java.awt.Color(0, 0, 0));
        cmbPemeriksaanKul.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbPemeriksaanKul.setLightWeightPopupEnabled(false);
        cmbPemeriksaanKul.setName("cmbPemeriksaanKul"); // NOI18N
        FormInput.add(cmbPemeriksaanKul);
        cmbPemeriksaanKul.setBounds(347, 1153, 60, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("d. Jaringan Dibawakan :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(410, 1153, 140, 23);

        cmbJaringanDibawkan.setBackground(new java.awt.Color(245, 253, 240));
        cmbJaringanDibawkan.setForeground(new java.awt.Color(0, 0, 0));
        cmbJaringanDibawkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbJaringanDibawkan.setLightWeightPopupEnabled(false);
        cmbJaringanDibawkan.setName("cmbJaringanDibawkan"); // NOI18N
        FormInput.add(cmbJaringanDibawkan);
        cmbJaringanDibawkan.setBounds(558, 1153, 60, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("ASESMEN RESIKO JATUH :");
        jLabel123.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 1181, 180, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Faktor Resiko :");
        jLabel125.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 1237, 140, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("Riwayat Jatuh :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(145, 1237, 100, 23);

        cmbRiwJatuh.setBackground(new java.awt.Color(245, 253, 240));
        cmbRiwJatuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwJatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< dari 3 bulan", "Tidak ada atau >= 3 bulan" }));
        cmbRiwJatuh.setLightWeightPopupEnabled(false);
        cmbRiwJatuh.setName("cmbRiwJatuh"); // NOI18N
        cmbRiwJatuh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRiwJatuhActionPerformed(evt);
            }
        });
        FormInput.add(cmbRiwJatuh);
        cmbRiwJatuh.setBounds(253, 1237, 160, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Kondisi Kesehatan :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(85, 1265, 160, 23);

        cmbKondisi.setBackground(new java.awt.Color(245, 253, 240));
        cmbKondisi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKondisi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", ">= diagnosa penyakit", "1 diagnosa penyakit" }));
        cmbKondisi.setLightWeightPopupEnabled(false);
        cmbKondisi.setName("cmbKondisi"); // NOI18N
        cmbKondisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKondisiActionPerformed(evt);
            }
        });
        FormInput.add(cmbKondisi);
        cmbKondisi.setBounds(253, 1265, 140, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("Alat Bantu :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(85, 1293, 160, 23);

        cmbAlat.setBackground(new java.awt.Color(245, 253, 240));
        cmbAlat.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Berpegangan pada perabot", "Tongkat / alat penopang", "Tidak ada  / kursi roda / tirah baring" }));
        cmbAlat.setLightWeightPopupEnabled(false);
        cmbAlat.setName("cmbAlat"); // NOI18N
        cmbAlat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAlatActionPerformed(evt);
            }
        });
        FormInput.add(cmbAlat);
        cmbAlat.setBounds(253, 1293, 205, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Terpasang Infus / Terapi IV :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(85, 1321, 160, 23);

        cmbTerpasang.setBackground(new java.awt.Color(245, 253, 240));
        cmbTerpasang.setForeground(new java.awt.Color(0, 0, 0));
        cmbTerpasang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Terapi IV terus menerus", "Tidak" }));
        cmbTerpasang.setLightWeightPopupEnabled(false);
        cmbTerpasang.setName("cmbTerpasang"); // NOI18N
        cmbTerpasang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTerpasangActionPerformed(evt);
            }
        });
        FormInput.add(cmbTerpasang);
        cmbTerpasang.setBounds(253, 1321, 150, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Gaya Berjalan :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(85, 1349, 160, 23);

        cmbGaya.setBackground(new java.awt.Color(245, 253, 240));
        cmbGaya.setForeground(new java.awt.Color(0, 0, 0));
        cmbGaya.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kerusakan / terganggu", "Lemah", "Normal / tirah baring / Immobilisasi" }));
        cmbGaya.setLightWeightPopupEnabled(false);
        cmbGaya.setName("cmbGaya"); // NOI18N
        cmbGaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGayaActionPerformed(evt);
            }
        });
        FormInput.add(cmbGaya);
        cmbGaya.setBounds(253, 1349, 200, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Status Mental :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(85, 1377, 160, 23);

        cmbStatus.setBackground(new java.awt.Color(245, 253, 240));
        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lupa keterbatasan yang dimiliki", "Sadar kemampuan diri sendiri" }));
        cmbStatus.setLightWeightPopupEnabled(false);
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbStatusActionPerformed(evt);
            }
        });
        FormInput.add(cmbStatus);
        cmbStatus.setBounds(253, 1377, 185, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Skor :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(470, 1237, 40, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Skor :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(470, 1265, 40, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Skor :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(470, 1293, 40, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Skor :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(470, 1321, 40, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Skor :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(470, 1349, 40, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Skor :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(470, 1377, 40, 23);

        TskorRiw.setEditable(false);
        TskorRiw.setForeground(new java.awt.Color(0, 0, 0));
        TskorRiw.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorRiw.setName("TskorRiw"); // NOI18N
        TskorRiw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorRiwKeyPressed(evt);
            }
        });
        FormInput.add(TskorRiw);
        TskorRiw.setBounds(515, 1237, 40, 23);

        TskorKon.setEditable(false);
        TskorKon.setForeground(new java.awt.Color(0, 0, 0));
        TskorKon.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKon.setName("TskorKon"); // NOI18N
        TskorKon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorKonKeyPressed(evt);
            }
        });
        FormInput.add(TskorKon);
        TskorKon.setBounds(515, 1265, 40, 23);

        TskorAlat.setEditable(false);
        TskorAlat.setForeground(new java.awt.Color(0, 0, 0));
        TskorAlat.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorAlat.setName("TskorAlat"); // NOI18N
        TskorAlat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorAlatKeyPressed(evt);
            }
        });
        FormInput.add(TskorAlat);
        TskorAlat.setBounds(515, 1293, 40, 23);

        TskorTerpasang.setEditable(false);
        TskorTerpasang.setForeground(new java.awt.Color(0, 0, 0));
        TskorTerpasang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTerpasang.setName("TskorTerpasang"); // NOI18N
        TskorTerpasang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorTerpasangKeyPressed(evt);
            }
        });
        FormInput.add(TskorTerpasang);
        TskorTerpasang.setBounds(515, 1321, 40, 23);

        TskorGaya.setEditable(false);
        TskorGaya.setForeground(new java.awt.Color(0, 0, 0));
        TskorGaya.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGaya.setName("TskorGaya"); // NOI18N
        TskorGaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorGayaKeyPressed(evt);
            }
        });
        FormInput.add(TskorGaya);
        TskorGaya.setBounds(515, 1349, 40, 23);

        TskorStatus.setEditable(false);
        TskorStatus.setForeground(new java.awt.Color(0, 0, 0));
        TskorStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorStatus.setName("TskorStatus"); // NOI18N
        TskorStatus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorStatusKeyPressed(evt);
            }
        });
        FormInput.add(TskorStatus);
        TskorStatus.setBounds(515, 1377, 40, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Total Skor :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(560, 1377, 70, 23);

        TtotSkorDewasa.setEditable(false);
        TtotSkorDewasa.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkorDewasa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotSkorDewasa.setName("TtotSkorDewasa"); // NOI18N
        TtotSkorDewasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotSkorDewasaKeyPressed(evt);
            }
        });
        FormInput.add(TtotSkorDewasa);
        TtotSkorDewasa.setBounds(635, 1377, 40, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Kesimpulan :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(85, 1405, 160, 23);

        TkesDewasa.setEditable(false);
        TkesDewasa.setForeground(new java.awt.Color(0, 0, 0));
        TkesDewasa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TkesDewasa.setName("TkesDewasa"); // NOI18N
        TkesDewasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesDewasaKeyPressed(evt);
            }
        });
        FormInput.add(TkesDewasa);
        TkesDewasa.setBounds(255, 1405, 350, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Faktor Resiko :");
        jLabel141.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(0, 1461, 140, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Usia :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(145, 1461, 100, 23);

        cmbUsia.setBackground(new java.awt.Color(245, 253, 240));
        cmbUsia.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 3 tahun", "3 - 7 tahun", "7 - 13 tahun", ">= 13 tahun" }));
        cmbUsia.setLightWeightPopupEnabled(false);
        cmbUsia.setName("cmbUsia"); // NOI18N
        cmbUsia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsiaActionPerformed(evt);
            }
        });
        FormInput.add(cmbUsia);
        cmbUsia.setBounds(253, 1461, 95, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Skor :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(605, 1461, 40, 23);

        TskorUsia.setEditable(false);
        TskorUsia.setForeground(new java.awt.Color(0, 0, 0));
        TskorUsia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorUsia.setName("TskorUsia"); // NOI18N
        TskorUsia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorUsiaKeyPressed(evt);
            }
        });
        FormInput.add(TskorUsia);
        TskorUsia.setBounds(650, 1461, 40, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Jenis Kelamin :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(145, 1489, 100, 23);

        cmbJenkel.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenkel.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenkel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Laki-laki", "Perempuan" }));
        cmbJenkel.setLightWeightPopupEnabled(false);
        cmbJenkel.setName("cmbJenkel"); // NOI18N
        cmbJenkel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJenkelActionPerformed(evt);
            }
        });
        FormInput.add(cmbJenkel);
        cmbJenkel.setBounds(253, 1489, 90, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Skor :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(605, 1489, 40, 23);

        TskorJenkel.setEditable(false);
        TskorJenkel.setForeground(new java.awt.Color(0, 0, 0));
        TskorJenkel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorJenkel.setName("TskorJenkel"); // NOI18N
        TskorJenkel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorJenkelKeyPressed(evt);
            }
        });
        FormInput.add(TskorJenkel);
        TskorJenkel.setBounds(650, 1489, 40, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Diagnosis :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(145, 1517, 100, 23);

        cmbDiagnosis.setBackground(new java.awt.Color(245, 253, 240));
        cmbDiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        cmbDiagnosis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dx Neurologis", "Perubahan oksigenasi", "Gangguan perilaku", "Dx Lainnya" }));
        cmbDiagnosis.setLightWeightPopupEnabled(false);
        cmbDiagnosis.setName("cmbDiagnosis"); // NOI18N
        cmbDiagnosis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDiagnosisActionPerformed(evt);
            }
        });
        FormInput.add(cmbDiagnosis);
        cmbDiagnosis.setBounds(253, 1517, 140, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Skor :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(605, 1517, 40, 23);

        TskorDiag.setEditable(false);
        TskorDiag.setForeground(new java.awt.Color(0, 0, 0));
        TskorDiag.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorDiag.setName("TskorDiag"); // NOI18N
        TskorDiag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorDiagKeyPressed(evt);
            }
        });
        FormInput.add(TskorDiag);
        TskorDiag.setBounds(650, 1517, 40, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Gangguan Kognitif :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(125, 1545, 120, 23);

        cmbGang.setBackground(new java.awt.Color(245, 253, 240));
        cmbGang.setForeground(new java.awt.Color(0, 0, 0));
        cmbGang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak menyadari", "Lupa akan keterbatasan", "Orientasi diri baik" }));
        cmbGang.setLightWeightPopupEnabled(false);
        cmbGang.setName("cmbGang"); // NOI18N
        cmbGang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGangActionPerformed(evt);
            }
        });
        FormInput.add(cmbGang);
        cmbGang.setBounds(253, 1545, 150, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Skor :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(605, 1545, 40, 23);

        TskorGang.setEditable(false);
        TskorGang.setForeground(new java.awt.Color(0, 0, 0));
        TskorGang.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorGang.setName("TskorGang"); // NOI18N
        TskorGang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorGangKeyPressed(evt);
            }
        });
        FormInput.add(TskorGang);
        TskorGang.setBounds(650, 1545, 40, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Respon Terhadap Pembedahan / Sedasi / :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(5, 1573, 240, 23);

        cmbResTerhadap.setBackground(new java.awt.Color(245, 253, 240));
        cmbResTerhadap.setForeground(new java.awt.Color(0, 0, 0));
        cmbResTerhadap.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dalam 24 jam", "Dalam 48 jam", "> 48 jam. Tidak ada pembedahan/sedasi/anestesi" }));
        cmbResTerhadap.setLightWeightPopupEnabled(false);
        cmbResTerhadap.setName("cmbResTerhadap"); // NOI18N
        cmbResTerhadap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbResTerhadapActionPerformed(evt);
            }
        });
        FormInput.add(cmbResTerhadap);
        cmbResTerhadap.setBounds(253, 1573, 275, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Skor :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(605, 1573, 40, 23);

        TskorResTerhadap.setEditable(false);
        TskorResTerhadap.setForeground(new java.awt.Color(0, 0, 0));
        TskorResTerhadap.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorResTerhadap.setName("TskorResTerhadap"); // NOI18N
        TskorResTerhadap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorResTerhadapKeyPressed(evt);
            }
        });
        FormInput.add(TskorResTerhadap);
        TskorResTerhadap.setBounds(650, 1573, 40, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Anestesi  ");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(55, 1588, 190, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Penggunaan Medikamentosa :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(55, 1616, 190, 23);

        cmbPenggu.setBackground(new java.awt.Color(245, 253, 240));
        cmbPenggu.setForeground(new java.awt.Color(0, 0, 0));
        cmbPenggu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Penggunaan multiple aditive/barbiturate, fenotiazin, antidepresan", "Penggunaan medikasi lainnya/tidak ada medikasi" }));
        cmbPenggu.setLightWeightPopupEnabled(false);
        cmbPenggu.setName("cmbPenggu"); // NOI18N
        cmbPenggu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPengguActionPerformed(evt);
            }
        });
        FormInput.add(cmbPenggu);
        cmbPenggu.setBounds(253, 1616, 350, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Skor :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(605, 1616, 40, 23);

        TskorPenggu.setEditable(false);
        TskorPenggu.setForeground(new java.awt.Color(0, 0, 0));
        TskorPenggu.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorPenggu.setName("TskorPenggu"); // NOI18N
        TskorPenggu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorPengguKeyPressed(evt);
            }
        });
        FormInput.add(TskorPenggu);
        TskorPenggu.setBounds(650, 1616, 40, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Faktor Lingkungan :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(55, 1644, 190, 23);

        cmbFaktor.setBackground(new java.awt.Color(245, 253, 240));
        cmbFaktor.setForeground(new java.awt.Color(0, 0, 0));
        cmbFaktor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Riwayat jatuh/bayi di TT dewasa", "Px menggunakan alat bantu/bayi di TT bayi", "Px diletakkan di TT", "Area di luar RS" }));
        cmbFaktor.setLightWeightPopupEnabled(false);
        cmbFaktor.setName("cmbFaktor"); // NOI18N
        cmbFaktor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFaktorActionPerformed(evt);
            }
        });
        FormInput.add(cmbFaktor);
        cmbFaktor.setBounds(253, 1644, 240, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Skor :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(605, 1644, 40, 23);

        TskorFaktor.setEditable(false);
        TskorFaktor.setForeground(new java.awt.Color(0, 0, 0));
        TskorFaktor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorFaktor.setName("TskorFaktor"); // NOI18N
        TskorFaktor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorFaktorKeyPressed(evt);
            }
        });
        FormInput.add(TskorFaktor);
        TskorFaktor.setBounds(650, 1644, 40, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("Total Skor :");
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(695, 1644, 60, 23);

        TtotSkorAnak.setEditable(false);
        TtotSkorAnak.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkorAnak.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TtotSkorAnak.setName("TtotSkorAnak"); // NOI18N
        TtotSkorAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtotSkorAnakKeyPressed(evt);
            }
        });
        FormInput.add(TtotSkorAnak);
        TtotSkorAnak.setBounds(760, 1644, 40, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Kesimpulan :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(85, 1672, 160, 23);

        TkesAnak.setEditable(false);
        TkesAnak.setForeground(new java.awt.Color(0, 0, 0));
        TkesAnak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        TkesAnak.setName("TkesAnak"); // NOI18N
        TkesAnak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesAnakKeyPressed(evt);
            }
        });
        FormInput.add(TkesAnak);
        TkesAnak.setBounds(255, 1672, 350, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("Serah Terima Dilakukan Di Ruang Pulih (RR) Pada Tanggal :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(0, 1700, 310, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("Nama Keluarga Pasien :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(0, 1728, 140, 23);

        TnmKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluarga.setName("TnmKeluarga"); // NOI18N
        TnmKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(TnmKeluarga);
        TnmKeluarga.setBounds(145, 1728, 410, 23);

        chkDewasa.setBackground(new java.awt.Color(255, 255, 250));
        chkDewasa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkDewasa);
        chkDewasa.setForeground(new java.awt.Color(0, 0, 0));
        chkDewasa.setText("Dewasa (Skala Morse)");
        chkDewasa.setBorderPainted(true);
        chkDewasa.setBorderPaintedFlat(true);
        chkDewasa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkDewasa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkDewasa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDewasa.setName("chkDewasa"); // NOI18N
        chkDewasa.setOpaque(false);
        chkDewasa.setPreferredSize(new java.awt.Dimension(175, 23));
        chkDewasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkDewasaActionPerformed(evt);
            }
        });
        FormInput.add(chkDewasa);
        chkDewasa.setBounds(0, 1209, 180, 23);

        chkAnak.setBackground(new java.awt.Color(255, 255, 250));
        chkAnak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        buttonGroup1.add(chkAnak);
        chkAnak.setForeground(new java.awt.Color(0, 0, 0));
        chkAnak.setText("Anak (Skala Humpty Dumpty)");
        chkAnak.setBorderPainted(true);
        chkAnak.setBorderPaintedFlat(true);
        chkAnak.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkAnak.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        chkAnak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnak.setName("chkAnak"); // NOI18N
        chkAnak.setOpaque(false);
        chkAnak.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAnak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAnakActionPerformed(evt);
            }
        });
        FormInput.add(chkAnak);
        chkAnak.setBounds(0, 1433, 220, 23);

        chkTopikal.setBackground(new java.awt.Color(255, 255, 250));
        chkTopikal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTopikal.setForeground(new java.awt.Color(0, 0, 0));
        chkTopikal.setText("Topikal");
        chkTopikal.setBorderPainted(true);
        chkTopikal.setBorderPaintedFlat(true);
        chkTopikal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTopikal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTopikal.setName("chkTopikal"); // NOI18N
        chkTopikal.setOpaque(false);
        chkTopikal.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkTopikal);
        chkTopikal.setBounds(290, 206, 70, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Serah Terima Pasien Pasca Operasi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass13.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbSerah.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbSerah.setComponentPopupMenu(jPopupMenu2);
        tbSerah.setName("tbSerah"); // NOI18N
        tbSerah.getTableHeader().setReorderingAllowed(false);
        tbSerah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSerahMouseClicked(evt);
            }
        });
        tbSerah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSerahKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSerah);

        panelGlass13.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass14.setName("panelGlass14"); // NOI18N
        panelGlass14.setPreferredSize(new java.awt.Dimension(282, 44));
        panelGlass14.setLayout(null);

        panelGlass15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass15.setName("panelGlass15"); // NOI18N
        panelGlass15.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane3.setViewportView(gambarQR);

        panelGlass15.add(scrollPane3);

        panelGlass14.add(panelGlass15);
        panelGlass15.setBounds(12, 10, 230, 245);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel82.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel82.setName("jLabel82"); // NOI18N
        jLabel82.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelGlass14.add(jLabel82);
        jLabel82.setBounds(20, 262, 210, 60);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: TANDA TANGAN :.", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass14.add(Scroll5);
        Scroll5.setBounds(12, 335, 260, 240);

        panelGlass13.add(panelGlass14, java.awt.BorderLayout.EAST);

        PanelInput1.add(panelGlass13, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Serah Terima :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "15-07-2026" }));
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

        panelGlass9.add(PanelInput1);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("serah_terima_pasien_pasca_operasi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?"
                    + ",?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 85, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), Tdiagnosa.getText(), Ttindakan.getText(), nipDrOperator, ett, lma, fima, tiva, spinal, epidural,
                        cse, infil, blok, ringan, sedang, dalam, nipDrAnes, Valid.SetTgl(TtglPindah.getSelectedItem() + ""),
                        cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Ttd.getText(), Trr.getText(), Tnadi.getText(),
                        Ttemp.getText(), cmbMual.getSelectedItem().toString(), cmbRespon.getSelectedItem().toString(), tdkSakit, sedSakit, agak, menggang, sangat,
                        tak, cmbDrain.getSelectedItem().toString(), cmbNgt.getSelectedItem().toString(), cmbDc.getSelectedItem().toString(),
                        cmbIrigasi.getSelectedItem().toString(), TtotAldret.getText(), TtotBromag.getText(), TcairanInfus1.getText(), TjmlInfus1.getText(),
                        cekJamInfus1, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), TcairanInfus2.getText(),
                        TjmlInfus2.getText(), cekJamInfus2, cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                        Ttranfusi1.getText(), TjmlTranfusi1.getText(), Ttranfusi2.getText(), TjmlTranfusi2.getText(), Tantibiotik.getText(), cekJamAnti,
                        cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(), Tanalgesik.getText(), cekJamAnal,
                        cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(), TobatLain.getText(),
                        cmbRuangan.getSelectedItem().toString(), TjnsJaringan.getText(), cmbPemeriksaanPA.getSelectedItem().toString(),
                        cmbPemeriksaanKul.getSelectedItem().toString(), cmbJaringanDibawkan.getSelectedItem().toString(), asesDewasa, asesAnak, 
                        cmbRiwJatuh.getSelectedItem().toString(), cmbKondisi.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(), 
                        cmbTerpasang.getSelectedItem().toString(), cmbGaya.getSelectedItem().toString(), cmbStatus.getSelectedItem().toString(), cmbUsia.getSelectedItem().toString(), 
                        cmbJenkel.getSelectedItem().toString(), cmbDiagnosis.getSelectedItem().toString(), cmbGang.getSelectedItem().toString(), 
                        cmbResTerhadap.getSelectedItem().toString(), cmbPenggu.getSelectedItem().toString(), cmbFaktor.getSelectedItem().toString(), 
                        Valid.SetTgl(TtglSerah.getSelectedItem() + ""), cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(), 
                        TnmKeluarga.getText(), nipPrwtIbs, nipPrwtRuang, Sequel.cariIsi("select now()"), "", topikal
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Serah Terima Pasien Pasca Operasi", "Simpan");
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
            if (tbSerah.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("serah_terima_pasien_pasca_operasi", "waktu_simpan=?", "diagnosis_medis=?, tindakan_operasi=?, nip_dr_operator=?, ett=?, lma=?, "
                        + "fima=?, tiva=?, spinal=?, epidural=?, cse=?, infiltrasi=?, block=?, sedasi_ringan=?, sedasi_sedang=?, sedasi_dalam=?, nip_dr_anestesi=?, tgl_pindah=?, "
                        + "jam_pindah=?, td=?, rr=?, nadi=?, temp=?, mual_muntah=?, respon_nyeri=?, tidak_sakit=?, sedikit_sakit=?, agak_mengganggu=?, mengganggu_aktivitas=?, "
                        + "sangat_mengganggu=?, tidak_tertahankan=?, drain=?, ngt=?, dc=?, irigasi=?, total_aldret_skor=?, total_bromag_skor=?, cairan_infus1=?, jml_cairan_infus1=?, "
                        + "cek_jam_cairan1=?, jam_cairan_infus1=?, cairan_infus2=?, jml_cairan_infus2=?, cek_jam_cairan2=?, jam_cairan_infus2=?, transfusi1=?, jml_transfusi1=?, "
                        + "transfusi2=?, jml_transfusi2=?, antibiotik=?, cek_jam_antibiotik=?, jam_antibiotik=?, analgesik=?, cek_jam_analgesik=?, jam_analgesik=?, obat_lain=?, "
                        + "advis_diruangan=?, jenis_jaringan=?, pemeriksaan_pa=?, pemeriksaan_kultur=?, jaringan_dibawakan=?, asesmen_dewasa=?, asesmen_anak=?, arj_dewasa_riwayat_jatuh=?, "
                        + "arj_dewasa_kondisi_kesehatan=?, arj_dewasa_alat_bantu=?, arj_dewasa_terpasang_infus=?, arj_dewasa_gaya_berjalan=?, arj_dewasa_status_mental=?, arj_anak_usia=?, "
                        + "arj_anak_jenis_kelamin=?, arj_anak_diagnosis=?, arj_anak_gangguan_kognitif=?, arj_anak_respon=?, arj_anak_penggunaan_medikamentosa=?, arj_anak_faktor_lingkungan=?, "
                        + "tgl_serah=?, pukul_serah=?, nm_keluarga_pasien=?, nip_perawat_ibs=?, nip_perawat_ruang=?, topikal=?", 82, new String[]{
                            Tdiagnosa.getText(), Ttindakan.getText(), nipDrOperator, ett, lma, fima, tiva, spinal, epidural,
                            cse, infil, blok, ringan, sedang, dalam, nipDrAnes, Valid.SetTgl(TtglPindah.getSelectedItem() + ""),
                            cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(), Ttd.getText(), Trr.getText(), Tnadi.getText(),
                            Ttemp.getText(), cmbMual.getSelectedItem().toString(), cmbRespon.getSelectedItem().toString(), tdkSakit, sedSakit, agak, menggang, sangat,
                            tak, cmbDrain.getSelectedItem().toString(), cmbNgt.getSelectedItem().toString(), cmbDc.getSelectedItem().toString(),
                            cmbIrigasi.getSelectedItem().toString(), TtotAldret.getText(), TtotBromag.getText(), TcairanInfus1.getText(), TjmlInfus1.getText(),
                            cekJamInfus1, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), TcairanInfus2.getText(),
                            TjmlInfus2.getText(), cekJamInfus2, cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(),
                            Ttranfusi1.getText(), TjmlTranfusi1.getText(), Ttranfusi2.getText(), TjmlTranfusi2.getText(), Tantibiotik.getText(), cekJamAnti,
                            cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(), Tanalgesik.getText(), cekJamAnal,
                            cmbJam5.getSelectedItem() + ":" + cmbMnt5.getSelectedItem() + ":" + cmbDtk5.getSelectedItem(), TobatLain.getText(),
                            cmbRuangan.getSelectedItem().toString(), TjnsJaringan.getText(), cmbPemeriksaanPA.getSelectedItem().toString(),
                            cmbPemeriksaanKul.getSelectedItem().toString(), cmbJaringanDibawkan.getSelectedItem().toString(), asesDewasa, asesAnak,
                            cmbRiwJatuh.getSelectedItem().toString(), cmbKondisi.getSelectedItem().toString(), cmbAlat.getSelectedItem().toString(),
                            cmbTerpasang.getSelectedItem().toString(), cmbGaya.getSelectedItem().toString(), cmbStatus.getSelectedItem().toString(), cmbUsia.getSelectedItem().toString(),
                            cmbJenkel.getSelectedItem().toString(), cmbDiagnosis.getSelectedItem().toString(), cmbGang.getSelectedItem().toString(),
                            cmbResTerhadap.getSelectedItem().toString(), cmbPenggu.getSelectedItem().toString(), cmbFaktor.getSelectedItem().toString(),
                            Valid.SetTgl(TtglSerah.getSelectedItem() + ""), cmbJam6.getSelectedItem() + ":" + cmbMnt6.getSelectedItem() + ":" + cmbDtk6.getSelectedItem(),
                            TnmKeluarga.getText(), nipPrwtIbs, nipPrwtRuang, topikal,
                            tbSerah.getValueAt(tbSerah.getSelectedRow(), 91).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Serah Terima Pasien Pasca Operasi", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbSerah.requestFocus();
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
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        WindowNomorDokumenRM.dispose();
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
        ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
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

    private void tbSerahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSerahMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSerahMouseClicked

    private void tbSerahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSerahKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSerahKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSerah.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from serah_terima_pasien_pasca_operasi where waktu_simpan=?", 1, new String[]{
                    tbSerah.getValueAt(tbSerah.getSelectedRow(), 91).toString()
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
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbSerah.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbSerah.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            if (Tdiagnosa.getText().equals("")) {
                param.put("diagnosa", "..........");
            } else {
                param.put("diagnosa", Tdiagnosa.getText());
            }
            
            if (Ttindakan.getText().equals("")) {
                param.put("tindakan", "..........");
            } else {
                param.put("tindakan", Ttindakan.getText());
            }           
            
            param.put("dokterOper", TnmDrOperator.getText());
            
            if (chkEtt.isSelected() == true) {
                param.put("ett", "V");
            } else {
                param.put("ett", "");
            }
            
            if (chkLma.isSelected() == true) {
                param.put("lma", "V");
            } else {
                param.put("lma", "");
            }
            
            if (chkFima.isSelected() == true) {
                param.put("fima", "V");
            } else {
                param.put("fima", "");
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
                param.put("epid", "V");
            } else {
                param.put("epid", "");
            }
            
            if (chkCse.isSelected() == true) {
                param.put("cse", "V");
            } else {
                param.put("cse", "");
            }
            
            if (chkInfiltrasi.isSelected() == true) {
                param.put("infil", "V");
            } else {
                param.put("infil", "");
            }
            
            if (chkBlock.isSelected() == true) {
                param.put("blok", "V");
            } else {
                param.put("blok", "");
            }
            
            if (chkTopikal.isSelected() == true) {
                param.put("topikal", "V");
            } else {
                param.put("topikal", "");
            }
            
            if (chkRingan.isSelected() == true) {
                param.put("ringan", "V");
            } else {
                param.put("ringan", "");
            }
            
            if (chkSedang.isSelected() == true) {
                param.put("sedang", "V");
            } else {
                param.put("sedang", "");
            }
            
            if (chkDalam.isSelected() == true) {
                param.put("dalam", "V");
            } else {
                param.put("dalam", "");
            }           
            
            param.put("dokterAnes", TnmDrAnestesi.getText());
            param.put("tglPindah", Valid.SetTglINDONESIA(Valid.SetTgl(TtglPindah.getSelectedItem() + "")) + ", Pukul "
                    + cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita");
            
            if (Ttd.getText().equals("")) {
                param.put("td", "........ mmHg");
            } else {
                param.put("td", Ttd.getText() + " mmHg");
            }
            
            if (Trr.getText().equals("")) {
                param.put("rr", "........ x/menit");
            } else {
                param.put("rr", Trr.getText() + " x/menit");
            }
            
            if (Tnadi.getText().equals("")) {
                param.put("nadi", "........ x/menit");
            } else {
                param.put("nadi", Tnadi.getText() + " x/menit");
            }
            
            if (Ttemp.getText().equals("")) {
                param.put("temp", "........ °C");
            } else {
                param.put("temp", Ttemp.getText() + " °C");
            }         
            
            param.put("mual", cmbMual.getSelectedItem().toString());
            param.put("respon", cmbRespon.getSelectedItem().toString());
            
            if (chkTdkSakit.isSelected() == true) {
                param.put("tdksakit", "V");
            } else {
                param.put("tdksakit", "");
            }
            
            if (chkSedikitSakit.isSelected() == true) {
                param.put("sedsakit", "V");
            } else {
                param.put("sedsakit", "");
            }
            
            if (chkAgak.isSelected() == true) {
                param.put("agak", "V");
            } else {
                param.put("agak", "");
            }
            
            if (chkMengganggu.isSelected() == true) {
                param.put("mengg", "V");
            } else {
                param.put("mengg", "");
            }
            
            if (chkSangat.isSelected() == true) {
                param.put("sangat", "V");
            } else {
                param.put("sangat", "");
            }
            
            if (chkTak.isSelected() == true) {
                param.put("tak", "V");
            } else {
                param.put("tak", "");
            }
            
            param.put("drain", cmbDrain.getSelectedItem().toString());
            param.put("ngt", cmbNgt.getSelectedItem().toString());
            param.put("dc", cmbDc.getSelectedItem().toString());
            param.put("irigasi", cmbIrigasi.getSelectedItem().toString());
            
            if (TtotAldret.getText().equals("")) {
                param.put("totAldret", "........");
            } else {
                param.put("totAldret", TtotAldret.getText());
            }
            
            if (TtotBromag.getText().equals("")) {
                param.put("totBrom", "........");
            } else {
                param.put("totBrom", TtotBromag.getText());
            }
            
            //---------------------------------------------
            String cairan1 = "", jmlcairan1 = "", jamCairan1 = "", cairan2 = "", jmlcairan2 = "", jamCairan2 = "",
                    tranfusi1 = "", jmlTran1 = "", tranfusi2 = "", jmlTran2 = "", anti = "", jamAnti = "", anal = "", jamAnal = "";
            if (TcairanInfus1.getText().equals("")) {
                cairan1 = "........";
            } else {
                cairan1 = TcairanInfus1.getText();
            }
            
            if (TjmlInfus1.getText().equals("")) {
                jmlcairan1 = "........";
            } else {
                jmlcairan1 = TjmlInfus1.getText();
            }
            
            if (chkJamCairan1.isSelected() == true) {
                jamCairan1 = cmbJam2.getSelectedItem().toString() + ":" + cmbMnt2.getSelectedItem().toString();
            } else {
                jamCairan1 = "........";
            }
            
            param.put("cairanInfus1", ": 1. " + cairan1 + " Jumlah : " + jmlcairan1 + " CC, Jam : " + jamCairan1 + " Wita");
            
            if (TcairanInfus2.getText().equals("")) {
                cairan2 = "........";
            } else {
                cairan2 = TcairanInfus2.getText();
            }
            
            if (TjmlInfus2.getText().equals("")) {
                jmlcairan2 = "........";
            } else {
                jmlcairan2 = TjmlInfus2.getText();
            }
            
            if (chkJamCairan2.isSelected() == true) {
                jamCairan2 = cmbJam3.getSelectedItem().toString() + ":" + cmbMnt3.getSelectedItem().toString();
            } else {
                jamCairan2 = "........";
            }
            
            param.put("cairanInfus2", ": 2. " + cairan2 + " Jumlah : " + jmlcairan2 + " CC, Jam : " + jamCairan2 + " Wita");
            
            if (Ttranfusi1.getText().equals("")) {
                tranfusi1 = "........";
            } else {
                tranfusi1 = Ttranfusi1.getText();
            }
            
            if (TjmlTranfusi1.getText().equals("")) {
                jmlTran1 = "........";
            } else {
                jmlTran1 = TjmlTranfusi1.getText();
            }
            
            param.put("tranfus1", ": 1. " + tranfusi1 + " Jumlah : " + jmlTran1 + " KALF");
            
            if (Ttranfusi2.getText().equals("")) {
                tranfusi2 = "........";
            } else {
                tranfusi2 = Ttranfusi2.getText();
            }
            
            if (TjmlTranfusi2.getText().equals("")) {
                jmlTran2 = "........";
            } else {
                jmlTran2 = TjmlTranfusi2.getText();
            }
            
            param.put("tranfus2", ": 2. " + tranfusi2 + " Jumlah : " + jmlTran2 + " KALF");
            
            if (Tantibiotik.getText().equals("")) {
                anti = "........";
            } else {
                anti = Tantibiotik.getText();
            }
            
            if (chkJamAntibiotik.isSelected() == true) {
                jamAnti = cmbJam4.getSelectedItem().toString() + ":" + cmbMnt4.getSelectedItem().toString();
            } else {
                jamAnti = "........";
            }
            
            param.put("antibiotik", anti + " Jumlah : " + jamAnti + " Wita");
            
            if (Tanalgesik.getText().equals("")) {
                anal = "........";
            } else {
                anal = Tanalgesik.getText();
            }
            
            if (chkJamAnalgesik.isSelected() == true) {
                jamAnal = cmbJam5.getSelectedItem().toString() + ":" + cmbMnt5.getSelectedItem().toString();
            } else {
                jamAnal = "........";
            }
            
            param.put("analgesik", anal + " Jumlah : " + jamAnal + " Wita");
            
            if (TobatLain.getText().equals("")) {
                param.put("obatLain", "........");
            } else {
                param.put("obatLain", TobatLain.getText());
            }
            
            param.put("advisRuangan", cmbRuangan.getSelectedItem().toString());
            
            if (TjnsJaringan.getText().equals("")) {
                param.put("jnsJaring", "........");
            } else {
                param.put("jnsJaring", TjnsJaringan.getText());
            }
            
            param.put("periksaPA", cmbPemeriksaanPA.getSelectedItem().toString());
            param.put("periksaKul", cmbPemeriksaanKul.getSelectedItem().toString());
            param.put("jaringanBawa", cmbJaringanDibawkan.getSelectedItem().toString());
            
            param.put("riwJatuh", cmbRiwJatuh.getSelectedItem().toString());
            param.put("kondisi", cmbKondisi.getSelectedItem().toString());
            param.put("alat", cmbAlat.getSelectedItem().toString());
            param.put("terpasang", cmbTerpasang.getSelectedItem().toString());
            param.put("gaya", cmbGaya.getSelectedItem().toString());
            param.put("status", cmbStatus.getSelectedItem().toString());
            
            param.put("skorRiw", TskorRiw.getText());
            param.put("skorKon", TskorKon.getText());
            param.put("skorAla", TskorAlat.getText());
            param.put("skorTer", TskorTerpasang.getText());
            param.put("skorGay", TskorGaya.getText());
            param.put("skorSta", TskorStatus.getText());
            param.put("totSkorD", TtotSkorDewasa.getText());
            param.put("kesDewasa", TkesDewasa.getText());
            
            param.put("usia", cmbUsia.getSelectedItem().toString());
            param.put("jenkel", cmbJenkel.getSelectedItem().toString());
            param.put("diagnosis", cmbDiagnosis.getSelectedItem().toString());
            param.put("gangguan", cmbGang.getSelectedItem().toString());
            param.put("resTerhadap", cmbResTerhadap.getSelectedItem().toString());
            param.put("penggunaan", cmbPenggu.getSelectedItem().toString());
            param.put("faktor", cmbFaktor.getSelectedItem().toString());
            
            param.put("skorUsia", TskorUsia.getText());
            param.put("skorJK", TskorJenkel.getText());
            param.put("skorDiag", TskorDiag.getText());
            param.put("skorGang", TskorGang.getText());
            param.put("skorResTer", TskorResTerhadap.getText());
            param.put("skorPeng", TskorPenggu.getText());
            param.put("skorFak", TskorFaktor.getText());
            param.put("totSkorA", TtotSkorAnak.getText());
            param.put("kesAnak", TkesAnak.getText());

            param.put("tglSerah", Valid.SetTglINDONESIA(Valid.SetTgl(TtglSerah.getSelectedItem() + ""))
                    + ", Pukul " + cmbJam6.getSelectedItem().toString() + ":" + cmbMnt6.getSelectedItem().toString() + " Wita");
            
            if (TnmKeluarga.getText().equals("")) {
                param.put("nmKeluarga", "(..........................)");
            } else {
                param.put("nmKeluarga", "(" + TnmKeluarga.getText() + ")");
            }
            
            if (TnmPrwtIbs.getText().equals("")) {
                param.put("perawatIBS", "(..........................)");
            } else {
                param.put("perawatIBS", "(" + TnmPrwtIbs.getText() + ")");
            }
            
            if (TnmPrwtRuang.getText().equals("")) {
                param.put("perawatRuang", "(..........................)");
            } else {
                param.put("perawatRuang", "(" + TnmPrwtRuang.getText() + ")");
            }
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiIBS = "", isiRuangan = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from serah_terima_pasien_pasca_operasi "
                        + "where waktu_simpan='" + tbSerah.getValueAt(tbSerah.getSelectedRow(), 91).toString() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from serah_terima_pasien_pasca_operasi "
                        + "where waktu_simpan='" + tbSerah.getValueAt(tbSerah.getSelectedRow(), 91).toString() + "'");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));

                try {
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
                
                //perawat IBS
                if (nipPrwtIbs.equals("") || nipPrwtIbs.equals("-") || nipPrwtIbs.equals("--")) {
                    param.put("lokasiQrIBS", "");
                } else {
                    isiIBS = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Serah Terima Pasien Pasca Operasi", TnmPrwtIbs.getText() + " (Perawat IBS)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiIBS, Sequel.cariFolderTte(), "QRTtePerawatIbs.jpg", "select logo from setting");
                    param.put("lokasiQrIBS", Sequel.cariFolderTte() + File.separator + "QRTtePerawatIbs.jpg");
                }
                
                //perawat Ruang
                if (nipPrwtRuang.equals("") || nipPrwtRuang.equals("-") || nipPrwtRuang.equals("--")) {
                    param.put("lokasiQrRuang", "");
                } else {
                    isiRuangan = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Serah Terima Pasien Pasca Operasi", TnmPrwtRuang.getText() + " (Perawat Ruang)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiRuangan, Sequel.cariFolderTte(), "QRTtePerawatRg.jpg", "select logo from setting");
                    param.put("lokasiQrRuang", Sequel.cariFolderTte() + File.separator + "QRTtePerawatRg.jpg");
                }
                
                Valid.MyReport("rptSerahTerimaPascaOperasi2Qr.jasper", "report", "::[ Serah Terima Pasien Pasca Operasi ]::",
                        "SELECT now() tanggal", param);
                
                Valid.MyReport("rptSerahTerimaPascaOperasi1Qr.jasper", "report", "::[ Serah Terima Pasien Pasca Operasi ]::",
                        "SELECT now() tanggal", param);
                
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                tampil();
                emptTeks();
            } else {
                Valid.MyReport("rptSerahTerimaPascaOperasi2.jasper", "report", "::[ Serah Terima Pasien Pasca Operasi ]::",
                        "SELECT now() tanggal", param);
                
                Valid.MyReport("rptSerahTerimaPascaOperasi1.jasper", "report", "::[ Serah Terima Pasien Pasca Operasi ]::",
                        "SELECT now() tanggal", param);
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbSerah.requestFocus();
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
        Sequel.cariIsiComboDB("SELECT distinct CASE WHEN nm_gedung IN ('AR-RAUDAH ATAS', 'AR-RAUDAH BAWAH') THEN 'AR-RAUDAH' ELSE nm_gedung END AS gedungnya "
                + "FROM bangsal WHERE status = '1' "
                + "AND nm_gedung NOT LIKE '%instalasi%' "
                + "AND nm_gedung NOT LIKE '%sdm%' "
                + "AND nm_gedung NOT LIKE '%ipsrs%' "
                + "AND nm_gedung NOT LIKE '%uang%' "
                + "AND nm_gedung NOT LIKE '%sanitasi%' "
                + "AND nm_gedung NOT LIKE '%inst.%' "
                + "AND nm_gedung NOT LIKE '%bid.%' "
                + "AND nm_gedung NOT LIKE '%unit%' "
                + "AND nm_gedung NOT LIKE '%bag.%' "
                + "AND nm_gedung NOT LIKE '%upm%' "
                + "AND nm_gedung <>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        tampil();
        ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan, Poliklinik & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void BtnDrOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrOperatorActionPerformed
        pilihDokter = 0;
        pilihDokter = 1;
        akses.setform("RMSerahTerimaPascaOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrOperatorActionPerformed

    private void BtnPrwtIbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrwtIbsActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 1;
        akses.setform("RMSerahTerimaPascaOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPrwtIbsActionPerformed

    private void BtnDrAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrAnestesiActionPerformed
        pilihDokter = 0;
        pilihDokter = 2;
        akses.setform("RMSerahTerimaPascaOperasi");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrAnestesiActionPerformed

    private void BtnPrwtRuangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrwtRuangActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 2;
        akses.setform("RMSerahTerimaPascaOperasi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPrwtRuangActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TdiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttindakan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosaKeyPressed

    private void TtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnDrOperator.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKeyPressed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TtdKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TrrKeyPressed

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttemp.requestFocus();
        }
    }//GEN-LAST:event_TnadiKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMual.requestFocus();
        }
    }//GEN-LAST:event_TtempKeyPressed

    private void cmbJam6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam6MouseReleased
        AutoCompleteDecorator.decorate(cmbJam6);
    }//GEN-LAST:event_cmbJam6MouseReleased

    private void cmbMnt6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt6MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt6);
    }//GEN-LAST:event_cmbMnt6MouseReleased

    private void cmbDtk6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk6MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk6);
    }//GEN-LAST:event_cmbDtk6MouseReleased

    private void TtotAldretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotAldretKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtotBromag.requestFocus();
        }
    }//GEN-LAST:event_TtotAldretKeyPressed

    private void TtotBromagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotBromagKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcairanInfus1.requestFocus();
        }
    }//GEN-LAST:event_TtotBromagKeyPressed

    private void TcairanInfus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcairanInfus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlInfus1.requestFocus();
        }
    }//GEN-LAST:event_TcairanInfus1KeyPressed

    private void TcairanInfus2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcairanInfus2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlInfus2.requestFocus();
        }
    }//GEN-LAST:event_TcairanInfus2KeyPressed

    private void TjmlInfus1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlInfus1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJamCairan1.requestFocus();
        }
    }//GEN-LAST:event_TjmlInfus1KeyPressed

    private void TjmlInfus2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlInfus2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJamCairan2.requestFocus();
        }
    }//GEN-LAST:event_TjmlInfus2KeyPressed

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void Ttranfusi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttranfusi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlTranfusi1.requestFocus();
        }
    }//GEN-LAST:event_Ttranfusi1KeyPressed

    private void TjmlTranfusi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlTranfusi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Ttranfusi2.requestFocus();
        }
    }//GEN-LAST:event_TjmlTranfusi1KeyPressed

    private void Ttranfusi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttranfusi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TjmlTranfusi2.requestFocus();
        }
    }//GEN-LAST:event_Ttranfusi2KeyPressed

    private void TjmlTranfusi2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlTranfusi2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tantibiotik.requestFocus();
        }
    }//GEN-LAST:event_TjmlTranfusi2KeyPressed

    private void TantibiotikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantibiotikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJamAntibiotik.requestFocus();
        }
    }//GEN-LAST:event_TantibiotikKeyPressed

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void TanalgesikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanalgesikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkJamAnalgesik.requestFocus();
        }
    }//GEN-LAST:event_TanalgesikKeyPressed

    private void cmbJam5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam5MouseReleased
        AutoCompleteDecorator.decorate(cmbJam5);
    }//GEN-LAST:event_cmbJam5MouseReleased

    private void cmbMnt5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt5MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt5);
    }//GEN-LAST:event_cmbMnt5MouseReleased

    private void cmbDtk5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk5MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk5);
    }//GEN-LAST:event_cmbDtk5MouseReleased

    private void TobatLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbRuangan.requestFocus();
        }
    }//GEN-LAST:event_TobatLainKeyPressed

    private void cmbRuanganMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseReleased
        AutoCompleteDecorator.decorate(cmbRuangan);
    }//GEN-LAST:event_cmbRuanganMouseReleased

    private void TjnsJaringanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjnsJaringanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPemeriksaanPA.requestFocus();
        }
    }//GEN-LAST:event_TjnsJaringanKeyPressed

    private void TskorRiwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorRiwKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorRiwKeyPressed

    private void TskorKonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorKonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorKonKeyPressed

    private void TskorAlatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorAlatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorAlatKeyPressed

    private void TskorTerpasangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorTerpasangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorTerpasangKeyPressed

    private void TskorGayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorGayaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorGayaKeyPressed

    private void TskorStatusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorStatusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorStatusKeyPressed

    private void TtotSkorDewasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotSkorDewasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtotSkorDewasaKeyPressed

    private void TkesDewasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesDewasaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkesDewasaKeyPressed

    private void TskorUsiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorUsiaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorUsiaKeyPressed

    private void TskorJenkelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorJenkelKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorJenkelKeyPressed

    private void TskorDiagKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorDiagKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorDiagKeyPressed

    private void TskorGangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorGangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorGangKeyPressed

    private void TskorResTerhadapKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorResTerhadapKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorResTerhadapKeyPressed

    private void TskorPengguKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorPengguKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorPengguKeyPressed

    private void TskorFaktorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorFaktorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TskorFaktorKeyPressed

    private void TtotSkorAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtotSkorAnakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtotSkorAnakKeyPressed

    private void TkesAnakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesAnakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkesAnakKeyPressed

    private void TnmKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TnmKeluargaKeyPressed

    private void chkJamCairan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJamCairan1ActionPerformed
        if (chkJamCairan1.isSelected() == true) {
            if (tbSerah.getSelectedRow() != -1) {
                cmbJam2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(0, 2));
                cmbMnt2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(3, 5));
                cmbDtk2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(6, 8));
            } else {
                cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk2.setSelectedIndex(0);
            }
            
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            cmbJam2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbMnt2.setSelectedIndex(0);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
    }//GEN-LAST:event_chkJamCairan1ActionPerformed

    private void chkJamCairan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJamCairan2ActionPerformed
        if (chkJamCairan2.isSelected() == true) {
            if (tbSerah.getSelectedRow() != -1) {
                cmbJam3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(0, 2));
                cmbMnt3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(3, 5));
                cmbDtk3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(6, 8));
            } else {
                cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk3.setSelectedIndex(0);
            }

            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            cmbJam3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
    }//GEN-LAST:event_chkJamCairan2ActionPerformed

    private void chkJamAntibiotikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJamAntibiotikActionPerformed
        if (chkJamAntibiotik.isSelected() == true) {
            if (tbSerah.getSelectedRow() != -1) {
                cmbJam4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(0, 2));
                cmbMnt4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(3, 5));
                cmbDtk4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(6, 8));
            } else {
                cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk4.setSelectedIndex(0);
            }

            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            cmbJam4.setSelectedIndex(0);
            cmbMnt4.setSelectedIndex(0);
            cmbMnt4.setSelectedIndex(0);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
    }//GEN-LAST:event_chkJamAntibiotikActionPerformed

    private void chkJamAnalgesikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkJamAnalgesikActionPerformed
        if (chkJamAnalgesik.isSelected() == true) {
            if (tbSerah.getSelectedRow() != -1) {
                cmbJam5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(0, 2));
                cmbMnt5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(3, 5));
                cmbDtk5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(6, 8));
            } else {
                cmbJam5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
                cmbMnt5.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
                cmbDtk5.setSelectedIndex(0);
            }

            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
        } else {
            cmbJam5.setSelectedIndex(0);
            cmbMnt5.setSelectedIndex(0);
            cmbMnt5.setSelectedIndex(0);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
        }
    }//GEN-LAST:event_chkJamAnalgesikActionPerformed

    private void cmbRiwJatuhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRiwJatuhActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbRiwJatuhActionPerformed

    private void cmbKondisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKondisiActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbKondisiActionPerformed

    private void cmbAlatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAlatActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbAlatActionPerformed

    private void cmbTerpasangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTerpasangActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbTerpasangActionPerformed

    private void cmbGayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGayaActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbGayaActionPerformed

    private void cmbStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbStatusActionPerformed
        hitungDewasa();
    }//GEN-LAST:event_cmbStatusActionPerformed

    private void cmbUsiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsiaActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbUsiaActionPerformed

    private void cmbJenkelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenkelActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbJenkelActionPerformed

    private void cmbDiagnosisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDiagnosisActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbDiagnosisActionPerformed

    private void cmbGangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGangActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbGangActionPerformed

    private void cmbResTerhadapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbResTerhadapActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbResTerhadapActionPerformed

    private void cmbPengguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPengguActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbPengguActionPerformed

    private void cmbFaktorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFaktorActionPerformed
        hitungAnak();
    }//GEN-LAST:event_cmbFaktorActionPerformed

    private void chkDewasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkDewasaActionPerformed
        cmbRiwJatuh.setSelectedIndex(0);
        cmbKondisi.setSelectedIndex(0);
        cmbAlat.setSelectedIndex(0);
        cmbTerpasang.setSelectedIndex(0);
        cmbGaya.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        hitungDewasa();
        TkesDewasa.setText("");
        
        if (chkDewasa.isSelected() == true) {
            chkDewasa.setEnabled(false);
            chkAnak.setEnabled(true);
            
            cmbRiwJatuh.setEnabled(true);
            cmbKondisi.setEnabled(true);
            cmbAlat.setEnabled(true);
            cmbTerpasang.setEnabled(true);
            cmbGaya.setEnabled(true);
            cmbStatus.setEnabled(true);
            chkAnakActionPerformed(null);
        } else {
            cmbRiwJatuh.setEnabled(false);
            cmbKondisi.setEnabled(false);
            cmbAlat.setEnabled(false);
            cmbTerpasang.setEnabled(false);
            cmbGaya.setEnabled(false);
            cmbStatus.setEnabled(false);
        }
    }//GEN-LAST:event_chkDewasaActionPerformed

    private void chkAnakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAnakActionPerformed
        cmbUsia.setSelectedIndex(0);
        cmbJenkel.setSelectedIndex(0);
        cmbDiagnosis.setSelectedIndex(0);
        cmbGang.setSelectedIndex(0);
        cmbResTerhadap.setSelectedIndex(0);
        cmbPenggu.setSelectedIndex(0);
        cmbFaktor.setSelectedIndex(0);        
        hitungAnak();
        TkesAnak.setText("");
        
        if (chkAnak.isSelected() == true) {
            chkAnak.setEnabled(false);
            chkDewasa.setEnabled(true);
            
            cmbUsia.setEnabled(true);
            cmbJenkel.setEnabled(true);
            cmbDiagnosis.setEnabled(true);
            cmbGang.setEnabled(true);
            cmbResTerhadap.setEnabled(true);
            cmbPenggu.setEnabled(true);
            cmbFaktor.setEnabled(true);
            chkDewasaActionPerformed(null);
        } else {            
            cmbUsia.setEnabled(false);
            cmbJenkel.setEnabled(false);
            cmbDiagnosis.setEnabled(false);
            cmbGang.setEnabled(false);
            cmbResTerhadap.setEnabled(false);
            cmbPenggu.setEnabled(false);
            cmbFaktor.setEnabled(false);
        }
    }//GEN-LAST:event_chkAnakActionPerformed

    private void MnHasilPemeriksaanPenunjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanPenunjangActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            akses.setform("RMSerahTerimaPascaOperasi");
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
            akses.setform("RMSerahTerimaPascaOperasi");
            RMDokumenPenunjangMedis form = new RMDokumenPenunjangMedis(null, false);
            form.setData(TNoRw.getText(), TNoRM.getText(), TPasien.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnDokumenJangMedActionPerformed

    private void MnHapusTtdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdActionPerformed
        if (tbSerah.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan keluarga pasien mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileTtd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Keluarga pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileTtd) == true) {
                                Sequel.mengedit("serah_terima_pasien_pasca_operasi", "waktu_simpan='" + tbSerah.getValueAt(tbSerah.getSelectedRow(), 91).toString() + "'",
                                        "id_file_nm_keluarga_pasien=''");
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
        if (tbSerah.getSelectedRow() > -1) {
            ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%SERAH TERIMA PASIEN PASCA OPERASI%'") > 0) {
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

        if (cmbRM.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                    + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                    + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSerahTerimaPascaOperasi dialog = new RMSerahTerimaPascaOperasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn2;
    private widget.Button BtnDrAnestesi;
    private widget.Button BtnDrOperator;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPrwtIbs;
    private widget.Button BtnPrwtRuang;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnDokumenJangMed;
    private javax.swing.JMenuItem MnHapusTtd;
    private javax.swing.JMenuItem MnHasilPemeriksaanPenunjang;
    private javax.swing.JPanel PanelInput1;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll5;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tanalgesik;
    private widget.TextBox Tantibiotik;
    private widget.TextBox TcairanInfus1;
    private widget.TextBox TcairanInfus2;
    private widget.TextBox Tdiagnosa;
    private widget.TextBox TjmlInfus1;
    private widget.TextBox TjmlInfus2;
    private widget.TextBox TjmlTranfusi1;
    private widget.TextBox TjmlTranfusi2;
    private widget.TextBox TjnsJaringan;
    private widget.TextBox TkesAnak;
    private widget.TextBox TkesDewasa;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDrAnestesi;
    private widget.TextBox TnmDrOperator;
    private widget.TextBox TnmKeluarga;
    private widget.TextBox TnmPrwtIbs;
    private widget.TextBox TnmPrwtRuang;
    private widget.TextBox TobatLain;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox TskorAlat;
    private widget.TextBox TskorDiag;
    private widget.TextBox TskorFaktor;
    private widget.TextBox TskorGang;
    private widget.TextBox TskorGaya;
    private widget.TextBox TskorJenkel;
    private widget.TextBox TskorKon;
    private widget.TextBox TskorPenggu;
    private widget.TextBox TskorResTerhadap;
    private widget.TextBox TskorRiw;
    private widget.TextBox TskorStatus;
    private widget.TextBox TskorTerpasang;
    private widget.TextBox TskorUsia;
    private widget.TextBox Ttd;
    private widget.TextBox Ttemp;
    private widget.Tanggal TtglPindah;
    private widget.Tanggal TtglSerah;
    private widget.TextBox Ttindakan;
    private widget.TextBox TtotAldret;
    private widget.TextBox TtotBromag;
    private widget.TextBox TtotSkorAnak;
    private widget.TextBox TtotSkorDewasa;
    private widget.TextBox Ttranfusi1;
    private widget.TextBox Ttranfusi2;
    private javax.swing.JDialog WindowNomorDokumenRM;
    private javax.swing.ButtonGroup buttonGroup1;
    public widget.CekBox chkAgak;
    public widget.CekBox chkAnak;
    public widget.CekBox chkBlock;
    public widget.CekBox chkCse;
    public widget.CekBox chkDalam;
    public widget.CekBox chkDewasa;
    public widget.CekBox chkEpidural;
    public widget.CekBox chkEtt;
    public widget.CekBox chkFima;
    public widget.CekBox chkInfiltrasi;
    public widget.CekBox chkJamAnalgesik;
    public widget.CekBox chkJamAntibiotik;
    public widget.CekBox chkJamCairan1;
    public widget.CekBox chkJamCairan2;
    public widget.CekBox chkLma;
    public widget.CekBox chkMengganggu;
    public widget.CekBox chkRingan;
    public widget.CekBox chkSangat;
    public widget.CekBox chkSedang;
    public widget.CekBox chkSedikitSakit;
    public widget.CekBox chkSpinal;
    public widget.CekBox chkTak;
    public widget.CekBox chkTdkSakit;
    public widget.CekBox chkTiva;
    public widget.CekBox chkTopikal;
    private widget.ComboBox cmbAlat;
    private widget.ComboBox cmbDc;
    private widget.ComboBox cmbDiagnosis;
    private widget.ComboBox cmbDrain;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbDtk5;
    private widget.ComboBox cmbDtk6;
    private widget.ComboBox cmbFaktor;
    private widget.ComboBox cmbGang;
    private widget.ComboBox cmbGaya;
    private widget.ComboBox cmbIrigasi;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJam5;
    private widget.ComboBox cmbJam6;
    private widget.ComboBox cmbJaringanDibawkan;
    private widget.ComboBox cmbJenkel;
    private widget.ComboBox cmbKondisi;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbMnt5;
    private widget.ComboBox cmbMnt6;
    private widget.ComboBox cmbMual;
    private widget.ComboBox cmbNgt;
    private widget.ComboBox cmbPemeriksaanKul;
    private widget.ComboBox cmbPemeriksaanPA;
    private widget.ComboBox cmbPenggu;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbResTerhadap;
    private widget.ComboBox cmbRespon;
    private widget.ComboBox cmbRiwJatuh;
    private widget.ComboBox cmbRuangan;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbTerpasang;
    private widget.ComboBox cmbUsia;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame6;
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
    private widget.Label jLabel120;
    private widget.Label jLabel121;
    private widget.Label jLabel122;
    private widget.Label jLabel123;
    private widget.Label jLabel125;
    private widget.Label jLabel126;
    private widget.Label jLabel127;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel142;
    private widget.Label jLabel143;
    private widget.Label jLabel144;
    private widget.Label jLabel145;
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
    private widget.Label jLabel160;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel276;
    private widget.Label jLabel279;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel283;
    private widget.Label jLabel284;
    private widget.Label jLabel285;
    private widget.Label jLabel286;
    private widget.Label jLabel287;
    private widget.Label jLabel288;
    private widget.Label jLabel289;
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
    private widget.Label jLabel74;
    private widget.Label jLabel75;
    private widget.Label jLabel76;
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel82;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel89;
    private widget.Label jLabel93;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass15;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbSerah;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select st.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, date_format(st.tgl_serah,'%d-%m-%Y') tglserah, "
                    + "time_format(st.pukul_serah,'%H:%i Wita') pukulSerah, pg1.nama drOperator, pg2.nama drAnes, pg3.nama perawatIbs, pg4.nama perawatRuang "
                    + "from serah_terima_pasien_pasca_operasi st inner join reg_periksa rp on rp.no_rawat=st.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=st.nip_dr_operator "
                    + "inner join pegawai pg2 on pg2.nik=st.nip_dr_anestesi inner join pegawai pg3 on pg3.nik=st.nip_perawat_ibs "
                    + "inner join pegawai pg4 on pg4.nik=st.nip_perawat_ruang where "
                    + "st.tgl_serah between ? and ? and st.no_rawat LIKE ? or "
                    + "st.tgl_serah between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "st.tgl_serah between ? and ? and p.nm_pasien LIKE ? or "
                    + "st.tgl_serah between ? and ? and pg1.nama LIKE ? or "
                    + "st.tgl_serah between ? and ? and pg2.nama LIKE ? or "
                    + "st.tgl_serah between ? and ? and pg3.nama LIKE ? or "
                    + "st.tgl_serah between ? and ? and pg4.nama LIKE ? or "
                    + "st.tgl_serah between ? and ? and st.ruang_rawat LIKE ? ORDER BY st.tgl_serah desc");
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
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText() + "%");                
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("ruang_rawat"), 
                        rs.getString("tglserah"), 
                        rs.getString("pukulSerah"),                         
                        rs.getString("drOperator"),                        
                        rs.getString("drAnes"),                        
                        rs.getString("perawatIbs"),
                        rs.getString("perawatRuang"),
                        rs.getString("diagnosis_medis"),
                        rs.getString("tindakan_operasi"),
                        rs.getString("nip_dr_operator"),
                        rs.getString("ett"),
                        rs.getString("lma"),
                        rs.getString("fima"),
                        rs.getString("tiva"),
                        rs.getString("spinal"),
                        rs.getString("epidural"),
                        rs.getString("cse"),
                        rs.getString("infiltrasi"),
                        rs.getString("block"),
                        rs.getString("sedasi_ringan"),
                        rs.getString("sedasi_sedang"),
                        rs.getString("sedasi_dalam"),
                        rs.getString("nip_dr_anestesi"),
                        rs.getString("tgl_pindah"),
                        rs.getString("jam_pindah"),
                        rs.getString("td"),
                        rs.getString("rr"),
                        rs.getString("nadi"),
                        rs.getString("temp"),
                        rs.getString("mual_muntah"),
                        rs.getString("respon_nyeri"),
                        rs.getString("tidak_sakit"),
                        rs.getString("sedikit_sakit"),
                        rs.getString("agak_mengganggu"),
                        rs.getString("mengganggu_aktivitas"),
                        rs.getString("sangat_mengganggu"),
                        rs.getString("tidak_tertahankan"),
                        rs.getString("drain"),
                        rs.getString("ngt"),
                        rs.getString("dc"),
                        rs.getString("irigasi"),
                        rs.getString("total_aldret_skor"),
                        rs.getString("total_bromag_skor"),
                        rs.getString("cairan_infus1"),
                        rs.getString("jml_cairan_infus1"),
                        rs.getString("cek_jam_cairan1"),
                        rs.getString("jam_cairan_infus1"),
                        rs.getString("cairan_infus2"),
                        rs.getString("jml_cairan_infus2"),
                        rs.getString("cek_jam_cairan2"),
                        rs.getString("jam_cairan_infus2"),
                        rs.getString("transfusi1"),
                        rs.getString("jml_transfusi1"),
                        rs.getString("transfusi2"),
                        rs.getString("jml_transfusi2"),
                        rs.getString("antibiotik"),
                        rs.getString("cek_jam_antibiotik"),
                        rs.getString("jam_antibiotik"),
                        rs.getString("analgesik"),
                        rs.getString("cek_jam_analgesik"),
                        rs.getString("jam_analgesik"),
                        rs.getString("obat_lain"),
                        rs.getString("advis_diruangan"),
                        rs.getString("jenis_jaringan"),
                        rs.getString("pemeriksaan_pa"),
                        rs.getString("pemeriksaan_kultur"),
                        rs.getString("jaringan_dibawakan"),                        
                        rs.getString("asesmen_dewasa"),
                        rs.getString("asesmen_anak"),                        
                        rs.getString("arj_dewasa_riwayat_jatuh"),
                        rs.getString("arj_dewasa_kondisi_kesehatan"),
                        rs.getString("arj_dewasa_alat_bantu"),
                        rs.getString("arj_dewasa_terpasang_infus"),
                        rs.getString("arj_dewasa_gaya_berjalan"),
                        rs.getString("arj_dewasa_status_mental"),
                        rs.getString("arj_anak_usia"),
                        rs.getString("arj_anak_jenis_kelamin"),
                        rs.getString("arj_anak_diagnosis"),
                        rs.getString("arj_anak_gangguan_kognitif"),
                        rs.getString("arj_anak_respon"),
                        rs.getString("arj_anak_penggunaan_medikamentosa"),
                        rs.getString("arj_anak_faktor_lingkungan"),
                        rs.getString("tgl_serah"),
                        rs.getString("pukul_serah"),
                        rs.getString("nm_keluarga_pasien"),
                        rs.getString("nip_perawat_ibs"),
                        rs.getString("nip_perawat_ruang"),
                        rs.getString("waktu_simpan"),
                        rs.getString("id_file_nm_keluarga_pasien"),
                        rs.getString("topikal")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMSerahTerimaPascaOperasi.tampil() : " + e);
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
        Tdiagnosa.setText("");
        Ttindakan.setText("");
        nipDrOperator = "-";
        TnmDrOperator.setText("-");
        chkEtt.setSelected(false);
        chkLma.setSelected(false);
        chkFima.setSelected(false);
        chkTiva.setSelected(false);
        chkSpinal.setSelected(false);
        chkEpidural.setSelected(false);
        chkCse.setSelected(false);
        chkInfiltrasi.setSelected(false);
        chkBlock.setSelected(false);
        chkTopikal.setSelected(false);
        chkRingan.setSelected(false);
        chkSedang.setSelected(false);
        chkDalam.setSelected(false);
        nipDrAnes = "-";
        TnmDrAnestesi.setText("-");
        TtglPindah.setDate(new Date());
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Ttd.setText("");
        Trr.setText("");
        Tnadi.setText("");
        Ttemp.setText("");
        cmbMual.setSelectedIndex(0);
        cmbRespon.setSelectedIndex(0);
        chkTdkSakit.setSelected(false);
        chkSedikitSakit.setSelected(false);
        chkAgak.setSelected(false);
        chkMengganggu.setSelected(false);
        chkSangat.setSelected(false);
        chkTak.setSelected(false);
        cmbDrain.setSelectedIndex(0);
        cmbNgt.setSelectedIndex(0);
        cmbDc.setSelectedIndex(0);
        cmbIrigasi.setSelectedIndex(0);
        TtotAldret.setText("");
        TtotBromag.setText("");
        TcairanInfus1.setText("");
        TjmlInfus1.setText("");
        chkJamCairan1.setSelected(false);
        cmbJam2.setSelectedIndex(0);
        cmbMnt2.setSelectedIndex(0);
        cmbDtk2.setSelectedIndex(0);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);        
        TcairanInfus2.setText("");
        TjmlInfus2.setText("");
        chkJamCairan2.setSelected(false);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        Ttranfusi1.setText("");
        TjmlTranfusi1.setText("");
        Ttranfusi2.setText("");
        TjmlTranfusi2.setText("");
        Tantibiotik.setText("");
        chkJamAntibiotik.setSelected(false);
        cmbJam4.setSelectedIndex(0);
        cmbMnt4.setSelectedIndex(0);
        cmbDtk4.setSelectedIndex(0);
        cmbJam4.setEnabled(false);
        cmbMnt4.setEnabled(false);
        cmbDtk4.setEnabled(false);        
        Tanalgesik.setText("");
        chkJamAnalgesik.setSelected(false);
        cmbJam5.setSelectedIndex(0);
        cmbMnt5.setSelectedIndex(0);
        cmbDtk5.setSelectedIndex(0);
        cmbJam5.setEnabled(false);
        cmbMnt5.setEnabled(false);
        cmbDtk5.setEnabled(false);
        TobatLain.setText("");
        cmbRuangan.setSelectedIndex(0);
        TjnsJaringan.setText("");
        cmbPemeriksaanPA.setSelectedIndex(0);
        cmbPemeriksaanKul.setSelectedIndex(0);
        cmbJaringanDibawkan.setSelectedIndex(0);
        
        buttonGroup1.clearSelection();
        chkDewasa.setEnabled(true);
        chkAnak.setEnabled(true);
        cmbRiwJatuh.setEnabled(false);
        cmbKondisi.setEnabled(false);
        cmbAlat.setEnabled(false);
        cmbTerpasang.setEnabled(false);
        cmbGaya.setEnabled(false);
        cmbStatus.setEnabled(false);
        cmbUsia.setEnabled(false);
        cmbJenkel.setEnabled(false);
        cmbDiagnosis.setEnabled(false);
        cmbGang.setEnabled(false);
        cmbResTerhadap.setEnabled(false);
        cmbPenggu.setEnabled(false);
        cmbFaktor.setEnabled(false);
        
        cmbRiwJatuh.setSelectedIndex(0);
        cmbKondisi.setSelectedIndex(0);
        cmbAlat.setSelectedIndex(0);
        cmbTerpasang.setSelectedIndex(0);
        cmbGaya.setSelectedIndex(0);
        cmbStatus.setSelectedIndex(0);
        hitungDewasa();
        TkesDewasa.setText("");
        
        cmbUsia.setSelectedIndex(0);
        cmbJenkel.setSelectedIndex(0);
        cmbDiagnosis.setSelectedIndex(0);
        cmbGang.setSelectedIndex(0);
        cmbResTerhadap.setSelectedIndex(0);
        cmbPenggu.setSelectedIndex(0);
        cmbFaktor.setSelectedIndex(0);
        hitungAnak();
        TkesAnak.setText("");
        
        TtglSerah.setDate(new Date());
        cmbJam6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt6.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk6.setSelectedIndex(0);
        TnmKeluarga.setText("");
        nipPrwtIbs = "-";
        TnmPrwtIbs.setText("-");
        nipPrwtRuang = "-";
        TnmPrwtRuang.setText("-");
        LoadHTML1.setText("");
    }

    private void getData() {
        variabelBersih();
        if (tbSerah.getSelectedRow() != -1) {
            TNoRw.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 1).toString());
            TPasien.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 4).toString());
            Tdiagnosa.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 11).toString());
            Ttindakan.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 12).toString());            
            nipDrOperator = tbSerah.getValueAt(tbSerah.getSelectedRow(), 13).toString();
            TnmDrOperator.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 7).toString());            
            ett = tbSerah.getValueAt(tbSerah.getSelectedRow(), 14).toString();
            lma = tbSerah.getValueAt(tbSerah.getSelectedRow(), 15).toString();
            fima = tbSerah.getValueAt(tbSerah.getSelectedRow(), 16).toString();
            tiva = tbSerah.getValueAt(tbSerah.getSelectedRow(), 17).toString();
            spinal = tbSerah.getValueAt(tbSerah.getSelectedRow(), 18).toString();
            epidural = tbSerah.getValueAt(tbSerah.getSelectedRow(), 19).toString();
            cse = tbSerah.getValueAt(tbSerah.getSelectedRow(), 20).toString();            
            infil = tbSerah.getValueAt(tbSerah.getSelectedRow(), 21).toString();
            blok = tbSerah.getValueAt(tbSerah.getSelectedRow(), 22).toString();            
            ringan = tbSerah.getValueAt(tbSerah.getSelectedRow(), 23).toString();
            sedang = tbSerah.getValueAt(tbSerah.getSelectedRow(), 24).toString();
            dalam = tbSerah.getValueAt(tbSerah.getSelectedRow(), 25).toString();
            nipDrAnes = tbSerah.getValueAt(tbSerah.getSelectedRow(), 26).toString();
            TnmDrAnestesi.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 8).toString());
            Valid.SetTgl(TtglPindah, tbSerah.getValueAt(tbSerah.getSelectedRow(), 27).toString());
            cmbJam1.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 28).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 28).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 28).toString().substring(6, 8));
            Ttd.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 29).toString());
            Trr.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 30).toString());
            Tnadi.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 31).toString());
            Ttemp.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 32).toString());            
            cmbMual.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 33).toString());
            cmbRespon.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 34).toString());            
            tdkSakit = tbSerah.getValueAt(tbSerah.getSelectedRow(), 35).toString();
            sedSakit = tbSerah.getValueAt(tbSerah.getSelectedRow(), 36).toString();
            agak = tbSerah.getValueAt(tbSerah.getSelectedRow(), 37).toString();
            menggang = tbSerah.getValueAt(tbSerah.getSelectedRow(), 38).toString();
            sangat = tbSerah.getValueAt(tbSerah.getSelectedRow(), 39).toString();
            tak = tbSerah.getValueAt(tbSerah.getSelectedRow(), 40).toString();
            cmbDrain.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 41).toString());
            cmbNgt.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 42).toString());
            cmbDc.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 43).toString());
            cmbIrigasi.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 44).toString());            
            TtotAldret.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 45).toString());
            TtotBromag.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 46).toString());
            TcairanInfus1.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 47).toString());
            TjmlInfus1.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 48).toString());
            cekJamInfus1 = tbSerah.getValueAt(tbSerah.getSelectedRow(), 49).toString();
            cmbJam2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 50).toString().substring(6, 8));
            TcairanInfus2.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 51).toString());
            TjmlInfus2.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 52).toString());
            cekJamInfus2 = tbSerah.getValueAt(tbSerah.getSelectedRow(), 53).toString();
            cmbJam3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 54).toString().substring(6, 8));
            Ttranfusi1.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 55).toString());
            TjmlTranfusi1.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 56).toString());
            Ttranfusi2.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 57).toString());
            TjmlTranfusi2.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 58).toString());
            Tantibiotik.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 59).toString());
            cekJamAnti = tbSerah.getValueAt(tbSerah.getSelectedRow(), 60).toString();
            cmbJam4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 61).toString().substring(6, 8));
            Tanalgesik.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 62).toString());
            cekJamAnal = tbSerah.getValueAt(tbSerah.getSelectedRow(), 63).toString();
            cmbJam5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(0, 2));
            cmbMnt5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(3, 5));
            cmbDtk5.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 64).toString().substring(6, 8));
            TobatLain.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 65).toString());
            cmbRuangan.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 66).toString());
            TjnsJaringan.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 67).toString());
            cmbPemeriksaanPA.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 68).toString());
            cmbPemeriksaanKul.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 69).toString());
            cmbJaringanDibawkan.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 70).toString());            
            asesDewasa = tbSerah.getValueAt(tbSerah.getSelectedRow(), 71).toString();
            asesAnak = tbSerah.getValueAt(tbSerah.getSelectedRow(), 72).toString();            
            cmbRiwJatuh.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 73).toString());
            cmbKondisi.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 74).toString());
            cmbAlat.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 75).toString());
            cmbTerpasang.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 76).toString());
            cmbGaya.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 77).toString());
            cmbStatus.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 78).toString());
            hitungDewasa();            
            cmbUsia.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 79).toString());
            cmbJenkel.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 80).toString());
            cmbDiagnosis.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 81).toString());
            cmbGang.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 82).toString());
            cmbResTerhadap.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 83).toString());
            cmbPenggu.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 84).toString());
            cmbFaktor.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 85).toString());
            hitungAnak();            
            Valid.SetTgl(TtglSerah, tbSerah.getValueAt(tbSerah.getSelectedRow(), 86).toString());            
            cmbJam6.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 87).toString().substring(0, 2));
            cmbMnt6.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 87).toString().substring(3, 5));
            cmbDtk6.setSelectedItem(tbSerah.getValueAt(tbSerah.getSelectedRow(), 87).toString().substring(6, 8));
            TnmKeluarga.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 88).toString());
            nipPrwtIbs = tbSerah.getValueAt(tbSerah.getSelectedRow(), 89).toString();
            TnmPrwtIbs.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 9).toString());
            nipPrwtRuang = tbSerah.getValueAt(tbSerah.getSelectedRow(), 90).toString();
            TnmPrwtRuang.setText(tbSerah.getValueAt(tbSerah.getSelectedRow(), 10).toString());
            idFileTtd = tbSerah.getValueAt(tbSerah.getSelectedRow(), 92).toString();
            topikal = tbSerah.getValueAt(tbSerah.getSelectedRow(), 93).toString();
            dataCek();
            tampilTTD();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
    }
    
    private void dataCek() {
        if (ett.equals("ya")) {
            chkEtt.setSelected(true);
        } else {
            chkEtt.setSelected(false);
        }
        
        if (lma.equals("ya")) {
            chkLma.setSelected(true);
        } else {
            chkLma.setSelected(false);
        }
        
        if (fima.equals("ya")) {
            chkFima.setSelected(true);
        } else {
            chkFima.setSelected(false);
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
        
        if (cse.equals("ya")) {
            chkCse.setSelected(true);
        } else {
            chkCse.setSelected(false);
        }
        
        if (infil.equals("ya")) {
            chkInfiltrasi.setSelected(true);
        } else {
            chkInfiltrasi.setSelected(false);
        }
        
        if (blok.equals("ya")) {
            chkBlock.setSelected(true);
        } else {
            chkBlock.setSelected(false);
        }
        
        if (topikal.equals("ya")) {
            chkTopikal.setSelected(true);
        } else {
            chkTopikal.setSelected(false);
        }
        
        if (ringan.equals("ya")) {
            chkRingan.setSelected(true);
        } else {
            chkRingan.setSelected(false);
        }
        
        if (sedang.equals("ya")) {
            chkSedang.setSelected(true);
        } else {
            chkSedang.setSelected(false);
        }
        
        if (dalam.equals("ya")) {
            chkDalam.setSelected(true);
        } else {
            chkDalam.setSelected(false);
        }
        
        if (tdkSakit.equals("ya")) {
            chkTdkSakit.setSelected(true);
        } else {
            chkTdkSakit.setSelected(false);
        }
        
        if (sedSakit.equals("ya")) {
            chkSedikitSakit.setSelected(true);
        } else {
            chkSedikitSakit.setSelected(false);
        }
        
        if (agak.equals("ya")) {
            chkAgak.setSelected(true);
        } else {
            chkAgak.setSelected(false);
        }
        
        if (menggang.equals("ya")) {
            chkMengganggu.setSelected(true);
        } else {
            chkMengganggu.setSelected(false);
        }
        
        if (sangat.equals("ya")) {
            chkSangat.setSelected(true);
        } else {
            chkSangat.setSelected(false);
        }
        
        if (tak.equals("ya")) {
            chkTak.setSelected(true);
        } else {
            chkTak.setSelected(false);
        }
        
        if (cekJamInfus1.equals("ya")) {
            chkJamCairan1.setSelected(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
        } else {
            chkJamCairan1.setSelected(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
        }
        
        if (cekJamInfus2.equals("ya")) {
            chkJamCairan2.setSelected(true);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        } else {
            chkJamCairan2.setSelected(false);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);
        }
        
        if (cekJamAnti.equals("ya")) {
            chkJamAntibiotik.setSelected(true);
            cmbJam4.setEnabled(true);
            cmbMnt4.setEnabled(true);
            cmbDtk4.setEnabled(true);
        } else {
            chkJamAntibiotik.setSelected(false);
            cmbJam4.setEnabled(false);
            cmbMnt4.setEnabled(false);
            cmbDtk4.setEnabled(false);
        }
        
        if (cekJamAnal.equals("ya")) {
            chkJamAnalgesik.setSelected(true);
            cmbJam5.setEnabled(true);
            cmbMnt5.setEnabled(true);
            cmbDtk5.setEnabled(true);
        } else {
            chkJamAnalgesik.setSelected(false);
            cmbJam5.setEnabled(false);
            cmbMnt5.setEnabled(false);
            cmbDtk5.setEnabled(false);
        }

        if (asesDewasa.equals("ya")) {
            chkDewasa.setEnabled(false);
            chkAnak.setEnabled(true);
            
            chkDewasa.setSelected(true);
            cmbRiwJatuh.setEnabled(true);
            cmbKondisi.setEnabled(true);
            cmbAlat.setEnabled(true);
            cmbTerpasang.setEnabled(true);
            cmbGaya.setEnabled(true);
            cmbStatus.setEnabled(true);            
        } else {
            chkDewasa.setEnabled(true);
            chkDewasa.setSelected(false);
            cmbRiwJatuh.setEnabled(false);
            cmbKondisi.setEnabled(false);
            cmbAlat.setEnabled(false);
            cmbTerpasang.setEnabled(false);
            cmbGaya.setEnabled(false);
            cmbStatus.setEnabled(false);
            TkesDewasa.setText("");
        }
        
        if (asesAnak.equals("ya")) {
            chkAnak.setEnabled(false);
            chkDewasa.setEnabled(true);
            
            chkAnak.setSelected(true);
            cmbUsia.setEnabled(true);
            cmbJenkel.setEnabled(true);
            cmbDiagnosis.setEnabled(true);
            cmbGang.setEnabled(true);
            cmbResTerhadap.setEnabled(true);
            cmbPenggu.setEnabled(true);
            cmbFaktor.setEnabled(true);
        } else {
            chkAnak.setEnabled(true);
            chkAnak.setSelected(false);
            cmbUsia.setEnabled(false);
            cmbJenkel.setEnabled(false);
            cmbDiagnosis.setEnabled(false);
            cmbGang.setEnabled(false);
            cmbResTerhadap.setEnabled(false);
            cmbPenggu.setEnabled(false);
            cmbFaktor.setEnabled(false);
            TkesAnak.setText("");
        }
        
        if (asesDewasa.equals("tidak") && asesAnak.equals("tidak")) {
            buttonGroup1.clearSelection();
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
        if (chkEtt.isSelected() == true) {
            ett = "ya";
        } else {
            ett = "tidak";
        }
        
        if (chkLma.isSelected() == true) {
            lma = "ya";
        } else {
            lma = "tidak";
        }
        
        if (chkFima.isSelected() == true) {
            fima = "ya";
        } else {
            fima = "tidak";
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
        
        if (chkCse.isSelected() == true) {
            cse = "ya";
        } else {
            cse = "tidak";
        }
        
        if (chkInfiltrasi.isSelected() == true) {
            infil = "ya";
        } else {
            infil = "tidak";
        }
        
        if (chkBlock.isSelected() == true) {
            blok = "ya";
        } else {
            blok = "tidak";
        }
        
        if (chkTopikal.isSelected() == true) {
            topikal = "ya";
        } else {
            topikal = "tidak";
        }
        
        if (chkRingan.isSelected() == true) {
            ringan = "ya";
        } else {
            ringan = "tidak";
        }
        
        if (chkSedang.isSelected() == true) {
            sedang = "ya";
        } else {
            sedang = "tidak";
        }
        
        if (chkDalam.isSelected() == true) {
            dalam = "ya";
        } else {
            dalam = "tidak";
        }
        
        if (chkTdkSakit.isSelected() == true) {
            tdkSakit = "ya";
        } else {
            tdkSakit = "tidak";
        }
        
        if (chkSedikitSakit.isSelected() == true) {
            sedSakit = "ya";
        } else {
            sedSakit = "tidak";
        }
        
        if (chkAgak.isSelected() == true) {
            agak = "ya";
        } else {
            agak = "tidak";
        }
        
        if (chkMengganggu.isSelected() == true) {
            menggang = "ya";
        } else {
            menggang = "tidak";
        }
        
        if (chkSangat.isSelected() == true) {
            sangat = "ya";
        } else {
            sangat = "tidak";
        }
        
        if (chkTak.isSelected() == true) {
            tak = "ya";
        } else {
            tak = "tidak";
        }
        
        if (chkJamCairan1.isSelected() == true) {
            cekJamInfus1 = "ya";
        } else {
            cekJamInfus1 = "tidak";
        }
        
        if (chkJamCairan2.isSelected() == true) {
            cekJamInfus2 = "ya";
        } else {
            cekJamInfus2 = "tidak";
        }
        
        if (chkJamAntibiotik.isSelected() == true) {
            cekJamAnti = "ya";
        } else {
            cekJamAnti = "tidak";
        }
        
        if (chkJamAnalgesik.isSelected() == true) {
            cekJamAnal = "ya";
        } else {
            cekJamAnal = "tidak";
        }
        
        if (chkDewasa.isSelected() == true) {
            asesDewasa = "ya";
        } else {
            asesDewasa = "tidak";
        }
        
        if (chkAnak.isSelected() == true) {
            asesAnak = "ya";
        } else {
            asesAnak = "tidak";
        }
    }
    
    private void variabelBersih() {
        nipDrOperator = "";
        nipDrAnes = "";
        nipPrwtIbs = "";
        nipPrwtRuang = "";
        ett = "";
        lma = "";
        fima = "";
        tiva = "";
        spinal = "";
        epidural = "";
        cse = "";
        infil = "";
        blok = "";
        topikal = "";
        ringan = "";
        sedang = "";
        dalam = "";
        tdkSakit = "";
        sedSakit = "";
        agak = "";
        menggang = "";
        sangat = "";
        tak = "";
        cekJamInfus1 = "";
        cekJamInfus2 = "";
        cekJamAnti = "";
        cekJamAnal = "";
        asesDewasa = "";
        asesAnak = "";
        idFileTtd = "";
        idParameterTtd = "";
        URL = "";
        usernya = "";
        pwdnya = "";
        ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
    }
    
    private void hitungDewasa() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, f = 0, hasil = 0;
        
        if (cmbRiwJatuh.getSelectedIndex() == 1) {
            a = 25;
            TskorRiw.setText("25");
        } else if (cmbRiwJatuh.getSelectedIndex() == 2) {
            a = 0;
            TskorRiw.setText("0");
        } else {
            a = 0;
            TskorRiw.setText("");
        }
        
        if (cmbKondisi.getSelectedIndex() == 1) {
            b = 15;
            TskorKon.setText("15");
        } else if (cmbKondisi.getSelectedIndex() == 2) {
            b = 0;
            TskorKon.setText("0");
        } else {
            b = 0;
            TskorKon.setText("");
        }
        
        if (cmbAlat.getSelectedIndex() == 1) {
            c = 30;
            TskorAlat.setText("30");
        } else if (cmbAlat.getSelectedIndex() == 2) {
            c = 15;
            TskorAlat.setText("15");
        } else if (cmbAlat.getSelectedIndex() == 3) {
            c = 0;
            TskorAlat.setText("0");
        } else {
            c = 0;
            TskorAlat.setText("");
        }
        
        if (cmbTerpasang.getSelectedIndex() == 1) {
            d = 20;
            TskorTerpasang.setText("20");
        } else if (cmbTerpasang.getSelectedIndex() == 2) {
            d = 0;
            TskorTerpasang.setText("0");
        } else {
            d = 0;
            TskorTerpasang.setText("");
        }
        
        if (cmbGaya.getSelectedIndex() == 1) {
            e = 20;
            TskorGaya.setText("20");
        } else if (cmbGaya.getSelectedIndex() == 2) {
            e = 10;
            TskorGaya.setText("10");
        } else if (cmbGaya.getSelectedIndex() == 3) {
            e = 0;
            TskorGaya.setText("0");
        } else {
            e = 0;
            TskorGaya.setText("");
        }
        
        if (cmbStatus.getSelectedIndex() == 1) {
            f = 15;
            TskorStatus.setText("15");
        } else if (cmbStatus.getSelectedIndex() == 2) {
            f = 0;
            TskorStatus.setText("0");
        } else {
            f = 0;
            TskorStatus.setText("");
        }
        
        hasil = a + b + c + d + e + f;
        TtotSkorDewasa.setText(Valid.SetAngka2(hasil));        
       
        if (hasil >= 0 && hasil <= 24) {
            TkesDewasa.setText("Resiko Rendah : 0 - 24");
        } else if (hasil >= 25 && hasil <= 44) {
            TkesDewasa.setText("Resiko Sedang : 25 - 44, Pasang Kancing Kuning");
        } else if (hasil >= 45) {
            TkesDewasa.setText("Resiko Tinggi : >=45, Pasang Kancing Kuning");
        } else if (hasil == 0) {
            TkesDewasa.setText("");
        }
    }
    
    private void hitungAnak() {
        int A = 0, B = 0, C = 0, D = 0, E = 0, F = 0, G = 0, hasil = 0;

        if (cmbUsia.getSelectedIndex() == 1) {
            A = 4;
            TskorUsia.setText("4");
        } else if (cmbUsia.getSelectedIndex() == 2) {
            A = 3;
            TskorUsia.setText("3");
        } else if (cmbUsia.getSelectedIndex() == 3) {
            A = 2;
            TskorUsia.setText("3");
        } else if (cmbUsia.getSelectedIndex() == 4) {
            A = 1;
            TskorUsia.setText("1");
        } else {
            A = 0;
            TskorUsia.setText("");
        }

        if (cmbJenkel.getSelectedIndex() == 1) {
            B = 2;
            TskorJenkel.setText("2");
        } else if (cmbJenkel.getSelectedIndex() == 2) {
            B = 1;
            TskorJenkel.setText("1");
        } else {
            B = 0;
            TskorJenkel.setText("");
        }

        if (cmbDiagnosis.getSelectedIndex() == 1) {
            C = 4;
            TskorDiag.setText("4");
        } else if (cmbDiagnosis.getSelectedIndex() == 2) {
            C = 3;
            TskorDiag.setText("3");
        } else if (cmbDiagnosis.getSelectedIndex() == 3 || cmbDiagnosis.getSelectedIndex() == 4) {
            C = 1;
            TskorDiag.setText("1");
        } else {
            C = 0;
            TskorDiag.setText("");
        }
        
        if (cmbGang.getSelectedIndex() == 1) {
            D = 3;
            TskorGang.setText("3");
        } else if (cmbGang.getSelectedIndex() == 2) {
            D = 2;
            TskorGang.setText("2");
        } else if (cmbGang.getSelectedIndex() == 3) {
            D = 1;
            TskorGang.setText("1");
        } else {
            D = 0;
            TskorGang.setText("");
        }
        
        if (cmbResTerhadap.getSelectedIndex() == 1) {
            E = 3;
            TskorResTerhadap.setText("3");
        } else if (cmbResTerhadap.getSelectedIndex() == 2) {
            E = 2;
            TskorResTerhadap.setText("2");
        } else if (cmbResTerhadap.getSelectedIndex() == 3) {
            E = 1;
            TskorResTerhadap.setText("1");
        } else {
            E = 0;
            TskorResTerhadap.setText("");
        }
        
        if (cmbPenggu.getSelectedIndex() == 1) {
            F = 2;
            TskorPenggu.setText("2");
        } else if (cmbPenggu.getSelectedIndex() == 2) {
            F = 1;
            TskorPenggu.setText("1");
        } else {
            F = 0;
            TskorPenggu.setText("");
        }
        
        if (cmbFaktor.getSelectedIndex() == 1) {
            G = 4;
            TskorFaktor.setText("4");
        } else if (cmbFaktor.getSelectedIndex() == 2) {
            G = 3;
            TskorFaktor.setText("3");
        } else if (cmbFaktor.getSelectedIndex() == 3) {
            G = 2;
            TskorFaktor.setText("2");
        } else if (cmbFaktor.getSelectedIndex() == 4) {
            G = 1;
            TskorFaktor.setText("1");
        } else {
            G = 0;
            TskorFaktor.setText("");
        }
        
        hasil = A + B + C + D + E + F + G;
        TtotSkorAnak.setText(Valid.SetAngka2(hasil));
        
        if (hasil >= 0 && hasil <= 7) {
            TkesAnak.setText("Resiko Rendah : 7 - 11");
        } else if (hasil >= 12) {
            TkesAnak.setText("Resiko Tinggi : >=12, Pasang Kancing Penanda Kuning");
        } else if (hasil == 0) {
            TkesAnak.setText("");
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
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%SERAH TERIMA PASIEN PASCA OPERASI%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
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
                    + "<td align='center' bgcolor='#f8fdf3'><b>Keluarga Pasien</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar + "' width='160' height='160' alt='TTD Keluarga Pasien'><br>(" + TnmKeluarga.getText() + ")<br></td>"
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
    
    public void awalData() {
        Sequel.cariIsiComboDB("SELECT distinct CASE WHEN nm_gedung IN ('AR-RAUDAH ATAS', 'AR-RAUDAH BAWAH') THEN 'AR-RAUDAH' ELSE nm_gedung END AS gedungnya "
                + "FROM bangsal WHERE status = '1' "
                + "AND nm_gedung NOT LIKE '%instalasi%' "
                + "AND nm_gedung NOT LIKE '%sdm%' "
                + "AND nm_gedung NOT LIKE '%ipsrs%' "
                + "AND nm_gedung NOT LIKE '%uang%' "
                + "AND nm_gedung NOT LIKE '%sanitasi%' "
                + "AND nm_gedung NOT LIKE '%inst.%' "
                + "AND nm_gedung NOT LIKE '%bid.%' "
                + "AND nm_gedung NOT LIKE '%unit%' "
                + "AND nm_gedung NOT LIKE '%bag.%' "
                + "AND nm_gedung NOT LIKE '%upm%' "
                + "AND nm_gedung <>'-' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        tampil();
        ((RMSerahTerimaPascaOperasi.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan, Poliklinik & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }
}
