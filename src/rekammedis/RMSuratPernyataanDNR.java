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

package rekammedis;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.HyperlinkEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import simrskhanza.frmUtama;

/**
 *
 * @author perpustakaan
 */
public final class RMSuratPernyataanDNR extends javax.swing.JDialog {
    private DefaultTableModel tabMode;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private PreparedStatement ps, ps1;
    private ResultSet rs, rs1;
    private int i = 0, x = 0;
    private String nipPtgs = "", pngJwbPasien = "", saksi1 = "", idFileNmBerttd = "", idFileSaksi1 = "", idParameterTtd = "", URL = "", usernya = "", pwdnya = "";
    private frmUtama formUtama;
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMSuratPernyataanDNR(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Scroll5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Scroll5.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        Scroll5.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setLocation(8,1);
        
        tabMode = new DefaultTableModel(null, new String[]{
            "No. Rawat", "No. RM", "Nama Pasien", "Jns. Kelamin", "Tgl. Lahir", "Ruang Perawatan", "Tgl. Surat", "Nama Petugas",
            "nm_berttd", "alamat", "selaku", "nm_selaku", "nm_saksi1", "nip_petugas", "tgl_surat", "id_file_nm_berttd", "id_file_saksi1", "waktu_simpan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbSurat.setModel(tabMode);
        tbSurat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbSurat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbSurat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(65);
            } else if (i == 2) {
                column.setPreferredWidth(220);
            } else if (i == 3) {
                column.setPreferredWidth(80);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(250);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(220);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
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
            } 
        }
        tbSurat.setDefaultRenderer(Object.class, new WarnaTable());
        //ini posisi kolom yang datanya ingin rata tengah
        tbSurat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbSurat.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        TnmBerttd.setDocument(new batasInput((int) 200).getKata(TnmBerttd));
        Talamat.setDocument(new batasInput((int) 500).getKata(Talamat));
        TnmSelaku.setDocument(new batasInput((int) 200).getKata(TnmSelaku));
        TnmSaksi1.setDocument(new batasInput((int) 200).getKata(TnmSaksi1));
        TCari.setDocument(new batasInput((int) 100).getKata(TCari));
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {                
                    if (petugas.getTable().getSelectedRow() != -1) {
                        nipPtgs = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();
                        TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                        BtnPtgs.requestFocus();
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
            System.out.println(e.toString());
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
        MnHapusTtdPngJawab = new javax.swing.JMenuItem();
        MnHapusTtdSaksi1 = new javax.swing.JMenuItem();
        MnBikinQrCode = new javax.swing.JMenuItem();
        WindowNomorDokumenRM = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        jLabel125 = new widget.Label();
        cmbRM = new widget.ComboBox();
        panelisi6 = new widget.panelisi();
        BtnTampilkanQr = new widget.Button();
        BtnCloseIn2 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        panelGlass12 = new widget.panelisi();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel5 = new widget.Label();
        TrgRawat = new widget.TextBox();
        jLabel146 = new widget.Label();
        BtnPtgs = new widget.Button();
        TtglSurat = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TnmBerttd = new widget.TextBox();
        jLabel7 = new widget.Label();
        Talamat = new widget.TextBox();
        cmbSelaku = new widget.ComboBox();
        jLabel26 = new widget.Label();
        jLabel27 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        jLabel28 = new widget.Label();
        TnmSaksi1 = new widget.TextBox();
        jLabel30 = new widget.Label();
        TnmSelaku = new widget.TextBox();
        panelGlass13 = new widget.panelisi();
        scrollPane3 = new widget.ScrollPane();
        gambarQR = new Painter();
        jLabel83 = new widget.Label();
        Scroll5 = new widget.ScrollPane();
        LoadHTML1 = new widget.editorpane();
        FormData = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        tbSurat = new widget.Table();
        panelGlass10 = new widget.panelisi();
        jLabel20 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel8 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel9 = new widget.Label();
        LCount = new widget.Label();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        jLabel63 = new widget.Label();
        cmbPilihCetak = new widget.ComboBox();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N
        jPopupMenu1.setPreferredSize(new java.awt.Dimension(190, 94));

        MnHapusTtdPngJawab.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdPngJawab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdPngJawab.setText("Hapus TTD Yg. Menyatakan");
        MnHapusTtdPngJawab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdPngJawab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdPngJawab.setIconTextGap(5);
        MnHapusTtdPngJawab.setName("MnHapusTtdPngJawab"); // NOI18N
        MnHapusTtdPngJawab.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHapusTtdPngJawab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdPngJawabActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtdPngJawab);

        MnHapusTtdSaksi1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnHapusTtdSaksi1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/delete-16x16.png"))); // NOI18N
        MnHapusTtdSaksi1.setText("Hapus TTD Saksi 1");
        MnHapusTtdSaksi1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnHapusTtdSaksi1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnHapusTtdSaksi1.setIconTextGap(5);
        MnHapusTtdSaksi1.setName("MnHapusTtdSaksi1"); // NOI18N
        MnHapusTtdSaksi1.setPreferredSize(new java.awt.Dimension(190, 26));
        MnHapusTtdSaksi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnHapusTtdSaksi1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnHapusTtdSaksi1);

        MnBikinQrCode.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnBikinQrCode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        MnBikinQrCode.setText("Bikin QR Code Ttd");
        MnBikinQrCode.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnBikinQrCode.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnBikinQrCode.setIconTextGap(5);
        MnBikinQrCode.setName("MnBikinQrCode"); // NOI18N
        MnBikinQrCode.setPreferredSize(new java.awt.Dimension(190, 26));
        MnBikinQrCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnBikinQrCodeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnBikinQrCode);

        WindowNomorDokumenRM.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowNomorDokumenRM.setName("WindowNomorDokumenRM"); // NOI18N
        WindowNomorDokumenRM.setUndecorated(true);
        WindowNomorDokumenRM.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Dokumen Rekam Medis Aktif ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        panelisi3.setBackground(new java.awt.Color(255, 150, 255));
        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 70));
        panelisi3.setLayout(null);

        jLabel125.setForeground(new java.awt.Color(0, 0, 0));
        jLabel125.setText("Pilih Rekam Medis :");
        jLabel125.setName("jLabel125"); // NOI18N
        panelisi3.add(jLabel125);
        jLabel125.setBounds(0, 10, 120, 23);

        cmbRM.setBackground(new java.awt.Color(245, 253, 240));
        cmbRM.setForeground(new java.awt.Color(0, 0, 0));
        cmbRM.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbRM.setLightWeightPopupEnabled(false);
        cmbRM.setName("cmbRM"); // NOI18N
        panelisi3.add(cmbRM);
        cmbRM.setBounds(127, 10, 550, 23);

        internalFrame6.add(panelisi3, java.awt.BorderLayout.CENTER);

        panelisi6.setName("panelisi6"); // NOI18N
        panelisi6.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 9, 9));

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
        panelisi6.add(BtnTampilkanQr);

        BtnCloseIn2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn2.setMnemonic('U');
        BtnCloseIn2.setText("Tutup");
        BtnCloseIn2.setToolTipText("Alt+U");
        BtnCloseIn2.setName("BtnCloseIn2"); // NOI18N
        BtnCloseIn2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnCloseIn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn2ActionPerformed(evt);
            }
        });
        panelisi6.add(BtnCloseIn2);

        internalFrame6.add(panelisi6, java.awt.BorderLayout.PAGE_END);

        WindowNomorDokumenRM.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "<html>::[ Surat Pernyataan DNR (<i>Do Not Resucitate</i>) Jangan Dilakukan Resusitasi ]::</html>", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass12.setName("panelGlass12"); // NOI18N
        panelGlass12.setPreferredSize(new java.awt.Dimension(44, 280));
        panelGlass12.setLayout(new java.awt.BorderLayout());

        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(870, 271));
        FormInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("No. Rawat : ");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 110, 23);

        TNoRw.setEditable(false);
        TNoRw.setBackground(new java.awt.Color(245, 250, 240));
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(114, 10, 122, 23);

        TPasien.setEditable(false);
        TPasien.setBackground(new java.awt.Color(245, 250, 240));
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(315, 10, 340, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(240, 10, 70, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Ruang Rawat : ");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 110, 23);

        TrgRawat.setEditable(false);
        TrgRawat.setBackground(new java.awt.Color(245, 250, 240));
        TrgRawat.setForeground(new java.awt.Color(0, 0, 0));
        TrgRawat.setName("TrgRawat"); // NOI18N
        FormInput.add(TrgRawat);
        TrgRawat.setBounds(114, 38, 540, 23);

        jLabel146.setForeground(new java.awt.Color(0, 0, 0));
        jLabel146.setText("Yang bertanda tangan di bawah ini :");
        jLabel146.setName("jLabel146"); // NOI18N
        FormInput.add(jLabel146);
        jLabel146.setBounds(0, 66, 210, 23);

        BtnPtgs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPtgs.setMnemonic('2');
        BtnPtgs.setToolTipText("Alt+2");
        BtnPtgs.setName("BtnPtgs"); // NOI18N
        BtnPtgs.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnPtgs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPtgsActionPerformed(evt);
            }
        });
        FormInput.add(BtnPtgs);
        BtnPtgs.setBounds(470, 206, 28, 23);

        TtglSurat.setEditable(false);
        TtglSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
        TtglSurat.setDisplayFormat("dd-MM-yyyy");
        TtglSurat.setName("TtglSurat"); // NOI18N
        TtglSurat.setOpaque(false);
        TtglSurat.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(TtglSurat);
        TtglSurat.setBounds(114, 236, 90, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nama : ");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(0, 94, 110, 23);

        TnmBerttd.setBackground(new java.awt.Color(245, 250, 240));
        TnmBerttd.setForeground(new java.awt.Color(0, 0, 0));
        TnmBerttd.setName("TnmBerttd"); // NOI18N
        TnmBerttd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmBerttdKeyPressed(evt);
            }
        });
        FormInput.add(TnmBerttd);
        TnmBerttd.setBounds(114, 94, 540, 23);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Alamat : ");
        jLabel7.setName("jLabel7"); // NOI18N
        FormInput.add(jLabel7);
        jLabel7.setBounds(0, 122, 110, 23);

        Talamat.setBackground(new java.awt.Color(245, 250, 240));
        Talamat.setForeground(new java.awt.Color(0, 0, 0));
        Talamat.setName("Talamat"); // NOI18N
        Talamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TalamatKeyPressed(evt);
            }
        });
        FormInput.add(Talamat);
        Talamat.setBounds(114, 122, 540, 23);

        cmbSelaku.setForeground(new java.awt.Color(0, 0, 0));
        cmbSelaku.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "diri sendiri", "suami", "istri", "orang tua", "anak", "kakak", "adik", "teman", "kerabat", "lainya" }));
        cmbSelaku.setName("cmbSelaku"); // NOI18N
        cmbSelaku.setPreferredSize(new java.awt.Dimension(55, 23));
        cmbSelaku.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSelakuActionPerformed(evt);
            }
        });
        FormInput.add(cmbSelaku);
        cmbSelaku.setBounds(114, 150, 80, 23);

        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Tgl. Surat : ");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(0, 236, 110, 23);

        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("<html><div style=\"text-align: right;\">Nama Petugas :&nbsp;<br>(Saksi 2)&nbsp;&nbsp;&nbsp;</div></html>");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(0, 206, 110, 30);

        TnmPetugas.setEditable(false);
        TnmPetugas.setBackground(new java.awt.Color(245, 250, 240));
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(114, 206, 350, 23);

        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Nama Saksi 1 : ");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(0, 178, 110, 23);

        TnmSaksi1.setBackground(new java.awt.Color(245, 250, 240));
        TnmSaksi1.setForeground(new java.awt.Color(0, 0, 0));
        TnmSaksi1.setName("TnmSaksi1"); // NOI18N
        TnmSaksi1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSaksi1KeyPressed(evt);
            }
        });
        FormInput.add(TnmSaksi1);
        TnmSaksi1.setBounds(114, 178, 540, 23);

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Selaku : ");
        jLabel30.setName("jLabel30"); // NOI18N
        FormInput.add(jLabel30);
        jLabel30.setBounds(0, 150, 110, 23);

        TnmSelaku.setBackground(new java.awt.Color(245, 250, 240));
        TnmSelaku.setForeground(new java.awt.Color(0, 0, 0));
        TnmSelaku.setName("TnmSelaku"); // NOI18N
        TnmSelaku.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TnmSelakuKeyPressed(evt);
            }
        });
        FormInput.add(TnmSelaku);
        TnmSelaku.setBounds(199, 150, 455, 23);

        panelGlass13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "[ Scan QR Untuk TTD ]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        panelGlass13.setName("panelGlass13"); // NOI18N
        panelGlass13.setPreferredSize(new java.awt.Dimension(44, 44));

        scrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        scrollPane3.setName("scrollPane3"); // NOI18N
        scrollPane3.setPreferredSize(new java.awt.Dimension(210, 220));

        gambarQR.setBackground(new java.awt.Color(245, 255, 235));
        gambarQR.setForeground(new java.awt.Color(235, 255, 235));
        gambarQR.setName("gambarQR"); // NOI18N
        scrollPane3.setViewportView(gambarQR);

        panelGlass13.add(scrollPane3);

        FormInput.add(panelGlass13);
        panelGlass13.setBounds(670, 10, 230, 245);

        jLabel83.setForeground(new java.awt.Color(0, 0, 0));
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("<html><b>Catatan :</b><br>Perangkat (smartphone / tab) harus terhubung dengan wifi rumah sakit diruangan ini terlebih dulu.</html>");
        jLabel83.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel83.setName("jLabel83"); // NOI18N
        jLabel83.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        FormInput.add(jLabel83);
        jLabel83.setBounds(910, 180, 150, 80);

        panelGlass12.add(FormInput, java.awt.BorderLayout.CENTER);

        Scroll5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, ":: Tanda Tangan Yang Menyatakan & Saksi Pasien ::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(600, 100));

        LoadHTML1.setBorder(null);
        LoadHTML1.setForeground(new java.awt.Color(0, 0, 0));
        LoadHTML1.setComponentPopupMenu(jPopupMenu1);
        LoadHTML1.setName("LoadHTML1"); // NOI18N
        Scroll5.setViewportView(LoadHTML1);

        panelGlass12.add(Scroll5, java.awt.BorderLayout.EAST);

        internalFrame1.add(panelGlass12, java.awt.BorderLayout.PAGE_START);

        FormData.setBorder(null);
        FormData.setName("FormData"); // NOI18N
        FormData.setLayout(new java.awt.BorderLayout());

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbSurat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbSurat.setComponentPopupMenu(jPopupMenu1);
        tbSurat.setName("tbSurat"); // NOI18N
        tbSurat.getTableHeader().setReorderingAllowed(false);
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
        Scroll1.setViewportView(tbSurat);

        FormData.add(Scroll1, java.awt.BorderLayout.CENTER);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Surat :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel20);

        DTPCari1.setEditable(false);
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari1);

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d.");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass10.add(jLabel22);

        DTPCari2.setEditable(false);
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "17-08-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(DTPCari2);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Key Word :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel8);

        TCari.setForeground(new java.awt.Color(0, 0, 0));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(205, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setText("Tampilkan Data");
        BtnCari.setToolTipText("Alt+3");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        panelGlass10.add(BtnCari);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Record :");
        jLabel9.setName("jLabel9"); // NOI18N
        jLabel9.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel9);

        LCount.setForeground(new java.awt.Color(0, 0, 0));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        FormData.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        internalFrame1.add(FormData, java.awt.BorderLayout.CENTER);

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 54));
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

        BtnEdit.setForeground(new java.awt.Color(0, 0, 0));
        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
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
        panelGlass8.add(BtnEdit);

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

        internalFrame1.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (TnmBerttd.getText().equals("")) {
                pngJwbPasien = "";
            } else {
                pngJwbPasien = TnmBerttd.getText() + " (Yang Menyatakan)";
            }
            
            if (TnmSaksi1.getText().equals("")) {
                saksi1 = "";
            } else {
                saksi1 = TnmSaksi1.getText() + " (Saksi 1)";
            }
            
            if (Sequel.menyimpantf("surat_pernyataan_dnr", "?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 12, new String[]{
                TNoRw.getText(), TrgRawat.getText(), pngJwbPasien, Talamat.getText(), cmbSelaku.getSelectedItem().toString(),
                TnmSelaku.getText(), saksi1, nipPtgs, Valid.SetTgl(TtglSurat.getSelectedItem() + ""), "", "",
                Sequel.cariIsi("select now()")
            }) == true) {

                Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Surat Pernyataan DNR", "Simpan");
                TCari.setText(TNoRw.getText());
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        tampil();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
            tampil();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnHapus);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (Sequel.queryu2tf("delete from surat_pernyataan_dnr where waktu_simpan=?", 1, new String[]{
                    tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString()
                }) == true) {
                    if (!idFileNmBerttd.equals("")) {
                        Sequel.hapusSemuaTtd(idFileNmBerttd);
                    }

                    if (!idFileSaksi1.equals("")) {
                        Sequel.hapusSemuaTtd(idFileSaksi1);
                    }

                    tampil();
                    emptTeks();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            } else {
                tampil();
                emptTeks();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            if (TnmBerttd.getText().equals("")) {
                pngJwbPasien = "";
            } else {
                pngJwbPasien = TnmBerttd.getText() + " (Yang Menyatakan)";
            }
            
            if (TnmSaksi1.getText().equals("")) {
                saksi1 = "";
            } else {
                saksi1 = TnmSaksi1.getText() + " (Saksi 1)";
            }
            
            if (Sequel.cariInteger("select count(-1) from ttd_erm_keluarga_pasien where id_file='" + idFileNmBerttd + "'") > 0
                    && !tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString().equals(TnmBerttd.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, penanggung jawab pasien sudah bertanda tangan, nama tidak bisa dirubah, kecuali       \n"
                        + "tanda tangan yang sudah tersimpan dihapus dulu, lalu lakukan tanda tangan ulang ..!!");
            } else if (Sequel.cariInteger("select count(-1) from ttd_erm_keluarga_pasien where id_file='" + idFileSaksi1 + "'") > 0
                    && !tbSurat.getValueAt(tbSurat.getSelectedRow(), 12).toString().equals(TnmSaksi1.getText())) {
                JOptionPane.showMessageDialog(rootPane, "Maaf, saksi 1 pasien sudah bertanda tangan, nama tidak bisa dirubah, kecuali       \n"
                        + "tanda tangan yang sudah tersimpan dihapus dulu, lalu lakukan tanda tangan ulang ..!!");
            } else {
                if (Sequel.mengedittf("surat_pernyataan_dnr", "waktu_simpan=?", "nm_berttd=?, alamat=?, selaku=?, nm_selaku=?, nm_saksi1=?, "
                        + "nip_petugas=?, tgl_surat=?", 8, new String[]{
                            pngJwbPasien, Talamat.getText(), cmbSelaku.getSelectedItem().toString(), TnmSelaku.getText(), saksi1, nipPtgs,
                            Valid.SetTgl(TtglSurat.getSelectedItem() + ""),
                            tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString()
                        }) == true) {

                    Sequel.SimpanHistoriRekamMedis(TNoRw.getText(), "Surat Pernyataan DNR", "Ganti");
                    TCari.setText(TNoRw.getText());
                    tampil();
                    emptTeks();
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnEditActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        frmUtama.getInstance().tutupDialogDiPanelUtama(this);
        WindowNomorDokumenRM.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnKeluarActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, TCari);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            String tglLahir = "";
            tglLahir = Sequel.cariIsi("select tgl_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'");
            
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRM.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format('" + tglLahir + "','%d-%m-%Y')"));
            
            param.put("nmBerttd", TnmBerttd.getText());
            param.put("alamat", Talamat.getText());
            param.put("selaku", cmbSelaku.getSelectedItem().toString());
            
            if (TnmSelaku.getText().equals("")) {
                param.put("nmSelaku", "..............");
            } else {
                param.put("nmSelaku", TnmSelaku.getText());
            }
            
            param.put("ttlPasien", Sequel.cariIsi("select tmp_lahir from pasien where no_rkm_medis='" + TNoRM.getText() + "'") + ", " + Valid.SetTglINDONESIA(tglLahir));
            
            param.put("tglSurat", "Martapura, " + Valid.SetTglINDONESIA(Valid.SetTgl(TtglSurat.getSelectedItem() + "")));
            param.put("saksi2", TnmPetugas.getText());
            
            if (TnmSaksi1.getText().equals("")) {
                param.put("saksi1", "..........................");
            } else {
                param.put("saksi1", TnmSaksi1.getText());
            }
            
            if (cmbPilihCetak.getSelectedIndex() == 0) {
                String isi = "";
                if (nipPtgs.equals("") || nipPtgs.equals("-") || nipPtgs.equals("--")) {
                    JOptionPane.showMessageDialog(rootPane, "Nama petugas / saksi 2 harus diisi dulu,..");
                } else {
                    try {
                        String gambar0 = "", gambar1 = "", ipGambar = "";
                        try {
                            //cek atau ping ip addres
                            ipGambar = "192.168.0.230";
                            InetAddress inet = InetAddress.getByName(ipGambar);

                            //ping sukses timeout 100 ms (0.1 detik)
                            if (inet.isReachable(100)) {
                                if (idFileNmBerttd.equals("")) {
                                    gambar0 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                                } else {
                                    gambar0 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileNmBerttd;
                                }

                                if (idFileSaksi1.equals("")) {
                                    gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                                } else {
                                    gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileSaksi1;
                                }
                                //ping gagal
                            } else {
                                gambar0 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                                gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                            }
                        } catch (Exception e) {
                            System.out.println("Notif : " + e);
                            gambar0 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                            gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                        }
                        
                        param.put("gambarTtd0", gambar0);
                        param.put("gambarTtd1", gambar1);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                    
                    isi = Sequel.cariIsi("select replace(kalimat_qrcode,kalimat_qrcode,"
                            + "'" + Valid.kalimatQRcode(Sequel.cariIsi("select jenis_dokumen from kalimat_tte where kode='001'"),
                                    "Surat Pernyataan DNR (Do Not Resucitate) Jangan Dilakukan Resusitasi", TnmPetugas.getText(),
                                    Sequel.cariIsi("select date_format('" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString() + "','%d/%m/%Y')"),
                                    Sequel.cariIsi("select time('" + tbSurat.getValueAt(tbSurat.getSelectedRow(), 17).toString() + "')")) + "') from kalimat_tte where kode='001'");

                    Valid.cetakQrTte(isi, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
                    Sequel.queryu("delete from setting_qr where judul = 'QRTte'");
                    Sequel.menyimpanQr("setting_qr", "'QRTte'", "file QRCode TTE Surat Pernyataan DNR", Sequel.cariFolderPrintTte());
                    param.put("lokasiQr", Sequel.cariGambar("select gambar from setting_qr where judul = 'QRTte'"));
                    param.put("kalimatTte", Sequel.cariIsi("select replace(kalimat_footer,'##jns_dokumen##',jenis_dokumen) from kalimat_tte where kode='001'"));
                    
                    Valid.MyReport("rptSuratPernyataanDNRQr.jasper", "report", "::[ Surat Pernyataan DNR (Do Not Resucitate) Jangan Dilakukan Resusitasi ]::",
                            "SELECT date(now()) tgl", param);
                    Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
                }
            } else {
                Valid.MyReport("rptSuratPernyataanDNR.jasper", "report", "::[ Surat Pernyataan DNR (Do Not Resucitate) Jangan Dilakukan Resusitasi ]::",
                        "SELECT date(now()) tgl", param);
            }
            
            BtnBatalActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Silahkan pilih/klik dulu salah satu datanya pada tabel..!!!");
            tbSurat.requestFocus();
        }        
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        BtnCariActionPerformed(null);
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            TCari.setText("");
            BtnCariActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
        ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan & Instalasi' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
    }//GEN-LAST:event_formWindowOpened

    private void BtnPtgsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPtgsActionPerformed
        akses.setform("RMSuratPernyataanDNR");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPtgsActionPerformed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
        tampil();
    }//GEN-LAST:event_BtnCariActionPerformed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void tbSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbSuratKeyPressed
        if (tabMode.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
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

    private void TnmBerttdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmBerttdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Talamat.requestFocus();
        }
    }//GEN-LAST:event_TnmBerttdKeyPressed

    private void TnmSaksi1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSaksi1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnPtgs.requestFocus();
        }
    }//GEN-LAST:event_TnmSaksi1KeyPressed

    private void TalamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TalamatKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cmbSelaku.requestFocus();
        }
    }//GEN-LAST:event_TalamatKeyPressed

    private void MnHapusTtdPngJawabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdPngJawabActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan yang menyatakan pasien mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileNmBerttd.equals("")) {
                            JOptionPane.showMessageDialog(null, "Yang menyatakan pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileNmBerttd) == true) {
                                Sequel.mengedit("surat_pernyataan_dnr", "no_rawat='" + TNoRw.getText() + "'", "id_file_nm_berttd=''");
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
    }//GEN-LAST:event_MnHapusTtdPngJawabActionPerformed

    private void MnHapusTtdSaksi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnHapusTtdSaksi1ActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin tanda tangan saksi 1 mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                String ipGambar = "";
                try {
                    //cek atau ping ip addres
                    ipGambar = "192.168.0.230";
                    InetAddress inet = InetAddress.getByName(ipGambar);

                    //ping sukses timeout 100 ms (0.1 detik)
                    if (inet.isReachable(100)) {
                        if (idFileSaksi1.equals("")) {
                            JOptionPane.showMessageDialog(null, "Saksi 1 pasien ini belum melakukan tanda tangan...!!!!");
                        } else {
                            if (Sequel.hapusFileTTD(idFileSaksi1) == true) {
                                Sequel.mengedit("surat_pernyataan_dnr", "no_rawat='" + TNoRw.getText() + "'", "id_file_saksi1=''");
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
    }//GEN-LAST:event_MnHapusTtdSaksi1ActionPerformed

    private void TnmSelakuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TnmSelakuKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            TnmSaksi1.requestFocus();
        }
    }//GEN-LAST:event_TnmSelakuKeyPressed

    private void cmbSelakuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSelakuActionPerformed
        if (cmbSelaku.getSelectedIndex() == 0 || cmbSelaku.getSelectedIndex() == 1) {
            TnmSelaku.setEditable(false);
        } else {
            TnmSelaku.setEditable(true);
            TnmSelaku.requestFocus();
        }
        
        if (cmbSelaku.getSelectedIndex() == 1) {
            TnmSelaku.setText(TPasien.getText());
        } else {
            TnmSelaku.setText("");
        }
    }//GEN-LAST:event_cmbSelakuActionPerformed

    private void MnBikinQrCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnBikinQrCodeActionPerformed
        if (tbSurat.getSelectedRow() > -1) {
            ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
            if (Sequel.cariInteger("select count(-1) from master_nomor_dokumen_erm where nm_dokumen like '%SURAT PERNYATAAN DNR (DO NOT RESUCITATE)%'") > 0) {
                bikinQR();
            } else {
                WindowNomorDokumenRM.setSize(737, 125);
                WindowNomorDokumenRM.setLocationRelativeTo(internalFrame1);
                WindowNomorDokumenRM.setVisible(true);

                cmbRM.setSelectedIndex(0);
                cmbRM.requestFocus();
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
            usernya = akses.getkode();
            pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
        }

        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        if (cmbRM.getSelectedIndex() != 0) {
            Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
            Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen='" + cmbRM.getSelectedItem().toString() + "'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

            try {
                ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
                ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
                for (int I = 0; hasil.next(); I++) {
                    Blob blob = hasil.getBlob(1);
                    ((RMSuratPernyataanDNR.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
                        blob.getBytes(1, (int) (blob.length()))));
                blob.free();
            }

            BtnCloseIn2ActionPerformed(null);
            tampil();
            Sequel.hapusIisiFolder(Sequel.cariFolderTte() + File.separator);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu jenis rekam medis yang dipilih..!!");
            cmbRM.requestFocus();
        }
    }//GEN-LAST:event_BtnTampilkanQrActionPerformed

    private void BtnCloseIn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn2ActionPerformed
        emptTeks();
        WindowNomorDokumenRM.dispose();
    }//GEN-LAST:event_BtnCloseIn2ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMSuratPernyataanDNR dialog = new RMSuratPernyataanDNR(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn2;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnPtgs;
    private widget.Button BtnSimpan;
    private widget.Button BtnTampilkanQr;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.InternalFrame FormData;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.editorpane LoadHTML1;
    private javax.swing.JMenuItem MnBikinQrCode;
    private javax.swing.JMenuItem MnHapusTtdPngJawab;
    private javax.swing.JMenuItem MnHapusTtdSaksi1;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll5;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Talamat;
    private widget.TextBox TnmBerttd;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TnmSaksi1;
    private widget.TextBox TnmSelaku;
    private widget.TextBox TrgRawat;
    private widget.Tanggal TtglSurat;
    private javax.swing.JDialog WindowNomorDokumenRM;
    private widget.ComboBox cmbPilihCetak;
    private widget.ComboBox cmbRM;
    private widget.ComboBox cmbSelaku;
    private java.awt.Canvas gambarQR;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame6;
    private widget.Label jLabel125;
    private widget.Label jLabel146;
    private widget.Label jLabel20;
    private widget.Label jLabel22;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
    private widget.Label jLabel30;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel63;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel83;
    private widget.Label jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass12;
    private widget.panelisi panelGlass13;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi6;
    private widget.ScrollPane scrollPane3;
    private widget.Table tbSurat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {        
        LoadHTML1.setText("");
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("select sp.*, p.no_rkm_medis, p.nm_pasien, if(p.jk='L','Laki-laki','Perempuan') jenkel, date_format(p.tgl_lahir,'%d-%m-%Y') tglLahir, "
                    + "date_format(sp.tgl_surat,'%d-%m-%Y') tglSurat, pg.nama nmPtgs, replace(sp.nm_berttd,' (Yang Menyatakan)','') nmttd, "
                    + "replace(sp.nm_saksi1,' (Saksi 1)','') saksi1 from surat_pernyataan_dnr sp "
                    + "inner join reg_periksa rp on rp.no_rawat=sp.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=sp.nip_petugas where "
                    + "sp.tgl_surat between ? and ? and sp.no_rawat like ? or "
                    + "sp.tgl_surat between ? and ? and p.no_rkm_medis like ? or "
                    + "sp.tgl_surat between ? and ? and p.nm_pasien like ? or "
                    + "sp.tgl_surat between ? and ? and pg.nama like ? order by sp.waktu_simpan desc");
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
                rs = ps.executeQuery();
                while (rs.next()) {
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("jenkel"),
                        rs.getString("tglLahir"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tglSurat"),
                        rs.getString("nmPtgs"),
                        rs.getString("nmttd"),
                        rs.getString("alamat"),
                        rs.getString("selaku"),
                        rs.getString("nm_selaku"),
                        rs.getString("saksi1"),
                        rs.getString("nip_petugas"),
                        rs.getString("tgl_surat"),
                        rs.getString("id_file_nm_berttd"),
                        rs.getString("id_file_saksi1"),
                        rs.getString("waktu_simpan")
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
    
    public void emptTeks(){
        TnmBerttd.setText("");
        Talamat.setText("");        
        cmbSelaku.setSelectedIndex(0);
        TnmSelaku.setText("");
        TnmSelaku.setEditable(false);        
        TnmSaksi1.setText("");        
        TtglSurat.setDate(new Date());
        LoadHTML1.setText("");

        if (akses.getadmin() == true) {
            nipPtgs = "-";
        } else {
            nipPtgs = akses.getkode();
        }

        TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPtgs + "'"));
        TnmSaksi1.setText("");
    }

    public void setData(String norwt, String norm, String nmPasien, String ruangrwt) {
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nmPasien);
        TrgRawat.setText(ruangrwt);
        Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_registrasi from reg_periksa where no_rawat='" + norwt + "'"));
        DTPCari2.setDate(new Date());
        TCari.setText(norwt);
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnEdit.setEnabled(akses.getcppt());
        
        if (akses.getjml2() >= 1) {
            nipPtgs = akses.getkode();
            Sequel.cariIsi("select nama from pegawai where nik=?", TnmPetugas, nipPtgs);
            if (TnmPetugas.getText().equals("")) {
                TnmPetugas.setText("-");
            }
        }
    }

    private void getData() {
        nipPtgs = "";
        idFileNmBerttd = "";
        idFileSaksi1 = "";
        LoadHTML1.setText("");
        ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
        
        if (tbSurat.getSelectedRow() != -1) {
            TNoRw.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 0).toString());
            TNoRM.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 1).toString());
            TPasien.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 2).toString());
            TrgRawat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 5).toString());
            TnmBerttd.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 8).toString());
            Talamat.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 9).toString());
            cmbSelaku.setSelectedItem(tbSurat.getValueAt(tbSurat.getSelectedRow(), 10).toString());
            TnmSelaku.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 11).toString());
            TnmSaksi1.setText(tbSurat.getValueAt(tbSurat.getSelectedRow(), 12).toString());
            nipPtgs = tbSurat.getValueAt(tbSurat.getSelectedRow(), 13).toString();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nipPtgs + "'"));            
            Valid.SetTgl(TtglSurat, tbSurat.getValueAt(tbSurat.getSelectedRow(), 14).toString());            
            idFileNmBerttd = tbSurat.getValueAt(tbSurat.getSelectedRow(), 15).toString();
            idFileSaksi1 = tbSurat.getValueAt(tbSurat.getSelectedRow(), 16).toString();
            tampilTTD();
            
            if (cmbSelaku.getSelectedIndex() == 0 || cmbSelaku.getSelectedIndex() == 1) {
                TnmSelaku.setEnabled(false);
            } else {
                TnmSelaku.setEnabled(true);
            }
        }
    }
    
    private void tampilTTD() {
        try {
            StringBuilder htmlContent = new StringBuilder();
            String gambar0 = "", gambar1 = "", ipGambar = "";
            try {
                //cek atau ping ip addres
                ipGambar = "192.168.0.230";
                InetAddress inet = InetAddress.getByName(ipGambar);
                
                //ping sukses timeout 100 ms (0.1 detik)
                if (inet.isReachable(100)) {
                    if (idFileNmBerttd.equals("")) {
                        gambar0 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar0 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileNmBerttd;
                    }
                    
                    if (idFileSaksi1.equals("")) {
                        gambar1 = "http://192.168.0.230:7183/img-rme/ttd_kosong.jpg";
                    } else {
                        gambar1 = "http://192.168.0.230:7183/reviewrm/index.php/ApiTtd/preview?id_file=" + idFileSaksi1;
                    }
                    //ping gagal
                } else {
                    gambar0 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                    gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
                gambar0 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
                gambar1 = "https://raw.githubusercontent.com/bibing-raza/gambar_online/main/ttd_kosong.jpg";
            }
            
            htmlContent.append(
                    "<table width='100%' class='isi'>"
                    + "<thead>"
                    + "<tr class='isi'>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Yang Menyatakan</b></td>"
                    + "    <td align='center' bgcolor='#f8fdf3'><b>TTD Saksi 1</b></td>"
                    + "</tr>"
                    + "</thead>"
                    + "<tbody>"
            );

            htmlContent.append(
                    "<tr class='isi'>"
                    + "<td valign='middle' align='center'><img src='" + gambar0 + "' width='160' height='160' alt='TTD Yang Menyatakan'><br>(" + TnmBerttd.getText() + ")<br></td>"
                    + "<td valign='middle' align='center'><img src='" + gambar1 + "' width='160' height='160' alt='TTD Saksi 1'><br>(" + TnmSaksi1.getText() + ")<br></td>"
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
    
    public void awalData() {
        tampil();
        ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
        Sequel.cariIsiComboDB("select nm_dokumen from master_nomor_dokumen_erm where "
                + "status='aktif' and unit_pengguna='Ruang Perawatan' and ttd_keluarga_pasien='Ya' order by kode_erm", cmbRM);
        Sequel.queryu("DELETE FROM parameter_ttd_rme WHERE DATE(waktu_kirim) < CURDATE()");
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
    
    private void bikinQR() {  
        if (akses.getadmin() == true) {
            usernya = "admin";
            pwdnya = "satu";
        } else {
            usernya = akses.getkode();
            pwdnya = Sequel.cariIsi("select AES_DECRYPT(u.password,'windi') from user u "
                    + "inner join petugas pt on pt.nip=AES_DECRYPT(u.id_user,'nur') where AES_DECRYPT(u.id_user,'nur')='" + akses.getkode() + "'");
        }
        
        idParameterTtd = Sequel.cariIsi("select concat('rmeRZ',replace(date(now()),'-',''),'',replace(time(now()),':',''))");

        try {
            URL = prop.getProperty("URLTTDKELUARGAPASIEN") + idParameterTtd;
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        Valid.cetakQrTte(URL, Sequel.cariFolderTte(), "QRTte.jpg", "select logo from setting");
        Sequel.menyimpanQrTte("parameter_ttd_rme", "'" + idParameterTtd + "','" + usernya + "','" + pwdnya + "','" + TNoRw.getText() + "','" + TNoRM.getText() + "','"
                + Sequel.cariIsi("select kode_erm from master_nomor_dokumen_erm where nm_dokumen like '%SURAT PERNYATAAN DNR (DO NOT RESUCITATE)%'") + "',"
                + "'" + Sequel.cariIsi("select now()") + "'", "file QRCode URL Ttd", Sequel.cariFolderPrintTte());

        try {
            ((RMSuratPernyataanDNR.Painter) gambarQR).setImage("");
            ResultSet hasil = koneksi.createStatement().executeQuery(
                    "select qr_code from parameter_ttd_rme where id_parameter = '" + idParameterTtd + "'");
            for (int I = 0; hasil.next(); I++) {
                Blob blob = hasil.getBlob(1);
                ((RMSuratPernyataanDNR.Painter) gambarQR).setImageIcon(new javax.swing.ImageIcon(
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
}
