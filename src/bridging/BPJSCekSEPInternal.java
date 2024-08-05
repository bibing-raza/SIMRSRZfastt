/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgKamar.java
 *
 * Created on May 23, 2010, 12:07:21 AM
 */

package bridging;

import fungsi.WarnaTable;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dosen
 */
public final class BPJSCekSEPInternal extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1;
    private final Properties prop = new Properties();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();    
    private BPJSApi api = new BPJSApi();
    private String utc = "", URL = "", user = "", requestJson = "", nosurat = "", kode = "", pesan = "", jlhSEP = "",
            tglrujukinternal = "", kdpolitujuan = "", nosep = "";
    private PreparedStatement ps1;
    private ResultSet rs1;
    private int x = 0;
    public JsonNode responya;
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSCekSEPInternal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row = {"tujuanrujuk", "Poli Tujuan Rujukan", "Poli Asalnya", "Tgl. Rujuk Internal",
            "No. SEP", "Ref. No. SEP", "Kode PPK Pelyan.", "No. Kartu", "Tgl. SEP", "No. Surat", "Flag Internal",
            "Kode Poli Asalnya", "kdpolituj", "kdpenunjang", "Nama Penunjang", "Kode Diagnosa", "Kode Dokter",
            "Nama Dokter", "Flag Prosedur", "Opsi Konsul", "Flag SEP", "F User", "F Date", "Nama Diagnosa ICD-10"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbSEPinternal.setModel(tabMode);
        tbSEPinternal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSEPinternal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 24; i++) {
            TableColumn column = tbSEPinternal.getColumnModel().getColumn(i);
            if (i == 0) {
//                column.setPreferredWidth(170);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(200);
            } else if (i == 2) {
                column.setPreferredWidth(200);
            } else if (i == 3) {
                column.setPreferredWidth(110);
            } else if (i == 4) {
                column.setPreferredWidth(135);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } else if (i == 6) {
                column.setPreferredWidth(100);
            } else if (i == 7) {
                column.setPreferredWidth(140);
            } else if (i == 8) {
                column.setPreferredWidth(75);
            } else if (i == 9) {
                column.setPreferredWidth(160);
            } else if (i == 10) {
                column.setPreferredWidth(75);
            } else if (i == 11) {
                column.setPreferredWidth(150);
            } else if (i == 12) {
//                column.setPreferredWidth(470);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
//                column.setPreferredWidth(470);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setPreferredWidth(200);
            } else if (i == 15) {
                column.setPreferredWidth(80);
            } else if (i == 16) {
                column.setPreferredWidth(75);
            } else if (i == 17) {
                column.setPreferredWidth(250);
            } else if (i == 18) {
                column.setPreferredWidth(80);
            } else if (i == 19) {
                column.setPreferredWidth(75);
            } else if (i == 20) {
                column.setPreferredWidth(75);
            } else if (i == 21) {
                column.setPreferredWidth(200);
            } else if (i == 22) {
                column.setPreferredWidth(75);
            } else if (i == 23) {
                column.setPreferredWidth(350);
            }
        }        
        tbSEPinternal.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode1 = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "No. Kartu", "No. SEP", "Tgl. SEP", "No. RM", "Nama Pasien", "J.K.", "Poliklinik/Inst.", "tgl_lhr", "jknya"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbSEP.setModel(tabMode1);
        tbSEP.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSEP.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 10; i++) {
            TableColumn column = tbSEP.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(125);
            } else if (i == 3) {
                column.setPreferredWidth(75);
            } else if (i == 4) {
                column.setPreferredWidth(60);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(30);
            } else if (i == 7) {
                column.setPreferredWidth(180);
            } else if (i == 8) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
//                column.setPreferredWidth(75);
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbSEP.setDefaultRenderer(Object.class, new WarnaTable());

        NoSEP.setDocument(new batasInput((int) 20).getKata(NoSEP));
        angka.setText("0");
        
        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
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

        WindowSEPbpjs = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel15 = new widget.Label();
        tgl1 = new widget.Tanggal();
        jLabel18 = new widget.Label();
        tgl2 = new widget.Tanggal();
        jLabel10 = new widget.Label();
        LCount2 = new widget.Label();
        jLabel12 = new widget.Label();
        cmbLimit1 = new widget.ComboBox();
        panelGlass11 = new widget.panelisi();
        jLabel19 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        BtnAll2 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        Scroll1 = new widget.ScrollPane();
        tbSEP = new widget.Table();
        jPopupMenu = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSEPinternal = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        NoSEP = new widget.TextBox();
        btnSEP = new widget.Button();
        jLabel20 = new widget.Label();
        angka = new widget.Label();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnKeluar = new widget.Button();

        WindowSEPbpjs.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowSEPbpjs.setName("WindowSEPbpjs"); // NOI18N
        WindowSEPbpjs.setUndecorated(true);
        WindowSEPbpjs.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Daftar SEP BPJS Pasien Rawat Jalan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(240, 245, 235));
        internalFrame5.setLayout(new java.awt.BorderLayout());

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 95));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. SEP : ");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel15);

        tgl1.setEditable(false);
        tgl1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-11-2021" }));
        tgl1.setDisplayFormat("dd-MM-yyyy");
        tgl1.setName("tgl1"); // NOI18N
        tgl1.setOpaque(false);
        tgl1.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl1KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl1);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("s.d");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass8.add(jLabel18);

        tgl2.setEditable(false);
        tgl2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "09-11-2021" }));
        tgl2.setDisplayFormat("dd-MM-yyyy");
        tgl2.setName("tgl2"); // NOI18N
        tgl2.setOpaque(false);
        tgl2.setPreferredSize(new java.awt.Dimension(95, 23));
        tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tgl2KeyPressed(evt);
            }
        });
        panelGlass8.add(tgl2);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Record :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(55, 30));
        panelGlass8.add(jLabel10);

        LCount2.setForeground(new java.awt.Color(0, 0, 0));
        LCount2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount2.setText("0");
        LCount2.setName("LCount2"); // NOI18N
        LCount2.setPreferredSize(new java.awt.Dimension(75, 30));
        panelGlass8.add(LCount2);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Limit Data : ");
        jLabel12.setName("jLabel12"); // NOI18N
        jLabel12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(jLabel12);

        cmbLimit1.setForeground(new java.awt.Color(0, 0, 0));
        cmbLimit1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "50", "200", "1000", "Semuanya" }));
        cmbLimit1.setName("cmbLimit1"); // NOI18N
        cmbLimit1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbLimit1KeyPressed(evt);
            }
        });
        panelGlass8.add(cmbLimit1);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_START);

        panelGlass11.setName("panelGlass11"); // NOI18N
        panelGlass11.setPreferredSize(new java.awt.Dimension(44, 48));
        panelGlass11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Key Word : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass11.add(jLabel19);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        panelGlass11.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('6');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+6");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelGlass11.add(BtnCari2);

        BtnAll2.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll2.setMnemonic('M');
        BtnAll2.setText("Semua Data");
        BtnAll2.setToolTipText("Alt+M");
        BtnAll2.setName("BtnAll2"); // NOI18N
        BtnAll2.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll2ActionPerformed(evt);
            }
        });
        BtnAll2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll2KeyPressed(evt);
            }
        });
        panelGlass11.add(BtnAll2);

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
        panelGlass11.add(BtnKeluar2);

        jPanel3.add(panelGlass11, java.awt.BorderLayout.PAGE_END);

        internalFrame5.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        Scroll1.setToolTipText("Silahkan pilih salah satu data pasien ibu bersalin");
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbSEP.setToolTipText("");
        tbSEP.setName("tbSEP"); // NOI18N
        tbSEP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSEPMouseClicked(evt);
            }
        });
        tbSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSEPKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbSEP);

        internalFrame5.add(Scroll1, java.awt.BorderLayout.CENTER);

        WindowSEPbpjs.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setForeground(new java.awt.Color(0, 0, 0));
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapus.setText("Hapus SEP Internal");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapus);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian SEP Internal BPJS Berdasarkan Nomor SEP ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ScrollKeyPressed(evt);
            }
        });

        tbSEPinternal.setAutoCreateRowSorter(true);
        tbSEPinternal.setToolTipText("");
        tbSEPinternal.setComponentPopupMenu(jPopupMenu);
        tbSEPinternal.setName("tbSEPinternal"); // NOI18N
        tbSEPinternal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSEPinternalMouseClicked(evt);
            }
        });
        tbSEPinternal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSEPinternalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSEPinternal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 55));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("No. SEP :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(55, 23));
        panelGlass6.add(jLabel16);

        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setName("NoSEP"); // NOI18N
        NoSEP.setPreferredSize(new java.awt.Dimension(250, 23));
        NoSEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSEPKeyPressed(evt);
            }
        });
        panelGlass6.add(NoSEP);

        btnSEP.setForeground(new java.awt.Color(0, 0, 0));
        btnSEP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnSEP.setMnemonic('5');
        btnSEP.setToolTipText("Alt+5");
        btnSEP.setName("btnSEP"); // NOI18N
        btnSEP.setPreferredSize(new java.awt.Dimension(28, 23));
        btnSEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSEPActionPerformed(evt);
            }
        });
        panelGlass6.add(btnSEP);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Jumlah SEP ada : ");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass6.add(jLabel20);

        angka.setForeground(new java.awt.Color(0, 0, 0));
        angka.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        angka.setText("angka");
        angka.setName("angka"); // NOI18N
        angka.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(angka);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('6');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+6");
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
        panelGlass6.add(BtnCari);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass6.add(jLabel17);

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
        panelGlass6.add(BtnKeluar);

        PanelInput.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void NoSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSEPKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_NoSEPKeyPressed

    private void btnSEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSEPActionPerformed
        tgl1.setDate(new Date());
        tgl2.setDate(new Date());
        cmbLimit1.setSelectedIndex(0);
        TCari2.setText("");
        TCari2.requestFocus();
        tampilSEP();
        WindowSEPbpjs.setSize(870, 512);
        WindowSEPbpjs.setLocationRelativeTo(internalFrame1);
        WindowSEPbpjs.setVisible(true);
    }//GEN-LAST:event_btnSEPActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                 
        if (NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoSEP, "No. SEP Pasien BPJS");
        } else {
            tampil();
        }
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl1KeyPressed

    private void tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tgl2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tgl2KeyPressed

    private void cmbLimit1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbLimit1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbLimit1KeyPressed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilSEP();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnCari2);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll2ActionPerformed
        TCari2.setText("");
        tampilSEP();
    }//GEN-LAST:event_BtnAll2ActionPerformed

    private void BtnAll2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari2.setText("");
        } else {
            Valid.pindah(evt, TCari2, BtnKeluar2);
        }
    }//GEN-LAST:event_BtnAll2KeyPressed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        WindowSEPbpjs.dispose();
        btnSEP.requestFocus();
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            WindowSEPbpjs.dispose();
        }
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    private void tbSEPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSEPMouseClicked
        if (tabMode1.getRowCount() != 0) {
            if (evt.getClickCount() == 2) {
                try {
                    getDataSEP();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSEPMouseClicked

    private void tbSEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSEPKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getDataSEP();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSEPKeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbSEPinternal.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try {
                x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    bodyWithDeleteRequest();
                }
            } catch (Exception ex) {
                System.out.println("Notifikasi Bridging : " + ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik dulu salah satu datanya pada tabel..!!");
            tbSEPinternal.requestFocus();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void ScrollKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ScrollKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ScrollKeyPressed

    private void tbSEPinternalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSEPinternalKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getDataSEPinternal();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSEPinternalKeyPressed

    private void tbSEPinternalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSEPinternalMouseClicked
        if (tabMode.getRowCount() != 0) {
            try {
                getDataSEPinternal();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSEPinternalMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSCekSEPInternal dialog = new BPJSCekSEPInternal(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll2;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar2;
    private widget.Label LCount2;
    private javax.swing.JMenuItem MnHapus;
    public widget.TextBox NoSEP;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TCari2;
    private javax.swing.JDialog WindowSEPbpjs;
    private widget.Label angka;
    private widget.Button btnSEP;
    public widget.ComboBox cmbLimit1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel10;
    private widget.Label jLabel12;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu;
    private widget.panelisi panelGlass11;
    private widget.panelisi panelGlass6;
    private widget.panelisi panelGlass8;
    private widget.Table tbSEP;
    private widget.Table tbSEPinternal;
    private widget.Tanggal tgl1;
    private widget.Tanggal tgl2;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            cekData(NoSEP.getText());
            if (kode.equals("200")) {
                Valid.tabelKosong(tabMode);
                if (responya.path("list").isArray()) {
                    for (JsonNode list : responya.path("list")) {
                        tabMode.addRow(new Object[]{
                            list.path("tujuanrujuk").asText(),
                            list.path("nmtujuanrujuk").asText(),
                            list.path("nmpoliasal").asText(),
                            list.path("tglrujukinternal").asText(),
                            list.path("nosep").asText(),
                            list.path("nosepref").asText().replaceAll("null", ""),
                            list.path("ppkpelsep").asText(),
                            list.path("nokapst").asText(),
                            list.path("tglsep").asText(),
                            list.path("nosurat").asText(),
                            list.path("flaginternal").asText(),
                            list.path("kdpoliasal").asText(),
                            list.path("kdpolituj").asText(),
                            list.path("kdpenunjang").asText(),
                            list.path("nmpenunjang").asText(),
                            list.path("diagppk").asText(),
                            list.path("kddokter").asText(),
                            list.path("nmdokter").asText(),
                            list.path("flagprosedur").asText(),
                            list.path("opsikonsul").asText().replaceAll("null", ""),
                            list.path("flagsep").asText(),
                            list.path("fuser").asText(),
                            list.path("fdate").asText(),
                            list.path("nmdiag").asText()
                        });
                    }
                }
                angka.setText(jlhSEP);
            } else {
                JOptionPane.showMessageDialog(null, "Notifikasi WS VClaim 2.0 : Kode " + kode + ", Isi Pesan : " + pesan);
                Valid.tabelKosong(tabMode);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
        }
    }  
    
    public void SetNoKartu(String sepnya){
        NoSEP.setText(sepnya);
        tampil();
    }
    
    private void tampilSEP() {
        Valid.tabelKosong(tabMode1);
        try {
            if (cmbLimit1.getSelectedItem().equals("Semuanya")) {
                ps1 = koneksi.prepareStatement("SELECT no_rawat, no_kartu, no_sep, DATE_FORMAT(tglsep,'%d-%m-%Y') tglsep, nomr, nama_pasien, jkel, "
                        + "nmpolitujuan, concat(tanggal_lahir,' ','00:00:00') tgl_lhr, IF(jkel='L','1','2') jknya FROM bridging_sep WHERE "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_rawat like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_kartu like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_sep like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and nomr like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and nama_pasien like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and jkel like ? ORDER BY no_sep DESC");
            } else {
                ps1 = koneksi.prepareStatement("SELECT no_rawat, no_kartu, no_sep, DATE_FORMAT(tglsep,'%d-%m-%Y') tglsep, nomr, nama_pasien, jkel, "
                        + "nmpolitujuan, concat(tanggal_lahir,' ','00:00:00') tgl_lhr, IF(jkel='L','1','2') jknya FROM bridging_sep WHERE "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_rawat like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_kartu like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and no_sep like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and nomr like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and nama_pasien like ? or "
                        + "jnspelayanan='2' and tglsep BETWEEN ? AND ? and jkel like ? "
                        + "ORDER BY no_sep DESC limit " + cmbLimit1.getSelectedItem().toString() + "");
            }            
            try {
                ps1.setString(1, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(2, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(3, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(4, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(5, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(6, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(7, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(8, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(9, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(10, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(11, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(12, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(13, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(14, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(15, "%" + TCari2.getText().trim() + "%");                
                ps1.setString(16, Valid.SetTgl(tgl1.getSelectedItem() + ""));
                ps1.setString(17, Valid.SetTgl(tgl2.getSelectedItem() + ""));
                ps1.setString(18, "%" + TCari2.getText().trim() + "%");                             
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        rs1.getString("no_rawat"),
                        rs1.getString("no_kartu"),
                        rs1.getString("no_sep"),
                        rs1.getString("tglsep"),
                        rs1.getString("nomr"),
                        rs1.getString("nama_pasien"),                        
                        rs1.getString("jkel"),
                        rs1.getString("nmpolitujuan"),
                        rs1.getString("tgl_lhr"),
                        rs1.getString("jknya")
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
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount2.setText("" + tabMode1.getRowCount());
    }
    
    private void getDataSEP() {
        if (tbSEP.getSelectedRow() != -1) {
            NoSEP.setText(tbSEP.getValueAt(tbSEP.getSelectedRow(), 2).toString());
            WindowSEPbpjs.dispose();
            btnSEP.requestFocus();
        }
    }
    
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new BPJSDataSEP.HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/Internal/delete";

            requestJson = "{\"request\": {"
                    + "\"t_sep\": {"
                    + "\"noSep\":\"" + nosep + "\","
                    + "\"noSurat\":\"" + nosurat + "\","
                    + "\"tglRujukanInternal\":\"" + tglrujukinternal + "\","
                    + "\"kdPoliTuj\":\"" + kdpolitujuan + "\","
                    + "\"user\":\"" + user + "\""
                    + "}"
                    + "}"
                    + "}";

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, new HttpEntity<String>(requestJson, headers), String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());

            if (nameNode.path("code").asText().equals("200")) {
//                Sequel.meghapus("bridging_sep", "no_sep", tbSEP.getValueAt(tbSEP.getSelectedRow(), 0).toString());
//                tampil(nosep);
                JOptionPane.showMessageDialog(null, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi Pesan : " + nameNode.path("message").asText());
                Valid.tabelKosong(tabMode); 
            } else {
                JOptionPane.showMessageDialog(null, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Isi Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception e) {   
            System.out.println("Notif : "+e);
        }
    }
    
    private void getDataSEPinternal() {
        nosep = "";
        nosurat = "";
        tglrujukinternal = "";
        kdpolitujuan = "";
        
        if (tbSEPinternal.getSelectedRow() != -1) {
            nosep = tbSEPinternal.getValueAt(tbSEPinternal.getSelectedRow(), 4).toString();
            nosurat = tbSEPinternal.getValueAt(tbSEPinternal.getSelectedRow(), 9).toString();
            tglrujukinternal = tbSEPinternal.getValueAt(tbSEPinternal.getSelectedRow(), 3).toString();
            kdpolitujuan = tbSEPinternal.getValueAt(tbSEPinternal.getSelectedRow(), 12).toString();           
        }
    }
    
    private void cekData(String nomorSEP) {
        kode = "";
        pesan = "";
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPIBPJS") + "/SEP/Internal/" + nomorSEP;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("X-Cons-ID", Sequel.decXML2(prop.getProperty("CONSIDAPIBPJS"), prop.getProperty("KEY")));
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIBPJS());

            HttpEntity requestEntity = new HttpEntity(headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.GET, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");            
            kode = nameNode.path("code").asText();
            pesan = nameNode.path("message").asText();

            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------                
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                responya = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------                
                jlhSEP = response.path("count").asText();                
            } else {
                System.out.println("Notifikasi WS VClaim 2.0 : Kode " + kode + ", isi pesan : " + pesan);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
}
