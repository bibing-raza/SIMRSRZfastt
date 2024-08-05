package bridging;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class CoronaDiagnosa extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int jml=0,i=0,row=0,index=0;
    private boolean [] pilihan;
    private String[] kodepenyakit,namapenyakit;
    private CoronaPasien pasien=new CoronaPasien(null,false);
    private String link="",idrs="",body="";
    private HttpHeaders headers ;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private ApiKemenkesCorona api=new ApiKemenkesCorona();

    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public CoronaDiagnosa(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] judul={"P","Kode ICD-10","Nama Penyakit"};
        tabMode=new DefaultTableModel(null,judul){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
            }
              
            Class[] types = new Class[]{
                java.lang.Boolean.class,java.lang.String.class,java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbInputDiagnosa.setModel(tabMode);

        tbInputDiagnosa.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbInputDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 3; i++) {
            TableColumn column = tbInputDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(90);
            } else if (i == 2) {
                column.setPreferredWidth(700);
            }
        }
        tbInputDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());

        norm.setDocument(new batasInput((byte)10).getKata(norm));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        tabMode2=new DefaultTableModel(null,new Object[]{
                "No.KTP/Paspor","No.RM","Nama Pasien","Tgl.Masuk","Kode ICD-10","Nama Penyakit","Status"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDataDiagnosa.setModel(tabMode2);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbDataDiagnosa.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDataDiagnosa.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 7; i++) {
            TableColumn column = tbDataDiagnosa.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(125);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(170);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(65);
            } else if (i == 5) {
                column.setPreferredWidth(300);
            } else if (i == 6) {
                column.setPreferredWidth(60);
            }
        }
        tbDataDiagnosa.setDefaultRenderer(Object.class, new WarnaTable());
        TCari2.setDocument(new batasInput((byte)100).getKata(TCari2));    
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
            TCari2.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari2.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari2.getText().length()>2){
                        tampil2();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari2.getText().length()>2){
                        tampil2();
                    }
                }
            });
        }
        
        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(pasien.getTable().getSelectedRow()!= -1){   
                    norm.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),1).toString());
                    nmpasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(),2).toString());
                    TCari.requestFocus();
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
        
        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    pasien.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
        try {
            link=koneksiDB.URLAPICORONA();
            idrs=koneksiDB.IDCORONA();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Kd2 = new widget.TextBox();
        Popup = new javax.swing.JPopupMenu();
        ppBersihkan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        TabSetting = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbInputDiagnosa = new widget.Table();
        panelisi3 = new widget.panelisi();
        label17 = new widget.Label();
        norm = new widget.TextBox();
        nmpasien = new widget.TextBox();
        btnPetugas = new widget.Button();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        label10 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari1 = new widget.Button();
        label11 = new widget.Label();
        BtnKeluar = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDataDiagnosa = new widget.Table();
        jPanel1 = new javax.swing.JPanel();
        panelisi4 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        label9 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll = new widget.Button();
        panelisi2 = new widget.panelisi();
        BtnHapus = new widget.Button();
        jLabel10 = new widget.Label();
        LCount = new widget.Label();
        BtnCetak = new widget.Button();
        BtnKeluar2 = new widget.Button();

        Kd2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setPreferredSize(new java.awt.Dimension(207, 23));

        Popup.setName("Popup"); // NOI18N

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(0, 0, 0));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Bersihkan Jumlah");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(200, 25));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        Popup.add(ppBersihkan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Bridging Kemenkes Diagnosa Pasien Teridentifikasi Corona ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setForeground(new java.awt.Color(0, 0, 0));
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        TabSetting.setBackground(new java.awt.Color(255, 255, 253));
        TabSetting.setForeground(new java.awt.Color(0, 0, 0));
        TabSetting.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TabSetting.setName("TabSetting"); // NOI18N
        TabSetting.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabSettingMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), " Diagnosa ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        scrollPane1.setForeground(new java.awt.Color(0, 0, 0));
        scrollPane1.setComponentPopupMenu(Popup);
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbInputDiagnosa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbInputDiagnosa.setComponentPopupMenu(Popup);
        tbInputDiagnosa.setName("tbInputDiagnosa"); // NOI18N
        tbInputDiagnosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbInputDiagnosaKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(tbInputDiagnosa);

        internalFrame2.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi3.setLayout(null);

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Pasien Teridentifikasi Corona :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label17);
        label17.setBounds(0, 10, 165, 23);

        norm.setEditable(false);
        norm.setForeground(new java.awt.Color(0, 0, 0));
        norm.setName("norm"); // NOI18N
        norm.setPreferredSize(new java.awt.Dimension(80, 23));
        norm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                normKeyPressed(evt);
            }
        });
        panelisi3.add(norm);
        norm.setBounds(169, 10, 90, 23);

        nmpasien.setEditable(false);
        nmpasien.setForeground(new java.awt.Color(0, 0, 0));
        nmpasien.setName("nmpasien"); // NOI18N
        nmpasien.setPreferredSize(new java.awt.Dimension(207, 23));
        panelisi3.add(nmpasien);
        nmpasien.setBounds(261, 10, 341, 23);

        btnPetugas.setForeground(new java.awt.Color(0, 0, 0));
        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('1');
        btnPetugas.setToolTipText("Alt+1");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        panelisi3.add(btnPetugas);
        btnPetugas.setBounds(605, 10, 28, 23);

        internalFrame2.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(734, 55));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi1.add(BtnSimpan);

        label10.setForeground(new java.awt.Color(0, 0, 0));
        label10.setText("Kode ICD-10 :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(98, 23));
        panelisi1.add(label10);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(215, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

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
        panelisi1.add(BtnCari1);

        label11.setForeground(new java.awt.Color(0, 0, 0));
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(74, 23));
        panelisi1.add(label11);

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
        panelisi1.add(BtnKeluar);

        internalFrame2.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        TabSetting.addTab("Input Diagnosa", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDataDiagnosa.setName("tbDataDiagnosa"); // NOI18N
        Scroll.setViewportView(tbDataDiagnosa);

        internalFrame3.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl.Masuk :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(65, 23));
        panelisi4.add(jLabel19);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-04-2020" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelisi4.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "24-04-2020" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelisi4.add(DTPCari2);

        label9.setForeground(new java.awt.Color(0, 0, 0));
        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi4.add(label9);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelisi4.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('2');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+2");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        panelisi4.add(BtnCari2);

        BtnAll.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua Data");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(120, 23));
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
        panelisi4.add(BtnAll);

        jPanel1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

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
        panelisi2.add(BtnHapus);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(140, 30));
        panelisi2.add(jLabel10);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(180, 30));
        panelisi2.add(LCount);

        BtnCetak.setForeground(new java.awt.Color(0, 0, 0));
        BtnCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnCetak.setMnemonic('C');
        BtnCetak.setText("Cetak");
        BtnCetak.setToolTipText("Alt+C");
        BtnCetak.setName("BtnCetak"); // NOI18N
        BtnCetak.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCetakActionPerformed(evt);
            }
        });
        BtnCetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCetakKeyPressed(evt);
            }
        });
        panelisi2.add(BtnCetak);

        BtnKeluar2.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Keluar");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        panelisi2.add(BtnKeluar2);

        jPanel1.add(panelisi2, java.awt.BorderLayout.CENTER);

        internalFrame3.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        TabSetting.addTab("Data Diagnosa", internalFrame3);

        internalFrame1.add(TabSetting, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){            
            dispose();              
        }else{Valid.pindah(evt,BtnSimpan,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (norm.getText().trim().equals("") || nmpasien.getText().trim().equals("")) {
            Valid.textKosong(norm, "Pasien");
        } else {
            index = 0;
            for (i = 0; i < tbInputDiagnosa.getRowCount(); i++) {
                if (tbInputDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                    index++;
                    try {
                        headers = new HttpHeaders();
                        headers.add("X-rs-id", idrs);
                        headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                        headers.add("X-pass", api.getHmac());
                        body = "{"
                                + "\"nomr\" : \"" + norm.getText() + "\","
                                + "\"icd\"  : \"" + tbInputDiagnosa.getValueAt(i, 1).toString() + "\","
                                + "\"level\": \"" + (index == 1 ? "1" : "2") + "\""
                                + "}";
                        requestEntity = new HttpEntity(body, headers);
                        root = mapper.readTree(api.getRest().exchange(link + "/Pasien/diagnosis", HttpMethod.POST, requestEntity, String.class).getBody());
                        response = root.path("diagnosis");
                        if (response.isArray()) {
                            for (JsonNode list : response) {
                                if (list.path("status").asText().equals("200")) {
                                    if (Sequel.menyimpantf("diagnosa_corona", "?,?,?,?", "Diagnosa Pasien", 4, new String[]{
                                        norm.getText(), tbInputDiagnosa.getValueAt(i, 1).toString(), tbInputDiagnosa.getValueAt(i, 2).toString(), (index == 1 ? "Primer" : "Sekunder")
                                    }) == true) {
                                        tbInputDiagnosa.setValueAt(false, i, 0);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, list.path("message").asText());
                                }
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println("Notifikasi : " + ex);
                        if (ex.toString().contains("UnknownHostException")) {
                            JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
                        } else if (ex.toString().contains("404")) {
                            JOptionPane.showMessageDialog(rootPane, "Tidak ditemukan....!");
                        } else if (ex.toString().contains("500")) {
                            JOptionPane.showMessageDialog(rootPane, "Server internal error....!");
                        } else if (ex.toString().contains("502")) {
                            JOptionPane.showMessageDialog(rootPane, "Server kemenkes lelah broo....!");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnSimpanActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, TCari);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        tampil();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        BtnCari1.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        norm.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        tbInputDiagnosa.requestFocus();
    }
}//GEN-LAST:event_TCariKeyPressed

private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
    if (TCari.getText().trim().equals("")) {
        JOptionPane.showMessageDialog(rootPane, "Silahkan masukkan kode ICD-10");
    } else {
        tampil();
    }
}//GEN-LAST:event_BtnCari1ActionPerformed

private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCari1ActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnSimpan, BtnKeluar);
        }
}//GEN-LAST:event_BtnCari1KeyPressed

private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
    for (i = 0; i < tbInputDiagnosa.getRowCount(); i++) {
        tbInputDiagnosa.setValueAt("", i, 0);
        tbInputDiagnosa.setValueAt("0", i, 4);
    }
}//GEN-LAST:event_ppBersihkanActionPerformed

    private void normKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_normKeyPressed
        
    }//GEN-LAST:event_normKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        akses.setform("CoronaDiagnosa");
        pasien.emptTeks();
        pasien.isCek();
        pasien.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        pasien.setLocationRelativeTo(internalFrame1);
        pasien.setAlwaysOnTop(false);
        pasien.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void tbInputDiagnosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbInputDiagnosaKeyPressed
        
    }//GEN-LAST:event_tbInputDiagnosaKeyPressed

    private void TabSettingMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabSettingMouseClicked
        if(TabSetting.getSelectedIndex()==1){
            tampil2();
        }
    }//GEN-LAST:event_TabSettingMouseClicked

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCari2ActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar2.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDataDiagnosa.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampil2();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCari2ActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari2.setText("");
        tampil2();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari2, TCari);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            TCari2.requestFocus();
        } else if (tbDataDiagnosa.getSelectedRow() <= -1) {
            JOptionPane.showMessageDialog(null, "Maaf, Silahkan pilih data yang mau dihapus..!!");
        } else {
            try {
                headers = new HttpHeaders();
                headers.add("X-rs-id", idrs);
                headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
                headers.add("X-pass", api.getHmac());
                headers.add("nomr", tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 1).toString());
                headers.add("icd", tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 4).toString());
                headers.add("level", tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 6).toString().replaceAll("Primer", "1").replaceAll("Sekunder", "2"));
                requestEntity = new HttpEntity(headers);
                root = mapper.readTree(api.getRest().exchange(link + "/Pasien/diagnosis", HttpMethod.DELETE, requestEntity, String.class).getBody());
                response = root.path("diagnosis");
                if (response.isArray()) {
                    for (JsonNode list : response) {
                        if (list.path("status").asText().equals("200")) {
                            if (Sequel.queryu2tf("delete from diagnosa_corona where no_rkm_medis=? and kode_icd=? and status=?", 3, new String[]{
                                tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 1).toString(), tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 4).toString(), tbDataDiagnosa.getValueAt(tbDataDiagnosa.getSelectedRow(), 6).toString()
                            }) == true) {
                                tampil2();
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, list.path("message").asText());
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
                } else if (ex.toString().contains("404")) {
                    JOptionPane.showMessageDialog(rootPane, "Tidak ditemukan....!");
                } else if (ex.toString().contains("500")) {
                    JOptionPane.showMessageDialog(rootPane, "Server internal error....!");
                } else if (ex.toString().contains("502")) {
                    JOptionPane.showMessageDialog(rootPane, "Server kemenkes lelah broo....!");
                }
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnKeluar2, BtnAll);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCetakActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if (tabMode2.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            BtnKeluar.requestFocus();
        } else {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            Valid.MyReport("rptDiagnosaCorona.jasper", "report", "::[ Data Bridging Kemenkes RI Diagnosa Pasien Teridentifikasi Corona ]::",
                    "select pasien_corona.no_pengenal,pasien_corona.no_rkm_medis,pasien_corona.nama_lengkap,"
                    + "pasien_corona.tgl_masuk,diagnosa_corona.kode_icd,diagnosa_corona.nama_penyakit,diagnosa_corona.status "
                    + "from pasien_corona inner join diagnosa_corona on pasien_corona.no_rkm_medis=diagnosa_corona.no_rkm_medis "
                    + "where pasien_corona.tgl_masuk between '" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "' "
                    + (TCari2.getText().trim().equals("") ? "" : "and (pasien_corona.no_pengenal like '%" + TCari2.getText().trim() + "%' or pasien_corona.no_rkm_medis like '%" + TCari2.getText().trim() + "%' or "
                    + "pasien_corona.nama_lengkap like '%" + TCari2.getText().trim() + "%' or diagnosa_corona.kode_icd like '%" + TCari2.getText().trim() + "%' or diagnosa_corona.nama_penyakit like '%" + TCari2.getText().trim() + "%' or "
                    + "diagnosa_corona.status like '%" + TCari2.getText().trim() + "%') ")
                    + "order by tgl_masuk", param);

        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCetakActionPerformed

    private void BtnCetakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCetakKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCetakKeyPressed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnAll,TCari2);}
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            CoronaDiagnosa dialog = new CoronaDiagnosa(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnCari2;
    private widget.Button BtnCetak;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Button BtnSimpan;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private javax.swing.JPopupMenu Popup;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TCari2;
    private javax.swing.JTabbedPane TabSetting;
    private widget.Button btnPetugas;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private javax.swing.JPanel jPanel1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label9;
    private widget.TextBox nmpasien;
    private widget.TextBox norm;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppBersihkan;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDataDiagnosa;
    private widget.Table tbInputDiagnosa;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        row = tbInputDiagnosa.getRowCount();
        jml = 0;
        for (i = 0; i < row; i++) {
            if (tbInputDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                jml++;
            }
        }

        kodepenyakit = new String[jml];
        namapenyakit = new String[jml];
        pilihan = new boolean[jml];
        index = 0;
        for (i = 0; i < tbInputDiagnosa.getRowCount(); i++) {
            if (tbInputDiagnosa.getValueAt(i, 0).toString().equals("true")) {
                pilihan[index] = true;
                kodepenyakit[index] = tbInputDiagnosa.getValueAt(i, 1).toString();
                namapenyakit[index] = tbInputDiagnosa.getValueAt(i, 2).toString();
                index++;
            }
        }

        Valid.tabelKosong(tabMode);

        for (i = 0; i < jml; i++) {
            tabMode.addRow(new Object[]{pilihan[i], kodepenyakit[i], namapenyakit[i]});
        }

        try {
            headers = new HttpHeaders();
            headers.add("X-rs-id", idrs);
            headers.add("X-Timestamp", String.valueOf(api.GetUTCdatetimeAsString()));
            headers.add("X-pass", api.getHmac());
            headers.add("icd", TCari.getText());
            requestEntity = new HttpEntity(headers);
            root = mapper.readTree(api.getRest().exchange(link + "/Referensi/icd", HttpMethod.POST, requestEntity, String.class).getBody());
            response = root.path("icd - " + TCari.getText());
            if (response.isArray()) {
                for (JsonNode list : response) {
                    tabMode.addRow(new Object[]{
                        false, list.path("icd").asText(), list.path("description").asText()
                    });
                }
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(rootPane, "Koneksi ke server Kemenkes terputus....!");
            } else if (ex.toString().contains("404")) {
                JOptionPane.showMessageDialog(rootPane, "Tidak ditemukan....!");
            } else if (ex.toString().contains("500")) {
                JOptionPane.showMessageDialog(rootPane, "Server internal error....!");
            } else if (ex.toString().contains("502")) {
                JOptionPane.showMessageDialog(rootPane, "Server kemenkes lelah broo....!");
            }
        }        
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdiagnosa_pasien_corona());
        BtnHapus.setEnabled(akses.getdiagnosa_pasien_corona());
    }
    
    public void tampil2() {
        Valid.tabelKosong(tabMode2);
        try {
            ps = koneksi.prepareStatement(
                    "select pasien_corona.no_pengenal,pasien_corona.no_rkm_medis,pasien_corona.nama_lengkap,"
                    + "pasien_corona.tgl_masuk,diagnosa_corona.kode_icd,diagnosa_corona.nama_penyakit,diagnosa_corona.status "
                    + "from pasien_corona inner join diagnosa_corona on pasien_corona.no_rkm_medis=diagnosa_corona.no_rkm_medis "
                    + "where pasien_corona.tgl_masuk between ? and ? "
                    + (TCari.getText().trim().equals("") ? "" : "and (pasien_corona.no_pengenal like ? or pasien_corona.no_rkm_medis like ? or "
                    + "pasien_corona.nama_lengkap like ? or diagnosa_corona.kode_icd like ? or diagnosa_corona.nama_penyakit like ? or "
                    + "diagnosa_corona.status like ?) ")
                    + "order by tgl_masuk");
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().trim().equals("")) {
                    ps.setString(3, "%" + TCari2.getText().trim() + "%");
                    ps.setString(4, "%" + TCari2.getText().trim() + "%");
                    ps.setString(5, "%" + TCari2.getText().trim() + "%");
                    ps.setString(6, "%" + TCari2.getText().trim() + "%");
                    ps.setString(7, "%" + TCari2.getText().trim() + "%");
                    ps.setString(8, "%" + TCari2.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode2.addRow(new Object[]{
                        rs.getString("no_pengenal"), rs.getString("no_rkm_medis"), rs.getString("nama_lengkap"), rs.getString("tgl_masuk"),
                        rs.getString("kode_icd"), rs.getString("nama_penyakit"), rs.getString("status")
                    });
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
        LCount.setText("" + tabMode2.getRowCount());
    }

    public void SetPasien(String norm,String nmpasien,Date tglmasuk,Date tglkeluar){
        this.norm.setText(norm);
        this.nmpasien.setText(nmpasien);
        TCari2.setText(norm);
        DTPCari1.setDate(tglmasuk);
        DTPCari2.setDate(tglkeluar);
    }
 
}
