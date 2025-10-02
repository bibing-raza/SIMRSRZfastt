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

import inventory.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable2;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import simrskhanza.DlgCariBangsal;
import widget.Button;

/**
 *
 * @author dosen
 */
public final class ApotekBPJSKirimObat extends javax.swing.JDialog {
    private final DefaultTableModel tabModeobat, tabModeObatRacikan, tabModeDetailObatRacikan;
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Connection koneksi = koneksiDB.condb();
    private BPJSCekNoKartu cekViaBPJSKartu = new BPJSCekNoKartu();
    private ApotekBPJSDaftarPelayananObat2 carisep = new ApotekBPJSDaftarPelayananObat2(null, false);
    private PreparedStatement psobat, psracikan, ps2, psobatracikan;
    private ResultSet rsobat, rsracikan, rs2, rscariobat, rsobatracikan;
    private double x = 0, y = 0, kenaikan = 0;
    private int i = 0, z = 0, row = 0, jml = 0;
    private String no_apotek = "", utc = "", pesan = "", link = koneksiDB.URLAPIAPOTEKBPJS(), kodeppkapotek = koneksiDB.KODEPPKAPOTEKBPJS(), 
            requestJson = "", URL = "", otorisasi, sql = "", aktifpcare = "no", kodedokter = "", namadokter = "", noresep = "", kandungan = "";
    private WarnaTable2 warna=new WarnaTable2();
    private WarnaTable2 warna2=new WarnaTable2();
    private WarnaTable2 warna3=new WarnaTable2();
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private ApiApotekBPJS api = new ApiApotekBPJS();
    
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public ApotekBPJSKirimObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //data di tabel grid rata tengah
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        this.setLocation(10,2);
        setSize(656,250);

        tabModeobat = new DefaultTableModel(null, new Object[]{
            "Cek", "Jumlah", "Kode Barang", "Nama Barang", "Signa 1", "Signa 2", "Jumlah Hari", "Jumlah 2", "Aturan Pakai"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 1) || (colIndex == 4) || (colIndex == 5) || (colIndex == 6)) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbNonRacikan.setModel(tabModeobat);
        tbNonRacikan.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbNonRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbNonRacikan.getColumnModel().getColumn(i);
            if (i == 0) {
                try {
                    kodeppkapotek.toString();
                    column.setPreferredWidth(30);
                } catch (Exception e) {
                    System.out.println("Notif : " + rs2);
                }
            } else if (i == 1) {
                column.setPreferredWidth(45);
            } else if (i == 2) {
                column.setPreferredWidth(120);
            } else if (i == 3) {
                column.setPreferredWidth(350);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(80);
            } else if (i == 7) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 8) {
                column.setPreferredWidth(650);
            }
        }
        warna.kolom = 6;
        tbNonRacikan.setDefaultRenderer(Object.class,warna);
        //ini posisi kolom yang datanya ingin rata tengah
        tbNonRacikan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbNonRacikan.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        
        tabModeObatRacikan = new DefaultTableModel(null, new String[]{
            "No. Rawat", "Kode Barang", "Nama Racikan", "Jumlah", "Aturan Pakai", "Keterangan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRacikan.setModel(tabModeObatRacikan);
        tbRacikan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        
        for (i = 0; i < 6; i++) {
            TableColumn column = tbRacikan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(120);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(350);
            } else if (i == 3) {
                column.setPreferredWidth(60);
            } else if (i == 4) {
                column.setPreferredWidth(650);
            } else if (i == 5) {
                column.setPreferredWidth(150);
            } 
        }

        warna2.kolom = 3;
        tbRacikan.setDefaultRenderer(Object.class,warna2);
        //ini posisi kolom yang datanya ingin rata tengah
        tbRacikan.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbRacikan.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        
        tabModeDetailObatRacikan = new DefaultTableModel(null, new Object[]{
            "Cek", "No. Resep", "Jumlah", "Kode Barang", "Nama Barang", "Signa 1", "Signa 2", "Jumlah Hari", "Dosis/Sediaan"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if ((colIndex == 0) || (colIndex == 2) || (colIndex == 5) || (colIndex == 6) || (colIndex == 7)) {
                    a = true;
                }
                return a;
            }

            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };

        tbDetailObatRacikan.setModel(tabModeDetailObatRacikan);
        tbDetailObatRacikan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDetailObatRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        
        for (i = 0; i < 9; i++) {
            TableColumn column = tbDetailObatRacikan.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setPreferredWidth(80);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(120);
            } else if (i == 4) {
                column.setPreferredWidth(450);
            } else if (i == 5) {
                column.setPreferredWidth(50);
            } else if (i == 6) {
                column.setPreferredWidth(50);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setPreferredWidth(90);
            }
        }

        warna3.kolom = 7;
        tbDetailObatRacikan.setDefaultRenderer(Object.class,warna3);
        //ini posisi kolom yang datanya ingin rata tengah
        tbDetailObatRacikan.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbDetailObatRacikan.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbDetailObatRacikan.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbDetailObatRacikan.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        jam();
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup1 = new javax.swing.JPopupMenu();
        ppConteng = new javax.swing.JMenuItem();
        ppHapusConteng = new javax.swing.JMenuItem();
        Popup2 = new javax.swing.JPopupMenu();
        ppConteng1 = new javax.swing.JMenuItem();
        ppHapusConteng1 = new javax.swing.JMenuItem();
        TNoRw = new widget.TextBox();
        Tanggal = new widget.TextBox();
        KdPj = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelisi3 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        CariDataObat = new widget.Button();
        BtnHapus = new widget.Button();
        BtnKeluar = new widget.Button();
        FormInput = new widget.PanelBiasa();
        jLabel8 = new widget.Label();
        Jam = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        ChkJln = new widget.CekBox();
        jLabel10 = new widget.Label();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        LblNoRawat = new widget.TextBox();
        TtglResep = new widget.Tanggal();
        jLabel20 = new widget.Label();
        NmPoli = new widget.TextBox();
        KdPoli = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel15 = new widget.Label();
        KdDPJP = new widget.TextBox();
        NmDPJP = new widget.TextBox();
        jLabel14 = new widget.Label();
        TResep = new widget.TextBox();
        jLabel16 = new widget.Label();
        jLabel17 = new widget.Label();
        jLabel4 = new widget.Label();
        NoKartu = new widget.TextBox();
        NoSEP = new widget.TextBox();
        jLabel18 = new widget.Label();
        Lahir = new widget.TextBox();
        jLabel19 = new widget.Label();
        TInfoPRB = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel6 = new widget.Label();
        TInfoIterasi = new widget.TextBox();
        JnsObat = new widget.ComboBox();
        Iterasi = new widget.ComboBox();
        TabResep = new javax.swing.JTabbedPane();
        Scroll = new widget.ScrollPane();
        tbNonRacikan = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        Scroll1 = new widget.ScrollPane();
        tbRacikan = new widget.Table();
        Scroll2 = new widget.ScrollPane();
        tbDetailObatRacikan = new widget.Table();

        Popup1.setName("Popup1"); // NOI18N

        ppConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppConteng.setText("Conteng Semua");
        ppConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppConteng.setIconTextGap(8);
        ppConteng.setName("ppConteng"); // NOI18N
        ppConteng.setPreferredSize(new java.awt.Dimension(160, 25));
        ppConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppContengActionPerformed(evt);
            }
        });
        Popup1.add(ppConteng);

        ppHapusConteng.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapusConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapusConteng.setText("Hapus Conteng Semua");
        ppHapusConteng.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapusConteng.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapusConteng.setIconTextGap(8);
        ppHapusConteng.setName("ppHapusConteng"); // NOI18N
        ppHapusConteng.setPreferredSize(new java.awt.Dimension(160, 25));
        ppHapusConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusContengActionPerformed(evt);
            }
        });
        Popup1.add(ppHapusConteng);

        Popup2.setName("Popup2"); // NOI18N

        ppConteng1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppConteng1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppConteng1.setText("Conteng Semua");
        ppConteng1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppConteng1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppConteng1.setIconTextGap(8);
        ppConteng1.setName("ppConteng1"); // NOI18N
        ppConteng1.setPreferredSize(new java.awt.Dimension(160, 25));
        ppConteng1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppConteng1ActionPerformed(evt);
            }
        });
        Popup2.add(ppConteng1);

        ppHapusConteng1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        ppHapusConteng1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        ppHapusConteng1.setText("Hapus Conteng Semua");
        ppHapusConteng1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapusConteng1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapusConteng1.setIconTextGap(8);
        ppHapusConteng1.setName("ppHapusConteng1"); // NOI18N
        ppHapusConteng1.setPreferredSize(new java.awt.Dimension(160, 25));
        ppHapusConteng1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ppHapusConteng1ActionPerformed(evt);
            }
        });
        Popup2.add(ppHapusConteng1);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N

        Tanggal.setHighlighter(null);
        Tanggal.setName("Tanggal"); // NOI18N

        KdPj.setHighlighter(null);
        KdPj.setName("KdPj"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Obat Apotek BPJS ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 48));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 9));

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
        panelisi3.add(BtnSimpan);

        CariDataObat.setForeground(new java.awt.Color(0, 0, 0));
        CariDataObat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        CariDataObat.setText("Data obat");
        CariDataObat.setToolTipText("");
        CariDataObat.setName("CariDataObat"); // NOI18N
        CariDataObat.setPreferredSize(new java.awt.Dimension(100, 30));
        CariDataObat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CariDataObatActionPerformed(evt);
            }
        });
        panelisi3.add(CariDataObat);

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
        panelisi3.add(BtnHapus);

        BtnKeluar.setForeground(new java.awt.Color(0, 0, 0));
        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('5');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+5");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        FormInput.setBackground(new java.awt.Color(215, 225, 215));
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 165));
        FormInput.setLayout(null);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Input :");
        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 40, 90, 23);

        Jam.setEditable(false);
        Jam.setForeground(new java.awt.Color(0, 0, 0));
        Jam.setHighlighter(null);
        Jam.setName("Jam"); // NOI18N
        FormInput.add(Jam);
        Jam.setBounds(825, 130, 80, 24);

        DTPTgl.setEditable(false);
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-10-2025" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.setPreferredSize(new java.awt.Dimension(100, 23));
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(95, 40, 90, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbJamKeyPressed(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(380, 40, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbMnt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbMntKeyPressed(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(430, 40, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.setPreferredSize(new java.awt.Dimension(50, 23));
        cmbDtk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmbDtkKeyPressed(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(480, 40, 45, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setEnabled(false);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.setPreferredSize(new java.awt.Dimension(22, 23));
        FormInput.add(ChkJln);
        ChkJln.setBounds(530, 40, 22, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Pasien :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(68, 23));
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 10, 90, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        TPasien.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TPasien);
        TPasien.setBounds(324, 10, 320, 23);

        TNoRM.setEditable(false);
        TNoRM.setForeground(new java.awt.Color(0, 0, 0));
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(TNoRM);
        TNoRM.setBounds(230, 10, 90, 23);

        LblNoRawat.setEditable(false);
        LblNoRawat.setForeground(new java.awt.Color(0, 0, 0));
        LblNoRawat.setName("LblNoRawat"); // NOI18N
        LblNoRawat.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(LblNoRawat);
        LblNoRawat.setBounds(95, 10, 130, 23);

        TtglResep.setEditable(false);
        TtglResep.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01-10-2025" }));
        TtglResep.setDisplayFormat("dd-MM-yyyy");
        TtglResep.setName("TtglResep"); // NOI18N
        TtglResep.setOpaque(false);
        TtglResep.setPreferredSize(new java.awt.Dimension(95, 23));
        FormInput.add(TtglResep);
        TtglResep.setBounds(730, 130, 90, 23);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Tgl. Resep :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 23));
        FormInput.add(jLabel20);
        jLabel20.setBounds(645, 130, 80, 23);

        NmPoli.setEditable(false);
        NmPoli.setForeground(new java.awt.Color(0, 0, 0));
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(174, 130, 470, 23);

        KdPoli.setEditable(false);
        KdPoli.setForeground(new java.awt.Color(0, 0, 0));
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(95, 130, 75, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Poliklinik :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(0, 130, 90, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Dokter DPJP :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 100, 90, 23);

        KdDPJP.setEditable(false);
        KdDPJP.setForeground(new java.awt.Color(0, 0, 0));
        KdDPJP.setHighlighter(null);
        KdDPJP.setName("KdDPJP"); // NOI18N
        FormInput.add(KdDPJP);
        KdDPJP.setBounds(95, 100, 75, 23);

        NmDPJP.setEditable(false);
        NmDPJP.setForeground(new java.awt.Color(0, 0, 0));
        NmDPJP.setHighlighter(null);
        NmDPJP.setName("NmDPJP"); // NOI18N
        FormInput.add(NmDPJP);
        NmDPJP.setBounds(174, 100, 250, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("No. Resep :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(425, 100, 70, 23);

        TResep.setEditable(false);
        TResep.setForeground(new java.awt.Color(0, 0, 0));
        TResep.setName("TResep"); // NOI18N
        FormInput.add(TResep);
        TResep.setBounds(500, 100, 80, 23);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Iterasi :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(655, 100, 70, 23);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Jns. Obat :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(260, 70, 70, 23);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Status PRB :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(645, 10, 80, 23);

        NoKartu.setEditable(false);
        NoKartu.setForeground(new java.awt.Color(0, 0, 0));
        NoKartu.setName("NoKartu"); // NOI18N
        FormInput.add(NoKartu);
        NoKartu.setBounds(730, 70, 150, 23);

        NoSEP.setEditable(false);
        NoSEP.setForeground(new java.awt.Color(0, 0, 0));
        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N
        FormInput.add(NoSEP);
        NoSEP.setBounds(95, 70, 160, 23);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("No.SEP :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 70, 90, 23);

        Lahir.setEditable(false);
        Lahir.setForeground(new java.awt.Color(0, 0, 0));
        Lahir.setHighlighter(null);
        Lahir.setName("Lahir"); // NOI18N
        FormInput.add(Lahir);
        Lahir.setBounds(265, 40, 110, 23);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Lahir :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(190, 40, 70, 23);

        TInfoPRB.setEditable(false);
        TInfoPRB.setForeground(new java.awt.Color(0, 51, 204));
        TInfoPRB.setName("TInfoPRB"); // NOI18N
        FormInput.add(TInfoPRB);
        TInfoPRB.setBounds(730, 10, 110, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("No. Kartu :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(645, 70, 80, 23);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Status Iterasi :");
        jLabel6.setName("jLabel6"); // NOI18N
        FormInput.add(jLabel6);
        jLabel6.setBounds(645, 40, 80, 23);

        TInfoIterasi.setEditable(false);
        TInfoIterasi.setForeground(new java.awt.Color(255, 0, 0));
        TInfoIterasi.setHighlighter(null);
        TInfoIterasi.setName("TInfoIterasi"); // NOI18N
        FormInput.add(TInfoIterasi);
        TInfoIterasi.setBounds(730, 40, 120, 23);

        JnsObat.setForeground(new java.awt.Color(0, 0, 0));
        JnsObat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1. Obat PRB", "2. Obat Kronis Belum Stabil", "3. Obat Kemoterapi" }));
        JnsObat.setSelectedIndex(1);
        JnsObat.setName("JnsObat"); // NOI18N
        FormInput.add(JnsObat);
        JnsObat.setBounds(335, 70, 165, 23);

        Iterasi.setForeground(new java.awt.Color(0, 0, 0));
        Iterasi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0. Tanpa Iterasi", "1. Dengan Iterasi", "2. Dengan Iterasi" }));
        Iterasi.setName("Iterasi"); // NOI18N
        FormInput.add(Iterasi);
        Iterasi.setBounds(730, 100, 120, 23);

        internalFrame1.add(FormInput, java.awt.BorderLayout.PAGE_START);

        TabResep.setBackground(new java.awt.Color(255, 255, 253));
        TabResep.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabResep.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        TabResep.setName("TabResep"); // NOI18N

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbNonRacikan.setComponentPopupMenu(Popup1);
        tbNonRacikan.setName("tbNonRacikan"); // NOI18N
        tbNonRacikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNonRacikanMouseClicked(evt);
            }
        });
        tbNonRacikan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbNonRacikanPropertyChange(evt);
            }
        });
        tbNonRacikan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbNonRacikanKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbNonRacikan);

        TabResep.addTab("Resep Non Racikan", Scroll);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 102));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);
        Scroll1.setPreferredSize(new java.awt.Dimension(454, 90));

        tbRacikan.setName("tbRacikan"); // NOI18N
        Scroll1.setViewportView(tbRacikan);

        jPanel3.add(Scroll1, java.awt.BorderLayout.PAGE_START);

        Scroll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        tbDetailObatRacikan.setAutoCreateRowSorter(true);
        tbDetailObatRacikan.setComponentPopupMenu(Popup2);
        tbDetailObatRacikan.setName("tbDetailObatRacikan"); // NOI18N
        tbDetailObatRacikan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDetailObatRacikanMouseClicked(evt);
            }
        });
        tbDetailObatRacikan.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tbDetailObatRacikanPropertyChange(evt);
            }
        });
        Scroll2.setViewportView(tbDetailObatRacikan);

        jPanel3.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabResep.addTab("Resep Racikan", jPanel3);

        internalFrame1.add(TabResep, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tbNonRacikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNonRacikanMouseClicked
        if(tbNonRacikan.getRowCount()!=0){
            try {
                if(tbNonRacikan.getSelectedColumn()==5||tbNonRacikan.getSelectedColumn()==4){
                   HitungKapasitas(tbNonRacikan.getSelectedRow());
                    getDataobat();
                }
            } catch (java.lang.NullPointerException e) {
            }
            
            if(evt.getClickCount()==2){
                if(akses.getform().equals("DlgPemberianObat")){
                    dispose();
                }
            }
        }
}//GEN-LAST:event_tbNonRacikanMouseClicked

    private void tbNonRacikanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNonRacikanKeyPressed
        if(tbNonRacikan.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_ENTER){
                try {
                 if(tbNonRacikan.getSelectedColumn()==6||tbNonRacikan.getSelectedColumn()==4||tbNonRacikan.getSelectedColumn()==5){
                    HitungKapasitas(tbNonRacikan.getSelectedRow());
                    getDataobat();
                }
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbNonRacikanKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed
    
private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
    if (TNoRw.getText().trim().equals("")) {
        Valid.textKosong(TNoRw, "No Rawat");
    } else if (NoSEP.getText().trim().equals("")) {
        Valid.textKosong(NoSEP, "Nomor Sep");
    } else if (KdDPJP.getText().trim().equals("")) {
        Valid.textKosong(KdDPJP, "Dokter");
    } else if (KdPoli.getText().trim().equals("")) {
        Valid.textKosong(KdPoli, "Poliklinik");
    } else if (TResep.getText().trim().equals("")) {
        Valid.textKosong(TResep, "Nomor Resep");
    } else if (Lahir.getText().trim().equals("")) {
        Valid.textKosong(Lahir, "Tgl. Lahir");
    } else {
        int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("x-timestamp", utc);
                headers.add("x-signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
                requestEntity = new HttpEntity(headers);

                if (Iterasi.getSelectedIndex() == 0) {
                    System.out.println("Tanpa/belum iterasi");
                    URL = link + "/sjpresep/v3/insert";
                    System.out.println(URL);
                    requestJson = "{"
                            + "\"TGLSJP\": \"" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "\","
                            + "\"REFASALSJP\": \"" + NoSEP.getText() + "\","
                            + "\"POLIRSP\": \"" + KdPoli.getText() + "\","
                            + "\"KDJNSOBAT\": \"" + JnsObat.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"NORESEP\": \"" + TResep.getText() + "\", "
                            + "\"IDUSERSJP\": \"RS_" + akses.getkode() + "\","
                            + "\"TGLRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\", "
                            + "\"TGLPELRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\","
                            + "\"KdDokter\": \"" + KdDPJP.getText() + "\","
                            + "\"iterasi\":\"" + Iterasi.getSelectedItem().toString().substring(0, 1) + "\""
                            + "}";

                    System.out.println("Resep : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("data = " + nameNode);
                    System.out.println("error = " + nameNode.path("message").asText());

                    if (!nameNode.path("code").asText().equals("200")) {
                        JOptionPane.showMessageDialog(null, "ERROR : " + nameNode.path("message").asText());
                        if (TabResep.getSelectedIndex() == 0) {
                            ppHapusContengActionPerformed(null);
                        } else {
                            ppHapusConteng1ActionPerformed(null);
                        }                   
                    }

                    if (nameNode.path("code").asText().equals("200")) {
                        response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                        System.out.println("Response : " + response);
                        if (Sequel.cariInteger("SELECT COUNT(-1) FROM iter_obat_bpjs WHERE no_sep='" + NoSEP.getText() + "' ") > 1) {
                            pesan = "Iter 2";
                        } else if (Sequel.cariInteger("SELECT COUNT(-1) FROM iter_obat_bpjs WHERE no_sep='" + NoSEP.getText() + "' ") == 1) {
                            pesan = "Iter 1";
                        } else {
                            pesan = "Tanpa Iter";
                        }

                        if (Sequel.menyimpantf2("bridging_apotek_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 13,
                                new String[]{
                                    response.path("noSep_Kunjungan").asText(),
                                    response.path("noApotik").asText(),
                                    TResep.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + "") + " " + Jam.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + ""),
                                    JnsObat.getSelectedItem().toString().substring(0, 1),
                                    Iterasi.getSelectedItem().toString().substring(0, 1),
                                    KdPoli.getText(),
                                    NmPoli.getText(),
                                    KdDPJP.getText(),
                                    NmDPJP.getText(),
                                    akses.getkode(),
                                    pesan,}) == true) {
                            System.out.println("Simpan No. Resep Selesai");
//                                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                            no_apotek = response.path("noApotik").asText();

                            //obat non racikan
                            if (TabResep.getSelectedIndex() == 0) {
                                jml = 0;
                                for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                    if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatnonracikan/v3/insert";
                                    System.out.println(URL);

                                    for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                        if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                            if (Valid.SetAngka(tbNonRacikan.getValueAt(i, 1).toString()) > 0) {
                                                try {
                                                    requestJson = "{"
                                                            + "\"NOSJP\": \"" + response.path("noApotik").asText() + "\","
                                                            + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                            + "\"KDOBT\": \"" + tbNonRacikan.getValueAt(i, 2).toString() + "\","
                                                            + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                            + "\"SIGNA1OBT\": " + tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                                                            + "\"SIGNA2OBT\": " + tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                            + "\"JMLOBT\": " + tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                                                            + "\"JHO\": " + tbNonRacikan.getValueAt(i, 6).toString() + ","
                                                            + "\"CatKhsObt\": \"non racikan\""
                                                            + "}";

                                                    System.out.println("Detail Obat : " + requestJson);
                                                    requestEntity = new HttpEntity(requestJson, headers);
                                                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                    nameNode = root.path("metaData");
                                                    System.out.println("data = " + nameNode);

                                                    if (nameNode.path("code").asText().equals("200")) {
                                                        if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS", 9, new String[]{
                                                            response.path("noSep_Kunjungan").asText(),
                                                            TResep.getText(),
                                                            tbNonRacikan.getValueAt(i, 2).toString(),
                                                            tbNonRacikan.getValueAt(i, 3).toString(),
                                                            tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                            "0",
                                                            no_apotek
                                                        }) == true) {
                                                            System.out.println("Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
//                                                            JOptionPane.showMessageDialog(null, "Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                                        }
                                                    } else {
                                                        System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        ppHapusContengActionPerformed(null);
                                                    }
                                                    System.out.println("non racikan = \n\n" + requestJson);
                                                } catch (Exception ex) {
                                                    System.out.println("Notifikasi : " + ex);
                                                    if (ex.toString().contains("UnknownHostException")) {
                                                        JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                        ppHapusContengActionPerformed(null);
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Silahkan ceklist obat yang akan di kirim ke Apotek Online. . .");
                                        }
                                    }

                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusContengActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat non racikan yang akan di kirim ke Apotek Online,...!!!");
                                }

                                //racikan    
                            } else if (TabResep.getSelectedIndex() == 1) {
                                jml = 0;
                                for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                    if (tbDetailObatRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatracikan/v3/insert";
                                    System.out.println(URL);
                                    for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                        if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 2).toString()) > 0) {
                                            try {
                                                requestJson = "{"
                                                        + "\"NOSJP\": \"" + no_apotek + "\","
                                                        + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                        + "\"JNSROBT\": \"R.0" + (tbDetailObatRacikan.getValueAt(i, 1).toString()) + "\","
                                                        + "\"KDOBT\": \"" + tbDetailObatRacikan.getValueAt(i, 3).toString() + "\","
                                                        + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbDetailObatRacikan.getValueAt(i, 3).toString()) + "\","
                                                        + "\"SIGNA1OBT\": " + tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                        + "\"SIGNA2OBT\": " + tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", ".") + ","
                                                        + "\"PERMINTAAN\": " + tbDetailObatRacikan.getValueAt(i, 8).toString() + ","
                                                        + "\"JMLOBT\": " + tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", ".") + ","
                                                        + "\"JHO\": " + tbDetailObatRacikan.getValueAt(i, 7).toString() + ","
                                                        + "\"CatKhsObt\": \"RACIKAN " + (i + 1) + "\""
                                                        + "}";
                                                requestEntity = new HttpEntity(requestJson, headers);
                                                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                nameNode = root.path("metaData");
                                                System.out.println("data = " + nameNode);
                                                if (nameNode.path("code").asText().equals("200")) {
                                                    if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS Racikan", 9, new String[]{
                                                        response.path("noSep_Kunjungan").asText(),
                                                        TResep.getText(),
                                                        tbDetailObatRacikan.getValueAt(i, 3).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 4).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", "."),
                                                        "1",
                                                        no_apotek
                                                    }) == true) {
                                                        System.out.println("Obat " + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
//                                                        JOptionPane.showMessageDialog(null, "Obat racikan" + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
                                                    }
                                                } else {
                                                    System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    ppHapusConteng1ActionPerformed(null);
                                                }

                                                System.out.println("racikan = \n\n" + requestJson);
                                            } catch (Exception ex) {
                                                System.out.println("Notifikasi : " + ex);
                                                if (ex.toString().contains("UnknownHostException")) {
                                                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                    ppHapusConteng1ActionPerformed(null);
                                                }
                                            }
                                        }
                                    }
                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat racikan berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusConteng1ActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat racikan yang akan di kirim ke Apotek Online,...!!!");
                                }
                            }
                        }
                    }

                } else if (Iterasi.getSelectedIndex() == 1) {
                    System.out.println("Dengan iterasi 1 x:");
                    URL = link + "/sjpresep/v3/insert";
                    System.out.println(URL);
                    requestJson = "{"
                            + "\"TGLSJP\": \"" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "\","
                            + "\"REFASALSJP\": \"" + NoSEP.getText() + "\","
                            + "\"POLIRSP\": \"" + KdPoli.getText() + "\","
                            + "\"KDJNSOBAT\": \"" + JnsObat.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"NORESEP\": \"" + TResep.getText() + "\", "
                            + "\"IDUSERSJP\": \"RS_" + akses.getkode() + "\","
                            + "\"TGLRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\", "
                            + "\"TGLPELRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\","
                            + "\"KdDokter\": \"" + KdDPJP.getText() + "\","
                            + "\"iterasi\":\"" + Iterasi.getSelectedItem().toString().substring(0, 1) + "\""
                            + "}";

                    System.out.println("Resep : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("data = " + nameNode);
                    System.out.println("error = " + nameNode.path("message").asText());

                    if (nameNode.path("code").asText().equals("200")) {
                        response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                        System.out.println("Response : " + response);
                        if (Sequel.menyimpantf2("bridging_apotek_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 13,
                                new String[]{
                                    response.path("noSep_Kunjungan").asText(),
                                    response.path("noApotik").asText(),
                                    TResep.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + "") + " " + Jam.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + ""),
                                    JnsObat.getSelectedItem().toString().substring(0, 1),
                                    Iterasi.getSelectedItem().toString().substring(0, 1),
                                    KdPoli.getText(),
                                    NmPoli.getText(),
                                    KdDPJP.getText(),
                                    NmDPJP.getText(),
                                    akses.getkode(),
                                    "Dengan Iter 1 Kali",}) == true) {
                            System.out.println("Simpan No Resep Selesai");
//                                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                            no_apotek = response.path("noApotik").asText();

                            //obat non racikan
                            if (TabResep.getSelectedIndex() == 0) {
                                jml = 0;
                                for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                    if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatnonracikan/v3/insert";
                                    System.out.println(URL);
                                    for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                        if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                            if (Valid.SetAngka(tbNonRacikan.getValueAt(i, 1).toString()) > 0) {
                                                try {
                                                    requestJson = "{"
                                                            + "\"NOSJP\": \"" + response.path("noApotik").asText() + "\","
                                                            + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                            + "\"KDOBT\": \"" + tbNonRacikan.getValueAt(i, 2).toString() + "\","
                                                            + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                            + "\"SIGNA1OBT\": " + tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                                                            + "\"SIGNA2OBT\": " + tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                            + "\"JMLOBT\": " + tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                                                            + "\"JHO\": " + tbNonRacikan.getValueAt(i, 6).toString() + ","
                                                            + "\"CatKhsObt\": \"non racikan\""
                                                            + "}";

                                                    System.out.println("Detail Obat : " + requestJson);
                                                    requestEntity = new HttpEntity(requestJson, headers);
                                                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                    nameNode = root.path("metaData");
                                                    System.out.println("data = " + nameNode);
                                                    if (nameNode.path("code").asText().equals("200")) {
                                                        if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS", 9, new String[]{
                                                            response.path("noSep_Kunjungan").asText(),
                                                            TResep.getText(),
                                                            tbNonRacikan.getValueAt(i, 2).toString(),
                                                            tbNonRacikan.getValueAt(i, 3).toString(),
                                                            tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                            "0",
                                                            no_apotek
                                                        }) == true) {
                                                            System.out.println("Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
//                                                            JOptionPane.showMessageDialog(null, "Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                                        }
                                                    } else {
                                                        System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        ppHapusContengActionPerformed(null);
                                                    }

                                                    System.out.println("non racikan = \n\n" + requestJson);
                                                } catch (Exception ex) {
                                                    System.out.println("Notifikasi : " + ex);
                                                    if (ex.toString().contains("UnknownHostException")) {
                                                        JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                        ppHapusContengActionPerformed(null);
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Silahkan ceklist obat yang akan di kirim ke Apotek Online. . .");
                                        }
                                    }
                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusContengActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat non racikan yang akan di kirim ke Apotek Online,...!!!");
                                }

                                //racikan
                            } else if (TabResep.getSelectedIndex() == 1) {
                                jml = 0;
                                for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                    if (tbDetailObatRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatracikan/v3/insert";
                                    System.out.println(URL);
                                    for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                        if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 2).toString()) > 0) {
                                            try {
                                                requestJson = "{"
                                                        + "\"NOSJP\": \"" + no_apotek + "\","
                                                        + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                        + "\"JNSROBT\": \"R.0" + (tbDetailObatRacikan.getValueAt(i, 1).toString()) + "\","
                                                        + "\"KDOBT\": \"" + tbDetailObatRacikan.getValueAt(i, 3).toString() + "\","
                                                        + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbDetailObatRacikan.getValueAt(i, 3).toString()) + "\","
                                                        + "\"SIGNA1OBT\": " + tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                        + "\"SIGNA2OBT\": " + tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", ".") + ","
                                                        + "\"PERMINTAAN\": " + tbDetailObatRacikan.getValueAt(i, 8).toString() + ","
                                                        + "\"JMLOBT\": " + tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", ".") + ","
                                                        + "\"JHO\": " + tbDetailObatRacikan.getValueAt(i, 7).toString() + ","
                                                        + "\"CatKhsObt\": \"RACIKAN " + (i + 1) + "\""
                                                        + "}";

                                                requestEntity = new HttpEntity(requestJson, headers);
                                                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                nameNode = root.path("metaData");
                                                System.out.println("data = " + nameNode);
                                                if (nameNode.path("code").asText().equals("200")) {
                                                    if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS Racikan", 9, new String[]{
                                                        response.path("noSep_Kunjungan").asText(),
                                                        TResep.getText(),
                                                        tbDetailObatRacikan.getValueAt(i, 3).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 4).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", "."),
                                                        "1",
                                                        no_apotek
                                                    }) == true) {
                                                        System.out.println("Obat " + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
//                                                        JOptionPane.showMessageDialog(null, "Obat racikan" + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
                                                    }
                                                } else {
                                                    System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    ppHapusConteng1ActionPerformed(null);
                                                }

                                                System.out.println("racikan = \n\n" + requestJson);
                                            } catch (Exception ex) {
                                                System.out.println("Notifikasi : " + ex);
                                                if (ex.toString().contains("UnknownHostException")) {
                                                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                    ppHapusConteng1ActionPerformed(null);
                                                }
                                            }
                                        }
                                    }
                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat racikan berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusConteng1ActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat racikan yang akan di kirim ke Apotek Online,...!!!");
                                }
                            }
                        }
                    }

                } else if (Iterasi.getSelectedIndex() == 2) {
                    System.out.println("Dengan iterasi 2 x:");
                    URL = link + "/sjpresep/v3/insert";
                    System.out.println(URL);
                    requestJson = "{"
                            + "\"TGLSJP\": \"" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "\","
                            + "\"REFASALSJP\": \"" + NoSEP.getText() + "\","
                            + "\"POLIRSP\": \"" + KdPoli.getText() + "\","
                            + "\"KDJNSOBAT\": \"" + JnsObat.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"NORESEP\": \"" + TResep.getText() + "\", "
                            + "\"IDUSERSJP\": \"RS_" + akses.getkode() + "\","
                            + "\"TGLRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\", "
                            + "\"TGLPELRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\","
                            + "\"KdDokter\": \"" + KdDPJP.getText() + "\","
                            + "\"iterasi\":\"" + Iterasi.getSelectedItem().toString().substring(0, 1) + "\""
                            + "}  ";
                    System.out.println("Resep : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("data = " + nameNode);
                    System.out.println("error = " + nameNode.path("message").asText());
                    if (nameNode.path("code").asText().equals("200")) {
                        response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                        System.out.println("Response : " + response);
                        if (Sequel.menyimpantf2("bridging_apotek_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 13,
                                new String[]{
                                    response.path("noSep_Kunjungan").asText(),
                                    response.path("noApotik").asText(),
                                    TResep.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + "") + " " + Jam.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + ""),
                                    JnsObat.getSelectedItem().toString().substring(0, 1),
                                    Iterasi.getSelectedItem().toString().substring(0, 1),
                                    KdPoli.getText(),
                                    NmPoli.getText(),
                                    KdDPJP.getText(),
                                    NmDPJP.getText(),
                                    akses.getkode(),
                                    "Dengan Iter 2 Kali",}) == true) {
                            System.out.println("Simpan No Resep Selesai");
//                                JOptionPane.showMessageDialog(null, nameNode.path("message").asText());
                            no_apotek = response.path("noApotik").asText();

                            //obat non racikan
                            if (TabResep.getSelectedIndex() == 0) {
                                jml = 0;
                                for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                    if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatnonracikan/v3/insert";
                                    System.out.println(URL);
                                    for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                                        if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                            if (Valid.SetAngka(tbNonRacikan.getValueAt(i, 1).toString()) > 0) {
                                                try {
                                                    requestJson = "{"
                                                            + "\"NOSJP\": \"" + response.path("noApotik").asText() + "\","
                                                            + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                            + "\"KDOBT\": \"" + tbNonRacikan.getValueAt(i, 2).toString() + "\","
                                                            + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                            + "\"SIGNA1OBT\": " + tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", ".") + ","
                                                            + "\"SIGNA2OBT\": " + tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                            + "\"JMLOBT\": " + tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", ".") + ","
                                                            + "\"JHO\": " + tbNonRacikan.getValueAt(i, 6).toString() + ","
                                                            + "\"CatKhsObt\": \"non racikan\""
                                                            + "}";
                                                    System.out.println("Detail Obat : " + requestJson);
                                                    requestEntity = new HttpEntity(requestJson, headers);
                                                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                    nameNode = root.path("metaData");
                                                    System.out.println("data = " + nameNode);
                                                    if (nameNode.path("code").asText().equals("200")) {
                                                        if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS", 9, new String[]{
                                                            response.path("noSep_Kunjungan").asText(),
                                                            TResep.getText(),
                                                            tbNonRacikan.getValueAt(i, 2).toString(),
                                                            tbNonRacikan.getValueAt(i, 3).toString(),
                                                            tbNonRacikan.getValueAt(i, 1).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 4).toString().replaceAll(",", "."),
                                                            tbNonRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                            "0",
                                                            no_apotek
                                                        }) == true) {
                                                            System.out.println("Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
//                                                            JOptionPane.showMessageDialog(null, "Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                                        }
                                                    } else {
                                                        System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                        ppHapusContengActionPerformed(null);
                                                    }

                                                    System.out.println("non racikan = \n\n" + requestJson);
                                                } catch (Exception ex) {
                                                    System.out.println("Notifikasi : " + ex);
                                                    if (ex.toString().contains("UnknownHostException")) {
                                                        JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                        ppHapusContengActionPerformed(null);
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Silahkan ceklist obat yang akan di kirim ke Apotek Online. . .");
                                        }
                                    }
                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusContengActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat non racikan yang akan di kirim ke Apotek Online,...!!!");
                                }

                                //racikan
                            } else if (TabResep.getSelectedIndex() == 1) {
                                jml = 0;
                                for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                    if (tbDetailObatRacikan.getValueAt(i, 0).toString().equals("true")) {
                                        jml++;
                                    }
                                }

                                if (jml > 0) {
                                    URL = link + "/obatracikan/v3/insert";
                                    System.out.println(URL);
                                    for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                                        if (Valid.SetAngka(tbDetailObatRacikan.getValueAt(i, 2).toString()) > 0) {
                                            try {
                                                requestJson = "{"
                                                        + "\"NOSJP\": \"" + no_apotek + "\","
                                                        + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                        + "\"JNSROBT\": \"R.0" + (tbDetailObatRacikan.getValueAt(i, 1).toString()) + "\","
                                                        + "\"KDOBT\": \"" + tbDetailObatRacikan.getValueAt(i, 3).toString() + "\","
                                                        + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng_apotek_bpjs=?", tbDetailObatRacikan.getValueAt(i, 3).toString()) + "\","
                                                        + "\"SIGNA1OBT\": " + tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", ".") + ","
                                                        + "\"SIGNA2OBT\": " + tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", ".") + ","
                                                        + "\"PERMINTAAN\": " + tbDetailObatRacikan.getValueAt(i, 8).toString() + ","
                                                        + "\"JMLOBT\": " + tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", ".") + ","
                                                        + "\"JHO\": " + tbDetailObatRacikan.getValueAt(i, 7).toString() + ","
                                                        + "\"CatKhsObt\": \"RACIKAN " + (i + 1) + "\""
                                                        + "}";
                                                requestEntity = new HttpEntity(requestJson, headers);
                                                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                                nameNode = root.path("metaData");
                                                System.out.println("data = " + nameNode);
                                                if (nameNode.path("code").asText().equals("200")) {
                                                    if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS Racikan", 9, new String[]{
                                                        response.path("noSep_Kunjungan").asText(),
                                                        TResep.getText(),
                                                        tbDetailObatRacikan.getValueAt(i, 3).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 4).toString(),
                                                        tbDetailObatRacikan.getValueAt(i, 2).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 5).toString().replaceAll(",", "."),
                                                        tbDetailObatRacikan.getValueAt(i, 6).toString().replaceAll(",", "."),
                                                        "1",
                                                        no_apotek
                                                    }) == true) {
                                                        System.out.println("Obat " + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
//                                                        JOptionPane.showMessageDialog(null, "Obat racikan" + tbDetailObatRacikan.getValueAt(i, 4).toString() + " Berhasil disimpan");
                                                    }
                                                } else {
                                                    System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                                    ppHapusConteng1ActionPerformed(null);
                                                }

                                                System.out.println("racikan = \n\n" + requestJson);
                                            } catch (Exception ex) {
                                                System.out.println("Notifikasi : " + ex);
                                                if (ex.toString().contains("UnknownHostException")) {
                                                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                                    ppHapusConteng1ActionPerformed(null);
                                                }
                                            }
                                        }
                                    }
                                    if (Sequel.cariInteger("select count(-1) from bridging_apotek_bpjs_obat where no_sep='" + response.path("noSep_Kunjungan").asText() + "'") > 0) {
                                        JOptionPane.showMessageDialog(null, "Obat racikan berhasil disimpan & dikirim. . .");
                                    }
                                    ppHapusConteng1ActionPerformed(null);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu obat racikan yang akan di kirim ke Apotek Online,...!!!");
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    tampil(noresep);
                }
            }
        }
    }
}//GEN-LAST:event_BtnSimpanActionPerformed
  
    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt,BtnKeluar,cmbJam);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void cmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbJamKeyPressed
        Valid.pindah(evt, DTPTgl, cmbMnt);
    }//GEN-LAST:event_cmbJamKeyPressed

    private void cmbMntKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbMntKeyPressed
        Valid.pindah(evt, cmbJam, cmbDtk);
    }//GEN-LAST:event_cmbMntKeyPressed

    private void cmbDtkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbDtkKeyPressed
        Valid.pindah(evt, cmbMnt, JnsObat);
    }//GEN-LAST:event_cmbDtkKeyPressed

    private void tbNonRacikanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbNonRacikanPropertyChange
        if(this.isVisible()==true){
            getDataobat();            
        }
    }//GEN-LAST:event_tbNonRacikanPropertyChange

    private void tbDetailObatRacikanPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tbDetailObatRacikanPropertyChange
        if(this.isVisible()==true){
            getDatadetailobatracikan();
        }
    }//GEN-LAST:event_tbDetailObatRacikanPropertyChange

    private void tbDetailObatRacikanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDetailObatRacikanMouseClicked
        if(tbNonRacikan.getRowCount()!=0){
            try {
                getDatadetailobatracikan();
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_tbDetailObatRacikanMouseClicked

    private void CariDataObatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CariDataObatActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        ApotekBPJSDaftarPelayananObat2 resume = new ApotekBPJSDaftarPelayananObat2(null, true);
        resume.setNoRm(NoSEP.getText());
        resume.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        resume.setLocationRelativeTo(internalFrame1);
        resume.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_CariDataObatActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbNonRacikan.getSelectedRow() != -1) {
            int reply = JOptionPane.showConfirmDialog(rootPane, "Yakin mau dihapus obat " + tbNonRacikan.getValueAt(tbNonRacikan.getSelectedRow(), 2) + " (" + tbNonRacikan.getValueAt(tbNonRacikan.getSelectedRow(), 3) + ") ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                tabModeobat.removeRow(tbNonRacikan.getSelectedRow());
            } else {
                tampil(noresep);
            }
        }

        if (tbDetailObatRacikan.getSelectedRow() != -1) {
            int reply = JOptionPane.showConfirmDialog(rootPane, "Yakin mau dihapus obat RACIKAN " + tbDetailObatRacikan.getValueAt(tbDetailObatRacikan.getSelectedRow(), 3) + " (" + tbDetailObatRacikan.getValueAt(tbDetailObatRacikan.getSelectedRow(), 4) + ") ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                tabModeDetailObatRacikan.removeRow(tbDetailObatRacikan.getSelectedRow());
            } else {
                tampil(noresep);
            }
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnKeluar, CariDataObat);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void ppContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppContengActionPerformed
        if (tbNonRacikan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa diconteng...!!!!");
        } else {
            for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                tbNonRacikan.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_ppContengActionPerformed

    private void ppHapusContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusContengActionPerformed
        if (tbNonRacikan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa diconteng...!!!!");
        } else {
            for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                tbNonRacikan.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_ppHapusContengActionPerformed

    private void ppConteng1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppConteng1ActionPerformed
        if (tbDetailObatRacikan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa diconteng...!!!!");
        } else {
            for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                tbDetailObatRacikan.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_ppConteng1ActionPerformed

    private void ppHapusConteng1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ppHapusConteng1ActionPerformed
        if (tbDetailObatRacikan.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang bisa diconteng...!!!!");
        } else {
            for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                tbDetailObatRacikan.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_ppHapusConteng1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            ApotekBPJSKirimObat dialog = new ApotekBPJSKirimObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button CariDataObat;
    private widget.CekBox ChkJln;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.ComboBox Iterasi;
    private widget.TextBox Jam;
    private widget.ComboBox JnsObat;
    private widget.TextBox KdDPJP;
    private widget.TextBox KdPj;
    private widget.TextBox KdPoli;
    private widget.TextBox Lahir;
    private widget.TextBox LblNoRawat;
    private widget.TextBox NmDPJP;
    private widget.TextBox NmPoli;
    private widget.TextBox NoKartu;
    private widget.TextBox NoSEP;
    private javax.swing.JPopupMenu Popup1;
    private javax.swing.JPopupMenu Popup2;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll2;
    private widget.TextBox TInfoIterasi;
    private widget.TextBox TInfoPRB;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox TResep;
    private javax.swing.JTabbedPane TabResep;
    private widget.TextBox Tanggal;
    private widget.Tanggal TtglResep;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private javax.swing.JPanel jPanel3;
    private widget.panelisi panelisi3;
    private javax.swing.JMenuItem ppConteng;
    private javax.swing.JMenuItem ppConteng1;
    private javax.swing.JMenuItem ppHapusConteng;
    private javax.swing.JMenuItem ppHapusConteng1;
    private widget.Table tbDetailObatRacikan;
    private widget.Table tbNonRacikan;
    private widget.Table tbRacikan;
    // End of variables declaration//GEN-END:variables
    private widget.TextBox noSJP,noResep;
    
    public void tampil(String no_resep) {
        this.noresep = no_resep;        
        Map<String, Integer> hitungKode = new HashMap<>();
        Valid.tabelKosong(tabModeobat);
        Valid.tabelKosong(tabModeObatRacikan);
        Valid.tabelKosong(tabModeDetailObatRacikan);
        
        try {
            ps2 = koneksi.prepareStatement("select ro.no_resep, ro.tgl_perawatan, ro.jam, ro.no_rawat, p.no_rkm_medis, p.nm_pasien, ro.kd_dokter, d.nm_dokter "
                    + "from resep_obat ro inner join reg_periksa rp on rp.no_rawat=ro.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join dokter d on d.kd_dokter=ro.kd_dokter where ro.no_resep=?");
            try {
                ps2.setString(1, no_resep);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    //non racikan
                    psobat = koneksi.prepareStatement("select m.kode_brng_apotek_bpjs, db.nama_brng, dpo.jml, "
                            + "CONCAT_WS(', ', at.aturan1, at.aturan2, at.aturan3, at.waktu1, at.waktu2, at.keterangan) aturanPakai "
                            + "from detail_pemberian_obat dpo inner join maping_obat_apotek_bpjs m on dpo.kode_brng=m.kode_brng inner join databarang db on dpo.kode_brng=db.kode_brng "
                            + "left join aturan_pakai at on at.no_rawat=dpo.no_rawat and at.tgl_perawatan=dpo.tgl_perawatan and at.jam=dpo.jam where "
                            + "dpo.tgl_perawatan=? and dpo.jam=? and dpo.no_rawat=? and db.kode_brng not in ('B000001938','B000002727','B000002610','B000002706','B000002722') "
                            + "group by db.kode_brng, dpo.tgl_perawatan, dpo.jam, dpo.no_rawat order by db.kode_brng");
                    try {
                        psobat.setString(1, rs2.getString("tgl_perawatan"));
                        psobat.setString(2, rs2.getString("jam"));
                        psobat.setString(3, rs2.getString("no_rawat"));                        
                        rsobat = psobat.executeQuery();
                        while (rsobat.next()) {
                            if (rsobat.getString("jml").equals("15")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 1, 0.5, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("1") || rsobat.getString("jml").equals("2") || rsobat.getString("jml").equals("3") || rsobat.getString("jml").equals("4") || rsobat.getString("jml").equals("5") || rsobat.getString("jml").equals("6") || rsobat.getString("jml").equals("7")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 1, 1, rsobat.getString("jml"), rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("23")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 1, 1, 23, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("45")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 1, 1.5, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("60")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 2, 1, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("90")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 3, 1, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else if (rsobat.getString("jml").equals("120")) {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 4, 1, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            } else {
                                tabModeobat.addRow(new Object[]{false, rsobat.getString("jml"), rsobat.getString("kode_brng_apotek_bpjs"),
                                    rsobat.getString("nama_brng"), 1, 1, 30, rsobat.getString("jml"), rsobat.getString("aturanPakai").replaceAll(", , , , ", "-")});
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    } finally {
                        if (rsobat != null) {
                            rsobat.close();
                        }

                        if (psobat != null) {
                            psobat.close();
                        }
                    }

                    for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                        getDataobat(i);
                    }

                    //racikan
                    psracikan = koneksi.prepareStatement("select dpo.no_rawat, dpo.kode_brng, db.nama_brng, dpo.jml, db.kapasitas, dpo.tgl_perawatan, dpo.jam, "
                            + "CONCAT_WS(', ', at.aturan1, at.aturan2, at.aturan3, at.waktu1, at.waktu2) aturanPakai, at.keterangan from detail_pemberian_obat dpo "
                            + "inner join databarang db on db.kode_brng=dpo.kode_brng left join aturan_pakai at on at.no_rawat=dpo.no_rawat and at.tgl_perawatan=dpo.tgl_perawatan and at.jam=dpo.jam where "
                            + "dpo.tgl_perawatan=? and dpo.jam=? and dpo.no_rawat=? and db.kode_brng IN ('B000001938','B000002727','B000002610','B000002706','B000002722') "
                            + "group by dpo.kode_brng, dpo.jml, db.kapasitas, dpo.tgl_perawatan, dpo.jam, dpo.no_rawat order by db.kode_brng");
                    try {
                        psracikan.setString(1, rs2.getString("tgl_perawatan"));
                        psracikan.setString(2, rs2.getString("jam"));
                        psracikan.setString(3, rs2.getString("no_rawat"));
                        rsracikan = psracikan.executeQuery();
                        while (rsracikan.next()) {
                            tabModeObatRacikan.addRow(new String[]{
                                rsracikan.getString("no_rawat"), rsracikan.getString("kode_brng"), rsracikan.getString("nama_brng"),
                                rsracikan.getString("jml"), rsracikan.getString("aturanPakai").replaceAll(", , , , ", "-"), rsracikan.getString("keterangan")
                            });
                        }

                        psobat = koneksi.prepareStatement("select dpo.no_rawat, m.kode_brng_apotek_bpjs, dpo.jml, db.nama_brng from detail_pemberian_obat dpo "
                                + "inner join databarang db on db.kode_brng=dpo.kode_brng inner join maping_obat_apotek_bpjs m on dpo.kode_brng=m.kode_brng where "
                                + "dpo.tgl_perawatan=? and dpo.jam=? and dpo.no_rawat=? and db.kode_brng not IN ('B000001938','B000002727','B000002610','B000002706','B000002722') "
                                + "order by db.kode_brng");
                        try {
                            psobat.setString(1, rs2.getString("tgl_perawatan"));
                            psobat.setString(2, rs2.getString("jam"));
                            psobat.setString(3, rs2.getString("no_rawat"));
                            rsobat = psobat.executeQuery();
                            while (rsobat.next()) {
                                String kodeobat = rsobat.getString("kode_brng_apotek_bpjs");
                                if (kodeobat != null && !kodeobat.isEmpty()) {
                                    hitungKode.put(kodeobat, hitungKode.getOrDefault(kodeobat, 0) + 1);
                                }

                                for (Map.Entry<String, Integer> entry : hitungKode.entrySet()) {
                                    kandungan = "" + entry.getValue();
                                }

                                if (rsobat.getString("jml").equals("15")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 1, 0.5, 30, kandungan});
                                } else if (rsobat.getString("jml").equals("45")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 1, 1.5, 30, kandungan});
                                } else if (rsobat.getString("jml").equals("60")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 2, 1, 30, kandungan});
                                } else if (rsobat.getString("jml").equals("90")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"),
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 3, 1, 30, kandungan});
                                } else if (rsobat.getString("jml").equals("120")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 4, 1, 30, kandungan});
                                } else if (rsobat.getString("jml").equals("2")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 1, 1, 2, kandungan});
                                } else if (rsobat.getString("jml").equals("2")) {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 1, 1, 2, kandungan});
                                } else {
                                    tabModeDetailObatRacikan.addRow(new Object[]{false, rs2.getString("no_resep"), rsobat.getString("jml"), 
                                        rsobat.getString("kode_brng_apotek_bpjs"), rsobat.getString("nama_brng"), 1, 1, 30, kandungan});
                                }                                
                            }
                        } catch (Exception e) {
                            System.out.println("Notifikasi Detail Racikan : " + e);
                        }
                    } catch (Exception e) {
                        System.out.println("Notif Racikan : " + e);
                    } finally {
                        if (rsracikan != null) {
                            rsracikan.close();
                        }
                        if (psracikan != null) {
                            psracikan.close();
                        }
                    }

                    for (i = 0; i < tbDetailObatRacikan.getRowCount(); i++) {
                        getDatadetailobatracikan(i);
                    }

                    if (tabModeObatRacikan.getRowCount() > 0) {
                        TabResep.setSelectedIndex(1);
                    } else {
                        TabResep.setSelectedIndex(0);
                        Valid.tabelKosong(tabModeDetailObatRacikan);
                    }
                }
            } catch (Exception e) {
                 System.out.println("Notifikasi racikan : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (rs2 != null) {
                    rs2.close();
                }
            }

        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void emptTeksobat() {
        LblNoRawat.setText("");
        TNoRM.setText("");
        TPasien.setText("");
        DTPTgl.setDate(new Date());
        Lahir.setText("");
        NoSEP.setText("");
        JnsObat.setSelectedIndex(1);
        KdDPJP.setText("");
        NmDPJP.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        TInfoPRB.setText("");
        TInfoIterasi.setText("");
        NoKartu.setText("");
        Iterasi.setSelectedIndex(0);
        TtglResep.setDate(new Date());
        Jam.setText(Sequel.cariIsi("select time(now())"));
    }

    private void getDataobat() {
        if(tbNonRacikan.getSelectedRow()!= -1){
            row=tbNonRacikan.getSelectedRow();
            if(!tbNonRacikan.getValueAt(row,1).toString().equals("")){
                if(Double.parseDouble(tbNonRacikan.getValueAt(row,1).toString())>0){
                    Double.parseDouble(tbNonRacikan.getValueAt(row,1).toString());
                } 
            }
        }            
    }
    
    private void getDataobat(int data) {        
        Double.parseDouble(tbNonRacikan.getValueAt(data, 1).toString());
    }

    public JTable getTable(){
        return tbNonRacikan;
    }
    
    public Button getButton(){
        return BtnSimpan;
    }
    
    public void setNoRm(String norwt, String norm, String nama, String tanggal, String jam, String Resep, String Nresep) {      
        aktifpcare = "no";
        TNoRw.setText(norwt);
        LblNoRawat.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(nama);
        Lahir.setText(Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=?", TNoRM.getText()));
        
        noresep = "";
        Valid.SetTgl(TtglResep, tanggal);
        Jam.setText(jam);
        KdPj.setText(Sequel.cariIsi("select kd_pj from reg_periksa where no_rawat=?", norwt));
        kenaikan = Sequel.cariIsiAngka("select (hargajual/100) from set_harga_obat_ralan where kd_pj=?", KdPj.getText());
        TResep.setText(Resep);
        
        try {
            ps2 = koneksi.prepareStatement("SELECT * from bridging_sep where no_rawat = ? and jnspelayanan='2'");
            try {
                ps2.setString(1, norwt);
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    NoSEP.setText(rs2.getString("no_sep"));
                    KdDPJP.setText(rs2.getString("dpjpLayan"));
                    NmDPJP.setText(rs2.getString("nmdpjpLayan"));
                    NoKartu.setText(rs2.getString("no_kartu"));
                    KdPoli.setText(rs2.getString("kdpolitujuan"));
                    NmPoli.setText(rs2.getString("nmpolitujuan"));
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }

        cekViaBPJSKartu.tampil(NoKartu.getText(), Sequel.cariIsi("select tglsep from bridging_sep where no_sep='" + NoSEP.getText() + "' and jnspelayanan='2'"));
        TInfoPRB.setText(cekViaBPJSKartu.informasiprolanisPRB);
        TInfoIterasi.setText(Sequel.cariIsi("select CASE WHEN kunjungan = 1 THEN '-' WHEN kunjungan = 2 THEN 'Iter 1 Kali' WHEN kunjungan = 3 THEN 'Iter 2 Kali' "
                + "ELSE CONCAT('Iter ', kunjungan - 1, ' Kali') END ket_kunjungan from iter_obat_bpjs where no_rawat='" + norwt + "'"));
        
        if (TInfoIterasi.getText().equals("-")) {
            Iterasi.setSelectedIndex(0);
        } else if (TInfoIterasi.getText().equals("Iter 1 Kali")) {
            Iterasi.setSelectedIndex(1);
        } else if (TInfoIterasi.getText().equals("Iter 2 Kali")) {
            Iterasi.setSelectedIndex(2);
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener() {
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;

            @Override
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                // Membuat Date
                //Date dt = new Date();
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if (ChkJln.isSelected() == true) {
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                } else if (ChkJln.isSelected() == false) {
                    nilai_jam = cmbJam.getSelectedIndex();
                    nilai_menit = cmbMnt.getSelectedIndex();
                    nilai_detik = cmbDtk.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                cmbJam.setSelectedItem(jam);
                cmbMnt.setSelectedItem(menit);
                cmbDtk.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }
    
    public void setDokter(String kodedokter,String namadokter){
        this.kodedokter=kodedokter;
        this.namadokter=namadokter;
    }
    
    private void getDatadetailobatracikan() {
        if (tbDetailObatRacikan.getSelectedRow() != -1) {
            row = tbDetailObatRacikan.getSelectedRow();
            try {
                if (Double.parseDouble(tbDetailObatRacikan.getValueAt(row, 1).toString()) > 0) {
                    Double.parseDouble(tbDetailObatRacikan.getValueAt(row, 1).toString());
                }
            } catch (Exception e) {
//                System.out.println("Notif Racikan : "+e);
            }
        }
    }
    
    private void getDatadetailobatracikan(int data) {       
       Double.parseDouble(tbDetailObatRacikan.getValueAt(data,1).toString());
    }
    
    private void KirimResepKosong() {
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No Rawat");
        } else if (NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoSEP, "Nomor Sep");
        } else if (KdDPJP.getText().trim().equals("")) {
            Valid.textKosong(KdDPJP, "Dokter");
        } else if (KdPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (TResep.getText().trim().equals("")) {
            Valid.textKosong(TResep, "Nomor Resep");
        } else if (Lahir.getText().trim().equals("")) {
            Valid.textKosong(Lahir, "Lahir");
        } else {
            int reply = JOptionPane.showConfirmDialog(rootPane, "Apakah yakin data akan disimpan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("x-timestamp", utc);
                    headers.add("x-signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
                    requestEntity = new HttpEntity(headers);

//                    System.out.println("Tanpa iterasi");
                    URL = link + "/sjpresep/v3/insert";
                    System.out.println(URL);
                    requestJson = "{"
                            + "\"TGLSJP\": \"" + Valid.SetTgl(DTPTgl.getSelectedItem() + "") + " " + cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem() + "\","
                            + "\"REFASALSJP\": \"" + NoSEP.getText() + "\","
                            + "\"POLIRSP\": \"" + KdPoli.getText() + "\","
                            + "\"KDJNSOBAT\": \"" + JnsObat.getSelectedItem().toString().substring(0, 1) + "\","
                            + "\"NORESEP\": \"" + TResep.getText() + "\", "
                            + "\"IDUSERSJP\": \"RS_" + akses.getkode() + "\","
                            + "\"TGLRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\", "
                            + "\"TGLPELRSP\": \"" + Valid.SetTgl(TtglResep.getSelectedItem() + "") + " 00:00:00\","
                            + "\"KdDokter\": \"" + KdDPJP.getText() + "\","
                            + "\"iterasi\":\"" + Iterasi.getSelectedItem().toString().substring(0, 1) + "\""
                            + "}  ";
                    System.out.println("Resep : " + requestJson);
                    requestEntity = new HttpEntity(requestJson, headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("data = " + nameNode);
                    System.out.println("error = " + nameNode.path("message").asText());
                    if (nameNode.path("code").asText().equals("200")) {
                        response = mapper.readTree(api.Decrypt(root.path("response").asText(), utc));
                        System.out.println("Response : " + response);
                        if (Sequel.menyimpantf2("bridging_apotek_bpjs", "?,?,?,?,?,?,?,?,?,?,?,?,?", "data", 13,
                                new String[]{
                                    response.path("noSep_Kunjungan").asText(),
                                    response.path("noApotik").asText(),
                                    TResep.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + "") + " " + Jam.getText(),
                                    Valid.SetTgl(TtglResep.getSelectedItem() + ""),
                                    JnsObat.getSelectedItem().toString().substring(0, 1),
                                    Iterasi.getSelectedItem().toString().substring(0, 1),
                                    KdPoli.getText(),
                                    NmPoli.getText(),
                                    KdDPJP.getText(),
                                    NmDPJP.getText(),
                                    akses.getkode(),
                                    "-"}) == true) {
                            System.out.println("Simpan No Resep Selesai");
                            JOptionPane.showMessageDialog(null, "Resep Apotek " + response.path("noApotik").asText() + " Berhasil disimpan ");
                            no_apotek = response.path("noApotik").asText();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, " ERROR : " + nameNode.path("message").asText());
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        }
    }
    
    private void InsertObatNonRacikan() {
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "No Rawat");
        } else if (NoSEP.getText().trim().equals("")) {
            Valid.textKosong(NoSEP, "Nomor Sep");
        } else if (KdDPJP.getText().trim().equals("")) {
            Valid.textKosong(KdDPJP, "Dokter");
        } else if (KdPoli.getText().trim().equals("")) {
            Valid.textKosong(KdPoli, "Poliklinik");
        } else if (TResep.getText().trim().equals("")) {
            Valid.textKosong(TResep, "Nomor Resep");
        } else if (Lahir.getText().trim().equals("")) {
            Valid.textKosong(Lahir, "Lahir");
        } else {
            int reply = JOptionPane.showConfirmDialog(rootPane, "Eeiiiiiits, udah bener belum data yang mau disimpan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
                    utc = String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("x-timestamp", utc);
                    headers.add("x-signature", api.getHmac(utc));
                    headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
                    requestEntity = new HttpEntity(headers);

                    //cek resep yang sdh ada
                    //sdh ada nomor apotek
                    if (!Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'").isEmpty() && Iterasi.getSelectedIndex() == 0) {
                        URL = link + "/obatnonracikan/v3/insert";
                        System.out.println(URL);
                        for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                            if (Valid.SetAngka(tbNonRacikan.getValueAt(i, 1).toString()) > 0) {
                                if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                    try {
                                        requestJson = "{"
                                                + "\"NOSJP\": \"" + Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'") + "\","
                                                + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                + "\"KDOBT\": \"" + Sequel.cariIsi("SELECT kode_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                + "\"SIGNA1OBT\": " + tbNonRacikan.getValueAt(i, 4).toString() + ","
                                                + "\"SIGNA2OBT\": " + tbNonRacikan.getValueAt(i, 5).toString() + ","
                                                + "\"JMLOBT\": " + tbNonRacikan.getValueAt(i, 1).toString() + ","
                                                + "\"JHO\": " + tbNonRacikan.getValueAt(i, 6).toString() + ","
                                                + "\"CatKhsObt\": \"non racikan\""
                                                + "}";
                                        System.out.println("Request JSON: " + requestJson);
                                        requestEntity = new HttpEntity(requestJson, headers);
                                        root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                        nameNode = root.path("metaData");
                                        System.out.println("data = " + nameNode);
                                        if (nameNode.path("code").asText().equals("200")) {
                                            if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS", 9, new String[]{
                                                //response.path("noSep_Kunjungan").asText(),
                                                NoSEP.getText(),
                                                TResep.getText(),
                                                tbNonRacikan.getValueAt(i, 2).toString(),
                                                tbNonRacikan.getValueAt(i, 3).toString(),
                                                tbNonRacikan.getValueAt(i, 1).toString(),
                                                tbNonRacikan.getValueAt(i, 4).toString(),
                                                tbNonRacikan.getValueAt(i, 5).toString(),
                                                "0",
                                                no_apotek = Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'")
                                            }) == true) {
                                                System.out.println("Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                                JOptionPane.showMessageDialog(null, "Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                            }
                                        } else {
                                            System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                            JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                        }

                                        System.out.println("non racikan = \n\n" + requestJson);
                                    } catch (Exception ex) {
                                        System.out.println("Notifikasi : " + ex);
                                        if (ex.toString().contains("UnknownHostException")) {
                                            JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                        }
                                    }
                                }
                            }
                        }

                    } else if (!Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'").isEmpty() && Iterasi.getSelectedIndex() == 1) {
                        URL = link + "/obatnonracikan/v3/insert";
                        System.out.println(URL);
                        for (i = 0; i < tbNonRacikan.getRowCount(); i++) {
                            if (Valid.SetAngka(tbNonRacikan.getValueAt(i, 1).toString()) > 0) {
                                if (tbNonRacikan.getValueAt(i, 0).toString().equals("true")) {
                                    try {
                                        requestJson = "{"
                                                + "\"NOSJP\": \"" + Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'") + "\","
                                                + "\"NORESEP\": \"" + TResep.getText() + "\","
                                                + "\"KDOBT\": \"" + Sequel.cariIsi("SELECT kode_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                + "\"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?", tbNonRacikan.getValueAt(i, 2).toString()) + "\","
                                                + "\"SIGNA1OBT\": " + tbNonRacikan.getValueAt(i, 4).toString() + ","
                                                + "\"SIGNA2OBT\": " + tbNonRacikan.getValueAt(i, 5).toString() + ","
                                                + "\"JMLOBT\": " + tbNonRacikan.getValueAt(i, 1).toString() + ","
                                                + "\"JHO\": " + tbNonRacikan.getValueAt(i, 6).toString() + ","
                                                + "\"CatKhsObt\": \"non racikan\""
                                                + "}";
                                        System.out.println("Request JSON: " + requestJson);
                                        requestEntity = new HttpEntity(requestJson, headers);
                                        root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                        nameNode = root.path("metaData");
                                        System.out.println("data = " + nameNode);
                                        if (nameNode.path("code").asText().equals("200")) {
                                            if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS", 9, new String[]{
                                                //response.path("noSep_Kunjungan").asText(),
                                                NoSEP.getText(),
                                                TResep.getText(),
                                                tbNonRacikan.getValueAt(i, 2).toString(),
                                                tbNonRacikan.getValueAt(i, 3).toString(),
                                                tbNonRacikan.getValueAt(i, 1).toString(),
                                                tbNonRacikan.getValueAt(i, 4).toString(),
                                                tbNonRacikan.getValueAt(i, 5).toString(),
                                                "0",
                                                no_apotek = Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'")
                                            }) == true) {
                                                System.out.println("Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                                JOptionPane.showMessageDialog(null, "Obat " + tbNonRacikan.getValueAt(i, 3).toString() + " Berhasil disimpan");
                                            }
                                        } else {
                                            System.out.println("Obat Gagal Simpan, " + nameNode.path("message").asText());
                                            JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, " + nameNode.path("message").asText());
                                        }

                                        System.out.println("non racikan = \n\n" + requestJson);
                                    } catch (Exception ex) {
                                        System.out.println("Notifikasi : " + ex);
                                        if (ex.toString().contains("UnknownHostException")) {
                                            JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    if (ex.toString().contains("UnknownHostException")) {
                        JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        }
    }
    
    private void InsertObatRacikan(){
    if(TNoRw.getText().trim().equals("")){
        Valid.textKosong(TNoRw,"No Rawat");
    }else if(NoSEP.getText().trim().equals("")){
        Valid.textKosong(NoSEP,"Nomor Sep");                                      
    }else if(KdDPJP.getText().trim().equals("")){
        Valid.textKosong(KdDPJP,"Dokter");                                      
    }else if(KdPoli.getText().trim().equals("")){
        Valid.textKosong(KdPoli,"Poliklinik");                                      
    }else if(TResep.getText().trim().equals("")){
        Valid.textKosong(TResep,"Nomor Resep");                                      
    }else if(Lahir.getText().trim().equals("")){
        Valid.textKosong(Lahir,"Lahir");                                      
    }else{
        int reply = JOptionPane.showConfirmDialog(rootPane,"Eeiiiiiits, udah bener belum data yang mau disimpan..?","Konfirmasi",JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try { 
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("x-cons-id", koneksiDB.CONSIDAPIAPOTEKBPJS());
                utc = String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("x-timestamp", utc);
                headers.add("x-signature", api.getHmac(utc));
                headers.add("user_key", koneksiDB.USERKEYAPIAPOTEKBPJS());
                requestEntity = new HttpEntity(headers);
                
//                cek resep yang sdh ada
                if (!Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'").isEmpty() && Iterasi.getSelectedIndex() == 0) {
                    URL = link + "/obatracikan/v3/insert";
                    System.out.println(URL);
                    for(i=0;i<tbDetailObatRacikan.getRowCount();i++){ 
                        if(Valid.SetAngka(tbDetailObatRacikan.getValueAt(i,1).toString())>0){
                            try {
                                requestJson = "{\n"
                                        + "            \"NOSJP\": \"" + Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='"+TResep.getText()+"'") + "\",\n"
                                        + "            \"NORESEP\": \"" + TResep.getText() + "\",\n"
                                        + "            \"JNSROBT\": \"R.0"+(tbDetailObatRacikan.getValueAt(i,0).toString())+"\",\n"
                                        + "            \"KDOBT\": \"" + Sequel.cariIsi("SELECT kode_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?",tbDetailObatRacikan.getValueAt(i,2).toString()) + "\",\n"
                                        + "            \"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?",tbDetailObatRacikan.getValueAt(i,2).toString()) + "\",\n"
                                        + "            \"SIGNA1OBT\": " + tbDetailObatRacikan.getValueAt(i,4).toString() + ",\n"
                                        + "            \"SIGNA2OBT\": " + tbDetailObatRacikan.getValueAt(i,5).toString() + ",\n"
                                        + "            \"PERMINTAAN\": " + tbDetailObatRacikan.getValueAt(i,7).toString() + ",\n"
                                        + "            \"JMLOBT\": " + tbDetailObatRacikan.getValueAt(i,1).toString() + ",\n"
                                        + "            \"JHO\": " + tbDetailObatRacikan.getValueAt(i,6).toString() + ",\n"
                                        + "            \"CatKhsObt\": \"RACIKAN "+(i+1)+"\"\n"
                                        + "        }     ";
                                requestEntity = new HttpEntity(requestJson, headers);
                                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                nameNode = root.path("metaData");
                                System.out.println("data = "+nameNode);
                                if (nameNode.path("code").asText().equals("200")) {
                                    if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS Racikan", 9, new String[]{
//                                        response.path("noSep_Kunjungan").asText(),
                                        NoSEP.getText(),
                                        TResep.getText(),
                                        tbDetailObatRacikan.getValueAt(i,2).toString(),
                                        tbDetailObatRacikan.getValueAt(i,3).toString(),
                                        tbDetailObatRacikan.getValueAt(i,1).toString(),
                                        tbDetailObatRacikan.getValueAt(i,4).toString(),
                                        tbDetailObatRacikan.getValueAt(i,5).toString(),
                                        "1",
                                        no_apotek = Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'")
                                    }) == true) {
                                        System.out.println("Obat "+tbDetailObatRacikan.getValueAt(i,3).toString()+" Berhasil disimpan");
                                        JOptionPane.showMessageDialog(null, "Obat racikan"+tbDetailObatRacikan.getValueAt(i,3).toString()+" Berhasil disimpan");
                                    }
                                } else {
                                    System.out.println("Obat Gagal Simpan, "+nameNode.path("message").asText());
                                    JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, "+nameNode.path("message").asText());
                                }

                                System.out.println("racikan = \n\n"+requestJson);
                            } catch (Exception ex) {
                                System.out.println("Notifikasi : " + ex);
                                if (ex.toString().contains("UnknownHostException")) {
                                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                }
                            }  
                        }
                    }
                }
                
                else if (!Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'").isEmpty() && Iterasi.getSelectedIndex() == 1) {
                    URL = link + "/obatracikan/v3/insert";
                    System.out.println(URL);
                    for(i=0;i<tbDetailObatRacikan.getRowCount();i++){ 
                        if(Valid.SetAngka(tbDetailObatRacikan.getValueAt(i,1).toString())>0){
                            try {
                                requestJson = "{\n"
                                        + "            \"NOSJP\": \"" + Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='"+TResep.getText()+"'") + "\",\n"
                                        + "            \"NORESEP\": \"" + TResep.getText() + "\",\n"
                                        + "            \"JNSROBT\": \"R.0"+(tbDetailObatRacikan.getValueAt(i,0).toString())+"\",\n"
                                        + "            \"KDOBT\": \"" + Sequel.cariIsi("SELECT kode_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?",tbDetailObatRacikan.getValueAt(i,2).toString()) + "\",\n"
                                        + "            \"NMOBAT\": \"" + Sequel.cariIsi("SELECT nama_brng_apotek_bpjs FROM maping_obat_apotek_bpjs WHERE kode_brng=?",tbDetailObatRacikan.getValueAt(i,2).toString()) + "\",\n"
                                        + "            \"SIGNA1OBT\": " + tbDetailObatRacikan.getValueAt(i,4).toString() + ",\n"
                                        + "            \"SIGNA2OBT\": " + tbDetailObatRacikan.getValueAt(i,5).toString() + ",\n"
                                        + "            \"PERMINTAAN\": " + tbDetailObatRacikan.getValueAt(i,7).toString() + ",\n"
                                        + "            \"JMLOBT\": " + tbDetailObatRacikan.getValueAt(i,1).toString() + ",\n"
                                        + "            \"JHO\": " + tbDetailObatRacikan.getValueAt(i,6).toString() + ",\n"
                                        + "            \"CatKhsObt\": \"RACIKAN "+(i+1)+"\"\n"
                                        + "        }     ";
                                requestEntity = new HttpEntity(requestJson, headers);
                                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                                nameNode = root.path("metaData");
                                System.out.println("data = "+nameNode);
                                if (nameNode.path("code").asText().equals("200")) {
                                    if (Sequel.menyimpantf("bridging_apotek_bpjs_obat", "?,?,?,?,?,?,?,?,?", "Simpan Obat Apotek BPJS Racikan", 9, new String[]{
//                                        response.path("noSep_Kunjungan").asText(),
                                        NoSEP.getText(),
                                        TResep.getText(),
                                        tbDetailObatRacikan.getValueAt(i,2).toString(),
                                        tbDetailObatRacikan.getValueAt(i,3).toString(),
                                        tbDetailObatRacikan.getValueAt(i,1).toString(),
                                        tbDetailObatRacikan.getValueAt(i,4).toString(),
                                        tbDetailObatRacikan.getValueAt(i,5).toString(),
                                        "1",
                                        no_apotek = Sequel.cariIsi("select no_apotek from bridging_apotek_bpjs where no_resep='" + TResep.getText() + "'")
                                    }) == true) {
                                        System.out.println("Obat "+tbDetailObatRacikan.getValueAt(i,3).toString()+" Berhasil disimpan");
                                        JOptionPane.showMessageDialog(null, "Obat racikan"+tbDetailObatRacikan.getValueAt(i,3).toString()+" Berhasil disimpan");
                                    }
                                } else {
                                    System.out.println("Obat Gagal Simpan, "+nameNode.path("message").asText());
                                    JOptionPane.showMessageDialog(null, "Obat Gagal Simpan, "+nameNode.path("message").asText());
                                }

                                System.out.println("racikan = \n\n"+requestJson);
                            } catch (Exception ex) {
                                System.out.println("Notifikasi : " + ex);
                                if (ex.toString().contains("UnknownHostException")) {
                                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                                }
                            }  
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);  
                if (ex.toString().contains("UnknownHostException")) {
                    JOptionPane.showMessageDialog(rootPane, "Koneksi ke server BPJS terputus...!");
                    }
                }
            }
        }
    }
    
     private void HitungKapasitas(int jmlh){        
  
          //  if(jmlh==1||jmlh==2||jmlh==3||jmlh==4||jmlh==5||jmlh==6||jmlh==7){
                try {
                    if(tbNonRacikan.getValueAt(jmlh,7).toString().equals("1")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("2")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("3")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("4")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("5")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("6")||tbNonRacikan.getValueAt(jmlh,7).toString().equals("7")){   
                        tbNonRacikan.setValueAt(Valid.SetAngka2(Double.parseDouble(tbNonRacikan.getValueAt(jmlh,1).toString())/Double.parseDouble(tbNonRacikan.getValueAt(jmlh,4).toString())), jmlh,6);     
                    } 
                } catch (Exception e) {
                 //   tbObat.setValueAt(0, row,1);   
                }
            }
                
     //   }
}