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
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilLIS;
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgCariPeriksaRadiologi;
import simrskhanza.DlgNotepad;

/**
 *
 * @author perpustakaan
 */
public final class RMPenilaianAwalKeperawatanIGDrz extends javax.swing.JDialog {
    private DefaultTableModel tabMode, tabMode1, tabModeResiko;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, pps1, pps2, pps3, pps4, pps5, pps6, pps7, pps8;
    private ResultSet rs, rs1, rs2, rs3, rs4, rrs1, rrs2, rrs3, rrs4, rrs5, rrs6, rrs7, rrs8;
    private int i = 0, x = 0, skor = 0, pilihan = 0, cekHasilRad = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);        
    private String nip = "", skrining_gz = "", gz_anak1 = "", gz_anak2 = "", gz_anak3 = "", gz_anak4 = "",
            aler_obat = "", aler_mak = "", aler_lain = "", pin = "", iden1 = "", iden2 = "", iden3 = "", iden4 = "",
            iden5 = "", iden6 = "", iden7 = "", iden8 = "", iden9 = "", iden10 = "", mpp = "", dp = "",
            faktorresikoigd = "", kdItemrad = "", itemDipilih = "", tglRad = "", jamRad = "";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMPenilaianAwalKeperawatanIGDrz(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode = new DefaultTableModel(null, new Object[]{
            "No.Rawat", "No.RM", "Nama Pasien", "Tgl. Lahir", "Tgl. Asesmen", "Keluhan Utama", "Tensi", "Nadi", "Nafas", "Suhu", "BB", "TB",
            "Asesmen Psikologis", "Masalah Perilaku", "Sebutkan Perilaku", "Hubungan Pasien", "Tempat Tinggal", "Tempat Tinggal Lain",
            "skrining_gizi", "gizi_dewasa1", "gizi_dewasa1ya", "gizi_dewasa2", "gizi_anak1", "gizi_anak2", "gizi_anak3", "gizi_anak_penyakit",
            "gizi_anak_penyakit_lain", "cegah_resiko_jatuh", "tindakan_pencegahan", "alat_bantu", "cacat_tubuh", "ada_cacat_tubuh",
            "riwayat_alergi", "alergi_obat", "reaksi_alergi_obat", "alergi_makanan", "reaksi_alergi_makanan", "alergi_lainnya",
            "reaksi_alergi_lainnya", "pin_kancing", "alergi_diberitahukan", "nyeri", "ya_nyeri_lokasi", "jenis_nyeri", "provocation",
            "provocation_lain", "quality", "quality_lain", "radiation", "severity", "time", "time_lama", "diagnosa_keperawatan",
            "tindakan_keperawatan", "evaluasi_keperawatan", "identifikasi1", "identifikasi2", "identifikasi3", "identifikasi4",
            "identifikasi5", "identifikasi6", "identifikasi7", "identifikasi8", "identifikasi9", "identifikasi10", "manajer_pelayanan",
            "discharge_planing", "tgl_verifikasi", "nip_dpjp", "nip_perawat", "tglasesmen", "nmperawat", "nmdpjp", "sttsnikah", "pekerjaan",
            "skala_nyeri", "ket_radiation"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 77; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(70);
            } else if (i == 2) {
                column.setPreferredWidth(250);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(120);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(75);                
            } else if (i == 9) {
                column.setPreferredWidth(75);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(150);
            } else if (i == 13) {
                column.setPreferredWidth(150);
            } else if (i == 14) {
                column.setPreferredWidth(250);
            } else if (i == 15) {
                column.setPreferredWidth(150);
            } else if (i == 16) {
                column.setPreferredWidth(150);
            } else if (i == 17) {
                column.setPreferredWidth(150);
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
            }
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
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
        
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        td.setDocument(new batasInput((int) 7).getKata(td));
        nadi.setDocument(new batasInput((int) 7).getKata(nadi));
        napas.setDocument(new batasInput((int) 7).getKata(napas));
        napas.setDocument(new batasInput((int) 7).getKata(napas));
        suhu.setDocument(new batasInput((int) 7).getKata(suhu));
        bb.setDocument(new batasInput((int) 7).getKata(bb));
        tb.setDocument(new batasInput((int) 7).getKata(tb));
        lainGZanak.setDocument(new batasInput((int) 255).getKata(lainGZanak));        
        
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
                    nm_perawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMPenilaianAwalKeperawatanIGDrz")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                    }
                    BtnDokter.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHasilPemeriksaanLab = new javax.swing.JMenuItem();
        MnHasilPemeriksaanRad = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        TabRawat = new javax.swing.JTabbedPane();
        FormTriase = new widget.InternalFrame();
        ScrollTriase1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel18 = new widget.Label();
        tgl_asesmen = new widget.Tanggal();
        jLabel5 = new widget.Label();
        Ttgl_lahir = new widget.TextBox();
        jLabel8 = new widget.Label();
        scrollPane2 = new widget.ScrollPane();
        Tkeluhan = new widget.TextArea();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        td = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel11 = new widget.Label();
        nadi = new widget.TextBox();
        jLabel42 = new widget.Label();
        jLabel12 = new widget.Label();
        napas = new widget.TextBox();
        jLabel43 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        jLabel15 = new widget.Label();
        suhu = new widget.TextBox();
        bb = new widget.TextBox();
        tb = new widget.TextBox();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel16 = new widget.Label();
        cmbAsesmen = new widget.ComboBox();
        jLabel17 = new widget.Label();
        cmbPerilaku = new widget.ComboBox();
        jLabel20 = new widget.Label();
        Tsebutkan = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        Tstts_nikah = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        cmbHub_pasien = new widget.ComboBox();
        jLabel27 = new widget.Label();
        cmbTempat_tgl = new widget.ComboBox();
        jLabel28 = new widget.Label();
        Ttmpt_tgl_lain = new widget.TextBox();
        jLabel29 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel30 = new widget.Label();
        ChkSkriningD = new widget.RadioButton();
        ChkSkriningA = new widget.RadioButton();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        jLabel75 = new widget.Label();
        cmbDewasaGZ1 = new widget.ComboBox();
        cmbDewasaYaGZ1 = new widget.ComboBox();
        skorGZ1 = new widget.TextBox();
        skorYaGZ1 = new widget.TextBox();
        jLabel69 = new widget.Label();
        jLabel73 = new widget.Label();
        cmbDewasaGZ2 = new widget.ComboBox();
        skorGZ2 = new widget.TextBox();
        TotSkorGZD = new widget.TextBox();
        jLabel74 = new widget.Label();
        kesimpulanGZDewasa = new widget.TextArea();
        kesimpulanGZanak = new widget.TextArea();
        ChkGZanak3 = new widget.CekBox();
        ChkGZanak2 = new widget.CekBox();
        skorGZ5 = new widget.TextBox();
        jLabel136 = new widget.Label();
        scrollPane7 = new widget.ScrollPane();
        malnutrisi = new widget.TextArea();
        jLabel93 = new widget.Label();
        jLabel54 = new widget.Label();
        jLabel134 = new widget.Label();
        ChkGZanak1 = new widget.CekBox();
        skorGZ3 = new widget.TextBox();
        ChkGZanak4 = new widget.CekBox();
        skorGZ4 = new widget.TextBox();
        jLabel92 = new widget.Label();
        jLabel137 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel135 = new widget.Label();
        jLabel138 = new widget.Label();
        skorGZ6 = new widget.TextBox();
        TotSkorGZA = new widget.TextBox();
        jLabel83 = new widget.Label();
        lainGZanak = new widget.TextBox();
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
        jLabel65 = new widget.Label();
        TabTindakanPencegahan = new javax.swing.JTabbedPane();
        panelBiasa6 = new widget.PanelBiasa();
        TabPencegahanDewasa = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        dewasaA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        dewasaB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        dewasaC = new widget.TextArea();
        panelBiasa7 = new widget.PanelBiasa();
        TabPencegahanAnak = new javax.swing.JTabbedPane();
        panelBiasa14 = new widget.PanelBiasa();
        anakA = new widget.TextArea();
        panelBiasa15 = new widget.PanelBiasa();
        anakB = new widget.TextArea();
        jLabel139 = new widget.Label();
        cmbPencegahan = new widget.ComboBox();
        cmbTindakanCegah = new widget.ComboBox();
        jLabel140 = new widget.Label();
        jLabel66 = new widget.Label();
        jLabel31 = new widget.Label();
        cmbAlatBantu = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbCacat = new widget.ComboBox();
        jLabel33 = new widget.Label();
        Tsebutkan_cacat = new widget.TextBox();
        jLabel34 = new widget.Label();
        cmbRiwayat = new widget.ComboBox();
        chkAlergiObat = new widget.CekBox();
        jLabel68 = new widget.Label();
        reaksiAlergiObat = new widget.TextBox();
        chkAlergiMakanan = new widget.CekBox();
        jLabel70 = new widget.Label();
        reaksiAlergiMakanan = new widget.TextBox();
        chkAlergiLain = new widget.CekBox();
        jLabel71 = new widget.Label();
        reaksiAlergiLain = new widget.TextBox();
        chkPin = new widget.CekBox();
        jLabel72 = new widget.Label();
        cmbdiberitahukan = new widget.ComboBox();
        jLabel76 = new widget.Label();
        jLabel77 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel78 = new widget.Label();
        Tnyeri = new widget.TextBox();
        jLabel79 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        jLabel80 = new widget.Label();
        jLabel81 = new widget.Label();
        jLabel82 = new widget.Label();
        jLabel84 = new widget.Label();
        jLabel86 = new widget.Label();
        cmbProvo = new widget.ComboBox();
        Tprovo = new widget.TextBox();
        cmbQuality = new widget.ComboBox();
        Tquality = new widget.TextBox();
        cmbRadia = new widget.ComboBox();
        cmbSevere = new widget.ComboBox();
        cmbTime = new widget.ComboBox();
        jLabel87 = new widget.Label();
        cmbLama = new widget.ComboBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        jLabel88 = new widget.Label();
        scrollPane3 = new widget.ScrollPane();
        Tdiagnosis = new widget.TextArea();
        scrollPane4 = new widget.ScrollPane();
        Ttindakan = new widget.TextArea();
        jLabel89 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        Tevaluasi = new widget.TextArea();
        jLabel90 = new widget.Label();
        jLabel91 = new widget.Label();
        jLabel94 = new widget.Label();
        chkIden1 = new widget.RadioButton();
        chkIden2 = new widget.RadioButton();
        chkIden3 = new widget.CekBox();
        chkIden4 = new widget.CekBox();
        chkIden5 = new widget.RadioButton();
        chkIden6 = new widget.RadioButton();
        chkIden7 = new widget.CekBox();
        chkIden8 = new widget.RadioButton();
        chkIden9 = new widget.CekBox();
        chkIden10 = new widget.RadioButton();
        jLabel95 = new widget.Label();
        jLabel96 = new widget.Label();
        chkMPP = new widget.CekBox();
        chkDP = new widget.RadioButton();
        jLabel98 = new widget.Label();
        TglVerif = new widget.Tanggal();
        jLabel99 = new widget.Label();
        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel35 = new widget.Label();
        nm_perawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel100 = new widget.Label();
        cmbSkala = new widget.ComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        BtnKeluhan = new widget.Button();
        BtnSebutkan = new widget.Button();
        BtnAlergiObat = new widget.Button();
        BtnAlergiMakanan = new widget.Button();
        BtnAlergiLain = new widget.Button();
        BtnDiagnosa = new widget.Button();
        BtnTindakan = new widget.Button();
        BtnEvaluasi = new widget.Button();
        Tradiation = new widget.TextBox();
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

        MnHasilPemeriksaanLab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanLab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanLab.setText("Hasil Pemeriksaan Lab.");
        MnHasilPemeriksaanLab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanLab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanLab.setIconTextGap(5);
        MnHasilPemeriksaanLab.setName("MnHasilPemeriksaanLab"); // NOI18N
        MnHasilPemeriksaanLab.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanLabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanLab);

        MnHasilPemeriksaanRad.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHasilPemeriksaanRad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHasilPemeriksaanRad.setText("Hasil Pemeriksaan Radiologi");
        MnHasilPemeriksaanRad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHasilPemeriksaanRad.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHasilPemeriksaanRad.setIconTextGap(5);
        MnHasilPemeriksaanRad.setName("MnHasilPemeriksaanRad"); // NOI18N
        MnHasilPemeriksaanRad.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHasilPemeriksaanRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHasilPemeriksaanRadActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHasilPemeriksaanRad);

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Asesmen Keperawatan IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

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

        internalFrame5.add(panelisi4, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbTemplate);

        jPanel1.add(Scroll1);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll3.setViewportView(Ttemplate);

        jPanel1.add(Scroll3);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Assesmen Keperawatan Gawat Darurat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        FormTriase.setBorder(null);
        FormTriase.setName("FormTriase"); // NOI18N
        FormTriase.setLayout(new java.awt.BorderLayout(1, 1));

        ScrollTriase1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 253)));
        ScrollTriase1.setName("ScrollTriase1"); // NOI18N
        ScrollTriase1.setOpaque(true);
        ScrollTriase1.setPreferredSize(new java.awt.Dimension(102, 557));

        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik kanan pada area ini untuk melihat hasil pemeriksaan penunjang medis");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 2421));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(116, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(317, 10, 240, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(242, 10, 70, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tanggal : ");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(558, 10, 70, 23);

        tgl_asesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-12-2023 15:01:18" }));
        tgl_asesmen.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        tgl_asesmen.setName("tgl_asesmen"); // NOI18N
        tgl_asesmen.setOpaque(false);
        FormInput.add(tgl_asesmen);
        tgl_asesmen.setBounds(630, 10, 135, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Tgl. Lahir :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 40, 110, 23);

        Ttgl_lahir.setEditable(false);
        Ttgl_lahir.setBackground(new java.awt.Color(245, 250, 240));
        Ttgl_lahir.setForeground(new java.awt.Color(0, 0, 0));
        Ttgl_lahir.setName("Ttgl_lahir"); // NOI18N
        FormInput.add(Ttgl_lahir);
        Ttgl_lahir.setBounds(116, 40, 100, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Keluhan Utama :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(280, 40, 90, 23);

        scrollPane2.setName("scrollPane2"); // NOI18N

        Tkeluhan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tkeluhan.setColumns(20);
        Tkeluhan.setRows(5);
        Tkeluhan.setName("Tkeluhan"); // NOI18N
        Tkeluhan.setPreferredSize(new java.awt.Dimension(162, 200));
        Tkeluhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkeluhanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Tkeluhan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(376, 40, 470, 58);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("KEADAAN UMUM :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 135, 110, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Tekanan Darah :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 155, 130, 23);

        td.setForeground(new java.awt.Color(0, 0, 0));
        td.setName("td"); // NOI18N
        td.setPreferredSize(new java.awt.Dimension(207, 23));
        td.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tdKeyPressed(evt);
            }
        });
        FormInput.add(td);
        td.setBounds(136, 155, 70, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("mmHg");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(210, 155, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Frekuensi Nadi :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 185, 130, 23);

        nadi.setForeground(new java.awt.Color(0, 0, 0));
        nadi.setName("nadi"); // NOI18N
        nadi.setPreferredSize(new java.awt.Dimension(207, 23));
        nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nadiKeyPressed(evt);
            }
        });
        FormInput.add(nadi);
        nadi.setBounds(136, 185, 70, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("x/menit");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(210, 185, 50, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Frekuensi Nafas :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 215, 130, 23);

        napas.setForeground(new java.awt.Color(0, 0, 0));
        napas.setName("napas"); // NOI18N
        napas.setPreferredSize(new java.awt.Dimension(207, 23));
        napas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                napasKeyPressed(evt);
            }
        });
        FormInput.add(napas);
        napas.setBounds(136, 215, 70, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("x/menit");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(210, 215, 50, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Suhu :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(260, 155, 90, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Berat Badan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(260, 185, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tinggi Badan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(260, 215, 90, 23);

        suhu.setForeground(new java.awt.Color(0, 0, 0));
        suhu.setName("suhu"); // NOI18N
        suhu.setPreferredSize(new java.awt.Dimension(207, 23));
        suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suhuKeyPressed(evt);
            }
        });
        FormInput.add(suhu);
        suhu.setBounds(357, 155, 70, 23);

        bb.setForeground(new java.awt.Color(0, 0, 0));
        bb.setName("bb"); // NOI18N
        bb.setPreferredSize(new java.awt.Dimension(207, 23));
        bb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bbKeyPressed(evt);
            }
        });
        FormInput.add(bb);
        bb.setBounds(357, 185, 70, 23);

        tb.setForeground(new java.awt.Color(0, 0, 0));
        tb.setName("tb"); // NOI18N
        tb.setPreferredSize(new java.awt.Dimension(207, 23));
        tb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKeyPressed(evt);
            }
        });
        FormInput.add(tb);
        tb.setBounds(357, 215, 70, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("°C");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(433, 155, 30, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Kg.");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(433, 185, 30, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Cm.");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(433, 215, 30, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Assesmen Psikologis :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 245, 130, 23);

        cmbAsesmen.setForeground(new java.awt.Color(0, 0, 0));
        cmbAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tenang", "Cemas", "Takut", "Marah", "Sedih", "Kecenderungan bunuh diri" }));
        cmbAsesmen.setName("cmbAsesmen"); // NOI18N
        cmbAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbAsesmenKeyPressed(evt);
            }
        });
        FormInput.add(cmbAsesmen);
        cmbAsesmen.setBounds(136, 245, 160, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Masalah Perilaku : ");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(300, 245, 110, 23);

        cmbPerilaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerilaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Ada", "Ada" }));
        cmbPerilaku.setName("cmbPerilaku"); // NOI18N
        cmbPerilaku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerilakuActionPerformed(evt);
            }
        });
        cmbPerilaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbPerilakuKeyPressed(evt);
            }
        });
        FormInput.add(cmbPerilaku);
        cmbPerilaku.setBounds(413, 245, 80, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Ada, Sebutkan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(300, 275, 110, 23);

        Tsebutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tsebutkan.setName("Tsebutkan"); // NOI18N
        Tsebutkan.setPreferredSize(new java.awt.Dimension(207, 23));
        Tsebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsebutkanKeyPressed(evt);
            }
        });
        FormInput.add(Tsebutkan);
        Tsebutkan.setBounds(413, 275, 440, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("STATUS SOSIAL DAN EKONOMI :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 305, 180, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Status Pernikahan :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(0, 325, 130, 23);

        Tstts_nikah.setEditable(false);
        Tstts_nikah.setForeground(new java.awt.Color(0, 0, 0));
        Tstts_nikah.setName("Tstts_nikah"); // NOI18N
        Tstts_nikah.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(Tstts_nikah);
        Tstts_nikah.setBounds(136, 325, 200, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Hubungan pasien :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(0, 355, 130, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("dengan anggota  ");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(0, 370, 130, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("keluarga  ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 385, 130, 23);

        cmbHub_pasien.setForeground(new java.awt.Color(0, 0, 0));
        cmbHub_pasien.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Baik", "Tidak Baik" }));
        cmbHub_pasien.setName("cmbHub_pasien"); // NOI18N
        cmbHub_pasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbHub_pasienKeyPressed(evt);
            }
        });
        FormInput.add(cmbHub_pasien);
        cmbHub_pasien.setBounds(136, 355, 80, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Tempat Tinggal :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(340, 325, 100, 23);

        cmbTempat_tgl.setForeground(new java.awt.Color(0, 0, 0));
        cmbTempat_tgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Rumah Sendiri", "Rumah Keluarga", "Lainnya" }));
        cmbTempat_tgl.setName("cmbTempat_tgl"); // NOI18N
        cmbTempat_tgl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTempat_tglActionPerformed(evt);
            }
        });
        cmbTempat_tgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbTempat_tglKeyPressed(evt);
            }
        });
        FormInput.add(cmbTempat_tgl);
        cmbTempat_tgl.setBounds(445, 325, 120, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tempat Tinggal, Lainnya :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(300, 355, 140, 23);

        Ttmpt_tgl_lain.setForeground(new java.awt.Color(0, 0, 0));
        Ttmpt_tgl_lain.setName("Ttmpt_tgl_lain"); // NOI18N
        Ttmpt_tgl_lain.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(Ttmpt_tgl_lain);
        Ttmpt_tgl_lain.setBounds(445, 355, 410, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Pekerjaan :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(370, 385, 70, 23);

        Tpekerjaan.setEditable(false);
        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        Tpekerjaan.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(445, 385, 410, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("SKRINING GIZI AWAL :");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 415, 130, 23);

        ChkSkriningD.setBackground(new java.awt.Color(242, 242, 242));
        ChkSkriningD.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(ChkSkriningD);
        ChkSkriningD.setText("DEWASA (Modifikasi MST)");
        ChkSkriningD.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSkriningD.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSkriningD.setName("ChkSkriningD"); // NOI18N
        ChkSkriningD.setPreferredSize(new java.awt.Dimension(95, 23));
        ChkSkriningD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSkriningDActionPerformed(evt);
            }
        });
        FormInput.add(ChkSkriningD);
        ChkSkriningD.setBounds(30, 435, 160, 23);

        ChkSkriningA.setBackground(new java.awt.Color(242, 242, 242));
        ChkSkriningA.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(ChkSkriningA);
        ChkSkriningA.setText("ANAK (berdasarkan modifikasi form STRONG Kids)");
        ChkSkriningA.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkSkriningA.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkSkriningA.setName("ChkSkriningA"); // NOI18N
        ChkSkriningA.setPreferredSize(new java.awt.Dimension(95, 23));
        ChkSkriningA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSkriningAActionPerformed(evt);
            }
        });
        FormInput.add(ChkSkriningA);
        ChkSkriningA.setBounds(410, 435, 270, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak ");
        jLabel61.setName("jLabel61"); // NOI18N
        FormInput.add(jLabel61);
        jLabel61.setBounds(0, 465, 390, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("diinginkan dalam 6 bulan terakhir ?");
        jLabel62.setName("jLabel62"); // NOI18N
        FormInput.add(jLabel62);
        jLabel62.setBounds(0, 480, 210, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        FormInput.add(jLabel75);
        jLabel75.setBounds(348, 480, 40, 23);

        cmbDewasaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaGZ1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Tahu/tidak yakin (ada tanda : baju menjadi longgar)", "Ya, ada penurunan BB sebanyak" }));
        cmbDewasaGZ1.setName("cmbDewasaGZ1"); // NOI18N
        cmbDewasaGZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaGZ1ActionPerformed(evt);
            }
        });
        FormInput.add(cmbDewasaGZ1);
        cmbDewasaGZ1.setBounds(45, 510, 310, 23);

        cmbDewasaYaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaYaGZ1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbDewasaYaGZ1.setName("cmbDewasaYaGZ1"); // NOI18N
        cmbDewasaYaGZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaYaGZ1ActionPerformed(evt);
            }
        });
        FormInput.add(cmbDewasaYaGZ1);
        cmbDewasaYaGZ1.setBounds(45, 540, 310, 23);

        skorGZ1.setEditable(false);
        skorGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ1.setText("0");
        skorGZ1.setFocusTraversalPolicyProvider(true);
        skorGZ1.setName("skorGZ1"); // NOI18N
        FormInput.add(skorGZ1);
        skorGZ1.setBounds(358, 510, 30, 23);

        skorYaGZ1.setEditable(false);
        skorYaGZ1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGZ1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGZ1.setText("0");
        skorYaGZ1.setFocusTraversalPolicyProvider(true);
        skorYaGZ1.setName("skorYaGZ1"); // NOI18N
        FormInput.add(skorYaGZ1);
        skorYaGZ1.setBounds(358, 540, 30, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 570, 360, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("makan / kesulitan menerima makanan ?");
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 585, 230, 23);

        cmbDewasaGZ2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDewasaGZ2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbDewasaGZ2.setName("cmbDewasaGZ2"); // NOI18N
        cmbDewasaGZ2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDewasaGZ2ActionPerformed(evt);
            }
        });
        FormInput.add(cmbDewasaGZ2);
        cmbDewasaGZ2.setBounds(45, 615, 65, 23);

        skorGZ2.setEditable(false);
        skorGZ2.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ2.setText("0");
        skorGZ2.setFocusTraversalPolicyProvider(true);
        skorGZ2.setName("skorGZ2"); // NOI18N
        FormInput.add(skorGZ2);
        skorGZ2.setBounds(358, 615, 30, 23);

        TotSkorGZD.setEditable(false);
        TotSkorGZD.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGZD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGZD.setText("0");
        TotSkorGZD.setFocusTraversalPolicyProvider(true);
        TotSkorGZD.setName("TotSkorGZD"); // NOI18N
        FormInput.add(TotSkorGZD);
        TotSkorGZD.setBounds(353, 645, 35, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        FormInput.add(jLabel74);
        jLabel74.setBounds(0, 645, 350, 23);

        kesimpulanGZDewasa.setEditable(false);
        kesimpulanGZDewasa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi Dewasa (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGZDewasa.setColumns(20);
        kesimpulanGZDewasa.setRows(5);
        kesimpulanGZDewasa.setName("kesimpulanGZDewasa"); // NOI18N
        FormInput.add(kesimpulanGZDewasa);
        kesimpulanGZDewasa.setBounds(38, 675, 350, 50);

        kesimpulanGZanak.setEditable(false);
        kesimpulanGZanak.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi Anak (Total Skor) : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGZanak.setColumns(20);
        kesimpulanGZanak.setRows(5);
        kesimpulanGZanak.setName("kesimpulanGZanak"); // NOI18N
        FormInput.add(kesimpulanGZanak);
        kesimpulanGZanak.setBounds(38, 730, 350, 50);

        ChkGZanak3.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak3.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak3.setText("3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan");
        ChkGZanak3.setBorderPainted(true);
        ChkGZanak3.setBorderPaintedFlat(true);
        ChkGZanak3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak3.setName("ChkGZanak3"); // NOI18N
        ChkGZanak3.setOpaque(false);
        ChkGZanak3.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak3ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGZanak3);
        ChkGZanak3.setBounds(410, 585, 350, 23);

        ChkGZanak2.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak2.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak2.setText("2. Terdapat tanda-tanda klinis gangguan gizi");
        ChkGZanak2.setBorderPainted(true);
        ChkGZanak2.setBorderPaintedFlat(true);
        ChkGZanak2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak2.setName("ChkGZanak2"); // NOI18N
        ChkGZanak2.setOpaque(false);
        ChkGZanak2.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak2ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGZanak2);
        ChkGZanak2.setBounds(410, 510, 330, 23);

        skorGZ5.setEditable(false);
        skorGZ5.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ5.setText("0");
        skorGZ5.setFocusTraversalPolicyProvider(true);
        skorGZ5.setName("skorGZ5"); // NOI18N
        FormInput.add(skorGZ5);
        skorGZ5.setBounds(765, 585, 30, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel136.setText("* Penurunan asupan makanan selama lebih dari 7 hari");
        jLabel136.setName("jLabel136"); // NOI18N
        jLabel136.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel136MouseClicked(evt);
            }
        });
        FormInput.add(jLabel136);
        jLabel136.setBounds(442, 630, 270, 23);

        scrollPane7.setName("scrollPane7"); // NOI18N

        malnutrisi.setEditable(false);
        malnutrisi.setColumns(20);
        malnutrisi.setRows(5);
        malnutrisi.setText("* Diare kronik > 2 minggu\n* Penyakit jantung bawaan (tersangka)\n* Infeksi HIV (tersangka)\n* Kelainan anatomi bawaan\n* Kelainan metabolisme bawaan\n* Retardasi mental\n* Keterlambatan perkembangan\n* Kanker (tersangka)\n* Penyakit hati/ginjal kronik\n* TB Paru\n* Renca/paska operasimayor\n* Luka bakar luas\n* Terpasang stoma\n* Trauma\n* Lain-lain (jika ada, isi pada kolom lain-lain)");
        malnutrisi.setName("malnutrisi"); // NOI18N
        malnutrisi.setPreferredSize(new java.awt.Dimension(170, 230));
        malnutrisi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                malnutrisiMouseClicked(evt);
            }
        });
        scrollPane7.setViewportView(malnutrisi);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(430, 695, 270, 82);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("selama 1 bulan terakhir");
        jLabel93.setName("jLabel93"); // NOI18N
        jLabel93.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel93MouseClicked(evt);
            }
        });
        FormInput.add(jLabel93);
        jLabel93.setBounds(442, 555, 270, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel54.setText("(tampak kurus, gemuk, pendek, edema, moon face,");
        jLabel54.setName("jLabel54"); // NOI18N
        jLabel54.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel54MouseClicked(evt);
            }
        });
        FormInput.add(jLabel54);
        jLabel54.setBounds(442, 525, 270, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel134.setText("malnutrisi berikut :");
        jLabel134.setName("jLabel134"); // NOI18N
        jLabel134.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel134MouseClicked(evt);
            }
        });
        FormInput.add(jLabel134);
        jLabel134.setBounds(442, 600, 270, 23);

        ChkGZanak1.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak1.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak1.setText("1. Terdapat penurunan BB atau BB menetap");
        ChkGZanak1.setBorderPainted(true);
        ChkGZanak1.setBorderPaintedFlat(true);
        ChkGZanak1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak1.setName("ChkGZanak1"); // NOI18N
        ChkGZanak1.setOpaque(false);
        ChkGZanak1.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak1ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGZanak1);
        ChkGZanak1.setBounds(410, 465, 340, 23);

        skorGZ3.setEditable(false);
        skorGZ3.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ3.setText("0");
        skorGZ3.setFocusTraversalPolicyProvider(true);
        skorGZ3.setName("skorGZ3"); // NOI18N
        FormInput.add(skorGZ3);
        skorGZ3.setBounds(765, 465, 30, 23);

        ChkGZanak4.setBackground(new java.awt.Color(255, 255, 250));
        ChkGZanak4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        ChkGZanak4.setForeground(new java.awt.Color(0, 0, 0));
        ChkGZanak4.setText("Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko");
        ChkGZanak4.setBorderPainted(true);
        ChkGZanak4.setBorderPaintedFlat(true);
        ChkGZanak4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkGZanak4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkGZanak4.setName("ChkGZanak4"); // NOI18N
        ChkGZanak4.setOpaque(false);
        ChkGZanak4.setPreferredSize(new java.awt.Dimension(175, 23));
        ChkGZanak4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkGZanak4ActionPerformed(evt);
            }
        });
        FormInput.add(ChkGZanak4);
        ChkGZanak4.setBounds(410, 660, 350, 23);

        skorGZ4.setEditable(false);
        skorGZ4.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ4.setText("0");
        skorGZ4.setFocusTraversalPolicyProvider(true);
        skorGZ4.setName("skorGZ4"); // NOI18N
        FormInput.add(skorGZ4);
        skorGZ4.setBounds(765, 510, 30, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel92.setText("tampak tua, iga gambang, baggy pant, anoreksia)");
        jLabel92.setName("jLabel92"); // NOI18N
        jLabel92.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel92MouseClicked(evt);
            }
        });
        FormInput.add(jLabel92);
        jLabel92.setBounds(442, 540, 270, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel137.setText("malnutrisi antara lain :");
        jLabel137.setName("jLabel137"); // NOI18N
        jLabel137.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel137MouseClicked(evt);
            }
        });
        FormInput.add(jLabel137);
        jLabel137.setBounds(430, 680, 270, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("(pada bayi < 1 tahun) selama >= 2 bulan");
        jLabel51.setName("jLabel51"); // NOI18N
        jLabel51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel51MouseClicked(evt);
            }
        });
        FormInput.add(jLabel51);
        jLabel51.setBounds(442, 480, 270, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel135.setText("* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)");
        jLabel135.setName("jLabel135"); // NOI18N
        jLabel135.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel135MouseClicked(evt);
            }
        });
        FormInput.add(jLabel135);
        jLabel135.setBounds(442, 615, 270, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Total Skor :");
        jLabel138.setName("jLabel138"); // NOI18N
        FormInput.add(jLabel138);
        jLabel138.setBounds(702, 695, 60, 23);

        skorGZ6.setEditable(false);
        skorGZ6.setForeground(new java.awt.Color(0, 0, 0));
        skorGZ6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGZ6.setText("0");
        skorGZ6.setFocusTraversalPolicyProvider(true);
        skorGZ6.setName("skorGZ6"); // NOI18N
        FormInput.add(skorGZ6);
        skorGZ6.setBounds(765, 660, 30, 23);

        TotSkorGZA.setEditable(false);
        TotSkorGZA.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGZA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGZA.setText("0");
        TotSkorGZA.setFocusTraversalPolicyProvider(true);
        TotSkorGZA.setName("TotSkorGZA"); // NOI18N
        FormInput.add(TotSkorGZA);
        TotSkorGZA.setBounds(765, 695, 30, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setText("Lain-lain : ");
        jLabel83.setName("jLabel83"); // NOI18N
        FormInput.add(jLabel83);
        jLabel83.setBounds(430, 785, 60, 23);

        lainGZanak.setForeground(new java.awt.Color(0, 0, 0));
        lainGZanak.setName("lainGZanak"); // NOI18N
        FormInput.add(lainGZanak);
        lainGZanak.setBounds(490, 785, 360, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Skor : ");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(758, 435, 40, 23);

        Scroll8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " ASSESMEN RESIKO JATUH : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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
        Scroll8.setBounds(10, 815, 615, 143);

        jLabel97.setForeground(new java.awt.Color(0, 0, 0));
        jLabel97.setText("Total Skor :");
        jLabel97.setName("jLabel97"); // NOI18N
        FormInput.add(jLabel97);
        jLabel97.setBounds(633, 815, 60, 23);

        TotSkorRJ.setEditable(false);
        TotSkorRJ.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorRJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorRJ.setText("0");
        TotSkorRJ.setFocusTraversalPolicyProvider(true);
        TotSkorRJ.setName("TotSkorRJ"); // NOI18N
        FormInput.add(TotSkorRJ);
        TotSkorRJ.setBounds(698, 815, 40, 23);

        kesimpulanResikoJatuh.setEditable(false);
        kesimpulanResikoJatuh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skor Resiko Jatuh : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanResikoJatuh.setColumns(20);
        kesimpulanResikoJatuh.setRows(5);
        kesimpulanResikoJatuh.setName("kesimpulanResikoJatuh"); // NOI18N
        FormInput.add(kesimpulanResikoJatuh);
        kesimpulanResikoJatuh.setBounds(633, 845, 220, 113);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Key Word :");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(60, 23));
        FormInput.add(label12);
        label12.setBounds(16, 965, 60, 23);

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
        TCariResiko.setBounds(80, 965, 215, 23);

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
        BtnCariResiko.setBounds(299, 965, 28, 23);

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
        BtnAllResiko.setBounds(331, 965, 28, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("TINDAKAN PENCEGAHAN RESIKO JATUH :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 995, 230, 23);

        TabTindakanPencegahan.setBackground(new java.awt.Color(255, 255, 254));
        TabTindakanPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabTindakanPencegahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabTindakanPencegahan.setName("TabTindakanPencegahan"); // NOI18N

        panelBiasa6.setName("panelBiasa6"); // NOI18N
        panelBiasa6.setLayout(new java.awt.BorderLayout());

        TabPencegahanDewasa.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanDewasa.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanDewasa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanDewasa.setName("TabPencegahanDewasa"); // NOI18N

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        dewasaA.setEditable(false);
        dewasaA.setBackground(new java.awt.Color(255, 255, 255));
        dewasaA.setColumns(20);
        dewasaA.setRows(5);
        dewasaA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        dewasaA.setName("dewasaA"); // NOI18N
        dewasaA.setOpaque(true);
        panelBiasa8.add(dewasaA, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        dewasaB.setEditable(false);
        dewasaB.setBackground(new java.awt.Color(255, 255, 255));
        dewasaB.setColumns(20);
        dewasaB.setRows(5);
        dewasaB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        dewasaB.setName("dewasaB"); // NOI18N
        dewasaB.setOpaque(true);
        panelBiasa9.add(dewasaB, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        dewasaC.setEditable(false);
        dewasaC.setBackground(new java.awt.Color(255, 255, 255));
        dewasaC.setColumns(20);
        dewasaC.setRows(5);
        dewasaC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        dewasaC.setName("dewasaC"); // NOI18N
        dewasaC.setOpaque(true);
        panelBiasa10.add(dewasaC, java.awt.BorderLayout.CENTER);

        TabPencegahanDewasa.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        panelBiasa6.add(TabPencegahanDewasa, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("DEWASA", panelBiasa6);

        panelBiasa7.setName("panelBiasa7"); // NOI18N
        panelBiasa7.setLayout(new java.awt.BorderLayout());

        TabPencegahanAnak.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahanAnak.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahanAnak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahanAnak.setName("TabPencegahanAnak"); // NOI18N

        panelBiasa14.setName("panelBiasa14"); // NOI18N
        panelBiasa14.setLayout(new java.awt.BorderLayout());

        anakA.setEditable(false);
        anakA.setBackground(new java.awt.Color(255, 255, 255));
        anakA.setColumns(20);
        anakA.setRows(5);
        anakA.setText("1. Oreintasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Bel & barang pribadi dalam jangkauan\n5. Pencahayaan adekuat\n6. Edukasi pencegahan jatuh");
        anakA.setName("anakA"); // NOI18N
        anakA.setOpaque(true);
        panelBiasa14.add(anakA, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Umum (A)", panelBiasa14);

        panelBiasa15.setName("panelBiasa15"); // NOI18N
        panelBiasa15.setLayout(new java.awt.BorderLayout());

        anakB.setEditable(false);
        anakB.setBackground(new java.awt.Color(255, 255, 255));
        anakB.setColumns(20);
        anakB.setRows(5);
        anakB.setText("1. Lakukan semua pencegahan umum (A)\n2. Beri tanda segitiga warna kuning pada bed/RM\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas\n4. Kunjungi dan monitor setiap 1 jam\n5. Libatkan keluarga untuk mengawasi pasien");
        anakB.setName("anakB"); // NOI18N
        anakB.setOpaque(true);
        panelBiasa15.add(anakB, java.awt.BorderLayout.CENTER);

        TabPencegahanAnak.addTab("Pencegahan Resiko Tinggi (B)", panelBiasa15);

        panelBiasa7.add(TabPencegahanAnak, java.awt.BorderLayout.CENTER);

        TabTindakanPencegahan.addTab("ANAK", panelBiasa7);

        FormInput.add(TabTindakanPencegahan);
        TabTindakanPencegahan.setBounds(10, 1015, 565, 160);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Jenis Pencegahan :");
        jLabel139.setName("jLabel139"); // NOI18N
        FormInput.add(jLabel139);
        jLabel139.setBounds(580, 1035, 190, 23);

        cmbPencegahan.setForeground(new java.awt.Color(0, 0, 0));
        cmbPencegahan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dewasa", "Anak" }));
        cmbPencegahan.setName("cmbPencegahan"); // NOI18N
        FormInput.add(cmbPencegahan);
        cmbPencegahan.setBounds(775, 1035, 70, 23);

        cmbTindakanCegah.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakanCegah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "A", "B", "C" }));
        cmbTindakanCegah.setName("cmbTindakanCegah"); // NOI18N
        FormInput.add(cmbTindakanCegah);
        cmbTindakanCegah.setBounds(775, 1065, 40, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Tindakan Pencegahan Resiko Jatuh :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(580, 1065, 190, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("STATUS FUNGSIONAL :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 1180, 130, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Penggunaan Alat Bantu :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 1200, 130, 23);

        cmbAlatBantu.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlatBantu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Tongkat", "Kursi Roda" }));
        cmbAlatBantu.setName("cmbAlatBantu"); // NOI18N
        FormInput.add(cmbAlatBantu);
        cmbAlatBantu.setBounds(136, 1200, 90, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Cacat Tubuh :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 1230, 130, 23);

        cmbCacat.setForeground(new java.awt.Color(0, 0, 0));
        cmbCacat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak", "Ada" }));
        cmbCacat.setName("cmbCacat"); // NOI18N
        cmbCacat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCacatActionPerformed(evt);
            }
        });
        FormInput.add(cmbCacat);
        cmbCacat.setBounds(136, 1230, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Sebutkan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(200, 1230, 70, 23);

        Tsebutkan_cacat.setForeground(new java.awt.Color(0, 0, 0));
        Tsebutkan_cacat.setToolTipText("Alt+C");
        Tsebutkan_cacat.setName("Tsebutkan_cacat"); // NOI18N
        Tsebutkan_cacat.setPreferredSize(new java.awt.Dimension(140, 23));
        Tsebutkan_cacat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tsebutkan_cacatKeyPressed(evt);
            }
        });
        FormInput.add(Tsebutkan_cacat);
        Tsebutkan_cacat.setBounds(277, 1230, 580, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Riwayat Alergi :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 1260, 130, 23);

        cmbRiwayat.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwayat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak ada", "Tidak diketahui" }));
        cmbRiwayat.setName("cmbRiwayat"); // NOI18N
        FormInput.add(cmbRiwayat);
        cmbRiwayat.setBounds(136, 1260, 110, 23);

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
        chkAlergiObat.setBounds(260, 1260, 100, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Reaksi : ");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(365, 1260, 60, 23);

        reaksiAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiObat.setFocusTraversalPolicyProvider(true);
        reaksiAlergiObat.setName("reaksiAlergiObat"); // NOI18N
        FormInput.add(reaksiAlergiObat);
        reaksiAlergiObat.setBounds(427, 1260, 430, 23);

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
        chkAlergiMakanan.setBounds(260, 1290, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Reaksi : ");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(365, 1290, 60, 23);

        reaksiAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiMakanan.setFocusTraversalPolicyProvider(true);
        reaksiAlergiMakanan.setName("reaksiAlergiMakanan"); // NOI18N
        FormInput.add(reaksiAlergiMakanan);
        reaksiAlergiMakanan.setBounds(427, 1290, 430, 23);

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
        chkAlergiLain.setBounds(260, 1320, 100, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Reaksi : ");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(365, 1320, 60, 23);

        reaksiAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        reaksiAlergiLain.setFocusTraversalPolicyProvider(true);
        reaksiAlergiLain.setName("reaksiAlergiLain"); // NOI18N
        FormInput.add(reaksiAlergiLain);
        reaksiAlergiLain.setBounds(427, 1320, 430, 23);

        chkPin.setBackground(new java.awt.Color(255, 255, 250));
        chkPin.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPin.setForeground(new java.awt.Color(0, 0, 0));
        chkPin.setText("Pin/kancing penanda alergi (warna merah) dipasang pada gelang identitas pasien");
        chkPin.setBorderPainted(true);
        chkPin.setBorderPaintedFlat(true);
        chkPin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPin.setName("chkPin"); // NOI18N
        chkPin.setOpaque(false);
        chkPin.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkPin);
        chkPin.setBounds(260, 1350, 420, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Alergi diberitahukan kepada :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(260, 1380, 150, 23);

        cmbdiberitahukan.setForeground(new java.awt.Color(0, 0, 0));
        cmbdiberitahukan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Dokter", "Farmasis/Apoteker", "Ahli Gizi" }));
        cmbdiberitahukan.setName("cmbdiberitahukan"); // NOI18N
        FormInput.add(cmbdiberitahukan);
        cmbdiberitahukan.setBounds(416, 1380, 120, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setText("ASESMEN NYERI :");
        jLabel76.setName("jLabel76"); // NOI18N
        FormInput.add(jLabel76);
        jLabel76.setBounds(0, 1410, 110, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Nyeri :");
        jLabel77.setName("jLabel77"); // NOI18N
        FormInput.add(jLabel77);
        jLabel77.setBounds(0, 1430, 130, 23);

        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        FormInput.add(cmbNyeri);
        cmbNyeri.setBounds(136, 1430, 60, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Lokasi :");
        jLabel78.setName("jLabel78"); // NOI18N
        FormInput.add(jLabel78);
        jLabel78.setBounds(200, 1430, 50, 23);

        Tnyeri.setForeground(new java.awt.Color(0, 0, 0));
        Tnyeri.setToolTipText("Alt+C");
        Tnyeri.setName("Tnyeri"); // NOI18N
        Tnyeri.setPreferredSize(new java.awt.Dimension(140, 23));
        Tnyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnyeriKeyPressed(evt);
            }
        });
        FormInput.add(Tnyeri);
        Tnyeri.setBounds(257, 1430, 600, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Jenis :");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(0, 1460, 130, 23);

        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Akut", "Kronis" }));
        cmbJenis.setName("cmbJenis"); // NOI18N
        FormInput.add(cmbJenis);
        cmbJenis.setBounds(136, 1460, 67, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setText("Provocation : Faktor yang memperburuk rasa nyeri");
        jLabel80.setName("jLabel80"); // NOI18N
        FormInput.add(jLabel80);
        jLabel80.setBounds(0, 1490, 270, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Quality : Rasa nyeri seperti");
        jLabel81.setName("jLabel81"); // NOI18N
        FormInput.add(jLabel81);
        jLabel81.setBounds(0, 1520, 270, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setText("Radiation : Nyeri menjalar ke bagian tubuh yang lain");
        jLabel82.setName("jLabel82"); // NOI18N
        FormInput.add(jLabel82);
        jLabel82.setBounds(0, 1550, 270, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setText("Severity : Tingkat keparahan nyeri");
        jLabel84.setName("jLabel84"); // NOI18N
        FormInput.add(jLabel84);
        jLabel84.setBounds(0, 1580, 270, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Time : Nyeri berlangsung");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(0, 1610, 270, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        FormInput.add(cmbProvo);
        cmbProvo.setBounds(280, 1490, 80, 23);

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
        Tprovo.setBounds(367, 1490, 490, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik Dibakar", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        FormInput.add(cmbQuality);
        cmbQuality.setBounds(280, 1520, 104, 23);

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
        Tquality.setBounds(390, 1520, 467, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        cmbRadia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRadiaActionPerformed(evt);
            }
        });
        FormInput.add(cmbRadia);
        cmbRadia.setBounds(280, 1550, 60, 23);

        cmbSevere.setForeground(new java.awt.Color(0, 0, 0));
        cmbSevere.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSevere.setName("cmbSevere"); // NOI18N
        FormInput.add(cmbSevere);
        cmbSevere.setBounds(280, 1580, 90, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        FormInput.add(cmbTime);
        cmbTime.setBounds(280, 1610, 105, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Lama :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(390, 1610, 40, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        FormInput.add(cmbLama);
        cmbLama.setBounds(436, 1610, 90, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        FormInput.add(PanelWall);
        PanelWall.setBounds(280, 1640, 540, 230);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Diagnosis Keperawatan :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(0, 1880, 180, 23);

        scrollPane3.setName("scrollPane3"); // NOI18N

        Tdiagnosis.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tdiagnosis.setColumns(20);
        Tdiagnosis.setRows(5);
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.setPreferredSize(new java.awt.Dimension(162, 200));
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        scrollPane3.setViewportView(Tdiagnosis);

        FormInput.add(scrollPane3);
        scrollPane3.setBounds(186, 1880, 670, 90);

        scrollPane4.setName("scrollPane4"); // NOI18N

        Ttindakan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Ttindakan.setColumns(20);
        Ttindakan.setRows(5);
        Ttindakan.setName("Ttindakan"); // NOI18N
        Ttindakan.setPreferredSize(new java.awt.Dimension(162, 200));
        Ttindakan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKeyPressed(evt);
            }
        });
        scrollPane4.setViewportView(Ttindakan);

        FormInput.add(scrollPane4);
        scrollPane4.setBounds(186, 1976, 670, 90);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("Tindakan Keperawatan Mandiri :");
        jLabel89.setName("jLabel89"); // NOI18N
        FormInput.add(jLabel89);
        jLabel89.setBounds(0, 1976, 180, 23);

        scrollPane5.setName("scrollPane5"); // NOI18N

        Tevaluasi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tevaluasi.setColumns(20);
        Tevaluasi.setRows(5);
        Tevaluasi.setName("Tevaluasi"); // NOI18N
        Tevaluasi.setPreferredSize(new java.awt.Dimension(162, 200));
        Tevaluasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TevaluasiKeyPressed(evt);
            }
        });
        scrollPane5.setViewportView(Tevaluasi);

        FormInput.add(scrollPane5);
        scrollPane5.setBounds(186, 2072, 670, 90);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("Evaluasi Keperawatan/ :");
        jLabel90.setName("jLabel90"); // NOI18N
        FormInput.add(jLabel90);
        jLabel90.setBounds(0, 2072, 180, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Tindak Lanjut   ");
        jLabel91.setName("jLabel91"); // NOI18N
        FormInput.add(jLabel91);
        jLabel91.setBounds(0, 2087, 180, 23);

        jLabel94.setForeground(new java.awt.Color(0, 0, 0));
        jLabel94.setText("IDENTIFIKASI, SELEKSI/SKRINING : ");
        jLabel94.setName("jLabel94"); // NOI18N
        FormInput.add(jLabel94);
        jLabel94.setBounds(0, 2169, 200, 23);

        chkIden1.setBackground(new java.awt.Color(242, 242, 242));
        chkIden1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden1.setText("Pasien dengan keterbatasan kognitif, ketergantungan aktifitas harian (hambatan mobilisasi)");
        chkIden1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden1.setName("chkIden1"); // NOI18N
        chkIden1.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden1);
        chkIden1.setBounds(30, 2189, 470, 23);

        chkIden2.setBackground(new java.awt.Color(242, 242, 242));
        chkIden2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden2.setText("Lansia dengan lebih dari 1 masalah kesehatan");
        chkIden2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden2.setName("chkIden2"); // NOI18N
        chkIden2.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden2);
        chkIden2.setBounds(30, 2219, 250, 23);

        chkIden3.setBackground(new java.awt.Color(242, 242, 242));
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
        chkIden3.setBounds(30, 2249, 350, 23);

        chkIden4.setBackground(new java.awt.Color(242, 242, 242));
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
        chkIden4.setBounds(30, 2279, 150, 23);

        chkIden5.setBackground(new java.awt.Color(242, 242, 242));
        chkIden5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden5.setText("Pasien dengan penyakit kronis, katastropik, terminal");
        chkIden5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden5.setName("chkIden5"); // NOI18N
        chkIden5.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden5);
        chkIden5.setBounds(30, 2309, 300, 23);

        chkIden6.setBackground(new java.awt.Color(242, 242, 242));
        chkIden6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden6.setText("Sering masuk IGD, readmisi Rumah Sakit");
        chkIden6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden6.setName("chkIden6"); // NOI18N
        chkIden6.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden6);
        chkIden6.setBounds(510, 2189, 310, 23);

        chkIden7.setBackground(new java.awt.Color(242, 242, 242));
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
        chkIden7.setBounds(510, 2219, 310, 23);

        chkIden8.setBackground(new java.awt.Color(242, 242, 242));
        chkIden8.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden8.setText("Pasien tanpa keluarga/terlantar, tinggal sendiri");
        chkIden8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden8.setName("chkIden8"); // NOI18N
        chkIden8.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden8);
        chkIden8.setBounds(510, 2249, 310, 23);

        chkIden9.setBackground(new java.awt.Color(242, 242, 242));
        chkIden9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIden9.setForeground(new java.awt.Color(0, 0, 0));
        chkIden9.setText("Kasus yang melebihi rata-rata lama dirawat");
        chkIden9.setBorderPainted(true);
        chkIden9.setBorderPaintedFlat(true);
        chkIden9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden9.setName("chkIden9"); // NOI18N
        chkIden9.setOpaque(false);
        chkIden9.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkIden9);
        chkIden9.setBounds(510, 2279, 310, 23);

        chkIden10.setBackground(new java.awt.Color(242, 242, 242));
        chkIden10.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkIden10.setText("Kasus yang membutuhkan kontinuitas pelayanan, rencana");
        chkIden10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIden10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIden10.setName("chkIden10"); // NOI18N
        chkIden10.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkIden10);
        chkIden10.setBounds(510, 2309, 310, 23);

        jLabel95.setForeground(new java.awt.Color(0, 0, 0));
        jLabel95.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel95.setText("pemulanganya penting/beresiko");
        jLabel95.setName("jLabel95"); // NOI18N
        FormInput.add(jLabel95);
        jLabel95.setBounds(528, 2324, 300, 20);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Memerlukan :");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(0, 2354, 100, 23);

        chkMPP.setBackground(new java.awt.Color(242, 242, 242));
        chkMPP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkMPP.setForeground(new java.awt.Color(0, 0, 0));
        chkMPP.setText("Manajer Pelayanan Pasien");
        chkMPP.setBorderPainted(true);
        chkMPP.setBorderPaintedFlat(true);
        chkMPP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMPP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMPP.setName("chkMPP"); // NOI18N
        chkMPP.setOpaque(false);
        chkMPP.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkMPP);
        chkMPP.setBounds(106, 2354, 150, 23);

        chkDP.setBackground(new java.awt.Color(242, 242, 242));
        chkDP.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        chkDP.setText("Discharge Planning");
        chkDP.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDP.setName("chkDP"); // NOI18N
        chkDP.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(chkDP);
        chkDP.setBounds(280, 2354, 120, 23);

        jLabel98.setForeground(new java.awt.Color(0, 0, 0));
        jLabel98.setText("Tgl. Verifikasi : ");
        jLabel98.setName("jLabel98"); // NOI18N
        FormInput.add(jLabel98);
        jLabel98.setBounds(408, 2354, 80, 20);

        TglVerif.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-12-2023 15:01:20" }));
        TglVerif.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TglVerif.setName("TglVerif"); // NOI18N
        TglVerif.setOpaque(false);
        FormInput.add(TglVerif);
        TglVerif.setBounds(491, 2354, 130, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama DPJP :");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 2384, 100, 23);

        KdDokter.setEditable(false);
        KdDokter.setForeground(new java.awt.Color(0, 0, 0));
        KdDokter.setName("KdDokter"); // NOI18N
        KdDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(KdDokter);
        KdDokter.setBounds(106, 2384, 210, 23);

        NmDokter.setEditable(false);
        NmDokter.setForeground(new java.awt.Color(0, 0, 0));
        NmDokter.setName("NmDokter"); // NOI18N
        NmDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NmDokter);
        NmDokter.setBounds(319, 2384, 470, 23);

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
        BtnDokter.setBounds(790, 2384, 28, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Nama Perawat :");
        jLabel35.setName("jLabel35"); // NOI18N
        FormInput.add(jLabel35);
        jLabel35.setBounds(0, 105, 110, 23);

        nm_perawat.setEditable(false);
        nm_perawat.setForeground(new java.awt.Color(0, 0, 0));
        nm_perawat.setName("nm_perawat"); // NOI18N
        nm_perawat.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(nm_perawat);
        nm_perawat.setBounds(116, 105, 479, 23);

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
        BtnPerawat.setBounds(600, 105, 28, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri :");
        jLabel100.setName("jLabel100"); // NOI18N
        FormInput.add(jLabel100);
        jLabel100.setBounds(0, 1640, 130, 23);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        FormInput.add(cmbSkala);
        cmbSkala.setBounds(136, 1640, 45, 23);

        jSeparator1.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator1.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(0, 2169, 880, 1);

        jSeparator2.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator2.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(0, 2348, 880, 1);

        jSeparator3.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator3.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator3.setName("jSeparator3"); // NOI18N
        FormInput.add(jSeparator3);
        jSeparator3.setBounds(0, 1410, 880, 1);

        jSeparator4.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator4.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator4.setName("jSeparator4"); // NOI18N
        FormInput.add(jSeparator4);
        jSeparator4.setBounds(0, 1180, 880, 1);

        jSeparator5.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator5.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator5.setName("jSeparator5"); // NOI18N
        FormInput.add(jSeparator5);
        jSeparator5.setBounds(0, 995, 880, 1);

        jSeparator6.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator6.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator6.setName("jSeparator6"); // NOI18N
        FormInput.add(jSeparator6);
        jSeparator6.setBounds(0, 415, 880, 1);

        jSeparator7.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator7.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator7.setName("jSeparator7"); // NOI18N
        FormInput.add(jSeparator7);
        jSeparator7.setBounds(0, 305, 880, 1);

        jSeparator8.setBackground(new java.awt.Color(239, 244, 234));
        jSeparator8.setForeground(new java.awt.Color(239, 244, 234));
        jSeparator8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)));
        jSeparator8.setName("jSeparator8"); // NOI18N
        FormInput.add(jSeparator8);
        jSeparator8.setBounds(0, 135, 880, 1);

        BtnKeluhan.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnKeluhan.setMnemonic('2');
        BtnKeluhan.setText("Template");
        BtnKeluhan.setToolTipText("Alt+2");
        BtnKeluhan.setName("BtnKeluhan"); // NOI18N
        BtnKeluhan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluhanActionPerformed(evt);
            }
        });
        FormInput.add(BtnKeluhan);
        BtnKeluhan.setBounds(860, 40, 100, 23);

        BtnSebutkan.setForeground(new java.awt.Color(0, 0, 0));
        BtnSebutkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSebutkan.setMnemonic('2');
        BtnSebutkan.setText("Template");
        BtnSebutkan.setToolTipText("Alt+2");
        BtnSebutkan.setName("BtnSebutkan"); // NOI18N
        BtnSebutkan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSebutkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSebutkanActionPerformed(evt);
            }
        });
        FormInput.add(BtnSebutkan);
        BtnSebutkan.setBounds(860, 275, 100, 23);

        BtnAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlergiObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlergiObat.setMnemonic('2');
        BtnAlergiObat.setText("Template");
        BtnAlergiObat.setToolTipText("Alt+2");
        BtnAlergiObat.setName("BtnAlergiObat"); // NOI18N
        BtnAlergiObat.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlergiObatActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlergiObat);
        BtnAlergiObat.setBounds(860, 1260, 100, 23);

        BtnAlergiMakanan.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlergiMakanan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlergiMakanan.setMnemonic('2');
        BtnAlergiMakanan.setText("Template");
        BtnAlergiMakanan.setToolTipText("Alt+2");
        BtnAlergiMakanan.setName("BtnAlergiMakanan"); // NOI18N
        BtnAlergiMakanan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlergiMakanan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlergiMakananActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlergiMakanan);
        BtnAlergiMakanan.setBounds(860, 1290, 100, 23);

        BtnAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        BtnAlergiLain.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnAlergiLain.setMnemonic('2');
        BtnAlergiLain.setText("Template");
        BtnAlergiLain.setToolTipText("Alt+2");
        BtnAlergiLain.setName("BtnAlergiLain"); // NOI18N
        BtnAlergiLain.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAlergiLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlergiLainActionPerformed(evt);
            }
        });
        FormInput.add(BtnAlergiLain);
        BtnAlergiLain.setBounds(860, 1320, 100, 23);

        BtnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        BtnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDiagnosa.setMnemonic('2');
        BtnDiagnosa.setText("Template");
        BtnDiagnosa.setToolTipText("Alt+2");
        BtnDiagnosa.setName("BtnDiagnosa"); // NOI18N
        BtnDiagnosa.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDiagnosaActionPerformed(evt);
            }
        });
        FormInput.add(BtnDiagnosa);
        BtnDiagnosa.setBounds(860, 1880, 100, 23);

        BtnTindakan.setForeground(new java.awt.Color(0, 0, 0));
        BtnTindakan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnTindakan.setMnemonic('2');
        BtnTindakan.setText("Template");
        BtnTindakan.setToolTipText("Alt+2");
        BtnTindakan.setName("BtnTindakan"); // NOI18N
        BtnTindakan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTindakan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTindakanActionPerformed(evt);
            }
        });
        FormInput.add(BtnTindakan);
        BtnTindakan.setBounds(860, 1976, 100, 23);

        BtnEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        BtnEvaluasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnEvaluasi.setMnemonic('2');
        BtnEvaluasi.setText("Template");
        BtnEvaluasi.setToolTipText("Alt+2");
        BtnEvaluasi.setName("BtnEvaluasi"); // NOI18N
        BtnEvaluasi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnEvaluasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEvaluasiActionPerformed(evt);
            }
        });
        FormInput.add(BtnEvaluasi);
        BtnEvaluasi.setBounds(860, 2072, 100, 23);

        Tradiation.setForeground(new java.awt.Color(0, 0, 0));
        Tradiation.setToolTipText("Alt+C");
        Tradiation.setName("Tradiation"); // NOI18N
        Tradiation.setPreferredSize(new java.awt.Dimension(140, 23));
        Tradiation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TradiationKeyPressed(evt);
            }
        });
        FormInput.add(Tradiation);
        Tradiation.setBounds(347, 1550, 510, 23);

        ScrollTriase1.setViewportView(FormInput);

        FormTriase.add(ScrollTriase1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Input Assesmen", FormTriase);

        internalFrame4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

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
        jLabel19.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-12-2023" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "27-12-2023" }));
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

        TabRawat.addTab("Data Assesmen", internalFrame4);

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
        } else if (nip.equals("")) {
            Valid.textKosong(nm_perawat, "Nama Perawat");
            BtnPerawat.requestFocus();
        } else if (cmbPencegahan.getSelectedIndex() == 2) {
            if (cmbTindakanCegah.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(null, "Utk. tindakan pecegahan resiko jatuh anak, pilihan pencegahan C\n"
                        + "tidak valid, silahkan ulangi lagi pilihannya dg. benar...!!!");
                cmbTindakanCegah.requestFocus();
            } else {
                simpan();
            }
        } else {
            simpan();
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
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bersangkutan..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else if (nm_perawat.getText().trim().equals("")) {
            Valid.textKosong(nm_perawat, "Nama Perawat");
        } else if (cmbPencegahan.getSelectedIndex() == 2) {
            if (cmbTindakanCegah.getSelectedIndex() == 3) {
                JOptionPane.showMessageDialog(null, "Utk. tindakan pecegahan resiko jatuh anak, pilihan pencegahan C\n"
                        + "tidak valid, silahkan ulangi lagi pilihannya dg. benar...!!!");
                cmbTindakanCegah.requestFocus();
            } else {
                if (tbAsesmen.getSelectedRow() > -1) {
                    if (akses.getkode().equals("Admin Utama")) {
                        ganti();
                    } else {
                        if (nip.equals(akses.getkode())) {
                            ganti();
                        } else {
                            JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bersangkutan..!!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
                }
            }
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                if (akses.getkode().equals("Admin Utama")) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bersangkutan..!!");
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
        WindowTemplate.dispose();
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
            
            if (ChkSkriningA.isSelected() == true) {
                param.put("jenisSkrining", ChkSkriningA.getText());
                param.put("kalimatSkrining", "1. Terdapat penurunan BB atau BB menetap (pada bayi < 1 tahun) selama >= 2 bulan                 Skor (" + skorGZ3.getText() + ")\n\n"
                        + "2. Terdapat tanda-tanda klinis gangguan gizi (tampak kurus, gemuk, pendek, edema, moon face,   Skor (" + skorGZ4.getText() + ")\n"
                        + "tampak tua, iga gambang, baggy pant, anoreksia) selama 1 bulan terakhir\n\n"
                        + "3. Terdapat salah satu penyakit/kondisi yg. beresiko mengakibatkan malnutrisi berikut :                 Skor (" + skorGZ5.getText() + ")\n"
                        + "* Diare berat (> 5x/hari) dan atau muntah (> 3x/hari)\n"
                        + "* Penurunan asupan makanan selama lebih dari 7 hari\n\n"
                        + "Terdapat penyakit-penyakit / keadaan yg. meningkatkan resiko malnutrisi antara lain :                   Skor (" + skorGZ6.getText() + ")\n"
                        + "* Diare kronik > 2 minggu                          * Penyakit hati/ginjal kronik\n"
                        + "* Penyakit jantung bawaan (tersangka)        * TB Paru\n"
                        + "* Infeksi HIV (tersangka)                            * Renca/paska operasimayor\n"
                        + "* Kelainan anatomi bawaan                         * Luka bakar luas\n"
                        + "* Kelainan metabolisme bawaan                  * Terpasang stoma\n"
                        + "* Retardasi mental                                     * Trauma\n"
                        + "* Keterlambatan perkembangan                  * Lain-lain : " + lainGZanak.getText() + "\n"
                        + "* Kanker (tersangka)\n"
                        + "_______________________________________________________________________\n"
                        + "Total Skor : (" + TotSkorGZA.getText() + ")\n"
                        + "Kesimpulan Skrining Gizi Anak :\n"
                        + kesimpulanGZanak.getText() + "\n");
                param.put("resikoJatuh", "Anak (Skala Humpty Dumpty)");
                param.put("tindakanRJ", "ANAK");
                if (TabPencegahanAnak.getSelectedIndex() == 0) {
                    param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                    param.put("IsitindakanRJ", anakA.getText());
                } else if (TabPencegahanAnak.getSelectedIndex() == 1) {
                    param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (B)");
                    param.put("IsitindakanRJ", anakB.getText());
                }
                
            } else if (ChkSkriningD.isSelected() == true) {
                param.put("jenisSkrining", ChkSkriningD.getText());
                param.put("kalimatSkrining", "1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?\n"
                        + cmbDewasaGZ1.getSelectedItem().toString() + "   Skor (" + skorGZ1.getText() + ")\n"
                        + cmbDewasaYaGZ1.getSelectedItem().toString() + "   Skor (" + skorYaGZ1.getText() + ")\n\n"
                        + "2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?\n"
                        + cmbDewasaGZ2.getSelectedItem().toString() + "   Skor (" + skorGZ2.getText() + ")\n"
                        + "_______________________________________________________________________\n"
                        + "Total Skor : (" + TotSkorGZD.getText() + ")\n"
                        + "Kesimpulan Skrining Gizi Dewasa :\n"
                        + kesimpulanGZDewasa.getText() + "\n");
                param.put("resikoJatuh", "Dewasa (Skala Morse)");
                param.put("tindakanRJ", "DEWASA");
                if (TabPencegahanDewasa.getSelectedIndex() == 0) {
                    param.put("JudultindakanRJ", "Pencegahan Umum (A)");
                    param.put("IsitindakanRJ", dewasaA.getText());
                } else if (TabPencegahanDewasa.getSelectedIndex() == 1) {
                    param.put("JudultindakanRJ", "Pencegahan Resiko Sedang (B)");
                    param.put("IsitindakanRJ", dewasaB.getText());
                } else if (TabPencegahanDewasa.getSelectedIndex() == 2) {
                    param.put("JudultindakanRJ", "Pencegahan Resiko Tinggi (C)");
                    param.put("IsitindakanRJ", dewasaC.getText());
                } 
                
            } else if (ChkSkriningA.isSelected() == false && ChkSkriningD.isSelected() == false) {
                param.put("jenisSkrining", "");
                param.put("kalimatSkrining", "");
                param.put("resikoJatuh", "");
                param.put("tindakanRJ", "");
                param.put("JudultindakanRJ", "");
                param.put("IsitindakanRJ", "");
            }

            //faktor resiko
            try {
                faktorresikoigd = "";
                ps4 = koneksi.prepareStatement("select m.kode_resiko, concat('Faktor : ',m.faktor_resiko,', Skala : ',m.skala,', Skor (',m.skor,')') resiko "
                        + "FROM master_faktor_resiko_igd m INNER JOIN penilaian_awal_keperawatan_igd_resiko pm ON pm.kode_resiko = m.kode_resiko "
                        + "WHERE pm.no_rawat=? ORDER BY pm.kode_resiko");
                try {
                    ps4.setString(1, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                    rs4 = ps4.executeQuery();
                    while (rs4.next()) {
                        faktorresikoigd = rs4.getString("resiko") + "\n" + faktorresikoigd;
                    }
                    
                    if (faktorresikoigd.endsWith("\n")) {
                        faktorresikoigd = faktorresikoigd.substring(0, faktorresikoigd.length() - 1);
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
            
            if (ChkSkriningA.isSelected() == false && ChkSkriningD.isSelected() == false) {
                param.put("dataResiko", "");
                param.put("TotSkorResikoJatuh", "");
                param.put("KesResikoJatuh", "");
            } else {
                param.put("dataResiko", faktorresikoigd);
                param.put("TotSkorResikoJatuh", "Total Skor : (" + TotSkorRJ.getText() + ")");
                param.put("KesResikoJatuh", "Kesimpulan Skor Resiko Jatuh :\n" + kesimpulanResikoJatuh.getText());
            }
            
            Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD1.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 2 ]::",
                    "SELECT if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                    + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                    + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.skala_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, "
                    + "if(pa.identifikasi1='ya','V','') iden1, if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, "
                    + "if(pa.identifikasi6='ya','V','') iden6, if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                    + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp, concat('Tanggal, ',date_format(pa.tgl_verifikasi,'%d-%m-%Y'),'   Jam ',TIME_FORMAT(pa.tgl_verifikasi,'%H:%i:%S')) tglverif, "
                    + "pg1.nama dr_dpjp, pg2.nama perawat from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg1 on pg1.nik=pa.nip_dpjp inner join pegawai pg2 on pg2.nik=pa.nip_perawat where "
                    + "pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);
            
            Valid.MyReport("rptCetakPenilaianAwalKeperawatanIGD.jasper", "report", "::[ Laporan Penilaian Awal Keperawatan IGD hal. 1 ]::",
                    "SELECT p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgllhr, concat('Tanggal : ', date_format(pa.tanggal,'%d-%m-%Y'),', Pukul : ',time_format(pa.tanggal,'%H:%i-%S')) tgl, "
                    + "pa.keluhan_utama, pa.td tensi, pa.nadi, pa.nafas, pa.suhu, pa.bb, pa.tb, pa.asesmen_psikologis psikologis, if(pa.masalah_perilaku='Ada',concat(pa.masalah_perilaku,', Sebutkan : ',pa.sebutkan_perilaku),pa.masalah_perilaku) perilaku, "
                    + "p.stts_nikah, pa.hubungan_pasien hubungan, if(pa.tempat_tinggal='Lainnya',concat(pa.tempat_tinggal,', ',pa.lainya_tempt_tgl),pa.tempat_tinggal) tmpttgl, p.pekerjaan, "
                    + "pa.alat_bantu, if(pa.cacat_tubuh='Ada',concat(pa.cacat_tubuh,', ',pa.ada_cacat_tubuh),pa.cacat_tubuh) cacat, "
                    + "pa.riwayat_alergi, if(pa.alergi_obat='ya','V','') aler_obat, if(pa.alergi_obat='ya',pa.reaksi_alergi_obat,'') reak_obat, if(pa.alergi_makanan='ya','V','') aler_mak, "
                    + "if(pa.alergi_makanan='ya',pa.reaksi_alergi_makanan,'') reak_mak, if(pa.alergi_lainnya='ya','V','') aler_lain, if(pa.alergi_lainnya='ya',pa.reaksi_alergi_lainnya,'') reak_lain, "
                    + "if(pa.pin_kancing='ya','V','') pin, pa.alergi_diberitahukan, if(pa.nyeri='Ya',concat(pa.nyeri,', Lokasi : ',pa.ya_nyeri_lokasi),pa.nyeri) nyeri, pa.jenis_nyeri, "
                    + "if(pa.provocation='Lainnya',concat(pa.provocation,', ',pa.provocation_lain),pa.provocation) provokes, if(pa.quality='Lainnya',concat(pa.quality,', ',pa.quality_lain),pa.quality) quality, "
                    + "pa.radiation radiasi, pa.severity severity_nyeri, concat(pa.time,', Lama : ',pa.time_lama) time_nyeri, pa.diagnosa_keperawatan, pa.tindakan_keperawatan, pa.evaluasi_keperawatan, if(pa.identifikasi1='ya','V','') iden1, "
                    + "if(pa.identifikasi2='ya','V','') iden2, if(pa.identifikasi3='ya','V','') iden3, if(pa.identifikasi4='ya','V','') iden4, if(pa.identifikasi5='ya','V','') iden5, if(pa.identifikasi6='ya','V','') iden6, "
                    + "if(pa.identifikasi7='ya','V','') iden7, if(pa.identifikasi8='ya','V','') iden8, if(pa.identifikasi9='ya','V','') iden9, if(pa.identifikasi10='ya','V','') iden10, "
                    + "if(pa.manajer_pelayanan='ya','V','') mpp, if(pa.discharge_planing='ya','V','') dp from penilaian_awal_keperawatan_igdrz pa "
                    + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                    + "pa.no_rawat='" + tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString() + "'", param);
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
        tampil();
        
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRw.getText() + "'") > 0) {
            TabRawat.setSelectedIndex(1);
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_igdrz where no_rawat='" + TNoRw.getText() + "'") == 0) {
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

    private void TkeluhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkeluhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            td.requestFocus();
        }
    }//GEN-LAST:event_TkeluhanKeyPressed

    private void tdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tdKeyPressed
        Valid.pindah(evt, Tkeluhan, nadi);
    }//GEN-LAST:event_tdKeyPressed

    private void nadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nadiKeyPressed
        Valid.pindah(evt, td, napas);
    }//GEN-LAST:event_nadiKeyPressed

    private void napasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_napasKeyPressed
        Valid.pindah(evt, nadi, suhu);
    }//GEN-LAST:event_napasKeyPressed

    private void suhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suhuKeyPressed
        Valid.pindah(evt, napas, bb);
    }//GEN-LAST:event_suhuKeyPressed

    private void bbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bbKeyPressed
        Valid.pindah(evt, suhu, tb);
    }//GEN-LAST:event_bbKeyPressed

    private void tbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKeyPressed
        Valid.pindah(evt, bb, cmbAsesmen);
    }//GEN-LAST:event_tbKeyPressed

    private void cmbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAsesmenKeyPressed
        Valid.pindah(evt, tb, cmbPerilaku);
    }//GEN-LAST:event_cmbAsesmenKeyPressed

    private void cmbPerilakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbPerilakuKeyPressed
        Valid.pindah(evt, cmbAsesmen, Tsebutkan);
    }//GEN-LAST:event_cmbPerilakuKeyPressed

    private void TsebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsebutkanKeyPressed
        Valid.pindah(evt, cmbPerilaku, cmbHub_pasien);
    }//GEN-LAST:event_TsebutkanKeyPressed

    private void cmbHub_pasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbHub_pasienKeyPressed
        Valid.pindah(evt, Tsebutkan, cmbTempat_tgl);
    }//GEN-LAST:event_cmbHub_pasienKeyPressed

    private void cmbTempat_tglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbTempat_tglKeyPressed
        Valid.pindah(evt, cmbTempat_tgl, Ttmpt_tgl_lain);
    }//GEN-LAST:event_cmbTempat_tglKeyPressed

    private void cmbPerilakuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerilakuActionPerformed
        if (cmbPerilaku.getSelectedIndex() == 2) {
            Tsebutkan.setEnabled(true);
            BtnSebutkan.setEnabled(true);
            Tsebutkan.setText("");
            Tsebutkan.requestFocus();
        } else {
            BtnSebutkan.setEnabled(false);
            Tsebutkan.setEnabled(false);
            Tsebutkan.setText("");
        }
    }//GEN-LAST:event_cmbPerilakuActionPerformed

    private void cmbTempat_tglActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTempat_tglActionPerformed
        if (cmbTempat_tgl.getSelectedIndex() == 3) {
            Ttmpt_tgl_lain.setEnabled(true);
            Ttmpt_tgl_lain.setText("");
            Ttmpt_tgl_lain.requestFocus();
        } else {
            Ttmpt_tgl_lain.setEnabled(false);
            Ttmpt_tgl_lain.setText("");
        }
    }//GEN-LAST:event_cmbTempat_tglActionPerformed

    private void ChkSkriningDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSkriningDActionPerformed
        if (ChkSkriningD.isSelected() == true) {
            cmbDewasaGZ1.setEnabled(true);
            cmbDewasaGZ2.setEnabled(true);
            cmbDewasaGZ1.requestFocus();

            cmbDewasaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setEnabled(false);
            skorGZ1.setText("0");
            skorYaGZ1.setText("0");
            cmbDewasaGZ2.setSelectedIndex(0);
            skorGZ2.setText("0");
            TotSkorGZD.setText("0");

            ChkGZanak1.setEnabled(false);
            ChkGZanak2.setEnabled(false);
            ChkGZanak3.setEnabled(false);
            ChkGZanak4.setEnabled(false);
            lainGZanak.setEditable(false);

            ChkGZanak1.setSelected(false);
            ChkGZanak2.setSelected(false);
            ChkGZanak3.setSelected(false);
            ChkGZanak4.setSelected(false);
            skorGZ3.setText("0");
            skorGZ4.setText("0");
            skorGZ5.setText("0");
            skorGZ6.setText("0");
            lainGZanak.setText("");
            TotSkorGZA.setText("0");
            hitungSkorGZ();
            TotSkorRJ.setText("0");
            kesimpulanResikoJatuh.setText("");
            TCariResiko.setText("");
            tampilFaktorResiko();
            hitungSkorRJ();
            TabTindakanPencegahan.setSelectedIndex(0);
            TabPencegahanDewasa.setSelectedIndex(0);
            cmbPencegahan.setSelectedIndex(1);
            cmbTindakanCegah.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkSkriningDActionPerformed

    private void ChkSkriningAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSkriningAActionPerformed
        if (ChkSkriningA.isSelected() == true) {
            cmbDewasaGZ1.setEnabled(false);
            cmbDewasaGZ2.setEnabled(false);

            cmbDewasaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setSelectedIndex(0);
            cmbDewasaYaGZ1.setEnabled(false);
            skorGZ1.setText("0");
            skorYaGZ1.setText("0");
            cmbDewasaGZ2.setSelectedIndex(0);
            skorGZ2.setText("0");
            TotSkorGZD.setText("0");

            ChkGZanak1.setEnabled(true);
            ChkGZanak2.setEnabled(true);
            ChkGZanak3.setEnabled(true);
            ChkGZanak4.setEnabled(true);
            lainGZanak.setEditable(true);
            ChkGZanak1.requestFocus();

            ChkGZanak1.setSelected(false);
            ChkGZanak2.setSelected(false);
            ChkGZanak3.setSelected(false);
            ChkGZanak4.setSelected(false);
            skorGZ3.setText("0");
            skorGZ4.setText("0");
            skorGZ5.setText("0");
            skorGZ6.setText("0");
            lainGZanak.setText("");
            TotSkorGZA.setText("0");
            hitungSkorGZ();
            TotSkorRJ.setText("0");
            kesimpulanResikoJatuh.setText("");
            TCariResiko.setText("");
            tampilFaktorResiko();
            hitungSkorRJ();
            TabTindakanPencegahan.setSelectedIndex(1);
            TabPencegahanAnak.setSelectedIndex(0);
            cmbPencegahan.setSelectedIndex(2);
            cmbTindakanCegah.setSelectedIndex(0);
        }
    }//GEN-LAST:event_ChkSkriningAActionPerformed

    private void cmbDewasaGZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaGZ1ActionPerformed
        cmbDewasaYaGZ1.setSelectedIndex(0);
        skorYaGZ1.setText("0");

        if (cmbDewasaGZ1.getSelectedIndex() == 0) {
            skorGZ1.setText("0");
            cmbDewasaYaGZ1.setEnabled(false);
        } else if (cmbDewasaGZ1.getSelectedIndex() == 1) {
            skorGZ1.setText("1");
            cmbDewasaYaGZ1.setEnabled(false);
        } else if (cmbDewasaGZ1.getSelectedIndex() == 2) {
            skorGZ1.setText("0");
            cmbDewasaYaGZ1.setEnabled(true);
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaGZ1ActionPerformed

    private void cmbDewasaYaGZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaYaGZ1ActionPerformed
        if (cmbDewasaYaGZ1.getSelectedIndex() == 0) {
            skorYaGZ1.setText("0");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 1) {
            skorYaGZ1.setText("1");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 2) {
            skorYaGZ1.setText("2");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 3) {
            skorYaGZ1.setText("3");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 4) {
            skorYaGZ1.setText("4");
        } else if (cmbDewasaYaGZ1.getSelectedIndex() == 5) {
            skorYaGZ1.setText("2");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaYaGZ1ActionPerformed

    private void cmbDewasaGZ2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDewasaGZ2ActionPerformed
        if (cmbDewasaGZ2.getSelectedIndex() == 0) {
            skorGZ2.setText("0");
        } else if (cmbDewasaGZ2.getSelectedIndex() == 1) {
            skorGZ2.setText("1");
        }
        hitungSkorGZ();
    }//GEN-LAST:event_cmbDewasaGZ2ActionPerformed

    private void ChkGZanak3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak3ActionPerformed
        if (ChkSkriningA.isSelected() == true) {
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak3ActionPerformed

    private void ChkGZanak2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak2ActionPerformed
        if (ChkSkriningA.isSelected() == true) {
            if (ChkGZanak2.isSelected() == true) {
                skorGZ4.setText("1");
            } else {
                skorGZ4.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak2ActionPerformed

    private void jLabel136MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel136MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak3.setSelected(true);
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel136MouseClicked

    private void malnutrisiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_malnutrisiMouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak4.setSelected(true);
            if (ChkGZanak4.isSelected() == true) {
                skorGZ6.setText("2");
            } else {
                skorGZ6.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_malnutrisiMouseClicked

    private void jLabel93MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel93MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak2.setSelected(true);
            if (ChkGZanak2.isSelected() == true) {
                skorGZ4.setText("1");
            } else {
                skorGZ4.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel93MouseClicked

    private void jLabel54MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel54MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak2.setSelected(true);
            if (ChkGZanak2.isSelected() == true) {
                skorGZ4.setText("1");
            } else {
                skorGZ4.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel54MouseClicked

    private void jLabel134MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel134MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak3.setSelected(true);
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel134MouseClicked

    private void ChkGZanak1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak1ActionPerformed
        if (ChkSkriningA.isSelected() == true) {
            if (ChkGZanak1.isSelected() == true) {
                skorGZ3.setText("1");
            } else {
                skorGZ3.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak1.setSelected(false);
            skorGZ3.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak1ActionPerformed

    private void ChkGZanak4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkGZanak4ActionPerformed
        if (ChkSkriningA.isSelected() == true) {
            if (ChkGZanak4.isSelected() == true) {
                skorGZ6.setText("2");
            } else {
                skorGZ6.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_ChkGZanak4ActionPerformed

    private void jLabel92MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel92MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak2.setSelected(true);
            if (ChkGZanak2.isSelected() == true) {
                skorGZ4.setText("1");
            } else {
                skorGZ4.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel92MouseClicked

    private void jLabel137MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel137MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak4.setSelected(true);
            if (ChkGZanak4.isSelected() == true) {
                skorGZ6.setText("2");
            } else {
                skorGZ6.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel137MouseClicked

    private void jLabel51MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel51MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak1.setSelected(true);
            if (ChkGZanak1.isSelected() == true) {
                skorGZ3.setText("1");
            } else {
                skorGZ3.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak1.setSelected(false);
            skorGZ3.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel51MouseClicked

    private void jLabel135MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel135MouseClicked
        if (ChkSkriningA.isSelected() == true) {
            ChkGZanak3.setSelected(true);
            if (ChkGZanak3.isSelected() == true) {
                skorGZ5.setText("1");
            } else {
                skorGZ5.setText("0");
            }
            hitungSkorGZ();
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
            hitungSkorGZ();
        }
    }//GEN-LAST:event_jLabel135MouseClicked

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

    private void Tsebutkan_cacatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tsebutkan_cacatKeyPressed
        Valid.pindah(evt, cmbCacat, cmbRiwayat);
    }//GEN-LAST:event_Tsebutkan_cacatKeyPressed

    private void cmbCacatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCacatActionPerformed
        if (cmbCacat.getSelectedIndex() == 2) {
            Tsebutkan_cacat.setEnabled(true);
            Tsebutkan_cacat.setText("");
            Tsebutkan_cacat.requestFocus();
        } else {
            Tsebutkan_cacat.setEnabled(false);
            Tsebutkan_cacat.setText("");
        }
    }//GEN-LAST:event_cmbCacatActionPerformed

    private void chkAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiObatActionPerformed
        if (chkAlergiObat.isSelected() == true) {
            reaksiAlergiObat.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiObatActionPerformed

    private void chkAlergiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiMakananActionPerformed
        if (chkAlergiMakanan.isSelected() == true) {
            reaksiAlergiMakanan.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiMakananActionPerformed

    private void chkAlergiLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAlergiLainActionPerformed
        if (chkAlergiLain.isSelected() == true) {
            reaksiAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_chkAlergiLainActionPerformed

    private void TnyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnyeriKeyPressed
        Valid.pindah(evt, cmbNyeri, cmbJenis);
    }//GEN-LAST:event_TnyeriKeyPressed

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tnyeri.setEnabled(true);
            Tnyeri.setText("");
            Tnyeri.requestFocus();
        } else {
            Tnyeri.setEnabled(false);
            Tnyeri.setText("");
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void TprovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprovoKeyPressed
        Valid.pindah(evt, cmbProvo, cmbQuality);
    }//GEN-LAST:event_TprovoKeyPressed

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

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Ttindakan.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TtindakanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tevaluasi.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKeyPressed

    private void TevaluasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TevaluasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            chkIden1.requestFocus();
        }
    }//GEN-LAST:event_TevaluasiKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        akses.setform("RMPenilaianAwalKeperawatanIGDrz");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMPenilaianAwalKeperawatanIGDrz");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnKeluhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluhanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Keluhan Utama ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnKeluhanActionPerformed

    private void BtnSebutkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSebutkanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Sebutkan Masalah Perilaku ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnSebutkanActionPerformed

    private void BtnAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlergiObatActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 3;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alergi Obat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlergiObatActionPerformed

    private void BtnAlergiMakananActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlergiMakananActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 4;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alergi Makanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlergiMakananActionPerformed

    private void BtnAlergiLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlergiLainActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 5;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Alergi Lainnya ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnAlergiLainActionPerformed

    private void BtnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDiagnosaActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 6;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Diagnosis Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDiagnosaActionPerformed

    private void BtnTindakanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTindakanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 7;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Tindakan Keperawatan Mandiri ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnTindakanActionPerformed

    private void BtnEvaluasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEvaluasiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 8;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Evaluasi Keperawatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, 325);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnEvaluasiActionPerformed

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

    private void MnHasilPemeriksaanLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanLabActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.cariInteger("select count(1) cek from lis_reg where no_rawat='" + TNoRw.getText() + "'") == 0) {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan laboratorium (LIS) tidak ditemukan ...!!!!");
            } else {
                DlgHasilLIS lis = new DlgHasilLIS(null, false);
                lis.setSize(914, internalFrame1.getHeight() - 40);
                lis.setLocationRelativeTo(internalFrame1);
                lis.setData(TNoRw.getText(), TPasien.getText(), TNoRM.getText());
                lis.setVisible(true);
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanLabActionPerformed

    private void MnHasilPemeriksaanRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHasilPemeriksaanRadActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekHasilRad = 0;
            kdItemrad = "";
            itemDipilih = "";
            tglRad = "";
            jamRad = "";

            tglRad = Sequel.cariIsi("select tgl_periksa from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            jamRad = Sequel.cariIsi("select jam from periksa_radiologi where no_rawat='" + TNoRw.getText() + "'");
            kdItemrad = Sequel.cariIsi("select kd_jenis_prw from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");
            itemDipilih = Sequel.cariIsi("select nm_perawatan from jns_perawatan_radiologi where kd_jenis_prw='" + kdItemrad + "'");
            cekHasilRad = Sequel.cariInteger("select count(-1) from periksa_radiologi where no_rawat='" + TNoRw.getText() + "' and "
                + "tgl_periksa='" + tglRad + "' and jam='" + jamRad + "'");

            if (cekHasilRad >= 1) {
                akses.setform("RMPenilaianAwalKeperawatanIGDrz");
                DlgCariPeriksaRadiologi form = new DlgCariPeriksaRadiologi(null, false);
                form.WindowHasil.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
                form.WindowHasil.setLocationRelativeTo(internalFrame1);
                form.isCek();
                form.setData(TNoRw.getText(), kdItemrad, itemDipilih, "OK", TNoRM.getText(), TPasien.getText(), tglRad, jamRad,
                    Sequel.cariIsi("select ifnull(diagnosa,'-') from pemeriksaan_ralan_petugas where no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select pl.nm_poli from poliklinik pl inner join reg_periksa rp on pl.kd_poli=rp.kd_poli where rp.no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.kd_dokter where pr.no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select p.nama from periksa_radiologi pr inner join pegawai p on p.nik=pr.dokter_perujuk where pr.no_rawat='" + TNoRw.getText() + "'"),
                    Sequel.cariIsi("select concat('(',UPPER(tipe_faskes),') ',nama_rujukan) from master_nama_rujukan where "
                        + "kd_rujukan='" + Sequel.cariIsi("select ifnull(kd_rujukan,'') from rujuk_masuk where no_rawat='" + TNoRw.getText() + "'") + "'"));
                form.WindowHasil.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Hasil pemeriksaan radiologi (expertise) tidak ditemukan ...!!!!");
            }
        }
    }//GEN-LAST:event_MnHasilPemeriksaanRadActionPerformed

    private void TradiationKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TradiationKeyPressed
        Valid.pindah(evt, cmbRadia, cmbSevere);
    }//GEN-LAST:event_TradiationKeyPressed

    private void cmbRadiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRadiaActionPerformed
        if (cmbRadia.getSelectedIndex() == 1) {
            Tradiation.setText("");
            Tradiation.setEnabled(true);
            Tradiation.requestFocus();
        } else {
            Tradiation.setText("");
            Tradiation.setEnabled(false);
        }
    }//GEN-LAST:event_cmbRadiaActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMPenilaianAwalKeperawatanIGDrz");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPenilaianAwalKeperawatanIGDrz dialog = new RMPenilaianAwalKeperawatanIGDrz(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAlergiLain;
    private widget.Button BtnAlergiMakanan;
    private widget.Button BtnAlergiObat;
    private widget.Button BtnAll;
    private widget.Button BtnAllResiko;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCariResiko;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDiagnosa;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnEvaluasi;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluhan;
    private widget.Button BtnNotepad;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSebutkan;
    private widget.Button BtnSimpan;
    private widget.Button BtnTindakan;
    public widget.CekBox ChkGZanak1;
    public widget.CekBox ChkGZanak2;
    public widget.CekBox ChkGZanak3;
    public widget.CekBox ChkGZanak4;
    private widget.RadioButton ChkSkriningA;
    private widget.RadioButton ChkSkriningD;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.InternalFrame FormTriase;
    private widget.TextBox KdDokter;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHasilPemeriksaanLab;
    private javax.swing.JMenuItem MnHasilPemeriksaanRad;
    private widget.TextBox NmDokter;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane ScrollTriase1;
    private widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TCariResiko;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabPencegahanAnak;
    private javax.swing.JTabbedPane TabPencegahanDewasa;
    private javax.swing.JTabbedPane TabRawat;
    private javax.swing.JTabbedPane TabTindakanPencegahan;
    private widget.TextArea Tdiagnosis;
    private widget.TextArea Tevaluasi;
    private widget.Tanggal TglVerif;
    private widget.TextArea Tkeluhan;
    private widget.TextBox Tnyeri;
    private widget.TextBox TotSkorGZA;
    private widget.TextBox TotSkorGZD;
    private widget.TextBox TotSkorRJ;
    private widget.TextBox Tpekerjaan;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox Tradiation;
    private widget.TextBox Tsebutkan;
    private widget.TextBox Tsebutkan_cacat;
    private widget.TextBox Tstts_nikah;
    private widget.TextArea Ttemplate;
    private widget.TextBox Ttgl_lahir;
    private widget.TextArea Ttindakan;
    private widget.TextBox Ttmpt_tgl_lain;
    private javax.swing.JDialog WindowTemplate;
    private widget.TextArea anakA;
    private widget.TextArea anakB;
    private widget.TextBox bb;
    private javax.swing.ButtonGroup buttonGroup1;
    public widget.CekBox chkAlergiLain;
    public widget.CekBox chkAlergiMakanan;
    public widget.CekBox chkAlergiObat;
    private widget.RadioButton chkDP;
    private widget.RadioButton chkIden1;
    private widget.RadioButton chkIden10;
    private widget.RadioButton chkIden2;
    public widget.CekBox chkIden3;
    public widget.CekBox chkIden4;
    private widget.RadioButton chkIden5;
    private widget.RadioButton chkIden6;
    public widget.CekBox chkIden7;
    private widget.RadioButton chkIden8;
    public widget.CekBox chkIden9;
    public widget.CekBox chkMPP;
    public widget.CekBox chkPin;
    private widget.ComboBox cmbAlatBantu;
    private widget.ComboBox cmbAsesmen;
    private widget.ComboBox cmbCacat;
    private widget.ComboBox cmbDewasaGZ1;
    private widget.ComboBox cmbDewasaGZ2;
    private widget.ComboBox cmbDewasaYaGZ1;
    private widget.ComboBox cmbHub_pasien;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbPencegahan;
    private widget.ComboBox cmbPerilaku;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbRiwayat;
    private widget.ComboBox cmbSevere;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbTempat_tgl;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbTindakanCegah;
    private widget.ComboBox cmbdiberitahukan;
    private widget.TextArea dewasaA;
    private widget.TextArea dewasaB;
    private widget.TextArea dewasaC;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel100;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel134;
    private widget.Label jLabel135;
    private widget.Label jLabel136;
    private widget.Label jLabel137;
    private widget.Label jLabel138;
    private widget.Label jLabel139;
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
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel5;
    private widget.Label jLabel51;
    private widget.Label jLabel54;
    private widget.Label jLabel6;
    private widget.Label jLabel61;
    private widget.Label jLabel62;
    private widget.Label jLabel65;
    private widget.Label jLabel66;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private widget.TextArea kesimpulanGZDewasa;
    private widget.TextArea kesimpulanGZanak;
    private widget.TextArea kesimpulanResikoJatuh;
    private widget.Label label12;
    private widget.TextBox lainGZanak;
    private widget.TextArea malnutrisi;
    private widget.TextBox nadi;
    private widget.TextBox napas;
    private widget.TextBox nm_perawat;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa14;
    private widget.PanelBiasa panelBiasa15;
    private widget.PanelBiasa panelBiasa6;
    private widget.PanelBiasa panelBiasa7;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi4;
    private widget.TextBox reaksiAlergiLain;
    private widget.TextBox reaksiAlergiMakanan;
    private widget.TextBox reaksiAlergiObat;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane7;
    private widget.TextBox skorGZ1;
    private widget.TextBox skorGZ2;
    private widget.TextBox skorGZ3;
    private widget.TextBox skorGZ4;
    private widget.TextBox skorGZ5;
    private widget.TextBox skorGZ6;
    private widget.TextBox skorYaGZ1;
    private widget.TextBox suhu;
    private widget.TextBox tb;
    private widget.Table tbAsesmen;
    private widget.Table tbFaktorResiko;
    private widget.Table tbTemplate;
    private widget.TextBox td;
    private widget.Tanggal tgl_asesmen;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select pa.no_rawat, p.no_rkm_medis, p.nm_pasien, date_format(p.tgl_lahir,'%d-%m-%Y') tgl_lahir, "
                    + "date_format(pa.tanggal,'%d-%m-%Y %H:%i:%s') tgl_asesmen, pg1.nama perawat, pg2.nama dpjp, p.stts_nikah, p.pekerjaan, "
                    + "pa.* from penilaian_awal_keperawatan_igdrz pa inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg1 on pg1.nik=pa.nip_perawat "
                    + "inner join pegawai pg2 on pg2.nik=pa.nip_dpjp where "
                    + "date(pa.tanggal) between ? and ? and pa.no_rawat like ? or "
                    + "date(pa.tanggal) between ? and ? and p.no_rkm_medis like ? or "
                    + "date(pa.tanggal) between ? and ? and p.nm_pasien like ? or "
                    + "date(pa.tanggal) between ? and ? and pa.keluhan_utama like ? or "
                    + "date(pa.tanggal) between ? and ? and pg1.nama like ? or "
                    + "date(pa.tanggal) between ? and ? and pg2.nama like ? order by pa.tanggal desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl_lahir"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("keluhan_utama"),
                        rs.getString("td"),
                        rs.getString("nadi"),
                        rs.getString("nafas"),
                        rs.getString("suhu"),
                        rs.getString("bb"),
                        rs.getString("tb"),
                        rs.getString("asesmen_psikologis"),
                        rs.getString("masalah_perilaku"),
                        rs.getString("sebutkan_perilaku"),
                        rs.getString("hubungan_pasien"),
                        rs.getString("tempat_tinggal"),
                        rs.getString("lainya_tempt_tgl"),
                        rs.getString("skrining_gizi"),
                        rs.getString("gizi_dewasa1"),
                        rs.getString("gizi_dewasa1ya"),
                        rs.getString("gizi_dewasa2"),
                        rs.getString("gizi_anak1"),
                        rs.getString("gizi_anak2"),
                        rs.getString("gizi_anak3"),
                        rs.getString("gizi_anak_penyakit"),
                        rs.getString("gizi_anak_penyakit_lain"),
                        rs.getString("cegah_resiko_jatuh"),
                        rs.getString("tindakan_pencegahan"),
                        rs.getString("alat_bantu"),
                        rs.getString("cacat_tubuh"),
                        rs.getString("ada_cacat_tubuh"),
                        rs.getString("riwayat_alergi"),
                        rs.getString("alergi_obat"),
                        rs.getString("reaksi_alergi_obat"),
                        rs.getString("alergi_makanan"),
                        rs.getString("reaksi_alergi_makanan"),
                        rs.getString("alergi_lainnya"),
                        rs.getString("reaksi_alergi_lainnya"),
                        rs.getString("pin_kancing"),
                        rs.getString("alergi_diberitahukan"),
                        rs.getString("nyeri"),
                        rs.getString("ya_nyeri_lokasi"),
                        rs.getString("jenis_nyeri"),
                        rs.getString("provocation"),
                        rs.getString("provocation_lain"),
                        rs.getString("quality"),
                        rs.getString("quality_lain"),
                        rs.getString("radiation"),
                        rs.getString("severity"),
                        rs.getString("time"),
                        rs.getString("time_lama"),
                        rs.getString("diagnosa_keperawatan"),
                        rs.getString("tindakan_keperawatan"),
                        rs.getString("evaluasi_keperawatan"),
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
                        rs.getString("tgl_verifikasi"),
                        rs.getString("nip_dpjp"),
                        rs.getString("nip_perawat"),
                        rs.getString("tanggal"),
                        rs.getString("perawat"),
                        rs.getString("dpjp"),
                        rs.getString("stts_nikah"),
                        rs.getString("pekerjaan"),
                        rs.getString("skala_nyeri"),
                        rs.getString("ket_radiation")
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
        tgl_asesmen.setDate(new Date());
        nip = "";
        nm_perawat.setText("");
        Tkeluhan.setText("");
        td.setText("");
        nadi.setText("");
        napas.setText("");
        suhu.setText("");
        bb.setText("");
        tb.setText("");
        cmbAsesmen.setSelectedIndex(0);
        cmbPerilaku.setSelectedIndex(0);
        Tsebutkan.setText("");
        Tsebutkan.setEnabled(false);
        Tstts_nikah.setText("");
        cmbHub_pasien.setSelectedIndex(0);
        cmbTempat_tgl.setSelectedIndex(0);
        Ttmpt_tgl_lain.setText("");
        Ttmpt_tgl_lain.setEnabled(false);
        Tpekerjaan.setText("");
        
        buttonGroup1.clearSelection();
        ChkSkriningD.setSelected(false);
        ChkSkriningA.setSelected(false);
        cmbDewasaGZ1.setEnabled(false);
        cmbDewasaGZ2.setEnabled(false);
        ChkGZanak1.setEnabled(false);
        ChkGZanak2.setEnabled(false);
        ChkGZanak3.setEnabled(false);
        ChkGZanak4.setEnabled(false);
        lainGZanak.setEditable(false);

        kesimpulanGZDewasa.setText("");
        kesimpulanGZanak.setText("");
        cmbDewasaGZ1.setSelectedIndex(0);
        cmbDewasaYaGZ1.setSelectedIndex(0);
        cmbDewasaYaGZ1.setEnabled(false);
        skorGZ1.setText("0");
        skorYaGZ1.setText("0");
        cmbDewasaGZ2.setSelectedIndex(0);
        skorGZ2.setText("0");
        TotSkorGZD.setText("0");

        ChkGZanak1.setSelected(false);
        ChkGZanak2.setSelected(false);
        ChkGZanak3.setSelected(false);
        ChkGZanak4.setSelected(false);        
        skorGZ3.setText("0");
        skorGZ4.setText("0");
        skorGZ5.setText("0");
        skorGZ6.setText("0");
        lainGZanak.setText("");
        TotSkorGZA.setText("0");
        TotSkorRJ.setText("0");
        kesimpulanResikoJatuh.setText("");
        BtnAllResikoActionPerformed(null);
        TabTindakanPencegahan.setSelectedIndex(0);
        TabPencegahanDewasa.setSelectedIndex(0);
        cmbPencegahan.setSelectedIndex(0);
        cmbTindakanCegah.setSelectedIndex(0);
        
        cmbAlatBantu.setSelectedIndex(0);
        cmbCacat.setSelectedIndex(0);
        Tsebutkan_cacat.setText("");
        Tsebutkan_cacat.setEnabled(false);
        cmbRiwayat.setSelectedIndex(0);
        chkAlergiObat.setSelected(false);
        chkAlergiMakanan.setSelected(false);
        chkAlergiLain.setSelected(false);
        chkPin.setSelected(false);
        reaksiAlergiObat.setText("");
        reaksiAlergiMakanan.setText("");
        reaksiAlergiLain.setText("");
        cmbdiberitahukan.setSelectedIndex(0);
        cmbNyeri.setSelectedIndex(0);
        Tnyeri.setText("");
        Tnyeri.setEnabled(false);
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
        Tdiagnosis.setText("");
        Ttindakan.setText("");
        Tevaluasi.setText("");
        
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
        chkMPP.setSelected(false);
        chkDP.setSelected(false);
        TglVerif.setDate(new Date());
        KdDokter.setText("-");
        NmDokter.setText("-");
        cmbSkala.setSelectedIndex(0);        
        BtnSebutkan.setEnabled(false);
        Valid.tabelKosong(tabMode1);
        Tradiation.setText("");
        Tradiation.setEnabled(false);
    }
    
    public void setNoRm(String norwt, Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);
        isRawat();
        tampil();
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnHapus.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnPrint.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        BtnEdit.setEnabled(akses.getpenilaian_awal_keperawatan_ralan());
        
        if (akses.getjml2() >= 1) {            
            BtnPerawat.setEnabled(false);
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", nm_perawat, nip);
            if (nm_perawat.getText().equals("")) {
                nip = "";
//                JOptionPane.showMessageDialog(null, "User login bukan dokter...!!");
            }
        }  
    }

    private void getData() {
        nip = "";
        skrining_gz = "";
        gz_anak1 = "";
        gz_anak2 = "";
        gz_anak3 = "";
        gz_anak4 = "";
        aler_obat = "";
        aler_mak = "";
        aler_lain = "";
        pin = "";
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
        mpp = "";
        dp = "";

        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRM.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            Ttgl_lahir.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            Valid.SetTgl2(tgl_asesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 70).toString());
            Tkeluhan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 5).toString());
            td.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            nadi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            napas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            suhu.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            bb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            tb.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            cmbAsesmen.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString());
            cmbPerilaku.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString());
            Tsebutkan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString());
            Tstts_nikah.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 73).toString());
            cmbHub_pasien.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 15).toString());
            cmbTempat_tgl.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 16).toString());
            Ttmpt_tgl_lain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 17).toString());
            Tpekerjaan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 74).toString());
            skrining_gz = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 18).toString();
            cmbDewasaGZ1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 19).toString());
            cmbDewasaYaGZ1.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 20).toString());
            cmbDewasaGZ2.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 21).toString());
            gz_anak1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 22).toString();
            gz_anak2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 23).toString();
            gz_anak3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 24).toString();
            gz_anak4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 25).toString();
            lainGZanak.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 26).toString());
            cmbPencegahan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 27).toString());
            cmbTindakanCegah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 28).toString());
            cmbAlatBantu.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 29).toString());
            cmbCacat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 30).toString());
            Tsebutkan_cacat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 31).toString());
            cmbRiwayat.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 32).toString());
            aler_obat = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 33).toString();
            reaksiAlergiObat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 34).toString());
            aler_mak = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 35).toString();
            reaksiAlergiMakanan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 36).toString());
            aler_lain = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 37).toString();
            reaksiAlergiLain.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 38).toString());
            pin = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 39).toString();
            cmbdiberitahukan.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 40).toString());
            cmbNyeri.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 41).toString());
            Tnyeri.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 42).toString());
            cmbJenis.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 43).toString());
            cmbProvo.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 44).toString());
            Tprovo.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 45).toString());
            cmbQuality.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 46).toString());
            Tquality.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 47).toString());
            cmbRadia.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 48).toString());
            cmbSevere.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 49).toString());
            cmbTime.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 50).toString());
            cmbLama.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 51).toString());
            Tdiagnosis.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 52).toString());
            Ttindakan.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 53).toString());
            Tevaluasi.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 54).toString());
            iden1 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 55).toString();
            iden2 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 56).toString();
            iden3 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 57).toString();
            iden4 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 58).toString();
            iden5 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 59).toString();
            iden6 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 60).toString();
            iden7 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 61).toString();
            iden8 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 62).toString();
            iden9 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 63).toString();
            iden10 = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 64).toString();
            mpp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 65).toString();
            dp = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 66).toString();
            Valid.SetTgl2(TglVerif, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 67).toString());
            KdDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 68).toString());
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 69).toString();
            nm_perawat.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 71).toString());
            NmDokter.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 72).toString());
            cmbSkala.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 75).toString());
            Tradiation.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 76).toString());
            dataCek();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from penilaian_awal_keperawatan_igdrz where no_rawat=?", 1, new String[]{
                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
            }) == true) {
                Sequel.meghapus("penilaian_awal_keperawatan_igd_resiko", "no_rawat", 
                        tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
                tampil();
                emptTeks();
                tampilFaktorResiko();
                TabRawat.setSelectedIndex(1);
            } else {
                JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
            }
        }
    }
    
    private void ganti() {
        cekData();
        if (Sequel.mengedittf("penilaian_awal_keperawatan_igdrz", "no_rawat=?", "no_rawat=?, tanggal=?, keluhan_utama=?, td=?, nadi=?, nafas=?, suhu=?, bb=?, tb=?, asesmen_psikologis=?, "
                + "masalah_perilaku=?, sebutkan_perilaku=?, hubungan_pasien=?, tempat_tinggal=?, lainya_tempt_tgl=?, skrining_gizi=?, gizi_dewasa1=?, gizi_dewasa1ya=?, gizi_dewasa2=?, "
                + "gizi_anak1=?, gizi_anak2=?, gizi_anak3=?, gizi_anak_penyakit=?, gizi_anak_penyakit_lain=?, cegah_resiko_jatuh=?, tindakan_pencegahan=?, alat_bantu=?, cacat_tubuh=?, "
                + "ada_cacat_tubuh=?, riwayat_alergi=?, alergi_obat=?, reaksi_alergi_obat=?, alergi_makanan=?, reaksi_alergi_makanan=?, alergi_lainnya=?, reaksi_alergi_lainnya=?, "
                + "pin_kancing=?, alergi_diberitahukan=?, nyeri=?, ya_nyeri_lokasi=?, jenis_nyeri=?, provocation=?, provocation_lain=?, quality=?, quality_lain=?, radiation=?, severity=?, "
                + "time=?, time_lama=?, diagnosa_keperawatan=?, tindakan_keperawatan=?, evaluasi_keperawatan=?, identifikasi1=?, identifikasi2=?, identifikasi3=?, identifikasi4=?, "
                + "identifikasi5=?, identifikasi6=?, identifikasi7=?, identifikasi8=?, identifikasi9=?, identifikasi10=?, manajer_pelayanan=?, discharge_planing=?, tgl_verifikasi=?, "
                + "nip_dpjp=?, nip_perawat=?, skala_nyeri=?, ket_radiation=?", 70, new String[]{
                    TNoRw.getText(), Valid.SetTgl(tgl_asesmen.getSelectedItem() + "") + " " + tgl_asesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), td.getText(), 
                    nadi.getText(), napas.getText(), suhu.getText(), bb.getText(), tb.getText(), cmbAsesmen.getSelectedItem().toString(), cmbPerilaku.getSelectedItem().toString(), Tsebutkan.getText(), 
                    cmbHub_pasien.getSelectedItem().toString(), cmbTempat_tgl.getSelectedItem().toString(), Ttmpt_tgl_lain.getText(), skrining_gz, cmbDewasaGZ1.getSelectedItem().toString(), 
                    cmbDewasaYaGZ1.getSelectedItem().toString(), cmbDewasaGZ2.getSelectedItem().toString(), gz_anak1, gz_anak2, gz_anak3, gz_anak4, lainGZanak.getText(), cmbPencegahan.getSelectedItem().toString(), 
                    cmbTindakanCegah.getSelectedItem().toString(), cmbAlatBantu.getSelectedItem().toString(), cmbCacat.getSelectedItem().toString(), Tsebutkan_cacat.getText(), cmbRiwayat.getSelectedItem().toString(), 
                    aler_obat, reaksiAlergiObat.getText(), aler_mak, reaksiAlergiMakanan.getText(), aler_lain, reaksiAlergiLain.getText(), pin, cmbdiberitahukan.getSelectedItem().toString(),
                    cmbNyeri.getSelectedItem().toString(), Tnyeri.getText(), cmbJenis.getSelectedItem().toString(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), 
                    Tquality.getText(), cmbRadia.getSelectedItem().toString(), cmbSevere.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(), 
                    Tdiagnosis.getText(), Ttindakan.getText(), Tevaluasi.getText(), iden1, iden2, iden3, iden4, iden5, iden6, iden7, iden8, iden9, iden10, mpp, dp,
                    Valid.SetTgl(TglVerif.getSelectedItem() + "") + " " + TglVerif.getSelectedItem().toString().substring(11, 19), KdDokter.getText(), nip,
                    cmbSkala.getSelectedItem().toString(), Tradiation.getText(),
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString()
                }) == true) {
            Sequel.meghapus("penilaian_awal_keperawatan_igd_resiko", "no_rawat", 
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_igd_resiko", "?,?", 2, new String[]{
                        TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                }
            }

            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
            tampil();
            emptTeks();            
        }
    }
    
    private void isRawat() {
        try {
            ps1 = koneksi.prepareStatement("SELECT rp.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') tgl_lahir, "
                    + "rp.tgl_registrasi, IFNULL(ti.td,'') td, IFNULL(ti.nadi,'') nadi, IFNULL(ti.napas,'') nafas, "
                    + "ifnull(ti.tb,'') tb, ifnull(ti.bb,'') bb, ifnull(ti.keluhan_utama,'') keluhan, IFNULL(ti.temperatur,'') suhu, "
                    + "rp.kd_dokter, d.nm_dokter, p.stts_nikah, p.pekerjaan FROM reg_periksa rp "
                    + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis inner join dokter d on d.kd_dokter=rp.kd_dokter "
                    + "LEFT JOIN triase_igd ti ON ti.no_rawat = rp.no_rawat WHERE rp.no_rawat=?");
            try {
                ps1.setString(1, TNoRw.getText());
                rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Ttgl_lahir.setText(rs1.getString("tgl_lahir"));
                    Tkeluhan.setText(rs1.getString("keluhan"));
                    
                    td.setText(rs1.getString("td"));
                    nadi.setText(rs1.getString("nadi"));
                    napas.setText(rs1.getString("nafas"));
                    suhu.setText(rs1.getString("suhu"));
                    bb.setText(rs1.getString("bb"));
                    tb.setText(rs1.getString("tb"));
                    
                    Tstts_nikah.setText(rs1.getString("stts_nikah"));
                    Tpekerjaan.setText(rs1.getString("pekerjaan"));
                    KdDokter.setText(rs1.getString("kd_dokter"));
                    NmDokter.setText(rs1.getString("nm_dokter"));
                    DTPCari1.setDate(rs1.getDate("tgl_registrasi"));                    
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
    }
    
    private void hitungSkorGZ() {
        int A1, B1, C1, TotD, A2, B2, C2, D2, TotA;
        A1 = Integer.parseInt(skorGZ1.getText());
        B1 = Integer.parseInt(skorYaGZ1.getText());
        C1 = Integer.parseInt(skorGZ2.getText());
        
        A2 = Integer.parseInt(skorGZ3.getText());
        B2 = Integer.parseInt(skorGZ4.getText());
        C2 = Integer.parseInt(skorGZ5.getText());
        D2 = Integer.parseInt(skorGZ6.getText());
        
        TotD = 0;
        TotA = 0;
        
        TotD = A1 + B1 + C1;
        TotA = A2 + B2 + C2 + D2;
        TotSkorGZD.setText(Valid.SetAngka2(TotD));
        TotSkorGZA.setText(Valid.SetAngka2(TotA));
        
        if (ChkSkriningD.isSelected() == true) {
            kesimpulanGZanak.setText("");
            if (TotD == 0 || TotD == 1) {
                kesimpulanGZDewasa.setText("0 - 1 : tidak beresiko malnutrisi");
            } else if (TotD >= 2) {
                kesimpulanGZDewasa.setText(">= 2 : beresiko malnutrisi, perlu pemantauan lanjutan oleh Tim Gizi/Dietisien");
            }
        }
        
        if (ChkSkriningA.isSelected() == true) {
            kesimpulanGZDewasa.setText("");
            if (TotA == 0) {
                kesimpulanGZanak.setText("0 : tidak beresiko malnutrisi");
            } else if (TotA >= 1 && TotA <= 3) {
                kesimpulanGZanak.setText("1 - 3 : resiko malnutrisi sedang, perlu pemantauan");
            } else if (TotA >= 4) {
                kesimpulanGZanak.setText(">= 4 : resiko malnutrisi berat, perlu pemantauan lanjutan "
                        + "oleh Tim Gizi/Dietisien");
            }
        }
    }
    
    public void tampilFaktorResiko() {
        Valid.tabelKosong(tabModeResiko);
        try {
            if (ChkSkriningA.isSelected() == true) {
                ps2 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                        + "asesmen = 'anak' and faktor_resiko like ? or "
                        + "asesmen = 'anak' and skala like ? order by kode_resiko");
            } else if (ChkSkriningD.isSelected() == true) {
                ps2 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                        + "asesmen = 'dewasa' and faktor_resiko like ? or "
                        + "asesmen = 'dewasa' and skala like ? order by kode_resiko");
            } else {
                ps2 = koneksi.prepareStatement("select kode_resiko, faktor_resiko, skala, skor, asesmen from master_faktor_resiko_igd where "
                        + "asesmen in ('dewasa','anak') and faktor_resiko like ? or "
                        + "asesmen in ('dewasa','anak') and skala like ? order by kode_resiko");
            }
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
    
    private void hitungSkorRJ() {
        skor = 0;
        for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
            if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 5).toString());
            }
        }
        
        TotSkorRJ.setText(Valid.SetAngka2(skor));        
        if (ChkSkriningD.isSelected() == false && ChkSkriningA.isSelected() == false) {
            TotSkorRJ.setText("0");
            kesimpulanResikoJatuh.setText("");
        }
        
        //asesmen dewasa
        if (ChkSkriningD.isSelected() == true) {            
            if (skor >= 45) {
                kesimpulanResikoJatuh.setText("Resiko Tinggi : >= 45, pasang kancing berwarna kuning");
            } else if (skor >= 25 && skor <= 44) {
                kesimpulanResikoJatuh.setText("Resiko Sedang : 25-44, pasang kancing berwarna kuning");
            } else if (skor >= 0 && skor <= 24) {
                kesimpulanResikoJatuh.setText("Resiko Rendah : 0-24");
            }
        }
        
        //asesmen anak
        if (ChkSkriningA.isSelected() == true) {
            if (skor >= 12) {
                kesimpulanResikoJatuh.setText("Resiko Tinggi : >= 12, pasang kancing penanda berwarna kuning");
            } else if (skor >= 7 && skor <= 11) {
                kesimpulanResikoJatuh.setText("Resiko Rendah : 7-11");
            } else if (skor >= 0 && skor <= 6) {
                kesimpulanResikoJatuh.setText("");
            }
        }
    }
    
    private void cekData() {
        if (ChkSkriningD.isSelected() == true) {
            skrining_gz = "dewasa";
        } else if (ChkSkriningA.isSelected() == true) {
            skrining_gz = "anak";
        } else {
            skrining_gz = "";
        }
        
        if (ChkGZanak1.isSelected() == true) {
            gz_anak1 = "1";
        } else {
            gz_anak1 = "0";
        }
        
        if (ChkGZanak2.isSelected() == true) {
            gz_anak2 = "1";
        } else {
            gz_anak2 = "0";
        }

        if (ChkGZanak3.isSelected() == true) {
            gz_anak3 = "1";
        } else {
            gz_anak3 = "0";
        }

        if (ChkGZanak4.isSelected() == true) {
            gz_anak4 = "2";
        } else {
            gz_anak4 = "0";
        }
        
        if (chkAlergiObat.isSelected() == true) {
            aler_obat = "ya";
        } else {
            aler_obat = "tidak";
        }

        if (chkAlergiMakanan.isSelected() == true) {
            aler_mak = "ya";
        } else {
            aler_mak = "tidak";
        }

        if (chkAlergiLain.isSelected() == true) {
            aler_lain = "ya";
        } else {
            aler_lain = "tidak";
        }

        if (chkPin.isSelected() == true) {
            pin = "ya";
        } else {
            pin = "tidak";
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
        
        if (chkMPP.isSelected() == true) {
            mpp = "ya";
        } else {
            mpp = "tidak";
        }
        
        if (chkDP.isSelected() == true) {
            dp = "ya";
        } else {
            dp = "tidak";
        }
    }
    
    private void simpan() {
        cekData();
        if (Sequel.menyimpantf("penilaian_awal_keperawatan_igdrz", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 70, new String[]{
            TNoRw.getText(), Valid.SetTgl(tgl_asesmen.getSelectedItem() + "") + " " + tgl_asesmen.getSelectedItem().toString().substring(11, 19), Tkeluhan.getText(), td.getText(), nadi.getText(), napas.getText(),
            suhu.getText(), bb.getText(), tb.getText(), cmbAsesmen.getSelectedItem().toString(), cmbPerilaku.getSelectedItem().toString(), Tsebutkan.getText(), cmbHub_pasien.getSelectedItem().toString(),
            cmbTempat_tgl.getSelectedItem().toString(), Ttmpt_tgl_lain.getText(), skrining_gz, cmbDewasaGZ1.getSelectedItem().toString(), cmbDewasaYaGZ1.getSelectedItem().toString(), cmbDewasaGZ2.getSelectedItem().toString(),
            gz_anak1, gz_anak2, gz_anak3, gz_anak4, lainGZanak.getText(), cmbPencegahan.getSelectedItem().toString(), cmbTindakanCegah.getSelectedItem().toString(), cmbAlatBantu.getSelectedItem().toString(), cmbCacat.getSelectedItem().toString(),
            Tsebutkan_cacat.getText(), cmbRiwayat.getSelectedItem().toString(), aler_obat, reaksiAlergiObat.getText(), aler_mak, reaksiAlergiMakanan.getText(), aler_lain, reaksiAlergiLain.getText(), pin, cmbdiberitahukan.getSelectedItem().toString(),
            cmbNyeri.getSelectedItem().toString(), Tnyeri.getText(), cmbJenis.getSelectedItem().toString(), cmbProvo.getSelectedItem().toString(), Tprovo.getText(), cmbQuality.getSelectedItem().toString(), Tquality.getText(),
            cmbRadia.getSelectedItem().toString(), cmbSevere.getSelectedItem().toString(), cmbTime.getSelectedItem().toString(), cmbLama.getSelectedItem().toString(), Tdiagnosis.getText(), Ttindakan.getText(), Tevaluasi.getText(),
            iden1, iden2, iden3, iden4, iden5, iden6, iden7, iden8, iden9, iden10, mpp, dp, Valid.SetTgl(TglVerif.getSelectedItem() + "") + " " + TglVerif.getSelectedItem().toString().substring(11, 19),
            KdDokter.getText(), nip, cmbSkala.getSelectedItem().toString(), Sequel.cariIsi("select now()"), Tradiation.getText()
        }) == true) {
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("penilaian_awal_keperawatan_igd_resiko", "?,?", 2, new String[]{
                        TNoRw.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                }
            }
            emptTeks();
            tampil();
            TabRawat.setSelectedIndex(1);
            tampilFaktorResiko();
        }
    }

    private void dataCek() {
        //data skrining gizi
        if (skrining_gz.equals("dewasa")) {
            ChkSkriningD.setSelected(true);
            cmbDewasaGZ1.setEnabled(true);
            cmbDewasaGZ2.setEnabled(true);

            if (cmbDewasaGZ1.getSelectedIndex() == 0) {
                cmbDewasaYaGZ1.setEnabled(false);
            } else if (cmbDewasaGZ1.getSelectedIndex() == 1) {
                cmbDewasaYaGZ1.setEnabled(false);
            } else if (cmbDewasaGZ1.getSelectedIndex() == 2) {
                cmbDewasaYaGZ1.setEnabled(true);
            }

            ChkGZanak1.setEnabled(false);
            ChkGZanak2.setEnabled(false);
            ChkGZanak3.setEnabled(false);
            ChkGZanak4.setEnabled(false);
            lainGZanak.setEditable(false);
        } else if (skrining_gz.equals("anak")) {
            ChkSkriningA.setSelected(true);
            cmbDewasaGZ1.setEnabled(false);
            cmbDewasaGZ2.setEnabled(false);
            cmbDewasaYaGZ1.setEnabled(false);

            ChkGZanak1.setEnabled(true);
            ChkGZanak2.setEnabled(true);
            ChkGZanak3.setEnabled(true);
            ChkGZanak4.setEnabled(true);
            lainGZanak.setEditable(true);
        } else {
            ChkSkriningD.setSelected(false);
            ChkSkriningA.setSelected(false);
            buttonGroup1.clearSelection();
        }
        
        if (gz_anak1.equals("1")) {
            ChkGZanak1.setSelected(true);
            skorGZ3.setText("1");
        } else {
            ChkGZanak1.setSelected(false);
            skorGZ3.setText("0");
        }

        if (gz_anak2.equals("1")) {
            ChkGZanak2.setSelected(true);
            skorGZ4.setText("1");
        } else {
            ChkGZanak2.setSelected(false);
            skorGZ4.setText("0");
        }

        if (gz_anak3.equals("1")) {
            ChkGZanak3.setSelected(true);
            skorGZ5.setText("1");
        } else {
            ChkGZanak3.setSelected(false);
            skorGZ5.setText("0");
        }

        if (gz_anak4.equals("2")) {
            ChkGZanak4.setSelected(true);
            skorGZ6.setText("2");
        } else {
            ChkGZanak4.setSelected(false);
            skorGZ6.setText("0");
        }
        hitungSkorGZ();
        
        //tindakan pencegahan resiko jatuh
        if (cmbPencegahan.getSelectedIndex() == 1) {
            TabTindakanPencegahan.setSelectedIndex(0);
            if (cmbTindakanCegah.getSelectedIndex() == 1) {
                TabPencegahanDewasa.setSelectedIndex(0);
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                TabPencegahanDewasa.setSelectedIndex(1);
            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
                TabPencegahanDewasa.setSelectedIndex(2);
            } else {
                TabPencegahanDewasa.setSelectedIndex(0);
            }
        } else if (cmbPencegahan.getSelectedIndex() == 2) {
            TabTindakanPencegahan.setSelectedIndex(1);
            if (cmbTindakanCegah.getSelectedIndex() == 1) {
                TabPencegahanAnak.setSelectedIndex(0);
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                TabPencegahanAnak.setSelectedIndex(1);
            } else {
                TabPencegahanAnak.setSelectedIndex(0);
            }
        } else {
            TabTindakanPencegahan.setSelectedIndex(0);
            TabPencegahanDewasa.setSelectedIndex(0);
        }
        
        //riwayat alergi
        if (aler_obat.equals("ya")) {
            chkAlergiObat.setSelected(true);
        } else {
            chkAlergiObat.setSelected(false);
        }
        
        if (aler_mak.equals("ya")) {
            chkAlergiMakanan.setSelected(true);
        } else {
            chkAlergiMakanan.setSelected(false);
        }

        if (aler_lain.equals("ya")) {
            chkAlergiLain.setSelected(true);
        } else {
            chkAlergiLain.setSelected(false);
        }

        if (pin.equals("ya")) {
            chkPin.setSelected(true);
        } else {
            chkPin.setSelected(false);
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
        
        if (mpp.equals("ya")) {
            chkMPP.setSelected(true);
        } else {
            chkMPP.setSelected(false);
        }

        if (dp.equals("ya")) {
            chkDP.setSelected(true);
        } else {
            chkDP.setSelected(false);
        }
        
        //radiation
        if (cmbRadia.getSelectedIndex() == 1) {
            Tradiation.setEnabled(true);
        } else {
            Tradiation.setEnabled(false);
        }
        
        //faktor resiko
        try {
            Valid.tabelKosong(tabModeResiko);
            if (ChkSkriningA.isSelected() == true) {
                ps3 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                        + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'anak' and pa.no_rawat=? ORDER BY pa.kode_resiko");
            } else if (ChkSkriningD.isSelected() == true) {
                ps3 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                        + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                        + "WHERE m.asesmen = 'dewasa' and pa.no_rawat=? ORDER BY pa.kode_resiko");
            } else {
                ps3 = koneksi.prepareStatement("select m.kode_resiko, m.faktor_resiko, m.skala, m.skor, m.asesmen FROM master_faktor_resiko_igd m "
                        + "INNER JOIN penilaian_awal_keperawatan_igd_resiko pa ON pa.kode_resiko = m.kode_resiko "
                        + "WHERE pa.no_rawat=? ORDER BY pa.kode_resiko");
            }
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
    
    public void setTampil(){
       TabRawat.setSelectedIndex(1);
       tampil();
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.keluhan_utama<>'' and p.no_rkm_medis like ? OR "
                        + "pa.keluhan_utama<>'' and p.nm_pasien like ? OR "
                        + "pa.keluhan_utama<>'' and pa.keluhan_utama like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.sebutkan_perilaku<>'' and p.no_rkm_medis like ? OR "
                        + "pa.sebutkan_perilaku<>'' and p.nm_pasien like ? OR "
                        + "pa.sebutkan_perilaku<>'' and pa.sebutkan_perilaku like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 3) {
                pps3 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.reaksi_alergi_obat<>'' and p.no_rkm_medis like ? OR "
                        + "pa.reaksi_alergi_obat<>'' and p.nm_pasien like ? OR "
                        + "pa.reaksi_alergi_obat<>'' and pa.reaksi_alergi_obat like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 4) {
                pps4 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.reaksi_alergi_makanan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.reaksi_alergi_makanan<>'' and p.nm_pasien like ? OR "
                        + "pa.reaksi_alergi_makanan<>'' and pa.reaksi_alergi_makanan like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 5) {
                pps5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.reaksi_alergi_lainnya<>'' and p.no_rkm_medis like ? OR "
                        + "pa.reaksi_alergi_lainnya<>'' and p.nm_pasien like ? OR "
                        + "pa.reaksi_alergi_lainnya<>'' and pa.reaksi_alergi_lainnya like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 6) {
                pps6 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.diagnosa_keperawatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.diagnosa_keperawatan<>'' and p.nm_pasien like ? OR "
                        + "pa.diagnosa_keperawatan<>'' and pa.diagnosa_keperawatan like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 7) {
                pps7 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.tindakan_keperawatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.tindakan_keperawatan<>'' and p.nm_pasien like ? OR "
                        + "pa.tindakan_keperawatan<>'' and pa.tindakan_keperawatan like ? ORDER BY pa.tanggal desc limit 20");
            } else if (pilihan == 8) {
                pps8 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from penilaian_awal_keperawatan_igdrz pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.evaluasi_keperawatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.evaluasi_keperawatan<>'' and p.nm_pasien like ? OR "
                        + "pa.evaluasi_keperawatan<>'' and pa.evaluasi_keperawatan like ? ORDER BY pa.tanggal desc limit 20");
            } 
            try {
                if (pilihan == 1) {
                    pps1.setString(1, "%" + TCari1.getText() + "%");
                    pps1.setString(2, "%" + TCari1.getText() + "%");
                    pps1.setString(3, "%" + TCari1.getText() + "%");
                    rrs1 = pps1.executeQuery();
                    while (rrs1.next()) {
                        tabMode1.addRow(new String[]{
                            rrs1.getString("no_rkm_medis"),
                            rrs1.getString("nm_pasien"),
                            rrs1.getString("keluhan_utama")
                        });
                    }
                } else if (pilihan == 2) {
                    pps2.setString(1, "%" + TCari1.getText() + "%");
                    pps2.setString(2, "%" + TCari1.getText() + "%");
                    pps2.setString(3, "%" + TCari1.getText() + "%");
                    rrs2 = pps2.executeQuery();
                    while (rrs2.next()) {
                        tabMode1.addRow(new String[]{
                            rrs2.getString("no_rkm_medis"),
                            rrs2.getString("nm_pasien"),
                            rrs2.getString("sebutkan_perilaku")
                        });
                    }
                } else if (pilihan == 3) {
                    pps3.setString(1, "%" + TCari1.getText() + "%");
                    pps3.setString(2, "%" + TCari1.getText() + "%");
                    pps3.setString(3, "%" + TCari1.getText() + "%");
                    rrs3 = pps3.executeQuery();
                    while (rrs3.next()) {
                        tabMode1.addRow(new String[]{
                            rrs3.getString("no_rkm_medis"),
                            rrs3.getString("nm_pasien"),
                            rrs3.getString("reaksi_alergi_obat")
                        });
                    }
                } else if (pilihan == 4) {
                    pps4.setString(1, "%" + TCari1.getText() + "%");
                    pps4.setString(2, "%" + TCari1.getText() + "%");
                    pps4.setString(3, "%" + TCari1.getText() + "%");
                    rrs4 = pps4.executeQuery();
                    while (rrs4.next()) {
                        tabMode1.addRow(new String[]{
                            rrs4.getString("no_rkm_medis"),
                            rrs4.getString("nm_pasien"),
                            rrs4.getString("reaksi_alergi_makanan")
                        });
                    }
                } else if (pilihan == 5) {
                    pps5.setString(1, "%" + TCari1.getText() + "%");
                    pps5.setString(2, "%" + TCari1.getText() + "%");
                    pps5.setString(3, "%" + TCari1.getText() + "%");
                    rrs5 = pps5.executeQuery();
                    while (rrs5.next()) {
                        tabMode1.addRow(new String[]{
                            rrs5.getString("no_rkm_medis"),
                            rrs5.getString("nm_pasien"),
                            rrs5.getString("reaksi_alergi_lainnya")
                        });
                    }
                } else if (pilihan == 6) {
                    pps6.setString(1, "%" + TCari1.getText() + "%");
                    pps6.setString(2, "%" + TCari1.getText() + "%");
                    pps6.setString(3, "%" + TCari1.getText() + "%");
                    rrs6 = pps6.executeQuery();
                    while (rrs6.next()) {
                        tabMode1.addRow(new String[]{
                            rrs6.getString("no_rkm_medis"),
                            rrs6.getString("nm_pasien"),
                            rrs6.getString("diagnosa_keperawatan")
                        });
                    }
                } else if (pilihan == 7) {
                    pps7.setString(1, "%" + TCari1.getText() + "%");
                    pps7.setString(2, "%" + TCari1.getText() + "%");
                    pps7.setString(3, "%" + TCari1.getText() + "%");
                    rrs7 = pps7.executeQuery();
                    while (rrs7.next()) {
                        tabMode1.addRow(new String[]{
                            rrs7.getString("no_rkm_medis"),
                            rrs7.getString("nm_pasien"),
                            rrs7.getString("tindakan_keperawatan")
                        });
                    }
                } else if (pilihan == 8) {
                    pps8.setString(1, "%" + TCari1.getText() + "%");
                    pps8.setString(2, "%" + TCari1.getText() + "%");
                    pps8.setString(3, "%" + TCari1.getText() + "%");
                    rrs8 = pps8.executeQuery();
                    while (rrs8.next()) {
                        tabMode1.addRow(new String[]{
                            rrs8.getString("no_rkm_medis"),
                            rrs8.getString("nm_pasien"),
                            rrs8.getString("evaluasi_keperawatan")
                        });
                    }
                } 
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rrs1 != null) {
                    rrs1.close();
                } else if (rrs2 != null) {
                    rrs2.close();
                } else if (rrs3 != null) {
                    rrs3.close();
                } else if (rrs4 != null) {
                    rrs4.close();
                } else if (rrs5 != null) {
                    rrs5.close();
                } else if (rrs6 != null) {
                    rrs6.close();
                } else if (rrs7 != null) {
                    rrs7.close();
                } else if (rrs8 != null) {
                    rrs8.close();
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } else if (pps3 != null) {
                    pps3.close();
                } else if (pps4 != null) {
                    pps4.close();
                } else if (pps5 != null) {
                    pps5.close();
                } else if (pps6 != null) {
                    pps6.close();
                } else if (pps7 != null) {
                    pps7.close();
                } else if (pps8 != null) {
                    pps8.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            Tkeluhan.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tsebutkan.setText(Ttemplate.getText());
        } else if (pilihan == 3) {
            reaksiAlergiObat.setText(Ttemplate.getText());
        } else if (pilihan == 4) {
            reaksiAlergiMakanan.setText(Ttemplate.getText());
        } else if (pilihan == 5) {
            reaksiAlergiLain.setText(Ttemplate.getText());
        } else if (pilihan == 6) {
            Tdiagnosis.setText(Ttemplate.getText());
        } else if (pilihan == 7) {
            Ttindakan.setText(Ttemplate.getText());
        } else if (pilihan == 8) {
            Tevaluasi.setText(Ttemplate.getText());
        }
    }
}
