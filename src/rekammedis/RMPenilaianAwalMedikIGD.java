/*
 * Kontribusi dari Bibing, RSUD Ratu Zalecha
 */

package rekammedis;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPegawai;
import kepegawaian.DlgCariPetugas;
import laporan.DlgPenyakit;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalMedikIGD extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private DlgPenyakit icd10 = new DlgPenyakit(null, false);
    private String cervical = "", rjp = "", defribrilasi = "", intubasi = "", vtp = "", dekompresi = "", balut = "", kateter = "",
            ngt = "", infus = "", obat = "", tidakada = "", paten = "", obsP = "", deformitas = "", contusio = "", penetrasi = "", tenderness = "",
            swelling = "", ekskoriasi = "", abrasi = "", burn = "", laserasi = "", tdk_tmpk_jls = "", obsT = "", traumaJlnNfs = "", resikoAspirasi = "",
            bendaAsing = "", hipertensi = "", dm = "", jantung = "", merokok = "", meninggal = "", ganggnafas = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalMedikIGD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "J.K.", "Tgl. Lahir", "Tgl. Asesmen", "Cerv. Collar", "RJP", "Defribilasi", "Intubasi", "VTP", "Dekompresi",
            "Balut", "Kateter", "NGT", "Infus", "Obat", "Ket. Obat", "Tidak Ada", "Paten", "Obs. Partial", "Jns. Obs. Partial", "Obs. Total",
            "Trauma Jln. Nafas", "Trauma", "Res. Aspirasi", "Aspirasi", "Bend. Asing", "Ket. Bend. Asing", "Kes. Jln. Napas", "Pernapasan", "Jns. Pernapasan", 
            "Gerakan Dada", "Tipe Pernpsn.", "Kes. Pernapasan", "Nadi", "Kulit/Mukosa", "Akral", "CRT", "Kes. Sirkulasi", "Ckp. Bulan", "Cairan Amnion", 
            "Menangis", "Tonus Otot", "Skor Apgar", "Frekuensi Nafas", "Retraksi", "Sianosis", "Air Entry", "Merintih", "GCS E", "Pupil", "Diam. Kanan", 
            "Reflek Chya.", "Meningeal Signs", "Literalisasi", "Deformitas", "Contusio", "Penetrasi", "Tenderness", "Swelling", "Ekskoriasi", "Abrasi", 
            "Burn", "Laserasi", "Tdk. Tampak Jls.", "Alergi", "Hipertensi", "DM", "Jantung", "Riw. Lain", "Merokok", "Kebiasaan Lain", "Anamnesis", 
            "Pemrk. Fisik", "Konjuntiva", "Sklera", "Bibir", "Mukosa", "Deviasi", "JVP", "LNN", "Tiroid", "Thorax", "Jantung", "Paru", "Abdomen", "Punggung", 
            "Ekstremitas", "lab", "x-ray", "ecg", "ctscan", "usg", "lainnya", "diagnosis", "icd", "Renc. Instruksi", "Ket. Rencana", "edukasi", "rencana", 
            "nip_edukasi", "penerima_edukasi", "nip_dokter", "tglkeluar", "ruang_opname", "indikasi_msk", "dipulangkan", "dirujuk","alasan_rujuk",
            "meninggal", "jam_mati", "penyebab", "ku", "td", "hr", "rr", "temp", "spo2", "gcs", "nip_perawat", "nip_dpjp", "ket_gambar", "gcs_v", "gcs_m",
            "diameter_kiri", "gangguan_jalan_nafas"
        }) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbPenilaian.setModel(tabMode);
        tbPenilaian.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPenilaian.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 127; i++) {
            TableColumn column = tbPenilaian.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(160);
            } else if (i == 3) {
                column.setPreferredWidth(70);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(120);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(100);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(100);
            } else if (i == 11) {
                column.setPreferredWidth(100);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(100);
            } else if (i == 14) {
                column.setPreferredWidth(100);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(100);
            } else if (i == 17) {
                column.setPreferredWidth(100);
            } else if (i == 18) {
                column.setPreferredWidth(100);
            } else if (i == 19) {
                column.setPreferredWidth(100);
            } else if (i == 20) {
                column.setPreferredWidth(100);
            } else if (i == 21) {
                column.setPreferredWidth(100);
            } else if (i == 22) {
                column.setPreferredWidth(100);
            } else if (i == 23) {
                column.setPreferredWidth(100);
            } else if (i == 24) {
                column.setPreferredWidth(100);
            } else if (i == 25) {
                column.setPreferredWidth(100);
            } else if (i == 26) {
                column.setPreferredWidth(100);
            } else if (i == 27) {
                column.setPreferredWidth(100);
            } else if (i == 28) {
                column.setPreferredWidth(100);
            } else if (i == 29) {
                column.setPreferredWidth(100);
            } else if (i == 30) {
                column.setPreferredWidth(100);
            } else if (i == 31) {
                column.setPreferredWidth(100);
            } else if (i == 32) {
                column.setPreferredWidth(100);
            } else if (i == 33) {
                column.setPreferredWidth(100);
            } else if (i == 34) {
                column.setPreferredWidth(100);
            } else if (i == 35) {
                column.setPreferredWidth(100);
            } else if (i == 36) {
                column.setPreferredWidth(100);
            } else if (i == 37) {
                column.setPreferredWidth(100);
            } else if (i == 38) {
                column.setPreferredWidth(100);
            } else if (i == 39) {
                column.setPreferredWidth(100);
            } else if (i == 40) {
                column.setPreferredWidth(100);
            } else if (i == 41) {
                column.setPreferredWidth(100);
            } else if (i == 42) {
                column.setPreferredWidth(100);
            } else if (i == 43) {
                column.setPreferredWidth(100);
            } else if (i == 44) {
                column.setPreferredWidth(100);
            } else if (i == 45) {
                column.setPreferredWidth(100);
            } else if (i == 46) {
                column.setPreferredWidth(100);
            } else if (i == 47) {
                column.setPreferredWidth(100);
            } else if (i == 48) {
                column.setPreferredWidth(100);
            } else if (i == 49) {
                column.setPreferredWidth(100);
            } else if (i == 50) {
                column.setPreferredWidth(100);
            } else if (i == 51) {
                column.setPreferredWidth(100);
            } else if (i == 52) {
                column.setPreferredWidth(100);
            } else if (i == 53) {
                column.setPreferredWidth(100);
            } else if (i == 54) {
                column.setPreferredWidth(100);
            } else if (i == 55) {
                column.setPreferredWidth(100);
            } else if (i == 56) {
                column.setPreferredWidth(100);
            } else if (i == 57) {
                column.setPreferredWidth(100);
            } else if (i == 58) {
                column.setPreferredWidth(100);
            } else if (i == 59) {
                column.setPreferredWidth(100);
            } else if (i == 60) {
                column.setPreferredWidth(100);
            } else if (i == 61) {
                column.setPreferredWidth(100);
            } else if (i == 62) {
                column.setPreferredWidth(100);
            } else if (i == 63) {
                column.setPreferredWidth(100);
            } else if (i == 64) {
                column.setPreferredWidth(100);
            } else if (i == 65) {
                column.setPreferredWidth(100);
            } else if (i == 66) {
                column.setPreferredWidth(100);
            } else if (i == 67) {
                column.setPreferredWidth(100);
            } else if (i == 68) {
                column.setPreferredWidth(100);
            } else if (i == 69) {
                column.setPreferredWidth(100);
            } else if (i == 70) {
                column.setPreferredWidth(100);
            } else if (i == 71) {
                column.setPreferredWidth(100);
            } else if (i == 72) {
                column.setPreferredWidth(100);
            } else if (i == 73) {
                column.setPreferredWidth(100);
            } else if (i == 74) {
                column.setPreferredWidth(100);
            } else if (i == 75) {
                column.setPreferredWidth(100);
            } else if (i == 76) {
                column.setPreferredWidth(100);
            } else if (i == 77) {
                column.setPreferredWidth(100);
            } else if (i == 78) {
                column.setPreferredWidth(100);
            } else if (i == 79) {
                column.setPreferredWidth(100);
            } else if (i == 80) {
                column.setPreferredWidth(100);
            } else if (i == 81) {
                column.setPreferredWidth(100);
            } else if (i == 82) {
                column.setPreferredWidth(100);
            } else if (i == 83) {
                column.setPreferredWidth(100);
            } else if (i == 84) {
                column.setPreferredWidth(100);
            } else if (i == 85) {
                column.setPreferredWidth(100);
            } else if (i == 86) {
                column.setPreferredWidth(100);
            } else if (i == 87) {
                column.setPreferredWidth(100);
            } else if (i == 88) {
                column.setPreferredWidth(100);
            } else if (i == 89) {
                column.setPreferredWidth(100);
            } else if (i == 90) {
                column.setPreferredWidth(100);
            } else if (i == 91) {
                column.setPreferredWidth(100);
            } else if (i == 92) {
                column.setPreferredWidth(100);
            } else if (i == 93) {
                column.setPreferredWidth(100);
            } else if (i == 94) {
                column.setPreferredWidth(100);
            } else if (i == 95) {
                column.setPreferredWidth(100);
            } else if (i == 96) {
                column.setPreferredWidth(100);
            } else if (i == 97) {
                column.setPreferredWidth(100);
            } else if (i == 98) {
                column.setPreferredWidth(100);
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
        tbPenilaian.setDefaultRenderer(Object.class, new WarnaTable());
        
        TObat.setDocument(new batasInput((int) 160).getKata(TObat));
        TBendaAsing.setDocument(new batasInput((int) 160).getKata(TBendaAsing));
        TSkorApgar.setDocument(new batasInput((byte) 3).getOnlyAngka(TSkorApgar));
        TDiam_kanan.setDocument(new batasInput((int) 4).getKata(TDiam_kanan));
        TDiam_kiri.setDocument(new batasInput((int) 4).getKata(TDiam_kiri));
        TReflek.setDocument(new batasInput((int) 7).getKata(TReflek));
        TMeningeal.setDocument(new batasInput((int) 120).getKata(TMeningeal));
        TFrekuensi.setDocument(new batasInput((byte) 3).getOnlyAngka(TFrekuensi));
        TRetraksi.setDocument(new batasInput((byte) 3).getOnlyAngka(TRetraksi));
        TSianosis.setDocument(new batasInput((byte) 3).getOnlyAngka(TSianosis));
        TAir.setDocument(new batasInput((byte) 3).getOnlyAngka(TAir));
        TMerintih.setDocument(new batasInput((byte) 3).getOnlyAngka(TMerintih));
        TAlergi.setDocument(new batasInput((int) 160).getKata(TAlergi));
        TLainPenyakit.setDocument(new batasInput((int) 160).getKata(TLainPenyakit));
        TPemeriksaan.setDocument(new batasInput((int) 200).getKata(TPemeriksaan));
        Ticd.setDocument(new batasInput((int) 10).getKata(Ticd));
        nmpenerima.setDocument(new batasInput((int) 160).getKata(nmpenerima));
        Tindikasi.setDocument(new batasInput((int) 200).getKata(Tindikasi));
        Tdipulangkan.setDocument(new batasInput((int) 200).getKata(Tdipulangkan));
        Tdirujuk.setDocument(new batasInput((int) 200).getKata(Tdirujuk));
        Tpenyebab.setDocument(new batasInput((int) 200).getKata(Tpenyebab));
        Ttd.setDocument(new batasInput((int) 7).getKata(Ttd));
        Thr.setDocument(new batasInput((int) 7).getKata(Thr));
        Trr.setDocument(new batasInput((int) 7).getKata(Trr));
        Ttemp.setDocument(new batasInput((int) 7).getKata(Ttemp));
        Tspo2.setDocument(new batasInput((int) 7).getKata(Tspo2));
        Tgcs.setDocument(new batasInput((int) 7).getKata(Tgcs));
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
                        KdPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        NmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        kdpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        nmpetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMPenilaianAwalMedikIGD")) {
                    if (pilihan == 1) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
                        BtnDpjp.requestFocus();
                    } else if (pilihan == 2) {
                        if (dokter.getTable().getSelectedRow() != -1) {
                            kddokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                            nmdokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        }
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
        
        icd10.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (icd10.getTable().getSelectedRow() != -1) {
                    x = JOptionPane.showConfirmDialog(rootPane, "Apakah deskripsi ICD 10 akan ditambahkan juga utk. diagnosa medis sementara..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Ticd.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                        if (TDiagSementara.getText().equals("")) {
                            TDiagSementara.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString());
                        } else {
                            TDiagSementara.setText(TDiagSementara.getText() + " (" + icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 3).toString() + ")");
                        }
                        btnICD.requestFocus();
                    } else {
                        Ticd.setText(icd10.getTable().getValueAt(icd10.getTable().getSelectedRow(), 1).toString());
                        btnICD.requestFocus();
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
        
        icd10.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (akses.getform().equals("RMPenilaianAwalMedikIGD")) {
                    if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                        icd10.dispose();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
        BtnResep = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jSeparator1 = new javax.swing.JSeparator();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        label14 = new widget.Label();
        KdPerawat = new widget.TextBox();
        NmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel8 = new widget.Label();
        TglLahir = new widget.TextBox();
        Jk = new widget.TextBox();
        jLabel10 = new widget.Label();
        label11 = new widget.Label();
        jLabel11 = new widget.Label();
        TglAsesmen = new widget.Tanggal();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel98 = new widget.Label();
        jLabel12 = new widget.Label();
        label15 = new widget.Label();
        kddpjp = new widget.TextBox();
        nmdpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        ChkCervical = new widget.CekBox();
        ChkRJP = new widget.CekBox();
        ChkDefribilasi = new widget.CekBox();
        ChkIntubasi = new widget.CekBox();
        ChkVTP = new widget.CekBox();
        ChkDekompresi = new widget.CekBox();
        ChkBalut = new widget.CekBox();
        ChkKateter = new widget.CekBox();
        ChkNGT = new widget.CekBox();
        ChkInfus = new widget.CekBox();
        ChkObat = new widget.CekBox();
        TObat = new widget.TextBox();
        ChkTidak = new widget.CekBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel99 = new widget.Label();
        jLabel100 = new widget.Label();
        ChkPaten = new widget.CekBox();
        ChkObstruksiP = new widget.CekBox();
        ChkObstruksiT = new widget.CekBox();
        ChkTrauma = new widget.CekBox();
        cmbTrauma = new widget.ComboBox();
        ChkResiko = new widget.CekBox();
        cmbResiko = new widget.ComboBox();
        ChkBendaAsing = new widget.CekBox();
        TBendaAsing = new widget.TextBox();
        jLabel101 = new widget.Label();
        cmbKesJalanNafas = new widget.ComboBox();
        jLabel102 = new widget.Label();
        cmbPernapasan = new widget.ComboBox();
        jLabel103 = new widget.Label();
        cmbGerakanDada = new widget.ComboBox();
        jLabel104 = new widget.Label();
        cmbTipePernapasan = new widget.ComboBox();
        jLabel105 = new widget.Label();
        cmbKesPernapasan = new widget.ComboBox();
        jLabel106 = new widget.Label();
        jLabel107 = new widget.Label();
        cmbNadi = new widget.ComboBox();
        jLabel108 = new widget.Label();
        cmbKulit = new widget.ComboBox();
        jLabel109 = new widget.Label();
        cmbAkral = new widget.ComboBox();
        jLabel110 = new widget.Label();
        cmbCRT = new widget.ComboBox();
        jLabel111 = new widget.Label();
        cmbKesSirkulasi = new widget.ComboBox();
        jLabel112 = new widget.Label();
        jLabel113 = new widget.Label();
        cmbCkpBulan = new widget.ComboBox();
        jLabel114 = new widget.Label();
        cmbCairan = new widget.ComboBox();
        jLabel115 = new widget.Label();
        cmbNapasNangis = new widget.ComboBox();
        jLabel116 = new widget.Label();
        cmbTonus = new widget.ComboBox();
        jLabel117 = new widget.Label();
        TSkorApgar = new widget.TextBox();
        jLabel118 = new widget.Label();
        jLabel119 = new widget.Label();
        jLabel120 = new widget.Label();
        cmbPupil = new widget.ComboBox();
        jLabel121 = new widget.Label();
        TDiam_kanan = new widget.TextBox();
        jLabel122 = new widget.Label();
        TReflek = new widget.TextBox();
        jLabel123 = new widget.Label();
        TMeningeal = new widget.TextBox();
        jLabel124 = new widget.Label();
        cmbLater = new widget.ComboBox();
        jLabel125 = new widget.Label();
        jLabel127 = new widget.Label();
        jLabel128 = new widget.Label();
        TFrekuensi = new widget.TextBox();
        jLabel129 = new widget.Label();
        TRetraksi = new widget.TextBox();
        jLabel130 = new widget.Label();
        TSianosis = new widget.TextBox();
        jLabel131 = new widget.Label();
        TAir = new widget.TextBox();
        jLabel132 = new widget.Label();
        TMerintih = new widget.TextBox();
        jLabel133 = new widget.Label();
        TAlergi = new widget.TextBox();
        jLabel134 = new widget.Label();
        ChkHipertensi = new widget.CekBox();
        ChkDM = new widget.CekBox();
        ChkJantung = new widget.CekBox();
        jLabel135 = new widget.Label();
        TLainPenyakit = new widget.TextBox();
        jLabel136 = new widget.Label();
        ChkMerokok = new widget.CekBox();
        jLabel137 = new widget.Label();
        TLainKebiasaan = new widget.TextBox();
        jLabel138 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        TAnamnesis = new widget.TextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel139 = new widget.Label();
        jLabel140 = new widget.Label();
        TPemeriksaan = new widget.TextBox();
        jLabel141 = new widget.Label();
        jLabel142 = new widget.Label();
        cmbKonjung = new widget.ComboBox();
        cmbSklera = new widget.ComboBox();
        jLabel143 = new widget.Label();
        jLabel144 = new widget.Label();
        cmbBibir = new widget.ComboBox();
        jLabel145 = new widget.Label();
        cmbMukosa = new widget.ComboBox();
        jLabel146 = new widget.Label();
        jLabel147 = new widget.Label();
        cmbDeviasi = new widget.ComboBox();
        jLabel148 = new widget.Label();
        cmbJVP = new widget.ComboBox();
        jLabel149 = new widget.Label();
        cmbLNN = new widget.ComboBox();
        jLabel150 = new widget.Label();
        cmbTiroid = new widget.ComboBox();
        jLabel151 = new widget.Label();
        jLabel152 = new widget.Label();
        scrollPane4 = new widget.ScrollPane();
        TJantung = new widget.TextArea();
        scrollPane5 = new widget.ScrollPane();
        TParu = new widget.TextArea();
        jLabel153 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        TAbdomen = new widget.TextArea();
        jLabel154 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        TPunggung = new widget.TextArea();
        jLabel155 = new widget.Label();
        scrollPane8 = new widget.ScrollPane();
        TEkstremitas = new widget.TextArea();
        jLabel156 = new widget.Label();
        jLabel157 = new widget.Label();
        jLabel158 = new widget.Label();
        TLab = new widget.TextBox();
        jLabel159 = new widget.Label();
        Txray = new widget.TextBox();
        jLabel160 = new widget.Label();
        Tecg = new widget.TextBox();
        jLabel161 = new widget.Label();
        Tctscan = new widget.TextBox();
        jLabel162 = new widget.Label();
        Tusg = new widget.TextBox();
        jLabel163 = new widget.Label();
        Tlainya = new widget.TextBox();
        jLabel164 = new widget.Label();
        scrollPane9 = new widget.ScrollPane();
        TDiagSementara = new widget.TextArea();
        jLabel165 = new widget.Label();
        Ticd = new widget.TextBox();
        jLabel166 = new widget.Label();
        jLabel167 = new widget.Label();
        jLabel168 = new widget.Label();
        Tedukasi = new widget.TextBox();
        jLabel169 = new widget.Label();
        Trencana = new widget.TextBox();
        jLabel170 = new widget.Label();
        kdpetugas = new widget.TextBox();
        nmpetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel171 = new widget.Label();
        nmpenerima = new widget.TextBox();
        TJlhSkor = new widget.TextBox();
        BtnJumlah = new widget.Button();
        jLabel172 = new widget.Label();
        kddokter = new widget.TextBox();
        nmdokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel173 = new widget.Label();
        jLabel174 = new widget.Label();
        TglKeluar = new widget.Tanggal();
        jLabel175 = new widget.Label();
        cmbRuangan = new widget.ComboBox();
        jLabel176 = new widget.Label();
        Tindikasi = new widget.TextBox();
        jLabel177 = new widget.Label();
        Tdipulangkan = new widget.TextBox();
        jLabel178 = new widget.Label();
        Tdirujuk = new widget.TextBox();
        jLabel179 = new widget.Label();
        scrollPane10 = new widget.ScrollPane();
        TAlasanDirujuk = new widget.TextArea();
        ChkMeninggal = new widget.CekBox();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel180 = new widget.Label();
        Tpenyebab = new widget.TextBox();
        jLabel181 = new widget.Label();
        Tku = new widget.TextBox();
        jLabel182 = new widget.Label();
        Ttd = new widget.TextBox();
        jLabel183 = new widget.Label();
        Thr = new widget.TextBox();
        jLabel184 = new widget.Label();
        jLabel185 = new widget.Label();
        Trr = new widget.TextBox();
        Ttemp = new widget.TextBox();
        jLabel186 = new widget.Label();
        jLabel187 = new widget.Label();
        Tspo2 = new widget.TextBox();
        Tgcs = new widget.TextBox();
        cmbObstruksi = new widget.ComboBox();
        ChkDeformitas = new widget.CekBox();
        ChkContusio = new widget.CekBox();
        ChkPenetrasi = new widget.CekBox();
        ChkTenderness = new widget.CekBox();
        ChkSwelling = new widget.CekBox();
        ChkEkskoriasi = new widget.CekBox();
        ChkAbrasi = new widget.CekBox();
        ChkBurn = new widget.CekBox();
        ChkLaserasi = new widget.CekBox();
        ChkTdkTampk = new widget.CekBox();
        cmbReguler = new widget.ComboBox();
        cmbRencana = new widget.ComboBox();
        jLabel188 = new widget.Label();
        btnICD = new widget.Button();
        jLabel189 = new widget.Label();
        scrollPane11 = new widget.ScrollPane();
        Tket_gambar = new widget.TextArea();
        jLabel126 = new widget.Label();
        TgcsE = new widget.TextBox();
        jLabel190 = new widget.Label();
        TgcsV = new widget.TextBox();
        jLabel191 = new widget.Label();
        TgcsM = new widget.TextBox();
        jLabel192 = new widget.Label();
        TDiam_kiri = new widget.TextBox();
        jLabel193 = new widget.Label();
        jLabel194 = new widget.Label();
        scrollPane12 = new widget.ScrollPane();
        TketRencana = new widget.TextArea();
        ChkGangNafas = new widget.CekBox();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPenilaian = new widget.Table();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Medik Gawat Darurat / Penilaian Awal Medik IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
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

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        BtnResep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnResepKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnResep);

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
        TabRawat.setForeground(new java.awt.Color(0, 0, 0));
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
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2300));
        FormInput.setLayout(null);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 120, 880, 1);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 30, 131, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(279, 30, 300, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(207, 30, 70, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Perawat Yg. Menyerahkan :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 90, 150, 23);

        KdPerawat.setEditable(false);
        KdPerawat.setForeground(new java.awt.Color(0, 0, 0));
        KdPerawat.setName("KdPerawat"); // NOI18N
        KdPerawat.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdPerawat);
        KdPerawat.setBounds(157, 90, 100, 23);

        NmPerawat.setEditable(false);
        NmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        NmPerawat.setName("NmPerawat"); // NOI18N
        NmPerawat.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmPerawat);
        NmPerawat.setBounds(260, 90, 318, 23);

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
        BtnPerawat.setBounds(580, 90, 28, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Lahir :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(580, 30, 60, 23);

        TglLahir.setEditable(false);
        TglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(644, 30, 80, 23);

        Jk.setEditable(false);
        Jk.setForeground(new java.awt.Color(0, 0, 0));
        Jk.setHighlighter(null);
        Jk.setName("Jk"); // NOI18N
        FormInput.add(Jk);
        Jk.setBounds(774, 30, 80, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("No. Rawat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 30, 70, 23);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setText("Tgl. Asesmen : ");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label11);
        label11.setBounds(625, 60, 100, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("J. Kel. :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(730, 30, 40, 23);

        TglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2023 11:09:15" }));
        TglAsesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglAsesmen.setName("TglAsesmen"); // NOI18N
        TglAsesmen.setOpaque(false);
        FormInput.add(TglAsesmen);
        TglAsesmen.setBounds(725, 60, 130, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/anatomi_tubuh_manusia.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(480, 950, 320, 500);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel98.setText("I. INTERVENSI PREHOSPITAL");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(10, 120, 330, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("MULAI PENANGANAN :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(10, 8, 130, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Mengetahui DPJP :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 60, 150, 23);

        kddpjp.setEditable(false);
        kddpjp.setForeground(new java.awt.Color(0, 0, 0));
        kddpjp.setName("kddpjp"); // NOI18N
        kddpjp.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(kddpjp);
        kddpjp.setBounds(157, 60, 100, 23);

        nmdpjp.setEditable(false);
        nmdpjp.setForeground(new java.awt.Color(0, 0, 0));
        nmdpjp.setName("nmdpjp"); // NOI18N
        nmdpjp.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(nmdpjp);
        nmdpjp.setBounds(260, 60, 318, 23);

        BtnDpjp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDpjp.setMnemonic('2');
        BtnDpjp.setToolTipText("Alt+2");
        BtnDpjp.setName("BtnDpjp"); // NOI18N
        BtnDpjp.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDpjp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDpjpActionPerformed(evt);
            }
        });
        FormInput.add(BtnDpjp);
        BtnDpjp.setBounds(580, 60, 28, 23);

        ChkCervical.setBackground(new java.awt.Color(255, 255, 250));
        ChkCervical.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkCervical.setForeground(new java.awt.Color(0, 0, 0));
        ChkCervical.setText("Cervical Collar");
        ChkCervical.setBorderPainted(true);
        ChkCervical.setBorderPaintedFlat(true);
        ChkCervical.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkCervical.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkCervical.setName("ChkCervical"); // NOI18N
        ChkCervical.setOpaque(false);
        ChkCervical.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkCervical);
        ChkCervical.setBounds(20, 150, 95, 23);

        ChkRJP.setBackground(new java.awt.Color(255, 255, 250));
        ChkRJP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkRJP.setForeground(new java.awt.Color(0, 0, 0));
        ChkRJP.setText("RJP");
        ChkRJP.setBorderPainted(true);
        ChkRJP.setBorderPaintedFlat(true);
        ChkRJP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkRJP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkRJP.setName("ChkRJP"); // NOI18N
        ChkRJP.setOpaque(false);
        ChkRJP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkRJP);
        ChkRJP.setBounds(20, 180, 95, 23);

        ChkDefribilasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDefribilasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDefribilasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDefribilasi.setText("Defribrilasi");
        ChkDefribilasi.setBorderPainted(true);
        ChkDefribilasi.setBorderPaintedFlat(true);
        ChkDefribilasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDefribilasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDefribilasi.setName("ChkDefribilasi"); // NOI18N
        ChkDefribilasi.setOpaque(false);
        ChkDefribilasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDefribilasi);
        ChkDefribilasi.setBounds(130, 150, 80, 23);

        ChkIntubasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkIntubasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkIntubasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkIntubasi.setText("Intubasi");
        ChkIntubasi.setBorderPainted(true);
        ChkIntubasi.setBorderPaintedFlat(true);
        ChkIntubasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkIntubasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkIntubasi.setName("ChkIntubasi"); // NOI18N
        ChkIntubasi.setOpaque(false);
        ChkIntubasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkIntubasi);
        ChkIntubasi.setBounds(130, 180, 80, 23);

        ChkVTP.setBackground(new java.awt.Color(255, 255, 250));
        ChkVTP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkVTP.setForeground(new java.awt.Color(0, 0, 0));
        ChkVTP.setText("VTP");
        ChkVTP.setBorderPainted(true);
        ChkVTP.setBorderPaintedFlat(true);
        ChkVTP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkVTP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkVTP.setName("ChkVTP"); // NOI18N
        ChkVTP.setOpaque(false);
        ChkVTP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkVTP);
        ChkVTP.setBounds(220, 150, 80, 23);

        ChkDekompresi.setBackground(new java.awt.Color(255, 255, 250));
        ChkDekompresi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDekompresi.setForeground(new java.awt.Color(0, 0, 0));
        ChkDekompresi.setText("Dekompresi Jarum/WSD*");
        ChkDekompresi.setBorderPainted(true);
        ChkDekompresi.setBorderPaintedFlat(true);
        ChkDekompresi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDekompresi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDekompresi.setName("ChkDekompresi"); // NOI18N
        ChkDekompresi.setOpaque(false);
        ChkDekompresi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDekompresi);
        ChkDekompresi.setBounds(220, 180, 150, 23);

        ChkBalut.setBackground(new java.awt.Color(255, 255, 250));
        ChkBalut.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBalut.setForeground(new java.awt.Color(0, 0, 0));
        ChkBalut.setText("Balut / Bidai*");
        ChkBalut.setBorderPainted(true);
        ChkBalut.setBorderPaintedFlat(true);
        ChkBalut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBalut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBalut.setName("ChkBalut"); // NOI18N
        ChkBalut.setOpaque(false);
        ChkBalut.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBalut);
        ChkBalut.setBounds(380, 150, 90, 23);

        ChkKateter.setBackground(new java.awt.Color(255, 255, 250));
        ChkKateter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkKateter.setForeground(new java.awt.Color(0, 0, 0));
        ChkKateter.setText("Kateter Urin");
        ChkKateter.setBorderPainted(true);
        ChkKateter.setBorderPaintedFlat(true);
        ChkKateter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkKateter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkKateter.setName("ChkKateter"); // NOI18N
        ChkKateter.setOpaque(false);
        ChkKateter.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkKateter);
        ChkKateter.setBounds(380, 180, 90, 23);

        ChkNGT.setBackground(new java.awt.Color(255, 255, 250));
        ChkNGT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkNGT.setForeground(new java.awt.Color(0, 0, 0));
        ChkNGT.setText("NGT");
        ChkNGT.setBorderPainted(true);
        ChkNGT.setBorderPaintedFlat(true);
        ChkNGT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkNGT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkNGT.setName("ChkNGT"); // NOI18N
        ChkNGT.setOpaque(false);
        ChkNGT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkNGT);
        ChkNGT.setBounds(480, 150, 50, 23);

        ChkInfus.setBackground(new java.awt.Color(255, 255, 250));
        ChkInfus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkInfus.setForeground(new java.awt.Color(0, 0, 0));
        ChkInfus.setText("Infus");
        ChkInfus.setBorderPainted(true);
        ChkInfus.setBorderPaintedFlat(true);
        ChkInfus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInfus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInfus.setName("ChkInfus"); // NOI18N
        ChkInfus.setOpaque(false);
        ChkInfus.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkInfus);
        ChkInfus.setBounds(480, 180, 50, 23);

        ChkObat.setBackground(new java.awt.Color(255, 255, 250));
        ChkObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObat.setForeground(new java.awt.Color(0, 0, 0));
        ChkObat.setText("Obat");
        ChkObat.setBorderPainted(true);
        ChkObat.setBorderPaintedFlat(true);
        ChkObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObat.setName("ChkObat"); // NOI18N
        ChkObat.setOpaque(false);
        ChkObat.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObatActionPerformed(evt);
            }
        });
        FormInput.add(ChkObat);
        ChkObat.setBounds(540, 150, 50, 23);

        TObat.setForeground(new java.awt.Color(0, 0, 0));
        TObat.setName("TObat"); // NOI18N
        FormInput.add(TObat);
        TObat.setBounds(594, 150, 260, 23);

        ChkTidak.setBackground(new java.awt.Color(255, 255, 250));
        ChkTidak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTidak.setForeground(new java.awt.Color(0, 0, 0));
        ChkTidak.setText("Tidak Ada");
        ChkTidak.setBorderPainted(true);
        ChkTidak.setBorderPaintedFlat(true);
        ChkTidak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTidak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTidak.setName("ChkTidak"); // NOI18N
        ChkTidak.setOpaque(false);
        ChkTidak.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTidak);
        ChkTidak.setBounds(540, 180, 90, 23);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 210, 880, 1);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel99.setText("II. SURVEI PRIMER");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(10, 210, 330, 23);

        jLabel100.setForeground(new java.awt.Color(0, 51, 204));
        jLabel100.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel100.setText("Jalan Nafas :");
        jLabel100.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(20, 230, 110, 23);

        ChkPaten.setBackground(new java.awt.Color(255, 255, 250));
        ChkPaten.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPaten.setForeground(new java.awt.Color(0, 0, 0));
        ChkPaten.setText("Paten");
        ChkPaten.setBorderPainted(true);
        ChkPaten.setBorderPaintedFlat(true);
        ChkPaten.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPaten.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPaten.setName("ChkPaten"); // NOI18N
        ChkPaten.setOpaque(false);
        ChkPaten.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPaten);
        ChkPaten.setBounds(20, 250, 60, 23);

        ChkObstruksiP.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiP.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiP.setText("Obstruksi Partial");
        ChkObstruksiP.setBorderPainted(true);
        ChkObstruksiP.setBorderPaintedFlat(true);
        ChkObstruksiP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiP.setName("ChkObstruksiP"); // NOI18N
        ChkObstruksiP.setOpaque(false);
        ChkObstruksiP.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkObstruksiP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkObstruksiPActionPerformed(evt);
            }
        });
        FormInput.add(ChkObstruksiP);
        ChkObstruksiP.setBounds(20, 280, 100, 23);

        ChkObstruksiT.setBackground(new java.awt.Color(255, 255, 250));
        ChkObstruksiT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkObstruksiT.setForeground(new java.awt.Color(0, 0, 0));
        ChkObstruksiT.setText("Obstruksi Total");
        ChkObstruksiT.setBorderPainted(true);
        ChkObstruksiT.setBorderPaintedFlat(true);
        ChkObstruksiT.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkObstruksiT.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkObstruksiT.setName("ChkObstruksiT"); // NOI18N
        ChkObstruksiT.setOpaque(false);
        ChkObstruksiT.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkObstruksiT);
        ChkObstruksiT.setBounds(20, 310, 100, 23);

        ChkTrauma.setBackground(new java.awt.Color(255, 255, 250));
        ChkTrauma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTrauma.setForeground(new java.awt.Color(0, 0, 0));
        ChkTrauma.setText("Trauma Jalan Nafas :");
        ChkTrauma.setBorderPainted(true);
        ChkTrauma.setBorderPaintedFlat(true);
        ChkTrauma.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTrauma.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTrauma.setName("ChkTrauma"); // NOI18N
        ChkTrauma.setOpaque(false);
        ChkTrauma.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkTrauma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkTraumaActionPerformed(evt);
            }
        });
        FormInput.add(ChkTrauma);
        ChkTrauma.setBounds(20, 340, 123, 23);

        cmbTrauma.setForeground(new java.awt.Color(0, 0, 0));
        cmbTrauma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Fasial", "Leher", "Inhalasi" }));
        cmbTrauma.setName("cmbTrauma"); // NOI18N
        FormInput.add(cmbTrauma);
        cmbTrauma.setBounds(145, 340, 70, 23);

        ChkResiko.setBackground(new java.awt.Color(255, 255, 250));
        ChkResiko.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkResiko.setForeground(new java.awt.Color(0, 0, 0));
        ChkResiko.setText("Resiko Aspirasi :");
        ChkResiko.setBorderPainted(true);
        ChkResiko.setBorderPaintedFlat(true);
        ChkResiko.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkResiko.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkResiko.setName("ChkResiko"); // NOI18N
        ChkResiko.setOpaque(false);
        ChkResiko.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkResiko.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkResikoActionPerformed(evt);
            }
        });
        FormInput.add(ChkResiko);
        ChkResiko.setBounds(20, 370, 100, 23);

        cmbResiko.setForeground(new java.awt.Color(0, 0, 0));
        cmbResiko.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Perdarahan", "Muntahan" }));
        cmbResiko.setName("cmbResiko"); // NOI18N
        FormInput.add(cmbResiko);
        cmbResiko.setBounds(122, 370, 92, 23);

        ChkBendaAsing.setBackground(new java.awt.Color(255, 255, 250));
        ChkBendaAsing.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        ChkBendaAsing.setText("Benda Asing : ");
        ChkBendaAsing.setBorderPainted(true);
        ChkBendaAsing.setBorderPaintedFlat(true);
        ChkBendaAsing.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBendaAsing.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBendaAsing.setName("ChkBendaAsing"); // NOI18N
        ChkBendaAsing.setOpaque(false);
        ChkBendaAsing.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkBendaAsing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkBendaAsingActionPerformed(evt);
            }
        });
        FormInput.add(ChkBendaAsing);
        ChkBendaAsing.setBounds(20, 400, 88, 23);

        TBendaAsing.setForeground(new java.awt.Color(0, 0, 0));
        TBendaAsing.setName("TBendaAsing"); // NOI18N
        FormInput.add(TBendaAsing);
        TBendaAsing.setBounds(110, 400, 240, 23);

        jLabel101.setForeground(new java.awt.Color(0, 0, 0));
        jLabel101.setText("Kesimpulan Jalan Nafas :");
        jLabel101.setName("jLabel101"); // NOI18N
        FormInput.add(jLabel101);
        jLabel101.setBounds(20, 430, 130, 23);

        cmbKesJalanNafas.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesJalanNafas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesJalanNafas.setName("cmbKesJalanNafas"); // NOI18N
        FormInput.add(cmbKesJalanNafas);
        cmbKesJalanNafas.setBounds(155, 430, 115, 23);

        jLabel102.setForeground(new java.awt.Color(0, 51, 204));
        jLabel102.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel102.setText("Pernapasan :");
        jLabel102.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel102.setName("jLabel102"); // NOI18N
        FormInput.add(jLabel102);
        jLabel102.setBounds(295, 230, 80, 23);

        cmbPernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Spontan", "Tidak Spontan" }));
        cmbPernapasan.setName("cmbPernapasan"); // NOI18N
        cmbPernapasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPernapasanActionPerformed(evt);
            }
        });
        FormInput.add(cmbPernapasan);
        cmbPernapasan.setBounds(295, 250, 105, 23);

        jLabel103.setForeground(new java.awt.Color(0, 0, 0));
        jLabel103.setText("Gerakan Dada :");
        jLabel103.setName("jLabel103"); // NOI18N
        FormInput.add(jLabel103);
        jLabel103.setBounds(270, 280, 130, 23);

        cmbGerakanDada.setForeground(new java.awt.Color(0, 0, 0));
        cmbGerakanDada.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Simetris", "Asimetris", "Jejas dinding dada Kanan", "Jejas dinding dada Kiri", "Jejas dinding dada Kanan & Kiri" }));
        cmbGerakanDada.setName("cmbGerakanDada"); // NOI18N
        FormInput.add(cmbGerakanDada);
        cmbGerakanDada.setBounds(405, 280, 183, 23);

        jLabel104.setForeground(new java.awt.Color(0, 0, 0));
        jLabel104.setText("Tipe Pernapasan :");
        jLabel104.setName("jLabel104"); // NOI18N
        FormInput.add(jLabel104);
        jLabel104.setBounds(270, 310, 130, 23);

        cmbTipePernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbTipePernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Kussmaul", "Biot", "Apneustic", "Retraktif", "Takipneu", "Hiperventilasi", "Cheyne Stoke", "Flare" }));
        cmbTipePernapasan.setName("cmbTipePernapasan"); // NOI18N
        FormInput.add(cmbTipePernapasan);
        cmbTipePernapasan.setBounds(405, 310, 100, 23);

        jLabel105.setForeground(new java.awt.Color(0, 0, 0));
        jLabel105.setText("Kesimpulan Pernapasan :");
        jLabel105.setName("jLabel105"); // NOI18N
        FormInput.add(jLabel105);
        jLabel105.setBounds(270, 340, 130, 23);

        cmbKesPernapasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesPernapasan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesPernapasan.setName("cmbKesPernapasan"); // NOI18N
        FormInput.add(cmbKesPernapasan);
        cmbKesPernapasan.setBounds(405, 340, 115, 23);

        jLabel106.setForeground(new java.awt.Color(0, 51, 204));
        jLabel106.setText("Sirkulasi :");
        jLabel106.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel106.setName("jLabel106"); // NOI18N
        FormInput.add(jLabel106);
        jLabel106.setBounds(630, 230, 80, 23);

        jLabel107.setForeground(new java.awt.Color(0, 0, 0));
        jLabel107.setText("Nadi :");
        jLabel107.setName("jLabel107"); // NOI18N
        FormInput.add(jLabel107);
        jLabel107.setBounds(630, 250, 80, 23);

        cmbNadi.setForeground(new java.awt.Color(0, 0, 0));
        cmbNadi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reguler", "Kuat", "Irreguler", "Lemah" }));
        cmbNadi.setName("cmbNadi"); // NOI18N
        FormInput.add(cmbNadi);
        cmbNadi.setBounds(715, 250, 80, 23);

        jLabel108.setForeground(new java.awt.Color(0, 0, 0));
        jLabel108.setText("Kulit / Mukosa :");
        jLabel108.setName("jLabel108"); // NOI18N
        FormInput.add(jLabel108);
        jLabel108.setBounds(630, 280, 80, 23);

        cmbKulit.setForeground(new java.awt.Color(0, 0, 0));
        cmbKulit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Normal", "Jaundice", "Berkeringat", "Pucat", "Cyanosis" }));
        cmbKulit.setName("cmbKulit"); // NOI18N
        FormInput.add(cmbKulit);
        cmbKulit.setBounds(715, 280, 90, 23);

        jLabel109.setForeground(new java.awt.Color(0, 0, 0));
        jLabel109.setText("Akral :");
        jLabel109.setName("jLabel109"); // NOI18N
        FormInput.add(jLabel109);
        jLabel109.setBounds(630, 310, 80, 23);

        cmbAkral.setForeground(new java.awt.Color(0, 0, 0));
        cmbAkral.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Hangat", "Kering", "Dingin", "Basah" }));
        cmbAkral.setName("cmbAkral"); // NOI18N
        FormInput.add(cmbAkral);
        cmbAkral.setBounds(715, 310, 70, 23);

        jLabel110.setForeground(new java.awt.Color(0, 0, 0));
        jLabel110.setText("CRT :");
        jLabel110.setName("jLabel110"); // NOI18N
        FormInput.add(jLabel110);
        jLabel110.setBounds(630, 340, 80, 23);

        cmbCRT.setForeground(new java.awt.Color(0, 0, 0));
        cmbCRT.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 2 detik", "> 2 detik" }));
        cmbCRT.setName("cmbCRT"); // NOI18N
        FormInput.add(cmbCRT);
        cmbCRT.setBounds(715, 340, 80, 23);

        jLabel111.setForeground(new java.awt.Color(0, 0, 0));
        jLabel111.setText("Kesimpulan Sirkulasi :");
        jLabel111.setName("jLabel111"); // NOI18N
        FormInput.add(jLabel111);
        jLabel111.setBounds(600, 370, 110, 23);

        cmbKesSirkulasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesSirkulasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Aman", "Mengancam Jiwa" }));
        cmbKesSirkulasi.setName("cmbKesSirkulasi"); // NOI18N
        FormInput.add(cmbKesSirkulasi);
        cmbKesSirkulasi.setBounds(715, 370, 115, 23);

        jLabel112.setForeground(new java.awt.Color(0, 51, 204));
        jLabel112.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel112.setText("Penilaian Bayi Baru Lahir :");
        jLabel112.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel112.setName("jLabel112"); // NOI18N
        FormInput.add(jLabel112);
        jLabel112.setBounds(20, 580, 140, 23);

        jLabel113.setForeground(new java.awt.Color(0, 0, 0));
        jLabel113.setText("Cukup Bulan :");
        jLabel113.setName("jLabel113"); // NOI18N
        FormInput.add(jLabel113);
        jLabel113.setBounds(20, 600, 140, 23);

        cmbCkpBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbCkpBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbCkpBulan.setName("cmbCkpBulan"); // NOI18N
        FormInput.add(cmbCkpBulan);
        cmbCkpBulan.setBounds(166, 600, 60, 23);

        jLabel114.setForeground(new java.awt.Color(0, 0, 0));
        jLabel114.setText("Cairan Amnion Jernih :");
        jLabel114.setName("jLabel114"); // NOI18N
        FormInput.add(jLabel114);
        jLabel114.setBounds(20, 630, 140, 23);

        cmbCairan.setForeground(new java.awt.Color(0, 0, 0));
        cmbCairan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbCairan.setName("cmbCairan"); // NOI18N
        FormInput.add(cmbCairan);
        cmbCairan.setBounds(166, 630, 60, 23);

        jLabel115.setForeground(new java.awt.Color(0, 0, 0));
        jLabel115.setText("Pernapasan / Menangis :");
        jLabel115.setName("jLabel115"); // NOI18N
        FormInput.add(jLabel115);
        jLabel115.setBounds(20, 660, 140, 23);

        cmbNapasNangis.setForeground(new java.awt.Color(0, 0, 0));
        cmbNapasNangis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbNapasNangis.setName("cmbNapasNangis"); // NOI18N
        FormInput.add(cmbNapasNangis);
        cmbNapasNangis.setBounds(166, 660, 60, 23);

        jLabel116.setForeground(new java.awt.Color(0, 0, 0));
        jLabel116.setText("Tonus Otot Baik :");
        jLabel116.setName("jLabel116"); // NOI18N
        FormInput.add(jLabel116);
        jLabel116.setBounds(20, 690, 140, 23);

        cmbTonus.setForeground(new java.awt.Color(0, 0, 0));
        cmbTonus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbTonus.setName("cmbTonus"); // NOI18N
        FormInput.add(cmbTonus);
        cmbTonus.setBounds(166, 690, 60, 23);

        jLabel117.setForeground(new java.awt.Color(0, 0, 0));
        jLabel117.setText("SKOR APGAR :");
        jLabel117.setName("jLabel117"); // NOI18N
        FormInput.add(jLabel117);
        jLabel117.setBounds(20, 720, 140, 23);

        TSkorApgar.setForeground(new java.awt.Color(0, 0, 0));
        TSkorApgar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TSkorApgar.setName("TSkorApgar"); // NOI18N
        TSkorApgar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSkorApgarKeyPressed(evt);
            }
        });
        FormInput.add(TSkorApgar);
        TSkorApgar.setBounds(166, 720, 50, 23);

        jLabel118.setForeground(new java.awt.Color(0, 51, 204));
        jLabel118.setText("Disabilitas :");
        jLabel118.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel118.setName("jLabel118"); // NOI18N
        FormInput.add(jLabel118);
        jLabel118.setBounds(370, 420, 80, 23);

        jLabel119.setForeground(new java.awt.Color(0, 0, 0));
        jLabel119.setText("GCS :");
        jLabel119.setName("jLabel119"); // NOI18N
        FormInput.add(jLabel119);
        jLabel119.setBounds(450, 420, 40, 23);

        jLabel120.setForeground(new java.awt.Color(0, 0, 0));
        jLabel120.setText("Pupil :");
        jLabel120.setName("jLabel120"); // NOI18N
        FormInput.add(jLabel120);
        jLabel120.setBounds(400, 450, 90, 23);

        cmbPupil.setForeground(new java.awt.Color(0, 0, 0));
        cmbPupil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Isokor", "Anisokor" }));
        cmbPupil.setName("cmbPupil"); // NOI18N
        FormInput.add(cmbPupil);
        cmbPupil.setBounds(494, 450, 75, 23);

        jLabel121.setForeground(new java.awt.Color(0, 0, 0));
        jLabel121.setText("Diameter (Kanan) :");
        jLabel121.setName("jLabel121"); // NOI18N
        FormInput.add(jLabel121);
        jLabel121.setBounds(580, 450, 110, 23);

        TDiam_kanan.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kanan.setName("TDiam_kanan"); // NOI18N
        TDiam_kanan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kananKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kanan);
        TDiam_kanan.setBounds(695, 450, 45, 23);

        jLabel122.setForeground(new java.awt.Color(0, 0, 0));
        jLabel122.setText("Reflek Cahaya :");
        jLabel122.setName("jLabel122"); // NOI18N
        FormInput.add(jLabel122);
        jLabel122.setBounds(400, 480, 90, 23);

        TReflek.setForeground(new java.awt.Color(0, 0, 0));
        TReflek.setName("TReflek"); // NOI18N
        TReflek.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TReflekKeyPressed(evt);
            }
        });
        FormInput.add(TReflek);
        TReflek.setBounds(494, 480, 80, 23);

        jLabel123.setForeground(new java.awt.Color(0, 0, 0));
        jLabel123.setText("Meningeal Signs :");
        jLabel123.setName("jLabel123"); // NOI18N
        FormInput.add(jLabel123);
        jLabel123.setBounds(400, 510, 90, 23);

        TMeningeal.setForeground(new java.awt.Color(0, 0, 0));
        TMeningeal.setName("TMeningeal"); // NOI18N
        TMeningeal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMeningealKeyPressed(evt);
            }
        });
        FormInput.add(TMeningeal);
        TMeningeal.setBounds(494, 510, 330, 23);

        jLabel124.setForeground(new java.awt.Color(0, 0, 0));
        jLabel124.setText("Lateralisasi :");
        jLabel124.setName("jLabel124"); // NOI18N
        FormInput.add(jLabel124);
        jLabel124.setBounds(400, 540, 90, 23);

        cmbLater.setForeground(new java.awt.Color(0, 0, 0));
        cmbLater.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Kanan", "Kiri" }));
        cmbLater.setName("cmbLater"); // NOI18N
        FormInput.add(cmbLater);
        cmbLater.setBounds(494, 540, 80, 23);

        jLabel125.setForeground(new java.awt.Color(0, 51, 204));
        jLabel125.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel125.setText("Eksposur :");
        jLabel125.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel125.setName("jLabel125"); // NOI18N
        FormInput.add(jLabel125);
        jLabel125.setBounds(20, 460, 70, 23);

        jLabel127.setForeground(new java.awt.Color(0, 51, 204));
        jLabel127.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel127.setText("Skor Downe :");
        jLabel127.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel127.setName("jLabel127"); // NOI18N
        FormInput.add(jLabel127);
        jLabel127.setBounds(250, 580, 120, 23);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("1. Frekuensi Napas :");
        jLabel128.setName("jLabel128"); // NOI18N
        FormInput.add(jLabel128);
        jLabel128.setBounds(240, 600, 110, 23);

        TFrekuensi.setForeground(new java.awt.Color(0, 0, 0));
        TFrekuensi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TFrekuensi.setName("TFrekuensi"); // NOI18N
        TFrekuensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TFrekuensiKeyPressed(evt);
            }
        });
        FormInput.add(TFrekuensi);
        TFrekuensi.setBounds(355, 600, 50, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("2. Retraksi :");
        jLabel129.setName("jLabel129"); // NOI18N
        FormInput.add(jLabel129);
        jLabel129.setBounds(240, 630, 110, 23);

        TRetraksi.setForeground(new java.awt.Color(0, 0, 0));
        TRetraksi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TRetraksi.setName("TRetraksi"); // NOI18N
        TRetraksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TRetraksiKeyPressed(evt);
            }
        });
        FormInput.add(TRetraksi);
        TRetraksi.setBounds(355, 630, 50, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("3. Sianosis :");
        jLabel130.setName("jLabel130"); // NOI18N
        FormInput.add(jLabel130);
        jLabel130.setBounds(240, 660, 110, 23);

        TSianosis.setForeground(new java.awt.Color(0, 0, 0));
        TSianosis.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TSianosis.setName("TSianosis"); // NOI18N
        TSianosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TSianosisKeyPressed(evt);
            }
        });
        FormInput.add(TSianosis);
        TSianosis.setBounds(355, 660, 50, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("4. Air Entry :");
        jLabel131.setName("jLabel131"); // NOI18N
        FormInput.add(jLabel131);
        jLabel131.setBounds(240, 690, 110, 23);

        TAir.setForeground(new java.awt.Color(0, 0, 0));
        TAir.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TAir.setName("TAir"); // NOI18N
        TAir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAirKeyPressed(evt);
            }
        });
        FormInput.add(TAir);
        TAir.setBounds(355, 690, 50, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("5. Merintih :");
        jLabel132.setName("jLabel132"); // NOI18N
        FormInput.add(jLabel132);
        jLabel132.setBounds(240, 720, 110, 23);

        TMerintih.setForeground(new java.awt.Color(0, 0, 0));
        TMerintih.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TMerintih.setName("TMerintih"); // NOI18N
        TMerintih.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TMerintihKeyPressed(evt);
            }
        });
        FormInput.add(TMerintih);
        TMerintih.setBounds(355, 720, 50, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Alergi :");
        jLabel133.setName("jLabel133"); // NOI18N
        FormInput.add(jLabel133);
        jLabel133.setBounds(410, 600, 50, 23);

        TAlergi.setForeground(new java.awt.Color(0, 0, 0));
        TAlergi.setName("TAlergi"); // NOI18N
        TAlergi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlergiKeyPressed(evt);
            }
        });
        FormInput.add(TAlergi);
        TAlergi.setBounds(465, 600, 330, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Riwayat Penyakit Dahulu :");
        jLabel134.setName("jLabel134"); // NOI18N
        FormInput.add(jLabel134);
        jLabel134.setBounds(410, 630, 140, 23);

        ChkHipertensi.setBackground(new java.awt.Color(255, 255, 250));
        ChkHipertensi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkHipertensi.setForeground(new java.awt.Color(0, 0, 0));
        ChkHipertensi.setText("Hipertensi");
        ChkHipertensi.setBorderPainted(true);
        ChkHipertensi.setBorderPaintedFlat(true);
        ChkHipertensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkHipertensi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkHipertensi.setName("ChkHipertensi"); // NOI18N
        ChkHipertensi.setOpaque(false);
        ChkHipertensi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkHipertensi);
        ChkHipertensi.setBounds(450, 650, 80, 23);

        ChkDM.setBackground(new java.awt.Color(255, 255, 250));
        ChkDM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDM.setForeground(new java.awt.Color(0, 0, 0));
        ChkDM.setText("Diabetes Mellitus");
        ChkDM.setBorderPainted(true);
        ChkDM.setBorderPaintedFlat(true);
        ChkDM.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDM.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDM.setName("ChkDM"); // NOI18N
        ChkDM.setOpaque(false);
        ChkDM.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDM);
        ChkDM.setBounds(540, 650, 110, 23);

        ChkJantung.setBackground(new java.awt.Color(255, 255, 250));
        ChkJantung.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkJantung.setForeground(new java.awt.Color(0, 0, 0));
        ChkJantung.setText("Jantung");
        ChkJantung.setBorderPainted(true);
        ChkJantung.setBorderPaintedFlat(true);
        ChkJantung.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkJantung.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkJantung.setName("ChkJantung"); // NOI18N
        ChkJantung.setOpaque(false);
        ChkJantung.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkJantung);
        ChkJantung.setBounds(660, 650, 70, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Lainnya :");
        jLabel135.setName("jLabel135"); // NOI18N
        FormInput.add(jLabel135);
        jLabel135.setBounds(410, 680, 90, 23);

        TLainPenyakit.setForeground(new java.awt.Color(0, 0, 0));
        TLainPenyakit.setName("TLainPenyakit"); // NOI18N
        FormInput.add(TLainPenyakit);
        TLainPenyakit.setBounds(505, 680, 290, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Riwayat Kebiasaan :");
        jLabel136.setName("jLabel136"); // NOI18N
        FormInput.add(jLabel136);
        jLabel136.setBounds(410, 710, 140, 23);

        ChkMerokok.setBackground(new java.awt.Color(255, 255, 250));
        ChkMerokok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMerokok.setForeground(new java.awt.Color(0, 0, 0));
        ChkMerokok.setText("Merokok");
        ChkMerokok.setBorderPainted(true);
        ChkMerokok.setBorderPaintedFlat(true);
        ChkMerokok.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkMerokok.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMerokok.setName("ChkMerokok"); // NOI18N
        ChkMerokok.setOpaque(false);
        ChkMerokok.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkMerokok);
        ChkMerokok.setBounds(560, 710, 70, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Lainnya :");
        jLabel137.setName("jLabel137"); // NOI18N
        FormInput.add(jLabel137);
        jLabel137.setBounds(410, 740, 90, 23);

        TLainKebiasaan.setForeground(new java.awt.Color(0, 0, 0));
        TLainKebiasaan.setName("TLainKebiasaan"); // NOI18N
        FormInput.add(TLainKebiasaan);
        TLainKebiasaan.setBounds(505, 740, 290, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Anamnesis :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(20, 780, 70, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        TAnamnesis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAnamnesis.setColumns(20);
        TAnamnesis.setRows(5);
        TAnamnesis.setName("TAnamnesis"); // NOI18N
        TAnamnesis.setPreferredSize(new java.awt.Dimension(162, 200));
        TAnamnesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAnamnesisKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(TAnamnesis);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(95, 780, 700, 110);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 900, 880, 1);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel139.setText("III. SURVEI SEKUNDER");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(10, 900, 330, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Pemeriksaan Fisik :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(20, 920, 110, 23);

        TPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        TPemeriksaan.setName("TPemeriksaan"); // NOI18N
        TPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPemeriksaanKeyPressed(evt);
            }
        });
        FormInput.add(TPemeriksaan);
        TPemeriksaan.setBounds(135, 920, 660, 23);

        jLabel141.setForeground(new java.awt.Color(0, 51, 204));
        jLabel141.setText("Kepala Leher :");
        jLabel141.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(20, 950, 110, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Konjungtiva :");
        jLabel142.setName("jLabel142"); // NOI18N
        FormInput.add(jLabel142);
        jLabel142.setBounds(20, 970, 110, 23);

        cmbKonjung.setForeground(new java.awt.Color(0, 0, 0));
        cmbKonjung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Pucat", "Pink" }));
        cmbKonjung.setName("cmbKonjung"); // NOI18N
        FormInput.add(cmbKonjung);
        cmbKonjung.setBounds(135, 970, 60, 23);

        cmbSklera.setForeground(new java.awt.Color(0, 0, 0));
        cmbSklera.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ikterik", "Tidak Ikterik" }));
        cmbSklera.setName("cmbSklera"); // NOI18N
        FormInput.add(cmbSklera);
        cmbSklera.setBounds(135, 1000, 90, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Sklera :");
        jLabel143.setName("jLabel143"); // NOI18N
        FormInput.add(jLabel143);
        jLabel143.setBounds(20, 1000, 110, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Bibir/Lidah :");
        jLabel144.setName("jLabel144"); // NOI18N
        FormInput.add(jLabel144);
        jLabel144.setBounds(20, 1030, 110, 23);

        cmbBibir.setForeground(new java.awt.Color(0, 0, 0));
        cmbBibir.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Sianosis", "Tidak Sianosis" }));
        cmbBibir.setName("cmbBibir"); // NOI18N
        FormInput.add(cmbBibir);
        cmbBibir.setBounds(135, 1030, 100, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Mukosa :");
        jLabel145.setName("jLabel145"); // NOI18N
        FormInput.add(jLabel145);
        jLabel145.setBounds(20, 1060, 110, 23);

        cmbMukosa.setForeground(new java.awt.Color(0, 0, 0));
        cmbMukosa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Kering", "Basah" }));
        cmbMukosa.setName("cmbMukosa"); // NOI18N
        FormInput.add(cmbMukosa);
        cmbMukosa.setBounds(135, 1060, 65, 23);

        jLabel146.setForeground(new java.awt.Color(0, 51, 204));
        jLabel146.setText("Leher :");
        jLabel146.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(250, 950, 100, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Deviasi Trakea :");
        jLabel147.setName("jLabel147"); // NOI18N
        FormInput.add(jLabel147);
        jLabel147.setBounds(250, 970, 100, 23);

        cmbDeviasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbDeviasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak Ada", "Kanan", "Kiri" }));
        cmbDeviasi.setName("cmbDeviasi"); // NOI18N
        FormInput.add(cmbDeviasi);
        cmbDeviasi.setBounds(356, 970, 80, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("JVP :");
        jLabel148.setName("jLabel148"); // NOI18N
        FormInput.add(jLabel148);
        jLabel148.setBounds(250, 1000, 100, 23);

        cmbJVP.setForeground(new java.awt.Color(0, 0, 0));
        cmbJVP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Meningkat", "Tidak" }));
        cmbJVP.setName("cmbJVP"); // NOI18N
        FormInput.add(cmbJVP);
        cmbJVP.setBounds(356, 1000, 80, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("LNN :");
        jLabel149.setName("jLabel149"); // NOI18N
        FormInput.add(jLabel149);
        jLabel149.setBounds(250, 1030, 100, 23);

        cmbLNN.setForeground(new java.awt.Color(0, 0, 0));
        cmbLNN.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Teraba", "Tidak" }));
        cmbLNN.setName("cmbLNN"); // NOI18N
        FormInput.add(cmbLNN);
        cmbLNN.setBounds(356, 1030, 80, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Tiroid :");
        jLabel150.setName("jLabel150"); // NOI18N
        FormInput.add(jLabel150);
        jLabel150.setBounds(250, 1060, 100, 23);

        cmbTiroid.setForeground(new java.awt.Color(0, 0, 0));
        cmbTiroid.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Teraba", "Tidak" }));
        cmbTiroid.setName("cmbTiroid"); // NOI18N
        FormInput.add(cmbTiroid);
        cmbTiroid.setBounds(356, 1060, 80, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Thorax :");
        jLabel151.setName("jLabel151"); // NOI18N
        FormInput.add(jLabel151);
        jLabel151.setBounds(20, 1090, 50, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Jantung :");
        jLabel152.setName("jLabel152"); // NOI18N
        FormInput.add(jLabel152);
        jLabel152.setBounds(80, 1090, 50, 23);

        scrollPane4.setName("scrollPane4"); // NOI18N

        TJantung.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TJantung.setColumns(20);
        TJantung.setRows(5);
        TJantung.setName("TJantung"); // NOI18N
        TJantung.setPreferredSize(new java.awt.Dimension(162, 200));
        TJantung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TJantungKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(TJantung);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(135, 1090, 330, 70);

        scrollPane5.setName("scrollPane5"); // NOI18N

        TParu.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TParu.setColumns(20);
        TParu.setRows(5);
        TParu.setName("TParu"); // NOI18N
        TParu.setPreferredSize(new java.awt.Dimension(162, 200));
        TParu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TParuKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(TParu);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(135, 1167, 330, 70);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Paru :");
        jLabel153.setName("jLabel153"); // NOI18N
        FormInput.add(jLabel153);
        jLabel153.setBounds(20, 1167, 110, 23);

        scrollPane6.setName("scrollPane6"); // NOI18N

        TAbdomen.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAbdomen.setColumns(20);
        TAbdomen.setRows(5);
        TAbdomen.setName("TAbdomen"); // NOI18N
        TAbdomen.setPreferredSize(new java.awt.Dimension(162, 200));
        TAbdomen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAbdomenKeyPressed(evt);
            }
        });
        scrollPane6.setViewportView(TAbdomen);

        FormInput.add(scrollPane6);
        scrollPane6.setBounds(135, 1244, 330, 70);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Abdomen & Pelvis :");
        jLabel154.setName("jLabel154"); // NOI18N
        FormInput.add(jLabel154);
        jLabel154.setBounds(20, 1244, 110, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        TPunggung.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TPunggung.setColumns(20);
        TPunggung.setRows(5);
        TPunggung.setName("TPunggung"); // NOI18N
        TPunggung.setPreferredSize(new java.awt.Dimension(162, 200));
        TPunggung.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPunggungKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(TPunggung);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(135, 1321, 330, 70);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Punggung & Pinggang :");
        jLabel155.setName("jLabel155"); // NOI18N
        FormInput.add(jLabel155);
        jLabel155.setBounds(10, 1321, 120, 23);

        scrollPane8.setName("scrollPane8"); // NOI18N

        TEkstremitas.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TEkstremitas.setColumns(20);
        TEkstremitas.setRows(5);
        TEkstremitas.setName("TEkstremitas"); // NOI18N
        TEkstremitas.setPreferredSize(new java.awt.Dimension(162, 200));
        TEkstremitas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TEkstremitasKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(TEkstremitas);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(135, 1399, 330, 70);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Ekstremitas :");
        jLabel156.setName("jLabel156"); // NOI18N
        FormInput.add(jLabel156);
        jLabel156.setBounds(10, 1399, 120, 23);

        jLabel157.setForeground(new java.awt.Color(0, 51, 204));
        jLabel157.setText("Pemeriksaan Penunjang :");
        jLabel157.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel157.setName("jLabel157"); // NOI18N
        FormInput.add(jLabel157);
        jLabel157.setBounds(0, 1575, 150, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Laboratorium :");
        jLabel158.setName("jLabel158"); // NOI18N
        FormInput.add(jLabel158);
        jLabel158.setBounds(10, 1595, 100, 23);

        TLab.setForeground(new java.awt.Color(0, 0, 0));
        TLab.setName("TLab"); // NOI18N
        TLab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TLabKeyPressed(evt);
            }
        });
        FormInput.add(TLab);
        TLab.setBounds(115, 1595, 340, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("X-Ray :");
        jLabel159.setName("jLabel159"); // NOI18N
        FormInput.add(jLabel159);
        jLabel159.setBounds(10, 1625, 100, 23);

        Txray.setForeground(new java.awt.Color(0, 0, 0));
        Txray.setName("Txray"); // NOI18N
        Txray.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxrayKeyPressed(evt);
            }
        });
        FormInput.add(Txray);
        Txray.setBounds(115, 1625, 340, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("ECG :");
        jLabel160.setName("jLabel160"); // NOI18N
        FormInput.add(jLabel160);
        jLabel160.setBounds(10, 1655, 100, 23);

        Tecg.setForeground(new java.awt.Color(0, 0, 0));
        Tecg.setName("Tecg"); // NOI18N
        Tecg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TecgKeyPressed(evt);
            }
        });
        FormInput.add(Tecg);
        Tecg.setBounds(115, 1655, 340, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setText("CT Scan :");
        jLabel161.setName("jLabel161"); // NOI18N
        FormInput.add(jLabel161);
        jLabel161.setBounds(465, 1595, 70, 23);

        Tctscan.setForeground(new java.awt.Color(0, 0, 0));
        Tctscan.setName("Tctscan"); // NOI18N
        Tctscan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TctscanKeyPressed(evt);
            }
        });
        FormInput.add(Tctscan);
        Tctscan.setBounds(540, 1595, 340, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("USG :");
        jLabel162.setName("jLabel162"); // NOI18N
        FormInput.add(jLabel162);
        jLabel162.setBounds(465, 1625, 70, 23);

        Tusg.setForeground(new java.awt.Color(0, 0, 0));
        Tusg.setName("Tusg"); // NOI18N
        Tusg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TusgKeyPressed(evt);
            }
        });
        FormInput.add(Tusg);
        Tusg.setBounds(540, 1625, 340, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("Lainnya :");
        jLabel163.setName("jLabel163"); // NOI18N
        FormInput.add(jLabel163);
        jLabel163.setBounds(465, 1655, 70, 23);

        Tlainya.setForeground(new java.awt.Color(0, 0, 0));
        Tlainya.setName("Tlainya"); // NOI18N
        Tlainya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainyaKeyPressed(evt);
            }
        });
        FormInput.add(Tlainya);
        Tlainya.setBounds(540, 1655, 340, 23);

        jLabel164.setForeground(new java.awt.Color(0, 51, 204));
        jLabel164.setText("Diagnosis Medis Sementara / Masalah :");
        jLabel164.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel164.setName("jLabel164"); // NOI18N
        FormInput.add(jLabel164);
        jLabel164.setBounds(0, 1685, 210, 23);

        scrollPane9.setName("scrollPane9"); // NOI18N

        TDiagSementara.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TDiagSementara.setColumns(20);
        TDiagSementara.setRows(5);
        TDiagSementara.setName("TDiagSementara"); // NOI18N
        TDiagSementara.setPreferredSize(new java.awt.Dimension(162, 200));
        TDiagSementara.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiagSementaraKeyPressed(evt);
            }
        });
        scrollPane9.setViewportView(TDiagSementara);

        FormInput.add(scrollPane9);
        scrollPane9.setBounds(25, 1705, 680, 70);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("ICD-10 :");
        jLabel165.setName("jLabel165"); // NOI18N
        FormInput.add(jLabel165);
        jLabel165.setBounds(705, 1705, 60, 23);

        Ticd.setForeground(new java.awt.Color(0, 0, 0));
        Ticd.setName("Ticd"); // NOI18N
        Ticd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TicdKeyPressed(evt);
            }
        });
        FormInput.add(Ticd);
        Ticd.setBounds(770, 1705, 80, 23);

        jLabel166.setForeground(new java.awt.Color(0, 51, 204));
        jLabel166.setText("Rencana / Instruksi :");
        jLabel166.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel166.setName("jLabel166"); // NOI18N
        FormInput.add(jLabel166);
        jLabel166.setBounds(0, 1785, 120, 23);

        jLabel167.setForeground(new java.awt.Color(0, 51, 204));
        jLabel167.setText("Telah Diberikan Informasi / Edukasi Tentang :");
        jLabel167.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel167.setName("jLabel167"); // NOI18N
        FormInput.add(jLabel167);
        jLabel167.setBounds(0, 1900, 240, 23);

        jLabel168.setForeground(new java.awt.Color(0, 0, 0));
        jLabel168.setText("Informasi / Edukasi Tentang :");
        jLabel168.setName("jLabel168"); // NOI18N
        FormInput.add(jLabel168);
        jLabel168.setBounds(10, 1920, 150, 23);

        Tedukasi.setForeground(new java.awt.Color(0, 0, 0));
        Tedukasi.setName("Tedukasi"); // NOI18N
        Tedukasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TedukasiKeyPressed(evt);
            }
        });
        FormInput.add(Tedukasi);
        Tedukasi.setBounds(165, 1920, 710, 23);

        jLabel169.setForeground(new java.awt.Color(0, 0, 0));
        jLabel169.setText("Rencana Asuhan Diharapkan :");
        jLabel169.setName("jLabel169"); // NOI18N
        FormInput.add(jLabel169);
        jLabel169.setBounds(10, 1950, 150, 23);

        Trencana.setForeground(new java.awt.Color(0, 0, 0));
        Trencana.setName("Trencana"); // NOI18N
        Trencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrencanaKeyPressed(evt);
            }
        });
        FormInput.add(Trencana);
        Trencana.setBounds(165, 1950, 710, 23);

        jLabel170.setForeground(new java.awt.Color(0, 0, 0));
        jLabel170.setText("Pemberi Edukasi / Informasi :");
        jLabel170.setName("jLabel170"); // NOI18N
        FormInput.add(jLabel170);
        jLabel170.setBounds(10, 1980, 150, 23);

        kdpetugas.setEditable(false);
        kdpetugas.setForeground(new java.awt.Color(0, 0, 0));
        kdpetugas.setName("kdpetugas"); // NOI18N
        FormInput.add(kdpetugas);
        kdpetugas.setBounds(165, 1980, 110, 23);

        nmpetugas.setEditable(false);
        nmpetugas.setForeground(new java.awt.Color(0, 0, 0));
        nmpetugas.setName("nmpetugas"); // NOI18N
        FormInput.add(nmpetugas);
        nmpetugas.setBounds(280, 1980, 260, 23);

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
        BtnPetugas.setBounds(540, 1980, 28, 23);

        jLabel171.setForeground(new java.awt.Color(0, 0, 0));
        jLabel171.setText("Penerima Edukasi :");
        jLabel171.setName("jLabel171"); // NOI18N
        FormInput.add(jLabel171);
        jLabel171.setBounds(575, 1980, 100, 23);

        nmpenerima.setForeground(new java.awt.Color(0, 0, 0));
        nmpenerima.setName("nmpenerima"); // NOI18N
        FormInput.add(nmpenerima);
        nmpenerima.setBounds(680, 1980, 195, 23);

        TJlhSkor.setEditable(false);
        TJlhSkor.setForeground(new java.awt.Color(0, 0, 0));
        TJlhSkor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJlhSkor.setName("TJlhSkor"); // NOI18N
        FormInput.add(TJlhSkor);
        TJlhSkor.setBounds(355, 750, 50, 23);

        BtnJumlah.setForeground(new java.awt.Color(0, 0, 0));
        BtnJumlah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnJumlah.setMnemonic('2');
        BtnJumlah.setText("Jumlah Skor :");
        BtnJumlah.setToolTipText("Alt+2");
        BtnJumlah.setGlassColor(new java.awt.Color(0, 204, 255));
        BtnJumlah.setName("BtnJumlah"); // NOI18N
        BtnJumlah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJumlahActionPerformed(evt);
            }
        });
        FormInput.add(BtnJumlah);
        BtnJumlah.setBounds(230, 750, 120, 23);

        jLabel172.setForeground(new java.awt.Color(0, 0, 0));
        jLabel172.setText("Nama Dokter : ");
        jLabel172.setName("jLabel172"); // NOI18N
        FormInput.add(jLabel172);
        jLabel172.setBounds(10, 2010, 150, 23);

        kddokter.setEditable(false);
        kddokter.setForeground(new java.awt.Color(0, 0, 0));
        kddokter.setName("kddokter"); // NOI18N
        FormInput.add(kddokter);
        kddokter.setBounds(165, 2010, 110, 23);

        nmdokter.setEditable(false);
        nmdokter.setForeground(new java.awt.Color(0, 0, 0));
        nmdokter.setName("nmdokter"); // NOI18N
        FormInput.add(nmdokter);
        nmdokter.setBounds(280, 2010, 260, 23);

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
        BtnDokter.setBounds(540, 2010, 28, 23);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 2040, 880, 1);

        jLabel173.setForeground(new java.awt.Color(0, 0, 0));
        jLabel173.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel173.setText("IV. PASIEN KELUAR IGD");
        jLabel173.setName("jLabel173"); // NOI18N
        FormInput.add(jLabel173);
        jLabel173.setBounds(10, 2040, 330, 23);

        jLabel174.setForeground(new java.awt.Color(0, 0, 0));
        jLabel174.setText("Tgl. & Jam Keluar :");
        jLabel174.setName("jLabel174"); // NOI18N
        FormInput.add(jLabel174);
        jLabel174.setBounds(10, 2070, 125, 23);

        TglKeluar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2023 11:09:16" }));
        TglKeluar.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglKeluar.setName("TglKeluar"); // NOI18N
        TglKeluar.setOpaque(false);
        FormInput.add(TglKeluar);
        TglKeluar.setBounds(140, 2070, 130, 23);

        jLabel175.setForeground(new java.awt.Color(0, 0, 0));
        jLabel175.setText("Opname Diruangan :");
        jLabel175.setName("jLabel175"); // NOI18N
        FormInput.add(jLabel175);
        jLabel175.setBounds(10, 2100, 125, 23);

        cmbRuangan.setForeground(new java.awt.Color(0, 0, 0));
        cmbRuangan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "AS-SAMI/1", "AS-SAMI/2" }));
        cmbRuangan.setName("cmbRuangan"); // NOI18N
        cmbRuangan.setOpaque(false);
        cmbRuangan.setPreferredSize(new java.awt.Dimension(145, 23));
        cmbRuangan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbRuanganMouseReleased(evt);
            }
        });
        FormInput.add(cmbRuangan);
        cmbRuangan.setBounds(140, 2100, 170, 23);

        jLabel176.setForeground(new java.awt.Color(0, 0, 0));
        jLabel176.setText("Indikasi Masuk :");
        jLabel176.setName("jLabel176"); // NOI18N
        FormInput.add(jLabel176);
        jLabel176.setBounds(10, 2130, 125, 23);

        Tindikasi.setForeground(new java.awt.Color(0, 0, 0));
        Tindikasi.setName("Tindikasi"); // NOI18N
        Tindikasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TindikasiKeyPressed(evt);
            }
        });
        FormInput.add(Tindikasi);
        Tindikasi.setBounds(140, 2130, 330, 23);

        jLabel177.setForeground(new java.awt.Color(0, 0, 0));
        jLabel177.setText("Dipulangkan, Kontrol Ke :");
        jLabel177.setName("jLabel177"); // NOI18N
        FormInput.add(jLabel177);
        jLabel177.setBounds(10, 2160, 125, 23);

        Tdipulangkan.setForeground(new java.awt.Color(0, 0, 0));
        Tdipulangkan.setName("Tdipulangkan"); // NOI18N
        Tdipulangkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdipulangkanKeyPressed(evt);
            }
        });
        FormInput.add(Tdipulangkan);
        Tdipulangkan.setBounds(140, 2160, 330, 23);

        jLabel178.setForeground(new java.awt.Color(0, 0, 0));
        jLabel178.setText("Dirujuk Ke :");
        jLabel178.setName("jLabel178"); // NOI18N
        FormInput.add(jLabel178);
        jLabel178.setBounds(10, 2190, 125, 23);

        Tdirujuk.setForeground(new java.awt.Color(0, 0, 0));
        Tdirujuk.setName("Tdirujuk"); // NOI18N
        Tdirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdirujukKeyPressed(evt);
            }
        });
        FormInput.add(Tdirujuk);
        Tdirujuk.setBounds(140, 2190, 330, 23);

        jLabel179.setForeground(new java.awt.Color(0, 0, 0));
        jLabel179.setText("Alasan Dirujuk :");
        jLabel179.setName("jLabel179"); // NOI18N
        FormInput.add(jLabel179);
        jLabel179.setBounds(10, 2220, 125, 23);

        scrollPane10.setName("scrollPane10"); // NOI18N

        TAlasanDirujuk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TAlasanDirujuk.setColumns(20);
        TAlasanDirujuk.setRows(5);
        TAlasanDirujuk.setName("TAlasanDirujuk"); // NOI18N
        TAlasanDirujuk.setPreferredSize(new java.awt.Dimension(162, 110));
        TAlasanDirujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlasanDirujukKeyPressed(evt);
            }
        });
        scrollPane10.setViewportView(TAlasanDirujuk);

        FormInput.add(scrollPane10);
        scrollPane10.setBounds(140, 2220, 680, 70);

        ChkMeninggal.setBackground(new java.awt.Color(255, 255, 250));
        ChkMeninggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkMeninggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkMeninggal.setText("Meninggal Jam :");
        ChkMeninggal.setBorderPainted(true);
        ChkMeninggal.setBorderPaintedFlat(true);
        ChkMeninggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkMeninggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkMeninggal.setName("ChkMeninggal"); // NOI18N
        ChkMeninggal.setOpaque(false);
        ChkMeninggal.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkMeninggal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkMeninggalActionPerformed(evt);
            }
        });
        FormInput.add(ChkMeninggal);
        ChkMeninggal.setBounds(411, 2070, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(515, 2070, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(565, 2070, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(615, 2070, 45, 23);

        jLabel180.setForeground(new java.awt.Color(0, 0, 0));
        jLabel180.setText("Penyebab :");
        jLabel180.setName("jLabel180"); // NOI18N
        FormInput.add(jLabel180);
        jLabel180.setBounds(420, 2100, 90, 23);

        Tpenyebab.setForeground(new java.awt.Color(0, 0, 0));
        Tpenyebab.setName("Tpenyebab"); // NOI18N
        Tpenyebab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TpenyebabKeyPressed(evt);
            }
        });
        FormInput.add(Tpenyebab);
        Tpenyebab.setBounds(515, 2100, 360, 23);

        jLabel181.setForeground(new java.awt.Color(0, 0, 0));
        jLabel181.setText("K/u :");
        jLabel181.setName("jLabel181"); // NOI18N
        FormInput.add(jLabel181);
        jLabel181.setBounds(470, 2130, 40, 23);

        Tku.setForeground(new java.awt.Color(0, 0, 0));
        Tku.setName("Tku"); // NOI18N
        Tku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkuKeyPressed(evt);
            }
        });
        FormInput.add(Tku);
        Tku.setBounds(515, 2130, 360, 23);

        jLabel182.setForeground(new java.awt.Color(0, 0, 0));
        jLabel182.setText("TD :");
        jLabel182.setName("jLabel182"); // NOI18N
        FormInput.add(jLabel182);
        jLabel182.setBounds(470, 2160, 40, 23);

        Ttd.setForeground(new java.awt.Color(0, 0, 0));
        Ttd.setName("Ttd"); // NOI18N
        Ttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtdKeyPressed(evt);
            }
        });
        FormInput.add(Ttd);
        Ttd.setBounds(515, 2160, 70, 23);

        jLabel183.setForeground(new java.awt.Color(0, 0, 0));
        jLabel183.setText("HR :");
        jLabel183.setName("jLabel183"); // NOI18N
        FormInput.add(jLabel183);
        jLabel183.setBounds(470, 2190, 40, 23);

        Thr.setForeground(new java.awt.Color(0, 0, 0));
        Thr.setName("Thr"); // NOI18N
        Thr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ThrKeyPressed(evt);
            }
        });
        FormInput.add(Thr);
        Thr.setBounds(515, 2190, 70, 23);

        jLabel184.setForeground(new java.awt.Color(0, 0, 0));
        jLabel184.setText("RR :");
        jLabel184.setName("jLabel184"); // NOI18N
        FormInput.add(jLabel184);
        jLabel184.setBounds(590, 2160, 40, 23);

        jLabel185.setForeground(new java.awt.Color(0, 0, 0));
        jLabel185.setText("Temp :");
        jLabel185.setName("jLabel185"); // NOI18N
        FormInput.add(jLabel185);
        jLabel185.setBounds(590, 2190, 40, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        FormInput.add(Trr);
        Trr.setBounds(636, 2160, 70, 23);

        Ttemp.setForeground(new java.awt.Color(0, 0, 0));
        Ttemp.setName("Ttemp"); // NOI18N
        Ttemp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtempKeyPressed(evt);
            }
        });
        FormInput.add(Ttemp);
        Ttemp.setBounds(636, 2190, 70, 23);

        jLabel186.setForeground(new java.awt.Color(0, 0, 0));
        jLabel186.setText("SpO2 :");
        jLabel186.setName("jLabel186"); // NOI18N
        FormInput.add(jLabel186);
        jLabel186.setBounds(708, 2160, 40, 23);

        jLabel187.setForeground(new java.awt.Color(0, 0, 0));
        jLabel187.setText("GCS :");
        jLabel187.setName("jLabel187"); // NOI18N
        FormInput.add(jLabel187);
        jLabel187.setBounds(708, 2190, 40, 23);

        Tspo2.setForeground(new java.awt.Color(0, 0, 0));
        Tspo2.setName("Tspo2"); // NOI18N
        Tspo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tspo2KeyPressed(evt);
            }
        });
        FormInput.add(Tspo2);
        Tspo2.setBounds(754, 2160, 70, 23);

        Tgcs.setForeground(new java.awt.Color(0, 0, 0));
        Tgcs.setName("Tgcs"); // NOI18N
        Tgcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsKeyPressed(evt);
            }
        });
        FormInput.add(Tgcs);
        Tgcs.setBounds(754, 2190, 70, 23);

        cmbObstruksi.setForeground(new java.awt.Color(0, 0, 0));
        cmbObstruksi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Stridor", "Snoring", "Gurgling", "Wheezing" }));
        cmbObstruksi.setName("cmbObstruksi"); // NOI18N
        FormInput.add(cmbObstruksi);
        cmbObstruksi.setBounds(125, 280, 80, 23);

        ChkDeformitas.setBackground(new java.awt.Color(255, 255, 250));
        ChkDeformitas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkDeformitas.setForeground(new java.awt.Color(0, 0, 0));
        ChkDeformitas.setText("Deformitas");
        ChkDeformitas.setBorderPainted(true);
        ChkDeformitas.setBorderPaintedFlat(true);
        ChkDeformitas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkDeformitas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkDeformitas.setName("ChkDeformitas"); // NOI18N
        ChkDeformitas.setOpaque(false);
        ChkDeformitas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkDeformitas);
        ChkDeformitas.setBounds(20, 480, 80, 23);

        ChkContusio.setBackground(new java.awt.Color(255, 255, 250));
        ChkContusio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkContusio.setForeground(new java.awt.Color(0, 0, 0));
        ChkContusio.setText("Contusio");
        ChkContusio.setBorderPainted(true);
        ChkContusio.setBorderPaintedFlat(true);
        ChkContusio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkContusio.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkContusio.setName("ChkContusio"); // NOI18N
        ChkContusio.setOpaque(false);
        ChkContusio.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkContusio);
        ChkContusio.setBounds(20, 510, 80, 23);

        ChkPenetrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkPenetrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkPenetrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkPenetrasi.setText("Penetrasi");
        ChkPenetrasi.setBorderPainted(true);
        ChkPenetrasi.setBorderPaintedFlat(true);
        ChkPenetrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkPenetrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkPenetrasi.setName("ChkPenetrasi"); // NOI18N
        ChkPenetrasi.setOpaque(false);
        ChkPenetrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkPenetrasi);
        ChkPenetrasi.setBounds(20, 540, 80, 23);

        ChkTenderness.setBackground(new java.awt.Color(255, 255, 250));
        ChkTenderness.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTenderness.setForeground(new java.awt.Color(0, 0, 0));
        ChkTenderness.setText("Tenderness");
        ChkTenderness.setBorderPainted(true);
        ChkTenderness.setBorderPaintedFlat(true);
        ChkTenderness.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTenderness.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTenderness.setName("ChkTenderness"); // NOI18N
        ChkTenderness.setOpaque(false);
        ChkTenderness.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTenderness);
        ChkTenderness.setBounds(105, 480, 80, 23);

        ChkSwelling.setBackground(new java.awt.Color(255, 255, 250));
        ChkSwelling.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkSwelling.setForeground(new java.awt.Color(0, 0, 0));
        ChkSwelling.setText("Swelling");
        ChkSwelling.setBorderPainted(true);
        ChkSwelling.setBorderPaintedFlat(true);
        ChkSwelling.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSwelling.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSwelling.setName("ChkSwelling"); // NOI18N
        ChkSwelling.setOpaque(false);
        ChkSwelling.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkSwelling);
        ChkSwelling.setBounds(105, 510, 80, 23);

        ChkEkskoriasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkEkskoriasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkEkskoriasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkEkskoriasi.setText("Ekskoriasi");
        ChkEkskoriasi.setBorderPainted(true);
        ChkEkskoriasi.setBorderPaintedFlat(true);
        ChkEkskoriasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkEkskoriasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkEkskoriasi.setName("ChkEkskoriasi"); // NOI18N
        ChkEkskoriasi.setOpaque(false);
        ChkEkskoriasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkEkskoriasi);
        ChkEkskoriasi.setBounds(105, 540, 80, 23);

        ChkAbrasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkAbrasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkAbrasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkAbrasi.setText("Abrasi");
        ChkAbrasi.setBorderPainted(true);
        ChkAbrasi.setBorderPaintedFlat(true);
        ChkAbrasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkAbrasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkAbrasi.setName("ChkAbrasi"); // NOI18N
        ChkAbrasi.setOpaque(false);
        ChkAbrasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkAbrasi);
        ChkAbrasi.setBounds(195, 480, 65, 23);

        ChkBurn.setBackground(new java.awt.Color(255, 255, 250));
        ChkBurn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkBurn.setForeground(new java.awt.Color(0, 0, 0));
        ChkBurn.setText("Burn");
        ChkBurn.setBorderPainted(true);
        ChkBurn.setBorderPaintedFlat(true);
        ChkBurn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkBurn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkBurn.setName("ChkBurn"); // NOI18N
        ChkBurn.setOpaque(false);
        ChkBurn.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkBurn);
        ChkBurn.setBounds(195, 510, 65, 23);

        ChkLaserasi.setBackground(new java.awt.Color(255, 255, 250));
        ChkLaserasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkLaserasi.setForeground(new java.awt.Color(0, 0, 0));
        ChkLaserasi.setText("Laserasi");
        ChkLaserasi.setBorderPainted(true);
        ChkLaserasi.setBorderPaintedFlat(true);
        ChkLaserasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkLaserasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkLaserasi.setName("ChkLaserasi"); // NOI18N
        ChkLaserasi.setOpaque(false);
        ChkLaserasi.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkLaserasi);
        ChkLaserasi.setBounds(195, 540, 65, 23);

        ChkTdkTampk.setBackground(new java.awt.Color(255, 255, 250));
        ChkTdkTampk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkTdkTampk.setForeground(new java.awt.Color(0, 0, 0));
        ChkTdkTampk.setText("Tidak Tampak Jelas");
        ChkTdkTampk.setBorderPainted(true);
        ChkTdkTampk.setBorderPaintedFlat(true);
        ChkTdkTampk.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkTdkTampk.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTdkTampk.setName("ChkTdkTampk"); // NOI18N
        ChkTdkTampk.setOpaque(false);
        ChkTdkTampk.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkTdkTampk);
        ChkTdkTampk.setBounds(270, 540, 120, 23);

        cmbReguler.setForeground(new java.awt.Color(0, 0, 0));
        cmbReguler.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Reguler", "Irreguler" }));
        cmbReguler.setName("cmbReguler"); // NOI18N
        FormInput.add(cmbReguler);
        cmbReguler.setBounds(405, 250, 80, 23);

        cmbRencana.setForeground(new java.awt.Color(0, 0, 0));
        cmbRencana.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Promotif", "Preventif", "Kuratif", "Paliatif", "Rehabilitatif" }));
        cmbRencana.setName("cmbRencana"); // NOI18N
        cmbRencana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRencanaActionPerformed(evt);
            }
        });
        FormInput.add(cmbRencana);
        cmbRencana.setBounds(20, 1805, 100, 23);

        jLabel188.setForeground(new java.awt.Color(0, 0, 0));
        jLabel188.setText("Ket. Rencana / Instruksi : ");
        jLabel188.setName("jLabel188"); // NOI18N
        FormInput.add(jLabel188);
        jLabel188.setBounds(125, 1805, 140, 23);

        btnICD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnICD.setMnemonic('2');
        btnICD.setToolTipText("Alt+2");
        btnICD.setName("btnICD"); // NOI18N
        btnICD.setPreferredSize(new java.awt.Dimension(28, 23));
        btnICD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnICDActionPerformed(evt);
            }
        });
        FormInput.add(btnICD);
        btnICD.setBounds(850, 1705, 28, 23);

        jLabel189.setForeground(new java.awt.Color(0, 0, 0));
        jLabel189.setText("Penjelasan / Keterangan / Deskripsi Gambar :");
        jLabel189.setName("jLabel189"); // NOI18N
        FormInput.add(jLabel189);
        jLabel189.setBounds(570, 1457, 300, 23);

        scrollPane11.setName("scrollPane11"); // NOI18N

        Tket_gambar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tket_gambar.setColumns(20);
        Tket_gambar.setRows(5);
        Tket_gambar.setName("Tket_gambar"); // NOI18N
        Tket_gambar.setPreferredSize(new java.awt.Dimension(162, 200));
        Tket_gambar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tket_gambarKeyPressed(evt);
            }
        });
        scrollPane11.setViewportView(Tket_gambar);

        FormInput.add(scrollPane11);
        scrollPane11.setBounds(25, 1477, 850, 90);

        jLabel126.setForeground(new java.awt.Color(0, 0, 0));
        jLabel126.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel126.setText("E :");
        jLabel126.setName("jLabel126"); // NOI18N
        FormInput.add(jLabel126);
        jLabel126.setBounds(494, 420, 16, 23);

        TgcsE.setForeground(new java.awt.Color(0, 0, 0));
        TgcsE.setName("TgcsE"); // NOI18N
        TgcsE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsEKeyPressed(evt);
            }
        });
        FormInput.add(TgcsE);
        TgcsE.setBounds(510, 420, 80, 23);

        jLabel190.setForeground(new java.awt.Color(0, 0, 0));
        jLabel190.setText("V :");
        jLabel190.setName("jLabel190"); // NOI18N
        FormInput.add(jLabel190);
        jLabel190.setBounds(590, 420, 25, 23);

        TgcsV.setForeground(new java.awt.Color(0, 0, 0));
        TgcsV.setName("TgcsV"); // NOI18N
        TgcsV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsVKeyPressed(evt);
            }
        });
        FormInput.add(TgcsV);
        TgcsV.setBounds(620, 420, 80, 23);

        jLabel191.setForeground(new java.awt.Color(0, 0, 0));
        jLabel191.setText("M :");
        jLabel191.setName("jLabel191"); // NOI18N
        FormInput.add(jLabel191);
        jLabel191.setBounds(700, 420, 25, 23);

        TgcsM.setForeground(new java.awt.Color(0, 0, 0));
        TgcsM.setName("TgcsM"); // NOI18N
        TgcsM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsMKeyPressed(evt);
            }
        });
        FormInput.add(TgcsM);
        TgcsM.setBounds(730, 420, 80, 23);

        jLabel192.setForeground(new java.awt.Color(0, 0, 0));
        jLabel192.setText("Diameter (Kiri) :");
        jLabel192.setName("jLabel192"); // NOI18N
        FormInput.add(jLabel192);
        jLabel192.setBounds(580, 480, 110, 23);

        TDiam_kiri.setForeground(new java.awt.Color(0, 0, 0));
        TDiam_kiri.setName("TDiam_kiri"); // NOI18N
        TDiam_kiri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TDiam_kiriKeyPressed(evt);
            }
        });
        FormInput.add(TDiam_kiri);
        TDiam_kiri.setBounds(695, 480, 45, 23);

        jLabel193.setForeground(new java.awt.Color(0, 0, 0));
        jLabel193.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel193.setText("mm");
        jLabel193.setName("jLabel193"); // NOI18N
        FormInput.add(jLabel193);
        jLabel193.setBounds(745, 450, 30, 23);

        jLabel194.setForeground(new java.awt.Color(0, 0, 0));
        jLabel194.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel194.setText("mm");
        jLabel194.setName("jLabel194"); // NOI18N
        FormInput.add(jLabel194);
        jLabel194.setBounds(745, 480, 30, 23);

        scrollPane12.setName("scrollPane12"); // NOI18N

        TketRencana.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TketRencana.setColumns(20);
        TketRencana.setRows(5);
        TketRencana.setName("TketRencana"); // NOI18N
        TketRencana.setPreferredSize(new java.awt.Dimension(162, 200));
        TketRencana.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRencanaKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(TketRencana);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(265, 1805, 610, 90);

        ChkGangNafas.setBackground(new java.awt.Color(255, 255, 250));
        ChkGangNafas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGangNafas.setForeground(new java.awt.Color(0, 0, 0));
        ChkGangNafas.setText("Gangguan Jalan Nafas");
        ChkGangNafas.setBorderPainted(true);
        ChkGangNafas.setBorderPaintedFlat(true);
        ChkGangNafas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGangNafas.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGangNafas.setName("ChkGangNafas"); // NOI18N
        ChkGangNafas.setOpaque(false);
        ChkGangNafas.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(ChkGangNafas);
        ChkGangNafas.setBounds(100, 250, 140, 23);

        scrollInput.setViewportView(FormInput);

        internalFrame2.add(scrollInput, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Assesmen / Penilaian", internalFrame2);

        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbPenilaian.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPenilaian.setName("tbPenilaian"); // NOI18N
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

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asuhan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "18-07-2023" }));
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

        TabRawat.addTab("Data Assesmen / Penilaian", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {            
            simpan();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Ttd.setText("");
        Trr.setText("");
        Ttemp.setText("");
        emptTeks();
        ChkMeninggal.setSelected(false);
        cmbJam.setSelectedIndex(0);
        cmbMnt.setSelectedIndex(0);
        cmbDtk.setSelectedIndex(0);

        cmbJam.setEnabled(false);
        cmbMnt.setEnabled(false);
        cmbDtk.setEnabled(false);
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            if (akses.getkode().equals("Admin Utama")) {
                hapus();
            } else {
                if (kddpjp.getText().equals(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 120).toString())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh DPJP yang bersangkutan..!!");
                }
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
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (kddpjp.getText().equals("")) {
            Valid.textKosong(kddpjp, "Mengetahui DPJP");
            BtnDpjp.requestFocus();
        } else {
            if (tbPenilaian.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (kddpjp.getText().equals(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 120).toString())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh DPJP yang bersangkutan..!!");
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
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPenilaian.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());            
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("jlhSkor", TJlhSkor.getText());
            
            if (ChkMeninggal.isSelected() == true) {
                param.put("jam_meninggal", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + ":" + cmbDtk.getSelectedItem().toString());                
            } else {
                param.put("jam_meninggal", "-");
            }
            
            Valid.MyReport("rptCetakPenilaianAwalMedisIGD.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 1 ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                    + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                    + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                    + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                    + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                    + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                    + "pa.kesimpulan_pernafasan, pa.nadi, pa.kulit_mukosa, pa.akral, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                    + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                    + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                    + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                    + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                    + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                    + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                    + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                    + "pa.rencana_asuhan_diharapkan, pg1.nama pemberi_edukasi, pa.penerima_edukasi, pg2.nama nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                    + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                    + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, pa.gcs_v, pa.gcs_m, pa.diameter_kiri "
                    + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                    + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                    + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString() + "'", param);

            Valid.MyReport("rptCetakPenilaianAwalMedisIGD1.jasper", "report", "::[ Laporan Penilaian Awal Medis IGD hal. 2 ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllahir, "
                    + "date_format(pa.tanggal,'Tanggal : %d-%m-%Y    Pukul : %H:%i') mulai_penanganan, if(pa.cervival='ya','V','') cer, "
                    + "if(pa.rjp='ya','V','') rjp, if(pa.defribilasi='ya','V','') def, if(pa.intubasi='ya','V','') intu, if(pa.vtp='ya','V','') vtp, if(pa.dekompresi='ya','V','') dek, "
                    + "if(pa.balut='ya','V','') bal, if(pa.kateter='ya','V','') kat, if(pa.ngt='ya','V','') ngt, if(pa.infus='ya','V','') infs, if(pa.obat='ya','V','') obt, pa.ket_obat, "
                    + "if(pa.tidak_ada='ya','V','') tdk, if(pa.gangguan_jalan_nafas='ya','V','') ggnfs, if(pa.paten='ya','V','') pat, if(pa.obstruksi_partial='ya','V','') obsp, if(pa.data_obstruksi_partial='','-',pa.data_obstruksi_partial) data_obsp, "
                    + "if(pa.obstruksi_total='ya','V','') obst, if(pa.trauma_jalan_nafas='ya','V','') trauma_jln_nfs, pa.trauma, if(pa.resiko_aspirasi='ya','V','') res, pa.aspirasi, "
                    + "if(pa.benda_asing='ya','V','') ben, pa.ket_benda_asing, pa.kesimpulan_jalan_nafas, pa.pernafasan, pa.data_pernafasan, pa.gerakan_dada, pa.tipe_pernafasan, "
                    + "pa.kesimpulan_pernafasan, pa.nadi, pa.kulit_mukosa, pa.akral, pa.crt, pa.kesimpulan_sirkulasi, pa.cukup_bulan, pa.cairan_amnion, pa.pernafasan_menangis, pa.tonus, "
                    + "pa.skor_apgar, pa.gcs_e, pa.pupil, pa.diameter_kanan, pa.reflek_cahaya, pa.meningeal_sign, pa.literasi, if(pa.deformitas='ya','V','') defo, if(pa.contusio='ya','V','') con, "
                    + "if(pa.penetrasi='ya','V','') pen, if(pa.tenderness='ya','V','') ten, if(pa.swelling='ya','V','') swe, if(pa.ekskoriasi='ya','V','') eks, if(pa.abrasi='ya','V','') abr, "
                    + "if(pa.burn='ya','V','') bur, if(pa.laserasi='ya','V','') las, if(pa.tidak_tampak_jelas='ya','V','') tdk_tmpk, pa.frekuensi_nafas, pa.retraksi, pa.sianosis, pa.air_entry, "
                    + "pa.merintih, pa.Alergi, if(pa.hipertensi='ya','V','') hip, if(pa.diabetes='ya','V','') dm, if(pa.jantung='ya','V','') jan, pa.riwayat_penyakit_lain, "
                    + "if(pa.merokok='ya','V','') mer, pa.kebiasaan_lain, pa.anamnesis, pa.pemeriksaan_fisik, pa.konjungtiva, pa.sklera, pa.bibir_lidah, pa.mukosa, pa.deviasi, pa.jvp, "
                    + "pa.lnn, pa.tiroid, pa.survei_jantung, pa.survei_paru, pa.survei_abdomen, pa.survei_punggung, pa.survei_ekstremitas, pa.laboratorium, pa.x_ray, "
                    + "pa.ecg, pa.ct_scan, pa.usg, pa.lainnya_penunjang, pa.diag_medis_sementara, pa.icd_10, pa.rencana_instruksi, pa.ket_rencana_instruksi, pa.telah_diberikan_informasi_edukasi, "
                    + "pa.rencana_asuhan_diharapkan, ifnull(pg1.nama,'-') pemberi_edukasi, pa.penerima_edukasi, ifnull(pg2.nama,'-') nm_dokter, date_format(pa.tgl_keluar_igd,'%d-%m-%Y') tglkeluar, "
                    + "date_format(pa.tgl_keluar_igd,'%H:%i') jamkeluar, pa.opname_diruang, pa.indikasi_msk, pa.dipulangkan_kontrol_ke, pa.dirujuk_ke, pa.alasan_dirujuk, pa.penyebab, "
                    + "pa.k_u, pa.td, pa.hr, pa.rr, pa.temp, pa.spo2, pa.gcs_pulang, pg3.nama nm_perawat, pg4.nama nm_dpjp, ifnull(pa.ket_gambar,'-') ket_gambar, pa.gcs_v, pa.gcs_m, pa.diameter_kiri "
                    + "from penilaian_awal_medis_igd pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis left join pegawai pg1 on pg1.nik=pa.nip_pemberi_edukasi "
                    + "left join pegawai pg2 on pg2.nik=pa.nip_dokter left join pegawai pg3 on pg3.nik=pa.nip_perawat "
                    + "left join pegawai pg4 on pg4.nik=pa.nip_dpjp where pa.no_rawat='" + tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString() + "'", param);
            
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
            TabRawat.setSelectedIndex(1);
            tampil();            
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih data terlebih dahulu..!!!!");
        }
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

    private void tbPenilaianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPenilaianMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {                
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if ((evt.getClickCount() == 2) && (tbPenilaian.getSelectedColumn() == 0)) {
                TabRawat.setSelectedIndex(0);
            }
        }
}//GEN-LAST:event_tbPenilaianMouseClicked

    private void tbPenilaianKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPenilaianKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {                    
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPenilaianKeyPressed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        pilihan = 1;
        akses.setform("RMPenilaianAwalMedikIGD");        
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 1) {
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'igd' and nm_gedung<>'-' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbRuangan);
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_medis_igd where no_rawat='" + TNoRw.getText() + "'") == 0) {
            TabRawat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_formWindowOpened

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        pilihan = 1;
        akses.setform("RMPenilaianAwalMedikIGD");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void ChkMerokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMerokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkMerokokActionPerformed

    private void TDiam_kananKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kananKeyPressed
        Valid.pindah(evt, cmbPupil, TDiam_kiri);
    }//GEN-LAST:event_TDiam_kananKeyPressed

    private void TSkorApgarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSkorApgarKeyPressed
        Valid.pindah(evt, cmbTonus, TFrekuensi);
    }//GEN-LAST:event_TSkorApgarKeyPressed

    private void TFrekuensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TFrekuensiKeyPressed
        Valid.pindah(evt, TSkorApgar, TRetraksi);
    }//GEN-LAST:event_TFrekuensiKeyPressed

    private void TRetraksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TRetraksiKeyPressed
        Valid.pindah(evt, TFrekuensi, TSianosis);
    }//GEN-LAST:event_TRetraksiKeyPressed

    private void TSianosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TSianosisKeyPressed
        Valid.pindah(evt, TRetraksi, TAir);
    }//GEN-LAST:event_TSianosisKeyPressed

    private void TAirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAirKeyPressed
        Valid.pindah(evt, TSianosis, TMerintih);
    }//GEN-LAST:event_TAirKeyPressed

    private void TMerintihKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMerintihKeyPressed
        Valid.pindah(evt, TAir, TAlergi);
    }//GEN-LAST:event_TMerintihKeyPressed

    private void TAlergiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlergiKeyPressed
        Valid.pindah(evt, TMerintih, TAlergi);
    }//GEN-LAST:event_TAlergiKeyPressed

    private void TAnamnesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAnamnesisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPemeriksaan.requestFocus();
        }
    }//GEN-LAST:event_TAnamnesisKeyPressed

    private void TPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPemeriksaanKeyPressed
        Valid.pindah(evt, TAnamnesis, cmbKonjung);
    }//GEN-LAST:event_TPemeriksaanKeyPressed

    private void TJantungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TJantungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TParu.requestFocus();
        }
    }//GEN-LAST:event_TJantungKeyPressed

    private void TParuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TParuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TAbdomen.requestFocus();
        }
    }//GEN-LAST:event_TParuKeyPressed

    private void TAbdomenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAbdomenKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TPunggung.requestFocus();
        }
    }//GEN-LAST:event_TAbdomenKeyPressed

    private void TPunggungKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPunggungKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TEkstremitas.requestFocus();
        }
    }//GEN-LAST:event_TPunggungKeyPressed

    private void TLabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TLabKeyPressed
        Valid.pindah(evt, Tket_gambar, Txray);
    }//GEN-LAST:event_TLabKeyPressed

    private void TxrayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxrayKeyPressed
        Valid.pindah(evt, TLab, Tecg);
    }//GEN-LAST:event_TxrayKeyPressed

    private void TecgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TecgKeyPressed
        Valid.pindah(evt, Txray, Tctscan);
    }//GEN-LAST:event_TecgKeyPressed

    private void TctscanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TctscanKeyPressed
        Valid.pindah(evt, Tecg, Tusg);
    }//GEN-LAST:event_TctscanKeyPressed

    private void TusgKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TusgKeyPressed
        Valid.pindah(evt, Tctscan, Tlainya);
    }//GEN-LAST:event_TusgKeyPressed

    private void TlainyaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainyaKeyPressed
        Valid.pindah(evt, Tusg, TDiagSementara);
    }//GEN-LAST:event_TlainyaKeyPressed

    private void TEkstremitasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TEkstremitasKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tket_gambar.requestFocus();
        }
    }//GEN-LAST:event_TEkstremitasKeyPressed

    private void TDiagSementaraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiagSementaraKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ticd.requestFocus();
        }
    }//GEN-LAST:event_TDiagSementaraKeyPressed

    private void TicdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TicdKeyPressed
        Valid.pindah(evt, TDiagSementara, cmbRencana);
    }//GEN-LAST:event_TicdKeyPressed

    private void TedukasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TedukasiKeyPressed
        Valid.pindah(evt, cmbRencana, Trencana);
    }//GEN-LAST:event_TedukasiKeyPressed

    private void TrencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrencanaKeyPressed
        Valid.pindah(evt, Tedukasi, BtnPetugas);
    }//GEN-LAST:event_TrencanaKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        pilihan = 2;
        akses.setform("RMPenilaianAwalMedikIGD");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJumlahActionPerformed
        if (TFrekuensi.getText().equals("")) {
            TFrekuensi.setText("0");
        } else {
            TFrekuensi.setText(TFrekuensi.getText());
        }

        if (TRetraksi.getText().equals("")) {
            TRetraksi.setText("0");
        } else {
            TRetraksi.setText(TRetraksi.getText());
        }

        if (TSianosis.getText().equals("")) {
            TSianosis.setText("0");
        } else {
            TSianosis.setText(TSianosis.getText());
        }

        if (TAir.getText().equals("")) {
            TAir.setText("0");
        } else {
            TAir.setText(TAir.getText());
        }

        if (TMerintih.getText().equals("")) {
            TMerintih.setText("0");
        } else {
            TMerintih.setText(TMerintih.getText());
        }
        
        hitungSkorDowne();
    }//GEN-LAST:event_BtnJumlahActionPerformed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        pilihan = 2;
        akses.setform("RMPenilaianAwalMedikIGD");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void cmbRuanganMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbRuanganMouseReleased
        AutoCompleteDecorator.decorate(cmbRuangan);
    }//GEN-LAST:event_cmbRuanganMouseReleased

    private void TindikasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TindikasiKeyPressed
        Valid.pindah(evt, cmbRuangan, Tdipulangkan);
    }//GEN-LAST:event_TindikasiKeyPressed

    private void TdipulangkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdipulangkanKeyPressed
        Valid.pindah(evt, Tindikasi, Tdirujuk);
    }//GEN-LAST:event_TdipulangkanKeyPressed

    private void TdirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdirujukKeyPressed
        Valid.pindah(evt, Tdipulangkan, TAlasanDirujuk);
    }//GEN-LAST:event_TdirujukKeyPressed

    private void TAlasanDirujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlasanDirujukKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            cmbJam.requestFocus();
        }
    }//GEN-LAST:event_TAlasanDirujukKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TpenyebabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TpenyebabKeyPressed
        Valid.pindah(evt, cmbDtk, Tku);
    }//GEN-LAST:event_TpenyebabKeyPressed

    private void TkuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkuKeyPressed
        Valid.pindah(evt, Tpenyebab, Ttd);
    }//GEN-LAST:event_TkuKeyPressed

    private void TtdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtdKeyPressed
        Valid.pindah(evt, Tku, Thr);
    }//GEN-LAST:event_TtdKeyPressed

    private void ThrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ThrKeyPressed
        Valid.pindah(evt, Ttd, Trr);
    }//GEN-LAST:event_ThrKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Thr, Ttemp);
    }//GEN-LAST:event_TrrKeyPressed

    private void TtempKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtempKeyPressed
        Valid.pindah(evt, Trr, Tspo2);
    }//GEN-LAST:event_TtempKeyPressed

    private void Tspo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tspo2KeyPressed
        Valid.pindah(evt, Ttemp, Tgcs);
    }//GEN-LAST:event_Tspo2KeyPressed

    private void TgcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsKeyPressed
        Valid.pindah(evt, Tspo2, TglKeluar);
    }//GEN-LAST:event_TgcsKeyPressed

    private void ChkObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObatActionPerformed
        if (ChkObat.isSelected() == true) {
            TObat.setEnabled(true);
            TObat.requestFocus();
        } else {
            TObat.setText("");
            TObat.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObatActionPerformed

    private void ChkTraumaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkTraumaActionPerformed
        if (ChkTrauma.isSelected() == true) {
            cmbTrauma.setEnabled(true);
        } else {
            cmbTrauma.setSelectedIndex(0);
            cmbTrauma.setEnabled(false);
        }
    }//GEN-LAST:event_ChkTraumaActionPerformed

    private void ChkResikoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkResikoActionPerformed
        if (ChkResiko.isSelected() == true) {
            cmbResiko.setEnabled(true);
        } else {
            cmbResiko.setSelectedIndex(0);
            cmbResiko.setEnabled(false);
        }
    }//GEN-LAST:event_ChkResikoActionPerformed

    private void ChkBendaAsingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkBendaAsingActionPerformed
        if (ChkBendaAsing.isSelected() == true) {
            TBendaAsing.setEnabled(true);
        } else {
            TBendaAsing.setText("");
            TBendaAsing.setEnabled(false);
        }
    }//GEN-LAST:event_ChkBendaAsingActionPerformed

    private void ChkMeninggalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkMeninggalActionPerformed
        if (ChkMeninggal.isSelected() == true) {
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
    }//GEN-LAST:event_ChkMeninggalActionPerformed

    private void ChkObstruksiPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkObstruksiPActionPerformed
        if (ChkObstruksiP.isSelected() == true) {
            cmbObstruksi.setEnabled(true);
        } else {
            cmbObstruksi.setSelectedIndex(0);
            cmbObstruksi.setEnabled(false);
        }
    }//GEN-LAST:event_ChkObstruksiPActionPerformed

    private void cmbPernapasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPernapasanActionPerformed
        if (cmbPernapasan.getSelectedIndex() != 0) {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(true);
            cmbReguler.requestFocus();
        } else {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPernapasanActionPerformed

    private void cmbRencanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRencanaActionPerformed
        if (cmbRencana.getSelectedIndex() != 0) {
            TketRencana.setText("");
            TketRencana.setEnabled(true);
            TketRencana.requestFocus();
        } else {
            TketRencana.setText("");
            TketRencana.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRencanaActionPerformed

    private void TReflekKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TReflekKeyPressed
        Valid.pindah(evt, TDiam_kiri, TMeningeal);
    }//GEN-LAST:event_TReflekKeyPressed

    private void TMeningealKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TMeningealKeyPressed
        Valid.pindah(evt, TReflek, cmbLater);
    }//GEN-LAST:event_TMeningealKeyPressed

    private void btnICDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnICDActionPerformed
        akses.setform("RMPenilaianAwalMedikIGD");
        icd10.isCek();
        icd10.emptTeks();
        icd10.ChkInput.setSelected(false);
        icd10.isForm();
        icd10.setSize(983, internalFrame1.getHeight() - 40);
        icd10.setLocationRelativeTo(internalFrame1);
        icd10.setAlwaysOnTop(false);
        icd10.setVisible(true);
    }//GEN-LAST:event_btnICDActionPerformed

    private void Tket_gambarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tket_gambarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            TLab.requestFocus();
        }
    }//GEN-LAST:event_Tket_gambarKeyPressed

    private void TgcsEKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsEKeyPressed
        Valid.pindah(evt, cmbKesSirkulasi, TgcsV);
    }//GEN-LAST:event_TgcsEKeyPressed

    private void TgcsVKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsVKeyPressed
        Valid.pindah(evt, TgcsE, TgcsM);
    }//GEN-LAST:event_TgcsVKeyPressed

    private void TgcsMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsMKeyPressed
        Valid.pindah(evt, TgcsV, cmbPupil);
    }//GEN-LAST:event_TgcsMKeyPressed

    private void TDiam_kiriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TDiam_kiriKeyPressed
        Valid.pindah(evt, TDiam_kanan, TReflek);
    }//GEN-LAST:event_TDiam_kiriKeyPressed

    private void TketRencanaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRencanaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tedukasi.requestFocus();
        }
    }//GEN-LAST:event_TketRencanaKeyPressed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMPenilaianAwalMedikIGD");
            DlgCatatanResep form = new DlgCatatanResep(null, false);
            form.isCek();
            form.setData(TNoRw.getText(), Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnResepActionPerformed

    private void BtnResepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnResepKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnResepKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalMedikIGD dialog = new RMPenilaianAwalMedikIGD(new javax.swing.JFrame(), true);
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
    private widget.Button BtnDokter;
    private widget.Button BtnDpjp;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnJumlah;
    private widget.Button BtnKeluar;
    private widget.Button BtnPerawat;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkAbrasi;
    public widget.CekBox ChkBalut;
    public widget.CekBox ChkBendaAsing;
    public widget.CekBox ChkBurn;
    public widget.CekBox ChkCervical;
    public widget.CekBox ChkContusio;
    public widget.CekBox ChkDM;
    public widget.CekBox ChkDeformitas;
    public widget.CekBox ChkDefribilasi;
    public widget.CekBox ChkDekompresi;
    public widget.CekBox ChkEkskoriasi;
    public widget.CekBox ChkGangNafas;
    public widget.CekBox ChkHipertensi;
    public widget.CekBox ChkInfus;
    public widget.CekBox ChkIntubasi;
    public widget.CekBox ChkJantung;
    public widget.CekBox ChkKateter;
    public widget.CekBox ChkLaserasi;
    public widget.CekBox ChkMeninggal;
    public widget.CekBox ChkMerokok;
    public widget.CekBox ChkNGT;
    public widget.CekBox ChkObat;
    public widget.CekBox ChkObstruksiP;
    public widget.CekBox ChkObstruksiT;
    public widget.CekBox ChkPaten;
    public widget.CekBox ChkPenetrasi;
    public widget.CekBox ChkRJP;
    public widget.CekBox ChkResiko;
    public widget.CekBox ChkSwelling;
    public widget.CekBox ChkTdkTampk;
    public widget.CekBox ChkTenderness;
    public widget.CekBox ChkTidak;
    public widget.CekBox ChkTrauma;
    public widget.CekBox ChkVTP;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.TextBox Jk;
    private widget.TextBox KdPerawat;
    private widget.Label LCount;
    private widget.TextBox NmPerawat;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.TextArea TAbdomen;
    private widget.TextBox TAir;
    private widget.TextArea TAlasanDirujuk;
    private widget.TextBox TAlergi;
    private widget.TextArea TAnamnesis;
    private widget.TextBox TBendaAsing;
    private widget.TextBox TCari;
    private widget.TextArea TDiagSementara;
    private widget.TextBox TDiam_kanan;
    private widget.TextBox TDiam_kiri;
    private widget.TextArea TEkstremitas;
    private widget.TextBox TFrekuensi;
    private widget.TextArea TJantung;
    private widget.TextBox TJlhSkor;
    private widget.TextBox TLab;
    private widget.TextBox TLainKebiasaan;
    private widget.TextBox TLainPenyakit;
    private widget.TextBox TMeningeal;
    private widget.TextBox TMerintih;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TObat;
    private widget.TextArea TParu;
    private widget.TextBox TPasien;
    private widget.TextBox TPemeriksaan;
    private widget.TextArea TPunggung;
    private widget.TextBox TReflek;
    private widget.TextBox TRetraksi;
    private widget.TextBox TSianosis;
    private widget.TextBox TSkorApgar;
    private javax.swing.JTabbedPane TabRawat;
    private widget.TextBox Tctscan;
    private widget.TextBox Tdipulangkan;
    private widget.TextBox Tdirujuk;
    private widget.TextBox Tecg;
    private widget.TextBox Tedukasi;
    private widget.TextBox Tgcs;
    private widget.TextBox TgcsE;
    private widget.TextBox TgcsM;
    private widget.TextBox TgcsV;
    private widget.Tanggal TglAsesmen;
    private widget.Tanggal TglKeluar;
    private widget.TextBox TglLahir;
    private widget.TextBox Thr;
    private widget.TextBox Ticd;
    private widget.TextBox Tindikasi;
    private widget.TextArea TketRencana;
    private widget.TextArea Tket_gambar;
    private widget.TextBox Tku;
    private widget.TextBox Tlainya;
    private widget.TextBox Tpenyebab;
    private widget.TextBox Trencana;
    private widget.TextBox Trr;
    private widget.TextBox Tspo2;
    private widget.TextBox Ttd;
    private widget.TextBox Ttemp;
    private widget.TextBox Tusg;
    private widget.TextBox Txray;
    private widget.Button btnICD;
    private widget.ComboBox cmbAkral;
    private widget.ComboBox cmbBibir;
    private widget.ComboBox cmbCRT;
    private widget.ComboBox cmbCairan;
    private widget.ComboBox cmbCkpBulan;
    private widget.ComboBox cmbDeviasi;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbGerakanDada;
    private widget.ComboBox cmbJVP;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbKesJalanNafas;
    private widget.ComboBox cmbKesPernapasan;
    private widget.ComboBox cmbKesSirkulasi;
    private widget.ComboBox cmbKonjung;
    private widget.ComboBox cmbKulit;
    private widget.ComboBox cmbLNN;
    private widget.ComboBox cmbLater;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbMukosa;
    private widget.ComboBox cmbNadi;
    private widget.ComboBox cmbNapasNangis;
    private widget.ComboBox cmbObstruksi;
    private widget.ComboBox cmbPernapasan;
    private widget.ComboBox cmbPupil;
    private widget.ComboBox cmbReguler;
    private widget.ComboBox cmbRencana;
    private widget.ComboBox cmbResiko;
    private widget.ComboBox cmbRuangan;
    private widget.ComboBox cmbSklera;
    private widget.ComboBox cmbTipePernapasan;
    private widget.ComboBox cmbTiroid;
    private widget.ComboBox cmbTonus;
    private widget.ComboBox cmbTrauma;
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
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
    private widget.Label jLabel166;
    private widget.Label jLabel167;
    private widget.Label jLabel168;
    private widget.Label jLabel169;
    private widget.Label jLabel170;
    private widget.Label jLabel171;
    private widget.Label jLabel172;
    private widget.Label jLabel173;
    private widget.Label jLabel174;
    private widget.Label jLabel175;
    private widget.Label jLabel176;
    private widget.Label jLabel177;
    private widget.Label jLabel178;
    private widget.Label jLabel179;
    private widget.Label jLabel180;
    private widget.Label jLabel181;
    private widget.Label jLabel182;
    private widget.Label jLabel183;
    private widget.Label jLabel184;
    private widget.Label jLabel185;
    private widget.Label jLabel186;
    private widget.Label jLabel187;
    private widget.Label jLabel188;
    private widget.Label jLabel189;
    private widget.Label jLabel19;
    private widget.Label jLabel190;
    private widget.Label jLabel191;
    private widget.Label jLabel192;
    private widget.Label jLabel193;
    private widget.Label jLabel194;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel98;
    private widget.Label jLabel99;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private widget.TextBox kddokter;
    private widget.TextBox kddpjp;
    private widget.TextBox kdpetugas;
    private widget.Label label11;
    private widget.Label label14;
    private widget.Label label15;
    private widget.TextBox nmdokter;
    private widget.TextBox nmdpjp;
    private widget.TextBox nmpenerima;
    private widget.TextBox nmpetugas;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane10;
    private widget.ScrollPane scrollPane11;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.ScrollPane scrollPane9;
    private widget.Table tbPenilaian;
    // End of variables declaration//GEN-END:variables

     private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jk, "
                    + "date_format(p.tgl_lahir,'%d-%m-%Y') tglLhr, pm.* FROM reg_periksa rp "
                    + "inner join penilaian_awal_medis_igd pm on pm.no_rawat=rp.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "left join pegawai pg1 on pg1.nik=pm.nip_perawat "
                    + "left join pegawai pg2 on pg2.nik=pm.nip_dpjp "
                    + "left join pegawai pg3 on pg3.nik=pm.nip_pemberi_edukasi "
                    + "left join pegawai pg4 on pg4.nik=pm.nip_dokter WHERE "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND rp.no_rawat LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND p.no_rkm_medis LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND p.nm_pasien LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_perawat LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_dpjp LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pm.nip_pemberi_edukasi LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg1.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg2.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg3.nama LIKE ? OR "
                    + "date(pm.tanggal) BETWEEN ? AND ? AND pg4.nama LIKE ? ORDER BY pm.tanggal");
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
                ps.setString(25, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(26, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(27, "%" + TCari.getText() + "%");                
                ps.setString(28, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(29, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(30, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jk"),
                        rs.getString("tglLhr"),
                        rs.getString("tanggal"),
                        rs.getString("cervival"),
                        rs.getString("rjp"),
                        rs.getString("defribilasi"),
                        rs.getString("intubasi"),
                        rs.getString("vtp"),
                        rs.getString("dekompresi"),
                        rs.getString("balut"),
                        rs.getString("kateter"),
                        rs.getString("ngt"),
                        rs.getString("infus"),
                        rs.getString("obat"),
                        rs.getString("ket_obat"),
                        rs.getString("tidak_ada"),
                        rs.getString("paten"),
                        rs.getString("obstruksi_partial"),
                        rs.getString("data_obstruksi_partial"),
                        rs.getString("obstruksi_total"),
                        rs.getString("trauma_jalan_nafas"),
                        rs.getString("trauma"),
                        rs.getString("resiko_aspirasi"),
                        rs.getString("aspirasi"),
                        rs.getString("benda_asing"),
                        rs.getString("ket_benda_asing"),
                        rs.getString("kesimpulan_jalan_nafas"),
                        rs.getString("pernafasan"),
                        rs.getString("data_pernafasan"),
                        rs.getString("gerakan_dada"),
                        rs.getString("tipe_pernafasan"),
                        rs.getString("kesimpulan_pernafasan"),
                        rs.getString("nadi"),
                        rs.getString("kulit_mukosa"),
                        rs.getString("akral"),
                        rs.getString("crt"),
                        rs.getString("kesimpulan_sirkulasi"),
                        rs.getString("cukup_bulan"),
                        rs.getString("cairan_amnion"),
                        rs.getString("pernafasan_menangis"),
                        rs.getString("tonus"),
                        rs.getString("skor_apgar"),
                        rs.getString("frekuensi_nafas"),
                        rs.getString("retraksi"),
                        rs.getString("sianosis"),
                        rs.getString("air_entry"),
                        rs.getString("merintih"),
                        rs.getString("gcs_e"),
                        rs.getString("pupil"),
                        rs.getString("diameter_kanan"),
                        rs.getString("reflek_cahaya"),
                        rs.getString("meningeal_sign"),
                        rs.getString("literasi"),
                        rs.getString("deformitas"),
                        rs.getString("contusio"),
                        rs.getString("penetrasi"),
                        rs.getString("tenderness"),
                        rs.getString("swelling"),
                        rs.getString("ekskoriasi"),
                        rs.getString("abrasi"),
                        rs.getString("burn"),
                        rs.getString("laserasi"),
                        rs.getString("tidak_tampak_jelas"),
                        rs.getString("Alergi"),
                        rs.getString("hipertensi"),
                        rs.getString("diabetes"),
                        rs.getString("jantung"),
                        rs.getString("riwayat_penyakit_lain"),
                        rs.getString("merokok"),
                        rs.getString("kebiasaan_lain"),
                        rs.getString("anamnesis"),
                        rs.getString("pemeriksaan_fisik"),
                        rs.getString("konjungtiva"),
                        rs.getString("sklera"),
                        rs.getString("bibir_lidah"),
                        rs.getString("mukosa"),
                        rs.getString("deviasi"),
                        rs.getString("jvp"),
                        rs.getString("lnn"),
                        rs.getString("tiroid"),                        
                        rs.getString("survei_jantung"),
                        rs.getString("survei_paru"),
                        rs.getString("survei_abdomen"),
                        rs.getString("survei_punggung"),
                        rs.getString("survei_ekstremitas"),
                        rs.getString("laboratorium"),
                        rs.getString("x_ray"),
                        rs.getString("ecg"),
                        rs.getString("ct_scan"),
                        rs.getString("usg"),
                        rs.getString("lainnya_penunjang"),
                        rs.getString("diag_medis_sementara"),
                        rs.getString("icd_10"),
                        rs.getString("rencana_instruksi"),
                        rs.getString("ket_rencana_instruksi"),
                        rs.getString("telah_diberikan_informasi_edukasi"),
                        rs.getString("rencana_asuhan_diharapkan"),
                        rs.getString("nip_pemberi_edukasi"),
                        rs.getString("penerima_edukasi"),
                        rs.getString("nip_dokter"),
                        rs.getString("tgl_keluar_igd"),
                        rs.getString("opname_diruang"),
                        rs.getString("indikasi_msk"),
                        rs.getString("dipulangkan_kontrol_ke"),
                        rs.getString("dirujuk_ke"),
                        rs.getString("alasan_dirujuk"),
                        rs.getString("meninggal"),
                        rs.getString("jam_meninggal"),
                        rs.getString("penyebab"),
                        rs.getString("k_u"),
                        rs.getString("td"),
                        rs.getString("hr"),
                        rs.getString("rr"),
                        rs.getString("temp"),
                        rs.getString("spo2"),
                        rs.getString("gcs_pulang"),
                        rs.getString("nip_perawat"),
                        rs.getString("nip_dpjp"),
                        rs.getString("ket_gambar"),
                        rs.getString("gcs_v"),
                        rs.getString("gcs_m"),
                        rs.getString("diameter_kiri"),
                        rs.getString("gangguan_jalan_nafas")
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
        KdPerawat.setText("-");
        NmPerawat.setText("-");        
        ChkCervical.setSelected(false);
        ChkRJP.setSelected(false);
        ChkDefribilasi.setSelected(false);
        ChkIntubasi.setSelected(false);
        ChkVTP.setSelected(false);
        ChkDekompresi.setSelected(false);
        ChkBalut.setSelected(false);
        ChkKateter.setSelected(false);
        ChkNGT.setSelected(false);
        ChkInfus.setSelected(false);
        ChkObat.setSelected(false);
        TObat.setText("");
        TObat.setEnabled(false);
        ChkTidak.setSelected(false);
        ChkGangNafas.setSelected(false);
        ChkPaten.setSelected(false);
        ChkObstruksiP.setSelected(false);
        cmbObstruksi.setSelectedIndex(0);
        cmbObstruksi.setEnabled(false);
        ChkObstruksiT.setSelected(false);
        ChkTrauma.setSelected(false);
        cmbTrauma.setSelectedIndex(0);
        cmbTrauma.setEnabled(false);
        ChkResiko.setSelected(false);
        cmbResiko.setSelectedIndex(0);
        cmbResiko.setEnabled(false);
        ChkBendaAsing.setSelected(false);
        TBendaAsing.setText("");
        TBendaAsing.setEnabled(false);
        cmbKesJalanNafas.setSelectedIndex(0);
        cmbPernapasan.setSelectedIndex(0);
        cmbReguler.setSelectedIndex(0);
        cmbReguler.setEnabled(false);
        cmbGerakanDada.setSelectedIndex(0);
        cmbTipePernapasan.setSelectedIndex(0);
        cmbKesPernapasan.setSelectedIndex(0);
        cmbNadi.setSelectedIndex(0);
        cmbKulit.setSelectedIndex(0);
        cmbAkral.setSelectedIndex(0);
        cmbCRT.setSelectedIndex(0);
        cmbKesSirkulasi.setSelectedIndex(0);
        cmbCkpBulan.setSelectedIndex(0);
        cmbCairan.setSelectedIndex(0);
        cmbNapasNangis.setSelectedIndex(0);
        cmbTonus.setSelectedIndex(0);
        TSkorApgar.setText("0");
        TgcsE.setText("");
        TgcsV.setText("");
        TgcsM.setText("");        
        cmbPupil.setSelectedIndex(0);
        TDiam_kanan.setText("");
        TDiam_kiri.setText("");
        TReflek.setText("");
        TMeningeal.setText("");
        cmbLater.setSelectedIndex(0);
        
        TFrekuensi.setText("0");
        TRetraksi.setText("0");
        TSianosis.setText("0");
        TAir.setText("0");
        TMerintih.setText("0");
        TAlergi.setText("");
        ChkHipertensi.setSelected(false);
        ChkDM.setSelected(false);
        ChkJantung.setSelected(false);
        TLainPenyakit.setText("");
        ChkMerokok.setSelected(false);
        TLainKebiasaan.setText("");
        TAnamnesis.setText("");
        TPemeriksaan.setText("");
        cmbKonjung.setSelectedIndex(0);
        cmbSklera.setSelectedIndex(0);
        cmbBibir.setSelectedIndex(0);
        cmbMukosa.setSelectedIndex(0);
        cmbDeviasi.setSelectedIndex(0);
        cmbJVP.setSelectedIndex(0);
        cmbLNN.setSelectedIndex(0);
        cmbTiroid.setSelectedIndex(0);
        TJantung.setText("");
        TParu.setText("");
        TAbdomen.setText("");
        TPunggung.setText("");
        TEkstremitas.setText("");
        Tket_gambar.setText("");
        TLab.setText("");
        Txray.setText("");
        Tecg.setText("");
        Tctscan.setText("");
        Tusg.setText("");
        Tlainya.setText("");
        TDiagSementara.setText("");
        Ticd.setText("");
        cmbRencana.setSelectedIndex(0);
        TketRencana.setText("");
        TketRencana.setEnabled(false);        
        Tedukasi.setText("");
        Trencana.setText("");
        kdpetugas.setText("");
        nmpetugas.setText("");
        nmpenerima.setText("");
        TglKeluar.setDate(new Date());
        cmbRuangan.setSelectedIndex(0);
        Tindikasi.setText("");
        Tdipulangkan.setText(""); 
        Tdirujuk.setText("");
        TAlasanDirujuk.setText("");
        Tpenyebab.setText("");
        Tku.setText("");
        Thr.setText("");
        Tspo2.setText("");
        Tgcs.setText("");
        
        ChkDeformitas.setSelected(false);
        ChkContusio.setSelected(false);
        ChkPenetrasi.setSelected(false);
        ChkTenderness.setSelected(false);
        ChkSwelling.setSelected(false);
        ChkEkskoriasi.setSelected(false);
        ChkAbrasi.setSelected(false);
        ChkBurn.setSelected(false);
        ChkLaserasi.setSelected(false);
        ChkTdkTampk.setSelected(false);
        hitungSkorDowne();
    }

    private void getData() {
        cervical = "";
        rjp = "";
        defribrilasi = "";
        intubasi = "";
        vtp = "";
        dekompresi = "";
        balut = "";
        kateter = "";
        ngt = "";
        infus = "";
        obat = "";
        tidakada = "";
        ganggnafas = "";
        paten = "";
        obsP = "";
        
        deformitas = "";
        contusio = "";
        penetrasi = "";
        tenderness = "";
        swelling = "";
        ekskoriasi = "";
        abrasi = "";
        burn = "";
        laserasi = "";
        tdk_tmpk_jls = "";
        
        obsT = "";
        traumaJlnNfs = "";
        resikoAspirasi = "";
        bendaAsing = "";
        hipertensi = "";
        dm = "";
        jantung = "";
        merokok = "";        
        meninggal = "";
        
        if (tbPenilaian.getSelectedRow() != -1) {
            TNoRw.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 1).toString());
            TPasien.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 2).toString());
            Jk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 3).toString());
            TglLahir.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 4).toString());
            Valid.SetTgl2(TglAsesmen, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 5).toString());
            
            cervical = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 6).toString();
            rjp = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 7).toString();
            defribrilasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 8).toString();
            intubasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 9).toString();
            vtp = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 10).toString();
            dekompresi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 11).toString();
            balut = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 12).toString();
            kateter = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 13).toString();
            ngt = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 14).toString();
            infus = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 15).toString();
            obat = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 16).toString();
            
            TObat.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 17).toString());
            
            tidakada = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 18).toString();
            paten = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 19).toString();
            obsP = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 20).toString();
            
            cmbObstruksi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 21).toString());
            
            obsT = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 22).toString();
            traumaJlnNfs = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 23).toString();
            
            cmbTrauma.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 24).toString());
            
            resikoAspirasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 25).toString();
            
            cmbResiko.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 26).toString());
            
            bendaAsing = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 27).toString();
            
            TBendaAsing.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 28).toString());
            cmbKesJalanNafas.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 29).toString());
            cmbPernapasan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 30).toString());
            cmbReguler.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 31).toString());
            cmbGerakanDada.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 32).toString());
            cmbTipePernapasan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 33).toString());
            cmbKesPernapasan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 34).toString());
            cmbNadi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 35).toString());
            cmbKulit.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 36).toString());
            cmbAkral.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 37).toString());
            cmbCRT.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 38).toString());
            cmbKesSirkulasi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 39).toString());
            cmbCkpBulan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 40).toString());
            cmbCairan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 41).toString());
            cmbNapasNangis.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 42).toString());
            cmbTonus.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 43).toString());
            TSkorApgar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 44).toString());
            TFrekuensi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 45).toString());
            TRetraksi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 46).toString());
            TSianosis.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 47).toString());
            TAir.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 48).toString());
            TMerintih.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 49).toString());
            TgcsE.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 50).toString());            
            cmbPupil.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 51).toString());
            TDiam_kanan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 52).toString());
            TReflek.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 53).toString());
            TMeningeal.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 54).toString());
            cmbLater.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 55).toString());
            
            deformitas = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 56).toString();
            contusio = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 57).toString();
            penetrasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 58).toString();
            tenderness = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 59).toString();
            swelling = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 60).toString();
            ekskoriasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 61).toString();
            abrasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 62).toString();
            burn = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 63).toString();
            laserasi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 64).toString();
            tdk_tmpk_jls = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 65).toString();            
            
            TAlergi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 66).toString());
            
            hipertensi = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 67).toString();
            dm = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 68).toString();
            jantung = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 69).toString();
            
            TLainPenyakit.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 70).toString());
            
            merokok = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 71).toString();
            
            TLainKebiasaan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 72).toString());
            TAnamnesis.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 73).toString());
            TPemeriksaan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 74).toString());
            cmbKonjung.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 75).toString());
            cmbSklera.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 76).toString());
            cmbBibir.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 77).toString());
            cmbMukosa.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 78).toString());
            cmbDeviasi.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 79).toString());
            cmbJVP.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 80).toString());
            cmbLNN.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 81).toString());
            cmbTiroid.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 82).toString());
            TJantung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 83).toString());
            TParu.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 84).toString());
            TAbdomen.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 85).toString());
            TPunggung.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 86).toString());
            TEkstremitas.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 87).toString());
            TLab.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 88).toString());
            Txray.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 89).toString());
            Tecg.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 90).toString());
            Tctscan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 91).toString());
            Tusg.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 92).toString());
            Tlainya.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 93).toString());
            TDiagSementara.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 94).toString());
            Ticd.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 95).toString());
            cmbRencana.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 96).toString());
            TketRencana.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 97).toString());
            Tedukasi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 98).toString());
            Trencana.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 99).toString());
            kdpetugas.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 100).toString());
            nmpetugas.setText(Sequel.cariIsi("select ifnull(nama,'') from pegawai where nik='" + kdpetugas.getText() + "'"));
            nmpenerima.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 101).toString());
            kddokter.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 102).toString());
            nmdokter.setText(Sequel.cariIsi("select ifnull(nama,'') from pegawai where nik='" + kddokter.getText() + "'"));
            Valid.SetTgl2(TglKeluar, tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 103).toString());
            cmbRuangan.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 104).toString());
            Tindikasi.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 105).toString());
            Tdipulangkan.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 106).toString());
            Tdirujuk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 107).toString());
            TAlasanDirujuk.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 108).toString());
            
            meninggal = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 109).toString();
            
            cmbJam.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 110).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 110).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 110).toString().substring(6, 8));
            Tpenyebab.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 111).toString());
            Tku.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 112).toString());
            Ttd.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 113).toString());
            Thr.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 114).toString());
            Trr.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 115).toString());
            Ttemp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 116).toString());
            Tspo2.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 117).toString());
            Tgcs.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 118).toString());
            KdPerawat.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 119).toString());
            NmPerawat.setText(Sequel.cariIsi("select ifnull(nama,'') from pegawai where nik='" + KdPerawat.getText() + "'"));
            kddpjp.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 120).toString());
            nmdpjp.setText(Sequel.cariIsi("select ifnull(nama,'') from pegawai where nik='" + kddpjp.getText() + "'"));
            Tket_gambar.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 121).toString());
            TgcsV.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 122).toString());
            TgcsM.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 123).toString());
            TDiam_kiri.setText(tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(),124).toString());
            ganggnafas = tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(),125).toString();
            hitungSkorDowne();
            dataCek();
        }
    }

    private void isRawat() {
        try {
            ps = koneksi.prepareStatement("select rp.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-Laki','Perempuan') jk, "
                    + "p.tgl_lahir, p.agama, bp.nama_bahasa, rp.tgl_registrasi, p.stts_nikah, p.pekerjaan, "
                    + "p.pnd, pj.png_jawab, ifnull(r.rujuk_ke,'') rujuk_ke, ifnull(r.keterangan,'') ket_rujuk, "
                    + "ifnull(pm.jam,'00:00:00') jam_mati, IFNULL(pa.td, '') td, "
                    + "IFNULL(pa.nafas, '') nafas, IFNULL(pa.suhu, '') suhu FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN bahasa_pasien bp ON bp.id = p.bahasa_pasien "
                    + "INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj left join rujuk r on r.no_rawat=rp.no_rawat "
                    + "left join pasien_mati pm on pm.no_rkm_medis=rp.no_rkm_medis LEFT JOIN penilaian_awal_keperawatan_igdrz pa ON pa.no_rawat = rp.no_rawat "
                    + "WHERE rp.no_rawat = ?");
            try {
                ps.setString(1, TNoRw.getText());
                rs = ps.executeQuery();
                if (rs.next()) {
                    TNoRM.setText(rs.getString("no_rkm_medis"));
                    TPasien.setText(rs.getString("nm_pasien"));
                    DTPCari1.setDate(rs.getDate("tgl_registrasi"));
                    Jk.setText(rs.getString("jk"));
                    TglLahir.setText(rs.getString("tgl_lahir")); 
                    Tdirujuk.setText(rs.getString("rujuk_ke"));
                    TAlasanDirujuk.setText(rs.getString("ket_rujuk"));
                    
                    Ttd.setText(rs.getString("td"));
                    Trr.setText(rs.getString("nafas"));
                    Ttemp.setText(rs.getString("suhu"));
                    
                    if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") == 0) {
                        ChkMeninggal.setSelected(false);
                        cmbJam.setSelectedIndex(0);
                        cmbMnt.setSelectedIndex(0);
                        cmbDtk.setSelectedIndex(0);
                        
                        cmbJam.setEnabled(false);
                        cmbMnt.setEnabled(false);
                        cmbDtk.setEnabled(false);
                    } else if (Sequel.cariInteger("select count(-1) from pasien_mati where no_rkm_medis='" + rs.getString("no_rkm_medis") + "'") > 0) {
                        ChkMeninggal.setSelected(true);
                        cmbJam.setSelectedItem(rs.getString("jam_mati").toString().substring(0, 2));
                        cmbMnt.setSelectedItem(rs.getString("jam_mati").toString().substring(3, 5));
                        cmbDtk.setSelectedItem(rs.getString("jam_mati").toString().substring(6, 8));
                        
                        cmbJam.setEnabled(true);
                        cmbMnt.setEnabled(true);
                        cmbDtk.setEnabled(true);
                    }                    
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
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        tampil();        
    }    
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getdata_triase_igd());
        BtnHapus.setEnabled(akses.getdata_triase_igd());
        BtnEdit.setEnabled(akses.getdata_triase_igd());
        BtnPrint.setEnabled(akses.getdata_triase_igd());
        BtnResep.setEnabled(akses.getresep_dokter());
        
        if (akses.getjml2() >= 1) {            
            BtnDpjp.setEnabled(false);            
            kddpjp.setText(akses.getkode());
            kddokter.setText(akses.getkode());
            Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", nmdpjp, kddpjp.getText());
            nmdokter.setText(nmdpjp.getText());
            if (nmdpjp.getText().equals("")) {
                kddpjp.setText("");
                kddokter.setText("");
                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }        
    }

    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void ganti() {
        cekData();
        BtnJumlahActionPerformed(null);
        if (Sequel.mengedittf("penilaian_awal_medis_igd", "no_rawat=?", "no_rawat=?, tanggal=?, cervival=?, rjp=?, defribilasi=?, intubasi=?, vtp=?, dekompresi=?,"
                + "balut=?, kateter=?, ngt=?, infus=?, obat=?, ket_obat=?, tidak_ada=?, paten=?, obstruksi_partial=?, data_obstruksi_partial=?,"
                + "obstruksi_total=?, trauma_jalan_nafas=?, trauma=?, resiko_aspirasi=?, aspirasi=?, benda_asing=?, ket_benda_asing=?, kesimpulan_jalan_nafas=?, "
                + "pernafasan=?, data_pernafasan=?, gerakan_dada=?, tipe_pernafasan=?, kesimpulan_pernafasan=?, nadi=?, kulit_mukosa=?, akral=?, crt=?, "
                + "kesimpulan_sirkulasi=?, cukup_bulan=?, cairan_amnion=?, pernafasan_menangis=?, tonus=?, skor_apgar=?, frekuensi_nafas=?, retraksi=?, sianosis=?, "
                + "air_entry=?, merintih=?, gcs_e=?, pupil=?, diameter_kanan=?, reflek_cahaya=?, meningeal_sign=?, literasi=?, deformitas=?, contusio=?, penetrasi=?, "
                + "tenderness=?, swelling=?, ekskoriasi=?, abrasi=?, burn=?, laserasi=?, tidak_tampak_jelas=?, Alergi=?, hipertensi=?, diabetes=?, jantung=?, "
                + "riwayat_penyakit_lain=?, merokok=?, kebiasaan_lain=?, anamnesis=?, pemeriksaan_fisik=?, konjungtiva=?, sklera=?, bibir_lidah=?, mukosa=?, "
                + "deviasi=?, jvp=?, lnn=?, tiroid=?, survei_jantung=?, survei_paru=?, survei_abdomen=?, survei_punggung=?, survei_ekstremitas=?, "
                + "laboratorium=?, x_ray=?, ecg=?, ct_scan=?, usg=?, lainnya_penunjang=?, diag_medis_sementara=?, icd_10=?, rencana_instruksi=?, ket_rencana_instruksi=?, "
                + "telah_diberikan_informasi_edukasi=?, rencana_asuhan_diharapkan=?, nip_pemberi_edukasi=?, penerima_edukasi=?, nip_dokter=?, tgl_keluar_igd=?, "
                + "opname_diruang=?, indikasi_msk=?, dipulangkan_kontrol_ke=?, dirujuk_ke=?, alasan_dirujuk=?, meninggal=?, jam_meninggal=?, penyebab=?, k_u=?, "
                + "td=?, hr=?, rr=?, temp=?, spo2=?, gcs_pulang=?, nip_perawat=?, nip_dpjp=?, ket_gambar=?, gcs_v=?, gcs_m=?, diameter_kiri=?, gangguan_jalan_nafas=?", 123, new String[]{
                    TNoRw.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), cervical, rjp, defribrilasi, intubasi,
                    vtp, dekompresi, balut, kateter, ngt, infus, obat, TObat.getText(), tidakada, paten, obsP, cmbObstruksi.getSelectedItem().toString(), obsT, traumaJlnNfs, cmbTrauma.getSelectedItem().toString(), 
                    resikoAspirasi, cmbResiko.getSelectedItem().toString(), bendaAsing, TBendaAsing.getText(), cmbKesJalanNafas.getSelectedItem().toString(), cmbPernapasan.getSelectedItem().toString(),
                    cmbReguler.getSelectedItem().toString(), cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(), cmbKesPernapasan.getSelectedItem().toString(), 
                    cmbNadi.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), 
                    cmbKesSirkulasi.getSelectedItem().toString(), cmbCkpBulan.getSelectedItem().toString(), cmbCairan.getSelectedItem().toString(), cmbNapasNangis.getSelectedItem().toString(), 
                    cmbTonus.getSelectedItem().toString(), TSkorApgar.getText(), TFrekuensi.getText(), TRetraksi.getText(), TSianosis.getText(), TAir.getText(), TMerintih.getText(), 
                    TgcsE.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TReflek.getText(), TMeningeal.getText(), cmbLater.getSelectedItem().toString(), 
                    deformitas, contusio, penetrasi, tenderness, swelling, ekskoriasi, abrasi, burn, laserasi, tdk_tmpk_jls, TAlergi.getText(), hipertensi, dm, jantung, TLainPenyakit.getText(), 
                    merokok, TLainKebiasaan.getText(), TAnamnesis.getText(), TPemeriksaan.getText(), cmbKonjung.getSelectedItem().toString(), cmbSklera.getSelectedItem().toString(), 
                    cmbBibir.getSelectedItem().toString(), cmbMukosa.getSelectedItem().toString(), cmbDeviasi.getSelectedItem().toString(), cmbJVP.getSelectedItem().toString(), 
                    cmbLNN.getSelectedItem().toString(), cmbTiroid.getSelectedItem().toString(), TJantung.getText(), TParu.getText(), TAbdomen.getText(), TPunggung.getText(), 
                    TEkstremitas.getText(), TLab.getText(), Txray.getText(), Tecg.getText(), Tctscan.getText(), Tusg.getText(), Tlainya.getText(), TDiagSementara.getText(), Ticd.getText(), 
                    cmbRencana.getSelectedItem().toString(), TketRencana.getText(), Tedukasi.getText(), Trencana.getText(), kdpetugas.getText(), nmpenerima.getText(), kddokter.getText(), 
                    Valid.SetTgl(TglKeluar.getSelectedItem() + "") + " " + TglKeluar.getSelectedItem().toString().substring(11, 19), cmbRuangan.getSelectedItem().toString(), Tindikasi.getText(), 
                    Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), meninggal, cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                    Tpenyebab.getText(), Tku.getText(), Ttd.getText(), Thr.getText(), Trr.getText(), Ttemp.getText(), Tspo2.getText(), Tgcs.getText(), KdPerawat.getText(), kddpjp.getText(),
                    Tket_gambar.getText(), TgcsV.getText(), TgcsM.getText(), TDiam_kiri.getText(), ganggnafas,
                    tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString()
                }) == true) {
            TCari.setText(TNoRw.getText());
            tampil();
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);
            TabRawat.setSelectedIndex(1);
        }
    }

    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_medis_igd where no_rawat=?", 1, new String[]{
                tbPenilaian.getValueAt(tbPenilaian.getSelectedRow(), 0).toString()
            }) == true) {                             
                tampil();
                Ttd.setText("");
                Trr.setText("");
                Ttemp.setText("");
                emptTeks();
                ChkMeninggal.setSelected(false);
                cmbJam.setSelectedIndex(0);
                cmbMnt.setSelectedIndex(0);
                cmbDtk.setSelectedIndex(0);

                cmbJam.setEnabled(false);
                cmbMnt.setEnabled(false);
                cmbDtk.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void simpan() {
        cekData();
        BtnJumlahActionPerformed(null);
        if (Sequel.menyimpantf("penilaian_awal_medis_igd", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 122, new String[]{
            TNoRw.getText(), Valid.SetTgl(TglAsesmen.getSelectedItem() + "") + " " + TglAsesmen.getSelectedItem().toString().substring(11, 19), cervical, rjp, defribrilasi, intubasi, vtp, dekompresi, balut, kateter, ngt, infus, obat,
            TObat.getText(), tidakada, paten, obsP, cmbObstruksi.getSelectedItem().toString(), obsT, traumaJlnNfs, cmbTrauma.getSelectedItem().toString(), resikoAspirasi, cmbResiko.getSelectedItem().toString(), bendaAsing, TBendaAsing.getText(),
            cmbKesJalanNafas.getSelectedItem().toString(), cmbPernapasan.getSelectedItem().toString(), cmbReguler.getSelectedItem().toString(), cmbGerakanDada.getSelectedItem().toString(), cmbTipePernapasan.getSelectedItem().toString(), 
            cmbKesPernapasan.getSelectedItem().toString(), cmbNadi.getSelectedItem().toString(), cmbKulit.getSelectedItem().toString(), cmbAkral.getSelectedItem().toString(), cmbCRT.getSelectedItem().toString(), cmbKesSirkulasi.getSelectedItem().toString(), 
            cmbCkpBulan.getSelectedItem().toString(), cmbCairan.getSelectedItem().toString(), cmbNapasNangis.getSelectedItem().toString(), cmbTonus.getSelectedItem().toString(), TSkorApgar.getText(), TFrekuensi.getText(), TRetraksi.getText(), 
            TSianosis.getText(), TAir.getText(), TMerintih.getText(), TgcsE.getText(), cmbPupil.getSelectedItem().toString(), TDiam_kanan.getText(), TReflek.getText(), TMeningeal.getText(), cmbLater.getSelectedItem().toString(), 
            deformitas, contusio, penetrasi, tenderness, swelling, ekskoriasi, abrasi, burn, laserasi, tdk_tmpk_jls, TAlergi.getText(), hipertensi, dm, jantung, TLainPenyakit.getText(), merokok, TLainKebiasaan.getText(), TAnamnesis.getText(), 
            TPemeriksaan.getText(), cmbKonjung.getSelectedItem().toString(), cmbSklera.getSelectedItem().toString(), cmbBibir.getSelectedItem().toString(), cmbMukosa.getSelectedItem().toString(), cmbDeviasi.getSelectedItem().toString(), 
            cmbJVP.getSelectedItem().toString(), cmbLNN.getSelectedItem().toString(), cmbTiroid.getSelectedItem().toString(), TJantung.getText(), TParu.getText(), TAbdomen.getText(), TPunggung.getText(), TEkstremitas.getText(), 
            TLab.getText(), Txray.getText(), Tecg.getText(), Tctscan.getText(), Tusg.getText(), Tlainya.getText(), TDiagSementara.getText(), Ticd.getText(), cmbRencana.getSelectedItem().toString(), TketRencana.getText(), Tedukasi.getText(), 
            Trencana.getText(), kdpetugas.getText(), nmpenerima.getText(), kddokter.getText(), Valid.SetTgl(TglKeluar.getSelectedItem() + "") + " " + TglKeluar.getSelectedItem().toString().substring(11, 19), cmbRuangan.getSelectedItem().toString(), 
            Tindikasi.getText(), Tdipulangkan.getText(), Tdirujuk.getText(), TAlasanDirujuk.getText(), meninggal, cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), Tpenyebab.getText(), Tku.getText(), 
            Ttd.getText(), Thr.getText(), Trr.getText(), Ttemp.getText(), Tspo2.getText(), Tgcs.getText(), KdPerawat.getText(), kddpjp.getText(), Tket_gambar.getText(), TgcsV.getText(), TgcsM.getText(), TDiam_kiri.getText(), ganggnafas
        }) == true) {
            TCari.setText(TNoRw.getText());
            Ttd.setText("");
            Trr.setText("");
            Ttemp.setText("");
            emptTeks();
            ChkMeninggal.setSelected(false);
            cmbJam.setSelectedIndex(0);
            cmbMnt.setSelectedIndex(0);
            cmbDtk.setSelectedIndex(0);

            cmbJam.setEnabled(false);
            cmbMnt.setEnabled(false);
            cmbDtk.setEnabled(false);            
            TabRawat.setSelectedIndex(1);
            tampil();
        }
    }
    
    private void cekData() {        
        if (ChkCervical.isSelected() == true) {
            cervical = "ya";
        } else {
            cervical = "tidak";
        }
        
        if (ChkRJP.isSelected() == true) {
            rjp = "ya";
        } else {
            rjp = "tidak";
        }
        
        if (ChkDefribilasi.isSelected() == true) {
            defribrilasi = "ya";
        } else {
            defribrilasi = "tidak";
        }
        
        if (ChkIntubasi.isSelected() == true) {
            intubasi = "ya";
        } else {
            intubasi = "tidak";
        }
        
        if (ChkVTP.isSelected() == true) {
            vtp = "ya";
        } else {
            vtp = "tidak";
        }
        
        if (ChkDekompresi.isSelected() == true) {
            dekompresi = "ya";
        } else {
            dekompresi = "tidak";
        }
        
        if (ChkBalut.isSelected() == true) {
            balut = "ya";
        } else {
            balut = "tidak";
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
        
        if (ChkInfus.isSelected() == true) {
            infus = "ya";
        } else {
            infus = "tidak";
        }
        
        if (ChkObat.isSelected() == true) {
            obat = "ya";
        } else {
            obat = "tidak";
        }
        
        if (ChkTidak.isSelected() == true) {
            tidakada = "ya";
        } else {
            tidakada = "tidak";
        }
        
        if (ChkGangNafas.isSelected() == true) {
            ganggnafas = "ya";
        } else {
            ganggnafas = "tidak";
        }
        
        if (ChkPaten.isSelected() == true) {
            paten = "ya";
        } else {
            paten = "tidak";
        }
        
        if (ChkObstruksiP.isSelected() == true) {
            obsP = "ya";
        } else {
            obsP = "tidak";
        }
        
        if (ChkDeformitas.isSelected() == true) {
            deformitas = "ya";
        } else {
            deformitas = "tidak";
        }
        
        if (ChkContusio.isSelected() == true) {
            contusio = "ya";
        } else {
            contusio = "tidak";
        }
        
        if (ChkPenetrasi.isSelected() == true) {
            penetrasi = "ya";
        } else {
            penetrasi = "tidak";
        }
        
        if (ChkTenderness.isSelected() == true) {
            tenderness = "ya";
        } else {
            tenderness = "tidak";
        }
        
        if (ChkSwelling.isSelected() == true) {
            swelling = "ya";
        } else {
            swelling = "tidak";
        }
        
        if (ChkEkskoriasi.isSelected() == true) {
            ekskoriasi = "ya";
        } else {
            ekskoriasi = "tidak";
        }
        
        if (ChkAbrasi.isSelected() == true) {
            abrasi = "ya";
        } else {
            abrasi = "tidak";
        }
        
        if (ChkBurn.isSelected() == true) {
            burn = "ya";
        } else {
            burn = "tidak";
        }
        
        if (ChkLaserasi.isSelected() == true) {
            laserasi = "ya";
        } else {
            laserasi = "tidak";
        }
        
        if (ChkTdkTampk.isSelected() == true) {
            tdk_tmpk_jls = "ya";
        } else {
            tdk_tmpk_jls = "tidak";
        }
        
        if (ChkObstruksiT.isSelected() == true) {
            obsT = "ya";
        } else {
            obsT = "tidak";
        }
        
        if (ChkTrauma.isSelected() == true) {
            traumaJlnNfs = "ya";
        } else {
            traumaJlnNfs = "tidak";
        }
        
        if (ChkResiko.isSelected() == true) {
            resikoAspirasi = "ya";
        } else {
            resikoAspirasi = "tidak";
        }
        
        if (ChkBendaAsing.isSelected() == true) {
            bendaAsing = "ya";
        } else {
            bendaAsing = "tidak";
        }
        
        if (ChkHipertensi.isSelected() == true) {
            hipertensi = "ya";
        } else {
            hipertensi = "tidak";
        }
        
        if (ChkDM.isSelected() == true) {
            dm = "ya";
        } else {
            dm = "tidak";
        }
        
        if (ChkJantung.isSelected() == true) {
            jantung = "ya";
        } else {
            jantung = "tidak";
        }
        
        if (ChkMerokok.isSelected() == true) {
            merokok = "ya";
        } else {
            merokok = "tidak";
        }
        
        if (ChkMeninggal.isSelected() == true) {
            meninggal = "ya";
        } else {
            meninggal = "tidak";
        }
    }
    
    private void hitungSkorDowne() {
        int A, B, C, D, E, hasil;
        A = Integer.parseInt(TFrekuensi.getText());
        B = Integer.parseInt(TRetraksi.getText());
        C = Integer.parseInt(TSianosis.getText());
        D = Integer.parseInt(TAir.getText());
        E = Integer.parseInt(TMerintih.getText());

        hasil = 0;
        hasil = A + B + C + D + E;
        TJlhSkor.setText(Valid.SetAngka2(hasil));
    }
    
    private void dataCek() {
        if (cervical.equals("ya")) {
            ChkCervical.setSelected(true);
        } else {
            ChkCervical.setSelected(false);
        }

        if (rjp.equals("ya")) {
            ChkRJP.setSelected(true);
        } else {
            ChkRJP.setSelected(false);
        }
       
        if (defribrilasi.equals("ya")) {
            ChkDefribilasi.setSelected(true);
        } else {
            ChkDefribilasi.setSelected(false);
        }
        
        if (intubasi.equals("ya")) {
            ChkIntubasi.setSelected(true);
        } else {
            ChkIntubasi.setSelected(false);
        }
        
        if (vtp.equals("ya")) {
            ChkVTP.setSelected(true);
        } else {
            ChkVTP.setSelected(false);
        }

        if (dekompresi.equals("ya")) {
            ChkDekompresi.setSelected(true);
        } else {
            ChkDekompresi.setSelected(false);
        }
        
        if (balut.equals("ya")) {
            ChkBalut.setSelected(true);
        } else {
            ChkBalut.setSelected(false);
        }
        
        if (kateter.equals("ya")) {
            ChkKateter.setSelected(true);
        } else {
            ChkKateter.setSelected(false);
        }
        
        if (ngt.equals("ya")) {
            ChkNGT.setSelected(true);
        } else {
            ChkNGT.setSelected(false);
        }
        
        if (infus.equals("ya")) {
            ChkInfus.setSelected(true);
        } else {
            ChkInfus.setSelected(false);
        }
        
        if (obat.equals("ya")) {
            ChkObat.setSelected(true);
        } else {
            ChkObat.setSelected(false);
        }

        if (tidakada.equals("ya")) {
            ChkTidak.setSelected(true);
        } else {
            ChkTidak.setSelected(false);
        }
        
        if (ganggnafas.equals("ya")) {
            ChkGangNafas.setSelected(true);
        } else {
            ChkGangNafas.setSelected(false);
        }

        if (paten.equals("ya")) {
            ChkPaten.setSelected(true);
        } else {
            ChkPaten.setSelected(false);
        }

        if (obsP.equals("ya")) {
            ChkObstruksiP.setSelected(true);
        } else {
            ChkObstruksiP.setSelected(false);
        }
        
        if (deformitas.equals("ya")) {
            ChkDeformitas.setSelected(true);
        } else {
            ChkDeformitas.setSelected(false);
        }
        
        if (contusio.equals("ya")) {
            ChkContusio.setSelected(true);
        } else {
            ChkContusio.setSelected(false);
        }

        if (penetrasi.equals("ya")) {
            ChkPenetrasi.setSelected(true);
        } else {
            ChkPenetrasi.setSelected(false);
        }

        if (tenderness.equals("ya")) {
            ChkTenderness.setSelected(true);
        } else {
            ChkTenderness.setSelected(false);
        }
        
        if (swelling.equals("ya")) {
            ChkSwelling.setSelected(true);
        } else {
            ChkSwelling.setSelected(false);
        }
        
        if (ekskoriasi.equals("ya")) {
            ChkEkskoriasi.setSelected(true);
        } else {
            ChkEkskoriasi.setSelected(false);
        }
        
        if (abrasi.equals("ya")) {
            ChkAbrasi.setSelected(true);
        } else {
            ChkAbrasi.setSelected(false);
        }
        
        if (burn.equals("ya")) {
            ChkBurn.setSelected(true);
        } else {
            ChkBurn.setSelected(false);
        }
        
        if (laserasi.equals("ya")) {
            ChkLaserasi.setSelected(true);
        } else {
            ChkLaserasi.setSelected(false);
        }
        
        if (tdk_tmpk_jls.equals("ya")) {
            ChkTdkTampk.setSelected(true);
        } else {
            ChkTdkTampk.setSelected(false);
        }

        if (obsT.equals("ya")) {
            ChkObstruksiT.setSelected(true);
        } else {
            ChkObstruksiT.setSelected(false);
        }
        
        if (traumaJlnNfs.equals("ya")) {
            ChkTrauma.setSelected(true);
        } else {
            ChkTrauma.setSelected(false);
        }

        if (resikoAspirasi.equals("ya")) {
            ChkResiko.setSelected(true);
        } else {
            ChkResiko.setSelected(false);
        }

        if (bendaAsing.equals("ya")) {
            ChkBendaAsing.setSelected(true);
        } else {
            ChkBendaAsing.setSelected(false);
        }

        if (hipertensi.equals("ya")) {
            ChkHipertensi.setSelected(true);
        } else {
            ChkHipertensi.setSelected(false);
        }
        
        if (dm.equals("ya")) {
            ChkDM.setSelected(true);
        } else {
            ChkDM.setSelected(false);
        }

        if (jantung.equals("ya")) {
            ChkJantung.setSelected(true);
        } else {
            ChkJantung.setSelected(false);
        }
        
        if (merokok.equals("ya")) {
            ChkMerokok.setSelected(true);
        } else {
            ChkMerokok.setSelected(false);
        }
        
        if (meninggal.equals("ya")) {
            ChkMeninggal.setSelected(true);
        } else {
            ChkMeninggal.setSelected(false);
        }
        
        //------------------------------------------------------------
        if (ChkObat.isSelected() == true) {
            TObat.setEnabled(true);
        } else {
            TObat.setText("");
            TObat.setEnabled(false);
        }
        
        if (ChkTrauma.isSelected() == true) {
            cmbTrauma.setEnabled(true);
        } else {
            cmbTrauma.setSelectedIndex(0);
            cmbTrauma.setEnabled(false);
        }
        
        if (ChkResiko.isSelected() == true) {
            cmbResiko.setEnabled(true);
        } else {
            cmbResiko.setSelectedIndex(0);
            cmbResiko.setEnabled(false);
        }
        
        if (ChkBendaAsing.isSelected() == true) {
            TBendaAsing.setEnabled(true);
        } else {
            TBendaAsing.setText("");
            TBendaAsing.setEnabled(false);
        }
        
        if (cmbRencana.getSelectedIndex() != 0) {
            TketRencana.setEnabled(true);
        } else {
            TketRencana.setText("");
            TketRencana.setEnabled(false);
        }
        
        if (ChkObstruksiP.isSelected() == true) {
            cmbObstruksi.setEnabled(true);
        } else {
            cmbObstruksi.setSelectedIndex(0);
            cmbObstruksi.setEnabled(false);
        }
        
        if (cmbPernapasan.getSelectedIndex() != 0) {
            cmbReguler.setEnabled(true);
        } else {
            cmbReguler.setSelectedIndex(0);
            cmbReguler.setEnabled(false);
        }
        
        if (ChkMeninggal.isSelected() == true) {
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
    }
}