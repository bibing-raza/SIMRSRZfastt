/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgAdmin.java
 *
 * Created on 21 Jun 10, 20:53:44
 */

package setting;

import fungsi.koneksiDB;
import fungsi.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public class DlgSetAplikasi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int x = 0;
    private String kd_update = "", cekrestart = "", cekip = "";

    /** Creates new form DlgAdmin
     * @param parent
     * @param modal */
    public DlgSetAplikasi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setLocation(10,10);
        setSize(457,249);

        Object[] row = {"Nama RS", "Alamat RS", "Kota", "Propinsi", "Aktifkan?", "Wallpaper", "KontaK", "Email", "Logo RS Warna", "Kode PPK BPJS",
            "Kode PPK Inhealth", "Logo RS Hitam Putih", "Logo Kabupaten", "Logo RS Warna Miring",
            "Auto Restart", "Periode Restart", "Tgl. Restart", "IP Address Tujuan", "IP Eksekusi"
        };
        tabMode = new DefaultTableModel(null, row) {
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbAdmin.setModel(tabMode);
        tbAdmin.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAdmin.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 19; i++) {
            TableColumn column = tbAdmin.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(150);
            } else {
                column.setPreferredWidth(150);
            }
        }

        tbAdmin.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new String[]{"Tgl. Update", "Jam Update", "Versi", "Keterangan", "kode"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbUpdate.setModel(tabMode1);
        tbUpdate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbUpdate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbUpdate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(80);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(95);
            } else if (i == 3) {
                column.setPreferredWidth(800);
            } else if (i == 4) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbUpdate.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbUpdate.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbUpdate.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbUpdate.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        
        tabMode2 = new DefaultTableModel(null, new String[]{"IP Address", "Versi", "Nama Aplikasi", "Waktu Update", "Petugas Login"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbHistoryUpdate.setModel(tabMode2);
        tbHistoryUpdate.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHistoryUpdate.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 5; i++) {
            TableColumn column = tbHistoryUpdate.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(110);
            } else if (i == 1) {
                column.setPreferredWidth(95);
            } else if (i == 2) {
                column.setPreferredWidth(150);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            }
        }
        tbHistoryUpdate.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbHistoryUpdate.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbHistoryUpdate.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        versi.setDocument(new batasInput((byte) 20).getKata(versi));
        Nm.setDocument(new batasInput((byte) 60).getKata(Nm));
//        alamatrs.setDocument(new batasInput((byte)150).getKata(alamatrs));
        Kota.setDocument(new batasInput((byte) 30).getKata(Kota));
        Propinsi.setDocument(new batasInput((byte) 30).getKata(Propinsi));
        Kontak.setDocument(new batasInput((byte) 50).getKata(Kontak));
        Email.setDocument(new batasInput((byte) 50).getKata(Email));
        kdPPK.setDocument(new batasInput((byte) 15).getKata(kdPPK));
        kdPPK1.setDocument(new batasInput((byte) 15).getKata(kdPPK1));
        Tipaddress.setDocument(new batasInput((byte) 15).getKata(Tipaddress));
    }
    
    Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private javax.swing.JFileChooser jfc = new JFileChooser();    
    private javax.swing.JFileChooser jfc2 = new JFileChooser();
    private javax.swing.JFileChooser jfc3 = new JFileChooser();
    private javax.swing.JFileChooser jfc4 = new JFileChooser();
    private javax.swing.JFileChooser jfc5 = new JFileChooser();
    private FileFilter jpgFilter = new FileNameExtensionFilter("Gambar JPEG", "jpg");
    private FileFilter gifFilter = new FileNameExtensionFilter("Gambar GIF", "gif");
    private FileFilter pngFilter = new FileNameExtensionFilter("Gambar PNG", "png");
    private FileFilter bothFilter = new FileNameExtensionFilter("Gambar JPEG dan GIF dan PNG", "jpg", "gif", "png");

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        panelGlass1 = new widget.panelGlass();
        label35 = new widget.Label();
        Nm = new widget.TextBox();
        label34 = new widget.Label();
        label36 = new widget.Label();
        Kota = new widget.TextBox();
        label37 = new widget.Label();
        Propinsi = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        PhotoGambar = new Painter();
        BtnCariGb = new widget.Button();
        EGb = new widget.TextBox();
        label38 = new widget.Label();
        label12 = new widget.Label();
        YesNo = new widget.ComboBox();
        label39 = new widget.Label();
        Kontak = new widget.TextBox();
        label40 = new widget.Label();
        Email = new widget.TextBox();
        scrollPane3 = new widget.ScrollPane();
        PhotoLogo = new Painter();
        label41 = new widget.Label();
        ELogo = new widget.TextBox();
        BtnCariLogo = new widget.Button();
        label42 = new widget.Label();
        kdPPK = new widget.TextBox();
        label43 = new widget.Label();
        kdPPK1 = new widget.TextBox();
        scrollPane4 = new widget.ScrollPane();
        PhotoLogo1 = new Painter();
        label45 = new widget.Label();
        ELogo1 = new widget.TextBox();
        BtnCariLogo1 = new widget.Button();
        alamatrs = new widget.TextBox();
        label46 = new widget.Label();
        scrollPane5 = new widget.ScrollPane();
        PhotoLogo2 = new Painter();
        ELogo2 = new widget.TextBox();
        BtnCariLogo2 = new widget.Button();
        label47 = new widget.Label();
        scrollPane6 = new widget.ScrollPane();
        PhotoLogo3 = new Painter();
        ELogo3 = new widget.TextBox();
        BtnCariLogo3 = new widget.Button();
        label44 = new widget.Label();
        chkRestart = new widget.CekBox();
        label48 = new widget.Label();
        cmbPeriode = new widget.ComboBox();
        label49 = new widget.Label();
        TtglRestart = new widget.Tanggal();
        label54 = new widget.Label();
        chkAddress = new widget.CekBox();
        label55 = new widget.Label();
        Tipaddress = new widget.TextBox();
        BtnSimpanPowerOff = new widget.Button();
        BtnSimpanPowerOff1 = new widget.Button();
        internalFrame2 = new widget.InternalFrame();
        panelisi6 = new widget.panelisi();
        Scroll = new widget.ScrollPane();
        tbAdmin = new widget.Table();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        internalFrame3 = new widget.InternalFrame();
        panelisi2 = new widget.panelisi();
        Scroll2 = new widget.ScrollPane();
        tbHistoryUpdate = new widget.Table();
        panelisi3 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnCari = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        Scroll21 = new widget.ScrollPane();
        ket_update = new widget.TextArea();
        Scroll1 = new widget.ScrollPane();
        tbUpdate = new widget.Table();
        panelisi5 = new widget.panelisi();
        jLabel24 = new widget.Label();
        versi = new widget.TextBox();
        jLabel23 = new widget.Label();
        tglUpdate = new widget.Tanggal();
        jLabel25 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        BtnSimpan1 = new widget.Button();
        BtnBaru1 = new widget.Button();
        BtnHapus1 = new widget.Button();
        BtnGanti1 = new widget.Button();
        BtnCari1 = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Setup Aplikasi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass1.setName("panelGlass1"); // NOI18N
        panelGlass1.setPreferredSize(new java.awt.Dimension(200, 290));
        panelGlass1.setLayout(null);

        label35.setForeground(new java.awt.Color(0, 0, 0));
        label35.setText("Nama RS :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label35);
        label35.setBounds(0, 10, 70, 23);

        Nm.setForeground(new java.awt.Color(0, 0, 0));
        Nm.setHighlighter(null);
        Nm.setName("Nm"); // NOI18N
        Nm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmKeyPressed(evt);
            }
        });
        panelGlass1.add(Nm);
        Nm.setBounds(75, 10, 320, 23);

        label34.setForeground(new java.awt.Color(0, 0, 0));
        label34.setText("Alamat RS :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label34);
        label34.setBounds(0, 40, 70, 23);

        label36.setForeground(new java.awt.Color(0, 0, 0));
        label36.setText("Kota :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label36);
        label36.setBounds(0, 70, 70, 23);

        Kota.setForeground(new java.awt.Color(0, 0, 0));
        Kota.setName("Kota"); // NOI18N
        Kota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KotaKeyPressed(evt);
            }
        });
        panelGlass1.add(Kota);
        Kota.setBounds(75, 70, 320, 23);

        label37.setForeground(new java.awt.Color(0, 0, 0));
        label37.setText("Wallpaper :");
        label37.setName("label37"); // NOI18N
        label37.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label37);
        label37.setBounds(0, 130, 70, 23);

        Propinsi.setForeground(new java.awt.Color(0, 0, 0));
        Propinsi.setName("Propinsi"); // NOI18N
        Propinsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PropinsiKeyPressed(evt);
            }
        });
        panelGlass1.add(Propinsi);
        Propinsi.setBounds(75, 100, 320, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        PhotoGambar.setBackground(new java.awt.Color(245, 255, 235));
        PhotoGambar.setForeground(new java.awt.Color(235, 255, 235));
        PhotoGambar.setName("PhotoGambar"); // NOI18N
        scrollPane2.setViewportView(PhotoGambar);

        panelGlass1.add(scrollPane2);
        scrollPane2.setBounds(402, 10, 233, 233);

        BtnCariGb.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariGb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariGb.setMnemonic('C');
        BtnCariGb.setToolTipText("Alt+C");
        BtnCariGb.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariGb.setName("BtnCariGb"); // NOI18N
        BtnCariGb.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariGb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariGbActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnCariGb);
        BtnCariGb.setBounds(370, 130, 25, 23);

        EGb.setEditable(false);
        EGb.setForeground(new java.awt.Color(0, 0, 0));
        EGb.setName("EGb"); // NOI18N
        EGb.setPreferredSize(new java.awt.Dimension(207, 23));
        EGb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EGbKeyPressed(evt);
            }
        });
        panelGlass1.add(EGb);
        EGb.setBounds(75, 130, 290, 23);

        label38.setForeground(new java.awt.Color(0, 0, 0));
        label38.setText("Propinsi :");
        label38.setName("label38"); // NOI18N
        label38.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label38);
        label38.setBounds(0, 100, 70, 23);

        label12.setForeground(new java.awt.Color(0, 0, 0));
        label12.setText("Mau Aktifkan Wallpaper ?");
        label12.setName("label12"); // NOI18N
        label12.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass1.add(label12);
        label12.setBounds(0, 160, 220, 23);

        YesNo.setForeground(new java.awt.Color(0, 0, 0));
        YesNo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Yes", "No" }));
        YesNo.setName("YesNo"); // NOI18N
        YesNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                YesNoKeyPressed(evt);
            }
        });
        panelGlass1.add(YesNo);
        YesNo.setBounds(225, 160, 70, 23);

        label39.setForeground(new java.awt.Color(0, 0, 0));
        label39.setText("Kontak :");
        label39.setName("label39"); // NOI18N
        label39.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label39);
        label39.setBounds(0, 190, 70, 23);

        Kontak.setForeground(new java.awt.Color(0, 0, 0));
        Kontak.setName("Kontak"); // NOI18N
        Kontak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KontakKeyPressed(evt);
            }
        });
        panelGlass1.add(Kontak);
        Kontak.setBounds(75, 190, 320, 23);

        label40.setForeground(new java.awt.Color(0, 0, 0));
        label40.setText("Email :");
        label40.setName("label40"); // NOI18N
        label40.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label40);
        label40.setBounds(0, 220, 70, 23);

        Email.setForeground(new java.awt.Color(0, 0, 0));
        Email.setName("Email"); // NOI18N
        Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                EmailKeyPressed(evt);
            }
        });
        panelGlass1.add(Email);
        Email.setBounds(75, 220, 320, 23);

        scrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane3.setName("scrollPane3"); // NOI18N

        PhotoLogo.setBackground(new java.awt.Color(245, 255, 235));
        PhotoLogo.setForeground(new java.awt.Color(235, 255, 235));
        PhotoLogo.setName("PhotoLogo"); // NOI18N
        PhotoLogo.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane3.setViewportView(PhotoLogo);

        panelGlass1.add(scrollPane3);
        scrollPane3.setBounds(650, 35, 90, 110);

        label41.setForeground(new java.awt.Color(0, 0, 0));
        label41.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label41.setText("Logo RS Warna");
        label41.setName("label41"); // NOI18N
        label41.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label41);
        label41.setBounds(650, 10, 90, 23);

        ELogo.setEditable(false);
        ELogo.setForeground(new java.awt.Color(0, 0, 0));
        ELogo.setText("- pilih logo RS warna -");
        ELogo.setName("ELogo"); // NOI18N
        ELogo.setPreferredSize(new java.awt.Dimension(207, 23));
        ELogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ELogoKeyPressed(evt);
            }
        });
        panelGlass1.add(ELogo);
        ELogo.setBounds(650, 150, 171, 23);

        BtnCariLogo.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariLogo.setMnemonic('C');
        BtnCariLogo.setToolTipText("Alt+C");
        BtnCariLogo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariLogo.setName("BtnCariLogo"); // NOI18N
        BtnCariLogo.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariLogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariLogoActionPerformed(evt);
            }
        });
        BtnCariLogo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariLogoKeyPressed(evt);
            }
        });
        panelGlass1.add(BtnCariLogo);
        BtnCariLogo.setBounds(828, 150, 25, 23);

        label42.setForeground(new java.awt.Color(0, 0, 0));
        label42.setText("Kode PPK BPJS :");
        label42.setName("label42"); // NOI18N
        label42.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label42);
        label42.setBounds(0, 250, 100, 23);

        kdPPK.setForeground(new java.awt.Color(0, 0, 0));
        kdPPK.setName("kdPPK"); // NOI18N
        kdPPK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdPPKKeyPressed(evt);
            }
        });
        panelGlass1.add(kdPPK);
        kdPPK.setBounds(105, 250, 110, 23);

        label43.setForeground(new java.awt.Color(0, 0, 0));
        label43.setText("Kode PPK Inhealth :");
        label43.setName("label43"); // NOI18N
        label43.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label43);
        label43.setBounds(225, 250, 120, 23);

        kdPPK1.setForeground(new java.awt.Color(0, 0, 0));
        kdPPK1.setName("kdPPK1"); // NOI18N
        kdPPK1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdPPK1KeyPressed(evt);
            }
        });
        panelGlass1.add(kdPPK1);
        kdPPK1.setBounds(350, 250, 110, 23);

        scrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane4.setName("scrollPane4"); // NOI18N

        PhotoLogo1.setBackground(new java.awt.Color(245, 255, 235));
        PhotoLogo1.setForeground(new java.awt.Color(235, 255, 235));
        PhotoLogo1.setName("PhotoLogo1"); // NOI18N
        PhotoLogo1.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane4.setViewportView(PhotoLogo1);

        panelGlass1.add(scrollPane4);
        scrollPane4.setBounds(760, 35, 90, 110);

        label45.setForeground(new java.awt.Color(0, 0, 0));
        label45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label45.setText("Logo RS Hitam Putih");
        label45.setName("label45"); // NOI18N
        label45.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label45);
        label45.setBounds(750, 10, 110, 23);

        ELogo1.setEditable(false);
        ELogo1.setForeground(new java.awt.Color(0, 0, 0));
        ELogo1.setText("- pilih logo RS hitam putih -");
        ELogo1.setName("ELogo1"); // NOI18N
        ELogo1.setPreferredSize(new java.awt.Dimension(207, 23));
        ELogo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ELogo1KeyPressed(evt);
            }
        });
        panelGlass1.add(ELogo1);
        ELogo1.setBounds(650, 179, 171, 23);

        BtnCariLogo1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariLogo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariLogo1.setMnemonic('C');
        BtnCariLogo1.setToolTipText("Alt+C");
        BtnCariLogo1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariLogo1.setName("BtnCariLogo1"); // NOI18N
        BtnCariLogo1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariLogo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariLogo1ActionPerformed(evt);
            }
        });
        BtnCariLogo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariLogo1KeyPressed(evt);
            }
        });
        panelGlass1.add(BtnCariLogo1);
        BtnCariLogo1.setBounds(828, 179, 25, 23);

        alamatrs.setForeground(new java.awt.Color(0, 0, 0));
        alamatrs.setName("alamatrs"); // NOI18N
        alamatrs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                alamatrsKeyPressed(evt);
            }
        });
        panelGlass1.add(alamatrs);
        alamatrs.setBounds(75, 40, 320, 23);

        label46.setForeground(new java.awt.Color(0, 0, 0));
        label46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label46.setText("Logo Kabupaten");
        label46.setName("label46"); // NOI18N
        label46.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label46);
        label46.setBounds(870, 10, 90, 23);

        scrollPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane5.setName("scrollPane5"); // NOI18N

        PhotoLogo2.setBackground(new java.awt.Color(245, 255, 235));
        PhotoLogo2.setForeground(new java.awt.Color(235, 255, 235));
        PhotoLogo2.setName("PhotoLogo2"); // NOI18N
        PhotoLogo2.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane5.setViewportView(PhotoLogo2);

        panelGlass1.add(scrollPane5);
        scrollPane5.setBounds(870, 35, 90, 110);

        ELogo2.setEditable(false);
        ELogo2.setForeground(new java.awt.Color(0, 0, 0));
        ELogo2.setText("- pilih logo kabupaten -");
        ELogo2.setName("ELogo2"); // NOI18N
        ELogo2.setPreferredSize(new java.awt.Dimension(207, 23));
        ELogo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ELogo2KeyPressed(evt);
            }
        });
        panelGlass1.add(ELogo2);
        ELogo2.setBounds(865, 150, 171, 23);

        BtnCariLogo2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariLogo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariLogo2.setMnemonic('C');
        BtnCariLogo2.setToolTipText("Alt+C");
        BtnCariLogo2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariLogo2.setName("BtnCariLogo2"); // NOI18N
        BtnCariLogo2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariLogo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariLogo2ActionPerformed(evt);
            }
        });
        BtnCariLogo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariLogo2KeyPressed(evt);
            }
        });
        panelGlass1.add(BtnCariLogo2);
        BtnCariLogo2.setBounds(1037, 150, 25, 23);

        label47.setForeground(new java.awt.Color(0, 0, 0));
        label47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label47.setText("Logo RS Warna (Miring)");
        label47.setName("label47"); // NOI18N
        label47.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label47);
        label47.setBounds(978, 10, 160, 23);

        scrollPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane6.setName("scrollPane6"); // NOI18N

        PhotoLogo3.setBackground(new java.awt.Color(245, 255, 235));
        PhotoLogo3.setForeground(new java.awt.Color(235, 255, 235));
        PhotoLogo3.setName("PhotoLogo3"); // NOI18N
        PhotoLogo3.setPreferredSize(new java.awt.Dimension(85, 90));
        scrollPane6.setViewportView(PhotoLogo3);

        panelGlass1.add(scrollPane6);
        scrollPane6.setBounds(985, 35, 150, 110);

        ELogo3.setEditable(false);
        ELogo3.setForeground(new java.awt.Color(0, 0, 0));
        ELogo3.setText("- pilih logo RS warna miring -");
        ELogo3.setName("ELogo3"); // NOI18N
        ELogo3.setPreferredSize(new java.awt.Dimension(207, 23));
        ELogo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ELogo3KeyPressed(evt);
            }
        });
        panelGlass1.add(ELogo3);
        ELogo3.setBounds(865, 179, 171, 23);

        BtnCariLogo3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCariLogo3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCariLogo3.setMnemonic('C');
        BtnCariLogo3.setToolTipText("Alt+C");
        BtnCariLogo3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        BtnCariLogo3.setName("BtnCariLogo3"); // NOI18N
        BtnCariLogo3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCariLogo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariLogo3ActionPerformed(evt);
            }
        });
        BtnCariLogo3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariLogo3KeyPressed(evt);
            }
        });
        panelGlass1.add(BtnCariLogo3);
        BtnCariLogo3.setBounds(1037, 179, 25, 23);

        label44.setForeground(new java.awt.Color(0, 0, 0));
        label44.setText("Auto Restart :");
        label44.setName("label44"); // NOI18N
        label44.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label44);
        label44.setBounds(1140, 10, 100, 23);

        chkRestart.setBackground(new java.awt.Color(255, 255, 250));
        chkRestart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkRestart.setForeground(new java.awt.Color(0, 0, 0));
        chkRestart.setText("Restart");
        chkRestart.setBorderPainted(true);
        chkRestart.setBorderPaintedFlat(true);
        chkRestart.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkRestart.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkRestart.setName("chkRestart"); // NOI18N
        chkRestart.setOpaque(false);
        chkRestart.setPreferredSize(new java.awt.Dimension(175, 23));
        chkRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRestartActionPerformed(evt);
            }
        });
        panelGlass1.add(chkRestart);
        chkRestart.setBounds(1245, 10, 80, 23);

        label48.setForeground(new java.awt.Color(0, 0, 0));
        label48.setText("Periode Restart :");
        label48.setName("label48"); // NOI18N
        label48.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label48);
        label48.setBounds(1140, 38, 100, 23);

        cmbPeriode.setForeground(new java.awt.Color(0, 0, 0));
        cmbPeriode.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tanggal", "Setiap Hari" }));
        cmbPeriode.setName("cmbPeriode"); // NOI18N
        cmbPeriode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPeriodeActionPerformed(evt);
            }
        });
        panelGlass1.add(cmbPeriode);
        cmbPeriode.setBounds(1245, 38, 85, 23);

        label49.setForeground(new java.awt.Color(0, 0, 0));
        label49.setText("Tgl. Restart :");
        label49.setName("label49"); // NOI18N
        label49.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label49);
        label49.setBounds(1140, 66, 100, 23);

        TtglRestart.setEditable(false);
        TtglRestart.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-10-2025" }));
        TtglRestart.setDisplayFormat("dd-MM-yyyy");
        TtglRestart.setName("TtglRestart"); // NOI18N
        TtglRestart.setOpaque(false);
        TtglRestart.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass1.add(TtglRestart);
        TtglRestart.setBounds(1245, 66, 90, 23);

        label54.setForeground(new java.awt.Color(0, 0, 0));
        label54.setText("Set IP Address :");
        label54.setName("label54"); // NOI18N
        label54.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label54);
        label54.setBounds(1130, 94, 110, 23);

        chkAddress.setBackground(new java.awt.Color(255, 255, 250));
        chkAddress.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkAddress.setForeground(new java.awt.Color(0, 0, 0));
        chkAddress.setText("IP Address");
        chkAddress.setBorderPainted(true);
        chkAddress.setBorderPaintedFlat(true);
        chkAddress.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkAddress.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkAddress.setName("chkAddress"); // NOI18N
        chkAddress.setOpaque(false);
        chkAddress.setPreferredSize(new java.awt.Dimension(175, 23));
        chkAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAddressActionPerformed(evt);
            }
        });
        panelGlass1.add(chkAddress);
        chkAddress.setBounds(1245, 94, 80, 23);

        label55.setForeground(new java.awt.Color(0, 0, 0));
        label55.setText("IP Address Tujuan :");
        label55.setName("label55"); // NOI18N
        label55.setPreferredSize(new java.awt.Dimension(35, 23));
        panelGlass1.add(label55);
        label55.setBounds(1130, 122, 110, 23);

        Tipaddress.setForeground(new java.awt.Color(0, 0, 0));
        Tipaddress.setName("Tipaddress"); // NOI18N
        panelGlass1.add(Tipaddress);
        Tipaddress.setBounds(1245, 122, 110, 23);

        BtnSimpanPowerOff.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPowerOff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clock.png"))); // NOI18N
        BtnSimpanPowerOff.setMnemonic('G');
        BtnSimpanPowerOff.setText("Set Pengaturan Power Off");
        BtnSimpanPowerOff.setToolTipText("Alt+G");
        BtnSimpanPowerOff.setIconTextGap(3);
        BtnSimpanPowerOff.setName("BtnSimpanPowerOff"); // NOI18N
        BtnSimpanPowerOff.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanPowerOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPowerOffActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnSimpanPowerOff);
        BtnSimpanPowerOff.setBounds(1140, 160, 200, 30);

        BtnSimpanPowerOff1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpanPowerOff1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnSimpanPowerOff1.setMnemonic('G');
        BtnSimpanPowerOff1.setText("Reset Pengaturan Power Off");
        BtnSimpanPowerOff1.setToolTipText("Alt+G");
        BtnSimpanPowerOff1.setIconTextGap(3);
        BtnSimpanPowerOff1.setName("BtnSimpanPowerOff1"); // NOI18N
        BtnSimpanPowerOff1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpanPowerOff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanPowerOff1ActionPerformed(evt);
            }
        });
        panelGlass1.add(BtnSimpanPowerOff1);
        BtnSimpanPowerOff1.setBounds(1140, 200, 220, 30);

        internalFrame1.add(panelGlass1, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 125));
        panelisi6.setLayout(new java.awt.BorderLayout());

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 90));

        tbAdmin.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbAdmin.setName("tbAdmin"); // NOI18N
        tbAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAdminMouseClicked(evt);
            }
        });
        tbAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAdminKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAdmin);

        panelisi6.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 54));
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
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnHapus);

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setIconTextGap(3);
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelisi1.add(BtnEdit);

        panelisi6.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        internalFrame2.add(panelisi6, java.awt.BorderLayout.PAGE_START);

        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout());

        panelisi2.setName("panelisi2"); // NOI18N
        panelisi2.setPreferredSize(new java.awt.Dimension(720, 54));
        panelisi2.setLayout(new java.awt.BorderLayout());

        Scroll2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "::[ History Update ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);
        Scroll2.setPreferredSize(new java.awt.Dimension(452, 400));

        tbHistoryUpdate.setAutoCreateRowSorter(true);
        tbHistoryUpdate.setName("tbHistoryUpdate"); // NOI18N
        tbHistoryUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHistoryUpdateMouseClicked(evt);
            }
        });
        tbHistoryUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbHistoryUpdateKeyPressed(evt);
            }
        });
        Scroll2.setViewportView(tbHistoryUpdate);

        panelisi2.add(Scroll2, java.awt.BorderLayout.CENTER);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 47));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(180, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 30));
        panelisi3.add(jLabel7);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(45, 30));
        panelisi3.add(LCount);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+4");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 30));
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
        panelisi3.add(BtnCari);

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
        panelisi3.add(BtnKeluar);

        panelisi2.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        internalFrame3.add(panelisi2, java.awt.BorderLayout.EAST);

        panelisi4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "::[ Daftar Catatan Update ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 54));
        panelisi4.setLayout(new java.awt.BorderLayout());

        Scroll21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, " Kalimat / Deskripsi Update ", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        Scroll21.setName("Scroll21"); // NOI18N
        Scroll21.setOpaque(true);

        ket_update.setColumns(20);
        ket_update.setRows(5);
        ket_update.setName("ket_update"); // NOI18N
        ket_update.setPreferredSize(new java.awt.Dimension(170, 1000));
        ket_update.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ket_updateKeyPressed(evt);
            }
        });
        Scroll21.setViewportView(ket_update);

        panelisi4.add(Scroll21, java.awt.BorderLayout.PAGE_START);

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(452, 400));

        tbUpdate.setAutoCreateRowSorter(true);
        tbUpdate.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbUpdate.setName("tbUpdate"); // NOI18N
        tbUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbUpdateMouseClicked(evt);
            }
        });
        tbUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbUpdateKeyPressed(evt);
            }
        });
        Scroll1.setViewportView(tbUpdate);

        panelisi4.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 54));
        panelisi5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Versi :");
        jLabel24.setName("jLabel24"); // NOI18N
        jLabel24.setPreferredSize(new java.awt.Dimension(45, 24));
        panelisi5.add(jLabel24);

        versi.setForeground(new java.awt.Color(0, 0, 0));
        versi.setName("versi"); // NOI18N
        versi.setPreferredSize(new java.awt.Dimension(95, 24));
        versi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                versiKeyPressed(evt);
            }
        });
        panelisi5.add(versi);

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Tgl. :");
        jLabel23.setName("jLabel23"); // NOI18N
        jLabel23.setPreferredSize(new java.awt.Dimension(40, 24));
        panelisi5.add(jLabel23);

        tglUpdate.setEditable(false);
        tglUpdate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "14-10-2025" }));
        tglUpdate.setDisplayFormat("dd-MM-yyyy");
        tglUpdate.setName("tglUpdate"); // NOI18N
        tglUpdate.setOpaque(false);
        tglUpdate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tglUpdateKeyPressed(evt);
            }
        });
        panelisi5.add(tglUpdate);

        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Jam :");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(40, 24));
        panelisi5.add(jLabel25);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(45, 24));
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        panelisi5.add(cmbJam);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(45, 24));
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        panelisi5.add(cmbMnt);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(45, 24));
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        panelisi5.add(cmbDtk);

        BtnSimpan1.setForeground(new java.awt.Color(0, 0, 0));
        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setText("Simpan");
        BtnSimpan1.setToolTipText("Simpan Update");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnSimpan1);

        BtnBaru1.setForeground(new java.awt.Color(0, 0, 0));
        BtnBaru1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBaru1.setText("Baru");
        BtnBaru1.setToolTipText("Data Baru Update");
        BtnBaru1.setName("BtnBaru1"); // NOI18N
        BtnBaru1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBaru1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBaru1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnBaru1);

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus1.setText("Hapus");
        BtnHapus1.setToolTipText("Hapus Data Update");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnHapus1);

        BtnGanti1.setForeground(new java.awt.Color(0, 0, 0));
        BtnGanti1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGanti1.setText("Ganti");
        BtnGanti1.setToolTipText("Ganti Data Update");
        BtnGanti1.setName("BtnGanti1"); // NOI18N
        BtnGanti1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnGanti1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGanti1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnGanti1);

        BtnCari1.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari1.setText("Tampilkan Update");
        BtnCari1.setToolTipText("");
        BtnCari1.setName("BtnCari1"); // NOI18N
        BtnCari1.setPreferredSize(new java.awt.Dimension(150, 30));
        BtnCari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari1ActionPerformed(evt);
            }
        });
        panelisi5.add(BtnCari1);

        panelisi4.add(panelisi5, java.awt.BorderLayout.PAGE_END);

        internalFrame3.add(panelisi4, java.awt.BorderLayout.CENTER);

        internalFrame2.add(internalFrame3, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmKeyPressed
        Valid.pindah(evt,BtnSimpan,alamatrs);
}//GEN-LAST:event_NmKeyPressed

    private void tbAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAdminMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAdminMouseClicked

    private void tbAdminKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAdminKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAdminKeyPressed

    private void KotaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KotaKeyPressed
        Valid.pindah(evt,alamatrs,Propinsi);
    }//GEN-LAST:event_KotaKeyPressed

    private void PropinsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PropinsiKeyPressed
        Valid.pindah(evt,Kota,YesNo);
    }//GEN-LAST:event_PropinsiKeyPressed

    private void BtnCariGbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariGbActionPerformed
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.addChoosableFileFilter(jpgFilter);
        jfc.addChoosableFileFilter(gifFilter);
        jfc.addChoosableFileFilter(pngFilter);
        jfc.addChoosableFileFilter(bothFilter);
        if (jfc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc.getSelectedFile().toString();
            EGb.setText(f);
            //lGambar.setIcon(new ImageIcon(f));
            ((Painter) PhotoGambar).setImage(f);

        }
    }//GEN-LAST:event_BtnCariGbActionPerformed

    private void EGbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EGbKeyPressed
        Valid.pindah(evt,Propinsi,BtnSimpan);
    }//GEN-LAST:event_EGbKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (Nm.getText().trim().equals("")) {
            Valid.textKosong(Nm, "Nama Rumah Sakit");
        } else if (EGb.getText().trim().equals("")) {
            YesNo.setSelectedItem("No");
            EGb.setText("./setting/wallpaper.jpg");
        } else if (ELogo.getText().trim().equals("") || (ELogo.getText().trim().equals("- pilih logo RS warna -"))) {
            YesNo.setSelectedItem("No");
            ELogo.setText("./setting/logo1.jpg");
        } else if (ELogo1.getText().trim().equals("") || (ELogo1.getText().trim().equals("- pilih logo RS hitam putih -"))) {
            YesNo.setSelectedItem("No");
            ELogo1.setText("./setting/logo.jpg");
        } else if (ELogo2.getText().trim().equals("") || (ELogo2.getText().trim().equals("- pilih logo kabupaten -"))) {
            YesNo.setSelectedItem("No");
            ELogo2.setText("./setting/logokab.jpg");
        } else if (ELogo3.getText().trim().equals("") || (ELogo3.getText().trim().equals("- pilih logo RS warna miring -"))) {
            YesNo.setSelectedItem("No");
            ELogo3.setText("./setting/logorswarnamiring.jpg");
        } else if (chkAddress.isSelected() == true && Tipaddress.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "IP address tujuan harus diisi dulu,...!!!");
            Tipaddress.requestFocus();
        } else if (tabMode.getRowCount() == 0) {
            if (chkRestart.isSelected() == true) {
                cekrestart = "ya";
            } else {
                cekrestart = "tidak";
            }
            
            if (chkAddress.isSelected() == true) {
                cekip = "ya";
            } else {
                cekip = "tidak";
            }
            
            Sequel.menyimpan2logo("setting", "'" + Nm.getText() + "','" + alamatrs.getText() + "','" + Kota.getText()
                    + "','" + Propinsi.getText() + "','" + Kontak.getText() + "','" + Email.getText()
                    + "','" + YesNo.getSelectedItem() + "','" + kdPPK.getText() + "','" + kdPPK1.getText() + "'", "Setting",
                    EGb, ELogo, ELogo1, ELogo2, ELogo3, "'" + cekrestart + "','" + cmbPeriode.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(TtglRestart.getSelectedItem() + "") + "','" + cekip + "','" + Tipaddress.getText() + "'");
            tampil();
        } else if (tabMode.getRowCount() > 0) {
            JOptionPane.showMessageDialog(null, "Maaf, Hanya diijinkan satu Set Aplikasi...!!!!");
            Nm.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Email,BtnBatal);
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
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            Nm.requestFocus();
        } else if (Nm.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else if (!Nm.getText().trim().equals("")) {
            Sequel.queryu("delete from setting ");
            tampil();
            emptTeks();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnHapus);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
          dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnHapus, BtnSimpan);
        }
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void YesNoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_YesNoKeyPressed
        Valid.pindah(evt, Propinsi,Kontak);
    }//GEN-LAST:event_YesNoKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        tampilUpdate();
        TCari.setText(Sequel.cariIsi("select ifnull(versi,'') FROM history_aplikasi ORDER BY waktu_update desc limit 1"));
        tampilHistori();
    }//GEN-LAST:event_formWindowOpened

    private void KontakKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KontakKeyPressed
        Valid.pindah(evt,YesNo,Email);
    }//GEN-LAST:event_KontakKeyPressed

    private void EmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailKeyPressed
        Valid.pindah(evt,Kontak,kdPPK);
    }//GEN-LAST:event_EmailKeyPressed

    private void ELogoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ELogoKeyPressed
        Valid.pindah(evt,kdPPK,BtnSimpan);
    }//GEN-LAST:event_ELogoKeyPressed

    private void BtnCariLogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariLogoActionPerformed
        jfc2.setAcceptAllFileFilterUsed(false);
        jfc2.addChoosableFileFilter(jpgFilter);
        jfc2.addChoosableFileFilter(gifFilter);
        jfc2.addChoosableFileFilter(pngFilter);
        jfc2.addChoosableFileFilter(bothFilter);
        if (jfc2.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc2.getSelectedFile().toString();
            ELogo.setText(f);
            //lGambar.setIcon(new ImageIcon(f));
            ((Painter) PhotoLogo).setImage(f);
        }
    }//GEN-LAST:event_BtnCariLogoActionPerformed

    private void BtnCariLogoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariLogoKeyPressed
        Valid.pindah(evt,Email,BtnSimpan);
    }//GEN-LAST:event_BtnCariLogoKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (Nm.getText().trim().equals("")) {
            Valid.textKosong(Nm, "Nama Rumah Sakit");
        } else if (EGb.getText().trim().equals("")) {
            YesNo.setSelectedItem("No");
            EGb.setText("./setting/wallpaper.jpg");
        } else if (ELogo.getText().trim().equals("") || (ELogo.getText().trim().equals("- pilih logo RS warna -"))) {
            YesNo.setSelectedItem("No");
            ELogo.setText("./setting/logo1.jpg");
        } else if (ELogo1.getText().trim().equals("") || (ELogo1.getText().trim().equals("- pilih logo RS hitam putih -"))) {
            YesNo.setSelectedItem("No");
            ELogo1.setText("./setting/logo.jpg");
        } else if (ELogo2.getText().trim().equals("") || (ELogo2.getText().trim().equals("- pilih logo kabupaten -"))) {
            YesNo.setSelectedItem("No");
            ELogo2.setText("./setting/logokab.jpg");
        } else if (ELogo3.getText().trim().equals("") || (ELogo3.getText().trim().equals("- pilih logo RS warna miring -"))) {
            YesNo.setSelectedItem("No");
            ELogo3.setText("./setting/logorswarnamiring.jpg");
        } else if (chkAddress.isSelected() == true && Tipaddress.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "IP address tujuan harus diisi dulu,...!!!");
            Tipaddress.requestFocus();
        } else {
            if (chkRestart.isSelected() == true) {
                cekrestart = "ya";
            } else {
                cekrestart = "tidak";
            }
            
            if (chkAddress.isSelected() == true) {
                cekip = "ya";
            } else {
                cekip = "tidak";
            }
            
            Sequel.queryu("delete from setting ");
            Sequel.menyimpan2logo("setting", "'" + Nm.getText() + "','" + alamatrs.getText() + "','" + Kota.getText()
                    + "','" + Propinsi.getText() + "','" + Kontak.getText() + "','" + Email.getText()
                    + "','" + YesNo.getSelectedItem() + "','" + kdPPK.getText() + "','" + kdPPK1.getText() + "'", "Setting",
                    EGb, ELogo, ELogo1, ELogo2, ELogo3, "'" + cekrestart + "','" + cmbPeriode.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(TtglRestart.getSelectedItem() + "") + "','" + cekip + "','" + Tipaddress.getText() + "'");
            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void kdPPKKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdPPKKeyPressed
        Valid.pindah(evt,Email,kdPPK1);
    }//GEN-LAST:event_kdPPKKeyPressed

    private void kdPPK1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdPPK1KeyPressed
        Valid.pindah(evt,kdPPK,BtnCariLogo);
    }//GEN-LAST:event_kdPPK1KeyPressed

    private void ket_updateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ket_updateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ket_updateKeyPressed

    private void tglUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tglUpdateKeyPressed
       
    }//GEN-LAST:event_tglUpdateKeyPressed

    private void tbUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbUpdateMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                getdataUpdate();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbUpdateMouseClicked

    private void tbUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbUpdateKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getdataUpdate();
                } catch (java.lang.NullPointerException e) {
                }
            } else if (evt.getKeyCode() == KeyEvent.VK_V) {
                if (tbUpdate.getSelectedColumn() > 4) {
                    if (tbUpdate.getSelectedRow() != -1) {
                        if (tbUpdate.getValueAt(tbUpdate.getSelectedRow(), tbUpdate.getSelectedColumn()).toString().equals("false")) {
                            tbUpdate.setValueAt(true, tbUpdate.getSelectedRow(), tbUpdate.getSelectedColumn());
                        } else {
                            tbUpdate.setValueAt(false, tbUpdate.getSelectedRow(), tbUpdate.getSelectedColumn());
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_tbUpdateKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if (versi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Versi update aplikasi harus diisi..!!!!");
            versi.requestFocus();
        } else if (ket_update.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan update aplikasi harus diisi..!!!!");
            ket_update.requestFocus();
        } else {
            Sequel.menyimpan("history_update", "'" + versi.getText() + "','" + Valid.SetTgl(tglUpdate.getSelectedItem() + "") + "',"
                    + "'" + ket_update.getText() + "','0','" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "'");
            
            frmUtama utama=frmUtama.getInstance();
            utama.Tversi.setText(versi.getText());            
            tampilUpdate();
            emptUpdate();
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnBaru1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBaru1ActionPerformed
        emptUpdate();
    }//GEN-LAST:event_BtnBaru1ActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Maaf, data sudah habis...!!!!");
            tbUpdate.requestFocus();
        } else if (kd_update.equals("")) {
            JOptionPane.showMessageDialog(null, "Maaf, Gagal menghapus. Pilih dulu data update yang mau dihapus. Klik data pada tabel untuk memilih...!!!!");
        } else {
            Sequel.queryu("delete from history_update where kode='" + kd_update + "' ");
            tampilUpdate();
            emptUpdate();
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnCari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari1ActionPerformed
        tampilUpdate();
    }//GEN-LAST:event_BtnCari1ActionPerformed

    private void BtnGanti1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGanti1ActionPerformed
        if (kd_update.equals("")) {
            JOptionPane.showMessageDialog(null, "Pilih dulu salah satu versi update yg. akan diperbaiki..!!!!");
            tbUpdate.requestFocus();
        } else if (versi.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Versi update aplikasi harus diisi..!!!!");
            versi.requestFocus();
        } else if (ket_update.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Keterangan update aplikasi harus diisi..!!!!");
            ket_update.requestFocus();
        } else {
            Sequel.mengedit("history_update", "kode='" + kd_update + "'", "versi_update='" + versi.getText() + "', keterangan='" + ket_update.getText() + "', "
                    + "tgl_update='" + Valid.SetTgl(tglUpdate.getSelectedItem() + "") + "',"
                    + "jam_update='" + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "' ");
            
            frmUtama utama=frmUtama.getInstance();
            utama.Tversi.setText(versi.getText());            
            tampilUpdate();
            emptUpdate();
        }
    }//GEN-LAST:event_BtnGanti1ActionPerformed

    private void versiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_versiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_versiKeyPressed

    private void ELogo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ELogo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELogo1KeyPressed

    private void BtnCariLogo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariLogo1ActionPerformed
        jfc3.setAcceptAllFileFilterUsed(false);
        jfc3.addChoosableFileFilter(jpgFilter);
        jfc3.addChoosableFileFilter(gifFilter);
        jfc3.addChoosableFileFilter(pngFilter);
        jfc3.addChoosableFileFilter(bothFilter);
        if (jfc3.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc3.getSelectedFile().toString();
            ELogo1.setText(f);
            ((Painter) PhotoLogo1).setImage(f);
        }
    }//GEN-LAST:event_BtnCariLogo1ActionPerformed

    private void BtnCariLogo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariLogo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariLogo1KeyPressed

    private void alamatrsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatrsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatrsKeyPressed

    private void ELogo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ELogo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELogo2KeyPressed

    private void BtnCariLogo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariLogo2ActionPerformed
        jfc4.setAcceptAllFileFilterUsed(false);
        jfc4.addChoosableFileFilter(jpgFilter);
        jfc4.addChoosableFileFilter(gifFilter);
        jfc4.addChoosableFileFilter(pngFilter);
        jfc4.addChoosableFileFilter(bothFilter);
        if (jfc4.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc4.getSelectedFile().toString();
            ELogo2.setText(f);
            ((Painter) PhotoLogo2).setImage(f);
        }
    }//GEN-LAST:event_BtnCariLogo2ActionPerformed

    private void BtnCariLogo2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariLogo2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariLogo2KeyPressed

    private void ELogo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ELogo3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ELogo3KeyPressed

    private void BtnCariLogo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariLogo3ActionPerformed
        jfc5.setAcceptAllFileFilterUsed(false);
        jfc5.addChoosableFileFilter(jpgFilter);
        jfc5.addChoosableFileFilter(gifFilter);
        jfc5.addChoosableFileFilter(pngFilter);
        jfc5.addChoosableFileFilter(bothFilter);
        if (jfc5.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String f = jfc5.getSelectedFile().toString();
            ELogo3.setText(f);
            ((Painter) PhotoLogo3).setImage(f);
        }
    }//GEN-LAST:event_BtnCariLogo3ActionPerformed

    private void BtnCariLogo3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariLogo3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCariLogo3KeyPressed

    private void tbHistoryUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHistoryUpdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHistoryUpdateMouseClicked

    private void tbHistoryUpdateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbHistoryUpdateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tbHistoryUpdateKeyPressed

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
        tampilHistori();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_BtnCariKeyPressed

    private void chkRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRestartActionPerformed
        TtglRestart.setDate(new Date());
        if (chkRestart.isSelected() == true) {
            cmbPeriode.setEnabled(true);
            TtglRestart.setEnabled(true);
        } else {
            cmbPeriode.setEnabled(false);
            TtglRestart.setEnabled(false);
        }
    }//GEN-LAST:event_chkRestartActionPerformed

    private void chkAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAddressActionPerformed
        Tipaddress.setText("");
        if (chkAddress.isSelected() == true) {
            Tipaddress.setEnabled(true);
            Tipaddress.requestFocus();
        } else {
            Tipaddress.setEnabled(false);
        }
    }//GEN-LAST:event_chkAddressActionPerformed

    private void BtnSimpanPowerOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPowerOffActionPerformed
        if (tbAdmin.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah sudah yakin pengaturan power off akan disimpan..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (chkAddress.isSelected() == true && Tipaddress.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "IP address tujuan harus diisi dulu,...!!!");
                    Tipaddress.requestFocus();
                } else {
                    if (chkRestart.isSelected() == true) {
                        cekrestart = "ya";
                    } else {
                        cekrestart = "tidak";
                    }

                    if (chkAddress.isSelected() == true) {
                        cekip = "ya";
                    } else {
                        cekip = "tidak";
                    }

                    Sequel.mengedit("setting", "nama_instansi='" + tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString() + "'",
                            "auto_restart='" + cekrestart + "', periode_restart='" + cmbPeriode.getSelectedItem().toString() + "', "
                            + "tgl_restart='" + Valid.SetTgl(TtglRestart.getSelectedItem() + "") + "', "
                            + "ip_addres_tertentu='" + cekip + "', ip_eksekusi='" + Tipaddress.getText() + "'");
                    
                    emptTeks();
                    tampil();
                }
            } else {
                emptTeks();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu datanya pada tabel..!!");
            tampil();
            tbAdmin.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpanPowerOffActionPerformed

    private void BtnSimpanPowerOff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanPowerOff1ActionPerformed
        if (tbAdmin.getSelectedRow() > -1) {
            String tglSet = "";
            tglSet = Sequel.cariIsi("select date(now())");
            
            Sequel.mengedit("setting", "nama_instansi='" + tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString() + "'",
                    "auto_restart='tidak', tgl_restart='" + tglSet + "', ip_addres_tertentu='tidak', ip_eksekusi=''");

            emptTeks();
            tampil();
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan klik dulu datanya pada tabel..!!");
            tampil();
            tbAdmin.requestFocus();
        }
    }//GEN-LAST:event_BtnSimpanPowerOff1ActionPerformed

    private void cmbPeriodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPeriodeActionPerformed
        TtglRestart.setDate(new Date());
        if (cmbPeriode.getSelectedIndex() == 0) {
            TtglRestart.setEnabled(true);
        } else {
            TtglRestart.setEnabled(false);
        }
    }//GEN-LAST:event_cmbPeriodeActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgSetAplikasi dialog = new DlgSetAplikasi(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBaru1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari1;
    private widget.Button BtnCariGb;
    private widget.Button BtnCariLogo;
    private widget.Button BtnCariLogo1;
    private widget.Button BtnCariLogo2;
    private widget.Button BtnCariLogo3;
    private widget.Button BtnEdit;
    private widget.Button BtnGanti1;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.Button BtnSimpanPowerOff;
    private widget.Button BtnSimpanPowerOff1;
    private widget.TextBox EGb;
    private widget.TextBox ELogo;
    private widget.TextBox ELogo1;
    private widget.TextBox ELogo2;
    private widget.TextBox ELogo3;
    private widget.TextBox Email;
    private widget.TextBox Kontak;
    private widget.TextBox Kota;
    private widget.Label LCount;
    private widget.TextBox Nm;
    private java.awt.Canvas PhotoGambar;
    private java.awt.Canvas PhotoLogo;
    private java.awt.Canvas PhotoLogo1;
    private java.awt.Canvas PhotoLogo2;
    private java.awt.Canvas PhotoLogo3;
    private widget.TextBox Propinsi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll21;
    private widget.TextBox TCari;
    private widget.TextBox Tipaddress;
    private widget.Tanggal TtglRestart;
    private widget.ComboBox YesNo;
    private widget.TextBox alamatrs;
    public widget.CekBox chkAddress;
    public widget.CekBox chkRestart;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbPeriode;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.TextBox kdPPK;
    private widget.TextBox kdPPK1;
    private widget.TextArea ket_update;
    private widget.Label label12;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label37;
    private widget.Label label38;
    private widget.Label label39;
    private widget.Label label40;
    private widget.Label label41;
    private widget.Label label42;
    private widget.Label label43;
    private widget.Label label44;
    private widget.Label label45;
    private widget.Label label46;
    private widget.Label label47;
    private widget.Label label48;
    private widget.Label label49;
    private widget.Label label54;
    private widget.Label label55;
    private widget.panelGlass panelGlass1;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi2;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane3;
    private widget.ScrollPane scrollPane4;
    private widget.ScrollPane scrollPane5;
    private widget.ScrollPane scrollPane6;
    private widget.Table tbAdmin;
    private widget.Table tbHistoryUpdate;
    private widget.Table tbUpdate;
    private widget.Tanggal tglUpdate;
    private widget.TextBox versi;
    // End of variables declaration//GEN-END:variables

    private void tampil() {
        String sql = "select nama_instansi, alamat_instansi, kabupaten, propinsi, aktifkan, wallpaper,kontak,"
                + "email,logo,kode_ppk,kode_ppkinhealth, logo_hitam_putih, logo_kabupaten, logo_rs_warna_miring1, auto_restart, periode_restart, "
                + "tgl_restart, ip_addres_tertentu, ip_eksekusi from setting";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try{
            ResultSet rs=koneksi.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                Object[] data = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getBlob(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getBlob(9),
                    rs.getString(10),
                    rs.getString(11),
                    rs.getBlob(12),
                    rs.getBlob(13),
                    rs.getBlob(14),                    
                    rs.getString(15),
                    rs.getString(16),
                    rs.getString(17),
                    rs.getString(18),
                    rs.getString(19)
                };
                tabMode.addRow(data);
            }
        }catch(SQLException e){
            System.out.println("Notifikasi : "+e);
        }
    }

    private void getData() {
        cekrestart = "";
        cekip = "";
        
        if (tbAdmin.getSelectedRow() != -1) {
            Nm.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString());
            alamatrs.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 1).toString());
            Kota.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 2).toString());
            Propinsi.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 3).toString());
            YesNo.setSelectedItem(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 4).toString());
            Kontak.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 6).toString());
            Email.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 7).toString());
            kdPPK.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 9).toString());
            kdPPK1.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 10).toString());
            
            cekrestart = tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 14).toString();
            cmbPeriode.setSelectedItem(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 15).toString());
            Valid.SetTgl(TtglRestart, tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 16).toString());
            
            cekip = tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 17).toString();
            Tipaddress.setText(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 18).toString());
            
            if (cekrestart.equals("ya")) {
                chkRestart.setSelected(true);
                cmbPeriode.setEnabled(true);
                if (cmbPeriode.getSelectedIndex() == 0) {
                    TtglRestart.setEnabled(true);
                } else {
                    TtglRestart.setEnabled(false);
                }                
            } else {
                chkRestart.setSelected(false);
                cmbPeriode.setEnabled(false);
                TtglRestart.setEnabled(false);
            }
            
            if (cekip.equals("ya")) {
                chkAddress.setSelected(true);
                Tipaddress.setEnabled(true);
            } else {
                chkAddress.setSelected(false);
                Tipaddress.setEnabled(false);
            }
            
            try {
                ResultSet hasil = koneksi.createStatement().executeQuery(
                        "select wallpaper, logo, logo_hitam_putih, logo_kabupaten, logo_rs_warna_miring1 from setting");
                for (int I = 0; hasil.next(); I++) {
                    // wallpaper RS
                    ((Painter) PhotoGambar).setImage(gambar(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString()));
                    Blob blob = hasil.getBlob(1);
                    ((Painter) PhotoGambar).setImageIcon(new javax.swing.ImageIcon(
                            blob.getBytes(1, (int) (blob.length()))));
                    blob.free();

                    // logo RS yang warna
                    ((Painter) PhotoLogo).setImage(gambar(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString()));
                    Blob blob2 = hasil.getBlob(2);
                    ((Painter) PhotoLogo).setImageIcon(new javax.swing.ImageIcon(
                            blob2.getBytes(1, (int) (blob2.length()))));
                    blob2.free();

                    // logo RS yang hitam putih
                    ((Painter) PhotoLogo1).setImage(gambar(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString()));
                    Blob blob3 = hasil.getBlob(3);
                    ((Painter) PhotoLogo1).setImageIcon(new javax.swing.ImageIcon(
                            blob3.getBytes(1, (int) (blob3.length()))));
                    blob3.free();
                    
                    // logo kabupaten
                    ((Painter) PhotoLogo2).setImage(gambar(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString()));
                    Blob blob4 = hasil.getBlob(4);
                    ((Painter) PhotoLogo2).setImageIcon(new javax.swing.ImageIcon(
                            blob4.getBytes(1, (int) (blob4.length()))));
                    blob4.free();
                    
                    // logo RS warna miring
                    ((Painter) PhotoLogo3).setImage(gambar(tbAdmin.getValueAt(tbAdmin.getSelectedRow(), 0).toString()));
                    Blob blob5 = hasil.getBlob(5);
                    ((Painter) PhotoLogo3).setImageIcon(new javax.swing.ImageIcon(
                            blob5.getBytes(1, (int) (blob5.length()))));
                    blob5.free();
                }
            } catch (Exception ex) {
                cetak(ex.toString());
            }
        }
    }

    public void emptTeks() {
        Nm.setText("");
        alamatrs.setText("");
        Kota.setText("");
        Propinsi.setText("");
        kdPPK.setText("");        
        kdPPK1.setText("");
        Email.setText("");
        ((Painter) PhotoGambar).setImage("");
        EGb.setText("");
        ((Painter) PhotoLogo).setImage("");
        ((Painter) PhotoLogo1).setImage("");
        ((Painter) PhotoLogo2).setImage("");
        ((Painter) PhotoLogo3).setImage("");
        ELogo.setText("- pilih logo RS warna -");
        ELogo1.setText("- pilih logo RS hitam putih -");
        ELogo2.setText("- pilih logo kabupaten -");
        ELogo3.setText("- pilih logo RS warna miring -");
        YesNo.setSelectedItem("No");
        Nm.requestFocus();

        chkRestart.setSelected(false);
        cmbPeriode.setSelectedIndex(0);
        TtglRestart.setDate(new Date());
        cmbPeriode.setEnabled(false);
        TtglRestart.setEnabled(false);

        chkAddress.setSelected(false);
        Tipaddress.setText("");
        Tipaddress.setEnabled(false);
    }    
    
    private String gambar(String id) {
        return folder + File.separator + id.trim() + ".jpg";
    }

    private String folder;
    public class Painter extends Canvas {

        Image image;

        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                cetak(ex.toString());
            }
            image = getToolkit().getImage(url);
            repaint();
        }
        public void setImageIcon(ImageIcon file) {
            image = file.getImage();
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            try {
                double d = image.getHeight(this) / this.getHeight();
                double w = image.getWidth(this) / d;
                double x = this.getWidth() / 2 - w / 2;
                g.drawImage(image, (int) x, 0, (int) (w), this.getHeight(), this);
            } catch (Exception e) {
            }            
        }
    }
    
    private void cetak(String str) {
        System.out.println(str);
    }
    
    public void tampilUpdate() {
        Valid.tabelKosong(tabMode1);
        try {
            ps = koneksi.prepareStatement("SELECT tgl_update, versi_update, keterangan, kode, "
                    + "ifnull(jam_update,'-') jam FROM history_update ORDER BY tgl_update desc limit 20");

            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode1.addRow(new Object[]{
                        rs.getString("tgl_update"),
                        rs.getString("jam"),
                        rs.getString("versi_update"),
                        rs.getString("keterangan"),
                        rs.getString("kode")
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgSetAplikasi.tampil() : " + e);
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
    
    private void tampilHistori() {
        Valid.tabelKosong(tabMode2);
        try {
            ps1 = koneksi.prepareStatement("SELECT *, date_format(waktu_update,'%d-%m-%Y %H:%i:%s') wktupdate, if(h.nip_login='-','Admin Utama',p.nama) nmPetugas "
                    + "FROM history_aplikasi h inner join pegawai p on p.nik=h.nip_login where "
                    + "h.ip_address like ? or "
                    + "h.versi like ? or "
                    + "h.aplikasi like ? or "
                    + "if(h.nip_login='-','Admin Utama',p.nama) like ? ORDER BY h.waktu_update desc");
            try {
                ps1.setString(1, "%" + TCari.getText().trim() + "%");
                ps1.setString(2, "%" + TCari.getText().trim() + "%");
                ps1.setString(3, "%" + TCari.getText().trim() + "%");
                ps1.setString(4, "%" + TCari.getText().trim() + "%");
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode2.addRow(new String[]{
                        rs1.getString("ip_address"),
                        rs1.getString("versi"),
                        rs1.getString("aplikasi"),
                        rs1.getString("wktupdate"),
                        rs1.getString("nmPetugas")                        
                    });
                }
            } catch (Exception e) {
                System.out.println("simrskhanza.DlgSetAplikasi.tampilHistori() : " + e);
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
        LCount.setText("" + tabMode2.getRowCount());
    }
    
    private void getdataUpdate() {
        kd_update = "";

        if (tbUpdate.getSelectedRow() != -1) {
            Valid.SetTgl(tglUpdate, tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 0).toString());
            versi.setText(tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 2).toString());
            ket_update.setText(tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 3).toString());
            kd_update = tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 4).toString();
            cmbJam.setSelectedItem(tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 1).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 1).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbUpdate.getValueAt(tbUpdate.getSelectedRow(), 1).toString().substring(6, 8));
            ket_update.requestFocus();
        }
    }
    
    public void emptUpdate() {
        kd_update= "";
        versi.setText("");
        ket_update.setText("");
        tglUpdate.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
    }
}
