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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.DlgCariDokter;

/**
 *
 * @author dosen
 */
public class RMPerencanaanPulang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, pps1, pps2;
    private ResultSet rs, rs1, rrs1, rrs2;
    private int i = 0, x = 0, pilihan = 0;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private String kodekamar = "", rencana1 = "", rencana2 = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMPerencanaanPulang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "waktu_simpan", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Lahir", "Umur", "Jns. Kelamin", "Pekerjaan", "Ruang Perawatan",
            "Agama", "No. Telp/HP", "Tgl. MRS", "Alamat", "Lama Rawat", "Tgl. Pulang", "Target Perawatan", "Keadaan Pulang", "Nama DPJP",
            "tgl_perkiraan_pulang", "target_perawatan", "stts_pulang", "rencana_kontrol1", "rencana_kontrol2", "hari1",
            "hari2", "tgl_kontrol1", "tgl_kontrol2", "jam_kontrol1", "jam_kontrol2", "tempat1", "tempat2", "terapi_pulang",
            "catatan", "tgl_surat", "nip_dpjp", "nm_keluarga_pasien"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPerencanaan.setModel(tabMode);
        tbPerencanaan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPerencanaan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 36; i++) {
            TableColumn column = tbPerencanaan.getColumnModel().getColumn(i);
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
                column.setPreferredWidth(75);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(100);
            } else if (i == 10) {
                column.setPreferredWidth(90);
            } else if (i == 11) {
                column.setPreferredWidth(75);
            } else if (i == 12) {
                column.setPreferredWidth(220);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(75);
            } else if (i == 15) {
                column.setPreferredWidth(220);
            } else if (i == 16) {
                column.setPreferredWidth(90);
            } else if (i == 17) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbPerencanaan.setDefaultRenderer(Object.class, new WarnaTable());
        
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

        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        Ttelp.setDocument(new batasInput((byte) 16).getOnlyAngka(Ttelp));
        TlamaRwt.setDocument(new batasInput((byte) 3).getOnlyAngka(TlamaRwt));
        Ttempat1.setDocument(new batasInput((int) 200).getKata(Ttempat1));
        Ttempat2.setDocument(new batasInput((int) 200).getKata(Ttempat2));
        TnmKeluarga.setDocument(new batasInput((int) 100).getKata(TnmKeluarga));
        
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
                if (akses.getform().equals("RMPerencanaanPulang")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        TnipDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString());
                        TnmDpjp.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        BtnDpjp.requestFocus();
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

        WindowTemplate = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        panelisi4 = new widget.panelisi();
        jLabel36 = new widget.Label();
        TCari1 = new widget.TextBox();
        BtnCari1 = new widget.Button();
        BtnCopas = new widget.Button();
        BtnCloseIn1 = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        Scroll3 = new widget.ScrollPane();
        tbTemplate = new widget.Table();
        Scroll4 = new widget.ScrollPane();
        Ttemplate = new widget.TextArea();
        internalFrame1 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel63 = new widget.Label();
        TrgRawat = new widget.TextBox();
        label15 = new widget.Label();
        TnipDpjp = new widget.TextBox();
        TnmDpjp = new widget.TextBox();
        BtnDpjp = new widget.Button();
        scrollPane13 = new widget.ScrollPane();
        Tterapi = new widget.TextArea();
        jLabel11 = new widget.Label();
        Tumur = new widget.TextBox();
        jLabel12 = new widget.Label();
        Tjk = new widget.TextBox();
        jLabel13 = new widget.Label();
        Tpekerjaan = new widget.TextBox();
        jLabel14 = new widget.Label();
        Tagama = new widget.TextBox();
        jLabel15 = new widget.Label();
        Ttelp = new widget.TextBox();
        jLabel16 = new widget.Label();
        Talamat = new widget.TextBox();
        jLabel17 = new widget.Label();
        TtglMrs = new widget.TextBox();
        jLabel64 = new widget.Label();
        TlamaRwt = new widget.TextBox();
        jLabel18 = new widget.Label();
        TtglPulang = new widget.Tanggal();
        jLabel65 = new widget.Label();
        Ttarget = new widget.TextBox();
        jLabel66 = new widget.Label();
        cmbPulang = new widget.ComboBox();
        jLabel67 = new widget.Label();
        chkRencana1 = new widget.CekBox();
        Thari1 = new widget.TextBox();
        jLabel20 = new widget.Label();
        TtglKontrol1 = new widget.Tanggal();
        jLabel86 = new widget.Label();
        cmbJam1 = new widget.ComboBox();
        cmbMnt1 = new widget.ComboBox();
        cmbDtk1 = new widget.ComboBox();
        jLabel69 = new widget.Label();
        Ttempat1 = new widget.TextBox();
        chkRencana2 = new widget.CekBox();
        Thari2 = new widget.TextBox();
        jLabel22 = new widget.Label();
        TtglKontrol2 = new widget.Tanggal();
        jLabel87 = new widget.Label();
        cmbJam2 = new widget.ComboBox();
        cmbMnt2 = new widget.ComboBox();
        cmbDtk2 = new widget.ComboBox();
        jLabel70 = new widget.Label();
        Ttempat2 = new widget.TextBox();
        jLabel71 = new widget.Label();
        scrollPane14 = new widget.ScrollPane();
        Tcatatan = new widget.TextArea();
        jLabel72 = new widget.Label();
        jLabel68 = new widget.Label();
        TnmKeluarga = new widget.TextBox();
        jLabel88 = new widget.Label();
        TtglTTd = new widget.Tanggal();
        BtnPaste = new widget.Button();
        BtnDataTerapi = new widget.Button();
        BtnDataCatatan = new widget.Button();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnResep = new widget.Button();
        BtnKeluar = new widget.Button();
        PanelInput1 = new javax.swing.JPanel();
        Scroll = new widget.ScrollPane();
        tbPerencanaan = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        WindowTemplate.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowTemplate.setName("WindowTemplate"); // NOI18N
        WindowTemplate.setUndecorated(true);
        WindowTemplate.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Assesmen Keperawatan Perioperatif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        panelisi4.setBackground(new java.awt.Color(255, 150, 255));
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 46));
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

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 250));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(452, 250));

        tbTemplate.setToolTipText("Silahkan klik salah satu data yang akan dipakai");
        tbTemplate.setName("tbTemplate"); // NOI18N
        tbTemplate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTemplateMouseClicked(evt);
            }
        });
        Scroll3.setViewportView(tbTemplate);

        jPanel1.add(Scroll3);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        Ttemplate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Baca Template Dipilih ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Ttemplate.setColumns(20);
        Ttemplate.setRows(5);
        Ttemplate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Ttemplate.setName("Ttemplate"); // NOI18N
        Scroll4.setViewportView(Ttemplate);

        jPanel1.add(Scroll4);

        internalFrame5.add(jPanel1, java.awt.BorderLayout.CENTER);

        WindowTemplate.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Perencanaan Pulang (Discharge Planning) ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(600, 402));

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 660));
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
        jLabel63.setBounds(0, 122, 140, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(145, 122, 615, 23);

        label15.setForeground(new java.awt.Color(0, 0, 0));
        label15.setText("DPJP Utama :");
        label15.setName("label15"); // NOI18N
        label15.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label15);
        label15.setBounds(0, 598, 140, 23);

        TnipDpjp.setEditable(false);
        TnipDpjp.setForeground(new java.awt.Color(0, 0, 0));
        TnipDpjp.setName("TnipDpjp"); // NOI18N
        TnipDpjp.setPreferredSize(new java.awt.Dimension(80, 23));
        FormInput.add(TnipDpjp);
        TnipDpjp.setBounds(145, 598, 150, 23);

        TnmDpjp.setEditable(false);
        TnmDpjp.setForeground(new java.awt.Color(0, 0, 0));
        TnmDpjp.setName("TnmDpjp"); // NOI18N
        TnmDpjp.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TnmDpjp);
        TnmDpjp.setBounds(300, 598, 400, 23);

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
        BtnDpjp.setBounds(700, 598, 28, 23);

        scrollPane13.setName("scrollPane13"); // NOI18N

        Tterapi.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tterapi.setColumns(20);
        Tterapi.setRows(5);
        Tterapi.setName("Tterapi"); // NOI18N
        Tterapi.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tterapi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TterapiKeyPressed(evt);
            }
        });
        scrollPane13.setViewportView(Tterapi);

        FormInput.add(scrollPane13);
        scrollPane13.setBounds(185, 346, 575, 130);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Umur :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 38, 140, 23);

        Tumur.setEditable(false);
        Tumur.setForeground(new java.awt.Color(0, 0, 0));
        Tumur.setName("Tumur"); // NOI18N
        FormInput.add(Tumur);
        Tumur.setBounds(145, 38, 80, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Jenis Kelamin :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(225, 38, 90, 23);

        Tjk.setEditable(false);
        Tjk.setForeground(new java.awt.Color(0, 0, 0));
        Tjk.setName("Tjk"); // NOI18N
        FormInput.add(Tjk);
        Tjk.setBounds(320, 38, 100, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Pekerjaan :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(422, 38, 70, 23);

        Tpekerjaan.setEditable(false);
        Tpekerjaan.setForeground(new java.awt.Color(0, 0, 0));
        Tpekerjaan.setName("Tpekerjaan"); // NOI18N
        FormInput.add(Tpekerjaan);
        Tpekerjaan.setBounds(498, 38, 260, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Agama :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 66, 140, 23);

        Tagama.setEditable(false);
        Tagama.setForeground(new java.awt.Color(0, 0, 0));
        Tagama.setName("Tagama"); // NOI18N
        FormInput.add(Tagama);
        Tagama.setBounds(145, 66, 140, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("No. Telp./HP :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(286, 66, 90, 23);

        Ttelp.setForeground(new java.awt.Color(0, 0, 0));
        Ttelp.setName("Ttelp"); // NOI18N
        Ttelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtelpKeyPressed(evt);
            }
        });
        FormInput.add(Ttelp);
        Ttelp.setBounds(380, 66, 200, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Alamat :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 94, 140, 23);

        Talamat.setEditable(false);
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        FormInput.add(Talamat);
        Talamat.setBounds(145, 94, 615, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Tgl. MRS :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(580, 66, 60, 23);

        TtglMrs.setEditable(false);
        TtglMrs.setForeground(new java.awt.Color(0, 0, 0));
        TtglMrs.setName("TtglMrs"); // NOI18N
        FormInput.add(TtglMrs);
        TtglMrs.setBounds(648, 66, 110, 23);

        jLabel64.setForeground(new java.awt.Color(0, 0, 0));
        jLabel64.setText("Perkiraaan Lama Rawat :");
        jLabel64.setName("jLabel64"); // NOI18N
        FormInput.add(jLabel64);
        jLabel64.setBounds(0, 150, 140, 23);

        TlamaRwt.setForeground(new java.awt.Color(0, 0, 0));
        TlamaRwt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TlamaRwt.setName("TlamaRwt"); // NOI18N
        TlamaRwt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TlamaRwtKeyPressed(evt);
            }
        });
        FormInput.add(TlamaRwt);
        TlamaRwt.setBounds(145, 150, 50, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("hari     Tgl. Perkiraan Pulang :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(202, 150, 150, 23);

        TtglPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-10-2024" }));
        TtglPulang.setDisplayFormat("dd-MM-yyyy");
        TtglPulang.setName("TtglPulang"); // NOI18N
        TtglPulang.setOpaque(false);
        TtglPulang.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglPulang);
        TtglPulang.setBounds(352, 150, 90, 23);

        jLabel65.setForeground(new java.awt.Color(0, 0, 0));
        jLabel65.setText("Target Perawatan :");
        jLabel65.setName("jLabel65"); // NOI18N
        FormInput.add(jLabel65);
        jLabel65.setBounds(0, 178, 140, 23);

        Ttarget.setForeground(new java.awt.Color(0, 0, 0));
        Ttarget.setName("Ttarget"); // NOI18N
        Ttarget.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtargetKeyPressed(evt);
            }
        });
        FormInput.add(Ttarget);
        Ttarget.setBounds(145, 178, 615, 23);

        jLabel66.setForeground(new java.awt.Color(0, 0, 0));
        jLabel66.setText("Pulang Dari RSUD Ratu Zalecha Martapura Dalam Keadaan :");
        jLabel66.setName("jLabel66"); // NOI18N
        FormInput.add(jLabel66);
        jLabel66.setBounds(0, 206, 340, 23);

        cmbPulang.setForeground(new java.awt.Color(0, 0, 0));
        cmbPulang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Dirujuk", "APS", "Meninggal >= 48 Jam", "Meninggal < 48 Jam", "Sembuh/BLPL", "Kabur", "Pindah Kamar" }));
        cmbPulang.setName("cmbPulang"); // NOI18N
        cmbPulang.setPreferredSize(new java.awt.Dimension(55, 28));
        FormInput.add(cmbPulang);
        cmbPulang.setBounds(347, 206, 140, 23);

        jLabel67.setForeground(new java.awt.Color(0, 0, 0));
        jLabel67.setText("Rencana Perawatan Lanjutan :");
        jLabel67.setName("jLabel67"); // NOI18N
        FormInput.add(jLabel67);
        jLabel67.setBounds(0, 234, 180, 23);

        chkRencana1.setBackground(new java.awt.Color(242, 242, 242));
        chkRencana1.setForeground(new java.awt.Color(0, 0, 0));
        chkRencana1.setText("1. Tgl. Kontrol : Hari");
        chkRencana1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRencana1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRencana1.setName("chkRencana1"); // NOI18N
        chkRencana1.setOpaque(false);
        chkRencana1.setPreferredSize(new java.awt.Dimension(220, 23));
        chkRencana1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRencana1ActionPerformed(evt);
            }
        });
        FormInput.add(chkRencana1);
        chkRencana1.setBounds(185, 234, 125, 23);

        Thari1.setEditable(false);
        Thari1.setForeground(new java.awt.Color(0, 0, 0));
        Thari1.setName("Thari1"); // NOI18N
        FormInput.add(Thari1);
        Thari1.setBounds(315, 234, 70, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(386, 234, 40, 23);

        TtglKontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-10-2024" }));
        TtglKontrol1.setDisplayFormat("dd-MM-yyyy");
        TtglKontrol1.setName("TtglKontrol1"); // NOI18N
        TtglKontrol1.setOpaque(false);
        TtglKontrol1.setPreferredSize(new java.awt.Dimension(90, 23));
        TtglKontrol1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglKontrol1ActionPerformed(evt);
            }
        });
        FormInput.add(TtglKontrol1);
        TtglKontrol1.setBounds(434, 234, 90, 23);

        jLabel86.setForeground(new java.awt.Color(0, 0, 0));
        jLabel86.setText("Jam :");
        jLabel86.setName("jLabel86"); // NOI18N
        FormInput.add(jLabel86);
        jLabel86.setBounds(550, 234, 53, 23);

        cmbJam1.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam1.setName("cmbJam1"); // NOI18N
        cmbJam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam1MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam1);
        cmbJam1.setBounds(608, 234, 45, 23);

        cmbMnt1.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt1.setName("cmbMnt1"); // NOI18N
        cmbMnt1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt1MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt1);
        cmbMnt1.setBounds(659, 234, 45, 23);

        cmbDtk1.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk1.setName("cmbDtk1"); // NOI18N
        cmbDtk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk1MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk1);
        cmbDtk1.setBounds(710, 234, 45, 23);

        jLabel69.setForeground(new java.awt.Color(0, 0, 0));
        jLabel69.setText("Tempat :");
        jLabel69.setName("jLabel69"); // NOI18N
        FormInput.add(jLabel69);
        jLabel69.setBounds(0, 262, 180, 23);

        Ttempat1.setForeground(new java.awt.Color(0, 0, 0));
        Ttempat1.setName("Ttempat1"); // NOI18N
        Ttempat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttempat1KeyPressed(evt);
            }
        });
        FormInput.add(Ttempat1);
        Ttempat1.setBounds(185, 262, 575, 23);

        chkRencana2.setBackground(new java.awt.Color(242, 242, 242));
        chkRencana2.setForeground(new java.awt.Color(0, 0, 0));
        chkRencana2.setText("2. Tgl. Kontrol : Hari");
        chkRencana2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRencana2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRencana2.setName("chkRencana2"); // NOI18N
        chkRencana2.setOpaque(false);
        chkRencana2.setPreferredSize(new java.awt.Dimension(220, 23));
        chkRencana2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRencana2ActionPerformed(evt);
            }
        });
        FormInput.add(chkRencana2);
        chkRencana2.setBounds(185, 290, 125, 23);

        Thari2.setEditable(false);
        Thari2.setForeground(new java.awt.Color(0, 0, 0));
        Thari2.setName("Thari2"); // NOI18N
        FormInput.add(Thari2);
        Thari2.setBounds(315, 290, 70, 23);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Tgl. :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(386, 290, 40, 23);

        TtglKontrol2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-10-2024" }));
        TtglKontrol2.setDisplayFormat("dd-MM-yyyy");
        TtglKontrol2.setName("TtglKontrol2"); // NOI18N
        TtglKontrol2.setOpaque(false);
        TtglKontrol2.setPreferredSize(new java.awt.Dimension(90, 23));
        TtglKontrol2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtglKontrol2ActionPerformed(evt);
            }
        });
        FormInput.add(TtglKontrol2);
        TtglKontrol2.setBounds(434, 290, 90, 23);

        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Jam :");
        jLabel87.setName("jLabel87"); // NOI18N
        FormInput.add(jLabel87);
        jLabel87.setBounds(550, 290, 53, 23);

        cmbJam2.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam2.setName("cmbJam2"); // NOI18N
        cmbJam2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJam2MouseReleased(evt);
            }
        });
        FormInput.add(cmbJam2);
        cmbJam2.setBounds(608, 290, 45, 23);

        cmbMnt2.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt2.setName("cmbMnt2"); // NOI18N
        cmbMnt2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMnt2MouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt2);
        cmbMnt2.setBounds(659, 290, 45, 23);

        cmbDtk2.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk2.setName("cmbDtk2"); // NOI18N
        cmbDtk2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtk2MouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk2);
        cmbDtk2.setBounds(710, 290, 45, 23);

        jLabel70.setForeground(new java.awt.Color(0, 0, 0));
        jLabel70.setText("Tempat :");
        jLabel70.setName("jLabel70"); // NOI18N
        FormInput.add(jLabel70);
        jLabel70.setBounds(0, 318, 180, 23);

        Ttempat2.setForeground(new java.awt.Color(0, 0, 0));
        Ttempat2.setName("Ttempat2"); // NOI18N
        Ttempat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Ttempat2KeyPressed(evt);
            }
        });
        FormInput.add(Ttempat2);
        Ttempat2.setBounds(185, 318, 575, 23);

        jLabel71.setForeground(new java.awt.Color(0, 0, 0));
        jLabel71.setText("3. Terapi Pulang :");
        jLabel71.setName("jLabel71"); // NOI18N
        FormInput.add(jLabel71);
        jLabel71.setBounds(0, 346, 180, 23);

        scrollPane14.setName("scrollPane14"); // NOI18N

        Tcatatan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tcatatan.setColumns(20);
        Tcatatan.setRows(5);
        Tcatatan.setName("Tcatatan"); // NOI18N
        Tcatatan.setPreferredSize(new java.awt.Dimension(162, 1500));
        Tcatatan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TcatatanKeyPressed(evt);
            }
        });
        scrollPane14.setViewportView(Tcatatan);

        FormInput.add(scrollPane14);
        scrollPane14.setBounds(185, 482, 575, 110);

        jLabel72.setForeground(new java.awt.Color(0, 0, 0));
        jLabel72.setText("4. Catatan Lain :");
        jLabel72.setName("jLabel72"); // NOI18N
        FormInput.add(jLabel72);
        jLabel72.setBounds(0, 482, 180, 23);

        jLabel68.setForeground(new java.awt.Color(0, 0, 0));
        jLabel68.setText("Nama Keluarga Pasien :");
        jLabel68.setName("jLabel68"); // NOI18N
        FormInput.add(jLabel68);
        jLabel68.setBounds(0, 626, 140, 23);

        TnmKeluarga.setForeground(new java.awt.Color(0, 0, 0));
        TnmKeluarga.setName("TnmKeluarga"); // NOI18N
        TnmKeluarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmKeluargaKeyPressed(evt);
            }
        });
        FormInput.add(TnmKeluarga);
        TnmKeluarga.setBounds(145, 626, 390, 23);

        jLabel88.setForeground(new java.awt.Color(0, 0, 0));
        jLabel88.setText("Tgl. Tanda Tangan :");
        jLabel88.setName("jLabel88"); // NOI18N
        FormInput.add(jLabel88);
        jLabel88.setBounds(543, 626, 120, 23);

        TtglTTd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "06-10-2024" }));
        TtglTTd.setDisplayFormat("dd-MM-yyyy");
        TtglTTd.setName("TtglTTd"); // NOI18N
        TtglTTd.setOpaque(false);
        TtglTTd.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglTTd);
        TtglTTd.setBounds(670, 626, 90, 23);

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
        BtnPaste.setBounds(770, 376, 90, 23);

        BtnDataTerapi.setForeground(new java.awt.Color(0, 0, 0));
        BtnDataTerapi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataTerapi.setMnemonic('2');
        BtnDataTerapi.setText("Template");
        BtnDataTerapi.setToolTipText("Alt+2");
        BtnDataTerapi.setName("BtnDataTerapi"); // NOI18N
        BtnDataTerapi.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDataTerapi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataTerapiActionPerformed(evt);
            }
        });
        FormInput.add(BtnDataTerapi);
        BtnDataTerapi.setBounds(770, 346, 100, 23);

        BtnDataCatatan.setForeground(new java.awt.Color(0, 0, 0));
        BtnDataCatatan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDataCatatan.setMnemonic('2');
        BtnDataCatatan.setText("Template");
        BtnDataCatatan.setToolTipText("Alt+2");
        BtnDataCatatan.setName("BtnDataCatatan"); // NOI18N
        BtnDataCatatan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDataCatatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDataCatatanActionPerformed(evt);
            }
        });
        FormInput.add(BtnDataCatatan);
        BtnDataCatatan.setBounds(770, 482, 100, 23);

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

        BtnResep.setForeground(new java.awt.Color(0, 0, 0));
        BtnResep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Vial-Pills.png"))); // NOI18N
        BtnResep.setMnemonic('R');
        BtnResep.setText("Resep Obat");
        BtnResep.setToolTipText("Alt+R");
        BtnResep.setName("BtnResep"); // NOI18N
        BtnResep.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnResep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnResepActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnResep);

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

        PanelInput1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Data Perencanaan Pulang ]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        PanelInput1.setName("PanelInput1"); // NOI18N
        PanelInput1.setOpaque(false);
        PanelInput1.setPreferredSize(new java.awt.Dimension(700, 700));
        PanelInput1.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(600, 402));

        tbPerencanaan.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki/dihapus");
        tbPerencanaan.setName("tbPerencanaan"); // NOI18N
        tbPerencanaan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPerencanaanMouseClicked(evt);
            }
        });
        tbPerencanaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPerencanaanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPerencanaan);

        PanelInput1.add(Scroll, java.awt.BorderLayout.CENTER);

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

        PanelInput1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput1, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().equals("")) {
            Valid.textKosong(TNoRw, "Nama Pasien");
        } else {
            try {
                cekData();
                if (Sequel.menyimpantf("perencanaan_pulang_ranap", "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 23, new String[]{
                    TNoRw.getText(), TrgRawat.getText(), Ttelp.getText(), TlamaRwt.getText(), Valid.SetTgl(TtglPulang.getSelectedItem() + ""),
                    Ttarget.getText(), cmbPulang.getSelectedItem().toString(), rencana1, rencana2, Thari1.getText(), Thari2.getText(),
                    Valid.SetTgl(TtglKontrol1.getSelectedItem() + ""), Valid.SetTgl(TtglKontrol2.getSelectedItem() + ""),
                    cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                    cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                    Ttempat1.getText(), Ttempat2.getText(), Tterapi.getText(), Tcatatan.getText(), Valid.SetTgl(TtglTTd.getSelectedItem() + ""),
                    TnipDpjp.getText(), TnmKeluarga.getText(), Sequel.cariIsi("select now()")
                }) == true) {

                    TCari.setText(TNoRw.getText());
                    emptTeks();
                    tampil();
                }
            } catch (Exception e) {
                System.out.println("Simpan Perencanaan Pulang (Discharge Planning) : " + e);
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
            if (tbPerencanaan.getSelectedRow() > -1) {
                cekData();
                try {
                    if (Sequel.mengedittf("perencanaan_pulang_ranap", "waktu_simpan=?", "no_tlp=?, perkiraan_lama_rawat=?, tgl_perkiraan_pulang=?, "
                            + "target_perawatan=?, stts_pulang=?, rencana_kontrol1=?, rencana_kontrol2=?, hari1=?, hari2=?, tgl_kontrol1=?, tgl_kontrol2=?, "
                            + "jam_kontrol1=?, jam_kontrol2=?, tempat1=?, tempat2=?, terapi_pulang=?, catatan=?, tgl_surat=?, nip_dpjp=?, nm_keluarga_pasien=?", 21, new String[]{
                                Ttelp.getText(), TlamaRwt.getText(), Valid.SetTgl(TtglPulang.getSelectedItem() + ""),
                                Ttarget.getText(), cmbPulang.getSelectedItem().toString(), rencana1, rencana2, Thari1.getText(), Thari2.getText(),
                                Valid.SetTgl(TtglKontrol1.getSelectedItem() + ""), Valid.SetTgl(TtglKontrol2.getSelectedItem() + ""),
                                cmbJam1.getSelectedItem() + ":" + cmbMnt1.getSelectedItem() + ":" + cmbDtk1.getSelectedItem(),
                                cmbJam2.getSelectedItem() + ":" + cmbMnt2.getSelectedItem() + ":" + cmbDtk2.getSelectedItem(),
                                Ttempat1.getText(), Ttempat2.getText(), Tterapi.getText(), Tcatatan.getText(), Valid.SetTgl(TtglTTd.getSelectedItem() + ""),
                                TnipDpjp.getText(), TnmKeluarga.getText(),
                                tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 0).toString()
                            }) == true) {

                        TCari.setText(TNoRw.getText());
                        tampil();
                        emptTeks();
                    }
                } catch (Exception e) {
                    System.out.println("Ganti Perencanaan Pulang (Discharge Planning) : " + e);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
                tbPerencanaan.requestFocus();
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

    private void tbPerencanaanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPerencanaanMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPerencanaanMouseClicked

    private void tbPerencanaanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPerencanaanKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPerencanaanKeyPressed

    private void BtnDpjpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDpjpActionPerformed
        akses.setform("RMPerencanaanPulang");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDpjpActionPerformed

    private void TterapiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TterapiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            Tcatatan.requestFocus();
        }
    }//GEN-LAST:event_TterapiKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbPerencanaan.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from perencanaan_pulang_ranap where waktu_simpan=?", 1, new String[]{
                    tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 0).toString()
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
            tbPerencanaan.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPerencanaan.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            
            param.put("umur", Tumur.getText() + "/" + Tjk.getText());
            param.put("agama", Tagama.getText());
            param.put("alamat", Talamat.getText());
            param.put("tglmrs", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + TNoRw.getText() + "'")));
            param.put("pekerjaan", Tpekerjaan.getText());
            param.put("notelp", Ttelp.getText());
            param.put("lamarwt", TlamaRwt.getText() + " hari");
            param.put("tglPerkiraanPlg", Valid.SetTglINDONESIA(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 18).toString()));
            param.put("target", Ttarget.getText());
            param.put("keadaanPulang", cmbPulang.getSelectedItem().toString());
            
            if (chkRencana1.isSelected() == true) {
                param.put("rencana1", "1. Tanggal Kontrol : " + Thari1.getText() + ", " + Valid.SetTglINDONESIA(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 25).toString())
                        + " Tempat : " + Ttempat1.getText());
            } else {
                param.put("rencana1", "1. Tanggal Kontrol : ..... Tempat : .....");
            }
            
            if (chkRencana2.isSelected() == true) {
                param.put("rencana2", "2. Tanggal Kontrol : " + Thari2.getText() + ", " + Valid.SetTglINDONESIA(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 26).toString())
                        + " Tempat : " + Ttempat2.getText());
            } else {
                param.put("rencana2", "2. Tanggal Kontrol : ..... Tempat : .....");
            }
            
            param.put("terapi", Tterapi.getText() + "\n");
            param.put("catatan", Tcatatan.getText() + "\n");
            param.put("tglTtd", "Martapura, " + Valid.SetTglINDONESIA(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 33).toString()));
            param.put("dpjp", "(" + TnmDpjp.getText() + ")");
            param.put("keluargaPasien", "(" + TnmKeluarga.getText() + ")");

            Valid.MyReport("rptPerencanaanPulang.jasper", "report", "::[ Lembar Perencanaan Pulang (Discharge Planning) ]::",
                "SELECT now() tanggal", param);

            tampil();
            emptTeks();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik/pilih salah satu datanya terlebih dulu pada tabel..!!!!");
            tbPerencanaan.requestFocus();
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

    private void cmbJam1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam1MouseReleased
        AutoCompleteDecorator.decorate(cmbJam1);
    }//GEN-LAST:event_cmbJam1MouseReleased

    private void cmbMnt1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt1MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt1);
    }//GEN-LAST:event_cmbMnt1MouseReleased

    private void cmbDtk1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk1MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk1);
    }//GEN-LAST:event_cmbDtk1MouseReleased

    private void cmbJam2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJam2MouseReleased
        AutoCompleteDecorator.decorate(cmbJam2);
    }//GEN-LAST:event_cmbJam2MouseReleased

    private void cmbMnt2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMnt2MouseReleased
        AutoCompleteDecorator.decorate(cmbMnt2);
    }//GEN-LAST:event_cmbMnt2MouseReleased

    private void cmbDtk2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtk2MouseReleased
        AutoCompleteDecorator.decorate(cmbDtk2);
    }//GEN-LAST:event_cmbDtk2MouseReleased

    private void TcatatanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TcatatanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            BtnDpjp.requestFocus();
        }
    }//GEN-LAST:event_TcatatanKeyPressed

    private void BtnPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPasteActionPerformed
        if (akses.getPasteData().equals("")) {
            JOptionPane.showMessageDialog(null, "Silahkan copy dulu data yg. dipilih..!!!!");
        } else {
            if (Tterapi.getText().equals("")) {
                Tterapi.setText(akses.getPasteData());
                akses.setCopyData("");
            } else {
                Tterapi.setText(Tterapi.getText() + "\n\n" + akses.getPasteData());
                akses.setCopyData("");
            }
        }
    }//GEN-LAST:event_BtnPasteActionPerformed

    private void TlamaRwtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TlamaRwtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglPulang.requestFocus();
        }
    }//GEN-LAST:event_TlamaRwtKeyPressed

    private void TtargetKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtargetKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbPulang.requestFocus();
        }
    }//GEN-LAST:event_TtargetKeyPressed

    private void Ttempat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttempat1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            chkRencana2.requestFocus();
        }
    }//GEN-LAST:event_Ttempat1KeyPressed

    private void Ttempat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Ttempat2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tterapi.requestFocus();
        }
    }//GEN-LAST:event_Ttempat2KeyPressed

    private void TnmKeluargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmKeluargaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TtglTTd.requestFocus();
        }
    }//GEN-LAST:event_TnmKeluargaKeyPressed

    private void TtglKontrol1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglKontrol1ActionPerformed
        Thari1.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol1.getSelectedItem() + "") + "','%W')"));
    }//GEN-LAST:event_TtglKontrol1ActionPerformed

    private void TtglKontrol2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtglKontrol2ActionPerformed
        Thari2.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol2.getSelectedItem() + "") + "','%W')"));
    }//GEN-LAST:event_TtglKontrol2ActionPerformed

    private void TtelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtelpKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TlamaRwt.requestFocus();
        }
    }//GEN-LAST:event_TtelpKeyPressed

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

    private void BtnDataTerapiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataTerapiActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 1;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Terapi Pulang ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDataTerapiActionPerformed

    private void BtnDataCatatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDataCatatanActionPerformed
        pilihan = 0;
        Ttemplate.setText("");
        TCari1.setText("");

        pilihan = 2;
        tampilTemplate();
        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Template Catatan Lain ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12)));
        WindowTemplate.setSize(998, internalFrame1.getHeight() - 40);
        WindowTemplate.setLocationRelativeTo(internalFrame1);
        WindowTemplate.setAlwaysOnTop(false);
        WindowTemplate.setVisible(true);
        TCari1.requestFocus();
    }//GEN-LAST:event_BtnDataCatatanActionPerformed

    private void chkRencana1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRencana1ActionPerformed
        TtglKontrol1.setDate(new Date());
        Thari1.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol1.getSelectedItem() + "") + "','%W')"));
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Ttempat1.setText("");
        if (chkRencana1.isSelected() == true) {
            TtglKontrol1.setEnabled(true);
            TtglKontrol1.requestFocus();
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            Ttempat1.setEnabled(true);
        } else {
            TtglKontrol1.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            Ttempat1.setEnabled(false);
        }
    }//GEN-LAST:event_chkRencana1ActionPerformed

    private void chkRencana2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRencana2ActionPerformed
        TtglKontrol2.setDate(new Date());
        Thari2.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol2.getSelectedItem() + "") + "','%W')"));
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        Ttempat2.setText("");
        if (chkRencana2.isSelected() == true) {
            TtglKontrol2.setEnabled(true);
            TtglKontrol2.requestFocus();
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            Ttempat2.setEnabled(true);
        } else {
            TtglKontrol2.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            Ttempat2.setEnabled(false);
        }
    }//GEN-LAST:event_chkRencana2ActionPerformed

    private void BtnResepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnResepActionPerformed
        kodekamar = "";
        kodekamar = Sequel.cariIsi("select ki.kd_kamar from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
            + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat='" + TNoRw.getText() + "' "
            + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1");

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        akses.setform("RMPerencanaanPulang");
        DlgCatatanResep form = new DlgCatatanResep(null, false);
        form.isCek();
        form.setData(TNoRw.getText(), "ranap");
        form.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        form.setLocationRelativeTo(internalFrame1);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnResepActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMPerencanaanPulang dialog = new RMPerencanaanPulang(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCloseIn1;
    private widget.Button BtnCopas;
    private widget.Button BtnDataCatatan;
    private widget.Button BtnDataTerapi;
    private widget.Button BtnDpjp;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPaste;
    private widget.Button BtnPrint;
    private widget.Button BtnResep;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private javax.swing.JPanel PanelInput1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    public widget.TextBox TCari;
    private widget.TextBox TCari1;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tagama;
    private widget.TextBox Talamat;
    private widget.TextArea Tcatatan;
    private widget.TextBox Thari1;
    private widget.TextBox Thari2;
    private widget.TextBox Tjk;
    private widget.TextBox TlamaRwt;
    private widget.TextBox TnipDpjp;
    private widget.TextBox TnmDpjp;
    private widget.TextBox TnmKeluarga;
    private widget.TextBox Tpekerjaan;
    private widget.TextBox TrgRawat;
    private widget.TextBox Ttarget;
    private widget.TextBox Ttelp;
    private widget.TextBox Ttempat1;
    private widget.TextBox Ttempat2;
    private widget.TextArea Ttemplate;
    private widget.TextArea Tterapi;
    private widget.Tanggal TtglKontrol1;
    private widget.Tanggal TtglKontrol2;
    private widget.TextBox TtglMrs;
    private widget.Tanggal TtglPulang;
    private widget.Tanggal TtglTTd;
    private widget.TextBox Tumur;
    private javax.swing.JDialog WindowTemplate;
    private widget.CekBox chkRencana1;
    private widget.CekBox chkRencana2;
    private widget.ComboBox cmbDtk1;
    private widget.ComboBox cmbDtk2;
    private widget.ComboBox cmbJam1;
    private widget.ComboBox cmbJam2;
    private widget.ComboBox cmbMnt1;
    private widget.ComboBox cmbMnt2;
    private widget.ComboBox cmbPulang;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel20;
    private widget.Label jLabel22;
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
    private widget.Label jLabel86;
    private widget.Label jLabel87;
    private widget.Label jLabel88;
    private javax.swing.JPanel jPanel1;
    private widget.Label label15;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane13;
    private widget.ScrollPane scrollPane14;
    private widget.Table tbPerencanaan;
    private widget.Table tbTemplate;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            if (TCari.getText().equals("")) {
                ps = koneksi.prepareStatement("SELECT pp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.umurdaftar, if(rp.sttsumur='Th','Tahun',if(rp.sttsumur='Bl','Bulan','Hari')) stsUmur, "
                        + "if(p.jk='L','Laki-laki','Perempuan') jenkel, p.pekerjaan, p.agama, p.no_tlp, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmrs, "
                        + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kota ',kb.nm_kab) almat, date_format(pp.tgl_perkiraan_pulang,'%d-%m-%Y') tglPulang, "
                        + "pg.nama nmDpjp from perencanaan_pulang_ranap pp inner join reg_periksa rp on rp.no_rawat=pp.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pp.nip_dpjp "
                        + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab WHERE "
                        + "pp.no_rawat LIKE ? or "
                        + "p.no_rkm_medis LIKE ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "pg.nama LIKE ? or "
                        + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kota ',kb.nm_kab) LIKE ? "
                        + "ORDER BY pp.tgl_surat desc limit 100");
            } else {
                ps = koneksi.prepareStatement("SELECT pp.*, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(p.tgl_lahir,'%d-%m-%Y') tgllahir, rp.umurdaftar, if(rp.sttsumur='Th','Tahun',if(rp.sttsumur='Bl','Bulan','Hari')) stsUmur, "
                        + "if(p.jk='L','Laki-laki','Perempuan') jenkel, p.pekerjaan, p.agama, p.no_tlp, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmrs, "
                        + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kota ',kb.nm_kab) almat, date_format(pp.tgl_perkiraan_pulang,'%d-%m-%Y') tglPulang, "
                        + "pg.nama nmDpjp from perencanaan_pulang_ranap pp inner join reg_periksa rp on rp.no_rawat=pp.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis inner join pegawai pg on pg.nik=pp.nip_dpjp "
                        + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec inner join kabupaten kb on kb.kd_kab=p.kd_kab WHERE "
                        + "pp.no_rawat LIKE ? or "
                        + "p.no_rkm_medis LIKE ? or "
                        + "p.nm_pasien LIKE ? or "
                        + "pg.nama LIKE ? or "
                        + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kota ',kb.nm_kab) LIKE ? "
                        + "ORDER BY pp.tgl_surat desc");
            }
            try {
                ps.setString(1, "%" + TCari.getText() + "%");                
                ps.setString(2, "%" + TCari.getText() + "%");
                ps.setString(3, "%" + TCari.getText() + "%");
                ps.setString(4, "%" + TCari.getText() + "%");
                ps.setString(5, "%" + TCari.getText() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("waktu_simpan"),
                        rs.getString("no_rawat"),                        
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgllahir"),
                        rs.getString("umurdaftar") + " " + rs.getString("stsUmur"),
                        rs.getString("jenkel"),
                        rs.getString("pekerjaan"),
                        rs.getString("ruang_rawat"),
                        rs.getString("agama"),
                        rs.getString("no_tlp"),
                        rs.getString("tglmrs"),
                        rs.getString("almat"),
                        rs.getString("perkiraan_lama_rawat"),
                        rs.getString("tglPulang"),
                        rs.getString("target_perawatan"),
                        rs.getString("stts_pulang"),
                        rs.getString("nmDpjp"),
                        rs.getString("tgl_perkiraan_pulang"),
                        rs.getString("target_perawatan"),
                        rs.getString("stts_pulang"),
                        rs.getString("rencana_kontrol1"),
                        rs.getString("rencana_kontrol2"),
                        rs.getString("hari1"),
                        rs.getString("hari2"),
                        rs.getString("tgl_kontrol1"),
                        rs.getString("tgl_kontrol2"),
                        rs.getString("jam_kontrol1"),
                        rs.getString("jam_kontrol2"),
                        rs.getString("tempat1"),
                        rs.getString("tempat2"),
                        rs.getString("terapi_pulang"),
                        rs.getString("catatan"),
                        rs.getString("tgl_surat"),
                        rs.getString("nip_dpjp"),
                        rs.getString("nm_keluarga_pasien")
                    });
                }                
            } catch (Exception e) {
                System.out.println("rekammedis.RMPerencanaanPulang.tampil() : " + e);
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
        TlamaRwt.setText("");
        TlamaRwt.requestFocus();
        TtglPulang.setDate(new Date());
        Ttarget.setText("");
        cmbPulang.setSelectedIndex(0);
        
        chkRencana1.setSelected(false);
        TtglKontrol1.setDate(new Date());
        Thari1.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol1.getSelectedItem() + "") + "','%W')"));
        cmbJam1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt1.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk1.setSelectedIndex(0);
        Ttempat1.setText("");
        TtglKontrol1.setEnabled(false);
        cmbJam1.setEnabled(false);
        cmbMnt1.setEnabled(false);
        cmbDtk1.setEnabled(false);
        Ttempat1.setEnabled(false);
        
        chkRencana2.setSelected(false);
        TtglKontrol2.setDate(new Date());
        Thari2.setText(Sequel.hariINDONESIA("SELECT DATE_FORMAT('" + Valid.SetTgl(TtglKontrol2.getSelectedItem() + "") + "','%W')"));
        cmbJam2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt2.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk2.setSelectedIndex(0);
        Ttempat2.setText("");
        TtglKontrol2.setEnabled(false);
        cmbJam2.setEnabled(false);
        cmbMnt2.setEnabled(false);
        cmbDtk2.setEnabled(false);
        Ttempat2.setEnabled(false);
        
        Tterapi.setText("");
        Tcatatan.setText("");
        TnipDpjp.setText("-");
        TnmDpjp.setText("-");
        TnmKeluarga.setText("");
        TtglTTd.setDate(new Date());
    }

    private void getData() {
        rencana1 = "";
        rencana2 = "";
        if (tbPerencanaan.getSelectedRow() != -1) {
            TNoRw.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 1).toString());
            TNoRM.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 2).toString());
            TPasien.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 3).toString());
            Tumur.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 5).toString());
            Tjk.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 6).toString());
            Tpekerjaan.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 7).toString());
            Tagama.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 9).toString());
            Ttelp.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 10).toString());
            TtglMrs.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 11).toString());
            Talamat.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 12).toString());
            TrgRawat.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 8).toString());
            TlamaRwt.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 13).toString());
            Valid.SetTgl(TtglPulang, tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 18).toString());
            Ttarget.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 15).toString());
            cmbPulang.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 20).toString());
            rencana1 = tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 21).toString();
            rencana2 = tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 22).toString();
            Thari1.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 23).toString());
            Thari2.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 24).toString());
            Valid.SetTgl(TtglKontrol1, tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 25).toString());
            Valid.SetTgl(TtglKontrol2, tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 26).toString());
            cmbJam1.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 27).toString().substring(0, 2));
            cmbMnt1.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 27).toString().substring(3, 5));
            cmbDtk1.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 27).toString().substring(6, 8));
            cmbJam2.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 28).toString().substring(0, 2));
            cmbMnt2.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 28).toString().substring(3, 5));
            cmbDtk2.setSelectedItem(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 28).toString().substring(6, 8));
            Ttempat1.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 29).toString());
            Ttempat2.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 30).toString());
            Tterapi.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 31).toString());
            Tcatatan.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 32).toString());
            TnipDpjp.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 34).toString());
            TnmDpjp.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 17).toString());
            TnmKeluarga.setText(tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 35).toString());
            Valid.SetTgl(TtglTTd, tbPerencanaan.getValueAt(tbPerencanaan.getSelectedRow(), 33).toString());
            dataCek();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnResep.setEnabled(akses.getresep_dokter());
    }
    
    private void dataCek() {
        if (rencana1.equals("ya")) {
            chkRencana1.setSelected(true);
            TtglKontrol1.setEnabled(true);
            cmbJam1.setEnabled(true);
            cmbMnt1.setEnabled(true);
            cmbDtk1.setEnabled(true);
            Ttempat1.setEnabled(true);
        } else {
            chkRencana1.setSelected(false);
            TtglKontrol1.setEnabled(false);
            cmbJam1.setEnabled(false);
            cmbMnt1.setEnabled(false);
            cmbDtk1.setEnabled(false);
            Ttempat1.setEnabled(false);
        }
        
        if (rencana2.equals("ya")) {
            chkRencana2.setSelected(true);
            TtglKontrol2.setEnabled(true);
            cmbJam2.setEnabled(true);
            cmbMnt2.setEnabled(true);
            cmbDtk2.setEnabled(true);
            Ttempat2.setEnabled(true);
        } else {
            chkRencana2.setSelected(false);
            TtglKontrol2.setEnabled(false);
            cmbJam2.setEnabled(false);
            cmbMnt2.setEnabled(false);
            cmbDtk2.setEnabled(false);
            Ttempat2.setEnabled(false);
        }
    }
    
    public void setData(String norw, String ruangan) {
        TNoRw.setText(norw);
        TrgRawat.setText(ruangan);
        isPasien();
        
        if (Sequel.cariInteger("select count(-1) from ringkasan_pulang_ranap where no_rawat='" + norw + "'") > 0) {
            cmbPulang.setSelectedItem(Sequel.cariIsi("select stts_pulang from ringkasan_pulang_ranap where no_rawat='" + norw + "'"));
            Tterapi.setText(Sequel.cariIsi("select terapi_pulang from ringkasan_pulang_ranap where no_rawat='" + norw + "'"));
        } else if (Sequel.cariInteger("select count(-1) from kamar_inap where no_rawat='" + norw + "' and stts_pulang not in ('-','Pindah Kamar')") > 0) {
            cmbPulang.setSelectedItem(Sequel.cariIsi("select stts_pulang from kamar_inap where no_rawat='" + norw + "' and stts_pulang not in ('-','Pindah Kamar')"));
            Tterapi.setText(Sequel.cariIsi("select ifnull(terapi_pulang,'') from ringkasan_pulang_ranap where no_rawat='" + norw + "'"));
        } else {
            cmbPulang.setSelectedIndex(0);
        }
        
        TnipDpjp.setText(Sequel.cariIsi("select ifnull(kd_dokter,'-') from dpjp_ranap where no_rawat='" + norw + "'"));
        TnmDpjp.setText(Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='" + TnipDpjp.getText() + "'"));
    }
    
    private void tampilTemplate() {
        Valid.tabelKosong(tabMode1);
        try {
            if (pilihan == 1) {
                pps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from perencanaan_pulang_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.terapi_pulang<>'' and p.no_rkm_medis like ? OR "
                        + "pa.terapi_pulang<>'' and p.nm_pasien like ? OR "
                        + "pa.terapi_pulang<>'' and pa.terapi_pulang like ? ORDER BY pa.tgl_surat desc limit 100");
            } else if (pilihan == 2) {
                pps2 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, pa.* from perencanaan_pulang_ranap pa "
                        + "inner join reg_periksa rp on rp.no_rawat=pa.no_rawat "
                        + "inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis where "
                        + "pa.catatan<>'' and p.no_rkm_medis like ? OR "
                        + "pa.catatan<>'' and p.nm_pasien like ? OR "
                        + "pa.catatan<>'' and pa.catatan like ? ORDER BY pa.tgl_surat desc limit 100");
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
                            rrs1.getString("terapi_pulang")
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
                            rrs2.getString("catatan")
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
                } 

                if (pps1 != null) {
                    pps1.close();
                } else if (pps2 != null) {
                    pps2.close();
                } 
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void copas() {
        if (pilihan == 1) {
            Tterapi.setText(Ttemplate.getText());
        } else if (pilihan == 2) {
            Tcatatan.setText(Ttemplate.getText());
        }
    }
    
    private void cekData() {
        if (chkRencana1.isSelected() == true) {
            rencana1 = "ya";
        } else {
            rencana1 = "tidak";
        }
        
        if (chkRencana2.isSelected() == true) {
            rencana2 = "ya";
        } else {
            rencana2 = "tidak";
        }
        
        if (TnipDpjp.getText().equals("")) {
            TnipDpjp.setText("-");
        } else {
            TnipDpjp.setText(TnipDpjp.getText());
        }
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, "
                    + "rp.umurdaftar, if(rp.sttsumur='Th','Tahun',if(rp.sttsumur='Bl','Bulan','Hari')) stsUmur, p.pekerjaan, "
                    + "p.agama, p.no_tlp, date_format(rp.tgl_registrasi,'%d-%m-%Y') tglmrs, "
                    + "concat(p.alamat,', Kel. ',kl.nm_kel,', Kec. ',kc.nm_kec,', Kab./Kota ',kb.nm_kab) almat "
                    + "from reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join kelurahan kl on kl.kd_kel=p.kd_kel inner join kecamatan kc on kc.kd_kec=p.kd_kec "
                    + "inner join kabupaten kb on kb.kd_kab=p.kd_kab where rp.no_rawat='" + TNoRw.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TNoRM.setText(rs1.getString("no_rkm_medis"));
                    TPasien.setText(rs1.getString("nm_pasien"));
                    Tumur.setText(rs1.getString("umurdaftar") + " " + rs1.getString("stsUmur"));
                    Tjk.setText(rs1.getString("jenkel"));
                    Tpekerjaan.setText(rs1.getString("pekerjaan"));
                    Tagama.setText(rs1.getString("agama"));
                    Ttelp.setText(rs1.getString("no_tlp"));
                    TtglMrs.setText(rs1.getString("tglmrs"));
                    Talamat.setText(rs1.getString("almat"));
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
            System.out.println("Notifikasi : " + e);
        }
    }
}
