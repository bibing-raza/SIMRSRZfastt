package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMCatatanRuangPemulihan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2;
    private ResultSet rs, rs1, rs2;
    private int i = 0, x = 0, pilihPetugas = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nipDrAnes = "", nipPenata = "", nipPerawat = "", urutData = "", urutanKe = "", wktSimpan = "",
            sis15 = "", sis30 = "", sis45 = "", sis60 = "", sis90 = "", sis120 = "", sis150 = "",
            kesAld15 = "", kesAld30 = "", kesAld45 = "", kesAld60 = "", kesAld90 = "", kesAld120 = "", kesAld150 = "",
            perAld15 = "", perAld30 = "", perAld45 = "", perAld60 = "", perAld90 = "", perAld120 = "", perAld150 = "",
            war15 = "", war30 = "", war45 = "", war60 = "", war90 = "", war120 = "", war150 = "",
            aktAld15 = "", aktAld30 = "", aktAld45 = "", aktAld60 = "", aktAld90 = "", aktAld120 = "", aktAld150 = "",
            kesSte15 = "", kesSte30 = "", kesSte45 = "", kesSte60 = "", kesSte90 = "", kesSte120 = "", kesSte150 = "",
            perSte15 = "", perSte30 = "", perSte45 = "", perSte60 = "", perSte90 = "", perSte120 = "", perSte150 = "",
            aktSte15 = "", aktSte30 = "", aktSte45 = "", aktSte60 = "", aktSte90 = "", aktSte120 = "", aktSte150 = "",
            kesBro15 = "", kesBro30 = "", kesBro45 = "", kesBro60 = "", kesBro90 = "", kesBro120 = "", kesBro150 = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCatatanRuangPemulihan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Dokter Anestesi", "Penata Anestesi/Perawat RR", "Perawat Bangsal",
            "jam_msk", "jalan_nafas", "ket_jalan_nafas", "pernapasan_cttn", "bila_spontan", "kesadaran_cttn", "td_pre_anestesi", "siskulasi", "sis_menit_15", 
            "sis_menit_30", "sis_menit_45", "sis_menit_60", "sis_menit_90", "sis_menit_120", "sis_menit_150", "siskulasi_discharge", "kesadaran", "kes_menit_15", 
            "kes_menit_30", "kes_menit_45", "kes_menit_60", "kes_menit_90", "kes_menit_120", "kes_menit_150", "kesadaran_discharge", "pernapasan", "per_menit_15", 
            "per_menit_30", "per_menit_45", "per_menit_60", "per_menit_90", "per_menit_120", "per_menit_150", "pernapasan_discharge", "warna_kulit", "war_menit_15", 
            "war_menit_30", "war_menit_45", "war_menit_60", "war_menit_90", "war_menit_120", "war_menit_150", "warna_kulit_discharge", "aktifitas", "akt_menit_15", 
            "akt_menit_30", "akt_menit_45", "akt_menit_60", "akt_menit_90", "akt_menit_120", "akt_menit_150", "aktifitas_discharge", "td_pre_anestesi_steward", 
            "kesadaran_steward", "kes_ste_menit_15", "kes_ste_menit_30", "kes_ste_menit_45", "kes_ste_menit_60", "kes_ste_menit_90", "kes_ste_menit_120", 
            "kes_ste_menit_150", "kesadaran_discharge_steward", "pernapasan_steward", "per_ste_menit_15", "per_ste_menit_30", "per_ste_menit_45", "per_ste_menit_60", 
            "per_ste_menit_90", "per_ste_menit_120", "per_ste_menit_150", "pernapasan_discharge_steward", "aktifitas_mot_steward", "akt_ste_menit_15", "akt_ste_menit_30", 
            "akt_ste_menit_45", "akt_ste_menit_60", "akt_ste_menit_90", "akt_ste_menit_120", "akt_ste_menit_150", "aktifitas_discharge_steward", "td_pre_anestesi_brom", 
            "kesadaran_brom", "kes_brom_menit_15", "kes_brom_menit_30", "kes_brom_menit_45", "kes_brom_menit_60", "kes_brom_menit_90", "kes_brom_menit_120", 
            "kes_brom_menit_150", "kesadaran_discharge_brom", "infus", "puasa", "minum_makan_jam", "bila", "analgetik", "antiemetik", "obat_lain", "obat_medikasi_a", 
            "obat_medikasi_b", "pasien_boleh_pindah", "jam_pindah", "lain_lain", "posisi_tidur", "kateter_epidural", "instruksi_khusus", "nip_dokter_anestesi", 
            "nip_penata", "nip_perawat_bangsal", "tgl_serah_terima", "jam_serah_terima", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCatatan.setModel(tabMode);
        tbCatatan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCatatan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 119; i++) {
            TableColumn column = tbCatatan.getColumnModel().getColumn(i);
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
            }
        }
        tbCatatan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{"no_rawat", "urutan", "Pukul", "TD (Sistole)", "TD (Diastole)",
            "Nadi", "RR", "Suhu", "SpO2", "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbTTV.setModel(tabMode1);
        tbTTV.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbTTV.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = tbTTV.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(60);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            } else if (i == 7) {
                column.setPreferredWidth(60);
            } else if (i == 8) {
                column.setPreferredWidth(60);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbTTV.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbTTV.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTTV.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        
        TketJlnNafas.setDocument(new batasInput((int) 100).getKata(TketJlnNafas));
        TtdPreAnesAldret.setDocument(new batasInput((int) 10).getKata(TtdPreAnesAldret));
        TdiscarSis.setDocument(new batasInput((int) 100).getKata(TdiscarSis));
        TdiscarKesAldret.setDocument(new batasInput((int) 100).getKata(TdiscarKesAldret));
        TdiscarPerAldret.setDocument(new batasInput((int) 100).getKata(TdiscarPerAldret));
        TdiscarWar.setDocument(new batasInput((int) 100).getKata(TdiscarWar));
        TdiscarAktAldret.setDocument(new batasInput((int) 100).getKata(TdiscarAktAldret));
        TtdPreAnesSte.setDocument(new batasInput((int) 10).getKata(TtdPreAnesSte));
        TdiscarKesSte.setDocument(new batasInput((int) 100).getKata(TdiscarKesSte));
        TdiscarPerSte.setDocument(new batasInput((int) 100).getKata(TdiscarPerSte));
        TdiscarAktSte.setDocument(new batasInput((int) 100).getKata(TdiscarAktSte));
        TtdPreAnesBrom.setDocument(new batasInput((int) 10).getKata(TtdPreAnesBrom));
        TdiscarKesBrom.setDocument(new batasInput((int) 100).getKata(TdiscarKesBrom));
        Tpuasa.setDocument(new batasInput((int) 200).getKata(Tpuasa));
        Tminum.setDocument(new batasInput((int) 100).getKata(Tminum));
        Tbila.setDocument(new batasInput((int) 200).getKata(Tbila));
        Tanalgetik.setDocument(new batasInput((int) 200).getKata(Tanalgetik));
        Tantiemetik.setDocument(new batasInput((int) 200).getKata(Tantiemetik));
        TobatLain.setDocument(new batasInput((int) 200).getKata(TobatLain));
        TobatA.setDocument(new batasInput((int) 200).getKata(TobatA));
        TobatB.setDocument(new batasInput((int) 200).getKata(TobatB));        
        Tsistol.setDocument(new batasInput((int) 5).getKata(Tsistol));
        Tdistol.setDocument(new batasInput((int) 5).getKata(Tdistol));
        Tnadi.setDocument(new batasInput((int) 5).getKata(Tnadi));
        Trr.setDocument(new batasInput((int) 5).getKata(Trr));
        Tsuhu.setDocument(new batasInput((int) 5).getKata(Tsuhu));
        Tspo.setDocument(new batasInput((int) 5).getKata(Tspo));    
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
                if (akses.getform().equals("RMCatatanRuangPemulihan")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        nipDrAnes = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        TnmDrAnestesi.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDrAnestesi.requestFocus();
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
                if (akses.getform().equals("RMCatatanRuangPemulihan")) {
                    if (pilihPetugas == 1) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPenata = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPenataAnes.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPenataAnes.requestFocus();
                        }
                    } else if (pilihPetugas == 2) {
                        if (petugas.getTable().getSelectedRow() != -1) {
                            nipPerawat = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                            TnmPerawatBangsal.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                            BtnPerawatBangsal.requestFocus();
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
        jLabel86 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        TketJlnNafas = new widget.TextBox();
        jLabel71 = new widget.Label();
        TnmPerawatBangsal = new widget.TextBox();
        jLabel74 = new widget.Label();
        TnmDrAnestesi = new widget.TextBox();
        jLabel75 = new widget.Label();
        TnmPenataAnes = new widget.TextBox();
        BtnPerawatBangsal = new widget.Button();
        BtnDrAnestesi = new widget.Button();
        BtnPenataAnes = new widget.Button();
        cmbJam3 = new widget.ComboBox();
        cmbMnt3 = new widget.ComboBox();
        cmbDtk3 = new widget.ComboBox();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel276 = new widget.Label();
        jLabel277 = new widget.Label();
        cmbJam4 = new widget.ComboBox();
        cmbMnt4 = new widget.ComboBox();
        cmbDtk4 = new widget.ComboBox();
        jLabel278 = new widget.Label();
        chkSis15 = new widget.CekBox();
        cmbJlnNafas = new widget.ComboBox();
        jLabel89 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel279 = new widget.Label();
        jLabel79 = new widget.Label();
        jLabel280 = new widget.Label();
        Tsistol = new widget.TextBox();
        jLabel281 = new widget.Label();
        Tdistol = new widget.TextBox();
        jLabel282 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel283 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel284 = new widget.Label();
        jLabel285 = new widget.Label();
        Tspo = new widget.TextBox();
        Tsuhu = new widget.TextBox();
        Scroll2 = new widget.ScrollPane();
        tbTTV = new widget.Table();
        BtnBaruTtv = new widget.Button();
        BtnTambahTtv = new widget.Button();
        BtnHapusTtv = new widget.Button();
        BtnGantiTtv = new widget.Button();
        scrollPane13 = new widget.ScrollPane();
        Tinfus = new widget.TextArea();
        TtglSerah = new widget.Tanggal();
        cmbPernafasanCttn = new widget.ComboBox();
        jLabel94 = new widget.Label();
        cmbBila = new widget.ComboBox();
        jLabel96 = new widget.Label();
        cmbKesadaranCttn = new widget.ComboBox();
        jLabel97 = new widget.Label();
        jLabel98 = new widget.Label();
        jLabel87 = new widget.Label();
        TtdPreAnesAldret = new widget.TextBox();
        jLabel287 = new widget.Label();
        jLabel99 = new widget.Label();
        jLabel288 = new widget.Label();
        jLabel289 = new widget.Label();
        jLabel290 = new widget.Label();
        jLabel291 = new widget.Label();
        jLabel292 = new widget.Label();
        jLabel293 = new widget.Label();
        jLabel294 = new widget.Label();
        jLabel295 = new widget.Label();
        jLabel296 = new widget.Label();
        jLabel66 = new widget.Label();
        cmbSiskulasi = new widget.ComboBox();
        TskorSis = new widget.TextBox();
        chkSis30 = new widget.CekBox();
        chkSis45 = new widget.CekBox();
        chkSis60 = new widget.CekBox();
        chkSis90 = new widget.CekBox();
        chkSis120 = new widget.CekBox();
        chkSis150 = new widget.CekBox();
        TdiscarSis = new widget.TextBox();
        cmbKesadaranAldret = new widget.ComboBox();
        jLabel67 = new widget.Label();
        TskorKesAldret = new widget.TextBox();
        chkKesAld15 = new widget.CekBox();
        chkKesAld30 = new widget.CekBox();
        chkKesAld45 = new widget.CekBox();
        chkKesAld60 = new widget.CekBox();
        chkKesAld90 = new widget.CekBox();
        chkKesAld120 = new widget.CekBox();
        chkKesAld150 = new widget.CekBox();
        TdiscarKesAldret = new widget.TextBox();
        jLabel68 = new widget.Label();
        cmbPernafasanAldret = new widget.ComboBox();
        TskorPerAldret = new widget.TextBox();
        chkPerAld15 = new widget.CekBox();
        chkPerAld30 = new widget.CekBox();
        chkPerAld45 = new widget.CekBox();
        chkPerAld60 = new widget.CekBox();
        chkPerAld90 = new widget.CekBox();
        chkPerAld120 = new widget.CekBox();
        chkPerAld150 = new widget.CekBox();
        TdiscarPerAldret = new widget.TextBox();
        jLabel69 = new widget.Label();
        cmbWrnKulit = new widget.ComboBox();
        TskorWar = new widget.TextBox();
        chkWar15 = new widget.CekBox();
        chkWar30 = new widget.CekBox();
        chkWar45 = new widget.CekBox();
        chkWar60 = new widget.CekBox();
        chkWar90 = new widget.CekBox();
        chkWar120 = new widget.CekBox();
        chkWar150 = new widget.CekBox();
        TdiscarWar = new widget.TextBox();
        jLabel70 = new widget.Label();
        cmbAktifitasAldret = new widget.ComboBox();
        TskorAktAldret = new widget.TextBox();
        chkAktAld15 = new widget.CekBox();
        chkAktAld30 = new widget.CekBox();
        chkAktAld45 = new widget.CekBox();
        chkAktAld60 = new widget.CekBox();
        chkAktAld90 = new widget.CekBox();
        chkAktAld120 = new widget.CekBox();
        chkAktAld150 = new widget.CekBox();
        TdiscarAktAldret = new widget.TextBox();
        jLabel77 = new widget.Label();
        TskorTotAldret = new widget.TextBox();
        jLabel100 = new widget.Label();
        jLabel101 = new widget.Label();
        jLabel102 = new widget.Label();
        jLabel103 = new widget.Label();
        jLabel104 = new widget.Label();
        TtdPreAnesSte = new widget.TextBox();
        jLabel297 = new widget.Label();
        jLabel298 = new widget.Label();
        jLabel299 = new widget.Label();
        jLabel300 = new widget.Label();
        jLabel301 = new widget.Label();
        jLabel302 = new widget.Label();
        jLabel303 = new widget.Label();
        jLabel304 = new widget.Label();
        jLabel305 = new widget.Label();
        jLabel306 = new widget.Label();
        jLabel105 = new widget.Label();
        cmbKesadaranSte = new widget.ComboBox();
        TskorKesSte = new widget.TextBox();
        chkKesSte15 = new widget.CekBox();
        chkKesSte30 = new widget.CekBox();
        chkKesSte45 = new widget.CekBox();
        chkKesSte60 = new widget.CekBox();
        chkKesSte90 = new widget.CekBox();
        chkKesSte120 = new widget.CekBox();
        chkKesSte150 = new widget.CekBox();
        TdiscarKesSte = new widget.TextBox();
        jLabel106 = new widget.Label();
        cmbPernafasanSte = new widget.ComboBox();
        TskorPerSte = new widget.TextBox();
        chkPerSte15 = new widget.CekBox();
        chkPerSte30 = new widget.CekBox();
        chkPerSte45 = new widget.CekBox();
        chkPerSte60 = new widget.CekBox();
        chkPerSte90 = new widget.CekBox();
        chkPerSte120 = new widget.CekBox();
        chkPerSte150 = new widget.CekBox();
        TdiscarPerSte = new widget.TextBox();
        jLabel107 = new widget.Label();
        cmbAktifitasSte = new widget.ComboBox();
        TskorAktSte = new widget.TextBox();
        chkAktSte15 = new widget.CekBox();
        chkAktSte30 = new widget.CekBox();
        chkAktSte45 = new widget.CekBox();
        chkAktSte60 = new widget.CekBox();
        chkAktSte90 = new widget.CekBox();
        chkAktSte120 = new widget.CekBox();
        chkAktSte150 = new widget.CekBox();
        TdiscarAktSte = new widget.TextBox();
        jLabel108 = new widget.Label();
        TskorTotSte = new widget.TextBox();
        jLabel109 = new widget.Label();
        jLabel110 = new widget.Label();
        jLabel111 = new widget.Label();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        TtdPreAnesBrom = new widget.TextBox();
        jLabel307 = new widget.Label();
        jLabel308 = new widget.Label();
        jLabel309 = new widget.Label();
        jLabel310 = new widget.Label();
        jLabel311 = new widget.Label();
        jLabel312 = new widget.Label();
        jLabel313 = new widget.Label();
        jLabel314 = new widget.Label();
        jLabel315 = new widget.Label();
        jLabel316 = new widget.Label();
        jLabel114 = new widget.Label();
        cmbKesadaranBrom = new widget.ComboBox();
        TskorKesBrom = new widget.TextBox();
        chkKesBro15 = new widget.CekBox();
        chkKesBro30 = new widget.CekBox();
        chkKesBro45 = new widget.CekBox();
        chkKesBro60 = new widget.CekBox();
        chkKesBro90 = new widget.CekBox();
        chkKesBro120 = new widget.CekBox();
        chkKesBro150 = new widget.CekBox();
        TdiscarKesBrom = new widget.TextBox();
        jLabel116 = new widget.Label();
        jLabel117 = new widget.Label();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        Tpuasa = new widget.TextBox();
        jLabel120 = new widget.Label();
        Tminum = new widget.TextBox();
        jLabel121 = new widget.Label();
        Tbila = new widget.TextBox();
        jLabel122 = new widget.Label();
        Tanalgetik = new widget.TextBox();
        jLabel123 = new widget.Label();
        Tantiemetik = new widget.TextBox();
        jLabel124 = new widget.Label();
        TobatA = new widget.TextBox();
        jLabel125 = new widget.Label();
        TobatB = new widget.TextBox();
        jLabel126 = new widget.Label();
        cmbPasienBoleh = new widget.ComboBox();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tlainlain = new widget.TextArea();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        scrollPane15 = new widget.ScrollPane();
        Tposisi = new widget.TextArea();
        scrollPane16 = new widget.ScrollPane();
        Tkateter = new widget.TextArea();
        scrollPane17 = new widget.ScrollPane();
        Tinstruksi = new widget.TextArea();
        jLabel131 = new widget.Label();
        TobatLain = new widget.TextBox();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCatatan = new widget.Table();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Catatan Ruang Pemulihan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 47));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
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
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 1622));
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

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Jam Masuk :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 66, 140, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Jalan Nafas :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 94, 140, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Pernafasan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 122, 140, 23);

        TketJlnNafas.setForeground(new java.awt.Color(0, 0, 0));
        TketJlnNafas.setName("TketJlnNafas"); // NOI18N
        TketJlnNafas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketJlnNafasKeyPressed(evt);
            }
        });
        FormInput.add(TketJlnNafas);
        TketJlnNafas.setBounds(285, 94, 475, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Perawat Bangsal :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 1582, 170, 23);

        TnmPerawatBangsal.setEditable(false);
        TnmPerawatBangsal.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawatBangsal.setName("TnmPerawatBangsal"); // NOI18N
        FormInput.add(TnmPerawatBangsal);
        TnmPerawatBangsal.setBounds(175, 1582, 410, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Dokter Anestesi :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 1526, 170, 23);

        TnmDrAnestesi.setEditable(false);
        TnmDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        TnmDrAnestesi.setName("TnmDrAnestesi"); // NOI18N
        FormInput.add(TnmDrAnestesi);
        TnmDrAnestesi.setBounds(175, 1526, 410, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Penata Anestesi / Perawat RR :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(0, 1554, 170, 23);

        TnmPenataAnes.setEditable(false);
        TnmPenataAnes.setForeground(new java.awt.Color(0, 0, 0));
        TnmPenataAnes.setName("TnmPenataAnes"); // NOI18N
        FormInput.add(TnmPenataAnes);
        TnmPenataAnes.setBounds(175, 1554, 410, 23);

        BtnPerawatBangsal.setForeground(new java.awt.Color(0, 0, 0));
        BtnPerawatBangsal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPerawatBangsal.setToolTipText("Alt+1");
        BtnPerawatBangsal.setName("BtnPerawatBangsal"); // NOI18N
        BtnPerawatBangsal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPerawatBangsalActionPerformed(evt);
            }
        });
        FormInput.add(BtnPerawatBangsal);
        BtnPerawatBangsal.setBounds(590, 1582, 28, 23);

        BtnDrAnestesi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDrAnestesi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDrAnestesi.setToolTipText("Alt+1");
        BtnDrAnestesi.setName("BtnDrAnestesi"); // NOI18N
        BtnDrAnestesi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDrAnestesiActionPerformed(evt);
            }
        });
        FormInput.add(BtnDrAnestesi);
        BtnDrAnestesi.setBounds(590, 1526, 28, 23);

        BtnPenataAnes.setForeground(new java.awt.Color(0, 0, 0));
        BtnPenataAnes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPenataAnes.setToolTipText("Alt+1");
        BtnPenataAnes.setName("BtnPenataAnes"); // NOI18N
        BtnPenataAnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPenataAnesActionPerformed(evt);
            }
        });
        FormInput.add(BtnPenataAnes);
        BtnPenataAnes.setBounds(590, 1554, 28, 23);

        cmbJam3.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam3.setName("cmbJam3"); // NOI18N
        cmbJam3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam3MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam3);
        cmbJam3.setBounds(350, 1324, 45, 23);

        cmbMnt3.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt3.setName("cmbMnt3"); // NOI18N
        cmbMnt3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt3MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt3);
        cmbMnt3.setBounds(402, 1324, 45, 23);

        cmbDtk3.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk3.setName("cmbDtk3"); // NOI18N
        cmbDtk3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk3MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk3);
        cmbDtk3.setBounds(455, 1324, 45, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(145, 66, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(198, 66, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(250, 66, 45, 23);

        jLabel276.setForeground(new java.awt.Color(0, 0, 0));
        jLabel276.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel276.setText("Wita");
        jLabel276.setName("jLabel276"); // NOI18N
        FormInput.add(jLabel276);
        jLabel276.setBounds(302, 66, 40, 23);

        jLabel277.setForeground(new java.awt.Color(0, 0, 0));
        jLabel277.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel277.setText("Wita");
        jLabel277.setName("jLabel277"); // NOI18N
        FormInput.add(jLabel277);
        jLabel277.setBounds(508, 1324, 50, 23);

        cmbJam4.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam4.setName("cmbJam4"); // NOI18N
        cmbJam4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam4MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam4);
        cmbJam4.setBounds(350, 1498, 45, 23);

        cmbMnt4.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt4.setName("cmbMnt4"); // NOI18N
        cmbMnt4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt4MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt4);
        cmbMnt4.setBounds(400, 1498, 45, 23);

        cmbDtk4.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk4.setName("cmbDtk4"); // NOI18N
        cmbDtk4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk4MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk4);
        cmbDtk4.setBounds(450, 1498, 45, 23);

        jLabel278.setForeground(new java.awt.Color(0, 0, 0));
        jLabel278.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel278.setText("Wita");
        jLabel278.setName("jLabel278"); // NOI18N
        FormInput.add(jLabel278);
        jLabel278.setBounds(502, 1498, 50, 23);

        chkSis15.setBackground(new java.awt.Color(255, 255, 250));
        chkSis15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis15.setForeground(new java.awt.Color(0, 0, 0));
        chkSis15.setBorderPainted(true);
        chkSis15.setBorderPaintedFlat(true);
        chkSis15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis15.setName("chkSis15"); // NOI18N
        chkSis15.setOpaque(false);
        chkSis15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis15);
        chkSis15.setBounds(430, 504, 28, 23);

        cmbJlnNafas.setBackground(new java.awt.Color(245, 253, 240));
        cmbJlnNafas.setForeground(new java.awt.Color(0, 0, 0));
        cmbJlnNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bersih dan lapang", "Terpasang alat bantu" }));
        cmbJlnNafas.setLightWeightPopupEnabled(false);
        cmbJlnNafas.setName("cmbJlnNafas"); // NOI18N
        cmbJlnNafas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJlnNafasActionPerformed(evt);
            }
        });
        FormInput.add(cmbJlnNafas);
        cmbJlnNafas.setBounds(145, 94, 135, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("OBSERVASI TANDA VITAL :");
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 150, 180, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Pukul :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(0, 178, 140, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(145, 178, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(198, 178, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(250, 178, 45, 23);

        jLabel279.setForeground(new java.awt.Color(0, 0, 0));
        jLabel279.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel279.setText("Wita");
        jLabel279.setName("jLabel279"); // NOI18N
        FormInput.add(jLabel279);
        jLabel279.setBounds(302, 178, 50, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Tekanan Darah :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 206, 140, 23);

        jLabel280.setForeground(new java.awt.Color(0, 0, 0));
        jLabel280.setText("Sistole :");
        jLabel280.setName("jLabel280"); // NOI18N
        FormInput.add(jLabel280);
        jLabel280.setBounds(145, 206, 50, 23);

        Tsistol.setForeground(new java.awt.Color(0, 0, 0));
        Tsistol.setName("Tsistol"); // NOI18N
        Tsistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsistolKeyPressed(evt);
            }
        });
        FormInput.add(Tsistol);
        Tsistol.setBounds(200, 206, 60, 23);

        jLabel281.setForeground(new java.awt.Color(0, 0, 0));
        jLabel281.setText("Diastole :");
        jLabel281.setName("jLabel281"); // NOI18N
        FormInput.add(jLabel281);
        jLabel281.setBounds(125, 234, 70, 23);

        Tdistol.setForeground(new java.awt.Color(0, 0, 0));
        Tdistol.setName("Tdistol"); // NOI18N
        Tdistol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdistolKeyPressed(evt);
            }
        });
        FormInput.add(Tdistol);
        Tdistol.setBounds(200, 234, 60, 23);

        jLabel282.setForeground(new java.awt.Color(0, 0, 0));
        jLabel282.setText("Nadi :");
        jLabel282.setName("jLabel282"); // NOI18N
        FormInput.add(jLabel282);
        jLabel282.setBounds(265, 206, 60, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        FormInput.add(Tnadi);
        Tnadi.setBounds(330, 206, 60, 23);

        jLabel283.setForeground(new java.awt.Color(0, 0, 0));
        jLabel283.setText("RR :");
        jLabel283.setName("jLabel283"); // NOI18N
        FormInput.add(jLabel283);
        jLabel283.setBounds(265, 234, 60, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(330, 234, 60, 23);

        jLabel284.setForeground(new java.awt.Color(0, 0, 0));
        jLabel284.setText("Suhu :");
        jLabel284.setName("jLabel284"); // NOI18N
        FormInput.add(jLabel284);
        jLabel284.setBounds(390, 206, 60, 23);

        jLabel285.setForeground(new java.awt.Color(0, 0, 0));
        jLabel285.setText("SpO2 :");
        jLabel285.setName("jLabel285"); // NOI18N
        FormInput.add(jLabel285);
        jLabel285.setBounds(390, 234, 60, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        FormInput.add(Tspo);
        Tspo.setBounds(456, 234, 60, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        FormInput.add(Tsuhu);
        Tsuhu.setBounds(456, 206, 60, 23);

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbTTV.setName("tbTTV"); // NOI18N
        tbTTV.getTableHeader().setReorderingAllowed(false);
        tbTTV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTTVMouseClicked(evt);
            }
        });
        tbTTV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbTTVKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbTTV);

        FormInput.add(Scroll2);
        Scroll2.setBounds(145, 265, 500, 150);

        BtnBaruTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaruTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaruTtv.setText("Baru");
        BtnBaruTtv.setToolTipText("Data DJJ Baru");
        BtnBaruTtv.setName("BtnBaruTtv"); // NOI18N
        BtnBaruTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnBaruTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnBaruTtv);
        BtnBaruTtv.setBounds(660, 265, 90, 30);

        BtnTambahTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnTambahTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambahTtv.setText("Tambah");
        BtnTambahTtv.setToolTipText("Tambah Data Air Ketuban/Mulase");
        BtnTambahTtv.setName("BtnTambahTtv"); // NOI18N
        BtnTambahTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnTambahTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnTambahTtv);
        BtnTambahTtv.setBounds(660, 304, 90, 30);

        BtnHapusTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        BtnHapusTtv.setText("Hapus");
        BtnHapusTtv.setToolTipText("Hapus Air Ketuban/Mulase");
        BtnHapusTtv.setName("BtnHapusTtv"); // NOI18N
        BtnHapusTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnHapusTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnHapusTtv);
        BtnHapusTtv.setBounds(660, 343, 90, 30);

        BtnGantiTtv.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiTtv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiTtv.setText("Ganti");
        BtnGantiTtv.setToolTipText("Ganti Air Ketuban/Mulase");
        BtnGantiTtv.setName("BtnGantiTtv"); // NOI18N
        BtnGantiTtv.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnGantiTtv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiTtvActionPerformed(evt);
            }
        });
        FormInput.add(BtnGantiTtv);
        BtnGantiTtv.setBounds(660, 382, 90, 30);

        scrollPane13.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane13.setName("scrollPane13"); // NOI18N

        Tinfus.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinfus.setColumns(20);
        Tinfus.setRows(5);
        Tinfus.setName("Tinfus"); // NOI18N
        Tinfus.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tinfus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TinfusKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tinfus);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(135, 1036, 500, 60);

        TtglSerah.setEditable(false);
        TtglSerah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-05-2026" }));
        TtglSerah.setDisplayFormat("dd-MM-yyyy");
        TtglSerah.setName("TtglSerah"); // NOI18N
        TtglSerah.setOpaque(false);
        TtglSerah.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglSerah);
        TtglSerah.setBounds(175, 1498, 90, 23);

        cmbPernafasanCttn.setBackground(new java.awt.Color(245, 253, 240));
        cmbPernafasanCttn.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernafasanCttn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Spontan", "Dibantu" }));
        cmbPernafasanCttn.setLightWeightPopupEnabled(false);
        cmbPernafasanCttn.setName("cmbPernafasanCttn"); // NOI18N
        cmbPernafasanCttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernafasanCttnActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernafasanCttn);
        cmbPernafasanCttn.setBounds(145, 122, 75, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("Bila Spontan :");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(220, 122, 90, 23);

        cmbBila.setBackground(new java.awt.Color(245, 253, 240));
        cmbBila.setForeground(new java.awt.Color(0, 0, 0));
        cmbBila.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Adekuat bersuara", "Penyumbatan", "Membutuhkan bantuan alat" }));
        cmbBila.setLightWeightPopupEnabled(false);
        cmbBila.setName("cmbBila"); // NOI18N
        cmbBila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBilaActionPerformed(evt);
            }
        });
        FormInput.add(cmbBila);
        cmbBila.setBounds(317, 122, 165, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Kesadaran :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(485, 122, 85, 23);

        cmbKesadaranCttn.setBackground(new java.awt.Color(245, 253, 240));
        cmbKesadaranCttn.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaranCttn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar betul", "Belum sadar", "Tidur dalam" }));
        cmbKesadaranCttn.setLightWeightPopupEnabled(false);
        cmbKesadaranCttn.setName("cmbKesadaranCttn"); // NOI18N
        cmbKesadaranCttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesadaranCttnActionPerformed(evt);
            }
        });
        FormInput.add(cmbKesadaranCttn);
        cmbKesadaranCttn.setBounds(577, 122, 90, 23);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("ALDRETTE SCORE (DEWASA) PASCA GENERAL ANESTESI (GA) :");
        jLabel97.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(0, 420, 380, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Post Anesteshia Score");
        jLabel98.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(0, 448, 250, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Pre Anestesi Vital Sign Source / TD Pre Anestesi :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(0, 476, 270, 23);

        TtdPreAnesAldret.setForeground(new java.awt.Color(0, 0, 0));
        TtdPreAnesAldret.setName("TtdPreAnesAldret"); // NOI18N
        TtdPreAnesAldret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdPreAnesAldretKeyPressed(evt);
            }
        });
        FormInput.add(TtdPreAnesAldret);
        TtdPreAnesAldret.setBounds(275, 476, 70, 23);

        jLabel287.setForeground(new java.awt.Color(0, 0, 0));
        jLabel287.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel287.setText("mmHg");
        jLabel287.setName("jLabel287"); // NOI18N
        FormInput.add(jLabel287);
        jLabel287.setBounds(350, 476, 40, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Waktu (menit)");
        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(390, 448, 210, 23);

        jLabel288.setForeground(new java.awt.Color(0, 0, 0));
        jLabel288.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel288.setText("Skor");
        jLabel288.setName("jLabel288"); // NOI18N
        FormInput.add(jLabel288);
        jLabel288.setBounds(390, 476, 34, 23);

        jLabel289.setForeground(new java.awt.Color(0, 0, 0));
        jLabel289.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel289.setText("15\"");
        jLabel289.setName("jLabel289"); // NOI18N
        FormInput.add(jLabel289);
        jLabel289.setBounds(430, 476, 34, 23);

        jLabel290.setForeground(new java.awt.Color(0, 0, 0));
        jLabel290.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel290.setText("30\"");
        jLabel290.setName("jLabel290"); // NOI18N
        FormInput.add(jLabel290);
        jLabel290.setBounds(470, 476, 34, 23);

        jLabel291.setForeground(new java.awt.Color(0, 0, 0));
        jLabel291.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel291.setText("45\"");
        jLabel291.setName("jLabel291"); // NOI18N
        FormInput.add(jLabel291);
        jLabel291.setBounds(510, 476, 34, 23);

        jLabel292.setForeground(new java.awt.Color(0, 0, 0));
        jLabel292.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel292.setText("60\"");
        jLabel292.setName("jLabel292"); // NOI18N
        FormInput.add(jLabel292);
        jLabel292.setBounds(550, 476, 34, 23);

        jLabel293.setForeground(new java.awt.Color(0, 0, 0));
        jLabel293.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel293.setText("90\"");
        jLabel293.setName("jLabel293"); // NOI18N
        FormInput.add(jLabel293);
        jLabel293.setBounds(590, 476, 34, 23);

        jLabel294.setForeground(new java.awt.Color(0, 0, 0));
        jLabel294.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel294.setText("120\"");
        jLabel294.setName("jLabel294"); // NOI18N
        FormInput.add(jLabel294);
        jLabel294.setBounds(630, 476, 34, 23);

        jLabel295.setForeground(new java.awt.Color(0, 0, 0));
        jLabel295.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel295.setText("150\"");
        jLabel295.setName("jLabel295"); // NOI18N
        FormInput.add(jLabel295);
        jLabel295.setBounds(670, 476, 34, 23);

        jLabel296.setForeground(new java.awt.Color(0, 0, 0));
        jLabel296.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel296.setText("Discharge");
        jLabel296.setName("jLabel296"); // NOI18N
        FormInput.add(jLabel296);
        jLabel296.setBounds(710, 476, 80, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Siskulasi :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 504, 130, 23);

        cmbSiskulasi.setBackground(new java.awt.Color(245, 253, 240));
        cmbSiskulasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbSiskulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "TD sistolik 20% mmHg dari pre anestesi", "20 - 50 %", "> 50 %" }));
        cmbSiskulasi.setLightWeightPopupEnabled(false);
        cmbSiskulasi.setName("cmbSiskulasi"); // NOI18N
        cmbSiskulasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSiskulasiActionPerformed(evt);
            }
        });
        FormInput.add(cmbSiskulasi);
        cmbSiskulasi.setBounds(135, 504, 220, 23);

        TskorSis.setEditable(false);
        TskorSis.setForeground(new java.awt.Color(0, 0, 0));
        TskorSis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorSis.setName("TskorSis"); // NOI18N
        FormInput.add(TskorSis);
        TskorSis.setBounds(390, 504, 34, 23);

        chkSis30.setBackground(new java.awt.Color(255, 255, 250));
        chkSis30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis30.setForeground(new java.awt.Color(0, 0, 0));
        chkSis30.setBorderPainted(true);
        chkSis30.setBorderPaintedFlat(true);
        chkSis30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis30.setName("chkSis30"); // NOI18N
        chkSis30.setOpaque(false);
        chkSis30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis30);
        chkSis30.setBounds(470, 504, 28, 23);

        chkSis45.setBackground(new java.awt.Color(255, 255, 250));
        chkSis45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis45.setForeground(new java.awt.Color(0, 0, 0));
        chkSis45.setBorderPainted(true);
        chkSis45.setBorderPaintedFlat(true);
        chkSis45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis45.setName("chkSis45"); // NOI18N
        chkSis45.setOpaque(false);
        chkSis45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis45);
        chkSis45.setBounds(510, 504, 28, 23);

        chkSis60.setBackground(new java.awt.Color(255, 255, 250));
        chkSis60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis60.setForeground(new java.awt.Color(0, 0, 0));
        chkSis60.setBorderPainted(true);
        chkSis60.setBorderPaintedFlat(true);
        chkSis60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis60.setName("chkSis60"); // NOI18N
        chkSis60.setOpaque(false);
        chkSis60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis60);
        chkSis60.setBounds(550, 504, 28, 23);

        chkSis90.setBackground(new java.awt.Color(255, 255, 250));
        chkSis90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis90.setForeground(new java.awt.Color(0, 0, 0));
        chkSis90.setBorderPainted(true);
        chkSis90.setBorderPaintedFlat(true);
        chkSis90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis90.setName("chkSis90"); // NOI18N
        chkSis90.setOpaque(false);
        chkSis90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis90);
        chkSis90.setBounds(590, 504, 28, 23);

        chkSis120.setBackground(new java.awt.Color(255, 255, 250));
        chkSis120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis120.setForeground(new java.awt.Color(0, 0, 0));
        chkSis120.setBorderPainted(true);
        chkSis120.setBorderPaintedFlat(true);
        chkSis120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis120.setName("chkSis120"); // NOI18N
        chkSis120.setOpaque(false);
        chkSis120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis120);
        chkSis120.setBounds(630, 504, 28, 23);

        chkSis150.setBackground(new java.awt.Color(255, 255, 250));
        chkSis150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSis150.setForeground(new java.awt.Color(0, 0, 0));
        chkSis150.setBorderPainted(true);
        chkSis150.setBorderPaintedFlat(true);
        chkSis150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkSis150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkSis150.setName("chkSis150"); // NOI18N
        chkSis150.setOpaque(false);
        chkSis150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkSis150);
        chkSis150.setBounds(670, 504, 28, 23);

        TdiscarSis.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarSis.setName("TdiscarSis"); // NOI18N
        TdiscarSis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarSisKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarSis);
        TdiscarSis.setBounds(710, 504, 80, 23);

        cmbKesadaranAldret.setBackground(new java.awt.Color(245, 253, 240));
        cmbKesadaranAldret.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaranAldret.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar penuh", "Respon/bangun terhadap panggilan", "Belum/tidak ada respon" }));
        cmbKesadaranAldret.setLightWeightPopupEnabled(false);
        cmbKesadaranAldret.setName("cmbKesadaranAldret"); // NOI18N
        cmbKesadaranAldret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesadaranAldretActionPerformed(evt);
            }
        });
        FormInput.add(cmbKesadaranAldret);
        cmbKesadaranAldret.setBounds(135, 532, 205, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Kesadaran :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 532, 130, 23);

        TskorKesAldret.setEditable(false);
        TskorKesAldret.setForeground(new java.awt.Color(0, 0, 0));
        TskorKesAldret.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKesAldret.setName("TskorKesAldret"); // NOI18N
        FormInput.add(TskorKesAldret);
        TskorKesAldret.setBounds(390, 532, 34, 23);

        chkKesAld15.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld15.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld15.setBorderPainted(true);
        chkKesAld15.setBorderPaintedFlat(true);
        chkKesAld15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld15.setName("chkKesAld15"); // NOI18N
        chkKesAld15.setOpaque(false);
        chkKesAld15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld15);
        chkKesAld15.setBounds(430, 532, 28, 23);

        chkKesAld30.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld30.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld30.setBorderPainted(true);
        chkKesAld30.setBorderPaintedFlat(true);
        chkKesAld30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld30.setName("chkKesAld30"); // NOI18N
        chkKesAld30.setOpaque(false);
        chkKesAld30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld30);
        chkKesAld30.setBounds(470, 532, 28, 23);

        chkKesAld45.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld45.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld45.setBorderPainted(true);
        chkKesAld45.setBorderPaintedFlat(true);
        chkKesAld45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld45.setName("chkKesAld45"); // NOI18N
        chkKesAld45.setOpaque(false);
        chkKesAld45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld45);
        chkKesAld45.setBounds(510, 532, 28, 23);

        chkKesAld60.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld60.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld60.setBorderPainted(true);
        chkKesAld60.setBorderPaintedFlat(true);
        chkKesAld60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld60.setName("chkKesAld60"); // NOI18N
        chkKesAld60.setOpaque(false);
        chkKesAld60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld60);
        chkKesAld60.setBounds(550, 532, 28, 23);

        chkKesAld90.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld90.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld90.setBorderPainted(true);
        chkKesAld90.setBorderPaintedFlat(true);
        chkKesAld90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld90.setName("chkKesAld90"); // NOI18N
        chkKesAld90.setOpaque(false);
        chkKesAld90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld90);
        chkKesAld90.setBounds(590, 532, 28, 23);

        chkKesAld120.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld120.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld120.setBorderPainted(true);
        chkKesAld120.setBorderPaintedFlat(true);
        chkKesAld120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld120.setName("chkKesAld120"); // NOI18N
        chkKesAld120.setOpaque(false);
        chkKesAld120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld120);
        chkKesAld120.setBounds(630, 532, 28, 23);

        chkKesAld150.setBackground(new java.awt.Color(255, 255, 250));
        chkKesAld150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesAld150.setForeground(new java.awt.Color(0, 0, 0));
        chkKesAld150.setBorderPainted(true);
        chkKesAld150.setBorderPaintedFlat(true);
        chkKesAld150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesAld150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesAld150.setName("chkKesAld150"); // NOI18N
        chkKesAld150.setOpaque(false);
        chkKesAld150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesAld150);
        chkKesAld150.setBounds(670, 532, 28, 23);

        TdiscarKesAldret.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarKesAldret.setName("TdiscarKesAldret"); // NOI18N
        TdiscarKesAldret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarKesAldretKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarKesAldret);
        TdiscarKesAldret.setBounds(710, 532, 80, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Pernafasan :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 560, 130, 23);

        cmbPernafasanAldret.setBackground(new java.awt.Color(245, 253, 240));
        cmbPernafasanAldret.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernafasanAldret.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Bisa tarik napas dalam dan batuk bebas", "Sesak atau bernafas dengan periode apneu", "Apneu/tidak bernafas" }));
        cmbPernafasanAldret.setLightWeightPopupEnabled(false);
        cmbPernafasanAldret.setName("cmbPernafasanAldret"); // NOI18N
        cmbPernafasanAldret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernafasanAldretActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernafasanAldret);
        cmbPernafasanAldret.setBounds(135, 560, 245, 23);

        TskorPerAldret.setEditable(false);
        TskorPerAldret.setForeground(new java.awt.Color(0, 0, 0));
        TskorPerAldret.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorPerAldret.setName("TskorPerAldret"); // NOI18N
        FormInput.add(TskorPerAldret);
        TskorPerAldret.setBounds(390, 560, 34, 23);

        chkPerAld15.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld15.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld15.setBorderPainted(true);
        chkPerAld15.setBorderPaintedFlat(true);
        chkPerAld15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld15.setName("chkPerAld15"); // NOI18N
        chkPerAld15.setOpaque(false);
        chkPerAld15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld15);
        chkPerAld15.setBounds(430, 560, 28, 23);

        chkPerAld30.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld30.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld30.setBorderPainted(true);
        chkPerAld30.setBorderPaintedFlat(true);
        chkPerAld30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld30.setName("chkPerAld30"); // NOI18N
        chkPerAld30.setOpaque(false);
        chkPerAld30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld30);
        chkPerAld30.setBounds(470, 560, 28, 23);

        chkPerAld45.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld45.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld45.setBorderPainted(true);
        chkPerAld45.setBorderPaintedFlat(true);
        chkPerAld45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld45.setName("chkPerAld45"); // NOI18N
        chkPerAld45.setOpaque(false);
        chkPerAld45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld45);
        chkPerAld45.setBounds(510, 560, 28, 23);

        chkPerAld60.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld60.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld60.setBorderPainted(true);
        chkPerAld60.setBorderPaintedFlat(true);
        chkPerAld60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld60.setName("chkPerAld60"); // NOI18N
        chkPerAld60.setOpaque(false);
        chkPerAld60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld60);
        chkPerAld60.setBounds(550, 560, 28, 23);

        chkPerAld90.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld90.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld90.setBorderPainted(true);
        chkPerAld90.setBorderPaintedFlat(true);
        chkPerAld90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld90.setName("chkPerAld90"); // NOI18N
        chkPerAld90.setOpaque(false);
        chkPerAld90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld90);
        chkPerAld90.setBounds(590, 560, 28, 23);

        chkPerAld120.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld120.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld120.setBorderPainted(true);
        chkPerAld120.setBorderPaintedFlat(true);
        chkPerAld120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld120.setName("chkPerAld120"); // NOI18N
        chkPerAld120.setOpaque(false);
        chkPerAld120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld120);
        chkPerAld120.setBounds(630, 560, 28, 23);

        chkPerAld150.setBackground(new java.awt.Color(255, 255, 250));
        chkPerAld150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerAld150.setForeground(new java.awt.Color(0, 0, 0));
        chkPerAld150.setBorderPainted(true);
        chkPerAld150.setBorderPaintedFlat(true);
        chkPerAld150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerAld150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerAld150.setName("chkPerAld150"); // NOI18N
        chkPerAld150.setOpaque(false);
        chkPerAld150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerAld150);
        chkPerAld150.setBounds(670, 560, 28, 23);

        TdiscarPerAldret.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarPerAldret.setName("TdiscarPerAldret"); // NOI18N
        TdiscarPerAldret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarPerAldretKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarPerAldret);
        TdiscarPerAldret.setBounds(710, 560, 80, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Warna Kulit :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 588, 130, 23);

        cmbWrnKulit.setBackground(new java.awt.Color(245, 253, 240));
        cmbWrnKulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbWrnKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Hangat, kulit kering, warna merah muda", "Pucat, dingin", "Sianosis" }));
        cmbWrnKulit.setLightWeightPopupEnabled(false);
        cmbWrnKulit.setName("cmbWrnKulit"); // NOI18N
        cmbWrnKulit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWrnKulitActionPerformed(evt);
            }
        });
        FormInput.add(cmbWrnKulit);
        cmbWrnKulit.setBounds(135, 588, 225, 23);

        TskorWar.setEditable(false);
        TskorWar.setForeground(new java.awt.Color(0, 0, 0));
        TskorWar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorWar.setName("TskorWar"); // NOI18N
        FormInput.add(TskorWar);
        TskorWar.setBounds(390, 588, 34, 23);

        chkWar15.setBackground(new java.awt.Color(255, 255, 250));
        chkWar15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar15.setForeground(new java.awt.Color(0, 0, 0));
        chkWar15.setBorderPainted(true);
        chkWar15.setBorderPaintedFlat(true);
        chkWar15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar15.setName("chkWar15"); // NOI18N
        chkWar15.setOpaque(false);
        chkWar15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar15);
        chkWar15.setBounds(430, 588, 28, 23);

        chkWar30.setBackground(new java.awt.Color(255, 255, 250));
        chkWar30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar30.setForeground(new java.awt.Color(0, 0, 0));
        chkWar30.setBorderPainted(true);
        chkWar30.setBorderPaintedFlat(true);
        chkWar30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar30.setName("chkWar30"); // NOI18N
        chkWar30.setOpaque(false);
        chkWar30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar30);
        chkWar30.setBounds(470, 588, 28, 23);

        chkWar45.setBackground(new java.awt.Color(255, 255, 250));
        chkWar45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar45.setForeground(new java.awt.Color(0, 0, 0));
        chkWar45.setBorderPainted(true);
        chkWar45.setBorderPaintedFlat(true);
        chkWar45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar45.setName("chkWar45"); // NOI18N
        chkWar45.setOpaque(false);
        chkWar45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar45);
        chkWar45.setBounds(510, 588, 28, 23);

        chkWar60.setBackground(new java.awt.Color(255, 255, 250));
        chkWar60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar60.setForeground(new java.awt.Color(0, 0, 0));
        chkWar60.setBorderPainted(true);
        chkWar60.setBorderPaintedFlat(true);
        chkWar60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar60.setName("chkWar60"); // NOI18N
        chkWar60.setOpaque(false);
        chkWar60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar60);
        chkWar60.setBounds(550, 588, 28, 23);

        chkWar90.setBackground(new java.awt.Color(255, 255, 250));
        chkWar90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar90.setForeground(new java.awt.Color(0, 0, 0));
        chkWar90.setBorderPainted(true);
        chkWar90.setBorderPaintedFlat(true);
        chkWar90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar90.setName("chkWar90"); // NOI18N
        chkWar90.setOpaque(false);
        chkWar90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar90);
        chkWar90.setBounds(590, 588, 28, 23);

        chkWar120.setBackground(new java.awt.Color(255, 255, 250));
        chkWar120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar120.setForeground(new java.awt.Color(0, 0, 0));
        chkWar120.setBorderPainted(true);
        chkWar120.setBorderPaintedFlat(true);
        chkWar120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar120.setName("chkWar120"); // NOI18N
        chkWar120.setOpaque(false);
        chkWar120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar120);
        chkWar120.setBounds(630, 588, 28, 23);

        chkWar150.setBackground(new java.awt.Color(255, 255, 250));
        chkWar150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkWar150.setForeground(new java.awt.Color(0, 0, 0));
        chkWar150.setBorderPainted(true);
        chkWar150.setBorderPaintedFlat(true);
        chkWar150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkWar150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkWar150.setName("chkWar150"); // NOI18N
        chkWar150.setOpaque(false);
        chkWar150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkWar150);
        chkWar150.setBounds(670, 588, 28, 23);

        TdiscarWar.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarWar.setName("TdiscarWar"); // NOI18N
        TdiscarWar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarWarKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarWar);
        TdiscarWar.setBounds(710, 588, 80, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Aktifitas :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 616, 130, 23);

        cmbAktifitasAldret.setBackground(new java.awt.Color(245, 253, 240));
        cmbAktifitasAldret.setForeground(new java.awt.Color(0, 0, 0));
        cmbAktifitasAldret.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Menggerakan 4 ekstremitas", "Menggerakan 2 ekstremitas", "Tidak mampu menggerakkan ekstremitas" }));
        cmbAktifitasAldret.setLightWeightPopupEnabled(false);
        cmbAktifitasAldret.setName("cmbAktifitasAldret"); // NOI18N
        cmbAktifitasAldret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAktifitasAldretActionPerformed(evt);
            }
        });
        FormInput.add(cmbAktifitasAldret);
        cmbAktifitasAldret.setBounds(135, 616, 225, 23);

        TskorAktAldret.setEditable(false);
        TskorAktAldret.setForeground(new java.awt.Color(0, 0, 0));
        TskorAktAldret.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorAktAldret.setName("TskorAktAldret"); // NOI18N
        FormInput.add(TskorAktAldret);
        TskorAktAldret.setBounds(390, 616, 34, 23);

        chkAktAld15.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld15.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld15.setBorderPainted(true);
        chkAktAld15.setBorderPaintedFlat(true);
        chkAktAld15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld15.setName("chkAktAld15"); // NOI18N
        chkAktAld15.setOpaque(false);
        chkAktAld15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld15);
        chkAktAld15.setBounds(430, 616, 28, 23);

        chkAktAld30.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld30.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld30.setBorderPainted(true);
        chkAktAld30.setBorderPaintedFlat(true);
        chkAktAld30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld30.setName("chkAktAld30"); // NOI18N
        chkAktAld30.setOpaque(false);
        chkAktAld30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld30);
        chkAktAld30.setBounds(470, 616, 28, 23);

        chkAktAld45.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld45.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld45.setBorderPainted(true);
        chkAktAld45.setBorderPaintedFlat(true);
        chkAktAld45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld45.setName("chkAktAld45"); // NOI18N
        chkAktAld45.setOpaque(false);
        chkAktAld45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld45);
        chkAktAld45.setBounds(510, 616, 28, 23);

        chkAktAld60.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld60.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld60.setBorderPainted(true);
        chkAktAld60.setBorderPaintedFlat(true);
        chkAktAld60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld60.setName("chkAktAld60"); // NOI18N
        chkAktAld60.setOpaque(false);
        chkAktAld60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld60);
        chkAktAld60.setBounds(550, 616, 28, 23);

        chkAktAld90.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld90.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld90.setBorderPainted(true);
        chkAktAld90.setBorderPaintedFlat(true);
        chkAktAld90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld90.setName("chkAktAld90"); // NOI18N
        chkAktAld90.setOpaque(false);
        chkAktAld90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld90);
        chkAktAld90.setBounds(590, 616, 28, 23);

        chkAktAld120.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld120.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld120.setBorderPainted(true);
        chkAktAld120.setBorderPaintedFlat(true);
        chkAktAld120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld120.setName("chkAktAld120"); // NOI18N
        chkAktAld120.setOpaque(false);
        chkAktAld120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld120);
        chkAktAld120.setBounds(630, 616, 28, 23);

        chkAktAld150.setBackground(new java.awt.Color(255, 255, 250));
        chkAktAld150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktAld150.setForeground(new java.awt.Color(0, 0, 0));
        chkAktAld150.setBorderPainted(true);
        chkAktAld150.setBorderPaintedFlat(true);
        chkAktAld150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktAld150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktAld150.setName("chkAktAld150"); // NOI18N
        chkAktAld150.setOpaque(false);
        chkAktAld150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktAld150);
        chkAktAld150.setBounds(670, 616, 28, 23);

        TdiscarAktAldret.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarAktAldret.setName("TdiscarAktAldret"); // NOI18N
        TdiscarAktAldret.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarAktAldretKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarAktAldret);
        TdiscarAktAldret.setBounds(710, 616, 80, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Total Skor :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(305, 644, 80, 23);

        TskorTotAldret.setEditable(false);
        TskorTotAldret.setForeground(new java.awt.Color(0, 0, 0));
        TskorTotAldret.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTotAldret.setName("TskorTotAldret"); // NOI18N
        FormInput.add(TskorTotAldret);
        TskorTotAldret.setBounds(390, 644, 34, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Pasien bisa dipindah ke bangsal jika skor minimal 8");
        jLabel100.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(430, 644, 350, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("STEWARD SCORE (DEWASA) PASCA GENERAL ANESTESI (GA) :");
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(0, 672, 380, 23);

        jLabel102.setForeground(new java.awt.Color(0, 0, 0));
        jLabel102.setText("Post Anesteshia Score");
        jLabel102.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(0, 700, 250, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Waktu (menit)");
        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(390, 700, 210, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Pre Anestesi Vital Sign Source / TD Pre Anestesi :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(0, 728, 270, 23);

        TtdPreAnesSte.setForeground(new java.awt.Color(0, 0, 0));
        TtdPreAnesSte.setName("TtdPreAnesSte"); // NOI18N
        TtdPreAnesSte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdPreAnesSteKeyPressed(evt);
            }
        });
        FormInput.add(TtdPreAnesSte);
        TtdPreAnesSte.setBounds(275, 728, 70, 23);

        jLabel297.setForeground(new java.awt.Color(0, 0, 0));
        jLabel297.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel297.setText("mmHg");
        jLabel297.setName("jLabel297"); // NOI18N
        FormInput.add(jLabel297);
        jLabel297.setBounds(350, 728, 40, 23);

        jLabel298.setForeground(new java.awt.Color(0, 0, 0));
        jLabel298.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel298.setText("Skor");
        jLabel298.setName("jLabel298"); // NOI18N
        FormInput.add(jLabel298);
        jLabel298.setBounds(390, 728, 34, 23);

        jLabel299.setForeground(new java.awt.Color(0, 0, 0));
        jLabel299.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel299.setText("15\"");
        jLabel299.setName("jLabel299"); // NOI18N
        FormInput.add(jLabel299);
        jLabel299.setBounds(430, 728, 34, 23);

        jLabel300.setForeground(new java.awt.Color(0, 0, 0));
        jLabel300.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel300.setText("30\"");
        jLabel300.setName("jLabel300"); // NOI18N
        FormInput.add(jLabel300);
        jLabel300.setBounds(470, 728, 34, 23);

        jLabel301.setForeground(new java.awt.Color(0, 0, 0));
        jLabel301.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel301.setText("45\"");
        jLabel301.setName("jLabel301"); // NOI18N
        FormInput.add(jLabel301);
        jLabel301.setBounds(510, 728, 34, 23);

        jLabel302.setForeground(new java.awt.Color(0, 0, 0));
        jLabel302.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel302.setText("60\"");
        jLabel302.setName("jLabel302"); // NOI18N
        FormInput.add(jLabel302);
        jLabel302.setBounds(550, 728, 34, 23);

        jLabel303.setForeground(new java.awt.Color(0, 0, 0));
        jLabel303.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel303.setText("90\"");
        jLabel303.setName("jLabel303"); // NOI18N
        FormInput.add(jLabel303);
        jLabel303.setBounds(590, 728, 34, 23);

        jLabel304.setForeground(new java.awt.Color(0, 0, 0));
        jLabel304.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel304.setText("120\"");
        jLabel304.setName("jLabel304"); // NOI18N
        FormInput.add(jLabel304);
        jLabel304.setBounds(630, 728, 34, 23);

        jLabel305.setForeground(new java.awt.Color(0, 0, 0));
        jLabel305.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel305.setText("150\"");
        jLabel305.setName("jLabel305"); // NOI18N
        FormInput.add(jLabel305);
        jLabel305.setBounds(670, 728, 34, 23);

        jLabel306.setForeground(new java.awt.Color(0, 0, 0));
        jLabel306.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel306.setText("Discharge");
        jLabel306.setName("jLabel306"); // NOI18N
        FormInput.add(jLabel306);
        jLabel306.setBounds(710, 728, 80, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Kesadaran :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(0, 756, 195, 23);

        cmbKesadaranSte.setBackground(new java.awt.Color(245, 253, 240));
        cmbKesadaranSte.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaranSte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar penuh", "Respon/bangun jika dipanggil", "Belum/tidak ada respon" }));
        cmbKesadaranSte.setLightWeightPopupEnabled(false);
        cmbKesadaranSte.setName("cmbKesadaranSte"); // NOI18N
        cmbKesadaranSte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesadaranSteActionPerformed(evt);
            }
        });
        FormInput.add(cmbKesadaranSte);
        cmbKesadaranSte.setBounds(200, 756, 172, 23);

        TskorKesSte.setEditable(false);
        TskorKesSte.setForeground(new java.awt.Color(0, 0, 0));
        TskorKesSte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKesSte.setName("TskorKesSte"); // NOI18N
        FormInput.add(TskorKesSte);
        TskorKesSte.setBounds(390, 756, 34, 23);

        chkKesSte15.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte15.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte15.setBorderPainted(true);
        chkKesSte15.setBorderPaintedFlat(true);
        chkKesSte15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte15.setName("chkKesSte15"); // NOI18N
        chkKesSte15.setOpaque(false);
        chkKesSte15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte15);
        chkKesSte15.setBounds(430, 756, 28, 23);

        chkKesSte30.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte30.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte30.setBorderPainted(true);
        chkKesSte30.setBorderPaintedFlat(true);
        chkKesSte30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte30.setName("chkKesSte30"); // NOI18N
        chkKesSte30.setOpaque(false);
        chkKesSte30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte30);
        chkKesSte30.setBounds(470, 756, 28, 23);

        chkKesSte45.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte45.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte45.setBorderPainted(true);
        chkKesSte45.setBorderPaintedFlat(true);
        chkKesSte45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte45.setName("chkKesSte45"); // NOI18N
        chkKesSte45.setOpaque(false);
        chkKesSte45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte45);
        chkKesSte45.setBounds(510, 756, 28, 23);

        chkKesSte60.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte60.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte60.setBorderPainted(true);
        chkKesSte60.setBorderPaintedFlat(true);
        chkKesSte60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte60.setName("chkKesSte60"); // NOI18N
        chkKesSte60.setOpaque(false);
        chkKesSte60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte60);
        chkKesSte60.setBounds(550, 756, 28, 23);

        chkKesSte90.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte90.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte90.setBorderPainted(true);
        chkKesSte90.setBorderPaintedFlat(true);
        chkKesSte90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte90.setName("chkKesSte90"); // NOI18N
        chkKesSte90.setOpaque(false);
        chkKesSte90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte90);
        chkKesSte90.setBounds(590, 756, 28, 23);

        chkKesSte120.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte120.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte120.setBorderPainted(true);
        chkKesSte120.setBorderPaintedFlat(true);
        chkKesSte120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte120.setName("chkKesSte120"); // NOI18N
        chkKesSte120.setOpaque(false);
        chkKesSte120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte120);
        chkKesSte120.setBounds(630, 756, 28, 23);

        chkKesSte150.setBackground(new java.awt.Color(255, 255, 250));
        chkKesSte150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesSte150.setForeground(new java.awt.Color(0, 0, 0));
        chkKesSte150.setBorderPainted(true);
        chkKesSte150.setBorderPaintedFlat(true);
        chkKesSte150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesSte150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesSte150.setName("chkKesSte150"); // NOI18N
        chkKesSte150.setOpaque(false);
        chkKesSte150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesSte150);
        chkKesSte150.setBounds(670, 756, 28, 23);

        TdiscarKesSte.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarKesSte.setName("TdiscarKesSte"); // NOI18N
        TdiscarKesSte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarKesSteKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarKesSte);
        TdiscarKesSte.setBounds(710, 756, 80, 23);

        jLabel106.setForeground(new java.awt.Color(0, 0, 0));
        jLabel106.setText("Pernafasan :");
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(0, 784, 195, 23);

        cmbPernafasanSte.setBackground(new java.awt.Color(245, 253, 240));
        cmbPernafasanSte.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernafasanSte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Batuk / Menangis", "Berusaha bernafas", "Perlu bantuan bernafas" }));
        cmbPernafasanSte.setLightWeightPopupEnabled(false);
        cmbPernafasanSte.setName("cmbPernafasanSte"); // NOI18N
        cmbPernafasanSte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernafasanSteActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernafasanSte);
        cmbPernafasanSte.setBounds(200, 784, 145, 23);

        TskorPerSte.setEditable(false);
        TskorPerSte.setForeground(new java.awt.Color(0, 0, 0));
        TskorPerSte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorPerSte.setName("TskorPerSte"); // NOI18N
        FormInput.add(TskorPerSte);
        TskorPerSte.setBounds(390, 784, 34, 23);

        chkPerSte15.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte15.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte15.setBorderPainted(true);
        chkPerSte15.setBorderPaintedFlat(true);
        chkPerSte15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte15.setName("chkPerSte15"); // NOI18N
        chkPerSte15.setOpaque(false);
        chkPerSte15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte15);
        chkPerSte15.setBounds(430, 784, 28, 23);

        chkPerSte30.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte30.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte30.setBorderPainted(true);
        chkPerSte30.setBorderPaintedFlat(true);
        chkPerSte30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte30.setName("chkPerSte30"); // NOI18N
        chkPerSte30.setOpaque(false);
        chkPerSte30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte30);
        chkPerSte30.setBounds(470, 784, 28, 23);

        chkPerSte45.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte45.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte45.setBorderPainted(true);
        chkPerSte45.setBorderPaintedFlat(true);
        chkPerSte45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte45.setName("chkPerSte45"); // NOI18N
        chkPerSte45.setOpaque(false);
        chkPerSte45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte45);
        chkPerSte45.setBounds(510, 784, 28, 23);

        chkPerSte60.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte60.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte60.setBorderPainted(true);
        chkPerSte60.setBorderPaintedFlat(true);
        chkPerSte60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte60.setName("chkPerSte60"); // NOI18N
        chkPerSte60.setOpaque(false);
        chkPerSte60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte60);
        chkPerSte60.setBounds(550, 784, 28, 23);

        chkPerSte90.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte90.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte90.setBorderPainted(true);
        chkPerSte90.setBorderPaintedFlat(true);
        chkPerSte90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte90.setName("chkPerSte90"); // NOI18N
        chkPerSte90.setOpaque(false);
        chkPerSte90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte90);
        chkPerSte90.setBounds(590, 784, 28, 23);

        chkPerSte120.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte120.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte120.setBorderPainted(true);
        chkPerSte120.setBorderPaintedFlat(true);
        chkPerSte120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte120.setName("chkPerSte120"); // NOI18N
        chkPerSte120.setOpaque(false);
        chkPerSte120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte120);
        chkPerSte120.setBounds(630, 784, 28, 23);

        chkPerSte150.setBackground(new java.awt.Color(255, 255, 250));
        chkPerSte150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPerSte150.setForeground(new java.awt.Color(0, 0, 0));
        chkPerSte150.setBorderPainted(true);
        chkPerSte150.setBorderPaintedFlat(true);
        chkPerSte150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkPerSte150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkPerSte150.setName("chkPerSte150"); // NOI18N
        chkPerSte150.setOpaque(false);
        chkPerSte150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPerSte150);
        chkPerSte150.setBounds(670, 784, 28, 23);

        TdiscarPerSte.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarPerSte.setName("TdiscarPerSte"); // NOI18N
        TdiscarPerSte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarPerSteKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarPerSte);
        TdiscarPerSte.setBounds(710, 784, 80, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Aktifitas Motorik :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(0, 812, 195, 23);

        cmbAktifitasSte.setBackground(new java.awt.Color(245, 253, 240));
        cmbAktifitasSte.setForeground(new java.awt.Color(0, 0, 0));
        cmbAktifitasSte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Gerakan beraturan", "Gerakan tanpa tujuan", "Tidak bergerak" }));
        cmbAktifitasSte.setLightWeightPopupEnabled(false);
        cmbAktifitasSte.setName("cmbAktifitasSte"); // NOI18N
        cmbAktifitasSte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAktifitasSteActionPerformed(evt);
            }
        });
        FormInput.add(cmbAktifitasSte);
        cmbAktifitasSte.setBounds(200, 812, 140, 23);

        TskorAktSte.setEditable(false);
        TskorAktSte.setForeground(new java.awt.Color(0, 0, 0));
        TskorAktSte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorAktSte.setName("TskorAktSte"); // NOI18N
        FormInput.add(TskorAktSte);
        TskorAktSte.setBounds(390, 812, 34, 23);

        chkAktSte15.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte15.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte15.setBorderPainted(true);
        chkAktSte15.setBorderPaintedFlat(true);
        chkAktSte15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte15.setName("chkAktSte15"); // NOI18N
        chkAktSte15.setOpaque(false);
        chkAktSte15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte15);
        chkAktSte15.setBounds(430, 812, 28, 23);

        chkAktSte30.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte30.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte30.setBorderPainted(true);
        chkAktSte30.setBorderPaintedFlat(true);
        chkAktSte30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte30.setName("chkAktSte30"); // NOI18N
        chkAktSte30.setOpaque(false);
        chkAktSte30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte30);
        chkAktSte30.setBounds(470, 812, 28, 23);

        chkAktSte45.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte45.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte45.setBorderPainted(true);
        chkAktSte45.setBorderPaintedFlat(true);
        chkAktSte45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte45.setName("chkAktSte45"); // NOI18N
        chkAktSte45.setOpaque(false);
        chkAktSte45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte45);
        chkAktSte45.setBounds(510, 812, 28, 23);

        chkAktSte60.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte60.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte60.setBorderPainted(true);
        chkAktSte60.setBorderPaintedFlat(true);
        chkAktSte60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte60.setName("chkAktSte60"); // NOI18N
        chkAktSte60.setOpaque(false);
        chkAktSte60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte60);
        chkAktSte60.setBounds(550, 812, 28, 23);

        chkAktSte90.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte90.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte90.setBorderPainted(true);
        chkAktSte90.setBorderPaintedFlat(true);
        chkAktSte90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte90.setName("chkAktSte90"); // NOI18N
        chkAktSte90.setOpaque(false);
        chkAktSte90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte90);
        chkAktSte90.setBounds(590, 812, 28, 23);

        chkAktSte120.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte120.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte120.setBorderPainted(true);
        chkAktSte120.setBorderPaintedFlat(true);
        chkAktSte120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte120.setName("chkAktSte120"); // NOI18N
        chkAktSte120.setOpaque(false);
        chkAktSte120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte120);
        chkAktSte120.setBounds(630, 812, 28, 23);

        chkAktSte150.setBackground(new java.awt.Color(255, 255, 250));
        chkAktSte150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAktSte150.setForeground(new java.awt.Color(0, 0, 0));
        chkAktSte150.setBorderPainted(true);
        chkAktSte150.setBorderPaintedFlat(true);
        chkAktSte150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkAktSte150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkAktSte150.setName("chkAktSte150"); // NOI18N
        chkAktSte150.setOpaque(false);
        chkAktSte150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkAktSte150);
        chkAktSte150.setBounds(670, 812, 28, 23);

        TdiscarAktSte.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarAktSte.setName("TdiscarAktSte"); // NOI18N
        TdiscarAktSte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarAktSteKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarAktSte);
        TdiscarAktSte.setBounds(710, 812, 80, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Total Skor :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(305, 840, 80, 23);

        TskorTotSte.setEditable(false);
        TskorTotSte.setForeground(new java.awt.Color(0, 0, 0));
        TskorTotSte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorTotSte.setName("TskorTotSte"); // NOI18N
        FormInput.add(TskorTotSte);
        TskorTotSte.setBounds(390, 840, 34, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel109.setText("Pasien bisa dipindah ke bangsal jika skor minimal 5");
        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(430, 840, 350, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("BROMAGE SCORE (SAB / SUB ARACHNOID BLOCK) :");
        jLabel110.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(0, 868, 320, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Post Anesteshia Score");
        jLabel111.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(0, 896, 250, 23);

        jLabel112.setForeground(new java.awt.Color(0, 0, 0));
        jLabel112.setText("Waktu (menit)");
        jLabel112.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(390, 896, 210, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Pre Anestesi Vital Sign Source / TD Pre Anestesi :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(0, 924, 270, 23);

        TtdPreAnesBrom.setForeground(new java.awt.Color(0, 0, 0));
        TtdPreAnesBrom.setName("TtdPreAnesBrom"); // NOI18N
        TtdPreAnesBrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdPreAnesBromKeyPressed(evt);
            }
        });
        FormInput.add(TtdPreAnesBrom);
        TtdPreAnesBrom.setBounds(275, 924, 70, 23);

        jLabel307.setForeground(new java.awt.Color(0, 0, 0));
        jLabel307.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel307.setText("Skor");
        jLabel307.setName("jLabel307"); // NOI18N
        FormInput.add(jLabel307);
        jLabel307.setBounds(390, 924, 34, 23);

        jLabel308.setForeground(new java.awt.Color(0, 0, 0));
        jLabel308.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel308.setText("mmHg");
        jLabel308.setName("jLabel308"); // NOI18N
        FormInput.add(jLabel308);
        jLabel308.setBounds(350, 924, 40, 23);

        jLabel309.setForeground(new java.awt.Color(0, 0, 0));
        jLabel309.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel309.setText("15\"");
        jLabel309.setName("jLabel309"); // NOI18N
        FormInput.add(jLabel309);
        jLabel309.setBounds(430, 924, 34, 23);

        jLabel310.setForeground(new java.awt.Color(0, 0, 0));
        jLabel310.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel310.setText("30\"");
        jLabel310.setName("jLabel310"); // NOI18N
        FormInput.add(jLabel310);
        jLabel310.setBounds(470, 924, 34, 23);

        jLabel311.setForeground(new java.awt.Color(0, 0, 0));
        jLabel311.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel311.setText("45\"");
        jLabel311.setName("jLabel311"); // NOI18N
        FormInput.add(jLabel311);
        jLabel311.setBounds(510, 924, 34, 23);

        jLabel312.setForeground(new java.awt.Color(0, 0, 0));
        jLabel312.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel312.setText("60\"");
        jLabel312.setName("jLabel312"); // NOI18N
        FormInput.add(jLabel312);
        jLabel312.setBounds(550, 924, 34, 23);

        jLabel313.setForeground(new java.awt.Color(0, 0, 0));
        jLabel313.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel313.setText("90\"");
        jLabel313.setName("jLabel313"); // NOI18N
        FormInput.add(jLabel313);
        jLabel313.setBounds(590, 924, 34, 23);

        jLabel314.setForeground(new java.awt.Color(0, 0, 0));
        jLabel314.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel314.setText("120\"");
        jLabel314.setName("jLabel314"); // NOI18N
        FormInput.add(jLabel314);
        jLabel314.setBounds(630, 924, 34, 23);

        jLabel315.setForeground(new java.awt.Color(0, 0, 0));
        jLabel315.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel315.setText("150\"");
        jLabel315.setName("jLabel315"); // NOI18N
        FormInput.add(jLabel315);
        jLabel315.setBounds(670, 924, 34, 23);

        jLabel316.setForeground(new java.awt.Color(0, 0, 0));
        jLabel316.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel316.setText("Discharge");
        jLabel316.setName("jLabel316"); // NOI18N
        FormInput.add(jLabel316);
        jLabel316.setBounds(710, 924, 80, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Kesadaran :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(0, 952, 130, 23);

        cmbKesadaranBrom.setBackground(new java.awt.Color(245, 253, 240));
        cmbKesadaranBrom.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaranBrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Gerakan penuh dari tungkai", "Tidak mampu mengekstensi tungkai", "Tidak mampu memfleksi lutut", "Tidak mampu memfleksi pergelangan kaki" }));
        cmbKesadaranBrom.setLightWeightPopupEnabled(false);
        cmbKesadaranBrom.setName("cmbKesadaranBrom"); // NOI18N
        cmbKesadaranBrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKesadaranBromActionPerformed(evt);
            }
        });
        FormInput.add(cmbKesadaranBrom);
        cmbKesadaranBrom.setBounds(135, 952, 230, 23);

        TskorKesBrom.setEditable(false);
        TskorKesBrom.setForeground(new java.awt.Color(0, 0, 0));
        TskorKesBrom.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TskorKesBrom.setName("TskorKesBrom"); // NOI18N
        FormInput.add(TskorKesBrom);
        TskorKesBrom.setBounds(390, 952, 34, 23);

        chkKesBro15.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro15.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro15.setBorderPainted(true);
        chkKesBro15.setBorderPaintedFlat(true);
        chkKesBro15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro15.setName("chkKesBro15"); // NOI18N
        chkKesBro15.setOpaque(false);
        chkKesBro15.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro15);
        chkKesBro15.setBounds(430, 952, 28, 23);

        chkKesBro30.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro30.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro30.setBorderPainted(true);
        chkKesBro30.setBorderPaintedFlat(true);
        chkKesBro30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro30.setName("chkKesBro30"); // NOI18N
        chkKesBro30.setOpaque(false);
        chkKesBro30.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro30);
        chkKesBro30.setBounds(470, 952, 28, 23);

        chkKesBro45.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro45.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro45.setBorderPainted(true);
        chkKesBro45.setBorderPaintedFlat(true);
        chkKesBro45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro45.setName("chkKesBro45"); // NOI18N
        chkKesBro45.setOpaque(false);
        chkKesBro45.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro45);
        chkKesBro45.setBounds(510, 952, 28, 23);

        chkKesBro60.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro60.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro60.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro60.setBorderPainted(true);
        chkKesBro60.setBorderPaintedFlat(true);
        chkKesBro60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro60.setName("chkKesBro60"); // NOI18N
        chkKesBro60.setOpaque(false);
        chkKesBro60.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro60);
        chkKesBro60.setBounds(550, 952, 28, 23);

        chkKesBro90.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro90.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro90.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro90.setBorderPainted(true);
        chkKesBro90.setBorderPaintedFlat(true);
        chkKesBro90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro90.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro90.setName("chkKesBro90"); // NOI18N
        chkKesBro90.setOpaque(false);
        chkKesBro90.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro90);
        chkKesBro90.setBounds(590, 952, 28, 23);

        chkKesBro120.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro120.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro120.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro120.setBorderPainted(true);
        chkKesBro120.setBorderPaintedFlat(true);
        chkKesBro120.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro120.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro120.setName("chkKesBro120"); // NOI18N
        chkKesBro120.setOpaque(false);
        chkKesBro120.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro120);
        chkKesBro120.setBounds(630, 952, 28, 23);

        chkKesBro150.setBackground(new java.awt.Color(255, 255, 250));
        chkKesBro150.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkKesBro150.setForeground(new java.awt.Color(0, 0, 0));
        chkKesBro150.setBorderPainted(true);
        chkKesBro150.setBorderPaintedFlat(true);
        chkKesBro150.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chkKesBro150.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        chkKesBro150.setName("chkKesBro150"); // NOI18N
        chkKesBro150.setOpaque(false);
        chkKesBro150.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkKesBro150);
        chkKesBro150.setBounds(670, 952, 28, 23);

        TdiscarKesBrom.setForeground(new java.awt.Color(0, 0, 0));
        TdiscarKesBrom.setName("TdiscarKesBrom"); // NOI18N
        TdiscarKesBrom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiscarKesBromKeyPressed(evt);
            }
        });
        FormInput.add(TdiscarKesBrom);
        TdiscarKesBrom.setBounds(710, 952, 80, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel116.setText("Pasien bisa dipindah ke bangsal jika skor < 3");
        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(430, 980, 280, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("INSTRUKSI PASCA ANESIETI / SEDASI :");
        jLabel117.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(0, 1008, 250, 23);

        jLabel118.setForeground(new java.awt.Color(0, 0, 0));
        jLabel118.setText("1. Infus :");
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(0, 1036, 130, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("2. a. Puasa Sampai Dengan :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(0, 1100, 170, 23);

        Tpuasa.setForeground(new java.awt.Color(0, 0, 0));
        Tpuasa.setName("Tpuasa"); // NOI18N
        Tpuasa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpuasaKeyPressed(evt);
            }
        });
        FormInput.add(Tpuasa);
        Tpuasa.setBounds(175, 1100, 459, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("b. Minum / Makan Jam :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(0, 1128, 170, 23);

        Tminum.setForeground(new java.awt.Color(0, 0, 0));
        Tminum.setName("Tminum"); // NOI18N
        Tminum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TminumKeyPressed(evt);
            }
        });
        FormInput.add(Tminum);
        Tminum.setBounds(175, 1128, 459, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Bila :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(0, 1156, 170, 23);

        Tbila.setForeground(new java.awt.Color(0, 0, 0));
        Tbila.setName("Tbila"); // NOI18N
        Tbila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbilaKeyPressed(evt);
            }
        });
        FormInput.add(Tbila);
        Tbila.setBounds(175, 1156, 459, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("3. Observasi : a. Analgetik :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(0, 1184, 170, 23);

        Tanalgetik.setForeground(new java.awt.Color(0, 0, 0));
        Tanalgetik.setName("Tanalgetik"); // NOI18N
        Tanalgetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanalgetikKeyPressed(evt);
            }
        });
        FormInput.add(Tanalgetik);
        Tanalgetik.setBounds(175, 1184, 459, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("b. Antiemetik :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(0, 1212, 170, 23);

        Tantiemetik.setForeground(new java.awt.Color(0, 0, 0));
        Tantiemetik.setName("Tantiemetik"); // NOI18N
        Tantiemetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TantiemetikKeyPressed(evt);
            }
        });
        FormInput.add(Tantiemetik);
        Tantiemetik.setBounds(175, 1212, 459, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("4. Obat Medikasi a. :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(0, 1268, 170, 23);

        TobatA.setForeground(new java.awt.Color(0, 0, 0));
        TobatA.setName("TobatA"); // NOI18N
        TobatA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatAKeyPressed(evt);
            }
        });
        FormInput.add(TobatA);
        TobatA.setBounds(175, 1268, 459, 23);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("b. :");
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(0, 1296, 170, 23);

        TobatB.setForeground(new java.awt.Color(0, 0, 0));
        TobatB.setName("TobatB"); // NOI18N
        TobatB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatBKeyPressed(evt);
            }
        });
        FormInput.add(TobatB);
        TobatB.setBounds(175, 1296, 459, 23);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setText("5. Pasien Boleh Dipindahkan Ke :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(0, 1324, 170, 23);

        cmbPasienBoleh.setBackground(new java.awt.Color(245, 253, 240));
        cmbPasienBoleh.setForeground(new java.awt.Color(0, 0, 0));
        cmbPasienBoleh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ruang Perawatan", "ICU", "Pulang (ODC)" }));
        cmbPasienBoleh.setLightWeightPopupEnabled(false);
        cmbPasienBoleh.setName("cmbPasienBoleh"); // NOI18N
        cmbPasienBoleh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPasienBolehActionPerformed(evt);
            }
        });
        FormInput.add(cmbPasienBoleh);
        cmbPasienBoleh.setBounds(175, 1324, 118, 23);

        jLabel127.setForeground(new java.awt.Color(0, 0, 0));
        jLabel127.setText("Jam :");
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(295, 1324, 50, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("6. Lain - lain :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(0, 1352, 170, 23);

        scrollPane14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        scrollPane14.setName("scrollPane14"); // NOI18N

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
        scrollPane14.setViewportView(Tlainlain);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(175, 1352, 459, 140);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("Tgl. Serah Terima :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(0, 1498, 170, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Jam :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(295, 1498, 50, 23);

        scrollPane15.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " [ Posisi Tidur Pasien ] ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        scrollPane15.setName("scrollPane15"); // NOI18N

        Tposisi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tposisi.setColumns(20);
        Tposisi.setRows(5);
        Tposisi.setName("Tposisi"); // NOI18N
        Tposisi.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tposisi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TposisiKeyPressed(evt);
            }
        });
        scrollPane15.setViewportView(Tposisi);

        FormInput.add(scrollPane15);
        scrollPane15.setBounds(650, 1036, 270, 150);

        scrollPane16.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " [ Kateter Epidural Dilepas ] ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        scrollPane16.setName("scrollPane16"); // NOI18N

        Tkateter.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkateter.setColumns(20);
        Tkateter.setRows(5);
        Tkateter.setName("Tkateter"); // NOI18N
        Tkateter.setPreferredSize(new java.awt.Dimension(162, 2000));
        Tkateter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkateterKeyPressed(evt);
            }
        });
        scrollPane16.setViewportView(Tkateter);

        FormInput.add(scrollPane16);
        scrollPane16.setBounds(650, 1194, 270, 150);

        scrollPane17.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), " [ Instruksi Khusus ] ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        scrollPane17.setName("scrollPane17"); // NOI18N

        Tinstruksi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tinstruksi.setColumns(20);
        Tinstruksi.setRows(5);
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(162, 2000));
        scrollPane17.setViewportView(Tinstruksi);

        FormInput.add(scrollPane17);
        scrollPane17.setBounds(650, 1355, 270, 150);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("c. Obat Lain :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(0, 1240, 170, 23);

        TobatLain.setForeground(new java.awt.Color(0, 0, 0));
        TobatLain.setName("TobatLain"); // NOI18N
        TobatLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TobatLainKeyPressed(evt);
            }
        });
        FormInput.add(TobatLain);
        TobatLain.setBounds(175, 1240, 459, 23);

        Scroll1.setViewportView(FormInput);

        panelGlass9.add(Scroll1, java.awt.BorderLayout.CENTER);

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Catatan Ruang Pemulihan ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(850, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCatatan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCatatan.setName("tbCatatan"); // NOI18N
        tbCatatan.getTableHeader().setReorderingAllowed(false);
        tbCatatan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCatatanMouseClicked(evt);
            }
        });
        tbCatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCatatanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCatatan);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

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
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-05-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-05-2026" }));
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
            try {
                wktSimpan = Sequel.cariIsi("select now()");
                cekData();
                if (Sequel.menyimpantf("catatan_ruang_pemulihan", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                        + "?,?,?,?", "No. Rawat", 113, new String[]{
                            TNoRw.getText(), TrgRawat.getText(), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                            cmbJlnNafas.getSelectedItem().toString(), TketJlnNafas.getText(), cmbPernafasanCttn.getSelectedItem().toString(), cmbBila.getSelectedItem().toString(),
                            cmbKesadaranCttn.getSelectedItem().toString(), TtdPreAnesAldret.getText(), cmbSiskulasi.getSelectedItem().toString(), sis15, sis30, sis45, sis60,
                            sis90, sis120, sis150, TdiscarSis.getText(), cmbKesadaranAldret.getSelectedItem().toString(), kesAld15, kesAld30, kesAld45, kesAld60,
                            kesAld90, kesAld120, kesAld150, TdiscarKesAldret.getText(), cmbPernafasanAldret.getSelectedItem().toString(), perAld15, perAld30, perAld45, perAld60,
                            perAld90, perAld120, perAld150, TdiscarPerAldret.getText(), cmbWrnKulit.getSelectedItem().toString(), war15, war30, war45, war60,
                            war90, war120, war150, TdiscarWar.getText(), cmbAktifitasAldret.getSelectedItem().toString(), aktAld15, aktAld30, aktAld45, aktAld60,
                            aktAld90, aktAld120, aktAld150, TdiscarAktAldret.getText(), TtdPreAnesSte.getText(), cmbKesadaranSte.getSelectedItem().toString(), kesSte15, 
                            kesSte30, kesSte45, kesSte60, kesSte90, kesSte120, kesSte150, TdiscarKesSte.getText(), cmbPernafasanSte.getSelectedItem().toString(), perSte15, 
                            perSte30, perSte45, perSte60, perSte90, perSte120, perSte150, TdiscarPerSte.getText(), cmbAktifitasSte.getSelectedItem().toString(), aktSte15, 
                            aktSte30, aktSte45, aktSte60, aktSte90, aktSte120, aktSte150, TdiscarAktSte.getText(), TtdPreAnesBrom.getText(), cmbKesadaranBrom.getSelectedItem().toString(), 
                            kesBro15, kesBro30, kesBro45, kesBro60, kesBro90, kesBro120, kesBro150, TdiscarKesBrom.getText(), Tinfus.getText(), Tpuasa.getText(), Tminum.getText(),
                            Tbila.getText(), Tanalgetik.getText(), Tantiemetik.getText(), TobatLain.getText(), TobatA.getText(), TobatB.getText(), cmbPasienBoleh.getSelectedItem().toString(),
                            cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), Tlainlain.getText(), Tposisi.getText(), Tkateter.getText(),
                            Tinstruksi.getText(), nipDrAnes, nipPenata, nipPerawat, Valid.SetTgl(TtglSerah.getSelectedItem() + ""),
                            cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(), wktSimpan
                        }) == true) {

                    if (tbTTV.getRowCount() != 0) {
                        for (i = 0; i < tbTTV.getRowCount(); i++) {
                            Sequel.menyimpanIgnore("catatan_ruang_pemulihan_obs_ttv",
                                    "'" + tbTTV.getValueAt(i, 0).toString() + "','"
                                    + tbTTV.getValueAt(i, 1).toString() + "','"
                                    + tbTTV.getValueAt(i, 2).toString() + "','"
                                    + tbTTV.getValueAt(i, 3).toString() + "','"
                                    + tbTTV.getValueAt(i, 4).toString() + "','"
                                    + tbTTV.getValueAt(i, 5).toString() + "','"
                                    + tbTTV.getValueAt(i, 6).toString() + "','"
                                    + tbTTV.getValueAt(i, 7).toString() + "','"
                                    + tbTTV.getValueAt(i, 8).toString() + "','"
                                    + wktSimpan + "'", "Data Observasi TTV");
                        }
                    }

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Ruang Pemulihan", "Simpan");
                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Catatan Ruang Pemulihan : " + e);
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
            if (tbCatatan.getSelectedRow() > -1) {
                try {
                    cekData();
                    if (Sequel.mengedittf("catatan_ruang_pemulihan", "waktu_simpan=?", "jam_msk=?, jalan_nafas=?, ket_jalan_nafas=?, pernapasan_cttn=?, bila_spontan=?, kesadaran_cttn=?, "
                            + "td_pre_anestesi=?, siskulasi=?, sis_menit_15=?, sis_menit_30=?, sis_menit_45=?, sis_menit_60=?, sis_menit_90=?, sis_menit_120=?, sis_menit_150=?, "
                            + "siskulasi_discharge=?, kesadaran=?, kes_menit_15=?, kes_menit_30=?, kes_menit_45=?, kes_menit_60=?, kes_menit_90=?, kes_menit_120=?, kes_menit_150=?, "
                            + "kesadaran_discharge=?, pernapasan=?, per_menit_15=?, per_menit_30=?, per_menit_45=?, per_menit_60=?, per_menit_90=?, per_menit_120=?, per_menit_150=?, "
                            + "pernapasan_discharge=?, warna_kulit=?, war_menit_15=?, war_menit_30=?, war_menit_45=?, war_menit_60=?, war_menit_90=?, war_menit_120=?, war_menit_150=?, "
                            + "warna_kulit_discharge=?, aktifitas=?, akt_menit_15=?, akt_menit_30=?, akt_menit_45=?, akt_menit_60=?, akt_menit_90=?, akt_menit_120=?, akt_menit_150=?, "
                            + "aktifitas_discharge=?, td_pre_anestesi_steward=?, kesadaran_steward=?, kes_ste_menit_15=?, kes_ste_menit_30=?, kes_ste_menit_45=?, kes_ste_menit_60=?, "
                            + "kes_ste_menit_90=?, kes_ste_menit_120=?, kes_ste_menit_150=?, kesadaran_discharge_steward=?, pernapasan_steward=?, per_ste_menit_15=?, per_ste_menit_30=?, "
                            + "per_ste_menit_45=?, per_ste_menit_60=?, per_ste_menit_90=?, per_ste_menit_120=?, per_ste_menit_150=?, pernapasan_discharge_steward=?, aktifitas_mot_steward=?, "
                            + "akt_ste_menit_15=?, akt_ste_menit_30=?, akt_ste_menit_45=?, akt_ste_menit_60=?, akt_ste_menit_90=?, akt_ste_menit_120=?, akt_ste_menit_150=?, "
                            + "aktifitas_discharge_steward=?, td_pre_anestesi_brom=?, kesadaran_brom=?, kes_brom_menit_15=?, kes_brom_menit_30=?, kes_brom_menit_45=?, kes_brom_menit_60=?, "
                            + "kes_brom_menit_90=?, kes_brom_menit_120=?, kes_brom_menit_150=?, kesadaran_discharge_brom=?, infus=?, puasa=?, minum_makan_jam=?, bila=?, analgetik=?, "
                            + "antiemetik=?, obat_lain=?, obat_medikasi_a=?, obat_medikasi_b=?, pasien_boleh_pindah=?, jam_pindah=?, lain_lain=?, posisi_tidur=?, kateter_epidural=?, "
                            + "instruksi_khusus=?, nip_dokter_anestesi=?, nip_penata=?, nip_perawat_bangsal=?, tgl_serah_terima=?, jam_serah_terima=?", 111, new String[]{
                                cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                                cmbJlnNafas.getSelectedItem().toString(), TketJlnNafas.getText(), cmbPernafasanCttn.getSelectedItem().toString(), cmbBila.getSelectedItem().toString(),
                                cmbKesadaranCttn.getSelectedItem().toString(), TtdPreAnesAldret.getText(), cmbSiskulasi.getSelectedItem().toString(), sis15, sis30, sis45, sis60,
                                sis90, sis120, sis150, TdiscarSis.getText(), cmbKesadaranAldret.getSelectedItem().toString(), kesAld15, kesAld30, kesAld45, kesAld60,
                                kesAld90, kesAld120, kesAld150, TdiscarKesAldret.getText(), cmbPernafasanAldret.getSelectedItem().toString(), perAld15, perAld30, perAld45, perAld60,
                                perAld90, perAld120, perAld150, TdiscarPerAldret.getText(), cmbWrnKulit.getSelectedItem().toString(), war15, war30, war45, war60,
                                war90, war120, war150, TdiscarWar.getText(), cmbAktifitasAldret.getSelectedItem().toString(), aktAld15, aktAld30, aktAld45, aktAld60,
                                aktAld90, aktAld120, aktAld150, TdiscarAktAldret.getText(), TtdPreAnesSte.getText(), cmbKesadaranSte.getSelectedItem().toString(), kesSte15,
                                kesSte30, kesSte45, kesSte60, kesSte90, kesSte120, kesSte150, TdiscarKesSte.getText(), cmbPernafasanSte.getSelectedItem().toString(), perSte15,
                                perSte30, perSte45, perSte60, perSte90, perSte120, perSte150, TdiscarPerSte.getText(), cmbAktifitasSte.getSelectedItem().toString(), aktSte15,
                                aktSte30, aktSte45, aktSte60, aktSte90, aktSte120, aktSte150, TdiscarAktSte.getText(), TtdPreAnesBrom.getText(), cmbKesadaranBrom.getSelectedItem().toString(),
                                kesBro15, kesBro30, kesBro45, kesBro60, kesBro90, kesBro120, kesBro150, TdiscarKesBrom.getText(), Tinfus.getText(), Tpuasa.getText(), Tminum.getText(),
                                Tbila.getText(), Tanalgetik.getText(), Tantiemetik.getText(), TobatLain.getText(), TobatA.getText(), TobatB.getText(), cmbPasienBoleh.getSelectedItem().toString(),
                                cmbJam3.getSelectedItem() + ":" + cmbMnt3.getSelectedItem() + ":" + cmbDtk3.getSelectedItem(), Tlainlain.getText(), Tposisi.getText(), Tkateter.getText(),
                                Tinstruksi.getText(), nipDrAnes, nipPenata, nipPerawat, Valid.SetTgl(TtglSerah.getSelectedItem() + ""),
                                cmbJam4.getSelectedItem() + ":" + cmbMnt4.getSelectedItem() + ":" + cmbDtk4.getSelectedItem(),
                                tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString()
                            }) == true) {

                        if (tbTTV.getRowCount() != 0) {
                            Sequel.queryu("delete from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'");
                            for (i = 0; i < tbTTV.getRowCount(); i++) {
                                Sequel.menyimpanIgnore("catatan_ruang_pemulihan_obs_ttv",
                                        "'" + tbTTV.getValueAt(i, 0).toString() + "','"
                                        + tbTTV.getValueAt(i, 1).toString() + "','"
                                        + tbTTV.getValueAt(i, 2).toString() + "','"
                                        + tbTTV.getValueAt(i, 3).toString() + "','"
                                        + tbTTV.getValueAt(i, 4).toString() + "','"
                                        + tbTTV.getValueAt(i, 5).toString() + "','"
                                        + tbTTV.getValueAt(i, 6).toString() + "','"
                                        + tbTTV.getValueAt(i, 7).toString() + "','"
                                        + tbTTV.getValueAt(i, 8).toString() + "','"
                                        + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'", "Data Observasi TTV");
                            }
                        }

                        Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Catatan Ruang Pemulihan", "Ganti");
                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Ganti Catatan Ruang Pemulihan : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCatatan.requestFocus();
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
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
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

    private void tbCatatanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCatatanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCatatanMouseClicked

    private void tbCatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCatatanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCatatanKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from catatan_ruang_pemulihan where waktu_simpan=?", 1, new String[]{
                    tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString()
                }) == true) {                    
                    Sequel.queryu("delete from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'");
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
            tbCatatan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbCatatan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("jamMasuk", cmbJam1.getSelectedItem().toString() + ":" + cmbMnt1.getSelectedItem().toString() + " Wita");

            if (cmbJlnNafas.getSelectedIndex() == 2) {
                if (TketJlnNafas.getText().equals("")) {
                    param.put("jlnNafas", cmbJlnNafas.getSelectedItem().toString() + " : .....................");
                } else {
                    param.put("jlnNafas", cmbJlnNafas.getSelectedItem().toString() + " : " + TketJlnNafas.getText());
                }
            } else {
                param.put("jlnNafas", cmbJlnNafas.getSelectedItem().toString());
            }
            
            param.put("pernaCttn", cmbPernafasanCttn.getSelectedItem().toString());
            param.put("bilaCttn", cmbBila.getSelectedItem().toString());
            param.put("kesadCttn", cmbKesadaranCttn.getSelectedItem().toString());
            
            if (TtdPreAnesAldret.getText().equals("")) {
                param.put("tdAldret", "....... mmHg");
            } else {
                param.put("tdAldret", TtdPreAnesAldret.getText() + " mmHg");
            }
            
            param.put("siskulasi", cmbSiskulasi.getSelectedItem().toString());
            param.put("skorSis", TskorSis.getText());
            
            if (chkSis15.isSelected() == true) {
                param.put("Sis15", "V");
            } else {
                param.put("Sis15", "");
            }
            
            if (chkSis30.isSelected() == true) {
                param.put("Sis30", "V");
            } else {
                param.put("Sis30", "");
            }
            
            if (chkSis45.isSelected() == true) {
                param.put("Sis45", "V");
            } else {
                param.put("Sis45", "");
            }
            
            if (chkSis60.isSelected() == true) {
                param.put("Sis60", "V");
            } else {
                param.put("Sis60", "");
            }
            
            if (chkSis90.isSelected() == true) {
                param.put("Sis90", "V");
            } else {
                param.put("Sis90", "");
            }
            
            if (chkSis120.isSelected() == true) {
                param.put("Sis120", "V");
            } else {
                param.put("Sis120", "");
            }
            
            if (chkSis150.isSelected() == true) {
                param.put("Sis150", "V");
            } else {
                param.put("Sis150", "");
            }
            
            if (TdiscarSis.getText().equals("")) {
                param.put("discarSis", ".........");
            } else {
                param.put("discarSis", TdiscarSis.getText());
            }            
            
            param.put("kesadaranAld", cmbKesadaranAldret.getSelectedItem().toString());
            param.put("skorKesAld", TskorKesAldret.getText());
            
            if (chkKesAld15.isSelected() == true) {
                param.put("KesAld15", "V");
            } else {
                param.put("KesAld15", "");
            }
            
            if (chkKesAld30.isSelected() == true) {
                param.put("KesAld30", "V");
            } else {
                param.put("KesAld30", "");
            }
            
            if (chkKesAld45.isSelected() == true) {
                param.put("KesAld45", "V");
            } else {
                param.put("KesAld45", "");
            }
            
            if (chkKesAld60.isSelected() == true) {
                param.put("KesAld60", "V");
            } else {
                param.put("KesAld60", "");
            }
            
            if (chkKesAld90.isSelected() == true) {
                param.put("KesAld90", "V");
            } else {
                param.put("KesAld90", "");
            }
            
            if (chkKesAld120.isSelected() == true) {
                param.put("KesAld120", "V");
            } else {
                param.put("KesAld120", "");
            }
            
            if (chkKesAld150.isSelected() == true) {
                param.put("KesAld150", "V");
            } else {
                param.put("KesAld150", "");
            }
            
            if (TdiscarKesAldret.getText().equals("")) {
                param.put("discarKesAld", ".........");
            } else {
                param.put("discarKesAld", TdiscarKesAldret.getText());
            }
            
            param.put("pernapasanAld", cmbPernafasanAldret.getSelectedItem().toString());
            param.put("skorPerAld", TskorPerAldret.getText());
            
            if (chkPerAld15.isSelected() == true) {
                param.put("PerAld15", "V");
            } else {
                param.put("PerAld15", "");
            }
            
            if (chkPerAld30.isSelected() == true) {
                param.put("PerAld30", "V");
            } else {
                param.put("PerAld30", "");
            }
            
            if (chkPerAld45.isSelected() == true) {
                param.put("PerAld45", "V");
            } else {
                param.put("PerAld45", "");
            }
            
            if (chkPerAld60.isSelected() == true) {
                param.put("PerAld60", "V");
            } else {
                param.put("PerAld60", "");
            }
            
            if (chkPerAld90.isSelected() == true) {
                param.put("PerAld90", "V");
            } else {
                param.put("PerAld90", "");
            }
            
            if (chkPerAld120.isSelected() == true) {
                param.put("PerAld120", "V");
            } else {
                param.put("PerAld120", "");
            }
            
            if (chkPerAld150.isSelected() == true) {
                param.put("PerAld150", "V");
            } else {
                param.put("PerAld150", "");
            }
            
            if (TdiscarPerAldret.getText().equals("")) {
                param.put("discarPerAld", ".........");
            } else {
                param.put("discarPerAld", TdiscarPerAldret.getText());
            }
            
            param.put("warna", cmbWrnKulit.getSelectedItem().toString());
            param.put("skorWarna", TskorWar.getText());
            
            if (chkWar15.isSelected() == true) {
                param.put("War15", "V");
            } else {
                param.put("War15", "");
            }
            
            if (chkWar30.isSelected() == true) {
                param.put("War30", "V");
            } else {
                param.put("War30", "");
            }
            
            if (chkWar45.isSelected() == true) {
                param.put("War45", "V");
            } else {
                param.put("War45", "");
            }
            
            if (chkWar60.isSelected() == true) {
                param.put("War60", "V");
            } else {
                param.put("War60", "");
            }
            
            if (chkWar90.isSelected() == true) {
                param.put("War90", "V");
            } else {
                param.put("War90", "");
            }
            
            if (chkWar120.isSelected() == true) {
                param.put("War120", "V");
            } else {
                param.put("War120", "");
            }
            
            if (chkWar150.isSelected() == true) {
                param.put("War150", "V");
            } else {
                param.put("War150", "");
            }
            
            if (TdiscarWar.getText().equals("")) {
                param.put("discarWar", ".........");
            } else {
                param.put("discarWar", TdiscarWar.getText());
            }
            
            param.put("aktifitasAld", cmbAktifitasAldret.getSelectedItem().toString());
            param.put("skorAktAld", TskorAktAldret.getText());
            
            if (chkAktAld15.isSelected() == true) {
                param.put("AktAld15", "V");
            } else {
                param.put("AktAld15", "");
            }
            
            if (chkAktAld30.isSelected() == true) {
                param.put("AktAld30", "V");
            } else {
                param.put("AktAld30", "");
            }
            
            if (chkAktAld45.isSelected() == true) {
                param.put("AktAld45", "V");
            } else {
                param.put("AktAld45", "");
            }
            
            if (chkAktAld60.isSelected() == true) {
                param.put("AktAld60", "V");
            } else {
                param.put("AktAld60", "");
            }
            
            if (chkAktAld90.isSelected() == true) {
                param.put("AktAld90", "V");
            } else {
                param.put("AktAld90", "");
            }
            
            if (chkAktAld120.isSelected() == true) {
                param.put("AktAld120", "V");
            } else {
                param.put("AktAld120", "");
            }
            
            if (chkAktAld150.isSelected() == true) {
                param.put("AktAld150", "V");
            } else {
                param.put("AktAld150", "");
            }
            
            if (TdiscarAktAldret.getText().equals("")) {
                param.put("discarAktAld", ".........");
            } else {
                param.put("discarAktAld", TdiscarAktAldret.getText());
            }
            
            param.put("totalAldret", TskorTotAldret.getText());
            
            if (TtdPreAnesSte.getText().equals("")) {
                param.put("tdSte", "....... mmHg");
            } else {
                param.put("tdSte", TtdPreAnesSte.getText() + " mmHg");
            }
            
            param.put("kesadaranSte", cmbKesadaranSte.getSelectedItem().toString());
            param.put("skorKesSte", TskorKesSte.getText());
            
            if (chkKesSte15.isSelected() == true) {
                param.put("KesSte15", "V");
            } else {
                param.put("KesSte15", "");
            }
            
            if (chkKesSte30.isSelected() == true) {
                param.put("KesSte30", "V");
            } else {
                param.put("KesSte30", "");
            }
            
            if (chkKesSte45.isSelected() == true) {
                param.put("KesSte45", "V");
            } else {
                param.put("KesSte45", "");
            }
            
            if (chkKesSte60.isSelected() == true) {
                param.put("KesSte60", "V");
            } else {
                param.put("KesSte60", "");
            }
            
            if (chkKesSte90.isSelected() == true) {
                param.put("KesSte90", "V");
            } else {
                param.put("KesSte90", "");
            }
            
            if (chkKesSte120.isSelected() == true) {
                param.put("KesSte120", "V");
            } else {
                param.put("KesSte120", "");
            }
            
            if (chkKesSte150.isSelected() == true) {
                param.put("KesSte150", "V");
            } else {
                param.put("KesSte150", "");
            }
            
            if (TdiscarKesSte.getText().equals("")) {
                param.put("discarKesSte", ".........");
            } else {
                param.put("discarKesSte", TdiscarKesSte.getText());
            }
            
            param.put("pernapasanSte", cmbPernafasanSte.getSelectedItem().toString());
            param.put("skorPerSte", TskorPerSte.getText());
            
            if (chkPerSte15.isSelected() == true) {
                param.put("PerSte15", "V");
            } else {
                param.put("PerSte15", "");
            }
            
            if (chkPerSte30.isSelected() == true) {
                param.put("PerSte30", "V");
            } else {
                param.put("PerSte30", "");
            }
            
            if (chkPerSte45.isSelected() == true) {
                param.put("PerSte45", "V");
            } else {
                param.put("PerSte45", "");
            }
            
            if (chkPerSte60.isSelected() == true) {
                param.put("PerSte60", "V");
            } else {
                param.put("PerSte60", "");
            }
            
            if (chkPerSte90.isSelected() == true) {
                param.put("PerSte90", "V");
            } else {
                param.put("PerSte90", "");
            }
            
            if (chkPerSte120.isSelected() == true) {
                param.put("PerSte120", "V");
            } else {
                param.put("PerSte120", "");
            }
            
            if (chkPerSte150.isSelected() == true) {
                param.put("PerSte150", "V");
            } else {
                param.put("PerSte150", "");
            }
            
            if (TdiscarPerSte.getText().equals("")) {
                param.put("discarPerSte", ".........");
            } else {
                param.put("discarPerSte", TdiscarPerSte.getText());
            }
            
            param.put("aktifitasSte", cmbAktifitasSte.getSelectedItem().toString());
            param.put("skorAktSte", TskorAktSte.getText());
            
            if (chkAktSte15.isSelected() == true) {
                param.put("AktSte15", "V");
            } else {
                param.put("AktSte15", "");
            }
            
            if (chkAktSte30.isSelected() == true) {
                param.put("AktSte30", "V");
            } else {
                param.put("AktSte30", "");
            }
            
            if (chkAktSte45.isSelected() == true) {
                param.put("AktSte45", "V");
            } else {
                param.put("AktSte45", "");
            }
            
            if (chkAktSte60.isSelected() == true) {
                param.put("AktSte60", "V");
            } else {
                param.put("AktSte60", "");
            }
            
            if (chkAktSte90.isSelected() == true) {
                param.put("AktSte90", "V");
            } else {
                param.put("AktSte90", "");
            }
            
            if (chkAktSte120.isSelected() == true) {
                param.put("AktSte120", "V");
            } else {
                param.put("AktSte120", "");
            }
            
            if (chkAktSte150.isSelected() == true) {
                param.put("AktSte150", "V");
            } else {
                param.put("AktSte150", "");
            }
            
            if (TdiscarAktSte.getText().equals("")) {
                param.put("discarAktSte", ".........");
            } else {
                param.put("discarAktSte", TdiscarAktSte.getText());
            }
            
            param.put("totalSte", TskorTotSte.getText());
            
            if (TtdPreAnesBrom.getText().equals("")) {
                param.put("tdBrom", "....... mmHg");
            } else {
                param.put("tdBrom", TtdPreAnesBrom.getText() + " mmHg");
            }
            
            param.put("kesadaranBro", cmbKesadaranBrom.getSelectedItem().toString());
            param.put("skorKesBro", TskorKesBrom.getText());
            
            if (chkKesBro15.isSelected() == true) {
                param.put("KesBro15", "V");
            } else {
                param.put("KesBro15", "");
            }
            
            if (chkKesBro30.isSelected() == true) {
                param.put("KesBro30", "V");
            } else {
                param.put("KesBro30", "");
            }
            
            if (chkKesBro45.isSelected() == true) {
                param.put("KesBro45", "V");
            } else {
                param.put("KesBro45", "");
            }
            
            if (chkKesBro60.isSelected() == true) {
                param.put("KesBro60", "V");
            } else {
                param.put("KesBro60", "");
            }
            
            if (chkKesBro90.isSelected() == true) {
                param.put("KesBro90", "V");
            } else {
                param.put("KesBro90", "");
            }
            
            if (chkKesBro120.isSelected() == true) {
                param.put("KesBro120", "V");
            } else {
                param.put("KesBro120", "");
            }
            
            if (chkKesBro150.isSelected() == true) {
                param.put("KesBro150", "V");
            } else {
                param.put("KesBro150", "");
            }
            
            if (TdiscarKesBrom.getText().equals("")) {
                param.put("discarKesBro", ".........");
            } else {
                param.put("discarKesBro", TdiscarKesBrom.getText());
            }
            
            if (Tinfus.getText().equals("")) {
                param.put("infus", ".........");
            } else {
                param.put("infus", Tinfus.getText());
            }

            if (Tpuasa.getText().equals("")) {
                param.put("puasa", ".........");
            } else {
                param.put("puasa", Tpuasa.getText());
            }
            
            if (Tminum.getText().equals("")) {
                param.put("minum", ".........");
            } else {
                param.put("minum", Tminum.getText());
            }
            
            if (Tbila.getText().equals("")) {
                param.put("bila", ".........");
            } else {
                param.put("bila", Tbila.getText());
            }
            
            if (Tanalgetik.getText().equals("")) {
                param.put("anal", ".........");
            } else {
                param.put("anal", Tanalgetik.getText());
            }
            
            if (Tantiemetik.getText().equals("")) {
                param.put("anti", ".........");
            } else {
                param.put("anti", Tantiemetik.getText());
            }
            
            if (TobatLain.getText().equals("")) {
                param.put("obatLain", ".........");
            } else {
                param.put("obatLain", TobatLain.getText());
            }
            
            if (TobatA.getText().equals("")) {
                param.put("obatA", ".........");
            } else {
                param.put("obatA", TobatA.getText());
            }
            
            if (TobatB.getText().equals("")) {
                param.put("obatB", ".........");
            } else {
                param.put("obatB", TobatB.getText());
            }
            
            if (cmbPasienBoleh.getSelectedIndex() == 0) {
                param.put("pasienBlh", cmbPasienBoleh.getSelectedItem().toString() + ", Jam ........ Wita");
            } else {
                param.put("pasienBlh", cmbPasienBoleh.getSelectedItem().toString() + ", Jam " + cmbJam3.getSelectedItem().toString() + ":" + cmbMnt3.getSelectedItem().toString() + " Wita");
            }
            
            if (Tlainlain.getText().equals("")) {
                param.put("lainlain", ".........");
            } else {
                param.put("lainlain", Tlainlain.getText());
            }
            
            if (Tposisi.getText().equals("")) {
                param.put("posisi", ".........");
            } else {
                param.put("posisi", Tposisi.getText());
            }
            
            if (Tkateter.getText().equals("")) {
                param.put("kateter", ".........");
            } else {
                param.put("kateter", Tkateter.getText());
            }
            
            if (Tinstruksi.getText().equals("")) {
                param.put("instruksi", ".........");
            } else {
                param.put("instruksi", Tinstruksi.getText());
            }
            
            if (TnmDrAnestesi.getText().equals("") || TnmDrAnestesi.getText().equals("-") || TnmDrAnestesi.getText().equals("--")) {
                param.put("dokterAnes", "(..........................)");
            } else {
                param.put("dokterAnes", "(" + TnmDrAnestesi.getText() + ")");
            }
            
            if (TnmPenataAnes.getText().equals("") || TnmPenataAnes.getText().equals("-") || TnmPenataAnes.getText().equals("--")) {
                param.put("penataAnes", "(..........................)");
            } else {
                param.put("penataAnes", "(" + TnmPenataAnes.getText() + ")");
            }
            
            if (TnmPerawatBangsal.getText().equals("") || TnmPerawatBangsal.getText().equals("-") || TnmPerawatBangsal.getText().equals("--")) {
                param.put("perawat", "(..........................)");
            } else {
                param.put("perawat", "(" + TnmPerawatBangsal.getText() + ")");
            }
            
            param.put("tglSerah", "Serah terima Tgl. " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglSerah.getSelectedItem() + "")) + ", Jam "
                    + cmbJam4.getSelectedItem().toString() + ":" + cmbMnt4.getSelectedItem().toString() + " Wita");
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isiDokter = "", isiPenata = "", isiPerawat = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from catatan_ruang_pemulihan where "
                        + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'");
                jamSimpan = Sequel.cariIsi("select time(waktu_simpan) from catatan_ruang_pemulihan where "
                        + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                
                //dokter anestesi
                if (nipDrAnes.equals("") || nipDrAnes.equals("-") || nipDrAnes.equals("--")) {
                    param.put("lokasiQrDokter", "");
                } else {
                    isiDokter = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Catatan Ruang Pemulihan", TnmDrAnestesi.getText() + " (Dokter Anestesi)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiDokter, Sequel.cariFolderTte(), "QRTteDokter.jpg", "select logo from setting");
                    param.put("lokasiQrDokter", Sequel.cariFolderTte() + File.separator + "QRTteDokter.jpg");
                }
                
                //penata anestesi
                if (nipPenata.equals("") || nipPenata.equals("-") || nipPenata.equals("--")) {
                    param.put("lokasiQrPenata", "");
                } else {
                    isiPenata = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Catatan Ruang Pemulihan", TnmPenataAnes.getText() + " (Penata Anestesi/Perawat RR)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPenata, Sequel.cariFolderTte(), "QRTtePenata.jpg", "select logo from setting");
                    param.put("lokasiQrPenata", Sequel.cariFolderTte() + File.separator + "QRTtePenata.jpg");
                }
                
                //perawat
                if (nipPerawat.equals("") || nipPerawat.equals("-") || nipPerawat.equals("--")) {
                    param.put("lokasiQrPerawat", "");
                } else {
                    isiPerawat = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Catatan Ruang Pemulihan", TnmPerawatBangsal.getText() + " (Perawat Bangsal)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isiPerawat, Sequel.cariFolderTte(), "QRTtePerawat.jpg", "select logo from setting");
                    param.put("lokasiQrPerawat", Sequel.cariFolderTte() + File.separator + "QRTtePerawat.jpg");
                }

                //data observasi ttv
                if (Sequel.cariInteger("select count(-1) from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'") > 0) {
                    Valid.MyReport("rptCatatanRuangPemulihanQr.jasper", "report", "::[ Catatan Ruang Pemulihan ]::",
                            "select no_rawat, urutan, TIME_FORMAT(pukul,'%H:%i:%s') pukul, td_sistole, td_diastole, nadi, rr, suhu, spo2, waktu_simpan "
                            + "from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "' "
                            + "order by urutan", param);
                } else {
                    Valid.MyReport("rptCatatanRuangPemulihanQr.jasper", "report", "::[ Catatan Ruang Pemulihan ]::",
                            "SELECT '-' no_rawat, '-' urutan, '-' pukul, '-' td_sistole, '-' td_diastole , '-' nadi, '-' rr, '-' suhu, '-' spo2, '-' waktu_simpan FROM dual "
                            + "WHERE NOT EXISTS (SELECT 1 FROM catatan_ruang_pemulihan_obs_ttv WHERE "
                            + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "')", param);
                }

                emptTeks();
                tampil();
                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
            } else {
                //data observasi ttv
                if (Sequel.cariInteger("select count(-1) from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "'") > 0) {
                    Valid.MyReport("rptCatatanRuangPemulihan.jasper", "report", "::[ Catatan Ruang Pemulihan ]::",
                            "select no_rawat, urutan, TIME_FORMAT(pukul,'%H:%i:%s') pukul, td_sistole, td_diastole, nadi, rr, suhu, spo2, waktu_simpan "
                            + "from catatan_ruang_pemulihan_obs_ttv where waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "' "
                            + "order by urutan", param);
                } else {
                    Valid.MyReport("rptCatatanRuangPemulihan.jasper", "report", "::[ Catatan Ruang Pemulihan ]::",
                            "SELECT '-' no_rawat, '-' urutan, '-' pukul, '-' td_sistole, '-' td_diastole , '-' nadi, '-' rr, '-' suhu, '-' spo2, '-' waktu_simpan FROM dual "
                            + "WHERE NOT EXISTS (SELECT 1 FROM catatan_ruang_pemulihan_obs_ttv WHERE "
                            + "waktu_simpan='" + tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString() + "')", param);
                }
                
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbCatatan.requestFocus();
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
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnPerawatBangsalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatBangsalActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 2;
        akses.setform("RMCatatanRuangPemulihan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatBangsalActionPerformed

    private void BtnDrAnestesiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDrAnestesiActionPerformed
        akses.setform("RMCatatanRuangPemulihan");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDrAnestesiActionPerformed

    private void BtnPenataAnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPenataAnesActionPerformed
        pilihPetugas = 0;
        pilihPetugas = 1;
        akses.setform("RMCatatanRuangPemulihan");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPenataAnesActionPerformed

    private void cmbJam3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam3MouseReleased
        AutoCompleteDecorator.decorate(cmbJam3);
    }//GEN-LAST:event_cmbJam3MouseReleased

    private void cmbMnt3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt3MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt3);
    }//GEN-LAST:event_cmbMnt3MouseReleased

    private void cmbDtk3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk3MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk3);
    }//GEN-LAST:event_cmbDtk3MouseReleased

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbJam4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam4MouseReleased
        AutoCompleteDecorator.decorate(cmbJam4);
    }//GEN-LAST:event_cmbJam4MouseReleased

    private void cmbMnt4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt4MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt4);
    }//GEN-LAST:event_cmbMnt4MouseReleased

    private void cmbDtk4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk4MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk4);
    }//GEN-LAST:event_cmbDtk4MouseReleased

    private void TketJlnNafasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketJlnNafasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPernafasanCttn.requestFocus();
        }
    }//GEN-LAST:event_TketJlnNafasKeyPressed

    private void cmbJlnNafasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJlnNafasActionPerformed
        TketJlnNafas.setText("");
        if (cmbJlnNafas.getSelectedIndex() == 2) {
            TketJlnNafas.setEnabled(true);
            TketJlnNafas.requestFocus();
        } else {
            TketJlnNafas.setEnabled(false);
        }
    }//GEN-LAST:event_cmbJlnNafasActionPerformed

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void tbTTVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTTVMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataTtv();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbTTVMouseClicked

    private void tbTTVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTTVKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataTtv();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbTTVKeyPressed

    private void BtnBaruTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruTtvActionPerformed
        emptTeksTTV();
        urutkanDataTTV();
    }//GEN-LAST:event_BtnBaruTtvActionPerformed

    private void TsistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tdistol.requestFocus();
        }
    }//GEN-LAST:event_TsistolKeyPressed

    private void TdistolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdistolKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tnadi.requestFocus();
        }
    }//GEN-LAST:event_TdistolKeyPressed

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
            Tspo.requestFocus();
        }
    }//GEN-LAST:event_TsuhuKeyPressed

    private void BtnTambahTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahTtvActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            urutData = "1";
            if (tabMode1.getRowCount() > 0) {
                int max = 0;
                for (int i = 0; i < tabMode1.getRowCount(); i++) {
                    int nilai = Integer.parseInt(tabMode1.getValueAt(i, 1).toString());
                    if (nilai > max) {
                        max = nilai;
                    }
                }
                urutData = String.valueOf(max + 1);
            }

            tabMode1.addRow(new String[]{TNoRw.getText(), urutData, cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                Tsistol.getText(), Tdistol.getText(), Tnadi.getText(), Trr.getText(), Tsuhu.getText(), Tspo.getText(), ""});
            BtnBaruTtvActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTambahTtvActionPerformed

    private void BtnHapusTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusTtvActionPerformed
        if (tbTTV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data observasi tanda vital yang bisa dihapus..!!");
        } else {
            if (tbTTV.getSelectedRow() > -1) {
                int row = tbTTV.convertRowIndexToModel(tbTTV.getSelectedRow());
                tabMode1.removeRow(row);
                BtnBaruTtvActionPerformed(null);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi tanda vital..!!");
                tbTTV.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnHapusTtvActionPerformed

    private void BtnGantiTtvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiTtvActionPerformed
        if (tbTTV.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Tidak ada data observasi tanda vital yang bisa diganti..!!");
        } else {
            if (tbTTV.getSelectedRow() > -1) {
                if (TNoRw.getText().equals("")) {
                    Valid.textKosong(TNoRw, "Nama Pasien");
                } else {
                    int row = tbTTV.convertRowIndexToModel(tbTTV.getSelectedRow());
                    tabMode1.setValueAt(TNoRw.getText(), row, 0);
                    tabMode1.setValueAt(urutanKe, row, 1);
                    tabMode1.setValueAt(cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(), row, 2);
                    tabMode1.setValueAt(Tsistol.getText(), row, 3);
                    tabMode1.setValueAt(Tdistol.getText(), row, 4);
                    tabMode1.setValueAt(Tnadi.getText(), row, 5);
                    tabMode1.setValueAt(Trr.getText(), row, 6);
                    tabMode1.setValueAt(Tsuhu.getText(), row, 7);
                    tabMode1.setValueAt(Tspo.getText(), row, 8);
                    tabMode1.setValueAt("", row, 9);

                    BtnBaruTtvActionPerformed(null);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel observasi tanda vital..!!");
                tbTTV.requestFocus();
            }
        }
    }//GEN-LAST:event_BtnGantiTtvActionPerformed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnTambahTtv.requestFocus();
        }
    }//GEN-LAST:event_TspoKeyPressed

    private void cmbPernafasanCttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernafasanCttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPernafasanCttnActionPerformed

    private void cmbBilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBilaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBilaActionPerformed

    private void cmbKesadaranCttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesadaranCttnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKesadaranCttnActionPerformed

    private void TtdPreAnesAldretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdPreAnesAldretKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSiskulasi.requestFocus();
        }
    }//GEN-LAST:event_TtdPreAnesAldretKeyPressed

    private void cmbSiskulasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSiskulasiActionPerformed
        hitungAldret();
    }//GEN-LAST:event_cmbSiskulasiActionPerformed

    private void TdiscarSisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarSisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKesadaranAldret.requestFocus();
        }
    }//GEN-LAST:event_TdiscarSisKeyPressed

    private void cmbKesadaranAldretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesadaranAldretActionPerformed
        hitungAldret();
    }//GEN-LAST:event_cmbKesadaranAldretActionPerformed

    private void TdiscarKesAldretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarKesAldretKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPernafasanAldret.requestFocus();
        }
    }//GEN-LAST:event_TdiscarKesAldretKeyPressed

    private void cmbPernafasanAldretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernafasanAldretActionPerformed
        hitungAldret();
    }//GEN-LAST:event_cmbPernafasanAldretActionPerformed

    private void TdiscarPerAldretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarPerAldretKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbWrnKulit.requestFocus();
        }
    }//GEN-LAST:event_TdiscarPerAldretKeyPressed

    private void cmbWrnKulitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWrnKulitActionPerformed
        hitungAldret();
    }//GEN-LAST:event_cmbWrnKulitActionPerformed

    private void TdiscarWarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarWarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAktifitasAldret.requestFocus();
        }
    }//GEN-LAST:event_TdiscarWarKeyPressed

    private void cmbAktifitasAldretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAktifitasAldretActionPerformed
        hitungAldret();
    }//GEN-LAST:event_cmbAktifitasAldretActionPerformed

    private void TdiscarAktAldretKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarAktAldretKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtdPreAnesSte.requestFocus();
        }
    }//GEN-LAST:event_TdiscarAktAldretKeyPressed

    private void TtdPreAnesSteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdPreAnesSteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKesadaranSte.requestFocus();
        }
    }//GEN-LAST:event_TtdPreAnesSteKeyPressed

    private void cmbKesadaranSteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesadaranSteActionPerformed
        hitungSteward();
    }//GEN-LAST:event_cmbKesadaranSteActionPerformed

    private void TdiscarKesSteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarKesSteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPernafasanSte.requestFocus();
        }
    }//GEN-LAST:event_TdiscarKesSteKeyPressed

    private void cmbPernafasanSteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernafasanSteActionPerformed
        hitungSteward();
    }//GEN-LAST:event_cmbPernafasanSteActionPerformed

    private void TdiscarPerSteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarPerSteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAktifitasSte.requestFocus();
        }
    }//GEN-LAST:event_TdiscarPerSteKeyPressed

    private void cmbAktifitasSteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAktifitasSteActionPerformed
        hitungSteward();
    }//GEN-LAST:event_cmbAktifitasSteActionPerformed

    private void TdiscarAktSteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarAktSteKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtdPreAnesBrom.requestFocus();
        }
    }//GEN-LAST:event_TdiscarAktSteKeyPressed

    private void TtdPreAnesBromKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdPreAnesBromKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbKesadaranBrom.requestFocus();
        }
    }//GEN-LAST:event_TtdPreAnesBromKeyPressed

    private void cmbKesadaranBromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKesadaranBromActionPerformed
        if (cmbKesadaranBrom.getSelectedIndex() == 1) {
            TskorKesBrom.setText("0");
        } else if (cmbKesadaranBrom.getSelectedIndex() == 2) {
            TskorKesBrom.setText("1");
        } else if (cmbKesadaranBrom.getSelectedIndex() == 3) {
            TskorKesBrom.setText("2");
        } else if (cmbKesadaranBrom.getSelectedIndex() == 4) {
            TskorKesBrom.setText("3");
        } else {
            TskorKesBrom.setText("");
        }
    }//GEN-LAST:event_cmbKesadaranBromActionPerformed

    private void TdiscarKesBromKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiscarKesBromKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tinfus.requestFocus();
        }
    }//GEN-LAST:event_TdiscarKesBromKeyPressed

    private void TpuasaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpuasaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tminum.requestFocus();
        }
    }//GEN-LAST:event_TpuasaKeyPressed

    private void TminumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TminumKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbila.requestFocus();
        }
    }//GEN-LAST:event_TminumKeyPressed

    private void TinfusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TinfusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tpuasa.requestFocus();
        }
    }//GEN-LAST:event_TinfusKeyPressed

    private void TbilaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbilaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tanalgetik.requestFocus();
        }
    }//GEN-LAST:event_TbilaKeyPressed

    private void TanalgetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanalgetikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tantiemetik.requestFocus();
        }
    }//GEN-LAST:event_TanalgetikKeyPressed

    private void TantiemetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TantiemetikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TobatLain.requestFocus();
        }
    }//GEN-LAST:event_TantiemetikKeyPressed

    private void TobatAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatAKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TobatB.requestFocus();
        }
    }//GEN-LAST:event_TobatAKeyPressed

    private void TobatBKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPasienBoleh.requestFocus();
        }
    }//GEN-LAST:event_TobatBKeyPressed

    private void TlainlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TtglSerah.requestFocus();
        }
    }//GEN-LAST:event_TlainlainKeyPressed

    private void TposisiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TposisiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tkateter.requestFocus();
        }
    }//GEN-LAST:event_TposisiKeyPressed

    private void TkateterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkateterKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tinstruksi.requestFocus();
        }
    }//GEN-LAST:event_TkateterKeyPressed

    private void TobatLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TobatLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TobatA.requestFocus();
        }
    }//GEN-LAST:event_TobatLainKeyPressed

    private void cmbPasienBolehActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPasienBolehActionPerformed
        if (cmbPasienBoleh.getSelectedIndex() == 0) {
            cmbJam3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbMnt3.setSelectedIndex(0);
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);            
        } else {
            cmbJam3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
            cmbMnt3.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
            cmbDtk3.setSelectedIndex(0);
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
        }
    }//GEN-LAST:event_cmbPasienBolehActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCatatanRuangPemulihan dialog = new RMCatatanRuangPemulihan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaruTtv;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDrAnestesi;
    private widget.Button BtnGanti;
    private widget.Button BtnGantiTtv;
    private widget.Button BtnHapus;
    private widget.Button BtnHapusTtv;
    private widget.Button BtnKeluar;
    private widget.Button BtnPenataAnes;
    private widget.Button BtnPerawatBangsal;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambahTtv;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tanalgetik;
    private widget.TextBox Tantiemetik;
    private widget.TextBox Tbila;
    private widget.TextBox TdiscarAktAldret;
    private widget.TextBox TdiscarAktSte;
    private widget.TextBox TdiscarKesAldret;
    private widget.TextBox TdiscarKesBrom;
    private widget.TextBox TdiscarKesSte;
    private widget.TextBox TdiscarPerAldret;
    private widget.TextBox TdiscarPerSte;
    private widget.TextBox TdiscarSis;
    private widget.TextBox TdiscarWar;
    private widget.TextBox Tdistol;
    private widget.TextArea Tinfus;
    private widget.TextArea Tinstruksi;
    private widget.TextArea Tkateter;
    private widget.TextBox TketJlnNafas;
    private widget.TextArea Tlainlain;
    private widget.TextBox Tminum;
    private widget.TextBox Tnadi;
    private widget.TextBox TnmDrAnestesi;
    private widget.TextBox TnmPenataAnes;
    private widget.TextBox TnmPerawatBangsal;
    private widget.TextBox TobatA;
    private widget.TextBox TobatB;
    private widget.TextBox TobatLain;
    private widget.TextArea Tposisi;
    private widget.TextBox Tpuasa;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tsistol;
    private widget.TextBox TskorAktAldret;
    private widget.TextBox TskorAktSte;
    private widget.TextBox TskorKesAldret;
    private widget.TextBox TskorKesBrom;
    private widget.TextBox TskorKesSte;
    private widget.TextBox TskorPerAldret;
    private widget.TextBox TskorPerSte;
    private widget.TextBox TskorSis;
    private widget.TextBox TskorTotAldret;
    private widget.TextBox TskorTotSte;
    private widget.TextBox TskorWar;
    private widget.TextBox Tspo;
    private widget.TextBox Tsuhu;
    private widget.TextBox TtdPreAnesAldret;
    private widget.TextBox TtdPreAnesBrom;
    private widget.TextBox TtdPreAnesSte;
    private widget.Tanggal TtglSerah;
    public widget.CekBox chkAktAld120;
    public widget.CekBox chkAktAld15;
    public widget.CekBox chkAktAld150;
    public widget.CekBox chkAktAld30;
    public widget.CekBox chkAktAld45;
    public widget.CekBox chkAktAld60;
    public widget.CekBox chkAktAld90;
    public widget.CekBox chkAktSte120;
    public widget.CekBox chkAktSte15;
    public widget.CekBox chkAktSte150;
    public widget.CekBox chkAktSte30;
    public widget.CekBox chkAktSte45;
    public widget.CekBox chkAktSte60;
    public widget.CekBox chkAktSte90;
    public widget.CekBox chkKesAld120;
    public widget.CekBox chkKesAld15;
    public widget.CekBox chkKesAld150;
    public widget.CekBox chkKesAld30;
    public widget.CekBox chkKesAld45;
    public widget.CekBox chkKesAld60;
    public widget.CekBox chkKesAld90;
    public widget.CekBox chkKesBro120;
    public widget.CekBox chkKesBro15;
    public widget.CekBox chkKesBro150;
    public widget.CekBox chkKesBro30;
    public widget.CekBox chkKesBro45;
    public widget.CekBox chkKesBro60;
    public widget.CekBox chkKesBro90;
    public widget.CekBox chkKesSte120;
    public widget.CekBox chkKesSte15;
    public widget.CekBox chkKesSte150;
    public widget.CekBox chkKesSte30;
    public widget.CekBox chkKesSte45;
    public widget.CekBox chkKesSte60;
    public widget.CekBox chkKesSte90;
    public widget.CekBox chkPerAld120;
    public widget.CekBox chkPerAld15;
    public widget.CekBox chkPerAld150;
    public widget.CekBox chkPerAld30;
    public widget.CekBox chkPerAld45;
    public widget.CekBox chkPerAld60;
    public widget.CekBox chkPerAld90;
    public widget.CekBox chkPerSte120;
    public widget.CekBox chkPerSte15;
    public widget.CekBox chkPerSte150;
    public widget.CekBox chkPerSte30;
    public widget.CekBox chkPerSte45;
    public widget.CekBox chkPerSte60;
    public widget.CekBox chkPerSte90;
    public widget.CekBox chkSis120;
    public widget.CekBox chkSis15;
    public widget.CekBox chkSis150;
    public widget.CekBox chkSis30;
    public widget.CekBox chkSis45;
    public widget.CekBox chkSis60;
    public widget.CekBox chkSis90;
    public widget.CekBox chkWar120;
    public widget.CekBox chkWar15;
    public widget.CekBox chkWar150;
    public widget.CekBox chkWar30;
    public widget.CekBox chkWar45;
    public widget.CekBox chkWar60;
    public widget.CekBox chkWar90;
    private widget.ComboBox cmbAktifitasAldret;
    private widget.ComboBox cmbAktifitasSte;
    private widget.ComboBox cmbBila;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbDtk3;
    private widget.ComboBox cmbDtk4;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbJam3;
    private widget.ComboBox cmbJam4;
    private widget.ComboBox cmbJlnNafas;
    private widget.ComboBox cmbKesadaranAldret;
    private widget.ComboBox cmbKesadaranBrom;
    private widget.ComboBox cmbKesadaranCttn;
    private widget.ComboBox cmbKesadaranSte;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbMnt3;
    private widget.ComboBox cmbMnt4;
    private widget.ComboBox cmbPasienBoleh;
    private widget.ComboBox cmbPernafasanAldret;
    private widget.ComboBox cmbPernafasanCttn;
    private widget.ComboBox cmbPernafasanSte;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbSiskulasi;
    private widget.ComboBox cmbWrnKulit;
    private widget.InternalFrame internalFrame1;
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
    private widget.Label jLabel116;
    private widget.Label jLabel117;
    private widget.Label jLabel118;
    private widget.Label jLabel119;
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
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel276;
    private widget.Label jLabel277;
    private widget.Label jLabel278;
    private widget.Label jLabel279;
    private widget.Label jLabel280;
    private widget.Label jLabel281;
    private widget.Label jLabel282;
    private widget.Label jLabel283;
    private widget.Label jLabel284;
    private widget.Label jLabel285;
    private widget.Label jLabel287;
    private widget.Label jLabel288;
    private widget.Label jLabel289;
    private widget.Label jLabel290;
    private widget.Label jLabel291;
    private widget.Label jLabel292;
    private widget.Label jLabel293;
    private widget.Label jLabel294;
    private widget.Label jLabel295;
    private widget.Label jLabel296;
    private widget.Label jLabel297;
    private widget.Label jLabel298;
    private widget.Label jLabel299;
    private widget.Label jLabel300;
    private widget.Label jLabel301;
    private widget.Label jLabel302;
    private widget.Label jLabel303;
    private widget.Label jLabel304;
    private widget.Label jLabel305;
    private widget.Label jLabel306;
    private widget.Label jLabel307;
    private widget.Label jLabel308;
    private widget.Label jLabel309;
    private widget.Label jLabel310;
    private widget.Label jLabel311;
    private widget.Label jLabel312;
    private widget.Label jLabel313;
    private widget.Label jLabel314;
    private widget.Label jLabel315;
    private widget.Label jLabel316;
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
    private widget.Label jLabel77;
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel89;
    private widget.Label jLabel94;
    private widget.Label jLabel95;
    private widget.Label jLabel96;
    private widget.Label jLabel97;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.ScrollPane scrollPane15;
    private widget.ScrollPane scrollPane16;
    private widget.ScrollPane scrollPane17;
    private widget.Table tbCatatan;
    private widget.Table tbTTV;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select cr.*, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, pg1.nama drAnes, pg2.nama nmPenata, "
                    + "pg3.nama nmPerawat from catatan_ruang_pemulihan cr inner join reg_periksa rp on rp.no_rawat=cr.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=cr.nip_dokter_anestesi "
                    + "inner join pegawai pg2 on pg2.nik=cr.nip_penata inner join pegawai pg3 on pg3.nik=cr.nip_perawat_bangsal where "
                    + "cr.tgl_serah_terima between ? and ? and cr.no_rawat LIKE ? or "
                    + "cr.tgl_serah_terima between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "cr.tgl_serah_terima between ? and ? and p.nm_pasien LIKE ? or "
                    + "cr.tgl_serah_terima between ? and ? and pg1.nama LIKE ? or "
                    + "cr.tgl_serah_terima between ? and ? and pg2.nama LIKE ? or "
                    + "cr.tgl_serah_terima between ? and ? and pg3.nama LIKE ? or "                    
                    + "cr.tgl_serah_terima between ? and ? and cr.ruang_rawat LIKE ? ORDER BY cr.tgl_serah_terima desc");
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
                        rs.getString("drAnes"),
                        rs.getString("nmPenata"),
                        rs.getString("nmPerawat"),
                        rs.getString("jam_msk"),
                        rs.getString("jalan_nafas"),
                        rs.getString("ket_jalan_nafas"),
                        rs.getString("pernapasan_cttn"),
                        rs.getString("bila_spontan"),
                        rs.getString("kesadaran_cttn"),
                        rs.getString("td_pre_anestesi"),
                        rs.getString("siskulasi"),
                        rs.getString("sis_menit_15"),
                        rs.getString("sis_menit_30"),
                        rs.getString("sis_menit_45"),
                        rs.getString("sis_menit_60"),
                        rs.getString("sis_menit_90"),
                        rs.getString("sis_menit_120"),
                        rs.getString("sis_menit_150"),
                        rs.getString("siskulasi_discharge"),
                        rs.getString("kesadaran"),
                        rs.getString("kes_menit_15"),
                        rs.getString("kes_menit_30"),
                        rs.getString("kes_menit_45"),
                        rs.getString("kes_menit_60"),
                        rs.getString("kes_menit_90"),
                        rs.getString("kes_menit_120"),
                        rs.getString("kes_menit_150"),
                        rs.getString("kesadaran_discharge"),
                        rs.getString("pernapasan"),
                        rs.getString("per_menit_15"),
                        rs.getString("per_menit_30"),
                        rs.getString("per_menit_45"),
                        rs.getString("per_menit_60"),
                        rs.getString("per_menit_90"),
                        rs.getString("per_menit_120"),
                        rs.getString("per_menit_150"),
                        rs.getString("pernapasan_discharge"),
                        rs.getString("warna_kulit"),
                        rs.getString("war_menit_15"),
                        rs.getString("war_menit_30"),
                        rs.getString("war_menit_45"),
                        rs.getString("war_menit_60"),
                        rs.getString("war_menit_90"),
                        rs.getString("war_menit_120"),
                        rs.getString("war_menit_150"),
                        rs.getString("warna_kulit_discharge"),
                        rs.getString("aktifitas"),
                        rs.getString("akt_menit_15"),
                        rs.getString("akt_menit_30"),
                        rs.getString("akt_menit_45"),
                        rs.getString("akt_menit_60"),
                        rs.getString("akt_menit_90"),
                        rs.getString("akt_menit_120"),
                        rs.getString("akt_menit_150"),
                        rs.getString("aktifitas_discharge"),
                        rs.getString("td_pre_anestesi_steward"),
                        rs.getString("kesadaran_steward"),
                        rs.getString("kes_ste_menit_15"),
                        rs.getString("kes_ste_menit_30"),
                        rs.getString("kes_ste_menit_45"),
                        rs.getString("kes_ste_menit_60"),
                        rs.getString("kes_ste_menit_90"),
                        rs.getString("kes_ste_menit_120"),
                        rs.getString("kes_ste_menit_150"),
                        rs.getString("kesadaran_discharge_steward"),
                        rs.getString("pernapasan_steward"),
                        rs.getString("per_ste_menit_15"),
                        rs.getString("per_ste_menit_30"),
                        rs.getString("per_ste_menit_45"),
                        rs.getString("per_ste_menit_60"),
                        rs.getString("per_ste_menit_90"),
                        rs.getString("per_ste_menit_120"),
                        rs.getString("per_ste_menit_150"),
                        rs.getString("pernapasan_discharge_steward"),
                        rs.getString("aktifitas_mot_steward"),
                        rs.getString("akt_ste_menit_15"),
                        rs.getString("akt_ste_menit_30"),
                        rs.getString("akt_ste_menit_45"),
                        rs.getString("akt_ste_menit_60"),
                        rs.getString("akt_ste_menit_90"),
                        rs.getString("akt_ste_menit_120"),
                        rs.getString("akt_ste_menit_150"),
                        rs.getString("aktifitas_discharge_steward"),
                        rs.getString("td_pre_anestesi_brom"),
                        rs.getString("kesadaran_brom"),
                        rs.getString("kes_brom_menit_15"),
                        rs.getString("kes_brom_menit_30"),
                        rs.getString("kes_brom_menit_45"),
                        rs.getString("kes_brom_menit_60"),
                        rs.getString("kes_brom_menit_90"),
                        rs.getString("kes_brom_menit_120"),
                        rs.getString("kes_brom_menit_150"),
                        rs.getString("kesadaran_discharge_brom"),
                        rs.getString("infus"),
                        rs.getString("puasa"),
                        rs.getString("minum_makan_jam"),
                        rs.getString("bila"),
                        rs.getString("analgetik"),
                        rs.getString("antiemetik"),
                        rs.getString("obat_lain"),
                        rs.getString("obat_medikasi_a"),
                        rs.getString("obat_medikasi_b"),
                        rs.getString("pasien_boleh_pindah"),
                        rs.getString("jam_pindah"),
                        rs.getString("lain_lain"),
                        rs.getString("posisi_tidur"),
                        rs.getString("kateter_epidural"),
                        rs.getString("instruksi_khusus"),
                        rs.getString("nip_dokter_anestesi"),
                        rs.getString("nip_penata"),
                        rs.getString("nip_perawat_bangsal"),
                        rs.getString("tgl_serah_terima"),
                        rs.getString("jam_serah_terima"),
                        rs.getString("waktu_simpan")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMCatatanRuangPemulihan.tampil() : " + e);
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
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));        
        cmbDtk1.setSelectedIndex(0);
        cmbJlnNafas.setSelectedIndex(0);
        TketJlnNafas.setText("");
        TketJlnNafas.setEnabled(false);
        cmbPernafasanCttn.setSelectedIndex(0);
        cmbBila.setSelectedIndex(0);
        cmbKesadaranCttn.setSelectedIndex(0);
        Valid.tabelKosong(tabMode1);
        emptTeksTTV();
        
        TtdPreAnesAldret.setText("");
        cmbSiskulasi.setSelectedIndex(0);
        cmbKesadaranAldret.setSelectedIndex(0);
        cmbPernafasanAldret.setSelectedIndex(0);
        cmbWrnKulit.setSelectedIndex(0);
        cmbAktifitasAldret.setSelectedIndex(0);
        hitungAldret();
        chkSis15.setSelected(false);
        chkSis30.setSelected(false);
        chkSis45.setSelected(false);
        chkSis60.setSelected(false);
        chkSis90.setSelected(false);
        chkSis120.setSelected(false);
        chkSis150.setSelected(false);        
        chkKesAld15.setSelected(false);
        chkKesAld30.setSelected(false);
        chkKesAld45.setSelected(false);
        chkKesAld60.setSelected(false);
        chkKesAld90.setSelected(false);
        chkKesAld120.setSelected(false);
        chkKesAld150.setSelected(false);
        chkPerAld15.setSelected(false);
        chkPerAld30.setSelected(false);
        chkPerAld45.setSelected(false);
        chkPerAld60.setSelected(false);
        chkPerAld90.setSelected(false);
        chkPerAld120.setSelected(false);
        chkPerAld150.setSelected(false);
        chkWar15.setSelected(false);
        chkWar30.setSelected(false);
        chkWar45.setSelected(false);
        chkWar60.setSelected(false);
        chkWar90.setSelected(false);
        chkWar120.setSelected(false);
        chkWar150.setSelected(false);
        chkAktAld15.setSelected(false);
        chkAktAld30.setSelected(false);
        chkAktAld45.setSelected(false);
        chkAktAld60.setSelected(false);
        chkAktAld90.setSelected(false);
        chkAktAld120.setSelected(false);
        chkAktAld150.setSelected(false);        
        TdiscarSis.setText("");
        TdiscarKesAldret.setText("");
        TdiscarPerAldret.setText("");
        TdiscarWar.setText("");
        TdiscarAktAldret.setText("");
        
        TtdPreAnesSte.setText("");
        cmbKesadaranSte.setSelectedIndex(0);
        cmbPernafasanSte.setSelectedIndex(0);
        cmbAktifitasSte.setSelectedIndex(0);
        hitungSteward();
        chkKesSte15.setSelected(false);
        chkKesSte30.setSelected(false);
        chkKesSte45.setSelected(false);
        chkKesSte60.setSelected(false);
        chkKesSte90.setSelected(false);
        chkKesSte120.setSelected(false);
        chkKesSte150.setSelected(false);
        chkPerSte15.setSelected(false);
        chkPerSte30.setSelected(false);
        chkPerSte45.setSelected(false);
        chkPerSte60.setSelected(false);
        chkPerSte90.setSelected(false);
        chkPerSte120.setSelected(false);
        chkPerSte150.setSelected(false);
        chkAktSte15.setSelected(false);
        chkAktSte30.setSelected(false);
        chkAktSte45.setSelected(false);
        chkAktSte60.setSelected(false);
        chkAktSte90.setSelected(false);
        chkAktSte120.setSelected(false);
        chkAktSte150.setSelected(false);
        TdiscarKesSte.setText("");
        TdiscarPerSte.setText("");
        TdiscarAktSte.setText("");
        
        TtdPreAnesBrom.setText("");
        cmbKesadaranBrom.setSelectedIndex(0);
        TskorKesBrom.setText("");
        chkKesBro15.setSelected(false);
        chkKesBro30.setSelected(false);
        chkKesBro45.setSelected(false);
        chkKesBro60.setSelected(false);
        chkKesBro90.setSelected(false);
        chkKesBro120.setSelected(false);
        chkKesBro150.setSelected(false);
        TdiscarKesBrom.setText("");
        
        Tinfus.setText("");        
        Tpuasa.setText("");
        Tminum.setText("");
        Tbila.setText("");
        Tanalgetik.setText("");
        Tantiemetik.setText("");
        TobatLain.setText("");
        TobatA.setText("");
        TobatB.setText("");
        cmbPasienBoleh.setSelectedIndex(0);
        cmbJam3.setEnabled(false);
        cmbMnt3.setEnabled(false);
        cmbDtk3.setEnabled(false);
        cmbJam3.setSelectedIndex(0);
        cmbMnt3.setSelectedIndex(0);
        cmbDtk3.setSelectedIndex(0);
        Tlainlain.setText("");        
        TtglSerah.setDate(new Date());        
        cmbJam4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt4.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));        
        cmbDtk4.setSelectedIndex(0);
        nipDrAnes = "-";
        nipPenata = "-";
        nipPerawat = "-";
        TnmDrAnestesi.setText("-");
        TnmPenataAnes.setText("-");
        TnmPerawatBangsal.setText("-");
        Tposisi.setText("");
        Tkateter.setText("");
        Tinstruksi.setText("");
    }

    private void getData() {
        variabelBersih();
        if (tbCatatan.getSelectedRow() != -1) {
            TNoRw.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 0).toString());
            TNoRM.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 1).toString());
            TPasien.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 4).toString());
            cmbJam1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 8).toString().substring(6, 8));
            cmbJlnNafas.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 9).toString());
            TketJlnNafas.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 10).toString());
            cmbPernafasanCttn.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 11).toString());
            cmbBila.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 12).toString());
            cmbKesadaranCttn.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 13).toString());
            emptTeksTTV();
            tampilTtv(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 118).toString());
            TtdPreAnesAldret.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 14).toString());
            cmbSiskulasi.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 15).toString());
            sis15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 16).toString();
            sis30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 17).toString();
            sis45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 18).toString();
            sis60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 19).toString();
            sis90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 20).toString();
            sis120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 21).toString();
            sis150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 22).toString();
            TdiscarSis.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 23).toString());
            cmbKesadaranAldret.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 24).toString());
            kesAld15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 25).toString();
            kesAld30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 26).toString();
            kesAld45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 27).toString();
            kesAld60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 28).toString();
            kesAld90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 29).toString();
            kesAld120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 30).toString();
            kesAld150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 31).toString();
            TdiscarKesAldret.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 32).toString());            
            cmbPernafasanAldret.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 33).toString());
            perAld15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 34).toString();
            perAld30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 35).toString();
            perAld45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 36).toString();
            perAld60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 37).toString();
            perAld90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 38).toString();
            perAld120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 39).toString();
            perAld150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 40).toString();
            TdiscarPerAldret.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 41).toString());
            cmbWrnKulit.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 42).toString());
            war15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 43).toString();
            war30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 44).toString();
            war45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 45).toString();
            war60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 46).toString();
            war90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 47).toString();
            war120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 48).toString();
            war150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 49).toString();
            TdiscarWar.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 50).toString());
            cmbAktifitasAldret.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 51).toString());
            aktAld15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 52).toString();
            aktAld30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 53).toString();
            aktAld45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 54).toString();
            aktAld60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 55).toString();
            aktAld90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 56).toString();
            aktAld120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 57).toString();
            aktAld150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 58).toString();
            TdiscarAktAldret.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 59).toString());
            hitungAldret();
            TtdPreAnesSte.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 60).toString());
            cmbKesadaranSte.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 61).toString());
            kesSte15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 62).toString();
            kesSte30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 63).toString();
            kesSte45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 64).toString();
            kesSte60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 65).toString();
            kesSte90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 66).toString();
            kesSte120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 67).toString();
            kesSte150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 68).toString();
            TdiscarKesSte.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 69).toString());
            cmbPernafasanSte.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 70).toString());
            perSte15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 71).toString();
            perSte30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 72).toString();
            perSte45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 73).toString();
            perSte60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 74).toString();
            perSte90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 75).toString();
            perSte120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 76).toString();
            perSte150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 77).toString();
            TdiscarPerSte.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 78).toString());
            cmbAktifitasSte.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 79).toString());
            aktSte15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 80).toString();
            aktSte30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 81).toString();
            aktSte45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 82).toString();
            aktSte60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 83).toString();
            aktSte90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 84).toString();
            aktSte120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 85).toString();
            aktSte150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 86).toString();
            TdiscarAktSte.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 87).toString());
            hitungSteward();
            TtdPreAnesBrom.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 88).toString());
            cmbKesadaranBrom.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 89).toString());
            kesBro15 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 90).toString();
            kesBro30 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 91).toString();
            kesBro45 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 92).toString();
            kesBro60 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 93).toString();
            kesBro90 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 94).toString();
            kesBro120 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 95).toString();
            kesBro150 = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 96).toString();
            TdiscarKesBrom.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 97).toString());
            Tinfus.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 98).toString());
            Tpuasa.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 99).toString());
            Tminum.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 100).toString());
            Tbila.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 101).toString());
            Tanalgetik.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 102).toString());
            Tantiemetik.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 103).toString());
            TobatLain.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 104).toString());
            TobatA.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 105).toString());
            TobatB.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 106).toString());
            cmbPasienBoleh.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 107).toString());
            cmbJam3.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 108).toString().substring(0, 2));
            cmbMnt3.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 108).toString().substring(3, 5));
            cmbDtk3.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 108).toString().substring(6, 8));
            Tlainlain.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 109).toString());
            Tposisi.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 110).toString());
            Tkateter.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 111).toString());
            Tinstruksi.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 112).toString());
            nipDrAnes = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 113).toString();
            nipPenata = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 114).toString();
            nipPerawat = tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 115).toString();
            TnmDrAnestesi.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 5).toString());
            TnmPenataAnes.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 6).toString());
            TnmPerawatBangsal.setText(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 7).toString());
            Valid.SetTgl(TtglSerah, tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 116).toString());
            cmbJam4.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 117).toString().substring(0, 2));
            cmbMnt4.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 117).toString().substring(3, 5));
            cmbDtk4.setSelectedItem(tbCatatan.getValueAt(tbCatatan.getSelectedRow(), 117).toString().substring(6, 8));            
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
    }
    
    private void dataCek() {
        if (cmbJlnNafas.getSelectedIndex() == 2) {
            TketJlnNafas.setEnabled(true);
        } else {
            TketJlnNafas.setEnabled(false);
        }        
        
        if (sis15.equals("ya")) {
            chkSis15.setSelected(true);
        } else {
            chkSis15.setSelected(false);
        }
        
        if (sis30.equals("ya")) {
            chkSis30.setSelected(true);
        } else {
            chkSis30.setSelected(false);
        }
        
        if (sis45.equals("ya")) {
            chkSis45.setSelected(true);
        } else {
            chkSis45.setSelected(false);
        }
        
        if (sis60.equals("ya")) {
            chkSis60.setSelected(true);
        } else {
            chkSis60.setSelected(false);
        }
        
        if (sis90.equals("ya")) {
            chkSis90.setSelected(true);
        } else {
            chkSis90.setSelected(false);
        }
        
        if (sis120.equals("ya")) {
            chkSis120.setSelected(true);
        } else {
            chkSis120.setSelected(false);
        }
        
        if (sis150.equals("ya")) {
            chkSis150.setSelected(true);
        } else {
            chkSis150.setSelected(false);
        }

        if (kesAld15.equals("ya")) {
            chkKesAld15.setSelected(true);
        } else {
            chkKesAld15.setSelected(false);
        }
        
        if (kesAld30.equals("ya")) {
            chkKesAld30.setSelected(true);
        } else {
            chkKesAld30.setSelected(false);
        }
        
        if (kesAld45.equals("ya")) {
            chkKesAld45.setSelected(true);
        } else {
            chkKesAld45.setSelected(false);
        }
        
        if (kesAld60.equals("ya")) {
            chkKesAld60.setSelected(true);
        } else {
            chkKesAld60.setSelected(false);
        }
        
        if (kesAld90.equals("ya")) {
            chkKesAld90.setSelected(true);
        } else {
            chkKesAld90.setSelected(false);
        }
        
        if (kesAld120.equals("ya")) {
            chkKesAld120.setSelected(true);
        } else {
            chkKesAld120.setSelected(false);
        }
        
        if (kesAld150.equals("ya")) {
            chkKesAld150.setSelected(true);
        } else {
            chkKesAld150.setSelected(false);
        }
        
        if (perAld15.equals("ya")) {
            chkPerAld15.setSelected(true);
        } else {
            chkPerAld15.setSelected(false);
        }
        
        if (perAld30.equals("ya")) {
            chkPerAld30.setSelected(true);
        } else {
            chkPerAld30.setSelected(false);
        }
        
        if (perAld45.equals("ya")) {
            chkPerAld45.setSelected(true);
        } else {
            chkPerAld45.setSelected(false);
        }
        
        if (perAld60.equals("ya")) {
            chkPerAld60.setSelected(true);
        } else {
            chkPerAld60.setSelected(false);
        }
        
        if (perAld90.equals("ya")) {
            chkPerAld90.setSelected(true);
        } else {
            chkPerAld90.setSelected(false);
        }
        
        if (perAld120.equals("ya")) {
            chkPerAld120.setSelected(true);
        } else {
            chkPerAld120.setSelected(false);
        }
        
        if (perAld150.equals("ya")) {
            chkPerAld150.setSelected(true);
        } else {
            chkPerAld150.setSelected(false);
        }
        
        if (war15.equals("ya")) {
            chkWar15.setSelected(true);
        } else {
            chkWar15.setSelected(false);
        }
        
        if (war30.equals("ya")) {
            chkWar30.setSelected(true);
        } else {
            chkWar30.setSelected(false);
        }
        
        if (war45.equals("ya")) {
            chkWar45.setSelected(true);
        } else {
            chkWar45.setSelected(false);
        }
        
        if (war60.equals("ya")) {
            chkWar60.setSelected(true);
        } else {
            chkWar60.setSelected(false);
        }
        
        if (war90.equals("ya")) {
            chkWar90.setSelected(true);
        } else {
            chkWar90.setSelected(false);
        }
        
        if (war120.equals("ya")) {
            chkWar120.setSelected(true);
        } else {
            chkWar120.setSelected(false);
        }
        
        if (war150.equals("ya")) {
            chkWar150.setSelected(true);
        } else {
            chkWar150.setSelected(false);
        }
        
        if (aktAld15.equals("ya")) {
            chkAktAld15.setSelected(true);
        } else {
            chkAktAld15.setSelected(false);
        }
        
        if (aktAld30.equals("ya")) {
            chkAktAld30.setSelected(true);
        } else {
            chkAktAld30.setSelected(false);
        }
        
        if (aktAld45.equals("ya")) {
            chkAktAld45.setSelected(true);
        } else {
            chkAktAld45.setSelected(false);
        }
        
        if (aktAld60.equals("ya")) {
            chkAktAld60.setSelected(true);
        } else {
            chkAktAld60.setSelected(false);
        }
        
        if (aktAld90.equals("ya")) {
            chkAktAld90.setSelected(true);
        } else {
            chkAktAld90.setSelected(false);
        }
        
        if (aktAld120.equals("ya")) {
            chkAktAld120.setSelected(true);
        } else {
            chkAktAld120.setSelected(false);
        }
        
        if (aktAld150.equals("ya")) {
            chkAktAld150.setSelected(true);
        } else {
            chkAktAld150.setSelected(false);
        }
        
        if (kesSte15.equals("ya")) {
            chkKesSte15.setSelected(true);
        } else {
            chkKesSte15.setSelected(false);
        }
        
        if (kesSte30.equals("ya")) {
            chkKesSte30.setSelected(true);
        } else {
            chkKesSte30.setSelected(false);
        }
        
        if (kesSte45.equals("ya")) {
            chkKesSte45.setSelected(true);
        } else {
            chkKesSte45.setSelected(false);
        }
        
        if (kesSte60.equals("ya")) {
            chkKesSte60.setSelected(true);
        } else {
            chkKesSte60.setSelected(false);
        }
        
        if (kesSte90.equals("ya")) {
            chkKesSte90.setSelected(true);
        } else {
            chkKesSte90.setSelected(false);
        }
        
        if (kesSte120.equals("ya")) {
            chkKesSte120.setSelected(true);
        } else {
            chkKesSte120.setSelected(false);
        }
        
        if (kesSte150.equals("ya")) {
            chkKesSte150.setSelected(true);
        } else {
            chkKesSte150.setSelected(false);
        }
        
        if (perSte15.equals("ya")) {
            chkPerSte15.setSelected(true);
        } else {
            chkPerSte15.setSelected(false);
        }
        
        if (perSte30.equals("ya")) {
            chkPerSte30.setSelected(true);
        } else {
            chkPerSte30.setSelected(false);
        }
        
        if (perSte45.equals("ya")) {
            chkPerSte45.setSelected(true);
        } else {
            chkPerSte45.setSelected(false);
        }
        
        if (perSte60.equals("ya")) {
            chkPerSte60.setSelected(true);
        } else {
            chkPerSte60.setSelected(false);
        }
        
        if (perSte90.equals("ya")) {
            chkPerSte90.setSelected(true);
        } else {
            chkPerSte90.setSelected(false);
        }
        
        if (perSte120.equals("ya")) {
            chkPerSte120.setSelected(true);
        } else {
            chkPerSte120.setSelected(false);
        }
        
        if (perSte150.equals("ya")) {
            chkPerSte150.setSelected(true);
        } else {
            chkPerSte150.setSelected(false);
        }
        
        if (aktSte15.equals("ya")) {
            chkAktSte15.setSelected(true);
        } else {
            chkAktSte15.setSelected(false);
        }
        
        if (aktSte30.equals("ya")) {
            chkAktSte30.setSelected(true);
        } else {
            chkAktSte30.setSelected(false);
        }
        
        if (aktSte45.equals("ya")) {
            chkAktSte45.setSelected(true);
        } else {
            chkAktSte45.setSelected(false);
        }
        
        if (aktSte60.equals("ya")) {
            chkAktSte60.setSelected(true);
        } else {
            chkAktSte60.setSelected(false);
        }
        
        if (aktSte90.equals("ya")) {
            chkAktSte90.setSelected(true);
        } else {
            chkAktSte90.setSelected(false);
        }
        
        if (aktSte120.equals("ya")) {
            chkAktSte120.setSelected(true);
        } else {
            chkAktSte120.setSelected(false);
        }
        
        if (aktSte150.equals("ya")) {
            chkAktSte150.setSelected(true);
        } else {
            chkAktSte150.setSelected(false);
        }
        
        if (kesBro15.equals("ya")) {
            chkKesBro15.setSelected(true);
        } else {
            chkKesBro15.setSelected(false);
        }
        
        if (kesBro30.equals("ya")) {
            chkKesBro30.setSelected(true);
        } else {
            chkKesBro30.setSelected(false);
        }
        
        if (kesBro45.equals("ya")) {
            chkKesBro45.setSelected(true);
        } else {
            chkKesBro45.setSelected(false);
        }
        
        if (kesBro60.equals("ya")) {
            chkKesBro60.setSelected(true);
        } else {
            chkKesBro60.setSelected(false);
        }
        
        if (kesBro90.equals("ya")) {
            chkKesBro90.setSelected(true);
        } else {
            chkKesBro90.setSelected(false);
        }
        
        if (kesBro120.equals("ya")) {
            chkKesBro120.setSelected(true);
        } else {
            chkKesBro120.setSelected(false);
        }
        
        if (kesBro150.equals("ya")) {
            chkKesBro150.setSelected(true);
        } else {
            chkKesBro150.setSelected(false);
        }
        
        if (cmbPasienBoleh.getSelectedIndex() == 0) {
            cmbJam3.setEnabled(false);
            cmbMnt3.setEnabled(false);
            cmbDtk3.setEnabled(false);            
        } else {
            cmbJam3.setEnabled(true);
            cmbMnt3.setEnabled(true);
            cmbDtk3.setEnabled(true);
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
        if (chkSis15.isSelected() == true) {
            sis15 = "ya";
        } else {
            sis15 = "tidak";
        }
        
        if (chkSis30.isSelected() == true) {
            sis30 = "ya";
        } else {
            sis30 = "tidak";
        }
        
        if (chkSis45.isSelected() == true) {
            sis45 = "ya";
        } else {
            sis45 = "tidak";
        }
        
        if (chkSis60.isSelected() == true) {
            sis60 = "ya";
        } else {
            sis60 = "tidak";
        }
        
        if (chkSis90.isSelected() == true) {
            sis90 = "ya";
        } else {
            sis90 = "tidak";
        }
        
        if (chkSis120.isSelected() == true) {
            sis120 = "ya";
        } else {
            sis120 = "tidak";
        }
        
        if (chkSis150.isSelected() == true) {
            sis150 = "ya";
        } else {
            sis150 = "tidak";
        }
        
        if (chkKesAld15.isSelected() == true) {
            kesAld15 = "ya";
        } else {
            kesAld15 = "tidak";
        }
        
        if (chkKesAld30.isSelected() == true) {
            kesAld30 = "ya";
        } else {
            kesAld30 = "tidak";
        }
        
        if (chkKesAld45.isSelected() == true) {
            kesAld45 = "ya";
        } else {
            kesAld45 = "tidak";
        }
        
        if (chkKesAld60.isSelected() == true) {
            kesAld60 = "ya";
        } else {
            kesAld60 = "tidak";
        }
        
        if (chkKesAld90.isSelected() == true) {
            kesAld90 = "ya";
        } else {
            kesAld90 = "tidak";
        }
        
        if (chkKesAld120.isSelected() == true) {
            kesAld120 = "ya";
        } else {
            kesAld120 = "tidak";
        }
        
        if (chkKesAld150.isSelected() == true) {
            kesAld150 = "ya";
        } else {
            kesAld150 = "tidak";
        }
        
        if (chkPerAld15.isSelected() == true) {
            perAld15 = "ya";
        } else {
            perAld15 = "tidak";
        }
        
        if (chkPerAld30.isSelected() == true) {
            perAld30 = "ya";
        } else {
            perAld30 = "tidak";
        }
        
        if (chkPerAld45.isSelected() == true) {
            perAld45 = "ya";
        } else {
            perAld45 = "tidak";
        }
        
        if (chkPerAld60.isSelected() == true) {
            perAld60 = "ya";
        } else {
            perAld60 = "tidak";
        }
        
        if (chkPerAld90.isSelected() == true) {
            perAld90 = "ya";
        } else {
            perAld90 = "tidak";
        }
        
        if (chkPerAld120.isSelected() == true) {
            perAld120 = "ya";
        } else {
            perAld120 = "tidak";
        }
        
        if (chkPerAld150.isSelected() == true) {
            perAld150 = "ya";
        } else {
            perAld150 = "tidak";
        }
        
        if (chkWar15.isSelected() == true) {
            war15 = "ya";
        } else {
            war15 = "tidak";
        }
        
        if (chkWar30.isSelected() == true) {
            war30 = "ya";
        } else {
            war30 = "tidak";
        }
        
        if (chkWar45.isSelected() == true) {
            war45 = "ya";
        } else {
            war45 = "tidak";
        }
        
        if (chkWar60.isSelected() == true) {
            war60 = "ya";
        } else {
            war60 = "tidak";
        }
        
        if (chkWar90.isSelected() == true) {
            war90 = "ya";
        } else {
            war90 = "tidak";
        }
        
        if (chkWar120.isSelected() == true) {
            war120 = "ya";
        } else {
            war120 = "tidak";
        }
        
        if (chkWar150.isSelected() == true) {
            war150 = "ya";
        } else {
            war150 = "tidak";
        }
        
        if (chkAktAld15.isSelected() == true) {
            aktAld15 = "ya";
        } else {
            aktAld15 = "tidak";
        }
        
        if (chkAktAld30.isSelected() == true) {
            aktAld30 = "ya";
        } else {
            aktAld30 = "tidak";
        }
        
        if (chkAktAld45.isSelected() == true) {
            aktAld45 = "ya";
        } else {
            aktAld45 = "tidak";
        }
        
        if (chkAktAld60.isSelected() == true) {
            aktAld60 = "ya";
        } else {
            aktAld60 = "tidak";
        }
        
        if (chkAktAld90.isSelected() == true) {
            aktAld90 = "ya";
        } else {
            aktAld90 = "tidak";
        }
        
        if (chkAktAld120.isSelected() == true) {
            aktAld120 = "ya";
        } else {
            aktAld120 = "tidak";
        }
        
        if (chkAktAld150.isSelected() == true) {
            aktAld150 = "ya";
        } else {
            aktAld150 = "tidak";
        }
        
        if (chkKesSte15.isSelected() == true) {
            kesSte15 = "ya";
        } else {
            kesSte15 = "tidak";
        }
        
        if (chkKesSte30.isSelected() == true) {
            kesSte30 = "ya";
        } else {
            kesSte30 = "tidak";
        }
        
        if (chkKesSte45.isSelected() == true) {
            kesSte45 = "ya";
        } else {
            kesSte45 = "tidak";
        }
        
        if (chkKesSte60.isSelected() == true) {
            kesSte60 = "ya";
        } else {
            kesSte60 = "tidak";
        }
        
        if (chkKesSte90.isSelected() == true) {
            kesSte90 = "ya";
        } else {
            kesSte90 = "tidak";
        }
        
        if (chkKesSte120.isSelected() == true) {
            kesSte120 = "ya";
        } else {
            kesSte120 = "tidak";
        }
        
        if (chkKesSte150.isSelected() == true) {
            kesSte150 = "ya";
        } else {
            kesSte150 = "tidak";
        }
        
        if (chkPerSte15.isSelected() == true) {
            perSte15 = "ya";
        } else {
            perSte15 = "tidak";
        }
        
        if (chkPerSte30.isSelected() == true) {
            perSte30 = "ya";
        } else {
            perSte30 = "tidak";
        }
        
        if (chkPerSte45.isSelected() == true) {
            perSte45 = "ya";
        } else {
            perSte45 = "tidak";
        }
        
        if (chkPerSte60.isSelected() == true) {
            perSte60 = "ya";
        } else {
            perSte60 = "tidak";
        }
        
        if (chkPerSte90.isSelected() == true) {
            perSte90 = "ya";
        } else {
            perSte90 = "tidak";
        }
        
        if (chkPerSte120.isSelected() == true) {
            perSte120 = "ya";
        } else {
            perSte120 = "tidak";
        }
        
        if (chkPerSte150.isSelected() == true) {
            perSte150 = "ya";
        } else {
            perSte150 = "tidak";
        }
        
        if (chkAktSte15.isSelected() == true) {
            aktSte15 = "ya";
        } else {
            aktSte15 = "tidak";
        }
        
        if (chkAktSte30.isSelected() == true) {
            aktSte30 = "ya";
        } else {
            aktSte30 = "tidak";
        }
        
        if (chkAktSte45.isSelected() == true) {
            aktSte45 = "ya";
        } else {
            aktSte45 = "tidak";
        }
        
        if (chkAktSte60.isSelected() == true) {
            aktSte60 = "ya";
        } else {
            aktSte60 = "tidak";
        }
        
        if (chkAktSte90.isSelected() == true) {
            aktSte90 = "ya";
        } else {
            aktSte90 = "tidak";
        }
        
        if (chkAktSte120.isSelected() == true) {
            aktSte120 = "ya";
        } else {
            aktSte120 = "tidak";
        }
        
        if (chkAktSte150.isSelected() == true) {
            aktSte150 = "ya";
        } else {
            aktSte150 = "tidak";
        }
        
        if (chkKesBro15.isSelected() == true) {
            kesBro15 = "ya";
        } else {
            kesBro15 = "tidak";
        }
        
        if (chkKesBro30.isSelected() == true) {
            kesBro30 = "ya";
        } else {
            kesBro30 = "tidak";
        }
        
        if (chkKesBro45.isSelected() == true) {
            kesBro45 = "ya";
        } else {
            kesBro45 = "tidak";
        }
        
        if (chkKesBro60.isSelected() == true) {
            kesBro60 = "ya";
        } else {
            kesBro60 = "tidak";
        }
        
        if (chkKesBro90.isSelected() == true) {
            kesBro90 = "ya";
        } else {
            kesBro90 = "tidak";
        }
        
        if (chkKesBro120.isSelected() == true) {
            kesBro120 = "ya";
        } else {
            kesBro120 = "tidak";
        }
        
        if (chkKesBro150.isSelected() == true) {
            kesBro150 = "ya";
        } else {
            kesBro150 = "tidak";
        }
    }
    
    private void variabelBersih() {
        nipDrAnes = "";
        nipPenata = "";
        nipPerawat = "";
        urutData = "";
        urutanKe = "";
        wktSimpan = "";
        sis15 = "";
        sis30 = "";
        sis45 = "";
        sis60 = "";
        sis90 = "";
        sis120 = "";
        sis150 = "";
        kesAld15 = "";
        kesAld30 = "";
        kesAld45 = "";
        kesAld60 = "";
        kesAld90 = "";
        kesAld120 = "";
        kesAld150 = "";
        perAld15 = "";
        perAld30 = "";
        perAld45 = "";
        perAld60 = "";
        perAld90 = "";
        perAld120 = "";
        perAld150 = "";
        war15 = "";
        war30 = "";
        war45 = "";
        war60 = "";
        war90 = "";
        war120 = "";
        war150 = "";
        aktAld15 = "";
        aktAld30 = "";
        aktAld45 = "";
        aktAld60 = "";
        aktAld90 = "";
        aktAld120 = "";
        aktAld150 = "";
        kesSte15 = "";
        kesSte30 = "";
        kesSte45 = "";
        kesSte60 = "";
        kesSte90 = "";
        kesSte120 = "";
        kesSte150 = "";
        perSte15 = "";
        perSte30 = "";
        perSte45 = "";
        perSte60 = "";
        perSte90 = "";
        perSte120 = "";
        perSte150 = "";
        aktSte15 = "";
        aktSte30 = "";
        aktSte45 = "";
        aktSte60 = "";
        aktSte90 = "";
        aktSte120 = "";
        aktSte150 = "";
        kesBro15 = "";
        kesBro30 = "";
        kesBro45 = "";
        kesBro60 = "";
        kesBro90 = "";
        kesBro120 = "";
        kesBro150 = "";
    }
    
    private void emptTeksTTV() {
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        Tsistol.setText("");
        Tdistol.setText("");
        Tnadi.setText("");
        Trr.setText("");
        Tsuhu.setText("");
        Tspo.setText("");
    }
    
    private void urutkanDataTTV() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tabMode1);
        tbTTV.setRowSorter(sorter);

        /*Penjelasan : sorter.setComparator(1 
        new RowSorter.SortKey(1
        angka 1 adalah kolom ke nya
         */
        sorter.setComparator(1, (o1, o2) -> {
            Integer n1 = Integer.parseInt(o1.toString());
            Integer n2 = Integer.parseInt(o2.toString());
            return n1.compareTo(n2);
        });

        sorter.setSortKeys(Arrays.asList(
                new RowSorter.SortKey(1, SortOrder.ASCENDING)
        ));

        sorter.sort();
    }
    
    private void getDataTtv() {
        urutanKe = "";
        if (tbTTV.getSelectedRow() != -1) {
            urutanKe = tbTTV.getValueAt(tbTTV.getSelectedRow(), 1).toString();
            cmbJam2.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbTTV.getValueAt(tbTTV.getSelectedRow(), 2).toString().substring(6, 8));
            Tsistol.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 3).toString());
            Tdistol.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 4).toString());
            Tnadi.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 5).toString());
            Trr.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 6).toString());
            Tsuhu.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 7).toString());
            Tspo.setText(tbTTV.getValueAt(tbTTV.getSelectedRow(), 8).toString());            
        }
    }
    
    private void tampilTtv(String wktsimpan) {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("select * from catatan_ruang_pemulihan_obs_ttv where waktu_simpan ='" + wktsimpan + "' order by urutan");
            try {                
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new String[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("urutan"),
                        rs1.getString("pukul"),
                        rs1.getString("td_sistole"),
                        rs1.getString("td_diastole"),
                        rs1.getString("nadi"),
                        rs1.getString("rr"),
                        rs1.getString("suhu"),
                        rs1.getString("spo2"),
                        rs1.getString("waktu_simpan")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampilTtv() : " + e);
            } finally {
                if (rs1 != null) {
                    rs1.close();
                }
                if (ps1 != null) {
                    ps1.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungAldret() {
        int sis = 0, kes = 0, per = 0, war = 0, akt = 0, hasil = 0;
        
        //skor combo
        if (cmbSiskulasi.getSelectedIndex() == 1) {
            TskorSis.setText("2");
        } else if (cmbSiskulasi.getSelectedIndex() == 2) {
            TskorSis.setText("1");
        } else if (cmbSiskulasi.getSelectedIndex() == 3) {
            TskorSis.setText("0");
        } else {
            TskorSis.setText("");
        }

        if (cmbKesadaranAldret.getSelectedIndex() == 1) {
            TskorKesAldret.setText("2");
        } else if (cmbKesadaranAldret.getSelectedIndex() == 2) {
            TskorKesAldret.setText("1");
        } else if (cmbKesadaranAldret.getSelectedIndex() == 3) {
            TskorKesAldret.setText("0");
        } else {
            TskorKesAldret.setText("");
        }

        if (cmbPernafasanAldret.getSelectedIndex() == 1) {
            TskorPerAldret.setText("2");
        } else if (cmbPernafasanAldret.getSelectedIndex() == 2) {
            TskorPerAldret.setText("1");
        } else if (cmbPernafasanAldret.getSelectedIndex() == 3) {
            TskorPerAldret.setText("0");
        } else {
            TskorPerAldret.setText("");
        }

        if (cmbWrnKulit.getSelectedIndex() == 1) {
            TskorWar.setText("2");
        } else if (cmbWrnKulit.getSelectedIndex() == 2) {
            TskorWar.setText("1");
        } else if (cmbWrnKulit.getSelectedIndex() == 3) {
            TskorWar.setText("0");
        } else {
            TskorWar.setText("");
        }

        if (cmbAktifitasAldret.getSelectedIndex() == 1) {
            TskorAktAldret.setText("2");
        } else if (cmbAktifitasAldret.getSelectedIndex() == 2) {
            TskorAktAldret.setText("1");
        } else if (cmbAktifitasAldret.getSelectedIndex() == 3) {
            TskorAktAldret.setText("0");
        } else {
            TskorAktAldret.setText("");
        }
        
        //cek skor
        if (TskorSis.getText().equals("")) {
            sis = 0;
        } else {
            sis = Integer.parseInt(TskorSis.getText());
        }

        if (TskorKesAldret.getText().equals("")) {
            kes = 0;
        } else {
            kes = Integer.parseInt(TskorKesAldret.getText());
        }
        
        if (TskorPerAldret.getText().equals("")) {
            per = 0;
        } else {
            per = Integer.parseInt(TskorPerAldret.getText());
        }
        
        if (TskorWar.getText().equals("")) {
            war = 0;
        } else {
            war = Integer.parseInt(TskorWar.getText());
        }
        
        if (TskorAktAldret.getText().equals("")) {
            akt = 0;
        } else {
            akt = Integer.parseInt(TskorAktAldret.getText());
        }
        
        hasil = sis + kes + per + war + akt;        
        TskorTotAldret.setText(Valid.SetAngka2(hasil));
    }
    
    private void hitungSteward() {
        int a = 0, b = 0, c = 0, hasil = 0;
        
        //skor combo
        if (cmbKesadaranSte.getSelectedIndex() == 1) {
            TskorKesSte.setText("2");
        } else if (cmbKesadaranSte.getSelectedIndex() == 2) {
            TskorKesSte.setText("1");
        } else if (cmbKesadaranSte.getSelectedIndex() == 3) {
            TskorKesSte.setText("0");
        } else {
            TskorKesSte.setText("");
        }
        
        if (cmbPernafasanSte.getSelectedIndex() == 1) {
            TskorPerSte.setText("2");
        } else if (cmbPernafasanSte.getSelectedIndex() == 2) {
            TskorPerSte.setText("1");
        } else if (cmbPernafasanSte.getSelectedIndex() == 3) {
            TskorPerSte.setText("0");
        } else {
            TskorPerSte.setText("");
        }
        
        if (cmbAktifitasSte.getSelectedIndex() == 1) {
            TskorAktSte.setText("2");
        } else if (cmbAktifitasSte.getSelectedIndex() == 2) {
            TskorAktSte.setText("1");
        } else if (cmbAktifitasSte.getSelectedIndex() == 3) {
            TskorAktSte.setText("0");
        } else {
            TskorAktSte.setText("");
        }
        
        //cek skor
        if (TskorKesSte.getText().equals("")) {
            a = 0;
        } else {
            a = Integer.parseInt(TskorKesSte.getText());
        }
        
        if (TskorPerSte.getText().equals("")) {
            b = 0;
        } else {
            b = Integer.parseInt(TskorPerSte.getText());
        }
        
        if (TskorAktSte.getText().equals("")) {
            c = 0;
        } else {
            c = Integer.parseInt(TskorAktSte.getText());
        }        
        
        hasil = a + b + c;        
        TskorTotSte.setText(Valid.SetAngka2(hasil));
    }
}
