    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgSpesialis.java
 *
 * Created on May 23, 2010, 1:25:13 AM
 */

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgNotepad;

/**
 *
 * @author dosen
 */
public class RMPemantauanHarian24Jam extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3, tabModeCppt;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, pscppt;
    private ResultSet rs, rs1, rs2, rs3, rs4, rscppt;
    private String urutanJam = "", dataParental = "", wktSimpan = "", tglPANTAU = "", norawatPANTAU = "", dataKonfirmasi = "", tglBekasInput = "",
            respiEWS = "", saturEWS = "", tensiEWS = "", nadiEWS = "", suhuEWS = "", skorRespi = "", skorSatur = "", skorSuplemen = "",
            skorTensi = "", skorNadi = "", skorKesadaran = "", skorTemperatur = "", totSkor = "", ruangranap = "";
    private int i = 0, x = 0, pilih = 0,
            total = 0, respirasi = 0, saturasi = 0, suplemen = 0, nilaitensi = 0, nilainadi = 0, kesadaran = 0, temperatur = 0;
    private double totParental = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);  

    /** Creates new form DlgSpesialis
     * @param parent
     * @param modal */
    public RMPemantauanHarian24Jam(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        String[] row = {
            "No. Rawat", "Kode Pantau", "No. RM", "Nama Pasien", "Tgl. Pantau", "Jam", "Nadi", "Suhu",
            "GCS", "Kesadaran", "Tensi (MAP)", "Frek. Nafas", "SPO2 (Oksigen)", "Makan Minum", "NGT", "Jml. Parental", "Tot. Intake",
            "Urine", "NGT/Darah", "Drain", "Muntah", "BAB", "Lain-lain", "IWL", "Tot. Output", "Balance",
            "gcse", "gcsm", "gcsv", "tgl_pantau", "urutan_jam", "wktu_simpan", "bb_msk_rs",
            "transfusi", "reaksi_transfusi", "lain_lain", "catatan", "nip_petugas", "nmpetugas", "pengali_iwl", 
            "oksigen", "spo2", "sistol", "distol", "map", "tensi", "ruangrawat", "pengali_iwl_bb"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPantau.setModel(tabMode);
        tbPantau.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPantau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 48; i++) {
            TableColumn column = tbPantau.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(45);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(105);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(100);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(45);
            } else if (i == 15) {
                column.setPreferredWidth(75);
            } else if (i == 16) {
                column.setPreferredWidth(75);
            } else if (i == 17) {
                column.setPreferredWidth(40);
            } else if (i == 18) {
                column.setPreferredWidth(75);
            } else if (i == 19) {
                column.setPreferredWidth(45);
            } else if (i == 20) {
                column.setPreferredWidth(65);
            } else if (i == 21) {
                column.setPreferredWidth(35);
            } else if (i == 22) {
                column.setPreferredWidth(65);
            } else if (i == 23) {
                column.setPreferredWidth(45);
            } else if (i == 24) {
                column.setPreferredWidth(75);
            } else if (i == 25) {
                column.setPreferredWidth(60);
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
            } 
        }
        tbPantau.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Tanggal", "Nama Obat", "Jml. Beri (cc)", "Kode Pemantauan", "tgl"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbParental.setModel(tabMode1);
        tbParental.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbParental.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbParental.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(350);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(140);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbParental.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Tgl. Beri", "Nama Obat", "Jns. Obat", "Jumlah", "Dosis", "Cara Pemberian/Rute"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbObat.setModel(tabMode2);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 6; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(75);
            } else if (i == 1) {
                column.setPreferredWidth(300);
            } else if (i == 2) {
                column.setPreferredWidth(80);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(90);
            } else if (i == 5) {
                column.setPreferredWidth(140);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new Object[]{
            "Pasien", "Tgl. Pantau", "Keterangan", "Total (Cc.)"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbTotal.setModel(tabMode3);
        tbTotal.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbTotal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbTotal.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(320);
            } else if (i == 1) {
                column.setPreferredWidth(75);
            } else if (i == 2) {
                column.setPreferredWidth(96);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } 
        }
        tbTotal.setDefaultRenderer(Object.class, new WarnaTable());
        
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

        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        Tnadi.setDocument(new batasInput((byte) 3).getOnlyAngka(Tnadi));
        Tsuhu.setDocument(new batasInput((int) 4).getKata(Tsuhu));
        Tbb.setDocument(new batasInput((byte) 4).getKata(Tbb));
        Tgcse.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcse));
        Tgcsm.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsm));
        Tgcsv.setDocument(new batasInput((byte) 2).getOnlyAngka(Tgcsv));
        Trr.setDocument(new batasInput((byte) 7).getKata(Trr));
        Tspo.setDocument(new batasInput((byte) 7).getKata(Tspo));
        Toksigen.setDocument(new batasInput((byte) 7).getKata(Toksigen));
        Tbb.setDocument(new batasInput((byte) 7).getKata(Tbb));
        
        Tmm.setDocument(new batasInput((byte) 4).getOnlyAngka(Tmm));
        Tngt.setDocument(new batasInput((byte) 4).getOnlyAngka(Tngt));
        Ttransfusi.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttransfusi));
        
        Turin.setDocument(new batasInput((byte) 4).getOnlyAngka(Turin));
        TngtDarah.setDocument(new batasInput((byte) 4).getOnlyAngka(TngtDarah));
        Tdrain.setDocument(new batasInput((byte) 4).getOnlyAngka(Tdrain));
        Tmuntah.setDocument(new batasInput((byte) 4).getOnlyAngka(Tmuntah));
        Tbab.setDocument(new batasInput((byte) 4).getOnlyAngka(Tbab));
        Tlain.setDocument(new batasInput((byte) 4).getOnlyAngka(Tlain));
        TjmlBeri.setDocument(new batasInput((int) 7).getKata(TjmlBeri));
        Tsistole.setDocument(new batasInput((byte) 3).getOnlyAngka(Tsistole));
        Tdistole.setDocument(new batasInput((byte) 3).getOnlyAngka(Tdistole));

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
                    Tnip.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
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
        
        ChkAccor.setSelected(false);
        isMenu();
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
        MnHapusSemuaPemantauan = new javax.swing.JMenuItem();
        MnSimpanEWS = new javax.swing.JMenuItem();
        MnMonitoringEWS = new javax.swing.JMenuItem();
        WindowParental = new javax.swing.JDialog();
        internalFrame10 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        tbObat = new widget.Table();
        internalFrame11 = new widget.InternalFrame();
        internalFrame12 = new widget.InternalFrame();
        jLabel3 = new widget.Label();
        TnmObat = new widget.TextBox();
        jLabel4 = new widget.Label();
        TjmlBeri = new widget.TextBox();
        jLabel21 = new widget.Label();
        internalFrame13 = new widget.InternalFrame();
        jLabel23 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnCari1 = new widget.Button();
        BtnSimpan1 = new widget.Button();
        BtnBatal1 = new widget.Button();
        BtnKeluar1 = new widget.Button();
        WindowEWS = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        internalFrame7 = new widget.InternalFrame();
        jLabel31 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel32 = new widget.Label();
        cmbSuplemen = new widget.ComboBox();
        jLabel33 = new widget.Label();
        cmbKesadaran = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Tgds = new widget.TextBox();
        jLabel72 = new widget.Label();
        TskorNyeri = new widget.TextBox();
        internalFrame15 = new widget.InternalFrame();
        BtnSimpan4 = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        panelGlass7 = new widget.panelisi();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglPantau = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Tnadi = new widget.TextBox();
        jLabel10 = new widget.Label();
        Tsuhu = new widget.TextBox();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        Tgcse = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tgcsm = new widget.TextBox();
        jLabel15 = new widget.Label();
        Tgcsv = new widget.TextBox();
        jLabel16 = new widget.Label();
        Tkesadaran = new widget.TextBox();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        Trr = new widget.TextBox();
        jLabel40 = new widget.Label();
        jLabel19 = new widget.Label();
        Tspo = new widget.TextBox();
        jLabel41 = new widget.Label();
        jLabel20 = new widget.Label();
        Tmm = new widget.TextBox();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        Tngt = new widget.TextBox();
        jLabel42 = new widget.Label();
        TjmlParental = new widget.TextBox();
        jLabel44 = new widget.Label();
        Ttotintake = new widget.TextBox();
        jLabel45 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel46 = new widget.Label();
        Turin = new widget.TextBox();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        TngtDarah = new widget.TextBox();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        Tdrain = new widget.TextBox();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        Tmuntah = new widget.TextBox();
        jLabel53 = new widget.Label();
        jLabel54 = new widget.Label();
        Tbab = new widget.TextBox();
        jLabel55 = new widget.Label();
        Tiwl = new widget.TextBox();
        jLabel57 = new widget.Label();
        Ttotouput = new widget.TextBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        Tbalance = new widget.TextBox();
        jLabel60 = new widget.Label();
        jLabel24 = new widget.Label();
        Tbb = new widget.TextBox();
        jLabel61 = new widget.Label();
        jLabel62 = new widget.Label();
        TkdPantau = new widget.TextBox();
        BtnTotOutput = new widget.Button();
        jLabel26 = new widget.Label();
        jLabel56 = new widget.Label();
        jLabel27 = new widget.Label();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel43 = new widget.Label();
        Ttransfusi = new widget.TextBox();
        jLabel64 = new widget.Label();
        jLabel65 = new widget.Label();
        TreaksiTransfusi = new widget.TextBox();
        jLabel66 = new widget.Label();
        Tlain = new widget.TextBox();
        jLabel67 = new widget.Label();
        jLabel68 = new widget.Label();
        Tcatatan = new widget.TextBox();
        jLabel30 = new widget.Label();
        Tnip = new widget.TextBox();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel69 = new widget.Label();
        cmbKali = new widget.ComboBox();
        jLabel70 = new widget.Label();
        Toksigen = new widget.TextBox();
        jLabel71 = new widget.Label();
        BtnNilaiMAP = new widget.Button();
        Tmap = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel74 = new widget.Label();
        Tsistole = new widget.TextBox();
        jLabel75 = new widget.Label();
        Tdistole = new widget.TextBox();
        jLabel76 = new widget.Label();
        TlabelTensi = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel78 = new widget.Label();
        cmbPerhari = new widget.ComboBox();
        jLabel79 = new widget.Label();
        Scroll1 = new widget.ScrollPane();
        tbParental = new widget.Table();
        panelGlass11 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbPantau = new widget.Table();
        Scroll3 = new widget.ScrollPane();
        tbTotal = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnParental = new widget.Button();
        BtnGrafik = new widget.Button();
        BtnNotepad = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel28 = new widget.Label();
        DTPCariA = new widget.Tanggal();
        jLabel29 = new widget.Label();
        DTPCariB = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
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

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusSemuaPemantauan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusSemuaPemantauan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusSemuaPemantauan.setText("Hapus Semua Pemantauan");
        MnHapusSemuaPemantauan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusSemuaPemantauan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusSemuaPemantauan.setIconTextGap(5);
        MnHapusSemuaPemantauan.setName("MnHapusSemuaPemantauan"); // NOI18N
        MnHapusSemuaPemantauan.setPreferredSize(new java.awt.Dimension(180, 26));
        MnHapusSemuaPemantauan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusSemuaPemantauanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusSemuaPemantauan);

        MnSimpanEWS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanEWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        MnSimpanEWS.setText("Simpan Ke Monitoring EWS");
        MnSimpanEWS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSimpanEWS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSimpanEWS.setIconTextGap(5);
        MnSimpanEWS.setName("MnSimpanEWS"); // NOI18N
        MnSimpanEWS.setPreferredSize(new java.awt.Dimension(180, 26));
        MnSimpanEWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanEWSActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSimpanEWS);

        MnMonitoringEWS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMonitoringEWS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMonitoringEWS.setText("Monitoring EWS");
        MnMonitoringEWS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMonitoringEWS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMonitoringEWS.setIconTextGap(5);
        MnMonitoringEWS.setName("MnMonitoringEWS"); // NOI18N
        MnMonitoringEWS.setPreferredSize(new java.awt.Dimension(180, 26));
        MnMonitoringEWS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMonitoringEWSActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMonitoringEWS);

        WindowParental.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowParental.setName("WindowParental"); // NOI18N
        WindowParental.setUndecorated(true);
        WindowParental.setResizable(false);
        WindowParental.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                WindowParentalWindowOpened(evt);
            }
        });

        internalFrame10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Parental / Line / Obat-obatan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame10.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Pemberian Obat Terjadwal ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbObat.setToolTipText("Silahkan klik untuk memilih data yang akan disimpan");
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        Scroll2.setViewportView(tbObat);

        internalFrame10.add(Scroll2, java.awt.BorderLayout.CENTER);

        internalFrame11.setBorder(null);
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setPreferredSize(new java.awt.Dimension(0, 90));
        internalFrame11.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame11.setLayout(new java.awt.BorderLayout());

        internalFrame12.setBorder(null);
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame12.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame12.setLayout(null);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nama Obat Dipilih : ");
        jLabel3.setName("jLabel3"); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(120, 23));
        internalFrame12.add(jLabel3);
        jLabel3.setBounds(5, 9, 120, 23);

        TnmObat.setForeground(new java.awt.Color(0, 0, 0));
        TnmObat.setName("TnmObat"); // NOI18N
        TnmObat.setPreferredSize(new java.awt.Dimension(450, 23));
        TnmObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmObatKeyPressed(evt);
            }
        });
        internalFrame12.add(TnmObat);
        TnmObat.setBounds(130, 9, 400, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Jml. Pemberian :");
        jLabel4.setName("jLabel4"); // NOI18N
        jLabel4.setPreferredSize(new java.awt.Dimension(100, 23));
        internalFrame12.add(jLabel4);
        jLabel4.setBounds(530, 9, 100, 23);

        TjmlBeri.setForeground(new java.awt.Color(0, 0, 0));
        TjmlBeri.setToolTipText("Isilah dengan angka yang benar, jika desimal gunakan titik sebagai pengganti koma");
        TjmlBeri.setName("TjmlBeri"); // NOI18N
        TjmlBeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjmlBeriKeyPressed(evt);
            }
        });
        internalFrame12.add(TjmlBeri);
        TjmlBeri.setBounds(635, 9, 80, 24);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("cc.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(30, 23));
        internalFrame12.add(jLabel21);
        jLabel21.setBounds(720, 9, 30, 23);

        internalFrame11.add(internalFrame12, java.awt.BorderLayout.PAGE_START);

        internalFrame13.setBorder(null);
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 9, 9));

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. Pemberian :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(99, 23));
        internalFrame13.add(jLabel23);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-08-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 26));
        internalFrame13.add(DTPCari1);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d.");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame13.add(jLabel25);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-08-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 26));
        internalFrame13.add(DTPCari2);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('1');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+1");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 26));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnCari1);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnSimpan1);

        BtnBatal1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal1.setMnemonic('B');
        BtnBatal1.setText("Baru");
        BtnBatal1.setToolTipText("Alt+B");
        BtnBatal1.setName("BtnBatal1"); // NOI18N
        BtnBatal1.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatal1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnBatal1);

        BtnKeluar1.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Keluar");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 26));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        internalFrame13.add(BtnKeluar1);

        internalFrame11.add(internalFrame13, java.awt.BorderLayout.PAGE_END);

        internalFrame10.add(internalFrame11, java.awt.BorderLayout.PAGE_END);

        WindowParental.getContentPane().add(internalFrame10, java.awt.BorderLayout.CENTER);

        WindowEWS.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowEWS.setName("WindowEWS"); // NOI18N
        WindowEWS.setUndecorated(true);
        WindowEWS.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Simpan Data Ke Monitoring EWS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Jam : ");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame7.add(jLabel31);
        jLabel31.setBounds(0, 10, 100, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        internalFrame7.add(cmbJam1);
        cmbJam1.setBounds(101, 10, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        internalFrame7.add(cmbMnt1);
        cmbMnt1.setBounds(152, 10, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        internalFrame7.add(cmbDtk1);
        cmbDtk1.setBounds(204, 10, 45, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Suplemen (O2) : ");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame7.add(jLabel32);
        jLabel32.setBounds(0, 38, 100, 23);

        cmbSuplemen.setForeground(new java.awt.Color(0, 0, 0));
        cmbSuplemen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbSuplemen.setName("cmbSuplemen"); // NOI18N
        internalFrame7.add(cmbSuplemen);
        cmbSuplemen.setBounds(101, 38, 60, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("%    Kesadaran : ");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame7.add(jLabel33);
        jLabel33.setBounds(165, 38, 85, 23);

        cmbKesadaran.setForeground(new java.awt.Color(0, 0, 0));
        cmbKesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sadar", "Nyeri/Verbal", "Unrespon" }));
        cmbKesadaran.setName("cmbKesadaran"); // NOI18N
        internalFrame7.add(cmbKesadaran);
        cmbKesadaran.setBounds(252, 38, 95, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("GDS : ");
        jLabel35.setName("jLabel35"); // NOI18N
        internalFrame7.add(jLabel35);
        jLabel35.setBounds(0, 66, 100, 23);

        Tgds.setForeground(new java.awt.Color(0, 0, 0));
        Tgds.setName("Tgds"); // NOI18N
        Tgds.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgdsKeyPressed(evt);
            }
        });
        internalFrame7.add(Tgds);
        Tgds.setBounds(101, 66, 55, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Skor Nyeri : ");
        jLabel72.setName("jLabel72"); // NOI18N
        internalFrame7.add(jLabel72);
        jLabel72.setBounds(160, 66, 70, 23);

        TskorNyeri.setForeground(new java.awt.Color(0, 0, 0));
        TskorNyeri.setName("TskorNyeri"); // NOI18N
        TskorNyeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TskorNyeriKeyPressed(evt);
            }
        });
        internalFrame7.add(TskorNyeri);
        TskorNyeri.setBounds(233, 66, 55, 23);

        internalFrame6.add(internalFrame7, java.awt.BorderLayout.CENTER);

        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 9));

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.setPreferredSize(new java.awt.Dimension(95, 26));
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnSimpan4);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnCloseIn4);

        internalFrame6.add(internalFrame15, java.awt.BorderLayout.PAGE_END);

        WindowEWS.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Pemantauan Harian Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 360));
        panelGlass10.setLayout(new java.awt.BorderLayout());

        panelGlass7.setName("panelGlass7"); // NOI18N
        panelGlass7.setPreferredSize(new java.awt.Dimension(44, 270));
        panelGlass7.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        panelGlass7.add(jLabel5);
        jLabel5.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        panelGlass7.add(TNoRw);
        TNoRw.setBounds(115, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        panelGlass7.add(TNoRm);
        TNoRm.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        panelGlass7.add(TPasien);
        TPasien.setBounds(314, 10, 490, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Pemantauan :");
        jLabel8.setName("jLabel8"); // NOI18N
        panelGlass7.add(jLabel8);
        jLabel8.setBounds(0, 38, 110, 23);

        tglPantau.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-08-2024" }));
        tglPantau.setDisplayFormat("dd-MM-yyyy");
        tglPantau.setName("tglPantau"); // NOI18N
        tglPantau.setOpaque(false);
        tglPantau.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass7.add(tglPantau);
        tglPantau.setBounds(115, 38, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Nadi :");
        jLabel9.setName("jLabel9"); // NOI18N
        panelGlass7.add(jLabel9);
        jLabel9.setBounds(0, 66, 110, 23);

        Tnadi.setForeground(new java.awt.Color(0, 0, 0));
        Tnadi.setName("Tnadi"); // NOI18N
        Tnadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnadiKeyPressed(evt);
            }
        });
        panelGlass7.add(Tnadi);
        Tnadi.setBounds(115, 66, 50, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Suhu :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelGlass7.add(jLabel10);
        jLabel10.setBounds(170, 66, 40, 23);

        Tsuhu.setForeground(new java.awt.Color(0, 0, 0));
        Tsuhu.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Tsuhu.setName("Tsuhu"); // NOI18N
        Tsuhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsuhuKeyPressed(evt);
            }
        });
        panelGlass7.add(Tsuhu);
        Tsuhu.setBounds(216, 66, 50, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelGlass7.add(jLabel11);
        jLabel11.setBounds(270, 66, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "01", "02", "03", "04", "05", "06" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelGlass7.add(cmbJam);
        cmbJam.setBounds(316, 66, 45, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("GCS :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelGlass7.add(jLabel12);
        jLabel12.setBounds(0, 94, 110, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("E :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelGlass7.add(jLabel13);
        jLabel13.setBounds(115, 94, 15, 23);

        Tgcse.setForeground(new java.awt.Color(0, 0, 0));
        Tgcse.setName("Tgcse"); // NOI18N
        Tgcse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcseKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcse);
        Tgcse.setBounds(135, 94, 45, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("M :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelGlass7.add(jLabel14);
        jLabel14.setBounds(186, 94, 15, 23);

        Tgcsm.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsm.setName("Tgcsm"); // NOI18N
        Tgcsm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsm);
        Tgcsm.setBounds(206, 94, 45, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("V :");
        jLabel15.setName("jLabel15"); // NOI18N
        panelGlass7.add(jLabel15);
        jLabel15.setBounds(255, 94, 15, 23);

        Tgcsv.setForeground(new java.awt.Color(0, 0, 0));
        Tgcsv.setName("Tgcsv"); // NOI18N
        Tgcsv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TgcsvKeyPressed(evt);
            }
        });
        panelGlass7.add(Tgcsv);
        Tgcsv.setBounds(274, 94, 45, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Kesadaran :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelGlass7.add(jLabel16);
        jLabel16.setBounds(320, 94, 70, 23);

        Tkesadaran.setForeground(new java.awt.Color(0, 0, 0));
        Tkesadaran.setName("Tkesadaran"); // NOI18N
        Tkesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TkesadaranKeyPressed(evt);
            }
        });
        panelGlass7.add(Tkesadaran);
        Tkesadaran.setBounds(395, 94, 408, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tekanan Darah :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelGlass7.add(jLabel17);
        jLabel17.setBounds(0, 122, 110, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Frek. Nafas / RR :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelGlass7.add(jLabel18);
        jLabel18.setBounds(0, 150, 110, 23);

        Trr.setForeground(new java.awt.Color(0, 0, 0));
        Trr.setName("Trr"); // NOI18N
        Trr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TrrKeyPressed(evt);
            }
        });
        panelGlass7.add(Trr);
        Trr.setBounds(115, 150, 70, 23);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("x/menit");
        jLabel40.setName("jLabel40"); // NOI18N
        panelGlass7.add(jLabel40);
        jLabel40.setBounds(190, 150, 50, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("SPO2 :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelGlass7.add(jLabel19);
        jLabel19.setBounds(240, 150, 50, 23);

        Tspo.setForeground(new java.awt.Color(0, 0, 0));
        Tspo.setName("Tspo"); // NOI18N
        Tspo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TspoKeyPressed(evt);
            }
        });
        panelGlass7.add(Tspo);
        Tspo.setBounds(296, 150, 50, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("% dengan Oksigen : ");
        jLabel41.setName("jLabel41"); // NOI18N
        panelGlass7.add(jLabel41);
        jLabel41.setBounds(353, 150, 105, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("INTAKE :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelGlass7.add(jLabel20);
        jLabel20.setBounds(0, 178, 110, 23);

        Tmm.setForeground(new java.awt.Color(0, 0, 0));
        Tmm.setName("Tmm"); // NOI18N
        Tmm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmmKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmm);
        Tmm.setBounds(200, 178, 55, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Makan Minum :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelGlass7.add(jLabel37);
        jLabel37.setBounds(115, 178, 80, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel38.setText("cc.");
        jLabel38.setName("jLabel38"); // NOI18N
        panelGlass7.add(jLabel38);
        jLabel38.setBounds(260, 178, 17, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("NGT :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelGlass7.add(jLabel39);
        jLabel39.setBounds(270, 178, 45, 23);

        Tngt.setForeground(new java.awt.Color(0, 0, 0));
        Tngt.setName("Tngt"); // NOI18N
        Tngt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtKeyPressed(evt);
            }
        });
        panelGlass7.add(Tngt);
        Tngt.setBounds(320, 178, 55, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("cc.");
        jLabel42.setName("jLabel42"); // NOI18N
        panelGlass7.add(jLabel42);
        jLabel42.setBounds(380, 178, 17, 23);

        TjmlParental.setEditable(false);
        TjmlParental.setForeground(new java.awt.Color(0, 0, 0));
        TjmlParental.setName("TjmlParental"); // NOI18N
        panelGlass7.add(TjmlParental);
        TjmlParental.setBounds(505, 178, 75, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("cc.");
        jLabel44.setName("jLabel44"); // NOI18N
        panelGlass7.add(jLabel44);
        jLabel44.setBounds(585, 178, 20, 23);

        Ttotintake.setEditable(false);
        Ttotintake.setForeground(new java.awt.Color(0, 0, 0));
        Ttotintake.setName("Ttotintake"); // NOI18N
        panelGlass7.add(Ttotintake);
        Ttotintake.setBounds(705, 178, 80, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("cc.");
        jLabel45.setName("jLabel45"); // NOI18N
        panelGlass7.add(jLabel45);
        jLabel45.setBounds(790, 178, 20, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("OUTPUT :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelGlass7.add(jLabel22);
        jLabel22.setBounds(0, 234, 110, 23);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Urine :");
        jLabel46.setName("jLabel46"); // NOI18N
        panelGlass7.add(jLabel46);
        jLabel46.setBounds(115, 234, 40, 23);

        Turin.setForeground(new java.awt.Color(0, 0, 0));
        Turin.setName("Turin"); // NOI18N
        Turin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TurinKeyPressed(evt);
            }
        });
        panelGlass7.add(Turin);
        Turin.setBounds(160, 234, 55, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("cc.");
        jLabel47.setName("jLabel47"); // NOI18N
        panelGlass7.add(jLabel47);
        jLabel47.setBounds(220, 234, 17, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("NGT / Darah :");
        jLabel48.setName("jLabel48"); // NOI18N
        panelGlass7.add(jLabel48);
        jLabel48.setBounds(235, 234, 80, 23);

        TngtDarah.setForeground(new java.awt.Color(0, 0, 0));
        TngtDarah.setName("TngtDarah"); // NOI18N
        TngtDarah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TngtDarahKeyPressed(evt);
            }
        });
        panelGlass7.add(TngtDarah);
        TngtDarah.setBounds(320, 234, 55, 23);

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("cc.");
        jLabel49.setName("jLabel49"); // NOI18N
        panelGlass7.add(jLabel49);
        jLabel49.setBounds(380, 234, 17, 23);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Drain :");
        jLabel50.setName("jLabel50"); // NOI18N
        panelGlass7.add(jLabel50);
        jLabel50.setBounds(380, 234, 62, 23);

        Tdrain.setForeground(new java.awt.Color(0, 0, 0));
        Tdrain.setName("Tdrain"); // NOI18N
        Tdrain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdrainKeyPressed(evt);
            }
        });
        panelGlass7.add(Tdrain);
        Tdrain.setBounds(446, 234, 55, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("cc.");
        jLabel51.setName("jLabel51"); // NOI18N
        panelGlass7.add(jLabel51);
        jLabel51.setBounds(505, 234, 17, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Muntah :");
        jLabel52.setName("jLabel52"); // NOI18N
        panelGlass7.add(jLabel52);
        jLabel52.setBounds(95, 262, 60, 23);

        Tmuntah.setForeground(new java.awt.Color(0, 0, 0));
        Tmuntah.setName("Tmuntah"); // NOI18N
        Tmuntah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmuntahKeyPressed(evt);
            }
        });
        panelGlass7.add(Tmuntah);
        Tmuntah.setBounds(160, 262, 55, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel53.setText("cc.");
        jLabel53.setName("jLabel53"); // NOI18N
        panelGlass7.add(jLabel53);
        jLabel53.setBounds(220, 262, 17, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("BAB :");
        jLabel54.setName("jLabel54"); // NOI18N
        panelGlass7.add(jLabel54);
        jLabel54.setBounds(255, 262, 60, 23);

        Tbab.setForeground(new java.awt.Color(0, 0, 0));
        Tbab.setName("Tbab"); // NOI18N
        Tbab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbabKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbab);
        Tbab.setBounds(320, 262, 55, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel55.setText("cc.");
        jLabel55.setName("jLabel55"); // NOI18N
        panelGlass7.add(jLabel55);
        jLabel55.setBounds(380, 262, 17, 23);

        Tiwl.setEditable(false);
        Tiwl.setForeground(new java.awt.Color(0, 0, 0));
        Tiwl.setName("Tiwl"); // NOI18N
        panelGlass7.add(Tiwl);
        Tiwl.setBounds(446, 262, 55, 23);

        jLabel57.setForeground(new java.awt.Color(0, 0, 0));
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("cc.");
        jLabel57.setName("jLabel57"); // NOI18N
        panelGlass7.add(jLabel57);
        jLabel57.setBounds(505, 262, 17, 23);

        Ttotouput.setEditable(false);
        Ttotouput.setForeground(new java.awt.Color(0, 0, 0));
        Ttotouput.setName("Ttotouput"); // NOI18N
        panelGlass7.add(Ttotouput);
        Ttotouput.setBounds(705, 234, 80, 23);

        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("cc.");
        jLabel58.setName("jLabel58"); // NOI18N
        panelGlass7.add(jLabel58);
        jLabel58.setBounds(790, 234, 20, 23);

        jLabel59.setForeground(new java.awt.Color(0, 0, 0));
        jLabel59.setText("BALANCE :");
        jLabel59.setName("jLabel59"); // NOI18N
        panelGlass7.add(jLabel59);
        jLabel59.setBounds(640, 262, 60, 23);

        Tbalance.setEditable(false);
        Tbalance.setForeground(new java.awt.Color(0, 0, 0));
        Tbalance.setName("Tbalance"); // NOI18N
        panelGlass7.add(Tbalance);
        Tbalance.setBounds(705, 262, 80, 23);

        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("cc.");
        jLabel60.setName("jLabel60"); // NOI18N
        panelGlass7.add(jLabel60);
        jLabel60.setBounds(790, 262, 20, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("BB Masuk RS :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelGlass7.add(jLabel24);
        jLabel24.setBounds(360, 66, 90, 23);

        Tbb.setForeground(new java.awt.Color(0, 0, 0));
        Tbb.setToolTipText("Jika pakai koma ganti dengan titik, sebagai gantinya koma");
        Tbb.setName("Tbb"); // NOI18N
        Tbb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TbbKeyPressed(evt);
            }
        });
        panelGlass7.add(Tbb);
        Tbb.setBounds(456, 66, 50, 23);

        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel61.setText("Kg.  Umur :");
        jLabel61.setName("jLabel61"); // NOI18N
        panelGlass7.add(jLabel61);
        jLabel61.setBounds(512, 66, 60, 23);

        jLabel62.setForeground(new java.awt.Color(0, 0, 0));
        jLabel62.setText("Kode Pemantauan :");
        jLabel62.setName("jLabel62"); // NOI18N
        panelGlass7.add(jLabel62);
        jLabel62.setBounds(200, 38, 110, 23);

        TkdPantau.setEditable(false);
        TkdPantau.setForeground(new java.awt.Color(0, 0, 0));
        TkdPantau.setName("TkdPantau"); // NOI18N
        panelGlass7.add(TkdPantau);
        TkdPantau.setBounds(314, 38, 165, 23);

        BtnTotOutput.setForeground(new java.awt.Color(0, 0, 0));
        BtnTotOutput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnTotOutput.setText("TOTAL OUTPUT :");
        BtnTotOutput.setToolTipText("");
        BtnTotOutput.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnTotOutput.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnTotOutput.setName("BtnTotOutput"); // NOI18N
        BtnTotOutput.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnTotOutput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTotOutputActionPerformed(evt);
            }
        });
        BtnTotOutput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnTotOutputKeyPressed(evt);
            }
        });
        panelGlass7.add(BtnTotOutput);
        BtnTotOutput.setBounds(570, 234, 130, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Jml. Parental / Line :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelGlass7.add(jLabel26);
        jLabel26.setBounds(390, 178, 110, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("IWL :");
        jLabel56.setName("jLabel56"); // NOI18N
        panelGlass7.add(jLabel56);
        jLabel56.setBounds(380, 262, 62, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("TOTAL INTAKE :");
        jLabel27.setName("jLabel27"); // NOI18N
        panelGlass7.add(jLabel27);
        jLabel27.setBounds(610, 178, 90, 23);

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Rg. Rawat : ");
        jLabel63.setName("jLabel63"); // NOI18N
        panelGlass7.add(jLabel63);
        jLabel63.setBounds(480, 38, 72, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        panelGlass7.add(TrgRawat);
        TrgRawat.setBounds(553, 38, 250, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Transfusi :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelGlass7.add(jLabel43);
        jLabel43.setBounds(115, 206, 80, 23);

        Ttransfusi.setForeground(new java.awt.Color(0, 0, 0));
        Ttransfusi.setName("Ttransfusi"); // NOI18N
        Ttransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtransfusiKeyPressed(evt);
            }
        });
        panelGlass7.add(Ttransfusi);
        Ttransfusi.setBounds(200, 206, 55, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel64.setText("cc.");
        jLabel64.setName("jLabel64"); // NOI18N
        panelGlass7.add(jLabel64);
        jLabel64.setBounds(260, 206, 17, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Reaksi Transfusi :");
        jLabel65.setName("jLabel65"); // NOI18N
        panelGlass7.add(jLabel65);
        jLabel65.setBounds(270, 206, 100, 23);

        TreaksiTransfusi.setForeground(new java.awt.Color(0, 0, 0));
        TreaksiTransfusi.setName("TreaksiTransfusi"); // NOI18N
        TreaksiTransfusi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreaksiTransfusiKeyPressed(evt);
            }
        });
        panelGlass7.add(TreaksiTransfusi);
        TreaksiTransfusi.setBounds(375, 206, 410, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel66.setText("cc.");
        jLabel66.setName("jLabel66"); // NOI18N
        panelGlass7.add(jLabel66);
        jLabel66.setBounds(220, 290, 17, 23);

        Tlain.setForeground(new java.awt.Color(0, 0, 0));
        Tlain.setName("Tlain"); // NOI18N
        Tlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeyPressed(evt);
            }
        });
        panelGlass7.add(Tlain);
        Tlain.setBounds(160, 290, 55, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Lain-lain :");
        jLabel67.setName("jLabel67"); // NOI18N
        panelGlass7.add(jLabel67);
        jLabel67.setBounds(95, 290, 60, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Catatan :");
        jLabel68.setName("jLabel68"); // NOI18N
        panelGlass7.add(jLabel68);
        jLabel68.setBounds(255, 290, 60, 23);

        Tcatatan.setForeground(new java.awt.Color(0, 0, 0));
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        panelGlass7.add(Tcatatan);
        Tcatatan.setBounds(320, 290, 465, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Petugas :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelGlass7.add(jLabel30);
        jLabel30.setBounds(0, 318, 110, 23);

        Tnip.setEditable(false);
        Tnip.setForeground(new java.awt.Color(0, 0, 0));
        Tnip.setName("Tnip"); // NOI18N
        panelGlass7.add(Tnip);
        Tnip.setBounds(115, 318, 170, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        panelGlass7.add(TnmPetugas);
        TnmPetugas.setBounds(289, 318, 390, 23);

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
        panelGlass7.add(BtnPetugas);
        BtnPetugas.setBounds(680, 318, 28, 23);

        chkSaya.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya.setText("Saya Sendiri");
        chkSaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya.setName("chkSaya"); // NOI18N
        chkSaya.setOpaque(false);
        chkSaya.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSayaActionPerformed(evt);
            }
        });
        panelGlass7.add(chkSaya);
        chkSaya.setBounds(715, 318, 87, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Per : ");
        jLabel69.setName("jLabel69"); // NOI18N
        panelGlass7.add(jLabel69);
        jLabel69.setBounds(525, 262, 35, 23);

        cmbKali.setForeground(new java.awt.Color(0, 0, 0));
        cmbKali.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));
        cmbKali.setName("cmbKali"); // NOI18N
        cmbKali.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbKaliMouseReleased(evt);
            }
        });
        cmbKali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKaliActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbKali);
        cmbKali.setBounds(563, 262, 45, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText("Jam");
        jLabel70.setName("jLabel70"); // NOI18N
        panelGlass7.add(jLabel70);
        jLabel70.setBounds(613, 262, 25, 23);

        Toksigen.setForeground(new java.awt.Color(0, 0, 0));
        Toksigen.setName("Toksigen"); // NOI18N
        Toksigen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ToksigenKeyPressed(evt);
            }
        });
        panelGlass7.add(Toksigen);
        Toksigen.setBounds(460, 150, 60, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel71.setText("Lpm");
        jLabel71.setName("jLabel71"); // NOI18N
        panelGlass7.add(jLabel71);
        jLabel71.setBounds(527, 150, 28, 23);

        BtnNilaiMAP.setForeground(new java.awt.Color(0, 0, 0));
        BtnNilaiMAP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNilaiMAP.setText("Nilai MAP :");
        BtnNilaiMAP.setToolTipText("");
        BtnNilaiMAP.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnNilaiMAP.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BtnNilaiMAP.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnNilaiMAP.setName("BtnNilaiMAP"); // NOI18N
        BtnNilaiMAP.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnNilaiMAP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNilaiMAPActionPerformed(evt);
            }
        });
        panelGlass7.add(BtnNilaiMAP);
        BtnNilaiMAP.setBounds(385, 122, 100, 23);

        Tmap.setEditable(false);
        Tmap.setForeground(new java.awt.Color(0, 0, 0));
        Tmap.setToolTipText("Mean arterial pressure (MAP)");
        Tmap.setName("Tmap"); // NOI18N
        panelGlass7.add(Tmap);
        Tmap.setBounds(489, 122, 70, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel73.setText("mmHg");
        jLabel73.setName("jLabel73"); // NOI18N
        panelGlass7.add(jLabel73);
        jLabel73.setBounds(565, 122, 40, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Sistole :");
        jLabel74.setName("jLabel74"); // NOI18N
        panelGlass7.add(jLabel74);
        jLabel74.setBounds(115, 122, 50, 23);

        Tsistole.setForeground(new java.awt.Color(0, 0, 0));
        Tsistole.setName("Tsistole"); // NOI18N
        Tsistole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsistoleKeyPressed(evt);
            }
        });
        panelGlass7.add(Tsistole);
        Tsistole.setBounds(170, 122, 50, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setText("Diastole :");
        jLabel75.setName("jLabel75"); // NOI18N
        panelGlass7.add(jLabel75);
        jLabel75.setBounds(220, 122, 60, 23);

        Tdistole.setForeground(new java.awt.Color(0, 0, 0));
        Tdistole.setName("Tdistole"); // NOI18N
        Tdistole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdistoleKeyPressed(evt);
            }
        });
        panelGlass7.add(Tdistole);
        Tdistole.setBounds(286, 122, 50, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText("mmHg");
        jLabel76.setName("jLabel76"); // NOI18N
        panelGlass7.add(jLabel76);
        jLabel76.setBounds(343, 122, 40, 23);

        TlabelTensi.setForeground(new java.awt.Color(0, 0, 0));
        TlabelTensi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        TlabelTensi.setText("Tensi : 0 mmHg");
        TlabelTensi.setName("TlabelTensi"); // NOI18N
        panelGlass7.add(TlabelTensi);
        TlabelTensi.setBounds(615, 122, 170, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setToolTipText("");
        Tumur.setName("Tumur"); // NOI18N
        panelGlass7.add(Tumur);
        Tumur.setBounds(570, 66, 60, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setText("Kg BB/Hari (IWL) : ");
        jLabel78.setName("jLabel78"); // NOI18N
        panelGlass7.add(jLabel78);
        jLabel78.setBounds(630, 66, 100, 23);

        cmbPerhari.setForeground(new java.awt.Color(0, 0, 0));
        cmbPerhari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "10", "15", "20", "30", "40", "50" }));
        cmbPerhari.setName("cmbPerhari"); // NOI18N
        cmbPerhari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPerhariActionPerformed(evt);
            }
        });
        panelGlass7.add(cmbPerhari);
        cmbPerhari.setBounds(732, 66, 45, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel79.setText("Cc.");
        jLabel79.setName("jLabel79"); // NOI18N
        panelGlass7.add(jLabel79);
        jLabel79.setBounds(783, 66, 25, 23);

        panelGlass10.add(panelGlass7, java.awt.BorderLayout.CENTER);

        Scroll1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " [ Parental / Line / Obat-obatan ] ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(640, 422));

        tbParental.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbParental.setName("tbParental"); // NOI18N
        tbParental.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbParentalMouseClicked(evt);
            }
        });
        Scroll1.setViewportView(tbParental);

        panelGlass10.add(Scroll1, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPantau.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPantau.setComponentPopupMenu(jPopupMenu1);
        tbPantau.setName("tbPantau"); // NOI18N
        tbPantau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPantauMouseClicked(evt);
            }
        });
        tbPantau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPantauKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbPantauKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbPantau);

        panelGlass11.add(Scroll, java.awt.BorderLayout.CENTER);

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemantauan Harian Pasien ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(487, 140));

        tbTotal.setToolTipText("");
        tbTotal.setName("tbTotal"); // NOI18N
        Scroll3.setViewportView(tbTotal);

        panelGlass11.add(Scroll3, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(panelGlass11, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
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

        BtnParental.setForeground(new java.awt.Color(0, 0, 0));
        BtnParental.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        BtnParental.setMnemonic('P');
        BtnParental.setText("Parental/Line/Obat");
        BtnParental.setToolTipText("Alt+P");
        BtnParental.setGlassColor(new java.awt.Color(255, 204, 0));
        BtnParental.setName("BtnParental"); // NOI18N
        BtnParental.setPreferredSize(new java.awt.Dimension(155, 30));
        BtnParental.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnParentalActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnParental);

        BtnGrafik.setForeground(new java.awt.Color(0, 0, 0));
        BtnGrafik.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Bar Chart (copy).png"))); // NOI18N
        BtnGrafik.setMnemonic('G');
        BtnGrafik.setText("Grafik Pemantauan");
        BtnGrafik.setToolTipText("Alt+G");
        BtnGrafik.setName("BtnGrafik"); // NOI18N
        BtnGrafik.setPreferredSize(new java.awt.Dimension(160, 30));
        BtnGrafik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGrafikActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnGrafik);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 3, 9));

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Tanggal :");
        jLabel28.setName("jLabel28"); // NOI18N
        jLabel28.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel28);

        DTPCariA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-08-2024" }));
        DTPCariA.setDisplayFormat("dd-MM-yyyy");
        DTPCariA.setName("DTPCariA"); // NOI18N
        DTPCariA.setOpaque(false);
        DTPCariA.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCariA);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("s.d.");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel29);

        DTPCariB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "28-08-2024" }));
        DTPCariB.setDisplayFormat("dd-MM-yyyy");
        DTPCariB.setName("DTPCariB"); // NOI18N
        DTPCariB.setOpaque(false);
        DTPCariB.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCariB);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+1");
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

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

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
        scrollPane5.setPreferredSize(new java.awt.Dimension(212, 400));

        Thasil.setEditable(false);
        Thasil.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Thasil.setColumns(20);
        Thasil.setRows(5);
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
        Tinstruksi.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Tinstruksi.setName("Tinstruksi"); // NOI18N
        Tinstruksi.setPreferredSize(new java.awt.Dimension(202, 4000));
        scrollPane4.setViewportView(Tinstruksi);

        panelGlass14.add(scrollPane4, java.awt.BorderLayout.CENTER);

        FormMenu.add(panelGlass14);

        PanelAccor.add(FormMenu, java.awt.BorderLayout.CENTER);

        getContentPane().add(PanelAccor, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (Tnadi.getText().trim().equals("")) {
            Valid.textKosong(Tnadi, "Nadi");
            Tnadi.requestFocus();
        } else if (Tsuhu.getText().trim().equals("")) {
            Valid.textKosong(Tsuhu, "Suhu");
            Tsuhu.requestFocus();
        } else {
            tglBekasInput = "";
            simpan();
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
        tampilTotal24Jam(tglPANTAU, norawatPANTAU);
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
            tampil();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnEdit);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (pilih == 1) {
            if (!wktSimpan.equals("")) {
                if (Tnadi.getText().trim().equals("")) {
                    Valid.textKosong(Tnadi, "Nadi");
                    Tnadi.requestFocus();
                } else if (Tsuhu.getText().trim().equals("")) {
                    Valid.textKosong(Tsuhu, "Suhu");
                    Tsuhu.requestFocus();
                } else if (Tsistole.getText().equals("0") && Tdistole.getText().equals("0")) {
                    JOptionPane.showMessageDialog(rootPane, "Angka sistole & diastole masih terisi angka 0, sesuaikan dulu dengan tensinya..!!");
                    Tsistole.requestFocus();
                } else {
                    tglBekasInput = "";
                    gantiPemantauan();
                    tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
                    emptTeks();
                    Valid.SetTgl(DTPCariA, tglBekasInput);
                    tampil();
                    tampilTotal24Jam(tglPANTAU, norawatPANTAU);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        } else if (pilih == 2) {
            if (tbParental.getSelectedRowCount() == 0 || dataParental.equals("")) {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
                tbParental.requestFocus();
            } else {
                gantiParental();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptTeks();
        dispose();
        WindowParental.dispose();
        WindowEWS.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
            WindowParental.dispose();
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            tbPantau.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
        tampilTotal24Jam(tglPANTAU,norawatPANTAU);
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void tbPantauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPantauMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPantauMouseClicked

    private void tbPantauKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyPressed
        if (tabMode.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
}//GEN-LAST:event_tbPantauKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        emptTeks();
        tampilTotal24Jam(tglPANTAU,norawatPANTAU);
    }//GEN-LAST:event_formWindowOpened

    private void tbPantauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPantauKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }          
        }
    }//GEN-LAST:event_tbPantauKeyReleased

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void TnadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnadiKeyPressed
        Valid.pindah(evt, tglPantau, Tsuhu);
    }//GEN-LAST:event_TnadiKeyPressed

    private void TsuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsuhuKeyPressed
        Valid.pindah(evt, Tnadi, cmbJam);
    }//GEN-LAST:event_TsuhuKeyPressed

    private void TbbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbbKeyPressed
        Valid.pindah(evt, cmbJam, Tgcse);
    }//GEN-LAST:event_TbbKeyPressed

    private void TgcseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcseKeyPressed
        Valid.pindah(evt, Tbb, Tgcsm);
    }//GEN-LAST:event_TgcseKeyPressed

    private void TgcsmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsmKeyPressed
        Valid.pindah(evt, Tgcse, Tgcsv);
    }//GEN-LAST:event_TgcsmKeyPressed

    private void TgcsvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgcsvKeyPressed
        Valid.pindah(evt, Tgcsm, Tkesadaran);
    }//GEN-LAST:event_TgcsvKeyPressed

    private void TkesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TkesadaranKeyPressed
        Valid.pindah(evt, Tgcsv, Tsistole);
    }//GEN-LAST:event_TkesadaranKeyPressed

    private void TrrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TrrKeyPressed
        Valid.pindah(evt, Tdistole, Tspo);
    }//GEN-LAST:event_TrrKeyPressed

    private void TspoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TspoKeyPressed
        Valid.pindah(evt, Trr, Toksigen);
    }//GEN-LAST:event_TspoKeyPressed

    private void TmmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmmKeyPressed
        Valid.pindah(evt, Tspo, Tngt);
    }//GEN-LAST:event_TmmKeyPressed

    private void TngtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtKeyPressed
        Valid.pindah(evt, Tmm, Ttransfusi);
    }//GEN-LAST:event_TngtKeyPressed

    private void TurinKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TurinKeyPressed
        Valid.pindah(evt, Tngt, TngtDarah);
    }//GEN-LAST:event_TurinKeyPressed

    private void TngtDarahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TngtDarahKeyPressed
        Valid.pindah(evt, Turin, Tdrain);
    }//GEN-LAST:event_TngtDarahKeyPressed

    private void TdrainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdrainKeyPressed
        Valid.pindah(evt, TngtDarah, Tmuntah);
    }//GEN-LAST:event_TdrainKeyPressed

    private void TmuntahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmuntahKeyPressed
        Valid.pindah(evt, Tdrain, Tbab);
    }//GEN-LAST:event_TmuntahKeyPressed

    private void TbabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TbabKeyPressed
        Valid.pindah(evt, Tmuntah, Tlain);
    }//GEN-LAST:event_TbabKeyPressed

    private void tbParentalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbParentalMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                pilih = 0;
                dataParental = "";
                dataParental = tbParental.getValueAt(tbParental.getSelectedRow(), 3).toString();
                pilih = 2;
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbParentalMouseClicked

    private void BtnTotOutputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTotOutputActionPerformed
        ChkAccor.setSelected(false);
        isMenu();        
        hitungParental();
        hitungIntake();
        hitungIWL();
        hitungOutput();
        hitungBalance();
    }//GEN-LAST:event_BtnTotOutputActionPerformed

    private void BtnTotOutputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnTotOutputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnTotOutputActionPerformed(null);
        }
    }//GEN-LAST:event_BtnTotOutputKeyPressed

    private void BtnParentalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnParentalActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu data pasienya...!!!!");
        } else {
            ChkAccor.setSelected(false);
            isMenu();
            WindowParental.setSize(820, 480);
            WindowParental.setLocationRelativeTo(internalFrame1);
            WindowParental.setAlwaysOnTop(false);
            WindowParental.setVisible(true);

            if (Sequel.cariInteger("select count(-1) from pemberian_obat where no_rawat='" + TNoRw.getText() + "' and nama_obat<>'-'") > 0) {
                Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_pemberian from pemberian_obat where "
                        + "no_rawat='" + TNoRw.getText() + "' order by tgl_pemberian desc limit 1"));
            } else {
                DTPCari1.setDate(new Date());
            }
            
            BtnBatal1ActionPerformed(null);
            DTPCari2.setDate(new Date());
            tampilObatTerjadwal();
        }
    }//GEN-LAST:event_BtnParentalActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (pilih == 1) {
            if (tbPantau.getSelectedRow() > -1) {
                x = JOptionPane.showConfirmDialog(rootPane, "Apakah pemantauan harian tgl. " + tglPantau.getSelectedItem().toString() + " jam "
                        + tbPantau.getValueAt(tbPantau.getSelectedRow(), 5).toString() + " akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    hapusPemantauan();
                } else {
                    tampil();
                    emptTeks();
                    tampilTotal24Jam(tglPANTAU, norawatPANTAU);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih salah satu datanya terlebih dahulu..!!");
            }
        } else if (pilih == 2) {
            hapusParental();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu datanya pada tabel utk. dihapus...!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                TnmObat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
                TjmlBeri.requestFocus();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbObatMouseClicked

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilObatTerjadwal();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (TnmObat.getText().trim().equals("")) {
            Valid.textKosong(TnmObat, "Nama Obat Dipilih");
            TnmObat.requestFocus();
        } else if (TjmlBeri.getText().trim().equals("")) {
            Valid.textKosong(TjmlBeri, "Jml. Pemberian");
            TjmlBeri.requestFocus();
        } else {
            if (TjmlBeri.getText().contains(",") == true) {
                TjmlBeri.setText(TjmlBeri.getText().replaceAll(",", "."));
            }
            
            tabMode1.addRow(new String[]{
                tglPantau.getSelectedItem().toString(), TnmObat.getText(), TjmlBeri.getText(), TkdPantau.getText(),
                Valid.SetTgl(tglPantau.getSelectedItem() + "")});

            tampilObatTerjadwal();
            BtnBatal1ActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatal1ActionPerformed
        pilih = 0;
        TnmObat.setText("");
        TjmlBeri.setText("");
        pilih = 1;
        TnmObat.requestFocus();
    }//GEN-LAST:event_BtnBatal1ActionPerformed

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        WindowParental.dispose();
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah obat yang sudah ditambahkan/diganti akan dibatalkan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (!TkdPantau.getText().equals("")) {
                if (Sequel.cariInteger("select count(-1) from pemantauan_harian_parental where "
                        + "tgl_pantau='" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "' and kd_pantau='" + TkdPantau.getText() + "'") > 0) {
                    tampilParental();
                }
            }
        } else {
            tglBekasInput = "";
            gantiPemantauan();
            tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");            
            Valid.SetTgl(DTPCariA, tglBekasInput);
            tampil();
            tampilTotal24Jam(tglPANTAU, norawatPANTAU);
        }
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void TnmObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmObatKeyPressed
        Valid.pindah(evt,TnmObat,TjmlBeri);
    }//GEN-LAST:event_TnmObatKeyPressed

    private void TjmlBeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjmlBeriKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan1ActionPerformed(null);
            BtnSimpan1.requestFocus();
        }
    }//GEN-LAST:event_TjmlBeriKeyPressed

    private void WindowParentalWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WindowParentalWindowOpened
        tampil();
    }//GEN-LAST:event_WindowParentalWindowOpened

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

    private void BtnGrafikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGrafikActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else if (tbPantau.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Grafik hasil pemantauan harian pasien tdk. ditemukan, cek lagi tanggalnya..!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ChkAccor.setSelected(false);
            isMenu();
            akses.setform("RMPemantauanHarian24Jam");
            RMGrafikPemantauanHarian24Jam form = new RMGrafikPemantauanHarian24Jam(null, false);
            form.setData(TNoRw.getText());
            form.setSize(747, 71);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_BtnGrafikActionPerformed

    private void MnHapusSemuaPemantauanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusSemuaPemantauanActionPerformed
        if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + TNoRw.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Data pemantauan harian pasien utk. no. rawat " + TNoRw.getText() + " tdk. ditemukan..!!");
        } else {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin semua data pemantauan harian pasien utk. no. rawat " + TNoRw.getText() + " akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from pemantauan_harian_24jam where no_rawat=?", 1, new String[]{
                    TNoRw.getText()
                }) == true) {
                    Sequel.meghapus("pemantauan_harian_parental", "no_rawat", TNoRw.getText());

                    tampil();
                    emptTeks();
                    tampilTotal24Jam(tglPANTAU, norawatPANTAU);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
                tampilTotal24Jam(tglPANTAU, norawatPANTAU);
            }
        }
    }//GEN-LAST:event_MnHapusSemuaPemantauanActionPerformed

    private void TtransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtransfusiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungParental();
            BtnTotOutputActionPerformed(null);
            TreaksiTransfusi.requestFocus();
        }
    }//GEN-LAST:event_TtransfusiKeyPressed

    private void TreaksiTransfusiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreaksiTransfusiKeyPressed
        Valid.pindah(evt, Ttransfusi, Turin);
    }//GEN-LAST:event_TreaksiTransfusiKeyPressed

    private void TlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            hitungIWL();
            Tcatatan.requestFocus();
        }
    }//GEN-LAST:event_TlainKeyPressed

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TcatatanKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMPemantauanHarian24Jam");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                Tnip.setText("-");
                TnmPetugas.setText("-");
            } else {
                Tnip.setText(akses.getkode());
                TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + Tnip.getText() + "'"));
            }
        } else {
            Tnip.setText("-");
            TnmPetugas.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void cmbKaliMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbKaliMouseReleased
        AutoCompleteDecorator.decorate(cmbKali);
    }//GEN-LAST:event_cmbKaliMouseReleased

    private void cmbKaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKaliActionPerformed
        hitungIWL();
    }//GEN-LAST:event_cmbKaliActionPerformed

    private void BtnNotepadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNotepadActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ChkAccor.setSelected(false);
        isMenu();
        akses.setform("RMPemantauanHarian24Jam");
        DlgNotepad form = new DlgNotepad(null, false);
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setData(akses.getkode());
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnNotepadActionPerformed

    private void ToksigenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ToksigenKeyPressed
        Valid.pindah(evt, Tspo, Tmm);
    }//GEN-LAST:event_ToksigenKeyPressed

    private void MnSimpanEWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanEWSActionPerformed
        if (!wktSimpan.equals("")) {
            if (Sequel.cariIsi("select nm_gedung from bangsal where nm_bangsal='" + TrgRawat.getText() + "'").equals("ANAK")) {
                JOptionPane.showMessageDialog(rootPane, "Data pemantauan harian pasien ruang anak tidak bisa disimpan ke monitoring EWS..!!");
            } else if (Tsistole.getText().equals("0") && Tdistole.getText().equals("0")) {
                JOptionPane.showMessageDialog(rootPane, "Angka sistole & diastole masih terisi angka 0, sesuaikan dulu dengan tensinya..!!");
                Tsistole.requestFocus();
            } else if (Trr.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Angka frekuensi nafas harus terisi dulu..!!");
                Trr.requestFocus();
            } else if (Tspo.getText().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Angka SPO2 atau saturasi harus terisi dulu..!!");
                Tspo.requestFocus();
            } else if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where waktu_simpan='" + wktSimpan + "' and sistole='0'") > 0) {
                JOptionPane.showMessageDialog(rootPane, "Angka sistole belum tersimpan, setelah merubah data klik tombol ganti dulu..!!");
            } else {
                WindowEWS.setSize(392, 169);
                WindowEWS.setLocationRelativeTo(internalFrame1);
                WindowEWS.setVisible(true);

                if (cmbJam.getSelectedItem().equals("7")) {
                    cmbJam1.setSelectedItem("07");
                } else if (cmbJam.getSelectedItem().equals("8")) {
                    cmbJam1.setSelectedItem("08");
                } else if (cmbJam.getSelectedItem().equals("9")) {
                    cmbJam1.setSelectedItem("09");
                } else if (cmbJam.getSelectedItem().equals("24")) {
                    cmbJam1.setSelectedItem("00");
                } else {
                    cmbJam1.setSelectedItem(cmbJam.getSelectedItem().toString());
                }

                cmbMnt1.setSelectedIndex(0);
                cmbDtk1.setSelectedIndex(0);
                cmbSuplemen.setSelectedIndex(0);
                cmbKesadaran.setSelectedIndex(0);
                Tgds.setText("");
                TskorNyeri.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya pada tabel terlebih dahulu..!!");
            tbPantau.requestFocus();
        }
    }//GEN-LAST:event_MnSimpanEWSActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowEWS.dispose();
        tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
        emptTeks();
        Valid.SetTgl(DTPCariA, tglBekasInput);
        tampil();
        tampilTotal24Jam(tglPANTAU, norawatPANTAU);
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        simpanEWS();
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void TskorNyeriKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TskorNyeriKeyPressed
        Valid.pindah(evt, TskorNyeri, BtnSimpan4);
    }//GEN-LAST:event_TskorNyeriKeyPressed

    private void TgdsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TgdsKeyPressed
        Valid.pindah(evt, Tgds, TskorNyeri);
    }//GEN-LAST:event_TgdsKeyPressed

    private void MnMonitoringEWSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMonitoringEWSActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan anda pilih dulu salah satu datanya pada tabel...!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMPemantauanHarian24Jam");
            RMMonitoringEWSDewasa form = new RMMonitoringEWSDewasa(null, false);
            form.emptTeks();
            form.isCek();
            form.setData(TNoRw.getText(), TNoRm.getText(), TPasien.getText(), TrgRawat.getText());
            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnMonitoringEWSActionPerformed

    private void BtnNilaiMAPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNilaiMAPActionPerformed
        hitungMAP();
    }//GEN-LAST:event_BtnNilaiMAPActionPerformed

    private void TsistoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsistoleKeyPressed
        Valid.pindah(evt, Tkesadaran, Tdistole);
    }//GEN-LAST:event_TsistoleKeyPressed

    private void TdistoleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdistoleKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnNilaiMAPActionPerformed(null);
            Trr.requestFocus();
        }
    }//GEN-LAST:event_TdistoleKeyPressed

    private void cmbPerhariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPerhariActionPerformed
        if (cmbPerhari.getSelectedIndex() != 0) {
            hitungIWL();
        }
    }//GEN-LAST:event_cmbPerhariActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPemantauanHarian24Jam dialog = new RMPemantauanHarian24Jam(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnBatal1;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnEdit;
    private widget.Button BtnGrafik;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnNilaiMAP;
    private widget.Button BtnNotepad;
    private widget.Button BtnParental;
    private widget.Button BtnPetugas;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpan4;
    private widget.Button BtnTotOutput;
    public widget.CekBox ChkAccor;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCariA;
    private widget.Tanggal DTPCariB;
    private widget.PanelBiasa FormMenu;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnHapusSemuaPemantauan;
    private javax.swing.JMenuItem MnMonitoringEWS;
    private javax.swing.JMenuItem MnSimpanEWS;
    private widget.PanelBiasa PanelAccor;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    public widget.TextBox TCari;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tbab;
    private widget.TextBox Tbalance;
    private widget.TextBox Tbb;
    private widget.TextBox Tcatatan;
    private widget.TextBox Tdistole;
    private widget.TextBox Tdrain;
    private widget.TextBox Tgcse;
    private widget.TextBox Tgcsm;
    private widget.TextBox Tgcsv;
    private widget.TextBox Tgds;
    private widget.TextArea Thasil;
    private widget.TextArea Tinstruksi;
    private widget.TextBox Tiwl;
    private widget.TextBox TjmlBeri;
    private widget.TextBox TjmlParental;
    private widget.TextBox TkdPantau;
    private widget.TextBox Tkesadaran;
    private widget.Label TlabelTensi;
    private widget.TextBox Tlain;
    private widget.TextBox Tmap;
    private widget.TextBox Tmm;
    private widget.TextBox Tmuntah;
    private widget.TextBox Tnadi;
    private widget.TextBox Tngt;
    private widget.TextBox TngtDarah;
    private widget.TextBox Tnip;
    private widget.TextBox TnmObat;
    private widget.TextBox TnmPetugas;
    private widget.TextBox Toksigen;
    private widget.TextBox TreaksiTransfusi;
    private widget.TextBox TrgRawat;
    private widget.TextBox Trr;
    private widget.TextBox Tsistole;
    private widget.TextBox TskorNyeri;
    private widget.TextBox Tspo;
    private widget.TextBox Tsuhu;
    private widget.TextBox Ttotintake;
    private widget.TextBox Ttotouput;
    private widget.TextBox Ttransfusi;
    private widget.TextBox Tumur;
    private widget.TextBox Turin;
    private javax.swing.JDialog WindowEWS;
    private javax.swing.JDialog WindowParental;
    private widget.CekBox chkSaya;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbKali;
    private widget.ComboBox cmbKesadaran;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbPerhari;
    private widget.ComboBox cmbSuplemen;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.Label jLabel10;
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
    private widget.Label jLabel3;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel35;
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
    private widget.Label jLabel78;
    private widget.Label jLabel79;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass14;
    private widget.panelisi panelGlass7;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.Table tbCPPT;
    private widget.Table tbObat;
    private widget.Table tbPantau;
    private widget.Table tbParental;
    private widget.Table tbTotal;
    private widget.Tanggal tglPantau;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        tglPANTAU = "";
        norawatPANTAU = "";
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ph.no_rawat, ph.kd_pantau, p.no_rkm_medis, p.nm_pasien, date_format(ph.tgl_pantau,'%d-%m-%Y') tglPantau, "
                    + "ph.jam, ph.nadi, ph.suhu, if(ph.gcs_e='' and ph.gcs_m='' and ph.gcs_v='','',concat('E : ',ph.gcs_e,', M : ',ph.gcs_m,', V : ',ph.gcs_v)) gcs, "
                    + "ph.kesadaran, if(ph.map<>'0',concat(ph.td,' (',ph.map,')'),ph.td) td, ph.nafas, ph.td tensi, "
                    + "if(ph.oksigen<>'',concat(ph.spo2,' % (',ph.oksigen,' Lpm)'),concat(ph.spo2,' %')) spo_oksigen, ph.makan_minum, ph.ngt, "
                    + "ph.total_parental, ph.total_intake, ph.urine, ph.ngt_darah, ph.drain, ph.muntah, ph.bab, ph.iwl, ph.total_output, ph.balance, ph.gcs_e, "
                    + "ph.gcs_m, ph.gcs_v, ph.tgl_pantau, ph.urutan_jam, ph.waktu_simpan, ph.bb_msk_rs, ph.transfusi, ph.reaksi_transfusi, ph.defekasi, "
                    + "ph.defekasi lainlain, ph.catatan, ph.nip_petugas, pg.nama, ph.pengali, ph.oksigen, ph.spo2, ph.sistole, ph.distole, ph.map, "
                    + "ph.ruang_rawat, ph.pengali_iwl_bb from pemantauan_harian_24jam ph inner join reg_periksa rp on rp.no_rawat=ph.no_rawat "
                    + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=ph.nip_petugas where "
                    + "ph.tgl_pantau between ? and ? and ph.no_rawat like '%" + TNoRw.getText() + "%' and ph.kd_pantau like ? or "
                    + "ph.tgl_pantau between ? and ? and ph.no_rawat like '%" + TNoRw.getText() + "%' and p.no_rkm_medis like ? or "
                    + "ph.tgl_pantau between ? and ? and ph.no_rawat like '%" + TNoRw.getText() + "%' and p.nm_pasien like ? or "
                    + "ph.tgl_pantau between ? and ? and ph.no_rawat like '%" + TNoRw.getText() + "%' and pg.nama like ? "
                    + "order by ph.no_rawat, ph.tgl_pantau, ph.urutan_jam");
            try {
                ps.setString(1, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");
                ps.setString(4, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCariA.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCariB.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    tglPANTAU = rs.getString("tgl_pantau");
                    norawatPANTAU = rs.getString("no_rawat");
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("kd_pantau"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglPantau"),
                        rs.getString("jam"),
                        rs.getString("nadi"),
                        rs.getString("suhu"),
                        rs.getString("gcs"),
                        rs.getString("kesadaran"),
                        rs.getString("td"),
                        rs.getString("nafas"),
                        rs.getString("spo_oksigen"),
                        rs.getString("makan_minum"),
                        rs.getString("ngt"),
                        rs.getString("total_parental"),
                        rs.getString("total_intake"),
                        rs.getString("urine"),
                        rs.getString("ngt_darah"),
                        rs.getString("drain"),
                        rs.getString("muntah"),
                        rs.getString("bab"),
                        rs.getString("lainlain"),
                        rs.getString("iwl"),
                        rs.getString("total_output"),
                        rs.getString("balance"),
                        rs.getString("gcs_e"),
                        rs.getString("gcs_m"),
                        rs.getString("gcs_v"),
                        rs.getString("tgl_pantau"),
                        rs.getString("urutan_jam"),
                        rs.getString("waktu_simpan"),
                        rs.getString("bb_msk_rs"),
                        rs.getString("transfusi"),
                        rs.getString("reaksi_transfusi"),
                        rs.getString("defekasi"),
                        rs.getString("catatan"),
                        rs.getString("nip_petugas"),
                        rs.getString("nama"),
                        rs.getString("pengali"),
                        rs.getString("oksigen"),
                        rs.getString("spo2"),
                        rs.getString("sistole"),
                        rs.getString("distole"),
                        rs.getString("map"),
                        rs.getString("tensi"),
                        rs.getString("ruang_rawat"),
                        rs.getString("pengali_iwl_bb")
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
        tglPantau.setDate(new Date());
        tglPantau.requestFocus();
        Valid.SetTgl(DTPCariB, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));        
        Tnadi.setText("");        
        Tsuhu.setText("");
        cmbJam.setSelectedIndex(0);
        Tgcse.setText("");
        Tgcsm.setText("");
        Tgcsv.setText("");
        Tkesadaran.setText("");
        TlabelTensi.setText("Tensi : 0 mmHg");
        Tsistole.setText("");
        Tdistole.setText("");
        Tmap.setText("");
        Trr.setText("");
        Tspo.setText("");
        Toksigen.setText("");
        Tmm.setText("");
        Tngt.setText("");
        TjmlParental.setText("");
        Ttotintake.setText("");
        Turin.setText("");
        TngtDarah.setText("");
        Tdrain.setText("");
        Tmuntah.setText("");
        Tbab.setText("");
        Tiwl.setText("");
        Ttotouput.setText("");
        Tbalance.setText("");
        Ttransfusi.setText("");
        TreaksiTransfusi.setText("");
        Tlain.setText("");
        Tcatatan.setText("");
        Tnip.setText("-");
        TnmPetugas.setText("-");  
        chkSaya.setSelected(false);
        cmbKali.setSelectedIndex(0);
        urutanJam = "";
        dataParental = "";
        wktSimpan = "";
        pilih = 0;
        Valid.tabelKosong(tabMode1);
        Valid.tabelKosong(tabMode3);
        autoNomorPerJam();
    }

    private void getData() {
        pilih = 0;
        wktSimpan = "";
        pilih = 1;
        if (tbPantau.getSelectedRow() != -1) {
            TNoRw.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),0).toString());
            TkdPantau.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),1).toString());
            TNoRm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(), 2).toString());
            TPasien.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(), 3).toString());
            TrgRawat.setText(Sequel.cariIsi("select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' and "
                    + "ki.tgl_masuk='" + tbPantau.getValueAt(tbPantau.getSelectedRow(), 29).toString() + "' "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1"));
            Valid.SetTgl(tglPantau, tbPantau.getValueAt(tbPantau.getSelectedRow(), 29).toString());
            cmbJam.setSelectedItem(tbPantau.getValueAt(tbPantau.getSelectedRow(), 5).toString());
            Tnadi.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),6).toString());
            Tsuhu.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),7).toString());
            Tbb.setText(Sequel.cariIsi("select ifnull(bb_msk_rs,'0') from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + TNoRw.getText() + "'"));
            Tgcse.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),26).toString());
            Tgcsm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),27).toString());
            Tgcsv.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),28).toString());
            Tkesadaran.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),9).toString());
            Trr.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),11).toString());
            Tspo.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),41).toString());
            Tmm.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),13).toString());
            Tngt.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),14).toString());
            TjmlParental.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),15).toString());
            Ttotintake.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),16).toString());
            Turin.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),17).toString());
            TngtDarah.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),18).toString());
            Tdrain.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),19).toString());
            Tmuntah.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),20).toString());
            Tbab.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),21).toString());
            
            Tiwl.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),23).toString());
            Ttotouput.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),24).toString());
            Tbalance.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),25).toString());
            wktSimpan = tbPantau.getValueAt(tbPantau.getSelectedRow(),31).toString();
            Tbb.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),32).toString());
            Ttransfusi.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),33).toString());
            TreaksiTransfusi.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),34).toString());
            Tlain.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),35).toString());
            Tcatatan.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),36).toString());
            Tnip.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),37).toString());
            TnmPetugas.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),38).toString());
            cmbKali.setSelectedItem(tbPantau.getValueAt(tbPantau.getSelectedRow(),39).toString());
            Toksigen.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),40).toString());
            Tsistole.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),42).toString());
            Tdistole.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(),43).toString());
            Tmap.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(), 44).toString());
            TlabelTensi.setText("Tensi : " + tbPantau.getValueAt(tbPantau.getSelectedRow(), 45).toString() + " mmHg");
            TrgRawat.setText(tbPantau.getValueAt(tbPantau.getSelectedRow(), 46).toString());
            cmbPerhari.setSelectedItem(tbPantau.getValueAt(tbPantau.getSelectedRow(),47).toString());
            Tumur.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));            
            tampilParental();
            tampilTotal24Jam(tbPantau.getValueAt(tbPantau.getSelectedRow(),29).toString(), TNoRw.getText());
        }
    }
    
    public void isCek(){
       BtnSimpan.setEnabled(akses.getcppt());       
       BtnEdit.setEnabled(akses.getcppt());
       BtnHapus.setEnabled(akses.getcppt());
       MnHapusSemuaPemantauan.setEnabled(akses.getadmin());
    }
    
    private void hitungIWL() {        
        try {
            double bb, jamPengali, hasil_kali, Total_perjam, Total_akhir, angkaIWL;
            hasil_kali = 0;
            Total_perjam = 0;
            jamPengali = 0;
            Total_akhir = 0;
            angkaIWL = 0;
            if (Tbb.getText().equals("")) {
                Tbb.setText("0");
            }
            
            if (Tbb.getText().trim().contains(",") == true) {
                Tbb.setText(Tbb.getText().trim().replaceAll(",", "."));
            }

            if (cmbPerhari.getSelectedIndex() == 0) {
                angkaIWL = 0;
            } else {
                angkaIWL = Double.parseDouble(cmbPerhari.getSelectedItem().toString());
            }
            
            bb = Double.parseDouble(Tbb.getText());            
            jamPengali = Double.parseDouble(cmbKali.getSelectedItem().toString());

            hasil_kali = bb * angkaIWL;
            Total_perjam = hasil_kali / 24;
            Total_akhir = jamPengali * Total_perjam;            

            if (Valid.SetAngka7(Total_akhir).equals("NaN")) {
                Tiwl.setText("0");
            } else {
                Tiwl.setText(Valid.SetAngka7(Total_akhir));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);            
        }
    }
    
    private void hitungIntake() {
        try {
            double A, B, C, D, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            D = 0;
            if (Tmm.getText().equals("")) {
                Tmm.setText("0");
            }
            
            if (Tngt.getText().equals("")) {
                Tngt.setText("0");
            }
            
            if (TjmlParental.getText().equals("")) {
                TjmlParental.setText("0");
            }
            
            if (Ttransfusi.getText().equals("")) {
                Ttransfusi.setText("0");
            }

            A = Double.parseDouble(Tmm.getText());
            B = Double.parseDouble(Tngt.getText());
            C = Double.parseDouble(TjmlParental.getText());
            D = Double.parseDouble(Ttransfusi.getText());
            Total = A + B + C + D;

            if (Valid.SetAngka7(Total).equals("NaN")) {
                Ttotintake.setText("0");
            } else {
                Ttotintake.setText(Valid.SetAngka7(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungOutput() {
        try {
            double A, B, C, D, E, F, G, Total;
            Total = 0;
            A = 0;
            B = 0;
            C = 0;
            D = 0;
            E = 0;
            F = 0;
            G = 0;
            if (Turin.getText().equals("")) {
                Turin.setText("0");
            }
            
            if (TngtDarah.getText().equals("")) {
                TngtDarah.setText("0");
            }
            
            if (Tdrain.getText().equals("")) {
                Tdrain.setText("0");
            }
            
            if (Tmuntah.getText().equals("")) {
                Tmuntah.setText("0");
            }
            
            if (Tbab.getText().equals("")) {
                Tbab.setText("0");
            }
            
            if (Tiwl.getText().equals("")) {
                Tiwl.setText("0");
            }
            
            if (Tlain.getText().equals("")) {
                Tlain.setText("0");
            }

            A = Double.parseDouble(Turin.getText());
            B = Double.parseDouble(TngtDarah.getText());
            C = Double.parseDouble(Tdrain.getText());            
            D = Double.parseDouble(Tmuntah.getText());
            E = Double.parseDouble(Tbab.getText());
            F = Double.parseDouble(Tiwl.getText());
            G = Double.parseDouble(Tlain.getText());
            Total = A + B + C + D + E + F + G;

            if (Valid.SetAngka7(Total).equals("NaN")) {
                Ttotouput.setText("0");
            } else {
                Ttotouput.setText(Valid.SetAngka7(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void hitungBalance() {
        try {
            double A, B, Total;
            Total = 0;
            A = 0;
            B = 0;
            if (Ttotintake.getText().equals("")) {
                Ttotintake.setText("0");
            }
            
            if (Ttotouput.getText().equals("")) {
                Ttotouput.setText("0");
            }

            A = Double.parseDouble(Ttotintake.getText());
            B = Double.parseDouble(Ttotouput.getText());
            Total = A - B;

            if (Valid.SetAngka7(Total).equals("NaN")) {
                Tbalance.setText("0");
            } else {
                Tbalance.setText(Valid.SetAngka7(Total));
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    public void autoNomorPerJam() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kd_pantau,9),signed)),0) from pemantauan_harian_24jam where "
                + "tgl_pantau like '%" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 7) + "%' ", "/24Jam/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglPantau.getSelectedItem() + "").substring(0, 4), 9, TkdPantau);
    }
    
    private void jamDiurutkan() {
        urutanJam = "";
        if (cmbJam.getSelectedIndex() == 0) {
            urutanJam = "1";
        } else if (cmbJam.getSelectedIndex() == 1) {
            urutanJam = "2";
        } else if (cmbJam.getSelectedIndex() == 2) {
            urutanJam = "3";
        } else if (cmbJam.getSelectedIndex() == 3) {
            urutanJam = "4";
        } else if (cmbJam.getSelectedIndex() == 4) {
            urutanJam = "5";
        } else if (cmbJam.getSelectedIndex() == 5) {
            urutanJam = "6";
        } else if (cmbJam.getSelectedIndex() == 6) {
            urutanJam = "7";
        } else if (cmbJam.getSelectedIndex() == 7) {
            urutanJam = "8";
        } else if (cmbJam.getSelectedIndex() == 8) {
            urutanJam = "9";
        } else if (cmbJam.getSelectedIndex() == 9) {
            urutanJam = "10";
        } else if (cmbJam.getSelectedIndex() == 10) {
            urutanJam = "11";
        } else if (cmbJam.getSelectedIndex() == 11) {
            urutanJam = "12";
        } else if (cmbJam.getSelectedIndex() == 12) {
            urutanJam = "13";
        } else if (cmbJam.getSelectedIndex() == 13) {
            urutanJam = "14";
        } else if (cmbJam.getSelectedIndex() == 14) {
            urutanJam = "15";
        } else if (cmbJam.getSelectedIndex() == 15) {
            urutanJam = "16";
        } else if (cmbJam.getSelectedIndex() == 16) {
            urutanJam = "17";
        } else if (cmbJam.getSelectedIndex() == 17) {
            urutanJam = "18";
        } else if (cmbJam.getSelectedIndex() == 18) {
            urutanJam = "19";
        } else if (cmbJam.getSelectedIndex() == 19) {
            urutanJam = "20";
        } else if (cmbJam.getSelectedIndex() == 20) {
            urutanJam = "21";
        } else if (cmbJam.getSelectedIndex() == 21) {
            urutanJam = "22";
        } else if (cmbJam.getSelectedIndex() == 22) {
            urutanJam = "23";
        } else if (cmbJam.getSelectedIndex() == 23) {
            urutanJam = "24";
        } 
    }
    
    private void tampilObatTerjadwal() {
        Valid.tabelKosong(tabMode2);
        try {
            ps1 = koneksi.prepareStatement("SELECT *, date_format(tgl_pemberian,'%d-%m-%Y') tgl_beri FROM pemberian_obat where "
                    + "tgl_pemberian between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + "and no_rawat='" + TNoRw.getText() + "' and nama_obat<>'-' order by waktu_simpan");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode2.addRow(new String[]{
                        rs1.getString("tgl_beri"),
                        rs1.getString("nama_obat"),
                        rs1.getString("jenis_obat"),
                        rs1.getString("jlh_obat"),
                        rs1.getString("dosis"),
                        rs1.getString("cara_pemberian")
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
    
    private void tampilParental() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("select date_format(p.tgl_pantau,'%d-%m-%Y') tglPantau, "
                    + "p.nm_obat, p.jml_pemberian, p.kd_pantau, p.tgl_pantau from pemantauan_harian_parental p "
                    + "inner join pemantauan_harian_24jam ph on ph.kd_pantau=p.kd_pantau where "
                    + "p.tgl_pantau='" + Valid.SetTgl(tglPantau.getSelectedItem() + "") + "' and p.kd_pantau='" + TkdPantau.getText() + "'");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode1.addRow(new String[]{
                        rs2.getString("tglPantau"),
                        rs2.getString("nm_obat"),
                        rs2.getString("jml_pemberian"),
                        rs2.getString("kd_pantau"),
                        rs2.getString("tgl_pantau")
                    });
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
    }
    
    public void setData(String norw, String rgrawat) {
        ruangranap = "";
        TNoRw.setText(norw);
        TNoRm.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norw + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
        Valid.SetTgl(DTPCariB, Sequel.cariIsi("select DATE_ADD(now(),interval 30 day)"));
        TrgRawat.setText(rgrawat);
        ruangranap = rgrawat;
        Tumur.setText(Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='" + TNoRw.getText() + "'"));
        
        if (TrgRawat.getText().contains("Anak") || TrgRawat.getText().contains("VIP") || TrgRawat.getText().contains("ICU/I")) {            
            cmbPerhari.setSelectedIndex(0);
        } else {
            cmbPerhari.setSelectedIndex(2);
        }
        
        //pasien dewasa
        if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norw + "'") > 0) {
            Tbb.setText(Sequel.cariIsi("select bb_msk_rs from penilaian_awal_keperawatan_dewasa_ranap where no_rawat='" + norw + "'"));
        //pasien ruang anak
        } else if (Sequel.cariInteger("select count(-1) from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norw + "'") > 0) {
            Tbb.setText(Sequel.cariIsi("select bb_msk_rs from penilaian_awal_keperawatan_anak_ranap where no_rawat='" + norw + "'"));
        } else {
            Tbb.setText("");
        }
        
        if (Sequel.cariInteger("select count(-1) from pemantauan_harian_24jam where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCariA, Sequel.cariIsi("select tgl_pantau from pemantauan_harian_24jam where "
                    + "no_rawat='" + norw + "' order by tgl_pantau desc limit 1"));
        } else {
            DTPCariA.setDate(new Date());
        }
    }
    
    private void hitungParental() {
        totParental = 0;
        for (i = 0; i < tbParental.getRowCount(); i++) {
            totParental = totParental + Double.parseDouble(tbParental.getValueAt(i, 2).toString());
        }
        
        try {
            TjmlParental.setText(Valid.SetAngka7(totParental));
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
            TjmlParental.setText("0");
            JOptionPane.showMessageDialog(null, "Silahkan cek lagi jlh. pemberian parentalnya, kemungkinan ada kesalahan data jlh. pemberianya..!!");
        }
    }
    
    private void tampilTotal24Jam(String tgl, String norw) {
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("select date_format(tgl_pantau,'%d/%m/%Y') tglpantau, ifnull(format(sum(total_intake),1),'-') intake, "
                    + "ifnull(format(sum(iwl),1),'-') iwl, ifnull(format(sum(total_output),1),'-') output, ifnull(format(sum(balance),1),'-') balance, "
                    + "count(-1) cek from pemantauan_harian_24jam where tgl_pantau = '" + tgl + "' and no_rawat ='" + norw + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    if (rs3.getInt("cek") == 0 || TNoRw.getText().equals("")) {
                        Valid.tabelKosong(tabMode3);
                    } else {
                        tabMode3.addRow(new String[]{TNoRm.getText() + " - " + TPasien.getText(),
                            rs3.getString("tglpantau"), "Total INTAKE", rs3.getString("intake")});
                        tabMode3.addRow(new String[]{TNoRm.getText() + " - " + TPasien.getText(),
                            rs3.getString("tglpantau"), "Total IWL", rs3.getString("iwl")});
                        tabMode3.addRow(new String[]{TNoRm.getText() + " - " + TPasien.getText(),
                            rs3.getString("tglpantau"), "Total OUTPUT", rs3.getString("output")});
                        tabMode3.addRow(new String[]{TNoRm.getText() + " - " + TPasien.getText(),
                            rs3.getString("tglpantau"), "Total BALANCE", rs3.getString("balance")});
                    }
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

        Scroll3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Hasil Pemantauan Selama "
                + Sequel.cariIsi("select count(-1) from pemantauan_harian_24jam where tgl_pantau = '" + tgl + "' and no_rawat ='" + norw + "'")
                + " Jam ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));
    }
    
    private void simpan() {
        if (Tsuhu.getText().contains(",") == true) {
            Tsuhu.setText(Tsuhu.getText().replaceAll(",", "."));
        }
        
        if (Tsistole.getText().equals("")) {
            Tsistole.setText("0");
        } else {
            Tsistole.setText(Tsistole.getText());
        }
        
        if (Tdistole.getText().equals("")) {
            Tdistole.setText("0");
        } else {
            Tdistole.setText(Tdistole.getText());
        }
        
        hitungMAP();
        autoNomorPerJam();
        jamDiurutkan();
        BtnTotOutputActionPerformed(null);
        if (Sequel.menyimpantf("pemantauan_harian_24jam", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat, Tgl. Pantau & Jam", 40, new String[]{
            TNoRw.getText(), TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
            urutanJam, Tnadi.getText(), Tsuhu.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), Tkesadaran.getText(), Tsistole.getText() + "/" + Tdistole.getText(),
            Trr.getText(), Tspo.getText(), Tmm.getText(), Tngt.getText(), TjmlParental.getText(), Ttotintake.getText(), Turin.getText(), TngtDarah.getText(),
            Tdrain.getText(), Tmuntah.getText(), Tbab.getText(), Tiwl.getText(), Ttotouput.getText(), Tbalance.getText(), Sequel.cariIsi("select now()"),
            Tbb.getText(), Ttransfusi.getText(), TreaksiTransfusi.getText(), Tlain.getText(), Tcatatan.getText(), Tnip.getText(),
            cmbKali.getSelectedItem().toString(), Toksigen.getText(), Tsistole.getText(), Tdistole.getText(), Tmap.getText(), TrgRawat.getText(),
            cmbPerhari.getSelectedItem().toString()
        }) == true) {

            //simpan Parental_Line_Obat-obatan
            if (tbParental.getRowCount() != 0) {
                for (i = 0; i < tbParental.getRowCount(); i++) {
                    Sequel.menyimpan2("pemantauan_harian_parental", "?,?,?,?,?,?", 6, new String[]{
                        TNoRw.getText(), TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
                        tbParental.getValueAt(i, 1).toString(), tbParental.getValueAt(i, 2).toString()
                    });
                }
            }

            tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
            emptTeks();
            Valid.SetTgl(DTPCariA, tglBekasInput);
            tampil();
            tampilTotal24Jam(tglPANTAU, norawatPANTAU);
        }
    }

    private void hapusPemantauan() {
        if (Sequel.queryu2tf("delete from pemantauan_harian_24jam where waktu_simpan=?", 1, new String[]{
            tbPantau.getValueAt(tbPantau.getSelectedRow(), 31).toString()
        }) == true) {
            Sequel.meghapus("pemantauan_harian_parental", "kd_pantau",
                    tbPantau.getValueAt(tbPantau.getSelectedRow(), 1).toString());

            tampil();
            emptTeks();
            tampilTotal24Jam(tglPANTAU, norawatPANTAU);
        } else {
            JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
        }
    }

    private void hapusParental() {
        if (tbParental.getSelectedRowCount() == 0 || dataParental.equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu salah satu datanya pada tabel...!!!!");
            tbParental.requestFocus();
        } else {
            tglBekasInput = "";
            dataParental = "";
            pilih = 0;
            
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah obat " + tbParental.getValueAt(tbParental.getSelectedRow(), 1).toString()
                    + " jml. pemberian " + tbParental.getValueAt(tbParental.getSelectedRow(), 2).toString()
                    + " Cc. akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                tabMode1.removeRow(tbParental.getSelectedRow());
                gantiPemantauan();                
                tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
                Valid.SetTgl(DTPCariA, tglBekasInput);
                tampil();
                tampilTotal24Jam(tglPANTAU, norawatPANTAU);
            } else {
                tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
                Valid.SetTgl(DTPCariA, tglBekasInput);
                tampil();
                tampilTotal24Jam(tglPANTAU, norawatPANTAU);
            }
        }
    }

    private void gantiPemantauan() {
        if (Tsuhu.getText().contains(",") == true) {
            Tsuhu.setText(Tsuhu.getText().replaceAll(",", "."));
        }
        
        if (Tsistole.getText().equals("")) {
            Tsistole.setText("0");
        } else {
            Tsistole.setText(Tsistole.getText());
        }
        
        if (Tdistole.getText().equals("")) {
            Tdistole.setText("0");
        } else {
            Tdistole.setText(Tdistole.getText());
        }
        
        if (TrgRawat.getText().equals("")) {
            TrgRawat.setText(ruangranap);
        } else {
            TrgRawat.setText(TrgRawat.getText());
        }

        hitungMAP();
        jamDiurutkan();
        BtnTotOutputActionPerformed(null);
        if (Sequel.mengedittf("pemantauan_harian_24jam", "waktu_simpan=?", "kd_pantau=?, tgl_pantau=?, jam=?, "
                + "urutan_jam=?, nadi=?, suhu=?, gcs_e=?, gcs_m=?, gcs_v=?, kesadaran=?, td=?, nafas=?, spo2=?, makan_minum=?, "
                + "ngt=?, total_parental=?, total_intake=?, urine=?, ngt_darah=?, drain=?, muntah=?, bab=?, iwl=?, total_output=?, "
                + "balance=?, bb_msk_rs=?, transfusi=?, reaksi_transfusi=?, defekasi=?, catatan=?, nip_petugas=?, pengali=?, oksigen=?, "
                + "sistole=?, distole=?, map=?, ruang_rawat=?, pengali_iwl_bb=?", 39, new String[]{
                    TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
                    urutanJam, Tnadi.getText(), Tsuhu.getText(), Tgcse.getText(), Tgcsm.getText(), Tgcsv.getText(), Tkesadaran.getText(), Tsistole.getText() + "/" + Tdistole.getText(),
                    Trr.getText(), Tspo.getText(), Tmm.getText(), Tngt.getText(), TjmlParental.getText(), Ttotintake.getText(), Turin.getText(), TngtDarah.getText(),
                    Tdrain.getText(), Tmuntah.getText(), Tbab.getText(), Tiwl.getText(), Ttotouput.getText(), Tbalance.getText(), Tbb.getText(),
                    Ttransfusi.getText(), TreaksiTransfusi.getText(), Tlain.getText(), Tcatatan.getText(), Tnip.getText(), cmbKali.getSelectedItem().toString(), 
                    Toksigen.getText(), Tsistole.getText(), Tdistole.getText(), Tmap.getText(), TrgRawat.getText(), cmbPerhari.getSelectedItem().toString(), 
                    wktSimpan
                }) == true) {

            //simpan Parental_Line_Obat-obatan
            Sequel.meghapus("pemantauan_harian_parental", "kd_pantau",
                    tbPantau.getValueAt(tbPantau.getSelectedRow(), 1).toString());

            if (tbParental.getRowCount() != 0) {
                for (i = 0; i < tbParental.getRowCount(); i++) {
                    Sequel.menyimpan2("pemantauan_harian_parental", "?,?,?,?,?,?", 6, new String[]{
                        TNoRw.getText(), TkdPantau.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam.getSelectedItem().toString(),
                        tbParental.getValueAt(i, 1).toString(), tbParental.getValueAt(i, 2).toString()
                    });
                }
            }
        }
    }
    
    private void gantiParental() {
        x = JOptionPane.showConfirmDialog(rootPane, "Apakah obat " + tbParental.getValueAt(tbParental.getSelectedRow(), 1).toString()
                + " jml. pemberian " + tbParental.getValueAt(tbParental.getSelectedRow(), 2).toString()
                + " Cc. akan diperbaiki/diganti..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            tabMode1.removeRow(tbParental.getSelectedRow());
            dataParental = "";
            pilih = 0;
            WindowParental.setSize(820, 480);
            WindowParental.setLocationRelativeTo(internalFrame1);
            WindowParental.setAlwaysOnTop(false);
            WindowParental.setVisible(true);

            BtnBatal1ActionPerformed(null);
            DTPCari1.setDate(new Date());
            DTPCari2.setDate(new Date());
            tampilObatTerjadwal();
        } else {
            dataParental = "";
            pilih = 0;
            tampilParental();
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
            ps4 = koneksi.prepareStatement("select pg1.nama ptgs, date_format(ck.tgl_lapor,'%d-%m-%Y') tgllapor, time_format(ck.jam_lapor,'%H:%i') jamlapor, "
                    + "pg2.nama dpjp, date_format(ck.tgl_verifikasi,'%d-%m-%Y') tglverif, time_format(ck.jam_verifikasi,'%H:%i') jamverif from cppt_konfirmasi_terapi ck "
                    + "inner join pegawai pg1 on pg1.nik=ck.nip_petugas_konfir inner join pegawai pg2 on pg2.nik=ck.nip_dpjp_konfir where "
                    + "ck.no_rawat = '" + norwt + "' and ck.tgl_cppt='" + tglcppt + "' and ck.cppt_shift='" + sift + "' "
                    + "and ck.jam_cppt='" + jamcppt + "' order by ck.waktu_simpan");
            try {
                rs4 = ps4.executeQuery();
                while (rs4.next()) {
                    if (dataKonfirmasi.equals("")) {
                        dataKonfirmasi = "Tgl. Lapor : " + rs4.getString("tgllapor") + ", Jam : " + rs4.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs4.getString("tglverif") + ", Jam : " + rs4.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs4.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs4.getString("dpjp");
                    } else {
                        dataKonfirmasi = dataKonfirmasi + "\n\nTgl. Lapor : " + rs4.getString("tgllapor") + ", Jam : " + rs4.getString("jamlapor") + " WITA\n"
                                + "Tgl. Verifikasi : " + rs4.getString("tglverif") + ", Jam : " + rs4.getString("jamverif") + " WITA\n"
                                + "Nama Petugas : " + rs4.getString("ptgs") + "\n"
                                + "Dengan DPJP : " + rs4.getString("dpjp");
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs4 != null) {
                    rs4.close();
                }
                if (ps4 != null) {
                    ps4.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void simpanEWS() {
        sesuaikanData();
        if (Sequel.menyimpantf("monitoring_ews_dewasa", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "Monitoring EWS", 24, new String[]{
            TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(tglPantau.getSelectedItem() + ""), cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
            respiEWS, saturEWS, cmbSuplemen.getSelectedItem().toString(), tensiEWS, nadiEWS, cmbKesadaran.getSelectedItem().toString(), suhuEWS,
            totSkor, Tgds.getText(), TskorNyeri.getText(), Turin.getText(), Tnip.getText(), Sequel.cariIsi("select now()"), skorRespi, skorSatur,
            skorSuplemen, skorTensi, skorNadi, skorKesadaran, skorTemperatur
        }) == true) {
            JOptionPane.showMessageDialog(null, "Data pemantauan harian pasien berhasil tersimpan ke monitoring EWS...!!!!");
            tglBekasInput = Valid.SetTgl(tglPantau.getSelectedItem() + "");
            emptTeks();
            Valid.SetTgl(DTPCariA, tglBekasInput);
            tampil();
            tampilTotal24Jam(tglPANTAU, norawatPANTAU);
            WindowEWS.dispose();
        }
    }
    
    private void sesuaikanData() {
        int respi, tensi, nadi;
        double satur, suhu;
        
        respi = 0;
        satur = 0;
        tensi = 0;
        nadi = 0;
        suhu = 0;
        total = 0;        
        respirasi = 0;
        saturasi = 0;
        suplemen = 0;
        nilaitensi = 0;
        nilainadi = 0;
        kesadaran = 0;
        temperatur = 0;        
        
        skorRespi = "";
        skorSatur = "";
        skorSuplemen = "";
        skorTensi = "";
        skorNadi = "";
        skorKesadaran = "";
        skorTemperatur = "";
        totSkor = "";
        
        respiEWS = "";
        saturEWS = "";
        tensiEWS = "";
        nadiEWS = "";
                
        //respirasi
        if (!Trr.getText().equals("") || !Trr.getText().equals("-")) {
            respi = Integer.parseInt(Trr.getText());
        } else {
            respi = 0;
        }
        
        if (respi >= 0 && respi <= 5) {
            respiEWS = "<= 5";
            respirasi = 0;
            skorRespi = "C. Blue";
        } else if (respi >= 6 && respi <= 8) {
            respiEWS = "6 - 8";
            respirasi = 3;
            skorRespi = "3";
        } else if (respi >= 9 && respi <= 11) {
            respiEWS = "9 - 11";
            respirasi = 1;
            skorRespi = "1";
        } else if (respi >= 12 && respi <= 20) {
            respiEWS = "12 - 20";
            respirasi = 0;
            skorRespi = "0";
        } else if (respi >= 21 && respi <= 24) {
            respiEWS = "21 - 24";
            respirasi = 2;
            skorRespi = "2";
        } else if (respi >= 25 && respi <= 34) {
            respiEWS = "25 - 34";
            respirasi = 3;
            skorRespi = "3";
        } else if (respi >= 35) {
            respiEWS = ">= 35";
            respirasi = 0;
            skorRespi = "C. Blue";
        }
        
        //saturasi
        if (Tspo.getText().contains(",") == true) {
            Tspo.setText(Tspo.getText().replaceAll(",", "."));
        }
        
        if (!Tspo.getText().equals("") || !Tspo.getText().equals("-")) {
            satur = Double.parseDouble(Tspo.getText());            
        } else {
            satur = 0;
        }
        
        if (satur >= 0 && satur <= 91) {
            saturEWS = "<= 91";
            saturasi = 3;
            skorSatur = "3";
        } else if (satur >= 92 && satur <= 93) {
            saturEWS = "92 - 93";
            saturasi = 2;
            skorSatur = "2";
        } else if (satur >= 94 && satur <= 95) {
            saturEWS = "94 - 95";
            saturasi = 1;
            skorSatur = "1";
        } else if (satur >= 96) {
            saturEWS = ">= 96";
            saturasi = 0;
            skorSatur = "0";
        }
        
        //suplemen
        if (cmbSuplemen.getSelectedIndex() == 0) {
            suplemen = 0;
            skorSuplemen = "";
        } else if (cmbSuplemen.getSelectedIndex() == 1) {
            suplemen = 2;
            skorSuplemen = "2";
        }
        
        //tensi
        if (!Tsistole.getText().equals("") || !Tsistole.getText().equals("-")) {
            tensi = Integer.parseInt(Tsistole.getText());
        } else {
            tensi = 0;
        }
        
        if (tensi >= 0 && tensi <= 70) {
            tensiEWS = "<= 70";
            nilaitensi = 0;
            skorTensi = "C. Blue";
        } else if (tensi >= 71 && tensi <= 90) {
            tensiEWS = "71 - 90";
            nilaitensi = 3;
            skorTensi = "3";
        } else if (tensi >= 91 && tensi <= 100) {
            tensiEWS = "91 - 100";
            nilaitensi = 2;
            skorTensi = "2";
        } else if (tensi >= 101 && tensi <= 110) {
            tensiEWS = "101 - 110";
            nilaitensi = 1;
            skorTensi = "1";
        } else if (tensi >= 111 && tensi <= 180) {
            tensiEWS = "111 - 180";
            nilaitensi = 0;
            skorTensi = "0";
        } else if (tensi >= 181 && tensi <= 220) {
            tensiEWS = "181 - 220";
            nilaitensi = 1;
            skorTensi = "1";
        } else if (tensi >= 220) {
            tensiEWS = ">= 220";
            nilaitensi = 3;
            skorTensi = "3";
        }
        
        //nadi
        if (!Tnadi.getText().equals("") || !Tnadi.getText().equals("-")) {
            nadi = Integer.parseInt(Tnadi.getText());
        } else {
            nadi = 0;
        }
        
        if (nadi >= 0 && nadi <= 40) {
            nadiEWS = "<= 40";
            nilainadi = 0;
            skorNadi = "C. Blue";
        } else if (nadi >= 41 && nadi <= 50) {
            nadiEWS = "41 - 50";
            nilainadi = 1;
            skorNadi = "1";
        } else if (nadi >= 51 && nadi <= 90) {
            nadiEWS = "51 - 90";
            nilainadi = 0;
            skorNadi = "0";
        } else if (nadi >= 91 && nadi <= 110) {
            nadiEWS = "91 - 110";
            nilainadi = 1;
            skorNadi = "1";
        } else if (nadi >= 111 && nadi <= 130) {
            nadiEWS = "111 - 130";
            nilainadi = 2;
            skorNadi = "2";
        } else if (nadi >= 131 && nadi <= 140) {
            nadiEWS = "131 - 140";
            nilainadi = 3;
            skorNadi = "3";
        } else if (nadi >= 140) {
            nadiEWS = ">= 140";
            nilainadi = 0;
            skorNadi = "C. Blue";
        }
        
        //kesadaran
        if (cmbKesadaran.getSelectedIndex() == 0) {
            kesadaran = 0;
            skorKesadaran = "";
        } else if (cmbKesadaran.getSelectedIndex() == 1) {
            kesadaran = 0;
            skorKesadaran = "0";
        } else if (cmbKesadaran.getSelectedIndex() == 2) {
            kesadaran = 3;
            skorKesadaran = "3";
        } else if (cmbKesadaran.getSelectedIndex() == 3) {
            kesadaran = 0;
            skorKesadaran = "C. Blue";
        }
        
        //suhu
        if (!Tsuhu.getText().equals("") || !Tsuhu.getText().equals("-")) {
            suhu = Double.parseDouble(Tsuhu.getText());
        } else {
            suhu = 0;
        }
        
        if (suhu >= 0 && suhu <= 35) {
            suhuEWS = "<= 35";
            temperatur = 3;
            skorTemperatur = "3";
        } else if (suhu >= 35.1 && suhu <= 36) {
            suhuEWS = "35,1 - 36";
            temperatur = 1;
            skorTemperatur = "1";
        } else if (suhu >= 36.1 && suhu <= 38) {
            suhuEWS = "36,1 - 38";
            temperatur = 0;
            skorTemperatur = "0";
        } else if (suhu >= 38.1 && suhu <= 39) {
            suhuEWS = "38,1 - 39";
            temperatur = 1;
            skorTemperatur = "1";
        } else if (suhu >= 39) {
            suhuEWS = ">= 39";
            temperatur = 2;
            skorTemperatur = "2";
        }
        
        //hitung skor
        total = respirasi + saturasi + suplemen + nilaitensi + nilainadi + kesadaran + temperatur;        
        totSkor = Valid.SetAngka2(total);
    }
    
    private void hitungMAP() {
        if (Tsistole.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka sistole belum terisi...!!!");
            Tsistole.requestFocus();
        } else if (Tdistole.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Angka distole belum terisi...!!!");
            Tdistole.requestFocus();
        } else {
            double sistol, distol, nilai2D, nilaiS, nilaiMAP;
            sistol = Double.parseDouble(Tsistole.getText());
            distol = Double.parseDouble(Tdistole.getText());
            
            // rumus MAP = (S + 2D)/3
            nilai2D = 2 * distol;
            nilaiS = sistol + nilai2D;
            nilaiMAP = nilaiS / 3;
            
            if (Valid.SetAngka4(nilaiMAP).equals("NaN")) {
                Tmap.setText("0");
            } else {
                Tmap.setText(Valid.SetAngka4(nilaiMAP));
            }
            
            if (!Tsistole.getText().equals("") && !Tdistole.getText().equals("")) {
                TlabelTensi.setText("Tensi : " + Tsistole.getText() + "/" + Tdistole.getText() + " mmHg");
            } else {
                TlabelTensi.setText("Tensi : 0 mmHg");
            }
        }
    }
}
