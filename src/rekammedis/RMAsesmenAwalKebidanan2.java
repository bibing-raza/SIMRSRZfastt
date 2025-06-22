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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMAsesmenAwalKebidanan2 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, pilihan = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private DlgCariDokter dokter = new DlgCariDokter(null, false);    
    private String nipBidan1 = "", nipBidan2 = "", nipDokter = "", tidakAda = "", tidakDiketahui = "", alergiObat = "", alergiMakanan = "",
            alergiLainya = "", gelangTanda = "", alergiDiberitahukanDokter = "", alergiDiberitahukanFarmasis = "", alergiDiberitahukanAhligizi = "",
            ya = "", pendengaran = "", penglihatan = "", kognitif = "", fisik = "", budaya = "", emosi = "", bahasa = "", lainHambatan = "",
            diagnosa = "", tindakanKeperawatan = "", lainKebutuhanEdukasi = "", obatObatan = "", rehabilitasi = "", diet = "", manajemenNyeri = "",
            pasien = "", keluargaPasien = "", tidakDapat = "", identifikasi1 = "", identifikasi2 = "", identifikasi3 = "", identifikasi4 = "",
            identifikasi5 = "", identifikasi6 = "", identifikasi7 = "", identifikasi8 = "", identifikasi9 = "", identifikasi10 = "", saya1 = "", 
            saya2 = "", noRawat = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenAwalKebidanan2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "Kode Komite", "Nama Komite"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
//        tbKomite.setModel(tabMode);
//        tbKomite.setPreferredScrollableViewportSize(new Dimension(500,500));
//        tbKomite.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//
//        for (i = 0; i < 2; i++) {
//            TableColumn column = tbKomite.getColumnModel().getColumn(i);
//            if (i == 0) {
//                column.setPreferredWidth(80);
//            } else if (i == 1) {
//                column.setPreferredWidth(300);
//            }
//        }
//        tbKomite.setDefaultRenderer(Object.class, new WarnaTable());

          Tlokasi.setDocument(new batasInput((int) 180).getKata(Tlokasi));
          Tprovo.setDocument(new batasInput((int) 180).getKata(Tprovo));
          Tquality.setDocument(new batasInput((int) 180).getKata(Tquality));
          TketRiwAlergiObat.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiObat));
          TreakRiwAlergiObat.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiObat));
          TketRiwAlergiMak.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiMak));
          TreakRiwAlergiMak.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiMak));
          TketRiwAlergiLain.setDocument(new batasInput((int) 255).getKata(TketRiwAlergiLain));
          TreakRiwAlergiLain.setDocument(new batasInput((int) 255).getKata(TreakRiwAlergiLain));
          TalatBantu.setDocument(new batasInput((int) 180).getKata(TalatBantu));
          Tprotesis.setDocument(new batasInput((int) 180).getKata(Tprotesis));
          TcacatTubuh.setDocument(new batasInput((int) 180).getKata(TcacatTubuh));
          TketLainHambatan.setDocument(new batasInput((int) 180).getKata(TketLainHambatan));
          Tsebutkan.setDocument(new batasInput((int) 180).getKata(Tsebutkan));
          TtindakanKep.setDocument(new batasInput((int) 180).getKata(TtindakanKep));
          TlainKebutuhan.setDocument(new batasInput((int) 180).getKata(TlainKebutuhan));
          TnmKlgPasien.setDocument(new batasInput((int) 180).getKata(TnmKlgPasien));
          TtidakDapat.setDocument(new batasInput((int) 180).getKata(TtidakDapat));
          Tmemerlukan.setDocument(new batasInput((int) 255).getKata(Tmemerlukan));
          TnmKeluargaPasien.setDocument(new batasInput((int) 180).getKata(TnmKeluargaPasien));

        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {                
                if (pilihan == 1) {
                    if (petugas.getTable().getSelectedRow() != -1) {                    
                        nipBidan1 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmBidan1.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnBidan1.requestFocus();
                    }
                } else if (pilihan == 2) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipBidan2 = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmBidan2.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnBidan2.requestFocus();
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
                if (akses.getform().equals("RMAsesmenAwalKebidanan2")) {
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
        scrollInput = new widget.ScrollPane();
        PanelInput = new javax.swing.JPanel();
        jLabel128 = new widget.Label();
        jLabel365 = new widget.Label();
        cmbNyeri = new widget.ComboBox();
        jLabel366 = new widget.Label();
        Tlokasi = new widget.TextBox();
        jLabel367 = new widget.Label();
        cmbJenis = new widget.ComboBox();
        PanelWall = new usu.widget.glass.PanelGlass();
        cmbSkala = new widget.ComboBox();
        jLabel100 = new widget.Label();
        jLabel68 = new widget.Label();
        jLabel70 = new widget.Label();
        cmbProvo = new widget.ComboBox();
        Tprovo = new widget.TextBox();
        jLabel71 = new widget.Label();
        jLabel72 = new widget.Label();
        cmbQuality = new widget.ComboBox();
        Tquality = new widget.TextBox();
        jLabel73 = new widget.Label();
        jLabel76 = new widget.Label();
        cmbRadia = new widget.ComboBox();
        jLabel77 = new widget.Label();
        jLabel78 = new widget.Label();
        cmbSever = new widget.ComboBox();
        jLabel79 = new widget.Label();
        jLabel80 = new widget.Label();
        cmbTime = new widget.ComboBox();
        jLabel81 = new widget.Label();
        cmbLama = new widget.ComboBox();
        jLabel129 = new widget.Label();
        jLabel130 = new widget.Label();
        jLabel64 = new widget.Label();
        jLabel75 = new widget.Label();
        chkRiwTidakAda = new widget.CekBox();
        chkRiwTidakDik = new widget.CekBox();
        cmbGizi1 = new widget.ComboBox();
        skorGizi1 = new widget.TextBox();
        chkRiwAlergiObat = new widget.CekBox();
        TketRiwAlergiObat = new widget.TextBox();
        cmbYaGizi1 = new widget.ComboBox();
        skorYaGizi1 = new widget.TextBox();
        jLabel82 = new widget.Label();
        TreakRiwAlergiObat = new widget.TextBox();
        jLabel65 = new widget.Label();
        chkRiwAlergiMak = new widget.CekBox();
        TketRiwAlergiMak = new widget.TextBox();
        kesimpulanGizi = new widget.TextArea();
        cmbGizi2 = new widget.ComboBox();
        skorGizi2 = new widget.TextBox();
        jLabel83 = new widget.Label();
        TreakRiwAlergiMak = new widget.TextBox();
        jLabel74 = new widget.Label();
        TotSkorGizi = new widget.TextBox();
        chkRiwAlergiLain = new widget.CekBox();
        TketRiwAlergiLain = new widget.TextBox();
        jLabel84 = new widget.Label();
        TreakRiwAlergiLain = new widget.TextBox();
        chkGelang = new widget.CekBox();
        jLabel85 = new widget.Label();
        jLabel131 = new widget.Label();
        chkDokter = new widget.CekBox();
        chkFarmasis = new widget.CekBox();
        chkAhliGz = new widget.CekBox();
        jLabel86 = new widget.Label();
        TalatBantu = new widget.TextBox();
        jLabel87 = new widget.Label();
        Tprotesis = new widget.TextBox();
        jLabel88 = new widget.Label();
        TcacatTubuh = new widget.TextBox();
        jLabel89 = new widget.Label();
        cmbAdl = new widget.ComboBox();
        jLabel90 = new widget.Label();
        cmbRiwJatuh = new widget.ComboBox();
        label14 = new widget.Label();
        TnmBidan1 = new widget.TextBox();
        BtnBidan1 = new widget.Button();
        chkSaya1 = new widget.CekBox();
        jLabel132 = new widget.Label();
        label15 = new widget.Label();
        chkYaTerdapat = new widget.CekBox();
        chkPendengaran = new widget.CekBox();
        chkPenglihatan = new widget.CekBox();
        chkKognitif = new widget.CekBox();
        chkBudaya = new widget.CekBox();
        chkEmosi = new widget.CekBox();
        chkBahasa = new widget.CekBox();
        chkLainHambatan = new widget.CekBox();
        TketLainHambatan = new widget.TextBox();
        label16 = new widget.Label();
        cmbDibutuhkan = new widget.ComboBox();
        jLabel91 = new widget.Label();
        Tsebutkan = new widget.TextBox();
        jLabel92 = new widget.Label();
        cmbBahasa = new widget.ComboBox();
        label17 = new widget.Label();
        chkDiagnosa = new widget.CekBox();
        chkObatTerapi = new widget.CekBox();
        chkDietNutrisi = new widget.CekBox();
        chkRehabilitasi = new widget.CekBox();
        chkManajemenNyeri = new widget.CekBox();
        chkTindakanKep = new widget.CekBox();
        TtindakanKep = new widget.TextBox();
        chkLainKebutuhan = new widget.CekBox();
        TlainKebutuhan = new widget.TextBox();
        jLabel133 = new widget.Label();
        label18 = new widget.Label();
        chkPasien = new widget.CekBox();
        chkKlgPasien = new widget.CekBox();
        TnmKlgPasien = new widget.TextBox();
        chkTidakDapat = new widget.CekBox();
        TtidakDapat = new widget.TextBox();
        label19 = new widget.Label();
        TtglEdukasi = new widget.Tanggal();
        label20 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel368 = new widget.Label();
        TnmDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel134 = new widget.Label();
        label21 = new widget.Label();
        chkIdentifikai1 = new widget.CekBox();
        chkIdentifikai6 = new widget.CekBox();
        chkIdentifikai2 = new widget.CekBox();
        chkIdentifikai7 = new widget.CekBox();
        chkIdentifikai3 = new widget.CekBox();
        chkIdentifikai8 = new widget.CekBox();
        chkIdentifikai4 = new widget.CekBox();
        chkIdentifikai9 = new widget.CekBox();
        chkIdentifikai5 = new widget.CekBox();
        chkIdentifikai10 = new widget.CekBox();
        label22 = new widget.Label();
        Tmemerlukan = new widget.TextBox();
        label23 = new widget.Label();
        cmbMPP = new widget.ComboBox();
        label24 = new widget.Label();
        cmbDP = new widget.ComboBox();
        label25 = new widget.Label();
        TnmKeluargaPasien = new widget.TextBox();
        label26 = new widget.Label();
        TnmBidan2 = new widget.TextBox();
        BtnBidan2 = new widget.Button();
        chkSaya2 = new widget.CekBox();
        chkFisik = new widget.CekBox();
        label27 = new widget.Label();
        TtglDp = new widget.Tanggal();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 557));

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 1455));
        PanelInput.setRequestFocusEnabled(false);
        PanelInput.setLayout(null);

        jLabel128.setForeground(new java.awt.Color(0, 0, 0));
        jLabel128.setText("ASSESMEN NYERI");
        jLabel128.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel128.setName("jLabel128"); // NOI18N
        PanelInput.add(jLabel128);
        jLabel128.setBounds(0, 10, 130, 23);

        jLabel365.setForeground(new java.awt.Color(0, 0, 0));
        jLabel365.setText("Nyeri :");
        jLabel365.setName("jLabel365"); // NOI18N
        PanelInput.add(jLabel365);
        jLabel365.setBounds(0, 38, 120, 23);

        cmbNyeri.setBackground(new java.awt.Color(245, 253, 240));
        cmbNyeri.setForeground(new java.awt.Color(0, 0, 0));
        cmbNyeri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbNyeri.setLightWeightPopupEnabled(false);
        cmbNyeri.setName("cmbNyeri"); // NOI18N
        cmbNyeri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNyeriActionPerformed(evt);
            }
        });
        PanelInput.add(cmbNyeri);
        cmbNyeri.setBounds(125, 38, 60, 23);

        jLabel366.setForeground(new java.awt.Color(0, 0, 0));
        jLabel366.setText("Lokasi :");
        jLabel366.setName("jLabel366"); // NOI18N
        PanelInput.add(jLabel366);
        jLabel366.setBounds(185, 38, 50, 23);

        Tlokasi.setForeground(new java.awt.Color(0, 0, 0));
        Tlokasi.setName("Tlokasi"); // NOI18N
        Tlokasi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlokasiKeyPressed(evt);
            }
        });
        PanelInput.add(Tlokasi);
        Tlokasi.setBounds(240, 38, 330, 23);

        jLabel367.setForeground(new java.awt.Color(0, 0, 0));
        jLabel367.setText("Jenis :");
        jLabel367.setName("jLabel367"); // NOI18N
        PanelInput.add(jLabel367);
        jLabel367.setBounds(580, 38, 40, 23);

        cmbJenis.setBackground(new java.awt.Color(245, 253, 240));
        cmbJenis.setForeground(new java.awt.Color(0, 0, 0));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Akut", "Kronis" }));
        cmbJenis.setLightWeightPopupEnabled(false);
        cmbJenis.setName("cmbJenis"); // NOI18N
        PanelInput.add(cmbJenis);
        cmbJenis.setBounds(626, 38, 65, 23);

        PanelWall.setBackground(new java.awt.Color(29, 29, 29));
        PanelWall.setBackgroundImage(new javax.swing.ImageIcon(getClass().getResource("/picture/skala_nyeri.png"))); // NOI18N
        PanelWall.setBackgroundImageType(usu.widget.constan.BackgroundConstan.BACKGROUND_IMAGE_STRECT);
        PanelWall.setPreferredSize(new java.awt.Dimension(200, 200));
        PanelWall.setRound(false);
        PanelWall.setWarna(new java.awt.Color(110, 110, 110));
        PanelWall.setLayout(null);
        PanelInput.add(PanelWall);
        PanelWall.setBounds(30, 66, 540, 230);

        cmbSkala.setForeground(new java.awt.Color(0, 0, 0));
        cmbSkala.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        cmbSkala.setName("cmbSkala"); // NOI18N
        PanelInput.add(cmbSkala);
        cmbSkala.setBounds(665, 273, 45, 23);

        jLabel100.setForeground(new java.awt.Color(0, 0, 0));
        jLabel100.setText("Skala Nyeri : ");
        jLabel100.setName("jLabel100"); // NOI18N
        PanelInput.add(jLabel100);
        jLabel100.setBounds(580, 273, 80, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Provocation");
        jLabel68.setName("jLabel68"); // NOI18N
        PanelInput.add(jLabel68);
        jLabel68.setBounds(0, 301, 100, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel70.setText(": Faktor yang memperburuk rasa nyeri");
        jLabel70.setName("jLabel70"); // NOI18N
        PanelInput.add(jLabel70);
        jLabel70.setBounds(110, 301, 210, 23);

        cmbProvo.setForeground(new java.awt.Color(0, 0, 0));
        cmbProvo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Cahaya", "Gelap", "Gerakan", "Berbaring", "Lainnya" }));
        cmbProvo.setName("cmbProvo"); // NOI18N
        cmbProvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProvoActionPerformed(evt);
            }
        });
        PanelInput.add(cmbProvo);
        cmbProvo.setBounds(325, 301, 80, 23);

        Tprovo.setBackground(new java.awt.Color(245, 250, 240));
        Tprovo.setForeground(new java.awt.Color(0, 0, 0));
        Tprovo.setName("Tprovo"); // NOI18N
        Tprovo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprovoKeyPressed(evt);
            }
        });
        PanelInput.add(Tprovo);
        Tprovo.setBounds(410, 301, 515, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("Quality");
        jLabel71.setName("jLabel71"); // NOI18N
        PanelInput.add(jLabel71);
        jLabel71.setBounds(0, 329, 100, 23);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel72.setText(": Rasa nyeri seperti");
        jLabel72.setName("jLabel72"); // NOI18N
        PanelInput.add(jLabel72);
        jLabel72.setBounds(110, 329, 210, 23);

        cmbQuality.setForeground(new java.awt.Color(0, 0, 0));
        cmbQuality.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ditusuk", "Dipukul", "Berdenyut", "Ditikam", "Kram", "Ditarik", "Dibakar", "Tajam", "Lainnya" }));
        cmbQuality.setName("cmbQuality"); // NOI18N
        cmbQuality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbQualityActionPerformed(evt);
            }
        });
        PanelInput.add(cmbQuality);
        cmbQuality.setBounds(325, 329, 85, 23);

        Tquality.setBackground(new java.awt.Color(245, 250, 240));
        Tquality.setForeground(new java.awt.Color(0, 0, 0));
        Tquality.setName("Tquality"); // NOI18N
        Tquality.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TqualityKeyPressed(evt);
            }
        });
        PanelInput.add(Tquality);
        Tquality.setBounds(415, 329, 510, 23);

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Radiation");
        jLabel73.setName("jLabel73"); // NOI18N
        PanelInput.add(jLabel73);
        jLabel73.setBounds(0, 357, 100, 23);

        jLabel76.setForeground(new java.awt.Color(0, 0, 0));
        jLabel76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel76.setText(": Nyeri menjalar ke bagian tubuh yang lain");
        jLabel76.setName("jLabel76"); // NOI18N
        PanelInput.add(jLabel76);
        jLabel76.setBounds(110, 357, 210, 23);

        cmbRadia.setForeground(new java.awt.Color(0, 0, 0));
        cmbRadia.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRadia.setName("cmbRadia"); // NOI18N
        PanelInput.add(cmbRadia);
        cmbRadia.setBounds(325, 357, 60, 23);

        jLabel77.setForeground(new java.awt.Color(0, 0, 0));
        jLabel77.setText("Severity");
        jLabel77.setName("jLabel77"); // NOI18N
        PanelInput.add(jLabel77);
        jLabel77.setBounds(0, 385, 100, 23);

        jLabel78.setForeground(new java.awt.Color(0, 0, 0));
        jLabel78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel78.setText(": Tingkat keparahan nyeri");
        jLabel78.setName("jLabel78"); // NOI18N
        PanelInput.add(jLabel78);
        jLabel78.setBounds(110, 385, 210, 23);

        cmbSever.setForeground(new java.awt.Color(0, 0, 0));
        cmbSever.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak Nyeri", "Ringan", "Sedang", "Berat" }));
        cmbSever.setName("cmbSever"); // NOI18N
        PanelInput.add(cmbSever);
        cmbSever.setBounds(325, 385, 86, 23);

        jLabel79.setForeground(new java.awt.Color(0, 0, 0));
        jLabel79.setText("Time");
        jLabel79.setName("jLabel79"); // NOI18N
        PanelInput.add(jLabel79);
        jLabel79.setBounds(0, 413, 100, 23);

        jLabel80.setForeground(new java.awt.Color(0, 0, 0));
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText(": Nyeri berlangsung");
        jLabel80.setName("jLabel80"); // NOI18N
        PanelInput.add(jLabel80);
        jLabel80.setBounds(110, 413, 210, 23);

        cmbTime.setForeground(new java.awt.Color(0, 0, 0));
        cmbTime.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Terus Menerus", "Hilang Timbul" }));
        cmbTime.setName("cmbTime"); // NOI18N
        cmbTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTimeActionPerformed(evt);
            }
        });
        PanelInput.add(cmbTime);
        cmbTime.setBounds(325, 413, 105, 23);

        jLabel81.setForeground(new java.awt.Color(0, 0, 0));
        jLabel81.setText("Lama  : ");
        jLabel81.setName("jLabel81"); // NOI18N
        PanelInput.add(jLabel81);
        jLabel81.setBounds(430, 413, 50, 23);

        cmbLama.setForeground(new java.awt.Color(0, 0, 0));
        cmbLama.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 30 Menit", "> 30 Menit" }));
        cmbLama.setName("cmbLama"); // NOI18N
        PanelInput.add(cmbLama);
        cmbLama.setBounds(485, 413, 86, 23);

        jLabel129.setForeground(new java.awt.Color(0, 0, 0));
        jLabel129.setText("SKRINING GIZI AWAL");
        jLabel129.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel129.setName("jLabel129"); // NOI18N
        PanelInput.add(jLabel129);
        jLabel129.setBounds(0, 441, 160, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel130.setText("RIWAYAT ALERGI");
        jLabel130.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel130.setName("jLabel130"); // NOI18N
        PanelInput.add(jLabel130);
        jLabel130.setBounds(650, 441, 120, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("1. Apakah pasien mengalami penurunan BB yang tidak direncanakan/tidak diinginkan dalam 6 bulan terakhir ?");
        jLabel64.setName("jLabel64"); // NOI18N
        PanelInput.add(jLabel64);
        jLabel64.setBounds(0, 469, 560, 23);

        jLabel75.setForeground(new java.awt.Color(0, 0, 0));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setText("Skor :");
        jLabel75.setName("jLabel75"); // NOI18N
        PanelInput.add(jLabel75);
        jLabel75.setBounds(590, 469, 40, 23);

        chkRiwTidakAda.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwTidakAda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwTidakAda.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwTidakAda.setText("Tidak Ada");
        chkRiwTidakAda.setBorderPainted(true);
        chkRiwTidakAda.setBorderPaintedFlat(true);
        chkRiwTidakAda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwTidakAda.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwTidakAda.setName("chkRiwTidakAda"); // NOI18N
        chkRiwTidakAda.setOpaque(false);
        chkRiwTidakAda.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRiwTidakAda);
        chkRiwTidakAda.setBounds(650, 469, 80, 23);

        chkRiwTidakDik.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwTidakDik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwTidakDik.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwTidakDik.setText("Tidak Diketahui");
        chkRiwTidakDik.setBorderPainted(true);
        chkRiwTidakDik.setBorderPaintedFlat(true);
        chkRiwTidakDik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwTidakDik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwTidakDik.setName("chkRiwTidakDik"); // NOI18N
        chkRiwTidakDik.setOpaque(false);
        chkRiwTidakDik.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRiwTidakDik);
        chkRiwTidakDik.setBounds(740, 469, 100, 23);

        cmbGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Tidak Yakin (ada tanda : baju menjadi longgar)", "Ya ada penurunan BB sebanyak :" }));
        cmbGizi1.setName("cmbGizi1"); // NOI18N
        cmbGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi1ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGizi1);
        cmbGizi1.setBounds(320, 497, 260, 23);

        skorGizi1.setEditable(false);
        skorGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi1.setText("0");
        skorGizi1.setFocusTraversalPolicyProvider(true);
        skorGizi1.setName("skorGizi1"); // NOI18N
        PanelInput.add(skorGizi1);
        skorGizi1.setBounds(590, 497, 40, 23);

        chkRiwAlergiObat.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiObat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiObat.setText("Alergi Obat");
        chkRiwAlergiObat.setBorderPainted(true);
        chkRiwAlergiObat.setBorderPaintedFlat(true);
        chkRiwAlergiObat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiObat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiObat.setName("chkRiwAlergiObat"); // NOI18N
        chkRiwAlergiObat.setOpaque(false);
        chkRiwAlergiObat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiObatActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiObat);
        chkRiwAlergiObat.setBounds(650, 497, 80, 23);

        TketRiwAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiObat.setName("TketRiwAlergiObat"); // NOI18N
        TketRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiObatKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiObat);
        TketRiwAlergiObat.setBounds(735, 497, 390, 23);

        cmbYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        cmbYaGizi1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 5 Kg", "6 - 10 Kg", "11 - 15 Kg", "> 15 Kg", "Tidak tahu berapa Kg penurunanya" }));
        cmbYaGizi1.setName("cmbYaGizi1"); // NOI18N
        cmbYaGizi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYaGizi1ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbYaGizi1);
        cmbYaGizi1.setBounds(320, 525, 260, 23);

        skorYaGizi1.setEditable(false);
        skorYaGizi1.setForeground(new java.awt.Color(0, 0, 0));
        skorYaGizi1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorYaGizi1.setText("0");
        skorYaGizi1.setFocusTraversalPolicyProvider(true);
        skorYaGizi1.setName("skorYaGizi1"); // NOI18N
        PanelInput.add(skorYaGizi1);
        skorYaGizi1.setBounds(590, 525, 40, 23);

        jLabel82.setForeground(new java.awt.Color(0, 0, 0));
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel82.setText("Reaksi Alergi Obat :");
        jLabel82.setName("jLabel82"); // NOI18N
        PanelInput.add(jLabel82);
        jLabel82.setBounds(650, 525, 110, 23);

        TreakRiwAlergiObat.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiObat.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiObat.setName("TreakRiwAlergiObat"); // NOI18N
        TreakRiwAlergiObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiObatKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiObat);
        TreakRiwAlergiObat.setBounds(760, 525, 365, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("2. Apakah asupan makan pasien berkurang karena penurunan nafsu makan / kesulitan menerima makanan ?");
        jLabel65.setName("jLabel65"); // NOI18N
        PanelInput.add(jLabel65);
        jLabel65.setBounds(0, 553, 560, 23);

        chkRiwAlergiMak.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiMak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiMak.setText("Alergi Makanan");
        chkRiwAlergiMak.setBorderPainted(true);
        chkRiwAlergiMak.setBorderPaintedFlat(true);
        chkRiwAlergiMak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiMak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiMak.setName("chkRiwAlergiMak"); // NOI18N
        chkRiwAlergiMak.setOpaque(false);
        chkRiwAlergiMak.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiMak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiMakActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiMak);
        chkRiwAlergiMak.setBounds(650, 553, 100, 23);

        TketRiwAlergiMak.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiMak.setName("TketRiwAlergiMak"); // NOI18N
        TketRiwAlergiMak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiMakKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiMak);
        TketRiwAlergiMak.setBounds(755, 553, 370, 23);

        kesimpulanGizi.setEditable(false);
        kesimpulanGizi.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kesimpulan Skrining Gizi : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        kesimpulanGizi.setColumns(20);
        kesimpulanGizi.setRows(5);
        kesimpulanGizi.setName("kesimpulanGizi"); // NOI18N
        PanelInput.add(kesimpulanGizi);
        kesimpulanGizi.setBounds(38, 581, 350, 50);

        cmbGizi2.setForeground(new java.awt.Color(0, 0, 0));
        cmbGizi2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tidak", "Ya" }));
        cmbGizi2.setName("cmbGizi2"); // NOI18N
        cmbGizi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGizi2ActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGizi2);
        cmbGizi2.setBounds(515, 581, 65, 23);

        skorGizi2.setEditable(false);
        skorGizi2.setForeground(new java.awt.Color(0, 0, 0));
        skorGizi2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        skorGizi2.setText("0");
        skorGizi2.setFocusTraversalPolicyProvider(true);
        skorGizi2.setName("skorGizi2"); // NOI18N
        PanelInput.add(skorGizi2);
        skorGizi2.setBounds(590, 581, 40, 23);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("Reaksi Alergi Makanan :");
        jLabel83.setName("jLabel83"); // NOI18N
        PanelInput.add(jLabel83);
        jLabel83.setBounds(650, 581, 130, 23);

        TreakRiwAlergiMak.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiMak.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiMak.setName("TreakRiwAlergiMak"); // NOI18N
        TreakRiwAlergiMak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiMakKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiMak);
        TreakRiwAlergiMak.setBounds(780, 581, 345, 23);

        jLabel74.setForeground(new java.awt.Color(0, 0, 0));
        jLabel74.setText("Total Skor :");
        jLabel74.setName("jLabel74"); // NOI18N
        PanelInput.add(jLabel74);
        jLabel74.setBounds(510, 609, 70, 23);

        TotSkorGizi.setEditable(false);
        TotSkorGizi.setForeground(new java.awt.Color(0, 0, 0));
        TotSkorGizi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TotSkorGizi.setText("0");
        TotSkorGizi.setFocusTraversalPolicyProvider(true);
        TotSkorGizi.setName("TotSkorGizi"); // NOI18N
        PanelInput.add(TotSkorGizi);
        TotSkorGizi.setBounds(590, 609, 40, 23);

        chkRiwAlergiLain.setBackground(new java.awt.Color(255, 255, 250));
        chkRiwAlergiLain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        chkRiwAlergiLain.setText("Alergi Lainnya");
        chkRiwAlergiLain.setBorderPainted(true);
        chkRiwAlergiLain.setBorderPaintedFlat(true);
        chkRiwAlergiLain.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRiwAlergiLain.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRiwAlergiLain.setName("chkRiwAlergiLain"); // NOI18N
        chkRiwAlergiLain.setOpaque(false);
        chkRiwAlergiLain.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRiwAlergiLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRiwAlergiLainActionPerformed(evt);
            }
        });
        PanelInput.add(chkRiwAlergiLain);
        chkRiwAlergiLain.setBounds(650, 609, 95, 23);

        TketRiwAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TketRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TketRiwAlergiLain.setName("TketRiwAlergiLain"); // NOI18N
        TketRiwAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketRiwAlergiLainKeyPressed(evt);
            }
        });
        PanelInput.add(TketRiwAlergiLain);
        TketRiwAlergiLain.setBounds(750, 609, 375, 23);

        jLabel84.setForeground(new java.awt.Color(0, 0, 0));
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Reaksi Alergi Lain :");
        jLabel84.setName("jLabel84"); // NOI18N
        PanelInput.add(jLabel84);
        jLabel84.setBounds(650, 637, 100, 23);

        TreakRiwAlergiLain.setBackground(new java.awt.Color(245, 250, 240));
        TreakRiwAlergiLain.setForeground(new java.awt.Color(0, 0, 0));
        TreakRiwAlergiLain.setName("TreakRiwAlergiLain"); // NOI18N
        TreakRiwAlergiLain.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TreakRiwAlergiLainKeyPressed(evt);
            }
        });
        PanelInput.add(TreakRiwAlergiLain);
        TreakRiwAlergiLain.setBounds(750, 637, 375, 23);

        chkGelang.setBackground(new java.awt.Color(255, 255, 250));
        chkGelang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkGelang.setForeground(new java.awt.Color(0, 0, 0));
        chkGelang.setText("Gelang Tanda Alergi Dipasang (Warna Merah)");
        chkGelang.setBorderPainted(true);
        chkGelang.setBorderPaintedFlat(true);
        chkGelang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkGelang.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkGelang.setName("chkGelang"); // NOI18N
        chkGelang.setOpaque(false);
        chkGelang.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkGelang);
        chkGelang.setBounds(650, 665, 250, 23);

        jLabel85.setForeground(new java.awt.Color(0, 0, 0));
        jLabel85.setText("Alergi Diberitahukan Kepada :");
        jLabel85.setName("jLabel85"); // NOI18N
        PanelInput.add(jLabel85);
        jLabel85.setBounds(650, 693, 160, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("FUNGSIONAL");
        jLabel131.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel131.setName("jLabel131"); // NOI18N
        PanelInput.add(jLabel131);
        jLabel131.setBounds(0, 693, 110, 23);

        chkDokter.setBackground(new java.awt.Color(255, 255, 250));
        chkDokter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDokter.setForeground(new java.awt.Color(0, 0, 0));
        chkDokter.setText("Dokter");
        chkDokter.setBorderPainted(true);
        chkDokter.setBorderPaintedFlat(true);
        chkDokter.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDokter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDokter.setName("chkDokter"); // NOI18N
        chkDokter.setOpaque(false);
        chkDokter.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkDokter);
        chkDokter.setBounds(820, 693, 60, 23);

        chkFarmasis.setBackground(new java.awt.Color(255, 255, 250));
        chkFarmasis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFarmasis.setForeground(new java.awt.Color(0, 0, 0));
        chkFarmasis.setText("Farmasis / Apoteker");
        chkFarmasis.setBorderPainted(true);
        chkFarmasis.setBorderPaintedFlat(true);
        chkFarmasis.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFarmasis.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFarmasis.setName("chkFarmasis"); // NOI18N
        chkFarmasis.setOpaque(false);
        chkFarmasis.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkFarmasis);
        chkFarmasis.setBounds(890, 693, 125, 23);

        chkAhliGz.setBackground(new java.awt.Color(255, 255, 250));
        chkAhliGz.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAhliGz.setForeground(new java.awt.Color(0, 0, 0));
        chkAhliGz.setText("Ahli Gizi");
        chkAhliGz.setBorderPainted(true);
        chkAhliGz.setBorderPaintedFlat(true);
        chkAhliGz.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAhliGz.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAhliGz.setName("chkAhliGz"); // NOI18N
        chkAhliGz.setOpaque(false);
        chkAhliGz.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkAhliGz);
        chkAhliGz.setBounds(1025, 693, 70, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("1. Alat Bantu :");
        jLabel86.setName("jLabel86"); // NOI18N
        PanelInput.add(jLabel86);
        jLabel86.setBounds(0, 721, 120, 23);

        TalatBantu.setForeground(new java.awt.Color(0, 0, 0));
        TalatBantu.setName("TalatBantu"); // NOI18N
        TalatBantu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalatBantuKeyPressed(evt);
            }
        });
        PanelInput.add(TalatBantu);
        TalatBantu.setBounds(125, 721, 530, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("2. Prothesis :");
        jLabel87.setName("jLabel87"); // NOI18N
        PanelInput.add(jLabel87);
        jLabel87.setBounds(0, 749, 120, 23);

        Tprotesis.setForeground(new java.awt.Color(0, 0, 0));
        Tprotesis.setName("Tprotesis"); // NOI18N
        Tprotesis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TprotesisKeyPressed(evt);
            }
        });
        PanelInput.add(Tprotesis);
        Tprotesis.setBounds(125, 749, 530, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("3. Cacat Tubuh :");
        jLabel88.setName("jLabel88"); // NOI18N
        PanelInput.add(jLabel88);
        jLabel88.setBounds(0, 777, 120, 23);

        TcacatTubuh.setForeground(new java.awt.Color(0, 0, 0));
        TcacatTubuh.setName("TcacatTubuh"); // NOI18N
        TcacatTubuh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcacatTubuhKeyPressed(evt);
            }
        });
        PanelInput.add(TcacatTubuh);
        TcacatTubuh.setBounds(125, 777, 530, 23);

        jLabel89.setForeground(new java.awt.Color(0, 0, 0));
        jLabel89.setText("4. ADL :");
        jLabel89.setName("jLabel89"); // NOI18N
        PanelInput.add(jLabel89);
        jLabel89.setBounds(0, 805, 120, 23);

        cmbAdl.setForeground(new java.awt.Color(0, 0, 0));
        cmbAdl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Mandiri", "Dibantu" }));
        cmbAdl.setName("cmbAdl"); // NOI18N
        PanelInput.add(cmbAdl);
        cmbAdl.setBounds(125, 805, 70, 23);

        jLabel90.setForeground(new java.awt.Color(0, 0, 0));
        jLabel90.setText("5. Riwayat Jatuh Dalam 3 Bulan Terakhir ? :");
        jLabel90.setName("jLabel90"); // NOI18N
        PanelInput.add(jLabel90);
        jLabel90.setBounds(195, 805, 230, 23);

        cmbRiwJatuh.setForeground(new java.awt.Color(0, 0, 0));
        cmbRiwJatuh.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbRiwJatuh.setName("cmbRiwJatuh"); // NOI18N
        PanelInput.add(cmbRiwJatuh);
        cmbRiwJatuh.setBounds(432, 805, 60, 23);

        label14.setForeground(new java.awt.Color(0, 0, 0));
        label14.setText("Nama Bidan :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label14);
        label14.setBounds(0, 833, 120, 23);

        TnmBidan1.setEditable(false);
        TnmBidan1.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan1.setName("TnmBidan1"); // NOI18N
        PanelInput.add(TnmBidan1);
        TnmBidan1.setBounds(125, 833, 430, 23);

        BtnBidan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBidan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan1.setMnemonic('2');
        BtnBidan1.setToolTipText("Alt+2");
        BtnBidan1.setName("BtnBidan1"); // NOI18N
        BtnBidan1.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidan1ActionPerformed(evt);
            }
        });
        PanelInput.add(BtnBidan1);
        BtnBidan1.setBounds(555, 833, 28, 23);

        chkSaya1.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya1.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya1.setText("Saya Sendiri");
        chkSaya1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya1.setName("chkSaya1"); // NOI18N
        chkSaya1.setOpaque(false);
        chkSaya1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya1ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya1);
        chkSaya1.setBounds(590, 833, 90, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("KEBUTUHAN KOMUNIKASI DAN EDUKASI");
        jLabel132.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel132.setName("jLabel132"); // NOI18N
        PanelInput.add(jLabel132);
        jLabel132.setBounds(0, 861, 250, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("Terdapat Hambatan Dalam Pembelajaran :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label15);
        label15.setBounds(0, 889, 230, 23);

        chkYaTerdapat.setBackground(new java.awt.Color(255, 255, 250));
        chkYaTerdapat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkYaTerdapat.setForeground(new java.awt.Color(0, 0, 0));
        chkYaTerdapat.setText("Ya, Jika Ya :");
        chkYaTerdapat.setBorderPainted(true);
        chkYaTerdapat.setBorderPaintedFlat(true);
        chkYaTerdapat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkYaTerdapat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkYaTerdapat.setName("chkYaTerdapat"); // NOI18N
        chkYaTerdapat.setOpaque(false);
        chkYaTerdapat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkYaTerdapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkYaTerdapatActionPerformed(evt);
            }
        });
        PanelInput.add(chkYaTerdapat);
        chkYaTerdapat.setBounds(237, 889, 85, 23);

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
        PanelInput.add(chkPendengaran);
        chkPendengaran.setBounds(330, 889, 90, 23);

        chkPenglihatan.setBackground(new java.awt.Color(255, 255, 250));
        chkPenglihatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkPenglihatan.setForeground(new java.awt.Color(0, 0, 0));
        chkPenglihatan.setText("Penglihatan");
        chkPenglihatan.setBorderPainted(true);
        chkPenglihatan.setBorderPaintedFlat(true);
        chkPenglihatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkPenglihatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkPenglihatan.setName("chkPenglihatan"); // NOI18N
        chkPenglihatan.setOpaque(false);
        chkPenglihatan.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkPenglihatan);
        chkPenglihatan.setBounds(430, 889, 85, 23);

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
        PanelInput.add(chkKognitif);
        chkKognitif.setBounds(523, 889, 65, 23);

        chkBudaya.setBackground(new java.awt.Color(255, 255, 250));
        chkBudaya.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkBudaya.setForeground(new java.awt.Color(0, 0, 0));
        chkBudaya.setText("Budaya");
        chkBudaya.setBorderPainted(true);
        chkBudaya.setBorderPaintedFlat(true);
        chkBudaya.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkBudaya.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkBudaya.setName("chkBudaya"); // NOI18N
        chkBudaya.setOpaque(false);
        chkBudaya.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkBudaya);
        chkBudaya.setBounds(660, 889, 65, 23);

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
        PanelInput.add(chkEmosi);
        chkEmosi.setBounds(735, 889, 55, 23);

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
        PanelInput.add(chkBahasa);
        chkBahasa.setBounds(237, 917, 65, 23);

        chkLainHambatan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainHambatan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainHambatan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainHambatan.setText("Lainnya");
        chkLainHambatan.setBorderPainted(true);
        chkLainHambatan.setBorderPaintedFlat(true);
        chkLainHambatan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainHambatan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainHambatan.setName("chkLainHambatan"); // NOI18N
        chkLainHambatan.setOpaque(false);
        chkLainHambatan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainHambatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainHambatanActionPerformed(evt);
            }
        });
        PanelInput.add(chkLainHambatan);
        chkLainHambatan.setBounds(312, 917, 65, 23);

        TketLainHambatan.setBackground(new java.awt.Color(245, 250, 240));
        TketLainHambatan.setForeground(new java.awt.Color(0, 0, 0));
        TketLainHambatan.setName("TketLainHambatan"); // NOI18N
        TketLainHambatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketLainHambatanKeyPressed(evt);
            }
        });
        PanelInput.add(TketLainHambatan);
        TketLainHambatan.setBounds(377, 917, 595, 23);

        label16.setForeground(new java.awt.Color(0, 0, 0));
        label16.setText("Dibutuhkan Penerjemah :");
        label16.setName("label16"); // NOI18N
        label16.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label16);
        label16.setBounds(0, 945, 230, 23);

        cmbDibutuhkan.setForeground(new java.awt.Color(0, 0, 0));
        cmbDibutuhkan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDibutuhkan.setName("cmbDibutuhkan"); // NOI18N
        cmbDibutuhkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDibutuhkanActionPerformed(evt);
            }
        });
        PanelInput.add(cmbDibutuhkan);
        cmbDibutuhkan.setBounds(237, 945, 60, 23);

        jLabel91.setForeground(new java.awt.Color(0, 0, 0));
        jLabel91.setText("Sebutkan :");
        jLabel91.setName("jLabel91"); // NOI18N
        PanelInput.add(jLabel91);
        jLabel91.setBounds(296, 945, 75, 23);

        Tsebutkan.setForeground(new java.awt.Color(0, 0, 0));
        Tsebutkan.setName("Tsebutkan"); // NOI18N
        Tsebutkan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsebutkanKeyPressed(evt);
            }
        });
        PanelInput.add(Tsebutkan);
        Tsebutkan.setBounds(377, 945, 300, 23);

        jLabel92.setForeground(new java.awt.Color(0, 0, 0));
        jLabel92.setText("Bahasa Isyarat :");
        jLabel92.setName("jLabel92"); // NOI18N
        PanelInput.add(jLabel92);
        jLabel92.setBounds(677, 945, 100, 23);

        cmbBahasa.setForeground(new java.awt.Color(0, 0, 0));
        cmbBahasa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbBahasa.setName("cmbBahasa"); // NOI18N
        PanelInput.add(cmbBahasa);
        cmbBahasa.setBounds(785, 945, 60, 23);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Kebutuhan Edukasi (Pilih Topik Edukasi Yang Tersedia) :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label17);
        label17.setBounds(0, 973, 300, 23);

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
        PanelInput.add(chkDiagnosa);
        chkDiagnosa.setBounds(305, 973, 195, 23);

        chkObatTerapi.setBackground(new java.awt.Color(255, 255, 250));
        chkObatTerapi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkObatTerapi.setForeground(new java.awt.Color(0, 0, 0));
        chkObatTerapi.setText("Obat-obatan / Terapi");
        chkObatTerapi.setBorderPainted(true);
        chkObatTerapi.setBorderPaintedFlat(true);
        chkObatTerapi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkObatTerapi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkObatTerapi.setName("chkObatTerapi"); // NOI18N
        chkObatTerapi.setOpaque(false);
        chkObatTerapi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkObatTerapi);
        chkObatTerapi.setBounds(510, 973, 130, 23);

        chkDietNutrisi.setBackground(new java.awt.Color(255, 255, 250));
        chkDietNutrisi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkDietNutrisi.setForeground(new java.awt.Color(0, 0, 0));
        chkDietNutrisi.setText("Diet dan Nutrisi");
        chkDietNutrisi.setBorderPainted(true);
        chkDietNutrisi.setBorderPaintedFlat(true);
        chkDietNutrisi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkDietNutrisi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkDietNutrisi.setName("chkDietNutrisi"); // NOI18N
        chkDietNutrisi.setOpaque(false);
        chkDietNutrisi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkDietNutrisi);
        chkDietNutrisi.setBounds(650, 973, 100, 23);

        chkRehabilitasi.setBackground(new java.awt.Color(255, 255, 250));
        chkRehabilitasi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRehabilitasi.setForeground(new java.awt.Color(0, 0, 0));
        chkRehabilitasi.setText("Rehabilitasi");
        chkRehabilitasi.setBorderPainted(true);
        chkRehabilitasi.setBorderPaintedFlat(true);
        chkRehabilitasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRehabilitasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRehabilitasi.setName("chkRehabilitasi"); // NOI18N
        chkRehabilitasi.setOpaque(false);
        chkRehabilitasi.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkRehabilitasi);
        chkRehabilitasi.setBounds(760, 973, 83, 23);

        chkManajemenNyeri.setBackground(new java.awt.Color(255, 255, 250));
        chkManajemenNyeri.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkManajemenNyeri.setForeground(new java.awt.Color(0, 0, 0));
        chkManajemenNyeri.setText("Manajemen Nyeri");
        chkManajemenNyeri.setBorderPainted(true);
        chkManajemenNyeri.setBorderPaintedFlat(true);
        chkManajemenNyeri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkManajemenNyeri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkManajemenNyeri.setName("chkManajemenNyeri"); // NOI18N
        chkManajemenNyeri.setOpaque(false);
        chkManajemenNyeri.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkManajemenNyeri);
        chkManajemenNyeri.setBounds(850, 973, 120, 23);

        chkTindakanKep.setBackground(new java.awt.Color(255, 255, 250));
        chkTindakanKep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTindakanKep.setForeground(new java.awt.Color(0, 0, 0));
        chkTindakanKep.setText("Tindakan Keperawatan");
        chkTindakanKep.setBorderPainted(true);
        chkTindakanKep.setBorderPaintedFlat(true);
        chkTindakanKep.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTindakanKep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTindakanKep.setName("chkTindakanKep"); // NOI18N
        chkTindakanKep.setOpaque(false);
        chkTindakanKep.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTindakanKep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTindakanKepActionPerformed(evt);
            }
        });
        PanelInput.add(chkTindakanKep);
        chkTindakanKep.setBounds(305, 1001, 140, 23);

        TtindakanKep.setForeground(new java.awt.Color(0, 0, 0));
        TtindakanKep.setName("TtindakanKep"); // NOI18N
        TtindakanKep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtindakanKepKeyPressed(evt);
            }
        });
        PanelInput.add(TtindakanKep);
        TtindakanKep.setBounds(445, 1001, 530, 23);

        chkLainKebutuhan.setBackground(new java.awt.Color(255, 255, 250));
        chkLainKebutuhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkLainKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        chkLainKebutuhan.setText("Lain-lain, Sebutkan :");
        chkLainKebutuhan.setBorderPainted(true);
        chkLainKebutuhan.setBorderPaintedFlat(true);
        chkLainKebutuhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkLainKebutuhan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkLainKebutuhan.setName("chkLainKebutuhan"); // NOI18N
        chkLainKebutuhan.setOpaque(false);
        chkLainKebutuhan.setPreferredSize(new java.awt.Dimension(175, 23));
        chkLainKebutuhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkLainKebutuhanActionPerformed(evt);
            }
        });
        PanelInput.add(chkLainKebutuhan);
        chkLainKebutuhan.setBounds(305, 1029, 125, 23);

        TlainKebutuhan.setForeground(new java.awt.Color(0, 0, 0));
        TlainKebutuhan.setName("TlainKebutuhan"); // NOI18N
        TlainKebutuhan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlainKebutuhanKeyPressed(evt);
            }
        });
        PanelInput.add(TlainKebutuhan);
        TlainKebutuhan.setBounds(430, 1029, 545, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("EDUKASI PASIEN");
        jLabel133.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel133.setName("jLabel133"); // NOI18N
        PanelInput.add(jLabel133);
        jLabel133.setBounds(0, 1057, 130, 23);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setText("Edukasi Awal Disampaikan Tentang Diagnosis, Rencana, Dan Tujuan Terapi Kepada :");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label18);
        label18.setBounds(0, 1085, 440, 23);

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
        PanelInput.add(chkPasien);
        chkPasien.setBounds(445, 1085, 60, 23);

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
        PanelInput.add(chkKlgPasien);
        chkKlgPasien.setBounds(515, 1085, 140, 23);

        TnmKlgPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmKlgPasien.setName("TnmKlgPasien"); // NOI18N
        TnmKlgPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKlgPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TnmKlgPasien);
        TnmKlgPasien.setBounds(660, 1085, 315, 23);

        chkTidakDapat.setBackground(new java.awt.Color(255, 255, 250));
        chkTidakDapat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkTidakDapat.setForeground(new java.awt.Color(0, 0, 0));
        chkTidakDapat.setText("Tidak Dapat Memberikan Edukasi Kepada Pasien Atau Keluarga, Karena :");
        chkTidakDapat.setBorderPainted(true);
        chkTidakDapat.setBorderPaintedFlat(true);
        chkTidakDapat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkTidakDapat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkTidakDapat.setName("chkTidakDapat"); // NOI18N
        chkTidakDapat.setOpaque(false);
        chkTidakDapat.setPreferredSize(new java.awt.Dimension(175, 23));
        chkTidakDapat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTidakDapatActionPerformed(evt);
            }
        });
        PanelInput.add(chkTidakDapat);
        chkTidakDapat.setBounds(445, 1113, 375, 23);

        TtidakDapat.setForeground(new java.awt.Color(0, 0, 0));
        TtidakDapat.setName("TtidakDapat"); // NOI18N
        TtidakDapat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtidakDapatKeyPressed(evt);
            }
        });
        PanelInput.add(TtidakDapat);
        TtidakDapat.setBounds(445, 1141, 685, 23);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setText("Tgl. Edukasi :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label19);
        label19.setBounds(0, 1169, 120, 23);

        TtglEdukasi.setEditable(false);
        TtglEdukasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2025" }));
        TtglEdukasi.setDisplayFormat("dd-MM-yyyy");
        TtglEdukasi.setName("TtglEdukasi"); // NOI18N
        TtglEdukasi.setOpaque(false);
        TtglEdukasi.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglEdukasi);
        TtglEdukasi.setBounds(125, 1169, 90, 23);

        label20.setForeground(new java.awt.Color(0, 0, 0));
        label20.setText("Jam Edukasi :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label20);
        label20.setBounds(215, 1169, 80, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        PanelInput.add(cmbJam);
        cmbJam.setBounds(300, 1169, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        PanelInput.add(cmbMnt);
        cmbMnt.setBounds(352, 1169, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        PanelInput.add(cmbDtk);
        cmbDtk.setBounds(404, 1169, 45, 23);

        jLabel368.setForeground(new java.awt.Color(0, 0, 0));
        jLabel368.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel368.setText("Wita       Nama Dokter :");
        jLabel368.setName("jLabel368"); // NOI18N
        PanelInput.add(jLabel368);
        jLabel368.setBounds(456, 1169, 118, 23);

        TnmDokter.setEditable(false);
        TnmDokter.setForeground(new java.awt.Color(0, 0, 0));
        TnmDokter.setName("TnmDokter"); // NOI18N
        PanelInput.add(TnmDokter);
        TnmDokter.setBounds(575, 1169, 420, 23);

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
        PanelInput.add(BtnDokter);
        BtnDokter.setBounds(1000, 1169, 28, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("LEMBAR DISCHARGE PLANNING");
        jLabel134.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel134.setName("jLabel134"); // NOI18N
        PanelInput.add(jLabel134);
        jLabel134.setBounds(0, 1197, 210, 23);

        label21.setForeground(new java.awt.Color(0, 0, 0));
        label21.setText("Identifikasi, Seleksi / Skrining Pasien :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label21);
        label21.setBounds(0, 1225, 220, 23);

        chkIdentifikai1.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai1.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai1.setText("Pasien dengan keterbatasan kognitif, ketergantungan ADL tinggi.");
        chkIdentifikai1.setBorderPainted(true);
        chkIdentifikai1.setBorderPaintedFlat(true);
        chkIdentifikai1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai1.setName("chkIdentifikai1"); // NOI18N
        chkIdentifikai1.setOpaque(false);
        chkIdentifikai1.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai1);
        chkIdentifikai1.setBounds(228, 1225, 350, 23);

        chkIdentifikai6.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai6.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai6.setText("Sering masuk IGD, readmisi RS");
        chkIdentifikai6.setBorderPainted(true);
        chkIdentifikai6.setBorderPaintedFlat(true);
        chkIdentifikai6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai6.setName("chkIdentifikai6"); // NOI18N
        chkIdentifikai6.setOpaque(false);
        chkIdentifikai6.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai6);
        chkIdentifikai6.setBounds(640, 1225, 180, 23);

        chkIdentifikai2.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai2.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai2.setText("Wanita usia rentan (Ibu hamil, Ibu menyusui, Lansia)");
        chkIdentifikai2.setBorderPainted(true);
        chkIdentifikai2.setBorderPaintedFlat(true);
        chkIdentifikai2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai2.setName("chkIdentifikai2"); // NOI18N
        chkIdentifikai2.setOpaque(false);
        chkIdentifikai2.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai2);
        chkIdentifikai2.setBounds(228, 1253, 280, 23);

        chkIdentifikai7.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai7.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai7.setText("Perkiraan asuhan dengan biaya tinggi");
        chkIdentifikai7.setBorderPainted(true);
        chkIdentifikai7.setBorderPaintedFlat(true);
        chkIdentifikai7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai7.setName("chkIdentifikai7"); // NOI18N
        chkIdentifikai7.setOpaque(false);
        chkIdentifikai7.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai7);
        chkIdentifikai7.setBounds(640, 1253, 210, 23);

        chkIdentifikai3.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai3.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai3.setText("Pasien dengan resiko tinggi (Infeksi kejang, Penurunan kesadaran)");
        chkIdentifikai3.setBorderPainted(true);
        chkIdentifikai3.setBorderPaintedFlat(true);
        chkIdentifikai3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai3.setName("chkIdentifikai3"); // NOI18N
        chkIdentifikai3.setOpaque(false);
        chkIdentifikai3.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai3);
        chkIdentifikai3.setBounds(228, 1281, 350, 23);

        chkIdentifikai8.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai8.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai8.setText("Pasien tanpa keluarga / terlantar, tinggal sendiri");
        chkIdentifikai8.setBorderPainted(true);
        chkIdentifikai8.setBorderPaintedFlat(true);
        chkIdentifikai8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai8.setName("chkIdentifikai8"); // NOI18N
        chkIdentifikai8.setOpaque(false);
        chkIdentifikai8.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai8);
        chkIdentifikai8.setBounds(640, 1281, 260, 23);

        chkIdentifikai4.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai4.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai4.setText("Potensi komplain tinggi");
        chkIdentifikai4.setBorderPainted(true);
        chkIdentifikai4.setBorderPaintedFlat(true);
        chkIdentifikai4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai4.setName("chkIdentifikai4"); // NOI18N
        chkIdentifikai4.setOpaque(false);
        chkIdentifikai4.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai4);
        chkIdentifikai4.setBounds(228, 1309, 140, 23);

        chkIdentifikai9.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai9.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai9.setText("Kasus yang melebihi rata-rata lama dirawat");
        chkIdentifikai9.setBorderPainted(true);
        chkIdentifikai9.setBorderPaintedFlat(true);
        chkIdentifikai9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai9.setName("chkIdentifikai9"); // NOI18N
        chkIdentifikai9.setOpaque(false);
        chkIdentifikai9.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai9);
        chkIdentifikai9.setBounds(640, 1309, 240, 23);

        chkIdentifikai5.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai5.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai5.setText("Pasien dengan penyakit kronis, katastropik (Penyakit Degenerative) terminal");
        chkIdentifikai5.setBorderPainted(true);
        chkIdentifikai5.setBorderPaintedFlat(true);
        chkIdentifikai5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai5.setName("chkIdentifikai5"); // NOI18N
        chkIdentifikai5.setOpaque(false);
        chkIdentifikai5.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai5);
        chkIdentifikai5.setBounds(228, 1337, 400, 23);

        chkIdentifikai10.setBackground(new java.awt.Color(255, 255, 250));
        chkIdentifikai10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkIdentifikai10.setForeground(new java.awt.Color(0, 0, 0));
        chkIdentifikai10.setText("Kasus yang membutuhkan kontinuitas pelayanan, rencana pemulangan penting / beresiko");
        chkIdentifikai10.setBorderPainted(true);
        chkIdentifikai10.setBorderPaintedFlat(true);
        chkIdentifikai10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkIdentifikai10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkIdentifikai10.setName("chkIdentifikai10"); // NOI18N
        chkIdentifikai10.setOpaque(false);
        chkIdentifikai10.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkIdentifikai10);
        chkIdentifikai10.setBounds(640, 1337, 460, 23);

        label22.setForeground(new java.awt.Color(0, 0, 0));
        label22.setText("Memerlukan :");
        label22.setName("label22"); // NOI18N
        label22.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label22);
        label22.setBounds(0, 1365, 220, 23);

        Tmemerlukan.setForeground(new java.awt.Color(0, 0, 0));
        Tmemerlukan.setName("Tmemerlukan"); // NOI18N
        Tmemerlukan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TmemerlukanKeyPressed(evt);
            }
        });
        PanelInput.add(Tmemerlukan);
        Tmemerlukan.setBounds(228, 1365, 685, 23);

        label23.setForeground(new java.awt.Color(0, 0, 0));
        label23.setText("Manajer Pelayanan Pasien :");
        label23.setName("label23"); // NOI18N
        label23.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label23);
        label23.setBounds(0, 1393, 220, 23);

        cmbMPP.setForeground(new java.awt.Color(0, 0, 0));
        cmbMPP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbMPP.setName("cmbMPP"); // NOI18N
        PanelInput.add(cmbMPP);
        cmbMPP.setBounds(228, 1393, 60, 23);

        label24.setForeground(new java.awt.Color(0, 0, 0));
        label24.setText("Discharge Planning :");
        label24.setName("label24"); // NOI18N
        label24.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label24);
        label24.setBounds(290, 1393, 120, 23);

        cmbDP.setForeground(new java.awt.Color(0, 0, 0));
        cmbDP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Ya", "Tidak" }));
        cmbDP.setName("cmbDP"); // NOI18N
        PanelInput.add(cmbDP);
        cmbDP.setBounds(420, 1393, 60, 23);

        label25.setForeground(new java.awt.Color(0, 0, 0));
        label25.setText("Nama Pasien / Keluarga Pasien :");
        label25.setName("label25"); // NOI18N
        label25.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label25);
        label25.setBounds(0, 1421, 220, 23);

        TnmKeluargaPasien.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluargaPasien.setName("TnmKeluargaPasien"); // NOI18N
        TnmKeluargaPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaPasienKeyPressed(evt);
            }
        });
        PanelInput.add(TnmKeluargaPasien);
        TnmKeluargaPasien.setBounds(228, 1421, 330, 23);

        label26.setForeground(new java.awt.Color(0, 0, 0));
        label26.setText("Nama Bidan :");
        label26.setName("label26"); // NOI18N
        label26.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label26);
        label26.setBounds(560, 1421, 80, 23);

        TnmBidan2.setEditable(false);
        TnmBidan2.setForeground(new java.awt.Color(0, 0, 0));
        TnmBidan2.setName("TnmBidan2"); // NOI18N
        PanelInput.add(TnmBidan2);
        TnmBidan2.setBounds(646, 1421, 350, 23);

        BtnBidan2.setForeground(new java.awt.Color(0, 0, 0));
        BtnBidan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnBidan2.setMnemonic('2');
        BtnBidan2.setToolTipText("Alt+2");
        BtnBidan2.setName("BtnBidan2"); // NOI18N
        BtnBidan2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnBidan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBidan2ActionPerformed(evt);
            }
        });
        PanelInput.add(BtnBidan2);
        BtnBidan2.setBounds(1000, 1421, 28, 23);

        chkSaya2.setBackground(new java.awt.Color(242, 242, 242));
        chkSaya2.setForeground(new java.awt.Color(0, 0, 0));
        chkSaya2.setText("Saya Sendiri");
        chkSaya2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSaya2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSaya2.setName("chkSaya2"); // NOI18N
        chkSaya2.setOpaque(false);
        chkSaya2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkSaya2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSaya2ActionPerformed(evt);
            }
        });
        PanelInput.add(chkSaya2);
        chkSaya2.setBounds(1035, 1421, 90, 23);

        chkFisik.setBackground(new java.awt.Color(255, 255, 250));
        chkFisik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkFisik.setForeground(new java.awt.Color(0, 0, 0));
        chkFisik.setText("Fisik");
        chkFisik.setBorderPainted(true);
        chkFisik.setBorderPaintedFlat(true);
        chkFisik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkFisik.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkFisik.setName("chkFisik"); // NOI18N
        chkFisik.setOpaque(false);
        chkFisik.setPreferredSize(new java.awt.Dimension(175, 23));
        PanelInput.add(chkFisik);
        chkFisik.setBounds(598, 889, 50, 23);

        label27.setForeground(new java.awt.Color(0, 0, 0));
        label27.setText("Tanggal :");
        label27.setName("label27"); // NOI18N
        label27.setPreferredSize(new java.awt.Dimension(60, 23));
        PanelInput.add(label27);
        label27.setBounds(480, 1393, 60, 23);

        TtglDp.setEditable(false);
        TtglDp.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "22-06-2025" }));
        TtglDp.setDisplayFormat("dd-MM-yyyy");
        TtglDp.setName("TtglDp"); // NOI18N
        TtglDp.setOpaque(false);
        TtglDp.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglDp);
        TtglDp.setBounds(545, 1393, 90, 23);

        scrollInput.setViewportView(PanelInput);

        internalFrame1.add(scrollInput, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNyeriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNyeriActionPerformed
        Tlokasi.setText("");
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
            Tlokasi.requestFocus();
        } else {
            Tlokasi.setEnabled(false);
        }
    }//GEN-LAST:event_cmbNyeriActionPerformed

    private void TlokasiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlokasiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbJenis.requestFocus();
        }
    }//GEN-LAST:event_TlokasiKeyPressed

    private void cmbProvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProvoActionPerformed
        Tprovo.setText("");
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
            Tprovo.requestFocus();
        } else {
            Tprovo.setEnabled(false);
        }
    }//GEN-LAST:event_cmbProvoActionPerformed

    private void TprovoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprovoKeyPressed
        Valid.pindah(evt, cmbProvo, cmbQuality);
    }//GEN-LAST:event_TprovoKeyPressed

    private void cmbQualityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbQualityActionPerformed
        Tquality.setText("");
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
            Tquality.requestFocus();
        } else {
            Tquality.setEnabled(false);
        }
    }//GEN-LAST:event_cmbQualityActionPerformed

    private void TqualityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TqualityKeyPressed
        Valid.pindah(evt, cmbQuality, cmbRadia);
    }//GEN-LAST:event_TqualityKeyPressed

    private void cmbTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTimeActionPerformed
        cmbLama.setSelectedIndex(0);
        if (cmbTime.getSelectedIndex() == 0) {
            cmbLama.setEnabled(false);
        } else {
            cmbLama.setEnabled(true);
            cmbLama.requestFocus();
        }
    }//GEN-LAST:event_cmbTimeActionPerformed

    private void cmbGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi1ActionPerformed
        cmbYaGizi1.setSelectedIndex(0);
        skorYaGizi1.setText("0");

        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGizi1.setText("2");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(true);
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi1ActionPerformed

    private void chkRiwAlergiObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiObatActionPerformed
        TketRiwAlergiObat.setText("");
        TreakRiwAlergiObat.setText("");
        if (chkRiwAlergiObat.isSelected() == true) {
            TketRiwAlergiObat.setEnabled(true);
            TreakRiwAlergiObat.setEnabled(true);
            TketRiwAlergiObat.requestFocus();
        } else {
            TketRiwAlergiObat.setEnabled(false);
            TreakRiwAlergiObat.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiObatActionPerformed

    private void TketRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiObat.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiObatKeyPressed

    private void cmbYaGizi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYaGizi1ActionPerformed
        if (cmbYaGizi1.getSelectedIndex() == 0) {
            skorYaGizi1.setText("0");
        } else if (cmbYaGizi1.getSelectedIndex() == 1) {
            skorYaGizi1.setText("1");
        } else if (cmbYaGizi1.getSelectedIndex() == 2) {
            skorYaGizi1.setText("2");
        } else if (cmbYaGizi1.getSelectedIndex() == 3) {
            skorYaGizi1.setText("3");
        } else if (cmbYaGizi1.getSelectedIndex() == 4) {
            skorYaGizi1.setText("4");
        } else if (cmbYaGizi1.getSelectedIndex() == 5) {
            skorYaGizi1.setText("2");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbYaGizi1ActionPerformed

    private void TreakRiwAlergiObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiObatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRiwAlergiMak.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiObatKeyPressed

    private void chkRiwAlergiMakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiMakActionPerformed
        TketRiwAlergiMak.setText("");
        TreakRiwAlergiMak.setText("");
        if (chkRiwAlergiMak.isSelected() == true) {
            TketRiwAlergiMak.setEnabled(true);
            TreakRiwAlergiMak.setEnabled(true);
            TketRiwAlergiMak.requestFocus();
        } else {
            TketRiwAlergiMak.setEnabled(false);
            TreakRiwAlergiMak.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiMakActionPerformed

    private void TketRiwAlergiMakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiMakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiMak.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiMakKeyPressed

    private void cmbGizi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGizi2ActionPerformed
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGizi2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGizi2.setText("1");
        }
        hitungSkorGizi();
    }//GEN-LAST:event_cmbGizi2ActionPerformed

    private void TreakRiwAlergiMakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiMakKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRiwAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiMakKeyPressed

    private void chkRiwAlergiLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRiwAlergiLainActionPerformed
        TketRiwAlergiLain.setText("");
        TreakRiwAlergiLain.setText("");
        if (chkRiwAlergiLain.isSelected() == true) {
            TketRiwAlergiLain.setEnabled(true);
            TreakRiwAlergiLain.setEnabled(true);
            TketRiwAlergiLain.requestFocus();
        } else {
            TketRiwAlergiLain.setEnabled(false);
            TreakRiwAlergiLain.setEnabled(false);
        }
    }//GEN-LAST:event_chkRiwAlergiLainActionPerformed

    private void TketRiwAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketRiwAlergiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TreakRiwAlergiLain.requestFocus();
        }
    }//GEN-LAST:event_TketRiwAlergiLainKeyPressed

    private void TreakRiwAlergiLainKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TreakRiwAlergiLainKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkGelang.requestFocus();
        }
    }//GEN-LAST:event_TreakRiwAlergiLainKeyPressed

    private void TalatBantuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalatBantuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tprotesis.requestFocus();
        }
    }//GEN-LAST:event_TalatBantuKeyPressed

    private void TprotesisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TprotesisKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TcacatTubuh.requestFocus();
        }
    }//GEN-LAST:event_TprotesisKeyPressed

    private void TcacatTubuhKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcacatTubuhKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbAdl.requestFocus();
        }
    }//GEN-LAST:event_TcacatTubuhKeyPressed

    private void BtnBidan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidan1ActionPerformed
        pilihan = 0;
        pilihan = 1;
        akses.setform("RMAsesmenAwalKebidanan2");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnBidan1ActionPerformed

    private void chkSaya1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya1ActionPerformed
        if (chkSaya1.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipBidan1 = "-";
                TnmBidan1.setText("-");
            } else {
                nipBidan1 = akses.getkode();
                TnmBidan1.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan1 + "'"));
            }
        } else {
            nipBidan1 = "-";
            TnmBidan1.setText("-");
        }
    }//GEN-LAST:event_chkSaya1ActionPerformed

    private void chkYaTerdapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkYaTerdapatActionPerformed
        chkPendengaran.setSelected(false);
        chkPenglihatan.setSelected(false);
        chkKognitif.setSelected(false);
        chkBudaya.setSelected(false);
        chkEmosi.setSelected(false);
        chkBahasa.setSelected(false);
        chkLainHambatan.setSelected(false);
        TketLainHambatan.setText("");
        if (chkYaTerdapat.isSelected() == true) {
            chkPendengaran.setEnabled(true);
            chkPenglihatan.setEnabled(true);
            chkKognitif.setEnabled(true);
            chkBudaya.setEnabled(true);
            chkEmosi.setEnabled(true);
            chkBahasa.setEnabled(true);
            chkLainHambatan.setEnabled(true);
            TketLainHambatan.setEnabled(false);
            chkPendengaran.requestFocus();
        } else {
            chkPendengaran.setEnabled(false);
            chkPenglihatan.setEnabled(false);
            chkKognitif.setEnabled(false);
            chkBudaya.setEnabled(false);
            chkEmosi.setEnabled(false);
            chkBahasa.setEnabled(false);
            chkLainHambatan.setEnabled(false);
            TketLainHambatan.setEnabled(false);
        }
    }//GEN-LAST:event_chkYaTerdapatActionPerformed

    private void chkLainHambatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainHambatanActionPerformed
        TketLainHambatan.setText("");
        if (chkLainHambatan.isSelected() == true) {
            TketLainHambatan.setEnabled(true);
            TketLainHambatan.requestFocus();
        } else {
            TketLainHambatan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainHambatanActionPerformed

    private void TketLainHambatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketLainHambatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbDibutuhkan.requestFocus();
        }
    }//GEN-LAST:event_TketLainHambatanKeyPressed

    private void cmbDibutuhkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDibutuhkanActionPerformed
        Tsebutkan.setText("");
        if (cmbDibutuhkan.getSelectedIndex() == 1) {
            Tsebutkan.setEnabled(true);
            Tsebutkan.requestFocus();
        } else {
            Tsebutkan.setEnabled(false);
        }
    }//GEN-LAST:event_cmbDibutuhkanActionPerformed

    private void TsebutkanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsebutkanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbBahasa.requestFocus();
        }
    }//GEN-LAST:event_TsebutkanKeyPressed

    private void chkTindakanKepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTindakanKepActionPerformed
        TtindakanKep.setText("");
        if (chkTindakanKep.isSelected() == true) {
            TtindakanKep.setEnabled(true);
            TtindakanKep.requestFocus();
        } else {
            TtindakanKep.setEnabled(false);
        }
    }//GEN-LAST:event_chkTindakanKepActionPerformed

    private void TtindakanKepKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtindakanKepKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkLainKebutuhan.requestFocus();
        }
    }//GEN-LAST:event_TtindakanKepKeyPressed

    private void chkLainKebutuhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkLainKebutuhanActionPerformed
        TlainKebutuhan.setText("");
        if (chkLainKebutuhan.isSelected() == true) {
            TlainKebutuhan.setEnabled(true);
            TlainKebutuhan.requestFocus();
        } else {
            TlainKebutuhan.setEnabled(false);
        }
    }//GEN-LAST:event_chkLainKebutuhanActionPerformed

    private void TlainKebutuhanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlainKebutuhanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkPasien.requestFocus();
        }
    }//GEN-LAST:event_TlainKebutuhanKeyPressed

    private void chkKlgPasienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkKlgPasienActionPerformed
        TnmKlgPasien.setText("");
        if (chkKlgPasien.isSelected() == true) {
            TnmKlgPasien.setEnabled(true);
            TnmKlgPasien.requestFocus();
        } else {
            TnmKlgPasien.setEnabled(false);
        }
    }//GEN-LAST:event_chkKlgPasienActionPerformed

    private void TnmKlgPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKlgPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkTidakDapat.requestFocus();
        }
    }//GEN-LAST:event_TnmKlgPasienKeyPressed

    private void chkTidakDapatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTidakDapatActionPerformed
        TtidakDapat.setText("");
        if (chkTidakDapat.isSelected() == true) {
            TtidakDapat.setEnabled(true);
            TtidakDapat.requestFocus();
        } else {
            TtidakDapat.setEnabled(false);
        }
    }//GEN-LAST:event_chkTidakDapatActionPerformed

    private void TtidakDapatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtidakDapatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglEdukasi.requestFocus();
        }
    }//GEN-LAST:event_TtidakDapatKeyPressed

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
        akses.setform("RMAsesmenAwalKebidanan2");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void TmemerlukanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TmemerlukanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbMPP.requestFocus();
        }
    }//GEN-LAST:event_TmemerlukanKeyPressed

    private void TnmKeluargaPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaPasienKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnBidan2.requestFocus();
        }
    }//GEN-LAST:event_TnmKeluargaPasienKeyPressed

    private void BtnBidan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBidan2ActionPerformed
        pilihan = 0;
        pilihan = 2;
        akses.setform("RMAsesmenAwalKebidanan2");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);;
    }//GEN-LAST:event_BtnBidan2ActionPerformed

    private void chkSaya2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSaya2ActionPerformed
        if (chkSaya2.isSelected() == true) {
            if (akses.getadmin() == true) {
                nipBidan2 = "-";
                TnmBidan2.setText("-");
            } else {
                nipBidan2 = akses.getkode();
                TnmBidan2.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipBidan2 + "'"));
            }
        } else {
            nipBidan2 = "-";
            TnmBidan2.setText("-");
        }
    }//GEN-LAST:event_chkSaya2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenAwalKebidanan2 dialog = new RMAsesmenAwalKebidanan2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBidan1;
    private widget.Button BtnBidan2;
    private widget.Button BtnDokter;
    private javax.swing.JPanel PanelInput;
    private usu.widget.glass.PanelGlass PanelWall;
    private widget.TextBox TalatBantu;
    private widget.TextBox TcacatTubuh;
    private widget.TextBox TketLainHambatan;
    private widget.TextBox TketRiwAlergiLain;
    private widget.TextBox TketRiwAlergiMak;
    private widget.TextBox TketRiwAlergiObat;
    private widget.TextBox TlainKebutuhan;
    private widget.TextBox Tlokasi;
    private widget.TextBox Tmemerlukan;
    private widget.TextBox TnmBidan1;
    private widget.TextBox TnmBidan2;
    private widget.TextBox TnmDokter;
    private widget.TextBox TnmKeluargaPasien;
    private widget.TextBox TnmKlgPasien;
    private widget.TextBox TotSkorGizi;
    private widget.TextBox Tprotesis;
    private widget.TextBox Tprovo;
    private widget.TextBox Tquality;
    private widget.TextBox TreakRiwAlergiLain;
    private widget.TextBox TreakRiwAlergiMak;
    private widget.TextBox TreakRiwAlergiObat;
    private widget.TextBox Tsebutkan;
    private widget.Tanggal TtglDp;
    private widget.Tanggal TtglEdukasi;
    private widget.TextBox TtidakDapat;
    private widget.TextBox TtindakanKep;
    public widget.CekBox chkAhliGz;
    public widget.CekBox chkBahasa;
    public widget.CekBox chkBudaya;
    public widget.CekBox chkDiagnosa;
    public widget.CekBox chkDietNutrisi;
    public widget.CekBox chkDokter;
    public widget.CekBox chkEmosi;
    public widget.CekBox chkFarmasis;
    public widget.CekBox chkFisik;
    public widget.CekBox chkGelang;
    public widget.CekBox chkIdentifikai1;
    public widget.CekBox chkIdentifikai10;
    public widget.CekBox chkIdentifikai2;
    public widget.CekBox chkIdentifikai3;
    public widget.CekBox chkIdentifikai4;
    public widget.CekBox chkIdentifikai5;
    public widget.CekBox chkIdentifikai6;
    public widget.CekBox chkIdentifikai7;
    public widget.CekBox chkIdentifikai8;
    public widget.CekBox chkIdentifikai9;
    public widget.CekBox chkKlgPasien;
    public widget.CekBox chkKognitif;
    public widget.CekBox chkLainHambatan;
    public widget.CekBox chkLainKebutuhan;
    public widget.CekBox chkManajemenNyeri;
    public widget.CekBox chkObatTerapi;
    public widget.CekBox chkPasien;
    public widget.CekBox chkPendengaran;
    public widget.CekBox chkPenglihatan;
    public widget.CekBox chkRehabilitasi;
    public widget.CekBox chkRiwAlergiLain;
    public widget.CekBox chkRiwAlergiMak;
    public widget.CekBox chkRiwAlergiObat;
    public widget.CekBox chkRiwTidakAda;
    public widget.CekBox chkRiwTidakDik;
    private widget.CekBox chkSaya1;
    private widget.CekBox chkSaya2;
    public widget.CekBox chkTidakDapat;
    public widget.CekBox chkTindakanKep;
    public widget.CekBox chkYaTerdapat;
    private widget.ComboBox cmbAdl;
    private widget.ComboBox cmbBahasa;
    private widget.ComboBox cmbDP;
    private widget.ComboBox cmbDibutuhkan;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbGizi1;
    private widget.ComboBox cmbGizi2;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbJenis;
    private widget.ComboBox cmbLama;
    private widget.ComboBox cmbMPP;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbNyeri;
    private widget.ComboBox cmbProvo;
    private widget.ComboBox cmbQuality;
    private widget.ComboBox cmbRadia;
    private widget.ComboBox cmbRiwJatuh;
    private widget.ComboBox cmbSever;
    private widget.ComboBox cmbSkala;
    private widget.ComboBox cmbTime;
    private widget.ComboBox cmbYaGizi1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel100;
    private widget.Label jLabel128;
    private widget.Label jLabel129;
    private widget.Label jLabel130;
    private widget.Label jLabel131;
    private widget.Label jLabel132;
    private widget.Label jLabel133;
    private widget.Label jLabel134;
    private widget.Label jLabel365;
    private widget.Label jLabel366;
    private widget.Label jLabel367;
    private widget.Label jLabel368;
    private widget.Label jLabel64;
    private widget.Label jLabel65;
    private widget.Label jLabel68;
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
    private widget.Label jLabel88;
    private widget.Label jLabel89;
    private widget.Label jLabel90;
    private widget.Label jLabel91;
    private widget.Label jLabel92;
    private widget.TextArea kesimpulanGizi;
    private widget.Label label14;
    private widget.Label label15;
    private widget.Label label16;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.Label label22;
    private widget.Label label23;
    private widget.Label label24;
    private widget.Label label25;
    private widget.Label label26;
    private widget.Label label27;
    private widget.ScrollPane scrollInput;
    private widget.TextBox skorGizi1;
    private widget.TextBox skorGizi2;
    private widget.TextBox skorYaGizi1;
    // End of variables declaration//GEN-END:variables

    public void emptTeks() {
        cmbNyeri.setSelectedIndex(0);
        Tlokasi.setText("");
        Tlokasi.setEnabled(false);
        cmbJenis.setSelectedIndex(0);
        cmbSkala.setSelectedIndex(0);

        cmbProvo.setSelectedIndex(0);
        Tprovo.setText("");
        Tprovo.setEnabled(false);

        cmbQuality.setSelectedIndex(0);
        Tquality.setText("");
        Tquality.setEnabled(false);
        cmbRadia.setSelectedIndex(0);
        cmbSever.setSelectedIndex(0);

        cmbTime.setSelectedIndex(0);
        cmbLama.setSelectedIndex(0);
        cmbLama.setEnabled(false);

        cmbGizi1.setSelectedIndex(0);
        cmbYaGizi1.setSelectedIndex(0);
        cmbYaGizi1.setEnabled(false);
        skorGizi1.setText("0");
        skorYaGizi1.setText("0");
        cmbGizi2.setSelectedIndex(0);
        skorGizi2.setText("0");
        TotSkorGizi.setText("0");
        kesimpulanGizi.setText("");
        hitungSkorGizi();

        chkRiwTidakAda.setSelected(false);
        chkRiwTidakDik.setSelected(false);
        chkRiwAlergiObat.setSelected(false);
        TketRiwAlergiObat.setText("");
        TreakRiwAlergiObat.setText("");
        TketRiwAlergiObat.setEnabled(false);
        TreakRiwAlergiObat.setEnabled(false);

        chkRiwAlergiMak.setSelected(false);
        TketRiwAlergiMak.setText("");
        TreakRiwAlergiMak.setText("");
        TketRiwAlergiMak.setEnabled(false);
        TreakRiwAlergiMak.setEnabled(false);

        chkRiwAlergiLain.setSelected(false);
        TketRiwAlergiLain.setText("");
        TreakRiwAlergiLain.setText("");
        TketRiwAlergiLain.setEnabled(false);
        TreakRiwAlergiLain.setEnabled(false);
        chkGelang.setSelected(false);
        chkDokter.setSelected(false);
        chkFarmasis.setSelected(false);
        chkAhliGz.setSelected(false);

        TalatBantu.setText("");
        Tprotesis.setText("");
        TcacatTubuh.setText("");
        cmbAdl.setSelectedIndex(0);
        cmbRiwJatuh.setSelectedIndex(0);
        nipBidan1 = "-";
        TnmBidan1.setText("-");

        chkYaTerdapat.setSelected(false);
        chkPendengaran.setSelected(false);
        chkPenglihatan.setSelected(false);
        chkKognitif.setSelected(false);
        chkBudaya.setSelected(false);
        chkEmosi.setSelected(false);
        chkBahasa.setSelected(false);
        chkLainHambatan.setSelected(false);
        TketLainHambatan.setText("");
        chkPendengaran.setEnabled(false);
        chkPenglihatan.setEnabled(false);
        chkKognitif.setEnabled(false);
        chkFisik.setEnabled(false);
        chkBudaya.setEnabled(false);
        chkEmosi.setEnabled(false);
        chkBahasa.setEnabled(false);
        chkLainHambatan.setEnabled(false);
        TketLainHambatan.setEnabled(false);

        cmbDibutuhkan.setSelectedIndex(0);
        Tsebutkan.setText("");
        Tsebutkan.setEnabled(false);
        cmbBahasa.setSelectedIndex(0);

        chkDiagnosa.setSelected(false);
        chkObatTerapi.setSelected(false);
        chkDietNutrisi.setSelected(false);
        chkRehabilitasi.setSelected(false);
        chkManajemenNyeri.setSelected(false);

        chkTindakanKep.setSelected(false);
        TtindakanKep.setText("");
        TtindakanKep.setEnabled(false);

        chkLainKebutuhan.setSelected(false);
        TlainKebutuhan.setText("");
        TlainKebutuhan.setEnabled(false);

        chkPasien.setSelected(false);
        chkKlgPasien.setSelected(false);
        TnmKlgPasien.setText("");
        TnmKlgPasien.setEnabled(false);
        chkTidakDapat.setSelected(false);
        TtidakDapat.setText("");
        TtidakDapat.setEnabled(false);

        TtglEdukasi.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        nipDokter = "-";
        TnmDokter.setText("-");

        chkIdentifikai1.setSelected(false);
        chkIdentifikai2.setSelected(false);
        chkIdentifikai3.setSelected(false);
        chkIdentifikai4.setSelected(false);
        chkIdentifikai5.setSelected(false);
        chkIdentifikai6.setSelected(false);
        chkIdentifikai7.setSelected(false);
        chkIdentifikai8.setSelected(false);
        chkIdentifikai9.setSelected(false);
        chkIdentifikai10.setSelected(false);
        Tmemerlukan.setText("");
        cmbMPP.setSelectedIndex(0);
        cmbDP.setSelectedIndex(0);
        TtglDp.setDate(new Date());
        TnmKeluargaPasien.setText("");
        nipBidan2 = "-";
        TnmBidan2.setText("-");
        chkSaya1.setSelected(false);
        chkSaya2.setSelected(false);
    }
    
    private void hitungSkorGizi() {
        int A, B, C, Total;
        A = Integer.parseInt(skorGizi1.getText());
        B = Integer.parseInt(skorYaGizi1.getText());
        C = Integer.parseInt(skorGizi2.getText());

        Total = 0;
        Total = A + B + C;
        TotSkorGizi.setText(Valid.SetAngka2(Total));

        if (Total == 0 || Total == 1) {
            kesimpulanGizi.setText("Pasien tidak beresiko malnutrisi");
        } else if (Total >= 2) {
            kesimpulanGizi.setText("Skor >= 2, pasien beresiko malnutrisi, konsul ke Ahli Gizi");
        }
    }
    
    public void isCek() {
        if (akses.getjml2() >= 1) {
            BtnBidan1.setEnabled(false);
            BtnBidan2.setEnabled(false);
            nipBidan1 = akses.getkode();
            nipBidan2 = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan1, nipBidan1);
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmBidan2, nipBidan2);
            if (TnmBidan1.getText().equals("")) {
                nipBidan1 = "";
            }

            if (TnmBidan2.getText().equals("")) {
                nipBidan2 = "";
            }
        }
    }
    
    public void SimpanTemporary(String norwt, String sttsrwt) {
        cekData();
        Sequel.menyimpanIgnore("temporary_asesmen_awal_kebidanan2", "'" + norwt + "','" + cmbNyeri.getSelectedItem().toString() + "',"
                + "'" + Tlokasi.getText() + "','" + cmbJenis.getSelectedItem().toString() + "','" + cmbSkala.getSelectedItem().toString() + "',"
                + "'" + cmbProvo.getSelectedItem().toString() + "','" + Tprovo.getText() + "','" + cmbQuality.getSelectedItem().toString() + "','" + Tquality.getText() + "',"
                + "'" + cmbRadia.getSelectedItem().toString() + "','" + cmbSever.getSelectedItem().toString() + "','" + cmbTime.getSelectedItem().toString() + "',"
                + "'" + cmbLama.getSelectedItem().toString() + "','" + cmbGizi1.getSelectedItem().toString() + "','" + cmbYaGizi1.getSelectedItem().toString() + "',"
                + "'" + cmbGizi2.getSelectedItem().toString() + "','" + tidakAda + "','" + tidakDiketahui + "','" + alergiObat + "','" + TketRiwAlergiObat.getText() + "',"
                + "'" + TreakRiwAlergiObat.getText() + "','" + alergiMakanan + "','" + TketRiwAlergiMak.getText() + "','" + TreakRiwAlergiMak.getText() + "',"
                + "'" + alergiLainya + "','" + TketRiwAlergiLain.getText() + "','" + TreakRiwAlergiLain.getText() + "','" + gelangTanda + "','" + alergiDiberitahukanDokter + "',"
                + "'" + alergiDiberitahukanFarmasis + "','" + alergiDiberitahukanAhligizi + "','" + TalatBantu.getText() + "','" + Tprotesis.getText() + "',"
                + "'" + TcacatTubuh.getText() + "','" + cmbAdl.getSelectedItem().toString() + "','" + cmbRiwJatuh.getSelectedItem().toString() + "',"
                + "'" + nipBidan1 + "','" + ya + "','" + pendengaran + "','" + penglihatan + "','" + kognitif + "','" + fisik + "','" + budaya + "','" + emosi + "',"
                + "'" + bahasa + "','" + lainHambatan + "','" + TketLainHambatan.getText() + "','" + cmbDibutuhkan.getSelectedItem().toString() + "',"
                + "'" + Tsebutkan.getText() + "','" + cmbBahasa.getSelectedItem().toString() + "','" + diagnosa + "','" + tindakanKeperawatan + "','" + TtindakanKep.getText() + "',"
                + "'" + lainKebutuhanEdukasi + "','" + TlainKebutuhan.getText() + "','" + obatObatan + "','" + rehabilitasi + "','" + diet + "','" + manajemenNyeri + "',"
                + "'" + pasien + "','" + keluargaPasien + "','" + TnmKlgPasien.getText() + "','" + tidakDapat + "','" + TtidakDapat.getText() + "',"
                + "'" + Valid.SetTgl(TtglEdukasi.getSelectedItem() + "") + "','" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                + "'" + nipDokter + "','" + identifikasi1 + "','" + identifikasi2 + "','" + identifikasi3 + "','" + identifikasi4 + "','" + identifikasi5 + "',"
                + "'" + identifikasi6 + "','" + identifikasi7 + "','" + identifikasi8 + "','" + identifikasi9 + "','" + identifikasi10 + "','" + Tmemerlukan.getText() + "',"
                + "'" + cmbMPP.getSelectedItem().toString() + "','" + cmbDP.getSelectedItem().toString() + "','" + Valid.SetTgl(TtglDp.getSelectedItem() + "") + "',"
                + "'" + TnmKeluargaPasien.getText() + "','" + nipBidan2 + "','" + sttsrwt + "','" + Sequel.cariIsi("select now()") + "','" + TnmBidan1.getText() + "',"
                + "'" + TnmBidan2.getText() + "','" + TnmDokter.getText() + "','" + saya1 + "','" + saya2 + "'", "Tabel asesmen_awal_kebidanan2");
    }
    
    public void tampil(String norwt) {
        try {
            ps1 = koneksi.prepareStatement("select * from asesmen_awal_kebidanan2 where no_rawat='" + norwt + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    noRawat = rs1.getString("no_rawat");
                    cmbNyeri.setSelectedItem(rs1.getString("nyeri"));
                    Tlokasi.setText(rs1.getString("lokasi_nyeri"));
                    cmbJenis.setSelectedItem(rs1.getString("jenis"));
                    cmbSkala.setSelectedItem(rs1.getString("skala_nyeri"));
                    cmbProvo.setSelectedItem(rs1.getString("provocation"));
                    Tprovo.setText(rs1.getString("ket_lain_provocation"));
                    cmbQuality.setSelectedItem(rs1.getString("quality"));
                    Tquality.setText(rs1.getString("ket_lain_quality"));
                    cmbRadia.setSelectedItem(rs1.getString("radiation"));
                    cmbSever.setSelectedItem(rs1.getString("severity"));
                    cmbTime.setSelectedItem(rs1.getString("time"));
                    cmbLama.setSelectedItem(rs1.getString("time_lama"));
                    cmbGizi1.setSelectedItem(rs1.getString("gizi_1"));
                    cmbYaGizi1.setSelectedItem(rs1.getString("gizi_1ya"));
                    cmbGizi2.setSelectedItem(rs1.getString("gizi_2"));
                    tidakAda = rs1.getString("cek_tidak_ada");
                    tidakDiketahui = rs1.getString("cek_tidak_diketahui");
                    alergiObat = rs1.getString("cek_alergi_obat");
                    TketRiwAlergiObat.setText(rs1.getString("ket_alergi_obat"));
                    TreakRiwAlergiObat.setText(rs1.getString("ket_reaksi_alergi_obat"));
                    alergiMakanan = rs1.getString("cek_alergi_makanan");
                    TketRiwAlergiMak.setText(rs1.getString("ket_alergi_makanan"));
                    TreakRiwAlergiMak.setText(rs1.getString("ket_reaksi_alergi_makanan"));
                    alergiLainya = rs1.getString("cek_alergi_lainya");
                    TketRiwAlergiLain.setText(rs1.getString("ket_alergi_lainya"));
                    TreakRiwAlergiLain.setText(rs1.getString("ket_reaksi_alergi_lainya"));
                    gelangTanda = rs1.getString("cek_gelang_tanda");
                    alergiDiberitahukanDokter = rs1.getString("cek_alergi_diberitahukan_dokter");
                    alergiDiberitahukanFarmasis = rs1.getString("cek_alergi_diberitahukan_farmasis");
                    alergiDiberitahukanAhligizi = rs1.getString("cek_alergi_diberitahukan_ahliGizi");
                    TalatBantu.setText(rs1.getString("alat_bantu"));
                    Tprotesis.setText(rs1.getString("prothesis"));
                    TcacatTubuh.setText(rs1.getString("cacat_tubuh"));
                    cmbAdl.setSelectedItem(rs1.getString("adl"));
                    cmbRiwJatuh.setSelectedItem(rs1.getString("riwayat_jatuh"));
                    nipBidan1 = rs1.getString("nip_bidan");
                    ya = rs1.getString("cek_ya");
                    pendengaran = rs1.getString("cek_pendengaran");
                    penglihatan = rs1.getString("cek_penglihatan");
                    kognitif = rs1.getString("cek_kognitif");
                    fisik = rs1.getString("cek_fisik");
                    budaya = rs1.getString("cek_budaya");
                    emosi = rs1.getString("cek_emosi");
                    bahasa = rs1.getString("cek_bahasa");
                    lainHambatan = rs1.getString("cek_lain_hambatan");
                    TketLainHambatan.setText(rs1.getString("ket_lain_hambatan"));
                    cmbDibutuhkan.setSelectedItem(rs1.getString("dibutuhkan_penerjemah"));
                    Tsebutkan.setText(rs1.getString("sebutkan"));
                    cmbBahasa.setSelectedItem(rs1.getString("bahasa_isyarat"));
                    diagnosa = rs1.getString("cek_diagnosa");
                    tindakanKeperawatan = rs1.getString("cek_tindakan_keperawatan");
                    TtindakanKep.setText(rs1.getString("ket_tindakan_keperawatan"));
                    lainKebutuhanEdukasi = rs1.getString("cek_lain_kebutuhan_edukasi");
                    TlainKebutuhan.setText(rs1.getString("ket_lain_kebutuhan_edukasi"));
                    obatObatan = rs1.getString("cek_obat_obatan");
                    rehabilitasi = rs1.getString("cek_rehabilitasi");
                    diet = rs1.getString("cek_diet");
                    manajemenNyeri = rs1.getString("cek_manajemen_nyeri");
                    pasien = rs1.getString("cek_pasien");
                    keluargaPasien = rs1.getString("cek_keluarga_pasien");
                    TnmKlgPasien.setText(rs1.getString("nama_keluarga_pasien"));
                    tidakDapat = rs1.getString("cek_tidak_dapat");
                    TtidakDapat.setText(rs1.getString("ket_tidak_dapat"));
                    Valid.SetTgl(TtglEdukasi, rs1.getString("tgl_edukasi"));
                    cmbJam.setSelectedItem(rs1.getString("jam_edukasi").toString().substring(0, 2));
                    cmbMnt.setSelectedItem(rs1.getString("jam_edukasi").toString().substring(3, 5));
                    cmbDtk.setSelectedItem(rs1.getString("jam_edukasi").toString().substring(6, 8));
                    nipDokter = rs1.getString("nip_dokter");
                    identifikasi1 = rs1.getString("cek_identifikasi1");
                    identifikasi2 = rs1.getString("cek_identifikasi2");
                    identifikasi3 = rs1.getString("cek_identifikasi3");
                    identifikasi4 = rs1.getString("cek_identifikasi4");
                    identifikasi5 = rs1.getString("cek_identifikasi5");
                    identifikasi6 = rs1.getString("cek_identifikasi6");
                    identifikasi7 = rs1.getString("cek_identifikasi7");
                    identifikasi8 = rs1.getString("cek_identifikasi8");
                    identifikasi9 = rs1.getString("cek_identifikasi9");
                    identifikasi10 = rs1.getString("cek_identifikasi10");
                    Tmemerlukan.setText(rs1.getString("memerlukan"));
                    cmbMPP.setSelectedItem(rs1.getString("mpp"));
                    cmbDP.setSelectedItem(rs1.getString("dp"));
                    Valid.SetTgl(TtglDp, rs1.getString("tgl_dp"));
                    TnmKeluargaPasien.setText(rs1.getString("nm_keluarga_pasien"));
                    nipBidan2 = rs1.getString("nip_bidan_dp");
                    dataCek();
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
    
    public void tampilTemporary(String norwt) {
        try {
            ps = koneksi.prepareStatement("select * from temporary_asesmen_awal_kebidanan2 where no_rawat='" + norwt + "'");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    cmbNyeri.setSelectedItem(rs.getString("nyeri"));
                    Tlokasi.setText(rs.getString("lokasi_nyeri"));
                    cmbJenis.setSelectedItem(rs.getString("jenis"));
                    cmbSkala.setSelectedItem(rs.getString("skala_nyeri"));
                    cmbProvo.setSelectedItem(rs.getString("provocation"));
                    Tprovo.setText(rs.getString("ket_lain_provocation"));
                    cmbQuality.setSelectedItem(rs.getString("quality"));
                    Tquality.setText(rs.getString("ket_lain_quality"));
                    cmbRadia.setSelectedItem(rs.getString("radiation"));
                    cmbSever.setSelectedItem(rs.getString("severity"));
                    cmbTime.setSelectedItem(rs.getString("time"));
                    cmbLama.setSelectedItem(rs.getString("time_lama"));
                    cmbGizi1.setSelectedItem(rs.getString("gizi_1"));
                    cmbYaGizi1.setSelectedItem(rs.getString("gizi_1ya"));
                    cmbGizi2.setSelectedItem(rs.getString("gizi_2"));
                    tidakAda = rs.getString("cek_tidak_ada");
                    tidakDiketahui = rs.getString("cek_tidak_diketahui");
                    alergiObat = rs.getString("cek_alergi_obat");
                    TketRiwAlergiObat.setText(rs.getString("ket_alergi_obat"));
                    TreakRiwAlergiObat.setText(rs.getString("ket_reaksi_alergi_obat"));
                    alergiMakanan = rs.getString("cek_alergi_makanan");
                    TketRiwAlergiMak.setText(rs.getString("ket_alergi_makanan"));
                    TreakRiwAlergiMak.setText(rs.getString("ket_reaksi_alergi_makanan"));
                    alergiLainya = rs.getString("cek_alergi_lainya");
                    TketRiwAlergiLain.setText(rs.getString("ket_alergi_lainya"));
                    TreakRiwAlergiLain.setText(rs.getString("ket_reaksi_alergi_lainya"));
                    gelangTanda = rs.getString("cek_gelang_tanda");
                    alergiDiberitahukanDokter = rs.getString("cek_alergi_diberitahukan_dokter");
                    alergiDiberitahukanFarmasis = rs.getString("cek_alergi_diberitahukan_farmasis");
                    alergiDiberitahukanAhligizi = rs.getString("cek_alergi_diberitahukan_ahliGizi");
                    TalatBantu.setText(rs.getString("alat_bantu"));
                    Tprotesis.setText(rs.getString("prothesis"));
                    TcacatTubuh.setText(rs.getString("cacat_tubuh"));
                    cmbAdl.setSelectedItem(rs.getString("adl"));
                    cmbRiwJatuh.setSelectedItem(rs.getString("riwayat_jatuh"));
                    nipBidan1 = rs.getString("nip_bidan");
                    TnmBidan1.setText(rs.getString("nm_bidan"));
                    saya1 = rs.getString("cek_saya1");
                    ya = rs.getString("cek_ya");
                    pendengaran = rs.getString("cek_pendengaran");
                    penglihatan = rs.getString("cek_penglihatan");
                    kognitif = rs.getString("cek_kognitif");
                    fisik = rs.getString("cek_fisik");
                    budaya = rs.getString("cek_budaya");
                    emosi = rs.getString("cek_emosi");
                    bahasa = rs.getString("cek_bahasa");
                    lainHambatan = rs.getString("cek_lain_hambatan");
                    TketLainHambatan.setText(rs.getString("ket_lain_hambatan"));
                    cmbDibutuhkan.setSelectedItem(rs.getString("dibutuhkan_penerjemah"));
                    Tsebutkan.setText(rs.getString("sebutkan"));
                    cmbBahasa.setSelectedItem(rs.getString("bahasa_isyarat"));
                    diagnosa = rs.getString("cek_diagnosa");
                    tindakanKeperawatan = rs.getString("cek_tindakan_keperawatan");
                    TtindakanKep.setText(rs.getString("ket_tindakan_keperawatan"));
                    lainKebutuhanEdukasi = rs.getString("cek_lain_kebutuhan_edukasi");
                    TlainKebutuhan.setText(rs.getString("ket_lain_kebutuhan_edukasi"));
                    obatObatan = rs.getString("cek_obat_obatan");
                    rehabilitasi = rs.getString("cek_rehabilitasi");
                    diet = rs.getString("cek_diet");
                    manajemenNyeri = rs.getString("cek_manajemen_nyeri");
                    pasien = rs.getString("cek_pasien");
                    keluargaPasien = rs.getString("cek_keluarga_pasien");
                    TnmKlgPasien.setText(rs.getString("nama_keluarga_pasien"));
                    tidakDapat = rs.getString("cek_tidak_dapat");
                    TtidakDapat.setText(rs.getString("ket_tidak_dapat"));
                    Valid.SetTgl(TtglEdukasi, rs.getString("tgl_edukasi"));
                    cmbJam.setSelectedItem(rs.getString("jam_edukasi").toString().substring(0, 2));
                    cmbMnt.setSelectedItem(rs.getString("jam_edukasi").toString().substring(3, 5));
                    cmbDtk.setSelectedItem(rs.getString("jam_edukasi").toString().substring(6, 8));                    
                    nipDokter = rs.getString("nip_dokter");
                    TnmDokter.setText(rs.getString("nm_dokter"));
                    identifikasi1 = rs.getString("cek_identifikasi1");
                    identifikasi2 = rs.getString("cek_identifikasi2");
                    identifikasi3 = rs.getString("cek_identifikasi3");
                    identifikasi4 = rs.getString("cek_identifikasi4");
                    identifikasi5 = rs.getString("cek_identifikasi5");
                    identifikasi6 = rs.getString("cek_identifikasi6");
                    identifikasi7 = rs.getString("cek_identifikasi7");
                    identifikasi8 = rs.getString("cek_identifikasi8");
                    identifikasi9 = rs.getString("cek_identifikasi9");
                    identifikasi10 = rs.getString("cek_identifikasi10");
                    Tmemerlukan.setText(rs.getString("memerlukan"));
                    cmbMPP.setSelectedItem(rs.getString("mpp"));
                    cmbDP.setSelectedItem(rs.getString("dp"));
                    Valid.SetTgl(TtglDp, rs.getString("tgl_dp"));
                    TnmKeluargaPasien.setText(rs.getString("nm_keluarga_pasien"));
                    nipBidan2 = rs.getString("nip_bidan_dp");
                    TnmBidan2.setText(rs.getString("nm_bidan_dp"));
                    saya2 = rs.getString("cek_saya2");
                    dataCek();
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
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
    }
    
    private void cekData() {
        if (chkRiwTidakAda.isSelected() == true) {
            tidakAda = "ya";
        } else {
            tidakAda = "tidak";
        }
        
        if (chkRiwTidakDik.isSelected() == true) {
            tidakDiketahui = "ya";
        } else {
            tidakDiketahui = "tidak";
        }
        
        if (chkRiwAlergiObat.isSelected() == true) {
            alergiObat = "ya";
        } else {
            alergiObat = "tidak";
        }
        
        if (chkRiwAlergiMak.isSelected() == true) {
            alergiMakanan = "ya";
        } else {
            alergiMakanan = "tidak";
        }
        
        if (chkRiwAlergiLain.isSelected() == true) {
            alergiLainya = "ya";
        } else {
            alergiLainya = "tidak";
        }
        
        if (chkGelang.isSelected() == true) {
            gelangTanda = "ya";
        } else {
            gelangTanda = "tidak";
        }
        
        if (chkDokter.isSelected() == true) {
            alergiDiberitahukanDokter = "ya";
        } else {
            alergiDiberitahukanDokter = "tidak";
        }
        if (chkFarmasis.isSelected() == true) {
            alergiDiberitahukanFarmasis = "ya";
        } else {
            alergiDiberitahukanFarmasis = "tidak";
        }
        
        if (chkAhliGz.isSelected() == true) {
            alergiDiberitahukanAhligizi = "ya";
        } else {
            alergiDiberitahukanAhligizi = "tidak";
        }
        
        if (chkYaTerdapat.isSelected() == true) {
            ya = "ya";
        } else {
            ya = "tidak";
        }
        
        if (chkPendengaran.isSelected() == true) {
            pendengaran = "ya";
        } else {
            pendengaran = "tidak";
        }
        
        if (chkPenglihatan.isSelected() == true) {
            penglihatan = "ya";
        } else {
            penglihatan = "tidak";
        }
        
        if (chkKognitif.isSelected() == true) {
            kognitif = "ya";
        } else {
            kognitif = "tidak";
        }

        if (chkFisik.isSelected() == true) {
            fisik = "ya";
        } else {
            fisik = "tidak";
        }
        
        if (chkBudaya.isSelected() == true) {
            budaya = "ya";
        } else {
            budaya = "tidak";
        }
        
        if (chkEmosi.isSelected() == true) {
            emosi = "ya";
        } else {
            emosi = "tidak";
        }
        
        if (chkBahasa.isSelected() == true) {
            bahasa = "ya";
        } else {
            bahasa = "tidak";
        }
        
        if (chkLainHambatan.isSelected() == true) {
            lainHambatan = "ya";
        } else {
            lainHambatan = "tidak";
        }
        
        if (chkDiagnosa.isSelected() == true) {
            diagnosa = "ya";
        } else {
            diagnosa = "tidak";
        }
        
        if (chkTindakanKep.isSelected() == true) {
            tindakanKeperawatan = "ya";
        } else {
            tindakanKeperawatan = "tidak";
        }
        
        if (chkLainKebutuhan.isSelected() == true) {
            lainKebutuhanEdukasi = "ya";
        } else {
            lainKebutuhanEdukasi = "tidak";
        }
        
        if (chkObatTerapi.isSelected() == true) {
            obatObatan = "ya";
        } else {
            obatObatan = "tidak";
        }
        
        if (chkRehabilitasi.isSelected() == true) {
            rehabilitasi = "ya";
        } else {
            rehabilitasi = "tidak";
        }
        
        if (chkDietNutrisi.isSelected() == true) {
            diet = "ya";
        } else {
            diet = "tidak";
        }
        
        if (chkManajemenNyeri.isSelected() == true) {
            manajemenNyeri = "ya";
        } else {
            manajemenNyeri = "tidak";
        }
        
        if (chkPasien.isSelected() == true) {
            pasien = "ya";
        } else {
            pasien = "tidak";
        }
        
        if (chkKlgPasien.isSelected() == true) {
            keluargaPasien = "ya";
        } else {
            keluargaPasien = "tidak";
        }
        
        if (chkTidakDapat.isSelected() == true) {
            tidakDapat = "ya";
        } else {
            tidakDapat = "tidak";
        }
        
        if (chkIdentifikai1.isSelected() == true) {
            identifikasi1 = "ya";
        } else {
            identifikasi1 = "tidak";
        }
        
        if (chkIdentifikai2.isSelected() == true) {
            identifikasi2 = "ya";
        } else {
            identifikasi2 = "tidak";
        }
        
        if (chkIdentifikai3.isSelected() == true) {
            identifikasi3 = "ya";
        } else {
            identifikasi3 = "tidak";
        }
        
        if (chkIdentifikai4.isSelected() == true) {
            identifikasi4 = "ya";
        } else {
            identifikasi4 = "tidak";
        }
        
        if (chkIdentifikai5.isSelected() == true) {
            identifikasi5 = "ya";
        } else {
            identifikasi5 = "tidak";
        }
        
        if (chkIdentifikai6.isSelected() == true) {
            identifikasi6 = "ya";
        } else {
            identifikasi6 = "tidak";
        }
        
        if (chkIdentifikai7.isSelected() == true) {
            identifikasi7 = "ya";
        } else {
            identifikasi7 = "tidak";
        }
        
        if (chkIdentifikai8.isSelected() == true) {
            identifikasi8 = "ya";
        } else {
            identifikasi8 = "tidak";
        }
        
        if (chkIdentifikai9.isSelected() == true) {
            identifikasi9 = "ya";
        } else {
            identifikasi9 = "tidak";
        }
        
        if (chkIdentifikai10.isSelected() == true) {
            identifikasi10 = "ya";
        } else {
            identifikasi10 = "tidak";
        }
        
        if (chkSaya1.isSelected() == true) {
            saya1 = "ya";
        } else {
            saya1 = "tidak";
        }
        
        if (chkSaya2.isSelected() == true) {
            saya2 = "ya";
        } else {
            saya2 = "tidak";
        }
    }
    
    private void dataCek() {
        if (cmbNyeri.getSelectedIndex() == 1) {
            Tlokasi.setEnabled(true);
        } else {
            Tlokasi.setEnabled(false);
        }
        
        if (cmbProvo.getSelectedIndex() == 5) {
            Tprovo.setEnabled(true);
        } else {
            Tprovo.setEnabled(false);
        }
        
        if (cmbQuality.getSelectedIndex() == 9) {
            Tquality.setEnabled(true);
        } else {
            Tquality.setEnabled(false);
        }
        
        if (cmbTime.getSelectedIndex() == 0) {
            cmbLama.setEnabled(false);
        } else {
            cmbLama.setEnabled(true);
        }
        
        if (cmbGizi1.getSelectedIndex() == 0) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 1) {
            skorGizi1.setText("2");
            cmbYaGizi1.setEnabled(false);
        } else if (cmbGizi1.getSelectedIndex() == 2) {
            skorGizi1.setText("0");
            cmbYaGizi1.setEnabled(true);
        }
        
        if (cmbYaGizi1.getSelectedIndex() == 0) {
            skorYaGizi1.setText("0");
        } else if (cmbYaGizi1.getSelectedIndex() == 1) {
            skorYaGizi1.setText("1");
        } else if (cmbYaGizi1.getSelectedIndex() == 2) {
            skorYaGizi1.setText("2");
        } else if (cmbYaGizi1.getSelectedIndex() == 3) {
            skorYaGizi1.setText("3");
        } else if (cmbYaGizi1.getSelectedIndex() == 4) {
            skorYaGizi1.setText("4");
        } else if (cmbYaGizi1.getSelectedIndex() == 5) {
            skorYaGizi1.setText("2");
        }
        
        if (cmbGizi2.getSelectedIndex() == 0) {
            skorGizi2.setText("0");
        } else if (cmbGizi2.getSelectedIndex() == 1) {
            skorGizi2.setText("1");
        }
        
        hitungSkorGizi();
        
        if (tidakAda.equals("ya")) {
            chkRiwTidakAda.setSelected(true);
        } else {
            chkRiwTidakAda.setSelected(false);
        }
        
        if (tidakDiketahui.equals("ya")) {
            chkRiwTidakDik.setSelected(true);
        } else {
            chkRiwTidakDik.setSelected(false);
        }
        
        if (alergiObat.equals("ya")) {
            chkRiwAlergiObat.setSelected(true);
            TketRiwAlergiObat.setEnabled(true);
            TreakRiwAlergiObat.setEnabled(true);
        } else {
            chkRiwAlergiObat.setSelected(false);
            TketRiwAlergiObat.setEnabled(false);
            TreakRiwAlergiObat.setEnabled(false);
        }
        
        if (alergiMakanan.equals("ya")) {
            chkRiwAlergiMak.setSelected(true);
            TketRiwAlergiMak.setEnabled(true);
            TreakRiwAlergiMak.setEnabled(true);
        } else {
            chkRiwAlergiMak.setSelected(false);
            TketRiwAlergiMak.setEnabled(false);
            TreakRiwAlergiMak.setEnabled(false);
        }
        
        if (alergiLainya.equals("ya")) {
            chkRiwAlergiLain.setSelected(true);
            TketRiwAlergiLain.setEnabled(true);
            TreakRiwAlergiLain.setEnabled(true);
        } else {
            chkRiwAlergiLain.setSelected(false);
            TketRiwAlergiLain.setEnabled(false);
            TreakRiwAlergiLain.setEnabled(false);
        }
        
        if (gelangTanda.equals("ya")) {
            chkGelang.setSelected(true);
        } else {
            chkGelang.setSelected(false);
        }
        
        if (alergiDiberitahukanDokter.equals("ya")) {
            chkDokter.setSelected(true);
        } else {
            chkDokter.setSelected(false);
        }
        
        if (alergiDiberitahukanFarmasis.equals("ya")) {
            chkFarmasis.setSelected(true);
        } else {
            chkFarmasis.setSelected(false);
        }
        
        if (alergiDiberitahukanAhligizi.equals("ya")) {
            chkAhliGz.setSelected(true);
        } else {
            chkAhliGz.setSelected(false);
        }
        
        if (ya.equals("ya")) {
            chkYaTerdapat.setSelected(true);
        } else {
            chkYaTerdapat.setSelected(false);
        }
        
        if (pendengaran.equals("ya")) {
            chkPendengaran.setSelected(true);
        } else {
            chkPendengaran.setSelected(false);
        }
        
        if (penglihatan.equals("ya")) {
            chkPenglihatan.setSelected(true);
        } else {
            chkPenglihatan.setSelected(false);
        }
        
        if (kognitif.equals("ya")) {
            chkKognitif.setSelected(true);
        } else {
            chkKognitif.setSelected(false);
        }
        
        if (fisik.equals("ya")) {
            chkFisik.setSelected(true);
        } else {
            chkFisik.setSelected(false);
        }
        
        if (budaya.equals("ya")) {
            chkBudaya.setSelected(true);
        } else {
            chkBudaya.setSelected(false);
        }
        
        if (emosi.equals("ya")) {
            chkEmosi.setSelected(true);
        } else {
            chkEmosi.setSelected(false);
        }
        
        if (bahasa.equals("ya")) {
            chkBahasa.setSelected(true);
        } else {
            chkBahasa.setSelected(false);
        }
        
        if (lainHambatan.equals("ya")) {
            chkLainHambatan.setSelected(true);
            TketLainHambatan.setEnabled(true);
        } else {
            chkLainHambatan.setSelected(false);
            TketLainHambatan.setEnabled(false);
        }
        
        if (cmbDibutuhkan.getSelectedIndex() == 1) {
            Tsebutkan.setEnabled(true);
        } else {
            Tsebutkan.setEnabled(false);
        }
        
        if (diagnosa.equals("ya")) {
            chkDiagnosa.setSelected(true);
        } else {
            chkDiagnosa.setSelected(false);
        }
        
        if (tindakanKeperawatan.equals("ya")) {
            chkTindakanKep.setSelected(true);
            TtindakanKep.setEnabled(true);
        } else {
            chkTindakanKep.setSelected(false);
            TtindakanKep.setEnabled(false);
        }
        
        if (lainKebutuhanEdukasi.equals("ya")) {
            chkLainKebutuhan.setSelected(true);
            TlainKebutuhan.setEnabled(true);
        } else {
            chkLainKebutuhan.setSelected(false);
            TlainKebutuhan.setEnabled(false);
        }
        
        if (obatObatan.equals("ya")) {
            chkObatTerapi.setSelected(true);
        } else {
            chkObatTerapi.setSelected(false);
        }
        
        if (rehabilitasi.equals("ya")) {
            chkRehabilitasi.setSelected(true);
        } else {
            chkRehabilitasi.setSelected(false);
        }
        
        if (diet.equals("ya")) {
            chkDietNutrisi.setSelected(true);
        } else {
            chkDietNutrisi.setSelected(false);
        }
        
        if (manajemenNyeri.equals("ya")) {
            chkManajemenNyeri.setSelected(true);
        } else {
            chkManajemenNyeri.setSelected(false);
        }
        
        if (pasien.equals("ya")) {
            chkPasien.setSelected(true);
        } else {
            chkPasien.setSelected(false);
        }
        
        if (keluargaPasien.equals("ya")) {
            chkKlgPasien.setSelected(true);
            TnmKlgPasien.setEnabled(true);
        } else {
            chkKlgPasien.setSelected(false);
            TnmKlgPasien.setEnabled(false);
        }
        
        if (tidakDapat.equals("ya")) {
            chkTidakDapat.setSelected(true);
            TtidakDapat.setEnabled(true);
        } else {
            chkTidakDapat.setSelected(false);
            TtidakDapat.setEnabled(false);
        }
        
        if (identifikasi1.equals("ya")) {
            chkIdentifikai1.setSelected(true);
        } else {
            chkIdentifikai1.setSelected(false);
        }
        
        if (identifikasi2.equals("ya")) {
            chkIdentifikai2.setSelected(true);
        } else {
            chkIdentifikai2.setSelected(false);
        }
        
        if (identifikasi3.equals("ya")) {
            chkIdentifikai3.setSelected(true);
        } else {
            chkIdentifikai3.setSelected(false);
        }
        
        if (identifikasi4.equals("ya")) {
            chkIdentifikai4.setSelected(true);
        } else {
            chkIdentifikai4.setSelected(false);
        }
        
        if (identifikasi5.equals("ya")) {
            chkIdentifikai5.setSelected(true);
        } else {
            chkIdentifikai5.setSelected(false);
        }
        
        if (identifikasi6.equals("ya")) {
            chkIdentifikai6.setSelected(true);
        } else {
            chkIdentifikai6.setSelected(false);
        }
        
        if (identifikasi7.equals("ya")) {
            chkIdentifikai7.setSelected(true);
        } else {
            chkIdentifikai7.setSelected(false);
        }
        
        if (identifikasi8.equals("ya")) {
            chkIdentifikai8.setSelected(true);
        } else {
            chkIdentifikai8.setSelected(false);
        }
        
        if (identifikasi9.equals("ya")) {
            chkIdentifikai9.setSelected(true);
        } else {
            chkIdentifikai9.setSelected(false);
        }
        
        if (identifikasi10.equals("ya")) {
            chkIdentifikai10.setSelected(true);
        } else {
            chkIdentifikai10.setSelected(false);
        }
        
        if (saya1.equals("ya")) {
            chkSaya1.setSelected(true);
        } else {
            chkSaya1.setSelected(false);
        }
        
        if (saya2.equals("ya")) {
            chkSaya2.setSelected(true);
        } else {
            chkSaya2.setSelected(false);
        }
    }
    
    public void Tutup() {
        dispose();
    }
    
    private void variabelBersihHal2() {
        nipBidan1 = "";
        nipBidan2 = "";
        nipDokter = "";
        tidakAda = "";
        tidakDiketahui = "";
        alergiObat = "";
        alergiMakanan = "";
        alergiLainya = "";
        gelangTanda = "";
        alergiDiberitahukanDokter = "";
        alergiDiberitahukanFarmasis = "";
        alergiDiberitahukanAhligizi = "";
        ya = "";
        pendengaran = "";
        penglihatan = "";
        kognitif = "";
        fisik = "";
        budaya = "";
        emosi = "";
        bahasa = "";
        lainHambatan = "";
        diagnosa = "";
        tindakanKeperawatan = "";
        lainKebutuhanEdukasi = "";
        obatObatan = "";
        rehabilitasi = "";
        diet = "";
        manajemenNyeri = "";
        pasien = "";
        keluargaPasien = "";
        tidakDapat = "";
        identifikasi1 = "";
        identifikasi2 = "";
        identifikasi3 = "";
        identifikasi4 = "";
        identifikasi5 = "";
        identifikasi6 = "";
        identifikasi7 = "";
        identifikasi8 = "";
        identifikasi9 = "";
        identifikasi10 = "";
        saya1 = "";
        saya2 = "";
        noRawat = "";
    }
}
