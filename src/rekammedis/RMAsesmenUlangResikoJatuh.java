package rekammedis;

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
import kepegawaian.DlgCariPetugas;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author dosen
 */
public class RMAsesmenUlangResikoJatuh extends javax.swing.JDialog {
    private final DefaultTableModel tabMode, tabMode1, tabMode2;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private Properties prop = new Properties();
    private PreparedStatement ps, ps1, ps2, ps3, ps4, psrestor;
    private ResultSet rs, rs1, rs2, rs3, rs4, rsrestor;
    private int i = 0, skor = 0, x = 0;
    private String nip = "", riwayatData = "", user = "";
    private DlgCariPetugas petugas = new DlgCariPetugas(null, false);
    private boolean nilai;
    
    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public RMAsesmenUlangResikoJatuh(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode = new DefaultTableModel(null, new Object[]{
            "No. Rawat", "Kode AURJ", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Jam Asesmen", "Total Skor",
            "Tind. Pencegahan", "Nama Petugas", "Kategori", "Ruang Rawat Inap", "tgl_asesmen", "jam_asesmen", "nip_petugas",
            "waktu_simpan"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        
        tbAsesmen.setModel(tabMode);
        tbAsesmen.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbAsesmen.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 15; i++) {
            TableColumn column = tbAsesmen.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(105);
            } else if (i == 1) {
                column.setPreferredWidth(120);
            } else if (i == 2) {
                column.setPreferredWidth(60);
            } else if (i == 3) {
                column.setPreferredWidth(220);
            } else if (i == 4) {
                column.setPreferredWidth(75);
            } else if (i == 5) {
                column.setPreferredWidth(85);
            } else if (i == 6) {
                column.setPreferredWidth(65);
            } else if (i == 7) {
                column.setPreferredWidth(100);
            } else if (i == 8) {
                column.setPreferredWidth(220);
            } else if (i == 9) {
                column.setPreferredWidth(350);
            } else if (i == 10) {
                column.setPreferredWidth(220);
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
            } 
        }
        tbAsesmen.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode1=new DefaultTableModel(null,new Object[]{
            "Cek", "kode", "Faktor Resiko", "Skala", "Poin"
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
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        
        tbFaktorResiko.setModel(tabMode1);
        tbFaktorResiko.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbFaktorResiko.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 5; i++) {
            TableColumn column = tbFaktorResiko.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(30);
            } else if (i == 1) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 2) {
                column.setPreferredWidth(240);
            } else if (i == 3) {
                column.setPreferredWidth(300);
            } else if (i == 4) {
                column.setPreferredWidth(50);
            }
        }
        tbFaktorResiko.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2 = new DefaultTableModel(null, new Object[]{
            "Dilakukan Oleh", "No. Rawat", "No. RM", "Nama Pasien", "Tgl. Asesmen", "Jam Asesmen",
            "Tgl. Eksekusi", "Status Data", "waktu_simpan", "kode_ulang_resiko"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        tbRiwayat.setModel(tabMode2);
        tbRiwayat.setPreferredScrollableViewportSize(new Dimension(500, 500));
        tbRiwayat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 10; i++) {
            TableColumn column = tbRiwayat.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(220);
            } else if (i == 1) {
                column.setPreferredWidth(105);
            } else if (i == 2) {
                column.setPreferredWidth(50);
            } else if (i == 3) {
                column.setPreferredWidth(200);
            } else if (i == 4) {
                column.setPreferredWidth(80);
            } else if (i == 5) {
                column.setPreferredWidth(80);
            } else if (i == 6) {
                column.setPreferredWidth(140);
            } else if (i == 7) {
                column.setPreferredWidth(80);
            } else if (i == 8) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } else if (i == 9) {
                column.setMinWidth(0);
                column.setMaxWidth(0);
            } 
        }
        tbRiwayat.setDefaultRenderer(Object.class, new WarnaTable());

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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if (petugas.getTable().getSelectedRow() != -1) {
                    nip = petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 0).toString();                    
                    TnmPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(), 1).toString());
                    BtnPetugas.requestFocus();
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
        
        ChkInput.setSelected(true);
        isForm();
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
        MnRiwayatData = new javax.swing.JMenuItem();
        WindowRiwayat = new javax.swing.JDialog();
        internalFrame13 = new widget.InternalFrame();
        internalFrame18 = new widget.InternalFrame();
        internalFrame17 = new widget.InternalFrame();
        jLabel30 = new widget.Label();
        DTPCari3 = new widget.Tanggal();
        jLabel31 = new widget.Label();
        DTPCari4 = new widget.Tanggal();
        jLabel32 = new widget.Label();
        TCari2 = new widget.TextBox();
        BtnCari2 = new widget.Button();
        jLabel33 = new widget.Label();
        LCount1 = new widget.Label();
        internalFrame19 = new widget.InternalFrame();
        BtnAll1 = new widget.Button();
        BtnRestor = new widget.Button();
        BtnCloseIn10 = new widget.Button();
        Scroll6 = new widget.ScrollPane();
        tbRiwayat = new widget.Table();
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
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel5 = new widget.Label();
        TNoRw = new widget.TextBox();
        TNoRm = new widget.TextBox();
        TPasien = new widget.TextBox();
        jLabel8 = new widget.Label();
        tglAsesmen = new widget.Tanggal();
        jLabel9 = new widget.Label();
        cmbJam = new widget.ComboBox();
        cmbMnt = new widget.ComboBox();
        cmbDtk = new widget.ComboBox();
        jLabel10 = new widget.Label();
        Scroll8 = new widget.ScrollPane();
        tbFaktorResiko = new widget.Table();
        jLabel11 = new widget.Label();
        TkdFaktor = new widget.TextBox();
        jLabel12 = new widget.Label();
        TtotSkor = new widget.TextBox();
        jLabel13 = new widget.Label();
        Tkategori = new widget.TextBox();
        jLabel14 = new widget.Label();
        TabPencegahan = new javax.swing.JTabbedPane();
        panelBiasa8 = new widget.PanelBiasa();
        cegahA = new widget.TextArea();
        panelBiasa9 = new widget.PanelBiasa();
        cegahB = new widget.TextArea();
        panelBiasa10 = new widget.PanelBiasa();
        cegahC = new widget.TextArea();
        jLabel140 = new widget.Label();
        cmbTindakanCegah = new widget.ComboBox();
        jLabel99 = new widget.Label();
        TnmPetugas = new widget.TextBox();
        BtnPetugas = new widget.Button();
        jLabel15 = new widget.Label();
        TruangRwt = new widget.TextBox();
        jLabel141 = new widget.Label();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbAsesmen = new widget.Table();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnRiwayatData.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnRiwayatData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnRiwayatData.setText("Riwayat Data Terhapus/Diganti");
        MnRiwayatData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnRiwayatData.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnRiwayatData.setIconTextGap(5);
        MnRiwayatData.setName("MnRiwayatData"); // NOI18N
        MnRiwayatData.setPreferredSize(new java.awt.Dimension(195, 26));
        MnRiwayatData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnRiwayatDataActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnRiwayatData);

        WindowRiwayat.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowRiwayat.setName("WindowRiwayat"); // NOI18N
        WindowRiwayat.setUndecorated(true);
        WindowRiwayat.setResizable(false);

        internalFrame13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Data Riwayat Asesmen Ulang Resiko Jatuh Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        internalFrame13.setName("internalFrame13"); // NOI18N
        internalFrame13.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame13.setLayout(new java.awt.BorderLayout());

        internalFrame18.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame18.setName("internalFrame18"); // NOI18N
        internalFrame18.setPreferredSize(new java.awt.Dimension(400, 88));
        internalFrame18.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame18.setLayout(new java.awt.BorderLayout());

        internalFrame17.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame17.setName("internalFrame17"); // NOI18N
        internalFrame17.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame17.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame17.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 7, 9));

        jLabel30.setForeground(new java.awt.Color(0, 0, 0));
        jLabel30.setText("Tanggal :");
        jLabel30.setName("jLabel30"); // NOI18N
        jLabel30.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel30);

        DTPCari3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2024" }));
        DTPCari3.setDisplayFormat("dd-MM-yyyy");
        DTPCari3.setName("DTPCari3"); // NOI18N
        DTPCari3.setOpaque(false);
        DTPCari3.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari3);

        jLabel31.setForeground(new java.awt.Color(0, 0, 0));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("s.d.");
        jLabel31.setName("jLabel31"); // NOI18N
        jLabel31.setPreferredSize(new java.awt.Dimension(23, 23));
        internalFrame17.add(jLabel31);

        DTPCari4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2024" }));
        DTPCari4.setDisplayFormat("dd-MM-yyyy");
        DTPCari4.setName("DTPCari4"); // NOI18N
        DTPCari4.setOpaque(false);
        DTPCari4.setPreferredSize(new java.awt.Dimension(90, 23));
        internalFrame17.add(DTPCari4);

        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setText("Key Word :");
        jLabel32.setName("jLabel32"); // NOI18N
        jLabel32.setPreferredSize(new java.awt.Dimension(60, 23));
        internalFrame17.add(jLabel32);

        TCari2.setForeground(new java.awt.Color(0, 0, 0));
        TCari2.setName("TCari2"); // NOI18N
        TCari2.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(TCari2);

        BtnCari2.setForeground(new java.awt.Color(0, 0, 0));
        BtnCari2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari2.setMnemonic('1');
        BtnCari2.setText("Tampilkan Data");
        BtnCari2.setToolTipText("Alt+1");
        BtnCari2.setName("BtnCari2"); // NOI18N
        BtnCari2.setPreferredSize(new java.awt.Dimension(130, 23));
        BtnCari2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari2ActionPerformed(evt);
            }
        });
        BtnCari2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCari2KeyPressed(evt);
            }
        });
        internalFrame17.add(BtnCari2);

        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setText("Record :");
        jLabel33.setName("jLabel33"); // NOI18N
        jLabel33.setPreferredSize(new java.awt.Dimension(65, 23));
        internalFrame17.add(jLabel33);

        LCount1.setForeground(new java.awt.Color(0, 0, 0));
        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setText("0");
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(50, 23));
        internalFrame17.add(LCount1);

        internalFrame18.add(internalFrame17, java.awt.BorderLayout.CENTER);

        internalFrame19.setMinimumSize(new java.awt.Dimension(0, 50));
        internalFrame19.setName("internalFrame19"); // NOI18N
        internalFrame19.setPreferredSize(new java.awt.Dimension(400, 44));
        internalFrame19.setWarnaBawah(new java.awt.Color(245, 250, 240));
        internalFrame19.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 7, 9));

        BtnAll1.setForeground(new java.awt.Color(0, 0, 0));
        BtnAll1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll1.setMnemonic('2');
        BtnAll1.setText("Semua Data");
        BtnAll1.setToolTipText("Alt+2");
        BtnAll1.setName("BtnAll1"); // NOI18N
        BtnAll1.setPreferredSize(new java.awt.Dimension(120, 23));
        BtnAll1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAll1ActionPerformed(evt);
            }
        });
        BtnAll1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAll1KeyPressed(evt);
            }
        });
        internalFrame19.add(BtnAll1);

        BtnRestor.setForeground(new java.awt.Color(0, 0, 0));
        BtnRestor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/42a.png"))); // NOI18N
        BtnRestor.setMnemonic('U');
        BtnRestor.setText("Restore");
        BtnRestor.setToolTipText("Alt+U");
        BtnRestor.setName("BtnRestor"); // NOI18N
        BtnRestor.setPreferredSize(new java.awt.Dimension(100, 23));
        BtnRestor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRestorActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnRestor);

        BtnCloseIn10.setForeground(new java.awt.Color(0, 0, 0));
        BtnCloseIn10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn10.setMnemonic('U');
        BtnCloseIn10.setText("Tutup");
        BtnCloseIn10.setToolTipText("Alt+U");
        BtnCloseIn10.setName("BtnCloseIn10"); // NOI18N
        BtnCloseIn10.setPreferredSize(new java.awt.Dimension(90, 23));
        BtnCloseIn10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseIn10ActionPerformed(evt);
            }
        });
        internalFrame19.add(BtnCloseIn10);

        internalFrame18.add(internalFrame19, java.awt.BorderLayout.PAGE_END);

        internalFrame13.add(internalFrame18, java.awt.BorderLayout.PAGE_END);

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        tbRiwayat.setToolTipText("Silahkan pilih salah satu data yang mau dihapus/direstore");
        tbRiwayat.setName("tbRiwayat"); // NOI18N
        Scroll6.setViewportView(tbRiwayat);

        internalFrame13.add(Scroll6, java.awt.BorderLayout.CENTER);

        WindowRiwayat.getContentPane().add(internalFrame13, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 3), "::[ Asesmen Ulang Resiko Jatuh ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
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

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Tgl. Asesmen :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass10.add(jLabel19);

        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2024" }));
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

        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2024" }));
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

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 497));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setForeground(new java.awt.Color(0, 0, 0));
        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setComponentPopupMenu(jPopupMenu1);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Pasien :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 120, 23);

        TNoRw.setEditable(false);
        TNoRw.setForeground(new java.awt.Color(0, 0, 0));
        TNoRw.setName("TNoRw"); // NOI18N
        FormInput.add(TNoRw);
        TNoRw.setBounds(125, 10, 122, 23);

        TNoRm.setEditable(false);
        TNoRm.setForeground(new java.awt.Color(0, 0, 0));
        TNoRm.setName("TNoRm"); // NOI18N
        FormInput.add(TNoRm);
        TNoRm.setBounds(252, 10, 70, 23);

        TPasien.setEditable(false);
        TPasien.setForeground(new java.awt.Color(0, 0, 0));
        TPasien.setName("TPasien"); // NOI18N
        FormInput.add(TPasien);
        TPasien.setBounds(326, 10, 350, 23);

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Tgl. Asesmen :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(0, 66, 120, 23);

        tglAsesmen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "03-03-2024" }));
        tglAsesmen.setDisplayFormat("dd-MM-yyyy");
        tglAsesmen.setName("tglAsesmen"); // NOI18N
        tglAsesmen.setOpaque(false);
        tglAsesmen.setPreferredSize(new java.awt.Dimension(90, 23));
        FormInput.add(tglAsesmen);
        tglAsesmen.setBounds(125, 66, 90, 23);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Jam Asesmen :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(220, 66, 100, 23);

        cmbJam.setForeground(new java.awt.Color(0, 0, 0));
        cmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        cmbJam.setName("cmbJam"); // NOI18N
        cmbJam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbJamMouseReleased(evt);
            }
        });
        FormInput.add(cmbJam);
        cmbJam.setBounds(326, 66, 45, 23);

        cmbMnt.setForeground(new java.awt.Color(0, 0, 0));
        cmbMnt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbMnt.setName("cmbMnt"); // NOI18N
        cmbMnt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbMntMouseReleased(evt);
            }
        });
        FormInput.add(cmbMnt);
        cmbMnt.setBounds(377, 66, 45, 23);

        cmbDtk.setForeground(new java.awt.Color(0, 0, 0));
        cmbDtk.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        cmbDtk.setName("cmbDtk"); // NOI18N
        cmbDtk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmbDtkMouseReleased(evt);
            }
        });
        FormInput.add(cmbDtk);
        cmbDtk.setBounds(429, 66, 45, 23);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Faktor Resiko :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 94, 120, 23);

        Scroll8.setName("Scroll8"); // NOI18N

        tbFaktorResiko.setToolTipText("Silahkan conteng pilihan data faktor resiko jatuh");
        tbFaktorResiko.setName("tbFaktorResiko"); // NOI18N
        tbFaktorResiko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbFaktorResikoMouseClicked(evt);
            }
        });
        tbFaktorResiko.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbFaktorResikoKeyReleased(evt);
            }
        });
        Scroll8.setViewportView(tbFaktorResiko);

        FormInput.add(Scroll8);
        Scroll8.setBounds(125, 94, 650, 156);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Kode AURJ :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(480, 66, 80, 23);

        TkdFaktor.setEditable(false);
        TkdFaktor.setForeground(new java.awt.Color(0, 0, 0));
        TkdFaktor.setName("TkdFaktor"); // NOI18N
        FormInput.add(TkdFaktor);
        TkdFaktor.setBounds(567, 66, 130, 23);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Total Skor :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 255, 120, 23);

        TtotSkor.setEditable(false);
        TtotSkor.setForeground(new java.awt.Color(0, 0, 0));
        TtotSkor.setName("TtotSkor"); // NOI18N
        FormInput.add(TtotSkor);
        TtotSkor.setBounds(125, 255, 50, 23);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Kategori :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(180, 255, 60, 23);

        Tkategori.setEditable(false);
        Tkategori.setForeground(new java.awt.Color(0, 0, 0));
        Tkategori.setName("Tkategori"); // NOI18N
        FormInput.add(Tkategori);
        Tkategori.setBounds(244, 255, 535, 23);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Pilihan Tindakan Pencegahan Resiko Jatuh :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 283, 240, 23);

        TabPencegahan.setBackground(new java.awt.Color(255, 255, 254));
        TabPencegahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        TabPencegahan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TabPencegahan.setName("TabPencegahan"); // NOI18N
        TabPencegahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabPencegahanMouseClicked(evt);
            }
        });

        panelBiasa8.setName("panelBiasa8"); // NOI18N
        panelBiasa8.setLayout(new java.awt.BorderLayout());

        cegahA.setEditable(false);
        cegahA.setBackground(new java.awt.Color(255, 255, 255));
        cegahA.setColumns(20);
        cegahA.setRows(5);
        cegahA.setText("1. Orientasi lingkungan\n2. Posisi tempat tidur rendah dan terkunci\n3. Rel tempat tidur dipasang (dinaikkan)\n4. Pencahayaan adekuat\n5. Edukasi pencegahan jatuh");
        cegahA.setName("cegahA"); // NOI18N
        cegahA.setOpaque(true);
        panelBiasa8.add(cegahA, java.awt.BorderLayout.CENTER);

        TabPencegahan.addTab("Pencegahan Umum (A)", panelBiasa8);

        panelBiasa9.setName("panelBiasa9"); // NOI18N
        panelBiasa9.setLayout(new java.awt.BorderLayout());

        cegahB.setEditable(false);
        cegahB.setBackground(new java.awt.Color(255, 255, 255));
        cegahB.setColumns(20);
        cegahB.setRows(5);
        cegahB.setText("1. Lakukan semua pencegahan umum (A)\n2. Menawarkan bantuan untuk ambulansi\n3. Beri tanda identifikasi dengan pin/kancing kuning pada gelang identitas");
        cegahB.setName("cegahB"); // NOI18N
        cegahB.setOpaque(true);
        panelBiasa9.add(cegahB, java.awt.BorderLayout.CENTER);

        TabPencegahan.addTab("Pencegahan Resiko Sedang (B)", panelBiasa9);

        panelBiasa10.setName("panelBiasa10"); // NOI18N
        panelBiasa10.setLayout(new java.awt.BorderLayout());

        cegahC.setEditable(false);
        cegahC.setBackground(new java.awt.Color(255, 255, 255));
        cegahC.setColumns(20);
        cegahC.setRows(5);
        cegahC.setText("1. Lakukan semua pencegahan umum A dan B\n2. Beri tanda segitiga warna kuning pada bed pasien\n3. Kunjungi dan monitor setiap 1 jam\n4. Pastikan pasien menggunakan alat bantu\n5. Libatkan keluarga untuk mengawasi pasien");
        cegahC.setName("cegahC"); // NOI18N
        cegahC.setOpaque(true);
        panelBiasa10.add(cegahC, java.awt.BorderLayout.CENTER);

        TabPencegahan.addTab("Pencegahan Resiko Tinggi (C)", panelBiasa10);

        FormInput.add(TabPencegahan);
        TabPencegahan.setBounds(25, 305, 565, 128);

        jLabel140.setForeground(new java.awt.Color(0, 0, 0));
        jLabel140.setText("Tindakan Pencegahan :");
        jLabel140.setName("jLabel140"); // NOI18N
        FormInput.add(jLabel140);
        jLabel140.setBounds(600, 340, 130, 23);

        cmbTindakanCegah.setForeground(new java.awt.Color(0, 0, 0));
        cmbTindakanCegah.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "A", "B", "C" }));
        cmbTindakanCegah.setName("cmbTindakanCegah"); // NOI18N
        cmbTindakanCegah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTindakanCegahActionPerformed(evt);
            }
        });
        FormInput.add(cmbTindakanCegah);
        cmbTindakanCegah.setBounds(737, 340, 40, 23);

        jLabel99.setForeground(new java.awt.Color(0, 0, 0));
        jLabel99.setText("Nama Petugas : ");
        jLabel99.setName("jLabel99"); // NOI18N
        FormInput.add(jLabel99);
        jLabel99.setBounds(0, 440, 120, 23);

        TnmPetugas.setEditable(false);
        TnmPetugas.setForeground(new java.awt.Color(0, 0, 0));
        TnmPetugas.setToolTipText("Alt+C");
        TnmPetugas.setName("TnmPetugas"); // NOI18N
        TnmPetugas.setPreferredSize(new java.awt.Dimension(140, 23));
        FormInput.add(TnmPetugas);
        TnmPetugas.setBounds(125, 440, 440, 23);

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
        BtnPetugas.setBounds(570, 440, 28, 23);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Ruang Rawat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 38, 120, 23);

        TruangRwt.setEditable(false);
        TruangRwt.setForeground(new java.awt.Color(0, 0, 0));
        TruangRwt.setName("TruangRwt"); // NOI18N
        FormInput.add(TruangRwt);
        TruangRwt.setBounds(125, 38, 550, 23);

        jLabel141.setForeground(new java.awt.Color(0, 0, 0));
        jLabel141.setText("Resiko Jatuh Dipilih  ");
        jLabel141.setName("jLabel141"); // NOI18N
        FormInput.add(jLabel141);
        jLabel141.setBounds(600, 355, 130, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbAsesmen.setToolTipText("Silahkan klik untuk memilih data yang diperbaiki");
        tbAsesmen.setComponentPopupMenu(jPopupMenu1);
        tbAsesmen.setName("tbAsesmen"); // NOI18N
        tbAsesmen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsesmenMouseClicked(evt);
            }
        });
        tbAsesmen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbAsesmenKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbAsesmen);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        internalFrame1.add(internalFrame2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            //cek data faktor resiko jatuh
            x = 0;
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    x++;
                }
            }
            
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data faktor resikonya..!!!!");
                tbFaktorResiko.requestFocus();
            } else {
                if (Sequel.menyimpantf("asesmen_ulang_resiko_jatuh", "?,?,?,?,?,?,?,?,?,?", "No.Rawat", 10, new String[]{
                        TNoRw.getText(), Valid.SetTgl(tglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                        TkdFaktor.getText(), TtotSkor.getText(), cmbTindakanCegah.getSelectedItem().toString(), nip, Tkategori.getText(),
                        TruangRwt.getText(), Sequel.cariIsi("select now()")
                    }) == true) {
                    
                    //simpan faktor resiko
                    for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                        if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                            Sequel.menyimpan2("detail_asesmen_ulang_resiko_jatuh", "?,?,?", 3, new String[]{
                                TNoRw.getText(), TkdFaktor.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                        }
                    }

                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(tglAsesmen.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                }
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
        ChkInput.setSelected(true);
        isForm(); 
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            emptTeks();
        } else {
            Valid.pindah(evt, BtnSimpan, BtnGanti);
        }
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnGantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGantiActionPerformed
        if (TNoRw.getText().trim().equals("")) {
            Valid.textKosong(TNoRw, "Pasien");
        } else {
            if (tbAsesmen.getSelectedRow() > -1) {
                //cek data faktor resiko jatuh
                x = 0;
                for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                    if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                        x++;
                    }
                }

                if (x == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan conteng dulu data faktor resikonya..!!!!");
                    tbFaktorResiko.requestFocus();
                } else {
                    riwayatData = "ganti";
                    SimpanHistori();
                    if (Sequel.mengedittf("asesmen_ulang_resiko_jatuh", "waktu_simpan=?", "tgl_asesmen=?, jam_asesmen=?, kode_ulang_resiko=?, total_skor=?, tindakan_pencegahan=?, "
                            + "nip_petugas=?, kategori=?", 8, new String[]{
                                Valid.SetTgl(tglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
                                TkdFaktor.getText(), TtotSkor.getText(), cmbTindakanCegah.getSelectedItem().toString(), nip, Tkategori.getText(),
                                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString()
                            }) == true) {

                        Sequel.meghapus("detail_asesmen_ulang_resiko_jatuh", "kode_ulang_resiko",
                                tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
                        
                        //simpan faktor resiko
                        for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                            if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                                Sequel.menyimpan2("detail_asesmen_ulang_resiko_jatuh", "?,?,?", 3, new String[]{
                                    TNoRw.getText(), TkdFaktor.getText(), tbFaktorResiko.getValueAt(i, 1).toString()});
                            }
                        }

                        TCari.setText(TNoRw.getText());
                        Valid.SetTgl(DTPCari1, Valid.SetTgl(tglAsesmen.getSelectedItem() + ""));
                        emptTeks();
                        BtnCariActionPerformed(null);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnGantiActionPerformed

    private void BtnGantiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnGantiKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnGantiActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnKeluar);
        }
}//GEN-LAST:event_BtnGantiKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
        WindowRiwayat.dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnBatal,TCari);}
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

    private void tbAsesmenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsesmenMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbAsesmenMouseClicked

    private void tbAsesmenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbAsesmenKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbAsesmenKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void cmbJamMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbJamMouseReleased
        AutoCompleteDecorator.decorate(cmbJam);
    }//GEN-LAST:event_cmbJamMouseReleased

    private void cmbMntMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbMntMouseReleased
        AutoCompleteDecorator.decorate(cmbMnt);
    }//GEN-LAST:event_cmbMntMouseReleased

    private void cmbDtkMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbDtkMouseReleased
        AutoCompleteDecorator.decorate(cmbDtk);
    }//GEN-LAST:event_cmbDtkMouseReleased

    private void tbFaktorResikoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbFaktorResikoMouseClicked
        if (tabMode1.getRowCount() != 0) {
            try {
                hitungSkorRJ();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_tbFaktorResikoMouseClicked

    private void tbFaktorResikoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyPressed
        if (tabMode1.getRowCount() != 0) {
            if (evt.getKeyCode() == KeyEvent.VK_SHIFT) {
                TCari.setText("");
                TCari.requestFocus();
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyPressed

    private void tbFaktorResikoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbFaktorResikoKeyReleased
        if (tabMode1.getRowCount() != 0) {
            if ((evt.getKeyCode() == KeyEvent.VK_ENTER) || (evt.getKeyCode() == KeyEvent.VK_UP) || (evt.getKeyCode() == KeyEvent.VK_DOWN)) {
                try {
                    hitungSkorRJ();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbFaktorResikoKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampilFaktorResiko();
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void cmbTindakanCegahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTindakanCegahActionPerformed
        if (cmbTindakanCegah.getSelectedIndex() == 1) {
            TabPencegahan.setSelectedIndex(0);
        } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
            TabPencegahan.setSelectedIndex(1);
        } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
            TabPencegahan.setSelectedIndex(2);
        } else {
            TabPencegahan.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cmbTindakanCegahActionPerformed

    private void BtnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPetugasActionPerformed
        akses.setform("RMAsesmenUlangResikoJatuh");
        petugas.isCek();
        petugas.setSize(983, internalFrame1.getHeight() - 40);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setAlwaysOnTop(false);
        petugas.setVisible(true);
    }//GEN-LAST:event_BtnPetugasActionPerformed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            Map<String, Object> param = new HashMap<>();
            param.put("namars", akses.getnamars());
            param.put("logo", Sequel.cariGambar("select logo from setting"));
            param.put("norm", TNoRm.getText());
            param.put("nmpasien", TPasien.getText());
            param.put("tgllahir", Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis='" + TNoRm.getText() + "'"));
            param.put("tglasesmen", Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")));
            param.put("jamasesmen", cmbJam.getSelectedItem().toString() + ":" + cmbMnt.getSelectedItem().toString() + " WITA");
            param.put("totalskor", TtotSkor.getText());
            param.put("tindakancegah", cmbTindakanCegah.getSelectedItem().toString());
            param.put("nmpetugas", TnmPetugas.getText());
            param.put("kategori", Tkategori.getText());

            if (cmbTindakanCegah.getSelectedIndex() == 1) {
                param.put("judultindakan", "Pencegahan Umum (A)");
                param.put("isitindakan", cegahA.getText() + "\n\n"
                        + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
                        + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                param.put("judultindakan", "Pencegahan Resiko Sedang (B)");
                param.put("isitindakan", cegahB.getText() + "\n\n"
                        + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
                        + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
                param.put("judultindakan", "Pencegahan Resiko Tinggi (C)");
                param.put("isitindakan", cegahC.getText() + "\n\n"
                        + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
                        + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
            } else {
                param.put("judultindakan", "-");
                param.put("isitindakan", "-\n\n"
                        + "Tanggal : " + Valid.SetTglINDONESIA(Valid.SetTgl(tglAsesmen.getSelectedItem() + "")) + "\n"
                        + "Tindakan Pencegahan Resiko Jatuh : " + cmbTindakanCegah.getSelectedItem().toString() + "\n");
            }

            Valid.MyReport("rptAsesmenUlangRJ.jasper", "report", "::[ Asesmen Ulang Resiko Jatuh ]::",
                    "SELECT b.faktor_resiko, b.skala, b.skor from detail_asesmen_ulang_resiko_jatuh a "
                    + "inner join master_faktor_resiko_igd b on b.kode_resiko=a.kode_resiko WHERE "
                    + "a.kode_ulang_resiko='" + TkdFaktor.getText() + "' order by b.kode_resiko", param);

            TCari.setText(TNoRw.getText());
            Valid.SetTgl(DTPCari1, Valid.SetTgl(tglAsesmen.getSelectedItem() + ""));
            emptTeks();
            BtnCariActionPerformed(null);
        } else {
            JOptionPane.showMessageDialog(null, "Maaf, silahkan pilih salah satu datanya terlebih dulu..!!!!");
            tbAsesmen.requestFocus();
        }
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnPrintActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnGanti, BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if (tbAsesmen.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data mau dihapus..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                riwayatData = "hapus";
                SimpanHistori();
                if (Sequel.queryu2tf("delete from asesmen_ulang_resiko_jatuh where waktu_simpan=?", 1, new String[]{
                    tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString()
                }) == true) {
                    Sequel.meghapus("detail_asesmen_ulang_resiko_jatuh", "kode_ulang_resiko",
                            tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());

                    TCari.setText(TNoRw.getText());
                    Valid.SetTgl(DTPCari1, Valid.SetTgl(tglAsesmen.getSelectedItem() + ""));
                    emptTeks();
                    BtnCariActionPerformed(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal menghapus..!!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salh satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnHapusActionPerformed

    private void TabPencegahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabPencegahanMouseClicked
        if (TabPencegahan.getSelectedIndex() == 0) {
            cmbTindakanCegah.setSelectedIndex(1);
        } else if (TabPencegahan.getSelectedIndex() == 1) {
            cmbTindakanCegah.setSelectedIndex(2);
        } else if (TabPencegahan.getSelectedIndex() == 2) {
            cmbTindakanCegah.setSelectedIndex(3);
        }
    }//GEN-LAST:event_TabPencegahanMouseClicked

    private void MnRiwayatDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnRiwayatDataActionPerformed
        Valid.SetTgl(DTPCari3, Valid.SetTgl(DTPCari1.getSelectedItem() + ""));
        DTPCari4.setDate(new Date());
        TCari2.setText(TNoRm.getText());
        BtnCari2ActionPerformed(null);
        WindowRiwayat.setSize(985, internalFrame1.getHeight() - 40);
        WindowRiwayat.setLocationRelativeTo(internalFrame1);
        WindowRiwayat.setAlwaysOnTop(false);
        WindowRiwayat.setVisible(true);
    }//GEN-LAST:event_MnRiwayatDataActionPerformed

    private void TCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BtnCari2ActionPerformed(null);
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            BtnCari2.requestFocus();
        } else if (evt.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            BtnCloseIn10.requestFocus();
        }
    }//GEN-LAST:event_TCari2KeyPressed

    private void BtnCari2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari2ActionPerformed
        tampilRiwayat();
    }//GEN-LAST:event_BtnCari2ActionPerformed

    private void BtnCari2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCari2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnCari2ActionPerformed(null);
        } else {
            Valid.pindah(evt, TCari2, BtnAll1);
        }
    }//GEN-LAST:event_BtnCari2KeyPressed

    private void BtnAll1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAll1ActionPerformed
        TCari2.setText("");
        tampilRiwayat();
    }//GEN-LAST:event_BtnAll1ActionPerformed

    private void BtnAll1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAll1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAll1ActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari2, TCari2);
        }
    }//GEN-LAST:event_BtnAll1KeyPressed

    private void BtnRestorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRestorActionPerformed
        if (tbRiwayat.getSelectedRow() > -1) {
            x = JOptionPane.showConfirmDialog(rootPane, "Yakin data yang dipilih & telah " + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString()
                + " akan dikembalikan/restore..??", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                if (tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 7).toString().equals("DIHAPUS")) {
                    if (Sequel.cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh where "
                        + "waktu_simpan='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString() + "'") > 0) {
                    JOptionPane.showMessageDialog(rootPane, "Proses kembalikan/restore data gagal, krn. sudah ada datanya dg. waktu simpan yg. sama..!!");
                } else {
                    kembalikanData();
                    TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                    Valid.SetTgl(DTPCari1, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                    BtnCloseIn10ActionPerformed(null);
                    tampil();
                    emptTeks();
                }
            } else {
                kembalikanDataDiganti();
                TCari.setText(tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 1).toString());
                Valid.SetTgl(DTPCari1, tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 4).toString());
                BtnCloseIn10ActionPerformed(null);
                tampil();
                emptTeks();
            }
        }
        } else {
            WindowRiwayat.setSize(1043, internalFrame1.getHeight() - 40);
            WindowRiwayat.setLocationRelativeTo(internalFrame1);
            WindowRiwayat.setAlwaysOnTop(false);
            WindowRiwayat.setVisible(true);
            JOptionPane.showMessageDialog(rootPane, "Silahkan pilih salah satu datanya terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRestorActionPerformed

    private void BtnCloseIn10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseIn10ActionPerformed
        WindowRiwayat.dispose();
        TCari2.setText("");
    }//GEN-LAST:event_BtnCloseIn10ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMAsesmenUlangResikoJatuh dialog = new RMAsesmenUlangResikoJatuh(new javax.swing.JFrame(), true);
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
    private widget.Button BtnAll1;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari2;
    private widget.Button BtnCloseIn10;
    private widget.Button BtnGanti;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPetugas;
    private widget.Button BtnPrint;
    private widget.Button BtnRestor;
    private widget.Button BtnSimpan;
    public widget.CekBox ChkInput;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPCari3;
    private widget.Tanggal DTPCari4;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnRiwayatData;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll6;
    private widget.ScrollPane Scroll8;
    public widget.TextBox TCari;
    private widget.TextBox TCari2;
    private widget.TextBox TNoRm;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private javax.swing.JTabbedPane TabPencegahan;
    private widget.TextBox Tkategori;
    private widget.TextBox TkdFaktor;
    private widget.TextBox TnmPetugas;
    private widget.TextBox TruangRwt;
    private widget.TextBox TtotSkor;
    private javax.swing.JDialog WindowRiwayat;
    private widget.TextArea cegahA;
    private widget.TextArea cegahB;
    private widget.TextArea cegahC;
    private widget.ComboBox cmbDtk;
    private widget.ComboBox cmbJam;
    private widget.ComboBox cmbMnt;
    private widget.ComboBox cmbTindakanCegah;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame13;
    private widget.InternalFrame internalFrame17;
    private widget.InternalFrame internalFrame18;
    private widget.InternalFrame internalFrame19;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel140;
    private widget.Label jLabel141;
    private widget.Label jLabel15;
    private widget.Label jLabel19;
    private widget.Label jLabel21;
    private widget.Label jLabel30;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private widget.Label jLabel99;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.PanelBiasa panelBiasa10;
    private widget.PanelBiasa panelBiasa8;
    private widget.PanelBiasa panelBiasa9;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbAsesmen;
    private widget.Table tbFaktorResiko;
    private widget.Table tbRiwayat;
    private widget.Tanggal tglAsesmen;
    // End of variables declaration//GEN-END:variables

    public void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
            ps = koneksi.prepareStatement("SELECT *, p.no_rkm_medis, p.nm_pasien, DATE_FORMAT(au.tgl_asesmen,'%d-%m-%Y') tgl, "
                    + "TIME_FORMAT(au.jam_asesmen,'%H:%i') jam, pg.nama ptgs from asesmen_ulang_resiko_jatuh au "
                    + "inner join reg_periksa rp on rp.no_rawat=au.no_rawat inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "
                    + "inner join pegawai pg on pg.nik=au.nip_petugas where "
                    + "au.tgl_asesmen between ? and ? and au.no_rawat like ? or "
                    + "au.tgl_asesmen between ? and ? and au.kode_ulang_resiko like ? or "
                    + "au.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "au.tgl_asesmen between ? and ? and p.nm_pasien like ? or "
                    + "au.tgl_asesmen between ? and ? and pg.nama like ? order by au.tgl_asesmen desc, au.jam_asesmen desc");
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
                rs = ps.executeQuery();                
                while (rs.next()) {
                    tabMode.addRow(new String[]{                        
                        rs.getString("no_rawat"),
                        rs.getString("kode_ulang_resiko"),
                        rs.getString("no_rkm_medis"),
                        rs.getString("nm_pasien"),
                        rs.getString("tgl"),
                        rs.getString("jam"),
                        rs.getString("total_skor"),
                        rs.getString("tindakan_pencegahan"),
                        rs.getString("ptgs"),
                        rs.getString("kategori"),
                        rs.getString("ruang_rawat"),
                        rs.getString("tgl_asesmen"),
                        rs.getString("jam_asesmen"),
                        rs.getString("nip_petugas"),
                        rs.getString("waktu_simpan")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println("rekammedis.RMAsesmenUlangResikoJatuh.tampil() : " + e);
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
        tglAsesmen.setDate(new Date());
        cmbJam.setSelectedItem(Sequel.cariIsi("select time(now())").substring(0, 2));
        cmbMnt.setSelectedItem(Sequel.cariIsi("select time(now())").substring(3, 5));
        cmbDtk.setSelectedIndex(0);
        tampilFaktorResiko();
        autoNomorFaktorResiko();
        TtotSkor.setText("");
        Tkategori.setText("");
        TabPencegahan.setSelectedIndex(0);
        cmbTindakanCegah.setSelectedIndex(0);
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }

    private void getData() {
        nip = "";
        riwayatData = "";
        
        if (tbAsesmen.getSelectedRow() != -1) {
            TNoRw.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 0).toString());
            TNoRm.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 2).toString());
            TPasien.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 3).toString());
            TruangRwt.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 10).toString());
            Valid.SetTgl(tglAsesmen, tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 11).toString());
            cmbJam.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(0, 2));
            cmbMnt.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(3, 5));
            cmbDtk.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 12).toString().substring(6, 8));
            TkdFaktor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 1).toString());
            TtotSkor.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 6).toString());
            nip = tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 13).toString();
            TnmPetugas.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 8).toString());
            Tkategori.setText(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 9).toString());
            cmbTindakanCegah.setSelectedItem(tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 7).toString());
            tampilDataFaktorResiko();
            
            if (cmbTindakanCegah.getSelectedIndex() == 0) {
                TabPencegahan.setSelectedIndex(0);
            } else if (cmbTindakanCegah.getSelectedIndex() == 1) {
                TabPencegahan.setSelectedIndex(0);
            } else if (cmbTindakanCegah.getSelectedIndex() == 2) {
                TabPencegahan.setSelectedIndex(1);
            } else if (cmbTindakanCegah.getSelectedIndex() == 3) {
                TabPencegahan.setSelectedIndex(2);
            }
        }
    }
    
    public void isForm(){
        if (ChkInput.isSelected() == true) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 497));
            FormInput.setVisible(true);
            ChkInput.setVisible(true);
        } else if (ChkInput.isSelected() == false) {
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH, 20));
            FormInput.setVisible(false);
            ChkInput.setVisible(true);
        }
    }
    
    public void isCek() {
        BtnSimpan.setEnabled(akses.getcppt());
        BtnHapus.setEnabled(akses.getcppt());
        BtnGanti.setEnabled(akses.getcppt());
        MnRiwayatData.setEnabled(akses.getadmin());
    }

    private void tampilFaktorResiko() {
        Valid.tabelKosong(tabMode1);
        try {
            ps1 = koneksi.prepareStatement("SELECT * FROM master_faktor_resiko_igd where asesmen='Ulang Resiko Jatuh' ORDER BY kode_resiko");
            try {
                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    tabMode1.addRow(new Object[]{
                        false,
                        rs1.getString("kode_resiko"),
                        rs1.getString("faktor_resiko"),
                        rs1.getString("skala"),
                        rs1.getString("skor")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println(e);
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
    }
    
    public void setData(String norw, String norm, String nmpasien, String ruangan) {
        TNoRw.setText(norw);
        TNoRm.setText(norm);
        TPasien.setText(nmpasien);
        TruangRwt.setText(ruangan);
        TCari.setText(norw);
        
        if (Sequel.cariInteger("select count(-1) from asesmen_ulang_resiko_jatuh where no_rawat='" + norw + "'") > 0) {
            Valid.SetTgl(DTPCari1, Sequel.cariIsi("select tgl_asesmen from asesmen_ulang_resiko_jatuh where "
                    + "no_rawat='" + norw + "' order by tgl_asesmen desc limit 1"));
        } else {
            DTPCari1.setDate(new Date());
        }
        
        if (akses.getadmin() == true) {
            nip = "-";
            TnmPetugas.setText("-");
        } else {
            nip = akses.getkode();
            TnmPetugas.setText(Sequel.cariIsi("select nama from pegawai where nik='" + nip + "'"));
        }
    }
    
    public void autoNomorFaktorResiko() {
        Valid.autoNomer7("select ifnull(MAX(CONVERT(LEFT(kode_ulang_resiko,4),signed)),0) from asesmen_ulang_resiko_jatuh where "
                + "tgl_asesmen like '%" + Valid.SetTgl(tglAsesmen.getSelectedItem() + "").substring(0, 7) + "%' ", "/AURJ/" + Valid.SetTgl(tglAsesmen.getSelectedItem() + "").substring(5, 7)
                + "/" + Valid.SetTgl(tglAsesmen.getSelectedItem() + "").substring(0, 4), 4, TkdFaktor);
    }
    
    private void hitungSkorRJ() {
        skor = 0;
        for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
            if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                skor = skor + Integer.parseInt(tbFaktorResiko.getValueAt(i, 4).toString());
            }
        }
        
        TtotSkor.setText(Valid.SetAngka2(skor));
        if (skor >= 45) {
            Tkategori.setText("Resiko Tinggi : >= 45 --> Pasang Kancing Penanda Warna Kuning");
        } else if (skor >= 25 && skor <= 44) {
            Tkategori.setText("Resiko Sedang : 25 - 44 --> Pasang Kancing Penanda Warna Kuning");
        } else if (skor >= 0 && skor <= 24) {
            Tkategori.setText("Resiko Rendah : 0 - 24");
        }
    }
    
    private void tampilDataFaktorResiko() {
        Valid.tabelKosong(tabMode1);
        try {
            ps2 = koneksi.prepareStatement("SELECT a.kode_resiko, a.faktor_resiko, a.skala, a.skor, IF(ifnull(b.kode_resiko,'-')='-','-','dipilih') cekkode "
                    + "FROM master_faktor_resiko_igd a left join detail_asesmen_ulang_resiko_jatuh b on a.kode_resiko=b.kode_resiko "
                    + "and b.kode_ulang_resiko='" + TkdFaktor.getText() + "' where "
                    + "a.asesmen='Ulang Resiko Jatuh' order by a.kode_resiko");
            try {
                rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (rs2.getString("cekkode").equals("dipilih")) {
                        nilai = true;
                    } else {
                        nilai = false;
                    }
                    tabMode1.addRow(new Object[]{
                        nilai,
                        rs2.getString("kode_resiko"),
                        rs2.getString("faktor_resiko"),
                        rs2.getString("skala"),
                        rs2.getString("skor")
                    });
                }
                this.setCursor(Cursor.getDefaultCursor());
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (rs2 != null) {
                    rs2.close();
                }
                if (ps2 != null) {
                    ps2.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    private void SimpanHistori() {
        user = "";
        if (akses.getadmin() == true) {
            user = "-";
        } else {
            user = akses.getkode();
        }
        
        if (Sequel.menyimpantf("asesmen_ulang_resiko_jatuh_histori", "?,?,?,?,?,?,?,?,?,?,?,?,?", "No.Rawat", 13, new String[]{
            TNoRw.getText(), Valid.SetTgl(tglAsesmen.getSelectedItem() + ""), cmbJam.getSelectedItem() + ":" + cmbMnt.getSelectedItem() + ":" + cmbDtk.getSelectedItem(),
            TkdFaktor.getText(), TtotSkor.getText(), cmbTindakanCegah.getSelectedItem().toString(), nip, Tkategori.getText(),
            TruangRwt.getText(), tbAsesmen.getValueAt(tbAsesmen.getSelectedRow(), 14).toString(), riwayatData, user, Sequel.cariIsi("select now()")
        }) == true) {

            //simpan faktor resiko
            for (i = 0; i < tbFaktorResiko.getRowCount(); i++) {
                if (tbFaktorResiko.getValueAt(i, 0).toString().equals("true")) {
                    Sequel.menyimpan2("detail_asesmen_ulang_resiko_jatuh_histori", "?,?,?,?", 4, new String[]{
                        TNoRw.getText(), TkdFaktor.getText(), tbFaktorResiko.getValueAt(i, 1).toString(), riwayatData
                    });
                }
            }
        }
    }
    
    private void tampilRiwayat() {
        Valid.tabelKosong(tabMode2);
        try {
            psrestor = koneksi.prepareStatement("SELECT IF(pg.nama='-','Admin Utama',pg.nama) pelaku, a.no_rawat, p.no_rkm_medis, "
                    + "p.nm_pasien, a.tgl_asesmen, a.jam_asesmen, a.waktu_eksekusi, upper(concat('DI',a.status_data)) sttsdata, a.waktu_simpan, "
                    + "a.kode_ulang_resiko FROM asesmen_ulang_resiko_jatuh_histori a inner join reg_periksa rp on rp.no_rawat=a.no_rawat "
                    + "INNER JOIN pasien p ON p.no_rkm_medis = rp.no_rkm_medis INNER JOIN pegawai pg ON pg.nik = a.nik_eksekutor WHERE "
                    + "a.tgl_asesmen between ? and ? and pg.nama like ? or "
                    + "a.tgl_asesmen between ? and ? and a.no_rawat like ? or "
                    + "a.tgl_asesmen between ? and ? and p.no_rkm_medis like ? or "
                    + "a.tgl_asesmen between ? and ? and a.status_data like ? or "
                    + "a.tgl_asesmen between ? and ? and p.nm_pasien like ? order by a.tgl_asesmen desc");
            try {
                psrestor.setString(1, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(2, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(3, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(4, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(5, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(6, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(7, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(8, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(9, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(10, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(11, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(12, "%" + TCari2.getText().trim() + "%");
                psrestor.setString(13, Valid.SetTgl(DTPCari3.getSelectedItem() + ""));
                psrestor.setString(14, Valid.SetTgl(DTPCari4.getSelectedItem() + ""));
                psrestor.setString(15, "%" + TCari2.getText().trim() + "%");
                rsrestor = psrestor.executeQuery();
                while (rsrestor.next()) {
                    tabMode2.addRow(new String[]{
                        rsrestor.getString("pelaku"),
                        rsrestor.getString("no_rawat"),
                        rsrestor.getString("no_rkm_medis"),
                        rsrestor.getString("nm_pasien"),
                        rsrestor.getString("tgl_asesmen"),
                        rsrestor.getString("jam_asesmen"),
                        rsrestor.getString("waktu_eksekusi"),
                        rsrestor.getString("sttsdata"),
                        rsrestor.getString("waktu_simpan"),
                        rsrestor.getString("kode_ulang_resiko")
                    });
                }
            } catch (Exception e) {
                System.out.println("Notif : " + e);
            } finally {
                if (rsrestor != null) {
                    rsrestor.close();
                }
                if (psrestor != null) {
                    psrestor.close();
                }
            }
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
        LCount1.setText("" + tabMode2.getRowCount());
    }
    
    private void kembalikanData() {
        try {
            ps3 = koneksi.prepareStatement("select * from asesmen_ulang_resiko_jatuh_histori where "
                    + "waktu_eksekusi='" + tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 6).toString() + "'");
            try {
                rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    try {
                        if (Sequel.menyimpantf("asesmen_ulang_resiko_jatuh", "?,?,?,?,?,?,?,?,?,?", "No.Rawat", 10, new String[]{
                            rs3.getString("no_rawat"), rs3.getString("tgl_asesmen"), rs3.getString("jam_asesmen"),
                            rs3.getString("kode_ulang_resiko"), rs3.getString("total_skor"), rs3.getString("tindakan_pencegahan"), 
                            rs3.getString("nip_petugas"), rs3.getString("kategori"), rs3.getString("ruang_rawat"), rs3.getString("waktu_simpan")
                        }) == true) {

                            //simpan faktor resiko
                            try {
                                ps4 = koneksi.prepareStatement("select * from detail_asesmen_ulang_resiko_jatuh_histori where "
                                        + "kode_ulang_resiko='" + rs3.getString("kode_ulang_resiko") + "'");
                                try {
                                    rs4 = ps4.executeQuery();
                                    while (rs4.next()) {
                                        Sequel.menyimpan2("detail_asesmen_ulang_resiko_jatuh", "?,?,?", 3, new String[]{
                                        rs4.getString("no_rawat"), rs4.getString("kode_ulang_resiko"), rs4.getString("kode_resiko")});
                                    }
                                } catch (Exception e) {
                                    System.out.println("Notifikasi : " + e);
                                } finally {
                                    if (rs4 != null) {
                                        rs4.close();
                                    }
                                    if (ps4 != null) {
                                        ps4.close();
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Notifikasi : " + e);
                            }
                        }
                        //--------------------------------
                    } catch (Exception e) {
                        System.out.println("Simpan : " + e);
                    }
                }
            } catch (Exception e) {
                System.out.println("Notifikasi : " + e);
            } finally {
                if (rs3 != null) {
                    rs3.close();
                }
                if (ps3 != null) {
                    ps3.close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void kembalikanDataDiganti() {
        if (Sequel.queryu2tf("delete from asesmen_ulang_resiko_jatuh where waktu_simpan=?", 1, new String[]{
            tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 8).toString()
        }) == true) {
            Sequel.meghapus("detail_asesmen_ulang_resiko_jatuh", "kode_ulang_resiko",
                    tbRiwayat.getValueAt(tbRiwayat.getSelectedRow(), 9).toString());
            kembalikanData();
        } else {
            JOptionPane.showMessageDialog(null, "Gagal dikembalikan..!!");
        }
    }
}
