package simrskhanza;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public class DlgPermintaanLabRAZA extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPasien, psLab;
    private ResultSet rs, rsPasien, rsLab;
    private int i = 0, x = 0, jlhOrder = 0, cekSudah = 0, cekDRinap = 0;
    private String kddokter = "", sttsRawat = "", kdPoli = "", cekNORW = "", cekNOMINTA = "", diperiksa = "", kddokterFIX = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPermintaanLabRAZA(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
            "No.","Nama Pemeriksaan Lab.","Tgl. Permintaan","Jam Permintaan","Diperiksa","norawat","kddokter","No. Permintaan"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=false;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbMintaPeriksa.setModel(tabMode);

        tbMintaPeriksa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMintaPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 8; i++) {
            TableColumn column = tbMintaPeriksa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
//                column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
//                column.setPreferredWidth(70);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(115);
            }
        }
        tbMintaPeriksa.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
            "Nama Item Pemeriksaan Lab.","Tarif PerBup (Rp.)"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=false;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbMasterItem.setModel(tabMode1);
        tbMasterItem.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMasterItem.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 2; i++) {
            TableColumn column = tbMasterItem.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(250);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            }
        }
        tbMasterItem.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
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
        
        jam();                
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
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnBaru = new widget.Button();
        BtnSimpan = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        nmPemeriksaan = new widget.TextBox();
        jLabel5 = new widget.Label();
        noRW = new widget.TextBox();
        noRM = new widget.TextBox();
        nmPasien = new widget.TextBox();
        jLabel6 = new widget.Label();
        jk = new widget.TextBox();
        jLabel7 = new widget.Label();
        umur = new widget.TextBox();
        jLabel8 = new widget.Label();
        diagnos = new widget.TextBox();
        jLabel9 = new widget.Label();
        tglMinta = new widget.Tanggal();
        jLabel11 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJam = new widget.CekBox();
        jLabel12 = new widget.Label();
        unit = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel13 = new widget.Label();
        drPerujuk = new widget.TextBox();
        TAlamat = new widget.TextBox();
        jLabel14 = new widget.Label();
        noMinta = new widget.TextBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbMintaPeriksa = new widget.Table();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel64 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari1 = new widget.Button();
        Scroll33 = new widget.ScrollPane();
        tbMasterItem = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Permintaan Pemeriksaan Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 57));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnBaru.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaru.setMnemonic('B');
        BtnBaru.setText("Baru");
        BtnBaru.setToolTipText("Alt+B");
        BtnBaru.setName("BtnBaru"); // NOI18N
        BtnBaru.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaruActionPerformed(evt);
            }
        });
        BtnBaru.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBaruKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBaru);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(28, 240));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Pemeriksaan Diminta : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 206, 130, 23);

        nmPemeriksaan.setForeground(new java.awt.Color(0, 0, 204));
        nmPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nmPemeriksaan.setHighlighter(null);
        nmPemeriksaan.setName("nmPemeriksaan"); // NOI18N
        nmPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPemeriksaanKeyPressed(evt);
            }
        });
        FormInput.add(nmPemeriksaan);
        nmPemeriksaan.setBounds(130, 206, 630, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 130, 23);

        noRW.setEditable(false);
        noRW.setForeground(new java.awt.Color(0, 0, 0));
        noRW.setHighlighter(null);
        noRW.setName("noRW"); // NOI18N
        noRW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noRWKeyPressed(evt);
            }
        });
        FormInput.add(noRW);
        noRW.setBounds(130, 10, 130, 23);

        noRM.setEditable(false);
        noRM.setForeground(new java.awt.Color(0, 0, 0));
        noRM.setHighlighter(null);
        noRM.setName("noRM"); // NOI18N
        noRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noRMKeyPressed(evt);
            }
        });
        FormInput.add(noRM);
        noRM.setBounds(263, 10, 70, 23);

        nmPasien.setEditable(false);
        nmPasien.setForeground(new java.awt.Color(0, 0, 0));
        nmPasien.setHighlighter(null);
        nmPasien.setName("nmPasien"); // NOI18N
        nmPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPasienKeyPressed(evt);
            }
        });
        FormInput.add(nmPasien);
        nmPasien.setBounds(337, 10, 420, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Kelamin : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(0, 38, 130, 23);

        jk.setEditable(false);
        jk.setForeground(new java.awt.Color(0, 0, 0));
        jk.setHighlighter(null);
        jk.setName("jk"); // NOI18N
        jk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jkKeyPressed(evt);
            }
        });
        FormInput.add(jk);
        jk.setBounds(130, 38, 100, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Umur : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(230, 38, 40, 23);

        umur.setEditable(false);
        umur.setForeground(new java.awt.Color(0, 0, 0));
        umur.setHighlighter(null);
        umur.setName("umur"); // NOI18N
        umur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                umurKeyPressed(evt);
            }
        });
        FormInput.add(umur);
        umur.setBounds(272, 38, 60, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Diagnosa Klinis : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 122, 130, 23);

        diagnos.setEditable(false);
        diagnos.setForeground(new java.awt.Color(0, 0, 0));
        diagnos.setHighlighter(null);
        diagnos.setName("diagnos"); // NOI18N
        diagnos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diagnosKeyPressed(evt);
            }
        });
        FormInput.add(diagnos);
        diagnos.setBounds(130, 122, 630, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Tgl. Permintaan : ");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(265, 178, 90, 23);

        tglMinta.setEditable(false);
        tglMinta.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-01-2021" }));
        tglMinta.setDisplayFormat("dd-MM-yyyy");
        tglMinta.setName("tglMinta"); // NOI18N
        tglMinta.setOpaque(false);
        tglMinta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglMintaKeyPressed(evt);
            }
        });
        FormInput.add(tglMinta);
        tglMinta.setBounds(355, 178, 90, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Jam : ");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(450, 178, 35, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setOpaque(false);
        cmbJam.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(485, 178, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setOpaque(false);
        cmbMnt.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(533, 178, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setOpaque(false);
        cmbDtk.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(582, 178, 45, 23);

        ChkJam.setBackground(new java.awt.Color(255, 255, 250));
        ChkJam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(195, 215, 195)));
        ChkJam.setForeground(new java.awt.Color(0, 0, 0));
        ChkJam.setSelected(true);
        ChkJam.setBorderPainted(true);
        ChkJam.setBorderPaintedFlat(true);
        ChkJam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJam.setName("ChkJam"); // NOI18N
        ChkJam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJamActionPerformed(evt);
            }
        });
        FormInput.add(ChkJam);
        ChkJam.setBounds(630, 178, 23, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Alamat : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 130, 23);

        unit.setForeground(new java.awt.Color(0, 0, 0));
        unit.setText("unit");
        unit.setName("unit"); // NOI18N
        FormInput.add(unit);
        unit.setBounds(0, 94, 130, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setHighlighter(null);
        nmUnit.setName("nmUnit"); // NOI18N
        nmUnit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmUnitKeyPressed(evt);
            }
        });
        FormInput.add(nmUnit);
        nmUnit.setBounds(130, 94, 630, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Dokter Perujuk : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 150, 130, 23);

        drPerujuk.setEditable(false);
        drPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        drPerujuk.setHighlighter(null);
        drPerujuk.setName("drPerujuk"); // NOI18N
        drPerujuk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                drPerujukKeyPressed(evt);
            }
        });
        FormInput.add(drPerujuk);
        drPerujuk.setBounds(130, 150, 630, 23);

        TAlamat.setEditable(false);
        TAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TAlamat.setHighlighter(null);
        TAlamat.setName("TAlamat"); // NOI18N
        TAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatKeyPressed(evt);
            }
        });
        FormInput.add(TAlamat);
        TAlamat.setBounds(130, 66, 630, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("No. Permintaan : ");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 178, 130, 23);

        noMinta.setEditable(false);
        noMinta.setForeground(new java.awt.Color(0, 0, 0));
        noMinta.setHighlighter(null);
        noMinta.setName("noMinta"); // NOI18N
        noMinta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                noMintaKeyPressed(evt);
            }
        });
        FormInput.add(noMinta);
        noMinta.setBounds(130, 178, 130, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Item Pemeriksaan Lab. yang diminta ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbMintaPeriksa.setAutoCreateRowSorter(true);
        tbMintaPeriksa.setToolTipText("Silahkan klik untuk memilih data yang ataupun dihapus");
        tbMintaPeriksa.setName("tbMintaPeriksa"); // NOI18N
        tbMintaPeriksa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMintaPeriksaMouseClicked(evt);
            }
        });
        tbMintaPeriksa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMintaPeriksaKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbMintaPeriksa);

        jPanel4.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Nama Pemeriksaan Lab. Sesuai PerBup ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 102));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setBorder(null);
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 40));
        panelisi4.setLayout(null);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Cari Item Pemeriksaan Lab. :");
        jLabel64.setName("jLabel64"); // NOI18N
        jLabel64.setPreferredSize(new java.awt.Dimension(125, 23));
        panelisi4.add(jLabel64);
        jLabel64.setBounds(5, 8, 150, 23);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(345, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi4.add(TCari);
        TCari.setBounds(159, 8, 230, 23);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari1.setMnemonic('C');
        BtnCari1.setText("Cek");
        BtnCari1.setToolTipText("Alt+C");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(130, 30));
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
        BtnCari1.setBounds(390, 8, 70, 23);

        jPanel2.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        Scroll33.setName("Scroll33"); // NOI18N
        Scroll33.setOpaque(true);

        tbMasterItem.setAutoCreateRowSorter(true);
        tbMasterItem.setToolTipText("");
        tbMasterItem.setName("tbMasterItem"); // NOI18N
        tbMasterItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMasterItemMouseClicked(evt);
            }
        });
        tbMasterItem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbMasterItemKeyPressed(evt);
            }
        });
        Scroll33.setViewportView(tbMasterItem);

        jPanel2.add(Scroll33, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nmPemeriksaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPemeriksaanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpanActionPerformed(null);
        }
}//GEN-LAST:event_nmPemeriksaanKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (noRW.getText().trim().equals("") && noRM.getText().trim().equals("") && nmPasien.getText().trim().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (nmPemeriksaan.getText().trim().equals("")) {
            Valid.textKosong(nmPemeriksaan, "nama pemeriksaan Lab. yang diminta");
        } else {
            Sequel.menyimpan("permintaan_lab_raza", "'" + noRW.getText() + "'," + "'" + Valid.SetTgl(tglMinta.getSelectedItem() + "") + "',"
                    + "'" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "',"
                    + "'" + kddokterFIX + "','" + nmPemeriksaan.getText() + "','" + sttsRawat + "','" + noMinta.getText() + "','BELUM'", "Permintaan Lab.");
            
            nmPemeriksaan.setText("");
            cekNORW = "";
            cekNOMINTA = "";
            tampil();
            AutoNomerMinta();
            ChkJam.setSelected(true);
            TCari.setText("");
            tampilItemLab();
            BtnSimpan.setEnabled(true);
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, nmPemeriksaan, BtnGanti);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (noRW.getText().trim().equals("") && noRM.getText().trim().equals("") && nmPasien.getText().trim().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (nmPemeriksaan.getText().trim().equals("")) {
            Valid.textKosong(nmPemeriksaan, "nama pemeriksaan Lab. yang diminta");
        } else if (cekNOMINTA.equals("") && cekNORW.equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. pada tabel belum dipilih..!!!");
            tbMintaPeriksa.requestFocus();
        } else if (diperiksa.equals("SUDAH")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat diganti..!!!");
            tbMintaPeriksa.requestFocus();
        } else {
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + cekNORW + "' and no_minta='" + cekNOMINTA + "'",
                    "tgl_permintaan='" + Valid.SetTgl(tglMinta.getSelectedItem() + "") + "', "
                    + "jam_permintaan='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "', "
                    + "dokter_perujuk='" + kddokterFIX + "',"
                    + "nm_pemeriksaan='" + nmPemeriksaan.getText() + "',"
                    + "status_rawat='" + sttsRawat + "' ");

            nmPemeriksaan.setText(""); 
            cekNORW = "";
            cekNOMINTA = "";
            tampil();
            AutoNomerMinta();
            ChkJam.setSelected(true);
            TCari.setText("");
            tampilItemLab();
            BtnSimpan.setEnabled(true);
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        emptTeks();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnGanti, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void tbMintaPeriksaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMintaPeriksaMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbMintaPeriksaMouseClicked

    private void tbMintaPeriksaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMintaPeriksaKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbMintaPeriksaKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari1ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari1.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
//        if (TCari.getText().trim().equals("")) {
//            JOptionPane.showMessageDialog(null, "Jenis pemeriksaan Lab. yg. akan dicari harus diisi dulu...!!!");
//            TCari.requestFocus();
//        } else {
            tampilItemLab();
            tbMasterItem.requestFocus();
//        }
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void tbMasterItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMasterItemMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getDataLab();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbMasterItemMouseClicked

    private void tbMasterItemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbMasterItemKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataLab();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbMasterItemKeyPressed

    private void noRWKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noRWKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRWKeyPressed

    private void noRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noRMKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noRMKeyPressed

    private void nmPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmPasienKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmPasienKeyPressed

    private void jkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jkKeyPressed

    private void umurKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_umurKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_umurKeyPressed

    private void diagnosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagnosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diagnosKeyPressed

    private void tglMintaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglMintaKeyPressed
        Valid.pindah(evt, diagnos, cmbJam);
    }//GEN-LAST:event_tglMintaKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, tglMinta, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, nmPemeriksaan);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void ChkJamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJamActionPerformed

    private void nmUnitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nmUnitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_nmUnitKeyPressed

    private void drPerujukKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_drPerujukKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_drPerujukKeyPressed

    private void TAlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TAlamatKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (noRW.getText().trim().equals("") && noRM.getText().trim().equals("") && nmPasien.getText().trim().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (cekNOMINTA.equals("") && cekNORW.equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. pada tabel belum dipilih..!!!");
            tbMintaPeriksa.requestFocus();
        } else if (diperiksa.equals("SUDAH")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat dihapus..!!!");
            tbMintaPeriksa.requestFocus();
        } else {
            Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + cekNORW + "' and no_minta='" + cekNOMINTA + "'");

            nmPemeriksaan.setText(""); 
            cekNORW = "";
            cekNOMINTA = "";
            tampil();
            AutoNomerMinta();
            ChkJam.setSelected(true);
            TCari.setText("");
            tampilItemLab();
            BtnSimpan.setEnabled(true);
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, item pemeriksaan Lab. yang diminta masih kosong...!!!!");
            TCari.requestFocus();
        } else if (noRW.getText().trim().equals("") && noRM.getText().trim().equals("") && nmPasien.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. pada tabel belum dipilih..!!!");
            tbMintaPeriksa.requestFocus();
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            if (sttsRawat.equals("Ralan")) {
                param.put("kamar", "Poliklinik");
                param.put("namakamar", Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + noRW.getText() + "'"));
                param.put("diagnosa", Sequel.cariIsi("select ifnull(diagnosa,'-') diag from pemeriksaan_ralan where no_rawat='" + noRW.getText() + "'"));
                cekSudah = 0;
                cekSudah = Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza WHERE no_rawat='" + noRW.getText() + "' and status_periksa='BELUM' and status_rawat='Ralan'");
                if (cekSudah == 0) {
                    JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. sudah diperiksa semua..!!!");
                } else if (cekSudah >= 1) {
                    Valid.MyReport("rptPermintaanLabRZ.jasper", "report", "::[ Lembar Permintaan Pemeriksaan Laboratorium ]::",
                            "SELECT x.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') usia, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, DATE_FORMAT(x.tgl_permintaan,'%d-%m-%Y') tglMinta, "
                            + "DATE_FORMAT(x.tgl_permintaan,'%d/%m/%Y') tglMinta1, x.jam_permintaan, d.nm_dokter, x.nm_pemeriksaan FROM permintaan_lab_raza x "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=x.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=x.dokter_perujuk WHERE "
                            + "x.no_rawat='" + noRW.getText() + "' and x.status_periksa='BELUM' and x.status_rawat='Ralan'", param);
                    
                    emptTeks();
                    dispose();
                }
            } else {
                param.put("kamar", "Ruang Rawat");
                param.put("namakamar", Sequel.cariIsi("SELECT b.nm_bangsal from kamar_inap ki INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                        + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang IN ('-','Pindah Kamar') and ki.no_rawat='" + noRW.getText() + "' "
                        + "ORDER BY ki.tgl_keluar DESC, ki.jam_keluar DESC LIMIT 1"));
                param.put("diagnosa", Sequel.cariIsi("select ifnull(diagnosa_awal,'-') diag from kamar_inap where no_rawat='" + noRW.getText() + "'"));
                cekSudah = 0;
                cekSudah = Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza WHERE no_rawat='" + noRW.getText() + "' and status_periksa='BELUM' and status_rawat='Ranap'");
                if (cekSudah == 0) {
                    JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. sudah diperiksa semua..!!!");
                } else if (cekSudah >= 1) {
                    Valid.MyReport("rptPermintaanLabRZ.jasper", "report", "::[ Lembar Permintaan Pemeriksaan Laboratorium ]::",
                            "SELECT x.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') usia, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, DATE_FORMAT(x.tgl_permintaan,'%d-%m-%Y') tglMinta, "
                            + "DATE_FORMAT(x.tgl_permintaan,'%d/%m/%Y') tglMinta1, x.jam_permintaan, d.nm_dokter, x.nm_pemeriksaan FROM permintaan_lab_raza x "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=x.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=x.dokter_perujuk WHERE "
                            + "x.no_rawat='" + noRW.getText() + "' and x.status_periksa='BELUM' and x.status_rawat='Ranap'", param);

                    emptTeks();
                    dispose();
                }
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void noMintaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noMintaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_noMintaKeyPressed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        BtnSimpan.setEnabled(true);
        nmPemeriksaan.setText("");
        cekNORW = "";
        cekNOMINTA = "";
        TCari.setText("");
        nmPemeriksaan.requestFocus();
        AutoNomerMinta();
        tampil();
        tampilItemLab();
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void BtnBaruKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBaruKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnBaruKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgMasterFaskes dialog = new DlgMasterFaskes(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaru;
    private widget.Button BtnCari1;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkJam;
    private widget.PanelBiasa FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll33;
    private widget.TextBox TAlamat;
    private widget.TextBox TCari;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.TextBox diagnos;
    private widget.TextBox drPerujuk;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel64;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private widget.TextBox jk;
    private widget.TextBox nmPasien;
    public widget.TextBox nmPemeriksaan;
    private widget.TextBox nmUnit;
    private widget.TextBox noMinta;
    private widget.TextBox noRM;
    private widget.TextBox noRW;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.Table tbMasterItem;
    private widget.Table tbMintaPeriksa;
    private widget.Tanggal tglMinta;
    private widget.TextBox umur;
    private widget.Label unit;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {            
            if (sttsRawat.equals("Ralan")) {
                ps = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.dokter_perujuk, d.nm_dokter, p.no_minta FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where p.status_rawat='Ralan' and p.no_rawat='" + noRW.getText() + "' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            } else {
                ps = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.dokter_perujuk, d.nm_dokter, p.no_minta FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where p.status_rawat='Ranap' and p.no_rawat='" + noRW.getText() + "' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            }

            try {                
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        x + ".",
                        rs.getString("nm_pemeriksaan"),
                        rs.getString("tglminta"),
                        rs.getString("jam_permintaan"),
                        rs.getString("status_periksa"),                        
                        rs.getString("no_rawat"),
                        rs.getString("dokter_perujuk"),
                        rs.getString("no_minta")                        
                    });
                    x++;
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPermintaanLabRAZA.tampil() : " + e);
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
    }
    
    public void tampilItemLab() {
        Valid.tabelKosong(tabMode1);
        try {
            psLab = koneksi.prepareStatement("SELECT t.Pemeriksaan, FORMAT(t.biaya_item,0) trf FROM template_laboratorium t "
                    + "INNER JOIN jns_perawatan_lab j on j.kd_jenis_prw=t.kd_jenis_prw WHERE j.status='1' and "
                    + "t.Pemeriksaan like '%" + TCari.getText() + "%'ORDER BY t.Pemeriksaan");
            try {
                rsLab = psLab.executeQuery();
                while (rsLab.next()) {
                    tabMode1.addRow(new Object[]{
                        rsLab.getString("Pemeriksaan"),
                        rsLab.getString("trf")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPermintaanLabRAZA.tampilItemLab() : " + e);
            } finally {
                if (rsLab != null) {
                    rsLab.close();
                }
                if (psLab != null) {
                    psLab.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void emptTeks() {        
        noRW.setText("");
        noRM.setText("");
        nmPasien.setText("");
        jk.setText("");
        umur.setText("");
        TAlamat.setText("");
        unit.setText("nama unit : ");
        nmUnit.setText("");
        diagnos.setText("");
        drPerujuk.setText("");
        kddokter = "";
        kddokterFIX = "";
        nmPemeriksaan.setText("");
        TCari.setText("");
        cekNORW = "";
        cekNOMINTA = "";
        ChkJam.setSelected(true);
        BtnSimpan.setEnabled(true);
        AutoNomerMinta();
    }

    private void getData() {
        cekNORW = "";
        cekNOMINTA = "";
        diperiksa = "";
        
        if (tbMintaPeriksa.getSelectedRow() != -1) {
            noRW.setText(tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 5).toString());
            diperiksa = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 4).toString();
            cekNORW = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 5).toString();
            cekNOMINTA = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 7).toString();            
            isPasien(noRW.getText());
            nmPemeriksaan.setText(tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 1).toString());
            Valid.SetTgl(tglMinta, Sequel.cariIsi("select tgl_permintaan from permintaan_lab_raza where "
                    + "no_rawat='" + noRW.getText() + "' and no_minta='" + cekNOMINTA + "'"));
            nmPemeriksaan.requestFocus();
            BtnSimpan.setEnabled(false);
        }
    }
    
    private void getDataLab() {
        if (tbMasterItem.getSelectedRow() != -1) {
            nmPemeriksaan.setText(tbMasterItem.getValueAt(tbMasterItem.getSelectedRow(), 0).toString());
            TCari.requestFocus();
        }
    }
    
    private void jam() {
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJam.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJam.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    public void isPasien(String norwt) {
        sttsRawat = "";
        kdPoli = "";
        kddokter = "";
        kddokterFIX = "";
        cekDRinap = 0;
        
        try {
            psPasien = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "CONCAT(rp.umurdaftar,' ',rp.sttsumur) usia, CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                    + "rp.status_lanjut, rp.kd_poli, rp.kd_dokter FROM reg_periksa rp INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab WHERE rp.no_rawat='" + norwt + "'");

            try {
                rsPasien = psPasien.executeQuery();
                while (rsPasien.next()) {
                    noRW.setText(rsPasien.getString("no_rawat"));
                    noRM.setText(rsPasien.getString("no_rkm_medis"));
                    nmPasien.setText(rsPasien.getString("nm_pasien"));
                    jk.setText(rsPasien.getString("jenkel"));
                    umur.setText(rsPasien.getString("usia"));
                    TAlamat.setText(rsPasien.getString("alamat"));
                    sttsRawat = rsPasien.getString("status_lanjut");
                    kdPoli = rsPasien.getString("kd_poli");
                    kddokter = rsPasien.getString("kd_dokter");
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rsPasien != null) {
                    rsPasien.close();
                }
                if (psPasien != null) {
                    psPasien.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        
        if (sttsRawat.equals("Ralan")) {
            unit.setText("Poliklinik/Inst. : ");
            nmUnit.setText(Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='" + kdPoli + "'"));
            diagnos.setText(Sequel.cariIsi("select ifnull(diagnosa,'-') diag from pemeriksaan_ralan where no_rawat='" + norwt + "'"));
            drPerujuk.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + kddokter + "'"));
            kddokterFIX = kddokter;
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + norwt + "' and status_rawat='Ralan'", "dokter_perujuk='" + kddokterFIX + "'");
        } else {
            unit.setText("Ruang Rawat : ");
            nmUnit.setText(Sequel.cariIsi("SELECT b.nm_bangsal from kamar_inap ki INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                    + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang IN ('-','Pindah Kamar') and ki.no_rawat='" + norwt + "' "
                    + "ORDER BY ki.tgl_keluar DESC, ki.jam_keluar DESC LIMIT 1"));
            diagnos.setText(Sequel.cariIsi("select ifnull(diagnosa_awal,'-') diag from kamar_inap where no_rawat='" + norwt + "'"));            
            
            cekDRinap = Sequel.cariInteger("select count(-1) from dpjp_ranap where no_rawat='" + norwt + "'");
            if (cekDRinap == 0) {
                kddokterFIX = "-";
                drPerujuk.setText("-");
                Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + norwt + "' and status_rawat='Ranap'", "dokter_perujuk='" + kddokterFIX + "'");
            } else if (cekDRinap >= 1) {
                kddokterFIX = Sequel.cariIsi("select kd_dokter from dpjp_ranap where no_rawat='" + norwt + "'");
                drPerujuk.setText(Sequel.cariIsi("select d.nm_dokter from dpjp_ranap dr inner join dokter d on d.kd_dokter=dr.kd_dokter where dr.no_rawat='" + norwt + "'"));
                Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + norwt + "' and status_rawat='Ranap'", "dokter_perujuk='" + kddokterFIX + "'");
            }
        }
    }    
    
    public void AutoNomerMinta() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_minta,5),signed)),0) from permintaan_lab_raza where "
                + "tgl_permintaan='" + Valid.SetTgl(tglMinta.getSelectedItem() + "") + "' ", "MPL" + Valid.SetTgl(tglMinta.getSelectedItem() + "").replaceAll("-", ""), 5, noMinta);
    }
}
