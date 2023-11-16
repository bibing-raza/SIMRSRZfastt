package permintaan;

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
import simrskhanza.DlgCariDokter;
import simrskhanza.DlgMasterFaskes;

/**
 *
 * @author dosen
 */
public class DlgPermintaanLabRAZA extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, psPasien, psLab, ps1;
    private ResultSet rs, rsPasien, rsLab, rs1;
    private DlgCariDokter dokter = new DlgCariDokter(null, false);
    private int i = 0, x = 0, jlhOrder = 0, cekDiterima = 0, cekDRinap = 0;
    private String kddokter = "", sttsRawat = "", kdPoli = "", cekNORW = "",
            cekNOMINTA = "", diperiksa = "", kddokterFIX = "", cito = "", nokirim = "", tgl = "", jam = "";

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgPermintaanLabRAZA(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No.", "Nama Pemeriksaan Lab.", "Tgl. Permintaan", "Jam Permintaan", "Status",
            "norawat", "kddokter", "No. Permintaan", "CITO", "No. Kirim"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbMintaPeriksa.setModel(tabMode);
        tbMintaPeriksa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbMintaPeriksa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
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
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(100);
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
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "No.", "No. Kirim", "Tgl. Permintaan", "Jam Permintaan", "'tgl_permintaan",
            "jam_permintaan", "status", "Status Kirim", "Pada Saat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbNomor.setModel(tabMode2);
        tbNomor.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbNomor.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbNomor.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(104);
            } else if (i == 2) {
                column.setPreferredWidth(90);
            } else if (i == 3) {
                column.setPreferredWidth(90);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 5) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 6) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(70);
            } 
        }
        tbNomor.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.cariCepat().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {tampilNomor();}
                @Override
                public void removeUpdate(DocumentEvent e) {tampilNomor();}
                @Override
                public void changedUpdate(DocumentEvent e) {tampilNomor();}
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
                if (akses.getform().equals("DlgPermintaanLabRAZA")) {
                    if (dokter.getTable().getSelectedRow() != -1) {
                        kddokterFIX = dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 0).toString();
                        drPerujuk.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(), 1).toString());
                        btnDokter.requestFocus();
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

        TNoKirim = new widget.TextBox();
        noMinta = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnHapusNomor = new javax.swing.JMenuItem();
        MnCetakPermintaan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBaru = new widget.Button();
        BtnGanti = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        BtnKirim = new widget.Button();
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
        jLabel12 = new widget.Label();
        unit = new widget.Label();
        nmUnit = new widget.TextBox();
        jLabel13 = new widget.Label();
        drPerujuk = new widget.TextBox();
        TAlamat = new widget.TextBox();
        Scroll6 = new widget.ScrollPane();
        diagnos = new widget.TextArea();
        chkCito = new widget.CekBox();
        btnDokter = new widget.Button();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        Scroll4 = new widget.ScrollPane();
        tbNomor = new widget.Table();
        Scroll = new widget.ScrollPane();
        tbMintaPeriksa = new widget.Table();
        panelisi6 = new widget.panelisi();
        jLabel9 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        BtnCari1 = new widget.Button();
        jPanel2 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel64 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        Scroll33 = new widget.ScrollPane();
        tbMasterItem = new widget.Table();

        TNoKirim.setEditable(false);
        TNoKirim.setForeground(new java.awt.Color(0, 0, 0));
        TNoKirim.setName("TNoKirim"); // NOI18N

        noMinta.setEditable(false);
        noMinta.setForeground(new java.awt.Color(0, 0, 0));
        noMinta.setName("noMinta"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnHapusNomor.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusNomor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapusNomor.setText("Hapus Permintaan");
        MnHapusNomor.setName("MnHapusNomor"); // NOI18N
        MnHapusNomor.setPreferredSize(new java.awt.Dimension(170, 28));
        MnHapusNomor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusNomorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusNomor);

        MnCetakPermintaan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnCetakPermintaan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PrinterSettings.png"))); // NOI18N
        MnCetakPermintaan.setText("Cetak Permintaan");
        MnCetakPermintaan.setName("MnCetakPermintaan"); // NOI18N
        MnCetakPermintaan.setPreferredSize(new java.awt.Dimension(170, 28));
        MnCetakPermintaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnCetakPermintaanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnCetakPermintaan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Permintaan Pemeriksaan Laboratorium ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 57));
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
        panelGlass8.add(BtnBaru);

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
        panelGlass8.add(BtnHapus);

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

        BtnKirim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('T');
        BtnKirim.setText("Kirim Permintaan");
        BtnKirim.setToolTipText("Alt+T");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKirim);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(28, 232));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 204));
        jLabel4.setText("Pemeriksaan Diminta : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 198, 130, 23);

        nmPemeriksaan.setForeground(new java.awt.Color(0, 0, 0));
        nmPemeriksaan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nmPemeriksaan.setHighlighter(null);
        nmPemeriksaan.setName("nmPemeriksaan"); // NOI18N
        nmPemeriksaan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nmPemeriksaanKeyPressed(evt);
            }
        });
        FormInput.add(nmPemeriksaan);
        nmPemeriksaan.setBounds(130, 198, 630, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 130, 23);

        noRW.setEditable(false);
        noRW.setForeground(new java.awt.Color(0, 0, 0));
        noRW.setName("noRW"); // NOI18N
        FormInput.add(noRW);
        noRW.setBounds(130, 10, 130, 23);

        noRM.setEditable(false);
        noRM.setForeground(new java.awt.Color(0, 0, 0));
        noRM.setName("noRM"); // NOI18N
        FormInput.add(noRM);
        noRM.setBounds(263, 10, 70, 23);

        nmPasien.setEditable(false);
        nmPasien.setForeground(new java.awt.Color(0, 0, 0));
        nmPasien.setName("nmPasien"); // NOI18N
        FormInput.add(nmPasien);
        nmPasien.setBounds(337, 10, 420, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Jenis Kelamin : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(0, 38, 130, 23);

        jk.setEditable(false);
        jk.setForeground(new java.awt.Color(0, 0, 0));
        jk.setName("jk"); // NOI18N
        FormInput.add(jk);
        jk.setBounds(130, 38, 100, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Umur : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(230, 38, 40, 23);

        umur.setEditable(false);
        umur.setForeground(new java.awt.Color(0, 0, 0));
        umur.setName("umur"); // NOI18N
        FormInput.add(umur);
        umur.setBounds(272, 38, 60, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Diagnosa : ");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 94, 130, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Alamat : ");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 130, 23);

        unit.setForeground(new java.awt.Color(0, 0, 0));
        unit.setText("unit");
        unit.setName("unit"); // NOI18N
        FormInput.add(unit);
        unit.setBounds(330, 38, 130, 23);

        nmUnit.setEditable(false);
        nmUnit.setForeground(new java.awt.Color(0, 0, 0));
        nmUnit.setName("nmUnit"); // NOI18N
        FormInput.add(nmUnit);
        nmUnit.setBounds(467, 38, 290, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Nama Dokter Perujuk : ");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 170, 130, 23);

        drPerujuk.setEditable(false);
        drPerujuk.setForeground(new java.awt.Color(0, 0, 0));
        drPerujuk.setName("drPerujuk"); // NOI18N
        FormInput.add(drPerujuk);
        drPerujuk.setBounds(130, 170, 630, 23);

        TAlamat.setEditable(false);
        TAlamat.setForeground(new java.awt.Color(0, 0, 0));
        TAlamat.setName("TAlamat"); // NOI18N
        FormInput.add(TAlamat);
        TAlamat.setBounds(130, 66, 630, 23);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        diagnos.setEditable(false);
        diagnos.setColumns(20);
        diagnos.setRows(5);
        diagnos.setName("diagnos"); // NOI18N
        diagnos.setOpaque(true);
        diagnos.setPreferredSize(new java.awt.Dimension(170, 150));
        Scroll6.setViewportView(diagnos);

        FormInput.add(Scroll6);
        Scroll6.setBounds(130, 94, 630, 70);

        chkCito.setBackground(new java.awt.Color(242, 242, 242));
        chkCito.setBorder(null);
        chkCito.setForeground(new java.awt.Color(0, 0, 0));
        chkCito.setText("CITO");
        chkCito.setBorderPainted(true);
        chkCito.setBorderPaintedFlat(true);
        chkCito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkCito.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkCito.setName("chkCito"); // NOI18N
        chkCito.setOpaque(false);
        chkCito.setPreferredSize(new java.awt.Dimension(175, 23));
        FormInput.add(chkCito);
        chkCito.setBounds(767, 198, 60, 23);

        btnDokter.setForeground(new java.awt.Color(0, 0, 0));
        btnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDokter.setMnemonic('2');
        btnDokter.setToolTipText("Alt+2");
        btnDokter.setName("btnDokter"); // NOI18N
        btnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDokterActionPerformed(evt);
            }
        });
        FormInput.add(btnDokter);
        btnDokter.setBounds(762, 170, 28, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 102));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(250, 102));
        jPanel4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Daftar Permintaan Pemeriksaan Laboratorium ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(452, 150));

        tbNomor.setToolTipText("Silahkan klik untuk memilih data yang mau dilihat");
        tbNomor.setComponentPopupMenu(jPopupMenu1);
        tbNomor.setName("tbNomor"); // NOI18N
        tbNomor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNomorMouseClicked(evt);
            }
        });
        tbNomor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNomorKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbNomor);

        jPanel4.add(Scroll4, java.awt.BorderLayout.PAGE_START);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ".: Item Pemeriksaan Lab. yang diminta ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Status Permintaan : ");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(110, 23));
        panelisi6.add(jLabel9);

        cmbStatus.setForeground(new java.awt.Color(0, 0, 0));
        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BELUM", "Diterima", "Semua" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi6.add(cmbStatus);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('5');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+5");
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
        panelisi6.add(BtnCari1);

        jPanel4.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel4);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(237, 242, 232)), ".: Daftar Nama Pemeriksaan Lab. Sesuai PerBup ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
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

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnCari.setMnemonic('C');
        BtnCari.setText("Cek");
        BtnCari.setToolTipText("Alt+C");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCari);
        BtnCari.setBounds(390, 8, 70, 23);

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
            AutoNomerMinta();
            if (chkCito.isSelected() == true) {
                cito = "ya";
            } else {
                cito = "tidak";
            }
            
            if (Sequel.menyimpantf("permintaan_lab_raza", "?,?,?,?,?,?,?,?,?,?,?,?", "Permintaan Lab.", 12, new String[]{
                noRW.getText(), Sequel.cariIsi("select date(now())"), Sequel.cariIsi("select time(now())"), kddokterFIX,
                nmPemeriksaan.getText(), sttsRawat, noMinta.getText(), "BELUM", cito, nmUnit.getText(), "-", "Menunggu"
            }) == true) {
                
                tampilNomor();
                tampil("Menunggu");
                nmPemeriksaan.setText("");
                cekNOMINTA = "";
                TCari.setText("");
                tampilItemLab();
            }
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
        } else if (diperiksa.equals("Diterima")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat diganti..!!!");
            tbMintaPeriksa.requestFocus();
        } else {
            if (chkCito.isSelected() == true) {
                cito = "ya";
            } else {
                cito = "tidak";
            }
            
            Sequel.mengedit("permintaan_lab_raza", "no_rawat='" + cekNORW + "' and no_minta='" + cekNOMINTA + "'",
                    "dokter_perujuk='" + kddokterFIX + "',"
                    + "nm_pemeriksaan='" + nmPemeriksaan.getText() + "',"
                    + "status_rawat='" + sttsRawat + "',cito='" + cito + "'");

            tampilNomor();
            tampil(nokirim);
            nmPemeriksaan.setText("");
            cekNOMINTA = "";
            TCari.setText("");
            tampilItemLab();            
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
        tampilItemLab();
        tampilNomor();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampilItemLab();
        tbMasterItem.requestFocus();
    }//GEN-LAST:event_BtnCariActionPerformed

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

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (noRW.getText().trim().equals("") && noRM.getText().trim().equals("") && nmPasien.getText().trim().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (cekNOMINTA.equals("") && cekNORW.equals("")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. pada tabel belum dipilih..!!!");
            tbMintaPeriksa.requestFocus();
        } else if (diperiksa.equals("Diterima")) {
            JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. yg sdh diperiksa tidak dapat dihapus..!!!");
            tbMintaPeriksa.requestFocus();
        } else {
            Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + cekNORW + "' and no_minta='" + cekNOMINTA + "'");

            tampilNomor();
            tampil(nokirim);
            nmPemeriksaan.setText("");
            cekNOMINTA = "";
            TCari.setText("");
            tampilItemLab();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaruActionPerformed
        nmPemeriksaan.setText("");
        cekNORW = "";
        cekNOMINTA = "";
        TCari.setText("");
        chkCito.setSelected(false);
        cmbStatus.setSelectedIndex(0);
        Valid.tabelKosong(tabMode);
        tampilNomor();
    }//GEN-LAST:event_BtnBaruActionPerformed

    private void btnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDokterActionPerformed
        akses.setform("DlgPermintaanLabRAZA");
        dokter.isCek();
        dokter.setSize(1041, internalFrame1.getHeight() - 40);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setAlwaysOnTop(false);
        dokter.setVisible(true);
    }//GEN-LAST:event_btnDokterActionPerformed

    private void tbNomorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNomorMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataNomor();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbNomorMouseClicked

    private void tbNomorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNomorKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataNomor();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbNomorKeyPressed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilNomor();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCari1KeyPressed

    private void MnHapusNomorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusNomorActionPerformed
        if (tbNomor.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Belum ada permintaan pemeriksaan laboratorium yg. tersimpan...!!!!");
        } else if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik dulu salah satu datanya pada tabel daftar permintaan....!!!");
            tbNomor.requestFocus();
        } else {
            if (nokirim.equals("Menunggu")) {
                x = JOptionPane.showConfirmDialog(rootPane, "Semua permintaan pemeriksaan yg. belum terkirim akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' and no_kirim='Menunggu'");

                    tampilNomor();
                    Valid.tabelKosong(tabMode);
                    nmPemeriksaan.setText("");
                    cekNOMINTA = "";
                    TCari.setText("");
                    tampilItemLab();
                }
            } else {
                if (diperiksa.equals("Diterima")) {
                    JOptionPane.showMessageDialog(null, "Permintaan pemeriksaan sudah diterima & akan dilakukan pemeriksaan oleh petugas laboratorium...!!!!");
                } else {
                    x = JOptionPane.showConfirmDialog(rootPane, "Permintaan pemeriksaan sudah terkirim, apakah akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (x == JOptionPane.YES_OPTION) {
                        Sequel.queryu("delete from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' and "
                            + "no_kirim='" + nokirim + "' and status_periksa='" + diperiksa + "'");
                    }
                }

                tampilNomor();
                Valid.tabelKosong(tabMode);
                nmPemeriksaan.setText("");
                cekNOMINTA = "";
                TCari.setText("");
                tampilItemLab();
            }
        }
    }//GEN-LAST:event_MnHapusNomorActionPerformed

    private void MnCetakPermintaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnCetakPermintaanActionPerformed
        if (noRW.getText().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (nokirim.equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik dulu salah satu datanya pada tabel daftar permintaan....!!!");
            tbNomor.requestFocus();
        } else if (nokirim.equals("Menunggu")) {
            JOptionPane.showMessageDialog(rootPane, "Permintaan pemeriksaan laboratorium harus dikirim dulu setelah disimpan....!!!");
            BtnKirim.requestFocus();
        } else {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));            

            if (sttsRawat.equals("Ralan")) {
                cekDiterima = 0;
                cekDiterima = Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza WHERE no_rawat='" + noRW.getText() + "' and status_periksa='BELUM' and status_rawat='Ralan'");
                if (cekDiterima == 0) {
                    JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. sudah diperiksa semua..!!!");
                } else if (cekDiterima >= 1) {
                    param.put("kamar", "Poliklinik");
                    param.put("namakamar", Sequel.cariIsi("select p.nm_poli from reg_periksa r inner join poliklinik p on p.kd_poli=r.kd_poli where r.no_rawat='" + noRW.getText() + "'"));
                    param.put("diagnosa", Sequel.cariIsi("select ifnull(diagnosa,'-') diag from pemeriksaan_ralan where no_rawat='" + noRW.getText() + "'"));
                    param.put("tglsurat", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_permintaan from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' "
                            + "and no_kirim='" + nokirim + "' and status_periksa='BELUM' and status_rawat='Ralan' group by no_kirim, no_rawat limit 1")));
                    
                    Valid.MyReport("rptPermintaanLabRZ.jasper", "report", "::[ Lembar Permintaan Pemeriksaan Laboratorium ]::",
                            "SELECT x.no_rawat, concat(p.no_rkm_medis,' (No. Kirim : ',x.no_kirim,')') no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') usia, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, DATE_FORMAT(x.tgl_permintaan,'%d-%m-%Y') tglMinta, "
                            + "date_format(x.jam_permintaan,'%H:%i') jam_permintaan, d.nm_dokter, x.nm_pemeriksaan FROM permintaan_lab_raza x "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=x.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=x.dokter_perujuk WHERE "
                            + "x.no_rawat='" + noRW.getText() + "' and x.no_kirim='" + nokirim + "' "
                            + "and x.status_rawat='Ralan'", param);
                    emptTeks();
                    dispose();
                }
            } else {
                cekDiterima = 0;
                cekDiterima = Sequel.cariInteger("SELECT count(-1) FROM permintaan_lab_raza WHERE no_rawat='" + noRW.getText() + "' and status_periksa='BELUM' and status_rawat='Ranap'");
                if (cekDiterima == 0) {
                    JOptionPane.showMessageDialog(null, "Item permintaan pemeriksaan Lab. sudah diperiksa semua..!!!");
                } else if (cekDiterima >= 1) {
                    param.put("kamar", "Ruang Rawat");
                    param.put("namakamar", Sequel.cariIsi("SELECT b.nm_bangsal from kamar_inap ki INNER JOIN kamar k ON k.kd_kamar=ki.kd_kamar "
                            + "INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE ki.stts_pulang IN ('-','Pindah Kamar') and ki.no_rawat='" + noRW.getText() + "' "
                            + "ORDER BY ki.tgl_keluar DESC, ki.jam_keluar DESC LIMIT 1"));
                    param.put("diagnosa", Sequel.cariIsi("select ifnull(diagnosa_awal,'-') from kamar_inap where no_rawat='" + noRW.getText() + "'"));
                    param.put("tglsurat", Valid.SetTglINDONESIA(Sequel.cariIsi("select tgl_permintaan from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' "
                            + "and no_kirim='" + nokirim + "'and status_periksa='BELUM' and status_rawat='Ranap' group by no_kirim, no_rawat limit 1")));

                    Valid.MyReport("rptPermintaanLabRZ.jasper", "report", "::[ Lembar Permintaan Pemeriksaan Laboratorium ]::",
                            "SELECT x.no_rawat, concat(p.no_rkm_medis,' (No. Kirim : ',x.no_kirim,')') no_rkm_medis, p.nm_pasien, IF(p.jk='L','Laki-laki','Perempuan') jk, CONCAT(rp.umurdaftar,' ',rp.sttsumur,'.') usia, "
                            + "CONCAT(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, DATE_FORMAT(x.tgl_permintaan,'%d-%m-%Y') tglMinta, "
                            + "date_format(x.jam_permintaan,'%H:%i') jam_permintaan, d.nm_dokter, x.nm_pemeriksaan FROM permintaan_lab_raza x "
                            + "INNER JOIN reg_periksa rp on rp.no_rawat=x.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                            + "INNER JOIN kelurahan kl on kl.kd_kel=p.kd_kel INNER JOIN kecamatan kc on kc.kd_kec=p.kd_kec "
                            + "INNER JOIN kabupaten kb on kb.kd_kab=p.kd_kab INNER JOIN dokter d on d.kd_dokter=x.dokter_perujuk WHERE "
                            + "x.no_rawat='" + noRW.getText() + "' and x.no_kirim='" + nokirim + "' and x.status_rawat='Ranap'", param);
                    emptTeks();
                    dispose();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_MnCetakPermintaanActionPerformed

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        if (noRW.getText().equals("")) {
            Valid.textKosong(noRW, "Pasien");
        } else if (tbMintaPeriksa.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Silahkan simpan dulu permintaan pemeriksaan Lab. nya..!!!");
            nmPemeriksaan.requestFocus();
        } else if (Sequel.cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + noRW.getText() + "'") == 0) {
            JOptionPane.showMessageDialog(null, "Belum ada permintaan pemeriksaan lab. yg. tersimpan utk. dikirim..!!!");
        } else if (Sequel.cariInteger("select count(-1) from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' and no_kirim='Menunggu'") == 0) {
            JOptionPane.showMessageDialog(null, "Semua permintaan pemeriksaan lab. sudah dikirim..!!!");
        } else {
            TNoKirim.setText("");
            AutoNomerKirim();

            try {
                for (i = 0; i < tbMintaPeriksa.getRowCount(); i++) {
                    Sequel.queryu("update permintaan_lab_raza set no_kirim = '" + TNoKirim.getText() + "' where "
                        + "no_minta='" + tbMintaPeriksa.getValueAt(i, 7).toString() + "' and no_kirim='Menunggu'");
                }

                tampilNomor();
                tampil(TNoKirim.getText());
                nmPemeriksaan.setText("");
                cekNOMINTA = "";
                TCari.setText("");
                tampilItemLab();
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            }
        }
    }//GEN-LAST:event_BtnKirimActionPerformed

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
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnSimpan;
    private widget.PanelBiasa FormInput;
    private javax.swing.JMenuItem MnCetakPermintaan;
    private javax.swing.JMenuItem MnHapusNomor;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll33;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TAlamat;
    private widget.TextBox TCari;
    private widget.TextBox TNoKirim;
    private widget.Button btnDokter;
    public widget.CekBox chkCito;
    private widget.ComboBox cmbStatus;
    private widget.TextArea diagnos;
    private widget.TextBox drPerujuk;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.TextBox jk;
    private widget.TextBox nmPasien;
    public widget.TextBox nmPemeriksaan;
    private widget.TextBox nmUnit;
    private widget.TextBox noMinta;
    private widget.TextBox noRM;
    private widget.TextBox noRW;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi6;
    private widget.Table tbMasterItem;
    private widget.Table tbMintaPeriksa;
    private widget.Table tbNomor;
    private widget.TextBox umur;
    private widget.Label unit;
    // End of variables declaration//GEN-END:variables

    private void tampil(String nomorKrm) {     
        Valid.tabelKosong(tabMode);
        try {
            if (cmbStatus.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.dokter_perujuk, d.nm_dokter, p.no_minta, p.cito, p.no_kirim FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where "
                        + "p.no_rawat='" + noRW.getText() + "' and p.no_kirim like '%" + nomorKrm + "%' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            } else {
                ps = koneksi.prepareStatement("SELECT p.nm_pemeriksaan, date_format(p.tgl_permintaan,'%d-%m-%Y') tglminta, p.jam_permintaan, "
                        + "p.status_periksa, p.no_rawat, p.dokter_perujuk, d.nm_dokter, p.no_minta, p.cito, p.no_kirim FROM permintaan_lab_raza p "
                        + "inner join dokter d on d.kd_dokter=p.dokter_perujuk where "
                        + "p.no_rawat='" + noRW.getText() + "' and p.no_kirim like '%" + nomorKrm + "%' and p.status_periksa='" + cmbStatus.getSelectedItem() + "' "
                        + "order by p.status_periksa, p.tgl_permintaan desc, p.jam_permintaan desc");
            }
            try {                
                rs = ps.executeQuery();
                x = 1;
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        x + ".",
                        rs.getString("nm_pemeriksaan"),
                        rs.getString("tglminta"),
                        rs.getString("jam_permintaan"),
                        rs.getString("status_periksa"),                        
                        rs.getString("no_rawat"),
                        rs.getString("dokter_perujuk"),
                        rs.getString("no_minta"),
                        rs.getString("cito"),
                        rs.getString("no_kirim")
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
        AutoNomerMinta();
    }

    private void getData() {
        cekNORW = "";
        cekNOMINTA = "";
        diperiksa = "";
        cito = "";
        
        if (tbMintaPeriksa.getSelectedRow() != -1) {
            noRW.setText(tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 5).toString());
            diperiksa = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 4).toString();
            cekNORW = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 5).toString();
            cekNOMINTA = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 7).toString();            
            cito = tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 8).toString();
            isPasien(noRW.getText());
            nmPemeriksaan.setText(tbMintaPeriksa.getValueAt(tbMintaPeriksa.getSelectedRow(), 1).toString());
            nmPemeriksaan.requestFocus();
            
            if (cito.equals("ya")) {
                chkCito.setSelected(true);
            } else {
                chkCito.setSelected(false);
            }
        }
    }
    
    private void getDataLab() {
        if (tbMasterItem.getSelectedRow() != -1) {
            nmPemeriksaan.setText(tbMasterItem.getValueAt(tbMasterItem.getSelectedRow(), 0).toString());
            TCari.requestFocus();
        }
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
                    
                    if (sttsRawat.equals("ralan")) {
                        diagnos.setText(Sequel.cariIsi("select ifnull(diagnosa,'-') from pemeriksaan_ralan where no_rawat='" + norwt + "'"));
                    } else {
                        diagnos.setText(Sequel.cariIsi("select ifnull(diagnosa_awal,'-') from kamar_inap where no_rawat='" + norwt + "'"));
                    }
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
                + "tgl_permintaan='" + Sequel.cariIsi("select date(now())") + "' ", "MPL" + Sequel.cariIsi("select DATE_FORMAT(now(),'%Y%m%d')"), 5, noMinta);
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getpermintaan_lab());
        BtnGanti.setEnabled(akses.getpermintaan_lab());
        BtnHapus.setEnabled(akses.getpermintaan_lab());
    }
    
    private void tampilNomor() {
        Valid.tabelKosong(tabMode2);
        try {            
            ps1 = koneksi.prepareStatement("SELECT no_kirim, DATE_FORMAT(tgl_permintaan,'%d-%m-%Y') tglminta, "
                    + "time_format(jam_permintaan,'%H:%i') jam, tgl_permintaan, jam_permintaan, status_periksa, "
                    + "if(no_kirim='Menunggu','Masih Proses','Terkirim') stts_kirim, if(status_rawat='Ralan','R. Jalan','R. Inap') rawat "
                    + "from permintaan_lab_raza where no_rawat='" + noRW.getText() + "' group by no_kirim "
                    + "order by tgl_permintaan desc, jam_permintaan desc");
            try {
                rs1 = ps1.executeQuery();
                x = 1;
                while (rs1.next()) {
                    tabMode2.addRow(new Object[]{
                        x + ".",
                        rs1.getString("no_kirim"),
                        rs1.getString("tglminta"),
                        rs1.getString("jam"),
                        rs1.getString("tgl_permintaan"),
                        rs1.getString("jam_permintaan"),
                        rs1.getString("status_periksa"),
                        rs1.getString("stts_kirim"),
                        rs1.getString("rawat")
                    });
                    x++;
                }                
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgPermintaanLabRAZA.tampilNomor() : " + e);
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
    
    private void getDataNomor() {
        nokirim = "";
        tgl = "";
        jam = "";
        diperiksa = "";

        if (tbNomor.getSelectedRow() != -1) {            
            nokirim = tbNomor.getValueAt(tbNomor.getSelectedRow(), 1).toString();
            tgl = tbNomor.getValueAt(tbNomor.getSelectedRow(), 4).toString();
            jam = tbNomor.getValueAt(tbNomor.getSelectedRow(), 5).toString();
            diperiksa = tbNomor.getValueAt(tbNomor.getSelectedRow(), 6).toString();
            cmbStatus.setSelectedItem(diperiksa);
            tampil(nokirim);
        }
    }
    
    private void AutoNomerKirim() {
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(no_kirim,4),signed)),0) from permintaan_lab_raza where "
                + "tgl_permintaan='" + Sequel.cariIsi("select date(now())") + "' ", "KL" + Sequel.cariIsi("select DATE_FORMAT(now(),'%Y%m%d')"), 4, TNoKirim);
    }
}
