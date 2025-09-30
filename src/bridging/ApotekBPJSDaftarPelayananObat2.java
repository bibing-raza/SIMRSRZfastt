/*
  Dilarang keras menggandakan/mengcopy/menyebarkan/membajak/mendecompile 
  Software ini dalam bentuk apapun tanpa seijin pembuat software
  (Khanza.Soft Media). Bagi yang sengaja membajak softaware ini ta
  npa ijin, kami sumpahi sial 1000 turunan, miskin sampai 500 turu
  nan. Selalu mendapat kecelakaan sampai 400 turunan. Anak pertama
  nya cacat tidak punya kaki sampai 300 turunan. Susah cari jodoh
  sampai umur 50 tahun sampai 200 turunan. Ya Alloh maafkan kami 
  karena telah berdoa buruk, semua ini kami lakukan karena kami ti
  dak pernah rela karya kami dibajak tanpa ijin.
 */

package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.Test;
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
public final class ApotekBPJSDaftarPelayananObat2 extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private int i=0, y=0,reply=0;
    private ApiApotekBPJS api=new ApiApotekBPJS();
    private String URL="",link="",utc="",requestJson="";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2,ps3;
    private ResultSet rs,rs2,rs3;

    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public ApotekBPJSDaftarPelayananObat2(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        this.setLocation(10,2);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new String[]{
            "No. SEP Apotek", "No. SEP Asal", "Iterasi", "No. Resep", "No. Kartu", "Nama Peserta", "Kode Jenis", "Jenis Obat", "Tgl. Pelayanan",
            "Kode Obat", "Nama Obat", "Tipe Obat", "Signa 1", "Signa 2", "Racikan", "Kode Poli", "PoliKlinik", "Kode Dokter", "Nama Dokter"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbObat.setModel(tabMode);
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(130);
            } else if (i == 2) {
                column.setPreferredWidth(100);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(95);
            } else if (i == 5) {
                column.setPreferredWidth(220);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(150);
            } else if (i == 8) {
                column.setPreferredWidth(80);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(300);
            } else if (i == 11) {
                column.setPreferredWidth(70);
            } else if (i == 12) {
                column.setPreferredWidth(70);
            } else if (i == 13) {
                column.setPreferredWidth(70);
            } else if (i == 14) {
                column.setPreferredWidth(70);
            } else if (i == 15) {
                column.setPreferredWidth(70);
            } else if (i == 16) {
                column.setPreferredWidth(120);
            } else if (i == 17) {
                column.setPreferredWidth(70);
            } else if (i == 18) {
                column.setPreferredWidth(220);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbObat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(15).setCellRenderer(centerRenderer);
        tbObat.getColumnModel().getColumn(17).setCellRenderer(centerRenderer);
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
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
        } 
        
        try {
            link = koneksiDB.URLAPIAPOTEKBPJS();
        } catch (Exception e) {
            System.out.println("E : " + e);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        ppRwayatResepApotekBPJS = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        panelGlass6 = new widget.panelisi();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel18 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(192, 30));

        ppRwayatResepApotekBPJS.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppRwayatResepApotekBPJS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppRwayatResepApotekBPJS.setText("Riwayat Obat Apotek BPJS");
        ppRwayatResepApotekBPJS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppRwayatResepApotekBPJS.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppRwayatResepApotekBPJS.setName("ppRwayatResepApotekBPJS"); // NOI18N
        ppRwayatResepApotekBPJS.setPreferredSize(new java.awt.Dimension(150, 26));
        ppRwayatResepApotekBPJS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppRwayatResepApotekBPJSActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppRwayatResepApotekBPJS);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass6.setName("panelGlass6"); // NOI18N
        panelGlass6.setPreferredSize(new java.awt.Dimension(44, 54));
        panelGlass6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Keyword :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel16);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
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

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Tgl. Kirim :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass6.add(jLabel18);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-09-2025" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass6.add(jLabel21);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "30-09-2025" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass6.add(DTPCari2);

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
        panelGlass6.add(BtnHapus);

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

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass6.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass6.add(LCount);

        internalFrame1.add(panelGlass6, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tampil();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            tampil();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TCariKeyPressed

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

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            if (!tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("")) {
                reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin No. Apotek " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " dan obat yang dipilih mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        bodyWithDeleteRequestPerobat(tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(),
                                tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString(),
                                tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString(), "N");
                    } catch (Exception ex) {
                        Logger.getLogger(ApotekBPJSDaftarPelayananObat2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    tampil();
                }
            } else {
                reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin No. Apotek " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " dan semua obatnya mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    try {
                        ps = koneksi.prepareStatement("SELECT m.kode_brng_apotek_bpjs, b.racikan FROM bridging_apotek_bpjs_obat b "
                                + "INNER JOIN maping_obat_apotek_bpjs m ON b.kd_obat=m.kode_brng_apotek_bpjs WHERE "
                                + "b.no_sjp='" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "' AND b.racikan='0'");
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            bodyWithDeleteRequest(rs.getString("kode_brng_apotek_bpjs"), "N");
                        }
                        
                        if (Sequel.cariIsi("select no_sjp from bridging_apotek_bpjs_obat where no_sjp=? AND racikan='0'", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString()).isEmpty()) {
                            Sequel.meghapus("bridging_apotek_bpjs_obat", "no_sjp", "racikan", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), "1");
                            bodyWithDeleteRequestResep();
                        }

                    } catch (Exception ex) {
                        System.out.println("Notifikasi Bridging : " + ex);
                    }
                } else {
                    tampil();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data yang mau dihapus..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void ppRwayatResepApotekBPJSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppRwayatResepApotekBPJSActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            if (tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString().equals("")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                ApotekBPJSRiwayatPelayananResep apotek = new ApotekBPJSRiwayatPelayananResep(null, true);
                apotek.setNoRm(tbObat.getValueAt(tbObat.getSelectedRow(), 4).toString(), DTPCari1.getDate(), DTPCari2.getDate());
                apotek.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
                apotek.setLocationRelativeTo(internalFrame1);
                apotek.setVisible(true);
                this.setCursor(Cursor.getDefaultCursor());
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data di bagian nomor kartu pasien..!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih dulu data..!!");
        }
    }//GEN-LAST:event_ppRwayatResepApotekBPJSActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSDaftarPelayananObat2 dialog = new ApotekBPJSDaftarPelayananObat2(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel21;
    private widget.Label jLabel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass6;
    private javax.swing.JMenuItem ppRwayatResepApotekBPJS;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT ba.no_apotek, ba.no_sep, ba.no_resep, bs.no_kartu, bs.nama_pasien, ba.jenis_obat, ba.tgl_pelayanan, ba.tgl_resep, "
                    + "ba.keterangan, bs.kdpolitujuan, bs.nmpolitujuan, bs.kddpjp, bs.nmdpdjp FROM bridging_apotek_bpjs ba "
                    + "INNER JOIN bridging_sep bs ON ba.no_sep=bs.no_sep WHERE "
                    + "ba.tgl_pelayanan BETWEEN ? and ? " + (TCari.getText().trim().equals("") ? "" : ""
                    + "and ba.no_apotek like ? or bs.no_sep like ? or bs.nama_pasien like ? or bs.no_kartu like ? or ba.no_resep like ? "));
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().trim().equals("")) {
                    ps.setString(3, "%" + TCari.getText().trim() + "%");
                    ps.setString(4, "%" + TCari.getText().trim() + "%");
                    ps.setString(5, "%" + TCari.getText().trim() + "%");
                    ps.setString(6, "%" + TCari.getText().trim() + "%");
                    ps.setString(7, "%" + TCari.getText().trim() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_apotek"),
                        rs.getString("no_sep"),
                        rs.getString("keterangan"),
                        rs.getString("no_resep"),
                        rs.getString("no_kartu"),
                        rs.getString("nama_pasien"),
                        rs.getString("jenis_obat"),
                        (rs.getString("jenis_obat").equals("1") ? "Obat PRB" : (rs.getString("jenis_obat").equals("2") ? "Obat Kronis Belum Stabil" : "Obat Kemoterapi")),
                        rs.getString("tgl_pelayanan"), "", "", "", "", "", "",
                        rs.getString("kdpolitujuan"),
                        rs.getString("nmpolitujuan"),
                        rs.getString("kddpjp"),
                        rs.getString("nmdpdjp")
                    });

//                    tampil obat
                    ps2 = koneksi.prepareStatement("select * from bridging_apotek_bpjs_obat ba where ba.no_sjp='" + rs.getString("no_apotek") + "'");
                    rs2 = ps2.executeQuery();
                    while (rs2.next()) {
                        tabMode.addRow(new Object[]{
                            rs.getString("no_apotek"),
                            rs.getString("no_sep"),
                            rs.getString("keterangan"),
                            rs.getString("no_resep"), "", "", "", "", "",
                            rs2.getString("kd_obat"),
                            rs2.getString("nm_obat"),
                            rs2.getString("jumlah"),
                            rs2.getString("signa1"),
                            rs2.getString("signa2"), (rs2.getString("racikan").equals("1") ? "ya" : "tidak")
                        });
                    }
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
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
        LCount.setText("" + tabMode.getRowCount());
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

    public void bodyWithDeleteRequest(String Kode_obat, String tipe_obat) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory() {
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);

        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            URL = link + "/pelayanan/obat/hapus";
            System.out.println("URL : " + URL);
            requestJson = "{ "
                    + "\"nosepapotek\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "\","
                    + "\"noresep\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() + "\","
                    + "\"kodeobat\":\"" + Kode_obat + "\","
                    + "\"tipeobat\":\"" + tipe_obat + "\"}  ";
            System.out.println("respone : " + requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_apotek_bpjs_obat", "no_sjp", "kd_obat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), Kode_obat);
//                JOptionPane.showMessageDialog(null, "Obat " + Kode_obat + " - " + Sequel.cariIsi("select nama_brng_apotek_bpjs from maping_obat_apotek_bpjs where kode_brng_apotek_bpjs=?", Kode_obat) + " berhasil dihapus..!!");
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }            
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void bodyWithDeleteRequestPerobat(String no_apotek, String no_resep, String Kode_obat, String tipe_obat) throws Exception {
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
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            URL = link + "/pelayanan/obat/hapus";
            System.out.println("URL : " + URL);
            requestJson = "{ "
                    + "\"nosepapotek\":\"" + no_apotek + "\","
                    + "\"noresep\":\"" + no_resep + "\","
                    + "\"kodeobat\":\"" + Kode_obat + "\","
                    + "\"tipeobat\":\"" + tipe_obat + "\"}  ";
            System.out.println("respone : " + requestJson);
            requestEntity = new HttpEntity(requestJson, headers);
            root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_apotek_bpjs_obat", "no_sjp", "kd_obat", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString(), tbObat.getValueAt(tbObat.getSelectedRow(), 9).toString());
                JOptionPane.showMessageDialog(null, "Obat " + Kode_obat + " - " + Sequel.cariIsi("select nama_brng_apotek_bpjs from maping_obat_apotek_bpjs where kode_brng_apotek_bpjs=?", Kode_obat) + " berhasil dihapus.. !!");
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
            tampil();
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
                tampil();
            }
        }
    }
    
    public void bodyWithDeleteRequestResep() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers = {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }
            }
        };
        sslContext.init(null, trustManagers, new SecureRandom());
        SSLSocketFactory sslFactory = new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme = new Scheme("https", 443, sslFactory);

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory() {
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
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID", koneksiDB.CONSIDAPIAPOTEKBPJS());
            utc = String.valueOf(api.GetUTCdatetimeAsString());
            headers.add("X-Timestamp", utc);
            headers.add("X-Signature", api.getHmac(utc));
            headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
            URL = link + "/hapusresep";
            requestJson = "{ "
                    + "\"nosjp\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + "\","
                    + "\"refasalsjp\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 1).toString() + "\","
                    + "\"noresep\":\"" + tbObat.getValueAt(tbObat.getSelectedRow(), 3).toString() + "\"}  ";
            requestEntity = new HttpEntity(requestJson, headers);
            root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE, requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : " + nameNode.path("code").asText());
            System.out.println("message : " + nameNode.path("message").asText());
            if (nameNode.path("code").asText().equals("200")) {
                Sequel.meghapus("bridging_apotek_bpjs", "no_apotek", tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString());
                JOptionPane.showMessageDialog(null, "Resep " + tbObat.getValueAt(tbObat.getSelectedRow(), 0).toString() + " berhasil dihapus,..!!");
                tampil();
            } else {
                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
            if (e.toString().contains("UnknownHostException")) {
                JOptionPane.showMessageDialog(null, "Koneksi ke server BPJS terputus...!");
            }
        }
    }
    
    public void setNoRm(String nosep) {        
        TCari.setText(nosep);
    }
    
    public void isCek(String nokartu) {        
        TCari.setText(nokartu);
    }
    
    public JTable getTable(){
        return tbObat;
    }
}
