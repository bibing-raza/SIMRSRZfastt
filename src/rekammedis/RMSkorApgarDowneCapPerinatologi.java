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
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class RMSkorApgarDowneCapPerinatologi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0, x = 0;
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private String nip = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMSkorApgarDowneCapPerinatologi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Skoring", "Jam Skoring", "Evaluasi Downe Score", "Nama Perawat",
            "apgar_frekuensi1", "apgar_usaha1", "apgar_tonus1", "apgar_reflex1", "apgar_warna1", "apgar_frekuensi5", "apgar_usaha5", "apgar_tonus5", "apgar_reflex5",
            "apgar_warna5", "apgar_frekuensi10", "apgar_usaha10", "apgar_tonus10", "apgar_reflex10", "apgar_warna10", "nilai_downeA", "nilai_downeB", "nilai_downeC",
            "downe_frekuensi_nilaiA", "downe_retraksi_nilaiA", "downe_sianosis_nilaiA", "downe_air_nilaiA", "downe_merintih_nilaiA", "downe_frekuensi_nilaiB",
            "downe_retraksi_nilaiB", "downe_sianosis_nilaiB", "downe_air_nilaiB", "downe_merintih_nilaiB", "downe_frekuensi_nilaiC", "downe_retraksi_nilaiC",
            "downe_sianosis_nilaiC", "downe_air_nilaiC", "downe_merintih_nilaiC", "evaluasi_downe", "tanggal", "jam", "nip_perawat", "waktu_simpan", "tgllahir"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbSkor.setModel(tabMode);
        tbSkor.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSkor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 49; i++) {
            TableColumn column = tbSkor.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(150);                
            } else if (i == 9) {
                column.setPreferredWidth(220);
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
            }
        }
        tbSkor.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        TmenitA.setDocument(new batasInput((byte) 3).getOnlyAngka(TmenitA));
        TmenitB.setDocument(new batasInput((byte) 3).getOnlyAngka(TmenitB));
        TmenitC.setDocument(new batasInput((byte) 3).getOnlyAngka(TmenitC));
        
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
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmPerawat.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
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
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel8 = new widget.Label();
        TabApgar = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        jLabel9 = new widget.Label();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        jLabel12 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel14 = new widget.Label();
        cmbFrek1 = new widget.ComboBox();
        cmbUsaha1 = new widget.ComboBox();
        cmbTonus1 = new widget.ComboBox();
        cmbReflex1 = new widget.ComboBox();
        cmbWarna1 = new widget.ComboBox();
        jLabel130 = new widget.Label();
        TnilaiFrek1 = new widget.TextBox();
        jLabel131 = new widget.Label();
        TnilaiUsaha1 = new widget.TextBox();
        jLabel132 = new widget.Label();
        TnilaiTonus1 = new widget.TextBox();
        jLabel133 = new widget.Label();
        TnilaiReflex1 = new widget.TextBox();
        jLabel134 = new widget.Label();
        TnilaiWarna1 = new widget.TextBox();
        jLabel135 = new widget.Label();
        TJumlah1 = new widget.TextBox();
        panelBiasa9 = new widget.PanelBiasa();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        cmbFrek5 = new widget.ComboBox();
        cmbUsaha5 = new widget.ComboBox();
        cmbTonus5 = new widget.ComboBox();
        cmbReflex5 = new widget.ComboBox();
        cmbWarna5 = new widget.ComboBox();
        jLabel136 = new widget.Label();
        TnilaiFrek5 = new widget.TextBox();
        jLabel137 = new widget.Label();
        TnilaiUsaha5 = new widget.TextBox();
        jLabel138 = new widget.Label();
        TnilaiTonus5 = new widget.TextBox();
        jLabel139 = new widget.Label();
        TnilaiReflex5 = new widget.TextBox();
        jLabel140 = new widget.Label();
        TnilaiWarna5 = new widget.TextBox();
        jLabel141 = new widget.Label();
        TJumlah5 = new widget.TextBox();
        jLabel55 = new widget.Label();
        panelBiasa10 = new widget.PanelBiasa();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        jLabel25 = new widget.Label();
        jLabel26 = new widget.Label();
        cmbFrek10 = new widget.ComboBox();
        cmbUsaha10 = new widget.ComboBox();
        cmbTonus10 = new widget.ComboBox();
        cmbReflex10 = new widget.ComboBox();
        cmbWarna10 = new widget.ComboBox();
        jLabel142 = new widget.Label();
        TnilaiFrek10 = new widget.TextBox();
        jLabel143 = new widget.Label();
        TnilaiUsaha10 = new widget.TextBox();
        jLabel144 = new widget.Label();
        TnilaiTonus10 = new widget.TextBox();
        jLabel145 = new widget.Label();
        TnilaiReflex10 = new widget.TextBox();
        jLabel146 = new widget.Label();
        TnilaiWarna10 = new widget.TextBox();
        jLabel147 = new widget.Label();
        TJumlah10 = new widget.TextBox();
        jLabel56 = new widget.Label();
        jLabel27 = new widget.Label();
        TabDowne = new javax.swing.JTabbedPane();
        panelBiasa11 = new widget.PanelBiasa();
        jLabel28 = new widget.Label();
        jLabel29 = new widget.Label();
        jLabel30 = new widget.Label();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        cmbFrekNafasA = new widget.ComboBox();
        cmbRetraksiA = new widget.ComboBox();
        cmbSianosisA = new widget.ComboBox();
        cmbMerintihA = new widget.ComboBox();
        jLabel148 = new widget.Label();
        TnilaiFrekA = new widget.TextBox();
        jLabel149 = new widget.Label();
        TnilaiRetraksiA = new widget.TextBox();
        jLabel150 = new widget.Label();
        TnilaiSianoA = new widget.TextBox();
        jLabel151 = new widget.Label();
        TnilaiAirA = new widget.TextBox();
        jLabel152 = new widget.Label();
        TnilaiMerintihA = new widget.TextBox();
        jLabel153 = new widget.Label();
        TJumlahA = new widget.TextBox();
        TmenitA = new widget.TextBox();
        cmbAirA = new widget.ComboBox();
        jLabel52 = new widget.Label();
        panelBiasa12 = new widget.PanelBiasa();
        jLabel34 = new widget.Label();
        jLabel35 = new widget.Label();
        jLabel36 = new widget.Label();
        jLabel37 = new widget.Label();
        jLabel38 = new widget.Label();
        jLabel39 = new widget.Label();
        jLabel154 = new widget.Label();
        TnilaiFrekB = new widget.TextBox();
        jLabel155 = new widget.Label();
        TnilaiRetraksiB = new widget.TextBox();
        jLabel156 = new widget.Label();
        TnilaiSianoB = new widget.TextBox();
        jLabel157 = new widget.Label();
        TnilaiAirB = new widget.TextBox();
        jLabel158 = new widget.Label();
        TnilaiMerintihB = new widget.TextBox();
        jLabel159 = new widget.Label();
        TJumlahB = new widget.TextBox();
        TmenitB = new widget.TextBox();
        cmbFrekNafasB = new widget.ComboBox();
        cmbRetraksiB = new widget.ComboBox();
        cmbSianosisB = new widget.ComboBox();
        cmbAirB = new widget.ComboBox();
        cmbMerintihB = new widget.ComboBox();
        jLabel53 = new widget.Label();
        panelBiasa13 = new widget.PanelBiasa();
        jLabel40 = new widget.Label();
        jLabel41 = new widget.Label();
        jLabel42 = new widget.Label();
        jLabel43 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        cmbAirC = new widget.ComboBox();
        jLabel160 = new widget.Label();
        TnilaiFrekC = new widget.TextBox();
        jLabel161 = new widget.Label();
        TnilaiRetraksiC = new widget.TextBox();
        jLabel162 = new widget.Label();
        TnilaiSianoC = new widget.TextBox();
        jLabel163 = new widget.Label();
        TnilaiAirC = new widget.TextBox();
        jLabel164 = new widget.Label();
        TnilaiMerintihC = new widget.TextBox();
        jLabel165 = new widget.Label();
        TJumlahC = new widget.TextBox();
        TmenitC = new widget.TextBox();
        cmbFrekNafasC = new widget.ComboBox();
        cmbRetraksiC = new widget.ComboBox();
        cmbSianosisC = new widget.ComboBox();
        cmbMerintihC = new widget.ComboBox();
        jLabel54 = new widget.Label();
        jLabel46 = new widget.Label();
        cmbEvaluasi = new widget.ComboBox();
        jLabel47 = new widget.Label();
        Ttgl = new widget.Tanggal();
        jLabel96 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel48 = new widget.Label();
        TnmPerawat = new widget.TextBox();
        BtnPerawat = new widget.Button();
        jLabel51 = new widget.Label();
        TtglLahir = new widget.TextBox();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSkor = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel73 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel49 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel50 = new widget.Label();
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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Skor Apgar, Skor Downe, Cap Jari Ibu Dan Bayi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 390));
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

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 407, 23);

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
        TrgRawat.setBounds(114, 38, 390, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("1. Apgar Score : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 110, 23);

        TabApgar.setBackground(new java.awt.Color(255, 255, 254));
        TabApgar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabApgar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabApgar.setName("TabApgar"); // NOI18N
        TabApgar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabApgarMouseClicked(evt);
            }
        });

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(null);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("TANDA : 1 '");
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N
        panelBiasa8.add(jLabel9);
        jLabel9.setBounds(0, 10, 125, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Frekuensi Jantung :");
        jLabel10.setName("jLabel10"); // NOI18N
        panelBiasa8.add(jLabel10);
        jLabel10.setBounds(0, 38, 110, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Usaha Nafas :");
        jLabel11.setName("jLabel11"); // NOI18N
        panelBiasa8.add(jLabel11);
        jLabel11.setBounds(0, 66, 110, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Tonus Otot :");
        jLabel12.setName("jLabel12"); // NOI18N
        panelBiasa8.add(jLabel12);
        jLabel12.setBounds(0, 94, 110, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Reflex :");
        jLabel13.setName("jLabel13"); // NOI18N
        panelBiasa8.add(jLabel13);
        jLabel13.setBounds(0, 122, 110, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Warna :");
        jLabel14.setName("jLabel14"); // NOI18N
        panelBiasa8.add(jLabel14);
        jLabel14.setBounds(0, 150, 110, 23);

        cmbFrek1.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrek1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "< 100", "> 100" }));
        cmbFrek1.setName("cmbFrek1"); // NOI18N
        cmbFrek1.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrek1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrek1ActionPerformed(evt);
            }
        });
        panelBiasa8.add(cmbFrek1);
        cmbFrek1.setBounds(115, 38, 80, 23);

        cmbUsaha1.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsaha1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "Lambat tak teratur", "Menangis kuat" }));
        cmbUsaha1.setName("cmbUsaha1"); // NOI18N
        cmbUsaha1.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUsaha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsaha1ActionPerformed(evt);
            }
        });
        panelBiasa8.add(cmbUsaha1);
        cmbUsaha1.setBounds(115, 66, 125, 23);

        cmbTonus1.setForeground(new java.awt.Color(0, 0, 0));
        cmbTonus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lumpuh", "Ext fleksi sedikit", "Gerakan aktif" }));
        cmbTonus1.setName("cmbTonus1"); // NOI18N
        cmbTonus1.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTonus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTonus1ActionPerformed(evt);
            }
        });
        panelBiasa8.add(cmbTonus1);
        cmbTonus1.setBounds(115, 94, 110, 23);

        cmbReflex1.setForeground(new java.awt.Color(0, 0, 0));
        cmbReflex1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada respon", "Pergerakan sedikit", "Menangis" }));
        cmbReflex1.setName("cmbReflex1"); // NOI18N
        cmbReflex1.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbReflex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReflex1ActionPerformed(evt);
            }
        });
        panelBiasa8.add(cmbReflex1);
        cmbReflex1.setBounds(115, 122, 120, 23);

        cmbWarna1.setForeground(new java.awt.Color(0, 0, 0));
        cmbWarna1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biru pucat", "Tubuh kemerahan tangan & kaki biru", "Kemerahan" }));
        cmbWarna1.setName("cmbWarna1"); // NOI18N
        cmbWarna1.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbWarna1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWarna1ActionPerformed(evt);
            }
        });
        panelBiasa8.add(cmbWarna1);
        cmbWarna1.setBounds(115, 150, 208, 23);

        jLabel130.setForeground(new java.awt.Color(0, 0, 0));
        jLabel130.setText("Nilai : ");
        jLabel130.setName("jLabel130"); // NOI18N
        panelBiasa8.add(jLabel130);
        jLabel130.setBounds(332, 38, 40, 23);

        TnilaiFrek1.setEditable(false);
        TnilaiFrek1.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrek1.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrek1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrek1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrek1.setName("TnilaiFrek1"); // NOI18N
        panelBiasa8.add(TnilaiFrek1);
        TnilaiFrek1.setBounds(374, 38, 40, 23);

        jLabel131.setForeground(new java.awt.Color(0, 0, 0));
        jLabel131.setText("Nilai : ");
        jLabel131.setName("jLabel131"); // NOI18N
        panelBiasa8.add(jLabel131);
        jLabel131.setBounds(332, 66, 40, 23);

        TnilaiUsaha1.setEditable(false);
        TnilaiUsaha1.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiUsaha1.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiUsaha1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiUsaha1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiUsaha1.setName("TnilaiUsaha1"); // NOI18N
        panelBiasa8.add(TnilaiUsaha1);
        TnilaiUsaha1.setBounds(374, 66, 40, 23);

        jLabel132.setForeground(new java.awt.Color(0, 0, 0));
        jLabel132.setText("Nilai : ");
        jLabel132.setName("jLabel132"); // NOI18N
        panelBiasa8.add(jLabel132);
        jLabel132.setBounds(332, 94, 40, 23);

        TnilaiTonus1.setEditable(false);
        TnilaiTonus1.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiTonus1.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiTonus1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiTonus1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiTonus1.setName("TnilaiTonus1"); // NOI18N
        panelBiasa8.add(TnilaiTonus1);
        TnilaiTonus1.setBounds(374, 94, 40, 23);

        jLabel133.setForeground(new java.awt.Color(0, 0, 0));
        jLabel133.setText("Nilai : ");
        jLabel133.setName("jLabel133"); // NOI18N
        panelBiasa8.add(jLabel133);
        jLabel133.setBounds(332, 122, 40, 23);

        TnilaiReflex1.setEditable(false);
        TnilaiReflex1.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiReflex1.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiReflex1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiReflex1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiReflex1.setName("TnilaiReflex1"); // NOI18N
        panelBiasa8.add(TnilaiReflex1);
        TnilaiReflex1.setBounds(374, 122, 40, 23);

        jLabel134.setForeground(new java.awt.Color(0, 0, 0));
        jLabel134.setText("Nilai : ");
        jLabel134.setName("jLabel134"); // NOI18N
        panelBiasa8.add(jLabel134);
        jLabel134.setBounds(332, 150, 40, 23);

        TnilaiWarna1.setEditable(false);
        TnilaiWarna1.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiWarna1.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiWarna1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiWarna1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiWarna1.setName("TnilaiWarna1"); // NOI18N
        panelBiasa8.add(TnilaiWarna1);
        TnilaiWarna1.setBounds(374, 150, 40, 23);

        jLabel135.setForeground(new java.awt.Color(0, 0, 0));
        jLabel135.setText("Jumlah Nilai : ");
        jLabel135.setName("jLabel135"); // NOI18N
        panelBiasa8.add(jLabel135);
        jLabel135.setBounds(282, 178, 90, 23);

        TJumlah1.setEditable(false);
        TJumlah1.setBackground(new java.awt.Color(245, 250, 240));
        TJumlah1.setForeground(new java.awt.Color(0, 0, 0));
        TJumlah1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlah1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlah1.setName("TJumlah1"); // NOI18N
        panelBiasa8.add(TJumlah1);
        TJumlah1.setBounds(374, 178, 40, 23);

        TabApgar.addTab("Nilai 1'", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(null);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Frekuensi Jantung :");
        jLabel16.setName("jLabel16"); // NOI18N
        panelBiasa9.add(jLabel16);
        jLabel16.setBounds(0, 38, 110, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Usaha Nafas :");
        jLabel17.setName("jLabel17"); // NOI18N
        panelBiasa9.add(jLabel17);
        jLabel17.setBounds(0, 66, 110, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tonus Otot :");
        jLabel18.setName("jLabel18"); // NOI18N
        panelBiasa9.add(jLabel18);
        jLabel18.setBounds(0, 94, 110, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Reflex :");
        jLabel19.setName("jLabel19"); // NOI18N
        panelBiasa9.add(jLabel19);
        jLabel19.setBounds(0, 122, 110, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Warna :");
        jLabel20.setName("jLabel20"); // NOI18N
        panelBiasa9.add(jLabel20);
        jLabel20.setBounds(0, 150, 110, 23);

        cmbFrek5.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrek5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "< 100", "> 100" }));
        cmbFrek5.setName("cmbFrek5"); // NOI18N
        cmbFrek5.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrek5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrek5ActionPerformed(evt);
            }
        });
        panelBiasa9.add(cmbFrek5);
        cmbFrek5.setBounds(115, 38, 80, 23);

        cmbUsaha5.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsaha5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "Lambat tak teratur", "Menangis kuat" }));
        cmbUsaha5.setName("cmbUsaha5"); // NOI18N
        cmbUsaha5.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUsaha5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsaha5ActionPerformed(evt);
            }
        });
        panelBiasa9.add(cmbUsaha5);
        cmbUsaha5.setBounds(115, 66, 125, 23);

        cmbTonus5.setForeground(new java.awt.Color(0, 0, 0));
        cmbTonus5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lumpuh", "Ext fleksi sedikit", "Gerakan aktif" }));
        cmbTonus5.setName("cmbTonus5"); // NOI18N
        cmbTonus5.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTonus5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTonus5ActionPerformed(evt);
            }
        });
        panelBiasa9.add(cmbTonus5);
        cmbTonus5.setBounds(115, 94, 110, 23);

        cmbReflex5.setForeground(new java.awt.Color(0, 0, 0));
        cmbReflex5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada respon", "Pergerakan sedikit", "Menangis" }));
        cmbReflex5.setName("cmbReflex5"); // NOI18N
        cmbReflex5.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbReflex5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReflex5ActionPerformed(evt);
            }
        });
        panelBiasa9.add(cmbReflex5);
        cmbReflex5.setBounds(115, 122, 120, 23);

        cmbWarna5.setForeground(new java.awt.Color(0, 0, 0));
        cmbWarna5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biru pucat", "Tubuh kemerahan tangan & kaki biru", "Kemerahan" }));
        cmbWarna5.setName("cmbWarna5"); // NOI18N
        cmbWarna5.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbWarna5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWarna5ActionPerformed(evt);
            }
        });
        panelBiasa9.add(cmbWarna5);
        cmbWarna5.setBounds(115, 150, 208, 23);

        jLabel136.setForeground(new java.awt.Color(0, 0, 0));
        jLabel136.setText("Nilai : ");
        jLabel136.setName("jLabel136"); // NOI18N
        panelBiasa9.add(jLabel136);
        jLabel136.setBounds(332, 38, 40, 23);

        TnilaiFrek5.setEditable(false);
        TnilaiFrek5.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrek5.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrek5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrek5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrek5.setName("TnilaiFrek5"); // NOI18N
        panelBiasa9.add(TnilaiFrek5);
        TnilaiFrek5.setBounds(374, 38, 40, 23);

        jLabel137.setForeground(new java.awt.Color(0, 0, 0));
        jLabel137.setText("Nilai : ");
        jLabel137.setName("jLabel137"); // NOI18N
        panelBiasa9.add(jLabel137);
        jLabel137.setBounds(332, 66, 40, 23);

        TnilaiUsaha5.setEditable(false);
        TnilaiUsaha5.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiUsaha5.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiUsaha5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiUsaha5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiUsaha5.setName("TnilaiUsaha5"); // NOI18N
        panelBiasa9.add(TnilaiUsaha5);
        TnilaiUsaha5.setBounds(374, 66, 40, 23);

        jLabel138.setForeground(new java.awt.Color(0, 0, 0));
        jLabel138.setText("Nilai : ");
        jLabel138.setName("jLabel138"); // NOI18N
        panelBiasa9.add(jLabel138);
        jLabel138.setBounds(332, 94, 40, 23);

        TnilaiTonus5.setEditable(false);
        TnilaiTonus5.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiTonus5.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiTonus5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiTonus5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiTonus5.setName("TnilaiTonus5"); // NOI18N
        panelBiasa9.add(TnilaiTonus5);
        TnilaiTonus5.setBounds(374, 94, 40, 23);

        jLabel139.setForeground(new java.awt.Color(0, 0, 0));
        jLabel139.setText("Nilai : ");
        jLabel139.setName("jLabel139"); // NOI18N
        panelBiasa9.add(jLabel139);
        jLabel139.setBounds(332, 122, 40, 23);

        TnilaiReflex5.setEditable(false);
        TnilaiReflex5.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiReflex5.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiReflex5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiReflex5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiReflex5.setName("TnilaiReflex5"); // NOI18N
        panelBiasa9.add(TnilaiReflex5);
        TnilaiReflex5.setBounds(374, 122, 40, 23);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Nilai : ");
        jLabel140.setName("jLabel140"); // NOI18N
        panelBiasa9.add(jLabel140);
        jLabel140.setBounds(332, 150, 40, 23);

        TnilaiWarna5.setEditable(false);
        TnilaiWarna5.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiWarna5.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiWarna5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiWarna5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiWarna5.setName("TnilaiWarna5"); // NOI18N
        panelBiasa9.add(TnilaiWarna5);
        TnilaiWarna5.setBounds(374, 150, 40, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Jumlah Nilai : ");
        jLabel141.setName("jLabel141"); // NOI18N
        panelBiasa9.add(jLabel141);
        jLabel141.setBounds(282, 178, 90, 23);

        TJumlah5.setEditable(false);
        TJumlah5.setBackground(new java.awt.Color(245, 250, 240));
        TJumlah5.setForeground(new java.awt.Color(0, 0, 0));
        TJumlah5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlah5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlah5.setName("TJumlah5"); // NOI18N
        panelBiasa9.add(TJumlah5);
        TJumlah5.setBounds(374, 178, 40, 23);

        jLabel55.setForeground(new java.awt.Color(0, 0, 0));
        jLabel55.setText("TANDA : 5 '");
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel55.setName("jLabel55"); // NOI18N
        panelBiasa9.add(jLabel55);
        jLabel55.setBounds(0, 10, 125, 23);

        TabApgar.addTab("Nilai 5'", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(null);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Frekuensi Jantung :");
        jLabel22.setName("jLabel22"); // NOI18N
        panelBiasa10.add(jLabel22);
        jLabel22.setBounds(0, 38, 110, 23);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Usaha Nafas :");
        jLabel23.setName("jLabel23"); // NOI18N
        panelBiasa10.add(jLabel23);
        jLabel23.setBounds(0, 66, 110, 23);

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Tonus Otot :");
        jLabel24.setName("jLabel24"); // NOI18N
        panelBiasa10.add(jLabel24);
        jLabel24.setBounds(0, 94, 110, 23);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Reflex :");
        jLabel25.setName("jLabel25"); // NOI18N
        panelBiasa10.add(jLabel25);
        jLabel25.setBounds(0, 122, 110, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Warna :");
        jLabel26.setName("jLabel26"); // NOI18N
        panelBiasa10.add(jLabel26);
        jLabel26.setBounds(0, 150, 110, 23);

        cmbFrek10.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrek10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "< 100", "> 100" }));
        cmbFrek10.setName("cmbFrek10"); // NOI18N
        cmbFrek10.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrek10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrek10ActionPerformed(evt);
            }
        });
        panelBiasa10.add(cmbFrek10);
        cmbFrek10.setBounds(115, 38, 80, 23);

        cmbUsaha10.setForeground(new java.awt.Color(0, 0, 0));
        cmbUsaha10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada", "Lambat tak teratur", "Menangis kuat" }));
        cmbUsaha10.setName("cmbUsaha10"); // NOI18N
        cmbUsaha10.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbUsaha10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsaha10ActionPerformed(evt);
            }
        });
        panelBiasa10.add(cmbUsaha10);
        cmbUsaha10.setBounds(115, 66, 125, 23);

        cmbTonus10.setForeground(new java.awt.Color(0, 0, 0));
        cmbTonus10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Lumpuh", "Ext fleksi sedikit", "Gerakan aktif" }));
        cmbTonus10.setName("cmbTonus10"); // NOI18N
        cmbTonus10.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbTonus10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTonus10ActionPerformed(evt);
            }
        });
        panelBiasa10.add(cmbTonus10);
        cmbTonus10.setBounds(115, 94, 110, 23);

        cmbReflex10.setForeground(new java.awt.Color(0, 0, 0));
        cmbReflex10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak ada respon", "Pergerakan sedikit", "Menangis" }));
        cmbReflex10.setName("cmbReflex10"); // NOI18N
        cmbReflex10.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbReflex10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReflex10ActionPerformed(evt);
            }
        });
        panelBiasa10.add(cmbReflex10);
        cmbReflex10.setBounds(115, 122, 120, 23);

        cmbWarna10.setForeground(new java.awt.Color(0, 0, 0));
        cmbWarna10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Biru pucat", "Tubuh kemerahan tangan & kaki biru", "Kemerahan" }));
        cmbWarna10.setName("cmbWarna10"); // NOI18N
        cmbWarna10.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbWarna10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbWarna10ActionPerformed(evt);
            }
        });
        panelBiasa10.add(cmbWarna10);
        cmbWarna10.setBounds(115, 150, 208, 23);

        jLabel142.setForeground(new java.awt.Color(0, 0, 0));
        jLabel142.setText("Nilai : ");
        jLabel142.setName("jLabel142"); // NOI18N
        panelBiasa10.add(jLabel142);
        jLabel142.setBounds(332, 38, 40, 23);

        TnilaiFrek10.setEditable(false);
        TnilaiFrek10.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrek10.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrek10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrek10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrek10.setName("TnilaiFrek10"); // NOI18N
        panelBiasa10.add(TnilaiFrek10);
        TnilaiFrek10.setBounds(374, 38, 40, 23);

        jLabel143.setForeground(new java.awt.Color(0, 0, 0));
        jLabel143.setText("Nilai : ");
        jLabel143.setName("jLabel143"); // NOI18N
        panelBiasa10.add(jLabel143);
        jLabel143.setBounds(332, 66, 40, 23);

        TnilaiUsaha10.setEditable(false);
        TnilaiUsaha10.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiUsaha10.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiUsaha10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiUsaha10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiUsaha10.setName("TnilaiUsaha10"); // NOI18N
        panelBiasa10.add(TnilaiUsaha10);
        TnilaiUsaha10.setBounds(374, 66, 40, 23);

        jLabel144.setForeground(new java.awt.Color(0, 0, 0));
        jLabel144.setText("Nilai : ");
        jLabel144.setName("jLabel144"); // NOI18N
        panelBiasa10.add(jLabel144);
        jLabel144.setBounds(332, 94, 40, 23);

        TnilaiTonus10.setEditable(false);
        TnilaiTonus10.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiTonus10.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiTonus10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiTonus10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiTonus10.setName("TnilaiTonus10"); // NOI18N
        panelBiasa10.add(TnilaiTonus10);
        TnilaiTonus10.setBounds(374, 94, 40, 23);

        jLabel145.setForeground(new java.awt.Color(0, 0, 0));
        jLabel145.setText("Nilai : ");
        jLabel145.setName("jLabel145"); // NOI18N
        panelBiasa10.add(jLabel145);
        jLabel145.setBounds(332, 122, 40, 23);

        TnilaiReflex10.setEditable(false);
        TnilaiReflex10.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiReflex10.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiReflex10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiReflex10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiReflex10.setName("TnilaiReflex10"); // NOI18N
        panelBiasa10.add(TnilaiReflex10);
        TnilaiReflex10.setBounds(374, 122, 40, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Nilai : ");
        jLabel146.setName("jLabel146"); // NOI18N
        panelBiasa10.add(jLabel146);
        jLabel146.setBounds(332, 150, 40, 23);

        TnilaiWarna10.setEditable(false);
        TnilaiWarna10.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiWarna10.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiWarna10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiWarna10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiWarna10.setName("TnilaiWarna10"); // NOI18N
        panelBiasa10.add(TnilaiWarna10);
        TnilaiWarna10.setBounds(374, 150, 40, 23);

        jLabel147.setForeground(new java.awt.Color(0, 0, 0));
        jLabel147.setText("Jumlah Nilai : ");
        jLabel147.setName("jLabel147"); // NOI18N
        panelBiasa10.add(jLabel147);
        jLabel147.setBounds(282, 178, 90, 23);

        TJumlah10.setEditable(false);
        TJumlah10.setBackground(new java.awt.Color(245, 250, 240));
        TJumlah10.setForeground(new java.awt.Color(0, 0, 0));
        TJumlah10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlah10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlah10.setName("TJumlah10"); // NOI18N
        panelBiasa10.add(TJumlah10);
        TJumlah10.setBounds(374, 178, 40, 23);

        jLabel56.setForeground(new java.awt.Color(0, 0, 0));
        jLabel56.setText("TANDA : 10 '");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel56.setName("jLabel56"); // NOI18N
        panelBiasa10.add(jLabel56);
        jLabel56.setBounds(0, 10, 132, 23);

        TabApgar.addTab("Nilai 10'", panelBiasa10);

        FormInput.add(TabApgar);
        TabApgar.setBounds(114, 66, 440, 250);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("2. Downe Score :");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(530, 66, 130, 23);

        TabDowne.setBackground(new java.awt.Color(255, 255, 254));
        TabDowne.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabDowne.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabDowne.setName("TabDowne"); // NOI18N
        TabDowne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabDowneMouseClicked(evt);
            }
        });

        panelBiasa11.setName("panelBiasa11"); // NOI18N
        panelBiasa11.setLayout(null);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Menit Ke :");
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N
        panelBiasa11.add(jLabel28);
        jLabel28.setBounds(0, 10, 110, 23);

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Frekuensi Nafas :");
        jLabel29.setName("jLabel29"); // NOI18N
        panelBiasa11.add(jLabel29);
        jLabel29.setBounds(0, 38, 110, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Retraksi :");
        jLabel30.setName("jLabel30"); // NOI18N
        panelBiasa11.add(jLabel30);
        jLabel30.setBounds(0, 66, 110, 23);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Sianosis :");
        jLabel31.setName("jLabel31"); // NOI18N
        panelBiasa11.add(jLabel31);
        jLabel31.setBounds(0, 94, 110, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Air Entry :");
        jLabel32.setName("jLabel32"); // NOI18N
        panelBiasa11.add(jLabel32);
        jLabel32.setBounds(0, 122, 110, 23);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Merintih :");
        jLabel33.setName("jLabel33"); // NOI18N
        panelBiasa11.add(jLabel33);
        jLabel33.setBounds(0, 150, 110, 23);

        cmbFrekNafasA.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrekNafasA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 60 x/menit", "60 - 80 x/menit", "> 80 x/menit" }));
        cmbFrekNafasA.setName("cmbFrekNafasA"); // NOI18N
        cmbFrekNafasA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrekNafasA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrekNafasAActionPerformed(evt);
            }
        });
        panelBiasa11.add(cmbFrekNafasA);
        cmbFrekNafasA.setBounds(115, 38, 105, 23);

        cmbRetraksiA.setForeground(new java.awt.Color(0, 0, 0));
        cmbRetraksiA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Retraksi (-)", "Retraksi ringan", "Retraksi berat" }));
        cmbRetraksiA.setName("cmbRetraksiA"); // NOI18N
        cmbRetraksiA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRetraksiA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRetraksiAActionPerformed(evt);
            }
        });
        panelBiasa11.add(cmbRetraksiA);
        cmbRetraksiA.setBounds(115, 66, 105, 23);

        cmbSianosisA.setForeground(new java.awt.Color(0, 0, 0));
        cmbSianosisA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sianosis (-)", "Sianosis hilang dengan oksigen", "Sianosis menetap dengan oksigen" }));
        cmbSianosisA.setName("cmbSianosisA"); // NOI18N
        cmbSianosisA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSianosisA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSianosisAActionPerformed(evt);
            }
        });
        panelBiasa11.add(cmbSianosisA);
        cmbSianosisA.setBounds(115, 94, 195, 23);

        cmbMerintihA.setForeground(new java.awt.Color(0, 0, 0));
        cmbMerintihA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak merintih", "Dapat didengar dengan stetoskop", "Dapat didengar tanpa alat bantu" }));
        cmbMerintihA.setName("cmbMerintihA"); // NOI18N
        cmbMerintihA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMerintihA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerintihAActionPerformed(evt);
            }
        });
        panelBiasa11.add(cmbMerintihA);
        cmbMerintihA.setBounds(115, 150, 195, 23);

        jLabel148.setForeground(new java.awt.Color(0, 0, 0));
        jLabel148.setText("Nilai : ");
        jLabel148.setName("jLabel148"); // NOI18N
        panelBiasa11.add(jLabel148);
        jLabel148.setBounds(332, 38, 40, 23);

        TnilaiFrekA.setEditable(false);
        TnilaiFrekA.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrekA.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrekA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrekA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrekA.setName("TnilaiFrekA"); // NOI18N
        panelBiasa11.add(TnilaiFrekA);
        TnilaiFrekA.setBounds(374, 38, 40, 23);

        jLabel149.setForeground(new java.awt.Color(0, 0, 0));
        jLabel149.setText("Nilai : ");
        jLabel149.setName("jLabel149"); // NOI18N
        panelBiasa11.add(jLabel149);
        jLabel149.setBounds(332, 66, 40, 23);

        TnilaiRetraksiA.setEditable(false);
        TnilaiRetraksiA.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiRetraksiA.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiRetraksiA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiRetraksiA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiRetraksiA.setName("TnilaiRetraksiA"); // NOI18N
        panelBiasa11.add(TnilaiRetraksiA);
        TnilaiRetraksiA.setBounds(374, 66, 40, 23);

        jLabel150.setForeground(new java.awt.Color(0, 0, 0));
        jLabel150.setText("Nilai : ");
        jLabel150.setName("jLabel150"); // NOI18N
        panelBiasa11.add(jLabel150);
        jLabel150.setBounds(332, 94, 40, 23);

        TnilaiSianoA.setEditable(false);
        TnilaiSianoA.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSianoA.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSianoA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSianoA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSianoA.setName("TnilaiSianoA"); // NOI18N
        panelBiasa11.add(TnilaiSianoA);
        TnilaiSianoA.setBounds(374, 94, 40, 23);

        jLabel151.setForeground(new java.awt.Color(0, 0, 0));
        jLabel151.setText("Nilai : ");
        jLabel151.setName("jLabel151"); // NOI18N
        panelBiasa11.add(jLabel151);
        jLabel151.setBounds(332, 122, 40, 23);

        TnilaiAirA.setEditable(false);
        TnilaiAirA.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiAirA.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiAirA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiAirA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiAirA.setName("TnilaiAirA"); // NOI18N
        panelBiasa11.add(TnilaiAirA);
        TnilaiAirA.setBounds(374, 122, 40, 23);

        jLabel152.setForeground(new java.awt.Color(0, 0, 0));
        jLabel152.setText("Nilai : ");
        jLabel152.setName("jLabel152"); // NOI18N
        panelBiasa11.add(jLabel152);
        jLabel152.setBounds(332, 150, 40, 23);

        TnilaiMerintihA.setEditable(false);
        TnilaiMerintihA.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiMerintihA.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMerintihA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiMerintihA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiMerintihA.setName("TnilaiMerintihA"); // NOI18N
        panelBiasa11.add(TnilaiMerintihA);
        TnilaiMerintihA.setBounds(374, 150, 40, 23);

        jLabel153.setForeground(new java.awt.Color(0, 0, 0));
        jLabel153.setText("Jumlah Nilai : ");
        jLabel153.setName("jLabel153"); // NOI18N
        panelBiasa11.add(jLabel153);
        jLabel153.setBounds(282, 178, 90, 23);

        TJumlahA.setEditable(false);
        TJumlahA.setBackground(new java.awt.Color(245, 250, 240));
        TJumlahA.setForeground(new java.awt.Color(0, 0, 0));
        TJumlahA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlahA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlahA.setName("TJumlahA"); // NOI18N
        panelBiasa11.add(TJumlahA);
        TJumlahA.setBounds(374, 178, 40, 23);

        TmenitA.setBackground(new java.awt.Color(245, 250, 240));
        TmenitA.setForeground(new java.awt.Color(0, 0, 0));
        TmenitA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TmenitA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TmenitA.setName("TmenitA"); // NOI18N
        panelBiasa11.add(TmenitA);
        TmenitA.setBounds(115, 10, 50, 23);

        cmbAirA.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Udara masuk", "Penurunan ringan udara masuk", "Tidak ada udara masuk" }));
        cmbAirA.setName("cmbAirA"); // NOI18N
        cmbAirA.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAirA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAirAActionPerformed(evt);
            }
        });
        panelBiasa11.add(cmbAirA);
        cmbAirA.setBounds(115, 122, 185, 23);

        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Pemeriksaan Ke 1");
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel52.setName("jLabel52"); // NOI18N
        panelBiasa11.add(jLabel52);
        jLabel52.setBounds(0, 178, 280, 23);

        TabDowne.addTab("Pemeriksaan Ke 1", panelBiasa11);

        panelBiasa12.setName("panelBiasa12"); // NOI18N
        panelBiasa12.setLayout(null);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Menit Ke :");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel34.setName("jLabel34"); // NOI18N
        panelBiasa12.add(jLabel34);
        jLabel34.setBounds(0, 10, 110, 23);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Frekuensi Nafas :");
        jLabel35.setName("jLabel35"); // NOI18N
        panelBiasa12.add(jLabel35);
        jLabel35.setBounds(0, 38, 110, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Retraksi :");
        jLabel36.setName("jLabel36"); // NOI18N
        panelBiasa12.add(jLabel36);
        jLabel36.setBounds(0, 66, 110, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Sianosis :");
        jLabel37.setName("jLabel37"); // NOI18N
        panelBiasa12.add(jLabel37);
        jLabel37.setBounds(0, 94, 110, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Air Entry :");
        jLabel38.setName("jLabel38"); // NOI18N
        panelBiasa12.add(jLabel38);
        jLabel38.setBounds(0, 122, 110, 23);

        jLabel39.setForeground(new java.awt.Color(0, 0, 0));
        jLabel39.setText("Merintih :");
        jLabel39.setName("jLabel39"); // NOI18N
        panelBiasa12.add(jLabel39);
        jLabel39.setBounds(0, 150, 110, 23);

        jLabel154.setForeground(new java.awt.Color(0, 0, 0));
        jLabel154.setText("Nilai : ");
        jLabel154.setName("jLabel154"); // NOI18N
        panelBiasa12.add(jLabel154);
        jLabel154.setBounds(332, 38, 40, 23);

        TnilaiFrekB.setEditable(false);
        TnilaiFrekB.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrekB.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrekB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrekB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrekB.setName("TnilaiFrekB"); // NOI18N
        panelBiasa12.add(TnilaiFrekB);
        TnilaiFrekB.setBounds(374, 38, 40, 23);

        jLabel155.setForeground(new java.awt.Color(0, 0, 0));
        jLabel155.setText("Nilai : ");
        jLabel155.setName("jLabel155"); // NOI18N
        panelBiasa12.add(jLabel155);
        jLabel155.setBounds(332, 66, 40, 23);

        TnilaiRetraksiB.setEditable(false);
        TnilaiRetraksiB.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiRetraksiB.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiRetraksiB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiRetraksiB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiRetraksiB.setName("TnilaiRetraksiB"); // NOI18N
        panelBiasa12.add(TnilaiRetraksiB);
        TnilaiRetraksiB.setBounds(374, 66, 40, 23);

        jLabel156.setForeground(new java.awt.Color(0, 0, 0));
        jLabel156.setText("Nilai : ");
        jLabel156.setName("jLabel156"); // NOI18N
        panelBiasa12.add(jLabel156);
        jLabel156.setBounds(332, 94, 40, 23);

        TnilaiSianoB.setEditable(false);
        TnilaiSianoB.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSianoB.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSianoB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSianoB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSianoB.setName("TnilaiSianoB"); // NOI18N
        panelBiasa12.add(TnilaiSianoB);
        TnilaiSianoB.setBounds(374, 94, 40, 23);

        jLabel157.setForeground(new java.awt.Color(0, 0, 0));
        jLabel157.setText("Nilai : ");
        jLabel157.setName("jLabel157"); // NOI18N
        panelBiasa12.add(jLabel157);
        jLabel157.setBounds(332, 122, 40, 23);

        TnilaiAirB.setEditable(false);
        TnilaiAirB.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiAirB.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiAirB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiAirB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiAirB.setName("TnilaiAirB"); // NOI18N
        panelBiasa12.add(TnilaiAirB);
        TnilaiAirB.setBounds(374, 122, 40, 23);

        jLabel158.setForeground(new java.awt.Color(0, 0, 0));
        jLabel158.setText("Nilai : ");
        jLabel158.setName("jLabel158"); // NOI18N
        panelBiasa12.add(jLabel158);
        jLabel158.setBounds(332, 150, 40, 23);

        TnilaiMerintihB.setEditable(false);
        TnilaiMerintihB.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiMerintihB.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMerintihB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiMerintihB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiMerintihB.setName("TnilaiMerintihB"); // NOI18N
        panelBiasa12.add(TnilaiMerintihB);
        TnilaiMerintihB.setBounds(374, 150, 40, 23);

        jLabel159.setForeground(new java.awt.Color(0, 0, 0));
        jLabel159.setText("Jumlah Nilai : ");
        jLabel159.setName("jLabel159"); // NOI18N
        panelBiasa12.add(jLabel159);
        jLabel159.setBounds(282, 178, 90, 23);

        TJumlahB.setEditable(false);
        TJumlahB.setBackground(new java.awt.Color(245, 250, 240));
        TJumlahB.setForeground(new java.awt.Color(0, 0, 0));
        TJumlahB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlahB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlahB.setName("TJumlahB"); // NOI18N
        panelBiasa12.add(TJumlahB);
        TJumlahB.setBounds(374, 178, 40, 23);

        TmenitB.setBackground(new java.awt.Color(245, 250, 240));
        TmenitB.setForeground(new java.awt.Color(0, 0, 0));
        TmenitB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TmenitB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TmenitB.setName("TmenitB"); // NOI18N
        panelBiasa12.add(TmenitB);
        TmenitB.setBounds(115, 10, 50, 23);

        cmbFrekNafasB.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrekNafasB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 60 x/menit", "60 - 80 x/menit", "> 80 x/menit" }));
        cmbFrekNafasB.setName("cmbFrekNafasB"); // NOI18N
        cmbFrekNafasB.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrekNafasB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrekNafasBActionPerformed(evt);
            }
        });
        panelBiasa12.add(cmbFrekNafasB);
        cmbFrekNafasB.setBounds(115, 38, 105, 23);

        cmbRetraksiB.setForeground(new java.awt.Color(0, 0, 0));
        cmbRetraksiB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Retraksi (-)", "Retraksi ringan", "Retraksi berat" }));
        cmbRetraksiB.setName("cmbRetraksiB"); // NOI18N
        cmbRetraksiB.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRetraksiB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRetraksiBActionPerformed(evt);
            }
        });
        panelBiasa12.add(cmbRetraksiB);
        cmbRetraksiB.setBounds(115, 66, 105, 23);

        cmbSianosisB.setForeground(new java.awt.Color(0, 0, 0));
        cmbSianosisB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sianosis (-)", "Sianosis hilang dengan oksigen", "Sianosis menetap dengan oksigen" }));
        cmbSianosisB.setName("cmbSianosisB"); // NOI18N
        cmbSianosisB.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSianosisB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSianosisBActionPerformed(evt);
            }
        });
        panelBiasa12.add(cmbSianosisB);
        cmbSianosisB.setBounds(115, 94, 195, 23);

        cmbAirB.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Udara masuk", "Penurunan ringan udara masuk", "Tidak ada udara masuk" }));
        cmbAirB.setName("cmbAirB"); // NOI18N
        cmbAirB.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAirB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAirBActionPerformed(evt);
            }
        });
        panelBiasa12.add(cmbAirB);
        cmbAirB.setBounds(115, 122, 185, 23);

        cmbMerintihB.setForeground(new java.awt.Color(0, 0, 0));
        cmbMerintihB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak merintih", "Dapat didengar dengan stetoskop", "Dapat didengar tanpa alat bantu" }));
        cmbMerintihB.setName("cmbMerintihB"); // NOI18N
        cmbMerintihB.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMerintihB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerintihBActionPerformed(evt);
            }
        });
        panelBiasa12.add(cmbMerintihB);
        cmbMerintihB.setBounds(115, 150, 195, 23);

        jLabel53.setForeground(new java.awt.Color(0, 0, 0));
        jLabel53.setText("Pemeriksaan Ke 2");
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel53.setName("jLabel53"); // NOI18N
        panelBiasa12.add(jLabel53);
        jLabel53.setBounds(0, 178, 280, 23);

        TabDowne.addTab("Pemeriksaan Ke 2", panelBiasa12);

        panelBiasa13.setName("panelBiasa13"); // NOI18N
        panelBiasa13.setLayout(null);

        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("Menit Ke :");
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setName("jLabel40"); // NOI18N
        panelBiasa13.add(jLabel40);
        jLabel40.setBounds(0, 10, 110, 23);

        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("Frekuensi Nafas :");
        jLabel41.setName("jLabel41"); // NOI18N
        panelBiasa13.add(jLabel41);
        jLabel41.setBounds(0, 38, 110, 23);

        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("Retraksi :");
        jLabel42.setName("jLabel42"); // NOI18N
        panelBiasa13.add(jLabel42);
        jLabel42.setBounds(0, 66, 110, 23);

        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Sianosis :");
        jLabel43.setName("jLabel43"); // NOI18N
        panelBiasa13.add(jLabel43);
        jLabel43.setBounds(0, 94, 110, 23);

        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Air Entry :");
        jLabel44.setName("jLabel44"); // NOI18N
        panelBiasa13.add(jLabel44);
        jLabel44.setBounds(0, 122, 110, 23);

        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("Merintih :");
        jLabel45.setName("jLabel45"); // NOI18N
        panelBiasa13.add(jLabel45);
        jLabel45.setBounds(0, 150, 110, 23);

        cmbAirC.setForeground(new java.awt.Color(0, 0, 0));
        cmbAirC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Udara masuk", "Penurunan ringan udara masuk", "Tidak ada udara masuk" }));
        cmbAirC.setName("cmbAirC"); // NOI18N
        cmbAirC.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbAirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAirCActionPerformed(evt);
            }
        });
        panelBiasa13.add(cmbAirC);
        cmbAirC.setBounds(115, 122, 185, 23);

        jLabel160.setForeground(new java.awt.Color(0, 0, 0));
        jLabel160.setText("Nilai : ");
        jLabel160.setName("jLabel160"); // NOI18N
        panelBiasa13.add(jLabel160);
        jLabel160.setBounds(332, 38, 40, 23);

        TnilaiFrekC.setEditable(false);
        TnilaiFrekC.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiFrekC.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiFrekC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiFrekC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiFrekC.setName("TnilaiFrekC"); // NOI18N
        panelBiasa13.add(TnilaiFrekC);
        TnilaiFrekC.setBounds(374, 38, 40, 23);

        jLabel161.setForeground(new java.awt.Color(0, 0, 0));
        jLabel161.setText("Nilai : ");
        jLabel161.setName("jLabel161"); // NOI18N
        panelBiasa13.add(jLabel161);
        jLabel161.setBounds(332, 66, 40, 23);

        TnilaiRetraksiC.setEditable(false);
        TnilaiRetraksiC.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiRetraksiC.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiRetraksiC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiRetraksiC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiRetraksiC.setName("TnilaiRetraksiC"); // NOI18N
        panelBiasa13.add(TnilaiRetraksiC);
        TnilaiRetraksiC.setBounds(374, 66, 40, 23);

        jLabel162.setForeground(new java.awt.Color(0, 0, 0));
        jLabel162.setText("Nilai : ");
        jLabel162.setName("jLabel162"); // NOI18N
        panelBiasa13.add(jLabel162);
        jLabel162.setBounds(332, 94, 40, 23);

        TnilaiSianoC.setEditable(false);
        TnilaiSianoC.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiSianoC.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiSianoC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiSianoC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiSianoC.setName("TnilaiSianoC"); // NOI18N
        panelBiasa13.add(TnilaiSianoC);
        TnilaiSianoC.setBounds(374, 94, 40, 23);

        jLabel163.setForeground(new java.awt.Color(0, 0, 0));
        jLabel163.setText("Nilai : ");
        jLabel163.setName("jLabel163"); // NOI18N
        panelBiasa13.add(jLabel163);
        jLabel163.setBounds(332, 122, 40, 23);

        TnilaiAirC.setEditable(false);
        TnilaiAirC.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiAirC.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiAirC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiAirC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiAirC.setName("TnilaiAirC"); // NOI18N
        panelBiasa13.add(TnilaiAirC);
        TnilaiAirC.setBounds(374, 122, 40, 23);

        jLabel164.setForeground(new java.awt.Color(0, 0, 0));
        jLabel164.setText("Nilai : ");
        jLabel164.setName("jLabel164"); // NOI18N
        panelBiasa13.add(jLabel164);
        jLabel164.setBounds(332, 150, 40, 23);

        TnilaiMerintihC.setEditable(false);
        TnilaiMerintihC.setBackground(new java.awt.Color(245, 250, 240));
        TnilaiMerintihC.setForeground(new java.awt.Color(0, 0, 0));
        TnilaiMerintihC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TnilaiMerintihC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TnilaiMerintihC.setName("TnilaiMerintihC"); // NOI18N
        panelBiasa13.add(TnilaiMerintihC);
        TnilaiMerintihC.setBounds(374, 150, 40, 23);

        jLabel165.setForeground(new java.awt.Color(0, 0, 0));
        jLabel165.setText("Jumlah Nilai : ");
        jLabel165.setName("jLabel165"); // NOI18N
        panelBiasa13.add(jLabel165);
        jLabel165.setBounds(282, 178, 90, 23);

        TJumlahC.setEditable(false);
        TJumlahC.setBackground(new java.awt.Color(245, 250, 240));
        TJumlahC.setForeground(new java.awt.Color(0, 0, 0));
        TJumlahC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TJumlahC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TJumlahC.setName("TJumlahC"); // NOI18N
        panelBiasa13.add(TJumlahC);
        TJumlahC.setBounds(374, 178, 40, 23);

        TmenitC.setBackground(new java.awt.Color(245, 250, 240));
        TmenitC.setForeground(new java.awt.Color(0, 0, 0));
        TmenitC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TmenitC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TmenitC.setName("TmenitC"); // NOI18N
        panelBiasa13.add(TmenitC);
        TmenitC.setBounds(115, 10, 50, 23);

        cmbFrekNafasC.setForeground(new java.awt.Color(0, 0, 0));
        cmbFrekNafasC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "< 60 x/menit", "60 - 80 x/menit", "> 80 x/menit" }));
        cmbFrekNafasC.setName("cmbFrekNafasC"); // NOI18N
        cmbFrekNafasC.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbFrekNafasC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFrekNafasCActionPerformed(evt);
            }
        });
        panelBiasa13.add(cmbFrekNafasC);
        cmbFrekNafasC.setBounds(115, 38, 105, 23);

        cmbRetraksiC.setForeground(new java.awt.Color(0, 0, 0));
        cmbRetraksiC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Retraksi (-)", "Retraksi ringan", "Retraksi berat" }));
        cmbRetraksiC.setName("cmbRetraksiC"); // NOI18N
        cmbRetraksiC.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbRetraksiC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRetraksiCActionPerformed(evt);
            }
        });
        panelBiasa13.add(cmbRetraksiC);
        cmbRetraksiC.setBounds(115, 66, 105, 23);

        cmbSianosisC.setForeground(new java.awt.Color(0, 0, 0));
        cmbSianosisC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Sianosis (-)", "Sianosis hilang dengan oksigen", "Sianosis menetap dengan oksigen" }));
        cmbSianosisC.setName("cmbSianosisC"); // NOI18N
        cmbSianosisC.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSianosisC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSianosisCActionPerformed(evt);
            }
        });
        panelBiasa13.add(cmbSianosisC);
        cmbSianosisC.setBounds(115, 94, 195, 23);

        cmbMerintihC.setForeground(new java.awt.Color(0, 0, 0));
        cmbMerintihC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Tidak merintih", "Dapat didengar dengan stetoskop", "Dapat didengar tanpa alat bantu" }));
        cmbMerintihC.setName("cmbMerintihC"); // NOI18N
        cmbMerintihC.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbMerintihC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMerintihCActionPerformed(evt);
            }
        });
        panelBiasa13.add(cmbMerintihC);
        cmbMerintihC.setBounds(115, 150, 195, 23);

        jLabel54.setForeground(new java.awt.Color(0, 0, 0));
        jLabel54.setText("Pemeriksaan Ke 3");
        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel54.setName("jLabel54"); // NOI18N
        panelBiasa13.add(jLabel54);
        jLabel54.setBounds(0, 178, 280, 23);

        TabDowne.addTab("Pemeriksaan Ke 3", panelBiasa13);

        FormInput.add(TabDowne);
        TabDowne.setBounds(665, 66, 440, 250);

        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Evaluasi Downe Score :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(530, 322, 130, 23);

        cmbEvaluasi.setForeground(new java.awt.Color(0, 0, 0));
        cmbEvaluasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "1 - 3 : Sesak Nafas Ringan", "4 - 5 : Sesak Nafas Sedang", ">= 6 : Sesak Nafas Berat" }));
        cmbEvaluasi.setName("cmbEvaluasi"); // NOI18N
        cmbEvaluasi.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(cmbEvaluasi);
        cmbEvaluasi.setBounds(665, 322, 165, 23);

        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Tanggal : ");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(0, 322, 110, 23);

        Ttgl.setEditable(false);
        Ttgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2025" }));
        Ttgl.setDisplayFormat("dd-MM-yyyy");
        Ttgl.setName("Ttgl"); // NOI18N
        Ttgl.setOpaque(false);
        Ttgl.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(Ttgl);
        Ttgl.setBounds(114, 322, 90, 23);

        jLabel96.setForeground(new java.awt.Color(0, 0, 0));
        jLabel96.setText("Jam : ");
        jLabel96.setName("jLabel96"); // NOI18N
        FormInput.add(jLabel96);
        jLabel96.setBounds(220, 322, 40, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(263, 322, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(315, 322, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(368, 322, 45, 23);

        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setText("Nama Perawat : ");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(0, 350, 110, 23);

        TnmPerawat.setEditable(false);
        TnmPerawat.setForeground(new java.awt.Color(0, 0, 0));
        TnmPerawat.setToolTipText("Alt+C");
        TnmPerawat.setName("TnmPerawat"); // NOI18N
        TnmPerawat.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPerawat);
        TnmPerawat.setBounds(114, 350, 430, 23);

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
        BtnPerawat.setBounds(545, 350, 28, 23);

        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Tgl. Lahir :");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(504, 38, 65, 23);

        TtglLahir.setEditable(false);
        TtglLahir.setBackground(new java.awt.Color(245, 250, 240));
        TtglLahir.setForeground(new java.awt.Color(0, 0, 0));
        TtglLahir.setName("TtglLahir"); // NOI18N
        FormInput.add(TtglLahir);
        TtglLahir.setBounds(572, 38, 150, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSkor.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbSkor.setName("tbSkor"); // NOI18N
        tbSkor.getTableHeader().setReorderingAllowed(false);
        tbSkor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSkorMouseClicked(evt);
            }
        });
        tbSkor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSkorKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSkor);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jLabel73.setForeground(new java.awt.Color(0, 0, 0));
        jLabel73.setText("Cetak Dalam Bentuk :");
        jLabel73.setName("jLabel73"); // NOI18N
        jLabel73.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel73);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Tgl. Skoring :");
        jLabel49.setName("jLabel49"); // NOI18N
        jLabel49.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel49);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setText("s.d.");
        jLabel50.setName("jLabel50"); // NOI18N
        jLabel50.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel50);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10-02-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (Sequel.menyimpantf("skor_apgar_downe_cap_jari_perinatologi", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 40, new String[]{
                        TNoRw.getText(), TrgRawat.getText(), cmbFrek1.getSelectedItem().toString(), cmbUsaha1.getSelectedItem().toString(), cmbTonus1.getSelectedItem().toString(),
                        cmbReflex1.getSelectedItem().toString(), cmbWarna1.getSelectedItem().toString(), cmbFrek5.getSelectedItem().toString(), cmbUsaha5.getSelectedItem().toString(), 
                        cmbTonus5.getSelectedItem().toString(), cmbReflex5.getSelectedItem().toString(), cmbWarna5.getSelectedItem().toString(), cmbFrek10.getSelectedItem().toString(),
                        cmbUsaha10.getSelectedItem().toString(), cmbTonus10.getSelectedItem().toString(), cmbReflex10.getSelectedItem().toString(), cmbWarna10.getSelectedItem().toString(), 
                        TmenitA.getText(), TmenitB.getText(), TmenitC.getText(), cmbFrekNafasA.getSelectedItem().toString(), cmbRetraksiA.getSelectedItem().toString(),
                        cmbSianosisA.getSelectedItem().toString(), cmbAirA.getSelectedItem().toString(), cmbMerintihA.getSelectedItem().toString(), cmbFrekNafasB.getSelectedItem().toString(),
                        cmbRetraksiB.getSelectedItem().toString(), cmbSianosisB.getSelectedItem().toString(), cmbAirB.getSelectedItem().toString(), cmbMerintihB.getSelectedItem().toString(),
                        cmbFrekNafasC.getSelectedItem().toString(), cmbRetraksiC.getSelectedItem().toString(), cmbSianosisC.getSelectedItem().toString(), cmbAirC.getSelectedItem().toString(), 
                        cmbMerintihC.getSelectedItem().toString(), cmbEvaluasi.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""), 
                        cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), nip, Sequel.cariIsi("select now()")
                    }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Skor Apgar, Skor Downe, Cap Jari Ibu Dan Bayi", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();                
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
        if (TNoRw.getText().trim().equals("") || TPasien.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbSkor.getSelectedRow() > -1) {
                if (akses.getadmin() == true) {
                    ganti();
                } else {
                    if (nip.equals(akses.getkode())) {
                        ganti();
                    } else {
                        JOptionPane.showMessageDialog(null, "Hanya bisa diganti oleh perawat yang bernama " + tbSkor.getValueAt(tbSkor.getSelectedRow(), 9).toString() + " ..!!");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
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

    private void tbSkorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSkorMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbSkorMouseClicked

    private void tbSkorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSkorKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbSkorKeyPressed

    private void TabApgarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabApgarMouseClicked
        if (TabApgar.getSelectedIndex() == 0) {
            hitungApgar1();
        } else if (TabApgar.getSelectedIndex() == 1) {
            hitungApgar5();
        } else if (TabApgar.getSelectedIndex() == 2) {
            hitungApgar10();
        }
    }//GEN-LAST:event_TabApgarMouseClicked

    private void cmbFrek1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrek1ActionPerformed
        hitungApgar1();
    }//GEN-LAST:event_cmbFrek1ActionPerformed

    private void cmbUsaha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsaha1ActionPerformed
        hitungApgar1();
    }//GEN-LAST:event_cmbUsaha1ActionPerformed

    private void cmbTonus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTonus1ActionPerformed
        hitungApgar1();
    }//GEN-LAST:event_cmbTonus1ActionPerformed

    private void cmbReflex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReflex1ActionPerformed
        hitungApgar1();
    }//GEN-LAST:event_cmbReflex1ActionPerformed

    private void cmbWarna1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWarna1ActionPerformed
        hitungApgar1();
    }//GEN-LAST:event_cmbWarna1ActionPerformed

    private void cmbFrek5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrek5ActionPerformed
        hitungApgar5();
    }//GEN-LAST:event_cmbFrek5ActionPerformed

    private void cmbUsaha5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsaha5ActionPerformed
        hitungApgar5();
    }//GEN-LAST:event_cmbUsaha5ActionPerformed

    private void cmbTonus5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTonus5ActionPerformed
        hitungApgar5();
    }//GEN-LAST:event_cmbTonus5ActionPerformed

    private void cmbReflex5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReflex5ActionPerformed
        hitungApgar5();
    }//GEN-LAST:event_cmbReflex5ActionPerformed

    private void cmbWarna5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWarna5ActionPerformed
        hitungApgar5();
    }//GEN-LAST:event_cmbWarna5ActionPerformed

    private void cmbFrek10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrek10ActionPerformed
        hitungApgar10();
    }//GEN-LAST:event_cmbFrek10ActionPerformed

    private void cmbUsaha10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsaha10ActionPerformed
        hitungApgar10();
    }//GEN-LAST:event_cmbUsaha10ActionPerformed

    private void cmbTonus10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTonus10ActionPerformed
        hitungApgar10();
    }//GEN-LAST:event_cmbTonus10ActionPerformed

    private void cmbReflex10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReflex10ActionPerformed
        hitungApgar10();
    }//GEN-LAST:event_cmbReflex10ActionPerformed

    private void cmbWarna10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbWarna10ActionPerformed
        hitungApgar10();
    }//GEN-LAST:event_cmbWarna10ActionPerformed

    private void TabDowneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabDowneMouseClicked
        if (TabDowne.getSelectedIndex() == 0) {
            hitungDowneA();
        } else if (TabDowne.getSelectedIndex() == 1) {
            hitungDowneB();
        } else if (TabDowne.getSelectedIndex() == 2) {
            hitungDowneC();
        }
    }//GEN-LAST:event_TabDowneMouseClicked

    private void cmbRetraksiCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRetraksiCActionPerformed
        hitungDowneC();
    }//GEN-LAST:event_cmbRetraksiCActionPerformed

    private void cmbFrekNafasCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrekNafasCActionPerformed
        hitungDowneC();
    }//GEN-LAST:event_cmbFrekNafasCActionPerformed

    private void cmbAirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAirCActionPerformed
        hitungDowneC();
    }//GEN-LAST:event_cmbAirCActionPerformed

    private void cmbRetraksiBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRetraksiBActionPerformed
        hitungDowneB();
    }//GEN-LAST:event_cmbRetraksiBActionPerformed

    private void cmbFrekNafasBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrekNafasBActionPerformed
        hitungDowneB();
    }//GEN-LAST:event_cmbFrekNafasBActionPerformed

    private void cmbMerintihAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerintihAActionPerformed
        hitungDowneA();
    }//GEN-LAST:event_cmbMerintihAActionPerformed

    private void cmbSianosisAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSianosisAActionPerformed
        hitungDowneA();
    }//GEN-LAST:event_cmbSianosisAActionPerformed

    private void cmbRetraksiAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRetraksiAActionPerformed
        hitungDowneA();
    }//GEN-LAST:event_cmbRetraksiAActionPerformed

    private void cmbFrekNafasAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFrekNafasAActionPerformed
        hitungDowneA();
    }//GEN-LAST:event_cmbFrekNafasAActionPerformed

    private void cmbSianosisBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSianosisBActionPerformed
        hitungDowneB();
    }//GEN-LAST:event_cmbSianosisBActionPerformed

    private void cmbSianosisCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSianosisCActionPerformed
        hitungDowneC();
    }//GEN-LAST:event_cmbSianosisCActionPerformed

    private void cmbAirBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAirBActionPerformed
        hitungDowneB();
    }//GEN-LAST:event_cmbAirBActionPerformed

    private void cmbAirAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAirAActionPerformed
        hitungDowneA();
    }//GEN-LAST:event_cmbAirAActionPerformed

    private void cmbMerintihBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerintihBActionPerformed
        hitungDowneB();
    }//GEN-LAST:event_cmbMerintihBActionPerformed

    private void cmbMerintihCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMerintihCActionPerformed
        hitungDowneC();
    }//GEN-LAST:event_cmbMerintihCActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void BtnPerawatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPerawatActionPerformed
        akses.setform("RMSkorApgarDowneCapPerinatologi");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPerawatActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSkor.getSelectedRow() > -1) {
            if (akses.getadmin() == true) {
                hapus();
            } else {
                if (nip.equals(akses.getkode())) {
                    hapus();
                } else {
                    JOptionPane.showMessageDialog(null, "Hanya bisa dihapus oleh perawat yang bernama " + tbSkor.getValueAt(tbSkor.getSelectedRow(), 9).toString() + " ..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan anda pilih data terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbSkor.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));

            //tanda 1
            param.put("frek1", TnilaiFrek1.getText());
            param.put("usaha1", TnilaiUsaha1.getText());
            param.put("tonus1", TnilaiTonus1.getText());
            param.put("reflex1", TnilaiReflex1.getText());
            param.put("warna1", TnilaiWarna1.getText());
            param.put("jmlTanda1", TJumlah1.getText());
            
            //tanda 5
            param.put("frek5", TnilaiFrek5.getText());
            param.put("usaha5", TnilaiUsaha5.getText());
            param.put("tonus5", TnilaiTonus5.getText());
            param.put("reflex5", TnilaiReflex5.getText());
            param.put("warna5", TnilaiWarna5.getText());
            param.put("jmlTanda5", TJumlah5.getText());
            
            //tanda 10
            param.put("frek10", TnilaiFrek10.getText());
            param.put("usaha10", TnilaiUsaha10.getText());
            param.put("tonus10", TnilaiTonus10.getText());
            param.put("reflex10", TnilaiReflex10.getText());
            param.put("warna10", TnilaiWarna10.getText());
            param.put("jmlTanda10", TJumlah10.getText());
            
            //menit ke
            if (TmenitA.getText().equals("")) {
                param.put("menitA", "");
            } else {
                param.put("menitA", TmenitA.getText() + " '");
            }
            
            if (TmenitB.getText().equals("")) {
                param.put("menitB", "");
            } else {
                param.put("menitB", TmenitB.getText() + " '");
            }
            
            if (TmenitC.getText().equals("")) {
                param.put("menitC", "");
            } else {
                param.put("menitC", TmenitC.getText() + " '");
            }
            
            //pemeriksaan ke 1
            param.put("frekNafasA", TnilaiFrekA.getText());
            param.put("retraksiA", TnilaiRetraksiA.getText());
            param.put("sianosisA", TnilaiSianoA.getText());
            param.put("airA", TnilaiAirA.getText());
            param.put("merintihA", TnilaiMerintihA.getText());
            param.put("jmlPemeriksaanA", TJumlahA.getText());
            
            //pemeriksaan ke 2
            param.put("frekNafasB", TnilaiFrekB.getText());
            param.put("retraksiB", TnilaiRetraksiB.getText());
            param.put("sianosisB", TnilaiSianoB.getText());
            param.put("airB", TnilaiAirB.getText());
            param.put("merintihB", TnilaiMerintihB.getText());
            param.put("jmlPemeriksaanB", TJumlahB.getText());
            
            //pemeriksaan ke 3
            param.put("frekNafasC", TnilaiFrekC.getText());
            param.put("retraksiC", TnilaiRetraksiC.getText());
            param.put("sianosisC", TnilaiSianoC.getText());
            param.put("airC", TnilaiAirC.getText());
            param.put("merintihC", TnilaiMerintihC.getText());
            param.put("jmlPemeriksaanC", TJumlahC.getText());
            
            param.put("evaluasi", cmbEvaluasi.getSelectedItem().toString());            
            param.put("tanggal", Valid.SetTglINDONESIA(Valid.SetTgl(Ttgl.getSelectedItem() + "")));
            param.put("jam", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " Wita");
            param.put("petugas", TnmPerawat.getText());

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (TnmPerawat.getText().equals("") || TnmPerawat.getText().equals("-") || TnmPerawat.getText().equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Untuk tanda tangan elektronik, nama perawat harus diisi dulu,..");
                } else {
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Skor Apgar, Skor Downe, Cap Jari Ibu Dan Bayi", TnmPerawat.getText(),
                                    Sequel.cariIsi("select date_format(waktu_simpan,'%d/%m/%Y') from skor_apgar_downe_cap_jari_perinatologi where "
                                            + "no_rawat='" + tbSkor.getValueAt(tbSkor.getSelectedRow(), 0).toString() + "'"),
                                    Sequel.cariIsi("select time(waktu_simpan) from skor_apgar_downe_cap_jari_perinatologi where "
                                            + "no_rawat='" + tbSkor.getValueAt(tbSkor.getSelectedRow(), 0).toString() + "'")) + "') from kalimat_tte where kode='001'");
                    
                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Skor Apgar, Skor Downe, Cap Jari Ibu Dan Bayi", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                    
                    Valid.MyReport("rptSkorApgarDowneCapJariQr.jasper", "report", "::[ Skor Apgar, Downe, Cap Jari Ibu & Bayi ]::",
                            "SELECT now() tanggal", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }                
            } else {
                Valid.MyReport("rptSkorApgarDowneCapJari.jasper", "report", "::[ Skor Apgar, Downe, Cap Jari Ibu & Bayi ]::",
                        "SELECT now() tanggal", param);
            }

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan klik/pilih datanya pada tabel terlebih dahulu..!!!!");
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

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSkorApgarDowneCapPerinatologi dialog = new RMSkorApgarDowneCapPerinatologi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPerawat;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private widget.TextBox TJumlah1;
    private widget.TextBox TJumlah10;
    private widget.TextBox TJumlah5;
    private widget.TextBox TJumlahA;
    private widget.TextBox TJumlahB;
    private widget.TextBox TJumlahC;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabApgar;
    private javax.swing.JTabbedPane TabDowne;
    private widget.TextBox TmenitA;
    private widget.TextBox TmenitB;
    private widget.TextBox TmenitC;
    private widget.TextBox TnilaiAirA;
    private widget.TextBox TnilaiAirB;
    private widget.TextBox TnilaiAirC;
    private widget.TextBox TnilaiFrek1;
    private widget.TextBox TnilaiFrek10;
    private widget.TextBox TnilaiFrek5;
    private widget.TextBox TnilaiFrekA;
    private widget.TextBox TnilaiFrekB;
    private widget.TextBox TnilaiFrekC;
    private widget.TextBox TnilaiMerintihA;
    private widget.TextBox TnilaiMerintihB;
    private widget.TextBox TnilaiMerintihC;
    private widget.TextBox TnilaiReflex1;
    private widget.TextBox TnilaiReflex10;
    private widget.TextBox TnilaiReflex5;
    private widget.TextBox TnilaiRetraksiA;
    private widget.TextBox TnilaiRetraksiB;
    private widget.TextBox TnilaiRetraksiC;
    private widget.TextBox TnilaiSianoA;
    private widget.TextBox TnilaiSianoB;
    private widget.TextBox TnilaiSianoC;
    private widget.TextBox TnilaiTonus1;
    private widget.TextBox TnilaiTonus10;
    private widget.TextBox TnilaiTonus5;
    private widget.TextBox TnilaiUsaha1;
    private widget.TextBox TnilaiUsaha10;
    private widget.TextBox TnilaiUsaha5;
    private widget.TextBox TnilaiWarna1;
    private widget.TextBox TnilaiWarna10;
    private widget.TextBox TnilaiWarna5;
    private widget.TextBox TnmPerawat;
    private widget.TextBox TrgRawat;
    private widget.Tanggal Ttgl;
    private widget.TextBox TtglLahir;
    private widget.ComboBox cmbAirA;
    private widget.ComboBox cmbAirB;
    private widget.ComboBox cmbAirC;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbEvaluasi;
    private widget.ComboBox cmbFrek1;
    private widget.ComboBox cmbFrek10;
    private widget.ComboBox cmbFrek5;
    private widget.ComboBox cmbFrekNafasA;
    private widget.ComboBox cmbFrekNafasB;
    private widget.ComboBox cmbFrekNafasC;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMerintihA;
    private widget.ComboBox cmbMerintihB;
    private widget.ComboBox cmbMerintihC;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbReflex1;
    private widget.ComboBox cmbReflex10;
    private widget.ComboBox cmbReflex5;
    private widget.ComboBox cmbRetraksiA;
    private widget.ComboBox cmbRetraksiB;
    private widget.ComboBox cmbRetraksiC;
    private widget.ComboBox cmbSianosisA;
    private widget.ComboBox cmbSianosisB;
    private widget.ComboBox cmbSianosisC;
    private widget.ComboBox cmbTonus1;
    private widget.ComboBox cmbTonus10;
    private widget.ComboBox cmbTonus5;
    private widget.ComboBox cmbUsaha1;
    private widget.ComboBox cmbUsaha10;
    private widget.ComboBox cmbUsaha5;
    private widget.ComboBox cmbWarna1;
    private widget.ComboBox cmbWarna10;
    private widget.ComboBox cmbWarna5;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
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
    private widget.Label jLabel14;
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
    private widget.Label jLabel16;
    private widget.Label jLabel160;
    private widget.Label jLabel161;
    private widget.Label jLabel162;
    private widget.Label jLabel163;
    private widget.Label jLabel164;
    private widget.Label jLabel165;
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
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel73;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel96;
    private javax.swing.JPanel jPanel3;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa11;
    private widget.PanelBiasa panelBiasa12;
    private widget.PanelBiasa panelBiasa13;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbSkor;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT sa.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(sa.tanggal,'%d-%m-%Y') tglSkoring, time_format(sa.jam,'%H:%i Wita') jamSkoring, pg.nama nmPerawat, p.tgl_lahir "
                    + "FROM skor_apgar_downe_cap_jari_perinatologi sa INNER JOIN reg_periksa rp on rp.no_rawat=sa.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN pegawai pg on pg.nik=sa.nip_perawat WHERE "
                    + "sa.tanggal between ? and ? and sa.no_rawat like ? or "
                    + "sa.tanggal between ? and ? and p.no_rkm_medis like ? or "
                    + "sa.tanggal between ? and ? and p.nm_pasien like ? or "
                    + "sa.tanggal between ? and ? and sa.ruang_rawat like ? or "
                    + "sa.tanggal between ? and ? and pg.nama like ? order by sa.waktu_simpan desc");            
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
                        rs.getString("tglSkoring"),
                        rs.getString("jamSkoring"),
                        rs.getString("evaluasi_downe"),
                        rs.getString("nmPerawat"),
                        rs.getString("apgar_frekuensi1"),
                        rs.getString("apgar_usaha1"),
                        rs.getString("apgar_tonus1"),
                        rs.getString("apgar_reflex1"),
                        rs.getString("apgar_warna1"),
                        rs.getString("apgar_frekuensi5"),
                        rs.getString("apgar_usaha5"),
                        rs.getString("apgar_tonus5"),
                        rs.getString("apgar_reflex5"),
                        rs.getString("apgar_warna5"),
                        rs.getString("apgar_frekuensi10"),
                        rs.getString("apgar_usaha10"),
                        rs.getString("apgar_tonus10"),
                        rs.getString("apgar_reflex10"),
                        rs.getString("apgar_warna10"),
                        rs.getString("nilai_downeA"),
                        rs.getString("nilai_downeB"),
                        rs.getString("nilai_downeC"),
                        rs.getString("downe_frekuensi_nilaiA"),
                        rs.getString("downe_retraksi_nilaiA"),
                        rs.getString("downe_sianosis_nilaiA"),
                        rs.getString("downe_air_nilaiA"),
                        rs.getString("downe_merintih_nilaiA"),
                        rs.getString("downe_frekuensi_nilaiB"),
                        rs.getString("downe_retraksi_nilaiB"),
                        rs.getString("downe_sianosis_nilaiB"),
                        rs.getString("downe_air_nilaiB"),
                        rs.getString("downe_merintih_nilaiB"),
                        rs.getString("downe_frekuensi_nilaiC"),
                        rs.getString("downe_retraksi_nilaiC"),
                        rs.getString("downe_sianosis_nilaiC"),
                        rs.getString("downe_air_nilaiC"),
                        rs.getString("downe_merintih_nilaiC"),
                        rs.getString("evaluasi_downe"),
                        rs.getString("tanggal"),
                        rs.getString("jam"),
                        rs.getString("nip_perawat"),
                        rs.getString("waktu_simpan"),
                        rs.getString("tgl_lahir")                        
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMSkorApgarDowneCapPerinatologi.tampil() : " + e);
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
        cmbFrek1.setSelectedIndex(0);
        cmbUsaha1.setSelectedIndex(0);
        cmbTonus1.setSelectedIndex(0);
        cmbReflex1.setSelectedIndex(0);
        cmbWarna1.setSelectedIndex(0);
        hitungApgar1();
        cmbFrek5.setSelectedIndex(0);
        cmbUsaha5.setSelectedIndex(0);
        cmbTonus5.setSelectedIndex(0);
        cmbReflex5.setSelectedIndex(0);
        cmbWarna5.setSelectedIndex(0);
        hitungApgar5();
        cmbFrek10.setSelectedIndex(0);
        cmbUsaha10.setSelectedIndex(0);
        cmbTonus10.setSelectedIndex(0);
        cmbReflex10.setSelectedIndex(0);
        cmbWarna10.setSelectedIndex(0);
        hitungApgar10();
        TmenitA.setText("");
        cmbFrekNafasA.setSelectedIndex(0);
        cmbRetraksiA.setSelectedIndex(0);
        cmbSianosisA.setSelectedIndex(0);
        cmbAirA.setSelectedIndex(0);
        cmbMerintihA.setSelectedIndex(0);
        hitungDowneA();
        TmenitB.setText("");
        cmbFrekNafasB.setSelectedIndex(0);
        cmbRetraksiB.setSelectedIndex(0);
        cmbSianosisB.setSelectedIndex(0);
        cmbAirB.setSelectedIndex(0);
        cmbMerintihB.setSelectedIndex(0);
        hitungDowneB();
        TmenitC.setText("");
        cmbFrekNafasC.setSelectedIndex(0);
        cmbRetraksiC.setSelectedIndex(0);
        cmbSianosisC.setSelectedIndex(0);
        cmbAirC.setSelectedIndex(0);
        cmbMerintihC.setSelectedIndex(0);
        hitungDowneC();
        Ttgl.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        cmbEvaluasi.setSelectedIndex(0);
        TabApgar.setSelectedIndex(0);
        TabDowne.setSelectedIndex(0);
    }

    private void getData() {
        nip = "";
        if (tbSkor.getSelectedRow() != -1) {
            TNoRw.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 1).toString());
            TPasien.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 5).toString());
            TtglLahir.setText(Valid.SetTglINDONESIA(tbSkor.getValueAt(tbSkor.getSelectedRow(), 48).toString()));
            
            cmbFrek1.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 10).toString());
            cmbUsaha1.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 11).toString());
            cmbTonus1.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 12).toString());
            cmbReflex1.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 13).toString());
            cmbWarna1.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 14).toString());
            hitungApgar1();
            cmbFrek5.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 15).toString());
            cmbUsaha5.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 16).toString());
            cmbTonus5.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 17).toString());
            cmbReflex5.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 18).toString());
            cmbWarna5.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 19).toString());
            hitungApgar5();
            cmbFrek10.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 20).toString());
            cmbUsaha10.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 21).toString());
            cmbTonus10.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 22).toString());
            cmbReflex10.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 23).toString());
            cmbWarna10.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 24).toString());
            hitungApgar10();
            TmenitA.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 25).toString());
            TmenitB.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 26).toString());
            TmenitC.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 27).toString());
            
            cmbFrekNafasA.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 28).toString());
            cmbRetraksiA.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 29).toString());
            cmbSianosisA.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 30).toString());
            cmbAirA.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 31).toString());
            cmbMerintihA.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 32).toString());
            hitungDowneA();
            cmbFrekNafasB.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 33).toString());
            cmbRetraksiB.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 34).toString());
            cmbSianosisB.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 35).toString());
            cmbAirB.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 36).toString());
            cmbMerintihB.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 37).toString());
            hitungDowneB();
            cmbFrekNafasC.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 38).toString());
            cmbRetraksiC.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 39).toString());
            cmbSianosisC.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 40).toString());
            cmbAirC.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 41).toString());
            cmbMerintihC.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 42).toString());
            hitungDowneC();
            cmbEvaluasi.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 43).toString());            
            Valid.SetTgl(Ttgl, tbSkor.getValueAt(tbSkor.getSelectedRow(), 44).toString());
            cmbJam.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 45).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 45).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbSkor.getValueAt(tbSkor.getSelectedRow(), 45).toString().substring(6, 8));
            nip = tbSkor.getValueAt(tbSkor.getSelectedRow(), 46).toString();
            TnmPerawat.setText(tbSkor.getValueAt(tbSkor.getSelectedRow(), 9).toString());
            
            TabApgar.setSelectedIndex(0);
            TabDowne.setSelectedIndex(0);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnPrint.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {
            nip = akses.getkode();            
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPerawat, nip);
            if (TnmPerawat.getText().equals("")) {
                nip = "";
            }
        } 
    }
    
    private void hitungApgar1() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;
        
        //frekuensi jantung
        if (cmbFrek1.getSelectedIndex() == 0 || cmbFrek1.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrek1.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrek1.getSelectedIndex() == 3) {
            a = 2;
        }
        
        //usaha nafas
        if (cmbUsaha1.getSelectedIndex() == 0 || cmbUsaha1.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbUsaha1.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbUsaha1.getSelectedIndex() == 3) {
            b = 2;
        }
        
        //tonus otot
        if (cmbTonus1.getSelectedIndex() == 0 || cmbTonus1.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbTonus1.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbTonus1.getSelectedIndex() == 3) {
            c = 2;
        }
        
        //reflex
        if (cmbReflex1.getSelectedIndex() == 0 || cmbReflex1.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbReflex1.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbReflex1.getSelectedIndex() == 3) {
            d = 2;
        }
        
        //warna
        if (cmbWarna1.getSelectedIndex() == 0 || cmbWarna1.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbWarna1.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbWarna1.getSelectedIndex() == 3) {
            e = 2;
        }
        
        //proses hitung
        if (cmbFrek1.getSelectedIndex() == 0) {
            TnilaiFrek1.setText("");
        } else {
            TnilaiFrek1.setText(Valid.SetAngka2(a));
        }
        
        if (cmbUsaha1.getSelectedIndex() == 0) {
            TnilaiUsaha1.setText("");
        } else {
            TnilaiUsaha1.setText(Valid.SetAngka2(b));
        }
        
        if (cmbTonus1.getSelectedIndex() == 0) {
            TnilaiTonus1.setText("");
        } else {
            TnilaiTonus1.setText(Valid.SetAngka2(c));
        }
        
        if (cmbReflex1.getSelectedIndex() == 0) {
            TnilaiReflex1.setText("");
        } else {
            TnilaiReflex1.setText(Valid.SetAngka2(d));
        }
        
        if (cmbWarna1.getSelectedIndex() == 0) {
            TnilaiWarna1.setText("");
        } else {
            TnilaiWarna1.setText(Valid.SetAngka2(e));
        }
        
        hasilNilai = a + b + c + d + e;
        TJumlah1.setText(Valid.SetAngka2(hasilNilai));
    }
    
    private void hitungApgar5() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;

        //frekuensi jantung
        if (cmbFrek5.getSelectedIndex() == 0 || cmbFrek5.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrek5.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrek5.getSelectedIndex() == 3) {
            a = 2;
        }

        //usaha nafas
        if (cmbUsaha5.getSelectedIndex() == 0 || cmbUsaha5.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbUsaha5.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbUsaha5.getSelectedIndex() == 3) {
            b = 2;
        }

        //tonus otot
        if (cmbTonus5.getSelectedIndex() == 0 || cmbTonus5.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbTonus5.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbTonus5.getSelectedIndex() == 3) {
            c = 2;
        }

        //reflex
        if (cmbReflex5.getSelectedIndex() == 0 || cmbReflex5.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbReflex5.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbReflex5.getSelectedIndex() == 3) {
            d = 2;
        }

        //warna
        if (cmbWarna5.getSelectedIndex() == 0 || cmbWarna5.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbWarna5.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbWarna5.getSelectedIndex() == 3) {
            e = 2;
        }

        //proses hitung
        if (cmbFrek5.getSelectedIndex() == 0) {
            TnilaiFrek5.setText("");
        } else {
            TnilaiFrek5.setText(Valid.SetAngka2(a));
        }
        
        if (cmbUsaha5.getSelectedIndex() == 0) {
            TnilaiUsaha5.setText("");
        } else {
            TnilaiUsaha5.setText(Valid.SetAngka2(b));
        }
        
        if (cmbTonus5.getSelectedIndex() == 0) {
            TnilaiTonus5.setText("");
        } else {
            TnilaiTonus5.setText(Valid.SetAngka2(c));
        }
        
        if (cmbReflex5.getSelectedIndex() == 0) {
            TnilaiReflex5.setText("");
        } else {
            TnilaiReflex5.setText(Valid.SetAngka2(d));
        }
        
        if (cmbWarna5.getSelectedIndex() == 0) {
            TnilaiWarna5.setText("");
        } else {
            TnilaiWarna5.setText(Valid.SetAngka2(e));
        }

        hasilNilai = a + b + c + d + e;
        TJumlah5.setText(Valid.SetAngka2(hasilNilai));
    }
    
    private void hitungApgar10() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;

        //frekuensi jantung
        if (cmbFrek10.getSelectedIndex() == 0 || cmbFrek10.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrek10.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrek10.getSelectedIndex() == 3) {
            a = 2;
        }

        //usaha nafas
        if (cmbUsaha10.getSelectedIndex() == 0 || cmbUsaha10.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbUsaha10.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbUsaha10.getSelectedIndex() == 3) {
            b = 2;
        }

        //tonus otot
        if (cmbTonus10.getSelectedIndex() == 0 || cmbTonus10.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbTonus10.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbTonus10.getSelectedIndex() == 3) {
            c = 2;
        }

        //reflex
        if (cmbReflex10.getSelectedIndex() == 0 || cmbReflex10.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbReflex10.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbReflex10.getSelectedIndex() == 3) {
            d = 2;
        }

        //warna
        if (cmbWarna10.getSelectedIndex() == 0 || cmbWarna10.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbWarna10.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbWarna10.getSelectedIndex() == 3) {
            e = 2;
        }

        //proses hitung
        if (cmbFrek10.getSelectedIndex() == 0) {
            TnilaiFrek10.setText("");
        } else {
            TnilaiFrek10.setText(Valid.SetAngka2(a));
        }
        
        if (cmbUsaha10.getSelectedIndex() == 0) {
            TnilaiUsaha10.setText("");
        } else {
            TnilaiUsaha10.setText(Valid.SetAngka2(b));
        }
        
        if (cmbTonus10.getSelectedIndex() == 0) {
            TnilaiTonus10.setText("");
        } else {
            TnilaiTonus10.setText(Valid.SetAngka2(c));
        }
        
        if (cmbReflex10.getSelectedIndex() == 0) {
            TnilaiReflex10.setText("");
        } else {
            TnilaiReflex10.setText(Valid.SetAngka2(d));
        }
        
        if (cmbWarna10.getSelectedIndex() == 0) {
            TnilaiWarna10.setText("");
        } else {
            TnilaiWarna10.setText(Valid.SetAngka2(e));
        }

        hasilNilai = a + b + c + d + e;
        TJumlah10.setText(Valid.SetAngka2(hasilNilai));
    }
    
    private void hitungDowneA() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;
        
        //frekuensi nafas
        if (cmbFrekNafasA.getSelectedIndex() == 0 || cmbFrekNafasA.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrekNafasA.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrekNafasA.getSelectedIndex() == 3) {
            a = 2;
        }
        
        //retraksi
        if (cmbRetraksiA.getSelectedIndex() == 0 || cmbRetraksiA.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbRetraksiA.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbRetraksiA.getSelectedIndex() == 3) {
            b = 2;
        }
        
        //sianosis
        if (cmbSianosisA.getSelectedIndex() == 0 || cmbSianosisA.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbSianosisA.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbSianosisA.getSelectedIndex() == 3) {
            c = 2;
        }
        
        //air entry
        if (cmbAirA.getSelectedIndex() == 0 || cmbAirA.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbAirA.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbAirA.getSelectedIndex() == 3) {
            d = 2;
        }
        
        //merintih
        if (cmbMerintihA.getSelectedIndex() == 0 || cmbMerintihA.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbMerintihA.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbMerintihA.getSelectedIndex() == 3) {
            e = 2;
        }
        
        //proses hitung
        if (cmbFrekNafasA.getSelectedIndex() == 0) {
            TnilaiFrekA.setText("");
        } else {
            TnilaiFrekA.setText(Valid.SetAngka2(a));
        }
	
        if (cmbRetraksiA.getSelectedIndex() == 0) {
            TnilaiRetraksiA.setText("");
        } else {
            TnilaiRetraksiA.setText(Valid.SetAngka2(b));
        }
        
        if (cmbSianosisA.getSelectedIndex() == 0) {
            TnilaiSianoA.setText("");
        } else {
            TnilaiSianoA.setText(Valid.SetAngka2(c));
        }
        
        if (cmbAirA.getSelectedIndex() == 0) {
            TnilaiAirA.setText("");
        } else {
            TnilaiAirA.setText(Valid.SetAngka2(d));
        }
        
        if (cmbMerintihA.getSelectedIndex() == 0) {
            TnilaiMerintihA.setText("");
        } else {
            TnilaiMerintihA.setText(Valid.SetAngka2(e));
        }
        
        hasilNilai = a + b + c + d + e;
        TJumlahA.setText(Valid.SetAngka2(hasilNilai));
    }
    
    private void hitungDowneB() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;

        //frekuensi nafas
        if (cmbFrekNafasB.getSelectedIndex() == 0 || cmbFrekNafasB.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrekNafasB.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrekNafasB.getSelectedIndex() == 3) {
            a = 2;
        }

        //retraksi
        if (cmbRetraksiB.getSelectedIndex() == 0 || cmbRetraksiB.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbRetraksiB.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbRetraksiB.getSelectedIndex() == 3) {
            b = 2;
        }

        //sianosis
        if (cmbSianosisB.getSelectedIndex() == 0 || cmbSianosisB.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbSianosisB.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbSianosisB.getSelectedIndex() == 3) {
            c = 2;
        }

        //air entry
        if (cmbAirB.getSelectedIndex() == 0 || cmbAirB.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbAirB.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbAirB.getSelectedIndex() == 3) {
            d = 2;
        }

        //merintih
        if (cmbMerintihB.getSelectedIndex() == 0 || cmbMerintihB.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbMerintihB.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbMerintihB.getSelectedIndex() == 3) {
            e = 2;
        }

        //proses hitung
        if (cmbFrekNafasB.getSelectedIndex() == 0) {
            TnilaiFrekB.setText("");
        } else {
            TnilaiFrekB.setText(Valid.SetAngka2(a));
        }
	
        if (cmbRetraksiB.getSelectedIndex() == 0) {
            TnilaiRetraksiB.setText("");
        } else {
            TnilaiRetraksiB.setText(Valid.SetAngka2(b));
        }
        
        if (cmbSianosisB.getSelectedIndex() == 0) {
            TnilaiSianoB.setText("");
        } else {
            TnilaiSianoB.setText(Valid.SetAngka2(c));
        }
        
        if (cmbAirB.getSelectedIndex() == 0) {
            TnilaiAirB.setText("");
        } else {
            TnilaiAirB.setText(Valid.SetAngka2(d));
        }
        
        if (cmbMerintihB.getSelectedIndex() == 0) {
            TnilaiMerintihB.setText("");
        } else {
            TnilaiMerintihB.setText(Valid.SetAngka2(e));
        }

        hasilNilai = a + b + c + d + e;
        TJumlahB.setText(Valid.SetAngka2(hasilNilai));
    }
    
    private void hitungDowneC() {
        int a = 0, b = 0, c = 0, d = 0, e = 0, hasilNilai = 0;

        //frekuensi nafas
        if (cmbFrekNafasC.getSelectedIndex() == 0 || cmbFrekNafasC.getSelectedIndex() == 1) {
            a = 0;
        } else if (cmbFrekNafasC.getSelectedIndex() == 2) {
            a = 1;
        } else if (cmbFrekNafasC.getSelectedIndex() == 3) {
            a = 2;
        }

        //retraksi
        if (cmbRetraksiC.getSelectedIndex() == 0 || cmbRetraksiC.getSelectedIndex() == 1) {
            b = 0;
        } else if (cmbRetraksiC.getSelectedIndex() == 2) {
            b = 1;
        } else if (cmbRetraksiC.getSelectedIndex() == 3) {
            b = 2;
        }

        //sianosis
        if (cmbSianosisC.getSelectedIndex() == 0 || cmbSianosisC.getSelectedIndex() == 1) {
            c = 0;
        } else if (cmbSianosisC.getSelectedIndex() == 2) {
            c = 1;
        } else if (cmbSianosisC.getSelectedIndex() == 3) {
            c = 2;
        }

        //air entry
        if (cmbAirC.getSelectedIndex() == 0 || cmbAirC.getSelectedIndex() == 1) {
            d = 0;
        } else if (cmbAirC.getSelectedIndex() == 2) {
            d = 1;
        } else if (cmbAirC.getSelectedIndex() == 3) {
            d = 2;
        }

        //merintih
        if (cmbMerintihC.getSelectedIndex() == 0 || cmbMerintihC.getSelectedIndex() == 1) {
            e = 0;
        } else if (cmbMerintihC.getSelectedIndex() == 2) {
            e = 1;
        } else if (cmbMerintihC.getSelectedIndex() == 3) {
            e = 2;
        }

        //proses hitung
        if (cmbFrekNafasC.getSelectedIndex() == 0) {
            TnilaiFrekC.setText("");
        } else {
            TnilaiFrekC.setText(Valid.SetAngka2(a));
        }
	
        if (cmbRetraksiC.getSelectedIndex() == 0) {
            TnilaiRetraksiC.setText("");
        } else {
            TnilaiRetraksiC.setText(Valid.SetAngka2(b));
        }
        
        if (cmbSianosisC.getSelectedIndex() == 0) {
            TnilaiSianoC.setText("");
        } else {
            TnilaiSianoC.setText(Valid.SetAngka2(c));
        }
        
        if (cmbAirC.getSelectedIndex() == 0) {
            TnilaiAirC.setText("");
        } else {
            TnilaiAirC.setText(Valid.SetAngka2(d));
        }
        
        if (cmbMerintihC.getSelectedIndex() == 0) {
            TnilaiMerintihC.setText("");
        } else {
            TnilaiMerintihC.setText(Valid.SetAngka2(e));
        }

        hasilNilai = a + b + c + d + e;
        TJumlahC.setText(Valid.SetAngka2(hasilNilai));
    }
    
    public void setData(String norwt, String rgrawat) {
        TNoRw.setText(norwt);
        TNoRM.setText(Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='" + norwt + "'"));
        TPasien.setText(Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
        TrgRawat.setText(rgrawat);
        TtglLahir.setText(Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'")));
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);
    }
    
    private void ganti() {
        if (Sequel.mengedittf("skor_apgar_downe_cap_jari_perinatologi", "no_rawat=?", "apgar_frekuensi1=?, apgar_usaha1=?, apgar_tonus1=?, apgar_reflex1=?, "
                + "apgar_warna1=?, apgar_frekuensi5=?, apgar_usaha5=?, apgar_tonus5=?, apgar_reflex5=?, apgar_warna5=?, apgar_frekuensi10=?, apgar_usaha10=?, apgar_tonus10=?, "
                + "apgar_reflex10=?, apgar_warna10=?, nilai_downeA=?, nilai_downeB=?, nilai_downeC=?, downe_frekuensi_nilaiA=?, downe_retraksi_nilaiA=?, downe_sianosis_nilaiA=?, "
                + "downe_air_nilaiA=?, downe_merintih_nilaiA=?, downe_frekuensi_nilaiB=?, downe_retraksi_nilaiB=?, downe_sianosis_nilaiB=?, downe_air_nilaiB=?, downe_merintih_nilaiB=?, "
                + "downe_frekuensi_nilaiC=?, downe_retraksi_nilaiC=?, downe_sianosis_nilaiC=?, downe_air_nilaiC=?, downe_merintih_nilaiC=?, evaluasi_downe=?, tanggal=?, jam=?, "
                + "nip_perawat=?", 38, new String[]{
                    cmbFrek1.getSelectedItem().toString(), cmbUsaha1.getSelectedItem().toString(), cmbTonus1.getSelectedItem().toString(),
                    cmbReflex1.getSelectedItem().toString(), cmbWarna1.getSelectedItem().toString(), cmbFrek5.getSelectedItem().toString(), cmbUsaha5.getSelectedItem().toString(),
                    cmbTonus5.getSelectedItem().toString(), cmbReflex5.getSelectedItem().toString(), cmbWarna5.getSelectedItem().toString(), cmbFrek10.getSelectedItem().toString(),
                    cmbUsaha10.getSelectedItem().toString(), cmbTonus10.getSelectedItem().toString(), cmbReflex10.getSelectedItem().toString(), cmbWarna10.getSelectedItem().toString(),
                    TmenitA.getText(), TmenitB.getText(), TmenitC.getText(), cmbFrekNafasA.getSelectedItem().toString(), cmbRetraksiA.getSelectedItem().toString(),
                    cmbSianosisA.getSelectedItem().toString(), cmbAirA.getSelectedItem().toString(), cmbMerintihA.getSelectedItem().toString(), cmbFrekNafasB.getSelectedItem().toString(),
                    cmbRetraksiB.getSelectedItem().toString(), cmbSianosisB.getSelectedItem().toString(), cmbAirB.getSelectedItem().toString(), cmbMerintihB.getSelectedItem().toString(),
                    cmbFrekNafasC.getSelectedItem().toString(), cmbRetraksiC.getSelectedItem().toString(), cmbSianosisC.getSelectedItem().toString(), cmbAirC.getSelectedItem().toString(),
                    cmbMerintihC.getSelectedItem().toString(), cmbEvaluasi.getSelectedItem().toString(), Valid.SetTgl(Ttgl.getSelectedItem() + ""),
                    cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(), nip,
                    tbSkor.getValueAt(tbSkor.getSelectedRow(), 0).toString()
                }) == true) {

            Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Skor Apgar, Skor Downe, Cap Jari Ibu Dan Bayi", "Ganti");
            TCari.setText(TNoRw.getText());
            tampil();
            emptTeks();
        }
    }
    
    private void hapus() {
        x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (x == JOptionPane.YES_OPTION) {
            if (Sequel.queryu2tf("delete from skor_apgar_downe_cap_jari_perinatologi where no_rawat=?", 1, new String[]{
                tbSkor.getValueAt(tbSkor.getSelectedRow(), 0).toString()
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
    }
    
    public void awalData() {
        tampil();
    }
}
