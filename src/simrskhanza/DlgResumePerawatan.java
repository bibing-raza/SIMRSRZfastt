/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */
package simrskhanza;

import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

/**
 *
 * @author perpustakaan
 */
public final class DlgResumePerawatan extends javax.swing.JDialog {

    private final Connection koneksi = koneksiDB.condb();
    private final sekuel Sequel = new sekuel();
    private final Properties prop = new Properties();
    private validasi Valid = new validasi();
    private ResultSet rs, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rshal, rsObat, rsDiag, rsDiag1, rsasesmenRJ,
            rsLIS1, rsLIS2, rsLIS3, rsLISMaster, rsTHT;
    private String sql, host = "";
    private int x = 0,lis1 = 0, lis2 = 0, lisM = 0;

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgResumePerawatan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));
        NoRM.setDocument(new batasInput((byte) 6).getOnlyAngka(NoRM));        

        pasien.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (pasien.getTable().getSelectedRow() != -1) {
                    NoRM.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 1).toString());
                    TPasien.setText(pasien.getTable().getValueAt(pasien.getTable().getSelectedRow(), 2).toString());
                }
                NoRM.requestFocus();
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

        pasien.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    pasien.dispose();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));            
        } catch (Exception e) {
            System.out.println("simrskhanza.DlgResumePerawatan.<init>() : " + e);
        }

        LoadHTML.setEditable(true);
        LoadHTML2.setEditable(true);
        LoadHTML3.setEditable(true);
        LoadHTML4.setEditable(true);
        LoadHTML5.setEditable(true);
        LoadHTML6.setEditable(true);
        LoadHTML7.setEditable(true);
        LoadHTML8.setEditable(true);
        LoadHTML9.setEditable(true);
        LoadHTML10.setEditable(true);
        LoadHTML11.setEditable(true);
        LoadHTML12.setEditable(true);
        LoadHTML13.setEditable(true);

        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML.setEditorKit(kit);
        LoadHTML2.setEditorKit(kit);
        LoadHTML3.setEditorKit(kit);
        LoadHTML4.setEditorKit(kit);
        LoadHTML5.setEditorKit(kit);
        LoadHTML6.setEditorKit(kit);
        LoadHTML7.setEditorKit(kit);
        LoadHTML8.setEditorKit(kit);
        LoadHTML9.setEditorKit(kit);
        LoadHTML10.setEditorKit(kit);
        LoadHTML11.setEditorKit(kit);
        LoadHTML12.setEditorKit(kit);
        LoadHTML13.setEditorKit(kit);

        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        LoadHTML.setDocument(doc);
        LoadHTML2.setDocument(doc);
        LoadHTML3.setDocument(doc);
        LoadHTML4.setDocument(doc);
        LoadHTML5.setDocument(doc);
        LoadHTML6.setDocument(doc);
        LoadHTML7.setDocument(doc);
        LoadHTML8.setDocument(doc);
        LoadHTML9.setDocument(doc);
        LoadHTML10.setDocument(doc);
        LoadHTML11.setDocument(doc);
        LoadHTML12.setDocument(doc);
        LoadHTML13.setDocument(doc);
        
        LoadHTML.setEditable(false);
        LoadHTML2.setEditable(false);
        LoadHTML3.setEditable(false);
        LoadHTML4.setEditable(false);
        LoadHTML5.setEditable(false);
        LoadHTML6.setEditable(false);
        LoadHTML7.setEditable(false);
        LoadHTML8.setEditable(false);
        LoadHTML9.setEditable(false);
        LoadHTML10.setEditable(false);
        LoadHTML11.setEditable(false);
        LoadHTML12.setEditable(false);
        LoadHTML13.setEditable(false);

        LoadHTML.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML2.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML3.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                System.out.println(e.getURL());
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML4.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML5.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML6.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML7.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML8.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML9.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML10.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        LoadHTML11.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML12.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        LoadHTML13.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    DlgPasien pasien = new DlgPasien(null, false);
    int y = 0, w = 0, urut;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        ChkTanggal = new widget.CekBox();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label19 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        label17 = new widget.Label();
        NoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        BtnCari1 = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        LoadHTML = new widget.editorpane();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        LoadHTML3 = new widget.editorpane();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        LoadHTML4 = new widget.editorpane();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        LoadHTML5 = new widget.editorpane();
        internalFrame14 = new widget.InternalFrame();
        Scroll12 = new widget.ScrollPane();
        LoadHTML13 = new widget.editorpane();
        internalFrame7 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        LoadHTML6 = new widget.editorpane();
        internalFrame8 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        LoadHTML7 = new widget.editorpane();
        internalFrame9 = new widget.InternalFrame();
        Scroll7 = new widget.ScrollPane();
        LoadHTML8 = new widget.editorpane();
        internalFrame10 = new widget.InternalFrame();
        Scroll8 = new widget.ScrollPane();
        LoadHTML9 = new widget.editorpane();
        internalFrame11 = new widget.InternalFrame();
        Scroll9 = new widget.ScrollPane();
        LoadHTML10 = new widget.editorpane();
        internalFrame12 = new widget.InternalFrame();
        Scroll10 = new widget.ScrollPane();
        LoadHTML11 = new widget.editorpane();
        internalFrame13 = new widget.InternalFrame();
        Scroll11 = new widget.ScrollPane();
        LoadHTML12 = new widget.editorpane();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Resume/Rincian Tindakan/Terapi Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        ChkTanggal.setBorder(null);
        ChkTanggal.setForeground(new java.awt.Color(0, 0, 0));
        ChkTanggal.setText("Tgl. Rawat :");
        ChkTanggal.setBorderPainted(true);
        ChkTanggal.setBorderPaintedFlat(true);
        ChkTanggal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkTanggal.setName("ChkTanggal"); // NOI18N
        ChkTanggal.setOpaque(false);
        ChkTanggal.setPreferredSize(new java.awt.Dimension(85, 23));
        ChkTanggal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkTanggalItemStateChanged(evt);
            }
        });
        panelGlass5.add(ChkTanggal);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelGlass5.add(Tgl1);

        label18.setForeground(new java.awt.Color(0, 0, 0));
        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelGlass5.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelGlass5.add(Tgl2);

        label19.setForeground(new java.awt.Color(0, 0, 0));
        label19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(125, 23));
        panelGlass5.add(label19);

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
        panelGlass5.add(BtnPrint);

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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label17.setForeground(new java.awt.Color(0, 0, 0));
        label17.setText("Pasien :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(55, 23));
        panelisi4.add(label17);

        NoRM.setForeground(new java.awt.Color(0, 0, 0));
        NoRM.setName("NoRM"); // NOI18N
        NoRM.setPreferredSize(new java.awt.Dimension(68, 23));
        NoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoRMKeyPressed(evt);
            }
        });
        panelisi4.add(NoRM);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(390, 23));
        panelisi4.add(TPasien);

        BtnSeek2.setForeground(new java.awt.Color(0, 0, 0));
        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/download24.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelisi4.add(BtnSeek2);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setMnemonic('D');
        BtnCari1.setText("Tampilkan Data");
        BtnCari1.setToolTipText("Alt+D");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(140, 23));
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

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(255, 255, 204));
        TabRawat.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        TabRawat.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        LoadHTML.setBorder(null);
        LoadHTML.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML.setName("LoadHTML"); // NOI18N
        Scroll.setViewportView(LoadHTML);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Seluruh Riwayat Perawatan", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        LoadHTML2.setBorder(null);
        LoadHTML2.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll1.setViewportView(LoadHTML2);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Penyakit/ICD 10", internalFrame3);

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        LoadHTML3.setBorder(null);
        LoadHTML3.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML3.setName("LoadHTML3"); // NOI18N
        Scroll2.setViewportView(LoadHTML3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Prosedur/ICD 9", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        LoadHTML4.setBorder(null);
        LoadHTML4.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML4.setName("LoadHTML4"); // NOI18N
        Scroll3.setViewportView(LoadHTML4);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Rawat Jalan", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        LoadHTML5.setBorder(null);
        LoadHTML5.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML5.setName("LoadHTML5"); // NOI18N
        Scroll4.setViewportView(LoadHTML5);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Rawat Inap", internalFrame6);

        internalFrame14.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame14.setBorder(null);
        internalFrame14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame14.setName("internalFrame14"); // NOI18N
        internalFrame14.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll12.setName("Scroll12"); // NOI18N
        Scroll12.setOpaque(true);

        LoadHTML13.setBorder(null);
        LoadHTML13.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML13.setName("LoadHTML13"); // NOI18N
        Scroll12.setViewportView(LoadHTML13);

        internalFrame14.add(Scroll12, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Ringkasan Pulang Rawat Inap", internalFrame14);

        internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame7.setBorder(null);
        internalFrame7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        LoadHTML6.setBorder(null);
        LoadHTML6.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML6.setName("LoadHTML6"); // NOI18N
        Scroll5.setViewportView(LoadHTML6);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Operasi", internalFrame7);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        LoadHTML7.setBorder(null);
        LoadHTML7.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML7.setName("LoadHTML7"); // NOI18N
        Scroll6.setViewportView(LoadHTML7);

        internalFrame8.add(Scroll6, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Radiologi", internalFrame8);

        internalFrame9.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame9.setBorder(null);
        internalFrame9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll7.setName("Scroll7"); // NOI18N
        Scroll7.setOpaque(true);

        LoadHTML8.setBorder(null);
        LoadHTML8.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML8.setName("LoadHTML8"); // NOI18N
        Scroll7.setViewportView(LoadHTML8);

        internalFrame9.add(Scroll7, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Laboratorium", internalFrame9);

        internalFrame10.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame10.setBorder(null);
        internalFrame10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame10.setName("internalFrame10"); // NOI18N
        internalFrame10.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll8.setName("Scroll8"); // NOI18N
        Scroll8.setOpaque(true);

        LoadHTML9.setBorder(null);
        LoadHTML9.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML9.setName("LoadHTML9"); // NOI18N
        Scroll8.setViewportView(LoadHTML9);

        internalFrame10.add(Scroll8, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Pemberian Obat", internalFrame10);

        internalFrame11.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame11.setBorder(null);
        internalFrame11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame11.setName("internalFrame11"); // NOI18N
        internalFrame11.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        LoadHTML10.setBorder(null);
        LoadHTML10.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML10.setName("LoadHTML10"); // NOI18N
        Scroll9.setViewportView(LoadHTML10);

        internalFrame11.add(Scroll9, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Obat Operasi", internalFrame11);

        internalFrame12.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame12.setBorder(null);
        internalFrame12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame12.setName("internalFrame12"); // NOI18N
        internalFrame12.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        LoadHTML11.setBorder(null);
        LoadHTML11.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML11.setName("LoadHTML11"); // NOI18N
        Scroll10.setViewportView(LoadHTML11);

        internalFrame12.add(Scroll10, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Riwayat Resep Pulang", internalFrame12);

        internalFrame13.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame13.setBorder(null);
        internalFrame13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        LoadHTML12.setBorder(null);
        LoadHTML12.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML12.setName("LoadHTML12"); // NOI18N
        Scroll11.setViewportView(LoadHTML12);

        internalFrame13.add(Scroll11, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Assesmen Rawat Jalan", internalFrame13);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, Tgl1, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void NoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoRMKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
        isPasien();
    } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
        isPasien();
        BtnKeluar.requestFocus();
    } else if (evt.getKeyCode() == KeyEvent.VK_UP) {
        BtnSeek2ActionPerformed(null);
    } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        isPasien();
        BtnPrint.requestFocus();
    }
}//GEN-LAST:event_NoRMKeyPressed

private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
    pasien.isCek();
    pasien.emptTeks();
    pasien.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
    pasien.setLocationRelativeTo(internalFrame1);
    pasien.setVisible(true);
}//GEN-LAST:event_BtnSeek2ActionPerformed

private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
    //Valid.pindah(evt,Tgl2,TKd);
}//GEN-LAST:event_BtnSeek2KeyPressed

private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
    TabRawatMouseClicked(null);
}//GEN-LAST:event_BtnCari1ActionPerformed

private void BtnCari1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari1KeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        TabRawatMouseClicked(null);
    } else {
        // Valid.pindah(evt, TKd, BtnAll);
    }
}//GEN-LAST:event_BtnCari1KeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            File g = new File("file.css");
            BufferedWriter bg = new BufferedWriter(new FileWriter(g));
            bg.write(".isi td{border-right: 1px solid #edf2e8;font: 16px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
            bg.close();

            File f = new File("resumemedis.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            switch (TabRawat.getSelectedIndex()) {
                case 0:
                    bw.write(LoadHTML.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='30%'>"
                            + "<img src='setting/logo.jpg' height=60px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='45%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "<br></font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 1:
                    bw.write(LoadHTML2.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 2:
                    bw.write(LoadHTML3.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 3:
                    bw.write(LoadHTML4.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 4:
                    bw.write(LoadHTML5.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 5:
                    bw.write(LoadHTML13.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>RINGKASAN PULANG PASIEN RAWAT INAP<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;    
                    
                case 6:
                    bw.write(LoadHTML6.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 7:
                    bw.write(LoadHTML7.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 8:
                    bw.write(LoadHTML8.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 9:
                    bw.write(LoadHTML9.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 10:
                    bw.write(LoadHTML10.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 11:
                    bw.write(LoadHTML11.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>DATA RESUME MEDIS / RIWAYAT PERAWATAN PASIEN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;
                case 12:
                    bw.write(LoadHTML12.getText().replaceAll("<head>", "<head><link href=\"file.css\" rel=\"stylesheet\" type=\"text/css\" />"
                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                            + "<tr class='isi2'>"
                            + "<td width='25%'>"
                            + "<img src='setting/logo.jpg' height=55px align='right'>"
                            + "</td>"
                            + "<td valign='top' align='center' width='50%'>"
                            + "<font size='3' face='Tahoma'><b>" + akses.getnamars() + "</b></font><br>"
                            + "<font size='3' face='Tahoma'>" + akses.getalamatrs() + ", " + akses.getkabupatenrs() + ", " + akses.getpropinsirs() + "<br>"
                            + akses.getkontakrs() + "e-Mail : " + akses.getemailrs() + "</font>"
                            + "</td>"
                            + "<td width='40%'>"
                            + "</td>"
                            + "</tr>"
                            + "<tr class='isi2'>"
                            + "<td colspan=3>"
                            + "<hr><center><font size='3' face='Tahoma'>PENILAIAN AWAL KEPERAWATAN (ASSESMEN) PASIEN RAWAT JALAN<br></font>"
                            + "<font size='3' face='Tahoma'>PERIODE TGL. " + Tgl1.getSelectedItem() + " S.D. " + Tgl2.getSelectedItem() + "<br><br></font></center>"
                            + "</td>"
                            + "</tr>"
                            + "</table>")
                    );
                    bw.close();
                    break;    
                    
                default:
                    break;
            }
            Desktop.getDesktop().browse(f.toURI());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnKeluar, Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        Valid.pindah(evt, Tgl1, NoRM);
    }//GEN-LAST:event_Tgl2KeyPressed

    private void ChkTanggalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkTanggalItemStateChanged
        TabRawatMouseClicked(null);
    }//GEN-LAST:event_ChkTanggalItemStateChanged

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        switch (TabRawat.getSelectedIndex()) {
            case 0:
                tampil();
                break;
            case 1:
                tampil2();
                break;
            case 2:
                tampil3();
                break;
            case 3:
//                tampil4();
                tampilRalan();
                break;
            case 4:
//                tampil5();
                tampilRanap();
                break;
            case 5:
                tampilRingkasanPulangRanap();
                break;
            case 6:
                tampil6();
                break;
            case 7:
                tampil7();
                break;
            case 8:
                tampil8();
                break;
            case 9:
                tampil9();
                break;
            case 10:
                tampil10();
                break;
            case 11:
                tampil11();
                break;
            case 12:
                tampilAssesmenRalan();
                break;                
            default:
                break;
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgResumePerawatan dialog = new DlgResumePerawatan(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCari1;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.CekBox ChkTanggal;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML10;
    private widget.editorpane LoadHTML11;
    private widget.editorpane LoadHTML12;
    private widget.editorpane LoadHTML13;
    private widget.editorpane LoadHTML2;
    private widget.editorpane LoadHTML3;
    private widget.editorpane LoadHTML4;
    private widget.editorpane LoadHTML5;
    private widget.editorpane LoadHTML6;
    private widget.editorpane LoadHTML7;
    private widget.editorpane LoadHTML8;
    private widget.editorpane LoadHTML9;
    private widget.TextBox NoRM;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll12;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll7;
    private widget.ScrollPane Scroll8;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TKd;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame10;
    private widget.InternalFrame internalFrame11;
    private widget.InternalFrame internalFrame12;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame14;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi4;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='ranap','Rawat Inap','Rawat Jalan') status_lanjut,"
                                    + "penjab.png_jawab, reg_periksa.kd_poli from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select a.no_reg, a.no_rawat,a.tgl_registrasi,a.jam_reg,a.kd_dokter,a.nm_dokter,"
                                    + "a.nm_poli,a.p_jawab,a.almt_pj,a.hubunganpj,a.biaya_reg,if(a.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,a.png_jawab, a.kd_poli from "
                                    + "(select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,"
                                    + "penjab.png_jawab, eg_periksa.kd_poli from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ORDER BY reg_periksa.tgl_registrasi DESC LIMIT 3)"
                                    + "as a ORDER BY a.tgl_registrasi").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan catatan diagnosa
//                            try {
//                                rsDiag = koneksi.prepareStatement(
//                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "
//                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
//                                if (rsDiag.next()) {
//                                    htmlContent.append(
//                                            "<tr class='isi'>"
//                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Diag.</td>"
//                                            + "<td valign='top' width='1%' align='center'>:</td>"
//                                            + "<td valign='top' width='79%'>" + rsDiag.getString("diagnosa") + "</td>"
//                                            + "</tr>");
//                                }
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : " + e);
//                            } finally {
//                                if (rsDiag != null) {
//                                    rsDiag.close();
//                                }
//                            }

                            //menampilkan rencana follow up dokter
                            try {
                                rsDiag = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }
                            
                            //menampilkan rencana follow up perawat/bidan
                            try {
                                rsDiag1 = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                rsObat = koneksi.prepareStatement(
                                        "Select nama_obat,status from catatan_resep "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='4%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='86%' valign='top' bgcolor='#f8fdf3'>Nama Obat</td><td width='5' valign='top' bgcolor='#f8fdf3'>Status</td></tr>"
                                    );
                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status "
                                        + "from diagnosa_pasien inner join penyakit "
                                        + "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                                        + "where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diag. ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Penyakit</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                                        + "from prosedur_pasien inner join icd9 "
                                        + "on prosedur_pasien.kode=icd9.kode "
                                        + "where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindkn. ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Tindakan</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"
                                        + "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                                        + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, "
                                        + "ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan,ifnull(pemeriksaan_ralan.terapi,'-') terapi, "
                                        + "ifnull(pemeriksaan_ralan.spo2,'-') spo2 from pemeriksaan_ralan where "
                                        + "pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"                                            
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>" 
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>" 
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan pemeriksaan THT
                            if (rs2.getString("kd_poli").equals("THT")) {
                                try {
                                    rsTHT = koneksi.prepareStatement(
                                            "Select ifnull(nama_pemeriksaan,'-') namanya, ifnull(hasil_pemeriksaan,'-') hasilnya "
                                            + "from pemeriksaan_tht where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                    if (rsTHT.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Tindakan Poliklinik THT</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>Nama Pemeriksaan : <span style='font-weight:bold'>" + rsTHT.getString("namanya").toUpperCase().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</span>"
                                                + "<br/><br/><span style='font-weight:bold'>Hasil Pemeriksaan : </span><br/>" + rsTHT.getString("hasilnya").replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsTHT != null) {
                                        rsTHT.close();
                                    }
                                }
                            }
                            
                            //menampilkan rujukan internal poliklinik
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT ifnull(pl.nm_poli,'-') ke_poli, ifnull(DATE_FORMAT(ri.tgl_rencana_dirujuk,'%d-%m-%Y'),'-') tgl_dirujuk, "
                                        + "ifnull(ri.keterangan,'-') keterangan, ifnull(ri.keterangan_balasan,'-') jwbn, ifnull(d.nm_dokter,'') drMenjawab FROM rujukan_internal_poli ri "
                                        + "INNER JOIN poliklinik pl on pl.kd_poli=ri.kd_poli_pembalas left join dokter d on d.kd_dokter=ri.kd_dokter_pembalas "
                                        + "WHERE ri.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirujuk Internal Ke</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik/Inst.</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Renc. Dirujuk</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Isi/Pesan Rujukan</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Balasan/Jawaban Rujukan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"                                                
                                                + "<td valign='top'>" + rs3.getString("ke_poli") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_dirujuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jwbn").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br>Ttd.<br>" + rs3.getString("drMenjawab") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,"
                                        + "pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, "
                                        + "pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa,"
                                        + "ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi, "
                                        + "ifnull(pemeriksaan_ralan_petugas.spo2,'-') spo2 from pemeriksaan_ralan_petugas where "
                                        + "pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"                                            
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>" 
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"     
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"  
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan asuhan awal keperawatan rawat jalan 
                            try {
                                rs3 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','Laki-Laki','Perempuan') jk, "
                                        + "date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.agama, bp.nama_bahasa, pa.nama_cacat_fisik nama_cacat, "
                                        + "date_format(pa.tanggal,'%d %M %Y, Jam : %h:%i %p') tanggal, pa.informasi, pa.td, pa.nadi, pa.rr, pa.suhu, pa.bb, pa.tb, pa.nadi, pa.rr, pa.suhu, pa.gcs, pa.bb, pa.tb, "
                                        + "pa.bmi, pa.keluhan_utama, pa.rpd, pa.rpk, pa.rpo, pa.alergi, pa.alat_bantu, pa.ket_bantu, pa.prothesa, "
                                        + "pa.ket_pro, pa.adl, pa.status_psiko, pa.ket_psiko, pa.hub_keluarga, pa.tinggal_dengan, pa.ket_tinggal, "
                                        + "pa.ekonomi, pa.edukasi, pa.ket_edukasi, pa.berjalan_a, pa.berjalan_b, pa.berjalan_c, pa.hasil, pa.lapor, "
                                        + "pa.ket_lapor, pa.sg1, pa.nilai1,  pa.sg2, pa.nilai2, pa.total_hasil, pa.nyeri, pa.provokes, pa.ket_provokes, "
                                        + "pa.quality, pa.ket_quality, pa.lokasi, pa.menyebar, pa.skala_nyeri, pa.durasi, pa.nyeri_hilang, "
                                        + "pa.ket_nyeri, pa.pada_dokter, pa.ket_dokter, pa.rencana, pa.nip, pt.nama, pa.budaya, pa.ket_budaya FROM reg_periksa rp  "
                                        + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN penilaian_awal_keperawatan_ralan pa ON rp.no_rawat = pa.no_rawat "
                                        + "INNER JOIN petugas pt ON pa.nip = pt.nip INNER JOIN bahasa_pasien bp ON bp.id = p.bahasa_pasien "
                                        + "WHERE pa.no_rawat = '"+rs2.getString("no_rawat")+"'").executeQuery();
                                if(rs3.next()){
                                    htmlContent.append(
                                      "<tr class='isi'>"+ 
                                        "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penilaian Awal Keperawatan Rawat Jalan</td>"+
                                        "<td valign='top' width='1%' align='center'>:</td>"+
                                        "<td valign='top' width='79%'>"+
                                          "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while(rs3.next()){
                                        htmlContent.append(
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "YANG MELAKUKAN PENGKAJIAN"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='20%' border='0'>Tanggal : "+rs3.getString("tanggal")+"</td>"+
                                                          "<td width='33%' border='0'>Petugas : NIP./NR. "+rs3.getString("nip")+" Nama : "+rs3.getString("nama")+"</td>"+
                                                          "<td width='20%' border='0'>Informasi didapat dari : "+rs3.getString("informasi")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "I. KEADAAN UMUM"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='20%' border='0'>TD : "+rs3.getString("td")+" mmHg</td>"+
                                                          "<td width='20%' border='0'>Nadi : "+rs3.getString("nadi")+" x/menit</td>"+
                                                          "<td width='20%' border='0'>RR : "+rs3.getString("rr")+" x/menit</td>"+
                                                          "<td width='20%' border='0'>Suhu : "+rs3.getString("suhu")+" °C</td>"+
                                                          "<td width='20%' border='0'>GCS(E,V,M) : "+rs3.getString("gcs")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "II. STATUS NUTRISI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='33%' border='0'>BB : "+rs3.getString("bb")+" Kg</td>"+
                                                          "<td width='33%' border='0'>TB : "+rs3.getString("tb")+" Cm</td>"+
                                                          "<td width='33%' border='0'>BMI : "+rs3.getString("bmi")+" Kg/m²</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "III. RIWAYAT KESEHATAN"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' colspan='2'>Keluhan Utama : "+rs3.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%'>Riwayat Penyakit Dahulu : "+rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                          "<td width='50%'>Riwayat Alergi : "+rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+" Cm</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%'>Riwayat Penyakit Keluarga : "+rs3.getString("rpk").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                          "<td width='50%'>Riwayat Pengobatan : "+rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+" Cm</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "IV. FUNGSIONAL"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Alat Bantu : "+rs3.getString("alat_bantu")+" "+rs3.getString("ket_bantu")+"</td>"+
                                                          "<td width='50%' border='0'>Prothesa : "+rs3.getString("prothesa")+" "+rs3.getString("ket_pro")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Cacat Fisik : "+rs3.getString("nama_cacat")+"</td>"+
                                                          "<td width='50%' border='0'>Aktivitas Kehidupan Sehari-hari ( ADL ) : "+rs3.getString("adl")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "V. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Status Psikologis : "+rs3.getString("status_psiko")+" "+rs3.getString("ket_psiko")+"</td>"+
                                                          "<td width='50%' border='0'>Bahasa yang digunakan sehari-hari : "+rs3.getString("nama_bahasa")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0' colspan='2'>Status Sosial dan ekonomi :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;a. Hubungan pasien dengan anggota keluarga</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("hub_keluarga")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;b. Tinggal dengan</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("tinggal_dengan")+" "+rs3.getString("ket_tinggal")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;c. Ekonomi</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("ekonomi")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("budaya")+" "+rs3.getString("ket_budaya")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Agama : "+rs3.getString("agama")+"</td>"+
                                                          "<td width='50%' border='0'>Edukasi diberikan kepada : "+rs3.getString("edukasi")+" "+rs3.getString("ket_edukasi")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VI. PENILAIAN RESIKO JATUH"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td colpsan='2' border='0'>a. Cara Berjalan :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;1. Tidak seimbang / sempoyongan / limbung</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_a")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;2. Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_b")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>b. Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja / benda lain sebagai penopang</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_c")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td colspan='2' border='0'>Hasil : "+rs3.getString("hasil")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dilaporkan kepada dokter ? "+rs3.getString("lapor")+" Jam dilaporkan : "+rs3.getString("ket_lapor")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VII. SKRINING GIZI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'>No</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70%'>Parameter</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='25%' colspan='2'>Nilai</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top'>1</td>"+
                                                          "<td valign='top'>Apakah ada penurunan berat badanyang tidak diinginkan selama enam bulan terakhir ?</td>"+
                                                          "<td valign='top' align='center' width='20%'>"+rs3.getString("sg1")+"</td>"+
                                                          "<td valign='top' align='right' width='5%'>"+rs3.getString("nilai1")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top'>2</td>"+
                                                          "<td valign='top'>Apakah nafsu makan berkurang karena tidak nafsu makan ?</td>"+
                                                          "<td valign='top' align='center' width='20%'>"+rs3.getString("sg2")+"</td>"+
                                                          "<td valign='top' align='right' width='5%'>"+rs3.getString("nilai2")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top' align='right' colspan='2'>Total Skor</td>"+
                                                          "<td valign='top' align='right' colspan='2'>"+rs3.getString("total_hasil")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VIII. PENILAIAN TINGKAT NYERI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Tingkat Nyeri : "+rs3.getString("nyeri")+", Waktu / Durasi : "+rs3.getString("durasi")+" Menit</td>"+
                                                          "<td width='50%' border='0'>Penyebab : "+rs3.getString("provokes")+" "+rs3.getString("ket_provokes")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Kualitas : "+rs3.getString("quality")+" "+rs3.getString("ket_quality")+"</td>"+
                                                          "<td width='50%' border='0'>Severity : Skala Nyeri "+rs3.getString("skala_nyeri")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' colspan='0' border='0'>Wilayah :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;Lokasi : "+rs3.getString("lokasi")+"</td>"+
                                                          "<td width='50%' border='0'>Menyebar : "+rs3.getString("menyebar")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Nyeri hilang bila : "+rs3.getString("nyeri_hilang")+" "+rs3.getString("ket_nyeri")+"</td>"+
                                                          "<td width='50%' border='0'>Diberitahukan pada dokter ? "+rs3.getString("pada_dokter")+", Jam : "+rs3.getString("ket_dokter")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'>MASALAH KEPERAWATAN :</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'>RENCANA KEPERAWATAN :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                           "<td>");
                                        try {
                                            rs4 = koneksi.prepareStatement("SELECT mk.kode_masalah, mk.nama_masalah FROM master_masalah_keperawatan mk  "
                                                    + "INNER JOIN penilaian_awal_keperawatan_ralan_masalah pam ON pam.kode_masalah = mk.kode_masalah "
                                                    + "WHERE pam.no_rawat = '"+rs2.getString("no_rawat")+"' ORDER BY mk.kode_masalah").executeQuery();
                                            while(rs4.next()){
                                                htmlContent.append(rs4.getString("nama_masalah")+"<br>");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs4!=null){
                                                rs4.close();
                                            }
                                        }
                                        htmlContent.append("</td>"+
                                                           "<td>"+rs3.getString("rencana").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"
                                        );                                        
                                        w++;
                                    }
                                    htmlContent.append(
                                          "</table>"+
                                        "</td>"+
                                      "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : "+e);
                            } finally{
                                if(rs3!=null){
                                    rs3.close();
                                }
                            }                            

                            //menampilkan riwayat pemeriksaan ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,"
                                        + "pemeriksaan_ranap.tinggi,pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan,"
                                        + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,date_format(pemeriksaan_ranap.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(pemeriksaan_ranap.jam_rawat,'%h:%i %p') jam_rawat from pemeriksaan_ranap where "
                                        + "pemeriksaan_ranap.no_rawat='" + rs2.getString("no_rawat") + "' order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemriksan. R. Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement(
                                        "SELECT no_lab FROM lis_reg "
                                        + "WHERE no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, lhp.satuan, lhp.flag_kode FROM lis_reg lr "
                                                                + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' "
                                                                + "GROUP BY lhp.pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }
                            
                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"                                            
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ralan
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ralan' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG Rawat Jalan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ranap
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ranap' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG Rawat Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_dr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_dr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "
                                        + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "
                                        + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_dr.tgl_perawatan, rawat_inap_dr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_pr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_pr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "petugas.nama,rawat_inap_pr.biaya_rawat "
                                        + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "
                                        + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_pr.tgl_perawatan, rawat_inap_pr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis dan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_drpr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(rawat_inap_drpr.jam_rawat,'%h:%i %p') jam_rawat,rawat_inap_drpr.kd_jenis_prw,"
                                        + "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "
                                        + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "
                                        + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "
                                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat ").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //kamar inap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select kamar_inap.kd_kamar,bangsal.nm_bangsal,date_format(kamar_inap.tgl_masuk,'%d-%m-%Y') tgl_masuk, "
                                        + "date_format(kamar_inap.tgl_keluar,'%d-%m-%Y') tgl_keluar, "
                                        + "kamar_inap.stts_pulang,kamar_inap.lama,date_format(kamar_inap.jam_masuk,'%h:%i %p') jam_masuk,"
                                        + "date_format(kamar_inap.jam_keluar,'%h:%i %p') jam_keluar,"
                                        + "kamar_inap.ttl_biaya from kamar_inap inner join bangsal inner join kamar "
                                        + "on kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  "
                                        + "where kamar_inap.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Penggunaan Kamar</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal Masuk</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggak Keluar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Lama Inap</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Kamar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Status</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_masuk") + " " + rs3.getString("jam_masuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_keluar") + " " + rs3.getString("jam_keluar") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("lama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_kamar") + ", " + rs3.getString("nm_bangsal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("stts_pulang") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("ttl_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,"
                                        + "operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, "
                                        + "operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,"
                                        + "operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, "
                                        + "operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, "
                                        + "operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, "
                                        + "operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, "
                                        + "operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,"
                                        + "operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,"
                                        + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
                                        + "(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+"
                                        + "operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+"
                                        + "operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+"
                                        + "operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+"
                                        + "operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
                                        + "operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi "
                                        + "on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,"
                                        + "periksa_radiologi.kd_jenis_prw, "
                                        + "jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter "
                                        + "from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter "
                                        + "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter "
                                        + "and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total "
                                        + "FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat "
                                        + "INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL "
                                        + "SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total "
                                        + "FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw "
                                        + "LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(beri_obat_operasi.tanggal,'%d-%m-%Y %h:%i %p') tanggal,beri_obat_operasi.kd_obat,"
                                        + "beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, "
                                        + "(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil2() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d %M %Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d %M %Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan diagnosa penyakit                            
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status "
                                        + "from diagnosa_pasien inner join penyakit "
                                        + "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                                        + "where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diag. ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td><td valign='top' width='5%' bgcolor='#f8fdf3'>Kode</td><td valign='top' width='72%' bgcolor='#f8fdf3'>Nama Penyakit</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td><td valign='top'>" + rs3.getString("kd_penyakit") + "</td><td valign='top'>" + rs3.getString("nm_penyakit") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML2.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil3() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d %M %Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d %M %Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan prosedur tindakan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                                        + "from prosedur_pasien inner join icd9 "
                                        + "on prosedur_pasien.kode=icd9.kode "
                                        + "where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindakan ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td><td valign='top' width='5%' bgcolor='#f8fdf3'>Kode</td><td valign='top' width='72%' bgcolor='#f8fdf3'>Nama Tindakan</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td><td valign='top'>" + rs3.getString("kode") + "</td><td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML3.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil4() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_dokter") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alamat P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("almt_pj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hub. P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("hubunganpj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + "</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );
                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML4.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil5() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dokter</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_dokter") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Alamat P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("almt_pj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hub. P.J.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("hubunganpj") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + "</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //tindakan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_dr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_dr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "
                                        + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "
                                        + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where "
                                        + "rawat_inap_dr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_dr.tgl_perawatan,rawat_inap_dr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_pr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_pr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "petugas.nama,rawat_inap_pr.biaya_rawat "
                                        + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "
                                        + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by rawat_inap_pr.tgl_perawatan,rawat_inap_pr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis dan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_drpr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_drpr.jam_rawat,'%h:%i %p') jam_rawat,rawat_inap_drpr.kd_jenis_prw,"
                                        + "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "
                                        + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "
                                        + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "
                                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML5.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil6() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,"
                                        + "operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, "
                                        + "operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,"
                                        + "operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, "
                                        + "operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, "
                                        + "operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, "
                                        + "operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, "
                                        + "operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,"
                                        + "operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,"
                                        + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
                                        + "(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+"
                                        + "operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+"
                                        + "operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+"
                                        + "operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+"
                                        + "operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
                                        + "operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi "
                                        + "on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "' order by operasi.tgl_operasi").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML6.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil7() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"                                            
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //biaya perawatan
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //tindakan pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,periksa_radiologi.kd_jenis_prw, "
                                        + "jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter "
                                        + "from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter "
                                        + "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter "
                                        + "and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "order by periksa_radiologi.tgl_periksa,periksa_radiologi.jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "' order by tgl_periksa,jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML7.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil8() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;
                            
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement(
                                        "SELECT no_lab FROM lis_reg "
                                        + "WHERE no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, lhp.satuan, lhp.flag_kode FROM lis_reg lr "
                                                                + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' "
                                                                + "GROUP BY lhp.pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //tindakan pemeriksaan laborat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total "
                                        + "FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat "
                                        + "INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL "
                                        + "SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total "
                                        + "FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw "
                                        + "LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML8.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil9() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //pemberian obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "' order by detail_pemberian_obat.tgl_perawatan,detail_pemberian_obat.jam").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, "
                                        + "(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML9.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil10() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, "
                        + "concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,"
                        + "pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(beri_obat_operasi.tanggal,'%d-%m-%Y %h:%i %p') tanggal,beri_obat_operasi.kd_obat,beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "' order by beri_obat_operasi.tanggal").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML10.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampil11() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                            );

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML11.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    public void tampilAssesmenRalan() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try{
            StringBuilder htmlContent = new StringBuilder();
            try {
                rsasesmenRJ = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE "
                        + "p.no_rkm_medis = '" + NoRM.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();
                x=1;
                while(rsasesmenRJ.next()){   
                    htmlContent.append(
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>No. RM</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("no_rkm_medis")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Nama Pasien</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("nm_pasien")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Alamat</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("alamat")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Umur</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("umur")+" ("+rsasesmenRJ.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")+")</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Tempat & Tanggal Lahir</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("tmp_lahir")+", "+rsasesmenRJ.getString("tgl_lahir")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Ibu Kandung</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("nm_ibu")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Golongan Darah</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("gol_darah")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Status Nikah</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("stts_nikah")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Agama</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("agama")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Pendidikan Terakhir</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("pnd")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Pertama Daftar</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rsasesmenRJ.getString("tgl_daftar")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Riwayat Perawatan</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'></td>"+
                        "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rsasesmenRJ.getString("no_rkm_medis") + "' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_reg, rp.no_rawat, date_format(rp.tgl_registrasi,'%d %M %Y') tgl_registrasi, date_format(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "rp.kd_dokter, d.nm_dokter, pl.nm_poli, rp.p_jawab, rp.almt_pj, rp.hubunganpj, rp.biaya_reg, if(rp.status_lanjut='Ralan','Rawat Jalan','Rawat Inap') status_lanjut, "
                                    + "pj.png_jawab FROM reg_periksa rp INNER JOIN dokter d ON rp.kd_dokter = d.kd_dokter INNER JOIN poliklinik pl ON rp.kd_poli = pl.kd_poli "
                                    + "INNER JOIN penjab pj ON rp.kd_pj = pj.kd_pj WHERE rp.stts <> 'Batal' AND rp.no_rkm_medis = '" + rsasesmenRJ.getString("no_rkm_medis") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg desc limit 3").executeQuery();
                        }
                            
                        urut=1;
                        while(rs2.next()){      
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );                        
                            urut++;
                            
                            //menampilkan asuhan awal keperawatan rawat jalan 
                            try {
                                rs3 = koneksi.prepareStatement("SELECT rp.no_rawat, p.no_rkm_medis, p.nm_pasien, IF (p.jk = 'L','Laki-Laki','Perempuan') jk, "
                                        + "date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.agama, bp.nama_bahasa, pa.nama_cacat_fisik nama_cacat, "
                                        + "date_format(pa.tanggal,'%d %M %Y, Jam : %h:%i %p') tanggal, pa.informasi, pa.td, pa.nadi, pa.rr, pa.suhu, pa.bb, pa.tb, pa.nadi, pa.rr, pa.suhu, pa.gcs, pa.bb, pa.tb, "
                                        + "pa.bmi, pa.keluhan_utama, pa.rpd, pa.rpk, pa.rpo, pa.alergi, pa.alat_bantu, pa.ket_bantu, pa.prothesa, "
                                        + "pa.ket_pro, pa.adl, pa.status_psiko, pa.ket_psiko, pa.hub_keluarga, pa.tinggal_dengan, pa.ket_tinggal, "
                                        + "pa.ekonomi, pa.edukasi, pa.ket_edukasi, pa.berjalan_a, pa.berjalan_b, pa.berjalan_c, pa.hasil, pa.lapor, "
                                        + "pa.ket_lapor, pa.sg1, pa.nilai1,  pa.sg2, pa.nilai2, pa.total_hasil, pa.nyeri, pa.provokes, pa.ket_provokes, "
                                        + "pa.quality, pa.ket_quality, pa.lokasi, pa.menyebar, pa.skala_nyeri, pa.durasi, pa.nyeri_hilang, "
                                        + "pa.ket_nyeri, pa.pada_dokter, pa.ket_dokter, pa.rencana, pa.nip, pt.nama, pa.budaya, pa.ket_budaya FROM reg_periksa rp  "
                                        + "INNER JOIN pasien p ON rp.no_rkm_medis = p.no_rkm_medis INNER JOIN penilaian_awal_keperawatan_ralan pa ON rp.no_rawat = pa.no_rawat "
                                        + "INNER JOIN petugas pt ON pa.nip = pt.nip INNER JOIN bahasa_pasien bp ON bp.id = p.bahasa_pasien "
                                        + "WHERE pa.no_rawat = '"+rs2.getString("no_rawat")+"'").executeQuery();
                                if(rs3.next()){
                                    htmlContent.append(
                                      "<tr class='isi'>"+ 
                                        "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Penilaian Awal Keperawatan Rawat Jalan</td>"+
                                        "<td valign='top' width='1%' align='center'>:</td>"+
                                        "<td valign='top' width='79%'>"+
                                          "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while(rs3.next()){
                                        htmlContent.append(
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "YANG MELAKUKAN PENGKAJIAN"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='20%' border='0'>Tanggal : "+rs3.getString("tanggal")+"</td>"+
                                                          "<td width='33%' border='0'>Petugas : NIP./NR. "+rs3.getString("nip")+" Nama : "+rs3.getString("nama")+"</td>"+
                                                          "<td width='20%' border='0'>Informasi didapat dari : "+rs3.getString("informasi")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "I. KEADAAN UMUM"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='20%' border='0'>TD : "+rs3.getString("td")+" mmHg</td>"+
                                                          "<td width='20%' border='0'>Nadi : "+rs3.getString("nadi")+" x/menit</td>"+
                                                          "<td width='20%' border='0'>RR : "+rs3.getString("rr")+" x/menit</td>"+
                                                          "<td width='20%' border='0'>Suhu : "+rs3.getString("suhu")+" °C</td>"+
                                                          "<td width='20%' border='0'>GCS(E,V,M) : "+rs3.getString("gcs")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "II. STATUS NUTRISI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='33%' border='0'>BB : "+rs3.getString("bb")+" Kg</td>"+
                                                          "<td width='33%' border='0'>TB : "+rs3.getString("tb")+" Cm</td>"+
                                                          "<td width='33%' border='0'>BMI : "+rs3.getString("bmi")+" Kg/m²</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "III. RIWAYAT KESEHATAN"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' colspan='2'>Keluhan Utama : "+rs3.getString("keluhan_utama").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%'>Riwayat Penyakit Dahulu : "+rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                          "<td width='50%'>Riwayat Alergi : "+rs3.getString("alergi")+" Cm</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%'>Riwayat Penyakit Keluarga : "+rs3.getString("rpk").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                          "<td width='50%'>Riwayat Pengobatan : "+rs3.getString("rpd").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+" Cm</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "IV. FUNGSIONAL"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Alat Bantu : "+rs3.getString("alat_bantu")+" "+rs3.getString("ket_bantu")+"</td>"+
                                                          "<td width='50%' border='0'>Prothesa : "+rs3.getString("prothesa")+" "+rs3.getString("ket_pro")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Cacat Fisik : "+rs3.getString("nama_cacat")+"</td>"+
                                                          "<td width='50%' border='0'>Aktivitas Kehidupan Sehari-hari ( ADL ) : "+rs3.getString("adl")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "V. RIWAYAT PSIKO-SOSIAL, SPIRITUAL DAN BUDAYA"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Status Psikologis : "+rs3.getString("status_psiko")+" "+rs3.getString("ket_psiko")+"</td>"+
                                                          "<td width='50%' border='0'>Bahasa yang digunakan sehari-hari : "+rs3.getString("nama_bahasa")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0' colspan='2'>Status Sosial dan ekonomi :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;a. Hubungan pasien dengan anggota keluarga</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("hub_keluarga")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;b. Tinggal dengan</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("tinggal_dengan")+" "+rs3.getString("ket_tinggal")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;c. Ekonomi</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("ekonomi")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Kepercayaan / Budaya / Nilai-nilai khusus yang perlu diperhatikan</td>"+
                                                          "<td width='50%' border='0'>: "+rs3.getString("budaya")+" "+rs3.getString("ket_budaya")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Agama : "+rs3.getString("agama")+"</td>"+
                                                          "<td width='50%' border='0'>Edukasi diberikan kepada : "+rs3.getString("edukasi")+" "+rs3.getString("ket_edukasi")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VI. PENILAIAN RESIKO JATUH"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td colpsan='2' border='0'>a. Cara Berjalan :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;1. Tidak seimbang / sempoyongan / limbung</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_a")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;2. Jalan dengan menggunakan alat bantu (kruk, tripot, kursi roda, orang lain)</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_b")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='75%' border='0'>b. Menopang saat akan duduk, tampak memegang pinggiran kursi atau meja / benda lain sebagai penopang</td>"+
                                                          "<td width='25%' border='0'>: "+rs3.getString("berjalan_c")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td colspan='2' border='0'>Hasil : "+rs3.getString("hasil")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dilaporkan kepada dokter ? "+rs3.getString("lapor")+" Jam dilaporkan : "+rs3.getString("ket_lapor")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VII. SKRINING GIZI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='5%'>No</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='70%'>Parameter</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='25%' colspan='2'>Nilai</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top'>1</td>"+
                                                          "<td valign='top'>Apakah ada penurunan berat badanyang tidak diinginkan selama enam bulan terakhir ?</td>"+
                                                          "<td valign='top' align='center' width='20%'>"+rs3.getString("sg1")+"</td>"+
                                                          "<td valign='top' align='right' width='5%'>"+rs3.getString("nilai1")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top'>2</td>"+
                                                          "<td valign='top'>Apakah nafsu makan berkurang karena tidak nafsu makan ?</td>"+
                                                          "<td valign='top' align='center' width='20%'>"+rs3.getString("sg2")+"</td>"+
                                                          "<td valign='top' align='right' width='5%'>"+rs3.getString("nilai2")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td valign='top' align='right' colspan='2'>Total Skor</td>"+
                                                          "<td valign='top' align='right' colspan='2'>"+rs3.getString("total_hasil")+"&nbsp;&nbsp;</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "VIII. PENILAIAN TINGKAT NYERI"+  
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Tingkat Nyeri : "+rs3.getString("nyeri")+", Waktu / Durasi : "+rs3.getString("durasi")+" Menit</td>"+
                                                          "<td width='50%' border='0'>Penyebab : "+rs3.getString("provokes")+" "+rs3.getString("ket_provokes")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Kualitas : "+rs3.getString("quality")+" "+rs3.getString("ket_quality")+"</td>"+
                                                          "<td width='50%' border='0'>Severity : Skala Nyeri "+rs3.getString("skala_nyeri")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' colspan='0' border='0'>Wilayah :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>&nbsp;&nbsp;&nbsp;&nbsp;Lokasi : "+rs3.getString("lokasi")+"</td>"+
                                                          "<td width='50%' border='0'>Menyebar : "+rs3.getString("menyebar")+"</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                          "<td width='50%' border='0'>Nyeri hilang bila : "+rs3.getString("nyeri_hilang")+" "+rs3.getString("ket_nyeri")+"</td>"+
                                                          "<td width='50%' border='0'>Diberitahukan pada dokter ? "+rs3.getString("pada_dokter")+", Jam : "+rs3.getString("ket_dokter")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"+
                                             "<tr>"+
                                                "<td valign='top'>"+
                                                   "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0px' class='tbl_form'>"+
                                                      "<tr>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'>MASALAH KEPERAWATAN :</td>"+
                                                           "<td valign='middle' bgcolor='#FFFAF8' align='center' width='50%'>RENCANA KEPERAWATAN :</td>"+
                                                      "</tr>"+
                                                      "<tr>"+
                                                           "<td>");
                                        try {
                                            rs4 = koneksi.prepareStatement("SELECT mk.kode_masalah, mk.nama_masalah FROM master_masalah_keperawatan mk  "
                                                    + "INNER JOIN penilaian_awal_keperawatan_ralan_masalah pam ON pam.kode_masalah = mk.kode_masalah "
                                                    + "WHERE pam.no_rawat = '"+rs2.getString("no_rawat")+"' ORDER BY mk.kode_masalah").executeQuery();
                                            while(rs4.next()){
                                                htmlContent.append(rs4.getString("nama_masalah")+"<br>");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Notif : "+e);
                                        } finally{
                                            if(rs4!=null){
                                                rs4.close();
                                            }
                                        }
                                        htmlContent.append("</td>"+
                                                           "<td>"+rs3.getString("rencana").replaceAll("(\r\n|\r|\n|\n\r)","<br>")+"</td>"+
                                                      "</tr>"+
                                                   "</table>"+
                                                "</td>"+
                                             "</tr>"
                                        );                                        
                                        w++;
                                    }
                                    htmlContent.append(
                                          "</table>"+
                                        "</td>"+
                                      "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : "+e);
                            } finally{
                                if(rs3!=null){
                                    rs3.close();
                                }
                            }
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }    
                    } catch (Exception e) {
                        System.out.println("Notifikasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML12.setText(
                    "<html>"+
                      "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            } finally{
                if(rsasesmenRJ!=null){
                    rsasesmenRJ.close();
                }
            }
            
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

    public void tampilRalan() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='ranap','Rawat Inap','Rawat Jalan') status_lanjut,"
                                    + "penjab.png_jawab, reg_periksa.kd_poli from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.status_lanjut = 'ralan' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select a.no_reg, a.no_rawat,a.tgl_registrasi,a.jam_reg,a.kd_dokter,a.nm_dokter,"
                                    + "a.nm_poli,a.p_jawab,a.almt_pj,a.hubunganpj,a.biaya_reg,if(a.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,a.png_jawab, a.kd_poli from "
                                    + "(select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,IF(reg_periksa.kd_poli='IRM',CONCAT(poliklinik.nm_poli,' - ',IFNULL(data_rehab_medik.jns_rehabmedik,'FISIOTERAPI')),poliklinik.nm_poli) nm_poli,"
                                    + "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,"
                                    + "penjab.png_jawab, reg_periksa.kd_poli from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli LEFT JOIN data_rehab_medik ON data_rehab_medik.no_rawat = reg_periksa.no_rawat where stts<>'Batal' and "
                                    + "reg_periksa.status_lanjut = 'ralan' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ORDER BY reg_periksa.tgl_registrasi DESC LIMIT 3)"
                                    + "as a ORDER BY a.tgl_registrasi").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("png_jawab") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("status_lanjut") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                            );
                            urut++;

                            //menampilkan catatan diagnosa
//                            try {
//                                rsDiag = koneksi.prepareStatement(
//                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "
//                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
//                                if (rsDiag.next()) {
//                                    htmlContent.append(
//                                            "<tr class='isi'>"
//                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Diag.</td>"
//                                            + "<td valign='top' width='1%' align='center'>:</td>"
//                                            + "<td valign='top' width='79%'>" + rsDiag.getString("diagnosa") + "</td>"
//                                            + "</tr>");
//                                }
//                            } catch (Exception e) {
//                                System.out.println("Notifikasi : " + e);
//                            } finally {
//                                if (rsDiag != null) {
//                                    rsDiag.close();
//                                }
//                            }

                            //menampilkan rencana follow up dokter
                            try {
                                rsDiag = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }
                            
                            //menampilkan rencana follow up perawat/bidan
                            try {
                                rsDiag1 = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                rsObat = koneksi.prepareStatement(
                                        "Select nama_obat,status from catatan_resep "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='4%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='86%' valign='top' bgcolor='#f8fdf3'>Nama Obat</td><td width='5' valign='top' bgcolor='#f8fdf3'>Status</td></tr>"
                                    );
                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status "
                                        + "from diagnosa_pasien inner join penyakit "
                                        + "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                                        + "where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diag. ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Penyakit</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                                        + "from prosedur_pasien inner join icd9 "
                                        + "on prosedur_pasien.kode=icd9.kode "
                                        + "where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindkn. ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Tindakan</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"
                                        + "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                                        + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, "
                                        + "ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan.terapi,'-') terapi, "
                                        + "ifnull(pemeriksaan_ralan.spo2,'-') spo2 from pemeriksaan_ralan where "
                                        + "pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan pemeriksaan THT
                            if (rs2.getString("kd_poli").equals("THT")) {
                                try {
                                    rsTHT = koneksi.prepareStatement(
                                            "Select ifnull(nama_pemeriksaan,'-') namanya, ifnull(hasil_pemeriksaan,'-') hasilnya "
                                            + "from pemeriksaan_tht where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                    if (rsTHT.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Tindakan THT</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>Nama Pemeriksaan : <span style='font-weight:bold'>" + rsTHT.getString("namanya").toUpperCase().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</span>"
                                                + "<br/><br/><span style='font-weight:bold'>Hasil Pemeriksaan : </span><br/>" + rsTHT.getString("hasilnya").replaceAll("(\r\n|\r|\n|\n\r)", "<br/>") + "</td>"
                                                + "</tr>");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rsTHT != null) {
                                        rsTHT.close();
                                    }
                                }
                            }
                            
                            //menampilkan rujukan internal poliklinik
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT ifnull(pl.nm_poli,'-') ke_poli, ifnull(DATE_FORMAT(ri.tgl_rencana_dirujuk,'%d-%m-%Y'),'-') tgl_dirujuk, "
                                        + "ifnull(ri.keterangan,'-') keterangan, ifnull(ri.keterangan_balasan,'-') jwbn, ifnull(d.nm_dokter,'') drMenjawab FROM rujukan_internal_poli ri "
                                        + "INNER JOIN poliklinik pl on pl.kd_poli=ri.kd_poli_pembalas left join dokter d on d.kd_dokter=ri.kd_dokter_pembalas "
                                        + "WHERE ri.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dirujuk Internal Ke</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Poliklinik/Inst.</td>"
                                            + "<td valign='top' width='13%' bgcolor='#f8fdf3'>Renc. Dirujuk</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Isi/Pesan Rujukan</td>"
                                            + "<td valign='top' width='150%' bgcolor='#f8fdf3'>Balasan/Jawaban Rujukan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"                                                
                                                + "<td valign='top'>" + rs3.getString("ke_poli") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_dirujuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keterangan").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("jwbn").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "<br><br>Ttd.<br>" + rs3.getString("drMenjawab") + "</td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,"
                                        + "pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, "
                                        + "pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa,"
                                        + "ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, "
                                        + "ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi, ifnull(pemeriksaan_ralan_petugas.spo2,'-') spo2 from pemeriksaan_ralan_petugas where "
                                        + "pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>SPO2</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"                                          
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"       
                                                + "<td valign='top'>" + rs3.getString("spo2") + "</td>"      
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"        
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>" 
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,"
                                        + "pemeriksaan_ranap.tinggi,pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan,"
                                        + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,date_format(pemeriksaan_ranap.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(pemeriksaan_ranap.jam_rawat,'%h:%i %p') jam_rawat from pemeriksaan_ranap where "
                                        + "pemeriksaan_ranap.no_rawat='" + rs2.getString("no_rawat") + "' order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemriksan. R. Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement(
                                        "SELECT no_lab FROM lis_reg "
                                        + "WHERE no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, lhp.satuan, lhp.flag_kode FROM lis_reg lr "
                                                                + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' "
                                                                + "GROUP BY lhp.pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }
                            
                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                               
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ralan
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ralan' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_dr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_dr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "
                                        + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "
                                        + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_dr.tgl_perawatan, rawat_inap_dr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_pr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_pr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "petugas.nama,rawat_inap_pr.biaya_rawat "
                                        + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "
                                        + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_pr.tgl_perawatan, rawat_inap_pr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis dan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_drpr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(rawat_inap_drpr.jam_rawat,'%h:%i %p') jam_rawat,rawat_inap_drpr.kd_jenis_prw,"
                                        + "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "
                                        + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "
                                        + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "
                                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat ").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //kamar inap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select kamar_inap.kd_kamar,bangsal.nm_bangsal,date_format(kamar_inap.tgl_masuk,'%d-%m-%Y') tgl_masuk, "
                                        + "date_format(kamar_inap.tgl_keluar,'%d-%m-%Y') tgl_keluar, "
                                        + "kamar_inap.stts_pulang,kamar_inap.lama,date_format(kamar_inap.jam_masuk,'%h:%i %p') jam_masuk,"
                                        + "date_format(kamar_inap.jam_keluar,'%h:%i %p') jam_keluar,"
                                        + "kamar_inap.ttl_biaya from kamar_inap inner join bangsal inner join kamar "
                                        + "on kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  "
                                        + "where kamar_inap.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Penggunaan Kamar</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal Masuk</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggak Keluar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Lama Inap</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Kamar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Status</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_masuk") + " " + rs3.getString("jam_masuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_keluar") + " " + rs3.getString("jam_keluar") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("lama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_kamar") + ", " + rs3.getString("nm_bangsal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("stts_pulang") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("ttl_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,"
                                        + "operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, "
                                        + "operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,"
                                        + "operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, "
                                        + "operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, "
                                        + "operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, "
                                        + "operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, "
                                        + "operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,"
                                        + "operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,"
                                        + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
                                        + "(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+"
                                        + "operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+"
                                        + "operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+"
                                        + "operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+"
                                        + "operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
                                        + "operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi "
                                        + "on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,"
                                        + "periksa_radiologi.kd_jenis_prw, "
                                        + "jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter "
                                        + "from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter "
                                        + "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter "
                                        + "and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total "
                                        + "FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat "
                                        + "INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL "
                                        + "SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total "
                                        + "FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw "
                                        + "LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(beri_obat_operasi.tanggal,'%d-%m-%Y %h:%i %p') tanggal,beri_obat_operasi.kd_obat,"
                                        + "beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, "
                                        + "(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML4.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());

    }
    
    public void tampilRanap() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs = koneksi.prepareStatement("select pasien.no_rkm_medis, pasien.nm_pasien, pasien.jk, concat(pasien.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat, pasien.umur, "
                        + "tmp_lahir,date_format(tgl_lahir,'%d %M %Y') tgl_lahir,nm_ibu,gol_darah,stts_nikah,agama,pnd,date_format(tgl_daftar,'%d %M %Y') tgl_daftar from pasien inner join kelurahan inner join kecamatan inner join kabupaten "
                        + "on pasien.kd_kel=kelurahan.kd_kel and pasien.kd_kec=kecamatan.kd_kec and "
                        + "pasien.kd_kab=kabupaten.kd_kab where pasien.no_rkm_medis='" + NoRM.getText() + "' order by pasien.no_rkm_medis desc ").executeQuery();
                y = 1;
                while (rs.next()) {
                    htmlContent.append(
                            "<tr class='isi'>"
                            + "<td valign='top' width='20%'>No. RM</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("no_rkm_medis") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Nama Pasien</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("nm_pasien") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Alamat</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("alamat") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Umur</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("umur") + " (" + rs.getString("jk").replaceAll("L", "Laki-Laki").replaceAll("P", "Perempuan") + ")</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Tempat & Tgl. Lahir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tmp_lahir") + ", " + rs.getString("tgl_lahir") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Gol. Darah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("gol_darah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Status Nikah</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("stts_nikah") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Agama</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("agama") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pnddkn. Terakhir</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("pnd") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Pertama Daftar</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'>" + rs.getString("tgl_daftar") + "</td>"
                            + "</tr>"
                            + "<tr class='isi'>"
                            + "<td valign='top' width='20%'>Riwayat Prawtn.</td>"
                            + "<td valign='top' width='1%' align='center'>:</td>"
                            + "<td valign='top' width='79%'></td>"
                            + "</tr>"
                    );
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement(
                                    "select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' and "
                                    + "reg_periksa.status_lanjut = 'ranap' and reg_periksa.tgl_registrasi between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "'").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement(
                                    "select a.no_reg, a.no_rawat,a.tgl_registrasi,a.jam_reg,a.kd_dokter,a.nm_dokter,"
                                    + "a.nm_poli,a.p_jawab,a.almt_pj,a.hubunganpj,a.biaya_reg,if(a.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,a.png_jawab from "
                                    + "(select reg_periksa.no_reg,reg_periksa.no_rawat,date_format(reg_periksa.tgl_registrasi,'%d-%m-%Y') tgl_registrasi,date_format(reg_periksa.jam_reg,'%h:%i %p') jam_reg,"
                                    + "reg_periksa.kd_dokter,dokter.nm_dokter,poliklinik.nm_poli,reg_periksa.p_jawab,reg_periksa.almt_pj,"
                                    + "reg_periksa.hubunganpj,reg_periksa.biaya_reg,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') status_lanjut,penjab.png_jawab "
                                    + "from reg_periksa inner join dokter inner join poliklinik inner join penjab "
                                    + "on reg_periksa.kd_dokter=dokter.kd_dokter and reg_periksa.kd_pj=penjab.kd_pj "
                                    + "and reg_periksa.kd_poli=poliklinik.kd_poli where stts<>'Batal' and "
                                    + "reg_periksa.status_lanjut = 'ranap' and reg_periksa.no_rkm_medis='" + rs.getString("no_rkm_medis") + "' ORDER BY reg_periksa.tgl_registrasi DESC LIMIT 3)"
                                    + "as a ORDER BY a.tgl_registrasi").executeQuery();
                        }

                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;" + urut + ". No. Rawat</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("no_rawat") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tgl. Reg.</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("tgl_registrasi") + ", Jam : " + rs2.getString("jam_reg") + "</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Unit/Poliklinik</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("nm_poli") + " (" + rs2.getString("nm_dokter") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Rawat/Cara Bayar</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + Sequel.cariIsi("select if(status_lanjut='Ranap','Rawat Inap','Rawat Jalan') "
                                            + "from reg_periksa where no_rawat='" + rs2.getString("no_rawat") + "'") + " (" + rs2.getString("png_jawab") + ")</td>"
                                    + "</tr>"
                                    + "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Png. Jawab</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>" + rs2.getString("p_jawab") + " (" + rs2.getString("hubunganpj") + ")</td>"
                                    + "</tr>"                                    
                            );
                            urut++;

                            //menampilkan catatan diagnosa
                            try {
                                rsDiag = koneksi.prepareStatement(
                                        "Select ifnull(diagnosa,'-') diagnosa from pemeriksaan_ralan "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Diag.</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }

                            //menampilkan rencana follow up dokter
                            try {
                                rsDiag = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag != null) {
                                    rsDiag.close();
                                }
                            }
                            
                            //menampilkan rencana follow up perawat/bidan
                            try {
                                rsDiag1 = koneksi.prepareStatement(
                                        "Select ifnull(rencana_follow_up,'-') rencana_follow_up from pemeriksaan_ralan_petugas "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsDiag1.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Renc. Follow Up Dari Perawat/Bidan</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>" + rsDiag1.getString("rencana_follow_up").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsDiag1 != null) {
                                    rsDiag1.close();
                                }
                            }

                            //menampilkan catatan Resep Obat
                            try {
                                rsObat = koneksi.prepareStatement(
                                        "Select nama_obat,status from catatan_resep "
                                        + "where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rsObat.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cttn. Resep Obat</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='4%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='86%' valign='top' bgcolor='#f8fdf3'>Nama Obat</td><td width='5' valign='top' bgcolor='#f8fdf3'>Status</td></tr>"
                                    );
                                    rsObat.beforeFirst();
                                    w = 1;
                                    while (rsObat.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("nama_obat") + "</td>"
                                                + "<td valign='top'>" + rsObat.getString("status") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsObat != null) {
                                    rsObat.close();
                                }
                            }

                            //menampilkan diagnosa penyakit                            
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select diagnosa_pasien.kd_penyakit,penyakit.nm_penyakit, diagnosa_pasien.status "
                                        + "from diagnosa_pasien inner join penyakit "
                                        + "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit "
                                        + "where diagnosa_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diag. ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Penyakit</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_penyakit") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_penyakit") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select prosedur_pasien.kode,icd9.deskripsi_panjang, prosedur_pasien.status "
                                        + "from prosedur_pasien inner join icd9 "
                                        + "on prosedur_pasien.kode=icd9.kode "
                                        + "where prosedur_pasien.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tindkn. ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'><td width='3%' valign='top' bgcolor='#f8fdf3'>No.</td><td width='5%' valign='top' bgcolor='#f8fdf3'>Kode</td><td width='72%' valign='top' bgcolor='#f8fdf3'>Nama Tindakan</td></tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append("<tr><td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kode") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("deskripsi_panjang") + "</td></tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ralan dokter
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan.suhu_tubuh,pemeriksaan_ralan.tensi,pemeriksaan_ralan.nadi,pemeriksaan_ralan.respirasi,"
                                        + "pemeriksaan_ralan.tinggi,pemeriksaan_ralan.berat,pemeriksaan_ralan.gcs,pemeriksaan_ralan.keluhan, "
                                        + "pemeriksaan_ralan.pemeriksaan,pemeriksaan_ralan.alergi,ifnull(pemeriksaan_ralan.diagnosa,'-') diagnosa, "
                                        + "ifnull(pemeriksaan_ralan.rincian_tindakan,'-') rincian_tindakan, ifnull(pemeriksaan_ralan.terapi,'-') terapi from pemeriksaan_ralan where "
                                        + "pemeriksaan_ralan.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Dokter</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"                                            
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>" 
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan riwayat pemeriksaan ralan petugas
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ralan_petugas.suhu_tubuh,pemeriksaan_ralan_petugas.tensi,pemeriksaan_ralan_petugas.nadi,pemeriksaan_ralan_petugas.respirasi,"
                                        + "pemeriksaan_ralan_petugas.tinggi,pemeriksaan_ralan_petugas.berat,pemeriksaan_ralan_petugas.gcs,pemeriksaan_ralan_petugas.keluhan, "
                                        + "pemeriksaan_ralan_petugas.pemeriksaan,pemeriksaan_ralan_petugas.alergi,ifnull(pemeriksaan_ralan_petugas.diagnosa,'-') diagnosa,"
                                        + "ifnull(pemeriksaan_ralan_petugas.rincian_tindakan,'-') rincian_tindakan, "
                                        + "ifnull(pemeriksaan_ralan_petugas.terapi,'-') terapi from pemeriksaan_ralan_petugas where "
                                        + "pemeriksaan_ralan_petugas.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemeriksaan Petugas</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='3%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Diagnosa</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='8%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='4%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Rincian Tindakan</td>"                                            
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Terapi</td>" 
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("diagnosa").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"     
                                                + "<td valign='top'>" + rs3.getString("rincian_tindakan").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("terapi").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //menampilkan riwayat pemeriksaan ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select pemeriksaan_ranap.suhu_tubuh,pemeriksaan_ranap.tensi,pemeriksaan_ranap.nadi,pemeriksaan_ranap.respirasi,"
                                        + "pemeriksaan_ranap.tinggi,pemeriksaan_ranap.berat,pemeriksaan_ranap.gcs,pemeriksaan_ranap.keluhan,"
                                        + "pemeriksaan_ranap.pemeriksaan,pemeriksaan_ranap.alergi,date_format(pemeriksaan_ranap.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(pemeriksaan_ranap.jam_rawat,'%h:%i %p') jam_rawat from pemeriksaan_ranap where "
                                        + "pemeriksaan_ranap.no_rawat='" + rs2.getString("no_rawat") + "' order by pemeriksaan_ranap.tgl_perawatan,pemeriksaan_ranap.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Pemriksan. R. Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Suhu(C)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tensi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Nadi(/menit)</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Respirasi(/menit)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Tinggi(Cm)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Berat(Kg)</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>GCS(E,V,M)</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Keluhan</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Pemeriksaan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Alergi</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("suhu_tubuh") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tensi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nadi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("respirasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tinggi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("berat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("gcs") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("keluhan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("alergi") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //hasil pemeriksaan laboratorium LIS
                            try {
                                rsLISMaster = koneksi.prepareStatement(
                                        "SELECT no_lab FROM lis_reg "
                                        + "WHERE no_rawat='" + rs2.getString("no_rawat") + "' "
                                        + "ORDER BY no_lab").executeQuery();
                                if (rsLISMaster.next()) {
                                    rsLISMaster.beforeFirst();
                                    lisM = 1;
                                    while (rsLISMaster.next()) {
                                        htmlContent.append(
                                                "<tr class='isi'>"
                                                + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Laboratorium</td>"
                                                + "<td valign='top' width='1%' align='center'>:</td>"
                                                + "<td valign='top' width='79%'>"
                                                + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"                                                
                                                + "<tr><td valign='top' colspan='6'>No. Lab. : " + rsLISMaster.getString("no_lab") + "</td></td></tr>"
                                                + "<tr align='center'>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Jenis Pemeriksaan/Item</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Metode Pemeriksaan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Hasil</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nilai Rujukan</td>"
                                                + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Satuan</td>"
                                                + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Flag Kode</td>"
                                                + "</tr>"
                                        );

                                        rsLIS1 = koneksi.prepareStatement(
                                                "SELECT ifnull(kategori_pemeriksaan_nama,'') kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lr.no_lab ='" + rsLISMaster.getString("no_lab") + "' GROUP BY lhp.kategori_pemeriksaan_nama "
                                                + "ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();

                                        if (rsLIS1.next()) {
                                            rsLIS1.beforeFirst();
                                            w = 1;
                                            while (rsLIS1.next()) {
                                                htmlContent.append(
                                                        "<tr>"
                                                        + "<td valign='top'>" + rsLIS1.getString("kategori_pemeriksaan_nama") + "</td>"
                                                        + "</tr>");

                                                rsLIS2 = koneksi.prepareStatement("SELECT ifnull(lhp.sub_kategori_pemeriksaan_nama,'') sub_kategori_pemeriksaan_nama FROM lis_reg lr LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab "
                                                        + "WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.kategori_pemeriksaan_nama='" + rsLIS1.getString("kategori_pemeriksaan_nama") + "' "
                                                        + "GROUP BY lhp.sub_kategori_pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                if (rsLIS2.next()) {
                                                    rsLIS2.beforeFirst();
                                                    lis1 = 1;
                                                    while (rsLIS2.next()) {
                                                        htmlContent.append(
                                                                "<tr>"
                                                                + "<td valign='top'>&emsp;" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "</td>"
                                                                + "</tr>");

                                                        rsLIS3 = koneksi.prepareStatement("SELECT ifnull(lhp.pemeriksaan_nama,'') pemeriksaan_nama, lhp.metode, lhp.nilai_hasil, lhp.nilai_rujukan, lhp.satuan, lhp.flag_kode FROM lis_reg lr "
                                                                + "LEFT JOIN lis_hasil_periksa_lab lhp on lhp.no_lab=lr.no_lab WHERE lr.no_rawat='" + rs2.getString("no_rawat") + "' and lhp.sub_kategori_pemeriksaan_nama='" + rsLIS2.getString("sub_kategori_pemeriksaan_nama") + "' "
                                                                + "GROUP BY lhp.pemeriksaan_nama ORDER BY lhp.kategori_pemeriksaan_no_urut, lhp.sub_kategori_pemeriksaan_no_urut,lhp.pemeriksaan_no_urut").executeQuery();
                                                        if (rsLIS3.next()) {
                                                            rsLIS3.beforeFirst();
                                                            lis2 = 1;
                                                            while (rsLIS3.next()) {
                                                                htmlContent.append(
                                                                        "<tr>"
                                                                        + "<td valign='top'>&emsp;&emsp;" + rsLIS3.getString("pemeriksaan_nama") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("metode") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_hasil") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("nilai_rujukan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("satuan") + "</td>"
                                                                        + "<td valign='top'>" + rsLIS3.getString("flag_kode") + "</td>"
                                                                        + "</tr>");
                                                                lis2++;
                                                            }
                                                        }
                                                        lis1++;
                                                    }
                                                }
                                                w++;
                                            }
                                            htmlContent.append(
                                                    "</table><br/>");
                                        }
                                    }
                                }

                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rsLIS1 != null) {
                                    rsLIS1.close();
                                }
                            }
                            
                            //hasil pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT date_format(pr.tgl_periksa, '%d-%m-%Y') tgl_periksa, date_format(pr.jam, '%h:%i %p') jam, "
                                        + "ifnull(jpr.nm_perawatan,'-') nm_pemeriksaan, ifnull(hr.diag_klinis_radiologi, '-') diag_klinis_radiologi, "
                                        + "ifnull(hr.hasil, '-') hasil FROM periksa_radiologi pr INNER JOIN jns_perawatan_radiologi jpr on jpr.kd_jenis_prw=pr.kd_jenis_prw "
                                        + "LEFT JOIN hasil_radiologi hr on hr.no_rawat=pr.no_rawat and hr.kd_jenis_prw=pr.kd_jenis_prw AND hr.tgl_periksa=pr.tgl_periksa AND hr.jam=pr.jam "
                                        + "WHERE pr.no_rawat='" + rs2.getString("no_rawat") + "' ORDER BY pr.tgl_periksa, pr.jam").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Hasil Pemeriksaan Radiologi</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tgl. Periksa</td>"                                            
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Diagnosa Klinis</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Item/Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Bacaan/Hasil Pemeriksaan</td>"
                                            + "</tr>"
                                    );
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"                                                
                                                + "<td valign='top'>" + rs3.getString("diag_klinis_radiologi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("hasil").replaceAll("(\r\n|\r|\n|\n\r)","<br>") + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }
                            
                            //menampilkan tarif klaim inacbg ranap
                            try {
                                rs3 = koneksi.prepareStatement("SELECT ifnull(enc.no_rawat,'') no_rawat, ifnull(enc.klaim_final,'') klaim_final, ifnull(eg.cbg_desc,'') cbg_desc, "
                                        + "IFNULL(egsc.desc,'-') topup_desc, concat('Rp. ',format(ifnull(eg.cbg_tarif,''),0)) cbg_tarif, "
                                        + "concat('Rp. ',IFNULL(format(egsc.tarif,0),0)) topup_tarif, concat('Rp. ',IFNULL(format(eg.cbg_tarif+egsc.tarif,0),format(eg.cbg_tarif,0))) total_trf_grp, "
                                        + "concat('Rp. ',format(ifnull(esc.tarif_obat,''),0)) by_obat_real, CONCAT(FORMAT((esc.tarif_obat/IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100,2),' ','%') perc_pakai_obat, "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=40,'#00ff00', "
                                        + "IF((esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100>40 AND (esc.tarif_obat/ IFNULL(eg.cbg_tarif+egsc.tarif,eg.cbg_tarif))*100<=80,'#ff8040','#ff3333')) warna_sel "
                                        + "FROM eklaim_new_claim enc INNER JOIN eklaim_set_claim esc ON esc.no_sep=enc.no_sep INNER JOIN eklaim_grouping eg ON eg.no_sep=enc.no_sep "
                                        + "INNER JOIN reg_periksa rp ON rp.no_rawat=enc.no_rawat INNER JOIN poliklinik p ON p.kd_poli=rp.kd_poli INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter "
                                        + "LEFT JOIN eklaim_grouping_spc_cmg egsc ON egsc.no_sep=enc.no_sep WHERE rp.status_lanjut='Ranap' and enc.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tarif Klaim INACBG Rawat Inap</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Status Klaim</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Deskripsi CBG</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Deskripsi TopUp</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Tarif CBG</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>TopUp Tarif</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Total Tarif Grouping</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Biaya Real Obat</td>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Pemakaian Obat</td>"
                                            + "</tr>"
                                    );
                                    
                                    rs3.beforeFirst();
                                    while (rs3.next()) {     
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("klaim_final") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_desc").replaceAll("(\r\n|\r|\n|\n\r)", "<br>") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("cbg_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("topup_tarif") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("total_trf_grp") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("by_obat_real") + "</td>"
                                                + "<td valign='top' bgcolor='" + rs3.getString("warna_sel") + "'><b>" + rs3.getString("perc_pakai_obat") + "</b></td>"
                                                + "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //biaya administrasi
                            htmlContent.append(
                                    "<tr class='isi'>"
                                    + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Biaya & Perawatan</td>"
                                    + "<td valign='top' width='1%' align='center'>:</td>"
                                    + "<td valign='top' width='79%'>"
                                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    + "<tr>"
                                    + "<td valign='top' width='89%'>Administrasi</td>"
                                    + "<td valign='top' width='1%' align='right'>:</td>"
                                    + "<td valign='top' width='10%' align='right'>" + Valid.SetAngka(rs2.getDouble("biaya_reg")) + "</td>"
                                    + "</tr>"
                                    + "</table>"
                            );

                            //tindakan dokter ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_dr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,rawat_jl_dr.biaya_rawat "
                                        + "from rawat_jl_dr inner join jns_perawatan inner join dokter "
                                        + "on rawat_jl_dr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_dr.kd_dokter=dokter.kd_dokter where rawat_jl_dr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ralan
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_pr.kd_jenis_prw,jns_perawatan.nm_perawatan,petugas.nama,rawat_jl_pr.biaya_rawat "
                                        + "from rawat_jl_pr inner join jns_perawatan inner join petugas "
                                        + "on rawat_jl_pr.kd_jenis_prw=jns_perawatan.kd_jenis_prw "
                                        + "and rawat_jl_pr.nip=petugas.nip where rawat_jl_pr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Jalan Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='45%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan ralan dokter dan paramedis
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select rawat_jl_drpr.kd_jenis_prw,jns_perawatan.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_jl_drpr.biaya_rawat "
                                        + "from rawat_jl_drpr inner join jns_perawatan inner join dokter inner join petugas "
                                        + "on rawat_jl_drpr.kd_jenis_prw=jns_perawatan.kd_jenis_prw and rawat_jl_drpr.nip=petugas.nip "
                                        + "and rawat_jl_drpr.kd_dokter=dokter.kd_dokter where rawat_jl_drpr.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Jalan Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='25%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs2.getString("tgl_registrasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_dr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_dr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_dr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "dokter.nm_dokter,rawat_inap_dr.biaya_rawat "
                                        + "from rawat_inap_dr inner join jns_perawatan_inap inner join dokter "
                                        + "on rawat_inap_dr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_dr.kd_dokter=dokter.kd_dokter where rawat_inap_dr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_dr.tgl_perawatan, rawat_inap_dr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Dokter</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_pr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,date_format(rawat_inap_pr.jam_rawat,'%h:%i %p') jam_rawat,"
                                        + "rawat_inap_pr.kd_jenis_prw,jns_perawatan_inap.nm_perawatan,"
                                        + "petugas.nama,rawat_inap_pr.biaya_rawat "
                                        + "from rawat_inap_pr inner join jns_perawatan_inap inner join petugas "
                                        + "on rawat_inap_pr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw "
                                        + "and rawat_inap_pr.nip=petugas.nip where rawat_inap_pr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_pr.tgl_perawatan, rawat_inap_pr.jam_rawat").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Tindakan Rawat Inap Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='20%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan paramedis dan dokter ranap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(rawat_inap_drpr.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(rawat_inap_drpr.jam_rawat,'%h:%i %p') jam_rawat,rawat_inap_drpr.kd_jenis_prw,"
                                        + "jns_perawatan_inap.nm_perawatan,dokter.nm_dokter,petugas.nama,rawat_inap_drpr.biaya_rawat "
                                        + "from rawat_inap_drpr inner join jns_perawatan_inap inner join dokter inner join petugas "
                                        + "on rawat_inap_drpr.kd_jenis_prw=jns_perawatan_inap.kd_jenis_prw and rawat_inap_drpr.nip=petugas.nip "
                                        + "and rawat_inap_drpr.kd_dokter=dokter.kd_dokter where rawat_inap_drpr.no_rawat='" + rs2.getString("no_rawat") + "' order by rawat_inap_drpr.tgl_perawatan,rawat_inap_drpr.jam_rawat ").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Tindakan Rawat Inap Dokter & Paramedis</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Tindakan/Perawatan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Paramedis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam_rawat") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya_rawat")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //kamar inap
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select kamar_inap.kd_kamar,bangsal.nm_bangsal,date_format(kamar_inap.tgl_masuk,'%d-%m-%Y') tgl_masuk, "
                                        + "date_format(kamar_inap.tgl_keluar,'%d-%m-%Y') tgl_keluar, "
                                        + "kamar_inap.stts_pulang,kamar_inap.lama,date_format(kamar_inap.jam_masuk,'%h:%i %p') jam_masuk,"
                                        + "date_format(kamar_inap.jam_keluar,'%h:%i %p') jam_keluar,"
                                        + "kamar_inap.ttl_biaya from kamar_inap inner join bangsal inner join kamar "
                                        + "on kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  "
                                        + "where kamar_inap.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Penggunaan Kamar</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal Masuk</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggak Keluar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Lama Inap</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Kamar</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Status</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_masuk") + " " + rs3.getString("jam_masuk") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_keluar") + " " + rs3.getString("jam_keluar") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("lama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("kd_kamar") + ", " + rs3.getString("nm_bangsal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("stts_pulang") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("ttl_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(operasi.tgl_operasi,'%d-%m-%Y %h:%i %p') tgl_operasi,operasi.jenis_anasthesi,operasi.operator1, operasi.operator2, operasi.operator3, operasi.asisten_operator1,"
                                        + "operasi.asisten_operator2, operasi.instrumen, operasi.dokter_anak, operasi.perawaat_resusitas, "
                                        + "operasi.dokter_anestesi, operasi.asisten_anestesi, operasi.bidan, operasi.bidan2, operasi.bidan3, operasi.perawat_luar, operasi.omloop,"
                                        + "operasi.omloop2,operasi.omloop3,operasi.dokter_pjanak,operasi.dokter_umum, "
                                        + "operasi.kode_paket,paket_operasi.nm_perawatan, operasi.biayaoperator1, operasi.biayaoperator2, operasi.biayaoperator3, "
                                        + "operasi.biayaasisten_operator1, operasi.biayaasisten_operator2, operasi.biayainstrumen, "
                                        + "operasi.biayadokter_anak, operasi.biayaperawaat_resusitas, operasi.biayadokter_anestesi, "
                                        + "operasi.biayaasisten_anestesi, operasi.biayabidan,operasi.biayabidan2,operasi.biayabidan3, operasi.biayaperawat_luar, operasi.biayaalat,"
                                        + "operasi.biayasewaok,operasi.akomodasi,operasi.bagian_rs,operasi.biaya_omloop,operasi.biaya_omloop2,operasi.biaya_omloop3,"
                                        + "operasi.biayasarpras,operasi.biaya_dokter_pjanak,operasi.biaya_dokter_umum,"
                                        + "(operasi.biayaoperator1+operasi.biayaoperator2+operasi.biayaoperator3+"
                                        + "operasi.biayaasisten_operator1+operasi.biayaasisten_operator2+operasi.biayainstrumen+"
                                        + "operasi.biayadokter_anak+operasi.biayaperawaat_resusitas+operasi.biayadokter_anestesi+"
                                        + "operasi.biayaasisten_anestesi+operasi.biayabidan+operasi.biayabidan2+operasi.biayabidan3+operasi.biayaperawat_luar+operasi.biayaalat+"
                                        + "operasi.biayasewaok+operasi.akomodasi+operasi.bagian_rs+operasi.biaya_omloop+operasi.biaya_omloop2+operasi.biaya_omloop3+"
                                        + "operasi.biayasarpras+operasi.biaya_dokter_pjanak+operasi.biaya_dokter_umum) as total from operasi inner join paket_operasi "
                                        + "on operasi.kode_paket=paket_operasi.kode_paket where operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Operasi/VK</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Tindakan</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Anastesi</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_operasi") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + " (");
                                        if (rs3.getDouble("biayaoperator1") > 0) {
                                            htmlContent.append("Operator 1 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator2") > 0) {
                                            htmlContent.append("Operator 2 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaoperator3") > 0) {
                                            htmlContent.append("Operator 3 : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("operator3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator1") > 0) {
                                            htmlContent.append("Asisten Operator 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator1")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_operator2") > 0) {
                                            htmlContent.append("Asisten Operator 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_operator2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayainstrumen") > 0) {
                                            htmlContent.append("Instrumen : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("instrumen")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anak") > 0) {
                                            htmlContent.append("Dokter Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anak")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawaat_resusitas") > 0) {
                                            htmlContent.append("Perawat Resusitas : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawaat_resusitas")) + ", ");
                                        }
                                        if (rs3.getDouble("biayadokter_anestesi") > 0) {
                                            htmlContent.append("Dokter Anestesi : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaasisten_anestesi") > 0) {
                                            htmlContent.append("Asisten Anestesi : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("asisten_anestesi")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan") > 0) {
                                            htmlContent.append("Bidan 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan2") > 0) {
                                            htmlContent.append("Bidan 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan2")) + ", ");
                                        }
                                        if (rs3.getDouble("biayabidan3") > 0) {
                                            htmlContent.append("Bidan 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("bidan3")) + ", ");
                                        }
                                        if (rs3.getDouble("biayaperawat_luar") > 0) {
                                            htmlContent.append("Perawat Luar : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("perawat_luar")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop") > 0) {
                                            htmlContent.append("Onloop 1 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop2") > 0) {
                                            htmlContent.append("Onloop 2 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop2")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_omloop3") > 0) {
                                            htmlContent.append("Onloop 3 : " + Sequel.cariIsi("select nama from petugas where nip=?", rs3.getString("omloop3")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_pjanak") > 0) {
                                            htmlContent.append("Dokter Pj Anak : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_pjanak")) + ", ");
                                        }
                                        if (rs3.getDouble("biaya_dokter_umum") > 0) {
                                            htmlContent.append("Dokter Umum : " + Sequel.cariIsi("select nm_dokter from dokter where kd_dokter=?", rs3.getString("dokter_umum")) + ", ");
                                        }
                                        htmlContent.append(
                                                ")</td>"
                                                + "<td valign='top'>" + rs3.getString("jenis_anasthesi") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan radiologi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(periksa_radiologi.tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(periksa_radiologi.jam,'%h:%i %p') jam,"
                                        + "periksa_radiologi.kd_jenis_prw, "
                                        + "jns_perawatan_radiologi.nm_perawatan,petugas.nama,periksa_radiologi.biaya,periksa_radiologi.dokter_perujuk,dokter.nm_dokter "
                                        + "from periksa_radiologi inner join jns_perawatan_radiologi inner join petugas inner join dokter "
                                        + "on periksa_radiologi.kd_jenis_prw=jns_perawatan_radiologi.kd_jenis_prw and periksa_radiologi.kd_dokter=dokter.kd_dokter "
                                        + "and periksa_radiologi.nip=petugas.nip  where periksa_radiologi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Radiologi</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='26%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Dokter Pemeriksa Rad.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Petugas</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //gambar pemeriksaan radiologi
                            try {
                                host = Sequel.decXML(prop.getProperty("HOST"), prop.getProperty("KEY"));
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(tgl_periksa,'%d-%m-%Y') tgl_periksa,date_format(jam,'%h:%i %p') jam, "
                                        + "lokasi_gambar from gambar_radiologi where no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Gambar Radiologi</td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='80%' bgcolor='#f8fdf3'>Gambar Radiologi</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_periksa") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'><a href='http://" + host + ":" + prop.getProperty("PORTWEB") + "/" + prop.getProperty("HYBRIDWEB") + "/radiologi/" + rs3.getString("lokasi_gambar") + "'>" + rs3.getString("lokasi_gambar").replaceAll("pages/upload/", "") + "</a></td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //tindakan pemeriksaan laborat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "SELECT DISTINCT dp.no_rawat, d.nm_dokter, pt.nama, '' nm_perawatan, '' Pemeriksaan, '' qty, '' total "
                                        + "FROM detail_periksa_lab dp INNER JOIN periksa_lab pl ON pl.no_rawat = dp.no_rawat "
                                        + "INNER JOIN dokter d ON d.kd_dokter = pl.kd_dokter INNER JOIN petugas pt ON pt.nip = pl.nip "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' UNION ALL "
                                        + "SELECT dp.no_rawat, '', '',j.nm_perawatan, tl.Pemeriksaan, count(dp.kd_jenis_prw) qty, sum(tl.biaya_item) total "
                                        + "FROM detail_periksa_lab dp LEFT JOIN jns_perawatan_lab j ON dp.kd_jenis_prw = j.kd_jenis_prw "
                                        + "LEFT JOIN template_laboratorium tl ON dp.id_template = tl.id_template "
                                        + "WHERE dp.no_rawat = '" + rs2.getString("no_rawat") + "' GROUP BY dp.no_rawat, j.nm_perawatan, tl.Pemeriksaan").executeQuery();
                                
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemeriksaan Laboratorium</td><td valign='top' colspan='1' align='right'>:</td><td valign='top'></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='18%' bgcolor='#f8fdf3'>Dokter Pnggng. Jwb. Lab.</td>"
                                            + "<td valign='top' width='17%' bgcolor='#f8fdf3'>Nama Petugas</td>"
                                            + "<td valign='top' width='16%' bgcolor='#f8fdf3'>Nama Pemeriksaan</td>"
                                            + "<td valign='top' width='40%' bgcolor='#f8fdf3'>Item Pemeriksaan</td>"
                                            + "<td valign='top' width='6%' bgcolor='#f8fdf3'>Qty.</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya/Tarif</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top'>" + rs3.getString("nm_dokter") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_perawatan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("Pemeriksaan") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("qty") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>"
                                        );
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select date_format(detail_pemberian_obat.tgl_perawatan,'%d-%m-%Y') tgl_perawatan,"
                                        + "date_format(detail_pemberian_obat.jam,'%h:%i %p') jam,databarang.kode_sat, "
                                        + "detail_pemberian_obat.kode_brng,detail_pemberian_obat.jml,detail_pemberian_obat.total,"
                                        + "databarang.nama_brng from detail_pemberian_obat inner join databarang "
                                        + "on detail_pemberian_obat.kode_brng=databarang.kode_brng  "
                                        + "where detail_pemberian_obat.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='5'>Pemberian Obat/BHP/Alkes</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='35%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Aturan Pakai</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tgl_perawatan") + " " + rs3.getString("jam") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top'>" + Sequel.cariIsi("select concat('Aturan pakai : ',aturan1,' ',aturan2,' ',aturan3,', Waktu : ',waktu1,' ',waktu2,', Keterangan : ',keterangan,', Masa simpan : ',waktu_simpan) "
                                                        + "from aturan_pakai where tgl_perawatan='" + rs3.getString("tgl_perawatan") + "' and "
                                                        + "jam='" + rs3.getString("jam") + "' and no_rawat='" + rs2.getString("no_rawat") + "' and "
                                                        + "kode_brng='" + rs3.getString("kode_brng") + "'") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //pemberian obat Operasi
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select DATE_FORMAT(beri_obat_operasi.tanggal,'%d-%m-%Y %h:%i %p') tanggal,beri_obat_operasi.kd_obat,"
                                        + "beri_obat_operasi.hargasatuan,obatbhp_ok.kode_sat, "
                                        + "beri_obat_operasi.jumlah, obatbhp_ok.nm_obat,(beri_obat_operasi.hargasatuan*beri_obat_operasi.jumlah) as total "
                                        + "from beri_obat_operasi inner join obatbhp_ok  on  beri_obat_operasi.kd_obat=obatbhp_ok.kd_obat  "
                                        + "where beri_obat_operasi.no_rawat='" + rs2.getString("no_rawat") + "'").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Penggunaan Obat/BHP Operasi</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Tanggal</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("tanggal") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nm_obat") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Resep Pulang
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select resep_pulang.kode_brng,databarang.nama_brng,resep_pulang.dosis,resep_pulang.jml_barang, "
                                        + "databarang.kode_sat,resep_pulang.dosis,resep_pulang.total from resep_pulang inner join databarang "
                                        + "on resep_pulang.kode_brng=databarang.kode_brng where "
                                        + "resep_pulang.no_rawat='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='4'>Resep Pulang</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='50%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='15%' bgcolor='#f8fdf3'>Dosis</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getString("dosis") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jml_barang") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Retur Obat
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select databarang.kode_brng,databarang.nama_brng,detreturjual.kode_sat,detreturjual.h_retur, "
                                        + "(detreturjual.jml_retur * -1) as jumlah,(detreturjual.subtotal * -1) as total from detreturjual "
                                        + "inner join databarang inner join returjual on detreturjual.kode_brng=databarang.kode_brng "
                                        + "and returjual.no_retur_jual=detreturjual.no_retur_jual where returjual.no_retur_jual='" + rs2.getString("no_rawat") + "' order by databarang.nama_brng").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='3'>Retur Obat</td><td valign='top' colspan='1' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='65%' bgcolor='#f8fdf3'>Nama Obat/BHP/Alkes</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Jumlah</td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_brng") + "</td>"
                                                + "<td valign='top'>" + rs3.getDouble("jumlah") + " " + rs3.getString("kode_sat") + "</td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("total")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Tambahan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_biaya, besar_biaya from tambahan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_biaya").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Tambahan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Tambahan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_biaya") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_biaya")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            //Pengurangan Biaya
                            try {
                                rs3 = koneksi.prepareStatement(
                                        "select nama_pengurangan, (-1*besar_pengurangan) as besar_pengurangan from pengurangan_biaya where no_rawat='" + rs2.getString("no_rawat") + "' order by nama_pengurangan").executeQuery();
                                if (rs3.next()) {
                                    htmlContent.append(
                                            "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr><td valign='top' colspan='2'>Potongan Biaya</td><td valign='top' align='right'>:</td><td></td></tr>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>No.</td>"
                                            + "<td valign='top' width='84%' bgcolor='#f8fdf3'>Nama Potongan</td>"
                                            + "<td valign='top' width='1%' bgcolor='#f8fdf3'></td>"
                                            + "<td valign='top' width='10%' bgcolor='#f8fdf3'>Biaya</td>"
                                            + "</tr>");
                                    rs3.beforeFirst();
                                    w = 1;
                                    while (rs3.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                                + "<td valign='top' align='center'>" + w + "</td>"
                                                + "<td valign='top'>" + rs3.getString("nama_pengurangan") + "</td>"
                                                + "<td valign='top'></td>"
                                                + "<td valign='top' align='right'>" + Valid.SetAngka(rs3.getDouble("besar_pengurangan")) + "</td>"
                                                + "</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }

                            htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());

    }

    public void isPasien() {
        Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis=? ", TPasien, NoRM.getText());
    }

    public void setNoRm(String norm, String nama) {
        NoRM.setText(norm);
        TPasien.setText(nama);
    }
    
    private void tampilRingkasanPulangRanap() {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            StringBuilder htmlContent = new StringBuilder();
            try {
                rs5 = koneksi.prepareStatement("SELECT p.no_rkm_medis, p.nm_pasien, p.jk, concat(p.alamat,', ',kl.nm_kel,', ',kc.nm_kec,', ',kb.nm_kab) alamat, "
                        + "p.umur, p.tmp_lahir, date_format(p.tgl_lahir,'%d %M %Y') tgl_lahir, p.nm_ibu, p.gol_darah, p.stts_nikah, "
                        + "p.agama, p.pnd, date_format(p.tgl_daftar,'%d %M %Y') tgl_daftar FROM pasien p INNER JOIN kelurahan kl ON p.kd_kel = kl.kd_kel "
                        + "INNER JOIN kecamatan kc ON p.kd_kec = kc.kd_kec INNER JOIN kabupaten kb ON p.kd_kab = kb.kd_kab WHERE  "
                        + "p.no_rkm_medis = '" + NoRM.getText() + "' ORDER BY p.no_rkm_medis DESC").executeQuery();
                
                y = 1;
                while (rs5.next()) {
                    htmlContent.append(
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>No. RM</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("no_rkm_medis")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Nama Pasien</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("nm_pasien")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Alamat</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("alamat")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Umur</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("umur")+" ("+rs5.getString("jk").replaceAll("L","Laki-Laki").replaceAll("P","Perempuan")+")</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Tempat & Tanggal Lahir</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("tmp_lahir")+", "+rs5.getString("tgl_lahir")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Ibu Kandung</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("nm_ibu")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Golongan Darah</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("gol_darah")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Status Nikah</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("stts_nikah")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Agama</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("agama")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Pendidikan Terakhir</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("pnd")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Pertama Daftar</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'>"+rs5.getString("tgl_daftar")+"</td>"+
                        "</tr>"+
                        "<tr class='isi'>"+ 
                          "<td valign='top' width='20%'>Riwayat Perawatan</td>"+
                          "<td valign='top' width='1%' align='center'>:</td>"+
                          "<td valign='top' width='79%'></td>"+
                        "</tr>"
                    );
                    
                    try {
                        if (ChkTanggal.isSelected() == true) {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_rawat, b.nm_bangsal, b.nm_gedung, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, DATE_FORMAT(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y') tgl_pulang, DATE_FORMAT(ki.jam_keluar, '%h:%i %p') jam_plg, pj.png_jawab, ifnull(d.nm_dokter,'-') dpjp, ki.stts_pulang "
                                    + "FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN dpjp_ranap dr ON dr.no_rawat = ki.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE ki.stts_pulang NOT IN ('-', 'Pindah Kamar') AND rp.no_rkm_medis = '" + rs5.getString("no_rkm_medis") + "' "
                                    + "AND rp.tgl_registrasi BETWEEN '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg").executeQuery();
                        } else {
                            rs2 = koneksi.prepareStatement("SELECT rp.no_rawat, b.nm_bangsal, b.nm_gedung, DATE_FORMAT(rp.tgl_registrasi,'%d-%m-%Y') tgl_reg, DATE_FORMAT(rp.jam_reg,'%h:%i %p') jam_reg, "
                                    + "DATE_FORMAT(ki.tgl_keluar, '%d-%m-%Y') tgl_pulang, DATE_FORMAT(ki.jam_keluar, '%h:%i %p') jam_plg, pj.png_jawab, ifnull(d.nm_dokter,'-') dpjp, ki.stts_pulang "
                                    + "FROM kamar_inap ki INNER JOIN kamar k ON k.kd_kamar = ki.kd_kamar INNER JOIN bangsal b ON b.kd_bangsal = k.kd_bangsal "
                                    + "INNER JOIN reg_periksa rp ON rp.no_rawat = ki.no_rawat INNER JOIN penjab pj ON pj.kd_pj = rp.kd_pj "
                                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis LEFT JOIN dpjp_ranap dr ON dr.no_rawat = ki.no_rawat "
                                    + "INNER JOIN dokter d ON d.kd_dokter = dr.kd_dokter WHERE ki.stts_pulang NOT IN ('-', 'Pindah Kamar') AND rp.no_rkm_medis = '" + rs5.getString("no_rkm_medis") + "' "
                                    + "ORDER BY rp.tgl_registrasi, rp.jam_reg desc limit 3").executeQuery();
                        }
                        
                        urut = 1;
                        while (rs2.next()) {
                            htmlContent.append(
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;"+urut+". No. Rawat</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("no_rawat")+"</td>"+
                              "</tr>"+
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanggal Masuk</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("tgl_reg")+", Jam : "+rs2.getString("jam_reg")+"</td>"+
                              "</tr>"+
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Tanggal Pulang</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("tgl_pulang")+", Jam : "+rs2.getString("jam_plg")+"</td>"+
                              "</tr>"+ 
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Status Pulang</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("stts_pulang")+"</td>"+
                              "</tr>"+
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ruang Perawatan</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("nm_bangsal")+", Gedung : "+rs2.getString("nm_gedung")+"</td>"+
                              "</tr>"+
                                        
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Nama DPJP</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("dpjp")+"</td>"+
                              "</tr>"+
                              "<tr class='isi'>"+ 
                                "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Cara Bayar</td>"+
                                "<td valign='top' width='1%' align='center'>:</td>"+
                                "<td valign='top' width='79%'>"+rs2.getString("png_jawab")+"</td>"+
                              "</tr>"
                            );
                            urut++;
                            
                            //menampilkan ringkasan pulang rawat inap
                            try {
                                rs3 = koneksi.prepareStatement("SELECT rr.no_rawat, IF(rr.nm_dokter_pengirim='','-',rr.nm_dokter_pengirim) dr_pengirim, rr.alasan_masuk_dirawat, rr.ringkasan_riwayat_penyakit, "
                                        + "rr.pemeriksaan_fisik, rr.pemeriksaan_penunjang, rr.terapi_pengobatan, rr.diagnosa_utama, rr.diagnosa_sekunder, rr.tindakan_prosedur, "
                                        + "rr.keadaan_umum, rr.kesadaran, rr.gcs, rr.tekanan_darah, rr.suhu, rr.nadi, rr.frekuensi_nafas, rr.catatan_penting, "
                                        + "rr.terapi_pulang, rr.pengobatan_dilanjutkan, if(rr.pengobatan_dilanjutkan='Dokter Luar',rr.dokter_luar_lanjutan,'-') dr_luar, "
                                        + "if(rr.tgl_kontrol_poliklinik='0000-00-00','-',date_format(rr.tgl_kontrol_poliklinik,'%d %M %Y')) tgl_kontrol FROM ringkasan_pulang_ranap rr "
                                        + "INNER JOIN kamar_inap ki on ki.no_rawat=rr.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=rr.no_rawat INNER JOIN penjab pj on pj.kd_pj=rp.kd_pj INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN dpjp_ranap dr on dr.no_rawat=ki.no_rawat INNER JOIN dokter d on d.kd_dokter=dr.kd_dokter "
                                        + "WHERE ki.stts_pulang not in ('-','Pindah Kamar') and rr.no_rawat  = '" + rs2.getString("no_rawat") + "'").executeQuery();
                                
                                if(rs3.next()){
                                    htmlContent.append(
                                      "<tr class='isi'>"+ 
                                        "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ringkasan Pulang Rawat Inap</td>"+
                                        "<td valign='top' width='1%' align='center'>:</td>"+
                                        "<td valign='top' width='79%'>"+
                                          "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                    );
                                    rs3.beforeFirst();
                                    while(rs3.next()){
                                        htmlContent.append(
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Dokter Pengirim</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("dr_pengirim")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Alasan Masuk Dirawat</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("alasan_masuk_dirawat")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Ringkasan Riwayat Penyakit</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("ringkasan_riwayat_penyakit")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Pemeriksaan Fisik</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("pemeriksaan_fisik")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Pemeriksaan Penunjang Diagnostik</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("pemeriksaan_penunjang")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Terapi Pengobatan Selama Dirawat & Efek Samping (bila ada)</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("terapi_pengobatan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Keadaan Umum</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("keadaan_umum")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Kesadaran</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("kesadaran")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Tanda Vital</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>Tekanan Darah : "+rs3.getString("tekanan_darah")+", Suhu : "+rs3.getString("suhu")+", Nadi : "+rs3.getString("nadi")+", Frekuensi Nafas : "+rs3.getString("frekuensi_nafas")+", GCS : "+rs3.getString("gcs")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Pengobatan Lanjutan</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("pengobatan_dilanjutkan")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Dokter Luar</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("dr_luar")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Tanggal Kontrol Ulang</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("tgl_kontrol")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Catatan Penting (Kondisi saat ini)</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("catatan_penting")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Terapi Pulang</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("terapi_pulang")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Diagnosa Utama/Primer yang diketik manual</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("diagnosa_utama")+"</td>"+
                                                "</tr>"+
                                                "<tr class='isi'>"+ 
                                                    "<td valign='top' width='20%'>Diagnosa Sekunder yang diketik manual</td>"+
                                                    "<td valign='top' width='1%' align='center'>:</td>"+
                                                    "<td valign='top' width='79%'>"+rs3.getString("diagnosa_sekunder")+"</td>"+
                                                "</tr>"+             
                                                "</tr>");
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");                                
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                            }  
                            
                            //menampilkan diagnosa utama/primer
                            try {
                                rs6 = koneksi.prepareStatement("SELECT dp.no_rawat, dp.kd_penyakit, py.ciri_ciri nm_penyakit FROM diagnosa_pasien dp "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit WHERE dp.prioritas='1' and "
                                        + "dp.no_rawat='" + rs2.getString("no_rawat") + "' and dp.status='Ranap' ORDER BY dp.prioritas").executeQuery();
                                
                                if (rs6.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa Utama/Primer ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td></tr>");
                                    
                                    rs6.beforeFirst();
                                    w = 1;
                                    while (rs6.next()) {
                                        htmlContent.append(
                                               "<tr>"
                                               +"<td valign='top'>" + rs6.getString("kd_penyakit") + "</td>"
                                               +"<td valign='top'>" + rs6.getString("nm_penyakit") + "</td>"
                                               +"</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs6 != null) {
                                    rs6.close();
                                }
                            }
                            
                            //menampilkan diagnosa sekunder
                            try {
                                rs7 = koneksi.prepareStatement("SELECT dp.no_rawat, dp.kd_penyakit, py.ciri_ciri nm_penyakit FROM diagnosa_pasien dp "
                                        + "INNER JOIN reg_periksa rp on rp.no_rawat=dp.no_rawat INNER JOIN pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                                        + "INNER JOIN penyakit py on py.kd_penyakit=dp.kd_penyakit WHERE dp.prioritas<>'1' and "
                                        + "dp.no_rawat='" + rs2.getString("no_rawat") + "' and dp.status='Ranap' ORDER BY dp.prioritas").executeQuery();
                                
                                if (rs7.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Diagnosa Sekunder ICD-10</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-10</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Diagnosa ICD-10</td></tr>");
                                    
                                    rs7.beforeFirst();
                                    w = 1;
                                    while (rs7.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                               +"<td valign='top'>" + rs7.getString("kd_penyakit") + "</td>"
                                               +"<td valign='top'>" + rs7.getString("nm_penyakit") + "</td>"
                                               +"</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs7 != null) {
                                    rs7.close();
                                }
                            }

                            //menampilkan prosedur tindakan
                            try {
                                rs8 = koneksi.prepareStatement("SELECT pp.kode, i.deskripsi_panjang FROM prosedur_pasien pp INNER JOIN icd9 i ON i.kode = pp.kode "
                                        + "WHERE pp.no_rawat = '" + rs2.getString("no_rawat") + "' and pp.status='Ranap'").executeQuery();
                                
                                if (rs8.next()) {
                                    htmlContent.append(
                                            "<tr class='isi'>"
                                            + "<td valign='top' width='20%'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prosedur Tindakan/ICD-9</td>"
                                            + "<td valign='top' width='1%' align='center'>:</td>"
                                            + "<td valign='top' width='79%'>"
                                            + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                                            + "<tr align='center'>"
                                            + "<td valign='top' width='5%' bgcolor='#f8fdf3'>Kode ICD-9</td>"
                                            + "<td valign='top' width='90%' bgcolor='#f8fdf3'>Deskripsi Tindakan/Prosedur ICD-9</td></tr>");
                                    
                                    rs8.beforeFirst();
                                    w = 1;
                                    while (rs8.next()) {
                                        htmlContent.append(
                                                "<tr>"
                                               +"<td valign='top'>" + rs8.getString("kode") + "</td>"
                                               +"<td valign='top'>" + rs8.getString("deskripsi_panjang") + "</td>"
                                               +"</tr>");
                                        w++;
                                    }
                                    htmlContent.append(
                                            "</table>"
                                            + "</td>"
                                            + "</tr>");
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            } finally {
                                if (rs8 != null) {
                                    rs8.close();
                                }
                            }
                            
                                htmlContent.append(
                                    "</td>"
                                    + "</tr>"
                            );
                            htmlContent.append("<tr class='isi'><td colspan='3' bgcolor='#7eccb9'>&nbsp;</td></tr>");
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                    }
                    y++;
                }
                LoadHTML.setText(
                        "<html>"
                        + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                        + htmlContent.toString()
                        + "</table>"
                        + "</html>");
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        this.setCursor(Cursor.getDefaultCursor());
    }

}
