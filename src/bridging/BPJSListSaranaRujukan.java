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
public final class BPJSListSaranaRujukan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private final Properties prop = new Properties();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private BPJSApi api = new BPJSApi();
    private BPJSCekReferensiFaskes faskes = new BPJSCekReferensiFaskes(null, false);
    private String utc = "", URL = "", requestJson = "", kode = "", pesan = "";
    private PreparedStatement ps;
    private ResultSet rs;
    private int x = 0;
    public JsonNode respon_lagi;
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public BPJSListSaranaRujukan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        Object[] row = {"No.","Kode","Nama Sarana"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        
        tbSarana.setModel(tabMode);
        tbSarana.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbSarana.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbSarana.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(60);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            }
        }        
        tbSarana.setDefaultRenderer(Object.class, new WarnaTable());
        
        faskes.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (faskes.getTable().getSelectedRow() != -1) {
                    kdRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 1).toString());
                    NmPpkRujukan.setText(faskes.getTable().getValueAt(faskes.getTable().getSelectedRow(), 2).toString());
                    btnPPKRujukan.requestFocus();
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
        
        faskes.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    faskes.dispose();
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

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbSarana = new widget.Table();
        PanelInput = new javax.swing.JPanel();
        panelGlass6 = new widget.panelisi();
        jLabel19 = new widget.Label();
        kdRujukan = new widget.TextBox();
        NmPpkRujukan = new widget.TextBox();
        btnPPKRujukan = new widget.Button();
        jLabel18 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel17 = new widget.Label();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pencarian Data/List Sarana Rujukan BPJS VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbSarana.setAutoCreateRowSorter(true);
        tbSarana.setToolTipText("");
        tbSarana.setName("tbSarana"); // NOI18N
        Scroll.setViewportView(tbSarana);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(200, 55));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("PPK Rujukan : ");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass6.add(jLabel19);

        kdRujukan.setEditable(false);
        kdRujukan.setForeground(new java.awt.Color(0, 0, 0));
        kdRujukan.setName("kdRujukan"); // NOI18N
        kdRujukan.setPreferredSize(new java.awt.Dimension(75, 23));
        panelGlass6.add(kdRujukan);

        NmPpkRujukan.setEditable(false);
        NmPpkRujukan.setBackground(new java.awt.Color(245, 250, 240));
        NmPpkRujukan.setForeground(new java.awt.Color(0, 0, 0));
        NmPpkRujukan.setHighlighter(null);
        NmPpkRujukan.setName("NmPpkRujukan"); // NOI18N
        NmPpkRujukan.setPreferredSize(new java.awt.Dimension(260, 23));
        panelGlass6.add(NmPpkRujukan);

        btnPPKRujukan.setForeground(new java.awt.Color(0, 0, 0));
        btnPPKRujukan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPPKRujukan.setMnemonic('X');
        btnPPKRujukan.setToolTipText("Alt+X");
        btnPPKRujukan.setName("btnPPKRujukan"); // NOI18N
        btnPPKRujukan.setPreferredSize(new java.awt.Dimension(28, 23));
        btnPPKRujukan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPPKRujukanActionPerformed(evt);
            }
        });
        panelGlass6.add(btnPPKRujukan);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Kode/Nama Sarana : ");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(140, 23));
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
        if (kdRujukan.getText().trim().equals("")) {
            Valid.textKosong(kdRujukan, "PPK Rujukan");
        } else {
            tampil();
        }
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

    private void btnPPKRujukanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPPKRujukanActionPerformed
        faskes.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        faskes.setLocationRelativeTo(internalFrame1);
        faskes.setVisible(true);
        faskes.fokus();
    }//GEN-LAST:event_btnPPKRujukanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSListSaranaRujukan dialog = new BPJSListSaranaRujukan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnKeluar;
    private widget.TextBox NmPpkRujukan;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    public widget.Button btnPPKRujukan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    public widget.TextBox kdRujukan;
    private widget.panelisi panelGlass6;
    private widget.Table tbSarana;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        try {
            cekData();
            if (kode.equals("200")) {
                Valid.tabelKosong(tabMode);
                if (respon_lagi.path("list").isArray()) {
                    x = 1;
                    for (JsonNode list : respon_lagi.path("list")) {
                        if (list.path("kodeSarana").asText().toLowerCase().contains(TCari.getText().toLowerCase())
                                || list.path("namaSarana").asText().toLowerCase().contains(TCari.getText().toLowerCase())) {
                            tabMode.addRow(new Object[]{
                                x + ".",
                                list.path("kodeSarana").asText(),
                                list.path("namaSarana").asText()
                            });
                        }
                        x++;
                    }
                }
            } else {
                System.out.println("Notifikasi WS VClaim 2.0 : Kode " + kode + ", Isi Pesan : " + pesan);
                Valid.tabelKosong(tabMode);
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi PPK Rujukan : " + ex);
        }
    }
    
    private void cekData() {
        kode = "";
        pesan = "";
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
            URL = prop.getProperty("URLAPIBPJS") + "/Rujukan/ListSarana/PPKRujukan/" + kdRujukan.getText();

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
}
