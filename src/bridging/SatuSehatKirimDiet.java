/*
  By Mas Elkhanza
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
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

/**
 *
 * @author dosen
 */
public final class SatuSehatKirimDiet extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i = 0;
    private String link = "", json = "", idpasien = "", idpraktisi = "", idpasienKirim = "", idpraktisiKirim = "";
    private ApiSatuSehat api = new ApiSatuSehat();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode response;
    private SatuSehatCekNIK cekViaSatuSehat = new SatuSehatCekNIK();
    private StringBuilder htmlContent;  
    
    /** Creates new form DlgKamar
     * @param parent
     * @param modal */
    public SatuSehatKirimDiet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(10,2);
        setSize(628,674);

        tabMode = new DefaultTableModel(null, new String[]{
            "P", "Tgl. Registrasi", "No. Rawat", "No. RM", "Nama Pasien", "No.KTP Pasien", "ID Encounter",
            "Instruksi Diet/Gizi", "Petugas/Praktisi", "No.KTP Praktisi", "Tanggal", "ID Diet", "kd_dokter"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                 java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                 java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
                 java.lang.String.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        
        tbDiet.setModel(tabMode);
        tbDiet.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDiet.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 13; i++) {
            TableColumn column = tbDiet.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(20);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(115);
            } else if (i == 3) {
                column.setPreferredWidth(65);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(110);
            } else if (i == 6) {
                column.setPreferredWidth(225);
            } else if (i == 7) {
                column.setPreferredWidth(300);
            } else if (i == 8) {
                column.setPreferredWidth(230);
            } else if (i == 9) {
                column.setPreferredWidth(110);
            } else if (i == 10) {
                column.setPreferredWidth(120);
            } else if (i == 11) {
                column.setPreferredWidth(225);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbDiet.setDefaultRenderer(Object.class, new WarnaTable());
        
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
            link=koneksiDB.URLFHIRSATUSEHAT();
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }   
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
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
        ppPilihSemua = new javax.swing.JMenuItem();
        ppPilihIdDietKosong = new javax.swing.JMenuItem();
        ppBersihkan = new javax.swing.JMenuItem();
        LoadHTML = new widget.editorpane();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDiet = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnAll = new widget.Button();
        BtnKirim = new widget.Button();
        BtnUpdate = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel15 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel18 = new widget.Label();
        cmbData = new widget.ComboBox();
        jLabel16 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        ppPilihSemua.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihSemua.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihSemua.setForeground(new java.awt.Color(50, 50, 50));
        ppPilihSemua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihSemua.setText("Pilih Semua");
        ppPilihSemua.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihSemua.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihSemua.setName("ppPilihSemua"); // NOI18N
        ppPilihSemua.setPreferredSize(new java.awt.Dimension(160, 26));
        ppPilihSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihSemuaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihSemua);

        ppPilihIdDietKosong.setBackground(new java.awt.Color(255, 255, 254));
        ppPilihIdDietKosong.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppPilihIdDietKosong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppPilihIdDietKosong.setText("Pilih ID Diet Kosong");
        ppPilihIdDietKosong.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppPilihIdDietKosong.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppPilihIdDietKosong.setName("ppPilihIdDietKosong"); // NOI18N
        ppPilihIdDietKosong.setPreferredSize(new java.awt.Dimension(160, 26));
        ppPilihIdDietKosong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppPilihIdDietKosongActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppPilihIdDietKosong);

        ppBersihkan.setBackground(new java.awt.Color(255, 255, 254));
        ppBersihkan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppBersihkan.setForeground(new java.awt.Color(50, 50, 50));
        ppBersihkan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppBersihkan.setText("Hilangkan Pilihan");
        ppBersihkan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppBersihkan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppBersihkan.setName("ppBersihkan"); // NOI18N
        ppBersihkan.setPreferredSize(new java.awt.Dimension(160, 26));
        ppBersihkan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppBersihkanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(ppBersihkan);

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setIconImages(null);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Pengiriman Data Diet Satu Sehat ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbDiet.setAutoCreateRowSorter(true);
        tbDiet.setComponentPopupMenu(jPopupMenu1);
        tbDiet.setName("tbDiet"); // NOI18N
        Scroll.setViewportView(tbDiet);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass8.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass8.add(LCount);

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

        BtnKirim.setForeground(new java.awt.Color(0, 0, 0));
        BtnKirim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnKirim.setMnemonic('K');
        BtnKirim.setText("Kirim");
        BtnKirim.setToolTipText("Alt+K");
        BtnKirim.setName("BtnKirim"); // NOI18N
        BtnKirim.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKirimActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnKirim);

        BtnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        BtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/edit_f2.png"))); // NOI18N
        BtnUpdate.setMnemonic('U');
        BtnUpdate.setText("Update");
        BtnUpdate.setToolTipText("Alt+U");
        BtnUpdate.setName("BtnUpdate"); // NOI18N
        BtnUpdate.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });
        panelGlass8.add(BtnUpdate);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Tgl. Registrasi :");
        jLabel15.setName("jLabel15"); // NOI18N
        jLabel15.setPreferredSize(new java.awt.Dimension(85, 23));
        panelGlass9.add(jLabel15);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-01-2024" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("s.d.");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 23));
        panelGlass9.add(jLabel17);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "20-01-2024" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Lihat Data :");
        jLabel18.setName("jLabel18"); // NOI18N
        jLabel18.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel18);

        cmbData.setForeground(new java.awt.Color(0, 0, 0));
        cmbData.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua", "Belum/Gagal", "Berhasil" }));
        cmbData.setName("cmbData"); // NOI18N
        cmbData.setPreferredSize(new java.awt.Dimension(96, 23));
        panelGlass9.add(cmbData);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Key Word :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel16);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(210, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

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
        panelGlass9.add(BtnCari);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,BtnKeluar);}
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            htmlContent = new StringBuilder();
            htmlContent.append(                             
                "<tr class='isi'>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal Registrasi</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.Rawat</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.RM</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Nama Pasien</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Pasien</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Encounter</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Instruksi Diet/Gizi</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Petugas/Praktisi</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>No.KTP Praktisi</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>Tanggal</b></td>"+
                    "<td valign='middle' bgcolor='#FFFAFA' align='center'><b>ID Diet</b></td>"+
                "</tr>"
            );
            for (i = 0; i < tabMode.getRowCount(); i++) {
                htmlContent.append(
                    "<tr class='isi'>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,1).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,2).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,3).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,4).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,5).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,6).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,7).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,8).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,9).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,10).toString()+"</td>"+
                        "<td valign='top'>"+tbDiet.getValueAt(i,11).toString()+"</td>"+
                    "</tr>");
            }
            LoadHTML.setText(
                "<html>"+
                  "<table width='1500px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                   htmlContent.toString()+
                  "</table>"+
                "</html>"
            );

            File g = new File("file2.css");            
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
            );
            bg.close();

            File f = new File("DataSatuSehatDiet.html");            
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
            bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                        "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                        "<table width='1700px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                            "<tr class='isi2'>"+
                                "<td valign='top' align='center'>"+
                                    "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                    akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                    akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                    "<font size='2' face='Tahoma'>DATA PENGIRIMAN SATU SEHAT DIET<br><br></font>"+        
                                "</td>"+
                           "</tr>"+
                        "</table>")
            );
            bw.close();                         
            Desktop.getDesktop().browse(f.toURI());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());       
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDiet.requestFocus();
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
        } else {
            Valid.pindah(evt, TCari, BtnPrint);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void BtnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKirimActionPerformed
        cekIdDokter();
        cekIdPasien();
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (tbDiet.getValueAt(i, 0).toString().equals("true") 
                    && (!tbDiet.getValueAt(i, 5).toString().equals("")) 
                    && (!tbDiet.getValueAt(i, 6).toString().equals("")) 
                    && (!tbDiet.getValueAt(i, 9).toString().equals("")) 
                    && tbDiet.getValueAt(i, 11).toString().equals("")) {
                try {
                    idpraktisiKirim = Sequel.cariIsi("select kd_dokter_satu_sehat from mapping_dokter where kd_dokter_rs='" + tbDiet.getValueAt(i, 12).toString() + "'");
                    idpasienKirim = Sequel.cariIsi("select id_pasien from satu_sehat_mapping_pasien where no_rkm_medis='" + tbDiet.getValueAt(i, 3).toString() + "'");
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                        json = "{" +
                                    "\"resourceType\" : \"Composition\"," +
                                    "\"identifier\" : {" +
                                        "\"system\" : \"http://sys-ids.kemkes.go.id/composition/"+koneksiDB.IDSATUSEHAT()+"\"," +
                                        "\"value\" : \""+tbDiet.getValueAt(i,2).toString()+"\"" +
                                    "}," +
                                    "\"status\" : \"final\"," +
                                    "\"type\" : {" +
                                        "\"coding\" : [" +
                                            "{" +
                                                "\"system\" : \"http://loinc.org\"," +
                                                "\"code\" : \"18842-5\"," +
                                                "\"display\" : \"Discharge summary\"" +
                                            "}" +
                                        "]" +
                                    "}," +
                                    "\"category\" : [" +
                                        "{" +
                                            "\"coding\" : [" +
                                                "{" +
                                                    "\"system\" : \"http://loinc.org\"," +
                                                    "\"code\" : \"LP173421-1\"," +
                                                    "\"display\" : \"Report\"" +
                                                "}" +
                                            "]" +
                                        "}" +
                                    "]," +
                                    "\"subject\" : {" +
                                        "\"reference\" : \"Patient/"+idpasienKirim+"\"," +
                                        "\"display\" : \""+tbDiet.getValueAt(i,4).toString()+"\"" +
                                    "}," +
                                    "\"encounter\" : {" +
                                        "\"reference\" : \"Encounter/"+tbDiet.getValueAt(i,6).toString()+"\","+
                                        "\"display\" : \"Kunjungan "+tbDiet.getValueAt(i,4).toString()+" pada tanggal "+tbDiet.getValueAt(i,1).toString()+" dengan nomor kunjungan "+tbDiet.getValueAt(i,2).toString()+"\""+
                                    "}," +
                                    "\"date\" : \""+tbDiet.getValueAt(i,10).toString().replaceAll(" ","T")+"01+08:00\" ," +
                                    "\"author\" : [" +
                                        "{" +
                                            "\"reference\" : \"Practitioner/"+idpraktisiKirim+"\"," +
                                            "\"display\" : \""+tbDiet.getValueAt(i,8).toString()+"\"" +
                                        "}" +
                                    "]," +
                                    "\"title\" : \"Modul Gizi\"," +
                                    "\"custodian\" : {" +
                                        "\"reference\" : \"Organization/"+koneksiDB.IDSATUSEHAT()+"\"" +
                                    "}," +
                                    "\"section\" : [" +
                                        "{" +
                                            "\"code\" : {" +
                                                "\"coding\" : [" +
                                                    "{" +
                                                        "\"system\" : \"http://loinc.org\"," +
                                                        "\"code\" : \"42344-2\"," +
                                                        "\"display\" : \"Discharge diet (narrative)\"" +
                                                    "}" +
                                                "]" +
                                            "}," +
                                            "\"text\" : {" +
                                                "\"status\" : \"additional\"," +
                                                "\"div\" : \""+tbDiet.getValueAt(i,7).toString()+"\"" +
                                            "}" +
                                        "}" +
                                    "]" +
                                "}";
                        System.out.println("URL : " + link + "/Composition");
                        System.out.println("Request JSON : " + json);
                        requestEntity = new HttpEntity(json, headers);
                        json = api.getRest().exchange(link + "/Composition", HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Result JSON : " + json);
                        root = mapper.readTree(json);
                        response = root.path("id");
                        if (!response.asText().equals("")) {
                            if (Sequel.menyimpantf2("satu_sehat_diet", "?,?,?", "Diet/Gizi", 3, new String[]{
                                tbDiet.getValueAt(i, 2).toString(), tbDiet.getValueAt(i, 10).toString().substring(0, 19), response.asText()
                            }) == true) {
                                tbDiet.setValueAt(response.asText(), i, 11);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Bridging : " + e);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
    }//GEN-LAST:event_BtnKirimActionPerformed

    private void ppPilihSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihSemuaActionPerformed
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            tbDiet.setValueAt(true, i, 0);
        }
    }//GEN-LAST:event_ppPilihSemuaActionPerformed

    private void ppBersihkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppBersihkanActionPerformed
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            tbDiet.setValueAt(false, i, 0);
        }
    }//GEN-LAST:event_ppBersihkanActionPerformed

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        cekIdDokter();
        cekIdPasien();
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (tbDiet.getValueAt(i, 0).toString().equals("true") 
                    && (!tbDiet.getValueAt(i, 5).toString().equals("")) 
                    && (!tbDiet.getValueAt(i, 6).toString().equals("")) 
                    && (!tbDiet.getValueAt(i, 9).toString().equals("")) 
                    && (!tbDiet.getValueAt(i, 11).toString().equals(""))) {
                try {
                    idpraktisiKirim = Sequel.cariIsi("select kd_dokter_satu_sehat from mapping_dokter where kd_dokter_rs='" + tbDiet.getValueAt(i, 12).toString() + "'");
                    idpasienKirim = Sequel.cariIsi("select id_pasien from satu_sehat_mapping_pasien where no_rkm_medis='" + tbDiet.getValueAt(i, 3).toString() + "'");
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
                        json = "{" +
                                    "\"resourceType\" : \"Composition\"," +
                                    "\"id\": \""+tbDiet.getValueAt(i,11).toString()+"\"," +
                                    "\"identifier\" : {" +
                                        "\"system\" : \"http://sys-ids.kemkes.go.id/composition/"+koneksiDB.IDSATUSEHAT()+"\"," +
                                        "\"value\" : \""+tbDiet.getValueAt(i,2).toString()+"\"" +
                                    "}," +
                                    "\"status\" : \"final\"," +
                                    "\"type\" : {" +
                                        "\"coding\" : [" +
                                            "{" +
                                                "\"system\" : \"http://loinc.org\" ," +
                                                "\"code\" : \"18842-5\" ," +
                                                "\"display\" : \"Discharge summary\"" +
                                            "}" +
                                        "]" +
                                    "}," +
                                    "\"category\" : [" +
                                        "{" +
                                            "\"coding\" : [" +
                                                "{" +
                                                    "\"system\" : \"http://loinc.org\" ," +
                                                    "\"code\" : \"LP173421-1\" ," +
                                                    "\"display\" : \"Report\"" +
                                                "}" +
                                            "]" +
                                        "}" +
                                    "]," +
                                    "\"subject\" : {" +
                                        "\"reference\" : \"Patient/"+idpasienKirim+"\" ," +
                                        "\"display\" : \""+tbDiet.getValueAt(i,4).toString()+"\"" +
                                    "}," +
                                    "\"encounter\" : {" +
                                        "\"reference\" : \"Encounter/"+tbDiet.getValueAt(i,6).toString()+"\","+
                                        "\"display\" : \"Kunjungan "+tbDiet.getValueAt(i,4).toString()+" pada tanggal "+tbDiet.getValueAt(i,1).toString()+" dengan nomor kunjungan "+tbDiet.getValueAt(i,2).toString()+"\""+
                                    "}," +
                                    "\"date\" : \""+tbDiet.getValueAt(i,10).toString().replaceAll(" ","T")+"+08:00\" ," +
                                    "\"author\" : [" +
                                        "{" +
                                            "\"reference\" : \"Practitioner/"+idpraktisiKirim+"\" ," +
                                            "\"display\" : \""+tbDiet.getValueAt(i,8).toString()+"\"" +
                                        "}" +
                                    "]," +
                                    "\"title\" : \"Modul Gizi\" ," +
                                    "\"custodian\" : {" +
                                        "\"reference\" : \"Organization/"+koneksiDB.IDSATUSEHAT()+"\"" +
                                    "}," +
                                    "\"section\" : [" +
                                        "{" +
                                            "\"code\" : {" +
                                                "\"coding\" : [" +
                                                    "{" +
                                                        "\"system\" : \"http://loinc.org\" ," +
                                                        "\"code\" : \"42344-2\" ," +
                                                        "\"display\" : \"Discharge diet (narrative)\"" +
                                                    "}" +
                                                "]" +
                                            "}," +
                                            "\"text\" : {" +
                                                "\"status\" : \"additional\" ," +
                                                "\"div\" : \""+tbDiet.getValueAt(i,7).toString()+"\"" +
                                            "}" +
                                        "}" +
                                    "]" +
                                "}";
                        System.out.println("URL : " + link + "/Composition/" + tbDiet.getValueAt(i, 11).toString());
                        System.out.println("Request JSON : " + json);
                        requestEntity = new HttpEntity(json, headers);
                        json = api.getRest().exchange(link + "/Composition/" + tbDiet.getValueAt(i, 11).toString(), HttpMethod.PUT, requestEntity, String.class).getBody();
                        System.out.println("Result JSON : " + json);
                    } catch (Exception e) {
                        System.out.println("Notifikasi Bridging : " + e);
                    }
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
            }
        }
    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            tampil();
        } else {
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ppPilihIdDietKosongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppPilihIdDietKosongActionPerformed
        for (i = 0; i < tbDiet.getRowCount(); i++) {
            if (tbDiet.getValueAt(i, 11).equals("")) {
                tbDiet.setValueAt(true, i, 0);
            }
        }
    }//GEN-LAST:event_ppPilihIdDietKosongActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SatuSehatKirimDiet dialog = new SatuSehatKirimDiet(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnKirim;
    private widget.Button BtnPrint;
    private widget.Button BtnUpdate;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Label LCount;
    private widget.editorpane LoadHTML;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.ComboBox cmbData;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private javax.swing.JMenuItem ppBersihkan;
    private javax.swing.JMenuItem ppPilihIdDietKosong;
    private javax.swing.JMenuItem ppPilihSemua;
    private widget.Table tbDiet;
    // End of variables declaration//GEN-END:variables
    
    private void tampil() {
        Valid.tabelKosong(tabMode);
        try {
            if (cmbData.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement(
                        "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "'-' instruksi, pg.nama, pg.no_ktp ktppraktisi, rp.tgl_registrasi tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, rp.kd_dokter "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON rp.kd_dokter = pg.nik "
                        + "LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = rp.no_rawat where rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            } else if (cmbData.getSelectedIndex() == 1) {
                ps = koneksi.prepareStatement(
                        "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "'-' instruksi, pg.nama, pg.no_ktp ktppraktisi, rp.tgl_registrasi tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, rp.kd_dokter "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON rp.kd_dokter = pg.nik "
                        + "LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = rp.no_rawat "
                        + "where (ss.id_diet is null or ss.id_diet='') and rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            } else if (cmbData.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement(
                        "SELECT rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "'-' instruksi, pg.nama, pg.no_ktp ktppraktisi, rp.tgl_registrasi tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, rp.kd_dokter "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat INNER JOIN pegawai pg ON rp.kd_dokter = pg.nik "
                        + "LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = rp.no_rawat "
                        + "where ss.id_diet<>'' and rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            }
            
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("id_encounter"), rs.getString("instruksi"), rs.getString("nama"), rs.getString("ktppraktisi"),
                        rs.getString("tanggal"), rs.getString("satu_sehat_diet"), rs.getString("kd_dokter")
                    });
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

            if (cmbData.getSelectedIndex() == 0) {
                ps = koneksi.prepareStatement(
                        "select rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "ifnull(c.instruksi_nakes,'-') instruksi, pg.nama, pg.no_ktp ktppraktisi, c.tgl_cppt tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, c.nip_ppa "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                        + "INNER JOIN cppt c ON c.no_rawat = rp.no_rawat and c.jenis_ppa='Nutrisionis' "
                        + "INNER JOIN pegawai pg ON c.nip_ppa = pg.nik LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = c.no_rawat AND ss.tanggal = c.tgl_cppt "
                        + "where ki.stts_pulang not in ('-','Pindah Kamar') and c.instruksi_nakes<>'' and rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            } else if (cmbData.getSelectedIndex() == 1) {
                ps = koneksi.prepareStatement(
                        "select rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "ifnull(c.instruksi_nakes,'-') instruksi, pg.nama, pg.no_ktp ktppraktisi, c.tgl_cppt tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, c.nip_ppa "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                        + "INNER JOIN cppt c ON c.no_rawat = rp.no_rawat and c.jenis_ppa='Nutrisionis' "
                        + "INNER JOIN pegawai pg ON c.nip_ppa = pg.nik LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = c.no_rawat AND ss.tanggal = c.tgl_cppt "
                        + "where (ss.id_diet is null or ss.id_diet='') and ki.stts_pulang not in ('-','Pindah Kamar') and c.instruksi_nakes<>'' and rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            } else if (cmbData.getSelectedIndex() == 2) {
                ps = koneksi.prepareStatement(
                        "select rp.tgl_registrasi, rp.jam_reg, rp.no_rawat, rp.no_rkm_medis, p.nm_pasien, p.no_ktp, se.id_encounter, "
                        + "ifnull(c.instruksi_nakes,'-') instruksi, pg.nama, pg.no_ktp ktppraktisi, c.tgl_cppt tanggal, ifnull(ss.id_diet,'') satu_sehat_diet, c.nip_ppa "
                        + "FROM reg_periksa rp INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis "
                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN satu_sehat_encounter se ON se.no_rawat = rp.no_rawat "
                        + "INNER JOIN cppt c ON c.no_rawat = rp.no_rawat and c.jenis_ppa='Nutrisionis' "
                        + "INNER JOIN pegawai pg ON c.nip_ppa = pg.nik LEFT JOIN satu_sehat_diet ss ON ss.no_rawat = c.no_rawat AND ss.tanggal = c.tgl_cppt "
                        + "where ss.id_diet<>'' and ki.stts_pulang not in ('-','Pindah Kamar') and c.instruksi_nakes<>'' and rp.tgl_registrasi between ? and ? "
                        + (TCari.getText().equals("") ? "" : "and (rp.no_rawat like ? or rp.no_rkm_medis like ? or "
                        + "p.nm_pasien like ? or p.no_ktp like ? or pg.no_ktp like ? or pg.nama like ?) ")
                        + "order by rp.tgl_registrasi,rp.jam_reg,rp.no_rawat");
            }
            
            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                if (!TCari.getText().equals("")) {
                    ps.setString(3, "%" + TCari.getText() + "%");
                    ps.setString(4, "%" + TCari.getText() + "%");
                    ps.setString(5, "%" + TCari.getText() + "%");
                    ps.setString(6, "%" + TCari.getText() + "%");
                    ps.setString(7, "%" + TCari.getText() + "%");
                    ps.setString(8, "%" + TCari.getText() + "%");
                }
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new Object[]{
                        false, rs.getString("tgl_registrasi") + " " + rs.getString("jam_reg"), rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("nm_pasien"),
                        rs.getString("no_ktp"), rs.getString("id_encounter"), rs.getString("instruksi"), rs.getString("nama"), rs.getString("ktppraktisi"),
                        rs.getString("tanggal"), rs.getString("satu_sehat_diet"), rs.getString("nip_ppa")
                    });
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

    public void isCek(){
        BtnKirim.setEnabled(akses.getsatu_sehat());
        BtnPrint.setEnabled(akses.getsatu_sehat());
    }
    
    private void cekIdPasien() {
        idpasien = "";
        try {
            for (i = 0; i < tbDiet.getRowCount(); i++) {
                if (tbDiet.getValueAt(i, 0).toString().equals("true")
                        && (!tbDiet.getValueAt(i, 5).toString().equals("")
                        || tbDiet.getValueAt(i, 5).toString().length() == 16)) {

                    idpasien = cekViaSatuSehat.tampilIDPasien(tbDiet.getValueAt(i, 5).toString());
                    if (!idpasien.equals("")) {
                        if (Sequel.cariInteger("select count(-1) from satu_sehat_mapping_pasien where no_rkm_medis='" + tbDiet.getValueAt(i, 3).toString() + "'") == 0) {
                            Sequel.menyimpan("satu_sehat_mapping_pasien", "?,?", "No. Rekam Medis", 2, new String[]{
                                tbDiet.getValueAt(i, 3).toString(), idpasien
                            });
                        } else {
                            if (Sequel.cariIsi("select id_pasien from satu_sehat_mapping_pasien where no_rkm_medis='" + tbDiet.getValueAt(i, 3).toString() + "'").equals("")) {
                                Sequel.mengedit("satu_sehat_mapping_pasien", "no_rkm_medis='" + tbDiet.getValueAt(i, 3).toString() + "'", "id_pasien='" + idpasien + "'");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void cekIdDokter() {
        idpraktisi = "";
        try {
            for (i = 0; i < tbDiet.getRowCount(); i++) {
                if (tbDiet.getValueAt(i, 0).toString().equals("true")
                        && !tbDiet.getValueAt(i, 9).toString().equals("")) {
                 
                    idpraktisi = cekViaSatuSehat.tampilIDParktisi(tbDiet.getValueAt(i, 9).toString());
                    if (!idpraktisi.equals("")) {
                        if (Sequel.cariInteger("select count(-1) from mapping_dokter where kd_dokter_rs='" + tbDiet.getValueAt(i, 12).toString() + "'") == 0) {
                            Sequel.menyimpan("mapping_dokter", "?,?,?", "Kode Dokter", 3, new String[]{"", tbDiet.getValueAt(i, 12).toString(), idpraktisi});
                        } else {
                            if (Sequel.cariIsi("select ifnull(kd_dokter_satu_sehat,'') from mapping_dokter where kd_dokter_rs='" + tbDiet.getValueAt(i, 12).toString() + "'").equals("")) {
                                Sequel.mengedit("mapping_dokter", "kd_dokter_rs='" + tbDiet.getValueAt(i, 12).toString() + "'", "kd_dokter_satu_sehat='" + idpraktisi + "'");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
}