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
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
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
public final class BPJSDataNomorSuratKontrol extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private final Properties prop = new Properties();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private BPJSApi api = new BPJSApi();    
    private BPJSCekReferensiPenyakit penyakit = new BPJSCekReferensiPenyakit(null, false);
    private String utc = "", URL = "", requestJson = "", kode = "", pesan = "", filterDong = "", nosurat = "";
    private PreparedStatement ps;
    private ResultSet rs;
    private int x = 0;
    public JsonNode respon_lagi;
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSDataNomorSuratKontrol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row = {"No.","No. Surat","Jns. Pelayanan","Kode Kontrol","Jenis Kontrol","Tgl. Rencana Kontrol","Tgl. Entri","No. SEP Asal Kontrol",
            "Kode Poli Asalnya","Nama Poliklinik Asalnya","Kode Poli Tujuan","Nama Poliklinik Tujuan","Tgl. SEP","Kode Dokter","Nama Dokter","No. Kartu","Nama Pasien"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 17; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(40);
            } else if (i == 1) {
                column.setPreferredWidth(145);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(85);
            } else if (i == 4) {
                column.setPreferredWidth(100);
            } else if (i == 5) {
                column.setPreferredWidth(130);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(145);
            } else if (i == 8) {
                column.setPreferredWidth(105);
            } else if (i == 9) {
                column.setPreferredWidth(210);
            } else if (i == 10) {
                column.setPreferredWidth(105);
            } else if (i == 11) {
                column.setPreferredWidth(210);
            } else if (i == 12) {
                column.setPreferredWidth(75);
            } else if (i == 13) {
                column.setPreferredWidth(75);
            } else if (i == 14) {
                column.setPreferredWidth(350);
            } else if (i == 15) {
                column.setPreferredWidth(100);
            } else if (i == 16) {
                column.setPreferredWidth(350);
            }
        }        
        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        
        penyakit.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penyakit.getTable().getSelectedRow()!= -1){
                    kdICD.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),1).toString());
                    nmDiagnosa.setText(penyakit.getTable().getValueAt(penyakit.getTable().getSelectedRow(),2).toString());                    
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
        
        penyakit.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penyakit.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
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

        jPopupMenu = new javax.swing.JPopupMenu();
        MnHapus = new javax.swing.JMenuItem();
        MnSimpanSPRI = new javax.swing.JMenuItem();
        MnSimpanSURKON = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        WindowDiagnosa = new javax.swing.JDialog();
        internalFrame5 = new widget.InternalFrame();
        BtnCloseIn4 = new widget.Button();
        BtnSimpan4 = new widget.Button();
        jLabel22 = new widget.Label();
        kdICD = new widget.TextBox();
        nmDiagnosa = new widget.TextBox();
        btnDiagnosa = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSurat = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel19 = new widget.Label();
        tglRencana = new widget.RadioButton();
        tglEntri = new widget.RadioButton();
        jLabel20 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel18 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnKeluar = new widget.Button();

        jPopupMenu.setName("jPopupMenu"); // NOI18N

        MnHapus.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapus.setForeground(new java.awt.Color(0, 0, 0));
        MnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnHapus.setText("Hapus Surat Kontrol/SPRI");
        MnHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapus.setIconTextGap(5);
        MnHapus.setName("MnHapus"); // NOI18N
        MnHapus.setPreferredSize(new java.awt.Dimension(210, 26));
        MnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnHapus);

        MnSimpanSPRI.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanSPRI.setForeground(new java.awt.Color(0, 0, 0));
        MnSimpanSPRI.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanSPRI.setText("Simpan SPRI ke Database");
        MnSimpanSPRI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSimpanSPRI.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSimpanSPRI.setIconTextGap(5);
        MnSimpanSPRI.setName("MnSimpanSPRI"); // NOI18N
        MnSimpanSPRI.setPreferredSize(new java.awt.Dimension(210, 26));
        MnSimpanSPRI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanSPRIActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnSimpanSPRI);

        MnSimpanSURKON.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSimpanSURKON.setForeground(new java.awt.Color(0, 0, 0));
        MnSimpanSURKON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSimpanSURKON.setText("Simpan Surat Kontrol ke Database");
        MnSimpanSURKON.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSimpanSURKON.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSimpanSURKON.setIconTextGap(5);
        MnSimpanSURKON.setName("MnSimpanSURKON"); // NOI18N
        MnSimpanSURKON.setPreferredSize(new java.awt.Dimension(210, 26));
        MnSimpanSURKON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSimpanSURKONActionPerformed(evt);
            }
        });
        jPopupMenu.add(MnSimpanSURKON);

        WindowDiagnosa.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowDiagnosa.setName("WindowDiagnosa"); // NOI18N
        WindowDiagnosa.setUndecorated(true);
        WindowDiagnosa.setResizable(false);

        internalFrame5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Diagnosa ICD-10 Data SPRI ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame5.setLayout(null);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnCloseIn4);
        BtnCloseIn4.setBounds(640, 28, 100, 30);

        BtnSimpan4.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan4.setMnemonic('S');
        BtnSimpan4.setText("Simpan");
        BtnSimpan4.setToolTipText("Alt+S");
        BtnSimpan4.setName("BtnSimpan4"); // NOI18N
        BtnSimpan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan4ActionPerformed(evt);
            }
        });
        internalFrame5.add(BtnSimpan4);
        BtnSimpan4.setBounds(530, 28, 100, 30);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Diagnosa :");
        jLabel22.setName("jLabel22"); // NOI18N
        internalFrame5.add(jLabel22);
        jLabel22.setBounds(0, 32, 77, 23);

        kdICD.setEditable(false);
        kdICD.setForeground(new java.awt.Color(0, 0, 0));
        kdICD.setName("kdICD"); // NOI18N
        internalFrame5.add(kdICD);
        kdICD.setBounds(81, 32, 60, 23);

        nmDiagnosa.setEditable(false);
        nmDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        nmDiagnosa.setName("nmDiagnosa"); // NOI18N
        internalFrame5.add(nmDiagnosa);
        nmDiagnosa.setBounds(144, 32, 350, 23);

        btnDiagnosa.setForeground(new java.awt.Color(0, 0, 0));
        btnDiagnosa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnDiagnosa.setMnemonic('7');
        btnDiagnosa.setToolTipText("ALt+7");
        btnDiagnosa.setName("btnDiagnosa"); // NOI18N
        btnDiagnosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagnosaActionPerformed(evt);
            }
        });
        internalFrame5.add(btnDiagnosa);
        btnDiagnosa.setBounds(495, 32, 28, 23);

        WindowDiagnosa.getContentPane().add(internalFrame5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Nomor Surat Kontrol/SPRI BPJS VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSurat.setAutoCreateRowSorter(true);
        tbSurat.setToolTipText("");
        tbSurat.setComponentPopupMenu(jPopupMenu);
        tbSurat.setName("tbSurat"); // NOI18N
        tbSurat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSuratMouseClicked(evt);
            }
        });
        tbSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbSuratKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbSurat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 55));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Filter Data Berdasarkan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass6.add(jLabel19);

        tglRencana.setBackground(new java.awt.Color(240, 250, 230));
        tglRencana.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(tglRencana);
        tglRencana.setSelected(true);
        tglRencana.setText("Tgl. Rencana Kontrol");
        tglRencana.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglRencana.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tglRencana.setName("tglRencana"); // NOI18N
        tglRencana.setPreferredSize(new java.awt.Dimension(130, 23));
        panelGlass6.add(tglRencana);

        tglEntri.setBackground(new java.awt.Color(240, 250, 230));
        tglEntri.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(tglEntri);
        tglEntri.setText("Tgl. Entri");
        tglEntri.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tglEntri.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tglEntri.setName("tglEntri"); // NOI18N
        tglEntri.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass6.add(tglEntri);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tanggal : ");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass6.add(jLabel20);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-03-2022" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass6.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "04-03-2022" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass6.add(DTPCari2);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Key Word : ");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel18);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass6.add(TCari);

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

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil();
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void MnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusActionPerformed
        if (tbSurat.getSelectedRow() != -1) {
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
            tbSurat.requestFocus();
        }
    }//GEN-LAST:event_MnHapusActionPerformed

    private void tbSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_SPACE)) {
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbSuratKeyPressed

    private void tbSuratMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSuratMouseClicked
        if (tabMode.getRowCount() != 0) {
                try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbSuratMouseClicked

    private void MnSimpanSPRIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanSPRIActionPerformed
        if (tbSurat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString().equals("2")) {
                JOptionPane.showMessageDialog(null, "Yang akan disimpan bukan data SPRI, anda salah memilih data...!");
                tbSurat.requestFocus();
            } else if (Sequel.cariInteger("SELECT COUNT(-1) FROM bridging_sep bs INNER JOIN kamar_inap ki ON ki.no_rawat=bs.no_rawat "
                    + "WHERE bs.no_kartu='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString() + "' ORDER BY bs.tglsep DESC LIMIT 1") > 0) {
                x = JOptionPane.showConfirmDialog(rootPane, "Data SEP sdh. tersimpan, data SPRI tgl. inap "
                        + tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString() + " tdk. diperlukan lagi. Apakah tetap akan disimpan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (x == JOptionPane.YES_OPTION) {
                    kdICD.setText("");
                    nmDiagnosa.setText("");
                    WindowDiagnosa.setSize(763, 86);
                    WindowDiagnosa.setLocationRelativeTo(internalFrame1);
                    WindowDiagnosa.setVisible(true);
                }
            } else {
                kdICD.setText("");
                nmDiagnosa.setText("");
                WindowDiagnosa.setSize(763, 86);
                WindowDiagnosa.setLocationRelativeTo(internalFrame1);
                WindowDiagnosa.setVisible(true);
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik dulu salah satu datanya pada tabel..!!");
            tbSurat.requestFocus();
        }
    }//GEN-LAST:event_MnSimpanSPRIActionPerformed

    private void MnSimpanSURKONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSimpanSURKONActionPerformed
        if (tbSurat.getSelectedRow() != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data Surat Rencana Kontrol mau disimpan ke database..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbSurat.getValueAt(tbSurat.getSelectedRow(), 3).toString().equals("1")) {
                    JOptionPane.showMessageDialog(null, "Yang akan disimpan bukan data Surat Rencana Kontrol, anda salah memilih data...!");
                    tbSurat.requestFocus();
                } else if (Sequel.cariInteger("select count(-1) from bridging_sep where no_sep='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString() + "' and urutan_sep='1'") <= 0) {
                    JOptionPane.showMessageDialog(null, "Data SEP tidak ditemukan, proses tdk. bisa dilanjutkan...!");
                } else {
                    Sequel.menyimpan("bridging_surat_kontrol_bpjs",
                            "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 6).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString() + "',"
                            + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString() + "',"
                            + "'2: Rencana Kontrol',"
                            + "'" + Sequel.cariIsi("select no_rawat from bridging_sep where no_sep='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 7).toString() + "'") + "' and urutan_sep='1'");

                    JOptionPane.showMessageDialog(rootPane, "Data berhasil tersimpan ke tabel bridging_surat_kontrol_bpjs...!");
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik dulu salah satu datanya pada tabel..!!");
            tbSurat.requestFocus();
        }
    }//GEN-LAST:event_MnSimpanSURKONActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowDiagnosa.dispose();        
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnSimpan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan4ActionPerformed
        if (kdICD.getText().trim().equals("")) {
            Valid.textKosong(kdICD, "Diagnosa");
            btnDiagnosa.requestFocus();
        } else if (Sequel.cariInteger("SELECT count(-1) FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat=ki.no_rawat "
                + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                + "WHERE ki.tgl_masuk='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString() + "' "
                + "AND p.no_peserta='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString() + "' AND ki.stts_pulang='-'") <= 0) {
            WindowDiagnosa.dispose();
            JOptionPane.showMessageDialog(rootPane, "Proses simpan data gagal, tgl. rencana inap berbeda dg. tgl. masuk inap...!");
        } else {
            Sequel.menyimpan("bridging_surat_pri_bpjs",
                    "'" + Sequel.cariIsi("SELECT ki.no_rawat FROM kamar_inap ki INNER JOIN reg_periksa rp ON rp.no_rawat=ki.no_rawat "
                            + "INNER JOIN pasien p ON p.no_rkm_medis=rp.no_rkm_medis "
                            + "WHERE ki.tgl_masuk='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString() + "' "
                            + "AND p.no_peserta='" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString() +"' AND ki.stts_pulang='-'") + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 6).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString() + "',"
                    + "'" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString() + "',"
                    + "'" + nmDiagnosa.getText() + "',"
                    + "'" + kdICD.getText() + "'");

            WindowDiagnosa.dispose();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil tersimpan ke tabel bridging_surat_pri_bpjs...!");
        }
    }//GEN-LAST:event_BtnSimpan4ActionPerformed

    private void btnDiagnosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagnosaActionPerformed
        akses.setform("BPJSDataNomorSuratKontrol");
        penyakit.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        penyakit.setLocationRelativeTo(internalFrame1);
        penyakit.setVisible(true);
        penyakit.diagnosa.requestFocus();
    }//GEN-LAST:event_btnDiagnosaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSDataNomorSuratKontrol dialog = new BPJSDataNomorSuratKontrol(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnCloseIn4;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan4;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private javax.swing.JMenuItem MnHapus;
    private javax.swing.JMenuItem MnSimpanSPRI;
    private javax.swing.JMenuItem MnSimpanSURKON;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    public widget.TextBox TCari;
    private javax.swing.JDialog WindowDiagnosa;
    private widget.Button btnDiagnosa;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame5;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private javax.swing.JPopupMenu jPopupMenu;
    private widget.TextBox kdICD;
    private widget.TextBox nmDiagnosa;
    private widget.panelisi panelGlass6;
    private widget.Table tbSurat;
    private widget.RadioButton tglEntri;
    private widget.RadioButton tglRencana;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        filterDong = "";
        if (tglEntri.isSelected() == true) {
            filterDong = "1";
        } else if (tglRencana.isSelected() == true) {
            filterDong = "2";
        }
        
        try {
            cekData(filterDong);
            if (kode.equals("200")) {
                Valid.tabelKosong(tabMode);
                if (respon_lagi.path("list").isArray()) {
                    x = 1;
                    for (JsonNode list : respon_lagi.path("list")) {
                        if (list.path("noSuratKontrol").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("jnsPelayanan").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("namaJnsKontrol").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("noSepAsalKontrol").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("namaPoliAsal").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("namaPoliTujuan").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("namaDokter").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("noKartu").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("nama").asText().toLowerCase().contains(TCari.getText().toLowerCase())) {
                            tabMode.addRow(new Object[]{
                                x + ".",
                                list.path("noSuratKontrol").asText(),
                                list.path("jnsPelayanan").asText(),
                                list.path("jnsKontrol").asText(),
                                list.path("namaJnsKontrol").asText(),
                                list.path("tglRencanaKontrol").asText(),
                                list.path("tglTerbitKontrol").asText(),
                                list.path("noSepAsalKontrol").asText().replaceAll("null", "-"),
                                list.path("poliAsal").asText(),
                                list.path("namaPoliAsal").asText(),
                                list.path("poliTujuan").asText(),
                                list.path("namaPoliTujuan").asText(),
                                list.path("tglSEP").asText().replaceAll("null", "-"),
                                list.path("kodeDokter").asText(),
                                list.path("namaDokter").asText().toUpperCase(),
                                list.path("noKartu").asText(),
                                list.path("nama").asText().toUpperCase(),
                            });
                        }
                        x++;
                    }
                }
            } else {
                System.out.println("Periksa kembali URL/jaringan koneksi internet anda..!");
                Valid.tabelKosong(tabMode);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi PPK Rujukan : " + ex);
        }
    }
    
    private void cekData(String filternya) {
        kode = "";
        pesan = "";
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/ListRencanaKontrol/tglAwal/" + Valid.SetTgl(DTPCari1.getSelectedItem() + "") + "/tglAkhir/" + Valid.SetTgl(DTPCari2.getSelectedItem() + "") + "/filter/" + filternya;

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
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            kode = nameNode.path("code").asText();
            pesan = nameNode.path("message").asText();
            
            if (nameNode.path("code").asText().equals("200")) {
//ini yang baru -----------                
                JsonNode response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));                
                respon_lagi = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
//sampai sini -------------
            } else {
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi Peserta : " + ex);
            if (ex.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
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
                    return new BPJSSuratKontrol.HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);            
	    headers.add("X-Signature",api.getHmac(utc));  
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = prop.getProperty("URLAPIBPJS") + "/RencanaKontrol/Delete";
            
            requestJson = "{"
                    + "\"request\":{"
                    + "\"t_suratkontrol\":{"
                    + "\"noSuratKontrol\":\"" + nosurat + "\","
                    + "\"user\":\"" + akses.getkode() + "\""
                    + "}"
                    + "}"
                    + "}";
            
            System.out.println("Ini mengirim : " + requestJson);
            HttpEntity requestEntity = new HttpEntity(requestJson, headers);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class).getBody());
            JsonNode nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());

            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_surat_kontrol_bpjs", "no_surat", nosurat);
                tampil();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Notifikasi WS VClaim 2.0 : Kode " + nameNode.path("code").asText() + ", Pesan : " + nameNode.path("message").asText());
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    private void getData() {
        nosurat = "";
        if (tbSurat.getSelectedRow() != -1) {
            nosurat = tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString();
        }
    }
    
    public void isCek() {
        if (akses.getkode().equals("Admin Utama") || akses.getHapusSEP() == true) {
            MnHapus.setEnabled(true);
        } else {
            MnHapus.setEnabled(false);
        }
    }
}
