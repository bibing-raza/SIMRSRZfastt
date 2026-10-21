package simrskhanza;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Canvas;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import simrskhanza.frmUtama;

/**
 *
 * @author dosen
 */
public class DlgRanapPulangAPS extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private DlgCariPetugas petugas;
    private Properties prop = new Properties();
    private int i = 0, x = 0;
    private String nip = "", idFileBerttd = "", idFileSaksi = "", kdAps = "", usernya = "", pwdnya = "", idParameterTtd = "", URL = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgRanapPulangAPS(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Ruang Rawat", "Nama DPJP", "Tgl. APS/Surat", "Alasan APS", "Keterangan", "Nama Petugas",
            "kd_aps", "keterangan", "nm_berttd", "alamat", "selaku", "tgl_surat", "nm_saksi", "nip_petugas", "id_file_nm_berttd", "id_file_nm_saksi"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbPulangAps.setModel(tabMode);
        tbPulangAps.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPulangAps.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbPulangAps.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(250);
            } else if (i == 4) {
                column.setPreferredWidth(220);
            } else if (i == 5) {
                column.setPreferredWidth(100);
            } else if (i == 6) {
                column.setPreferredWidth(200);
            } else if (i == 7) {
                column.setPreferredWidth(250);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 10) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 11) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 12) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 13) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 14) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 15) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 16) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 17) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 18) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbPulangAps.setDefaultRenderer(Object.class, new WarnaTable());

        TnmBerttd.setDocument(new batasInput((int) 150).getKata(TnmBerttd));
        TAlamatBerttd.setDocument(new batasInput((int) 255).getKata(TAlamatBerttd));
        TnmSaksi.setDocument(new batasInput((int) 150).getKata(TnmSaksi));
        Tket.setDocument(new batasInput((int) 255).getKata(Tket));
        TCari.setDocument(new batasInput((byte) 100).getKata(TCari));
        
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
        
        HTMLEditorKit kit = new HTMLEditorKit();
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(".isi td{border-right: 1px solid #edf2e8;font: 10px tahoma;height:12px;border-bottom: 1px solid #edf2e8;background: 0000000;color:0000000;}");
        Document doc = kit.createDefaultDocument();
        
        LoadHTML1.setEditable(true);
        LoadHTML1.setEditorKit(kit);
        LoadHTML1.setDocument(doc);
        LoadHTML1.setEditable(false);
        LoadHTML1.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        try {
            prop.loadFromXML(new FileInputStream("setting/database.xml"));
        } catch (Exception e) {
            System.out.println("notif : " + e);
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
        MnBikinQrCode = new javax.swing.JMenuItem();
        MnHapusTtdPembuat = new javax.swing.JMenuItem();
        MnHapusTtdSaksi = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi5 = new widget.panelisi();
        jLabel125 = new widget.Label();
        cmbRekmed = new widget.ComboBox();
        panelisi7 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn3 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass9 = new widget.panelisi();
        FormInput = new widget.PanelBiasa();
        jLabel10 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRM = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel11 = new widget.Label();
        TnmBerttd = new widget.TextBox();
        jLabel12 = new widget.Label();
        TAlamatBerttd = new widget.TextBox();
        jLabel13 = new widget.Label();
        cmbSelaku = new widget.ComboBox();
        jLabel14 = new widget.Label();
        TtglAps = new widget.Tanggal();
        jLabel15 = new widget.Label();
        TnmSaksi = new widget.TextBox();
        jLabel16 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel17 = new widget.Label();
        cmbAlasan = new widget.ComboBox();
        jLabel18 = new widget.Label();
        Tket = new widget.TextBox();
        chkSama = new widget.CekBox();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnGanti = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPulangAps = new widget.Table();
        panelGlass13 = new widget.panelisi();
        panelGlass23 = new widget.panelisi();
        scrollPane7 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel93 = new widget.Label();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(220, 94));

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(300, 26));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBikinQrCode);

        MnHapusTtdPembuat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdPembuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdPembuat.setText("Hapus TTD Pembuat Pernyataan");
        MnHapusTtdPembuat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdPembuat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdPembuat.setIconTextGap(5);
        MnHapusTtdPembuat.setName("MnHapusTtdPembuat"); // NOI18N
        MnHapusTtdPembuat.setPreferredSize(new java.awt.Dimension(300, 26));
        MnHapusTtdPembuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdPembuatActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtdPembuat);

        MnHapusTtdSaksi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdSaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdSaksi.setText("Hapus TTD Saksi Keluarga");
        MnHapusTtdSaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdSaksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdSaksi.setIconTextGap(5);
        MnHapusTtdSaksi.setName("MnHapusTtdSaksi"); // NOI18N
        MnHapusTtdSaksi.setPreferredSize(new java.awt.Dimension(300, 26));
        MnHapusTtdSaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdSaksiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtdSaksi);

        WindowNomorDokumenRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNomorDokumenRM.setName("WindowNomorDokumenRM"); // NOI18N
        WindowNomorDokumenRM.setUndecorated(true);
        WindowNomorDokumenRM.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi5.setBackground(new java.awt.Color(255, 150, 255));
        panelisi5.setName("panelisi5"); // NOI18N
        panelisi5.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi5.setLayout(null);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Pilih Rekam Medis :");
        jLabel125.setName("jLabel125"); // NOI18N
        panelisi5.add(jLabel125);
        jLabel125.setBounds(0, 10, 120, 23);

        cmbRekmed.setBackground(new java.awt.Color(245, 253, 240));
        cmbRekmed.setForeground(new java.awt.Color(0, 0, 0));
        cmbRekmed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRekmed.setLightWeightPopupEnabled(false);
        cmbRekmed.setName("cmbRekmed"); // NOI18N
        panelisi5.add(cmbRekmed);
        cmbRekmed.setBounds(127, 10, 550, 23);

        internalFrame6.add(panelisi5, java.awt.BorderLayout.CENTER);

        panelisi7.setName("panelisi7"); // NOI18N
        panelisi7.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi7.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

        BtnTampilkanQr.setForeground(new java.awt.Color(0, 0, 0));
        BtnTampilkanQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/clear24.png"))); // NOI18N
        BtnTampilkanQr.setMnemonic('S');
        BtnTampilkanQr.setText("Tampilkan Qr Code TTD");
        BtnTampilkanQr.setToolTipText("Alt+S");
        BtnTampilkanQr.setName("BtnTampilkanQr"); // NOI18N
        BtnTampilkanQr.setPreferredSize(new java.awt.Dimension(180, 30));
        BtnTampilkanQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTampilkanQrActionPerformed(evt);
            }
        });
        panelisi7.add(BtnTampilkanQr);

        BtnCloseIn3.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn3.setMnemonic('U');
        BtnCloseIn3.setText("Tutup");
        BtnCloseIn3.setToolTipText("Alt+U");
        BtnCloseIn3.setName("BtnCloseIn3"); // NOI18N
        BtnCloseIn3.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn3ActionPerformed(evt);
            }
        });
        panelisi7.add(BtnCloseIn3);

        internalFrame6.add(panelisi7, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Rawat Inap Pulang APS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(55, 270));
        panelGlass9.setLayout(new java.awt.GridLayout(1, 2));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 270));
        FormInput.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pasien :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 120, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(124, 10, 131, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(260, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(335, 10, 390, 23);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Nama Ber TTD :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(0, 38, 120, 23);

        TnmBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TnmBerttd.setName("TnmBerttd"); // NOI18N
        TnmBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TnmBerttd);
        TnmBerttd.setBounds(124, 38, 360, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Alamat Ber TTD :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 66, 120, 23);

        TAlamatBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TAlamatBerttd.setName("TAlamatBerttd"); // NOI18N
        TAlamatBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TAlamatBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TAlamatBerttd);
        TAlamatBerttd.setBounds(124, 66, 600, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Selaku :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 94, 120, 23);

        cmbSelaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbSelaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "diri sendiri", "istri", "suami", "ayah", "ibu", "anak", "saudara kandung" }));
        cmbSelaku.setName("cmbSelaku"); // NOI18N
        cmbSelaku.setPreferredSize(new java.awt.Dimension(105, 23));
        FormInput.add(cmbSelaku);
        cmbSelaku.setBounds(124, 94, 115, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Tgl. APS / Surat :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(240, 94, 110, 23);

        TtglAps.setEditable(false);
        TtglAps.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2026" }));
        TtglAps.setDisplayFormat("dd-MM-yyyy");
        TtglAps.setName("TtglAps"); // NOI18N
        TtglAps.setOpaque(false);
        TtglAps.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglAps);
        TtglAps.setBounds(357, 94, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Nama Saksi Klg. :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 122, 120, 23);

        TnmSaksi.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksi.setName("TnmSaksi"); // NOI18N
        TnmSaksi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSaksiKeyPressed(evt);
            }
        });
        FormInput.add(TnmSaksi);
        TnmSaksi.setBounds(124, 122, 360, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Petugas Dari RS :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 150, 120, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(124, 150, 360, 23);

        BtnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPetugas.setMnemonic('2');
        BtnPetugas.setToolTipText("Alt+2");
        BtnPetugas.setName("BtnPetugas"); // NOI18N
        BtnPetugas.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPetugasActionPerformed(evt);
            }
        });
        FormInput.add(BtnPetugas);
        BtnPetugas.setBounds(490, 150, 28, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Alasan Pulang APS :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(0, 178, 120, 23);

        cmbAlasan.setForeground(new java.awt.Color(0, 0, 0));
        cmbAlasan.setName("cmbAlasan"); // NOI18N
        cmbAlasan.setPreferredSize(new java.awt.Dimension(105, 23));
        FormInput.add(cmbAlasan);
        cmbAlasan.setBounds(124, 178, 310, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Keterangan APS :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 206, 120, 23);

        Tket.setForeground(new java.awt.Color(0, 0, 0));
        Tket.setName("Tket"); // NOI18N
        Tket.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TketKeyPressed(evt);
            }
        });
        FormInput.add(Tket);
        Tket.setBounds(124, 206, 600, 23);

        chkSama.setBackground(new java.awt.Color(255, 255, 250));
        chkSama.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 250)));
        chkSama.setForeground(new java.awt.Color(0, 0, 0));
        chkSama.setText("Sama Dengan Yang BerTTD");
        chkSama.setBorderPainted(true);
        chkSama.setBorderPaintedFlat(true);
        chkSama.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        chkSama.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        chkSama.setName("chkSama"); // NOI18N
        chkSama.setOpaque(false);
        chkSama.setPreferredSize(new java.awt.Dimension(175, 23));
        chkSama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSamaActionPerformed(evt);
            }
        });
        FormInput.add(chkSama);
        chkSama.setBounds(490, 122, 170, 23);

        panelGlass9.add(FormInput);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: TTD Pembuat Pernyataan & Saksi Pihak Keluarga ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(800, 38));

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass9.add(Scroll5);

        internalFrame1.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
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

        jLabel63.setForeground(new java.awt.Color(0, 0, 0));
        jLabel63.setText("Cetak Dalam Bentuk :");
        jLabel63.setName("jLabel63"); // NOI18N
        jLabel63.setPreferredSize(new java.awt.Dimension(120, 23));
        panelGlass8.add(jLabel63);

        cmbPilihCetak.setForeground(new java.awt.Color(0, 0, 0));
        cmbPilihCetak.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TTE (QR Code)", "TTD Basah" }));
        cmbPilihCetak.setName("cmbPilihCetak"); // NOI18N
        cmbPilihCetak.setPreferredSize(new java.awt.Dimension(105, 23));
        panelGlass8.add(cmbPilihCetak);

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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. APS/Surat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel21);

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "21-10-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPulangAps.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbPulangAps.setComponentPopupMenu(jPopupMenu1);
        tbPulangAps.setName("tbPulangAps"); // NOI18N
        tbPulangAps.getTableHeader().setReorderingAllowed(false);
        tbPulangAps.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbPulangApsMouseClicked(evt);
            }
        });
        tbPulangAps.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbPulangApsKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbPulangAps);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(282, 44));
        panelGlass13.setLayout(null);

        panelGlass23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass23.setName("panelGlass23"); // NOI18N
        panelGlass23.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane7.setName("scrollPane7"); // NOI18N
        scrollPane7.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane7.setViewportView(gambarQR);

        panelGlass23.add(scrollPane7);

        panelGlass13.add(panelGlass23);
        panelGlass23.setBounds(12, 10, 230, 245);

        jLabel93.setForeground(new java.awt.Color(0, 0, 0));
        jLabel93.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel93.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel93.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel93.setName("jLabel93"); // NOI18N
        jLabel93.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelGlass13.add(jLabel93);
        jLabel93.setBounds(20, 262, 210, 60);

        internalFrame2.add(panelGlass13, java.awt.BorderLayout.EAST);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "pasien");
        } else {
            kdAps = Sequel.cariIsi("select kd_aps from master_aps where nm_alasan='" + cmbAlasan.getSelectedItem().toString() + "'");

            if (Sequel.menyimpantf("ranap_aps", "?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 11, new String[]{
                TNoRw.getText(), kdAps, Tket.getText(), TnmBerttd.getText(), TAlamatBerttd.getText(), cmbSelaku.getSelectedItem().toString(),
                Valid.SetTgl(TtglAps.getSelectedItem() + ""), TnmSaksi.getText(), nip, "", ""
            }) == true) {
                emptTeks();
                tampil();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
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
        if (tbPulangAps.getSelectedRow() != -1) {
            kdAps = Sequel.cariIsi("select kd_aps from master_aps where nm_alasan='" + cmbAlasan.getSelectedItem().toString() + "'");
            
            if (Sequel.mengedittf("ranap_aps", "no_rawat=?", "kd_aps=?, keterangan=?, nm_berttd=?, alamat=?, selaku=?, tgl_surat=?, nm_saksi=?, nip_petugas=?", 9, new String[]{
                kdAps, Tket.getText(), TnmBerttd.getText(), TAlamatBerttd.getText(), cmbSelaku.getSelectedItem().toString(),
                Valid.SetTgl(TtglAps.getSelectedItem() + ""), TnmSaksi.getText(), nip,
                tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 0).toString()
            }) == true) {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silakan klik/pilih salah satu datanya terlebih dulu pada tabel.");
            tampil();
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnGantiActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowNomorDokumenRM.dispose();
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);        
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            WindowNomorDokumenRM.dispose();
            frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        } else {
            Valid.pindah(evt, BtnBatal, TCari);
        }
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
        ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
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
        ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
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

    private void tbPulangApsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbPulangApsMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbPulangApsMouseClicked

    private void tbPulangApsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbPulangApsKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbPulangApsKeyPressed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        initPetugas();
        akses.setform("DlgRanapPulangAPS");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("select nm_alasan from master_aps where status='1' order by nm_alasan", cmbAlasan);
        tampil();

        ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRekmed);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void chkSamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSamaActionPerformed
        TnmSaksi.setText("");
        if (chkSama.isSelected() == true) {
            TnmSaksi.setText(TnmBerttd.getText());
            BtnPetugas.requestFocus();
        } else {
            TnmSaksi.setText("");
            TnmSaksi.requestFocus();
        }
    }//GEN-LAST:event_chkSamaActionPerformed

    private void MnHapusTtdPembuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdPembuatActionPerformed
        if (tbPulangAps.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan pembuat pernyataan mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileBerttd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Pembuat pernyataan pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileBerttd) == true) {
                                Sequel.mengedit("ranap_aps", "no_rawat='" + TNoRw.getText() + "'", "id_file_nm_berttd=''");
                                tampil();
                                emptTeks();
                            }
                        }
                        //ping gagal
                    } else {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server terputus...!!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdPembuatActionPerformed

    private void MnHapusTtdSaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdSaksiActionPerformed
        if (tbPulangAps.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan saksi pihak keluarga mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileSaksi.equals("")) {
                            JOptionPane.showMessageDialog(null, "Saksi pihak keluarga pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileSaksi) == true) {
                                Sequel.mengedit("ranap_aps", "no_rawat='" + TNoRw.getText() + "'", "id_file_nm_saksi=''");
                                tampil();
                                emptTeks();
                            }
                        }
                        //ping gagal
                    } else {
                        JOptionPane.showMessageDialog(null, "Koneksi ke server terputus...!!!!");
                    }
                } catch (Exception e) {
                    System.out.println("Notif : " + e);
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnHapusTtdSaksiActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbPulangAps.getSelectedRow() > -1) {
            ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%SURAT PERNYATAAN PULANG APS RAWAT INAP%'") > 0) {
                bikinQR();
            } else {
                WindowNomorDokumenRM.setSize(737, 125);
                WindowNomorDokumenRM.setLocationRelativeTo(internalFrame1);
                WindowNomorDokumenRM.setVisible(true);

                cmbRekmed.setSelectedIndex(0);
                cmbRekmed.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
    }//GEN-LAST:event_MnBikinQrCodeActionPerformed

    private void BtnTampilkanQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTampilkanQrActionPerformed
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            if (Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'").equals(akses.getkode())) {
                usernya = akses.getkode();
                pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                    + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
            } else {
                usernya = Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'");
                pwdnya = Sequel.cariIsi("select CAST(AES_DECRYPT(password,'windi') AS CHAR) from user where CAST(AES_DECRYPT(id_user,'nur') AS CHAR)='" + usernya + "'");
            }
        }

        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRekmed.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRekmed.getSelectedItem().toString() + "'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((DlgRanapPulangAPS.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            BtnCloseIn3ActionPerformed(null);
            tampil();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRekmed.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn3ActionPerformed
        emptTeks();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn3ActionPerformed

    private void TnmBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TAlamatBerttd.requestFocus();
        }
    }//GEN-LAST:event_TnmBerttdKeyPressed

    private void TAlamatBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TAlamatBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSelaku.requestFocus();
        }
    }//GEN-LAST:event_TAlamatBerttdKeyPressed

    private void TnmSaksiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSaksiKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPetugas.requestFocus();
        }
    }//GEN-LAST:event_TnmSaksiKeyPressed

    private void TketKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TketKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TketKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbPulangAps.getSelectedRow() > -1) {
            String nipDpjp = "", nmDpjp = "";
            nipDpjp = Sequel.cariIsi("select pg.nik from dpjp_ranap dr inner join pegawai pg on pg.nik=dr.kd_dokter where dr.no_rawat='" + TNoRw.getText() + "'");
            nmDpjp = Sequel.cariIsi("select pg.nama from dpjp_ranap dr inner join pegawai pg on pg.nik=dr.kd_dokter where dr.no_rawat='" + TNoRw.getText() + "'");
            
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));

            if (TnmBerttd.getText().equals("")) {
                param.put("nmberttd", "..........................");
            } else {
                param.put("nmberttd", TnmBerttd.getText());
            }

            if (TAlamatBerttd.getText().equals("")) {
                param.put("alamat", "..........................");
            } else {
                param.put("alamat", TAlamatBerttd.getText());
            }
            
            param.put("selaku", cmbSelaku.getSelectedItem().toString());
            param.put("nmpasien", TPasien.getText());
            param.put("ttlPasien", Sequel.cariIsi("select concat(tmp_lahir,', ',DATE_FORMAT(tgl_lahir,'%d-%m-%Y')) from pasien where no_rkm_medis='" + TNoRM.getText() + "'"));
            param.put("norm", TNoRM.getText());
            param.put("nmdpjp", nmDpjp);
            param.put("nipdpjp", nipDpjp);
            param.put("nmPetugas", TnmPetugas.getText());
            param.put("nipPetugas", nip);
            
            if (Tket.getText().equals("")) {
                param.put("alasanAps", cmbAlasan.getSelectedItem().toString());
            } else {
                param.put("alasanAps", cmbAlasan.getSelectedItem().toString() + " (" + Tket.getText() + ")");
            }
            
            param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglAps.getSelectedItem() + "")));

            if (TnmSaksi.getText().equals("")) {
                param.put("saksi", "..........................");
            } else {
                param.put("saksi", TnmSaksi.getText());
            }

            if (cmbPilihCetak.getSelectedIndex() == 0) {
                try {
                    String gambar1 = "", gambar2 = "", ipGambar = "";
                    try {
                        //cek atau ping ip addres
                        ipGambar = "192.168.0.230";
                        InetAddress inet = InetAddress.getByName(ipGambar);

                        //ping sukses timeout 100 ms (0.1 detik)
                        if (inet.isReachable(100)) {
                            if (idFileBerttd.equals("")) {
                                gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                            } else {
                                gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileBerttd;
                            }

                            if (idFileSaksi.equals("")) {
                                gambar2 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                            } else {
                                gambar2 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileSaksi;
                            }
                            //ping gagal
                        } else {
                            gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                            gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : " + e);
                        gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    }

                    param.put("gambarTtd1", gambar1);
                    param.put("gambarTtd2", gambar2);
                } catch (Exception e) {
                    System.out.println("Notifikasi : " + e);
                }
                
                String isiDpjp = "", isiPetugas = "", tglSimpan = "", jamSimpan = "";
                tglSimpan = Sequel.cariIsi("select date_format(tgl_surat,'%d/%m/%Y') from ranap_aps where no_rawat='" + TNoRw.getText() + "'");
                jamSimpan = Sequel.cariIsi("select time(now())");
                param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='003'"));
                
                //dpjp
                if (nipDpjp.equals("") || nipDpjp.equals("-") || nipDpjp.equals("--")) {
                    param.put("lokasiQrDpjp", "");
                } else {
                    isiDpjp = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='003'"),
                                    "Surat Pernyataan Pulang APS", nmDpjp + " (DPJP)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='003'");

                    Valid.cetakQrTte(isiDpjp, Sequel.cariFolderTte(), "QRTteDpjp.jpg", "select logo from setting");
                    param.put("lokasiQrDpjp", Sequel.cariFolderTte() + File.separator + "QRTteDpjp.jpg");
                }
                
                //petugas
                if (nip.equals("") || nip.equals("-") || nip.equals("--")) {
                    param.put("lokasiQrPetugas", "");
                } else {
                    isiPetugas = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='003'"),
                                    "Surat Pernyataan Pulang APS", TnmPetugas.getText() + " (Petugas Dari RS)",
                                    tglSimpan, jamSimpan) + "') from kalimat_tte where kode='003'");

                    Valid.cetakQrTte(isiPetugas, Sequel.cariFolderTte(), "QRTtePetugas.jpg", "select logo from setting");
                    param.put("lokasiQrPetugas", Sequel.cariFolderTte() + File.separator + "QRTtePetugas.jpg");
                }

                Valid.MyReport("rptsuratAPSQr.jasper", "report", "::[ Surat Pernyataan Pulang APS (Atas Permintaan Sendiri) RM 18.5 ]::",
                        "select now() tanggal", param);

                Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                BtnBatalActionPerformed(null);
            } else {
                Valid.MyReport("rptsuratAPS.jasper", "report", "::[ Surat Pernyataan Pulang APS (Atas Permintaan Sendiri) RM 18.5 ]::",
                        "select now() tanggal", param);
                BtnBatalActionPerformed(null);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbPulangAps.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } 
    }//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRanapPulangAPS dialog = new DlgRanapPulangAPS(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn3;
    private widget.Button BtnGanti;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnHapusTtdPembuat;
    private javax.swing.JMenuItem MnHapusTtdSaksi;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TAlamatBerttd;
    public widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tket;
    private widget.TextBox TnmBerttd;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmSaksi;
    private widget.Tanggal TtglAps;
    private javax.swing.JDialog WindowNomorDokumenRM;
    public widget.CekBox chkSama;
    private widget.ComboBox cmbAlasan;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRekmed;
    private widget.ComboBox cmbSelaku;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel125;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel93;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass23;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.panelisi panelisi5;
    private widget.panelisi panelisi7;
    private widget.ScrollPane scrollPane7;
    private widget.Table tbPulangAps;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select ra.*, p.no_rkm_medis, p.nm_pasien, pg1.nama nmDpjp, date_format(ra.tgl_surat,'%d-%m-%Y') tglAps, "
                    + "ma.nm_alasan, pg2.nama nmPetugas, (select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat=ra.no_rawat "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1) ruangRawat from ranap_aps ra "
                    + "inner join reg_periksa rp on rp.no_rawat=ra.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join dpjp_ranap dr on dr.no_rawat=ra.no_rawat inner join pegawai pg1 on pg1.nik=dr.kd_dokter "
                    + "inner join pegawai pg2 on pg2.nik=ra.nip_petugas inner join master_aps ma on ma.kd_aps=ra.kd_aps where "
                    + "ra.tgl_surat between ? and ? and ra.no_rawat like ? or "
                    + "ra.tgl_surat between ? and ? and p.no_rkm_medis like ? or "
                    + "ra.tgl_surat between ? and ? and p.nm_pasien like ? or "
                    + "ra.tgl_surat between ? and ? and pg1.nama like ? or "
                    + "ra.tgl_surat between ? and ? and ma.nm_alasan like ? or "
                    + "ra.tgl_surat between ? and ? and ra.keterangan like ? or "
                    + "ra.tgl_surat between ? and ? and (select b.nm_bangsal from kamar_inap ki inner join kamar k on k.kd_kamar=ki.kd_kamar "
                    + "inner join bangsal b on b.kd_bangsal=k.kd_bangsal where ki.no_rawat=ra.no_rawat "
                    + "order by ki.tgl_masuk desc, ki.jam_masuk desc limit 1) like ? or "
                    + "ra.tgl_surat between ? and ? and pg2.nama like ? order by ra.tgl_surat desc");

            try {
                ps.setString(1, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(3, "%" + TCari.getText().trim() + "%");                
                ps.setString(4, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(5, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(6, "%" + TCari.getText().trim() + "%");
                ps.setString(7, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(8, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(9, "%" + TCari.getText().trim() + "%");
                ps.setString(10, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(11, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(12, "%" + TCari.getText().trim() + "%");
                ps.setString(13, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(14, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(15, "%" + TCari.getText().trim() + "%");
                ps.setString(16, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(17, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(18, "%" + TCari.getText().trim() + "%");
                ps.setString(19, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(20, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(21, "%" + TCari.getText().trim() + "%");
                ps.setString(22, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
                ps.setString(23, Valid.SetTgl(DTPCari2.getSelectedItem() + ""));
                ps.setString(24, "%" + TCari.getText().trim() + "%");
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{     
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("ruangRawat"),                        
                        rs.getString("nmDpjp"),
                        rs.getString("tglAps"),
                        rs.getString("nm_alasan"),
                        rs.getString("keterangan"),
                        rs.getString("nmPetugas"),
                        rs.getString("kd_aps"),
                        rs.getString("keterangan"),
                        rs.getString("nm_berttd"),
                        rs.getString("alamat"),
                        rs.getString("selaku"),
                        rs.getString("tgl_surat"),
                        rs.getString("nm_saksi"),
                        rs.getString("nip_petugas"),
                        rs.getString("id_file_nm_berttd"),
                        rs.getString("id_file_nm_saksi")
                    });
                }
            } catch (Exception e) {
                System.out.println("tampil() : " + e);
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
        TNoRw.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        TnmBerttd.setText("");
        TnmBerttd.requestFocus();
        TAlamatBerttd.setText("");
        cmbSelaku.setSelectedIndex(0);
        TtglAps.setDate(new Date());
        chkSama.setSelected(false);
        TnmSaksi.setText("");
        nip = "-";
        TnmPetugas.setText("-");
        cmbAlasan.setSelectedIndex(0);
        Tket.setText("");
        LoadHTML1.setText("");
    }

    private void getData() {
        nip = "";
        idFileBerttd = "";
        idFileSaksi = "";
        kdAps = "";
        chkSama.setSelected(false);
        LoadHTML1.setText("");
        ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
        
        if (tbPulangAps.getSelectedRow() != -1) {
            TNoRw.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 0).toString());
            TNoRM.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 1).toString());
            TPasien.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 2).toString());
            TnmBerttd.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 11).toString());
            TAlamatBerttd.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 12).toString());
            cmbSelaku.setSelectedItem(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 13).toString());
            Valid.SetTgl(TtglAps, tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 14).toString());
            TnmSaksi.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 15).toString());
            nip = tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 16).toString();
            TnmPetugas.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 8).toString());
            kdAps = tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 9).toString();
            cmbAlasan.setSelectedItem(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 6).toString());
            Tket.setText(tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 7).toString());
            idFileBerttd = tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 17).toString();
            idFileSaksi = tbPulangAps.getValueAt(tbPulangAps.getSelectedRow(), 18).toString();
            tampilTTD();
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getkamar_inap());
        BtnGanti.setEnabled(akses.getkamar_inap());
    }
    
    private void initPetugas() {
        if (petugas == null) {
            petugas = new DlgCariPetugas(null, false);

            petugas.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {
                }

                @Override
                public void windowClosing(WindowEvent e) {
                }

                @Override
                public void windowClosed(WindowEvent e) {
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPetugas.requestFocus();
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
    }
    
    public void setData(String norwt, String norm) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);        
        isPasien();
        TCari.setText(norwt);
    }
    
    private void isPasien() {
        try {
            ps1 = koneksi.prepareStatement("select *, concat(alamatpj,', Kel. ',kelurahanpj,', Kec. ',kecamatanpj,', ',kabupatenpj) almtPJ "
                    + "from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    TPasien.setText(rs1.getString("nm_pasien"));
                    TnmBerttd.setText(rs1.getString("namakeluarga").replaceAll("-", ""));
                    
                    if (rs1.getString("almtPJ").contains("ALAMAT")) {
                        TAlamatBerttd.setText("");
                    } else {
                        TAlamatBerttd.setText(rs1.getString("almtPJ"));
                    }
                    
                    if (rs1.getString("keluarga").equals("PASIEN SENDIRI")) {
                        cmbSelaku.setSelectedIndex(1);
                    } else if (rs1.getString("keluarga").equals("ISTRI")) {
                        cmbSelaku.setSelectedIndex(2);
                    } else if (rs1.getString("keluarga").equals("SUAMI")) {
                        cmbSelaku.setSelectedIndex(3);
                    } else if (rs1.getString("keluarga").equals("AYAH")) {
                        cmbSelaku.setSelectedIndex(4);
                    } else if (rs1.getString("keluarga").equals("IBU")) {
                        cmbSelaku.setSelectedIndex(5);
                    } else if (rs1.getString("keluarga").equals("ANAK")) {
                        cmbSelaku.setSelectedIndex(6);
                    } else if (rs1.getString("keluarga").equals("SAUDARA")) {
                        cmbSelaku.setSelectedIndex(7);
                    } else {
                        cmbSelaku.setSelectedIndex(0);
                    }
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
            System.out.println("Notif : " + e);
        }
    }
    
    public class Painter extends Canvas {
        Image image;
        public void setImage(String file) {
            URL url = null;
            try {
                url = new File(file).toURI().toURL();
            } catch (MalformedURLException ex) {
                System.out.println(ex.toString());
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
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar1 = "", gambar2 = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);

                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFileBerttd.equals("")) {
                        gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileBerttd;
                    }

                    if (idFileSaksi.equals("")) {
                        gambar2 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar2 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileSaksi;
                    }
                    //ping gagal
                } else {
                    gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                gambar2 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }

            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Pembuat Pernyataan</b></td>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Saksi Pihak Keluarga</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar1 + "' width='160' height='160' alt='TTD Pembuat Pernyataan'><br>(" + TnmBerttd.getText() + ")<br></td>"
                    + "<td valign='middle' align='center'><img src='" + gambar2 + "' width='160' height='160' alt='TTD Saksi Keluarga'><br>(" + TnmSaksi.getText() + ")<br></td>"
                    + "</tr>"
            );

            htmlContent.append("</tbody>"
                    + "</table>");

            LoadHTML1.setText(
                    "<html>"
                    + "<table width='100%' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"
                    + htmlContent.toString()
                    + "</table>"
                    + "</html>");
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void bikinQR() {  
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            if (Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'").equals(akses.getkode())) {
                usernya = akses.getkode();
                pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                        + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
            } else {
                usernya = Sequel.cariIsi("select user_id from petugas where nip='" + akses.getkode() + "'");
                pwdnya = Sequel.cariIsi("select CAST(AES_DECRYPT(password,'windi') AS CHAR) from user where CAST(AES_DECRYPT(id_user,'nur') AS CHAR)='" + usernya + "'");
            }            
        }
        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%SURAT PERNYATAAN PULANG APS RAWAT INAP%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((DlgRanapPulangAPS.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            emptTeks();
            tampil();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public void awalData() {
        Sequel.cariIsiComboDB("select nm_alasan from master_aps where status='1' order by nm_alasan", cmbAlasan);
        tampil();

        ((DlgRanapPulangAPS.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRekmed);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }
}

