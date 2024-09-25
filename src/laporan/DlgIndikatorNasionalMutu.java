package laporan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import rekammedis.DlgMasterIndikatorMutu;

/**
 *
 * @author dosen
 */
public class DlgIndikatorNasionalMutu extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2, tabMode3;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3;
    private ResultSet rs, rs1, rs2, rs3;
    private int i = 0, x = 0, n = 0;
    private double hitungTot = 0;
    private String kdIndikator = "", kdNumdemon = "", gedungDIpilih = "", tglDipilih = "", angkaBulan = "", cekBulan = "", total = "",
            tgl1 = "", tgl2 = "", tgl3 = "", tgl4 = "", tgl5 = "", tgl6 = "", tgl7 = "", tgl8 = "", tgl9 = "", tgl10 = "",
            tgl11 = "", tgl12 = "", tgl13 = "", tgl14 = "", tgl15 = "", tgl16 = "", tgl17 = "", tgl18 = "", tgl19 = "", tgl20 = "",
            tgl21 = "", tgl22 = "", tgl23 = "", tgl24 = "", tgl25 = "", tgl26 = "", tgl27 = "", tgl28 = "", tgl29 = "", tgl30 = "", tgl31 = "";
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public DlgIndikatorNasionalMutu(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new String[]{
            "Ruangan", "No.", "Indikator", "Numerator Demonimator", "Tgl. 1", "Tgl. 2", "Tgl. 3", "Tgl. 4", "Tgl. 5", "Tgl. 6", "Tgl. 7", "Tgl. 8", "Tgl. 9", "Tgl. 10",
            "Tgl. 11", "Tgl. 12", "Tgl. 13", "Tgl. 14", "Tgl. 15", "Tgl. 16", "Tgl. 17", "Tgl. 18", "Tgl. 19", "Tgl. 20",
            "Tgl. 21", "Tgl. 22", "Tgl. 23", "Tgl. 24", "Tgl. 25", "Tgl. 26", "Tgl. 27", "Tgl. 28", "Tgl. 29", "Tgl. 30", "Tgl. 31", "Total",
            "kdindikator", "tglcatat", "kdnumdemon"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbIndikator.setModel(tabMode);
        tbIndikator.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbIndikator.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 39; i++) {
            TableColumn column = tbIndikator.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(130);
            } else if (i == 1) {
                column.setPreferredWidth(30);
            } else if (i == 2) {
                column.setPreferredWidth(400);
            } else if (i == 3) {
                column.setPreferredWidth(400);
            } else if (i == 4) {
                column.setPreferredWidth(48);
            } else if (i == 5) {
                column.setPreferredWidth(48);
            } else if (i == 6) {
                column.setPreferredWidth(48);
            } else if (i == 7) {
                column.setPreferredWidth(48);
            } else if (i == 8) {
                column.setPreferredWidth(48);
            } else if (i == 9) {
                column.setPreferredWidth(48);
            } else if (i == 10) {
                column.setPreferredWidth(48);
            } else if (i == 11) {
                column.setPreferredWidth(48);
            } else if (i == 12) {
                column.setPreferredWidth(48);
            } else if (i == 13) {
                column.setPreferredWidth(48);
            } else if (i == 14) {
                column.setPreferredWidth(48);
            } else if (i == 15) {
                column.setPreferredWidth(48);
            } else if (i == 16) {
                column.setPreferredWidth(48);
            } else if (i == 17) {
                column.setPreferredWidth(48);
            } else if (i == 18) {
                column.setPreferredWidth(48);
            } else if (i == 19) {
                column.setPreferredWidth(48);
            } else if (i == 20) {
                column.setPreferredWidth(48);
            } else if (i == 21) {
                column.setPreferredWidth(48);
            } else if (i == 22) {
                column.setPreferredWidth(48);
            } else if (i == 23) {
                column.setPreferredWidth(48);
            } else if (i == 24) {
                column.setPreferredWidth(48);
            } else if (i == 25) {
                column.setPreferredWidth(48);
            } else if (i == 26) {
                column.setPreferredWidth(48);
            } else if (i == 27) {
                column.setPreferredWidth(48);
            } else if (i == 28) {
                column.setPreferredWidth(48);
            } else if (i == 29) {
                column.setPreferredWidth(48);
            } else if (i == 30) {
                column.setPreferredWidth(48);
            } else if (i == 31) {
                column.setPreferredWidth(48);
            } else if (i == 32) {
                column.setPreferredWidth(48);
            } else if (i == 33) {
                column.setPreferredWidth(48);
            } else if (i == 34) {
                column.setPreferredWidth(48);
            } else if (i == 35) {
                column.setPreferredWidth(55);
            } else if (i == 36) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 37) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 38) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbIndikator.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1 = new DefaultTableModel(null, new Object[]{
            "Cek", "Tgl. Dilaporkan", "Jumlah Dilaporkan", "tgl_catat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                boolean a = false;
                if (colIndex == 0) {
                    a = true;
                }
                return a;
            }
            Class[] types = new Class[]{
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        tbHapus.setModel(tabMode1);
        tbHapus.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbHapus.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 4; i++) {
            TableColumn column = tbHapus.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(38);
            } else if (i == 1) {
                column.setPreferredWidth(100);
            } else if (i == 2) {
                column.setPreferredWidth(140);
            } else if (i == 3) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbHapus.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new String[]{
            "Tgl. Dilaporkan", "Jumlah Dilaporkan", "tgl_catat"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbGanti.setModel(tabMode2);
        tbGanti.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbGanti.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 3; i++) {
            TableColumn column = tbGanti.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(100);
            } else if (i == 1) {
                column.setPreferredWidth(140);
            } else if (i == 2) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbGanti.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode3 = new DefaultTableModel(null, new String[]{
            "kode", "Numerator Demonimator"
        }) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        tbNumdemon.setModel(tabMode3);
        tbNumdemon.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbNumdemon.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 2; i++) {
            TableColumn column = tbNumdemon.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 1) {
                column.setPreferredWidth(530);
            } 
        }
        tbNumdemon.setDefaultRenderer(Object.class, new WarnaTable());

        Ttahun.setDocument(new batasInput((byte) 4).getOnlyAngka(Ttahun));
        Tjumlah.setDocument(new batasInput((int) 5).getKata(Tjumlah));
        Tjumlah1.setDocument(new batasInput((int) 5).getKata(Tjumlah1));
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
        MnMasterIndikator = new javax.swing.JMenuItem();
        WindowHapus = new javax.swing.JDialog();
        internalFrame6 = new widget.InternalFrame();
        internalFrame7 = new widget.InternalFrame();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        Truangan = new widget.TextBox();
        TkdIndikator = new widget.TextBox();
        TnmIndikator = new widget.TextBox();
        jLabel38 = new widget.Label();
        TkdNumdemon = new widget.TextBox();
        TnmNumdemon = new widget.TextBox();
        Scroll3 = new widget.ScrollPane();
        tbHapus = new widget.Table();
        internalFrame15 = new widget.InternalFrame();
        BtnHapus1 = new widget.Button();
        BtnConteng = new widget.Button();
        BtnHapusINM = new widget.Button();
        BtnCloseIn4 = new widget.Button();
        WindowGanti = new javax.swing.JDialog();
        internalFrame8 = new widget.InternalFrame();
        internalFrame9 = new widget.InternalFrame();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        Truangan1 = new widget.TextBox();
        TkdIndikator1 = new widget.TextBox();
        TnmIndikator1 = new widget.TextBox();
        jLabel36 = new widget.Label();
        TtglCatat1 = new widget.Tanggal();
        jLabel10 = new widget.Label();
        Tjumlah1 = new widget.TextBox();
        jLabel37 = new widget.Label();
        TkdNumdemon1 = new widget.TextBox();
        TnmNumdemon1 = new widget.TextBox();
        Scroll4 = new widget.ScrollPane();
        tbGanti = new widget.Table();
        internalFrame16 = new widget.InternalFrame();
        BtnGantiINM = new widget.Button();
        BtnCloseIn5 = new widget.Button();
        internalFrame1 = new widget.InternalFrame();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnGanti = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel29 = new widget.Label();
        cmbBulan = new widget.ComboBox();
        jLabel35 = new widget.Label();
        Ttahun = new widget.TextBox();
        jLabel6 = new widget.Label();
        cmbGedung1 = new widget.ComboBox();
        BtnCari = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        jLabel4 = new widget.Label();
        cmbGedung = new widget.ComboBox();
        jLabel5 = new widget.Label();
        cmbIndikator = new widget.ComboBox();
        jLabel8 = new widget.Label();
        TtglCatat = new widget.Tanggal();
        jLabel9 = new widget.Label();
        Tjumlah = new widget.TextBox();
        Scroll1 = new widget.ScrollPane();
        tbNumdemon = new widget.Table();
        BtnNumdemon = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbIndikator = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnMasterIndikator.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnMasterIndikator.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnMasterIndikator.setText("Master Indikator NM");
        MnMasterIndikator.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnMasterIndikator.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnMasterIndikator.setIconTextGap(5);
        MnMasterIndikator.setName("MnMasterIndikator"); // NOI18N
        MnMasterIndikator.setPreferredSize(new java.awt.Dimension(150, 26));
        MnMasterIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnMasterIndikatorActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnMasterIndikator);

        WindowHapus.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowHapus.setName("WindowHapus"); // NOI18N
        WindowHapus.setUndecorated(true);
        WindowHapus.setResizable(false);

        internalFrame6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Hapus Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame6.setLayout(new java.awt.BorderLayout());

        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setPreferredSize(new java.awt.Dimension(0, 106));
        internalFrame7.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame7.setLayout(null);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setText("Ruangan : ");
        jLabel31.setName("jLabel31"); // NOI18N
        internalFrame7.add(jLabel31);
        jLabel31.setBounds(0, 10, 150, 23);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Jenis Indikator : ");
        jLabel32.setName("jLabel32"); // NOI18N
        internalFrame7.add(jLabel32);
        jLabel32.setBounds(0, 38, 150, 23);

        Truangan.setEditable(false);
        Truangan.setForeground(new java.awt.Color(0, 0, 0));
        Truangan.setName("Truangan"); // NOI18N
        internalFrame7.add(Truangan);
        Truangan.setBounds(151, 10, 180, 23);

        TkdIndikator.setEditable(false);
        TkdIndikator.setForeground(new java.awt.Color(0, 0, 0));
        TkdIndikator.setName("TkdIndikator"); // NOI18N
        internalFrame7.add(TkdIndikator);
        TkdIndikator.setBounds(151, 38, 70, 23);

        TnmIndikator.setEditable(false);
        TnmIndikator.setForeground(new java.awt.Color(0, 0, 0));
        TnmIndikator.setName("TnmIndikator"); // NOI18N
        internalFrame7.add(TnmIndikator);
        TnmIndikator.setBounds(224, 38, 490, 23);

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Numerator Demonimator : ");
        jLabel38.setName("jLabel38"); // NOI18N
        internalFrame7.add(jLabel38);
        jLabel38.setBounds(0, 66, 150, 23);

        TkdNumdemon.setEditable(false);
        TkdNumdemon.setForeground(new java.awt.Color(0, 0, 0));
        TkdNumdemon.setName("TkdNumdemon"); // NOI18N
        internalFrame7.add(TkdNumdemon);
        TkdNumdemon.setBounds(151, 66, 70, 23);

        TnmNumdemon.setEditable(false);
        TnmNumdemon.setForeground(new java.awt.Color(0, 0, 0));
        TnmNumdemon.setName("TnmNumdemon"); // NOI18N
        internalFrame7.add(TnmNumdemon);
        TnmNumdemon.setBounds(224, 66, 530, 23);

        internalFrame6.add(internalFrame7, java.awt.BorderLayout.PAGE_START);

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);
        Scroll3.setPreferredSize(new java.awt.Dimension(487, 140));

        tbHapus.setToolTipText("Silahkan klik/pilih salah satu data pada tabel utk. dihapus");
        tbHapus.setName("tbHapus"); // NOI18N
        Scroll3.setViewportView(tbHapus);

        internalFrame6.add(Scroll3, java.awt.BorderLayout.CENTER);

        internalFrame15.setBorder(null);
        internalFrame15.setName("internalFrame15"); // NOI18N
        internalFrame15.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame15.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 9));

        BtnHapus1.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnHapus1.setMnemonic('M');
        BtnHapus1.setText("Hapus Conteng");
        BtnHapus1.setToolTipText("Alt+M");
        BtnHapus1.setName("BtnHapus1"); // NOI18N
        BtnHapus1.setPreferredSize(new java.awt.Dimension(140, 26));
        BtnHapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapus1ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnHapus1);

        BtnConteng.setForeground(new java.awt.Color(0, 0, 0));
        BtnConteng.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnConteng.setMnemonic('G');
        BtnConteng.setText("Conteng Semua");
        BtnConteng.setToolTipText("Alt+G");
        BtnConteng.setName("BtnConteng"); // NOI18N
        BtnConteng.setPreferredSize(new java.awt.Dimension(150, 26));
        BtnConteng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnContengActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnConteng);

        BtnHapusINM.setForeground(new java.awt.Color(0, 0, 0));
        BtnHapusINM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnHapusINM.setMnemonic('H');
        BtnHapusINM.setText("Hapus");
        BtnHapusINM.setToolTipText("Alt+H");
        BtnHapusINM.setName("BtnHapusINM"); // NOI18N
        BtnHapusINM.setPreferredSize(new java.awt.Dimension(95, 26));
        BtnHapusINM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusINMActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnHapusINM);

        BtnCloseIn4.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn4.setMnemonic('U');
        BtnCloseIn4.setText("Tutup");
        BtnCloseIn4.setToolTipText("Alt+U");
        BtnCloseIn4.setName("BtnCloseIn4"); // NOI18N
        BtnCloseIn4.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn4ActionPerformed(evt);
            }
        });
        internalFrame15.add(BtnCloseIn4);

        internalFrame6.add(internalFrame15, java.awt.BorderLayout.PAGE_END);

        WindowHapus.getContentPane().add(internalFrame6, java.awt.BorderLayout.CENTER);

        WindowGanti.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowGanti.setName("WindowGanti"); // NOI18N
        WindowGanti.setUndecorated(true);
        WindowGanti.setResizable(false);

        internalFrame8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Ganti Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame8.setLayout(new java.awt.BorderLayout());

        internalFrame9.setName("internalFrame9"); // NOI18N
        internalFrame9.setPreferredSize(new java.awt.Dimension(0, 128));
        internalFrame9.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame9.setLayout(null);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Ruangan : ");
        jLabel33.setName("jLabel33"); // NOI18N
        internalFrame9.add(jLabel33);
        jLabel33.setBounds(0, 10, 150, 23);

        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setText("Jenis Indikator : ");
        jLabel34.setName("jLabel34"); // NOI18N
        internalFrame9.add(jLabel34);
        jLabel34.setBounds(0, 38, 150, 23);

        Truangan1.setEditable(false);
        Truangan1.setForeground(new java.awt.Color(0, 0, 0));
        Truangan1.setName("Truangan1"); // NOI18N
        internalFrame9.add(Truangan1);
        Truangan1.setBounds(151, 10, 180, 23);

        TkdIndikator1.setEditable(false);
        TkdIndikator1.setForeground(new java.awt.Color(0, 0, 0));
        TkdIndikator1.setName("TkdIndikator1"); // NOI18N
        internalFrame9.add(TkdIndikator1);
        TkdIndikator1.setBounds(151, 38, 70, 23);

        TnmIndikator1.setEditable(false);
        TnmIndikator1.setForeground(new java.awt.Color(0, 0, 0));
        TnmIndikator1.setName("TnmIndikator1"); // NOI18N
        internalFrame9.add(TnmIndikator1);
        TnmIndikator1.setBounds(225, 38, 490, 23);

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Tgl. Dilaporkan : ");
        jLabel36.setName("jLabel36"); // NOI18N
        internalFrame9.add(jLabel36);
        jLabel36.setBounds(0, 94, 150, 23);

        TtglCatat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
        TtglCatat1.setDisplayFormat("dd-MM-yyyy");
        TtglCatat1.setName("TtglCatat1"); // NOI18N
        TtglCatat1.setOpaque(false);
        TtglCatat1.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame9.add(TtglCatat1);
        TtglCatat1.setBounds(151, 94, 90, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Jumlah Dilaporkan :");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame9.add(jLabel10);
        jLabel10.setBounds(240, 94, 110, 23);

        Tjumlah1.setForeground(new java.awt.Color(0, 0, 0));
        Tjumlah1.setName("Tjumlah1"); // NOI18N
        Tjumlah1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tjumlah1KeyPressed(evt);
            }
        });
        internalFrame9.add(Tjumlah1);
        Tjumlah1.setBounds(356, 94, 60, 23);

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Numerator Demonimator : ");
        jLabel37.setName("jLabel37"); // NOI18N
        internalFrame9.add(jLabel37);
        jLabel37.setBounds(0, 66, 150, 23);

        TkdNumdemon1.setEditable(false);
        TkdNumdemon1.setForeground(new java.awt.Color(0, 0, 0));
        TkdNumdemon1.setName("TkdNumdemon1"); // NOI18N
        internalFrame9.add(TkdNumdemon1);
        TkdNumdemon1.setBounds(151, 66, 70, 23);

        TnmNumdemon1.setEditable(false);
        TnmNumdemon1.setForeground(new java.awt.Color(0, 0, 0));
        TnmNumdemon1.setName("TnmNumdemon1"); // NOI18N
        internalFrame9.add(TnmNumdemon1);
        TnmNumdemon1.setBounds(225, 66, 530, 23);

        internalFrame8.add(internalFrame9, java.awt.BorderLayout.PAGE_START);

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);
        Scroll4.setPreferredSize(new java.awt.Dimension(487, 140));

        tbGanti.setToolTipText("Silahkan klik/pilih salah satu data pada tabel utk. diganti");
        tbGanti.setName("tbGanti"); // NOI18N
        tbGanti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGantiMouseClicked(evt);
            }
        });
        tbGanti.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbGantiKeyPressed(evt);
            }
        });
        Scroll4.setViewportView(tbGanti);

        internalFrame8.add(Scroll4, java.awt.BorderLayout.CENTER);

        internalFrame16.setBorder(null);
        internalFrame16.setName("internalFrame16"); // NOI18N
        internalFrame16.setPreferredSize(new java.awt.Dimension(0, 44));
        internalFrame16.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame16.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 9));

        BtnGantiINM.setForeground(new java.awt.Color(0, 0, 0));
        BtnGantiINM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnGantiINM.setMnemonic('G');
        BtnGantiINM.setText("Ganti");
        BtnGantiINM.setToolTipText("Alt+G");
        BtnGantiINM.setName("BtnGantiINM"); // NOI18N
        BtnGantiINM.setPreferredSize(new java.awt.Dimension(95, 26));
        BtnGantiINM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGantiINMActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnGantiINM);

        BtnCloseIn5.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn5.setMnemonic('U');
        BtnCloseIn5.setText("Tutup");
        BtnCloseIn5.setToolTipText("Alt+U");
        BtnCloseIn5.setName("BtnCloseIn5"); // NOI18N
        BtnCloseIn5.setPreferredSize(new java.awt.Dimension(90, 26));
        BtnCloseIn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn5ActionPerformed(evt);
            }
        });
        internalFrame16.add(BtnCloseIn5);

        internalFrame8.add(internalFrame16, java.awt.BorderLayout.PAGE_END);

        WindowGanti.getContentPane().add(internalFrame8, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Indikator Nasional Mutu ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

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
        panelGlass8.add(BtnHapus);

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

        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Bulan :");
        jLabel29.setName("jLabel29"); // NOI18N
        jLabel29.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(jLabel29);

        cmbBulan.setForeground(new java.awt.Color(0, 0, 0));
        cmbBulan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "Nopember", "Desember" }));
        cmbBulan.setName("cmbBulan"); // NOI18N
        cmbBulan.setPreferredSize(new java.awt.Dimension(85, 23));
        cmbBulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBulanActionPerformed(evt);
            }
        });
        panelGlass10.add(cmbBulan);

        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Tahun :");
        jLabel35.setName("jLabel35"); // NOI18N
        jLabel35.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(jLabel35);

        Ttahun.setForeground(new java.awt.Color(0, 0, 0));
        Ttahun.setName("Ttahun"); // NOI18N
        Ttahun.setPreferredSize(new java.awt.Dimension(60, 23));
        Ttahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TtahunKeyPressed(evt);
            }
        });
        panelGlass10.add(Ttahun);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Ruang Perawatan :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(110, 23));
        panelGlass10.add(jLabel6);

        cmbGedung1.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "VK BERSALIN", "RAWAT JALAN", "IBS", "AR RAUDAH" }));
        cmbGedung1.setName("cmbGedung1"); // NOI18N
        cmbGedung1.setPreferredSize(new java.awt.Dimension(190, 23));
        cmbGedung1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbGedung1MouseReleased(evt);
            }
        });
        panelGlass10.add(cmbGedung1);

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

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 200));
        PanelInput.setLayout(null);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Ruang Perawatan :");
        jLabel4.setName("jLabel4"); // NOI18N
        PanelInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 120, 23);

        cmbGedung.setForeground(new java.awt.Color(0, 0, 0));
        cmbGedung.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "VK BERSALIN", "RAWAT JALAN", "IBS", "AR RAUDAH" }));
        cmbGedung.setName("cmbGedung"); // NOI18N
        cmbGedung.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbGedung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbGedungMouseReleased(evt);
            }
        });
        cmbGedung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGedungActionPerformed(evt);
            }
        });
        PanelInput.add(cmbGedung);
        cmbGedung.setBounds(125, 10, 190, 23);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Jenis Indikator :");
        jLabel5.setName("jLabel5"); // NOI18N
        PanelInput.add(jLabel5);
        jLabel5.setBounds(0, 38, 120, 23);

        cmbIndikator.setForeground(new java.awt.Color(0, 0, 0));
        cmbIndikator.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        cmbIndikator.setName("cmbIndikator"); // NOI18N
        cmbIndikator.setPreferredSize(new java.awt.Dimension(55, 28));
        cmbIndikator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbIndikatorActionPerformed(evt);
            }
        });
        PanelInput.add(cmbIndikator);
        cmbIndikator.setBounds(125, 38, 490, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Dilaporkan :");
        jLabel8.setName("jLabel8"); // NOI18N
        PanelInput.add(jLabel8);
        jLabel8.setBounds(0, 164, 120, 23);

        TtglCatat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "25-09-2024" }));
        TtglCatat.setDisplayFormat("dd-MM-yyyy");
        TtglCatat.setName("TtglCatat"); // NOI18N
        TtglCatat.setOpaque(false);
        TtglCatat.setPreferredSize(new java.awt.Dimension(90, 23));
        PanelInput.add(TtglCatat);
        TtglCatat.setBounds(125, 164, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jumlah Dilaporkan :");
        jLabel9.setName("jLabel9"); // NOI18N
        PanelInput.add(jLabel9);
        jLabel9.setBounds(220, 164, 110, 23);

        Tjumlah.setForeground(new java.awt.Color(0, 0, 0));
        Tjumlah.setName("Tjumlah"); // NOI18N
        Tjumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TjumlahKeyPressed(evt);
            }
        });
        PanelInput.add(Tjumlah);
        Tjumlah.setBounds(335, 164, 60, 23);

        Scroll1.setComponentPopupMenu(jPopupMenu1);
        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        tbNumdemon.setToolTipText("Silahkan klik untuk memilih data");
        tbNumdemon.setComponentPopupMenu(jPopupMenu1);
        tbNumdemon.setName("tbNumdemon"); // NOI18N
        Scroll1.setViewportView(tbNumdemon);

        PanelInput.add(Scroll1);
        Scroll1.setBounds(125, 67, 570, 90);

        BtnNumdemon.setForeground(new java.awt.Color(0, 0, 0));
        BtnNumdemon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnNumdemon.setMnemonic('2');
        BtnNumdemon.setText("Numerator Demonimator");
        BtnNumdemon.setName("BtnNumdemon"); // NOI18N
        BtnNumdemon.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnNumdemon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNumdemonActionPerformed(evt);
            }
        });
        PanelInput.add(BtnNumdemon);
        BtnNumdemon.setBounds(410, 164, 210, 23);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Judul", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13))); // NOI18N
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbIndikator.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbIndikator.setComponentPopupMenu(jPopupMenu1);
        tbIndikator.setName("tbIndikator"); // NOI18N
        tbIndikator.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbIndikatorMouseClicked(evt);
            }
        });
        tbIndikator.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbIndikatorKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbIndikator);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (cmbGedung.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbGedung, "ruang perawatan");
            cmbGedung.requestFocus();
        } else if (cmbIndikator.getSelectedItem().toString().equals("-")) {
            Valid.textKosong(cmbIndikator, "jenis indikator");
            cmbIndikator.requestFocus();
        } else if (tbNumdemon.getSelectedRow() < -1) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu data Numerator Demonimatornya..!!");
            tbNumdemon.requestFocus();
        } else if (Tjumlah.getText().equals("")) {
            Valid.textKosong(Tjumlah, "jumlah dilaporkan");
            Tjumlah.requestFocus();
        } else {
            kdIndikator = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu "
                + "where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");
            
            kdNumdemon = Sequel.cariIsi("SELECT m1.kd_numdemon FROM master_numdemon_indikator_nasional_mutu m1 "
                    + "inner join master_indikator_nasional_mutu m2 on m1.kd_indikator=m2.kd_indikator WHERE "
                    + "m2.gedung='" + cmbGedung.getSelectedItem().toString() + "' and m2.kd_indikator='" + kdIndikator + "' and "
                    + "m1.nm_numdemon='" + tbNumdemon.getValueAt(tbNumdemon.getSelectedRow(), 1).toString() + "' and m1.status_data='aktif'");
            
            Sequel.menyimpan("indikator_nasional_mutu", "'" + kdIndikator + "','" + cmbGedung.getSelectedItem().toString() + "',"
                    + "'" + Valid.SetTgl(TtglCatat.getSelectedItem() + "") + "',"
                    + "'" + Tjumlah.getText().trim().replaceAll(",", ".") + "','" + kdNumdemon + "'", "Indikator & Tgl. Dilaporkan");

            cekBulanTahun(Valid.SetTgl(TtglCatat.getSelectedItem() + ""));
            BtnCariActionPerformed(null);
            emptTeks();
            tampilNumdemon();
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
        tampilNumdemon();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (tbIndikator.getSelectedRow() > -1) {
            WindowGanti.setSize(795, internalFrame1.getHeight() - 40);
            WindowGanti.setLocationRelativeTo(internalFrame1);
            WindowGanti.setAlwaysOnTop(false);
            WindowGanti.setVisible(true);
            
            TtglCatat1.setDate(new Date());
            Tjumlah1.setText("0");
            cekBulanTahun(tglDipilih);
            tampilGanti();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbIndikator.requestFocus();
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
        dispose();
        WindowHapus.dispose();
        WindowGanti.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        BtnCariActionPerformed(null);
        emptTeks();        
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCariActionPerformed(null);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbIndikatorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbIndikatorMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbIndikatorMouseClicked

    private void tbIndikatorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbIndikatorKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbIndikatorKeyPressed

    private void TjumlahKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TjumlahKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tjumlah.getText().contains(",") == true) {
                Tjumlah.setText(Tjumlah.getText().trim().replaceAll(",", "."));
            }
            BtnSimpan.requestFocus();
        }
    }//GEN-LAST:event_TjumlahKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung);
        Sequel.cariIsiComboDB("SELECT nm_gedung FROM bangsal WHERE nm_gedung<>'-' and nm_gedung not like '%ar-rau%' and status='1' GROUP BY nm_gedung ORDER BY nm_gedung", cmbGedung1);
        
        cmbBulan.setSelectedItem(Sequel.bulanINDONESIA("select month(now())"));
        angkaBulan = Sequel.cariIsi("select month(now())");
        Ttahun.setText(Sequel.cariIsi("select year(now())"));
        tampil();
        MnMasterIndikator.setEnabled(akses.getadmin());
    }//GEN-LAST:event_formWindowOpened

    private void cmbGedungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGedungActionPerformed
        cmbIndikator.removeAllItems();
        Sequel.cariIsiComboDB("SELECT nm_indikator from master_indikator_nasional_mutu "
                + "WHERE gedung='" + cmbGedung.getSelectedItem().toString() + "' and status_data='aktif' order by no_urut", cmbIndikator);
        
        cmbGedung1.setSelectedItem(cmbGedung.getSelectedItem().toString());
    }//GEN-LAST:event_cmbGedungActionPerformed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbIndikator.getSelectedRow() > -1) {
            WindowHapus.setSize(793, internalFrame1.getHeight() - 40);
            WindowHapus.setLocationRelativeTo(internalFrame1);
            WindowHapus.setAlwaysOnTop(false);
            WindowHapus.setVisible(true);
            cekBulanTahun(tglDipilih);
            tampilHapus();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih dulu salah satu datanya pada tabel..!!");
            tbIndikator.requestFocus();
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnHapusActionPerformed(null);
        }
    }//GEN-LAST:event_BtnHapusKeyPressed

    private void cmbBulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBulanActionPerformed
        angkaBulan = "";
        if (cmbBulan.getSelectedIndex() == 0) {
            angkaBulan = "1";
        } else if (cmbBulan.getSelectedIndex() == 1) {
            angkaBulan = "2";
        } else if (cmbBulan.getSelectedIndex() == 2) {
            angkaBulan = "3";
        } else if (cmbBulan.getSelectedIndex() == 3) {
            angkaBulan = "4";
        } else if (cmbBulan.getSelectedIndex() == 4) {
            angkaBulan = "5";
        } else if (cmbBulan.getSelectedIndex() == 5) {
            angkaBulan = "6";
        } else if (cmbBulan.getSelectedIndex() == 6) {
            angkaBulan = "7";
        } else if (cmbBulan.getSelectedIndex() == 7) {
            angkaBulan = "8";
        } else if (cmbBulan.getSelectedIndex() == 8) {
            angkaBulan = "9";
        } else if (cmbBulan.getSelectedIndex() == 9) {
            angkaBulan = "10";
        } else if (cmbBulan.getSelectedIndex() == 10) {
            angkaBulan = "11";
        } else if (cmbBulan.getSelectedIndex() == 11) {
            angkaBulan = "12";
        }
        BtnCariActionPerformed(null);
    }//GEN-LAST:event_cmbBulanActionPerformed

    private void TtahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TtahunKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCariActionPerformed(null);
        }
    }//GEN-LAST:event_TtahunKeyPressed

    private void cmbGedungMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbGedungMouseReleased
        AutoCompleteDecorator.decorate(cmbGedung);
    }//GEN-LAST:event_cmbGedungMouseReleased

    private void cmbGedung1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbGedung1MouseReleased
        AutoCompleteDecorator.decorate(cmbGedung1);
    }//GEN-LAST:event_cmbGedung1MouseReleased

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tabMode.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator nasional mutu belum ditampilkan pada tabel..!!");
        } else if (Ttahun.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "Tahun harus diisi dulu dengan benar..!!");
            Ttahun.requestFocus();
        } else if (cmbGedung1.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu ruang perawatannya dulu..!!");
            cmbGedung1.requestFocus();
        } else {
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("alamatrs", akses.getalamatrs());
            param.put("kotars", akses.getkabupatenrs());
            param.put("propinsirs", akses.getpropinsirs());
            param.put("kontakrs", akses.getkontakrs());
            param.put("emailrs", akses.getemailrs());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("judul", "RUANG " + cmbGedung1.getSelectedItem().toString().toUpperCase() + " BULAN " + cmbBulan.getSelectedItem().toString().toUpperCase() + " TAHUN " + Ttahun.getText());

            Sequel.AutoComitFalse();
            Sequel.queryu("delete from temporary");
            int row = tabMode.getRowCount();
            for (int r = 0; r < row; r++) {
                Sequel.menyimpan("temporary", "'0','"
                        + tabMode.getValueAt(r, 1).toString() + "','"
                        + tabMode.getValueAt(r, 2).toString() + "','"
                        + tabMode.getValueAt(r, 3).toString() + "','"
                        + tabMode.getValueAt(r, 4).toString() + "','"
                        + tabMode.getValueAt(r, 5).toString() + "','"
                        + tabMode.getValueAt(r, 6).toString() + "','"
                        + tabMode.getValueAt(r, 7).toString() + "','"
                        + tabMode.getValueAt(r, 8).toString() + "','"
                        + tabMode.getValueAt(r, 9).toString() + "','"
                        + tabMode.getValueAt(r, 10).toString() + "','"
                        + tabMode.getValueAt(r, 11).toString() + "','"
                        + tabMode.getValueAt(r, 12).toString() + "','"
                        + tabMode.getValueAt(r, 13).toString() + "','"
                        + tabMode.getValueAt(r, 14).toString() + "','"
                        + tabMode.getValueAt(r, 15).toString() + "','"
                        + tabMode.getValueAt(r, 16).toString() + "','"
                        + tabMode.getValueAt(r, 17).toString() + "','"
                        + tabMode.getValueAt(r, 18).toString() + "','"
                        + tabMode.getValueAt(r, 19).toString() + "','"
                        + tabMode.getValueAt(r, 20).toString() + "','"
                        + tabMode.getValueAt(r, 21).toString() + "','"
                        + tabMode.getValueAt(r, 22).toString() + "','"
                        + tabMode.getValueAt(r, 23).toString() + "','"
                        + tabMode.getValueAt(r, 24).toString() + "','"
                        + tabMode.getValueAt(r, 25).toString() + "','"
                        + tabMode.getValueAt(r, 26).toString() + "','"
                        + tabMode.getValueAt(r, 27).toString() + "','"
                        + tabMode.getValueAt(r, 28).toString() + "','"
                        + tabMode.getValueAt(r, 29).toString() + "','"
                        + tabMode.getValueAt(r, 30).toString() + "','"
                        + tabMode.getValueAt(r, 31).toString() + "','"
                        + tabMode.getValueAt(r, 32).toString() + "','"
                        + tabMode.getValueAt(r, 33).toString() + "','"
                        + tabMode.getValueAt(r, 34).toString() + "','"
                        + tabMode.getValueAt(r, 35).toString() + "','',''", "Indikator Nasional Mutu (INM)");
            }
            Sequel.AutoComitTrue();
            Valid.MyReport("rptIndikatorNasionalMutu.jasper", "report", "::[ Indikator Nasional Mutu (INM) ]::",
                    "select * from temporary", param);
            this.setCursor(Cursor.getDefaultCursor());

            emptTeks();
            tampil();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void MnMasterIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnMasterIndikatorActionPerformed
        akses.setform("DlgIndikatorNasionalMutu");
        DlgMasterIndikatorMutu mutu = new DlgMasterIndikatorMutu(null, false);
        mutu.isCek();
        mutu.emptTeks();
        mutu.setSize(internalFrame1.getWidth() - 40, internalFrame1.getHeight() - 40);
        mutu.setLocationRelativeTo(internalFrame1);
        mutu.setVisible(true);
    }//GEN-LAST:event_MnMasterIndikatorActionPerformed

    private void BtnHapusINMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusINMActionPerformed
        if (tbHapus.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Data indikator nasional mutu yang diinput belum ada..!!");
            BtnCloseIn4.requestFocus();
        } else {
            //cek conteng
            x = 0;
            for (i = 0; i < tbHapus.getRowCount(); i++) {
                if (tbHapus.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }

            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu utk. memilih data yang akan dihapus..!!!!");
                tbHapus.requestFocus();
            } else {
                n = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih akan dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    try {
                        for (i = 0; i < tbHapus.getRowCount(); i++) {
                            if (tbHapus.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.queryu("delete from indikator_nasional_mutu where kd_indikator='" + TkdIndikator.getText() + "' "
                                        + "and gedung='" + Truangan.getText() + "' and tgl_catat='" + tbHapus.getValueAt(i, 3).toString() + "' "
                                        + "and kd_numdemon='" + TkdNumdemon.getText() + "'");
                            }
                        }
                        tampilHapus();
                        tampil();
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                    }
                } else {
                    tampilHapus();
                    tampil();
                }
            }
        }
    }//GEN-LAST:event_BtnHapusINMActionPerformed

    private void BtnCloseIn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn4ActionPerformed
        WindowHapus.dispose();
        Truangan.setText("");
        TkdIndikator.setText("");
        TnmIndikator.setText("");
        TkdNumdemon.setText("");
        TnmNumdemon.setText("");
    }//GEN-LAST:event_BtnCloseIn4ActionPerformed

    private void BtnContengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnContengActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data belum ada...!!!!");
            tbHapus.requestFocus();
        } else {
            tampilHapus();
            for (i = 0; i < tbHapus.getRowCount(); i++) {
                tbHapus.setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }//GEN-LAST:event_BtnContengActionPerformed

    private void BtnHapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapus1ActionPerformed
        if (tabMode1.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Data belum ada...!!!!");
            tbHapus.requestFocus();
        } else {
            tampilHapus();
            for (i = 0; i < tbHapus.getRowCount(); i++) {
                tbHapus.setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }//GEN-LAST:event_BtnHapus1ActionPerformed

    private void BtnGantiINMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiINMActionPerformed
        if (tbGanti.getSelectedRow() > -1) {
            if (Tjumlah1.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(rootPane, "Angka jumlah dilaporkan harus diisi dengan benar..!!");
                Tjumlah1.requestFocus();
            } else {                
                Sequel.mengedit("indikator_nasional_mutu", "kd_indikator='" + TkdIndikator1.getText() + "' and "
                        + "gedung='" + Truangan1.getText() + "' and tgl_catat='" + tbGanti.getValueAt(tbGanti.getSelectedRow(), 2).toString() + "' and kd_numdemon='" + TkdNumdemon1.getText() + "'",
                        "tgl_catat='" + Valid.SetTgl(TtglCatat1.getSelectedItem() + "") + "', jumlah_pertanggal='" + Tjumlah1.getText().trim().replaceAll(",", ".") + "'");
                tampilGanti();
                tampil();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan klik/pilih dulu salah satu datanya pada tabel..!!");
            tbGanti.requestFocus();
        }
    }//GEN-LAST:event_BtnGantiINMActionPerformed

    private void BtnCloseIn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn5ActionPerformed
        WindowGanti.dispose();
        Truangan1.setText("");
        TkdIndikator1.setText("");
        TnmIndikator1.setText("");
        TkdNumdemon1.setText("");
        TnmNumdemon1.setText("");
        TtglCatat1.setDate(new Date());
        Tjumlah1.setText("0");
    }//GEN-LAST:event_BtnCloseIn5ActionPerformed

    private void Tjumlah1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tjumlah1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (Tjumlah1.getText().contains(",") == true) {
                Tjumlah1.setText(Tjumlah1.getText().trim().replaceAll(",", "."));
            }
            BtnGantiINM.requestFocus();
        }
    }//GEN-LAST:event_Tjumlah1KeyPressed

    private void tbGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGantiKeyPressed
        if (tabMode2.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    getDataGanti();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbGantiKeyPressed

    private void tbGantiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGantiMouseClicked
        if (tabMode2.getRowCount() != 0) {
            try {
                getDataGanti();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbGantiMouseClicked

    private void BtnNumdemonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNumdemonActionPerformed
        tampilNumdemon();
    }//GEN-LAST:event_BtnNumdemonActionPerformed

    private void cmbIndikatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbIndikatorActionPerformed
        Valid.tabelKosong(tabMode3);
    }//GEN-LAST:event_cmbIndikatorActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgIndikatorNasionalMutu dialog = new DlgIndikatorNasionalMutu(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn4;
    private widget.Button BtnCloseIn5;
    private widget.Button BtnConteng;
    private widget.Button BtnGanti;
    private widget.Button BtnGantiINM;
    private widget.Button BtnHapus;
    private widget.Button BtnHapus1;
    private widget.Button BtnHapusINM;
    private widget.Button BtnKeluar;
    private widget.Button BtnNumdemon;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private javax.swing.JMenuItem MnMasterIndikator;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.TextBox Tjumlah;
    private widget.TextBox Tjumlah1;
    private widget.TextBox TkdIndikator;
    private widget.TextBox TkdIndikator1;
    private widget.TextBox TkdNumdemon;
    private widget.TextBox TkdNumdemon1;
    private widget.TextBox TnmIndikator;
    private widget.TextBox TnmIndikator1;
    private widget.TextBox TnmNumdemon;
    private widget.TextBox TnmNumdemon1;
    private widget.TextBox Truangan;
    private widget.TextBox Truangan1;
    private widget.TextBox Ttahun;
    private widget.Tanggal TtglCatat;
    private widget.Tanggal TtglCatat1;
    private javax.swing.JDialog WindowGanti;
    private javax.swing.JDialog WindowHapus;
    private widget.ComboBox cmbBulan;
    private widget.ComboBox cmbGedung;
    private widget.ComboBox cmbGedung1;
    private widget.ComboBox cmbIndikator;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame15;
    private widget.InternalFrame internalFrame16;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.InternalFrame internalFrame9;
    private widget.Label jLabel10;
    private widget.Label jLabel29;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel35;
    private widget.Label jLabel36;
    private widget.Label jLabel37;
    private widget.Label jLabel38;
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbGanti;
    private widget.Table tbHapus;
    private widget.Table tbIndikator;
    private widget.Table tbNumdemon;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Scroll.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
                ".: Data Indikator Nasional Mutu Bulan " + cmbBulan.getSelectedItem().toString() + " Tahun " + Ttahun.getText() + " Ruang Perawatan " + cmbGedung1.getSelectedItem().toString() + " :.",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 13)));

        if (Ttahun.getText().equals("")) {
            Ttahun.setText(Sequel.cariIsi("select year(now())"));
        } else {
            Ttahun.setText(Ttahun.getText());
        }

        hitungTot = 0;
        Valid.tabelKosong(tabMode);

        try {
            ps = koneksi.prepareStatement("SELECT inm.*, m.no_urut urutInm, mn.no_urut, m.nm_indikator, mn.nm_numdemon, MONTH(inm.tgl_catat) bln, YEAR(inm.tgl_catat) thn, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=1 THEN inm.jumlah_pertanggal END),'0') tgl1, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=2 THEN inm.jumlah_pertanggal END),'0') tgl2, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=3 THEN inm.jumlah_pertanggal END),'0') tgl3, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=4 THEN inm.jumlah_pertanggal END),'0') tgl4, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=5 THEN inm.jumlah_pertanggal END),'0') tgl5, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=6 THEN inm.jumlah_pertanggal END),'0') tgl6, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=7 THEN inm.jumlah_pertanggal END),'0') tgl7, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=8 THEN inm.jumlah_pertanggal END),'0') tgl8, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=9 THEN inm.jumlah_pertanggal END),'0') tgl9, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=10 THEN inm.jumlah_pertanggal END),'0') tgl10, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=11 THEN inm.jumlah_pertanggal END),'0') tgl11, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=12 THEN inm.jumlah_pertanggal END),'0') tgl12, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=13 THEN inm.jumlah_pertanggal END),'0') tgl13, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=14 THEN inm.jumlah_pertanggal END),'0') tgl14, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=15 THEN inm.jumlah_pertanggal END),'0') tgl15, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=16 THEN inm.jumlah_pertanggal END),'0') tgl16, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=17 THEN inm.jumlah_pertanggal END),'0') tgl17, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=18 THEN inm.jumlah_pertanggal END),'0') tgl18, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=19 THEN inm.jumlah_pertanggal END),'0') tgl19, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=20 THEN inm.jumlah_pertanggal END),'0') tgl20, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=21 THEN inm.jumlah_pertanggal END),'0') tgl21, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=22 THEN inm.jumlah_pertanggal END),'0') tgl22, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=23 THEN inm.jumlah_pertanggal END),'0') tgl23, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=24 THEN inm.jumlah_pertanggal END),'0') tgl24, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=25 THEN inm.jumlah_pertanggal END),'0') tgl25, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=26 THEN inm.jumlah_pertanggal END),'0') tgl26, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=27 THEN inm.jumlah_pertanggal END),'0') tgl27, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=28 THEN inm.jumlah_pertanggal END),'0') tgl28, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=29 THEN inm.jumlah_pertanggal END),'0') tgl29, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=30 THEN inm.jumlah_pertanggal END),'0') tgl30, "
                    + "ifnull(sum(case when day(inm.tgl_catat)=31 THEN inm.jumlah_pertanggal END),'0') tgl31 "
                    + "from indikator_nasional_mutu inm INNER JOIN master_indikator_nasional_mutu m on m.kd_indikator=inm.kd_indikator "
                    + "inner join master_numdemon_indikator_nasional_mutu mn on mn.kd_numdemon=inm.kd_numdemon where "
                    + "MONTH(inm.tgl_catat)='" + angkaBulan + "' and YEAR(inm.tgl_catat)='" + Ttahun.getText() + "' "
                    + "and inm.gedung='" + cmbGedung1.getSelectedItem().toString() + "' "
                    + "GROUP BY inm.kd_indikator, mn.kd_numdemon, MONTH(inm.tgl_catat), YEAR(inm.tgl_catat), inm.gedung order by m.no_urut, mn.no_urut");
            try {
                rs = ps.executeQuery();
                while (rs.next()) {
                    try {
                        hitungTot = Double.parseDouble(rs.getString("tgl1"))
                                + Double.parseDouble(rs.getString("tgl2"))
                                + Double.parseDouble(rs.getString("tgl3"))
                                + Double.parseDouble(rs.getString("tgl4"))
                                + Double.parseDouble(rs.getString("tgl5"))
                                + Double.parseDouble(rs.getString("tgl6"))
                                + Double.parseDouble(rs.getString("tgl7"))
                                + Double.parseDouble(rs.getString("tgl8"))
                                + Double.parseDouble(rs.getString("tgl9"))
                                + Double.parseDouble(rs.getString("tgl10"))
                                + Double.parseDouble(rs.getString("tgl11"))
                                + Double.parseDouble(rs.getString("tgl12"))
                                + Double.parseDouble(rs.getString("tgl13"))
                                + Double.parseDouble(rs.getString("tgl14"))
                                + Double.parseDouble(rs.getString("tgl15"))
                                + Double.parseDouble(rs.getString("tgl16"))
                                + Double.parseDouble(rs.getString("tgl17"))
                                + Double.parseDouble(rs.getString("tgl18"))
                                + Double.parseDouble(rs.getString("tgl19"))
                                + Double.parseDouble(rs.getString("tgl20"))
                                + Double.parseDouble(rs.getString("tgl21"))
                                + Double.parseDouble(rs.getString("tgl22"))
                                + Double.parseDouble(rs.getString("tgl23"))
                                + Double.parseDouble(rs.getString("tgl24"))
                                + Double.parseDouble(rs.getString("tgl25"))
                                + Double.parseDouble(rs.getString("tgl26"))
                                + Double.parseDouble(rs.getString("tgl27"))
                                + Double.parseDouble(rs.getString("tgl28"))
                                + Double.parseDouble(rs.getString("tgl29"))
                                + Double.parseDouble(rs.getString("tgl30"))
                                + Double.parseDouble(rs.getString("tgl31"));
                        total = Valid.SetAngka2(hitungTot);
                    } catch (Exception e) {
                        System.out.println("Notifikasi : " + e);
                        total = "error";
                    }

                    //cek angka jumlah
                    if (rs.getString("tgl1").equals("0")) {
                        tgl1 = "";
                    } else {
                        tgl1 = rs.getString("tgl1");
                    }

                    if (rs.getString("tgl2").equals("0")) {
                        tgl2 = "";
                    } else {
                        tgl2 = rs.getString("tgl2");
                    }

                    if (rs.getString("tgl3").equals("0")) {
                        tgl3 = "";
                    } else {
                        tgl3 = rs.getString("tgl3");
                    }

                    if (rs.getString("tgl4").equals("0")) {
                        tgl4 = "";
                    } else {
                        tgl4 = rs.getString("tgl4");
                    }

                    if (rs.getString("tgl5").equals("0")) {
                        tgl5 = "";
                    } else {
                        tgl5 = rs.getString("tgl5");
                    }

                    if (rs.getString("tgl6").equals("0")) {
                        tgl6 = "";
                    } else {
                        tgl6 = rs.getString("tgl6");
                    }

                    if (rs.getString("tgl7").equals("0")) {
                        tgl7 = "";
                    } else {
                        tgl7 = rs.getString("tgl7");
                    }

                    if (rs.getString("tgl8").equals("0")) {
                        tgl8 = "";
                    } else {
                        tgl8 = rs.getString("tgl8");
                    }

                    if (rs.getString("tgl9").equals("0")) {
                        tgl9 = "";
                    } else {
                        tgl9 = rs.getString("tgl9");
                    }

                    if (rs.getString("tgl10").equals("0")) {
                        tgl10 = "";
                    } else {
                        tgl10 = rs.getString("tgl10");
                    }

                    if (rs.getString("tgl11").equals("0")) {
                        tgl11 = "";
                    } else {
                        tgl11 = rs.getString("tgl11");
                    }

                    if (rs.getString("tgl12").equals("0")) {
                        tgl12 = "";
                    } else {
                        tgl12 = rs.getString("tgl12");
                    }

                    if (rs.getString("tgl13").equals("0")) {
                        tgl13 = "";
                    } else {
                        tgl13 = rs.getString("tgl13");
                    }

                    if (rs.getString("tgl14").equals("0")) {
                        tgl14 = "";
                    } else {
                        tgl14 = rs.getString("tgl14");
                    }

                    if (rs.getString("tgl15").equals("0")) {
                        tgl15 = "";
                    } else {
                        tgl15 = rs.getString("tgl15");
                    }

                    if (rs.getString("tgl16").equals("0")) {
                        tgl16 = "";
                    } else {
                        tgl16 = rs.getString("tgl16");
                    }

                    if (rs.getString("tgl17").equals("0")) {
                        tgl17 = "";
                    } else {
                        tgl17 = rs.getString("tgl17");
                    }

                    if (rs.getString("tgl18").equals("0")) {
                        tgl18 = "";
                    } else {
                        tgl18 = rs.getString("tgl18");
                    }

                    if (rs.getString("tgl19").equals("0")) {
                        tgl19 = "";
                    } else {
                        tgl19 = rs.getString("tgl19");
                    }

                    if (rs.getString("tgl20").equals("0")) {
                        tgl20 = "";
                    } else {
                        tgl20 = rs.getString("tgl20");
                    }

                    if (rs.getString("tgl21").equals("0")) {
                        tgl21 = "";
                    } else {
                        tgl21 = rs.getString("tgl21");
                    }

                    if (rs.getString("tgl22").equals("0")) {
                        tgl22 = "";
                    } else {
                        tgl22 = rs.getString("tgl22");
                    }

                    if (rs.getString("tgl23").equals("0")) {
                        tgl23 = "";
                    } else {
                        tgl23 = rs.getString("tgl23");
                    }

                    if (rs.getString("tgl24").equals("0")) {
                        tgl24 = "";
                    } else {
                        tgl24 = rs.getString("tgl24");
                    }

                    if (rs.getString("tgl25").equals("0")) {
                        tgl25 = "";
                    } else {
                        tgl25 = rs.getString("tgl25");
                    }

                    if (rs.getString("tgl26").equals("0")) {
                        tgl26 = "";
                    } else {
                        tgl26 = rs.getString("tgl26");
                    }

                    if (rs.getString("tgl27").equals("0")) {
                        tgl27 = "";
                    } else {
                        tgl27 = rs.getString("tgl27");
                    }

                    if (rs.getString("tgl28").equals("0")) {
                        tgl28 = "";
                    } else {
                        tgl28 = rs.getString("tgl28");
                    }

                    if (rs.getString("tgl29").equals("0")) {
                        tgl29 = "";
                    } else {
                        tgl29 = rs.getString("tgl29");
                    }

                    if (rs.getString("tgl30").equals("0")) {
                        tgl30 = "";
                    } else {
                        tgl30 = rs.getString("tgl30");
                    }

                    if (rs.getString("tgl31").equals("0")) {
                        tgl31 = "";
                    } else {
                        tgl31 = rs.getString("tgl31");
                    }

                    tabMode.addRow(new String[]{
                        rs.getString("gedung"),
                        rs.getString("urutInm") + ".",
                        rs.getString("nm_indikator"),
                        rs.getString("nm_numdemon"),
                        tgl1, tgl2, tgl3, tgl4, tgl5, tgl6, tgl7, tgl8, tgl9, tgl10,
                        tgl11, tgl12, tgl13, tgl14, tgl15, tgl16, tgl17, tgl18, tgl19, tgl20,
                        tgl21, tgl22, tgl23, tgl24, tgl25, tgl26, tgl27, tgl28, tgl29, tgl30, tgl31, total,
                        rs.getString("kd_indikator"),
                        rs.getString("tgl_catat"),
                        rs.getString("kd_numdemon")
                    });
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgIndikatorNasionalMutu.tampil() : " + e);
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
    
    public void emptTeks() {
        TtglCatat.setDate(new Date());
        Tjumlah.setText("0");
    }

    private void getData() {
        kdIndikator = "";
        gedungDIpilih = "";
        tglDipilih = "";
        kdNumdemon = "";

        if (tbIndikator.getSelectedRow() != -1) {
            gedungDIpilih = tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 0).toString();
            kdIndikator = tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 36).toString();
            tglDipilih = tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 37).toString();
            kdNumdemon = tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 38).toString();
            
            //untuk proses hapus
            Truangan.setText(gedungDIpilih);
            TkdIndikator.setText(kdIndikator);
            TnmIndikator.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 2).toString());
            TkdNumdemon.setText(kdNumdemon);
            TnmNumdemon.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 3).toString());
            //untuk proses ganti
            Truangan1.setText(gedungDIpilih);
            TkdIndikator1.setText(kdIndikator);
            TnmIndikator1.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 2).toString());
            TkdNumdemon1.setText(kdNumdemon);
            TnmNumdemon1.setText(tbIndikator.getValueAt(tbIndikator.getSelectedRow(), 3).toString());
        }
    }
    
    private void cekBulanTahun(String tanggal) {
        cekBulan = Sequel.cariIsi("select MONTH('" + tanggal + "')");
        Ttahun.setText(Sequel.cariIsi("select YEAR('" + tanggal + "')"));

        if (cekBulan.equals("1")) {
            cmbBulan.setSelectedIndex(0);
        } else if (cekBulan.equals("2")) {
            cmbBulan.setSelectedIndex(1);
        } else if (cekBulan.equals("3")) {
            cmbBulan.setSelectedIndex(2);
        } else if (cekBulan.equals("4")) {
            cmbBulan.setSelectedIndex(3);
        } else if (cekBulan.equals("5")) {
            cmbBulan.setSelectedIndex(4);
        } else if (cekBulan.equals("6")) {
            cmbBulan.setSelectedIndex(5);
        } else if (cekBulan.equals("7")) {
            cmbBulan.setSelectedIndex(6);
        } else if (cekBulan.equals("8")) {
            cmbBulan.setSelectedIndex(7);
        } else if (cekBulan.equals("9")) {
            cmbBulan.setSelectedIndex(8);
        } else if (cekBulan.equals("10")) {
            cmbBulan.setSelectedIndex(9);
        } else if (cekBulan.equals("11")) {
            cmbBulan.setSelectedIndex(10);
        } else if (cekBulan.equals("12")) {
            cmbBulan.setSelectedIndex(11);
        }
    }
    
    private void tampilHapus() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT *, DATE_FORMAT(tgl_catat,'%d-%m-%Y') tgl from indikator_nasional_mutu "
                    + "WHERE kd_indikator='" + TkdIndikator.getText() + "' and gedung='" + Truangan.getText() + "' and "
                    + "MONTH(tgl_catat)=" + cekBulan + " and YEAR(tgl_catat)=" + Ttahun.getText() + " and "
                    + "kd_numdemon='" + TkdNumdemon.getText() + "' order by tgl_catat");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("tgl"),
                        rs1.getString("jumlah_pertanggal"),
                        rs1.getString("tgl_catat")
                    });
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
    
    private void tampilGanti() {
        Valid.tabelKosong(tabMode2);
        try {
            ps2 = koneksi.prepareStatement("SELECT *, DATE_FORMAT(tgl_catat,'%d-%m-%Y') tgl from indikator_nasional_mutu "
                    + "WHERE kd_indikator='" + TkdIndikator1.getText() + "' and gedung='" + Truangan1.getText() + "' and "
                    + "MONTH(tgl_catat)=" + cekBulan + " and YEAR(tgl_catat)=" + Ttahun.getText() + " and "
                    + "kd_numdemon='" + TkdNumdemon1.getText() + "' order by tgl_catat");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    tabMode2.addRow(new String[]{
                        rs2.getString("tgl"),
                        rs2.getString("jumlah_pertanggal"),
                        rs2.getString("tgl_catat")
                    });
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
            System.out.println("Notif : " + e);
        }
    }
    
    private void getDataGanti() {
        if (tbGanti.getSelectedRow() != -1) {
            Valid.SetTgl(TtglCatat1, tbGanti.getValueAt(tbGanti.getSelectedRow(), 2).toString());
            Tjumlah1.setText(tbGanti.getValueAt(tbGanti.getSelectedRow(), 1).toString());
        }
    }
    
    private void tampilNumdemon() {
        kdIndikator = Sequel.cariIsi("select kd_indikator from master_indikator_nasional_mutu "
                + "where gedung='" + cmbGedung.getSelectedItem().toString() + "' and "
                + "nm_indikator='" + cmbIndikator.getSelectedItem().toString() + "'");
        
        Valid.tabelKosong(tabMode3);
        try {
            ps3 = koneksi.prepareStatement("SELECT m1.kd_numdemon, m1.nm_numdemon FROM master_numdemon_indikator_nasional_mutu m1 "
                    + "inner join master_indikator_nasional_mutu m2 on m1.kd_indikator=m2.kd_indikator WHERE "
                    + "m2.gedung='" + cmbGedung.getSelectedItem().toString() + "' and m2.kd_indikator='" + kdIndikator + "' "
                    + "and m1.status_data='aktif' ORDER BY m1.no_urut");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    tabMode3.addRow(new String[]{
                        rs3.getString("kd_numdemon"),
                        rs3.getString("nm_numdemon")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notif : " + e);
        }
    }
}
