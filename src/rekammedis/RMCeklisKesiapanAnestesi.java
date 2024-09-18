package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import inventory.DlgCatatanResep;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;
import laporan.DlgHasilPenunjangMedis;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMCeklisKesiapanAnestesi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;    
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String mesin = "", layar = "", syrine = "",
            selang = "", flowMeterO2 = "", n2o = "", flowMeterN2o = "",
            power = "", tidak = "", zat = "", absorber = "",
            sungkup = "", oropa = "", batang = "", bilah = "", gagang = "", ett = "", stilet = "", spuit = "", forcep = "",
            kabel = "", elek = "", nibp = "", spo2 = "", kapno = "", pemantau = "",
            stetos = "", suction = "", selangSuction = "", plester = "", lida = "",
            epin = "", atro = "", sedatif = "", opi = "", pelumpuh = "", anal = "", anti = "", lain = "",
            kodekamar = "", status = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMCeklisKesiapanAnestesi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Checklist",
            "Jam", "Diagnosis", "Teknik Anestesi", "Jenis Operasi", "Tgl. Tindakan", "Perawat Anestesi", 
            "tgl_ceklis", "jam_ceklis", "diagnosis", "teknik_anastesia", "jenis_operasi", "tgl_tindakan", "mesin", "layar", "syrine_pump", "selang",
            "flow_meter_o2", "n2o", "flow_meter_n20", "power_on", "tidak_ada_kebocoran", "zat_volatil", "absorber", "sungkup", "oroparingeal",
            "batang", "bilah", "gagang", "ett", "stilet", "spuit", "forceps", "kabel_ekg", "elektroda_ekg", "nibp", "spo2", "kapnografi", "pemantau_suhu",
            "stetoskop", "suction", "selang_suction", "plester", "lidacaine", "epinefrin", "atropin", "sedatif", "opiat", "pelumpuh_otot", "analgetik",
            "antibiotik", "lain", "ket_lain", "nip_perawat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbCeklis.setModel(tabMode);
        tbCeklis.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbCeklis.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 60; i++) {
            TableColumn column = tbCeklis.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(65);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(200);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(50);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(220);
            } else if (i == 10) {
                column.setPreferredWidth(220);
            } else if (i == 11) {
                column.setPreferredWidth(80);
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
            }
        }
        tbCeklis.setDefaultRenderer(Object.class, new WarnaTable());

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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (akses.getform().equals("RMCeklisKesiapanAnestesi")) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        TnipPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString());
                        TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPerawat.requestFocus();
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnResepObat = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel64 = new widget.Label();
        Tdiagnosis = new widget.TextBox();
        jLabel65 = new widget.Label();
        Tteknik = new widget.TextBox();
        jLabel79 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        scrollPane13 = new widget.ScrollPane();
        Tlain = new widget.TextArea();
        jLabel85 = new widget.Label();
        Ttglceklis = new widget.Tanggal();
        label20 = new widget.Label();
        TnipPerawat = new widget.TextBox();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        chkSaya = new widget.CekBox();
        jLabel66 = new widget.Label();
        Tjenis = new widget.TextBox();
        jLabel86 = new widget.Label();
        TtglTindakan = new widget.Tanggal();
        jLabel67 = new widget.Label();
        chkMesin = new widget.CekBox();
        chkLayar = new widget.CekBox();
        chkSyrine = new widget.CekBox();
        jLabel68 = new widget.Label();
        chkSelang = new widget.CekBox();
        chkFlowMeterO2 = new widget.CekBox();
        chkN2o = new widget.CekBox();
        chkFlowMeterN2o = new widget.CekBox();
        jLabel69 = new widget.Label();
        chkPower = new widget.CekBox();
        chkTidakAda = new widget.CekBox();
        chkZat = new widget.CekBox();
        chkAbsorber = new widget.CekBox();
        jLabel70 = new widget.Label();
        chkSungkup = new widget.CekBox();
        chkOroparingeal = new widget.CekBox();
        chkBatang = new widget.CekBox();
        chkBilah = new widget.CekBox();
        chkGagang = new widget.CekBox();
        chkEtt = new widget.CekBox();
        chkStilet = new widget.CekBox();
        chkSpuit = new widget.CekBox();
        chkForceps = new widget.CekBox();
        jLabel71 = new widget.Label();
        chkKabel = new widget.CekBox();
        chkElektroda = new widget.CekBox();
        chkNibp = new widget.CekBox();
        chkSpo2 = new widget.CekBox();
        chkKapnografi = new widget.CekBox();
        chkPemantau = new widget.CekBox();
        jLabel72 = new widget.Label();
        chkStetoskop = new widget.CekBox();
        chkSuction = new widget.CekBox();
        chkSelangSuction = new widget.CekBox();
        chkPlester = new widget.CekBox();
        chkLidacaine = new widget.CekBox();
        jLabel73 = new widget.Label();
        chkEpinefrin = new widget.CekBox();
        chkAtropin = new widget.CekBox();
        chkSedatif = new widget.CekBox();
        chkOpiat = new widget.CekBox();
        chkPelumpuh = new widget.CekBox();
        chkAnalgetik = new widget.CekBox();
        chkAntibiotik = new widget.CekBox();
        chkLain = new widget.CekBox();
        BtnPaste = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbCeklis = new widget.Table();
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

        MnResepObat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnResepObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnResepObat.setText("Resep Obat");
        MnResepObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnResepObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnResepObat.setIconTextGap(5);
        MnResepObat.setName("MnResepObat"); // NOI18N
        MnResepObat.setPreferredSize(new java.awt.Dimension(110, 26));
        MnResepObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnResepObatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnResepObat);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Checklist Kesiapan Anestesi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setToolTipText("Klik Kanan Pada Area Ini Untuk Melihat Resep Dokter");
        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(760, 885));
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
        TrgRawat.setBounds(145, 38, 290, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Diagnosis :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 66, 140, 23);

        Tdiagnosis.setForeground(new java.awt.Color(0, 0, 0));
        Tdiagnosis.setName("Tdiagnosis"); // NOI18N
        Tdiagnosis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TdiagnosisKeyPressed(evt);
            }
        });
        FormInput.add(Tdiagnosis);
        Tdiagnosis.setBounds(145, 66, 615, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Teknik Anesthesia :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 94, 140, 23);

        Tteknik.setForeground(new java.awt.Color(0, 0, 0));
        Tteknik.setName("Tteknik"); // NOI18N
        Tteknik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TteknikKeyPressed(evt);
            }
        });
        FormInput.add(Tteknik);
        Tteknik.setBounds(145, 94, 615, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Waktu :");
        jLabel79.setToolTipText("");
        jLabel79.setName("jLabel79"); // NOI18N
        FormInput.add(jLabel79);
        jLabel79.setBounds(563, 38, 50, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(619, 38, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(669, 38, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(720, 38, 45, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tlain.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tlain.setColumns(20);
        Tlain.setRows(5);
        Tlain.setName("Tlain"); // NOI18N
        Tlain.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tlain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tlain);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(215, 766, 550, 80);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Tgl. :");
        jLabel85.setName("jLabel85"); // NOI18N
        FormInput.add(jLabel85);
        jLabel85.setBounds(435, 38, 35, 23);

        Ttglceklis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-09-2024" }));
        Ttglceklis.setDisplayFormat("dd-MM-yyyy");
        Ttglceklis.setName("Ttglceklis"); // NOI18N
        Ttglceklis.setOpaque(false);
        Ttglceklis.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttglceklis);
        Ttglceklis.setBounds(475, 38, 88, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Perawat Anestesi :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label20);
        label20.setBounds(0, 852, 140, 23);

        TnipPerawat.setEditable(false);
        TnipPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnipPerawat.setName("TnipPerawat"); // NOI18N
        TnipPerawat.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipPerawat);
        TnipPerawat.setBounds(145, 852, 150, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(298, 852, 360, 23);

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
        BtnPerawat.setBounds(660, 852, 28, 23);

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
        FormInput.add(chkSaya);
        chkSaya.setBounds(695, 852, 90, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Jenis Operasi :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 122, 140, 23);

        Tjenis.setForeground(new java.awt.Color(0, 0, 0));
        Tjenis.setName("Tjenis"); // NOI18N
        Tjenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjenisKeyPressed(evt);
            }
        });
        FormInput.add(Tjenis);
        Tjenis.setBounds(145, 122, 430, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Tgl. Tindakan :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(575, 122, 90, 23);

        TtglTindakan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-09-2024" }));
        TtglTindakan.setDisplayFormat("dd-MM-yyyy");
        TtglTindakan.setName("TtglTindakan"); // NOI18N
        TtglTindakan.setOpaque(false);
        TtglTindakan.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTindakan);
        TtglTindakan.setBounds(669, 122, 90, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Listrik :");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 150, 140, 23);

        chkMesin.setBackground(new java.awt.Color(242, 242, 242));
        chkMesin.setForeground(new java.awt.Color(0, 0, 0));
        chkMesin.setText("Mesin Anesteshia terhubung dengan sumber listrik, indikator (+) menyala");
        chkMesin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkMesin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkMesin.setName("chkMesin"); // NOI18N
        chkMesin.setOpaque(false);
        chkMesin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkMesin);
        chkMesin.setBounds(145, 150, 380, 23);

        chkLayar.setBackground(new java.awt.Color(242, 242, 242));
        chkLayar.setForeground(new java.awt.Color(0, 0, 0));
        chkLayar.setText("Layar pemantauan terhubung dengan sumber listrik, indikator (+)");
        chkLayar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLayar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLayar.setName("chkLayar"); // NOI18N
        chkLayar.setOpaque(false);
        chkLayar.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLayar);
        chkLayar.setBounds(145, 178, 380, 23);

        chkSyrine.setBackground(new java.awt.Color(242, 242, 242));
        chkSyrine.setForeground(new java.awt.Color(0, 0, 0));
        chkSyrine.setText("Syrine Pump terhubung dengan sumber listrik, indikator (+)");
        chkSyrine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSyrine.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSyrine.setName("chkSyrine"); // NOI18N
        chkSyrine.setOpaque(false);
        chkSyrine.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSyrine);
        chkSyrine.setBounds(145, 206, 380, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Gas Medis :");
        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 234, 140, 23);

        chkSelang.setBackground(new java.awt.Color(242, 242, 242));
        chkSelang.setForeground(new java.awt.Color(0, 0, 0));
        chkSelang.setText("Selang oksigen terhubung dengan sumber gas mesin anestesia");
        chkSelang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSelang.setName("chkSelang"); // NOI18N
        chkSelang.setOpaque(false);
        chkSelang.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSelang);
        chkSelang.setBounds(145, 234, 460, 23);

        chkFlowMeterO2.setBackground(new java.awt.Color(242, 242, 242));
        chkFlowMeterO2.setForeground(new java.awt.Color(0, 0, 0));
        chkFlowMeterO2.setText("Flow meter O2 di mesin anestesia berfungsi, aliran gas keluar dari mesin dapat dirasakan");
        chkFlowMeterO2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFlowMeterO2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFlowMeterO2.setName("chkFlowMeterO2"); // NOI18N
        chkFlowMeterO2.setOpaque(false);
        chkFlowMeterO2.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkFlowMeterO2);
        chkFlowMeterO2.setBounds(145, 262, 460, 23);

        chkN2o.setBackground(new java.awt.Color(242, 242, 242));
        chkN2o.setForeground(new java.awt.Color(0, 0, 0));
        chkN2o.setText("N2O terhubung antara sumber gas dengan mesin anestesia");
        chkN2o.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkN2o.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkN2o.setName("chkN2o"); // NOI18N
        chkN2o.setOpaque(false);
        chkN2o.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkN2o);
        chkN2o.setBounds(145, 290, 460, 23);

        chkFlowMeterN2o.setBackground(new java.awt.Color(242, 242, 242));
        chkFlowMeterN2o.setForeground(new java.awt.Color(0, 0, 0));
        chkFlowMeterN2o.setText("Flow meter N2O dimesin anestesia berfungsi, aliran gas mesin dapat dirasakan");
        chkFlowMeterN2o.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFlowMeterN2o.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFlowMeterN2o.setName("chkFlowMeterN2o"); // NOI18N
        chkFlowMeterN2o.setOpaque(false);
        chkFlowMeterN2o.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkFlowMeterN2o);
        chkFlowMeterN2o.setBounds(145, 318, 460, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Mesin Anestesia :");
        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 346, 140, 23);

        chkPower.setBackground(new java.awt.Color(242, 242, 242));
        chkPower.setForeground(new java.awt.Color(0, 0, 0));
        chkPower.setText("Power On");
        chkPower.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPower.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPower.setName("chkPower"); // NOI18N
        chkPower.setOpaque(false);
        chkPower.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPower);
        chkPower.setBounds(145, 346, 190, 23);

        chkTidakAda.setBackground(new java.awt.Color(242, 242, 242));
        chkTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakAda.setText("Tidak ada kebocoran sirkuit nafas");
        chkTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakAda.setName("chkTidakAda"); // NOI18N
        chkTidakAda.setOpaque(false);
        chkTidakAda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkTidakAda);
        chkTidakAda.setBounds(145, 374, 190, 23);

        chkZat.setBackground(new java.awt.Color(242, 242, 242));
        chkZat.setForeground(new java.awt.Color(0, 0, 0));
        chkZat.setText("Zat Volatil terisi");
        chkZat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkZat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkZat.setName("chkZat"); // NOI18N
        chkZat.setOpaque(false);
        chkZat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkZat);
        chkZat.setBounds(350, 346, 190, 23);

        chkAbsorber.setBackground(new java.awt.Color(242, 242, 242));
        chkAbsorber.setForeground(new java.awt.Color(0, 0, 0));
        chkAbsorber.setText("Absorber CO2 dalam kondisi baik");
        chkAbsorber.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAbsorber.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAbsorber.setName("chkAbsorber"); // NOI18N
        chkAbsorber.setOpaque(false);
        chkAbsorber.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAbsorber);
        chkAbsorber.setBounds(350, 374, 190, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Manajemen Jalan Nafas :");
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 402, 160, 23);

        chkSungkup.setBackground(new java.awt.Color(242, 242, 242));
        chkSungkup.setForeground(new java.awt.Color(0, 0, 0));
        chkSungkup.setText("Sungkup muka dalam ukuran yang benar");
        chkSungkup.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSungkup.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSungkup.setName("chkSungkup"); // NOI18N
        chkSungkup.setOpaque(false);
        chkSungkup.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSungkup);
        chkSungkup.setBounds(165, 402, 310, 23);

        chkOroparingeal.setBackground(new java.awt.Color(242, 242, 242));
        chkOroparingeal.setForeground(new java.awt.Color(0, 0, 0));
        chkOroparingeal.setText("Oropharyngeal air way (Guedel) dalam ukuran yang benar");
        chkOroparingeal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOroparingeal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOroparingeal.setName("chkOroparingeal"); // NOI18N
        chkOroparingeal.setOpaque(false);
        chkOroparingeal.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkOroparingeal);
        chkOroparingeal.setBounds(165, 430, 310, 23);

        chkBatang.setBackground(new java.awt.Color(242, 242, 242));
        chkBatang.setForeground(new java.awt.Color(0, 0, 0));
        chkBatang.setText("Batang laringoskop berisi baterai");
        chkBatang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBatang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBatang.setName("chkBatang"); // NOI18N
        chkBatang.setOpaque(false);
        chkBatang.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBatang);
        chkBatang.setBounds(165, 458, 310, 23);

        chkBilah.setBackground(new java.awt.Color(242, 242, 242));
        chkBilah.setForeground(new java.awt.Color(0, 0, 0));
        chkBilah.setText("Bilah laringoskop dalam ukuran yang benar");
        chkBilah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBilah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBilah.setName("chkBilah"); // NOI18N
        chkBilah.setOpaque(false);
        chkBilah.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkBilah);
        chkBilah.setBounds(165, 486, 310, 23);

        chkGagang.setBackground(new java.awt.Color(242, 242, 242));
        chkGagang.setForeground(new java.awt.Color(0, 0, 0));
        chkGagang.setText("Gagang dan bilah laringoskop berfungsi baik, tidak bocor");
        chkGagang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGagang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGagang.setName("chkGagang"); // NOI18N
        chkGagang.setOpaque(false);
        chkGagang.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkGagang);
        chkGagang.setBounds(165, 514, 310, 23);

        chkEtt.setBackground(new java.awt.Color(242, 242, 242));
        chkEtt.setForeground(new java.awt.Color(0, 0, 0));
        chkEtt.setText("ETT atau LMA dalam ukuran yang benar, tidak bocor");
        chkEtt.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEtt.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEtt.setName("chkEtt"); // NOI18N
        chkEtt.setOpaque(false);
        chkEtt.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEtt);
        chkEtt.setBounds(490, 402, 280, 23);

        chkStilet.setBackground(new java.awt.Color(242, 242, 242));
        chkStilet.setForeground(new java.awt.Color(0, 0, 0));
        chkStilet.setText("Stilet (Introduser)");
        chkStilet.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStilet.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStilet.setName("chkStilet"); // NOI18N
        chkStilet.setOpaque(false);
        chkStilet.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkStilet);
        chkStilet.setBounds(490, 430, 190, 23);

        chkSpuit.setBackground(new java.awt.Color(242, 242, 242));
        chkSpuit.setForeground(new java.awt.Color(0, 0, 0));
        chkSpuit.setText("Spuit untuk mengembangkan cuff");
        chkSpuit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpuit.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpuit.setName("chkSpuit"); // NOI18N
        chkSpuit.setOpaque(false);
        chkSpuit.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSpuit);
        chkSpuit.setBounds(490, 458, 190, 23);

        chkForceps.setBackground(new java.awt.Color(242, 242, 242));
        chkForceps.setForeground(new java.awt.Color(0, 0, 0));
        chkForceps.setText("Forceps Magill");
        chkForceps.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkForceps.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkForceps.setName("chkForceps"); // NOI18N
        chkForceps.setOpaque(false);
        chkForceps.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkForceps);
        chkForceps.setBounds(490, 486, 190, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Pemantauan :");
        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 542, 140, 23);

        chkKabel.setBackground(new java.awt.Color(242, 242, 242));
        chkKabel.setForeground(new java.awt.Color(0, 0, 0));
        chkKabel.setText("Kabel EKG terhubung dengan layar pemantau");
        chkKabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKabel.setName("chkKabel"); // NOI18N
        chkKabel.setOpaque(false);
        chkKabel.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKabel);
        chkKabel.setBounds(145, 542, 320, 23);

        chkElektroda.setBackground(new java.awt.Color(242, 242, 242));
        chkElektroda.setForeground(new java.awt.Color(0, 0, 0));
        chkElektroda.setText("Elektroda EKG dalam jumlah dan ukuran yang sesuai");
        chkElektroda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkElektroda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkElektroda.setName("chkElektroda"); // NOI18N
        chkElektroda.setOpaque(false);
        chkElektroda.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkElektroda);
        chkElektroda.setBounds(145, 570, 320, 23);

        chkNibp.setBackground(new java.awt.Color(242, 242, 242));
        chkNibp.setForeground(new java.awt.Color(0, 0, 0));
        chkNibp.setText("NIBP terhubung dengan layar pantau, ukuran manset sesuai");
        chkNibp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkNibp.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkNibp.setName("chkNibp"); // NOI18N
        chkNibp.setOpaque(false);
        chkNibp.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkNibp);
        chkNibp.setBounds(145, 598, 320, 23);

        chkSpo2.setBackground(new java.awt.Color(242, 242, 242));
        chkSpo2.setForeground(new java.awt.Color(0, 0, 0));
        chkSpo2.setText("SpO2 terhubung dengan layar pantau, berfungsi baik");
        chkSpo2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSpo2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSpo2.setName("chkSpo2"); // NOI18N
        chkSpo2.setOpaque(false);
        chkSpo2.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSpo2);
        chkSpo2.setBounds(475, 542, 310, 23);

        chkKapnografi.setBackground(new java.awt.Color(242, 242, 242));
        chkKapnografi.setForeground(new java.awt.Color(0, 0, 0));
        chkKapnografi.setText("Kapnografi terhubung dengan layar pantau, berfungsi baik");
        chkKapnografi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkKapnografi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkKapnografi.setName("chkKapnografi"); // NOI18N
        chkKapnografi.setOpaque(false);
        chkKapnografi.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkKapnografi);
        chkKapnografi.setBounds(475, 570, 310, 23);

        chkPemantau.setBackground(new java.awt.Color(242, 242, 242));
        chkPemantau.setForeground(new java.awt.Color(0, 0, 0));
        chkPemantau.setText("Pemantau suhu terhubung dengan layar pantau");
        chkPemantau.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPemantau.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPemantau.setName("chkPemantau"); // NOI18N
        chkPemantau.setOpaque(false);
        chkPemantau.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPemantau);
        chkPemantau.setBounds(475, 598, 310, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("Lain-lain :");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 626, 140, 23);

        chkStetoskop.setBackground(new java.awt.Color(242, 242, 242));
        chkStetoskop.setForeground(new java.awt.Color(0, 0, 0));
        chkStetoskop.setText("Stetoskop tersedia");
        chkStetoskop.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkStetoskop.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkStetoskop.setName("chkStetoskop"); // NOI18N
        chkStetoskop.setOpaque(false);
        chkStetoskop.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkStetoskop);
        chkStetoskop.setBounds(145, 626, 140, 23);

        chkSuction.setBackground(new java.awt.Color(242, 242, 242));
        chkSuction.setForeground(new java.awt.Color(0, 0, 0));
        chkSuction.setText("Suction berfungsi baik");
        chkSuction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSuction.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSuction.setName("chkSuction"); // NOI18N
        chkSuction.setOpaque(false);
        chkSuction.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSuction);
        chkSuction.setBounds(145, 654, 140, 23);

        chkSelangSuction.setBackground(new java.awt.Color(242, 242, 242));
        chkSelangSuction.setForeground(new java.awt.Color(0, 0, 0));
        chkSelangSuction.setText("Selang suction terhubung, kateter suction dalam ukuran yang benar");
        chkSelangSuction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSelangSuction.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSelangSuction.setName("chkSelangSuction"); // NOI18N
        chkSelangSuction.setOpaque(false);
        chkSelangSuction.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSelangSuction);
        chkSelangSuction.setBounds(145, 682, 360, 23);

        chkPlester.setBackground(new java.awt.Color(242, 242, 242));
        chkPlester.setForeground(new java.awt.Color(0, 0, 0));
        chkPlester.setText("Plester untuk fiksasi");
        chkPlester.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPlester.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPlester.setName("chkPlester"); // NOI18N
        chkPlester.setOpaque(false);
        chkPlester.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPlester);
        chkPlester.setBounds(300, 626, 140, 23);

        chkLidacaine.setBackground(new java.awt.Color(242, 242, 242));
        chkLidacaine.setForeground(new java.awt.Color(0, 0, 0));
        chkLidacaine.setText("Lidacaine spray/jelly");
        chkLidacaine.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLidacaine.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLidacaine.setName("chkLidacaine"); // NOI18N
        chkLidacaine.setOpaque(false);
        chkLidacaine.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkLidacaine);
        chkLidacaine.setBounds(300, 654, 140, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Obat-obat :");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel73.setName("jLabel73"); // NOI18N
        FormInput.add(jLabel73);
        jLabel73.setBounds(0, 710, 140, 23);

        chkEpinefrin.setBackground(new java.awt.Color(242, 242, 242));
        chkEpinefrin.setForeground(new java.awt.Color(0, 0, 0));
        chkEpinefrin.setText("Efinefrin");
        chkEpinefrin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkEpinefrin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkEpinefrin.setName("chkEpinefrin"); // NOI18N
        chkEpinefrin.setOpaque(false);
        chkEpinefrin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkEpinefrin);
        chkEpinefrin.setBounds(145, 710, 70, 23);

        chkAtropin.setBackground(new java.awt.Color(242, 242, 242));
        chkAtropin.setForeground(new java.awt.Color(0, 0, 0));
        chkAtropin.setText("Atropoin");
        chkAtropin.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAtropin.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAtropin.setName("chkAtropin"); // NOI18N
        chkAtropin.setOpaque(false);
        chkAtropin.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAtropin);
        chkAtropin.setBounds(145, 738, 70, 23);

        chkSedatif.setBackground(new java.awt.Color(242, 242, 242));
        chkSedatif.setForeground(new java.awt.Color(0, 0, 0));
        chkSedatif.setText("Sedatif (Midazolam / Propofol / Etomidat / Ketamin / Tiopental)");
        chkSedatif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSedatif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSedatif.setName("chkSedatif"); // NOI18N
        chkSedatif.setOpaque(false);
        chkSedatif.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkSedatif);
        chkSedatif.setBounds(430, 710, 330, 23);

        chkOpiat.setBackground(new java.awt.Color(242, 242, 242));
        chkOpiat.setForeground(new java.awt.Color(0, 0, 0));
        chkOpiat.setText("Opiat / Opioid");
        chkOpiat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkOpiat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkOpiat.setName("chkOpiat"); // NOI18N
        chkOpiat.setOpaque(false);
        chkOpiat.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkOpiat);
        chkOpiat.setBounds(230, 710, 100, 23);

        chkPelumpuh.setBackground(new java.awt.Color(242, 242, 242));
        chkPelumpuh.setForeground(new java.awt.Color(0, 0, 0));
        chkPelumpuh.setText("Pelumpuh Otot");
        chkPelumpuh.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPelumpuh.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPelumpuh.setName("chkPelumpuh"); // NOI18N
        chkPelumpuh.setOpaque(false);
        chkPelumpuh.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkPelumpuh);
        chkPelumpuh.setBounds(230, 738, 100, 23);

        chkAnalgetik.setBackground(new java.awt.Color(242, 242, 242));
        chkAnalgetik.setForeground(new java.awt.Color(0, 0, 0));
        chkAnalgetik.setText("Analgetik");
        chkAnalgetik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAnalgetik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAnalgetik.setName("chkAnalgetik"); // NOI18N
        chkAnalgetik.setOpaque(false);
        chkAnalgetik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAnalgetik);
        chkAnalgetik.setBounds(340, 710, 80, 23);

        chkAntibiotik.setBackground(new java.awt.Color(242, 242, 242));
        chkAntibiotik.setForeground(new java.awt.Color(0, 0, 0));
        chkAntibiotik.setText("Antibiotik");
        chkAntibiotik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAntibiotik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAntibiotik.setName("chkAntibiotik"); // NOI18N
        chkAntibiotik.setOpaque(false);
        chkAntibiotik.setPreferredSize(new java.awt.Dimension(220, 23));
        FormInput.add(chkAntibiotik);
        chkAntibiotik.setBounds(340, 738, 80, 23);

        chkLain.setBackground(new java.awt.Color(242, 242, 242));
        chkLain.setForeground(new java.awt.Color(0, 0, 0));
        chkLain.setText("Lain-lain :");
        chkLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLain.setName("chkLain"); // NOI18N
        chkLain.setOpaque(false);
        chkLain.setPreferredSize(new java.awt.Dimension(220, 23));
        chkLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainActionPerformed(evt);
            }
        });
        FormInput.add(chkLain);
        chkLain.setBounds(145, 766, 70, 23);

        BtnPaste.setForeground(new java.awt.Color(0, 0, 0));
        BtnPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/paste.png"))); // NOI18N
        BtnPaste.setMnemonic('L');
        BtnPaste.setText("Paste");
        BtnPaste.setToolTipText("Alt+L");
        BtnPaste.setName("BtnPaste"); // NOI18N
        BtnPaste.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPasteActionPerformed(evt);
            }
        });
        FormInput.add(BtnPaste);
        BtnPaste.setBounds(116, 796, 90, 23);

        Scroll1.setViewportView(FormInput);

        internalFrame1.add(Scroll1, java.awt.BorderLayout.CENTER);

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

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Checklist Kesiapan Anestesi ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbCeklis.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbCeklis.setName("tbCeklis"); // NOI18N
        tbCeklis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbCeklisMouseClicked(evt);
            }
        });
        tbCeklis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbCeklisKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbCeklis);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 86));
        panelGlass11.setLayout(new java.awt.BorderLayout());

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass12.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 6));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Checklist :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass12.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-09-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-09-2024" }));
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

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            cekData();
            if (Sequel.menyimpantf("ceklis_kesiapan_anestesi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No. Rawat", 50, new String[]{
                TNoRw.getText(), TrgRawat.getText(), Valid.SetTgl(Ttglceklis.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                Tdiagnosis.getText(), Tteknik.getText(), Tjenis.getText(), Valid.SetTgl(TtglTindakan.getSelectedItem() + ""), mesin, layar, syrine, selang, flowMeterO2, n2o, flowMeterN2o,
                power, tidak, zat, absorber, sungkup, oropa, batang, bilah, gagang, ett, stilet, spuit, forcep, kabel, elek, nibp, spo2, kapno, pemantau, stetos, suction, selangSuction,
                plester, lida, epin, atro, sedatif, opi, pelumpuh, anal, anti, lain, Tlain.getText(), TnipPerawat.getText(), Sequel.cariIsi("select now()")
            }) == true) {
                TCari.setText(TNoRw.getText());
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
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
            if (tbCeklis.getSelectedRow() > -1) {
                cekData();
                if (Sequel.mengedittf("ceklis_kesiapan_anestesi", "waktu_simpan=?", "tgl_ceklis=?, jam_ceklis=?, diagnosis=?, teknik_anastesia=?, "
                        + "jenis_operasi=?, tgl_tindakan=?, mesin=?, layar=?, syrine_pump=?, selang=?, flow_meter_o2=?, n2o=?, flow_meter_n20=?, power_on=?, "
                        + "tidak_ada_kebocoran=?, zat_volatil=?, absorber=?, sungkup=?, oroparingeal=?, batang=?, bilah=?, gagang=?, ett=?, stilet=?, spuit=?, "
                        + "forceps=?, kabel_ekg=?, elektroda_ekg=?, nibp=?, spo2=?, kapnografi=?, pemantau_suhu=?, stetoskop=?, suction=?, selang_suction=?, "
                        + "plester=?, lidacaine=?, epinefrin=?, atropin=?, sedatif=?, opiat=?, pelumpuh_otot=?, analgetik=?, antibiotik=?, lain=?, ket_lain=?, "
                        + "nip_perawat=?", 48, new String[]{
                            Valid.SetTgl(Ttglceklis.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                            Tdiagnosis.getText(), Tteknik.getText(), Tjenis.getText(), Valid.SetTgl(TtglTindakan.getSelectedItem() + ""), mesin, layar, syrine, selang,
                            flowMeterO2, n2o, flowMeterN2o, power, tidak, zat, absorber, sungkup, oropa, batang, bilah, gagang, ett, stilet, spuit, forcep, kabel,
                            elek, nibp, spo2, kapno, pemantau, stetos, suction, selangSuction, plester, lida, epin, atro, sedatif, opi, pelumpuh, anal, anti, lain,
                            Tlain.getText(), TnipPerawat.getText(),
                            tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
                        }) == true) {

                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbCeklis.requestFocus();
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void tbCeklisMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbCeklisMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbCeklisMouseClicked

    private void tbCeklisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbCeklisKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbCeklisKeyPressed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void TlainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnPerawat.requestFocus();
        }
    }//GEN-LAST:event_TlainKeyPressed

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMCeklisKesiapanAnestesi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void TdiagnosisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TdiagnosisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tteknik.requestFocus();
        }
    }//GEN-LAST:event_TdiagnosisKeyPressed

    private void TteknikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TteknikKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tjenis.requestFocus();
        }
    }//GEN-LAST:event_TteknikKeyPressed

    private void chkSayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSayaActionPerformed
        if (chkSaya.isSelected() == true) {
            if (akses.getadmin() == true) {
                TnipPerawat.setText("-");
                TnmPerawat.setText("-");
            } else {
                TnipPerawat.setText(akses.getkode());
                TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawat.getText() + "'"));
            }
        } else {
            TnipPerawat.setText("-");
            TnmPerawat.setText("-");
        }
    }//GEN-LAST:event_chkSayaActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbCeklis.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from ceklis_kesiapan_anestesi where waktu_simpan=?", 1, new String[]{
                    tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 0).toString()
                }) == true) {
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
            tbCeklis.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbCeklis.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("ruangan", TrgRawat.getText());
            param.put("tglCeklis", Valid.SetTglINDONESIA(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 13).toString()));
            param.put("jamCeklis", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita");
            param.put("diagnosis", Tdiagnosis.getText());
            param.put("teknik", Tteknik.getText());
            param.put("jenis", Tjenis.getText());
            param.put("tglTindakan", Valid.SetTglINDONESIA(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 18).toString()));
            param.put("perawat", "(" + TnmPerawat.getText() + ")");

            //listrik
            if (chkMesin.isSelected() == true) {
                param.put("mesin", "V");
            } else {
                param.put("mesin", "");
            }
            
            if (chkLayar.isSelected() == true) {
                param.put("layar", "V");
            } else {
                param.put("layar", "");
            }
            
            if (chkSyrine.isSelected() == true) {
                param.put("syrine", "V");
            } else {
                param.put("syrine", "");
            }
            
            //gas medis
            if (chkSelang.isSelected() == true) {
                param.put("selang", "V");
            } else {
                param.put("selang", "");
            }
            
            if (chkFlowMeterO2.isSelected() == true) {
                param.put("flowO2", "V");
            } else {
                param.put("flowO2", "");
            }
            
            if (chkN2o.isSelected() == true) {
                param.put("n2o", "V");
            } else {
                param.put("n2o", "");
            }
            
            if (chkFlowMeterN2o.isSelected() == true) {
                param.put("flowN2o", "V");
            } else {
                param.put("flowN2o", "");
            }
            
            //mesin anestesi
            if (chkPower.isSelected() == true) {
                param.put("power", "V");
            } else {
                param.put("power", "");
            }
            
            if (chkTidakAda.isSelected() == true) {
                param.put("tidak", "V");
            } else {
                param.put("tidak", "");
            }
            
            if (chkZat.isSelected() == true) {
                param.put("zat", "V");
            } else {
                param.put("zat", "");
            }
            
            if (chkAbsorber.isSelected() == true) {
                param.put("absor", "V");
            } else {
                param.put("absor", "");
            }
            
            //manajemen jalan nafas
            if (chkSungkup.isSelected() == true) {
                param.put("sungkup", "V");
            } else {
                param.put("sungkup", "");
            }
            
            if (chkOroparingeal.isSelected() == true) {
                param.put("oropa", "V");
            } else {
                param.put("oropa", "");
            }
            
            if (chkBatang.isSelected() == true) {
                param.put("batang", "V");
            } else {
                param.put("batang", "");
            }
            
            if (chkBilah.isSelected() == true) {
                param.put("bilah", "V");
            } else {
                param.put("bilah", "");
            }
            
            if (chkGagang.isSelected() == true) {
                param.put("gagang", "V");
            } else {
                param.put("gagang", "");
            }
            
            if (chkEtt.isSelected() == true) {
                param.put("ett", "V");
            } else {
                param.put("ett", "");
            }
            
            if (chkStilet.isSelected() == true) {
                param.put("stilet", "V");
            } else {
                param.put("stilet", "");
            }
            
            if (chkSpuit.isSelected() == true) {
                param.put("spuit", "V");
            } else {
                param.put("spuit", "");
            }
            
            if (chkForceps.isSelected() == true) {
                param.put("forcep", "V");
            } else {
                param.put("forcep", "");
            }
            
            //pemantauan
            if (chkKabel.isSelected() == true) {
                param.put("kabel", "V");
            } else {
                param.put("kabel", "");
            }
            
            if (chkElektroda.isSelected() == true) {
                param.put("elek", "V");
            } else {
                param.put("elek", "");
            }
            
            if (chkNibp.isSelected() == true) {
                param.put("nibp", "V");
            } else {
                param.put("nibp", "");
            }
            
            if (chkSpo2.isSelected() == true) {
                param.put("spo2", "V");
            } else {
                param.put("spo2", "");
            }
            
            if (chkKapnografi.isSelected() == true) {
                param.put("kapno", "V");
            } else {
                param.put("kapno", "");
            }
            
            if (chkPemantau.isSelected() == true) {
                param.put("pemantau", "V");
            } else {
                param.put("pemantau", "");
            }
            
            //lain-lain
            if (chkStetoskop.isSelected() == true) {
                param.put("stetos", "V");
            } else {
                param.put("stetos", "");
            }
            
            if (chkSuction.isSelected() == true) {
                param.put("suction", "V");
            } else {
                param.put("suction", "");
            }
            
            if (chkSelangSuction.isSelected() == true) {
                param.put("selSuction", "V");
            } else {
                param.put("selSuction", "");
            }
            
            if (chkPlester.isSelected() == true) {
                param.put("plester", "V");
            } else {
                param.put("plester", "");
            }
            
            if (chkLidacaine.isSelected() == true) {
                param.put("lida", "V");
            } else {
                param.put("lida", "");
            }
            
            //obat-obat
            if (chkEpinefrin.isSelected() == true) {
                param.put("epin", "V");
            } else {
                param.put("epin", "");
            }
            
            if (chkAtropin.isSelected() == true) {
                param.put("atro", "V");
            } else {
                param.put("atro", "");
            }
            
            if (chkSedatif.isSelected() == true) {
                param.put("sedatif", "V");
            } else {
                param.put("sedatif", "");
            }
            
            if (chkOpiat.isSelected() == true) {
                param.put("opiat", "V");
            } else {
                param.put("opiat", "");
            }
            
            if (chkPelumpuh.isSelected() == true) {
                param.put("pelumpuh", "V");
            } else {
                param.put("pelumpuh", "");
            }
            
            if (chkAnalgetik.isSelected() == true) {
                param.put("anal", "V");
            } else {
                param.put("anal", "");
            }
            
            if (chkAntibiotik.isSelected() == true) {
                param.put("anti", "V");
            } else {
                param.put("anti", "");
            }

            if (chkLain.isSelected() == true) {
                param.put("lain", "V");
                param.put("ketLain", Tlain.getText() + "\n");
            } else {
                param.put("lain", "");
                param.put("ketLain", "-\n");
            }

            Valid.MyReport("rptCeklisKesiapanAnestesi.jasper", "report", "::[ Lembar Checklist Kesiapan Anestesi ]::",
                "SELECT now() tanggal", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbCeklis.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnResepObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnResepObatActionPerformed
        if (TNoRw.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan pilih salah satu data pasiennya terlebih dulu.!!!!");
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            akses.setform("RMCeklisKesiapanAnestesi");
            DlgCatatanResep form = new DlgCatatanResep(null, false);
            form.isCek();

            if (status.equals("IGD (Ralan)") || status.equals("IGD (Ranap)") || status.equals("ralan")) {
                form.setData(TNoRw.getText(), status);
            } else if (status.equals("ranap")) {
                kodekamar = "";
                kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
                        + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");
                form.setData(TNoRw.getText(), status);
            }

            form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
            form.setLocationRelativeTo(internalFrame1);
            form.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnResepObatActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TjenisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjenisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglTindakan.requestFocus();
        }
    }//GEN-LAST:event_TjenisKeyPressed

    private void chkLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainActionPerformed
        Tlain.setText("");
        if (chkLain.isSelected() == true) {
            BtnPaste.setEnabled(true);
            Tlain.setEnabled(true);
            Tlain.requestFocus();
        } else {
            Tlain.setEnabled(false);
            BtnPaste.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainActionPerformed

    private void BtnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tlain.getText().equals("")) {
                Tlain.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tlain.setText(Tlain.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMCeklisKesiapanAnestesi dialog = new RMCeklisKesiapanAnestesi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPaste;
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnResepObat;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tdiagnosis;
    private widget.TextBox Tjenis;
    private widget.TextArea Tlain;
    private widget.TextBox TnipPerawat;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TrgRawat;
    private widget.TextBox Tteknik;
    private widget.Tanggal TtglTindakan;
    private widget.Tanggal Ttglceklis;
    private widget.CekBox chkAbsorber;
    private widget.CekBox chkAnalgetik;
    private widget.CekBox chkAntibiotik;
    private widget.CekBox chkAtropin;
    private widget.CekBox chkBatang;
    private widget.CekBox chkBilah;
    private widget.CekBox chkElektroda;
    private widget.CekBox chkEpinefrin;
    private widget.CekBox chkEtt;
    private widget.CekBox chkFlowMeterN2o;
    private widget.CekBox chkFlowMeterO2;
    private widget.CekBox chkForceps;
    private widget.CekBox chkGagang;
    private widget.CekBox chkKabel;
    private widget.CekBox chkKapnografi;
    private widget.CekBox chkLain;
    private widget.CekBox chkLayar;
    private widget.CekBox chkLidacaine;
    private widget.CekBox chkMesin;
    private widget.CekBox chkN2o;
    private widget.CekBox chkNibp;
    private widget.CekBox chkOpiat;
    private widget.CekBox chkOroparingeal;
    private widget.CekBox chkPelumpuh;
    private widget.CekBox chkPemantau;
    private widget.CekBox chkPlester;
    private widget.CekBox chkPower;
    private widget.CekBox chkSaya;
    private widget.CekBox chkSedatif;
    private widget.CekBox chkSelang;
    private widget.CekBox chkSelangSuction;
    private widget.CekBox chkSpo2;
    private widget.CekBox chkSpuit;
    private widget.CekBox chkStetoskop;
    private widget.CekBox chkStilet;
    private widget.CekBox chkSuction;
    private widget.CekBox chkSungkup;
    private widget.CekBox chkSyrine;
    private widget.CekBox chkTidakAda;
    private widget.CekBox chkZat;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
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
    private widget.Label jLabel79;
    private widget.Label jLabel85;
    private widget.Label jLabel86;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label20;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass8;
    private widget.ScrollPane scrollPane13;
    private widget.Table tbCeklis;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ck.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') tglLahir, "
                    + "DATE_FORMAT(ck.tgl_ceklis,'%d/%m/%Y') tglCeklis, TIME_FORMAT(ck.jam_ceklis,'%H:%i Wita') jam, "
                    + "DATE_FORMAT(ck.tgl_tindakan,'%d/%m/%Y') tglTindakan, pg.nama nmPerawat from ceklis_kesiapan_anestesi ck "
                    + "inner join reg_periksa rp on rp.no_rawat=ck.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=ck.nip_perawat WHERE "
                    + "ck.tgl_ceklis between ? and ? and ck.no_rawat LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and p.no_rkm_medis LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and p.nm_pasien LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and pg.nama LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and ck.ruang_rawat LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and ck.jenis_operasi LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and ck.diagnosis LIKE ? or "
                    + "ck.tgl_ceklis between ? and ? and ck.teknik_anastesia LIKE ? ORDER BY ck.tgl_ceklis desc, ck.jam_ceklis desc");
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
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglCeklis"),
                        rs.getString("jam"),
                        rs.getString("diagnosis"),
                        rs.getString("teknik_anastesia"),
                        rs.getString("jenis_operasi"),
                        rs.getString("tglTindakan"),
                        rs.getString("nmPerawat"),
                        rs.getString("tgl_ceklis"),
                        rs.getString("jam_ceklis"),
                        rs.getString("diagnosis"),
                        rs.getString("teknik_anastesia"),
                        rs.getString("jenis_operasi"),
                        rs.getString("tgl_tindakan"),
                        rs.getString("mesin"),
                        rs.getString("layar"),
                        rs.getString("syrine_pump"),
                        rs.getString("selang"),
                        rs.getString("flow_meter_o2"),
                        rs.getString("n2o"),
                        rs.getString("flow_meter_n20"),
                        rs.getString("power_on"),
                        rs.getString("tidak_ada_kebocoran"),
                        rs.getString("zat_volatil"),
                        rs.getString("absorber"),
                        rs.getString("sungkup"),
                        rs.getString("oroparingeal"),
                        rs.getString("batang"),
                        rs.getString("bilah"),
                        rs.getString("gagang"),
                        rs.getString("ett"),
                        rs.getString("stilet"),
                        rs.getString("spuit"),
                        rs.getString("forceps"),
                        rs.getString("kabel_ekg"),
                        rs.getString("elektroda_ekg"),
                        rs.getString("nibp"),
                        rs.getString("spo2"),
                        rs.getString("kapnografi"),
                        rs.getString("pemantau_suhu"),
                        rs.getString("stetoskop"),
                        rs.getString("suction"),
                        rs.getString("selang_suction"),
                        rs.getString("plester"),
                        rs.getString("lidacaine"),
                        rs.getString("epinefrin"),
                        rs.getString("atropin"),
                        rs.getString("sedatif"),
                        rs.getString("opiat"),
                        rs.getString("pelumpuh_otot"),
                        rs.getString("analgetik"),
                        rs.getString("antibiotik"),
                        rs.getString("lain"),
                        rs.getString("ket_lain"),
                        rs.getString("nip_perawat")
                    });
                }
            } catch (Exception e) {
                System.out.println("rekammedis.RMCeklisKesiapanAnestesi.tampil() : " + e);
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
        Ttglceklis.setDate(new Date());
        Ttglceklis.requestFocus();
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        Tdiagnosis.setText("");
        Tteknik.setText("");
        Tjenis.setText("");        
        TtglTindakan.setDate(new Date());
        
        chkMesin.setSelected(false);
        chkLayar.setSelected(false);
        chkSyrine.setSelected(false);
        
        chkSelang.setSelected(false);
        chkFlowMeterO2.setSelected(false);
        chkN2o.setSelected(false);
        chkFlowMeterN2o.setSelected(false);
        
        chkPower.setSelected(false);
        chkTidakAda.setSelected(false);
        chkZat.setSelected(false);
        chkAbsorber.setSelected(false);
        
        chkSungkup.setSelected(false);
        chkOroparingeal.setSelected(false);
        chkBatang.setSelected(false);
        chkBilah.setSelected(false);
        chkGagang.setSelected(false);
        chkEtt.setSelected(false);
        chkStilet.setSelected(false);
        chkSpuit.setSelected(false);
        chkForceps.setSelected(false);
        
        chkKabel.setSelected(false);
        chkElektroda.setSelected(false);
        chkNibp.setSelected(false);
        chkSpo2.setSelected(false);
        chkKapnografi.setSelected(false);
        chkPemantau.setSelected(false);
        
        chkStetoskop.setSelected(false);
        chkSuction.setSelected(false);
        chkSelangSuction.setSelected(false);
        chkPlester.setSelected(false);
        chkLidacaine.setSelected(false);
        
        chkEpinefrin.setSelected(false);
        chkAtropin.setSelected(false);
        chkSedatif.setSelected(false);
        chkOpiat.setSelected(false);
        chkPelumpuh.setSelected(false);
        chkAnalgetik.setSelected(false);
        chkAntibiotik.setSelected(false);
        chkLain.setSelected(false);        
        Tlain.setText("");
        Tlain.setEnabled(false);
        BtnPaste.setEnabled(false);
        TnipPerawat.setText("-");
        TnmPerawat.setText("-");
        chkSaya.setSelected(false);
    }

    private void getData() {
        emptVariabel();
        status = "";
        if (tbCeklis.getSelectedRow() != -1) {
            TNoRw.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 1).toString());
            TNoRM.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 2).toString());
            TPasien.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 3).toString());
            TrgRawat.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 5).toString());
            Valid.SetTgl(Ttglceklis, tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 13).toString());
            cmbJam.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 14).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 14).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 14).toString().substring(6, 8));
            Tdiagnosis.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 15).toString());
            Tteknik.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 16).toString());
            Tjenis.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 17).toString());
            Valid.SetTgl(TtglTindakan, tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 18).toString());
            
            mesin = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 19).toString();
            layar = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 20).toString();
            syrine = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 21).toString();
            
            selang = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 22).toString();
            flowMeterO2 = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 23).toString();
            n2o = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 24).toString();
            flowMeterN2o = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 25).toString();
            
            power = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 26).toString();
            tidak = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 27).toString();
            zat = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 28).toString();
            absorber = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 29).toString();
            
            sungkup = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 30).toString();
            oropa = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 31).toString();
            batang = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 32).toString();
            bilah = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 33).toString();
            gagang = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 34).toString();
            ett = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 35).toString();
            stilet = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 36).toString();
            spuit = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 37).toString();
            forcep = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 38).toString();
            
            kabel = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 39).toString();
            elek = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 40).toString();
            nibp = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 41).toString();
            spo2 = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 42).toString();
            kapno = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 43).toString();
            pemantau = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 44).toString();
            
            stetos = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 45).toString();
            suction = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 46).toString();
            selangSuction = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 47).toString();
            plester = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 48).toString();
            lida = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 49).toString();
            
            epin = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 50).toString();
            atro = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 51).toString();
            sedatif = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 52).toString();
            opi = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 53).toString();
            pelumpuh = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 54).toString();
            anal = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 55).toString();
            anti = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 56).toString();
            lain = tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 57).toString();
            Tlain.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 58).toString());
            TnipPerawat.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 59).toString());
            TnmPerawat.setText(tbCeklis.getValueAt(tbCeklis.getSelectedRow(), 12).toString());
            status = Sequel.cariIsi("select if(status_lanjut='Ranap','ranap','ralan') from reg_periksa where no_rawat='" + TNoRw.getText() + "'");
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkegiatan_operasi());
        BtnGanti.setEnabled(akses.getkegiatan_operasi());
        BtnHapus.setEnabled(akses.getkegiatan_operasi());
    }
    
    private void dataCek() {
        //listrik
        if (mesin.equals("ya")) {
            chkMesin.setSelected(true);
        } else {
            chkMesin.setSelected(false);
        }
        
        if (layar.equals("ya")) {
            chkLayar.setSelected(true);
        } else {
            chkLayar.setSelected(false);
        }
        
        if (syrine.equals("ya")) {
            chkSyrine.setSelected(true);
        } else {
            chkSyrine.setSelected(false);
        }
        
        //gas medis
        if (selang.equals("ya")) {
            chkSelang.setSelected(true);
        } else {
            chkSelang.setSelected(false);
        }
        
        if (flowMeterO2.equals("ya")) {
            chkFlowMeterO2.setSelected(true);
        } else {
            chkFlowMeterO2.setSelected(false);
        }
        
        if (n2o.equals("ya")) {
            chkN2o.setSelected(true);
        } else {
            chkN2o.setSelected(false);
        }
        
        if (flowMeterN2o.equals("ya")) {
            chkFlowMeterN2o.setSelected(true);
        } else {
            chkFlowMeterN2o.setSelected(false);
        }
        
        //mesin anestesia
        if (power.equals("ya")) {
            chkPower.setSelected(true);
        } else {
            chkPower.setSelected(false);
        }
        
        if (tidak.equals("ya")) {
            chkTidakAda.setSelected(true);
        } else {
            chkTidakAda.setSelected(false);
        }
        
        if (zat.equals("ya")) {
            chkZat.setSelected(true);
        } else {
            chkZat.setSelected(false);
        }
        
        if (absorber.equals("ya")) {
            chkAbsorber.setSelected(true);
        } else {
            chkAbsorber.setSelected(false);
        }
        
        //manajemen jalan nafas
        if (sungkup.equals("ya")) {
            chkSungkup.setSelected(true);
        } else {
            chkSungkup.setSelected(false);
        }
        
        if (oropa.equals("ya")) {
            chkOroparingeal.setSelected(true);
        } else {
            chkOroparingeal.setSelected(false);
        }
        
        if (batang.equals("ya")) {
            chkBatang.setSelected(true);
        } else {
            chkBatang.setSelected(false);
        }
        
        if (bilah.equals("ya")) {
            chkBilah.setSelected(true);
        } else {
            chkBilah.setSelected(false);
        }
        
        if (gagang.equals("ya")) {
            chkGagang.setSelected(true);
        } else {
            chkGagang.setSelected(false);
        }
        
        if (ett.equals("ya")) {
            chkEtt.setSelected(true);
        } else {
            chkEtt.setSelected(false);
        }
        
        if (stilet.equals("ya")) {
            chkStilet.setSelected(true);
        } else {
            chkStilet.setSelected(false);
        }
        
        if (spuit.equals("ya")) {
            chkSpuit.setSelected(true);
        } else {
            chkSpuit.setSelected(false);
        }
        
        if (forcep.equals("ya")) {
            chkForceps.setSelected(true);
        } else {
            chkForceps.setSelected(false);
        }
        
        //pemantauan
        if (kabel.equals("ya")) {
            chkKabel.setSelected(true);
        } else {
            chkKabel.setSelected(false);
        }
        
        if (elek.equals("ya")) {
            chkElektroda.setSelected(true);
        } else {
            chkElektroda.setSelected(false);
        }
        
        if (nibp.equals("ya")) {
            chkNibp.setSelected(true);
        } else {
            chkNibp.setSelected(false);
        }
        
        if (spo2.equals("ya")) {
            chkSpo2.setSelected(true);
        } else {
            chkSpo2.setSelected(false);
        }
        
        if (kapno.equals("ya")) {
            chkKapnografi.setSelected(true);
        } else {
            chkKapnografi.setSelected(false);
        }
        
        if (pemantau.equals("ya")) {
            chkPemantau.setSelected(true);
        } else {
            chkPemantau.setSelected(false);
        }
        
        //lain-lain
        if (stetos.equals("ya")) {
            chkStetoskop.setSelected(true);
        } else {
            chkStetoskop.setSelected(false);
        }
        
        if (suction.equals("ya")) {
            chkSuction.setSelected(true);
        } else {
            chkSuction.setSelected(false);
        }
        
        if (selangSuction.equals("ya")) {
            chkSelangSuction.setSelected(true);
        } else {
            chkSelangSuction.setSelected(false);
        }
        
        if (plester.equals("ya")) {
            chkPlester.setSelected(true);
        } else {
            chkPlester.setSelected(false);
        }
        
        if (lida.equals("ya")) {
            chkLidacaine.setSelected(true);
        } else {
            chkLidacaine.setSelected(false);
        }
        
        //obat-obat
        if (epin.equals("ya")) {
            chkEpinefrin.setSelected(true);
        } else {
            chkEpinefrin.setSelected(false);
        }
        
        if (atro.equals("ya")) {
            chkAtropin.setSelected(true);
        } else {
            chkAtropin.setSelected(false);
        }
        
        if (sedatif.equals("ya")) {
            chkSedatif.setSelected(true);
        } else {
            chkSedatif.setSelected(false);
        }
        
        if (opi.equals("ya")) {
            chkOpiat.setSelected(true);
        } else {
            chkOpiat.setSelected(false);
        }
        
        if (pelumpuh.equals("ya")) {
            chkPelumpuh.setSelected(true);
        } else {
            chkPelumpuh.setSelected(false);
        }
        
        if (anal.equals("ya")) {
            chkAnalgetik.setSelected(true);
        } else {
            chkAnalgetik.setSelected(false);
        }
        
        if (anti.equals("ya")) {
            chkAntibiotik.setSelected(true);
        } else {
            chkAntibiotik.setSelected(false);
        }
        
        if (lain.equals("ya")) {
            chkLain.setSelected(true);
            Tlain.setEnabled(true);
            BtnPaste.setEnabled(true);
        } else {
            chkLain.setSelected(false);
            Tlain.setEnabled(false);
            BtnPaste.setEnabled(false);
        }
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan, String stts_rwt) {
        TNoRw.setText(norw);
        TNoRM.setText(norm);
        TPasien.setText(nmpasien);
        TrgRawat.setText(ruangan);
        status = stts_rwt;
        
        if (akses.getadmin() == true) {
            TnipPerawat.setText("-");
            TnmPerawat.setText("-");
            chkSaya.setSelected(false);
        } else {
            TnipPerawat.setText(akses.getkode());
            TnmPerawat.setText(Sequel.cariIsi("select nama from pegawai where nik='" + TnipPerawat.getText() + "'"));
            chkSaya.setSelected(true);
        }
    }
    
    private void cekData() {
        //listrik
        if (chkMesin.isSelected() == true) {
            mesin = "ya";
        } else {
            mesin = "tidak";
        }
        
        if (chkLayar.isSelected() == true) {
            layar = "ya";
        } else {
            layar = "tidak";
        }
        
        if (chkSyrine.isSelected() == true) {
            syrine = "ya";
        } else {
            syrine = "tidak";
        }
        
        //gas medis
        if (chkSelang.isSelected() == true) {
            selang = "ya";
        } else {
            selang = "tidak";
        }
        
        if (chkFlowMeterO2.isSelected() == true) {
            flowMeterO2 = "ya";
        } else {
            flowMeterO2 = "tidak";
        }
        
        if (chkN2o.isSelected() == true) {
            n2o = "ya";
        } else {
            n2o = "tidak";
        }
        
        if (chkFlowMeterN2o.isSelected() == true) {
            flowMeterN2o = "ya";
        } else {
            flowMeterN2o = "tidak";
        }
        
        //mesin anestesia
        if (chkPower.isSelected() == true) {
            power = "ya";
        } else {
            power = "tidak";
        }
        
        if (chkTidakAda.isSelected() == true) {
            tidak = "ya";
        } else {
            tidak = "tidak";
        }
        
        if (chkZat.isSelected() == true) {
            zat = "ya";
        } else {
            zat = "tidak";
        }
        
        if (chkAbsorber.isSelected() == true) {
            absorber = "ya";
        } else {
            absorber = "tidak";
        }
        
        //manajemen jalan nafas
        if (chkSungkup.isSelected() == true) {
            sungkup = "ya";
        } else {
            sungkup = "tidak";
        }
        
        if (chkOroparingeal.isSelected() == true) {
            oropa = "ya";
        } else {
            oropa = "tidak";
        }
        
        if (chkBatang.isSelected() == true) {
            batang = "ya";
        } else {
            batang = "tidak";
        }
        
        if (chkBilah.isSelected() == true) {
            bilah = "ya";
        } else {
            bilah = "tidak";
        }
        
        if (chkGagang.isSelected() == true) {
            gagang = "ya";
        } else {
            gagang = "tidak";
        }
        
        if (chkEtt.isSelected() == true) {
            ett = "ya";
        } else {
            ett = "tidak";
        }
        
        if (chkStilet.isSelected() == true) {
            stilet = "ya";
        } else {
            stilet = "tidak";
        }
        
        if (chkSpuit.isSelected() == true) {
            spuit = "ya";
        } else {
            spuit = "tidak";
        }
        
        if (chkForceps.isSelected() == true) {
            forcep = "ya";
        } else {
            forcep = "tidak";
        }
        
        //pemantauan
        if (chkKabel.isSelected() == true) {
            kabel = "ya";
        } else {
            kabel = "tidak";
        }
        
        if (chkElektroda.isSelected() == true) {
            elek = "ya";
        } else {
            elek = "tidak";
        }
        
        if (chkNibp.isSelected() == true) {
            nibp = "ya";
        } else {
            nibp = "tidak";
        }
        
        if (chkSpo2.isSelected() == true) {
            spo2 = "ya";
        } else {
            spo2 = "tidak";
        }
        
        if (chkKapnografi.isSelected() == true) {
            kapno = "ya";
        } else {
            kapno = "tidak";
        }
        
        if (chkPemantau.isSelected() == true) {
            pemantau = "ya";
        } else {
            pemantau = "tidak";
        }
        
        //lain-lain
        if (chkStetoskop.isSelected() == true) {
            stetos = "ya";
        } else {
            stetos = "tidak";
        }
        
        if (chkSuction.isSelected() == true) {
            suction = "ya";
        } else {
            suction = "tidak";
        }
        
        if (chkSelangSuction.isSelected() == true) {
            selangSuction = "ya";
        } else {
            selangSuction = "tidak";
        }
        
        if (chkPlester.isSelected() == true) {
            plester = "ya";
        } else {
            plester = "tidak";
        }
        
        if (chkLidacaine.isSelected() == true) {
            lida = "ya";
        } else {
            lida = "tidak";
        }
        
        //obat-obat
        if (chkEpinefrin.isSelected() == true) {
            epin = "ya";
        } else {
            epin = "tidak";
        }
        
        if (chkAtropin.isSelected() == true) {
            atro = "ya";
        } else {
            atro = "tidak";
        }
        
        if (chkSedatif.isSelected() == true) {
            sedatif = "ya";
        } else {
            sedatif = "tidak";
        }
        
        if (chkOpiat.isSelected() == true) {
            opi = "ya";
        } else {
            opi = "tidak";
        }
        
        if (chkPelumpuh.isSelected() == true) {
            pelumpuh = "ya";
        } else {
            pelumpuh = "tidak";
        }
        
        if (chkAnalgetik.isSelected() == true) {
            anal = "ya";
        } else {
            anal = "tidak";
        }
        
        if (chkAntibiotik.isSelected() == true) {
            anti = "ya";
        } else {
            anti = "tidak";
        }
        
        if (chkLain.isSelected() == true) {
            lain = "ya";
        } else {
            lain = "tidak";
        }
    }
    
    private void emptVariabel() {
        mesin = "";
        layar = "";
        syrine = "";
        selang = "";
        flowMeterO2 = "";
        n2o = "";
        flowMeterN2o = "";
        power = "";
        tidak = "";
        zat = "";
        absorber = "";
        sungkup = "";
        oropa = "";
        batang = "";
        bilah = "";
        gagang = "";
        ett = "";
        stilet = "";
        spuit = "";
        forcep = "";
        kabel = "";
        elek = "";
        nibp = "";
        spo2 = "";
        kapno = "";
        pemantau = "";
        stetos = "";
        suction = "";
        selangSuction = "";
        plester = "";
        lida = "";
        epin = "";
        atro = "";
        sedatif = "";
        opi = "";
        pelumpuh = "";
        anal = "";
        anti = "";
        lain = "";
    }
}
